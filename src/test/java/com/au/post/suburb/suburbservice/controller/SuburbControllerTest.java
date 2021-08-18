package com.au.post.suburb.suburbservice.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.au.post.suburb.suburbservice.SuburbServiceApplicationTests;
import com.au.post.suburb.suburbservice.model.Suburb;
import com.au.post.suburb.suburbservice.model.Suburbs;
import com.au.post.suburb.suburbservice.service.SuburbService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

class SuburbControllerTest extends SuburbServiceApplicationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Mock
    SuburbService suburbService;

    private MockMvc mockMvc;
    Suburb suburb = new Suburb("Hawthorn", "3132", "VIC");

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).
                apply(SecurityMockMvcConfigurers.springSecurity()).build();
    }

    @Test
    void searchSuburbsByNameOrCode() throws Exception {
        Suburbs suburbs = new Suburbs();
        suburbs.setSuburbs(Arrays.asList(suburb));
        Mockito.when(suburbService.searchSuburbsByPostcodeOrName("3132", "")).thenReturn(suburbs);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/v1/suburbs")
                        .queryParam("postcode", "3132")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        String resultSuburb = result.getResponse().toString();
        assertNotNull(resultSuburb);
    }

    @Test
    void addSuburbInfoThrowsErrorWithoutAuthentication() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/suburbs")
                        .content(convertToJSON(suburb))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isForbidden());

    }

    @Test
    void addSuburbInfoSuccess() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/v1/suburbs")
                        .content(convertToJSON(suburb))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.user("test")))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();

        String resultSuburb = result.getResponse().toString();
        assertNotNull(resultSuburb);
    }

    private String convertToJSON(Suburb suburb) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonSuburb = mapper.writeValueAsString(suburb);
            return jsonSuburb;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}