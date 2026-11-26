import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.JOptionPane;

public class WT100_ShipPlovision{
	//在庫引き当て
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
	static final int ColFixLot					= (int)17;	//引当ロット
	static final int ColFixExpDate			= (int)18;	//引当賞味期限
	static final int ColOrderItemName01		= (int)19;	//受注品名01
	static final int ColPlovisionCheck		= (int)20;	//引当済マーキング
	
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
						,{"FixLot"					,ColFixLot					,"String"	,"引当ロット"			,""		}
						,{"FixExpDate"				,ColFixExpDate			,"String"	,"引当賞味期限"			,""		}
						,{"OrderItemName01"			,ColOrderItemName01		,"String"	,"受注品名01"			,""		}
						,{"PlovisionCheck"			,ColPlovisionCheck		,"int"		,"引当済マーキング"		,""		}
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
			ArrayList<String> TgtPlanDateList	= new ArrayList<String>();
			ArrayList<Integer> TgtPlanDateCount	= new ArrayList<Integer>();
			//出荷予定日リスト化する
			for(int i=0;i<Rt.length;i++) {
				if("".equals((String)Rt[i][ColErrType])) {
					boolean UnHitFg = true;
					for(int i01=0;i01<TgtPlanDateList.size();i01++) {
						if(TgtPlanDateList.get(i01).equals((String)Rt[i][ColPlanDate])) {
							UnHitFg = false;
							i01=TgtPlanDateList.size()+1;
						}
					}
					if(UnHitFg) {
						TgtPlanDateList.add((String)Rt[i][ColPlanDate]);
						TgtPlanDateCount.add(0);
					}
				}
			}
			TgtPlanDateList.sort(Comparator.naturalOrder());
			//各出荷予定日毎の明細行数把握
			for(int i=0;i<TgtPlanDateList.size();i++) {
				for(int i01=0;i01<Rt.length;i01++) {
					if("".equals((String)Rt[i01][ColErrType])&&TgtPlanDateList.get(i).equals((String)Rt[i01][ColPlanDate])) {
						TgtPlanDateCount.set(i, TgtPlanDateCount.get(i)+1);
					}
				}
			}
			//出荷予定日古い順に処理引当まで走る
			for(int i=0;i<TgtPlanDateList.size();i++) {
				Object[][] CheckTgtRt = new Object[TgtPlanDateCount.get(i)][RtShipPlovision().length];
				int counter = 0;
				for(int i01=0;i01<Rt.length;i01++) {
					if("".equals((String)Rt[i01][ColErrType])&&TgtPlanDateList.get(i).equals((String)Rt[i01][ColPlanDate])) {
						for(int i02=0;i02<Rt[i01].length;i02++) {
							CheckTgtRt[counter][i02]	= Rt[i01][i02];
						}
						counter= counter+1;
					}
				}
				CheckTgtRt = SelfishCheck(TgtWhCd,TgtClCd,CheckTgtRt);			//我儘な届先の出荷可能在庫条件を検出
				CheckTgtRt = StockCheck(TgtWhCd,TgtClCd,CheckTgtRt);				//在庫の過不足チェック
				CheckTgtRt = ItemCheck(TgtWhCd,TgtClCd,CheckTgtRt);				//日付ロットまとめの我儘を言わない場合同一商品の欠品があった場合引当対象から外す※我儘言う先には現時点で出荷可能な古い商品押し付けるので引き当てる
				CheckTgtRt = SlipCheck(TgtWhCd,TgtClCd,CheckTgtRt);				//伝票内に1行でも欠品があった場合引当対象から外す
				
				for(int i01=0;i01<Rt.length;i01++) {
					for(int i02=0;i02<CheckTgtRt.length;i02++) {
						if(((String)Rt[i01][ColOkuriNo]).equals((String)CheckTgtRt[i02][ColOkuriNo])
								&&(int)Rt[i01][ColMsNo]==(int)CheckTgtRt[i02][ColMsNo]) {
							for(int i03=0;i03<Rt[i01].length;i03++) {
								Rt[i01][i03]=CheckTgtRt[i02][i03];
							}
							i02=CheckTgtRt.length+1;
						}
					}
				}
				int PlovisionTgtCount = 0;
				for(int i01=0;i01<CheckTgtRt.length;i01++) {
					if("".equals((String)CheckTgtRt[i01][ColErrType])) {
						PlovisionTgtCount = PlovisionTgtCount+1;
					}
				}
				
				Object[][] PlovisionTgt = new Object[PlovisionTgtCount][RtShipPlovision().length];
				
				PlovisionTgtCount = 0;
				for(int i01=0;i01<CheckTgtRt.length;i01++) {
					if("".equals((String)CheckTgtRt[i01][ColErrType])) {
						for(int i02=0;i02<CheckTgtRt[i01].length;i02++) {
							PlovisionTgt[PlovisionTgtCount][i02]	= CheckTgtRt[i01][i02];
						}
						PlovisionTgtCount = PlovisionTgtCount+1;
					}
				}
				
				
				if(0<PlovisionTgt.length) {
					PlovisionTgt = StockCheck(TgtWhCd,TgtClCd,PlovisionTgt);				//欠品がおこらないはずのデータで再度在庫チェック
					PlovisionTgt = ShipPlovisionCore(TgtWhCd,TgtClCd,PlovisionTgt);		//引当処理コア
					
					//もしも途中で引当処理失敗していた場合、引当結果戻す
					ArrayList<String> ErrOkurino = new ArrayList<String>();
					for(int i01=0;i01<PlovisionTgt.length;i01++) {
						if(!"".equals((String)PlovisionTgt[i01][ColErrType])){
							ErrOkurino.add((String)PlovisionTgt[i01][ColOkuriNo]);
						}
					}
					if(null!=ErrOkurino && 0<ErrOkurino.size()) {
						WT100_ShipPlovisionCancel.ShipPlovisionCancel(TgtWhCd,TgtClCd,ErrOkurino);
					}
					
				}
				for(int i01=0;i01<Rt.length;i01++) {
					for(int i02=0;i02<PlovisionTgt.length;i02++) {
						if(((String)Rt[i01][ColOkuriNo]).equals((String)PlovisionTgt[i02][ColOkuriNo])
								&&(int)Rt[i01][ColMsNo]==(int)PlovisionTgt[i02][ColMsNo]) {
							for(int i03=0;i03<Rt[i01].length;i03++) {
								Rt[i01][i03]=PlovisionTgt[i02][i03];
							}
							
							i02=CheckTgtRt.length+1;
						}
					}
				}
			}
		}
		
		ArrayList<String>ErrMsg = new ArrayList<String>();
		for(int i=0;i<Rt.length;i++) {
			if(!"".equals((String)Rt[i][ColErrType])) {
				ErrMsg.add((String)Rt[i][ColOkuriNo]+(String)Rt[i][ColErrType]+":"+(String)Rt[i][ColErrMsg]+"(商品CD:"+(String)Rt[i][ColItemCd]+"×必要数:"+(int)Rt[i][ColNeedQty]+")("+(String)Rt[i][ColDeCd]+":"+(String)Rt[i][ColDeName]+")");
			}
		}
		
		if(null!=ErrMsg && 0<ErrMsg.size()) {
			ErrView(ErrMsg);
		}
		JOptionPane.showMessageDialog(null, "引当処理終了");
		return Rt;
	}
	
	private static Object[][] SlipCheck(String TgtWhCd,String TgtClCd,Object[][] CheckTgtRt){
		ArrayList<String> ErrOkuriNo = new ArrayList();
		for(int i=0;i<CheckTgtRt.length;i++) {
			if(null!=CheckTgtRt[i][ColErrType] && !"".equals((String)CheckTgtRt[i][ColErrType])) {
				ErrOkuriNo.add((String)CheckTgtRt[i][ColOkuriNo]);
			}
		}
		ErrOkuriNo 	= B100_ArrayListControl.ArryListStringUniqueList(ErrOkuriNo);	//重複除く
		
		for(int i=0;i<CheckTgtRt.length;i++) {
			if(null!=CheckTgtRt[i][ColErrType] && !"".equals((String)CheckTgtRt[i][ColErrType])) {
				
			}else {
				for(int i01=0;i01<ErrOkuriNo.size();i01++) {
					if(ErrOkuriNo.get(i01).equals((String)CheckTgtRt[i][ColOkuriNo])) {
						CheckTgtRt[i][ColErrType] 				= "KpnErrOtherRow";			//エラータイプ
						CheckTgtRt[i][ColErrMsg] 				= "別行に欠品があります";	//エラーメッセージ
						i01=ErrOkuriNo.size()+1;
					}
				}
			}
		}
		
		return CheckTgtRt;
	}
	
	private static Object[][] ItemCheck(String TgtWhCd,String TgtClCd,Object[][] CheckTgtRt){
		ArrayList<String> ErrItemList = new ArrayList();
		for(int i=0;i<CheckTgtRt.length;i++) {
			if(null!=CheckTgtRt[i][ColErrType] && !"".equals((String)CheckTgtRt[i][ColErrType])) {
				if((null==CheckTgtRt[i][ColLot]||"".equals((String)CheckTgtRt[i][ColLot]))	//ロット指定
						&&(null==CheckTgtRt[i][ColExpDate]||"".equals((String)CheckTgtRt[i][ColExpDate]))					//賞味期限指定
						&&(null==CheckTgtRt[i][ColCheckExpDateMin]||"".equals((String)CheckTgtRt[i][ColCheckExpDateMin]))	//引当対象賞味期限Min
						&&(null==CheckTgtRt[i][ColCheckExpDateMax]||"".equals((String)CheckTgtRt[i][ColCheckExpDateMax]))	//引当対象賞味期限Max
						&&1!=(int)CheckTgtRt[i][ColCheckLotOneOnlyFg]	//ロット1種指定
						) {
					ErrItemList.add((String)CheckTgtRt[i][ColItemCd]);
				}
			}
		}
		ErrItemList 	= B100_ArrayListControl.ArryListStringUniqueList(ErrItemList);	//重複除く
		for(int i=0;i<CheckTgtRt.length;i++) {
			if(null==CheckTgtRt[i][ColErrType] || "".equals((String)CheckTgtRt[i][ColErrType])) {
				if((null==CheckTgtRt[i][ColLot]||"".equals((String)CheckTgtRt[i][ColLot]))	//ロット指定
						&&(null==CheckTgtRt[i][ColExpDate]||"".equals((String)CheckTgtRt[i][ColExpDate]))					//賞味期限指定
						&&(null==CheckTgtRt[i][ColCheckExpDateMin]||"".equals((String)CheckTgtRt[i][ColCheckExpDateMin]))	//引当対象賞味期限Min
						&&(null==CheckTgtRt[i][ColCheckExpDateMax]||"".equals((String)CheckTgtRt[i][ColCheckExpDateMax]))	//引当対象賞味期限Max
						&&1!=(int)CheckTgtRt[i][ColCheckLotOneOnlyFg]	//ロット1種指定
						) {
					for(int i01=0;i01<ErrItemList.size();i01++) {
						if(ErrItemList.get(i01).equals((String)CheckTgtRt[i][ColItemCd])) {
							CheckTgtRt[i][ColErrType] 				= "KpnErr";			//エラータイプ
							CheckTgtRt[i][ColErrMsg] 				= "出荷可能な在庫が不足しています";	//エラーメッセージ
							i01=ErrItemList.size();
						}
					}
				}
			}
		}
		
		return CheckTgtRt;
	}
	
	private static Object[][] WmsStatusCheck(String TgtWhCd,String TgtClCd,ArrayList<String> TgtOkurino) {
		Object[][] Rt = new Object[0][RtShipPlovision().length];
		
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
			Rt[Counter][ColCheckLotOneOnlyFg]	= 0;	//ロット1種指定
			Rt[Counter][ColChecPackingQty] 		= 0;	//荷姿数量
			Rt[Counter][ColPlanDate]				= "";	//出荷予定日
			Rt[Counter][ColDeCd]					= "";	//届先CD
			Rt[Counter][ColDptCd]					= "";	//届先部署CD
			Rt[Counter][ColDeName]					= "";	//届先名
			Rt[Counter][ColFixLot]					= "";	//引当ロット
			Rt[Counter][ColFixExpDate]			= "";	//引当賞味期限
			Rt[Counter][ColOrderItemName01]		= "";	//受注品名01
			Rt[Counter][ColPlovisionCheck]		= 0;	//引当済マーキング
			
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
			Rt[Counter][ColCheckLotOneOnlyFg]	= 0;	//ロット1種指定
			Rt[Counter][ColChecPackingQty] 		= 0;	//荷姿数量
			Rt[Counter][ColPlanDate]				= "";	//出荷予定日
			Rt[Counter][ColDeCd]					= "";	//届先CD
			Rt[Counter][ColDptCd]					= "";	//届先部署CD
			Rt[Counter][ColDeName]					= "";	//届先名
			Rt[Counter][ColFixLot]					= "";	//引当ロット
			Rt[Counter][ColFixExpDate]			= "";	//引当賞味期限
			Rt[Counter][ColOrderItemName01]		= "";	//受注品名01
			Rt[Counter][ColPlovisionCheck]		= 0;	//引当済マーキング
			
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
			String GetMsItemName01			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColMsItemName01];			//明細商品表記名
			
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
			Rt[Counter][ColCheckLotOneOnlyFg]	= 0;					//ロット1種指定
			Rt[Counter][ColChecPackingQty] 		= GetMsPackingQty;		//荷姿数量
			Rt[Counter][ColPlanDate]				= GetPlanDate;			//出荷予定日
			Rt[Counter][ColDeCd]					= GetDeliCd;			//届先CD
			Rt[Counter][ColDptCd]					= GetDeliDepartmentCd;	//届先部署CD
			Rt[Counter][ColDeName]					= GetDeliName01;		//届先名
			Rt[Counter][ColFixLot]					= "";					//引当ロット
			Rt[Counter][ColFixExpDate]			= "";					//引当賞味期限
			Rt[Counter][ColOrderItemName01]		= GetMsItemName01;		//受注品名01
			Rt[Counter][ColPlovisionCheck]		= 0;					//引当済マーキング
			
			Counter = Counter+1;
		}
		
		return Rt;
	}
	
	private static Object[][] SelfishCheck(String TgtWhCd,String TgtClCd,Object[][] CheckTgtRt){
		int AllSelfishLot		= AllSelfishLot(TgtWhCd,TgtClCd);
		int BanAllExpDateOld	= BanAllExpDateOld(TgtWhCd,TgtClCd);
		
		//我儘納品先の引き当て条件設定
		ArrayList<String> SearchDeCd = new ArrayList<String>();
		for(int i=0;i<CheckTgtRt.length;i++) {
			SearchDeCd.add((String)CheckTgtRt[i][ColDeCd]);
		}
		if(1==AllSelfishLot) {
			for(int i=0;i<CheckTgtRt.length;i++) {
				CheckTgtRt[i][ColCheckLotOneOnlyFg]	= 1;					//ロット1種指定
			}
		}else {
			Object[][] SelfishLot = SelfishBanExpDateOld(TgtWhCd,TgtClCd,SearchDeCd);
			for(int i=0;i<CheckTgtRt.length;i++) {
				for(int i01=0;i01<SelfishLot.length;i01++) {
					String GetParaTxt01		=(String)SelfishLot[i01][M100_ParameterMstWankoRt.ColParaTxt01];		//パラメータテキスト項目01 届先CD
					String GetParaTxt02		=(String)SelfishLot[i01][M100_ParameterMstWankoRt.ColParaTxt02];		//パラメータテキスト項目02 部署CD
					if(GetParaTxt01.equals((String)CheckTgtRt[i][ColDeCd])
							&& GetParaTxt02.equals((String)CheckTgtRt[i][ColDptCd])
							&&("".equals((String)CheckTgtRt[i][ColLot]) && "".equals((String)CheckTgtRt[i][ColExpDate]) )
							) {
						
						CheckTgtRt[i][ColCheckLotOneOnlyFg]	= 1;					//ロット1種指定
						
						i01=SelfishLot.length+1;
					}
				}
			}
		}
		
		PreparedStatement MaxExpDateGet=MaxExpDateGet(TgtWhCd,TgtClCd);
		PreparedStatement MinExpDateGet=MinExpDateGet(TgtWhCd,TgtClCd);
		ResultSet rsetMax = null;
		ResultSet rsetMin = null;
		Object[][] SelfishBanExpDateOld = SelfishBanExpDateOld(TgtWhCd,TgtClCd,SearchDeCd);
		
		int SetPlanDate			= 1;
		int SetOrderItemCd		= 2;
		int SetDeliCd			= 3;
		int SetDeliDepartmentCd	= 4;
		
		for(int i=0;i<CheckTgtRt.length;i++) {
			for(int i01=0;i01<SelfishBanExpDateOld.length;i01++) {
				String GetParaTxt01		=(String)SelfishBanExpDateOld[i01][M100_ParameterMstWankoRt.ColParaTxt01];		//パラメータテキスト項目01 届先CD
				String GetParaTxt02		=(String)SelfishBanExpDateOld[i01][M100_ParameterMstWankoRt.ColParaTxt02];		//パラメータテキスト項目02 部署CD
				if((
						GetParaTxt01.equals((String)CheckTgtRt[i][ColDeCd])
						&& GetParaTxt02.equals((String)CheckTgtRt[i][ColDptCd])
						&&("".equals((String)CheckTgtRt[i][ColLot]) && "".equals((String)CheckTgtRt[i][ColExpDate]) )
						)||(1==BanAllExpDateOld)
						) {
					try {
						MaxExpDateGet.setString(SetPlanDate			,(String)CheckTgtRt[i][ColPlanDate]);
						MaxExpDateGet.setString(SetOrderItemCd		,(String)CheckTgtRt[i][ColItemCd]);
						MaxExpDateGet.setString(SetDeliCd			,(String)CheckTgtRt[i][ColDeCd]);
						MaxExpDateGet.setString(SetDeliDepartmentCd	,(String)CheckTgtRt[i][ColDptCd]);
						
						MinExpDateGet.setString(SetPlanDate			,(String)CheckTgtRt[i][ColPlanDate]);
						MinExpDateGet.setString(SetOrderItemCd		,(String)CheckTgtRt[i][ColItemCd]);
						MinExpDateGet.setString(SetDeliCd			,(String)CheckTgtRt[i][ColDeCd]);
						MinExpDateGet.setString(SetDeliDepartmentCd	,(String)CheckTgtRt[i][ColDptCd]);
						rsetMax = MaxExpDateGet.executeQuery();
						rsetMin = MinExpDateGet.executeQuery();
						
						rsetMax.beforeFirst();
						while (rsetMax.next()) {
							if(null==rsetMax.getTimestamp("ShipExpdate")){
								
							}else {
								CheckTgtRt[i][ColCheckExpDateMax] = B100_DateTimeControl.dtmString2(rsetMax.getTimestamp("ShipExpdate"))[0];
							}
						}
						rsetMin.beforeFirst();
						while (rsetMin.next()) {
							if(null==rsetMin.getTimestamp("ShipExpdate")){
								
							}else {
								CheckTgtRt[i][ColCheckExpDateMin] = B100_DateTimeControl.dtmString2(rsetMin.getTimestamp("ShipExpdate"))[0];
							}
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					i01=SelfishBanExpDateOld.length+1;
				}
			}
		}
		
		try {
			if(null!=rsetMax) {rsetMax.close();}
			if(null!=rsetMin) {rsetMin.close();}
			if(null!=MaxExpDateGet) {MaxExpDateGet.close();}
			if(null!=MinExpDateGet) {MinExpDateGet.close();}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		A100_DbConnect.close();
		
		return CheckTgtRt;
	}
	
	private static Object[][] StockCheck(String TgtWhCd,String TgtClCd,Object[][] CheckTgtRt) {
		ArrayList<String> ItemList = new ArrayList<String>();
		
		for(int i=0;i<CheckTgtRt.length;i++) {
			ItemList.add((String)CheckTgtRt[i][ColItemCd]);
		}
		ItemList 	= B100_ArrayListControl.ArryListStringUniqueList(ItemList);	//商品CD重複除く
		
		PreparedStatement StockSummaryGet = StockSummaryGet(TgtWhCd,TgtClCd);
		ResultSet rsetStockSummary = null;
		int ItemCDSet = 1;
		for(int i=0;i<ItemList.size();i++) {
			ArrayList<Integer> QtyList = new ArrayList<Integer>();
			for(int i01=0;i01<CheckTgtRt.length;i01++) {
				if(ItemList.get(i).equals((String)CheckTgtRt[i01][ColItemCd])){
					QtyList.add((int)CheckTgtRt[i01][ColNeedQty]);
				}
			}
			QtyList 	= B100_ArrayListControl.ArryListIntegerUniqueList(QtyList);	//数量の重複除く
			QtyList.sort(Comparator.reverseOrder());
			try {
				StockSummaryGet.setString(ItemCDSet,ItemList.get(i));
				rsetStockSummary = StockSummaryGet.executeQuery();
				rsetStockSummary.beforeFirst();
				int counter = 0;
				while (rsetStockSummary.next()) {
					counter = counter+1;
				}
				
				int ColStockClCd				=  0;	//荷主コード
				int ColStockWhCd				=  1;	//倉庫コード
				int ColStockItemCd				=  2;	//商品コード
				int ColStockLot					=  3;	//ロット
				int ColStockExpdate				=  4;	//消費期限
				int ColStockQty					=  5;	//数量
				int ColStockShipPlanQty			=  6;	//引当済数
				int ColStockPossibleQty			=  7;	//出荷可能数
				int ColStockItemName			=  8;	//商品名
				int ColStockCtUnitQty			=  9;	//カートン入数
				int ColStockCsUnitQty			= 10;	//ケース入数
				int ColStockPlUnitQty			= 11;	//パレット入数
				int ColStockEntryDate			= 12;	//登録日時
				int ColStockUpdateDate			= 13;	//更新日時
				int ColStockEntryUser			= 14;	//登録者
				int ColStockUpdateUser			= 15;	//更新者
				int ColStockAfterPossibleQty	= 16;	//引当後出荷可能数
				int ColStockAfterShipQty		= 17;	//今回引当数
				
				Object[][] StockData = new Object[counter][18];
				rsetStockSummary.beforeFirst();
				counter = 0;
				
				while (rsetStockSummary.next()) {
					if(null==rsetStockSummary.getString("ClCd"			)){StockData[counter][ColStockClCd]			="";}else{StockData[counter][ColStockClCd]=rsetStockSummary.getString("ClCd");}						//荷主コード
					if(null==rsetStockSummary.getString("WhCd"			)){StockData[counter][ColStockWhCd]			="";}else{StockData[counter][ColStockWhCd]=rsetStockSummary.getString("WhCd");}						//倉庫コード
					if(null==rsetStockSummary.getString("ItemCd"		)){StockData[counter][ColStockItemCd]		="";}else{StockData[counter][ColStockItemCd]=rsetStockSummary.getString("ItemCd");}					//商品コード
					if(null==rsetStockSummary.getString("Lot"			)){StockData[counter][ColStockLot]			="";}else{StockData[counter][ColStockLot]=rsetStockSummary.getString("Lot");}						//ロット
					if(null==rsetStockSummary.getTimestamp("Expdate"	)){StockData[counter][ColStockExpdate]		="";}else{StockData[counter][ColStockExpdate]=B100_DateTimeControl.dtmString2(rsetStockSummary.getTimestamp("Expdate"))[0];}				//消費期限
					StockData[counter][ColStockQty]			=rsetStockSummary.getInt("Qty");						//数量
					StockData[counter][ColStockShipPlanQty]	=rsetStockSummary.getInt("ShipPlanQty");				//引当済数
					StockData[counter][ColStockPossibleQty]	=rsetStockSummary.getInt("PossibleQty");				//出荷可能数
					if(null==rsetStockSummary.getString("ItemName"		)){StockData[counter][ColStockItemName]		="";}else{StockData[counter][ColStockItemName]=rsetStockSummary.getString("ItemName");}				//商品名
					StockData[counter][ColStockCtUnitQty]	=rsetStockSummary.getInt("CtUnitQty");					//カートン入数
					StockData[counter][ColStockCsUnitQty]	=rsetStockSummary.getInt("CsUnitQty");					//ケース入数
					StockData[counter][ColStockPlUnitQty]	=rsetStockSummary.getInt("PlUnitQty");					//パレット入数
					if(null==rsetStockSummary.getTimestamp("EntryDate"	)){StockData[counter][ColStockEntryDate]	="";}else{StockData[counter][ColStockEntryDate]		=B100_DateTimeControl.dtmString2(rsetStockSummary.getTimestamp("EntryDate"))[1];}				//登録日時
					if(null==rsetStockSummary.getTimestamp("UpdateDate"	)){StockData[counter][ColStockUpdateDate]	="";}else{StockData[counter][ColStockUpdateDate]	=B100_DateTimeControl.dtmString2(rsetStockSummary.getTimestamp("UpdateDate"))[1];}				//更新日時
					if(null==rsetStockSummary.getString("EntryUser"		)){StockData[counter][ColStockEntryUser]	="";}else{StockData[counter][ColStockEntryUser]		=rsetStockSummary.getString("EntryUser");}			//登録者
					if(null==rsetStockSummary.getString("UpdateUser"	)){StockData[counter][ColStockUpdateUser]	="";}else{StockData[counter][ColStockUpdateUser]	=rsetStockSummary.getString("UpdateUser");}			//更新者
					StockData[counter][ColStockAfterPossibleQty]		=rsetStockSummary.getInt("PossibleQty");	//引当後出荷可能数
					StockData[counter][ColStockAfterShipQty]			=0;											//今回引当数
					counter = counter+1;
				}
				
				//ロット・賞味期限指定のある出荷指示ありかつ単一ロットの在庫チェック
				for(int i01=0;i01<QtyList.size();i01++) {
					for(int i02=0;i02<CheckTgtRt.length;i02++) {
						if(ItemList.get(i).equals((String)CheckTgtRt[i02][ColItemCd])
							&& QtyList.get(i01).equals((int)CheckTgtRt[i02][ColNeedQty])){
							if(	"".equals((String)CheckTgtRt[i02][ColLot])						//ロット指定
									&&"".equals((String)CheckTgtRt[i02][ColExpDate])			//賞味期限指定
									) {
							}else {
								if(1==(int)CheckTgtRt[i02][ColCheckLotOneOnlyFg]) {
									int WorkNeedQty 	= (int)CheckTgtRt[i02][ColNeedQty];
									
									for(int i03=0;i03<StockData.length;i03++) {
										if(("".equals((String)CheckTgtRt[i02][ColLot])||((String)StockData[i03][ColStockLot]).equals((String)CheckTgtRt[i02][ColLot]))
											&&("".equals((String)CheckTgtRt[i02][ColExpDate])||((String)StockData[i03][ColStockExpdate]).equals((String)CheckTgtRt[i02][ColExpDate]))
											&&0<(int)StockData[i03][ColStockAfterPossibleQty]) {
											
											if((int)StockData[i03][ColStockAfterPossibleQty]>=WorkNeedQty) {
												StockData[i03][ColStockAfterPossibleQty]=(int)StockData[i03][ColStockAfterPossibleQty]-WorkNeedQty;
												WorkNeedQty=0;
												CheckTgtRt[i02][ColFixLot] = (String)StockData[i03][ColStockLot];
												CheckTgtRt[i02][ColFixExpDate] = (String)StockData[i03][ColStockExpdate];
												i03=StockData.length+1;
											}
										}
									}
									if(0<WorkNeedQty) {
										CheckTgtRt[i02][ColErrType] = "KpnErrOneLot";
										CheckTgtRt[i02][ColErrMsg] = "ロット賞味期限指定("+CheckTgtRt[i02][ColLot]+":"+CheckTgtRt[i02][ColExpDate]+")の単一ロット在庫が不足しています";
									}
								}
							}
						}
					}
				}
				
				//ロット・賞味期限指定のある出荷指示ありかつ単一ロットまとめ不要の在庫チェック
				for(int i01=0;i01<QtyList.size();i01++) {
					for(int i02=0;i02<CheckTgtRt.length;i02++) {
						if(ItemList.get(i).equals((String)CheckTgtRt[i02][ColItemCd])
							&& QtyList.get(i01).equals((int)CheckTgtRt[i02][ColNeedQty])){
							if(	"".equals((String)CheckTgtRt[i02][ColLot])						//ロット指定
									&&"".equals((String)CheckTgtRt[i02][ColExpDate])			//賞味期限指定
									) {
							}else {
								if(1!=(int)CheckTgtRt[i02][ColCheckLotOneOnlyFg]) {
									int WorkNeedQty 	= (int)CheckTgtRt[i02][ColNeedQty];
									int WorkPackingQty 	= (int)CheckTgtRt[i02][ColChecPackingQty];		//荷姿数量
									int WorkPossibleQty = 0;
									int HitCount = 0;
									for(int i03=0;i03<StockData.length;i03++) {
										if(("".equals((String)CheckTgtRt[i02][ColLot])||((String)StockData[i03][ColStockLot]).equals((String)CheckTgtRt[i02][ColLot]))
											&&("".equals((String)CheckTgtRt[i02][ColExpDate])||((String)StockData[i03][ColStockExpdate]).equals((String)CheckTgtRt[i02][ColExpDate]))
											&&0<(int)StockData[i03][ColStockAfterPossibleQty]) {
											
											HitCount=HitCount+1;
											int UnitQty = 1;
											switch((int)CheckTgtRt[i02][ColPackingType]) {
												case 0:
													UnitQty = (int)1;
													break;
												case 1:
													UnitQty = (int)StockData[i03][ColStockCtUnitQty];
													break;
												case 2:
													UnitQty = (int)StockData[i03][ColStockCsUnitQty];
													break;
												case 3:
													UnitQty = (int)StockData[i03][ColStockPlUnitQty];
													break;
												default:
													break;
											}
											if(0>=UnitQty) {UnitQty=1;}
											
											if((int)StockData[i03][ColStockAfterPossibleQty]>=WorkNeedQty){
												StockData[i03][ColStockAfterPossibleQty]=(int)StockData[i03][ColStockAfterPossibleQty]-WorkNeedQty;
												WorkNeedQty=0;
												if(1==HitCount) {
													CheckTgtRt[i02][ColFixLot] 		= (String)StockData[i03][ColStockLot];
													CheckTgtRt[i02][ColFixExpDate] 	= (String)StockData[i03][ColStockExpdate];
												}
												i03=StockData.length+1;
											}else {
												int ShipQty = 	(int)StockData[i03][ColStockAfterPossibleQty]/UnitQty;
												ShipQty = ShipQty*UnitQty;
												
												WorkNeedQty = WorkNeedQty-ShipQty;
												StockData[i03][ColStockAfterPossibleQty]=(int)StockData[i03][ColStockAfterPossibleQty]-ShipQty;
											}
										}
									}
									if(0<WorkNeedQty) {
										CheckTgtRt[i02][ColErrType] = "KpnErrOneLot";
										CheckTgtRt[i02][ColErrMsg] = "ロット賞味期限指定("+CheckTgtRt[i02][ColLot]+":"+CheckTgtRt[i02][ColExpDate]+")の在庫が不足しています";
									}
								}
							}
						}
					}
				}
				
				//ロット指定はないが日付範囲指定があるかつロットまとめ必要な在庫チェック
				for(int i01=0;i01<QtyList.size();i01++) {
					for(int i02=0;i02<CheckTgtRt.length;i02++) {
						if(ItemList.get(i).equals((String)CheckTgtRt[i02][ColItemCd])
							&& QtyList.get(i01).equals((int)CheckTgtRt[i02][ColNeedQty])){
							if(	"".equals((String)CheckTgtRt[i02][ColLot])						//ロット指定
									&&"".equals((String)CheckTgtRt[i02][ColExpDate])			//賞味期限指定
									&&1==(int)CheckTgtRt[i02][ColCheckLotOneOnlyFg]
									) {
								if("".equals((String)CheckTgtRt[i02][ColCheckExpDateMin])
										&&"".equals((String)CheckTgtRt[i02][ColCheckExpDateMax])
										) {
								}else {
									int WorkNeedQty = (int)CheckTgtRt[i02][ColNeedQty];
									int WorkPackingQty 	= (int)CheckTgtRt[i02][ColChecPackingQty];		//荷姿数量
									int HitCount = 0;
									
									for(int i03=0;i03<StockData.length;i03++) {
										if(0<(int)StockData[i03][ColStockAfterPossibleQty]) {
											Timestamp StockExpDate = null;
											Timestamp ExpDateMin = null;
											Timestamp ExpDateMax = null;
											
											if(!"".equals((String)StockData[i03][ColStockExpdate])) {
												StockExpDate = B100_DateTimeControl.dtmTimestamp2((String)StockData[i03][ColStockExpdate])[0];
											}
											if(!"".equals((String)CheckTgtRt[i02][ColCheckExpDateMin])) {
												ExpDateMin = B100_DateTimeControl.dtmTimestamp2((String)CheckTgtRt[i02][ColCheckExpDateMin])[0];
											}
											if(!"".equals((String)CheckTgtRt[i02][ColCheckExpDateMax])) {
												ExpDateMax = B100_DateTimeControl.dtmTimestamp2((String)CheckTgtRt[i02][ColCheckExpDateMax])[0];
											}
											
											if((null==ExpDateMin||0<=B100_DateTimeControl.ddif(ExpDateMin,StockExpDate))
													&& (null==ExpDateMax||0<=B100_DateTimeControl.ddif(StockExpDate,ExpDateMax))
													) {
												
												if((int)StockData[i03][ColStockAfterPossibleQty]>=WorkNeedQty) {
													StockData[i03][ColStockAfterPossibleQty]=(int)StockData[i03][ColStockAfterPossibleQty]-WorkNeedQty;
													WorkNeedQty=0;
													
													CheckTgtRt[i02][ColFixLot] 		= (String)StockData[i03][ColStockLot];
													CheckTgtRt[i02][ColFixExpDate] 	= (String)StockData[i03][ColStockExpdate];
													
													i03=StockData.length+1;
												}
											}
										}
									}
									if(WorkNeedQty>0) {
										CheckTgtRt[i02][ColErrType] = "KpnErrExpDate";
										CheckTgtRt[i02][ColErrMsg] = "出荷可能な賞味期限範囲("+CheckTgtRt[i02][ColCheckExpDateMin]+"～"+CheckTgtRt[i02][ColCheckExpDateMax]+")の単一ロット在庫が不足しています";
									}
								}
							}
						}
					}
				}
				
				//ロット指定なし・出荷日付範囲気にしない※但しロットまとめ必要な出荷指示の在庫チェック
				for(int i01=0;i01<QtyList.size();i01++) {
					for(int i02=0;i02<CheckTgtRt.length;i02++) {
						if(ItemList.get(i).equals((String)CheckTgtRt[i02][ColItemCd])
							&& QtyList.get(i01).equals((int)CheckTgtRt[i02][ColNeedQty])){
							if(	"".equals((String)CheckTgtRt[i02][ColLot])						//ロット指定
									&&"".equals((String)CheckTgtRt[i02][ColExpDate])			//賞味期限指定
									&&1==(int)CheckTgtRt[i02][ColCheckLotOneOnlyFg]
									) {
								if("".equals((String)CheckTgtRt[i02][ColCheckExpDateMin])
										&&"".equals((String)CheckTgtRt[i02][ColCheckExpDateMax])
										) {
									int WorkNeedQty = (int)CheckTgtRt[i02][ColNeedQty];
									int WorkPackingQty 	= (int)CheckTgtRt[i02][ColChecPackingQty];		//荷姿数量
									
									for(int i03=0;i03<StockData.length;i03++) {
										if(0<(int)StockData[i03][ColStockAfterPossibleQty]) {
											if((int)StockData[i03][ColStockAfterPossibleQty]>=WorkNeedQty) {
												StockData[i03][ColStockAfterPossibleQty]=(int)StockData[i03][ColStockAfterPossibleQty]-WorkNeedQty;
												WorkNeedQty=0;
												
												CheckTgtRt[i02][ColFixLot] 		= (String)StockData[i03][ColStockLot];
												CheckTgtRt[i02][ColFixExpDate] 	= (String)StockData[i03][ColStockExpdate];
												
												i03=StockData.length+1;
											}
										}
									}
									if(WorkNeedQty>0) {
										CheckTgtRt[i02][ColErrType] = "KpnErr";
										CheckTgtRt[i02][ColErrMsg] = "出荷可能な在庫が不足しています。同一ロットにまとめて出荷可能な在庫がありません";
									}
								}
							}
						}
					}
				}
				//ロット指定はないが日付範囲指定がある。但しロットをまとめる必要はない在庫チェック
				for(int i01=0;i01<QtyList.size();i01++) {
					for(int i02=0;i02<CheckTgtRt.length;i02++) {
						if(ItemList.get(i).equals((String)CheckTgtRt[i02][ColItemCd])
							&& QtyList.get(i01).equals((int)CheckTgtRt[i02][ColNeedQty])){
							if(	"".equals((String)CheckTgtRt[i02][ColLot])						//ロット指定
									&&"".equals((String)CheckTgtRt[i02][ColExpDate])			//賞味期限指定
									&&1!=(int)CheckTgtRt[i02][ColCheckLotOneOnlyFg]
									) {
								if("".equals((String)CheckTgtRt[i02][ColCheckExpDateMin])
										&&"".equals((String)CheckTgtRt[i02][ColCheckExpDateMax])
										) {
								}else {
									int WorkNeedQty = (int)CheckTgtRt[i02][ColNeedQty];
									int WorkPackingQty 	= (int)CheckTgtRt[i02][ColChecPackingQty];		//荷姿数量
									int HitCount = 0;
									
									for(int i03=0;i03<StockData.length;i03++) {
										if(0<(int)StockData[i03][ColStockAfterPossibleQty]) {
											Timestamp StockExpDate = null;
											Timestamp ExpDateMin = null;
											Timestamp ExpDateMax = null;
											
											if(!"".equals((String)StockData[i03][ColStockExpdate])) {
												StockExpDate = B100_DateTimeControl.dtmTimestamp2((String)StockData[i03][ColStockExpdate])[0];
											}
											if(!"".equals((String)CheckTgtRt[i02][ColCheckExpDateMin])) {
												ExpDateMin = B100_DateTimeControl.dtmTimestamp2((String)CheckTgtRt[i02][ColCheckExpDateMin])[0];
											}
											if(!"".equals((String)CheckTgtRt[i02][ColCheckExpDateMax])) {
												ExpDateMax = B100_DateTimeControl.dtmTimestamp2((String)CheckTgtRt[i02][ColCheckExpDateMax])[0];
											}
											
											if((null==ExpDateMin||0<=B100_DateTimeControl.ddif(ExpDateMin,StockExpDate))
													&& (null==ExpDateMax||0<=B100_DateTimeControl.ddif(StockExpDate,ExpDateMax))
													) {
												HitCount=HitCount+1;
												if((int)StockData[i03][ColStockAfterPossibleQty]>=WorkNeedQty) {
													StockData[i03][ColStockAfterPossibleQty]=(int)StockData[i03][ColStockAfterPossibleQty]-WorkNeedQty;
													WorkNeedQty=0;
													if(1==HitCount) {
														CheckTgtRt[i02][ColFixLot] 		= (String)StockData[i03][ColStockLot];
														CheckTgtRt[i02][ColFixExpDate] 	= (String)StockData[i03][ColStockExpdate];
													}
													i03=StockData.length+1;
												}else {
													int UnitQty = 1;
													switch((int)CheckTgtRt[i02][ColPackingType]) {
														case 0:
															UnitQty = (int)1;
															break;
														case 1:
															UnitQty = (int)StockData[i03][ColStockCtUnitQty];
															break;
														case 2:
															UnitQty = (int)StockData[i03][ColStockCsUnitQty];
															break;
														case 3:
															UnitQty = (int)StockData[i03][ColStockPlUnitQty];
															break;
														default:
															break;
													}
													if(0>=UnitQty) {UnitQty=1;}
													int ShipQty = 	(int)StockData[i03][ColStockAfterPossibleQty]/UnitQty;
													WorkPackingQty = WorkPackingQty-ShipQty;
													ShipQty = ShipQty*UnitQty;
													
													WorkNeedQty=WorkNeedQty-ShipQty;
													StockData[i03][ColStockAfterPossibleQty]=(int)StockData[i03][ColStockAfterPossibleQty]-ShipQty;
												}
											}
										}
									}
									if(WorkNeedQty>0) {
										CheckTgtRt[i02][ColErrType] = "KpnErrExpDate";
										CheckTgtRt[i02][ColErrMsg] = "出荷可能なロット賞味期限("+CheckTgtRt[i02][ColCheckExpDateMin]+"～"+CheckTgtRt[i02][ColCheckExpDateMax]+")の在庫が不足しています";
									}
								}
							}
						}
					}
				}
				
				//ロット指定なし・出荷日付範囲気にしない全くわがままを言わない出荷指示の在庫チェック
				for(int i01=0;i01<QtyList.size();i01++) {
					for(int i02=0;i02<CheckTgtRt.length;i02++) {
						if(ItemList.get(i).equals((String)CheckTgtRt[i02][ColItemCd])
							&& QtyList.get(i01).equals((int)CheckTgtRt[i02][ColNeedQty])){
							if(	"".equals((String)CheckTgtRt[i02][ColLot])						//ロット指定
									&&"".equals((String)CheckTgtRt[i02][ColExpDate])			//賞味期限指定
									&&1!=(int)CheckTgtRt[i02][ColCheckLotOneOnlyFg]
									) {
								if("".equals((String)CheckTgtRt[i02][ColCheckExpDateMin])
										&&"".equals((String)CheckTgtRt[i02][ColCheckExpDateMax])
										) {
									int WorkNeedQty = (int)CheckTgtRt[i02][ColNeedQty];
									int WorkPackingQty 	= (int)CheckTgtRt[i02][ColChecPackingQty];		//荷姿数量
									
									for(int i03=0;i03<StockData.length;i03++) {
										if(0<(int)StockData[i03][ColStockAfterPossibleQty]) {
											if((int)StockData[i03][ColStockAfterPossibleQty]>=WorkNeedQty) {
												StockData[i03][ColStockAfterPossibleQty]=(int)StockData[i03][ColStockAfterPossibleQty]-WorkNeedQty;
												WorkNeedQty=0;
												i03=StockData.length+1;
											}else {
												int UnitQty = 1;
												switch((int)CheckTgtRt[i02][ColPackingType]) {
													case 0:
														UnitQty = (int)1;
														break;
													case 1:
														UnitQty = (int)StockData[i03][ColStockCtUnitQty];
														break;
													case 2:
														UnitQty = (int)StockData[i03][ColStockCsUnitQty];
														break;
													case 3:
														UnitQty = (int)StockData[i03][ColStockPlUnitQty];
														break;
													default:
														break;
												}
												if(0>=UnitQty) {UnitQty=1;}
												int ShipQty = 	(int)StockData[i03][ColStockAfterPossibleQty]/UnitQty;
												WorkPackingQty = WorkPackingQty-ShipQty;
												ShipQty = ShipQty*UnitQty;
												
												
												WorkNeedQty=WorkNeedQty-ShipQty;
												StockData[i03][ColStockAfterPossibleQty]=(int)StockData[i03][ColStockAfterPossibleQty]-ShipQty;
											}
										}
									}
									if(WorkNeedQty>0) {
										CheckTgtRt[i02][ColErrType] = "KpnErr";
										CheckTgtRt[i02][ColErrMsg] = "出荷可能な在庫が不足しています";
									}
								}
							}
						}
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		try {
			if(null!=rsetStockSummary) {rsetStockSummary.close();}
			if(null!=StockSummaryGet) {StockSummaryGet.close();}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		A100_DbConnect.close();
		
		return CheckTgtRt;
	}
	
	private static Object[][] ShipPlovisionCore(String TgtWhCd,String TgtClCd,Object[][] PlovisionTgt){
		String now_dtm = B100_DateTimeControl.dtmString2(B100_DateTimeControl.dtm()[1])[1];
		String UpdateUser = "(" + A00000_Main.LoginUserId + ")" + A00000_Main.LoginUserName;
		
		
		ArrayList<String> ItemList = new ArrayList<String>();
		
		for(int i=0;i<PlovisionTgt.length;i++) {
			ItemList.add((String)PlovisionTgt[i][ColItemCd]);
		}
		ItemList 	= B100_ArrayListControl.ArryListStringUniqueList(ItemList);	//商品CD重複除く
		
		ArrayList<String>ErrOkurino	= new ArrayList<String>();
		
		//引当順序在庫残少ない順もしくはロケ順の判断基準入手
		Object[][] PlovisionSortMode	= PlovisionSortMode(TgtWhCd,TgtClCd);
		String SortMode = "";
		if(0<PlovisionSortMode.length) {if(1==(int)PlovisionSortMode[0][M100_ParameterMstWankoRt.ColParaInt01]) {SortMode="Loc";}}
		
		//確認用の在庫ピンポイント検索と引当対象在庫一覧検索用PreparedStatement
		PreparedStatement StockSearchPinPoint	= StockSearchPinPoint(TgtWhCd,TgtClCd);
		PreparedStatement StockGet				= StockGet(TgtWhCd,TgtClCd,SortMode);
		
		ResultSet rsetStockSearchPinPoint = null;
		ResultSet rsetStockGet = null;
		
		//在庫更新用
		PreparedStatement StockControlCreate	= StockControlCreate(TgtWhCd,TgtClCd);
		//引当結果登録用
		PreparedStatement CreateShipPlovision	= CreateShipPlovision(TgtWhCd,TgtClCd);
		//送り状ヘッダ更新用
		PreparedStatement OkuriHdUpdate			= OkuriHdUpdate(TgtWhCd,TgtClCd);
		
		int ItemCDSet = 1;
		for(int i=0;i<ItemList.size();i++) {
			try {
				StockGet.setString(ItemCDSet,ItemList.get(i));
				rsetStockGet = StockGet.executeQuery();
				rsetStockGet.beforeFirst();
				int counter = 0;
				while (rsetStockGet.next()) {
					counter = counter+1;
				}
				int ColStockClCd		=   0;			//荷主コード
				int ColStockCLName01	=   1;			//荷主表記名
				int ColStockWhCd		=   2;			//倉庫コード
				int ColStockWHName		=   3;			//担当倉庫名
				int ColStockClGpCD		=   4;			//荷主グループCD
				int ColStockClGpName01	=   5;			//グループ名1
				int ColStockLoc			=   6;			//ロケーション
				int ColStockLocName		=   7;			//ロケーション名
				int ColStockLocType		=   8;			//ロケタイプ　0:通常　1:保管　8:入荷時　9:引当禁止
				int ColStockItemCd		=   9;			//商品コード
				int ColStockLot			=  10;			//ロット
				int ColStockExpdate		=  11;			//消費期限
				int ColStockActualDate	=  12;			//入荷実績日
				int ColStockQty			=  13;			//数量
				int ColStockShipPlanQty	=  14;			//引当済数
				int ColStockPossibleQty	=  15;			//出荷可能数
				int ColStockItemName	=  16;			//商品名
				int ColStockItemName01	=  17;			//商品表記名
				int ColStockItemName02	=  18;			//商品正式名
				int ColStockItemName03	=  19;			//商品略名
				int ColStockClItemCd	=  20;			//荷主商品コード
				int ColStockJanCd		=  21;			//ソースマーク_BCD（バラ）
				int ColStockItemMdNo	=  22;			//商品型番
				int ColStockCtUnitQty	=  23;			//カートン入数
				int ColStockCsUnitQty	=  24;			//ケース入数
				int ColStockPlUnitQty	=  25;			//パレット入数
				int ColStockUnitName	=  26;			//商品単位
				int ColStockCtUnitName	=  27;			//カートン商品単位
				int ColStockCsUnitName	=  28;			//ケース商品単位
				int ColStockPlUnitName	=  29;			//パレット商品単位
				int ColStockEntryDate	=  30;			//登録日時
				int ColStockUpdateDate	=  31;			//更新日時
				int ColStockEntryUser	=  32;			//登録者
				int ColStockUpdateUser	=  33;			//更新者
				int ColAfterStockPossibleQty	=  34;			//引当後出荷可能数
				
				int ColStockPinPointLoc        = 1;					//ロケーション
				int ColStockPinPointItemCd     = 2;					//商品コード
				int ColStockPinPointLot        = 3;					//ロット
				int ColStockPinPointExpdate    = 4;					//消費期限
				int ColStockPinPointActualDate = 5;					//入荷実績日
				
				
				int ColOkuriHdUpdateDate 	= 1;
				int ColOkuriHdUpdateUser 	= 2;
				int ColOkuriHdOkuriNo 		= 3;
				
				Object[][] StockData = new Object[counter][35];
				rsetStockGet.beforeFirst();
				counter = 0;
				while (rsetStockGet.next()) {
					if(null==rsetStockGet.getString("ClCd"			)){StockData[counter][ColStockClCd]			= "";}else{StockData[counter][ColStockClCd]			=rsetStockGet.getString("ClCd");}				//荷主コード
					if(null==rsetStockGet.getString("CLName"		)){StockData[counter][ColStockCLName01]		= "";}else{StockData[counter][ColStockCLName01]		=rsetStockGet.getString("CLName");}				//荷主表記名
					if(null==rsetStockGet.getString("WhCd"			)){StockData[counter][ColStockWhCd]			= "";}else{StockData[counter][ColStockWhCd]			=rsetStockGet.getString("WhCd");}				//倉庫コード
					if(null==rsetStockGet.getString("ClWHName"		)){StockData[counter][ColStockWHName]		= "";}else{StockData[counter][ColStockWHName]		=rsetStockGet.getString("ClWHName");}			//担当倉庫名
					if(null==rsetStockGet.getString("ClGpCD"		)){StockData[counter][ColStockClGpCD]		= "";}else{StockData[counter][ColStockClGpCD]		=rsetStockGet.getString("ClGpCD");}				//荷主グループCD
					if(null==rsetStockGet.getString("ClGpName"		)){StockData[counter][ColStockClGpName01]	= "";}else{StockData[counter][ColStockClGpName01]	=rsetStockGet.getString("ClGpName");}			//グループ名1
					if(null==rsetStockGet.getString("Loc"			)){StockData[counter][ColStockLoc]			= "";}else{StockData[counter][ColStockLoc]			=rsetStockGet.getString("Loc");}				//ロケーション
					if(null==rsetStockGet.getString("LocName"		)){StockData[counter][ColStockLocName]		= "";}else{StockData[counter][ColStockLocName]		=rsetStockGet.getString("LocName");}			//ロケーション名
					StockData[counter][ColStockLocType]=rsetStockGet.getInt("LocType");				//ロケタイプ　0:通常　1:保管　8:入荷時　9:引当禁止
					if(null==rsetStockGet.getString("ItemCd"		)){StockData[counter][ColStockItemCd]		= "";}else{StockData[counter][ColStockItemCd]		=rsetStockGet.getString("ItemCd");}				//商品コード
					if(null==rsetStockGet.getString("Lot"			)){StockData[counter][ColStockLot]			= "";}else{StockData[counter][ColStockLot]			=rsetStockGet.getString("Lot");}				//ロット
					if(null==rsetStockGet.getTimestamp("Expdate"	)){StockData[counter][ColStockExpdate]		= "";}else{StockData[counter][ColStockExpdate]		=B100_DateTimeControl.dtmString2(rsetStockGet.getTimestamp("Expdate"))[0];}		//消費期限
					if(null==rsetStockGet.getTimestamp("ActualDate"	)){StockData[counter][ColStockActualDate]	= "";}else{StockData[counter][ColStockActualDate]	=B100_DateTimeControl.dtmString2(rsetStockGet.getTimestamp("ActualDate"))[0];}		//入荷実績日
					StockData[counter][ColStockQty]=rsetStockGet.getInt("Qty");						//数量
					StockData[counter][ColStockShipPlanQty]=rsetStockGet.getInt("ShipPlanQty");		//引当済数
					StockData[counter][ColStockPossibleQty]=rsetStockGet.getInt("PossibleQty");		//出荷可能数
					if(null==rsetStockGet.getString("ItemName"		)){StockData[counter][ColStockItemName]		= "";}else{StockData[counter][ColStockItemName]		=rsetStockGet.getString("ItemName");}			//商品名
					if(null==rsetStockGet.getString("ItemName01"	)){StockData[counter][ColStockItemName01]	= "";}else{StockData[counter][ColStockItemName01]	=rsetStockGet.getString("ItemName01");}			//商品表記名
					if(null==rsetStockGet.getString("ItemName02"	)){StockData[counter][ColStockItemName02]	= "";}else{StockData[counter][ColStockItemName02]	=rsetStockGet.getString("ItemName02");}			//商品正式名
					if(null==rsetStockGet.getString("ItemName03"	)){StockData[counter][ColStockItemName03]	= "";}else{StockData[counter][ColStockItemName03]	=rsetStockGet.getString("ItemName03");}			//商品略名
					if(null==rsetStockGet.getString("ClItemCd"		)){StockData[counter][ColStockClItemCd]		= "";}else{StockData[counter][ColStockClItemCd]		=rsetStockGet.getString("ClItemCd");}			//荷主商品コード
					if(null==rsetStockGet.getString("JanCd"			)){StockData[counter][ColStockJanCd]		= "";}else{StockData[counter][ColStockJanCd]		=rsetStockGet.getString("JanCd");}				//ソースマーク_BCD（バラ）
					if(null==rsetStockGet.getString("ItemMdNo"		)){StockData[counter][ColStockItemMdNo]		= "";}else{StockData[counter][ColStockItemMdNo]		=rsetStockGet.getString("ItemMdNo");}			//商品型番
					StockData[counter][ColStockCtUnitQty]=rsetStockGet.getInt("CtUnitQty");			//カートン入数
					StockData[counter][ColStockCsUnitQty]=rsetStockGet.getInt("CsUnitQty");			//ケース入数
					StockData[counter][ColStockPlUnitQty]=rsetStockGet.getInt("PlUnitQty");			//パレット入数
					if(null==rsetStockGet.getString("UnitName"		)){StockData[counter][ColStockUnitName]	= "";}else{StockData[counter][ColStockUnitName]			=rsetStockGet.getString("UnitName");}			//商品単位
					if(null==rsetStockGet.getString("CtUnitName"	)){StockData[counter][ColStockCtUnitName]	= "";}else{StockData[counter][ColStockCtUnitName]	=rsetStockGet.getString("CtUnitName");}			//カートン商品単位
					if(null==rsetStockGet.getString("CsUnitName"	)){StockData[counter][ColStockCsUnitName]	= "";}else{StockData[counter][ColStockCsUnitName]	=rsetStockGet.getString("CsUnitName");}			//ケース商品単位
					if(null==rsetStockGet.getString("PlUnitName"	)){StockData[counter][ColStockPlUnitName]	= "";}else{StockData[counter][ColStockPlUnitName]	=rsetStockGet.getString("PlUnitName");}			//パレット商品単位
					if(null==rsetStockGet.getTimestamp("EntryDate"	)){StockData[counter][ColStockEntryDate]	= "";}else{StockData[counter][ColStockEntryDate]	=B100_DateTimeControl.dtmString2(rsetStockGet.getTimestamp("EntryDate"))[1];}				//登録日時
					if(null==rsetStockGet.getTimestamp("UpdateDate"	)){StockData[counter][ColStockUpdateDate]	= "";}else{StockData[counter][ColStockUpdateDate]	=B100_DateTimeControl.dtmString2(rsetStockGet.getTimestamp("UpdateDate"))[1];}		//更新日時
					if(null==rsetStockGet.getString("EntryUser"		)){StockData[counter][ColStockEntryUser]	= "";}else{StockData[counter][ColStockEntryUser]	=rsetStockGet.getString("EntryUser");}			//登録者
					if(null==rsetStockGet.getString("UpdateUser"	)){StockData[counter][ColStockUpdateUser]	= "";}else{StockData[counter][ColStockUpdateUser]	=rsetStockGet.getString("UpdateUser");}			//更新者
					StockData[counter][ColAfterStockPossibleQty]=rsetStockGet.getInt("PossibleQty");	//引当後出荷可能数
					counter = counter+1;
				}
				
				//引当ロット・賞味期限が定まっている在庫を引き当て
				//ロット・賞味期限指定のある出荷指示ありかつ単一ロットの在庫引当
				//ロット・賞味期限指定はないが日付範囲指定あるかつロットまとめ必要な在庫引当
				//ロット指定なし・出荷日付範囲気にしない※但しロットまとめ必要な出荷指示の在庫引当
				//※極力荷姿指定で引き当て（荷姿崩れまくっていたら最後にくっつける前提　そんな汚い倉庫運営しないでいただきたいが、あちこちの在庫かき集めたら1パレになることもあるでしょう）
				
				for(int i01=0;i01<PlovisionTgt.length;i01++) {
					if(ItemList.get(i).equals((String)PlovisionTgt[i01][ColItemCd])&&0==(int)PlovisionTgt[i01][ColPlovisionCheck]) {
						if(!"".equals((String)PlovisionTgt[i01][ColFixLot])||!"".equals((String)PlovisionTgt[i01][ColFixExpDate])) {
							PlovisionTgt[i01][ColPlovisionCheck] = 1;
							int Seq = 0;
							int NeedQty = (int)PlovisionTgt[i01][ColNeedQty];
							
							for(int i02=0;i02<StockData.length;i02++) {
								if(((String)StockData[i02][ColStockLot]).equals((String)PlovisionTgt[i01][ColFixLot])
										&& ((String)StockData[i02][ColStockExpdate]).equals((String)PlovisionTgt[i01][ColFixExpDate])) {

									int UnitQty = 1;
									switch((int)PlovisionTgt[i01][ColPackingType]) {
										case 0:
											UnitQty = (int)1;
											break;
										case 1:
											UnitQty = (int)StockData[i02][ColStockCtUnitQty];
											break;
										case 2:
											UnitQty = (int)StockData[i02][ColStockCsUnitQty];
											break;
										case 3:
											UnitQty = (int)StockData[i02][ColStockPlUnitQty];
											break;
										default:
											break;
									}
									if(0>=UnitQty) {UnitQty=1;}
									if(0<(int)StockData[i02][ColAfterStockPossibleQty]) {
										int WorkPossibleQty = (int)((int)StockData[i02][ColAfterStockPossibleQty]/UnitQty);
										WorkPossibleQty = WorkPossibleQty*UnitQty;
										int ShipQty = 0;
										if(WorkPossibleQty>=NeedQty) {
											ShipQty = NeedQty;
											NeedQty = 0;
										}else {
											ShipQty = WorkPossibleQty;
											NeedQty = NeedQty-WorkPossibleQty;
										}
										StockData[i02][ColAfterStockPossibleQty] = (int)StockData[i02][ColAfterStockPossibleQty]-ShipQty;
										//在庫更新
										boolean StockFg	= StockControl(StockControlCreate,(String)StockData[i02][ColStockLoc],(String)StockData[i02][ColStockItemCd],(String)StockData[i02][ColStockLot],(String)StockData[i02][ColStockExpdate],(String)StockData[i02][ColStockActualDate],ShipQty,now_dtm,UpdateUser);
										//引当結果登録
										if(StockFg) {
											boolean PlovisionFg	= CreateShipPlovisionKick(CreateShipPlovision
													,TgtWhCd,TgtClCd,ShipQty,Seq,now_dtm,UpdateUser
													,PlovisionTgt[i01]
													,StockData[i02]
													);
											if(PlovisionFg) {
												OkuriHdUpdate.setString(ColOkuriHdUpdateDate	,now_dtm);
												OkuriHdUpdate.setString(ColOkuriHdUpdateUser 	,UpdateUser);
												OkuriHdUpdate.setString(ColOkuriHdOkuriNo 		,(String)PlovisionTgt[i01][ColOkuriNo]);
												OkuriHdUpdate.executeUpdate();
												
												//更新後在庫確認　マイナス在庫発生していればエラー
												StockSearchPinPoint.setString(ColStockPinPointLoc        ,(String)StockData[i02][ColStockLoc]);					//ロケーション
												StockSearchPinPoint.setString(ColStockPinPointItemCd     ,(String)StockData[i02][ColStockItemCd]);				//商品コード
												StockSearchPinPoint.setString(ColStockPinPointLot        ,(String)StockData[i02][ColStockLot]);					//ロット
												StockSearchPinPoint.setString(ColStockPinPointExpdate    ,(String)StockData[i02][ColStockExpdate]);				//消費期限
												StockSearchPinPoint.setString(ColStockPinPointActualDate ,(String)StockData[i02][ColStockActualDate]);			//入荷実績日
												
												rsetStockSearchPinPoint	= StockSearchPinPoint.executeQuery();
												
												boolean AfterStoCkErr = true;
												rsetStockSearchPinPoint.beforeFirst();
												while (rsetStockSearchPinPoint.next()) {
													if(0<=rsetStockSearchPinPoint.getInt("PossibleQty")) {AfterStoCkErr = false;}
												}
												if(AfterStoCkErr) {
													PlovisionTgt[i01][ColErrType]	= "StockMinusErr";
													PlovisionTgt[i01][ColErrMsg]	= "謎ですがマイナス在庫生成しました。引当中に在庫が動いた？";
												}
											}else {
												PlovisionTgt[i01][ColErrType]	= "PlovisionEntryErr";
												PlovisionTgt[i01][ColErrMsg]	= "謎ですが引当結果登録に失敗しました";
												//引当てしまった在庫戻す
												StockFg	= StockControl(StockControlCreate,(String)StockData[i02][ColStockLoc],(String)StockData[i02][ColStockItemCd],(String)StockData[i02][ColStockLot],(String)StockData[i02][ColStockExpdate],(String)StockData[i02][ColStockActualDate],-1*ShipQty,now_dtm,UpdateUser);
											}
										}else {
											PlovisionTgt[i01][ColErrType]	= "StockControlErr";
											PlovisionTgt[i01][ColErrMsg]	= "謎ですが在庫更新失敗しました。引当中に在庫が動いた？";
										}
										Seq = Seq+1;
										if(0==NeedQty) {
											i02=StockData.length+1;
										}
									}
								}
							}
							if(0<NeedQty) {
								//在庫が散りまくった結果引当処理がうまくできていないため諦めてバラ扱いで引当　後で頑張って荷姿整えたまえ
								for(int i02=0;i02<StockData.length;i02++) {
									if(((String)StockData[i02][ColStockLot]).equals((String)PlovisionTgt[i01][ColFixLot])
											&& ((String)StockData[i02][ColStockExpdate]).equals((String)PlovisionTgt[i01][ColFixExpDate])) {
										if(0<(int)StockData[i02][ColAfterStockPossibleQty]) {
											int ShipQty = 0;
											if((int)StockData[i02][ColAfterStockPossibleQty]>=NeedQty) {
												ShipQty = NeedQty;
												NeedQty = 0;
											}else {
												ShipQty = (int)StockData[i02][ColAfterStockPossibleQty];
												NeedQty = NeedQty-(int)StockData[i02][ColAfterStockPossibleQty];
											}
											StockData[i02][ColAfterStockPossibleQty] = (int)StockData[i02][ColAfterStockPossibleQty]-ShipQty;
											//在庫更新
											boolean StockFg	= StockControl(StockControlCreate,(String)StockData[i02][ColStockLoc],(String)StockData[i02][ColStockItemCd],(String)StockData[i02][ColStockLot],(String)StockData[i02][ColStockExpdate],(String)StockData[i02][ColStockActualDate],ShipQty,now_dtm,UpdateUser);
											//引当結果登録
											if(StockFg) {
												boolean PlovisionFg	= CreateShipPlovisionKick(CreateShipPlovision
														,TgtWhCd,TgtClCd,ShipQty,Seq,now_dtm,UpdateUser
														,PlovisionTgt[i01]
														,StockData[i02]
														);
												if(PlovisionFg) {
													OkuriHdUpdate.setString(ColOkuriHdUpdateDate	,now_dtm);
													OkuriHdUpdate.setString(ColOkuriHdUpdateUser 	,UpdateUser);
													OkuriHdUpdate.setString(ColOkuriHdOkuriNo 		,(String)PlovisionTgt[i01][ColOkuriNo]);
													OkuriHdUpdate.executeUpdate();
													
													//更新後在庫確認　マイナス在庫発生していればエラー
													StockSearchPinPoint.setString(ColStockPinPointLoc        ,(String)StockData[i02][ColStockLoc]);					//ロケーション
													StockSearchPinPoint.setString(ColStockPinPointItemCd     ,(String)StockData[i02][ColStockItemCd]);				//商品コード
													StockSearchPinPoint.setString(ColStockPinPointLot        ,(String)StockData[i02][ColStockLot]);					//ロット
													StockSearchPinPoint.setString(ColStockPinPointExpdate    ,(String)StockData[i02][ColStockExpdate]);				//消費期限
													StockSearchPinPoint.setString(ColStockPinPointActualDate ,(String)StockData[i02][ColStockActualDate]);			//入荷実績日
													
													rsetStockSearchPinPoint	= StockSearchPinPoint.executeQuery();
													
													boolean AfterStoCkErr = true;
													rsetStockSearchPinPoint.beforeFirst();
													while (rsetStockSearchPinPoint.next()) {
														if(0<=rsetStockSearchPinPoint.getInt("PossibleQty")) {AfterStoCkErr = false;}
													}
													if(AfterStoCkErr) {
														PlovisionTgt[i01][ColErrType]	= "StockMinusErr";
														PlovisionTgt[i01][ColErrMsg]	= "謎ですがマイナス在庫生成しました。引当中に在庫が動いた？";
													}
												}else {
													PlovisionTgt[i01][ColErrType]	= "PlovisionEntryErr";
													PlovisionTgt[i01][ColErrMsg]	= "謎ですが引当結果登録に失敗しました";
													//引当てしまった在庫戻す
													StockFg	= StockControl(StockControlCreate,(String)StockData[i02][ColStockLoc],(String)StockData[i02][ColStockItemCd],(String)StockData[i02][ColStockLot],(String)StockData[i02][ColStockExpdate],(String)StockData[i02][ColStockActualDate],-1*ShipQty,now_dtm,UpdateUser);
												}
											}else {
												PlovisionTgt[i01][ColErrType]	= "StockControlErr";
												PlovisionTgt[i01][ColErrMsg]	= "謎ですが在庫更新失敗しました。引当中に在庫が動いた？";
											}
											Seq = Seq+1;
											if(0==NeedQty) {
												i02=StockData.length+1;
											}
										}
									}
								}
							}
							if(0<NeedQty) {
								//在庫引き当て全部できなかった時エラー
								PlovisionTgt[i01][ColErrType]	= "PlovisionErr";
								PlovisionTgt[i01][ColErrMsg]	= "謎ですが引当残発生。引当中に在庫が動いた？";
							}
						}
					}
				}
				
				//ロット・賞味期限指定のある出荷指示ありかつ単一ロットまとめ不要の引当
				for(int i01=0;i01<PlovisionTgt.length;i01++) {
					if(ItemList.get(i).equals((String)PlovisionTgt[i01][ColItemCd])&&0==(int)PlovisionTgt[i01][ColPlovisionCheck]) {
						if("".equals((String)PlovisionTgt[i01][ColFixLot])&&"".equals((String)PlovisionTgt[i01][ColFixExpDate])) {
							if(!"".equals((String)PlovisionTgt[i01][ColLot])||!"".equals((String)PlovisionTgt[i01][ColExpDate])) {
								PlovisionTgt[i01][ColPlovisionCheck] = 1;
								int Seq = 0;
								int NeedQty = (int)PlovisionTgt[i01][ColNeedQty];
								for(int i02=0;i02<StockData.length;i02++) {
									if(("".equals((String)PlovisionTgt[i01][ColLot])||((String)StockData[i02][ColStockLot]).equals((String)PlovisionTgt[i01][ColLot]))
										&&("".equals((String)PlovisionTgt[i01][ColExpDate])||((String)StockData[i02][ColStockExpdate]).equals((String)PlovisionTgt[i01][ColExpDate]))	
											&& 0<(int)StockData[i02][ColAfterStockPossibleQty]) {
										int UnitQty = 1;
										switch((int)PlovisionTgt[i01][ColPackingType]) {
											case 0:
												UnitQty = (int)1;
												break;
											case 1:
												UnitQty = (int)StockData[i02][ColStockCtUnitQty];
												break;
											case 2:
												UnitQty = (int)StockData[i02][ColStockCsUnitQty];
												break;
											case 3:
												UnitQty = (int)StockData[i02][ColStockPlUnitQty];
												break;
											default:
												break;
										}
										if(0>=UnitQty) {UnitQty=1;}
										int WorkPossibleQty = (int)((int)StockData[i02][ColAfterStockPossibleQty]/UnitQty);
										WorkPossibleQty = WorkPossibleQty*UnitQty;
										int ShipQty = 0;
										if(WorkPossibleQty>=NeedQty) {
											ShipQty = NeedQty;
											NeedQty = 0;
										}else {
											ShipQty = WorkPossibleQty;
											NeedQty = NeedQty-WorkPossibleQty;
										}
										StockData[i02][ColAfterStockPossibleQty] = (int)StockData[i02][ColAfterStockPossibleQty]-ShipQty;
										//在庫更新
										boolean StockFg	= StockControl(StockControlCreate,(String)StockData[i02][ColStockLoc],(String)StockData[i02][ColStockItemCd],(String)StockData[i02][ColStockLot],(String)StockData[i02][ColStockExpdate],(String)StockData[i02][ColStockActualDate],ShipQty,now_dtm,UpdateUser);
										//引当結果登録
										if(StockFg) {
											boolean PlovisionFg	= CreateShipPlovisionKick(CreateShipPlovision
													,TgtWhCd,TgtClCd,ShipQty,Seq,now_dtm,UpdateUser
													,PlovisionTgt[i01]
													,StockData[i02]
													);
											if(PlovisionFg) {
												OkuriHdUpdate.setString(ColOkuriHdUpdateDate	,now_dtm);
												OkuriHdUpdate.setString(ColOkuriHdUpdateUser 	,UpdateUser);
												OkuriHdUpdate.setString(ColOkuriHdOkuriNo 		,(String)PlovisionTgt[i01][ColOkuriNo]);
												OkuriHdUpdate.executeUpdate();
												
												//更新後在庫確認　マイナス在庫発生していればエラー
												StockSearchPinPoint.setString(ColStockPinPointLoc        ,(String)StockData[i02][ColStockLoc]);					//ロケーション
												StockSearchPinPoint.setString(ColStockPinPointItemCd     ,(String)StockData[i02][ColStockItemCd]);				//商品コード
												StockSearchPinPoint.setString(ColStockPinPointLot        ,(String)StockData[i02][ColStockLot]);					//ロット
												StockSearchPinPoint.setString(ColStockPinPointExpdate    ,(String)StockData[i02][ColStockExpdate]);				//消費期限
												StockSearchPinPoint.setString(ColStockPinPointActualDate ,(String)StockData[i02][ColStockActualDate]);			//入荷実績日
												
												rsetStockSearchPinPoint	= StockSearchPinPoint.executeQuery();
												
												boolean AfterStoCkErr = true;
												rsetStockSearchPinPoint.beforeFirst();
												while (rsetStockSearchPinPoint.next()) {
													if(0<=rsetStockSearchPinPoint.getInt("PossibleQty")) {AfterStoCkErr = false;}
												}
												if(AfterStoCkErr) {
													PlovisionTgt[i01][ColErrType]	= "StockMinusErr";
													PlovisionTgt[i01][ColErrMsg]	= "謎ですがマイナス在庫生成しました。引当中に在庫が動いた？";
												}
											}else {
												PlovisionTgt[i01][ColErrType]	= "PlovisionEntryErr";
												PlovisionTgt[i01][ColErrMsg]	= "謎ですが引当結果登録に失敗しました";
												//引当てしまった在庫戻す
												StockFg	= StockControl(StockControlCreate,(String)StockData[i02][ColStockLoc],(String)StockData[i02][ColStockItemCd],(String)StockData[i02][ColStockLot],(String)StockData[i02][ColStockExpdate],(String)StockData[i02][ColStockActualDate],-1*ShipQty,now_dtm,UpdateUser);
											}
										}else {
											PlovisionTgt[i01][ColErrType]	= "StockControlErr";
											PlovisionTgt[i01][ColErrMsg]	= "謎ですが在庫更新失敗しました。引当中に在庫が動いた？";
										}
										Seq = Seq+1;
										if(0==NeedQty) {
											i02=StockData.length+1;
										}
									}
								}
								if(0<NeedQty) {
									//在庫が散りまくった結果引当処理がうまくできていないため諦めてバラ扱いで引当　後で頑張って荷姿整えたまえ
									for(int i02=0;i02<StockData.length;i02++) {
										if(("".equals((String)PlovisionTgt[i01][ColLot])||((String)StockData[i02][ColStockLot]).equals((String)PlovisionTgt[i01][ColLot]))
											&&("".equals((String)PlovisionTgt[i01][ColExpDate])||((String)StockData[i02][ColStockExpdate]).equals((String)PlovisionTgt[i01][ColExpDate]))	
												&& 0<(int)StockData[i02][ColAfterStockPossibleQty]) {
											int UnitQty = 1;
											int WorkPossibleQty = (int)((int)StockData[i02][ColAfterStockPossibleQty]/UnitQty);
											WorkPossibleQty = WorkPossibleQty*UnitQty;
											int ShipQty = 0;
											if(WorkPossibleQty>=NeedQty) {
												ShipQty = NeedQty;
												NeedQty = 0;
											}else {
												ShipQty = WorkPossibleQty;
												NeedQty = NeedQty-WorkPossibleQty;
											}
											StockData[i02][ColAfterStockPossibleQty] = (int)StockData[i02][ColAfterStockPossibleQty]-ShipQty;
											//在庫更新
											boolean StockFg	= StockControl(StockControlCreate,(String)StockData[i02][ColStockLoc],(String)StockData[i02][ColStockItemCd],(String)StockData[i02][ColStockLot],(String)StockData[i02][ColStockExpdate],(String)StockData[i02][ColStockActualDate],ShipQty,now_dtm,UpdateUser);
											//引当結果登録
											if(StockFg) {
												boolean PlovisionFg	= CreateShipPlovisionKick(CreateShipPlovision
														,TgtWhCd,TgtClCd,ShipQty,Seq,now_dtm,UpdateUser
														,PlovisionTgt[i01]
														,StockData[i02]
														);
												if(PlovisionFg) {
													OkuriHdUpdate.setString(ColOkuriHdUpdateDate	,now_dtm);
													OkuriHdUpdate.setString(ColOkuriHdUpdateUser 	,UpdateUser);
													OkuriHdUpdate.setString(ColOkuriHdOkuriNo 		,(String)PlovisionTgt[i01][ColOkuriNo]);
													OkuriHdUpdate.executeUpdate();
													
													//更新後在庫確認　マイナス在庫発生していればエラー
													StockSearchPinPoint.setString(ColStockPinPointLoc        ,(String)StockData[i02][ColStockLoc]);					//ロケーション
													StockSearchPinPoint.setString(ColStockPinPointItemCd     ,(String)StockData[i02][ColStockItemCd]);				//商品コード
													StockSearchPinPoint.setString(ColStockPinPointLot        ,(String)StockData[i02][ColStockLot]);					//ロット
													StockSearchPinPoint.setString(ColStockPinPointExpdate    ,(String)StockData[i02][ColStockExpdate]);				//消費期限
													StockSearchPinPoint.setString(ColStockPinPointActualDate ,(String)StockData[i02][ColStockActualDate]);			//入荷実績日
													
													rsetStockSearchPinPoint	= StockSearchPinPoint.executeQuery();
													
													boolean AfterStoCkErr = true;
													rsetStockSearchPinPoint.beforeFirst();
													while (rsetStockSearchPinPoint.next()) {
														if(0<=rsetStockSearchPinPoint.getInt("PossibleQty")) {AfterStoCkErr = false;}
													}
													if(AfterStoCkErr) {
														PlovisionTgt[i01][ColErrType]	= "StockMinusErr";
														PlovisionTgt[i01][ColErrMsg]	= "謎ですがマイナス在庫生成しました。引当中に在庫が動いた？";
													}
												}else {
													PlovisionTgt[i01][ColErrType]	= "PlovisionEntryErr";
													PlovisionTgt[i01][ColErrMsg]	= "謎ですが引当結果登録に失敗しました";
													//引当てしまった在庫戻す
													StockFg	= StockControl(StockControlCreate,(String)StockData[i02][ColStockLoc],(String)StockData[i02][ColStockItemCd],(String)StockData[i02][ColStockLot],(String)StockData[i02][ColStockExpdate],(String)StockData[i02][ColStockActualDate],-1*ShipQty,now_dtm,UpdateUser);
												}
											}else {
												PlovisionTgt[i01][ColErrType]	= "StockControlErr";
												PlovisionTgt[i01][ColErrMsg]	= "謎ですが在庫更新失敗しました。引当中に在庫が動いた？";
											}
											Seq = Seq+1;
											if(0==NeedQty) {
												i02=StockData.length+1;
											}
										}
									}
								}
								if(0<NeedQty) {
									//在庫引き当て全部できなかった時エラー
									PlovisionTgt[i01][ColErrType]	= "PlovisionErr";
									PlovisionTgt[i01][ColErrMsg]	= "謎ですが引当残発生。引当中に在庫が動いた？";
								}
							}
						}
					}
				}
				
				//ロット指定はないが日付範囲指定がある。但しロットをまとめる必要はない在庫引当
				for(int i01=0;i01<PlovisionTgt.length;i01++) {
					if(ItemList.get(i).equals((String)PlovisionTgt[i01][ColItemCd])&&0==(int)PlovisionTgt[i01][ColPlovisionCheck]) {
						if("".equals((String)PlovisionTgt[i01][ColFixLot])&&"".equals((String)PlovisionTgt[i01][ColFixExpDate])) {
							if("".equals((String)PlovisionTgt[i01][ColLot])&&"".equals((String)PlovisionTgt[i01][ColExpDate])) {
								if(!"".equals((String)PlovisionTgt[i01][ColCheckExpDateMin])||!"".equals((String)PlovisionTgt[i01][ColCheckExpDateMax])) {
									PlovisionTgt[i01][ColPlovisionCheck] = 1;
									int Seq = 0;
									int NeedQty = (int)PlovisionTgt[i01][ColNeedQty];
									for(int i02=0;i02<StockData.length;i02++) {
										if(0<(int)StockData[i02][ColAfterStockPossibleQty]) {
											Timestamp StockExpDate = null;
											Timestamp ExpDateMin = null;
											Timestamp ExpDateMax = null;
											
											if(!"".equals((String)StockData[i02][ColStockExpdate])) {
												StockExpDate = B100_DateTimeControl.dtmTimestamp2((String)StockData[i02][ColStockExpdate])[0];
											}
											if(!"".equals((String)PlovisionTgt[i01][ColCheckExpDateMin])) {
												ExpDateMin = B100_DateTimeControl.dtmTimestamp2((String)PlovisionTgt[i01][ColCheckExpDateMin])[0];
											}
											if(!"".equals((String)PlovisionTgt[i01][ColCheckExpDateMax])) {
												ExpDateMax = B100_DateTimeControl.dtmTimestamp2((String)PlovisionTgt[i01][ColCheckExpDateMax])[0];
											}
											if((null==ExpDateMin||0<=B100_DateTimeControl.ddif(ExpDateMin,StockExpDate))
													&& (null==ExpDateMax||0<=B100_DateTimeControl.ddif(StockExpDate,ExpDateMax))
													) {
												int UnitQty = 1;
												switch((int)PlovisionTgt[i01][ColPackingType]) {
													case 0:
														UnitQty = (int)1;
														break;
													case 1:
														UnitQty = (int)StockData[i02][ColStockCtUnitQty];
														break;
													case 2:
														UnitQty = (int)StockData[i02][ColStockCsUnitQty];
														break;
													case 3:
														UnitQty = (int)StockData[i02][ColStockPlUnitQty];
														break;
													default:
														break;
												}
												if(0>=UnitQty) {UnitQty=1;}
												int WorkPossibleQty = (int)((int)StockData[i02][ColAfterStockPossibleQty]/UnitQty);
												WorkPossibleQty = WorkPossibleQty*UnitQty;
												int ShipQty = 0;
												if(WorkPossibleQty>=NeedQty) {
													ShipQty = NeedQty;
													NeedQty = 0;
												}else {
													ShipQty = WorkPossibleQty;
													NeedQty = NeedQty-WorkPossibleQty;
												}
												StockData[i02][ColAfterStockPossibleQty] = (int)StockData[i02][ColAfterStockPossibleQty]-ShipQty;
												//在庫更新
												boolean StockFg	= StockControl(StockControlCreate,(String)StockData[i02][ColStockLoc],(String)StockData[i02][ColStockItemCd],(String)StockData[i02][ColStockLot],(String)StockData[i02][ColStockExpdate],(String)StockData[i02][ColStockActualDate],ShipQty,now_dtm,UpdateUser);
												//引当結果登録
												if(StockFg) {
													boolean PlovisionFg	= CreateShipPlovisionKick(CreateShipPlovision
															,TgtWhCd,TgtClCd,ShipQty,Seq,now_dtm,UpdateUser
															,PlovisionTgt[i01]
															,StockData[i02]
															);
													if(PlovisionFg) {
														OkuriHdUpdate.setString(ColOkuriHdUpdateDate	,now_dtm);
														OkuriHdUpdate.setString(ColOkuriHdUpdateUser 	,UpdateUser);
														OkuriHdUpdate.setString(ColOkuriHdOkuriNo 		,(String)PlovisionTgt[i01][ColOkuriNo]);
														OkuriHdUpdate.executeUpdate();
														
														//更新後在庫確認　マイナス在庫発生していればエラー
														StockSearchPinPoint.setString(ColStockPinPointLoc        ,(String)StockData[i02][ColStockLoc]);					//ロケーション
														StockSearchPinPoint.setString(ColStockPinPointItemCd     ,(String)StockData[i02][ColStockItemCd]);				//商品コード
														StockSearchPinPoint.setString(ColStockPinPointLot        ,(String)StockData[i02][ColStockLot]);					//ロット
														StockSearchPinPoint.setString(ColStockPinPointExpdate    ,(String)StockData[i02][ColStockExpdate]);				//消費期限
														StockSearchPinPoint.setString(ColStockPinPointActualDate ,(String)StockData[i02][ColStockActualDate]);			//入荷実績日
														
														rsetStockSearchPinPoint	= StockSearchPinPoint.executeQuery();
														
														boolean AfterStoCkErr = true;
														rsetStockSearchPinPoint.beforeFirst();
														while (rsetStockSearchPinPoint.next()) {
															if(0<=rsetStockSearchPinPoint.getInt("PossibleQty")) {AfterStoCkErr = false;}
														}
														if(AfterStoCkErr) {
															PlovisionTgt[i01][ColErrType]	= "StockMinusErr";
															PlovisionTgt[i01][ColErrMsg]	= "謎ですがマイナス在庫生成しました。引当中に在庫が動いた？";
														}
													}else {
														PlovisionTgt[i01][ColErrType]	= "PlovisionEntryErr";
														PlovisionTgt[i01][ColErrMsg]	= "謎ですが引当結果登録に失敗しました";
														//引当てしまった在庫戻す
														StockFg	= StockControl(StockControlCreate,(String)StockData[i02][ColStockLoc],(String)StockData[i02][ColStockItemCd],(String)StockData[i02][ColStockLot],(String)StockData[i02][ColStockExpdate],(String)StockData[i02][ColStockActualDate],-1*ShipQty,now_dtm,UpdateUser);
													}
												}else {
													PlovisionTgt[i01][ColErrType]	= "StockControlErr";
													PlovisionTgt[i01][ColErrMsg]	= "謎ですが在庫更新失敗しました。引当中に在庫が動いた？";
												}
												Seq = Seq+1;
												if(0==NeedQty) {
													i02=StockData.length+1;
												}
											}
										}
									}
									if(0<NeedQty) {
										//在庫が散りまくった結果引当処理がうまくできていないため諦めてバラ扱いで引当　後で頑張って荷姿整えたまえ
										for(int i02=0;i02<StockData.length;i02++) {
											if(0<(int)StockData[i02][ColAfterStockPossibleQty]) {
												Timestamp StockExpDate = null;
												Timestamp ExpDateMin = null;
												Timestamp ExpDateMax = null;
												
												if(!"".equals((String)StockData[i02][ColStockExpdate])) {
													StockExpDate = B100_DateTimeControl.dtmTimestamp2((String)StockData[i02][ColStockExpdate])[0];
												}
												if(!"".equals((String)PlovisionTgt[i01][ColCheckExpDateMin])) {
													ExpDateMin = B100_DateTimeControl.dtmTimestamp2((String)PlovisionTgt[i01][ColCheckExpDateMin])[0];
												}
												if(!"".equals((String)PlovisionTgt[i01][ColCheckExpDateMax])) {
													ExpDateMax = B100_DateTimeControl.dtmTimestamp2((String)PlovisionTgt[i01][ColCheckExpDateMax])[0];
												}
												if((null==ExpDateMin||0<=B100_DateTimeControl.ddif(ExpDateMin,StockExpDate))
														&& (null==ExpDateMax||0<=B100_DateTimeControl.ddif(StockExpDate,ExpDateMax))
														) {
													int UnitQty = 1;
													int WorkPossibleQty = (int)((int)StockData[i02][ColAfterStockPossibleQty]/UnitQty);
													WorkPossibleQty = WorkPossibleQty*UnitQty;
													int ShipQty = 0;
													if(WorkPossibleQty>=NeedQty) {
														ShipQty = NeedQty;
														NeedQty = 0;
													}else {
														ShipQty = WorkPossibleQty;
														NeedQty = NeedQty-WorkPossibleQty;
													}
													StockData[i02][ColAfterStockPossibleQty] = (int)StockData[i02][ColAfterStockPossibleQty]-ShipQty;
													//在庫更新
													boolean StockFg	= StockControl(StockControlCreate,(String)StockData[i02][ColStockLoc],(String)StockData[i02][ColStockItemCd],(String)StockData[i02][ColStockLot],(String)StockData[i02][ColStockExpdate],(String)StockData[i02][ColStockActualDate],ShipQty,now_dtm,UpdateUser);
													//引当結果登録
													if(StockFg) {
														boolean PlovisionFg	= CreateShipPlovisionKick(CreateShipPlovision
																,TgtWhCd,TgtClCd,ShipQty,Seq,now_dtm,UpdateUser
																,PlovisionTgt[i01]
																,StockData[i02]
																);
														if(PlovisionFg) {
															OkuriHdUpdate.setString(ColOkuriHdUpdateDate	,now_dtm);
															OkuriHdUpdate.setString(ColOkuriHdUpdateUser 	,UpdateUser);
															OkuriHdUpdate.setString(ColOkuriHdOkuriNo 		,(String)PlovisionTgt[i01][ColOkuriNo]);
															OkuriHdUpdate.executeUpdate();
															
															//更新後在庫確認　マイナス在庫発生していればエラー
															StockSearchPinPoint.setString(ColStockPinPointLoc        ,(String)StockData[i02][ColStockLoc]);					//ロケーション
															StockSearchPinPoint.setString(ColStockPinPointItemCd     ,(String)StockData[i02][ColStockItemCd]);				//商品コード
															StockSearchPinPoint.setString(ColStockPinPointLot        ,(String)StockData[i02][ColStockLot]);					//ロット
															StockSearchPinPoint.setString(ColStockPinPointExpdate    ,(String)StockData[i02][ColStockExpdate]);				//消費期限
															StockSearchPinPoint.setString(ColStockPinPointActualDate ,(String)StockData[i02][ColStockActualDate]);			//入荷実績日
															
															rsetStockSearchPinPoint	= StockSearchPinPoint.executeQuery();
															
															boolean AfterStoCkErr = true;
															rsetStockSearchPinPoint.beforeFirst();
															while (rsetStockSearchPinPoint.next()) {
																if(0<=rsetStockSearchPinPoint.getInt("PossibleQty")) {AfterStoCkErr = false;}
															}
															if(AfterStoCkErr) {
																PlovisionTgt[i01][ColErrType]	= "StockMinusErr";
																PlovisionTgt[i01][ColErrMsg]	= "謎ですがマイナス在庫生成しました。引当中に在庫が動いた？";
															}
														}else {
															PlovisionTgt[i01][ColErrType]	= "PlovisionEntryErr";
															PlovisionTgt[i01][ColErrMsg]	= "謎ですが引当結果登録に失敗しました";
															//引当てしまった在庫戻す
															StockFg	= StockControl(StockControlCreate,(String)StockData[i02][ColStockLoc],(String)StockData[i02][ColStockItemCd],(String)StockData[i02][ColStockLot],(String)StockData[i02][ColStockExpdate],(String)StockData[i02][ColStockActualDate],-1*ShipQty,now_dtm,UpdateUser);
														}
													}else {
														PlovisionTgt[i01][ColErrType]	= "StockControlErr";
														PlovisionTgt[i01][ColErrMsg]	= "謎ですが在庫更新失敗しました。引当中に在庫が動いた？";
													}
													Seq = Seq+1;
													if(0==NeedQty) {
														i02=StockData.length+1;
													}
												}
											}
										}
									}
									if(0<NeedQty) {
										//在庫引き当て全部できなかった時エラー
										PlovisionTgt[i01][ColErrType]	= "PlovisionErr";
										PlovisionTgt[i01][ColErrMsg]	= "謎ですが引当残発生。引当中に在庫が動いた？";
									}
								}
							}
						}
					}
				}
				//ロット指定なし・出荷日付範囲気にしない全くわがままを言わない出荷指示の在庫引当
				for(int i01=0;i01<PlovisionTgt.length;i01++) {
					if(ItemList.get(i).equals((String)PlovisionTgt[i01][ColItemCd])&&0==(int)PlovisionTgt[i01][ColPlovisionCheck]) {
						if("".equals((String)PlovisionTgt[i01][ColFixLot])&&"".equals((String)PlovisionTgt[i01][ColFixExpDate])) {
							if("".equals((String)PlovisionTgt[i01][ColLot])&&"".equals((String)PlovisionTgt[i01][ColExpDate])) {
								if("".equals((String)PlovisionTgt[i01][ColCheckExpDateMin])&&"".equals((String)PlovisionTgt[i01][ColCheckExpDateMax])) {
									PlovisionTgt[i01][ColPlovisionCheck] = 1;
									int Seq = 0;
									int NeedQty = (int)PlovisionTgt[i01][ColNeedQty];
									for(int i02=0;i02<StockData.length;i02++) {
										if(0<(int)StockData[i02][ColAfterStockPossibleQty]) {
											int UnitQty = 1;
											switch((int)PlovisionTgt[i01][ColPackingType]) {
												case 0:
													UnitQty = (int)1;
													break;
												case 1:
													UnitQty = (int)StockData[i02][ColStockCtUnitQty];
													break;
												case 2:
													UnitQty = (int)StockData[i02][ColStockCsUnitQty];
													break;
												case 3:
													UnitQty = (int)StockData[i02][ColStockPlUnitQty];
													break;
												default:
													break;
											}
											if(0>=UnitQty) {UnitQty=1;}
											int WorkPossibleQty = (int)((int)StockData[i02][ColAfterStockPossibleQty]/UnitQty);
											WorkPossibleQty = WorkPossibleQty*UnitQty;
											int ShipQty = 0;
											if(WorkPossibleQty>=NeedQty) {
												ShipQty = NeedQty;
												NeedQty = 0;
											}else {
												ShipQty = WorkPossibleQty;
												NeedQty = NeedQty-WorkPossibleQty;
											}
											StockData[i02][ColAfterStockPossibleQty] = (int)StockData[i02][ColAfterStockPossibleQty]-ShipQty;
											//在庫更新
											boolean StockFg	= StockControl(StockControlCreate,(String)StockData[i02][ColStockLoc],(String)StockData[i02][ColStockItemCd],(String)StockData[i02][ColStockLot],(String)StockData[i02][ColStockExpdate],(String)StockData[i02][ColStockActualDate],ShipQty,now_dtm,UpdateUser);
											//引当結果登録
											if(StockFg) {
												boolean PlovisionFg	= CreateShipPlovisionKick(CreateShipPlovision
														,TgtWhCd,TgtClCd,ShipQty,Seq,now_dtm,UpdateUser
														,PlovisionTgt[i01]
														,StockData[i02]
														);
												if(PlovisionFg) {
													OkuriHdUpdate.setString(ColOkuriHdUpdateDate	,now_dtm);
													OkuriHdUpdate.setString(ColOkuriHdUpdateUser 	,UpdateUser);
													OkuriHdUpdate.setString(ColOkuriHdOkuriNo 		,(String)PlovisionTgt[i01][ColOkuriNo]);
													OkuriHdUpdate.executeUpdate();
													
													//更新後在庫確認　マイナス在庫発生していればエラー
													StockSearchPinPoint.setString(ColStockPinPointLoc        ,(String)StockData[i02][ColStockLoc]);					//ロケーション
													StockSearchPinPoint.setString(ColStockPinPointItemCd     ,(String)StockData[i02][ColStockItemCd]);				//商品コード
													StockSearchPinPoint.setString(ColStockPinPointLot        ,(String)StockData[i02][ColStockLot]);					//ロット
													StockSearchPinPoint.setString(ColStockPinPointExpdate    ,(String)StockData[i02][ColStockExpdate]);				//消費期限
													StockSearchPinPoint.setString(ColStockPinPointActualDate ,(String)StockData[i02][ColStockActualDate]);			//入荷実績日
													
													rsetStockSearchPinPoint	= StockSearchPinPoint.executeQuery();
													
													boolean AfterStoCkErr = true;
													rsetStockSearchPinPoint.beforeFirst();
													while (rsetStockSearchPinPoint.next()) {
														if(0<=rsetStockSearchPinPoint.getInt("PossibleQty")) {AfterStoCkErr = false;}
													}
													if(AfterStoCkErr) {
														PlovisionTgt[i01][ColErrType]	= "StockMinusErr";
														PlovisionTgt[i01][ColErrMsg]	= "謎ですがマイナス在庫生成しました。引当中に在庫が動いた？";
													}
												}else {
													PlovisionTgt[i01][ColErrType]	= "PlovisionEntryErr";
													PlovisionTgt[i01][ColErrMsg]	= "謎ですが引当結果登録に失敗しました";
													//引当てしまった在庫戻す
													StockFg	= StockControl(StockControlCreate,(String)StockData[i02][ColStockLoc],(String)StockData[i02][ColStockItemCd],(String)StockData[i02][ColStockLot],(String)StockData[i02][ColStockExpdate],(String)StockData[i02][ColStockActualDate],-1*ShipQty,now_dtm,UpdateUser);
												}
											}else {
												PlovisionTgt[i01][ColErrType]	= "StockControlErr";
												PlovisionTgt[i01][ColErrMsg]	= "謎ですが在庫更新失敗しました。引当中に在庫が動いた？";
											}
											Seq = Seq+1;
											if(0==NeedQty) {
												i02=StockData.length+1;
											}
										}
									}
									if(0<NeedQty) {
										//在庫が散りまくった結果引当処理がうまくできていないため諦めてバラ扱いで引当　後で頑張って荷姿整えたまえ
										for(int i02=0;i02<StockData.length;i02++) {
											if(0<(int)StockData[i02][ColAfterStockPossibleQty]) {
												int UnitQty = 1;
												int WorkPossibleQty = (int)((int)StockData[i02][ColAfterStockPossibleQty]/UnitQty);
												WorkPossibleQty = WorkPossibleQty*UnitQty;
												int ShipQty = 0;
												if(WorkPossibleQty>=NeedQty) {
													ShipQty = NeedQty;
													NeedQty = 0;
												}else {
													ShipQty = WorkPossibleQty;
													NeedQty = NeedQty-WorkPossibleQty;
												}
												StockData[i02][ColAfterStockPossibleQty] = (int)StockData[i02][ColAfterStockPossibleQty]-ShipQty;
												//在庫更新
												boolean StockFg	= StockControl(StockControlCreate,(String)StockData[i02][ColStockLoc],(String)StockData[i02][ColStockItemCd],(String)StockData[i02][ColStockLot],(String)StockData[i02][ColStockExpdate],(String)StockData[i02][ColStockActualDate],ShipQty,now_dtm,UpdateUser);
												//引当結果登録
												if(StockFg) {
													boolean PlovisionFg	= CreateShipPlovisionKick(CreateShipPlovision
															,TgtWhCd,TgtClCd,ShipQty,Seq,now_dtm,UpdateUser
															,PlovisionTgt[i01]
															,StockData[i02]
															);
													if(PlovisionFg) {
														OkuriHdUpdate.setString(ColOkuriHdUpdateDate	,now_dtm);
														OkuriHdUpdate.setString(ColOkuriHdUpdateUser 	,UpdateUser);
														OkuriHdUpdate.setString(ColOkuriHdOkuriNo 		,(String)PlovisionTgt[i01][ColOkuriNo]);
														OkuriHdUpdate.executeUpdate();
														
														//更新後在庫確認　マイナス在庫発生していればエラー
														StockSearchPinPoint.setString(ColStockPinPointLoc        ,(String)StockData[i02][ColStockLoc]);					//ロケーション
														StockSearchPinPoint.setString(ColStockPinPointItemCd     ,(String)StockData[i02][ColStockItemCd]);				//商品コード
														StockSearchPinPoint.setString(ColStockPinPointLot        ,(String)StockData[i02][ColStockLot]);					//ロット
														StockSearchPinPoint.setString(ColStockPinPointExpdate    ,(String)StockData[i02][ColStockExpdate]);				//消費期限
														StockSearchPinPoint.setString(ColStockPinPointActualDate ,(String)StockData[i02][ColStockActualDate]);			//入荷実績日
														
														rsetStockSearchPinPoint	= StockSearchPinPoint.executeQuery();
														
														boolean AfterStoCkErr = true;
														rsetStockSearchPinPoint.beforeFirst();
														while (rsetStockSearchPinPoint.next()) {
															if(0<=rsetStockSearchPinPoint.getInt("PossibleQty")) {AfterStoCkErr = false;}
														}
														if(AfterStoCkErr) {
															PlovisionTgt[i01][ColErrType]	= "StockMinusErr";
															PlovisionTgt[i01][ColErrMsg]	= "謎ですがマイナス在庫生成しました。引当中に在庫が動いた？";
														}
													}else {
														PlovisionTgt[i01][ColErrType]	= "PlovisionEntryErr";
														PlovisionTgt[i01][ColErrMsg]	= "謎ですが引当結果登録に失敗しました";
														//引当てしまった在庫戻す
														StockFg	= StockControl(StockControlCreate,(String)StockData[i02][ColStockLoc],(String)StockData[i02][ColStockItemCd],(String)StockData[i02][ColStockLot],(String)StockData[i02][ColStockExpdate],(String)StockData[i02][ColStockActualDate],-1*ShipQty,now_dtm,UpdateUser);
													}
												}else {
													PlovisionTgt[i01][ColErrType]	= "StockControlErr";
													PlovisionTgt[i01][ColErrMsg]	= "謎ですが在庫更新失敗しました。引当中に在庫が動いた？";
												}
												Seq = Seq+1;
												if(0==NeedQty) {
													i02=StockData.length+1;
												}
											}
										}
									}
									if(0<NeedQty) {
										//在庫引き当て全部できなかった時エラー
										PlovisionTgt[i01][ColErrType]	= "PlovisionErr";
										PlovisionTgt[i01][ColErrMsg]	= "謎ですが引当残発生。引当中に在庫が動いた？";
									}
								}
							}
						}
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		try {
			if(null!=rsetStockGet) {rsetStockGet.close();}
			if(null!=StockGet) {StockGet.close();}
			if(null!=StockSearchPinPoint) {StockSearchPinPoint.close();}
			if(null!=rsetStockSearchPinPoint) {rsetStockSearchPinPoint.close();}
			if(null!=StockControlCreate) {StockControlCreate.close();}
			if(null!=CreateShipPlovision) {CreateShipPlovision.close();}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		A100_DbConnect.close();
		
		return PlovisionTgt;
	}
	
	private static boolean StockControl(PreparedStatement StockControlCreate,String TgtLoc,String TgtItemCd,String TgtLot,String TgtExpdate,String TgtActualDate,int Qty,String now_dtm,String UpdateUser) {
		//在庫更新実施
		boolean Rt = true;
		
		int SetColShipPlanQty	= 1;
		int SetColPossibleQty	= 2;
		int SetColUpdateDate	= 3;
		int SetColUpdateUser	= 4;
		int SetColLoc			= 5;
		int SetColItemCd		= 6;
		int SetColLot			= 7;
		int SetColExpdate		= 8;
		int SetColActualDate	= 9;
		
		try {
			StockControlCreate.setString(SetColShipPlanQty	,""+Qty);
			StockControlCreate.setString(SetColPossibleQty	,""+Qty);
			StockControlCreate.setString(SetColUpdateDate	,""+now_dtm);
			StockControlCreate.setString(SetColUpdateUser	,""+UpdateUser);
			StockControlCreate.setString(SetColLoc			,""+TgtLoc);
			StockControlCreate.setString(SetColItemCd		,""+TgtItemCd);
			StockControlCreate.setString(SetColLot			,""+TgtLot);
			StockControlCreate.setString(SetColExpdate		,""+TgtExpdate);
			StockControlCreate.setString(SetColActualDate	,""+TgtActualDate);
			StockControlCreate.executeUpdate();
		} catch (SQLException e) {
			Rt = false;
			e.printStackTrace();
		}
		
		return Rt;
	}
	
	private static PreparedStatement StockControlCreate(String TgtWhCd,String TgtClCd) {
		//在庫更新するSQL
		String sql = "Update "
				+ ""+A00000_Main.MySqlDefaultSchemaWANKO+".WW0015Stock"
				+ " set "
				+ "WW0015Stock.ShipPlanQty =WW0015Stock.ShipPlanQty+?,\n"			//引当済数
				+ "WW0015Stock.PossibleQty =WW0015Stock.PossibleQty-?,\n"			//出荷可能数
				+ "WW0015Stock.UpdateDate  = ?,\n"		//更新日時
				+ "WW0015Stock.UpdateUser  = ? \n"		//更新者
				+ " where 1=1\n"
				+ " and WW0015Stock.ClCd       = '" + TgtClCd +"' \n"	//荷主コード
				+ " and WW0015Stock.WhCd       = '" + TgtWhCd +"' \n"	//倉庫コード
				+ " and WW0015Stock.Loc        = ? \n"					//ロケーション
				+ " and WW0015Stock.ItemCd     = ? \n"					//商品コード
				+ " and WW0015Stock.Lot        = ? \n"					//ロット
				+ " and WW0015Stock.Expdate    = ? \n"					//消費期限
				+ " and WW0015Stock.ActualDate = ? \n"					//入荷実績日
				;
		
		A100_DbConnect.DB_CONN("WANKO");
		PreparedStatement stmt01 = null;
		try {
			A100_DbConnect.conn.setAutoCommit(true);
			stmt01 = A100_DbConnect.conn.prepareStatement(sql);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return stmt01;
	}
	
	private static PreparedStatement StockSearchPinPoint(String TgtWhCd,String TgtClCd) {
		//在庫ピンポイント検索するSQL
		String sql = "select "
				+"(WW0015Stock.ClCd) as ClCd,\n"						//荷主コード
				+"(WW0015Stock.WhCd) as WhCd,\n"						//倉庫コード
				+"(WW0015Stock.Loc) as Loc,\n"							//ロケーション
				+"(WW0015Stock.ItemCd) as ItemCd,\n"					//商品コード
				+"(WW0015Stock.Lot) as Lot,\n"							//ロット
				+"(WW0015Stock.Expdate) as Expdate,\n"					//消費期限
				+"(WW0015Stock.ActualDate) as ActualDate,\n"			//入荷実績日
				+"(WW0015Stock.Qty) as Qty,\n"							//数量
				+"(WW0015Stock.ShipPlanQty) as ShipPlanQty,\n"			//引当済数
				+"(WW0015Stock.PossibleQty) as PossibleQty \n"			//出荷可能数
				+" from "+A00000_Main.MySqlDefaultSchemaWANKO+".WW0015Stock"
				+" where 1=1 \n"
				+ " and WW0015Stock.ClCd       = '" + TgtClCd +"' \n"	//荷主コード
				+ " and WW0015Stock.WhCd       = '" + TgtWhCd +"' \n"	//倉庫コード
				+ " and WW0015Stock.Loc        = ? \n"					//ロケーション
				+ " and WW0015Stock.ItemCd     = ? \n"					//商品コード
				+ " and WW0015Stock.Lot        = ? \n"					//ロット
				+ " and WW0015Stock.Expdate    = ? \n"					//消費期限
				+ " and WW0015Stock.ActualDate = ? \n"					//入荷実績日
				;
		A100_DbConnect.DB_CONN("WANKO");
		PreparedStatement stmt01 = null;
		try {
			A100_DbConnect.conn.setAutoCommit(true);
			stmt01 = A100_DbConnect.conn.prepareStatement(sql);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return stmt01;
	}
	
	private static boolean CreateShipPlovisionKick(PreparedStatement CreateShipPlovision
			,String TgtWhCd,String TgtClCd,int ShipQty,int Seq,String now_dtm,String UpdateUser
			,Object[] TgtData
			,Object[] StockData
			) {
		int ColStockClCd		=   0;			//荷主コード
		int ColStockCLName01	=   1;			//荷主表記名
		int ColStockWhCd		=   2;			//倉庫コード
		int ColStockWHName		=   3;			//担当倉庫名
		int ColStockClGpCD		=   4;			//荷主グループCD
		int ColStockClGpName01	=   5;			//グループ名1
		int ColStockLoc			=   6;			//ロケーション
		int ColStockLocName		=   7;			//ロケーション名
		int ColStockLocType		=   8;			//ロケタイプ　0:通常　1:保管　8:入荷時　9:引当禁止
		int ColStockItemCd		=   9;			//商品コード
		int ColStockLot			=  10;			//ロット
		int ColStockExpdate		=  11;			//消費期限
		int ColStockActualDate	=  12;			//入荷実績日
		int ColStockQty			=  13;			//数量
		int ColStockShipPlanQty	=  14;			//引当済数
		int ColStockPossibleQty	=  15;			//出荷可能数
		int ColStockItemName	=  16;			//商品名
		int ColStockItemName01	=  17;			//商品表記名
		int ColStockItemName02	=  18;			//商品正式名
		int ColStockItemName03	=  19;			//商品略名
		int ColStockClItemCd	=  20;			//荷主商品コード
		int ColStockJanCd		=  21;			//ソースマーク_BCD（バラ）
		int ColStockItemMdNo	=  22;			//商品型番
		int ColStockCtUnitQty	=  23;			//カートン入数
		int ColStockCsUnitQty	=  24;			//ケース入数
		int ColStockPlUnitQty	=  25;			//パレット入数
		int ColStockUnitName	=  26;			//商品単位
		int ColStockCtUnitName	=  27;			//カートン商品単位
		int ColStockCsUnitName	=  28;			//ケース商品単位
		int ColStockPlUnitName	=  29;			//パレット商品単位
		int ColStockEntryDate	=  30;			//登録日時
		int ColStockUpdateDate	=  31;			//更新日時
		int ColStockEntryUser	=  32;			//登録者
		int ColStockUpdateUser	=  33;			//更新者
		int ColAfterStockPossibleQty	=  34;			//引当後出荷可能数
		
		
		int CtUnitQty	= (int)StockData[ColStockCtUnitQty];			//カートン入数
		int CsUnitQty	= (int)StockData[ColStockCsUnitQty];			//ケース入数
		int PlUnitQty	= (int)StockData[ColStockPlUnitQty];			//パレット入数
		
		String SetOkuriNo			= (String)TgtData[ColOkuriNo];				//送り状番号(WMS採番)
		int SetMsNo					= (int)TgtData[ColMsNo];					//明細番号
		String SetOrderItemCd		= (String)TgtData[ColItemCd];				//商品コード
		String SetOrderItemName01	= (String)TgtData[ColOrderItemName01];	//品名01
		String SetOrderLot			= (String)TgtData[ColLot];					//受注ロット指定
		String SetOrderExpDate		= (String)TgtData[ColExpDate];				//受注賞味期限指定
		int SetOrderQty				= (int)TgtData[ColNeedQty];				//受注個数
		String SetShipLoc			= (String)StockData[ColStockLoc];			//ロケーション
		String SetShipItemCd		= (String)StockData[ColStockItemCd];		//商品コード
		String SetShipLot			= (String)StockData[ColStockLot];			//ロット
		String SetShipExpdate		= (String)StockData[ColStockExpdate];		//消費期限
		String SetShipActualDate	= (String)StockData[ColStockActualDate];	//入荷実績日
		int SetPackingType			= (int)TgtData[ColPackingType];			//荷姿タイプ
		int SetPackingQty			= (int)ShipQty;			//荷姿数量
		String SetUnitName			= (String)(String)StockData[ColStockUnitName];			//荷姿単位
		int SetPackingUnitQty		= (int)1;				//荷姿単位のバラ入数
		int SetBRShipQty			= (int)ShipQty;			//バラ数量
		int SetCTShipQty			= (int)0;				//カートン数量
		int SetCSShipQty			= (int)0;				//ケース数量
		int SetPLShipQty			= (int)0;				//パレット数量
		String SetBRUnitName		= (String)StockData[ColStockUnitName];		//バラ単位名
		String SetCTUnitName		= (String)StockData[ColStockCtUnitName];	//カートン単位名
		String SetCSUnitName		= (String)StockData[ColStockCsUnitName];	//ケース単位名
		String SetPLUnitName		= (String)StockData[ColStockPlUnitName];	//パレット単位名

		switch(SetPackingType) {
			case 1:
				if(0<CtUnitQty && 0==ShipQty%CtUnitQty) {
					SetPackingUnitQty		= CtUnitQty;				//荷姿単位のバラ入数
					SetPackingQty			= (int)ShipQty/CtUnitQty;	//荷姿数量
				}else {
					SetPackingType	= 0;
				}
				break;
			case 2:
				if(0<CsUnitQty && 0==ShipQty%CsUnitQty) {
					SetPackingUnitQty		= CsUnitQty;				//荷姿単位のバラ入数
					SetPackingQty			= (int)ShipQty/CsUnitQty;	//荷姿数量
				}else {
					SetPackingType	= 0;
				}
				break;
			case 3:
				if(0<PlUnitQty && 0==ShipQty%PlUnitQty) {
					SetPackingUnitQty		= PlUnitQty;				//荷姿単位のバラ入数
					SetPackingQty			= (int)ShipQty/PlUnitQty;	//荷姿数量
				}else {
					SetPackingType	= 0;
				}
				break;
			default:
				break;
		}
		
		if(0<PlUnitQty) {
			SetPLShipQty = (int)(SetBRShipQty/PlUnitQty);
			SetBRShipQty = (int)(SetBRShipQty%PlUnitQty);
		}
		if(0<CsUnitQty) {
			SetCSShipQty = (int)(SetBRShipQty/CsUnitQty);
			SetBRShipQty = (int)(SetBRShipQty%CsUnitQty);
		}
		if(0<CtUnitQty) {
			SetCTShipQty = (int)(SetBRShipQty/CtUnitQty);
			SetBRShipQty = (int)(SetBRShipQty%CtUnitQty);
		}
		
		boolean Rt = true;
		int SetColOkuriNo			=  1;	//送り状番号(WMS採番)
		int SetColMsNo				=  2;	//明細番号
		int SetColSeq				=  3;	//引当枝番
		int SetColOrderItemCd		=  4;	//商品コード
		int SetColOrderItemName01	=  5;	//品名01
		int SetColOrderLot			=  6;	//受注ロット指定
		int SetColOrderExpDate		=  7;	//受注賞味期限指定
		int SetColOrderQty			=  8;	//受注個数
		int SetColShipWhCd			=  9;	//倉庫コード
		int SetColShipLoc			= 10;	//ロケーション
		int SetColShipItemCd		= 11;	//商品コード
		int SetColShipLot			= 12;	//ロット
		int SetColShipExpdate		= 13;	//消費期限
		int SetColShipActualDate	= 14;	//入荷実績日
		int SetColShipQty			= 15;	//引当数量
		int SetColEntryDate			= 16;	//登録日時
		int SetColUpdateDate		= 17;	//更新日時
		int SetColEntryUser			= 18;	//登録者
		int SetColUpdateUser		= 19;	//更新者
		int SetColFixFg				= 20;	//引落済フラグ
		int SetColPackingType		= 21;	//荷姿タイプ
		int SetColPackingQty		= 22;	//荷姿数量
		int SetColUnitName			= 23;	//荷姿単位
		int SetColPackingUnitQty	= 24;	//荷姿単位のバラ入数
		int SetColBRShipQty			= 25;	//バラ数量
		int SetColCTShipQty			= 26;	//カートン数量
		int SetColCSShipQty			= 27;	//ケース数量
		int SetColPLShipQty			= 28;	//パレット数量
		int SetColBRUnitName		= 29;	//バラ単位名
		int SetColCTUnitName		= 30;	//カートン単位名
		int SetColCSUnitName		= 31;	//ケース単位名
		int SetColPLUnitName		= 32;	//パレット単位名
		
		try {
			CreateShipPlovision.setString(SetColOkuriNo			,""+SetOkuriNo);			//送り状番号(WMS採番)
			CreateShipPlovision.setString(SetColMsNo			,""+SetMsNo);				//明細番号
			CreateShipPlovision.setString(SetColSeq				,""+Seq);					//引当枝番
			CreateShipPlovision.setString(SetColOrderItemCd		,""+SetOrderItemCd);		//商品コード
			CreateShipPlovision.setString(SetColOrderItemName01	,""+SetOrderItemName01);	//品名01
			CreateShipPlovision.setString(SetColOrderLot		,""+SetOrderLot);			//受注ロット指定
			if("".equals(SetOrderExpDate)) {
				CreateShipPlovision.setString(SetColOrderExpDate	,null);		//受注賞味期限指定
			}else {
				CreateShipPlovision.setString(SetColOrderExpDate	,""+SetOrderExpDate);		//受注賞味期限指定
			}
			CreateShipPlovision.setString(SetColOrderQty		,""+SetOrderQty);			//受注個数
			CreateShipPlovision.setString(SetColShipWhCd		,TgtWhCd);					//倉庫コード
			CreateShipPlovision.setString(SetColShipLoc			,""+SetShipLoc);			//ロケーション
			CreateShipPlovision.setString(SetColShipItemCd		,""+SetShipItemCd);			//商品コード
			CreateShipPlovision.setString(SetColShipLot			,""+SetShipLot);			//ロット
			if("".equals(SetShipExpdate)) {
				CreateShipPlovision.setString(SetColShipExpdate		,null);					//消費期限
			}else {
				CreateShipPlovision.setString(SetColShipExpdate		,""+SetShipExpdate);	//消費期限
			}
			if("".equals(SetShipActualDate)) {
				CreateShipPlovision.setString(SetColShipActualDate	,null);					//入荷実績日
			}else {
				CreateShipPlovision.setString(SetColShipActualDate	,""+SetShipActualDate);	//入荷実績日
			}
			
			CreateShipPlovision.setString(SetColShipQty			,""+ShipQty);				//引当数量
			CreateShipPlovision.setString(SetColEntryDate		,now_dtm);					//登録日時
			CreateShipPlovision.setString(SetColUpdateDate		,now_dtm);					//更新日時
			CreateShipPlovision.setString(SetColEntryUser		,UpdateUser);				//登録者
			CreateShipPlovision.setString(SetColUpdateUser		,UpdateUser);				//更新者
			CreateShipPlovision.setString(SetColFixFg			,"0");						//引落済フラグ
			CreateShipPlovision.setString(SetColPackingType		,""+SetPackingType);		//荷姿タイプ
			CreateShipPlovision.setString(SetColPackingQty		,""+SetPackingQty);			//荷姿数量
			CreateShipPlovision.setString(SetColUnitName		,""+SetUnitName);			//荷姿単位
			CreateShipPlovision.setString(SetColPackingUnitQty	,""+SetPackingUnitQty);		//荷姿単位のバラ入数
			CreateShipPlovision.setString(SetColBRShipQty		,""+SetBRShipQty);			//バラ数量
			CreateShipPlovision.setString(SetColCTShipQty		,""+SetCTShipQty);			//カートン数量
			CreateShipPlovision.setString(SetColCSShipQty		,""+SetCSShipQty);			//ケース数量
			CreateShipPlovision.setString(SetColPLShipQty		,""+SetPLShipQty);			//パレット数量
			CreateShipPlovision.setString(SetColBRUnitName		,""+SetBRUnitName);			//バラ単位名
			CreateShipPlovision.setString(SetColCTUnitName		,""+SetCTUnitName);			//カートン単位名
			CreateShipPlovision.setString(SetColCSUnitName		,""+SetCSUnitName);			//ケース単位名
			CreateShipPlovision.setString(SetColPLUnitName		,""+SetPLUnitName);			//パレット単位名
			
			CreateShipPlovision.executeUpdate();
		} catch (SQLException e) {
			Rt = false;
			e.printStackTrace();
		}
		
		return Rt;
	}
	
	
	private static PreparedStatement CreateShipPlovision(String TgtWhCd,String TgtClCd) {
		//引当結果レコードを生成するSQL
		String sql = "insert into "+A00000_Main.MySqlDefaultSchemaWANKO+".WW0020ShipPlovision\n"
				+"(cl_cd,"				//荷主コード
				+"InvoiceWHCD,"			//倉庫コード
				+"OkuriNo,"				//送り状番号(WMS採番)
				+"MsNo,"				//明細番号
				+"Seq,"					//引当枝番
				+"OrderItemCd,"			//商品コード
				+"OrderItemName01,"		//品名01
				+"OrderLot,"			//受注ロット指定
				+"OrderExpDate,"		//受注賞味期限指定
				+"OrderQty,"			//受注個数
				+"ShipWhCd,"			//倉庫コード
				+"ShipLoc,"				//ロケーション
				+"ShipItemCd,"			//商品コード
				+"ShipLot,"				//ロット
				+"ShipExpdate,"			//消費期限
				+"ShipActualDate,"		//入荷実績日
				+"ShipQty,"				//引当数量
				+"EntryDate,"			//登録日時
				+"UpdateDate,"			//更新日時
				+"EntryUser,"			//登録者
				+"UpdateUser,"			//更新者
				+"FixFg,"				//引落済フラグ
				+"PackingType,"			//荷姿タイプ
				+"PackingQty,"			//荷姿数量
				+"UnitName,"			//荷姿単位
				+"PackingUnitQty,"		//荷姿単位のバラ入数
				+"BRShipQty,"			//バラ数量
				+"CTShipQty,"			//カートン数量
				+"CSShipQty,"			//ケース数量
				+"PLShipQty,"			//パレット数量
				+"BRUnitName,"			//バラ単位名
				+"CTUnitName,"			//カートン単位名
				+"CSUnitName,"			//ケース単位名
				+"PLUnitName"			//パレット単位名
				
				+")values ("
				
				+"'"+TgtClCd+"',"	//荷主コード
				+"'"+TgtWhCd+"',"	//倉庫コード
				+"?,"				//送り状番号(WMS採番)
				+"?,"				//明細番号
				+"?,"				//引当枝番
				+"?,"				//商品コード
				+"?,"				//品名01
				+"?,"				//受注ロット指定
				+"?,"				//受注賞味期限指定
				+"?,"				//受注個数
				+"?,"				//倉庫コード
				+"?,"				//ロケーション
				+"?,"				//商品コード
				+"?,"				//ロット
				+"?,"				//消費期限
				+"?,"				//入荷実績日
				+"?,"				//引当数量
				+"?,"				//登録日時
				+"?,"				//更新日時
				+"?,"				//登録者
				+"?,"				//更新者
				+"?,"				//引落済フラグ
				+"?,"				//荷姿タイプ
				+"?,"				//荷姿数量
				+"?,"				//荷姿単位
				+"?,"				//荷姿単位のバラ入数
				+"?,"				//バラ数量
				+"?,"				//カートン数量
				+"?,"				//ケース数量
				+"?,"				//パレット数量
				+"?,"				//バラ単位名
				+"?,"				//カートン単位名
				+"?,"				//ケース単位名
				+"?"				//パレット単位名
				+")"
				;
		A100_DbConnect.DB_CONN("WANKO");
		PreparedStatement stmt01 = null;
		try {
			A100_DbConnect.conn.setAutoCommit(true);
			stmt01 = A100_DbConnect.conn.prepareStatement(sql);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return stmt01;
	}
	
	
	
	private static PreparedStatement StockGet(String TgtWhCd,String TgtClCd,String SortMode) {
		String sql = "select "
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
		
		sql = sql + " and WW0015Stock.ClCd = '"+TgtClCd+"' \n";
		sql = sql + " and WW0015Stock.WhCd = '"+TgtWhCd+"' \n";
		sql = sql + " and WW0015Stock.PossibleQty >0 \n";
		sql = sql + " and WW0015Stock.ItemCd = ? \n";
		if("Loc".equals(SortMode)) {
			sql = sql + " order by WW0015Stock.ItemCd,WW0015Stock.Expdate,WM0010LOCATIONMST.LocType,WW0015Stock.Lot,WW0015Stock.ActualDate,WW0015Stock.Loc \n";
		}else {
			sql = sql + " order by WW0015Stock.ItemCd,WW0015Stock.Expdate,WM0010LOCATIONMST.LocType,WW0015Stock.Lot,WW0015Stock.ActualDate,WW0015Stock.PossibleQty \n";
		}
		A100_DbConnect.DB_CONN("WANKO");
		PreparedStatement stmt01 = null;
		try {
			stmt01 = A100_DbConnect.conn.prepareStatement(sql);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return stmt01;
	}
	private static PreparedStatement StockCheck(String TgtWhCd,String TgtClCd) {
		String sql = "select "
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
		
		sql = sql + " and WW0015Stock.ClCd = '"+TgtClCd+"' \n";
		sql = sql + " and WW0015Stock.WhCd = '"+TgtWhCd+"' \n";
		sql = sql + " and WW0015Stock.ItemCd = ? \n";
		sql = sql + " and WW0015Stock.Loc = ? \n";
		sql = sql + " and WW0015Stock.Lot = ? \n";
		sql = sql + " and WW0015Stock.Expdate = ? \n";
		sql = sql + " and WW0015Stock.ActualDate = ? \n";
		sql = sql + " order by WW0015Stock.ItemCd,WW0015Stock.Expdate,WM0010LOCATIONMST.LocType,WW0015Stock.Lot,WW0015Stock.ActualDate \n";
		
		A100_DbConnect.DB_CONN("WANKO");
		PreparedStatement stmt01 = null;
		try {
			stmt01 = A100_DbConnect.conn.prepareStatement(sql);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return stmt01;
	}
	
	private static PreparedStatement StockSummaryGet(String TgtWhCd,String TgtClCd) {
		String sql = "select "
				+"(WW0015Stock.ClCd) as ClCd,\n"						//荷主コード
				+"(WW0015Stock.WhCd) as WhCd,\n"						//倉庫コード
				+"(WW0015Stock.ItemCd) as ItemCd,\n"					//商品コード
				+"(WW0015Stock.Lot) as Lot,\n"							//ロット
				+"(WW0015Stock.Expdate) as Expdate,\n"					//消費期限
				+"sum(WW0015Stock.Qty) as Qty,\n"						//数量
				+"sum(WW0015Stock.ShipPlanQty) as ShipPlanQty,\n"		//引当済数
				+"sum(WW0015Stock.PossibleQty) as PossibleQty,\n"		//出荷可能数
				+"max(WW0015Stock.ItemName) as ItemName,\n"				//商品名
				+"max(KM0061_ITEMMSTSUB.CtQty) as CtUnitQty,\n"			//カートン入数
				+"max(KM0061_ITEMMSTSUB.CsQty) as CsUnitQty,\n"			//ケース入数
				+"max(KM0061_ITEMMSTSUB.PlQty) as PlUnitQty,\n"			//パレット入数
				+"max(WW0015Stock.EntryDate) as EntryDate,\n"			//登録日時
				+"max(WW0015Stock.UpdateDate) as UpdateDate,\n"			//更新日時
				+"max(WW0015Stock.EntryUser) as EntryUser,\n"			//登録者
				+"max(WW0015Stock.UpdateUser) as UpdateUser\n"			//更新者
				+ " from "+A00000_Main.MySqlDefaultSchemaWANKO+".WW0015Stock"
				+ " left outer join "+A00000_Main.MySqlDefaultSchemaWANKO+".WM0010LOCATIONMST\n"
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
				+ " left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0060_ITEMMST\n"
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
		
		sql = sql + " and WW0015Stock.PossibleQty>0\n";
		sql = sql + " and WW0015Stock.ClCd='"+TgtClCd+"'\n";
		sql = sql + " and WW0015Stock.WhCd='"+TgtWhCd+"'\n";
		sql = sql + " and WW0015Stock.ItemCd=?\n";
		
		sql = sql + " group by WW0015Stock.ItemCd,WW0015Stock.Expdate,WW0015Stock.Lot\n";
		sql = sql + " order by WW0015Stock.ItemCd,WW0015Stock.Expdate,WW0015Stock.Lot\n";
		//System.out.println(sql);
		A100_DbConnect.DB_CONN("WANKO");
		PreparedStatement stmt01 = null;
		try {
			stmt01 = A100_DbConnect.conn.prepareStatement(sql);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return stmt01;
	}
	
	private static PreparedStatement OkuriHdUpdate(String TgtWhCd,String TgtClCd) {
		String sql = "update "+A00000_Main.MySqlDefaultSchemaNYANKO+".KT0010_OKURI_HD \n"
				+ "set "
				+ "KT0010_OKURI_HD.WmsStatus = '1',\n"
				+ "KT0010_OKURI_HD.UpdateDate = ?,\n"
				+ "KT0010_OKURI_HD.UpdateUser = ?,\n"
				+ "KT0010_OKURI_HD.UpdatePG = 'WT100_ShipPlovision' \n"
				+ " where 1=1"
				+ " and KT0010_OKURI_HD.cl_cd ='"+TgtClCd+"'"
				+ " and KT0010_OKURI_HD.InvoiceWHCD ='"+TgtWhCd+"'"
				+ " and KT0010_OKURI_HD.OkuriNo = ?"
				;
		A100_DbConnect.DB_CONN("NYANKO");
		PreparedStatement stmt01 = null;
		try {
			A100_DbConnect.conn.setAutoCommit(true);
			stmt01 = A100_DbConnect.conn.prepareStatement(sql);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return stmt01;
	}
	
	private static PreparedStatement MinExpDateGet(String TgtWhCd,String TgtClCd) {
		String sql = "select "
				+"Max(KT0010_OKURI_HD.cl_cd) 				as ClCd,\n"				//荷主コード
				+"Max(KT0010_OKURI_HD.InvoiceWHCD) 			as InvoiceWhCd,\n"		//倉庫コード
				+"Max(KT0010_OKURI_HD.DeliCd) 				as DeliCd,\n"			//荷届先コード
				+"Max(KT0010_OKURI_HD.DeliDepartmentCd) 	as DeliDepartmentCd,\n"	//部署CD
				+"Max(KT0010_OKURI_HD.PlanDate) 			as PlanDate,\n"			//出荷予定日
				+"(WW0020ShipPlovision.OrderItemCd) as OrderItemCd,\n"				//商品コード
				+"Max(WW0020ShipPlovision.ShipExpdate) as ShipExpdate \n"			//消費期限
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
		//System.out.println(sql);
		try {
			stmt01 = A100_DbConnect.conn.prepareStatement(sql);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return stmt01;
	}
	
	private static PreparedStatement MaxExpDateGet(String TgtWhCd,String TgtClCd) {
		String sql = "select "
				+"Min(KT0010_OKURI_HD.cl_cd) 				as ClCd,\n"				//荷主コード
				+"Min(KT0010_OKURI_HD.InvoiceWHCD) 			as InvoiceWhCd,\n"		//倉庫コード
				+"Min(KT0010_OKURI_HD.DeliCd) 				as DeliCd,\n"			//荷届先コード
				+"Min(KT0010_OKURI_HD.DeliDepartmentCd) 	as DeliDepartmentCd,\n"	//部署CD
				+"Min(KT0010_OKURI_HD.PlanDate) 			as PlanDate,\n"			//出荷予定日
				+"(WW0020ShipPlovision.OrderItemCd) as OrderItemCd,\n"				//商品コード
				+"Min(WW0020ShipPlovision.ShipExpdate) as ShipExpdate \n"			//消費期限
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
	private static Object[][]  PlovisionSortMode(String TgtWhCd,String TgtClCd){
		//引当順序在庫残少ない順もしくはロケ順の判断基準入手
		ArrayList<String> SearchClWh 			= new ArrayList<String>();
		ArrayList<String> SearchClCd 			= new ArrayList<String>();
		ArrayList<String> SearchParaCd 			= new ArrayList<String>();	
		ArrayList<Integer> SearchParaCdSeqStr	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaCdSeqEnd	= new ArrayList<Integer>();
		ArrayList<String> SearchParaName 		= new ArrayList<String>();
		ArrayList<String> SearchParaTxt01		= new ArrayList<String>();
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
		SearchParaCd.add("PlovisionSortMode");
		
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
	
	private static int BanAllExpDateOld(String TgtWhCd,String TgtClCd){
		//全届先で古い賞味期限禁止設定をWMSパラメータマスタから取得
		//1を返却した場合全届け先で日付逆転禁止
		ArrayList<String> SearchClWh 			= new ArrayList<String>();
		ArrayList<String> SearchClCd 			= new ArrayList<String>();
		ArrayList<String> SearchParaCd 			= new ArrayList<String>();	
		ArrayList<Integer> SearchParaCdSeqStr	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaCdSeqEnd	= new ArrayList<Integer>();
		ArrayList<String> SearchParaName 		= new ArrayList<String>();
		ArrayList<String> SearchParaTxt01		= new ArrayList<String>();
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
		SearchParaCd.add("BanAllExpDateOld");
		
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
		
		int Rt = 0;
		if(0<ParameterMstWankoRt.length) {
			Rt = (int)ParameterMstWankoRt[0][M100_ParameterMstWankoRt.ColParaInt01];		//パラメータ数値項目01
		}
		return Rt;
	}
	private static int AllSelfishLot(String TgtWhCd,String TgtClCd){
		//全届先でロットまとめる設定をWMSパラメータマスタから取得
		//1を返却した場合全届け先でロット一種にまとめる
		ArrayList<String> SearchClWh 			= new ArrayList<String>();
		ArrayList<String> SearchClCd 			= new ArrayList<String>();
		ArrayList<String> SearchParaCd 			= new ArrayList<String>();	
		ArrayList<Integer> SearchParaCdSeqStr	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaCdSeqEnd	= new ArrayList<Integer>();
		ArrayList<String> SearchParaName 		= new ArrayList<String>();
		ArrayList<String> SearchParaTxt01		= new ArrayList<String>();
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
		SearchParaCd.add("AllSelfishLot");
		
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
		
		int Rt = 0;
		if(0<ParameterMstWankoRt.length) {
			Rt = (int)ParameterMstWankoRt[0][M100_ParameterMstWankoRt.ColParaInt01];		//パラメータ数値項目01
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