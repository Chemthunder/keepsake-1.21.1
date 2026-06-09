package com.peak.keepsake.cca.entity;

import com.peak.keepsake.core.Keepsake;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.GameMode;
import net.minecraft.world.World;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;

public class KeepsakeComponent implements AutoSyncedComponent {
    public static final ComponentKey<KeepsakeComponent> KEY = ComponentRegistry.getOrCreate(
            Keepsake.id("keepsake"),
            KeepsakeComponent.class
    );
    private final PlayerEntity player;

    /// WISP
    private boolean wisp = true;
    private int wispColor = 0xFFffffff;

    /// COMBAT
    private boolean combatState = true;

    public KeepsakeComponent(PlayerEntity player) {
        this.player = player;
    }

    public void sync() {
        KEY.sync(this.player);
    }

    public static KeepsakeComponent clientIns() {
        return KEY.get(MinecraftClient.getInstance().player);
    }

    public void becomeWisp(PlayerEntity player) {
        World world = player.getWorld();

        if (player instanceof ServerPlayerEntity serverPlayer) {
            serverPlayer.changeGameMode(GameMode.SPECTATOR);
        }
    }

    public void readFromNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup wrapperLookup) {
        this.wisp = nbt.getBoolean("Wisp");
        this.wispColor = nbt.getInt("WispColor");

        this.combatState = nbt.getBoolean("CombatState");
    }

    public void writeToNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup wrapperLookup) {
        nbt.putBoolean("Wisp", this.wisp);
        nbt.putInt("WispColor", this.wispColor);

        nbt.putBoolean("CombatState", this.combatState);
    }

    public boolean isWisp() {
        return this.wisp;
    }

    public void setWisp(boolean wisp) {
        this.wisp = wisp;
        this.sync();
    }

    public int getWispColor() {
        return this.wispColor;
    }

    public void setWispColor(int wispColor) {
        this.wispColor = wispColor;
        this.sync();
    }

    public boolean isInCombatState() {
        return this.combatState;
    }

    public void setCombatState(boolean combatState) {
        this.combatState = combatState;
        this.sync();
    }
}

/*
 * Keeps track of Wisp state.
 * If in combat state, unable to attack others or be attacked, along with unable to restore health
 */