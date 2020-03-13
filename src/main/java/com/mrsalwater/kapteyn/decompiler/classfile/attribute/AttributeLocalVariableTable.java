package com.mrsalwater.kapteyn.decompiler.classfile.attribute;

public final class AttributeLocalVariableTable implements Attribute {

    private final LocalVariable[] localVariableTable;

    public AttributeLocalVariableTable(LocalVariable[] localVariableTable) {
        this.localVariableTable = localVariableTable;
    }

    public LocalVariable[] getLocalVariableTable() {
        return localVariableTable;
    }

    public static final class LocalVariable {

        private final int startPc;
        private final int length;
        private final int nameIndex;
        private final int descriptorIndex;
        private final int index;

        public LocalVariable(int startPc, int length, int nameIndex, int descriptorIndex, int index) {
            this.startPc = startPc;
            this.length = length;
            this.nameIndex = nameIndex;
            this.descriptorIndex = descriptorIndex;
            this.index = index;
        }

        public int getStartPc() {
            return startPc;
        }

        public int getLength() {
            return length;
        }

        public int getNameIndex() {
            return nameIndex;
        }

        public int getDescriptorIndex() {
            return descriptorIndex;
        }

        public int getIndex() {
            return index;
        }

    }

}
