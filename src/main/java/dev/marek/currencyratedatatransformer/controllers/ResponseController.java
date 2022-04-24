package dev.marek.currencyratedatatransformer.controllers;

import dev.marek.currencyratedatatransformer.models.NbpRateWithDifferences;
import dev.marek.currencyratedatatransformer.services.ExchangeRatesService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.validator.GenericValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ResponseController {

    @Autowired
    private ExchangeRatesService exchangeRatesService;

    @GetMapping("/dates/{startDate}")
    @ApiOperation(value = "Shows USD ask and bid rates, as well as differences between days in range from startDate to current date",
                    notes = "Provide startDate to get data abut USD rates, StartDate format: YYYY-MM-DD",
                    response = List.class)
    public ResponseEntity<List<NbpRateWithDifferences>> getNbpRatesFromDateToNow(@ApiParam(value = "StartDate value for the beginning of dates range") @PathVariable String startDate) {
        LocalDate endDate = LocalDate.now();
        return getNbpRatesBetweenDates(startDate, endDate.toString());
    }

    @GetMapping("/dates/{startDate}/{endDate}")
    @ApiOperation(value = "Shows USD ask and bid rates, as well as differences between days in range from startDate to endDate",
            notes = "Provide startDate and endDate to get data abut USD rates, StartDate and EndDate format: YYYY-MM-DD",
            response = List.class)
    public ResponseEntity<List<NbpRateWithDifferences>> getNbpRatesBetweenDates(@ApiParam(value = "StartDate value for the beginning of dates range")@PathVariable String startDate
            ,@ApiParam(value = "EndDate value for the ending of dates range") @PathVariable String endDate) {

        LocalDate start;
        if (!GenericValidator.isDate(startDate, "yyyy-MM-dd", true)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Start date invalid");
        }
        start = LocalDate.parse(startDate);

        LocalDate end;
        if (!GenericValidator.isDate(endDate, "yyyy-MM-dd", true)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "End date invalid");
        }
        end = LocalDate.parse(endDate);

        if (start.isAfter(end)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Start date is after end date");
        }

        return ResponseEntity.ok(this.exchangeRatesService.getRatesBetweenDates(start, end));
    }
}
