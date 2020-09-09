package com.example.demo.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(
        description = "Details about person"
)
public class PersonDto {

    @JsonProperty("id")
    @ApiModelProperty(notes = "Unique id")
    private int id;

    @ApiModelProperty(notes = "Name of person")
    @JsonProperty("name")
    private String name;

    @ApiModelProperty(notes = "Company the person is employed in")
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
