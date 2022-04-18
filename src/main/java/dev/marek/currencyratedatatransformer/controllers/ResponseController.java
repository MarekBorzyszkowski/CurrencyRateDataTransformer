package dev.marek.currencyratedatatransformer.controllers;

import dev.marek.currencyratedatatransformer.models.NbpRateWithDifferences;
import dev.marek.currencyratedatatransformer.services.ExchangeRatesService;
import org.apache.commons.validator.GenericValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@RestController
public class ResponseController {

    @Autowired
    private ExchangeRatesService exchangeRatesService;

    @GetMapping("/dates/{startDate}/{endDate}")
    public ResponseEntity<List<NbpRateWithDifferences>> getNbpRatesBetweenDates(@PathVariable String startDate, @PathVariable String endDate) {

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
