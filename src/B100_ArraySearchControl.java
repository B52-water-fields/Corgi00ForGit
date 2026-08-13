import java.util.ArrayList;

public class B100_ArraySearchControl{
	public static Object[][] SearchDefinitionControl(Object[][] Definition){
		/*
		日付系検索最小は念のため00:00:00扱い
		日付系検索項目最大は一日進めて00:00:00扱い
		検索条件の重複除去
		Definition	= B100_ArraySearchControl.SearchDefinitionControl(Definition);
		*/
		for(int i=0;i<Definition.length;i++) {
			String DataType	=((String)Definition[i][0]).toUpperCase();
			String CheckPt	=((String)Definition[i][2]).toUpperCase();
			
			if("DATE".toUpperCase().equals(DataType) && "RANGESTR".toUpperCase().equals(CheckPt)) {
				//日付系最小は念のため00:00:00扱い
				Definition[i][1]	= B100_ArrayListControl.DateOnlySet((ArrayList<String>)Definition[i][1]);
				
			}
			if("DATE".equals(DataType) && "RANGEEND".equals(CheckPt)) {
				//日付系項目最大は一日進めて00:00:00扱い　※時刻まで検索条件にする場合はそのままなのでDateTimeにしてここに入れない
				Definition[i][1]	= B100_ArrayListControl.DateOnlySetNdateAfter((ArrayList<String>)Definition[i][1],1);
				
			}
			if("DATE".toUpperCase().equals(DataType) && "RANGEMIN".toUpperCase().equals(CheckPt)) {
				//日付系最小は念のため00:00:00扱い
				Definition[i][1]	= B100_ArrayListControl.DateOnlySet((ArrayList<String>)Definition[i][1]);
				
			}
			if("DATE".equals(DataType) && "RANGEMAX".equals(CheckPt)) {
				//日付系項目最大は一日進めて00:00:00扱い　※時刻まで検索条件にする場合はそのままなのでDateTimeにしてここに入れない
				Definition[i][1]	= B100_ArrayListControl.DateOnlySetNdateAfter((ArrayList<String>)Definition[i][1],1);
				
			}
			switch(DataType) {
				case"INTEGER":
					Definition[i][1]				= B100_ArrayListControl.ArryListIntegerUniqueList((ArrayList<Integer>)Definition[i][1]);
					break;
				case"FLOAT":
					Definition[i][1]				= B100_ArrayListControl.ArryListFloatUniqueList((ArrayList<Float>)Definition[i][1]);
					break;
				case"STRING":
					Definition[i][1]				= B100_ArrayListControl.ArryListStringUniqueList((ArrayList<String>)Definition[i][1]);
					break;
				case"DATE":
					Definition[i][1]				= B100_ArrayListControl.ArryListStringUniqueList((ArrayList<String>)Definition[i][1]);
					break;
				case"DATETIME":
					Definition[i][1]				= B100_ArrayListControl.ArryListStringUniqueList((ArrayList<String>)Definition[i][1]);
					break;
				default:
					Definition[i][1]				= B100_ArrayListControl.ArryListStringUniqueList((ArrayList<String>)Definition[i][1]);
					break;
			}
		}
		return Definition;
	}
}