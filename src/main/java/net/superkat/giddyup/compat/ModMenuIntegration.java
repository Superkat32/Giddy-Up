package net.superkat.giddyup.compat;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.fabricmc.loader.api.FabricLoader;
import net.superkat.giddyup.config.GiddyUpConfig;

public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        if(FabricLoader.getInstance().isModLoaded("yet_another_config_lib_v3")) {
            return GiddyUpConfig::makeScreen;
        }
        return null;
    }
}
