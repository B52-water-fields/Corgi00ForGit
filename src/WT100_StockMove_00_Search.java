import java.awt.Font;
import java.awt.event.ActionEvent;
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

public class WT100_StockMove_00_Search{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	public static void StockMoveSearch(int x,int y) {
		A00000_Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		final JFrame main_fm = B100_FrameParts.FrameCreate(x,y,850,820,"Corgi00在庫調整検索　WT100_StockMove_00_Search","ZK");
		JLabel userinfo = B100_FrameParts.UserInfo();
		JButton exit_btn = B100_FrameParts.ExitBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		
		//検索条件パネル
		JPanel PN_Search = B100_FrameParts.JPanelSet(10,40,810,300,"White");
		JLabel PN_SearchLabel = B100_FrameParts.JLabelSet(0,0,100,20,"検索条件",11,0);
		PN_Search.add(PN_SearchLabel);
		String[] LocExactMatchList = {"で始まる","と一致"};
		
		
		JLabel LB_SearchWhCd					= B100_FrameParts.JLabelSet(  0, 25, 80,20,"倉庫:"					,10,1);
		JLabel LB_SearchClCd					= B100_FrameParts.JLabelSet(  0, 50, 80,20,"荷主:"					,10,1);
		JLabel LB_SearchEntryDate				= B100_FrameParts.JLabelSet(  0, 75, 80,20,"移動日時:"				,10,1);
		JLabel LB_SearchMoveNo					= B100_FrameParts.JLabelSet(  0,100, 80,20,"移動番号:"				,10,1);
		JLabel LB_SearchEntryUser				= B100_FrameParts.JLabelSet(240,100, 80,20,"登録者:"				,10,1);
		JLabel LB_SearchFromLoc					= B100_FrameParts.JLabelSet(  0,125, 80,20,"元ロケ:"				,10,1);
		JLabel LB_SearchFromLocName				= B100_FrameParts.JLabelSet(  0,150, 80,20,"元ロケ名:"				,10,1);
		JLabel LB_SearchToLoc					= B100_FrameParts.JLabelSet(240,125, 80,20,"先ロケ:"				,10,1);
		JLabel LB_SearchToLocName				= B100_FrameParts.JLabelSet(240,150, 80,20,"先ロケ名:"				,10,1);
		JLabel LB_SearchMoveCom					= B100_FrameParts.JLabelSet(480,125, 80,20,"コメント:"				,10,1);
		JLabel LB_SearchMoveQty					= B100_FrameParts.JLabelSet(480,150, 80,20,"移動数:"				,10,1);
		
		JLabel LB_SearchItemCd					= B100_FrameParts.JLabelSet(  0,200, 80,20,"商品CD:"				,10,1);
		JLabel LB_SearchItemName				= B100_FrameParts.JLabelSet(  0,225, 80,20,"商品名:"				,10,1);
		
		JLabel LB_SearchLot						= B100_FrameParts.JLabelSet(240,200, 80,20,"ロット:"				,10,1);
		JLabel LB_SearchExpDate					= B100_FrameParts.JLabelSet(240,225, 80,20,"賞味期限:"				,10,1);
		JLabel LB_SearchActualDate				= B100_FrameParts.JLabelSet(240,250, 80,20,"入荷日:"				,10,1);
		
		
		final JComboBox TB_SearchWhCd								= B100_FrameParts.JComboBoxSet(				 80, 25,240,20,B100_DefaultVariable.SearchWhList[0],11);					//倉庫コード
		final JComboBox TB_SearchClCd								= B100_FrameParts.JComboBoxSet(				 80, 50,240,20,B100_DefaultVariable.SearchClList[0],11);					//荷主コード
		final JFormattedTextField TB_SearchEntryDateMin				= B100_FrameParts.JFormattedTextFieldSet(	 80, 75,140,20,""	,11,0,"YYYY/MM/DD HH:MM:SS");		//登録日最小
		final JFormattedTextField TB_SearchEntryDateMax				= B100_FrameParts.JFormattedTextFieldSet(	320, 75,140,20,""	,11,0,"YYYY/MM/DD HH:MM:SS");		//登録日最大
		final JTextField TB_SearchEntryUser							= B100_FrameParts.JTextFieldSet(				320,100,100,20,""	,11,0);					//登録者
		final JTextField TB_SearchMoveNo							= B100_FrameParts.JTextFieldSet(				 80,100,100,20,""	,11,0);					//調整番号
		final JTextField TB_SearchFromLoc							= B100_FrameParts.JTextFieldSet(				 80,125,100,20,""	,11,0);					//移動元ロケ
		final JComboBox TB_FromLocExactMatch						= B100_FrameParts.JComboBoxSet(				180,125, 80,20,LocExactMatchList,11);		//Fromロケーション完全一致
		final JTextField TB_SearchFromLocName						= B100_FrameParts.JTextFieldSet(				 80,150,100,20,""	,11,0);					//移動元ロケーション名
		final JTextField TB_SearchToLoc								= B100_FrameParts.JTextFieldSet(				320,125,100,20,""	,11,0);					//移動先ロケ
		final JComboBox TB_ToLocExactMatch							= B100_FrameParts.JComboBoxSet(				420,125, 80,20,LocExactMatchList,11);		//Toロケーション完全一致
		final JTextField TB_SearchToLocName							= B100_FrameParts.JTextFieldSet(				320,150,100,20,""	,11,0);					//移動先ロケーション名
		final JTextField TB_SearchMoveCom							= B100_FrameParts.JTextFieldSet(				560,125,140,20,""	,11,0);					//移動コメント
		final JFormattedTextField TB_SearchMoveQtyMin				= B100_FrameParts.JFormattedTextFieldSet(	560,150, 70,20,""	,11,1,"#,###");			//移動数最小
		final JFormattedTextField TB_SearchMoveQtyMax				= B100_FrameParts.JFormattedTextFieldSet(	670,150, 70,20,""	,11,1,"#,###");			//移動数最大
		
