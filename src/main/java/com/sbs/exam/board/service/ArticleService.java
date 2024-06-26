package com.sbs.exam.board.service;

import com.sbs.exam.board.dto.Article;
import com.sbs.exam.board.container.Container;
import com.sbs.exam.board.repository.ArticleRepository;

import java.util.List;

public class ArticleService {
  private ArticleRepository articleRepository;
  public ArticleService() {
    articleRepository = Container.getArticleRepository();
  }

  public void makeTestData() {
    for(int i = 1; i <= 100; i++) {
      String title = "제목" + i;
      String body = "내용" + i;
      String writerName = "회원1";
      int memberId = 1;
      write(title, body, writerName, memberId);
    }
  }

  public int write(String title, String body, String writerName, int memberId) {
    return articleRepository.write(title, body, writerName, memberId);
  }

  public Article findById(int id) {
    return articleRepository.findById(id);
  }

  public void remove(Article article) {
    articleRepository.remove(article);
  }

  public void modify(int id, String title, String body) {
    articleRepository.modify(id, title, body);
  }

  public List<Article> getArticles(String searchKeywordTypeCode, String keyword, String orderBy) {
    return articleRepository.getArticles(searchKeywordTypeCode, keyword, orderBy);
  }
}
