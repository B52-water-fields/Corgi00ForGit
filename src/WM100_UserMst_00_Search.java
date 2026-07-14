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

public class WM100_UserMst_00_Search{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	public static void UserMstSearch(int x,int y) {
		A00000_Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		
		final JFrame main_fm = B100_FrameParts.FrameCreate(x,y,860,750,"Corgi00ユーザー検索　WM100_UserMst_00_Search","");
		JLabel userinfo = B100_FrameParts.UserInfo();
		JButton exit_btn = B100_FrameParts.ExitBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		
		//検索条件パネル
		JPanel PN_Search = B100_FrameParts.JPanelSet(10,40,820,180,"White");
		JLabel PN_SearchLabel = B100_FrameParts.JLabelSet(10,0,150,20,"検索条件",11,0);
		PN_Search.add(PN_SearchLabel);
		main_fm.add(PN_Search);

		JLabel LB_SearchWHCD				= B100_FrameParts.JLabelSet(  0, 25,100,20,"所属倉庫:",	11,1);
		JLabel LB_SearchShippingCompanyCd	= B100_FrameParts.JLabelSet(  0, 50,100,20,"所属会社:",	11,1);
		JLabel LB_SearchUserCd				= B100_FrameParts.JLabelSet(  0, 75,100,20,"ユーザーCD:",	11,1);
		JLabel LB_SearchUserName			= B100_FrameParts.JLabelSet(  0,100,100,20,"ユーザー名:",	11,1);
		JLabel LB_SearchAuthorityFG			= B100_FrameParts.JLabelSet(  0,125,100,20,"権限:",		11,1);
		JLabel LB_SearchDelFg				= B100_FrameParts.JLabelSet(  0,150,100,20,"削除区分:",	11,1);
		
		JLabel LB_SearchCarCd				= B100_FrameParts.JLabelSet(300, 25,100,20,"乗務車輛CD:",	11,1);
		JLabel LB_SearchCarName				= B100_FrameParts.JLabelSet(300, 50,100,20,"乗務車輛名:",	11,1);
		JLabel LB_SearchPost				= B100_FrameParts.JLabelSet(300, 75,100,20,"郵便番号:",	11,1);
		JLabel LB_SearchAdd					= B100_FrameParts.JLabelSet(300,100,100,20,"住所:",		11,1);
		
		JLabel LB_SearchTel					= B100_FrameParts.JLabelSet(550, 25,100,20,"Tel:",		11,1);
		JLabel LB_SearchFax					= B100_FrameParts.JLabelSet(550, 50,100,20,"Fax:",		11,1);
		JLabel LB_SearchMail				= B100_FrameParts.JLabelSet(550, 75,100,20,"Mail:",		11,1);
		JLabel LB_SearchCom					= B100_FrameParts.JLabelSet(550,100,100,20,"コメント:",	11,1);
		
		final JComboBox  TB_SearchWHCD				= B100_FrameParts.JComboBoxSet( 100, 25,200,20,B100_DefaultVariable.SearchWhList[0],11);	//所属倉庫
		final JComboBox  TB_SearchShippingCompanyCd	= B100_FrameParts.JComboBoxSet( 100, 50,200,20,B100_DefaultVariable.SearchShippingCompanyList[0],11);	//所属会社
		final JTextField TB_SearchUserCd			= B100_FrameParts.JTextFieldSet(100, 75,100,20,"",11,0);	//ユーザーCD
		final JTextField TB_SearchUserName			= B100_FrameParts.JTextFieldSet(100,100,100,20,"",11,0);	//ユーザー名
		final JComboBox  TB_SearchAuthorityFG		= B100_FrameParts.JComboBoxSet( 100,125,150,20,B100_DefaultVariable.SerachAuthorityFG[0],11);	//権限
		final JComboBox  TB_SearchDelFg				= B100_FrameParts.JComboBoxSet( 100,150,100,20,B100_DefaultVariable.SearchDelList[0],11);	//削除区分
		
