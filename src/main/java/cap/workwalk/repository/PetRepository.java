package cap.workwalk.repository;

import cap.workwalk.entity.Pet;
import cap.workwalk.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Integer> {
    List<Pet> findByUser(User user);
}
