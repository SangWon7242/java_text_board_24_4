package com.sbs.exam.board.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
  private int id;
  private String title;
  private String body;
}