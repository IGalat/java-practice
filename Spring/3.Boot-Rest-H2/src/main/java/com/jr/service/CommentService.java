package com.jr.service;

import com.jr.model.Comment;
import com.jr.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;

/**
 * @author Galatyuk Ilya
 */

@Service
@Transactional
public class CommentService {

    private final CommentRepository repository;

    @Autowired
    public CommentService(CommentRepository repository) {
        this.repository = repository;
    }


    public List<Comment> getAll() {
        return repository.findAll();
    }

    public Comment getOne(Long id) {
        try {
            Comment comment = repository.getOne(id);
            Objects.requireNonNull(comment.getAuthor());
            return comment;
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Comment with id " + id + " is not in DB");
        }
    }

    public List<Comment> getByArticleId(Long articleId) {
        return repository.getByArticleId(articleId);
    }

    public List<Comment> getByAuthor(String author) {
        return repository.getByAuthor(author);
    }

    public Comment addOne(Comment comment) {
        if (comment.getId() != null) {
            throw new IllegalArgumentException("Trying to add comment with existing id " + comment.getId());
        }
        return repository.save(comment);
    }

    public Comment updateOne(Comment comment) {
        if (!repository.existsById(comment.getId()))
            throw new EntityNotFoundException("Trying to update non-existing comment! ID = " + comment.getId());
        return repository.save(comment);
    }

    public Iterable<Comment> addAll(Iterable<Comment> comments) {
        for (Comment comment : comments) {
            if (comment.getId() != null) {
                throw new EntityNotFoundException("Trying to add comment with existing id " + comment.getId());
            }
        }
        return repository.saveAll(comments);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
