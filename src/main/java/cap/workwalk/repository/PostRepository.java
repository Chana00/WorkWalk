package cap.workwalk.repository;

import cap.workwalk.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    Page<Post> findByPosttype(String posttype, Pageable pageable);
    Post findByPosttypeAndId(String posttype, Integer id);
}
