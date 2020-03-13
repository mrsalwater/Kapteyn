package com.mrsalwater.kapteyn.decompiler.classfile.attribute;

public final class AttributeBootstrapMethods implements Attribute {

    private final BootstrapMethod[] bootstrapMethods;

    public AttributeBootstrapMethods(BootstrapMethod[] bootstrapMethods) {
        this.bootstrapMethods = bootstrapMethods;
    }

    public BootstrapMethod[] getBootstrapMethods() {
        return bootstrapMethods;
    }

    public static final class BootstrapMethod {

        private final int bootstrapMethodRef;
        private final int[] bootstrapArguments;

        public BootstrapMethod(int bootstrapMethodRef, int[] bootstrapArguments) {
            this.bootstrapMethodRef = bootstrapMethodRef;
            this.bootstrapArguments = bootstrapArguments;
        }

        public int getBootstrapMethodRef() {
            return bootstrapMethodRef;
        }

        public int[] getBootstrapArguments() {
            return bootstrapArguments;
        }

    }

}
