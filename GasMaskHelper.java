package com.immersiveaudio.client;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;

public class GasMaskHelper {

    public static final Set<ResourceLocation> GAS_MASKS = Set.of(
            new ResourceLocation("mm_armor", "gp_5blackgasmask_helmet"),
            new ResourceLocation("mm_armor", "gp_5gasmask_helmet"),
            new ResourceLocation("mm_armor", "pmk_2gasmask_helmet"),
            new ResourceLocation("mm_armor", "pmk_4gasmask_helmet"),
            new ResourceLocation("mm_armor", "m_40gasmask_helmet")
    );

    public static boolean hasGasMask(LivingEntity entity) {
        ItemStack head = entity.getItemBySlot(EquipmentSlot.HEAD);
        if (head.isEmpty()) return false;
        return GAS_MASKS.contains(ForgeRegistries.ITEMS.getKey(head.getItem()));
    }
}
