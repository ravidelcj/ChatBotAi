package ProcessingLayer;
import java.util.ArrayList;

public class ListIntersection {
	
	public static int intersect(ArrayList<ArrayList<Integer> > list){
		for(int i = 1; i < list.size(); i++){
			list.get(0).retainAll(list.get(i));
		}
		if(list.get(0).size()>0){
			return list.get(0).get(0);
		}
		return -1;
	
	}

}
