package com.mrsalwater.kapteyn.decompiler.classfile.attribute.elementvalue.value;

public final class ValueConstant implements Value {

    private final int tag;
    private final int constValueIndex;

    public ValueConstant(int tag, int constValueIndex) {
        this.tag = tag;
        this.constValueIndex = constValueIndex;
    }

    public int getTag() {
        return tag;
    }

    public int getConstValueIndex() {
        return constValueIndex;
    }

    public enum ConstantType {

        BYTE('B'),
        CHAR('C'),
        DOUBLE('D'),
        FLOAT('F'),
        INT('I'),
        LONG('J'),
        SHORT('S'),
        BOOLEAN('Z'),
        STRING('s');

        private final char tag;

        ConstantType(char tag) {
            this.tag = tag;
        }

        public int getTag() {
            return tag;
        }

        public static ConstantType match(int tag) {
            for (ConstantType constantType : values()) {
                if (constantType.tag == tag) {
                    return constantType;
                }
            }

            throw new NullPointerException();
        }

    }

}
