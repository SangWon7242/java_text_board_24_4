package com.sbs.exam.board.repository;

import com.sbs.exam.board.dto.Member;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class MemberRepository {
  private int lastId;

  @Getter
  private List<Member> members;
  public MemberRepository() {
    lastId = 0;
    members = new ArrayList<>();

    if (members.size() > 0) {
      lastId = members.get(members.size() - 1).getId();
    }
  }

  public Member join(String username, String password, String name) {
    int id = ++lastId;
    Member member = new Member(id, username, password, name);

    members.add(member);

    return member;
  }

  public Member findByUsername(String username) {
    for(Member member : members) {
      if(member.getUsername().equals(username)) {
        return member;
      }
    }

    return null;
  }

  public Member findById(int id) {
    for(Member member : members) {
      if(member.getId() == id) {
        return member;
      }
    }

    return null;
  }
}
