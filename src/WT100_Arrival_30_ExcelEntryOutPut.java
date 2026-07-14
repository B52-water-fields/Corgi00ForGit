import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class WT100_Arrival_30_ExcelEntryOutPut{
	//入荷予定に対してエクセルで入荷実績を取り込むためのエクセルファイルを出力する
	
	static final int ColGetClWh				=  0;	//担当倉庫
	static final int ColGetClCd				=  1;	//荷主CD
	static final int ColGetCLName01			=  2;	//荷主名
	static final int ColGetPlanDate			=  3;	//入荷予定日
	static final int ColGetSpCd				=  4;	//仕入先CD
	static final int ColGetSpName01			=  5;	//仕入先標記名
	static final int ColGetClArrNo			=  6;	//荷主予定番号
	static final int ColGetArrNo				=  7;	//入荷予定NO
	static final int ColGetMsNo				=  8;	//明細番号
	static final int ColGetItemCd				=  9;	//商品コード
	static final int ColGetClItemCd			= 10;	//荷主商品コード
	static final int ColGetItemName			= 11;	//商品名
	static final int ColGetlot					= 12;	//予定ロット
	static final int ColGetExpDate			= 13;	//予定消費期限
	static final int ColGetPlanQty			= 14;	//予定数量
	static final int ColGetRecmendLoc			= 15;	//在庫考慮推奨ロケ
	static final int ColGetRecmendLocMst		= 16;	//推奨ロケマスタ情報
	static final int ColEntryLot				= 17;	//入荷実績ロット
	static final int ColEntryExpDate			= 18;	//入荷実績消費期限
	static final int ColEntryQty				= 19;	//入荷実績数
	static final int ColEntryStoreLoc			= 20;	//格納ロケ
	static final int ColGetCom01				= 21;	//明細コメント1
	static final int ColGetCom02				= 22;	//明細コメント2
	static final int ColGetArCom01			= 23;	//ヘッダコメント1
	static final int ColGetArCom02			= 24;	//ヘッダコメント2
	static final int ColGetArCom03			= 25;	//ヘッダコメント3
	static final int ColGetJanCd				= 26;	//JANCD（バラ）
	static final int ColGetItemMdNo			= 27;	//商品型番
	static final int ColGetClGpCD				= 28;	//荷主グループCD
	static final int ColGetCLGpName01			= 29;	//荷主グループ標記名
	static final int ColGetEntryDate			= 30;	//登録日
	static final int ColGetUpdateDate			= 31;	//更新日
	static final int ColGetEntryUser			= 32;	//登録者
	static final int ColGetUpdateUser			= 33;	//更新者
	static final int ColGetOutPutDTM			= 34;	//出力日時
	
	public static Object[][] RtArrivalExcelOutPut() {
		Object[][] Rt = {
					 {"GetClWh"				,ColGetClWh				,"String"	,"担当倉庫" 				,"Key"}
					,{"GetClCd"				,ColGetClCd				,"String"	,"荷主CD" 					,"Key"}
					,{"GetCLName01"			,ColGetCLName01			,"String"	,"荷主名"		 			,""}
					,{"GetPlanDate"			,ColGetPlanDate			,"Date"		,"入荷予定日" 				,"Key"}
					,{"GetSpCd"				,ColGetSpCd				,"String"	,"仕入先CD" 				,""}
					,{"GetSpName01"			,ColGetSpName01			,"String"	,"仕入先標記名"				,""}
					,{"GetClArrNo"			,ColGetClArrNo			,"String"	,"荷主予定番号" 			,""}
					,{"GetArrNo"			,ColGetArrNo				,"String"	,"入荷予定NO" 				,"Key"}
					,{"GetMsNo"				,ColGetMsNo				,"int"		,"明細番号" 				,"Key"}
					,{"GetItemCd"			,ColGetItemCd				,"String"	,"商品コード" 				,""}
					,{"GetClItemCd"			,ColGetClItemCd			,"String"	,"荷主商品コード" 			,""}
					,{"GetItemName"			,ColGetItemName			,"String"	,"商品名" 					,""}
					,{"Getlot"				,ColGetlot					,"String"	,"予定ロット" 				,""}
					,{"GetExpDate"			,ColGetExpDate			,"Date"		,"予定消費期限" 			,""}
					,{"GetPlanQty"			,ColGetPlanQty			,"int"		,"予定数量" 				,""}
					,{"GetRecmendLoc"		,ColGetRecmendLoc			,"String"	,"在庫考慮推奨ロケ"			,""}
					,{"GetRecmendLocMst"	,ColGetRecmendLocMst		,"String"	,"推奨ロケマスタ情報" 		,""}
					,{"EntryLot"			,ColEntryLot				,"String"	,"入荷実績ロット" 			,""}
					,{"EntryExpDate"		,ColEntryExpDate			,"String"	,"入荷実績消費期限" 		,""}
					,{"EntryQty"			,ColEntryQty				,"int"		,"入荷実績数" 				,""}
					,{"EntryStoreLoc"		,ColEntryStoreLoc			,"String"	,"格納ロケ" 				,""}
					,{"GetCom01"			,ColGetCom01				,"String"	,"明細コメント1" 			,""}
					,{"GetCom02"			,ColGetCom02				,"String"	,"明細コメント2" 			,""}
					,{"GetArCom01"			,ColGetArCom01			,"String"	,"ヘッダコメント1" 			,""}
					,{"GetArCom02"			,ColGetArCom02			,"String"	,"ヘッダコメント2" 			,""}
					,{"GetArCom03"			,ColGetArCom03			,"String"	,"ヘッダコメント3" 			,""}
					,{"GetJanCd"			,ColGetJanCd				,"String"	,"JANCD（バラ）" 			,""}
					,{"GetItemMdNo"			,ColGetItemMdNo			,"String"	,"商品型番" 				,""}
					,{"GetClGpCD"			,ColGetClGpCD				,"String"	,"ヘッダ荷主グループCD" 	,""}
					,{"GetCLGpName01"		,ColGetCLGpName01			,"String"	,"ヘッダ荷主グループ標記名" ,""}
					,{"GetEntryDate"		,ColGetEntryDate			,"DateTime"	,"登録日" 					,""}
					,{"GetUpdateDate"		,ColGetUpdateDate			,"DateTime"	,"更新日" 					,""}
					,{"GetEntryUser"		,ColGetEntryUser			,"String"	,"登録者" 					,""}
					,{"GetUpdateUser"		,ColGetUpdateUser			,"String"	,"更新者" 					,""}
					,{"GetOutPutDTM"		,ColGetOutPutDTM			,"DateTime"	,"出力日時" 				,""}
				};
		return Rt;
	}
	
	public static void ArrivalExcelOutPut(String TgtWhCd,String TgtClCd,ArrayList<String>TgtArrivalNo) {
		//登録用のエクセルを出力する
		String Selected = B100_FolderSelect.FolderSelect("出力先選択");
		if(null!=Selected) {
			String[][] OutData = ArrivalExcelOutPutCreate(TgtWhCd,TgtClCd,TgtArrivalNo);
			
			String NowDTM=B100_DateTimeControl.dtmString2(B100_DateTimeControl.dtm()[1])[1].replace(" ", "").replace("/", "").replace(":", "");
			String fp = Selected+"\\"+"入荷実績登録用Excell"+NowDTM+".xlsx";
			
			int MFG = 0;
			int OPFG = 1;
			B100_ExcellControl.EXCELL_DATA_SET(fp,"入荷予定（明細）検索結果",OutData ,MFG,OPFG);
			
			//ファイル開く
			File file = new File(fp);
			Desktop desktop = Desktop.getDesktop();
			try {
				desktop.open(file);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public static String[][] ArrivalExcelOutPutCreate(String TgtWhCd,String TgtClCd,ArrayList<String>TgtArrivalNo) {
		Object[][] ArrivalPlanMsRt = ArrivalPlanMsRt(TgtWhCd,TgtClCd,TgtArrivalNo);
		String now_dtm = B100_DateTimeControl.dtmString2(B100_DateTimeControl.dtm()[1])[1];
		
		Object[][] RtArrivalExcelOutPut	= RtArrivalExcelOutPut();
		String[][] OutString	= new String[ArrivalPlanMsRt.length+1][RtArrivalExcelOutPut.length];
		
		String[][] RecomendLocTgtItemCd = new String[ArrivalPlanMsRt.length][ArrivalPlanMsRt.length];
		
		for(int i=0;i<RtArrivalExcelOutPut.length;i++) {
			OutString[0][(int)RtArrivalExcelOutPut[i][1]]	=	(String)RtArrivalExcelOutPut[i][3];
			for(int i01=1;i01<OutString.length;i01++) {
				if("int".equals((String)RtArrivalExcelOutPut[i][2])||"float".equals((String)RtArrivalExcelOutPut[i][2])) {
					OutString[i01][(int)RtArrivalExcelOutPut[i][1]]	= "0";
				}else {
					OutString[i01][(int)RtArrivalExcelOutPut[i][1]]	= "";
				}
			}
		}
		
		for(int i=0;i<ArrivalPlanMsRt.length;i++) {
			String GetClWh			= B100_TextControl.Trim((String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColClWh]);					//ヘッダ担当倉庫
			String GetClCd			= B100_TextControl.Trim((String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColClCd]);					//ヘッダ荷主CD
			String GetCLName01		= B100_TextControl.Trim((String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColCLName01]);			//ヘッダ荷主名
			String GetClGpCD		= B100_TextControl.Trim((String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColClGpCD]);				//ヘッダ荷主グループCD
			String GetCLGpName01	= B100_TextControl.Trim((String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColCLGpName01]);			//ヘッダ荷主グループ標記名
			String GetArrNo			= B100_TextControl.Trim((String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColArrNo]);				//ヘッダ入荷予定NO
			String GetClArrNo		= B100_TextControl.Trim((String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColClArrNo]);				//ヘッダ荷主予定番号
			String GetPlanDate		= B100_TextControl.TextToDate((String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColPlanDate]);		//ヘッダ入荷予定日
			String GetHdActualDate	= B100_TextControl.Trim((String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColHdActualDate]);		//ヘッダ入荷実績日
			String GetSpCd			= B100_TextControl.Trim((String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColSpCd]);					//ヘッダ仕入先CD
			String GetSpName01		= B100_TextControl.Trim((String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColSpName01]);			//ヘッダ仕入先名01
			String GetSpName02		= B100_TextControl.Trim((String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColSpName02]);			//ヘッダ仕入先名02
			String GetSpName03		= B100_TextControl.Trim((String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColSpName03]);			//ヘッダ仕入先名03
			String GetSpPost		= B100_TextControl.Trim((String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColSpPost]);				//ヘッダ仕入先郵便
			String GetSpAdd01		= B100_TextControl.Trim((String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColSpAdd01]);				//ヘッダ仕入先住所01
			String GetSpAdd02		= B100_TextControl.Trim((String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColSpAdd02]);				//ヘッダ仕入先住所02
			String GetSpAdd03		= B100_TextControl.Trim((String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColSpAdd03]);				//ヘッダ仕入先住所03
			String GetSpTel			= B100_TextControl.Trim((String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColSpTel]);				//ヘッダ仕入先電話
			String GetArCom01		= B100_TextControl.Trim((String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColArCom01]);				//ヘッダコメント1
			String GetArCom02		= B100_TextControl.Trim((String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColArCom02]);				//ヘッダコメント2
			String GetArCom03		= B100_TextControl.Trim((String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColArCom03]);				//ヘッダコメント3
			String GetHdEntryDate	= B100_TextControl.Trim((String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColHdEntryDate]);		//ヘッダ登録日
			String GetHdUpdateDate	= B100_TextControl.Trim((String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColHdUpdateDate]);		//ヘッダ更新日
			String GetHdEntryUser	= B100_TextControl.Trim((String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColHdEntryUser]);		//ヘッダ登録者
			String GetHdUpdateUser	= B100_TextControl.Trim((String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColHdUpdateUser]);		//ヘッダ更新者
			int  GetFixFg			= (int)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColFixFg];											//ヘッダ状況
						
			int GetMsNo				= (int)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColMsNo];											//明細番号
			String GetItemCd		= B100_TextControl.Trim((String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColItemCd]);				//商品コード
			String GetClItemCd		= B100_TextControl.Trim((String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColClItemCd]);			//荷主商品コード
			String GetJanCd			= B100_TextControl.Trim((String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColJanCd]);				//JANCD（バラ）
			String GetItemMdNo		= B100_TextControl.Trim((String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColItemMdNo]);			//商品型番
			String GetItemName		= B100_TextControl.Trim((String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColItemName]);			//商品名
			String Getlot			= B100_TextControl.Trim((String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.Collot]);					//ロット
			String GetExpDate		= B100_TextControl.TextToDate((String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColExpDate]);		//消費期限
			int GetPlanQty			= (int)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColPlanQty];										//予定数量
			int GetActualQty		= (int)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColActualQty];										//実績数
			String GetActualDate	= B100_TextControl.Trim((String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColActualDate]);			//入荷日
			String GetCom01			= B100_TextControl.Trim((String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColCom01]);				//コメント1
			String GetCom02			= B100_TextControl.Trim((String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColCom02]);				//コメント2
			String GetEntryDate		= B100_TextControl.Trim((String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColEntryDate]);			//登録日
			String GetUpdateDate	= B100_TextControl.Trim((String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColUpdateDate]);			//更新日
			String GetEntryUser		= B100_TextControl.Trim((String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColEntryUser]);			//登録者
			String GetUpdateUser	= B100_TextControl.Trim((String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColUpdateUser]);			//更新者
			
			RecomendLocTgtItemCd[i][Tools100_RecomendLocWithStockSerch.InColItemCd]	= GetItemCd;
			RecomendLocTgtItemCd[i][Tools100_RecomendLocWithStockSerch.InColLot]		= Getlot;
			RecomendLocTgtItemCd[i][Tools100_RecomendLocWithStockSerch.InColExpdate]	= GetExpDate;
			
			OutString[i+1][ColGetClWh]				= 	"" + GetClWh;				//担当倉庫
			OutString[i+1][ColGetClCd]				= 	"" + GetClCd;				//荷主CD
			OutString[i+1][ColGetCLName01]		= 	"" + GetCLName01;			//荷主名
			OutString[i+1][ColGetPlanDate]		= 	"" + GetPlanDate;			//入荷予定日
			OutString[i+1][ColGetSpCd]				= 	"" + GetSpCd;				//仕入先CD
			OutString[i+1][ColGetSpName01]		= 	"" + GetSpName01;			//仕入先標記名
			OutString[i+1][ColGetClArrNo]			= 	"" + GetClArrNo;				//荷主予定番号
			OutString[i+1][ColGetArrNo]			= 	"" + GetArrNo;				//入荷予定NO
			OutString[i+1][ColGetMsNo]				= 	"" + GetMsNo;				//明細番号
			OutString[i+1][ColGetItemCd]			= 	"" + GetItemCd;				//商品コード
			OutString[i+1][ColGetClItemCd]		= 	"" + GetClItemCd;			//荷主商品コード
			OutString[i+1][ColGetItemName]		= 	"" + GetItemName;			//商品名
			OutString[i+1][ColGetlot]				= 	"" + Getlot;				//予定ロット
			OutString[i+1][ColGetExpDate]			= 	"" + GetExpDate;			//予定消費期限
			OutString[i+1][ColGetPlanQty]			= 	"" + GetPlanQty;			//予定数量
			OutString[i+1][ColGetRecmendLoc]		= 	"";							//在庫考慮推奨ロケ
			OutString[i+1][ColGetRecmendLocMst]	= 	"";							//推奨ロケマスタ情報
			OutString[i+1][ColEntryLot]			= 	"";							//入荷実績ロット
			OutString[i+1][ColEntryExpDate]		= 	"";							//入荷実績消費期限
			OutString[i+1][ColEntryQty]			= 	"0";						//入荷実績数
			OutString[i+1][ColEntryStoreLoc]		= 	"";							//格納ロケ
			OutString[i+1][ColGetCom01]			= 	"" + GetCom01;				//明細コメント1
			OutString[i+1][ColGetCom02]			= 	"" + GetCom02;				//明細コメント2
			OutString[i+1][ColGetArCom01]			= 	"" + GetArCom01;			//ヘッダコメント1
			OutString[i+1][ColGetArCom02]			= 	"" + GetArCom02;			//ヘッダコメント2
			OutString[i+1][ColGetArCom03]			= 	"" + GetArCom03;			//ヘッダコメント3
			OutString[i+1][ColGetJanCd]			= 	"" + GetJanCd;				//JANCD（バラ）
			OutString[i+1][ColGetItemMdNo]		= 	"" + GetItemMdNo;			//商品型番
			OutString[i+1][ColGetClGpCD]			= 	"" + GetClGpCD;				//ヘッダ荷主グループCD
			OutString[i+1][ColGetCLGpName01]		= 	"" + GetCLGpName01;			//ヘッダ荷主グループ標記名
			OutString[i+1][ColGetEntryDate]		= 	"" + GetEntryDate;			//登録日
			OutString[i+1][ColGetUpdateDate]		= 	"" + GetUpdateDate;			//更新日
			OutString[i+1][ColGetEntryUser]		= 	"" + GetEntryUser;			//登録者
			OutString[i+1][ColGetUpdateUser]		= 	"" + GetUpdateUser;			//更新者
			OutString[i+1][ColGetOutPutDTM]		= 	now_dtm;					//出力日時
		}
		
		String[][] RecomendLocWithStockSerch = Tools100_RecomendLocWithStockSerch.RecomendLocWithStockSerch(TgtWhCd,TgtClCd,RecomendLocTgtItemCd);
		for(int i01=1;i01<OutString.length;i01++) {
			for(int i02=0;i02<RecomendLocWithStockSerch.length;i02++) {
				if(OutString[i01][ColGetItemCd].equals(RecomendLocWithStockSerch[i02][Tools100_RecomendLocWithStockSerch.ColItemCd])
						&& OutString[i01][ColGetlot].equals(RecomendLocWithStockSerch[i02][Tools100_RecomendLocWithStockSerch.ColLot])
						&& OutString[i01][ColGetExpDate].equals(RecomendLocWithStockSerch[i02][Tools100_RecomendLocWithStockSerch.ColExpdate])
						){
					OutString[i01][ColGetRecmendLoc]		= 	RecomendLocWithStockSerch[i02][Tools100_RecomendLocWithStockSerch.ColRecomendLoc];	//在庫考慮推奨ロケ
					OutString[i01][ColGetRecmendLocMst]	= 	RecomendLocWithStockSerch[i02][Tools100_RecomendLocWithStockSerch.ColMstRecomendLoc];	//推奨ロケマスタ情報
					i02=RecomendLocWithStockSerch.length+1;
				}
			}
		}
		return OutString;
	}
	
	private static Object[][] ArrivalPlanMsRt(String TgtWhCd,String TgtClCd,ArrayList<String>TgtArrivalNo){
		ArrayList<String> SearchClWh			= new ArrayList<String>();		//ヘッダ担当倉庫
		ArrayList<String> SearchClCd			= new ArrayList<String>();		//ヘッダ荷主CD
		ArrayList<String> SearchCLName01		= new ArrayList<String>();		//ヘッダ荷主名
		ArrayList<String> SearchClGpCD			= new ArrayList<String>();		//ヘッダ荷主グループCD
		ArrayList<String> SearchCLGpName01		= new ArrayList<String>();		//ヘッダ荷主グループ標記名
		ArrayList<String> SearchArrNo			= TgtArrivalNo;					//ヘッダ入荷予定NO
		ArrayList<String> SearchClArrNo			= new ArrayList<String>();		//ヘッダ荷主予定番号
		ArrayList<String> SearchPlanDateMin		= new ArrayList<String>();		//ヘッダ入荷予定日
		ArrayList<String> SearchPlanDateMax		= new ArrayList<String>();		//ヘッダ入荷予定日
		ArrayList<String> SearchHdActualDateMin	= new ArrayList<String>();		//ヘッダ入荷実績日
		ArrayList<String> SearchHdActualDateMax	= new ArrayList<String>();		//ヘッダ入荷実績日
		ArrayList<String> SearchSpCd			= new ArrayList<String>();		//ヘッダ仕入先CD
		ArrayList<String> SearchSpName			= new ArrayList<String>();		//ヘッダ仕入先名
		ArrayList<String> SearchSpPost			= new ArrayList<String>();		//ヘッダ仕入先郵便
		ArrayList<String> SearchSpAdd			= new ArrayList<String>();		//ヘッダ仕入先住所
		ArrayList<String> SearchSpTel			= new ArrayList<String>();		//ヘッダ仕入先電話
		ArrayList<String> SearchArCom			= new ArrayList<String>();		//ヘッダコメント
		ArrayList<Integer> SearchFixFg			= new ArrayList<Integer>();		//ヘッダ状況
				
		ArrayList<Integer> SearchMsNoMin		= new ArrayList<Integer>();		//明細番号最小
		ArrayList<Integer> SearchMsNoMax		= new ArrayList<Integer>();		//明細番号最大
		ArrayList<String> SearchItemCd			= new ArrayList<String>();		//商品コード
		ArrayList<String> SearchClItemCd		= new ArrayList<String>();		//荷主商品コード
		ArrayList<String> SearchJanCd			= new ArrayList<String>();		//JANCD（バラ）
		ArrayList<String> SearchItemMdNo		= new ArrayList<String>();		//商品型番
		ArrayList<String> SearchItemName		= new ArrayList<String>();		//商品名
		ArrayList<String> Searchlot				= new ArrayList<String>();		//ロット
		ArrayList<String> SearchExpDateMin		= new ArrayList<String>();		//消費期限最小
		ArrayList<String> SearchExpDateMax		= new ArrayList<String>();		//消費期限最大
		ArrayList<Integer> SearchPlanQtyMin		= new ArrayList<Integer>();		//予定数量最小
		ArrayList<Integer> SearchPlanQtyMax		= new ArrayList<Integer>();		//予定数量最大
		ArrayList<Integer> SearchActualQtyMin	= new ArrayList<Integer>();		//実績数
		ArrayList<Integer> SearchActualQtyMax	= new ArrayList<Integer>();		//実績数
		ArrayList<String> SearchActualDateMin	= new ArrayList<String>();		//入荷日
		ArrayList<String> SearchActualDateMax	= new ArrayList<String>();		//入荷日
		ArrayList<String> SearchCom				= new ArrayList<String>();		//コメント
		ArrayList<String> SearchEntryDateMin	= new ArrayList<String>();		//登録日
		ArrayList<String> SearchEntryDateMax	= new ArrayList<String>();		//登録日
		ArrayList<String> SearchUpdateDateMin	= new ArrayList<String>();		//更新日
		ArrayList<String> SearchUpdateDateMax	= new ArrayList<String>();		//更新日
		ArrayList<String> SearchEntryUser		= new ArrayList<String>();		//登録者
		ArrayList<String> SearchUpdateUser		= new ArrayList<String>();		//更新者
		boolean AllSearch = false;
		
		if(null==TgtWhCd) {TgtWhCd	= "";}
		if(null==TgtClCd) {TgtClCd	= "";}
		
		if("".equals(TgtWhCd)) {TgtWhCd	= A00000_Main.ClWh;}
		if("".equals(TgtClCd)) {TgtClCd	= A00000_Main.ClCd;}
		
		SearchClWh.add(TgtWhCd);
		SearchClCd.add(TgtClCd);
		
		SearchFixFg.add(0);
		
		
		Object[][] ArrivalPlanMsRt	= T100_ArrivalPlanMsRt.ArrivalPlanMsRt(
				SearchClWh,					//ヘッダ担当倉庫
				SearchClCd,					//ヘッダ荷主CD
				SearchCLName01,				//ヘッダ荷主名
				SearchClGpCD,				//ヘッダ荷主グループCD
				SearchCLGpName01,			//ヘッダ荷主グループ標記名
				SearchArrNo,				//ヘッダ入荷予定NO
				SearchClArrNo,				//ヘッダ荷主予定番号
				SearchPlanDateMin,			//ヘッダ入荷予定日
				SearchPlanDateMax,			//ヘッダ入荷予定日
				SearchHdActualDateMin,		//ヘッダ入荷実績日
				SearchHdActualDateMax,		//ヘッダ入荷実績日
				SearchSpCd,					//ヘッダ仕入先CD
				SearchSpName,				//ヘッダ仕入先名
				SearchSpPost,				//ヘッダ仕入先郵便
				SearchSpAdd,				//ヘッダ仕入先住所
				SearchSpTel,				//ヘッダ仕入先電話
				SearchArCom,				//ヘッダコメント
				SearchFixFg,				//ヘッダ状況
						
				SearchMsNoMin,				//明細番号最小
				SearchMsNoMax,				//明細番号最大
				SearchItemCd,				//商品コード
				SearchClItemCd,				//荷主商品コード
				SearchJanCd,				//JANCD（バラ）
				SearchItemMdNo,				//商品型番
				SearchItemName,				//商品名
				Searchlot,					//ロット
				SearchExpDateMin,			//消費期限最小
				SearchExpDateMax,			//消費期限最大
				SearchPlanQtyMin,			//予定数量最小
				SearchPlanQtyMax,			//予定数量最大
				SearchActualQtyMin,			//実績数
				SearchActualQtyMax,			//実績数
				SearchActualDateMin,		//入荷日
				SearchActualDateMax,		//入荷日
				SearchCom,					//コメント
				SearchEntryDateMin,			//登録日
				SearchEntryDateMax,			//登録日
				SearchUpdateDateMin,		//更新日
				SearchUpdateDateMax,		//更新日
				SearchEntryUser,			//登録者
				SearchUpdateUser,			//更新者
				AllSearch);
		
		return ArrivalPlanMsRt;
	}
}