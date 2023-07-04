package net.superkat.giddyup.config;

import lombok.Getter;
import me.lortseam.completeconfig.api.ConfigContainer;
import me.lortseam.completeconfig.api.ConfigEntries;
import me.lortseam.completeconfig.api.ConfigEntry;
import me.lortseam.completeconfig.api.ConfigGroup;
import me.lortseam.completeconfig.data.Config;
import me.lortseam.completeconfig.data.ConfigOptions;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.superkat.giddyup.GiddyUpMain;

import java.util.Collection;
import java.util.List;

@ConfigEntries(includeAll = true)
public class GiddyUpServerConfig extends Config {

    public GiddyUpServerConfig() {
        super(ConfigOptions
                .mod(GiddyUpMain.MOD_ID)
                .fileHeader("Giddy Up's config."));
    }

//    @ConfigEntry(comment = "test")
//    private boolean comment;

    @Override
    public Collection<ConfigContainer> getTransitives() {
        if(FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            return List.of(new ServerConfig.Dashing(), new ServerConfig.Buffs());
        }
        return List.of(new ServerConfig.Dashing(), new ServerConfig.Buffs());
    }

    @Transitive
    @ConfigEntries(includeAll = true)
    public static class ServerConfig implements ConfigGroup {

        @ConfigEntries(includeAll = true)
        public static class Buffs implements ConfigGroup {
            @Getter
            @ConfigEntry(comment = "Server only")
            @ConfigEntry.Checkbox
            private static boolean horseSpeedBuff = true;

            @Getter
            @ConfigEntry.Checkbox
            private static boolean horseJumpBuff = true;

        }

//        @ConfigEntry.Checkbox
//        private boolean secondTestBoolean;

        @ConfigEntries(includeAll = true)
        public static class Dashing implements ConfigGroup {
            @Getter
            @ConfigEntry.Checkbox
            private static boolean test = false;
//            @ConfigEntry.Checkbox
//            @Getter
//            private boolean testBoolean;
//
//            @ConfigEntry.BoundedInteger(min = 0, max = 10)
//            @ConfigEntry.Slider
//            @ConfigEntry.IntegerSliderInterval(1)
//            private int mySlider;
        }


    }
}
