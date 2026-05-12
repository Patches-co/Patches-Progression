package net.patches.progression;

import net.fabricmc.api.ModInitializer;

import net.patches.progression.block.ModBlocks;
import net.patches.progression.item.ModItemGroups;
import net.patches.progression.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PatchesProgression implements ModInitializer {
	public static final String MOD_ID = "patches-progression";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
        ModItemGroups.registerItemGroups();

        ModItems.registerModItems();
        ModBlocks.registerModBlocks();

		LOGGER.info("Hello Fabric world!");
	}
}