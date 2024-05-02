package com.sbs.exam.board.controller;

import com.sbs.exam.board.Rq;
import com.sbs.exam.board.container.Container;
import com.sbs.exam.board.dto.Article;
import com.sbs.exam.board.dto.Member;
import com.sbs.exam.board.service.ArticleService;

import java.util.List;

public class ArticleController {
  private ArticleService articleService;

  public ArticleController() {
    articleService = Container.getArticleService();

    articleService.makeTestData();
  }

  public void actionWrite(Rq rq) {
    System.out.println("== 게시물 작성 ==");

    System.out.printf("제목) ");
    String title = Container.getSc().nextLine();

    System.out.printf("내용) ");
    String body = Container.getSc().nextLine();

    Member member = rq.getLoginedMember();

    int id = articleService.write(title, body, member.getName(), member.getId());

    System.out.printf("%d번 게시물이 생성되었습니다.\n", id);
  }

  public void showList(Rq rq) {
    String searchKeyword = rq.getParam("searchKeyword", "");
    String orderBy = rq.getParam("orderBy", "idDesc()");

    // 정렬
    List<Article> articles = articleService.findByArticles(searchKeyword, orderBy);

    System.out.println("== 게시물 리스트 ==");
    System.out.println("번호 | 제목 | 작성자");

    for (Article article : articles) {
      System.out.printf(" %d  | %s |  %s\n", article.getId(), article.getTitle(), article.getWriter__name());
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
    System.out.printf("작성자 : %s\n", article.getWriter__name());
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

    Member member = rq.getLoginedMember();

    if(article.getMemberId() != member.getId()) {
      System.out.println("게시물 수정 권한이 없습니다.");
      return;
    }

    System.out.printf("새 제목 : ");
    String title = Container.getSc().nextLine();

    System.out.printf("새 내용 : ");
    String body = Container.getSc().nextLine();

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

    Member member = rq.getLoginedMember();

    if(article.getMemberId() != member.getId()) {
      System.out.println("게시물 수정 권한이 없습니다.");
      return;
    }

    articleService.remove(article);

    System.out.printf("%d번 게시물이 삭제되었습니다.\n", id);
  }
}
