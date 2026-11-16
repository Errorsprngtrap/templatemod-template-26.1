package com.template.templatemod.entity.custom;

import com.template.templatemod.entity.ModEntities;
import com.template.templatemod.item.Custom.RockItem;
import com.template.templatemod.item.ModItems;
import com.template.templatemod.particles.ModParticles;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class RockProjectileEntity extends AbstractArrow {


    public RockProjectileEntity(EntityType<? extends AbstractArrow> type, Level level) {
        super(type, level);
    }

    public RockProjectileEntity(ServerLevel level, LivingEntity shooter,ItemStack itemStack) {
        super(ModEntities.ROCK.get(), shooter,level,new ItemStack(ModItems.ROCK.get(),1),null);
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return new ItemStack(ModItems.ROCK.get(),1) ;
    }

    @Override
    protected double getDefaultGravity() {
        return 0.0;
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        Level level = level();

        if (!level.isClientSide()) {
            ((ServerLevel) level).sendParticles(ModParticles.TEST_PARTICLE.get(),
                    result.getLocation().x + .5,result.getLocation().y + .5,result.getLocation().z + .5,
                    10,.1,.1,.1,1);
            discard();
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult hitResult) {
        super.onHitEntity(hitResult);
        Level level = level();
        if (!level().isClientSide()) {
            ((ServerLevel) level).sendParticles(ModParticles.TEST_PARTICLE.get(),
                    hitResult.getLocation().x + .5,hitResult.getLocation().y + .5,hitResult.getLocation().z + .5,
                    10,.1,.1,.1,1);
            discard();
        }
    }


}
