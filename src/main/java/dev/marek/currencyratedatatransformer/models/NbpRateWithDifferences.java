package dev.marek.currencyratedatatransformer.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Details about certain rate")
public class NbpRateWithDifferences {

    @ApiModelProperty(notes = "Date when the rate was read")
    private String effectiveDate;
    @ApiModelProperty(notes = "The bid value of rate")
    private String bid;
    @ApiModelProperty(notes = "The ask value of rate")
    private String ask;
    @ApiModelProperty(notes = "The difference this bid value, and the previous bid value")
    private String bidDifference;
    @ApiModelProperty(notes = "The difference this ask value, and the previous ask value")
    private String askDifference;

    public NbpRateWithDifferences(String effectiveDate, String bid, String ask) {
        this.effectiveDate = effectiveDate;
        this.bid = bid;
        this.ask = ask;
        this.bidDifference = "0";
        this.askDifference = "0";
    }
}
