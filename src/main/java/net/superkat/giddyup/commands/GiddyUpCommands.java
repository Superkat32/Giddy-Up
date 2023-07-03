package net.superkat.giddyup.commands;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.text.Text;
import net.superkat.giddyup.DashHandler;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class GiddyUpCommands {

    public static void register() {
        registerMaxDashesCommand();
    }
    public static void registerMaxDashesCommand() {
        CommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess, environment) ->
                dispatcher.register(literal("horseMaxDashes")
                        .requires(source -> source.hasPermissionLevel(4))
                        .then(literal("color")
                                .executes(context -> {
                                    context.getSource().sendMessage(Text.literal("Incorrect arguments! Please select a horse color!"));

                                    return 1;
                                })
                                .then(literal("WHITE")
                                        .executes(context -> {
                                            context.getSource().sendMessage(Text.literal("Current max dashes: " + DashHandler.whiteHorseMaxDash));

                                            return 1;
                                        }).then(argument("value", IntegerArgumentType.integer())
                                                .executes(context -> {
                                                    int value = IntegerArgumentType.getInteger(context, "value");

                                                    if(value > 5) {
                                                        value = 5;
                                                    }
                                                    DashHandler.whiteHorseMaxDash = value;
                                                    context.getSource().sendMessage(Text.literal("Max dashes updated to : " + value + "!"));
                                                    return 1;
                                                })))
                                .then(literal("CREAMY")
                                        .executes(context -> {
                                            context.getSource().sendMessage(Text.literal("Current max dashes: " + DashHandler.creamyHorseMaxDash));

                                            return 1;
                                        }).then(argument("value", IntegerArgumentType.integer())
                                                .executes(context -> {
                                                    int value = IntegerArgumentType.getInteger(context, "value");

                                                    if(value > 5) {
                                                        value = 5;
                                                    }
                                                    DashHandler.creamyHorseMaxDash = value;
                                                    context.getSource().sendMessage(Text.literal("Max dashes updated to : " + value + "!"));
                                                    return 1;
                                                })))
                                .then(literal("CHESTNUT")
                                        .executes(context -> {
                                            context.getSource().sendMessage(Text.literal("Current max dashes: " + DashHandler.chestnutHorseMaxDash));

                                            return 1;
                                        }).then(argument("value", IntegerArgumentType.integer())
                                                .executes(context -> {
                                                    int value = IntegerArgumentType.getInteger(context, "value");

                                                    if(value > 5) {
                                                        value = 5;
                                                    }
                                                    DashHandler.chestnutHorseMaxDash = value;
                                                    context.getSource().sendMessage(Text.literal("Max dashes updated to : " + value + "!"));
                                                    return 1;
                                                })))
                                .then(literal("BROWN")
                                        .executes(context -> {
                                            context.getSource().sendMessage(Text.literal("Current max dashes: " + DashHandler.brownHorseMaxDash));

                                            return 1;
                                        }).then(argument("value", IntegerArgumentType.integer())
                                                .executes(context -> {
                                                    int value = IntegerArgumentType.getInteger(context, "value");

                                                    if(value > 5) {
                                                        value = 5;
                                                    }
                                                    DashHandler.brownHorseMaxDash = value;
                                                    context.getSource().sendMessage(Text.literal("Max dashes updated to : " + value + "!"));
                                                    return 1;
                                                })))
                                .then(literal("BLACK")
                                        .executes(context -> {
                                            context.getSource().sendMessage(Text.literal("Current max dashes: " + DashHandler.blackHorseMaxDash));

                                            return 1;
                                        }).then(argument("value", IntegerArgumentType.integer())
                                                .executes(context -> {
                                                    int value = IntegerArgumentType.getInteger(context, "value");

                                                    if(value > 5) {
                                                        value = 5;
                                                    }
                                                    DashHandler.blackHorseMaxDash = value;
                                                    context.getSource().sendMessage(Text.literal("Max dashes updated to : " + value + "!"));
                                                    return 1;
                                                })))
                                .then(literal("GRAY")
                                        .executes(context -> {
                                            context.getSource().sendMessage(Text.literal("Current max dashes: " + DashHandler.grayHorseMaxDash));

                                            return 1;
                                        }).then(argument("value", IntegerArgumentType.integer())
                                                .executes(context -> {
                                                    int value = IntegerArgumentType.getInteger(context, "value");

                                                    if(value > 5) {
                                                        value = 5;
                                                    }
                                                    DashHandler.grayHorseMaxDash = value;
                                                    context.getSource().sendMessage(Text.literal("Max dashes updated to : " + value + "!"));
                                                    return 1;
                                                })))
                                .then(literal("DARK_BROWN")
                                        .executes(context -> {
                                            context.getSource().sendMessage(Text.literal("Current max dashes: " + DashHandler.darkbrownHorseMaxDash));

                                            return 1;
                                        }).then(argument("value", IntegerArgumentType.integer())
                                                .executes(context -> {
                                                    int value = IntegerArgumentType.getInteger(context, "value");

                                                    if(value > 5) {
                                                        value = 5;
                                                    }
                                                    DashHandler.darkbrownHorseMaxDash = value;
                                                    context.getSource().sendMessage(Text.literal("Max dashes updated to : " + value + "!"));
                                                    return 1;
                                                })))
                        )
                        .then(literal("marking")
                                .executes(context -> {
                                    context.getSource().sendMessage(Text.literal("Incorrect arguments! Please select a horse marking!"));

                                    return 1;
                                })
                                .then(literal("WHITE")
                                        .executes(context -> {
                                            context.getSource().sendMessage(Text.literal("Current additional max dashes: " + DashHandler.whiteHorseMarking));

                                            return 1;
                                        }).then(argument("value", IntegerArgumentType.integer())
                                                .executes(context -> {
                                                    int value = IntegerArgumentType.getInteger(context, "value");
                                                    if(value > 5) {
                                                        value = 5;
                                                    }
                                                    DashHandler.whiteHorseMarking = value;
                                                    context.getSource().sendMessage(Text.literal("Additional max dashes updated to : " + value + "!"));
                                                    return 1;
                                                })))
                                .then(literal("WHITE_FIELD")
                                        .executes(context -> {
                                            context.getSource().sendMessage(Text.literal("Current additional max dashes: " + DashHandler.whiteFieldHorseMarking));

                                            return 1;
                                        }).then(argument("value", IntegerArgumentType.integer())
                                                .executes(context -> {
                                                    int value = IntegerArgumentType.getInteger(context, "value");
                                                    if(value > 5) {
                                                        value = 5;
                                                    }
                                                    DashHandler.whiteFieldHorseMarking = value;
                                                    context.getSource().sendMessage(Text.literal("Additional max dashes updated to : " + value + "!"));
                                                    return 1;
                                                })))
                                .then(literal("BLACK_DOTS")
                                        .executes(context -> {
                                            context.getSource().sendMessage(Text.literal("Current additional max dashes: " + DashHandler.blackDotsHorseMarking));

                                            return 1;
                                        }).then(argument("value", IntegerArgumentType.integer())
                                                .executes(context -> {
                                                    int value = IntegerArgumentType.getInteger(context, "value");
                                                    if(value > 5) {
                                                        value = 5;
                                                    }
                                                    DashHandler.blackDotsHorseMarking = value;
                                                    context.getSource().sendMessage(Text.literal("Additional max dashes updated to : " + value + "!"));
                                                    return 1;
                                                })))
                                .then(literal("NONE")
                                        .executes(context -> {
                                            context.getSource().sendMessage(Text.literal("Current additional max dashes: " + DashHandler.noneHorseMarking));

                                            return 1;
                                        }).then(argument("value", IntegerArgumentType.integer())
                                                .executes(context -> {
                                                    int value = IntegerArgumentType.getInteger(context, "value");
                                                    if(value > 5) {
                                                        value = 5;
                                                    }
                                                    DashHandler.noneHorseMarking = value;
                                                    context.getSource().sendMessage(Text.literal("Additional max dashes updated to : " + value + "!"));
                                                    return 1;
                                                })))
                                .then(literal("WHITE_DOTS")
                                        .executes(context -> {
                                            context.getSource().sendMessage(Text.literal("Current additional max dashes: " + DashHandler.whiteDotsHorseMarking));

                                            return 1;
                                        }).then(argument("value", IntegerArgumentType.integer())
                                                .executes(context -> {
                                                    int value = IntegerArgumentType.getInteger(context, "value");
                                                    if(value > 5) {
                                                        value = 5;
                                                    }
                                                    DashHandler.whiteDotsHorseMarking = value;
                                                    context.getSource().sendMessage(Text.literal("Additional max dashes updated to : " + value + "!"));
                                                    return 1;
                                                })))
                        )
                        .then(literal("help")
                                .executes(context -> {
                                    context.getSource().sendMessage(Text.literal("Syntax: /horseMaxDashes [<color/marking>] [<COLOR/MARKING>] [<value>]"));
                                    context.getSource().sendMessage(Text.literal("Resetting: /horseMaxDashes [<reset>] [<color/marking/both>]"));
                                    context.getSource().sendMessage(Text.literal("You can change the amount of max dashes a horse has depending on its breed. The color and marking are factors that contribute to determining its breed. You can customize the number of max dashes added by each color or marking. The most max dashes a horse can have is limited to 5"));

                                    return 1;
                                })
                        )
                                .then(literal("reset")
                                        .executes(context -> {
                                            context.getSource().sendMessage(Text.literal("Incorrect arguments! Please choose which values to reset!"));

                                            return 1;
                                        }).then(literal("color")
                                                .executes(context -> {
                                                    DashHandler.whiteHorseMaxDash = 4;
                                                    DashHandler.creamyHorseMaxDash = 3;
                                                    DashHandler.chestnutHorseMaxDash = 3;
                                                    DashHandler.brownHorseMaxDash = 2;
                                                    DashHandler.blackHorseMaxDash = 2;
                                                    DashHandler.grayHorseMaxDash = 1;
                                                    DashHandler.darkbrownHorseMaxDash = 1;

                                                    context.getSource().sendMessage(Text.literal("Reset max dashes(colors)!"));

                                                    return 1;
                                                })
                                            )
                                        .then(literal("marking")
                                                .executes(context -> {
                                                    DashHandler.whiteHorseMarking = 2;
                                                    DashHandler.whiteFieldHorseMarking = 1;
                                                    DashHandler.blackDotsHorseMarking = 1;
                                                    DashHandler.noneHorseMarking = 1;
                                                    DashHandler.whiteDotsHorseMarking = 0;

                                                    context.getSource().sendMessage(Text.literal("Reset additional max dashes(markings)!"));

                                                    return 1;
                                                })
                                        ).then(literal("both")
                                                .executes(context -> {
                                                    DashHandler.whiteHorseMaxDash = 4;
                                                    DashHandler.creamyHorseMaxDash = 3;
                                                    DashHandler.chestnutHorseMaxDash = 3;
                                                    DashHandler.brownHorseMaxDash = 2;
                                                    DashHandler.blackHorseMaxDash = 2;
                                                    DashHandler.grayHorseMaxDash = 1;
                                                    DashHandler.darkbrownHorseMaxDash = 1;

                                                    DashHandler.whiteHorseMarking = 2;
                                                    DashHandler.whiteFieldHorseMarking = 1;
                                                    DashHandler.blackDotsHorseMarking = 1;
                                                    DashHandler.noneHorseMarking = 1;
                                                    DashHandler.whiteDotsHorseMarking = 0;

                                                    context.getSource().sendMessage(Text.literal("Reset max dashes!"));

                                                    return 1;
                                                })))
                        .executes(context -> {
//					context.getSource().sendMessage(() -> Text.literal("Called /foo with no arguments"));
                            context.getSource().sendMessage(Text.literal("Incorrect arguments!"));

                            return 1;
                        }))));
    }
}
