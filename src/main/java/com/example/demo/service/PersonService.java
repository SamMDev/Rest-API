package com.example.demo.service;

import com.example.demo.api.dto.PersonDto;
import com.example.demo.db.dao.PersonRepository;
import com.example.demo.db.model.Person;
import com.example.demo.exceptions.RecordAlreadyExistsException;
import com.example.demo.exceptions.RecordNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public PersonService(@Qualifier("personRepo") PersonRepository personRepository, ObjectMapper objectMapper) {
        this.personRepository = personRepository;
        this.objectMapper = objectMapper;
    }

    /**
     * Returns List of all the people in database
     *
     * @return  List of people
     */
    public List<PersonDto> getAllPeople(){
        return personRepository.findAll()
                .stream()
                .map(person -> objectMapper.convertValue(person, PersonDto.class))
                .collect(Collectors.toList());
    }


    /**
     * Returns person according to his id
     *
     * @param id    id of person we want to be returned
     * @return      person with such id
     */
    public PersonDto getPerson(int id){
        Optional<Person> person = personRepository.findById(id);

        if(!person.isPresent()){
            throw new RecordNotFoundException("No record with such id");
        }
        return objectMapper.convertValue(person.get(), PersonDto.class);
    }

    /**
     * Inserts person in database if not exists
     *
     * @param newPerson person to be inserted
     */
    public void insertPerson(Person newPerson){
        if(!personRepository.existsById(newPerson.getId())){
            personRepository.saveAndFlush(newPerson);
        }else{
            throw new RecordAlreadyExistsException("Person with such id is already registered");
        }
    }

    /**
     * Deletes person with given id
     *
     * @param id    id of person that is going to be deleted
     */
    public void deletePersonWithSuchId(int id){
        if(!personRepository.existsById(id)){
            throw new RecordNotFoundException("No record with such id");
        }else{
            personRepository.deleteById(id);
        }
    }

    /**
     * Deletes given person
     *
     * @param person    person to be deleted
     */
    public void deletePerson(Person person){
        if(personRepository.existsById(person.getId())){
            personRepository.delete(person);
        }else{
            throw new RecordNotFoundException("No record with such id");
        }
    }

    /**
     * Updates given person if exists
     *
     * @param givenPerson   Person to be updated
     */
    public void updatePerson(Person givenPerson){
        Optional<Person> person = personRepository.findById(givenPerson.getId());

        if(!person.isPresent()){
            throw new RecordNotFoundException("No such person found");
        }else{
            personRepository.save(givenPerson);
        }
    }


    /**
     * Gets list of people with similar first name
     *
     * @param name
     * @return          List of people
     */
    public List<PersonDto> getPeopleByNameSearch(String name){
        return personRepository.getPeopleByNameSearch(name).stream()
                .map(person -> objectMapper.convertValue(person, PersonDto.class))
                .collect(Collectors.toList());
    }


    /**
     * Gets list of people with given company
     *
     * @param company   company name
     * @return          List of people
     */
    public List<PersonDto> getPeopleByCompany(String company){
        return personRepository.getPersonByCompany(company).stream()
                .map(person -> objectMapper.convertValue(person, PersonDto.class))
                .collect(Collectors.toList());
    }
}
