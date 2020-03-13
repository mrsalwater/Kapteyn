package com.mrsalwater.kapteyn.decompiler.classfile.attribute;

public final class AttributeLineNumberTable implements Attribute {

    private final LineNumber[] lineNumberTable;

    public AttributeLineNumberTable(LineNumber[] lineNumberTable) {
        this.lineNumberTable = lineNumberTable;
    }

    public LineNumber[] getLineNumberTable() {
        return lineNumberTable;
    }

    public static final class LineNumber {

        private final int startPc;
        private final int lineNumber;

        public LineNumber(int startPc, int lineNumber) {
            this.startPc = startPc;
            this.lineNumber = lineNumber;
        }

        public int getStartPc() {
            return startPc;
        }

        public int getLineNumber() {
            return lineNumber;
        }

    }

}
