import java.util.ArrayList;

public class B00100DefaultVariable{
	//設定用条件
	static String[][] HaisyaDataLayoutPt;			//運送会社向け配車データ出力パターン ※個別開発ごとにHaisyaDataLayoutPt()修正
	static String[][] LayoutPt;						//荷主データ⇒送り状データ取り込みパターン　※個別開発ごとにLayoutPt()修正
	
	static String[][] 	ShimeDateList;				//1日～28日　末日99のリスト
	static String[][] 	DeliFeeNorm;					//運賃請求基準　発請求/着請求

	static String[][] SearchWhList;					//倉庫リスト
	static String[][] WhList;							//倉庫リスト
	
	static String[][] SearchClGpList;					//検索用荷主グループ一覧
	static String[][] ClGpList;						//設定用荷主グループ一覧
	
	static String[][] SearchClList;					//検索用荷主一覧
	static String[][] ClList;							//設定用荷主一覧
	
	static String[][] SearchShippingCompanyList;	//検索用運送会社一覧
	static String[][] ShippingCompanyList;			//設定用運送会社一覧
	
	static String[][] SearchDelList = {{"0:稼働中","1:削除","未指定"},{"0","1",""},{"稼働中","削除",""}};		//検索用削除区分
	static String[][] DelList = {{"0:稼働中","1:削除"},{"0","1"},{"稼働中","削除"}};							//設定用削除区分
	
	static String[][] SerachAuthorityFG;				//検索用ユーザー権限区分
	static String[][] AuthorityFG;						//設定用ユーザー権限区分
	
	static String[][] SearchLocType = {{"未指定","0:通常","1:保管","8:入荷時","9:引当禁止"},{"","0","1","8","9"},{"","通常","保管","入荷時","引当禁止"}};		//ロケタイプ検索値
	static String[][] LocType = {{"0:通常","1:保管","8:入荷時","9:引当禁止"},{"0","1","8","9"},{"通常","保管","入荷時","引当禁止"}};							//ロケタイプ設定値
	
	static String DefaultActualDate 	= "1941/12/08";	//入荷日管理しない場合の入荷実績日
	
	static String DefaultTroughLoc	= "ZZZ2222222";	//スルー型運用の場合スルー引当用ロケ
	
	static String DefaultExpDate 		= "3000/01/01";	//消費期限管理しない場合の消費期限
	
	static String DefaultPtmsItemCd = "0001";			//基幹システム商品CD
	
	static String[][] SearchStatusList = {{"未指定","0:未配車","1:配車済","2:出荷完了","3:配達完了","8:保留","9:キャンセル"},{"","0","1","2","3","8","9"}};	//検索条件：状況
	static String[][] StatusList = {{"0:未配車","1:配車済","2:出荷完了","3:配達完了","8:保留","9:キャンセル"},{"0","1","2","3","8","9"}};						//状況
	
	static String[][] SearchWmsStatusList = {{"未指定","0:未引当","1:引当済","2:指示済","3:出荷済","8:引当保留","7:出荷対象外","9:キャンセル"},{"","0","1","2","3","8","7","9"}};	//検索条件：倉庫状況
	static String[][] WmsStatusList = {{"0:未引当","1:引当済","2:指示済","3:出荷済","8:引当保留","7:出荷対象外","9:キャンセル"},{"0","1","2","3","8","7","9"}};						//倉庫状況
	
	static String[][] SearchArryvalFixFgList = {{"未指定","0:未入荷","1:入荷済","2:分納待","9:キャンセル"},{"","0","1","2","9"},{"","未入荷","入荷済","分納待","キャンセル"}};		//入荷状況リスト
	static String[][] ArryvalFixFgList = {{"0:未入荷","1:入荷済","2:分納待","9:キャンセル"},{"0","1","2","9"},{"未入荷","入荷済","分納待","キャンセル"}};								//入荷状況リスト
	
	static String[][] SearchCautionTiming = {{"未指定","0:納品時","1:出荷時"},{"","0","1"},{"","納品時","出荷時"}};		//検索条件：注意事項タイミング
	static String[][] CautionTiming = {{"0:納品時","1:出荷時"},{"0","1"},{"納品時","出荷時"}};							//注意事項タイミング
	
	static String[][] ChildrenFGList = {{"0:親伝票","1:子伝票"},{"0","1"},{"親伝票","子伝票"}};				//赤黒区分
	
	static String[][] SearchTildFG = {{"未指定","0:常温","1:冷蔵","2:冷凍","3:チルド"},{"","0","1","2","3"},{"","常温","冷蔵","冷凍","チルド"}};
	static String[][] TildFG = {{"0:常温","1:冷蔵","2:冷凍","3:チルド"},{"0","1","2","3"},{"常温","冷蔵","冷凍","チルド"}};								//0:常温必須
	
	static String[][] ReceiptStampFGList = {{"0:未回収","1:回収済","2:返送済","9:回収不要"},{"0","1","2","9"},{"未回収","回収済","返送済","回収不要"}};	//受領印区分
	
	static String[][] SearchInvoiceStatusList = {{"未指定","0:未請求","1:請求済","9:対象外"},{"","0","1","9"},{"","未請求","請求済","対象外"}};			//請求区分
	static String[][] InvoiceStatusList = {{"0:未請求","1:請求済","9:対象外"},{"0","1","9"},{"未請求","請求済","対象外"}};
	
	static String[][] SearchFeeFixFgList = {{"未指定","0:未確定","1:確定済"},{"","0","1"},{"未指定","未確定","確定済"}};			//金額確定フラグ検索値(請求)
	static String[][] FeeFixFgList = {{"0:未確定","1:確定済"},{"0","1"},{"未確定","確定済"}};										//金額確定フラグ設定値(請求)
	
