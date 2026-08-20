package com.template.templatemod.attachmenttype;

import com.template.templatemod.TemplateMod;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ModAttachmentType {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPE =
            DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, TemplateMod.MOD_ID);

//    public static final Supplier<AttachmentType<Integer>> NEG_WATERMELON_PLR_COUNT_WATER = ATTACHMENT_TYPE.register(
//            "neg_watermelon_plr_count_water",
//            () -> AttachmentType.builder(()-> 0).sync(ByteBufCodecs.INT)
//                    .serialize(Codec.INT.fieldOf("neg_watermelon_plr_count")).build()
//    );


    public static void register(IEventBus eventBus)
    {
        ATTACHMENT_TYPE.register(eventBus);
    }
}
