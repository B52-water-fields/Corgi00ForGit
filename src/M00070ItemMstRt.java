import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class M00070ItemMstRt{
	/*
	コピペ用
	ArrayList<String> SearchClGpCd 				= new ArrayList<String>();	//荷主グループコード
	ArrayList<String> SearchItemCd 				= new ArrayList<String>();	//商品コード
	ArrayList<String> SearchCLItemCd 			= new ArrayList<String>();	//荷主商品コード
	ArrayList<String> SearchItemName 			= new ArrayList<String>();	//商品名
	ArrayList<String> SearchDeliveryTypeCd01 	= new ArrayList<String>();	//運送タイプコード01
	ArrayList<String> SearchDeliveryTypeCd02 	= new ArrayList<String>();	//運送タイプコード02
	ArrayList<String> SearchDeliveryTypeCd03 	= new ArrayList<String>();	//運送タイプコード03
	ArrayList<String> SearchDeliveryTypeCd04 	= new ArrayList<String>();	//運送タイプコード04
	ArrayList<String> SearchDeliveryTypeCd05 	= new ArrayList<String>();	//運送タイプコード05
	ArrayList<String> SearchItemMDNo 			= new ArrayList<String>();	//商品モデル番号（型番）
	ArrayList<String> SearchCategoryCd 			= new ArrayList<String>();	//商品カテゴリCD
	ArrayList<String> SearchCategoryName 		= new ArrayList<String>();	//商品カテゴリ名
	ArrayList<String> SearchItemColorCd 		= new ArrayList<String>();	//商品カラーコード
	ArrayList<String> SearchItemColorName 		= new ArrayList<String>();	//商品カラー名
	ArrayList<String> SearchItemSizeCd 			= new ArrayList<String>();	//商品サイズコード
	ArrayList<String> SearchItemSizeName 		= new ArrayList<String>();	//商品サイズ名
	ArrayList<String> SearchJanCd 				= new ArrayList<String>();	//JANCD
	ArrayList<String> SearchTildFG 				= new ArrayList<String>();	//温度区分
	ArrayList<String> SearchTildName 			= new ArrayList<String>();	//温度区分名
	ArrayList<String> SearchDelFg 				= new ArrayList<String>();	//削除フラグ
	boolean AllSearch = false;
	
	Object[][] ItemMstRt = M00070ItemMstRt.ItemMstRt(
			SearchClGpCd,			//荷主グループコード
			SearchItemCd,			//商品コード
			SearchCLItemCd,			//荷主商品コード
			SearchItemName,			//商品名
			SearchDeliveryTypeCd01,	//運送タイプコード01
			SearchDeliveryTypeCd02,	//運送タイプコード02
			SearchDeliveryTypeCd03,	//運送タイプコード03
			SearchDeliveryTypeCd04,	//運送タイプコード04
			SearchDeliveryTypeCd05,	//運送タイプコード05
			SearchItemMDNo,			//商品モデル番号（型番）
			SearchCategoryCd,		//商品カテゴリCD
			SearchCategoryName,		//商品カテゴリ名
			SearchItemColorCd,		//商品カラーコード
			SearchItemColorName,	//商品カラー名
			SearchItemSizeCd,		//商品サイズコード
			SearchItemSizeName,		//商品サイズ名
			SearchJanCd,			//JANCD
			SearchTildFG,			//温度区分
			SearchTildName,			//温度区分名
			SearchDelFg,			//削除フラグ
			AllSearch);
			
	String GetClGpCd				= (String)ItemMstRt[i][M00070ItemMstRt.ColClGpCd];				//荷主グループコード
	String GetCLGpName01			= (String)ItemMstRt[i][M00070ItemMstRt.ColCLGpName01];			//荷主グループ名1
	String GetItemCd				= (String)ItemMstRt[i][M00070ItemMstRt.ColItemCd];				//商品コード
	String GetCLItemCd				= (String)ItemMstRt[i][M00070ItemMstRt.ColCLItemCd];			//荷主商品コード
	String GetItemName01			= (String)ItemMstRt[i][M00070ItemMstRt.ColItemName01];			//商品名1
	String GetItemName02			= (String)ItemMstRt[i][M00070ItemMstRt.ColItemName02];			//商品名2
	String GetItemName03			= (String)ItemMstRt[i][M00070ItemMstRt.ColItemName03];			//商品名3
	String GetDeliveryTypeCd01		= (String)ItemMstRt[i][M00070ItemMstRt.ColDeliveryTypeCd01];	//運送タイプコード01
	String GetDeliveryTypeName01	= (String)ItemMstRt[i][M00070ItemMstRt.ColDeliveryTypeName01];	//運送タイプ名01
	String GetDeliveryTypeCd02		= (String)ItemMstRt[i][M00070ItemMstRt.ColDeliveryTypeCd02];	//運送タイプコード02
	String GetDeliveryTypeName02	= (String)ItemMstRt[i][M00070ItemMstRt.ColDeliveryTypeName02];	//運送タイプ名02
	String GetDeliveryTypeCd03		= (String)ItemMstRt[i][M00070ItemMstRt.ColDeliveryTypeCd03];	//運送タイプコード03
	String GetDeliveryTypeName03	= (String)ItemMstRt[i][M00070ItemMstRt.ColDeliveryTypeName03];	//運送タイプ名03
	String GetDeliveryTypeCd04		= (String)ItemMstRt[i][M00070ItemMstRt.ColDeliveryTypeCd04];	//運送タイプコード04
	String GetDeliveryTypeName04	= (String)ItemMstRt[i][M00070ItemMstRt.ColDeliveryTypeName04];	//運送タイプ名04
	String GetDeliveryTypeCd05		= (String)ItemMstRt[i][M00070ItemMstRt.ColDeliveryTypeCd05];	//運送タイプコード05
	String GetDeliveryTypeName05	= (String)ItemMstRt[i][M00070ItemMstRt.ColDeliveryTypeName05];	//運送タイプ名05
	String GetPTMSCD				= (String)ItemMstRt[i][M00070ItemMstRt.ColPTMSCD];				//基幹システム商品コード
	int GetCtQty					= (int)ItemMstRt[i][M00070ItemMstRt.ColCtQty];					//カートン入数
	int GetCsQty					= (int)ItemMstRt[i][M00070ItemMstRt.ColCsQty];					//ケース入数
	int GetPlQty					= (int)ItemMstRt[i][M00070ItemMstRt.ColPlQty];					//パレット入数
	String GetJanCd					= (String)ItemMstRt[i][M00070ItemMstRt.ColJanCd];				//JANCD
	String GetCtJan					= (String)ItemMstRt[i][M00070ItemMstRt.ColCtJan];				//カートンバーコード
	String GetCsJan					= (String)ItemMstRt[i][M00070ItemMstRt.ColCsJan];				//ケースバーコード
	String GetPlJan					= (String)ItemMstRt[i][M00070ItemMstRt.ColPlJan];				//パレットバーコード
	String GetCtName				= (String)ItemMstRt[i][M00070ItemMstRt.ColCtName];				//カートン商品名称
	String GetCsName				= (String)ItemMstRt[i][M00070ItemMstRt.ColCsName];				//ケース商品名称
	String GetPlName				= (String)ItemMstRt[i][M00070ItemMstRt.ColPlName];				//パレット商品名称
	String GetUnitName				= (String)ItemMstRt[i][M00070ItemMstRt.ColUnitName];			//商品単位
	String GetCtUnitName			= (String)ItemMstRt[i][M00070ItemMstRt.ColCtUnitName];			//カートン商品単位
	String GetCsUnitName			= (String)ItemMstRt[i][M00070ItemMstRt.ColCsUnitName];			//ケース商品単位
	String GetPlUnitName			= (String)ItemMstRt[i][M00070ItemMstRt.ColPlUnitName];			//パレット商品単位
	float GetItemWeight				= (float)ItemMstRt[i][M00070ItemMstRt.ColItemWeight];			//商品重量
	float GetCtWeight				= (float)ItemMstRt[i][M00070ItemMstRt.ColCtWeight];				//カートン重量
	float GetCsWeight				= (float)ItemMstRt[i][M00070ItemMstRt.ColCsWeight];				//ケース重量
	float GetPlWeight				= (float)ItemMstRt[i][M00070ItemMstRt.ColPlWeight];				//パレット重量
	float GetItemSize				= (float)ItemMstRt[i][M00070ItemMstRt.ColItemSize];				//商品サイズ
	float GetCtSize					= (float)ItemMstRt[i][M00070ItemMstRt.ColCtSize];				//カートンサイズ
	float GetCsSize					= (float)ItemMstRt[i][M00070ItemMstRt.ColCsSize];				//ケースサイズ
	float GetPlSize					= (float)ItemMstRt[i][M00070ItemMstRt.ColPlSize];				//パレットサイズ
	String GetRecomendLoc			= (String)ItemMstRt[i][M00070ItemMstRt.ColRecomendLoc];			//推奨ロケ
	String GetItemMDNo				= (String)ItemMstRt[i][M00070ItemMstRt.ColItemMDNo];			//商品モデル番号（型番）
	String GetCategoryCd			= (String)ItemMstRt[i][M00070ItemMstRt.ColCategoryCd];			//商品カテゴリCD
	String GetCategoryName			= (String)ItemMstRt[i][M00070ItemMstRt.ColCategoryName];		//商品カテゴリ名
	String GetItemColorCd			= (String)ItemMstRt[i][M00070ItemMstRt.ColItemColorCd];			//商品カラーコード
	String GetItemColorName			= (String)ItemMstRt[i][M00070ItemMstRt.ColItemColorName];		//商品カラー名
	String GetItemSizeCd			= (String)ItemMstRt[i][M00070ItemMstRt.ColItemSizeCd];			//商品サイズコード
	String GetItemSizeName			= (String)ItemMstRt[i][M00070ItemMstRt.ColItemSizeName];		//商品サイズ名
	String GetCom01					= (String)ItemMstRt[i][M00070ItemMstRt.ColCom01];				//コメント1
	String GetCom02					= (String)ItemMstRt[i][M00070ItemMstRt.ColCom02];				//コメント2
	String GetCom03					= (String)ItemMstRt[i][M00070ItemMstRt.ColCom03];				//コメント3
	String GetTildFG				= (String)ItemMstRt[i][M00070ItemMstRt.ColTildFG];				//温度区分
	String GetTildName				= (String)ItemMstRt[i][M00070ItemMstRt.ColTildName];			//温度区分名
	String GetPictPass01			= (String)ItemMstRt[i][M00070ItemMstRt.ColPictPass01];			//画像パス01
	String GetPictPass02			= (String)ItemMstRt[i][M00070ItemMstRt.ColPictPass02];			//画像パス02
	String GetPictPass03			= (String)ItemMstRt[i][M00070ItemMstRt.ColPictPass03];			//画像パス03
	String GetPictPass04			= (String)ItemMstRt[i][M00070ItemMstRt.ColPictPass04];			//画像パス04
	String GetPictPass05			= (String)ItemMstRt[i][M00070ItemMstRt.ColPictPass05];			//画像パス05
	int GetExpDateHowLong			= (int)ItemMstRt[i][M00070ItemMstRt.ColExpDateHowLong];			//賞味期限日数
	String GetEntryDate				= (String)ItemMstRt[i][M00070ItemMstRt.ColEntryDate];			//データ登録日時
	String GetUpdateDate			= (String)ItemMstRt[i][M00070ItemMstRt.ColUpdateDate];			//データ更新日時
	String GetEntryUser				= (String)ItemMstRt[i][M00070ItemMstRt.ColEntryUser];			//登録者コード
	String GetUpdateUser			= (String)ItemMstRt[i][M00070ItemMstRt.ColUpdateUser];			//更新者コード
	int GetDelFg					= (int)ItemMstRt[i][M00070ItemMstRt.ColDelFg];					//削除フラグ
	
	*/
	//戻り値カラム
	static final  int ColClGpCd				= (int) 0;	//荷主グループコード
	static final  int ColCLGpName01			= (int) 1;	//荷主グループ名1
	static final  int ColItemCd				= (int) 2;	//商品コード
	static final  int ColCLItemCd				= (int) 3;	//荷主商品コード
	static final  int ColItemName01			= (int) 4;	//商品名1
	static final  int ColItemName02			= (int) 5;	//商品名2
	static final  int ColItemName03			= (int) 6;	//商品名3
	static final  int ColDeliveryTypeCd01	= (int) 7;	//運送タイプコード01
	static final  int ColDeliveryTypeName01	= (int) 8;	//運送タイプ名01
	static final  int ColDeliveryTypeCd02	= (int) 9;	//運送タイプコード02
	static final  int ColDeliveryTypeName02	= (int)10;	//運送タイプ名02
	static final  int ColDeliveryTypeCd03	= (int)11;	//運送タイプコード03
	static final  int ColDeliveryTypeName03	= (int)12;	//運送タイプ名03
	static final  int ColDeliveryTypeCd04	= (int)13;	//運送タイプコード04
	static final  int ColDeliveryTypeName04	= (int)14;	//運送タイプ名04
	static final  int ColDeliveryTypeCd05	= (int)15;	//運送タイプコード05
	static final  int ColDeliveryTypeName05	= (int)16;	//運送タイプ名05
	static final  int ColPTMSCD				= (int)17;	//基幹システム商品コード
	static final  int ColCtQty					= (int)18;	//カートン入数
	static final  int ColCsQty					= (int)19;	//ケース入数
	static final  int ColPlQty					= (int)20;	//パレット入数
	static final  int ColJanCd					= (int)21;	//JANCD
	static final  int ColCtJan					= (int)22;	//カートンバーコード
	static final  int ColCsJan					= (int)23;	//ケースバーコード
	static final  int ColPlJan					= (int)24;	//パレットバーコード
	static final  int ColCtName				= (int)25;	//カートン商品名称
	static final  int ColCsName				= (int)26;	//ケース商品名称
	static final  int ColPlName				= (int)27;	//パレット商品名称
	static final  int ColUnitName				= (int)28;	//商品単位
	static final  int ColCtUnitName			= (int)29;	//カートン商品単位
	static final  int ColCsUnitName			= (int)30;	//ケース商品単位
	static final  int ColPlUnitName			= (int)31;	//パレット商品単位
	static final  int ColItemWeight			= (int)32;	//商品重量
	static final  int ColCtWeight				= (int)33;	//カートン重量
	static final  int ColCsWeight				= (int)34;	//ケース重量
	static final  int ColPlWeight				= (int)35;	//パレット重量
	static final  int ColItemSize				= (int)36;	//商品サイズ
	static final  int ColCtSize				= (int)37;	//カートンサイズ
	static final  int ColCsSize				= (int)38;	//ケースサイズ
	static final  int ColPlSize				= (int)39;	//パレットサイズ
	static final  int ColRecomendLoc			= (int)40;	//推奨ロケ
	static final  int ColItemMDNo				= (int)41;	//商品モデル番号（型番）
	static final  int ColCategoryCd			= (int)42;	//商品カテゴリCD
	static final  int ColCategoryName			= (int)43;	//商品カテゴリ名
	static final  int ColItemColorCd			= (int)44;	//商品カラーコード
	static final  int ColItemColorName		= (int)45;	//商品カラー名
	static final  int ColItemSizeCd			= (int)46;	//商品サイズコード
	static final  int ColItemSizeName			= (int)47;	//商品サイズ名
	static final  int ColCom01					= (int)48;	//コメント1
	static final  int ColCom02					= (int)49;	//コメント2
	static final  int ColCom03					= (int)50;	//コメント3
	static final  int ColTildFG				= (int)51;	//温度区分
	static final  int ColTildName				= (int)52;	//温度区分名
	static final  int ColPictPass01			= (int)53;	//画像パス01
	static final  int ColPictPass02			= (int)54;	//画像パス02
	static final  int ColPictPass03			= (int)55;	//画像パス03
	static final  int ColPictPass04			= (int)56;	//画像パス04
	static final  int ColPictPass05			= (int)57;	//画像パス05
	static final  int ColExpDateHowLong		= (int)58;	//賞味期限日数
	static final  int ColEntryDate			= (int)59;	//データ登録日時
	static final  int ColUpdateDate			= (int)60;	//データ更新日時
	static final  int ColEntryUser			= (int)61;	//登録者コード
	static final  int ColUpdateUser			= (int)62;	//更新者コード
	static final  int ColDelFg				= (int)63;	//削除フラグ

	public static Object[][] RtSettingItemMstRt(){
		Object[][] RtSettingItemMstRt = {
				 {"ClGpCd"					,ColClGpCd					,"String"	,"荷主グループコード"}
				,{"CLGpName01"				,ColCLGpName01			,"String"	,"荷主グループ名1"}
				,{"ItemCd"					,ColItemCd					,"String"	,"商品コード"}
				,{"CLItemCd"				,ColCLItemCd				,"String"	,"荷主商品コード"}
				,{"ItemName01"				,ColItemName01			,"String"	,"商品名1"}
				,{"ItemName02"				,ColItemName02			,"String"	,"商品名2"}
				,{"ItemName03"				,ColItemName03			,"String"	,"商品名3"}
				,{"DeliveryTypeCd01"		,ColDeliveryTypeCd01		,"String"	,"運送タイプコード01"}
				,{"DeliveryTypeName01"		,ColDeliveryTypeName01	,"String"	,"運送タイプ名01"}
				,{"DeliveryTypeCd02"		,ColDeliveryTypeCd02		,"String"	,"運送タイプコード02"}
				,{"DeliveryTypeName02"		,ColDeliveryTypeName02	,"String"	,"運送タイプ名02"}
				,{"DeliveryTypeCd03"		,ColDeliveryTypeCd03		,"String"	,"運送タイプコード03"}
				,{"DeliveryTypeName03"		,ColDeliveryTypeName03	,"String"	,"運送タイプ名03"}
				,{"DeliveryTypeCd04"		,ColDeliveryTypeCd04		,"String"	,"運送タイプコード04"}
				,{"DeliveryTypeName04"		,ColDeliveryTypeName04	,"String"	,"運送タイプ名04"}
				,{"DeliveryTypeCd05"		,ColDeliveryTypeCd05		,"String"	,"運送タイプコード05"}
				,{"DeliveryTypeName05"		,ColDeliveryTypeName05	,"String"	,"運送タイプ名05"}
				,{"PTMSCD"					,ColPTMSCD					,"String"	,"基幹システム商品コード"}
				,{"CtQty"					,ColCtQty					,"int"		,"カートン入数"}
				,{"CsQty"					,ColCsQty					,"int"		,"ケース入数"}
				,{"PlQty"					,ColPlQty					,"int"		,"パレット入数"}
				,{"JanCd"					,ColJanCd					,"String"	,"JANCD"}
				,{"CtJan"					,ColCtJan					,"String"	,"カートンバーコード"}
				,{"CsJan"					,ColCsJan					,"String"	,"ケースバーコード"}
				,{"PlJan"					,ColPlJan					,"String"	,"パレットバーコード"}
				,{"CtName"					,ColCtName					,"String"	,"カートン商品名称"}
				,{"CsName"					,ColCsName					,"String"	,"ケース商品名称"}
				,{"PlName"					,ColPlName					,"String"	,"パレット商品名称"}
				,{"UnitName"				,ColUnitName				,"String"	,"商品単位"}
				,{"CtUnitName"				,ColCtUnitName			,"String"	,"カートン商品単位"}
				,{"CsUnitName"				,ColCsUnitName			,"String"	,"ケース商品単位"}
				,{"PlUnitName"				,ColPlUnitName			,"String"	,"パレット商品単位"}
				,{"ItemWeight"				,ColItemWeight			,"float"	,"商品重量"}
				,{"CtWeight"				,ColCtWeight				,"float"	,"カートン重量"}
				,{"CsWeight"				,ColCsWeight				,"float"	,"ケース重量"}
				,{"PlWeight"				,ColPlWeight				,"float"	,"パレット重量"}
				,{"ItemSize"				,ColItemSize				,"float"	,"商品サイズ"}
				,{"CtSize"					,ColCtSize					,"float"	,"カートンサイズ"}
				,{"CsSize"					,ColCsSize					,"float"	,"ケースサイズ"}
				,{"PlSize"					,ColPlSize					,"float"	,"パレットサイズ"}
				,{"RecomendLoc"				,ColRecomendLoc			,"String"	,"推奨ロケ"}
				,{"ItemMDNo"				,ColItemMDNo				,"String"	,"商品モデル番号（型番）"}
				,{"CategoryCd"				,ColCategoryCd			,"String"	,"商品カテゴリCD"}
				,{"CategoryName"			,ColCategoryName			,"String"	,"商品カテゴリ名"}
				,{"ItemColorCd"				,ColItemColorCd			,"String"	,"商品カラーコード"}
				,{"ItemColorName"			,ColItemColorName			,"String"	,"商品カラー名"}
				,{"ItemSizeCd"				,ColItemSizeCd			,"String"	,"商品サイズコード"}
				,{"ItemSizeName"			,ColItemSizeName			,"String"	,"商品サイズ名"}
				,{"Com01"					,ColCom01					,"String"	,"コメント1"}
				,{"Com02"					,ColCom02					,"String"	,"コメント2"}
				,{"Com03"					,ColCom03					,"String"	,"コメント3"}
				,{"TildFG"					,ColTildFG					,"String"	,"温度区分"}
				,{"TildName"				,ColTildName				,"String"	,"温度区分名"}
				,{"PictPass01"				,ColPictPass01			,"String"	,"画像パス01"}
				,{"PictPass02"				,ColPictPass02			,"String"	,"画像パス02"}
				,{"PictPass03"				,ColPictPass03			,"String"	,"画像パス03"}
				,{"PictPass04"				,ColPictPass04			,"String"	,"画像パス04"}
				,{"PictPass05"				,ColPictPass05			,"String"	,"画像パス05"}
				,{"ExpDateHowLong"			,ColExpDateHowLong		,"int"		,"賞味期限日数"}
				,{"EntryDate"				,ColEntryDate				,"String"	,"データ登録日時"}
				,{"UpdateDate"				,ColUpdateDate			,"String"	,"データ更新日時"}
				,{"EntryUser"				,ColEntryUser				,"String"	,"登録者コード"}
				,{"UpdateUser"				,ColUpdateUser			,"String"	,"更新者コード"}
				,{"DelFg"					,ColDelFg					,"int"		,"削除フラグ"}
				};
		
		return RtSettingItemMstRt;
	}
	public static Object[][] ItemMstRt(
			ArrayList<String> SearchClGpCd,				//荷主グループコード
			ArrayList<String> SearchItemCd,				//商品コード
			ArrayList<String> SearchCLItemCd,			//荷主商品コード
			ArrayList<String> SearchItemName,			//商品名
			ArrayList<String> SearchDeliveryTypeCd01,	//運送タイプコード01
			ArrayList<String> SearchDeliveryTypeCd02,	//運送タイプコード02
			ArrayList<String> SearchDeliveryTypeCd03,	//運送タイプコード03
			ArrayList<String> SearchDeliveryTypeCd04,	//運送タイプコード04
			ArrayList<String> SearchDeliveryTypeCd05,	//運送タイプコード05
			ArrayList<String> SearchItemMDNo,			//商品モデル番号（型番）
			ArrayList<String> SearchCategoryCd,			//商品カテゴリCD
			ArrayList<String> SearchCategoryName,		//商品カテゴリ名
			ArrayList<String> SearchItemColorCd,		//商品カラーコード
			ArrayList<String> SearchItemColorName,		//商品カラー名
			ArrayList<String> SearchItemSizeCd,			//商品サイズコード
			ArrayList<String> SearchItemSizeName,		//商品サイズ名
			ArrayList<String> SearchJanCd,				//JANCD
			ArrayList<String> SearchTildFG,				//温度区分
			ArrayList<String> SearchTildName,			//温度区分名
			ArrayList<String> SearchDelFg,				//削除フラグ
			boolean AllSearch){
		
		SearchClGpCd			= B00150ArrayListControl.ArryListStringUniqueList(SearchClGpCd);
		SearchItemCd			= B00150ArrayListControl.ArryListStringUniqueList(SearchItemCd);
		SearchCLItemCd			= B00150ArrayListControl.ArryListStringUniqueList(SearchCLItemCd);
		SearchItemName			= B00150ArrayListControl.ArryListStringUniqueList(SearchItemName);
		SearchDeliveryTypeCd01	= B00150ArrayListControl.ArryListStringUniqueList(SearchDeliveryTypeCd01);
		SearchDeliveryTypeCd02	= B00150ArrayListControl.ArryListStringUniqueList(SearchDeliveryTypeCd02);
		SearchDeliveryTypeCd03	= B00150ArrayListControl.ArryListStringUniqueList(SearchDeliveryTypeCd03);
		SearchDeliveryTypeCd04	= B00150ArrayListControl.ArryListStringUniqueList(SearchDeliveryTypeCd04);
		SearchDeliveryTypeCd05	= B00150ArrayListControl.ArryListStringUniqueList(SearchDeliveryTypeCd05);
		SearchItemMDNo			= B00150ArrayListControl.ArryListStringUniqueList(SearchItemMDNo);
		SearchCategoryCd		= B00150ArrayListControl.ArryListStringUniqueList(SearchCategoryCd);
		SearchCategoryName		= B00150ArrayListControl.ArryListStringUniqueList(SearchCategoryName);
		SearchItemColorCd		= B00150ArrayListControl.ArryListStringUniqueList(SearchItemColorCd);
		SearchItemColorName		= B00150ArrayListControl.ArryListStringUniqueList(SearchItemColorName);
		SearchItemSizeCd		= B00150ArrayListControl.ArryListStringUniqueList(SearchItemSizeCd);
		SearchItemSizeName		= B00150ArrayListControl.ArryListStringUniqueList(SearchItemSizeName);
		SearchJanCd				= B00150ArrayListControl.ArryListStringUniqueList(SearchJanCd);
		SearchTildFG			= B00150ArrayListControl.ArryListStringUniqueList(SearchTildFG);
		SearchTildName			= B00150ArrayListControl.ArryListStringUniqueList(SearchTildName);
		SearchDelFg				= B00150ArrayListControl.ArryListStringUniqueList(SearchDelFg);
		
		Object[][] rt = new Object[0][0];
		boolean SearchKick = false;
		if(AllSearch) {SearchKick = true;}
		
		String sql = " select \n"
				+"(KM0060_ITEMMST.ClGpCd) as ClGpCd,\n"						//荷主グループコード
				+"(KM0031_CLIENT_GROUP.CLGpName01) as CLGpName01,\n"		//荷主グループ名1
				+"(KM0060_ITEMMST.ItemCd) as ItemCd,\n"						//商品コード
				+"(KM0060_ITEMMST.CLItemCd) as CLItemCd,\n"					//荷主商品コード
				+"(KM0060_ITEMMST.ItemName01) as ItemName01,\n"				//商品名1
				+"(KM0060_ITEMMST.ItemName02) as ItemName02,\n"				//商品名2
				+"(KM0060_ITEMMST.ItemName03) as ItemName03,\n"				//商品名3
				+"(KM0060_ITEMMST.DeliveryTypeCd) as DeliveryTypeCd01,\n"	//運送タイプコード01
				+"(DT01.DeliveryTypeName) as DeliveryTypeName01,\n"			//運送タイプ名01
				+"(KM0060_ITEMMST.DeliveryTypeCd02) as DeliveryTypeCd02,\n"	//運送タイプコード02
				+"(DT02.DeliveryTypeName) as DeliveryTypeName02,\n"			//運送タイプ名02
				+"(KM0060_ITEMMST.DeliveryTypeCd03) as DeliveryTypeCd03,\n"	//運送タイプコード03
				+"(DT03.DeliveryTypeName) as DeliveryTypeName03,\n"			//運送タイプ名03
				+"(KM0060_ITEMMST.DeliveryTypeCd04) as DeliveryTypeCd04,\n"	//運送タイプコード04
				+"(DT04.DeliveryTypeName) as DeliveryTypeName04,\n"			//運送タイプ名04
				+"(KM0060_ITEMMST.DeliveryTypeCd05) as DeliveryTypeCd05,\n"	//運送タイプコード05
				+"(DT05.DeliveryTypeName) as DeliveryTypeName05,\n"			//運送タイプ名05
				+"(KM0060_ITEMMST.PTMSCD) as PTMSCD,\n"						//基幹システム商品コード
				+"(KM0061_ITEMMSTSUB.CtQty) as CtQty,\n"					//カートン入数
				+"(KM0061_ITEMMSTSUB.CsQty) as CsQty,\n"					//ケース入数
				+"(KM0061_ITEMMSTSUB.PlQty) as PlQty,\n"					//パレット入数
				+"(KM0060_ITEMMST.JanCd) as JanCd,\n"						//JANCD
				+"(KM0061_ITEMMSTSUB.CtJan) as CtJan,\n"					//カートンバーコード
				+"(KM0061_ITEMMSTSUB.CsJan) as CsJan,\n"					//ケースバーコード
				+"(KM0061_ITEMMSTSUB.PlJan) as PlJan,\n"					//パレットバーコード
				+"(KM0061_ITEMMSTSUB.CtName) as CtName,\n"					//カートン商品名称
				+"(KM0061_ITEMMSTSUB.CsName) as CsName,\n"					//ケース商品名称
				+"(KM0061_ITEMMSTSUB.PlName) as PlName,\n"					//パレット商品名称
				+"(KM0060_ITEMMST.UnitName) as UnitName,\n"					//商品単位
				+"(KM0061_ITEMMSTSUB.CtUnitName) as CtUnitName,\n"			//カートン商品単位
				+"(KM0061_ITEMMSTSUB.CsUnitName) as CsUnitName,\n"			//ケース商品単位
				+"(KM0061_ITEMMSTSUB.PlUnitName) as PlUnitName,\n"			//パレット商品単位
				+"(KM0060_ITEMMST.ItemWeight) as ItemWeight,\n"				//商品重量
				+"(KM0061_ITEMMSTSUB.CtWeight) as CtWeight,\n"				//カートン重量
				+"(KM0061_ITEMMSTSUB.CsWeight) as CsWeight,\n"				//ケース重量
				+"(KM0061_ITEMMSTSUB.PlWeight) as PlWeight,\n"				//パレット重量
				+"(KM0060_ITEMMST.ItemSize) as ItemSize,\n"					//商品サイズ
				+"(KM0061_ITEMMSTSUB.CtSize) as CtSize,\n"					//カートンサイズ
				+"(KM0061_ITEMMSTSUB.CsSize) as CsSize,\n"					//ケースサイズ
				+"(KM0061_ITEMMSTSUB.PlSize) as PlSize,\n"					//パレットサイズ
				+"(KM0061_ITEMMSTSUB.RecomendLoc) as RecomendLoc,\n"		//推奨ロケ
				+"(KM0061_ITEMMSTSUB.ItemMDNo) as ItemMDNo,\n"				//商品モデル番号（型番）
				+"(KM0061_ITEMMSTSUB.CategoryCd) as CategoryCd,\n"			//商品カテゴリCD
				+"(KM0061_ITEMMSTSUB.CategoryName) as CategoryName,\n"		//商品カテゴリ名
				+"(KM0061_ITEMMSTSUB.ItemColorCd) as ItemColorCd,\n"		//商品カラーコード
				+"(KM0061_ITEMMSTSUB.ItemColorName) as ItemColorName,\n"	//商品カラー名
				+"(KM0061_ITEMMSTSUB.ItemSizeCd) as ItemSizeCd,\n"			//商品サイズコード
				+"(KM0061_ITEMMSTSUB.ItemSizeName) as ItemSizeName,\n"		//商品サイズ名
				+"(KM0061_ITEMMSTSUB.Com01) as Com01,\n"					//コメント1
				+"(KM0061_ITEMMSTSUB.Com02) as Com02,\n"					//コメント2
				+"(KM0061_ITEMMSTSUB.Com03) as Com03,\n"					//コメント3
				+"(KM0061_ITEMMSTSUB.TildFG) as TildFG,\n"					//温度区分
				+"(KM0061_ITEMMSTSUB.TildName) as TildName,\n"				//温度区分名
				+"(KM0061_ITEMMSTSUB.PictPass01) as PictPass01,\n"			//画像パス01
				+"(KM0061_ITEMMSTSUB.PictPass02) as PictPass02,\n"			//画像パス02
				+"(KM0061_ITEMMSTSUB.PictPass03) as PictPass03,\n"			//画像パス03
				+"(KM0061_ITEMMSTSUB.PictPass04) as PictPass04,\n"			//画像パス04
				+"(KM0061_ITEMMSTSUB.PictPass05) as PictPass05,\n"			//画像パス05
				+"(KM0061_ITEMMSTSUB.ExpDateHowLong) as ExpDateHowLong,\n"	//賞味期限日数
				+"(KM0060_ITEMMST.EntryDate) as EntryDate,\n"				//データ登録日時
				+"(KM0060_ITEMMST.UpdateDate) as UpdateDate,\n"				//データ更新日時
				+"(KM0060_ITEMMST.EntryUser) as EntryUser,\n"				//登録者コード
				+"(KM0060_ITEMMST.UpdateUser) as UpdateUser,\n"				//更新者コード
				+"(KM0060_ITEMMST.DelFg) as DelFg\n"						//削除フラグ
				
				
				+ " from "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0060_ITEMMST \n"
				+ " left outer join "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0061_ITEMMSTSUB \n"
				+ " on("
				+ " KM0060_ITEMMST.ClGpCd = KM0061_ITEMMSTSUB.ClGpCd and KM0060_ITEMMST.ItemCd = KM0061_ITEMMSTSUB.ItemCd"
				+ ")\n"
				+ " left outer join "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP"
				+ " on("
				+ " KM0060_ITEMMST.ClGpCd = KM0031_CLIENT_GROUP.ClGpCD"
				+ ")\n"
				+ " left outer join "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0050_DELIVERY_TYPEMST as DT01"
				+ " on("
				+ "1 = DT01.DeliveryTypeNo and KM0060_ITEMMST.DeliveryTypeCd = DT01.DeliveryTypeCd"
				+ ")\n"
				+ " left outer join "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0050_DELIVERY_TYPEMST as DT02"
				+ " on("
				+ "2 = DT02.DeliveryTypeNo and KM0060_ITEMMST.DeliveryTypeCd02 = DT02.DeliveryTypeCd"
				+ ")\n"
				+ " left outer join "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0050_DELIVERY_TYPEMST as DT03"
				+ " on("
				+ "3 = DT03.DeliveryTypeNo and KM0060_ITEMMST.DeliveryTypeCd03 = DT03.DeliveryTypeCd"
				+ ")\n"
				+ " left outer join "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0050_DELIVERY_TYPEMST as DT04"
				+ " on("
				+ "4 = DT04.DeliveryTypeNo and KM0060_ITEMMST.DeliveryTypeCd04 = DT04.DeliveryTypeCd"
				+ ")\n"
				+ " left outer join "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0050_DELIVERY_TYPEMST as DT05"
				+ " on("
				+ "5 = DT05.DeliveryTypeNo and KM0060_ITEMMST.DeliveryTypeCd05 = DT05.DeliveryTypeCd"
				+ ")\n"
				+ "where 1=1\n"
				+ "";
		if(null!=SearchClGpCd&&0<SearchClGpCd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchClGpCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KM0060_ITEMMST.ClGpCd = ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchItemCd&&0<SearchItemCd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchItemCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KM0060_ITEMMST.ItemCd = ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchCLItemCd&&0<SearchCLItemCd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchCLItemCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KM0060_ITEMMST.CLItemCd = ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchItemName&&0<SearchItemName.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchItemName.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KM0060_ITEMMST.ItemName01 like ?";
				sql = sql + " or KM0060_ITEMMST.ItemName02 like ?";
				sql = sql + " or KM0060_ITEMMST.ItemName03 like ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchDeliveryTypeCd01&&0<SearchDeliveryTypeCd01.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchDeliveryTypeCd01.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KM0060_ITEMMST.DeliveryTypeCd = ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchDeliveryTypeCd02&&0<SearchDeliveryTypeCd02.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchDeliveryTypeCd02.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KM0060_ITEMMST.DeliveryTypeCd02 = ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchDeliveryTypeCd03&&0<SearchDeliveryTypeCd03.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchDeliveryTypeCd03.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KM0060_ITEMMST.DeliveryTypeCd03 = ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchDeliveryTypeCd04&&0<SearchDeliveryTypeCd04.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchDeliveryTypeCd04.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KM0060_ITEMMST.DeliveryTypeCd04 = ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchDeliveryTypeCd05&&0<SearchDeliveryTypeCd05.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchDeliveryTypeCd05.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KM0060_ITEMMST.DeliveryTypeCd05 = ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchJanCd&&0<SearchJanCd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchJanCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KM0060_ITEMMST.JanCd = ?";
				sql = sql + " or KM0061_ITEMMSTSUB.CtJan = ?";
				sql = sql + " or KM0061_ITEMMSTSUB.CsJan = ?";
				sql = sql + " or KM0061_ITEMMSTSUB.PlJan = ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchItemMDNo&&0<SearchItemMDNo.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchItemMDNo.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KM0061_ITEMMSTSUB.ItemMDNo = ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchCategoryCd&&0<SearchCategoryCd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchCategoryCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KM0061_ITEMMSTSUB.CategoryCd = ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchCategoryName&&0<SearchCategoryName.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchCategoryName.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KM0061_ITEMMSTSUB.CategoryName like ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchItemColorCd&&0<SearchItemColorCd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchItemColorCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + "KM0061_ITEMMSTSUB.ItemColorCd  = ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchItemColorName&&0<SearchItemColorName.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchItemColorName.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KM0061_ITEMMSTSUB.ItemColorName like ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchItemSizeCd&&0<SearchItemSizeCd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchItemSizeCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KM0061_ITEMMSTSUB.ItemSizeCd = ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchItemSizeName&&0<SearchItemSizeName.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchItemSizeName.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KM0061_ITEMMSTSUB.ItemSizeName like ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchTildFG&&0<SearchTildFG.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchTildFG.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KM0061_ITEMMSTSUB.TildFG = ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchTildName&&0<SearchTildName.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchTildName.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KM0061_ITEMMSTSUB.TildName like ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchDelFg&&0<SearchDelFg.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchDelFg.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KM0060_ITEMMST.DelFg = ?";
			}
			sql = sql + ")";
		}

		
		sql = sql+" order by KM0060_ITEMMST.ClGpCd,KM0060_ITEMMST.ItemCd\n";
		if(SearchKick) {
			A00010DbConnect.DB_CONN("NYANKO");
			ResultSet rset01 = null;
			PreparedStatement stmt01 = null;
			try {
				stmt01 = A00010DbConnect.conn.prepareStatement(sql);
				int StmtCount = 0;
				if(null!=SearchClGpCd&&0<SearchClGpCd.size()){
					for(int i=0;i<SearchClGpCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchClGpCd.get(i)+"");
					}
				}
				if(null!=SearchItemCd&&0<SearchItemCd.size()){
					for(int i=0;i<SearchItemCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchItemCd.get(i)+"");
					}
				}
				if(null!=SearchCLItemCd&&0<SearchCLItemCd.size()){
					for(int i=0;i<SearchCLItemCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchCLItemCd.get(i)+"");
					}
				}
				if(null!=SearchItemName&&0<SearchItemName.size()){
					for(int i=0;i<SearchItemName.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchItemName.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchItemName.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchItemName.get(i)+"%");
					}
				}
				if(null!=SearchDeliveryTypeCd01&&0<SearchDeliveryTypeCd01.size()){
					for(int i=0;i<SearchDeliveryTypeCd01.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchDeliveryTypeCd01.get(i)+"");
					}
				}
				if(null!=SearchDeliveryTypeCd02&&0<SearchDeliveryTypeCd02.size()){
					for(int i=0;i<SearchDeliveryTypeCd02.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchDeliveryTypeCd02.get(i)+"");
					}
				}
				if(null!=SearchDeliveryTypeCd03&&0<SearchDeliveryTypeCd03.size()){
					for(int i=0;i<SearchDeliveryTypeCd03.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchDeliveryTypeCd03.get(i)+"");
					}
				}
				if(null!=SearchDeliveryTypeCd04&&0<SearchDeliveryTypeCd04.size()){
					for(int i=0;i<SearchDeliveryTypeCd04.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchDeliveryTypeCd04.get(i)+"");
					}
				}
				if(null!=SearchDeliveryTypeCd05&&0<SearchDeliveryTypeCd05.size()){
					for(int i=0;i<SearchDeliveryTypeCd05.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchDeliveryTypeCd05.get(i)+"");
					}
				}
				if(null!=SearchJanCd&&0<SearchJanCd.size()){
					for(int i=0;i<SearchJanCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchJanCd.get(i)+"");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchJanCd.get(i)+"");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchJanCd.get(i)+"");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchJanCd.get(i)+"");
					}
				}
				if(null!=SearchItemMDNo&&0<SearchItemMDNo.size()){
					for(int i=0;i<SearchItemMDNo.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchItemMDNo.get(i)+"");
					}
				}
				if(null!=SearchCategoryCd&&0<SearchCategoryCd.size()){
					for(int i=0;i<SearchCategoryCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchCategoryCd.get(i)+"");
					}
				}
				if(null!=SearchCategoryName&&0<SearchCategoryName.size()){
					for(int i=0;i<SearchCategoryName.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchCategoryName.get(i)+"%");
					}
				}
				if(null!=SearchItemColorCd&&0<SearchItemColorCd.size()){
					for(int i=0;i<SearchItemColorCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchItemColorCd.get(i)+"");
					}
				}
				if(null!=SearchItemColorName&&0<SearchItemColorName.size()){
					for(int i=0;i<SearchItemColorName.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchItemColorName.get(i)+"%");
					}
				}
				if(null!=SearchItemSizeCd&&0<SearchItemSizeCd.size()){
					for(int i=0;i<SearchItemSizeCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchItemSizeCd.get(i)+"");
					}
				}
				if(null!=SearchItemSizeName&&0<SearchItemSizeName.size()){
					for(int i=0;i<SearchItemSizeName.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchItemSizeName.get(i)+"%");
					}
				}
				if(null!=SearchTildFG&&0<SearchTildFG.size()){
					for(int i=0;i<SearchTildFG.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchTildFG.get(i)+"");
					}
				}
				if(null!=SearchTildName&&0<SearchTildName.size()){
					for(int i=0;i<SearchTildName.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchTildName.get(i)+"%");
					}
				}
				if(null!=SearchDelFg&&0<SearchDelFg.size()){
					for(int i=0;i<SearchDelFg.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchDelFg.get(i)+"");
					}
				}
				rset01 = stmt01.executeQuery();
				
				int counter = 0;
				rset01.beforeFirst();
				while (rset01.next()) {
					counter=counter+1;
				}
				rt = new Object[counter][64];
				counter = 0;
				rset01.beforeFirst();
				while (rset01.next()) {
					if(null==rset01.getString("ClGpCd")){				rt[counter][ColClGpCd]					="";}else{rt[counter][ColClGpCd]				=rset01.getString("ClGpCd");}				//荷主グループコード
					if(null==rset01.getString("CLGpName01")){			rt[counter][ColCLGpName01]			="";}else{rt[counter][ColCLGpName01]			=rset01.getString("CLGpName01");}			//荷主グループ名1
					if(null==rset01.getString("ItemCd")){				rt[counter][ColItemCd]					="";}else{rt[counter][ColItemCd]				=rset01.getString("ItemCd");}				//商品コード
					if(null==rset01.getString("CLItemCd")){				rt[counter][ColCLItemCd]				="";}else{rt[counter][ColCLItemCd]				=rset01.getString("CLItemCd");}				//荷主商品コード
					if(null==rset01.getString("ItemName01")){			rt[counter][ColItemName01]			="";}else{rt[counter][ColItemName01]			=rset01.getString("ItemName01");}			//商品名1
					if(null==rset01.getString("ItemName02")){			rt[counter][ColItemName02]			="";}else{rt[counter][ColItemName02]			=rset01.getString("ItemName02");}			//商品名2
					if(null==rset01.getString("ItemName03")){			rt[counter][ColItemName03]			="";}else{rt[counter][ColItemName03]			=rset01.getString("ItemName03");}			//商品名3
					if(null==rset01.getString("DeliveryTypeCd01")){		rt[counter][ColDeliveryTypeCd01]		="";}else{rt[counter][ColDeliveryTypeCd01]	=rset01.getString("DeliveryTypeCd01");}		//運送タイプコード01
					if(null==rset01.getString("DeliveryTypeName01")){	rt[counter][ColDeliveryTypeName01]	="";}else{rt[counter][ColDeliveryTypeName01]	=rset01.getString("DeliveryTypeName01");}	//運送タイプ名01
					if(null==rset01.getString("DeliveryTypeCd02")){		rt[counter][ColDeliveryTypeCd02]		="";}else{rt[counter][ColDeliveryTypeCd02]	=rset01.getString("DeliveryTypeCd02");}		//運送タイプコード02
					if(null==rset01.getString("DeliveryTypeName02")){	rt[counter][ColDeliveryTypeName02]	="";}else{rt[counter][ColDeliveryTypeName02]	=rset01.getString("DeliveryTypeName02");}	//運送タイプ名02
					if(null==rset01.getString("DeliveryTypeCd03")){		rt[counter][ColDeliveryTypeCd03]		="";}else{rt[counter][ColDeliveryTypeCd03]	=rset01.getString("DeliveryTypeCd03");}		//運送タイプコード03
					if(null==rset01.getString("DeliveryTypeName03")){	rt[counter][ColDeliveryTypeName03]	="";}else{rt[counter][ColDeliveryTypeName03]	=rset01.getString("DeliveryTypeName03");}	//運送タイプ名03
					if(null==rset01.getString("DeliveryTypeCd04")){		rt[counter][ColDeliveryTypeCd04]		="";}else{rt[counter][ColDeliveryTypeCd04]	=rset01.getString("DeliveryTypeCd04");}		//運送タイプコード04
					if(null==rset01.getString("DeliveryTypeName04")){	rt[counter][ColDeliveryTypeName04]	="";}else{rt[counter][ColDeliveryTypeName04]	=rset01.getString("DeliveryTypeName04");}	//運送タイプ名04
					if(null==rset01.getString("DeliveryTypeCd05")){		rt[counter][ColDeliveryTypeCd05]		="";}else{rt[counter][ColDeliveryTypeCd05]	=rset01.getString("DeliveryTypeCd05");}		//運送タイプコード05
					if(null==rset01.getString("DeliveryTypeName05")){	rt[counter][ColDeliveryTypeName05]	="";}else{rt[counter][ColDeliveryTypeName05]	=rset01.getString("DeliveryTypeName05");}	//運送タイプ名05
					if(null==rset01.getString("PTMSCD")){				rt[counter][ColPTMSCD]					="";}else{rt[counter][ColPTMSCD]				=rset01.getString("PTMSCD");}				//基幹システム商品コード
					rt[counter][ColCtQty]	=rset01.getInt("CtQty");			//カートン入数
					rt[counter][ColCsQty]	=rset01.getInt("CsQty");			//ケース入数
					rt[counter][ColPlQty]	=rset01.getInt("PlQty");			//パレット入数
					if(null==rset01.getString("JanCd")){				rt[counter][ColJanCd]					="";}else{rt[counter][ColJanCd]				=rset01.getString("JanCd");}				//JANCD
					if(null==rset01.getString("CtJan")){				rt[counter][ColCtJan]					="";}else{rt[counter][ColCtJan]				=rset01.getString("CtJan");}				//カートンバーコード
					if(null==rset01.getString("CsJan")){				rt[counter][ColCsJan]					="";}else{rt[counter][ColCsJan]				=rset01.getString("CsJan");}				//ケースバーコード
					if(null==rset01.getString("PlJan")){				rt[counter][ColPlJan]					="";}else{rt[counter][ColPlJan]				=rset01.getString("PlJan");}				//パレットバーコード
					if(null==rset01.getString("CtName")){				rt[counter][ColCtName]					="";}else{rt[counter][ColCtName]				=rset01.getString("CtName");}				//カートン商品名称
					if(null==rset01.getString("CsName")){				rt[counter][ColCsName]					="";}else{rt[counter][ColCsName]				=rset01.getString("CsName");}				//ケース商品名称
					if(null==rset01.getString("PlName")){				rt[counter][ColPlName]					="";}else{rt[counter][ColPlName]				=rset01.getString("PlName");}				//パレット商品名称
					if(null==rset01.getString("UnitName")){				rt[counter][ColUnitName]				="";}else{rt[counter][ColUnitName]				=rset01.getString("UnitName");}				//商品単位
					if(null==rset01.getString("CtUnitName")){			rt[counter][ColCtUnitName]			="";}else{rt[counter][ColCtUnitName]			=rset01.getString("CtUnitName");}			//カートン商品単位
					if(null==rset01.getString("CsUnitName")){			rt[counter][ColCsUnitName]			="";}else{rt[counter][ColCsUnitName]			=rset01.getString("CsUnitName");}			//ケース商品単位
					if(null==rset01.getString("PlUnitName")){			rt[counter][ColPlUnitName]			="";}else{rt[counter][ColPlUnitName]			=rset01.getString("PlUnitName");}			//パレット商品単位
					rt[counter][ColItemWeight]	=rset01.getFloat("ItemWeight");	//商品重量
					rt[counter][ColCtWeight]		=rset01.getFloat("CtWeight");	//カートン重量
					rt[counter][ColCsWeight]		=rset01.getFloat("CsWeight");	//ケース重量
					rt[counter][ColPlWeight]		=rset01.getFloat("PlWeight");	//パレット重量
					rt[counter][ColItemSize]		=rset01.getFloat("ItemSize");	//商品サイズ
					rt[counter][ColCtSize]			=rset01.getFloat("CtSize");		//カートンサイズ
					rt[counter][ColCsSize]			=rset01.getFloat("CsSize");		//ケースサイズ
					rt[counter][ColPlSize]			=rset01.getFloat("PlSize");		//パレットサイズ
					if(null==rset01.getString("RecomendLoc")){			rt[counter][ColRecomendLoc]			="";}else{rt[counter][ColRecomendLoc]			=rset01.getString("RecomendLoc");}			//推奨ロケ
					if(null==rset01.getString("ItemMDNo")){				rt[counter][ColItemMDNo]				="";}else{rt[counter][ColItemMDNo]				=rset01.getString("ItemMDNo");}				//商品モデル番号（型番）
					if(null==rset01.getString("CategoryCd")){			rt[counter][ColCategoryCd]			="";}else{rt[counter][ColCategoryCd]			=rset01.getString("CategoryCd");}			//商品カテゴリCD
					if(null==rset01.getString("CategoryName")){			rt[counter][ColCategoryName]			="";}else{rt[counter][ColCategoryName]		=rset01.getString("CategoryName");}			//商品カテゴリ名
					if(null==rset01.getString("ItemColorCd")){			rt[counter][ColItemColorCd]			="";}else{rt[counter][ColItemColorCd]			=rset01.getString("ItemColorCd");}			//商品カラーコード
					if(null==rset01.getString("ItemColorName")){		rt[counter][ColItemColorName]			="";}else{rt[counter][ColItemColorName]		=rset01.getString("ItemColorName");}		//商品カラー名
					if(null==rset01.getString("ItemSizeCd")){			rt[counter][ColItemSizeCd]			="";}else{rt[counter][ColItemSizeCd]			=rset01.getString("ItemSizeCd");}			//商品サイズコード
					if(null==rset01.getString("ItemSizeName")){			rt[counter][ColItemSizeName]			="";}else{rt[counter][ColItemSizeName]		=rset01.getString("ItemSizeName");}			//商品サイズ名
					if(null==rset01.getString("Com01")){				rt[counter][ColCom01]					="";}else{rt[counter][ColCom01]				=rset01.getString("Com01");}				//コメント1
					if(null==rset01.getString("Com02")){				rt[counter][ColCom02]					="";}else{rt[counter][ColCom02]				=rset01.getString("Com02");}				//コメント2
					if(null==rset01.getString("Com03")){				rt[counter][ColCom03]					="";}else{rt[counter][ColCom03]				=rset01.getString("Com03");}				//コメント3
					if(null==rset01.getString("TildFG")){				rt[counter][ColTildFG]					="";}else{rt[counter][ColTildFG]				=rset01.getString("TildFG");}				//温度区分
					if(null==rset01.getString("TildName")){				rt[counter][ColTildName]				="";}else{rt[counter][ColTildName]				=rset01.getString("TildName");}				//温度区分名
					if(null==rset01.getString("PictPass01")){			rt[counter][ColPictPass01]			="";}else{rt[counter][ColPictPass01]			=rset01.getString("PictPass01");}			//画像パス01
					if(null==rset01.getString("PictPass02")){			rt[counter][ColPictPass02]			="";}else{rt[counter][ColPictPass02]			=rset01.getString("PictPass02");}			//画像パス02
					if(null==rset01.getString("PictPass03")){			rt[counter][ColPictPass03]			="";}else{rt[counter][ColPictPass03]			=rset01.getString("PictPass03");}			//画像パス03
					if(null==rset01.getString("PictPass04")){			rt[counter][ColPictPass04]			="";}else{rt[counter][ColPictPass04]			=rset01.getString("PictPass04");}			//画像パス04
					if(null==rset01.getString("PictPass05")){			rt[counter][ColPictPass05]			="";}else{rt[counter][ColPictPass05]			=rset01.getString("PictPass05");}			//画像パス05
					rt[counter][ColExpDateHowLong]	=rset01.getInt("ExpDateHowLong");//賞味期限日数
					if(null==rset01.getTimestamp("EntryDate")){			rt[counter][ColEntryDate]				="";}else{rt[counter][ColEntryDate]			=B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("EntryDate"))[1];}			//データ登録日時
					if(null==rset01.getTimestamp("UpdateDate")){		rt[counter][ColUpdateDate]			="";}else{rt[counter][ColUpdateDate]			=B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("UpdateDate"))[1];}			//データ更新日時
					if(null==rset01.getString("EntryUser")){			rt[counter][ColEntryUser]				="";}else{rt[counter][ColEntryUser]			=rset01.getString("EntryUser");}			//登録者コード
					if(null==rset01.getString("UpdateUser")){			rt[counter][ColUpdateUser]			="";}else{rt[counter][ColUpdateUser]			=rset01.getString("UpdateUser");}			//更新者コード
					rt[counter][ColDelFg]			=rset01.getInt("DelFg");		//削除フラグ
					
					counter=counter+1;
				}
				if(rset01!=null){rset01.close();}
				if(stmt01!=null){stmt01.close();}
			}catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					if(rset01!=null){rset01.close();}
					if(stmt01!=null){stmt01.close();}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			A00010DbConnect.close();
		}
		return rt;
	}
}