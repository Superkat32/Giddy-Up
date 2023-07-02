package net.superkat.giddyup.config;

import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.config.ConfigEntry;
import dev.isxander.yacl3.config.GsonConfigInstance;
import dev.isxander.yacl3.gui.controllers.BooleanController;
import dev.isxander.yacl3.gui.controllers.slider.IntegerSliderController;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.nio.file.Path;

public class GiddyUpConfig {

    public static final GsonConfigInstance<GiddyUpConfig> INSTANCE = GsonConfigInstance.<GiddyUpConfig>createBuilder(GiddyUpConfig.class)
            .setPath(Path.of("./config/giddyup.json")).build();

    @ConfigEntry public boolean horseSpeedBuff = true;
    @ConfigEntry public boolean horseJumpBuff = true;
    @ConfigEntry public int iconX = 0;
    @ConfigEntry public int iconY = 160;

    public static Screen makeScreen(Screen parent) {
        return YetAnotherConfigLib.create(INSTANCE, (defaults, config, builder) -> {
            var serverCategoryBuilder = ConfigCategory.createBuilder()
                    .name(Text.translatable("giddyup.category.server"));

            var buffsGroup = OptionGroup.createBuilder()
                    .name(Text.translatable("giddyup.buffs.group"))
                    .description(OptionDescription.createBuilder()
                            .text(Text.translatable("giddyup.buffs.group.tooltip"))
                            .build());

            var speedBuff = Option.<Boolean>createBuilder()
                    .name(Text.translatable("giddyup.speedbuff"))
                    .description(OptionDescription.createBuilder()
                            .text(Text.translatable("giddyup.speedbuff.tooltip"))
                            .build())
                    .binding(
                            defaults.horseSpeedBuff,
                            () -> config.horseSpeedBuff,
                            val -> config.horseSpeedBuff = val
                    )
                    .customController(booleanOption -> new BooleanController(booleanOption, true))
                    .build();

            var jumpBuff = Option.<Boolean>createBuilder()
                    .name(Text.translatable("giddyup.jumpbuff"))
                    .description(OptionDescription.createBuilder()
                            .text(Text.translatable("giddyup.jumpbuff.tooltip"))
                            .build())
                    .binding(
                            defaults.horseJumpBuff,
                            () -> config.horseJumpBuff,
                            val -> config.horseJumpBuff = val
                    )
                    .customController(booleanOption -> new BooleanController(booleanOption, true))
                    .build();
//


            buffsGroup.option(speedBuff);
            buffsGroup.option(jumpBuff);
            serverCategoryBuilder.group(buffsGroup.build());

            var dashCategory = ConfigCategory.createBuilder()
                    .name(Text.translatable("giddyup.category.dash"));

            var iconGroup = OptionGroup.createBuilder()
                    .name(Text.translatable("giddyup.icon.group"))
                    .description(OptionDescription.createBuilder()
                            .text(Text.translatable("giddyup.icon.group.tooltip"))
                            .build());

            var iconX = Option.<Integer>createBuilder()
                    .name(Text.translatable("giddyup.iconx"))
                    .description(OptionDescription.createBuilder()
                            .text(Text.translatable("giddyup.iconx.tooltip"))
                            .build())
                    .binding(
                            defaults.iconX,
                            () -> config.iconX,
                            val -> config.iconX = val
                    )
                    .customController(opt -> new <Integer>IntegerSliderController(opt, -MinecraftClient.getInstance().getWindow().getScaledWidth(), MinecraftClient.getInstance().getWindow().getScaledWidth(), 1))
                    .build();


            var iconY = Option.<Integer>createBuilder()
                    .name(Text.translatable("giddyup.icony"))
                    .description(OptionDescription.createBuilder()
                            .text(Text.translatable("giddyup.icony.tooltip"))
                            .build())
                    .binding(
                            defaults.iconY,
                            () -> config.iconY,
                            val -> config.iconY = val
                    )
                    .customController(opt -> new <Integer>IntegerSliderController(opt, -MinecraftClient.getInstance().getWindow().getScaledHeight(), MinecraftClient.getInstance().getWindow().getScaledHeight(), 1))
                    .build();

            var button = ButtonOption.createBuilder()
                    .name(Text.literal("button name test"))
                    .description(OptionDescription.createBuilder()
                            .text(Text.literal("yay"))
                            .build())
                    .action((screen, opt) -> MinecraftClient.getInstance().setScreen(new DashElementScreen(screen)))
                    .build();

            iconGroup.option(iconX);
            iconGroup.option(iconY);
            iconGroup.option(button);

            dashCategory.group(iconGroup.build());

            return builder
                    .title(Text.translatable("giddyup.title"))

                    .category(serverCategoryBuilder.build())
                    .category(dashCategory.build());
        }).generateScreen(parent);
    }

}
