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

public class WT100_WTEST{
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

		final JFrame main_fm = B100_FrameParts.FrameCreate(x,y,1250,920,"Corgi00在庫移動検索　WT100_StockMove_00_Search","ZK");
		JLabel userinfo = B100_FrameParts.UserInfo();
		JButton exit_btn = B100_FrameParts.ExitBtn();

		main_fm.add(userinfo);
		main_fm.add(exit_btn);

		//検索条件パネル
		JPanel PN_Search = B100_FrameParts.JPanelSet(10,40,1210,470,"White");
		JLabel PN_SearchLabel = B100_FrameParts.JLabelSet(0,0,100,20,"検索条件",11,0);
		String[] LocExactMatchList = {"で始まる","と一致"};

		//基本情報
		JLabel LB_SearchWhCd			= B100_FrameParts.JLabelSet(  0, 25,120,20,"倉庫コード:"			,11,1);
		JLabel LB_SearchClCd			= B100_FrameParts.JLabelSet(  0, 50,120,20,"荷主コード:"			,11,1);
		JLabel LB_SearchCLName			= B100_FrameParts.JLabelSet(  0, 75,120,20,"荷主名:"				,11,1);
		JLabel LB_SearchClWHName		= B100_FrameParts.JLabelSet(  0,100,120,20,"担当倉庫名:"			,11,1);
		JLabel LB_SearchMoveNo			= B100_FrameParts.JLabelSet(  0,125,120,20,"移動番号:"				,11,1);
		JLabel LB_SearchFromLoc			= B100_FrameParts.JLabelSet(  0,150,120,20,"移動元ロケ:"			,11,1);
		JLabel LB_SearchFromLocName		= B100_FrameParts.JLabelSet(  0,175,120,20,"移動元ロケ名:"			,11,1);
		JLabel LB_SearchToLoc			= B100_FrameParts.JLabelSet(  0,200,120,20,"移動先ロケ:"			,11,1);
		JLabel LB_SearchToLocName		= B100_FrameParts.JLabelSet(  0,225,120,20,"移動先ロケ名:"			,11,1);
		JLabel LB_SearchMoveCom			= B100_FrameParts.JLabelSet(  0,250,120,20,"移動コメント:"			,11,1);
		JLabel LB_SearchEntryUser		= B100_FrameParts.JLabelSet(  0,275,120,20,"登録者:"				,11,1);
		JLabel LB_SearchUpdateUser		= B100_FrameParts.JLabelSet(  0,300,120,20,"更新者:"				,11,1);
		JLabel LB_SearchEntryDate		= B100_FrameParts.JLabelSet(  0,325,120,20,"登録日時:"				,11,1);
		JLabel LB_SearchUpdateDate		= B100_FrameParts.JLabelSet(  0,350,120,20,"更新日時:"				,11,1);

		final JComboBox TB_SearchWhCd		= B100_FrameParts.JComboBoxSet(120, 25,180,20,B100_DefaultVariable.SearchWhList[0],11);
		final JComboBox TB_SearchClCd		= B100_FrameParts.JComboBoxSet(120, 50,180,20,B100_DefaultVariable.SearchClList[0],11);
		final JTextField TB_SearchCLName	= B100_FrameParts.JTextFieldSet(120, 75,100,20,"",11,0);
		final JTextField TB_SearchClWHName	= B100_FrameParts.JTextFieldSet(120,100,100,20,"",11,0);
		final JTextField TB_SearchMoveNo	= B100_FrameParts.JTextFieldSet(120,125,100,20,"",11,0);
		final JTextField TB_SearchFromLoc	= B100_FrameParts.JTextFieldSet(120,150,100,20,"",11,0);
		final JComboBox TB_FromLocExactMatch	= B100_FrameParts.JComboBoxSet(220,150,80,20,LocExactMatchList,11);
		final JTextField TB_SearchFromLocName	= B100_FrameParts.JTextFieldSet(120,175,100,20,"",11,0);
		final JTextField TB_SearchToLoc		= B100_FrameParts.JTextFieldSet(120,200,100,20,"",11,0);
		final JComboBox TB_ToLocExactMatch	= B100_FrameParts.JComboBoxSet(220,200,80,20,LocExactMatchList,11);
		final JTextField TB_SearchToLocName	= B100_FrameParts.JTextFieldSet(120,225,100,20,"",11,0);
		final JTextField TB_SearchMoveCom	= B100_FrameParts.JTextFieldSet(120,250,100,20,"",11,0);
		final JTextField TB_SearchEntryUser	= B100_FrameParts.JTextFieldSet(120,275,100,20,"",11,0);
		final JTextField TB_SearchUpdateUser	= B100_FrameParts.JTextFieldSet(120,300,100,20,"",11,0);
		final JTextField TB_SearchEntryDateMin	= B100_FrameParts.JTextFieldSet(120,325,125,20,"",11,0);
		final JTextField TB_SearchEntryDateMax	= B100_FrameParts.JTextFieldSet(265,325,125,20,"",11,0);
		final JTextField TB_SearchUpdateDateMin	= B100_FrameParts.JTextFieldSet(120,350,125,20,"",11,0);
		final JTextField TB_SearchUpdateDateMax	= B100_FrameParts.JTextFieldSet(265,350,125,20,"",11,0);

		JLabel LB2_SearchCLName			= B100_FrameParts.JLabelSet(220, 75,70,20,"を含む",11,0);
		JLabel LB2_SearchClWHName		= B100_FrameParts.JLabelSet(220,100,70,20,"を含む",11,0);
		JLabel LB2_SearchMoveNo			= B100_FrameParts.JLabelSet(220,125,70,20,"と一致",11,0);
		JLabel LB2_SearchFromLocName	= B100_FrameParts.JLabelSet(220,175,70,20,"を含む",11,0);
		JLabel LB2_SearchToLocName		= B100_FrameParts.JLabelSet(220,225,70,20,"を含む",11,0);
		JLabel LB2_SearchMoveCom		= B100_FrameParts.JLabelSet(220,250,70,20,"を含む",11,0);
		JLabel LB2_SearchEntryUser		= B100_FrameParts.JLabelSet(220,275,70,20,"を含む",11,0);
		JLabel LB2_SearchUpdateUser		= B100_FrameParts.JLabelSet(220,300,70,20,"を含む",11,0);
		JLabel LB2_SearchEntryDate		= B100_FrameParts.JLabelSet(245,325,20,20,"～",11,2);
		JLabel LB2_SearchUpdateDate		= B100_FrameParts.JLabelSet(245,350,20,20,"～",11,2);

