package com.mrsalwater.kapteyn.decompiler.bytecode.util;

import com.mrsalwater.kapteyn.decompiler.classfile.attribute.*;
import com.mrsalwater.kapteyn.decompiler.classfile.attribute.annotation.Annotation;
import com.mrsalwater.kapteyn.decompiler.classfile.constantpool.ConstantNameAndType;
import com.mrsalwater.kapteyn.decompiler.classfile.constantpool.ConstantPool;
import com.mrsalwater.kapteyn.decompiler.classfile.field.Field;
import com.mrsalwater.kapteyn.decompiler.exception.CorruptClassFileException;

import java.util.ArrayList;
import java.util.List;

public final class AttributeUtil {

    private AttributeUtil() {

    }

    public static String getAttributeSourceFile(ConstantPool constantPool, AttributeSourceFile attributeSourceFile) throws CorruptClassFileException {
        String sourceFileName = constantPool.getUTF8(attributeSourceFile.getSourceFileIndex()).getValue();
        return "// source file: ".concat(sourceFileName);
    }

    public static String getAttributeDebugExtension(AttributeDebugExtension attributeDebugExtension) {
        int[] debugExtension = attributeDebugExtension.getDebugExtension();
        String debugInformation = new String(debugExtension, 0, debugExtension.length); // TODO: 18.01.2020 Check if the string is correctly parsed

        return "// debug information: ".concat(debugInformation);
    }

    public static String getAttributeSynthetic() {
        return "// marked *synthetic*";
    }

    public static String getAttributeDeprecated() {
        return "// marked *deprecated*";
    }

    public static String getAttributeSignature(ConstantPool constantPool, AttributeSignature attributeSignature) throws CorruptClassFileException {
        String signature = constantPool.getUTF8(attributeSignature.getSignatureIndex()).getValue();
        return "// signature: ".concat(signature);
    }

    public static String getAttributeAnnotationDefault(ConstantPool constantPool, AttributeAnnotationDefault attributeAnnotationDefault) throws CorruptClassFileException {
        String elementValue = AnnotationUtil.getElementValue(constantPool, attributeAnnotationDefault.getDefaultValue());
        return "// default: ".concat(elementValue);
    }

    public static List<String> getAttributeRuntimeVisibleAnnotations(ConstantPool constantPool, AttributeRuntimeVisibleAnnotations attributeRuntimeVisibleAnnotations) throws CorruptClassFileException {
        List<String> annotations = new ArrayList<>();

        for (Annotation annotation : attributeRuntimeVisibleAnnotations.getAnnotations()) {
            annotations.add(AnnotationUtil.getAnnotation(constantPool, annotation));
        }

        return annotations;
    }

    public static List<String> getAttributeRuntimeInvisibleAnnotations(ConstantPool constantPool, AttributeRuntimeInvisibleAnnotations attributeRuntimeInvisibleAnnotations) throws CorruptClassFileException {
        List<String> annotations = new ArrayList<>();

        for (Annotation annotation : attributeRuntimeInvisibleAnnotations.getAnnotations()) {
            annotations.add(AnnotationUtil.getAnnotation(constantPool, annotation));
        }

        return annotations;
    }

    public static String getAttributeEnclosingMethods(ConstantPool constantPool, AttributeEnclosingMethod attributeEnclosingMethod) throws CorruptClassFileException {
        StringBuilder builder = new StringBuilder();

        String innermostClass = constantPool.getUTF8(constantPool.getClass(attributeEnclosingMethod.getClassIndex()).getNameIndex()).getValue();
        builder.append("enclosingmethod ").append(innermostClass);

        if (attributeEnclosingMethod.getMethodIndex() != 0) {
            ConstantNameAndType constantNameAndType = constantPool.getNameAndType(attributeEnclosingMethod.getMethodIndex());

            String name = constantPool.getUTF8(constantNameAndType.getNameIndex()).getValue();
            String descriptor = constantPool.getUTF8(constantNameAndType.getDescriptorIndex()).getValue();

            builder.append(".").append(name).append(" ").append(descriptor);
        }

        return builder.toString();
    }

    public static String getAttributeConstantValue(ConstantPool constantPool, Field field, AttributeConstantValue attributeConstantValue) throws CorruptClassFileException {
        String descriptor = constantPool.getUTF8(field.getDescriptorIndex()).getValue();

        if (descriptor.length() == 1) {
            switch (descriptor.charAt(0)) {
                case 'I':
                case 'S':
                case 'C':
                case 'B':
                case 'Z':
                    return "// constant value: ".concat(String.valueOf(constantPool.getInteger(attributeConstantValue.getConstantValueIndex()).getValue()));
                case 'D':
                    return "// constant value: ".concat(String.valueOf(constantPool.getDouble(attributeConstantValue.getConstantValueIndex()).getValue()));
                case 'F':
                    return "// constant value: ".concat(String.valueOf(constantPool.getFloat(attributeConstantValue.getConstantValueIndex()).getValue()));
                case 'J':
                    return "// constant value: ".concat(String.valueOf(constantPool.getLong(attributeConstantValue.getConstantValueIndex()).getValue()));
            }
        } else if (descriptor.equals("Ljava/lang/String;")) {
            return "// constant value: ".concat(constantPool.getUTF8(constantPool.getString(attributeConstantValue.getConstantValueIndex()).getStringIndex()).getValue());
        }

        throw new NullPointerException("Invalid primitive type");
    }

}
