package com.sampleapp.db.repository;

import com.sampleapp.db.domain.Person;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * PrismUser persistence impl. Persistence store dependent. This is just a sample implementation.
 * We could possibly use Hibernate for SQL/NoSQL reference implementations.
 *
 * http://docs.jboss.org/hibernate/orm/4.2/quickstart/en-US/html_single/
 */
@Repository
public class PrismUserRepositoryImpl implements PrismUserRepository {

    @Override
    public void create(Person prismUer) {
    }

    @Override
    public Person get(UUID email) {
        return null;
    }

    @Override
    public Person get(String email) {
        return null;
    }

    @Override
    public void delete(Person person) {
    }

    @Override
    public void update(Person person) {
    }
}
