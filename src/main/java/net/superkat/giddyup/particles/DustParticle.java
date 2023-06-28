package net.superkat.giddyup.particles;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

@Environment(EnvType.CLIENT)
public class DustParticle extends SpriteBillboardParticle {
    private final SpriteProvider spriteProvider;

    DustParticle(ClientWorld world, double x, double y, double z, double velX, double velY, double velZ, SpriteProvider spriteProvider) {
        super(world, x, y, z);
        this.spriteProvider = spriteProvider;
        this.scale = (float) velX;
        this.maxAge = (int) velY;
        this.velocityX = 0;
        this.velocityY = 0.1;
        this.velocityZ = 0;
        this.x = x;
        this.y = y;
        this.z = z;
        this.alpha = 1.0f;
        this.collidesWithWorld = true;
        this.setSpriteForAge(spriteProvider);
    }

    public void tick() {
        this.prevPosX = this.x;
        this.prevPosY = this.y;
        this.prevPosZ = this.z;
        this.prevAngle = this.angle;
        if(this.age ++ >= this.maxAge || this.scale <= 0 || this.alpha <= 0) {
            this.markDead();
        } else {
            if(this.maxAge == 80) {
                this.scale *= 0.92;
            } else {
                this.scale *= 0.85;
            }
            if(this.scale <= 0.05) {
                this.scale -= 0.005;
            }
            this.move(this.velocityX, this.velocityY, this.velocityZ);
            this.setSpriteForAge(this.spriteProvider);
        }
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider spriteProvider;
        public Factory(SpriteProvider spriteProvider) { //The factory used in the client initializer
            this.spriteProvider = spriteProvider;
        }
        public Particle createParticle(DefaultParticleType defaultParticleType, ClientWorld clientWorld, double x, double y, double z, double velX, double velY, double velZ) {
            return new DustParticle(clientWorld, x, y, z, velX, velY, velZ, this.spriteProvider);
        }
    }

}
