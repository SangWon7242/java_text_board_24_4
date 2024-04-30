package com.sbs.exam.board.container;

import com.sbs.exam.board.controller.ArticleController;
import com.sbs.exam.board.controller.MemberController;
import com.sbs.exam.board.session.Session;

import java.util.Scanner;

public class Container {
  public static Scanner sc;
  public static Session session;
  public static ArticleController articleController;
  public static MemberController memberController;

  static {
    sc = new Scanner(System.in);
    session = new Session();

    articleController = new ArticleController();
    memberController = new MemberController();
  }

  public static Session getSession() {
    return session;
  }
}
