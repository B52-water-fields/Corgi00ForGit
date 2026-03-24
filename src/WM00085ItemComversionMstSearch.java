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

public class WM00085ItemComversionMstSearch{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	public static void ItemComversionMstSearch(int x,int y) {
		A00000Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		
		final JFrame main_fm = B00110FrameParts.FrameCreate(x,y,1300,750,"Corgi00商品変換マスタ検索","");
		JLabel userinfo = B00110FrameParts.UserInfo();
		JButton exit_btn = B00110FrameParts.ExitBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		
		//検索条件パネル
		JPanel PN_Search = B00110FrameParts.JPanelSet(10,40,1280,220,"White");
		JLabel PN_SearchLabel = B00110FrameParts.JLabelSet(10,0,150,20,"検索条件",11,0);
		
		//検索条件
		JLabel LB_SearchClGpCd		= B00110FrameParts.JLabelSet(	  0, 25,130,20,"荷主グループコード:"	,11,1);
		JLabel LB_SearchClCd		= B00110FrameParts.JLabelSet(	  0, 50,130,20,"荷主コード:"			,11,1);
		JLabel LB_SearchItemCd		= B00110FrameParts.JLabelSet(	  0, 75,130,20,"商品コード:"			,11,1);
		JLabel LB_SearchCLItemCd	= B00110FrameParts.JLabelSet(	  0,100,130,20,"荷主商品コード:"		,11,1);
		JLabel LB_SearchItemName	= B00110FrameParts.JLabelSet(	  0,125,130,20,"商品名:"				,11,1);
		
		final JComboBox   TB_SearchClGpCd	= B00110FrameParts.JComboBoxSet(	130, 25,250,20,B00100DefaultVariable.SearchClGpList[0],11);	//荷主グループコード
		final JComboBox   TB_SearchClCd		= B00110FrameParts.JComboBoxSet(	130, 50,250,20,B00100DefaultVariable.SearchClList[0],11);		//荷主コード
		final JTextField  TB_SearchItemCd	= B00110FrameParts.JTextFieldSet(	130, 75,100,20,""	,11,0);		//商品コード
		final JTextField  TB_SearchCLItemCd	= B00110FrameParts.JTextFieldSet(	130,100,100,20,""	,11,0);		//荷主商品コード
		final JTextField  TB_SearchItemName	= B00110FrameParts.JTextFieldSet(	130,125,100,20,""	,11,0);		//商品名
		
		JLabel LB2_SearchItemCd		= B00110FrameParts.JLabelSet(	230, 75, 80,20,"と一致"	,11,0);
		JLabel LB2_SearchCLItemCd	= B00110FrameParts.JLabelSet(	230,100, 80,20,"と一致"	,11,0);
		JLabel LB2_SearchItemName	= B00110FrameParts.JLabelSet(	230,125, 80,20,"を含む"	,11,0);
		
		//荷主グループ検索条件に現在荷主を設定 管理者以外は荷主選択条件固定する
		for(int i=0;i<B00100DefaultVariable.SearchClGpList[1].length;i++) {
			if(A00000Main.ClGp.equals(""+B00100DefaultVariable.SearchClGpList[1][i])) {
				TB_SearchClGpCd.setSelectedIndex(i);
			}
		}
		for(int i=0;i<B00100DefaultVariable.SearchClList[1].length;i++) {
			if(A00000Main.ClCd.equals(""+B00100DefaultVariable.SearchClList[1][i])) {
				TB_SearchClCd.setSelectedIndex(i);
			}
		}
		if("9".equals(A00000Main.LoginUserAuthorityFG)) {
			
		}else {
			TB_SearchClGpCd.setEditable(false);
			TB_SearchClCd.setEditable(false);
		}
		
		PN_Search.add(LB_SearchClGpCd);
		PN_Search.add(LB_SearchClCd);
		PN_Search.add(LB_SearchItemCd);
		PN_Search.add(LB_SearchCLItemCd);
		PN_Search.add(LB_SearchItemName);
		
		PN_Search.add(TB_SearchClGpCd);
		PN_Search.add(TB_SearchClCd);
		PN_Search.add(TB_SearchItemCd);
		PN_Search.add(TB_SearchCLItemCd);
		PN_Search.add(TB_SearchItemName);
		
		PN_Search.add(LB2_SearchItemCd);
		PN_Search.add(LB2_SearchCLItemCd);
		PN_Search.add(LB2_SearchItemName);
		
		//検索ボタン
		JButton SearchBtn = B00110FrameParts.BtnSet(130,175,100,20,"検索",11);
		PN_Search.add(SearchBtn);
		
		main_fm.add(PN_Search);
		
		//検索結果
		Object[][] RtItemComversionMstRt = M00080ItemComversionMstRt.RtItemComversionMstRt();
		
		String[] columnNames01 = new String[RtItemComversionMstRt.length+1];
		
