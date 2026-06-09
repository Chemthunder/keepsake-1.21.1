package com.peak.keepsake.core.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class KeepsakeCommand implements CommandRegistrationCallback {
    public void register(CommandDispatcher<ServerCommandSource> dispatch, CommandRegistryAccess access, CommandManager.RegistrationEnvironment registrationEnvironment) {
        dispatch.register(literal("keepsake")
                .then(literal("clearArrows").executes(context -> {
                    context.getSource().getPlayerOrThrow().setStuckArrowCount(0);
                    return Command.SINGLE_SUCCESS;
                }))

                .then(literal("combatState").then(argument("state", BoolArgumentType.bool()).executes(context -> {
                    return Command.SINGLE_SUCCESS;
                })))

                .then(literal("wisp")
                        .then(literal("color").then(argument("hex", IntegerArgumentType.integer()).executes(context -> {
                            return Command.SINGLE_SUCCESS;
                        })))
                )
        );
    }

    public static int convertToHex(String hexString) {
        hexString = hexString.replace("#", "");
        return Integer.parseInt(hexString, 16);
    }
}
