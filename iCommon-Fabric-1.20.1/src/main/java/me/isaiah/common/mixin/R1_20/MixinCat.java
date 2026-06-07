package me.isaiah.common.mixin.R1_20;

import org.spongepowered.asm.mixin.Mixin;
import me.isaiah.common.entity.ICat;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.animal.Cat;

@Mixin(Cat.class)
public class MixinCat implements ICat {
	
	@Override
    public CatType get_cat_type() {
    	Cat mc_cat = (Cat) (Object) this;
        return CatType.values()[BuiltInRegistries.CAT_VARIANT.getId(mc_cat.getVariant())];
    }

    @Override
    public void set_cat_type(CatType type) {
    	Cat mc_cat = (Cat) (Object) this;
        mc_cat.setVariant(BuiltInRegistries.CAT_VARIANT.byId(type.ordinal()));
    }

}