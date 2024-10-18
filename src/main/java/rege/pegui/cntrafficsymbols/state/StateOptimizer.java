package rege.pegui.cntrafficsymbols.state;
import net.minecraft.state.State;
import net.minecraft.state.property.Property;import org.jetbrains.annotations.Nullable;import rege.pegui.cntrafficsymbols.mixin.Accessor1;
import java.util.IdentityHashMap;
import java.util.Map;
public abstract class StateOptimizer{
	private static final IdentityHashMap<Object,IdentityHashMap<State<?,?>,
	Void>>recordList=new IdentityHashMap<>();
	public static void add(State<?,?>state){
		Object o=((Accessor1<?,?>)state).getOwner();
		if(!recordList.containsKey(o))recordList.put(o,new IdentityHashMap<>());
		recordList.get(o).put(state,null);
	}
	@SuppressWarnings("unchecked")public static<O>@Nullable State<O,?>get(O owner,
	Map<Property<?>,Comparable<?>>ppts){
		if(!recordList.containsKey(owner))return null;
		for(State<?,?>i:recordList.get(owner).keySet()){if(i.getEntries()
		.equals(ppts))return(State<O,?>)i;}
		return null;
	}
}
