import java.util.ArrayList;

public class B100_DefaultVariableWarehouse{
	//倉庫決定時に初期値として１件以上欲しい情報を作成する
	public static void DefaultVariableWarehouse(String WhCd) {
		DefaultClCreate(WhCd);
	}
	
	//倉庫マスタ新規登録更新を行う
	public static String RenewAndCreateWh(
			String GetWHCD,		//倉庫コード
			String GetWHName,	//拠点倉庫名
			String GetPost,		//拠点倉庫郵便番号
			String GetAdd01,	//拠点倉庫住所1
			String GetAdd02,	//拠点倉庫住所2
			String GetTel,		//拠点倉庫電話
			String GetFax,		//拠点倉庫FAX
			String GetMail,		//拠点倉庫MAIL
			String GetCom01,	//コメント１
			String GetCom02,	//コメント２
			String GetCom03,	//コメント３
			String GetPTMSCD	//基幹SysCD
			) {
		
		if(null==GetWHCD){GetWHCD="";}		//倉庫コード
		if(null==GetWHName){GetWHName="";}	//拠点倉庫名
		if(null==GetPost){GetPost="";}		//拠点倉庫郵便番号
		if(null==GetAdd01){GetAdd01="";}	//拠点倉庫住所1
		if(null==GetAdd02){GetAdd02="";}	//拠点倉庫住所2
		if(null==GetTel){GetTel="";}		//拠点倉庫電話
		if(null==GetFax){GetFax="";}		//拠点倉庫FAX
		if(null==GetMail){GetMail="";}		//拠点倉庫MAIL
		if(null==GetCom01){GetCom01="";}	//コメント１
		if(null==GetCom02){GetCom02="";}	//コメント２
		if(null==GetCom03){GetCom03="";}	//コメント３
		if(null==GetPTMSCD){GetPTMSCD="";}	//基幹SysCD
		
		if(null==GetWHCD||"".equals(GetWHCD)) {
			GetWHCD = M100_WhMstRt.NewWhCdGet(1)[0];
		}
		
		String[][] field_name = new String[16][3];
		String[][] entry_data = new String[1][16];
		String[] judg_field = new String[1];
		String[][] judg_data = new String[1][1];
		
		
		String now_dtm = B100_DateTimeControl.dtmString2(B100_DateTimeControl.dtm()[1])[1];
		
		Object[][] SetString = {
				 {"WHCD"		,"1","1","Key"	,GetWHCD}		//倉庫コード
				,{"WHName"		,"1","1",""		,GetWHName}		//拠点倉庫名
				,{"Post"		,"1","1",""		,GetPost}		//拠点倉庫郵便番号
				,{"Add01"		,"1","1",""		,GetAdd01}		//拠点倉庫住所1
				,{"Add02"		,"1","1",""		,GetAdd02}		//拠点倉庫住所2
				,{"Tel"			,"1","1",""		,GetTel}		//拠点倉庫電話
				,{"Fax"			,"1","1",""		,GetFax}		//拠点倉庫FAX
				,{"Mail"		,"1","1",""		,GetMail}		//拠点倉庫MAIL
				,{"Com01"		,"1","1",""		,GetCom01}		//コメント１
				,{"Com02"		,"1","1",""		,GetCom02}		//コメント２
				,{"Com03"		,"1","1",""		,GetCom03}		//コメント３
				,{"PTMSCD"		,"1","1",""		,GetPTMSCD}		//基幹システム連携用事業所CD
				,{"EntryDate"	,"1","0",""		,now_dtm}		//データ登録日時
				,{"UpdateDate"	,"1","1",""		,now_dtm}		//データ更新日時
				,{"EntryUser"	,"1","0",""		,"(" + A00000_Main.LoginUserId + ")" + A00000_Main.LoginUserName}		//登録者コード
				,{"UpdateUser"	,"1","1",""		,"(" + A00000_Main.LoginUserId + ")" + A00000_Main.LoginUserName}		//更新者コード
				};
		String tgt_table = "KM0010_WHMST";
		String TgtDB = "NANKO";
		int non_msg_fg = 1;
		
		A100_InsertUpdateSQL.InsertUpdateOneRecord(SetString,tgt_table,TgtDB,non_msg_fg);
		B100_DefaultVariable.WhList();
		
		DefaultVariableWarehouse(GetWHCD);
		
		return GetWHCD;
	}
	
