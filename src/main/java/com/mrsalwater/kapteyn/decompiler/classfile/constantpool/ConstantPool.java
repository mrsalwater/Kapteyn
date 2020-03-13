package com.mrsalwater.kapteyn.decompiler.classfile.constantpool;

import com.mrsalwater.kapteyn.decompiler.exception.CorruptClassFileException;

public final class ConstantPool {

    private final Constant[] constants;

    public ConstantPool(Constant[] constants) {
        this.constants = constants;
    }

    public Constant[] getConstants() {
        return constants;
    }

    public ConstantClass getClass(int index) throws CorruptClassFileException {
        return get(index, ConstantClass.class);
    }

    public ConstantFieldReference getFieldReference(int index) throws CorruptClassFileException {
        return get(index, ConstantFieldReference.class);
    }

    public ConstantMethodReference getMethodReference(int index) throws CorruptClassFileException {
        return get(index, ConstantMethodReference.class);
    }

    public ConstantInterfaceMethodReference getInterfaceMethodReference(int index) throws CorruptClassFileException {
        return get(index, ConstantInterfaceMethodReference.class);
    }

    public ConstantString getString(int index) throws CorruptClassFileException {
        return get(index, ConstantString.class);
    }

    public ConstantInteger getInteger(int index) throws CorruptClassFileException {
        return get(index, ConstantInteger.class);
    }

    public ConstantFloat getFloat(int index) throws CorruptClassFileException {
        return get(index, ConstantFloat.class);
    }

    public ConstantLong getLong(int index) throws CorruptClassFileException {
        return get(index, ConstantLong.class);
    }

    public ConstantDouble getDouble(int index) throws CorruptClassFileException {
        return get(index, ConstantDouble.class);
    }

    public ConstantNameAndType getNameAndType(int index) throws CorruptClassFileException {
        return get(index, ConstantNameAndType.class);
    }

    public ConstantUTF8 getUTF8(int index) throws CorruptClassFileException {
        return get(index, ConstantUTF8.class);
    }

    public ConstantMethodHandle getMethodHandle(int index) throws CorruptClassFileException {
        return get(index, ConstantMethodHandle.class);
    }

    public ConstantMethodType getMethodType(int index) throws CorruptClassFileException {
        return get(index, ConstantMethodType.class);
    }

    public ConstantInvokeDynamic getInvokeDynamic(int index) throws CorruptClassFileException {
        return get(index, ConstantInvokeDynamic.class);
    }

    public Constant get(int index) throws CorruptClassFileException {
        if (index < 0 || index >= constants.length) {
            throw new CorruptClassFileException("Invalid constant pool entry index");
        }

        return constants[index];
    }

    @SuppressWarnings("unchecked")
    private <T extends Constant> T get(int index, Class<T> constantType) throws CorruptClassFileException {
        if (index < 0 || index >= constants.length) {
            throw new CorruptClassFileException("Invalid constant pool entry index");
        }

        Constant constant = constants[index];
        if (constant.getClass() != constantType) {
            throw new CorruptClassFileException("Invalid constant pool entry type");
        }

        return (T) constant;
    }

}
