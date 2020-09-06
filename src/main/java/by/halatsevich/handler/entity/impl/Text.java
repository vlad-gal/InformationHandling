package by.halatsevich.handler.entity.impl;

import by.halatsevich.handler.entity.Component;

import java.util.ArrayList;
import java.util.List;

public class Text implements Component {
    private List<Component> text;

    public Text() {
        this.text = new ArrayList<>();
    }

    @Override
    public boolean addComponent(Component component) {
        return component != null && text.add(component);
    }

    @Override
    public boolean removeComponent(Component component) {
        return component != null && text.remove(component);
    }

    @Override
    public List<Component> getComponents() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Text text1 = (Text) o;

        return text.equals(text1.text);
    }

    @Override
    public int hashCode() {
        return text.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Component component : text) {
            builder.append(component);
        }
        builder.append("\n");
        return builder.toString();
    }
}
