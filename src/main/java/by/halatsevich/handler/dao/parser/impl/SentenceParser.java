package by.halatsevich.handler.dao.parser.impl;

import by.halatsevich.handler.dao.parser.AbstractParser;
import by.halatsevich.handler.entity.Component;
import by.halatsevich.handler.entity.impl.Sentence;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser implements AbstractParser {
    private AbstractParser partParser = new SentencePartParser();
    private static final String REGEX_SENTENCE = "([A-Za-zА-Яа-я]+(\\s|[-'\",():;])+)*[A-Za-zА-Яа-я]+[)\"]?[.!?:]";

    @Override
    public Component parseComponent(String component) {
        Pattern pattern = Pattern.compile(REGEX_SENTENCE);
        Matcher matcher = pattern.matcher(component);
        Component sentence = new Sentence();
        while (matcher.find()) {
            String parts = matcher.group();
            Component part = partParser.parseComponent(parts);
            sentence.addComponent(part);
        }
        return sentence;
    }
}