package rege.pegui.cntrafficsymbols.block;
import static net.minecraft.util.shape.VoxelShapes.cuboid;
import static net.minecraft.util.shape.VoxelShapes.union;
import net.minecraft.util.shape.VoxelShape;
public class CircleStandingSymbolBlock
extends Attachment3DoubleFaceFacingBlock{
	public CircleStandingSymbolBlock(Settings s){super(s);}
	public static final VoxelShape
	SHAPE0=cuboid(.3125,.3125,.3125,.6875,.6875,.6875);
	public static final VoxelShape SHAPE1=cuboid(.3125,0,.3125,.6875,.375,.6875);
	public static final VoxelShape SHAPE2=cuboid(.3125,.625,.3125,.6875,1,.6875);
	public static final VoxelShape SHAPE0N=union(
		cuboid(.125,.40625,.9375,.875,.59375,1),
		cuboid(.15625,.34375,.9375,.84375,.65625,1),
		cuboid(.1875,.28125,.9375,.8125,.71875,1),
		cuboid(.21875,.25,.9375,.78125,.75,1),
		cuboid(.25,.21875,.9375,.75,.78125,1),
		cuboid(.28125,.1875,.9375,.71875,.8125,1),
		cuboid(.34375,.15625,.9375,.65625,.84375,1),
		cuboid(.40625,.125,.9375,.59375,.875,1)
	);
	public static final VoxelShape SHAPE0E=union(
		cuboid(0,.125,.40625,.0625,.875,.59375),
		cuboid(0,.15625,.34375,.0625,.84375,.65625),
		cuboid(0,.1875,.28125,.0625,.8125,.71875),
		cuboid(0,.21875,.25,.0625,.78125,.75),
		cuboid(0,.25,.21875,.0625,.75,.78125),
		cuboid(0,.28125,.1875,.0625,.71875,.8125),
		cuboid(0,.34375,.15625,.0625,.65625,.84375),
		cuboid(0,.40625,.125,.0625,.59375,.875)
	);
	public static final VoxelShape SHAPE0S=union(
		cuboid(.125,.40625,0,.875,.59375,.0625),
		cuboid(.15625,.34375,0,.84375,.65625,.0625),
		cuboid(.1875,.28125,0,.8125,.71875,.0625),
		cuboid(.21875,.25,0,.78125,.75,.0625),
		cuboid(.25,.21875,0,.75,.78125,.0625),
		cuboid(.28125,.1875,0,.71875,.8125,.0625),
		cuboid(.34375,.15625,0,.65625,.84375,.0625),
		cuboid(.40625,.125,0,.59375,.875,.0625)
	);
	public static final VoxelShape SHAPE0W=union(
		cuboid(.9375,.125,.40625,1,.875,.59375),
		cuboid(.9375,.15625,.34375,1,.84375,.65625),
		cuboid(.9375,.1875,.28125,1,.8125,.71875),
		cuboid(.9375,.21875,.25,1,.78125,.75),
		cuboid(.9375,.25,.21875,1,.75,.78125),
		cuboid(.9375,.28125,.1875,1,.71875,.8125),
		cuboid(.9375,.34375,.15625,1,.65625,.84375),
		cuboid(.9375,.40625,.125,1,.59375,.875)
	);
	public static final VoxelShape SHAPE1N=union(
		cuboid(.125,.28125,.9375,.875,.46875,1),
		cuboid(.15625,.21875,.9375,.84375,.53125,1),
		cuboid(.1875,.15625,.9375,.8125,.59375,1),
		cuboid(.21875,.125,.9375,.78125,.625,1),
		cuboid(.25,.09375,.9375,.75,.65625,1),
		cuboid(.28125,.0625,.9375,.71875,.6875,1),
		cuboid(.34375,.03125,.9375,.65625,.71875,1),
		cuboid(.40625,0,.9375,.59375,.75,1)
	);
	public static final VoxelShape SHAPE1E=union(
		cuboid(0,0,.40625,.0625,.75,.59375),
		cuboid(0,.03125,.34375,.0625,.71875,.65625),
		cuboid(0,.0625,.28125,.0625,.6875,.71875),
		cuboid(0,.09375,.25,.0625,.65625,.75),
		cuboid(0,.125,.21875,.0625,.625,.78125),
		cuboid(0,.15625,.1875,.0625,.59375,.8125),
		cuboid(0,.21875,.15625,.0625,.53125,.84375),
		cuboid(0,.28125,.125,.0625,.46875,.875)
	);
	public static final VoxelShape SHAPE1S=union(
		cuboid(.125,.28125,0,.875,.46875,.0625),
		cuboid(.15625,.21875,0,.84375,.53125,.0625),
		cuboid(.1875,.15625,0,.8125,.59375,.0625),
		cuboid(.21875,.125,0,.78125,.625,.0625),
		cuboid(.25,.09375,0,.75,.65625,.0625),
		cuboid(.28125,.0625,0,.71875,.6875,.0625),
		cuboid(.34375,.03125,0,.65625,.71875,.0625),
		cuboid(.40625,0,0,.59375,.75,.0625)
	);
	public static final VoxelShape SHAPE1W=union(
		cuboid(.9375,0,.40625,1,.75,.59375),
		cuboid(.9375,.03125,.34375,1,.71875,.65625),
		cuboid(.9375,.0625,.28125,1,.6875,.71875),
		cuboid(.9375,.09375,.25,1,.65625,.75),
		cuboid(.9375,.125,.21875,1,.625,.78125),
		cuboid(.9375,.15625,.1875,1,.59375,.8125),
		cuboid(.9375,.21875,.15625,1,.53125,.84375),
		cuboid(.9375,.28125,.125,1,.46875,.875)
	);
	public static final VoxelShape SHAPE2N=union(
		cuboid(.125,.53125,.9375,.875,.71875,1),
		cuboid(.15625,.46875,.9375,.84375,.78125,1),
		cuboid(.1875,.40625,.9375,.8125,.84375,1),
		cuboid(.21875,.375,.9375,.78125,.875,1),
		cuboid(.25,.34375,.9375,.75,.90625,1),
		cuboid(.28125,.3125,.9375,.71875,.9375,1),
		cuboid(.34375,.28125,.9375,.65625,.96875,1),
		cuboid(.40625,.25,.9375,.59375,1,1)
	);
	public static final VoxelShape SHAPE2E=union(
		cuboid(0,.25,.40625,.0625,1,.59375),
		cuboid(0,.28125,.34375,.0625,.96875,.65625),
		cuboid(0,.3125,.28125,.0625,.9375,.71875),
		cuboid(0,.34375,.25,.0625,.90625,.75),
		cuboid(0,.375,.21875,.0625,.875,.78125),
		cuboid(0,.40625,.1875,.0625,.84375,.8125),
		cuboid(0,.46875,.15625,.0625,.78125,.84375),
		cuboid(0,.53125,.125,.0625,.71875,.875)
	);
	public static final VoxelShape SHAPE2S=union(
		cuboid(.125,.53125,0,.875,.71875,.0625),
		cuboid(.15625,.46875,0,.84375,.78125,.0625),
		cuboid(.1875,.40625,0,.8125,.84375,.0625),
		cuboid(.21875,.375,0,.78125,.875,.0625),
		cuboid(.25,.34375,0,.75,.90625,.0625),
		cuboid(.28125,.3125,0,.71875,.9375,.0625),
		cuboid(.34375,.28125,0,.65625,.96875,.0625),
		cuboid(.40625,.25,0,.59375,1,.0625)
	);
	public static final VoxelShape SHAPE2W=union(
		cuboid(.9375,.25,.40625,1,1,.59375),
		cuboid(.9375,.28125,.34375,1,.96875,.65625),
		cuboid(.9375,.3125,.28125,1,.9375,.71875),
		cuboid(.9375,.34375,.25,1,.90625,.75),
		cuboid(.9375,.375,.21875,1,.875,.78125),
		cuboid(.9375,.40625,.1875,1,.84375,.8125),
		cuboid(.9375,.46875,.15625,1,.78125,.84375),
		cuboid(.9375,.53125,.125,1,.71875,.875)
	);
	@Override public VoxelShape getOutlineShape(net.minecraft.block.BlockState st,
	net.minecraft.world.BlockView v,net.minecraft.util.math.BlockPos p,
	net.minecraft.block.ShapeContext c){
		int f=st.get(rege.pegui.cntrafficsymbols.struct.DoubleFaceFacing.FACING).id;
		int i=st.get(rege.pegui.cntrafficsymbols.struct.Attachment3.ATTACHMENT).id;
		switch(f){
			case 24:switch(i){
				case 1:return SHAPE1N;
				case 2:return SHAPE2N;
				default:return SHAPE0N;
			}
			case 25:switch(i){
				case 1:return SHAPE1E;
				case 2:return SHAPE2E;
				default:return SHAPE0E;
			}
			case 26:switch(i){
				case 1:return SHAPE1S;
				case 2:return SHAPE2S;
				default:return SHAPE0S;
			}
			case 27:switch(i){
				case 1:return SHAPE1W;
				case 2:return SHAPE2W;
				default:return SHAPE0W;
			}
		}
		switch(i){
			case 1:return SHAPE1;
			case 2:return SHAPE2;
			default:return SHAPE0;
		}
	}
}
