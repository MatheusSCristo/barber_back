package com.matheus.barber.utils;

import com.matheus.barber.enums.ProhibitedWordsEnum;
import com.matheus.barber.infra.exceptions.TextHasProhibitedWordsException;

import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Collectors;

public class FilterProhibitedWords {

    public static void filter(String text) {
        String[] words = text.split(" ");
        Set<String> prohibitedWordsSet = EnumSet.allOf(ProhibitedWordsEnum.class).stream()
                .map(ProhibitedWordsEnum::getWord)
                .collect(Collectors.toSet());
        for (String word : words) {
            if (prohibitedWordsSet.contains(word.toLowerCase())) throw new TextHasProhibitedWordsException();
        }
    }
}
