import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class M100_ItemMstRt{
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
	
	Object[][] ItemMstRt = M100_ItemMstRt.ItemMstRt(
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
			
	String GetClGpCd				= (String)ItemMstRt[i][M100_ItemMstRt.ColClGpCd];				//荷主グループコード
	String GetCLGpName01			= (String)ItemMstRt[i][M100_ItemMstRt.ColCLGpName01];			//荷主グループ標記名
	String GetItemCd				= (String)ItemMstRt[i][M100_ItemMstRt.ColItemCd];				//商品コード
	String GetCLItemCd				= (String)ItemMstRt[i][M100_ItemMstRt.ColCLItemCd];				//荷主商品コード
	String GetItemName01			= (String)ItemMstRt[i][M100_ItemMstRt.ColItemName01];			//商品表記名
	String GetItemName02			= (String)ItemMstRt[i][M100_ItemMstRt.ColItemName02];			//商品正式名
	String GetItemName03			= (String)ItemMstRt[i][M100_ItemMstRt.ColItemName03];			//商品略名
	String GetDeliveryTypeCd01		= (String)ItemMstRt[i][M100_ItemMstRt.ColDeliveryTypeCd01];		//運送タイプコード01
	String GetDeliveryTypeName01	= (String)ItemMstRt[i][M100_ItemMstRt.ColDeliveryTypeName01];	//運送タイプ名01
	String GetDeliveryTypeCd02		= (String)ItemMstRt[i][M100_ItemMstRt.ColDeliveryTypeCd02];		//運送タイプコード02
	String GetDeliveryTypeName02	= (String)ItemMstRt[i][M100_ItemMstRt.ColDeliveryTypeName02];	//運送タイプ名02
	String GetDeliveryTypeCd03		= (String)ItemMstRt[i][M100_ItemMstRt.ColDeliveryTypeCd03];		//運送タイプコード03
	String GetDeliveryTypeName03	= (String)ItemMstRt[i][M100_ItemMstRt.ColDeliveryTypeName03];	//運送タイプ名03
	String GetDeliveryTypeCd04		= (String)ItemMstRt[i][M100_ItemMstRt.ColDeliveryTypeCd04];		//運送タイプコード04
	String GetDeliveryTypeName04	= (String)ItemMstRt[i][M100_ItemMstRt.ColDeliveryTypeName04];	//運送タイプ名04
	String GetDeliveryTypeCd05		= (String)ItemMstRt[i][M100_ItemMstRt.ColDeliveryTypeCd05];		//運送タイプコード05
	String GetDeliveryTypeName05	= (String)ItemMstRt[i][M100_ItemMstRt.ColDeliveryTypeName05];	//運送タイプ名05
	String GetPTMSCD				= (String)ItemMstRt[i][M100_ItemMstRt.ColPTMSCD];				//基幹システム商品コード
	int GetCtQty					= (int)ItemMstRt[i][M100_ItemMstRt.ColCtQty];					//カートン入数
	int GetCsQty					= (int)ItemMstRt[i][M100_ItemMstRt.ColCsQty];					//ケース入数
	int GetPlQty					= (int)ItemMstRt[i][M100_ItemMstRt.ColPlQty];					//パレット入数
	String GetJanCd					= (String)ItemMstRt[i][M100_ItemMstRt.ColJanCd];				//JANCD
	String GetCtJan					= (String)ItemMstRt[i][M100_ItemMstRt.ColCtJan];				//カートンバーコード
	String GetCsJan					= (String)ItemMstRt[i][M100_ItemMstRt.ColCsJan];				//ケースバーコード
	String GetPlJan					= (String)ItemMstRt[i][M100_ItemMstRt.ColPlJan];				//パレットバーコード
	String GetCtName				= (String)ItemMstRt[i][M100_ItemMstRt.ColCtName];				//カートン商品名称
	String GetCsName				= (String)ItemMstRt[i][M100_ItemMstRt.ColCsName];				//ケース商品名称
	String GetPlName				= (String)ItemMstRt[i][M100_ItemMstRt.ColPlName];				//パレット商品名称
	String GetUnitName				= (String)ItemMstRt[i][M100_ItemMstRt.ColUnitName];				//商品単位
	String GetCtUnitName			= (String)ItemMstRt[i][M100_ItemMstRt.ColCtUnitName];			//カートン商品単位
	String GetCsUnitName			= (String)ItemMstRt[i][M100_ItemMstRt.ColCsUnitName];			//ケース商品単位
	String GetPlUnitName			= (String)ItemMstRt[i][M100_ItemMstRt.ColPlUnitName];			//パレット商品単位
	float GetItemWeight				= (float)ItemMstRt[i][M100_ItemMstRt.ColItemWeight];			//商品重量
	float GetCtWeight				= (float)ItemMstRt[i][M100_ItemMstRt.ColCtWeight];				//カートン重量
	float GetCsWeight				= (float)ItemMstRt[i][M100_ItemMstRt.ColCsWeight];				//ケース重量
	float GetPlWeight				= (float)ItemMstRt[i][M100_ItemMstRt.ColPlWeight];				//パレット重量
	float GetItemSize				= (float)ItemMstRt[i][M100_ItemMstRt.ColItemSize];				//商品サイズ
	float GetCtSize					= (float)ItemMstRt[i][M100_ItemMstRt.ColCtSize];				//カートンサイズ
	float GetCsSize					= (float)ItemMstRt[i][M100_ItemMstRt.ColCsSize];				//ケースサイズ
	float GetPlSize					= (float)ItemMstRt[i][M100_ItemMstRt.ColPlSize];				//パレットサイズ
	String GetRecomendLoc			= (String)ItemMstRt[i][M100_ItemMstRt.ColRecomendLoc];			//推奨ロケ
	String GetItemMDNo				= (String)ItemMstRt[i][M100_ItemMstRt.ColItemMDNo];				//商品モデル番号（型番）
	String GetCategoryCd			= (String)ItemMstRt[i][M100_ItemMstRt.ColCategoryCd];			//商品カテゴリCD
	String GetCategoryName			= (String)ItemMstRt[i][M100_ItemMstRt.ColCategoryName];			//商品カテゴリ名
	String GetItemColorCd			= (String)ItemMstRt[i][M100_ItemMstRt.ColItemColorCd];			//商品カラーコード
	String GetItemColorName			= (String)ItemMstRt[i][M100_ItemMstRt.ColItemColorName];		//商品カラー名
	String GetItemSizeCd			= (String)ItemMstRt[i][M100_ItemMstRt.ColItemSizeCd];			//商品サイズコード
	String GetItemSizeName			= (String)ItemMstRt[i][M100_ItemMstRt.ColItemSizeName];			//商品サイズ名
	String GetCom01					= (String)ItemMstRt[i][M100_ItemMstRt.ColCom01];				//コメント1
	String GetCom02					= (String)ItemMstRt[i][M100_ItemMstRt.ColCom02];				//コメント2
	String GetCom03					= (String)ItemMstRt[i][M100_ItemMstRt.ColCom03];				//コメント3
	String GetTildFG				= (String)ItemMstRt[i][M100_ItemMstRt.ColTildFG];				//温度区分
	String GetTildName				= (String)ItemMstRt[i][M100_ItemMstRt.ColTildName];				//温度区分名
	String GetPictPass01			= (String)ItemMstRt[i][M100_ItemMstRt.ColPictPass01];			//画像パス01
	String GetPictPass02			= (String)ItemMstRt[i][M100_ItemMstRt.ColPictPass02];			//画像パス02
	String GetPictPass03			= (String)ItemMstRt[i][M100_ItemMstRt.ColPictPass03];			//画像パス03
	String GetPictPass04			= (String)ItemMstRt[i][M100_ItemMstRt.ColPictPass04];			//画像パス04
	String GetPictPass05			= (String)ItemMstRt[i][M100_ItemMstRt.ColPictPass05];			//画像パス05
	int GetExpDateHowLong			= (int)ItemMstRt[i][M100_ItemMstRt.ColExpDateHowLong];			//賞味期限日数
	String GetEntryDate				= (String)ItemMstRt[i][M100_ItemMstRt.ColEntryDate];			//データ登録日時
	String GetUpdateDate			= (String)ItemMstRt[i][M100_ItemMstRt.ColUpdateDate];			//データ更新日時
	String GetEntryUser				= (String)ItemMstRt[i][M100_ItemMstRt.ColEntryUser];			//登録者コード
	String GetUpdateUser			= (String)ItemMstRt[i][M100_ItemMstRt.ColUpdateUser];			//更新者コード
	int GetDelFg					= (int)ItemMstRt[i][M100_ItemMstRt.ColDelFg];					//削除フラグ
	
	*/
	//戻り値カラム
	static final  int ColItemCd				= (int) 0;	//商品コード
	static final  int ColCLItemCd				= (int) 1;	//荷主商品コード
	static final  int ColItemName01			= (int) 2;	//商品表記名
	static final  int ColItemName02			= (int) 3;	//商品正式名
	static final  int ColItemName03			= (int) 4;	//商品略名
	static final  int ColCtQty					= (int) 5;	//カートン入数
	static final  int ColCsQty					= (int) 6;	//ケース入数
	static final  int ColPlQty					= (int) 7;	//パレット入数
	static final  int ColUnitName				= (int) 8;	//商品単位
	static final  int ColCtUnitName			= (int) 9;	//カートン商品単位
	static final  int ColCsUnitName			= (int)10;	//ケース商品単位
	static final  int ColPlUnitName			= (int)11;	//パレット商品単位
	static final  int ColClGpCd				= (int)12;	//荷主グループコード
	static final  int ColCLGpName01			= (int)13;	//荷主グループ標記名
	static final  int ColDeliveryTypeCd01	= (int)14;	//運送タイプコード01
	static final  int ColDeliveryTypeName01	= (int)15;	//運送タイプ名01
	static final  int ColDeliveryTypeCd02	= (int)16;	//運送タイプコード02
	static final  int ColDeliveryTypeName02	= (int)17;	//運送タイプ名02
	static final  int ColDeliveryTypeCd03	= (int)18;	//運送タイプコード03
	static final  int ColDeliveryTypeName03	= (int)19;	//運送タイプ名03
	static final  int ColDeliveryTypeCd04	= (int)20;	//運送タイプコード04
	static final  int ColDeliveryTypeName04	= (int)21;	//運送タイプ名04
	static final  int ColDeliveryTypeCd05	= (int)22;	//運送タイプコード05
	static final  int ColDeliveryTypeName05	= (int)23;	//運送タイプ名05
	static final  int ColPTMSCD				= (int)24;	//基幹システム商品コード
	static final  int ColJanCd					= (int)25;	//JANCD
	static final  int ColCtJan					= (int)26;	//カートンバーコード
	static final  int ColCsJan					= (int)27;	//ケースバーコード
	static final  int ColPlJan					= (int)28;	//パレットバーコード
	static final  int ColCtName				= (int)29;	//カートン商品名称
	static final  int ColCsName				= (int)30;	//ケース商品名称
	static final  int ColPlName				= (int)31;	//パレット商品名称
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
	static final  int ColDelFg					= (int)63;	//削除フラグ

	public static Object[][] RtItemMstRt(){
		Object[][] RtSettingItemMstRt = {
				 {"ClGpCd"					,ColClGpCd					,"String"	,"荷主グループCD"}
				,{"CLGpName01"				,ColCLGpName01			,"String"	,"荷主グループ標記名"}
				,{"ItemCd"					,ColItemCd					,"String"	,"商品CD"}
				,{"CLItemCd"				,ColCLItemCd				,"String"	,"荷主商品CD"}
				,{"ItemName01"				,ColItemName01			,"String"	,"商品表記名"}
				,{"ItemName02"				,ColItemName02			,"String"	,"商品正式名"}
				,{"ItemName03"				,ColItemName03			,"String"	,"商品略名"}
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
				,{"EntryDate"				,ColEntryDate				,"DateTime"	,"データ登録日時"}
				,{"UpdateDate"				,ColUpdateDate			,"DateTime"	,"データ更新日時"}
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
		
		SearchClGpCd			= B100_ArrayListControl.ArryListStringUniqueList(SearchClGpCd);
		SearchItemCd			= B100_ArrayListControl.ArryListStringUniqueList(SearchItemCd);
		SearchCLItemCd			= B100_ArrayListControl.ArryListStringUniqueList(SearchCLItemCd);
		SearchItemName			= B100_ArrayListControl.ArryListStringUniqueList(SearchItemName);
		SearchDeliveryTypeCd01	= B100_ArrayListControl.ArryListStringUniqueList(SearchDeliveryTypeCd01);
		SearchDeliveryTypeCd02	= B100_ArrayListControl.ArryListStringUniqueList(SearchDeliveryTypeCd02);
		SearchDeliveryTypeCd03	= B100_ArrayListControl.ArryListStringUniqueList(SearchDeliveryTypeCd03);
		SearchDeliveryTypeCd04	= B100_ArrayListControl.ArryListStringUniqueList(SearchDeliveryTypeCd04);
		SearchDeliveryTypeCd05	= B100_ArrayListControl.ArryListStringUniqueList(SearchDeliveryTypeCd05);
		SearchItemMDNo			= B100_ArrayListControl.ArryListStringUniqueList(SearchItemMDNo);
		SearchCategoryCd		= B100_ArrayListControl.ArryListStringUniqueList(SearchCategoryCd);
		SearchCategoryName		= B100_ArrayListControl.ArryListStringUniqueList(SearchCategoryName);
		SearchItemColorCd		= B100_ArrayListControl.ArryListStringUniqueList(SearchItemColorCd);
		SearchItemColorName		= B100_ArrayListControl.ArryListStringUniqueList(SearchItemColorName);
		SearchItemSizeCd		= B100_ArrayListControl.ArryListStringUniqueList(SearchItemSizeCd);
		SearchItemSizeName		= B100_ArrayListControl.ArryListStringUniqueList(SearchItemSizeName);
		SearchJanCd				= B100_ArrayListControl.ArryListStringUniqueList(SearchJanCd);
		SearchTildFG			= B100_ArrayListControl.ArryListStringUniqueList(SearchTildFG);
		SearchTildName			= B100_ArrayListControl.ArryListStringUniqueList(SearchTildName);
		SearchDelFg				= B100_ArrayListControl.ArryListStringUniqueList(SearchDelFg);
		
		//商品変換マスタを元に荷主商品コードを商品コードに変換する
		Object[][] SearchItemCdFromClItem	= SearchItemCdFromClItem(SearchClGpCd,SearchCLItemCd);
		
		Object[][] rt = new Object[0][RtItemMstRt().length];
		boolean SearchKick = false;
		if(AllSearch) {SearchKick = true;}
		
		String sql = " select \n"
				+"(KM0060_ITEMMST.ClGpCd) as ClGpCd,\n"						//荷主グループコード
				+"(KM0031_CLIENT_GROUP.CLGpName01) as CLGpName01,\n"		//荷主グループ標記名
				+"(KM0060_ITEMMST.ItemCd) as ItemCd,\n"						//商品コード
				+"(KM0060_ITEMMST.CLItemCd) as CLItemCd,\n"					//荷主商品コード
				+"(KM0060_ITEMMST.ItemName01) as ItemName01,\n"				//商品表記名
				+"(KM0060_ITEMMST.ItemName02) as ItemName02,\n"				//商品正式名
				+"(KM0060_ITEMMST.ItemName03) as ItemName03,\n"				//商品略名
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
				
				
				+ " from "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0060_ITEMMST \n"
				+ " left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0061_ITEMMSTSUB \n"
				+ " on("
				+ " KM0060_ITEMMST.ClGpCd = KM0061_ITEMMSTSUB.ClGpCd and KM0060_ITEMMST.ItemCd = KM0061_ITEMMSTSUB.ItemCd"
				+ ")\n"
				+ " left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP"
				+ " on("
				+ " KM0060_ITEMMST.ClGpCd = KM0031_CLIENT_GROUP.ClGpCD"
				+ ")\n"
				+ " left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0050_DELIVERY_TYPEMST as DT01"
				+ " on("
				+ "1 = DT01.DeliveryTypeNo and KM0060_ITEMMST.DeliveryTypeCd = DT01.DeliveryTypeCd"
				+ ")\n"
				+ " left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0050_DELIVERY_TYPEMST as DT02"
				+ " on("
				+ "2 = DT02.DeliveryTypeNo and KM0060_ITEMMST.DeliveryTypeCd02 = DT02.DeliveryTypeCd"
				+ ")\n"
				+ " left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0050_DELIVERY_TYPEMST as DT03"
				+ " on("
				+ "3 = DT03.DeliveryTypeNo and KM0060_ITEMMST.DeliveryTypeCd03 = DT03.DeliveryTypeCd"
				+ ")\n"
				+ " left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0050_DELIVERY_TYPEMST as DT04"
				+ " on("
				+ "4 = DT04.DeliveryTypeNo and KM0060_ITEMMST.DeliveryTypeCd04 = DT04.DeliveryTypeCd"
				+ ")\n"
				+ " left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0050_DELIVERY_TYPEMST as DT05"
				+ " on("
				+ "5 = DT05.DeliveryTypeNo and KM0060_ITEMMST.DeliveryTypeCd05 = DT05.DeliveryTypeCd"
				+ ")\n"
				+ "where 1=1\n"
				+ "";
		if(null!=SearchCLItemCd&&0<SearchCLItemCd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchCLItemCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KM0060_ITEMMST.CLItemCd = ?";
			}
			if(null!=SearchItemCdFromClItem&&0<SearchItemCdFromClItem.length) {
				for(int i=0;i<SearchItemCdFromClItem.length;i++){
					sql = sql + " or (KM0060_ITEMMST.ItemCd = ? ";
					sql = sql + "  and KM0060_ITEMMST.ClGpCd = ?)";
				}
			}
			sql = sql + ")";
		}
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
			A100_DbConnect.DB_CONN("NYANKO");
			ResultSet rset01 = null;
			PreparedStatement stmt01 = null;
			try {
				stmt01 = A100_DbConnect.conn.prepareStatement(sql);
				int StmtCount = 0;

				if(null!=SearchCLItemCd&&0<SearchCLItemCd.size()){
					for(int i=0;i<SearchCLItemCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchCLItemCd.get(i)+"");
					}
					if(null!=SearchItemCdFromClItem&&0<SearchItemCdFromClItem.length) {
						for(int i=0;i<SearchItemCdFromClItem.length;i++){
							StmtCount = StmtCount+1;
							stmt01.setString(StmtCount, ""+SearchItemCdFromClItem[i][M100_ItemComversionMstRt.ColItemCd]+"");
							StmtCount = StmtCount+1;
							stmt01.setString(StmtCount, ""+SearchItemCdFromClItem[i][M100_ItemComversionMstRt.ColClGpCd]+"");
						}
					}
				}
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
				
				rt = B100_RtObjectCreate.B100_RtObjectCreate(rset01,RtItemMstRt());
				
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
			A100_DbConnect.close();
		}
		return rt;
	}
	
	
	private static Object[][] SearchItemCdFromClItem(ArrayList<String> SearchClGpCd,ArrayList<String> SearchCLItemCd){
		//ArrayList<String> SearchClGpCd = new ArrayList<String>();		//荷主グループコード
		ArrayList<String> SearchClCd = new ArrayList<String>();			//荷主コード
		ArrayList<String> SearchItemCd = new ArrayList<String>();		//商品コード
		//ArrayList<String> SearchCLItemCd = new ArrayList<String>();		//荷主商品コード
		ArrayList<String> SearchItemName = new ArrayList<String>();		//商品名
		boolean AllSearch = false;
		
		Object[][] ItemComversionMstRt = M100_ItemComversionMstRt.ItemComversionMstRt(
				SearchClGpCd,			//荷主グループコード
				SearchClCd,				//荷主コード
				SearchItemCd,			//商品コード
				SearchCLItemCd,			//荷主商品コード
				SearchItemName,			//商品名
				AllSearch);
		return ItemComversionMstRt;
	}
}