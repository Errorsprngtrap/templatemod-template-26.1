package com.template.templatemod.entity.custom;

import com.template.templatemod.entity.ModEntities;
import com.template.templatemod.item.Custom.RockItem;
import com.template.templatemod.item.ModItems;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class RockProjectileEntity extends AbstractArrow {


    public RockProjectileEntity(EntityType<? extends AbstractArrow> type, Level level) {
        super(type, level);
    }

    public RockProjectileEntity(ServerLevel level, LivingEntity shooter,ItemStack itemStack) {
        super(ModEntities.ROCK.get(), shooter,level,new ItemStack(ModItems.ROCK.get(),1),null);
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return null;
    }


}
