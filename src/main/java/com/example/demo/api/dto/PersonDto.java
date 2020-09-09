package com.example.demo.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonDto {
    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("company")
    private String company;

    public PersonDto(int id, String name, String company) {
        this.id = id;
        this.name = name;
        this.company = company;
    }

    public PersonDto(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setCompany(String company){
        this.company = company;
    }


    public String getCompany(){
        return company;
    }

}
