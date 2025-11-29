import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class B00050ToolsDateTimeControl{
	//日付や時刻に対して定型的な処理を行うクラス
	

	//現在の日付時刻Timestampを返す
	public static Timestamp[] dtm(){
		//dtm[0] 日付
		//dtm[1] 日付時刻
		String[] dtm_string = {"",""};
		Timestamp[] dtm = {null,null};
		//現在の日付時刻取得
		Calendar cal= Calendar.getInstance();
		Timestamp ps=new Timestamp(cal.getTimeInMillis());
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy'/'MM'/'dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy'/'MM'/'dd' 'HH':'mm':'ss");
		dtm_string[0] = sdf2.format(ps);
		dtm_string[1] = sdf2.format(ps);
		try {
			dtm[0] = new Timestamp(sdf1.parse(dtm_string[0]).getTime());
			dtm[1] = new Timestamp(sdf2.parse(dtm_string[1]).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dtm;
	}

	//日付時刻配列をString配列にして返す
	public static String[][] dtmString(Timestamp[] f0){
		//dtm_string[i][0] 日付
		//dtm_string[i][1] 日付時刻
		String[][] dtm_string = new String[f0.length][2];
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy'/'MM'/'dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy'/'MM'/'dd' 'HH':'mm':'ss");
		for(int i=0;i<f0.length;i++){
			if(f0[i]==null){
				dtm_string[i][0] = "";
				dtm_string[i][1] = "";
			}else{
				dtm_string[i][0] = sdf1.format(f0[i]);
				dtm_string[i][1] = sdf2.format(f0[i]);
			}
		}
		return dtm_string;
	}

	//日付時刻を文字列配列String[]にして返す
	public static String[] dtmString2(Timestamp f0){
		//dtm_string[0] 日付
		//dtm_string[1] 日付時刻
		String[] dtm_string = new String[2];
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy'/'MM'/'dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy'/'MM'/'dd' 'HH':'mm':'ss");
		dtm_string[0] = "";
		dtm_string[1] = "";
		if(f0==null){
		}else{
			dtm_string[0] = sdf1.format(f0);
			dtm_string[1] = sdf2.format(f0);
		}

		return dtm_string;
	}

	//日付時刻文字列配列をTimeStamp配列にして返す
	//元の文字列はyyyy/mm/dd　またはyyyy/mm/dd hh:mm:ss
	public static Timestamp[][] dtmTimestamp(String[] f0){
		//dtm_Timestamp[i][0] 日付
		//dtm_Timestamp[i][1] 日付時刻
		Timestamp[][] dtm_Timestamp = new Timestamp[f0.length][2];
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy'/'MM'/'dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy'/'MM'/'dd' 'HH':'mm':'ss");
		for(int i=0;i<f0.length;i++){
			if("".equals(f0[i])){
				dtm_Timestamp[i][0] = null;
				dtm_Timestamp[i][1] = null;
			}else{
				try {
					if(f0[i].length()<=10){
						dtm_Timestamp[i][0] = new Timestamp(sdf1.parse(f0[i]).getTime());
						dtm_Timestamp[i][1] = new Timestamp(sdf1.parse(f0[i]).getTime());
					}else{
						dtm_Timestamp[i][0] = new Timestamp(sdf1.parse(f0[i].substring(0,10)).getTime());
						dtm_Timestamp[i][1] = new Timestamp(sdf2.parse(f0[i]).getTime());
					}
				} catch (ParseException e) {
					e.printStackTrace();
				};
			}
		}
		return dtm_Timestamp;
	}

	//日付時刻文字列をTimeStamp配列にして返す
	//元の文字列はyyyy/mm/dd　またはyyyy/mm/dd hh:mm:ss
	public static Timestamp[] dtmTimestamp2(String f0){
		//dtm_Timestamp[0] 日付
		//dtm_Timestamp[1] 日付時刻
		Timestamp[] dtm_Timestamp = new Timestamp[2];
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy'/'MM'/'dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy'/'MM'/'dd' 'HH':'mm':'ss");

		if("".equals(f0)){
			dtm_Timestamp[0] = null;
			dtm_Timestamp[1] = null;
		}else{
			try {
				if(f0.length()<=10){
					dtm_Timestamp[0] = new Timestamp(sdf1.parse(f0).getTime());
					dtm_Timestamp[1] = new Timestamp(sdf1.parse(f0).getTime());
				}else{
					dtm_Timestamp[0] = new Timestamp(sdf1.parse(f0.substring(0,10)).getTime());
					dtm_Timestamp[1] = new Timestamp(sdf2.parse(f0).getTime());
				}
			} catch (ParseException e) {
				e.printStackTrace();
			};
		}
		return dtm_Timestamp;
	}
	//Timestamp配列受け取ってhh:mm:ssの文字列配列返す
	public static String[] dtmHMS(Timestamp[] f0){
		String[] dtm_String = new String[f0.length];
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy'/'MM'/'dd' 'HH':'mm':'ss");
		for(int i=0;i<f0.length;i++){
			dtm_String[i] = sdf2.format(f0[i]).substring(11, 19);
		}
		return dtm_String;
	}
	//Timestamp受け取ってhh:mm:ssの文字列返す
	public static String dtmHMS2(Timestamp f0){
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy'/'MM'/'dd' 'HH':'mm':'ss");
		String dtm_String = sdf2.format(f0).substring(11, 19);
		return dtm_String;
	}

	//Timestamp from to受け取って差分日数返す
	public static int ddif(Timestamp f0,Timestamp f1){
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy'/'MM'/'dd");
		String[] dtm_string = new String[2];
		//Timestampから時刻要素除外
		dtm_string[0] = sdf1.format(f0);
		dtm_string[1] = sdf1.format(f1);
		try {
			f0=new Timestamp(sdf1.parse(dtm_string[0]).getTime());
			f1=new Timestamp(sdf1.parse(dtm_string[1]).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int rt = 0;
	    long dateTimeFrom = f0.getTime();
	    long dateTimeTo = f1.getTime();
	    long dayDiff = ( dateTimeTo - dateTimeFrom  ) / (1000 * 60 * 60 * 24 );
		return rt=(int)dayDiff;
	}

	//Timestampと数値N受け取ってN日前のタイムスタンプを返す
	public static Timestamp ndate_before(Timestamp f0,int nd){
		long dateTimeFrom = f0.getTime();
		Timestamp nbefore = new Timestamp(0);
		for(int i=0;i<nd;i++){
			nbefore = new Timestamp(dateTimeFrom-((1000 * 60 * 60 * 24 )));
			dateTimeFrom = nbefore.getTime();
		}
		return nbefore;
	}

	//Timestampと数値N受け取ってN日後のタイムスタンプを返す
	public static Timestamp ndate_after(Timestamp f0,int nd){
		long dateTimeFrom = f0.getTime();
		Timestamp nafter = new Timestamp(0);
		for(int i=0;i<nd;i++){
			nafter = new Timestamp(dateTimeFrom+((1000 * 60 * 60 * 24 )));
			dateTimeFrom = nafter.getTime();
		}
		return nafter;
	}

	//Timestampと数値N受け取ってN時間前のタイムスタンプを返す
	public static Timestamp nhour_before(Timestamp f0,BigDecimal nd){
		long dateTimeFrom = f0.getTime();
		Timestamp nbefore = new Timestamp(0);
		BigDecimal thousand = new BigDecimal("1000");
		int nd_int = nd.multiply(thousand).intValue();
		nbefore = new Timestamp(dateTimeFrom-((60 * 60 * nd_int)));
		return nbefore;
	}

	//Timestampと数値N受け取ってN時間後のタイムスタンプを返す
	public static Timestamp nhour_after(Timestamp f0,BigDecimal nd){
		long dateTimeFrom = f0.getTime();
		Timestamp nbefore = new Timestamp(0);
		BigDecimal thousand = new BigDecimal("1000");
		int nd_int = nd.multiply(thousand).intValue();
		nbefore = new Timestamp(dateTimeFrom+((60 * 60 * nd_int)));
		return nbefore;
	}

	//年月（yyyyと㎜を文字列で受け取って）月1日と末日のTimeStampを返す
	public static Timestamp[] month_str_end(String year,String month){
		//rt[0]:当月初日
		//rt[1]:対象月末
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy'/'MM'/'dd");
		Timestamp[] rt = new Timestamp[2];

		//EX）1月が01ではなく1で渡された場合に備えた処理
		year = "0000" + year;
		month = "00" + month;
		year = year.substring(year.length()-4, year.length());
		month = month.substring(month.length()-2, month.length());

		//受取った文字列で対象月1日のTimestamp生成
		String ws = year+"/"+month+"/01";
		try {
			rt[0] = new Timestamp(sdf1.parse(ws).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//受取った文字列で対象翌月1日のTimestamp生成
		if("12".equals(month)){
			int mint = Integer.parseInt(year);
			mint = mint+1;
			ws = mint + "/01/01";
		}else{
			int mint = Integer.parseInt(month);
			mint = mint+1;
			String ws2 = "00"+mint;
			ws2 = ws2.substring(ws2.length()-2, ws2.length());
			ws = year+"/"+ws2+"/01";
		}
		try {
			rt[1] = new Timestamp(sdf1.parse(ws).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//翌月1日の1日前=対象月月末
		long dateTimeFrom = rt[1].getTime();
		for(int i=0;i<1;i++){
			rt[1] = new Timestamp(dateTimeFrom-((1000 * 60 * 60 * 24 )));
			dateTimeFrom = rt[1].getTime();
		}
		return rt;
	}
	//年月日TimeStamp受け取って対象月の月末月初を返す
	public static Timestamp[] month_str_end2(Timestamp dtm){
		String dtmString = dtmString2(dtm)[0];
		Timestamp[] rt = month_str_end(dtmString.substring(0,4),dtmString.substring(5,7));
		return rt;
	}

	//Timestamp受け取って曜日を返す
	//日曜：1　月曜:2　火曜：3　水曜：4　木曜：5　金曜：6　土曜：7
	public static int week_day(Timestamp f1){
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy'/'MM'/'dd");
		int rt = 0;
		int year  = Integer.parseInt((sdf1.format(f1)).substring(0,4));   	// 年
		int month = Integer.parseInt((sdf1.format(f1)).substring(5,7))-1; 	// 月
		int date  = Integer.parseInt((sdf1.format(f1)).substring(8,10));		// 日

		Calendar cal = Calendar.getInstance();
		cal.set(year, month, date);
		rt = cal.get(Calendar.DAY_OF_WEEK);	//日曜日：1～土曜日:7

		return rt;
	}

	//Timestamp(from,to)受け取って差分時間返す
	public static BigDecimal hdif(Timestamp f0,Timestamp f1){
		BigDecimal rt = new BigDecimal("0");
	    long dateTimeFrom = f0.getTime();
	    long dateTimeTo = f1.getTime();
	    long dayDiff = ( dateTimeTo - dateTimeFrom  ) ;// (1000 * 60 * 60 * 24 );
	    BigDecimal TL = new BigDecimal(""+dayDiff);
	    BigDecimal DVBD = new BigDecimal("3600000");
	    rt = TL.divide(DVBD,3,BigDecimal.ROUND_HALF_UP);
		return rt;
	}

	//Timestamp(from,N)受け取ってNヶ月後のTimeStamp返す
	//Nヶ月後同日がなければ加算
	//EX）2019/01/31,1 ⇒2019/02/31　存在しないので　2019/03/01 hh:mm:ss
	public static Timestamp N_MONTH_AFTER_FIRSTDAY_MODE(Timestamp f0,int f1){
		Timestamp rt = null;
		String ws = dtmString2(f0)[1];
		//Nヶ月後をN年Nヶ月後に処理
		int ADD_YEAR = f1/12;
		int ADD_MONTH = f1%12;
		int NOW_YEAR = Integer.parseInt(ws.substring(0, 4));
		int NOW_MONTH = Integer.parseInt(ws.substring(5, 7));
		int NOW_DATE = Integer.parseInt(ws.substring(8,10));
		String NOW_CLOCK = ws.substring(11,19);
		int RT_YEAR = NOW_YEAR + ADD_YEAR;
		int RT_MONTH = NOW_MONTH + ADD_MONTH;
		int RT_DATE = NOW_DATE;
		if(RT_MONTH>12){
			RT_YEAR = RT_YEAR+1;
			RT_MONTH = RT_MONTH-12;
		}else if(RT_MONTH<1){
			RT_YEAR = RT_YEAR-1;
			RT_MONTH = 12+RT_MONTH;
		}
		//戻す対象月の月末日付を取得
		int END_DATE = Integer.parseInt(dtmString2(month_str_end(""+RT_YEAR,""+RT_MONTH)[1])[0].substring(8,10));
		//対象月の月末日付よりも大きな日付を戻そうとしている場合、翌月日付の1日を返す対象にする
		if(END_DATE<RT_DATE){
			RT_DATE = 1;
			if(RT_MONTH+1>12){
				RT_YEAR = RT_YEAR+1;
				RT_MONTH = 1;
			}else{
				RT_MONTH = RT_MONTH+1;
			}
		}
		//戻す日付yyyy/mm/dd hh:mm:ssの文字列生成
		String rt_st = "";
		String w_st = "0000"+RT_YEAR;
		w_st = w_st.substring(w_st.length()-4,w_st.length());
		rt_st = rt_st+w_st;
		w_st = "00"+RT_MONTH;
		w_st = w_st.substring(w_st.length()-2,w_st.length());
		rt_st = rt_st+"/"+w_st;
		w_st = "00"+RT_DATE;
		w_st = w_st.substring(w_st.length()-2,w_st.length());
		rt_st = rt_st+"/"+w_st;
		rt_st = rt_st + " "  + NOW_CLOCK;

		rt = dtmTimestamp2(rt_st)[1];
		return rt;
	}

	//Timestamp(from,N)受け取ってNヶ月後のTimeStamp返す
	//Nヶ月後同日がなければ減算
	//EX）2019/01/31,1 ⇒2019/02/31　存在しないので　2019/02/28
	public static Timestamp N_MONTH_AFTER_LASTDAY_MODE(Timestamp f0,int f1){
		Timestamp rt = null;
		String ws = dtmString2(f0)[1];
		//Nヶ月後をN年Nヶ月後に処理
		int ADD_YEAR = f1/12;
		int ADD_MONTH = f1%12;
		int NOW_YEAR = Integer.parseInt(ws.substring(0, 4));
		int NOW_MONTH = Integer.parseInt(ws.substring(5, 7));
		int NOW_DATE = Integer.parseInt(ws.substring(8,10));
		String NOW_CLOCK = ws.substring(11,19);
		int RT_YEAR = NOW_YEAR + ADD_YEAR;
		int RT_MONTH = NOW_MONTH + ADD_MONTH;
		int RT_DATE = NOW_DATE;
		if(RT_MONTH>12){
			RT_YEAR = RT_YEAR+1;
			RT_MONTH = RT_MONTH-12;
		}else if(RT_MONTH<1){
			RT_YEAR = RT_YEAR-1;
			RT_MONTH = 12+RT_MONTH;
		}
		//戻す対象月の月末日付を取得
		int END_DATE = Integer.parseInt(dtmString2(month_str_end(""+RT_YEAR,""+RT_MONTH)[1])[0].substring(8,10));
		//対象月の月末日付よりも大きな日付を戻そうとしている場合、月末日付を返す対象にする
		if(END_DATE<RT_DATE){
			RT_DATE = END_DATE;
		}
		//戻す日付yyyy/mm/dd hh:mm:ssの文字列生成
		String rt_st = "";
		String w_st = "0000"+RT_YEAR;
		w_st = w_st.substring(w_st.length()-4,w_st.length());
		rt_st = rt_st+w_st;
		w_st = "00"+RT_MONTH;
		w_st = w_st.substring(w_st.length()-2,w_st.length());
		rt_st = rt_st+"/"+w_st;
		w_st = "00"+RT_DATE;
		w_st = w_st.substring(w_st.length()-2,w_st.length());
		rt_st = rt_st+"/"+w_st;
		rt_st = rt_st + " "  + NOW_CLOCK;

		rt = dtmTimestamp2(rt_st)[1];
		return rt;
	}
	
	//日付文字列8桁数字　yyyy/m/dを受け取ってyyyy/mm/ddの文字列にして返却する
	public static String DateStyleRt(String DateString) {
		String rt = "";
		if(null==DateString) {DateString="";}
		if(8==(""+DateString).length()&& 8==(B00020ToolsTextControl.num_only_String(""+DateString)).length()) {
			DateString = (""+DateString).substring(0, 4) + "/" + (""+DateString).substring(4, 6) + "/" + (""+DateString).substring(6, 8);
			rt = DateString;
		}else {
			String[] WST = (""+DateString).split("/");
			if(3==WST.length) {
				DateString = ("0000"+WST[0]).substring(("0000"+WST[0]).length()-4,("0000"+WST[0]).length());
				DateString = DateString+"/"+("00"+WST[1]).substring(("00"+WST[1]).length()-2,("00"+WST[1]).length());
				DateString = DateString+"/"+("00"+WST[2]).substring(("00"+WST[2]).length()-2,("00"+WST[2]).length());
				rt = DateString;
			}
		}
		return rt;
	}
	
	//日付項目yyyy/mm/ddにする　日付っぽい形式をyyyy/mm/ddに
	public static String DateFormat(String CSt) {
		String rt="";
		String now_dtm = B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[1];
		if(!(CSt.equals(CSt.replace("/", "")))) {
			String[] WST = CSt.split("/");
			if(3==WST.length) {
				//年・月・日がスラッシュで区切られた文字列として判断
				
				switch(WST[0].length()) {
					//年項目チェック
					case 0:
						WST[0] = now_dtm.substring(0,4);
						break;
					case 1:
						WST[0] = now_dtm.substring(0,4);
						break;
					case 2:
						WST[0] = now_dtm.substring(0,2)+WST[0];
					case 3:
						WST[0] = now_dtm.substring(0,1)+WST[0];
					default:
						WST[0] = WST[0].substring(0,4);
						break;
				}
				switch(WST[1].length()) {
					case 0:
						WST[1] = now_dtm.substring(5,7);
						break;
					case 1:
						WST[1] = "0"+WST[1];
						break;
					case 2:
						WST[1] = WST[1];
						break;
					default:
						WST[1] = WST[1].substring(0,2);
						break;
				}
				switch(WST[2].length()) {
					case 0:
						WST[2] = now_dtm.substring(8,10);
						break;
					case 1:
						WST[2] = "0"+WST[2];
						break;
					case 2:
						WST[2] = WST[2];
						break;
					default:
						WST[2] = WST[2].substring(0,2);
						break;
				}
				rt=WST[0]+"/"+WST[1]+"/"+WST[2];
			}
			if(2==WST.length) {
				//月・日がスラッシュで区切られた文字列として判断
				switch(WST[0].length()) {
					case 0:
						WST[0] = now_dtm.substring(5,7);
						break;
					case 1:
						WST[0] = "0"+WST[0];
						break;
					case 2:
						WST[0] = WST[0];
						break;
					default:
						WST[0] = WST[0].substring(0,2);
						break;
				}
				switch(WST[1].length()) {
					case 0:
						WST[1] = now_dtm.substring(8,10);
						break;
					case 1:
						WST[1] = "0"+WST[1];
						break;
					case 2:
						WST[1] = WST[1];
						break;
					default:
						WST[1] = WST[1].substring(0,2);
						break;
				}
				rt=now_dtm.substring(0,4)+"/"+WST[0]+"/"+WST[1];
			}
		}else if(!(CSt.equals(CSt.replace("-", "")))){
			String[] WST = CSt.split("-");
			if(3==WST.length) {
				//年・月・日が-で区切られた文字列として判断
				switch(WST[0].length()) {
					case 0:
						WST[0] = now_dtm.substring(0,4);
						break;
					case 1:
						WST[0] = now_dtm.substring(0,4);
						break;
					case 2:
						WST[0] = now_dtm.substring(0,2)+WST[0];
					case 3:
						WST[0] = now_dtm.substring(0,1)+WST[0];
					default:
						WST[0] = WST[0].substring(0,4);
						break;
				}
				switch(WST[1].length()) {
					case 0:
						WST[1] = now_dtm.substring(5,7);
						break;
					case 1:
						WST[1] = "0"+WST[1];
						break;
					case 2:
						WST[1] = WST[1];
						break;
					default:
						WST[1] = WST[1].substring(0,2);
						break;
				}
				switch(WST[2].length()) {
					case 0:
						WST[2] = now_dtm.substring(8,10);
						break;
					case 1:
						WST[2] = "0"+WST[2];
						break;
					case 2:
						WST[2] = WST[2];
						break;
					default:
						WST[2] = WST[2].substring(0,2);
						break;
				}
				rt=WST[0]+"/"+WST[1]+"/"+WST[2];
			}
			if(2==WST.length) {
				//月・日が-で区切られた文字列として判断
				switch(WST[0].length()) {
					case 0:
						WST[0] = now_dtm.substring(5,7);
						break;
					case 1:
						WST[0] = "0"+WST[0];
						break;
					case 2:
						WST[0] = WST[0];
						break;
					default:
						WST[0] = WST[0].substring(0,2);
						break;
				}
				switch(WST[1].length()) {
					case 0:
						WST[1] = now_dtm.substring(8,10);
						break;
					case 1:
						WST[1] = "0"+WST[1];
						break;
					case 2:
						WST[1] = WST[1];
						break;
					default:
						WST[1] = WST[1].substring(0,2);
						break;
				}
				rt=now_dtm.substring(0,4)+"/"+WST[0]+"/"+WST[1];
			}
		}else {
			CSt = B00020ToolsTextControl.num_only_String(CSt);
			if(8==CSt.length()) {
				//yyyymmddと判断
				rt=CSt.substring(0,4)+"/"+CSt.substring(4,6)+"/"+CSt.substring(6,8);
			}else if(7==CSt.length()) {
				//日付判断できないため空白返却
				rt="";
			}else if(6==CSt.length()) {
				//yymmddと判断
				CSt = now_dtm.substring(0,2)+CSt;
				rt=CSt.substring(0,4)+"/"+CSt.substring(4,6)+"/"+CSt.substring(6,8);
			}else if(5==CSt.length()) {
				//日付判断できないため空白返却
				rt="";
			}else if(4==CSt.length()) {
				//mmddと判断
				CSt = now_dtm.substring(0,4)+CSt;
				rt=CSt.substring(0,4)+"/"+CSt.substring(4,6)+"/"+CSt.substring(6,8);
			}else if(3==CSt.length()) {
				//日付判断できないため空白返却
				rt="";
			}else if(2==CSt.length()) {
				//ddと判断
				CSt = now_dtm.substring(0,4)+now_dtm.substring(5,7)+CSt;
				rt=CSt.substring(0,4)+"/"+CSt.substring(4,6)+"/"+CSt.substring(6,8);
			}else if(1==CSt.length()) {
				//dと判断
				CSt = now_dtm.substring(0,4)+now_dtm.substring(5,7)+"0"+CSt;
				rt=CSt.substring(0,4)+"/"+CSt.substring(4,6)+"/"+CSt.substring(6,8);
			}else {
				//日付判断できないため空白返却
				rt="";
			}
		}
		if(""!=rt) {
			//月の項目が01～12　日付項目が01～31でなければ空白返却
			if(10==rt.length()) {
				String RtDate = rt.substring(8,10);
				String RtMonth = rt.substring(5,7);
				if(12<Integer.parseInt(RtMonth)) {rt = "";}
				if(31<Integer.parseInt(RtDate)) {rt = "";}
				if(""!=rt) {rt=B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtmTimestamp2(rt)[0])[0];}
			}else {
				rt = "";
			}
		}
		
		return rt;
	}

}