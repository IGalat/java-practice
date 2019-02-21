package com.jr;

import com.jr.model.Article;
import com.jr.model.Comment;
import com.jr.service.ArticleService;
import com.jr.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 *
 */
@Component
public class InitActions implements CommandLineRunner {
    private final ArticleService articleService;

    private final CommentService commentService;

    @Autowired
    public InitActions(ArticleService articleService, CommentService commentService) {
        this.articleService = articleService;
        this.commentService = commentService;
    }

    @Override
    public void run(String... args) {
        fillExampleDataIfEmptyDB();
    }

    private void fillExampleDataIfEmptyDB() {
        if (articleService.getAll().size() == 0) {
            List<Article> articles = new ArrayList<>(Arrays.asList(
                    new Article("On bees and not bees", "To bee or not to be?")
                    , new Article("Java power summarized", "ITS OVER 9000!")
                    , new Article("Continued story lyrics", "Sono hitotsubu no shizuku de sae mo\n" +
                            "Hana o mamoru kamo shirenai\n" +
                            "Sono waraigao tada sore dake de\n" +
                            "Sashinoberu te ni mo nareru\n" +
                            "Sono furueteru koe atsumereba\n" +
                            "Kaze o okosu kamo shirenai\n" +
                            "Sono inochi to iu hakanaki akari\n" +
                            "Tomoshite ashi o susumeyou")
            ));
            articles = StreamSupport.stream(articleService.addAll(articles).spliterator(), false)
                    .collect(Collectors.toList());

            List<Comment> comments = new ArrayList<>(Arrays.asList(
                    new Comment(articles.get(0), "ShakespeareFan#1", "To be!")
                    , new Comment(articles.get(1), "Anon", "First! Hu ha")
                    , new Comment(articles.get(2), "Orange", "Subarashi desu ne")
                    , new Comment(articles.get(2), "Mao", "FFFFFFUUUUUUUUUUUUUUUUU-")
            ));
            comments = StreamSupport.stream(commentService.addAll(comments).spliterator(), false)
                    .collect(Collectors.toList());
        }
    }
}
