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

public class WM00070CautionMstSearch{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	
	static String[][] PrefecturesCdList;
	static String[][] MunicipalityCd;
	public static void CautionMstSearch(int x,int y) {
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
		JPanel PN_Search = B00110FrameParts.JPanelSet(			 10, 40,870,160,"White");
		JLabel PN_SearchLabel = B00110FrameParts.JLabelSet(	 10,  0,150,20,"検索条件",11,0);
		PN_Search.add(PN_SearchLabel);
		main_fm.add(PN_Search);
		
		JLabel LB_SearchCautionCd		= B00110FrameParts.JLabelSet(  0, 25,100,20,"注意事項CD:"		,11,1);
		JLabel LB_SearchCautionName		= B00110FrameParts.JLabelSet(  0, 50,100,20,"注意事項名:"		,11,1);
		JLabel LB_SearchCaution			= B00110FrameParts.JLabelSet(  0, 75,100,20,"注意事項内容:"	,11,1);
		JLabel LB_SearchCautionTiming	= B00110FrameParts.JLabelSet(  0,100,100,20,"注意タイミング:"	,11,1);
		
		JLabel LB_SearchClGpCD			= B00110FrameParts.JLabelSet(250, 25,100,20,"荷主グループCD:"	,11,1);
		JLabel LB_SearchDECD			= B00110FrameParts.JLabelSet(250, 50,100,20,"届先CD:"			,11,1);
		JLabel LB_SearchDepartmentCd	= B00110FrameParts.JLabelSet(250, 75,100,20,"届先部署CD:"		,11,1);
		JLabel LB_SearchDeName			= B00110FrameParts.JLabelSet(250,100,100,20,"届先名:"			,11,1);
		
		final JTextField  TB_SearchCautionCd		= B00110FrameParts.JTextFieldSet(100, 25,100,20,"",11,0);			//注意事項CD
		final JTextField  TB_SearchCautionName		= B00110FrameParts.JTextFieldSet(100, 50,100,20,"",11,0);			//注意事項名
		final JTextField  TB_SearchCaution			= B00110FrameParts.JTextFieldSet(100, 75,100,20,"",11,0);			//注意事項内容
		final JComboBox   TB_SearchCautionTiming	= B00110FrameParts.JComboBoxSet( 100,100,100,20,B00100DefaultVariable.SearchCautionTiming[0],11);	//注意タイミング
		
		final JTextField  TB_SearchClGpCD			= B00110FrameParts.JTextFieldSet(350, 25,100,20,"",11,0);			//荷主グループCD
		final JTextField  TB_SearchDECD				= B00110FrameParts.JTextFieldSet(350, 50,100,20,"",11,0);			//届先CD
		final JTextField  TB_SearchDepartmentCd		= B00110FrameParts.JTextFieldSet(350, 75,100,20,"",11,0);			//届先部署CD
		final JTextField  TB_SearchDeName			= B00110FrameParts.JTextFieldSet(350,100,100,20,"",11,0);			//届先名
		
		JLabel LB2_SearchCautionCd		= B00110FrameParts.JLabelSet(200, 25, 50,20,"と一致"	,11,0);
		JLabel LB2_SearchCautionName	= B00110FrameParts.JLabelSet(200, 50, 50,20,"を含む"	,11,0);
		JLabel LB2_SearchCaution		= B00110FrameParts.JLabelSet(200, 75, 50,20,"を含む"	,11,0);
		JLabel LB2_SearchCautionTiming	= B00110FrameParts.JLabelSet(200,100, 50,20,""			,11,0);
		
		JLabel LB2_SearchClGpCD			= B00110FrameParts.JLabelSet(450, 25, 50,20,"と一致"	,11,0);
		JLabel LB2_SearchDECD			= B00110FrameParts.JLabelSet(450, 50, 50,20,"と一致"	,11,0);
		JLabel LB2_SearchDepartmentCd	= B00110FrameParts.JLabelSet(450, 75, 50,20,"と一致"	,11,0);
		JLabel LB2_SearchDeName			= B00110FrameParts.JLabelSet(450,100, 50,20,"を含む"	,11,0);

		PN_Search.add(LB_SearchCautionCd);
		PN_Search.add(LB_SearchCautionName);
		PN_Search.add(LB_SearchCaution);
		PN_Search.add(LB_SearchCautionTiming);
		
		PN_Search.add(LB_SearchClGpCD);
		PN_Search.add(LB_SearchDECD);
		PN_Search.add(LB_SearchDepartmentCd);
		PN_Search.add(LB_SearchDeName);
		
		PN_Search.add(TB_SearchCautionCd);
		PN_Search.add(TB_SearchCautionName);
		PN_Search.add(TB_SearchCaution);
		PN_Search.add(TB_SearchCautionTiming);
		
		PN_Search.add(TB_SearchClGpCD);
		PN_Search.add(TB_SearchDECD);
		PN_Search.add(TB_SearchDepartmentCd);
		PN_Search.add(TB_SearchDeName);
		
		PN_Search.add(LB2_SearchCautionCd);
		PN_Search.add(LB2_SearchCautionName);
		PN_Search.add(LB2_SearchCaution);
		PN_Search.add(LB2_SearchCautionTiming);
		
		PN_Search.add(LB2_SearchClGpCD);
		PN_Search.add(LB2_SearchDECD);
		PN_Search.add(LB2_SearchDepartmentCd);
		PN_Search.add(LB2_SearchDeName);
		
		//検索ボタン
		JButton SearchBtn = B00110FrameParts.BtnSet(350,125,100,20,"検索",11);
		PN_Search.add(SearchBtn);
		
		Object[][] RtSettingCautionMstRt = M00042CautionMstRt.RtSettingCautionMstRt();
		
		String[] columnNames01 = new String[RtSettingCautionMstRt.length+1];
		
		columnNames01[0] = "Fg";
		for(int i=0;i<RtSettingCautionMstRt.length;i++) {
			columnNames01[1+i] = ""+RtSettingCautionMstRt[i][3];
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
		
		for(int i=0;i<RtSettingCautionMstRt.length;i++) {
			column = columnModel01.getColumn(1+i);	column.setPreferredWidth( 90*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());
		}
		
		//スクロール用設定
		JScrollPane scpn01 = B00110FrameParts.JScrollPaneSet(10,210,870,395,tb01);
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
		
		JLabel LB_DeleteBtn  = B00110FrameParts.JLabelSet(	610,640,100,20,"チェック行を" ,11,2);
		main_fm.add(LB_DeleteBtn);
		
		//削除ボタン
		JButton DeleteBtn = B00110FrameParts.BtnSet(	610,660,100,20,"削除",11);
		main_fm.add(DeleteBtn);
		
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
					
					String GetSearchCautionCd = TB_SearchCautionCd.getText();		//注意事項CD
					String GetSearchCautionName = TB_SearchCautionName.getText();	//注意事項名
					String GetSearchCaution = TB_SearchCaution.getText();			//注意事項内容
					String GetSearchCautionTiming = B00100DefaultVariable.SearchCautionTiming[1][TB_SearchCautionTiming.getSelectedIndex()];	//注意タイミング
					String GetSearchClGpCD = TB_SearchClGpCD.getText();				//荷主グループCD
					String GetSearchDECD = TB_SearchDECD.getText();					//届先CD
					String GetSearchDepartmentCd = TB_SearchDepartmentCd.getText();	//届先部署CD
					String GetSearchDeName = TB_SearchDeName.getText();				//届先名
					
					if(null==GetSearchCautionCd		){GetSearchCautionCd	= "";}
					if(null==GetSearchCautionName	){GetSearchCautionName	= "";}
					if(null==GetSearchCaution		){GetSearchCaution		= "";}
					if(null==GetSearchCautionTiming	){GetSearchCautionTiming= "";}
					if(null==GetSearchClGpCD		){GetSearchClGpCD		= "";}
					if(null==GetSearchDECD			){GetSearchDECD			= "";}
					if(null==GetSearchDepartmentCd	){GetSearchDepartmentCd	= "";}
					if(null==GetSearchDeName		){GetSearchDeName		= "";}
					
					GetSearchCautionCd		= B00020ToolsTextControl.Trim(GetSearchCautionCd);
					GetSearchCautionName	= B00020ToolsTextControl.Trim(GetSearchCautionName);
					GetSearchCaution		= B00020ToolsTextControl.Trim(GetSearchCaution);
					GetSearchCautionTiming	= B00020ToolsTextControl.Trim(GetSearchCautionTiming);
					GetSearchClGpCD			= B00020ToolsTextControl.Trim(GetSearchClGpCD);
					GetSearchDECD			= B00020ToolsTextControl.Trim(GetSearchDECD);
					GetSearchDepartmentCd	= B00020ToolsTextControl.Trim(GetSearchDepartmentCd);
					GetSearchDeName			= B00020ToolsTextControl.Trim(GetSearchDeName);
					
					ArrayList<String> SearchCautionCd = new ArrayList<String>();
					ArrayList<String> SearchClGpCD = new ArrayList<String>();
					ArrayList<String> SearchDECD = new ArrayList<String>();
					ArrayList<String> SearchDepartmentCd = new ArrayList<String>();
					ArrayList<String> SearchCautionTiming = new ArrayList<String>();
					ArrayList<String> SearchCautionName = new ArrayList<String>();
					ArrayList<String> SearchCaution = new ArrayList<String>();
					ArrayList<String> SearchDeName = new ArrayList<String>();
					boolean AllSearch = true;
					
					if(!"".equals(GetSearchCautionCd		)){SearchCautionCd.add(		GetSearchCautionCd);}
					if(!"".equals(GetSearchCautionName		)){SearchCautionName.add(	GetSearchCautionName);}
					if(!"".equals(GetSearchCaution			)){SearchCaution.add(		GetSearchCaution);}
					if(!"".equals(GetSearchCautionTiming	)){SearchCautionTiming.add(	GetSearchCautionTiming);}
					if(!"".equals(GetSearchClGpCD			)){SearchClGpCD.add(		GetSearchClGpCD);}
					if(!"".equals(GetSearchDECD				)){SearchDECD.add(			GetSearchDECD);}
					if(!"".equals(GetSearchDepartmentCd		)){SearchDepartmentCd.add(	GetSearchDepartmentCd);}
					if(!"".equals(GetSearchDeName			)){SearchDeName.add(		GetSearchDeName);}
					
					Object[][] CautionMstRt = M00042CautionMstRt.CautionMstRt(
							SearchCautionCd,
							SearchClGpCD,
							SearchDECD,
							SearchDepartmentCd,
							SearchCautionTiming,
							SearchCautionName,
							SearchCaution,
							SearchDeName,
							AllSearch);
					
					for(int i=0;i<CautionMstRt.length;i++) {
						Object[] SetOb = new Object[CautionMstRt[i].length+1];
						SetOb[0] = false;
						for(int i01=0;i01<CautionMstRt[i].length;i01++) {
							SetOb[i01+1] = ""+CautionMstRt[i][i01];
						}
						tableModel_ms01.addRow(SetOb);
					}
					if(0<CautionMstRt.length) {
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
					String TgtCautionCd = "";
					String TgtDECD = "";
					String TgtDepartmentCd = "";
					
					for(int i=0;i<RowCount;i++) {
						if((boolean)tableModel_ms01.getValueAt(i, 0)) {
							TgtCautionCd 	= ""+tableModel_ms01.getValueAt(i, 1);
							TgtDECD 		= ""+tableModel_ms01.getValueAt(i, 4);
							TgtDepartmentCd	= ""+tableModel_ms01.getValueAt(i, 5);
						}
					}
					if(!"".equals(TgtCautionCd) && !"".equals(TgtDECD)) {
						SetX=main_fm.getX();
						SetY=main_fm.getY();
		
						main_fm.setVisible(false);
						main_fm.dispose();
						
						WM00071CautionMstRenewAndCreate.CautionMstRenewAndCreate(0,0,TgtCautionCd,TgtDECD,TgtDepartmentCd);
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
					
					WM00071CautionMstRenewAndCreate.CautionMstRenewAndCreate(0,0,"","","");
					RenewFg = true;
				}
			}
		});
		
