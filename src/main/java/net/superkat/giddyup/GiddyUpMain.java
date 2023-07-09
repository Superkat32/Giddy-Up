package net.superkat.giddyup;

import io.wispforest.owo.network.OwoNetChannel;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.superkat.giddyup.config.GiddyUpConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GiddyUpMain implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("giddyup");
	public static final String MOD_ID = "giddyup";
	public static final DefaultParticleType DUST = FabricParticleTypes.simple();
	public static final GiddyUpConfig CONFIG = GiddyUpConfig.createAndLoad();
	public static final OwoNetChannel MY_CHANNEL = OwoNetChannel.createOptional(new Identifier(MOD_ID, "main"));
//	public record MyPacket(int someData, String otherData) {}
	public record DashPacket() {}
	public record DashAcceptedPacket() {}

	@Override
	public void onInitialize() {
//		LOGGER.info("Hello Fabric world!");
		Registry.register(Registries.PARTICLE_TYPE, new Identifier(MOD_ID, "dust"), DUST);
//		MY_CHANNEL.registerServerbound(MyPacket.class, ((message, access) -> {
//
//		}));
//		MY_CHANNEL.registerServerbound(MyPacket.class, ((message, access) -> {
//			MY_CHANNEL.serverHandle(access.player()).send(new MyPacket(1, "test"));
//		}));

//		MY_CHANNEL.registerClientbound(DashAcceptedPacket.class, (((message, access) -> {
////			MY_CHANNEL.serverHandle(access.player()).send(new DashAcceptedPacket());
////			MY_CHANNEL.clientHandle().send(new DashPacket());
//			DashHandler.startDash((HorseEntity) access.player().getVehicle());
//		})));
		MY_CHANNEL.registerServerbound(DashPacket.class, (((message, access) -> {
            MY_CHANNEL.serverHandle(access.player()).send(new DashAcceptedPacket());
            LOGGER.info("packet");
        })));
		MY_CHANNEL.registerClientbound(DashAcceptedPacket.class, (((message, access) -> {
			DashHandler.startDash((HorseEntity) access.player().getVehicle());
		})));
	}
}