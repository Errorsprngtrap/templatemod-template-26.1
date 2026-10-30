package com.template.templatemod.datagen;

import com.template.templatemod.entity.ModEntities;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.stream.Stream;

public class ModEntitiesLootTableProvider extends EntityLootSubProvider {
    public ModEntitiesLootTableProvider(HolderLookup.Provider registries) {
        super(FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    public void generate() {
        add(ModEntities.CUBEMOB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(Items.MAGMA_BLOCK)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries,UniformGenerator.between(0.0F, 1.0F)))
                                .when(LootItemKilledByPlayerCondition.killedByPlayer()))));
    }

    @Override
    protected Stream<EntityType<?>> getKnownEntityTypes() {
        return ModEntities.ENTITY_TYPES.getEntries().stream().map(Holder::value);
    }
}
