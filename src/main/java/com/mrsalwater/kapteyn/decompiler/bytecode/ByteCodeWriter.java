package com.mrsalwater.kapteyn.decompiler.bytecode;

import java.util.ArrayList;
import java.util.List;

public final class ByteCodeWriter {

    private final StringBuilder builder = new StringBuilder();
    private final List<String> lines = new ArrayList<>();

    public ByteCodeWriter append(String string) {
        builder.append(string);
        return this;
    }

    public ByteCodeWriter append(int i) {
        builder.append(i);
        return this;
    }

    public ByteCodeWriter tab() {
        builder.append("   ");
        return this;
    }

    public ByteCodeWriter indent() {
        lines.add(builder.toString());
        builder.setLength(0);
        return this;
    }

    public void finish() {
        lines.add(builder.toString());
    }

    public void reset() {
        lines.clear();
        builder.setLength(0);
    }

    public List<String> getLines() {
        return lines;
    }

    public String getSource() {
        StringBuilder builder = new StringBuilder();

        for (String line : lines) {
            builder.append(line).append('\n');
        }

        return builder.toString();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (String line : lines) {
            builder.append(line);
        }

        return builder.toString();
    }

}
