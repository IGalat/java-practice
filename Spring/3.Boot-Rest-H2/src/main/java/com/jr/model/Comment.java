package com.jr.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

/**
 * @author Galatyuk Ilya
 */

@Entity
@Data
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // @Json... for article's id only to be serialized. also needs setter to deserialize(below)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("article_id")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    //for unidirectional delete:
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Article article;
    //so you can only save it with author, but not update it
    @Column(nullable = false, updatable = false)
    private String author;
    @Column
    @Lob
    private String text;

    public Comment(Article article, String author, String text) {
        this.article = article;
        this.author = author;
        this.text = text;
    }

    //is needed for deserialization from article_id
    @JsonProperty("article_id")
    public void setArticle(Long articleId) {
        Article temp = new Article();
        temp.setId(articleId);
        this.article = temp;
    }

    public static void main(String[] args) {
        String lala = "ыыыыїїїї私企業";

        JSONPObject
    }
}