		//商品・移動数
		JLabel LB_SearchItemCd			= B100_FrameParts.JLabelSet(400, 25,135,20,"商品CD:"				,11,1);
		JLabel LB_SearchItemName		= B100_FrameParts.JLabelSet(400, 50,135,20,"商品名:"				,11,1);
		JLabel LB_SearchLot				= B100_FrameParts.JLabelSet(400, 75,135,20,"ロット:"				,11,1);
		JLabel LB_SearchExpDate			= B100_FrameParts.JLabelSet(400,100,135,20,"賞味期限:"				,11,1);
		JLabel LB_SearchActualDate		= B100_FrameParts.JLabelSet(400,125,135,20,"入荷日:"				,11,1);
		JLabel LB_SearchMoveQty			= B100_FrameParts.JLabelSet(400,150,135,20,"移動数:"				,11,1);

		final JTextField TB_SearchItemCd			= B100_FrameParts.JTextFieldSet(535, 25,100,20,"",11,0);
		final JTextField TB_SearchItemName			= B100_FrameParts.JTextFieldSet(535, 50,100,20,"",11,0);
		final JTextField TB_SearchLot				= B100_FrameParts.JTextFieldSet(535, 75,100,20,"",11,0);
		final JFormattedTextField TB_SearchExpDateMin	= B100_FrameParts.JFormattedTextFieldSet(535,100,70,20,"",11,0,"YYYY/MM/DD");
		final JFormattedTextField TB_SearchExpDateMax	= B100_FrameParts.JFormattedTextFieldSet(675,100,70,20,"",11,0,"YYYY/MM/DD");
		final JFormattedTextField TB_SearchActualDateMin	= B100_FrameParts.JFormattedTextFieldSet(535,125,70,20,"",11,0,"YYYY/MM/DD");
		final JFormattedTextField TB_SearchActualDateMax	= B100_FrameParts.JFormattedTextFieldSet(675,125,70,20,"",11,0,"YYYY/MM/DD");
		final JFormattedTextField TB_SearchMoveQtyMin		= B100_FrameParts.JFormattedTextFieldSet(535,150,70,20,"",11,1,"#,###");
		final JFormattedTextField TB_SearchMoveQtyMax		= B100_FrameParts.JFormattedTextFieldSet(635,150,70,20,"",11,1,"#,###");

		JLabel LB2_SearchItemCd		= B100_FrameParts.JLabelSet(635, 25,70,20,"と一致",11,0);
		JLabel LB2_SearchItemName	= B100_FrameParts.JLabelSet(635, 50,70,20,"を含む",11,0);
		JLabel LB2_SearchLot			= B100_FrameParts.JLabelSet(635, 75,70,20,"と一致",11,0);
		JLabel LB2_SearchExpDate		= B100_FrameParts.JLabelSet(645,100,30,20,"～",11,2);
		JLabel LB2_SearchActualDate	= B100_FrameParts.JLabelSet(645,125,30,20,"～",11,2);
		JLabel LB2_SearchMoveQty		= B100_FrameParts.JLabelSet(605,150,30,20,"～",11,2);

		//移動前数量
		JLabel LB_BeforeFromQty			= B100_FrameParts.JLabelSet(400,200,170,20,"移動前 元在庫数:"		,11,1);
		JLabel LB_BeforeFromPlanQty		= B100_FrameParts.JLabelSet(400,225,170,20,"移動前 元引当済数:"		,11,1);
		JLabel LB_BeforeFromPossibleQty	= B100_FrameParts.JLabelSet(400,250,170,20,"移動前 元出荷可能数:"	,11,1);
		JLabel LB_BeforeToQty			= B100_FrameParts.JLabelSet(400,275,170,20,"移動前 先在庫数:"		,11,1);
		JLabel LB_BeforeToPlanQty		= B100_FrameParts.JLabelSet(400,300,170,20,"移動前 先引当済数:"		,11,1);
		JLabel LB_BeforeToPossibleQty	= B100_FrameParts.JLabelSet(400,325,170,20,"移動前 先出荷可能数:"	,11,1);

		final JFormattedTextField TB_BeforeFromQtyMin			= B100_FrameParts.JFormattedTextFieldSet(570,200,70,20,"",11,1,"#,###");
		final JFormattedTextField TB_BeforeFromQtyMax			= B100_FrameParts.JFormattedTextFieldSet(670,200,70,20,"",11,1,"#,###");
		final JFormattedTextField TB_BeforeFromPlanQtyMin		= B100_FrameParts.JFormattedTextFieldSet(570,225,70,20,"",11,1,"#,###");
		final JFormattedTextField TB_BeforeFromPlanQtyMax		= B100_FrameParts.JFormattedTextFieldSet(670,225,70,20,"",11,1,"#,###");
		final JFormattedTextField TB_BeforeFromPossibleQtyMin	= B100_FrameParts.JFormattedTextFieldSet(570,250,70,20,"",11,1,"#,###");
		final JFormattedTextField TB_BeforeFromPossibleQtyMax	= B100_FrameParts.JFormattedTextFieldSet(670,250,70,20,"",11,1,"#,###");
		final JFormattedTextField TB_BeforeToQtyMin				= B100_FrameParts.JFormattedTextFieldSet(570,275,70,20,"",11,1,"#,###");
		final JFormattedTextField TB_BeforeToQtyMax				= B100_FrameParts.JFormattedTextFieldSet(670,275,70,20,"",11,1,"#,###");
		final JFormattedTextField TB_BeforeToPlanQtyMin			= B100_FrameParts.JFormattedTextFieldSet(570,300,70,20,"",11,1,"#,###");
		final JFormattedTextField TB_BeforeToPlanQtyMax			= B100_FrameParts.JFormattedTextFieldSet(670,300,70,20,"",11,1,"#,###");
		final JFormattedTextField TB_BeforeToPossibleQtyMin		= B100_FrameParts.JFormattedTextFieldSet(570,325,70,20,"",11,1,"#,###");
		final JFormattedTextField TB_BeforeToPossibleQtyMax		= B100_FrameParts.JFormattedTextFieldSet(670,325,70,20,"",11,1,"#,###");

