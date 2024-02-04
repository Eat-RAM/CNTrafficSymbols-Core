package rege.pegui.cntrafficsymbols.block;
import static net.minecraft.util.shape.VoxelShapes.cuboid;
import static net.minecraft.util.shape.VoxelShapes.union;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
public class CircleStandingSymbolBlock
extends Attachment3DoubleFaceFacingBlock{
	public CircleStandingSymbolBlock(Settings s){super(s);}
	public static final VoxelShape
	SHAPE0=cuboid(.3125,.3125,.3125,.6875,.6875,.6875);
	public static final VoxelShape SHAPE1=cuboid(.3125,0,.3125,.6875,.375,.6875);
	public static final VoxelShape SHAPE2=cuboid(.3125,.625,.3125,.6875,1,.6875);
	public static final VoxelShape SHAPE_N=union(
		cuboid(.125,.40625,.9375,.875,.59375,1),
		cuboid(.15625,.34375,.9375,.84375,.65625,1),
		cuboid(.1875,.28125,.9375,.8125,.71875,1),
		cuboid(.21875,.25,.9375,.78125,.75,1),
		cuboid(.25,.21875,.9375,.75,.78125,1),
		cuboid(.28125,.1875,.9375,.71875,.8125,1),
		cuboid(.34375,.15625,.9375,.65625,.84375,1),
		cuboid(.40625,.125,.9375,.59375,.875,1)
	);
	public static final VoxelShape SHAPE_E=union(
		cuboid(0,.125,.40625,.0625,.875,.59375),
		cuboid(0,.15625,.34375,.0625,.84375,.65625),
		cuboid(0,.1875,.28125,.0625,.8125,.71875),
		cuboid(0,.21875,.25,.0625,.78125,.75),
		cuboid(0,.25,.21875,.0625,.75,.78125),
		cuboid(0,.28125,.1875,.0625,.71875,.8125),
		cuboid(0,.34375,.15625,.0625,.65625,.84375),
		cuboid(0,.40625,.125,.0625,.59375,.875)
	);
	public static final VoxelShape SHAPE_S=union(
		cuboid(.125,.40625,0,.875,.59375,.0625),
		cuboid(.15625,.34375,0,.84375,.65625,.0625),
		cuboid(.1875,.28125,0,.8125,.71875,.0625),
		cuboid(.21875,.25,0,.78125,.75,.0625),
		cuboid(.25,.21875,0,.75,.78125,.0625),
		cuboid(.28125,.1875,0,.71875,.8125,.0625),
		cuboid(.34375,.15625,0,.65625,.84375,.0625),
		cuboid(.40625,.125,0,.59375,.875,.0625)
	);
	public static final VoxelShape SHAPE_W=union(
		cuboid(.9375,.125,.40625,1,.875,.59375),
		cuboid(.9375,.15625,.34375,1,.84375,.65625),
		cuboid(.9375,.1875,.28125,1,.8125,.71875),
		cuboid(.9375,.21875,.25,1,.78125,.75),
		cuboid(.9375,.25,.21875,1,.75,.78125),
		cuboid(.9375,.28125,.1875,1,.71875,.8125),
		cuboid(.9375,.34375,.15625,1,.65625,.84375),
		cuboid(.9375,.40625,.125,1,.59375,.875)
	);
	@Override public VoxelShape
	getOutlineShape(BlockState st,BlockView v,BlockPos p,ShapeContext c){
		int f=st.get(rege.pegui.cntrafficsymbols.struct.DoubleFaceFacing.FACING).id;
		switch(f){
			case 24:return SHAPE_N;
			case 25:return SHAPE_E;
			case 26:return SHAPE_S;
			case 27:return SHAPE_W;
		}
		int i=st.get(rege.pegui.cntrafficsymbols.struct.Attachment3.ATTACHMENT).id;
		switch(i){
			case 0:return SHAPE0;
			case 1:return SHAPE1;
			case 2:return SHAPE2;
		}
		return null;
	}
}
