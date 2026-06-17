import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

public class WM100_ItemRecomendLocMst_00_Search{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	public static void ItemRecomendLocMstSearch(int x,int y) {
		A00000_Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		
		final JFrame main_fm = B100_FrameParts.FrameCreate(x,y,900,750,"Corgi00推奨ロケマスタ検索","");
		JLabel userinfo = B100_FrameParts.UserInfo();
		JButton exit_btn = B100_FrameParts.ExitBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		
		//検索条件パネル
		JPanel PN_Search = B100_FrameParts.JPanelSet(10,40,880,250,"White");
		JLabel PN_SearchLabel = B100_FrameParts.JLabelSet(10,0,150,20,"検索条件",11,0);
		
		PN_Search.add(PN_SearchLabel);
		
		//検索条件
		JLabel LB_SearchClWh		= B100_FrameParts.JLabelSet(	  0, 25,130,20,"担当倉庫コード:"		,11,1);
		JLabel LB_SearchClCd		= B100_FrameParts.JLabelSet(	  0, 50,130,20,"荷主コード:"			,11,1);
		JLabel LB_SearchClGpCD		= B100_FrameParts.JLabelSet(	  0, 75,130,20,"荷主グループCD:"		,11,1);
		JLabel LB_SearchItemCd		= B100_FrameParts.JLabelSet(	  0,100,130,20,"商品コード:"			,11,1);
		JLabel LB_SearchItemName01	= B100_FrameParts.JLabelSet(	  0,125,130,20,"商品名1:"				,11,1);
		JLabel LB_SearchRecomendLoc	= B100_FrameParts.JLabelSet(	  0,150,130,20,"推奨ロケ:"				,11,1);
		JLabel LB_SearchLocName		= B100_FrameParts.JLabelSet(	  0,175,130,20,"ロケーション名:"		,11,1);
		JLabel LB_SearchType		= B100_FrameParts.JLabelSet(	  0,200,130,20,"ロケタイプ:"			,11,1);
		
		final JComboBox   TB_SearchClWh			= B100_FrameParts.JComboBoxSet(	130, 25,250,20,B100_DefaultVariable.SearchWhList[0],11);		//担当倉庫コード
		final JComboBox   TB_SearchClCd			= B100_FrameParts.JComboBoxSet(	130, 50,250,20,B100_DefaultVariable.SearchClList[0],11);		//荷主CD
		final JComboBox   TB_SearchClGpCD		= B100_FrameParts.JComboBoxSet(	130, 75,250,20,B100_DefaultVariable.SearchClGpList[0],11);	//荷主グループCD
		final JTextField  TB_SearchItemCd		= B100_FrameParts.JTextFieldSet( 130,100,100,20,"",11,0);	//商品コード
		final JTextField  TB_SearchItemName01	= B100_FrameParts.JTextFieldSet( 130,125,100,20,"",11,0);	//商品名1
		final JTextField  TB_SearchRecomendLoc	= B100_FrameParts.JTextFieldSet( 130,150,100,20,"",11,0);	//推奨ロケ
		final JTextField  TB_SearchLocName		= B100_FrameParts.JTextFieldSet( 130,175,100,20,"",11,0);	//ロケーション名
		final JComboBox   TB_SearchType			= B100_FrameParts.JComboBoxSet(	130,200,100,20,B100_DefaultVariable.SearchLocType[0],11);	//ロケタイプ
		
		JLabel LB2_SearchItemCd			= B100_FrameParts.JLabelSet(	230,100,130,20,"と一致"		,11,0);
		JLabel LB2_SearchItemName01		= B100_FrameParts.JLabelSet(	230,125,130,20,"を含む"		,11,0);
		JLabel LB2_SearchRecomendLoc	= B100_FrameParts.JLabelSet(	230,150,130,20,"と一致"		,11,0);
		JLabel LB2_SearchLocName		= B100_FrameParts.JLabelSet(	230,175,130,20,"を含む"		,11,0);
		
		for(int i=0;i<B100_DefaultVariable.SearchWhList[1].length;i++) {
			if(B100_DefaultVariable.SearchWhList[1][i].equals(A00000_Main.ClWh)) {
				TB_SearchClWh.setSelectedIndex(i);
			}
		}
		
		for(int i=0;i<B100_DefaultVariable.SearchClList[1].length;i++) {
			if(B100_DefaultVariable.SearchClList[1][i].equals(A00000_Main.ClCd)) {
				TB_SearchClCd.setSelectedIndex(i);
			}
		}
		
