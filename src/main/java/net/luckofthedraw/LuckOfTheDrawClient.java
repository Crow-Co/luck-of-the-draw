package net.luckofthedraw;

import dev.architectury.registry.ReloadListenerRegistry;
import mod.chloeprime.aaaparticles.client.loader.EffekAssetLoader;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;

public class LuckOfTheDrawClient implements ClientModInitializer {
    // ? Runs the mod initializer on the client environment.
    @Override
    public void onInitializeClient() {
        ReloadListenerRegistry.register(ResourceType.CLIENT_RESOURCES, new EffekAssetLoader(), new Identifier(LuckOfTheDraw.MOD_ID, "effeks"));
    }
}
