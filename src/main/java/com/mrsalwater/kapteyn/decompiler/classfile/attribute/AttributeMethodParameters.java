package com.mrsalwater.kapteyn.decompiler.classfile.attribute;

public final class AttributeMethodParameters implements Attribute {

    private final Parameter[] parameters;

    public AttributeMethodParameters(Parameter[] parameters) {
        this.parameters = parameters;
    }

    public Parameter[] getParameters() {
        return parameters;
    }

    public static final class Parameter {

        private final int nameIndex;
        private final int accessFlags;

        public Parameter(int nameIndex, int accessFlags) {
            this.nameIndex = nameIndex;
            this.accessFlags = accessFlags;
        }

        public int getNameIndex() {
            return nameIndex;
        }

        public int getAccessFlags() {
            return accessFlags;
        }

    }

}
