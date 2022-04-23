package cap.workwalk.adapter;

import cap.workwalk.entity.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class UserDetailsAdapter extends org.springframework.security.core.userdetails.User {

    private User user;
    //https://ziponia.github.io/2019/05/25/spring-security-custom-principal.html
    //아이디, 비밀번호, 권한을 생성하는 constructor
    public UserDetailsAdapter(User user) {
        super(user.getMemberId(), user.getPassword(), getAuthorities(user));
        this.user = user;
    }

    // 권한 생성
    private static Collection<? extends GrantedAuthority> getAuthorities(User user)
    {
        return user.getRoles().stream()
                .map((role) -> new SimpleGrantedAuthority("ROLE_" + role.getRolename()))
                .collect(Collectors.toSet());
    }
}
