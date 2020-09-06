package by.halatsevich.handler.dao;

import by.halatsevich.handler.dao.impl.TextDaoImpl;

public class DaoFactory {
    private static final DaoFactory instance = new DaoFactory();

    private final TextDao textDao = new TextDaoImpl();

    private DaoFactory() {
    }

    public TextDao getTextDao() {
        return textDao;
    }

    public static DaoFactory getInstance() {
        return instance;
    }
}
