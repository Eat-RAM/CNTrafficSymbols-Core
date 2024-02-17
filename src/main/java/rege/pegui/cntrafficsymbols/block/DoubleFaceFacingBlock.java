package rege.pegui.cntrafficsymbols.block;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Waterloggable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
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
import static rege.pegui.cntrafficsymbols.Main.getWaterloggedProperty;
public class DoubleFaceFacingBlock extends Block implements Waterloggable{
	public DoubleFaceFacingBlock(Settings s){
		super(s);BlockState st=getDefaultState().with(DoubleFaceFacing.FACING,
		DoubleFaceFacing.SOUTH);
		if(getWaterloggedProperty())st=st.with(WATERLOGGED,false);
		setDefaultState(st);
	}
	@Override public BlockState rotate(BlockState st,BlockRotation rot){
		if(rot==BlockRotation.NONE)return st;
		return st.with(DoubleFaceFacing.FACING,DoubleFaceFacingRotator.rotate(st
		.get(DoubleFaceFacing.FACING),((rot==BlockRotation.CLOCKWISE_90)?1:
		((rot==BlockRotation.CLOCKWISE_180)?2:3))));
	}
	@Override public BlockState mirror(BlockState st,BlockMirror mir){
		if(mir==BlockMirror.NONE)return st;
		return st.with(DoubleFaceFacing.FACING,(mir==BlockMirror.FRONT_BACK)?
		DoubleFaceFacingMirrorer.mirrorX(st.get(DoubleFaceFacing.FACING)):
		DoubleFaceFacingMirrorer.mirrorZ(st.get(DoubleFaceFacing.FACING)));
	}
	@Override public boolean isTransparent(BlockState st,BlockView vi,BlockPos p){
		return true;
	}
	@Override protected void appendProperties(Builder<Block,BlockState>bd){
		bd.add(DoubleFaceFacing.FACING);
		if(getWaterloggedProperty())bd.add(WATERLOGGED);
	}
	@Override public BlockState getPlacementState(ItemPlacementContext ctx){
		BlockState st=ctx.getWorld().getBlockState(ctx.getBlockPos());
		BlockState sst=st.isOf(this)?st.with(DoubleFaceFacing.FACING,DoubleFaceFacing
		.byID(st.get(DoubleFaceFacing.FACING).id%8+16)):getDefaultState()
		.with(DoubleFaceFacing.FACING,DoubleFaceFacing.byID(ctx.getSide().getAxis()
		.isVertical()?RotationPropertyHelper
		.fromYaw(ctx.getPlayerYaw()+180f):(ctx.getSide().getHorizontal()+2)%4+24));
		if(getWaterloggedProperty())sst=sst.with(WATERLOGGED,ctx.getWorld()
		.getFluidState(ctx.getBlockPos()).getFluid()==WATER);
		return sst;
	}
	@Override public BlockState getStateForNeighborUpdate(BlockState st,
	Direction d,BlockState nst,WorldAccess w,BlockPos p,BlockPos np){
		if (getWaterloggedProperty()&&st.get(WATERLOGGED).booleanValue())w
		.scheduleFluidTick(p,WATER,WATER.getTickRate(w));
		return super.getStateForNeighborUpdate(st,d,nst,w,p,np);
	}
	@Override public FluidState getFluidState(BlockState st){
		return(getWaterloggedProperty()&&st.get(WATERLOGGED).booleanValue())?
		WATER.getStill(false):super.getFluidState(st);
	}
	@Override public boolean canReplace(BlockState st,ItemPlacementContext ctx){
		return st.get(DoubleFaceFacing.FACING).id<16&&ctx.getStack().isOf(asItem());
	}
	@Override public boolean canFillWithFluid(PlayerEntity pl,BlockView v,
	BlockPos p,BlockState st,Fluid fl){
		return getWaterloggedProperty()&&
		Waterloggable.super.canFillWithFluid(pl,v,p,st,fl);
	}
	@Override public boolean
	tryFillWithFluid(WorldAccess w,BlockPos p,BlockState st,FluidState fst){
		return getWaterloggedProperty()&&
		Waterloggable.super.tryFillWithFluid(w,p,st,fst);
	}
	@Override public ItemStack
	tryDrainFluid(PlayerEntity pl,WorldAccess w,BlockPos p,BlockState st){
		return getWaterloggedProperty()?Waterloggable.super.tryDrainFluid(pl,w,p,st):
		ItemStack.EMPTY;
	}
}
