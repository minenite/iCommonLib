package me.isaiah.common.mixin.R1_20;

import org.spongepowered.asm.mixin.Mixin;

import me.isaiah.common.cmixin.IMixinGlobalPos;
import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;

@Mixin(GlobalPos.class)
public class MixinGlobalPos implements IMixinGlobalPos {

	@Override
	public BlockPos IC$get_pos() {
		return ((GlobalPos)(Object)this).pos();
	}
	
	@Override
	public Object IC$get_dimension() {
		return ((GlobalPos)(Object)this).dimension();
	}

}
