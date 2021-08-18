package com.au.post.suburb.suburbservice.controller;

import com.au.post.suburb.suburbservice.model.Suburb;
import com.au.post.suburb.suburbservice.model.Suburbs;
import com.au.post.suburb.suburbservice.service.SuburbService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bnaragani created on 14/08/2021
 */
@RestController
@RequestMapping("/v1/suburbs")
@Api(description = "Add and search suburbs in Australia")
public class SuburbController {

    @Autowired
    SuburbService suburbService;

//    @GetMapping(produces = "application/json")
//    public ResponseEntity<Suburbs> searchSuburbs(@RequestParam("q") String searchValue) {
//        Suburbs suburbs = suburbService.searchSuburbs(searchValue);
//        return new ResponseEntity<>(suburbs, HttpStatus.OK);
//    }

    @GetMapping(produces = "application/json")
    @ApiOperation(value = "Search suburbs by name or postcode")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = Suburbs.class),
            @ApiResponse(code = 400, message = "Invalid input")})
    public ResponseEntity<Suburbs> searchSuburbsByNameOrCode(
            @ApiParam("Search suburbs by postcode") @RequestParam(value = "postcode", defaultValue = "") String postcode,
            @ApiParam("Search suburbs by name") @RequestParam(value = "name", defaultValue = "") String name) {
        Suburbs suburbs;
        if (!postcode.isEmpty() || !name.isEmpty()) {
            suburbs = suburbService.searchSuburbsByPostcodeOrName(postcode, name);
        } else {
            return new ResponseEntity("Please search by post code or suburb name", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(suburbs, HttpStatus.OK);
    }


    @PostMapping(consumes = "application/json", produces = "application/json")
    @ApiOperation(value = "Add a new suburb information")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Suburb added"),
            @ApiResponse(code = 400, message = "Invalid input"),
            @ApiResponse(code = 409, message = "Suburb already exists")})
    public ResponseEntity<Suburb> addSuburbInfo(
            @ApiParam("Suburb information to add. Cannot be null or empty.") @RequestBody Suburb suburb) {
        // postcode and name have to be unique
        if (suburbService.isSuburbExists(suburb.getPostCode(), suburb.getName())) {
            return new ResponseEntity("Suburb information exists already!", HttpStatus.ALREADY_REPORTED);
        }
        Suburb newSuburb = suburbService.addNewSuburb(suburb);
        return new ResponseEntity(newSuburb, HttpStatus.CREATED);
    }

}
