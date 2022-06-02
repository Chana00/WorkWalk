package cap.workwalk.service;


import cap.workwalk.dto.PostDto;
import cap.workwalk.entity.Post;
import cap.workwalk.entity.Reservation;
import cap.workwalk.entity.User;
import cap.workwalk.repository.PostRepository;
import cap.workwalk.repository.ReservationRepository;
import cap.workwalk.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PostService {
    //@RequiredArgsConstructor 로 생성되는 생성자로 주입받기 위해 final 추가
    private final PostRepository postRepository;
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;


    @Transactional
    public Integer savePost(PostDto postDto) {
        return postRepository.save(postDto.toEntity()).getId();
    }

    @Transactional //user 테이블 fetch=FetchType.LAZY -> transactional 사용해서 데이터 불러오기
    public List<Post> findByPosttype(String posttype) {
        return postRepository.findByPosttype(posttype);
    }

    @Transactional
    public Post findById(Integer id) {
        Optional<Post> post = postRepository.findById(id);
        if (!post.isPresent()) {
            throw new IllegalArgumentException();
        }
        return post.get();
    }

    public Integer saveReservation(Reservation rese) {
        rese.setState("산책대기");
        return reservationRepository.save(rese).getId();

    }

    /*@Transactional
    public void deletePost(Integer id) {
        postRepository.deleteById(id);
    }*/

}


