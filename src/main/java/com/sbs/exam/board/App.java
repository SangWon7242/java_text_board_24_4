package com.sbs.exam.board;

import com.sbs.exam.board.container.Container;
import com.sbs.exam.board.dto.Member;
import com.sbs.exam.board.interceptor.Interceptor;

import java.util.ArrayList;
import java.util.List;

public class App {
  public void run() {
    System.out.println("== 텍스트 게시판 v 0.1 ==");
    System.out.println("프로그램 시작");

    // 프로그램이 실행되자마 회원 1번이 로그인 될 수 있도록.
    forTestLoginByMemberId(1);

    while (true) {
      Rq rq = new Rq();

      String promptName = "명령";

      if(rq.isLogined()) {
        Member loginedMember = rq.getLoginedMember();
        promptName = loginedMember.getUsername();
      }

      System.out.printf("%s) ", promptName);
      String cmd = Container.getSc().nextLine();

      rq.setCommand(cmd);

      if(runInterceptor(rq) == false) {
        continue;
      }

      if (rq.getUrlPath().equals("/usr/article/write")) {
        Container.getArticleController().actionWrite(rq);
      } else if (rq.getUrlPath().equals("/usr/article/list")) {
        Container.getArticleController().showList(rq);
      } else if (rq.getUrlPath().equals("/usr/article/detail")) {
        Container.getArticleController().showDetail(rq);
      } else if (rq.getUrlPath().equals("/usr/article/modify")) {
        Container.getArticleController().actionModify(rq);
      } else if (rq.getUrlPath().equals("/usr/article/delete")) {
        Container.getArticleController().actionDelete(rq);
      } else if (rq.getUrlPath().equals("/usr/member/join")) {
        Container.getMemberController().actionJoin();
      } else if (rq.getUrlPath().equals("/usr/member/login")) {
        Container.getMemberController().actionLogin(rq);
      } else if (rq.getUrlPath().equals("/usr/member/logout")) {
        Container.getMemberController().actionLogout(rq);
      } else if (cmd.equals("exit")) {
        System.out.println("== 게시판을 종료합니다 ==");
        break;
      }
    }

    Container.getSc().close();
  }

  private void forTestLoginByMemberId(int id) {
    Member member = Container.getMemberService().findById(id);

    new Rq().login(member);
  }

  private boolean runInterceptor(Rq rq) {
    List<Interceptor> interceptors = new ArrayList<>();

    interceptors.add(Container.getNeedLoginInterceptor());
    interceptors.add(Container.getNeedLogoutInterceptor());

    for(Interceptor interceptor : interceptors) {
      // interceptor.run(rq) : 로그인, 로그아웃이 되어 있다면 true, 아니면 false
      if(interceptor.run(rq) == false) {
        return false;
      }
    }

    return true;
  }
}
