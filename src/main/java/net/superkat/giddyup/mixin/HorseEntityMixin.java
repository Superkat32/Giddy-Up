package net.superkat.giddyup.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.VariantHolder;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.entity.passive.HorseColor;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.entity.passive.HorseMarking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.superkat.giddyup.DashRenderer;
import net.superkat.giddyup.GiddyUpClient;
import net.superkat.giddyup.GiddyUpMain;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.UUID;

import static net.superkat.giddyup.GiddyUpMain.LOGGER;

@Mixin(HorseEntity.class)
public abstract class HorseEntityMixin extends AbstractHorseEntity implements VariantHolder<HorseColor> {

    @Shadow public abstract HorseMarking getMarking();

    //Literally just the soul speed's id, except wit the last number replaced
    private static final UUID HORSE_DASH_ID = UUID.fromString("87f46a96-686f-4796-b035-22e16ee9e039");
    public int ticksRidden = 0;
    public boolean dashing = false;
    public int dashCooldown = 0;
    public int dashRechargeTime = 115;
    public int ticksUntilRechargeReset;
    public int dashRecharge = 0;
    public int dashDuration = 0;
    public int maxDashes = 1;
    public int dashesRemaining = maxDashes;

    protected HorseEntityMixin(EntityType<? extends AbstractHorseEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void tickControlled(PlayerEntity controllingPlayer, Vec3d movementInput) {
        super.tickControlled(controllingPlayer, movementInput);
        if(this.getVariant() == HorseColor.WHITE) {
            maxDashes = 5;
        } else if (this.getVariant() == HorseColor.CREAMY) {
            maxDashes = 4;
        } else if (this.getVariant() == HorseColor.CHESTNUT) {
            maxDashes = 4;
        } else if (this.getVariant() == HorseColor.BROWN) {
            maxDashes = 3;
        } else if (this.getVariant() == HorseColor.BLACK) {
            maxDashes = 3;
        } else if (this.getVariant() == HorseColor.GRAY) {
            maxDashes = 2;
        } else if (this.getVariant() == HorseColor.DARK_BROWN) {
            maxDashes = 1;
        }

        if(maxDashes != 5) {
            if(this.getMarking() == HorseMarking.WHITE) {
                if(maxDashes + 2 > 5) {
                    maxDashes = 5;
                } else {
                    maxDashes += 2;
                }
            } else if(this.getMarking() == HorseMarking.WHITE_FIELD) {
                maxDashes++;
            } else if(this.getMarking() == HorseMarking.BLACK_DOTS) {
                maxDashes++;
            } else if(this.getMarking() == HorseMarking.NONE) {
                maxDashes++;
            }
        }
        ticksRidden++;

        //dashing
        if(controllingPlayer != null) DashRenderer.setShouldRender(true);
        if(ticksRidden == 4) {
            updateDashHud();
        }

        if(GiddyUpClient.keyBinding.isPressed() && !dashing && dashCooldown == 0 && dashesRemaining > 0) {
            this.dashing = true;
            dashesRemaining--;
            DashRenderer.setDashing(true);
            updateDashHud();
            dashCooldown = 50;
            dashRecharge = dashRechargeTime;
            dashDuration = 35;
            removeDashBoost();
            addDashBoost();
            this.playSound(this.getAngrySound(), this.getSoundVolume(), this.getSoundPitch());
        }

        //dust particles
        double velX = Math.abs(this.getVelocity().getX());
        double velZ = Math.abs(this.getVelocity().getZ());
        if(velX > 0.15 || velZ > 0.15) {
            if(this.isOnGround() && ticksRidden % 2 == 0) {
                int amount = this.random.nextBetween(1, 4);
                for(int smokeAmount = amount; smokeAmount >= 1; smokeAmount--) {
                    //The particle's "velocity" isn't actually the velocity, it is used to determine other numbers
                    //The velX number is used to determine the dust particle's scale
                    //The velY number is used to determine the dust particle's age, which in turn is used to determine how quickly the particle should shrink
                    if(dashing) {
                        this.getWorld().addParticle(GiddyUpMain.DUST, this.getX() + this.getRotationVector().multiply(this.random.nextFloat() * (this.random.nextBoolean() ? 1 : -1), 0, 0).getX(), this.getY() + this.random.nextFloat() / 10, this.getZ() + this.getRotationVector().multiply(0, 0, this.random.nextFloat() * (this.random.nextBoolean() ? 1 : -1)).getZ(), this.random.nextFloat() * 2, 80, 0.0);
                    } else {
                        this.getWorld().addParticle(GiddyUpMain.DUST, this.getX() + this.getRotationVector().multiply(this.random.nextFloat() * (this.random.nextBoolean() ? 1 : -1), 0, 0).getX(), this.getY() + this.random.nextFloat() / 10, this.getZ() + this.getRotationVector().multiply(0, 0, this.random.nextFloat() * (this.random.nextBoolean() ? 1 : -1)).getZ(), this.random.nextFloat(), 40, 0.0);
                    }
                }
            }
        }
    }

    public void updateDashHud() {
        DashRenderer.maxDashes = maxDashes;
        DashRenderer.dashesRemaining = dashesRemaining;
        switch (dashesRemaining) {
            case 5 -> {
                DashRenderer.isDashFiveReady = true;
                DashRenderer.isDashFourReady = true;
                DashRenderer.isDashThreeReady = true;
                DashRenderer.isDashTwoReady = true;
                DashRenderer.isDashOneReady = true;
            } case 4 -> {
                DashRenderer.isDashFiveReady = false;
                DashRenderer.isDashFourReady = true;
                DashRenderer.isDashThreeReady = true;
                DashRenderer.isDashTwoReady = true;
                DashRenderer.isDashOneReady = true;
            } case 3 -> {
                DashRenderer.isDashFiveReady = false;
                DashRenderer.isDashFourReady = false;
                DashRenderer.isDashThreeReady = true;
                DashRenderer.isDashTwoReady = true;
                DashRenderer.isDashOneReady = true;
            } case 2 -> {
                DashRenderer.isDashFiveReady = false;
                DashRenderer.isDashFourReady = false;
                DashRenderer.isDashThreeReady = false;
                DashRenderer.isDashTwoReady = true;
                DashRenderer.isDashOneReady = true;
            } case 1 -> {
                DashRenderer.isDashFiveReady = false;
                DashRenderer.isDashFourReady = false;
                DashRenderer.isDashThreeReady = false;
                DashRenderer.isDashTwoReady = false;
                DashRenderer.isDashOneReady = true;
            } case 0 -> {
                DashRenderer.isDashFiveReady = false;
                DashRenderer.isDashFourReady = false;
                DashRenderer.isDashThreeReady = false;
                DashRenderer.isDashTwoReady = false;
                DashRenderer.isDashOneReady = false;
            }
            default -> {
                LOGGER.warn("DASHES REMAINING UNKNOWN: " + dashesRemaining);
                DashRenderer.isDashFiveReady = false;
                DashRenderer.isDashFourReady = false;
                DashRenderer.isDashThreeReady = false;
                DashRenderer.isDashTwoReady = false;
                DashRenderer.isDashOneReady = false;
            }
        }
    }

    public void addDashBoost() {
        EntityAttributeInstance speed = this.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
        if(speed == null) {
            return;
        }

        speed.addTemporaryModifier(new EntityAttributeModifier(HORSE_DASH_ID, "Horse dash speed boost", 0.15, EntityAttributeModifier.Operation.ADDITION));
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
        if(this.getControllingPassenger() == null) {
            ticksRidden = 0;
        }

        if(dashRecharge > 0) {
            --dashRecharge;
            if(dashRecharge == 0) {
                if(dashesRemaining == maxDashes) {
                    return;
                }
                dashesRemaining++;
                dashRecharge = 115;
                if(this.getControllingPassenger() !=  null) {
                    DashRenderer.iconAlpha = 0f;
                    updateDashHud();
                }
            } else if(dashRecharge > 3 && this.getControllingPassenger() != null) {
                DashRenderer.iconAlpha = 0.2f - (0.8f * (float) dashRecharge / dashRechargeTime);
            }
        }

        if(dashCooldown > 0) {
            --dashCooldown;
            --dashDuration;
//            LOGGER.info(String.valueOf(dashCooldown));
            if(dashDuration == 0) {
                removeDashBoost();
                dashing = false;
                if(this.getControllingPassenger() != null) {
                    DashRenderer.setDashing(false);
                }
            }
        }

    }

//    @Override
//    public boolean canSprintAsVehicle() {
//        return true;
//    }

}
