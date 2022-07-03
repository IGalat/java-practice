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
@RequestMapping("comments")
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

    @GetMapping("/articles/{articleId}")
    public List<Comment> getByArticleId(@PathVariable Long articleId) {
        return commentService.getByArticleId(articleId);
    }

    @GetMapping("/authors/{author}")
    public List<Comment> getByAuthor(@PathVariable String author) {
        return commentService.getByAuthor(author);
    }

    @PostMapping
    public Comment addOne(@RequestBody Comment comment) {
        return commentService.addOne(comment);
    }

    @PatchMapping
    public void updateOne(@RequestBody Comment comment) {
        commentService.updateOne(comment);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteOne(@PathVariable Long id) {
        commentService.deleteById(id);
    }
}
