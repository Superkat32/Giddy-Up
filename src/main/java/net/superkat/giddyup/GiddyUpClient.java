package net.superkat.giddyup;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.passive.HorseEntity;
import net.superkat.giddyup.particles.DustParticle;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class GiddyUpClient implements ClientModInitializer {

    public static final KeyBinding DASH = KeyBindingHelper.registerKeyBinding(
        new KeyBinding(
                "giddyup.key.dash",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_H,
                "giddyup.category.keybinds"
        ));
    @Override
    public void onInitializeClient() {
//        GiddyUpConfig.INSTANCE.load();
        ParticleFactoryRegistry.getInstance().register(GiddyUpMain.DUST, DustParticle.Factory::new);
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if(client.getNetworkHandler() != null) {
                if(DASH.isPressed()) {
                    GiddyUpMain.LOGGER.info("hotkey pressed");
//                    ClientPlayNetworking.send(GiddyUpMain.KEYBINDING_PACKET_ID, PacketByteBufs.empty());
                    if(client.player.getVehicle() instanceof HorseEntity && DashHandler.canContinue()) {
//                        DashHandler.test((HorseEntity) client.player.getVehicle());
                        tryDash((HorseEntity) client.player.getVehicle());
                        GiddyUpMain.LOGGER.info("attempting dash");
                    }
                }
            }
        });
    }

    private void tryDash(HorseEntity horse) {
        GiddyUpMain.LOGGER.info("tryDash: 1");
        if(DashHandler.canContinue() && horse.isTame() && horse.isSaddled()) {
            DashHandler.startDash(horse);
            GiddyUpMain.LOGGER.info("tryDash: 2");
        } else {
            return;
        }
    }
}
