package com.mrsalwater.kapteyn.decompiler.bytecode.visitor;

import com.mrsalwater.kapteyn.decompiler.bytecode.settings.ByteCodeSettings;
import com.mrsalwater.kapteyn.decompiler.bytecode.util.ConstantUtil;
import com.mrsalwater.kapteyn.decompiler.classfile.ClassFile;
import com.mrsalwater.kapteyn.decompiler.classfile.attribute.AttributeCode;
import com.mrsalwater.kapteyn.decompiler.classfile.attribute.AttributeLineNumberTable;
import com.mrsalwater.kapteyn.decompiler.classfile.attribute.AttributeLocalVariableTable;
import com.mrsalwater.kapteyn.decompiler.classfile.attribute.Attributes;
import com.mrsalwater.kapteyn.decompiler.classfile.constantpool.*;
import com.mrsalwater.kapteyn.decompiler.exception.ClassFileException;
import com.mrsalwater.kapteyn.decompiler.exception.CorruptClassFileException;
import com.mrsalwater.kapteyn.decompiler.instruction.Instruction;
import com.mrsalwater.kapteyn.decompiler.instruction.OperandType;
import com.mrsalwater.kapteyn.decompiler.util.ClassFileUtil;

import java.util.List;

public final class CodeVisitor extends Visitor<AttributeCode> {

    private int[] code;
    private int i;

    private boolean hasLineNumberTable;
    private int lineNumberIndex;
    private AttributeLineNumberTable.LineNumber[] lineNumbers;

    private boolean hasLocalVariableTable;
    private AttributeLocalVariableTable.LocalVariable[] localVariableTable;

    public CodeVisitor(ClassFile classFile, ByteCodeSettings settings) {
        super(classFile, settings);
    }

    @Override
    public void visit(AttributeCode attributeCode) throws ClassFileException {
        Attributes attributes = attributeCode.getAttributes();

        if (attributes.has(AttributeLineNumberTable.class)) {
            initializeLineNumberTable(attributes.get(AttributeLineNumberTable.class));
        }

        if (attributes.has(AttributeLocalVariableTable.class)) {
            initializeLocalVariableTable(attributes.get(AttributeLocalVariableTable.class));
        }

        code = attributeCode.getCode();
        for (i = 0; i < code.length; i++) {
            if (hasLineNumberTable) {
                visitLineNumberTable();
            }

            Instruction instruction = Instruction.match(code[i]);
            writer.tab().append(i).append(": ").append(instruction.getName()).append(" ");

            if (instruction.getOperandType() == OperandType.SWITCH) {
                visitSwitchInstruction(instruction);
            }

            visitInstructionInformation(instruction);
            i += instruction.getOperandType().getLength();

            writer.indent();
        }

        if (hasLocalVariableTable) {
            visitLocalVariableTable();
        }

        writer.append("max stack ").append(attributeCode.getMaxStack()).indent();
        writer.append("max locals ").append(attributeCode.getMaxLocals());

        writer.finish();
    }

    private void initializeLineNumberTable(AttributeLineNumberTable attributeLineNumberTable) {
        hasLineNumberTable = true;
        lineNumbers = attributeLineNumberTable.getLineNumberTable();
    }

    private void initializeLocalVariableTable(AttributeLocalVariableTable attributeLocalVariableTable) {
        hasLocalVariableTable = true;
        localVariableTable = attributeLocalVariableTable.getLocalVariableTable();
    }

    private void visitLineNumberTable() {
        if (lineNumberIndex < lineNumbers.length) {
            AttributeLineNumberTable.LineNumber lineNumber = lineNumbers[lineNumberIndex];

            if (lineNumber.getStartPc() == i) {
                writer.append("line number " + lineNumber.getLineNumber()).indent();
                lineNumberIndex++;
            }
        }
    }

    private void visitLocalVariableTable() throws CorruptClassFileException {
        writer.append("local variables").indent();

        for (AttributeLocalVariableTable.LocalVariable localVariable : localVariableTable) {
            String localVariableName = constantPool.getUTF8(localVariable.getNameIndex()).getValue();
            String localVariableDescriptor = constantPool.getUTF8(localVariable.getDescriptorIndex()).getValue();

            writer.tab().append(localVariableName).append(" ").append(localVariableDescriptor).append(" ");
            writer.append(localVariable.getIndex()).append(" ").append(localVariable.getStartPc()).append("-").append(localVariable.getStartPc() + localVariable.getLength());
            writer.indent();
        }
    }

