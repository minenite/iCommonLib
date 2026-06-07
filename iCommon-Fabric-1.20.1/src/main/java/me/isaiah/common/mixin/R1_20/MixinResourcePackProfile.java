package me.isaiah.common.mixin.R1_20;


import org.spongepowered.asm.mixin.Mixin;

import me.isaiah.common.ICommonMod;
import me.isaiah.common.cmixin.IMixinResourcePackProfile;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.server.packs.repository.Pack;

@Mixin(Pack.class)
public class MixinResourcePackProfile implements IMixinResourcePackProfile {

	@Override
	public PackResources IC$open_pack(String id) {
		// No on <= 1.20.2
		
		Pack handle = ((Pack)(Object)this);
		
		/*try (ResourcePack pack = handle.packFactory.open(this.handle.getId())) {
        	this.resourcePackInfo = pack.parseMetadata(PackResourceMetadata.SERIALIZER);
        } catch (IOException e) { // This is already called in NMS then if in NMS not happen is secure this not throw here
        	throw new RuntimeException(e);
        }*/
		return null;
	}

	@Override
	public PackMetadataSection IC$open_and_parse_metadata() {
		// No on <= 1.20.2
		return null;
	}

	@Override
	public String IC$get_raw_id() {
		return ((Pack)(Object)this).getId();
	}

	@Override
	public boolean IC$is_required() {
		return ((Pack)(Object)this).isRequired();
	}

	@Override
	public boolean IC$is_enabled() {
		return ICommonMod.getIServer().getMinecraft().getPackRepository().getAvailableIds().contains(this.IC$get_raw_id());
	}

}
