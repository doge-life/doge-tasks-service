package org.doge.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TodoDomain {
   private long id;
   private String name;
   private boolean isCompleted;
}