package com.mrsalwater.kapteyn.decompiler.bytecode.util;

import com.mrsalwater.kapteyn.decompiler.classfile.ClassFile;
import com.mrsalwater.kapteyn.decompiler.classfile.attribute.AttributeBootstrapMethods;
import com.mrsalwater.kapteyn.decompiler.classfile.constantpool.*;
import com.mrsalwater.kapteyn.decompiler.exception.CorruptClassFileException;

import java.util.ArrayList;
import java.util.List;

public final class ConstantUtil {

    private ConstantUtil() {

    }

    public static String getConstantClassString(ConstantPool constantPool, int index) throws CorruptClassFileException {
        return constantPool.getUTF8(constantPool.getClass(index).getNameIndex()).getValue();
    }

    public static String getConstantFieldReferenceString(ConstantPool constantPool, int index) throws CorruptClassFileException {
        ConstantFieldReference fieldReference = constantPool.getFieldReference(index);

        String className = constantPool.getUTF8(constantPool.getClass(fieldReference.getClassIndex()).getNameIndex()).getValue();
        String name = constantPool.getUTF8(constantPool.getNameAndType(fieldReference.getNameAndTypeIndex()).getNameIndex()).getValue();
        String descriptor = constantPool.getUTF8(constantPool.getNameAndType(fieldReference.getNameAndTypeIndex()).getDescriptorIndex()).getValue();

        return className.concat(".").concat(name).concat(" ").concat(descriptor);
    }

    public static String getConstantInterfaceMethodReferenceString(ConstantPool constantPool, int index) throws CorruptClassFileException {
        ConstantInterfaceMethodReference interfaceMethodReference = constantPool.getInterfaceMethodReference(index);

        String className = constantPool.getUTF8(constantPool.getClass(interfaceMethodReference.getClassIndex()).getNameIndex()).getValue();
        String name = constantPool.getUTF8(constantPool.getNameAndType(interfaceMethodReference.getNameAndTypeIndex()).getNameIndex()).getValue();
        String descriptor = constantPool.getUTF8(constantPool.getNameAndType(interfaceMethodReference.getNameAndTypeIndex()).getDescriptorIndex()).getValue();

        return className.concat(".").concat(name).concat(" ").concat(descriptor);
    }

    public static List<String> getConstantInvokeDynamicString(ClassFile classFile, ConstantPool constantPool, int index) throws CorruptClassFileException {
        ArrayList<String> list = new ArrayList<>();

        ConstantInvokeDynamic constantInvokeDynamic = constantPool.getInvokeDynamic(index);
        AttributeBootstrapMethods attributeBootstrapMethods = classFile.getAttributes().get(AttributeBootstrapMethods.class);

        AttributeBootstrapMethods.BootstrapMethod bootstrapMethod = attributeBootstrapMethods.getBootstrapMethods()[constantInvokeDynamic.getBootstrapMethodAttributeIndex()];
        ConstantNameAndType constantNameAndType = constantPool.getNameAndType(constantInvokeDynamic.getNameAndTypeIndex());

        String name = constantPool.getUTF8(constantNameAndType.getNameIndex()).getValue();
        String descriptor = constantPool.getUTF8(constantNameAndType.getDescriptorIndex()).getValue();

        list.add(name.concat(" ").concat(descriptor).concat(" ["));
        list.add("   ".concat(getConstantMethodHandleString(constantPool, bootstrapMethod.getBootstrapMethodRef())));
        list.add("   arguments");

        for (int bootstrapArgument : bootstrapMethod.getBootstrapArguments()) {
            Constant constant = constantPool.get(bootstrapArgument);

            String string;
            if (constant instanceof ConstantString) {
                string = "\"" + constantPool.getUTF8(((ConstantString) constant).getStringIndex()).getValue() + "\"";
            } else if (constant instanceof ConstantClass) {
                string = constantPool.getUTF8(((ConstantClass) constant).getNameIndex()).getValue();
            } else if (constant instanceof ConstantInteger) {
                string = String.valueOf(((ConstantInteger) constant).getValue());
            } else if (constant instanceof ConstantLong) {
                string = String.valueOf(((ConstantLong) constant).getValue());
            } else if (constant instanceof ConstantFloat) {
                string = String.valueOf(((ConstantFloat) constant).getValue());
            } else if (constant instanceof ConstantDouble) {
                string = String.valueOf(((ConstantDouble) constant).getValue());
            } else if (constant instanceof ConstantMethodHandle) {
                string = getConstantMethodHandleString(constantPool, bootstrapArgument);
            } else if (constant instanceof ConstantMethodType) {
                string = constantPool.getUTF8(((ConstantMethodType) constant).getDescriptorIndex()).getValue();
            } else {
                string = "*error*"; // TODO: 20.01.2020 ?
            }

            list.add("   ".concat(string));
        }

        list.add("]");
        return list;
    }

    public static String getConstantMethodHandleString(ConstantPool constantPool, int index) throws CorruptClassFileException {
        ConstantMethodHandle methodHandle = constantPool.getMethodHandle(index);

        StringBuilder builder = new StringBuilder();
        switch (ConstantMethodHandle.ReferenceKind.match(methodHandle.getReferenceKind())) {
            case GETFIELD:
            case GETSTATIC:
            case PUTFIELD:
            case PUTSTATIC: {
                builder.append(getConstantFieldReferenceString(constantPool, methodHandle.getReferenceIndex()));
                break;
            }
            case INVOKEVIRTUAL:
            case INVOKESTATIC:
            case INVOKESPECIAL:
            case NEWINVOKESPECIAL:
            case INVOKEINTERFACE:
                Constant constant = constantPool.get(methodHandle.getReferenceIndex());

                if (constant instanceof ConstantMethodReference) {
                    builder.append(getConstantMethodReferenceString(constantPool, methodHandle.getReferenceIndex()));
                } else if (constant instanceof ConstantInterfaceMethodReference) {
                    builder.append(getConstantInterfaceMethodReferenceString(constantPool, methodHandle.getReferenceIndex()));
                }
                break;
            default:
                builder.append("*error*"); // TODO: 20.01.2020 ?
                break;
        }

        return builder.toString();
    }

    public static String getConstantMethodReferenceString(ConstantPool constantPool, int index) throws CorruptClassFileException {
        ConstantMethodReference methodReference = constantPool.getMethodReference(index);

        String className = constantPool.getUTF8(constantPool.getClass(methodReference.getClassIndex()).getNameIndex()).getValue();
        String name = constantPool.getUTF8(constantPool.getNameAndType(methodReference.getNameAndTypeIndex()).getNameIndex()).getValue();
        String descriptor = constantPool.getUTF8(constantPool.getNameAndType(methodReference.getNameAndTypeIndex()).getDescriptorIndex()).getValue();

        return className.concat(".").concat(name).concat(" ").concat(descriptor);
    }

    public static String getConstantMethodTypeString(ConstantPool constantPool, int index) throws CorruptClassFileException {
        ConstantMethodType methodType = constantPool.getMethodType(index);
        return constantPool.getUTF8(methodType.getDescriptorIndex()).getValue();
    }

    public static String getConstantNameAndTypeString(ConstantPool constantPool, int index) throws CorruptClassFileException {
        ConstantNameAndType nameAndType = constantPool.getNameAndType(index);

        String name = constantPool.getUTF8(nameAndType.getNameIndex()).getValue();
        String descriptor = constantPool.getUTF8(nameAndType.getDescriptorIndex()).getValue();

        return name.concat(" ").concat(descriptor);
    }

}
