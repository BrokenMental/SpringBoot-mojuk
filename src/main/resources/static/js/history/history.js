const swiper = new Swiper('.swiper-container', {
    loop: true,
    autoplay: false, /*{
        delay: 10000,
        disableOnInteraction: false, // 사용자가 상호작용 후에도 자동재생 유지
        pauseOnMouseEnter: true, // 마우스가 슬라이더 위에 있을 때 자동 이동 멈춤
    },*/

    pagination: {
        el: '.swiper-pagination',
        clickable: true,
        renderBullet: function (index, className) {
            //const years = [2021, 2022, 2023];
            return `<span class="${className}"></span>`;
        },
    },

    navigation: {
        nextEl: '.swiper-button-next',
        prevEl: '.swiper-button-prev',
    },

    scrollbar: {
        el: '.swiper-scrollbar',
    },

    spaceBetween: 10,
});

const manageYearList = async () => {
    try {
        const response = await axios.get('/history/manageYears');
        const years = response.data.map(yearList => yearList.manageYear);

        // Swiper 슬라이드 업데이트
        swiper.removeAllSlides();
        years.forEach((year, index) => {
            //swiper-slide 클래스 elements 하위 요소 추가
            swiper.appendSlide(`<div class="swiper-slide">
<div class="swiper-slide-header">Slide ${index + 1}</div>
<div class="swiper-slide-body">
    <div id="usersContainer_${year}">
        <input class="search" placeholder="검색" />
        <div>
            <span>정렬</span>
            <button class="sort" data-sort="name">이름</button>
            <button class="sort" data-sort="entranceYear">입학년도</button>
            <button class="sort" data-sort="levelLabel">직무</button>
        </div>
        <ul class="list"></ul>
    </div>
</div>`);
        });

        // 페이지네이션 불릿에 연도 데이터 추가
        const bullets = document.querySelectorAll('.swiper-pagination .swiper-pagination-bullet');
        bullets.forEach((bullet, index) => {
            bullet.textContent = years[index];
        });

        // 루프 모드 다시 생성
        swiper.update();
        swiper.pagination.update();

        clubDisplay(years[0]);

        // 이벤트 리스너 등록
        swiper.on('slideChange', function () {
            const activeIndex = swiper.realIndex;
            const selectedYear = years[activeIndex];
            clubDisplay(selectedYear);
        });
    } catch (err) {
        console.error('데이터를 가져오는 중 오류가 발생했습니다:', err);
    }
}

//페이지 초기화
manageYearList();

/* List.js */
const clubDisplay = (year) => {
    const options = {
        valueNames: [ 'levelLabel', 'name', 'entranceYear' ],
        item: '<li>' +
            '<span class="levelLabel"></span>' +
            '<h3 class="name"></h3>' +
            '<p class="entranceYear"></p>' +
            '<div>' +
            '<i class="fa-brands fa-git-alt"></i>' +
            '<i class="fa-solid fa-house"></i>' +
            '</div>' +
            '</li>'
    };

    //levelLabel 99: 게스트, 0: 지도교수, 1: 회장, 2: 부회장, 3: 학년부장, 4: 회원
    axios.get('/history/studentList?manageYear='+year
    ).then((res) => {
        const studentList = res.data;

        const transformStudentList = studentList.map(student => ({
            ...student,
            levelLabel: student.level.label
        }));

        //
        const userList = new List(`usersContainer_${year}`, options);
        userList.clear();
        transformStudentList.forEach(studentData => {
            userList.add(studentData);
        });

        const usersContainer = document.getElementById(`usersContainer_${year}`);
        usersContainer.addEventListener('click', function(e) {
            //git 버튼
            if(e.target.classList.contains('fa-git-alt')) {
                const inli = e.target.closest('li');
                const levelLabel = inli.querySelector('.levelLabel').textContent;
                const name = inli.querySelector('.name').textContent;
                const entranceYear = inli.querySelector('.entranceYear').textContent;

                console.log(`${levelLabel} ${name} ${entranceYear}`);
            }

            //홈페이지 버튼
            if(e.target.classList.contains('fa-house')) {
                const inli = e.target.closest('li');
                const levelLabel = inli.querySelector('.levelLabel').textContent;
                const name = inli.querySelector('.name').textContent;
                const entranceYear = inli.querySelector('.entranceYear').textContent;

                console.log(`${levelLabel} ${name} ${entranceYear}`);
            }
        });
    }).catch((err) => {

    })
}
/* List.js */

