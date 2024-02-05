package rege.pegui.cntrafficsymbols.struct;
import net.minecraft.state.property.EnumProperty;
public enum Attachment3 implements net.minecraft.util.StringIdentifiable{
	NONE("none",0),DOWN("down",1),UP("up",2);
	public final String str;
	public final int id;
	private Attachment3(String n,int i){str=n;id=i;}
	@Override public String asString(){return str;}
	private static final Attachment3[]VARS={NONE,DOWN,UP};
	public static Attachment3 byID(int id){return VARS[id];}
	public static final EnumProperty<Attachment3>ATTACHMENT=
	EnumProperty.of("attachment",Attachment3.class);
}
