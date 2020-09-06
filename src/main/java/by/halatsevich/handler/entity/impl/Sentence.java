package by.halatsevich.handler.entity.impl;

import by.halatsevich.handler.entity.Component;

import java.util.ArrayList;
import java.util.List;

public class Sentence implements Component {
    private List<Component> sentence;

    public Sentence() {
        this.sentence = new ArrayList<>();
    }

    @Override
    public boolean addComponent(Component component) {
        return component != null && sentence.add(component);
    }

    @Override
    public boolean removeComponent(Component component) {
        return component != null && sentence.remove(component);
    }

    @Override
    public List<Component> getComponents() {
        return sentence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sentence sentence1 = (Sentence) o;

        return sentence.equals(sentence1.sentence);
    }

    @Override
    public int hashCode() {
        return sentence.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Component component : sentence) {
            if (component.getClass() == Symbol.class) {
                Symbol symbol = (Symbol) component;
                if (symbol.getType() == Symbol.SymbolType.PUNCT) {
                    builder.deleteCharAt(builder.length() - 1);
                    builder.append(symbol);
                    continue;
                }
            }
            builder.append(component);
        }
        return builder.toString();
    }
}
