package net.superkat.giddyup.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.VariantHolder;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.entity.passive.HorseColor;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

import java.util.UUID;

import static net.superkat.giddyup.GiddyUpMain.LOGGER;

@Mixin(HorseEntity.class)
public abstract class HorseEntityMixin extends AbstractHorseEntity implements VariantHolder<HorseColor> {

    //Literally just the soul speed's id, except wit the last number replaced
    private static final UUID HORSE_DASH_ID = UUID.fromString("87f46a96-686f-4796-b035-22e16ee9e039");
    public boolean dashing = false;
    public int dashCooldown = 0;
    public int dashDuration = 0;

    protected HorseEntityMixin(EntityType<? extends AbstractHorseEntity> entityType, World world) {
        super(entityType, world);
    }

//    @Override
//    protected void jump(float strength, Vec3d movementInput) {
//        double d = this.getJumpStrength() * (double)strength * (double)this.getJumpVelocityMultiplier();
//        double e = d + (double)this.getJumpBoostVelocityModifier();
//        Vec3d vec3d = this.getVelocity();
//        this.setVelocity(vec3d.x, e, vec3d.z);
//        this.setInAir(true);
//        this.velocityDirty = true;
//        if (movementInput.z > 0.0) {
//            float f = MathHelper.sin(this.getYaw() * 0.017453292F);
//            float g = MathHelper.cos(this.getYaw() * 0.017453292F);
//            this.setVelocity(this.getVelocity().add((double)(-0.4F * f * strength), 0.0, (double)(0.4F * g * strength)));
//        }
//        if(getFirstPassenger().isSprinting()) {
//            double f = this.getAttributeValue(EntityAttributes.HORSE_JUMP_STRENGTH) * (double)this.getJumpVelocityMultiplier() + (double)this.getJumpBoostVelocityModifier();
//            this.addVelocity(this.getRotationVector().multiply(1.0, 0.0, 1.0).normalize().multiply((double)(22.2222F * strength) * this.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED) * (double)this.getVelocityMultiplier()).add(0.0, (double)(1.4285F * strength) * f, 0.0));
//            GiddyUpMain.LOGGER.info("is sprinting!");
//        }
//    }

    @Override
    protected void tickControlled(PlayerEntity controllingPlayer, Vec3d movementInput) {
        super.tickControlled(controllingPlayer, movementInput);
        if(dashCooldown > 0) {
            --this.dashCooldown;
            --this.dashDuration;
            LOGGER.info(String.valueOf(dashCooldown));
            if(dashDuration == 0) {
                removeDashBoost();
                dashing = false;
            }
        }
        if(controllingPlayer.isSprinting() && !dashing && dashCooldown == 0) {
            this.dashing = true;
            dashCooldown = 175;
            dashDuration = 35;
            removeDashBoost();
            addDashBoost();
        }
    }

    public void addDashBoost() {
        EntityAttributeInstance speed = this.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
        if(speed == null) {
            return;
        }

        speed.addTemporaryModifier(new EntityAttributeModifier(HORSE_DASH_ID, "Horse dash speed boost", 0.37, EntityAttributeModifier.Operation.ADDITION));
    }

    public void removeDashBoost() {
        EntityAttributeInstance speed = this.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
        if(speed != null) {
            if(speed.getModifier(HORSE_DASH_ID) != null) {
                speed.removeModifier(HORSE_DASH_ID);
            }
        }
    }

    @Override
    public void tick() {
        super.tick();
        if(this.dashing) {
//            addDashBoost();
        }
    }

    @Override
    public boolean canSprintAsVehicle() {
        return true;
    }

}
