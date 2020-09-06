package by.halatsevich.handler.entity.impl;

import java.util.List;

public class Symbol implements by.halatsevich.handler.entity.Component {
    private String symbol;
    private SymbolType type;

    public enum SymbolType {
        DIGIT, PUNCT, CHAR
    }

    public Symbol(String symbol, SymbolType type) {
        this.symbol = symbol;
        this.type = type;
    }

    public SymbolType getType() {
        return type;
    }

    @Override
    public boolean addComponent(by.halatsevich.handler.entity.Component component) {
        return false;
    }

    @Override
    public boolean removeComponent(by.halatsevich.handler.entity.Component component) {
        return false;
    }

    @Override
    public List<by.halatsevich.handler.entity.Component> getComponents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Symbol symbol1 = (Symbol) o;

        if (!symbol.equals(symbol1.symbol)) return false;
        return type == symbol1.type;
    }

    @Override
    public int hashCode() {
        int result = symbol.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }

    @Override
    public String toString() {
        if (this.type == SymbolType.PUNCT) {
            return symbol + " ";
        }
        return symbol;
    }
}