		final JTextField TB_SearchCarCd				= B100_FrameParts.JTextFieldSet(400, 25,100,20,"",11,0);	//乗務車輛CD
		final JTextField TB_SearchCarName			= B100_FrameParts.JTextFieldSet(400, 50,100,20,"",11,0);	//乗務車輛名
		final JTextField TB_SearchPost				= B100_FrameParts.JTextFieldSet(400, 75,100,20,"",11,0);	//郵便番号
		final JTextField TB_SearchAdd				= B100_FrameParts.JTextFieldSet(400,100,100,20,"",11,0);	//住所
		
		final JTextField TB_SearchTel				= B100_FrameParts.JTextFieldSet(650, 25,100,20,"",11,0);	//Tel
		final JTextField TB_SearchFax				= B100_FrameParts.JTextFieldSet(650, 50,100,20,"",11,0);	//Fax
		final JTextField TB_SearchMail				= B100_FrameParts.JTextFieldSet(650, 75,100,20,"",11,0);	//Mail
		final JTextField TB_SearchCom				= B100_FrameParts.JTextFieldSet(650,100,100,20,"",11,0);	//コメント
		
		JLabel LB2_SearchWHCD				= B100_FrameParts.JLabelSet(300, 25,100,20,"",				11,0);
		JLabel LB2_SearchShippingCompanyCd	= B100_FrameParts.JLabelSet(300, 50,100,20,"",				11,0);
		JLabel LB2_SearchUserCd				= B100_FrameParts.JLabelSet(200, 75,100,20,"と一致",		11,0);
		JLabel LB2_SearchUserName			= B100_FrameParts.JLabelSet(200,100,100,20,"を含む",		11,0);
		JLabel LB2_SearchAuthorityFG		= B100_FrameParts.JLabelSet(250,125,100,20,"",				11,0);
		JLabel LB2_SearchDelFg				= B100_FrameParts.JLabelSet(200,150,100,20,"",				11,0);
		
		JLabel LB2_SearchCarCd				= B100_FrameParts.JLabelSet(500, 25,100,20,"と一致",		11,0);
		JLabel LB2_SearchCarName			= B100_FrameParts.JLabelSet(500, 50,100,20,"を含む",		11,0);
		JLabel LB2_SearchPost				= B100_FrameParts.JLabelSet(500, 75,100,20,"で始まる",		11,0);
		JLabel LB2_SearchAdd				= B100_FrameParts.JLabelSet(500,100,100,20,"を含む",		11,0);
		
		JLabel LB2_SearchTel				= B100_FrameParts.JLabelSet(750, 25,100,20,"を含む",		11,0);
		JLabel LB2_SearchFax				= B100_FrameParts.JLabelSet(750, 50,100,20,"を含む",		11,0);
		JLabel LB2_SearchMail				= B100_FrameParts.JLabelSet(750, 75,100,20,"を含む",		11,0);
		JLabel LB2_SearchCom				= B100_FrameParts.JLabelSet(750,100,100,20,"を含む",		11,0);
		
		PN_Search.add(LB_SearchWHCD);
		PN_Search.add(LB_SearchShippingCompanyCd);
		PN_Search.add(LB_SearchUserCd);
		PN_Search.add(LB_SearchUserName);
		PN_Search.add(LB_SearchAuthorityFG);
		PN_Search.add(LB_SearchDelFg);
		
		PN_Search.add(LB_SearchCarCd);
		PN_Search.add(LB_SearchCarName);
		PN_Search.add(LB_SearchPost);
		PN_Search.add(LB_SearchAdd);
		
		PN_Search.add(LB_SearchTel);
		PN_Search.add(LB_SearchFax);
		PN_Search.add(LB_SearchMail);
		PN_Search.add(LB_SearchCom);
		
		PN_Search.add(TB_SearchWHCD);
		PN_Search.add(TB_SearchShippingCompanyCd);
		PN_Search.add(TB_SearchUserCd);
		PN_Search.add(TB_SearchUserName);
		PN_Search.add(TB_SearchAuthorityFG);
		PN_Search.add(TB_SearchDelFg);
		
