package com.example.demo.api.controller;

import com.example.demo.api.dto.PersonDto;
import com.example.demo.db.model.Person;
import com.example.demo.service.PersonService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PersonController {

    private final PersonService personService;
    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    /**
     * Gets person based on id
     *
     * @param   id id from url path
     * @return  Person with such id
     */
    @ApiOperation(
            value = "Gets person by id if exists",
            notes = "Pass id to get current person",
            response = PersonDto.class
    )
    @GetMapping(path = "/people/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonDto getPerson(
            @ApiParam(value = "id of requested person", required = true)
            @PathVariable("id") int id){
        return personService.getPerson(id);
    }


    /**
     * Gets a person list by his first name
     *
     * @param name      first name of person we want
     * @return          list of people with similar first name
     */
    @ApiOperation(
            value = "Gets list of people from database by given substring in their name",
            notes = "Pass substring you want to search in the name",
            response = PersonDto.class
    )
    @GetMapping(path = "/people", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PersonDto> getPeopleByNameSearch(
            @ApiParam(value = "substring you want to search in a name")
            @RequestParam(name = "search") String name){
        return personService.getPeopleByNameSearch(name);
    }


    /**
     * Gets a person list by company
     *
     * @param company   Company name
     * @return          List of people working in current company
     */
    @ApiOperation(
            value = "Gets list of people who work in same company",
            notes = "Pass company name",
            response = PersonDto.class
    )
    @GetMapping(path = "/people/{company}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PersonDto> getPeopleByCompany(
            @ApiParam(value = "Name of the company", required = true)
            @PathVariable("company") String company){
        return personService.getPeopleByCompany(company);
    }

    /**
     * Inserts new person to database if does not exists
     *
     * @param person    Person object value from JSON
     */
    @ApiOperation(
            value = "Insert not existing person into database",
            notes = "Pass JSON person body"
    )
    @PostMapping(path = "/people", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insertPerson(
            @ApiParam(value = "person to be inserted", required = true)
            @RequestBody Person person){
        personService.insertPerson(person);
    }


    /**
     * Updates given person if exists
     *
     * @param person    Person object value from JSON
     */
    @ApiOperation(
            value = "Update person",
            notes = "Pass updated person body"
    )
    @PutMapping(
            path = "people/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updatePerson(
            @ApiParam(value = "Updated person", required = true)
            @RequestBody Person person){
        personService.updatePerson(person);
    }


    /**
     * Deletes person based on id (if exists)
     *
     * @param id    id value from url
     */
    @ApiOperation(
            value = "Delete person with given id from database",
            notes = "Pass id of person you want to be deleted"
    )
    @DeleteMapping(path = "/people/{id}")
    public void deletePersonWithSuchId(
            @ApiParam(value = "id of person we want to be deleted", required = true)
            @PathVariable("id") int id){
        personService.deletePersonWithSuchId(id);
    }


    /**
     * Deletes the given person
     *
     * @param person    person that is going to be deleted
     */
    @ApiOperation(
            value = "Delete given person from database",
            notes = "Pass body of person you want to be deleted"
    )
    @DeleteMapping(path = "/people")
    public void deletePerson(
            @ApiParam(value = "deletes given person")
            @RequestBody Person person){
        personService.deletePerson(person);
    }
}
