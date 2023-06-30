package net.superkat.giddyup;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GiddyUpMain implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("giddyup");
	public static final String MOD_ID = "giddyup";
//	public static final Identifier KEYBINDING_PACKET_ID = GiddyUpMain.id("keybind_press_test");
	public static final DefaultParticleType DUST = FabricParticleTypes.simple();

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");
		Registry.register(Registries.PARTICLE_TYPE, new Identifier(MOD_ID, "dust"), DUST);
//		ServerPlayConnectionEvents.INIT.register((handler, server) -> ServerPlayNetworking.registerReceiver(handler, KEYBINDING_PACKET_ID, GiddyUpMain::receive));
	}

//	private static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
//		server.execute(() ->  {
//			player.sendMessage(Text.literal("So you pressed ").append(Text.keybind("giddyup.key.dash").styled(style -> style.withFormatting(Formatting.BLUE))), false);
//		});
//	}
//
//	public static Identifier id(String name) {
//		return new Identifier(MOD_ID, name);
//	}
}