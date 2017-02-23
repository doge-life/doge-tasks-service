package org.doge.services;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Todo {

    @Id
    private final long id;
    private final String name;
    private final boolean completed;
}
