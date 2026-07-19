import java.util.ArrayList;

public class Tools100_StockMoveControl{
	//在庫From,To,Qty受け取って在庫移動する
	//複数行まとめて処理するとエラー発生時に在庫移動履歴が崩れる可能性があります
	//処理スピードよりも厳密さを優先して
	//厳密に処理を行う場合は　StockMoveControl(Object[][] TgtData)　を呼び出すときのTgtDataを1明細のデータにして複数回呼んでください
	
	/*投入データと戻りデータ共通*/
	static final int ColClCd						=  0;	//荷主コード
	static final int ColWhCd						=  1;	//倉庫コード
	static final int ColFromLoc					=  2;	//ロケーション
	static final int ColToLoc						=  3;	//ロケーション
	static final int ColItemCd						=  4;	//商品コード
	static final int ColLot						=  5;	//ロット
	static final int ColExpdate					=  6;	//消費期限
	static final int ColActualDate				=  7;	//入荷実績日
	static final int ColMoveQty					=  8;	//移動数
	static final int ColMoveCom01					=  9;	//移動コメント01
	static final int ColMoveCom02					= 10;	//移動コメント02
	static final int ColMoveCom03					= 11;	//移動コメント03
	
	/*戻りデータ部*/
	static final int ColBeforeFromQty				= 12;	//（移動前）移動元在庫数
	static final int ColBeforeFromPlanQty		= 13;	//（移動前）移動元引当済数
	static final int ColBeforeFromPossibleQty	= 14;	//（移動前）移動元出荷可能数
	static final int ColBeforeToQty				= 15;	//（移動前）移動先在庫数
	static final int ColBeforeToPlanQty			= 16;	//（移動前）移動先引当済数
	static final int ColBeforeToPossibleQty		= 17;	//（移動前）移動先出荷可能数
	static final int ColAfterFromQty				= 18;	//（移動後）移動元在庫数
	static final int ColAfterFromPlanQty			= 19;	//（移動後）移動元引当済数
	static final int ColAfterFromPossibleQty		= 20;	//（移動後）移動元出荷可能数
	static final int ColAfterToQty				= 21;	//（移動後）移動先在庫数
	static final int ColAfterToPlanQty			= 22;	//（移動後）移動先引当済数
	static final int ColAfterToPossibleQty		= 23;	//（移動後）移動先出荷可能数
	static final int ColMoveNo						= 24;	//移動番号
	static final int ColErrFg						= 25;	//エラーフラグ
	static final int ColErrList					= 26;	//エラーメッセージ
	
	
	public static Object[][] StockMoveControlLayout() {
		Object[][] Layout = {
							 {"ClCd"		,ColClCd			,"String"	,"荷主コード"}
							,{"WhCd"		,ColWhCd			,"String"	,"倉庫コード"}
							,{"FromLoc"		,ColFromLoc		,"String"	,"移動元ロケーション"}
							,{"FromLoc"		,ColToLoc			,"String"	,"移動先ロケーション"}
							,{"ItemCd"		,ColItemCd			,"String"	,"商品コード"}
							,{"Lot"			,ColLot			,"String"	,"ロット"}
							,{"Expdate"		,ColExpdate		,"Date"		,"消費期限"}
							,{"ActualDate"	,ColActualDate	,"Date"		,"入荷実績日"}
							,{"ControlQty"	,ColMoveQty		,"int"		,"移動数"}
							,{"MoveCom01"	,ColMoveCom01		,"String"	,"移動コメント01"}
							,{"MoveCom02"	,ColMoveCom02		,"String"	,"移動コメント02"}
							,{"MoveCom03"	,ColMoveCom03		,"String"	,"移動コメント03"}
							};
		return Layout;
	}
	
