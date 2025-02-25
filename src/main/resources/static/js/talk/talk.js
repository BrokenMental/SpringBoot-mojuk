const getRooms = async () => {
    try {
        const response = await axios.get('/talk/rooms');
        return response.data;
    } catch (error) {
        console.error('채팅방 목록을 가져오는 중 오류 발생:', error);
        return [];
    }
};

const loadRooms = async () => {
    const rooms = await getRooms();
    const roomContainer = document.getElementById('roomList');
    roomContainer.innerHTML = ''; // 기존 목록 초기화

    rooms.forEach(room => {
        const roomElement = document.createElement('div');
        roomElement.textContent = `방 ID: ${room.roomId}`;
        roomElement.onclick = () => joinRoom(room.roomId); // 클릭 시 입장
        roomContainer.appendChild(roomElement);
    });
};

// 페이지 로드 시 채팅방 목록 가져오기
//window.onload = loadRooms;

//생성 팝업 열기
document.querySelector('.btn-create-room').addEventListener('click', (e) => {
    document.querySelector('.create-form').style.display = 'block';
});

//참여 팝업 열기
document.querySelector('.btn-join-room').addEventListener('click', (e) => {
    document.querySelector('.join-form').style.display = 'block';
});

//팝업 닫기
document.querySelectorAll('.form-cancel').forEach((e) => {
    e.addEventListener('click', (e) => {
        document.querySelectorAll('.popup-background').forEach((e) => {
            e.style.display = 'none';
        });
    });
});

//생성 팝업에서 생성 버튼 클릭 시
document.querySelector('.btn-create-room-view').addEventListener('click', (e) => {
    const createForm = document.createForm;
    if (!createForm.createEmail.value) {
        alert('이메일을 입력해주세요.');
        createForm.createEmail.focus();
        return;
    }

    const regExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;

    if (!createForm.createEmail.value.match(regExp)) {
        alert('올바른 메일 형식으로 입력해주세요.');
        createForm.createEmail.focus();
        return;
    }

    if (!createForm.createPassword.value) {
        alert('비밀번호를 입력해주세요.');
        createForm.createPassword.focus();
        return;
    }

    if (createForm.createPassword.value.length < 8) {
        alert('비밀번호는 8자 이상으로 입력해주세요.');
        createForm.createPassword.focus();
        return;
    }

    createRoom({
        email: createForm.createEmail.value,
        password: createForm.createPassword.value,
        roomId: '',
    }).then(res => {
        document.querySelector('.message-contents-area').style.display = 'block';
    }).catch((err) => {
        console.log('문제 발생');
    });
});

//방 생성(email, password)
const createRoom = async (inData) => {
    try {
        const response = await axios.post('/talk/room/create', inData);
        console.log('채팅방 생성 성공:', response.data);
        return response.data;
    } catch (error) {
        console.error('채팅방 생성 중 오류 발생:', error);
        throw error;
    }
};

//방 참여(roomId, password)
const joinRoom = async (inData) => {
    try {
        const response = await axios.post('/talk/room/join', inData);

        console.log('방 입장 성공:', response.data);
        return response.data;
    } catch (error) {
        console.log('방 입장 실패:', error.response?.data || error.message);
        throw error;
    }
}

//채팅 내역 가져오기
const getTalkHistory = async (roomId) => {
    try {
        const response = await axios.get(`/talk/room/history/${roomId}`);
        console.log("채팅 내역:", response.data);
        return response.data;
    } catch (error) {
        console.error("채팅 내역 가져오기 실패:", error.response?.data || error.message);
    }
}

//참여 방에서 닫기 버튼 클릭 시
document.querySelector('.btn-content-close').addEventListener('click', (e) => {
    document.querySelector('.message-contents-area').style.display = 'none';
});