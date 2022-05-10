package cap.workwalk.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;


    @Column(columnDefinition = "TEXT")
    private String content;


    @Column
    private String status;

    @Column
    private Integer posttype;

    @ManyToOne
    @JoinColumn(name = "memberId", updatable = false)
    private User user;


    @Builder
    public Post(Integer id,String content, String status, Integer posttype, User user) {
        this.id=id;
        this.content = content;
        this.status = status;
        this.posttype=posttype;
        this.user=user;
    }
}