package com.immersiveaudio.client;

import com.immersiveaudio.ImmersiveAudio;
import com.immersiveaudio.ModSounds;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ImmersiveAudio.MODID, value = Dist.CLIENT)
public class ClientHandler {

    private static final ResourceLocation VANILLA_EXPLODE = new ResourceLocation("minecraft", "entity.generic.explode");

    /** A partir de esta distancia (bloques) la explosión suena "lejana". Vanilla solo envía explosiones a jugadores a menos de 64 bloques. */
    private static final double DISTANT_MIN = 24.0;

    /** Sustituye el sonido de explosión vanilla por el nuestro (cerca) o por el lejano (24+ bloques). */
    @SubscribeEvent
    public static void onPlaySound(PlaySoundEvent event) {
        SoundInstance original = event.getSound();
        if (original == null || !VANILLA_EXPLODE.equals(original.getLocation())) return;

        Player player = Minecraft.getInstance().player;
        if (player == null) return;

        double dist = Math.sqrt(player.distanceToSqr(original.getX(), original.getY(), original.getZ()));
        SoundEvent replacement = dist >= DISTANT_MIN
                ? ModSounds.EXPLOSION_DISTANT.get()
                : ModSounds.EXPLOSION.get();

        event.setSound(new SimpleSoundInstance(replacement, original.getSource(),
                original.getVolume(), original.getPitch(), SoundInstance.createUnseededRandom(),
                original.getX(), original.getY(), original.getZ()));
    }

    /** Sonido "zip" al abrir el inventario con la tecla E (supervivencia y creativo). */
    @SubscribeEvent
    public static void onScreenOpening(ScreenEvent.Opening event) {
        if (event.getCurrentScreen() != null) return; // solo al abrirlo desde el juego
        Screen screen = event.getNewScreen();
        if (screen instanceof InventoryScreen || screen instanceof CreativeModeInventoryScreen) {
            Minecraft.getInstance().getSoundManager()
                    .play(SimpleSoundInstance.forUI(ModSounds.ZIP.get(), 1.0F, 1.0F));
        }
    }
}
