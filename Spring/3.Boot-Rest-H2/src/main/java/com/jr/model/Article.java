package com.jr.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Galatyuk Ilya
 */

@Entity
@Data
@NoArgsConstructor
//for problem with lazy loading via the hibernate proxy object:
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column
    @Lob // Large object. CLOB/TEXT instead of varchar in DB; no checks for distinct on this column when hibernate makes requests etc
    private String text;

    /**
     * How to make cascade delete if we wanna bidirectional:
     * 1) establish bidirectional connection: have it in both entity classes
     * 2) on the parent: orphanRemoval, cascade - for actual removing; mappedBy shows who's the parent
     */
//    @JsonIgnore
//    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "article")
//    private List<Comment> comments;
    public Article(String name, String text/*, List<Comment> comments*/) {
        this.name = name;
        this.text = text;
//        this.comments = comments;
    }

    /**
     * Projection https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#projections
     * if we wanna query part of properties of class:
     * 1) make interface with getters
     * 2) in Repo make method. getAll(), getAllStats() etc - FAIL
     * valid examples: getAllBy(), getAllStatsBy()
     */
    public interface ArticleStats {
        Long getId();

        String getName();
    }
}
