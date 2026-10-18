package com.immersiveaudio.client;

import com.immersiveaudio.ModSounds;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

/** Una respiración de máscara de gas que sigue a la entidad y se corta sola si deja de llevarla puesta. */
public class GasMaskBreathSound extends AbstractTickableSoundInstance {

    private final LivingEntity entity;
    public final long createdAt;

    public GasMaskBreathSound(LivingEntity entity, long gameTime) {
        super(ModSounds.GAS_BREATH.get(),
                entity instanceof Player ? SoundSource.PLAYERS : SoundSource.NEUTRAL,
                entity.getRandom());
        this.entity = entity;
        this.createdAt = gameTime;
        this.looping = false;   // al terminar, el manager lanza otra variante al azar
        this.delay = 0;
        this.volume = 0.7F;
        this.pitch = 1.0F;
        this.x = entity.getX();
        this.y = entity.getEyeY();
        this.z = entity.getZ();
    }

    @Override
    public void tick() {
        if (!entity.isAlive() || entity.isRemoved() || !GasMaskHelper.hasGasMask(entity)) {
            this.stop();
            return;
        }
        this.x = entity.getX();
        this.y = entity.getEyeY();
        this.z = entity.getZ();
    }
}
