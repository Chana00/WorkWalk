package cap.workwalk.repository;

import cap.workwalk.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findByPosttype(Integer posttype);
}
