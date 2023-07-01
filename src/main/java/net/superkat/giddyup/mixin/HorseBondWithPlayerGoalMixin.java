package net.superkat.giddyup.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.HorseBondWithPlayerGoal;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HorseBondWithPlayerGoal.class)
public abstract class HorseBondWithPlayerGoalMixin extends Goal {

    @Shadow @Final private AbstractHorseEntity horse;

    @Inject(method = "tick", at = @At(value = "HEAD"), cancellable = true)
    public void tick(CallbackInfo ci) {
        if(!this.horse.isTame()) {
            Entity entity = this.horse.getPassengerList().get(0);
            if(entity instanceof PlayerEntity && ((PlayerEntity) entity).isCreative()) {
                ci.cancel();
                this.horse.bondWithPlayer((PlayerEntity) entity);
            }
        }
    }
}
