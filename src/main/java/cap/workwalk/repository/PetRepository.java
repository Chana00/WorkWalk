package cap.workwalk.repository;

import cap.workwalk.entity.Pet;
import cap.workwalk.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PetRepository extends JpaRepository<Pet, Integer> {
    Optional<Pet> findById(String PetId);
}
