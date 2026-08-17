import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class WT200_StockSearchSubFm{
	//検索子画面として在庫検索する
	//業務系の画面から召喚される
	/*
	コピペ用
	final Object[] StockSearchSubFm	= WT200_StockSearchSubFm.StockSearchSubFm(0,0,A00000_Main.ClWh,A00000_Main.ClCd,"ZK")
	
	((JFrame)SubFm[WT200_StockSearchSubFm.RtJFrame]).setVisible(true);
	
	((JButton)SubFm[WT200_StockSearchSubFm.EntryBtn]).addActionListener(new AbstractAction(){
		int RowCount = ((DefaultTableModel)SubFm[WT200_StockSearchSubFm.RtDefaultTableModel]).getRowCount();
		for(int i01=0;i01<RowCount;i01++) {
			
		}
	}
	
	((JFrame)SubFm[WT200_StockSearchSubFm.RtJFrame]).setVisible(false);
	((JFrame)SubFm[WT200_StockSearchSubFm.RtJFrame]).dispose();
	*/
	
	static boolean RenewFg;
	
	static final int RtJFrame				= 0;
	static final int RtDefaultTableModel	= 1;
	static final int RtJTable				= 2;
	static final int EntryBtn				= 3;
	public static Object[] StockSearchSubFm(int x,int y,String ClWh,String ClCd,String BackGroundColor) {
		RenewFg=false;
		if(null==ClWh) {ClWh="";}
		if(null==ClCd) {ClCd="";}
		if("".equals(ClWh)) {ClWh=A00000_Main.ClWh;}
		if("".equals(ClCd)) {ClCd=A00000_Main.ClCd;}
		
		final JFrame Stock_fm 	= B100_FrameParts.FrameCreate(x+10,y+10,800,750,"Corgi00在庫検索　WT200_StockSearchSubFm",BackGroundColor);
		JLabel 	StockUserinfo 	= B100_FrameParts.UserInfo();
		JButton StockExit_btn 	= B100_FrameParts.ExitBtn();
		JButton StockEntry_btn 	= B100_FrameParts.EntryBtn();
		
		Stock_fm.add(StockUserinfo);
		Stock_fm.add(StockExit_btn);
		Stock_fm.add(StockEntry_btn);
		
		Object[][] DefinitionRt	= T100_StockRt.DefinitionRt();
		
		JLabel LB_WhCd				= B100_FrameParts.JLabelSet(	 0, 50,100,20,(String)DefinitionRt[T100_StockRt.ColSearchWhCd][5]		+":"	,11,1);
		JLabel LB_ClCd				= B100_FrameParts.JLabelSet(	 0, 75,100,20,(String)DefinitionRt[T100_StockRt.ColSearchClCd][5]		+":"	,11,1);
		JLabel LB_SearchLoc			= B100_FrameParts.JLabelSet(	 0,100,100,20,(String)DefinitionRt[T100_StockRt.ColSearchLoc][5]		+":"	,11,1);
		JLabel LB_SearchItemCd		= B100_FrameParts.JLabelSet(	 0,125,100,20,(String)DefinitionRt[T100_StockRt.ColSearchItemCd][5]	+":"	,11,1);
		JLabel LB_SearchItemName	= B100_FrameParts.JLabelSet(	 0,150,100,20,(String)DefinitionRt[T100_StockRt.ColSearchItemName][5]	+":"	,11,1);
		
		final JComboBox TB_WhCd		= B100_FrameParts.JComboBoxSet(			100, 50,300,20,B100_DefaultVariable.WhList[0],11);	//倉庫
		final JComboBox TB_ClCd		= B100_FrameParts.JComboBoxSet(			100, 75,300,20,B100_DefaultVariable.ClList[0],11);	//荷主
		
		final JTextField  TB_SearchLoc		= B100_FrameParts.JTextFieldSet(	100,100,100,20,"",12,0);							//ロケ
		final JTextField  TB_SearchItemCd	= B100_FrameParts.JTextFieldSet(	100,125,100,20,"",12,0);							//商品CD
		final JTextField  TB_SearchItemName	= B100_FrameParts.JTextFieldSet(	100,150,100,20,"",12,0);							//商品名
		
		JLabel LB2_SearchLoc		= B100_FrameParts.JLabelSet(	200,100,100,20,B100_DefaultVariable.SearchPrefix	,11,0);
		JLabel LB2_SearchItemCd		= B100_FrameParts.JLabelSet(	200,125,100,20,B100_DefaultVariable.SearchExact	,11,0);
		JLabel LB2_SearchItemName	= B100_FrameParts.JLabelSet(	200,150,100,20,B100_DefaultVariable.SearchPartial	,11,0);
		
		JButton StockSearchKickBtn	= B100_FrameParts.BtnSet(		100,175, 90,20,"検索",11);
		
		TB_WhCd.setSelectedIndex(	B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.WhList[1]		,ClWh ,true) );		//倉庫コード
		TB_ClCd.setSelectedIndex(	B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.ClList[1]		,ClCd ,true) );		//荷主コード
		
		
		TB_WhCd.setEnabled(false);
		TB_ClCd.setEnabled(false);
		
		Stock_fm.add(LB_WhCd);
		Stock_fm.add(LB_ClCd);
		Stock_fm.add(LB_SearchLoc);
		Stock_fm.add(LB_SearchItemCd);
		Stock_fm.add(LB_SearchItemName);
		
		Stock_fm.add(TB_WhCd);
		Stock_fm.add(TB_ClCd);
		
		Stock_fm.add(TB_SearchLoc);
		Stock_fm.add(TB_SearchItemCd);
		Stock_fm.add(TB_SearchItemName);
		
		Stock_fm.add(LB2_SearchLoc);
		Stock_fm.add(LB2_SearchItemCd);
		Stock_fm.add(LB2_SearchItemName);
		
		Stock_fm.add(StockSearchKickBtn);
		
		Object[][] RtStockRt = T100_StockRt.RtStockRt();
		
		String[] columnNamesStock = new String[RtStockRt.length+1];
		
		columnNamesStock[0] = "Fg";
		for(int i=0;i<RtStockRt.length;i++) {
			columnNamesStock[1+(int)RtStockRt[i][1]] = ""+RtStockRt[i][3];
		}
		
		//編集可能カラム1列目のみ
		final DefaultTableModel tableModel_msStock = new B100_TableControl.MyTableModel00(columnNamesStock,0);
		
		final JTable tbStock = new JTable(tableModel_msStock);
		tbStock.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tbStock.setRowHeight(20*A00000_Main.Mul/A00000_Main.Div);
		tbStock.setFont(new Font(A00000_Main.DefaultFont, Font.PLAIN, 12*A00000_Main.Mul/A00000_Main.Div));
		
		DefaultTableColumnModel columnModelStock
		= (DefaultTableColumnModel)tbStock.getColumnModel();
		
		//列幅初期設定 表示位置設定
		TableColumn column = null;
		
		column = columnModelStock.getColumn( 0);	column.setPreferredWidth( 30*A00000_Main.Mul/A00000_Main.Div);	//FG
		
		for(int i=0;i<RtStockRt.length;i++) {
			if("int".equals((String)RtStockRt[i][2])||"float".equals((String)RtStockRt[i][2])) {
				column = columnModelStock.getColumn(1+(int)RtStockRt[i][1]);	column.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.rightCellRenderer());
			}else {
				column = columnModelStock.getColumn(1+(int)RtStockRt[i][1]);	column.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.leftCellRenderer());
			}
		}
		
		//スクロール用設定
		JScrollPane scpnLoc = B100_FrameParts.JScrollPaneSet(10,225,760,380,tbStock);
		Stock_fm.add(scpnLoc);
		
		RenewFg=true;
		
		//検索ボタン押下時の挙動
		StockSearchKickBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				int RowCount = tableModel_msStock.getRowCount();
				for(int i=0;i<RowCount;i++) {
					tableModel_msStock.removeRow(0);
				}
				String SearchTgtClCd	= B100_TextControl.Trim(B100_DefaultVariable.ClList[1][TB_ClCd.getSelectedIndex()]);	//荷主コード
				String SearchTgtWhCd	= B100_TextControl.Trim(B100_DefaultVariable.WhList[1][TB_WhCd.getSelectedIndex()]);	//倉庫コード
				String SearchTgtLoc		= B100_TextControl.Trim(TB_SearchLoc.getText());
				String SearchTgtItemCd	= B100_TextControl.Trim(TB_SearchItemCd.getText());
				String SearchTgtItemName= B100_TextControl.Trim(TB_SearchItemName.getText());
				
				ArrayList<String> SearchClCd				= new ArrayList<String>();			//荷主コード
				ArrayList<String> SearchWhCd				= new ArrayList<String>();			//倉庫コード
				ArrayList<String> SearchClGpCD				= new ArrayList<String>();			//荷主グループCD
				ArrayList<String> SearchLoc					= new ArrayList<String>();			//ロケーション
				ArrayList<Integer>SearchType				= new ArrayList<Integer>();			//ロケタイプ　0:通常　1:保管　8:入荷時　9:引当禁止
				ArrayList<String> SearchItemCd				= new ArrayList<String>();			//商品コード
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
				boolean LocExactMatch 	= false;												//ロケーション完全一致
				boolean AllSearch 		= true;													//全件検索
				boolean SortItemcdMode 	= false;												//商品CDでソート
				
				if(!"".equals(SearchTgtClCd)) {SearchClCd.add(SearchTgtClCd);}
				if(!"".equals(SearchTgtWhCd)) {SearchWhCd.add(SearchTgtWhCd);}
				if(!"".equals(SearchTgtLoc)) {SearchLoc.add(SearchTgtLoc);}
				if(!"".equals(SearchTgtItemCd)) {SearchItemCd.add(SearchTgtItemCd);}
				if(!"".equals(SearchTgtItemName)) {SearchItemName.add(SearchTgtItemName);}
				
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
				
				for(int i=0;i<StockRt.length;i++) {
					Object[] SetOb = new Object[StockRt[i].length+1];
					SetOb[0] = false;
					for(int i01=0;i01<StockRt[i].length;i01++) {
						SetOb[i01+1] = ""+StockRt[i][i01];
					}
					tableModel_msStock.addRow(SetOb);
				}
			}
		});
		
		//在庫検索チェックボックス操作時の挙動
		tableModel_msStock.addTableModelListener(new TableModelListener(){
			public void tableChanged(TableModelEvent e){
				if(RenewFg) {
					RenewFg = false;
					int row_count = tbStock.getRowCount();
					Boolean setBL=Boolean.valueOf(false);
					for(int i=0;i<row_count;i++){
						if(i!=e.getFirstRow()){
							tableModel_msStock.setValueAt(setBL, i, 0);
						}else {
	
						}
					}
					RenewFg = true;
				}
			}
		});
		
		//在庫検索EXITボタン押下時の挙動
		StockExit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				Stock_fm.setVisible(false);
			}
		});
		
		Object[] Rt = {
				Stock_fm
				,tableModel_msStock
				,tbStock
				,StockEntry_btn
				};
		return Rt;
	}
}