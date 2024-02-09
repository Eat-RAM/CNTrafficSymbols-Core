package rege.pegui.cntrafficsymbols.block;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager.Builder;
import rege.pegui.cntrafficsymbols.struct.Attachment3;
import rege.pegui.cntrafficsymbols.struct.DoubleFaceFacing;
import static net.minecraft.state.property.Properties.WATERLOGGED;
public class Attachment3DoubleFaceFacingBlock extends DoubleFaceFacingBlock{
	public Attachment3DoubleFaceFacingBlock(Settings s){
		super(s);setDefaultState(getDefaultState()
		.with(Attachment3.ATTACHMENT,Attachment3.DOWN));
	}
	@Override protected void appendProperties(Builder<Block,BlockState>bd){
		bd.add(DoubleFaceFacing.FACING,Attachment3.ATTACHMENT,WATERLOGGED);
	}
	@Override public BlockState getPlacementState(ItemPlacementContext ctx){
		BlockState st=ctx.getWorld().getBlockState(ctx.getBlockPos());
		return st.isOf(this)?st.with(DoubleFaceFacing.FACING,DoubleFaceFacing
		.byID(st.get(DoubleFaceFacing.FACING).id%8+16)):getDefaultState()
		.with(WATERLOGGED,ctx.getWorld().getFluidState(ctx.getBlockPos()).getFluid()
		==net.minecraft.fluid.Fluids.WATER).with(DoubleFaceFacing.FACING,
		DoubleFaceFacing.byID(ctx.getSide().getAxis().isVertical()?
		net.minecraft.util.math.RotationPropertyHelper
		.fromYaw(ctx.getPlayerYaw()+180f):(ctx.getSide().getHorizontal()+2)%4+24))
		.with(Attachment3.ATTACHMENT,ctx.getSide().getAxis().isVertical()?
		((ctx.getSide().getId()==0)?Attachment3.UP:Attachment3.DOWN):
		((ctx.getHitPos().y-ctx.getBlockPos().getY()>=.75)?Attachment3.UP:
		((ctx.getHitPos().y-ctx.getBlockPos().getY()<=.25)?Attachment3.DOWN:
		Attachment3.NONE)));
	}
}
