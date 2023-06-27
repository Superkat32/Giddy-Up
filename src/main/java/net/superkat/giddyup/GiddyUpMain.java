package net.superkat.giddyup;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GiddyUpMain implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("giddyup");
	public static final String MOD_ID = "giddyup";

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");
	}
}