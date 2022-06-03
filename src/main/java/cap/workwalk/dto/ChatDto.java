package cap.workwalk.dto;

import cap.workwalk.entity.Chat;
import cap.workwalk.entity.User;
import lombok.Builder;
import lombok.Getter;


@Getter
public class ChatDto {
    private Integer id;
    private String content;
    private User send;
    private User receive;


    public Chat toEntity(){
        Chat chat = Chat.builder()
                .id(id)
                .content(content)
                .send(send)
                .receive(receive)
                .build();

        return chat;
    }

    @Builder
    public ChatDto(Integer id, String content, User send, User receive) {
        this.id=id;
        this.content = content;
        this.send=send;
        this.receive=receive;
    }
}
