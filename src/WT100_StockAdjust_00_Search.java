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

public class WT100_StockAdjust_00_Search{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	public static void StockAdjustSearch(int x,int y) {
		A00000_Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		
		final JFrame main_fm = B100_FrameParts.FrameCreate(x,y,850,820,"Corgi00在庫調整検索　WT100_StockAdjust_00_Search","ZK");
		JLabel userinfo = B100_FrameParts.UserInfo();
		JButton exit_btn = B100_FrameParts.ExitBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		
		//検索条件パネル
		JPanel PN_Search = B100_FrameParts.JPanelSet(10,40,810,300,"White");
		JLabel PN_SearchLabel = B100_FrameParts.JLabelSet(0,0,100,20,"検索条件",11,0);
		String[] LocExactMatchList = {"で始まる","と一致"};
		
		JLabel LB_SearchClCd				= B100_FrameParts.JLabelSet(  0, 25,130,20,"荷主コード:"				,11,1);
		JLabel LB_SearchWhCd				= B100_FrameParts.JLabelSet(  0, 50,130,20,"倉庫コード:"				,11,1);
		JLabel LB_SearchClGpCD				= B100_FrameParts.JLabelSet(  0, 75,130,20,"荷主グループCD:"			,11,1);
		JLabel LB_SearchAdjustNo			= B100_FrameParts.JLabelSet(  0,100,130,20,"調整番号:"					,11,1);
		JLabel LB_SearchAdjustReasonCd		= B100_FrameParts.JLabelSet(  0,125,130,20,"調整理由コード:"			,11,1);
		JLabel LB_SearchAdjustReasonName	= B100_FrameParts.JLabelSet(  0,150,130,20,"調整理由名:"				,11,1);
		JLabel LB_SearchAdjustdate			= B100_FrameParts.JLabelSet(  0,175,130,20,"調整日:"					,11,1);
		JLabel LB_SearchLoc					= B100_FrameParts.JLabelSet(  0,200,130,20,"調整元ロケ:"				,11,1);
		JLabel LB_SearchType				= B100_FrameParts.JLabelSet(  0,225,130,20,"ロケタイプ:"				,11,1);
		JLabel LB_SearchAdjustCom			= B100_FrameParts.JLabelSet(  0,250,130,20,"調整理由コメント:"			,11,1);
		
		JLabel LB_SearchItemCd				= B100_FrameParts.JLabelSet(370, 25,130,20,"調整元商品CD:"				,11,1);
		JLabel LB_SearchItemName			= B100_FrameParts.JLabelSet(370, 50,130,20,"調整元商品名:"				,11,1);
		JLabel LB_SearchLot					= B100_FrameParts.JLabelSet(370, 75,130,20,"調整元ロット:"				,11,1);
		JLabel LB_SearchExpDate				= B100_FrameParts.JLabelSet(370,100,130,20,"調整元賞味期限:"			,11,1);
		JLabel LB_SearchActualDate			= B100_FrameParts.JLabelSet(370,125,130,20,"調整元入荷日:"				,11,1);
		JLabel LB_SearchAdjustQty			= B100_FrameParts.JLabelSet(370,150,130,20,"調整数:"					,11,1);
		
