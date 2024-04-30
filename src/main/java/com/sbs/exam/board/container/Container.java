package com.sbs.exam.board.container;

import com.sbs.exam.board.controller.ArticleController;
import com.sbs.exam.board.controller.MemberController;
import com.sbs.exam.board.repository.ArticleRepository;
import com.sbs.exam.board.repository.MemberRepository;
import com.sbs.exam.board.service.ArticleService;
import com.sbs.exam.board.service.MemberService;
import com.sbs.exam.board.session.Session;
import lombok.Getter;

import java.util.Scanner;

public class Container {

  @Getter
  public static Scanner sc;

  @Getter
  public static Session session;

  @Getter
  public static ArticleRepository articleRepository;

  @Getter
  public static MemberRepository memberRepository;

  @Getter
  public static ArticleService articleService;

  @Getter
  public static MemberService memberService;

  @Getter
  public static ArticleController articleController;

  @Getter
  public static MemberController memberController;

  static {
    sc = new Scanner(System.in);
    session = new Session();

    articleRepository = new ArticleRepository();
    memberRepository = new MemberRepository();

    articleService = new ArticleService();
    memberService = new MemberService();

    articleController = new ArticleController();
    memberController = new MemberController();
  }
}