	static String[][] SearchPayFixFgList = {{"未指定","0:未確定","1:確定済"},{"","0","1"},{"未指定","未確定","確定済"}};			//金額確定フラグ検索値(支払)
	static String[][] PayFixFgList = {{"0:未確定","1:確定済"},{"0","1"},{"未確定","確定済"}};										//金額確定フラグ設定値(支払)
	
	static String[][] SearchCODList = {{"未指定","0:一般","1:代引"},{"","0","1"},{"","一般","代引"}};		//検索条件：代引区分
	static String[][] CODList = {{"0:一般","1:代引"},{"0","1"},{"一般","代引"}};							//代引区分
	
	static String[][] SearchTaxFgList = {{"未指定","0:外税","1:内税","2:非課税"},{"","0","1","2"},{"","外税","内税","非課税"}};	//検索条件：外税内税区分
	static String[][] TaxFgList = {{"0:外税","1:内税","2:非課税"},{"0","1","2"},{"外税","内税","非課税"}};	//外税内税区分
	
	static String[][] SearchUnitTypeList = {{"未指定","0:バラ","1:カートン","2:ケース","3：パレット"},{"","0","1","2","3"},{"","バラ","カートン","ケース","パレット"}};
	static String[][] UnitTypeList = {{"0:バラ","1:カートン","2:ケース","3：パレット"},{"0","1","2","3"},{"バラ","カートン","ケース","パレット"}};
	
	static String[][] PurposeList = {{"0:配達","1:配達","2:集荷","3:中継"},{"0","1","2","3"},{"配達","配達","集荷","中継"}};					//送り状目的区分
	
	static String[][] SearchDeliveryType01;
	static String[][] SearchDeliveryType02;
	static String[][] SearchDeliveryType03;
	static String[][] SearchDeliveryType04;
	static String[][] SearchDeliveryType05;

	static String[][] DeliveryType01;
	static String[][] DeliveryType02;
	static String[][] DeliveryType03;
	static String[][] DeliveryType04;
	static String[][] DeliveryType05;
	
	
	
	/*	
	
	
	*/
	
	
	/*
	====================================================================
	↑設定確認済み
	====================================================================
	*/

	
	static boolean ActualDateUnControl = false;		//入荷日管理しない
	
	static int NormalTaxRate;							//消費税率　※10%なら10
	static int[] TaxRateList;							//消費税率のリスト　※10%なら10　Ex）10,8,0
	
	static String PictSetFolder;						//画像保存先フォルダ
	static String ErrSoundPath;						//エラー音パス
	static String OKSoundPath;							//OK音パス
	
	static int Cl_OldDataRenewDate = 93;				//これより古い荷主管理番号は先頭にOldつけて重複から外す

	//検索条件 登録条件選択リスト用配列設定


	
	
	static Object[][] SupplierList;				//仕入先リスト
	
	static Object[][] RouteGloupList;				//自動配車グループリスト	
	static Object[][] SearchRouteGloupList;		//自動配車グループリスト検索条件用

	static String[] DeliveryWildCard;
	
	static Object[][] FeeLogicTypeList;			//運賃計算タイプ(請求)
	static Object[][] PayLogicTypeList;			//運賃計算タイプ(支払)
	
	static Object[][] WhFeeUnitList;				//倉庫入出荷保管料課金単位リスト
	static Object[][] WhFeeSummaryFgList;		//倉庫入出荷保管料集計区分リスト
	
	static String ShipForcedDeliCd;				//強制出荷届先Cｄ
	static String ShipForcedDeliDepartmentCd;	//強制出荷届先部署Cｄ

	
	public static void DefaultVariable() {
		//ログイン⇒荷主決定時に前提情報を取得する
		//荷主選択処理後も呼び出されます
		WhList();						//倉庫一覧生成
		ClGpList();					//荷主グループ一覧生成
		ClList();						//荷主一覧生成
		ShippingCompanyList();		//運送会社一覧生成
		
		
		ShimeList();					//締め日・締め条件リスト生成
		AuthorityFG();					//管理者ログイン時のみ権利権限設定
		
		HaisyaDataLayoutPt();		//運送会社向け配車データ出力パターン
		LayoutPt();					//荷主データ⇒送り状データ取り込みパターン
		
		DeliveryType();				//運送タイプ
	}
	
	private static void HaisyaDataLayoutPt() {		//運送会社向け配車データ出力パターン
		HaisyaDataLayoutPt = new String[3][1];
		
		HaisyaDataLayoutPt[0][0] = "Normal:標準";
		HaisyaDataLayoutPt[1][0] = "Normal";
		HaisyaDataLayoutPt[2][0] = "標準";
	}
	
