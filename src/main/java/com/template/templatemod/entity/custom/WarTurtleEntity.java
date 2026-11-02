package com.template.templatemod.entity.custom;

import com.template.templatemod.entity.ModEntities;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.Nullable;

public class WarTurtleEntity extends TamableAnimal {
    public final AnimationState sitAnimationState = new AnimationState();
    public final AnimationState sitPoseAnimationState = new AnimationState();
    public final AnimationState sitUpAnimationState = new AnimationState();

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public static final EntityDataAccessor<Long> LAST_POSE_CHANGE_TICK = SynchedEntityData.defineId(WarTurtleEntity.class, EntityDataSerializers.LONG);

    public WarTurtleEntity(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
    }

    //goals
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));

        this.goalSelector.addGoal(1, new SitWhenOrderedToGoal(this));

        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this,1.0));
        this.goalSelector.addGoal(3, new FollowOwnerGoal(this, 1.0, 10.0F, 2.0F));
        this.goalSelector.addGoal(4, new BreedGoal(this, 1.0D));
        super.registerGoals();
    }

    public static AttributeSupplier.Builder createWarTurtleAttributes() {
        return PathfinderMob.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 10.0F)
                .add(Attributes.MOVEMENT_SPEED, 0.23F)
                .add(Attributes.FOLLOW_RANGE, 10.0F)
                .add(Attributes.TEMPT_RANGE, 10.0F);
    }

    @Override
    public void tick() {
        super.tick();
        if(this.level().isClientSide()){
            this.setupAnimationStates();
        }
    }


    private boolean shouldPlaySittingTransitionAnimation() {
        return this.isSitting() && this.getLastPoseTickDelta() < 40L && this.getLastPoseTickDelta() >= 0L;
    }

    public boolean shouldUpdateSittingAnimations() {
        return this.getLastPoseTickDelta() < 0L != this.isSitting();
    }

    public long getLastPoseTickDelta() {
        long x = this.level().getGameTime() - Math.abs(this.entityData.get(LAST_POSE_CHANGE_TICK));
        return this.level().getGameTime() - Math.abs(this.entityData.get(LAST_POSE_CHANGE_TICK));
    }

    public boolean isChangingPose() {
        long l = this.getLastPoseTickDelta();
        return l < (long) (this.isSitting() ? 40 : 52);
    }

    public void setLastPoseTick(long lastPoseTick) {
        this.entityData.set(LAST_POSE_CHANGE_TICK, lastPoseTick);
    }

    //animation
    public void resetLastPoseChangeTick(long syncedPoseTickTime) {
        this.entityData.set(LAST_POSE_CHANGE_TICK, syncedPoseTickTime);
    }

    private void setupAnimationStates() {
        if(this.idleAnimationTimeout <= 0){
            this.idleAnimationTimeout = 20;
            this.idleAnimationState.start(this.tickCount);
        }else{
            this.idleAnimationTimeout--;
        }

        if (this.shouldUpdateSittingAnimations()) {
            this.sitUpAnimationState.stop();
            if (this.shouldPlaySittingTransitionAnimation()) {
                this.sitAnimationState.startIfStopped(this.tickCount);
                this.sitPoseAnimationState.stop();
            } else {
                this.sitAnimationState.stop();
                this.sitPoseAnimationState.startIfStopped(this.tickCount);
            }
        } else {
            this.sitAnimationState.stop();
            this.sitPoseAnimationState.stop();
            this.sitUpAnimationState.animateWhen(this.isChangingPose() && this.getLastPoseTickDelta() >= 0L, this.tickCount);
        }

    }

    //sitting
    public void toggleSitting() {
        if (this.isSitting()) {
            standUp();
        }else{
            sitDown();
        }
    }
    public boolean isSitting() {
        return this.entityData.get(LAST_POSE_CHANGE_TICK) < 0L;
    }

    public void sitDown() {
        if (!this.isSitting()) {
            this.playSound(SoundEvents.CAMEL_SIT);
            this.setPose(Pose.SITTING);
            this.gameEvent(GameEvent.ENTITY_ACTION);
            this.resetLastPoseChangeTick(-this.level().getGameTime());

            setInSittingPose(true);
            setOrderedToSit(true);
        }
    }

    public void standUp() {
        if (this.isSitting()) {
            this.playSound(SoundEvents.CAMEL_STAND);
            this.setPose(Pose.STANDING);
            this.gameEvent(GameEvent.ENTITY_ACTION);
            this.resetLastPoseChangeTick(this.level().getGameTime());

            setInSittingPose(false);
            setOrderedToSit(false);
        }
    }

    //breeding thing
    @Override
    public boolean isFood(ItemStack itemStack) {
        return itemStack.is(Items.CARVED_PUMPKIN);
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel level, AgeableMob partner) {
        return ModEntities.WAR_TURTLE.get().create(level, EntitySpawnReason.BREEDING);
    }

    //right click

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        if (!this.level().isClientSide() && itemStack.is(Items.PUMPKIN) && !this.isTame()) {
            itemStack.consume(1, player);
            this.tame(player);
            return InteractionResult.SUCCESS_SERVER;
        }

        if (isTame() && hand == InteractionHand.MAIN_HAND && !isFood(itemStack) && !player.isSecondaryUseActive()) {
            toggleSitting();
            return InteractionResult.SUCCESS;
        }

        return super.mobInteract(player, hand);
    }

    //save and read data
    @Override
    protected void addAdditionalSaveData(ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.putLong("LastPoseTick", this.entityData.get(LAST_POSE_CHANGE_TICK));
    }

    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);
        long poseTick = input.getLongOr("LastPoseTick", 0L);
        if (poseTick < 0L) {
            this.setPose(Pose.SITTING);
        }

        this.resetLastPoseChangeTick(poseTick);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder entityData) {
        super.defineSynchedData(entityData);
        entityData.define(LAST_POSE_CHANGE_TICK, 0L);
    }
}