    private void visitInstructionInformation(Instruction instruction) throws ClassFileException {
        if (instruction == Instruction.ILOAD_0 || instruction == Instruction.LLOAD_0 || instruction == Instruction.FLOAD_0 || instruction == Instruction.DLOAD_0 || instruction == Instruction.ALOAD_0
                || instruction == Instruction.ISTORE_0 || instruction == Instruction.LSTORE_0 || instruction == Instruction.FSTORE_0 || instruction == Instruction.DSTORE_0 || instruction == Instruction.ASTORE_0) {
            if (hasLocalVariableTable && localVariableTable.length >= 1) {
                AttributeLocalVariableTable.LocalVariable localVariable = localVariableTable[0];
                String name = constantPool.getUTF8(localVariable.getNameIndex()).getValue();

                writer.append("/* ").append(name).append(" */");
            }
        } else if (instruction == Instruction.ILOAD_1 || instruction == Instruction.LLOAD_1 || instruction == Instruction.FLOAD_1 || instruction == Instruction.DLOAD_1 || instruction == Instruction.ALOAD_1
                || instruction == Instruction.ISTORE_1 || instruction == Instruction.LSTORE_1 || instruction == Instruction.FSTORE_1 || instruction == Instruction.DSTORE_1 || instruction == Instruction.ASTORE_1) {
            if (hasLocalVariableTable && localVariableTable.length >= 2) {
                AttributeLocalVariableTable.LocalVariable localVariable = localVariableTable[1];
                String name = constantPool.getUTF8(localVariable.getNameIndex()).getValue();

                writer.append("/* ").append(name).append(" */");
            }
        } else if (instruction == Instruction.ILOAD_2 || instruction == Instruction.LLOAD_2 || instruction == Instruction.FLOAD_2 || instruction == Instruction.DLOAD_2 || instruction == Instruction.ALOAD_2
                || instruction == Instruction.ISTORE_2 || instruction == Instruction.LSTORE_2 || instruction == Instruction.FSTORE_2 || instruction == Instruction.DSTORE_2 || instruction == Instruction.ASTORE_2) {
            if (hasLocalVariableTable && localVariableTable.length >= 3) {
                AttributeLocalVariableTable.LocalVariable localVariable = localVariableTable[2];
                String name = constantPool.getUTF8(localVariable.getNameIndex()).getValue();

                writer.append("/* ").append(name).append(" */");
            }
        } else if (instruction == Instruction.ILOAD_3 || instruction == Instruction.LLOAD_3 || instruction == Instruction.FLOAD_3 || instruction == Instruction.DLOAD_3 || instruction == Instruction.ALOAD_3
                || instruction == Instruction.ISTORE_3 || instruction == Instruction.LSTORE_3 || instruction == Instruction.FSTORE_3 || instruction == Instruction.DSTORE_3 || instruction == Instruction.ASTORE_3) {
            if (hasLocalVariableTable && localVariableTable.length >= 4) {
                AttributeLocalVariableTable.LocalVariable localVariable = localVariableTable[3];
                String name = constantPool.getUTF8(localVariable.getNameIndex()).getValue();

                writer.append("/* ").append(name).append(" */");
            }
        } else {
            switch (instruction.getOperandType()) {
                case LOCAL: {
                    int index = (code[i + 1] & 0xff);
                    if (hasLocalVariableTable && index < localVariableTable.length) {
                        AttributeLocalVariableTable.LocalVariable localVariable = localVariableTable[index];

                        String name = constantPool.getUTF8(localVariable.getNameIndex()).getValue();
                        String descriptor = constantPool.getUTF8(localVariable.getDescriptorIndex()).getValue();
                        writer.append(name).append(" ").append(descriptor);
                    } else {
                        writer.append("#").append(index);
                    }
                    break;
                }
                case LOCAL_I:
                    writer.append("+").append(code[i + 2]);
                    break;
                case TYPE_REFERENCE: {
                    int index = ((code[i + 1] & 0xff) << 8) | (code[i + 2] & 0xff);
                    writer.append(ConstantUtil.getConstantClassString(constantPool, index));
                    break;
                }
                case TYPE_REFERENCE_I: {
                    int index = ((code[i + 1] & 0xff) << 8) | (code[i + 2] & 0xff);
                    writer.append(ConstantUtil.getConstantClassString(constantPool, index));

                    int dimensions = code[i + 3];
                    for (int j = 0; j < dimensions; j++) {
                        writer.append("[]");
                    }
                }
                break;
                case FIELD_REFERENCE: {
                    int index = ((code[i + 1] & 0xff) << 8) | (code[i + 2] & 0xff);
                    writer.append(ConstantUtil.getConstantFieldReferenceString(constantPool, index));
                }
                break;
                case METHOD_REFERENCE: {
                    int index = ((code[i + 1] & 0xff) << 8) | (code[i + 2] & 0xff);
                    writer.append(ConstantUtil.getConstantMethodReferenceString(constantPool, index));
                    break;
                }
                case INTERFACE_REFERENCE: {
                    int index = ((code[i + 1] & 0xff) << 8) | (code[i + 2] & 0xff);
                    writer.append(ConstantUtil.getConstantInterfaceMethodReferenceString(constantPool, index));
                    break;
                }
                case DYNAMIC_CALL_SITE: {
                    int index = ((code[i + 1] & 0xff) << 8) | (code[i + 2] & 0xff);
                    List<String> lines = ConstantUtil.getConstantInvokeDynamicString(classFile, constantPool, index);

                    for (int i = 0, size = lines.size(); i < size; i++) {
                        String line = lines.get(i);

                        if (i == 0) {
                            writer.append(line).indent();
                        } else if (i + 1 == size) {
                            writer.tab().append(line);
                        } else {
                            writer.tab().append(line).indent();
                        }
                    }
                    break;
                }
                case PRIMITIVE_TYPE_CODE: {
                    int atype = code[i + 1];
                    writer.append(ClassFileUtil.getNewArrayPrimitiveType(atype));
                    break;
                }
                case INT:
                    writer.append(String.valueOf((byte) (code[i + 1] & 0xff)));
                    break;
                case SHORT:
                    writer.append(String.valueOf((short) (((code[i + 1] & 0xff) << 8) | (code[i + 2] & 0xff))));
                    break;
                case BRANCH_TARGET: {
                    int offset = ((code[i + 1] & 0xff) << 8) | (code[i + 2] & 0xff);
                    writer.append("#").append(i + offset);
                    break;
                }
                case BRANCH_TARGET_WIDE: {
                    int offset = ((code[i + 1] & 0xff) << 24) | ((code[i + 2] & 0xff) << 16) | ((code[i + 3] & 0xff) << 8) | (code[i + 4] & 0xff);
                    writer.append("#").append(i + offset);
                    break;
                }
                case CONSTANT: {
                    int offset = code[i + 1] & 0xff;
                    visitConstant(offset);
                    break;
                }
                case CONSTANT_WIDE: {
                    int offset = ((code[i + 1] & 0xff) << 8) | (code[i + 2] & 0xff);
                    visitConstant(offset);
                    break;
                }
            }
        }
    }

