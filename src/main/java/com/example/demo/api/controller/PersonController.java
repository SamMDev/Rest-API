package com.example.demo.api.controller;

import com.example.demo.api.dto.PersonDto;
import com.example.demo.db.model.Person;
import com.example.demo.service.PersonService;
import io.swagger.annotations.ApiOperation;
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
     * Gets all people in database
     *
     * @return List of people from database
     */
    @ApiOperation(
            value = "Gets all the people in database",
            notes = "Gives you a list of people in database",
            response = PersonDto.class
    )
    @GetMapping(path = "/getAllPeople", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PersonDto> getAllPeople(){
        return personService.getAllPeople();
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
    @GetMapping(path = "/getById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonDto getPerson(@PathVariable("id") int id){
        return personService.getPerson(id);
    }





    /**
     * Gets a person list by his first name
     *
     * @param firstName first name of person we want
     * @return          list of people with similar first name
     */
    @ApiOperation(
            value = "Gets list of people from database by their first name",
            notes = "Pass first name of people you want to get",
            response = PersonDto.class
    )
    @GetMapping(path = "/getByFirstName/{firstName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PersonDto> getPeopleByFirstName(@PathVariable("firstName") String firstName){
        return personService.getPeopleByFirstName(firstName);
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
    @GetMapping(path = "/getByCompany/{company}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PersonDto> getPeopleByCompany(@PathVariable("company") String company){
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
    @PostMapping(path = "/insert", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insertPerson(@RequestBody Person person){
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
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updatePerson(@RequestBody Person person){
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
    @DeleteMapping(path = "/delete/{id}")
    public void deletePersonWithSuchId(@PathVariable("id") int id){
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
    @DeleteMapping(path = "/delete")
    public void deletePerson(@RequestBody Person person){
        personService.deletePerson(person);
    }

}
