package br.com.cams.model.fireandforget;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Event {

  private Long id;
  private Type type;
  private Instant timestamp;

  public enum Type {
    SUCCESS, FAILURE, ERROR
  }
}
