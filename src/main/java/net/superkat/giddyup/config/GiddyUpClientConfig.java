package net.superkat.giddyup.config;

import me.lortseam.completeconfig.api.ConfigContainer;
import me.lortseam.completeconfig.api.ConfigEntries;
import me.lortseam.completeconfig.api.ConfigEntry;
import me.lortseam.completeconfig.api.ConfigGroup;

import java.util.Collection;
import java.util.List;

@ConfigEntries(includeAll = true)
public class GiddyUpClientConfig extends GiddyUpServerConfig {
    @Transitive
    @ConfigEntries(includeAll = true)
    public static final class ClientDataTypes implements ConfigGroup {

        @Override
        public Collection<ConfigContainer> getTransitives() {
            return List.of(new DashingIconPlacementClient());
        }

        @ConfigEntries(includeAll = true)
        public static class DashingIconPlacementClient implements ConfigGroup {
            @ConfigEntry.Checkbox
            private boolean testBoolean;

            @ConfigEntry.BoundedInteger(min = 0, max = 10)
            @ConfigEntry.Slider
            @ConfigEntry.IntegerSliderInterval(1)
            private int mySlider;
        }
    }
}
