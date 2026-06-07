package me.isaiah.common.mixin.R1_20;

import java.util.UUID;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import me.isaiah.common.ICommonMod;
import me.isaiah.common.cmixin.IMixinEntity;
import me.isaiah.common.entity.IEntity;
import me.isaiah.common.entity.IRemoveReason;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

@Mixin(Entity.class)
public class MixinEntity implements IMixinEntity {

    @Override
    public void Iremove(IRemoveReason r) {
        switch (r) {
            case DIMENSION_CHANGE:
                removeAfterChangingDimensions();
                break;
            case DISCARDED:
                discard();
                break;
            case KILLED:
                kill();
                break;
            default:
                ICommonMod.LOGGER.warn("Unknown RemoveReason: " + r.toString());
                break;
        }
    }

    private IEntity icommon;

    @Override
    public IEntity getAsICommon() {
        if (null == icommon) icommon = newICommonInstance_InternalOnly();
        return icommon;
    }

    @Shadow public void kill()  {} // Dimension change
    @Shadow public void discard() {} // Discard
    @Shadow public void removeAfterChangingDimensions() {} // Kill

    @Override
    public void IsendText(Component text, UUID id) {
        IgetMCEntity().sendSystemMessage(text);
    }

    @Override
    public boolean ic_isRemoved() {
        return isRemoved();
    }
    
    @Shadow
    public boolean isRemoved() {
        return false;
    }
    
	@Override
	public boolean IC$has_status_effect(MobEffect effect) {
		Entity thiz = (Entity) (Object) this;
		if (!(thiz instanceof LivingEntity)) {
			ICommonMod.LOGGER.info("ERROR: Entity is not living enitity");
			return false;
		}
		LivingEntity entity = (LivingEntity) (Object) this;
        return entity.hasEffect(effect);
	}
	
	@Override
	public void IC$add_status_effect(MobEffect effect, int duration, int amp, boolean ambient, boolean particles) {
		Entity thiz = (Entity) (Object) this;
		if (!(thiz instanceof LivingEntity)) {
			ICommonMod.LOGGER.info("ERROR: Entity is not living enitity");
			return;
		}
		LivingEntity entity = (LivingEntity) thiz;
        entity.addEffect(new MobEffectInstance(effect, duration, amp, ambient, particles));
	}
	
	@Override
	public void IC$remove_status_effect(MobEffect effect) {
		Entity thiz = (Entity) (Object) this;
		if (!(thiz instanceof LivingEntity)) {
			ICommonMod.LOGGER.info("ERROR: Entity is not living enitity");
			return;
		}
		LivingEntity entity = (LivingEntity) thiz;
        entity.removeEffect(effect);
	}
	
	@Override
	public MobEffectInstance IC$get_status_effect(int typeId) {
		Entity thiz = (Entity) (Object) this;
		if (!(thiz instanceof LivingEntity)) {
			ICommonMod.LOGGER.info("ERROR: Entity is not living enitity");
			return null;
		}
		LivingEntity entity = (LivingEntity) thiz;
		MobEffect effect = BuiltInRegistries.MOB_EFFECT.byId(typeId);
		MobEffectInstance handle = entity.getEffect(effect);
		return handle;
	}

	@Override
	public int IC$get_status_effect_id(MobEffectInstance handle) {
		MobEffect effect = handle.getEffect();
		return BuiltInRegistries.MOB_EFFECT.getId(effect);
	}

	@Override
	public void IC$teleport(ServerLevel world, double x, double y, double z) {
		((Entity) (Object) this).teleportToWithTicket(x, y, z);
	}

	@Override
	public Level ic$getWorld() {
		return ((Entity) (Object) this).level();
	}

}