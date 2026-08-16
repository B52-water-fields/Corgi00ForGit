import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class T100_StockRt{
	//在庫情報をマスタ情報等とのJOINして返却する
	/*
	コピペ用
	ArrayList<String> SearchClCd				= new ArrayList<String>();			//荷主コード
	ArrayList<String> SearchWhCd				= new ArrayList<String>();			//倉庫コード
	ArrayList<String> SearchClGpCD				= new ArrayList<String>();			//荷主グループCD
	ArrayList<String> SearchLoc					= new ArrayList<String>();			//ロケーション
	ArrayList<Integer>SearchType				= new ArrayList<Integer>();			//ロケタイプ　0:通常　1:保管　8:入荷時　9:引当禁止
	ArrayList<String> SearchItemCd				= new ArrayList<String>();			//商品コード
	ArrayList<String> SearchLot					= new ArrayList<String>();			//ロット
	ArrayList<String> SearchExpdateMin			= new ArrayList<String>();			//消費期限最小
	ArrayList<String> SearchExpdateMax			= new ArrayList<String>();			//消費期限最大
	ArrayList<String> SearchActualDateMin		= new ArrayList<String>();			//入荷実績日最小
	ArrayList<String> SearchActualDateMax		= new ArrayList<String>();			//入荷実績日最大
	ArrayList<Integer> SearchQtyMin				= new ArrayList<Integer>();			//数量最小
	ArrayList<Integer> SearchQtyMax				= new ArrayList<Integer>();			//数量最大
	ArrayList<Integer> SearchShipPlanQtyMin		= new ArrayList<Integer>();			//引当済数最小
	ArrayList<Integer> SearchShipPlanQtyMax		= new ArrayList<Integer>();			//引当済数最大
	ArrayList<Integer> SearchPossibleQtyMin		= new ArrayList<Integer>();			//出荷可能数最小
	ArrayList<Integer> SearchPossibleQtyMax		= new ArrayList<Integer>();			//出荷可能数最大
	ArrayList<String> SearchItemName			= new ArrayList<String>();			//商品名
	ArrayList<String> SearchClItemCd			= new ArrayList<String>();			//荷主商品コード
	ArrayList<String> SearchJanCd				= new ArrayList<String>();			//ソースマーク_BCD（バラ）
	ArrayList<String> SearchItemMdNo			= new ArrayList<String>();			//商品型番
	boolean LocExactMatch = false;													//ロケーション完全一致
	boolean AllSearch = false;														//全件検索
	boolean SortItemcdMode = false;													//商品CDでソート
	
	Object[][] StockRt= T100_StockRt.StockRt(
							SearchClCd,				//荷主コード
							SearchWhCd,				//倉庫コード
							SearchClGpCD,			//荷主グループCD
							SearchLoc,				//ロケーション
							SearchType,				//ロケタイプ　0:通常　1:保管　8:入荷時　9:引当禁止
							SearchItemCd,			//商品コード
							SearchLot,				//ロット
							SearchExpdateMin,		//消費期限最小
							SearchExpdateMax,		//消費期限最大
							SearchActualDateMin,	//入荷実績日最小
							SearchActualDateMax,	//入荷実績日最大
							SearchQtyMin,			//数量最小
							SearchQtyMax,			//数量最大
							SearchShipPlanQtyMin,	//引当済数最小
							SearchShipPlanQtyMax,	//引当済数最大
							SearchPossibleQtyMin,	//出荷可能数最小
							SearchPossibleQtyMax,	//出荷可能数最大
							SearchItemName,			//商品名
							SearchClItemCd,			//荷主商品コード
							SearchJanCd,			//ソースマーク_BCD（バラ）
							SearchItemMdNo,			//商品型番
							LocExactMatch,			//ロケーション完全一致
							AllSearch,
							SortItemcdMode);
							
							
	String GetClCd			= (String)StockRt[i][T100_StockRt.ColClCd];				//荷主コード
	String GetCLName		= (String)StockRt[i][T100_StockRt.ColCLName];			//荷主表記名
	String GetWhCd			= (String)StockRt[i][T100_StockRt.ColWhCd];				//倉庫コード
	String GetClWHName		= (String)StockRt[i][T100_StockRt.ColClWHName];			//担当倉庫名
	String GetClGpCD		= (String)StockRt[i][T100_StockRt.ColClGpCD];			//荷主グループCD
	String GetClGpName		= (String)StockRt[i][T100_StockRt.ColClGpName];			//グループ名1
	String GetLoc			= (String)StockRt[i][T100_StockRt.ColLoc];				//ロケーション
	String GetLocName		= (String)StockRt[i][T100_StockRt.ColLocName];			//ロケーション名
	int GetType				= (int)StockRt[i][T100_StockRt.ColType];				//ロケタイプ
	String GetItemCd		= (String)StockRt[i][T100_StockRt.ColItemCd];			//商品コード
	String GetLot			= (String)StockRt[i][T100_StockRt.ColLot];				//ロット
	String GetExpdate		= (String)StockRt[i][T100_StockRt.ColExpdate];			//消費期限
	String GetActualDate	= (String)StockRt[i][T100_StockRt.ColActualDate];		//入荷実績日
	int GetQty				= (int)StockRt[i][T100_StockRt.ColQty];					//総数量
	int GetShipPlanQty		= (int)StockRt[i][T100_StockRt.ColShipPlanQty];			//引当済総数
	int GetPossibleQty		= (int)StockRt[i][T100_StockRt.ColPossibleQty];			//出荷可能総数
	String GetItemName		= (String)StockRt[i][T100_StockRt.ColItemName];			//商品名
	String GetItemName01	= (String)StockRt[i][T100_StockRt.ColItemName01];		//商品表記名
	String GetItemName02	= (String)StockRt[i][T100_StockRt.ColItemName02];		//商品正式名
	String GetItemName03	= (String)StockRt[i][T100_StockRt.ColItemName03];		//商品略名
	String GetClItemCd		= (String)StockRt[i][T100_StockRt.ColClItemCd];			//荷主商品コード
	String GetJanCd			= (String)StockRt[i][T100_StockRt.ColJanCd];			//ソースマーク_BCD（バラ）
	String GetItemMdNo		= (String)StockRt[i][T100_StockRt.ColItemMdNo];			//商品型番
	int GetCtUnitQty		= (int)StockRt[i][T100_StockRt.ColCtUnitQty];			//カートン入数
	int GetCsUnitQty		= (int)StockRt[i][T100_StockRt.ColCsUnitQty];			//ケース入数
	int GetPlUnitQty		= (int)StockRt[i][T100_StockRt.ColPlUnitQty];			//パレット入数
	String GetUnitName		= (String)StockRt[i][T100_StockRt.ColUnitName];			//商品単位
	String GetCtUnitName	= (String)StockRt[i][T100_StockRt.ColCtUnitName];		//カートン商品単位
	String GetCsUnitName	= (String)StockRt[i][T100_StockRt.ColCsUnitName];		//ケース商品単位
	String GetPlUnitName	= (String)StockRt[i][T100_StockRt.ColPlUnitName];		//パレット商品単位
	String GetEntryDate		= (String)StockRt[i][T100_StockRt.ColEntryDate];		//登録日時
	String GetUpdateDate	= (String)StockRt[i][T100_StockRt.ColUpdateDate];		//更新日時
	String GetEntryUser		= (String)StockRt[i][T100_StockRt.ColEntryUser];		//登録者
	String GetUpdateUser	= (String)StockRt[i][T100_StockRt.ColUpdateUser];		//更新者
	int GetBrQty			= (int)StockRt[i][T100_StockRt.ColBrQty];				//バラ数量
	int GetBrShipPlanQty	= (int)StockRt[i][T100_StockRt.ColBrShipPlanQty];		//引当済バラ数
	int GetBrPossibleQty	= (int)StockRt[i][T100_StockRt.ColBrPossibleQty];		//出荷可能バラ数
	int GetCtQty			= (int)StockRt[i][T100_StockRt.ColCtQty];				//カートン数量
	int GetCtShipPlanQty	= (int)StockRt[i][T100_StockRt.ColCtShipPlanQty];		//引当済カートン数
	int GetCtPossibleQty	= (int)StockRt[i][T100_StockRt.ColCtPossibleQty];		//出荷可能カートン数
	int GetCsQty			= (int)StockRt[i][T100_StockRt.ColCsQty];				//ケース数量
	int GetCsShipPlanQty	= (int)StockRt[i][T100_StockRt.ColCsShipPlanQty];		//引当済ケース数
	int GetCsPossibleQty	= (int)StockRt[i][T100_StockRt.ColCsPossibleQty];		//出荷可能ケース数
	int GetPlQty			= (int)StockRt[i][T100_StockRt.ColPlQty];				//パレット数量
	int GetPlShipPlanQty	= (int)StockRt[i][T100_StockRt.ColPlShipPlanQty];		//引当済パレット数
	int GetPlPossibleQty	= (int)StockRt[i][T100_StockRt.ColPlPossibleQty];		//出荷可能パレット数
	
	*/
	//戻り値カラム
	static final int ColLocName		= (int)0;		//ロケーション名
	static final int ColItemCd			= (int)1;		//商品コード
	static final int ColItemName		= (int)2;		//商品名
	static final int ColLot			= (int)3;		//ロット
	static final int ColExpdate		= (int)4;		//消費期限
	static final int ColActualDate	= (int)5;		//入荷実績日
	static final int ColQty			= (int)6;		//数量
	static final int ColShipPlanQty	= (int)7;		//引当済数
	static final int ColPossibleQty	= (int)8;		//出荷可能数
	static final int ColClCd			= (int)9;		//荷主コード
	static final int ColCLName			= (int)10;		//荷主表記名
	static final int ColWhCd			= (int)11;		//倉庫コード
	static final int ColClWHName		= (int)12;		//担当倉庫名
	static final int ColClGpCD			= (int)13;		//荷主グループCD
	static final int ColClGpName		= (int)14;		//グループ名1
	static final int ColLoc			= (int)15;		//ロケーション
	static final int ColType			= (int)16;		//ロケタイプ
	static final int ColItemName01	= (int)17;		//商品表記名
	static final int ColItemName02	= (int)18;		//商品正式名
	static final int ColItemName03	= (int)19;		//商品略名
	static final int ColClItemCd		= (int)20;		//荷主商品コード
	static final int ColJanCd			= (int)21;		//ソースマーク_BCD（バラ）
	static final int ColItemMdNo		= (int)22;		//商品型番
	static final int ColCtUnitQty		= (int)23;		//カートン入数
	static final int ColCsUnitQty		= (int)24;		//ケース入数
	static final int ColPlUnitQty		= (int)25;		//パレット入数
	static final int ColUnitName		= (int)26;		//商品単位
	static final int ColCtUnitName	= (int)27;		//カートン商品単位
	static final int ColCsUnitName	= (int)28;		//ケース商品単位
	static final int ColPlUnitName	= (int)29;		//パレット商品単位
	static final int ColEntryDate		= (int)30;		//登録日時
	static final int ColUpdateDate	= (int)31;		//更新日時
	static final int ColEntryUser		= (int)32;		//登録者
	static final int ColUpdateUser	= (int)33;		//更新者
	static final int ColBrQty			= (int)34;		//バラ数量
	static final int ColBrShipPlanQty	= (int)35;		//引当済バラ数
	static final int ColBrPossibleQty	= (int)36;		//出荷可能バラ数
	static final int ColCtQty			= (int)37;		//カートン数量
	static final int ColCtShipPlanQty	= (int)38;		//引当済カートン数
	static final int ColCtPossibleQty	= (int)39;		//出荷可能カートン数
	static final int ColCsQty			= (int)40;		//ケース数量
	static final int ColCsShipPlanQty	= (int)41;		//引当済ケース数
	static final int ColCsPossibleQty	= (int)42;		//出荷可能ケース数
	static final int ColPlQty			= (int)43;		//パレット数量
	static final int ColPlShipPlanQty	= (int)44;		//引当パレット済数
	static final int ColPlPossibleQty	= (int)45;		//出荷可能パレット数
	
	//検索値カラム
	static final int ColSearchClCd				= (int) 0;	//荷主コード
	static final int ColSearchWhCd				= (int) 1;	//倉庫コード
	static final int ColSearchClGpCD				= (int) 2;	//荷主グループCD
	static final int ColSearchLoc					= (int) 3;	//ロケーション
	static final int ColSearchType				= (int) 4;	//ロケタイプ　0:通常　1:保管　8:入荷時　9:引当禁止
	static final int ColSearchItemCd				= (int) 5;	//商品コード
	static final int ColSearchLot					= (int) 6;	//ロット
	static final int ColSearchExpdateMin			= (int) 7;	//消費期限開始
	static final int ColSearchExpdateMax			= (int) 8;	//消費期限終了
	static final int ColSearchActualDateMin		= (int) 9;	//入荷実績日開始
	static final int ColSearchActualDateMax		= (int)10;	//入荷実績日終了
	static final int ColSearchQtyMin				= (int)11;	//数量最小
	static final int ColSearchQtyMax				= (int)12;	//数量最大
	static final int ColSearchShipPlanQtyMin		= (int)13;	//引当済数最小
	static final int ColSearchShipPlanQtyMax		= (int)14;	//引当済数最大
	static final int ColSearchPossibleQtyMin		= (int)15;	//出荷可能数最小
	static final int ColSearchPossibleQtyMax		= (int)16;	//出荷可能数最大
	static final int ColSearchItemName			= (int)17;	//商品名
	static final int ColSearchClItemCd			= (int)18;	//荷主商品コード
	static final int ColSearchJanCd				= (int)19;	//ソースマーク_BCD（バラ）
	static final int ColSearchItemMdNo			= (int)20;	//商品型番
	
	public static Object[][] RtStockRt(){
		Object[][] RtStockRtBase = {
				 {"ClCd"			,ColClCd			,"String"	,"荷主コード"					,"key"	,"Client Code"				,"货主代码"}
				,{"CLName"			,ColCLName			,"String"	,"荷主表記名"					,""		,"Client Name"				,"货主名称"}
				,{"WhCd"			,ColWhCd			,"String"	,"倉庫コード"					,"key"	,"Warehouse Code"			,"仓库代码"}
				,{"ClWHName"		,ColClWHName		,"String"	,"担当倉庫名"					,""		,"Warehouse Name"			,"仓库名称"}
				,{"ClGpCD"			,ColClGpCD			,"String"	,"荷主グループCD"				,""		,"Client Group Code"		,"货主组代码"}
				,{"ClGpName"		,ColClGpName		,"String"	,"グループ名1"					,""		,"Client Group Name"		,"货主组名称"}
				,{"Loc"				,ColLoc			,"String"	,"ロケーション"					,"key"	,"Location"					,"库位"}
				,{"LocName"			,ColLocName		,"String"	,"ロケーション名"				,""		,"Location Name"			,"库位名称"}
				,{"LocType"			,ColType			,"int"		,"ロケタイプ"					,""		,"Location Type"			,"库位类型"}
				,{"ItemCd"			,ColItemCd			,"String"	,"商品コード"					,"key"	,"Item Code"				,"商品代码"}
				,{"Lot"				,ColLot			,"String"	,"ロット"						,"key"	,"Lot"						,"批次"}
				,{"Expdate"			,ColExpdate		,"Date"		,"消費期限"						,"key"	,"Expiration Date"			,"有效期"}
				,{"ActualDate"		,ColActualDate	,"Date"		,"入荷実績日"					,"key"	,"Receipt Date"				,"入库日期"}
				,{"Qty"				,ColQty			,"int"		,"総数量"						,""		,"Total Qty"				,"总数量"}
				,{"ShipPlanQty"		,ColShipPlanQty	,"int"		,"引当済総数"					,""		,"Allocated Total Qty"		,"已分配总数"}
				,{"PossibleQty"		,ColPossibleQty	,"int"		,"出荷可能総数"					,""		,"Available Total Qty"		,"可出库总数"}
				,{"ItemName"		,ColItemName		,"String"	,"商品名"						,""		,"Item Name"				,"商品名称"}
				,{"ItemName01"		,ColItemName01	,"String"	,"商品表記名"					,""		,"Display Item Name"		,"商品显示名称"}
				,{"ItemName02"		,ColItemName02	,"String"	,"商品正式名"					,""		,"Official Item Name"		,"商品正式名称"}
				,{"ItemName03"		,ColItemName03	,"String"	,"商品略名"						,""		,"Short Item Name"			,"商品简称"}
				,{"ClItemCd"		,ColClItemCd		,"String"	,"荷主商品コード"				,""		,"Client Item Code"			,"货主商品代码"}
				,{"JanCd"			,ColJanCd			,"String"	,"バラBCD"						,""		,"Each BCD"					,"单品BCD"}
				,{"ItemMdNo"		,ColItemMdNo		,"String"	,"商品型番"						,""		,"Item Model No."			,"商品型号"}
				,{"CtUnitQty"		,ColCtUnitQty		,"int"		,"カートン入数"					,""		,"Carton Pack Qty"			,"纸箱装量"}
				,{"CsUnitQty"		,ColCsUnitQty		,"int"		,"ケース入数"					,""		,"Case Pack Qty"				,"箱装量"}
				,{"PlUnitQty"		,ColPlUnitQty		,"int"		,"パレット入数"					,""		,"Pallet Pack Qty"			,"托盘装量"}
				,{"UnitName"		,ColUnitName		,"String"	,"商品単位"						,""		,"Item Unit"				,"商品单位"}
				,{"CtUnitName"		,ColCtUnitName	,"String"	,"カートン商品単位"				,""		,"Carton Unit"				,"纸箱单位"}
				,{"CsUnitName"		,ColCsUnitName	,"String"	,"ケース商品単位"				,""		,"Case Unit"				,"箱单位"}
				,{"PlUnitName"		,ColPlUnitName	,"String"	,"パレット商品単位"				,""		,"Pallet Unit"				,"托盘单位"}
				,{"EntryDate"		,ColEntryDate		,"DateTime"	,"登録日時"						,""		,"Created At"				,"登记时间"}
				,{"UpdateDate"		,ColUpdateDate	,"DateTime"	,"更新日時"						,""		,"Updated At"				,"更新时间"}
				,{"EntryUser"		,ColEntryUser		,"String"	,"登録者"						,""		,"Created By"				,"登记人"}
				,{"UpdateUser"		,ColUpdateUser	,"String"	,"更新者"						,""		,"Updated By"				,"更新人"}
				,{"BrQty"			,ColBrQty			,"int"		,"バラ数量"						,""		,"Each Qty"					,"单品数量"}
				,{"BrShipPlanQty"	,ColBrShipPlanQty	,"int"		,"引当済バラ数"					,""		,"Allocated Each Qty"		,"已分配单品数"}
				,{"BrPossibleQty"	,ColBrPossibleQty	,"int"		,"出荷可能バラ数"				,""		,"Available Each Qty"		,"可出库单品数"}
				,{"CtQty"			,ColCtQty			,"int"		,"カートン数量"					,""		,"Carton Qty"				,"纸箱数量"}
				,{"CtShipPlanQty"	,ColCtShipPlanQty	,"int"		,"引当済カートン数"				,""		,"Allocated Carton Qty"		,"已分配纸箱数"}
				,{"CtPossibleQty"	,ColCtPossibleQty	,"int"		,"出荷可能カートン数"			,""		,"Available Carton Qty"		,"可出库纸箱数"}
				,{"CsQty"			,ColCsQty			,"int"		,"ケース数量"					,""		,"Case Qty"					,"箱数量"}
				,{"CsShipPlanQty"	,ColCsShipPlanQty	,"int"		,"引当済ケース数"				,""		,"Allocated Case Qty"		,"已分配箱数"}
				,{"CsPossibleQty"	,ColCsPossibleQty	,"int"		,"出荷可能ケース数"				,""		,"Available Case Qty"		,"可出库箱数"}
				,{"PlQty"			,ColPlQty			,"int"		,"パレット数量"					,""		,"Pallet Qty"				,"托盘数量"}
				,{"PlShipPlanQty"	,ColPlShipPlanQty	,"int"		,"引当済パレット数"				,""		,"Allocated Pallet Qty"		,"已分配托盘数"}
				,{"PlPossibleQty"	,ColPlPossibleQty	,"int"		,"出荷可能パレット数"			,""		,"Available Pallet Qty"		,"可出库托盘数"}
				};
		
		Object[][] RtStockRt = B100_LanguageControl.RtControl(RtStockRtBase);
		return RtStockRt;
	}
	
	public static Object[][] DefinitionRt(){
		Object[][] Definition = {
				 {"String"		,null	,"Exact"			,ColSearchClCd				,B100_DefaultVariable.SearchClList		,"荷主CD"			,""		,"Client Code"			,""		,"货主代码"			,""}
				,{"String"		,null	,"Exact"			,ColSearchWhCd				,B100_DefaultVariable.SearchWhList		,"倉庫CD"			,""		,"Warehouse Code"		,""		,"仓库代码"			,""}
				,{"String"		,null	,"Exact"			,ColSearchClGpCD				,B100_DefaultVariable.SearchClGpList		,"荷主グループCD"	,""		,"Client Group Code"	,""		,"货主组代码"			,""}
				,{"String"		,null	,"ExactOrPrefix"	,ColSearchLoc					,""											,"ロケーション"		,""		,"Location"				,""		,"库位"				,""}
				,{"Integer"		,null	,"Exact"			,ColSearchType				,""											,"ロケタイプ"		,""		,"Location Type"		,""		,"库位类型"			,""}
				,{"String"		,null	,"Exact"			,ColSearchItemCd				,""											,"商品コード"		,""		,"Item Code"			,""		,"商品代码"			,""}
				,{"String"		,null	,"Exact"			,ColSearchLot					,""											,"ロット"			,""		,"Lot"					,""		,"批次"				,""}
				,{"Date"		,null	,"RangeStr"			,ColSearchExpdateMin			,""											,"消費期限"			,"開始"	,"Expiration Date"		,"From"	,"有效期"			,"开始"}
				,{"Date"		,null	,"RangeEnd"			,ColSearchExpdateMax			,""											,"消費期限"			,"終了"	,"Expiration Date"		,"To"	,"有效期"			,"结束"}
				,{"Date"		,null	,"RangeStr"			,ColSearchActualDateMin		,""											,"入荷実績日"		,"開始"	,"Receipt Date"			,"From"	,"入库日期"			,"开始"}
				,{"Date"		,null	,"RangeEnd"			,ColSearchActualDateMax		,""											,"入荷実績日"		,"終了"	,"Receipt Date"			,"To"	,"入库日期"			,"结束"}
				,{"Integer"		,null	,"RangeMin"			,ColSearchQtyMin				,""											,"数量最小"			,""		,"Minimum Qty"			,""		,"最小数量"			,""}
				,{"Integer"		,null	,"RangeMax"			,ColSearchQtyMax				,""											,"数量最大"			,""		,"Maximum Qty"			,""		,"最大数量"			,""}
				,{"Integer"		,null	,"RangeMin"			,ColSearchShipPlanQtyMin		,""											,"引当済数"			,"最小"	,"Allocated Qty"		,"Min"	,"已分配数量"		,"最小"}
				,{"Integer"		,null	,"RangeMax"			,ColSearchShipPlanQtyMax		,""											,"引当済数"			,"最大"	,"Allocated Qty"		,"Max"	,"已分配数量"		,"最大"}
				,{"Integer"		,null	,"RangeMin"			,ColSearchPossibleQtyMin		,""											,"出荷可能数"		,"最小"	,"Available Qty"		,"Min"	,"可出库数量"		,"最小"}
				,{"Integer"		,null	,"RangeMax"			,ColSearchPossibleQtyMax		,""											,"出荷可能数"		,"最大"	,"Available Qty"		,"Max"	,"可出库数量"		,"最大"}
				,{"String"		,null	,"Partial"			,ColSearchItemName			,""											,"商品名"			,""		,"Item Name"			,""		,"商品名称"			,""}
				,{"String"		,null	,"Exact"			,ColSearchClItemCd			,""											,"荷主商品CD"		,""		,"Client Item Code"		,""		,"货主商品代码"		,""}
				,{"String"		,null	,"Exact"			,ColSearchJanCd				,""											,"BCD"				,""		,"BCD"					,""		,"BCD"				,""}
				,{"String"		,null	,"Exact"			,ColSearchItemMdNo			,""											,"商品型番"			,""		,"Item Model No."		,""		,"商品型号"			,""}
				};
		
		Definition = B100_LanguageControl.DefinitionControl(Definition);
		
		return Definition;
	}
	
	public static Object[][] StockRt(
			ArrayList<String> SearchClCd,				//荷主コード
			ArrayList<String> SearchWhCd,				//倉庫コード
			ArrayList<String> SearchClGpCD,				//荷主グループCD
			ArrayList<String> SearchLoc,				//ロケーション
			ArrayList<Integer> SearchType,				//ロケタイプ　0:通常　1:保管　8:入荷時　9:引当禁止
			ArrayList<String> SearchItemCd,				//商品コード
			ArrayList<String> SearchLot,				//ロット
			ArrayList<String> SearchExpdateMin,			//消費期限開始
			ArrayList<String> SearchExpdateMax,			//消費期限終了
			ArrayList<String> SearchActualDateMin,		//入荷実績日開始
			ArrayList<String> SearchActualDateMax,		//入荷実績日終了
			ArrayList<Integer> SearchQtyMin,			//数量最小
			ArrayList<Integer> SearchQtyMax,			//数量最大
			ArrayList<Integer> SearchShipPlanQtyMin,	//引当済数最小
			ArrayList<Integer> SearchShipPlanQtyMax,	//引当済数最大
			ArrayList<Integer> SearchPossibleQtyMin,	//出荷可能数最小
			ArrayList<Integer> SearchPossibleQtyMax,	//出荷可能数最大
			ArrayList<String> SearchItemName,			//商品名
			ArrayList<String> SearchClItemCd,			//荷主商品コード
			ArrayList<String> SearchJanCd,				//ソースマーク_BCD（バラ）
			ArrayList<String> SearchItemMdNo,			//商品型番
			boolean LocExactMatch,	//ロケーション完全一致
			boolean AllSearch,
			boolean SortItemcdMode){

		Object[][] Definition = DefinitionRt();
		for(int i=0;i<Definition.length;i++) {
			switch((int)Definition[i][3]) {
				case ColSearchClCd:	
					Definition[i][1]	= SearchClCd;
					break;
				case ColSearchWhCd:	
					Definition[i][1]	= SearchWhCd;
					break;
				case ColSearchClGpCD:	
					Definition[i][1]	= SearchClGpCD;
					break;
				case ColSearchLoc:	
					Definition[i][1]	= SearchLoc;
					break;
				case ColSearchType:	
					Definition[i][1]	= SearchType;
					break;
				case ColSearchItemCd:	
					Definition[i][1]	= SearchItemCd;
					break;
				case ColSearchLot:	
					Definition[i][1]	= SearchLot;
					break;
				case ColSearchExpdateMin:	
					Definition[i][1]	= SearchExpdateMin;
					break;
				case ColSearchExpdateMax:	
					Definition[i][1]	= SearchExpdateMax;
					break;
				case ColSearchActualDateMin:	
					Definition[i][1]	= SearchActualDateMin;
					break;
				case ColSearchActualDateMax:	
					Definition[i][1]	= SearchActualDateMax;
					break;
				case ColSearchQtyMin:	
					Definition[i][1]	= SearchQtyMin;
					break;
				case ColSearchQtyMax:	
					Definition[i][1]	= SearchQtyMax;
					break;
				case ColSearchShipPlanQtyMin:	
					Definition[i][1]	= SearchShipPlanQtyMin;
					break;
				case ColSearchShipPlanQtyMax:	
					Definition[i][1]	= SearchShipPlanQtyMax;
					break;
				case ColSearchPossibleQtyMin:	
					Definition[i][1]	= SearchPossibleQtyMin;
					break;
				case ColSearchPossibleQtyMax:	
					Definition[i][1]	= SearchPossibleQtyMax;
					break;
				case ColSearchItemName:	
					Definition[i][1]	= SearchItemName;
					break;
				case ColSearchClItemCd:	
					Definition[i][1]	= SearchClItemCd;
					break;
				case ColSearchJanCd:	
					Definition[i][1]	= SearchJanCd;
					break;
				case ColSearchItemMdNo:	
					Definition[i][1]	= SearchItemMdNo;
					break;
				default:
					break;
			}
		}
		/*
		日付系検索最小は念のため00:00:00扱い
		日付系検索項目最大は一日進めて00:00:00扱い
		検索条件の重複除去
		*/
		Definition	= B100_ArraySearchControl.SearchDefinitionControl(Definition);
		
		for(int i=0;i<Definition.length;i++) {
			switch((int)Definition[i][3]) {
				case ColSearchClCd:	
					SearchClCd				= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchWhCd:	
					SearchWhCd				= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchClGpCD:	
					SearchClGpCD			= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchLoc:	
					SearchLoc				= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchType:	
					SearchType				= (ArrayList<Integer>)Definition[i][1];
					break;
				case ColSearchItemCd:	
					SearchItemCd			= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchLot:	
					SearchLot				= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchExpdateMin:	
					SearchExpdateMin		= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchExpdateMax:	
					SearchExpdateMax		= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchActualDateMin:	
					SearchActualDateMin		= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchActualDateMax:	
					SearchActualDateMax		= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchQtyMin:	
					SearchQtyMin			= (ArrayList<Integer>)Definition[i][1];
					break;
				case ColSearchQtyMax:	
					SearchQtyMax			= (ArrayList<Integer>)Definition[i][1];
					break;
				case ColSearchShipPlanQtyMin:	
					SearchShipPlanQtyMin	= (ArrayList<Integer>)Definition[i][1];
					break;
				case ColSearchShipPlanQtyMax:	
					SearchShipPlanQtyMax	= (ArrayList<Integer>)Definition[i][1];
					break;
				case ColSearchPossibleQtyMin:	
					SearchPossibleQtyMin	= (ArrayList<Integer>)Definition[i][1];
					break;
				case ColSearchPossibleQtyMax:	
					SearchPossibleQtyMax	= (ArrayList<Integer>)Definition[i][1];
					break;
				case ColSearchItemName:	
					SearchItemName			= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchClItemCd:	
					SearchClItemCd			= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchJanCd:	
					SearchJanCd				= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchItemMdNo:	
					SearchItemMdNo			= (ArrayList<String>)Definition[i][1];
					break;
				default:
					break;
			}
		}
		
		Object[][] Rt	= StockRtMain(
				SearchClCd,				//荷主コード
				SearchWhCd,				//倉庫コード
				SearchClGpCD,			//荷主グループCD
				SearchLoc,				//ロケーション
				SearchType,				//ロケタイプ　0:通常　1:保管　8:入荷時　9:引当禁止
				SearchItemCd,			//商品コード
				SearchLot,				//ロット
				SearchExpdateMin,		//消費期限開始
				SearchExpdateMax,		//消費期限終了
				SearchActualDateMin,	//入荷実績日開始
				SearchActualDateMax,	//入荷実績日終了
				SearchQtyMin,			//数量最小
				SearchQtyMax,			//数量最大
				SearchShipPlanQtyMin,	//引当済数最小
				SearchShipPlanQtyMax,	//引当済数最大
				SearchPossibleQtyMin,	//出荷可能数最小
				SearchPossibleQtyMax,	//出荷可能数最大
				SearchItemName,			//商品名
				SearchClItemCd,			//荷主商品コード
				SearchJanCd,			//ソースマーク_BCD（バラ）
				SearchItemMdNo,			//商品型番
				LocExactMatch,			//ロケーション完全一致
				AllSearch,
				SortItemcdMode);
		
		return Rt;
	}
	
	private static Object[][] StockRtMain(
			ArrayList<String> SearchClCd,				//荷主コード
			ArrayList<String> SearchWhCd,				//倉庫コード
			ArrayList<String> SearchClGpCD,				//荷主グループCD
			ArrayList<String> SearchLoc,				//ロケーション
			ArrayList<Integer> SearchType,				//ロケタイプ　0:通常　1:保管　8:入荷時　9:引当禁止
			ArrayList<String> SearchItemCd,				//商品コード
			ArrayList<String> SearchLot,				//ロット
			ArrayList<String> SearchExpdateMin,			//消費期限開始
			ArrayList<String> SearchExpdateMax,			//消費期限終了
			ArrayList<String> SearchActualDateMin,		//入荷実績日開始
			ArrayList<String> SearchActualDateMax,		//入荷実績日終了
			ArrayList<Integer> SearchQtyMin,			//数量最小
			ArrayList<Integer> SearchQtyMax,			//数量最大
			ArrayList<Integer> SearchShipPlanQtyMin,	//引当済数最小
			ArrayList<Integer> SearchShipPlanQtyMax,	//引当済数最大
			ArrayList<Integer> SearchPossibleQtyMin,	//出荷可能数最小
			ArrayList<Integer> SearchPossibleQtyMax,	//出荷可能数最大
			ArrayList<String> SearchItemName,			//商品名
			ArrayList<String> SearchClItemCd,			//荷主商品コード
			ArrayList<String> SearchJanCd,				//ソースマーク_BCD（バラ）
			ArrayList<String> SearchItemMdNo,			//商品型番
			boolean LocExactMatch,	//ロケーション完全一致
			boolean AllSearch,
			boolean SortItemcdMode){
	
		//荷主商品コードを元に商品コードを絞り込む
		Object[][] SearchItemCdFromClItem = SearchItemCdFromClItem(SearchClGpCD,SearchClCd,SearchClItemCd);
		
		Object[][] rt = new Object[0][RtStockRt().length];
		boolean SearchKick = false;
		if(AllSearch) {SearchKick = true;}
		
		String sql = "select \n"
				+"(WW0015Stock.ClCd) as ClCd,\n"						//荷主コード
				+"(KM0030_CLIENTMST.CLName01) as CLName,\n"				//荷主表記名
				+"(WW0015Stock.WhCd) as WhCd,\n"						//倉庫コード
				+"(KM0010_WHMST.WHName) as ClWHName,\n"					//担当倉庫名
				+"(KM0030_CLIENTMST.ClGpCD) as ClGpCD,\n"				//荷主グループCD
				+"(KM0031_CLIENT_GROUP.ClGpName01) as ClGpName,\n"		//グループ名1
				+"(WW0015Stock.Loc) as Loc,\n"							//ロケーション
				+"(WM0010LOCATIONMST.LocName) as LocName,\n"			//ロケーション名
				+"(WM0010LOCATIONMST.LocType) as LocType,\n"			//ロケタイプ　0:通常　1:保管　8:入荷時　9:引当禁止
				+"(WW0015Stock.ItemCd) as ItemCd,\n"					//商品コード
				+"(WW0015Stock.Lot) as Lot,\n"							//ロット
				+"(WW0015Stock.Expdate) as Expdate,\n"					//消費期限
				+"(WW0015Stock.ActualDate) as ActualDate,\n"			//入荷実績日
				+"(WW0015Stock.Qty) as Qty,\n"							//数量
				+"(WW0015Stock.ShipPlanQty) as ShipPlanQty,\n"			//引当済数
				+"(WW0015Stock.PossibleQty) as PossibleQty,\n"			//出荷可能数
				+"(WW0015Stock.ItemName) as ItemName,\n"				//商品名
				+"(KM0060_ITEMMST.ItemName01) as ItemName01,\n"			//商品表記名
				+"(KM0060_ITEMMST.ItemName02) as ItemName02,\n"			//商品正式名
				+"(KM0060_ITEMMST.ItemName03) as ItemName03,\n"			//商品略名
				+"(WW0015Stock.ClItemCd) as ClItemCd,\n"				//荷主商品コード
				+"(WW0015Stock.JanCd) as JanCd,\n"						//ソースマーク_BCD（バラ）
				+"(WW0015Stock.ItemMdNo) as ItemMdNo,\n"				//商品型番
				+"(KM0061_ITEMMSTSUB.CtQty) as CtUnitQty,\n"			//カートン入数
				+"(KM0061_ITEMMSTSUB.CsQty) as CsUnitQty,\n"			//ケース入数
				+"(KM0061_ITEMMSTSUB.PlQty) as PlUnitQty,\n"			//パレット入数
				+"(KM0060_ITEMMST.UnitName) as UnitName,\n"				//商品単位
				+"(KM0061_ITEMMSTSUB.CtUnitName) as CtUnitName,\n"		//カートン商品単位
				+"(KM0061_ITEMMSTSUB.CsUnitName) as CsUnitName,\n"		//ケース商品単位
				+"(KM0061_ITEMMSTSUB.PlUnitName) as PlUnitName,\n"		//パレット商品単位
				+"(WW0015Stock.EntryDate) as EntryDate,\n"				//登録日時
				+"(WW0015Stock.UpdateDate) as UpdateDate,\n"			//更新日時
				+"(WW0015Stock.EntryUser) as EntryUser,\n"				//登録者
				+"(WW0015Stock.UpdateUser) as UpdateUser\n"				//更新者
				+ " from "+A00000_Main.MySqlDefaultSchemaWANKO+".WW0015Stock"
				+ " left outer join "+A00000_Main.MySqlDefaultSchemaWANKO+".WM0010LOCATIONMST"
				+ " on("
				+ " WW0015Stock.ClCd = WM0010LOCATIONMST.ClCd"
				+ " and WW0015Stock.WhCd = WM0010LOCATIONMST.WhCd"
				+ " and WW0015Stock.Loc= WM0010LOCATIONMST.Loc"
				+ ")\n"
				+ " left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0030_CLIENTMST"
				+ " on("
				+ " WW0015Stock.WhCd = KM0030_CLIENTMST.WHCD"
				+ " and WW0015Stock.ClCd = KM0030_CLIENTMST.cl_cd"
				+ ")\n"
				+ " left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP"
				+ " on("
				+ " KM0030_CLIENTMST.ClGpCD = KM0031_CLIENT_GROUP.ClGpCD"
				+ ")\n"
				+ " left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST"
				+ " on("
				+ " WW0015Stock.WhCd = KM0010_WHMST.WHCD"
				+ ")\n"
				+ " left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0060_ITEMMST"
				+ " on("
				+ " KM0030_CLIENTMST.ClGpCD = KM0060_ITEMMST.ClGpCd"
				+ " and WW0015Stock.ItemCd = KM0060_ITEMMST.ItemCd"
				+ ")\n"
				+ " left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0061_ITEMMSTSUB \n"
				+ " on("
				+ " KM0060_ITEMMST.ClGpCd = KM0061_ITEMMSTSUB.ClGpCd"
				+ " and KM0060_ITEMMST.ItemCd = KM0061_ITEMMSTSUB.ItemCd"
				+ ")\n"
				+ " where 1=1\n";
		
		if(null!=SearchClItemCd && 0<SearchClItemCd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchClItemCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KM0060_ITEMMST.ClItemCd = ?";
			}
			if(null!=SearchItemCdFromClItem && 0< SearchItemCdFromClItem.length) {
				for(int i=0;i<SearchItemCdFromClItem.length;i++) {
					sql = sql + " or (WW0015Stock.ClCd = ?";
					sql = sql + "  and WW0015Stock.ItemCd = ?)";
				}
			}
			sql = sql + ")\n";
		}
		if(null!=SearchClCd && 0<SearchClCd.size()){							//荷主コード
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchClCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0015Stock.ClCd = ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchWhCd && 0<SearchWhCd.size()){							//倉庫コード
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchWhCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0015Stock.WhCd = ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchClGpCD && 0<SearchClGpCD.size()){						//荷主グループCD
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchClGpCD.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KM0030_CLIENTMST.ClGpCD = ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchLoc && 0<SearchLoc.size()){								//ロケーション
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchLoc.size();i++){
				if(0<i){sql = sql + " or ";}
				if(LocExactMatch) {
					sql = sql + " WW0015Stock.Loc = ?";
				}else {
					sql = sql + " WW0015Stock.Loc Like ?";
				}
			}
			sql = sql + ")\n";
		}
		if(null!=SearchType && 0<SearchType.size()){							//ロケタイプ　0:通常　1:保管　8:入荷時　9:引当禁止
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchType.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WM0010LOCATIONMST.LocType = ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchItemCd && 0<SearchItemCd.size()){						//商品コード
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchItemCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0015Stock.ItemCd = ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchLot && 0<SearchLot.size()){								//ロット
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchLot.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0015Stock.Lot = ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchExpdateMin && 0<SearchExpdateMin.size()){				//消費期限最小
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchExpdateMin.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0015Stock.Expdate >= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchExpdateMax && 0<SearchExpdateMax.size()){				//消費期限最大
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchExpdateMax.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0015Stock.Expdate < ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchActualDateMin && 0<SearchActualDateMin.size()){			//入荷実績日最小
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchActualDateMin.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0015Stock.ActualDate >= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchActualDateMax && 0<SearchActualDateMax.size()){			//入荷実績日最大
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchActualDateMax.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0015Stock.ActualDate < ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchQtyMin && 0<SearchQtyMin.size()){						//数量最小
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchQtyMin.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0015Stock.Qty >= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchQtyMax && 0<SearchQtyMax.size()){						//数量最大
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchQtyMax.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0015Stock.Qty <= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchShipPlanQtyMin && 0<SearchShipPlanQtyMin.size()){		//引当済数最小
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchShipPlanQtyMin.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0015Stock.ShipPlanQty >= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchShipPlanQtyMax && 0<SearchShipPlanQtyMax.size()){		//引当済数最大
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchShipPlanQtyMax.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0015Stock.ShipPlanQty <= ?";
			}
			sql = sql + ")\n";
		}
		
		if(null!=SearchPossibleQtyMin && 0<SearchPossibleQtyMin.size()){		//出荷可能数最小
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchPossibleQtyMin.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0015Stock.PossibleQty >= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchPossibleQtyMax && 0<SearchPossibleQtyMax.size()){		//出荷可能数最大
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchPossibleQtyMax.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0015Stock.PossibleQty <= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchItemName && 0<SearchItemName.size()){					//商品名
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchItemName.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0015Stock.ItemName Like ?";
				sql = sql + " or KM0060_ITEMMST.ItemName01 Like ?";
				sql = sql + " or KM0060_ITEMMST.ItemName02 Like ?";
				sql = sql + " or KM0060_ITEMMST.ItemName03 Like ?";
			}
			sql = sql + ")\n";
		}
		
		if(null!=SearchJanCd && 0<SearchJanCd.size()){							//ソースマーク_BCD（バラ）
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchJanCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0015Stock.JanCd = ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchItemMdNo && 0<SearchItemMdNo.size()){					//商品型番
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchItemMdNo.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0015Stock.ItemMdNo = ?";
			}
			sql = sql + ")\n";
		}
		
		if(SortItemcdMode) {
			sql = sql + " order by WW0015Stock.ClCd,WW0015Stock.ItemCd,WW0015Stock.Expdate,WW0015Stock.Lot,WW0015Stock.ActualDate,WW0015Stock.Loc";
		}else {
			sql = sql + " order by WW0015Stock.ClCd,WW0015Stock.Loc,WW0015Stock.ItemCd,WW0015Stock.Expdate,WW0015Stock.Lot,WW0015Stock.ActualDate";
		}
		//System.out.println(sql);
		if(SearchKick) {
			A100_DbConnect.DB_CONN("NYANKO");
			ResultSet rset01 = null;
			PreparedStatement stmt01 = null;
			try {
				stmt01 = A100_DbConnect.conn.prepareStatement(sql);
				int StmtCount = 0;
				
				if(null!=SearchClItemCd && 0<SearchClItemCd.size()){
					for(int i=0;i<SearchClItemCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchClItemCd.get(i)+"");
					}
					if(null!=SearchItemCdFromClItem && 0< SearchItemCdFromClItem.length) {
						for(int i=0;i<SearchItemCdFromClItem.length;i++) {
							StmtCount = StmtCount+1;
							stmt01.setString(StmtCount, ""+SearchItemCdFromClItem[i][M100_ItemComversionMstRt.ColClCd]+"");
							StmtCount = StmtCount+1;
							stmt01.setString(StmtCount, ""+SearchItemCdFromClItem[i][M100_ItemComversionMstRt.ColItemCd]+"");
						}
					}
				}
				if(null!=SearchClCd && 0<SearchClCd.size()){							//荷主コード
					for(int i=0;i<SearchClCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchClCd.get(i)+"");
					}
				}
				if(null!=SearchWhCd && 0<SearchWhCd.size()){							//倉庫コード
					for(int i=0;i<SearchWhCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchWhCd.get(i)+"");
					}
				}
				if(null!=SearchClGpCD && 0<SearchClGpCD.size()){						//荷主グループCD
					for(int i=0;i<SearchClGpCD.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchClGpCD.get(i)+"");
					}
				}
				if(null!=SearchLoc && 0<SearchLoc.size()){								//ロケーション
					for(int i=0;i<SearchLoc.size();i++){
						if(LocExactMatch) {
							StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchLoc.get(i)+"");
						}else {
							StmtCount = StmtCount+1;
							stmt01.setString(StmtCount, ""+SearchLoc.get(i)+"%");
						}
					}
				}
				if(null!=SearchType && 0<SearchType.size()){							//ロケタイプ　0:通常　1:保管　8:入荷時　9:引当禁止
					for(int i=0;i<SearchType.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchType.get(i)+"");
					}
				}
				if(null!=SearchItemCd && 0<SearchItemCd.size()){						//商品コード
					for(int i=0;i<SearchItemCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchItemCd.get(i)+"");
					}
				}
				if(null!=SearchLot && 0<SearchLot.size()){								//ロット
					for(int i=0;i<SearchLot.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchLot.get(i)+"");
					}
				}
				if(null!=SearchExpdateMin && 0<SearchExpdateMin.size()){				//消費期限最小
					for(int i=0;i<SearchExpdateMin.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchExpdateMin.get(i)+"");
					}
				}
				if(null!=SearchExpdateMax && 0<SearchExpdateMax.size()){				//消費期限最大
					for(int i=0;i<SearchExpdateMax.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchExpdateMax.get(i)+"");
					}
				}
				if(null!=SearchActualDateMin && 0<SearchActualDateMin.size()){			//入荷実績日最小
					for(int i=0;i<SearchActualDateMin.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchActualDateMin.get(i)+"");
					}
				}
				if(null!=SearchActualDateMax && 0<SearchActualDateMax.size()){			//入荷実績日最大
					for(int i=0;i<SearchActualDateMax.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchActualDateMax.get(i)+"");
					}
				}
				if(null!=SearchQtyMin && 0<SearchQtyMin.size()){						//数量最小
					for(int i=0;i<SearchQtyMin.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchQtyMin.get(i)+"");
					}
				}
				if(null!=SearchQtyMax && 0<SearchQtyMax.size()){						//数量最大
					for(int i=0;i<SearchQtyMax.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchQtyMax.get(i)+"");
					}
				}
				if(null!=SearchShipPlanQtyMin && 0<SearchShipPlanQtyMin.size()){		//引当済数最小
					for(int i=0;i<SearchShipPlanQtyMin.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchShipPlanQtyMin.get(i)+"");
					}
				}
				if(null!=SearchShipPlanQtyMax && 0<SearchShipPlanQtyMax.size()){		//引当済数最大
					for(int i=0;i<SearchShipPlanQtyMax.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchShipPlanQtyMax.get(i)+"");
					}
				}
				
				if(null!=SearchPossibleQtyMin && 0<SearchPossibleQtyMin.size()){		//出荷可能数最小
					for(int i=0;i<SearchPossibleQtyMin.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchPossibleQtyMin.get(i)+"");
					}
				}
				if(null!=SearchPossibleQtyMax && 0<SearchPossibleQtyMax.size()){		//出荷可能数最大
					for(int i=0;i<SearchPossibleQtyMax.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchPossibleQtyMax.get(i)+"");
					}
				}
				if(null!=SearchItemName && 0<SearchItemName.size()){					//商品名
					for(int i=0;i<SearchItemName.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchItemName.get(i)+"%");
						
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchItemName.get(i)+"%");
						
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchItemName.get(i)+"%");
						
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchItemName.get(i)+"%");
					}
				}
				
				if(null!=SearchJanCd && 0<SearchJanCd.size()){							//ソースマーク_BCD（バラ）
					for(int i=0;i<SearchJanCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchJanCd.get(i)+"");
					}
				}
				if(null!=SearchItemMdNo && 0<SearchItemMdNo.size()){					//商品型番
					for(int i=0;i<SearchItemMdNo.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchItemMdNo.get(i)+"");
					}
				}
				
				rset01 = stmt01.executeQuery();
				
				int counter = 0;
				rset01.beforeFirst();
				while (rset01.next()) {
					counter=counter+1;
				}
				rt = new Object[counter][RtStockRt().length];
				counter = 0;
				rset01.beforeFirst();
				while (rset01.next()) {
					//在庫の戻り値はいろいろ計算するのでB100_RtObjectCreateではなく個別に作成
					
					if(null==rset01.getString("ClCd"			)){rt[counter][ColClCd]			="";}else{rt[counter][ColClCd]			=rset01.getString("ClCd");}		//荷主コード
					if(null==rset01.getString("CLName"			)){rt[counter][ColCLName]			="";}else{rt[counter][ColCLName]		=rset01.getString("CLName");}	//荷主表記名
					if(null==rset01.getString("WhCd"			)){rt[counter][ColWhCd]			="";}else{rt[counter][ColWhCd]			=rset01.getString("WhCd");}		//倉庫コード
					if(null==rset01.getString("ClWHName"		)){rt[counter][ColClWHName]		="";}else{rt[counter][ColClWHName]		=rset01.getString("ClWHName");}	//担当倉庫名
					if(null==rset01.getString("ClGpCD"			)){rt[counter][ColClGpCD]			="";}else{rt[counter][ColClGpCD]		=rset01.getString("ClGpCD");}	//荷主グループCD
					if(null==rset01.getString("ClGpName"		)){rt[counter][ColClGpName]		="";}else{rt[counter][ColClGpName]		=rset01.getString("ClGpName");}	//グループ名1
					if(null==rset01.getString("Loc"				)){rt[counter][ColLoc]				="";}else{rt[counter][ColLoc]			=rset01.getString("Loc");}		//ロケーション
					if(null==rset01.getString("LocName"			)){rt[counter][ColLocName]			="";}else{rt[counter][ColLocName]		=rset01.getString("LocName");}	//ロケーション名
					rt[counter][ColType]						=rset01.getInt("LocType");				//ロケタイプ　0:通常　1:保管　8:入荷時　9:引当禁止
					if(null==rset01.getString("ItemCd"			)){rt[counter][ColItemCd]			="";}else{rt[counter][ColItemCd]		=rset01.getString("ItemCd");}	//商品コード
					if(null==rset01.getString("Lot"				)){rt[counter][ColLot]				="";}else{rt[counter][ColLot]			=rset01.getString("Lot");}		//ロット
					if(null==rset01.getTimestamp("Expdate"		)){rt[counter][ColExpdate]			="";}else{rt[counter][ColExpdate]		=B100_DateTimeControl.dtmString2(rset01.getTimestamp("Expdate"))[0];}		//消費期限
					if(null==rset01.getTimestamp("ActualDate"	)){rt[counter][ColActualDate]		="";}else{rt[counter][ColActualDate]	=B100_DateTimeControl.dtmString2(rset01.getTimestamp("ActualDate"))[0];}		//入荷実績日
					rt[counter][ColQty]						=rset01.getInt("Qty");				//総数量
					rt[counter][ColShipPlanQty]				=rset01.getInt("ShipPlanQty");		//引当済総数
					rt[counter][ColPossibleQty]				=rset01.getInt("PossibleQty");		//出荷可能総数
					if(null==rset01.getString("ItemName"		)){rt[counter][ColItemName]		="";}else{rt[counter][ColItemName]		=rset01.getString("ItemName");}		//商品名
					if(null==rset01.getString("ItemName01"		)){rt[counter][ColItemName01]		="";}else{rt[counter][ColItemName01]	=rset01.getString("ItemName01");}	//商品表記名
					if(null==rset01.getString("ItemName02"		)){rt[counter][ColItemName02]		="";}else{rt[counter][ColItemName02]	=rset01.getString("ItemName02");}	//商品正式名
					if(null==rset01.getString("ItemName03"		)){rt[counter][ColItemName03]		="";}else{rt[counter][ColItemName03]	=rset01.getString("ItemName03");}	//商品略名
					if(null==rset01.getString("ClItemCd"		)){rt[counter][ColClItemCd]		="";}else{rt[counter][ColClItemCd]		=rset01.getString("ClItemCd");}		//荷主商品コード
					if(null==rset01.getString("JanCd"			)){rt[counter][ColJanCd]			="";}else{rt[counter][ColJanCd]		=rset01.getString("JanCd");}		//ソースマーク_BCD（バラ）
					if(null==rset01.getString("ItemMdNo"		)){rt[counter][ColItemMdNo]		="";}else{rt[counter][ColItemMdNo]		=rset01.getString("ItemMdNo");}		//商品型番
					rt[counter][ColCtUnitQty]					=rset01.getInt("CtUnitQty");		//カートン入数
					rt[counter][ColCsUnitQty]					=rset01.getInt("CsUnitQty");		//ケース入数
					rt[counter][ColPlUnitQty]					=rset01.getInt("PlUnitQty");		//パレット入数
					if(null==rset01.getString("UnitName"		)){rt[counter][ColUnitName]		="";}else{rt[counter][ColUnitName]		=rset01.getString("UnitName");}		//商品単位
					if(null==rset01.getString("CtUnitName"		)){rt[counter][ColCtUnitName]		="";}else{rt[counter][ColCtUnitName]	=rset01.getString("CtUnitName");}	//カートン商品単位
					if(null==rset01.getString("CsUnitName"		)){rt[counter][ColCsUnitName]		="";}else{rt[counter][ColCsUnitName]	=rset01.getString("CsUnitName");}	//ケース商品単位
					if(null==rset01.getString("PlUnitName"		)){rt[counter][ColPlUnitName]		="";}else{rt[counter][ColPlUnitName]	=rset01.getString("PlUnitName");}	//パレット商品単位
					if(null==rset01.getTimestamp("EntryDate"	)){rt[counter][ColEntryDate]		="";}else{rt[counter][ColEntryDate]	=B100_DateTimeControl.dtmString2(rset01.getTimestamp("EntryDate"))[1];}		//登録日時
					if(null==rset01.getTimestamp("UpdateDate"	)){rt[counter][ColUpdateDate]		="";}else{rt[counter][ColUpdateDate]	=B100_DateTimeControl.dtmString2(rset01.getTimestamp("UpdateDate"))[1];}		//更新日時
					if(null==rset01.getString("EntryUser"		)){rt[counter][ColEntryUser]		="";}else{rt[counter][ColEntryUser]	=rset01.getString("EntryUser");}	//登録者
					if(null==rset01.getString("UpdateUser"		)){rt[counter][ColUpdateUser]		="";}else{rt[counter][ColUpdateUser]	=rset01.getString("UpdateUser");}	//更新者
					
					rt[counter][ColBrQty]						=(int)rt[counter][ColQty];	//バラ数量
					rt[counter][ColBrShipPlanQty]				=(int)rt[counter][ColShipPlanQty];	//引当済バラ数
					rt[counter][ColBrPossibleQty]				=(int)rt[counter][ColPossibleQty];	//出荷可能バラ数
					rt[counter][ColCtQty]						=(int)0;	//カートン数量
					rt[counter][ColCtShipPlanQty]				=(int)0;	//引当済カートン数
					rt[counter][ColCtPossibleQty]				=(int)0;	//出荷可能カートン数
					rt[counter][ColCsQty]						=(int)0;	//ケース数量
					rt[counter][ColCsShipPlanQty]				=(int)0;	//引当済ケース数
					rt[counter][ColCsPossibleQty]				=(int)0;	//出荷可能ケース数
					rt[counter][ColPlQty]						=(int)0;	//パレット数量
					rt[counter][ColPlShipPlanQty]				=(int)0;	//引当済パレット数
					rt[counter][ColPlPossibleQty]				=(int)0;	//出荷可能パレット数
					
					if(0<(int)rt[counter][ColPlUnitQty]) {
						int UQ = (int)rt[counter][ColPlUnitQty];
						
						int SetQty 			= (int)((int)rt[counter][ColBrQty]/UQ);
						int SetShipPlanQty 	= (int)((int)rt[counter][ColBrShipPlanQty]/UQ);
						int SetPossibleQty 	= (int)((int)rt[counter][ColBrPossibleQty]/UQ);
						
						int SetBrQty 			= (int)((int)rt[counter][ColBrQty]%UQ);
						int SetBrShipPlanQty 	= (int)((int)rt[counter][ColBrShipPlanQty]%UQ);
						int SetBrPossibleQty 	= (int)((int)rt[counter][ColBrPossibleQty]%UQ);
						
						rt[counter][ColPlQty]						=SetQty;			//パレット数量
						rt[counter][ColPlShipPlanQty]				=SetShipPlanQty;	//引当済パレット数
						rt[counter][ColPlPossibleQty]				=SetPossibleQty;	//出荷可能パレット数
						
						rt[counter][ColBrQty]						=SetBrQty;			//バラ数量
						rt[counter][ColBrShipPlanQty]				=SetBrShipPlanQty;	//引当済バラ数
						rt[counter][ColBrPossibleQty]				=SetBrPossibleQty;	//出荷可能バラ数
					}
					
					if(0<(int)rt[counter][ColCsUnitQty]) {
						int UQ = (int)rt[counter][ColCsUnitQty];
						
						int SetQty 			= (int)((int)rt[counter][ColBrQty]/UQ);
						int SetShipPlanQty 	= (int)((int)rt[counter][ColBrShipPlanQty]/UQ);
						int SetPossibleQty 	= (int)((int)rt[counter][ColBrPossibleQty]/UQ);
						
						int SetBrQty 			= (int)((int)rt[counter][ColBrQty]%UQ);
						int SetBrShipPlanQty 	= (int)((int)rt[counter][ColBrShipPlanQty]%UQ);
						int SetBrPossibleQty 	= (int)((int)rt[counter][ColBrPossibleQty]%UQ);
						
						rt[counter][ColCsQty]						=SetQty;			//ケース数量
						rt[counter][ColCsShipPlanQty]				=SetShipPlanQty;	//引当済ケース数
						rt[counter][ColCsPossibleQty]				=SetPossibleQty;	//出荷可能ケース数
						
						rt[counter][ColBrQty]						=SetBrQty;			//バラ数量
						rt[counter][ColBrShipPlanQty]				=SetBrShipPlanQty;	//引当済バラ数
						rt[counter][ColBrPossibleQty]				=SetBrPossibleQty;	//出荷可能バラ数
					}
					
					if(0<(int)rt[counter][ColCtUnitQty]) {
						int UQ = (int)rt[counter][ColCtUnitQty];
						
						int SetQty 			= (int)((int)rt[counter][ColBrQty]/UQ);
						int SetShipPlanQty 	= (int)((int)rt[counter][ColBrShipPlanQty]/UQ);
						int SetPossibleQty 	= (int)((int)rt[counter][ColBrPossibleQty]/UQ);
						
						int SetBrQty 			= (int)((int)rt[counter][ColBrQty]%UQ);
						int SetBrShipPlanQty 	= (int)((int)rt[counter][ColBrShipPlanQty]%UQ);
						int SetBrPossibleQty 	= (int)((int)rt[counter][ColBrPossibleQty]%UQ);
						
						rt[counter][ColCtQty]						=SetQty;			//カートン数量
						rt[counter][ColCtShipPlanQty]				=SetShipPlanQty;	//引当済カートン数
						rt[counter][ColCtPossibleQty]				=SetPossibleQty;	//出荷可能カートン数
						
						rt[counter][ColBrQty]						=SetBrQty;			//バラ数量
						rt[counter][ColBrShipPlanQty]				=SetBrShipPlanQty;	//引当済バラ数
						rt[counter][ColBrPossibleQty]				=SetBrPossibleQty;	//出荷可能バラ数
					}
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
			A100_DbConnect.close();
		}
		return rt;
	}
	private static Object[][] SearchItemCdFromClItem(ArrayList<String> SearchClGpCd,ArrayList<String> SearchClCd,ArrayList<String> SearchClItemCd){
		//ArrayList<String> SearchClGpCd = new ArrayList<String>();		//荷主グループコード
		//ArrayList<String> SearchClCd = new ArrayList<String>();			//荷主コード
		ArrayList<String> SearchItemCd = new ArrayList<String>();		//商品コード
		//ArrayList<String> SearchClItemCd = new ArrayList<String>();	//荷主商品コード
		ArrayList<String> SearchItemName = new ArrayList<String>();		//商品名
		boolean AllSearch = false;
		Object[][] ItemComversionMstRt = null;
		if(null!=SearchClItemCd && 0<SearchClItemCd.size()) {
			ItemComversionMstRt = M100_ItemComversionMstRt.ItemComversionMstRt(
					SearchClGpCd,			//荷主グループコード
					SearchClCd,				//荷主コード
					SearchItemCd,			//商品コード
					SearchClItemCd,			//荷主商品コード
					SearchItemName,			//商品名
					AllSearch);
		}
		return ItemComversionMstRt;
	}
}