		//削除ボタン押下時の挙動
		DeleteBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					int RowCount = tableModel_ms01.getRowCount();
					String TgtCautionCd = "";
					String TgtDECD = "";
					String TgtDepartmentCd = "";
					int RemoveTgt = -1;
					
					for(int i=0;i<RowCount;i++) {
						if((boolean)tableModel_ms01.getValueAt(i, 0)) {
							RemoveTgt = i;
						}
					}
					if(0<=RemoveTgt) {
						TgtCautionCd 	= ""+tableModel_ms01.getValueAt(RemoveTgt, 1);
						TgtDECD 		= ""+tableModel_ms01.getValueAt(RemoveTgt, 4);
						TgtDepartmentCd	= ""+tableModel_ms01.getValueAt(RemoveTgt, 5);
						tableModel_ms01.removeRow(RemoveTgt);
						
						String tgt_table = "KM0090_CAUTION";
						String[] judg_field = new String[3];
						String[][] judg_data = new String[1][3];
						String TgtDB = "NYANKO";
						
						judg_field[0] = "CautionCd";	//注意事項コード
						judg_field[1] = "DECD";			//運送タイプコード
						judg_field[2] = "DepartmentCd";	//部署CD
		
						judg_data[0][0] = TgtCautionCd;		//注意事項コード
						judg_data[0][1] = TgtDECD;			//届先コード
						judg_data[0][2] = TgtDepartmentCd;	//部署CD
						
						A00030DeleteSQL.DeleteSql(tgt_table,judg_field,judg_data,TgtDB);
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
					B10010TableControl.TableOutPutCsv("出力先選択","届先注意事項マスタ検索結果",tb01);
					RenewFg = true;
				}
			}
		});
		
		//エクセル出力ボタン押下時の挙動
		ExcelBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					B10010TableControl.TableOutPutExcel("出力先選択","届先注意事項マスタ検索結果",tb01);
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