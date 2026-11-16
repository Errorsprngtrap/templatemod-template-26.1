package com.template.templatemod.particles;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;
import org.jspecify.annotations.Nullable;

public class TestParticle extends SingleQuadParticle {

    public TestParticle(ClientLevel level, double x, double y, double z,
                        double xspd, double yspd, double zspd, SpriteSet spriteset) {
        super(level, x, y, z, xspd, yspd, zspd, spriteset.first());

        this.setSpriteFromAge(spriteset);
        this.lifetime = 120;


    }

    @Override
    protected Layer getLayer() {
        return Layer.OPAQUE;
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {

        private final SpriteSet spriteset;

        public Provider(SpriteSet spriteset) {
            this.spriteset = spriteset;
        }

        @Override
        public @Nullable Particle createParticle(SimpleParticleType options, ClientLevel level,
                                                 double x, double y, double z,
                                                 double xSpeed, double ySpeed, double zSpeed, RandomSource random) {
            return new TestParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteset);
        }
    }
}
