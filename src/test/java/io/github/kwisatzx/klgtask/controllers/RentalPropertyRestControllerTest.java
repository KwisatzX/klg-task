package io.github.kwisatzx.klgtask.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RentalPropertyRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void successfullyReturnsReservationForProperty() throws Exception {
        String expectedJson = "[{\"property\":{\"id\":2,\"name\":\"Magazyn Hutnicza 6/12, 40-241 Katowice\"," +
                "\"ownerName\":\"Anna Nowak\",\"unitPrice\":10.2,\"surfaceArea\":8.0," +
                "\"description\":\"Magazyn nr.12 na ul. Hutnicza 6\"},\"renterName\":\"Jan Kowalski\"," +
                "\"startDate\":\"2021-12-01\",\"endDate\":\"2022-01-31\",\"monthlyCost\":81.6}]";

        this.mockMvc.perform(get("/api/property/2/reservations")).andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }
}