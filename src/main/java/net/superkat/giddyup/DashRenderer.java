package net.superkat.giddyup;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.util.Identifier;
import net.superkat.giddyup.config.DashElementScreen;

import static net.superkat.giddyup.GiddyUpMain.CONFIG;


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
    public static int x = 0;
    public static int easeOutTick = 0;
    private static MinecraftClient client;
    private int textureSize = 0;
    private int textureSpacing = 0;
    public DashRenderer() {
        client = MinecraftClient.getInstance();
    }

    public void renderDashElement(DrawContext context) {
        int width = client.getWindow().getScaledWidth();
        int height = client.getWindow().getScaledHeight();
//        GiddyUpMain.LOGGER.info("width: " + width);
//        GiddyUpMain.LOGGER.info("height: " + height);
        textureSize = CONFIG.nestedDashes.textureSize();
        textureSpacing = CONFIG.nestedDashes.textureSpacing();
//        int x = 0;
//        int y = 0;
//        easeOutTick = 0;
//        int y = 187;
        if(!(client.player.getVehicle() instanceof HorseEntity)) {
            y = 0;
            easeOutTick= 0;
        } else if(client.currentScreen instanceof DashElementScreen) {
            y = CONFIG.nestedDashes.iconY();
        }
//        int y = 160;
        if(client.player.hasVehicle() && client.player.getVehicle() instanceof HorseEntity && ((HorseEntity) client.player.getVehicle()).isTame() && ((HorseEntity) client.player.getVehicle()).isSaddled() && maxDashes != 0 && shouldRender) {
            int totalWidth = maxDashes * textureSize + (maxDashes - 1) * textureSize;
            int startX = (width - totalWidth) / 2;
            int finishX = 0;
            int startY = 0;

            if(easeOutTick < 40 && CONFIG.nestedDashes.easeIn()) {
                float t = (float) easeOutTick / 40;
                float easedValue = textureSize * 2 + height + (CONFIG.nestedDashes.iconY() - height - textureSize * 2) * (1 - (1 - t) * (1 - t));
                startY = Math.round(easedValue);
                easeOutTick++;
//          0.35f - (0.8f * (float) currentRechargeTicks / 115)
            } else {
                startY = CONFIG.nestedDashes.iconY();
            }


//            RenderSystem.enableColorLogicOp();
            for(int i = 0; i < maxDashes; i++) {
                finishX = startX + (textureSize + textureSpacing) * i;
                x = finishX + CONFIG.nestedDashes.iconX();
                y = startY;
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
                    if(CONFIG.nestedDashes.opacityRecharge()) {
                        context.setShaderColor(1f, 1f, 1f, iconAlpha);
                        context.drawTexture(dash_ready, x, y, 0.0f, 0.0f, textureSize, textureSize, textureSize, textureSize);
                    }
//                    LOGGER.info(String.valueOf(i));
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
