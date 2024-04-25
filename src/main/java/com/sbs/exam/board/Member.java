package com.sbs.exam.board;

public class Member {
  private int id;
  private String username;
  private String password;
  private String name;

  public int getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String getName() {
    return name;
  }

  public Member(int id, String username, String password, String name) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.name = name;
  }

  @Override
  public String toString() {
    return "Member{" +
        "id=" + id +
        ", username='" + username + '\'' +
        ", password='" + password + '\'' +
        ", name='" + name + '\'' +
        '}';
  }
}
