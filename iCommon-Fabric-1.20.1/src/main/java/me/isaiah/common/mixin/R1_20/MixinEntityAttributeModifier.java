package me.isaiah.common.mixin.R1_20;

import java.util.UUID;

import org.spongepowered.asm.mixin.Mixin;

import me.isaiah.common.cmixin.IMixinEntityAttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;

@Mixin(AttributeModifier.class)
public class MixinEntityAttributeModifier implements IMixinEntityAttributeModifier {

	@Override
	public double IC$get_value() {
		return ((AttributeModifier)(Object)this).getAmount();
	}

	@Override
	public Operation IC$get_operation() {
		return ((AttributeModifier)(Object)this).getOperation();
	}

	@Override
	public UUID IC$get_uuid() {
		return ((AttributeModifier)(Object)this).getId();
	}
	
}
