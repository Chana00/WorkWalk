package cap.workwalk.repository;

import cap.workwalk.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

//Optional : https://dbbymoon.tistory.com/3
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByMemberId(String member_id);

    // 같은 주소를 가지고있는 유저리스트 검색
    @Query(nativeQuery = true, value =
                "SELECT * " +
                "FROM users " +
                "WHERE search_address = :searchAddress ")
    List<User> findDongAddress(String searchAddress);


}
