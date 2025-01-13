function connect() {
    // WebSocket 연결 생성
    const socket = new WebSocket(`ws://${window.location.host}/ws/talk`);

    socket.onopen = function() {
        console.log('서버와 연결되었습니다.');
        // 필요한 초기화 작업
    };

    socket.onmessage = function(event) {
        const message = JSON.parse(event.data);
        console.log('수신한 메시지:', message);
        displayMessage(message);
    };

    socket.onclose = function(event) {
        console.error('WebSocket 연결이 닫혔습니다. 재연결을 시도 중...', event.code, event.reason);
        setTimeout(connect, 1000); // 1초 후에 재연결 시도
    };

    socket.onerror = function(error) {
        console.error('WebSocket 오류:', error);
        socket.close();
    };

    return socket;
}

let socket = connect();

// 메시지를 화면에 추가하는 함수
function displayMessage(message) {
    const messageContainer = document.getElementById('messages');
    const messageElement = document.createElement('div');
    messageElement.textContent = `${message.sender}: ${message.content}`;
    messageContainer.appendChild(messageElement);
}

// 서버로 메시지 전송
function sendMessage(roomId, sender, content, type) {
    if (isOpen(socket)) {
        if(!type) type = 'CONNECTING';

        socket.send(JSON.stringify({
            type: type,
            roomId: roomId,
            sender: sender,
            content: content
        }));
    } else {
        console.error('WebSocket 연결이 닫혀 있어 메시지를 전송할 수 없습니다.');
        socket = connect();
        // 필요에 따라 재연결 로직 추가
    }
}

function joinRoom(roomId) {
    // 서버에 특정 채팅방에 입장한다고 알리는 로직
    sendMessage(roomId, 'system', 'join', 'JOIN');
}

//소캣 열린상태인지 확인
function isOpen(ws) {
    return ws.readyState === WebSocket.OPEN;
}

//서버 연결 종료 방지
function sendPing() {
    if (isOpen(socket)) {
        socket.send(JSON.stringify({ type: 'ping' }));
    }
}