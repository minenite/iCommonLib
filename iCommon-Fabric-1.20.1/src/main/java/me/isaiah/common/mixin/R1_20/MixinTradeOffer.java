package me.isaiah.common.mixin.R1_20;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import me.isaiah.common.cmixin.IMixinTradeOffer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.MerchantOffer;

@Mixin(MerchantOffer.class)
public class MixinTradeOffer implements IMixinTradeOffer {

	// Lnet/minecraft/village/TradeOffer;firstBuyItem:Lnet/minecraft/item/ItemStack;
	@Shadow
	public ItemStack baseCostA;

	// Lnet/minecraft/village/TradeOffer;secondBuyItem:Lnet/minecraft/item/ItemStack;
	@Shadow
	public ItemStack costB;
	
	@Override
	public ItemStack IC$get_first_buy_itemstack() {
		return baseCostA;
	}

	@Override
	public ItemStack IC$get_second_buy_itemstack() {
		if (null == costB) {
			return null;
		}
		return costB;
	}
	
	@Override
	public void IC$set_first_buy_itemstack(ItemStack stack) {
		this.baseCostA = stack;
	}

	@Override
	public void IC$set_second_buy_itemstack(ItemStack stack) {
		this.costB = stack;
	}

}