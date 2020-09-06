package by.halatsevich.handler.service;

import by.halatsevich.handler.entity.Component;
import by.halatsevich.handler.service.exception.ServiceException;

import java.util.Map;

public interface TextService {
    //2
    Map<Component, Integer> showAscendingOrderByCountWordsInSentences() throws ServiceException;

    //5
    Component replaceFirstAndLastWordInSentence() throws ServiceException;

    //7
    Map<Component, Double> showRelationBetweenVowelAndAllLettersInWord() throws ServiceException;

    //12
    Component deleteAllWordsWithDefineLengthStartWithConsonant(int length) throws ServiceException;

    //16
    Component replaceWordsToSubstring(String substring, int length) throws ServiceException;
}

