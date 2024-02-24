package rege.pegui.cntrafficsymbols.struct;
import net.minecraft.state.property.EnumProperty;
public enum FacePosition9 implements net.minecraft.util.StringIdentifiable{
	WEST_CENTER("west_center",0),WEST_UPPER("west_upper",1),
	WEST_UPPERSOUTH("west_uppersouth",2),WEST_SOUTH("west_south",3),
	WEST_LOWERSOUTH("west_lowersouth",4),WEST_LOWER("west_lower",5),
	WEST_LOWERNORTH("west_lowernorth",6),WEST_NORTH("west_north",7),
	WEST_UPPERNORTH("west_uppernorth",8),EAST_CENTER("east_center",9),
	EAST_UPPER("east_upper",10),EAST_UPPERSOUTH("east_uppersouth",11),
	EAST_SOUTH("east_south",12),EAST_LOWERSOUTH("east_lowersouth",13),
	EAST_LOWER("east_lower",14),EAST_LOWERNORTH("east_lowernorth",15),
	EAST_NORTH("east_north",16),EAST_UPPERNORTH("east_uppernorth",17),
	DOWN_CENTER("down_center",18),DOWN_NORTH("down_north",19),
	DOWN_NORTHEAST("down_northeast",20),DOWN_EAST("down_east",21),
	DOWN_SOUTHEAST("down_southeast",22),DOWN_SOUTH("down_south",23),
	DOWN_SOUTHWEST("down_southwest",24),DOWN_WEST("down_west",25),
	DOWN_NORTHWEST("down_northwest",26),UP_CENTER("up_center",27),
	UP_NORTH("up_north",28),UP_NORTHEAST("up_northeast",29),
	UP_EAST("up_east",30),UP_SOUTHEAST("up_southeast",31),
	UP_SOUTH("up_south",32),UP_SOUTHWEST("up_southwest",33),
	UP_WEST("up_west",34),UP_NORTHWEST("up_northwest",35),
	NORTH_CENTER("north_center",36),NORTH_UPPER("north_upper",37),
	NORTH_UPPEREAST("north_uppereast",38),NORTH_EAST("north_east",39),
	NORTH_LOWEREAST("north_lowereast",40),NORTH_LOWER("north_lower",41),
	NORTH_LOWERWEST("north_lowerwest",42),NORTH_WEST("north_west",43),
	NORTH_UPPERWEST("north_upperwest",44),SOUTH_CENTER("south_center",36),
	SOUTH_UPPER("south_upper",37),SOUTH_UPPEREAST("south_uppereast",38),
	SOUTH_EAST("south_east",39),SOUTH_LOWEREAST("south_lowereast",40),
	SOUTH_LOWER("south_lower",41),SOUTH_LOWERWEST("south_lowerwest",42),
	SOUTH_WEST("south_west",43),SOUTH_UPPERWEST("south_upperwest",44);
	public final String str;
	public final int id;
	private FacePosition9(String n,int i){str=n;id=i;}
	@Override public String asString(){return str;}
	private static final FacePosition9[]VARS={
		WEST_CENTER,WEST_UPPER,WEST_UPPERSOUTH,WEST_SOUTH,WEST_LOWERSOUTH,WEST_LOWER,
		WEST_LOWERNORTH,WEST_NORTH,WEST_UPPERNORTH,EAST_CENTER,EAST_UPPER,
		EAST_UPPERSOUTH,EAST_SOUTH,EAST_LOWERSOUTH,EAST_LOWER,EAST_LOWERNORTH,
		EAST_NORTH,EAST_UPPERNORTH,DOWN_CENTER,DOWN_NORTH,DOWN_NORTHEAST,DOWN_EAST,
		DOWN_SOUTHEAST,DOWN_SOUTH,DOWN_SOUTHWEST,DOWN_WEST,DOWN_NORTHWEST,UP_CENTER,
		UP_NORTH,UP_NORTHEAST,UP_EAST,UP_SOUTHEAST,UP_SOUTH,UP_SOUTHWEST,UP_WEST,
		UP_NORTHWEST,NORTH_CENTER,NORTH_UPPER,NORTH_UPPEREAST,NORTH_EAST,
		NORTH_LOWEREAST,NORTH_LOWER,NORTH_LOWERWEST,NORTH_WEST,NORTH_UPPERWEST,
		SOUTH_CENTER,SOUTH_UPPER,SOUTH_UPPEREAST,SOUTH_EAST,SOUTH_LOWEREAST,
		SOUTH_LOWER,SOUTH_LOWERWEST,SOUTH_WEST,SOUTH_UPPERWEST
	};
	public static FacePosition9 byID(int id){return VARS[id];}
	public static final EnumProperty<FacePosition9>POSITION=
	EnumProperty.of("position",FacePosition9.class);
}