		//移動後数量
		JLabel LB_AfterFromQty			= B100_FrameParts.JLabelSet(760, 25,170,20,"移動後 元在庫数:"		,11,1);
		JLabel LB_AfterFromPlanQty		= B100_FrameParts.JLabelSet(760, 50,170,20,"移動後 元引当済数:"		,11,1);
		JLabel LB_AfterFromPossibleQty	= B100_FrameParts.JLabelSet(760, 75,170,20,"移動後 元出荷可能数:"	,11,1);
		JLabel LB_AfterToQty			= B100_FrameParts.JLabelSet(760,100,170,20,"移動後 先在庫数:"		,11,1);
		JLabel LB_AfterToPlanQty		= B100_FrameParts.JLabelSet(760,125,170,20,"移動後 先引当済数:"		,11,1);
		JLabel LB_AfterToPossibleQty	= B100_FrameParts.JLabelSet(760,150,170,20,"移動後 先出荷可能数:"	,11,1);

		final JFormattedTextField TB_AfterFromQtyMin			= B100_FrameParts.JFormattedTextFieldSet(930, 25,70,20,"",11,1,"#,###");
		final JFormattedTextField TB_AfterFromQtyMax			= B100_FrameParts.JFormattedTextFieldSet(1030, 25,70,20,"",11,1,"#,###");
		final JFormattedTextField TB_AfterFromPlanQtyMin		= B100_FrameParts.JFormattedTextFieldSet(930, 50,70,20,"",11,1,"#,###");
		final JFormattedTextField TB_AfterFromPlanQtyMax		= B100_FrameParts.JFormattedTextFieldSet(1030, 50,70,20,"",11,1,"#,###");
		final JFormattedTextField TB_AfterFromPossibleQtyMin	= B100_FrameParts.JFormattedTextFieldSet(930, 75,70,20,"",11,1,"#,###");
		final JFormattedTextField TB_AfterFromPossibleQtyMax	= B100_FrameParts.JFormattedTextFieldSet(1030, 75,70,20,"",11,1,"#,###");
		final JFormattedTextField TB_AfterToQtyMin				= B100_FrameParts.JFormattedTextFieldSet(930,100,70,20,"",11,1,"#,###");
		final JFormattedTextField TB_AfterToQtyMax				= B100_FrameParts.JFormattedTextFieldSet(1030,100,70,20,"",11,1,"#,###");
		final JFormattedTextField TB_AfterToPlanQtyMin			= B100_FrameParts.JFormattedTextFieldSet(930,125,70,20,"",11,1,"#,###");
		final JFormattedTextField TB_AfterToPlanQtyMax			= B100_FrameParts.JFormattedTextFieldSet(1030,125,70,20,"",11,1,"#,###");
		final JFormattedTextField TB_AfterToPossibleQtyMin		= B100_FrameParts.JFormattedTextFieldSet(930,150,70,20,"",11,1,"#,###");
		final JFormattedTextField TB_AfterToPossibleQtyMax		= B100_FrameParts.JFormattedTextFieldSet(1030,150,70,20,"",11,1,"#,###");

		//「～」
		int[] QtyY = {200,225,250,275,300,325};
		for(int SetQtyY : QtyY) {
			PN_Search.add(B100_FrameParts.JLabelSet(640,SetQtyY,30,20,"～",11,2));
		}
		int[] AfterQtyY = {25,50,75,100,125,150};
		for(int SetQtyY : AfterQtyY) {
			PN_Search.add(B100_FrameParts.JLabelSet(1000,SetQtyY,30,20,"～",11,2));
		}

		//日付進む戻るボタン
		JButton SearchExpDateMinAfterBtn		= B100_FrameParts.BtnSet(605,100,40,10,"▲",6);
		JButton SearchExpDateMinBeforeBtn		= B100_FrameParts.BtnSet(605,110,40,10,"▼",6);
		JButton SearchExpDateMaxAfterBtn			= B100_FrameParts.BtnSet(745,100,40,10,"▲",6);
		JButton SearchExpDateMaxBeforeBtn		= B100_FrameParts.BtnSet(745,110,40,10,"▼",6);
		JButton SearchActualDateMinAfterBtn		= B100_FrameParts.BtnSet(605,125,40,10,"▲",6);
		JButton SearchActualDateMinBeforeBtn		= B100_FrameParts.BtnSet(605,135,40,10,"▼",6);
		JButton SearchActualDateMaxAfterBtn		= B100_FrameParts.BtnSet(745,125,40,10,"▲",6);
		JButton SearchActualDateMaxBeforeBtn		= B100_FrameParts.BtnSet(745,135,40,10,"▼",6);

		PN_Search.add(SearchExpDateMinAfterBtn);
		PN_Search.add(SearchExpDateMinBeforeBtn);
		PN_Search.add(SearchExpDateMaxAfterBtn);
		PN_Search.add(SearchExpDateMaxBeforeBtn);
		PN_Search.add(SearchActualDateMinAfterBtn);
		PN_Search.add(SearchActualDateMinBeforeBtn);
		PN_Search.add(SearchActualDateMaxAfterBtn);
		PN_Search.add(SearchActualDateMaxBeforeBtn);