		PN_Search.add(TB_SearchCarCd);
		PN_Search.add(TB_SearchCarName);
		PN_Search.add(TB_SearchPost);
		PN_Search.add(TB_SearchAdd);
		
		PN_Search.add(TB_SearchTel);
		PN_Search.add(TB_SearchFax);
		PN_Search.add(TB_SearchMail);
		PN_Search.add(TB_SearchCom);
		
		PN_Search.add(LB2_SearchWHCD);
		PN_Search.add(LB2_SearchShippingCompanyCd);
		PN_Search.add(LB2_SearchUserCd);
		PN_Search.add(LB2_SearchUserName);
		PN_Search.add(LB2_SearchAuthorityFG);
		PN_Search.add(LB2_SearchDelFg);
		
		PN_Search.add(LB2_SearchCarCd);
		PN_Search.add(LB2_SearchCarName);
		PN_Search.add(LB2_SearchPost);
		PN_Search.add(LB2_SearchAdd);
		
		PN_Search.add(LB2_SearchTel);
		PN_Search.add(LB2_SearchFax);
		PN_Search.add(LB2_SearchMail);
		PN_Search.add(LB2_SearchCom);
		
		//検索ボタン
		JButton SearchBtn = B100_FrameParts.BtnSet(		400,150,100,20,"検索",11);
		PN_Search.add(SearchBtn);
		
		Object[][] RtSettingUserMstRt = M100_UserMstRt.RtUserMstRt();
		
		String[] columnNames01 = new String[RtSettingUserMstRt.length+1];
		
		columnNames01[0] = "Fg";
		for(int i=0;i<RtSettingUserMstRt.length;i++) {
			columnNames01[1+(int)RtSettingUserMstRt[i][1]] = ""+RtSettingUserMstRt[i][3];
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
		
		for(int i=0;i<RtSettingUserMstRt.length;i++) {
			if("int".equals((String)RtSettingUserMstRt[i][2])||"float".equals((String)RtSettingUserMstRt[i][2])) {
				column = columnModel01.getColumn(1+(int)RtSettingUserMstRt[i][1]);	column.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.rightCellRenderer());
			}else {
				column = columnModel01.getColumn(1+(int)RtSettingUserMstRt[i][1]);	column.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.leftCellRenderer());
			}
		}
		
		//スクロール用設定
		JScrollPane scpn01 = B100_FrameParts.JScrollPaneSet(10,230,820,375,tb01);
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
		
		//Excelボタン
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
					
					int RowCount = MainFmTableModel.getRowCount();
					for(int i=0;i<RowCount;i++) {
						MainFmTableModel.removeRow(0);
					}
					
					String GetSearchWHCD				= ""+B100_DefaultVariable.SearchWhList[1][TB_SearchWHCD.getSelectedIndex()];								//所属倉庫
					String GetSearchShippingCompanyCd	= ""+B100_DefaultVariable.SearchShippingCompanyList[1][TB_SearchShippingCompanyCd.getSelectedIndex()];	//所属会社
					String GetSearchUserCd				= TB_SearchUserCd.getText();	//ユーザーCD
					String GetSearchUserName			= TB_SearchUserName.getText();	//ユーザー名
					String GetSearchAuthorityFG			= ""+B100_DefaultVariable.SerachAuthorityFG[1][TB_SearchAuthorityFG.getSelectedIndex()];	//権限
					String GetSearchDelFg				= ""+B100_DefaultVariable.SearchDelList[1][TB_SearchDelFg.getSelectedIndex()];			//削除区分
					String GetSearchCarCd				= TB_SearchCarCd.getText();		//乗務車輛CD
					String GetSearchCarName				= TB_SearchCarName.getText();	//乗務車輛名
					String GetSearchPost				= TB_SearchPost.getText();		//郵便番号
					String GetSearchAdd					= TB_SearchAdd.getText();		//住所
					String GetSearchTel					= TB_SearchTel.getText();		//Tel
					String GetSearchFax					= TB_SearchFax.getText();		//Fax
					String GetSearchMail				= TB_SearchMail.getText();		//Mail
					String GetSearchCom					= TB_SearchCom.getText();		//コメント
					
