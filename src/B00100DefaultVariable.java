public class B00100DefaultVariable{
	static String[][] LocType;							//ロケタイプ設定値
	static String[][] SearchLocType;					//ロケタイプ検索値
	static String DefaultTroughLoc="ZZZ2222222";		//スルー型運用の場合スルー引当用ロケ
	
	static String DefaultActualDate = "1941/12/08";	//入荷日管理しない場合の入荷実績日
	static String DefaultExpDate = "3000/01/01";		//消費期限管理しない場合の消費期限
	static boolean ActualDateUnControl = false;		//入荷日管理しない
	static String[][] ArryvalFixFgList;				//入荷状況リスト
	static String[][] SearchArryvalFixFgList;		//入荷状況リスト
	static int NormalTaxRate;							//消費税率　※10%なら10
	static int[] TaxRateList;							//消費税率のリスト　※10%なら10　Ex）10,8,0
	static Object[][] TildFG;							//0:常温必須
	static Object[][] SearchTildFG;
	static String PictSetFolder;						//画像保存先フォルダ
	static String ErrSoundPath;						//エラー音パス
	static String OKSoundPath;							//OK音パス
	

	//検索条件 登録条件選択リスト用配列設定
	static Object[][] SearchFeeFixFGList;
	static Object[][] SearchInvoiceStatusList;
	static Object[][] InvoiceStatusList;
	static Object[][] SearchFeeFixList;			//金額確定フラグ検索値(請求)
	static Object[][] FeeFixFGList;				//金額確定フラグ設定値(請求)
	static Object[][] SearchPayFixList;			//金額確定フラグ検索値(支払)
	static Object[][] PayFixFGList;				//金額確定フラグ設定値(支払)
	static Object[][] SearchStatusList;
	static Object[][] StatusList;
	static Object[][] WmsStatusList;
	static Object[][] SearchWmsStatusList;

	static Object[][] SearchDeliveryType01;
	static Object[][] SearchDeliveryType02;
	static Object[][] SearchDeliveryType03;
	static Object[][] SearchDeliveryType04;
	static Object[][] SearchDeliveryType05;

	static Object[][] DeliveryType01;
	static Object[][] DeliveryType02;
	static Object[][] DeliveryType03;
	static Object[][] DeliveryType04;
	static Object[][] DeliveryType05;
	static String DefaultPtmsItemCd;
	
	static Object[][] WhList;						//倉庫リスト
	static Object[][] SearchWhList;				//倉庫リスト
	
	static Object[][] SupplierList;				//仕入先リスト
	
	static Object[][] RouteGloupList;				//自動配車グループリスト	
	static Object[][] SearchRouteGloupList;		//自動配車グループリスト検索条件用
	static Object[][] PurposeList;					//送り状目的区分	
	static Object[][] ChildrenFGList;				//赤黒区分
	static Object[][] ReceiptStampFGList;		//受領印区分
	
	static Object[][] CODList;
	static Object[][] TaxFgList;
	static Object[][] UnitTypeList;
	static Object[][] SearchCODList;
	static Object[][] SearchTaxFgList;
	static Object[][] SearchUnitTypeList;

	static String[] DeliveryWildCard;
	
	static Object[][] SearchClList;				//検索用荷主一覧
	static Object[][] ClList;						//設定用荷主一覧
	
	static Object[][] SearchClGpList;				//検索用荷主グループ一覧
	static Object[][] ClGpList;					//設定用荷主一覧
	
	static Object[][] FeeLogicTypeList;			//運賃計算タイプ(請求)
	static Object[][] PayLogicTypeList;			//運賃計算タイプ(支払)
	
	static Object[][] SearchCautionTiming;		//注意事項タイミング
	static Object[][] CautionTiming;				//注意事項タイミング
	
	static String ShipForcedDeliCd;				//強制出荷届先Cｄ
	static String ShipForcedDeliDepartmentCd;	//強制出荷届先部署Cｄ
	
	static Object[][] WhFeeUnitList;				//倉庫入出荷保管料課金単位リスト
	static Object[][] WhFeeSummaryFgList;		//倉庫入出荷保管料集計区分リスト
	static String[] DateList;						//1日～28日　末日99のリスト
	static Object[][] DeliFeeNorm;					//運賃請求基準　発請求/着請求
	
	static String[][] LayoutPt;					//送り状データ取り込みパターン
	static String[][] HaisyaDataLayoutPt;		//運送会社向け配車データ出力パターン
	
	
	static String[][] DelList;					//削除区分
	
	static Object[][] SerachAuthorityFG;		//ユーザー権限区分
	static Object[][] AuthorityFG;
	
	static int Cl_OldDataRenewDate = 93;		//これより古い荷主管理番号は先頭にOldつけて重複から外す
	
	
	
	
	
	
	// ==========================================================================
    //  zeusログイン時に始まりの情報を登録する
    // ==========================================================================
	
	//zeusログイン時、基本の取引先荷主グループClGp000を作る
	public static void DefaultClGp() {
		//荷主グループ特に設定されていない場合用にClGp000を作る
		String tgt_table = "KM0031_CLIENT_GROUP";
		String[][] field_name = new String[19][3];
		String[][] entry_data = new String[1][19];
		String[] judg_field = new String[1];
		String[][] judg_data = new String[1][1];
		String TgtDB = "NANKO";
		int non_msg_fg = 1;
		String now_dtm = B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[1];
		
		judg_field[0] ="ClGpCD";			//荷主グループCD
		
		field_name[0][0] ="ClGpCD";			//荷主グループCD
		field_name[1][0] ="CLGpName01";		//荷主名1
		field_name[2][0] ="CLGpName02";		//荷主名2
		field_name[3][0] ="CLGpName03";		//荷主名3
		field_name[4][0] ="Post";			//郵便番号
		field_name[5][0] ="Add01";			//住所1
		field_name[6][0] ="Add02";			//住所2
		field_name[7][0] ="Add03";			//住所3
		field_name[8][0] ="Tel";			//電話番号
		field_name[9][0] ="Fax";			//FAX
		field_name[10][0] ="Mail";			//メールアドレス
		field_name[11][0] ="Com01";			//コメント1
		field_name[12][0] ="Com02";			//コメント2
		field_name[13][0] ="Com03";			//コメント3
		field_name[14][0] ="EntryDate";		//データ登録日時
		field_name[15][0] ="UpdateDate";	//データ更新日時
		field_name[16][0] ="EntryUser";		//登録者コード
		field_name[17][0] ="UpdateUser";	//更新者コード
		field_name[18][0] ="PassWord";		//パスワード

		field_name[0][1] ="1";		//荷主グループCD
		field_name[1][1] ="1";		//荷主名1
		field_name[2][1] ="1";		//荷主名2
		field_name[3][1] ="1";		//荷主名3
		field_name[4][1] ="1";		//郵便番号
		field_name[5][1] ="1";		//住所1
		field_name[6][1] ="1";		//住所2
		field_name[7][1] ="1";		//住所3
		field_name[8][1] ="1";		//電話番号
		field_name[9][1] ="1";		//FAX
		field_name[10][1] ="1";		//メールアドレス
		field_name[11][1] ="1";		//コメント1
		field_name[12][1] ="1";		//コメント2
		field_name[13][1] ="1";		//コメント3
		field_name[14][1] ="1";		//データ登録日時
		field_name[15][1] ="1";		//データ更新日時
		field_name[16][1] ="1";		//登録者コード
		field_name[17][1] ="1";		//更新者コード
		field_name[18][1] ="1";		//パスワード
		
		field_name[0][2] ="0";		//荷主グループCD
		field_name[1][2] ="0";		//荷主名1
		field_name[2][2] ="0";		//荷主名2
		field_name[3][2] ="0";		//荷主名3
		field_name[4][2] ="0";		//郵便番号
		field_name[5][2] ="0";		//住所1
		field_name[6][2] ="0";		//住所2
		field_name[7][2] ="0";		//住所3
		field_name[8][2] ="0";		//電話番号
		field_name[9][2] ="0";		//FAX
		field_name[10][2] ="0";		//メールアドレス
		field_name[11][2] ="0";		//コメント1
		field_name[12][2] ="0";		//コメント2
		field_name[13][2] ="0";		//コメント3
		field_name[14][2] ="0";		//データ登録日時
		field_name[15][2] ="0";		//データ更新日時
		field_name[16][2] ="0";		//登録者コード
		field_name[17][2] ="0";		//更新者コード
		field_name[18][2] ="0";		//パスワード
		
		judg_data[0][0] ="ClGp000";		//荷主グループCD
		
		entry_data[0][0] ="ClGp000";							//荷主グループCD
		entry_data[0][1] ="高天原ホールディングス";				//荷主名1
		entry_data[0][2] ="高天原ホールディングス株式会社";		//荷主名2
		entry_data[0][3] ="";				//荷主名3
		entry_data[0][4] ="5160023";		//郵便番号
		entry_data[0][5] ="三重県伊勢市";	//住所1
		entry_data[0][6] ="宇治館町１";		//住所2
		entry_data[0][7] ="";				//住所3
		entry_data[0][8] ="";				//電話番号
		entry_data[0][9] ="0596241111";		//FAX
		entry_data[0][10] ="";				//メールアドレス
		entry_data[0][11] ="";				//コメント1
		entry_data[0][12] ="";				//コメント2
		entry_data[0][13] ="";				//コメント3
		entry_data[0][14] =now_dtm;			//データ登録日時
		entry_data[0][15] =now_dtm;			//データ更新日時
		entry_data[0][16] ="(" + A00000Main.LoginUserId + ")" + A00000Main.LoginUserName;			//登録者コード
		entry_data[0][17] ="(" + A00000Main.LoginUserId + ")" + A00000Main.LoginUserName;			//更新者コード
		entry_data[0][18] ="";				//パスワード
		
		A00020InsertUdateSQL.RUN_SQLS_EU(tgt_table, field_name, entry_data, judg_field, judg_data, non_msg_fg,TgtDB);
	}
	
	//zeusログイン時、基本の運送会社　自社SC00000を作る
	public static void DefaultShippingCompany() {
		String tgt_table = "KM0070_SHIPPINGCOMPANYMST";
		String[][] field_name = new String[21][3];
		String[][] entry_data = new String[1][21];
		String[] judg_field = new String[1];
		String[][] judg_data = new String[1][1];
		String TgtDB = "NANKO";
		int non_msg_fg = 1;
		String now_dtm = B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[1];

		judg_field[0] = "ShippingCompanyCd";		//運送会社CD
		
		field_name[0][0] = "ShippingCompanyCd";		//運送会社CD
		field_name[1][0] = "ShippingCompanyName01";	//運送会社名1
		field_name[2][0] = "ShippingCompanyName02";	//運送会社名2
		field_name[3][0] = "ShippingCompanyName03";	//運送会社名3
		field_name[4][0] = "Post";					//運送会社郵便
		field_name[5][0] = "Add01";					//運送会社住所1
		field_name[6][0] = "Add02";					//運送会社住所2
		field_name[7][0] = "Add03";					//運送会社住所3
		field_name[8][0] = "Tel";					//運送会社電話
		field_name[9][0] = "Fax";					//運送会社FAX
		field_name[10][0] = "Mail";					//運送会社MAIL
		field_name[11][0] = "Com01";				//コメント1
		field_name[12][0] = "Com02";				//コメント2
		field_name[13][0] = "Com03";				//コメント3
		field_name[14][0] = "ShimeDate";			//締日
		field_name[15][0] = "ShimeBasis";			//請求基準
		field_name[16][0] = "EntryDate";			//データ登録日時
		field_name[17][0] = "UpdateDate";			//データ更新日時
		field_name[18][0] = "EntryUser";			//登録者コード
		field_name[19][0] = "UpdateUser";			//更新者コード
		field_name[20][0] = "PTMSCD";				//基幹システム連携用傭車コード
		
		field_name[0][1] = "1";		//運送会社CD
		field_name[1][1] = "1";		//運送会社名1
		field_name[2][1] = "1";		//運送会社名2
		field_name[3][1] = "1";		//運送会社名3
		field_name[4][1] = "1";		//運送会社郵便
		field_name[5][1] = "1";		//運送会社住所1
		field_name[6][1] = "1";		//運送会社住所2
		field_name[7][1] = "1";		//運送会社住所3
		field_name[8][1] = "1";		//運送会社電話
		field_name[9][1] = "1";		//運送会社FAX
		field_name[10][1] = "1";	//運送会社MAIL
		field_name[11][1] = "1";	//コメント1
		field_name[12][1] = "1";	//コメント2
		field_name[13][1] = "1";	//コメント3
		field_name[14][1] = "1";	//締日
		field_name[15][1] = "1";	//請求基準
		field_name[16][1] = "1";	//データ登録日時
		field_name[17][1] = "1";	//データ更新日時
		field_name[18][1] = "1";	//登録者コード
		field_name[19][1] = "1";	//更新者コード
		field_name[20][1] = "1";	//基幹システム連携用傭車コード

		field_name[0][2] = "0";		//運送会社CD
		field_name[1][2] = "0";		//運送会社名1
		field_name[2][2] = "0";		//運送会社名2
		field_name[3][2] = "0";		//運送会社名3
		field_name[4][2] = "0";		//運送会社郵便
		field_name[5][2] = "0";		//運送会社住所1
		field_name[6][2] = "0";		//運送会社住所2
		field_name[7][2] = "0";		//運送会社住所3
		field_name[8][2] = "0";		//運送会社電話
		field_name[9][2] = "0";		//運送会社FAX
		field_name[10][2] = "0";	//運送会社MAIL
		field_name[11][2] = "0";	//コメント1
		field_name[12][2] = "0";	//コメント2
		field_name[13][2] = "0";	//コメント3
		field_name[14][2] = "0";	//締日
		field_name[15][2] = "0";	//請求基準
		field_name[16][2] = "0";	//データ登録日時
		field_name[17][2] = "0";	//データ更新日時
		field_name[18][2] = "0";	//登録者コード
		field_name[19][2] = "0";	//更新者コード
		field_name[20][2] = "0";	//基幹システム連携用傭車コード
		
		judg_data[0][0] = "SC00000";		//運送会社CD
		
		entry_data[0][0] = "SC00000";					//運送会社CD
		entry_data[0][1] = "自社設定初期値";		//運送会社名1
		entry_data[0][2] = "";						//運送会社名2
		entry_data[0][3] = "";						//運送会社名3
		entry_data[0][4] = "6990701";				//運送会社郵便
		entry_data[0][5] = "島根県出雲市大社町杵築東１９５";		//運送会社住所1
		entry_data[0][6] = "";			//運送会社住所2
		entry_data[0][7] = "";			//運送会社住所3
		entry_data[0][8] = "";			//運送会社電話
		entry_data[0][9] = "";			//運送会社FAX
		entry_data[0][10] = "";			//運送会社MAIL
		entry_data[0][11] = "";			//コメント1
		entry_data[0][12] = "";			//コメント2
		entry_data[0][13] = "";			//コメント3
		entry_data[0][14] = "99";		//締日
		entry_data[0][15] = "0";		//請求基準
		entry_data[0][16] = now_dtm;	//データ登録日時
		entry_data[0][17] = now_dtm;	//データ更新日時
		entry_data[0][18] = "(" + A00000Main.LoginUserId + ")" + A00000Main.LoginUserName;		//登録者コード
		entry_data[0][19] = "(" + A00000Main.LoginUserId + ")" + A00000Main.LoginUserName;		//更新者コード
		entry_data[0][20] = "";			//基幹システム連携用傭車コード

		A00020InsertUdateSQL.RUN_SQLS_EU(tgt_table, field_name, entry_data, judg_field, judg_data, non_msg_fg,TgtDB);
	}
	
	//zeusログイン時、自分が所属する倉庫マスタ作成
	public static void DefaultWarehouse(){
		String tgt_table = "KM0010_WHMST";
		String[][] field_name = new String[16][3];
		String[][] entry_data = new String[1][16];
		String[] judg_field = new String[1];
		String[][] judg_data = new String[1][1];
		String TgtDB = "NANKO";
		int non_msg_fg = 1;
		String now_dtm = B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[1];

		judg_field[0] = "WHCD";			//倉庫コード
		
		field_name[0][0] = "WHCD";			//倉庫コード
		field_name[1][0] = "WHName";		//拠点倉庫名
		field_name[2][0] = "Post";			//拠点倉庫郵便番号
		field_name[3][0] = "Add01";			//拠点倉庫住所1
		field_name[4][0] = "Add02";			//拠点倉庫住所2
		field_name[5][0] = "Tel";			//拠点倉庫電話
		field_name[6][0] = "Fax";			//拠点倉庫FAX
		field_name[7][0] = "Mail";			//拠点倉庫MAIL
		field_name[8][0] = "Com01";			//コメント１
		field_name[9][0] = "Com02";			//コメント２
		field_name[10][0] = "Com03";		//コメント３
		field_name[11][0] = "PTMSCD";		//基幹システム連携用ユーザーCD
		field_name[12][0] = "EntryDate";	//データ登録日時
		field_name[13][0] = "UpdateDate";	//データ更新日時
		field_name[14][0] = "EntryUser";	//登録者コード
		field_name[15][0] = "UpdateUser";	//更新者コード

		field_name[0][1] = "1";		//倉庫コード
		field_name[1][1] = "1";		//拠点倉庫名
		field_name[2][1] = "1";		//拠点倉庫郵便番号
		field_name[3][1] = "1";		//拠点倉庫住所1
		field_name[4][1] = "1";		//拠点倉庫住所2
		field_name[5][1] = "1";		//拠点倉庫電話
		field_name[6][1] = "1";		//拠点倉庫FAX
		field_name[7][1] = "1";		//拠点倉庫MAIL
		field_name[8][1] = "1";		//コメント１
		field_name[9][1] = "1";		//コメント２
		field_name[10][1] = "1";	//コメント３
		field_name[11][1] = "1";	//基幹システム連携用ユーザーCD
		field_name[12][1] = "1";	//データ登録日時
		field_name[13][1] = "1";	//データ更新日時
		field_name[14][1] = "1";	//登録者コード
		field_name[15][1] = "1";	//更新者コード
		
		field_name[0][2] = "0";		//倉庫コード
		field_name[1][2] = "0";		//拠点倉庫名
		field_name[2][2] = "0";		//拠点倉庫郵便番号
		field_name[3][2] = "0";		//拠点倉庫住所1
		field_name[4][2] = "0";		//拠点倉庫住所2
		field_name[5][2] = "0";		//拠点倉庫電話
		field_name[6][2] = "0";		//拠点倉庫FAX
		field_name[7][2] = "0";		//拠点倉庫MAIL
		field_name[8][2] = "0";		//コメント１
		field_name[9][2] = "0";		//コメント２
		field_name[10][2] = "0";	//コメント３
		field_name[11][2] = "0";	//基幹システム連携用ユーザーCD
		field_name[12][2] = "0";	//データ登録日時
		field_name[13][2] = "0";	//データ更新日時
		field_name[14][2] = "0";	//登録者コード
		field_name[15][2] = "0";	//更新者コード
		
		judg_data[0][0] = "0000";			//倉庫コード
		
		entry_data[0][0] = "0000";			//倉庫コード
		entry_data[0][1] = "初期設定倉庫";		//拠点倉庫名
		entry_data[0][2] = "4568585";			//拠点倉庫郵便番号
		entry_data[0][3] = "愛知県名古屋市熱田区神宮１丁目１−１";		//拠点倉庫住所1
		entry_data[0][4] = "";		//拠点倉庫住所2
		entry_data[0][5] = "0526714151";		//拠点倉庫電話
		entry_data[0][6] = "";			//拠点倉庫FAX
		entry_data[0][7] = "";			//拠点倉庫MAIL
		entry_data[0][8] = "";			//コメント１
		entry_data[0][9] = "";			//コメント２
		entry_data[0][10] = "";			//コメント３
		entry_data[0][11] = "";			//基幹システム連携用ユーザーCD
		entry_data[0][12] = now_dtm;	//データ登録日時
		entry_data[0][13] = now_dtm;	//データ更新日時
		entry_data[0][14] = "(" + A00000Main.LoginUserId + ")" + A00000Main.LoginUserName;		//登録者コード
		entry_data[0][15] = "(" + A00000Main.LoginUserId + ")" + A00000Main.LoginUserName;		//更新者コード

		A00020InsertUdateSQL.RUN_SQLS_EU(tgt_table, field_name, entry_data, judg_field, judg_data, non_msg_fg,TgtDB);
		B00101DefaultVariableWarehouse.DefaultVariableWarehouse("0000");
	}
}