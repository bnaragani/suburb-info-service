package com.au.post.suburb.suburbservice.service;

import com.au.post.suburb.suburbservice.model.Suburb;
import com.au.post.suburb.suburbservice.model.Suburbs;
import com.au.post.suburb.suburbservice.repository.SuburbRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author bnaragani created on 14/08/2021
 */
@Service
public class SuburbServiceImpl implements SuburbService {

    @Autowired
    SuburbRepository suburbRepository;

    @Override
    public Suburbs searchSuburbs(String searchValue) {
        List<Suburb> suburbList = suburbRepository.findAll().stream()
                .filter(suburb -> (suburb.getPostCode().equals(searchValue) || suburb.getName().toLowerCase(Locale.ROOT).contains(searchValue.toLowerCase(
                        Locale.ROOT))))
                .collect(Collectors.toList());
        Suburbs suburbs = new Suburbs();
        suburbs.setSuburbs(suburbList);

        return suburbs;

    }

    @Override
    public Suburb addNewSuburb(Suburb suburb) {
        suburb = suburbRepository.save(suburb);
        return suburb;
    }

    @Override
    public boolean isSuburbExists(String postcode, String name) {
        long count = suburbRepository.findAll().stream()
                .filter(suburb -> (suburb.getName().equalsIgnoreCase(name) && suburb.getPostCode().equals(postcode))).count();
        if (count > 0) {
            return true;
        }
        return false;
    }


    private List<Suburb> searchSuburbsByName(String name) {
        return suburbRepository.findAll().stream()
                .filter(suburb -> suburb.getName().toLowerCase(Locale.ROOT).contains(name.toLowerCase(Locale.ROOT))).collect(Collectors.toList());
    }

    private List<Suburb> searchSuburbsByPostCode(String postcode) {
        return suburbRepository.findAll().stream()
                .filter(suburb -> suburb.getPostCode().equals(postcode)).collect(Collectors.toList());
    }

    @Override
    public Suburbs searchSuburbsByPostcodeOrName(String postcode, String name) {
        List<Suburb> suburbList = new ArrayList<>();
        if (!postcode.isEmpty() && !name.isEmpty()) {
            suburbList = suburbRepository.findAll().stream()
                    .filter(suburb -> (suburb.getName().toLowerCase(Locale.ROOT).contains(name.toLowerCase(Locale.ROOT)) && suburb.getPostCode().equals(postcode)))
                    .collect(Collectors.toList());
        } else if (!postcode.isEmpty()) {
            suburbList = searchSuburbsByPostCode(postcode);
        } else if (!name.isEmpty()) {
            suburbList = searchSuburbsByName(name);
        }
        Suburbs suburbs = new Suburbs();
        suburbs.setSuburbs(suburbList);
        return suburbs;
    }


}
