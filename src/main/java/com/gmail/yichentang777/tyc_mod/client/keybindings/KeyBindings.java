package com.gmail.yichentang777.tyc_mod.client.keybindings;

import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;
import com.gmail.yichentang777.tyc_mod.TycMod;

public class KeyBindings {

    public static final KeyMapping ROLL_LEFT = new KeyMapping(
            "key"+TycMod.MODID+".roll_left", // Description
            GLFW.GLFW_KEY_Q,       // Bind to the Q key
            "key.categories.movement" // Movement category
    );

    public static final KeyMapping ROLL_RIGHT = new KeyMapping(
            "key"+TycMod.MODID+".roll_right", // Description
            GLFW.GLFW_KEY_E,       // Bind to the Q key
            "key.categories.movement" // Movement category
    );

    public static final KeyMapping LIFT = new KeyMapping(
            "key"+TycMod.MODID+".lift",
            GLFW.GLFW_KEY_SPACE,
            "key.categories.movement"
    );
    public static final KeyMapping DIVE = new KeyMapping(
            "key"+TycMod.MODID+".dive",
            GLFW.GLFW_KEY_C,
            "key.categories.movement"
    );


}
