let socket = null;

function connectWebSocket(roomId) {
    // 기존 연결이 있으면 닫기
    if (socket && socket.readyState === WebSocket.OPEN) {
        socket.close();
    }

    // WebSocket 연결 생성
    socket = new WebSocket(`ws://${window.location.host}/ws/talk/${roomId}`);

    socket.onopen = function() {
        console.log(`방 ${roomId}에 연결되었습니다.`);
        // 필요한 초기화 작업
    };

    socket.onmessage = function(event) {
        const message = JSON.parse(event.data);
        console.log('수신한 메시지:', message);
        displayMessage(message);
    };

    socket.onclose = function(event) {
        console.log(`방 ${roomId} 연결 종료됨:`, event);
        //자동 재연결
        //setTimeout(() => connectWebSocket(roomId), 1000);
        document.querySelector('.message-contents-area').style.display = 'none';
    };

    socket.onerror = function(error) {
        console.error('WebSocket 오류:', error);
        socket.close();
    };

    return socket;
}

// 메시지를 화면에 추가하는 함수
function displayMessage(msg) {
    const msgContainer = document.getElementById('messages');
    const msgElement = document.createElement('div');

    // 메시지 형식에 따라 표시
    if (msg.sender) {
        msgElement.textContent = `${msg.sender}: ${msg.message}`;
    } else {
        msgElement.textContent = msg; // 단순 문자열일 경우
    }

    msgContainer.appendChild(msgElement);

    // 스크롤을 항상 최신 메시지로 이동
    msgContainer.scrollTop = msgContainer.scrollHeight;
}

// 서버로 메시지 전송(message)
function sendMessage(message) {
    if (!socket || socket.readyState !== WebSocket.OPEN) {
        console.error('WebSocket 이 연결되어 있지 않습니다.');
        return;
    }

    // 현재 활성화된 방 ID 가져오기
    const currentRoomId = document.getElementById('currentRoomId').value;
    const userEmail = document.getElementById('userEmail').value;
    const userType = document.getElementById('userType').value;

    const msg = {
        roomId: currentRoomId,
        sender: userType == 1 ? '방장' : '참여자',
        message: message,
    };

    socket.send(JSON.stringify(msg));
}