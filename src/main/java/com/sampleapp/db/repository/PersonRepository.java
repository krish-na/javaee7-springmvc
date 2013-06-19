package com.sampleapp.db.repository;

import com.sampleapp.db.domain.Person;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

/**
 * Services for PrimUser persistence purposes.
 */
public interface PersonRepository extends PagingAndSortingRepository<Person, Long>  {

    Person findByUsername(@Param("username") String username);

    Person findByUuid(@Param("uuid") UUID uuid);

    List<Person> findPrismUsersByFirstNameOrLastName(@Param("firstName") String firstName,
                                                     @Param("lastName") String lastName);
}
