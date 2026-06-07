package me.isaiah.common.mixin.R1_21;

import me.isaiah.common.cmixin.IMixinPersistentStateManager;
import net.minecraft.world.level.ForcedChunksSavedData;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.storage.DimensionDataStorage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(DimensionDataStorage.class)
public abstract class MixinPersistentStateManager implements IMixinPersistentStateManager {
    @Shadow public abstract SavedData get(SavedData.Factory<ForcedChunksSavedData> par1, String par2);

    @Override
    public ForcedChunksSavedData Iget() {
        return (ForcedChunksSavedData) get(ForcedChunksSavedData.factory(), "chunks");
    }

}
