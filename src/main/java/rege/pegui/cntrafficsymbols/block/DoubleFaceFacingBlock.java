package rege.pegui.cntrafficsymbols.block;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationPropertyHelper;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import rege.pegui.cntrafficsymbols.helper.DoubleFaceFacingMirrorer;
import rege.pegui.cntrafficsymbols.helper.DoubleFaceFacingRotator;
import rege.pegui.cntrafficsymbols.struct.DoubleFaceFacing;
import static net.minecraft.fluid.Fluids.WATER;
import static net.minecraft.state.property.Properties.WATERLOGGED;
public class DoubleFaceFacingBlock extends net.minecraft.block.Block
implements net.minecraft.block.Waterloggable{
	public DoubleFaceFacingBlock(Settings s){
		super(s);setDefaultState(getDefaultState().with(DoubleFaceFacing.FACING,
		DoubleFaceFacing.SOUTH).with(WATERLOGGED,false));
	}
	@Override public BlockState mirror(BlockState st,BlockMirror mir){
		if(mir==BlockMirror.NONE)return st;
		return st.with(DoubleFaceFacing.FACING,(mir==BlockMirror.FRONT_BACK)?
		DoubleFaceFacingMirrorer.mirrorX(st.get(DoubleFaceFacing.FACING)):
		DoubleFaceFacingMirrorer.mirrorZ(st.get(DoubleFaceFacing.FACING)));
	}
	@Override public BlockState rotate(BlockState st,BlockRotation rot){
		if(rot==BlockRotation.NONE)return st;
		return st.with(DoubleFaceFacing.FACING,DoubleFaceFacingRotator.rotate(st
		.get(DoubleFaceFacing.FACING),((rot==BlockRotation.CLOCKWISE_90)?1:
		((rot==BlockRotation.CLOCKWISE_180)?2:3))));
	}
	@Override public boolean isTransparent(BlockState st,BlockView vi,BlockPos p){
		return true;
	}
	@Override protected void appendProperties(Builder<Block,BlockState>bd){
		bd.add(DoubleFaceFacing.FACING,WATERLOGGED);
	}
	@Override public BlockState getPlacementState(ItemPlacementContext ctx){
		BlockState st=ctx.getWorld().getBlockState(ctx.getBlockPos());
		return st.isOf(this)?st.with(DoubleFaceFacing.FACING,DoubleFaceFacing
		.byID(st.get(DoubleFaceFacing.FACING).id%8+16)):
		getDefaultState().with(WATERLOGGED,ctx.getWorld().getFluidState(ctx
		.getBlockPos()).getFluid()==WATER).with(DoubleFaceFacing.FACING,
		DoubleFaceFacing.byID(RotationPropertyHelper
		.fromYaw(ctx.getPlayerYaw()+180f)));
	}
	@Override public BlockState getStateForNeighborUpdate(BlockState st,
	Direction d,BlockState nst,WorldAccess w,BlockPos p,BlockPos np){
  if (st.get(WATERLOGGED).booleanValue())w
		.scheduleFluidTick(p,WATER,WATER.getTickRate(w));
		return super.getStateForNeighborUpdate(st,d,nst,w,p,np);
 }
	@Override public FluidState getFluidState(BlockState st){
  return st.get(WATERLOGGED).booleanValue()?WATER.getStill(false):
		super.getFluidState(st);
 }
	@Override public boolean canReplace(BlockState st,ItemPlacementContext ctx){
  return st.get(DoubleFaceFacing.FACING).id<16&&ctx.getStack().isOf(asItem());
 }
}