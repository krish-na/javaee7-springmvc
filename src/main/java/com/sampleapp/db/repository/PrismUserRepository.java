package com.sampleapp.db.repository;

import com.sampleapp.db.domain.Person;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Interface for PrimUser persistence purposes.
 */
@Repository
public interface PrismUserRepository {

    public void create(Person prismUer);

    public Person get(UUID email);

    public Person get(String email);

    public void delete(Person person);

    public void update(Person person);

}
