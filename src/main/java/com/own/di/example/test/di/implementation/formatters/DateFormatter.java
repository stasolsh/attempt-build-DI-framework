package com.own.di.example.test.di.implementation.formatters;

import com.own.di.example.test.di.entity.ValidationResult;
import com.own.di.example.test.di.exception.DataFormatException;
import com.own.di.example.test.di.interfaces.Formatter;
import com.own.di.example.framework.annotation.Singleton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.own.di.example.test.di.entity.ValidationResult.ERROR;
import static com.own.di.example.test.di.entity.ValidationResult.OK;

@Singleton(qualifier = "dateFormatter")
public class DateFormatter implements Formatter<Date> {
    private String pattern;
    private Locale locale;
    private final ThreadLocal<SimpleDateFormat> dateFormat = ThreadLocal
            .withInitial(() -> new SimpleDateFormat(pattern, locale));


    @Override
    public String format(Date object) {
        return dateFormat.get().format(object);
    }

    @Override
    public Date parse(String date) {
        try {
            return dateFormat.get().parse(date);
        } catch (ParseException e) {
            throw new DataFormatException("Date format is invalid:" + date);
        }
    }

    @Override
    public ValidationResult isValid(String text) {
        try {
            dateFormat.get().parse(text);
            return OK;
        } catch (ParseException e) {
            return ERROR;
        }
    }

    @Override
    public void setAvailablePattern(String pattern) {
        this.pattern = pattern;
    }
    @Override
    public void setLocale(Locale locale){
        this.locale = locale;
    }

}
