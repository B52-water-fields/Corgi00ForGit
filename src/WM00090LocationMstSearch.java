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

public class WM00090LocationMstSearch{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	public static void LocationMstSearch(int x,int y) {
		
		A00000Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		final JFrame main_fm = B00110FrameParts.FrameCreate(x,y,900,750,"Corgi00届先注意事項マスタ検索","");
		JLabel userinfo = B00110FrameParts.UserInfo();
		JButton exit_btn = B00110FrameParts.ExitBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		
		//検索条件パネル
		JPanel PN_Search = B00110FrameParts.JPanelSet(			 10, 40,870,180,"White");
		JLabel PN_SearchLabel = B00110FrameParts.JLabelSet(	 10,  0,150,20,"検索条件",11,0);
		PN_Search.add(PN_SearchLabel);
		main_fm.add(PN_Search);
		
		JLabel LB_SearchClCd		= B00110FrameParts.JLabelSet(  0, 25,100,20,"荷主コード:"		,11,1);
		JLabel LB_SearchWhCd		= B00110FrameParts.JLabelSet(  0, 50,100,20,"倉庫コード:"		,11,1);
		JLabel LB_SearchLoc			= B00110FrameParts.JLabelSet(  0, 75,100,20,"ロケーション:"	,11,1);
		JLabel LB_SearchLocName		= B00110FrameParts.JLabelSet(  0,100,100,20,"ロケーション名:"	,11,1);
		JLabel LB_SearchType		= B00110FrameParts.JLabelSet(  0,125,100,20,"ロケタイプ:"		,11,1);
		
		final JComboBox   TB_SearchClCd		= B00110FrameParts.JComboBoxSet( 100, 25,200,20,B00100DefaultVariable.SearchClList[0],11);	//荷主コード
		final JComboBox   TB_SearchWhCd		= B00110FrameParts.JComboBoxSet( 100, 50,200,20,B00100DefaultVariable.SearchWhList[0],11);	//倉庫コード
		final JTextField  TB_SearchLoc		= B00110FrameParts.JTextFieldSet(100, 75,100,20,"",11,0);										//ロケーション
		final JTextField  TB_SearchLocName	= B00110FrameParts.JTextFieldSet(100,100,100,20,"",11,0);										//ロケーション名
		final JComboBox   TB_SearchType		= B00110FrameParts.JComboBoxSet( 100,125,100,20,B00100DefaultVariable.SearchLocType[0],11);	//ロケタイプ
		
		JLabel LB2_SearchLoc		= B00110FrameParts.JLabelSet(  200, 75,100,20,"で始まる"	,11,0);
		JLabel LB2_SearchLocName	= B00110FrameParts.JLabelSet(  200,100,100,20,"を含む"		,11,0);
		
		for(int i=0;i<B00100DefaultVariable.SearchClList[1].length;i++) {
			if(A00000Main.ClCd.equals(B00100DefaultVariable.SearchClList[1][i])) {
				TB_SearchClCd.setSelectedIndex(i);
			}
		}
		for(int i=0;i<B00100DefaultVariable.SearchWhList[1].length;i++) {
			if(A00000Main.ClWh.equals(B00100DefaultVariable.SearchWhList[1][i])) {
				TB_SearchWhCd.setSelectedIndex(i);
			}
		}
		if("9".equals(A00000Main.LoginUserAuthorityFG)) {
			
		}else {
			TB_SearchClCd.setEnabled(false);
			TB_SearchWhCd.setEnabled(false);
		}
		
		PN_Search.add(LB_SearchClCd);
		PN_Search.add(LB_SearchWhCd);
		PN_Search.add(LB_SearchLoc);
		PN_Search.add(LB_SearchLocName);
		PN_Search.add(LB_SearchType);
		
		PN_Search.add(TB_SearchClCd);
		PN_Search.add(TB_SearchWhCd);
		PN_Search.add(TB_SearchLoc);
		PN_Search.add(TB_SearchLocName);
		PN_Search.add(TB_SearchType);
		
		PN_Search.add(LB2_SearchLoc);
		PN_Search.add(LB2_SearchLocName);
		
		//検索ボタン
		JButton SearchBtn = B00110FrameParts.BtnSet(100,150,100,20,"検索",11);
		PN_Search.add(SearchBtn);
		
