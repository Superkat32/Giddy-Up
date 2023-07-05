package net.superkat.giddyup;

import net.minecraft.entity.passive.HorseColor;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.entity.passive.HorseMarking;

import static net.superkat.giddyup.GiddyUpMain.CONFIG;
import static net.superkat.giddyup.GiddyUpMain.LOGGER;

public class DashHandler {
    public static int currentMaxDashes;
    public static int currentRemainingDashes;
    public static boolean canDash = false;
    public static boolean dashing = false;
    public static int currentDashTicks;
    public static int currentCooldownTicks;
    public static int currentRechargeTicks;
    public static int whiteHorseMaxDash = 4;
    public static int creamyHorseMaxDash = 3;
    public static int chestnutHorseMaxDash = 3;
    public static int brownHorseMaxDash = 2;
    public static int blackHorseMaxDash = 2;
    public static int grayHorseMaxDash = 1;
    public static int darkbrownHorseMaxDash = 1;
    public static int whiteHorseMarking = 2;
    public static int whiteFieldHorseMarking = 1;
    public static int blackDotsHorseMarking = 1;
    public static int noneHorseMarking = 1;
    public static int whiteDotsHorseMarking = 0;

    //Called by client and handler after hotkey and before dash
    public static boolean canContinue() {
        if(!dashing && currentCooldownTicks == 0 && currentRemainingDashes > 0) {
//        if(!dashing) {
            LOGGER.info("canDash: 1");
            return true;
        }
        LOGGER.info("canDash: 2");
        return false;
    }

    //Called by client after hotkey
    public static void startDash(HorseEntity horse) {
        LOGGER.info("startDash: 1");
        if(canContinue()) {
            LOGGER.info("startDash: 2");
            canDash = true;
            dashing = true;
            DashRenderer.setDashing(true);
        }
    }

    //Called by HorseEntityMixin
    public static void renderHUD() {
        DashRenderer.shouldRender = true;
//        LOGGER.info(String.valueOf(currentRechargeTicks));
        if(currentRechargeTicks == 1) {
            DashRenderer.iconAlpha = 0;
//            LOGGER.info("ok1");
        } else if (currentRechargeTicks > 7) {
            DashRenderer.iconAlpha = 0.35f - (0.8f * (float) currentRechargeTicks / 115);
//            LOGGER.info("ok2");
        }
        if(currentDashTicks == 0) {
            DashRenderer.setDashing(false);
        }

    }


    //Called by HorseEntityMixin
    public static int determineMaxDashes(HorseEntity horse, HorseColor color, HorseMarking marking) {
        int returnValue = 0;
//        LOGGER.info("maxDashes1");
        if(color == HorseColor.WHITE) {
//            returnValue = whiteHorseMaxDash = GiddyUpMain.
            returnValue = whiteHorseMaxDash = CONFIG.nestedMaxDashes.whiteHorseMaxDash();
        } else if (color == HorseColor.CREAMY) {
            returnValue = creamyHorseMaxDash = CONFIG.nestedMaxDashes.creamyHorseMaxDash();
        } else if (color == HorseColor.CHESTNUT) {
            returnValue = chestnutHorseMaxDash = CONFIG.nestedMaxDashes.chestnutHorseMaxDash();
        } else if (color == HorseColor.BROWN) {
            returnValue = brownHorseMaxDash = CONFIG.nestedMaxDashes.brownHorseMaxDash();
        } else if (color == HorseColor.BLACK) {
            returnValue = blackHorseMaxDash = CONFIG.nestedMaxDashes.blackHorseMaxDash();
        } else if (color == HorseColor.GRAY) {
            returnValue = grayHorseMaxDash = CONFIG.nestedMaxDashes.grayHorseMaxDash();
        } else if (color == HorseColor.DARK_BROWN) {
            returnValue = darkbrownHorseMaxDash = CONFIG.nestedMaxDashes.darkbrownHorseMaxDash();
        }

//        LOGGER.info("maxDashes2");
        if(marking == HorseMarking.WHITE) {
            returnValue += whiteHorseMarking = CONFIG.nestedMaxDashes.whiteHorseMarking();
        } else if(marking == HorseMarking.WHITE_FIELD) {
            returnValue += whiteFieldHorseMarking = CONFIG.nestedMaxDashes.whiteFieldHorseMarking();
        } else if(marking == HorseMarking.BLACK_DOTS) {
            returnValue += blackDotsHorseMarking = CONFIG.nestedMaxDashes.blackDotsHorseMarking();
        } else if(marking == HorseMarking.NONE) {
            returnValue += noneHorseMarking = CONFIG.nestedMaxDashes.noneHorseMarking();
        } else if (marking == HorseMarking.WHITE_DOTS) {
            returnValue += whiteDotsHorseMarking = CONFIG.nestedMaxDashes.whiteDotsHorseMarking();
        }
        //        LOGGER.info("maxDashes3");
        if(returnValue > 5) {
            returnValue = 5;
        }
        return returnValue;
    }

}
