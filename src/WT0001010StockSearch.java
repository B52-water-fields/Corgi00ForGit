import java.awt.Font;
import java.awt.event.ActionEvent;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class WT0001010StockSearch{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	public static void StockSearch(int x,int y) {
		A00000Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		
		final JFrame main_fm = B00110FrameParts.FrameCreate(x,y,1200,750,"Corgi00在庫検索","ZK");
		JLabel userinfo = B00110FrameParts.UserInfo();
		JButton exit_btn = B00110FrameParts.ExitBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		
		//検索条件パネル
		JPanel PN_Search = B00110FrameParts.JPanelSet(10,40,1160,250,"White");
		JLabel PN_SearchLabel = B00110FrameParts.JLabelSet(0,0,100,20,"検索条件",11,0);
		PN_Search.add(PN_SearchLabel);
		
		String[] LocExactMatchList	= {"で始まる","と一致"};
		String[] SortModeList		= {"ロケ順","商品CD順"};
		
		//検索条件
		JLabel LB_SearchClCd		= B00110FrameParts.JLabelSet(  0, 25,100,20,"荷主コード:"		,11,1);
		JLabel LB_SearchWhCd		= B00110FrameParts.JLabelSet(  0, 50,100,20,"倉庫コード:"		,11,1);
		JLabel LB_SearchClGpCD		= B00110FrameParts.JLabelSet(  0, 75,100,20,"荷主グループ:"	,11,1);
		
		JLabel LB_SearchItemCd		= B00110FrameParts.JLabelSet(300, 25,100,20,"商品コード:"		,11,1);
		JLabel LB_SearchItemName	= B00110FrameParts.JLabelSet(300, 50,100,20,"商品名:"			,11,1);
		JLabel LB_SearchClItemCd	= B00110FrameParts.JLabelSet(300, 75,100,20,"荷主商品コード:"	,11,1);
		JLabel LB_SearchJanCd		= B00110FrameParts.JLabelSet(300,100,100,20,"BCD（バラ）:"		,11,1);
		JLabel LB_SearchItemMdNo	= B00110FrameParts.JLabelSet(300,125,100,20,"商品型番:"		,11,1);
		JLabel LB_SearchLot			= B00110FrameParts.JLabelSet(300,150,100,20,"ロット:"			,11,1);
		JLabel LB_SearchExpdate		= B00110FrameParts.JLabelSet(300,175,100,20,"消費期限:"		,11,1);
		JLabel LB_SearchActualDate	= B00110FrameParts.JLabelSet(300,200,100,20,"入荷実績日:"		,11,1);
		
		JLabel LB_SearchLoc			= B00110FrameParts.JLabelSet(580, 25,100,20,"ロケーション:"	,11,1);
		JLabel LB_SearchType		= B00110FrameParts.JLabelSet(580, 50,100,20,"ロケタイプ:"		,11,1);
		JLabel LB_SearchQty			= B00110FrameParts.JLabelSet(580, 75,100,20,"数量:"			,11,1);
		JLabel LB_SearchShipPlanQty	= B00110FrameParts.JLabelSet(580,100,100,20,"引当済数:"		,11,1);
		JLabel LB_SearchPossibleQty	= B00110FrameParts.JLabelSet(580,125,100,20,"出荷可能数:"		,11,1);
		
		JLabel LB_SortItemcdMode	= B00110FrameParts.JLabelSet(660,175,100,20,"並び順:"			,11,1);
		
		final JComboBox TB_SearchClCd		= B00110FrameParts.JComboBoxSet(	100, 25,200,20,B00100DefaultVariable.SearchClList[0],11);		//荷主コード
		final JComboBox TB_SearchWhCd		= B00110FrameParts.JComboBoxSet(	100, 50,200,20,B00100DefaultVariable.SearchWhList[0],11);		//倉庫コード
		final JComboBox TB_SearchClGpCD		= B00110FrameParts.JComboBoxSet(	100, 75,200,20,B00100DefaultVariable.SearchClGpList[0],11);	//荷主グループ
		
		final JTextField TB_SearchItemCd	= B00110FrameParts.JTextFieldSet(	400, 25,100,20,""	,11,0);						//商品コード
		final JTextField TB_SearchItemName	= B00110FrameParts.JTextFieldSet(	400, 50,100,20,""	,11,0);						//商品名
		final JTextField TB_SearchClItemCd	= B00110FrameParts.JTextFieldSet(	400, 75,100,20,""	,11,0);					//荷主商品コード
		final JTextField TB_SearchJanCd		= B00110FrameParts.JTextFieldSet(	400,100,100,20,""	,11,0);						//BCD（バラ）
		final JTextField TB_SearchItemMdNo	= B00110FrameParts.JTextFieldSet(	400,125,100,20,""	,11,0);						//商品型番
		final JTextField TB_SearchLot						= B00110FrameParts.JTextFieldSet(				400,150,100,20,""	,11,0);						//ロット
		final JFormattedTextField TB_SearchExpdateMin		= B00110FrameParts.JFormattedTextFieldSet(	400,175, 70,20,""	,11,0,"");		//消費期限開始
		final JFormattedTextField TB_SearchExpdateMax		= B00110FrameParts.JFormattedTextFieldSet(	550,175, 70,20,""	,11,0,"");		//消費期限終了
		final JFormattedTextField TB_SearchActualDateMin	= B00110FrameParts.JFormattedTextFieldSet(	400,200, 70,20,""	,11,0,"");		//入荷実績日開始
		final JFormattedTextField TB_SearchActualDateMax	= B00110FrameParts.JFormattedTextFieldSet(	550,200, 70,20,""	,11,0,"");		//入荷実績日終了
		
		final JTextField TB_SearchLoc		= B00110FrameParts.JTextFieldSet(	680, 25,100,20,""	,11,0);					//ロケーション
		final JComboBox TB_LocExactMatch	= B00110FrameParts.JComboBoxSet(	780, 25, 80,20,LocExactMatchList,11);		//ロケーション一致条件0:前方一致　1:完全一致
		final JComboBox TB_SearchType		= B00110FrameParts.JComboBoxSet(	680, 50,100,20,B00100DefaultVariable.SearchLocType[0],11);	//ロケタイプ
		final JFormattedTextField TB_SearchQtyMin			= B00110FrameParts.JFormattedTextFieldSet(	680, 75, 70,20,"",11,1,"####");		//数量最小
		final JFormattedTextField TB_SearchQtyMax			= B00110FrameParts.JFormattedTextFieldSet(	790, 75, 70,20,"",11,1,"####");		//数量最大
		final JFormattedTextField TB_SearchShipPlanQtyMin	= B00110FrameParts.JFormattedTextFieldSet(	680,100, 70,20,"",11,1,"####");		//引当済数最小
		final JFormattedTextField TB_SearchShipPlanQtyMax	= B00110FrameParts.JFormattedTextFieldSet(	790,100, 70,20,"",11,1,"####");		//引当済数最大
		final JFormattedTextField TB_SearchPossibleQtyMin	= B00110FrameParts.JFormattedTextFieldSet(	680,125, 70,20,"",11,1,"####");		//出荷可能数最小
		final JFormattedTextField TB_SearchPossibleQtyMax	= B00110FrameParts.JFormattedTextFieldSet(	790,125, 70,20,"",11,1,"####");		//出荷可能数最大
		
		final JComboBox TB_SortItemcdMode	= B00110FrameParts.JComboBoxSet(760,175,100,20,SortModeList,11);	//並び順 0:ロケ順 1:商品CD順
		
		JLabel LB2_SearchItemCd			= B00110FrameParts.JLabelSet(500, 25, 80,20,"と一致"	,11,0);
		JLabel LB2_SearchItemName		= B00110FrameParts.JLabelSet(500, 50, 80,20,"を含む"	,11,0);
		JLabel LB2_SearchClItemCd		= B00110FrameParts.JLabelSet(500, 75, 80,20,"と一致"	,11,0);
		JLabel LB2_SearchJanCd			= B00110FrameParts.JLabelSet(500,100, 80,20,"と一致"	,11,0);
		JLabel LB2_SearchItemMdNo		= B00110FrameParts.JLabelSet(500,125, 80,20,"と一致"	,11,0);
		JLabel LB2_SearchLot			= B00110FrameParts.JLabelSet(500,150, 80,20,"と一致"	,11,0);
		JLabel LB2_SearchExpdate		= B00110FrameParts.JLabelSet(510,175, 40,20,"～"		,11,2);
		JLabel LB2_SearchActualDate		= B00110FrameParts.JLabelSet(510,200, 40,20,"～"		,11,2);
		
		JLabel LB2_SearchQty			= B00110FrameParts.JLabelSet(750, 75, 40,20,"～"		,11,2);
		JLabel LB2_SearchShipPlanQty	= B00110FrameParts.JLabelSet(750,100, 40,20,"～"		,11,2);
		JLabel LB2_SearchPossibleQty	= B00110FrameParts.JLabelSet(750,125, 40,20,"～"		,11,2);
		
		TB_SearchClCd.setSelectedIndex(GetSelectIndex(B00100DefaultVariable.SearchClList[1],A00000Main.ClCd));	//荷主コード
		TB_SearchWhCd.setSelectedIndex(GetSelectIndex(B00100DefaultVariable.SearchWhList[1],A00000Main.ClWh));	//倉庫コード
		TB_SearchClGpCD.setSelectedIndex(GetSelectIndex(B00100DefaultVariable.SearchClGpList[1],A00000Main.ClGp));	//荷主グループ
		
		TB_SearchClCd.setEnabled(false);	//荷主コード
		TB_SearchWhCd.setEnabled(false);	//倉庫コード
		TB_SearchClGpCD.setEnabled(false);	//荷主グループ
		
		
		//消費期限進む戻るボタン
		JButton SearchExpdateMinAfterBtn	= B00110FrameParts.BtnSet(470,175, 40,10,"▲",6);
		JButton SearchExpdateMinBeforeBtn	= B00110FrameParts.BtnSet(470,185, 40,10,"▼",6);
		JButton SearchExpdateMaxAfterBtn	= B00110FrameParts.BtnSet(620,175, 40,10,"▲",6);
		JButton SearchExpdateMaxBeforeBtn	= B00110FrameParts.BtnSet(620,185, 40,10,"▼",6);
		//消費期限進む戻るボタン押下事の挙動
		SearchExpdateMinAfterBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				String NowDate = B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[0];
				String GetDate = TB_SearchExpdateMin.getText();
				String SetDate = NowDate;
				if(null==GetDate||"".equals(GetDate)) {
				}else {
					Timestamp WT	= B00050ToolsDateTimeControl.dtmTimestamp2(GetDate)[0];
					WT				= B00050ToolsDateTimeControl.ndate_after(WT, 1);
					SetDate			= B00050ToolsDateTimeControl.dtmString2(WT)[0];
				}
				TB_SearchExpdateMin.setText(SetDate);
			}
		});
		SearchExpdateMinBeforeBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				String NowDate = B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[0];
				String GetDate = TB_SearchExpdateMin.getText();
				String SetDate = NowDate;
				if(null==GetDate||"".equals(GetDate)) {
				}else {
					Timestamp WT	= B00050ToolsDateTimeControl.dtmTimestamp2(GetDate)[0];
					WT				= B00050ToolsDateTimeControl.ndate_before(WT, 1);
					SetDate			= B00050ToolsDateTimeControl.dtmString2(WT)[0];
				}
				TB_SearchExpdateMin.setText(SetDate);
			}
		});
		SearchExpdateMaxAfterBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				String NowDate = B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[0];
				String GetDate = TB_SearchExpdateMax.getText();
				String SetDate = NowDate;
				if(null==GetDate||"".equals(GetDate)) {
				}else {
					Timestamp WT	= B00050ToolsDateTimeControl.dtmTimestamp2(GetDate)[0];
					WT				= B00050ToolsDateTimeControl.ndate_after(WT, 1);
					SetDate			= B00050ToolsDateTimeControl.dtmString2(WT)[0];
				}
				TB_SearchExpdateMax.setText(SetDate);
			}
		});
		SearchExpdateMaxBeforeBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				String NowDate = B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[0];
				String GetDate = TB_SearchExpdateMax.getText();
				String SetDate = NowDate;
				if(null==GetDate||"".equals(GetDate)) {
				}else {
					Timestamp WT	= B00050ToolsDateTimeControl.dtmTimestamp2(GetDate)[0];
					WT				= B00050ToolsDateTimeControl.ndate_before(WT, 1);
					SetDate			= B00050ToolsDateTimeControl.dtmString2(WT)[0];
				}
				TB_SearchExpdateMax.setText(SetDate);
			}
		});
		
		//入荷実績日進む戻るボタン
		JButton SearchActualDateMinAfterBtn		= B00110FrameParts.BtnSet(470,200, 40,10,"▲",6);
		JButton SearchActualDateMinBeforeBtn	= B00110FrameParts.BtnSet(470,210, 40,10,"▼",6);
		JButton SearchActualDateMaxAfterBtn		= B00110FrameParts.BtnSet(620,200, 40,10,"▲",6);
		JButton SearchActualDateMaxBeforeBtn	= B00110FrameParts.BtnSet(620,210, 40,10,"▼",6);
		//入荷実績日進む戻るボタン押下事の挙動
		SearchActualDateMinAfterBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				String NowDate = B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[0];
				String GetDate = TB_SearchActualDateMin.getText();
				String SetDate = NowDate;
				if(null==GetDate||"".equals(GetDate)) {
				}else {
					Timestamp WT	= B00050ToolsDateTimeControl.dtmTimestamp2(GetDate)[0];
					WT				= B00050ToolsDateTimeControl.ndate_after(WT, 1);
					SetDate			= B00050ToolsDateTimeControl.dtmString2(WT)[0];
				}
				TB_SearchActualDateMin.setText(SetDate);
			}
		});
		SearchActualDateMinBeforeBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				String NowDate = B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[0];
				String GetDate = TB_SearchActualDateMin.getText();
				String SetDate = NowDate;
				if(null==GetDate||"".equals(GetDate)) {
				}else {
					Timestamp WT	= B00050ToolsDateTimeControl.dtmTimestamp2(GetDate)[0];
					WT				= B00050ToolsDateTimeControl.ndate_before(WT, 1);
					SetDate			= B00050ToolsDateTimeControl.dtmString2(WT)[0];
				}
				TB_SearchActualDateMin.setText(SetDate);
			}
		});
		SearchActualDateMaxAfterBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				String NowDate = B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[0];
				String GetDate = TB_SearchActualDateMax.getText();
				String SetDate = NowDate;
				if(null==GetDate||"".equals(GetDate)) {
				}else {
					Timestamp WT	= B00050ToolsDateTimeControl.dtmTimestamp2(GetDate)[0];
					WT				= B00050ToolsDateTimeControl.ndate_after(WT, 1);
					SetDate			= B00050ToolsDateTimeControl.dtmString2(WT)[0];
				}
				TB_SearchActualDateMax.setText(SetDate);
			}
		});
		SearchActualDateMaxBeforeBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				String NowDate = B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[0];
				String GetDate = TB_SearchActualDateMax.getText();
				String SetDate = NowDate;
				if(null==GetDate||"".equals(GetDate)) {
				}else {
					Timestamp WT	= B00050ToolsDateTimeControl.dtmTimestamp2(GetDate)[0];
					WT				= B00050ToolsDateTimeControl.ndate_before(WT, 1);
					SetDate			= B00050ToolsDateTimeControl.dtmString2(WT)[0];
				}
				TB_SearchActualDateMax.setText(SetDate);
			}
		});
		
		
		PN_Search.add(SearchExpdateMinAfterBtn);
		PN_Search.add(SearchExpdateMinBeforeBtn);
		PN_Search.add(SearchExpdateMaxAfterBtn);
		PN_Search.add(SearchExpdateMaxBeforeBtn);
		
		PN_Search.add(SearchActualDateMinAfterBtn);
		PN_Search.add(SearchActualDateMinBeforeBtn);
		PN_Search.add(SearchActualDateMaxAfterBtn);
		PN_Search.add(SearchActualDateMaxBeforeBtn);
		
		PN_Search.add(LB_SearchClCd);
		PN_Search.add(LB_SearchWhCd);
		PN_Search.add(LB_SearchClGpCD);
		PN_Search.add(LB_SearchLoc);
		PN_Search.add(LB_SearchType);
		PN_Search.add(LB_SearchQty);
		PN_Search.add(LB_SearchShipPlanQty);
		PN_Search.add(LB_SearchPossibleQty);
		PN_Search.add(LB_SearchLot);
		PN_Search.add(LB_SearchExpdate);
		PN_Search.add(LB_SearchActualDate);
		PN_Search.add(LB_SearchItemCd);
		PN_Search.add(LB_SearchItemName);
		PN_Search.add(LB_SearchClItemCd);
		PN_Search.add(LB_SearchJanCd);
		PN_Search.add(LB_SearchItemMdNo);
		PN_Search.add(LB_SortItemcdMode);
		
		PN_Search.add(TB_SearchClCd);
		PN_Search.add(TB_SearchWhCd);
		PN_Search.add(TB_SearchClGpCD);
		PN_Search.add(TB_SearchLoc);
		PN_Search.add(TB_LocExactMatch);
		PN_Search.add(TB_SearchType);
		PN_Search.add(TB_SearchQtyMin);
		PN_Search.add(TB_SearchQtyMax);
		PN_Search.add(TB_SearchShipPlanQtyMin);
		PN_Search.add(TB_SearchShipPlanQtyMax);
		PN_Search.add(TB_SearchPossibleQtyMin);
		PN_Search.add(TB_SearchPossibleQtyMax);
		PN_Search.add(TB_SearchLot);
		PN_Search.add(TB_SearchExpdateMin);
		PN_Search.add(TB_SearchExpdateMax);
		PN_Search.add(TB_SearchActualDateMin);
		PN_Search.add(TB_SearchActualDateMax);
		PN_Search.add(TB_SearchItemCd);
		PN_Search.add(TB_SearchItemName);
		PN_Search.add(TB_SearchClItemCd);
		PN_Search.add(TB_SearchJanCd);
		PN_Search.add(TB_SearchItemMdNo);
		PN_Search.add(TB_SortItemcdMode);
		
		PN_Search.add(LB2_SearchQty);
		PN_Search.add(LB2_SearchShipPlanQty);
		PN_Search.add(LB2_SearchPossibleQty);
		PN_Search.add(LB2_SearchLot);
		PN_Search.add(LB2_SearchExpdate);
		PN_Search.add(LB2_SearchActualDate);
		PN_Search.add(LB2_SearchItemCd);
		PN_Search.add(LB2_SearchItemName);
		PN_Search.add(LB2_SearchClItemCd);
		PN_Search.add(LB2_SearchJanCd);
		PN_Search.add(LB2_SearchItemMdNo);
		
		Object[][] RtStockRt = T00030StockRt.RtStockRt();
		
		String[] columnNames01 = new String[RtStockRt.length+1];
		
		columnNames01[0] = "Fg";
		for(int i=0;i<RtStockRt.length;i++) {
			columnNames01[1+i] = ""+RtStockRt[i][3];
		}
		
		//編集可能カラムの指定
		B10010TableControl.RenewTgt = new int[1];
		B10010TableControl.RenewTgt[0] = 0;

		final DefaultTableModel tableModel_ms01 = new B10010TableControl.MyTableModel01(columnNames01,0);
		
		final JTable tb01 = new JTable(tableModel_ms01);
		tb01.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tb01.setRowHeight(20*A00000Main.Mul/A00000Main.Div);
		tb01.setFont(new Font(A00000Main.DefaultFont, Font.PLAIN, 12*A00000Main.Mul/A00000Main.Div));
		
		DefaultTableColumnModel columnModel01
		= (DefaultTableColumnModel)tb01.getColumnModel();
		
		//列幅初期設定 表示位置設定
		TableColumn column = null;
		
		column = columnModel01.getColumn( 0);	column.setPreferredWidth( 30*A00000Main.Mul/A00000Main.Div);	//FG
		
		for(int i=0;i<RtStockRt.length;i++) {
			if("int".equals((String)RtStockRt[i][2])||"float".equals((String)RtStockRt[i][2])) {
				column = columnModel01.getColumn(1+i);	column.setPreferredWidth( 90*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.rightCellRenderer());
			}else {
				column = columnModel01.getColumn(1+i);	column.setPreferredWidth( 90*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());
			}
		}
		//スクロール用設定
		JScrollPane scpn01 = B00110FrameParts.JScrollPaneSet(10,300,1160,325,tb01);
		main_fm.add(scpn01);
		
		PN_Search.add(PN_SearchLabel);
		main_fm.add(PN_Search);
		
		//検索ボタン
		JButton SearchBtn 		= B00110FrameParts.BtnSet(760,200,100,20,"検索",11);
		PN_Search.add(SearchBtn);
		
		//条件クリアボタン
		JButton SearchCrearBtn 	= B00110FrameParts.BtnSet(100,0,100,20,"条件クリア",10);
		PN_Search.add(SearchCrearBtn);
		
		//CSVボタン
		JButton CsvBtn = B00110FrameParts.BtnSet(			 10,660,100,20,"csv出力",11);
		main_fm.add(CsvBtn);
		
		//Excel出力ボタン
		JButton ExcelBtn = B00110FrameParts.BtnSet(		130,660,100,20,"Excel出力",11);
		main_fm.add(ExcelBtn);
		
		//在庫表出力ボタン
		JButton ListPrintBtn = B00110FrameParts.BtnSet(	250,660,100,20,"在庫表出力",11);
		main_fm.add(ListPrintBtn);
		
		
		JLabel LB_StockAdjust 	= B00110FrameParts.JLabelSet(370,640,100,20,"チェック行を"	,11,2);
		main_fm.add(LB_StockAdjust);
		//在庫調整ボタン
		JButton StockAdjustBtn 	= B00110FrameParts.BtnSet(	370,660,100,20,"在庫調整",11);
		main_fm.add(StockAdjustBtn);
		
		main_fm.add(PN_Search);
		
		RenewFg = true;
		main_fm.setVisible(true);
		
		//在庫調整ボタン押下時の挙動
		StockAdjustBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				String TgtWhCd 			= "";
				String TgtClCd 			= "";
				String TgtLoc 			= "";
				String TgtItemCd 		= "";
				String TgtLot 			= "";
				String TgtExpdate 		= "";
				String TgtActualDate 	= "";
				
				int RowCount 	= tableModel_ms01.getRowCount();
				for(int i=0;i<RowCount;i++) {
					if((boolean)tableModel_ms01.getValueAt(i, 0)) {
						TgtWhCd 		= ""+tableModel_ms01.getValueAt(i,T00030StockRt.ColWhCd+1);
						TgtClCd 		= ""+tableModel_ms01.getValueAt(i,T00030StockRt.ColClCd+1);
						TgtLoc 			= ""+tableModel_ms01.getValueAt(i,T00030StockRt.ColLoc+1);
						TgtItemCd 		= ""+tableModel_ms01.getValueAt(i,T00030StockRt.ColItemCd+1);
						TgtLot 			= ""+tableModel_ms01.getValueAt(i,T00030StockRt.ColLot+1);
						TgtExpdate 		= ""+tableModel_ms01.getValueAt(i,T00030StockRt.ColExpdate+1);
						TgtActualDate 	= ""+tableModel_ms01.getValueAt(i,T00030StockRt.ColActualDate+1);
					}
				}
				
				main_fm.setVisible(false);
				main_fm.dispose();
				WT0001020StockAdjust.StockAdjust(0,0,TgtWhCd,TgtClCd,TgtLoc,TgtItemCd,TgtLot,TgtExpdate,TgtActualDate);
			}
		});
		
		//在庫表出力ボタン押下時の挙動
		ListPrintBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					int RowCount 	= tableModel_ms01.getRowCount();
					int ClumnCount 	= tableModel_ms01.getColumnCount();
					
					Object[][] PrintData = new Object[RowCount][ClumnCount-1]; 
					
					for(int i=0;i<RowCount;i++) {
						for(int i01=1;i01<ClumnCount;i01++) {
							PrintData[i][i01-1] = ""+tableModel_ms01.getValueAt(i, i01);
						}
					}
					WTList0001100StockList.StockList(PrintData);
					RenewFg = true;
				}
			}
		});
		
		//検索ボタン押下時の挙動
		SearchBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					int RowCount = tableModel_ms01.getRowCount();
					for(int i=0;i<RowCount;i++) {
						tableModel_ms01.removeRow(0);
					}
					
					String GetSearchClCd			= B00100DefaultVariable.SearchClList[1][TB_SearchClCd.getSelectedIndex()];		//荷主コード
					String GetSearchWhCd			= B00100DefaultVariable.SearchWhList[1][TB_SearchWhCd.getSelectedIndex()];		//倉庫コード
					String GetSearchClGpCD			= B00100DefaultVariable.SearchClGpList[1][TB_SearchClGpCD.getSelectedIndex()];	//荷主グループ
					
					String GetSearchItemCd			= TB_SearchItemCd.getText();			//商品コード
					String GetSearchItemName		= TB_SearchItemName.getText();			//商品名
					String GetSearchClItemCd		= TB_SearchClItemCd.getText();			//荷主商品コード
					String GetSearchJanCd			= TB_SearchJanCd.getText();				//BCD（バラ）
					String GetSearchItemMdNo		= TB_SearchItemMdNo.getText();			//商品型番
					String GetSearchLot				= TB_SearchLot.getText();				//ロット
					String GetSearchExpdateMin		= TB_SearchExpdateMin.getText();		//消費期限開始
					String GetSearchExpdateMax		= TB_SearchExpdateMax.getText();		//消費期限終了
					String GetSearchActualDateMin	= TB_SearchActualDateMin.getText();		//入荷実績日開始
					String GetSearchActualDateMax	= TB_SearchActualDateMax.getText();		//入荷実績日終了
					
					String GetSearchLoc				= TB_SearchLoc.getText();				//ロケーション
					String GetSearchType			= B00100DefaultVariable.SearchLocType[1][TB_SearchType.getSelectedIndex()];	//ロケタイプ
					String GetSearchQtyMin			= TB_SearchQtyMin.getText();			//数量最小
					String GetSearchQtyMax			= TB_SearchQtyMax.getText();			//数量最大
					String GetSearchShipPlanQtyMin	= TB_SearchShipPlanQtyMin.getText();	//引当済数最小
					String GetSearchShipPlanQtyMax	= TB_SearchShipPlanQtyMax.getText();	//引当済数最大
					String GetSearchPossibleQtyMin	= TB_SearchPossibleQtyMin.getText();	//出荷可能数最小
					String GetSearchPossibleQtyMax	= TB_SearchPossibleQtyMax.getText();	//出荷可能数最大
					
					
					GetSearchClCd			= B00020ToolsTextControl.Trim(GetSearchClCd);				//荷主コード
					GetSearchWhCd			= B00020ToolsTextControl.Trim(GetSearchWhCd);				//倉庫コード
					GetSearchClGpCD			= B00020ToolsTextControl.Trim(GetSearchClGpCD);				//荷主グループ
					
					GetSearchItemCd			= B00020ToolsTextControl.Trim(GetSearchItemCd);				//商品コード
					GetSearchItemName		= B00020ToolsTextControl.Trim(GetSearchItemName);			//商品名
					GetSearchClItemCd		= B00020ToolsTextControl.Trim(GetSearchClItemCd);			//荷主商品コード
					GetSearchJanCd			= B00020ToolsTextControl.Trim(GetSearchJanCd);				//BCD（バラ）
					GetSearchItemMdNo		= B00020ToolsTextControl.Trim(GetSearchItemMdNo);			//商品型番
					GetSearchLot			= B00020ToolsTextControl.Trim(GetSearchLot);				//ロット
					GetSearchExpdateMin		= B00020ToolsTextControl.Trim(GetSearchExpdateMin);			//消費期限開始
					GetSearchExpdateMax		= B00020ToolsTextControl.Trim(GetSearchExpdateMax);			//消費期限終了
					GetSearchActualDateMin	= B00020ToolsTextControl.Trim(GetSearchActualDateMin);		//入荷実績日開始
					GetSearchActualDateMax	= B00020ToolsTextControl.Trim(GetSearchActualDateMax);		//入荷実績日終了
					
					GetSearchLoc			= B00020ToolsTextControl.Trim(GetSearchLoc);				//ロケーション
					GetSearchType			= B00020ToolsTextControl.Trim(GetSearchType);				//ロケタイプ
					GetSearchQtyMin			= B00020ToolsTextControl.Trim(GetSearchQtyMin);				//数量最小
					GetSearchQtyMax			= B00020ToolsTextControl.Trim(GetSearchQtyMax);				//数量最大
					GetSearchShipPlanQtyMin	= B00020ToolsTextControl.Trim(GetSearchShipPlanQtyMin);		//引当済数最小
					GetSearchShipPlanQtyMax	= B00020ToolsTextControl.Trim(GetSearchShipPlanQtyMax);		//引当済数最大
					GetSearchPossibleQtyMin	= B00020ToolsTextControl.Trim(GetSearchPossibleQtyMin);		//出荷可能数最小
					GetSearchPossibleQtyMax	= B00020ToolsTextControl.Trim(GetSearchPossibleQtyMax);		//出荷可能数最大
					
					
					GetSearchType			= B00020ToolsTextControl.num_only_String(GetSearchType);					//ロケタイプ
					GetSearchQtyMin			= B00020ToolsTextControl.num_only_String02(GetSearchQtyMin);				//数量最小
					GetSearchQtyMax			= B00020ToolsTextControl.num_only_String02(GetSearchQtyMax);				//数量最大
					GetSearchShipPlanQtyMin	= B00020ToolsTextControl.num_only_String02(GetSearchShipPlanQtyMin);		//引当済数最小
					GetSearchShipPlanQtyMax	= B00020ToolsTextControl.num_only_String02(GetSearchShipPlanQtyMax);		//引当済数最大
					GetSearchPossibleQtyMin	= B00020ToolsTextControl.num_only_String02(GetSearchPossibleQtyMin);		//出荷可能数最小
					GetSearchPossibleQtyMax	= B00020ToolsTextControl.num_only_String02(GetSearchPossibleQtyMax);		//出荷可能数最大
					
					GetSearchExpdateMin		= B00050ToolsDateTimeControl.DateFormat(GetSearchExpdateMin);			//消費期限開始
					GetSearchExpdateMax		= B00050ToolsDateTimeControl.DateFormat(GetSearchExpdateMax);			//消費期限終了
					GetSearchActualDateMin	= B00050ToolsDateTimeControl.DateFormat(GetSearchActualDateMin);		//入荷実績日開始
					GetSearchActualDateMax	= B00050ToolsDateTimeControl.DateFormat(GetSearchActualDateMax);		//入荷実績日終了
					
					ArrayList<String> SearchClCd				= new ArrayList<String>();			//荷主コード
					ArrayList<String> SearchWhCd				= new ArrayList<String>();			//倉庫コード
					ArrayList<String> SearchClGpCD				= new ArrayList<String>();			//荷主グループCD
					ArrayList<String> SearchLoc					= new ArrayList<String>();			//ロケーション
					ArrayList<Integer> SearchType				= new ArrayList<Integer>();			//ロケタイプ　0:通常　1:保管　8:入荷時　9:引当禁止
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
					boolean LocExactMatch = false;													//ロケーション完全一致
					boolean AllSearch = false;														//全件検索
					boolean SortItemcdMode = false;													//商品CDでソート
					
					if(!"".equals(GetSearchClCd)){SearchClCd.add(GetSearchClCd);}					//荷主コード
					if(!"".equals(GetSearchWhCd)){SearchWhCd.add(GetSearchWhCd);}					//倉庫コード
					if(!"".equals(GetSearchClGpCD)){SearchClGpCD.add(GetSearchClGpCD);}				//荷主グループ
					
					if(!"".equals(GetSearchItemCd)){SearchItemCd.add(GetSearchItemCd);}				//商品コード
					if(!"".equals(GetSearchItemName)){SearchItemName.add(GetSearchItemName);}		//商品名
					if(!"".equals(GetSearchClItemCd)){SearchClItemCd.add(GetSearchClItemCd);}		//荷主商品コード
					if(!"".equals(GetSearchJanCd)){SearchJanCd.add(GetSearchJanCd);}				//BCD（バラ）
					if(!"".equals(GetSearchItemMdNo)){SearchItemMdNo.add(GetSearchItemMdNo);}		//商品型番
					if(!"".equals(GetSearchLot)){SearchLot.add(GetSearchLot);}						//ロット
					if(!"".equals(GetSearchExpdateMin)){SearchExpdateMin.add(GetSearchExpdateMin);}				//消費期限開始
					if(!"".equals(GetSearchExpdateMax)){SearchExpdateMax.add(GetSearchExpdateMax);}				//消費期限終了
					if(!"".equals(GetSearchActualDateMin)){SearchActualDateMin.add(GetSearchActualDateMin);}	//入荷実績日開始
					if(!"".equals(GetSearchActualDateMax)){SearchActualDateMax.add(GetSearchActualDateMax);}	//入荷実績日終了
					
					if(!"".equals(GetSearchLoc)){SearchLoc.add(GetSearchLoc);}									//ロケーション
					if(!"".equals(GetSearchType)){SearchType.add(Integer.parseInt(GetSearchType));}			//ロケタイプ
					if(!"".equals(GetSearchQtyMin)){SearchQtyMin.add(Integer.parseInt(GetSearchQtyMin));}		//数量最小
					if(!"".equals(GetSearchQtyMax)){SearchQtyMax.add(Integer.parseInt(GetSearchQtyMax));}		//数量最大
					if(!"".equals(GetSearchShipPlanQtyMin)){SearchShipPlanQtyMin.add(Integer.parseInt(GetSearchShipPlanQtyMin));}			//引当済数最小
					if(!"".equals(GetSearchShipPlanQtyMax)){SearchShipPlanQtyMax.add(Integer.parseInt(GetSearchShipPlanQtyMax));}			//引当済数最大
					if(!"".equals(GetSearchPossibleQtyMin)){SearchPossibleQtyMin.add(Integer.parseInt(GetSearchPossibleQtyMin));}			//出荷可能数最小
					if(!"".equals(GetSearchPossibleQtyMax)){SearchPossibleQtyMax.add(Integer.parseInt(GetSearchPossibleQtyMax));}			//出荷可能数最大
					
					if(0==TB_LocExactMatch.getSelectedIndex()) {
						LocExactMatch = false;
					}else {
						LocExactMatch = true;
					}
					
					if(0==TB_SortItemcdMode.getSelectedIndex()) {
						SortItemcdMode = false;
					}else {
						SortItemcdMode = true;
					}
					
					Object[][] StockRt= T00030StockRt.StockRt(
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
						tableModel_ms01.addRow(SetOb);
					}
					if(0<StockRt.length) {
						B10010TableControl.AddSortON(tb01,tableModel_ms01);
					}else {
						B10010TableControl.AddSortOFF(tb01,tableModel_ms01);
					}
					RenewFg = true;
				}
			}
		});
		
		
		//条件クリアボタン押下時の挙動
		SearchCrearBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					/**************************************************************
					検索条件初期値に戻す
					***************************************************************/
					TB_SearchClCd.setSelectedIndex(GetSelectIndex(B00100DefaultVariable.SearchClList[1],A00000Main.ClCd));	//荷主コード
					TB_SearchWhCd.setSelectedIndex(GetSelectIndex(B00100DefaultVariable.SearchWhList[1],A00000Main.ClWh));	//倉庫コード
					TB_SearchClGpCD.setSelectedIndex(GetSelectIndex(B00100DefaultVariable.SearchClGpList[1],A00000Main.ClGp));	//荷主グループ
					
					TB_SearchItemCd.setText("");			//商品コード
					TB_SearchItemName.setText("");			//商品名
					TB_SearchClItemCd.setText("");			//荷主商品コード
					TB_SearchJanCd.setText("");				//BCD（バラ）
					TB_SearchItemMdNo.setText("");			//商品型番
					TB_SearchLot.setText("");				//ロット
					TB_SearchExpdateMin.setText("");		//消費期限開始
					TB_SearchExpdateMax.setText("");		//消費期限終了
					TB_SearchActualDateMin.setText("");		//入荷実績日開始
					TB_SearchActualDateMax.setText("");		//入荷実績日終了
					
					TB_SearchLoc.setText("");				//ロケーション
					TB_LocExactMatch.setSelectedIndex(0);	//ロケーション一致条件0:前方一致　1:完全一致
					TB_SearchType.setSelectedIndex(0);		//ロケタイプ
					TB_SearchQtyMin.setText("");			//数量
					TB_SearchQtyMax.setText("");			//数量
					TB_SearchShipPlanQtyMin.setText("");	//引当済数
					TB_SearchShipPlanQtyMax.setText("");	//引当済数
					TB_SearchPossibleQtyMin.setText("");	//出荷可能数
					TB_SearchPossibleQtyMax.setText("");	//出荷可能数
					
					TB_SortItemcdMode.setSelectedIndex(0);	//並び順 0:ロケ順 1:商品CD順
					
					/**************************************************************
					検索結果消す
					***************************************************************/
					int RowCount = tableModel_ms01.getRowCount();
					for(int i=0;i<RowCount;i++) {
						tableModel_ms01.removeRow(0);
					}
					B10010TableControl.AddSortOFF(tb01,tableModel_ms01);
					RenewFg = true;
				}
			}
		});
		
		//チェックボックス操作時の挙動
		tableModel_ms01.addTableModelListener(new TableModelListener(){
			public void tableChanged(TableModelEvent e){
				if(RenewFg) {
					RenewFg = false;
					int row_count = tb01.getRowCount();
					Boolean setBL=Boolean.valueOf(false);
					for(int i=0;i<row_count;i++){
						if(i!=e.getFirstRow()){
							tableModel_ms01.setValueAt(setBL, i, 0);
						}else {
	
						}
					}
					RenewFg = true;
				}
			}
		});
		
		//CSVボタン押下時の挙動
		CsvBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					B10010TableControl.TableOutPutCsv("出力先選択","在庫検索結果",tb01);
					RenewFg = true;
				}
			}
		});
		
		//エクセル出力ボタン押下時の挙動
		ExcelBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					B10010TableControl.TableOutPutExcel("出力先選択","在庫検索結果",tb01);
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
				W00030WorkMain.WorkMain(0,0);
			}
		});
	}
	
	private static int GetSelectIndex(String[] SelectList,String TgtData ) {
		int rt = 0;
		for(int i=0;i<SelectList.length;i++) {
			if(TgtData.equals(SelectList[i])) {
				rt	= i;
				i	= SelectList.length+1;
			}
		}
		return rt;
	}
}