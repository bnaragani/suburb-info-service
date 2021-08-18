package com.au.post.suburb.suburbservice.model;

import io.swagger.annotations.ApiModel;
import java.util.List;

/**
 * @author bnaragani created on 15/08/2021
 */
@ApiModel(description = "List of Suburbs information")
public class Suburbs {

    List<Suburb> suburbs;

    public Suburbs() {
    }

    public List<Suburb> getSuburbs() {
        return suburbs;
    }

    public void setSuburbs(List<Suburb> suburbs) {
        this.suburbs = suburbs;
    }
}
