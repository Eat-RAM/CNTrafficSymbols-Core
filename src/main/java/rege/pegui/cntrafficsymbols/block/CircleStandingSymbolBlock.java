package rege.pegui.cntrafficsymbols.block;
import static net.minecraft.util.shape.VoxelShapes.cuboid;

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
	@Override public VoxelShape
	getOutlineShape(BlockState st,BlockView v,BlockPos p,ShapeContext c){
		int i=st.get(rege.pegui.cntrafficsymbols.struct.Attachment3.ATTACHMENT).id;
		switch(i){
			case 0:return SHAPE0;
			case 1:return SHAPE1;
			case 2:return SHAPE2;
		}
		return null;
	}
}