		final JComboBox TB_SearchClWh						= B100_FrameParts.JComboBoxSet(				130, 25,240,20,B100_DefaultVariable.SearchWhList[0],11);		//ヘッダ担当倉庫
		final JComboBox TB_SearchClCd						= B100_FrameParts.JComboBoxSet(				130, 50,240,20,B100_DefaultVariable.SearchClList[0],11);		//ヘッダ荷主CD
		final JComboBox TB_SearchClGpCD						= B100_FrameParts.JComboBoxSet(				130, 75,240,20,B100_DefaultVariable.SearchClGpList[0],11);	//ヘッダ荷主グループCD
		final JTextField TB_SearchAdjustNo					= B100_FrameParts.JTextFieldSet(				130,100,100,20,""	,11,0);										//調整番号
		final JTextField TB_SearchAdjustReasonCd			= B100_FrameParts.JTextFieldSet(				130,125,100,20,""	,11,0);										//調整理由コード
		final JTextField TB_SearchAdjustReasonName			= B100_FrameParts.JTextFieldSet(				130,150,100,20,""	,11,0);										//調整理由名
		final JFormattedTextField TB_SearchAdjustdateMin	= B100_FrameParts.JFormattedTextFieldSet(	130,175, 70,20,""	,11,0,"YYYY/MM/DD");						//調整日最小
		final JFormattedTextField TB_SearchAdjustdateMax	= B100_FrameParts.JFormattedTextFieldSet(	270,175, 70,20,""	,11,0,"YYYY/MM/DD");						//調整日最大
		final JTextField TB_SearchLoc						= B100_FrameParts.JTextFieldSet(				130,200,100,20,""	,11,0);										//調整元ロケ
		final JComboBox TB_LocExactMatch					= B100_FrameParts.JComboBoxSet(				230,200, 80,20,LocExactMatchList,11);							//ロケーション完全一致
		final JComboBox TB_SearchType						= B100_FrameParts.JComboBoxSet(				130,225,100,20,B100_DefaultVariable.SearchLocType[0],11);		//ロケタイプ
		final JTextField TB_SearchAdjustCom					= B100_FrameParts.JTextFieldSet(				130,250,100,20,"",11,0);										//調整理由コメント
		
		final JTextField TB_SearchItemCd					= B100_FrameParts.JTextFieldSet(				500, 25,100,20,""	,11,0);										//調整元商品CD
		final JTextField TB_SearchItemName					= B100_FrameParts.JTextFieldSet(				500, 50,100,20,""	,11,0);										//調整元商品名
		final JTextField TB_SearchLot						= B100_FrameParts.JTextFieldSet(				500, 75,100,20,""	,11,0);										//調整元ロット
		final JFormattedTextField TB_SearchExpDateMin		= B100_FrameParts.JFormattedTextFieldSet(	500,100, 70,20,""	,11,0,"YYYY/MM/DD");						//調整元賞味期限最小
		final JFormattedTextField TB_SearchExpDateMax		= B100_FrameParts.JFormattedTextFieldSet(	640,100, 70,20,""	,11,0,"YYYY/MM/DD");						//調整元賞味期限最大
		final JFormattedTextField TB_SearchActualDateMin	= B100_FrameParts.JFormattedTextFieldSet(	500,125, 70,20,""	,11,0,"YYYY/MM/DD");						//調整元入荷日最小
		final JFormattedTextField TB_SearchActualDateMax	= B100_FrameParts.JFormattedTextFieldSet(	640,125, 70,20,""	,11,0,"YYYY/MM/DD");						//調整元入荷日最大
		final JFormattedTextField TB_SearchAdjustQtyMin		= B100_FrameParts.JFormattedTextFieldSet(	500,150, 70,20,""	,11,1,"#,###");								//調整数最小
		final JFormattedTextField TB_SearchAdjustQtyMax		= B100_FrameParts.JFormattedTextFieldSet(	600,150, 70,20,""	,11,1,"#,###");								//調整数最大
		
		JLabel LB2_SearchAdjustNo			= B100_FrameParts.JLabelSet(230,100, 80,20,"と一致"		,11,0);
		JLabel LB2_SearchAdjustReasonCd		= B100_FrameParts.JLabelSet(230,125, 80,20,"と一致"		,11,0);
		JLabel LB2_SearchAdjustReasonName	= B100_FrameParts.JLabelSet(230,150, 80,20,"を含む"		,11,0);
		JLabel LB2_SearchAdjustdate			= B100_FrameParts.JLabelSet(240,175, 30,20,"～"			,11,2);
		JLabel LB2_SearchAdjustCom			= B100_FrameParts.JLabelSet(230,250, 80,20,"を含む"		,11,0);
		
		
		JLabel LB2_SearchItemCd				= B100_FrameParts.JLabelSet(600, 25, 80,20,"と一致"		,11,0);
		JLabel LB2_SearchItemName			= B100_FrameParts.JLabelSet(600, 50, 80,20,"を含む"		,11,0);
		JLabel LB2_SearchLot				= B100_FrameParts.JLabelSet(600, 75, 80,20,"と一致"		,11,0);
		JLabel LB2_SearchExpDate			= B100_FrameParts.JLabelSet(610,100, 30,20,"～"			,11,2);
		JLabel LB2_SearchActualDate			= B100_FrameParts.JLabelSet(610,125, 30,20,"～"			,11,2);
		JLabel LB2_SearchAdjustQty			= B100_FrameParts.JLabelSet(570,150, 30,20,"～"			,11,2);
		
