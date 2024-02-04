package rege.pegui.cntrafficsymbols.struct;
import net.minecraft.state.property.EnumProperty;
public enum DoubleFaceFacing implements net.minecraft.util.StringIdentifiable{
	SOUTH("south",0),SSW("ssw",1),SOUTHWEST("southwest",2),WSW("wsw",3),
	WEST("west",4),WNW("wnw",5),NORTHWEST("northwest",6),NNW("nnw",7),
	NORTH("north",8),NNE("nne",9),NORTHEAST("northeast",10),ENE("ene",11),
	EAST("east",12),ESE("ese",13),SOUTHEAST("southeast",14),SSE("sse",15),
	NORTH_SOUTH("north_south",16),NNE_SSW("nne_ssw",17),
	NORTHEAST_SOUTHWEST("northeast_southwest",18),ENE_WSW("ene_wsw",19),
	EAST_WEST("east_west",20),ESE_WNW("ese_wnw",21),
	NORTHWEST_SOUTHEAST("northwest_southeast",22),SSE_NNW("sse_nnw",23),
	WALL_NORTH("wall_north",24),WALL_EAST("wall_east",25),
	WALL_SOUTH("wall_south",26),WALL_WEST("wall_west",27);
	public final String str;
	public final int id;
	private DoubleFaceFacing(String n,int i){str=n;id=i;}
	@Override public String asString(){return str;}
	private static final DoubleFaceFacing[]VARS={
		SOUTH,SSW,SOUTHWEST,WSW,WEST,WNW,NORTHWEST,NNW,NORTH,NNE,NORTHEAST,ENE,EAST,
		ESE,SOUTHEAST,SSE,NORTH_SOUTH,NNE_SSW,NORTHEAST_SOUTHWEST,ENE_WSW,EAST_WEST,
		ESE_WNW,NORTHWEST_SOUTHEAST,SSE_NNW,WALL_NORTH,WALL_EAST,WALL_SOUTH,WALL_WEST
	};
	public static DoubleFaceFacing byID(int id){return VARS[id];}
	public static final EnumProperty<DoubleFaceFacing>FACING=
	EnumProperty.of("facing",DoubleFaceFacing.class);
	public boolean isSingle(){return id<16||id>23;}
	public boolean isWall(){return id>23;}
}
