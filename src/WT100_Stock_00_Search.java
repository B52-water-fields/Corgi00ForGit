import java.awt.Font;
import java.awt.event.ActionEvent;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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

public class WT100_Stock_00_Search{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	
	
	static String DefaultSearchClCd;
	static String DefaultSearchWhCd;
	static String DefaultSearchClGpCD;
	static String DefaultSearchItemCd;
	static String DefaultSearchItemName;
	static String DefaultSearchClItemCd;
	static String DefaultSearchJanCd;
	static String DefaultSearchItemMdNo;
	static String DefaultSearchLot;
	static String DefaultSearchExpdateMin;
	static String DefaultSearchExpdateMax;
	static String DefaultSearchActualDateMin;
	static String DefaultSearchActualDateMax;
	
	static String DefaultSearchLoc;
	static String DefaultSearchType;
	static String DefaultSearchQtyMin;
	static String DefaultSearchQtyMax;
	static String DefaultSearchShipPlanQtyMin;
	static String DefaultSearchShipPlanQtyMax;
	static String DefaultSearchPossibleQtyMin;
	static String GDefaultSearchPossibleQtyMax;
	
	static boolean DefaultSearchLotWhitespace;
	static boolean DefaultLocExactMatch;
	static boolean DefaultSortItemcdMode;
	
	
	public static void StockSearch(int x,int y) {
		A00000_Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		
		final JFrame main_fm = B100_FrameParts.FrameCreate(x,y,1200,750,"Corgi00在庫検索","ZK");
		JLabel userinfo = B100_FrameParts.UserInfo();
		JButton exit_btn = B100_FrameParts.ExitBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		
		//検索条件パネル
		JPanel PN_Search = B100_FrameParts.JPanelSet(10,40,1160,250,"White");
		JLabel PN_SearchLabel = B100_FrameParts.JLabelSet(0,0,100,20,"検索条件",11,0);
		PN_Search.add(PN_SearchLabel);
		
		String[] LocExactMatchList	= {"で始まる","と一致"};
		String[] SortModeList		= {"ロケ順","商品CD順"};
		
		//検索条件
		JLabel LB_SearchClCd		= B100_FrameParts.JLabelSet(  0, 25,100,20,"荷主コード:"		,11,1);
		JLabel LB_SearchWhCd		= B100_FrameParts.JLabelSet(  0, 50,100,20,"倉庫コード:"		,11,1);
		JLabel LB_SearchClGpCD		= B100_FrameParts.JLabelSet(  0, 75,100,20,"荷主グループ:"		,11,1);
		
		JLabel LB_SearchItemCd		= B100_FrameParts.JLabelSet(300, 25,100,20,"商品コード:"		,11,1);
		JLabel LB_SearchItemName	= B100_FrameParts.JLabelSet(300, 50,100,20,"商品名:"			,11,1);
		JLabel LB_SearchClItemCd	= B100_FrameParts.JLabelSet(300, 75,100,20,"荷主商品コード:"	,11,1);
		JLabel LB_SearchJanCd		= B100_FrameParts.JLabelSet(300,100,100,20,"BCD（バラ）:"		,11,1);
		JLabel LB_SearchItemMdNo	= B100_FrameParts.JLabelSet(300,125,100,20,"商品型番:"			,11,1);
		JLabel LB_SearchLot			= B100_FrameParts.JLabelSet(300,150,100,20,"ロット:"			,11,1);
		JLabel LB_SearchExpdate		= B100_FrameParts.JLabelSet(300,175,100,20,"消費期限:"			,11,1);
		JLabel LB_SearchActualDate	= B100_FrameParts.JLabelSet(300,200,100,20,"入荷実績日:"		,11,1);
		
		JLabel LB_SearchLoc			= B100_FrameParts.JLabelSet(580, 25,100,20,"ロケーション:"		,11,1);
		JLabel LB_SearchType		= B100_FrameParts.JLabelSet(580, 50,100,20,"ロケタイプ:"		,11,1);
		JLabel LB_SearchQty			= B100_FrameParts.JLabelSet(580, 75,100,20,"数量:"				,11,1);
		JLabel LB_SearchShipPlanQty	= B100_FrameParts.JLabelSet(580,100,100,20,"引当済数:"			,11,1);
		JLabel LB_SearchPossibleQty	= B100_FrameParts.JLabelSet(580,125,100,20,"出荷可能数:"		,11,1);
		
		JLabel LB_SortItemcdMode	= B100_FrameParts.JLabelSet(660,175,100,20,"並び順:"			,11,1);
		
		final JComboBox TB_SearchClCd		= B100_FrameParts.JComboBoxSet(	100, 25,200,20,B100_DefaultVariable.SearchClList[0],11);		//荷主コード
		final JComboBox TB_SearchWhCd		= B100_FrameParts.JComboBoxSet(	100, 50,200,20,B100_DefaultVariable.SearchWhList[0],11);		//倉庫コード
		final JComboBox TB_SearchClGpCD		= B100_FrameParts.JComboBoxSet(	100, 75,200,20,B100_DefaultVariable.SearchClGpList[0],11);	//荷主グループ
		
		final JTextField TB_SearchItemCd	= B100_FrameParts.JTextFieldSet(	400, 25,100,20,""	,11,0);						//商品コード
		final JTextField TB_SearchItemName	= B100_FrameParts.JTextFieldSet(	400, 50,100,20,""	,11,0);						//商品名
		final JTextField TB_SearchClItemCd	= B100_FrameParts.JTextFieldSet(	400, 75,100,20,""	,11,0);						//荷主商品コード
		final JTextField TB_SearchJanCd		= B100_FrameParts.JTextFieldSet(	400,100,100,20,""	,11,0);						//BCD（バラ）
		final JTextField TB_SearchItemMdNo	= B100_FrameParts.JTextFieldSet(	400,125,100,20,""	,11,0);						//商品型番
		final JTextField TB_SearchLot						= B100_FrameParts.JTextFieldSet(				400,150,100,20,""	,11,0);			//ロット
		final JFormattedTextField TB_SearchExpdateMin		= B100_FrameParts.JFormattedTextFieldSet(	400,175, 70,20,""	,11,0,"YYYY/MM/DD");		//消費期限開始
		final JFormattedTextField TB_SearchExpdateMax		= B100_FrameParts.JFormattedTextFieldSet(	550,175, 70,20,""	,11,0,"YYYY/MM/DD");		//消費期限終了
		final JFormattedTextField TB_SearchActualDateMin	= B100_FrameParts.JFormattedTextFieldSet(	400,200, 70,20,""	,11,0,"YYYY/MM/DD");		//入荷実績日開始
		final JFormattedTextField TB_SearchActualDateMax	= B100_FrameParts.JFormattedTextFieldSet(	550,200, 70,20,""	,11,0,"YYYY/MM/DD");		//入荷実績日終了
		
		final JCheckBox TB_SearchLotWhitespace				= B100_FrameParts.JCheckBoxSet(				580,150,200,20,"ロット空白文字を絞込条件に追加"	,11);
		
		final JTextField TB_SearchLoc		= B100_FrameParts.JTextFieldSet(	680, 25,100,20,""	,11,0);					//ロケーション
		final JComboBox TB_LocExactMatch	= B100_FrameParts.JComboBoxSet(	780, 25, 80,20,LocExactMatchList,11);		//ロケーション一致条件0:前方一致　1:完全一致
		final JComboBox TB_SearchType		= B100_FrameParts.JComboBoxSet(	680, 50,100,20,B100_DefaultVariable.SearchLocType[0],11);	//ロケタイプ
		final JFormattedTextField TB_SearchQtyMin			= B100_FrameParts.JFormattedTextFieldSet(	680, 75, 70,20,"",11,1,"####");		//数量最小
		final JFormattedTextField TB_SearchQtyMax			= B100_FrameParts.JFormattedTextFieldSet(	790, 75, 70,20,"",11,1,"####");		//数量最大
		final JFormattedTextField TB_SearchShipPlanQtyMin	= B100_FrameParts.JFormattedTextFieldSet(	680,100, 70,20,"",11,1,"####");		//引当済数最小
		final JFormattedTextField TB_SearchShipPlanQtyMax	= B100_FrameParts.JFormattedTextFieldSet(	790,100, 70,20,"",11,1,"####");		//引当済数最大
		final JFormattedTextField TB_SearchPossibleQtyMin	= B100_FrameParts.JFormattedTextFieldSet(	680,125, 70,20,"",11,1,"####");		//出荷可能数最小
		final JFormattedTextField TB_SearchPossibleQtyMax	= B100_FrameParts.JFormattedTextFieldSet(	790,125, 70,20,"",11,1,"####");		//出荷可能数最大
		
		final JComboBox TB_SortItemcdMode	= B100_FrameParts.JComboBoxSet(760,175,100,20,SortModeList,11);	//並び順 0:ロケ順 1:商品CD順
		
		JLabel LB2_SearchItemCd			= B100_FrameParts.JLabelSet(500, 25, 80,20,"と一致"	,11,0);
		JLabel LB2_SearchItemName		= B100_FrameParts.JLabelSet(500, 50, 80,20,"を含む"	,11,0);
		JLabel LB2_SearchClItemCd		= B100_FrameParts.JLabelSet(500, 75, 80,20,"と一致"	,11,0);
		JLabel LB2_SearchJanCd			= B100_FrameParts.JLabelSet(500,100, 80,20,"と一致"	,11,0);
		JLabel LB2_SearchItemMdNo		= B100_FrameParts.JLabelSet(500,125, 80,20,"と一致"	,11,0);
		JLabel LB2_SearchLot			= B100_FrameParts.JLabelSet(500,150, 80,20,"と一致"	,11,0);
		JLabel LB2_SearchExpdate		= B100_FrameParts.JLabelSet(510,175, 40,20,"～"		,11,2);
		JLabel LB2_SearchActualDate		= B100_FrameParts.JLabelSet(510,200, 40,20,"～"		,11,2);
		
		JLabel LB2_SearchQty			= B100_FrameParts.JLabelSet(750, 75, 40,20,"～"		,11,2);
		JLabel LB2_SearchShipPlanQty	= B100_FrameParts.JLabelSet(750,100, 40,20,"～"		,11,2);
		JLabel LB2_SearchPossibleQty	= B100_FrameParts.JLabelSet(750,125, 40,20,"～"		,11,2);
		
		TB_SearchClCd.setSelectedIndex(GetSelectIndex(B100_DefaultVariable.SearchClList[1],A00000_Main.ClCd));	//荷主コード
		TB_SearchWhCd.setSelectedIndex(GetSelectIndex(B100_DefaultVariable.SearchWhList[1],A00000_Main.ClWh));	//倉庫コード
		TB_SearchClGpCD.setSelectedIndex(GetSelectIndex(B100_DefaultVariable.SearchClGpList[1],A00000_Main.ClGp));	//荷主グループ
		
		TB_SearchClCd.setEnabled(false);	//荷主コード
		TB_SearchWhCd.setEnabled(false);	//倉庫コード
		TB_SearchClGpCD.setEnabled(false);	//荷主グループ
		
		//荷主切り替わっていたらデフォルト検索条件セットしない
		boolean DefaultSetFg = true;
		if(null==DefaultSearchWhCd) {
			DefaultSetFg=false;
		}else if(!A00000_Main.ClWh.equals(DefaultSearchWhCd)){
			DefaultSetFg=false;
		}
		if(null==DefaultSearchClCd) {
			DefaultSetFg=false;
		}else if(!A00000_Main.ClCd.equals(DefaultSearchClCd)){
			DefaultSetFg=false;
		}
		if(null==DefaultSearchClGpCD) {
			DefaultSetFg=false;
		}else if(!A00000_Main.ClGp.equals(DefaultSearchClGpCD)){
			DefaultSetFg=false;
		}
		//覚えた検索条件をセット
		if(DefaultSetFg) {
			if(null!=DefaultSearchItemCd				) {TB_SearchItemCd.setText(DefaultSearchItemCd);}
			if(null!=DefaultSearchItemName			) {TB_SearchItemName.setText(DefaultSearchItemName);}
			if(null!=DefaultSearchClItemCd			) {TB_SearchClItemCd.setText(DefaultSearchClItemCd);}
			if(null!=DefaultSearchJanCd				) {TB_SearchJanCd.setText(DefaultSearchJanCd);}
			if(null!=DefaultSearchItemMdNo			) {TB_SearchItemMdNo.setText(DefaultSearchItemMdNo);}
			if(null!=DefaultSearchLot					) {TB_SearchLot.setText(DefaultSearchLot);}
			if(null!=DefaultSearchExpdateMin			) {TB_SearchExpdateMin.setText(DefaultSearchExpdateMin);}
			if(null!=DefaultSearchExpdateMax			) {TB_SearchExpdateMax.setText(DefaultSearchExpdateMax);}
			if(null!=DefaultSearchActualDateMin		) {TB_SearchActualDateMin.setText(DefaultSearchActualDateMin);}
			if(null!=DefaultSearchActualDateMax		) {TB_SearchActualDateMax.setText(DefaultSearchActualDateMax);}
			
			if(null!=DefaultSearchLoc					) {TB_SearchLoc.setText(DefaultSearchLoc);}
			if(null!=DefaultSearchType				) {TB_SearchType.setSelectedIndex(GetSelectIndex(B100_DefaultVariable.SearchLocType[1],DefaultSearchType));}
			if(null!=DefaultSearchQtyMin				) {TB_SearchQtyMin.setText(DefaultSearchQtyMin);}
			if(null!=DefaultSearchQtyMax				) {TB_SearchQtyMax.setText(DefaultSearchQtyMax);}
			if(null!=DefaultSearchShipPlanQtyMin	) {TB_SearchShipPlanQtyMin.setText(DefaultSearchShipPlanQtyMin);}
			if(null!=DefaultSearchShipPlanQtyMax	) {TB_SearchShipPlanQtyMax.setText(DefaultSearchShipPlanQtyMax);}
			if(null!=DefaultSearchPossibleQtyMin	) {TB_SearchPossibleQtyMin.setText(DefaultSearchPossibleQtyMin);}
			if(null!=GDefaultSearchPossibleQtyMax	) {TB_SearchPossibleQtyMax.setText(GDefaultSearchPossibleQtyMax);}
			
			if(DefaultSearchLotWhitespace			){TB_SearchLotWhitespace.setSelected(true);}else {TB_SearchLotWhitespace.setSelected(false);}
			if(DefaultLocExactMatch					){TB_LocExactMatch.setSelectedIndex(1);}else {TB_LocExactMatch.setSelectedIndex(0);}
			if(DefaultSortItemcdMode					){TB_SortItemcdMode.setSelectedIndex(1);}else{TB_SortItemcdMode.setSelectedIndex(0);}
		}
		
		//消費期限進む戻るボタン
		JButton SearchExpdateMinAfterBtn	= B100_FrameParts.BtnSet(470,175, 40,10,"▲",6);
		JButton SearchExpdateMinBeforeBtn	= B100_FrameParts.BtnSet(470,185, 40,10,"▼",6);
		JButton SearchExpdateMaxAfterBtn	= B100_FrameParts.BtnSet(620,175, 40,10,"▲",6);
		JButton SearchExpdateMaxBeforeBtn	= B100_FrameParts.BtnSet(620,185, 40,10,"▼",6);
		
		//消費期限進む戻るボタン押下事の挙動
		SearchExpdateMinAfterBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				B100_DateTimeControl.AfterDateSet(TB_SearchExpdateMin);
			}
		});
		SearchExpdateMinBeforeBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				B100_DateTimeControl.BeforeDateSet(TB_SearchExpdateMin);
			}
		});
		SearchExpdateMaxAfterBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				B100_DateTimeControl.AfterDateSet(TB_SearchExpdateMax);
			}
		});
		SearchExpdateMaxBeforeBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				B100_DateTimeControl.BeforeDateSet(TB_SearchExpdateMax);
			}
		});
		
		//入荷実績日進む戻るボタン
		JButton SearchActualDateMinAfterBtn		= B100_FrameParts.BtnSet(470,200, 40,10,"▲",6);
		JButton SearchActualDateMinBeforeBtn	= B100_FrameParts.BtnSet(470,210, 40,10,"▼",6);
		JButton SearchActualDateMaxAfterBtn		= B100_FrameParts.BtnSet(620,200, 40,10,"▲",6);
		JButton SearchActualDateMaxBeforeBtn	= B100_FrameParts.BtnSet(620,210, 40,10,"▼",6);
		//入荷実績日進む戻るボタン押下事の挙動
		SearchActualDateMinAfterBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				B100_DateTimeControl.AfterDateSet(TB_SearchActualDateMin);
			}
		});
		SearchActualDateMinBeforeBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				B100_DateTimeControl.BeforeDateSet(TB_SearchActualDateMin);
			}
		});
		SearchActualDateMaxAfterBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				B100_DateTimeControl.AfterDateSet(TB_SearchActualDateMax);
			}
		});
		SearchActualDateMaxBeforeBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				B100_DateTimeControl.BeforeDateSet(TB_SearchActualDateMax);
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
		PN_Search.add(TB_SearchLotWhitespace);
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
		
		Object[][] RtStockRt = T100_StockRt.RtStockRt();
		
		String[] columnNames01 = new String[RtStockRt.length+1];
		
		columnNames01[0] = "Fg";
		for(int i=0;i<RtStockRt.length;i++) {
			columnNames01[1+(int)RtStockRt[i][1]] = ""+RtStockRt[i][3];
		}
		
		//編集可能カラムの指定
		B100_TableControl.RenewTgt = new int[1];
		B100_TableControl.RenewTgt[0] = 0;

		final DefaultTableModel tableModel_ms01 = new B100_TableControl.MyTableModel01(columnNames01,0);
		
		final JTable tb01 = new JTable(tableModel_ms01);
		tb01.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tb01.setRowHeight(20*A00000_Main.Mul/A00000_Main.Div);
		tb01.setFont(new Font(A00000_Main.DefaultFont, Font.PLAIN, 12*A00000_Main.Mul/A00000_Main.Div));
		
		DefaultTableColumnModel columnModel01
		= (DefaultTableColumnModel)tb01.getColumnModel();
		
		//列幅初期設定 表示位置設定
		TableColumn column = null;
		
		column = columnModel01.getColumn( 0);	column.setPreferredWidth( 30*A00000_Main.Mul/A00000_Main.Div);	//FG
		
		for(int i=0;i<RtStockRt.length;i++) {
			if("int".equals((String)RtStockRt[i][2])||"float".equals((String)RtStockRt[i][2])) {
				column = columnModel01.getColumn(1+(int)RtStockRt[i][1]);	column.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.rightCellRenderer());
			}else {
				column = columnModel01.getColumn(1+(int)RtStockRt[i][1]);	column.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.leftCellRenderer());
			}
		}
		//スクロール用設定
		JScrollPane scpn01 = B100_FrameParts.JScrollPaneSet(10,300,1160,325,tb01);
		main_fm.add(scpn01);
		
		PN_Search.add(PN_SearchLabel);
		main_fm.add(PN_Search);
		
		//検索ボタン
		JButton SearchBtn 		= B100_FrameParts.BtnSet(		1050,200,100,20,"検索",11);
		PN_Search.add(SearchBtn);
		
		//条件クリアボタン
		JButton SearchCrearBtn 	= B100_FrameParts.BtnSet(		1050,25,100,20,"条件クリア",10);
		PN_Search.add(SearchCrearBtn);
		
		//CSVボタン
		JButton CsvBtn = B100_FrameParts.BtnSet(				 10,660,100,20,"csv出力",11);
		main_fm.add(CsvBtn);
		
		//Excel出力ボタン
		JButton ExcelBtn = B100_FrameParts.BtnSet(				130,660,100,20,"Excel出力",11);
		main_fm.add(ExcelBtn);
		
		//在庫表出力ボタン
		JButton ListPrintBtn = B100_FrameParts.BtnSet(			250,660,100,20,"在庫表出力",11);
		main_fm.add(ListPrintBtn);
		
		JLabel LB_StockLookUp 	= B100_FrameParts.JLabelSet(	370,640,100,20,"チェック行の"	,11,2);
		main_fm.add(LB_StockLookUp);
		//在庫1画面表示ボタン
		JButton StockLookUpBtn 	= B100_FrameParts.BtnSet(		370,660,100,20,"詳細表示",11);
		main_fm.add(StockLookUpBtn);
		
		JLabel LB_StockAdjust 	= B100_FrameParts.JLabelSet(	490,640,100,20,"チェック行を"	,11,2);
		main_fm.add(LB_StockAdjust);
		//在庫調整ボタン
		JButton StockAdjustBtn 	= B100_FrameParts.BtnSet(		490,660,100,20,"在庫調整",11);
		main_fm.add(StockAdjustBtn);
		
		JLabel LB_StockAdjustFromNoStock 	= B100_FrameParts.JLabelSet(	610,640,100,20,"在庫が無い商品を"	,10,2);
		main_fm.add(LB_StockAdjustFromNoStock);
		//在庫調整ボタン
		JButton StockAdjustBtnFromNoStock 	= B100_FrameParts.BtnSet(		610,660,100,20,"在庫調整",11);
		main_fm.add(StockAdjustBtnFromNoStock);
		
		main_fm.add(PN_Search);
		
		final JFrame LookUp_fm = B100_FrameParts.FrameCreate(x+50,y+50,800,650,"Corgi00在庫検索(詳細表示)","ZK");
		JLabel LookUpUserinfo = B100_FrameParts.UserInfo();
		JButton LookUpExit_btn = B100_FrameParts.ExitBtn();
		JButton LookUpAdjust_btn = B100_FrameParts.AnyBtn(70,"在庫調整",11);
		
		LookUp_fm.add(LookUpUserinfo);
		LookUp_fm.add(LookUpExit_btn);
		LookUp_fm.add(LookUpAdjust_btn);
		
		JLabel LB_LookUpWh					= B100_FrameParts.JLabelSet(  0, 50,130,20,"担当倉庫:"			,11,1);
		JLabel LB_LookUpGp					= B100_FrameParts.JLabelSet(  0, 75,130,20,"荷主グループ:"		,11,1);
		JLabel LB_LookUpCl					= B100_FrameParts.JLabelSet(  0,100,130,20,"荷主:"				,11,1);
		JLabel LB_LookUpLoc					= B100_FrameParts.JLabelSet(  0,125,130,20,"ロケーション:"		,11,1);
		JLabel LB_LookUpItem				= B100_FrameParts.JLabelSet(  0,150,130,20,"商品:"				,11,1);
		JLabel LB_LookUpLot					= B100_FrameParts.JLabelSet(  0,175,130,20,"ロット:"			,11,1);
		JLabel LB_LookUpExpdate				= B100_FrameParts.JLabelSet(  0,200,130,20,"消費期限:"			,11,1);
		JLabel LB_LookUpActualDate			= B100_FrameParts.JLabelSet(  0,225,130,20,"入荷実績日:"		,11,1);
		final JLabel  TB_LookUpWh						= B100_FrameParts.JLabelSet(130, 50,300,20,""		,12,0);
		final JLabel  TB_LookUpGp						= B100_FrameParts.JLabelSet(130, 75,300,20,""		,12,0);
		final JLabel  TB_LookUpCl						= B100_FrameParts.JLabelSet(130,100,300,20,""		,12,0);
		final JTextField  TB_LookUpLoc					= B100_FrameParts.JTextFieldSet(130,125,190,20,""	,12,0);
		final JLabel  TB_LookUpLocType					= B100_FrameParts.JLabelSet(330,125,100,20,""		,12,0);
		final JTextField  TB_LookUpItem					= B100_FrameParts.JTextFieldSet(130,150,300,20,""	,12,0);
		final JTextField  TB_LookUpLot					= B100_FrameParts.JTextFieldSet(130,175,100,20,""	,12,0);
		final JTextField  TB_LookUpExpdate				= B100_FrameParts.JTextFieldSet(130,200,100,20,""	,12,0);
		final JTextField  TB_LookUpActualDate			= B100_FrameParts.JTextFieldSet(130,225,100,20,""	,12,0);
		
		
		JLabel LB_LookUpPlUnitQty			= B100_FrameParts.JLabelSet(430,175,130,20,"パレット入り数:"	,11,1);
		JLabel LB_LookUpCsUnitQty			= B100_FrameParts.JLabelSet(430,200,130,20,"ケース入り数:"		,11,1);
		JLabel LB_LookUpCtUnitQty			= B100_FrameParts.JLabelSet(430,225,130,20,"カートン入り数:"	,11,1);
		final JLabel  TB_LookUpPlUnitQty			= B100_FrameParts.JLabelSet(560,175,100,20,""			,12,1);
		final JLabel  TB_LookUpCsUnitQty			= B100_FrameParts.JLabelSet(560,200,100,20,""			,12,1);
		final JLabel  TB_LookUpCtUnitQty			= B100_FrameParts.JLabelSet(560,225,100,20,""			,12,1);
		
		JLabel LB_LookUpQtyTitle			= B100_FrameParts.JLabelSet(  0,300,130,20,"バラ換算総数:"		,11,1);
		JLabel LB_LookUpPlQtyTitle			= B100_FrameParts.JLabelSet(  0,350,130,20,"パレット数量:"		,11,1);
		JLabel LB_LookUpCsQtyTitle			= B100_FrameParts.JLabelSet(  0,375,130,20,"カートン数量:"		,11,1);
		JLabel LB_LookUpCtQtyTitle			= B100_FrameParts.JLabelSet(  0,400,130,20,"ケース数量:"		,11,1);
		JLabel LB_LookUpBrQtyTitle			= B100_FrameParts.JLabelSet(  0,425,130,20,"バラ数量:"			,11,1);
		
		JLabel LB_LookUpQtyTtalTitle		= B100_FrameParts.JLabelSet(130,275,100,20,"総数量"			,11,2);
		final JTextField  TB_LookUpQty					= B100_FrameParts.JTextFieldSet(130,300,100,20,""	,12,1);
		final JTextField  TB_LookUpPlQty				= B100_FrameParts.JTextFieldSet(130,350,100,20,""	,12,1);
		final JTextField  TB_LookUpCsQty				= B100_FrameParts.JTextFieldSet(130,375,100,20,""	,12,1);
		final JTextField  TB_LookUpCtQty				= B100_FrameParts.JTextFieldSet(130,400,100,20,""	,12,1);
		final JTextField  TB_LookUpBrQty				= B100_FrameParts.JTextFieldSet(130,425,100,20,""	,12,1);
		final JLabel  TB_LookUpQtyUN				= B100_FrameParts.JLabelSet(230,300, 80,20,""			,12,0);
		final JLabel  TB_LookUpPlQtyUN				= B100_FrameParts.JLabelSet(230,350, 80,20,""			,12,0);
		final JLabel  TB_LookUpCsQtyUN				= B100_FrameParts.JLabelSet(230,375, 80,20,""			,12,0);
		final JLabel  TB_LookUpCtQtyUN				= B100_FrameParts.JLabelSet(230,400, 80,20,""			,12,0);
		final JLabel  TB_LookUpBrQtyUN				= B100_FrameParts.JLabelSet(230,425, 80,20,""			,12,0);
		
		JLabel LB_LookUpPossibleQtyTitle	= B100_FrameParts.JLabelSet(310,275,100,20,"出荷可能数"		,11,2);
		final JTextField  TB_LookUpShipPlanQty			= B100_FrameParts.JTextFieldSet(310,300,100,20,""	,12,1);
		final JTextField  TB_LookUpPlShipPlanQty		= B100_FrameParts.JTextFieldSet(310,350,100,20,""	,12,1);
		final JTextField  TB_LookUpCsShipPlanQty		= B100_FrameParts.JTextFieldSet(310,375,100,20,""	,12,1);
		final JTextField  TB_LookUpCtShipPlanQty		= B100_FrameParts.JTextFieldSet(310,400,100,20,""	,12,1);
		final JTextField  TB_LookUpBrQShipPlanQty		= B100_FrameParts.JTextFieldSet(310,425,100,20,""	,12,1);
		final JLabel  TB_LookUpShipPlanQtyUN		= B100_FrameParts.JLabelSet(410,300, 80,20,""			,12,0);
		final JLabel  TB_LookUpPlShipPlanQtyUN		= B100_FrameParts.JLabelSet(410,350, 80,20,""			,12,0);
		final JLabel  TB_LookUpCsShipPlanQtyUN		= B100_FrameParts.JLabelSet(410,375, 80,20,""			,12,0);
		final JLabel  TB_LookUpCtShipPlanQtyUN		= B100_FrameParts.JLabelSet(410,400, 80,20,""			,12,0);
		final JLabel  TB_LookUpBrQShipPlanQtyUN		= B100_FrameParts.JLabelSet(410,425, 80,20,""			,12,0);
		
		
		JLabel LB_LookUpShipPlanQtyTitle	= B100_FrameParts.JLabelSet(490,275,100,20,"引当済数"			,11,2);
		final JTextField  TB_LookUpPossibleQty			= B100_FrameParts.JTextFieldSet(490,300,100,20,""	,12,1);
		final JTextField  TB_LookUpPlPossibleQty		= B100_FrameParts.JTextFieldSet(490,350,100,20,""	,12,1);
		final JTextField  TB_LookUptCsPossibleQty		= B100_FrameParts.JTextFieldSet(490,375,100,20,""	,12,1);
		final JTextField  TB_LookUpCtPossibleQty		= B100_FrameParts.JTextFieldSet(490,400,100,20,""	,12,1);
		final JTextField  TB_LookUpBrPossibleQty		= B100_FrameParts.JTextFieldSet(490,425,100,20,""	,12,1);
		final JLabel  TB_LookUpPossibleQtyUN		= B100_FrameParts.JLabelSet(590,300, 80,20,""			,12,0);
		final JLabel  TB_LookUpPlPossibleQtyUN		= B100_FrameParts.JLabelSet(590,350, 80,20,""			,12,0);
		final JLabel  TB_LookUptCsPossibleQtyUN		= B100_FrameParts.JLabelSet(590,375, 80,20,""			,12,0);
		final JLabel  TB_LookUpCtPossibleQtyUN		= B100_FrameParts.JLabelSet(590,400, 80,20,""			,12,0);
		final JLabel  TB_LookUpBrPossibleQtyUN		= B100_FrameParts.JLabelSet(590,425, 80,20,""			,12,0);
		
		JLabel LB_LookUpEntryDate			= B100_FrameParts.JLabelSet(  0,550,130,20,"登録日時:"			,11,1);
		JLabel LB_LookUpUpdateDate			= B100_FrameParts.JLabelSet(  0,575,130,20,"更新日時:"			,11,1);
		JLabel LB_LookUpEntryUser			= B100_FrameParts.JLabelSet(380,550,130,20,"登録者:"			,11,1);
		JLabel LB_LookUpUpdateUser			= B100_FrameParts.JLabelSet(380,575,130,20,"更新者:"			,11,1);
		final JLabel  TB_LookUpEntryDate			= B100_FrameParts.JLabelSet(130,550,250,20,""			,12,0);
		final JLabel  TB_LookUpUpdateDate			= B100_FrameParts.JLabelSet(130,575,250,20,""			,12,0);
		final JLabel  TB_LookUpEntryUser			= B100_FrameParts.JLabelSet(510,550,250,20,""			,12,0);
		final JLabel  TB_LookUpUpdateUser			= B100_FrameParts.JLabelSet(510,575,250,20,""			,12,0);
		
		TB_LookUpLoc.setEditable(false);
		TB_LookUpItem.setEditable(false);
		TB_LookUpLot.setEditable(false);
		TB_LookUpExpdate.setEditable(false);
		TB_LookUpActualDate.setEditable(false);
		TB_LookUpQty.setEditable(false);
		TB_LookUpPlQty.setEditable(false);
		TB_LookUpCsQty.setEditable(false);
		TB_LookUpCtQty.setEditable(false);
		TB_LookUpBrQty.setEditable(false);
		TB_LookUpShipPlanQty.setEditable(false);
		TB_LookUpPlShipPlanQty.setEditable(false);
		TB_LookUpCsShipPlanQty.setEditable(false);
		TB_LookUpCtShipPlanQty.setEditable(false);
		TB_LookUpBrQShipPlanQty.setEditable(false);
		TB_LookUpPossibleQty.setEditable(false);
		TB_LookUpPlPossibleQty.setEditable(false);
		TB_LookUptCsPossibleQty.setEditable(false);
		TB_LookUpCtPossibleQty.setEditable(false);
		TB_LookUpBrPossibleQty.setEditable(false);
		
		
		LookUp_fm.add(LB_LookUpWh);
		LookUp_fm.add(LB_LookUpGp);
		LookUp_fm.add(LB_LookUpCl);
		LookUp_fm.add(LB_LookUpLoc);
		LookUp_fm.add(LB_LookUpItem);
		LookUp_fm.add(LB_LookUpLot);
		LookUp_fm.add(LB_LookUpExpdate);
		LookUp_fm.add(LB_LookUpActualDate);
		LookUp_fm.add(TB_LookUpWh);
		LookUp_fm.add(TB_LookUpGp);
		LookUp_fm.add(TB_LookUpCl);
		LookUp_fm.add(TB_LookUpLoc);
		LookUp_fm.add(TB_LookUpLocType);
		LookUp_fm.add(TB_LookUpItem);
		LookUp_fm.add(TB_LookUpLot);
		LookUp_fm.add(TB_LookUpExpdate);
		LookUp_fm.add(TB_LookUpActualDate);
		
		LookUp_fm.add(LB_LookUpPlUnitQty);
		LookUp_fm.add(LB_LookUpCsUnitQty);
		LookUp_fm.add(LB_LookUpCtUnitQty);
		LookUp_fm.add(TB_LookUpPlUnitQty);
		LookUp_fm.add(TB_LookUpCsUnitQty);
		LookUp_fm.add(TB_LookUpCtUnitQty);
		
		LookUp_fm.add(LB_LookUpQtyTitle);
		LookUp_fm.add(LB_LookUpPlQtyTitle);
		LookUp_fm.add(LB_LookUpCsQtyTitle);
		LookUp_fm.add(LB_LookUpCtQtyTitle);
		LookUp_fm.add(LB_LookUpBrQtyTitle);
		
		LookUp_fm.add(LB_LookUpQtyTtalTitle);
		LookUp_fm.add(TB_LookUpQty);
		LookUp_fm.add(TB_LookUpPlQty);
		LookUp_fm.add(TB_LookUpCsQty);
		LookUp_fm.add(TB_LookUpCtQty);
		LookUp_fm.add(TB_LookUpBrQty);
		LookUp_fm.add(TB_LookUpQtyUN);
		LookUp_fm.add(TB_LookUpPlQtyUN);
		LookUp_fm.add(TB_LookUpCsQtyUN);
		LookUp_fm.add(TB_LookUpCtQtyUN);
		LookUp_fm.add(TB_LookUpBrQtyUN);
		
		LookUp_fm.add(LB_LookUpPossibleQtyTitle);
		LookUp_fm.add(TB_LookUpShipPlanQty);
		LookUp_fm.add(TB_LookUpPlShipPlanQty);
		LookUp_fm.add(TB_LookUpCsShipPlanQty);
		LookUp_fm.add(TB_LookUpCtShipPlanQty);
		LookUp_fm.add(TB_LookUpBrQShipPlanQty);
		LookUp_fm.add(TB_LookUpShipPlanQtyUN);
		LookUp_fm.add(TB_LookUpPlShipPlanQtyUN);
		LookUp_fm.add(TB_LookUpCsShipPlanQtyUN);
		LookUp_fm.add(TB_LookUpCtShipPlanQtyUN);
		LookUp_fm.add(TB_LookUpBrQShipPlanQtyUN);
		
		LookUp_fm.add(LB_LookUpShipPlanQtyTitle);
		LookUp_fm.add(TB_LookUpPossibleQty);
		LookUp_fm.add(TB_LookUpPlPossibleQty);
		LookUp_fm.add(TB_LookUptCsPossibleQty);
		LookUp_fm.add(TB_LookUpCtPossibleQty);
		LookUp_fm.add(TB_LookUpBrPossibleQty);
		LookUp_fm.add(TB_LookUpPossibleQtyUN);
		LookUp_fm.add(TB_LookUpPlPossibleQtyUN);
		LookUp_fm.add(TB_LookUptCsPossibleQtyUN);
		LookUp_fm.add(TB_LookUpCtPossibleQtyUN);
		LookUp_fm.add(TB_LookUpBrPossibleQtyUN);
		
		LookUp_fm.add(LB_LookUpEntryDate);
		LookUp_fm.add(LB_LookUpUpdateDate);
		LookUp_fm.add(LB_LookUpEntryUser);
		LookUp_fm.add(LB_LookUpUpdateUser);
		LookUp_fm.add(TB_LookUpEntryDate);
		LookUp_fm.add(TB_LookUpUpdateDate);
		LookUp_fm.add(TB_LookUpEntryUser);
		LookUp_fm.add(TB_LookUpUpdateUser);
		
		
		RenewFg = true;
		main_fm.setVisible(true);
		
		//在庫1画面表示ボタン押下時の挙動 	どうせスクロールして見ると見にくいって言い始める
		StockLookUpBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					LookUp_fm.setVisible(false);
					
					Object[][] RtStockRt	= T100_StockRt.RtStockRt();
					String[] SetVol = new String[RtStockRt.length];
					for(int i=0;i<SetVol.length;i++) {
						SetVol[i]="";
					}
					boolean KickFg = false;
					int RowCount 	= tableModel_ms01.getRowCount();
					for(int i=0;i<RowCount;i++) {
						if((boolean)tableModel_ms01.getValueAt(i, 0)) {
							for(int i01=0;i01<SetVol.length;i01++) {
								SetVol[i01]=""+tableModel_ms01.getValueAt(i, i01+1);
							}
							KickFg = true;
						}
					}
					if(KickFg) {
						NumberFormat ni = NumberFormat.getNumberInstance();
						
						String GetClCd			= B100_TextControl.Trim(SetVol[T100_StockRt.ColClCd]);
						String GetCLName		= B100_TextControl.Trim(SetVol[T100_StockRt.ColCLName]);
						String GetWhCd			= B100_TextControl.Trim(SetVol[T100_StockRt.ColWhCd]);
						String GetClWHName		= B100_TextControl.Trim(SetVol[T100_StockRt.ColClWHName]);
						String GetClGpCD		= B100_TextControl.Trim(SetVol[T100_StockRt.ColClGpCD]);
						String GetClGpName		= B100_TextControl.Trim(SetVol[T100_StockRt.ColClGpName]);
						String GetLoc			= B100_TextControl.Trim(SetVol[T100_StockRt.ColLoc]);
						String GetLocName		= B100_TextControl.Trim(SetVol[T100_StockRt.ColLocName]);
						int GetLocType			= B100_TextControl.TextToInt(SetVol[T100_StockRt.ColType]);
						String GetItemCd		= B100_TextControl.Trim(SetVol[T100_StockRt.ColItemCd]);
						String GetLot			= B100_TextControl.Trim(SetVol[T100_StockRt.ColLot]);
						String GetExpdate		= B100_TextControl.TextToDate(SetVol[T100_StockRt.ColExpdate]);
						String GetActualDate	= B100_TextControl.TextToDate(SetVol[T100_StockRt.ColActualDate]);
						int GetQty				= B100_TextControl.TextToInt(SetVol[T100_StockRt.ColQty]);
						int GetShipPlanQty		= B100_TextControl.TextToInt(SetVol[T100_StockRt.ColShipPlanQty]);
						int GetPossibleQty		= B100_TextControl.TextToInt(SetVol[T100_StockRt.ColPossibleQty]);
						String GetItemName		= B100_TextControl.Trim(SetVol[T100_StockRt.ColItemName]);
						String GetItemName01	= B100_TextControl.Trim(SetVol[T100_StockRt.ColItemName01]);
						String GetItemName02	= B100_TextControl.Trim(SetVol[T100_StockRt.ColItemName02]);
						String GetItemName03	= B100_TextControl.Trim(SetVol[T100_StockRt.ColItemName03]);
						String GetClItemCd		= B100_TextControl.Trim(SetVol[T100_StockRt.ColClItemCd]);
						String GetJanCd			= B100_TextControl.Trim(SetVol[T100_StockRt.ColJanCd]);
						String GetItemMdNo		= B100_TextControl.Trim(SetVol[T100_StockRt.ColItemMdNo]);
						int GetCtUnitQty		= B100_TextControl.TextToInt(SetVol[T100_StockRt.ColCtUnitQty]);
						int GetCsUnitQty		= B100_TextControl.TextToInt(SetVol[T100_StockRt.ColCsUnitQty]);
						int GetPlUnitQty		= B100_TextControl.TextToInt(SetVol[T100_StockRt.ColPlUnitQty]);
						String GetUnitName		= B100_TextControl.Trim(SetVol[T100_StockRt.ColUnitName]);
						String GetCtUnitName	= B100_TextControl.Trim(SetVol[T100_StockRt.ColCtUnitName]);
						String GetCsUnitName	= B100_TextControl.Trim(SetVol[T100_StockRt.ColCsUnitName]);
						String GetPlUnitName	= B100_TextControl.Trim(SetVol[T100_StockRt.ColPlUnitName]);
						String GetEntryDate		= B100_TextControl.Trim(SetVol[T100_StockRt.ColEntryDate]);
						String GetUpdateDate	= B100_TextControl.Trim(SetVol[T100_StockRt.ColUpdateDate]);
						String GetEntryUser		= B100_TextControl.Trim(SetVol[T100_StockRt.ColEntryUser]);
						String GetUpdateUser	= B100_TextControl.Trim(SetVol[T100_StockRt.ColUpdateUser]);
						int GetBrQty			= B100_TextControl.TextToInt(SetVol[T100_StockRt.ColBrQty]);
						int GetBrShipPlanQty	= B100_TextControl.TextToInt(SetVol[T100_StockRt.ColBrShipPlanQty]);
						int GetBrPossibleQty	= B100_TextControl.TextToInt(SetVol[T100_StockRt.ColBrPossibleQty]);
						int GetCtQty			= B100_TextControl.TextToInt(SetVol[T100_StockRt.ColCtQty]);
						int GetCtShipPlanQty	= B100_TextControl.TextToInt(SetVol[T100_StockRt.ColCtShipPlanQty]);
						int GetCtPossibleQty	= B100_TextControl.TextToInt(SetVol[T100_StockRt.ColCtPossibleQty]);
						int GetCsQty			= B100_TextControl.TextToInt(SetVol[T100_StockRt.ColCsQty]);
						int GetCsShipPlanQty	= B100_TextControl.TextToInt(SetVol[T100_StockRt.ColCsShipPlanQty]);
						int GetCsPossibleQty	= B100_TextControl.TextToInt(SetVol[T100_StockRt.ColCsPossibleQty]);
						int GetPlQty			= B100_TextControl.TextToInt(SetVol[T100_StockRt.ColPlQty]);
						int GetPlShipPlanQty	= B100_TextControl.TextToInt(SetVol[T100_StockRt.ColPlShipPlanQty]);
						int GetPlPossibleQty	= B100_TextControl.TextToInt(SetVol[T100_StockRt.ColPlPossibleQty]);
						
						String SetWh 			= "("+GetWhCd+")"+GetClWHName;
						String SetGp 			= "("+GetClGpCD+")"+GetClGpName;
						String SetCl 			= "("+GetClCd+")"+GetCLName;
						String SetLoc 			= "("+GetLoc+")"+GetLocName;
						String SetLocType 		= B100_DefaultVariable.LocType[2][GetSelectIndex(B100_DefaultVariable.LocType[1],""+GetLocType)];
						String SetItem 			= "("+GetItemCd+")"+GetItemName;
						String SetLot 			= GetLot;
						String SetExpdate 		= GetExpdate;
						String SetActualDate 	= GetActualDate;
						String SetUnitName		= GetUnitName;
						String SetCtUnitName	= GetCtUnitName;
						String SetCsUnitName	= GetCsUnitName;
						String SetPlUnitName	= GetPlUnitName;
						String SetCtUnitQty		= ""+ni.format(GetCtUnitQty);
						String SetCsUnitQty		= ""+ni.format(GetCsUnitQty);
						String SetPlUnitQty		= ""+ni.format(GetPlUnitQty);
						
						String SetQty 			= ""+ni.format(GetQty);
						String SetShipPlanQty 	= ""+ni.format(GetShipPlanQty);
						String SetPossibleQty 	= ""+ni.format(GetPossibleQty);
						
						String SetBrQty 		= ""+ni.format(GetBrQty);
						String SetBrShipPlanQty= ""+ni.format(GetBrShipPlanQty);
						String SetBrPossibleQty = ""+ni.format(GetBrPossibleQty);
						String SetCtQty 		= ""+ni.format(GetCtQty);
						String SetCtShipPlanQty = ""+ni.format(GetCtShipPlanQty);
						String SetCtPossibleQty = ""+ni.format(GetCtPossibleQty);
						String SetCsQty 		= ""+ni.format(GetCsQty);
						String SetCsShipPlanQty = ""+ni.format(GetCsShipPlanQty);
						String SetCsPossibleQty	= ""+ni.format(GetCsPossibleQty);
						String SetPlQty 		= ""+ni.format(GetPlQty);
						String SetPlShipPlanQty = ""+ni.format(GetPlShipPlanQty);
						String SetPlPossibleQty = ""+ni.format(GetPlPossibleQty);
						
						String SetEntryDate 	= GetEntryDate;
						String SetUpdateDate 	= GetUpdateDate;
						String SetEntryUser 	= GetEntryUser;
						String SetUpdateUser 	= GetUpdateUser;
						
						TB_LookUpWh.setText(SetWh);
						TB_LookUpGp.setText(SetGp);
						TB_LookUpCl.setText(SetCl);
						TB_LookUpLoc.setText(SetLoc);
						TB_LookUpLocType.setText(SetLocType);
						TB_LookUpItem.setText(SetItem);
						TB_LookUpLot.setText(SetLot);
						TB_LookUpExpdate.setText(SetExpdate);
						TB_LookUpActualDate.setText(SetActualDate);
						
						TB_LookUpPlUnitQty.setText(SetPlUnitQty);
						TB_LookUpCsUnitQty.setText(SetCsUnitQty);
						TB_LookUpCtUnitQty.setText(SetCtUnitQty);
						
						TB_LookUpQty.setText(SetQty);
						TB_LookUpPlQty.setText(SetPlQty);
						TB_LookUpCsQty.setText(SetCsQty);
						TB_LookUpCtQty.setText(SetCtQty);
						TB_LookUpBrQty.setText(SetBrQty);
						TB_LookUpQtyUN.setText(SetUnitName);
						TB_LookUpPlQtyUN.setText(SetPlUnitName);
						TB_LookUpCsQtyUN.setText(SetCsUnitName);
						TB_LookUpCtQtyUN.setText(SetCtUnitName);
						TB_LookUpBrQtyUN.setText(SetUnitName);
						
						TB_LookUpShipPlanQty.setText(SetShipPlanQty);
						TB_LookUpPlShipPlanQty.setText(SetPlShipPlanQty);
						TB_LookUpCsShipPlanQty.setText(SetCsShipPlanQty);
						TB_LookUpCtShipPlanQty.setText(SetCtShipPlanQty);
						TB_LookUpBrQShipPlanQty.setText(SetBrShipPlanQty);
						TB_LookUpShipPlanQtyUN.setText(SetUnitName);
						TB_LookUpPlShipPlanQtyUN.setText(SetPlUnitName);
						TB_LookUpCsShipPlanQtyUN.setText(SetCsUnitName);
						TB_LookUpCtShipPlanQtyUN.setText(SetCtUnitName);
						TB_LookUpBrQShipPlanQtyUN.setText(SetUnitName);
						
						TB_LookUpPossibleQty.setText(SetPossibleQty);
						TB_LookUpPlPossibleQty.setText(SetPlPossibleQty);
						TB_LookUptCsPossibleQty.setText(SetCsPossibleQty);
						TB_LookUpCtPossibleQty.setText(SetCtPossibleQty);
						TB_LookUpBrPossibleQty.setText(SetBrPossibleQty);
						TB_LookUpPossibleQtyUN.setText(SetUnitName);
						TB_LookUpPlPossibleQtyUN.setText(SetPlUnitName);
						TB_LookUptCsPossibleQtyUN.setText(SetCsUnitName);
						TB_LookUpCtPossibleQtyUN.setText(SetCtUnitName);
						TB_LookUpBrPossibleQtyUN.setText(SetUnitName);
						
						TB_LookUpEntryDate.setText(SetEntryDate);
						TB_LookUpUpdateDate.setText(SetUpdateDate);
						TB_LookUpEntryUser.setText(SetEntryUser);
						TB_LookUpUpdateUser.setText(SetUpdateUser);
						
						
						
						LookUp_fm.setVisible(true);
					}
					RenewFg = true;
				}
			}
		});
		//在庫調整ボタン押下時の挙動
		LookUpAdjust_btn.addActionListener(new AbstractAction(){
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
				Object[][] RtStockRt	= T100_StockRt.RtStockRt();
				boolean KickFg = false;
				
				for(int i=0;i<RowCount;i++) {
					if((boolean)tableModel_ms01.getValueAt(i, 0)) {
						TgtWhCd 		= ""+tableModel_ms01.getValueAt(i,T100_StockRt.ColWhCd+1);
						TgtClCd 		= ""+tableModel_ms01.getValueAt(i,T100_StockRt.ColClCd+1);
						TgtLoc 			= ""+tableModel_ms01.getValueAt(i,T100_StockRt.ColLoc+1);
						TgtItemCd 		= ""+tableModel_ms01.getValueAt(i,T100_StockRt.ColItemCd+1);
						TgtLot 			= ""+tableModel_ms01.getValueAt(i,T100_StockRt.ColLot+1);
						TgtExpdate 		= ""+tableModel_ms01.getValueAt(i,T100_StockRt.ColExpdate+1);
						TgtActualDate 	= ""+tableModel_ms01.getValueAt(i,T100_StockRt.ColActualDate+1);
						KickFg = true;
					}
				}
				if(KickFg) {
					LookUp_fm.setVisible(false);
					LookUp_fm.dispose();
					main_fm.setVisible(false);
					main_fm.dispose();
					WT100_Stock_10_Adjust.StockAdjust(0,0,TgtWhCd,TgtClCd,TgtLoc,TgtItemCd,TgtLot,TgtExpdate,TgtActualDate);
				}
			}
		});
		
		
		LookUpExit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					LookUp_fm.setVisible(false);
					RenewFg = true;
				}
			}
		});
		
		
		//チェック行在庫調整ボタン押下時の挙動
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
				Object[][] RtStockRt	= T100_StockRt.RtStockRt();
				boolean KickFg = false;
				
				for(int i=0;i<RowCount;i++) {
					if((boolean)tableModel_ms01.getValueAt(i, 0)) {
						TgtWhCd 		= ""+tableModel_ms01.getValueAt(i,T100_StockRt.ColWhCd+1);
						TgtClCd 		= ""+tableModel_ms01.getValueAt(i,T100_StockRt.ColClCd+1);
						TgtLoc 			= ""+tableModel_ms01.getValueAt(i,T100_StockRt.ColLoc+1);
						TgtItemCd 		= ""+tableModel_ms01.getValueAt(i,T100_StockRt.ColItemCd+1);
						TgtLot 			= ""+tableModel_ms01.getValueAt(i,T100_StockRt.ColLot+1);
						TgtExpdate 		= ""+tableModel_ms01.getValueAt(i,T100_StockRt.ColExpdate+1);
						TgtActualDate 	= ""+tableModel_ms01.getValueAt(i,T100_StockRt.ColActualDate+1);
						KickFg = true;
					}
				}
				if(KickFg) {
					LookUp_fm.setVisible(false);
					LookUp_fm.dispose();
					main_fm.setVisible(false);
					main_fm.dispose();
					WT100_Stock_10_Adjust.StockAdjust(0,0,TgtWhCd,TgtClCd,TgtLoc,TgtItemCd,TgtLot,TgtExpdate,TgtActualDate);
				}
			}
		});
		//在庫無し分在庫調整ボタン押下時の挙動
		StockAdjustBtnFromNoStock.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				String TgtWhCd 			= B100_TextControl.Trim(B100_DefaultVariable.SearchWhList[1][TB_SearchWhCd.getSelectedIndex()]);
				String TgtClCd 			= B100_TextControl.Trim(B100_DefaultVariable.SearchClList[1][TB_SearchClCd.getSelectedIndex()]);
				String TgtLoc 			= "";
				String TgtItemCd 		= "";
				String TgtLot 			= "";
				String TgtExpdate 		= "";
				String TgtActualDate 	= "";
				
				LookUp_fm.setVisible(false);
				LookUp_fm.dispose();
				main_fm.setVisible(false);
				main_fm.dispose();
				WT100_Stock_10_Adjust.StockAdjust(0,0,TgtWhCd,TgtClCd,TgtLoc,TgtItemCd,TgtLot,TgtExpdate,TgtActualDate);
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
					WTList100_StockList.StockList(PrintData);
					RenewFg = true;
				}
			}
		});
		
		//検索ボタン押下時の挙動
		SearchBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					LookUp_fm.setVisible(false);
					int RowCount = tableModel_ms01.getRowCount();
					for(int i=0;i<RowCount;i++) {
						tableModel_ms01.removeRow(0);
					}
					
					String GetSearchClCd			= B100_DefaultVariable.SearchClList[1][TB_SearchClCd.getSelectedIndex()];		//荷主コード
					String GetSearchWhCd			= B100_DefaultVariable.SearchWhList[1][TB_SearchWhCd.getSelectedIndex()];		//倉庫コード
					String GetSearchClGpCD			= B100_DefaultVariable.SearchClGpList[1][TB_SearchClGpCD.getSelectedIndex()];	//荷主グループ
					
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
					String GetSearchType			= B100_DefaultVariable.SearchLocType[1][TB_SearchType.getSelectedIndex()];	//ロケタイプ
					String GetSearchQtyMin			= TB_SearchQtyMin.getText();			//数量最小
					String GetSearchQtyMax			= TB_SearchQtyMax.getText();			//数量最大
					String GetSearchShipPlanQtyMin	= TB_SearchShipPlanQtyMin.getText();	//引当済数最小
					String GetSearchShipPlanQtyMax	= TB_SearchShipPlanQtyMax.getText();	//引当済数最大
					String GetSearchPossibleQtyMin	= TB_SearchPossibleQtyMin.getText();	//出荷可能数最小
					String GetSearchPossibleQtyMax	= TB_SearchPossibleQtyMax.getText();	//出荷可能数最大
					
					boolean GetSearchLotWhitespace	= TB_SearchLotWhitespace.isSelected();
					
					GetSearchClCd			= B100_TextControl.Trim(GetSearchClCd);				//荷主コード
					GetSearchWhCd			= B100_TextControl.Trim(GetSearchWhCd);				//倉庫コード
					GetSearchClGpCD			= B100_TextControl.Trim(GetSearchClGpCD);			//荷主グループ
					
					GetSearchItemCd			= B100_TextControl.Trim(GetSearchItemCd);			//商品コード
					GetSearchItemName		= B100_TextControl.Trim(GetSearchItemName);			//商品名
					GetSearchClItemCd		= B100_TextControl.Trim(GetSearchClItemCd);			//荷主商品コード
					GetSearchJanCd			= B100_TextControl.Trim(GetSearchJanCd);			//BCD（バラ）
					GetSearchItemMdNo		= B100_TextControl.Trim(GetSearchItemMdNo);			//商品型番
					GetSearchLot			= B100_TextControl.Trim(GetSearchLot);				//ロット
					GetSearchExpdateMin		= B100_TextControl.Trim(GetSearchExpdateMin);		//消費期限開始
					GetSearchExpdateMax		= B100_TextControl.Trim(GetSearchExpdateMax);		//消費期限終了
					GetSearchActualDateMin	= B100_TextControl.Trim(GetSearchActualDateMin);	//入荷実績日開始
					GetSearchActualDateMax	= B100_TextControl.Trim(GetSearchActualDateMax);	//入荷実績日終了
					
					GetSearchLoc			= B100_TextControl.Trim(GetSearchLoc);				//ロケーション
					GetSearchType			= B100_TextControl.Trim(GetSearchType);				//ロケタイプ
					GetSearchQtyMin			= B100_TextControl.Trim(GetSearchQtyMin);			//数量最小
					GetSearchQtyMax			= B100_TextControl.Trim(GetSearchQtyMax);			//数量最大
					GetSearchShipPlanQtyMin	= B100_TextControl.Trim(GetSearchShipPlanQtyMin);	//引当済数最小
					GetSearchShipPlanQtyMax	= B100_TextControl.Trim(GetSearchShipPlanQtyMax);	//引当済数最大
					GetSearchPossibleQtyMin	= B100_TextControl.Trim(GetSearchPossibleQtyMin);	//出荷可能数最小
					GetSearchPossibleQtyMax	= B100_TextControl.Trim(GetSearchPossibleQtyMax);	//出荷可能数最大
					
					
					GetSearchType			= B100_TextControl.num_only_String(GetSearchType);					//ロケタイプ
					GetSearchQtyMin			= B100_TextControl.num_only_String02(GetSearchQtyMin);				//数量最小
					GetSearchQtyMax			= B100_TextControl.num_only_String02(GetSearchQtyMax);				//数量最大
					GetSearchShipPlanQtyMin	= B100_TextControl.num_only_String02(GetSearchShipPlanQtyMin);		//引当済数最小
					GetSearchShipPlanQtyMax	= B100_TextControl.num_only_String02(GetSearchShipPlanQtyMax);		//引当済数最大
					GetSearchPossibleQtyMin	= B100_TextControl.num_only_String02(GetSearchPossibleQtyMin);		//出荷可能数最小
					GetSearchPossibleQtyMax	= B100_TextControl.num_only_String02(GetSearchPossibleQtyMax);		//出荷可能数最大
					
					GetSearchExpdateMin		= B100_DateTimeControl.DateFormat(GetSearchExpdateMin);		//消費期限開始
					GetSearchExpdateMax		= B100_DateTimeControl.DateFormat(GetSearchExpdateMax);		//消費期限終了
					GetSearchActualDateMin	= B100_DateTimeControl.DateFormat(GetSearchActualDateMin);		//入荷実績日開始
					GetSearchActualDateMax	= B100_DateTimeControl.DateFormat(GetSearchActualDateMax);		//入荷実績日終了
					
					
					//次に訪れた時の為に検索条件覚える
					DefaultSearchClCd					= GetSearchClCd;
					DefaultSearchWhCd					= GetSearchWhCd;
					DefaultSearchClGpCD				= GetSearchClGpCD;
					DefaultSearchItemCd				= GetSearchItemCd;
					DefaultSearchItemName			= GetSearchItemName;
					DefaultSearchClItemCd			= GetSearchClItemCd;
					DefaultSearchJanCd				= GetSearchJanCd;
					DefaultSearchItemMdNo			= GetSearchItemMdNo;
					DefaultSearchLot					= GetSearchLot;
					DefaultSearchExpdateMin			= GetSearchExpdateMin;
					DefaultSearchExpdateMax			= GetSearchExpdateMax;
					DefaultSearchActualDateMin		= GetSearchActualDateMin;
					DefaultSearchActualDateMax		= GetSearchActualDateMax;
					
					DefaultSearchLoc					= GetSearchLoc;
					DefaultSearchType					= GetSearchType;
					DefaultSearchQtyMin				= GetSearchQtyMin;
					DefaultSearchQtyMax				= GetSearchShipPlanQtyMax;
					DefaultSearchShipPlanQtyMin		= GetSearchShipPlanQtyMin;
					DefaultSearchShipPlanQtyMax		= GetSearchShipPlanQtyMax;
					DefaultSearchPossibleQtyMin		= GetSearchPossibleQtyMin;
					GDefaultSearchPossibleQtyMax	= GetSearchPossibleQtyMax;
					
					DefaultSearchLotWhitespace 		= GetSearchLotWhitespace;
					DefaultLocExactMatch				=false;
					DefaultSortItemcdMode			=false;
					
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
					if(GetSearchLotWhitespace) {SearchLot.add("");}
					
					if(0==TB_LocExactMatch.getSelectedIndex()) {
						LocExactMatch = false;
						DefaultLocExactMatch = false;
					}else {
						LocExactMatch = true;
						DefaultLocExactMatch  = true;
					}
					
					if(0==TB_SortItemcdMode.getSelectedIndex()) {
						SortItemcdMode = false;
						DefaultSortItemcdMode = false;
					}else {
						SortItemcdMode = true;
						DefaultSortItemcdMode = true;
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
					
					for(int i=0;i<StockRt.length;i++) {
						Object[] SetOb = new Object[StockRt[i].length+1];
						SetOb[0] = false;
						for(int i01=0;i01<StockRt[i].length;i01++) {
							SetOb[i01+1] = ""+StockRt[i][i01];
						}
						tableModel_ms01.addRow(SetOb);
					}
					if(0<StockRt.length) {
						B100_TableControl.AddSortON(tb01,tableModel_ms01);
					}else {
						B100_TableControl.AddSortOFF(tb01,tableModel_ms01);
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
					TB_SearchClCd.setSelectedIndex(GetSelectIndex(B100_DefaultVariable.SearchClList[1],A00000_Main.ClCd));	//荷主コード
					TB_SearchWhCd.setSelectedIndex(GetSelectIndex(B100_DefaultVariable.SearchWhList[1],A00000_Main.ClWh));	//倉庫コード
					TB_SearchClGpCD.setSelectedIndex(GetSelectIndex(B100_DefaultVariable.SearchClGpList[1],A00000_Main.ClGp));	//荷主グループ
					
					TB_SearchItemCd.setText("");			//商品コード
					TB_SearchItemName.setText("");			//商品名
					TB_SearchClItemCd.setText("");			//荷主商品コード
					TB_SearchJanCd.setText("");				//BCD（バラ）
					TB_SearchItemMdNo.setText("");			//商品型番
					TB_SearchLot.setText("");				//ロット
					TB_SearchLotWhitespace.setSelected(false);
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
					B100_TableControl.AddSortOFF(tb01,tableModel_ms01);
					RenewFg = true;
				}
			}
		});
		
		//チェックボックス操作時の挙動
		tableModel_ms01.addTableModelListener(new TableModelListener(){
			public void tableChanged(TableModelEvent e){
				if(RenewFg) {
					RenewFg = false;
					LookUp_fm.setVisible(false);
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
					B100_TableControl.TableOutPutCsv("出力先選択","在庫検索結果",tb01);
					RenewFg = true;
				}
			}
		});
		
		//エクセル出力ボタン押下時の挙動
		ExcelBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					B100_TableControl.TableOutPutExcel("出力先選択","在庫検索結果",tb01);
					RenewFg = true;
				}
			}
		});

		//EXITボタン押下時の挙動
		exit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				LookUp_fm.setVisible(false);
				LookUp_fm.dispose();
				
				main_fm.setVisible(false);
				main_fm.dispose();
				A00001_WorkMain.WorkMain(0,0);
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