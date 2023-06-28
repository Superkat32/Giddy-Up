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
	public static final DefaultParticleType DUST = FabricParticleTypes.simple();

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");
		Registry.register(Registries.PARTICLE_TYPE, new Identifier(MOD_ID, "dust"), DUST);
	}
}