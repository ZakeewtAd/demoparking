package be.matthieu.demoparking.controllers;

import be.matthieu.demoparking.DemoParkingApplication;
import be.matthieu.demoparking.repository.models.Parking;
import be.matthieu.demoparking.service.ParkingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { DemoParkingApplication.class })
@WebAppConfiguration
class ParkingControllerIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ParkingService parkingService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();

        parkingService.deleteAll();
        parkingService.saveOrUpdate(new Parking(10L, "10 Places parking"));
        parkingService.saveOrUpdate(new Parking(30L, "Confluence"));
    }

    @Test
    void listParkingTest() throws Exception {
            this.mockMvc
                .perform(get("/parking"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].maxCapacity").value(10L))
                .andExpect(jsonPath("$[1].name").value("Confluence"));
    }

    @Test
    void createParkingTest() throws Exception {
        this.mockMvc
                .perform(
                    post("/parking")
                        .content("{ \"name\": \"My Super Parking\", \"maxCapacity\": 400 }")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.maxCapacity").value(400L))
                .andExpect(jsonPath("$.name").value("My Super Parking"))
                .andExpect(jsonPath("$.id").value(3L));
    }
}