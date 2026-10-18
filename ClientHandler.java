package com.immersiveaudio.client;

import com.immersiveaudio.ImmersiveAudio;
import com.immersiveaudio.ModSounds;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ImmersiveAudio.MODID, value = Dist.CLIENT)
public class ClientHandler {

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