		for(int i=0;i<B100_DefaultVariable.SearchClGpList[1].length;i++) {
			if(B100_DefaultVariable.SearchClGpList[1][i].equals(A00000_Main.ClGp)) {
				TB_SearchClGpCD.setSelectedIndex(i);
			}
		}
		
		TB_SearchClWh.setEnabled(false);
		TB_SearchClCd.setEnabled(false);
		TB_SearchClGpCD.setEnabled(false);
		
		//検索ボタン
		JButton SearchBtn = B100_FrameParts.BtnSet(130,225,100,20,"検索",11);
		PN_Search.add(SearchBtn);
		
		PN_Search.add(LB_SearchClWh);
		PN_Search.add(LB_SearchClCd);
		PN_Search.add(LB_SearchClGpCD);
		PN_Search.add(LB_SearchItemCd);
		PN_Search.add(LB_SearchItemName01);
		PN_Search.add(LB_SearchRecomendLoc);
		PN_Search.add(LB_SearchLocName);
		PN_Search.add(LB_SearchType);
		
		PN_Search.add(TB_SearchClWh);
		PN_Search.add(TB_SearchClCd);
		PN_Search.add(TB_SearchClGpCD);
		PN_Search.add(TB_SearchItemCd);
		PN_Search.add(TB_SearchItemName01);
		PN_Search.add(TB_SearchRecomendLoc);
		PN_Search.add(TB_SearchLocName);
		PN_Search.add(TB_SearchType);
		
		PN_Search.add(LB2_SearchItemCd);
		PN_Search.add(LB2_SearchItemName01);
		PN_Search.add(LB2_SearchRecomendLoc);
		PN_Search.add(LB2_SearchLocName);
		
		main_fm.add(PN_Search);
		
		
		Object[][] RtItemRecomendLocMstRt = M100_ItemRecomendLocMstRt.RtItemRecomendLocMstRt();
		
		String[] columnNames01 = new String[RtItemRecomendLocMstRt.length+1];
		
		columnNames01[0] = "Fg";
		for(int i=0;i<RtItemRecomendLocMstRt.length;i++) {
			columnNames01[1+i] = ""+RtItemRecomendLocMstRt[i][3];
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
		
		for(int i=0;i<RtItemRecomendLocMstRt.length;i++) {
			if("int".equals((String)RtItemRecomendLocMstRt[i][2])||"float".equals((String)RtItemRecomendLocMstRt[i][2])) {
				column = columnModel01.getColumn(1+i);	column.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.rightCellRenderer());
			}else {
				column = columnModel01.getColumn(1+i);	column.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.leftCellRenderer());
			}
		}
		//スクロール用設定
		JScrollPane scpn01 = B100_FrameParts.JScrollPaneSet(10,300,870,325,tb01);
		main_fm.add(scpn01);
		
		//CSVボタン
		JButton CsvBtn = B100_FrameParts.BtnSet(			 10,660,100,20,"csv出力",11);
		main_fm.add(CsvBtn);
		
		JLabel LB_RenewBtn  = B100_FrameParts.JLabelSet(	130,640,100,20,"チェック行を" ,11,2);
		main_fm.add(LB_RenewBtn);
		
		//修正ボタン
		JButton RenewBtn = B100_FrameParts.BtnSet(		130,660,100,20,"修正",11);
		main_fm.add(RenewBtn);
		
		//新規登録ボタン
		JButton CreateBtn = B100_FrameParts.BtnSet(		250,660,100,20,"新規登録",11);
		main_fm.add(CreateBtn);
		
		//Excel出力ボタン
		JButton ExcelBtn = B100_FrameParts.BtnSet(		370,660,100,20,"Excel出力",11);
		main_fm.add(ExcelBtn);
		
		//Excel取込ボタン
		JButton ExcelEntryBtn = B100_FrameParts.BtnSet(	490,660,100,20,"Excel取込",11);
		main_fm.add(ExcelEntryBtn);	
		
		
		
		main_fm.setVisible(true);
		RenewFg = true;
		
		//検索ボタン押下時の挙動
		SearchBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					int RowCount = tableModel_ms01.getRowCount();
					for(int i=0;i<RowCount;i++) {
						tableModel_ms01.removeRow(0);
					}
					
