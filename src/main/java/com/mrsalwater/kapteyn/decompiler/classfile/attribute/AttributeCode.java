package com.mrsalwater.kapteyn.decompiler.classfile.attribute;

public final class AttributeCode implements Attribute {

    private final int maxStack;
    private final int maxLocals;
    private final int[] code;
    private final CodeException[] codeExceptions;
    private final Attributes attributes;

    public AttributeCode(int maxStack, int maxLocals, int[] code, CodeException[] codeExceptions, Attributes attributes) {
        this.maxStack = maxStack;
        this.maxLocals = maxLocals;
        this.code = code;
        this.codeExceptions = codeExceptions;
        this.attributes = attributes;
    }

    public int getMaxStack() {
        return maxStack;
    }

    public int getMaxLocals() {
        return maxLocals;
    }

    public int[] getCode() {
        return code;
    }

    public CodeException[] getCodeExceptions() {
        return codeExceptions;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public static final class CodeException {

        private final int startPc;
        private final int endPc;
        private final int handlerPc;
        private final int catchType;

        public CodeException(int startPc, int endPc, int handlerPc, int catchType) {
            this.startPc = startPc;
            this.endPc = endPc;
            this.handlerPc = handlerPc;
            this.catchType = catchType;
        }

        public int getStartPc() {
            return startPc;
        }

        public int getEndPc() {
            return endPc;
        }

        public int getHandlerPc() {
            return handlerPc;
        }

        public int getCatchType() {
            return catchType;
        }

    }

}
