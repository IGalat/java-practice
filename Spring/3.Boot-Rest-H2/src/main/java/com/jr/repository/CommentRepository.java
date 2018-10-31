package com.jr.repository;

import com.jr.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Galatyuk Ilya
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> getByArticleId(Long articleId);

    List<Comment> getByAuthor(String author);
}
