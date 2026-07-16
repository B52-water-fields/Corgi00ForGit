import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class WT100_Arrival_31_ExcelEntryInPut{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	
	static final int ColSetFg					=  0;
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
	
	public static void ParameterMstNyankoExcelEntry(int x,int y,String TgtFilePath) {
		A00000_Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;

		final JFrame main_fm = B100_FrameParts.FrameCreate(x,y,600,200,"Corgi00入荷実績登録（エクセル）WT100_Arrival_31_ExcelEntryInPut","");
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
		A00000_Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		final JFrame main_fm = B100_FrameParts.FrameCreate(x,y,1300,800,"Corgi00入荷実績登録（エクセル）WT100_Arrival_31_ExcelEntryInPut","");
		JLabel userinfo = B100_FrameParts.UserInfo();
		JButton exit_btn = B100_FrameParts.ExitBtn();
		JButton entry_btn = B100_FrameParts.EntryBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		main_fm.add(entry_btn);
		
		String[] Title = B100_RtObjectCreate.RtTitleName(WT100_Arrival_30_ExcelEntryOutPut.RtArrivalExcelOutPut());
		
		Object[][] NeedCol = {
				 {Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetClWh]					,1	,-1,ColSetClWh}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetClCd]					,1	,-1,ColSetClCd}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetPlanDate]				,1	,-1,ColSetPlanDate}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetSpCd]					,1	,-1,ColSetSpCd}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetSpName01]				,1	,-1,ColSetSpName01}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetClArrNo]				,1	,-1,ColSetClArrNo}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetArrNo]					,1	,-1,ColSetArrNo}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetMsNo]					,0	,-1,ColSetMsNo}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetItemCd]				,1	,-1,ColSetItemCd}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetItemName]				,1	,-1,ColSetItemName}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetlot]					,1	,-1,ColSetlot}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetExpDate]				,1	,-1,ColSetExpDate}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetPlanQty]				,0	,-1,ColSetPlanQty}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetActualQty]			,0	,-1,ColSetActualQty}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetRemainingPlanQty]	,0	,-1,ColSetRemainingPlanQty}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetRecmendLoc]			,1	,-1,ColSetRecmendLoc}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetRecmendLocMst]		,1	,-1,ColSetRecmendLocMst}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColEntryLot]					,1	,-1,ColSetEntryLot}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColEntryExpDate]			,1	,-1,ColSetEntryExpDate}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColEntryQty]					,0	,-1,ColSetEntryQty}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColEntryStoreLoc]			,1	,-1,ColSetEntryStoreLoc}
		};//フィールド名,フィールドタイプ(0:数値 1:文字列 2:日付時刻),基本のカラム(ゼロスタート),基本のカラム位置※カラム位置は後で読み込んだエクセルの1行目でフィールド名比較して更新されます
		
		JLabel LB_SheetList	= B100_FrameParts.JLabelSet(	10, 40,540,20,"以下のデータを登録しようとしています",11,0);
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
			SetX=main_fm.getX();
			SetY=main_fm.getY();

			main_fm.setVisible(false);
			main_fm.dispose();
			
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
			ParameterMstNyankoExcelEntry(0,0,TgtFilePath);
		}else {
			int[] ClmnType = new int[HeaderRead[0].length];
			for(int i=0;i<ClmnType.length;i++) {ClmnType[i]=1;}
			
			for(int i01=0;i01<NeedCol.length;i01++) {
				ClmnType[(int)NeedCol[i01][2]]=(int)NeedCol[i01][1];
			}
			Object[][] ExcellRead = B100_ExcellControl.ExcellRead(TgtFilePath,SheetName,ClmnType,true);
			
			if(0<ExcellRead.length&&ClmnType.length<=ExcellRead[0].length) {
				for(int i=0;i<ExcellRead.length;i++) {
					Object[] SetOb = new Object[NeedCol.length+1];
					SetOb[ 0] = false;
					
					for(int i01=0;i01<NeedCol.length;i01++) {
						SetOb[(int)NeedCol[i01][3]] = ExcellRead[i][(int)NeedCol[i01][2]];
					}
					MainFmTableModel.addRow(SetOb);
				}
			}
			
			ArrayList<String> ErrMsg = ErrCheck(tb01);
			
			if(null!=ErrMsg && 0<ErrMsg.size()) {
				ErrView(ErrMsg);
				
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				WT100_ArrivalPlan_00_Search.ArrivalPlanSearch(0, 0);
			}else {
				main_fm.setVisible(true);
			}
		}
		RenewFg = true;
		
		//チェックボックス操作時の挙動
		MainFmTableModel.addTableModelListener(new TableModelListener(){
			public void tableChanged(TableModelEvent e){
				if(RenewFg) {
					RenewFg = false;
					int row_count = tb01.getRowCount();
					Boolean setBL=Boolean.valueOf(false);
					for(int i=0;i<row_count;i++){
						if(i!=e.getFirstRow()){
							MainFmTableModel.setValueAt(setBL, i, 0);
						}else {
	
						}
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

				main_fm.setVisible(false);
				main_fm.dispose();
				WT100_ArrivalPlan_00_Search.ArrivalPlanSearch(0, 0);
			}
		});
	}
	private static ArrayList<String> ErrCheck(JTable tb01){
		ArrayList<String> ErrMsg = new ArrayList<String>();
		String[] TableCol = B100_TableControl.TableFieldNameRt(tb01);
		int RowCount = tb01.getRowCount();
		
		//商品マスタと入荷予定取得用に
		
		for(int i=0;i<RowCount;i++) {
			int wint = i+1;
			boolean GetFg				= (boolean)tb01.getValueAt(i,ColSetFg);
			String GetSetClWh			= B100_TextControl.Trim(		""+tb01.getValueAt(i,ColSetClWh));
			String GetSetClCd			= B100_TextControl.Trim(		""+tb01.getValueAt(i,ColSetClCd));
			String GetSetPlanDate		= B100_TextControl.TextToDate(	""+tb01.getValueAt(i,ColSetPlanDate));
			String GetSetSpCd			= B100_TextControl.Trim(		""+tb01.getValueAt(i,ColSetSpCd));
			String GetSetSpName01		= B100_TextControl.Trim(		""+tb01.getValueAt(i,ColSetSpName01));
			String GetSetClArrNo		= B100_TextControl.Trim(		""+tb01.getValueAt(i,ColSetClArrNo));
			String GetSetArrNo			= B100_TextControl.Trim(		""+tb01.getValueAt(i,ColSetArrNo));
			int GetSetMsNo				= B100_TextControl.TextToInt(	""+tb01.getValueAt(i,ColSetMsNo));
			String GetSetItemCd			= B100_TextControl.Trim(		""+tb01.getValueAt(i,ColSetItemCd));
			String GetSetItemName		= B100_TextControl.Trim(		""+tb01.getValueAt(i,ColSetItemName));
			String GetSetlot			= B100_TextControl.Trim(		""+tb01.getValueAt(i,ColSetlot));
			String GetSetExpDate		= B100_TextControl.TextToDate(	""+tb01.getValueAt(i,ColSetExpDate));
			int GetSetPlanQty			= B100_TextControl.TextToInt(	""+tb01.getValueAt(i,ColSetPlanQty));
			String GetSetRecmendLoc		= B100_TextControl.Trim(		""+tb01.getValueAt(i,ColSetRecmendLoc));
			String GetSetRecmendLocMst	= B100_TextControl.Trim(		""+tb01.getValueAt(i,ColSetRecmendLocMst));
			String GetSetEntryLot		= B100_TextControl.Trim(		""+tb01.getValueAt(i,ColSetEntryLot));
			String GetSetEntryExpDate	= B100_TextControl.TextToDate(	""+tb01.getValueAt(i,ColSetEntryExpDate));
			int GetSetEntryQty			= B100_TextControl.TextToInt(	""+tb01.getValueAt(i,ColSetEntryQty));
			String GetSetEntryStoreLoc	= B100_TextControl.Trim(		""+tb01.getValueAt(i,ColSetEntryStoreLoc));
			
			if(!A00000_Main.ClWh.equals(GetSetClWh)) {ErrMsg.add(wint+"行目エラー ("+GetSetClWh+")は現在ログイン中の倉庫ではありません");}
			if(!A00000_Main.ClCd.equals(GetSetClCd)) {ErrMsg.add(wint+"行目エラー ("+GetSetClCd+")は現在ログイン中の荷主ではありません");}
			
			System.out.println(GetSetItemCd);
		}
		
		return ErrMsg;
	}
	
	private static void ErrView(ArrayList<String>ErrMsg) {
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