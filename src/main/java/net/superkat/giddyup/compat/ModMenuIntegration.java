package net.superkat.giddyup.compat;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.lortseam.completeconfig.gui.yacl.YaclScreenBuilder;
import net.superkat.giddyup.GiddyUpMain;

public class ModMenuIntegration implements ModMenuApi {

//    private static final ConfigScreenBuilder screenBuilder = new YaclScreenBuilder();
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
//        if(FabricLoader.getInstance().isModLoaded("yet_another_config_lib_v3")) {
//            return GiddyUpConfig::makeScreen;
//        }
//        return null;
//        return parent -> screenBuilder.build(parent, );
        return parent -> new YaclScreenBuilder().build(parent, GiddyUpMain.config);
    }
}
