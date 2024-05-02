package com.sbs.exam.board.controller;

import com.sbs.exam.board.Rq;
import com.sbs.exam.board.container.Container;
import com.sbs.exam.board.dto.Member;
import com.sbs.exam.board.service.MemberService;

public class MemberController {
  private MemberService memberService;

  public MemberController() {
    memberService = Container.getMemberService();
  }

  public void actionJoin() {
    String username;
    String password;
    String passwordConfirm;
    String name;

    Member member;

    // 아이디 입력 시작
    while (true) {
      System.out.printf("아이디 : ");
      username = Container.sc.nextLine();

      if (username.trim().isEmpty()) {
        System.out.println("username을 입력해주세요.");
        continue;
      }

      member = memberService.findByUsername(username);

      if(member != null) {
        System.out.println("이미 가입된 회원입니다.");
        continue;
      }

      break;
    }
    // 아이디 입력 끝

    // 비밀번호 입력 시작
    while (true) {
      System.out.printf("비밀번호 : ");
      password = Container.sc.nextLine();

      if (password.trim().isEmpty()) {
        System.out.println("password(을)를 입력해주세요.");
        continue;
      }

      while (true) {
        System.out.printf("비밀번호 확인 : ");
        passwordConfirm = Container.sc.nextLine();

        if (passwordConfirm.trim().isEmpty()) {
          System.out.println("passwordConfirm(을)를 입력해주세요.");
          continue;
        }

        if (!passwordConfirm.equals(password)) {
          System.out.println("비밀번호가 일치하지 않습니다.");
          continue;
        }

        break;
      }

      break;
    }
    // 비밀번호 입력 시작

    // 이름 입력 시작
    while (true) {
      System.out.printf("이름 : ");
      name = Container.sc.nextLine();

      if (name.trim().isEmpty()) {
        System.out.println("name을 입력해주세요.");
        continue;
      }

      break;
    }
    // 이름 입력 끝

    member = memberService.join(username, password, name);

    System.out.printf("\"%s\"님 회원 가입이 되었습니다.\n", member.getUsername());
  }

  public void actionLogin(Rq rq) {

    if(rq.isLogined()) {
      System.out.println("이미 로그인 되어 있습니다.");
      System.out.println("로그아웃 후 이용해주세요.");
      return;
    }

    String username;
    String password;
    Member member;

    // 아이디 입력 시작
    while (true) {
      System.out.printf("아이디 : ");
      username = Container.sc.nextLine();

      if (username.trim().isEmpty()) {
        System.out.println("username을 입력해주세요.");
        continue;
      }

      member = memberService.findByUsername(username);

      if(member == null) {
        System.out.println("존재하지 않는 아이디입니다.");
        continue;
      }

      break;
    }
    // 아이디 입력 끝

    int tryCount = 0;
    int tryMaxCount = 3;

    // 비밀번호 입력 시작
    while (true) {
      if(tryCount >= tryMaxCount) {
        System.out.println("비밀번호를 확인 후 다시 입력해주세요.");
        return;
      }

      System.out.printf("비밀번호 : ");
      password = Container.sc.nextLine();

      if (password.trim().isEmpty()) {
        System.out.println("password(을)를 입력해주세요.");
        continue;
      }

      if(!member.getPassword().equals(password)) {
        System.out.println("로그인 비밀번호가 일치하지 않습니다.");

        tryCount++;
        System.out.printf("비밀번호를 %d번 틀리셨습니다.(%d/%d)\n", tryCount, tryCount, tryMaxCount);

        continue;
      }

      break;
    }
    // 비밀번호 입력 끝

    rq.login(member);

    System.out.printf("\"%s\"님 로그인 되었습니다.\n", member.getUsername());
  }

  public void actionLogout(Rq rq) {
    if(rq.isLogout()) {
      System.out.println("이미 로그아웃 상태입니다.");
      return;
    }

    rq.logout();
    System.out.println("로그아웃 되었습니다.");
  }
}
