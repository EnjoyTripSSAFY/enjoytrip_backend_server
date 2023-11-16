package com.ssafy.enjoytrip_springboot.trip.query.controller;

import com.ssafy.enjoytrip_springboot.trip.query.service.AttractionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest(controllers = {TripApiController.class})
@SpringBootTest
@AutoConfigureMockMvc
class TripApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AttractionService attractionService;

    @Test
    void context(){
        assertNotNull(mockMvc);
        assertNotNull(attractionService);
    }

    @Test
    void getStateList() throws Exception {
        mockMvc.perform(get("/trip/state"))
                .andExpect(status().isOk())
                .andDo(print());

    }
    @Test
    void getCityList() throws Exception {
        mockMvc.perform(get("/trip/city")
                        .param("areaCode", "3"))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    void getTripList() throws Exception {
        mockMvc.perform(get("/trip/triplist")
//                        .param("areaCode", "1")
//                        .param("sigunguCode", "0")
//                        .param("contentTypeId", "0")
//                        .param("pageNo", "0")
                )
                .andExpect(status().isOk())
                .andDo(print());
    }
}