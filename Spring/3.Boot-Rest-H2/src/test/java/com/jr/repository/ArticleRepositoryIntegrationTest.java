package com.jr.repository;

import com.jr.model.Article;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * This boots context and DB
 * Gotta test only complicated methods, no sense testing basics
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class ArticleRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ArticleRepository articleRepository;

    @Test
    public void whenFindByName_thenReturnArticle(){
        Article zen = new Article("Zen name", "Zen is important, mkay kids?");
        entityManager.persist(zen);
        entityManager.flush();

        Article found = articleRepository.getByName(zen.getName());

        Assert.assertEquals(zen.getText(), found.getText());
    }
}
