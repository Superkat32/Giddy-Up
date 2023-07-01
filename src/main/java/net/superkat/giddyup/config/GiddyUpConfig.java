package net.superkat.giddyup.config;

import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.config.ConfigEntry;
import dev.isxander.yacl3.config.GsonConfigInstance;
import dev.isxander.yacl3.gui.controllers.BooleanController;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.nio.file.Path;

public class GiddyUpConfig {

    public static final GsonConfigInstance<GiddyUpConfig> INSTANCE = GsonConfigInstance.<GiddyUpConfig>createBuilder(GiddyUpConfig.class)
            .setPath(Path.of("./config/giddyup.json")).build();

    @ConfigEntry public boolean horseSpeedBuff = true;
    @ConfigEntry public boolean horseJumpBuff = true;

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
//            var button = ButtonOption.createBuilder()
//                    .name(Text.literal("button name test"))
//                    .description(OptionDescription.createBuilder()
//                            .text(Text.literal("yay"))
//                            .build())
//                    .action((screen, opt) -> SystemToast.add(MinecraftClient.getInstance().getToastManager(), SystemToast.Type.TUTORIAL_HINT, Text.literal("Yay!"), Text.literal("You pressed a button!")))
//                    .action((screen, opt) -> MinecraftClient.getInstance().setScreen(new DashElementScreen(screen)))
//                    .build();
//


            buffsGroup.option(speedBuff);
            buffsGroup.option(jumpBuff);
            serverCategoryBuilder.group(buffsGroup.build());

            return builder
                    .title(Text.translatable("giddyup.title"))

                    .category(serverCategoryBuilder.build());
        }).generateScreen(parent);
    }

}
