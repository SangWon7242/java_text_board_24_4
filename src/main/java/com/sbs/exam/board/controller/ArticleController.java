package com.sbs.exam.board.controller;

import com.sbs.exam.board.Rq;
import com.sbs.exam.board.container.Container;
import com.sbs.exam.board.dto.Article;
import com.sbs.exam.board.service.ArticleService;

import java.util.List;

public class ArticleController {
  private ArticleService articleService;
  private List<Article> articles;

  public ArticleController() {
    articleService = Container.getArticleService();
    articleService.makeTestData();
    articles = articleService.getArticles();
  }

  public void actionWrite() {
    System.out.println("== 게시물 작성 ==");

    System.out.printf("제목) ");
    String title = Container.sc.nextLine();

    System.out.printf("내용) ");
    String body = Container.sc.nextLine();

    int id = articleService.write(title, body);

    System.out.printf("%d번 게시물이 생성되었습니다.\n", id);
  }

  public void showList(Rq rq) {
    String searchKeyword = rq.getParam("searchKeyword", "");
    String orderBy = rq.getParam("orderBy", "idDesc");

    // 정렬
    List<Article> articles = articleService.findByArticles(searchKeyword, orderBy);

    System.out.println("== 게시물 리스트 ==");
    System.out.println("번호 | 제목");

    for (Article article : articles) {
      System.out.printf(" %d  | %s\n", article.getId(), article.getTitle());
    }
  }

  public void showDetail(Rq rq) {
    int id = rq.getIntParam("id", 0);

    if(id == 0) {
      System.out.println("id를 올바르게 입력해주세요.");
      return;
    }

    Article article = articleService.findById(id);

    if (article == null) {
      System.out.printf("%d번 게시물은 존재하지 않습니다.", id);
      return;
    }

    System.out.println("== 게시물 상세보기 ==");
    System.out.printf("번호 : %d\n", article.getId());
    System.out.printf("제목 : %s\n", article.getTitle());
    System.out.printf("내용 : %s\n", article.getBody());
  }

  public void actionModify(Rq rq) {
    int id = rq.getIntParam("id", 0);

    if(id == 0) {
      System.out.println("id를 올바르게 입력해주세요.");
      return;
    }

    Article article = articleService.findById(id);

    if (article == null) {
      System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
      return;
    }

    System.out.printf("새 제목 : ");
    String title = Container.sc.nextLine();

    System.out.printf("새 내용 : ");
    String body = Container.sc.nextLine();

    articleService.modify(id, title, body);

    System.out.printf("%d번 게시물이 수정되었습니다.\n", article.getId());
  }

  public void actionDelete(Rq rq) {
    int id = rq.getIntParam("id", 0);

    if(id == 0) {
      System.out.println("id를 올바르게 입력해주세요.");
      return;
    }

    Article article = articleService.findById(id);

    if (article == null) {
      System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
      return;
    }

    articleService.remove(article);

    System.out.printf("%d번 게시물이 삭제되었습니다.\n", id);
  }
}
