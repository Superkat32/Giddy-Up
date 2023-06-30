package net.superkat.giddyup;

import net.minecraft.entity.passive.HorseColor;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.entity.passive.HorseMarking;

import static net.superkat.giddyup.GiddyUpMain.LOGGER;

public class DashHandler {
    public static int currentMaxDashes;
    public static int currentRemainingDashes;
    public static boolean canDash = false;
    public static boolean dashing = false;
    public static int currentDashTicks;
    public static int currentCooldownTicks;
    public static int currentRechargeTicks;

    public static void test(HorseEntity horse) {
        LOGGER.info("yay");
        if(horse != null) {
            LOGGER.info("YAY");
        }
    }

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
//            determineMaxDashes(horse, horse.getVariant(), horse.getMarking());
//            dashing = true;
//            dashTicks = dashDuration;
//            cooldownTicks = dashCooldown;
//            rechargeTicks = dashRecharge;
//            remainingDashes--;
//            addDashBoost(horse);
        }
    }

    //Called by HorseEntityMixin
//    public static void tick(HorseEntity horse) {
//        if(horse == null) {
//            return;
//        }
//        LOGGER.info("cooldownTicks: " + cooldownTicks);
//        LOGGER.info("dashTicks: " + dashTicks);
//        LOGGER.info("dashes: " + remainingDashes);
//        LOGGER.info("Max dashes: " + maxDashes);
//        if(cooldownTicks > 0) {
//            --cooldownTicks;
//            --dashTicks;
//            if(dashTicks <= 0) {
//                removeDashBoost(horse);
//                dashing = false;
//            }
//        }

//        LOGGER.info("rechargeTicks: " + rechargeTicks);
//        if(rechargeTicks > 0) {
//            --rechargeTicks;
//            if(rechargeTicks == 0) {
//                if(remainingDashes == maxDashes) {
//                    return;
//                }
//                remainingDashes++;
//                rechargeTicks = dashRecharge;
//            }
//        }
//    }

    public static void renderHUD() {
        DashRenderer.shouldRender = true;
//        LOGGER.info(String.valueOf(currentRechargeTicks));
        if(currentRechargeTicks == 1) {
            DashRenderer.iconAlpha = 0;
//            LOGGER.info("ok1");
        } else if (currentRechargeTicks > 7) {
            DashRenderer.iconAlpha = 0.35f - (0.8f * (float) currentRechargeTicks / 115);
//            DashRenderer.iconAlpha += 0.0022;
//            LOGGER.info("ok2");
        }
        if(currentDashTicks == 0) {
            DashRenderer.setDashing(false);
        }

    }

//    public static void addDashBoost(HorseEntity horse) {
//        EntityAttributeInstance speed = horse.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
//        if(speed == null) {
//            return;
//        }
//
//        LOGGER.info("addDashBoost");
//        if(speed.getModifier(HORSE_DASH_ID) != null) {
//            return;
//        }
//        speed.addTemporaryModifier(new EntityAttributeModifier(HORSE_DASH_ID, "Horse dash speed boost", 0.23, EntityAttributeModifier.Operation.ADDITION));
//    }

//    public static void removeDashBoost(HorseEntity horse) {
//        EntityAttributeInstance speed = horse.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
//        LOGGER.info("removeDashBoost: 1");
//        if(speed != null) {
//            if(speed.getModifier(HORSE_DASH_ID) != null) {
//                LOGGER.info("removeDashBoost: 2");
//                speed.removeModifier(HORSE_DASH_ID);
//            }
//        }
//    }

    //Called by HorseEntityMixin
    public static int determineMaxDashes(HorseEntity horse, HorseColor color, HorseMarking marking) {
        int returnValue = 0;
//        LOGGER.info("maxDashes1");
        if(color == HorseColor.WHITE) {
            returnValue = 5;
        } else if (color == HorseColor.CREAMY) {
            returnValue = 4;
        } else if (color == HorseColor.CHESTNUT) {
            returnValue = 4;
        } else if (color == HorseColor.BROWN) {
            returnValue = 3;
        } else if (color == HorseColor.BLACK) {
            returnValue = 3;
        } else if (color == HorseColor.GRAY) {
            returnValue = 2;
        } else if (color == HorseColor.DARK_BROWN) {
            returnValue = 1;
        }

//        LOGGER.info("maxDashes2");
        if(returnValue != 5) {
            if(marking == HorseMarking.WHITE) {
                if(returnValue + 2 > 5) {
                    returnValue = 5;
                } else {
                    returnValue += 2;
                }
            } else if(marking == HorseMarking.WHITE_FIELD) {
                returnValue += 1;
            } else if(marking == HorseMarking.BLACK_DOTS) {
                returnValue += 1;
            } else if(marking == HorseMarking.NONE) {
                returnValue += 1;
            }
        }
//        LOGGER.info("maxDashes3");
        return returnValue;
    }

}