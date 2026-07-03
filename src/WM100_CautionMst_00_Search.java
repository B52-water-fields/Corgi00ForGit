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

public class WM100_CautionMst_00_Search{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	public static void CautionMstSearch(int x,int y) {
		A00000_Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		final JFrame main_fm = B100_FrameParts.FrameCreate(x,y,900,750,"Corgi00届先注意事項マスタ検索","");
		JLabel userinfo = B100_FrameParts.UserInfo();
		JButton exit_btn = B100_FrameParts.ExitBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		
		//検索条件パネル
		JPanel PN_Search = B100_FrameParts.JPanelSet(			 10, 40,870,160,"White");
		JLabel PN_SearchLabel = B100_FrameParts.JLabelSet(	 10,  0,150,20,"検索条件",11,0);
		PN_Search.add(PN_SearchLabel);
		main_fm.add(PN_Search);
		
		JLabel LB_SearchCautionCd		= B100_FrameParts.JLabelSet(  0, 25,100,20,"注意事項CD:"		,11,1);
		JLabel LB_SearchCautionName		= B100_FrameParts.JLabelSet(  0, 50,100,20,"注意事項名:"		,11,1);
		JLabel LB_SearchCaution			= B100_FrameParts.JLabelSet(  0, 75,100,20,"注意事項内容:"	,11,1);
		JLabel LB_SearchCautionTiming	= B100_FrameParts.JLabelSet(  0,100,100,20,"注意タイミング:"	,11,1);
		
		JLabel LB_SearchClGpCD			= B100_FrameParts.JLabelSet(250, 25,100,20,"荷主グループCD:"	,11,1);
		JLabel LB_SearchDECD			= B100_FrameParts.JLabelSet(250, 50,100,20,"届先CD:"			,11,1);
		JLabel LB_SearchDepartmentCd	= B100_FrameParts.JLabelSet(250, 75,100,20,"届先部署CD:"		,11,1);
		JLabel LB_SearchDeName			= B100_FrameParts.JLabelSet(250,100,100,20,"届先名:"			,11,1);
		
		final JTextField  TB_SearchCautionCd		= B100_FrameParts.JTextFieldSet(100, 25,100,20,"",11,0);			//注意事項CD
		final JTextField  TB_SearchCautionName		= B100_FrameParts.JTextFieldSet(100, 50,100,20,"",11,0);			//注意事項名
		final JTextField  TB_SearchCaution			= B100_FrameParts.JTextFieldSet(100, 75,100,20,"",11,0);			//注意事項内容
		final JComboBox   TB_SearchCautionTiming	= B100_FrameParts.JComboBoxSet( 100,100,100,20,B100_DefaultVariable.SearchCautionTiming[0],11);	//注意タイミング
		
		final JTextField  TB_SearchClGpCD			= B100_FrameParts.JTextFieldSet(350, 25,100,20,"",11,0);			//荷主グループCD
		final JTextField  TB_SearchDECD				= B100_FrameParts.JTextFieldSet(350, 50,100,20,"",11,0);			//届先CD
		final JTextField  TB_SearchDepartmentCd		= B100_FrameParts.JTextFieldSet(350, 75,100,20,"",11,0);			//届先部署CD
		final JTextField  TB_SearchDeName			= B100_FrameParts.JTextFieldSet(350,100,100,20,"",11,0);			//届先名
		
		JLabel LB2_SearchCautionCd		= B100_FrameParts.JLabelSet(200, 25, 50,20,"と一致"	,11,0);
		JLabel LB2_SearchCautionName	= B100_FrameParts.JLabelSet(200, 50, 50,20,"を含む"	,11,0);
		JLabel LB2_SearchCaution		= B100_FrameParts.JLabelSet(200, 75, 50,20,"を含む"	,11,0);
		JLabel LB2_SearchCautionTiming	= B100_FrameParts.JLabelSet(200,100, 50,20,""			,11,0);
		
		JLabel LB2_SearchClGpCD			= B100_FrameParts.JLabelSet(450, 25, 50,20,"と一致"	,11,0);
		JLabel LB2_SearchDECD			= B100_FrameParts.JLabelSet(450, 50, 50,20,"と一致"	,11,0);
		JLabel LB2_SearchDepartmentCd	= B100_FrameParts.JLabelSet(450, 75, 50,20,"と一致"	,11,0);
		JLabel LB2_SearchDeName			= B100_FrameParts.JLabelSet(450,100, 50,20,"を含む"	,11,0);

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
		JButton SearchBtn = B100_FrameParts.BtnSet(350,125,100,20,"検索",11);
		PN_Search.add(SearchBtn);
		
		Object[][] RtSettingCautionMstRt = M100_CautionMstRt.RtSettingCautionMstRt();
		
		String[] columnNames01 = new String[RtSettingCautionMstRt.length+1];
		
		columnNames01[0] = "Fg";
		for(int i=0;i<RtSettingCautionMstRt.length;i++) {
			columnNames01[1+(int)RtSettingCautionMstRt[i][1]] = ""+RtSettingCautionMstRt[i][3];
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
		
		for(int i=0;i<RtSettingCautionMstRt.length;i++) {
			if("int".equals((String)RtSettingCautionMstRt[i][2])||"float".equals((String)RtSettingCautionMstRt[i][2])) {
				column = columnModel01.getColumn(1+(int)RtSettingCautionMstRt[i][1]);	column.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.rightCellRenderer());
			}else {
				column = columnModel01.getColumn(1+(int)RtSettingCautionMstRt[i][1]);	column.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.leftCellRenderer());
			}
		}
		
