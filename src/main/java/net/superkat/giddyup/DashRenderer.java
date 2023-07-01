package net.superkat.giddyup;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.util.Identifier;

public class DashRenderer {
    public Identifier dash_ready = new Identifier(GiddyUpMain.MOD_ID, "textures/dash/dash.png");
    public Identifier dash_used = new Identifier(GiddyUpMain.MOD_ID, "textures/dash/dash_used.png");
    public static boolean dashing;
    public static boolean shouldRender = false;
    public static int maxDashes;
    public static int dashesRemaining;
    public static boolean isDashOneReady;
    public static boolean isDashTwoReady;
    public static boolean isDashThreeReady;
    public static boolean isDashFourReady;
    public static boolean isDashFiveReady;
    public static float iconAlpha;
    public static int y = 0;
    public static int easeOutTick = 0;
    private static MinecraftClient client;
    private int textureSize = 20;
    public DashRenderer() {
        client = MinecraftClient.getInstance();
    }

    public void renderDashElement(DrawContext context) {
        int width = client.getWindow().getScaledWidth();
        int height = client.getWindow().getScaledHeight();
        textureSize = 20;
        int x = 0;
//        int y = 0;
//        easeOutTick = 0;
//        int y = 187;
        if(easeOutTick < 40 && client.player.getVehicle() instanceof HorseEntity) {
            float t = (float) easeOutTick / 40;
            float easedValue = textureSize * 2 + height + (160 - height - textureSize * 2) * (1 - (1 - t) * (1 - t));
            y = Math.round(easedValue);
            easeOutTick++;
//          0.35f - (0.8f * (float) currentRechargeTicks / 115)
        }
        if(!(client.player.getVehicle() instanceof HorseEntity)) {
            y = 0;
            easeOutTick= 0;
        }
//        int y = 160;
        if(client.player.hasVehicle() && client.player.getVehicle() instanceof HorseEntity && ((HorseEntity) client.player.getVehicle()).isTame() && ((HorseEntity) client.player.getVehicle()).isSaddled() && maxDashes != 0 && shouldRender) {
            int totalWidth = maxDashes * textureSize + (maxDashes - 1) * textureSize;
            int startX = (width - totalWidth) / 2;


//            RenderSystem.enableColorLogicOp();
            for(int i = 0; i < maxDashes; i++) {
                x = startX + (textureSize + textureSize) * i;
//                int currentDash = dashesRemaining;
                RenderSystem.enableBlend();
                RenderSystem.defaultBlendFunc();
                RenderSystem.disableDepthTest();
                RenderSystem.depthMask(false);

                context.setShaderColor(1f, 1f, 1f,1f);
                context.drawTexture(isDashReady(i) ? dash_ready : dash_used, x, y, 0.0f, 0.0f, textureSize, textureSize, textureSize, textureSize);
                if(!isDashReady(i) && i == dashesRemaining) {
//                    RenderSystem.enableBlend();
//                    RenderSystem.disableDepthTest();
//                    RenderSystem.enableCull();
//                    LOGGER.info(String.valueOf(iconAlpha));
                    context.setShaderColor(1f, 1f, 1f, iconAlpha);
//                    LOGGER.info(String.valueOf(i));
                    context.drawTexture(dash_ready, x, y, 0.0f, 0.0f, textureSize, textureSize, textureSize, textureSize);
                }
//                RenderSystem.disableBlend();
                RenderSystem.depthMask(true);
                RenderSystem.enableDepthTest();
                context.setShaderColor(1f, 1f, 1f,1f);
//                renderUsedDash(context, i, x, y);
//                LOGGER.info(String.valueOf(i));
            }

//            x = 179;
//            context.drawTexture(isDashOneReady ? dash_ready : dash_used, x, y, 0.0f, 0.0f, 24, 24, 24, 24);
//            x = 227;
//            context.drawTexture(isDashTwoReady ? dash_ready : dash_used, x, y, 0.0f, 0.0f, 24, 24, 24, 24);
//            x = 275;
//            context.drawTexture(isDashThreeReady ? dash_ready : dash_used, x, y, 0.0f, 0.0f, 24, 24, 24, 24);
        }
    }

    public static void setDashing(boolean dashing) {
        DashRenderer.dashing = dashing;
    }

    public static void setShouldRender(boolean shouldRender) {
        DashRenderer.shouldRender = shouldRender;
    }

    public boolean isDashReady(int dash) {
        switch(dash) {
            case 0 -> { return isDashOneReady; }
            case 1 -> { return isDashTwoReady; }
            case 2 -> { return isDashThreeReady; }
            case 3 -> { return isDashFourReady; }
            case 4 -> { return isDashFiveReady; }
        }
        return false;
    }
}
