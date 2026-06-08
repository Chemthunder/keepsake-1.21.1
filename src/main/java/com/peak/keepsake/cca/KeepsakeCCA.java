package com.peak.keepsake.cca;

import com.peak.keepsake.cca.entity.KeepsakeComponent;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;
import org.ladysnake.cca.api.v3.entity.RespawnCopyStrategy;

public class KeepsakeCCA implements EntityComponentInitializer {
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry cca) {
        cca.registerForPlayers(
                KeepsakeComponent.KEY,
                KeepsakeComponent::new,
                RespawnCopyStrategy.ALWAYS_COPY
        );
    }
}
