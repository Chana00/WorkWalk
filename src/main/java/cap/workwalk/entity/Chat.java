package cap.workwalk.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "chat")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne
    @JoinColumn(name = "senduser")
    User send;

    @ManyToOne
    @JoinColumn(name = "receiveuser")
    User receive;


    String content;

    @Builder
    public Chat(Integer id, User send, User receive, String content){
        this.id=id;
        this.send=send;
        this.receive=receive;
        this.content=content;
    }
}
