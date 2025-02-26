// 방 입장 성공 후 호출되는 함수
function onRoomJoined(roomId, email) {
    // 방 ID와 사용자 이메일 저장 (숨겨진 필드)
    document.getElementById('currentRoomId').value = roomId;
    document.getElementById('userEmail').value = email;

    // WebSocket 연결
    connectWebSocket(roomId);

    // 채팅 영역 표시
    document.querySelector('.message-contents-area').style.display = 'block';

    // 채팅 내역 가져오기
    getTalkHistory(roomId).then(messages => {
        // 기존 메시지 초기화
        document.getElementById('messages').innerHTML = '';

        // 메시지 표시
        messages.forEach(message => {
            displayMessage(message);
        });
    });
}

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
        // 방 생성 성공 시 방에 입장
        onRoomJoined(res.roomId, createForm.createEmail.value);

        // 팝업 닫기
        document.querySelector('.create-form').style.display = 'none';
    }).catch((err) => {
        console.log('방 생성 중 문제 발생:', err);
        alert('방 생성에 실패했습니다.');
    });
});

//참여 팝업에서 입장 버튼 클릭 시
document.querySelector('.btn-join-room-view').addEventListener('click', (e) => {
    const joinForm = document.joinForm;
    if (!joinForm.joinRoomId.value) {
        alert('방 번호를 입력해주세요.');
        joinForm.joinRoomId.focus();
        return;
    }

    if (!joinForm.joinPassword.value) {
        alert('비밀번호를 입력해주세요.');
        joinForm.joinPassword.focus();
        return;
    }

    joinRoom({
        roomId: joinForm.joinRoomId.value,
        password: joinForm.joinPassword.value
    }).then(res => {
        // 방 입장 성공 시 WebSocket 연결
        onRoomJoined(joinForm.joinRoomId.value, joinForm.joinEmail?.value || '참여자');

        // 팝업 닫기
        document.querySelector('.join-form').style.display = 'none';
    }).catch((err) => {
        console.log('방 입장 중 문제 발생:', err);
        alert('방 입장에 실패했습니다.');
    });
});

// 메시지 전송 관련 이벤트 리스너
document.querySelector('.input-messages').addEventListener('keydown', (e) => {
    if (e.keyCode === 13 || e.key === 'Enter') {
        const content = e.target.value.trim();
        if (content) {
            sendMessage(content);
        }
        e.preventDefault(); // 폼 제출 방지
    }
});

document.querySelector('.btn-chat-enter').addEventListener('click', (e) => {
    const inputMessages = document.querySelector('.input-messages');
    const content = inputMessages.value.trim();
    if (content) {
        sendMessage(content);
    }
});

//참여 방에서 닫기 버튼 클릭 시
document.querySelector('.btn-content-close').addEventListener('click', (e) => {
    document.querySelector('.message-contents-area').style.display = 'none';

    // WebSocket 연결 종료
    if (socket) {
        socket.close();
        socket = null;
    }
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