package rege.pegui.cntrafficsymbols.helper;
import rege.pegui.cntrafficsymbols.struct.DoubleFaceFacing;
public class DoubleFaceFacingRotator{
	private DoubleFaceFacingRotator(){throw new UnsupportedOperationException();}
	public static DoubleFaceFacing rotate(DoubleFaceFacing v,int t){
		if(v.isWall())return DoubleFaceFacing.byID((v.id+t)%4+24);
		return DoubleFaceFacing.byID((v.id<16)?(t*4+v.id)%16:((t*4+v.id)%8+16));
	}
}
