import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class T100_StockAdjustRt{
	//在庫調整実績を返却する
	/*
	コピペ用
	ArrayList<String>  SearchClCd				= new ArrayList<String>();
	ArrayList<String>  SearchWhCd				= new ArrayList<String>();
	ArrayList<String>  SearchClGpCD				= new ArrayList<String>();
	ArrayList<String>  SearchAdjustNo			= new ArrayList<String>();
	ArrayList<String>  SearchAdjustReasonCd		= new ArrayList<String>();
	ArrayList<String>  SearchAdjustReasonName	= new ArrayList<String>();
	ArrayList<String>  SearchAdjustdateMin		= new ArrayList<String>();
	ArrayList<String>  SearchAdjustdateMax		= new ArrayList<String>();
	ArrayList<String>  SearchLoc				= new ArrayList<String>();
	ArrayList<Integer> SearchType				= new ArrayList<Integer>();
	ArrayList<String>  SearchItemCd				= new ArrayList<String>();
	ArrayList<String>  SearchItemName			= new ArrayList<String>();
	ArrayList<String>  SearchLot				= new ArrayList<String>();
	ArrayList<String>  SearchExpDateMin			= new ArrayList<String>();
	ArrayList<String>  SearchExpDateMax			= new ArrayList<String>();
	ArrayList<String>  SearchActualDateMin		= new ArrayList<String>();
	ArrayList<String>  SearchActualDateMax		= new ArrayList<String>();
	ArrayList<Integer> SearchAdjustQtyMin		= new ArrayList<Integer>();
	ArrayList<Integer> SearchAdjustQtyMax		= new ArrayList<Integer>();
	ArrayList<String>  SearchAdjustCom			= new ArrayList<String>();
	boolean LocExactMatch 	= false;
	boolean AllSearch 		= false;
	
	
	Object[][] AdjustRt = T100_StockAdjustRt.AdjustRt(
			SearchClCd,					//荷主コード
			SearchWhCd,					//倉庫コード
			SearchClGpCD,				//荷主グループCD
			SearchAdjustNo,				//調整番号
			SearchAdjustReasonCd,		//調整理由コード
			SearchAdjustReasonName,		//調整理由名
			SearchAdjustdateMin,		//調整日最小
			SearchAdjustdateMax,		//調整日最大
			SearchLoc,					//調整元ロケ
			SearchType,					//ロケタイプ
			SearchItemCd,				//調整元商品CD
			SearchItemName,				//調整元商品名
			SearchLot,	 				//調整元ロット
			SearchExpDateMin,			//調整元賞味期限最小
			SearchExpDateMax,			//調整元賞味期限最大
			SearchActualDateMin,		//調整元入荷日最小
			SearchActualDateMax,		//調整元入荷日最大
			SearchAdjustQtyMin,			//調整数最小
			SearchAdjustQtyMax,			//調整数最大
			SearchAdjustCom,			//調整理由コメント
			LocExactMatch,				//ロケーション完全一致
			AllSearch);
			
	String GetClCd				= (String)AdjustRt[i][T100_StockAdjustRt.ColClCd];
	String GetCLName			= (String)AdjustRt[i][T100_StockAdjustRt.ColCLName];
	String GetWhCd				= (String)AdjustRt[i][T100_StockAdjustRt.ColWhCd];
	String GetClWHName			= (String)AdjustRt[i][T100_StockAdjustRt.ColClWHName];
	String GetClGpCD			= (String)AdjustRt[i][T100_StockAdjustRt.ColClGpCD];
	String GetClGpName			= (String)AdjustRt[i][T100_StockAdjustRt.ColClGpName];
	String GetAdjustNo			= (String)AdjustRt[i][T100_StockAdjustRt.ColAdjustNo];
	String GetAdjustReasonCd	= (String)AdjustRt[i][T100_StockAdjustRt.ColAdjustReasonCd];
	String GetAdjustReasonName	= (String)AdjustRt[i][T100_StockAdjustRt.ColAdjustReasonName];
	String GetAdjustdate		= (String)AdjustRt[i][T100_StockAdjustRt.ColAdjustdate];
	String GetLoc				= (String)AdjustRt[i][T100_StockAdjustRt.ColLoc];
	String GetLocName			= (String)AdjustRt[i][T100_StockAdjustRt.ColLocName];
	int GetType					= (int)AdjustRt[i][T100_StockAdjustRt.ColType];
	String GetItemCd			= (String)AdjustRt[i][T100_StockAdjustRt.ColItemCd];
	String GetItemName			= (String)AdjustRt[i][T100_StockAdjustRt.ColItemName];
	String GetItemName01		= (String)AdjustRt[i][T100_StockAdjustRt.ColItemName01];
	String GetItemName02		= (String)AdjustRt[i][T100_StockAdjustRt.ColItemName02];
	String GetItemName03		= (String)AdjustRt[i][T100_StockAdjustRt.ColItemName03];
	int GetCtUnitQty			= (int)AdjustRt[i][T100_StockAdjustRt.ColCtUnitQty];
	int GetCsUnitQty			= (int)AdjustRt[i][T100_StockAdjustRt.ColCsUnitQty];
	int GetPlUnitQty			= (int)AdjustRt[i][T100_StockAdjustRt.ColPlUnitQty];
	String GetUnitName			= (String)AdjustRt[i][T100_StockAdjustRt.ColUnitName];
	String GetCtUnitName		= (String)AdjustRt[i][T100_StockAdjustRt.ColCtUnitName];
	String GetCsUnitName		= (String)AdjustRt[i][T100_StockAdjustRt.ColCsUnitName];
	String GetPlUnitName		= (String)AdjustRt[i][T100_StockAdjustRt.ColPlUnitName];
	String GetLot				= (String)AdjustRt[i][T100_StockAdjustRt.ColLot];
	String GetExpDate			= (String)AdjustRt[i][T100_StockAdjustRt.ColExpDate];
	String GetActualDate		= (String)AdjustRt[i][T100_StockAdjustRt.ColActualDate];
	int GetBeforeQty			= (int)AdjustRt[i][T100_StockAdjustRt.ColBeforeQty];
	int GetShipPlanQty			= (int)AdjustRt[i][T100_StockAdjustRt.ColShipPlanQty];
	int GetPossibleQty			= (int)AdjustRt[i][T100_StockAdjustRt.ColPossibleQty];
	int GetAdjustQty			= (int)AdjustRt[i][T100_StockAdjustRt.ColAdjustQty];
	String GetAdjustCom01		= (String)AdjustRt[i][T100_StockAdjustRt.ColAdjustCom01];
	String GetAdjustCom02		= (String)AdjustRt[i][T100_StockAdjustRt.ColAdjustCom02];
	String GetAdjustCom03		= (String)AdjustRt[i][T100_StockAdjustRt.ColAdjustCom03];
	int GetAfterQty				= (int)AdjustRt[i][T100_StockAdjustRt.ColAfterQty];
	String GetEntryDate			= (String)AdjustRt[i][T100_StockAdjustRt.ColEntryDate];
	String GetUpdateDate		= (String)AdjustRt[i][T100_StockAdjustRt.ColUpdateDate];
	String GetEntryUser			= (String)AdjustRt[i][T100_StockAdjustRt.ColEntryUser];
	String GetUpdateUser		= (String)AdjustRt[i][T100_StockAdjustRt.ColUpdateUser];
	*/
	
	static final int ColAdjustNo				= 0;		//調整番号
	static final int ColAdjustReasonName		= 1;		//調整理由名
	static final int ColAdjustdate			= 2;		//調整日
	static final int ColLoc					= 3;		//調整元ロケ
	static final int ColLocName				= 4;		//ロケーション名
	static final int ColItemCd					= 5;		//調整元商品CD
	static final int ColItemName				= 6;		//調整元商品名
	static final int ColLot					= 7;	 	//調整元ロット
	static final int ColExpDate				= 8;		//調整元賞味期限
	static final int ColAdjustQty				= 9;		//調整数
	static final int ColAfterQty				=10;		//調整後在庫数
	static final int ColCtUnitQty				=11;		//カートン入数
	static final int ColCsUnitQty				=12;		//ケース入数
	static final int ColPlUnitQty				=13;		//パレット入数
	static final int ColUnitName				=14;		//商品単位
	static final int ColCtUnitName			=15;		//カートン商品単位
	static final int ColCsUnitName			=16;		//ケース商品単位
	static final int ColPlUnitName			=17;		//パレット商品単位
	static final int ColActualDate			=18;		//調整元入荷日
	static final int ColBeforeQty				=19;		//調整元在庫数
	static final int ColShipPlanQty			=20;		//調整元引当済数
	static final int ColPossibleQty			=21;		//調整元出荷可能数
	static final int ColAdjustCom01			=22;		//調整理由コメント01
	static final int ColAdjustCom02			=23;		//調整理由コメント02
	static final int ColAdjustCom03			=24;		//調整理由コメント03
	static final int ColClCd					=25;		//荷主コード
	static final int ColCLName					=26;		//荷主表記名
	static final int ColWhCd					=27;		//倉庫コード
	static final int ColClWHName				=28;		//担当倉庫名
	static final int ColClGpCD					=29;		//荷主グループCD
	static final int ColClGpName				=30;		//グループ名1
	static final int ColEntryDate				=31;		//登録日
	static final int ColUpdateDate			=32;		//更新日
	static final int ColEntryUser				=33;		//登録者
	static final int ColUpdateUser			=34;		//更新者
	static final int ColType					=35;		//ロケタイプ　0:通常　1:保管　8:入荷時　9:引当禁止
	static final int ColItemName01			=36;		//商品表記名
	static final int ColItemName02			=37;		//商品正式名
	static final int ColItemName03			=38;		//商品略名
	static final int ColAdjustReasonCd		=39;		//調整理由コードstatic final int ColAdjustReasonCd		=36;		//調整理由コード
	public static Object[][] RtAdjustRt(){
		Object[][] RtAdjustRt = {
				 {"ClCd"			,ColClCd				,"String"	,"荷主コード"				,"key"}
				,{"CLName"			,ColCLName				,"String"	,"荷主名"					,""}
				,{"WhCd"			,ColWhCd				,"String"	,"倉庫コード"				,"key"}
				,{"ClWHName"		,ColClWHName			,"String"	,"担当倉庫名"				,""}
				,{"ClGpCD"			,ColClGpCD				,"String"	,"荷主グループCD"			,""}
				,{"ClGpName"		,ColClGpName			,"String"	,"グループ名"				,""}
				,{"AdjustNo"		,ColAdjustNo			,"String"	,"調整番号"					,"key"}
				,{"AdjustReasonCd"	,ColAdjustReasonCd	,"String"	,"調整理由コード"			,""}
				,{"AdjustReasonName",ColAdjustReasonName	,"String"	,"調整理由名"				,""}
				,{"Adjustdate"		,ColAdjustdate		,"DateTime"	,"調整日"					,""}
				,{"Loc"				,ColLoc				,"String"	,"調整元ロケ"				,""}
				,{"LocName"			,ColLocName			,"String"	,"ロケーション名"			,""}
				,{"Type"			,ColType				,"int"		,"ロケタイプ"				,""}
				,{"ItemCd"			,ColItemCd				,"String"	,"商品CD"					,""}
				,{"ItemName"		,ColItemName			,"String"	,"商品名"					,""}
				,{"ItemName01"		,ColItemName01		,"String"	,"商品表記名"				,""}
				,{"ItemName02"		,ColItemName02		,"String"	,"商品正式名"				,""}
				,{"ItemName03"		,ColItemName03		,"String"	,"商品略名"					,""}
				,{"CtUnitQty"		,ColCtUnitQty			,"int"		,"カートン入数"				,""}
				,{"CsUnitQty"		,ColCsUnitQty			,"int"		,"ケース入数"				,""}
				,{"PlUnitQty"		,ColPlUnitQty			,"int"		,"パレット入数"				,""}
				,{"UnitName"		,ColUnitName			,"String"	,"商品単位"					,""}
				,{"CtUnitName"		,ColCtUnitName		,"String"	,"カートン商品単位"			,""}
				,{"CsUnitName"		,ColCsUnitName		,"String"	,"ケース商品単位"			,""}
				,{"PlUnitName"		,ColPlUnitName		,"String"	,"パレット商品単位"			,""}
				,{"Lot"				,ColLot				,"String"	,"調整元ロット"				,""}
				,{"ExpDate"			,ColExpDate			,"Date"		,"調整元賞味期限"			,""}
				,{"ActualDate"		,ColActualDate		,"Date"		,"調整元入荷日"				,""}
				,{"BeforeQty"		,ColBeforeQty			,"int"		,"調整元在庫数"				,""}
				,{"ShipPlanQty"		,ColShipPlanQty		,"int"		,"調整元引当済数"			,""}
				,{"PossibleQty"		,ColPossibleQty		,"int"		,"調整元出荷可能数"			,""}
				,{"AdjustQty"		,ColAdjustQty			,"int"		,"調整数"					,""}
				,{"AdjustCom01"		,ColAdjustCom01		,"String"	,"調整理由コメント01"		,""}
				,{"AdjustCom02"		,ColAdjustCom02		,"String"	,"調整理由コメント02"		,""}
				,{"AdjustCom03"		,ColAdjustCom03		,"String"	,"調整理由コメント03"		,""}
				,{"AfterQty"		,ColAfterQty			,"int"		,"調整後在庫数"				,""}
				,{"EntryDate"		,ColEntryDate			,"DateTime"	,"登録日"					,""}
				,{"UpdateDate"		,ColUpdateDate		,"DateTime"	,"更新日"					,""}
				,{"EntryUser"		,ColEntryUser			,"String"	,"登録者"					,""}
				,{"UpdateUser"		,ColUpdateUser		,"String"	,"更新者"					,""}
				};
		return RtAdjustRt;
	}
	public static Object[][] AdjustRt(
			ArrayList<String>  SearchClCd,				//荷主コード
			ArrayList<String>  SearchWhCd,				//倉庫コード
			ArrayList<String>  SearchClGpCD,			//荷主グループCD
			ArrayList<String>  SearchAdjustNo,			//調整番号
			ArrayList<String>  SearchAdjustReasonCd,	//調整理由コード
			ArrayList<String>  SearchAdjustReasonName,	//調整理由名
			ArrayList<String>  SearchAdjustdateMin,		//調整日最小
			ArrayList<String>  SearchAdjustdateMax,		//調整日最大
			ArrayList<String>  SearchLoc,				//調整元ロケ
			ArrayList<Integer> SearchType,				//ロケタイプ
			ArrayList<String>  SearchItemCd,			//調整元商品CD
			ArrayList<String>  SearchItemName,			//調整元商品名
			ArrayList<String>  SearchLot,	 			//調整元ロット
			ArrayList<String>  SearchExpDateMin,		//調整元賞味期限最小
			ArrayList<String>  SearchExpDateMax,		//調整元賞味期限最大
			ArrayList<String>  SearchActualDateMin,		//調整元入荷日最小
			ArrayList<String>  SearchActualDateMax,		//調整元入荷日最大
			ArrayList<Integer> SearchAdjustQtyMin,		//調整数最小
			ArrayList<Integer> SearchAdjustQtyMax,		//調整数最大
			ArrayList<String>  SearchAdjustCom,			//調整理由コメント
			boolean LocExactMatch,	//ロケーション完全一致
			boolean AllSearch){
		//日付系最小は念のため00:00:00扱い
		if(null!=SearchAdjustdateMin && 0<SearchAdjustdateMin.size()){
			for(int i=0;i<SearchAdjustdateMin.size();i++){
				String SetString = B100_DateTimeControl.DateFormat(SearchAdjustdateMin.get(i));
				Timestamp SetTimestamp = B100_DateTimeControl.dtmTimestamp2(SetString)[0];
				SetString = B100_DateTimeControl.dtmString2(SetTimestamp)[0];
				SearchAdjustdateMin.set(i,SetString);
			}
		}
		if(null!=SearchExpDateMin && 0<SearchExpDateMin.size()){
			for(int i=0;i<SearchExpDateMin.size();i++){
				String SetString = B100_DateTimeControl.DateFormat(SearchExpDateMin.get(i));
				Timestamp SetTimestamp = B100_DateTimeControl.dtmTimestamp2(SetString)[0];
				SetString = B100_DateTimeControl.dtmString2(SetTimestamp)[0];
				SearchExpDateMin.set(i,SetString);
			}
		}
		if(null!=SearchActualDateMin && 0<SearchActualDateMin.size()){
			for(int i=0;i<SearchActualDateMin.size();i++){
				String SetString = B100_DateTimeControl.DateFormat(SearchActualDateMin.get(i));
				Timestamp SetTimestamp = B100_DateTimeControl.dtmTimestamp2(SetString)[0];
				SetString = B100_DateTimeControl.dtmString2(SetTimestamp)[0];
				SearchActualDateMin.set(i,SetString);
			}
		}
		
		//日付系項目最大は一日進めて00:00:00扱い　※時刻まで検索条件にする場合はそのまま
		if(null!=SearchAdjustdateMax && 0<SearchAdjustdateMax.size()){
			for(int i=0;i<SearchAdjustdateMax.size();i++){
				String SetString = B100_DateTimeControl.DateFormat(SearchAdjustdateMax.get(i));
				Timestamp SetTimestamp = B100_DateTimeControl.dtmTimestamp2(SetString)[0];
				SetTimestamp = B100_DateTimeControl.ndate_after(SetTimestamp,1);
				SetString = B100_DateTimeControl.dtmString2(SetTimestamp)[0];
				SearchAdjustdateMax.set(i,SetString);
			}
		}
		if(null!=SearchExpDateMax && 0<SearchExpDateMax.size()){
			for(int i=0;i<SearchExpDateMax.size();i++){
				String SetString = B100_DateTimeControl.DateFormat(SearchExpDateMax.get(i));
				Timestamp SetTimestamp = B100_DateTimeControl.dtmTimestamp2(SetString)[0];
				SetTimestamp = B100_DateTimeControl.ndate_after(SetTimestamp,1);
				SetString = B100_DateTimeControl.dtmString2(SetTimestamp)[0];
				SearchExpDateMax.set(i,SetString);
			}
		}
		if(null!=SearchActualDateMax && 0<SearchActualDateMax.size()){
			for(int i=0;i<SearchActualDateMax.size();i++){
				String SetString = B100_DateTimeControl.DateFormat(SearchActualDateMax.get(i));
				Timestamp SetTimestamp = B100_DateTimeControl.dtmTimestamp2(SetString)[0];
				SetTimestamp = B100_DateTimeControl.ndate_after(SetTimestamp,1);
				SetString = B100_DateTimeControl.dtmString2(SetTimestamp)[0];
				SearchActualDateMax.set(i,SetString);
			}
		}
		SearchClCd				= B100_ArrayListControl.ArryListStringUniqueList(SearchClCd);
		SearchWhCd				= B100_ArrayListControl.ArryListStringUniqueList(SearchWhCd);
		SearchClGpCD			= B100_ArrayListControl.ArryListStringUniqueList(SearchClGpCD);
		SearchAdjustNo			= B100_ArrayListControl.ArryListStringUniqueList(SearchAdjustNo);
		SearchAdjustReasonCd	= B100_ArrayListControl.ArryListStringUniqueList(SearchAdjustReasonCd);
		SearchAdjustReasonName	= B100_ArrayListControl.ArryListStringUniqueList(SearchAdjustReasonName);
		SearchAdjustdateMin		= B100_ArrayListControl.ArryListStringUniqueList(SearchAdjustdateMin);
		SearchAdjustdateMax		= B100_ArrayListControl.ArryListStringUniqueList(SearchAdjustdateMax);
		SearchLoc				= B100_ArrayListControl.ArryListStringUniqueList(SearchLoc);
		SearchType				= B100_ArrayListControl.ArryListIntegerUniqueList(SearchType);
		SearchItemCd			= B100_ArrayListControl.ArryListStringUniqueList(SearchItemCd);
		SearchItemName			= B100_ArrayListControl.ArryListStringUniqueList(SearchItemName);
		SearchLot				= B100_ArrayListControl.ArryListStringUniqueList(SearchLot);
		SearchExpDateMin		= B100_ArrayListControl.ArryListStringUniqueList(SearchExpDateMin);
		SearchExpDateMax		= B100_ArrayListControl.ArryListStringUniqueList(SearchExpDateMax);
		SearchActualDateMin		= B100_ArrayListControl.ArryListStringUniqueList(SearchActualDateMin);
		SearchActualDateMax		= B100_ArrayListControl.ArryListStringUniqueList(SearchActualDateMax);
		SearchAdjustQtyMin		= B100_ArrayListControl.ArryListIntegerUniqueList(SearchAdjustQtyMin);
		SearchAdjustQtyMax		= B100_ArrayListControl.ArryListIntegerUniqueList(SearchAdjustQtyMax);
		SearchAdjustCom			= B100_ArrayListControl.ArryListStringUniqueList(SearchAdjustCom);

		
		Object[][] RtAdjustRt=RtAdjustRt();
		Object[][] rt = new Object[0][RtAdjustRt.length];
		
		boolean SearchKick = false;
		if(AllSearch) {SearchKick = true;}
		
		String sql = "select \n"
				+"(WW0016StockAdjust.ClCd)				as ClCd				,\n"		//荷主コード
				+"(KM0030_CLIENTMST.CLName01)			as CLName			,\n"		//荷主名
				+"(WW0016StockAdjust.WhCd)				as WhCd             ,\n"		//倉庫コード
				+"(KM0010_WHMST.WHName)					as ClWHName         ,\n"		//担当倉庫名
				+"(KM0030_CLIENTMST.ClGpCD)				as ClGpCD           ,\n"		//荷主グループCD
				+"(KM0031_CLIENT_GROUP.ClGpName01)		as ClGpName         ,\n"		//グループ名
				+"(WW0016StockAdjust.AdjustNo)			as AdjustNo         ,\n"		//調整番号
				+"(WW0016StockAdjust.AdjustReasonCd)	as AdjustReasonCd   ,\n"		//調整理由コード
				+"(WW0016StockAdjust.AdjustReasonName)	as AdjustReasonName ,\n"		//調整理由名
				+"(WW0016StockAdjust.Adjustdate)		as Adjustdate       ,\n"		//調整日
				+"(WW0016StockAdjust.Loc)				as Loc              ,\n"		//調整元ロケ
				+"(WM0010LOCATIONMST.LocName)			as LocName          ,\n"		//ロケーション名
				+"(WM0010LOCATIONMST.LocType)			as Type             ,\n"		//ロケタイプ　0:通常　1:保管　8:入荷時　9:引当禁止
				+"(WW0016StockAdjust.ItemCd)			as ItemCd           ,\n"		//調整元商品CD
				+"(WW0016StockAdjust.ItemName)			as ItemName         ,\n"		//調整元商品名
				+"(KM0060_ITEMMST.ItemName01)			as ItemName01       ,\n"		//商品表記名
				+"(KM0060_ITEMMST.ItemName02)			as ItemName02       ,\n"		//商品正式名
				+"(KM0060_ITEMMST.ItemName03)			as ItemName03       ,\n"		//商品略名
				+"(KM0061_ITEMMSTSUB.CtQty)				as CtUnitQty        ,\n"		//カートン入数
				+"(KM0061_ITEMMSTSUB.CsQty)				as CsUnitQty        ,\n"		//ケース入数
				+"(KM0061_ITEMMSTSUB.PlQty)				as PlUnitQty        ,\n"		//パレット入数
				+"(KM0060_ITEMMST.UnitName)				as UnitName         ,\n"		//商品単位
				+"(KM0061_ITEMMSTSUB.CtUnitName)		as CtUnitName       ,\n"		//カートン商品単位
				+"(KM0061_ITEMMSTSUB.CsUnitName)		as CsUnitName       ,\n"		//ケース商品単位
				+"(KM0061_ITEMMSTSUB.PlUnitName)		as PlUnitName       ,\n"		//パレット商品単位
				+"(WW0016StockAdjust.Lot)				as Lot              ,\n"	 	//調整元ロット
				+"(WW0016StockAdjust.ExpDate)			as ExpDate          ,\n"		//調整元賞味期限
				+"(WW0016StockAdjust.ActualDate)		as ActualDate       ,\n"		//調整元入荷日
				+"(WW0016StockAdjust.BeforeQty)			as BeforeQty        ,\n"		//調整元在庫数
				+"(WW0016StockAdjust.ShipPlanQty)		as ShipPlanQty      ,\n"		//調整元引当済数
				+"(WW0016StockAdjust.PossibleQty)		as PossibleQty      ,\n"		//調整元出荷可能数
				+"(WW0016StockAdjust.AdjustQty)			as AdjustQty        ,\n"		//調整数
				+"(WW0016StockAdjust.AdjustCom01)		as AdjustCom01      ,\n"		//調整理由コメント01
				+"(WW0016StockAdjust.AdjustCom02)		as AdjustCom02      ,\n"		//調整理由コメント02
				+"(WW0016StockAdjust.AdjustCom03)		as AdjustCom03      ,\n"		//調整理由コメント03
				+"(WW0016StockAdjust.AfterQty)			as AfterQty         ,\n"		//調整後在庫数
				+"(WW0016StockAdjust.EntryDate)			as EntryDate        ,\n"		//登録日
				+"(WW0016StockAdjust.UpdateDate)		as UpdateDate       ,\n"		//更新日
				+"(WW0016StockAdjust.EntryUser)			as EntryUser        ,\n"		//登録者
				+"(WW0016StockAdjust.UpdateUser)		as UpdateUser        \n"		//更新者
				+ " from "+A00000_Main.MySqlDefaultSchemaWANKO+".WW0016StockAdjust \n"
				+ " left outer join "+A00000_Main.MySqlDefaultSchemaWANKO+".WM0010LOCATIONMST"
				+ " on("
				+ " WW0016StockAdjust.ClCd = WM0010LOCATIONMST.ClCd"
				+ " and WW0016StockAdjust.WhCd = WM0010LOCATIONMST.WhCd"
				+ " and WW0016StockAdjust.Loc= WM0010LOCATIONMST.Loc"
				+ ")\n"
				+ " left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0030_CLIENTMST"
				+ " on("
				+ " WW0016StockAdjust.WhCd = KM0030_CLIENTMST.WHCD"
				+ " and WW0016StockAdjust.ClCd = KM0030_CLIENTMST.cl_cd"
				+ ")\n"
				+ " left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP"
				+ " on("
				+ " KM0030_CLIENTMST.ClGpCD = KM0031_CLIENT_GROUP.ClGpCD"
				+ ")\n"
				+ " left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST"
				+ " on("
				+ " WW0016StockAdjust.WhCd = KM0010_WHMST.WHCD"
				+ ")\n"
				+ " left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0060_ITEMMST"
				+ " on("
				+ " KM0030_CLIENTMST.ClGpCD = KM0060_ITEMMST.ClGpCd"
				+ " and WW0016StockAdjust.ItemCd = KM0060_ITEMMST.ItemCd"
				+ ")\n"
				+ " left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0061_ITEMMSTSUB \n"
				+ " on("
				+ " KM0060_ITEMMST.ClGpCd = KM0061_ITEMMSTSUB.ClGpCd"
				+ " and KM0060_ITEMMST.ItemCd = KM0061_ITEMMSTSUB.ItemCd"
				+ ")\n"
				+ " where 1=1\n";
		
		if(null!=SearchClCd && 0<SearchClCd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchClCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0016StockAdjust.ClCd = ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchWhCd && 0<SearchWhCd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchWhCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0016StockAdjust.WhCd = ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchClGpCD && 0<SearchClGpCD.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchClGpCD.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KM0030_CLIENTMST.ClGpCD = ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchAdjustNo && 0<SearchAdjustNo.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchAdjustNo.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0016StockAdjust.AdjustNo = ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchAdjustReasonCd && 0<SearchAdjustReasonCd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchAdjustReasonCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0016StockAdjust.AdjustReasonCd = ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchAdjustReasonName && 0<SearchAdjustReasonName.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchAdjustReasonName.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0016StockAdjust.AdjustReasonName Like ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchAdjustdateMin && 0<SearchAdjustdateMin.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchAdjustdateMin.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0016StockAdjust.Adjustdate >= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchAdjustdateMax && 0<SearchAdjustdateMax.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchAdjustdateMax.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0016StockAdjust.Adjustdate < ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchLoc && 0<SearchLoc.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchLoc.size();i++){
				if(0<i){sql = sql + " or ";}
				if(LocExactMatch) {
					sql = sql + " WW0016StockAdjust.Loc = ?";
				}else {
					sql = sql + " WW0016StockAdjust.Loc Like ?";
				}
			}
			sql = sql + ")\n";
		}
		if(null!=SearchType && 0<SearchType.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchType.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WM0010LOCATIONMST.LocType = ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchItemCd && 0<SearchItemCd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchItemCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0016StockAdjust.ItemCd = ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchItemName && 0<SearchItemName.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchItemName.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0016StockAdjust.ItemName Like ?";
				sql = sql + " or KM0060_ITEMMST.ItemName01 Like ?";
				sql = sql + " or KM0060_ITEMMST.ItemName02 Like ?";
				sql = sql + " or KM0060_ITEMMST.ItemName03 Like ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchLot && 0<SearchLot.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchLot.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0016StockAdjust.Lot = ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchExpDateMin && 0<SearchExpDateMin.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchExpDateMin.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0016StockAdjust.ExpDate >= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchExpDateMax && 0<SearchExpDateMax.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchExpDateMax.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0016StockAdjust.ExpDate < ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchActualDateMin && 0<SearchActualDateMin.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchActualDateMin.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0016StockAdjust.ActualDate >= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchActualDateMax && 0<SearchActualDateMax.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchActualDateMax.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0016StockAdjust.ActualDate < ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchAdjustQtyMin && 0<SearchAdjustQtyMin.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchAdjustQtyMin.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0016StockAdjust.AdjustQty >= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchAdjustQtyMax && 0<SearchAdjustQtyMax.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchAdjustQtyMax.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0016StockAdjust.AdjustQty <= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchAdjustCom && 0<SearchAdjustCom.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchAdjustCom.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0016StockAdjust.AdjustCom01 Like ?";
				sql = sql + " or WW0016StockAdjust.AdjustCom02 Like ?";
				sql = sql + " or WW0016StockAdjust.AdjustCom03 Like ?";
			}
			sql = sql + ")\n";
		}
		
		sql = sql+" order by WW0016StockAdjust.ClCd,WW0016StockAdjust.Adjustdate,WW0016StockAdjust.AdjustNo";
		
		//System.out.println(sql);
		if(SearchKick) {
			A100_DbConnect.DB_CONN("WANKO");
			ResultSet rset01 = null;
			PreparedStatement stmt01 = null;
			try {
				stmt01 = A100_DbConnect.conn.prepareStatement(sql);
				int StmtCount = 0;
				
				if(null!=SearchClCd && 0<SearchClCd.size()){
					for(int i=0;i<SearchClCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchClCd.get(i)+"");
					}
				}
				if(null!=SearchWhCd && 0<SearchWhCd.size()){
					for(int i=0;i<SearchWhCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchWhCd.get(i)+"");
					}
				}
				if(null!=SearchClGpCD && 0<SearchClGpCD.size()){
					for(int i=0;i<SearchClGpCD.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchClGpCD.get(i)+"");
					}
				}
				if(null!=SearchAdjustNo && 0<SearchAdjustNo.size()){
					for(int i=0;i<SearchAdjustNo.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchAdjustNo.get(i)+"");
					}
				}
				if(null!=SearchAdjustReasonCd && 0<SearchAdjustReasonCd.size()){
					for(int i=0;i<SearchAdjustReasonCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchAdjustReasonCd.get(i)+"");
					}
				}
				if(null!=SearchAdjustReasonName && 0<SearchAdjustReasonName.size()){
					for(int i=0;i<SearchAdjustReasonName.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchAdjustReasonName.get(i)+"%");
					}
				}
				if(null!=SearchAdjustdateMin && 0<SearchAdjustdateMin.size()){
					for(int i=0;i<SearchAdjustdateMin.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchAdjustdateMin.get(i)+"");
					}
				}
				if(null!=SearchAdjustdateMax && 0<SearchAdjustdateMax.size()){
					for(int i=0;i<SearchAdjustdateMax.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchAdjustdateMax.get(i)+"");
					}
				}
				if(null!=SearchLoc && 0<SearchLoc.size()){
					for(int i=0;i<SearchLoc.size();i++){
						if(0<i){sql = sql + " or ";}
						if(LocExactMatch) {
							StmtCount = StmtCount+1;
							stmt01.setString(StmtCount, ""+SearchLoc.get(i)+"");
						}else {
							StmtCount = StmtCount+1;
							stmt01.setString(StmtCount, ""+SearchLoc.get(i)+"%");
						}
					}
				}
				if(null!=SearchType && 0<SearchType.size()){
					for(int i=0;i<SearchType.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchType.get(i)+"");
					}
				}
				if(null!=SearchItemCd && 0<SearchItemCd.size()){
					for(int i=0;i<SearchItemCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchItemCd.get(i)+"");
					}
				}
				if(null!=SearchItemName && 0<SearchItemName.size()){
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
				if(null!=SearchLot && 0<SearchLot.size()){
					for(int i=0;i<SearchLot.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchLot.get(i)+"");
					}
				}
				if(null!=SearchExpDateMin && 0<SearchExpDateMin.size()){
					for(int i=0;i<SearchExpDateMin.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchExpDateMin.get(i)+"");
					}
				}
				if(null!=SearchExpDateMax && 0<SearchExpDateMax.size()){
					for(int i=0;i<SearchExpDateMax.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchExpDateMax.get(i)+"");
					}
				}
				if(null!=SearchActualDateMin && 0<SearchActualDateMin.size()){
					for(int i=0;i<SearchActualDateMin.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchActualDateMin.get(i)+"");
					}
				}
				if(null!=SearchActualDateMax && 0<SearchActualDateMax.size()){
					for(int i=0;i<SearchActualDateMax.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchActualDateMax.get(i)+"");
					}
				}
				if(null!=SearchAdjustQtyMin && 0<SearchAdjustQtyMin.size()){
					for(int i=0;i<SearchAdjustQtyMin.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchAdjustQtyMin.get(i)+"");
					}
				}
				if(null!=SearchAdjustQtyMax && 0<SearchAdjustQtyMax.size()){
					for(int i=0;i<SearchAdjustQtyMax.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchAdjustQtyMax.get(i)+"");
					}
				}
				if(null!=SearchAdjustCom && 0<SearchAdjustCom.size()){
					for(int i=0;i<SearchAdjustCom.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchAdjustCom.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchAdjustCom.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchAdjustCom.get(i)+"%");
					}
				}
				rset01 = stmt01.executeQuery();
				
				rt = B100_RtObjectCreate.B100_RtObjectCreate(rset01,RtAdjustRt());
				
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
}