package com.mrsalwater.kapteyn.decompiler.bytecode.settings;

import java.util.ArrayList;
import java.util.List;

public final class ByteCodeSettings {

    private final List<ByteCodeSetting> byteCodeSettings;

    public ByteCodeSettings() {
        byteCodeSettings = new ArrayList<>();

        for (ByteCodeSetting byteCodeSetting : ByteCodeSetting.values()) {
            if (byteCodeSetting.isDefaultValue()) {
                byteCodeSettings.add(byteCodeSetting);
            }
        }
    }

    public void setSetting(ByteCodeSetting byteCodeSetting, boolean value) {
        if (value) {
            if (!byteCodeSettings.contains(byteCodeSetting)) {
                byteCodeSettings.add(byteCodeSetting);
            }
        } else {
            byteCodeSettings.remove(byteCodeSetting);
        }
    }

    public boolean hasSetting(ByteCodeSetting byteCodeSetting) {
        return byteCodeSettings.contains(byteCodeSetting);
    }

}
