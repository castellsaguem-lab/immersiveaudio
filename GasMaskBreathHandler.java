package com.immersiveaudio.client;

import com.immersiveaudio.ImmersiveAudio;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;

/**
 * Mantiene una respiración activa por cada entidad cercana con máscara de gas.
 * Cuando una variante termina del todo, se lanza otra al azar.
 */
@Mod.EventBusSubscriber(modid = ImmersiveAudio.MODID, value = Dist.CLIENT)
public class GasMaskBreathHandler {

    /** Radio de búsqueda. Debe quedar por debajo del rango audible (16 bloques) para no crear sonidos que no se oyen. */
    private static final double MAX_DISTANCE = 14.0;
    private static final double MAX_DISTANCE_SQ = MAX_DISTANCE * MAX_DISTANCE;

    private static final Map<Integer, GasMaskBreathSound> ACTIVE = new HashMap<>();

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Minecraft mc = Minecraft.getInstance();
        ClientLevel level = mc.level;
        Player me = mc.player;
        if (level == null || me == null) {
            ACTIVE.clear();
            return;
        }
        if (mc.isPaused()) return;

        long now = level.getGameTime();

        for (Entity e : level.entitiesForRendering()) {
            if (!(e instanceof LivingEntity living)) continue;
            if (!living.isAlive() || !GasMaskHelper.hasGasMask(living)) continue;
            if (living.distanceToSqr(me) > MAX_DISTANCE_SQ) continue;

            GasMaskBreathSound current = ACTIVE.get(living.getId());
            if (current != null) {
                if (mc.getSoundManager().isActive(current)) continue; // aún está sonando
                if (now - current.createdAt < 3) continue;            // acaba de lanzarse
            }

            GasMaskBreathSound next = new GasMaskBreathSound(living, now);
            ACTIVE.put(living.getId(), next);
            mc.getSoundManager().play(next);
        }

        ACTIVE.values().removeIf(GasMaskBreathSound::isStopped);
    }
}
