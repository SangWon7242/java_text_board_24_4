package com.sbs.exam.board.interceptor;

import com.sbs.exam.board.Rq;

public interface Interceptor {
  boolean run(Rq rq);
}
