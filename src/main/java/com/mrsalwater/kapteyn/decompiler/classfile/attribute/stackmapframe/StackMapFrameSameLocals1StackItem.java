package com.mrsalwater.kapteyn.decompiler.classfile.attribute.stackmapframe;

import com.mrsalwater.kapteyn.decompiler.classfile.attribute.stackmapframe.verificationtype.VerificationTypeInfo;

public final class StackMapFrameSameLocals1StackItem extends StackMapFrame {

    private final VerificationTypeInfo stack;

    public StackMapFrameSameLocals1StackItem(int frameType, VerificationTypeInfo stack) {
        super(frameType);
        this.stack = stack;
    }

    public VerificationTypeInfo getStack() {
        return stack;
    }

}
