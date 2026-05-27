package net.patches.progression;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.patches.progression.block.ModBlocks;
import net.patches.progression.event.ModItemModifiers;
import net.patches.progression.item.ModItemGroups;
import net.patches.progression.item.ModItems;
import net.patches.progression.worldgen.ModFeatures;
import net.patches.progression.worldgen.ModWorldGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PatchesProgression implements ModInitializer {
	public static final String MOD_ID = "patches-progression";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
        ModItems.registerModItems();
		ModItemGroups.registerItemGroups();
		ModItemModifiers.registerModifiers();
        ModBlocks.registerModBlocks();
        ModFeatures.register();
        ModWorldGeneration.register();

		// REMOVENDO FERRAMENTAS DE MADEIRA
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
			entries.getDisplayStacks().removeIf(stack ->
					stack.isOf(Items.WOODEN_PICKAXE) ||
							stack.isOf(Items.WOODEN_AXE) ||
							stack.isOf(Items.WOODEN_SHOVEL) ||
							stack.isOf(Items.WOODEN_HOE)
			);
		});

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
			entries.getDisplayStacks().removeIf(stack ->
					stack.isOf(Items.WOODEN_SWORD) ||
							stack.isOf(Items.WOODEN_AXE)
			);
		});

		LOGGER.info("Hello Fabric world!");
	}
}