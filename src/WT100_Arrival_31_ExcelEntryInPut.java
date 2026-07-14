import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class WT100_Arrival_31_ExcelEntryInPut{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
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
				 {Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetClWh]				,1	,-1}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetClCd]				,1	,-1}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetPlanDate]			,1	,-1}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetSpCd]				,1	,-1}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetSpName01]			,1	,-1}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetClArrNo]			,1	,-1}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetArrNo]				,1	,-1}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetMsNo]				,0	,-1}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetItemCd]			,1	,-1}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetItemName]			,1	,-1}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetlot]				,1	,-1}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetExpDate]			,1	,-1}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetPlanQty]			,0	,-1}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetRecmendLoc]		,1	,-1}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetRecmendLocMst]	,1	,-1}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColEntryLot]				,1	,-1}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColEntryExpDate]		,1	,-1}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColEntryQty]				,0	,-1}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColEntryStoreLoc]		,1	,-1}
		};//フィールド名,フィールドタイプ(0:数値 1:文字列 2:日付時刻),基本のカラム(ゼロスタート),基本のカラム位置※カラム位置は後で読み込んだエクセルの1行目でフィールド名比較して更新されます
		
		JLabel LB_SheetList	= B100_FrameParts.JLabelSet(	10, 40,540,20,"以下のデータを登録しようとしています",11,0);
		main_fm.add(LB_SheetList);
		
		String[] columnNames01 = new String[NeedCol.length+1];
		columnNames01[0] = "Fg";
		for(int i=0;i<NeedCol.length;i++) {columnNames01[i+1] = (String)NeedCol[i][0];}
		
		//編集可能カラムの指定
		B100_TableControl.RenewTgt = new int[1];
		B100_TableControl.RenewTgt[0] = 0;

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
			if(0==(int)NeedCol[i][1]) {
				column = columnModel01.getColumn(i+1);	column.setPreferredWidth(100*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.rightCellRenderer());	
			}else {
				column = columnModel01.getColumn(i+1);	column.setPreferredWidth(100*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.leftCellRenderer());	
			}
		}
		JScrollPane scpn01 = B100_FrameParts.JScrollPaneSet(10,65,1260,500,tb01);
		main_fm.add(scpn01);
		
		
		
		
		main_fm.setVisible(true);
		RenewFg = true;
		
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
}