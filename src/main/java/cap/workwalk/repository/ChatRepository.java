package cap.workwalk.repository;

import cap.workwalk.entity.Chat;
import cap.workwalk.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Integer> {
    Page<Chat> findBySend(User send, Pageable pageable);
    Page<Chat> findByReceive(User receive, Pageable pageable);

}
