package cap.workwalk.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="reservation")
@Getter
@Setter
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer my_id;
    private Integer other_id;
    private String other_nickname;
    private Integer post_id;
    private LocalDateTime createdAt;
    private String state;

    @PrePersist
    public void createdAt() {
        this.createdAt = LocalDateTime.now();
    }


}
