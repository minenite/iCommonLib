package me.isaiah.common.mixin.R1_20;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import me.isaiah.common.cmixin.IMixinBeaconBlockEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.block.entity.BeaconBlockEntity;

@Mixin(BeaconBlockEntity.class)
public class MixinBeaconBlockEntity implements IMixinBeaconBlockEntity {

	@Shadow
	public MobEffect primaryPower;
	
	@Shadow
	public MobEffect secondaryPower;
	
	@Override
	public void IC$set_primary_effect(int effectId) {
		if (effectId == -99) {
			this.primaryPower = null;
		}
		this.primaryPower = BuiltInRegistries.MOB_EFFECT.byId(effectId);
	}

	@Override
	public void IC$set_secondary_effect(int effectId) {
		if (effectId == -99) {
			this.secondaryPower = null;
		}
		this.secondaryPower = BuiltInRegistries.MOB_EFFECT.byId(effectId);
	}

}
