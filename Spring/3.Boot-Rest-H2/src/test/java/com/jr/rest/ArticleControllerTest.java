package com.jr.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jr.model.Article;
import com.jr.service.ArticleService;
import com.jr.service.BlogControllerAdvice;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.persistence.EntityNotFoundException;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * Best way to test the controller. No spring context, so no additional time for test class init.
 */

@RunWith(MockitoJUnitRunner.class)
public class ArticleControllerTest {
    Article zen = new Article("Zen name", "Zen is important, mkay kids?");
    ObjectMapper objectMapper = new ObjectMapper();

    private MockMvc mvc;

    @Mock
    private ArticleService articleService;

    @InjectMocks
    private ArticleController articleController;

    //sadly, init happens before every method, seems like no way around it
    @Before
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(articleController)
                .setControllerAdvice(new BlogControllerAdvice())
                .build();
    }

    @Test
    public void getOneExisting() throws Exception {
        given(articleService.getOne(1L)).willReturn(zen);

        MockHttpServletResponse response = mvc.perform(get("/article/1").accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assert.assertEquals(objectMapper.writeValueAsString(zen), response.getContentAsString());
    }

    // tests BlogControllerAdvice as well as controller. Which is meh
    @Test
    public void getOneNotExisting() throws Exception {
        given(articleService.getOne(1L)).willThrow(new EntityNotFoundException());

        MockHttpServletResponse response = mvc.perform(get("/article/1").accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        Assert.assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }
}
