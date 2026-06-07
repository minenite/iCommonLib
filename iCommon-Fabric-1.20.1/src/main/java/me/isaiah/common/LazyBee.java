package me.isaiah.common;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.level.block.entity.BeehiveBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;

public class LazyBee {

    public static void IC$add_bee_to_beehive(BlockEntity tileentity, ServerLevel world, int rand) {
		if (tileentity instanceof BeehiveBlockEntity) {
            BeehiveBlockEntity beehive = (BeehiveBlockEntity) tileentity;
            Bee bee = new Bee(EntityType.BEE, world);
            
            // BeeEntity bee = EntityType.BEE.create(world);
            
            // BeeEntity bee = LazyBee.get(world);
            
            beehive.addOccupantWithPresetTicks(bee, false, rand);
        }
	}

}