	public static Object[][] RtStockMoveControl(){
		Object[][] Layout = {
							 {"ClCd"					,ColClCd						,"String"		,"荷主コード"					,""}
							,{"WhCd"					,ColWhCd						,"String"		,"倉庫コード"					,""}
							,{"FromLoc"					,ColFromLoc					,"String"		,"移動元ロケーション"			,""}
							,{"FromLoc"					,ColToLoc						,"String"		,"移動先ロケーション"			,""}
							,{"ItemCd"					,ColItemCd						,"String"		,"商品コード"					,""}
							,{"Lot"						,ColLot						,"String"		,"ロット"						,""}
							,{"Expdate"					,ColExpdate					,"Date"			,"消費期限"						,""}
							,{"ActualDate"				,ColActualDate				,"Date"			,"入荷実績日"					,""}
							,{"ControlQty"				,ColMoveQty					,"int"			,"移動数"						,""}
							,{"MoveCom01"				,ColMoveCom01					,"String"		,"移動コメント01"				,""}
							,{"MoveCom02"				,ColMoveCom02					,"String"		,"移動コメント02"				,""}
							,{"MoveCom03"				,ColMoveCom03					,"String"		,"移動コメント03"				,""}
							,{"BeforeFromQty"			,ColBeforeFromQty				,"int"			,"（移動前）移動元在庫数"		,""}
							,{"BeforeFromPlanQty"		,ColBeforeFromPlanQty		,"int"			,"（移動前）移動元引当済数"		,""}
							,{"BeforeFromPossibleQty"	,ColBeforeFromPossibleQty	,"int"			,"（移動前）移動元出荷可能数"	,""}
							,{"BeforeToQty"				,ColBeforeToQty				,"int"			,"（移動前）移動先在庫数"		,""}
							,{"BeforeToPlanQty"			,ColBeforeToPlanQty			,"int"			,"（移動前）移動先引当済数"		,""}
							,{"BeforeToPossibleQty"		,ColBeforeToPossibleQty		,"int"			,"（移動前）移動先出荷可能数"	,""}
							,{"AfterFromQty"			,ColAfterFromQty				,"int"			,"（移動後）移動元在庫数"		,""}
							,{"AfterFromPlanQty"		,ColAfterFromPlanQty			,"int"			,"（移動後）移動元引当済数"		,""}
							,{"AfterFromPossibleQty"	,ColAfterFromPossibleQty		,"int"			,"（移動後）移動元出荷可能数"	,""}
							,{"AfterToQty"				,ColAfterToQty				,"int"			,"（移動後）移動先在庫数"		,""}
							,{"AfterToPlanQty"			,ColAfterToPlanQty			,"int"			,"（移動後）移動先引当済数"		,""}
							,{"AfterToPossibleQty"		,ColAfterToPossibleQty		,"int"			,"（移動後）移動先出荷可能数"	,""}
							,{"MoveNo"					,ColMoveNo						,"String"		,"移動番号"}
							,{"ErrFg"					,ColErrFg						,"boolean"		,"エラーフラグ"}
							,{"ErrList"					,ColErrList					,"ArrayList"	,"エラーメッセージ"}
							};
		return Layout;
	}
	
