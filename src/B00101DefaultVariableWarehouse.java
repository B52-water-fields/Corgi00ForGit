import java.util.ArrayList;

public class B00101DefaultVariableWarehouse{
	//倉庫決定時に初期値として１件以上欲しい情報を作成する
	public static void DefaultVariableWarehouse(String WhCd) {
		DefaultClCreate(WhCd);
	}
	
	public static void DefaultClCreate(String WhCd) {
		//倉庫に荷主が設定されていない場合用に荷主グループClGp000配下で荷主Cl0000を一つ作る
		if(null==WhCd) {WhCd="";}
		
		//倉庫マスタ取得
		ArrayList SearchWHCD = new ArrayList();
		ArrayList SearchWHName = new ArrayList();
		ArrayList SearchPost = new ArrayList();
		ArrayList SearchAdd = new ArrayList();
		ArrayList SearchTel = new ArrayList();
		ArrayList SearchFax = new ArrayList();
		ArrayList SearchMail = new ArrayList();
		ArrayList SearchCom = new ArrayList();
		ArrayList SearchPTMSCD = new ArrayList();
		boolean AllSearch = false;
		
		SearchWHCD.add(WhCd);
		
		Object[][] WhMstRt = M00001WhMstRt.WhMstRt(
					SearchWHCD, SearchWHName, SearchPost,
					SearchAdd, SearchTel, SearchFax, SearchMail,
					SearchCom, SearchPTMSCD,
					AllSearch);
		
		
		if(null!=WhMstRt && 0<WhMstRt.length) {
	    	//新規荷主コードを採番
			String GetClCｄ = M00011ClMstRt.NewClCdGet();
			
			String tgt_table = "KM0030_CLIENTMST";
			String[][] field_name = new String[23][3];
			String[][] entry_data = new String[1][23];
			String[] judg_field = new String[1];
			String[][] judg_data = new String[1][1];
			String TgtDB = "NANKO";
			int non_msg_fg = 1;
			String now_dtm = B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[1];
			
			judg_field[0] = "cl_cd";			//荷主CD
			
			field_name[0][0] = "cl_cd";			//荷主CD
			field_name[1][0] = "ClGpCD";		//荷主グループCD
			field_name[2][0] = "WHCD";			//担当倉庫
			field_name[3][0] = "CLName01";		//荷主名1
			field_name[4][0] = "CLName02";		//荷主名2
			field_name[5][0] = "CLName03";		//荷主名3
			field_name[6][0] = "Post";			//郵便番号
			field_name[7][0] = "Add01";			//住所1
			field_name[8][0] = "Add02";			//住所2
			field_name[9][0] = "Add03";			//住所3
			field_name[10][0] = "Tel";			//電話番号
			field_name[11][0] = "Fax";			//FAX
			field_name[12][0] = "Mail";			//メールアドレス
			field_name[13][0] = "Com01";		//コメント1
			field_name[14][0] = "Com02";		//コメント2
			field_name[15][0] = "Com03";		//コメント3
			field_name[16][0] = "ShimeDate";	//運賃締日
			field_name[17][0] = "ShimeBasis";	//請求基準
			field_name[18][0] = "EntryDate";	//データ登録日時
			field_name[19][0] = "UpdateDate";	//データ更新日時
			field_name[20][0] = "EntryUser";	//登録者コード
			field_name[21][0] = "UpdateUser";	//更新者コード
			field_name[22][0] = "PTMSCD";		//基幹システム連携用荷主コード
	
			field_name[0][1] = "1";		//荷主CD
			field_name[1][1] = "1";		//荷主グループCD
			field_name[2][1] = "1";		//担当倉庫
			field_name[3][1] = "1";		//荷主名1
			field_name[4][1] = "1";		//荷主名2
			field_name[5][1] = "1";		//荷主名3
			field_name[6][1] = "1";		//郵便番号
			field_name[7][1] = "1";		//住所1
			field_name[8][1] = "1";		//住所2
			field_name[9][1] = "1";		//住所3
			field_name[10][1] = "1";	//電話番号
			field_name[11][1] = "1";	//FAX
			field_name[12][1] = "1";	//メールアドレス
			field_name[13][1] = "1";	//コメント1
			field_name[14][1] = "1";	//コメント2
			field_name[15][1] = "1";	//コメント3
			field_name[16][1] = "1";	//運賃締日
			field_name[17][1] = "1";	//請求基準
			field_name[18][1] = "1";	//データ登録日時
			field_name[19][1] = "1";	//データ更新日時
			field_name[20][1] = "1";	//登録者コード
			field_name[21][1] = "1";	//更新者コード
			field_name[22][1] = "1";	//基幹システム連携用荷主コード
			
			field_name[0][2] = "0";		//荷主CD
			field_name[1][2] = "0";		//荷主グループCD
			field_name[2][2] = "0";		//担当倉庫
			field_name[3][2] = "0";		//荷主名1
			field_name[4][2] = "0";		//荷主名2
			field_name[5][2] = "0";		//荷主名3
			field_name[6][2] = "0";		//郵便番号
			field_name[7][2] = "0";		//住所1
			field_name[8][2] = "0";		//住所2
			field_name[9][2] = "0";		//住所3
			field_name[10][2] = "0";	//電話番号
			field_name[11][2] = "0";	//FAX
			field_name[12][2] = "0";	//メールアドレス
			field_name[13][2] = "0";	//コメント1
			field_name[14][2] = "0";	//コメント2
			field_name[15][2] = "0";	//コメント3
			field_name[16][2] = "0";	//運賃締日
			field_name[17][2] = "0";	//請求基準
			field_name[18][2] = "0";	//データ登録日時
			field_name[19][2] = "0";	//データ更新日時
			field_name[20][2] = "0";	//登録者コード
			field_name[21][2] = "0";	//更新者コード
			field_name[22][2] = "0";	//基幹システム連携用荷主コード
			
			judg_data[0][0] = GetClCｄ;			//荷主CD
			
			entry_data[0][0] = GetClCｄ;					//荷主CD
			entry_data[0][1] = "ClGp000";					//荷主グループCD
			entry_data[0][2] = ""+WhMstRt[0][0];			//担当倉庫
			entry_data[0][3] = "TMGHD"+WhMstRt[0][1]+"荷主";//荷主名1
			entry_data[0][4] = "TMGHD"+WhMstRt[0][0];		//荷主名2
			entry_data[0][5] = "";							//荷主名3
			entry_data[0][6] = "5160023";					//郵便番号
			entry_data[0][7] = "三重県伊勢市";				//住所1
			entry_data[0][8] = "宇治館町１";				//住所2
			entry_data[0][9] = "";							//住所3
			entry_data[0][10] = "0596241111";				//電話番号
			entry_data[0][11] = "";	//FAX
			entry_data[0][12] = "";	//メールアドレス
			entry_data[0][13] = "";	//コメント1
			entry_data[0][14] = "";	//コメント2
			entry_data[0][15] = "";	//コメント3
			entry_data[0][16] = "99";	//運賃締日
			entry_data[0][17] = "0";	//請求基準
			entry_data[0][18] = now_dtm;	//データ登録日時
			entry_data[0][19] = now_dtm;	//データ更新日時
			entry_data[0][20] = "(" + A00000Main.LoginUserId + ")" + A00000Main.LoginUserName;	//登録者コード
			entry_data[0][21] = "(" + A00000Main.LoginUserId + ")" + A00000Main.LoginUserName;	//更新者コード
			entry_data[0][22] = "";	//基幹システム連携用荷主コード
			
			A00020InsertUdateSQL.RUN_SQLS_EU(tgt_table, field_name, entry_data, judg_field, judg_data, non_msg_fg,TgtDB);
		}
	}
}