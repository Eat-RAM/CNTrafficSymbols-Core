package rege.pegui.cntrafficsymbols.block;
import static net.minecraft.fluid.Fluids.WATER;
import static net.minecraft.state.property.Properties.WATERLOGGED;
import static rege.pegui.cntrafficsymbols.Main.getWaterloggedProperty;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;
public class SquareQuarterDefaulted3Block extends Block
implements ManagedWaterloggable{
	public static final IntProperty NORTHWEST=IntProperty.of("northwest",0,2);
	public static final IntProperty NORTHEAST=IntProperty.of("northeast",0,2);
	public static final IntProperty SOUTHWEST=IntProperty.of("southwest",0,2);
	public static final IntProperty SOUTHEAST=IntProperty.of("southeast",0,2);
	private Item itm0;
	private Item itm1;
	private Item itm2;
	public SquareQuarterDefaulted3Block(Item itm0,Item itm1,Item itm2,Settings s){
		super(s);this.itm0=itm0;this.itm1=itm1;this.itm2=itm2;BlockState st=
		getDefaultState().with(NORTHWEST,0).with(NORTHEAST,0).with(SOUTHWEST,0)
		.with(SOUTHEAST,0);if(getWaterloggedProperty())st=st.with(WATERLOGGED,false);
		setDefaultState(st);
	}
	public SquareQuarterDefaulted3Block(Settings s){
		super(s);BlockState st=getDefaultState().with(NORTHWEST,0).with(NORTHEAST,0)
		.with(SOUTHWEST,0).with(SOUTHEAST,0);
		if(getWaterloggedProperty())st=st.with(WATERLOGGED,false);
		setDefaultState(st);
	}
	@Override public ItemStack
	getPickStack(net.minecraft.world.BlockView v,BlockPos p,BlockState st){
		byte s1=0;byte s2=0;switch(st.get(NORTHWEST).intValue()){
			case 1:s1++;break;
			case 2:s2++;break;
		}
		switch(st.get(NORTHEAST).intValue()){
			case 1:s1++;break;
			case 2:s2++;break;
		}
		switch(st.get(SOUTHWEST).intValue()){
			case 1:s1++;break;
			case 2:s2++;break;
		}
		switch(st.get(SOUTHEAST).intValue()){
			case 1:s1++;break;
			case 2:s2++;break;
		}
		if(s1==(byte)0){
			return new ItemStack((s2==(byte)0)?itm0:itm2);
		}
		if(s2==(byte)0)return new ItemStack(itm1);
		return new ItemStack((Math.random()<.5)?itm1:itm2);
	}
	@Override protected void appendProperties(Builder<Block,BlockState>bd){
		bd.add(NORTHWEST,NORTHEAST,SOUTHWEST,SOUTHEAST);
		if(getWaterloggedProperty())bd.add(WATERLOGGED);
	}
	public boolean shouldHardcodedDrop(World w,PlayerEntity pl,BlockPos p,
	BlockState st,@Nullable BlockEntity ett,ItemStack tool){return true;}
	@Override public void afterBreak(World w,PlayerEntity pl,BlockPos p,
	BlockState st,@Nullable BlockEntity ett,ItemStack tool) {
		super.afterBreak(w,pl,p,st,ett,tool);
		if((w instanceof net.minecraft.server.world.ServerWorld)&&
		shouldHardcodedDrop(w,pl,p,st,ett,tool)){
			byte s1=0;byte s2=0;switch(st.get(NORTHWEST).intValue()){
				case 1:s1++;break;
				case 2:s2++;break;
			}
			switch(st.get(NORTHEAST).intValue()){
				case 1:s1++;break;
				case 2:s2++;break;
			}
			switch(st.get(SOUTHWEST).intValue()){
				case 1:s1++;break;
				case 2:s2++;break;
			}
			switch(st.get(SOUTHEAST).intValue()){
				case 1:s1++;break;
				case 2:s2++;break;
			}
			if(s1==(byte)0&&s2==(byte)0){
				if(itm0!=null)
				ItemScatterer.spawn(w,p.getX(),p.getY(),p.getZ(),new ItemStack(itm0));
			}else{
				if(itm1!=null)
				ItemScatterer.spawn(w,p.getX(),p.getY(),p.getZ(),new ItemStack(itm1,s1));
				if(itm2!=null)
				ItemScatterer.spawn(w,p.getX(),p.getY(),p.getZ(),new ItemStack(itm2,s2));
			}
		}
	}
	@Override public BlockState getPlacementState(ItemPlacementContext ctx){
		BlockState st=ctx.getWorld().getBlockState(ctx.getBlockPos());
		if(st.isOf(this)){
			byte repl=1;
			if(ctx.getStack().isOf(itm2)){repl++;}else if(!ctx.getStack().isOf(itm1)){
				throw new IllegalStateException("Unexpected item "+ctx.getStack()
				.getItem());
			}
			return st.with(readyToReplace(st,ctx),(int)repl);
		}
		if(ctx.getStack().isOf(itm0)){
			if(getWaterloggedProperty())return getDefaultState().with(WATERLOGGED,
			ctx.getWorld().getFluidState(ctx.getBlockPos()).getFluid()==WATER);
			return getDefaultState();
		}
		byte repl=1;
		if(ctx.getStack().isOf(itm2)){repl++;}else if(!ctx.getStack().isOf(itm1)){
			throw new IllegalStateException("Unexpected item "+ctx.getStack()
			.getItem());
		}
		BlockState sst=getDefaultState().with(readyToReplace(null,ctx),(int)repl);
		if(getWaterloggedProperty())return sst.with(WATERLOGGED,ctx.getWorld()
		.getFluidState(ctx.getBlockPos()).getFluid()==WATER);
		return sst;
	}
	@Override public boolean canReplace(BlockState st,ItemPlacementContext ctx){
		return(ctx.getStack().isOf(itm1)||ctx.getStack().isOf(itm2))&&
		readyToReplace(st,ctx)!=null;
	}
	@Nullable public static IntProperty
	readyToReplace(@Nullable BlockState st,ItemPlacementContext ctx){
		int nw=(st!=null)?st.get(NORTHWEST).intValue():0;
		int ne=(st!=null)?st.get(NORTHEAST).intValue():0;
		int sw=(st!=null)?st.get(SOUTHWEST).intValue():0;
		int se=(st!=null)?st.get(SOUTHEAST).intValue():0;
		if(st!=null&&nw==0&&ne==0&&sw==0&&se==0)return null;
		switch(ctx.getSide()){
			case DOWN:case UP:{
				double rx=ctx.getHitPos().x-ctx.getBlockPos().getX();
				double rz=ctx.getHitPos().z-ctx.getBlockPos().getZ();if(rx<.5){
					if(rz<.5)return(nw==0)?NORTHWEST:null;
					return(sw==0)?SOUTHWEST:null;
				}
				if(rz<.5)return(ne==0)?NORTHEAST:null;
				return(se==0)?SOUTHEAST:null;
			}
			case NORTH:{
				double rx=ctx.getHitPos().x-ctx.getBlockPos().getX();if(rx<.5){
					if(sw==0)return SOUTHWEST;
					return(nw==0)?NORTHWEST:null;
				}
				if(se==0)return SOUTHEAST;
				return(ne==0)?NORTHEAST:null;
			}
			case SOUTH:{
				double rx=ctx.getHitPos().x-ctx.getBlockPos().getX();if(rx<.5){
					if(nw==0)return NORTHWEST;
					return(sw==0)?SOUTHWEST:null;
				}
				if(ne==0)return NORTHEAST;
				return(se==0)?SOUTHEAST:null;
			}
			case WEST:{
				double rz=ctx.getHitPos().z-ctx.getBlockPos().getZ();if(rz<.5){
					if(ne==0)return NORTHEAST;
					return(nw==0)?NORTHWEST:null;
				}
				if(se==0)return SOUTHEAST;
				return(sw==0)?SOUTHWEST:null;
			}
			case EAST:{
				double rz=ctx.getHitPos().z-ctx.getBlockPos().getZ();if(rz<.5){
					if(nw==0)return NORTHWEST;
					return(ne==0)?NORTHEAST:null;
				}
				if(sw==0)return SOUTHWEST;
				return(se==0)?SOUTHEAST:null;
			}
			default:return null;
		}
	}
	@Override public BlockState rotate(BlockState st,BlockRotation rotation){
		switch(rotation){
			case CLOCKWISE_90:return st.with(NORTHWEST,st.get(SOUTHWEST))
			.with(NORTHEAST,st.get(NORTHWEST)).with(SOUTHEAST,st.get(NORTHEAST))
			.with(SOUTHWEST,st.get(SOUTHEAST));
			case CLOCKWISE_180:return st.with(NORTHWEST,st.get(SOUTHEAST))
			.with(NORTHEAST,st.get(SOUTHWEST)).with(SOUTHEAST,st.get(NORTHWEST))
			.with(SOUTHWEST,st.get(NORTHEAST));
			case COUNTERCLOCKWISE_90:return st.with(NORTHWEST,st.get(NORTHEAST))
			.with(NORTHEAST,st.get(SOUTHEAST)).with(SOUTHEAST,st.get(SOUTHWEST))
			.with(SOUTHWEST,st.get(NORTHWEST));
			default:return st;
		}
	}
	@Override public BlockState mirror(BlockState st,BlockMirror mirror){
		switch(mirror){
			case FRONT_BACK:return st.with(NORTHWEST,st.get(NORTHEAST))
			.with(NORTHEAST,st.get(NORTHWEST)).with(SOUTHWEST,st.get(SOUTHEAST))
			.with(SOUTHEAST,st.get(SOUTHWEST));
			case LEFT_RIGHT:return st.with(NORTHWEST,st.get(SOUTHWEST))
			.with(NORTHEAST,st.get(SOUTHEAST)).with(SOUTHWEST,st.get(NORTHWEST))
			.with(SOUTHEAST,st.get(NORTHEAST));
			default:return st;
		}
	}
	@Override public BlockState getStateForNeighborUpdate(BlockState st,net
	.minecraft.util.math.Direction d,BlockState nst,WorldAccess w,BlockPos p
	,BlockPos np){
		if(getWaterloggedProperty()&&st.get(WATERLOGGED).booleanValue())w
		.scheduleFluidTick(p,WATER,WATER.getTickRate(w));
		return super.getStateForNeighborUpdate(st,d,nst,w,p,np);
	}
	@Override public net.minecraft.fluid.FluidState getFluidState(BlockState st){
		return(getWaterloggedProperty()&&st.get(WATERLOGGED).booleanValue())?
		WATER.getStill(false):super.getFluidState(st);
	}
	@Nullable public Item setItm0(@Nullable Item v){
		Item r=itm0;if(r==null)itm0=v;
		return v;
	}
	@Nullable public Item setItm1(@Nullable Item v){
		Item r=itm1;if(r==null)itm1=v;
		return v;
	}
	@Nullable public Item setItm2(@Nullable Item v){
		Item r=itm2;if(r==null)itm2=v;
		return v;
	}
}
