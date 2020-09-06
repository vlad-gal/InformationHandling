package by.halatsevich.handler.dao.parser.impl;

import by.halatsevich.handler.dao.parser.AbstractParser;
import by.halatsevich.handler.entity.Component;
import by.halatsevich.handler.entity.impl.Symbol;
import by.halatsevich.handler.entity.impl.Word;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordParser implements AbstractParser {
    private static final String REGEX_CHAR = "[A-Za-zА-Яа-я]";

    @Override
    public Component parseComponent(String component) {
        Pattern pattern = Pattern.compile(REGEX_CHAR);
        Matcher matcher = pattern.matcher(component);
        Component word = new Word();
        while (matcher.find()) {
            String chr = matcher.group();
            word.addComponent(new Symbol(chr, Symbol.SymbolType.CHAR));
        }
        return word;
    }
}
