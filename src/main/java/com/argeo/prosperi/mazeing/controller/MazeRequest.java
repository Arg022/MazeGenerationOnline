package com.argeo.prosperi.mazeing.controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MazeRequest {
  private int width;
  private int height;
  private Long seed;
}