package com.sbs.exam.board.repository;

import com.sbs.exam.board.Util;
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

  public int write(String title, String body, String writerName, int memberId) {
    int id = ++lastId;
    articles.add(new Article(id, title, body, writerName, memberId));

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

  public List<Article> findByArticles(String keyword, String orderBy) {
    List<Article> filteredArticles = articles;

    // 검색 시작
    if (keyword.length() > 0) {
      filteredArticles = new ArrayList<>();

      for (Article article : articles) {
        boolean matched = article.getTitle().contains(keyword) || article.getBody().contains(keyword);

        if (matched) {
          filteredArticles.add(article);
        }
      }
    }
    // 검색 끝

    // 정렬 시작
    boolean orderByIdDesc = orderBy.equals("idDesc"); // 기존로직

    List<Article> sortedArticles = filteredArticles;

    if (orderByIdDesc) {
      sortedArticles = Util.reverseList(sortedArticles);
    }
    // 정렬 끝

    return sortedArticles;
  }
}