					if(null==GetSearchWHCD				){GetSearchWHCD = "";}
					if(null==GetSearchShippingCompanyCd	){GetSearchShippingCompanyCd = "";}
					if(null==GetSearchUserCd			){GetSearchUserCd = "";}
					if(null==GetSearchUserName			){GetSearchUserName = "";}
					if(null==GetSearchAuthorityFG		){GetSearchAuthorityFG = "";}
					if(null==GetSearchDelFg				){GetSearchDelFg = "";}
					if(null==GetSearchCarCd				){GetSearchCarCd = "";}
					if(null==GetSearchCarName			){GetSearchCarName = "";}
					if(null==GetSearchPost				){GetSearchPost = "";}
					if(null==GetSearchAdd				){GetSearchAdd = "";}
					if(null==GetSearchTel				){GetSearchTel = "";}
					if(null==GetSearchFax				){GetSearchFax = "";}
					if(null==GetSearchMail				){GetSearchMail = "";}
					if(null==GetSearchCom				){GetSearchCom = "";}
					
					GetSearchPost = B100_TextControl.num_only_String(GetSearchPost);
					GetSearchTel  = B100_TextControl.num_only_String(GetSearchTel);
					GetSearchFax  = B100_TextControl.num_only_String(GetSearchFax);
					

					TB_SearchUserCd.setText(GetSearchUserCd);
					TB_SearchUserName.setText(GetSearchUserName);
					TB_SearchCarCd.setText(GetSearchCarCd);
					TB_SearchCarName.setText(GetSearchCarName);
					TB_SearchPost.setText(GetSearchPost);
					TB_SearchAdd.setText(GetSearchAdd);
					TB_SearchTel.setText(GetSearchTel);
					TB_SearchFax.setText(GetSearchFax);
					TB_SearchMail.setText(GetSearchMail);
					TB_SearchCom.setText(GetSearchCom);
					
					ArrayList<String> SearchWHCD = new ArrayList<String>();
					ArrayList<String> SearchShippingCompanyCd = new ArrayList<String>();
					ArrayList<String> SearchAuthorityFG = new ArrayList<String>();
					ArrayList<String> SearchUserCd = new ArrayList<String>();
					ArrayList<String> SearchUserName = new ArrayList<String>();
					ArrayList<String> SearchCarCd = new ArrayList<String>();
					ArrayList<String> SearchCarName = new ArrayList<String>();
					ArrayList<String> SearchPost = new ArrayList<String>();
					ArrayList<String> SearchAdd = new ArrayList<String>();
					ArrayList<String> SearchTel = new ArrayList<String>();
					ArrayList<String> SearchFax = new ArrayList<String>();
					ArrayList<String> SearchMail = new ArrayList<String>();
					ArrayList<String> SearchCom = new ArrayList<String>();
					ArrayList<String> SearchDelFg = new ArrayList<String>();
					boolean AllSearch = true;
					
					if(!"".equals(GetSearchWHCD					)){SearchWHCD.add(GetSearchWHCD);}
					if(!"".equals(GetSearchShippingCompanyCd	)){SearchShippingCompanyCd.add(GetSearchShippingCompanyCd);}
					if(!"".equals(GetSearchUserCd				)){SearchUserCd.add(GetSearchUserCd);}
					if(!"".equals(GetSearchUserName				)){SearchUserName.add(GetSearchUserName);}
					if(!"".equals(GetSearchAuthorityFG			)){SearchAuthorityFG.add(GetSearchAuthorityFG);}
					if(!"".equals(GetSearchDelFg				)){SearchDelFg.add(GetSearchDelFg);}
					if(!"".equals(GetSearchCarCd				)){SearchCarCd.add(GetSearchCarCd);}
					if(!"".equals(GetSearchCarName				)){SearchCarName.add(GetSearchCarName);}
					if(!"".equals(GetSearchPost					)){SearchPost.add(GetSearchPost);}
					if(!"".equals(GetSearchAdd					)){SearchAdd.add(GetSearchAdd);}
					if(!"".equals(GetSearchTel					)){SearchTel.add(GetSearchTel);}
					if(!"".equals(GetSearchFax					)){SearchFax.add(GetSearchFax);}
					if(!"".equals(GetSearchMail					)){SearchMail.add(GetSearchMail);}
					if(!"".equals(GetSearchCom					)){SearchCom.add(GetSearchCom);}
					
