import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;

public class WT100_ShipPlovision{
	//在庫引き当て
	/*
	返却エラータイプ
	"WmsStatusErr":送り状ステータスエラー　引当指示された送り状のWMSステータスが未引当でない場合に返却、対象送り状が存在しない場合もこのエラー
	*/
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
	
	static final int ColErrType			= (int) 0;
	static final int ColOkuriNo			= (int) 1;
	static final int ColMsNo				= (int) 2;
	static final int ColItemCd				= (int) 3;
	static final int ColLot				= (int) 4;
	static final int ColExpDate			= (int) 5;
	static final int ColNeedQty			= (int) 6;
	static final int ColPackingType		= (int) 7;
	static final int ColErrMsg				= (int) 8;
	
	static ArrayList<String> CheckOkurino;		//チェック対象送り状NO
	public static Object[][] RtShipPlovision(){
		Object[][] Rt = {
						 {"ErrType"		,ColErrType		,"String"	,"エラータイプ"		,"Key"	}
						,{"OkuriNo"		,ColOkuriNo		,"String"	,"送り状番号"		,"Key"	}
						,{"MsNo"		,ColMsNo			,"int"		,"明細番号"			,"Key"	}
						,{"ItemCd"		,ColItemCd			,"String"	,"商品CD"			,""		}
						,{"Lot"			,ColLot			,"String"	,"ロット指定"		,""		}
						,{"ExpDate"		,ColExpDate		,"Date"		,"賞味期限指定"		,""		}
						,{"NeedQty"		,ColNeedQty		,"int"		,"指示数"			,""		}
						,{"PackingType"	,ColPackingType	,"int"		,"荷姿タイプ"		,""		}
						,{"ErrMsg"		,ColErrMsg			,"String"	,"エラーメッセージ"	,""		}
						};
		Rt = B100_LanguageControl.RtControl(Rt);
		return Rt;
	}
	
	public static Object[][] ShipPlovision(String TgtWhCd,String TgtClCd,ArrayList<String> TgtOkurino) {
		Object[][] ErrRt = new Object[0][0];
		Object[][] StatusErr = new Object[0][0];
		Object[][] StockErr = new Object[0][0];
		
		if(null==TgtWhCd||"".equals(TgtWhCd)) {TgtWhCd=A00000_Main.ClWh;}
		if(null==TgtClCd||"".equals(TgtClCd)) {TgtClCd=A00000_Main.ClCd;}
		
		CheckOkurino = new ArrayList<String>();
		
		if(null!=TgtOkurino && 0<TgtOkurino.size()) {
			StatusErr 	= WmsStatusCheck(TgtWhCd,TgtClCd,TgtOkurino);
			if(null!=CheckOkurino && 0<CheckOkurino.size()) {
				StockErr 	= StockCheck(TgtWhCd,TgtClCd);
			}
			
		}
		
		ArrayList<String>ErrMsg = new ArrayList<String>();
		Object[][] RtShipPlovision=RtShipPlovision();
		ErrRt = new Object[StatusErr.length+StockErr.length][RtShipPlovision.length];
		
		int counter = 0;
		
		for(int i=0;i<StatusErr.length;i++) {
			ErrMsg.add((String)StatusErr[i][ColErrMsg]);
			for(int i01=0;i01<RtShipPlovision.length;i01++) {
				ErrRt[counter][(int)RtShipPlovision[i01][1]]=StatusErr[i][(int)RtShipPlovision[i01][1]];
			}
			counter = counter+1;
		}
		
		for(int i=0;i<StockErr.length;i++) {
			ErrMsg.add((String)StockErr[i][ColErrMsg]);
			for(int i01=0;i01<RtShipPlovision.length;i01++) {
				ErrRt[counter][(int)RtShipPlovision[i01][1]]=StockErr[i][(int)RtShipPlovision[i01][1]];
			}
			counter = counter+1;
		}
		
		if(null!=ErrMsg && 0<ErrMsg.size()) {
			ErrView(ErrMsg);
		}
		return ErrRt;
	}
	
	public static Object[][] WmsStatusCheck(String TgtWhCd,String TgtClCd,ArrayList<String> TgtOkurino) {
		ArrayList<String> ErrOkuriNo	= new ArrayList<String>();
		ArrayList<String> ErrMsg		= new ArrayList<String>();
		int ErrCount=0;
		
		Object[][] OkuriHdRt	= OkuriHdRt(TgtWhCd,TgtClCd,TgtOkurino);
		
		CheckOkurino = new ArrayList<String>();
		
		for(int i=0;i<TgtOkurino.size();i++) {
			boolean UnHitFg = true;
			for(int i01=0;i01<OkuriHdRt.length;i01++) {
				if(TgtOkurino.get(i).equals((String)OkuriHdRt[i01][T100_OkuriHdRt.ColOkuriNo])) {
					if(0!=(int)OkuriHdRt[i01][T100_OkuriHdRt.ColWmsStatus]) {
						ErrOkuriNo.add(TgtOkurino.get(i));
						ErrMsg.add("("+TgtOkurino.get(i)+")は未引当ステータスではないので引当対象外です");
						ErrCount=ErrCount+1;
					}else {
						CheckOkurino.add(TgtOkurino.get(i));
					}
					UnHitFg = false;
					i01=OkuriHdRt.length+1;
				}
			}
			if(UnHitFg) {
				ErrOkuriNo.add(TgtOkurino.get(i));
				ErrMsg.add("("+TgtOkurino.get(i)+")は当該荷主の送り状Noに存在しません");
				ErrCount=ErrCount+1;
			}
		}
		Object[][] Rt	= DefaultSett(ErrCount);
		
		for(int i=0;i<Rt.length;i++) {
			Rt[i][ColErrType]			= "WmsStatusErr";
			Rt[i][ColOkuriNo]			= ErrOkuriNo.get(i);
			Rt[i][ColMsNo]				= 0;
			Rt[i][ColItemCd]			= "";
			Rt[i][ColLot]				= "";
			Rt[i][ColExpDate]			= "";
			Rt[i][ColNeedQty]			= "";
			Rt[i][ColPackingType]		= 0;
			Rt[i][ColErrMsg	]		= ErrMsg.get(i);
		}
		
		return Rt;
	}
	
