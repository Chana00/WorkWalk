package cap.workwalk.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Getter
@Setter
@Builder
public class UserInfoDto implements Serializable {
    private String memberId;
    private String password;
    private String auth;

    private String name;
    private String nickname;
    private String address;
    private String phone;
    private String sex;
    private String birth;

    private String license;
    private String exp;
    private String imgUrl;

    private int point;
}
