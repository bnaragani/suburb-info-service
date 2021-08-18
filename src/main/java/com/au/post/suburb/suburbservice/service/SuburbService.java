package com.au.post.suburb.suburbservice.service;

import com.au.post.suburb.suburbservice.model.Suburb;
import com.au.post.suburb.suburbservice.model.Suburbs;
import java.util.List;
import java.util.Optional;

/**
 * @author bnaragani created on 14/08/2021
 */
public interface SuburbService {

   Suburbs searchSuburbs(String code);

   Suburb addNewSuburb(Suburb suburb);

   boolean isSuburbExists(String postcode, String name);

   Suburbs searchSuburbsByPostcodeOrName(String postcode, String name);

}
