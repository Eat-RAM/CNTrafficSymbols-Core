package rege.pegui.cntrafficsymbols.block;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import rege.pegui.cntrafficsymbols.helper.DoubleFaceFacing90Mirrorer;
import rege.pegui.cntrafficsymbols.helper.DoubleFaceFacing90Rotator;
import rege.pegui.cntrafficsymbols.struct.DoubleFaceFacing90;
import static net.minecraft.fluid.Fluids.WATER;
import static net.minecraft.state.property.Properties.WATERLOGGED;
import static net.minecraft.util.shape.VoxelShapes.cuboid;
import static rege.pegui.cntrafficsymbols.Main.getWaterloggedProperty;
public class WideBoardBlock extends Block implements ManagedWaterloggable{
	public static final VoxelShape SHAPE0=cuboid(0,0,.5,1,1,.5625);
	public static final VoxelShape SHAPE1=cuboid(.4375,0,0,.5,1,1);
	public static final VoxelShape SHAPE2=cuboid(0,0,.4375,1,1,.5);
	public static final VoxelShape SHAPE3=cuboid(.5,0,0,.5625,1,1);
	public static final VoxelShape SHAPE4=cuboid(0,0,.4375,1,1,.5625);
	public static final VoxelShape SHAPE5=cuboid(.4375,0,0,.5625,1,1);
	public static final VoxelShape SHAPE6=cuboid(0,0,.9375,1,1,1);
	public static final VoxelShape SHAPE7=cuboid(0,0,0,.0625,1,1);
	public static final VoxelShape SHAPE8=cuboid(0,0,0,1,1,.0625);
	public static final VoxelShape SHAPE9=cuboid(.9375,0,0,1,1,1);
	private List<WideBoardBlock>arr;
	private int arri=-1;
	public WideBoardBlock(Settings s){
		super(s);BlockState st=getDefaultState().with(DoubleFaceFacing90.FACING,
		DoubleFaceFacing90.SOUTH);arr=new java.util.ArrayList<>();
		if(getWaterloggedProperty())st=st.with(WATERLOGGED,false);
		setDefaultState(st);
	}
	public WideBoardBlock(WideBoardBlock cb,Settings s){
		super(s);BlockState st=getDefaultState().with(DoubleFaceFacing90.FACING,
		DoubleFaceFacing90.SOUTH);arr=cb.arr;
		if(getWaterloggedProperty())st=st.with(WATERLOGGED,false);
		setDefaultState(st);
	}
	public boolean addToArr(WideBoardBlock b)
	throws UnsupportedOperationException{return arr.add(b);}
	public void addToArr(int i,WideBoardBlock b)
	throws UnsupportedOperationException{arr.add(i,b);}
	public WideBoardBlock getInArr(int i)
	throws IndexOutOfBoundsException{return arr.get(i);}
	public WideBoardBlock removeInArr(int i)throws UnsupportedOperationException,
	IndexOutOfBoundsException{return arr.remove(i);}
	public List<WideBoardBlock>freezeArr()throws IllegalArgumentException{
		int s=arr.size();
		for(int i=0;i<s;i++){if(arr.get(i)==this){
			if(arri==-1){arri=i;}else{throw new IllegalArgumentException();}
		}}
		if(arri==-1)throw new IllegalArgumentException();
		return arr=List.copyOf(arr);
	}
	@Override public VoxelShape getOutlineShape(BlockState st,net.minecraft.world
	.BlockView v,BlockPos p,net.minecraft.block.ShapeContext c){
		switch(st.get(DoubleFaceFacing90.FACING).id){
			case 0:return SHAPE0;
			case 1:return SHAPE1;
			case 2:return SHAPE2;
			case 3:return SHAPE3;
			case 4:return SHAPE4;
			case 5:return SHAPE5;
			case 6:return SHAPE6;
			case 7:return SHAPE7;
			case 8:return SHAPE8;
			case 9:return SHAPE9;
			default:assert false;
		}
		return null;
	}
	@Override protected void appendProperties(net.minecraft.state.StateManager
	.Builder<Block,BlockState>bd){
		bd.add(DoubleFaceFacing90.FACING);
		if(getWaterloggedProperty())bd.add(WATERLOGGED);
	}
	@Override public BlockState getPlacementState(ItemPlacementContext ctx){
		BlockState st=ctx.getWorld().getBlockState(ctx.getBlockPos());
		BlockState sst=st.isOf(this)?st.with(DoubleFaceFacing90.FACING,
		DoubleFaceFacing90.byID(st.get(DoubleFaceFacing90.FACING).id%2+4)):
		getDefaultState().with(DoubleFaceFacing90.FACING,DoubleFaceFacing90.byID(ctx
		.getSide().getAxis().isVertical()?ctx.getHorizontalPlayerFacing()
		.getOpposite().getHorizontal():(ctx.getSide().getHorizontal()+2)%4+6));
		if(getWaterloggedProperty())sst=sst.with(WATERLOGGED,ctx.getWorld()
		.getFluidState(ctx.getBlockPos()).getFluid()==WATER);
		return sst;
	}
	@Override public boolean canReplace(BlockState st,ItemPlacementContext ctx){
		return st.get(DoubleFaceFacing90.FACING).id<4&&ctx.getStack().isOf(asItem());
	}
	@Override public BlockState rotate(BlockState st,BlockRotation rot){
		if(rot==BlockRotation.NONE)return st;
		return st.with(DoubleFaceFacing90.FACING,DoubleFaceFacing90Rotator.rotate(st
		.get(DoubleFaceFacing90.FACING),((rot==BlockRotation.CLOCKWISE_90)?1:
		((rot==BlockRotation.CLOCKWISE_180)?2:3))));
	}
	@Override public BlockState mirror(BlockState st,BlockMirror mir){
		if(mir==BlockMirror.NONE)return st;
		BlockState sst=arr.get(arr.size()-1-arri).getDefaultState()
		.with(DoubleFaceFacing90.FACING,(mir==BlockMirror.FRONT_BACK)?
		DoubleFaceFacing90Mirrorer.mirrorX(st.get(DoubleFaceFacing90.FACING)):
		DoubleFaceFacing90Mirrorer.mirrorZ(st.get(DoubleFaceFacing90.FACING)));
		if(getWaterloggedProperty())sst=sst.with(WATERLOGGED,st.get(WATERLOGGED));
		return sst;
	}
	@Override public BlockState getStateForNeighborUpdate(BlockState st,net
	.minecraft.util.math.Direction d,BlockState nst,net.minecraft.world
	.WorldAccess w,BlockPos p,BlockPos np){
		if(getWaterloggedProperty()&&st.get(WATERLOGGED).booleanValue())w
		.scheduleFluidTick(p,WATER,WATER.getTickRate(w));
		return super.getStateForNeighborUpdate(st,d,nst,w,p,np);
	}
	@Override public net.minecraft.fluid.FluidState getFluidState(BlockState st){
		return(getWaterloggedProperty()&&st.get(WATERLOGGED).booleanValue())?
		WATER.getStill(false):super.getFluidState(st);
	}
}
