package cap.workwalk.dto;


import cap.workwalk.entity.Pet;
import cap.workwalk.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
@Getter
@Setter
public class PetDto {
    private String name;
    private String sex;
    private String birth;
    private String kind;
    private String neutering;
    private String vaccination;
    private String personality;
    private User user;


    public Pet toEntity(){
        Pet pet = Pet.builder()
                .name(name)
                .sex(sex)
                .birth(birth)
                .kind(kind)
                .neutering(neutering)
                .vaccination(vaccination)
                .personality(personality)
                .user(user)
                .build();

        return pet;
    }

    @Builder
    public PetDto(String name, String sex, String birth, String kind, String neutering, String vaccination, String personality, User user) {
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