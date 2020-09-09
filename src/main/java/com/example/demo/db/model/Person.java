package com.example.demo.db.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.NonNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "people")
public class Person {
    @Id @NonNull
    private int id;
    private String name;
    private String company;

    public Person(@JsonProperty("id") int id, @JsonProperty("name") String name, @JsonProperty("company") String company) {
        this.id = id;
        this.name = name;
        this.company = company;
    }

    public Person(){}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCompany(){
        return company;
    }
}
