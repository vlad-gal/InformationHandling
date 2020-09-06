package by.halatsevich.handler.dao;

import by.halatsevich.handler.dao.exception.DaoException;
import by.halatsevich.handler.entity.Component;

public interface TextDao {
    Component readText() throws DaoException;
}