	public static Object[][] StockMoveControl(Object[][] TgtData) {
		Object[][] rt = new Object[0][RtStockMoveControl().length];
		
		if(null!=TgtData && 0<TgtData.length) {
			//移動番号取得
			int[] MoveNoRt	= Tools100_StockMoveNoGet.MoveNoRt(TgtData.length);
			
			//移動数マイナスの場合FromとTo入れ替えると共に戻りデータの準備
			rt = new Object[TgtData.length][RtStockMoveControl().length];
			for(int i=0;i<TgtData.length;i++) {
				if(0>(int)TgtData[i][ColMoveQty]) {
					TgtData[i][ColMoveQty] = -1*(int)TgtData[i][ColMoveQty];
					String FromLoc	= (String)TgtData[i][ColFromLoc];
					String ToLoc 	= (String)TgtData[i][ColToLoc];
					
					TgtData[i][ColFromLoc]	= ToLoc;
					TgtData[i][ColToLoc]	= FromLoc;
				}
				String GetClCd			= B100_TextControl.Trim((String)TgtData[i][ColClCd]);			//荷主コード
				String GetWhCd			= B100_TextControl.Trim((String)TgtData[i][ColWhCd]);			//倉庫コード
				String GetFromLoc		= B100_TextControl.Trim((String)TgtData[i][ColFromLoc]);		//移動元ロケーション
				String GetToLoc			= B100_TextControl.Trim((String)TgtData[i][ColToLoc]);		//移動先ロケーション
				String GetItemCd		= B100_TextControl.Trim((String)TgtData[i][ColItemCd]);		//商品コード
				String GetLot			= B100_TextControl.Trim((String)TgtData[i][ColLot]);			//ロット
				String GetExpdate		= B100_TextControl.Trim((String)TgtData[i][ColExpdate]);		//消費期限
				String GetActualDate	= B100_TextControl.Trim((String)TgtData[i][ColActualDate]);	//入荷実績日
				int GetMoveQty			= (int)TgtData[i][ColMoveQty];									//移動数
				String GetMoveCom01		= B100_TextControl.Trim((String)TgtData[i][ColMoveCom01]);	//移動コメント01
				String GetMoveCom02		= B100_TextControl.Trim((String)TgtData[i][ColMoveCom02]);	//移動コメント02
				String GetMoveCom03		= B100_TextControl.Trim((String)TgtData[i][ColMoveCom03]);	//移動コメント03
				
				rt[i][ColClCd]						= GetClCd;			//荷主コード
				rt[i][ColWhCd]						= GetWhCd;			//倉庫コード
				rt[i][ColFromLoc]					= GetFromLoc;		//移動元ロケーション
				rt[i][ColToLoc]					= GetToLoc;			//移動先ロケーション
				rt[i][ColItemCd]					= GetItemCd;		//商品コード
				rt[i][ColLot]						= GetLot;			//ロット
				rt[i][ColExpdate]					= GetExpdate;		//消費期限
				rt[i][ColActualDate]				= GetActualDate;	//入荷実績日
				rt[i][ColMoveQty]					= GetMoveQty;		//移動数
				rt[i][ColMoveCom01]				= GetMoveCom01;		//移動コメント01
				rt[i][ColMoveCom02]				= GetMoveCom02;		//移動コメント02
				rt[i][ColMoveCom03]				= GetMoveCom03;		//移動コメント03
				rt[i][ColBeforeFromQty]			= (int)0;			//（移動前）移動元在庫数
				rt[i][ColBeforeFromPlanQty]		= (int)0;			//（移動前）移動元引当済数
				rt[i][ColBeforeFromPossibleQty]	= (int)0;			//（移動前）移動元出荷可能数
				rt[i][ColBeforeToQty]				= (int)0;			//（移動前）移動先在庫数
				rt[i][ColBeforeToPlanQty]		= (int)0;			//（移動前）移動先引当済数
				rt[i][ColBeforeToPossibleQty]	= (int)0;			//（移動前）移動先出荷可能数
				rt[i][ColAfterFromQty]			= (int)0;			//（移動後）移動元在庫数
				rt[i][ColAfterFromPlanQty]		= (int)0;			//（移動後）移動元引当済数
				rt[i][ColAfterFromPossibleQty]	= (int)0;			//（移動後）移動元出荷可能数
				rt[i][ColAfterToQty]				= (int)0;			//（移動後）移動先在庫数
				rt[i][ColAfterToPlanQty]			= (int)0;			//（移動後）移動先引当済数
				rt[i][ColAfterToPossibleQty]		= (int)0;			//（移動後）移動先出荷可能数
				rt[i][ColMoveNo]					= ""+MoveNoRt[i];	//移動番号
				rt[i][ColErrFg]					= (boolean)false;	//エラーフラグ
				rt[i][ColErrList]					= new ArrayList<String>();	//エラーメッセージ
			}
			
			//元ロケから減らす処理実行
			Object[][] FromSetData = new Object[rt.length][Tools100_StockQtyControl.StockQtyControlDataLayout().length];
			for(int i=0;i<rt.length;i++) {
				FromSetData[i][Tools100_StockQtyControl.ColClCd] 			= (String)rt[i][ColClCd];
				FromSetData[i][Tools100_StockQtyControl.ColWhCd] 			= (String)rt[i][ColWhCd];
				FromSetData[i][Tools100_StockQtyControl.ColLoc]			= (String)rt[i][ColFromLoc];
				FromSetData[i][Tools100_StockQtyControl.ColItemCd]			= (String)rt[i][ColItemCd];
				FromSetData[i][Tools100_StockQtyControl.ColLot] 			= (String)rt[i][ColLot];
				FromSetData[i][Tools100_StockQtyControl.ColExpdate] 		= (String)rt[i][ColExpdate];
				FromSetData[i][Tools100_StockQtyControl.ColActualDate] 	= (String)rt[i][ColActualDate];
				FromSetData[i][Tools100_StockQtyControl.ColControlQty] 	= -1*(int)rt[i][ColMoveQty];
			}
			Object[][] FromData = Tools100_StockQtyControl.StockQtyControl(FromSetData);
			
			for(int i=0;i<FromData.length;i++) {
				if("EntryData".equals((String)FromData[i][Tools100_StockQtyControl.RtColErrType])) {
					rt[(int)FromData[i][Tools100_StockQtyControl.RtColErrRow]][ColBeforeFromQty]			=(int)FromData[i][Tools100_StockQtyControl.RtColBeforeQty];					//（移動前）移動元在庫数
					rt[(int)FromData[i][Tools100_StockQtyControl.RtColErrRow]][ColBeforeFromPlanQty]		=(int)FromData[i][Tools100_StockQtyControl.RtColBeforeShipPlanQty];			//（移動前）移動元引当済数
					rt[(int)FromData[i][Tools100_StockQtyControl.RtColErrRow]][ColBeforeFromPossibleQty]	=(int)FromData[i][Tools100_StockQtyControl.RtColBeforePossibleQty];			//（移動前）移動元出荷可能数
					rt[(int)FromData[i][Tools100_StockQtyControl.RtColErrRow]][ColAfterFromQty]				=(int)FromData[i][Tools100_StockQtyControl.RtColAfterQty];					//（移動後）移動元在庫数
					rt[(int)FromData[i][Tools100_StockQtyControl.RtColErrRow]][ColAfterFromPlanQty]		=(int)FromData[i][Tools100_StockQtyControl.RtColAfterShipPlanQty];			//（移動後）移動元引当済数
					rt[(int)FromData[i][Tools100_StockQtyControl.RtColErrRow]][ColAfterFromPossibleQty]	=(int)FromData[i][Tools100_StockQtyControl.RtColAfterPossibleQty];			//（移動後）移動元出荷可能数
				}else {
					rt[i][ColErrFg]					= (boolean)true;	//エラーフラグ
					((ArrayList<String>)rt[i][ColErrList]).add((String)FromData[i][Tools100_StockQtyControl.RtColErrType]+":移動元在庫が想定外の事態です移動中止しました");
				}
			}
			
			//元ロケから減らすことができた場合先ロケを増やす　※移動元からの在庫減に失敗していれば増やす数量0=在庫増しない
			Object[][] ToSetData = new Object[rt.length][Tools100_StockQtyControl.StockQtyControlDataLayout().length];
			for(int i=0;i<rt.length;i++) {
				ToSetData[i][Tools100_StockQtyControl.ColClCd] 		= (String)rt[i][ColClCd];
				ToSetData[i][Tools100_StockQtyControl.ColWhCd] 		= (String)rt[i][ColWhCd];
				ToSetData[i][Tools100_StockQtyControl.ColLoc]			= (String)rt[i][ColToLoc];
				ToSetData[i][Tools100_StockQtyControl.ColItemCd]		= (String)rt[i][ColItemCd];
				ToSetData[i][Tools100_StockQtyControl.ColLot] 			= (String)rt[i][ColLot];
				ToSetData[i][Tools100_StockQtyControl.ColExpdate] 		= (String)rt[i][ColExpdate];
				ToSetData[i][Tools100_StockQtyControl.ColActualDate] 	= (String)rt[i][ColActualDate];
				ToSetData[i][Tools100_StockQtyControl.ColControlQty] 	= (int)rt[i][ColMoveQty];
				if((boolean)rt[i][ColErrFg]) {
					rt[i][ColMoveQty] = (int)0;
					ToSetData[i][Tools100_StockQtyControl.ColControlQty] = (int)0;
				}
			}
			Object[][] ToData = Tools100_StockQtyControl.StockQtyControl(ToSetData);
			ArrayList<Integer> FromRoleBackList = new ArrayList<Integer>();
			for(int i=0;i<ToData.length;i++) {
				if("EntryData".equals((String)FromData[i][Tools100_StockQtyControl.RtColErrType])) {
					rt[(int)ToData[i][Tools100_StockQtyControl.RtColErrRow]][ColBeforeToQty]			=(int)ToData[i][Tools100_StockQtyControl.RtColBeforeQty];					//（移動前）移動先在庫数
					rt[(int)ToData[i][Tools100_StockQtyControl.RtColErrRow]][ColBeforeToPlanQty]		=(int)ToData[i][Tools100_StockQtyControl.RtColBeforeShipPlanQty];		//（移動前）移動先引当済数
					rt[(int)ToData[i][Tools100_StockQtyControl.RtColErrRow]][ColBeforeToPossibleQty]	=(int)ToData[i][Tools100_StockQtyControl.RtColBeforePossibleQty];		//（移動前）移動先出荷可能数
					rt[(int)ToData[i][Tools100_StockQtyControl.RtColErrRow]][ColAfterToQty]				=(int)ToData[i][Tools100_StockQtyControl.RtColAfterQty];					//（移動後）移動先在庫数
					rt[(int)ToData[i][Tools100_StockQtyControl.RtColErrRow]][ColAfterToPlanQty]			=(int)ToData[i][Tools100_StockQtyControl.RtColAfterShipPlanQty];			//（移動後）移動先引当済数
					rt[(int)ToData[i][Tools100_StockQtyControl.RtColErrRow]][ColAfterToPossibleQty]	=(int)ToData[i][Tools100_StockQtyControl.RtColAfterPossibleQty];			//（移動後）移動先出荷可能数
				}else {
					//移動元で在庫減に成功している場合移動元を（一回だけ）増やしなおす※在庫減に成功しているということは在庫増は成功するはず
					//在庫マイナス以外での失敗はロケマスタエラーぐらいしか発生しないので投入前にチェックしておきましょう
					//移動元同一ロケ・同一賞味・同一ロット・同一入荷実績日の移動が複数行の移動先に投入された場合移動前後在庫が信用ならなくなります
					//1行づつやればその問題は解決するので厳密な管理が必要な場合は1行づつ処理してください
					if((boolean)rt[i][ColErrFg]) {
						
					}else {
						boolean UnHitFg = true;
						if(null!=FromRoleBackList && 0<FromRoleBackList.size()) {
							for(int i01=0;i01<FromRoleBackList.size();i01++) {
								if((int)ToData[i][Tools100_StockQtyControl.RtColErrRow]==FromRoleBackList.get(i01)) {
									UnHitFg = false;
									i01=FromRoleBackList.size()+1;
								}
							}
						}
						
						if(UnHitFg) {
							Object[][] SetData = new Object[1][Tools100_StockQtyControl.StockQtyControlDataLayout().length];
							SetData[0][Tools100_StockQtyControl.ColClCd] 			= (String)rt[(int)ToData[i][Tools100_StockQtyControl.RtColErrRow]][ColClCd];
							SetData[0][Tools100_StockQtyControl.ColWhCd] 			= (String)rt[(int)ToData[i][Tools100_StockQtyControl.RtColErrRow]][ColWhCd];
							SetData[0][Tools100_StockQtyControl.ColLoc]			= (String)rt[(int)ToData[i][Tools100_StockQtyControl.RtColErrRow]][ColFromLoc];
							SetData[0][Tools100_StockQtyControl.ColItemCd]			= (String)rt[(int)ToData[i][Tools100_StockQtyControl.RtColErrRow]][ColItemCd];
							SetData[0][Tools100_StockQtyControl.ColLot] 			= (String)rt[(int)ToData[i][Tools100_StockQtyControl.RtColErrRow]][ColLot];
							SetData[0][Tools100_StockQtyControl.ColExpdate] 		= (String)rt[(int)ToData[i][Tools100_StockQtyControl.RtColErrRow]][ColExpdate];
							SetData[0][Tools100_StockQtyControl.ColActualDate] 	= (String)rt[(int)ToData[i][Tools100_StockQtyControl.RtColErrRow]][ColActualDate];
							SetData[0][Tools100_StockQtyControl.ColControlQty] 	= (int)rt[(int)ToData[i][Tools100_StockQtyControl.RtColErrRow]][ColMoveQty];
							Object[][] ErrData = Tools100_StockQtyControl.StockQtyControl(SetData);
							FromRoleBackList.add((int)ToData[i][Tools100_StockQtyControl.RtColErrRow]);
							rt[(int)ToData[i][Tools100_StockQtyControl.RtColErrRow]][ColMoveQty] = (int)0;
							
							rt[(int)ToData[i][Tools100_StockQtyControl.RtColErrRow]][ColAfterFromQty]			=(int)rt[(int)ToData[i][Tools100_StockQtyControl.RtColErrRow]][ColBeforeFromQty];				//（移動後）移動先在庫数
							rt[(int)ToData[i][Tools100_StockQtyControl.RtColErrRow]][ColAfterFromPlanQty]		=(int)rt[(int)ToData[i][Tools100_StockQtyControl.RtColErrRow]][ColBeforeFromPlanQty];			//（移動後）移動先引当済数
							rt[(int)ToData[i][Tools100_StockQtyControl.RtColErrRow]][ColAfterFromPossibleQty]	=(int)rt[(int)ToData[i][Tools100_StockQtyControl.RtColErrRow]][ColBeforeFromPossibleQty];		//（移動後）移動先出荷可能数
						}
						
					}
					rt[i][ColErrFg]					= (boolean)true;	//エラーフラグ
					((ArrayList<String>)rt[i][ColErrList]).add((String)ToData[i][Tools100_StockQtyControl.RtColErrType]+":移動元在庫が想定外の事態です移動中止しました");
				}
			}
			String now_dtm = B100_DateTimeControl.dtmString2(B100_DateTimeControl.dtm()[1])[1];
			//移動履歴残す
			String tgt_table = "WW0017StockMove";
			String TgtDB = "WANKO";
			int non_msg_fg = 1;
			
			String[] SetClCd					= new String[rt.length];	//荷主コード
			String[] SetWhCd					= new String[rt.length];	//倉庫コード
			String[] SetMoveNo					= new String[rt.length];	//調整番号
			String[] SetFromLoc					= new String[rt.length];	//移動元ロケ
			String[] SetToLoc					= new String[rt.length];	//移動先ロケ
			String[] SetItemCd					= new String[rt.length];	//商品CD
			String[] SetItemName				= new String[rt.length];	//商品名
			String[] SetLot						= new String[rt.length];	//ロット
			String[] SetExpDate					= new String[rt.length];	//賞味期限
			String[] SetActualDate				= new String[rt.length];	//入荷日
			String[] SetBeforeFromQty			= new String[rt.length];	//（移動前）移動元在庫数
			String[] SetBeforeFromPlanQty		= new String[rt.length];	//（移動前）移動元引当済数
			String[] SetBeforeFromPossibleQty	= new String[rt.length];	//（移動前）移動元出荷可能数
			String[] SetBeforeToQty				= new String[rt.length];	//（移動前）移動先在庫数
			String[] SetBeforeToPlanQty			= new String[rt.length];	//（移動前）移動先引当済数
			String[] SetBeforeToPossibleQty		= new String[rt.length];	//（移動前）移動先出荷可能数
			String[] SetMoveQty					= new String[rt.length];	//移動数
			String[] SetAfterFromQty			= new String[rt.length];	//（移動後）移動元在庫数
			String[] SetAfterFromPlanQty		= new String[rt.length];	//（移動後）移動元引当済数
			String[] SetAfterFromPossibleQty	= new String[rt.length];	//（移動後）移動元出荷可能数
			String[] SetAfterToQty				= new String[rt.length];	//（移動後）移動先在庫数
			String[] SetAfterToPlanQty			= new String[rt.length];	//（移動後）移動先引当済数
			String[] SetAfterToPossibleQty		= new String[rt.length];	//（移動後）移動先出荷可能数
			String[] SetMoveCom01				= new String[rt.length];	//移動コメント01
			String[] SetMoveCom02				= new String[rt.length];	//移動コメント02
			String[] SetMoveCom03				= new String[rt.length];	//移動コメント03
			String[] SetEntryDate				= new String[rt.length];	//登録日
			String[] SetUpdateDate				= new String[rt.length];	//更新日
			String[] SetEntryUser				= new String[rt.length];	//登録者
			String[] SetUpdateUser				= new String[rt.length];	//更新者
			
			for(int i=0;i<rt.length;i++) {
				SetClCd[i]					= ""+rt[i][ColClCd];						//荷主コード
				SetWhCd[i]					= ""+rt[i][ColWhCd];						//倉庫コード
				SetMoveNo[i]				= ""+rt[i][ColMoveNo];						//調整番号
				SetFromLoc[i]				= ""+rt[i][ColFromLoc];					//移動元ロケ
				SetToLoc[i]					= ""+rt[i][ColToLoc];						//移動先ロケ
				SetItemCd[i]				= ""+rt[i][ColItemCd];						//商品CD
				SetItemName[i]				= "";										//商品名
				SetLot[i]					= ""+rt[i][ColLot];						//ロット
				SetExpDate[i]				= ""+rt[i][ColExpdate];					//賞味期限
				SetActualDate[i]			= ""+rt[i][ColActualDate];				//入荷日
				SetBeforeFromQty[i]			= ""+rt[i][ColBeforeFromQty];				//（移動前）移動元在庫数
				SetBeforeFromPlanQty[i]		= ""+rt[i][ColBeforeFromPlanQty];		//（移動前）移動元引当済数
				SetBeforeFromPossibleQty[i]	= ""+rt[i][ColBeforeFromPossibleQty];	//（移動前）移動元出荷可能数
				SetBeforeToQty[i]			= ""+rt[i][ColBeforeToQty];				//（移動前）移動先在庫数
				SetBeforeToPlanQty[i]		= ""+rt[i][ColBeforeToPlanQty];			//（移動前）移動先引当済数
				SetBeforeToPossibleQty[i]	= ""+rt[i][ColBeforeToPossibleQty];		//（移動前）移動先出荷可能数
				SetMoveQty[i]				= ""+rt[i][ColMoveQty];					//移動数
				SetAfterFromQty[i]			= ""+rt[i][ColAfterFromQty];				//（移動後）移動元在庫数
				SetAfterFromPlanQty[i]		= ""+rt[i][ColAfterFromPlanQty];			//（移動後）移動元引当済数
				SetAfterFromPossibleQty[i]	= ""+rt[i][ColAfterFromPossibleQty];		//（移動後）移動元出荷可能数
				SetAfterToQty[i]			= ""+rt[i][ColAfterToQty];				//（移動後）移動先在庫数
				SetAfterToPlanQty[i]		= ""+rt[i][ColAfterToPlanQty];			//（移動後）移動先引当済数
				SetAfterToPossibleQty[i]	= ""+rt[i][ColAfterToPossibleQty];		//（移動後）移動先出荷可能数
				SetMoveCom01[i]				= ""+rt[i][ColMoveCom01];					//移動コメント01
				SetMoveCom02[i]				= ""+rt[i][ColMoveCom02];					//移動コメント02
				SetMoveCom03[i]				= ""+rt[i][ColMoveCom03];					//移動コメント03
				SetEntryDate[i]				= now_dtm;	//登録日
				SetUpdateDate[i]			= now_dtm;	//更新日
				SetEntryUser[i]				= "(" + A00000_Main.LoginUserId + ")" + A00000_Main.LoginUserName;	//登録者
				SetUpdateUser[i]			= "(" + A00000_Main.LoginUserId + ")" + A00000_Main.LoginUserName;	//更新者
			}
			
			Object[][] ClMstRt		= ClMstRt(SetClCd);
			Object[][] ItemMstRt	= ItemMstRt(SetClCd, SetItemCd);
			for(int i=0;i<rt.length;i++) {
				String CheckClGpCd = "";
				for(int i01=0;i01<ClMstRt.length;i01++) {
					if(SetClCd[i].equals((String)(String)ClMstRt[i01][M100_ClMstRt.Colcl_cd])) {
						CheckClGpCd = (String)(String)ClMstRt[i01][M100_ClMstRt.ColClGpCD];
						i01=ClMstRt.length+1;
					}
				}
				for(int i01=0;i01<ItemMstRt.length;i01++) {
					if(CheckClGpCd.equals((String)ItemMstRt[i01][M100_ItemMstRt.ColClGpCd])
							&& SetItemCd[i].equals((String)ItemMstRt[i01][M100_ItemMstRt.ColItemCd])
							) {
						SetItemName[i]	= (String)ItemMstRt[i01][M100_ItemMstRt.ColItemName01];
					}
				}
			}
			
			Object[][] SetString	={
									 {"ClCd"					,"1"	,"0"	,"key"	,SetClCd}					//荷主コード
									,{"WhCd"					,"1"	,"0"	,"key"	,SetWhCd}					//倉庫コード
									,{"MoveNo"					,"1"	,"0"	,"key"	,SetMoveNo}					//調整番号
									,{"FromLoc"					,"1"	,"0"	,""		,SetFromLoc}				//移動元ロケ
									,{"ToLoc"					,"1"	,"0"	,""		,SetToLoc}					//移動先ロケ
									,{"ItemCd"					,"1"	,"0"	,""		,SetItemCd}					//商品CD
									,{"ItemName"				,"1"	,"0"	,""		,SetItemName}				//商品名
									,{"Lot"						,"1"	,"0"	,""		,SetLot}					//ロット
									,{"ExpDate"					,"1"	,"0"	,""		,SetExpDate}				//賞味期限
									,{"ActualDate"				,"1"	,"0"	,""		,SetActualDate}				//入荷日
									,{"BeforeFromQty"			,"1"	,"0"	,""		,SetBeforeFromQty}			//（移動前）移動元在庫数
									,{"BeforeFromPlanQty"		,"1"	,"0"	,""		,SetBeforeFromPlanQty}		//（移動前）移動元引当済数
									,{"BeforeFromPossibleQty"	,"1"	,"0"	,""		,SetBeforeFromPossibleQty}	//（移動前）移動元出荷可能数
									,{"BeforeToQty"				,"1"	,"0"	,""		,SetBeforeToQty}			//（移動前）移動先在庫数
									,{"BeforeToPlanQty"			,"1"	,"0"	,""		,SetBeforeToPlanQty}		//（移動前）移動先引当済数
									,{"BeforeToPossibleQty"		,"1"	,"0"	,""		,SetBeforeToPossibleQty}	//（移動前）移動先出荷可能数
									,{"MoveQty"					,"1"	,"0"	,""		,SetMoveQty}				//移動数
									,{"AfterFromQty"			,"1"	,"0"	,""		,SetAfterFromQty}			//（移動後）移動元在庫数
									,{"AfterFromPlanQty"		,"1"	,"0"	,""		,SetAfterFromPlanQty}		//（移動後）移動元引当済数
									,{"AfterFromPossibleQty"	,"1"	,"0"	,""		,SetAfterFromPossibleQty}	//（移動後）移動元出荷可能数
									,{"AfterToQty"				,"1"	,"0"	,""		,SetAfterToQty}				//（移動後）移動先在庫数
									,{"AfterToPlanQty"			,"1"	,"0"	,""		,SetAfterToPlanQty}			//（移動後）移動先引当済数
									,{"AfterToPossibleQty"		,"1"	,"0"	,""		,SetAfterToPossibleQty}		//（移動後）移動先出荷可能数
									,{"MoveCom01"				,"1"	,"0"	,""		,SetMoveCom01}				//移動コメント01
									,{"MoveCom02"				,"1"	,"0"	,""		,SetMoveCom02}				//移動コメント02
									,{"MoveCom03"				,"1"	,"0"	,""		,SetMoveCom03}				//移動コメント03
									,{"EntryDate"				,"1"	,"0"	,""		,SetEntryDate}				//登録日
									,{"UpdateDate"				,"1"	,"0"	,""		,SetUpdateDate}				//更新日
									,{"EntryUser"				,"1"	,"0"	,""		,SetEntryUser}				//登録者
									,{"UpdateUser"				,"1"	,"0"	,""		,SetUpdateUser}				//更新者
									};
			A100_InsertUpdateSQL.InsertUpdateSomeRecord(SetString	,tgt_table	,TgtDB	,non_msg_fg);
		}
		return rt;
	}
	
