package com.example.demo.api;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/person")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity<String> addPerson(@RequestBody Person person){
        personService.addPerson(person);
        return new ResponseEntity<>("Record added successfully!!",
                HttpStatus.OK);
    }

    @GetMapping
    public List<Person> getAllPeoples(){
        return personService.getAllPeople();
    }

    @GetMapping(path = "{id}")
    public Person getPersonById(@PathVariable("id") UUID id) {
        return personService.getPersonById(id).orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<String> deletePersonById(@PathVariable("id") UUID id){
         personService.deletePersonById(id);

        return new ResponseEntity<>("Record deleted successfully!!",
                HttpStatus.OK);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<String> updatePersonById(@PathVariable("id") UUID id, @RequestBody Person personToUpdate){
        personService.updatePersonById(id, personToUpdate);
        return new ResponseEntity<>("Record updated successfully!!",HttpStatus.OK);
    }
}
