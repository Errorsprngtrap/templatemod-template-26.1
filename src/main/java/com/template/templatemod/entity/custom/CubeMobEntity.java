package com.template.templatemod.entity.custom;

import com.template.templatemod.entity.variant.CubeMobVariant;
import com.template.templatemod.sound.ModSounds;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Util;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.Nullable;

public class CubeMobEntity extends PathfinderMob {
    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(CubeMobEntity.class, EntityDataSerializers.INT);

    //Idle Anim
    public static AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public CubeMobEntity(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        //this determine ai of the mob
        goalSelector.addGoal(0,new FloatGoal(this));
        goalSelector.addGoal(1,new PanicGoal(this,1.5f));
        goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        goalSelector.addGoal(3,new OpenDoorGoal(this,true));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0F));
        super.registerGoals();
    }

    public static AttributeSupplier.Builder createCubeMobAttributes() {
        return PathfinderMob.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 10.0F)
                .add(Attributes.MOVEMENT_SPEED, 0.23F)
                .add(Attributes.FOLLOW_RANGE, 10.0F);
    }

    @Override
    public void tick() {
        super.tick();
        if(this.level().isClientSide()){
            this.setupAnimationStates();
        }
    }

    private void setupAnimationStates() {
        if(this.idleAnimationTimeout <= 0){
            this.idleAnimationTimeout = 30;
            this.idleAnimationState.start(this.tickCount);
        }else{
            this.idleAnimationTimeout--;
        }
    }

    //Variants

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder entityData) {
        super.defineSynchedData(entityData);
        entityData.define(VARIANT, 0);
    }

    private int getTypeVariant(){
        return this.entityData.get(VARIANT);
    }

    public CubeMobVariant getVariant(){
        return CubeMobVariant.getById(this.getTypeVariant() & 255);
    }

    private void setVariant(CubeMobVariant variant){
        this.entityData.set(VARIANT, variant.getID() & 255);
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.putInt("Variant", this.getTypeVariant());
    }

    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);
        this.entityData.set(VARIANT, input.getIntOr("Variant",0));
    }

    @Override
    public @Nullable SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, EntitySpawnReason spawnReason, @Nullable SpawnGroupData groupData) {
        CubeMobVariant variant = Util.getRandom(CubeMobVariant.values(),this.random);
        this.setVariant(variant);
        return super.finalizeSpawn(level, difficulty, spawnReason, groupData);
    }

    //Sounds


    @Override
    protected @Nullable SoundEvent getAmbientSound() {
        return SoundEvents.ZOMBIE_AMBIENT;
    }

    @Override
    protected @Nullable SoundEvent getHurtSound(DamageSource source) {
        return ModSounds.CUBEMOB_HURT.get();
    }

    @Override
    protected @Nullable SoundEvent getDeathSound() {
        return super.getDeathSound();
    }
}
