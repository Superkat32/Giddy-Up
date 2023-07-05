package net.superkat.giddyup.config;

import io.wispforest.owo.config.ui.ConfigScreen;
import io.wispforest.owo.ui.base.BaseOwoScreen;
import io.wispforest.owo.ui.component.Components;
import io.wispforest.owo.ui.container.Containers;
import io.wispforest.owo.ui.container.FlowLayout;
import io.wispforest.owo.ui.core.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

import static net.superkat.giddyup.GiddyUpMain.CONFIG;

public class DashElementScreen extends BaseOwoScreen<FlowLayout> {
//    private Screen lastScreen;
    private int x;
    private int y;

//    public DashElementScreen(Screen lastScreen) {
//        super(Text.translatable("giddyup.dashscreen"));
//        this.lastScreen = lastScreen;
//    }

    @Override
    protected @NotNull OwoUIAdapter<FlowLayout> createAdapter() {
        return OwoUIAdapter.create(this, Containers::verticalFlow);
    }

    @Override
    protected void build(FlowLayout rootComponent) {
        x = CONFIG.iconX();
        y = CONFIG.iconY();
        rootComponent
                .surface(Surface.BLANK)
                .horizontalAlignment(HorizontalAlignment.CENTER)
                .verticalAlignment(VerticalAlignment.CENTER);

        var selectBox = Components.textBox(Sizing.fixed(40));
        selectBox.setTextPredicate(s -> s.matches("\\d*"));

        rootComponent
                .child(
                    Containers.draggable(Sizing.content(), Sizing.content(),
                        Containers.verticalFlow(Sizing.content(), Sizing.content())
                        .child(
                            Containers.verticalFlow(Sizing.content(), Sizing.content())
                                    .child(Components.button(Text.literal("Save and return"), button -> {
                                        this.client.setScreen(ConfigScreen.create(CONFIG, null));
                                    }))
                                    .padding(Insets.of(5))
                                    .surface(Surface.DARK_PANEL)
                                    .verticalAlignment(VerticalAlignment.TOP)
                                    .horizontalAlignment(HorizontalAlignment.CENTER)
                        )
                        .child(
                            Containers.verticalFlow(Sizing.content(), Sizing.content())
                                    .child(Components.button(Text.literal("⬆"), buttonComponent -> {
                                        y -= 1;
                                        CONFIG.iconY(y);
                                    }).sizing(Sizing.fixed(17)))
                                    .child(Containers.horizontalFlow(Sizing.content(), Sizing.content())
                                            .child(Components.button(Text.literal("⬅"), buttonComponent -> {
                                                x -= 1;
                                                CONFIG.iconX(x);
                                            }).sizing(Sizing.fixed(17)))
                                            .child(Components.button(Text.literal("⬇"), buttonComponent -> {
                                                y += 1;
                                                CONFIG.iconY(y);
                                            }).sizing(Sizing.fixed(17)))
                                            .child(Components.button(Text.literal("➡"), buttonComponent -> {
                                                x += 1;
                                                CONFIG.iconX(x);
                                            }).sizing(Sizing.fixed(17)))
                                    )
//                                    .child(Components.texture(new Identifier(GiddyUpMain.MOD_ID, "textures/dash/dash.png"), 0, 0, 24, 24, 24, 24))
                                    .surface(Surface.TOOLTIP)
                                    .verticalAlignment(VerticalAlignment.CENTER)
                                    .horizontalAlignment(HorizontalAlignment.CENTER)
                        ).verticalAlignment(VerticalAlignment.CENTER).horizontalAlignment(HorizontalAlignment.CENTER)
//                        .child(
//                                Containers.verticalFlow(Sizing.content(), Sizing.content())
//                                        .child(Components.button(Text.literal("Save and close"), button -> {
//                                            this.close();
//                                        }))
//                                        .padding(Insets.of(5))
//                                        .surface(Surface.DARK_PANEL)
//                                        .verticalAlignment(VerticalAlignment.TOP)
//                                        .horizontalAlignment(HorizontalAlignment.CENTER)
//                        )
                    ).alwaysOnTop(false).surface(Surface.DARK_PANEL).padding(Insets.of(5)).allowOverflow(true).positioning(Positioning.absolute((int) (MinecraftClient.getInstance().getWindow().getScaledWidth() / 1.35), 40))
                );
    }
//                .child(
//                        Containers.draggable(
//                                Sizing.content(), Sizing.content(),
//                                Containers.verticalFlow(Sizing.content(), Sizing.content())
//                                        .child(Components.label(Text.literal("froge :)"))
//                                                .horizontalTextAlignment(HorizontalAlignment.CENTER)
//                                                .positioning(Positioning.absolute(0, -9))
//                                                .horizontalSizing(Sizing.fixed(100)))
////                                        .child(Components.entity(Sizing.fixed(100), EntityType.FROG, frogeNbt).scale(.75f).allowMouseRotation(true).tooltip(Text.literal(":)")))
//                                        .child(Containers.horizontalFlow(Sizing.fixed(100), Sizing.content())
//                                                .child(Components.button(Text.of("✔"), (ButtonComponent button) -> {
////                                                    this.enableSlot(Integer.parseInt(selectBox.getText()));
//                                                    GiddyUpMain.LOGGER.info("yay");
//                                                }).tooltip(Text.literal("Enable")))
//                                                .child(selectBox.margins(Insets.horizontal(3)).tooltip(Text.literal("Slot Index")))
//                                                .child(Components.button(Text.of("❌"), (ButtonComponent button) -> {
////                                                    this.disableSlot(Integer.parseInt(selectBox.getText()));
//                                                    GiddyUpMain.LOGGER.info("yay2");
//                                                }).tooltip(Text.literal("Disable"))).verticalAlignment(VerticalAlignment.CENTER).horizontalAlignment(HorizontalAlignment.CENTER))
//                                        .allowOverflow(true)
//                        ).alwaysOnTop(true).surface(Surface.DARK_PANEL).padding(Insets.of(5)).allowOverflow(true).positioning(Positioning.absolute(100, 100))
//                );

//    @Override
//    protected void init() {
//        var buttonWidth = 60;
//        var buttonHeight = 15;
//        var buttonCenterX = (width / 2) - (buttonWidth / 2);
//        var buttonCenterY = (height / 2) - (buttonHeight / 2);
//        x = CONFIG.iconX();
//        y = CONFIG.iconY();
////        x = INSTANCE.getConfig().iconX;
////        y = INSTANCE.getConfig().iconY;
//
//        addDrawableChild(
//                ButtonWidget.builder(Text.translatable("giddyup.icon.exit"), button -> {
//                            this.close();
//                        })
//                        .position(buttonCenterX, buttonCenterY - 35)
//                        .size(buttonWidth, buttonHeight)
//                        .build()
//        );
//        addDrawableChild(
//                ButtonWidget.builder(Text.translatable("giddyup.icon.reset"), button -> {
//                            x = CONFIG.iconX();
//                            y = CONFIG.iconY();
//                            CONFIG.iconX(x);
//                            CONFIG.iconY(y);
//                        })
//                        .position(buttonCenterX, buttonCenterY + 10)
//                        .size(buttonWidth, buttonHeight)
//                        .build()
//        );
//        addDrawableChild(
//                ButtonWidget.builder(Text.literal("+"), button -> {
//                            x += 1;
//                            CONFIG.iconX(x);
//                        })
//                        .position((int) (buttonCenterX + buttonWidth / 1.5), buttonCenterY - 20)
//                        .size(buttonWidth / 3, buttonHeight)
//                        .build()
//        );
//        addDrawableChild(
//                ButtonWidget.builder(Text.literal("-"), button -> {
//                            x -= 1;
//                            CONFIG.iconX(x);
//                        })
//                        .position(buttonCenterX + buttonWidth / 3, buttonCenterY - 20)
//                        .size(buttonWidth / 3, buttonHeight)
//                        .build()
//        );
//        addDrawableChild(
//                ButtonWidget.builder(Text.literal("x:"), button -> {
//
//                        })
//                        .position((int) (buttonCenterX), buttonCenterY - 20)
//                        .size(buttonWidth / 3, buttonHeight)
//                        .build()
//        );
//
//        addDrawableChild(
//                ButtonWidget.builder(Text.literal("+"), button -> {
//                            y += 1;
//                            CONFIG.iconY(y);
//                        })
//                        .position((int) (buttonCenterX + buttonWidth / 1.5), buttonCenterY - 5)
//                        .size(buttonWidth / 3, buttonHeight)
//                        .build()
//        );
//        addDrawableChild(
//                ButtonWidget.builder(Text.literal("-"), button -> {
//                            y -= 1;
//                            CONFIG.iconY(y);
//                        })
//                        .position(buttonCenterX + buttonWidth / 3, buttonCenterY - 5)
//                        .size(buttonWidth / 3, buttonHeight)
//                        .build()
//        );
//        addDrawableChild(
//                ButtonWidget.builder(Text.literal("y:"), button -> {
//
//                        })
//                        .position((int) (buttonCenterX), buttonCenterY - 5)
//                        .size(buttonWidth / 3, buttonHeight)
//                        .build()
//        );
//    }

//    @Override
//    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
////        renderBackground(context);
////        renderBackgroundTexture(context);
//        super.render(context, mouseX, mouseY, delta);
//    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if(!this.isDragging() && button == 0) {
            if(!(CONFIG.iconX() + deltaX + 120 == this.client.getWindow().getScaledWidth())) {
                x += deltaX;
                CONFIG.iconX(x);
//                INSTANCE.getDefaults().iconX = x;
            }
            if(!(CONFIG.iconY() + deltaY + 20 == this.client.getWindow().getScaledHeight())) {
                y += deltaY;
                CONFIG.iconY(y);
//                INSTANCE.getDefaults().iconY = y;
            }
//            GiddyUpMain.LOGGER.info("iconX: " + INSTANCE.getConfig().iconX);
//            GiddyUpMain.LOGGER.info("iconY: " + INSTANCE.getConfig().iconY);
//            INSTANCE.save();
        }
        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }
}
