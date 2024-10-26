package rege.pegui.cntrafficsymbols.helper;
import rege.pegui.cntrafficsymbols.struct.DoubleFaceFacing90;
public abstract class DoubleFaceFacing90Rotator{
	private
	DoubleFaceFacing90Rotator(){throw new UnsupportedOperationException();}
	public static DoubleFaceFacing90 rotate(DoubleFaceFacing90 v,int t){
		if(v.isWall())return DoubleFaceFacing90.byID((v.id+t)%4+6);
		return DoubleFaceFacing90.byID((v.id<4)?(t+v.id)%4:((t+v.id)%2+4));
	}
}
