package by.halatsevich.handler.dao.impl;

import by.halatsevich.handler.dao.TextDao;
import by.halatsevich.handler.dao.exception.DaoException;
import by.halatsevich.handler.dao.parser.AbstractParser;
import by.halatsevich.handler.dao.parser.impl.TextParser;
import by.halatsevich.handler.dao.reader.TextReader;
import by.halatsevich.handler.entity.Component;

public class TextDaoImpl implements TextDao {
    @Override
    public Component readText() throws DaoException {
        TextReader reader = new TextReader();
        AbstractParser parser = new TextParser();
        String text = reader.readText();
        Component parsedText = parser.parseComponent(text);
        return parsedText;
    }
}
