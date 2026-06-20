public class Tools010_RtClassControl{
	/*--------------------------
	Rtクラスを使ってできること
	---------------------------*/
	
	public static Object[] RtXXXXRtUseStringExChange(Object[][] RtRt,String[] TgatData) {
		//Stringで格納された配列をRtクラスの戻り配列と同じ型にして返却
		
		Object[] BackData 		= new Object[RtRt.length];
		
		for(int i01=0;i01<RtRt.length;i01++) {
			if(null==TgatData[(int)RtRt[i01][1]]) {TgatData[(int)RtRt[i01][1]]="";}
			
			switch((String)RtRt[i01][2]) {
				case "int":
					BackData[(int)RtRt[i01][1]]	= B100_TextControl.TextToInt(		""+TgatData[(int)RtRt[i01][1]]);
					break;
				case "float":
					BackData[(int)RtRt[i01][1]]	= B100_TextControl.TextToFloat(	""+TgatData[(int)RtRt[i01][1]]);
					break;
				case "Date":
					BackData[(int)RtRt[i01][1]]	= B100_TextControl.TextToDate(		""+TgatData[(int)RtRt[i01][1]]);
				default:
					BackData[(int)RtRt[i01][1]]	= B100_TextControl.Trim(			""+TgatData[(int)RtRt[i01][1]]);
					break;
			}
		}
		return BackData;
	}
}