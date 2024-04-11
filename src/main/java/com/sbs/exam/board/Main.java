package com.sbs.exam.board;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int articleLastId = 0;

    System.out.println("== 텍스트 게시판 v 0.1 ==");
    System.out.println("프로그램 시작");

    while (true) {
      System.out.printf("명령) ");
      String cmd = sc.nextLine();

      if(cmd.equals("/usr/article/write")) {
        System.out.println("== 게시물 작성 ==");

        System.out.printf("제목) ");
        String title = sc.nextLine();

        System.out.printf("내용) ");
        String body = sc.nextLine();

        int id = ++articleLastId;
        // int id = articleLastId + 1;
        System.out.printf("%d번 게시물이 생성되었습니다.\n", id);
        // articleLastId++;
      }
      else if(cmd.equals("exit")) {
        System.out.println("== 게시판을 종료합니다 ==");
        break;
      }
    }

    sc.close();
  }
}