		final JTextField TB_SearchItemCd							= B100_FrameParts.JTextFieldSet(				 80,200,100,20,""	,11,0);					//商品CD
		final JTextField TB_SearchItemName							= B100_FrameParts.JTextFieldSet(				 80,225,100,20,""	,11,0);					//商品名
		
		final JTextField TB_SearchLot								= B100_FrameParts.JTextFieldSet(				320,200,100,20,""	,11,0);					//ロット
		final JFormattedTextField TB_SearchExpDateMin				= B100_FrameParts.JFormattedTextFieldSet(	320,225, 70,20,""	,11,0,"YYYY/MM/DD");	//賞味期限最小
		final JFormattedTextField TB_SearchExpDateMax				= B100_FrameParts.JFormattedTextFieldSet(	470,225, 70,20,""	,11,0,"YYYY/MM/DD");	//賞味期限最大
		final JFormattedTextField TB_SearchActualDateMin			= B100_FrameParts.JFormattedTextFieldSet(	320,250, 70,20,""	,11,0,"YYYY/MM/DD");	//入荷日最小
		final JFormattedTextField TB_SearchActualDateMax			= B100_FrameParts.JFormattedTextFieldSet(	470,250, 70,20,""	,11,0,"YYYY/MM/DD");	//入荷日最大
		
		
		JLabel LB2_SearchEntryDate					= B100_FrameParts.JLabelSet(260, 75, 60,20,"～"		,10,2);
		JLabel LB2_SearchMoveNo						= B100_FrameParts.JLabelSet(180,100, 60,20,"と一致"	,10,0);
		JLabel LB2_SearchEntryUser					= B100_FrameParts.JLabelSet(420,100, 80,20,"を含む"	,10,0);
		JLabel LB2_SearchFromLocName				= B100_FrameParts.JLabelSet(180,150, 60,20,"を含む"	,10,0);
		JLabel LB2_SearchToLocName					= B100_FrameParts.JLabelSet(420,150, 60,20,"を含む"	,10,0);
		JLabel LB2_SearchMoveCom					= B100_FrameParts.JLabelSet(700,125, 60,20,"を含む"	,10,0);
		JLabel LB2_SearchMoveQty					= B100_FrameParts.JLabelSet(630,150, 40,20,"～"		,10,2);
		
		JLabel LB2_SearchItemCd						= B100_FrameParts.JLabelSet(180,200, 60,20,"と一致"	,10,0);
		JLabel LB2_SearchItemName					= B100_FrameParts.JLabelSet(180,225, 60,20,"を含む"	,10,0);
		
		JLabel LB2_SearchLot						= B100_FrameParts.JLabelSet(420,200, 60,20,"と一致"	,10,0);
		JLabel LB2_SearchExpDate					= B100_FrameParts.JLabelSet(430,225, 40,20,"～"		,10,2);
		JLabel LB2_SearchActualDate					= B100_FrameParts.JLabelSet(430,250, 40,20,"～"		,10,2);
		
		
		//条件クリアボタン
		JButton SearchCrearBtn 	= B100_FrameParts.BtnSet(		700, 75,100,20,"条件クリア",10);
		PN_Search.add(SearchCrearBtn);
		