		Object[][] RtSettingLocationMstRt = M00090LocationMstRt.RtSettingLocationMstRt();
		
		String[] columnNames01 = new String[RtSettingLocationMstRt.length+1];
		
		columnNames01[0] = "Fg";
		for(int i=0;i<RtSettingLocationMstRt.length;i++) {
			columnNames01[1+i] = ""+RtSettingLocationMstRt[i][3];
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
		
		for(int i=0;i<RtSettingLocationMstRt.length;i++) {
			column = columnModel01.getColumn(1+i);	column.setPreferredWidth( 90*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());
		}
		
		//スクロール用設定
		JScrollPane scpn01 = B00110FrameParts.JScrollPaneSet(10,230,870,395,tb01);
		main_fm.add(scpn01);
		
		//CSVボタン
		JButton CsvBtn = B00110FrameParts.BtnSet(			 10,660,100,20,"csv出力",11);
		main_fm.add(CsvBtn);
		
		JLabel LB_RenewBtn  = B00110FrameParts.JLabelSet(	130,640,100,20,"チェック行を" ,11,2);
		main_fm.add(LB_RenewBtn);
		
		//修正ボタン
		JButton RenewBtn = B00110FrameParts.BtnSet(		130,660,100,20,"修正",11);
		main_fm.add(RenewBtn);
		
		//新規登録ボタン
		JButton CreateBtn = B00110FrameParts.BtnSet(		250,660,100,20,"新規登録",11);
		main_fm.add(CreateBtn);
		
		//Excel出力ボタン
		JButton ExcelBtn = B00110FrameParts.BtnSet(		370,660,100,20,"Excel出力",11);
		main_fm.add(ExcelBtn);
		
		//Excel取込ボタン
		JButton ExcelEntryBtn = B00110FrameParts.BtnSet(	490,660,100,20,"Excel取込",11);
		main_fm.add(ExcelEntryBtn);	
		
		main_fm.setVisible(true);
		RenewFg = true;
		
		//検索ボタン押下時の挙動
		SearchBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					int row_count = tableModel_ms01.getRowCount();
					Boolean setBL=Boolean.valueOf(false);
					for(int i=0;i<row_count;i++){
						tableModel_ms01.removeRow(0);
					}
					
					String GetSearchClCd 	= B00100DefaultVariable.SearchClList[1][TB_SearchClCd.getSelectedIndex()];
					String GetSearchWhCd 	= B00100DefaultVariable.SearchWhList[1][TB_SearchWhCd.getSelectedIndex()];
					String GetSearchLoc 	= TB_SearchLoc.getText();
					String GetSearchLocName = TB_SearchLocName.getText();
					String GetSearchType 	= B00100DefaultVariable.SearchLocType[1][TB_SearchType.getSelectedIndex()];
					
					if(null==GetSearchClCd		){GetSearchClCd 	= "";}
					if(null==GetSearchWhCd		){GetSearchWhCd 	= "";}
					if(null==GetSearchLoc		){GetSearchLoc 		= "";}
					if(null==GetSearchLocName	){GetSearchLocName 	= "";}
					if(null==GetSearchType		){GetSearchType 	= "";}
					
					GetSearchClCd 		= B00020ToolsTextControl.Trim(GetSearchClCd);
					GetSearchWhCd 		= B00020ToolsTextControl.Trim(GetSearchWhCd);
					GetSearchLoc 		= B00020ToolsTextControl.Trim(GetSearchLoc);
					GetSearchLocName 	= B00020ToolsTextControl.Trim(GetSearchLocName);
					GetSearchType 		= B00020ToolsTextControl.Trim(GetSearchType);
					
					ArrayList<String> SearchClCd 	= new ArrayList<String>();	//荷主コード
					ArrayList<String> SearchWhCd 	= new ArrayList<String>();	//倉庫コード
					ArrayList<String> SearchLoc 	= new ArrayList<String>();	//ロケーション
					ArrayList<String> SearchLocName = new ArrayList<String>();	//ロケーション名
					ArrayList<String> SearchType 	= new ArrayList<String>();	//ロケタイプ
					boolean LocExactMatch = false;	//ロケーション完全一致
					boolean AllSearch = true;
					
					if(!"".equals(GetSearchClCd		)){SearchClCd.add(		GetSearchClCd);}	//荷主コード
					if(!"".equals(GetSearchWhCd		)){SearchWhCd.add(		GetSearchWhCd);}	//倉庫コード
					if(!"".equals(GetSearchLoc		)){SearchLoc.add(		GetSearchLoc);}		//ロケーション
					if(!"".equals(GetSearchLocName	)){SearchLocName.add(	GetSearchLocName);}	//ロケーション名
					if(!"".equals(GetSearchType		)){SearchType.add(		GetSearchType);}	//ロケタイプ
					
					Object[][] LocationMstRt = M00090LocationMstRt.LocationMstRt(
							SearchClCd,		//荷主コード
							SearchWhCd,		//倉庫コード
							SearchLoc,		//ロケーション
							SearchLocName,	//ロケーション名
							SearchType,		//ロケタイプ
							LocExactMatch,	//ロケーション完全一致
							AllSearch);
					
					for(int i=0;i<LocationMstRt.length;i++) {
						Object[] SetOb = new Object[LocationMstRt[i].length+1];
						SetOb[0] = false;
						for(int i01=0;i01<LocationMstRt[i].length;i01++) {
							SetOb[i01+1] = ""+LocationMstRt[i][i01];
						}
						tableModel_ms01.addRow(SetOb);
					}
					if(0<LocationMstRt.length) {
						B10010TableControl.AddSortON(tb01,tableModel_ms01);
					}else {
						B10010TableControl.AddSortOFF(tb01,tableModel_ms01);
					}
					
					RenewFg = true;
				}
			}
		});
		
		//修正ボタン
		RenewBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					int RowCount = tableModel_ms01.getRowCount();
					String TgtClCd 	= "";
					String TgtWhCd 	= "";
					String TgtLoc 	= "";
					boolean KickFg = false;
					for(int i=0;i<RowCount;i++) {
						if((boolean)tableModel_ms01.getValueAt(i, 0)) {
							TgtClCd = ""+tableModel_ms01.getValueAt(i, 1);
							TgtWhCd = ""+tableModel_ms01.getValueAt(i, 3);
							TgtLoc 	= ""+tableModel_ms01.getValueAt(i, 5);
							KickFg = true;
						}
					}
					if(KickFg) {
						SetX=main_fm.getX();
						SetY=main_fm.getY();

						main_fm.setVisible(false);
						main_fm.dispose();
						WM00091LocationMstRenewAndCreate.LocationMstRenewAndCreate(0,0,TgtClCd,TgtWhCd,TgtLoc);
					}
					RenewFg = true;
				}
			}
		});
				
		//新規登録ボタン
		CreateBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					SetX=main_fm.getX();
					SetY=main_fm.getY();

					main_fm.setVisible(false);
					main_fm.dispose();
					
					String TgtClCd 	= "";
					String TgtWhCd 	= "";
					String TgtLoc 	= "";
					WM00091LocationMstRenewAndCreate.LocationMstRenewAndCreate(0,0,TgtClCd,TgtWhCd,TgtLoc);
					RenewFg = true;
				}
			}
		});
		
		//Excel取込ボタン押下時の挙動
		ExcelEntryBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					String MSG = "エクセルファイル選択";
					String[] file_type = {".xlsx"};
					String file_type_name = "エクセルファイル";
					String Selected = B00090FileSelect.FileSelect(MSG,file_type,file_type_name);
					
					if(null!=Selected && !Selected.equals(Selected.replace(".xlsx", ""))) {
						SetX=main_fm.getX();
						SetY=main_fm.getY();

						main_fm.setVisible(false);
						main_fm.dispose();
						WM00092LocationMstExcelEntry.LocationMstExcelEntry(0,0,Selected);
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
					int row_count = tableModel_ms01.getRowCount();
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
					B10010TableControl.TableOutPutCsv("出力先選択","ロケーションマスタ検索結果",tb01);
					RenewFg = true;
				}
			}
		});
		
		//エクセル出力ボタン押下時の挙動
		ExcelBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					B10010TableControl.TableOutPutExcel("出力先選択","ロケーションマスタ検索結果",tb01);
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
				W00020MstMain.MstMain(0, 0);
			}
		});
	}
}