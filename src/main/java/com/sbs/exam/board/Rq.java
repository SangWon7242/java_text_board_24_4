package com.sbs.exam.board;

import com.sbs.exam.board.container.Container;
import com.sbs.exam.board.dto.Member;
import com.sbs.exam.board.session.Session;
import lombok.Getter;

import java.util.Map;

public class Rq {
  public String url;
  public String loginedMember;

  @Getter
  public Map<String, String> params;

  @Getter
  public String urlPath;

  public Rq() {
    loginedMember = "loginedMember";
  }

  public void setCommand(String url) {
    params = Util.getParamsFromUrl(url);
    urlPath = Util.getUrlPathFromUrl(url);
  }

  public int getIntParam(String paramName, int defaultValue) {
    if (params.containsKey(paramName) == false) {
      return defaultValue;
    }

    try {
      return Integer.parseInt(params.get(paramName));
    } catch (NumberFormatException e) {
      return defaultValue;
    }

  }

  public String getParam(String paramName, String defaultValue) {
    if(params.containsKey(paramName) == false) {
      return defaultValue;
    }

    return params.get(paramName);
  }

  public void setSessionAttr(String key, Object value) {
    Session session = Container.getSession();

    session.setAttribute(key, value);
  }

  public void removeSessionAttr(String key) {
    Session session = Container.getSession();

    session.removeAttribute(key);
  }


  // 로그인 된 경우 : true
  // 로그인이 안 된 경우 : false
  public boolean isLogined() {
    Session session = Container.getSession();

    return session.hasAttribute(loginedMember);
  }

  public boolean isLogout() {
    return !isLogined();
  }

  public void login(Member member) {
    setSessionAttr(loginedMember, member);
  }

  public void logout() {
    removeSessionAttr(loginedMember);
  }

  public Member getLoginedMember() {
    Session session = Container.getSession();

    return (Member) session.getAttribute(loginedMember);
  }

}
