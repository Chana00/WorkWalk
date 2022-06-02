package cap.workwalk.repository;

import cap.workwalk.entity.Pet;
import cap.workwalk.entity.Reservation;
import cap.workwalk.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    Optional<Reservation> findById(Integer id);


    @Query(nativeQuery = true, value =
            "SELECT distinct reservation.* " +
                    "FROM reservation, users " +
                    "WHERE reservation.my_id = :userId and reservation.my_id = users.id")
    List<Reservation> findMyReservationById(Integer userId);
}
