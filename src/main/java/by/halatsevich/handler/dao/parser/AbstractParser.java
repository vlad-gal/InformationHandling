package by.halatsevich.handler.dao.parser;

import by.halatsevich.handler.entity.Component;

public interface AbstractParser {
    Component parseComponent(String component);
}
