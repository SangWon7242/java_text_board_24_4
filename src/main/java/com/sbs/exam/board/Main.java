package com.sbs.exam.board;

import java.util.*;
import java.util.stream.IntStream;

public class Main {
  static void makeTestData(List<Article> articles) {
    IntStream.rangeClosed(1, 100)
        .forEach(i -> articles.add(new Article(i, "제목" + i, "내용" + i)));
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int articleLastId = 0;

    List<Article> articles = new ArrayList<>();

    makeTestData(articles);

    if(articles.size() > 0) {
      articleLastId = articles.get(articles.size() - 1).id;
    }

    System.out.println("== 텍스트 게시판 v 0.1 ==");
    System.out.println("프로그램 시작");

    while (true) {
      System.out.printf("명령) ");
      String cmd = sc.nextLine();

      Rq rq = new Rq(cmd);
      Map<String, String> params = rq.getParams();

      if (rq.getUrlPath().equals("/usr/article/write")) {
        System.out.println("== 게시물 작성 ==");

        System.out.printf("제목) ");
        String title = sc.nextLine();

        System.out.printf("내용) ");
        String body = sc.nextLine();

        int id = ++articleLastId;

        Article article = new Article(id, title, body);

        System.out.println("생성된 게시물 객체 : " + article);

        System.out.printf("%d번 게시물이 생성되었습니다.\n", article.id);
      } else if (rq.getUrlPath().equals("/usr/article/list")) {
        System.out.println("== 게시물 리스트 ==");
        System.out.println("번호 | 제목");

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
          for(int i = sortedArticles.size() - 1; i >= 0; i--) {
            Article article = sortedArticles.get(i);
            System.out.printf(" %d  | %s\n", article.id, article.title);
          }
        }
        else {
          for(Article article : sortedArticles) {
            System.out.printf(" %d  | %s\n", article.id, article.title);
          }
        }


      } else if (rq.getUrlPath().equals("/usr/article/detail")) {

        if(params.containsKey("id") == false) {
          System.out.println("id를 입력해주세요.");
          continue;
        }

        int id = 0;

        try {
          id = Integer.parseInt(params.get("id"));
        } catch (NumberFormatException e) {
          System.out.println("id를 정수 형태로 입력해주세요.");
          continue;
        }

        if (articles.isEmpty()) {
          System.out.println("게시물이 존재하지 않습니다.");
          continue;
        }

        if(id > articles.size()) {
          System.out.println("게시물이 존재하지 않습니다.");
          continue;
        }

        Article article = articles.get(id - 1);

        System.out.println("== 게시물 상세보기 ==");
        System.out.printf("번호 : %d\n", article.id);
        System.out.printf("제목 : %s\n", article.title);
        System.out.printf("내용 : %s\n", article.body);


      } else if (cmd.equals("exit")) {
        System.out.println("== 게시판을 종료합니다 ==");
        break;
      }
    }

    sc.close();
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
}