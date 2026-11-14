package com.template.templatemod.keymapping;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.neoforged.neoforge.common.util.Lazy;
import org.lwjgl.glfw.GLFW;

public class ModKeyMappings {
    private static final KeyMapping TEST_KEY_MAPPING = new KeyMapping("key.templatemod.test_key",
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_K,KeyMapping.Category.GAMEPLAY);
    public static final Lazy<KeyMapping> PRESS_TEST_KEY = Lazy.of(() -> TEST_KEY_MAPPING);

    public static void register() {

    }
}
