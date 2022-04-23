package cap.workwalk.entity;

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
    @GeneratedValue
    private Integer id;

    @Column
    private String name;

    @Column
    private String sex;

    @Column
    private String birth;
    //견종
    @Column
    private String kind;
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
    private String imgUrl;


}
