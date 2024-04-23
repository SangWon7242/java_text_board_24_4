package com.sbs.exam.board;

import java.util.*;
import java.util.stream.IntStream;

public class Main {
  static int articleLastId = 0;

  static List<Article> articles = new ArrayList<>();

  static void makeTestData() {
    IntStream.rangeClosed(1, 100)
        .forEach(i -> articles.add(new Article(i, "제목" + i, "내용" + i)));
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    makeTestData();

    if(articles.size() > 0) {
      articleLastId = articles.get(articles.size() - 1).id;
    }

    System.out.println("== 텍스트 게시판 v 0.1 ==");
    System.out.println("프로그램 시작");

    while (true) {
      System.out.printf("명령) ");
      String cmd = sc.nextLine();

      Rq rq = new Rq(cmd);

      if (rq.getUrlPath().equals("/usr/article/write")) {
        actionUsrArticleWrite(sc);
      } else if (rq.getUrlPath().equals("/usr/article/list")) {
        actionUsrArticleList(rq);
      } else if (rq.getUrlPath().equals("/usr/article/detail")) {
        actionUsrArticleDetail(rq);
      } else if (rq.getUrlPath().equals("/usr/article/modify")) {
        actionUsrArticleModify(sc, rq);
      } else if (rq.getUrlPath().equals("/usr/article/delete")) {
        actionUsrArticleDelete(rq);
      } else if (cmd.equals("exit")) {
        System.out.println("== 게시판을 종료합니다 ==");
        break;
      }
    }

    sc.close();
  }

  private static void actionUsrArticleDelete(Rq rq) {
    Map<String, String> params = rq.getParams();

    if(params.containsKey("id") == false) {
      System.out.println("id를 입력해주세요.");
      return;
    }

    int id = 0;

    try {
      id = Integer.parseInt(params.get("id"));
    } catch (NumberFormatException e) {
      System.out.println("id를 정수 형태로 입력해주세요.");
      return;
    }

    if (articles.isEmpty()) {
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }

    Article article = findById(id, articles);

    if(article == null) {
      System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
      return;
    }

    articles.remove(article);

    System.out.printf("%d번 게시물이 삭제되었습니다.\n", id);
  }

  private static void actionUsrArticleModify(Scanner sc, Rq rq) {
    Map<String, String> params = rq.getParams();

    if(params.containsKey("id") == false) {
      System.out.println("id를 입력해주세요.");
      return;
    }

    int id = 0;

    try {
      id = Integer.parseInt(params.get("id"));
    } catch (NumberFormatException e) {
      System.out.println("id를 정수 형태로 입력해주세요.");
      return;
    }

    if (articles.isEmpty()) {
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }

    Article article = findById(id, articles);

    if(article == null) {
      System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
      return;
    }

    System.out.printf("새 제목 : ");
    article.title = sc.nextLine();

    System.out.printf("새 내용 : ");
    article.body = sc.nextLine();

    System.out.printf("%d번 게시물이 수정되었습니다.\n", article.id);
  }

  private static void actionUsrArticleWrite(Scanner sc) {
    System.out.println("== 게시물 작성 ==");

    System.out.printf("제목) ");
    String title = sc.nextLine();

    System.out.printf("내용) ");
    String body = sc.nextLine();

    int id = ++articleLastId;

    Article article = new Article(id, title, body);
    articles.add(article);

    System.out.printf("%d번 게시물이 생성되었습니다.\n", article.id);
  }

  private static void actionUsrArticleDetail(Rq rq) {
    Map<String, String> params = rq.getParams();

    if(params.containsKey("id") == false) {
      System.out.println("id를 입력해주세요.");
      return;
    }

    int id = 0;

    try {
      id = Integer.parseInt(params.get("id"));
    } catch (NumberFormatException e) {
      System.out.println("id를 정수 형태로 입력해주세요.");
      return;
    }

    if (articles.isEmpty()) {
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }

    Article article = findById(id, articles);

    if(article == null) {
      System.out.printf("%d번 게시물은 존재하지 않습니다.", id);
      return;
    }

    System.out.println("== 게시물 상세보기 ==");
    System.out.printf("번호 : %d\n", article.id);
    System.out.printf("제목 : %s\n", article.title);
    System.out.printf("내용 : %s\n", article.body);

  }



  private static void actionUsrArticleList(Rq rq) {
    Map<String, String> params = rq.getParams();

    // 검색 시작
    List<Article> filteredArticles = articles;

    if(params.containsKey("searchKeyword")) {
      String searchKeyword = params.get("searchKeyword");

      filteredArticles = new ArrayList<>();

      for(Article article : articles) {
        boolean matched = article.title.contains(searchKeyword) || article.body.contains(searchKeyword);

        if(matched) {
          filteredArticles.add(article);
        }
      }
    }
    // 검색 기능 끝

    List<Article> sortedArticles = filteredArticles;

    boolean orderByIdDesc = true; // 기존로직

    if(params.containsKey("orderBy") && params.get("orderBy").equals("idAsc")) {
      orderByIdDesc = false;
    }

    if(orderByIdDesc) {
      sortedArticles = Util.reverseList(sortedArticles);
    }


    System.out.println("== 게시물 리스트 ==");
    System.out.println("번호 | 제목");

    for(Article article : sortedArticles) {
      System.out.printf(" %d  | %s\n", article.id, article.title);
    }
  }

  private static Article findById(int id, List<Article> articles) {
    for(Article article : articles) {
      if(article.id == id) {
        return article;
      }
    }

    return null;
  }
}

class Article {
  int id;
  String title;
  String body;

  Article(int id, String title, String body) {
    this.id = id;
    this.title = title;
    this.body = body;
  }

  @Override // 어노테이션
  public String toString() {
    return String.format("{id : %d, title : \"%s\", body : \"%s\"}", id, title, body);
  }
}

class Rq {
  String url;
  Map<String, String> params;
  String urlPath;

  Rq(String url) {
    this.url = url;
    params = Util.getParamsFromUrl(this.url);
    urlPath = Util.getUrlPathFromUrl(this.url);
  }

  public Map<String, String> getParams() {
    return params;
  }

  public String getUrlPath() {
    return urlPath;
  }
}

class Util {
  static Map<String, String> getParamsFromUrl(String url) {
    Map<String, String> params = new HashMap<>();
    String[] urlBits = url.split("\\?", 2);

    if(urlBits.length == 1) {
      return params;
    }

    String queryString = urlBits[1];

    for(String bit : queryString.split("&")) {
      String[] bits = bit.split("=", 2);

      if(bits.length == 1) {
        continue;
      }

      params.put(bits[0], bits[1]); // key, value
    }

    return params;
  }

  static String getUrlPathFromUrl(String url) {
    return url.split("\\?", 2)[0];
  }

  // 이 함수는 원본리스트를 훼손하지 않고, 새 리스트를 만듭니다. 즉 정렬이 반대인 복사본리스트를 만들어서 반환합니다.
  public static<T> List<T> reverseList(List<T> list) {
    List<T> reverse = new ArrayList<>(list.size());

    for ( int i = list.size() - 1; i >= 0; i-- ) {
      reverse.add(list.get(i));
    }
    return reverse;
  }
}