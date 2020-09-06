package by.halatsevich.handler.entity.impl;

import by.halatsevich.handler.entity.Component;

import java.util.List;

public class CodeBlock implements Component {
    private String code;

    public CodeBlock(String code) {
        this.code = code;
    }

    @Override
    public boolean addComponent(Component component) {
        return false;
    }

    @Override
    public boolean removeComponent(Component component) {
        return false;
    }

    @Override
    public List<Component> getComponents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CodeBlock codeBlock = (CodeBlock) o;

        return code.equals(codeBlock.code);
    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }

    @Override
    public String toString() {
        return code;
    }
}
