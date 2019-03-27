package kr.hs.dgsw.demo.Repository;

import kr.hs.dgsw.demo.Domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository
        extends JpaRepository<Comment, Long> {
}
