import java.util.ArrayList;
import java.util.HashSet;

public class B00150ArrayListControl{
	public static ArrayList<String> ArryListStringUniqueList(ArrayList<String> TgtArrayList){
		/*
		 ArrayList<String>を受け取って重複を除外したArrayList<String>にして返却する
		 ※並び順保証されない
		*/
		ArrayList<String> rt = new ArrayList<String>();
		if(null!=TgtArrayList && 0<TgtArrayList.size()) {
			HashSet<String> WorkHashSet = new HashSet<String>(TgtArrayList);
			rt = new ArrayList<String>(WorkHashSet);
		}
		return rt;
	}
	
	public static ArrayList<Integer> ArryListIntegerUniqueList(ArrayList<Integer> TgtArrayList){
		/*
		 ArrayList<Integer>を受け取って重複を除外したArrayList<Integer>にして返却する
		 ※並び順保証されない
		*/
		ArrayList<Integer> rt = new ArrayList<Integer>();
		if(null!=TgtArrayList && 0<TgtArrayList.size()) {
			HashSet<Integer> WorkHashSet = new HashSet<Integer>(TgtArrayList);
			rt = new ArrayList<Integer>(WorkHashSet);
		}
		return rt;
	}
}