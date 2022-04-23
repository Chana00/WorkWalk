package cap.workwalk.service;

import cap.workwalk.adapter.UserDetailsAdapter;
import cap.workwalk.dto.UserInfoDto;
import cap.workwalk.entity.Role;
import cap.workwalk.entity.User;
import cap.workwalk.repository.RoleRepository;
import cap.workwalk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

// 인증 관리자는 UserDetailsService 를 통해 UserDetails 객체를 획득하고
// 이 UserDetails 객체에서 인증 및 인가에 필요한 정보를 추출하여 사용한다.
@Service
@Transactional
public class CustomUserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public CustomUserService() {
        passwordEncoder = new BCryptPasswordEncoder();
    }


    //https://dncjf64.tistory.com/330
    //https://odol87.tistory.com/7
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Optional 객체의 orElseThrow로 null 체크
        User user = userRepository.findByMemberId(username)
                .orElseThrow(() -> new UsernameNotFoundException("ID : " + username + " not found"));
        System.out.println("유저 ID 데이터 반환 .. " + user.getId());

        return new UserDetailsAdapter(user);
    }


    //유저 회원가입
    public Integer signUpUser(User user, List<Role> userRoles) {
        roleRepository.saveAll(userRoles);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);

        return userRepository.save(user).getId();
    }

    public Role findByRolename(String rolename) {
        Role role = roleRepository.findByRolename(rolename);
        return role;
    }



}
