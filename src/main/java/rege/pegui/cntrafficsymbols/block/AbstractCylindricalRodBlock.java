package rege.pegui.cntrafficsymbols.block;
import java.util.List;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Map.Entry;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import org.apache.commons.lang3.tuple.Triple;
import static net.minecraft.fluid.Fluids.WATER;
import static net.minecraft.state.property.Properties.WATERLOGGED;
import static net.minecraft.util.shape.VoxelShapes.cuboid;
import static net.minecraft.util.shape.VoxelShapes.empty;
import static net.minecraft.util.shape.VoxelShapes.union;
import static rege.pegui.cntrafficsymbols.Main.getWaterloggedProperty;
public abstract class AbstractCylindricalRodBlock
extends net.minecraft.block.Block implements ManagedWaterloggable{
	public static final List<Entry<Triple<Double,Double,Double>,
	Triple<Double,Double,Double>>>DOWN_CENTER_BASE=List
	.of(new SimpleImmutableEntry<>(Triple.of(.375,0.,.4375),Triple
	.of(.625,.5,.5625)),new SimpleImmutableEntry<>(Triple.of(.4375,
	0.,.375),Triple.of(.5625,.5,.625)));
	public static final VoxelShape DOWN_CENTER=
	createOffsetShape(DOWN_CENTER_BASE,0,0,0);
	public static final VoxelShape DOWN_NORTH=
	createOffsetShape(DOWN_CENTER_BASE,0,0,-.375);
	public static final VoxelShape DOWN_NORTHEAST=
	createOffsetShape(DOWN_CENTER_BASE,.375,0,-.375);
	public static final VoxelShape DOWN_EAST=
	createOffsetShape(DOWN_CENTER_BASE,.375,0,0);
	public static final VoxelShape DOWN_SOUTHEAST=
	createOffsetShape(DOWN_CENTER_BASE,.375,0,.375);
	public static final VoxelShape DOWN_SOUTH=
	createOffsetShape(DOWN_CENTER_BASE,0,0,.375);
	public static final VoxelShape DOWN_SOUTHWEST=
	createOffsetShape(DOWN_CENTER_BASE,-.375,0,.375);
	public static final VoxelShape DOWN_WEST=
	createOffsetShape(DOWN_CENTER_BASE,-.375,0,0);
	public static final VoxelShape DOWN_NORTHWEST=
	createOffsetShape(DOWN_CENTER_BASE,-.375,0,-.375);
	public static final List<Entry<Triple<Double,Double,Double>,
	Triple<Double,Double,Double>>>UP_CENTER_BASE=List
	.of(new SimpleImmutableEntry<>(Triple.of(.375,.5,.4375),Triple
	.of(.625,1.,.5625)),new SimpleImmutableEntry<>(Triple.of(.4375,
	.5,.375),Triple.of(.5625,1.,.625)));
	public static final VoxelShape UP_CENTER=
	createOffsetShape(UP_CENTER_BASE,0,0,0);
	public static final VoxelShape UP_NORTH=
	createOffsetShape(UP_CENTER_BASE,0,0,-.375);
	public static final VoxelShape UP_NORTHEAST=
	createOffsetShape(UP_CENTER_BASE,.375,0,-.375);
	public static final VoxelShape UP_EAST=
	createOffsetShape(UP_CENTER_BASE,.375,0,0);
	public static final VoxelShape UP_SOUTHEAST=
	createOffsetShape(UP_CENTER_BASE,.375,0,.375);
	public static final VoxelShape UP_SOUTH=
	createOffsetShape(UP_CENTER_BASE,0,0,.375);
	public static final VoxelShape UP_SOUTHWEST=
	createOffsetShape(UP_CENTER_BASE,-.375,0,.375);
	public static final VoxelShape UP_WEST=
	createOffsetShape(UP_CENTER_BASE,-.375,0,0);
	public static final VoxelShape UP_NORTHWEST=
	createOffsetShape(UP_CENTER_BASE,-.375,0,-.375);
	public static final List<Entry<Triple<Double,Double,Double>,
	Triple<Double,Double,Double>>>NORTH_CENTER_BASE=List
	.of(new SimpleImmutableEntry<>(Triple.of(.375,.4375,0.),Triple
	.of(.625,.5625,.5)),new SimpleImmutableEntry<>(Triple.of(.4375,
	.375,0.),Triple.of(.5625,.625,.5)));
	public static final VoxelShape NORTH_CENTER=
	createOffsetShape(NORTH_CENTER_BASE,0,0,0);
	public static final VoxelShape NORTH_UPPER=
	createOffsetShape(NORTH_CENTER_BASE,0,.375,0);
	public static final VoxelShape NORTH_UPPEREAST=
	createOffsetShape(NORTH_CENTER_BASE,.375,.375,0);
	public static final VoxelShape NORTH_EAST=
	createOffsetShape(NORTH_CENTER_BASE,.375,0,0);
	public static final VoxelShape NORTH_LOWEREAST=
	createOffsetShape(NORTH_CENTER_BASE,.375,-.375,0);
	public static final VoxelShape NORTH_LOWER=
	createOffsetShape(NORTH_CENTER_BASE,0,-.375,0);
	public static final VoxelShape NORTH_LOWERWEST=
	createOffsetShape(NORTH_CENTER_BASE,-.375,-.375,0);
	public static final VoxelShape NORTH_WEST=
	createOffsetShape(NORTH_CENTER_BASE,-.375,0,0);
	public static final VoxelShape NORTH_UPPERWEST=
	createOffsetShape(NORTH_CENTER_BASE,-.375,.375,0);
	public static final List<Entry<Triple<Double,Double,Double>,
	Triple<Double,Double,Double>>>SOUTH_CENTER_BASE=List
	.of(new SimpleImmutableEntry<>(Triple.of(.375,.4375,.5),Triple
	.of(.625,.5625,1.)),new SimpleImmutableEntry<>(Triple.of(.4375,
	.375,.5),Triple.of(.5625,.625,1.)));
	public static final VoxelShape SOUTH_CENTER=
	createOffsetShape(SOUTH_CENTER_BASE,0,0,0);
	public static final VoxelShape SOUTH_UPPER=
	createOffsetShape(SOUTH_CENTER_BASE,0,.375,0);
	public static final VoxelShape SOUTH_UPPEREAST=
	createOffsetShape(SOUTH_CENTER_BASE,.375,.375,0);
	public static final VoxelShape SOUTH_EAST=
	createOffsetShape(SOUTH_CENTER_BASE,.375,0,0);
	public static final VoxelShape SOUTH_LOWEREAST=
	createOffsetShape(SOUTH_CENTER_BASE,.375,-.375,0);
	public static final VoxelShape SOUTH_LOWER=
	createOffsetShape(SOUTH_CENTER_BASE,0,-.375,0);
	public static final VoxelShape SOUTH_LOWERWEST=
	createOffsetShape(SOUTH_CENTER_BASE,-.375,-.375,0);
	public static final VoxelShape SOUTH_WEST=
	createOffsetShape(SOUTH_CENTER_BASE,-.375,0,0);
	public static final VoxelShape SOUTH_UPPERWEST=
	createOffsetShape(SOUTH_CENTER_BASE,-.375,.375,0);
	public static final List<Entry<Triple<Double,Double,Double>,
	Triple<Double,Double,Double>>>WEST_CENTER_BASE=List
	.of(new SimpleImmutableEntry<>(Triple.of(0.,.4375,.375),Triple
	.of(.5,.5625,.625)),new SimpleImmutableEntry<>(Triple.of(0.,
	.375,.4375),Triple.of(.5,.625,.5625)));
	public static final VoxelShape WEST_CENTER=
	createOffsetShape(WEST_CENTER_BASE,0,0,0);
	public static final VoxelShape WEST_UPPER=
	createOffsetShape(WEST_CENTER_BASE,0,.375,0);
	public static final VoxelShape WEST_UPPERSOUTH=
	createOffsetShape(WEST_CENTER_BASE,0,.375,.375);
	public static final VoxelShape WEST_SOUTH=
	createOffsetShape(WEST_CENTER_BASE,0,0,.375);
	public static final VoxelShape WEST_LOWERSOUTH=
	createOffsetShape(WEST_CENTER_BASE,0,-.375,.375);
	public static final VoxelShape WEST_LOWER=
	createOffsetShape(WEST_CENTER_BASE,0,-.375,0);
	public static final VoxelShape WEST_LOWERNORTH=
	createOffsetShape(WEST_CENTER_BASE,0,-.375,-.375);
	public static final VoxelShape WEST_NORTH=
	createOffsetShape(WEST_CENTER_BASE,0,0,-.375);
	public static final VoxelShape WEST_UPPERNORTH=
	createOffsetShape(WEST_CENTER_BASE,0,.375,-.375);
	public static final List<Entry<Triple<Double,Double,Double>,
	Triple<Double,Double,Double>>>EAST_CENTER_BASE=List
	.of(new SimpleImmutableEntry<>(Triple.of(.5,.4375,.375),Triple
	.of(1.,.5625,.625)),new SimpleImmutableEntry<>(Triple.of(.5,
	.375,.4375),Triple.of(1.,.625,.5625)));
	public static final VoxelShape EAST_CENTER=
	createOffsetShape(EAST_CENTER_BASE,0,0,0);
	public static final VoxelShape EAST_UPPER=
	createOffsetShape(EAST_CENTER_BASE,0,.375,0);
	public static final VoxelShape EAST_UPPERSOUTH=
	createOffsetShape(EAST_CENTER_BASE,0,.375,.375);
	public static final VoxelShape EAST_SOUTH=
	createOffsetShape(EAST_CENTER_BASE,0,0,.375);
	public static final VoxelShape EAST_LOWERSOUTH=
	createOffsetShape(EAST_CENTER_BASE,0,-.375,.375);
	public static final VoxelShape EAST_LOWER=
	createOffsetShape(EAST_CENTER_BASE,0,-.375,0);
	public static final VoxelShape EAST_LOWERNORTH=
	createOffsetShape(EAST_CENTER_BASE,0,-.375,-.375);
	public static final VoxelShape EAST_NORTH=
	createOffsetShape(EAST_CENTER_BASE,0,0,-.375);
	public static final VoxelShape EAST_UPPERNORTH=
	createOffsetShape(EAST_CENTER_BASE,0,.375,-.375);
	public static final List<Entry<Triple<Double,Double,Double>,
	Triple<Double,Double,Double>>>X_CENTER_BASE=List
	.of(new SimpleImmutableEntry<>(Triple.of(0.,.4375,.375),Triple
	.of(1.,.5625,.625)),new SimpleImmutableEntry<>(Triple.of(0.,
	.375,.4375),Triple.of(1.,.625,.5625)));
	public static final VoxelShape X_CENTER=
	createOffsetShape(X_CENTER_BASE,0,0,0);
	public static final VoxelShape X_UPPER=
	createOffsetShape(X_CENTER_BASE,0,.375,0);
	public static final VoxelShape X_UPPERSOUTH=
	createOffsetShape(X_CENTER_BASE,0,.375,.375);
	public static final VoxelShape X_SOUTH=
	createOffsetShape(X_CENTER_BASE,0,0,.375);
	public static final VoxelShape X_LOWERSOUTH=
	createOffsetShape(X_CENTER_BASE,0,-.375,.375);
	public static final VoxelShape X_LOWER=
	createOffsetShape(X_CENTER_BASE,0,-.375,0);
	public static final VoxelShape X_LOWERNORTH=
	createOffsetShape(X_CENTER_BASE,0,-.375,-.375);
	public static final VoxelShape X_NORTH=
	createOffsetShape(X_CENTER_BASE,0,0,-.375);
	public static final VoxelShape X_UPPERNORTH=
	createOffsetShape(X_CENTER_BASE,0,.375,-.375);
	public static final List<Entry<Triple<Double,Double,Double>,
	Triple<Double,Double,Double>>>Y_CENTER_BASE=List
	.of(new SimpleImmutableEntry<>(Triple.of(.375,0.,.4375),Triple
	.of(.625,1.,.5625)),new SimpleImmutableEntry<>(Triple.of(.4375,
	0.,.375),Triple.of(.5625,1.,.625)));
	public static final VoxelShape Y_CENTER=
	createOffsetShape(Y_CENTER_BASE,0,0,0);
	public static final VoxelShape Y_NORTH=
	createOffsetShape(Y_CENTER_BASE,0,0,-.375);
	public static final VoxelShape Y_NORTHEAST=
	createOffsetShape(Y_CENTER_BASE,.375,0,-.375);
	public static final VoxelShape Y_EAST=
	createOffsetShape(Y_CENTER_BASE,.375,0,0);
	public static final VoxelShape Y_SOUTHEAST=
	createOffsetShape(Y_CENTER_BASE,.375,0,.375);
	public static final VoxelShape Y_SOUTH=
	createOffsetShape(Y_CENTER_BASE,0,0,.375);
	public static final VoxelShape Y_SOUTHWEST=
	createOffsetShape(Y_CENTER_BASE,-.375,0,.375);
	public static final VoxelShape Y_WEST=
	createOffsetShape(Y_CENTER_BASE,-.375,0,0);
	public static final VoxelShape Y_NORTHWEST=
	createOffsetShape(Y_CENTER_BASE,-.375,0,-.375);
	public static final List<Entry<Triple<Double,Double,Double>,
	Triple<Double,Double,Double>>>Z_CENTER_BASE=List
	.of(new SimpleImmutableEntry<>(Triple.of(.375,.4375,0.),Triple
	.of(.625,.5625,1.)),new SimpleImmutableEntry<>(Triple.of(.4375,
	.375,0.),Triple.of(.5625,.625,1.)));
	public static final VoxelShape Z_CENTER=
	createOffsetShape(Z_CENTER_BASE,0,0,0);
	public static final VoxelShape Z_UPPER=
	createOffsetShape(Z_CENTER_BASE,0,.375,0);
	public static final VoxelShape Z_UPPEREAST=
	createOffsetShape(Z_CENTER_BASE,.375,.375,0);
	public static final VoxelShape Z_EAST=
	createOffsetShape(Z_CENTER_BASE,.375,0,0);
	public static final VoxelShape Z_LOWEREAST=
	createOffsetShape(Z_CENTER_BASE,.375,-.375,0);
	public static final VoxelShape Z_LOWER=
	createOffsetShape(Z_CENTER_BASE,0,-.375,0);
	public static final VoxelShape Z_LOWERWEST=
	createOffsetShape(Z_CENTER_BASE,-.375,-.375,0);
	public static final VoxelShape Z_WEST=
	createOffsetShape(Z_CENTER_BASE,-.375,0,0);
	public static final VoxelShape Z_UPPERWEST=
	createOffsetShape(Z_CENTER_BASE,-.375,.375,0);
	public static final List<Entry<Triple<Double,Double,Double>,Triple<Double,
	Double,Double>>>INTERSECT_CENTER_BASE=List
	.of(new SimpleImmutableEntry<>(Triple.of(.375,.4375,.4375),Triple
	.of(.625,.5625,.5625)),new SimpleImmutableEntry<>(Triple
	.of(.4375,.375,.4375),Triple.of(.5625,.625,.5625)),
	new SimpleImmutableEntry<>(Triple.of(.4375,.4375,.375),Triple
	.of(.5625,.5625,.625)));
	public static final VoxelShape INTERSECT_CENTER=
	createOffsetShape(INTERSECT_CENTER_BASE,0,0,0);
	public static final VoxelShape INTERSECT_UPPER=
	createOffsetShape(INTERSECT_CENTER_BASE,0,.375,0);
	public static final VoxelShape INTERSECT_UPPERNORTH=
	createOffsetShape(INTERSECT_CENTER_BASE,0,.375,-.375);
	public static final VoxelShape INTERSECT_UPPERNORTHEAST=
	createOffsetShape(INTERSECT_CENTER_BASE,.375,.375,-.375);
	public static VoxelShape
	createOffsetShape(Iterable<Entry<Triple<Double,Double,Double>,Triple<Double,
	Double,Double>>>shape,double offX,double offY,double offZ){
		java.util.Iterator<Entry<Triple<Double,Double,Double>,Triple<Double,Double,
		Double>>>itt=shape.iterator();if(!(itt.hasNext()))return empty();
		Entry<Triple<Double,Double,Double>,Triple<Double,Double,Double>>e=itt.next();
		VoxelShape r=cuboid(e.getKey().getLeft().doubleValue()+offX,e.getKey()
		.getMiddle().doubleValue()+offY,e.getKey().getRight().doubleValue()+offZ,e
		.getValue().getLeft().doubleValue()+offX,e.getValue().getMiddle()
		.doubleValue()+offY,e.getValue().getRight().doubleValue()+offZ);
		while(itt.hasNext()){
			e=itt.next();r=union(r,cuboid(e.getKey().getLeft().doubleValue()+offX,e
			.getKey().getMiddle().doubleValue()+offY,e.getKey().getRight().doubleValue()
			+offZ,e.getValue().getLeft().doubleValue()+offX,e.getValue().getMiddle()
			.doubleValue()+offY,e.getValue().getRight().doubleValue()+offZ));
		}
		return r;
	}
	protected AbstractCylindricalRodBlock(Settings s){
		super(s);if(getWaterloggedProperty())setDefaultState(getDefaultState()
		.with(WATERLOGGED,false));
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
