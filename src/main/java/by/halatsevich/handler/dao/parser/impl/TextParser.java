package by.halatsevich.handler.dao.parser.impl;

import by.halatsevich.handler.dao.parser.AbstractParser;
import by.halatsevich.handler.entity.Component;
import by.halatsevich.handler.entity.impl.CodeBlock;
import by.halatsevich.handler.entity.impl.Text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextParser implements AbstractParser {
    private AbstractParser sentenceParser = new SentenceParser();
    private static final String REGEX_TEXT_CODE = "([^{}]+\\n)|(.*\\{\\n(.*\\n)+?\\n*}\\n)";

    @Override
    public Component parseComponent(String component) {
        Pattern pattern = Pattern.compile(REGEX_TEXT_CODE);
        Matcher matcher = pattern.matcher(component);
        Component text = new Text();
        while (matcher.find()) {
            String textBlock = matcher.group(1);
            String codeBlock = matcher.group(2);
            if (textBlock != null) {
                Component sentences = sentenceParser.parseComponent(textBlock);
                for (Component sentence : sentences.getComponents()) {
                    text.addComponent(sentence);
                }
            }
            if (codeBlock != null) {
                text.addComponent(new CodeBlock(codeBlock));
            }
        }
        return text;
    }
}
