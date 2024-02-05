package com.example.leetcode.java.java12;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.CaseUtils;

import java.util.ArrayList;
import java.util.List;

import static java.util.Calendar.*;

public class Test {

    public static void main(String[] args) {
        List<String> names = List.of(
                "   Alex",
                "brian");

        List<String> transformedNames = new ArrayList<>();

        for (String name : names) {
            String transformedName = name.transform(String::strip);
            transformedNames.add(transformedName);
        }
        transformedNames.forEach(System.out::println);


        int day = SATURDAY;
        boolean isWeekend = switch (day) {
            case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> false;
            case SATURDAY, SUNDAY -> true;
            default -> throw new IllegalStateException("Illegal day entry :: " + day);
        };

        System.out.println(isWeekend);

    }
}
