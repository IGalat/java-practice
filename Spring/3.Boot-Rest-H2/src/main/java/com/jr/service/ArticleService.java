package com.jr.service;

import com.jr.model.Article;
import com.jr.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Galatyuk Ilya
 */

@Service
@Transactional
public class ArticleService {

    private final ArticleRepository repository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.repository = articleRepository;
    }


    public List<Article> getAll() {
        return repository.findAll();
    }

    public Article getOne(Long id) {
        return repository.getOne(id);
    }

    public Article getByName(String name) {
        return repository.getByName(name);
    }

    public List<Article.ArticleStats> getStats() {
        return repository.getAllStatsBy();
    }

    public Article addOne(Article article) {
        if (article.getId() != null) {
            throw new RuntimeException("Trying to add article with existing id " + article.getId());
        }
        return repository.save(article);

        // Transaction rollback demo:
        /*Article article1 = repository.save(new Article(article.getName(), article.getText()));
        if (article1.getId() == 7)
            throw new RuntimeException("Transactional!");
        return article1;*/
    }

    public Article updateOne(Article article) {
        if (!repository.existsById(article.getId()))
            throw new RuntimeException("Trying to update non-existing article! ID = " + article.getId());
        return repository.save(article);
    }

    public Iterable<Article> addAll(Iterable<Article> articles) {
        for (Article article : articles) {
            if (article.getId() != null) {
                throw new RuntimeException("Trying to add article with existing id " + article.getId());
            }
        }

        return repository.saveAll(articles);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
