package by.halatsevich.handler.entity.impl;

import by.halatsevich.handler.entity.Component;

import java.util.ArrayList;
import java.util.List;

public class Word implements Component {
    private List<Component> word;

    public Word() {
        this.word = new ArrayList<>();
    }

    @Override
    public boolean addComponent(Component component) {
        return component != null && word.add(component);
    }

    @Override
    public boolean removeComponent(Component component) {
        return component != null && word.remove(component);
    }

    @Override
    public List<Component> getComponents() {
        return word;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Word word1 = (Word) o;

        return word.equals(word1.word);
    }

    @Override
    public int hashCode() {
        return word.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Component component : word) {
            builder.append(component);
        }
        builder.append(" ");
        return builder.toString();
    }
}