		SearchExpDateMinAfterBtn.addActionListener(new AbstractAction(){public void actionPerformed(ActionEvent e){B100_DateTimeControl.AfterDateSet(TB_SearchExpDateMin);}});
		SearchExpDateMinBeforeBtn.addActionListener(new AbstractAction(){public void actionPerformed(ActionEvent e){B100_DateTimeControl.BeforeDateSet(TB_SearchExpDateMin);}});
		SearchExpDateMaxAfterBtn.addActionListener(new AbstractAction(){public void actionPerformed(ActionEvent e){B100_DateTimeControl.AfterDateSet(TB_SearchExpDateMax);}});
		SearchExpDateMaxBeforeBtn.addActionListener(new AbstractAction(){public void actionPerformed(ActionEvent e){B100_DateTimeControl.BeforeDateSet(TB_SearchExpDateMax);}});
		SearchActualDateMinAfterBtn.addActionListener(new AbstractAction(){public void actionPerformed(ActionEvent e){B100_DateTimeControl.AfterDateSet(TB_SearchActualDateMin);}});
		SearchActualDateMinBeforeBtn.addActionListener(new AbstractAction(){public void actionPerformed(ActionEvent e){B100_DateTimeControl.BeforeDateSet(TB_SearchActualDateMin);}});
		SearchActualDateMaxAfterBtn.addActionListener(new AbstractAction(){public void actionPerformed(ActionEvent e){B100_DateTimeControl.AfterDateSet(TB_SearchActualDateMax);}});
		SearchActualDateMaxBeforeBtn.addActionListener(new AbstractAction(){public void actionPerformed(ActionEvent e){B100_DateTimeControl.BeforeDateSet(TB_SearchActualDateMax);}});

