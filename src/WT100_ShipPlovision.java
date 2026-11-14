import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class WT100_ShipPlovision{
	//在庫引き当て
	static final int StockItemLotExpDateSumRt_Col_ClCd				= (int) 0;		//荷主コード
	static final int StockItemLotExpDateSumRt_Col_CLName			= (int) 1;		//荷主表記名
	static final int StockItemLotExpDateSumRt_Col_WhCd				= (int) 2;		//倉庫コード
	static final int StockItemLotExpDateSumRt_Col_ClWHName		= (int) 3;		//担当倉庫名
	static final int StockItemLotExpDateSumRt_Col_ClGpCD			= (int) 4;		//荷主グループCD
	static final int StockItemLotExpDateSumRt_Col_ClGpName		= (int) 5;		//グループ名1
	static final int StockItemLotExpDateSumRt_Col_ItemCd			= (int) 6;		//商品コード
	static final int StockItemLotExpDateSumRt_Col_Lot				= (int) 7;		//ロット
	static final int StockItemLotExpDateSumRt_Col_Expdate			= (int) 8;		//消費期限
	static final int StockItemLotExpDateSumRt_Col_Qty				= (int) 9;		//数量
	static final int StockItemLotExpDateSumRt_Col_ShipPlanQty		= (int)10;		//引当済数
	static final int StockItemLotExpDateSumRt_Col_PossibleQty		= (int)11;		//出荷可能数
	static final int StockItemLotExpDateSumRt_Col_ItemName		= (int)12;		//商品名
	static final int StockItemLotExpDateSumRt_Col_ItemName01		= (int)13;		//商品表記名
	static final int StockItemLotExpDateSumRt_Col_ItemName02		= (int)14;		//商品正式名
	static final int StockItemLotExpDateSumRt_Col_ItemName03		= (int)15;		//商品略名
	static final int StockItemLotExpDateSumRt_Col_CtUnitQty		= (int)16;		//カートン入数
	static final int StockItemLotExpDateSumRt_Col_CsUnitQty		= (int)17;		//ケース入数
	static final int StockItemLotExpDateSumRt_Col_PlUnitQty		= (int)18;		//パレット入数
	static final int StockItemLotExpDateSumRt_Col_UnitName		= (int)19;		//商品単位
	static final int StockItemLotExpDateSumRt_Col_CtUnitName		= (int)20;		//カートン商品単位
	static final int StockItemLotExpDateSumRt_Col_CsUnitName		= (int)21;		//ケース商品単位
	static final int StockItemLotExpDateSumRt_Col_PlUnitName		= (int)22;		//パレット商品単位
	
	static final int RtOkuriItemLotExpDateSumRt_ColMsItemCd		= (int) 0;		//明細商品コード
	static final int RtOkuriItemLotExpDateSumRt_ColMsQty			= (int) 1;		//明細個数
	static final int RtOkuriItemLotExpDateSumRt_ColMsLot			= (int) 2;		//明細ロット指定
	static final int RtOkuriItemLotExpDateSumRt_ColMsExpDate		= (int) 3;		//明細賞味期限指定
	static final int RtOkuriItemLotExpDateSumRt_ColMsPackingType	= (int) 4;		//明細荷姿タイプ
	
	static final int ColErrType				= (int) 0;
	static final int ColOkuriNo				= (int) 1;
	static final int ColMsNo					= (int) 2;
	static final int ColItemCd					= (int) 3;
	static final int ColLot					= (int) 4;
	static final int ColExpDate				= (int) 5;
	static final int ColNeedQty				= (int) 6;
	static final int ColPackingType			= (int) 7;
	static final int ColErrMsg					= (int) 8;
	static final int ColCheckExpDateMin		= (int) 9;	//引当対象賞味期限Min
	static final int ColCheckExpDateMax		= (int)10;	//引当対象賞味期限Max
	static final int ColCheckLotOneOnlyFg	= (int)11;	//ロット1種指定
	static final int ColChecPackingQty		= (int)12;	//荷姿数量
	static final int ColPlanDate				= (int)13;	//出荷予定日
	static final int ColDeCd					= (int)14;	//届先CD
	static final int ColDptCd					= (int)15;	//届先部署CD
	static final int ColDeName					= (int)16;	//届先名
	
	public static Object[][] RtShipPlovision(){
		//戻り値定義
		Object[][] Rt = {
						 {"ErrType"					,ColErrType				,"String"	,"エラータイプ"			,"Key"	}
						,{"OkuriNo"					,ColOkuriNo				,"String"	,"送り状番号"			,"Key"	}
						,{"MsNo"					,ColMsNo					,"int"		,"明細番号"				,"Key"	}
						,{"ItemCd"					,ColItemCd					,"String"	,"商品CD"				,""		}
						,{"Lot"						,ColLot					,"String"	,"ロット指定"			,""		}
						,{"ExpDate"					,ColExpDate				,"Date"		,"賞味期限指定"			,""		}
						,{"NeedQty"					,ColNeedQty				,"int"		,"指示数"				,""		}
						,{"PackingType"				,ColPackingType			,"int"		,"荷姿タイプ"			,""		}
						,{"ErrMsg"					,ColErrMsg					,"String"	,"エラーメッセージ"		,""		}
						,{"CheckExpDateMin"			,ColCheckExpDateMin		,"String"	,"引当対象賞味期限Min"	,""		}
						,{"CheckExpDateMax"			,ColCheckExpDateMax		,"String"	,"引当対象賞味期限Max"	,""		}
						,{"CheckLotOneOnlyFg"		,ColCheckLotOneOnlyFg	,"int"		,"ロット1種指定"		,""		}
						,{"ChecPackingQty"			,ColChecPackingQty		,"int"		,"荷姿数量"				,""		}
						,{"PlanDate"				,ColPlanDate				,"Date"		,"出荷予定日"			,""		}
						,{"DeCd"					,ColDeCd					,"String"	,"届先CD"				,""		}
						,{"DptCd"					,ColDptCd					,"String"	,"届先部署CD"			,""		}
						,{"DeName"					,ColDeName					,"String"	,"届先名"				,""		}
						};
		Rt = B100_LanguageControl.RtControl(Rt);
		return Rt;
	}
	
	public static Object[][] ShipPlovision(String TgtWhCd,String TgtClCd,ArrayList<String> TgtOkurino) {
		Object[][] Rt = new Object[0][RtShipPlovision().length];
		if(null==TgtWhCd||"".equals(TgtWhCd)) {TgtWhCd=A00000_Main.ClWh;}
		if(null==TgtClCd||"".equals(TgtClCd)) {TgtClCd=A00000_Main.ClCd;}
		
		if(null!=TgtOkurino && 0<TgtOkurino.size()) {
			ShipPlovisionMain(TgtWhCd,TgtClCd,TgtOkurino);
		}
		
		return Rt;
	}
		
	public static Object[][] ShipPlovisionMain(String TgtWhCd,String TgtClCd,ArrayList<String> TgtOkurino) {
		Object[][] Rt = new Object[0][RtShipPlovision().length];
		
		if(null!=TgtOkurino && 0<TgtOkurino.size()) {
			Rt= WmsStatusCheck(TgtWhCd,TgtClCd,TgtOkurino);
			for(int i=0;i<Rt.length;i++) {
				System.out.println((String)Rt[i][ColOkuriNo]+":"+(String)Rt[i][ColErrMsg]);
			}
		}
		
		ArrayList<String>ErrMsg = new ArrayList<String>();
		for(int i=0;i<Rt.length;i++) {
			if(!"".equals((String)Rt[i][ColErrType])) {
				ErrMsg.add((String)Rt[i][ColErrMsg]);
			}
		}
		
		if(null!=ErrMsg && 0<ErrMsg.size()) {
			ErrView(ErrMsg);
		}
		return Rt;
	}
	
	
	private static Object[][] WmsStatusCheck(String TgtWhCd,String TgtClCd,ArrayList<String> TgtOkurino) {
		Object[][] Rt = new Object[0][RtShipPlovision().length];
		Object[] RtTgt = new Object[RtShipPlovision().length];
		
		Object[][] OkuriHdRt	= OkuriHdRt(TgtWhCd,TgtClCd,TgtOkurino);
		Object[][] OkuriMsRt	= OkuriMsRt(TgtWhCd,TgtClCd,TgtOkurino);
		
		ArrayList<String> SttusErrOkurino = new ArrayList<String>();
		ArrayList<String> UnKnownOkurino = new ArrayList<String>();
		
		for(int i=0;i<TgtOkurino.size();i++) {
			boolean UnHitFg = true;
			for(int i01=0;i01<OkuriHdRt.length;i01++) {
				if(TgtOkurino.get(i).equals((String)OkuriHdRt[i01][T100_OkuriHdRt.ColOkuriNo])) {
					if(0!=(int)OkuriHdRt[i01][T100_OkuriHdRt.ColWmsStatus]) {
						SttusErrOkurino.add(TgtOkurino.get(i));
					}
					UnHitFg = false;
					i01=OkuriHdRt.length+1;
				}
			}
			if(UnHitFg) {
				UnKnownOkurino.add(TgtOkurino.get(i));
			}
		}
		Rt = new Object[OkuriMsRt.length+SttusErrOkurino.size()+UnKnownOkurino.size()][RtShipPlovision().length];
		int Counter = 0;
		for(int i=0;i<SttusErrOkurino.size();i++) {
			Rt[Counter][ColErrType] 				= "";	//エラータイプ
			Rt[Counter][ColOkuriNo] 				= "";	//送り状番号
			Rt[Counter][ColMsNo] 					= 0;	//明細番号
			Rt[Counter][ColItemCd] 				= "";	//商品CD
			Rt[Counter][ColLot] 					= "";	//ロット指定
			Rt[Counter][ColExpDate] 				= "";	//賞味期限指定
			Rt[Counter][ColNeedQty] 				= 0;	//指示数
			Rt[Counter][ColPackingType] 			= 0;	//荷姿タイプ
			Rt[Counter][ColErrMsg] 				= "";	//エラーメッセージ
			Rt[Counter][ColCheckExpDateMin] 		= "";	//引当対象賞味期限Min
			Rt[Counter][ColCheckExpDateMax] 		= "";	//引当対象賞味期限Max
			Rt[Counter][ColCheckLotOneOnlyFg] 	= 0;	//ロット1種指定
			Rt[Counter][ColChecPackingQty] 		= 0;	//荷姿数量
			Rt[Counter][ColPlanDate]				= "";	//出荷予定日
			Rt[Counter][ColDeCd]					= "";	//届先CD
			Rt[Counter][ColDptCd]					= "";	//届先部署CD
			Rt[Counter][ColDeName]					= "";	//届先名
			
			Rt[Counter][ColOkuriNo] 				= SttusErrOkurino.get(i);	//送り状番号
			Rt[Counter][ColErrType] 				= "StatusErr";			//エラータイプ
			Rt[Counter][ColErrMsg] 				= "("+SttusErrOkurino.get(i)+")は未引当ステータスではないので引当対象外です";	//エラーメッセージ
			Counter = Counter+1;
		}
		for(int i=0;i<UnKnownOkurino.size();i++) {
			Rt[Counter][ColErrType] 				= "";	//エラータイプ
			Rt[Counter][ColOkuriNo] 				= "";	//送り状番号
			Rt[Counter][ColMsNo] 					= 0;	//明細番号
			Rt[Counter][ColItemCd] 				= "";	//商品CD
			Rt[Counter][ColLot] 					= "";	//ロット指定
			Rt[Counter][ColExpDate] 				= "";	//賞味期限指定
			Rt[Counter][ColNeedQty] 				= 0;	//指示数
			Rt[Counter][ColPackingType] 			= 0;	//荷姿タイプ
			Rt[Counter][ColErrMsg] 				= "";	//エラーメッセージ
			Rt[Counter][ColCheckExpDateMin] 		= "";	//引当対象賞味期限Min
			Rt[Counter][ColCheckExpDateMax] 		= "";	//引当対象賞味期限Max
			Rt[Counter][ColCheckLotOneOnlyFg] 	= 0;	//ロット1種指定
			Rt[Counter][ColChecPackingQty] 		= 0;	//荷姿数量
			Rt[Counter][ColPlanDate]				= "";	//出荷予定日
			Rt[Counter][ColDeCd]					= "";	//届先CD
			Rt[Counter][ColDptCd]					= "";	//届先部署CD
			Rt[Counter][ColDeName]					= "";	//届先名
			
			Rt[Counter][ColOkuriNo] 				= UnKnownOkurino.get(i);	//送り状番号
			Rt[Counter][ColErrType] 				= "UnKnownOkuriNo";			//エラータイプ
			Rt[Counter][ColErrMsg] 				= "("+UnKnownOkurino.get(i)+")は当該荷主の送り状Noに存在しません";	//エラーメッセージ
			Counter = Counter+1;
		}
		
		for(int i=0;i<OkuriMsRt.length;i++) {
			String GetOkuriNo				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColOkuriNo];					//送り状番号
			String GetPlanDate				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColPlanDate];				//出荷予定日
			int GetMsNo						= (int)OkuriMsRt[i][T100_OkuriMsRt.ColMsNo];						//明細番号
			String GetMsItemCd				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColMsItemCd];				//明細商品コード
			int GetMsQty					= (int)OkuriMsRt[i][T100_OkuriMsRt.ColMsQty];						//明細個数
			int GetMsPackingQty				= (int)OkuriMsRt[i][T100_OkuriMsRt.ColMsPackingQty];				//明細荷姿数量
			String GetMsLot					= (String)OkuriMsRt[i][T100_OkuriMsRt.ColMsLot];					//明細ロット指定
			String GetMsExpDate				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColMsExpDate];				//明細賞味期限指定
			int GetMsPackingType			= (int)OkuriMsRt[i][T100_OkuriMsRt.ColMsPackingType];				//明細荷姿タイプ
			String GetDeliCd				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColDeliCd];					//荷届先コード
			String GetDeliDepartmentCd		= (String)OkuriMsRt[i][T100_OkuriMsRt.ColDeliDepartmentCd];		//部署CD
			String GetDeliName01			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColDeliName01];				//荷届先名01
			
			Rt[Counter][ColErrType] 				= "";					//エラータイプ
			Rt[Counter][ColOkuriNo] 				= GetOkuriNo;			//送り状番号
			Rt[Counter][ColMsNo] 					= GetMsNo;				//明細番号
			Rt[Counter][ColItemCd] 				= GetMsItemCd;			//商品CD
			Rt[Counter][ColLot] 					= GetMsLot;				//ロット指定
			Rt[Counter][ColExpDate] 				= GetMsExpDate;			//賞味期限指定
			Rt[Counter][ColNeedQty] 				= GetMsQty;				//指示数
			Rt[Counter][ColPackingType] 			= GetMsPackingType;		//荷姿タイプ
			Rt[Counter][ColErrMsg] 				= "";					//エラーメッセージ
			Rt[Counter][ColCheckExpDateMin] 		= "";					//引当対象賞味期限Min
			Rt[Counter][ColCheckExpDateMax] 		= "";					//引当対象賞味期限Max
			Rt[Counter][ColCheckLotOneOnlyFg] 	= 0;					//ロット1種指定
			Rt[Counter][ColChecPackingQty] 		= GetMsPackingQty;		//荷姿数量
			Rt[Counter][ColPlanDate]				= GetPlanDate;			//出荷予定日
			Rt[Counter][ColDeCd]					= GetDeliCd;			//届先CD
			Rt[Counter][ColDptCd]					= GetDeliDepartmentCd;	//届先部署CD
			Rt[Counter][ColDeName]					= GetDeliName01;		//届先名
			
			Counter = Counter+1;
		}
		
		return Rt;
	}
	private static Object[][] SelfishBanExpDateOld(String TgtWhCd,String TgtClCd,ArrayList<String> SearchDeCd){
		//古い賞味期限禁止届先設定をWMSパラメータマスタから取得
		ArrayList<String> SearchClWh 			= new ArrayList<String>();
		ArrayList<String> SearchClCd 			= new ArrayList<String>();
		ArrayList<String> SearchParaCd 			= new ArrayList<String>();	
		ArrayList<Integer> SearchParaCdSeqStr	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaCdSeqEnd	= new ArrayList<Integer>();
		ArrayList<String> SearchParaName 		= new ArrayList<String>();
		ArrayList<String> SearchParaTxt01		= SearchDeCd;
		ArrayList<String> SearchParaTxt02		= new ArrayList<String>();
		ArrayList<String> SearchParaTxt03		= new ArrayList<String>();
		ArrayList<String> SearchParaTxt04		= new ArrayList<String>();
		ArrayList<String> SearchParaTxt05		= new ArrayList<String>();
		ArrayList<String> SearchParaTxt06		= new ArrayList<String>();
		ArrayList<String> SearchParaTxt07		= new ArrayList<String>();
		ArrayList<String> SearchParaTxt08		= new ArrayList<String>();
		ArrayList<String> SearchParaTxt09		= new ArrayList<String>();
		ArrayList<String> SearchParaTxt10		= new ArrayList<String>();
		ArrayList<Integer> SearchParaInt01Str	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt02Str	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt03Str	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt04Str	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt05Str	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt06Str	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt07Str	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt08Str	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt09Str	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt10Str	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt01End	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt02End	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt03End	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt04End	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt05End	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt06End	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt07End	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt08End	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt09End	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt10End	= new ArrayList<Integer>();
		ArrayList<String> SearchParaTxtAll = new ArrayList<String>();
		Boolean AllSearch = false;
		if(null!=SearchDeCd && 0<SearchDeCd.size()) {
			SearchClWh.add(TgtWhCd);
			SearchClCd.add(TgtClCd);
			SearchParaCd.add("SelfishBanExpDateOld");
		}
		Object[][] ParameterMstWankoRt = M100_ParameterMstWankoRt.ParameterMstWankoRt(
				SearchClWh,SearchClCd,
				SearchParaCd,SearchParaCdSeqStr,SearchParaCdSeqEnd,SearchParaName,
				SearchParaTxt01,SearchParaTxt02,SearchParaTxt03,SearchParaTxt04,SearchParaTxt05,
				SearchParaTxt06,SearchParaTxt07,SearchParaTxt08,SearchParaTxt09,SearchParaTxt10,
				SearchParaInt01Str,SearchParaInt02Str,SearchParaInt03Str,SearchParaInt04Str,SearchParaInt05Str,
				SearchParaInt06Str,SearchParaInt07Str,SearchParaInt08Str,SearchParaInt09Str,SearchParaInt10Str,
				SearchParaInt01End,SearchParaInt02End,SearchParaInt03End,SearchParaInt04End,SearchParaInt05End,
				SearchParaInt06End,SearchParaInt07End,SearchParaInt08End,SearchParaInt09End,SearchParaInt10End,
				SearchParaTxtAll,
				AllSearch);
		
		return ParameterMstWankoRt;
	}
	private static Object[][] SelfishLot(String TgtWhCd,String TgtClCd,ArrayList<String> SearchDeCd){
		//ロット割れ禁止届先設定をWMSパラメータマスタから取得
		ArrayList<String> SearchClWh 			= new ArrayList<String>();
		ArrayList<String> SearchClCd 			= new ArrayList<String>();
		ArrayList<String> SearchParaCd 			= new ArrayList<String>();	
		ArrayList<Integer> SearchParaCdSeqStr	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaCdSeqEnd	= new ArrayList<Integer>();
		ArrayList<String> SearchParaName 		= new ArrayList<String>();
		ArrayList<String> SearchParaTxt01		= SearchDeCd;
		ArrayList<String> SearchParaTxt02		= new ArrayList<String>();
		ArrayList<String> SearchParaTxt03		= new ArrayList<String>();
		ArrayList<String> SearchParaTxt04		= new ArrayList<String>();
		ArrayList<String> SearchParaTxt05		= new ArrayList<String>();
		ArrayList<String> SearchParaTxt06		= new ArrayList<String>();
		ArrayList<String> SearchParaTxt07		= new ArrayList<String>();
		ArrayList<String> SearchParaTxt08		= new ArrayList<String>();
		ArrayList<String> SearchParaTxt09		= new ArrayList<String>();
		ArrayList<String> SearchParaTxt10		= new ArrayList<String>();
		ArrayList<Integer> SearchParaInt01Str	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt02Str	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt03Str	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt04Str	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt05Str	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt06Str	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt07Str	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt08Str	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt09Str	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt10Str	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt01End	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt02End	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt03End	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt04End	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt05End	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt06End	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt07End	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt08End	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt09End	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt10End	= new ArrayList<Integer>();
		ArrayList<String> SearchParaTxtAll = new ArrayList<String>();
		Boolean AllSearch = false;
		if(null!=SearchDeCd && 0<SearchDeCd.size()) {
			SearchClWh.add(TgtWhCd);
			SearchClCd.add(TgtClCd);
			SearchParaCd.add("SelfishLot");
		}
		Object[][] ParameterMstWankoRt = M100_ParameterMstWankoRt.ParameterMstWankoRt(
				SearchClWh,SearchClCd,
				SearchParaCd,SearchParaCdSeqStr,SearchParaCdSeqEnd,SearchParaName,
				SearchParaTxt01,SearchParaTxt02,SearchParaTxt03,SearchParaTxt04,SearchParaTxt05,
				SearchParaTxt06,SearchParaTxt07,SearchParaTxt08,SearchParaTxt09,SearchParaTxt10,
				SearchParaInt01Str,SearchParaInt02Str,SearchParaInt03Str,SearchParaInt04Str,SearchParaInt05Str,
				SearchParaInt06Str,SearchParaInt07Str,SearchParaInt08Str,SearchParaInt09Str,SearchParaInt10Str,
				SearchParaInt01End,SearchParaInt02End,SearchParaInt03End,SearchParaInt04End,SearchParaInt05End,
				SearchParaInt06End,SearchParaInt07End,SearchParaInt08End,SearchParaInt09End,SearchParaInt10End,
				SearchParaTxtAll,
				AllSearch);
		
		return ParameterMstWankoRt;
	}
	
	private static Object[][] OkuriMsRt(String TgtWhCd,String TgtClCd,ArrayList<String> TgtOkurino){
		ArrayList<String> SearchInvoiceWHCD			= new ArrayList<String>();	//倉庫CD
		ArrayList<String> SearchClGpCD				= new ArrayList<String>();	//荷主グループCD
		ArrayList<String> SearchClCd				= new ArrayList<String>();	//荷主CD
		ArrayList<String> SearchOkuriNo				= TgtOkurino;				//送り状番号
		ArrayList<String> SearchClDeliNo			= new ArrayList<String>();	//荷主管理番号
		ArrayList<String> SearchPickupWhCd			= new ArrayList<String>();	//集荷倉庫CD
		ArrayList<String> SearchPurposeFG			= new ArrayList<String>();	//目的フラグ
		ArrayList<String> SearchPlanDateStr			= new ArrayList<String>();	//出荷予定日開始
		ArrayList<String> SearchShipDateStr			= new ArrayList<String>();	//出荷実績日開始
		ArrayList<String> SearchSPPlanDateStr		= new ArrayList<String>();	//着日指定開始
		ArrayList<String> SearchSPDateStr			= new ArrayList<String>();	//着日実績開始
		
		ArrayList<String> SearchPlanDateEnd			= new ArrayList<String>();	//出荷予定日終了
		ArrayList<String> SearchShipDateEnd			= new ArrayList<String>();	//出荷実績日終了
		ArrayList<String> SearchSPPlanDateEnd		= new ArrayList<String>();	//着日指定終了
		ArrayList<String> SearchSPDateEnd			= new ArrayList<String>();	//着日実績終了
		
		ArrayList<Float> SearchTotalWeightMin		= new ArrayList<Float>();	//荷物重量(kg)最小
		ArrayList<Float> SearchTotalSizeMin			= new ArrayList<Float>();	//荷物サイズ最小
		ArrayList<Integer> SearchTotalQtyMin		= new ArrayList<Integer>();	//個口数最小
		
		ArrayList<Float> SearchTotalWeightMax		= new ArrayList<Float>();	//荷物重量(kg)最大
		ArrayList<Float> SearchTotalSizeMax			= new ArrayList<Float>();	//荷物サイズ最大
		ArrayList<Integer> SearchTotalQtyMax		= new ArrayList<Integer>();	//個口数最大
		
		ArrayList<String> SearchDeliveryTypeCd01	= new ArrayList<String>();	//運送タイプ01
		ArrayList<String> SearchDeliveryTypeCd02	= new ArrayList<String>();	//運送タイプ02
		ArrayList<String> SearchDeliveryTypeCd03	= new ArrayList<String>();	//運送タイプ03
		ArrayList<String> SearchDeliveryTypeCd04	= new ArrayList<String>();	//運送タイプ04
		ArrayList<String> SearchDeliveryTypeCd05	= new ArrayList<String>();	//運送タイプ05
		
		ArrayList<Integer> SearchCodFG				= new ArrayList<Integer>();	//代引区分
		ArrayList<Integer> SearchCodPayTotalMin		= new ArrayList<Integer>();	//代引収受金額合計最小
		ArrayList<Integer> SearchCodPayTotalMax		= new ArrayList<Integer>();	//代引収受金額合計最大
		
		ArrayList<Integer> SearchChildrenFG			= new ArrayList<Integer>();	//子伝票区分
		ArrayList<String> SearchParentOkuriNo		= new ArrayList<String>();	//親伝票番号
		
		ArrayList<String> SearchNiokuriCd			= new ArrayList<String>();	//荷送人CD
		ArrayList<String> SearchNiokuriDepartmentCd	= new ArrayList<String>();	//荷送人部署CD
		ArrayList<String> SearchNiokuriName			= new ArrayList<String>();	//荷送人名称
		ArrayList<String> SearchNiokuriPost			= new ArrayList<String>();	//荷送人郵便番号
		ArrayList<String> SearchNiokuriAdd			= new ArrayList<String>();	//荷送人住所
		ArrayList<String> SearchNioKuriTel			= new ArrayList<String>();	//荷送人Tel
		ArrayList<String> SearchNioKuriFax			= new ArrayList<String>();	//荷送人Fax
		ArrayList<String> SearchNioKuriMail			= new ArrayList<String>();	//荷送人Mail
		ArrayList<String> SearchNiokuriMunicCd		= new ArrayList<String>();	//荷送人市区町村CD
		
		ArrayList<String> SearchDeliCd				= new ArrayList<String>();	//届先CD
		ArrayList<String> SearchClDeliCd			= new ArrayList<String>();	//荷主届先CD
		ArrayList<String> SearchDeliDepartmentCd	= new ArrayList<String>();	//届先部署CD
		ArrayList<String> SearchDeliName			= new ArrayList<String>();	//届先名称
		ArrayList<String> SearchDeliPost			= new ArrayList<String>();	//届先郵便番号
		ArrayList<String> SearchDeliAdd				= new ArrayList<String>();	//届先住所
		ArrayList<String> SearchDeliTel				= new ArrayList<String>();	//届先Tel
		ArrayList<String> SearchDeliFax				= new ArrayList<String>();	//届先Fax
		ArrayList<String> SearchDeliMail			= new ArrayList<String>();	//届先Mail
		ArrayList<String> SearchDeliMunicCd			= new ArrayList<String>();	//届先市区町村CD
		
		ArrayList<String> SearchCom					= new ArrayList<String>();	//コメント
		ArrayList<Integer> SearchStatus				= new ArrayList<Integer>();	//運送ステータス
		
		ArrayList<Integer> SearchFeeFixFG			= new ArrayList<Integer>();	//運賃確定フラグ
		ArrayList<Integer> SearchReceiptStampFG		= new ArrayList<Integer>();	//受領印フラグ
		ArrayList<Integer> SearchInvoiceStatus		= new ArrayList<Integer>();	//請求ステータス
		
		ArrayList<Integer> SearchWithOutTaxTotalMin	= new ArrayList<Integer>();	//税別運賃合計最小
		ArrayList<Integer> SearchTotalFeeMin		= new ArrayList<Integer>();	//税込運賃合計税込運賃合計
		ArrayList<String> SearchFeeFixDateStr		= new ArrayList<String>();	//運賃確定日時開始
		ArrayList<String> SearchReceiptStampDateStr	= new ArrayList<String>();	//受領印日時開始
		ArrayList<String> SearchEntryDateStr		= new ArrayList<String>();	//登録日終了
		ArrayList<String> SearchUpdateDateStr		= new ArrayList<String>();	//更新日終了
		
		ArrayList<Integer> SearchWithOutTaxTotalMax	= new ArrayList<Integer>();	//税別運賃合計最大
		ArrayList<Integer> SearchTotalFeeMax		= new ArrayList<Integer>();	//税込運賃合計最大
		ArrayList<String> SearchFeeFixDateEnd		= new ArrayList<String>();	//運賃確定日時終了
		ArrayList<String> SearchReceiptStampDateEnd	= new ArrayList<String>();	//受領印日時終了
		ArrayList<String> SearchEntryDateEnd		= new ArrayList<String>();	//登録日終了
		ArrayList<String> SearchUpdateDateEnd		= new ArrayList<String>();	//更新日終了
		
		ArrayList<String> SearchEntryUser			= new ArrayList<String>();	//登録者
		ArrayList<String> SearchUpdateUser			= new ArrayList<String>();	//更新者
		ArrayList<String> SearchEntryPG				= new ArrayList<String>();	//登録プログラム
		ArrayList<String> SearchUpdatePG			= new ArrayList<String>();	//更新プログラム
		ArrayList<String> SearchUseFeeBasePtCd		= new ArrayList<String>();	//運転計算タリフ
		ArrayList<Integer> SearchWmsStatus			= new ArrayList<Integer>();	//倉庫出荷ステータス
		ArrayList<String> SearchWmsShipDateStr		= new ArrayList<String>();	//倉庫出荷日時開始
		ArrayList<String> SearchWmsShipDateEnd		= new ArrayList<String>();	//倉庫出荷日時終了
		ArrayList<String> SearchCourseGpCd			= new ArrayList<String>();	//配車コースグループコード
		ArrayList<String> SearchCourseCD			= new ArrayList<String>();	//配車コースコード
		ArrayList<Integer> SearchCourseCDEda		= new ArrayList<Integer>();	//配車コースコード枝番
		ArrayList<String> SearchPitGrp				= new ArrayList<String>();	//荷物払出ピットグループ
		ArrayList<String> SearchPit					= new ArrayList<String>();	//荷物払出ピット
		
		ArrayList<String> SearchMsItemCd			= new ArrayList<String>();	//商品CD
		ArrayList<String> SearchMsItemName			= new ArrayList<String>();	//商品名
		
		ArrayList<String> SearchClItemCd			= new ArrayList<String>();	//荷主商品CD
		
		ArrayList<String> SearchMsCategoryCd		= new ArrayList<String>();	//カテゴリCD
		ArrayList<String> SearchMsCategoryName		= new ArrayList<String>();	//カテゴリ名
		ArrayList<String> SearchMsTildFG			= new ArrayList<String>();	//温度区分
		ArrayList<String> SearchMsTildName			= new ArrayList<String>();	//温度区分名
		
		ArrayList<String> SearchMsLot				= new ArrayList<String>();	//ロット指定
		ArrayList<String> SearchMsExpDateStr		= new ArrayList<String>();	//賞味期限指定開始
		ArrayList<String> SearchMsExpDateEnd		= new ArrayList<String>();	//賞味期限指定終了
		ArrayList<Integer> SearchMsPackingType		= new ArrayList<Integer>();	//荷姿タイプ
		
		boolean AllSearch = false;
		
		//対象送り状番号無ければゼロ件返却してほしい
		if(null!=TgtOkurino && 0<TgtOkurino.size()) {
			SearchInvoiceWHCD.add(TgtWhCd);
			SearchClCd.add(TgtClCd);
			SearchWmsStatus.add(0);
		}
		
		Object[][] OkuriMsRt	= T100_OkuriMsRt.OkuriMsRt(
					SearchInvoiceWHCD,			//倉庫CD
					SearchClGpCD,				//荷主グループCD
					SearchClCd,					//荷主CD
					SearchOkuriNo,				//送り状番号
					SearchClDeliNo,				//荷主管理番号
					SearchPickupWhCd,			//集荷倉庫CD
					SearchPurposeFG,			//目的フラグ
					SearchPlanDateStr,			//出荷予定日開始
					SearchShipDateStr,			//出荷実績日開始
					SearchSPPlanDateStr,		//着日指定開始
					SearchSPDateStr,			//着日実績開始
					
					SearchPlanDateEnd,			//出荷予定日終了
					SearchShipDateEnd,			//出荷実績日終了
					SearchSPPlanDateEnd,		//着日指定終了
					SearchSPDateEnd,			//着日実績終了
					
					SearchTotalWeightMin,		//荷物重量(kg)最小
					SearchTotalSizeMin,			//荷物サイズ最小
					SearchTotalQtyMin,			//個口数最小
					
					SearchTotalWeightMax,		//荷物重量(kg)最大
					SearchTotalSizeMax,			//荷物サイズ最大
					SearchTotalQtyMax,			//個口数最大
					
					SearchDeliveryTypeCd01,		//運送タイプ01
					SearchDeliveryTypeCd02,		//運送タイプ02
					SearchDeliveryTypeCd03,		//運送タイプ03
					SearchDeliveryTypeCd04,		//運送タイプ04
					SearchDeliveryTypeCd05,		//運送タイプ05
					
					SearchCodFG,				//代引区分
					SearchCodPayTotalMin,		//代引収受金額合計最小
					SearchCodPayTotalMax,		//代引収受金額合計最大
					
					SearchChildrenFG,			//子伝票区分
					SearchParentOkuriNo,		//親伝票番号
					
					SearchNiokuriCd,			//荷送人CD
					SearchNiokuriDepartmentCd,	//荷送人部署CD
					SearchNiokuriName,			//荷送人名称
					SearchNiokuriPost,			//荷送人郵便番号
					SearchNiokuriAdd,			//荷送人住所
					SearchNioKuriTel,			//荷送人Tel
					SearchNioKuriFax,			//荷送人Fax
					SearchNioKuriMail,			//荷送人Mail
					SearchNiokuriMunicCd,		//荷送人市区町村CD
					
					SearchDeliCd,				//届先CD
					SearchClDeliCd,				//荷主届先CD
					SearchDeliDepartmentCd,		//届先部署CD
					SearchDeliName,				//届先名称
					SearchDeliPost,				//届先郵便番号
					SearchDeliAdd,				//届先住所
					SearchDeliTel,				//届先Tel
					SearchDeliFax,				//届先Fax
					SearchDeliMail,				//届先Mail
					SearchDeliMunicCd,			//届先市区町村CD
					
					SearchCom,					//コメント
					SearchStatus,				//運送ステータス
					
					SearchFeeFixFG,				//運賃確定フラグ
					SearchReceiptStampFG,		//受領印フラグ
					SearchInvoiceStatus,		//請求ステータス
					
					SearchWithOutTaxTotalMin,	//税別運賃合計最小
					SearchTotalFeeMin,			//税込運賃合計最小
					SearchFeeFixDateStr,		//運賃確定日時開始
					SearchReceiptStampDateStr,	//受領印日時開始
					SearchEntryDateStr,			//登録日開始
					SearchUpdateDateStr,		//更新日開始
					
					SearchWithOutTaxTotalMax,	//税別運賃合計最大
					SearchTotalFeeMax,			//税込運賃合計最大
					SearchFeeFixDateEnd,		//運賃確定日時終了
					SearchReceiptStampDateEnd,	//受領印日時終了
					SearchEntryDateEnd,			//登録日終了
					SearchUpdateDateEnd,		//更新日終了
					
					SearchEntryUser,			//登録者
					SearchUpdateUser,			//更新者
					SearchEntryPG,				//登録プログラム
					SearchUpdatePG,				//更新プログラム
					SearchUseFeeBasePtCd,		//運転計算タリフ
					SearchWmsStatus,			//倉庫出荷ステータス
					SearchWmsShipDateStr,		//倉庫出荷日時開始
					SearchWmsShipDateEnd,		//倉庫出荷日時終了
					SearchCourseGpCd,			//配車コースグループコード
					SearchCourseCD,				//配車コースコード
					SearchCourseCDEda,			//配車コースコード枝番
					SearchPitGrp,				//荷物払出ピットグループ
					SearchPit,					//荷物払出ピット
					
					SearchMsItemCd,				//商品CD
					SearchMsItemName,			//商品名
					
					SearchClItemCd,				//荷主商品CD
					
					SearchMsCategoryCd,			//カテゴリCD
					SearchMsCategoryName,		//カテゴリ名
					SearchMsTildFG,				//温度区分
					SearchMsTildName,			//温度区分名
					
					SearchMsLot,				//ロット指定
					SearchMsExpDateStr,			//賞味期限指定開始
					SearchMsExpDateEnd,			//賞味期限指定終了
					SearchMsPackingType,		//荷姿タイプ
					AllSearch);
		return OkuriMsRt;
	}
	private static Object[][] OkuriHdRt(String TgtWhCd,String TgtClCd,ArrayList<String> TgtOkurino){
		ArrayList<String> SearchInvoiceWHCD			= new ArrayList<String>();			//倉庫CD
		ArrayList<String> SearchClGpCD				= new ArrayList<String>();			//荷主グループCD
		ArrayList<String> SearchClCd				= new ArrayList<String>();			//荷主CD
		ArrayList<String> SearchOkuriNo				= TgtOkurino;						//送り状番号
		ArrayList<String> SearchClDeliNo			= new ArrayList<String>();			//荷主管理番号
		ArrayList<String> SearchPickupWhCd			= new ArrayList<String>();			//集荷倉庫CD
		ArrayList<String> SearchPurposeFG			= new ArrayList<String>();			//目的フラグ
		ArrayList<String> SearchPlanDateStr			= new ArrayList<String>();			//出荷予定日開始
		ArrayList<String> SearchShipDateStr			= new ArrayList<String>();			//出荷実績日開始
		ArrayList<String> SearchSPPlanDateStr		= new ArrayList<String>();			//着日指定開始
		ArrayList<String> SearchSPDateStr			= new ArrayList<String>();			//着日実績開始
		
		ArrayList<String> SearchPlanDateEnd			= new ArrayList<String>();			//出荷予定日終了
		ArrayList<String> SearchShipDateEnd			= new ArrayList<String>();			//出荷実績日終了
		ArrayList<String> SearchSPPlanDateEnd		= new ArrayList<String>();			//着日指定終了
		ArrayList<String> SearchSPDateEnd			= new ArrayList<String>();			//着日実績終了
		
		ArrayList<Float> SearchTotalWeightMin		= new ArrayList<Float>();			//荷物重量(kg)最小
		ArrayList<Float> SearchTotalSizeMin			= new ArrayList<Float>();			//荷物サイズ最小
		ArrayList<Integer> SearchTotalQtyMin		= new ArrayList<Integer>();			//個口数最小
		
		ArrayList<Float> SearchTotalWeightMax		= new ArrayList<Float>();			//荷物重量(kg)最大
		ArrayList<Float> SearchTotalSizeMax			= new ArrayList<Float>();			//荷物サイズ最大
		ArrayList<Integer> SearchTotalQtyMax		= new ArrayList<Integer>();			//個口数最大
		
		ArrayList<String> SearchDeliveryTypeCd01	= new ArrayList<String>();			//運送タイプ01
		ArrayList<String> SearchDeliveryTypeCd02	= new ArrayList<String>();			//運送タイプ02
		ArrayList<String> SearchDeliveryTypeCd03	= new ArrayList<String>();			//運送タイプ03
		ArrayList<String> SearchDeliveryTypeCd04	= new ArrayList<String>();			//運送タイプ04
		ArrayList<String> SearchDeliveryTypeCd05	= new ArrayList<String>();			//運送タイプ05
		
		ArrayList<Integer> SearchCodFG				= new ArrayList<Integer>();			//代引区分
		ArrayList<Integer> SearchCodPayTotalMin		= new ArrayList<Integer>();			//代引収受金額合計最小
		ArrayList<Integer> SearchCodPayTotalMax		= new ArrayList<Integer>();			//代引収受金額合計最大
		
		ArrayList<Integer> SearchChildrenFG			= new ArrayList<Integer>();			//子伝票区分
		ArrayList<String> SearchParentOkuriNo		= new ArrayList<String>();			//親伝票番号
		
		ArrayList<String> SearchNiokuriCd			= new ArrayList<String>();			//荷送人CD
		ArrayList<String> SearchNiokuriDepartmentCd	= new ArrayList<String>();			//荷送人部署CD
		ArrayList<String> SearchNiokuriName			= new ArrayList<String>();			//荷送人名称
		ArrayList<String> SearchNiokuriPost			= new ArrayList<String>();			//荷送人郵便番号
		ArrayList<String> SearchNiokuriAdd			= new ArrayList<String>();			//荷送人住所
		ArrayList<String> SearchNioKuriTel			= new ArrayList<String>();			//荷送人Tel
		ArrayList<String> SearchNioKuriFax			= new ArrayList<String>();			//荷送人Fax
		ArrayList<String> SearchNioKuriMail			= new ArrayList<String>();			//荷送人Mail
		ArrayList<String> SearchNiokuriMunicCd		= new ArrayList<String>();			//荷送人市区町村CD
		
		ArrayList<String> SearchDeliCd				= new ArrayList<String>();			//届先CD
		ArrayList<String> SearchClDeliCd			= new ArrayList<String>();			//荷主届先CD
		ArrayList<String> SearchDeliDepartmentCd	= new ArrayList<String>();			//届先部署CD
		ArrayList<String> SearchDeliName			= new ArrayList<String>();			//届先名称
		ArrayList<String> SearchDeliPost			= new ArrayList<String>();			//届先郵便番号
		ArrayList<String> SearchDeliAdd				= new ArrayList<String>();			//届先住所
		ArrayList<String> SearchDeliTel				= new ArrayList<String>();			//届先Tel
		ArrayList<String> SearchDeliFax				= new ArrayList<String>();			//届先Fax
		ArrayList<String> SearchDeliMail			= new ArrayList<String>();			//届先Mail
		ArrayList<String> SearchDeliMunicCd			= new ArrayList<String>();			//届先市区町村CD
		
		ArrayList<String> SearchCom					= new ArrayList<String>();			//コメント
		ArrayList<Integer> SearchStatus				= new ArrayList<Integer>();			//運送ステータス
		
		ArrayList<Integer> SearchFeeFixFG			= new ArrayList<Integer>();			//運賃確定フラグ
		ArrayList<Integer> SearchReceiptStampFG		= new ArrayList<Integer>();			//受領印フラグ
		ArrayList<Integer> SearchInvoiceStatus		= new ArrayList<Integer>();			//請求ステータス
		
		ArrayList<Integer> SearchWithOutTaxTotalMin	= new ArrayList<Integer>();			//税別運賃合計最小
		ArrayList<Integer> SearchTotalFeeMin		= new ArrayList<Integer>();			//税込運賃合計税込運賃合計
		ArrayList<String> SearchFeeFixDateStr		= new ArrayList<String>();			//運賃確定日時開始
		ArrayList<String> SearchReceiptStampDateStr	= new ArrayList<String>();			//受領印日時開始
		ArrayList<String> SearchEntryDateStr		= new ArrayList<String>();			//登録日終了
		ArrayList<String> SearchUpdateDateStr		= new ArrayList<String>();			//更新日終了
		
		ArrayList<Integer> SearchWithOutTaxTotalMax	= new ArrayList<Integer>();			//税別運賃合計最大
		ArrayList<Integer> SearchTotalFeeMax		= new ArrayList<Integer>();			//税込運賃合計最大
		ArrayList<String> SearchFeeFixDateEnd		= new ArrayList<String>();			//運賃確定日時終了
		ArrayList<String> SearchReceiptStampDateEnd	= new ArrayList<String>();			//受領印日時終了
		ArrayList<String> SearchEntryDateEnd		= new ArrayList<String>();			//登録日終了
		ArrayList<String> SearchUpdateDateEnd		= new ArrayList<String>();			//更新日終了
		
		ArrayList<String> SearchEntryUser			= new ArrayList<String>();			//登録者
		ArrayList<String> SearchUpdateUser			= new ArrayList<String>();			//更新者
		ArrayList<String> SearchEntryPG				= new ArrayList<String>();			//登録プログラム
		ArrayList<String> SearchUpdatePG			= new ArrayList<String>();			//更新プログラム
		ArrayList<String> SearchUseFeeBasePtCd		= new ArrayList<String>();			//運転計算タリフ
		ArrayList<Integer> SearchWmsStatus			= new ArrayList<Integer>();			//倉庫出荷ステータス
		ArrayList<String> SearchWmsShipDateStr		= new ArrayList<String>();			//倉庫出荷日時開始
		ArrayList<String> SearchWmsShipDateEnd		= new ArrayList<String>();			//倉庫出荷日時終了
		ArrayList<String> SearchCourseGpCd			= new ArrayList<String>();			//配車コースグループコード
		ArrayList<String> SearchCourseCD			= new ArrayList<String>();			//配車コースコード
		ArrayList<Integer> SearchCourseCDEda		= new ArrayList<Integer>();			//配車コースコード枝番
		ArrayList<String> SearchPitGrp				= new ArrayList<String>();			//荷物払出ピットグループ
		ArrayList<String> SearchPit					= new ArrayList<String>();			//荷物払出ピット
		
		ArrayList<String> SearchMsItemCd			= new ArrayList<String>();			//商品CD
		ArrayList<String> SearchMsItemName			= new ArrayList<String>();			//商品名
		
		ArrayList<String> SearchClItemCd			= new ArrayList<String>();			//荷主商品CD
		
		ArrayList<String> SearchMsCategoryCd		= new ArrayList<String>();			//カテゴリCD
		ArrayList<String> SearchMsCategoryName		= new ArrayList<String>();			//カテゴリ名
		ArrayList<String> SearchMsTildFG			= new ArrayList<String>();			//温度区分
		ArrayList<String> SearchMsTildName			= new ArrayList<String>();			//温度区分名
		
		ArrayList<String> SearchMsLot				= new ArrayList<String>();			//ロット指定
		ArrayList<String> SearchMsExpDateStr		= new ArrayList<String>();			//賞味期限指定開始
		ArrayList<String> SearchMsExpDateEnd		= new ArrayList<String>();			//賞味期限指定終了
		ArrayList<Integer> SearchMsPackingType		= new ArrayList<Integer>();			//荷姿タイプ
		boolean AllSearch = false;
		
		//対象送り状番号無ければゼロ件返却してほしい
		if(null!=TgtOkurino && 0<TgtOkurino.size()) {
			SearchInvoiceWHCD.add(TgtWhCd);
			SearchClCd.add(TgtClCd);
		}
		Object[][] OkuriHdRt	= T100_OkuriHdRt.OkuriHdRt(
					SearchInvoiceWHCD,			//倉庫CD
					SearchClGpCD,				//荷主グループCD
					SearchClCd,					//荷主CD
					SearchOkuriNo,				//送り状番号
					SearchClDeliNo,				//荷主管理番号
					SearchPickupWhCd,			//集荷倉庫CD
					SearchPurposeFG,			//目的フラグ
					SearchPlanDateStr,			//出荷予定日開始
					SearchShipDateStr,			//出荷実績日開始
					SearchSPPlanDateStr,		//着日指定開始
					SearchSPDateStr,			//着日実績開始
					
					SearchPlanDateEnd,			//出荷予定日終了
					SearchShipDateEnd,			//出荷実績日終了
					SearchSPPlanDateEnd,		//着日指定終了
					SearchSPDateEnd,			//着日実績終了
					
					SearchTotalWeightMin,		//荷物重量(kg)最小
					SearchTotalSizeMin,			//荷物サイズ最小
					SearchTotalQtyMin,			//個口数最小
					
					SearchTotalWeightMax,		//荷物重量(kg)最大
					SearchTotalSizeMax,			//荷物サイズ最大
					SearchTotalQtyMax,			//個口数最大
					
					SearchDeliveryTypeCd01,		//運送タイプ01
					SearchDeliveryTypeCd02,		//運送タイプ02
					SearchDeliveryTypeCd03,		//運送タイプ03
					SearchDeliveryTypeCd04,		//運送タイプ04
					SearchDeliveryTypeCd05,		//運送タイプ05
					
					SearchCodFG,				//代引区分
					SearchCodPayTotalMin,		//代引収受金額合計最小
					SearchCodPayTotalMax,		//代引収受金額合計最大
					
					SearchChildrenFG,			//子伝票区分
					SearchParentOkuriNo,		//親伝票番号
					
					SearchNiokuriCd,			//荷送人CD
					SearchNiokuriDepartmentCd,	//荷送人部署CD
					SearchNiokuriName,			//荷送人名称
					SearchNiokuriPost,			//荷送人郵便番号
					SearchNiokuriAdd,			//荷送人住所
					SearchNioKuriTel,			//荷送人Tel
					SearchNioKuriFax,			//荷送人Fax
					SearchNioKuriMail,			//荷送人Mail
					SearchNiokuriMunicCd,		//荷送人市区町村CD
					
					SearchDeliCd,				//届先CD
					SearchClDeliCd,				//荷主届先CD
					SearchDeliDepartmentCd,		//届先部署CD
					SearchDeliName,				//届先名称
					SearchDeliPost,				//届先郵便番号
					SearchDeliAdd,				//届先住所
					SearchDeliTel,				//届先Tel
					SearchDeliFax,				//届先Fax
					SearchDeliMail,				//届先Mail
					SearchDeliMunicCd,			//届先市区町村CD
					
					SearchCom,					//コメント
					SearchStatus,				//運送ステータス
					
					SearchFeeFixFG,				//運賃確定フラグ
					SearchReceiptStampFG,		//受領印フラグ
					SearchInvoiceStatus,		//請求ステータス
					
					SearchWithOutTaxTotalMin,	//税別運賃合計最小
					SearchTotalFeeMin,			//税込運賃合計最小
					SearchFeeFixDateStr,		//運賃確定日時開始
					SearchReceiptStampDateStr,	//受領印日時開始
					SearchEntryDateStr,			//登録日開始
					SearchUpdateDateStr,		//更新日開始
					
					SearchWithOutTaxTotalMax,	//税別運賃合計最大
					SearchTotalFeeMax,			//税込運賃合計最大
					SearchFeeFixDateEnd,		//運賃確定日時終了
					SearchReceiptStampDateEnd,	//受領印日時終了
					SearchEntryDateEnd,			//登録日終了
					SearchUpdateDateEnd,		//更新日終了
					
					SearchEntryUser,			//登録者
					SearchUpdateUser,			//更新者
					SearchEntryPG,				//登録プログラム
					SearchUpdatePG,				//更新プログラム
					SearchUseFeeBasePtCd,		//運転計算タリフ
					SearchWmsStatus,			//倉庫出荷ステータス
					SearchWmsShipDateStr,		//倉庫出荷日時開始
					SearchWmsShipDateEnd,		//倉庫出荷日時終了
					SearchCourseGpCd,			//配車コースグループコード
					SearchCourseCD,				//配車コースコード
					SearchCourseCDEda,			//配車コースコード枝番
					SearchPitGrp,				//荷物払出ピットグループ
					SearchPit,					//荷物払出ピット
					
					SearchMsItemCd,				//商品CD
					SearchMsItemName,			//商品名
					
					SearchClItemCd,				//荷主商品CD
					
					SearchMsCategoryCd,			//カテゴリCD
					SearchMsCategoryName,		//カテゴリ名
					SearchMsTildFG,				//温度区分
					SearchMsTildName,			//温度区分名
					
					SearchMsLot,				//ロット指定
					SearchMsExpDateStr,			//賞味期限指定開始
					SearchMsExpDateEnd,			//賞味期限指定終了
					SearchMsPackingType,		//荷姿タイプ
					AllSearch);
		
		return OkuriHdRt;
	}
	
	private static void ErrView(ArrayList<String>ErrMsg) {
		//必要フォルダを生成する
		String FLD_PATH = A00000_Main.MainFLD+"\\ShipPlovisionControl";
		B100_FolderCheck.FLD_CHECK(FLD_PATH);
		FLD_PATH = A00000_Main.MainFLD+"\\ShipPlovisionControl\\Plovision";
		B100_FolderCheck.FLD_CHECK(FLD_PATH);
		FLD_PATH = A00000_Main.MainFLD+"\\ShipPlovisionControl\\Plovision\\Err";
		B100_FolderCheck.FLD_CHECK(FLD_PATH);
		FLD_PATH = A00000_Main.MainFLD+"\\ShipPlovisionControl\\Plovision\\BK";
		B100_FolderCheck.FLD_CHECK(FLD_PATH);
		
		//ファイルに出力
		String NowDTM=B100_DateTimeControl.dtmString2(B100_DateTimeControl.dtm()[1])[1].replace(" ", "").replace("/", "").replace(":", "");
		
		FLD_PATH = A00000_Main.MainFLD+"\\ShipPlovisionControl\\Plovision\\Err";
		
		String ErrFP = FLD_PATH+"\\ERR"+NowDTM+".txt";
		
		B100_TextExport.txt_exp2(ErrMsg, ErrFP,"UTF-8");
		
		//古いエラーデータ削除
		B100_FolderCheck.ToolsOldFileDeleteWhereFileName(FLD_PATH ,"ERR",B100_DefaultVariable.ErrTxtDelete);
		
		//ファイル開く
		File file = new File(ErrFP);
		Desktop desktop = Desktop.getDesktop();
		try {
			desktop.open(file);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}