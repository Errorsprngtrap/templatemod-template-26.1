package com.template.templatemod.effect;

import com.template.templatemod.TemplateMod;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, TemplateMod.MOD_ID);

    public static final Holder<MobEffect> TEST_EFFECT = MOB_EFFECTS.register("test_effect",
            () -> new ExampleEffect(MobEffectCategory.NEUTRAL, 0xffffff)
    );

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
