package com.jr.rest;

import com.jr.model.Comment;
import com.jr.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Galatyuk Ilya
 */

@RestController
@RequestMapping("comment")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    //another @, to avoid specifying methods separately:
    @GetMapping
    public List<Comment> getAll() {
        return commentService.getAll();
    }

    @GetMapping("/{id}")
    public Comment getOne(@PathVariable Long id) {
        return commentService.getOne(id);
    }

    @GetMapping("/article/{articleId}")
    public List<Comment> getByArticleId(@PathVariable Long articleId) {
        return commentService.getByArticleId(articleId);
    }

    @GetMapping("/author/{author}")
    public List<Comment> getByAuthor(@PathVariable String author) {
        return commentService.getByAuthor(author);
    }

    @PostMapping
    public Long addOne(@RequestBody Comment comment) {
        return commentService.addOne(comment).getId();
    }

    @PutMapping
    public void updateOne(@RequestBody Comment comment) {
        commentService.updateOne(comment);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteOne(@PathVariable Long id) {
        commentService.deleteById(id);
    }
}
