package com.mrsalwater.kapteyn.decompiler.bytecode.util;

import com.mrsalwater.kapteyn.decompiler.classfile.attribute.annotation.Annotation;
import com.mrsalwater.kapteyn.decompiler.classfile.attribute.elementvalue.ElementValue;
import com.mrsalwater.kapteyn.decompiler.classfile.attribute.elementvalue.ElementValuePair;
import com.mrsalwater.kapteyn.decompiler.classfile.attribute.elementvalue.value.*;
import com.mrsalwater.kapteyn.decompiler.classfile.constantpool.*;
import com.mrsalwater.kapteyn.decompiler.exception.CorruptClassFileException;

public final class AnnotationUtil {

    private AnnotationUtil() {

    }

    public static String getAnnotation(ConstantPool constantPool, Annotation annotation) throws CorruptClassFileException {
        StringBuilder builder = new StringBuilder();

        String type = constantPool.getUTF8(annotation.getTypeIndex()).getValue();
        builder.append("@").append(type, 1, type.length() - 1);

        if (annotation.getElementValuePairs().length > 0) {
            builder.append("(");

            for (int i = 0, length = annotation.getElementValuePairs().length; i < length; i++) {
                ElementValuePair elementValuePair = annotation.getElementValuePairs()[i];

                String elementName = constantPool.getUTF8(elementValuePair.getElementNameIndex()).getValue();
                String elementValue = getElementValue(constantPool, elementValuePair.getValue());

                builder.append(elementName).append(" = ").append(elementValue);
                if (i + 1 != length) {
                    builder.append(", ");
                }
            }

            builder.append(")");
        }

        return builder.toString();
    }

    public static String getElementValue(ConstantPool constantPool, ElementValue elementValue) throws CorruptClassFileException {
        if (elementValue.getValue() instanceof ValueConstant) {
            ValueConstant valueConstant = (ValueConstant) elementValue.getValue();

            switch (ValueConstant.ConstantType.match(valueConstant.getTag())) {
                case BYTE:
                case CHAR:
                case INT:
                case SHORT:
                    ConstantInteger constantInteger = constantPool.getInteger(valueConstant.getConstValueIndex());
                    return String.valueOf(constantInteger.getValue());
                case BOOLEAN:
                    constantInteger = constantPool.getInteger(valueConstant.getConstValueIndex());
                    return constantInteger.getValue() == 0 ? "false" : "true";
                case DOUBLE:
                    ConstantDouble constantDouble = constantPool.getDouble(valueConstant.getConstValueIndex());
                    return String.valueOf(constantDouble.getValue());
                case FLOAT:
                    ConstantFloat constantFloat = constantPool.getFloat(valueConstant.getConstValueIndex());
                    return String.valueOf(constantFloat.getValue());
                case LONG:
                    ConstantLong constantLong = constantPool.getLong(valueConstant.getConstValueIndex());
                    return String.valueOf(constantLong.getValue());
                case STRING:
                    ConstantUTF8 constantUTF8 = constantPool.getUTF8(valueConstant.getConstValueIndex());
                    return "\"" + constantUTF8.getValue() + "\"";
            }
        } else if (elementValue.getValue() instanceof ValueEnum) {
            ValueEnum valueEnum = (ValueEnum) elementValue.getValue();

            String typeName = constantPool.getUTF8(valueEnum.getTypeNameIndex()).getValue();
            String constName = constantPool.getUTF8(valueEnum.getConstNameIndex()).getValue();

            return typeName.substring(1, typeName.length() - 1).concat(".").concat(constName);
        } else if (elementValue.getValue() instanceof ValueClass) {
            ValueClass valueClass = (ValueClass) elementValue.getValue();
            String classInfo = constantPool.getUTF8(valueClass.getClassInfoIndex()).getValue();


            return getClassInfo(classInfo).concat(".class");
        } else if (elementValue.getValue() instanceof ValueAnnotation) {
            // TODO: 31.01.2020
        } else if (elementValue.getValue() instanceof ValueArray) {
            ValueArray valueArray = (ValueArray) elementValue.getValue();
            ElementValue[] elementValues = valueArray.getValues();

            StringBuilder builder = new StringBuilder("{");

            for (int i = 0, length = elementValues.length; i < length; i++) {
                builder.append(getElementValue(constantPool, elementValues[i]));
                if (i + 1 != length) builder.append(", ");
            }

            return builder.append("}").toString();
        }

        return "";
    }

    private static String getClassInfo(String classInfo) {
        switch (classInfo) {
            case "B":
                return "byte";
            case "C":
                return "char";
            case "D":
                return "double";
            case "F":
                return "float";
            case "I":
                return "integer";
            case "J":
                return "long";
            case "S":
                return "short";
            case "Z":
                return "boolean";
            case "V":
                return "void";
        }

        return classInfo.substring(1, classInfo.length() - 1);
    }

}
