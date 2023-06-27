package net.superkat.giddyup.mixin;

import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractHorseEntity.class)
public class AbstractHorseEntityMixin {

	//Horse speed buff
	@Inject(at = @At("TAIL"), method = "getSaddledSpeed", cancellable = true)
	private void speedBuff(PlayerEntity controllingPlayer, CallbackInfoReturnable<Float> cir) {
		float returnSpeed = cir.getReturnValue() * 1.25f;
		cir.setReturnValue(returnSpeed);
//		LOGGER.info(String.valueOf(cir.getReturnValue()));
	}

	//Horse jump buff
	@Inject(at = @At("TAIL"), method = "getJumpStrength", cancellable = true)
	private void jumpBuff(CallbackInfoReturnable<Double> cir) {
		double returnJump = cir.getReturnValue() * 1.15;
		cir.setReturnValue(returnJump);
//		LOGGER.info(String.valueOf(cir.getReturnValue()));
	}


}