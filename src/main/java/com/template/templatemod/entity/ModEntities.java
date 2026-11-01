package com.template.templatemod.entity;

import com.template.templatemod.TemplateMod;
import com.template.templatemod.entity.custom.CubeMobEntity;
import com.template.templatemod.entity.custom.PuffBallEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.createEntities(TemplateMod.MOD_ID);

    public static final ResourceKey<EntityType<?>> CUBEMOB_KEY = ResourceKey.create(Registries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(TemplateMod.MOD_ID, "cubemob"));

    public static final ResourceKey<EntityType<?>> PUFFBALL_KEY = ResourceKey.create(Registries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(TemplateMod.MOD_ID, "puffball"));

    public static final ResourceKey<EntityType<?>> WAR_TURTLE_KEY = ResourceKey.create(Registries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(TemplateMod.MOD_ID, "war_turtle_key"));

    public static final Supplier<EntityType<CubeMobEntity>> CUBEMOB = ENTITY_TYPES.register("cubemob",
            ()-> EntityType.Builder.of(CubeMobEntity::new, MobCategory.CREATURE).sized(1,1).build(CUBEMOB_KEY));

    public static final Supplier<EntityType<PuffBallEntity>> PUFFBALL = ENTITY_TYPES.register("puffball",
            ()-> EntityType.Builder.of(PuffBallEntity::new, MobCategory.CREATURE).sized(1,1).build(PUFFBALL_KEY));

    public static final Supplier<EntityType<PuffBallEntity>> WAR_TURTLE = ENTITY_TYPES.register("war_turtle",
            ()-> EntityType.Builder.of(PuffBallEntity::new, MobCategory.CREATURE).sized(1,1).build(WAR_TURTLE_KEY));

    public static void register(IEventBus bus) {
        ENTITY_TYPES.register(bus);
    }

}
