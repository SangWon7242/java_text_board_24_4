package com.sbs.exam.board.repository;

import com.sbs.exam.board.dto.Article;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ArticleRepository {
  private int lastId;

  @Getter
  private List<Article> articles;
  public ArticleRepository() {
    lastId = 0;
    articles = new ArrayList<>();

    if (articles.size() > 0) {
      lastId = articles.get(articles.size() - 1).getId();
    }
  }

  public int write(String title, String body) {
    int id = ++lastId;
    articles.add(new Article(id, title, body));

    return id;
  }

  public void remove(Article article) {
    articles.remove(article);
  }

  public void modify(int id, String title, String body) {
    Article article = findById(id);

    article.setTitle(title);
    article.setBody(body);
  }

  public Article findById(int id) {
    for (Article article : articles) {
      if (article.getId() == id) {
        return article;
      }
    }

    return null;
  }

}
