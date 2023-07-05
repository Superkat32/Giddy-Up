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
    private int x;
    private int y;

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
                                    .surface(Surface.TOOLTIP)
                                    .verticalAlignment(VerticalAlignment.CENTER)
                                    .horizontalAlignment(HorizontalAlignment.CENTER)
                        ).verticalAlignment(VerticalAlignment.CENTER).horizontalAlignment(HorizontalAlignment.CENTER)
                    ).alwaysOnTop(false).surface(Surface.DARK_PANEL).padding(Insets.of(5)).allowOverflow(true).positioning(Positioning.absolute((int) (MinecraftClient.getInstance().getWindow().getScaledWidth() / 1.35), 40))
                );
    }
    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if(!this.isDragging() && button == 0) {
            if(!(CONFIG.iconX() + deltaX + 120 == this.client.getWindow().getScaledWidth())) {
                x += deltaX;
                CONFIG.iconX(x);
            }
            if(!(CONFIG.iconY() + deltaY + 20 == this.client.getWindow().getScaledHeight())) {
                y += deltaY;
                CONFIG.iconY(y);
            }
        }
        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }
}
