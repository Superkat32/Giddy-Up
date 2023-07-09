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

import static net.superkat.giddyup.GiddyUpMain.LOGGER;
//import static net.superkat.giddyup.GiddyUpMain.MY_CHANNEL;

@Environment(EnvType.CLIENT)
public class GiddyUpClient implements ClientModInitializer {

//    public record DashAcceptedPacket() {}

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
//        MY_CHANNEL.registerClientbound(GiddyUpMain.MyPacket.class, (message, access) -> {
//            LOGGER.info("yay1111");
//        });
//        MY_CHANNEL.registerServerbound(GiddyUpMain.DashPacket.class, (message, access) -> {
////            DashHandler.startDash((HorseEntity) access.player().getVehicle());
//            MY_CHANNEL.serverHandle(access.player()).send(new GiddyUpMain.DashAcceptedPacket());
//            LOGGER.info("packet");
//        });
//        GiddyUpMain.MY_CHANNEL.registerClientbound(GiddyUpMain.DashAcceptedPacket.class, (((message, access) -> {
//            DashHandler.startDash((HorseEntity) access.player().getVehicle());
//        })));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if(client.getNetworkHandler() != null) {
                if(DASH.isPressed()) {
//                    MY_CHANNEL.clientHandle().send(new GiddyUpMain.MyPacket(1, "this"));
                    LOGGER.info("hotkey pressed");
//                    ClientPlayNetworking.send(GiddyUpMain.KEYBINDING_PACKET_ID, PacketByteBufs.empty());
                    if(client.player.getVehicle() instanceof HorseEntity && DashHandler.canContinue()) {
//                        DashHandler.test((HorseEntity) client.player.getVehicle());
                        GiddyUpMain.MY_CHANNEL.clientHandle().send(new GiddyUpMain.DashPacket());
                        tryDash((HorseEntity) client.player.getVehicle());
                        LOGGER.info("attempting dash");
                    }
                }
            }
        });
    }

    private void tryDash(HorseEntity horse) {
        LOGGER.info("tryDash: 1");
        if(DashHandler.canContinue() && horse.isTame() && horse.isSaddled()) {
            LOGGER.info("tryDash: 2");
//            DashHandler.startDash(horse);
        }
    }
}