package com.mrsalwater.kapteyn.decompiler.classfile;

import com.mrsalwater.kapteyn.decompiler.exception.CorruptClassFileException;

import java.util.Arrays;

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

    public int readU4() {
        return ((data[offset++] & 0xff) << 24) | ((data[offset++] & 0xff) << 16) | ((data[offset++] & 0xff) << 8) | (data[offset++] & 0xff);
    }

    public String readUTF8() throws CorruptClassFileException {
        int length = readU2();
        byte[] bytes = Arrays.copyOfRange(data, offset, offset + length);

        String string = decodeUTF8(length, bytes);
        offset += length;

        return string;
    }

    private String decodeUTF8(int length, byte[] bytes) throws CorruptClassFileException {
        char[] value = new char[length];
        int count = 0;

        int index = 0;
        int character, character2, character3;

        while (index < length) {
            character = bytes[index] & 0xff;
            if (character > 127) break;

            index++;
            value[count++] = (char) character;
        }

        while (index < length) {
            character = (int) bytes[index] & 0xff;

            switch (character >> 4) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    index++;
                    value[count++] = (char) character;
                    break;
                case 12:
                case 13:
                    index += 2;
                    if (index > length) {
                        throw new CorruptClassFileException("Invalid UTF-8 string");
                    }

                    character2 = bytes[index - 1];
                    if ((character2 & 0xC0) != 0x80) {
                        throw new CorruptClassFileException("Invalid UTF-8 string");
                    }

                    value[count++] = (char) (((character & 0x1F) << 6) | (character2 & 0x3F));
                    break;
                case 14:
                    index += 3;
                    if (index > length) {
                        throw new CorruptClassFileException("Invalid UTF-8 string");
                    }

                    character2 = bytes[index - 2];
                    character3 = bytes[index - 1];

                    if (((character2 & 0xC0) != 0x80) || ((character3 & 0xC0) != 0x80)) {
                        throw new CorruptClassFileException("Invalid UTF-8 string");
                    }

                    value[count++] = (char) (((character & 0x0F) << 12) | ((character2 & 0x3F) << 6) | ((character3 & 0x3F)));
                    break;
                default:
                    throw new CorruptClassFileException("Invalid UTF-8 string");
            }
        }

        return new String(value, 0, count);
    }

}