					String GetSearchClWh		= B100_DefaultVariable.SearchWhList[1][TB_SearchClWh.getSelectedIndex()];		//担当倉庫コード
					String GetSearchClCd		= B100_DefaultVariable.SearchClList[1][TB_SearchClCd.getSelectedIndex()];		//荷主CD
					String GetSearchClGpCD		= B100_DefaultVariable.SearchClGpList[1][TB_SearchClGpCD.getSelectedIndex()];	//荷主グループCD
					String GetSearchItemCd		= TB_SearchItemCd.getText();			//商品CD
					String GetSearchItemName01	= TB_SearchItemName01.getText();		//商品名1
					String GetSearchRecomendLoc	= TB_SearchRecomendLoc.getText();		//推奨ロケ
					String GetSearchLocName		= TB_SearchLocName.getText();			//ロケーション名
					String GetSearchType		= B100_DefaultVariable.SearchLocType[1][TB_SearchType.getSelectedIndex()];		//ロケタイプ
					
					if(null==GetSearchClWh			){GetSearchClWh			= "";}		//担当倉庫コード
					if(null==GetSearchClCd			){GetSearchClCd			= "";}		//荷主CD
					if(null==GetSearchClGpCD		){GetSearchClGpCD		= "";}		//荷主グループCD
					if(null==GetSearchItemCd		){GetSearchItemCd		= "";}		//商品CD
					if(null==GetSearchItemName01	){GetSearchItemName01	= "";}		//商品名1
					if(null==GetSearchRecomendLoc	){GetSearchRecomendLoc	= "";}		//推奨ロケ
					if(null==GetSearchLocName		){GetSearchLocName		= "";}		//ロケーション名
					if(null==GetSearchType			){GetSearchType			= "";}		//ロケタイプ
					
					GetSearchClWh			= B100_TextControl.Trim(GetSearchClWh);			//担当倉庫コード
					GetSearchClCd			= B100_TextControl.Trim(GetSearchClCd);			//荷主CD
					GetSearchClGpCD			= B100_TextControl.Trim(GetSearchClGpCD);			//荷主グループCD
					GetSearchItemCd			= B100_TextControl.Trim(GetSearchItemCd);			//商品CD
					GetSearchItemName01		= B100_TextControl.Trim(GetSearchItemName01);		//商品名1
					GetSearchRecomendLoc	= B100_TextControl.Trim(GetSearchRecomendLoc);	//推奨ロケ
					GetSearchLocName		= B100_TextControl.Trim(GetSearchLocName);		//ロケーション名
					GetSearchType			= B100_TextControl.Trim(GetSearchType);			//ロケタイプ
					
					GetSearchType			= B100_TextControl.num_only_String(GetSearchType);			//ロケタイプ
					
					
					ArrayList<String> SearchClCd		= new ArrayList<String>();	//荷主コード
					ArrayList<String> SearchClWh		= new ArrayList<String>();	//担当倉庫コード
					ArrayList<String> SearchClGpCD		= new ArrayList<String>();	//荷主グループCD
					ArrayList<String> SearchItemCd		= new ArrayList<String>();	//商品コード
					ArrayList<String> SearchItemName01	= new ArrayList<String>();	//商品名1
					ArrayList<String> SearchRecomendLoc	= new ArrayList<String>();	//推奨ロケ
					ArrayList<String> SearchLocName		= new ArrayList<String>();	//ロケーション名
					ArrayList<Integer> SearchType		= new ArrayList<Integer>();	//ロケタイプ　0:通常　1:保管　8:入荷時　9:引当禁止
					boolean LocExactMatch = true;									//ロケーション完全一致
					boolean AllSearch = true;
					
					if(!"".equals(GetSearchClWh			)){SearchClWh.add(GetSearchClWh);}						//担当倉庫コード
					if(!"".equals(GetSearchClCd			)){SearchClCd.add(GetSearchClCd);}						//荷主CD
					if(!"".equals(GetSearchClGpCD		)){SearchClGpCD.add(GetSearchClGpCD);}					//荷主グループCD
					if(!"".equals(GetSearchItemCd		)){SearchItemCd.add(GetSearchItemCd);}					//商品CD
					if(!"".equals(GetSearchItemName01	)){SearchItemName01.add(GetSearchItemName01);}			//商品名1
					if(!"".equals(GetSearchRecomendLoc	)){SearchRecomendLoc.add(GetSearchRecomendLoc);}		//推奨ロケ
					if(!"".equals(GetSearchLocName		)){SearchLocName.add(GetSearchLocName);}				//ロケーション名
					if(!"".equals(GetSearchType			)){SearchType.add(Integer.parseInt(GetSearchType));}	//ロケタイプ
					
