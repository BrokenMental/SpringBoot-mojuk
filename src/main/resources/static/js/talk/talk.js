// WebSocket 연결 생성
const socket = new WebSocket(`ws://${window.location.host}/ws/talk`);

// 연결 성공 시 호출
socket.onopen = function() {
    console.log('서버와 연결되었습니다.');
};

// 서버로부터 메시지를 수신
socket.onmessage = function(event) {
    const message = event.data;
    console.log('수신한 메시지:', message);
    // 화면에 메시지를 추가하는 로직
    displayMessage(message);
};

// 서버로 메시지 전송
function sendMessage(roomId, sender, content) {
    const message = {
        roomId: roomId,
        sender: sender,
        content: content
    };

    socket.send(JSON.stringify(message)); // 메시지 전송
}

// 메시지를 화면에 추가하는 함수
function displayMessage(message) {
    const messageContainer = document.getElementById('messages');
    const messageElement = document.createElement('div');
    messageElement.textContent = message;
    messageContainer.appendChild(messageElement);
}

function joinRoom(roomId) {
    // 서버에 특정 채팅방에 입장한다고 알리는 로직
    sendMessage(roomId, 'system', 'join');
}