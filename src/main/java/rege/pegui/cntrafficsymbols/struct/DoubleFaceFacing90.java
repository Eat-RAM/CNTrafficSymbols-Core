package rege.pegui.cntrafficsymbols.struct;
import net.minecraft.state.property.EnumProperty;
public enum DoubleFaceFacing90
implements net.minecraft.util.StringIdentifiable{
	SOUTH("south",0),WEST("west",1),NORTH("north",2),EAST("east",3),
	NORTH_SOUTH("north_south",4),EAST_WEST("east_west",5),
	WALL_NORTH("wall_north",6),WALL_EAST("wall_east",7),
	WALL_SOUTH("wall_south",8),WALL_WEST("wall_west",9);
	public final String str;
	public final int id;
	private DoubleFaceFacing90(String n,int i){str=n;id=i;}
	@Override public String asString(){return str;}
	private static final DoubleFaceFacing90[]VARS={
		SOUTH,WEST,NORTH,EAST,NORTH_SOUTH,EAST_WEST,
		WALL_NORTH,WALL_EAST,WALL_SOUTH,WALL_WEST
	};
	public static DoubleFaceFacing90 byID(int id){return VARS[id];}
	public static final EnumProperty<DoubleFaceFacing90>FACING=
	EnumProperty.of("facing",DoubleFaceFacing90.class);
	public boolean isSingle(){return id<4||id>5;}
	public boolean isWall(){return id>5;}
}