	private static void LayoutPt() {				//荷主データ⇒送り状データ取り込みパターン
		LayoutPt = new String[3][1];
		
		LayoutPt[0][0] = "Normal:標準";
		LayoutPt[1][0] = "Normal";
		LayoutPt[2][0] = "標準";
	}

	
	public static void WhList() {
		ArrayList<String> SearchWHCD = new ArrayList<String>();
		ArrayList<String> SearchWHName = new ArrayList<String>();
		ArrayList<String> SearchPost = new ArrayList<String>();
		ArrayList<String> SearchAdd = new ArrayList<String>();
		ArrayList<String> SearchTel = new ArrayList<String>();
		ArrayList<String> SearchFax = new ArrayList<String>();
		ArrayList<String> SearchMail = new ArrayList<String>();
		ArrayList<String> SearchCom = new ArrayList<String>();
		ArrayList<String> SearchPTMSCD = new ArrayList<String>();
		boolean AllSearch = true;
		
		Object[][] WhMstRt = M00001WhMstRt.WhMstRt(
					SearchWHCD,SearchWHName,SearchPost,
					SearchAdd,SearchTel,SearchFax,SearchMail,
					SearchCom,SearchPTMSCD,
					AllSearch);
				
		WhList 		= new String[3][WhMstRt.length];				//倉庫リスト
		SearchWhList 	= new String[3][WhMstRt.length+1];				//倉庫リスト
		
		SearchWhList[0][0] = "未指定";
		SearchWhList[1][0] = "";
		SearchWhList[2][0] = "";
		
		
		for(int i=0;i<WhMstRt.length;i++) {
			WhList[0][i] = "" + WhMstRt[i][0] + ":" + WhMstRt[i][1];
			WhList[1][i] = "" + WhMstRt[i][0];
			WhList[2][i] = "" + WhMstRt[i][1];
			
			SearchWhList[0][i+1] = "" + WhMstRt[i][0] + ":" + WhMstRt[i][1];
			SearchWhList[1][i+1] = "" + WhMstRt[i][0];
			SearchWhList[2][i+1] = "" + WhMstRt[i][1];
		}
	}
	
	public static void ClGpList() {
		ArrayList<String> SearchClGpCD = new ArrayList<String>();
		ArrayList<String> SearchCLGpName = new ArrayList<String>();
		ArrayList<String> SearchPost = new ArrayList<String>();
		ArrayList<String> SearchAdd = new ArrayList<String>();
		ArrayList<String> SearchTel = new ArrayList<String>();
		ArrayList<String> SearchFax = new ArrayList<String>();
		ArrayList<String> SearchMail = new ArrayList<String>();
		ArrayList<String> SearchCom = new ArrayList<String>();
		boolean AllSearch = true;
		
		Object[][] ClGpMstRt = M00010ClGpMstRt.ClGpMstRt(
					SearchClGpCD,SearchCLGpName,SearchPost,
					SearchAdd,SearchTel,SearchFax,SearchMail,SearchCom,AllSearch);
		
		SearchClGpList 	= new String[3][ClGpMstRt.length+1];				//検索用荷主グループ一覧
		ClGpList 			= new String[3][ClGpMstRt.length];					//設定用荷主グループ一覧
		
		SearchClGpList[0][0] = "未指定";
		SearchClGpList[1][0] = "";
		SearchClGpList[2][0] = "";
		
		for(int i=0;i<ClGpMstRt.length;i++) {
			ClGpList[0][i] = "" + ClGpMstRt[i][0] + ":" + ClGpMstRt[i][1];
			ClGpList[1][i] = "" + ClGpMstRt[i][0];
			ClGpList[2][i] = "" + ClGpMstRt[i][1];
			
			SearchClGpList[0][i+1] = "" + ClGpMstRt[i][0] + ":" + ClGpMstRt[i][1];
			SearchClGpList[1][i+1] = "" + ClGpMstRt[i][0];
			SearchClGpList[2][i+1] = "" + ClGpMstRt[i][1];
		}
	}
	
	public static void ClList() {
		ArrayList<String> SearchClGpCD = new ArrayList<String>();
		ArrayList<String> SearchCLCD = new ArrayList<String>();
		ArrayList<String> SearchCLName = new ArrayList<String>();
		ArrayList<String> SearchPost = new ArrayList<String>();
		ArrayList<String> searchAdd = new ArrayList<String>();
		ArrayList<String> SearchTel = new ArrayList<String>();
		ArrayList<String> SearchFax = new ArrayList<String>();
		ArrayList<String> SearchMail = new ArrayList<String>();
		ArrayList<String> SearchCom = new ArrayList<String>();
		ArrayList<String> SearchWHCD = new ArrayList<String>();
		boolean AllSearch = true;
		
		Object[][] ClMstRt = M00011ClMstRt.ClMstRt(
					SearchClGpCD,SearchCLCD,SearchCLName,SearchPost,searchAdd,
					SearchTel,SearchFax,SearchMail,SearchCom,SearchWHCD,AllSearch);
		

		SearchClList 	= new String[3][ClMstRt.length+1];			//検索用荷主グループ一覧
		ClList 		= new String[3][ClMstRt.length];			//設定用荷主グループ一覧
		
		SearchClList[0][0] = "未指定";
		SearchClList[1][0] = "";
		SearchClList[2][0] = "";
		
		for(int i=0;i<ClMstRt.length;i++) {
			ClList[0][i] = "" + ClMstRt[i][0] + ":" + ClMstRt[i][5];
			ClList[1][i] = "" + ClMstRt[i][0];
			ClList[2][i] = "" + ClMstRt[i][5];
			
			SearchClList[0][i+1] = "" + ClMstRt[i][0] + ":" + ClMstRt[i][5];
			SearchClList[1][i+1] = "" + ClMstRt[i][0];
			SearchClList[2][i+1] = "" + ClMstRt[i][5];
		}
	}
	
