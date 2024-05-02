package com.sbs.exam.board.container;

import com.sbs.exam.board.controller.ArticleController;
import com.sbs.exam.board.controller.MemberController;
import com.sbs.exam.board.interceptor.NeedLoginInterceptor;
import com.sbs.exam.board.interceptor.NeedLogoutInterceptor;
import com.sbs.exam.board.repository.ArticleRepository;
import com.sbs.exam.board.repository.MemberRepository;
import com.sbs.exam.board.service.ArticleService;
import com.sbs.exam.board.service.MemberService;
import com.sbs.exam.board.session.Session;
import lombok.Getter;

import java.util.Scanner;


public class Container {

  @Getter
  private static Scanner sc;
  @Getter
  private static Session session;

  @Getter
  private static NeedLoginInterceptor needLoginInterceptor;
  @Getter
  private static NeedLogoutInterceptor needLogoutInterceptor;

  @Getter
  private static ArticleRepository articleRepository;
  @Getter
  private static MemberRepository memberRepository;

  @Getter
  private static ArticleService articleService;
  @Getter
  private static MemberService memberService;

  @Getter
  private static ArticleController articleController;
  @Getter
  private static MemberController memberController;

  static {
    sc = new Scanner(System.in);
    session = new Session();

    needLoginInterceptor = new NeedLoginInterceptor();
    needLogoutInterceptor = new NeedLogoutInterceptor();

    articleRepository = new ArticleRepository();
    memberRepository = new MemberRepository();

    articleService = new ArticleService();
    memberService = new MemberService();

    articleController = new ArticleController();
    memberController = new MemberController();
  }
}
