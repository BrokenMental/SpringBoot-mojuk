function connectWebSocket() {
    // WebSocket 연결 생성
    const socket = new WebSocket(`ws://${window.location.host}/ws/talk`);

    socket.onopen = function() {
        console.log('서버 연결.');
        // 필요한 초기화 작업
    };

    socket.onmessage = function(event) {
        const message = JSON.parse(event.data);
        console.log('수신한 메시지:', message);
        displayMessage(message);
    };

    socket.onclose = function(event) {
        console.error('WebSocket 연결 종료.');
        //setTimeout(connect, 1000); // 1초 후에 재연결 시도
    };

    socket.onerror = function(error) {
        console.error('WebSocket 오류:', error);
        socket.close();
    };

    return socket;
}

// 메시지를 화면에 추가하는 함수
function displayMessage(message) {
    const messageContainer = document.getElementById('messages');
    const messageElement = document.createElement('div');
    messageElement.textContent = `${message.sender}: ${message.content}`;
    messageContainer.appendChild(messageElement);
}

// 서버로 메시지 전송
function sendMessage(socket, message) {
    if (socket && socket.readyState === WebSocket.OPEN) {

        socket.send(JSON.stringify(message));
    } else {
        console.error('WebSocket 연결되지 않음');
        //socket = connect();
        // 필요에 따라 재연결 로직 추가
    }
}