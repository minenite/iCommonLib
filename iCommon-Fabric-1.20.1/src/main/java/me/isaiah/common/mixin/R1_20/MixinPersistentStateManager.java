package me.isaiah.common.mixin.R1_20;

import org.spongepowered.asm.mixin.Mixin;

import me.isaiah.common.cmixin.IMixinPersistentStateManager;
import net.minecraft.world.level.ForcedChunksSavedData;
import net.minecraft.world.level.storage.DimensionDataStorage;

@Mixin(DimensionDataStorage.class)
public class MixinPersistentStateManager implements IMixinPersistentStateManager {

    @Override
    public ForcedChunksSavedData Iget() {
        return ((DimensionDataStorage)(Object)this).get(ForcedChunksSavedData::load, "chunks");
    }

}