// CSRF 토큰 초기화 및 Axios 설정
(async function () {
    // Axios 기본 설정
    axios.defaults.withCredentials = true;  // 쿠키 전송 활성화

    // CSRF 토큰을 쿠키에서 가져오는 함수
    const getCsrfToken = () => {
        const cookies = document.cookie.split('; ');
        for (let cookie of cookies) {
            if (cookie.startsWith('XSRF-TOKEN=')) {
                return decodeURIComponent(cookie.split('=')[1]);
            }
        }
        return null;
    };

    // 요청 인터셉터 설정
    axios.interceptors.request.use(
        config => {
            config = config || {};
            config.headers = config.headers || {};

            const token = getCsrfToken();
            if (token) {
                console.log('CSRF 토큰 사용:', token);
                config.headers['X-XSRF-TOKEN'] = token;
            } else {
                console.warn('CSRF 토큰을 찾을 수 없습니다!');
            }

            return config;
        },
        error => {
            return Promise.reject(error);
        }
    );

    // 응답 인터셉터 설정
    axios.interceptors.response.use(
        response => {
            console.log('응답 성공:', response.status);
            return response;
        },
        error => {
            if (error.response) {
                console.error('응답 오류:', error.response.status, error.response.data);

                if (error.response.status === 403) {
                    console.error('CSRF 토큰 오류! 토큰을 다시 요청합니다.');
                    fetchCsrfToken(); // 토큰 재요청
                }
            } else if (error.request) {
                console.error('응답 없음:', error.request);
            } else {
                console.error('요청 오류:', error.message);
            }

            return Promise.reject(error);
        }
    );

    // CSRF 토큰 가져오기
    const fetchCsrfToken = async () => {
        try {
            console.log('CSRF 토큰 요청 중...');
            const response = await axios.get('/csrf/token');
            console.log('CSRF 토큰 응답:', response.data);

            // 쿠키 확인
            console.log('현재 쿠키:', document.cookie);

            return response.data.token;
        } catch (error) {
            console.error('CSRF 토큰 가져오기 실패:', error);
            return null;
        }
    };

    // 페이지 로드 시 CSRF 토큰 요청
    await fetchCsrfToken();
    console.log('초기화 완료, 쿠키 상태:', document.cookie);
})();