	private static Object[][] ItemMstRt(String[]SetClCd, String[] SetItemCd){
		ArrayList<String> SearchClCd				= new ArrayList<String>();	//荷主コード
		ArrayList<String> SearchClGpCd 				= new ArrayList<String>();	//荷主グループコード
		ArrayList<String> SearchItemCd 				= new ArrayList<String>();	//商品コード
		ArrayList<String> SearchClItemCd 			= new ArrayList<String>();	//荷主商品コード
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
		
		for(int i=0;i<SetClCd.length;i++) {
			SearchClCd.add(SetClCd[i]);
		}
		
		for(int i=0;i<SetItemCd.length;i++) {
			SearchItemCd.add(SetItemCd[i]);
		}
		
		Object[][] ItemMstRt = M100_ItemMstRt.ItemMstRt(
				SearchClCd,				//荷主コード
				SearchClGpCd,			//荷主グループコード
				SearchItemCd,			//商品コード
				SearchClItemCd,			//荷主商品コード
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
		return ItemMstRt;
	}
	
	private static Object[][] ClMstRt(String[]SetClCd){
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
		boolean AllSearch = false;
		for(int i=0;i<SetClCd.length;i++) {
			SearchCLCD.add(SetClCd[i]);
		}
		
		Object[][] ClMstRt = M100_ClMstRt.ClMstRt(
			SearchClGpCD,
			SearchCLCD,
			SearchCLName,
			SearchPost,
			searchAdd,
			SearchTel,
			SearchFax,
			SearchMail,
			SearchCom,
			SearchWHCD,
			AllSearch);
		
		return ClMstRt;
	}
	
	
}