					if(!"9".equals(A00000_Main.LoginUserAuthorityFG)&&"".equals(GetSearchAuthorityFG)) {
						for(int i=0;i<B100_DefaultVariable.AuthorityFG[1].length;i++) {
							SearchAuthorityFG.add(""+B100_DefaultVariable.AuthorityFG[1][i]);
						}
					}
					
					Object[][] UserMstRt = M100_UserMstRt.UserMstRt(
								SearchWHCD,
								SearchShippingCompanyCd,
								SearchAuthorityFG,
								SearchUserCd,
								SearchUserName,
								SearchCarCd,
								SearchCarName,
								SearchPost,
								SearchAdd,
								SearchTel,
								SearchFax,
								SearchMail,
								SearchCom,
								SearchDelFg,
								AllSearch);
					
					for(int i=0;i<UserMstRt.length;i++) {
						Object[] SetOb = new Object[UserMstRt[i].length+1];
						SetOb[0] = false;
						for(int i01=0;i01<UserMstRt[i].length;i01++) {
							SetOb[i01+1] = UserMstRt[i][i01];
						}
						
						//パスワードは一覧に出さない
						if("".equals(""+UserMstRt[i][M100_UserMstRt.ColPassWord])) {
						}else {
							SetOb[M100_UserMstRt.ColPassWord+1] = "********";
						}
						MainFmTableModel.addRow(SetOb);
					}
					if(0<UserMstRt.length) {
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
					String TgtWhCd = "";
					String TgtShippingCompanyCd = "";
					String TgtUser = "";
					for(int i=0;i<RowCount;i++) {
						if((boolean)MainFmTableModel.getValueAt(i, 0)) {
							TgtWhCd 				= ""+MainFmTableModel.getValueAt(i, 1+M100_UserMstRt.ColWHCD);					if(null==TgtWhCd				) {TgtWhCd="";}
							TgtShippingCompanyCd 	= ""+MainFmTableModel.getValueAt(i, 1+M100_UserMstRt.ColShippingCompanyCd);	if(null==TgtShippingCompanyCd	) {TgtShippingCompanyCd="";}
							TgtUser 				= ""+MainFmTableModel.getValueAt(i, 1+M100_UserMstRt.ColUserCd);				if(null==TgtUser				) {TgtUser="";}
						}
					}
					if(!"".equals(TgtUser)) {
						SetX=main_fm.getX();
						SetY=main_fm.getY();

						main_fm.setVisible(false);
						main_fm.dispose();
						WM100_UserMst_01_RenewAndCreate.UserMstRenewAndCreate(0,0,TgtWhCd,TgtShippingCompanyCd,TgtUser);
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
					WM100_UserMst_01_RenewAndCreate.UserMstRenewAndCreate(0,0,"","","");
					
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
					B100_TableControl.TableOutPutCsv("出力先選択","ユーザーマスタ検索結果",tb01);
					RenewFg = true;
				}
			}
		});
		
		//エクセル出力ボタン押下時の挙動
		ExcelBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					B100_TableControl.TableOutPutExcel("出力先選択","ユーザーマスタ検索結果",tb01);
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
					String Selected = B100_FileSelect.FileSelect(MSG,file_type,file_type_name);
					
					if(null!=Selected && !Selected.equals(Selected.replace(".xlsx", ""))) {
						SetX=main_fm.getX();
						SetY=main_fm.getY();

						main_fm.setVisible(false);
						main_fm.dispose();
						WM100_UserMst_02_ExcelEntry.UserMstExcelEntry(0,0,Selected);
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
				A00001_MstMain.MstMain(0, 0);
			}
		});
	}
}