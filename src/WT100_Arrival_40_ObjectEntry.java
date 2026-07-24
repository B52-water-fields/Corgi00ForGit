import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class WT100_Arrival_40_ObjectEntry{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	
	static final int ColSetFg					=  0;	//ゼロ固定変更すると絶対やけどします
	static final int ColSetArrNo				=  1;
	static final int ColSetMsNo				=  2;
	static final int ColSetItemCd				=  3;
	static final int ColSetItemName			=  4;
	static final int ColSetlot					=  5;
	static final int ColSetExpDate			=  6;
	static final int ColSetPlanQty			=  7;
	static final int ColSetRemainingPlanQty	=  8;
	static final int ColSetEntryLot			=  9;
	static final int ColSetEntryExpDate		= 10;
	static final int ColSetEntryQty			= 11;
	static final int ColSetEntryStoreLoc		= 12;
	static final int ColSetRecmendLoc			= 13;
	static final int ColSetRecmendLocMst		= 14;
	static final int ColSetClWh				= 15;
	static final int ColSetClCd				= 16;
	static final int ColSetPlanDate			= 17;
	static final int ColSetSpCd				= 18;
	static final int ColSetSpName01			= 19;
	static final int ColSetClArrNo			= 20;
	static final int ColSetActualQty			= 21;
	
	private static void ArrivalExcelEntry(int x,int y,String TgtFilePath) {
		A00000_Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		
		RenewFg = false;

		final JFrame main_fm = B100_FrameParts.FrameCreate(x,y,600,200,"Corgi00入荷実績登録（エクセル）WT100_Arrival_31_ExcelEntryInPut","NK");
		JLabel userinfo = B100_FrameParts.UserInfo();
		JButton exit_btn = B100_FrameParts.ExitBtn();
		JButton entry_btn = B100_FrameParts.EntryBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		main_fm.add(entry_btn);
		
		final String[] SheetList = B100_ExcellControl.ExcellSheetList(TgtFilePath);
		
		JLabel LB_SheetList				= B100_FrameParts.JLabelSet(		 20, 45,300,20,"登録するシートを選択してください"		,11,0);
		final JComboBox   TB_SheetList	= B100_FrameParts.JComboBoxSet( 	 20, 70,250,20,SheetList,11);	//シート一覧
		
		main_fm.add(LB_SheetList);
		main_fm.add(TB_SheetList);	//シート一覧
		
		main_fm.setVisible(true);
		RenewFg = true;
		
		//登録ボタン押下時の挙動
		entry_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				String SheetName = SheetList[TB_SheetList.getSelectedIndex()];
				
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				ArrivalExcelEntryMain(0,0,TgtFilePath,SheetName);
			}
		});
		
		//EXITボタン押下時の挙動
		exit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				WT100_ArrivalPlan_00_Search.ArrivalPlanSearch(0, 0);
			}
		});
	}
	
	public static void ArrivalExcelEntryMain(int x,int y,String TgtFilePath,String SheetName){
		//対象エクセルとシート名を受け取ってオブジェクト生成

		String[] Title = B100_RtObjectCreate.RtTitleName(WT100_Arrival_30_ExcelEntryOutPut.RtArrivalExcelOutPut());
		
		final Object[][] NeedCol = {
				 {Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetClWh]					,1	,-1,WT100_Arrival_40_ObjectEntry.ColSetClWh}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetClCd]					,1	,-1,WT100_Arrival_40_ObjectEntry.ColSetClCd}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetPlanDate]				,2	,-1,WT100_Arrival_40_ObjectEntry.ColSetPlanDate}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetSpCd]					,1	,-1,WT100_Arrival_40_ObjectEntry.ColSetSpCd}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetSpName01]				,1	,-1,WT100_Arrival_40_ObjectEntry.ColSetSpName01}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetClArrNo]				,1	,-1,WT100_Arrival_40_ObjectEntry.ColSetClArrNo}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetArrNo]					,1	,-1,WT100_Arrival_40_ObjectEntry.ColSetArrNo}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetMsNo]					,0	,-1,WT100_Arrival_40_ObjectEntry.ColSetMsNo}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetItemCd]				,1	,-1,WT100_Arrival_40_ObjectEntry.ColSetItemCd}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetItemName]				,1	,-1,WT100_Arrival_40_ObjectEntry.ColSetItemName}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetlot]					,1	,-1,WT100_Arrival_40_ObjectEntry.ColSetlot}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetExpDate]				,2	,-1,WT100_Arrival_40_ObjectEntry.ColSetExpDate}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetPlanQty]				,0	,-1,WT100_Arrival_40_ObjectEntry.ColSetPlanQty}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetActualQty]			,0	,-1,WT100_Arrival_40_ObjectEntry.ColSetActualQty}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetRemainingPlanQty]	,0	,-1,WT100_Arrival_40_ObjectEntry.ColSetRemainingPlanQty}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetRecmendLoc]			,1	,-1,WT100_Arrival_40_ObjectEntry.ColSetRecmendLoc}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetRecmendLocMst]		,1	,-1,WT100_Arrival_40_ObjectEntry.ColSetRecmendLocMst}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColEntryLot]					,1	,-1,WT100_Arrival_40_ObjectEntry.ColSetEntryLot}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColEntryExpDate]			,2	,-1,WT100_Arrival_40_ObjectEntry.ColSetEntryExpDate}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColEntryQty]					,0	,-1,WT100_Arrival_40_ObjectEntry.ColSetEntryQty}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColEntryStoreLoc]			,1	,-1,WT100_Arrival_40_ObjectEntry.ColSetEntryStoreLoc}
		};//フィールド名,フィールドタイプ(0:数値 1:文字列 2:日付時刻),基本のカラム(ゼロスタート),基本のカラム位置※カラム位置は後で読み込んだエクセルの1行目でフィールド名比較して更新されます
		//ヘッダ行取得⇒フィールド名判定
		//必要フィールドなければシート選択に戻る
		Object[][] HeaderRead = B100_ExcellControl.ExcellRead2(TgtFilePath,SheetName,1,0);
		boolean ErrFg = false;
		
		if(null==HeaderRead||0==HeaderRead.length) {
			ErrFg = true;
		}else {
			for(int i01=0;i01<NeedCol.length;i01++) {
				boolean UnHitFg = true;
				
				for(int i02=0;i02<HeaderRead[0].length;i02++) {
					if(((String)NeedCol[i01][0]).equals(""+HeaderRead[0][i02])) {
						UnHitFg = false;
						NeedCol[i01][2] = i02;
						
						i02=1+HeaderRead[0].length;
					}
				}
				if(UnHitFg) {
					ErrFg = true;
				}
			}
		}
		if(ErrFg) {
			String Msg = "ヘッダ行で取込ファイルのレイアウト判別ができませんでした。\n確認しやがれください\n";
			for(int i=0;i<NeedCol.length;i++) {
				if(0<i&&0==i%5) {
					Msg = Msg + (String)NeedCol[i][0] + ",\n";
				}else {
					Msg = Msg + (String)NeedCol[i][0] + ",";
				}
			}
			Msg = Msg+"\nがヘッダに必要です";
			
			JOptionPane.showMessageDialog(null, Msg);
			ArrivalExcelEntry(x,y,TgtFilePath);
		}else {
			int[] ClmnType = new int[HeaderRead[0].length];
			for(int i=0;i<ClmnType.length;i++) {ClmnType[i]=1;}
			
			for(int i01=0;i01<NeedCol.length;i01++) {
				ClmnType[(int)NeedCol[i01][2]]=(int)NeedCol[i01][1];
			}
			Object[][] ExcellRead = B100_ExcellControl.ExcellRead(TgtFilePath,SheetName,ClmnType,true);
			Object[][] SetOb = new Object[NeedCol.length+1][ExcellRead.length];
			
			if(0<ExcellRead.length&&ClmnType.length<=ExcellRead[0].length) {
				for(int i=0;i<ExcellRead.length;i++) {
					SetOb[i][ 0] = false;
					
					for(int i01=0;i01<NeedCol.length;i01++) {
						SetOb[i][(int)NeedCol[i01][3]] = ExcellRead[i][(int)NeedCol[i01][2]];
					}
				}
			}
			ArrivalObjectEntryMain(0,0,TgtFilePath,SetOb);
		}
		
	}
	
	public static Object[][] RtArrivalObjectEntryMainLayout(){
		Object[][] Rt = {
				/*
				static final int ColSetFg					=  0;	//ゼロ固定変更すると絶対やけどします
				static final int ColSetArrNo				=  1;
				static final int ColSetMsNo				=  2;
				static final int ColSetItemCd				=  3;
				static final int ColSetItemName			=  4;
				static final int ColSetlot					=  5;
				static final int ColSetExpDate			=  6;
				static final int ColSetPlanQty			=  7;
				static final int ColSetRemainingPlanQty	=  8;
				static final int ColSetEntryLot			=  9;
				static final int ColSetEntryExpDate		= 10;
				static final int ColSetEntryQty			= 11;
				static final int ColSetEntryStoreLoc		= 12;
				static final int ColSetRecmendLoc			= 13;
				static final int ColSetRecmendLocMst		= 14;
				static final int ColSetClWh				= 15;
				static final int ColSetClCd				= 16;
				static final int ColSetPlanDate			= 17;
				static final int ColSetSpCd				= 18;
				static final int ColSetSpName01			= 19;
				static final int ColSetClArrNo			= 20;
				static final int ColSetActualQty			= 21;
					 
					 
					 {"GetFg"				,ColGetClWh				,"String"	,"Fg" 				,"Key"}
					,{"GetClWh"				,ColGetClWh				,"String"	,"担当倉庫" 				,"Key"}
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
					,{"GetActualQty"		,ColGetActualQty			,"int"		,"入荷済数量" 				,""}
					,{"GetRemainingPlanQty"	,ColGetRemainingPlanQty	,"int"		,"予定残数量" 				,""}
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
					*/
					};
		return Rt;
	}

	public static void ArrivalObjectEntryMain(int x,int y,String TgtFilePath,Object[][] TgtData){
		A00000_Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		
		NumberFormat ni = NumberFormat.getNumberInstance();
		
		RenewFg = false;
		final JFrame main_fm = B100_FrameParts.FrameCreate(x,y,1300,800,"Corgi00入荷実績登録（エクセル）WT100_Arrival_31_ExcelEntryInPut","NK");
		JLabel userinfo = B100_FrameParts.UserInfo();
		JButton exit_btn = B100_FrameParts.ExitBtn();
		JButton entry_btn = B100_FrameParts.EntryBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		main_fm.add(entry_btn);
		
		final Object[][] NeedCol = {
				 {Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetClWh]					,1	,-1,ColSetClWh}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetClCd]					,1	,-1,ColSetClCd}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetPlanDate]				,2	,-1,ColSetPlanDate}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetSpCd]					,1	,-1,ColSetSpCd}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetSpName01]				,1	,-1,ColSetSpName01}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetClArrNo]				,1	,-1,ColSetClArrNo}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetArrNo]					,1	,-1,ColSetArrNo}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetMsNo]					,0	,-1,ColSetMsNo}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetItemCd]				,1	,-1,ColSetItemCd}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetItemName]				,1	,-1,ColSetItemName}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetlot]					,1	,-1,ColSetlot}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetExpDate]				,2	,-1,ColSetExpDate}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetPlanQty]				,0	,-1,ColSetPlanQty}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetActualQty]			,0	,-1,ColSetActualQty}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetRemainingPlanQty]	,0	,-1,ColSetRemainingPlanQty}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetRecmendLoc]			,1	,-1,ColSetRecmendLoc}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetRecmendLocMst]		,1	,-1,ColSetRecmendLocMst}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColEntryLot]					,1	,-1,ColSetEntryLot}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColEntryExpDate]			,2	,-1,ColSetEntryExpDate}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColEntryQty]					,0	,-1,ColSetEntryQty}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColEntryStoreLoc]			,1	,-1,ColSetEntryStoreLoc}
		};
		
		
		JLabel LB_SheetList	= B100_FrameParts.JLabelSet(	10, 40,540,20,"以下のデータを登録しようとしています 格納先が特に指定されていない場合入荷時ロケに在庫増えます",11,0);
		main_fm.add(LB_SheetList);
		
		String[] columnNames01 = new String[NeedCol.length+1];
		columnNames01[0] = "Fg";
		for(int i=0;i<NeedCol.length;i++) {columnNames01[(int)NeedCol[i][3]] = (String)NeedCol[i][0];}
		
		//編集可能カラムの指定
		int[] RenewTgtCol = {ColSetFg
							,ColSetEntryLot
							,ColSetEntryExpDate
							,ColSetEntryQty
							,ColSetEntryStoreLoc
							};
		B100_TableControl.RenewTgt = new int[RenewTgtCol.length+1];
		for(int i=0;i<RenewTgtCol.length;i++) {
			B100_TableControl.RenewTgt[i] = RenewTgtCol[i];
		}

		final DefaultTableModel MainFmTableModel = new B100_TableControl.MyTableModel01(columnNames01,0);
		
		final JTable tb01 = new JTable(MainFmTableModel);
		tb01.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tb01.setRowHeight(20*A00000_Main.Mul/A00000_Main.Div);
		tb01.setFont(new Font(A00000_Main.DefaultFont, Font.PLAIN, 12*A00000_Main.Mul/A00000_Main.Div));
		
		DefaultTableColumnModel columnModel01
		= (DefaultTableColumnModel)tb01.getColumnModel();
		
		//列幅初期設定 表示位置設定
		TableColumn column = null;
		column = columnModel01.getColumn( 0);	column.setPreferredWidth( 30*A00000_Main.Mul/A00000_Main.Div);
		for(int i=1;i<NeedCol.length;i++) {
			boolean RenewFg = false;
			for(int i01=0;i01<RenewTgtCol.length;i01++) {
				if((int)NeedCol[i][3]==RenewTgtCol[i01]) {
					RenewFg=true;
					i01=RenewTgtCol.length+1;
				}
			}
			if(RenewFg) {
				if(0==(int)NeedCol[i][1]) {
					column = columnModel01.getColumn((int)NeedCol[i][3]);	column.setPreferredWidth(100*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.rightCellRendererEntry());	
				}else {
					column = columnModel01.getColumn((int)NeedCol[i][3]);	column.setPreferredWidth(100*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.leftCellRendererEntry());	
				}
			}else {
				if(0==(int)NeedCol[i][1]) {
					column = columnModel01.getColumn((int)NeedCol[i][3]);	column.setPreferredWidth(100*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.rightCellRenderer());	
				}else {
					column = columnModel01.getColumn((int)NeedCol[i][3]);	column.setPreferredWidth(100*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.leftCellRenderer());	
				}
			}
		}
		JScrollPane scpn01 = B100_FrameParts.JScrollPaneSet(10,65,1260,250,tb01);
		main_fm.add(scpn01);
		
		//入荷予定残数が全部予定通りに入荷した情報にするボタン
		JLabel scpnMsg				= B100_FrameParts.JLabelSet(1140,325,130,20,"↑"	,11,2);
		main_fm.add(scpnMsg);
		
		JButton PlanSameBtn 		= B100_FrameParts.AnyBtn(350,"予定=実績",10);
		main_fm.add(PlanSameBtn);
		
		JButton MstLocEntryBtn 		= B100_FrameParts.AnyBtn(400,"MST推奨ロケ",10);
		main_fm.add(MstLocEntryBtn);
		
		JButton StockLocEntryBtn 	= B100_FrameParts.AnyBtn(425,"在庫推奨ロケ",10);
		main_fm.add(StockLocEntryBtn);
		
		JButton NKLocEntryBtn 		= B100_FrameParts.AnyBtn(450,"入荷時ロケ",10);
		main_fm.add(NKLocEntryBtn);
		
		JButton LocClearBtn 		= B100_FrameParts.AnyBtn(475,"ロケクリア",10);
		main_fm.add(LocClearBtn);
		
		final Object[] LocSerachSet	= WT200_LocSearchSubFm.LocSearchSubFm(main_fm.getX()+120,main_fm.getY()+30,A00000_Main.ClWh,A00000_Main.ClCd,"NK");
		
		JLabel LB_RowNo				= B100_FrameParts.JLabelSet(  0,325,130,20,"RowNo:"				,11,1);
		JLabel LB_PlanDate			= B100_FrameParts.JLabelSet(230,325,130,20,"入荷予定日:"			,11,1);
		JLabel LB_ArrNo				= B100_FrameParts.JLabelSet(460,325,130,20,"入荷予定番号:"			,11,1);
		JLabel LB_MsNo				= B100_FrameParts.JLabelSet(690,325,130,20,"明細番号:"				,11,1);
		JLabel LB_ClWh				= B100_FrameParts.JLabelSet(  0,350,130,20,"担当倉庫:"				,11,1);
		JLabel LB_ClCd				= B100_FrameParts.JLabelSet(  0,375,130,20,"荷主:"					,11,1);
		JLabel LB_SpCd				= B100_FrameParts.JLabelSet(  0,400,130,20,"仕入先:"				,11,1);
		
		JLabel LB_ItemCd			= B100_FrameParts.JLabelSet(  0,450,130,20,"商品:"					,11,1);
		
		
		JLabel LB_lot				= B100_FrameParts.JLabelSet(  0,475,130,20,"予定ロット:"			,11,1);
		JLabel LB_ExpDate			= B100_FrameParts.JLabelSet(  0,500,130,20,"予定賞味期限:"			,11,1);
		JLabel LB_RemainingPlanQty	= B100_FrameParts.JLabelSet(  0,525,130,20,"入荷予定残:"			,11,1);
		JLabel LB_RecmendLoc		= B100_FrameParts.JLabelSet(  0,550,130,20,"推奨ロケ（在庫考慮）:"	,11,1);
		JLabel LB_RecmendLocMst		= B100_FrameParts.JLabelSet(  0,575,130,20,"推奨ロケ（マスタ）:"	,11,1);
		
		JLabel LB_EntryLot			= B100_FrameParts.JLabelSet(230,475,130,20,"登録ロット:"			,11,1);
		JLabel LB_EntryExpDate		= B100_FrameParts.JLabelSet(230,500,130,20,"登録賞味期限:"			,11,1);
		JLabel LB_EntryQty			= B100_FrameParts.JLabelSet(230,525,130,20,"登録数量:"				,11,1);
		JLabel LB_EntryStoreLoc		= B100_FrameParts.JLabelSet(230,550,130,20,"格納先ロケ:"			,11,1);
		
		
		JLabel LB_ClArrNo			= B100_FrameParts.JLabelSet(690,350,130,20,"荷主入荷予定番号:"		,11,1);
		JLabel LB_PlanQty			= B100_FrameParts.JLabelSet(690,375,130,20,"予定数量:"				,11,1);
		JLabel LB_ActualQty			= B100_FrameParts.JLabelSet(690,400,130,20,"入荷済数量:"			,11,1);
		
		final JTextField TB_RowNo						= B100_FrameParts.JTextFieldSet(				130,325,100,20,"-1",11,1);									//RowNo
		final JTextField TB_PlanDate					= B100_FrameParts.JTextFieldSet(				360,325,100,20,"",11,0);									//入荷予定日
		final JTextField TB_ArrNo						= B100_FrameParts.JTextFieldSet(				590,325,100,20,"",11,0);									//入荷予定番号
		final JTextField TB_MsNo						= B100_FrameParts.JTextFieldSet(				820,325,100,20,"",11,1);									//明細番号
		final JComboBox TB_ClWh							= B100_FrameParts.JComboBoxSet(				130,350,300,20,B100_DefaultVariable.WhList[0],11);			//担当倉庫
		final JComboBox TB_ClCd							= B100_FrameParts.JComboBoxSet(				130,375,300,20,B100_DefaultVariable.ClList[0],11);			//荷主
		final JComboBox TB_SpCd							= B100_FrameParts.JComboBoxSet(				130,400,300,20,B100_DefaultVariable.SupplierList[0],11);	//仕入先
		
		final JTextField TB_ItemCd						= B100_FrameParts.JTextFieldSet(				130,450,100,20,"",11,0);									//商品
		final JTextField TB_ItemName					= B100_FrameParts.JTextFieldSet(				240,450,300,20,"",11,0);									//商品名
		
		final JTextField TB_lot							= B100_FrameParts.JTextFieldSet(				130,475,100,20,"",11,0);									//予定ロット
		final JFormattedTextField TB_ExpDate			= B100_FrameParts.JFormattedTextFieldSet(	130,500,100,20,"",11,0,"YYYY/MM/DD");						//予定賞味期限
		final JFormattedTextField TB_RemainingPlanQty	= B100_FrameParts.JFormattedTextFieldSet(	130,525,100,20,"",11,1,"#,###");							//入荷予定残
		final JTextField TB_RecmendLoc					= B100_FrameParts.JTextFieldSet(				130,550,100,20,"",11,0);									//推奨ロケ（在庫考慮）
		final JTextField TB_RecmendLocMst				= B100_FrameParts.JTextFieldSet(				130,575,100,20,"",11,0);									//推奨ロケ（マスタ）
		
		JButton MsPlanSameBtn							= B100_FrameParts.BtnSet(						130,600,100,20,"予定通り"		,11);
		JButton MsStockLocEntryBtn 						= B100_FrameParts.BtnSet(						130,650,100,20,"在庫推奨ロケ"	,10);
		JButton MsMstLocEntryBtn 						= B100_FrameParts.BtnSet(						130,675,100,20,"MST推奨ロケ"	,10);
		
		
		final JTextField TB_EntryLot					= B100_FrameParts.JTextFieldSet(				360,475,100,20,"",11,0);									//登録ロット
		final JFormattedTextField TB_EntryExpDate		= B100_FrameParts.JFormattedTextFieldSet(	360,500,100,20,"",11,0,"YYYY/MM/DD");						//登録賞味期限
		final JFormattedTextField TB_EntryQty			= B100_FrameParts.JFormattedTextFieldSet(	360,525,100,20,"",11,1,"#,###");							//登録数量
		final JTextField TB_EntryStoreLoc				= B100_FrameParts.JTextFieldSet(				360,550,100,20,"",11,0);									//格納先ロケ
		JButton LocSearchBtn							= B100_FrameParts.BtnSet(						470,550, 90,20,"ロケ検索"	,11);
		JButton MsEntryBtn								= B100_FrameParts.BtnSet(						360,575, 90,20,"明細に反映"	,11);
		
		final JTextField TB_ClArrNo						= B100_FrameParts.JTextFieldSet(				820,350,100,20,"",11,0);									//荷主入荷予定番号
		final JFormattedTextField TB_PlanQty			= B100_FrameParts.JFormattedTextFieldSet(	820,375,100,20,"",11,1,"#,###");							//予定数量
		final JFormattedTextField TB_ActualQty			= B100_FrameParts.JFormattedTextFieldSet(	820,400,100,20,"",11,1,"#,###");							//入荷済数量
		
		final JCheckBox ShortageSplit					= B100_FrameParts.JCheckBoxSet(				960,625,300,20,"入荷不足分は分納待ちにする"				,11);
		final JCheckBox ZeroEntry						= B100_FrameParts.JCheckBoxSet(				960,650,300,20,"入荷数量ゼロの伝票も実績として扱う"		,11);
		final JCheckBox OverErr							= B100_FrameParts.JCheckBoxSet(				960,675,300,20,"過剰入荷は拒否する"						,11);
		ShortageSplit.setSelected(true);
		ZeroEntry.setSelected(true);
		OverErr.setSelected(true);
		main_fm.add(ShortageSplit);
		main_fm.add(ZeroEntry);
		main_fm.add(OverErr);
		
		TB_EntryLot.setBackground(B100_FrameParts.SelectColer("Entry"));
		TB_EntryExpDate.setBackground(B100_FrameParts.SelectColer("Entry"));
		TB_EntryQty.setBackground(B100_FrameParts.SelectColer("Entry"));
		TB_EntryStoreLoc.setBackground(B100_FrameParts.SelectColer("Entry"));
		
		TB_RowNo.setEditable(false);
		TB_ClWh.setEnabled(false);
		TB_ClCd.setEnabled(false);
		TB_SpCd.setEnabled(false);
		TB_PlanDate.setEditable(false);
		TB_ArrNo.setEditable(false);
		TB_MsNo.setEditable(false);
		TB_ItemCd.setEditable(false);
		TB_ItemName.setEditable(false);
		
		TB_lot.setEditable(false);
		TB_ExpDate.setEditable(false);
		TB_RemainingPlanQty.setEditable(false);
		TB_RecmendLoc.setEditable(false);
		TB_RecmendLocMst.setEditable(false);
		
		TB_EntryLot.setEditable(true);
		TB_EntryExpDate.setEditable(true);
		TB_EntryQty.setEditable(true);
		TB_EntryStoreLoc.setEditable(true);
		
		TB_ClArrNo.setEditable(false);
		TB_PlanQty.setEditable(false);
		TB_ActualQty.setEditable(false);
		
		TB_ClWh.setSelectedIndex(		B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.WhList[1]	,A00000_Main.ClWh,true));			//ヘッダ担当倉庫
		TB_ClCd.setSelectedIndex(		B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.ClList[1]	,A00000_Main.ClCd,true));			//ヘッダ荷主CD
		
		
		main_fm.add(LB_RowNo);
		main_fm.add(LB_ClWh);
		main_fm.add(LB_ClCd);
		main_fm.add(LB_SpCd);
		
		main_fm.add(LB_PlanDate);
		main_fm.add(LB_ClArrNo);
		main_fm.add(LB_ArrNo);
		main_fm.add(LB_MsNo);
		main_fm.add(LB_ItemCd);
		main_fm.add(LB_PlanQty);
		main_fm.add(LB_ActualQty);
		main_fm.add(LB_lot);
		main_fm.add(LB_ExpDate);
		main_fm.add(LB_RemainingPlanQty);
		main_fm.add(LB_RecmendLoc);
		main_fm.add(LB_RecmendLocMst);
		
		main_fm.add(LB_EntryLot);
		main_fm.add(LB_EntryExpDate);
		main_fm.add(LB_EntryQty);
		main_fm.add(LB_EntryStoreLoc);
		
		main_fm.add(TB_RowNo);
		main_fm.add(TB_ClWh);
		main_fm.add(TB_ClCd);
		main_fm.add(TB_SpCd);
		main_fm.add(TB_PlanDate);
		main_fm.add(TB_ArrNo);
		main_fm.add(TB_MsNo);
		main_fm.add(TB_ItemCd);
		main_fm.add(TB_ItemName);
		main_fm.add(MsPlanSameBtn);
		main_fm.add(MsMstLocEntryBtn);
		main_fm.add(MsStockLocEntryBtn);
		
		main_fm.add(TB_lot);
		main_fm.add(TB_ExpDate);
		main_fm.add(TB_RemainingPlanQty);
		main_fm.add(TB_RecmendLoc);
		main_fm.add(TB_RecmendLocMst);
		main_fm.add(LocSearchBtn);
		main_fm.add(MsEntryBtn);
		
		main_fm.add(TB_EntryLot);
		main_fm.add(TB_EntryExpDate);
		main_fm.add(TB_EntryQty);
		main_fm.add(TB_EntryStoreLoc);
		
		main_fm.add(TB_ClArrNo);
		main_fm.add(TB_PlanQty);
		main_fm.add(TB_ActualQty);
		
		ArrayList<String> ErrMsg = ErrCheck(MainFmTableModel,tb01);
		
		if(null!=ErrMsg && 0<ErrMsg.size()) {
			ErrView(ErrMsg,"Data");
			
			SetX=main_fm.getX();
			SetY=main_fm.getY();

			main_fm.setVisible(false);
			main_fm.dispose();
			WT100_ArrivalPlan_00_Search.ArrivalPlanSearch(0, 0);
		}else {
			int RowCount = MainFmTableModel.getRowCount();
			if(0<RowCount) {
				B100_TableControl.AddSortON(tb01,MainFmTableModel);
			}
			main_fm.setVisible(true);
		}

		RenewFg = true;
		
		//登録ボタン押下時の挙動
		entry_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					ArrayList<String> ErrMsg	= ErrCheck(MainFmTableModel,tb01);
					if(null!=ErrMsg&&0<ErrMsg.size()) {
						ErrView(ErrMsg,"Data");
					}
					int RowNo				= B100_TextControl.TextToInt(TB_RowNo.getText());
					boolean KickFg = true;
					if(null==ErrMsg||0==ErrMsg.size()) {
					}else {
						KickFg = false;
					}
					if(0>RowNo) {
					}else {
						JOptionPane.showMessageDialog(null, "メンテナンス中の行があるようだが？");
						KickFg = false;
					}
					
					
					if(KickFg) {
						int RowCount = MainFmTableModel.getRowCount();
						ArrayList<String> SearchArrNo = new ArrayList<String>();
						
						for(int i=0;i<RowCount;i++) {
							SearchArrNo.add(""+MainFmTableModel.getValueAt(i, ColSetArrNo));
						}
						Object[][] ArrivalPlanHdRt	= ArrivalPlanHdRt(A00000_Main.ClWh,A00000_Main.ClCd,SearchArrNo);
						Object[][] ArrivalPlanMsRt	= ArrivalPlanMsRt(A00000_Main.ClWh,A00000_Main.ClCd,SearchArrNo);
						Object[][] ArrivalHdRt		= ArrivalHdRt(A00000_Main.ClWh,A00000_Main.ClCd,SearchArrNo);
						Object[][] ArrivalMsRt		= ArrivalMsRt(A00000_Main.ClWh,A00000_Main.ClCd,SearchArrNo);
						
						int ColHdPlanArrNo				=  0;
						int ColHdPlanClWh				=  1;
						int ColHdPlanClCd				=  2;
						int ColHdPlanSpCd				=  3;
						int ColHdPlanPlanDate			=  4;
						int ColHdPlanQty				=  5;
						int ColHdPlanActualQty			=  6;
						int ColHdPlanRemainingPlanQty	=  7;
						int ColHdPlanEntryQty			=  8;
						int ColHdPlanCompleteFg			=  9;
						
						
						int ColMsPlanArrNo				=  0;
						int ColMsPlanMsNo				=  1;
						int ColMsPlanItemCd				=  2;
						int ColMsPlanItemName			=  3;
						int ColMsPlanlot				=  4;
						int ColMsPlanExpDate			=  5;
						int ColMsPlanPlanQty			=  6;
						int ColMsPlanActualQty			=  7;
						int ColMsPlanRemainingPlanQty	=  8;
						int ColMsPlanEntryQty			=  9;
						int ColMsPlanClWh				= 10;
						int ColMsPlanClCd				= 11;
						int ColMsPlanPlanDate			= 12;
						int ColMsPlanSpCd				= 13;
						int ColHdPlanEntryFg			= 14;
						
						Object[][] CheckPlanHdData = new Object[ArrivalPlanHdRt.length][10];
						Object[][] CheckPlanMsData = new Object[ArrivalPlanMsRt.length][15];
						
						for(int i=0;i<ArrivalPlanMsRt.length;i++) {
							String GetClWh			= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColClWh];			//ヘッダ担当倉庫
							String GetClCd			= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColClCd];			//ヘッダ荷主CD
							String GetCLName01		= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColCLName01];		//ヘッダ荷主名
							String GetClGpCD		= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColClGpCD];			//ヘッダ荷主グループCD
							String GetCLGpName01	= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColCLGpName01];		//ヘッダ荷主グループ標記名
							String GetArrNo			= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColArrNo];			//ヘッダ入荷予定NO
							String GetClArrNo		= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColClArrNo];			//ヘッダ荷主予定番号
							String GetPlanDate		= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColPlanDate];		//ヘッダ入荷予定日
							String GetHdActualDate	= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColHdActualDate];	//ヘッダ入荷実績日
							String GetSpCd			= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColSpCd];			//ヘッダ仕入先CD
							String GetSpName01		= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColSpName01];		//ヘッダ仕入先名01
							String GetSpName02		= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColSpName02];		//ヘッダ仕入先名02
							String GetSpName03		= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColSpName03];		//ヘッダ仕入先名03
							String GetSpPost		= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColSpPost];			//ヘッダ仕入先郵便
							String GetSpAdd01		= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColSpAdd01];			//ヘッダ仕入先住所01
							String GetSpAdd02		= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColSpAdd02];			//ヘッダ仕入先住所02
							String GetSpAdd03		= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColSpAdd03];			//ヘッダ仕入先住所03
							String GetSpTel			= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColSpTel];			//ヘッダ仕入先電話
							String GetArCom01		= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColArCom01];			//ヘッダコメント1
							String GetArCom02		= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColArCom02];			//ヘッダコメント2
							String GetArCom03		= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColArCom03];			//ヘッダコメント3
							String GetHdEntryDate	= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColHdEntryDate];	//ヘッダ登録日
							String GetHdUpdateDate	= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColHdUpdateDate];	//ヘッダ更新日
							String GetHdEntryUser	= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColHdEntryUser];	//ヘッダ登録者
							String GetHdUpdateUser	= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColHdUpdateUser];	//ヘッダ更新者
							int  GetFixFg			= (int)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColFixFg];				//ヘッダ状況
										
							int GetMsNo				= (int)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColMsNo];				//明細番号
							String GetItemCd		= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColItemCd];			//商品コード
							String GetClItemCd		= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColClItemCd];		//荷主商品コード
							String GetJanCd			= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColJanCd];			//JANCD（バラ）
							String GetItemMdNo		= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColItemMdNo];		//商品型番
							String GetItemName		= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColItemName];		//商品名
							String Getlot			= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.Collot];				//ロット
							String GetExpDate		= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColExpDate];			//消費期限
							int GetPlanQty			= (int)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColPlanQty];			//予定数量
							int GetActualQty		= (int)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColActualQty];			//実績数
							String GetActualDate	= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColActualDate];		//入荷日
							String GetCom01			= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColCom01];			//コメント1
							String GetCom02			= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColCom02];			//コメント2
							String GetEntryDate		= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColEntryDate];		//登録日
							String GetUpdateDate	= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColUpdateDate];		//更新日
							String GetEntryUser		= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColEntryUser];		//登録者
							String GetUpdateUser	= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColUpdateUser];		//更新者
							
							CheckPlanMsData[i][ColMsPlanArrNo]				= GetArrNo;
							CheckPlanMsData[i][ColMsPlanMsNo]				= GetMsNo;
							CheckPlanMsData[i][ColMsPlanItemCd]				= GetItemCd;
							CheckPlanMsData[i][ColMsPlanItemName]			= GetItemName;
							CheckPlanMsData[i][ColMsPlanlot]				= Getlot;
							CheckPlanMsData[i][ColMsPlanExpDate]			= GetExpDate;
							CheckPlanMsData[i][ColMsPlanPlanQty]			= GetPlanQty;
							CheckPlanMsData[i][ColMsPlanActualQty]			= GetActualQty;
							CheckPlanMsData[i][ColMsPlanRemainingPlanQty]	= GetPlanQty-GetActualQty;
							CheckPlanMsData[i][ColMsPlanEntryQty]			= GetActualQty;
							CheckPlanMsData[i][ColMsPlanClWh]				= GetClWh;
							CheckPlanMsData[i][ColMsPlanClCd]				= GetClCd;
							CheckPlanMsData[i][ColMsPlanPlanDate]			= GetPlanDate;
							CheckPlanMsData[i][ColMsPlanSpCd]				= GetSpCd;
							CheckPlanMsData[i][ColHdPlanEntryFg]			= (boolean)false;
							
						}
						
						for(int i=0;i<ArrivalPlanHdRt.length;i++) {
							String GetClWh			= (String)ArrivalPlanHdRt[i][T100_ArrivalPlanHdRt.ColClWh];			//ヘッダ担当倉庫
							String GetClCd			= (String)ArrivalPlanHdRt[i][T100_ArrivalPlanHdRt.ColClCd];			//ヘッダ荷主CD
							String GetCLName01		= (String)ArrivalPlanHdRt[i][T100_ArrivalPlanHdRt.ColCLName01];		//ヘッダ荷主名
							String GetClGpCD		= (String)ArrivalPlanHdRt[i][T100_ArrivalPlanHdRt.ColClGpCD];			//ヘッダ荷主グループCD
							String GetCLGpName01	= (String)ArrivalPlanHdRt[i][T100_ArrivalPlanHdRt.ColCLGpName01];		//ヘッダ荷主グループ標記名
							String GetArrNo			= (String)ArrivalPlanHdRt[i][T100_ArrivalPlanHdRt.ColArrNo];			//ヘッダ入荷予定NO
							String GetClArrNo		= (String)ArrivalPlanHdRt[i][T100_ArrivalPlanHdRt.ColClArrNo];			//ヘッダ荷主予定番号
							String GetPlanDate		= (String)ArrivalPlanHdRt[i][T100_ArrivalPlanHdRt.ColPlanDate];		//ヘッダ入荷予定日
							String GetHdActualDate	= (String)ArrivalPlanHdRt[i][T100_ArrivalPlanHdRt.ColActualDate];		//ヘッダ入荷実績日
							String GetSpCd			= (String)ArrivalPlanHdRt[i][T100_ArrivalPlanHdRt.ColSpCd];			//ヘッダ仕入先CD
							String GetSpName01		= (String)ArrivalPlanHdRt[i][T100_ArrivalPlanHdRt.ColSpName01];		//ヘッダ仕入先名01
							String GetSpName02		= (String)ArrivalPlanHdRt[i][T100_ArrivalPlanHdRt.ColSpName02];		//ヘッダ仕入先名02
							String GetSpName03		= (String)ArrivalPlanHdRt[i][T100_ArrivalPlanHdRt.ColSpName03];		//ヘッダ仕入先名03
							String GetSpPost		= (String)ArrivalPlanHdRt[i][T100_ArrivalPlanHdRt.ColSpPost];			//ヘッダ仕入先郵便
							String GetSpAdd01		= (String)ArrivalPlanHdRt[i][T100_ArrivalPlanHdRt.ColSpAdd01];			//ヘッダ仕入先住所01
							String GetSpAdd02		= (String)ArrivalPlanHdRt[i][T100_ArrivalPlanHdRt.ColSpAdd02];			//ヘッダ仕入先住所02
							String GetSpAdd03		= (String)ArrivalPlanHdRt[i][T100_ArrivalPlanHdRt.ColSpAdd03];			//ヘッダ仕入先住所03
							String GetSpTel			= (String)ArrivalPlanHdRt[i][T100_ArrivalPlanHdRt.ColSpTel];			//ヘッダ仕入先電話
							String GetArCom01		= (String)ArrivalPlanHdRt[i][T100_ArrivalPlanHdRt.ColArCom01];			//ヘッダコメント1
							String GetArCom02		= (String)ArrivalPlanHdRt[i][T100_ArrivalPlanHdRt.ColArCom02];			//ヘッダコメント2
							String GetArCom03		= (String)ArrivalPlanHdRt[i][T100_ArrivalPlanHdRt.ColArCom03];			//ヘッダコメント3
							String GetHdEntryDate	= (String)ArrivalPlanHdRt[i][T100_ArrivalPlanHdRt.ColEntryDate];		//ヘッダ登録日
							String GetHdUpdateDate	= (String)ArrivalPlanHdRt[i][T100_ArrivalPlanHdRt.ColUpdateDate];		//ヘッダ更新日
							String GetHdEntryUser	= (String)ArrivalPlanHdRt[i][T100_ArrivalPlanHdRt.ColEntryUser];		//ヘッダ登録者
							String GetHdUpdateUser	= (String)ArrivalPlanHdRt[i][T100_ArrivalPlanHdRt.ColUpdateUser];		//ヘッダ更新者
							int GetFixFg			= (int)ArrivalPlanHdRt[i][T100_ArrivalPlanHdRt.ColFixFg];				//ヘッダ状況
							int GetPlanQty			= (int)ArrivalPlanHdRt[i][T100_ArrivalPlanHdRt.ColPlanQty];			//予定数合計
							int GetActualQty		= (int)ArrivalPlanHdRt[i][T100_ArrivalPlanHdRt.ColActualQty];			//実績数合計
							
							CheckPlanHdData[i][ColHdPlanArrNo]				= GetArrNo;
							CheckPlanHdData[i][ColHdPlanClWh]				= GetClWh;
							CheckPlanHdData[i][ColHdPlanClCd]				= GetClCd;
							CheckPlanHdData[i][ColHdPlanSpCd]				= GetSpCd;
							CheckPlanHdData[i][ColHdPlanPlanDate]			= GetPlanDate;
							CheckPlanHdData[i][ColHdPlanQty]				= GetPlanQty;
							CheckPlanHdData[i][ColHdPlanActualQty]			= GetActualQty;
							CheckPlanHdData[i][ColHdPlanRemainingPlanQty]	= GetPlanQty-GetActualQty;
							CheckPlanHdData[i][ColHdPlanEntryQty]			= (int)0;
							CheckPlanHdData[i][ColHdPlanCompleteFg]			= (boolean)false;
						}
						
						for(int i=0;i<RowCount;i++) {
							String GetClWh			= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(i,ColSetClWh));
							String GetClCd			= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(i,ColSetClCd));
							String GetPlanDate		= B100_TextControl.TextToDate(	""+MainFmTableModel.getValueAt(i,ColSetPlanDate));
							String GetSpCd			= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(i,ColSetSpCd));
							String GetSpName01		= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(i,ColSetSpName01));
							String GetClArrNo		= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(i,ColSetClArrNo));
							String GetArrNo			= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(i,ColSetArrNo));
							int GetMsNo				= B100_TextControl.TextToInt(	""+MainFmTableModel.getValueAt(i,ColSetMsNo));
							String GetItemCd		= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(i,ColSetItemCd));
							String GetItemName		= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(i,ColSetItemName));
							String Getlot			= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(i,ColSetlot));
							String GetExpDate		= B100_TextControl.TextToDate(	""+MainFmTableModel.getValueAt(i,ColSetExpDate));
							int GetPlanQty			= B100_TextControl.TextToInt(	""+MainFmTableModel.getValueAt(i,ColSetPlanQty));
							String GetRecmendLoc	= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(i,ColSetRecmendLoc));
							String GetRecmendLocMst	= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(i,ColSetRecmendLocMst));
							String GetEntryLot		= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(i,ColSetEntryLot));
							String GetEntryExpDate	= B100_TextControl.TextToDate(	""+MainFmTableModel.getValueAt(i,ColSetEntryExpDate));
							int GetEntryQty			= B100_TextControl.TextToInt(	""+MainFmTableModel.getValueAt(i,ColSetEntryQty));
							String GetEntryStoreLoc	= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(i,ColSetEntryStoreLoc));
							int GetActualQty		= B100_TextControl.TextToInt(	""+MainFmTableModel.getValueAt(i,ColSetActualQty));
							int GetRemainingPlanQty	= B100_TextControl.TextToInt(	""+MainFmTableModel.getValueAt(i,ColSetRemainingPlanQty));
							
							if("".equals(GetEntryStoreLoc)) {GetEntryStoreLoc	= B100_DefaultVariable.DefaultArrivalLoc;}
							
							String[] MsTgtData = {GetClWh,GetClCd,GetArrNo,""+GetMsNo};
							int[] MsKeyClm = {ColMsPlanClWh,ColMsPlanClCd,ColMsPlanArrNo,ColMsPlanMsNo};
							int MsHitRow=B100_ArrayListControl.ObjectGetRowAnyKey(CheckPlanMsData,MsTgtData,MsKeyClm,false);
							
							String[] HdTgtData = {GetClWh,GetClCd,GetArrNo};
							int[] HdKeyClm = {ColHdPlanClWh,ColHdPlanClCd,ColHdPlanArrNo};
							int HdHitRow=B100_ArrayListControl.ObjectGetRowAnyKey(CheckPlanHdData,HdTgtData,HdKeyClm,false);
							
							
							CheckPlanMsData[MsHitRow][ColMsPlanEntryQty]			= (int)CheckPlanMsData[MsHitRow][ColMsPlanEntryQty]+GetEntryQty;
							CheckPlanHdData[HdHitRow][ColHdPlanEntryQty]			= (int)CheckPlanHdData[HdHitRow][ColHdPlanEntryQty]+GetEntryQty;
							CheckPlanMsData[MsHitRow][ColHdPlanEntryFg]				= (boolean)true;
							
						}
						ArrayList<String> UnCompleteArrNo	= new ArrayList<String>();
						ArrayList<String> OverQtyArrNo		= new ArrayList<String>();
						ArrayList<Integer> OverQtyMsNo		= new ArrayList<Integer>();
						
						for(int i=0;i<CheckPlanMsData.length;i++) {
							if(ShortageSplit.isSelected()	) {	//入荷不足分は分納待ちにする
								if((int)CheckPlanMsData[i][ColMsPlanRemainingPlanQty]>(int)CheckPlanMsData[i][ColMsPlanEntryQty]) {
									UnCompleteArrNo.add((String)CheckPlanMsData[i][ColMsPlanArrNo]);
								}
							}
							if(OverErr.isSelected()			) {	//過剰入荷は拒否する
								if((int)CheckPlanMsData[i][ColMsPlanRemainingPlanQty]<(int)CheckPlanMsData[i][ColMsPlanEntryQty]) {
									OverQtyArrNo.add((String)CheckPlanMsData[i][ColMsPlanArrNo]);
									OverQtyMsNo.add((int)CheckPlanMsData[i][ColMsPlanMsNo]);
								}
							}
						}
						
						if(null!=OverQtyArrNo&&0<OverQtyArrNo.size()) {
							ArrayList<String> OverErr	= new ArrayList<String>();
							for(int i=0;i<OverQtyArrNo.size();i++) {
								OverErr.add("入荷予定番号:"+OverQtyArrNo.get(i)+"明細行番号:"+OverQtyMsNo.get(i)+" の入荷数量が予定数量を超えています");
							}
							ErrView(OverErr,"QtyOver");
						}else {
							//登録済みの入荷実績データを取得し枝番採番する
							/************************************************************************************************
							  不足分を分納待ちにしない場合
							  入荷予定ヘッダに対して数量ゼロは数量ゼロを対象にする場合のみ実績生成
							  入荷予定明細に対して数量ゼロは入荷実績生成不要不要
							************************************************************************************************/
							
							/*************************
							  入荷予定ヘッダ更新件数
							  入荷予定明細更新件数
							  入荷実績ヘッダ登録件数
							  入荷実績明細更新件数
							  入荷時ロケから実格納ロケへの移動必要件数							  
							  を事前把握する
							*************************/
							int PlanHdEntryCount	= 0;
							int PlanMsEntryCount	= 0;
							int HdEntryCount 		= 0;
							int MsEntryCount		= 0;
							int MoveCount			= 0;
							
							ArrayList<String> EntryArrNoList	= new ArrayList<String>();
							
							for(int i=0;i<CheckPlanHdData.length;i++) {
								if(0<(int)CheckPlanHdData[i][ColHdPlanEntryQty]) {
									EntryArrNoList.add((String)CheckPlanHdData[i][ColHdPlanArrNo]);
								}else if(0==(int)CheckPlanHdData[i][ColHdPlanEntryQty]){
									if(ZeroEntry.isSelected()) {
										EntryArrNoList.add((String)CheckPlanHdData[i][ColHdPlanArrNo]);
									}
								}
							}
							
							EntryArrNoList		= B100_ArrayListControl.ArryListStringUniqueList(EntryArrNoList);
							UnCompleteArrNo		= B100_ArrayListControl.ArryListStringUniqueList(UnCompleteArrNo);
							PlanHdEntryCount	= EntryArrNoList.size();
							HdEntryCount		= PlanHdEntryCount;
							
							for(int i=0;i<EntryArrNoList.size();i++) {
								for(int i01=0;i01<CheckPlanMsData.length;i01++) {
									if(EntryArrNoList.get(i).equals((String)CheckPlanMsData[i01][ColMsPlanArrNo])) {
										PlanMsEntryCount	= PlanMsEntryCount+1;
									}
								}
								for(int i01=0;i01<RowCount;i01++) {
									String GetArrNo			= B100_TextControl.Trim(""+MainFmTableModel.getValueAt(i01,ColSetArrNo));
									String GetEntryStoreLoc	= B100_TextControl.Trim(""+MainFmTableModel.getValueAt(i01,ColSetEntryStoreLoc));
									if("".equals(GetEntryStoreLoc)) {GetEntryStoreLoc	= B100_DefaultVariable.DefaultArrivalLoc;}
									if(EntryArrNoList.get(i).equals(GetArrNo)) {
										MsEntryCount		= MsEntryCount+1;
										if(!B100_DefaultVariable.DefaultArrivalLoc.equals(GetEntryStoreLoc)) {
											MoveCount = MoveCount+1;
										}
									}
								}
							}
							
							//入荷予定ヘッダKey+更新項目
							String[] ArrivalPlanHd_ClWh			= new String[PlanHdEntryCount];	//担当倉庫		※Key
					 		String[] ArrivalPlanHd_ClCd			= new String[PlanHdEntryCount];	//荷主CD		※Key
							String[] ArrivalPlanHd_ArrNo		= new String[PlanHdEntryCount];	//入荷予定NO	※Key
							String[] ArrivalPlanHd_ActualDate	= new String[PlanHdEntryCount];	//入荷実績日
							String[] ArrivalPlanHd_UpdateDate	= new String[PlanHdEntryCount];	//更新日
							String[] ArrivalPlanHd_UpdateUser	= new String[PlanHdEntryCount];	//更新者
							String[] ArrivalPlanHd_FixFg		= new String[PlanHdEntryCount];	//ステータス
						
							//入荷予定明細Key+更新項目
							String[] ArrivalPlanMs_ClWh			= new String[PlanMsEntryCount];	//担当倉庫		※Key
							String[] ArrivalPlanMs_ClCd			= new String[PlanMsEntryCount];	//荷主CD		※Key
							String[] ArrivalPlanMs_ArrNo		= new String[PlanMsEntryCount];	//入荷予定NO	※Key
							String[] ArrivalPlanMs_MsNo			= new String[PlanMsEntryCount];	//明細番号		※Key
							String[] ArrivalPlanMs_ActualQty	= new String[PlanMsEntryCount];	//実績数
							String[] ArrivalPlanMs_ActualDate	= new String[PlanMsEntryCount];	//入荷日
							String[] ArrivalPlanMs_UpdateDate	= new String[PlanMsEntryCount];	//更新日
							String[] ArrivalPlanMs_UpdateUser	= new String[PlanMsEntryCount];	//更新者
						
							//入荷実績ヘッダKey+登録項目
							String[] ArrivalHd_ClWh				= new String[HdEntryCount];		//担当倉庫		※Key
							String[] ArrivalHd_ClCd				= new String[HdEntryCount];		//荷主CD		※Key
							String[] ArrivalHd_ArrNo			= new String[HdEntryCount];		//入荷予定NO	※Key
							String[] ArrivalHd_ArrCount			= new String[HdEntryCount];		//入荷予定枝番	※Key
							String[] ArrivalHd_ClArrNo			= new String[HdEntryCount];		//荷主予定番号
					 		String[] ArrivalHd_PlanDate			= new String[HdEntryCount];		//入荷予定日
							String[] ArrivalHd_ActualDate		= new String[HdEntryCount];		//入荷実績日
							String[] ArrivalHd_SpCd				= new String[HdEntryCount];		//仕入先CD
							String[] ArrivalHd_SpName01			= new String[HdEntryCount];		//仕入先名01
							String[] ArrivalHd_SpName02			= new String[HdEntryCount];		//仕入先名02
							String[] ArrivalHd_SpName03			= new String[HdEntryCount];		//仕入先名03
							String[] ArrivalHd_SpPost			= new String[HdEntryCount];		//仕入先郵便
							String[] ArrivalHd_SpAdd01			= new String[HdEntryCount];		//仕入先住所01
							String[] ArrivalHd_SpAdd02			= new String[HdEntryCount];		//仕入先住所02
							String[] ArrivalHd_SpAdd03			= new String[HdEntryCount];		//仕入先住所03
							String[] ArrivalHd_SpTel			= new String[HdEntryCount];		//仕入先電話
							String[] ArrivalHd_ArCom01			= new String[HdEntryCount];		//コメント1
							String[] ArrivalHd_ArCom02			= new String[HdEntryCount];		//コメント2
							String[] ArrivalHd_ArCom03			= new String[HdEntryCount];		//コメント3
							String[] ArrivalHd_EntryDate		= new String[HdEntryCount];		//登録日
							String[] ArrivalHd_UpdateDate		= new String[HdEntryCount];		//更新日
							String[] ArrivalHd_EntryUser		= new String[HdEntryCount];		//登録者
							String[] ArrivalHd_UpdateUser		= new String[HdEntryCount];		//更新者
							
							//入荷実績明細　Key+登録項目
							String[] ArrivalMs_ClWh				= new String[MsEntryCount];		//担当倉庫		※Key
							String[] ArrivalMs_ClCd				= new String[MsEntryCount];		//荷主CD		※Key
							String[] ArrivalMs_ArrNo			= new String[MsEntryCount];		//入荷予定NO	※Key
							String[] ArrivalMs_ArrCount			= new String[MsEntryCount];		//入荷予定枝番	※Key
							String[] ArrivalMs_MsNo				= new String[MsEntryCount];		//明細番号		※Key
							String[] ArrivalMs_MsSeq			= new String[MsEntryCount];		//明細Seq番号	※Key
							String[] ArrivalMs_ItemCd			= new String[MsEntryCount];		//商品コード
							String[] ArrivalMs_ClItemCd			= new String[MsEntryCount];		//荷主商品コード
							String[] ArrivalMs_JanCd			= new String[MsEntryCount];		//JanCd(バラ)
							String[] ArrivalMs_ItemMdNo			= new String[MsEntryCount];		//商品型番
							String[] ArrivalMs_ItemName			= new String[MsEntryCount];		//商品名
							String[] ArrivalMs_Lot				= new String[MsEntryCount];		//ロット
							String[] ArrivalMs_ExpDate			= new String[MsEntryCount];		//消費期限
							String[] ArrivalMs_PlanQty			= new String[MsEntryCount];		//予定数量
							String[] ArrivalMs_ActualQty		= new String[MsEntryCount];		//実績数
							String[] ArrivalMs_ActualDate		= new String[MsEntryCount];		//入荷日
							String[] ArrivalMs_Com01			= new String[MsEntryCount];		//コメント1
							String[] ArrivalMs_Com02			= new String[MsEntryCount];		//コメント2
							String[] ArrivalMs_EntryDate		= new String[MsEntryCount];		//登録日
							String[] ArrivalMs_UpdateDate		= new String[MsEntryCount];		//更新日
							String[] ArrivalMs_EntryUser		= new String[MsEntryCount];		//登録者
							String[] ArrivalMs_UpdateUser		= new String[MsEntryCount];		//更新者
							
							//実格納ロケに在庫移動するためのデータ
							Object[][] MoveData = new Object[MoveCount][Tools100_StockMoveControl.StockMoveControlLayout().length];
							
							//明細から在庫数増やすためのデータ
							Object[][] StockQtyControlSetData = new Object[MsEntryCount][Tools100_StockQtyControl.StockQtyControlDataLayout().length];
							
							PlanHdEntryCount	= 0;
							PlanMsEntryCount	= 0;
							HdEntryCount		= 0;
							MsEntryCount		= 0;
							MoveCount			= 0;
							String now_dtm = B100_DateTimeControl.dtmString2(B100_DateTimeControl.dtm()[1])[1];
							String now_date = B100_DateTimeControl.dtmString2(B100_DateTimeControl.dtm()[1])[0];
							
							for(int i=0;i<EntryArrNoList.size();i++) {
								ArrivalPlanHd_ClWh[PlanHdEntryCount]		= A00000_Main.ClWh;			//担当倉庫		※Key
								ArrivalPlanHd_ClCd[PlanHdEntryCount]		= A00000_Main.ClCd;			//荷主CD		※Key
								ArrivalPlanHd_ArrNo[PlanHdEntryCount]		= EntryArrNoList.get(i);	//入荷予定NO	※Key
								ArrivalPlanHd_ActualDate[PlanHdEntryCount]	= now_dtm;					//入荷実績日
								ArrivalPlanHd_UpdateDate[PlanHdEntryCount]	= now_dtm;					//更新日
								ArrivalPlanHd_UpdateUser[PlanHdEntryCount]	= "(" + A00000_Main.LoginUserId + ")" + A00000_Main.LoginUserName;	//更新者
								ArrivalPlanHd_FixFg[PlanHdEntryCount]		= "1";	//ステータス
								//予定コンプリートしていない場合分納待ちにする場合ステータス"2"で設定
								if(ShortageSplit.isSelected()	) {	//入荷不足分は分納待ちにする
									int HitRow = B100_ArrayListControl.ArryListGetRow(UnCompleteArrNo,EntryArrNoList.get(i),false);
									if(0>HitRow) {
										//Hitしない場合-1が返却されるので入荷済み
									}else {
										ArrivalPlanHd_FixFg[PlanHdEntryCount]		= "2";	//ステータス
									}
								}
								//入荷予定情報を元に入荷実績登録情報セット
								String[] TgtData	= {A00000_Main.ClWh,A00000_Main.ClCd,EntryArrNoList.get(i)};
								int[] KeyClm		= {T100_ArrivalPlanHdRt.ColClWh,T100_ArrivalPlanHdRt.ColClCd,T100_ArrivalPlanHdRt.ColArrNo};
								
								int HitRow = B100_ArrayListControl.ObjectGetRowAnyKey(ArrivalPlanHdRt,TgtData,KeyClm,false);
								int ArrCount = 1;
								ArrivalHd_ClWh[HdEntryCount]			= A00000_Main.ClWh;				//担当倉庫		※Key
								ArrivalHd_ClCd[HdEntryCount]			= A00000_Main.ClCd;				//荷主CD		※Key
								ArrivalHd_ArrNo[HdEntryCount]			= EntryArrNoList.get(i);		//入荷予定NO	※Key
								ArrivalHd_ArrCount[HdEntryCount]		= ""+ArrCount;					//入荷予定枝番	※Key
								ArrivalHd_ClArrNo[HdEntryCount]			= (String)ArrivalPlanHdRt[HitRow][T100_ArrivalPlanHdRt.ColClArrNo];	//荷主予定番号
								ArrivalHd_PlanDate[HdEntryCount]		= (String)ArrivalPlanHdRt[HitRow][T100_ArrivalPlanHdRt.ColPlanDate];	//入荷予定日
								ArrivalHd_ActualDate[HdEntryCount]		= now_dtm;						//入荷実績日
								ArrivalHd_SpCd[HdEntryCount]			= (String)ArrivalPlanHdRt[HitRow][T100_ArrivalPlanHdRt.ColSpCd];		//仕入先CD
								ArrivalHd_SpName01[HdEntryCount]		= (String)ArrivalPlanHdRt[HitRow][T100_ArrivalPlanHdRt.ColSpName01];	//仕入先名01
								ArrivalHd_SpName02[HdEntryCount]		= (String)ArrivalPlanHdRt[HitRow][T100_ArrivalPlanHdRt.ColSpName02];	//仕入先名02
								ArrivalHd_SpName03[HdEntryCount]		= (String)ArrivalPlanHdRt[HitRow][T100_ArrivalPlanHdRt.ColSpName03];	//仕入先名03
								ArrivalHd_SpPost[HdEntryCount]			= (String)ArrivalPlanHdRt[HitRow][T100_ArrivalPlanHdRt.ColSpPost];		//仕入先郵便
								ArrivalHd_SpAdd01[HdEntryCount]			= (String)ArrivalPlanHdRt[HitRow][T100_ArrivalPlanHdRt.ColSpAdd01];	//仕入先住所01
								ArrivalHd_SpAdd02[HdEntryCount]			= (String)ArrivalPlanHdRt[HitRow][T100_ArrivalPlanHdRt.ColSpAdd02];	//仕入先住所02
								ArrivalHd_SpAdd03[HdEntryCount]			= (String)ArrivalPlanHdRt[HitRow][T100_ArrivalPlanHdRt.ColSpAdd03];	//仕入先住所03
								ArrivalHd_SpTel[HdEntryCount]			= (String)ArrivalPlanHdRt[HitRow][T100_ArrivalPlanHdRt.ColSpTel];		//仕入先電話
								ArrivalHd_ArCom01[HdEntryCount]			= "";		//コメント1
								ArrivalHd_ArCom02[HdEntryCount]			= "";		//コメント2
								ArrivalHd_ArCom03[HdEntryCount]			= "";		//コメント3
								ArrivalHd_EntryDate[HdEntryCount]		= now_dtm;	//登録日
								ArrivalHd_UpdateDate[HdEntryCount]		= now_dtm;	//更新日
								ArrivalHd_EntryUser[HdEntryCount]		= "(" + A00000_Main.LoginUserId + ")" + A00000_Main.LoginUserName;	//登録者
								ArrivalHd_UpdateUser[HdEntryCount]		= "(" + A00000_Main.LoginUserId + ")" + A00000_Main.LoginUserName;	//更新者
								
								//入荷実績を元に入荷枝番を決定
								for(int i01=0;i01<ArrivalHdRt.length;i01++) {
									if(A00000_Main.ClWh.equals((String)ArrivalHdRt[i01][T100_ArrivalHdRt.ColClWh])
											&& A00000_Main.ClCd.equals((String)ArrivalHdRt[i01][T100_ArrivalHdRt.ColClCd])
											&& EntryArrNoList.get(i).equals((String)ArrivalHdRt[i01][T100_ArrivalHdRt.ColArrNo])
											) {
										if(ArrCount<=(int)ArrivalHdRt[i01][T100_ArrivalHdRt.ColArrCount]) {
											ArrCount = (int)ArrivalHdRt[i01][T100_ArrivalHdRt.ColArrCount]+1;
										}
									}
								}
								ArrivalHd_ArrCount[HdEntryCount]		= ""+ArrCount;					//入荷予定枝番	※Key
								
								for(int i01=0;i01<CheckPlanMsData.length;i01++) {
									if(EntryArrNoList.get(i).equals((String)CheckPlanMsData[i01][ColMsPlanArrNo])) {
										ArrivalPlanMs_ClWh[PlanMsEntryCount]		= A00000_Main.ClWh;								//担当倉庫		※Key
										ArrivalPlanMs_ClCd[PlanMsEntryCount]		= A00000_Main.ClCd;								//荷主CD		※Key
										ArrivalPlanMs_ArrNo[PlanMsEntryCount]		= ""+CheckPlanMsData[i01][ColMsPlanArrNo];		//入荷予定NO	※Key
										ArrivalPlanMs_MsNo[PlanMsEntryCount]		= ""+CheckPlanMsData[i01][ColMsPlanMsNo];		//明細番号		※Key
										ArrivalPlanMs_ActualQty[PlanMsEntryCount]	= ""+CheckPlanMsData[i01][ColMsPlanEntryQty];	//実績数
										ArrivalPlanMs_ActualDate[PlanMsEntryCount]	= now_dtm;	//入荷日
										ArrivalPlanMs_UpdateDate[PlanMsEntryCount]	= now_dtm;	//更新日
										ArrivalPlanMs_UpdateUser[PlanMsEntryCount]	= "(" + A00000_Main.LoginUserId + ")" + A00000_Main.LoginUserName;	//更新者
										
										
										PlanMsEntryCount	= PlanMsEntryCount+1;
									}
								}
								int seq = 0;
								for(int i01=0;i01<RowCount;i01++) {
									String GetClWh			= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(i01,ColSetClWh));
									String GetClCd			= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(i01,ColSetClCd));
									String GetPlanDate		= B100_TextControl.TextToDate(	""+MainFmTableModel.getValueAt(i01,ColSetPlanDate));
									String GetSpCd			= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(i01,ColSetSpCd));
									String GetSpName01		= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(i01,ColSetSpName01));
									String GetClArrNo		= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(i01,ColSetClArrNo));
									String GetArrNo			= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(i01,ColSetArrNo));
									int GetMsNo				= B100_TextControl.TextToInt(	""+MainFmTableModel.getValueAt(i01,ColSetMsNo));
									String GetItemCd		= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(i01,ColSetItemCd));
									String GetItemName		= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(i01,ColSetItemName));
									String Getlot			= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(i01,ColSetlot));
									String GetExpDate		= B100_TextControl.TextToDate(	""+MainFmTableModel.getValueAt(i01,ColSetExpDate));
									int GetPlanQty			= B100_TextControl.TextToInt(	""+MainFmTableModel.getValueAt(i01,ColSetPlanQty));
									String GetRecmendLoc	= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(i01,ColSetRecmendLoc));
									String GetRecmendLocMst	= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(i01,ColSetRecmendLocMst));
									String GetEntryLot		= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(i01,ColSetEntryLot));
									String GetEntryExpDate	= B100_TextControl.TextToDate(	""+MainFmTableModel.getValueAt(i01,ColSetEntryExpDate));
									int GetEntryQty			= B100_TextControl.TextToInt(	""+MainFmTableModel.getValueAt(i01,ColSetEntryQty));
									String GetEntryStoreLoc	= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(i01,ColSetEntryStoreLoc));
									int GetActualQty		= B100_TextControl.TextToInt(	""+MainFmTableModel.getValueAt(i01,ColSetActualQty));
									int GetRemainingPlanQty	= B100_TextControl.TextToInt(	""+MainFmTableModel.getValueAt(i01,ColSetRemainingPlanQty));
									if("".equals(GetEntryStoreLoc)) {GetEntryStoreLoc	= B100_DefaultVariable.DefaultArrivalLoc;}
									
									if(EntryArrNoList.get(i).equals(GetArrNo)) {
										seq = seq+1;
										//在庫管理上の日付項目
										String SetExpDate		= GetEntryExpDate;
										String SetActualDate	= now_date;
										//在庫の入荷日管理しない場合デフォルト入荷実績日をセット
										if(B100_DefaultVariable.ActualDateUnControl) {
											SetActualDate	= B100_DefaultVariable.DefaultActualDate;
										}else {
											
										}
										//賞味期限がセットされていなければデフォルト値
										if("".equals(SetExpDate)) {SetExpDate	= B100_DefaultVariable.DefaultExpDate;}
										
										
										ArrivalMs_ClWh[MsEntryCount]		= A00000_Main.ClWh;		//担当倉庫		※Key
										ArrivalMs_ClCd[MsEntryCount]		= A00000_Main.ClCd;		//荷主CD		※Key
										ArrivalMs_ArrNo[MsEntryCount]		= GetArrNo;				//入荷予定NO	※Key
										ArrivalMs_ArrCount[MsEntryCount]	= ""+ArrCount;			//入荷予定枝番	※Key
										ArrivalMs_MsNo[MsEntryCount]		= ""+GetMsNo;			//明細番号		※Key
										ArrivalMs_MsSeq[MsEntryCount]		= ""+seq;				//明細Seq番号	※Key
										ArrivalMs_ItemCd[MsEntryCount]		= GetItemCd;			//商品コード
										ArrivalMs_ClItemCd[MsEntryCount]	= "";					//荷主商品コード
										ArrivalMs_JanCd[MsEntryCount]		= "";					//JanCd(バラ)
										ArrivalMs_ItemMdNo[MsEntryCount]	= "";					//商品型番
										ArrivalMs_ItemName[MsEntryCount]	= GetItemName;			//商品名
										ArrivalMs_Lot[MsEntryCount]			= GetEntryLot;			//ロット
										ArrivalMs_ExpDate[MsEntryCount]		= SetExpDate;			//消費期限
										ArrivalMs_PlanQty[MsEntryCount]		= ""+GetPlanQty;		//予定数量
										ArrivalMs_ActualQty[MsEntryCount]	= ""+GetEntryQty;		//実績数
										ArrivalMs_ActualDate[MsEntryCount]	= SetActualDate;		//入荷日
										ArrivalMs_Com01[MsEntryCount]		= "";					//コメント1
										ArrivalMs_Com02[MsEntryCount]		= "";					//コメント2
										ArrivalMs_EntryDate[MsEntryCount]	= now_dtm;				//登録日
										ArrivalMs_UpdateDate[MsEntryCount]	= now_dtm;				//更新日
										ArrivalMs_EntryUser[MsEntryCount]	= "(" + A00000_Main.LoginUserId + ")" + A00000_Main.LoginUserName;		//登録者
										ArrivalMs_UpdateUser[MsEntryCount]	= "(" + A00000_Main.LoginUserId + ")" + A00000_Main.LoginUserName;		//更新者
										

										String[] MsTgtData	= {A00000_Main.ClWh,A00000_Main.ClCd,GetArrNo,""+GetMsNo};
										int[] MsKeyClm	= {T100_ArrivalPlanMsRt.ColClWh,T100_ArrivalPlanMsRt.ColClCd,T100_ArrivalPlanMsRt.ColArrNo,T100_ArrivalPlanMsRt.ColMsNo};
										
										HitRow = B100_ArrayListControl.ObjectGetRowAnyKey(ArrivalPlanMsRt,MsTgtData,MsKeyClm,false);
										if(0<=HitRow) {
											ArrivalMs_ClItemCd[MsEntryCount]	= (String)ArrivalPlanMsRt[HitRow][T100_ArrivalPlanMsRt.ColClItemCd];		//荷主商品コード
											ArrivalMs_JanCd[MsEntryCount]		= (String)ArrivalPlanMsRt[HitRow][T100_ArrivalPlanMsRt.ColJanCd];		//JanCd(バラ)
											ArrivalMs_ItemMdNo[MsEntryCount]	= (String)ArrivalPlanMsRt[HitRow][T100_ArrivalPlanMsRt.ColItemMdNo];		//商品型番
										}
										
										
										//入荷時ロケに在庫増やすためのデータ
										StockQtyControlSetData[MsEntryCount][Tools100_StockQtyControl.ColClCd]			= A00000_Main.ClCd;		//荷主コード
										StockQtyControlSetData[MsEntryCount][Tools100_StockQtyControl.ColWhCd]			= A00000_Main.ClWh;		//倉庫コード
										StockQtyControlSetData[MsEntryCount][Tools100_StockQtyControl.ColLoc]			= B100_DefaultVariable.DefaultArrivalLoc;		//ロケーション
										StockQtyControlSetData[MsEntryCount][Tools100_StockQtyControl.ColItemCd]		= GetItemCd;			//商品コード
										StockQtyControlSetData[MsEntryCount][Tools100_StockQtyControl.ColLot]			= GetEntryLot;			//ロット
										StockQtyControlSetData[MsEntryCount][Tools100_StockQtyControl.ColExpdate]		= SetExpDate;			//消費期限
										StockQtyControlSetData[MsEntryCount][Tools100_StockQtyControl.ColActualDate]	= SetActualDate;		//入荷実績日
										StockQtyControlSetData[MsEntryCount][Tools100_StockQtyControl.ColControlQty]	= GetEntryQty;			//調整数
										
										if(!B100_DefaultVariable.DefaultArrivalLoc.equals(GetEntryStoreLoc)) {
											//入荷時ロケから実ロケに在庫移動するためのデータ
											MoveData[MoveCount][Tools100_StockMoveControl.ColClCd]			= A00000_Main.ClCd;			//荷主コード
											MoveData[MoveCount][Tools100_StockMoveControl.ColWhCd]			= A00000_Main.ClWh;			//倉庫コード
											MoveData[MoveCount][Tools100_StockMoveControl.ColFromLoc]		= B100_DefaultVariable.DefaultArrivalLoc;	//移動元ロケーション
											MoveData[MoveCount][Tools100_StockMoveControl.ColToLoc]		= GetEntryStoreLoc;			//移動先ロケーション
											MoveData[MoveCount][Tools100_StockMoveControl.ColItemCd]		= GetItemCd;				//商品コード
											MoveData[MoveCount][Tools100_StockMoveControl.ColLot]			= GetEntryLot;				//ロット
											MoveData[MoveCount][Tools100_StockMoveControl.ColExpdate]		= SetExpDate;				//消費期限
											MoveData[MoveCount][Tools100_StockMoveControl.ColActualDate]	= SetActualDate;			//入荷実績日
											MoveData[MoveCount][Tools100_StockMoveControl.ColMoveQty]		= GetEntryQty;				//移動数
											MoveData[MoveCount][Tools100_StockMoveControl.ColMoveCom01]	= "入荷格納";				//移動コメント01
											MoveData[MoveCount][Tools100_StockMoveControl.ColMoveCom02]	= "直接格納";				//移動コメント02
											MoveData[MoveCount][Tools100_StockMoveControl.ColMoveCom03]	= ""+GetArrNo;				//移動コメント03
											
											MoveCount = MoveCount+1;
										}
										
										MsEntryCount		= MsEntryCount+1;
									}
								}
								PlanHdEntryCount	= PlanHdEntryCount+1;
								HdEntryCount		= HdEntryCount+1;
							}
							//在庫増やして移動する
							if(0<StockQtyControlSetData.length) {
								Tools100_StockQtyControl.StockQtyControl(StockQtyControlSetData) ;
							}
							if(0<MoveData.length) {
								Tools100_StockMoveControl.StockMoveControl(MoveData);
							}
							
							//入荷予定更新・入荷実績登録
							String ArrivalPlanHd_tgt_table 	= "WW0010ArrivalPlanHd";
							String ArrivalHd_tgt_table 		= "WW0012ArrivalHd";
							String ArrivalPlanMs_tgt_table	= "WW0011ArrivalPlanMs";
							String ArrivalMs_tgt_table 		= "WW0013ArrivalMs";
							
							
							String TgtDB = "WANKO";
							int non_msg_fg = 1;
							
							Object[][] ArrivalPlanHd_SetString	= {
											 {"ClWh"		,"0" ,"1" ,"Key" 	,ArrivalPlanHd_ClWh}		//担当倉庫
									 		,{"ClCd"		,"0" ,"1" ,"Key" 	,ArrivalPlanHd_ClCd}		//荷主CD
											,{"ArrNo"		,"0" ,"1" ,"Key" 	,ArrivalPlanHd_ArrNo}		//入荷予定NO
											,{"ActualDate"	,"0" ,"1" ,"" 		,ArrivalPlanHd_ActualDate}	//入荷実績日
											,{"UpdateDate"	,"0" ,"1" ,"" 		,ArrivalPlanHd_UpdateDate}	//更新日
											,{"UpdateUser"	,"0" ,"1" ,"" 		,ArrivalPlanHd_UpdateUser}	//更新者
											,{"FixFg"		,"0" ,"1" ,"" 		,ArrivalPlanHd_FixFg}		//ステータス
											};
					
							Object[][] ArrivalPlanMs_SetString	= {
											 {"ClWh"		,"0" ,"1" ,"Key"	,ArrivalPlanMs_ClWh}		//担当倉庫
											,{"ClCd"		,"0" ,"1" ,"Key"	,ArrivalPlanMs_ClCd}		//荷主CD
											,{"ArrNo"		,"0" ,"1" ,"Key"	,ArrivalPlanMs_ArrNo}		//入荷予定NO
											,{"MsNo"		,"0" ,"1" ,"Key"	,ArrivalPlanMs_MsNo}		//明細番号
											,{"ActualQty"	,"0" ,"1" ,"" 		,ArrivalPlanMs_ActualQty}	//実績数
											,{"ActualDate"	,"0" ,"1" ,"" 		,ArrivalPlanMs_ActualDate}	//入荷日
											,{"UpdateDate"	,"0" ,"1" ,"" 		,ArrivalPlanMs_UpdateDate}	//更新日
											,{"UpdateUser"	,"0" ,"1" ,"" 		,ArrivalPlanMs_UpdateUser}	//更新者
											};
							
							Object[][] ArrivalHd_SetString	= {
											 {"ClWh"		,"1" ,"0" ,"Key"	,ArrivalHd_ClWh}		//担当倉庫
											,{"ClCd"		,"1" ,"0" ,"Key"	,ArrivalHd_ClCd}		//荷主CD
											,{"ArrNo"		,"1" ,"0" ,"Key"	,ArrivalHd_ArrNo}		//入荷予定NO
											,{"ArrCount"	,"1" ,"0" ,"Key"	,ArrivalHd_ArrCount}	//入荷予定枝番
											,{"ClArrNo"		,"1" ,"0" ,"" 		,ArrivalHd_ClArrNo}		//荷主予定番号
									 		,{"PlanDate"	,"1" ,"0" ,"" 		,ArrivalHd_PlanDate}	//入荷予定日
											,{"ActualDate"	,"1" ,"0" ,"" 		,ArrivalHd_ActualDate}	//入荷実績日
											,{"SpCd"		,"1" ,"0" ,"" 		,ArrivalHd_SpCd}		//仕入先CD
											,{"SpName01"	,"1" ,"0" ,"" 		,ArrivalHd_SpName01}	//仕入先名01
											,{"SpName02"	,"1" ,"0" ,"" 		,ArrivalHd_SpName02}	//仕入先名02
											,{"SpName03"	,"1" ,"0" ,"" 		,ArrivalHd_SpName03}	//仕入先名03
											,{"SpPost"		,"1" ,"0" ,"" 		,ArrivalHd_SpPost}		//仕入先郵便
											,{"SpAdd01"		,"1" ,"0" ,"" 		,ArrivalHd_SpAdd01}		//仕入先住所01
											,{"SpAdd02"		,"1" ,"0" ,"" 		,ArrivalHd_SpAdd02}		//仕入先住所02
											,{"SpAdd03"		,"1" ,"0" ,"" 		,ArrivalHd_SpAdd03}		//仕入先住所03
											,{"SpTel"		,"1" ,"0" ,"" 		,ArrivalHd_SpTel}		//仕入先電話
											,{"ArCom01"		,"1" ,"0" ,"" 		,ArrivalHd_ArCom01}		//コメント1
											,{"ArCom02"		,"1" ,"0" ,"" 		,ArrivalHd_ArCom02}		//コメント2
											,{"ArCom03"		,"1" ,"0" ,"" 		,ArrivalHd_ArCom03}		//コメント3
											,{"EntryDate"	,"1" ,"0" ,"" 		,ArrivalHd_EntryDate}	//登録日
											,{"UpdateDate"	,"1" ,"0" ,"" 		,ArrivalHd_UpdateDate}	//更新日
											,{"EntryUser"	,"1" ,"0" ,"" 		,ArrivalHd_EntryUser}	//登録者
											,{"UpdateUser"	,"1" ,"0" ,"" 		,ArrivalHd_UpdateUser}	//更新者
											};
									
							Object[][] ArrivalMs_SetString	= {
											 {"ClWh"		,"1" ,"0" ,"Key"	,ArrivalMs_ClWh}		//担当倉庫
											,{"ClCd"		,"1" ,"0" ,"Key"	,ArrivalMs_ClCd}		//荷主CD
											,{"ArrNo"		,"1" ,"0" ,"Key"	,ArrivalMs_ArrNo}		//入荷予定NO
											,{"ArrCount"	,"1" ,"0" ,"Key"	,ArrivalMs_ArrCount}	//入荷予定枝番
											,{"MsNo"		,"1" ,"0" ,"Key"	,ArrivalMs_MsNo}		//明細番号
											,{"MsSeq"		,"1" ,"0" ,"Key"	,ArrivalMs_MsSeq}		//明細Seq番号
											,{"ItemCd"		,"1" ,"0" ,"" 		,ArrivalMs_ItemCd	}	//商品コード
											,{"ClItemCd"	,"1" ,"0" ,"" 		,ArrivalMs_ClItemCd}	//荷主商品コード
											,{"JanCd"		,"1" ,"0" ,"" 		,ArrivalMs_JanCd}		//JanCd(バラ)
											,{"ItemMdNo"	,"1" ,"0" ,"" 		,ArrivalMs_ItemMdNo}	//商品型番
											,{"ItemName"	,"1" ,"0" ,"" 		,ArrivalMs_ItemName}	//商品名
											,{"Lot"			,"1" ,"0" ,"" 		,ArrivalMs_Lot}			//ロット
											,{"ExpDate"		,"1" ,"0" ,"" 		,ArrivalMs_ExpDate}		//消費期限
											,{"PlanQty"		,"1" ,"0" ,"" 		,ArrivalMs_PlanQty}		//予定数量
											,{"ActualQty"	,"1" ,"0" ,"" 		,ArrivalMs_ActualQty}	//実績数
											,{"ActualDate"	,"1" ,"0" ,"" 		,ArrivalMs_ActualDate}	//入荷日
											,{"Com01"		,"1" ,"0" ,"" 		,ArrivalMs_Com01}		//コメント1
											,{"Com02"		,"1" ,"0" ,"" 		,ArrivalMs_Com02}		//コメント2
											,{"EntryDate"	,"1" ,"0" ,"" 		,ArrivalMs_EntryDate}	//登録日
											,{"UpdateDate"	,"1" ,"0" ,"" 		,ArrivalMs_UpdateDate}	//更新日
											,{"EntryUser"	,"1" ,"0" ,"" 		,ArrivalMs_EntryUser}	//登録者
											,{"UpdateUser"	,"1" ,"0" ,"" 		,ArrivalMs_UpdateUser}	//更新者
											};
							if(0<HdEntryCount) {
								A100_InsertUpdateSQL.InsertUpdateSomeRecord(ArrivalPlanHd_SetString	,ArrivalPlanHd_tgt_table	,TgtDB	,non_msg_fg);
								A100_InsertUpdateSQL.InsertUpdateSomeRecord(ArrivalHd_SetString		,ArrivalHd_tgt_table		,TgtDB	,non_msg_fg);
								A100_InsertUpdateSQL.InsertUpdateSomeRecord(ArrivalPlanMs_SetString	,ArrivalPlanMs_tgt_table	,TgtDB	,non_msg_fg);
								A100_InsertUpdateSQL.InsertUpdateSomeRecord(ArrivalMs_SetString		,ArrivalMs_tgt_table		,TgtDB	,non_msg_fg);
								
								//元ファイルバックアップ
								String FileName	= B100_FolderCheck.FILENAME(TgtFilePath);
								String FLD		= B100_FolderCheck.FILE_FLD(TgtFilePath);
								//フォルダ存在チェック→なければ作る
								B100_FolderCheck.FLD_CHECK(FLD+"\\BK");

								B100_FolderCheck.FILE_BACKUP(FLD,FLD+"\\BK",FileName);
								
								SetX=main_fm.getX();
								SetY=main_fm.getY();

								((JFrame)LocSerachSet[WT200_ItemSearchSubFm.RtJFrame]).setVisible(false);
								((JFrame)LocSerachSet[WT200_ItemSearchSubFm.RtJFrame]).dispose();
								
								main_fm.setVisible(false);
								main_fm.dispose();
								WT100_ArrivalPlan_00_Search.ArrivalPlanSearch(0, 0);
							}
						}
					}
					RenewFg = true;
				}
			}
		});
		
		//明細に反映ボタン押下時の挙動
		MsEntryBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					int RowNo				= B100_TextControl.TextToInt(TB_RowNo.getText());
					
					String PlanLot		= B100_TextControl.Trim(TB_lot.getText());
					String PlanExpDate	= B100_TextControl.Trim(TB_ExpDate.getText());
					int RemainingPlanQty	= B100_TextControl.TextToInt(TB_RemainingPlanQty.getText());
					
					String EntryLot			= B100_TextControl.Trim(TB_EntryLot.getText());
					String EntryExpDate		= B100_TextControl.Trim(TB_EntryExpDate.getText());
					int EntryQty			= B100_TextControl.TextToInt(TB_EntryQty.getText());
					String EntryStoreLoc	= B100_TextControl.Trim(TB_EntryStoreLoc.getText());
					boolean kickFg	= false;
					int ColCount	= MainFmTableModel.getColumnCount();
					if(0<=RowNo) {
						kickFg	= true;
						if(!"".equals(EntryStoreLoc)) {
							ArrayList<String> SearchLoc 	= new ArrayList<String>();	//ロケーション
							SearchLoc.add(EntryStoreLoc);
							Object[][] LocationMstRt	= LocationMstRt(A00000_Main.ClWh,A00000_Main.ClCd,SearchLoc);
							if(1==LocationMstRt.length) {
							}else {
								JOptionPane.showMessageDialog(null, "ロケーションマスタが不正です　やり直し");
								EntryStoreLoc	= "";
								kickFg	= false;
								TB_EntryStoreLoc.setText("");
							}
						}
						if(0>EntryQty) {
							JOptionPane.showMessageDialog(null, "数量マイナスは認められません　やり直し");
							kickFg	= false;
							TB_EntryQty.setText("0");
						}
						
						if(("".equals(PlanLot))||PlanLot.equals(EntryLot)&&("".equals(PlanExpDate))||PlanExpDate.equals(EntryExpDate)) {
							//予定にロット賞味期限が設定されていない又は予定と一致していればOK
						}else {
							int option = JOptionPane.showConfirmDialog(null, "登録しようとしているロット・賞味期限が予定と違います\n"
									+ "わざとですよね？\n"
									+ "このまま登録しますか？"
									,"登録確認", JOptionPane.YES_NO_OPTION,
								      JOptionPane.WARNING_MESSAGE);
							if (option == JOptionPane.YES_OPTION){
								kickFg	= true;
							}else {
								kickFg	= false;
							}
						}
						if(RemainingPlanQty<EntryQty) {
							int option = JOptionPane.showConfirmDialog(null, "登録しようとしている数量が予定数量を超えています\n"
									+ "わざとですよね？\n"
									+ "このまま登録しますか？"
									,"登録確認", JOptionPane.YES_NO_OPTION,
								      JOptionPane.WARNING_MESSAGE);
							if (option == JOptionPane.YES_OPTION){
								kickFg	= true;
							}else {
								kickFg	= false;
							}
						}
						
					}else {
						
					}
					if(kickFg) {
						//現在の値に有効な値が入っているいて異なる値を設定しようとした場合確認,
						//必要に応じて行追加
						String GetClWh			= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(RowNo,ColSetClWh));
						String GetClCd			= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(RowNo,ColSetClCd));
						String GetPlanDate		= B100_TextControl.TextToDate(	""+MainFmTableModel.getValueAt(RowNo,ColSetPlanDate));
						String GetSpCd			= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(RowNo,ColSetSpCd));
						String GetSpName01		= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(RowNo,ColSetSpName01));
						String GetClArrNo		= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(RowNo,ColSetClArrNo));
						String GetArrNo			= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(RowNo,ColSetArrNo));
						int GetMsNo				= B100_TextControl.TextToInt(	""+MainFmTableModel.getValueAt(RowNo,ColSetMsNo));
						String GetItemCd		= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(RowNo,ColSetItemCd));
						String GetItemName		= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(RowNo,ColSetItemName));
						String Getlot			= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(RowNo,ColSetlot));
						String GetExpDate		= B100_TextControl.TextToDate(	""+MainFmTableModel.getValueAt(RowNo,ColSetExpDate));
						int GetPlanQty			= B100_TextControl.TextToInt(	""+MainFmTableModel.getValueAt(RowNo,ColSetPlanQty));
						String GetRecmendLoc	= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(RowNo,ColSetRecmendLoc));
						String GetRecmendLocMst	= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(RowNo,ColSetRecmendLocMst));
						String GetEntryLot		= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(RowNo,ColSetEntryLot));
						String GetEntryExpDate	= B100_TextControl.TextToDate(	""+MainFmTableModel.getValueAt(RowNo,ColSetEntryExpDate));
						int GetEntryQty			= B100_TextControl.TextToInt(	""+MainFmTableModel.getValueAt(RowNo,ColSetEntryQty));
						String GetEntryStoreLoc	= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(RowNo,ColSetEntryStoreLoc));
						int GetActualQty		= B100_TextControl.TextToInt(	""+MainFmTableModel.getValueAt(RowNo,ColSetActualQty));
						int GetRemainingPlanQty	= B100_TextControl.TextToInt(	""+MainFmTableModel.getValueAt(RowNo,ColSetRemainingPlanQty));
						
						
						if(("".equals(GetEntryLot) ||EntryLot.equals(GetEntryLot)) && ("".equals(GetEntryExpDate) ||EntryExpDate.equals(GetEntryExpDate)) && ("".equals(GetEntryStoreLoc) ||EntryStoreLoc.equals(GetEntryStoreLoc))) {
							MainFmTableModel.setValueAt(""+EntryLot		, RowNo, ColSetEntryLot);
							MainFmTableModel.setValueAt(""+EntryExpDate	, RowNo, ColSetEntryExpDate);
							MainFmTableModel.setValueAt(""+EntryQty		, RowNo, ColSetEntryQty);
							MainFmTableModel.setValueAt(""+EntryStoreLoc, RowNo, ColSetEntryStoreLoc);
						}else {
							boolean NewowCreateFg	= false;
							int option = JOptionPane.showConfirmDialog(null, "登録しようとしている商品からロット・賞味期限・格納先が変更されています\n"
															+ "別の入荷行として行追加しますか？\n"
															+ "[いいえ]を選択した場合上書きします"
															,"登録確認", JOptionPane.YES_NO_OPTION,
								      JOptionPane.WARNING_MESSAGE);
							if (option == JOptionPane.YES_OPTION){
								NewowCreateFg	= true;
							}else {
								NewowCreateFg	= false;
							}
							
							if(NewowCreateFg) {
								Object[] SetOb = new Object[ColCount];
								SetOb[ 0] = false;
								
								for(int i01=1;i01<ColCount;i01++) {
									SetOb[i01] = MainFmTableModel.getValueAt(RowNo,i01);
								}
								SetOb[ColSetEntryLot]			= EntryLot;
								SetOb[ColSetEntryExpDate]	= EntryExpDate;
								SetOb[ColSetEntryQty]			= EntryQty;
								SetOb[ColSetEntryStoreLoc]	= EntryStoreLoc;
								
								MainFmTableModel.addRow(SetOb);
							}else {
								MainFmTableModel.setValueAt(""+EntryLot		, RowNo, ColSetEntryLot);
								MainFmTableModel.setValueAt(""+EntryExpDate	, RowNo, ColSetEntryExpDate);
								MainFmTableModel.setValueAt(""+EntryQty		, RowNo, ColSetEntryQty);
								MainFmTableModel.setValueAt(""+EntryStoreLoc, RowNo, ColSetEntryStoreLoc);
							}
						}
					}
					RenewFg = true;
					
					if(kickFg) {
						MainFmTableModel.setValueAt(false, RowNo, 0);
					}
				}
			}
		});
		
		
		//予定=実績ボタン押下時の挙動
		PlanSameBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					int RowCount = MainFmTableModel.getRowCount();
					int CheckOffRow = -1;
					for(int i=0;i<RowCount;i++) {
						if((boolean)MainFmTableModel.getValueAt(i, 0)) {CheckOffRow=i;}
						String EntryLot			= B100_TextControl.Trim(""+MainFmTableModel.getValueAt(i, ColSetlot));							//予定ロット
						String EntryExpDate		= B100_TextControl.Trim(""+MainFmTableModel.getValueAt(i, ColSetExpDate));						//予定賞味期限
						int EntryQty			= B100_TextControl.TextToInt(""+MainFmTableModel.getValueAt(i, ColSetRemainingPlanQty));		//入荷予定残
						
						MainFmTableModel.setValueAt(""+EntryLot		, i, ColSetEntryLot);
						MainFmTableModel.setValueAt(""+EntryExpDate	, i, ColSetEntryExpDate);
						MainFmTableModel.setValueAt(""+EntryQty		, i, ColSetEntryQty);
					}
					RenewFg = true;
					if(0>CheckOffRow) {
						
					}else {
						MainFmTableModel.setValueAt(false, CheckOffRow, 0);
					}
				}
			}
		});
		
		//MST推奨ロケ押下時の挙動
		MstLocEntryBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					int RowCount = MainFmTableModel.getRowCount();
					int CheckOffRow = -1;
					for(int i=0;i<RowCount;i++) {
						if((boolean)MainFmTableModel.getValueAt(i, 0)) {CheckOffRow=i;}
						String EntryLoc			= B100_TextControl.Trim(""+MainFmTableModel.getValueAt(i, ColSetRecmendLocMst));							//格納ロケ
						
						MainFmTableModel.setValueAt(""+EntryLoc		, i, ColSetEntryStoreLoc);
						
					}
					RenewFg = true;
					if(0>CheckOffRow) {
						
					}else {
						MainFmTableModel.setValueAt(false, CheckOffRow, 0);
					}
				}
			}
		});
		
		//在庫推奨ロケ押下時の挙動
		StockLocEntryBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					int RowCount = MainFmTableModel.getRowCount();
					int CheckOffRow = -1;
					for(int i=0;i<RowCount;i++) {
						if((boolean)MainFmTableModel.getValueAt(i, 0)) {CheckOffRow=i;}
						String EntryLoc			= B100_TextControl.Trim(""+MainFmTableModel.getValueAt(i, ColSetRecmendLoc));	//格納ロケ
						
						MainFmTableModel.setValueAt(""+EntryLoc		, i, ColSetEntryStoreLoc);
						
					}
					RenewFg = true;
					if(0>CheckOffRow) {
						
					}else {
						MainFmTableModel.setValueAt(false, CheckOffRow, 0);
					}
				}
			}
		});
		
		//入荷時ロケ押下時の挙動
		NKLocEntryBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					int RowCount = MainFmTableModel.getRowCount();
					int CheckOffRow = -1;
					for(int i=0;i<RowCount;i++) {
						if((boolean)MainFmTableModel.getValueAt(i, 0)) {CheckOffRow=i;}
						String EntryLoc			= B100_DefaultVariable.DefaultArrivalLoc;							//格納ロケ
						
						MainFmTableModel.setValueAt(""+EntryLoc		, i, ColSetEntryStoreLoc);
					}
					RenewFg = true;
					if(0>CheckOffRow) {
						
					}else {
						MainFmTableModel.setValueAt(false, CheckOffRow, 0);
					}
				}
			}
		});
		
		//ロケクリア押下時の挙動
		LocClearBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					int RowCount = MainFmTableModel.getRowCount();
					int CheckOffRow = -1;
					for(int i=0;i<RowCount;i++) {
						if((boolean)MainFmTableModel.getValueAt(i, 0)) {CheckOffRow=i;}
						String EntryLoc			= "";							//格納ロケ
						
						MainFmTableModel.setValueAt(""+EntryLoc		, i, ColSetEntryStoreLoc);
					}
					RenewFg = true;
					if(0>CheckOffRow) {
						
					}else {
						MainFmTableModel.setValueAt(false, CheckOffRow, 0);
					}
				}
			}
		});
		
		//明細予定通りボタン押下時の挙動
		MsPlanSameBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					int RowNo				= B100_TextControl.TextToInt(TB_RowNo.getText());
					if(0<=RowNo) {
						String EntryLot			= B100_TextControl.Trim(TB_lot.getText());							//予定ロット
						String EntryExpDate		= B100_TextControl.Trim(TB_ExpDate.getText());						//予定賞味期限
						int EntryQty			= B100_TextControl.TextToInt(TB_RemainingPlanQty.getText());		//入荷予定残
						
						TB_EntryLot.setText(EntryLot);
						TB_EntryExpDate.setText(EntryExpDate);
						TB_EntryQty.setText(""+ni.format(EntryQty));
					}
					
					RenewFg = true;
				}
			}
		});
		//明細MST推奨ロケボタン押下時の挙動
		MsMstLocEntryBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					int RowNo				= B100_TextControl.TextToInt(TB_RowNo.getText());
					if(0<=RowNo) {
						String EntryLoc			= B100_TextControl.Trim(TB_RecmendLocMst.getText());
						
						TB_EntryStoreLoc.setText(EntryLoc);
					}
					
					RenewFg = true;
				}
			}
		});
		//明細在庫推奨ロケボタン押下時の挙動
		MsStockLocEntryBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					int RowNo				= B100_TextControl.TextToInt(TB_RowNo.getText());
					if(0<=RowNo) {
						String EntryLoc			= B100_TextControl.Trim(TB_RecmendLoc.getText());
						
						TB_EntryStoreLoc.setText(EntryLoc);
					}
					
					RenewFg = true;
				}
			}
		});
		
		//チェックボックス操作時の挙動
		MainFmTableModel.addTableModelListener(new TableModelListener(){
			public void tableChanged(TableModelEvent e){
				if(RenewFg) {
					RenewFg = false;
					TB_RowNo.setText("-1");
					TB_PlanDate.setText("");
					TB_ArrNo.setText("");
					TB_MsNo.setText("");
					TB_ClWh.setSelectedIndex(		B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.WhList[1]	,A00000_Main.ClWh,true));
					TB_ClCd.setSelectedIndex(		B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.ClList[1]	,A00000_Main.ClCd,true));
					TB_SpCd.setSelectedIndex(0);	//仕入先
					
					TB_ItemCd.setText("");
					TB_ItemName.setText("");
					
					TB_lot.setText("");
					TB_ExpDate.setText("");
					TB_RemainingPlanQty.setText("");
					TB_RecmendLoc.setText("");
					TB_RecmendLocMst.setText("");
					
					TB_EntryLot.setText("");
					TB_EntryExpDate.setText("");
					TB_EntryQty.setText("");
					TB_EntryStoreLoc.setText("");
					
					TB_ClArrNo.setText("");
					TB_PlanQty.setText("");
					TB_ActualQty.setText("");
					
					
					int row_count = MainFmTableModel.getRowCount();
					Boolean setBL=Boolean.valueOf(false);
					for(int i=0;i<row_count;i++){
						if(i!=e.getFirstRow()){
							MainFmTableModel.setValueAt(setBL, i, 0);
						}else {
							
							if((boolean)MainFmTableModel.getValueAt(i, 0)) {
								
								String GetClWh			= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(i,ColSetClWh));
								String GetClCd			= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(i,ColSetClCd));
								String GetPlanDate		= B100_TextControl.TextToDate(	""+MainFmTableModel.getValueAt(i,ColSetPlanDate));
								String GetSpCd			= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(i,ColSetSpCd));
								String GetSpName01		= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(i,ColSetSpName01));
								String GetClArrNo		= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(i,ColSetClArrNo));
								String GetArrNo			= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(i,ColSetArrNo));
								int GetMsNo				= B100_TextControl.TextToInt(	""+MainFmTableModel.getValueAt(i,ColSetMsNo));
								String GetItemCd		= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(i,ColSetItemCd));
								String GetItemName		= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(i,ColSetItemName));
								String Getlot			= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(i,ColSetlot));
								String GetExpDate		= B100_TextControl.TextToDate(	""+MainFmTableModel.getValueAt(i,ColSetExpDate));
								int GetPlanQty			= B100_TextControl.TextToInt(	""+MainFmTableModel.getValueAt(i,ColSetPlanQty));
								String GetRecmendLoc	= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(i,ColSetRecmendLoc));
								String GetRecmendLocMst	= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(i,ColSetRecmendLocMst));
								String GetEntryLot		= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(i,ColSetEntryLot));
								String GetEntryExpDate	= B100_TextControl.TextToDate(	""+MainFmTableModel.getValueAt(i,ColSetEntryExpDate));
								int GetEntryQty			= B100_TextControl.TextToInt(	""+MainFmTableModel.getValueAt(i,ColSetEntryQty));
								String GetEntryStoreLoc	= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(i,ColSetEntryStoreLoc));
								int GetActualQty		= B100_TextControl.TextToInt(	""+MainFmTableModel.getValueAt(i,ColSetActualQty));
								int GetRemainingPlanQty	= B100_TextControl.TextToInt(	""+MainFmTableModel.getValueAt(i,ColSetRemainingPlanQty));
								
								TB_RowNo.setText(""+i);
								TB_PlanDate.setText(GetPlanDate);
								TB_ArrNo.setText(GetArrNo);
								TB_MsNo.setText(""+GetMsNo);
								TB_ClWh.setSelectedIndex(		B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.WhList[1]			,GetClWh,true));
								TB_ClCd.setSelectedIndex(		B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.ClList[1]			,GetClCd,true));
								TB_SpCd.setSelectedIndex(		B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.SupplierList[1]	,GetSpCd,true));	//仕入先
								
								TB_ItemCd.setText(GetItemCd);
								TB_ItemName.setText(GetItemName);
								
								TB_lot.setText(Getlot);
								TB_ExpDate.setText(GetExpDate);
								TB_RemainingPlanQty.setText(""+ni.format(GetRemainingPlanQty));
								TB_RecmendLoc.setText(GetRecmendLoc);
								TB_RecmendLocMst.setText(GetRecmendLocMst);
								
								TB_EntryLot.setText(GetEntryLot);
								TB_EntryExpDate.setText(GetEntryExpDate);
								TB_EntryQty.setText(""+ni.format(GetEntryQty));
								TB_EntryStoreLoc.setText(GetEntryStoreLoc);
								
								TB_ClArrNo.setText(GetClArrNo);
								TB_PlanQty.setText(""+ni.format(GetPlanQty));
								TB_ActualQty.setText(""+ni.format(GetActualQty));
							}else {
								
							}
						}
					}
					RenewFg = true;
				}
			}
		});
		
		//ロケーション検索ボタン押下時の挙動
		LocSearchBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					((JFrame)LocSerachSet[WT200_ItemSearchSubFm.RtJFrame]).setVisible(true);
					RenewFg = true;
				}
			}
		});
		
		//ロケーション検索サブ画面登録ボタン押下時の挙動
		((JButton)LocSerachSet[WT200_LocSearchSubFm.EntryBtn]).addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					int RowCount = ((DefaultTableModel)LocSerachSet[WT200_LocSearchSubFm.RtDefaultTableModel]).getRowCount();
					String SetLoc = "";
					boolean KickFg = false;
					for(int i=0;i<RowCount;i++) {
						if((boolean)((DefaultTableModel)LocSerachSet[WT200_LocSearchSubFm.RtDefaultTableModel]).getValueAt(i, 0)) {
							SetLoc = ""+((DefaultTableModel)LocSerachSet[WT200_LocSearchSubFm.RtDefaultTableModel]).getValueAt(i,1+M100_LocationMstRt.ColLoc);
							KickFg = true;
							i=RowCount+1;
						}
					}
					if(KickFg) {
						TB_EntryStoreLoc.setText(SetLoc);
						((JFrame)LocSerachSet[WT200_LocSearchSubFm.RtJFrame]).setVisible(false);
					}
					RenewFg = true;
				}
			}
		});
		
		//EXITボタン押下時の挙動
		exit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				((JFrame)LocSerachSet[WT200_ItemSearchSubFm.RtJFrame]).setVisible(false);
				((JFrame)LocSerachSet[WT200_ItemSearchSubFm.RtJFrame]).dispose();
				
				main_fm.setVisible(false);
				main_fm.dispose();
				WT100_ArrivalPlan_00_Search.ArrivalPlanSearch(0, 0);
			}
		});
	}
	private static ArrayList<String> ErrCheck(DefaultTableModel MainFmTableModel,JTable tb01){
		ArrayList<String> ErrMsg = new ArrayList<String>();
		String[] TableCol = B100_TableControl.TableFieldNameRt(tb01);
		int RowCount = MainFmTableModel.getRowCount();
		
		//入荷予定取得用
		ArrayList<String> SearchArrNo	= new ArrayList<String>();
		
		//格納ロケ取得用
		ArrayList<String> SearchLoc		= new ArrayList<String>();
		
		for(int i=0;i<RowCount;i++) {
			int wint = i+1;
			boolean GetFg				= (boolean)tb01.getValueAt(i,ColSetFg);
			String GetClWh			= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(i,ColSetClWh));
			String GetClCd			= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(i,ColSetClCd));
			String GetPlanDate		= B100_TextControl.TextToDate(	""+MainFmTableModel.getValueAt(i,ColSetPlanDate));
			String GetSpCd			= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(i,ColSetSpCd));
			String GetSpName01		= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(i,ColSetSpName01));
			String GetClArrNo		= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(i,ColSetClArrNo));
			String GetArrNo			= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(i,ColSetArrNo));
			int GetMsNo				= B100_TextControl.TextToInt(	""+MainFmTableModel.getValueAt(i,ColSetMsNo));
			String GetItemCd		= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(i,ColSetItemCd));
			String GetItemName		= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(i,ColSetItemName));
			String Getlot			= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(i,ColSetlot));
			String GetExpDate		= B100_TextControl.TextToDate(	""+MainFmTableModel.getValueAt(i,ColSetExpDate));
			int GetPlanQty			= B100_TextControl.TextToInt(	""+MainFmTableModel.getValueAt(i,ColSetPlanQty));
			String GetRecmendLoc	= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(i,ColSetRecmendLoc));
			String GetRecmendLocMst	= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(i,ColSetRecmendLocMst));
			String GetEntryLot		= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(i,ColSetEntryLot));
			String GetEntryExpDate	= B100_TextControl.TextToDate(	""+MainFmTableModel.getValueAt(i,ColSetEntryExpDate));
			int GetEntryQty			= B100_TextControl.TextToInt(	""+MainFmTableModel.getValueAt(i,ColSetEntryQty));
			String GetEntryStoreLoc	= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(i,ColSetEntryStoreLoc));
			int GetActualQty		= B100_TextControl.TextToInt(	""+MainFmTableModel.getValueAt(i,ColSetActualQty));
			int GetRemainingPlanQty	= B100_TextControl.TextToInt(	""+MainFmTableModel.getValueAt(i,ColSetRemainingPlanQty));
			
			MainFmTableModel.setValueAt(""+GetClWh				,i,ColSetClWh);
			MainFmTableModel.setValueAt(""+GetClCd				,i,ColSetClCd);
			MainFmTableModel.setValueAt(""+GetPlanDate			,i,ColSetPlanDate);
			MainFmTableModel.setValueAt(""+GetSpCd				,i,ColSetSpCd);
			MainFmTableModel.setValueAt(""+GetSpName01			,i,ColSetSpName01);
			MainFmTableModel.setValueAt(""+GetClArrNo			,i,ColSetClArrNo);
			MainFmTableModel.setValueAt(""+GetArrNo				,i,ColSetArrNo);
			MainFmTableModel.setValueAt(""+GetMsNo				,i,ColSetMsNo);
			MainFmTableModel.setValueAt(""+GetItemCd			,i,ColSetItemCd);
			MainFmTableModel.setValueAt(""+GetItemName			,i,ColSetItemName);
			MainFmTableModel.setValueAt(""+Getlot				,i,ColSetlot);
			MainFmTableModel.setValueAt(""+GetExpDate			,i,ColSetExpDate);
			MainFmTableModel.setValueAt(""+GetPlanQty			,i,ColSetPlanQty);
			MainFmTableModel.setValueAt(""+GetRecmendLoc		,i,ColSetRecmendLoc);
			MainFmTableModel.setValueAt(""+GetRecmendLocMst		,i,ColSetRecmendLocMst);
			MainFmTableModel.setValueAt(""+GetEntryLot			,i,ColSetEntryLot);
			MainFmTableModel.setValueAt(""+GetEntryExpDate		,i,ColSetEntryExpDate);
			MainFmTableModel.setValueAt(""+GetEntryQty			,i,ColSetEntryQty);
			MainFmTableModel.setValueAt(""+GetEntryStoreLoc		,i,ColSetEntryStoreLoc);
			MainFmTableModel.setValueAt(""+GetActualQty			,i,ColSetActualQty);
			MainFmTableModel.setValueAt(""+GetRemainingPlanQty	,i,ColSetRemainingPlanQty);
			
			if(!A00000_Main.ClWh.equals(GetClWh)) {ErrMsg.add(wint+"行目エラー ("+GetClWh+")は現在ログイン中の倉庫ではありません");}
			if(!A00000_Main.ClCd.equals(GetClCd)) {ErrMsg.add(wint+"行目エラー ("+GetClCd+")は現在ログイン中の荷主ではありません");}
			SearchArrNo.add(GetArrNo);
			if("".equals(GetEntryStoreLoc)) {
				
			}else {
				SearchLoc.add(GetEntryStoreLoc);
			}
		}
		
		Object[][] ArrivalPlanMsRt	= ArrivalPlanMsRt(A00000_Main.ClWh,A00000_Main.ClCd,SearchArrNo);
		Object[][] LocationMstRt	= LocationMstRt(A00000_Main.ClWh,A00000_Main.ClCd,SearchLoc);
		
		if(null==ErrMsg||0==ErrMsg.size()) {
			for(int i=0;i<RowCount;i++) {
				int wint = i+1;
				boolean GetFg			= (boolean)MainFmTableModel.getValueAt(i,ColSetFg);
				String GetClWh			= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(i,ColSetClWh));
				String GetClCd			= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(i,ColSetClCd));
				String GetPlanDate		= B100_TextControl.TextToDate(	""+MainFmTableModel.getValueAt(i,ColSetPlanDate));
				String GetSpCd			= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(i,ColSetSpCd));
				String GetSpName01		= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(i,ColSetSpName01));
				String GetClArrNo		= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(i,ColSetClArrNo));
				String GetArrNo			= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(i,ColSetArrNo));
				int GetMsNo				= B100_TextControl.TextToInt(	""+MainFmTableModel.getValueAt(i,ColSetMsNo));
				String GetItemCd		= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(i,ColSetItemCd));
				String GetItemName		= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(i,ColSetItemName));
				String Getlot			= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(i,ColSetlot));
				String GetExpDate		= B100_TextControl.TextToDate(	""+MainFmTableModel.getValueAt(i,ColSetExpDate));
				int GetPlanQty			= B100_TextControl.TextToInt(	""+MainFmTableModel.getValueAt(i,ColSetPlanQty));
				String GetRecmendLoc	= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(i,ColSetRecmendLoc));
				String GetRecmendLocMst	= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(i,ColSetRecmendLocMst));
				String GetEntryLot		= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(i,ColSetEntryLot));
				String GetEntryExpDate	= B100_TextControl.TextToDate(	""+MainFmTableModel.getValueAt(i,ColSetEntryExpDate));
				int GetEntryQty			= B100_TextControl.TextToInt(	""+MainFmTableModel.getValueAt(i,ColSetEntryQty));
				String GetEntryStoreLoc	= B100_TextControl.Trim(		""+MainFmTableModel.getValueAt(i,ColSetEntryStoreLoc));
				int GetActualQty		= B100_TextControl.TextToInt(	""+MainFmTableModel.getValueAt(i,ColSetActualQty));
				int GetRemainingPlanQty	= B100_TextControl.TextToInt(	""+MainFmTableModel.getValueAt(i,ColSetRemainingPlanQty));
				
				boolean UnHitFg = true;
				
				if(0>GetEntryQty) {
					ErrMsg.add(wint+"行目エラー(入荷予定番号:"+GetArrNo+" 入荷数量:"+GetEntryQty+")入荷数量はマイナス禁止です");
				}
				
				for(int i01=0;i01<ArrivalPlanMsRt.length;i01++) {
					
					if(GetArrNo.equals(		(String)ArrivalPlanMsRt[i01][T100_ArrivalPlanMsRt.ColArrNo])
							&& GetMsNo==	(int)ArrivalPlanMsRt[i01][T100_ArrivalPlanMsRt.ColMsNo]
							) {
						if(GetItemCd.equals(			(String)ArrivalPlanMsRt[i01][T100_ArrivalPlanMsRt.ColItemCd])
								&& Getlot.equals(		(String)ArrivalPlanMsRt[i01][T100_ArrivalPlanMsRt.Collot])
								&& GetExpDate.equals(	(String)ArrivalPlanMsRt[i01][T100_ArrivalPlanMsRt.ColExpDate])
								&& GetPlanQty	==		(int)ArrivalPlanMsRt[i01][T100_ArrivalPlanMsRt.ColPlanQty]
								&& GetActualQty	==		(int)ArrivalPlanMsRt[i01][T100_ArrivalPlanMsRt.ColActualQty]
								) {
							if(0==(int)ArrivalPlanMsRt[i01][T100_ArrivalPlanMsRt.ColFixFg]||2==(int)ArrivalPlanMsRt[i01][T100_ArrivalPlanMsRt.ColFixFg]) {
								
							}else {
								ErrMsg.add(wint+"行目エラー(入荷予定番号:"+GetArrNo+" 明細番号:"+GetMsNo+")入荷予定は入荷登録可能なステータスではありません");
							}
						}else {
							ErrMsg.add(wint+"行目エラー(入荷予定番号:"+GetArrNo+" 明細番号:"+GetMsNo+")入荷予定が変更されています。最新の入荷予定に対して実績登録してください");
						}
						UnHitFg = false;
						i01=ArrivalPlanMsRt.length+1;
					}
				}
				if(UnHitFg) {
					ErrMsg.add(wint+"行目エラー(入荷予定番号:"+GetArrNo+" 明細番号:"+GetMsNo+")入荷予定は存在しません。予定削除されていませんか？");
				}
				
				UnHitFg = true;
				if(!"".equals(GetEntryStoreLoc)) {
					for(int i01=0;i01<LocationMstRt.length;i01++) {
						if(GetEntryStoreLoc.equals((String)LocationMstRt[i01][M100_LocationMstRt.ColLoc])) {
							UnHitFg = false;
							i01=LocationMstRt.length+1;
						}
					}
				}else {
					UnHitFg = false;
				}
				if(UnHitFg) {
					ErrMsg.add(wint+"行目エラー(入荷予定番号:"+GetArrNo+" 明細番号:"+GetMsNo+"　ロケーション"+GetEntryStoreLoc+")は存在しないロケーションです　ロケーションコード確認して出直してください");
				}
			}
		}
		return ErrMsg;
	}
	
	private static Object[][] LocationMstRt(String TgtClWh,String TgtClCd,ArrayList<String> SearchLoc){
		ArrayList<String> SearchClCd 	= new ArrayList<String>();	//荷主コード
		ArrayList<String> SearchWhCd 	= new ArrayList<String>();	//倉庫コード
		//ArrayList<String> SearchLoc 	= new ArrayList<String>();	//ロケーション
		ArrayList<String> SearchLocName = new ArrayList<String>();	//ロケーション名
		ArrayList<String> SearchType 	= new ArrayList<String>();	//ロケタイプ
		boolean LocExactMatch = false;	//ロケーション完全一致
		boolean AllSearch = false;
		if(null!= SearchLoc && 0<SearchLoc.size()) {
			SearchClCd.add(TgtClCd);
			SearchWhCd.add(TgtClWh);
		}
		
		Object[][] LocationMstRt = M100_LocationMstRt.LocationMstRt(
				SearchClCd,		//荷主コード
				SearchWhCd,		//倉庫コード
				SearchLoc,		//ロケーション
				SearchLocName,	//ロケーション名
				SearchType,		//ロケタイプ
				LocExactMatch,	//ロケーション完全一致
				AllSearch);
		
		return LocationMstRt;
	}
	private static Object[][] ArrivalHdRt(String TgtClWh,String TgtClCd,ArrayList<String> SearchArrNo){
		ArrayList<String> SearchClWh			= new ArrayList<String>();		//担当倉庫
		ArrayList<String> SearchClCd			= new ArrayList<String>();		//荷主CD
		ArrayList<String> SearchClGpCD			= new ArrayList<String>();		//ヘッダ荷主グループCD
		//ArrayList<String> SearchArrNo			= new ArrayList<String>();		//入荷予定NO
		ArrayList<Integer> SearchArrCountMin	= new ArrayList<Integer>();		//入荷予定枝番最小
		ArrayList<Integer> SearchArrCountMax	= new ArrayList<Integer>();		//入荷予定枝番最大
		ArrayList<String> SearchClArrNo			= new ArrayList<String>();		//荷主予定番号
		ArrayList<String> SearchPlanDateMin		= new ArrayList<String>();		//入荷予定日最小
		ArrayList<String> SearchPlanDateMax		= new ArrayList<String>();		//入荷予定日最大
		ArrayList<String> SearchActualDateMin	= new ArrayList<String>();		//入荷実績日最小
		ArrayList<String> SearchActualDateMax	= new ArrayList<String>();		//入荷実績日最大
		ArrayList<String> SearchSpCd			= new ArrayList<String>();		//仕入先CD
		ArrayList<String> SearchCom				= new ArrayList<String>();		//コメント
		ArrayList<String> SearchEntryDateMin	= new ArrayList<String>();		//登録日最小
		ArrayList<String> SearchEntryDateMax	= new ArrayList<String>();		//登録日最大
		ArrayList<String> SearchUpdateDateMin	= new ArrayList<String>();		//更新日最小
		ArrayList<String> SearchUpdateDateMax	= new ArrayList<String>();		//更新日最大
		ArrayList<String> SearchEntryUser		= new ArrayList<String>();		//登録者
		ArrayList<String> SearchUpdateUser		= new ArrayList<String>();		//更新者
		
		//明細WW0013ArrivalMs由来
		ArrayList<String> SearchItemCd			= new ArrayList<String>();		//商品コード
		ArrayList<String> SearchClItemCd		= new ArrayList<String>();		//荷主商品コード
		ArrayList<String> SearchItemName		= new ArrayList<String>();		//商品名
		ArrayList<String> SearchLot				= new ArrayList<String>();		//ロット
		ArrayList<String> SearchExpDateMin		= new ArrayList<String>();		//消費期限最小
		ArrayList<String> SearchExpDateMax		= new ArrayList<String>();		//消費期限最大
		boolean AllSearch	= false;
		
		if(null!=SearchArrNo && 0<SearchArrNo.size()) {
			SearchClWh.add(TgtClWh);
			SearchClCd.add(TgtClCd);
		}
		
		Object[][] ArrivalHdRt	= T100_ArrivalHdRt.ArrivalHdRt(
				SearchClWh,				//担当倉庫
				SearchClCd,				//荷主CD
				SearchClGpCD,			//ヘッダ荷主グループCD
				SearchArrNo,			//入荷予定NO
				SearchArrCountMin,		//入荷予定枝番最小
				SearchArrCountMax,		//入荷予定枝番最大
				SearchClArrNo,			//荷主予定番号
				SearchPlanDateMin,		//入荷予定日最小
				SearchPlanDateMax,		//入荷予定日最大
				SearchActualDateMin,	//入荷実績日最小
				SearchActualDateMax,	//入荷実績日最大
				SearchSpCd,				//仕入先CD
				SearchCom,				//コメント
				SearchEntryDateMin,		//登録日最小
				SearchEntryDateMax,		//登録日最大
				SearchUpdateDateMin,	//更新日最小
				SearchUpdateDateMax,	//更新日最大
				SearchEntryUser,		//登録者
				SearchUpdateUser,		//更新者
				
				//明細WW0013ArrivalMs由来
				SearchItemCd,			//商品コード
				SearchClItemCd,			//荷主商品コード
				SearchItemName,			//商品名
				SearchLot,				//ロット
				SearchExpDateMin,		//消費期限最小
				SearchExpDateMax,		//消費期限最大
				AllSearch);
		
		return ArrivalHdRt;
	}
	
	private static Object[][] ArrivalMsRt(String TgtClWh,String TgtClCd,ArrayList<String> SearchArrNo){
		ArrayList<String> SearchClWh			= new ArrayList<String>();		//担当倉庫
		ArrayList<String> SearchClCd			= new ArrayList<String>();		//荷主CD
		ArrayList<String> SearchClGpCD			= new ArrayList<String>();		//ヘッダ荷主グループCD
		//ArrayList<String> SearchArrNo			= new ArrayList<String>();		//入荷予定NO
		ArrayList<Integer> SearchArrCountMin	= new ArrayList<Integer>();		//入荷予定枝番最小
		ArrayList<Integer> SearchArrCountMax	= new ArrayList<Integer>();		//入荷予定枝番最大
		ArrayList<String> SearchClArrNo			= new ArrayList<String>();		//荷主予定番号
		ArrayList<String> SearchPlanDateMin		= new ArrayList<String>();		//入荷予定日最小
		ArrayList<String> SearchPlanDateMax		= new ArrayList<String>();		//入荷予定日最大
		ArrayList<String> SearchActualDateMin	= new ArrayList<String>();		//入荷実績日最小
		ArrayList<String> SearchActualDateMax	= new ArrayList<String>();		//入荷実績日最大
		ArrayList<String> SearchSpCd			= new ArrayList<String>();		//仕入先CD
		ArrayList<String> SearchCom				= new ArrayList<String>();		//コメント
		ArrayList<String> SearchEntryDateMin	= new ArrayList<String>();		//登録日最小
		ArrayList<String> SearchEntryDateMax	= new ArrayList<String>();		//登録日最大
		ArrayList<String> SearchUpdateDateMin	= new ArrayList<String>();		//更新日最小
		ArrayList<String> SearchUpdateDateMax	= new ArrayList<String>();		//更新日最大
		ArrayList<String> SearchEntryUser		= new ArrayList<String>();		//登録者
		ArrayList<String> SearchUpdateUser		= new ArrayList<String>();		//更新者
		
		//明細WW0013ArrivalMs由来
		ArrayList<String> SearchItemCd			= new ArrayList<String>();		//商品コード
		ArrayList<String> SearchClItemCd		= new ArrayList<String>();		//荷主商品コード
		ArrayList<String> SearchItemName		= new ArrayList<String>();		//商品名
		ArrayList<String> SearchLot				= new ArrayList<String>();		//ロット
		ArrayList<String> SearchExpDateMin		= new ArrayList<String>();		//消費期限最小
		ArrayList<String> SearchExpDateMax		= new ArrayList<String>();		//消費期限最大
		boolean AllSearch	= false;
		
		if(null!=SearchArrNo && 0<SearchArrNo.size()) {
			SearchClWh.add(TgtClWh);
			SearchClCd.add(TgtClCd);
		}
		
		Object[][] ArrivalMsRt	= T100_ArrivalMsRt.ArrivalMsRt(
				SearchClWh,				//担当倉庫
				SearchClCd,				//荷主CD
				SearchClGpCD,			//ヘッダ荷主グループCD
				SearchArrNo,			//入荷予定NO
				SearchArrCountMin,		//入荷予定枝番最小
				SearchArrCountMax,		//入荷予定枝番最大
				SearchClArrNo,			//荷主予定番号
				SearchPlanDateMin,		//入荷予定日最小
				SearchPlanDateMax,		//入荷予定日最大
				SearchActualDateMin,	//入荷実績日最小
				SearchActualDateMax,	//入荷実績日最大
				SearchSpCd,				//仕入先CD
				SearchCom,				//コメント
				SearchEntryDateMin,		//登録日最小
				SearchEntryDateMax,		//登録日最大
				SearchUpdateDateMin,	//更新日最小
				SearchUpdateDateMax,	//更新日最大
				SearchEntryUser,		//登録者
				SearchUpdateUser,		//更新者
				
				//明細WW0013ArrivalMs由来
				SearchItemCd,			//商品コード
				SearchClItemCd,			//荷主商品コード
				SearchItemName,			//商品名
				SearchLot,				//ロット
				SearchExpDateMin,		//消費期限最小
				SearchExpDateMax,		//消費期限最大
				AllSearch);
		
		return ArrivalMsRt;
	}
	
	
	private static Object[][] ArrivalPlanHdRt(String TgtClWh,String TgtClCd,ArrayList<String> SearchArrNo){
		if(null==TgtClWh) {TgtClWh="";}
		if(null==TgtClCd) {TgtClCd="";}
		ArrayList<String> SearchClWh			= new ArrayList<String>();		//ヘッダ担当倉庫
		ArrayList<String> SearchClCd			= new ArrayList<String>();		//ヘッダ荷主CD
		ArrayList<String> SearchCLName01		= new ArrayList<String>();		//ヘッダ荷主名
		ArrayList<String> SearchClGpCD			= new ArrayList<String>();		//ヘッダ荷主グループCD
		ArrayList<String> SearchCLGpName01		= new ArrayList<String>();		//ヘッダ荷主グループ標記名
		//ArrayList<String> SearchArrNo			= new ArrayList<String>();		//ヘッダ入荷予定NO
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
		if(null!=SearchArrNo && 0<SearchArrNo.size()) {
			SearchClWh.add(TgtClWh);
			SearchClCd.add(TgtClCd);
		}
		
		Object[][] ArrivalPlanHdRt	= T100_ArrivalPlanHdRt.ArrivalPlanHdRt(
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
		return ArrivalPlanHdRt;
	}
	
	private static Object[][] ArrivalPlanMsRt(String TgtClWh,String TgtClCd,ArrayList<String> SearchArrNo){
		if(null==TgtClWh) {TgtClWh="";}
		if(null==TgtClCd) {TgtClCd="";}
		ArrayList<String> SearchClWh			= new ArrayList<String>();		//ヘッダ担当倉庫
		ArrayList<String> SearchClCd			= new ArrayList<String>();		//ヘッダ荷主CD
		ArrayList<String> SearchCLName01		= new ArrayList<String>();		//ヘッダ荷主名
		ArrayList<String> SearchClGpCD			= new ArrayList<String>();		//ヘッダ荷主グループCD
		ArrayList<String> SearchCLGpName01		= new ArrayList<String>();		//ヘッダ荷主グループ標記名
		//ArrayList<String> SearchArrNo			= new ArrayList<String>();		//ヘッダ入荷予定NO
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
		if(null!=SearchArrNo && 0<SearchArrNo.size()) {
			SearchClWh.add(TgtClWh);
			SearchClCd.add(TgtClCd);
		}
		
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
	
	private static void ErrView(ArrayList<String>ErrMsg,String ErrTitle) {
		//必要フォルダを生成する
		String FLD_PATH = A00000_Main.MainFLD+"\\ArrivalPlan";
		B100_FolderCheck.FLD_CHECK(FLD_PATH);
		FLD_PATH = A00000_Main.MainFLD+"\\ArrivalPlan\\ArrivalPlanExcellentry";
		B100_FolderCheck.FLD_CHECK(FLD_PATH);
		FLD_PATH = A00000_Main.MainFLD+"\\ArrivalPlan\\ArrivalPlanExcellentry\\Err";
		B100_FolderCheck.FLD_CHECK(FLD_PATH);
		FLD_PATH = A00000_Main.MainFLD+"\\ArrivalPlan\\ArrivalPlanExcellentry\\BK";
		B100_FolderCheck.FLD_CHECK(FLD_PATH);
		
		//ファイルに出力
		String NowDTM=B100_DateTimeControl.dtmString2(B100_DateTimeControl.dtm()[1])[1].replace(" ", "").replace("/", "").replace(":", "");
		
		FLD_PATH = A00000_Main.MainFLD+"\\ArrivalPlan\\ArrivalPlanExcellentry\\Err";
		
		String ErrFP = FLD_PATH+"\\ERR"+ErrTitle+NowDTM+".txt";
		
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