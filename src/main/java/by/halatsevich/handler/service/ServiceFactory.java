package by.halatsevich.handler.service;

import by.halatsevich.handler.service.impl.TextServiceImpl;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private final TextService textService = new TextServiceImpl();

    private ServiceFactory() {
    }

    public TextService getTextService() {
        return textService;
    }

    public static ServiceFactory getInstance() {
        return instance;
    }
}
