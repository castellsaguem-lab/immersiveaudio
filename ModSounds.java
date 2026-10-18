package com.immersiveaudio;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, ImmersiveAudio.MODID);

    public static final RegistryObject<SoundEvent> ARMOR_EQUIP = register("armorequip");
    public static final RegistryObject<SoundEvent> GUITAR = register("guitar");
    public static final RegistryObject<SoundEvent> ZIP = register("zip");
    public static final RegistryObject<SoundEvent> GAS_BREATH = register("gasbreath");

    private static RegistryObject<SoundEvent> register(String name) {
        return SOUNDS.register(name, () ->
                SoundEvent.createVariableRangeEvent(new ResourceLocation(ImmersiveAudio.MODID, name)));
    }
}
