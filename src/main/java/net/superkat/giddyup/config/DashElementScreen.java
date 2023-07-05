package net.superkat.giddyup.config;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

import static net.superkat.giddyup.GiddyUpMain.CONFIG;

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
        x = CONFIG.nestedDashes.iconX();
        y = CONFIG.nestedDashes.iconY();
//        x = INSTANCE.getConfig().iconX;
//        y = INSTANCE.getConfig().iconY;

        addDrawableChild(
                ButtonWidget.builder(Text.translatable("giddyup.icon.exit"), button -> {
                            this.close();
                        })
                        .position(buttonCenterX, buttonCenterY - 35)
                        .size(buttonWidth, buttonHeight)
                        .build()
        );
        addDrawableChild(
                ButtonWidget.builder(Text.translatable("giddyup.icon.reset"), button -> {
                            x = CONFIG.nestedDashes.iconX();
                            y = CONFIG.nestedDashes.iconY();
                            CONFIG.nestedDashes.iconX(x);
                            CONFIG.nestedDashes.iconY(y);
                        })
                        .position(buttonCenterX, buttonCenterY + 10)
                        .size(buttonWidth, buttonHeight)
                        .build()
        );
        addDrawableChild(
                ButtonWidget.builder(Text.literal("+"), button -> {
                            x += 1;
                            CONFIG.nestedDashes.iconX(x);
                        })
                        .position((int) (buttonCenterX + buttonWidth / 1.5), buttonCenterY - 20)
                        .size(buttonWidth / 3, buttonHeight)
                        .build()
        );
        addDrawableChild(
                ButtonWidget.builder(Text.literal("-"), button -> {
                            x -= 1;
                            CONFIG.nestedDashes.iconX(x);
                        })
                        .position(buttonCenterX + buttonWidth / 3, buttonCenterY - 20)
                        .size(buttonWidth / 3, buttonHeight)
                        .build()
        );
        addDrawableChild(
                ButtonWidget.builder(Text.literal("x:"), button -> {

                        })
                        .position((int) (buttonCenterX), buttonCenterY - 20)
                        .size(buttonWidth / 3, buttonHeight)
                        .build()
        );

        addDrawableChild(
                ButtonWidget.builder(Text.literal("+"), button -> {
                            y += 1;
                            CONFIG.nestedDashes.iconY(y);
                        })
                        .position((int) (buttonCenterX + buttonWidth / 1.5), buttonCenterY - 5)
                        .size(buttonWidth / 3, buttonHeight)
                        .build()
        );
        addDrawableChild(
                ButtonWidget.builder(Text.literal("-"), button -> {
                            y -= 1;
                            CONFIG.nestedDashes.iconY(y);
                        })
                        .position(buttonCenterX + buttonWidth / 3, buttonCenterY - 5)
                        .size(buttonWidth / 3, buttonHeight)
                        .build()
        );
        addDrawableChild(
                ButtonWidget.builder(Text.literal("y:"), button -> {

                        })
                        .position((int) (buttonCenterX), buttonCenterY - 5)
                        .size(buttonWidth / 3, buttonHeight)
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
            if(!(CONFIG.nestedDashes.iconX() + deltaX + 120 == this.client.getWindow().getScaledWidth())) {
                x += deltaX;
                CONFIG.nestedDashes.iconX(x);
//                INSTANCE.getDefaults().iconX = x;
            }
            if(!(CONFIG.nestedDashes.iconY() + deltaY + 20 == this.client.getWindow().getScaledHeight())) {
                y += deltaY;
                CONFIG.nestedDashes.iconY(y);
//                INSTANCE.getDefaults().iconY = y;
            }
//            GiddyUpMain.LOGGER.info("iconX: " + INSTANCE.getConfig().iconX);
//            GiddyUpMain.LOGGER.info("iconY: " + INSTANCE.getConfig().iconY);
//            INSTANCE.save();
        }
        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }
}
