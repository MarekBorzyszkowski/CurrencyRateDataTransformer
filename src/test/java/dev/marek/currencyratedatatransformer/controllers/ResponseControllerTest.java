package dev.marek.currencyratedatatransformer.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class ResponseControllerTest {

    @Test
    void getRatesBetweenDates_WrongStartDate_ExceptionThrown() {
        ResponseController controller = new ResponseController();
        String startDate = "2022-02-a";
        String endDate = "2022-04-11";
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> controller.getNbpRatesBetweenDates(startDate,endDate));
        assertEquals(exception.getReason(), "Start date invalid");
    }

    @Test
    void getRatesBetweenDates_WrongEndDate_ExceptionThrown() {
        ResponseController controller = new ResponseController();
        String startDate = "2022-02-02";
        String endDate = "2022-04-dd";
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> controller.getNbpRatesBetweenDates(startDate,endDate));
        assertEquals(exception.getReason(), "End date invalid");
    }

    @Test
    void getRatesBetweenDates_StartDateIsAfterEndDate_ExceptionThrown() {
        ResponseController controller = new ResponseController();
        String startDate = "2022-02-02";
        String endDate = "2022-02-01";
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> controller.getNbpRatesBetweenDates(startDate,endDate));
        assertEquals(exception.getReason(), "Start date is after end date");
    }

}