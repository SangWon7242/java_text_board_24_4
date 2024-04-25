package com.sbs.exam.board.controller;

import com.sbs.exam.board.Member;
import com.sbs.exam.board.container.Container;

import java.util.ArrayList;
import java.util.List;

public class MemberController {
  private int memberLastId;

  private List<Member> members;

  public MemberController() {
    memberLastId = 0;
    members = new ArrayList<>();
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

      if(username.trim().isEmpty()) {
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

      if(password.trim().isEmpty()) {
        System.out.println("password(을)를 입력해주세요.");
        continue;
      }

      while (true) {
        System.out.printf("비밀번호 확인 : ");
        passwordConfirm = Container.sc.nextLine();

        if(passwordConfirm.trim().isEmpty()) {
          System.out.println("passwordConfirm(을)를 입력해주세요.");
          continue;
        }

        if(!passwordConfirm.equals(password)) {
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

      if(name.trim().isEmpty()) {
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
}
