package ru.netology.data;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Card {
  private String CardNumber;
  private String Month;
  private String Year;
  private String CardHolder;
  private String CVC;
}