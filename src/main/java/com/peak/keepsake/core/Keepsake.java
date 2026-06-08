package com.peak.keepsake.core;

import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Chemthunder
 */
public class Keepsake implements ModInitializer {
	public static final String MOD_ID = "keepsake";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public void onInitialize() {

		LOGGER.info("\"Keepsakes of a forgotten time...\"");
	}

    public static Identifier id(String path) {
        return Identifier.of(MOD_ID, path);
    }
}