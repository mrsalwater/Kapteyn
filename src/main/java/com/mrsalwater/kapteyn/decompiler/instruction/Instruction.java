package com.mrsalwater.kapteyn.decompiler.instruction;

public enum Instruction {

    NOP("nop", 0, OperandType.NONE),

    ACONST_NULL("aconst_null", 1, OperandType.NONE),

    ICONST_M1("iconst_m1", 2, OperandType.NONE),
    ICONST_0("iconst_0", 3, OperandType.NONE),
    ICONST_1("iconst_1", 4, OperandType.NONE),
    ICONST_2("iconst_2", 5, OperandType.NONE),
    ICONST_3("iconst_3", 6, OperandType.NONE),
    ICONST_4("iconst_4", 7, OperandType.NONE),
    ICONST_5("iconst_5", 8, OperandType.NONE),

    LCONST_0("lconst_0", 9, OperandType.NONE),
    LCONST_1("lconst_1", 10, OperandType.NONE),

    FCONST_0("fconst_0", 11, OperandType.NONE),
    FCONST_1("fconst_0", 12, OperandType.NONE),
    FCONST_2("fconst_0", 13, OperandType.NONE),

    DCONST_0("dconst_0", 14, OperandType.NONE),
    DCONST_1("dconst_1", 15, OperandType.NONE),

    BIPUSH("bipush", 16, OperandType.INT),
    SIPUSH("sipush", 17, OperandType.SHORT),

    LDC("ldc", 18, OperandType.CONSTANT),
    LDC_W("ldc_w", 19, OperandType.CONSTANT_WIDE),
    LDC2_W("ldc2_w", 20, OperandType.CONSTANT_WIDE),

    ILOAD("iload", 21, OperandType.LOCAL),
    LLOAD("lload", 22, OperandType.LOCAL),
    FLOAD("fload", 23, OperandType.LOCAL),
    DLOAD("dload", 24, OperandType.LOCAL),
    ALOAD("aload", 25, OperandType.LOCAL),

    ILOAD_0("iload_0", 26, OperandType.NONE),
    ILOAD_1("iload_1", 27, OperandType.NONE),
    ILOAD_2("iload_2", 28, OperandType.NONE),
    ILOAD_3("iload_3", 29, OperandType.NONE),

    LLOAD_0("lload_0", 30, OperandType.NONE),
    LLOAD_1("lload_1", 31, OperandType.NONE),
    LLOAD_2("lload_2", 32, OperandType.NONE),
    LLOAD_3("lload_3", 33, OperandType.NONE),

    FLOAD_0("fload_0", 34, OperandType.NONE),
    FLOAD_1("fload_1", 35, OperandType.NONE),
    FLOAD_2("fload_2", 36, OperandType.NONE),
    FLOAD_3("fload_3", 37, OperandType.NONE),

    DLOAD_0("dload_0", 38, OperandType.NONE),
    DLOAD_1("dload_1", 39, OperandType.NONE),
    DLOAD_2("dload_2", 40, OperandType.NONE),
    DLOAD_3("dload_3", 41, OperandType.NONE),

    ALOAD_0("aload_0", 42, OperandType.NONE),
    ALOAD_1("aload_1", 43, OperandType.NONE),
    ALOAD_2("aload_2", 44, OperandType.NONE),
    ALOAD_3("aload_3", 45, OperandType.NONE),

    IALOAD("iaload", 46, OperandType.NONE),
    LALOAD("laload", 47, OperandType.NONE),
    FALOAD("faload", 48, OperandType.NONE),
    DALOAD("daload", 49, OperandType.NONE),
    AALOAD("aaload", 50, OperandType.NONE),
    BALOAD("baload", 51, OperandType.NONE),
    CALOAD("caload", 52, OperandType.NONE),
    SALOAD("saload", 53, OperandType.NONE),

    ISTORE("istore", 54, OperandType.LOCAL),
    LSTORE("lstore", 55, OperandType.LOCAL),
    FSTORE("fstore", 56, OperandType.LOCAL),
    DSTORE("dstore", 57, OperandType.LOCAL),
    ASTORE("astore", 58, OperandType.LOCAL),

    ISTORE_0("istore_0", 59, OperandType.NONE),
    ISTORE_1("istore_1", 60, OperandType.NONE),
    ISTORE_2("istore_2", 61, OperandType.NONE),
    ISTORE_3("istore_3", 62, OperandType.NONE),

    LSTORE_0("lstore_0", 63, OperandType.NONE),
    LSTORE_1("lstore_1", 64, OperandType.NONE),
    LSTORE_2("lstore_2", 65, OperandType.NONE),
    LSTORE_3("lstore_3", 66, OperandType.NONE),

    FSTORE_0("fstore_0", 67, OperandType.NONE),
    FSTORE_1("fstore_1", 68, OperandType.NONE),
    FSTORE_2("fstore_2", 69, OperandType.NONE),
    FSTORE_3("fstore_3", 70, OperandType.NONE),

