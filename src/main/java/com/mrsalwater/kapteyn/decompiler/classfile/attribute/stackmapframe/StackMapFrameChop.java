package com.mrsalwater.kapteyn.decompiler.classfile.attribute.stackmapframe;

public final class StackMapFrameChop extends StackMapFrame {

    private final int offsetDelta;

    public StackMapFrameChop(int frameType, int offsetDelta) {
        super(frameType);
        this.offsetDelta = offsetDelta;
    }

    public int getOffsetDelta() {
        return offsetDelta;
    }

}
