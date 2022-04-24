package dev.marek.currencyratedatatransformer.services;

import dev.marek.currencyratedatatransformer.models.NbpRateWithDifferences;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
class NbpExchangeRatesServiceTest {

    @Autowired
    JacksonTester<List<NbpRateWithDifferences>> jacksonTester;

    @Test
    void contextLoads(){
       assertThat(jacksonTester).isNotNull();
    }

    @Test
    void getRatesBetweenDates_DuringHolidays_EmptyJsonListResponse() throws IOException {
        NbpExchangeRatesService service = new NbpExchangeRatesService();
        LocalDate startDate = LocalDate.of(2022, 4, 16);
        LocalDate endDate = LocalDate.of(2022, 4, 17);
        JsonContent<List<NbpRateWithDifferences>> json = jacksonTester.write(service.getRatesBetweenDates(startDate, endDate));
        assertThat(json).isEqualToJson(new ClassPathResource("emptyListResponse.json"));
    }

    @Test
    void getRatesBetweenDates_SameDateWithRate_SingleDayJsonResponse() throws IOException {
        NbpExchangeRatesService service = new NbpExchangeRatesService();
        LocalDate startDate = LocalDate.of(2022, 4, 12);
        LocalDate endDate = LocalDate.of(2022, 4, 12);
        JsonContent<List<NbpRateWithDifferences>> json = jacksonTester.write(service.getRatesBetweenDates(startDate, endDate));
        assertThat(json).isEqualToJson(new ClassPathResource("singleDayResponse.json"));
    }

    @Test
    void getRatesBetweenDates_WholeYear_WholeYearJsonResponse() throws IOException {
        NbpExchangeRatesService service = new NbpExchangeRatesService();
        LocalDate startDate = LocalDate.of(2021, 1, 1);
        LocalDate endDate = LocalDate.of(2021, 12, 31);
        JsonContent<List<NbpRateWithDifferences>> json = jacksonTester.write(service.getRatesBetweenDates(startDate, endDate));
        assertThat(json).isEqualToJson(new ClassPathResource("wholeYearResponse.json"));
    }

    @Test
    void getRatesBetweenDates_FirstDatePossible_FirstDayJsonResponse() throws IOException {
        NbpExchangeRatesService service = new NbpExchangeRatesService();
        LocalDate startDate = LocalDate.of(2002, 1, 2);
        LocalDate endDate = LocalDate.of(2002, 1, 2);
        JsonContent<List<NbpRateWithDifferences>> json = jacksonTester.write(service.getRatesBetweenDates(startDate, endDate));
        assertThat(json).isEqualToJson(new ClassPathResource("firstDateResponse.json"));
    }

    @Test
    void getRatesBetweenDates_BigRange_BigJsonResponse() throws IOException {
        NbpExchangeRatesService service = new NbpExchangeRatesService();
        LocalDate startDate = LocalDate.of(2002, 1, 2);
        LocalDate endDate = LocalDate.of(2022, 4, 15);
        JsonContent<List<NbpRateWithDifferences>> json = jacksonTester.write(service.getRatesBetweenDates(startDate, endDate));
        assertThat(json).isEqualToJson(new ClassPathResource("bigResponse.json"));
    }

    @Test
    void getRatesBetweenDates_DuringHolidaysBetweenYears_EmptyJsonListResponse() throws IOException {
        NbpExchangeRatesService service = new NbpExchangeRatesService();
        LocalDate startDate = LocalDate.of(2017, 12, 31);
        LocalDate endDate = LocalDate.of(2018, 1, 1);
        JsonContent<List<NbpRateWithDifferences>> json = jacksonTester.write(service.getRatesBetweenDates(startDate, endDate));
        assertThat(json).isEqualToJson(new ClassPathResource("emptyListResponse.json"));
    }

}