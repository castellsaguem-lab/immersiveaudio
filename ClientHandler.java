package com.immersiveaudio.client;

import com.immersiveaudio.ImmersiveAudio;
import com.immersiveaudio.ModSounds;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * "zip" al abrir el inventario (E) o cualquier contenedor (cofres, hornos, mesas de crafteo...)
 * y "zipreverse" (el mismo sonido al revés) al cerrarlos.
 */
@Mod.EventBusSubscriber(modid = ImmersiveAudio.MODID, value = Dist.CLIENT)
public class ClientHandler {

    @SubscribeEvent
    public static void onScreenOpening(ScreenEvent.Opening event) {
        Screen current = event.getCurrentScreen();
        Screen next = event.getNewScreen();

        // Abrir: del juego (sin pantalla) a un contenedor
        if (current == null && next instanceof AbstractContainerScreen<?>) {
            play(ModSounds.ZIP.get());
            return;
        }

        // Cerrar: de un contenedor de vuelta al juego (sin pantalla)
        if (current instanceof AbstractContainerScreen<?> && next == null) {
            play(ModSounds.ZIP_REVERSE.get());
        }
    }

    private static void play(SoundEvent sound) {
        Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(sound, 1.0F, 1.0F));
    }
}
