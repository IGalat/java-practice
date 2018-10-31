package com.jr.rest;

import com.jr.model.Article;
import com.jr.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Galatyuk Ilya
 */

@RestController
@RequestMapping("article")
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }


    //method GET is default, no need to specify RequestMethod.GET(but @RequestMapping is mandatory)
    @RequestMapping(method = RequestMethod.GET)
    public List<Article> getAll() {
        return articleService.getAll();
    }

    @RequestMapping("/{id}")
    public Article getOne(@PathVariable Long id) {
        return articleService.getOne(id);
    }

    @RequestMapping("/name/{name}")
    public Article getByName(@PathVariable String name) {
        return articleService.getByName(name);
    }

    @RequestMapping("/stats")
    public List<Article.ArticleStats> getStats() {
        return articleService.getStats();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Long addOne(@RequestBody Article article) {
        return articleService.addOne(article).getId();
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void updateOne(@RequestBody Article article) {
        articleService.updateOne(article);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deleteOne(@PathVariable Long id) {
        articleService.deleteById(id);
    }
}
