package com.example.demo.db.dao;

import com.example.demo.db.model.Person;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Qualifier("personRepo")
public interface PersonRepository extends JpaRepository<Person, Integer> {


    @Query(value = "select p from Person p where p.name like :firstName%")
    List<Person> getPeopleByFirstName(@Param("firstName") String firstName);

    @Query(value = "select p from Person p where p.company = :companyName")
    List<Person> getPersonByCompany(@Param("companyName") String company);
}
