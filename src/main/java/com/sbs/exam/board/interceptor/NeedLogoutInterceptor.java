package com.sbs.exam.board.interceptor;

import com.sbs.exam.board.Rq;

public class NeedLogoutInterceptor implements Interceptor {
  @Override
  public boolean run(Rq rq) {
    if(rq.isLogout()) {
      return true;
    }

    return switch (rq.getUrlPath()) {
      case "/usr/member/login",
          "/usr/member/join",
          "/usr/member/findByLoginId",
          "/usr/member/findByLoginPw" -> {
        System.out.println("로그아웃 후 이용해주세요.");
        yield false;
      }
      default -> true;
    };

  }
}
