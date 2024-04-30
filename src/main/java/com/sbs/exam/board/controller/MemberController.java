package com.sbs.exam.board.controller;

import com.sbs.exam.board.Member;
import com.sbs.exam.board.Rq;
import com.sbs.exam.board.container.Container;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class MemberController {
  private int memberLastId;

  private List<Member> members;

  public MemberController() {
    memberLastId = 0;
    members = new ArrayList<>();

    makeTestData();
  }

  void makeTestData() {
    IntStream.rangeClosed(1, 3)
        .forEach(i -> members.add(new Member(i, "user" + i, "user" + i, "이름" + i)));
  }

  public void actionJoin() {
    String username;
    String password;
    String passwordConfirm;
    String name;

    // 아이디 입력 시작
    while (true) {
      System.out.printf("아이디 : ");
      username = Container.sc.nextLine();

      if (username.trim().isEmpty()) {
        System.out.println("username을 입력해주세요.");
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

    int id = ++memberLastId;

    Member member = new Member(id, username, password, name);
    members.add(member);

    System.out.printf("\"%s\"님 회원가입 되었습니다.\n", member.getUsername());
  }

  public void actionLogin(Rq rq) {
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

      member = findByUsername(username);

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

    rq.setSessionAttr("loginedMember", member);

    System.out.printf("\"%s\"님 로그인 되었습니다.\n", member.getUsername());
  }

  private Member findByUsername(String username) {
    for(Member member : members) {
      if(member.getUsername().equals(username)) {
        return member;
      }
    }

    return null;
  }
}
