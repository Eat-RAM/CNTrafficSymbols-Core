package rege.pegui.cntrafficsymbols.helper;
import rege.pegui.cntrafficsymbols.struct.DoubleFaceFacing90;
public abstract class DoubleFaceFacing90Mirrorer{
	private
	DoubleFaceFacing90Mirrorer(){throw new UnsupportedOperationException();}
	public static DoubleFaceFacing90 mirrorX(DoubleFaceFacing90 v){
		switch(v.id){
			case 1:return DoubleFaceFacing90.EAST;
			case 3:return DoubleFaceFacing90.WEST;
			case 7:return DoubleFaceFacing90.WALL_WEST;
			case 9:return DoubleFaceFacing90.WALL_EAST;
			default:return v;
		}
	}
	public static DoubleFaceFacing90 mirrorZ(DoubleFaceFacing90 v){
		switch(v.id){
			case 0:return DoubleFaceFacing90.NORTH;
			case 2:return DoubleFaceFacing90.SOUTH;
			case 6:return DoubleFaceFacing90.WALL_SOUTH;
			case 8:return DoubleFaceFacing90.WALL_NORTH;
			default:return v;
		}
	}
}
