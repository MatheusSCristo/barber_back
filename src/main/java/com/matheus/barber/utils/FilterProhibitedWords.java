package com.matheus.barber.utils;

import com.matheus.barber.enums.ProhibitedWordsEnum;
import com.matheus.barber.infra.exceptions.TextHasProhibitedWordsException;
import org.ahocorasick.trie.Emit;
import org.ahocorasick.trie.Trie;

import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Collectors;

public class FilterProhibitedWords {

    public static void filter(String text) {
        Set<String> prohibitedWordsSet = EnumSet.allOf(ProhibitedWordsEnum.class).stream()
                .map(ProhibitedWordsEnum::getWord)
                .collect(Collectors.toSet());
        Trie.TrieBuilder builder = Trie.builder().ignoreCase().ignoreOverlaps().stopOnHit();
        for (String prohibitedWord : prohibitedWordsSet) {
            builder.addKeyword(prohibitedWord);
        }
        Trie trie = builder.build();
        text = text.replaceAll("(?i)(\\p{L})\\d+(\\p{L})", "$1$2");
        text = text.replaceAll("(?i)\\d+(\\p{L})", "$1");
        text = text.replaceAll("(?i)(\\p{L})\\d+", "$1");
        text = text.replaceAll("[^\\p{L}\\d\\s]", "");
        Iterable<Emit> emits = trie.parseText(text);
        for (Emit emit : emits) {
            throw new TextHasProhibitedWordsException();
        }
    }
}









