package by.halatsevich.handler.entity;

import java.util.List;

public interface Component {
    boolean addComponent(Component component);

    boolean removeComponent(Component component);

    List<Component> getComponents();

    String toString();
}