					Object[][] ItemRecomendLocMstRt	= M100_ItemRecomendLocMstRt.ItemRecomendLocMstRt(
							SearchClCd,			//荷主コード
							SearchClWh,			//担当倉庫コード
							SearchClGpCD,		//荷主グループCD
							SearchItemCd,		//商品コード
							SearchItemName01,	//商品名1
							SearchRecomendLoc,	//推奨ロケ
							SearchLocName,		//ロケーション名
							SearchType,			//ロケタイプ　0:通常　1:保管　8:入荷時　9:引当禁止
							LocExactMatch,		//ロケーション完全一致
							AllSearch);
					
					for(int i=0;i<ItemRecomendLocMstRt.length;i++) {
						Object[] SetOb = new Object[ItemRecomendLocMstRt[i].length+1];
						SetOb[0] = false;
						for(int i01=0;i01<ItemRecomendLocMstRt[i].length;i01++) {
							SetOb[i01+1] = ""+ItemRecomendLocMstRt[i][i01];
						}
						tableModel_ms01.addRow(SetOb);;
					}
					if(0==ItemRecomendLocMstRt.length) {
						B100_TableControl.AddSortOFF(tb01,tableModel_ms01);
					}else {
						B100_TableControl.AddSortON(tb01,tableModel_ms01);
					}
					RenewFg = true;
				}
			}
		});
		
		//修正ボタン押下時の挙動
		RenewBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					String TgtClCd		= "";
					String TgtWhCd		= "";
					String TgtItemCd	= "";
					boolean KickFg = false;
					
					int RowCount = tableModel_ms01.getRowCount();
					for(int i=0;i<RowCount;i++) {
						if((boolean)tableModel_ms01.getValueAt(i, 0)) {
							TgtClCd		= ""+tableModel_ms01.getValueAt(i, M100_ItemRecomendLocMstRt.ColClCd+1);
							TgtWhCd		= ""+tableModel_ms01.getValueAt(i, M100_ItemRecomendLocMstRt.ColClWh+1);
							TgtItemCd	= ""+tableModel_ms01.getValueAt(i, M100_ItemRecomendLocMstRt.ColItemCd+1);
							
							KickFg = true;
						}
					}
					
					if(KickFg) {
						SetX=main_fm.getX();
						SetY=main_fm.getY();
	
						main_fm.setVisible(false);
						main_fm.dispose();
						
						WM100_ItemRecomendLocMst_01_RenewAndCreate.ItemRecomendLocMstRenewAndCreate(0,0,TgtClCd,TgtWhCd,TgtItemCd);
					}
					RenewFg = true;
				}
			}
		});
		
		//新規登録ボタン押下時の挙動
		CreateBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					String TgtClCd		= "";
					String TgtWhCd		= "";
					String TgtItemCd	= "";
					
					SetX=main_fm.getX();
					SetY=main_fm.getY();

					main_fm.setVisible(false);
					main_fm.dispose();
					
					WM100_ItemRecomendLocMst_01_RenewAndCreate.ItemRecomendLocMstRenewAndCreate(0,0,TgtClCd,TgtWhCd,TgtItemCd);
					RenewFg = true;
				}
			}
		});
		
		//Excel取込ボタン
		ExcelEntryBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					String MSG = "エクセルファイル選択";
					String[] file_type = {".xlsx"};
					String file_type_name = "エクセルファイル";
					String Selected = B100_FileSelect.FileSelect(MSG,file_type,file_type_name);
					
					if(null!=Selected && !Selected.equals(Selected.replace(".xlsx", ""))) {
						SetX=main_fm.getX();
						SetY=main_fm.getY();

						main_fm.setVisible(false);
						main_fm.dispose();
						WM100_ItemRecomendLocMst_02_ExcelEntry.ItemRecomendLocMstExcelEntry(0,0,Selected);
					}
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
					B100_TableControl.TableOutPutCsv("出力先選択","推奨ロケマスタ検索結果",tb01);
					RenewFg = true;
				}
			}
		});
		
		//エクセル出力ボタン押下時の挙動
		ExcelBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					B100_TableControl.TableOutPutExcel("出力先選択","推奨ロケマスタ検索結果",tb01);
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
				A00001_MstMain.MstMain(0, 0);
			}
		});
	}
}