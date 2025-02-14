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