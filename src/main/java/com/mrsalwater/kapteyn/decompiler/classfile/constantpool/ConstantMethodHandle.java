package com.mrsalwater.kapteyn.decompiler.classfile.constantpool;

public final class ConstantMethodHandle implements Constant {

    private final int referenceKind;
    private final int referenceIndex;

    public ConstantMethodHandle(int referenceKind, int referenceIndex) {
        this.referenceKind = referenceKind;
        this.referenceIndex = referenceIndex;
    }

    public int getReferenceKind() {
        return referenceKind;
    }

    public int getReferenceIndex() {
        return referenceIndex;
    }

    public enum ReferenceKind {

        GETFIELD(1),
        GETSTATIC(2),
        PUTFIELD(3),
        PUTSTATIC(4),
        INVOKEVIRTUAL(5),
        INVOKESTATIC(6),
        INVOKESPECIAL(7),
        NEWINVOKESPECIAL(8),
        INVOKEINTERFACE(9);

        private final int kind;

        ReferenceKind(int kind) {
            this.kind = kind;
        }

        public int getKind() {
            return kind;
        }

        public static ReferenceKind match(int kind) {
            for (ReferenceKind referenceKind : values()) {
                if (referenceKind.kind == kind) {
                    return referenceKind;
                }
            }

            throw new NullPointerException();
        }

    }

}
