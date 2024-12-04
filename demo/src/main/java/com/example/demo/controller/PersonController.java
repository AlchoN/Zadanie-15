package com.example.demo.controller;

import com.example.demo.dto.Person;
import com.example.demo.repository.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/person")
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @GetMapping("/person/{id}")
    public Optional<Person> getPersonById(@PathVariable int id) {
        return personRepository.findById(id);
    }

    @PostMapping("/person")
    public Person createPerson(@RequestBody Person person) {
        return personRepository.save(person);
    }

    @PutMapping("/person/{id}")
    @Transactional
    public Person updatePerson(@PathVariable int id, @RequestBody Person updatedPerson) {
        Optional<Person> existingPerson = personRepository.findById(id);
        if (existingPerson.isPresent()) {
            Person person = existingPerson.get();
            person.setFirstname(updatedPerson.getFirstname());
            person.setSurname(updatedPerson.getSurname());
            person.setLastname(updatedPerson.getLastname());
            person.setBirthday(updatedPerson.getBirthday());
            return personRepository.save(person); 
        }
        return null; 
    }

    @DeleteMapping("/person/{id}")
    public void deletePerson(@PathVariable int id) {
        personRepository.deleteById(id);
    }
}
