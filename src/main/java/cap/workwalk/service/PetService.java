package cap.workwalk.service;


import cap.workwalk.dto.PetDto;
import cap.workwalk.entity.User;
import cap.workwalk.repository.PetRepository;
import cap.workwalk.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.ManyToAny;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;


@Service
@RequiredArgsConstructor
public class PetService {
    private final PetRepository petRepository;
    private final UserRepository userRepository;

    @Transactional
    public Integer petSave(Integer userId, PetDto petDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 userId가 없습니다. id=" + userId));
        petDto.setUser(user);
        return petRepository.save(petDto.toEntity()).getUser().getId();
    }
}
