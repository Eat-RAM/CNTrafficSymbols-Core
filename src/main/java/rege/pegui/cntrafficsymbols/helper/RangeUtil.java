package rege.pegui.cntrafficsymbols.helper;
public class RangeUtil{
	private RangeUtil(){throw new UnsupportedOperationException();}
	public static byte closedRangeTo(double start,double end,double v){
		return(byte)((v>=start)?((v<=end)?0:1):-1);
	}
}
