package net.superkat.giddyup;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.superkat.giddyup.particles.DustParticle;

public class GiddyUpClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ParticleFactoryRegistry.getInstance().register(GiddyUpMain.DUST, DustParticle.Factory::new);
    }
}
