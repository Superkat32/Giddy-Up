package net.superkat.giddyup;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.superkat.giddyup.particles.DustParticle;
import org.lwjgl.glfw.GLFW;

public class GiddyUpClient implements ClientModInitializer {

    public static KeyBinding keyBinding;
    @Override
    public void onInitializeClient() {
        ParticleFactoryRegistry.getInstance().register(GiddyUpMain.DUST, DustParticle.Factory::new);
        keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "giddyup.key.dash",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_H,
                "giddyup.category.keybinds"
        ));

    }
}
