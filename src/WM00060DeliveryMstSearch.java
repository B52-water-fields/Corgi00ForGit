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

public class WM00060DeliveryMstSearch{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	
	static String[][] PrefecturesCdList;
	static String[][] MunicipalityCd;
	
	public static void DeliveryMstSearch(int x,int y) {
		A00000Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		
		ArrayList<String> SearchName = new ArrayList<String>();
		ArrayList<String> SearchMunicipalityCd = new ArrayList<String>();
		boolean AllSearch = true;
		Object[][] PrefecuturesRt = M10010PostMstRt.PrefecuturesRt();
		Object[][] MunicipalityRt = M10010PostMstRt.MunicipalityRt(SearchName,SearchMunicipalityCd,AllSearch);
		
		PrefecturesCdList = new String[3][PrefecuturesRt.length+1];
		MunicipalityCd = new String[3][MunicipalityRt.length+1];
		
		PrefecturesCdList[0][0] = "未指定";
		MunicipalityCd[0][0] = "未指定";
		
		PrefecturesCdList[1][0] = "";
		MunicipalityCd[1][0] = "";
		
		PrefecturesCdList[2][0] = "";
		MunicipalityCd[2][0] = "";
		
		for(int i=0;i<PrefecuturesRt.length;i++) {
			PrefecturesCdList[0][i+1] = PrefecuturesRt[i][0]+":"+PrefecuturesRt[i][1];
			PrefecturesCdList[1][i+1] = PrefecuturesRt[i][0]+"";	
			PrefecturesCdList[2][i+1] = ""+PrefecuturesRt[i][1];
		}
		for(int i=0;i<MunicipalityRt.length;i++) {
			MunicipalityCd[0][i+1] = MunicipalityRt[i][2]+":"+MunicipalityRt[i][0]+MunicipalityRt[i][1];
			MunicipalityCd[1][i+1] = MunicipalityRt[i][2]+"";
			MunicipalityCd[2][i+1] = ""+MunicipalityRt[i][0]+MunicipalityRt[i][1];
		}
		
		final JFrame main_fm = B00110FrameParts.FrameCreate(x,y,900,750,"Corgi00届先マスタ検索","");
		JLabel userinfo = B00110FrameParts.UserInfo();
		JButton exit_btn = B00110FrameParts.ExitBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		
		//検索条件パネル
		JPanel PN_Search = B00110FrameParts.JPanelSet(10,40,870,160,"White");
		JLabel PN_SearchLabel = B00110FrameParts.JLabelSet(10,0,150,20,"検索条件",11,0);
		PN_Search.add(PN_SearchLabel);
		main_fm.add(PN_Search);
		
		JLabel LB_SearchDECD			= B00110FrameParts.JLabelSet(  0, 25,100,20,"届先CD:"			,11,1);
		JLabel LB_SearchDepartmentCd	= B00110FrameParts.JLabelSet(  0, 50,100,20,"届先部署CD:"		,11,1);
		JLabel LB_SearchDEName			= B00110FrameParts.JLabelSet(  0, 75,100,20,"届先名:"			,11,1);
		JLabel LB_SearchPost			= B00110FrameParts.JLabelSet(  0,100,100,20,"届先郵便:"		,11,1);
		JLabel LB_SearchAdd				= B00110FrameParts.JLabelSet(  0,125,100,20,"届先住所:"		,11,1);
		JLabel LB_SearchTel				= B00110FrameParts.JLabelSet(250, 25,100,20,"届先TEL:"			,11,1);
		JLabel LB_SearchFax				= B00110FrameParts.JLabelSet(250, 50,100,20,"届先FAX:"			,11,1);
		JLabel LB_SearchMail			= B00110FrameParts.JLabelSet(250, 75,100,20,"届先MAIL:"		,11,1);
		JLabel LB_SearchCom				= B00110FrameParts.JLabelSet(250,100,100,20,"届先コメント:"	,11,1);
		JLabel LB_SearchPrefecturesCd	= B00110FrameParts.JLabelSet(500, 25,100,20,"届先県CD:"		,11,1);
		JLabel LB_SearchMunicipalityCd	= B00110FrameParts.JLabelSet(500, 50,100,20,"届先市区町村CD:"	,11,1);
		JLabel LB_SearchDelFg			= B00110FrameParts.JLabelSet(500, 75,100,20,"削除区分:"		,11,1);
		
		final JTextField  TB_SearchDECD				= B00110FrameParts.JTextFieldSet(100, 25,100,20,"",11,0);			//届先CD
		final JTextField  TB_SearchDepartmentCd		= B00110FrameParts.JTextFieldSet(100, 50,100,20,"",11,0);			//届先部署CD
		final JTextField  TB_SearchDEName			= B00110FrameParts.JTextFieldSet(100, 75,100,20,"",11,0);			//届先名
		final JTextField  TB_SearchPost				= B00110FrameParts.JTextFieldSet(100,100,100,20,"",11,0);			//届先郵便
		final JTextField  TB_SearchAdd				= B00110FrameParts.JTextFieldSet(100,125,100,20,"",11,0);			//届先住所
		final JTextField  TB_SearchTel				= B00110FrameParts.JTextFieldSet(350, 25,100,20,"",11,0);			//届先TEL
		final JTextField  TB_SearchFax				= B00110FrameParts.JTextFieldSet(350, 50,100,20,"",11,0);			//届先FAX
		final JTextField  TB_SearchMail				= B00110FrameParts.JTextFieldSet(350, 75,100,20,"",11,0);			//届先MAIL
		final JTextField  TB_SearchCom				= B00110FrameParts.JTextFieldSet(350,100,100,20,"",11,0);			//届先コメント
		final JComboBox   TB_SearchPrefecturesCd	= B00110FrameParts.JComboBoxSet( 600, 25,100,20,PrefecturesCdList[0],11);					//届先県CD
		final JComboBox   TB_SearchMunicipalityCd	= B00110FrameParts.JComboBoxSet( 600, 50,200,20,MunicipalityCd[0],11);						//届先市区町村CD
		final JComboBox   TB_SearchDelFg			= B00110FrameParts.JComboBoxSet( 600, 75,100,20,B00100DefaultVariable.SearchDelList[0],11);	//削除区分
		
		JLabel LB2_SearchDECD			= B00110FrameParts.JLabelSet(200, 25, 50,20,"と一致"		,11,0);
		JLabel LB2_SearchDepartmentCd	= B00110FrameParts.JLabelSet(200, 50, 50,20,"と一致"		,11,0);
		JLabel LB2_SearchDEName			= B00110FrameParts.JLabelSet(200, 75, 50,20,"を含む"		,11,0);
		JLabel LB2_SearchPost			= B00110FrameParts.JLabelSet(200,100, 50,20,"で始まる"		,11,0);
		JLabel LB2_SearchAdd			= B00110FrameParts.JLabelSet(200,125, 50,20,"を含む"		,11,0);
		JLabel LB2_SearchTel			= B00110FrameParts.JLabelSet(450, 25, 50,20,"を含む"		,11,0);
		JLabel LB2_SearchFax			= B00110FrameParts.JLabelSet(450, 50, 50,20,"を含む"		,11,0);
		JLabel LB2_SearchMail			= B00110FrameParts.JLabelSet(450, 75, 50,20,"を含む"		,11,0);
		JLabel LB2_SearchCom			= B00110FrameParts.JLabelSet(450,100, 50,20,"を含む"		,11,0);
		JLabel LB2_SearchPrefecturesCd	= B00110FrameParts.JLabelSet(700, 25, 50,20,""				,11,0);
		JLabel LB2_SearchMunicipalityCd	= B00110FrameParts.JLabelSet(800, 50, 50,20,""				,11,0);
		JLabel LB2_SearchDelFg			= B00110FrameParts.JLabelSet(700, 75, 50,20,""				,11,0);

		PN_Search.add(LB_SearchDECD);
		PN_Search.add(LB_SearchDepartmentCd);
		PN_Search.add(LB_SearchDEName);
		PN_Search.add(LB_SearchPost);
		PN_Search.add(LB_SearchAdd);
		PN_Search.add(LB_SearchTel);
		PN_Search.add(LB_SearchFax);
		PN_Search.add(LB_SearchMail);
		PN_Search.add(LB_SearchCom);
		PN_Search.add(LB_SearchPrefecturesCd);
		PN_Search.add(LB_SearchMunicipalityCd);
		PN_Search.add(LB_SearchDelFg);
		
		PN_Search.add(TB_SearchDECD);
		PN_Search.add(TB_SearchDepartmentCd);
		PN_Search.add(TB_SearchDEName);
		PN_Search.add(TB_SearchPost);
		PN_Search.add(TB_SearchAdd);
		PN_Search.add(TB_SearchTel);
		PN_Search.add(TB_SearchFax);
		PN_Search.add(TB_SearchMail);
		PN_Search.add(TB_SearchCom);
		PN_Search.add(TB_SearchPrefecturesCd);
		PN_Search.add(TB_SearchMunicipalityCd);
		PN_Search.add(TB_SearchDelFg);
		
		PN_Search.add(LB2_SearchDECD);
		PN_Search.add(LB2_SearchDepartmentCd);
		PN_Search.add(LB2_SearchDEName);
		PN_Search.add(LB2_SearchPost);
		PN_Search.add(LB2_SearchAdd);
		PN_Search.add(LB2_SearchTel);
		PN_Search.add(LB2_SearchFax);
		PN_Search.add(LB2_SearchMail);
		PN_Search.add(LB2_SearchCom);
		PN_Search.add(LB2_SearchPrefecturesCd);
		PN_Search.add(LB2_SearchMunicipalityCd);
		PN_Search.add(LB2_SearchDelFg);

		//検索ボタン
		JButton SearchBtn = B00110FrameParts.BtnSet(600,125,100,20,"検索",11);
		PN_Search.add(SearchBtn);
		
		Object[][] RtSettingCarMstRt = M00040DeliveryMstRt.RtSettingDeliveryMstRt();
		
		String[] columnNames01 = new String[RtSettingCarMstRt.length+1];
		
		columnNames01[0] = "Fg";
		for(int i=0;i<RtSettingCarMstRt.length;i++) {
			columnNames01[1+i] = ""+RtSettingCarMstRt[i][3];
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
		
		for(int i=0;i<RtSettingCarMstRt.length;i++) {
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
					
					String GetSearchDECD 			= TB_SearchDECD.getText();			//届先CD
					String GetSearchDepartmentCd 	= TB_SearchDepartmentCd.getText();	//届先部署CD
					String GetSearchDEName 			= TB_SearchDEName.getText();		//届先名
					String GetSearchPost	 		= TB_SearchPost.getText();			//届先郵便
					String GetSearchAdd 			= TB_SearchAdd.getText();			//届先住所
					String GetSearchTel 			= TB_SearchTel.getText();			//届先TEL
					String GetSearchFax 			= TB_SearchFax.getText();			//届先FAX
					String GetSearchMail 			= TB_SearchMail.getText();			//届先MAIL
					String GetSearchCom 			= TB_SearchCom.getText();			//届先コメント
					String GetSearchPrefecturesCd 	= PrefecturesCdList[1][TB_SearchPrefecturesCd.getSelectedIndex()];			//届先県CD
					String GetSearchMunicipalityCd 	= MunicipalityCd[1][TB_SearchMunicipalityCd.getSelectedIndex()];				//届先市区町村CD
					String GetSearchDelFg 			= B00100DefaultVariable.SearchDelList[1][TB_SearchDelFg.getSelectedIndex()];	//削除区分

					if(null==GetSearchDECD			){GetSearchDECD 			= "";}
					if(null==GetSearchDepartmentCd	){GetSearchDepartmentCd 	= "";}
					if(null==GetSearchDEName		){GetSearchDEName 			= "";}
					if(null==GetSearchPost			){GetSearchPost 			= "";}
					if(null==GetSearchAdd			){GetSearchAdd 				= "";}
					if(null==GetSearchTel			){GetSearchTel 				= "";}
					if(null==GetSearchFax			){GetSearchFax 				= "";}
					if(null==GetSearchMail			){GetSearchMail 			= "";}
					if(null==GetSearchCom 			){GetSearchCom 				= "";}
					if(null==GetSearchPrefecturesCd	){GetSearchPrefecturesCd 	= "";}
					if(null==GetSearchMunicipalityCd){GetSearchMunicipalityCd 	= "";}
					if(null==GetSearchDelFg			){GetSearchDelFg 			= "";}
					
					GetSearchDECD			= B00020ToolsTextControl.Trim(GetSearchDECD);
					GetSearchDepartmentCd	= B00020ToolsTextControl.Trim(GetSearchDepartmentCd);
					GetSearchDEName			= B00020ToolsTextControl.Trim(GetSearchDEName);
					GetSearchPost			= B00020ToolsTextControl.Trim(GetSearchPost);
					GetSearchAdd			= B00020ToolsTextControl.Trim(GetSearchAdd);
					GetSearchTel			= B00020ToolsTextControl.Trim(GetSearchTel);
					GetSearchFax			= B00020ToolsTextControl.Trim(GetSearchFax);
					GetSearchMail			= B00020ToolsTextControl.Trim(GetSearchMail);
					GetSearchCom			= B00020ToolsTextControl.Trim(GetSearchCom);
					GetSearchPrefecturesCd	= B00020ToolsTextControl.Trim(GetSearchPrefecturesCd);
					GetSearchMunicipalityCd	= B00020ToolsTextControl.Trim(GetSearchMunicipalityCd);
					GetSearchDelFg			= B00020ToolsTextControl.Trim(GetSearchDelFg);
					
					GetSearchPost			= B00020ToolsTextControl.num_only_String(GetSearchPost);
					GetSearchTel			= B00020ToolsTextControl.num_only_String(GetSearchTel);
					GetSearchFax			= B00020ToolsTextControl.num_only_String(GetSearchFax);

					ArrayList<String> SearchDECD = new ArrayList<String>();
					ArrayList<String> SearchDepartmentCd = new ArrayList<String>();
					ArrayList<String> SearchDEName = new ArrayList<String>();
					ArrayList<String> SearchPost = new ArrayList<String>();
					ArrayList<String> SearchAdd = new ArrayList<String>();
					ArrayList<String> SearchTel = new ArrayList<String>();
					ArrayList<String> SearchFax = new ArrayList<String>();
					ArrayList<String> SearchMail = new ArrayList<String>();
					ArrayList<String> SearchCom = new ArrayList<String>();
					ArrayList<String> SearchPrefecturesCd = new ArrayList<String>();
					ArrayList<String> SearchMunicipalityCd = new ArrayList<String>();
					ArrayList<String> SearchDelFg = new ArrayList<String>();
					boolean AllSearch = true;
					
					if(!"".equals(GetSearchDECD)){SearchDECD.add(GetSearchDECD);}
					if(!"".equals(GetSearchDepartmentCd)){SearchDepartmentCd.add(GetSearchDepartmentCd);}
					if(!"".equals(GetSearchDEName)){SearchDEName.add(GetSearchDEName);}
					if(!"".equals(GetSearchPost)){SearchPost.add(GetSearchPost);}
					if(!"".equals(GetSearchAdd)){SearchAdd.add(GetSearchAdd);}
					if(!"".equals(GetSearchTel)){SearchTel.add(GetSearchTel);}
					if(!"".equals(GetSearchFax)){SearchFax.add(GetSearchFax);}
					if(!"".equals(GetSearchMail)){SearchMail.add(GetSearchMail);}
					if(!"".equals(GetSearchCom)){SearchCom.add(GetSearchCom);}
					if(!"".equals(GetSearchPrefecturesCd)){SearchPrefecturesCd.add(GetSearchPrefecturesCd);}
					if(!"".equals(GetSearchMunicipalityCd)){SearchMunicipalityCd.add(GetSearchMunicipalityCd);}
					if(!"".equals(GetSearchDelFg)){SearchDelFg.add(GetSearchDelFg);}
					
					Object[][] DeliveryMstRt = M00040DeliveryMstRt.DeliveryMstRt(
							SearchDECD,				//検索条件届先CD
							SearchDepartmentCd,		//検索条件届先部署CD
							SearchDEName,			//検索条件届先名
							SearchPost,				//検索条件届先郵便
							SearchAdd,				//検索条件届先住所
							SearchTel,				//検索条件届先TEL
							SearchFax,				//検索条件届先FAX
							SearchMail,				//検索条件届先MAIL
							SearchCom,				//検索条件届先コメント
							SearchPrefecturesCd,	//検索条件届先県CD
							SearchMunicipalityCd,	//検索条件届先市区町村CD
							SearchDelFg,			//検索条件削除区分
							AllSearch
							);
					
					
					for(int i=0;i<DeliveryMstRt.length;i++) {
						Object[] SetOb= new Object[DeliveryMstRt[i].length+1];
						SetOb[0] = false;
						for(int i01=0;i01<DeliveryMstRt[i].length;i01++) {
							SetOb[i01+1] = DeliveryMstRt[i][i01];
						}
						tableModel_ms01.addRow(SetOb);
					}
					if(0<DeliveryMstRt.length) {
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
					String TgtDECD = "";
					String TgtDepartmentCd = "";
					
					for(int i=0;i<RowCount;i++) {
						if((boolean)tableModel_ms01.getValueAt(i, 0)) {
							TgtDECD 		= ""+tableModel_ms01.getValueAt(i, 1);	if(null==TgtDECD	) {TgtDECD="";}
							TgtDepartmentCd = ""+tableModel_ms01.getValueAt(i, 2);	if(null==TgtDepartmentCd	) {TgtDepartmentCd="";}
						}
					}
					if(!"".equals(TgtDECD) && !"".equals(TgtDepartmentCd)) {
						SetX=main_fm.getX();
						SetY=main_fm.getY();

						main_fm.setVisible(false);
						main_fm.dispose();
						WM00061DeliveryMstRenewAndCreate.DeliveryMstRenewAndCreate(0,0,TgtDECD,TgtDepartmentCd);
					}
					RenewFg = true;
				}
			}
		});
		
		//新規登録ボタン
		CreateBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				WM00061DeliveryMstRenewAndCreate.DeliveryMstRenewAndCreate(0,0,"","");
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
					B10010TableControl.TableOutPutCsv("出力先選択","届先マスタ検索結果",tb01);
					RenewFg = true;
				}
			}
		});
		
		//エクセル出力ボタン押下時の挙動
		ExcelBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					B10010TableControl.TableOutPutExcel("出力先選択","届先マスタ検索結果",tb01);
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
					String Selected = B00090FileSelect.FileSelect(MSG,file_type,file_type_name);
					
					if(null!=Selected && !Selected.equals(Selected.replace(".xlsx", ""))) {
						SetX=main_fm.getX();
						SetY=main_fm.getY();

						main_fm.setVisible(false);
						main_fm.dispose();
						WM00062DeliveryMstExcelEntry.DeliveryMstExcelEntry(0,0,Selected);
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