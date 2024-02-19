package rege.pegui.cntrafficsymbols.struct;
import net.minecraft.state.property.EnumProperty;
public enum AxisPosition9 implements net.minecraft.util.StringIdentifiable{
	X_CENTER("x_center",0),X_UPPER("x_upper",1),X_UPPERSOUTH("x_uppersouth",2),
	X_SOUTH("x_south",3),X_LOWERSOUTH("x_lowersouth",4),X_LOWER("x_lower",5),
	X_LOWERNORTH("x_lowernorth",6),X_NORTH("x_north",7),
	X_UPPERNORTH("x_uppernorth",8),Y_CENTER("y_center",9),Y_NORTH("y_north",10),
	Y_NORTHEAST("y_northeast",11),Y_EAST("y_east",12),Y_SOUTHEAST("y_southeast",
	13),Y_SOUTH("y_south",14),Y_SOUTHWEST("y_southwest",15),Y_WEST("y_west",16),
	Y_NORTHWEST("y_northwest",17),Z_CENTER("z_center",18),Z_UPPER("z_upper",19),
	Z_UPPEREAST("z_uppereast",20),Z_EAST("z_east",21),Z_LOWEREAST("z_lowereast",
	22),Z_LOWER("z_lower",23),Z_LOWERWEST("z_lowerwest",24),Z_WEST("z_west",25),
	Z_UPPERWEST("z_upperwest",26);
	public final String str;
	public final int id;
	private AxisPosition9(String n,int i){str=n;id=i;}
	@Override public String asString(){return str;}
	private static final AxisPosition9[]VARS={
		X_CENTER,X_UPPER,X_UPPERSOUTH,X_SOUTH,X_LOWERSOUTH,X_LOWER,X_LOWERNORTH,
		X_NORTH,X_UPPERNORTH,Y_CENTER,Y_NORTH,Y_NORTHEAST,Y_EAST,Y_SOUTHEAST,Y_SOUTH,
		Y_SOUTHWEST,Y_WEST,Y_NORTHEAST,Z_CENTER,Z_UPPER,Z_UPPEREAST,Z_EAST,
		Z_LOWEREAST,Z_LOWER,Z_LOWERWEST,Z_WEST,Z_UPPERWEST
	};
	public static AxisPosition9 byID(int id){return VARS[id];}
	public static final EnumProperty<AxisPosition9>POSITION=
	EnumProperty.of("position",AxisPosition9.class);
}
