package rege.pegui.cntrafficsymbols.block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Waterloggable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldAccess;
import static rege.pegui.cntrafficsymbols.Main.getWaterloggedProperty;
public interface ManagedWaterloggable extends Waterloggable{
	@Override public default boolean
	canFillWithFluid(PlayerEntity pl,net.minecraft.world
	.BlockView v,BlockPos p,BlockState st,net.minecraft.fluid.Fluid fl){
		return getWaterloggedProperty()&&
		Waterloggable.super.canFillWithFluid(pl,v,p,st,fl);
	}
	@Override public default boolean tryFillWithFluid(WorldAccess w,BlockPos p,
	BlockState st,net.minecraft.fluid.FluidState fst){
		return getWaterloggedProperty()&&
		Waterloggable.super.tryFillWithFluid(w,p,st,fst);
	}
	@Override public default ItemStack tryDrainFluid(PlayerEntity pl,
	WorldAccess w,BlockPos p,BlockState st){return getWaterloggedProperty()?
	Waterloggable.super.tryDrainFluid(pl,w,p,st):ItemStack.EMPTY;}
}
