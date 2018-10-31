package com.jr.repository;

import com.jr.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Galatyuk Ilya
 */

public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article.ArticleStats> getAllStatsBy();

    Article getByName(String name);
}
