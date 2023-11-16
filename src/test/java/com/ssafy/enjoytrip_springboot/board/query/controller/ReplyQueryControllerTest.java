package com.ssafy.enjoytrip_springboot.board.query.controller;

import com.ssafy.enjoytrip_springboot.board.query.service.ReplyQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;


@WebMvcTest(ReplyQueryController.class)
class ReplyQueryControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    ReplyQueryService service;

    void context(){
        assertNotNull(mvc);
        assertNotNull(service);
    }

}