package by.halatsevich.handler.service.impl;

import by.halatsevich.handler.dao.DaoFactory;
import by.halatsevich.handler.dao.TextDao;
import by.halatsevich.handler.dao.exception.DaoException;
import by.halatsevich.handler.entity.Component;
import by.halatsevich.handler.entity.impl.Sentence;
import by.halatsevich.handler.entity.impl.Symbol;
import by.halatsevich.handler.entity.impl.Text;
import by.halatsevich.handler.entity.impl.Word;
import by.halatsevich.handler.service.TextService;
import by.halatsevich.handler.service.exception.ServiceException;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TextServiceImpl implements TextService {
    private static final String REGEX_VOWEL = "[aeiouyAEIOUYаеёиоуэыюяАЕЁИОУЭЫЮЯ]";
    private static final String REGEX_CONSONANT = "\\b[^aeiouyAEIOUYаеёиоуэыюяАЕЁИОУЭЫЮЯ][A-Za-zА-Яа-я]{%d}\\b";
    private static final String REGEX_DELIMITER = "";

    @Override
    public Map<Component, Integer> showAscendingOrderByCountWordsInSentences() throws ServiceException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        TextDao dao = daoFactory.getTextDao();
        Component text;
        try {
            text = dao.readText();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        Map<Component, Integer> counts = new LinkedHashMap<>();
        for (Component component : text.getComponents()) {
            if (component instanceof Sentence) {
                int count = 0;
                Sentence sentence = (Sentence) component;
                for (Component word : sentence.getComponents()) {
                    if (word instanceof Word) {
                        count++;
                    }
                }
                counts.put(sentence, count);
            }
        }
        return counts.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }

    @Override
    public Component replaceFirstAndLastWordInSentence() throws ServiceException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        TextDao dao = daoFactory.getTextDao();
        Component text;
        try {
            text = dao.readText();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        Component changedText = new Text();
        for (Component component : text.getComponents()) {
            if (component instanceof Sentence) {
                Component changedSentence = replaceWordsInSentence(component);
                changedText.addComponent(changedSentence);
                continue;
            }
            changedText.addComponent(component);
        }
        return changedText;
    }

    private Component replaceWordsInSentence(Component sentence) {
        int firstIndex = 0;
        int lastIndex = sentence.getComponents().size() - 2;
        Word firstWord = (Word) sentence.getComponents().get(firstIndex);
        Word lastWord = (Word) sentence.getComponents().get(lastIndex);
        Sentence newSentence = new Sentence();
        newSentence.addComponent(lastWord);
        for (int i = 1; i < sentence.getComponents().size() - 2; i++) {
            newSentence.addComponent(sentence.getComponents().get(i));
        }
        newSentence.addComponent(firstWord);
        newSentence.addComponent(sentence.getComponents().get(sentence.getComponents().size() - 1));
        return newSentence;
    }

    @Override
    public Map<Component, Double> showRelationBetweenVowelAndAllLettersInWord() throws ServiceException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        TextDao dao = daoFactory.getTextDao();
        Component text;
        try {
            text = dao.readText();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        Map<Component, Double> result = new HashMap<>();
        for (Component component : text.getComponents()) {
            if (component instanceof Sentence) {
                Sentence sentence = (Sentence) component;
                for (Component parts : sentence.getComponents()) {
                    if (parts instanceof Word) {
                        Pattern pattern = Pattern.compile(REGEX_VOWEL);
                        Matcher matcher = pattern.matcher(parts.getComponents().toString());
                        double wordLength = parts.getComponents().size();
                        double vowelCount = 0;
                        while (matcher.find()) {
                            vowelCount++;
                        }
                        double relation = vowelCount / wordLength;
                        result.put(parts, relation);
                    }
                }
            }
        }
        return result;
    }

    @Override
    public Component deleteAllWordsWithDefineLengthStartWithConsonant(int length) throws ServiceException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        TextDao dao = daoFactory.getTextDao();
        Component text;
        try {
            text = dao.readText();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        Component changedText = new Text();
        for (Component component : text.getComponents()) {
            if (component instanceof Sentence) {
                Component changedSentence = deleteWordFromSentence(component, length);
                changedText.addComponent(changedSentence);
                continue;
            }
            changedText.addComponent(component);
        }
        return changedText;
    }

    private Component deleteWordFromSentence(Component sentence, int length) {
        Component changedSentence = new Sentence();
        for (Component part : sentence.getComponents()) {
            String format = String.format(REGEX_CONSONANT, length - 1);
            Pattern pattern = Pattern.compile(format);
            Matcher matcher = pattern.matcher(part.toString());
            if (matcher.find()) {
                continue;
            }
            changedSentence.addComponent(part);
        }
        return changedSentence;
    }

    @Override
    public Component replaceWordsToSubstring(String substring, int length) throws ServiceException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        TextDao dao = daoFactory.getTextDao();
        Component text;
        try {
            text = dao.readText();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        Component changedText = new Text();
        for (Component component : text.getComponents()) {
            if (component instanceof Sentence) {
                Component changedSentence = replaceToSubstring(component, substring, length);
                changedText.addComponent(changedSentence);
                continue;
            }
            changedText.addComponent(component);
        }
        return changedText;
    }

    private Component replaceToSubstring(Component sentence, String substring, int length) {
        Component changedSentence = new Sentence();
        for (Component part : sentence.getComponents()) {
            if (part instanceof Word) {
                if (part.toString().trim().length() == length) {
                    Word newWord = new Word();
                    String[] chars = substring.split(REGEX_DELIMITER);
                    for (String symbol : chars) {
                        newWord.addComponent(new Symbol(symbol, Symbol.SymbolType.CHAR));
                    }
                    changedSentence.addComponent(newWord);
                    continue;
                }
                changedSentence.addComponent(part);
                continue;
            }
            changedSentence.addComponent(part);
        }
        return changedSentence;
    }
}
