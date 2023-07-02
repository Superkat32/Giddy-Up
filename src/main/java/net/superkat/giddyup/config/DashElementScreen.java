package net.superkat.giddyup.config;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.superkat.giddyup.GiddyUpMain;

import static net.superkat.giddyup.config.GiddyUpConfig.INSTANCE;

public class DashElementScreen extends Screen {
    private Screen lastScreen;
    private int x;
    private int y;

    public DashElementScreen(Screen lastScreen) {
        super(Text.translatable("giddyup.dashscreen"));
        this.lastScreen = lastScreen;
    }

    @Override
    protected void init() {
        var buttonWidth = 60;
        var buttonHeight = 15;
        var buttonCenterX = (width / 2) - (buttonWidth / 2);
        var buttonCenterY = (height / 2) - (buttonHeight / 2);
        x = INSTANCE.getConfig().iconX;
        y = INSTANCE.getConfig().iconY;

        addDrawableChild(
                ButtonWidget.builder(Text.translatable("giddyup.icon.exit"), button -> {
                    this.close();
//                    this.client.setScreenAndRender(lastScreen);
//                    this.client.setScreen(lastScreen);
//                    INSTANCE.getConfig().iconX = x;
//                    INSTANCE.getConfig().iconY = y;
//                    this.client.currentScreen = lastScreen;
//                    INSTANCE.load();
//                    this.client.setScreen(GiddyUpConfig.makeScreen(lastScreen));
//                    INSTANCE.save();
                })
                .position(buttonCenterX, buttonCenterY - 35)
                .size(buttonWidth, buttonHeight)
                .build()
        );
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
//        renderBackground(context);
//        renderBackgroundTexture(context);
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if(!this.isDragging() && button == 0) {
            if(!(INSTANCE.getConfig().iconX + deltaX + 120 == this.client.getWindow().getScaledWidth())) {
                x += deltaX;
                INSTANCE.getConfig().iconX = x;
//                INSTANCE.getDefaults().iconX = x;
            }
            if(!(INSTANCE.getConfig().iconY + deltaY + 20 == this.client.getWindow().getScaledHeight())) {
                y += deltaY;
                INSTANCE.getConfig().iconY = y;
//                INSTANCE.getDefaults().iconY = y;
            }
            GiddyUpMain.LOGGER.info("iconX: " + INSTANCE.getConfig().iconX);
            GiddyUpMain.LOGGER.info("iconY: " + INSTANCE.getConfig().iconY);
//            INSTANCE.save();
        }
        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }
}
