package com.template.templatemod.sound;

import com.template.templatemod.TemplateMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, TemplateMod.MOD_ID);

    public static final Supplier<SoundEvent> CUBEMOB_HURT = registerSoundEvent("cubemob_hurt");




    private static Supplier<SoundEvent> registerSoundEvent(String name) {
        Identifier id = Identifier.fromNamespaceAndPath(TemplateMod.MOD_ID, name);
        return SOUND_EVENTS.register(name,()->SoundEvent.createVariableRangeEvent(id));
    }

    public static void register(IEventBus bus) {
        SOUND_EVENTS.register(bus);
    }
}