		columnNames01[0] = "Fg";
		for(int i=0;i<RtItemComversionMstRt.length;i++) {
			columnNames01[1+i] = ""+RtItemComversionMstRt[i][3];
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
		
		for(int i=0;i<RtItemComversionMstRt.length;i++) {
			column = columnModel01.getColumn(1+i);	column.setPreferredWidth( 90*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());
		}
		
		//スクロール用設定
		JScrollPane scpn01 = B00110FrameParts.JScrollPaneSet(10,270,1280,350,tb01);
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
					
					int RowCount = tableModel_ms01.getRowCount();
					for(int i=0;i<RowCount;i++) {
						tableModel_ms01.removeRow(0);
					}
					String GetSearchClGpCd 		= B00100DefaultVariable.SearchClGpList[1][TB_SearchClGpCd.getSelectedIndex()];	//荷主グループコード
					String GetSearchClCd 		= B00100DefaultVariable.SearchClList[1][TB_SearchClCd.getSelectedIndex()];		//荷主コード
					String GetSearchItemCd		= TB_SearchItemCd.getText();	//商品コード
					String GetSearchCLItemCd	= TB_SearchCLItemCd.getText();	//荷主商品コード
					String GetSearchItemName	= TB_SearchItemName.getText();	//商品名
					
					if(null==GetSearchClGpCd	){GetSearchClGpCd="";}		//荷主グループコード
					if(null==GetSearchClCd		){GetSearchClCd="";}		//荷主コード
					if(null==GetSearchItemCd	){GetSearchItemCd="";}		//商品コード
					if(null==GetSearchCLItemCd	){GetSearchCLItemCd="";}	//荷主商品コード
					if(null==GetSearchItemName	){GetSearchCLItemCd="";}	//商品名
					
					GetSearchClGpCd		= B00020ToolsTextControl.Trim(GetSearchClGpCd);		//荷主グループコード
					GetSearchClCd		= B00020ToolsTextControl.Trim(GetSearchClCd);		//荷主コード
					GetSearchItemCd		= B00020ToolsTextControl.Trim(GetSearchItemCd);		//商品コード
					GetSearchCLItemCd	= B00020ToolsTextControl.Trim(GetSearchCLItemCd);	//荷主商品コード
					GetSearchItemName	= B00020ToolsTextControl.Trim(GetSearchCLItemCd);	//商品名
					
					ArrayList<String> SearchClGpCd = new ArrayList<String>();		//荷主グループコード
					ArrayList<String> SearchClCd = new ArrayList<String>();			//荷主コード
					ArrayList<String> SearchItemCd = new ArrayList<String>();		//商品コード
					ArrayList<String> SearchCLItemCd = new ArrayList<String>();		//荷主商品コード
					ArrayList<String> SearchItemName = new ArrayList<String>();		//商品名
					boolean AllSearch = false;
					
					if(!"".equals(GetSearchClGpCd	)){SearchClGpCd.add(GetSearchClGpCd);}		//荷主グループコード
					if(!"".equals(GetSearchClCd		)){SearchClCd.add(GetSearchClCd);}			//荷主コード
					if(!"".equals(GetSearchItemCd	)){SearchItemCd.add(GetSearchItemCd);}		//商品コード
					if(!"".equals(GetSearchCLItemCd	)){SearchCLItemCd.add(GetSearchCLItemCd);}	//荷主商品コード
					if(!"".equals(GetSearchItemName	)){SearchItemName.add(GetSearchCLItemCd);}	//商品名
					
					Object[][] ItemComversionMstRt = M00080ItemComversionMstRt.ItemComversionMstRt(
							SearchClGpCd,			//荷主グループコード
							SearchClCd,				//荷主コード
							SearchItemCd,			//商品コード
							SearchCLItemCd,			//荷主商品コード
							SearchItemName,			//商品名
							AllSearch);
					
					for(int i=0;i<ItemComversionMstRt.length;i++) {
						Object[] SetOb = new Object[ItemComversionMstRt[i].length+1];
						SetOb[0] = false;
						for(int i01=0;i01<ItemComversionMstRt[i].length;i01++) {
							SetOb[i01+1] = ""+ItemComversionMstRt[i][i01];
						}
						tableModel_ms01.addRow(SetOb);
					}
					if(0<ItemComversionMstRt.length) {
						B10010TableControl.AddSortON(tb01,tableModel_ms01);
					}else {
						B10010TableControl.AddSortOFF(tb01,tableModel_ms01);
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
					int RowCount = tableModel_ms01.getRowCount();
					String TgtClgpCd = "";
					String TgtClCd = "";
					String TgtClItemCd = "";
					
					for(int i=0;i<RowCount;i++) {
						if((boolean)tableModel_ms01.getValueAt(i, 0)) {
							TgtClgpCd 	= ""+tableModel_ms01.getValueAt(i, 1);
							TgtClCd		= ""+tableModel_ms01.getValueAt(i, 3);
							TgtClItemCd 	= ""+tableModel_ms01.getValueAt(i, 5);
						}
					}
					if(!"".equals(TgtClgpCd) && !"".equals(TgtClCd) && !"".equals(TgtClItemCd)) {
						SetX=main_fm.getX();
						SetY=main_fm.getY();
		
						main_fm.setVisible(false);
						main_fm.dispose();
						
						WM00086ItemComversionMstRenewAndCreate.ItemComversionMstRenewAndCreate(0,0,TgtClgpCd,TgtClCd,TgtClItemCd);
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
					SetX=main_fm.getX();
					SetY=main_fm.getY();
	
					main_fm.setVisible(false);
					main_fm.dispose();
					
					WM00086ItemComversionMstRenewAndCreate.ItemComversionMstRenewAndCreate(0,0,"","","");
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
					B10010TableControl.TableOutPutCsv("出力先選択","商品変換マスタ検索結果",tb01);
					RenewFg = true;
				}
			}
		});
		
		//エクセル出力ボタン押下時の挙動
		ExcelBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					B10010TableControl.TableOutPutExcel("出力先選択","商品変換マスタ検索結果",tb01);
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
						WM00087ItemComversionMstExcelEntry.ItemComversionMstExcelEntry(0,0,Selected);
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
				W00020MstMain.MstMain(0, 0);
			}
		});
	}
}