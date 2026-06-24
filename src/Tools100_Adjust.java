import java.util.ArrayList;

public class Tools100_Adjust{
	public static int[] AdjustNoRt(int NeedCount) {
		ParameterMstWankoCheck();
		Object[][] ParameterMstRt = ParameterMstRt();
		int[] rt = new int[NeedCount];
		
		int MinAdjustNo		=(int)ParameterMstRt[0][M100_ParameterMstWankoRt.ColParaInt01];		//パラメータ数値項目01
		int NowAdjustNo		=(int)ParameterMstRt[0][M100_ParameterMstWankoRt.ColParaInt02];		//パラメータ数値項目02
		int MaxAdjustNo		=(int)ParameterMstRt[0][M100_ParameterMstWankoRt.ColParaInt03];		//パラメータ数値項目03
		
		for(int i=0;i<NeedCount;i++) {
			NowAdjustNo=NowAdjustNo+1;
			if(NowAdjustNo>MaxAdjustNo) {
				NowAdjustNo = MinAdjustNo;
			}
			rt[i] = NowAdjustNo;
		}
		ParameterMstWankoSet(NowAdjustNo);
		
		return rt;
	}
	
	public static void ParameterMstWankoCheck() {
		//パラメータマスタに在庫調整番号採番情報がなければ作る
		String now_dtm = B100_DateTimeControl.dtmString2(B100_DateTimeControl.dtm()[1])[1];
		
		Object[][] SetString = {
				 {"ClWh"		,"1","0","Key"	,A00000_Main.ClWh}				//担当倉庫コード
				,{"ClCd"		,"1","0","Key"	,A00000_Main.ClCd}				//荷主コード
				,{"ParaCd"		,"1","0","Key"	,"NowAdjustNo"}					//パラメータコード
				,{"ParaCdSeq"	,"1","0","Key"	,"0"}							//ナンバリング
				,{"ParaName"	,"1","0",""		,"在庫調整番号"}				//パラメータ名
				,{"ParaTxt01"	,"1","0",""		,"在庫調整番号採番状況"}								//パラメータテキスト項目01
				,{"ParaTxt02"	,"1","0",""		,"ParaInt01:最小 ParaInt02:現在値 ParaInt03:最大"}		//パラメータテキスト項目02
				,{"ParaTxt03"	,"1","0",""		,""}				//パラメータテキスト項目03
				,{"ParaTxt04"	,"1","0",""		,""}				//パラメータテキスト項目04
				,{"ParaTxt05"	,"1","0",""		,""}				//パラメータテキスト項目05
				,{"ParaTxt06"	,"1","0",""		,""}				//パラメータテキスト項目06
				,{"ParaTxt07"	,"1","0",""		,""}				//パラメータテキスト項目07
				,{"ParaTxt08"	,"1","0",""		,""}				//パラメータテキスト項目08
				,{"ParaTxt09"	,"1","0",""		,""}				//パラメータテキスト項目09
				,{"ParaTxt10"	,"1","0",""		,""}				//パラメータテキスト項目10
				,{"ParaInt01"	,"1","0",""		,"100000000"}		//パラメータ数値項目01
				,{"ParaInt02"	,"1","0",""		,"100000000"}		//パラメータ数値項目02
				,{"ParaInt03"	,"1","0",""		,"499999999"}		//パラメータ数値項目03
				,{"ParaInt04"	,"1","0",""		,"0"}				//パラメータ数値項目04
				,{"ParaInt05"	,"1","0",""		,"0"}				//パラメータ数値項目05
				,{"ParaInt06"	,"1","0",""		,"0"}				//パラメータ数値項目06
				,{"ParaInt07"	,"1","0",""		,"0"}				//パラメータ数値項目07
				,{"ParaInt08"	,"1","0",""		,"0"}				//パラメータ数値項目08
				,{"ParaInt09"	,"1","0",""		,"0"}				//パラメータ数値項目09
				,{"ParaInt10"	,"1","0",""		,"0"}				//パラメータ数値項目10
				,{"EntryDate"	,"1","0",""		,now_dtm}			//登録日
				,{"UpdateDate"	,"1","0",""		,now_dtm}			//更新日
				,{"EntryUser"	,"1","0",""		,"(" + A00000_Main.LoginUserId + ")" + A00000_Main.LoginUserName}				//登録者
				,{"UpdateUser"	,"1","0",""		,"(" + A00000_Main.LoginUserId + ")" + A00000_Main.LoginUserName}				//更新者
				};
		
		String tgt_table = "WM0000PARAMETER";
		String TgtDB = "WANKO";
		int non_msg_fg = 1;
		
		A100_InsertUpdateSQL.InsertUpdateOneRecord(SetString,tgt_table,TgtDB,non_msg_fg);
	}
	