		//検索ボタン
		JButton SearchBtn 		= B100_FrameParts.BtnSet(		700,250,100,20,"検索",11);
		PN_Search.add(SearchBtn);
		
		//日付進む戻るボタン
		JButton SearchEntryDateMinAfterBtn		= B100_FrameParts.BtnSet(	220, 75, 40,10,"▲",6);
		JButton SearchEntryDateMinBeforeBtn		= B100_FrameParts.BtnSet(	220, 85, 40,10,"▼",6);
		JButton SearchEntryDateMaxAfterBtn		= B100_FrameParts.BtnSet(	460, 75, 40,10,"▲",6);
		JButton SearchEntryDateMaxBeforeBtn		= B100_FrameParts.BtnSet(	460, 85, 40,10,"▼",6);
		
		JButton SearchExpDateMinAfterBtn		= B100_FrameParts.BtnSet(	390,225, 40,10,"▲",6);
		JButton SearchExpDateMinBeforeBtn		= B100_FrameParts.BtnSet(	390,235, 40,10,"▼",6);
		JButton SearchExpDateMaxAfterBtn		= B100_FrameParts.BtnSet(	540,225, 40,10,"▲",6);
		JButton SearchExpDateMaxBeforeBtn		= B100_FrameParts.BtnSet(	540,235, 40,10,"▼",6);
		
		JButton SearchActualDateMinAfterBtn		= B100_FrameParts.BtnSet(	390,250, 40,10,"▲",6);
		JButton SearchActualDateMinBeforeBtn	= B100_FrameParts.BtnSet(	390,260, 40,10,"▼",6);
		JButton SearchActualDateMaxAfterBtn		= B100_FrameParts.BtnSet(	540,250, 40,10,"▲",6);
		JButton SearchActualDateMaxBeforeBtn	= B100_FrameParts.BtnSet(	540,260, 40,10,"▼",6);
		