		//調整日進む戻るボタン
		JButton SearchAdjustdateMinAfterBtn		= B100_FrameParts.BtnSet(200,175, 40,10,"▲",6);
		JButton SearchAdjustdateMinBeforeBtn	= B100_FrameParts.BtnSet(200,185, 40,10,"▼",6);
		JButton SearchAdjustdateMaxAfterBtn		= B100_FrameParts.BtnSet(340,175, 40,10,"▲",6);
		JButton SearchAdjustdateMaxBeforeBtn	= B100_FrameParts.BtnSet(340,185, 40,10,"▼",6);
		PN_Search.add(SearchAdjustdateMinAfterBtn);
		PN_Search.add(SearchAdjustdateMinBeforeBtn);
		PN_Search.add(SearchAdjustdateMaxAfterBtn);
		PN_Search.add(SearchAdjustdateMaxBeforeBtn);
		//調整日進む戻るボタン押下事の挙動
		SearchAdjustdateMinAfterBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				B100_DateTimeControl.AfterDateSet(TB_SearchAdjustdateMin);
			}
		});
		SearchAdjustdateMinBeforeBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				B100_DateTimeControl.BeforeDateSet(TB_SearchAdjustdateMin);
			}
		});
		SearchAdjustdateMaxAfterBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				B100_DateTimeControl.AfterDateSet(TB_SearchAdjustdateMax);
			}
		});
		SearchAdjustdateMaxBeforeBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				B100_DateTimeControl.BeforeDateSet(TB_SearchAdjustdateMax);
			}
		});
		
		//賞味期限日進む戻るボタン
		JButton SearchExpDateMinAfterBtn	= B100_FrameParts.BtnSet(570,100, 40,10,"▲",6);
		JButton SearchExpDateMinBeforeBtn	= B100_FrameParts.BtnSet(570,110, 40,10,"▼",6);
		JButton SearchExpDateMaxAfterBtn	= B100_FrameParts.BtnSet(710,100, 40,10,"▲",6);
		JButton SearchExpDateMaxBeforeBtn	= B100_FrameParts.BtnSet(710,110, 40,10,"▼",6);
		PN_Search.add(SearchExpDateMinAfterBtn);
		PN_Search.add(SearchExpDateMinBeforeBtn);
		PN_Search.add(SearchExpDateMaxAfterBtn);
		PN_Search.add(SearchExpDateMaxBeforeBtn);
		
		//賞味期限日進む戻るボタン押下事の挙動
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
		//入荷実績日進む戻るボタン
		JButton SearchActualDateMinAfterBtn		= B100_FrameParts.BtnSet(570,125, 40,10,"▲",6);
		JButton SearchActualDateMinBeforeBtn	= B100_FrameParts.BtnSet(570,135, 40,10,"▼",6);
		JButton SearchActualDateMaxAfterBtn		= B100_FrameParts.BtnSet(710,125, 40,10,"▲",6);
		JButton SearchActualDateMaxBeforeBtn	= B100_FrameParts.BtnSet(710,135, 40,10,"▼",6);
		PN_Search.add(SearchActualDateMinAfterBtn);
		PN_Search.add(SearchActualDateMinBeforeBtn);
		PN_Search.add(SearchActualDateMaxAfterBtn);
		PN_Search.add(SearchActualDateMaxBeforeBtn);
		//実績日進む戻るボタン押下事の挙動
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
		
		//現在ログイン中の荷主情報選択済みにする
		TB_SearchClWh.setSelectedIndex(		B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.SearchWhList[1]		,A00000_Main.ClWh,true));			//ヘッダ担当倉庫
		TB_SearchClCd.setSelectedIndex(		B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.SearchClList[1]		,A00000_Main.ClCd,true));			//ヘッダ荷主CD
		TB_SearchClGpCD.setSelectedIndex(	B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.SearchClGpList[1]		,A00000_Main.ClGp,true));			//ヘッダ荷主グループCD
		
		TB_SearchClWh.setEnabled(false);
		TB_SearchClCd.setEnabled(false);
		TB_SearchClGpCD.setEnabled(false);
		
		PN_Search.add(LB_SearchClCd);
		PN_Search.add(LB_SearchWhCd);
		PN_Search.add(LB_SearchClGpCD);
		PN_Search.add(LB_SearchAdjustNo);
		PN_Search.add(LB_SearchAdjustReasonCd);
		PN_Search.add(LB_SearchAdjustReasonName);
		PN_Search.add(LB_SearchAdjustdate);
		PN_Search.add(LB_SearchLoc);
		PN_Search.add(LB_SearchType);
		PN_Search.add(LB_SearchItemCd);
		PN_Search.add(LB_SearchItemName);
		PN_Search.add(LB_SearchLot);
		PN_Search.add(LB_SearchExpDate);
		PN_Search.add(LB_SearchActualDate);
		PN_Search.add(LB_SearchAdjustQty);
		PN_Search.add(LB_SearchAdjustCom);
		
		PN_Search.add(TB_SearchClWh);
		PN_Search.add(TB_SearchClCd);
		PN_Search.add(TB_SearchClGpCD);
		PN_Search.add(TB_SearchAdjustNo);
		PN_Search.add(TB_SearchAdjustReasonCd);
		PN_Search.add(TB_SearchAdjustReasonName);
		PN_Search.add(TB_SearchAdjustdateMin);
		PN_Search.add(TB_SearchAdjustdateMax);
		
		PN_Search.add(TB_SearchLoc);
		PN_Search.add(TB_LocExactMatch);
		PN_Search.add(TB_SearchType);
		PN_Search.add(TB_SearchItemCd);
		PN_Search.add(TB_SearchItemName);
		PN_Search.add(TB_SearchLot);
		PN_Search.add(TB_SearchExpDateMin);
		PN_Search.add(TB_SearchExpDateMax);
		PN_Search.add(TB_SearchActualDateMin);
		PN_Search.add(TB_SearchActualDateMax);
		PN_Search.add(TB_SearchAdjustQtyMin);
		PN_Search.add(TB_SearchAdjustQtyMax);
		PN_Search.add(TB_SearchAdjustCom);
		
		PN_Search.add(LB2_SearchAdjustNo);
		PN_Search.add(LB2_SearchAdjustReasonCd);
		PN_Search.add(LB2_SearchAdjustReasonName);
		PN_Search.add(LB2_SearchAdjustdate);
		PN_Search.add(LB2_SearchItemCd);
		PN_Search.add(LB2_SearchItemName);
		PN_Search.add(LB2_SearchLot);
		PN_Search.add(LB2_SearchExpDate);
		PN_Search.add(LB2_SearchActualDate);
		PN_Search.add(LB2_SearchAdjustQty);
		PN_Search.add(LB2_SearchAdjustCom);
		
		
		//検索ボタン
		JButton SearchBtn 		= B100_FrameParts.BtnSet(		700,250,100,20,"検索",11);
		PN_Search.add(SearchBtn);
		
		//条件クリアボタン
		JButton SearchCrearBtn 	= B100_FrameParts.BtnSet(		700, 25,100,20,"条件クリア",10);
		PN_Search.add(SearchCrearBtn);
		
		Object[][] RtAdjustRt = T100_StockAdjustRt.RtAdjustRt();
		
		String[] columnNames01 = new String[RtAdjustRt.length+1];
		
		columnNames01[0] = "Fg";
		for(int i=0;i<RtAdjustRt.length;i++) {
			columnNames01[1+(int)RtAdjustRt[i][1]] = ""+RtAdjustRt[i][3];
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
		
		for(int i=0;i<RtAdjustRt.length;i++) {
			if("int".equals((String)RtAdjustRt[i][2])||"float".equals((String)RtAdjustRt[i][2])) {
				column = columnModel01.getColumn(1+(int)RtAdjustRt[i][1]);	column.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.rightCellRenderer());
			}else {
				column = columnModel01.getColumn(1+(int)RtAdjustRt[i][1]);	column.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.leftCellRenderer());
			}
		}
		//スクロール用設定
		JScrollPane scpn01 = B100_FrameParts.JScrollPaneSet(10,350,810,365,tb01);
		main_fm.add(scpn01);
		
		PN_Search.add(PN_SearchLabel);
		main_fm.add(PN_Search);
		
		//CSVボタン
		JButton CsvBtn = B100_FrameParts.BtnSet(		 10,740,100,20,"csv出力",11);
		main_fm.add(CsvBtn);
		//Excel出力ボタン
		JButton ExcelBtn = B100_FrameParts.BtnSet(		130,740,100,20,"Excel出力",11);
		main_fm.add(ExcelBtn);
		
		RenewFg = true;
		main_fm.setVisible(true);
		
		//検索ボタン
		SearchBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					int RowCount = MainFmTableModel.getRowCount();
					for(int i=0;i<RowCount;i++) {
						MainFmTableModel.removeRow(0);
					}
					String GetSearchClWh 				= B100_TextControl.Trim(B100_DefaultVariable.SearchWhList[1][TB_SearchClWh.getSelectedIndex()]);		//ヘッダ担当倉庫
					String GetSearchClCd 				= B100_TextControl.Trim(B100_DefaultVariable.SearchClList[1][TB_SearchClCd.getSelectedIndex()]);		//ヘッダ荷主CD
					String GetSearchClGpCD 				= B100_TextControl.Trim(B100_DefaultVariable.SearchClGpList[1][TB_SearchClGpCD.getSelectedIndex()]);	//ヘッダ荷主グループCD
					String GetSearchAdjustNo 			= B100_TextControl.Trim(TB_SearchAdjustNo.getText());													//調整番号
					String GetSearchAdjustReasonCd 		= B100_TextControl.Trim(TB_SearchAdjustReasonCd.getText());												//調整理由コード
					String GetSearchAdjustReasonName 	= B100_TextControl.Trim(TB_SearchAdjustReasonName.getText());											//調整理由名
					String GetSearchAdjustdateMin 		= B100_TextControl.TextToDate(TB_SearchAdjustdateMin.getText());										//調整日最小
					String GetSearchAdjustdateMax 		= B100_TextControl.TextToDate(TB_SearchAdjustdateMax.getText());										//調整日最大
					String GetSearchLoc 				= B100_TextControl.Trim(TB_SearchLoc.getText());														//調整元ロケ
					String GetSearchType				= B100_TextControl.Trim(B100_DefaultVariable.SearchLocType[1][TB_SearchType.getSelectedIndex()]);		//ロケタイプ
					String GetSearchItemCd 				= B100_TextControl.Trim(TB_SearchItemCd.getText());														//調整元商品CD
					String GetSearchItemName 			= B100_TextControl.Trim(TB_SearchItemName.getText());													//調整元商品名
					String GetSearchLot 				= B100_TextControl.Trim(TB_SearchLot.getText());														//調整元ロット
					String GetSearchExpDateMin 			= B100_TextControl.TextToDate(TB_SearchExpDateMin.getText());											//調整元賞味期限最小
					String GetSearchExpDateMax 			= B100_TextControl.TextToDate(TB_SearchExpDateMax.getText());											//調整元賞味期限最大
					String GetSearchActualDateMin 		= B100_TextControl.TextToDate(TB_SearchActualDateMin.getText());										//調整元入荷日最小
					String GetSearchActualDateMax 		= B100_TextControl.TextToDate(TB_SearchActualDateMax.getText());										//調整元入荷日最大
					String GetSearchAdjustQtyMin 		= B100_TextControl.num_only_String02(TB_SearchAdjustQtyMin.getText());								//調整数最小
					String GetSearchAdjustQtyMax 		= B100_TextControl.num_only_String02(TB_SearchAdjustQtyMax.getText());								//調整数最大
					String GetSearchAdjustCom 			= B100_TextControl.Trim(TB_SearchAdjustCom.getText());													//調整理由コメント
					
					ArrayList<String>  SearchClCd				= new ArrayList<String>();
					ArrayList<String>  SearchWhCd				= new ArrayList<String>();
					ArrayList<String>  SearchClGpCD				= new ArrayList<String>();
					ArrayList<String>  SearchAdjustNo			= new ArrayList<String>();
					ArrayList<String>  SearchAdjustReasonCd		= new ArrayList<String>();
					ArrayList<String>  SearchAdjustReasonName	= new ArrayList<String>();
					ArrayList<String>  SearchAdjustdateMin		= new ArrayList<String>();
					ArrayList<String>  SearchAdjustdateMax		= new ArrayList<String>();
					ArrayList<String>  SearchLoc				= new ArrayList<String>();
					ArrayList<Integer> SearchType				= new ArrayList<Integer>();
					ArrayList<String>  SearchItemCd				= new ArrayList<String>();
					ArrayList<String>  SearchItemName			= new ArrayList<String>();
					ArrayList<String>  SearchLot				= new ArrayList<String>();
					ArrayList<String>  SearchExpDateMin			= new ArrayList<String>();
					ArrayList<String>  SearchExpDateMax			= new ArrayList<String>();
					ArrayList<String>  SearchActualDateMin		= new ArrayList<String>();
					ArrayList<String>  SearchActualDateMax		= new ArrayList<String>();
					ArrayList<Integer> SearchAdjustQtyMin		= new ArrayList<Integer>();
					ArrayList<Integer> SearchAdjustQtyMax		= new ArrayList<Integer>();
					ArrayList<String>  SearchAdjustCom			= new ArrayList<String>();
					boolean LocExactMatch 	= false;
					boolean AllSearch 		= false;
					
					if(!"".equals(GetSearchClWh				)){SearchWhCd.add(GetSearchClWh);}
					if(!"".equals(GetSearchClCd				)){SearchClCd.add(GetSearchClCd);}
					if(!"".equals(GetSearchClGpCD			)){SearchClGpCD.add(GetSearchClGpCD);}
					if(!"".equals(GetSearchAdjustNo			)){SearchAdjustNo.add(GetSearchAdjustNo);}
					if(!"".equals(GetSearchAdjustReasonCd	)){SearchAdjustReasonCd.add(GetSearchAdjustReasonCd);}
					if(!"".equals(GetSearchAdjustReasonName	)){SearchAdjustReasonName.add(GetSearchAdjustReasonName);}
					if(!"".equals(GetSearchAdjustdateMin	)){SearchAdjustdateMin.add(GetSearchAdjustdateMin);}
					if(!"".equals(GetSearchAdjustdateMax	)){SearchAdjustdateMax.add(GetSearchAdjustdateMax);}
					if(!"".equals(GetSearchLoc				)){SearchLoc.add(GetSearchLoc);}
					if(!"".equals(GetSearchType				)){SearchType.add(B100_TextControl.TextToInt(GetSearchType));}
					if(!"".equals(GetSearchItemCd			)){SearchItemCd.add(GetSearchItemCd);}
					if(!"".equals(GetSearchItemName			)){SearchItemName.add(GetSearchItemName);}
					if(!"".equals(GetSearchLot				)){SearchLot.add(GetSearchLot);}
					if(!"".equals(GetSearchExpDateMin		)){SearchExpDateMin.add(GetSearchExpDateMin);}
					if(!"".equals(GetSearchExpDateMax		)){SearchExpDateMax.add(GetSearchExpDateMax);}
					if(!"".equals(GetSearchActualDateMin	)){SearchActualDateMin.add(GetSearchActualDateMin);}
					if(!"".equals(GetSearchActualDateMax	)){SearchActualDateMax.add(GetSearchActualDateMax);}
					if(!"".equals(GetSearchAdjustQtyMin		)){SearchAdjustQtyMin.add(B100_TextControl.TextToInt(GetSearchAdjustQtyMin));}
					if(!"".equals(GetSearchAdjustQtyMax		)){SearchAdjustQtyMax.add(B100_TextControl.TextToInt(GetSearchAdjustQtyMax));}
					if(!"".equals(GetSearchAdjustCom		)){SearchAdjustCom.add(GetSearchAdjustCom);}
					
					if(0==TB_LocExactMatch.getSelectedIndex()) {	//ロケーション完全一致
						LocExactMatch = false;
					}else {
						LocExactMatch = true;
					}
					
					Object[][] AdjustRt = T100_StockAdjustRt.AdjustRt(
							SearchClCd,					//荷主コード
							SearchWhCd,					//倉庫コード
							SearchClGpCD,				//荷主グループCD
							SearchAdjustNo,				//調整番号
							SearchAdjustReasonCd,		//調整理由コード
							SearchAdjustReasonName,		//調整理由名
							SearchAdjustdateMin,		//調整日最小
							SearchAdjustdateMax,		//調整日最大
							SearchLoc,					//調整元ロケ
							SearchType,					//ロケタイプ
							SearchItemCd,				//調整元商品CD
							SearchItemName,				//調整元商品名
							SearchLot,	 				//調整元ロット
							SearchExpDateMin,			//調整元賞味期限最小
							SearchExpDateMax,			//調整元賞味期限最大
							SearchActualDateMin,		//調整元入荷日最小
							SearchActualDateMax,		//調整元入荷日最大
							SearchAdjustQtyMin,			//調整数最小
							SearchAdjustQtyMax,			//調整数最大
							SearchAdjustCom,			//調整理由コメント
							LocExactMatch,				//ロケーション完全一致
							AllSearch);
					
					for(int i=0;i<AdjustRt.length;i++) {
						Object[] SetOb = new Object[AdjustRt[i].length+1];
						SetOb[0] = false;
						for(int i01=0;i01<AdjustRt[i].length;i01++) {
							SetOb[i01+1] = ""+AdjustRt[i][i01];
						}
						MainFmTableModel.addRow(SetOb);
					}
					if(0<AdjustRt.length) {
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
					int RowCount = MainFmTableModel.getRowCount();
					for(int i=0;i<RowCount;i++) {
						MainFmTableModel.removeRow(0);
					}
					TB_SearchClWh.setSelectedIndex(		B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.SearchWhList[1]		,A00000_Main.ClWh,true));			//ヘッダ担当倉庫
					TB_SearchClCd.setSelectedIndex(		B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.SearchClList[1]		,A00000_Main.ClCd,true));			//ヘッダ荷主CD
					TB_SearchClGpCD.setSelectedIndex(	B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.SearchClGpList[1]		,A00000_Main.ClGp,true));			//ヘッダ荷主グループCD
					TB_SearchType.setSelectedIndex(0);		//ロケタイプ
					TB_LocExactMatch.setSelectedIndex(0);	//ロケーション完全一致
					
					TB_SearchAdjustNo.setText("");			//調整番号
					TB_SearchAdjustReasonCd.setText("");	//調整理由コード
					TB_SearchAdjustReasonName.setText("");	//調整理由名
					TB_SearchAdjustdateMin.setText("");		//調整日最小
					TB_SearchAdjustdateMax.setText("");		//調整日最大
					TB_SearchLoc.setText("");				//調整元ロケ
					TB_SearchItemCd.setText("");			//調整元商品CD
					TB_SearchItemName.setText("");			//調整元商品名
					TB_SearchLot.setText("");				//調整元ロット
					TB_SearchExpDateMin.setText("");		//調整元賞味期限最小
					TB_SearchExpDateMax.setText("");		//調整元賞味期限最大
					TB_SearchActualDateMin.setText("");		//調整元入荷日最小
					TB_SearchActualDateMax.setText("");		//調整元入荷日最大
					TB_SearchAdjustQtyMin.setText("");		//調整数最小
					TB_SearchAdjustQtyMax.setText("");		//調整数最大
					TB_SearchAdjustCom.setText("");			//調整理由コメント
					RenewFg = true;
				}
			}
		});
		
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
		
		//CSVボタン押下時の挙動
		CsvBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					B100_TableControl.TableOutPutCsv("出力先選択","在庫調整検索結果",tb01);
					RenewFg = true;
				}
			}
		});
		
		//エクセル出力ボタン押下時の挙動
		ExcelBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					B100_TableControl.TableOutPutExcel("出力先選択","在庫調整検索結果",tb01);
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