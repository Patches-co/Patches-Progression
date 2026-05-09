package net.patches.progression;

import net.fabricmc.api.ModInitializer;

import net.patches.progression.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PatchesProgression implements ModInitializer {
	public static final String MOD_ID = "patches-progression";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
        ModItems.registerModItems();
		LOGGER.info("Hello Fabric world!");
	}
}