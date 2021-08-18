package com.au.post.suburb.suburbservice.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author bnaragani created on 14/08/2021
 */
@Entity
@Table(name = "suburb")
@ApiModel(description = "Suburb information")
public class Suburb {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "suburb_seq_gen")
    @SequenceGenerator(name = "suburb_seq_gen", sequenceName = "suburb_seq_gen", allocationSize = 1)
    Integer id;

    @ApiModelProperty(value = "post code")
    @Column(name = "post_code")
    String postcode;

    @ApiModelProperty(value = "suburb")
    @Column(name = "name")
    String name;

    @ApiModelProperty(value = "state code")
    @Column(name = "state_code")
    String state;

    public Suburb() {
    }

    public Suburb(String name, String postCode, String state) {
        this.name = name;
        this.postcode = postCode;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPostCode() {
        return postcode;
    }

    public void setPostCode(String postcode) {
        this.postcode = postcode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Suburb{" +
                "id=" + id +
                ", postCode='" + postcode + '\'' +
                ", name='" + name + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Suburb)) {
            return false;
        }
        Suburb suburb = (Suburb) o;
        return id.equals(suburb.id) && getPostCode().equals(suburb.getPostCode()) && getName().equals(suburb.getName())
                && getState().equals(
                suburb.getState());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getPostCode(), getName(), getState());
    }
}
