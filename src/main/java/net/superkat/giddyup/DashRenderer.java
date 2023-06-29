package net.superkat.giddyup;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.util.Identifier;

import static net.superkat.giddyup.GiddyUpMain.LOGGER;

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
    private static MinecraftClient client;
    public DashRenderer() {
        client = MinecraftClient.getInstance();
    }

    public void renderDashElement(DrawContext context) {
        int width = client.getWindow().getScaledWidth();
        int height = client.getWindow().getScaledHeight();
        int x = 0;
        int y = 175;
        if(client.player.hasVehicle() && client.player.getVehicle() instanceof HorseEntity && ((HorseEntity) client.player.getVehicle()).isTame() && ((HorseEntity) client.player.getVehicle()).isSaddled() && maxDashes != 0 && shouldRender) {
            int totalWidth = maxDashes * 24 + (maxDashes - 1) * 24;
            int startX = (width - totalWidth) / 2;

            for(int i = 0; i < maxDashes; i++) {
                x = startX + (24 + 24) * i;
//                int currentDash = dashesRemaining;

                context.drawTexture(isDashReady(i) ? dash_ready : dash_used, x, y, 0.0f, 0.0f, 24, 24, 24, 24);
                if(!isDashReady(i) && i == dashesRemaining) {
                    RenderSystem.enableBlend();
//                    RenderSystem.enableBlend();
//                    RenderSystem.disableDepthTest();
//                    RenderSystem.enableCull();
                    context.setShaderColor(1f, 1f, 1f, iconAlpha);
                    LOGGER.info(String.valueOf(dashesRemaining));
                    context.drawTexture(dash_ready, x, y, 0.0f, 0.0f, 24, 24, 24, 24);
                } else {
                    context.setShaderColor(1, 1, 1, 1f);
                }
//                RenderSystem.disableBlend();
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

    public void renderUsedDash(DrawContext context, int i, int x, int y) {
//        context.drawTexture(isDashReady(i) ? dash_ready : dash_used, x, y, 0.0f, 0.0f, 24, 24, 24, 24);
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
