package com.jr.service;

import com.jr.model.Comment;
import com.jr.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        return repository.getOne(id);
    }

    public List<Comment> getByArticleId(Long articleId) {
        return repository.getByArticleId(articleId);
    }

    public List<Comment> getByAuthor(String author) {
        return repository.getByAuthor(author);
    }

    public Comment addOne(Comment comment) {
        return repository.save(new Comment(comment.getArticle(), comment.getAuthor(), comment.getText()));
    }

    public Comment updateOne(Comment comment) {
        if (!repository.existsById(comment.getId()))
            throw new RuntimeException("Trying to update non-existing comment! ID = " + comment.getId());
        return repository.save(comment);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