	public static void ShippingCompanyList() {
		ArrayList<String> SearchShippingCompanyCd = new ArrayList<String>();
		ArrayList<String> SearchCompanyName = new ArrayList<String>();
		ArrayList<String> SearchPost = new ArrayList<String>();
		ArrayList<String> SearchAdd = new ArrayList<String>();
		ArrayList<String> SearchTel = new ArrayList<String>();
		ArrayList<String> SearchFax = new ArrayList<String>();
		ArrayList<String> SearchMail = new ArrayList<String>();
		ArrayList<String> SearchCom = new ArrayList<String>();
		boolean AllSearch = true;
		
		Object[][] ShippingCompanyMstRt = M00030ShippingCompanyMstRt.ShippingCompanyMstRt(
					SearchShippingCompanyCd,SearchCompanyName,SearchPost,SearchAdd,
					SearchTel,SearchFax,SearchMail,SearchCom,AllSearch);
		
		SearchShippingCompanyList 	= new String[3][ShippingCompanyMstRt.length+1];		//検索用荷主グループ一覧
		ShippingCompanyList 		= new String[3][ShippingCompanyMstRt.length];			//設定用荷主グループ一覧
		
		SearchShippingCompanyList[0][0] = "未指定";
		SearchShippingCompanyList[1][0] = "";
		SearchShippingCompanyList[2][0] = "";
		
		for(int i=0;i<ShippingCompanyMstRt.length;i++) {
			ShippingCompanyList[0][i] = "" + ShippingCompanyMstRt[i][0] + ":" + ShippingCompanyMstRt[i][1];
			ShippingCompanyList[1][i] = "" + ShippingCompanyMstRt[i][0];
			ShippingCompanyList[2][i] = "" + ShippingCompanyMstRt[i][1];
			
			SearchShippingCompanyList[0][i+1] = "" + ShippingCompanyMstRt[i][0] + ":" + ShippingCompanyMstRt[i][1];
			SearchShippingCompanyList[1][i+1] = "" + ShippingCompanyMstRt[i][0];
			SearchShippingCompanyList[2][i+1] = "" + ShippingCompanyMstRt[i][1];
		}
	}
	
