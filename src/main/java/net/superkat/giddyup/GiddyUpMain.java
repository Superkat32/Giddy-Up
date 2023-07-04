package net.superkat.giddyup;

import lombok.Getter;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.GameRules;
import net.superkat.giddyup.commands.GiddyUpCommands;
import net.superkat.giddyup.config.GiddyUpServerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GiddyUpMain implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("giddyup");
	public static final String MOD_ID = "giddyup";
//	public static final Identifier KEYBINDING_PACKET_ID = GiddyUpMain.id("keybind_press_test");
	public static final DefaultParticleType DUST = FabricParticleTypes.simple();
	public static GameRules.Key<GameRules.BooleanRule> HORSE_SPEED_BUFF;
	public static final GameRules.Key<GameRules.BooleanRule> HORSE_JUMP_BUFF = GameRuleRegistry.register("horseJumpBuff", GameRules.Category.MOBS, GameRuleFactory.createBooleanRule(true));
	public static GameRules.Key<GameRules.BooleanRule> TEST;
	@Getter
	public static final GiddyUpServerConfig config = new GiddyUpServerConfig();
//	public static final GameRules.Key<GameRules.BooleanRule> SHOULD_PIGS_FLY = GameRuleRegistry.register("shouldPigsFly", GameRules.Category.MOBS, GameRuleFactory.createBooleanRule(true));

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");
		config.load();
		Registry.register(Registries.PARTICLE_TYPE, new Identifier(MOD_ID, "dust"), DUST);
		HORSE_SPEED_BUFF = GameRuleRegistry.register("horseSpeedBuff", GameRules.Category.MOBS, GameRuleFactory.createBooleanRule(true));
		TEST = GameRuleRegistry.register("test", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(true));
		GiddyUpCommands.register();
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