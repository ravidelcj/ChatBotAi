package ProcessingLayer;
import java.util.ArrayList;

public class ListIntersection {
	
	public static int intersect(ArrayList<ArrayList<Integer> > arr){
		
		ArrayList<Integer> list = new ArrayList<>(arr.get(0));
		//System.out.println(arr.size());
		for(int i = 1; i < arr.size(); i++){
			list.retainAll(arr.get(i));
		}
		if(list.size()>0){
			return list.get(0);
		}
		return -1;
	}

}
