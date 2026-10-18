package com.immersiveaudio;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ImmersiveAudio.MODID)
public class ImmersiveAudio {
    public static final String MODID = "immersiveaudio";

    public ImmersiveAudio() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModSounds.SOUNDS.register(modBus);
    }
}
