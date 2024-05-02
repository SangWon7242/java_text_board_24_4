package com.sbs.exam.board.service;

import com.sbs.exam.board.container.Container;
import com.sbs.exam.board.dto.Member;
import com.sbs.exam.board.repository.MemberRepository;

public class MemberService {
  private MemberRepository memberRepository;

  public MemberService() {
    memberRepository = Container.getMemberRepository();

    makeTestData();
  }

  public void makeTestData() {
    for(int i = 1; i <= 3; i++) {
      String username = "user" + i;
      String password = "user" + i;
      String name = "회원" + i;
      join(username, password, name);
    }
  }

  public Member join(String username, String password, String name) {
    return memberRepository.join(username, password, name);
  }

  public Member findByUsername(String username) {
    return memberRepository.findByUsername(username);
  }
}
