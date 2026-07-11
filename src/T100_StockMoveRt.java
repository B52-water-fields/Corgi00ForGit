import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class T100_StockMoveRt{
	//在庫移動履歴を返却する
	//※在庫移動履歴はデータ保持期間短めに設定されます
	/*
	コピペ用
	ArrayList<String> SearchClCd						= new ArrayList<String>();		//荷主コード
	ArrayList<String> SearchCLName						= new ArrayList<String>();		//荷主名
	ArrayList<String> SearchWhCd						= new ArrayList<String>();		//倉庫コード
	ArrayList<String> SearchClWHName					= new ArrayList<String>();		//担当倉庫名
	ArrayList<String> SearchMoveNo						= new ArrayList<String>();		//調整番号
	ArrayList<String> SearchFromLoc						= new ArrayList<String>();		//移動元ロケ
	ArrayList<String> SearchFromLocName					= new ArrayList<String>();		//移動元ロケーション名
	ArrayList<String> SearchToLoc						= new ArrayList<String>();		//移動先ロケ
	ArrayList<String> SearchToLocName					= new ArrayList<String>();		//移動先ロケーション名
	ArrayList<String> SearchItemCd						= new ArrayList<String>();		//商品CD
	ArrayList<String> SearchItemName					= new ArrayList<String>();		//商品名
	ArrayList<String> SearchLot							= new ArrayList<String>();		//ロット
	ArrayList<String> SearchExpDateMin					= new ArrayList<String>();		//賞味期限最小
	ArrayList<String> SearchExpDateMax					= new ArrayList<String>();		//賞味期限最大
	ArrayList<String> SearchActualDateMin				= new ArrayList<String>();		//入荷日最小
	ArrayList<String> SearchActualDateMax				= new ArrayList<String>();		//入荷日最大
	ArrayList<Integer> SearchBeforeFromQtyMin			= new ArrayList<Integer>();		//（移動前）移動元在庫数最小
	ArrayList<Integer> SearchBeforeFromPlanQtyMin		= new ArrayList<Integer>();		//（移動前）移動元引当済数最小
	ArrayList<Integer> SearchBeforeFromPossibleQtyMin	= new ArrayList<Integer>();		//（移動前）移動元出荷可能数最小
	ArrayList<Integer> SearchBeforeToQtyMin				= new ArrayList<Integer>();		//（移動前）移動先在庫数最小
	ArrayList<Integer> SearchBeforeToPlanQtyMin			= new ArrayList<Integer>();		//（移動前）移動先引当済数最小
	ArrayList<Integer> SearchBeforeToPossibleQtyMin		= new ArrayList<Integer>();		//（移動前）移動先出荷可能数最小
	ArrayList<Integer> SearchMoveQtyMin					= new ArrayList<Integer>();		//移動数最小
	ArrayList<Integer> SearchAfterFromQtyMin			= new ArrayList<Integer>();		//（移動後）移動元在庫数最小
	ArrayList<Integer> SearchAfterFromPlanQtyMin		= new ArrayList<Integer>();		//（移動後）移動元引当済数最小
	ArrayList<Integer> SearchAfterFromPossibleQtyMin	= new ArrayList<Integer>();		//（移動後）移動元出荷可能数最小
	ArrayList<Integer> SearchAfterToQtyMin				= new ArrayList<Integer>();		//（移動後）移動先在庫数最小
	ArrayList<Integer> SearchAfterToPlanQtyMin			= new ArrayList<Integer>();		//（移動後）移動先引当済数最小
	ArrayList<Integer> SearchAfterToPossibleQtyMin		= new ArrayList<Integer>();		//（移動後）移動先出荷可能数最小
	ArrayList<Integer> SearchBeforeFromQtyMax			= new ArrayList<Integer>();		//（移動前）移動元在庫数最大
	ArrayList<Integer> SearchBeforeFromPlanQtyMax		= new ArrayList<Integer>();		//（移動前）移動元引当済数最大
	ArrayList<Integer> SearchBeforeFromPossibleQtyMax	= new ArrayList<Integer>();		//（移動前）移動元出荷可能数最大
	ArrayList<Integer> SearchBeforeToQtyMax				= new ArrayList<Integer>();		//（移動前）移動先在庫数最大
	ArrayList<Integer> SearchBeforeToPlanQtyMax			= new ArrayList<Integer>();		//（移動前）移動先引当済数最大
	ArrayList<Integer> SearchBeforeToPossibleQtyMax		= new ArrayList<Integer>();		//（移動前）移動先出荷可能数最大
	ArrayList<Integer> SearchMoveQtyMax					= new ArrayList<Integer>();		//移動数最大
	ArrayList<Integer> SearchAfterFromQtyMax			= new ArrayList<Integer>();		//（移動後）移動元在庫数最大
	ArrayList<Integer> SearchAfterFromPlanQtyMax		= new ArrayList<Integer>();		//（移動後）移動元引当済数最大
	ArrayList<Integer> SearchAfterFromPossibleQtyMax	= new ArrayList<Integer>();		//（移動後）移動元出荷可能数最大
	ArrayList<Integer> SearchAfterToQtyMax				= new ArrayList<Integer>();		//（移動後）移動先在庫数最大
	ArrayList<Integer> SearchAfterToPlanQtyMax			= new ArrayList<Integer>();		//（移動後）移動先引当済数最大
	ArrayList<Integer> SearchAfterToPossibleQtyMax		= new ArrayList<Integer>();		//（移動後）移動先出荷可能数最大
	ArrayList<String> SearchMoveCom						= new ArrayList<String>();		//移動コメント
	ArrayList<String> SearchEntryDateMin				= new ArrayList<String>();		//登録日最小
	ArrayList<String> SearchUpdateDateMin				= new ArrayList<String>();		//更新日最小
	ArrayList<String> SearchEntryDateMax				= new ArrayList<String>();		//登録日最大
	ArrayList<String> SearchUpdateDateMax				= new ArrayList<String>();		//更新日最大
	ArrayList<String> SearchEntryUser					= new ArrayList<String>();		//登録者
	ArrayList<String> SearchUpdateUser					= new ArrayList<String>();		//更新者
	boolean FromLocExactMatch							= true;							//Fromロケーション完全一致
	boolean ToLocExactMatch								= true;							//Toロケーション完全一致
	boolean AllSearch									= false;
	
	Object[][] StockMoveRt	= T100_StockMoveRt.StockMoveRt(
			SearchClCd,						//荷主コード
			SearchCLName,					//荷主名
			SearchWhCd,						//倉庫コード
			SearchClWHName,					//担当倉庫名
			SearchMoveNo,					//調整番号
			SearchFromLoc,					//移動元ロケ
			SearchFromLocName,				//移動元ロケーション名
			SearchToLoc,					//移動先ロケ
			SearchToLocName,				//移動先ロケーション名
			SearchItemCd,					//商品CD
			SearchItemName,					//商品名
			SearchLot,						//ロット
			SearchExpDateMin,				//賞味期限最小
			SearchExpDateMax,				//賞味期限最大
			SearchActualDateMin,			//入荷日最小
			SearchActualDateMax,			//入荷日最大
			SearchBeforeFromQtyMin,			//（移動前）移動元在庫数最小
			SearchBeforeFromPlanQtyMin,		//（移動前）移動元引当済数最小
			SearchBeforeFromPossibleQtyMin,	//（移動前）移動元出荷可能数最小
			SearchBeforeToQtyMin,			//（移動前）移動先在庫数最小
			SearchBeforeToPlanQtyMin,		//（移動前）移動先引当済数最小
			SearchBeforeToPossibleQtyMin,	//（移動前）移動先出荷可能数最小
			SearchMoveQtyMin,				//移動数最小
			SearchAfterFromQtyMin,			//（移動後）移動元在庫数最小
			SearchAfterFromPlanQtyMin,		//（移動後）移動元引当済数最小
			SearchAfterFromPossibleQtyMin,	//（移動後）移動元出荷可能数最小
			SearchAfterToQtyMin,			//（移動後）移動先在庫数最小
			SearchAfterToPlanQtyMin,		//（移動後）移動先引当済数最小
			SearchAfterToPossibleQtyMin,	//（移動後）移動先出荷可能数最小
			SearchBeforeFromQtyMax,			//（移動前）移動元在庫数最大
			SearchBeforeFromPlanQtyMax,		//（移動前）移動元引当済数最大
			SearchBeforeFromPossibleQtyMax,	//（移動前）移動元出荷可能数最大
			SearchBeforeToQtyMax,			//（移動前）移動先在庫数最大
			SearchBeforeToPlanQtyMax,		//（移動前）移動先引当済数最大
			SearchBeforeToPossibleQtyMax,	//（移動前）移動先出荷可能数最大
			SearchMoveQtyMax,				//移動数最大
			SearchAfterFromQtyMax,			//（移動後）移動元在庫数最大
			SearchAfterFromPlanQtyMax,		//（移動後）移動元引当済数最大
			SearchAfterFromPossibleQtyMax,	//（移動後）移動元出荷可能数最大
			SearchAfterToQtyMax,			//（移動後）移動先在庫数最大
			SearchAfterToPlanQtyMax,		//（移動後）移動先引当済数最大
			SearchAfterToPossibleQtyMax,	//（移動後）移動先出荷可能数最大
			SearchMoveCom,					//移動コメント
			SearchEntryDateMin,				//登録日最小
			SearchUpdateDateMin,			//更新日最小
			SearchEntryDateMax,				//登録日最大
			SearchUpdateDateMax,			//更新日最大
			SearchEntryUser,				//登録者
			SearchUpdateUser,				//更新者
			FromLocExactMatch,				//Fromロケーション完全一致
			ToLocExactMatch,				//Toロケーション完全一致
			AllSearch);
			
			String GetClCd					= (String)StockMoveRt[i][T100_StockMoveRt.ColClCd];					//荷主コード
			String GetCLName				= (String)StockMoveRt[i][T100_StockMoveRt.ColCLName];				//荷主名
			String GetCLName				= (String)StockMoveRt[i][T100_StockMoveRt.ColWhCd];					//倉庫コード
			String GetClWHName				= (String)StockMoveRt[i][T100_StockMoveRt.ColClWHName];				//担当倉庫名
			String GetMoveNo				= (String)StockMoveRt[i][T100_StockMoveRt.ColMoveNo];				//調整番号
			String GetFromLoc				= (String)StockMoveRt[i][T100_StockMoveRt.ColFromLoc];				//移動元ロケ
			String GetFromLocName			= (String)StockMoveRt[i][T100_StockMoveRt.ColFromLocName];			//移動元ロケーション名
			String GetToLoc					= (String)StockMoveRt[i][T100_StockMoveRt.ColToLoc];				//移動先ロケ
			String GetToLocName				= (String)StockMoveRt[i][T100_StockMoveRt.ColToLocName];			//移動先ロケーション名
			String GetItemCd				= (String)StockMoveRt[i][T100_StockMoveRt.ColItemCd];				//商品CD
			String GetItemName				= (String)StockMoveRt[i][T100_StockMoveRt.ColItemName];				//商品名
			String GetItemName01			= (String)StockMoveRt[i][T100_StockMoveRt.ColItemName01];			//商品表記名
			String GetItemName02			= (String)StockMoveRt[i][T100_StockMoveRt.ColItemName02];			//商品正式名
			String GetItemName03			= (String)StockMoveRt[i][T100_StockMoveRt.ColItemName03];			//商品略名
			String GetCtUnitQty				= (String)StockMoveRt[i][T100_StockMoveRt.ColCtUnitQty];			//カートン入数
			String GetCsUnitQty				= (String)StockMoveRt[i][T100_StockMoveRt.ColCsUnitQty];			//ケース入数
			String GetPlUnitQty				= (String)StockMoveRt[i][T100_StockMoveRt.ColPlUnitQty];			//パレット入数
			String GetUnitName				= (String)StockMoveRt[i][T100_StockMoveRt.ColUnitName];				//商品単位
			String GetCtUnitName			= (String)StockMoveRt[i][T100_StockMoveRt.ColCtUnitName];			//カートン商品単位
			String GetCsUnitName			= (String)StockMoveRt[i][T100_StockMoveRt.ColCsUnitName];			//ケース商品単位
			String GetPlUnitName			= (String)StockMoveRt[i][T100_StockMoveRt.ColPlUnitName];			//パレット商品単位
			String GetLot					= (String)StockMoveRt[i][T100_StockMoveRt.ColLot];					//ロット
			String GetExpDate				= (String)StockMoveRt[i][T100_StockMoveRt.ColExpDate];				//賞味期限
			String GetActualDate			= (String)StockMoveRt[i][T100_StockMoveRt.ColActualDate];			//入荷日
			int GetBeforeFromQty			= (int)StockMoveRt[i][T100_StockMoveRt.ColBeforeFromQty];			//（移動前）移動元在庫数
			int GetBeforeFromPlanQty		= (int)StockMoveRt[i][T100_StockMoveRt.ColBeforeFromPlanQty];		//（移動前）移動元引当済数
			int GetBeforeFromPossibleQty	= (int)StockMoveRt[i][T100_StockMoveRt.ColBeforeFromPossibleQty];	//移動前）移動元出荷可能数
			int GetBeforeToQty				= (int)StockMoveRt[i][T100_StockMoveRt.ColBeforeToQty];				//（移動前）移動先在庫数
			int GetBeforeToPlanQty			= (int)StockMoveRt[i][T100_StockMoveRt.ColBeforeToPlanQty];			//（移動前）移動先引当済数
			int GetBeforeToPossibleQty		= (int)StockMoveRt[i][T100_StockMoveRt.ColBeforeToPossibleQty];		//（移動前）移動先出荷可能数
			int GetMoveQty					= (int)StockMoveRt[i][T100_StockMoveRt.ColMoveQty];					//移動数
			int GetAfterFromQty				= (int)StockMoveRt[i][T100_StockMoveRt.ColAfterFromQty];			//（移動後）移動元在庫数
			int GetAfterFromPlanQty			= (int)StockMoveRt[i][T100_StockMoveRt.ColAfterFromPlanQty];		//（移動後）移動元引当済数
			int GetAfterFromPossibleQty		= (int)StockMoveRt[i][T100_StockMoveRt.ColAfterFromPossibleQty];	//（移動後）移動元出荷可能数
			int GetAfterToQty				= (int)StockMoveRt[i][T100_StockMoveRt.ColAfterToQty];				//（移動後）移動先在庫数
			int GetAfterToPlanQty			= (int)StockMoveRt[i][T100_StockMoveRt.ColAfterToPlanQty];			//（移動後）移動先引当済数
			int GetAfterToPossibleQty		= (int)StockMoveRt[i][T100_StockMoveRt.ColAfterToPossibleQty];		//（移動後）移動先出荷可能数
			String GetMoveCom01				= (String)StockMoveRt[i][T100_StockMoveRt.ColMoveCom01];			//移動コメント01
			String GetMoveCom02				= (String)StockMoveRt[i][T100_StockMoveRt.ColMoveCom02];			//移動コメント02
			String GetMoveCom03				= (String)StockMoveRt[i][T100_StockMoveRt.ColMoveCom03];			//移動コメント03
			String GetEntryDate				= (String)StockMoveRt[i][T100_StockMoveRt.ColEntryDate];			//登録日
			String GetUpdateDate			= (String)StockMoveRt[i][T100_StockMoveRt.ColUpdateDate];			//更新日
			String GetEntryUser				= (String)StockMoveRt[i][T100_StockMoveRt.ColEntryUser];			//登録者
			String GetUpdateUser			= (String)StockMoveRt[i][T100_StockMoveRt.ColUpdateUser];			//更新者
	*/
	
	static final int ColMoveNo						= 0;	//調整番号
	static final int ColFromLocName				= 1;	//移動元ロケーション名
	static final int ColToLocName					= 2;	//移動先ロケーション名
	static final int ColItemCd						= 3;	//商品CD
	static final int ColMoveQty					= 4;	//移動数
	static final int ColLot						= 5;	//ロット
	static final int ColExpDate					= 6;	//賞味期限
	static final int ColActualDate				= 7;	//入荷日
	static final int ColItemName					= 8;	//商品名
	static final int ColMoveCom01					= 9;	//移動コメント01
	static final int ColMoveCom02					=10;	//移動コメント02
	static final int ColMoveCom03					=11;	//移動コメント03
	static final int ColEntryDate					=12;	//登録日
	static final int ColUpdateDate				=13;	//更新日
	static final int ColEntryUser					=14;	//登録者
	static final int ColUpdateUser				=15;	//更新者
	static final int ColFromLoc					=16;	//移動元ロケ
	static final int ColToLoc						=17;	//移動先ロケ
	static final int ColItemName01				=18;	//商品表記名
	static final int ColItemName02				=19;	//商品正式名
	static final int ColItemName03				=20;	//商品略名
	static final int ColCtUnitQty					=21;	//カートン入数
	static final int ColCsUnitQty					=22;	//ケース入数
	static final int ColPlUnitQty					=23;	//パレット入数
	static final int ColUnitName					=24;	//商品単位
	static final int ColCtUnitName				=25;	//カートン商品単位
	static final int ColCsUnitName				=26;	//ケース商品単位
	static final int ColPlUnitName				=27;	//パレット商品単位
	static final int ColBeforeFromQty				=28;	//（移動前）移動元在庫数
	static final int ColBeforeFromPlanQty		=29;	//（移動前）移動元引当済数
	static final int ColBeforeFromPossibleQty	=30;	//（移動前）移動元出荷可能数
	static final int ColBeforeToQty				=31;	//（移動前）移動先在庫数
	static final int ColBeforeToPlanQty			=32;	//（移動前）移動先引当済数
	static final int ColBeforeToPossibleQty		=33;	//（移動前）移動先出荷可能数
	static final int ColAfterFromQty				=34;	//（移動後）移動元在庫数
	static final int ColAfterFromPlanQty			=35;	//（移動後）移動元引当済数
	static final int ColAfterFromPossibleQty		=36;	//（移動後）移動元出荷可能数
	static final int ColAfterToQty				=37;	//（移動後）移動先在庫数
	static final int ColAfterToPlanQty			=38;	//（移動後）移動先引当済数
	static final int ColAfterToPossibleQty		=39;	//（移動後）移動先出荷可能数
	static final int ColClCd						=40;	//荷主コード
	static final int ColCLName						=41;	//荷主名
	static final int ColWhCd						=42;	//倉庫コード
	static final int ColClWHName					=43;	//担当倉庫名
	
	
	
	public static Object[][] RtStockMoveRt(){
		Object[][] RtStockMoveRt = {
							 {"ClCd"					,ColClCd						,"String"	,"荷主コード"					,"Key"}
							,{"CLName"					,ColCLName						,"String"	,"荷主名"						,""}
							,{"WhCd"					,ColWhCd						,"String"	,"倉庫コード"					,"Key"}
							,{"ClWHName"				,ColClWHName					,"String"	,"担当倉庫名"					,""}
							,{"MoveNo"					,ColMoveNo						,"int"		,"調整番号"						,"Key"}
							,{"FromLoc"					,ColFromLoc					,"String"	,"移動元ロケ"					,""}
							,{"FromLocName"				,ColFromLocName				,"String"	,"移動元ロケーション名"			,""}
							,{"ToLoc"					,ColToLoc						,"String"	,"移動先ロケ"					,""}
							,{"ToLocName"				,ColToLocName					,"String"	,"移動先ロケーション名"			,""}
							,{"ItemCd"					,ColItemCd						,"String"	,"商品CD"						,""}
							,{"ItemName"				,ColItemName					,"String"	,"商品名"						,""}
							,{"ItemName01"				,ColItemName01				,"String"	,"商品表記名"					,""}
							,{"ItemName02"				,ColItemName02				,"String"	,"商品正式名"					,""}
							,{"ItemName03"				,ColItemName03				,"String"	,"商品略名"						,""}
							,{"CtUnitQty"				,ColCtUnitQty					,"int"		,"カートン入数"					,""}
							,{"CsUnitQty"				,ColCsUnitQty					,"int"		,"ケース入数"					,""}
							,{"PlUnitQty"				,ColPlUnitQty					,"int"		,"パレット入数"					,""}
							,{"UnitName"				,ColUnitName					,"String"	,"商品単位"						,""}
							,{"CtUnitName"				,ColCtUnitName				,"String"	,"カートン商品単位"				,""}
							,{"CsUnitName"				,ColCsUnitName				,"String"	,"ケース商品単位"				,""}
							,{"PlUnitName"				,ColPlUnitName				,"String"	,"パレット商品単位"				,""}
							,{"Lot"						,ColLot						,"String"	,"ロット"						,""}
							,{"ExpDate"					,ColExpDate					,"Date"		,"賞味期限"						,""}
							,{"ActualDate"				,ColActualDate				,"Date"		,"入荷日"						,""}
							,{"BeforeFromQty"			,ColBeforeFromQty				,"int"		,"（移動前）移動元在庫数"		,""}
							,{"BeforeFromPlanQty"		,ColBeforeFromPlanQty		,"int"		,"（移動前）移動元引当済数"		,""}
							,{"BeforeFromPossibleQty"	,ColBeforeFromPossibleQty	,"int"		,"（移動前）移動元出荷可能数"	,""}
							,{"BeforeToQty"				,ColBeforeToQty				,"int"		,"（移動前）移動先在庫数"		,""}
							,{"BeforeToPlanQty"			,ColBeforeToPlanQty			,"int"		,"（移動前）移動先引当済数"		,""}
							,{"BeforeToPossibleQty"		,ColBeforeToPossibleQty		,"int"		,"（移動前）移動先出荷可能数"	,""}
							,{"MoveQty"					,ColMoveQty					,"int"		,"移動数"						,""}
							,{"AfterFromQty"			,ColAfterFromQty				,"int"		,"（移動後）移動元在庫数"		,""}
							,{"AfterFromPlanQty"		,ColAfterFromPlanQty			,"int"		,"（移動後）移動元引当済数"		,""}
							,{"AfterFromPossibleQty"	,ColAfterFromPossibleQty		,"int"		,"（移動後）移動元出荷可能数"	,""}
							,{"AfterToQty"				,ColAfterToQty				,"int"		,"（移動後）移動先在庫数"		,""}
							,{"AfterToPlanQty"			,ColAfterToPlanQty			,"int"		,"（移動後）移動先引当済数"		,""}
							,{"AfterToPossibleQty"		,ColAfterToPossibleQty		,"int"		,"（移動後）移動先出荷可能数"	,""}
							,{"MoveCom01"				,ColMoveCom01					,"String"	,"移動コメント01"				,""}
							,{"MoveCom02"				,ColMoveCom02					,"String"	,"移動コメント02"				,""}
							,{"MoveCom03"				,ColMoveCom03					,"String"	,"移動コメント03"				,""}
							,{"EntryDate"				,ColEntryDate					,"DateTime"	,"登録日"						,""}
							,{"UpdateDate"				,ColUpdateDate				,"DateTime"	,"更新日"						,""}
							,{"EntryUser"				,ColEntryUser					,"String"	,"登録者"						,""}
							,{"UpdateUser"				,ColUpdateUser				,"String"	,"更新者"						,""}
							};
		return RtStockMoveRt;
	}
	
	
	public static Object[][] StockMoveRt(
			ArrayList<String> SearchClCd,						//荷主コード
			ArrayList<String> SearchCLName,						//荷主名
			ArrayList<String> SearchWhCd,						//倉庫コード
			ArrayList<String> SearchClWHName,					//担当倉庫名
			ArrayList<String> SearchMoveNo,						//調整番号
			ArrayList<String> SearchFromLoc,					//移動元ロケ
			ArrayList<String> SearchFromLocName,				//移動元ロケーション名
			ArrayList<String> SearchToLoc,						//移動先ロケ
			ArrayList<String> SearchToLocName,					//移動先ロケーション名
			ArrayList<String> SearchItemCd,						//商品CD
			ArrayList<String> SearchItemName,					//商品名
			ArrayList<String> SearchLot,						//ロット
			ArrayList<String> SearchExpDateMin,					//賞味期限最小
			ArrayList<String> SearchExpDateMax,					//賞味期限最大
			ArrayList<String> SearchActualDateMin,				//入荷日最小
			ArrayList<String> SearchActualDateMax,				//入荷日最大
			ArrayList<Integer> SearchBeforeFromQtyMin,			//（移動前）移動元在庫数最小
			ArrayList<Integer> SearchBeforeFromPlanQtyMin,		//（移動前）移動元引当済数最小
			ArrayList<Integer> SearchBeforeFromPossibleQtyMin,	//（移動前）移動元出荷可能数最小
			ArrayList<Integer> SearchBeforeToQtyMin,			//（移動前）移動先在庫数最小
			ArrayList<Integer> SearchBeforeToPlanQtyMin,		//（移動前）移動先引当済数最小
			ArrayList<Integer> SearchBeforeToPossibleQtyMin,	//（移動前）移動先出荷可能数最小
			ArrayList<Integer> SearchMoveQtyMin,				//移動数最小
			ArrayList<Integer> SearchAfterFromQtyMin,			//（移動後）移動元在庫数最小
			ArrayList<Integer> SearchAfterFromPlanQtyMin,		//（移動後）移動元引当済数最小
			ArrayList<Integer> SearchAfterFromPossibleQtyMin,	//（移動後）移動元出荷可能数最小
			ArrayList<Integer> SearchAfterToQtyMin,				//（移動後）移動先在庫数最小
			ArrayList<Integer> SearchAfterToPlanQtyMin,			//（移動後）移動先引当済数最小
			ArrayList<Integer> SearchAfterToPossibleQtyMin,		//（移動後）移動先出荷可能数最小
			ArrayList<Integer> SearchBeforeFromQtyMax,			//（移動前）移動元在庫数最大
			ArrayList<Integer> SearchBeforeFromPlanQtyMax,		//（移動前）移動元引当済数最大
			ArrayList<Integer> SearchBeforeFromPossibleQtyMax,	//（移動前）移動元出荷可能数最大
			ArrayList<Integer> SearchBeforeToQtyMax,			//（移動前）移動先在庫数最大
			ArrayList<Integer> SearchBeforeToPlanQtyMax,		//（移動前）移動先引当済数最大
			ArrayList<Integer> SearchBeforeToPossibleQtyMax,	//（移動前）移動先出荷可能数最大
			ArrayList<Integer> SearchMoveQtyMax,				//移動数最大
			ArrayList<Integer> SearchAfterFromQtyMax,			//（移動後）移動元在庫数最大
			ArrayList<Integer> SearchAfterFromPlanQtyMax,		//（移動後）移動元引当済数最大
			ArrayList<Integer> SearchAfterFromPossibleQtyMax,	//（移動後）移動元出荷可能数最大
			ArrayList<Integer> SearchAfterToQtyMax,				//（移動後）移動先在庫数最大
			ArrayList<Integer> SearchAfterToPlanQtyMax,			//（移動後）移動先引当済数最大
			ArrayList<Integer> SearchAfterToPossibleQtyMax,		//（移動後）移動先出荷可能数最大
			ArrayList<String> SearchMoveCom,					//移動コメント
			ArrayList<String> SearchEntryDateMin,				//登録日最小
			ArrayList<String> SearchUpdateDateMin,				//更新日最小
			ArrayList<String> SearchEntryDateMax,				//登録日最大
			ArrayList<String> SearchUpdateDateMax,				//更新日最大
			ArrayList<String> SearchEntryUser,					//登録者
			ArrayList<String> SearchUpdateUser,					//更新者
			boolean FromLocExactMatch,							//Fromロケーション完全一致
			boolean ToLocExactMatch,							//Toロケーション完全一致
			boolean AllSearch){
		//日付系最小は念のため00:00:00扱い
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
		
		SearchClCd						= B100_ArrayListControl.ArryListStringUniqueList(SearchClCd);						//荷主コード
		SearchCLName					= B100_ArrayListControl.ArryListStringUniqueList(SearchCLName);						//荷主名
		SearchWhCd						= B100_ArrayListControl.ArryListStringUniqueList(SearchWhCd);						//倉庫コード
		SearchClWHName					= B100_ArrayListControl.ArryListStringUniqueList(SearchClWHName);					//担当倉庫名
		SearchMoveNo					= B100_ArrayListControl.ArryListStringUniqueList(SearchMoveNo);						//調整番号
		SearchFromLoc					= B100_ArrayListControl.ArryListStringUniqueList(SearchFromLoc);					//移動元ロケ
		SearchFromLocName				= B100_ArrayListControl.ArryListStringUniqueList(SearchFromLocName);				//移動元ロケーション名
		SearchToLoc						= B100_ArrayListControl.ArryListStringUniqueList(SearchToLoc);						//移動先ロケ
		SearchToLocName					= B100_ArrayListControl.ArryListStringUniqueList(SearchToLocName);					//移動先ロケーション名
		SearchItemCd					= B100_ArrayListControl.ArryListStringUniqueList(SearchItemCd);						//商品CD
		SearchItemName					= B100_ArrayListControl.ArryListStringUniqueList(SearchItemName);					//商品名
		SearchLot						= B100_ArrayListControl.ArryListStringUniqueList(SearchLot);						//ロット
		SearchExpDateMin				= B100_ArrayListControl.ArryListStringUniqueList(SearchExpDateMin);					//賞味期限最小
		SearchExpDateMax				= B100_ArrayListControl.ArryListStringUniqueList(SearchExpDateMax);					//賞味期限最大
		SearchActualDateMin				= B100_ArrayListControl.ArryListStringUniqueList(SearchActualDateMin);				//入荷日最小
		SearchActualDateMax				= B100_ArrayListControl.ArryListStringUniqueList(SearchActualDateMax);				//入荷日最大
		SearchBeforeFromQtyMin			= B100_ArrayListControl.ArryListIntegerUniqueList(SearchBeforeFromQtyMin);			//（移動前）移動元在庫数最小
		SearchBeforeFromPlanQtyMin		= B100_ArrayListControl.ArryListIntegerUniqueList(SearchBeforeFromPlanQtyMin);		//（移動前）移動元引当済数最小
		SearchBeforeFromPossibleQtyMin	= B100_ArrayListControl.ArryListIntegerUniqueList(SearchBeforeFromPossibleQtyMin);	//（移動前）移動元出荷可能数最小
		SearchBeforeToQtyMin			= B100_ArrayListControl.ArryListIntegerUniqueList(SearchBeforeToQtyMin);			//（移動前）移動先在庫数最小
		SearchBeforeToPlanQtyMin		= B100_ArrayListControl.ArryListIntegerUniqueList(SearchBeforeToPlanQtyMin);		//（移動前）移動先引当済数最小
		SearchBeforeToPossibleQtyMin	= B100_ArrayListControl.ArryListIntegerUniqueList(SearchBeforeToPossibleQtyMin);	//（移動前）移動先出荷可能数最小
		SearchMoveQtyMin				= B100_ArrayListControl.ArryListIntegerUniqueList(SearchMoveQtyMin);				//移動数最小
		SearchAfterFromQtyMin			= B100_ArrayListControl.ArryListIntegerUniqueList(SearchAfterFromQtyMin);			//（移動後）移動元在庫数最小
		SearchAfterFromPlanQtyMin		= B100_ArrayListControl.ArryListIntegerUniqueList(SearchAfterFromPlanQtyMin);		//（移動後）移動元引当済数最小
		SearchAfterFromPossibleQtyMin	= B100_ArrayListControl.ArryListIntegerUniqueList(SearchAfterFromPossibleQtyMin);	//（移動後）移動元出荷可能数最小
		SearchAfterToQtyMin				= B100_ArrayListControl.ArryListIntegerUniqueList(SearchAfterToQtyMin);				//（移動後）移動先在庫数最小
		SearchAfterToPlanQtyMin			= B100_ArrayListControl.ArryListIntegerUniqueList(SearchAfterToPlanQtyMin);			//（移動後）移動先引当済数最小
		SearchAfterToPossibleQtyMin		= B100_ArrayListControl.ArryListIntegerUniqueList(SearchAfterToPossibleQtyMin);		//（移動後）移動先出荷可能数最小
		SearchBeforeFromQtyMax			= B100_ArrayListControl.ArryListIntegerUniqueList(SearchBeforeFromQtyMax);			//（移動前）移動元在庫数最大
		SearchBeforeFromPlanQtyMax		= B100_ArrayListControl.ArryListIntegerUniqueList(SearchBeforeFromPlanQtyMax);		//（移動前）移動元引当済数最大
		SearchBeforeFromPossibleQtyMax	= B100_ArrayListControl.ArryListIntegerUniqueList(SearchBeforeFromPossibleQtyMax);	//（移動前）移動元出荷可能数最大
		SearchBeforeToQtyMax			= B100_ArrayListControl.ArryListIntegerUniqueList(SearchBeforeToQtyMax);			//（移動前）移動先在庫数最大
		SearchBeforeToPlanQtyMax		= B100_ArrayListControl.ArryListIntegerUniqueList(SearchBeforeToPlanQtyMax);		//（移動前）移動先引当済数最大
		SearchBeforeToPossibleQtyMax	= B100_ArrayListControl.ArryListIntegerUniqueList(SearchBeforeToPossibleQtyMax);	//（移動前）移動先出荷可能数最大
		SearchMoveQtyMax				= B100_ArrayListControl.ArryListIntegerUniqueList(SearchMoveQtyMax);				//移動数最大
		SearchAfterFromQtyMax			= B100_ArrayListControl.ArryListIntegerUniqueList(SearchAfterFromQtyMax);			//（移動後）移動元在庫数最大
		SearchAfterFromPlanQtyMax		= B100_ArrayListControl.ArryListIntegerUniqueList(SearchAfterFromPlanQtyMax);		//（移動後）移動元引当済数最大
		SearchAfterFromPossibleQtyMax	= B100_ArrayListControl.ArryListIntegerUniqueList(SearchAfterFromPossibleQtyMax);	//（移動後）移動元出荷可能数最大
		SearchAfterToQtyMax				= B100_ArrayListControl.ArryListIntegerUniqueList(SearchAfterToQtyMax);				//（移動後）移動先在庫数最大
		SearchAfterToPlanQtyMax			= B100_ArrayListControl.ArryListIntegerUniqueList(SearchAfterToPlanQtyMax);			//（移動後）移動先引当済数最大
		SearchAfterToPossibleQtyMax		= B100_ArrayListControl.ArryListIntegerUniqueList(SearchAfterToPossibleQtyMax);		//（移動後）移動先出荷可能数最大
		SearchMoveCom					= B100_ArrayListControl.ArryListStringUniqueList(SearchMoveCom);					//移動コメント
		SearchEntryDateMin				= B100_ArrayListControl.ArryListStringUniqueList(SearchEntryDateMin);				//登録日最小
		SearchUpdateDateMin				= B100_ArrayListControl.ArryListStringUniqueList(SearchUpdateDateMin);				//更新日最小
		SearchEntryDateMax				= B100_ArrayListControl.ArryListStringUniqueList(SearchEntryDateMax);				//登録日最大
		SearchUpdateDateMax				= B100_ArrayListControl.ArryListStringUniqueList(SearchUpdateDateMax);				//更新日最大
		SearchEntryUser					= B100_ArrayListControl.ArryListStringUniqueList(SearchEntryUser);					//登録者
		SearchUpdateUser				= B100_ArrayListControl.ArryListStringUniqueList(SearchUpdateUser);					//更新者
		
		Object[][] rt = new Object[0][RtStockMoveRt().length];
		
		boolean SearchKick = false;
		if(AllSearch) {SearchKick = true;}
		
		String sql = " select "
				+"(WW0017StockMove.ClCd) 					as ClCd						,\n"	//荷主コード
				+"(KM0030_CLIENTMST.CLName01)				as CLName					,\n"	//荷主名
				+"(WW0017StockMove.WhCd) 					as WhCd						,\n"	//倉庫コード
				+"(KM0010_WHMST.WHName)						as ClWHName					,\n"	//担当倉庫名
				+"(WW0017StockMove.MoveNo) 					as MoveNo					,\n"	//調整番号
				+"(WW0017StockMove.FromLoc) 				as FromLoc					,\n"	//移動元ロケ
				+"(FLoc.LocName)          					as FromLocName				,\n"	//移動元ロケーション名
				+"(WW0017StockMove.ToLoc) 					as ToLoc					,\n"	//移動先ロケ
				+"(TLoc.LocName)          					as ToLocName				,\n"	//移動先ロケーション名
				+"(WW0017StockMove.ItemCd) 					as ItemCd					,\n"	//商品CD
				+"(WW0017StockMove.ItemName) 				as ItemName					,\n"	//商品名
				+"(KM0060_ITEMMST.ItemName01)				as ItemName01				,\n"	//商品表記名
				+"(KM0060_ITEMMST.ItemName02)				as ItemName02				,\n"	//商品正式名
				+"(KM0060_ITEMMST.ItemName03)				as ItemName03				,\n"	//商品略名
				+"(KM0061_ITEMMSTSUB.CtQty)					as CtUnitQty				,\n"	//カートン入数
				+"(KM0061_ITEMMSTSUB.CsQty)					as CsUnitQty				,\n"	//ケース入数
				+"(KM0061_ITEMMSTSUB.PlQty)					as PlUnitQty				,\n"	//パレット入数
				+"(KM0060_ITEMMST.UnitName)					as UnitName					,\n"	//商品単位
				+"(KM0061_ITEMMSTSUB.CtUnitName)			as CtUnitName				,\n"	//カートン商品単位
				+"(KM0061_ITEMMSTSUB.CsUnitName)			as CsUnitName				,\n"	//ケース商品単位
				+"(KM0061_ITEMMSTSUB.PlUnitName)			as PlUnitName				,\n"	//パレット商品単位
				+"(WW0017StockMove.Lot) 					as Lot						,\n"	//ロット
				+"(WW0017StockMove.ExpDate) 				as ExpDate					,\n"	//賞味期限
				+"(WW0017StockMove.ActualDate) 				as ActualDate				,\n"	//入荷日
				+"(WW0017StockMove.BeforeFromQty) 			as BeforeFromQty			,\n"	//（移動前）移動元在庫数
				+"(WW0017StockMove.BeforeFromPlanQty) 		as BeforeFromPlanQty		,\n"	//（移動前）移動元引当済数
				+"(WW0017StockMove.BeforeFromPossibleQty) 	as BeforeFromPossibleQty	,\n"	//（移動前）移動元出荷可能数
				+"(WW0017StockMove.BeforeToQty) 			as BeforeToQty				,\n"	//（移動前）移動先在庫数
				+"(WW0017StockMove.BeforeToPlanQty) 		as BeforeToPlanQty			,\n"	//（移動前）移動先引当済数
				+"(WW0017StockMove.BeforeToPossibleQty) 	as BeforeToPossibleQty		,\n"	//（移動前）移動先出荷可能数
				+"(WW0017StockMove.MoveQty) 				as MoveQty					,\n"	//移動数
				+"(WW0017StockMove.AfterFromQty) 			as AfterFromQty				,\n"	//（移動後）移動元在庫数
				+"(WW0017StockMove.AfterFromPlanQty) 		as AfterFromPlanQty			,\n"	//（移動後）移動元引当済数
				+"(WW0017StockMove.AfterFromPossibleQty) 	as AfterFromPossibleQty		,\n"	//（移動後）移動元出荷可能数
				+"(WW0017StockMove.AfterToQty) 				as AfterToQty				,\n"	//（移動後）移動先在庫数
				+"(WW0017StockMove.AfterToPlanQty) 			as AfterToPlanQty			,\n"	//（移動後）移動先引当済数
				+"(WW0017StockMove.AfterToPossibleQty) 		as AfterToPossibleQty		,\n"	//（移動後）移動先出荷可能数
				+"(WW0017StockMove.MoveCom01) 				as MoveCom01				,\n"	//移動コメント01
				+"(WW0017StockMove.MoveCom02) 				as MoveCom02				,\n"	//移動コメント02
				+"(WW0017StockMove.MoveCom03) 				as MoveCom03				,\n"	//移動コメント03
				+"(WW0017StockMove.EntryDate) 				as EntryDate				,\n"	//登録日
				+"(WW0017StockMove.UpdateDate) 				as UpdateDate				,\n"	//更新日
				+"(WW0017StockMove.EntryUser) 				as EntryUser				,\n"	//登録者
				+"(WW0017StockMove.UpdateUser) 				as UpdateUser				\n"		//更新者
				+ " from "+A00000_Main.MySqlDefaultSchemaWANKO+".WW0017StockMove\n"
				+ " left outer join "+A00000_Main.MySqlDefaultSchemaWANKO+".WM0010LOCATIONMST as FLoc"
				+ " on("
				+ " WW0017StockMove.ClCd = FLoc.ClCd"
				+ " and WW0017StockMove.WhCd = FLoc.WhCd"
				+ " and WW0017StockMove.FromLoc= FLoc.Loc"
				+ ")\n"
				+ " left outer join "+A00000_Main.MySqlDefaultSchemaWANKO+".WM0010LOCATIONMST as TLoc"
				+ " on("
				+ " WW0017StockMove.ClCd = TLoc.ClCd"
				+ " and WW0017StockMove.WhCd = TLoc.WhCd"
				+ " and WW0017StockMove.ToLoc= TLoc.Loc"
				+ ")\n"
				+ " left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0030_CLIENTMST"
				+ " on("
				+ " WW0017StockMove.WhCd = KM0030_CLIENTMST.WHCD"
				+ " and WW0017StockMove.ClCd = KM0030_CLIENTMST.cl_cd"
				+ ")\n"
				+ " left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP"
				+ " on("
				+ " KM0030_CLIENTMST.ClGpCD = KM0031_CLIENT_GROUP.ClGpCD"
				+ ")\n"
				+ " left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST"
				+ " on("
				+ " WW0017StockMove.WhCd = KM0010_WHMST.WHCD"
				+ ")\n"
				+ " left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0060_ITEMMST"
				+ " on("
				+ " KM0030_CLIENTMST.ClGpCD = KM0060_ITEMMST.ClGpCd"
				+ " and WW0017StockMove.ItemCd = KM0060_ITEMMST.ItemCd"
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
				sql = sql + " WW0017StockMove.ClCd = ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchCLName && 0<SearchCLName.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchCLName.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KM0030_CLIENTMST.CLName01 like ?";
				sql = sql + " or KM0030_CLIENTMST.CLName02 like ?";
				sql = sql + " or KM0030_CLIENTMST.CLName03 like ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchWhCd && 0<SearchWhCd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchWhCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0017StockMove.WhCd = ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchClWHName && 0<SearchClWHName.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchClWHName.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KM0010_WHMST.WHName Like ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchMoveNo && 0<SearchMoveNo.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchMoveNo.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0017StockMove.MoveNo = ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchFromLoc && 0<SearchFromLoc.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchFromLoc.size();i++){
				if(0<i){sql = sql + " or ";}
				if(FromLocExactMatch) {
					sql = sql + " WW0017StockMove.FromLoc = ?";
				}else {
					sql = sql + " WW0017StockMove.FromLoc Like ?";
				}
			}
			sql = sql + ")\n";
		}
		if(null!=SearchFromLocName && 0<SearchFromLocName.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchFromLocName.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " FLoc.LocName Like ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchToLoc && 0<SearchToLoc.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchToLoc.size();i++){
				if(0<i){sql = sql + " or ";}
				if(ToLocExactMatch) {
					sql = sql + " WW0017StockMove.ToLoc = ?";
				}else {
					sql = sql + " WW0017StockMove.ToLoc Like ?";
				}
			}
			sql = sql + ")\n";
		}
		if(null!=SearchToLocName && 0<SearchToLocName.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchToLocName.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " TLoc.LocName Like ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchItemCd && 0<SearchItemCd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchItemCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0017StockMove.ItemCd = ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchItemName && 0<SearchItemName.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchItemName.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0017StockMove.ItemName Like ?";
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
				sql = sql + " WW0017StockMove.Lot = ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchExpDateMin && 0<SearchExpDateMin.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchExpDateMin.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0017StockMove.ExpDate <= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchExpDateMax && 0<SearchExpDateMax.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchExpDateMax.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0017StockMove.ExpDate > ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchActualDateMin && 0<SearchActualDateMin.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchActualDateMin.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0017StockMove.ActualDate <= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchActualDateMax && 0<SearchActualDateMax.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchActualDateMax.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0017StockMove.ActualDate > ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchBeforeFromQtyMin && 0<SearchBeforeFromQtyMin.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchBeforeFromQtyMin.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0017StockMove.BeforeFromQty <= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchBeforeFromPlanQtyMin && 0<SearchBeforeFromPlanQtyMin.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchBeforeFromPlanQtyMin.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0017StockMove.BeforeFromPlanQty <= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchBeforeFromPossibleQtyMin && 0<SearchBeforeFromPossibleQtyMin.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchBeforeFromPossibleQtyMin.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0017StockMove.BeforeFromPossibleQty <= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchBeforeToQtyMin && 0<SearchBeforeToQtyMin.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchBeforeToQtyMin.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0017StockMove.BeforeToQty <= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchBeforeToPlanQtyMin && 0<SearchBeforeToPlanQtyMin.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchBeforeToPlanQtyMin.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0017StockMove.BeforeToPlanQty <= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchBeforeToPossibleQtyMin && 0<SearchBeforeToPossibleQtyMin.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchBeforeToPossibleQtyMin.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0017StockMove.BeforeToPossibleQty <= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchMoveQtyMin && 0<SearchMoveQtyMin.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchMoveQtyMin.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0017StockMove.MoveQty <= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchAfterFromQtyMin && 0<SearchAfterFromQtyMin.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchAfterFromQtyMin.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0017StockMove.AfterFromQty <= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchAfterFromPlanQtyMin && 0<SearchAfterFromPlanQtyMin.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchAfterFromPlanQtyMin.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0017StockMove.AfterFromPlanQty <= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchAfterFromPossibleQtyMin && 0<SearchAfterFromPossibleQtyMin.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchAfterFromPossibleQtyMin.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0017StockMove.AfterFromPossibleQty <= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchAfterToQtyMin && 0<SearchAfterToQtyMin.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchAfterToQtyMin.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0017StockMove.AfterToQty <= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchAfterToPlanQtyMin && 0<SearchAfterToPlanQtyMin.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchAfterToPlanQtyMin.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0017StockMove.AfterToPlanQty <= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchAfterToPossibleQtyMin && 0<SearchAfterToPossibleQtyMin.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchAfterToPossibleQtyMin.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0017StockMove.AfterToPossibleQty <= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchBeforeFromQtyMax && 0<SearchBeforeFromQtyMax.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchBeforeFromQtyMax.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0017StockMove.BeforeFromQty >= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchBeforeFromPlanQtyMax && 0<SearchBeforeFromPlanQtyMax.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchBeforeFromPlanQtyMax.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0017StockMove.BeforeFromPlanQty >= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchBeforeFromPossibleQtyMax && 0<SearchBeforeFromPossibleQtyMax.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchBeforeFromPossibleQtyMax.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0017StockMove.BeforeFromPossibleQty >= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchBeforeToQtyMax && 0<SearchBeforeToQtyMax.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchBeforeToQtyMax.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0017StockMove.BeforeToQty >= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchBeforeToPlanQtyMax && 0<SearchBeforeToPlanQtyMax.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchBeforeToPlanQtyMax.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0017StockMove.BeforeToPlanQty >= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchBeforeToPossibleQtyMax && 0<SearchBeforeToPossibleQtyMax.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchBeforeToPossibleQtyMax.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0017StockMove.BeforeToPossibleQty >= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchMoveQtyMax && 0<SearchMoveQtyMax.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchMoveQtyMax.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0017StockMove.MoveQty >= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchAfterFromQtyMax && 0<SearchAfterFromQtyMax.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchAfterFromQtyMax.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0017StockMove.AfterFromQty >= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchAfterFromPlanQtyMax && 0<SearchAfterFromPlanQtyMax.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchAfterFromPlanQtyMax.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0017StockMove.AfterFromPlanQty >= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchAfterFromPossibleQtyMax && 0<SearchAfterFromPossibleQtyMax.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchAfterFromPossibleQtyMax.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0017StockMove.AfterFromPossibleQty >= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchAfterToQtyMax && 0<SearchAfterToQtyMax.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchAfterToQtyMax.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0017StockMove.AfterToQty >= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchAfterToPlanQtyMax && 0<SearchAfterToPlanQtyMax.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchAfterToPlanQtyMax.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0017StockMove.AfterToPlanQty >= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchAfterToPossibleQtyMax && 0<SearchAfterToPossibleQtyMax.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchAfterToPossibleQtyMax.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0017StockMove.AfterToPossibleQty >= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchMoveCom && 0<SearchMoveCom.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchMoveCom.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0017StockMove.MoveCom01 Like ?";
				sql = sql + " or WW0017StockMove.MoveCom02 Like ?";
				sql = sql + " or WW0017StockMove.MoveCom03 Like ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchEntryDateMin && 0<SearchEntryDateMin.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchEntryDateMin.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0017StockMove.EntryDate <= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchUpdateDateMin && 0<SearchUpdateDateMin.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchUpdateDateMin.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0017StockMove.UpdateDate < = ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchEntryDateMax && 0<SearchEntryDateMax.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchEntryDateMax.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0017StockMove.EntryDate >= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchUpdateDateMax && 0<SearchUpdateDateMax.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchUpdateDateMax.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0017StockMove.UpdateDate >= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchEntryUser && 0<SearchEntryUser.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchEntryUser.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0017StockMove.EntryUser Like ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchUpdateUser && 0<SearchUpdateUser.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchUpdateUser.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0017StockMove.UpdateUser Like ?";
			}
			sql = sql + ")\n";
		}
		
		sql = sql + " order by WW0017StockMove.EntryDate,WW0017StockMove.WhCd,WW0017StockMove.ClCd,WW0017StockMove.MoveNo \n";
		
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
				if(null!=SearchCLName && 0<SearchCLName.size()){
					for(int i=0;i<SearchCLName.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchCLName.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchCLName.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchCLName.get(i)+"%");
					}
				}
				if(null!=SearchWhCd && 0<SearchWhCd.size()){
					for(int i=0;i<SearchWhCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchWhCd.get(i)+"");
					}
				}
				if(null!=SearchClWHName && 0<SearchClWHName.size()){
					for(int i=0;i<SearchClWHName.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchClWHName.get(i)+"%");
					}
				}
				if(null!=SearchMoveNo && 0<SearchMoveNo.size()){
					for(int i=0;i<SearchMoveNo.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchMoveNo.get(i)+"");
					}
				}
				if(null!=SearchFromLoc && 0<SearchFromLoc.size()){
					for(int i=0;i<SearchFromLoc.size();i++){
						if(FromLocExactMatch) {
							StmtCount = StmtCount+1;
							stmt01.setString(StmtCount, ""+SearchFromLoc.get(i)+"");
						}else {
							StmtCount = StmtCount+1;
							stmt01.setString(StmtCount, ""+SearchFromLoc.get(i)+"%");
						}
					}
				}
				if(null!=SearchFromLocName && 0<SearchFromLocName.size()){
					for(int i=0;i<SearchFromLocName.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchFromLocName.get(i)+"%");
					}
				}
				if(null!=SearchToLoc && 0<SearchToLoc.size()){
					for(int i=0;i<SearchToLoc.size();i++){
						if(ToLocExactMatch) {
							StmtCount = StmtCount+1;
							stmt01.setString(StmtCount, ""+SearchToLoc.get(i)+"");
						}else {
							StmtCount = StmtCount+1;
							stmt01.setString(StmtCount, ""+SearchToLoc.get(i)+"%");
						}
					}
				}
				if(null!=SearchToLocName && 0<SearchToLocName.size()){
					for(int i=0;i<SearchToLocName.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchToLocName.get(i)+"");
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
				if(null!=SearchBeforeFromQtyMin && 0<SearchBeforeFromQtyMin.size()){
					for(int i=0;i<SearchBeforeFromQtyMin.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchBeforeFromQtyMin.get(i)+"");
					}
				}
				if(null!=SearchBeforeFromPlanQtyMin && 0<SearchBeforeFromPlanQtyMin.size()){
					for(int i=0;i<SearchBeforeFromPlanQtyMin.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchBeforeFromPlanQtyMin.get(i)+"");
					}
				}
				if(null!=SearchBeforeFromPossibleQtyMin && 0<SearchBeforeFromPossibleQtyMin.size()){
					for(int i=0;i<SearchBeforeFromPossibleQtyMin.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchBeforeFromPossibleQtyMin.get(i)+"");
					}
				}
				if(null!=SearchBeforeToQtyMin && 0<SearchBeforeToQtyMin.size()){
					for(int i=0;i<SearchBeforeToQtyMin.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchBeforeToQtyMin.get(i)+"");
					}
				}
				if(null!=SearchBeforeToPlanQtyMin && 0<SearchBeforeToPlanQtyMin.size()){
					for(int i=0;i<SearchBeforeToPlanQtyMin.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchBeforeToPlanQtyMin.get(i)+"");
					}
				}
				if(null!=SearchBeforeToPossibleQtyMin && 0<SearchBeforeToPossibleQtyMin.size()){
					for(int i=0;i<SearchBeforeToPossibleQtyMin.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchBeforeToPossibleQtyMin.get(i)+"");
					}
				}
				if(null!=SearchMoveQtyMin && 0<SearchMoveQtyMin.size()){
					for(int i=0;i<SearchMoveQtyMin.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchMoveQtyMin.get(i)+"");
					}
				}
				if(null!=SearchAfterFromQtyMin && 0<SearchAfterFromQtyMin.size()){
					for(int i=0;i<SearchAfterFromQtyMin.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchAfterFromQtyMin.get(i)+"");
					}
				}
				if(null!=SearchAfterFromPlanQtyMin && 0<SearchAfterFromPlanQtyMin.size()){
					for(int i=0;i<SearchAfterFromPlanQtyMin.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchAfterFromPlanQtyMin.get(i)+"");
					}
				}
				if(null!=SearchAfterFromPossibleQtyMin && 0<SearchAfterFromPossibleQtyMin.size()){
					for(int i=0;i<SearchAfterFromPossibleQtyMin.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchAfterFromPossibleQtyMin.get(i)+"");
					}
				}
				if(null!=SearchAfterToQtyMin && 0<SearchAfterToQtyMin.size()){
					for(int i=0;i<SearchAfterToQtyMin.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchAfterToQtyMin.get(i)+"");
					}
				}
				if(null!=SearchAfterToPlanQtyMin && 0<SearchAfterToPlanQtyMin.size()){
					for(int i=0;i<SearchAfterToPlanQtyMin.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchAfterToPlanQtyMin.get(i)+"");
					}
				}
				if(null!=SearchAfterToPossibleQtyMin && 0<SearchAfterToPossibleQtyMin.size()){
					for(int i=0;i<SearchAfterToPossibleQtyMin.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchAfterToPossibleQtyMin.get(i)+"");
					}
				}
				if(null!=SearchBeforeFromQtyMax && 0<SearchBeforeFromQtyMax.size()){
					for(int i=0;i<SearchBeforeFromQtyMax.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchBeforeFromQtyMax.get(i)+"");
					}
				}
				if(null!=SearchBeforeFromPlanQtyMax && 0<SearchBeforeFromPlanQtyMax.size()){
					for(int i=0;i<SearchBeforeFromPlanQtyMax.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchBeforeFromPlanQtyMax.get(i)+"");
					}
				}
				if(null!=SearchBeforeFromPossibleQtyMax && 0<SearchBeforeFromPossibleQtyMax.size()){
					for(int i=0;i<SearchBeforeFromPossibleQtyMax.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchBeforeFromPossibleQtyMax.get(i)+"");
					}
				}
				if(null!=SearchBeforeToQtyMax && 0<SearchBeforeToQtyMax.size()){
					for(int i=0;i<SearchBeforeToQtyMax.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchBeforeToQtyMax.get(i)+"");
					}
				}
				if(null!=SearchBeforeToPlanQtyMax && 0<SearchBeforeToPlanQtyMax.size()){
					for(int i=0;i<SearchBeforeToPlanQtyMax.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchBeforeToPlanQtyMax.get(i)+"");
					}
				}
				if(null!=SearchBeforeToPossibleQtyMax && 0<SearchBeforeToPossibleQtyMax.size()){
					for(int i=0;i<SearchBeforeToPossibleQtyMax.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchBeforeToPossibleQtyMax.get(i)+"");
					}
				}
				if(null!=SearchMoveQtyMax && 0<SearchMoveQtyMax.size()){
					for(int i=0;i<SearchMoveQtyMax.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchMoveQtyMax.get(i)+"");
					}
				}
				if(null!=SearchAfterFromQtyMax && 0<SearchAfterFromQtyMax.size()){
					for(int i=0;i<SearchAfterFromQtyMax.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchAfterFromQtyMax.get(i)+"");
					}
				}
				if(null!=SearchAfterFromPlanQtyMax && 0<SearchAfterFromPlanQtyMax.size()){
					for(int i=0;i<SearchAfterFromPlanQtyMax.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchAfterFromPlanQtyMax.get(i)+"");
					}
				}
				if(null!=SearchAfterFromPossibleQtyMax && 0<SearchAfterFromPossibleQtyMax.size()){
					for(int i=0;i<SearchAfterFromPossibleQtyMax.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchAfterFromPossibleQtyMax.get(i)+"");
					}
				}
				if(null!=SearchAfterToQtyMax && 0<SearchAfterToQtyMax.size()){
					for(int i=0;i<SearchAfterToQtyMax.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchAfterToQtyMax.get(i)+"");
					}
				}
				if(null!=SearchAfterToPlanQtyMax && 0<SearchAfterToPlanQtyMax.size()){
					for(int i=0;i<SearchAfterToPlanQtyMax.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchAfterToPlanQtyMax.get(i)+"");
					}
				}
				if(null!=SearchAfterToPossibleQtyMax && 0<SearchAfterToPossibleQtyMax.size()){
					for(int i=0;i<SearchAfterToPossibleQtyMax.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchAfterToPossibleQtyMax.get(i)+"");
					}
				}
				if(null!=SearchMoveCom && 0<SearchMoveCom.size()){
					for(int i=0;i<SearchMoveCom.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchMoveCom.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchMoveCom.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchMoveCom.get(i)+"%");
					}
				}
				if(null!=SearchEntryDateMin && 0<SearchEntryDateMin.size()){
					for(int i=0;i<SearchEntryDateMin.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchEntryDateMin.get(i)+"");
					}
				}
				if(null!=SearchUpdateDateMin && 0<SearchUpdateDateMin.size()){
					for(int i=0;i<SearchUpdateDateMin.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchUpdateDateMin.get(i)+"");
					}
				}
				if(null!=SearchEntryDateMax && 0<SearchEntryDateMax.size()){
					for(int i=0;i<SearchEntryDateMax.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchEntryDateMax.get(i)+"");
					}
				}
				if(null!=SearchUpdateDateMax && 0<SearchUpdateDateMax.size()){
					for(int i=0;i<SearchUpdateDateMax.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchUpdateDateMax.get(i)+"");
					}
				}
				if(null!=SearchEntryUser && 0<SearchEntryUser.size()){
					for(int i=0;i<SearchEntryUser.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchEntryUser.get(i)+"%");
					}
				}
				if(null!=SearchUpdateUser && 0<SearchUpdateUser.size()){
					for(int i=0;i<SearchUpdateUser.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchUpdateUser.get(i)+"%");
					}
				}

				rset01 = stmt01.executeQuery();
				
				rt = B100_RtObjectCreate.B100_RtObjectCreate(rset01,RtStockMoveRt());
				
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