	public static void DeliveryType() {
		//運送タイプマスタ00無ければつくる
		String tgt_table = "KM0050_DELIVERY_TYPEMST";
		String[][] field_name = new String[7][3];
		String[][] entry_data = new String[5][7];
		String[] judg_field = new String[2];
		String[][] judg_data = new String[5][2];
		String TgtDB = "NYANKO";
		int non_msg_fg = 1;
		String now_dtm = B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[1];
		
		field_name[0][0] = "DeliveryTypeNo";	//タイプ番号
		field_name[1][0] = "DeliveryTypeCd";	//運送タイプコード
		field_name[2][0] = "DeliveryTypeName";	//運送タイプ名
		field_name[3][0] = "EntryDate";			//データ登録日時
		field_name[4][0] = "UpdateDate";		//データ更新日時
		field_name[5][0] = "EntryUser";			//登録者コード
		field_name[6][0] = "UpdateUser";		//更新者コード

		field_name[0][1] = "1";	//タイプ番号
		field_name[1][1] = "1";	//運送タイプコード
		field_name[2][1] = "1";	//運送タイプ名
		field_name[3][1] = "1";	//データ登録日時
		field_name[4][1] = "1";	//データ更新日時
		field_name[5][1] = "1";	//登録者コード
		field_name[6][1] = "1";	//更新者コード

		field_name[0][2] = "0";	//タイプ番号
		field_name[1][2] = "0";	//運送タイプコード
		field_name[2][2] = "0";	//運送タイプ名
		field_name[3][2] = "0";	//データ登録日時
		field_name[4][2] = "0";	//データ更新日時
		field_name[5][2] = "0";	//登録者コード
		field_name[6][2] = "0";	//更新者コード

		judg_field[0] = "DeliveryTypeNo";	//タイプ番号
		judg_field[1] = "DeliveryTypeCd";	//運送タイプコード

		entry_data[0][0] = "1";
		entry_data[0][1] = "00";
		entry_data[0][2] = "タイプ01標準";
		entry_data[0][3] = now_dtm;
		entry_data[0][4] = now_dtm;
		entry_data[0][5] = "(" + A00000Main.LoginUserId + ")" + A00000Main.LoginUserName;
		entry_data[0][6] = "(" + A00000Main.LoginUserId + ")" + A00000Main.LoginUserName;

		judg_data[0][0] = "1";
		judg_data[0][1] = "00";

		entry_data[1][0] = "2";
		entry_data[1][1] = "00";
		entry_data[1][2] = "タイプ02標準";
		entry_data[1][3] = now_dtm;
		entry_data[1][4] = now_dtm;
		entry_data[1][5] = "(" + A00000Main.LoginUserId + ")" + A00000Main.LoginUserName;
		entry_data[1][6] = "(" + A00000Main.LoginUserId + ")" + A00000Main.LoginUserName;

		judg_data[1][0] = "2";
		judg_data[1][1] = "00";

		entry_data[2][0] = "3";
		entry_data[2][1] = "00";
		entry_data[2][2] = "タイプ03標準";
		entry_data[2][3] = now_dtm;
		entry_data[2][4] = now_dtm;
		entry_data[2][5] = "(" + A00000Main.LoginUserId + ")" + A00000Main.LoginUserName;
		entry_data[2][6] = "(" + A00000Main.LoginUserId + ")" + A00000Main.LoginUserName;

		judg_data[2][0] = "3";
		judg_data[2][1] = "00";

		entry_data[3][0] = "4";
		entry_data[3][1] = "00";
		entry_data[3][2] = "タイプ04標準";
		entry_data[3][3] = now_dtm;
		entry_data[3][4] = now_dtm;
		entry_data[3][5] = "(" + A00000Main.LoginUserId + ")" + A00000Main.LoginUserName;
		entry_data[3][6] = "(" + A00000Main.LoginUserId + ")" + A00000Main.LoginUserName;

		judg_data[3][0] = "4";
		judg_data[3][1] = "00";

		entry_data[4][0] = "5";
		entry_data[4][1] = "00";
		entry_data[4][2] = "タイプ05標準";
		entry_data[4][3] = now_dtm;
		entry_data[4][4] = now_dtm;
		entry_data[4][5] = "(" + A00000Main.LoginUserId + ")" + A00000Main.LoginUserName;
		entry_data[4][6] = "(" + A00000Main.LoginUserId + ")" + A00000Main.LoginUserName;

		judg_data[4][0] = "5";
		judg_data[4][1] = "00";
		
		A00020InsertUdateSQL.RUN_SQLS_EU(tgt_table, field_name, entry_data, judg_field, judg_data, non_msg_fg,TgtDB);
		
		ArrayList<String> SearchDeliveryTypeNo = new ArrayList<String>();
		ArrayList<String> SearchDeliveryTypeCd = new ArrayList<String>();
		ArrayList<String> SearchDeliveryTypeName = new ArrayList<String>();
		boolean AllSearch = false;
		
		SearchDeliveryTypeNo = new ArrayList<String>();
		SearchDeliveryTypeNo.add("1");
		Object[][] DeliveryTypeMstRt01 = M00050DeliveryTypeMstRt.DeliveryTypeMstRt(
				SearchDeliveryTypeNo,
				SearchDeliveryTypeCd,
				SearchDeliveryTypeName,
				AllSearch);
		
		SearchDeliveryTypeNo = new ArrayList<String>();
		SearchDeliveryTypeNo.add("2");
		Object[][] DeliveryTypeMstRt02 = M00050DeliveryTypeMstRt.DeliveryTypeMstRt(
				SearchDeliveryTypeNo,
				SearchDeliveryTypeCd,
				SearchDeliveryTypeName,
				AllSearch);
		
		SearchDeliveryTypeNo = new ArrayList<String>();
		SearchDeliveryTypeNo.add("3");
		Object[][] DeliveryTypeMstRt03 = M00050DeliveryTypeMstRt.DeliveryTypeMstRt(
				SearchDeliveryTypeNo,
				SearchDeliveryTypeCd,
				SearchDeliveryTypeName,
				AllSearch);
		
		SearchDeliveryTypeNo = new ArrayList<String>();
		SearchDeliveryTypeNo.add("4");
		Object[][] DeliveryTypeMstRt04 = M00050DeliveryTypeMstRt.DeliveryTypeMstRt(
				SearchDeliveryTypeNo,
				SearchDeliveryTypeCd,
				SearchDeliveryTypeName,
				AllSearch);
		
		SearchDeliveryTypeNo = new ArrayList<String>();
		SearchDeliveryTypeNo.add("5");
		Object[][] DeliveryTypeMstRt05 = M00050DeliveryTypeMstRt.DeliveryTypeMstRt(
				SearchDeliveryTypeNo,
				SearchDeliveryTypeCd,
				SearchDeliveryTypeName,
				AllSearch);
		
		SearchDeliveryType01	= new String[3][DeliveryTypeMstRt01.length+1];
		DeliveryType01 		= new String[3][DeliveryTypeMstRt01.length];
		
		SearchDeliveryType02	= new String[3][DeliveryTypeMstRt02.length+1];
		DeliveryType02 		= new String[3][DeliveryTypeMstRt02.length];
		
		SearchDeliveryType03	= new String[3][DeliveryTypeMstRt03.length+1];
		DeliveryType03 		= new String[3][DeliveryTypeMstRt03.length];
		
		SearchDeliveryType04	= new String[3][DeliveryTypeMstRt04.length+1];
		DeliveryType04 		= new String[3][DeliveryTypeMstRt04.length];
		
		SearchDeliveryType05	= new String[3][DeliveryTypeMstRt05.length+1];
		DeliveryType05 		= new String[3][DeliveryTypeMstRt05.length];
		
		SearchDeliveryType01[0][0] = "未指定";
		SearchDeliveryType01[1][0] = "";
		SearchDeliveryType01[2][0] = "";
		
		SearchDeliveryType02[0][0] = "未指定";
		SearchDeliveryType02[1][0] = "";
		SearchDeliveryType02[2][0] = "";
		
		SearchDeliveryType03[0][0] = "未指定";
		SearchDeliveryType03[1][0] = "";
		SearchDeliveryType03[2][0] = "";
		
		SearchDeliveryType04[0][0] = "未指定";
		SearchDeliveryType04[1][0] = "";
		SearchDeliveryType04[2][0] = "";
		
		SearchDeliveryType05[0][0] = "未指定";
		SearchDeliveryType05[1][0] = "";
		SearchDeliveryType05[2][0] = "";
		
		for(int i=0;i<DeliveryTypeMstRt01.length;i++) {
			SearchDeliveryType01[0][i+1] 	= "" + DeliveryTypeMstRt01[i][1] + ":" + DeliveryTypeMstRt01[i][2];
			DeliveryType01[0][i] 				= "" + DeliveryTypeMstRt01[i][1] + ":" + DeliveryTypeMstRt01[i][2]; 
			
			SearchDeliveryType01[1][i+1] 	= "" + DeliveryTypeMstRt01[i][1];
			DeliveryType01[1][i] 				= "" + DeliveryTypeMstRt01[i][1]; 
			
			SearchDeliveryType01[2][i+1] 	= "" + DeliveryTypeMstRt01[i][2];
			DeliveryType01[2][i] 				= "" + DeliveryTypeMstRt01[i][2]; 
		}
		
		for(int i=0;i<DeliveryTypeMstRt02.length;i++) {
			SearchDeliveryType02[0][i+1] 	= "" + DeliveryTypeMstRt02[i][1] + ":" + DeliveryTypeMstRt02[i][2];
			DeliveryType02[0][i] 				= "" + DeliveryTypeMstRt02[i][1] + ":" + DeliveryTypeMstRt02[i][2]; 
			
			SearchDeliveryType02[1][i+1] 	= "" + DeliveryTypeMstRt02[i][1];
			DeliveryType02[1][i] 				= "" + DeliveryTypeMstRt02[i][1]; 
			
			SearchDeliveryType02[2][i+1] 	= "" + DeliveryTypeMstRt02[i][2];
			DeliveryType02[2][i] 				= "" + DeliveryTypeMstRt02[i][2]; 
		}
		
		for(int i=0;i<DeliveryTypeMstRt03.length;i++) {
			SearchDeliveryType03[0][i+1] 	= "" + DeliveryTypeMstRt03[i][1] + ":" + DeliveryTypeMstRt03[i][2];
			DeliveryType03[0][i] 				= "" + DeliveryTypeMstRt03[i][1] + ":" + DeliveryTypeMstRt03[i][2]; 
			
			SearchDeliveryType03[1][i+1] 	= "" + DeliveryTypeMstRt03[i][1];
			DeliveryType03[1][i] 				= "" + DeliveryTypeMstRt03[i][1]; 
			
			SearchDeliveryType03[2][i+1] 	= "" + DeliveryTypeMstRt03[i][2];
			DeliveryType03[2][i] 				= "" + DeliveryTypeMstRt03[i][2]; 
		}
		
		for(int i=0;i<DeliveryTypeMstRt04.length;i++) {
			SearchDeliveryType04[0][i+1] 	= "" + DeliveryTypeMstRt04[i][1] + ":" + DeliveryTypeMstRt04[i][2];
			DeliveryType04[0][i] 				= "" + DeliveryTypeMstRt04[i][1] + ":" + DeliveryTypeMstRt04[i][2]; 
			
			SearchDeliveryType04[1][i+1] 	= "" + DeliveryTypeMstRt04[i][1];
			DeliveryType04[1][i] 				= "" + DeliveryTypeMstRt04[i][1]; 
			
			SearchDeliveryType04[2][i+1] 	= "" + DeliveryTypeMstRt04[i][2];
			DeliveryType04[2][i] 				= "" + DeliveryTypeMstRt04[i][2]; 
		}
		
		for(int i=0;i<DeliveryTypeMstRt05.length;i++) {
			SearchDeliveryType05[0][i+1] 	= "" + DeliveryTypeMstRt05[i][1] + ":" + DeliveryTypeMstRt05[i][2];
			DeliveryType05[0][i] 				= "" + DeliveryTypeMstRt05[i][1] + ":" + DeliveryTypeMstRt05[i][2]; 
			
			SearchDeliveryType05[1][i+1] 	= "" + DeliveryTypeMstRt05[i][1];
			DeliveryType05[1][i] 				= "" + DeliveryTypeMstRt05[i][1]; 
			
			SearchDeliveryType05[2][i+1] 	= "" + DeliveryTypeMstRt05[i][2];
			DeliveryType05[2][i] 				= "" + DeliveryTypeMstRt05[i][2]; 
		}
	}
	
