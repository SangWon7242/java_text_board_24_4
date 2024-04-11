package com.sbs.exam.board;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("== 텍스트 게시판 v 0.1 ==");
    System.out.println("프로그램 시작");

    while (true) {
      System.out.printf("명령) ");
      String cmd = sc.nextLine();

      if(cmd.equals("exit")) {
        System.out.println("== 게시판을 종료합니다 ==");
        break;
      }

      System.out.printf("입력받은 명령어 : %s\n", cmd);

      sc.close();
    }
  }
}