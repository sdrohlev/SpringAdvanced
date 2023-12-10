package com.example.dorne.web;

import com.example.dorne.util.TestDataUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EventControllerTestIT {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TestDataUtils testDataUtils;

    @BeforeEach
    void setUp() {
        testDataUtils.createTestUser();
        testDataUtils.createTestDestination();
    }

    @Test
    void testAddEvent() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.post("/events/add")
                        .param("name", "New Year Party")
                        .param("description", "Event description")
                        .param("dayAndTime", String.valueOf(LocalDateTime.now()))
                        .param("imgUrl", "https://luckybansko.com/wp-content/uploads/2017/04/Detska_ploshtadka_big_14-1.png")
                        .param("destination", "Sofia, Bulgaria")

        ).andExpect(status().is3xxRedirection());




    }
}
