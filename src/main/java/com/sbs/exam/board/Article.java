package com.sbs.exam.board;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
  private int id;
  private String title;
  private String body;
}