		TB_SearchWhCd.setSelectedIndex(		B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.SearchWhList[1]		,A00000_Main.ClWh,true));			//ヘッダ担当倉庫
		TB_SearchClCd.setSelectedIndex(		B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.SearchClList[1]		,A00000_Main.ClCd,true));			//ヘッダ荷主CD
		
		TB_SearchWhCd.setEnabled(false);
		TB_SearchClCd.setEnabled(false);
		
		PN_Search.add(SearchEntryDateMinAfterBtn);
		PN_Search.add(SearchEntryDateMinBeforeBtn);
		PN_Search.add(SearchEntryDateMaxAfterBtn);
		PN_Search.add(SearchEntryDateMaxBeforeBtn);
		
		PN_Search.add(SearchExpDateMinAfterBtn);
		PN_Search.add(SearchExpDateMinBeforeBtn);
		PN_Search.add(SearchExpDateMaxAfterBtn);
		PN_Search.add(SearchExpDateMaxBeforeBtn);
		
		PN_Search.add(SearchActualDateMinAfterBtn);
		PN_Search.add(SearchActualDateMinBeforeBtn);
		PN_Search.add(SearchActualDateMaxAfterBtn);
		PN_Search.add(SearchActualDateMaxBeforeBtn);
		
		SearchEntryDateMinAfterBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				B100_DateTimeControl.AfterDateTimeSet(TB_SearchEntryDateMin);
			}
		});
		SearchEntryDateMinBeforeBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				B100_DateTimeControl.BeforeDateTimeSet(TB_SearchEntryDateMin);
			}
		});
		SearchEntryDateMaxAfterBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				B100_DateTimeControl.AfterDateTimeSet(TB_SearchEntryDateMax);
			}
		});
		SearchEntryDateMaxBeforeBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				B100_DateTimeControl.BeforeDateTimeSet(TB_SearchEntryDateMax);
			}
		});
		
		SearchExpDateMinAfterBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				B100_DateTimeControl.AfterDateSet(TB_SearchExpDateMin);
			}
		});
		SearchExpDateMinBeforeBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				B100_DateTimeControl.BeforeDateSet(TB_SearchExpDateMin);
			}
		});
		
		SearchExpDateMaxAfterBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				B100_DateTimeControl.AfterDateSet(TB_SearchExpDateMax);
			}
		});
		SearchExpDateMaxBeforeBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				B100_DateTimeControl.BeforeDateSet(TB_SearchExpDateMax);
			}
		});
		
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
		
		PN_Search.add(LB_SearchClCd);
		PN_Search.add(LB_SearchWhCd);
		PN_Search.add(LB_SearchEntryDate);
		PN_Search.add(LB_SearchMoveNo);
		PN_Search.add(LB_SearchEntryUser);
		PN_Search.add(LB_SearchFromLoc);
		PN_Search.add(LB_SearchFromLocName);
		PN_Search.add(LB_SearchToLoc);
		PN_Search.add(LB_SearchToLocName);
		PN_Search.add(LB_SearchItemCd);
		PN_Search.add(LB_SearchItemName);
		PN_Search.add(LB_SearchLot);
		PN_Search.add(LB_SearchExpDate);
		PN_Search.add(LB_SearchActualDate);
		PN_Search.add(LB_SearchMoveCom);
		PN_Search.add(LB_SearchMoveQty);
		
		PN_Search.add(TB_SearchClCd);			//荷主コード
		PN_Search.add(TB_SearchWhCd);			//倉庫コード
		PN_Search.add(TB_SearchEntryDateMin);	//登録日最小
		PN_Search.add(TB_SearchEntryDateMax);	//更新日最小
		PN_Search.add(TB_SearchMoveNo);			//調整番号
		PN_Search.add(TB_SearchEntryUser);		//登録者
		PN_Search.add(TB_SearchFromLoc);		//移動元ロケ
		PN_Search.add(TB_FromLocExactMatch);	//Fromロケーション完全一致
		PN_Search.add(TB_SearchFromLocName);	//移動元ロケーション名
		PN_Search.add(TB_SearchToLoc);			//移動先ロケ
		PN_Search.add(TB_ToLocExactMatch);		//Toロケーション完全一致
		PN_Search.add(TB_SearchToLocName);		//移動先ロケーション名
		PN_Search.add(TB_SearchItemCd);			//商品CD
		PN_Search.add(TB_SearchItemName);		//商品名
		PN_Search.add(TB_SearchLot);			//ロット
		PN_Search.add(TB_SearchExpDateMin);		//賞味期限最小
		PN_Search.add(TB_SearchExpDateMax);		//賞味期限最大
		PN_Search.add(TB_SearchActualDateMin);	//入荷日最小
		PN_Search.add(TB_SearchActualDateMax);	//入荷日最大
		PN_Search.add(TB_SearchMoveQtyMin);		//移動数最小
		PN_Search.add(TB_SearchMoveQtyMax);		//移動数最大
		PN_Search.add(TB_SearchMoveCom);		//移動コメント
		
		
		
		PN_Search.add(LB2_SearchEntryDate);
		PN_Search.add(LB2_SearchMoveNo);
		PN_Search.add(LB2_SearchEntryUser);
		PN_Search.add(LB2_SearchFromLocName);
		PN_Search.add(LB2_SearchToLocName);
		PN_Search.add(LB2_SearchItemCd);
		PN_Search.add(LB2_SearchItemName);
		PN_Search.add(LB2_SearchLot);
		PN_Search.add(LB2_SearchExpDate);
		PN_Search.add(LB2_SearchActualDate);
		
		PN_Search.add(LB2_SearchMoveQty);
		PN_Search.add(LB2_SearchMoveCom);
		
		main_fm.add(PN_Search);
		
		Object[][] RtStockMoveRt = T100_StockMoveRt.RtStockMoveRt();
		
		String[] columnNames01 = new String[RtStockMoveRt.length+1];
		
		columnNames01[0] = "Fg";
		for(int i=0;i<RtStockMoveRt.length;i++) {
			columnNames01[1+(int)RtStockMoveRt[i][1]] = ""+RtStockMoveRt[i][3];
		}
		
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
		
		column = columnModel01.getColumn( 0);	column.setPreferredWidth( 30*A00000_Main.Mul/A00000_Main.Div);	//FG
		
		for(int i=0;i<RtStockMoveRt.length;i++) {
			if("int".equals((String)RtStockMoveRt[i][2])||"float".equals((String)RtStockMoveRt[i][2])) {
				column = columnModel01.getColumn(1+(int)RtStockMoveRt[i][1]);	column.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.rightCellRenderer());
			}else {
				column = columnModel01.getColumn(1+(int)RtStockMoveRt[i][1]);	column.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.leftCellRenderer());
			}
		}
		//スクロール用設定
		JScrollPane scpn01 = B100_FrameParts.JScrollPaneSet(10,350,810,365,tb01);
		main_fm.add(scpn01);
		
		//CSVボタン
		JButton CsvBtn = B100_FrameParts.BtnSet(		 10,740,100,20,"csv出力",11);
		main_fm.add(CsvBtn);
		//Excel出力ボタン
		JButton ExcelBtn = B100_FrameParts.BtnSet(		130,740,100,20,"Excel出力",11);
		main_fm.add(ExcelBtn);
		
		RenewFg = true;
		main_fm.setVisible(true);
		
		//検索ボタン押下時の挙動
		SearchBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					ArrayList<String> SearchClCd						= new ArrayList<String>();		//荷主コード
					ArrayList<String> SearchCLName						= new ArrayList<String>();		//荷主名
					ArrayList<String> SearchWhCd						= new ArrayList<String>();		//倉庫コード
					ArrayList<String> SearchClWHName					= new ArrayList<String>();		//担当倉庫名
					ArrayList<String> SearchMoveNo						= new ArrayList<String>();		//調整番号
					ArrayList<String> SearchFromLoc						= new ArrayList<String>();		//移動元ロケ
					ArrayList<String> SearchFromLocName					= new ArrayList<String>();		//移動元ロケーション名
					ArrayList<String> SearchToLoc						= new ArrayList<String>();		//移動先ロケ
					ArrayList<String> SearchToLocName					= new ArrayList<String>();		//移動先ロケーション名
					ArrayList<String> SearchItemCd						= new ArrayList<String>();		//商品CD
					ArrayList<String> SearchItemName					= new ArrayList<String>();		//商品名
					ArrayList<String> SearchLot							= new ArrayList<String>();		//ロット
					ArrayList<String> SearchExpDateMin					= new ArrayList<String>();		//賞味期限最小
					ArrayList<String> SearchExpDateMax					= new ArrayList<String>();		//賞味期限最大
					ArrayList<String> SearchActualDateMin				= new ArrayList<String>();		//入荷日最小
					ArrayList<String> SearchActualDateMax				= new ArrayList<String>();		//入荷日最大
					ArrayList<Integer> SearchBeforeFromQtyMin			= new ArrayList<Integer>();		//（移動前）移動元在庫数最小
					ArrayList<Integer> SearchBeforeFromPlanQtyMin		= new ArrayList<Integer>();		//（移動前）移動元引当済数最小
					ArrayList<Integer> SearchBeforeFromPossibleQtyMin	= new ArrayList<Integer>();		//（移動前）移動元出荷可能数最小
					ArrayList<Integer> SearchBeforeToQtyMin				= new ArrayList<Integer>();		//（移動前）移動先在庫数最小
					ArrayList<Integer> SearchBeforeToPlanQtyMin			= new ArrayList<Integer>();		//（移動前）移動先引当済数最小
					ArrayList<Integer> SearchBeforeToPossibleQtyMin		= new ArrayList<Integer>();		//（移動前）移動先出荷可能数最小
					ArrayList<Integer> SearchMoveQtyMin					= new ArrayList<Integer>();		//移動数最小
					ArrayList<Integer> SearchAfterFromQtyMin			= new ArrayList<Integer>();		//（移動後）移動元在庫数最小
					ArrayList<Integer> SearchAfterFromPlanQtyMin		= new ArrayList<Integer>();		//（移動後）移動元引当済数最小
					ArrayList<Integer> SearchAfterFromPossibleQtyMin	= new ArrayList<Integer>();		//（移動後）移動元出荷可能数最小
					ArrayList<Integer> SearchAfterToQtyMin				= new ArrayList<Integer>();		//（移動後）移動先在庫数最小
					ArrayList<Integer> SearchAfterToPlanQtyMin			= new ArrayList<Integer>();		//（移動後）移動先引当済数最小
					ArrayList<Integer> SearchAfterToPossibleQtyMin		= new ArrayList<Integer>();		//（移動後）移動先出荷可能数最小
					ArrayList<Integer> SearchBeforeFromQtyMax			= new ArrayList<Integer>();		//（移動前）移動元在庫数最大
					ArrayList<Integer> SearchBeforeFromPlanQtyMax		= new ArrayList<Integer>();		//（移動前）移動元引当済数最大
					ArrayList<Integer> SearchBeforeFromPossibleQtyMax	= new ArrayList<Integer>();		//（移動前）移動元出荷可能数最大
					ArrayList<Integer> SearchBeforeToQtyMax				= new ArrayList<Integer>();		//（移動前）移動先在庫数最大
					ArrayList<Integer> SearchBeforeToPlanQtyMax			= new ArrayList<Integer>();		//（移動前）移動先引当済数最大
					ArrayList<Integer> SearchBeforeToPossibleQtyMax		= new ArrayList<Integer>();		//（移動前）移動先出荷可能数最大
					ArrayList<Integer> SearchMoveQtyMax					= new ArrayList<Integer>();		//移動数最大
					ArrayList<Integer> SearchAfterFromQtyMax			= new ArrayList<Integer>();		//（移動後）移動元在庫数最大
					ArrayList<Integer> SearchAfterFromPlanQtyMax		= new ArrayList<Integer>();		//（移動後）移動元引当済数最大
					ArrayList<Integer> SearchAfterFromPossibleQtyMax	= new ArrayList<Integer>();		//（移動後）移動元出荷可能数最大
					ArrayList<Integer> SearchAfterToQtyMax				= new ArrayList<Integer>();		//（移動後）移動先在庫数最大
					ArrayList<Integer> SearchAfterToPlanQtyMax			= new ArrayList<Integer>();		//（移動後）移動先引当済数最大
					ArrayList<Integer> SearchAfterToPossibleQtyMax		= new ArrayList<Integer>();		//（移動後）移動先出荷可能数最大
					ArrayList<String> SearchMoveCom						= new ArrayList<String>();		//移動コメント
					ArrayList<String> SearchEntryDateMin				= new ArrayList<String>();		//登録日最小
					ArrayList<String> SearchUpdateDateMin				= new ArrayList<String>();		//更新日最小
					ArrayList<String> SearchEntryDateMax				= new ArrayList<String>();		//登録日最大
					ArrayList<String> SearchUpdateDateMax				= new ArrayList<String>();		//更新日最大
					ArrayList<String> SearchEntryUser					= new ArrayList<String>();		//登録者
					ArrayList<String> SearchUpdateUser					= new ArrayList<String>();		//更新者
					boolean AllSearch									= false;
					
					String GetSearchWhCd			= B100_TextControl.Trim(B100_DefaultVariable.SearchWhList[1][TB_SearchWhCd.getSelectedIndex()]);					//倉庫コード
					String GetSearchClCd			= B100_TextControl.Trim(B100_DefaultVariable.SearchClList[1][TB_SearchClCd.getSelectedIndex()]);					//荷主コード
					String GetSearchEntryDateMin	= B100_TextControl.Trim(TB_SearchEntryDateMin.getText());					//登録日最小
					String GetSearchEntryDateMax	= B100_TextControl.Trim(TB_SearchEntryDateMax.getText());					//登録日最大
					String GetSearchEntryUser		= B100_TextControl.Trim(TB_SearchEntryUser.getText());						//登録者
					String GetSearchMoveNo			= B100_TextControl.Trim(TB_SearchMoveNo.getText());							//調整番号
					String GetSearchFromLoc			= B100_TextControl.Trim(TB_SearchFromLoc.getText());						//移動元ロケ
					String GetSearchFromLocName		= B100_TextControl.Trim(TB_SearchFromLocName.getText());					//移動元ロケーション名
					String GetSearchToLoc			= B100_TextControl.Trim(TB_SearchToLoc.getText());							//移動先ロケ
					String GetSearchToLocName		= B100_TextControl.Trim(TB_SearchToLocName.getText());						//移動先ロケーション名
					String GetSearchMoveCom			= B100_TextControl.Trim(TB_SearchMoveCom.getText());						//移動コメント
					String GetSearchMoveQtyMin		= B100_TextControl.num_only_String02(TB_SearchMoveQtyMin.getText());		//移動数最小
					String GetSearchMoveQtyMax		= B100_TextControl.num_only_String02(TB_SearchMoveQtyMax.getText());		//移動数最大
					String GetSearchItemCd			= B100_TextControl.Trim(TB_SearchItemCd.getText());							//商品CD
					String GetSearchItemName		= B100_TextControl.Trim(TB_SearchItemName.getText());						//商品名
					String GetSearchLot				= B100_TextControl.Trim(TB_SearchLot.getText());							//ロット
					String GetSearchExpDateMin		= B100_TextControl.TextToDate(TB_SearchExpDateMin.getText());				//賞味期限最小
					String GetSearchExpDateMax		= B100_TextControl.TextToDate(TB_SearchExpDateMax.getText());				//賞味期限最大
					String GetSearchActualDateMin	= B100_TextControl.TextToDate(TB_SearchActualDateMin.getText());			//入荷日最小
					String GetSearchActualDateMax	= B100_TextControl.TextToDate(TB_SearchActualDateMax.getText());			//入荷日最大
					
					
					if(!"".equals(GetSearchWhCd				)){SearchWhCd.add(GetSearchWhCd);}
					if(!"".equals(GetSearchClCd				)){SearchClCd.add(GetSearchClCd);}
					if(!"".equals(GetSearchEntryDateMin		)){SearchEntryDateMin.add(GetSearchEntryDateMin);}
					if(!"".equals(GetSearchEntryDateMax		)){SearchEntryDateMax.add(GetSearchEntryDateMax);}
					if(!"".equals(GetSearchEntryUser		)){SearchEntryUser.add(GetSearchEntryUser);}
					if(!"".equals(GetSearchMoveNo			)){SearchMoveNo.add(GetSearchMoveNo);}
					if(!"".equals(GetSearchFromLoc			)){SearchFromLoc.add(GetSearchFromLoc);}
					if(!"".equals(GetSearchFromLocName		)){SearchFromLocName.add(GetSearchFromLocName);}
					if(!"".equals(GetSearchToLoc			)){SearchToLoc.add(GetSearchToLoc);}
					if(!"".equals(GetSearchToLocName		)){SearchToLocName.add(GetSearchToLocName);}
					if(!"".equals(GetSearchMoveCom			)){SearchMoveCom.add(GetSearchMoveCom);}
					if(!"".equals(GetSearchMoveQtyMin		)){SearchMoveQtyMin.add(B100_TextControl.TextToInt(GetSearchMoveQtyMin));}
					if(!"".equals(GetSearchMoveQtyMax		)){SearchMoveQtyMax.add(B100_TextControl.TextToInt(GetSearchMoveQtyMax));}
					if(!"".equals(GetSearchItemCd			)){SearchItemCd.add(GetSearchItemCd);}
					if(!"".equals(GetSearchItemName			)){SearchItemName.add(GetSearchItemName);}
					if(!"".equals(GetSearchLot				)){SearchLot.add(GetSearchLot);}
					if(!"".equals(GetSearchExpDateMin		)){SearchExpDateMin.add(GetSearchExpDateMin);}
					if(!"".equals(GetSearchExpDateMax		)){SearchExpDateMax.add(GetSearchExpDateMax);}
					if(!"".equals(GetSearchActualDateMin	)){SearchActualDateMin.add(GetSearchActualDateMin);}
					if(!"".equals(GetSearchActualDateMax	)){SearchActualDateMax.add(GetSearchActualDateMax);}
					
					boolean FromLocExactMatch	= true;							//Fromロケーション完全一致
					boolean ToLocExactMatch		= true;							//Toロケーション完全一致
					if(0==TB_FromLocExactMatch.getSelectedIndex()) {
						FromLocExactMatch	= false;
					}else {
						FromLocExactMatch	= true;
					}
					if(0==TB_ToLocExactMatch.getSelectedIndex()) {
						ToLocExactMatch	= false;
					}else {
						ToLocExactMatch		= true;
					}
					
					Object[][] StockMoveRt	= T100_StockMoveRt.StockMoveRt(
							SearchClCd,						//荷主コード
							SearchCLName,					//荷主名
							SearchWhCd,						//倉庫コード
							SearchClWHName,					//担当倉庫名
							SearchMoveNo,					//調整番号
							SearchFromLoc,					//移動元ロケ
							SearchFromLocName,				//移動元ロケーション名
							SearchToLoc,					//移動先ロケ
							SearchToLocName,				//移動先ロケーション名
							SearchItemCd,					//商品CD
							SearchItemName,					//商品名
							SearchLot,						//ロット
							SearchExpDateMin,				//賞味期限最小
							SearchExpDateMax,				//賞味期限最大
							SearchActualDateMin,			//入荷日最小
							SearchActualDateMax,			//入荷日最大
							SearchBeforeFromQtyMin,			//（移動前）移動元在庫数最小
							SearchBeforeFromPlanQtyMin,		//（移動前）移動元引当済数最小
							SearchBeforeFromPossibleQtyMin,	//（移動前）移動元出荷可能数最小
							SearchBeforeToQtyMin,			//（移動前）移動先在庫数最小
							SearchBeforeToPlanQtyMin,		//（移動前）移動先引当済数最小
							SearchBeforeToPossibleQtyMin,	//（移動前）移動先出荷可能数最小
							SearchMoveQtyMin,				//移動数最小
							SearchAfterFromQtyMin,			//（移動後）移動元在庫数最小
							SearchAfterFromPlanQtyMin,		//（移動後）移動元引当済数最小
							SearchAfterFromPossibleQtyMin,	//（移動後）移動元出荷可能数最小
							SearchAfterToQtyMin,			//（移動後）移動先在庫数最小
							SearchAfterToPlanQtyMin,		//（移動後）移動先引当済数最小
							SearchAfterToPossibleQtyMin,	//（移動後）移動先出荷可能数最小
							SearchBeforeFromQtyMax,			//（移動前）移動元在庫数最大
							SearchBeforeFromPlanQtyMax,		//（移動前）移動元引当済数最大
							SearchBeforeFromPossibleQtyMax,	//（移動前）移動元出荷可能数最大
							SearchBeforeToQtyMax,			//（移動前）移動先在庫数最大
							SearchBeforeToPlanQtyMax,		//（移動前）移動先引当済数最大
							SearchBeforeToPossibleQtyMax,	//（移動前）移動先出荷可能数最大
							SearchMoveQtyMax,				//移動数最大
							SearchAfterFromQtyMax,			//（移動後）移動元在庫数最大
							SearchAfterFromPlanQtyMax,		//（移動後）移動元引当済数最大
							SearchAfterFromPossibleQtyMax,	//（移動後）移動元出荷可能数最大
							SearchAfterToQtyMax,			//（移動後）移動先在庫数最大
							SearchAfterToPlanQtyMax,		//（移動後）移動先引当済数最大
							SearchAfterToPossibleQtyMax,	//（移動後）移動先出荷可能数最大
							SearchMoveCom,					//移動コメント
							SearchEntryDateMin,				//登録日最小
							SearchUpdateDateMin,			//更新日最小
							SearchEntryDateMax,				//登録日最大
							SearchUpdateDateMax,			//更新日最大
							SearchEntryUser,				//登録者
							SearchUpdateUser,				//更新者
							FromLocExactMatch,				//Fromロケーション完全一致
							ToLocExactMatch,				//Toロケーション完全一致
							AllSearch);
					
					int row_count = MainFmTableModel.getRowCount();
					for(int i=0;i<row_count;i++){
						MainFmTableModel.removeRow(0);
					}
					
					for(int i=0;i<StockMoveRt.length;i++) {
						Object[] SetOb = new Object[StockMoveRt[i].length+1];
						SetOb[0] = false;
						for(int i01=0;i01<StockMoveRt[i].length;i01++) {
							SetOb[i01+1] = ""+StockMoveRt[i][i01];
						}
						MainFmTableModel.addRow(SetOb);
					}
					if(0<StockMoveRt.length) {
						B100_TableControl.AddSortON(tb01,MainFmTableModel);
					}else {
						B100_TableControl.AddSortOFF(tb01,MainFmTableModel);
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
					int row_count = MainFmTableModel.getRowCount();
					for(int i=0;i<row_count;i++){
						MainFmTableModel.removeRow(0);
					}
					TB_SearchWhCd.setSelectedIndex(		B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.SearchWhList[1]		,A00000_Main.ClWh,true));
					TB_SearchClCd.setSelectedIndex(		B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.SearchClList[1]		,A00000_Main.ClCd,true));
					TB_SearchEntryDateMin.setText("");
					TB_SearchEntryDateMax.setText("");
					TB_SearchEntryUser.setText("");
					TB_SearchMoveNo.setText("");
					TB_SearchFromLoc.setText("");
					TB_FromLocExactMatch.setSelectedIndex(0);
					TB_SearchFromLocName.setText("");
					TB_SearchToLoc.setText("");
					TB_ToLocExactMatch.setSelectedIndex(0);
					TB_SearchToLocName.setText("");
					TB_SearchMoveCom.setText("");
					TB_SearchMoveQtyMin.setText("");
					TB_SearchMoveQtyMax.setText("");
					
					TB_SearchItemCd.setText("");
					TB_SearchItemName.setText("");
					
					TB_SearchLot.setText("");
					TB_SearchExpDateMin.setText("");
					TB_SearchExpDateMax.setText("");
					TB_SearchActualDateMin.setText("");
					TB_SearchActualDateMax.setText("");
					
					RenewFg = true;
				}
			}
		});
		
		//チェックボックス操作時の挙動
		MainFmTableModel.addTableModelListener(new TableModelListener(){
			public void tableChanged(TableModelEvent e){
				if(RenewFg) {
					RenewFg = false;
					int row_count = MainFmTableModel.getRowCount();
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
		
		//CSVボタン押下時の挙動
		CsvBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					B100_TableControl.TableOutPutCsv("出力先選択","在庫移動検索結果",tb01);
					RenewFg = true;
				}
			}
		});
		
		//エクセル出力ボタン押下時の挙動
		ExcelBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					B100_TableControl.TableOutPutExcel("出力先選択","在庫移動検索結果",tb01);
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
				A00001_WorkMain.WorkMain(0,0);
			}
		});
	}
}