	private static void ShimeList() {
		ShimeDateList=new String[3][29];							//1日～28日　末日99のリスト
		DeliFeeNorm = new String[3][2];						//運賃請求基準　発請求/着請求
		
		ShimeDateList[0][ 0] =  "1日";
		ShimeDateList[0][ 1] =  "2日";
		ShimeDateList[0][ 2] =  "3日";
		ShimeDateList[0][ 3] =  "4日";
		ShimeDateList[0][ 4] =  "5日";
		ShimeDateList[0][ 5] =  "6日";
		ShimeDateList[0][ 6] =  "7日";
		ShimeDateList[0][ 7] =  "8日";
		ShimeDateList[0][ 8] =  "9日";
		ShimeDateList[0][ 9] = "10日";
		ShimeDateList[0][10] = "11日";
		ShimeDateList[0][11] = "12日";
		ShimeDateList[0][12] = "13日";
		ShimeDateList[0][13] = "14日";
		ShimeDateList[0][14] = "15日";
		ShimeDateList[0][15] = "16日";
		ShimeDateList[0][16] = "17日";
		ShimeDateList[0][17] = "18日";
		ShimeDateList[0][18] = "19日";
		ShimeDateList[0][19] = "20日";
		ShimeDateList[0][20] = "21日";
		ShimeDateList[0][21] = "22日";
		ShimeDateList[0][22] = "23日";
		ShimeDateList[0][23] = "24日";
		ShimeDateList[0][24] = "25日";
		ShimeDateList[0][25] = "26日";
		ShimeDateList[0][26] = "27日";
		ShimeDateList[0][27] = "28日";
		ShimeDateList[0][28] = "月末";
		
		ShimeDateList[1][ 0] =  "1";
		ShimeDateList[1][ 1] =  "2";
		ShimeDateList[1][ 2] =  "3";
		ShimeDateList[1][ 3] =  "4";
		ShimeDateList[1][ 4] =  "5";
		ShimeDateList[1][ 5] =  "6";
		ShimeDateList[1][ 6] =  "7";
		ShimeDateList[1][ 7] =  "8";
		ShimeDateList[1][ 8] =  "9";
		ShimeDateList[1][ 9] = "10";
		ShimeDateList[1][10] = "11";
		ShimeDateList[1][11] = "12";
		ShimeDateList[1][12] = "13";
		ShimeDateList[1][13] = "14";
		ShimeDateList[1][14] = "15";
		ShimeDateList[1][15] = "16";
		ShimeDateList[1][16] = "17";
		ShimeDateList[1][17] = "18";
		ShimeDateList[1][18] = "19";
		ShimeDateList[1][19] = "20";
		ShimeDateList[1][20] = "21";
		ShimeDateList[1][21] = "22";
		ShimeDateList[1][22] = "23";
		ShimeDateList[1][23] = "24";
		ShimeDateList[1][24] = "25";
		ShimeDateList[1][25] = "26";
		ShimeDateList[1][26] = "27";
		ShimeDateList[1][27] = "28";
		ShimeDateList[1][28] = "99";
		
		ShimeDateList[2][ 0] =  "1日";
		ShimeDateList[2][ 1] =  "2日";
		ShimeDateList[2][ 2] =  "3日";
		ShimeDateList[2][ 3] =  "4日";
		ShimeDateList[2][ 4] =  "5日";
		ShimeDateList[2][ 5] =  "6日";
		ShimeDateList[2][ 6] =  "7日";
		ShimeDateList[2][ 7] =  "8日";
		ShimeDateList[2][ 8] =  "9日";
		ShimeDateList[2][ 9] = "10日";
		ShimeDateList[2][10] = "11日";
		ShimeDateList[2][11] = "12日";
		ShimeDateList[2][12] = "13日";
		ShimeDateList[2][13] = "14日";
		ShimeDateList[2][14] = "15日";
		ShimeDateList[2][15] = "16日";
		ShimeDateList[2][16] = "17日";
		ShimeDateList[2][17] = "18日";
		ShimeDateList[2][18] = "19日";
		ShimeDateList[2][19] = "20日";
		ShimeDateList[2][20] = "21日";
		ShimeDateList[2][21] = "22日";
		ShimeDateList[2][22] = "23日";
		ShimeDateList[2][23] = "24日";
		ShimeDateList[2][24] = "25日";
		ShimeDateList[2][25] = "26日";
		ShimeDateList[2][26] = "27日";
		ShimeDateList[2][27] = "28日";
		ShimeDateList[2][28] = "月末";
		
		DeliFeeNorm[0][0] = "0:発日請求";
		DeliFeeNorm[0][1] = "1:着日請求";
		
		DeliFeeNorm[1][0] = "0";
		DeliFeeNorm[1][1] = "1";
		
		DeliFeeNorm[2][0] = "発日請求";
		DeliFeeNorm[2][1] = "着日請求";
	}
	
