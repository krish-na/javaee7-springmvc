package com.sampleapp.service;

import com.sampleapp.db.domain.Person;
import com.sampleapp.db.repository.PersonRepository;
import com.sampleapp.mvc.exception.RecordNotFoundException;
import com.sampleapp.mvc.model.PrismUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * Service facade to handle Prim User's use cases.
 */
@Service
@Transactional
public class PrismUserService {

    @Autowired
    private PersonRepository personRepository;

    public PrismUser getPrismUser(UUID uuid) throws RecordNotFoundException {
        Person person = getPersonByUuid(uuid);
        return convertPersontoPrismUser(person);
    }

    public void createPrismUser(String firstName, String lastName, String email) {
        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setEmail(email);
        person.setUuid(UUID.randomUUID());
        personRepository.save(person);
    }

    public void deletePrismUser(UUID uuid) throws RecordNotFoundException{
        Person person = getPersonByUuid(uuid);
        if(person != null) {
            personRepository.delete(person);
        }
    }

    public Person getPersonByUuid(UUID uuid) throws RecordNotFoundException {
        Person person = personRepository.findByUuid(uuid);
        if(person == null) {
            throw new RecordNotFoundException("Person not found with UUID: " + uuid);
        }
        return person;
    }

    // We convert a domain/entity object (Person) to a model object (PrismUser). This way,
    // we don't return unnecessary domain/entity data to the client, ex. id or uuid
    public PrismUser convertPersontoPrismUser(Person person) {
        if(person == null) return null;
        PrismUser prismUser = new PrismUser();
        prismUser.setFirstName(person.getFirstName());
        prismUser.setLastName(person.getLastName());
        prismUser.setEmail(person.getEmail());
        return prismUser;
    }
}
