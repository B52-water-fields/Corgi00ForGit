import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class T00030StockRt{
	/*
	コピペ用
	ArrayList<String> SearchClCd				= new ArrayList<String>();			//荷主コード
	ArrayList<String> SearchWhCd				= new ArrayList<String>();			//倉庫コード
	ArrayList<String> SearchClGpCD				= new ArrayList<String>();			//荷主グループCD
	ArrayList<String> SearchLoc					= new ArrayList<String>();			//ロケーション
	ArrayList<int> 	　SearchType				= new ArrayList<String>();			//ロケタイプ　0:通常　1:保管　8:入荷時　9:引当禁止
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
	
	Object[][] StockRt= T00030StockRt.StockRt(
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
							
							
	String GetClCd			= (String)StockRt[i][T00030StockRt.ColClCd];			//荷主コード
	String GetCLName		= (String)StockRt[i][T00030StockRt.ColCLName];			//荷主名1
	String GetWhCd			= (String)StockRt[i][T00030StockRt.ColWhCd];			//倉庫コード
	String GetClWHName		= (String)StockRt[i][T00030StockRt.ColClWHName];		//担当倉庫名
	String GetClGpCD		= (String)StockRt[i][T00030StockRt.ColClGpCD];			//荷主グループCD
	String GetClGpName		= (String)StockRt[i][T00030StockRt.ColClGpName];		//グループ名1
	String GetLoc			= (String)StockRt[i][T00030StockRt.ColLoc];				//ロケーション
	String GetLocName		= (String)StockRt[i][T00030StockRt.ColLocName];			//ロケーション名
	int GetType				= (int)StockRt[i][T00030StockRt.ColType];				//ロケタイプ
	String GetItemCd		= (String)StockRt[i][T00030StockRt.ColItemCd];			//商品コード
	String GetLot			= (String)StockRt[i][T00030StockRt.ColLot];				//ロット
	String GetExpdate		= (String)StockRt[i][T00030StockRt.ColExpdate];			//消費期限
	String GetActualDate	= (String)StockRt[i][T00030StockRt.ColActualDate];		//入荷実績日
	int GetQty				= (int)StockRt[i][T00030StockRt.ColQty];				//総数量
	int GetShipPlanQty		= (int)StockRt[i][T00030StockRt.ColShipPlanQty];		//引当済総数
	int GetPossibleQty		= (int)StockRt[i][T00030StockRt.ColPossibleQty];		//出荷可能総数
	String GetItemName		= (String)StockRt[i][T00030StockRt.ColItemName];		//商品名
	String GetItemName01	= (String)StockRt[i][T00030StockRt.ColItemName01];		//商品名1
	String GetItemName02	= (String)StockRt[i][T00030StockRt.ColItemName02];		//商品名2
	String GetItemName03	= (String)StockRt[i][T00030StockRt.ColItemName03];		//商品名3
	String GetClItemCd		= (String)StockRt[i][T00030StockRt.ColClItemCd];		//荷主商品コード
	String GetJanCd			= (String)StockRt[i][T00030StockRt.ColJanCd];			//ソースマーク_BCD（バラ）
	String GetItemMdNo		= (String)StockRt[i][T00030StockRt.ColItemMdNo];		//商品型番
	int GetCtUnitQty		= (int)StockRt[i][T00030StockRt.ColCtUnitQty];			//カートン入数
	int GetCsUnitQty		= (int)StockRt[i][T00030StockRt.ColCsUnitQty];			//ケース入数
	int GetPlUnitQty		= (int)StockRt[i][T00030StockRt.ColPlUnitQty];			//パレット入数
	String GetUnitName		= (String)StockRt[i][T00030StockRt.ColUnitName];		//商品単位
	String GetCtUnitName	= (String)StockRt[i][T00030StockRt.ColCtUnitName];		//カートン商品単位
	String GetCsUnitName	= (String)StockRt[i][T00030StockRt.ColCsUnitName];		//ケース商品単位
	String GetPlUnitName	= (String)StockRt[i][T00030StockRt.ColPlUnitName];		//パレット商品単位
	String GetEntryDate		= (String)StockRt[i][T00030StockRt.ColEntryDate];		//登録日時
	String GetUpdateDate	= (String)StockRt[i][T00030StockRt.ColUpdateDate];		//更新日時
	String GetEntryUser		= (String)StockRt[i][T00030StockRt.ColEntryUser];		//登録者
	String GetUpdateUser	= (String)StockRt[i][T00030StockRt.ColUpdateUser];		//更新者
	int GetBrQty			= (int)StockRt[i][T00030StockRt.ColBrQty];				//バラ数量
	int GetBrShipPlanQty	= (int)StockRt[i][T00030StockRt.ColBrShipPlanQty];		//引当済バラ数
	int GetBrPossibleQty	= (int)StockRt[i][T00030StockRt.ColBrPossibleQty];		//出荷可能バラ数
	int GetCtQty			= (int)StockRt[i][T00030StockRt.ColCtQty];				//カートン数量
	int GetCtShipPlanQty	= (int)StockRt[i][T00030StockRt.ColCtShipPlanQty];		//引当済カートン数
	int GetCtPossibleQty	= (int)StockRt[i][T00030StockRt.ColCtPossibleQty];		//出荷可能カートン数
	int GetCsQty			= (int)StockRt[i][T00030StockRt.ColCsQty];				//ケース数量
	int GetCsShipPlanQty	= (int)StockRt[i][T00030StockRt.ColCsShipPlanQty];		//引当済ケース数
	int GetCsPossibleQty	= (int)StockRt[i][T00030StockRt.ColCsPossibleQty];		//出荷可能ケース数
	int GetPlQty			= (int)StockRt[i][T00030StockRt.ColPlQty];				//パレット数量
	int GetPlShipPlanQty	= (int)StockRt[i][T00030StockRt.ColPlShipPlanQty];		//引当済パレット数
	int GetPlPossibleQty	= (int)StockRt[i][T00030StockRt.ColPlPossibleQty];		//出荷可能パレット数
	
	*/
	
	static final int ColClCd			= (int)0;		//荷主コード
	static final int ColCLName			= (int)1;		//荷主名1
	static final int ColWhCd			= (int)2;		//倉庫コード
	static final int ColClWHName		= (int)3;		//担当倉庫名
	static final int ColClGpCD			= (int)4;		//荷主グループCD
	static final int ColClGpName		= (int)5;		//グループ名1
	static final int ColLoc			= (int)6;		//ロケーション
	static final int ColLocName		= (int)7;		//ロケーション名
	static final int ColType			= (int)8;		//ロケタイプ
	static final int ColItemCd			= (int)9;		//商品コード
	static final int ColLot			= (int)10;		//ロット
	static final int ColExpdate		= (int)11;		//消費期限
	static final int ColActualDate	= (int)12;		//入荷実績日
	static final int ColQty			= (int)13;		//数量
	static final int ColShipPlanQty	= (int)14;		//引当済数
	static final int ColPossibleQty	= (int)15;		//出荷可能数
	static final int ColItemName		= (int)16;		//商品名
	static final int ColItemName01	= (int)17;		//商品名1
	static final int ColItemName02	= (int)18;		//商品名2
	static final int ColItemName03	= (int)19;		//商品名3
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
	
	public static Object[][] RtStockRt(){
		Object[][] RtStockRt = {
				 {"ClCd"			,ColClCd			,"String"	,"荷主コード"					,"key"}
				,{"CLName"			,ColCLName			,"String"	,"荷主名1"						,""}
				,{"WhCd"			,ColWhCd			,"String"	,"倉庫コード"					,"key"}
				,{"ClWHName"		,ColClWHName		,"String"	,"担当倉庫名"					,""}
				,{"ClGpCD"			,ColClGpCD			,"String"	,"荷主グループCD"				,""}
				,{"ClGpName"		,ColClGpName		,"String"	,"グループ名1"					,""}
				,{"Loc"				,ColLoc			,"String"	,"ロケーション"					,"key"}
				,{"LocName"			,ColLocName		,"String"	,"ロケーション名"				,""}
				,{"Type"			,ColType			,"int"		,"ロケタイプ"					,""}
				,{"ItemCd"			,ColItemCd			,"String"	,"商品コード"					,"key"}
				,{"Lot"				,ColLot			,"String"	,"ロット"						,"key"}
				,{"Expdate"			,ColExpdate		,"DateTime"	,"消費期限"						,"key"}
				,{"ActualDate"		,ColActualDate	,"DateTime"	,"入荷実績日"					,"key"}
				,{"Qty"				,ColQty			,"int"		,"総数量"						,""}
				,{"ShipPlanQty"		,ColShipPlanQty	,"int"		,"引当済総数"					,""}
				,{"PossibleQty"		,ColPossibleQty	,"int"		,"出荷可能総数"					,""}
				,{"ItemName"		,ColItemName		,"String"	,"商品名"						,""}
				,{"ItemName01"		,ColItemName01	,"String"	,"商品名1"						,""}
				,{"ItemName02"		,ColItemName02	,"String"	,"商品名2"						,""}
				,{"ItemName03"		,ColItemName03	,"String"	,"商品名3"						,""}
				,{"ClItemCd"		,ColClItemCd		,"String"	,"荷主商品コード"				,""}
				,{"JanCd"			,ColJanCd			,"String"	,"ソースマーク_BCD（バラ）"		,""}
				,{"ItemMdNo"		,ColItemMdNo		,"String"	,"商品型番"						,""}
				,{"CtUnitQty"		,ColCtUnitQty		,"int"		,"カートン入数"					,""}
				,{"CsUnitQty"		,ColCsUnitQty		,"int"		,"ケース入数"					,""}
				,{"PlUnitQty"		,ColPlUnitQty		,"int"		,"パレット入数"					,""}
				,{"UnitName"		,ColUnitName		,"String"	,"商品単位"						,""}
				,{"CtUnitName"		,ColCtUnitName	,"String"	,"カートン商品単位"				,""}
				,{"CsUnitName"		,ColCsUnitName	,"String"	,"ケース商品単位"				,""}
				,{"PlUnitName"		,ColPlUnitName	,"String"	,"パレット商品単位"				,""}
				,{"EntryDate"		,ColEntryDate		,"DateTime"	,"登録日時"						,""}
				,{"UpdateDate"		,ColUpdateDate	,"DateTime"	,"更新日時"						,""}
				,{"EntryUser"		,ColEntryUser		,"String"	,"登録者"						,""}
				,{"UpdateUser"		,ColUpdateUser	,"String"	,"更新者"						,""}
				,{"BrQty"			,ColBrQty			,"int"		,"バラ数量"						,""}
				,{"BrShipPlanQty"	,ColBrShipPlanQty	,"int"		,"引当済バラ数"					,""}
				,{"BrPossibleQty"	,ColBrPossibleQty	,"int"		,"出荷可能バラ数"				,""}
				,{"CtQty"			,ColCtQty			,"int"		,"カートン数量"					,""}
				,{"CtShipPlanQty"	,ColCtShipPlanQty	,"int"		,"引当済カートン数"				,""}
				,{"CtPossibleQty"	,ColCtPossibleQty	,"int"		,"出荷可能カートン数"			,""}
				,{"CsQty"			,ColCsQty			,"int"		,"ケース数量"					,""}
				,{"CsShipPlanQty"	,ColCsShipPlanQty	,"int"		,"引当済ケース数"				,""}
				,{"CsPossibleQty"	,ColCsPossibleQty	,"int"		,"出荷可能ケース数"				,""}
				,{"PlQty"			,ColPlQty			,"int"		,"パレット数量"					,""}
				,{"PlShipPlanQty"	,ColPlShipPlanQty	,"int"		,"引当済パレット数"				,""}
				,{"PlPossibleQty"	,ColPlPossibleQty	,"int"		,"出荷可能パレット数"			,""}
				};
		return RtStockRt;
	}
	
	public static Object[][] StockRt(
			ArrayList<String> SearchClCd,				//荷主コード
			ArrayList<String> SearchWhCd,				//倉庫コード
			ArrayList<String> SearchClGpCD,				//荷主グループCD
			ArrayList<String> SearchLoc,				//ロケーション
			ArrayList<String> SearchType,				//ロケタイプ　0:通常　1:保管　8:入荷時　9:引当禁止
			ArrayList<String> SearchItemCd,				//商品コード
			ArrayList<String> SearchLot,				//ロット
			ArrayList<String> SearchExpdateMin,			//消費期限最小
			ArrayList<String> SearchExpdateMax,			//消費期限最大
			ArrayList<String> SearchActualDateMin,		//入荷実績日最小
			ArrayList<String> SearchActualDateMax,		//入荷実績日最大
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
		SearchClCd				= B00150ArrayListControl.ArryListStringUniqueList(SearchClCd);				//荷主コード
		SearchWhCd				= B00150ArrayListControl.ArryListStringUniqueList(SearchWhCd);				//倉庫コード
		SearchClGpCD			= B00150ArrayListControl.ArryListStringUniqueList(SearchClGpCD);			//荷主グループCD
		SearchLoc				= B00150ArrayListControl.ArryListStringUniqueList(SearchLoc);				//ロケーション
		SearchType				= B00150ArrayListControl.ArryListStringUniqueList(SearchType);				//ロケタイプ　0:通常　1:保管　8:入荷時　9:引当禁止
		SearchItemCd			= B00150ArrayListControl.ArryListStringUniqueList(SearchItemCd);			//商品コード
		SearchLot				= B00150ArrayListControl.ArryListStringUniqueList(SearchLot);				//ロット
		SearchExpdateMin		= B00150ArrayListControl.ArryListStringUniqueList(SearchExpdateMin);		//消費期限最小
		SearchExpdateMax		= B00150ArrayListControl.ArryListStringUniqueList(SearchExpdateMax);		//消費期限最大
		SearchActualDateMin		= B00150ArrayListControl.ArryListStringUniqueList(SearchActualDateMin);		//入荷実績日最小
		SearchActualDateMax		= B00150ArrayListControl.ArryListStringUniqueList(SearchActualDateMax);		//入荷実績日最大
		SearchQtyMin			= B00150ArrayListControl.ArryListIntegerUniqueList(SearchQtyMin);			//数量最小
		SearchQtyMax			= B00150ArrayListControl.ArryListIntegerUniqueList(SearchQtyMax);			//数量最大
		SearchShipPlanQtyMin	= B00150ArrayListControl.ArryListIntegerUniqueList(SearchShipPlanQtyMin);	//引当済数最小
		SearchShipPlanQtyMax	= B00150ArrayListControl.ArryListIntegerUniqueList(SearchShipPlanQtyMax);	//引当済数最大
		SearchPossibleQtyMin	= B00150ArrayListControl.ArryListIntegerUniqueList(SearchPossibleQtyMin);	//出荷可能数最小
		SearchPossibleQtyMax	= B00150ArrayListControl.ArryListIntegerUniqueList(SearchPossibleQtyMax);	//出荷可能数最大
		SearchItemName			= B00150ArrayListControl.ArryListStringUniqueList(SearchItemName);			//商品名
		SearchClItemCd			= B00150ArrayListControl.ArryListStringUniqueList(SearchClItemCd);			//荷主商品コード
		SearchJanCd				= B00150ArrayListControl.ArryListStringUniqueList(SearchJanCd);				//ソースマーク_BCD（バラ）
		SearchItemMdNo			= B00150ArrayListControl.ArryListStringUniqueList(SearchItemMdNo);			//商品型番
		
		//日付系項目最大は一日進めて00:00:00扱い　※時刻まで検索条件にする場合はそのまま
		if(null!=SearchExpdateMax && 0<SearchExpdateMax.size()){				//消費期限最大
			for(int i=0;i<SearchExpdateMax.size();i++){
				String SetString = B00050ToolsDateTimeControl.DateFormat(SearchExpdateMax.get(i));
				Timestamp SetTimestamp = B00050ToolsDateTimeControl.dtmTimestamp2(SetString)[0];
				SetTimestamp = B00050ToolsDateTimeControl.ndate_after(SetTimestamp,1);
				SetString = B00050ToolsDateTimeControl.dtmString2(SetTimestamp)[0];
				SearchExpdateMax.set(i,SetString);
			}
		}
		
		if(null!=SearchActualDateMax && 0<SearchActualDateMax.size()){				//入荷実績日最大
			for(int i=0;i<SearchActualDateMax.size();i++){
				String SetString = B00050ToolsDateTimeControl.DateFormat(SearchActualDateMax.get(i));
				Timestamp SetTimestamp = B00050ToolsDateTimeControl.dtmTimestamp2(SetString)[0];
				SetTimestamp = B00050ToolsDateTimeControl.ndate_after(SetTimestamp,1);
				SetString = B00050ToolsDateTimeControl.dtmString2(SetTimestamp)[0];
				SearchActualDateMax.set(i,SetString);
			}
		}
		
		//荷主商品コードを元に商品コードを絞り込む
		ArrayList<String> SearchItemCdFromClItemCd = new ArrayList<String>();
		if(null==SearchClItemCd||0==SearchClItemCd.size()) {
			
		}else {
			if(null==SearchClCd||0==SearchClCd.size()) {
				String ClCd	= A00000Main.ClCd; 
				ArrayList<String> ClItemCd=new ArrayList<String>();
				
				for(int i=0;i<SearchClItemCd.size();i++) {
					ClItemCd.add(SearchClItemCd.get(i));
				}
				
				Object[][] ItemComversion = WMTools000100ItemComversion.ItemComversion(ClCd,ClItemCd);
				
				for(int i=0;i<ItemComversion.length;i++) {
					String GetClCd						= (String)ItemComversion[i][WMTools000100ItemComversion.ColClCd];							//荷主CD
					String GetClGpCd					= (String)ItemComversion[i][WMTools000100ItemComversion.ColClGpCd];						//荷主グループCD
					String GetItemCd					= (String)ItemComversion[i][WMTools000100ItemComversion.ColItemCd];						//変換後商品CD
					String GetClIemCd					= (String)ItemComversion[i][WMTools000100ItemComversion.ColClIemCd];						//荷主商品CD
					int GetPackingType					= (int)ItemComversion[i][WMTools000100ItemComversion.ColPackingType];						//荷姿タイプ
					int GetPackingQty					= (int)ItemComversion[i][WMTools000100ItemComversion.ColPackingQty];						//荷姿入数(バラ換算数)
					String GetItemName01				= (String)ItemComversion[i][WMTools000100ItemComversion.ColItemName01];					//商品名
					String GetItemPackingTypeName		= (String)ItemComversion[i][WMTools000100ItemComversion.ColPackingTypeItemName];			//荷姿商品名
					String GetItemUnitName				= (String)ItemComversion[i][WMTools000100ItemComversion.ColItemUnitName];					//商品単位(バラ)
					String GetItemPackingTypeUnitName	= (String)ItemComversion[i][WMTools000100ItemComversion.ColPackingTypeItemUnitName];	//商品単位(荷姿単位)
					
					if(!"".equals(GetItemCd)) {
						SearchItemCdFromClItemCd.add(GetItemCd);
					}
				}
			}else {
				for(int i01=0;i01<SearchClCd.size();i01++) {
					String ClCd	= SearchClCd.get(i01);
					ArrayList<String> ClItemCd=new ArrayList<String>();
					
					for(int i=0;i<SearchClItemCd.size();i++) {
						ClItemCd.add(SearchClItemCd.get(i));
					}
					
					Object[][] ItemComversion = WMTools000100ItemComversion.ItemComversion(ClCd,ClItemCd);
					
					for(int i=0;i<ItemComversion.length;i++) {
						String GetClCd						= (String)ItemComversion[i][WMTools000100ItemComversion.ColClCd];							//荷主CD
						String GetClGpCd					= (String)ItemComversion[i][WMTools000100ItemComversion.ColClGpCd];						//荷主グループCD
						String GetItemCd					= (String)ItemComversion[i][WMTools000100ItemComversion.ColItemCd];						//変換後商品CD
						String GetClIemCd					= (String)ItemComversion[i][WMTools000100ItemComversion.ColClIemCd];						//荷主商品CD
						int GetPackingType					= (int)ItemComversion[i][WMTools000100ItemComversion.ColPackingType];						//荷姿タイプ
						int GetPackingQty					= (int)ItemComversion[i][WMTools000100ItemComversion.ColPackingQty];						//荷姿入数(バラ換算数)
						String GetItemName01				= (String)ItemComversion[i][WMTools000100ItemComversion.ColItemName01];					//商品名
						String GetItemPackingTypeName		= (String)ItemComversion[i][WMTools000100ItemComversion.ColPackingTypeItemName];			//荷姿商品名
						String GetItemUnitName				= (String)ItemComversion[i][WMTools000100ItemComversion.ColItemUnitName];					//商品単位(バラ)
						String GetItemPackingTypeUnitName	= (String)ItemComversion[i][WMTools000100ItemComversion.ColPackingTypeItemUnitName];	//商品単位(荷姿単位)
						
						if(!"".equals(GetItemCd)) {
							SearchItemCdFromClItemCd.add(GetItemCd);
						}
					}
				}
			}
		}
		
		
		Object[][] rt = new Object[0][RtStockRt().length];
		boolean SearchKick = false;
		if(AllSearch) {SearchKick = true;}
		
		String sql = "select \n"
				+"(WW0015Stock.ClCd) as ClCd,\n"						//荷主コード
				+"(KM0030_CLIENTMST.CLName01) as CLName,\n"				//荷主名1
				+"(WW0015Stock.WhCd) as WhCd,\n"						//倉庫コード
				+"(KM0010_WHMST.WHName) as ClWHName,\n"					//担当倉庫名
				+"(KM0030_CLIENTMST.ClGpCD) as ClGpCD,\n"				//荷主グループCD
				+"(KM0031_CLIENT_GROUP.ClGpName01) as ClGpName,\n"		//グループ名1
				+"(WW0015Stock.Loc) as Loc,\n"							//ロケーション
				+"(WM0010LOCATIONMST.LocName) as LocName,\n"			//ロケーション名
				+"(WM0010LOCATIONMST.Type) as Type,\n"					//ロケタイプ　0:通常　1:保管　8:入荷時　9:引当禁止
				+"(WW0015Stock.ItemCd) as ItemCd,\n"					//商品コード
				+"(WW0015Stock.Lot) as Lot,\n"							//ロット
				+"(WW0015Stock.Expdate) as Expdate,\n"					//消費期限
				+"(WW0015Stock.ActualDate) as ActualDate,\n"			//入荷実績日
				+"(WW0015Stock.Qty) as Qty,\n"							//数量
				+"(WW0015Stock.ShipPlanQty) as ShipPlanQty,\n"			//引当済数
				+"(WW0015Stock.PossibleQty) as PossibleQty,\n"			//出荷可能数
				+"(WW0015Stock.ItemName) as ItemName,\n"				//商品名
				+"(KM0060_ITEMMST.ItemName01) as ItemName01,\n"			//商品名1
				+"(KM0060_ITEMMST.ItemName02) as ItemName02,\n"			//商品名2
				+"(KM0060_ITEMMST.ItemName03) as ItemName03,\n"			//商品名3
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
				+ " from "+A00000Main.MySqlDefaultSchemaWANKO+".WW0015Stock"
				+ " left outer join "+A00000Main.MySqlDefaultSchemaWANKO+".WM0010LOCATIONMST"
				+ " on("
				+ " WW0015Stock.ClCd = WM0010LOCATIONMST.ClCd"
				+ " and WW0015Stock.WhCd = WM0010LOCATIONMST.WhCd"
				+ " and WW0015Stock.Loc= WM0010LOCATIONMST.Loc"
				+ ")\n"
				+ " left outer join "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0030_CLIENTMST"
				+ " on("
				+ " WW0015Stock.WhCd = KM0030_CLIENTMST.WHCD"
				+ " and WW0015Stock.ClCd = KM0030_CLIENTMST.cl_cd"
				+ ")\n"
				+ " left outer join "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP"
				+ " on("
				+ " KM0030_CLIENTMST.ClGpCD = KM0031_CLIENT_GROUP.ClGpCD"
				+ ")\n"
				+ " left outer join "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST"
				+ " on("
				+ " WW0015Stock.WhCd = KM0010_WHMST.WHCD"
				+ ")\n"
				+ " left outer join "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0060_ITEMMST"
				+ " on("
				+ " KM0030_CLIENTMST.ClGpCD = KM0060_ITEMMST.ClGpCd"
				+ " and WW0015Stock.ItemCd = KM0060_ITEMMST.ItemCd"
				+ ")\n"
				+ " left outer join "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0061_ITEMMSTSUB \n"
				+ " on("
				+ " KM0060_ITEMMST.ClGpCd = KM0061_ITEMMSTSUB.ClGpCd"
				+ " and KM0060_ITEMMST.ItemCd = KM0061_ITEMMSTSUB.ItemCd"
				+ ")\n"
				+ " where 1=1\n";
		
		if(null!=SearchItemCdFromClItemCd && 0<SearchItemCdFromClItemCd.size()){//荷主商品コード
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchItemCdFromClItemCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0015Stock.ItemCd = ?";
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
				sql = sql + " WM0010LOCATIONMST.Type = ?";
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
			A00010DbConnect.DB_CONN("NYANKO");
			ResultSet rset01 = null;
			PreparedStatement stmt01 = null;
			try {
				stmt01 = A00010DbConnect.conn.prepareStatement(sql);
				int StmtCount = 0;
				
				if(null!=SearchItemCdFromClItemCd && 0<SearchItemCdFromClItemCd.size()){						//荷主商品コード
					for(int i=0;i<SearchItemCdFromClItemCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchItemCdFromClItemCd.get(i)+"");
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
					if(null==rset01.getString("ClCd"			)){rt[counter][ColClCd]			="";}else{rt[counter][ColClCd]			=rset01.getString("ClCd");}		//荷主コード
					if(null==rset01.getString("CLName"			)){rt[counter][ColCLName]			="";}else{rt[counter][ColCLName]		=rset01.getString("CLName");}	//荷主名1
					if(null==rset01.getString("WhCd"			)){rt[counter][ColWhCd]			="";}else{rt[counter][ColWhCd]			=rset01.getString("WhCd");}		//倉庫コード
					if(null==rset01.getString("ClWHName"		)){rt[counter][ColClWHName]		="";}else{rt[counter][ColClWHName]		=rset01.getString("ClWHName");}	//担当倉庫名
					if(null==rset01.getString("ClGpCD"			)){rt[counter][ColClGpCD]			="";}else{rt[counter][ColClGpCD]		=rset01.getString("ClGpCD");}	//荷主グループCD
					if(null==rset01.getString("ClGpName"		)){rt[counter][ColClGpName]		="";}else{rt[counter][ColClGpName]		=rset01.getString("ClGpName");}	//グループ名1
					if(null==rset01.getString("Loc"				)){rt[counter][ColLoc]				="";}else{rt[counter][ColLoc]			=rset01.getString("Loc");}		//ロケーション
					if(null==rset01.getString("LocName"			)){rt[counter][ColLocName]			="";}else{rt[counter][ColLocName]		=rset01.getString("LocName");}	//ロケーション名
					rt[counter][ColType]						=rset01.getInt("Type");				//ロケタイプ　0:通常　1:保管　8:入荷時　9:引当禁止
					if(null==rset01.getString("ItemCd"			)){rt[counter][ColItemCd]			="";}else{rt[counter][ColItemCd]		=rset01.getString("ItemCd");}	//商品コード
					if(null==rset01.getString("Lot"				)){rt[counter][ColLot]				="";}else{rt[counter][ColLot]			=rset01.getString("Lot");}		//ロット
					if(null==rset01.getTimestamp("Expdate"		)){rt[counter][ColExpdate]			="";}else{rt[counter][ColExpdate]		=B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("Expdate"))[0];}		//消費期限
					if(null==rset01.getTimestamp("ActualDate"	)){rt[counter][ColActualDate]		="";}else{rt[counter][ColActualDate]	=B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("ActualDate"))[0];}		//入荷実績日
					rt[counter][ColQty]						=rset01.getInt("Qty");				//総数量
					rt[counter][ColShipPlanQty]				=rset01.getInt("ShipPlanQty");		//引当済総数
					rt[counter][ColPossibleQty]				=rset01.getInt("PossibleQty");		//出荷可能総数
					if(null==rset01.getString("ItemName"		)){rt[counter][ColItemName]		="";}else{rt[counter][ColItemName]		=rset01.getString("ItemName");}		//商品名
					if(null==rset01.getString("ItemName01"		)){rt[counter][ColItemName01]		="";}else{rt[counter][ColItemName01]	=rset01.getString("ItemName01");}	//商品名1
					if(null==rset01.getString("ItemName02"		)){rt[counter][ColItemName02]		="";}else{rt[counter][ColItemName02]	=rset01.getString("ItemName02");}	//商品名2
					if(null==rset01.getString("ItemName03"		)){rt[counter][ColItemName03]		="";}else{rt[counter][ColItemName03]	=rset01.getString("ItemName03");}	//商品名3
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
					if(null==rset01.getTimestamp("EntryDate"	)){rt[counter][ColEntryDate]		="";}else{rt[counter][ColEntryDate]	=B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("EntryDate"))[1];}		//登録日時
					if(null==rset01.getTimestamp("UpdateDate"	)){rt[counter][ColUpdateDate]		="";}else{rt[counter][ColUpdateDate]	=B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("UpdateDate"))[1];}		//更新日時
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
			A00010DbConnect.close();
		}
		return rt;
	}
}