package me.isaiah.common.mixin.R1_21;

import java.util.Optional;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import me.isaiah.common.cmixin.IMixinTradeOffer;
import net.minecraft.core.component.DataComponentPredicate;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;

@Mixin(MerchantOffer.class)
public class MixinTradeOffer implements IMixinTradeOffer {

	@Shadow
	public ItemCost baseCostA;

	@Shadow
	public Optional<ItemCost> costB;
	
	@Override
	public ItemStack IC$get_first_buy_itemstack() {
		MerchantOffer ofr = (MerchantOffer) (Object) this;
		return ofr.getItemCostA().itemStack();
	}

	@Override
	public ItemStack IC$get_second_buy_itemstack() {
		MerchantOffer ofr = (MerchantOffer) (Object) this;
		Optional<ItemCost> opt = ofr.getItemCostB();
		
		if (!opt.isPresent()) {
			return null;
		}

		return opt.get().itemStack();
	}
	
	@Override
	public void IC$set_first_buy_itemstack(ItemStack stack) {
        this.baseCostA = new ItemCost(stack.getItemHolder(), stack.getCount(), DataComponentPredicate.allOf(stack.getComponents()), stack);

	}

	@Override
	public void IC$set_second_buy_itemstack(ItemStack stack) {
        this.costB = Optional.of(new ItemCost(stack.getItemHolder(), stack.getCount(), DataComponentPredicate.allOf(stack.getComponents()), stack));

	}

}