		//スクロール用設定
		JScrollPane scpn01 = B100_FrameParts.JScrollPaneSet(10,210,870,395,tb01);
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
		
		JLabel LB_DeleteBtn  = B100_FrameParts.JLabelSet(	610,640,100,20,"チェック行を" ,11,2);
		main_fm.add(LB_DeleteBtn);
		
		//削除ボタン
		JButton DeleteBtn = B100_FrameParts.BtnSet(	610,660,100,20,"削除",11);
		main_fm.add(DeleteBtn);
		
		main_fm.setVisible(true);
		RenewFg = true;
		

		//検索ボタン押下時の挙動
		SearchBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					
					int RowCount = MainFmTableModel.getRowCount();
					for(int i=0;i<RowCount;i++) {
						MainFmTableModel.removeRow(0);
					}
					
					String GetSearchCautionCd = TB_SearchCautionCd.getText();		//注意事項CD
					String GetSearchCautionName = TB_SearchCautionName.getText();	//注意事項名
					String GetSearchCaution = TB_SearchCaution.getText();			//注意事項内容
					String GetSearchCautionTiming = B100_DefaultVariable.SearchCautionTiming[1][TB_SearchCautionTiming.getSelectedIndex()];	//注意タイミング
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
					
					GetSearchCautionCd		= B100_TextControl.Trim(GetSearchCautionCd);
					GetSearchCautionName	= B100_TextControl.Trim(GetSearchCautionName);
					GetSearchCaution		= B100_TextControl.Trim(GetSearchCaution);
					GetSearchCautionTiming	= B100_TextControl.Trim(GetSearchCautionTiming);
					GetSearchClGpCD			= B100_TextControl.Trim(GetSearchClGpCD);
					GetSearchDECD			= B100_TextControl.Trim(GetSearchDECD);
					GetSearchDepartmentCd	= B100_TextControl.Trim(GetSearchDepartmentCd);
					GetSearchDeName			= B100_TextControl.Trim(GetSearchDeName);
					
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
					
					Object[][] CautionMstRt = M100_CautionMstRt.CautionMstRt(
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
						MainFmTableModel.addRow(SetOb);
					}
					if(0<CautionMstRt.length) {
						B100_TableControl.AddSortON(tb01,MainFmTableModel);
					}else {
						B100_TableControl.AddSortOFF(tb01,MainFmTableModel);
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
					int RowCount = MainFmTableModel.getRowCount();
					String TgtCautionCd = "";
					String TgtDECD = "";
					String TgtDepartmentCd = "";
					
					for(int i=0;i<RowCount;i++) {
						if((boolean)MainFmTableModel.getValueAt(i, 0)) {
							TgtCautionCd 	= ""+MainFmTableModel.getValueAt(i,M100_CautionMstRt.ColCautionCd+1);
							TgtDECD 		= ""+MainFmTableModel.getValueAt(i,M100_CautionMstRt.ColDECD+1);
							TgtDepartmentCd	= ""+MainFmTableModel.getValueAt(i,M100_CautionMstRt.ColDepartmentCd+1);
						}
					}
					if(!"".equals(TgtCautionCd) && !"".equals(TgtDECD)) {
						SetX=main_fm.getX();
						SetY=main_fm.getY();
		
						main_fm.setVisible(false);
						main_fm.dispose();
						
						WM100_CautionMst_01_RenewAndCreate.CautionMstRenewAndCreate(0,0,TgtCautionCd,TgtDECD,TgtDepartmentCd);
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
					
					WM100_CautionMst_01_RenewAndCreate.CautionMstRenewAndCreate(0,0,"","","");
					RenewFg = true;
				}
			}
		});
		
		//削除ボタン押下時の挙動
		DeleteBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					int RowCount = MainFmTableModel.getRowCount();
					String TgtCautionCd = "";
					String TgtDECD = "";
					String TgtDepartmentCd = "";
					int RemoveTgt = -1;
					
					for(int i=0;i<RowCount;i++) {
						if((boolean)MainFmTableModel.getValueAt(i, 0)) {
							RemoveTgt = i;
						}
					}
					if(0<=RemoveTgt) {
						TgtCautionCd 	= ""+MainFmTableModel.getValueAt(RemoveTgt, 1);
						TgtDECD 		= ""+MainFmTableModel.getValueAt(RemoveTgt, 4);
						TgtDepartmentCd	= ""+MainFmTableModel.getValueAt(RemoveTgt, 5);
						MainFmTableModel.removeRow(RemoveTgt);
						
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
						
						A100_DeleteSQL.DeleteSql(tgt_table,judg_field,judg_data,TgtDB);
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
					B100_TableControl.TableOutPutCsv("出力先選択","届先注意事項マスタ検索結果",tb01);
					RenewFg = true;
				}
			}
		});
		
		//エクセル出力ボタン押下時の挙動
		ExcelBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					B100_TableControl.TableOutPutExcel("出力先選択","届先注意事項マスタ検索結果",tb01);
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