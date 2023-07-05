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
        textureSize = CONFIG.textureSize();
        textureSpacing = CONFIG.textureSpacing();
//        int x = 0;
//        int y = 0;
//        easeOutTick = 0;
//        int y = 187;
        if(!(client.player.getVehicle() instanceof HorseEntity)) {
            y = 0;
            easeOutTick= 0;
        } else if(client.currentScreen instanceof DashElementScreen) {
            y = CONFIG.iconY();
        }
//        int y = 160;
        if(client.player.hasVehicle() && client.player.getVehicle() instanceof HorseEntity && ((HorseEntity) client.player.getVehicle()).isTame() && ((HorseEntity) client.player.getVehicle()).isSaddled() && maxDashes != 0 && shouldRender) {
            if(CONFIG.openScreenNextTime()) {
                MinecraftClient.getInstance().setScreen(new DashElementScreen());
                CONFIG.openScreenNextTime(false);
            }
            int totalWidth = maxDashes * textureSize + (maxDashes - 1) * textureSize;
            int startX = (width - totalWidth) / 2;
            int finishX = 0;
            int startY = 0;

            if(easeOutTick < 40 && CONFIG.easeIn()) {
                float t = (float) easeOutTick / 40;
                float easedValue = textureSize * 2 + height + (CONFIG.iconY() - height - textureSize * 2) * (1 - (1 - t) * (1 - t));
                startY = Math.round(easedValue);
                easeOutTick++;
            } else {
                startY = CONFIG.iconY();
            }

            for(int i = 0; i < maxDashes; i++) {
                finishX = startX + (textureSize + textureSpacing) * i;
                x = finishX + CONFIG.iconX();
                y = startY;
                RenderSystem.enableBlend();
                RenderSystem.defaultBlendFunc();
                RenderSystem.disableDepthTest();
                RenderSystem.depthMask(false);

                context.setShaderColor(1f, 1f, 1f,1f);
                context.drawTexture(isDashReady(i) ? dash_ready : dash_used, x, y, 0.0f, 0.0f, textureSize, textureSize, textureSize, textureSize);
                if(!isDashReady(i) && i == dashesRemaining) {
//                    LOGGER.info(String.valueOf(iconAlpha));
                    if(CONFIG.opacityRecharge()) {
                        context.setShaderColor(1f, 1f, 1f, iconAlpha);
                        context.drawTexture(dash_ready, x, y, 0.0f, 0.0f, textureSize, textureSize, textureSize, textureSize);
                    }
//                    LOGGER.info(String.valueOf(i));
                }

                RenderSystem.depthMask(true);
                RenderSystem.enableDepthTest();
                context.setShaderColor(1f, 1f, 1f,1f);
            }
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
