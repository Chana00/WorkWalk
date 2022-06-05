package cap.workwalk.service;

import cap.workwalk.adapter.UserDetailsAdapter;
import cap.workwalk.dto.UserInfoDto;
import cap.workwalk.entity.Pet;
import cap.workwalk.entity.Reservation;
import cap.workwalk.entity.Role;
import cap.workwalk.entity.User;
import cap.workwalk.repository.PetRepository;
import cap.workwalk.repository.ReservationRepository;
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
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.security.Principal;
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

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private ReservationRepository reservationRepository;

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

    public Integer registrationPet(String username, Pet pet) {
        User user = userRepository.findByMemberId(username)
                .orElseThrow(() -> new UsernameNotFoundException("ID : " + username + " not found"));
        System.out.println("펫 새로 등록 " + user.getName());
        pet.setUser(user);

        return petRepository.save(pet).getId();
    }

    public void profileImgSave(MultipartFile uploadImg, Principal principal) throws IOException {
        User user = userRepository.findByMemberId(principal.getName())
                .orElseThrow(() -> new UsernameNotFoundException("ID not found"));
        String ext = uploadImg.getOriginalFilename().substring(uploadImg.getOriginalFilename().lastIndexOf(".") + 1);   //확장자 구하기
        //System.out.println("파일이 넘어왔습니다.. 확장자 : " + ext + " .. " + uploadImg.getOriginalFilename());
        if(ext.equals("jpg") || ext.equals("png") || ext.equals("jpeg")) {
            File newImg = new File(user.getName()+ "_profile." +ext);
            uploadImg.transferTo(newImg);
            user.setImgUrl(newImg.getName());
            userRepository.save(user);
            //System.out.println(user.getImgUrl() + "입니다");
        }

    }

    public void reservationWalkingChange(Principal principal, Integer reserId){
        Reservation reser = reservationRepository.findById(reserId)
                .orElseThrow(() -> new UsernameNotFoundException("Reservation ID not found"));

        reser.setState("산책완료");
        reservationRepository.save(reser);
    }


}
