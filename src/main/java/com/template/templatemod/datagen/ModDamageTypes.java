package com.template.templatemod.datagen;

import com.template.templatemod.TemplateMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.level.Level;

public class ModDamageTypes {

    public static final ResourceKey<DamageType> TEST_DAMAGE= ResourceKey.create(Registries.DAMAGE_TYPE,
            Identifier.fromNamespaceAndPath(TemplateMod.MOD_ID, "test_damage"));

    public static void bootstrap(BootstrapContext<DamageType> context) {
        context.register(TEST_DAMAGE,new DamageType("test damage",1.0f, DamageEffects.HURT));
    }

    public static DamageSource create(Level level, ResourceKey<DamageType> key) {
        return new DamageSource(level.registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE).getOrThrow(key));
    }
}