    private void visitSwitchInstruction(Instruction instruction) {
        int opcodeAddress = i;
        if (instruction == Instruction.TABLESWITCH) {
            i = (i + 4) & 0xFFFC;

            int defaultValue = ((code[i++] & 0xff) << 24) | ((code[i++] & 0xff) << 16) | ((code[i++] & 0xff) << 8) | (code[i++] & 0xff);
            int lowValue = ((code[i++] & 0xff) << 24) | ((code[i++] & 0xff) << 16) | ((code[i++] & 0xff) << 8) | (code[i++] & 0xff);
            int highValue = ((code[i++] & 0xff) << 24) | ((code[i++] & 0xff) << 16) | ((code[i++] & 0xff) << 8) | (code[i++] & 0xff);

            writer.append("[").indent();
            for (int j = 0; j < (highValue - lowValue + 1); j++) {
                int offset = ((code[i++] & 0xff) << 24) | ((code[i++] & 0xff) << 16) | ((code[i++] & 0xff) << 8) | (code[i++] & 0xff);
                writer.tab().tab().append(lowValue + j).append(" -> #").append(opcodeAddress + offset).indent();
            }

            writer.tab().tab().append("default -> #").append(opcodeAddress + defaultValue).indent();
            writer.tab().append("]");
        } else if (instruction == Instruction.LOOKUPSWITCH) {
            i = (i + 4) & 0xFFFC;

            int defaultValue = ((code[i++] & 0xff) << 24) | ((code[i++] & 0xff) << 16) | ((code[i++] & 0xff) << 8) | (code[i++] & 0xff);
            int nPairsValue = ((code[i++] & 0xff) << 24) | ((code[i++] & 0xff) << 16) | ((code[i++] & 0xff) << 8) | (code[i++] & 0xff);

            writer.append("[").indent();
            for (int j = 0; j < nPairsValue; j++) {
                int match = ((code[i++] & 0xff) << 24) | ((code[i++] & 0xff) << 16) | ((code[i++] & 0xff) << 8) | (code[i++] & 0xff);
                int offset = ((code[i++] & 0xff) << 24) | ((code[i++] & 0xff) << 16) | ((code[i++] & 0xff) << 8) | (code[i++] & 0xff);
                writer.tab().tab().append(match + j).append(" -> #").append(opcodeAddress + offset).indent();
            }

            writer.tab().tab().append("default -> #").append(opcodeAddress + defaultValue).indent();
            writer.tab().append("]");
        }
    }