	private static void ParameterMstWankoSet(int NowNowAdjustNo) {
		String now_dtm = B100_DateTimeControl.dtmString2(B100_DateTimeControl.dtm()[1])[1];
		
		Object[][] SetString = {
				 {"ClWh"		,"0","1","Key"	,A00000_Main.ClWh}				//担当倉庫コード
				,{"ClCd"		,"0","1","Key"	,A00000_Main.ClCd}				//荷主コード
				,{"ParaCd"		,"0","1","Key"	,"NowAdjustNo"}					//パラメータコード
				,{"ParaCdSeq"	,"0","1","Key"	,"0"}							//ナンバリング
				,{"ParaName"	,"0","0",""		,"在庫調整番号"}				//パラメータ名
				,{"ParaTxt01"	,"0","0",""		,"在庫調整番号採番状況"}								//パラメータテキスト項目01
				,{"ParaTxt02"	,"0","0",""		,"ParaInt01:最小 ParaInt02:現在値 ParaInt03:最大"}		//パラメータテキスト項目02
				,{"ParaTxt03"	,"0","0",""		,""}				//パラメータテキスト項目03
				,{"ParaTxt04"	,"0","0",""		,""}				//パラメータテキスト項目04
				,{"ParaTxt05"	,"0","0",""		,""}				//パラメータテキスト項目05
				,{"ParaTxt06"	,"0","0",""		,""}				//パラメータテキスト項目06
				,{"ParaTxt07"	,"0","0",""		,""}				//パラメータテキスト項目07
				,{"ParaTxt08"	,"0","0",""		,""}				//パラメータテキスト項目08
				,{"ParaTxt09"	,"0","0",""		,""}				//パラメータテキスト項目09
				,{"ParaTxt10"	,"0","0",""		,""}				//パラメータテキスト項目10
				,{"ParaInt01"	,"0","0",""		,"100000000"}		//パラメータ数値項目01
				,{"ParaInt02"	,"0","1",""		,""+NowNowAdjustNo}	//パラメータ数値項目02
				,{"ParaInt03"	,"0","0",""		,"499999999"}		//パラメータ数値項目03
				,{"ParaInt04"	,"0","0",""		,"0"}				//パラメータ数値項目04
				,{"ParaInt05"	,"0","0",""		,"0"}				//パラメータ数値項目05
				,{"ParaInt06"	,"0","0",""		,"0"}				//パラメータ数値項目06
				,{"ParaInt07"	,"0","0",""		,"0"}				//パラメータ数値項目07
				,{"ParaInt08"	,"0","0",""		,"0"}				//パラメータ数値項目08
				,{"ParaInt09"	,"0","0",""		,"0"}				//パラメータ数値項目09
				,{"ParaInt10"	,"0","0",""		,"0"}				//パラメータ数値項目10
				,{"EntryDate"	,"0","0",""		,now_dtm}			//登録日
				,{"UpdateDate"	,"0","1",""		,now_dtm}			//更新日
				,{"EntryUser"	,"0","0",""		,"(" + A00000_Main.LoginUserId + ")" + A00000_Main.LoginUserName}				//登録者
				,{"UpdateUser"	,"0","1",""		,"(" + A00000_Main.LoginUserId + ")" + A00000_Main.LoginUserName}				//更新者
				};
		
		String tgt_table = "WM0000PARAMETER";
		String TgtDB = "WANKO";
		int non_msg_fg = 1;
		
		A100_InsertUpdateSQL.InsertUpdateOneRecord(SetString,tgt_table,TgtDB,non_msg_fg);
	}
	
	private static Object[][] ParameterMstRt(){
		ArrayList<String> SearchClWh 			= new ArrayList<String>();
		ArrayList<String> SearchClCd 			= new ArrayList<String>();
		ArrayList<String> SearchParaCd 			= new ArrayList<String>();	
		ArrayList<Integer> SearchParaCdSeqStr	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaCdSeqEnd	= new ArrayList<Integer>();
		ArrayList<String> SearchParaName 		= new ArrayList<String>();
		ArrayList<String> SearchParaTxt01		= new ArrayList<String>();
		ArrayList<String> SearchParaTxt02		= new ArrayList<String>();
		ArrayList<String> SearchParaTxt03		= new ArrayList<String>();
		ArrayList<String> SearchParaTxt04		= new ArrayList<String>();
		ArrayList<String> SearchParaTxt05		= new ArrayList<String>();
		ArrayList<String> SearchParaTxt06		= new ArrayList<String>();
		ArrayList<String> SearchParaTxt07		= new ArrayList<String>();
		ArrayList<String> SearchParaTxt08		= new ArrayList<String>();
		ArrayList<String> SearchParaTxt09		= new ArrayList<String>();
		ArrayList<String> SearchParaTxt10		= new ArrayList<String>();
		ArrayList<Integer> SearchParaInt01Str	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt02Str	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt03Str	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt04Str	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt05Str	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt06Str	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt07Str	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt08Str	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt09Str	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt10Str	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt01End	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt02End	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt03End	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt04End	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt05End	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt06End	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt07End	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt08End	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt09End	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt10End	= new ArrayList<Integer>();
		ArrayList<String> SearchParaTxtAll = new ArrayList<String>();
		Boolean AllSearch = false;
		SearchClWh.add(A00000_Main.ClWh);
		SearchClCd.add(A00000_Main.ClCd);
		SearchParaCd.add("NowAdjustNo");
		SearchParaCdSeqStr.add(0);
		SearchParaCdSeqEnd.add(0);
		
		Object[][] ParameterMstWankoRt = M100_ParameterMstWankoRt.ParameterMstWankoRt(
				SearchClWh,SearchClCd,
				SearchParaCd,SearchParaCdSeqStr,SearchParaCdSeqEnd,SearchParaName,
				SearchParaTxt01,SearchParaTxt02,SearchParaTxt03,SearchParaTxt04,SearchParaTxt05,
				SearchParaTxt06,SearchParaTxt07,SearchParaTxt08,SearchParaTxt09,SearchParaTxt10,
				SearchParaInt01Str,SearchParaInt02Str,SearchParaInt03Str,SearchParaInt04Str,SearchParaInt05Str,
				SearchParaInt06Str,SearchParaInt07Str,SearchParaInt08Str,SearchParaInt09Str,SearchParaInt10Str,
				SearchParaInt01End,SearchParaInt02End,SearchParaInt03End,SearchParaInt04End,SearchParaInt05End,
				SearchParaInt06End,SearchParaInt07End,SearchParaInt08End,SearchParaInt09End,SearchParaInt10End,
				SearchParaTxtAll,
				AllSearch);
		
		return ParameterMstWankoRt;
	}
}