package com.sampleapp.db.repository;

import com.sampleapp.db.domain.Person;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

/**
 * Services for PrimUser persistence purposes.
 */
public interface PersonRepository extends PagingAndSortingRepository<Person, Long>  {

    public Person findByUuid(@Param("uuid") UUID uuid);
}
