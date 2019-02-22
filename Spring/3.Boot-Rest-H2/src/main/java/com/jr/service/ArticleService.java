package com.jr.service;

import com.jr.model.Article;
import com.jr.repository.ArticleRepository;
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
        try {
            Article article = repository.getOne(id);
            // fetch is lazy, so we have to access some method except getId
            // Objects.requireNonNull(getId()) gets optimized to nothing; and just getName() too.
            // with this we unpack the proxy and get EntityNotFoundException if none. It's a crutch...
            Objects.requireNonNull(article.getName());
            return article;

            // this is purely for exception type unification and better message
            // we need the right type for intercepting and giving BAD_REQUEST and not INTERNAL_SERVER_ERROR
        } catch (EntityNotFoundException e) {
            throw new IllegalArgumentException("Article with id " + id + " is not in DB");
        }
    }

    public Article getByName(String name) {
        Article article = repository.getByName(name);
        if (article == null) {
            throw new IllegalArgumentException("Article with name '" + name + "' is not in DB");
        }
        return article;
    }

    public List<Article.ArticleStats> getStats() {
        return repository.getAllStatsBy();
    }

    public Article addOne(Article article) {
        if (article.getId() != null) {
            throw new IllegalArgumentException("Trying to add article with existing id " + article.getId());
        }

        // Transaction rollback demo:
        /*if (article1.getId() == 7)
            throw new RuntimeException("Transactional!");
        return article1;*/
        return repository.save(article);
    }

    public Article updateOne(Article article) {
        if (!repository.existsById(article.getId()))
            throw new IllegalArgumentException("Trying to update non-existing article! ID = " + article.getId());
        return repository.save(article);
    }

    public Iterable<Article> addAll(Iterable<Article> articles) {
        for (Article article : articles) {
            if (article.getId() != null) {
                throw new IllegalArgumentException("Trying to add article with existing id " + article.getId());
            }
        }

        return repository.saveAll(articles);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
