package rege.pegui.cntrafficsymbols.helper;
import rege.pegui.cntrafficsymbols.struct.DoubleFaceFacing;
public class DoubleFaceFacingMirrorer{
	private DoubleFaceFacingMirrorer(){throw new UnsupportedOperationException();}
	public static DoubleFaceFacing mirrorX(DoubleFaceFacing v){
		if(v.isWall())return(v.id==25||v.id==27)?DoubleFaceFacing.byID(52-v.id):v;
		return(v.id>0)?((v.id>15)?((v.id>16)?DoubleFaceFacing.byID(40-v.id):v):
		DoubleFaceFacing.byID(16-v.id)):v;
	}
	public static DoubleFaceFacing mirrorZ(DoubleFaceFacing v){
		if(v.isWall())return(v.id==24||v.id==26)?DoubleFaceFacing.byID(50-v.id):v;
		return(v.id>8)?((v.id>15)?((v.id>16)?DoubleFaceFacing.byID(40-v.id):v):
		DoubleFaceFacing.byID(24-v.id)):DoubleFaceFacing.byID(8-v.id);
	}
}
