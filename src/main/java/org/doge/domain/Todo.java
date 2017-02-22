package org.doge.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Todo {
   private int id;
   private String name;
   private boolean isFinished;
}
