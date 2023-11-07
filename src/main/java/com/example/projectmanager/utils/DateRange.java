package com.example.projectmanager.utils;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DateRange {
    @JsonProperty("start")
    private Date start;

    @JsonProperty("end")
    private Date end;
}
