import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class WT100_ShipPlovisionFix{
	//引き当て解除
	static final int ColClCd					=  0;			//荷主コード
	static final int ColInvoiceWHCD			=  1;			//倉庫コード
	static final int ColOkuriNo				=  2;			//送り状番号
	static final int ColMsNo					=  3;			//明細番号
	static final int ColSeq					=  4;			//引当枝番
	static final int ColItemCd					=  5;			//商品コード
	static final int ColItemName				=  6;			//商品表記名
	static final int ColLot					=  7;			//ロット
	static final int ColExpdate				=  8;			//消費期限
	static final int ColActualDate			=  9;			//入荷実績日
	static final int ColQty					= 10;			//引当数量
	static final int ColErrType				= 11;			//エラータイプ
	static final int ColErrMsg					= 12;			//エラーメッセージ
	static final int ColClDeliNo				= 13;			//荷主管理番号
	static final int ColDeliCd					= 14;			//荷届先コード
	static final int ColClDeliCd				= 15;			//荷主荷届先コード
	static final int ColDeliDepartmentCd		= 16;			//部署CD
	static final int ColDeliName01			= 17;			//荷届先名01
	static final int ColLoc					= 18;			//ロケーション
	
	public static Object[][] RtShipPlovisionFix() {
		Object[][] Rt	= {
				 {"ClCd"					,ColClCd				,"String"	,"荷主コード"			,"Key"	}
				,{"InvoiceWHCD"				,ColInvoiceWHCD		,"String"	,"倉庫コード"			,"Key"	}
				,{"OkuriNo"					,ColOkuriNo			,"int"		,"送り状番号"			,"Key"	}
				,{"MsNo"					,ColMsNo				,"int"		,"明細番号"				,"Key"	}
				,{"Seq"						,ColSeq				,"int"		,"引当枝番"				,"Key"	}
				,{"OrderItemCd"				,ColItemCd				,"String"	,"商品コード"			,""		}
				,{"OrderItemName"			,ColItemName			,"String"	,"商品表記名"			,""		}
				,{"Lot"						,ColLot				,"String"	,"ロット"				,""		}
				,{"Expdate"					,ColExpdate			,"Date"		,"消費期限"				,""		}
				,{"ActualDate"				,ColActualDate		,"Date"		,"入荷実績日"			,""		}
				,{"Qty"						,ColQty				,"int"		,"引当数量"				,""		}
				,{"ErrType"					,ColErrType			,"String"	,"エラータイプ"			,""		}
				,{"ErrMsg"					,ColErrMsg				,"String"	,"エラーメッセージ"		,""		}
				,{"ClDeliNo"				,ColClDeliNo			,"String"	,"荷主管理番号"			,""		}
				,{"DeliCd"					,ColDeliCd				,"String"	,"荷届先コード"			,""		}
				,{"ClDeliCd"				,ColClDeliCd			,"String"	,"荷主荷届先コード"		,""		}
				,{"ColDeliDepartmentCd"		,ColDeliDepartmentCd	,"String"	,"部署CD"				,""		}
				,{"ColDeliName01"			,ColDeliName01		,"String"	,"荷届先名01"			,""		}
				,{"Loc"						,ColLoc				,"String"	,"ロケーション"			,""		}
				};
		Rt = B100_LanguageControl.RtControl(Rt);
		return Rt;
	}
	
	public static Object[][] ShipPlovisionFix(String TgtWhCd,String TgtClCd,ArrayList<String> TgtOkuriNo) {
		Object[][] Rt = new Object[0][RtShipPlovisionFix().length];
		if(null==TgtWhCd||"".equals(TgtWhCd)) {TgtWhCd = A00000_Main.ClWh;}
		if(null==TgtClCd||"".equals(TgtClCd)) {TgtClCd = A00000_Main.ClCd;}
		
		if(null!=TgtOkuriNo && 0<TgtOkuriNo.size()) {
			TgtOkuriNo 	= B100_ArrayListControl.ArryListStringUniqueList(TgtOkuriNo);
			Rt = ShipPlovisionCancelmain (TgtWhCd,TgtClCd,TgtOkuriNo);
			JOptionPane.showMessageDialog(null, "引落処理終了");
		}
		return Rt;
	}
	
	private static Object[][] ShipPlovisionCancelmain (String TgtWhCd,String TgtClCd,ArrayList<String> TgtOkuriNo) {
		
		ArrayList<Integer> TgtWmsStatus = new ArrayList<Integer>();
		TgtWmsStatus.add(1);
		TgtWmsStatus.add(2);
		Object[][] OkuriHdRt	= OkuriHdRt(TgtWhCd,TgtClCd,TgtOkuriNo,TgtWmsStatus);
		
		ArrayList<String> ControlOkuriNoList = new ArrayList<String>();
		for(int i=0;i<OkuriHdRt.length;i++) {
			ControlOkuriNoList.add((String)OkuriHdRt[i][T100_OkuriHdRt.ColOkuriNo]);
		}
		
		Object[][] ShipPlovisionRt = ShipPlovisionRt(TgtWhCd,TgtClCd,ControlOkuriNoList);
		Object[][] RtShipPlovisionCancel	= RtShipPlovisionFix();
		
		Object[][] RtWork = new Object[ShipPlovisionRt.length][RtShipPlovisionCancel.length];
		
		int counter = 0;
		for(int i=0;i<TgtOkuriNo.size();i++) {
			boolean UnHitFg = true;
			for(int i01=0;i01<ShipPlovisionRt.length;i01++) {
				if(TgtOkuriNo.get(i).equals((String)ShipPlovisionRt[i01][T100_ShipPlovisionRt.ColOkuriNo])) {
					UnHitFg = false;
					i01=ShipPlovisionRt.length+1;
				}
			}
			if(UnHitFg) {
				counter = counter+1;
			}
		}
		Object[][] RtAdd = new Object[counter][RtShipPlovisionCancel.length];
		counter = 0;
		for(int i=0;i<TgtOkuriNo.size();i++) {
			boolean UnHitFg = true;
			for(int i01=0;i01<ShipPlovisionRt.length;i01++) {
				if(TgtOkuriNo.get(i).equals((String)ShipPlovisionRt[i01][T100_ShipPlovisionRt.ColOkuriNo])) {
					UnHitFg = false;
					i01=ShipPlovisionRt.length+1;
				}
			}
			if(UnHitFg) {
				RtAdd[counter][ColClCd]				= TgtOkuriNo;					//荷主コード
				RtAdd[counter][ColInvoiceWHCD]		= TgtWhCd;						//倉庫コード
				RtAdd[counter][ColOkuriNo]				= TgtOkuriNo.get(i);			//送り状番号
				RtAdd[counter][ColMsNo]				= (int)0;						//明細番号
				RtAdd[counter][ColSeq]					= (int)0;						//引当枝番
				RtAdd[counter][ColItemCd]				= "";							//商品コード
				RtAdd[counter][ColItemName]			= "";							//商品表記名
				RtAdd[counter][ColLot]					= "";							//ロット
				RtAdd[counter][ColExpdate]				= "";							//消費期限
				RtAdd[counter][ColActualDate]			= "";							//入荷実績日
				RtAdd[counter][ColQty]					= (int)0;						//引当数量
				RtAdd[counter][ColErrType]				= "NotTgt";						//エラータイプ
				RtAdd[counter][ColErrMsg]				= "引落対象ではありません";		//エラーメッセージ
				RtAdd[counter][ColClDeliNo]			= "";							//荷主管理番号
				RtAdd[counter][ColDeliCd]				= "";							//荷届先コード
				RtAdd[counter][ColClDeliCd]			= "";							//荷主荷届先コード
				RtAdd[counter][ColDeliDepartmentCd]	= "";							//部署CD
				RtAdd[counter][ColDeliName01]			= "";							//荷届先名01
				RtAdd[counter][ColLoc]					= "";							//ロケーション
				counter = counter+1;
			}
		}
		
		for(int i=0;i<ShipPlovisionRt.length;i++) {
			for(int i01=0;i01<RtShipPlovisionCancel.length;i01++) {
				String CST = (String)RtShipPlovisionCancel[i01][2];
				CST = CST.toUpperCase();
				switch(CST) {
					case"STRING":
						RtWork [i][i01]	= "";
						break;
					case"INT":
						RtWork [i][i01]	= (int)0;
						break;
					case"FLOAT":
						RtWork [i][i01]	= (float)0;
						break;
					default:
						RtWork [i][i01]	= "";
						break;
				}
			}
			
			RtWork[i][ColClCd]					= (String)ShipPlovisionRt[i][T100_ShipPlovisionRt.Colcl_cd];				//荷主コード
			RtWork[i][ColInvoiceWHCD]			= (String)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColInvoiceWHCD];		//倉庫コード
			RtWork[i][ColOkuriNo]				= (String)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColOkuriNo];				//送り状番号
			RtWork[i][ColMsNo]					= (int)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColMsNo];					//明細番号
			RtWork[i][ColSeq]					= (int)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColSeq];					//引当枝番
			RtWork[i][ColItemCd]				= (String)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColShipItemCd];			//商品コード
			RtWork[i][ColItemName]				= (String)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColOrderItemName01];	//商品表記名
			RtWork[i][ColLot]					= (String)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColShipLot];				//ロット
			RtWork[i][ColExpdate]				= (String)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColShipExpdate];		//消費期限
			RtWork[i][ColActualDate]			= (String)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColShipActualDate];		//入荷実績日
			RtWork[i][ColQty]					= (int)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColShipQty];				//引当数量
			RtWork[i][ColErrType]				= "";//エラータイプ
			RtWork[i][ColErrMsg]				= "";//エラーメッセージ
			RtWork[i][ColClDeliNo]				= (String)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColClDeliNo];			//荷主管理番号
			RtWork[i][ColDeliCd]				= (String)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColDeliCd];				//荷届先コード
			RtWork[i][ColClDeliCd]				= (String)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColClDeliCd];			//荷主荷届先コード
			RtWork[i][ColDeliDepartmentCd]	= (String)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColDeliDepartmentCd];	//部署CD
			RtWork[i][ColDeliName01]			= (String)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColDeliName01];			//荷届先名01
			RtWork[i][ColLoc]					= (String)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColShipLoc];				//ロケーション
		}
		
		PreparedStatement StockControlCreate = StockControlCreate(TgtWhCd,TgtClCd);
		ArrayList<String> ErrOkuriNoList = new ArrayList<String>();
		for(int i=0;i<RtWork.length;i++) {
			boolean FixFg = StockControl(StockControlCreate,(String)RtWork[i][ColLoc],(String)RtWork[i][ColItemCd],(String)RtWork[i][ColLot],(String)RtWork[i][ColExpdate],(String)RtWork[i][ColActualDate],(int)RtWork[i][ColQty]);
			if(!FixFg) {
				RtWork[i][ColErrType]				= "ControlErr";					//エラータイプ
				RtWork[i][ColErrMsg]				= "在庫の更新に失敗しました";	//エラーメッセージ
				ErrOkuriNoList.add((String)RtWork[i][ColOkuriNo]);
			}
		}
		ErrOkuriNoList = B100_ArrayListControl.ArryListStringUniqueList(ErrOkuriNoList);
		if(null!=ErrOkuriNoList&&0<ErrOkuriNoList.size()) {
			//在庫更新失敗していた場合、在庫更新成功している同一伝票の在庫戻す
			for(int i01=0;i01<ErrOkuriNoList.size();i01++) {
				for(int i=0;i<RtWork.length;i++) {
					if("".equals((String)RtWork[i][ColErrType])&&ErrOkuriNoList.get(i01).equals((String)RtWork[i][ColOkuriNo])) {
						boolean FixFg = StockControl(StockControlCreate,(String)RtWork[i][ColLoc],(String)RtWork[i][ColItemCd],(String)RtWork[i][ColLot],(String)RtWork[i][ColExpdate],(String)RtWork[i][ColActualDate],-1*(int)RtWork[i][ColQty]);
						RtWork[i][ColErrType]				= "ControlErr";						//エラータイプ
						RtWork[i][ColErrMsg]				= "別行で在庫の更新に失敗しました";	//エラーメッセージ
					}
				}
			}
		}
		
		try {
			if(null!=StockControlCreate) {StockControlCreate.close();}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		A100_DbConnect.close();
		
		ShipPlovisionDelete(TgtWhCd,TgtClCd,RtWork);		//引当結果データ更新
		OkuriHdStatusControl(TgtWhCd,TgtClCd,RtWork);	//送り状データステータス更新
		
		Object[][] Rt = new Object[RtWork.length+RtAdd.length][RtShipPlovisionCancel.length];
		ArrayList<String> ErrMsg = new ArrayList<String>();
		counter = 0;
		for(int i=0;i<RtAdd.length;i++) {
			for(int i01=0;i01<RtAdd[i].length;i01++) {
				Rt[counter][i01] = RtAdd[i][i01];
			}
			if(!"".equals((String)RtAdd[i][ColErrType])) {
				ErrMsg.add((String)RtAdd[i][ColErrType]+":(送り状番号:"+(String)RtAdd[i][ColOkuriNo]+"("+RtAdd[i][ColMsNo]+"-"+RtAdd[i][ColSeq]+"))"+RtAdd[i][ColErrMsg]);
			}
			counter = counter+1;
		}
		for(int i=0;i<RtWork.length;i++) {
			for(int i01=0;i01<RtWork[i].length;i01++) {
				Rt[counter][i01] = RtWork[i][i01];
			}
			if(!"".equals((String)RtWork[i][ColErrType])) {
				ErrMsg.add((String)RtWork[i][ColErrType]+":(送り状番号:"+(String)RtWork[i][ColOkuriNo]+"("+RtWork[i][ColMsNo]+"-"+RtWork[i][ColSeq]+"))"+RtWork[i][ColErrMsg]);
			}
			counter = counter+1;
		}
		
		if(null!=ErrMsg && 0<ErrMsg.size()) {
			ErrView(ErrMsg);
		}
		
		return Rt;
	}
	
	private static void OkuriHdStatusControl(String TgtWhCd,String TgtClCd,Object[][] Rt) {
		//送り状データのステータス出荷済み（3）にする
		String now_dtm = B100_DateTimeControl.dtmString2(B100_DateTimeControl.dtm()[1])[1];
		String now_Date = B100_DateTimeControl.dtmString2(B100_DateTimeControl.dtm()[1])[0];
		String tgt_table = "KT0010_OKURI_HD";
		String TgtDB = "NYANKO";
		int non_msg_fg = 1;
		
		int Counter = 0;
		for(int i=0;i<Rt.length;i++) {
			if("".equals((String)Rt[i][ColErrType])) {
				Counter = Counter+1;
			}
		}
		String[] Setcl_cd		= new String[Counter];	//荷主コード
		String[] SetInvoiceWHCD	= new String[Counter];	//倉庫コード
		String[] SetOkuriNo		= new String[Counter];	//送り状番号(WMS採番)
		String[] SetWmsStatus	= new String[Counter];	//在庫管理ステータス
		String[] SetUpdateDate	= new String[Counter];	//更新日
		String[] SetUpdateUser	= new String[Counter];	//更新者
		String[] SetUpdatePG	= new String[Counter];	//更新プログラム
		String[] SetWmsShipDate	= new String[Counter];	//倉庫出荷日
		
		Counter = 0;
		for(int i=0;i<Rt.length;i++) {
			if("".equals((String)Rt[i][ColErrType])) {
				Setcl_cd[Counter]			= TgtClCd;	//荷主コード
				SetInvoiceWHCD[Counter]		= TgtWhCd;	//倉庫コード
				SetOkuriNo[Counter]			= (String)Rt[i][ColOkuriNo];	//送り状番号(WMS採番)
				SetWmsStatus[Counter]		= "3";							//在庫管理ステータス
				SetUpdateDate[Counter]		= now_dtm;						//更新日
				SetUpdateUser[Counter]		= "(" + A00000_Main.LoginUserId + ")" + A00000_Main.LoginUserName;	//更新者
				SetUpdatePG[Counter]		= "WT100_ShipPlovisionFix";											//更新プログラム
				SetWmsShipDate[Counter]		= now_Date;	//倉庫出荷日
				Counter = Counter+1;
			}
		}
		
		Object[][] OkuriUpDateData	={
				 {"cl_cd"			,"0" ,"1" ,"Key" 	,Setcl_cd			}	//荷主コード
				,{"InvoiceWHCD" 	,"0" ,"1" ,"Key" 	,SetInvoiceWHCD		}	//倉庫コード
				,{"OkuriNo" 		,"0" ,"1" ,"Key"	,SetOkuriNo			}	//送り状番号(WMS採番)
				,{"WmsStatus" 		,"0" ,"1" ,"" 		,SetWmsStatus		}	//在庫管理ステータス
				,{"UpdateDate" 		,"0" ,"1" ,"" 		,SetUpdateDate		}	//更新日
				,{"UpdateUser" 		,"0" ,"1" ,"" 		,SetUpdateUser		}	//更新者
				,{"UpdatePG"	 	,"0" ,"1" ,"" 		,SetUpdatePG		}	//更新プログラム
				,{"WmsShipDate"	 	,"0" ,"1" ,"" 		,SetWmsShipDate		}	//倉庫出荷日
				};
		A100_InsertUpdateSQL.InsertUpdateSomeRecord(OkuriUpDateData,tgt_table,TgtDB,non_msg_fg);
	}
	
	private static void ShipPlovisionDelete(String TgtWhCd,String TgtClCd,Object[][] Rt) {
		//引き当て結果データ更新　FixFg　出荷済み(1)にする
		String now_dtm = B100_DateTimeControl.dtmString2(B100_DateTimeControl.dtm()[1])[1];
		String tgt_table = "WW0020ShipPlovision";
		String TgtDB = "WANKO";
		int non_msg_fg = 1;
		
		int Counter = 0;
		for(int i=0;i<Rt.length;i++) {
			if("".equals((String)Rt[i][ColErrType])) {
				Counter = Counter+1;
			}
		}
		
		String[] Setcl_cd		= new String[Counter];
		String[] SetInvoiceWHCD	= new String[Counter];
		String[] SetOkuriNo		= new String[Counter];
		String[] SetMsNo		= new String[Counter];
		String[] SetSeq			= new String[Counter];
		String[] SetFixFg		= new String[Counter];
		String[] SetUpdateDate	= new String[Counter];
		String[] SetUpdateUser	= new String[Counter];

		Counter = 0;
		for(int i=0;i<Rt.length;i++) {
			if("".equals((String)Rt[i][ColErrType])) {
				Setcl_cd[Counter]		= ""+Rt[i][ColClCd];
				SetInvoiceWHCD[Counter]	= ""+Rt[i][ColInvoiceWHCD];
				SetOkuriNo[Counter]		= ""+Rt[i][ColOkuriNo];
				SetMsNo[Counter]		= ""+Rt[i][ColMsNo];
				SetSeq[Counter]			= ""+Rt[i][ColSeq];
				SetFixFg[Counter]		= "1";
				SetUpdateDate[Counter]	= now_dtm;
				SetUpdateUser[Counter]	= "(" + A00000_Main.LoginUserId + ")" + A00000_Main.LoginUserName;
				
				Counter = Counter+1;
			}
		}
		
		Object[][] PlovisionUpDateData	={
				 {"cl_cd"		,"0" ,"1" ,"Key" 	,Setcl_cd}			//荷主コード
				,{"InvoiceWHCD"	,"0" ,"1" ,"Key" 	,SetInvoiceWHCD}	//倉庫コード
				,{"OkuriNo"		,"0" ,"1" ,"Key" 	,SetOkuriNo}		//送り状番号(WMS採番)
				,{"MsNo"		,"0" ,"1" ,"Key" 	,SetMsNo}			//明細番号
				,{"Seq"			,"0" ,"1" ,"Key" 	,SetSeq}			//引当枝番
				,{"FixFg"		,"0" ,"1" ,"" 		,SetFixFg}			//引落済フラグ
				,{"UpdateDate"	,"0" ,"1" ,"" 		,SetUpdateDate}		//更新日時
				,{"UpdateUser"	,"0" ,"1" ,"" 		,SetUpdateUser}		//更新者
				};
		
		A100_InsertUpdateSQL.InsertUpdateSomeRecord(PlovisionUpDateData,tgt_table,TgtDB,non_msg_fg);
	}
	
	private static boolean StockControl(PreparedStatement StockControlCreate,String TgtLoc,String TgtItemCd,String TgtLot,String TgtExpdate,String TgtActualDate,int ControlQty) {
		boolean Rt = true;
		String now_dtm = B100_DateTimeControl.dtmString2(B100_DateTimeControl.dtm()[1])[1];
		
		int ControlColQty			= 1;	//在庫数
		int ControlColShipPlanQty 	= 2;	//引当済数
		int ControlColUpdateDate  	= 3;	//更新日時
		int ControlColUpdateUser  	= 4;	//更新者
		int ControlColLoc        	= 5;	//ロケーション
		int ControlColItemCd     	= 6;	//商品コード
		int ControlColLot        	= 7;	//ロット
		int ControlColExpdate    	= 8;	//消費期限
		int ControlColActualDate 	= 9;	//入荷実績日
		
		try {
			StockControlCreate.setString(ControlColQty			,""+ControlQty);
			StockControlCreate.setString(ControlColShipPlanQty	,""+ControlQty);
			StockControlCreate.setString(ControlColUpdateDate  	,now_dtm);
			StockControlCreate.setString(ControlColUpdateUser  	,"(" + A00000_Main.LoginUserId + ")" + A00000_Main.LoginUserName);
			StockControlCreate.setString(ControlColLoc			,TgtLoc);
			StockControlCreate.setString(ControlColItemCd		,TgtItemCd);
			StockControlCreate.setString(ControlColLot			,TgtLot);
			StockControlCreate.setString(ControlColExpdate		,TgtExpdate);
			StockControlCreate.setString(ControlColActualDate	,TgtActualDate);
			StockControlCreate.executeUpdate();
		} catch (SQLException e) {
			Rt = false;
			e.printStackTrace();
		}
		return Rt;
	}
	
	private static PreparedStatement StockControlCreate(String TgtWhCd,String TgtClCd) {
		String sql = "Update "
				+ ""+A00000_Main.MySqlDefaultSchemaWANKO+".WW0015Stock"
				+ " set "
				+ "WW0015Stock.Qty =WW0015Stock.Qty-?,\n"					//在庫数
				+ "WW0015Stock.ShipPlanQty =WW0015Stock.ShipPlanQty-?,\n"	//引当済数
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
	
	private static Object[][] ShipPlovisionRt(String TgtWhCd,String TgtClCd,ArrayList<String> TgtOkuriNo){
		ArrayList<String> Searchcl_cd				= new ArrayList<String>();			//荷主コード
		ArrayList<String> SearchInvoiceWHCD			= new ArrayList<String>();			//倉庫コード
		ArrayList<String> SearchOkuriNo				= TgtOkuriNo;						//送り状番号
		ArrayList<Integer> SearchMsNoMin			= new ArrayList<Integer>();			//明細番号
		ArrayList<Integer> SearchSeqMin				= new ArrayList<Integer>();			//引当枝番
		ArrayList<Integer> SearchMsNoMax			= new ArrayList<Integer>();			//明細番号
		ArrayList<Integer> SearchSeqMax				= new ArrayList<Integer>();			//引当枝番
		ArrayList<String> SearchOrderItemCd			= new ArrayList<String>();			//商品コード
		ArrayList<String> SearchOrderItemName		= new ArrayList<String>();			//商品表記名
		ArrayList<String> SearchOrderLot			= new ArrayList<String>();			//受注ロット指定
		ArrayList<String> SearchOrderExpDateStr		= new ArrayList<String>();			//受注賞味期限指定
		ArrayList<String> SearchOrderExpDateEnd		= new ArrayList<String>();			//受注賞味期限指定
		ArrayList<Integer> SearchOrderQtyMin		= new ArrayList<Integer>();			//受注個数
		ArrayList<Integer> SearchOrderQtyMax		= new ArrayList<Integer>();			//受注個数
		ArrayList<String> SearchShipWhCd			= new ArrayList<String>();			//出荷倉庫コード
		ArrayList<String> SearchShipLoc				= new ArrayList<String>();			//ロケーション
		ArrayList<String> SearchShipItemCd			= new ArrayList<String>();			//商品コード
		ArrayList<String> SearchShipLot				= new ArrayList<String>();			//ロット
		ArrayList<String> SearchShipExpdateStr		= new ArrayList<String>();			//消費期限
		ArrayList<String> SearchShipActualDateStr	= new ArrayList<String>();			//入荷実績日
		ArrayList<String> SearchShipExpdateEnd		= new ArrayList<String>();			//消費期限
		ArrayList<String> SearchShipActualDateEnd	= new ArrayList<String>();			//入荷実績日
		ArrayList<String> SearchShipQtyMin			= new ArrayList<String>();			//引当数量
		ArrayList<String> SearchShipQtyMax			= new ArrayList<String>();			//引当数量
		ArrayList<Integer> SearchFixFg				= new ArrayList<Integer>();			//引落済フラグ
		ArrayList<Integer> SearchPackingType		= new ArrayList<Integer>();			//荷姿タイプ
		ArrayList<Integer> SearchPackingQtyMin		= new ArrayList<Integer>();			//荷姿数量
		ArrayList<Integer> SearchPackingQtyMax		= new ArrayList<Integer>();			//荷姿数量
		ArrayList<Integer> SearchBRShipQtyMin		= new ArrayList<Integer>();			//バラ数量
		ArrayList<Integer> SearchCTShipQtyMin		= new ArrayList<Integer>();			//カートン数量
		ArrayList<Integer> SearchCSShipQtyMin		= new ArrayList<Integer>();			//ケース数量
		ArrayList<Integer> SearchPLShipQtyMin		= new ArrayList<Integer>();			//パレット数量
		ArrayList<Integer> SearchBRShipQtyMax		= new ArrayList<Integer>();			//バラ数量
		ArrayList<Integer> SearchCTShipQtyMax		= new ArrayList<Integer>();			//カートン数量
		ArrayList<Integer> SearchCSShipQtyMax		= new ArrayList<Integer>();			//ケース数量
		ArrayList<Integer> SearchPLShipQtyMax		= new ArrayList<Integer>();			//パレット数量
		ArrayList<String> SearchEntryDateStr		= new ArrayList<String>();			//登録日時
		ArrayList<String> SearchUpdateDateStr		= new ArrayList<String>();			//更新日時
		ArrayList<String> SearchEntryDateEnd		= new ArrayList<String>();			//登録日時
		ArrayList<String> SearchUpdateDateEnd		= new ArrayList<String>();			//更新日時
		ArrayList<String> SearchEntryUser			= new ArrayList<String>();			//登録者
		ArrayList<String> SearchUpdateUser			= new ArrayList<String>();			//更新者
			
		ArrayList<String> SearchClDeliNo			= new ArrayList<String>();			//荷主管理番号
		ArrayList<String> SearchPlanDateStr			= new ArrayList<String>();			//出荷予定日
		ArrayList<String> SearchShipDateStr			= new ArrayList<String>();			//出荷実績日
		ArrayList<String> SearchPlanDateEnd			= new ArrayList<String>();			//出荷予定日
		ArrayList<String> SearchShipDateEnd			= new ArrayList<String>();			//出荷実績日
			
		ArrayList<String> SearchDeliCd				= new ArrayList<String>();			//荷届先コード
		ArrayList<String> SearchClDeliCd			= new ArrayList<String>();			//荷主荷届先コード
		ArrayList<String> SearchDeliDepartmentCd	= new ArrayList<String>();			//部署CD
		ArrayList<String> SearchDeliName			= new ArrayList<String>();			//荷届先名
		ArrayList<String> SearchDeliPost			= new ArrayList<String>();			//荷届先郵便番号
		ArrayList<String> SearchDeliAdd				= new ArrayList<String>();			//荷届先住所
		ArrayList<String> SearchDeliTel				= new ArrayList<String>();			//荷届先TEL
		ArrayList<String> SearchDeliFax				= new ArrayList<String>();			//荷届先FAX
		ArrayList<String> SearchDeliMail			= new ArrayList<String>();			//荷届先MAIL
		ArrayList<String> SearchDeliMunicCd			= new ArrayList<String>();			//荷届先市区町村CD
			 
		ArrayList<String> SearchClGpCD				= new ArrayList<String>();			//荷主グループCD
		ArrayList<String> SearchClCd				= new ArrayList<String>();			//荷主CD
		ArrayList<String> SearchPickupWhCd			= new ArrayList<String>();			//集荷倉庫CD
		ArrayList<String> SearchPurposeFG			= new ArrayList<String>();			//目的フラグ
		ArrayList<String> SearchSPPlanDateStr		= new ArrayList<String>();			//着日指定
		ArrayList<String> SearchSPDateStr			= new ArrayList<String>();			//着日実績
		ArrayList<String> SearchSPPlanDateEnd		= new ArrayList<String>();			//着日指定
		ArrayList<String> SearchSPDateEnd			= new ArrayList<String>();			//着日実績

		ArrayList<String> SearchCategoryCd			= new ArrayList<String>();			//商品カテゴリCD
		ArrayList<String> SearchCategoryName		= new ArrayList<String>();			//商品カテゴリ名
		ArrayList<String> SearchItemColorCd			= new ArrayList<String>();			//商品カラーコード
		ArrayList<String> SearchItemColorName		= new ArrayList<String>();			//商品カラー名
		ArrayList<String> SearchItemSizeCd			= new ArrayList<String>();			//商品サイズコード
		ArrayList<String> SearchItemSizeName		= new ArrayList<String>();			//商品サイズ名
		boolean AllSearch = false;
		
		if(null!=SearchOkuriNo && 0<SearchOkuriNo.size()) {
			SearchInvoiceWHCD.add(TgtWhCd);
			SearchClCd.add(TgtClCd);
			SearchFixFg.add(0);
		}
		
		Object[][] ShipPlovisionRt	= T100_ShipPlovisionRt.ShipPlovisionRt(
							 Searchcl_cd				//荷主コード
							,SearchInvoiceWHCD			//倉庫コード
							,SearchOkuriNo				//送り状番号
							,SearchMsNoMin				//明細番号
							,SearchSeqMin				//引当枝番
							,SearchMsNoMax				//明細番号
							,SearchSeqMax				//引当枝番
							,SearchOrderItemCd			//商品コード
							,SearchOrderItemName		//商品表記名
							,SearchOrderLot				//受注ロット指定
							,SearchOrderExpDateStr		//受注賞味期限指定
							,SearchOrderExpDateEnd		//受注賞味期限指定
							,SearchOrderQtyMin			//受注個数
							,SearchOrderQtyMax			//受注個数
							,SearchShipWhCd				//出荷倉庫コード
							,SearchShipLoc				//ロケーション
							,SearchShipItemCd			//商品コード
							,SearchShipLot				//ロット
							,SearchShipExpdateStr		//消費期限
							,SearchShipActualDateStr	//入荷実績日
							,SearchShipExpdateEnd		//消費期限
							,SearchShipActualDateEnd	//入荷実績日
							,SearchShipQtyMin			//引当数量
							,SearchShipQtyMax			//引当数量
							,SearchFixFg				//引落済フラグ
							,SearchPackingType			//荷姿タイプ
							,SearchPackingQtyMin		//荷姿数量
							,SearchPackingQtyMax		//荷姿数量
							,SearchBRShipQtyMin			//バラ数量
							,SearchCTShipQtyMin			//カートン数量
							,SearchCSShipQtyMin			//ケース数量
							,SearchPLShipQtyMin			//パレット数量
							,SearchBRShipQtyMax			//バラ数量
							,SearchCTShipQtyMax			//カートン数量
							,SearchCSShipQtyMax			//ケース数量
							,SearchPLShipQtyMax			//パレット数量
							,SearchEntryDateStr			//登録日時
							,SearchUpdateDateStr		//更新日時
							,SearchEntryDateEnd			//登録日時
							,SearchUpdateDateEnd		//更新日時
							,SearchEntryUser			//登録者
							,SearchUpdateUser			//更新者
								
							,SearchClDeliNo				//荷主管理番号
							,SearchPlanDateStr			//出荷予定日
							,SearchShipDateStr			//出荷実績日
							,SearchPlanDateEnd			//出荷予定日
							,SearchShipDateEnd			//出荷実績日
								
							,SearchDeliCd				//荷届先コード
							,SearchClDeliCd				//荷主荷届先コード
							,SearchDeliDepartmentCd		//部署CD
							,SearchDeliName				//荷届先名
							,SearchDeliPost				//荷届先郵便番号
							,SearchDeliAdd				//荷届先住所
							,SearchDeliTel				//荷届先TEL
							,SearchDeliFax				//荷届先FAX
							,SearchDeliMail				//荷届先MAIL
							,SearchDeliMunicCd			//荷届先市区町村CD
								 
							,SearchClGpCD				//荷主グループCD
							,SearchClCd					//荷主CD
							,SearchPickupWhCd			//集荷倉庫CD
							,SearchPurposeFG			//目的フラグ
							,SearchSPPlanDateStr		//着日指定
							,SearchSPDateStr			//着日実績
							,SearchSPPlanDateEnd		//着日指定
							,SearchSPDateEnd			//着日実績

							,SearchCategoryCd			//商品カテゴリCD
							,SearchCategoryName			//商品カテゴリ名
							,SearchItemColorCd			//商品カラーコード
							,SearchItemColorName		//商品カラー名
							,SearchItemSizeCd			//商品サイズコード
							,SearchItemSizeName			//商品サイズ名
							,AllSearch
							);
		return ShipPlovisionRt;
	}
	
	private static Object[][] OkuriHdRt(String TgtWhCd,String TgtClCd,ArrayList<String>TgtOkuriNo,ArrayList<Integer> TgtWmsStatus) {
		ArrayList<String> SearchInvoiceWHCD			= new ArrayList<String>();			//倉庫CD
		ArrayList<String> SearchClGpCD				= new ArrayList<String>();			//荷主グループCD
		ArrayList<String> SearchClCd				= new ArrayList<String>();			//荷主CD
		ArrayList<String> SearchOkuriNo				= TgtOkuriNo;						//送り状番号
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
		
		if(null!=SearchOkuriNo && 0<SearchOkuriNo.size()) {
			SearchInvoiceWHCD.add(TgtWhCd);
			SearchClCd.add(TgtClCd);
			SearchWmsStatus=TgtWmsStatus;
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
		String FLD_PATH = A00000_Main.MainFLD+"\\ShipPlovisionFix";
		B100_FolderCheck.FLD_CHECK(FLD_PATH);
		FLD_PATH = A00000_Main.MainFLD+"\\ShipPlovisionControl\\ShipPlovisionFix";
		B100_FolderCheck.FLD_CHECK(FLD_PATH);
		FLD_PATH = A00000_Main.MainFLD+"\\ShipPlovisionControl\\ShipPlovisionFix\\Err";
		B100_FolderCheck.FLD_CHECK(FLD_PATH);
		FLD_PATH = A00000_Main.MainFLD+"\\ShipPlovisionControl\\ShipPlovisionFix\\BK";
		B100_FolderCheck.FLD_CHECK(FLD_PATH);
		
		//ファイルに出力
		String NowDTM=B100_DateTimeControl.dtmString2(B100_DateTimeControl.dtm()[1])[1].replace(" ", "").replace("/", "").replace(":", "");
		
		FLD_PATH = A00000_Main.MainFLD+"\\ShipPlovisionControl\\ShipPlovisionFix\\Err";
		
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