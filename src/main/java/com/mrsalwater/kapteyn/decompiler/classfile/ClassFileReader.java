package com.mrsalwater.kapteyn.decompiler.classfile;

import java.nio.charset.StandardCharsets;

public final class ClassFileReader {

    private final byte[] data;
    private int offset;

    public ClassFileReader(byte[] data) {
        this.data = data;
    }

    public int readU1() {
        return (data[offset++] & 0xff);
    }

    public int readU2() {
        return ((data[offset++] & 0xff) << 8) | (data[offset++] & 0xff);
    }

    public final int readU4() {
        return ((data[offset++] & 0xff) << 24) | ((data[offset++] & 0xff) << 16) | ((data[offset++] & 0xff) << 8) | (data[offset++] & 0xff);
    }

    public final String readUTF8() {
        int length = readU2();
        String string = new String(data, offset, length, StandardCharsets.UTF_8);
        offset += length;
        return string;
    }

}
