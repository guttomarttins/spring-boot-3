package br.com.cams.model.bidirectional;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoanDetails {

  private double amount;
  private float rate;
  private int years;

}