	private static void AuthorityFG(){
		if("9".equals(A00000Main.LoginUserAuthorityFG)) {
			SerachAuthorityFG = new String[3][5];		//検索用ユーザー権限区分
			AuthorityFG = new String[3][4];			//設定用ユーザー権限区分
			
			SerachAuthorityFG[0][0] = "0:SYS利用者";
			SerachAuthorityFG[1][0] = "0";
			SerachAuthorityFG[2][0] = "システム利用者";		
			AuthorityFG[0][0] = "0:システム利用者";
			AuthorityFG[1][0] = "0";
			AuthorityFG[2][0] = "システム利用者";
			
			SerachAuthorityFG[0][1] = "1:乗務員";
			SerachAuthorityFG[1][1] = "1";
			SerachAuthorityFG[2][1] = "乗務員";		
			AuthorityFG[0][1] = "1:乗務員";
			AuthorityFG[1][1] = "1";
			AuthorityFG[2][1] = "乗務員";
			
			SerachAuthorityFG[0][2] = "2:荷主ユーザー";
			SerachAuthorityFG[1][2] = "2";
			SerachAuthorityFG[2][2] = "荷主ユーザー";		
			AuthorityFG[0][2] = "2:荷主ユーザー";
			AuthorityFG[1][2] = "2";
			AuthorityFG[2][2] = "荷主ユーザー";
			
			SerachAuthorityFG[0][3] = "9:管理ユーザー";
			SerachAuthorityFG[1][3] = "9";
			SerachAuthorityFG[2][3] = "管理ユーザー";		
			AuthorityFG[0][3] = "9:管理ユーザー";
			AuthorityFG[1][3] = "9";
			AuthorityFG[2][3] = "管理ユーザー";
			
			SerachAuthorityFG[0][4] = "未指定";
			SerachAuthorityFG[1][4] = "";
			SerachAuthorityFG[2][4] = "";	
		}else {
			SerachAuthorityFG = new String[3][4];		//検索用ユーザー権限区分
			AuthorityFG = new String[3][3];			//設定用ユーザー権限区分
			
			SerachAuthorityFG[0][0] = "0:SYS利用者";
			SerachAuthorityFG[1][0] = "0";
			SerachAuthorityFG[2][0] = "システム利用者";		
			AuthorityFG[0][0] = "0:システム利用者";
			AuthorityFG[1][0] = "0";
			AuthorityFG[2][0] = "システム利用者";
			
			SerachAuthorityFG[0][1] = "1:乗務員";
			SerachAuthorityFG[1][1] = "1";
			SerachAuthorityFG[2][1] = "乗務員";		
			AuthorityFG[0][1] = "1:乗務員";
			AuthorityFG[1][1] = "1";
			AuthorityFG[2][1] = "乗務員";
			
			SerachAuthorityFG[0][2] = "2:荷主ユーザー";
			SerachAuthorityFG[1][2] = "2";
			SerachAuthorityFG[2][2] = "荷主ユーザー";		
			AuthorityFG[0][2] = "2:荷主ユーザー";
			AuthorityFG[1][2] = "2";
			AuthorityFG[2][2] = "荷主ユーザー";
			
			SerachAuthorityFG[0][3] = "未指定";
			SerachAuthorityFG[1][3] = "";
			SerachAuthorityFG[2][3] = "";	
		}
	}
	
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
		B00100DefaultVariable.ClGpList();
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
		B00100DefaultVariable.ShippingCompanyList();
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
		B00100DefaultVariable.WhList();
		B00101DefaultVariableWarehouse.DefaultVariableWarehouse("0000");
	}
	
	
	//zeusログイン時、郵便番号0000000作る
	public static void Post0000000(){
		String tgt_table = "M0010_PostMst";
		String[][] field_name = new String[5][3];
		String[][] entry_data = new String[1][5];
		String[] judg_field = new String[1];
		String[][] judg_data = new String[1][1];
		String TgtDB = "POST";
		int non_msg_fg = 1;
		
		judg_field[0] = "POST";					//郵便番号
		
		field_name[0][0] = "POST";				//郵便番号
		field_name[1][0] = "PREFECTURES";		//県
		field_name[2][0] = "MUNICI01";			//市区町村
		field_name[3][0] = "MUNICI02";			//町丁目
		field_name[4][0] = "MUNICIPALITY_CD";	//市区町村CD

		field_name[0][1] = "1";	//郵便番号
		field_name[1][1] = "1";	//県
		field_name[2][1] = "1";	//市区町村
		field_name[3][1] = "1";	//町丁目
		field_name[4][1] = "1";	//市区町村CD

		field_name[0][2] = "0";	//郵便番号
		field_name[1][2] = "0";	//県
		field_name[2][2] = "0";	//市区町村
		field_name[3][2] = "0";	//町丁目
		field_name[4][2] = "0";	//市区町村CD
		
		judg_data[0][0] = "0000000";	//郵便番号
		
		entry_data[0][0] = "0000000";	//郵便番号
		entry_data[0][1] = "Yggdrasill";//県
		entry_data[0][2] = "Glitnir";	//市区町村
		entry_data[0][3] = "Walhalla";	//町丁目
		entry_data[0][4] = "00000";	//市区町村CD
		
		A00020InsertUdateSQL.RUN_SQLS_EU(tgt_table, field_name, entry_data, judg_field, judg_data, non_msg_fg,TgtDB);
	}
	//zeusログイン時、強制出荷用届け先0000000作る
	public static void ForcedShipmentCD(){
		String now_dtm = B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[1];
		String[][] SetString = {
				 {"DECD"			,"1","0","ForcedShip"}	//納品先コード
				,{"DepartmentCd"	,"1","0","0000"}		//部署CD
				,{"DEName01"		,"1","0","強制出荷"}	//納品先名1
				,{"DEName02"		,"1","0",""}			//納品先名2
				,{"DEName03"		,"1","0",""}			//納品先名3
				,{"Post"			,"1","0","0000000"}		//納品先郵便
				,{"Add01"			,"1","0","Walhalla　Glitnir　Yggdrasill"}	//納品先住所1
				,{"Add02"			,"1","0",""}		//納品先住所2
				,{"Add03"			,"1","0",""}		//納品先住所3
				,{"Tel"				,"1","0",""}		//納品先電話
				,{"Fax"				,"1","0",""}		//納品先FAX
				,{"Mail"			,"1","0",""}		//納品先MAIL
				,{"Com01"			,"1","0",""}		//コメント1
				,{"Com02"			,"1","0",""}		//コメント2
				,{"Com03"			,"1","0",""}		//コメント3
				,{"PrefecturesCd"	,"1","0","00"}		//JIS県CD2桁
				,{"MunicipalityCd"	,"1","0","00000"}	//JIS市区町村CD5桁
				,{"PTMSCD"			,"1","0",""}		//基幹システム発着地コード
				,{"EntryDate"		,"1","0",now_dtm}	//データ登録日時
				,{"UpdateDate"		,"1","0",now_dtm}	//データ更新日時
				,{"EntryUser"		,"1","0","(" + A00000Main.LoginUserId + ")" + A00000Main.LoginUserName}	//登録者コード
				,{"UpdateUser"		,"1","0","(" + A00000Main.LoginUserId + ")" + A00000Main.LoginUserName}	//更新者コード
				,{"FirstClient"		,"1","0",A00000Main.ClCd}	//登録した荷主CD
				,{"LastClient"		,"1","0",A00000Main.ClCd}	//更新した荷主CD
				,{"DelFg"			,"1","0","0"}	//削除区分
		};
		
		String tgt_table = "KM0040_DELIVERYMST";
		String[][] field_name = new String[SetString.length][3];
		String[][] entry_data = new String[1][SetString.length];
		String[] judg_field = new String[2];
		String[][] judg_data = new String[1][2];
		String TgtDB = "NYANKO";
		int non_msg_fg = 1;
		
		judg_field[0] = "DECD";	//納品先コード
		judg_field[1] = "DepartmentCd";	//部署CD
		
		judg_data[0][0] = "ForcedShip";	//納品先コード
		judg_data[0][1] = "0000";		//部署CD

		for(int i=0;i<SetString.length;i++) {
			field_name[i][0] = SetString[i][0];
			field_name[i][1] = SetString[i][1];
			field_name[i][2] = SetString[i][2];
			entry_data[0][i] = SetString[i][3];
		}
		
		A00020InsertUdateSQL.RUN_SQLS_EU(tgt_table, field_name, entry_data, judg_field, judg_data, non_msg_fg,TgtDB);
	}
}