    DSTORE_0("dstore_0", 71, OperandType.NONE),
    DSTORE_1("dstore_1", 72, OperandType.NONE),
    DSTORE_2("dstore_2", 73, OperandType.NONE),
    DSTORE_3("dstore_3", 74, OperandType.NONE),

    ASTORE_0("astore_0", 75, OperandType.NONE),
    ASTORE_1("astore_1", 76, OperandType.NONE),
    ASTORE_2("astore_2", 77, OperandType.NONE),
    ASTORE_3("astore_3", 78, OperandType.NONE),

    IASTORE("iastore", 79, OperandType.NONE),
    LASTORE("lastore", 80, OperandType.NONE),
    FASTORE("fastore", 81, OperandType.NONE),
    DASTORE("dastore", 82, OperandType.NONE),
    AASTORE("aastore", 83, OperandType.NONE),
    BASTORE("bastore", 84, OperandType.NONE),
    CASTORE("castore", 85, OperandType.NONE),
    SASTORE("sastore", 86, OperandType.NONE),

    POP("pop", 87, OperandType.NONE),
    POP2("pop2", 88, OperandType.NONE),

    DUP("dup", 89, OperandType.NONE),
    DUP_X1("dup_x1", 90, OperandType.NONE),
    DUP_X2("dup_x2", 91, OperandType.NONE),
    DUP2("dup2", 92, OperandType.NONE),
    DUP2_X1("dup2_x1", 93, OperandType.NONE),
    DUP2_X2("dup2_x2", 94, OperandType.NONE),

    SWAP("swap", 95, OperandType.NONE),

    IADD("iadd", 96, OperandType.NONE),
    LADD("ladd", 97, OperandType.NONE),
    FADD("fadd", 98, OperandType.NONE),
    DADD("dadd", 99, OperandType.NONE),

    ISUB("isub", 100, OperandType.NONE),
    LSUB("lsub", 101, OperandType.NONE),
    FSUB("fsub", 102, OperandType.NONE),
    DSUB("dsub", 103, OperandType.NONE),

    IMUL("imul", 104, OperandType.NONE),
    LMUL("lmul", 105, OperandType.NONE),
    FMUL("fmul", 106, OperandType.NONE),
    DMUL("dmul", 107, OperandType.NONE),

    LDIV("ldiv", 109, OperandType.NONE),
    IDIV("idiv", 108, OperandType.NONE),
    FDIV("fdiv", 110, OperandType.NONE),
    DDIV("ddiv", 111, OperandType.NONE),

    IREM("irem", 112, OperandType.NONE),
    LREM("lrem", 113, OperandType.NONE),
    FREM("frem", 114, OperandType.NONE),
    DREM("drem", 115, OperandType.NONE),

    INEG("ineg", 116, OperandType.NONE),
    LNEG("lneg", 117, OperandType.NONE),
    FNEG("fneg", 118, OperandType.NONE),
    DNEG("dneg", 119, OperandType.NONE),

    ISHL("ishl", 120, OperandType.NONE),
    LSHL("lshl", 121, OperandType.NONE),
    ISHR("ishr", 122, OperandType.NONE),
    LSHR("lshr", 123, OperandType.NONE),

    IUSHR("iushr", 124, OperandType.NONE),
    LUSHR("lushr", 125, OperandType.NONE),

    IAND("iand", 126, OperandType.NONE),
    LAND("land", 127, OperandType.NONE),

    IOR("ior", 128, OperandType.NONE),
    LIOR("lior", 129, OperandType.NONE),
    IXOR("ixor", 130, OperandType.NONE),
    LXOR("lxor", 131, OperandType.NONE),

    IINC("iinc", 132, OperandType.LOCAL_I),

    I2L("i2l", 133, OperandType.NONE),
    I2F("i2f", 134, OperandType.NONE),
    I2D("i2d", 135, OperandType.NONE),

    L2I("l2i", 136, OperandType.NONE),
    L2F("l2f", 137, OperandType.NONE),
    L2D("l2d", 138, OperandType.NONE),

    F2I("f2i", 139, OperandType.NONE),
    F2L("f2l", 140, OperandType.NONE),
    F2D("f2d", 141, OperandType.NONE),

    D2I("d2i", 142, OperandType.NONE),
    D2L("d2l", 143, OperandType.NONE),
    D2F("d2f", 144, OperandType.NONE),

    I2B("i2b", 145, OperandType.NONE),
    I2C("i2c", 146, OperandType.NONE),
    I2S("i2s", 147, OperandType.NONE),

    LCMO("lcmp", 148, OperandType.NONE),
    FCMPL("fcmpl", 149, OperandType.NONE),
    FCMPG("fcmpg", 150, OperandType.NONE),
    DCMPL("dcmpl", 151, OperandType.NONE),
    DCMPG("dcmpg", 152, OperandType.NONE),

