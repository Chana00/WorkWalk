package cap.workwalk.repository;

import cap.workwalk.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//Optional : https://dbbymoon.tistory.com/3
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByMemberId(String member_id);
}
