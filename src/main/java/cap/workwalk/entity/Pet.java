package cap.workwalk.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="pet")
@Getter
@Setter
@NoArgsConstructor
public class Pet {
    @Id
    @Column(name="pet_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //반려견 이름
    @Column
    private String name;

    @Column
    private String sex;

    @Column
    private String birth;
    //견종
    @Column
    private String kind;

    //크기
    @Column
    private String size;

    //중성화 여부
    @Column
    private String neutering;
    //예방접종 여부
    @Column
    private String vaccination;
    //성격
    @Column
    private String personality;
    //이미지
    @Column
    private String img_url;

    @ManyToOne
    @JoinColumn(name="userId")
    private User user;


    @Builder
    public Pet(String name, String sex, String birth, String kind, String neutering, String vaccination, String personality, User user) {
        this.name=name;
        this.sex=sex;
        this.birth=birth;
        this.kind=kind;
        this.neutering=neutering;
        this.vaccination=vaccination;
        this.personality=personality;
        this.user=user;
    }



}
