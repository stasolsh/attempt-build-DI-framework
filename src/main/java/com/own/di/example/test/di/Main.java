package com.own.di.example.test.di;

import com.own.di.example.framework.context.ApplicationContext;
import com.own.di.example.test.di.implementation.formatters.*;

import java.time.LocalTime;
import java.util.Date;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        Locale locale = Locale.GERMANY;
        ApplicationContext context = ApplicationRunner.run("com.own.di.example");

        // Test email
        EmailFormatter emailFormatter = context.getObject(EmailFormatter.class);
        String email = "stasolsh77@gmail.com";
        System.out.println(emailFormatter.parse(email));
        System.out.println("Email valid: " + emailFormatter.isValid(email));
        System.out.println("===================================================");

        // Test email bank account number
        BankAccountNumberFormatter bankAccountNumberFormatter = context.getObject(BankAccountNumberFormatter.class);
        String bankAccountNumber = "121232323132323232";
        System.out.println(bankAccountNumberFormatter.parse(bankAccountNumber));
        System.out.println("Bank account number valid: " + bankAccountNumberFormatter.isValid(bankAccountNumber));
        System.out.println("===================================================");

        // Test currency
        CurrencyFormatter currencyFormatter = context.getObject(CurrencyFormatter.class);
        currencyFormatter.setLocale(locale);
        String currency = "100,00";
        System.out.println(currencyFormatter.parse(currency));
        System.out.println("Currency valid: " + currencyFormatter.isValid(currency));
        System.out.println(currencyFormatter.format(1123.32));
        System.out.println("===================================================");

        // Test date formatter
        DateFormatter dateFormatter = context.getObject(DateFormatter.class);
        dateFormatter.setAvailablePattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
        dateFormatter.setLocale(locale);
        String date = "2012-07-10 14:58:00.000000";
        System.out.println(dateFormatter.parse(date));
        System.out.println("Date valid: " + dateFormatter.isValid(date));
        System.out.println(dateFormatter.format(new Date()));
        System.out.println("===================================================");

        // Test number formatter
        NumberFormatter numberFormatter = context.getObject(NumberFormatter.class);
        String number = "532324.2442";
        System.out.println(numberFormatter.parse(number).doubleValue());
        System.out.println("Number valid: " + numberFormatter.isValid(number));
        System.out.println(numberFormatter.format(21233.23121));
        System.out.println("===================================================");

        // Test phone number formatter
        PhoneNumberFormatter phoneNumberFormatter = context.getObject(PhoneNumberFormatter.class);
        String phoneNumber = "+4917622971038";
        System.out.println(phoneNumberFormatter.parse(phoneNumber));
        System.out.println("Phone number valid: " + phoneNumberFormatter.isValid(number));
        System.out.println(phoneNumberFormatter.format(phoneNumber));
        System.out.println("===================================================");

        // Test SSN formatter
        SSNFormatter ssnFormatter = context.getObject(SSNFormatter.class);
        String ssnNumber = "123-45-6789";
        System.out.println(ssnFormatter.parse(ssnNumber));
        System.out.println("SSN valid: " + ssnFormatter.isValid(ssnNumber));
        System.out.println(ssnFormatter.format(ssnNumber));
        System.out.println("===================================================");

        // Test time formatter
        TimeFormatter timeFormatter = context.getObject(TimeFormatter.class);
        timeFormatter.setAvailablePattern("HH:mm");
        String time = "900";
        System.out.println(timeFormatter.parse(time));
        System.out.println(timeFormatter.format(LocalTime.now()));
    }
}