		//現在ログイン中の荷主情報選択済みにする
		TB_SearchWhCd.setSelectedIndex(B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.SearchWhList[1],A00000_Main.ClWh,true));
		TB_SearchClCd.setSelectedIndex(B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.SearchClList[1],A00000_Main.ClCd,true));
		TB_SearchWhCd.setEnabled(false);
		TB_SearchClCd.setEnabled(false);

		//パネルへ追加
		PN_Search.add(PN_SearchLabel);

		JLabel[] Labels = {
			LB_SearchWhCd,LB_SearchClCd,LB_SearchCLName,LB_SearchClWHName,LB_SearchMoveNo,
			LB_SearchFromLoc,LB_SearchFromLocName,LB_SearchToLoc,LB_SearchToLocName,LB_SearchMoveCom,
			LB_SearchEntryUser,LB_SearchUpdateUser,LB_SearchEntryDate,LB_SearchUpdateDate,
			LB2_SearchCLName,LB2_SearchClWHName,LB2_SearchMoveNo,LB2_SearchFromLocName,LB2_SearchToLocName,
			LB2_SearchMoveCom,LB2_SearchEntryUser,LB2_SearchUpdateUser,LB2_SearchEntryDate,LB2_SearchUpdateDate,
			LB_SearchItemCd,LB_SearchItemName,LB_SearchLot,LB_SearchExpDate,LB_SearchActualDate,LB_SearchMoveQty,
			LB2_SearchItemCd,LB2_SearchItemName,LB2_SearchLot,LB2_SearchExpDate,LB2_SearchActualDate,LB2_SearchMoveQty,
			LB_BeforeFromQty,LB_BeforeFromPlanQty,LB_BeforeFromPossibleQty,LB_BeforeToQty,LB_BeforeToPlanQty,LB_BeforeToPossibleQty,
			LB_AfterFromQty,LB_AfterFromPlanQty,LB_AfterFromPossibleQty,LB_AfterToQty,LB_AfterToPlanQty,LB_AfterToPossibleQty
		};
		for(JLabel SetLabel : Labels) {PN_Search.add(SetLabel);}

		java.awt.Component[] Inputs = {
			TB_SearchWhCd,TB_SearchClCd,TB_SearchCLName,TB_SearchClWHName,TB_SearchMoveNo,
			TB_SearchFromLoc,TB_FromLocExactMatch,TB_SearchFromLocName,TB_SearchToLoc,TB_ToLocExactMatch,TB_SearchToLocName,
			TB_SearchMoveCom,TB_SearchEntryUser,TB_SearchUpdateUser,TB_SearchEntryDateMin,TB_SearchEntryDateMax,TB_SearchUpdateDateMin,TB_SearchUpdateDateMax,
			TB_SearchItemCd,TB_SearchItemName,TB_SearchLot,TB_SearchExpDateMin,TB_SearchExpDateMax,TB_SearchActualDateMin,TB_SearchActualDateMax,
			TB_SearchMoveQtyMin,TB_SearchMoveQtyMax,
			TB_BeforeFromQtyMin,TB_BeforeFromQtyMax,TB_BeforeFromPlanQtyMin,TB_BeforeFromPlanQtyMax,TB_BeforeFromPossibleQtyMin,TB_BeforeFromPossibleQtyMax,
			TB_BeforeToQtyMin,TB_BeforeToQtyMax,TB_BeforeToPlanQtyMin,TB_BeforeToPlanQtyMax,TB_BeforeToPossibleQtyMin,TB_BeforeToPossibleQtyMax,
			TB_AfterFromQtyMin,TB_AfterFromQtyMax,TB_AfterFromPlanQtyMin,TB_AfterFromPlanQtyMax,TB_AfterFromPossibleQtyMin,TB_AfterFromPossibleQtyMax,
			TB_AfterToQtyMin,TB_AfterToQtyMax,TB_AfterToPlanQtyMin,TB_AfterToPlanQtyMax,TB_AfterToPossibleQtyMin,TB_AfterToPossibleQtyMax
		};
		for(java.awt.Component SetInput : Inputs) {PN_Search.add(SetInput);}

		//検索ボタン
		JButton SearchBtn = B100_FrameParts.BtnSet(1090,420,100,20,"検索",11);
		PN_Search.add(SearchBtn);

		//条件クリアボタン
		JButton SearchCrearBtn = B100_FrameParts.BtnSet(1090,25,100,20,"条件クリア",10);
		PN_Search.add(SearchCrearBtn);

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

		DefaultTableColumnModel columnModel01 = (DefaultTableColumnModel)tb01.getColumnModel();
		TableColumn column = null;
		column = columnModel01.getColumn(0);
		column.setPreferredWidth(30*A00000_Main.Mul/A00000_Main.Div);

		for(int i=0;i<RtStockMoveRt.length;i++) {
			column = columnModel01.getColumn(1+(int)RtStockMoveRt[i][1]);
			column.setPreferredWidth(90*A00000_Main.Mul/A00000_Main.Div);
			if("int".equals((String)RtStockMoveRt[i][2])||"float".equals((String)RtStockMoveRt[i][2])) {
				column.setCellRenderer(B100_FrameParts.rightCellRenderer());
			}else {
				column.setCellRenderer(B100_FrameParts.leftCellRenderer());
			}
		}

		JScrollPane scpn01 = B100_FrameParts.JScrollPaneSet(10,520,1210,300,tb01);
		main_fm.add(scpn01);
		main_fm.add(PN_Search);

		JButton CsvBtn = B100_FrameParts.BtnSet(10,840,100,20,"csv出力",11);
		JButton ExcelBtn = B100_FrameParts.BtnSet(130,840,100,20,"Excel出力",11);
		main_fm.add(CsvBtn);
		main_fm.add(ExcelBtn);

		RenewFg = true;
		main_fm.setVisible(true);

		//検索ボタン
		SearchBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;

					int RowCount = MainFmTableModel.getRowCount();
					for(int i=0;i<RowCount;i++) {MainFmTableModel.removeRow(0);}

					String GetSearchWhCd			= B100_TextControl.Trim(B100_DefaultVariable.SearchWhList[1][TB_SearchWhCd.getSelectedIndex()]);
					String GetSearchClCd			= B100_TextControl.Trim(B100_DefaultVariable.SearchClList[1][TB_SearchClCd.getSelectedIndex()]);
					String GetSearchCLName		= B100_TextControl.Trim(TB_SearchCLName.getText());
					String GetSearchClWHName		= B100_TextControl.Trim(TB_SearchClWHName.getText());
					String GetSearchMoveNo		= B100_TextControl.Trim(TB_SearchMoveNo.getText());
					String GetSearchFromLoc		= B100_TextControl.Trim(TB_SearchFromLoc.getText());
					String GetSearchFromLocName	= B100_TextControl.Trim(TB_SearchFromLocName.getText());
					String GetSearchToLoc		= B100_TextControl.Trim(TB_SearchToLoc.getText());
					String GetSearchToLocName	= B100_TextControl.Trim(TB_SearchToLocName.getText());
					String GetSearchItemCd		= B100_TextControl.Trim(TB_SearchItemCd.getText());
					String GetSearchItemName		= B100_TextControl.Trim(TB_SearchItemName.getText());
					String GetSearchLot			= B100_TextControl.Trim(TB_SearchLot.getText());
					String GetSearchExpDateMin	= B100_TextControl.TextToDate(TB_SearchExpDateMin.getText());
					String GetSearchExpDateMax	= B100_TextControl.TextToDate(TB_SearchExpDateMax.getText());
					String GetSearchActualDateMin= B100_TextControl.TextToDate(TB_SearchActualDateMin.getText());
					String GetSearchActualDateMax= B100_TextControl.TextToDate(TB_SearchActualDateMax.getText());
					String GetSearchMoveCom		= B100_TextControl.Trim(TB_SearchMoveCom.getText());
					String GetSearchEntryDateMin	= B100_TextControl.Trim(TB_SearchEntryDateMin.getText());
					String GetSearchEntryDateMax	= B100_TextControl.Trim(TB_SearchEntryDateMax.getText());
					String GetSearchUpdateDateMin= B100_TextControl.Trim(TB_SearchUpdateDateMin.getText());
					String GetSearchUpdateDateMax= B100_TextControl.Trim(TB_SearchUpdateDateMax.getText());
					String GetSearchEntryUser	= B100_TextControl.Trim(TB_SearchEntryUser.getText());
					String GetSearchUpdateUser	= B100_TextControl.Trim(TB_SearchUpdateUser.getText());

					String GetSearchBeforeFromQtyMin			= B100_TextControl.num_only_String02(TB_BeforeFromQtyMin.getText());
					String GetSearchBeforeFromQtyMax			= B100_TextControl.num_only_String02(TB_BeforeFromQtyMax.getText());
					String GetSearchBeforeFromPlanQtyMin		= B100_TextControl.num_only_String02(TB_BeforeFromPlanQtyMin.getText());
					String GetSearchBeforeFromPlanQtyMax		= B100_TextControl.num_only_String02(TB_BeforeFromPlanQtyMax.getText());
					String GetSearchBeforeFromPossibleQtyMin	= B100_TextControl.num_only_String02(TB_BeforeFromPossibleQtyMin.getText());
					String GetSearchBeforeFromPossibleQtyMax	= B100_TextControl.num_only_String02(TB_BeforeFromPossibleQtyMax.getText());
					String GetSearchBeforeToQtyMin				= B100_TextControl.num_only_String02(TB_BeforeToQtyMin.getText());
					String GetSearchBeforeToQtyMax				= B100_TextControl.num_only_String02(TB_BeforeToQtyMax.getText());
					String GetSearchBeforeToPlanQtyMin			= B100_TextControl.num_only_String02(TB_BeforeToPlanQtyMin.getText());
					String GetSearchBeforeToPlanQtyMax			= B100_TextControl.num_only_String02(TB_BeforeToPlanQtyMax.getText());
					String GetSearchBeforeToPossibleQtyMin		= B100_TextControl.num_only_String02(TB_BeforeToPossibleQtyMin.getText());
					String GetSearchBeforeToPossibleQtyMax		= B100_TextControl.num_only_String02(TB_BeforeToPossibleQtyMax.getText());
					String GetSearchMoveQtyMin					= B100_TextControl.num_only_String02(TB_SearchMoveQtyMin.getText());
					String GetSearchMoveQtyMax					= B100_TextControl.num_only_String02(TB_SearchMoveQtyMax.getText());
					String GetSearchAfterFromQtyMin			= B100_TextControl.num_only_String02(TB_AfterFromQtyMin.getText());
					String GetSearchAfterFromQtyMax			= B100_TextControl.num_only_String02(TB_AfterFromQtyMax.getText());
					String GetSearchAfterFromPlanQtyMin		= B100_TextControl.num_only_String02(TB_AfterFromPlanQtyMin.getText());
					String GetSearchAfterFromPlanQtyMax		= B100_TextControl.num_only_String02(TB_AfterFromPlanQtyMax.getText());
					String GetSearchAfterFromPossibleQtyMin	= B100_TextControl.num_only_String02(TB_AfterFromPossibleQtyMin.getText());
					String GetSearchAfterFromPossibleQtyMax	= B100_TextControl.num_only_String02(TB_AfterFromPossibleQtyMax.getText());
					String GetSearchAfterToQtyMin				= B100_TextControl.num_only_String02(TB_AfterToQtyMin.getText());
					String GetSearchAfterToQtyMax				= B100_TextControl.num_only_String02(TB_AfterToQtyMax.getText());
					String GetSearchAfterToPlanQtyMin			= B100_TextControl.num_only_String02(TB_AfterToPlanQtyMin.getText());
					String GetSearchAfterToPlanQtyMax			= B100_TextControl.num_only_String02(TB_AfterToPlanQtyMax.getText());
					String GetSearchAfterToPossibleQtyMin		= B100_TextControl.num_only_String02(TB_AfterToPossibleQtyMin.getText());
					String GetSearchAfterToPossibleQtyMax		= B100_TextControl.num_only_String02(TB_AfterToPossibleQtyMax.getText());

					ArrayList<String> SearchClCd						= new ArrayList<String>();
					ArrayList<String> SearchCLName						= new ArrayList<String>();
					ArrayList<String> SearchWhCd						= new ArrayList<String>();
					ArrayList<String> SearchClWHName					= new ArrayList<String>();
					ArrayList<String> SearchMoveNo						= new ArrayList<String>();
					ArrayList<String> SearchFromLoc					= new ArrayList<String>();
					ArrayList<String> SearchFromLocName				= new ArrayList<String>();
					ArrayList<String> SearchToLoc						= new ArrayList<String>();
					ArrayList<String> SearchToLocName					= new ArrayList<String>();
					ArrayList<String> SearchItemCd						= new ArrayList<String>();
					ArrayList<String> SearchItemName					= new ArrayList<String>();
					ArrayList<String> SearchLot						= new ArrayList<String>();
					ArrayList<String> SearchExpDateMin					= new ArrayList<String>();
					ArrayList<String> SearchExpDateMax					= new ArrayList<String>();
					ArrayList<String> SearchActualDateMin				= new ArrayList<String>();
					ArrayList<String> SearchActualDateMax				= new ArrayList<String>();
					ArrayList<Integer> SearchBeforeFromQtyMin			= new ArrayList<Integer>();
					ArrayList<Integer> SearchBeforeFromPlanQtyMin		= new ArrayList<Integer>();
					ArrayList<Integer> SearchBeforeFromPossibleQtyMin	= new ArrayList<Integer>();
					ArrayList<Integer> SearchBeforeToQtyMin			= new ArrayList<Integer>();
					ArrayList<Integer> SearchBeforeToPlanQtyMin		= new ArrayList<Integer>();
					ArrayList<Integer> SearchBeforeToPossibleQtyMin	= new ArrayList<Integer>();
					ArrayList<Integer> SearchMoveQtyMin				= new ArrayList<Integer>();
					ArrayList<Integer> SearchAfterFromQtyMin			= new ArrayList<Integer>();
					ArrayList<Integer> SearchAfterFromPlanQtyMin		= new ArrayList<Integer>();
					ArrayList<Integer> SearchAfterFromPossibleQtyMin	= new ArrayList<Integer>();
					ArrayList<Integer> SearchAfterToQtyMin			= new ArrayList<Integer>();
					ArrayList<Integer> SearchAfterToPlanQtyMin		= new ArrayList<Integer>();
					ArrayList<Integer> SearchAfterToPossibleQtyMin	= new ArrayList<Integer>();
					ArrayList<Integer> SearchBeforeFromQtyMax			= new ArrayList<Integer>();
					ArrayList<Integer> SearchBeforeFromPlanQtyMax		= new ArrayList<Integer>();
					ArrayList<Integer> SearchBeforeFromPossibleQtyMax	= new ArrayList<Integer>();
					ArrayList<Integer> SearchBeforeToQtyMax			= new ArrayList<Integer>();
					ArrayList<Integer> SearchBeforeToPlanQtyMax		= new ArrayList<Integer>();
					ArrayList<Integer> SearchBeforeToPossibleQtyMax	= new ArrayList<Integer>();
					ArrayList<Integer> SearchMoveQtyMax				= new ArrayList<Integer>();
					ArrayList<Integer> SearchAfterFromQtyMax			= new ArrayList<Integer>();
					ArrayList<Integer> SearchAfterFromPlanQtyMax		= new ArrayList<Integer>();
					ArrayList<Integer> SearchAfterFromPossibleQtyMax	= new ArrayList<Integer>();
					ArrayList<Integer> SearchAfterToQtyMax			= new ArrayList<Integer>();
					ArrayList<Integer> SearchAfterToPlanQtyMax		= new ArrayList<Integer>();
					ArrayList<Integer> SearchAfterToPossibleQtyMax	= new ArrayList<Integer>();
					ArrayList<String> SearchMoveCom					= new ArrayList<String>();
					ArrayList<String> SearchEntryDateMin				= new ArrayList<String>();
					ArrayList<String> SearchUpdateDateMin				= new ArrayList<String>();
					ArrayList<String> SearchEntryDateMax				= new ArrayList<String>();
					ArrayList<String> SearchUpdateDateMax				= new ArrayList<String>();
					ArrayList<String> SearchEntryUser					= new ArrayList<String>();
					ArrayList<String> SearchUpdateUser				= new ArrayList<String>();

					if(!"".equals(GetSearchClCd))				{SearchClCd.add(GetSearchClCd);}
					if(!"".equals(GetSearchCLName))			{SearchCLName.add(GetSearchCLName);}
					if(!"".equals(GetSearchWhCd))				{SearchWhCd.add(GetSearchWhCd);}
					if(!"".equals(GetSearchClWHName))			{SearchClWHName.add(GetSearchClWHName);}
					if(!"".equals(GetSearchMoveNo))			{SearchMoveNo.add(GetSearchMoveNo);}
					if(!"".equals(GetSearchFromLoc))			{SearchFromLoc.add(GetSearchFromLoc);}
					if(!"".equals(GetSearchFromLocName))		{SearchFromLocName.add(GetSearchFromLocName);}
					if(!"".equals(GetSearchToLoc))				{SearchToLoc.add(GetSearchToLoc);}
					if(!"".equals(GetSearchToLocName))			{SearchToLocName.add(GetSearchToLocName);}
					if(!"".equals(GetSearchItemCd))			{SearchItemCd.add(GetSearchItemCd);}
					if(!"".equals(GetSearchItemName))			{SearchItemName.add(GetSearchItemName);}
					if(!"".equals(GetSearchLot))				{SearchLot.add(GetSearchLot);}
					if(!"".equals(GetSearchExpDateMin))		{SearchExpDateMin.add(GetSearchExpDateMin);}
					if(!"".equals(GetSearchExpDateMax))		{SearchExpDateMax.add(GetSearchExpDateMax);}
					if(!"".equals(GetSearchActualDateMin))		{SearchActualDateMin.add(GetSearchActualDateMin);}
					if(!"".equals(GetSearchActualDateMax))		{SearchActualDateMax.add(GetSearchActualDateMax);}
					if(!"".equals(GetSearchMoveCom))			{SearchMoveCom.add(GetSearchMoveCom);}
					if(!"".equals(GetSearchEntryDateMin))		{SearchEntryDateMin.add(GetSearchEntryDateMin);}
					if(!"".equals(GetSearchUpdateDateMin))		{SearchUpdateDateMin.add(GetSearchUpdateDateMin);}
					if(!"".equals(GetSearchEntryDateMax))		{SearchEntryDateMax.add(GetSearchEntryDateMax);}
					if(!"".equals(GetSearchUpdateDateMax))		{SearchUpdateDateMax.add(GetSearchUpdateDateMax);}
					if(!"".equals(GetSearchEntryUser))			{SearchEntryUser.add(GetSearchEntryUser);}
					if(!"".equals(GetSearchUpdateUser))		{SearchUpdateUser.add(GetSearchUpdateUser);}

					if(!"".equals(GetSearchBeforeFromQtyMin))			{SearchBeforeFromQtyMin.add(B100_TextControl.TextToInt(GetSearchBeforeFromQtyMin));}
					if(!"".equals(GetSearchBeforeFromQtyMax))			{SearchBeforeFromQtyMax.add(B100_TextControl.TextToInt(GetSearchBeforeFromQtyMax));}
					if(!"".equals(GetSearchBeforeFromPlanQtyMin))		{SearchBeforeFromPlanQtyMin.add(B100_TextControl.TextToInt(GetSearchBeforeFromPlanQtyMin));}
					if(!"".equals(GetSearchBeforeFromPlanQtyMax))		{SearchBeforeFromPlanQtyMax.add(B100_TextControl.TextToInt(GetSearchBeforeFromPlanQtyMax));}
					if(!"".equals(GetSearchBeforeFromPossibleQtyMin))	{SearchBeforeFromPossibleQtyMin.add(B100_TextControl.TextToInt(GetSearchBeforeFromPossibleQtyMin));}
					if(!"".equals(GetSearchBeforeFromPossibleQtyMax))	{SearchBeforeFromPossibleQtyMax.add(B100_TextControl.TextToInt(GetSearchBeforeFromPossibleQtyMax));}
					if(!"".equals(GetSearchBeforeToQtyMin))				{SearchBeforeToQtyMin.add(B100_TextControl.TextToInt(GetSearchBeforeToQtyMin));}
					if(!"".equals(GetSearchBeforeToQtyMax))				{SearchBeforeToQtyMax.add(B100_TextControl.TextToInt(GetSearchBeforeToQtyMax));}
					if(!"".equals(GetSearchBeforeToPlanQtyMin))			{SearchBeforeToPlanQtyMin.add(B100_TextControl.TextToInt(GetSearchBeforeToPlanQtyMin));}
					if(!"".equals(GetSearchBeforeToPlanQtyMax))			{SearchBeforeToPlanQtyMax.add(B100_TextControl.TextToInt(GetSearchBeforeToPlanQtyMax));}
					if(!"".equals(GetSearchBeforeToPossibleQtyMin))		{SearchBeforeToPossibleQtyMin.add(B100_TextControl.TextToInt(GetSearchBeforeToPossibleQtyMin));}
					if(!"".equals(GetSearchBeforeToPossibleQtyMax))		{SearchBeforeToPossibleQtyMax.add(B100_TextControl.TextToInt(GetSearchBeforeToPossibleQtyMax));}
					if(!"".equals(GetSearchMoveQtyMin))					{SearchMoveQtyMin.add(B100_TextControl.TextToInt(GetSearchMoveQtyMin));}
					if(!"".equals(GetSearchMoveQtyMax))					{SearchMoveQtyMax.add(B100_TextControl.TextToInt(GetSearchMoveQtyMax));}
					if(!"".equals(GetSearchAfterFromQtyMin))			{SearchAfterFromQtyMin.add(B100_TextControl.TextToInt(GetSearchAfterFromQtyMin));}
					if(!"".equals(GetSearchAfterFromQtyMax))			{SearchAfterFromQtyMax.add(B100_TextControl.TextToInt(GetSearchAfterFromQtyMax));}
					if(!"".equals(GetSearchAfterFromPlanQtyMin))		{SearchAfterFromPlanQtyMin.add(B100_TextControl.TextToInt(GetSearchAfterFromPlanQtyMin));}
					if(!"".equals(GetSearchAfterFromPlanQtyMax))		{SearchAfterFromPlanQtyMax.add(B100_TextControl.TextToInt(GetSearchAfterFromPlanQtyMax));}
					if(!"".equals(GetSearchAfterFromPossibleQtyMin))	{SearchAfterFromPossibleQtyMin.add(B100_TextControl.TextToInt(GetSearchAfterFromPossibleQtyMin));}
					if(!"".equals(GetSearchAfterFromPossibleQtyMax))	{SearchAfterFromPossibleQtyMax.add(B100_TextControl.TextToInt(GetSearchAfterFromPossibleQtyMax));}
					if(!"".equals(GetSearchAfterToQtyMin))				{SearchAfterToQtyMin.add(B100_TextControl.TextToInt(GetSearchAfterToQtyMin));}
					if(!"".equals(GetSearchAfterToQtyMax))				{SearchAfterToQtyMax.add(B100_TextControl.TextToInt(GetSearchAfterToQtyMax));}
					if(!"".equals(GetSearchAfterToPlanQtyMin))			{SearchAfterToPlanQtyMin.add(B100_TextControl.TextToInt(GetSearchAfterToPlanQtyMin));}
					if(!"".equals(GetSearchAfterToPlanQtyMax))			{SearchAfterToPlanQtyMax.add(B100_TextControl.TextToInt(GetSearchAfterToPlanQtyMax));}
					if(!"".equals(GetSearchAfterToPossibleQtyMin))		{SearchAfterToPossibleQtyMin.add(B100_TextControl.TextToInt(GetSearchAfterToPossibleQtyMin));}
					if(!"".equals(GetSearchAfterToPossibleQtyMax))		{SearchAfterToPossibleQtyMax.add(B100_TextControl.TextToInt(GetSearchAfterToPossibleQtyMax));}

					boolean FromLocExactMatch = (0!=TB_FromLocExactMatch.getSelectedIndex());
					boolean ToLocExactMatch = (0!=TB_ToLocExactMatch.getSelectedIndex());
					boolean AllSearch = false;

					Object[][] StockMoveRt = T100_StockMoveRt.StockMoveRt(
							SearchClCd,
							SearchCLName,
							SearchWhCd,
							SearchClWHName,
							SearchMoveNo,
							SearchFromLoc,
							SearchFromLocName,
							SearchToLoc,
							SearchToLocName,
							SearchItemCd,
							SearchItemName,
							SearchLot,
							SearchExpDateMin,
							SearchExpDateMax,
							SearchActualDateMin,
							SearchActualDateMax,
							SearchBeforeFromQtyMin,
							SearchBeforeFromPlanQtyMin,
							SearchBeforeFromPossibleQtyMin,
							SearchBeforeToQtyMin,
							SearchBeforeToPlanQtyMin,
							SearchBeforeToPossibleQtyMin,
							SearchMoveQtyMin,
							SearchAfterFromQtyMin,
							SearchAfterFromPlanQtyMin,
							SearchAfterFromPossibleQtyMin,
							SearchAfterToQtyMin,
							SearchAfterToPlanQtyMin,
							SearchAfterToPossibleQtyMin,
							SearchBeforeFromQtyMax,
							SearchBeforeFromPlanQtyMax,
							SearchBeforeFromPossibleQtyMax,
							SearchBeforeToQtyMax,
							SearchBeforeToPlanQtyMax,
							SearchBeforeToPossibleQtyMax,
							SearchMoveQtyMax,
							SearchAfterFromQtyMax,
							SearchAfterFromPlanQtyMax,
							SearchAfterFromPossibleQtyMax,
							SearchAfterToQtyMax,
							SearchAfterToPlanQtyMax,
							SearchAfterToPossibleQtyMax,
							SearchMoveCom,
							SearchEntryDateMin,
							SearchUpdateDateMin,
							SearchEntryDateMax,
							SearchUpdateDateMax,
							SearchEntryUser,
							SearchUpdateUser,
							FromLocExactMatch,
							ToLocExactMatch,
							AllSearch);

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

		//条件クリアボタン
		SearchCrearBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;

					int RowCount = MainFmTableModel.getRowCount();
					for(int i=0;i<RowCount;i++) {MainFmTableModel.removeRow(0);}

					TB_SearchWhCd.setSelectedIndex(B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.SearchWhList[1],A00000_Main.ClWh,true));
					TB_SearchClCd.setSelectedIndex(B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.SearchClList[1],A00000_Main.ClCd,true));
					TB_FromLocExactMatch.setSelectedIndex(0);
					TB_ToLocExactMatch.setSelectedIndex(0);

					java.awt.Component[] ClearInputs = {
						TB_SearchCLName,TB_SearchClWHName,TB_SearchMoveNo,TB_SearchFromLoc,TB_SearchFromLocName,TB_SearchToLoc,TB_SearchToLocName,
						TB_SearchMoveCom,TB_SearchEntryUser,TB_SearchUpdateUser,TB_SearchEntryDateMin,TB_SearchEntryDateMax,TB_SearchUpdateDateMin,TB_SearchUpdateDateMax,
						TB_SearchItemCd,TB_SearchItemName,TB_SearchLot,TB_SearchExpDateMin,TB_SearchExpDateMax,TB_SearchActualDateMin,TB_SearchActualDateMax,
						TB_SearchMoveQtyMin,TB_SearchMoveQtyMax,
						TB_BeforeFromQtyMin,TB_BeforeFromQtyMax,TB_BeforeFromPlanQtyMin,TB_BeforeFromPlanQtyMax,TB_BeforeFromPossibleQtyMin,TB_BeforeFromPossibleQtyMax,
						TB_BeforeToQtyMin,TB_BeforeToQtyMax,TB_BeforeToPlanQtyMin,TB_BeforeToPlanQtyMax,TB_BeforeToPossibleQtyMin,TB_BeforeToPossibleQtyMax,
						TB_AfterFromQtyMin,TB_AfterFromQtyMax,TB_AfterFromPlanQtyMin,TB_AfterFromPlanQtyMax,TB_AfterFromPossibleQtyMin,TB_AfterFromPossibleQtyMax,
						TB_AfterToQtyMin,TB_AfterToQtyMax,TB_AfterToPlanQtyMin,TB_AfterToPlanQtyMax,TB_AfterToPossibleQtyMin,TB_AfterToPossibleQtyMax
					};
					for(java.awt.Component SetInput : ClearInputs) {
						if(SetInput instanceof JTextField) {
							((JTextField)SetInput).setText("");
						}
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
					int row_count = tb01.getRowCount();
					Boolean setBL=Boolean.valueOf(false);
					for(int i=0;i<row_count;i++){
						if(i!=e.getFirstRow()){
							MainFmTableModel.setValueAt(setBL, i, 0);
						}
					}
					RenewFg = true;
				}
			}
		});

		//CSVボタン
		CsvBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					B100_TableControl.TableOutPutCsv("出力先選択","在庫移動検索結果",tb01);
					RenewFg = true;
				}
			}
		});

		//Excel出力ボタン
		ExcelBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					B100_TableControl.TableOutPutExcel("出力先選択","在庫移動検索結果",tb01);
					RenewFg = true;
				}
			}
		});

		//EXITボタン
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