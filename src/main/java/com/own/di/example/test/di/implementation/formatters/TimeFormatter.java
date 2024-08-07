package com.own.di.example.test.di.implementation.formatters;

import com.own.di.example.test.di.entity.ValidationResult;
import com.own.di.example.test.di.interfaces.Formatter;
import com.own.di.example.framework.annotation.Singleton;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static com.own.di.example.test.di.entity.ValidationResult.ERROR;
import static com.own.di.example.test.di.entity.ValidationResult.OK;

@Singleton(qualifier = "timeFormatter")
public class TimeFormatter implements Formatter<LocalTime> {
    private String pattern;

    @Override
    public String format(LocalTime time) {
        return time.format(DateTimeFormatter.ofPattern(pattern));
    }

    @Override
    public LocalTime parse(String time) {
        time = time.replaceAll("\\D", "");
        int hours = Integer.parseInt(time.substring(0, Math.max(time.length() - 2, 0)));
        int minutes = Integer.parseInt(time.substring(Math.max(time.length() - 2, 0)));
        return LocalTime.of(hours, minutes);
    }

    @Override
    public ValidationResult isValid(String time) {
        if (time == null) {
            return ERROR;
        }
        try {
            LocalTime.parse(time);
            return OK;
        } catch (Exception e) {
            return ERROR;
        }
    }

    @Override
    public void setAvailablePattern(String pattern) {
        this.pattern = pattern;
    }
}
