package com.sampleapp.service;

import com.sampleapp.db.domain.Person;
import com.sampleapp.db.repository.PrismUserRepository;
import com.sampleapp.mvc.exception.RecordNotFoundException;
import com.sampleapp.mvc.model.PrismUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Service facade to handle Prim User's use cases.
 */
@Service
public class PrismUserService {

    @Autowired
    private PrismUserRepository prismUserRepository;

    public void createPrismUser(String firstName, String lastName, String email) {
        prismUserRepository.create(new Person(firstName,lastName,email));
    }

    public PrismUser getPrismUser(UUID uuid) throws RecordNotFoundException {
        Person person = prismUserRepository.get(uuid);
        if(person == null) {
            throw new RecordNotFoundException("Person not found with UUID: " + uuid);
        }
        return convertPersontoPrismUser(person);
    }

    // We convert a domain/entity object (Person) to a model object (PrismUser). This way,
    // we don't return unnecessary domain/entity data to the client, for example row id
    public PrismUser convertPersontoPrismUser(Person person) {
        if(person == null) return null;
        PrismUser prismUser = new PrismUser();
        prismUser.setFirstName(person.getFirstName());
        prismUser.setLastName(person.getFirstName());
        return prismUser;
    }
}