    private void visitConstant(int index) throws ClassFileException {
        Constant constant = constantPool.get(index);

        if (constant instanceof ConstantClass) {
            ConstantClass constantClass = (ConstantClass) constant;
            String className = constantPool.getUTF8(constantClass.getNameIndex()).getValue();

            writer.append(className);
        } else if (constant instanceof ConstantDouble) {
            ConstantDouble constantDouble = (ConstantDouble) constant;
            double value = constantDouble.getValue();

            writer.append(String.valueOf(value));
        } else if (constant instanceof ConstantFieldReference) {
            writer.append(ConstantUtil.getConstantFieldReferenceString(constantPool, index));
        } else if (constant instanceof ConstantFloat) {
            ConstantFloat constantFloat = (ConstantFloat) constant;
            float value = constantFloat.getValue();

            writer.append(String.valueOf(value));
        } else if (constant instanceof ConstantInteger) {
            ConstantInteger constantInteger = (ConstantInteger) constant;
            int value = constantInteger.getValue();

            writer.append(String.valueOf(value));
        } else if (constant instanceof ConstantInterfaceMethodReference) {
            writer.append(ConstantUtil.getConstantInterfaceMethodReferenceString(constantPool, index));
        } else if (constant instanceof ConstantInvokeDynamic) {
            List<String> lines = ConstantUtil.getConstantInvokeDynamicString(classFile, constantPool, index);

            for (int i = 0, size = lines.size(); i < size; i++) {
                String line = lines.get(i);

                if (i == 0) {
                    writer.append(line).indent();
                } else if (i + 1 == size) {
                    writer.tab().append(line);
                } else {
                    writer.tab().append(line).indent();
                }
            }
        } else if (constant instanceof ConstantLong) {
            ConstantLong constantLong = (ConstantLong) constant;
            long value = constantLong.getValue();

            writer.append(String.valueOf(value));
        } else if (constant instanceof ConstantMethodHandle) {
            writer.append(ConstantUtil.getConstantMethodHandleString(constantPool, index));
        } else if (constant instanceof ConstantMethodReference) {
            writer.append(ConstantUtil.getConstantMethodReferenceString(constantPool, index));
        } else if (constant instanceof ConstantMethodType) {
            writer.append(ConstantUtil.getConstantMethodTypeString(constantPool, index));
        } else if (constant instanceof ConstantNameAndType) {
            writer.append(ConstantUtil.getConstantNameAndTypeString(constantPool, index));
        } else if (constant instanceof ConstantString) {
            ConstantString constantString = (ConstantString) constant;
            String value = constantPool.getUTF8(constantString.getStringIndex()).getValue();

            writer.append("\"").append(value).append("\"");
        } else if (constant instanceof ConstantUTF8) {
            ConstantUTF8 constantUTF8 = (ConstantUTF8) constant;
            String value = constantUTF8.getValue();

            writer.append("\"").append(value).append("\"");
        }
    }

}
