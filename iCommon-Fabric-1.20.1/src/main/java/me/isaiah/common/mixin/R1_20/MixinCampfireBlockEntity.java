package me.isaiah.common.mixin.R1_20;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import me.isaiah.common.R117.ICampfireBlockEntity;
import me.isaiah.common.event.EventRegistery;
import me.isaiah.common.event.entity.CampfireBlockEntityCookEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.CampfireBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

@Mixin(value = CampfireBlockEntity.class, priority = 90)
public class MixinCampfireBlockEntity implements ICampfireBlockEntity {

    @Shadow
    public NonNullList<ItemStack> items;

    /**
     * @author Isaiah
     * @reason Fire events
     */
	 // TODO: Update to 1.19.4
    @Overwrite
    public static void cookTick(Level world, BlockPos pos, BlockState state, CampfireBlockEntity mc) {
        ICampfireBlockEntity helper = (ICampfireBlockEntity)(Object)mc;
        for (int i = 0; i < mc.getItems().size(); ++i) {
            ItemStack itemstack = (ItemStack) mc.getItems().get(i);

            if (!itemstack.isEmpty()) {
                helper.IgetCookingTimes()[i]++;

                if (helper.IgetCookingTimes()[i] >= helper.IgetCookingTotalTimes()[i]) {
                    SimpleContainer inventorysubcontainer = new SimpleContainer(new ItemStack[]{itemstack});
                    ItemStack itemstack1 = (ItemStack) mc.getLevel().getRecipeManager().getRecipeFor(RecipeType.CAMPFIRE_COOKING, inventorysubcontainer, mc.getLevel()).map((recipecampfire) -> {
                        return recipecampfire.assemble(inventorysubcontainer, world.registryAccess());
                    }).orElse(itemstack);
                    BlockPos blockposition = mc.getBlockPos();

                    CampfireBlockEntityCookEvent event = (CampfireBlockEntityCookEvent)EventRegistery.invoke(CampfireBlockEntityCookEvent.class,
                            new CampfireBlockEntityCookEvent(mc.getLevel(), pos, itemstack, itemstack1));

                    if (event.isCanceled()) return;

                    itemstack1 = (ItemStack) event.getResult();

                    Containers.dropItemStack(mc.getLevel(), (double) blockposition.getX(), (double) blockposition.getY(), (double) blockposition.getZ(), itemstack1);
                    mc.getItems().set(i, ItemStack.EMPTY);
                    helper.IupdateListeners();
                }
            }
        }

    }

    @Shadow
    public int[] cookingProgress;

    @Shadow
    public int[] cookingTime;

    @Shadow
    public void markUpdated() {
    }


    @Override
    public int[] IgetCookingTimes() {
        return cookingProgress;
    }

    @Override
    public int[] IgetCookingTotalTimes() {
        return cookingTime;
    }

    @Override
    public void IupdateListeners() {
        markUpdated();
    }

}