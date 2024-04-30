package com.sbs.exam.board;

import com.sbs.exam.board.container.Container;
import com.sbs.exam.board.session.Session;

import java.util.Map;

public class Rq {
  public String url;
  public Map<String, String> params;
  public String urlPath;

  Rq(String url) {
    this.url = url;
    params = Util.getParamsFromUrl(this.url);
    urlPath = Util.getUrlPathFromUrl(this.url);
  }

  public Map<String, String> getParams() {
    return params;
  }

  public String getUrlPath() {
    return urlPath;
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
  public boolean isLogined(String loginedValue) {
    Session session = Container.getSession();

    return session.hasAttribute(loginedValue);
  }

  public boolean isLogout(String loginedValue) {
    return !isLogined(loginedValue);
  }

}
