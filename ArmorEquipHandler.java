package com.immersiveaudio;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/** Suena una variante aleatoria de "armorequip" al equiparse O quitarse cualquier pieza de armadura (servidor: la oyen los jugadores cercanos). */
@Mod.EventBusSubscriber(modid = ImmersiveAudio.MODID)
public class ArmorEquipHandler {

    @SubscribeEvent
    public static void onEquipmentChange(LivingEquipmentChangeEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;
        if (event.getSlot().getType() != EquipmentSlot.Type.ARMOR) return;
        if (!player.isAlive()) return;                  // no suena al morir (se sueltan las piezas)

        ItemStack from = event.getFrom();
        ItemStack to = event.getTo();
        if (ItemStack.isSameItem(from, to)) return;     // sin cambio real (durabilidad/NBT) o ambos vacíos
        if (player.tickCount < 40) return;              // evita el sonido al entrar al mundo

        float pitch = 0.95F + player.getRandom().nextFloat() * 0.1F;
        player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                ModSounds.ARMOR_EQUIP.get(), SoundSource.PLAYERS, 0.8F, pitch);
    }
}
