package cap.workwalk.repository;

import cap.workwalk.entity.Pet;
import cap.workwalk.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PetRepository extends JpaRepository<Pet, Integer> {
    Optional<Pet> findById(String PetId);

    List<Pet> findByUser(User user);

    // 같은 주소에 살고있는 강아지 목록 검색
    @Query(nativeQuery = true, value =
            "SELECT distinct pet.* " +
                    "FROM pet, users " +
                    "WHERE users.search_address = :searchAddress ")
    List<Pet> findListByAddress(String searchAddress);
}
