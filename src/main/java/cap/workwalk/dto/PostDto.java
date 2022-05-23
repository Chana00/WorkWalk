package cap.workwalk.dto;


import cap.workwalk.entity.Post;
import cap.workwalk.entity.User;
import lombok.Builder;
import lombok.Getter;


@Getter
//@Setter 사용 지양. 대신 @Builder 사용
public class PostDto {
    private Integer id;
    private String content;
    private String status;
    private String posttype;
    private User user;


    public Post toEntity(){
        Post post = Post.builder()
                .id(id)
                .content(content)
                .status(status)
                .posttype(posttype)
                .user(user)
                .build();

                return post;
    }

    @Builder
    public PostDto(Integer id, String content, String status, String posttype, User user) {
        this.id=id;
        this.content = content;
        this.status = status;
        this.posttype=posttype;
        this.user=user;

    }
}