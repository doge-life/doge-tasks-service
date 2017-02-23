package org.doge.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.StreamSupport;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TodoRepositoryTest {

    @Autowired
    private TodoRepository repository;

    @Test
    public void findAll() {
        Iterable<Todo> actual = repository.findAll();
        assertThat(StreamSupport.stream(actual.spliterator(), false).count(), is(2L));
    }
}
