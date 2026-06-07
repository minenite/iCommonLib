package me.isaiah.common.mixin.R1_20;

import org.spongepowered.asm.mixin.Mixin;

import com.mojang.authlib.GameProfile;

import me.isaiah.common.cmixin.IMixinPlayerManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;

@Mixin(PlayerList.class)
public class MixinPlayerManager implements IMixinPlayerManager {

    @Override
    public ServerPlayer InewPlayer(MinecraftServer server, ServerLevel world, GameProfile profile) {	
        return new ServerPlayer(server, world, profile);
    }

}