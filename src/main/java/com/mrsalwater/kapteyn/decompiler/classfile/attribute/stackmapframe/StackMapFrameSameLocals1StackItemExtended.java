package com.mrsalwater.kapteyn.decompiler.classfile.attribute.stackmapframe;

import com.mrsalwater.kapteyn.decompiler.classfile.attribute.stackmapframe.verificationtype.VerificationTypeInfo;

public final class StackMapFrameSameLocals1StackItemExtended extends StackMapFrame {

    private final int offsetDelta;
    private final VerificationTypeInfo stack;

    public StackMapFrameSameLocals1StackItemExtended(int frameType, int offsetDelta, VerificationTypeInfo stack) {
        super(frameType);
        this.offsetDelta = offsetDelta;
        this.stack = stack;
    }

    public int getOffsetDelta() {
        return offsetDelta;
    }

    public VerificationTypeInfo getStack() {
        return stack;
    }

}
