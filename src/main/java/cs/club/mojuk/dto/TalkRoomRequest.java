package cs.club.mojuk.dto;

//@JsonInclude(JsonInclude.Include.NON_NULL) // roomId가 null이면 JSON 변환 시 제외
public record TalkRoomRequest(String email, String password, String roomId) {
}
