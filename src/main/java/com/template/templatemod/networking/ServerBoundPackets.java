package com.template.templatemod.networking;

import com.template.templatemod.networking.packet.TestPacketC2S;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class ServerBoundPackets {
    public static void handleTestPacket(TestPacketC2S testPacketC2S, IPayloadContext iPayloadContext) {
        Player player = iPayloadContext.player();
        ServerLevel level = ((ServerLevel) player.level());

        EntityType.RABBIT.spawn(level,player.getOnPos(), EntitySpawnReason.TRIGGERED);
        player.sendSystemMessage(Component.literal(testPacketC2S.name() + "thing: " + testPacketC2S.value()));
    }
}