    IFEQ("ifeq", 153, OperandType.BRANCH_TARGET),
    IFNE("ifne", 154, OperandType.BRANCH_TARGET),
    IFLT("iflt", 155, OperandType.BRANCH_TARGET),
    IFGE("ifge", 156, OperandType.BRANCH_TARGET),
    IFGT("ifgt", 157, OperandType.BRANCH_TARGET),
    IFLE("ifle", 158, OperandType.BRANCH_TARGET),


    IF_ICMPEQ("if_icmpeq", 159, OperandType.BRANCH_TARGET),
    IF_ICMPNE("if_icmpeq", 160, OperandType.BRANCH_TARGET),
    IF_ICMPLT("if_icmpeq", 161, OperandType.BRANCH_TARGET),
    IF_ICMPGE("if_icmpeq", 162, OperandType.BRANCH_TARGET),
    IF_ICMPGT("if_icmpeq", 163, OperandType.BRANCH_TARGET),
    IF_ICMPLE("if_icmpeq", 164, OperandType.BRANCH_TARGET),

    IF_ACMPEQ("if_acmpeq", 165, OperandType.BRANCH_TARGET),
    IF_ACMPNE("if_acmpne", 166, OperandType.BRANCH_TARGET),

    GOTO("goto", 167, OperandType.BRANCH_TARGET),
    JSR("jsr", 168, OperandType.BRANCH_TARGET),
    RET("ret", 169, OperandType.LOCAL),

    TABLESWITCH("tableswitch", 170, OperandType.SWITCH),
    LOOKUPSWITCH("lookupswitch", 171, OperandType.SWITCH),

    IRETURN("ireturn", 172, OperandType.NONE),
    LRETURN("lreturn", 173, OperandType.NONE),
    FRETURN("freturn", 174, OperandType.NONE),
    DRETURN("dreturn", 175, OperandType.NONE),
    ARETURN("areturn", 176, OperandType.NONE),
    RETURN("return", 177, OperandType.NONE),

    GETSTATIC("getstatic", 178, OperandType.FIELD_REFERENCE),
    PUTSTATIC("putstatic", 179, OperandType.FIELD_REFERENCE),
    GETFIELD("getfield", 180, OperandType.FIELD_REFERENCE),
    PUTFIELD("putfield", 181, OperandType.FIELD_REFERENCE),

    INVOKEVIRTUAL("invokevirtual", 182, OperandType.METHOD_REFERENCE),
    INVOKESPECIAL("invokespecial", 183, OperandType.METHOD_REFERENCE),
    INVOKESTATIC("invokestatic", 184, OperandType.METHOD_REFERENCE),
    INVOKEINTERFACE("invokeinterace", 185, OperandType.INTERFACE_REFERENCE),
    INVOKEDYNAMIC("invokedynamic", 186, OperandType.DYNAMIC_CALL_SITE),

    NEW("new", 187, OperandType.TYPE_REFERENCE),
    NEWARRAY("newarray", 188, OperandType.PRIMITIVE_TYPE_CODE),
    ANEWARRAY("anewarray", 189, OperandType.TYPE_REFERENCE),
    ARRAYLENGTH("arraylength", 190, OperandType.NONE),

    ATHROW("athrow", 191, OperandType.NONE),

    CHECKCAST("checkcast", 192, OperandType.TYPE_REFERENCE),

    INSTANCEOF("instanceof", 193, OperandType.TYPE_REFERENCE),

    MONITORENTER("monitorenter", 194, OperandType.NONE),
    MONITOREXIT("monitorexit", 195, OperandType.NONE),

    WIDE("wide", 196, OperandType.NONE), // TODO: IMPLEMENT

    MULTIANEWARRAY("multianrewarray", 197, OperandType.TYPE_REFERENCE_I),

    IFNULL("ifnull", 198, OperandType.BRANCH_TARGET),
    IFNONNULL("ifnonnull", 199, OperandType.BRANCH_TARGET),

    GOTO_W("goto_w", 200, OperandType.BRANCH_TARGET_WIDE),
    JSR_W("jsr_w", 201, OperandType.BRANCH_TARGET_WIDE);

    private final String name;
    private final int opcode;
    private final OperandType operandType;

    Instruction(String name, int opcode, OperandType operandType) {
        this.name = name;
        this.opcode = opcode;
        this.operandType = operandType;
    }

    public String getName() {
        return name;
    }

    public int getOpcode() {
        return opcode;
    }

    public OperandType getOperandType() {
        return operandType;
    }

    public static Instruction match(int opcode) {
        for (Instruction instruction : values()) {
            if (instruction.opcode == opcode) {
                return instruction;
            }
        }

        throw new NullPointerException("Invalid opcode");
    }

}
