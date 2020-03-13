package com.mrsalwater.kapteyn.decompiler.bytecode.settings;

public enum ByteCodeSetting {

    SHOW_INNERCLASSES("s-ic", "Show inner classes", true),
    SHOW_ENCLOSINGMETHOD("s-em", "Show enclosing methods", false),
    SHOW_BOOTSTRAPMETHODS("s-bm", "Show bootstrap methods", false),
    SHOW_INVISIBLEANNOTATIONS("s-ia", "Show invisible annotations", true);

    private final String id;
    private final String name;
    private final boolean defaultValue;

    ByteCodeSetting(String id, String name, boolean defaultValue) {
        this.id = id;
        this.name = name;
        this.defaultValue = defaultValue;
    }

    public String getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isDefaultValue() {
        return defaultValue;
    }

    public static ByteCodeSetting match(String id) {
        for (ByteCodeSetting byteCodeSetting : values()) {
            if (byteCodeSetting.getID().equals(id)) {
                return byteCodeSetting;
            }
        }

        return null;
    }

}
