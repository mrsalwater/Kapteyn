package com.mrsalwater.kapteyn.decompiler.classfile.attribute;

public final class AttributeLocalVariableTypeTable implements Attribute {

    private final LocalVariableType[] localVariableTypeTable;

    public AttributeLocalVariableTypeTable(LocalVariableType[] localVariableTypeTable) {
        this.localVariableTypeTable = localVariableTypeTable;
    }

    public LocalVariableType[] getLocalVariableTypeTable() {
        return localVariableTypeTable;
    }

    public static final class LocalVariableType {

        private final int startPc;
        private final int length;
        private final int nameIndex;
        private final int signatureIndex;
        private final int index;

        public LocalVariableType(int startPc, int length, int nameIndex, int signatureIndex, int index) {
            this.startPc = startPc;
            this.length = length;
            this.nameIndex = nameIndex;
            this.signatureIndex = signatureIndex;
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

        public int getSignatureIndex() {
            return signatureIndex;
        }

        public int getIndex() {
            return index;
        }

    }

}
