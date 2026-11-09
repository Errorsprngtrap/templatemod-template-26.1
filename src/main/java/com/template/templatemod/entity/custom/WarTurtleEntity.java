package com.template.templatemod.entity.custom;

import com.template.templatemod.TemplateMod;
import com.template.templatemod.entity.ModEntities;
import com.template.templatemod.item.Custom.WarTurtleArmor;
import com.template.templatemod.menu.custom.WarTurtleMenu;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.world.*;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerListener;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;


public class WarTurtleEntity extends TamableAnimal implements ContainerListener, HasCustomInventoryScreen , PlayerRideable{
    public final AnimationState sitAnimationState = new AnimationState();
    public final AnimationState sitPoseAnimationState = new AnimationState();
    public final AnimationState sitUpAnimationState = new AnimationState();

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public static final EntityDataAccessor<Long> LAST_POSE_CHANGE_TICK = SynchedEntityData.defineId(WarTurtleEntity.class, EntityDataSerializers.LONG);
    private static final EntityDataAccessor<Boolean> HAS_CHEST = SynchedEntityData.defineId(WarTurtleEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> HAS_CHEST_2 = SynchedEntityData.defineId(WarTurtleEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> HAS_CHEST_3 = SynchedEntityData.defineId(WarTurtleEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> HAS_SADDLE = SynchedEntityData.defineId(WarTurtleEntity.class, EntityDataSerializers.BOOLEAN);

    protected SimpleContainer inventory;
    private final int CHEST_SLOT = 2;
    private final int CHEST_SLOT_2 = 3;
    private final int CHEST_SLOT_3 = 4;

    public WarTurtleEntity(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
        this.createInventory();
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

        if (!this.level().isClientSide()) {
            if (itemStack.is(Items.PUMPKIN) && !this.isTame()) {
                itemStack.consume(1, player);
                this.tame(player);
                return InteractionResult.SUCCESS_SERVER;
            } else if (this.isTame() && isSaddled() && !this.isVehicle()) {
                player.startRiding(this);
                return InteractionResult.SUCCESS;
            }
        }

        if (isTame() && hand == InteractionHand.MAIN_HAND && !isFood(itemStack) && !player.isSecondaryUseActive() && !isSaddled()) {
            toggleSitting();
            return InteractionResult.SUCCESS;
        } else if (this.isTame()) {
            this.openCustomInventoryScreen(player);
            return InteractionResult.SUCCESS;
        }

        return super.mobInteract(player, hand);
    }

    //save and read data
    @Override
    protected void addAdditionalSaveData(ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.putLong("LastPoseTick", this.entityData.get(LAST_POSE_CHANGE_TICK));

        ValueOutput.TypedOutputList<ItemStackWithSlot> items = output.list("Items", ItemStackWithSlot.CODEC);
        for (int i = 0; i < this.inventory.getContainerSize(); i++) {
            ItemStack stack = this.inventory.getItem(i);
            if (!stack.isEmpty()) {
                items.add(new ItemStackWithSlot(i, stack));
            }
        }
    }

    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);
        long poseTick = input.getLongOr("LastPoseTick", 0L);
        if (poseTick < 0L) {
            this.setPose(Pose.SITTING);
        }

        this.resetLastPoseChangeTick(poseTick);

        //ibentory related
        this.createInventory();
        for (ItemStackWithSlot item : input.listOrEmpty("Items", ItemStackWithSlot.CODEC)) {
            if (item.isValidInContainer(this.inventory.getContainerSize())) {
                this.inventory.setItem(item.slot(), item.stack());
            }
        }

        // Ensures the server/client are synced!
        if (inventory.getItem(CHEST_SLOT).is(Items.CHEST) && !has1Chest()) {
            setChest(CHEST_SLOT, true);
        }
        if (inventory.getItem(CHEST_SLOT_2).is(Items.CHEST) && !has2Chest()) {
            setChest(CHEST_SLOT_2, true);
        }
        if (inventory.getItem(CHEST_SLOT_3).is(Items.CHEST) && !has3Chest()) {
            setChest(CHEST_SLOT_3, true);
        }

        if (inventory.getItem(1).is(Items.SADDLE) && !isSaddled()) {
            this.entityData.set(HAS_SADDLE, true);
        }
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder entityData) {
        super.defineSynchedData(entityData);
        entityData.define(LAST_POSE_CHANGE_TICK, 0L);
        entityData.define(HAS_CHEST, false);
        entityData.define(HAS_CHEST_2, false);
        entityData.define(HAS_CHEST_3, false);
        entityData.define(HAS_SADDLE, false);
    }

    //inventory shit
    private void dropChestInventory(int slot) {
        if (slot == CHEST_SLOT) {
            Containers.dropItemStack(this.level(), this.getX(), this.getY() + 1, this.getZ(), inventory.removeItem(5, 64));
            Containers.dropItemStack(this.level(), this.getX(), this.getY() + 1, this.getZ(), inventory.removeItem(6, 64));
            Containers.dropItemStack(this.level(), this.getX(), this.getY() + 1, this.getZ(), inventory.removeItem(7, 64));
            Containers.dropItemStack(this.level(), this.getX(), this.getY() + 1, this.getZ(), inventory.removeItem(8, 64));
        }

        if (slot == CHEST_SLOT_2) {
            Containers.dropItemStack(this.level(), this.getX(), this.getY() + 1, this.getZ(), inventory.removeItem(9, 64));
            Containers.dropItemStack(this.level(), this.getX(), this.getY() + 1, this.getZ(), inventory.removeItem(10, 64));
            Containers.dropItemStack(this.level(), this.getX(), this.getY() + 1, this.getZ(), inventory.removeItem(11, 64));
            Containers.dropItemStack(this.level(), this.getX(), this.getY() + 1, this.getZ(), inventory.removeItem(12, 64));
        }

        if (slot == CHEST_SLOT_3) {
            Containers.dropItemStack(this.level(), this.getX(), this.getY() + 1, this.getZ(), inventory.removeItem(13, 64));
            Containers.dropItemStack(this.level(), this.getX(), this.getY() + 1, this.getZ(), inventory.removeItem(14, 64));
            Containers.dropItemStack(this.level(), this.getX(), this.getY() + 1, this.getZ(), inventory.removeItem(15, 64));
            Containers.dropItemStack(this.level(), this.getX(), this.getY() + 1, this.getZ(), inventory.removeItem(16, 64));
        }
    }

    public void setChest(int slot,boolean chested){
        if (slot == CHEST_SLOT) {
            this.entityData.set(HAS_CHEST, chested);
        } else if (slot == CHEST_SLOT_2) {
            this.entityData.set(HAS_CHEST_2, chested);
        }else if (slot == CHEST_SLOT_3) {
            this.entityData.set(HAS_CHEST_3, chested);
        }else {
            TemplateMod.LOGGER.error("Cant give chest to slot that doesnt exist");
        }
    }

    public boolean has1Chest() {
        return this.entityData.get(HAS_CHEST);
    }

    public boolean has2Chest() {
        return this.entityData.get(HAS_CHEST_2);
    }

    public boolean has3Chest() {
        return this.entityData.get(HAS_CHEST_3);
    }

    public final int getInventorySize() {
        return getInventorySize(4);
    }

    protected void createInventory() {
        SimpleContainer simpleContainer = this.inventory;
        this.inventory = new SimpleContainer(this.getInventorySize());
        if (simpleContainer != null) {
            int max = Math.min(simpleContainer.getContainerSize(), this.inventory.getContainerSize());

            for (int slot = 0; slot < max; slot++) {
                ItemStack itemStack = simpleContainer.getItem(slot);
                if (!itemStack.isEmpty()) {
                    this.inventory.setItem(slot, itemStack.copy());
                }
            }
        }
    }

    public boolean hasInventoryChanged(Container oldInventory) {
        return this.inventory != oldInventory;
    }

    public static int getInventorySize(int columns) {
        return columns * 3 + 5;
    }

    @Override
    public void openCustomInventoryScreen(Player player) {
        if (!this.level().isClientSide() && isTame()) {
            ServerPlayer serverPlayer = (ServerPlayer) player;
            if (serverPlayer.containerMenu != serverPlayer.inventoryMenu) {
                serverPlayer.closeContainer();
            }

            serverPlayer.openMenu(new SimpleMenuProvider((ix, playerInventory, playerEntity) -> {
                AbstractContainerMenu menu = new WarTurtleMenu(ix, playerInventory, this.inventory, this, 4);
                menu.addSlotListener(WarTurtleEntity.this);
                return menu;
            }, this.getDisplayName()), buf -> {
                buf.writeUUID(getUUID());
            });
        }
    }

    @Override
    protected void dropEquipment(ServerLevel level) {
        super.dropEquipment(level);
        Containers.dropContents(level(),this.blockPosition().above(1),inventory);
    }

    @Override
    public void slotChanged(AbstractContainerMenu container, int slotIndex, ItemStack itemStack) {
        if (container.getSlot(CHEST_SLOT).getItem().is(Items.CHEST) && !has1Chest()) {
            setChest(CHEST_SLOT, true);
        }
        if (container.getSlot(CHEST_SLOT_2).getItem().is(Items.CHEST) && !has2Chest()) {
            setChest(CHEST_SLOT_2, true);
        }
        if (container.getSlot(CHEST_SLOT_3).getItem().is(Items.CHEST) && !has3Chest()) {
            setChest(CHEST_SLOT_3, true);
        }

        if (!container.getSlot(CHEST_SLOT).getItem().is(Items.CHEST) && has1Chest()) {
            setChest(CHEST_SLOT, false);
            dropChestInventory(CHEST_SLOT);
        }
        if (!container.getSlot(CHEST_SLOT_2).getItem().is(Items.CHEST) && has2Chest()) {
            setChest(CHEST_SLOT_2, false);
            dropChestInventory(CHEST_SLOT_2);
        }
        if (!container.getSlot(CHEST_SLOT_3).getItem().is(Items.CHEST) && has3Chest()) {
            setChest(CHEST_SLOT_3, false);
            dropChestInventory(CHEST_SLOT_3);
        }

        if (container.getSlot(0).getItem().getItem() instanceof WarTurtleArmor){
            setBodyArmorItem(container.getSlot(0).getItem());
        }

        if (container.getSlot(0).getItem().isEmpty() && isWearingBodyArmor()){
            setBodyArmorItem(ItemStack.EMPTY);
        }

        if (container.getSlot(1).getItem().is(Items.SADDLE) && !isSaddled()){
            this.entityData.set(HAS_SADDLE, true);
        }

        if (container.getSlot(1).getItem().isEmpty() && isSaddled()){
            this.entityData.set(HAS_SADDLE, false);
        }
    }

    @Override
    public void dataChanged(AbstractContainerMenu container, int id, int value) {

    }

    //Armor
    public boolean hasArmor(){
        return isWearingBodyArmor();
    }

    @Override
    public boolean isWearingBodyArmor() {
        return this.inventory.getItem(0).getItem() instanceof WarTurtleArmor;
    }

    @Override
    protected void actuallyHurt(ServerLevel level, DamageSource source, float dmg) {
        if (!this.canArmorAbsorb(source)) {
            super.actuallyHurt(level, source, dmg);
        } else {
            ItemStack armorBefore = this.getBodyArmorItem();
            armorBefore.hurtAndBreak(Mth.ceil(dmg), this, EquipmentSlot.BODY);

            if(armorBefore.getItem() instanceof WarTurtleArmor){
                int damageRed = 2;
                super.actuallyHurt(level, source, Math.max(0,dmg-damageRed));
            }
        }
    }

    private boolean canArmorAbsorb(DamageSource source) {
        return hasArmor() && !source.is(DamageTypeTags.BYPASSES_WOLF_ARMOR);
    }

    //all Saddle BS

    @Override
    public boolean isSaddled() {
        return this.entityData.get(HAS_SADDLE);
    }

    @Override
    public @Nullable LivingEntity getControllingPassenger() {
        return (LivingEntity)(this.isSaddled() && this.getFirstPassenger() instanceof Player player ? player : super.getControllingPassenger());
    }

    @Override
    protected void tickRidden(Player controller, Vec3 riddenInput) {
        this.setRot(controller.getYRot(), controller.getXRot() * 0.5F);
        this.yRotO = this.yBodyRot = this.yHeadRot = this.getYRot();
        super.tickRidden(controller, riddenInput);
    }

    @Override
    protected Vec3 getRiddenInput(Player controller, Vec3 selfInput) {
        float sideways = controller.xxa * 0.5F;
        float forward = controller.zza;
        return new Vec3(sideways, 0.0, forward);
    }

    @Override
    protected float getRiddenSpeed(Player controller) {
        return (float)(this.getAttributeValue(Attributes.MOVEMENT_SPEED));
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(Entity passenger, EntityDimensions dimensions, float scale) {
        if (!this.level().isClientSide()) {
            return super.getPassengerAttachmentPoint(passenger, dimensions, scale);
        } else {
            float animSpeed = Math.min(0.25F, this.walkAnimation.speed());
            float animPos = this.walkAnimation.position();
            float offset = 0.12F * Mth.cos(animPos * 1.5F) * 2.0F * animSpeed;
            return super.getPassengerAttachmentPoint(passenger, dimensions, scale).add(0.0, offset * scale, 0.0);
        }
    }
}
