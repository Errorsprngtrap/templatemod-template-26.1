package com.template.templatemod.networking.packet;

import com.template.templatemod.TemplateMod;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

public record TestPacketC2S(String name, int value) implements CustomPacketPayload {
    public static final Type<TestPacketC2S> TYPE = new Type<>(Identifier.fromNamespaceAndPath(TemplateMod.MOD_ID, "test_packet_c2s"));
    public static final StreamCodec<ByteBuf,TestPacketC2S> STREAM_CODEC = StreamCodec.composite(
            //Name part
            ByteBufCodecs.STRING_UTF8,
            TestPacketC2S::name,

            //Value
            ByteBufCodecs.VAR_INT,
            TestPacketC2S::value,

            TestPacketC2S::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
