package its_meow.betteranimalsplus.block.render;

import its_meow.betteranimalsplus.block.TileEntityHirschgeistSkull;
import its_meow.betteranimalsplus.entity.model.ModelHirschgeistSkull;
import its_meow.betteranimalsplus.registry.TextureRegistry;
import net.minecraft.block.BlockDirectional;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;

@SideOnly(Side.CLIENT)
public class RenderBlockHirschgeistSkull extends TileEntitySpecialRenderer<TileEntityHirschgeistSkull> {
	
	ModelHirschgeistSkull mainModel;


	public RenderBlockHirschgeistSkull() {
		mainModel = new ModelHirschgeistSkull();
	}



	@Override
    public void render(TileEntityHirschgeistSkull tileentity, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		GlStateManager.pushMatrix();
        GlStateManager.translate(x+ 0.5F,y + 1.5F, z + 0.5F);
        GlStateManager.rotate(180, 0, 0, 1);
        World world = tileentity.getWorld();
        Comparable facing = world.getBlockState(tileentity.getPos()).getProperties().get(BlockDirectional.FACING);
        if(facing == EnumFacing.NORTH) {
        	
        }
        if(facing == EnumFacing.EAST) {
        	
        }
        if(facing == EnumFacing.WEST) {
        	
        }
        if(facing == EnumFacing.SOUTH) {
        	
        }
        this.bindTexture(TextureRegistry.hirschgeist);
        this.mainModel.render((Entity) null, 0F, 0F, 0F, 0F, 0F, 0.0625F);
        GlStateManager.popMatrix();
    }
	
}