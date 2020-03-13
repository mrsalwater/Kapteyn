package com.mrsalwater.kapteyn.decompiler.classfile.attribute;

public final class AttributeInnerClasses implements Attribute {

    private final Class[] classes;

    public AttributeInnerClasses(Class[] classes) {
        this.classes = classes;
    }

    public Class[] getClasses() {
        return classes;
    }

    public static final class Class {

        private final int innerClassInfoIndex;
        private final int outerClassInfoIndex;
        private final int innerNameIndex;
        private final int innerClassAccessFlags;

        public Class(int innerClassInfoIndex, int outerClassInfoIndex, int innerNameIndex, int innerClassAccessFlags) {
            this.innerClassInfoIndex = innerClassInfoIndex;
            this.outerClassInfoIndex = outerClassInfoIndex;
            this.innerNameIndex = innerNameIndex;
            this.innerClassAccessFlags = innerClassAccessFlags;
        }

        public int getInnerClassInfoIndex() {
            return innerClassInfoIndex;
        }

        public int getOuterClassInfoIndex() {
            return outerClassInfoIndex;
        }

        public int getInnerNameIndex() {
            return innerNameIndex;
        }

        public int getInnerClassAccessFlags() {
            return innerClassAccessFlags;
        }

    }

}
