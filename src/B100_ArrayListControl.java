import java.sql.Timestamp;
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
	
	public static ArrayList<Float> ArryListFloatUniqueList(ArrayList<Float> TgtArrayList){
		/*
		 ArrayList<Integer>を受け取って重複を除外したArrayList<Integer>にして返却する
		 ※並び順保証されない
		*/
		ArrayList<Float> rt = new ArrayList<Float>();
		if(null!=TgtArrayList && 0<TgtArrayList.size()) {
			HashSet<Float> WorkHashSet = new HashSet<Float>(TgtArrayList);
			rt = new ArrayList<Float>(WorkHashSet);
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
	//オブジェクトから単一キーで一致行番号返却
	public static int ObjectGetRow(Object[][] CheckObject,String TgtData,int KeyClm,boolean UnHitZeroMode) {
		int rt = -1;
		if(UnHitZeroMode) {rt = 0;}
		if(null!=CheckObject) {
			for(int i=0;i<CheckObject.length;i++) {
				if(TgtData.equals(""+CheckObject[i][KeyClm])) {
					rt = i;
					i=CheckObject.length+1;
				}
			}
		}
		return rt;
	}
	//オブジェクトから複数キーで一致行番号返却
	public static int ObjectGetRowAnyKey(Object[][] CheckObject,String[] TgtData,int[] KeyClm,boolean UnHitZeroMode) {
		int rt = -1;
		if(UnHitZeroMode) {rt = 0;}
		if(null!=CheckObject) {
			for(int i=0;i<CheckObject.length;i++) {
				boolean CheckFg = true;
				for(int i01=0;i01<KeyClm.length;i01++) {
					if(!(""+TgtData[i01]).equals(""+CheckObject[i][KeyClm[i01]])) {
						CheckFg = false;
						i01=KeyClm.length+1;
					}
				}
				if(CheckFg) {
					rt = i;
					i=CheckObject.length+1;
				}
			}
		}
		return rt;
	}
	
	//yyyy/mm/dd 又はyyyy/mm/dd hh:mm:ss のArrayList受け取って全部 yyyy/mm/dd の配列にして返す
	public static ArrayList<String> DateOnlySet(ArrayList<String> TgtArray){
		if(null!=TgtArray && 0<TgtArray.size()){
			int RowCount = TgtArray.size();
			for(int i=0;i<RowCount;i++){
				String SetString = B100_DateTimeControl.DateFormat(TgtArray.get(RowCount-1-i));
				if("".equals(SetString)) {
					TgtArray.remove(RowCount-1-i);
				}else {
					Timestamp SetTimestamp = B100_DateTimeControl.dtmTimestamp2(SetString)[0];
					SetString = B100_DateTimeControl.dtmString2(SetTimestamp)[0];
					TgtArray.set(RowCount-1-i,SetString);
				}
			}
		}
		return TgtArray;
	}
	
	//yyyy/mm/dd 又はyyyy/mm/dd hh:mm:ss のArrayList受け取って全部 N日進めたyyyy/mm/dd の配列にして返す
	public static ArrayList<String> DateOnlySetNdateAfter(ArrayList<String> TgtArray,int Ndate){
		if(null!=TgtArray && 0<TgtArray.size()){
			int RowCount = TgtArray.size();
			for(int i=0;i<RowCount;i++){
				String SetString = B100_DateTimeControl.DateFormat(TgtArray.get(RowCount-1-i));
				if("".equals(SetString)) {
					TgtArray.remove(RowCount-1-i);
				}else {
					Timestamp SetTimestamp = B100_DateTimeControl.dtmTimestamp2(SetString)[0];
					SetTimestamp = B100_DateTimeControl.ndate_after(SetTimestamp,Ndate);
					SetString = B100_DateTimeControl.dtmString2(SetTimestamp)[0];
					TgtArray.set(RowCount-1-i,SetString);
				}
			}
		}
		return TgtArray;
	}
	
	//yyyy/mm/dd 又はyyyy/mm/dd hh:mm:ss のArrayList受け取って全部 N日戻した　yyyy/mm/dd の配列にして返す
	public static ArrayList<String> DateOnlySetNdateBefore(ArrayList<String> TgtArray,int Ndate){
		if(null!=TgtArray && 0<TgtArray.size()){
			int RowCount = TgtArray.size();
			for(int i=0;i<RowCount;i++){
				String SetString = B100_DateTimeControl.DateFormat(TgtArray.get(RowCount-1-i));
				if("".equals(SetString)) {
					TgtArray.remove(RowCount-1-i);
				}else {
					Timestamp SetTimestamp = B100_DateTimeControl.dtmTimestamp2(SetString)[0];
					SetTimestamp = B100_DateTimeControl.ndate_before(SetTimestamp,Ndate);
					SetString = B100_DateTimeControl.dtmString2(SetTimestamp)[0];
					TgtArray.set(RowCount-1-i,SetString);
				}
			}
		}
		return TgtArray;
	}
}