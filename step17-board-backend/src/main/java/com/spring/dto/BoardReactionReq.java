package com.spring.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardReactionReq {
  private int id;
  private int bno;
  private String type;
  private int mno;

}
