package cap.workwalk.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    //실제 사용자 아이디, notnull, 중복X
    @Column(nullable=false, unique=true)
    private String memberId;

    //비밀번호

    @Column(nullable=false)
    private String password;

    //이름
    @Column
    private String name;

    //닉네임
    @Column
    private String nickname;

    //주소
    @Column
    private String address;

    //연락처
    @Column
    private String phone;

    //성별
    @Column
    private String sex;

    //생일
    @Column
    private String birth;

    //자격증 여부
    @Column
    private String license;

    //경험 여부
    @Column
    private String exp;

    //프로필사진
    @Column
    private String imgUrl;

    @Column
    private int point;

    @OneToMany(mappedBy =  "user", fetch = FetchType.EAGER)
    private List<Pet> pets = new ArrayList<Pet>();

    @OneToMany(mappedBy ="user", fetch =FetchType.LAZY)
    private List<Post> posts = new ArrayList<Post>();

    //유저인지 관리자인지 설정 user-role 양방향 many-many 관계
    @ManyToMany(cascade=CascadeType.MERGE)
    @JoinTable(
            name="user_role",
            joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="ID")},
            inverseJoinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="ID")})
    private List<Role> roles;


}
