package me.isaiah.common.mixin.R1_20;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;

import com.mojang.brigadier.exceptions.CommandSyntaxException;

import me.isaiah.common.ICommonMod;
import me.isaiah.common.cmixin.IMixinItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.TagParser;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionUtils;

@Mixin(ItemStack.class)
public class MixinItemStack implements IMixinItemStack {

	@Override
	public void IC$damage(int amount, LivingEntity entity, InteractionHand hand) {
        ((ItemStack)(Object)this).hurtAndBreak(1, entity, (plr1) -> {
        	plr1.broadcastBreakEvent(hand);
        });
	}

	@Override
	public void IC$modify_arguments(String arguments) {
        try {
        	((ItemStack)(Object)this).setTag((CompoundTag) TagParser.parseTag(arguments));
        } catch (CommandSyntaxException ex) {
            ICommonMod.LOGGER.error("CommandSyntaxException while modifying arguments", ex);
        }
	}
	
	@Override
	public List<MobEffectInstance> IC$get_potion_status_effects() {
		return PotionUtils.getMobEffects(((ItemStack) (Object) this));
	}
	
}
