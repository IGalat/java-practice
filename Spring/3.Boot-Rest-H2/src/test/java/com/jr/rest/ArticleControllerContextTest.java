package com.jr.rest;

import com.jr.model.Article;
import com.jr.service.ArticleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * SpringRunner for actual context(takes time).
 * This is not the best way! We can test without SpringRunner and context @see ArticleControllerTest
 * <p>
 * WebMvcTest: focuses us solely on Spring MVC components. Needs SpringRunner.
 * Is it the reason why InitActions don't interfere?
 * We can get more control over MockMvc with @AutoConfigureMockMvc. dunno
 * <p>
 * The only mock in this case is ArticleService
 */

@RunWith(SpringRunner.class)
@WebMvcTest(ArticleController.class)
@Import(ArticleService.class)
public class ArticleControllerContextTest {
    Article zen = new Article("Zen name", "Zen is important, mkay kids?");

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ArticleService articleService;

    @Test
    public void getOne() throws Exception {
        List<Article> articles = Collections.singletonList(zen);
        articleService.addOne(zen);

        given(articleService.getAll()).willReturn(articles);

        mockMvc.perform(get("/articles").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(zen.getName())));
    }
}
