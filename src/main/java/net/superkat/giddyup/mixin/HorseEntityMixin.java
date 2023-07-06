package net.superkat.giddyup.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
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
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.superkat.giddyup.DashHandler;
import net.superkat.giddyup.DashRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.UUID;

import static net.superkat.giddyup.GiddyUpMain.LOGGER;

@Mixin(value = HorseEntity.class, priority = 490)
public abstract class HorseEntityMixin extends AbstractHorseEntity implements VariantHolder<HorseColor> {

    @Shadow
    public abstract HorseMarking getMarking();

    @Shadow protected abstract SoundEvent getAngrySound();

    //Literally just the soul speed's id, except wit the last number replaced
    private static final UUID HORSE_DASH_ID = UUID.fromString("87f46a96-686f-4796-b035-22e16ee9e039");
    public int ticksRidden = 0;
    public boolean continueNoRiderActions = false;
    public int maxDashes = -1;
    public int dashesRemaining;
    public boolean canDash = false;
    public boolean dashing = false;
    public int dashRechargeTime = 115;
    public int dashTicks = 0;
    public int cooldownTicks = 0;
    public int rechargeTicks = 0;
    public boolean hasDashedBefore = false;

    public HorseEntityMixin(EntityType<? extends AbstractHorseEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void tickControlled(PlayerEntity controllingPlayer, Vec3d movementInput) {
        super.tickControlled(controllingPlayer, movementInput);
        ticksRidden++;
        if(FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            updateDashHandler(false);
        }

        //dashing
        if (controllingPlayer != null && FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            DashHandler.renderHUD();
        };
        if(ticksRidden == 1) {
            if(FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
                DashHandler.updateDashHud(maxDashes, dashesRemaining);
            }
            if(!hasDashedBefore) {
                hasDashedBefore = true;
                rechargeTicks = dashRechargeTime;
            }
        }

        if(canDash && !dashing && cooldownTicks == 0 && dashesRemaining > 0) {
            canDash = false;
            DashHandler.canDash = false;
            this.dashing = true;
            dashesRemaining--;
//            DashRenderer.setDashing(true);
            if(FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
                DashHandler.updateDashHud(maxDashes, dashesRemaining);
            }
            cooldownTicks = 50;
            rechargeTicks = dashRechargeTime;
            dashTicks = 35;
            removeDashBoost();
            addDashBoost();
//            this.playSound(this.getAngrySound(), this.getSoundVolume(), this.getSoundPitch());
//            this.playAngrySound();
//            this.getWorld().playSound(this.getX(), this.getY(), this.getZ(), this.getAngrySound(), this.getSoundCategory(), this.getSoundVolume(), 1, true);
//            this.getWorld().playSoundFromEntity(null, getSelf(), this.getAngrySound(), this.getSoundCategory(), 5.0f, this.getSoundPitch());
//            Objects.requireNonNull(Objects.requireNonNull(this.getServer()).getWorld(this.getWorld().getRegistryKey() == World.NETHER ? World.OVERWORLD : World.NETHER)).playSoundFromEntity(null, this, this.getAngrySound(), this.getSoundCategory(), 2.0f, 1.0f);
//            getSelf().playAngrySound();
//            this.getControllingPassenger().playSound(this.getAngrySound(), this.getSoundVolume(), this.getSoundPitch());
//            if(FabricLoader.getInstance().getEnvironmentType() == EnvType.SERVER) {
//                this.getWorld().playSoundFromEntity(null, this, this.getAngrySound(), this.getSoundCategory(), 2.0f, 1.0f);
//            } else {
//            }
        }
    }

    public void updateDashHandler(boolean noRider) {
        if(noRider) {
            DashHandler.currentMaxDashes = -1;
            DashHandler.currentRemainingDashes = -1;
            DashHandler.currentDashTicks = -1;
            DashHandler.currentCooldownTicks = -1;
            DashHandler.currentRechargeTicks = -1;
            continueNoRiderActions = false;
        } else {
            DashHandler.currentMaxDashes = maxDashes;
            DashHandler.currentRemainingDashes = dashesRemaining;
            DashHandler.currentDashTicks = dashTicks;
            DashHandler.currentCooldownTicks = cooldownTicks;
            DashHandler.currentRechargeTicks = rechargeTicks;
            continueNoRiderActions = true;
        }
    }

//    public void updateDashHud() {

//        DashRenderer.maxDashes = maxDashes;
//        DashRenderer.dashesRemaining = dashesRemaining;
//
//        DashRenderer.isDashFiveReady = dashesRemaining >= 5;
//        DashRenderer.isDashFourReady = dashesRemaining >= 4;
//        DashRenderer.isDashThreeReady = dashesRemaining >= 3;
//        DashRenderer.isDashTwoReady = dashesRemaining >= 2;
//        DashRenderer.isDashOneReady = dashesRemaining >= 1;
//
//        if (dashesRemaining < 0 || dashesRemaining > 5) {
//            LOGGER.warn("DASHES REMAINING UNKNOWN: " + dashesRemaining);
//            DashRenderer.isDashFiveReady = false;
//            DashRenderer.isDashFourReady = false;
//            DashRenderer.isDashThreeReady = false;
//            DashRenderer.isDashTwoReady = false;
//            DashRenderer.isDashOneReady = false;
//        }
//    }

    //Remove later
    public void addDashBoost() {
        EntityAttributeInstance speed = this.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
        if(speed == null) {
            return;
        }

        speed.addTemporaryModifier(new EntityAttributeModifier(HORSE_DASH_ID, "Horse dash speed boost", 0.15, EntityAttributeModifier.Operation.ADDITION));
    }

    //Remove later
    public void removeDashBoost() {
        EntityAttributeInstance speed = this.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
        if(speed != null) {
            if(speed.getModifier(HORSE_DASH_ID) != null) {
                speed.removeModifier(HORSE_DASH_ID);
            }
        }
    }

    public HorseEntity getSelf() {
        var horse = (HorseEntity) (Object)this;
        if(horse == null) {
            LOGGER.warn("HorseEntityMixin is null!");
        }
        return horse;
    }

    @Override
    public void tick() {
        super.tick();

        //Activates upon being loaded/spawned
        if(this.getControllingPassenger() == null && continueNoRiderActions && FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            ticksRidden = 0;
            updateDashHandler(true);
        }
        if(maxDashes == -1 && FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            maxDashes = DashHandler.determineMaxDashes(getSelf(), this.getVariant(), this.getMarking());
            dashesRemaining = maxDashes;
            updateDashHandler(true);
        }

        //Checks if the keybind has been pressed
        canDash = DashHandler.canDash;
        DashHandler.dashing = dashing;

        //Updates the max dashes
        if(this.getControllingPassenger() != null) {
            maxDashes = DashHandler.determineMaxDashes(getSelf(), this.getVariant(), this.getMarking());
            if(FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
                DashRenderer.maxDashes = maxDashes;
            }
            if(dashesRemaining > maxDashes) {
                dashesRemaining = maxDashes;
            }
        }


        //Recharges the dash
        if(rechargeTicks > 0) {
            --rechargeTicks;
            if(rechargeTicks == 0) {
                if(dashesRemaining == maxDashes) {
                    return;
                }
                dashesRemaining++;
                rechargeTicks = 115;
                if(this.getControllingPassenger() !=  null && FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
                    DashHandler.updateDashHud(maxDashes, dashesRemaining);
                }
            }
        }

        //Adds a brief cooldown before allowing another dash
        if(cooldownTicks > 0) {
            --cooldownTicks;
            --dashTicks;
//            LOGGER.info(String.valueOf(dashCooldown));
            if(dashTicks == 0) {
                removeDashBoost();
                dashing = false;
//                if(this.getControllingPassenger() != null) {
//                    DashRenderer.setDashing(false);
//                }
            }
        }

        //dust particles
//        double velX = Math.abs(this.getVelocity().getX());
//        double velZ = Math.abs(this.getVelocity().getZ());
////        this.getWorld().addParticle(GiddyUpMain.DUST, this.getX() + this.getRotationVector().multiply(this.random.nextFloat() * (this.random.nextBoolean() ? 1 : -1), 0, 0).getX() + this.getRotationVector().multiply(-1.25, 0, 0).getX(), this.getY() + this.random.nextFloat() / 10, this.getZ() + this.getRotationVector().multiply(0, 0, this.random.nextFloat() * (this.random.nextBoolean() ? 1 : -1)).getZ() + this.getRotationVector().multiply(0, 0, -1.25).getZ(), this.random.nextFloat(), 40, 0.0);
//        if(velX > 0.15 || velZ > 0.15) {
//            if(this.isOnGround() && ticksRidden % 2 == 0) {
//                int amount = this.random.nextBetween(1, 4);
//                for(int smokeAmount = amount; smokeAmount >= 1; smokeAmount--) {
//                    //The particle's "velocity" isn't actually the velocity, it is used to determine other numbers
//                    //The velX number is used to determine the dust particle's scale
//                    //The velY number is used to determine the dust particle's age, which in turn is used to determine how quickly the particle should shrink
//                    if(dashing) {
////                        this.getWorld().addParticle(GiddyUpMain.DUST, this.getX() + this.getRotationVector().multiply(this.random.nextFloat() * (this.random.nextBoolean() ? 1 : -1), 0, 0).getX() + this.getRotationVector().multiply(-1.25, 0, 0).getX(), this.getY() + this.random.nextFloat() / 10, this.getZ() + this.getRotationVector().multiply(0, 0, this.random.nextFloat() * (this.random.nextBoolean() ? 1 : -1)).getZ() + this.getRotationVector().multiply(0, 0, -1.25).getZ(), this.random.nextFloat() * 2, 80, 0.0);
//                    } else {
////                        this.getWorld().addParticle(GiddyUpMain.DUST, this.getX() + this.getRotationVector().multiply(this.random.nextFloat() * (this.random.nextBoolean() ? 1 : -1), 0, 0).getX() + this.getRotationVector().multiply(-1.25, 0, 0).getX(), this.getY() + this.random.nextFloat() / 10, this.getZ() + this.getRotationVector().multiply(0, 0, this.random.nextFloat() * (this.random.nextBoolean() ? 1 : -1)).getZ() + this.getRotationVector().multiply(0, 0, -1.25).getZ(), this.random.nextFloat(), 40, 0.0);
//                    }
//                }
//            }
//        }
    }
}
