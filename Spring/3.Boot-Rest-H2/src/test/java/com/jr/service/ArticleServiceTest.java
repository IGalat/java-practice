package com.jr.service;

import com.jr.model.Article;
import com.jr.repository.ArticleRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Typical service test. @RunWith(MockitoJUnitRunner.class) and not with SpringRunner, so no spring context loaded;
 * make @Mock repo, @InjectMocks in service; in test methods mock corresponding things for repo
 * <p>
 * making separate mocks in each method is good, apparently. Advantage is seeing what's mocked.
 * with mocks that are used in many methods you can't use @BeforeClass, and have to use @Before, which sucks
 */
@RunWith(MockitoJUnitRunner.class)
public class ArticleServiceTest {
    Article zen = new Article("Zen name", "Zen is important, mkay kids?");

    @Mock // using @Mock with @RunWith(MockitoJUnitRunner.class) works, unlike Mockito.mock()
    private ArticleRepository articleRepositoryMock;

    @InjectMocks // it depends on constructor args of articleService; only works with class, not an interface
    private ArticleService articleService;

    @Test
    public void getByName() {
        Mockito.when(articleRepositoryMock.getByName(zen.getName())).thenReturn(zen);

        Article article = articleService.getByName(zen.getName());
        Assert.assertEquals(zen.getText(), article.getText());
    }

    @Test(expected = RuntimeException.class)
    public void updateOne(){
        Mockito.when(articleRepositoryMock.existsById(null)).thenReturn(false);

        articleService.updateOne(zen);
    }
}
