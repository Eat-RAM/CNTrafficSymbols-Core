package rege.pegui.cntrafficsymbols.block;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager.Builder;
import rege.pegui.cntrafficsymbols.struct.Attachment3;
import rege.pegui.cntrafficsymbols.struct.DoubleFaceFacing;
import static net.minecraft.fluid.Fluids.WATER;
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
		.byID(st.get(DoubleFaceFacing.FACING).id%8+16)):
		getDefaultState().with(WATERLOGGED,ctx.getWorld().getFluidState(ctx
		.getBlockPos()).getFluid()==WATER).with(DoubleFaceFacing.FACING,
		DoubleFaceFacing.byID(net.minecraft.util.math.RotationPropertyHelper
		.fromYaw(ctx.getPlayerYaw()+180f))).with(Attachment3.ATTACHMENT,
		(ctx.getSide().getId()==0)?Attachment3.UP:((ctx.getSide().getId()==1)?
		Attachment3.DOWN:Attachment3.NONE));
	}
}