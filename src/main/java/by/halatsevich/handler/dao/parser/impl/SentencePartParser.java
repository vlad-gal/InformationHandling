package by.halatsevich.handler.dao.parser.impl;

import by.halatsevich.handler.dao.parser.AbstractParser;
import by.halatsevich.handler.entity.Component;
import by.halatsevich.handler.entity.impl.Sentence;
import by.halatsevich.handler.entity.impl.Symbol;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentencePartParser implements AbstractParser {
    private static final String REGEX_WORD = "\\b[A-Za-zА-Яа-я]+\\b";
    private static final String REGEX_DIGIT = "(\\d+)|(\\d+\\.\\d+)";
    private static final String REGEX_PARTS = "((\\d+)|(\\d+\\.\\d+))|(\\b[A-Za-zА-Яа-я]+\\b)|(\\p{Punct})";
    private static final String REGEX_MARK = "\\p{Punct}";
    private AbstractParser wordParser = new WordParser();

    @Override
    public Component parseComponent(String component) {
        Pattern pattern = Pattern.compile(REGEX_PARTS);
        Matcher matcher = pattern.matcher(component);
        Component sentence = new Sentence();
        while (matcher.find()) {
            String part = matcher.group();
            if (part.matches(REGEX_WORD)) {
                Component word = wordParser.parseComponent(part);
                sentence.addComponent(word);
            } else if (part.matches(REGEX_DIGIT)) {
                sentence.addComponent(new Symbol(part, Symbol.SymbolType.DIGIT));
            } else if (part.matches(REGEX_MARK)) {
                sentence.addComponent(new Symbol(part, Symbol.SymbolType.PUNCT));
            }
        }
        return sentence;
    }
}