	private static Object[][] StockCheck(String TgtWhCd,String TgtClCd){
		int ErrCount=0;
		//対象送り状ヘッダ取得
		Object[][] OkuriHdRt	= OkuriHdRt(TgtWhCd,TgtClCd,CheckOkurino);
		
		//出荷予定日のリスト作る
		ArrayList<String> PlanDateList	= new ArrayList<String>();
		for(int i=0;i<OkuriHdRt.length;i++) {
			boolean UnHitFg = true;
			for(int i01=0;i01<PlanDateList.size();i01++) {
				if(PlanDateList.get(i01).equals((String)OkuriHdRt[i][T100_OkuriHdRt.ColPlanDate])) {
					UnHitFg = false;
					i01=PlanDateList.size()+1;
				}
			}
			if(UnHitFg) {
				PlanDateList.add((String)OkuriHdRt[i][T100_OkuriHdRt.ColPlanDate]);
			}
		}
		
		//念のために出荷予定日ソート
		PlanDateList.sort(Comparator.naturalOrder());
		
		for(int DateCount=0;DateCount<PlanDateList.size();DateCount++) {
			String TgtDate = PlanDateList.get(DateCount);
			ArrayList<String> OkuriNoList = new ArrayList<String>();
			for(int i=0;i<OkuriHdRt.length;i++) {
				if(TgtDate.equals((String)OkuriHdRt[i][T100_OkuriHdRt.ColPlanDate])) {
					OkuriNoList.add((String)OkuriHdRt[i][T100_OkuriHdRt.ColOkuriNo]);
				}
			}
			
			Object[][] CheckOkuriHdRt	= OkuriHdRt(TgtWhCd,TgtClCd,OkuriNoList);
			Object[][] CheckOkuriMsRt	= OkuriMsRt(TgtWhCd,TgtClCd,OkuriNoList);
			
			//我儘届先をパラメータマスタから拾う
			ArrayList<String> SearchDeCd = new ArrayList<String>();
			for(int i=0;i<CheckOkuriHdRt.length;i++) {
				SearchDeCd.add((String)CheckOkuriHdRt[i][T100_OkuriHdRt.ColDeliCd]);
			}
			
			Object[][] SelfishBanExpDateOld	= SelfishBanExpDateOld(TgtWhCd,TgtClCd,SearchDeCd);
			Object[][] SelfishLot			= SelfishLot(TgtWhCd,TgtClCd,SearchDeCd);
			/*
			送り状明細から　現在の在庫を元に　商品‐ロット-賞味期限別の　出荷可能数（総数）,出荷可能数（パレット）,出荷可能数（ケース）,出荷可能数（カートン）,出荷可能数（バラ）,パレット入数,ケース入数,カートン入数の情報生成
			*/
			ArrayList<String> TgtItemCd = new ArrayList<String>();
			for(int i=0;i<CheckOkuriMsRt.length;i++) {
				TgtItemCd.add((String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColMsItemCd]);
			}
			Object[][] StockRt	= StockRt(TgtWhCd,TgtClCd,TgtItemCd);
			ArrayList<String> StockItemCd 	= new ArrayList<String>();
			ArrayList<String> StockLot 		= new ArrayList<String>();
			ArrayList<String> StockExpDate 	= new ArrayList<String>();
			ArrayList<Integer> StockTotalQty 	= new ArrayList<Integer>();
			ArrayList<Integer> StockPlQty 	= new ArrayList<Integer>();
			ArrayList<Integer> StockCsQty 	= new ArrayList<Integer>();
			ArrayList<Integer> StockCtQty 	= new ArrayList<Integer>();
			ArrayList<Integer> StockBrQty 	= new ArrayList<Integer>();
			ArrayList<Integer> StockPlUnitQty 	= new ArrayList<Integer>();
			ArrayList<Integer> StockCsUnitQty 	= new ArrayList<Integer>();
			ArrayList<Integer> StockCtUnitQty 	= new ArrayList<Integer>();
			
			for(int i=0;i<StockRt.length;i++) {
				boolean UnHitFg = true;
				String GetClCd			= (String)StockRt[i][T100_StockRt.ColClCd];			//荷主コード
				String GetCLName		= (String)StockRt[i][T100_StockRt.ColCLName];			//荷主表記名
				String GetWhCd			= (String)StockRt[i][T100_StockRt.ColWhCd];			//倉庫コード
				String GetClWHName		= (String)StockRt[i][T100_StockRt.ColClWHName];		//担当倉庫名
				String GetClGpCD		= (String)StockRt[i][T100_StockRt.ColClGpCD];			//荷主グループCD
				String GetClGpName		= (String)StockRt[i][T100_StockRt.ColClGpName];		//グループ名1
				String GetLoc			= (String)StockRt[i][T100_StockRt.ColLoc];				//ロケーション
				String GetLocName		= (String)StockRt[i][T100_StockRt.ColLocName];			//ロケーション名
				int GetType				= (int)StockRt[i][T100_StockRt.ColType];				//ロケタイプ
				String GetItemCd		= (String)StockRt[i][T100_StockRt.ColItemCd];			//商品コード
				String GetLot			= (String)StockRt[i][T100_StockRt.ColLot];				//ロット
				String GetExpdate		= (String)StockRt[i][T100_StockRt.ColExpdate];			//消費期限
				String GetActualDate	= (String)StockRt[i][T100_StockRt.ColActualDate];		//入荷実績日
				int GetQty				= (int)StockRt[i][T100_StockRt.ColQty];				//総数量
				int GetShipPlanQty		= (int)StockRt[i][T100_StockRt.ColShipPlanQty];		//引当済総数
				int GetPossibleQty		= (int)StockRt[i][T100_StockRt.ColPossibleQty];		//出荷可能総数
				String GetItemName		= (String)StockRt[i][T100_StockRt.ColItemName];		//商品名
				String GetItemName01	= (String)StockRt[i][T100_StockRt.ColItemName01];		//商品表記名
				String GetItemName02	= (String)StockRt[i][T100_StockRt.ColItemName02];		//商品正式名
				String GetItemName03	= (String)StockRt[i][T100_StockRt.ColItemName03];		//商品略名
				String GetClItemCd		= (String)StockRt[i][T100_StockRt.ColClItemCd];		//荷主商品コード
				String GetJanCd			= (String)StockRt[i][T100_StockRt.ColJanCd];			//ソースマーク_BCD（バラ）
				String GetItemMdNo		= (String)StockRt[i][T100_StockRt.ColItemMdNo];		//商品型番
				int GetCtUnitQty		= (int)StockRt[i][T100_StockRt.ColCtUnitQty];			//カートン入数
				int GetCsUnitQty		= (int)StockRt[i][T100_StockRt.ColCsUnitQty];			//ケース入数
				int GetPlUnitQty		= (int)StockRt[i][T100_StockRt.ColPlUnitQty];			//パレット入数
				String GetUnitName		= (String)StockRt[i][T100_StockRt.ColUnitName];		//商品単位
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
				if(null!=StockItemCd && 0<StockItemCd.size()) {
					for(int i01=0;i01<StockItemCd.size();i01++) {
						if(
							StockItemCd.get(i01).equals(GetItemCd)
							&& StockLot.get(i01).equals(GetLot)
							&& StockExpDate.get(i01).equals(GetExpdate)
							) {
							
							StockTotalQty.set(i01,StockTotalQty.get(i01)+GetPossibleQty);
							StockPlQty.set(i01,StockPlQty.get(i01)+GetPlPossibleQty);
							StockCsQty.set(i01,StockCsQty.get(i01)+GetCsPossibleQty);
							StockCtQty.set(i01,StockCtQty.get(i01)+GetCtPossibleQty);
							StockBrQty.set(i01,StockBrQty.get(i01)+GetBrPossibleQty);
							
							UnHitFg = false;
						}
					}
				}
				if(UnHitFg) {
					StockItemCd.add(GetItemCd);
					StockLot.add(GetLot);
					StockExpDate.add(GetExpdate);
					
					StockTotalQty.add(GetPossibleQty);
					StockPlQty.add(GetPlPossibleQty);
					StockCsQty.add(GetCsPossibleQty);
					StockCtQty.add(GetCtPossibleQty);
					StockBrQty.add(GetBrPossibleQty);
					StockPlUnitQty.add(GetPlUnitQty);
					StockCsUnitQty.add(GetCsUnitQty);
					StockCtUnitQty.add(GetCtUnitQty);
				}
			}
			
			/*
			送り状明細から　送り状番号,届先CD,届先部署CD,届先名,明細番号,商品CD,荷姿タイプ,必要数（バラ）,荷姿必要数量,ロット指定,賞味期限指定,引当対象賞味期限Min,引当対象賞味期限Msx,ロット1種指定,エラータイプを格納する配列生成
			*/
			int ColCheckOkuriNo		=  0;	//送り状番号
			int ColCheckDeliCd		=  1;	//届先CD
			int ColCheckDptCd		=  2;	//届先部署CD
			int ColCheckDeliName	=  3;	//届先名
			int ColCheckMsNo		=  4;	//明細番号
			int ColCheckItemCd		=  5;	//商品CD
			int ColCheckUnitType	=  6;	//荷姿タイプ
			int ColCheckQty			=  7;	//必要数（バラ）
			int ColCheckUnitQty		=  8;	//荷姿必要数量
			int ColCheckLot			=  9;	//ロット指定
			int ColCheckExpDate		= 10;	//賞味期限指定
			int ColCheckExpDateMin	= 11;	//引当対象賞味期限Min
			int ColCheckExpDateMax	= 12;	//引当対象賞味期限Msx
			int ColLotOneOnlyFg		= 13;	//ロット1種指定
			int ColCheckErrType		= 14;	//エラータイプ
			int ColCheckErrMsg		= 15;	//エラーメッセージ
			
			Object[][] CheckMs = CheckMs(CheckOkuriMsRt);
			
			//我儘届先でロット一種指定の場合ロット1種指定 = 1に
			for(int i=0;i<CheckMs.length;i++) {
				for(int i01=0;i01<SelfishLot.length;i01++) {
					if(((String)CheckMs[i][ColCheckDeliCd]).equals((String)SelfishLot[i01][M100_ParameterMstWankoRt.ColParaTxt01])
							&&((String)CheckMs[i][ColCheckDptCd]).equals((String)SelfishLot[i01][M100_ParameterMstWankoRt.ColParaTxt02])
							) {
						CheckMs[i][ColLotOneOnlyFg] = 1;
						i01=SelfishLot.length+1;
					}
				}
			}
			//我儘届先で前回賞味期限より古い商品禁止の場合引当対象MinMax設定
			PreparedStatement MaxExpDateGet	= MaxExpDateGet(TgtWhCd,TgtClCd);
			PreparedStatement MinExpDateGet	= MinExpDateGet(TgtWhCd,TgtClCd);
			
			ResultSet rsetMaxExpDateGet = null;
			ResultSet rsetMinExpDateGet = null;
			
			int SetPlanDate			= 1;
			int SetOrderItemCd		= 2;
			int SetDeliCd			= 3;
			int SetDeliDepartmentCd	= 4;
			
			for(int i=0;i<CheckMs.length;i++) {
				for(int i01=0;i01<SelfishBanExpDateOld.length;i01++) {
					if(((String)CheckMs[i][ColCheckDeliCd]).equals((String)SelfishBanExpDateOld[i01][M100_ParameterMstWankoRt.ColParaTxt01])
							&&((String)CheckMs[i][ColCheckDptCd]).equals((String)SelfishBanExpDateOld[i01][M100_ParameterMstWankoRt.ColParaTxt02])
							) {
						try {
							MaxExpDateGet.setString(SetPlanDate,PlanDateList.get(DateCount));
							MaxExpDateGet.setString(SetOrderItemCd		,(String)CheckMs[i][ColCheckItemCd]);
							MaxExpDateGet.setString(SetDeliCd			,(String)CheckMs[i][ColCheckDeliCd]);
							MaxExpDateGet.setString(SetDeliDepartmentCd	,(String)CheckMs[i][ColCheckDptCd]);
							
							MinExpDateGet.setString(SetPlanDate,PlanDateList.get(DateCount));
							MinExpDateGet.setString(SetOrderItemCd		,(String)CheckMs[i][ColCheckItemCd]);
							MinExpDateGet.setString(SetDeliCd			,(String)CheckMs[i][ColCheckDeliCd]);
							MinExpDateGet.setString(SetDeliDepartmentCd	,(String)CheckMs[i][ColCheckDptCd]);
							
							rsetMaxExpDateGet = MaxExpDateGet.executeQuery();
							rsetMinExpDateGet = MinExpDateGet.executeQuery();
							
							int counter = 0;
							rsetMaxExpDateGet.beforeFirst();
							while (rsetMaxExpDateGet.next()) {
								if(null==rsetMaxExpDateGet.getTimestamp("ShipExpdate")){
									
								}else {
									CheckMs[i][ColCheckExpDateMax] = B100_DateTimeControl.dtmString2(rsetMaxExpDateGet.getTimestamp("ShipExpdate"))[0];
								}
							}
							counter = 0;
							rsetMinExpDateGet.beforeFirst();
							while (rsetMinExpDateGet.next()) {
								if(null==rsetMinExpDateGet.getTimestamp("ShipExpdate")){
									
								}else {
									CheckMs[i][ColCheckExpDateMin] = B100_DateTimeControl.dtmString2(rsetMinExpDateGet.getTimestamp("ShipExpdate"))[0];
								}
							}
						} catch (SQLException e) {
						}
						i01=SelfishLot.length+1;
					}
				}
			}
			try {
				if(rsetMaxExpDateGet!=null){rsetMaxExpDateGet.close();}
				if(rsetMinExpDateGet!=null){rsetMinExpDateGet.close();}
				if(MaxExpDateGet!=null){MaxExpDateGet.close();}
				if(MinExpDateGet!=null){MinExpDateGet.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			A100_DbConnect.close();
			
			ArrayList<String> ErrOkuriNo = new ArrayList<String>();
			
			//ロット指定有の在庫チェック
			//荷姿パレット指定
			for(int i=0;i<CheckMs.length;i++) {
				if(null==CheckMs[i][ColCheckLot]) {CheckMs[i][ColCheckLot]="";}
				if(null==CheckMs[i][ColCheckExpDate]) {CheckMs[i][ColCheckExpDate]="";}
				if(null==CheckMs[i][ColCheckErrType]) {CheckMs[i][ColCheckErrType]="";}
				if("".equals((String)CheckMs[i][ColCheckErrType]) ) {
					if("".equals((String)CheckMs[i][ColCheckLot])&&"".equals((String)CheckMs[i][ColCheckExpDate])) {
						
					}else {
						if(3==(int)CheckMs[i][ColCheckUnitType]) {
							int NeedTotalQty	= (int)CheckMs[i][ColCheckQty];		//必要数（バラ）
							int NeedUnitQty		= (int)CheckMs[i][ColCheckUnitQty];	//荷姿必要数量
							
							int WorkStockTotalQty	= 0;	//比較対象在庫数（バラ）
							int WorkUnitQty			= 0;	//比較対象在庫荷姿必要数量
							
							for(int i01=0;i01<StockItemCd.size();i01++) {
								if(((String)CheckMs[i][ColCheckItemCd]).equals(StockItemCd.get(i01))){
									boolean CheckFg = true;
									if(!"".equals((String)CheckMs[i][ColCheckLot])&&!StockLot.get(i01).equals((String)CheckMs[i][ColCheckLot])) {
										CheckFg = false;
									}
									if(!"".equals((String)CheckMs[i][ColCheckExpDate])&&!StockExpDate.get(i01).equals((String)CheckMs[i][ColCheckExpDate])) {
										CheckFg = false;
									}
									if(CheckFg) {
										WorkStockTotalQty	= WorkStockTotalQty+StockTotalQty.get(i01);
										WorkUnitQty			= WorkUnitQty+StockPlQty.get(i01);
									}
								}
							}
							
							if(NeedUnitQty<WorkUnitQty) {
								for(int i01=0;i01<StockItemCd.size();i01++) {
									if(((String)CheckMs[i][ColCheckItemCd]).equals(StockItemCd.get(i01))){
										boolean CheckFg = true;
										if(!"".equals((String)CheckMs[i][ColCheckLot])&&!StockLot.get(i01).equals((String)CheckMs[i][ColCheckLot])) {
											CheckFg = false;
										}
										if(!"".equals((String)CheckMs[i][ColCheckExpDate])&&!StockExpDate.get(i01).equals((String)CheckMs[i][ColCheckExpDate])) {
											CheckFg = false;
										}
										if(CheckFg) {
											if(StockPlQty.get(i01)>=NeedUnitQty) {
												NeedUnitQty = 0;
												NeedTotalQty = 0;
												StockPlQty.set(i01, StockPlQty.get(i01)-NeedUnitQty);
												StockTotalQty.set(i01, StockTotalQty.get(i01)-NeedTotalQty);
											}else {
												NeedUnitQty 	= NeedUnitQty  - StockPlQty.get(i01);
												NeedTotalQty 	= NeedTotalQty - StockTotalQty.get(i01);
												StockPlQty.set(i01, 0);
												StockTotalQty.set(i01, 0);
											}
										}
									}
									if(0==NeedTotalQty) {
										i01=StockItemCd.size()+1;
									}
								}
							}else {
								ErrOkuriNo.add((String)CheckMs[i][ColCheckOkuriNo]);
								CheckMs[i][ColCheckErrType]	= "LotKpn00";	//エラータイプ
								CheckMs[i][ColCheckErrMsg]	= "ロット賞味期限指定のパレット在庫が不足しています";	//エラーメッセージ
								for(int i01=0;i01<CheckMs.length;i01++) {
									if("".equals((String)CheckMs[i01][ColCheckErrType]) && ((String)CheckMs[i][ColCheckOkuriNo]).equals((String)CheckMs[i01][ColCheckOkuriNo])) {
										CheckMs[i][ColCheckErrType]	= "LotKpn01";	//エラータイプ
										CheckMs[i][ColCheckErrMsg]	= "別明細でロット賞味期限指定のパレット在庫が不足しています";	//エラーメッセージ
									}
								}
								
							}
						}
					}
				}
			}
			/*
			StockItemCd.add(GetItemCd);
			StockLot.add(GetLot);
			StockExpDate.add(GetExpdate);
			
			StockTotalQty.add(GetPossibleQty);
			StockPlQty.add(GetPlPossibleQty);
			StockCsQty.add(GetCsPossibleQty);
			StockCtQty.add(GetCtPossibleQty);
			StockBrQty.add(GetBrPossibleQty);
			StockPlUnitQty.add(GetPlUnitQty);
			StockCsUnitQty.add(GetCsUnitQty);
			StockCtUnitQty.add(GetCtUnitQty);
			*/
			//荷姿ケース指定
			for(int i=0;i<CheckMs.length;i++) {
				if(null==CheckMs[i][ColCheckLot]) {CheckMs[i][ColCheckLot]="";}
				if(null==CheckMs[i][ColCheckExpDate]) {CheckMs[i][ColCheckExpDate]="";}
				if(null==CheckMs[i][ColCheckErrType]) {CheckMs[i][ColCheckErrType]="";}
				if("".equals((String)CheckMs[i][ColCheckErrType]) ) {
					if("".equals((String)CheckMs[i][ColCheckLot])&&"".equals((String)CheckMs[i][ColCheckExpDate])) {
						
					}else {
						if(2==(int)CheckMs[i][ColCheckUnitType]) {
							int NeedTotalQty	= (int)CheckMs[i][ColCheckQty];		//必要数（バラ）
							int NeedUnitQty		= (int)CheckMs[i][ColCheckUnitQty];	//荷姿必要数量
							
							int WorkStockTotalQty	= 0;	//比較対象在庫数（バラ）
							int WorkUnitQty			= 0;	//比較対象在庫荷姿必要数量
							
							for(int i01=0;i01<StockItemCd.size();i01++) {
								if(((String)CheckMs[i][ColCheckItemCd]).equals(StockItemCd.get(i01))){
									boolean CheckFg = true;
									if(!"".equals((String)CheckMs[i][ColCheckLot])&&!StockLot.get(i01).equals((String)CheckMs[i][ColCheckLot])) {
										CheckFg = false;
									}
									if(!"".equals((String)CheckMs[i][ColCheckExpDate])&&!StockExpDate.get(i01).equals((String)CheckMs[i][ColCheckExpDate])) {
										CheckFg = false;
									}
									if(CheckFg) {
										WorkStockTotalQty	= WorkStockTotalQty+StockTotalQty.get(i01);
										WorkUnitQty			= WorkUnitQty+StockCsQty.get(i01);
									}
								}
							}
							
							if(NeedUnitQty<WorkUnitQty) {
								for(int i01=0;i01<StockItemCd.size();i01++) {
									if(((String)CheckMs[i][ColCheckItemCd]).equals(StockItemCd.get(i01))){
										boolean CheckFg = true;
										if(!"".equals((String)CheckMs[i][ColCheckLot])&&!StockLot.get(i01).equals((String)CheckMs[i][ColCheckLot])) {
											CheckFg = false;
										}
										if(!"".equals((String)CheckMs[i][ColCheckExpDate])&&!StockExpDate.get(i01).equals((String)CheckMs[i][ColCheckExpDate])) {
											CheckFg = false;
										}
										if(CheckFg) {
											if(StockPlQty.get(i01)>=NeedUnitQty) {
												NeedUnitQty = 0;
												NeedTotalQty = 0;
												StockPlQty.set(i01, StockPlQty.get(i01)-NeedUnitQty);
												StockTotalQty.set(i01, StockTotalQty.get(i01)-NeedTotalQty);
											}else {
												NeedUnitQty 	= NeedUnitQty  - StockPlQty.get(i01);
												NeedTotalQty 	= NeedTotalQty - StockTotalQty.get(i01);
												StockPlQty.set(i01, 0);
												StockTotalQty.set(i01, 0);
											}
										}
									}
								}
							}else {
								ErrOkuriNo.add((String)CheckMs[i][ColCheckOkuriNo]);
								CheckMs[i][ColCheckErrType]	= "LotKpn00";	//エラータイプ
								CheckMs[i][ColCheckErrMsg]	= "ロット賞味期限指定のパレット在庫が不足しています";	//エラーメッセージ
								for(int i01=0;i01<CheckMs.length;i01++) {
									if("".equals((String)CheckMs[i01][ColCheckErrType]) && ((String)CheckMs[i][ColCheckOkuriNo]).equals((String)CheckMs[i01][ColCheckOkuriNo])) {
										CheckMs[i][ColCheckErrType]	= "LotKpn01";	//エラータイプ
										CheckMs[i][ColCheckErrMsg]	= "別明細でロット賞味期限指定のパレット在庫が不足しています";	//エラーメッセージ
									}
								}
								
							}
						}
					}
				}
			}
			
			
		}
		
		
		
		
		
		
		
		
		Object[][] Rt	= DefaultSett(ErrCount);
		
		return Rt;
	}
	
	private static PreparedStatement MaxExpDateGet(String TgtWhCd,String TgtClCd) {
		String sql = "select "
				+"Max(KT0010_OKURI_HD.cl_cd) 				as ClCd,\n"				//荷主コード
				+"Max(KT0010_OKURI_HD.InvoiceWHCD) 			as InvoiceWhCd,\n"		//倉庫コード
				+"Max(KT0010_OKURI_HD.DeliCd) 				as DeliCd,\n"			//荷届先コード
				+"Max(KT0010_OKURI_HD.DeliDepartmentCd) 	as DeliDepartmentCd,\n"	//部署CD
				+"Max(KT0010_OKURI_HD.PlanDate) 			as PlanDate,\n"			//出荷予定日
				+"(WW0020ShipPlovision.OrderItemCd) as OrderItemCd,\n"				//商品コード
				+"Max(WW0020ShipPlovision.ShipExpdate) as ShipExpdate,\n"			//消費期限
				+" from "+A00000_Main.MySqlDefaultSchemaWANKO+".WW0020ShipPlovision \n"
				+" left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KT0010_OKURI_HD \n"
				+" on(WW0020ShipPlovision.cl_cd = KT0010_OKURI_HD.cl_cd"
				+" and WW0020ShipPlovision.InvoiceWHCD = KT0010_OKURI_HD.InvoiceWHCD"
				+" and WW0020ShipPlovision.OkuriNo = KT0010_OKURI_HD.OkuriNo"
				+")\n"
				+" left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KT0011_OKURI_MS \n"
				+" on(KT0010_OKURI_HD.cl_cd = KT0011_OKURI_MS.cl_cd"
				+" and KT0010_OKURI_HD.InvoiceWHCD = KT0011_OKURI_MS.InvoiceWHCD"
				+" and KT0010_OKURI_HD.OkuriNo = KT0011_OKURI_MS.OkuriNo"
				+")\n"
				+" where 1=1 \n"
				;
		sql = sql + " and KT0010_OKURI_HD.InvoiceWHCD ='"+TgtWhCd+"' \n";
		sql = sql + " and KT0010_OKURI_HD.cl_cd ='"+TgtClCd+"' \n";
		sql = sql + " and KT0011_OKURI_MS.ExpDate is null \n";
		sql = sql + " and (KT0011_OKURI_MS.Lot is null or KT0011_OKURI_MS.Lot = '') \n";
		
		sql = sql + " and KT0010_OKURI_HD.PlanDate<?\n";
		sql = sql + " and WW0020ShipPlovision.OrderItemCd=?\n";
		sql = sql + " and KT0010_OKURI_HD.DeliCd=?\n";
		sql = sql + " and KT0010_OKURI_HD.DeliDepartmentCd=?\n";
		
		sql = sql + " group by WW0020ShipPlovision.OrderItemCd";
		
		A100_DbConnect.DB_CONN("NYANKO");
		PreparedStatement stmt01 = null;
		try {
			stmt01 = A100_DbConnect.conn.prepareStatement(sql);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return stmt01;
	}
	private static PreparedStatement MinExpDateGet(String TgtWhCd,String TgtClCd) {
		String sql = "select "
				+"Min(KT0010_OKURI_HD.cl_cd) 				as ClCd,\n"				//荷主コード
				+"Min(KT0010_OKURI_HD.InvoiceWHCD) 			as InvoiceWhCd,\n"		//倉庫コード
				+"Min(KT0010_OKURI_HD.DeliCd) 				as DeliCd,\n"			//荷届先コード
				+"Min(KT0010_OKURI_HD.DeliDepartmentCd) 	as DeliDepartmentCd,\n"	//部署CD
				+"Min(KT0010_OKURI_HD.PlanDate) 			as PlanDate,\n"			//出荷予定日
				+"(WW0020ShipPlovision.OrderItemCd) as OrderItemCd,\n"				//商品コード
				+"Min(WW0020ShipPlovision.ShipExpdate) as ShipExpdate,\n"			//消費期限
				+" from "+A00000_Main.MySqlDefaultSchemaWANKO+".WW0020ShipPlovision \n"
				+" left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KT0010_OKURI_HD \n"
				+" on(WW0020ShipPlovision.cl_cd = KT0010_OKURI_HD.cl_cd"
				+" and WW0020ShipPlovision.InvoiceWHCD = KT0010_OKURI_HD.InvoiceWHCD"
				+" and WW0020ShipPlovision.OkuriNo = KT0010_OKURI_HD.OkuriNo"
				+")\n"
				+" left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KT0011_OKURI_MS \n"
				+" on(KT0010_OKURI_HD.cl_cd = KT0011_OKURI_MS.cl_cd"
				+" and KT0010_OKURI_HD.InvoiceWHCD = KT0011_OKURI_MS.InvoiceWHCD"
				+" and KT0010_OKURI_HD.OkuriNo = KT0011_OKURI_MS.OkuriNo"
				+")\n"
				+" where 1=1 \n"
				;
		sql = sql + " and KT0010_OKURI_HD.InvoiceWHCD ='"+TgtWhCd+"' \n";
		sql = sql + " and KT0010_OKURI_HD.cl_cd ='"+TgtClCd+"' \n";
		sql = sql + " and KT0011_OKURI_MS.ExpDate is null \n";
		sql = sql + " and (KT0011_OKURI_MS.Lot is null or KT0011_OKURI_MS.Lot = '') \n";
		
		sql = sql + " and KT0010_OKURI_HD.PlanDate>?\n";
		sql = sql + " and WW0020ShipPlovision.OrderItemCd=?\n";
		sql = sql + " and KT0010_OKURI_HD.DeliCd=?\n";
		sql = sql + " and KT0010_OKURI_HD.DeliDepartmentCd=?\n";
		
		sql = sql + " group by WW0020ShipPlovision.OrderItemCd";
		
		A100_DbConnect.DB_CONN("NYANKO");
		PreparedStatement stmt01 = null;
		try {
			stmt01 = A100_DbConnect.conn.prepareStatement(sql);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return stmt01;
	}
	
	
	private static Object[][] CheckMs(Object[][] CheckOkuriMsRt){
		int ColCheckOkuriNo		=  0;	//送り状番号
		int ColCheckDeliCd		=  1;	//届先CD
		int ColCheckDptCd		=  2;	//届先部署CD
		int ColCheckDeliName	=  3;	//届先名
		int ColCheckMsNo		=  4;	//明細番号
		int ColCheckItemCd		=  5;	//商品CD
		int ColCheckUnitType	=  6;	//荷姿タイプ
		int ColCheckQty			=  7;	//必要数（バラ）
		int ColCheckUnitQty		=  8;	//荷姿必要数量
		int ColCheckLot			=  9;	//ロット指定
		int ColCheckExpDate		= 10;	//賞味期限指定
		int ColCheckExpDateMin	= 11;	//引当対象賞味期限Min
		int ColCheckExpDateMax	= 12;	//引当対象賞味期限Msx
		int ColLotOneOnlyFg		= 13;	//ロット1種指定
		int ColCheckErrType		= 14;	//エラータイプ
		int ColCheckErrMsg		= 15;	//エラーメッセージ
		
		Object[][] CheckMs = new Object[CheckOkuriMsRt.length][16];
		for(int i=0;i<CheckOkuriMsRt.length;i++) {
			String GetClCd					= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColClCd];					//荷主コード
			String GetInvoiceWhCd			= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColInvoiceWhCd];			//倉庫コード
			String GetOkuriNo				= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColOkuriNo];				//送り状番号
			String GetClDeliN				= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColClDeliNo];				//荷主管理番号
			String GetPickupWhCd			= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColPickupWhCd];			//集荷倉庫CD
			int GetPurposeFG				= (int)CheckOkuriMsRt[i][T100_OkuriMsRt.ColPurposeFG];				//目的フラグ
			String GetPlanDate				= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColPlanDate];				//出荷予定日
			String GetShipDate				= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColShipDate];				//出荷実績日
			String GetSPPlanDate			= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColSPPlanDate];			//着日指定
			String GetSPDate				= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColSPDate];					//着日実績
			String GetSPTimeFG				= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColSPTimeFG];				//時間指定区分
			String GetSPTimeStr				= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColSPTimeStr];				//時間指定開始
			String GetSPTimeEnd				= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColSPTimeEnd];				//時間指定終了
			float GetTotalWeight			= (float)CheckOkuriMsRt[i][T100_OkuriMsRt.ColTotalWeight];			//荷物重量(kg)
			float GetTotalSize				= (float)CheckOkuriMsRt[i][T100_OkuriMsRt.ColTotalSize];				//荷物サイズ
			int GetTotalQty					= (int)CheckOkuriMsRt[i][T100_OkuriMsRt.ColTotalQty];					//個口数
			String GetDeliveryTypeCd01		= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColDeliveryTypeCd01];		//運送タイプ01
			String GetDeliTypeName			= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColDeliTypeName];			//運送タイプ名01
			String GetDeliveryTypeCd02		= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColDeliveryTypeCd02];		//運送タイプ02
			String GetDeliTypeName02		= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColDeliTypeName02];		//運送タイプ名02
			String GetDeliveryTypeCd03		= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColDeliveryTypeCd03];		//運送タイプ03
			String GetDeliTypeName03		= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColDeliTypeName03];		//運送タイプ名03
			String GetDeliveryTypeCd04		= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColDeliveryTypeCd04];		//運送タイプ04
			String GetDeliTypeName04		= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColDeliTypeName04];		//運送タイプ名04
			String GetDeliveryTypeCd05		= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColDeliveryTypeCd05];		//運送タイプ05
			String GetDeliTypeName05		= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColDeliTypeName05];		//運送タイプ名05

			int GetCodFG					= (int)CheckOkuriMsRt[i][T100_OkuriMsRt.ColCodFG];						//代引フラグ
			int GetCodPayTotal				= (int)CheckOkuriMsRt[i][T100_OkuriMsRt.ColCodPayTotal];				//代引収受金額合計
			int GetCodPay					= (int)CheckOkuriMsRt[i][T100_OkuriMsRt.ColCodPay];					//代引金額
			int GetCodConsumptionTax		= (int)CheckOkuriMsRt[i][T100_OkuriMsRt.ColCodConsumptionTax];		//代引消費税

			int GetChildrenFG				= (int)CheckOkuriMsRt[i][T100_OkuriMsRt.ColChildrenFG];				//子伝票区分
			String GetParentOkuriNo			= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColParentOkuriNo];			//親伝票番号
			
			String GetNiokuriCd				= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColNiokuriCd];				//荷送人コード
			String GetNiokuriDepartmentCd	= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColNiokuriDepartmentCd];	//荷送人部署CD
			String GetNiokuriName01			= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColNiokuriName01];			//荷送人名01
			String GetNiokuriName02			= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColNiokuriName02];			//荷送人名02
			String GetNiokuriName03			= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColNiokuriName03];			//荷送人名03
			String GetNiokuriPost			= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColNiokuriPost];			//荷送人郵便番号
			String GetNiokuriAdd01			= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColNiokuriAdd01];			//荷送人住所01
			String GetNiokuriAdd02			= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColNiokuriAdd02];			//荷送人住所02
			String GetNiokuriAdd03			= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColNiokuriAdd03];			//荷送人住所03
			String GetNioKuriTel			= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColNioKuriTel];			//荷送人TEL
			String GetNioKuriFax			= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColNioKuriFax];			//荷送人FAX
			String GetNioKuriMail			= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColNioKuriMail];			//荷送人MAIL
			String GetNiokuriMunicCd		= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColNiokuriMunicCd];		//荷送人市区町村CD

			String GetDeliCd				= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColDeliCd];					//荷届先コード
			String GetClDeliCd				= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColClDeliCd];				//荷主荷届先コード
			String GetDeliDepartmentCd		= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColDeliDepartmentCd];		//部署CD
			String GetDeliName01			= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColDeliName01];			//荷届先名01
			String GetDeliName02			= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColDeliName02];			//荷届先名02
			String GetDeliName03			= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColDeliName03];			//荷届先名03
			String GetDeliPost				= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColDeliPost];				//荷届先郵便番号
			String GetDeliAdd01				= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColDeliAdd01];				//荷届先住所01
			String GetDeliAdd02				= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColDeliAdd02];				//荷届先住所02
			String GetDeliAdd03				= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColDeliAdd03];				//荷届先住所03
			String GetDeliTel				= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColDeliTel];				//荷届先TEL
			String GetDeliFax				= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColDeliFax];				//荷届先FAX
			String GetDeliMail				= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColDeliMail];				//荷届先MAIL
			String GetDeliMunicCd			= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColDeliMunicCd];			//荷届先市区町村CD

			String GetCom01					= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColCom01];					//コメント01
			String GetCom02					= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColCom02];					//コメント02
			String GetCom03					= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColCom03];					//コメント03
			String GetCom04					= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColCom04];					//コメント04
			String GetCom05					= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColCom05];					//コメント05

			int GetStatus					= (int)CheckOkuriMsRt[i][T100_OkuriMsRt.ColStatus];					//運送状況
			int GetTaxFg					= (int)CheckOkuriMsRt[i][T100_OkuriMsRt.ColTaxFg];						//税区分
			int GetTaxRate					= (int)CheckOkuriMsRt[i][T100_OkuriMsRt.ColTaxRate];					//税率
			int GetDeliFee					= (int)CheckOkuriMsRt[i][T100_OkuriMsRt.ColDeliFee];					//運賃
			int GetAddDeliFee01				= (int)CheckOkuriMsRt[i][T100_OkuriMsRt.ColAddDeliFee01];				//付帯費用1
			int GetAddDeliFee02				= (int)CheckOkuriMsRt[i][T100_OkuriMsRt.ColAddDeliFee02];				//付帯費用2
			int GetAddDeliFee03				= (int)CheckOkuriMsRt[i][T100_OkuriMsRt.ColAddDeliFee03];				//付帯費用3
			int GetHaighWayFee01			= (int)CheckOkuriMsRt[i][T100_OkuriMsRt.ColHaighWayFee01];			//高速代等実費精算分1（内税）
			int GetHaighWayFee02			= (int)CheckOkuriMsRt[i][T100_OkuriMsRt.ColHaighWayFee02];			//高速代等実費精算分2（内税）
			int GetConsumptionTax			= (int)CheckOkuriMsRt[i][T100_OkuriMsRt.ColConsumptionTax];			//消費税
			int GetWithOutTaxTotal			= (int)CheckOkuriMsRt[i][T100_OkuriMsRt.ColWithOutTaxTotal];			//税別合計金額
			int GetTotalFee					= (int)CheckOkuriMsRt[i][T100_OkuriMsRt.ColTotalFee];					//税込請求額合計
			int GetFeeFixFG					= (int)CheckOkuriMsRt[i][T100_OkuriMsRt.ColFeeFixFG];					//金額確定フラグ
			String GetFeeFixDate			= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColFeeFixDate];			//金額確定日時
			int GetReceiptStampFG			= (int)CheckOkuriMsRt[i][T100_OkuriMsRt.ColReceiptStampFG];			//受領印チェック
			String GetReceiptStampDate		= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColReceiptStampDate];		//受領印日時
			int GetInvoiceStatus			= (int)CheckOkuriMsRt[i][T100_OkuriMsRt.ColInvoiceStatus];			//請求ステータス
			String GetEntryDate				= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColEntryDate];				//登録日
			String GetUpdateDate			= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColUpdateDate];			//更新日
			String GetEntryUser				= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColEntryUser];				//登録者
			String GetUpdateUser			= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColUpdateUser];			//更新者
			String GetEntryPG				= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColEntryPG];				//登録プログラム
			String GetUpdatePG				= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColUpdatePG];				//更新プログラム

			String GetUseFeeBasePtCd		= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColUseFeeBasePtCd];		//適用運賃タリフCD
			int GetWmsStatus				= (int)CheckOkuriMsRt[i][T100_OkuriMsRt.ColWmsStatus];				//在庫管理ステータス
			String GetWmsShipDate			= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColWmsShipDate];			//倉庫出荷日
			String GetCourseGpCd			= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColCourseGpCd];			//コースグループコード
			String GetCourseCD				= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColCourseCD];				//一次配車コースコード
			int GetCourseCDEda				= (int)CheckOkuriMsRt[i][T100_OkuriMsRt.ColCourseCDEda];				//一次配車コースコード枝番
			String GetPitGrp				= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColPitGrp];					//一次配車払出ピットグループ
			String GetPit01					= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColPit01];					//一次配車払出ピット01
			String GetPit02					= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColPit02];					//一次配車払出ピット02
			String GetPit03					= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColPit03];					//一次配車払出ピット03
			String GetPit04					= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColPit04];					//一次配車払出ピット04
			String GetPit05					= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColPit05];					//一次配車払出ピット05

			String GetCLName01				= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColCLName01];				//荷主名
			String GetClGpCD				= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColClGpCD];					//荷主グループCD
			String GetCLGpName01			= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColCLGpName01];			//荷主グループ標記名
			
			
			String GetMsClCd				= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColMsClCd];					//明細荷主コード
			String GetMsInvoiceWhCd			= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColMsInvoiceWhCd];			//明細倉庫コード
			String GetMsOkuriNo				= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColMsOkuriNo];				//明細送り状番号
			int GetMsNo						= (int)CheckOkuriMsRt[i][T100_OkuriMsRt.ColMsNo];						//明細番号
			String GetMsDeliNo				= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColMsDeliNo];				//明細出荷番号
			int GetMsDelliMsNo				= (int)CheckOkuriMsRt[i][T100_OkuriMsRt.ColMsDelliMsNo];				//明細出荷番号明細番号
			String GetMsClOrderNo			= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColMsClOrderNo];			//明細荷主管理番号
			String GetMsClGpCd				= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColMsClGpCd];				//明細荷主グループコード
			String GetMsItemCd				= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColMsItemCd];				//明細商品コード
			String GetMsItemName01			= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColMsItemName01];			//明細商品表記名
			String GetMsItemName02			= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColMsItemName02];			//明細商品正式名
			String GetMsItemName03			= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColMsItemName03];			//明細商品略名
			float GetMsUnitWeight			= (float)CheckOkuriMsRt[i][T100_OkuriMsRt.ColMsUnitWeight];			//明細単位重量
			float GetMsUnitSize				= (float)CheckOkuriMsRt[i][T100_OkuriMsRt.ColMsUnitSize];				//明細単位サイズ
			int GetMsQty					= (int)CheckOkuriMsRt[i][T100_OkuriMsRt.ColMsQty];						//明細個数
			int GetMsPackingQty				= (int)CheckOkuriMsRt[i][T100_OkuriMsRt.ColMsPackingQty];				//明細荷姿数量
			String GetMsUnitName			= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColMsUnitName];			//明細明細単位
			float GetMsSubTotalWeight		= (float)CheckOkuriMsRt[i][T100_OkuriMsRt.ColMsSubTotalWeight];		//明細明細重量
			float GetMsSubTotalSize			= (float)CheckOkuriMsRt[i][T100_OkuriMsRt.ColMsSubTotalSize];			//明細明細サイズ
			float GetMsUnitPrice			= (float)CheckOkuriMsRt[i][T100_OkuriMsRt.ColMsUnitPrice];			//明細単価
			float GetMsSubTotalPrice		= (float)CheckOkuriMsRt[i][T100_OkuriMsRt.ColMsSubTotalPrice];		//明細金額
			String GetMsCategoryCd			= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColMsCategoryCd];			//明細商品分類
			String GetMsCategoryName		= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColMsCategoryName];		//明細商品分類名
			String GetMsTildFG				= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColMsTildFG];				//明細温度区分
			String GetMsTildName			= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColMsTildName];			//明細温度区分名
			String GetMsCom01				= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColMsCom01];				//明細コメント01
			String GetMsCom02				= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColMsCom02];				//明細コメント02
			String GetMsCom03				= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColMsCom03];				//明細コメント03
			String GetMsCom04				= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColMsCom04];				//明細コメント04
			String GetMsCom05				= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColMsCom05];				//明細コメント05
			String GetMsEntryDate			= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColMsEntryDate];			//明細登録日
			String GetMsUpdateDate			= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColMsUpdateDate];			//明細更新日
			String GetMsEntryUser			= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColMsEntryUser];			//明細登録者
			String GetMsUpdateUser			= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColMsUpdateUser];			//明細更新者
			String GetMsLot					= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColMsLot];					//明細ロット指定
			String GetMsExpDate				= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColMsExpDate];				//明細賞味期限指定
			int GetMsPackingType			= (int)CheckOkuriMsRt[i][T100_OkuriMsRt.ColMsPackingType];			//明細荷姿タイプ
			String GetMsClItemCd			= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColMsClItemCd];			//明細荷主商品CD
			String GetMsItemMDNo			= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColMsItemMDNo];			//明細型番
			String GetMsJanCd				= (String)CheckOkuriMsRt[i][T100_OkuriMsRt.ColMsJanCd];				//明細荷姿JanCd
			
			CheckMs[i][ColCheckOkuriNo]		= GetOkuriNo;			//送り状番号
			CheckMs[i][ColCheckDeliCd]		= GetDeliCd;			//届先CD
			CheckMs[i][ColCheckDptCd]		= GetDeliDepartmentCd;	//届先部署CD
			CheckMs[i][ColCheckDeliName]	= GetDeliName01;		//届先名
			CheckMs[i][ColCheckMsNo]		= GetMsNo;				//明細番号
			CheckMs[i][ColCheckItemCd]		= GetMsItemCd;			//商品CD
			CheckMs[i][ColCheckUnitType]	= GetMsPackingType;		//荷姿タイプ
			CheckMs[i][ColCheckQty]			= GetMsQty;				//必要数（バラ）
			CheckMs[i][ColCheckUnitQty]		= GetMsPackingQty;		//荷姿必要数量
			CheckMs[i][ColCheckLot]			= GetMsLot;				//ロット指定
			CheckMs[i][ColCheckExpDate]		= GetMsExpDate;			//賞味期限指定
			CheckMs[i][ColCheckExpDateMin]	= "";					//引当対象賞味期限Min
			CheckMs[i][ColCheckExpDateMax]	= "";					//引当対象賞味期限Msx
			CheckMs[i][ColLotOneOnlyFg]		= 0;					//ロット1種指定
			CheckMs[i][ColCheckErrType]		= "";					//エラータイプ
			CheckMs[i][ColCheckErrMsg]		= "";					//エラーメッセージ
		}
		
		return CheckMs;
	}
	
	private static Object[][] DefaultSett(int RowCount){
		Object[][] RtShipPlovision = RtShipPlovision();
		Object[][] Rt = new Object[RowCount][RtShipPlovision.length];
		
		for(int i=0;i<Rt.length;i++) {
			for(int i01=0;i01<RtShipPlovision.length;i01++) {
				switch((String)RtShipPlovision[i01][2]) {
					case "int":
						Rt[i][(int)RtShipPlovision[i01][1]] = (int)0;
						break;
					case "float":
						Rt[i][(int)RtShipPlovision[i01][1]] = (float)0;
						break;
					case "Date":
						Rt[i][(int)RtShipPlovision[i01][1]] = "";
						break;
					case "DateTime":
						Rt[i][(int)RtShipPlovision[i01][1]] = "";
						break;
					default:
						Rt[i][(int)RtShipPlovision[i01][1]] = "";
						break;
				}
			}
		}
		return Rt;
	}
	
	/*
	private static Object[][] RtOkuriItemLotExpDateSumRt(){
		Object[][] Rt = {
				 {"MsItemCd"			,RtOkuriItemLotExpDateSumRt_ColMsItemCd			,"String"	,"明細商品コード"				,"Key"		,"Detail Item Code"						,"明细商品代码"			,"Mã hàng chi tiết"}
				,{"MsQty"				,RtOkuriItemLotExpDateSumRt_ColMsQty				,"int"		,"明細個数"						,""			,"Detail Quantity"						,"明细数量"				,"Số lượng chi tiết"}
				,{"MsLot"				,RtOkuriItemLotExpDateSumRt_ColMsLot				,"String"	,"明細ロット指定"				,"Key"		,"Detail Specified Lot"					,"明细指定批次"			,"Lô chỉ định chi tiết"}
				,{"MsExpDate"			,RtOkuriItemLotExpDateSumRt_ColMsExpDate			,"Date"		,"明細賞味期限指定"				,"Key"		,"Detail Specified Expiration Date"		,"明细指定保质期"			,"Hạn sử dụng chỉ định chi tiết"}
				,{"MsPackingType"		,RtOkuriItemLotExpDateSumRt_ColMsPackingType		,"int"		,"明細荷姿タイプ"				,"Key"		,"Detail Packing Type"					,"明细包装类型"				,"Loại đóng gói chi tiết"}
				};
		Rt = B100_LanguageControl.RtControl(Rt);
		return Rt;
	}
	
	private static Object[][] OkuriItemLotExpDateSumRt(String TgtWhCd,String TgtClCd,ArrayList<String> TgtOkurino){
		//未引当出荷指示データの商品・ロット賞味期限・荷姿タイプ別数量を取得する
		Object[][] Rt = new Object[0][RtOkuriItemLotExpDateSumRt().length];
		boolean SearchKick = false;
		
		String sql = "select "
				+"(KT0011_OKURI_MS.ItemCd) 				as MsItemCd,\n"				//明細商品コード
				+"(KT0011_OKURI_MS.Lot) 				as MsLot,\n"				//明細ロット指定
				+"(KT0011_OKURI_MS.ExpDate) 			as MsExpDate,\n"			//明細賞味期限指定
				+"(KT0011_OKURI_MS.PackingType) 		as MsPackingType,\n"		//明細荷姿タイプ
				+"sum(KT0011_OKURI_MS.Qty) 				as MsQty \n"				//明細個数
				+" from "+A00000_Main.MySqlDefaultSchemaNYANKO+".KT0010_OKURI_HD \n"
				+" left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KT0011_OKURI_MS \n"
				+" on(KT0010_OKURI_HD.cl_cd = KT0011_OKURI_MS.cl_cd"
				+" and KT0010_OKURI_HD.InvoiceWHCD = KT0011_OKURI_MS.InvoiceWHCD"
				+" and KT0010_OKURI_HD.OkuriNo = KT0011_OKURI_MS.OkuriNo"
				+")\n"
				+" where KT0010_OKURI_HD.cl_cd = "		+TgtClCd+"\n"
				+" and KT0010_OKURI_HD.InvoiceWHCD = "	+TgtWhCd+"\n"
				+" and KT0010_OKURI_HD.WmsStatus = 0 \n";
				
			if(null!=TgtOkurino && 0<TgtOkurino.size()) {
				SearchKick = true;
				sql = sql + " and (";
				for(int i=0;i<TgtOkurino.size();i++) {
					if(0<i) {sql = sql + " or ";}
					sql = sql + "KT0010_OKURI_HD.OkuriNo = ?";
				}
				sql = sql + ")\n";
			}
			
			sql = sql+" group by KT0011_OKURI_MS.ItemCd,KT0011_OKURI_MS.Lot,KT0011_OKURI_MS.ExpDate,KT0011_OKURI_MS.PackingType";
			sql = sql+" order by KT0011_OKURI_MS.ItemCd,KT0011_OKURI_MS.Lot,KT0011_OKURI_MS.ExpDate,KT0011_OKURI_MS.PackingType";
			if(SearchKick) {
				A100_DbConnect.DB_CONN("NYANKO");
				ResultSet rset01 = null;
				PreparedStatement stmt01 = null;
				try {
					stmt01 = A100_DbConnect.conn.prepareStatement(sql);
					int StmtCount = 0;
					if(null!=TgtOkurino && 0<TgtOkurino.size()) {
						for(int i=0;i<TgtOkurino.size();i++) {
							StmtCount = StmtCount+1;
							stmt01.setString(StmtCount, ""+TgtOkurino.get(i)+"");
						}
					}
					rset01 = stmt01.executeQuery();
					
					Rt = B100_RtObjectCreate.B100_RtObjectCreate(rset01,RtOkuriItemLotExpDateSumRt());
					
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
		return Rt;
	}
	*/
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
		
		SearchClWh.add(TgtWhCd);
		SearchClCd.add(TgtClCd);
		SearchParaCd.add("SelfishBanExpDateOld");
		
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
		
		SearchClWh.add(TgtWhCd);
		SearchClCd.add(TgtClCd);
		SearchParaCd.add("SelfishLot");
		
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
	/*
	private static PreparedStatement StockItemLotExpDateSumRt(String TgtWhCd,String TgtClCd) {
		//ロケ・商品・賞味期限別（出荷可能）在庫を検索するPreparedStatement返却
		Object[][] Rt = new Object[0][100];
		String sql = "select \n"
				+"(WW0015Stock.ClCd) as ClCd,\n"						//荷主コード
				+"max(KM0030_CLIENTMST.CLName01) as CLName,\n"			//荷主表記名
				+"(WW0015Stock.WhCd) as WhCd,\n"						//倉庫コード
				+"max(KM0010_WHMST.WHName) as ClWHName,\n"				//担当倉庫名
				+"max(KM0030_CLIENTMST.ClGpCD) as ClGpCD,\n"			//荷主グループCD
				+"max(KM0031_CLIENT_GROUP.ClGpName01) as ClGpName,\n"	//グループ名1
				+"(WW0015Stock.ItemCd) as ItemCd,\n"					//商品コード
				+"(WW0015Stock.Lot) as Lot,\n"							//ロット
				+"(WW0015Stock.Expdate) as Expdate,\n"					//消費期限
				+"(WW0015Stock.Loc) as Loc,\n"							//ロケーション
				+"max(WM0010LOCATIONMST.LocType) as LocType,\n"			//ロケタイプ　0:通常　1:保管　8:入荷時　9:引当禁止
				+"sum(WW0015Stock.Qty) as Qty,\n"						//数量
				+"sum(WW0015Stock.ShipPlanQty) as ShipPlanQty,\n"		//引当済数
				+"sum(WW0015Stock.PossibleQty) as PossibleQty,\n"		//出荷可能数
				+"max(WW0015Stock.ItemName) as ItemName,\n"				//商品名
				+"max(KM0060_ITEMMST.ItemName01) as ItemName01,\n"		//商品表記名
				+"max(KM0060_ITEMMST.ItemName02) as ItemName02,\n"		//商品正式名
				+"max(KM0060_ITEMMST.ItemName03) as ItemName03,\n"		//商品略名
				+"max(KM0061_ITEMMSTSUB.CtQty) as CtUnitQty,\n"			//カートン入数
				+"max(KM0061_ITEMMSTSUB.CsQty) as CsUnitQty,\n"			//ケース入数
				+"max(KM0061_ITEMMSTSUB.PlQty) as PlUnitQty,\n"			//パレット入数
				+"max(KM0060_ITEMMST.UnitName) as UnitName,\n"			//商品単位
				+"max(KM0061_ITEMMSTSUB.CtUnitName) as CtUnitName,\n"	//カートン商品単位
				+"max(KM0061_ITEMMSTSUB.CsUnitName) as CsUnitName,\n"	//ケース商品単位
				+"max(KM0061_ITEMMSTSUB.PlUnitName) as PlUnitName,\n"	//パレット商品単位
				+ " from "+A00000_Main.MySqlDefaultSchemaWANKO+".WW0015Stock"
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
				+ " left outer join "+A00000_Main.MySqlDefaultSchemaWANKO+".WM0010LOCATIONMST"
				+ " on("
				+ " WW0015Stock.ClCd = WM0010LOCATIONMST.ClCd"
				+ " and WW0015Stock.WhCd = WM0010LOCATIONMST.WhCd"
				+ " and WW0015Stock.Loc= WM0010LOCATIONMST.Loc"
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
				+ " where WW0015Stock.WhCd = "	+TgtWhCd+"\n"
				+ " and WW0015Stock.ClCd = "	+TgtClCd+"\n"
				+ " and WW0015Stock.PossibleQty >0"
				+ " and WW0015Stock.ItemCd = ? \n";
		
		sql = sql + " group by WW0015Stock.WhCd,WW0015Stock.ClCd,WW0015Stock.ItemCd,WW0015Stock.Loc,WW0015Stock.Expdate,WW0015Stock.Lot";
		sql = sql + " order by WW0015Stock.WhCd,WW0015Stock.ClCd,WW0015Stock.ItemCd,WW0015Stock.Expdate,WM0010LOCATIONMST.LocType,WW0015Stock.Loc,WW0015Stock.Lot";
		
		System.out.println(sql);
		
		A100_DbConnect.DB_CONN("WANKO");
		ResultSet rset01 = null;
		PreparedStatement stmt01 = null;
		try {
			stmt01 = A100_DbConnect.conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stmt01;
	}
	*/
	
	private static Object[][] StockRt(String TgtWhCd,String TgtClCd,ArrayList<String> TgtItemCd){
		ArrayList<String> SearchClCd				= new ArrayList<String>();			//荷主コード
		ArrayList<String> SearchWhCd				= new ArrayList<String>();			//倉庫コード
		ArrayList<String> SearchClGpCD				= new ArrayList<String>();			//荷主グループCD
		ArrayList<String> SearchLoc					= new ArrayList<String>();			//ロケーション
		ArrayList<Integer>SearchType				= new ArrayList<Integer>();			//ロケタイプ　0:通常　1:保管　8:入荷時　9:引当禁止
		ArrayList<String> SearchItemCd				= TgtItemCd;						//商品コード
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
		boolean SortItemcdMode = true;													//商品CDでソート
		
		if(null!=TgtItemCd&&0<TgtItemCd.size()) {
			SearchClCd.add(TgtClCd);
			SearchWhCd.add(TgtWhCd);
			SearchPossibleQtyMin.add(1);
		}
		
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
		return StockRt;
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