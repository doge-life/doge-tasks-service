package org.doge.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoDomain {
    private long id;
    private String name;
    private boolean isCompleted;
}
