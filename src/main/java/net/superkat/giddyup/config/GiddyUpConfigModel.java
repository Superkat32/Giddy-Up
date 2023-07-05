package net.superkat.giddyup.config;

import io.wispforest.owo.config.Option;
import io.wispforest.owo.config.annotation.*;
import net.superkat.giddyup.GiddyUpMain;

@Modmenu(modId = GiddyUpMain.MOD_ID)
@Config(name = "giddyupconfig", wrapperName = "GiddyUpConfig")
public class GiddyUpConfigModel {

    @SectionHeader("Buffs")

    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public boolean horseSpeedBuff = true;

    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public boolean horseJumpBuff = true;

    @SectionHeader("Dashes")

    @Nest
    public NestedMaxDashes nestedMaxDashes = new NestedMaxDashes();

    public static class NestedMaxDashes {

        @Sync(Option.SyncMode.OVERRIDE_CLIENT)
        @RangeConstraint(min = 0, max = 5)
        public int whiteHorseMaxDash = 4;

        @Sync(Option.SyncMode.OVERRIDE_CLIENT)
        @RangeConstraint(min = 0, max = 5)
        public int creamyHorseMaxDash = 3;

        @Sync(Option.SyncMode.OVERRIDE_CLIENT)
        @RangeConstraint(min = 0, max = 5)
        public int chestnutHorseMaxDash = 3;

        @Sync(Option.SyncMode.OVERRIDE_CLIENT)
        @RangeConstraint(min = 0, max = 5)
        public int brownHorseMaxDash = 2;

        @Sync(Option.SyncMode.OVERRIDE_CLIENT)
        @RangeConstraint(min = 0, max = 5)
        public int blackHorseMaxDash = 2;

        @Sync(Option.SyncMode.OVERRIDE_CLIENT)
        @RangeConstraint(min = 0, max = 5)
        public int grayHorseMaxDash = 1;

        @Sync(Option.SyncMode.OVERRIDE_CLIENT)
        @RangeConstraint(min = 0, max = 5)
        public int darkbrownHorseMaxDash = 1;

        @Sync(Option.SyncMode.OVERRIDE_CLIENT)
        @RangeConstraint(min = 0, max = 5)
        public int whiteHorseMarking = 2;

        @Sync(Option.SyncMode.OVERRIDE_CLIENT)
        @RangeConstraint(min = 0, max = 5)
        public int whiteFieldHorseMarking = 1;

        @Sync(Option.SyncMode.OVERRIDE_CLIENT)
        @RangeConstraint(min = 0, max = 5)
        public int blackDotsHorseMarking = 1;

        @Sync(Option.SyncMode.OVERRIDE_CLIENT)
        @RangeConstraint(min = 0, max = 5)
        public int noneHorseMarking = 1;

        @Sync(Option.SyncMode.OVERRIDE_CLIENT)
        @RangeConstraint(min = 0, max = 5)
        public int whiteDotsHorseMarking = 0;
    }


    @SectionHeader("Dash HUD")

    @RangeConstraint(min = -500, max = 500)
    public int iconX = 0;

    @RangeConstraint(min = -500, max = 500)
    public int iconY = 0;

    @RangeConstraint(min = 0, max = 30)
    public int textureSize = 20;

    @RangeConstraint(min = 0, max = 30)
    public int textureSpacing = 20;

    public boolean easeIn = true;

    public boolean opacityRecharge = true;

}