	public static void DefaultClCreate(String WhCd) {
		//倉庫に荷主が設定されていない場合用に荷主グループClGp000配下で荷主を一つ作る
		if(null==WhCd) {WhCd="";}
		
		//倉庫マスタ取得
		ArrayList<String> SearchWHCD = new ArrayList<String>();
		ArrayList<String> SearchWHName = new ArrayList<String>();
		ArrayList<String> SearchPost = new ArrayList<String>();
		ArrayList<String> SearchAdd = new ArrayList<String>();
		ArrayList<String> SearchTel = new ArrayList<String>();
		ArrayList<String> SearchFax = new ArrayList<String>();
		ArrayList<String> SearchMail = new ArrayList<String>();
		ArrayList<String> SearchCom = new ArrayList<String>();
		ArrayList<String> SearchPTMSCD = new ArrayList<String>();
		boolean AllSearch = false;
		
		SearchWHCD.add(WhCd);
		
		Object[][] WhMstRt = M100_WhMstRt.WhMstRt(
					SearchWHCD, SearchWHName, SearchPost,
					SearchAdd, SearchTel, SearchFax, SearchMail,
					SearchCom, SearchPTMSCD,
					AllSearch);
		
		
		if(null!=WhMstRt && 0<WhMstRt.length) {
			//倉庫が担当する荷主が存在しない場合荷主作る
			ArrayList<String> SearchClGpCD = new ArrayList<String>();
			ArrayList<String> SearchCLCD = new ArrayList<String>();
			ArrayList<String> SearchCLName = new ArrayList<String>();
			SearchPost = new ArrayList<String>();
			ArrayList<String> searchAdd = new ArrayList<String>();
			SearchTel = new ArrayList<String>();
			SearchFax = new ArrayList<String>();
			SearchMail = new ArrayList<String>();
			SearchCom = new ArrayList<String>();
			SearchWHCD = new ArrayList<String>();
			AllSearch = false;
			
			SearchWHCD.add(WhCd);
			
			Object[][] ClMstRt = M100_ClMstRt.ClMstRt(
						SearchClGpCD,SearchCLCD,SearchCLName,SearchPost,searchAdd,
						SearchTel,SearchFax,SearchMail, SearchCom,SearchWHCD,AllSearch);
			
			if(0>=ClMstRt.length) {
		    	//新規荷主コードを採番
				String GetClCd = M100_ClMstRt.NewClCdGet(1)[0];
				
				String now_dtm = B100_DateTimeControl.dtmString2(B100_DateTimeControl.dtm()[1])[1];
				
				Object[][] SetString = {
						 {"cl_cd"		,"1","0","Key"	,GetClCd}																//荷主CD
						,{"ClGpCD"		,"1","0",""		,"ClGp000"}																//荷主グループCD
						,{"WHCD"		,"1","0",""		,""+WhMstRt[0][M100_WhMstRt.ColNoWHCD]}								//担当倉庫
						,{"CLName01"	,"1","0",""		,"高天原ホールディングス"+WhMstRt[0][M100_WhMstRt.ColNoWHName]+"支店"}	//荷主表記名
						,{"CLName02"	,"1","0",""		,WhMstRt[0][M100_WhMstRt.ColNoWHName]+"初期設定荷主"}					//荷主正式名
						,{"CLName03"	,"1","0",""		,"TMGHD"+WhMstRt[0][M100_WhMstRt.ColNoWHName]+"初期設定荷主"}			//荷主略名
						,{"Post"		,"1","0",""		,"5160023"}																//郵便番号
						,{"Add01"		,"1","0",""		,"三重県伊勢市"}														//住所1
						,{"Add02"		,"1","0",""		,"宇治館町１"}															//住所2
						,{"Add03"		,"1","0",""		,""}																	//住所3
						,{"Tel"			,"1","0",""		,"0596241111"}															//電話番号
						,{"Fax"			,"1","0",""		,""}																	//FAX
						,{"Mail"		,"1","0",""		,""}																	//メールアドレス
						,{"Com01"		,"1","0",""		,""}																	//コメント1
						,{"Com02"		,"1","0",""		,""}																	//コメント2
						,{"Com03"		,"1","0",""		,""}																	//コメント3
						,{"ShimeDate"	,"1","0",""		,"99"}																	//運賃締日
						,{"ShimeBasis"	,"1","0",""		,"0"}																	//請求基準
						,{"EntryDate"	,"1","0",""		,now_dtm}																//データ登録日時
						,{"UpdateDate"	,"1","0",""		,now_dtm}																//データ更新日時
						,{"EntryUser"	,"1","0",""		,"(" + A00000_Main.LoginUserId + ")" + A00000_Main.LoginUserName}	//登録者コード
						,{"UpdateUser"	,"1","0",""		,"(" + A00000_Main.LoginUserId + ")" + A00000_Main.LoginUserName}	//更新者コード
						,{"PTMSCD"		,"1","0",""		,""}																	//基幹システム連携用荷主コード
						};
				String tgt_table = "KM0030_CLIENTMST";
				String TgtDB = "NANKO";
				int non_msg_fg = 1;
				
				A100_InsertUpdateSQL.InsertUpdateOneRecord(SetString,tgt_table,TgtDB,non_msg_fg);
				B100_DefaultVariable.ClList();
			}
		}
	}
}