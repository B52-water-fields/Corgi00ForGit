import java.util.ArrayList;
import java.util.HashSet;

public class B100_ArrayListControl{
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
	
	/*************************************************
	B100_ArrayListControl.ArryListGetRow(TgtArrayList,TgtData,UnHitZeroMode)
	
	　String 又はIntegerの配列要素に対して
	　比較値と最初に一致した行番号を返却する
		UnHitZeroMode=trueなら一致行なければ0を返却（0行目で一致しても0が返るので注意）
		UnHitZeroMode=falseなら一致行なければ-1を返却
	
	*************************************************/
	
	public static int ArryListGetRow(ArrayList<String> TgtArrayList,String TgtData,boolean UnHitZeroMode) {
		int rt = -1;
		if(UnHitZeroMode) {rt = 0;}
		if(null!=TgtArrayList&&null!=TgtData) {
			for(int i=0;i<TgtArrayList.size();i++) {
				if(TgtData.equals(TgtArrayList.get(i))) {
					rt = i;
					i=TgtArrayList.size()+1;
				}
			}
		}
		return rt;
	}
	
	public static int ArryListGetRow(String[] TgtArrayList,String TgtData,boolean UnHitZeroMode) {
		int rt = -1;
		if(UnHitZeroMode) {rt = 0;}
		if(null!=TgtArrayList&&null!=TgtData) {
			for(int i=0;i<TgtArrayList.length;i++) {
				if(TgtData.equals(TgtArrayList[i])) {
					rt = i;
					i=TgtArrayList.length+1;
				}
			}
		}
		return rt;
	}
	
	public static int ArryListGetRow(ArrayList<Integer> TgtArrayList,int TgtData,boolean UnHitZeroMode) {
		int rt = -1;
		if(UnHitZeroMode) {rt = 0;}
		if(null!=TgtArrayList) {
			for(int i=0;i<TgtArrayList.size();i++) {
				if(TgtData==TgtArrayList.get(i)) {
					rt = i;
					i=TgtArrayList.size()+1;
				}
			}
		}
		return rt;
	}
	
	public static int ArryListGetRow(int[] TgtArrayList,int TgtData,boolean UnHitZeroMode) {
		int rt = -1;
		if(UnHitZeroMode) {rt = 0;}
		if(null!=TgtArrayList) {
			for(int i=0;i<TgtArrayList.length;i++) {
				if(TgtData==TgtArrayList[i]) {
					rt = i;
					i=TgtArrayList.length+1;
				}
			}
		}
		return rt;
	}
}