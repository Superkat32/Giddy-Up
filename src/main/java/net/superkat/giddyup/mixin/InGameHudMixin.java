package net.superkat.giddyup.mixin;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.superkat.giddyup.DashRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {
    private static final DashRenderer dashRenderer = new DashRenderer();

    @Inject(method = "render", at = @At("RETURN"))
    public void renderDash(DrawContext context, float tickDelta, CallbackInfo ci) {
        dashRenderer.renderDashElement(context);
    }

}
