package com.template.templatemod.entity.client;

import com.template.templatemod.entity.variant.CubeMobVariant;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.AnimationState;

public class CubeMobRenderState extends LivingEntityRenderState {
    public final AnimationState idleAnimationState = new AnimationState();
    public CubeMobVariant variant;
}
