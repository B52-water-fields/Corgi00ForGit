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

public class WM00030UserMstSearch{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	public static void UserMstSearch(int x,int y) {
		A00000Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		
		final JFrame main_fm = B00110FrameParts.FrameCreate(x,y,860,750,"Corgi00ユーザー検索","");
		JLabel userinfo = B00110FrameParts.UserInfo();
		JButton exit_btn = B00110FrameParts.ExitBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		
		//検索条件パネル
		JPanel PN_Search = B00110FrameParts.JPanelSet(10,40,820,180,"White");
		JLabel PN_SearchLabel = B00110FrameParts.JLabelSet(10,0,150,20,"検索条件",11,0);
		PN_Search.add(PN_SearchLabel);
		main_fm.add(PN_Search);

		JLabel LB_SearchWHCD				= B00110FrameParts.JLabelSet(  0, 25,100,20,"所属倉庫:",	11,1);
		JLabel LB_SearchShippingCompanyCd	= B00110FrameParts.JLabelSet(  0, 50,100,20,"所属会社:",	11,1);
		JLabel LB_SearchUserCd				= B00110FrameParts.JLabelSet(  0, 75,100,20,"ユーザーCD:",	11,1);
		JLabel LB_SearchUserName			= B00110FrameParts.JLabelSet(  0,100,100,20,"ユーザー名:",	11,1);
		JLabel LB_SearchAuthorityFG			= B00110FrameParts.JLabelSet(  0,125,100,20,"権限:",		11,1);
		JLabel LB_SearchDelFg				= B00110FrameParts.JLabelSet(  0,150,100,20,"削除区分:",	11,1);
		
		JLabel LB_SearchCarCd				= B00110FrameParts.JLabelSet(300, 25,100,20,"乗務車輛CD:",	11,1);
		JLabel LB_SearchCarName				= B00110FrameParts.JLabelSet(300, 50,100,20,"乗務車輛名:",	11,1);
		JLabel LB_SearchPost				= B00110FrameParts.JLabelSet(300, 75,100,20,"郵便番号:",	11,1);
		JLabel LB_SearchAdd					= B00110FrameParts.JLabelSet(300,100,100,20,"住所:",		11,1);
		
		JLabel LB_SearchTel					= B00110FrameParts.JLabelSet(550, 25,100,20,"Tel:",		11,1);
		JLabel LB_SearchFax					= B00110FrameParts.JLabelSet(550, 50,100,20,"Fax:",		11,1);
		JLabel LB_SearchMail				= B00110FrameParts.JLabelSet(550, 75,100,20,"Mail:",		11,1);
		JLabel LB_SearchCom					= B00110FrameParts.JLabelSet(550,100,100,20,"コメント:",	11,1);
		
		final JComboBox  TB_SearchWHCD				= B00110FrameParts.JComboBoxSet( 100, 25,200,20,B00100DefaultVariable.SearchWhList[0],11);	//所属倉庫
		final JComboBox  TB_SearchShippingCompanyCd	= B00110FrameParts.JComboBoxSet( 100, 50,200,20,B00100DefaultVariable.SearchShippingCompanyList[0],11);	//所属会社
		final JTextField TB_SearchUserCd			= B00110FrameParts.JTextFieldSet(100, 75,100,20,"",11,0);	//ユーザーCD
		final JTextField TB_SearchUserName			= B00110FrameParts.JTextFieldSet(100,100,100,20,"",11,0);	//ユーザー名
		final JComboBox  TB_SearchAuthorityFG		= B00110FrameParts.JComboBoxSet( 100,125,150,20,B00100DefaultVariable.SerachAuthorityFG[0],11);	//権限
		final JComboBox  TB_SearchDelFg				= B00110FrameParts.JComboBoxSet( 100,150,100,20,B00100DefaultVariable.SearchDelList[0],11);	//削除区分
		
		final JTextField TB_SearchCarCd				= B00110FrameParts.JTextFieldSet(400, 25,100,20,"",11,0);	//乗務車輛CD
		final JTextField TB_SearchCarName			= B00110FrameParts.JTextFieldSet(400, 50,100,20,"",11,0);	//乗務車輛名
		final JTextField TB_SearchPost				= B00110FrameParts.JTextFieldSet(400, 75,100,20,"",11,0);	//郵便番号
		final JTextField TB_SearchAdd				= B00110FrameParts.JTextFieldSet(400,100,100,20,"",11,0);	//住所
		
		final JTextField TB_SearchTel				= B00110FrameParts.JTextFieldSet(650, 25,100,20,"",11,0);	//Tel
		final JTextField TB_SearchFax				= B00110FrameParts.JTextFieldSet(650, 50,100,20,"",11,0);	//Fax
		final JTextField TB_SearchMail				= B00110FrameParts.JTextFieldSet(650, 75,100,20,"",11,0);	//Mail
		final JTextField TB_SearchCom				= B00110FrameParts.JTextFieldSet(650,100,100,20,"",11,0);	//コメント
		
		JLabel LB2_SearchWHCD				= B00110FrameParts.JLabelSet(300, 25,100,20,"",			11,0);
		JLabel LB2_SearchShippingCompanyCd	= B00110FrameParts.JLabelSet(300, 50,100,20,"",			11,0);
		JLabel LB2_SearchUserCd				= B00110FrameParts.JLabelSet(200, 75,100,20,"と一致",		11,0);
		JLabel LB2_SearchUserName			= B00110FrameParts.JLabelSet(200,100,100,20,"を含む",		11,0);
		JLabel LB2_SearchAuthorityFG		= B00110FrameParts.JLabelSet(250,125,100,20,"",			11,0);
		JLabel LB2_SearchDelFg				= B00110FrameParts.JLabelSet(200,150,100,20,"",			11,0);
		
		JLabel LB2_SearchCarCd				= B00110FrameParts.JLabelSet(500, 25,100,20,"と一致",		11,0);
		JLabel LB2_SearchCarName			= B00110FrameParts.JLabelSet(500, 50,100,20,"を含む",		11,0);
		JLabel LB2_SearchPost				= B00110FrameParts.JLabelSet(500, 75,100,20,"で始まる",	11,0);
		JLabel LB2_SearchAdd				= B00110FrameParts.JLabelSet(500,100,100,20,"を含む",		11,0);
		
		JLabel LB2_SearchTel				= B00110FrameParts.JLabelSet(750, 25,100,20,"を含む",		11,0);
		JLabel LB2_SearchFax				= B00110FrameParts.JLabelSet(750, 50,100,20,"を含む",		11,0);
		JLabel LB2_SearchMail				= B00110FrameParts.JLabelSet(750, 75,100,20,"を含む",		11,0);
		JLabel LB2_SearchCom				= B00110FrameParts.JLabelSet(750,100,100,20,"を含む",		11,0);
		
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
		
		String[] columnNames01 = {
				"FG"
				,"倉庫CD"
				,"運送会社CD"
				,"ユーザーCD"
				,"倉庫名"
				,"運送会社名"
				,"ユーザー名1"
				,"ユーザー名2"
				,"ユーザー名3"
				,"権限区分"
				,"標準車輛CD"
				,"車両名称01"
				,"車両名称02"
				,"車両名称03"
				,"郵便番号"
				,"住所1"
				,"住所2"
				,"住所3"
				,"電話番号"
				,"FAX"
				,"メールアドレス"
				,"コメント1"
				,"コメント2"
				,"コメント3"
				,"登録者コード"
				,"更新者コード"
				,"データ登録日時"
				,"データ更新日時"
				,"基幹システムユーザーコード"
				,"削除区分"
				,"主要担当荷主CD"
				,"主要担当荷主名"
				};
		
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
		column = columnModel01.getColumn( 1);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//倉庫CD
		column = columnModel01.getColumn( 2);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//運送会社CD
		column = columnModel01.getColumn( 3);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//ユーザーCD
		column = columnModel01.getColumn( 4);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//倉庫名
		column = columnModel01.getColumn( 5);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//運送会社名
		column = columnModel01.getColumn( 6);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//ユーザー名1
		column = columnModel01.getColumn( 7);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//ユーザー名2
		column = columnModel01.getColumn( 8);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//ユーザー名3
		column = columnModel01.getColumn( 9);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//権限区分
		column = columnModel01.getColumn(10);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//標準車輛CD
		column = columnModel01.getColumn(11);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//車両名称01
		column = columnModel01.getColumn(12);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//車両名称02
		column = columnModel01.getColumn(13);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//車両名称03
		column = columnModel01.getColumn(14);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//郵便番号
		column = columnModel01.getColumn(15);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//住所1
		column = columnModel01.getColumn(16);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//住所2
		column = columnModel01.getColumn(17);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//住所3
		column = columnModel01.getColumn(18);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//電話番号
		column = columnModel01.getColumn(19);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//FAX
		column = columnModel01.getColumn(20);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//メールアドレス
		column = columnModel01.getColumn(21);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//コメント1
		column = columnModel01.getColumn(22);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//コメント2
		column = columnModel01.getColumn(23);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//コメント3
		column = columnModel01.getColumn(24);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//登録者コード
		column = columnModel01.getColumn(25);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//更新者コード
		column = columnModel01.getColumn(26);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//データ登録日時
		column = columnModel01.getColumn(27);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//データ更新日時
		column = columnModel01.getColumn(28);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//基幹システムユーザーコード
		column = columnModel01.getColumn(29);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//削除区分
		column = columnModel01.getColumn(30);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//主要担当荷主CD
		column = columnModel01.getColumn(31);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//主要担当荷主名
		
		//スクロール用設定
		JScrollPane scpn01 = B00110FrameParts.JScrollPaneSet(10,230,820,375,tb01);
		main_fm.add(scpn01);
		
		//CSVボタン
		JButton CsvBtn = B00110FrameParts.BtnSet(10,660,100,20,"csv出力",11);
		main_fm.add(CsvBtn);
		
		JLabel LB_RenewBtn  = B00110FrameParts.JLabelSet(130,640,100,20,"チェック行を" ,11,2);
		main_fm.add(LB_RenewBtn);
		
		//修正ボタン
		JButton RenewBtn = B00110FrameParts.BtnSet(130,660,100,20,"修正",11);
		main_fm.add(RenewBtn);
		
		//新規登録ボタン
		JButton CreateBtn = B00110FrameParts.BtnSet(250,660,100,20,"新規登録",11);
		main_fm.add(CreateBtn);
		
		//検索ボタン
		JButton SearchBtn = B00110FrameParts.BtnSet(400,150,100,20,"検索",11);
		PN_Search.add(SearchBtn);
		
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
					
					String GetSearchWHCD				= ""+B00100DefaultVariable.SearchWhList[1][TB_SearchWHCD.getSelectedIndex()];								//所属倉庫
					String GetSearchShippingCompanyCd	= ""+B00100DefaultVariable.SearchShippingCompanyList[1][TB_SearchShippingCompanyCd.getSelectedIndex()];	//所属会社
					String GetSearchUserCd				= TB_SearchUserCd.getText();	//ユーザーCD
					String GetSearchUserName			= TB_SearchUserName.getText();	//ユーザー名
					String GetSearchAuthorityFG			= ""+B00100DefaultVariable.SerachAuthorityFG[1][TB_SearchAuthorityFG.getSelectedIndex()];	//権限
					String GetSearchDelFg				= ""+B00100DefaultVariable.SearchDelList[1][TB_SearchDelFg.getSelectedIndex()];			//削除区分
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
					
					GetSearchPost = B00020ToolsTextControl.num_only_String(GetSearchPost);
					GetSearchTel  = B00020ToolsTextControl.num_only_String(GetSearchTel);
					GetSearchFax  = B00020ToolsTextControl.num_only_String(GetSearchFax);
					
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
					
					if(!"9".equals(A00000Main.LoginUserAuthorityFG)&&"".equals(GetSearchAuthorityFG)) {
						for(int i=0;i<B00100DefaultVariable.AuthorityFG[1].length;i++) {
							SearchAuthorityFG.add(""+B00100DefaultVariable.AuthorityFG[1][i]);
						}
					}
					
					Object[][] UserMstRt = M00020UserMstRt.UserMstRt(
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
						Object[] SetOb = new Object[32];
						
						SetOb[ 0] = false;	//FG
						SetOb[ 1] = ""+UserMstRt[i][0];		//倉庫CD
						SetOb[ 2] = ""+UserMstRt[i][1];		//運送会社CD
						SetOb[ 3] = ""+UserMstRt[i][3];		//ユーザーCD
						SetOb[ 4] = ""+UserMstRt[i][29];	//倉庫名
						SetOb[ 5] = ""+UserMstRt[i][2];		//運送会社名
						SetOb[ 6] = ""+UserMstRt[i][10];	//ユーザー名1
						SetOb[ 7] = ""+UserMstRt[i][11];	//ユーザー名2
						SetOb[ 8] = ""+UserMstRt[i][12];	//ユーザー名3
						SetOb[ 9] = ""+UserMstRt[i][5];		//権限区分
						SetOb[10] = ""+UserMstRt[i][6];		//標準車輛CD
						SetOb[11] = ""+UserMstRt[i][7];		//車両名称01
						SetOb[12] = ""+UserMstRt[i][8];		//車両名称02
						SetOb[13] = ""+UserMstRt[i][9];		//車両名称03
						SetOb[14] = ""+UserMstRt[i][13];	//郵便番号
						SetOb[15] = ""+UserMstRt[i][14];	//住所1
						SetOb[16] = ""+UserMstRt[i][15];	//住所2
						SetOb[17] = ""+UserMstRt[i][16];	//住所3
						SetOb[18] = ""+UserMstRt[i][17];	//電話番号
						SetOb[19] = ""+UserMstRt[i][18];	///FAX
						SetOb[20] = ""+UserMstRt[i][19];	//メールアドレス
						SetOb[21] = ""+UserMstRt[i][20];	//コメント1
						SetOb[22] = ""+UserMstRt[i][21];	//コメント2
						SetOb[23] = ""+UserMstRt[i][22];	//コメント3
						SetOb[24] = ""+UserMstRt[i][25];	//登録者コード
						SetOb[25] = ""+UserMstRt[i][26];	//更新者コード
						SetOb[26] = ""+UserMstRt[i][23];	//データ登録日時
						SetOb[27] = ""+UserMstRt[i][24];	//データ更新日時
						SetOb[28] = ""+UserMstRt[i][27];	//基幹システムユーザーコード
						SetOb[29] = ""+UserMstRt[i][28];	//削除区分
						SetOb[30] = ""+UserMstRt[i][30];	//主要担当荷主CD
						SetOb[31] = ""+UserMstRt[i][31];	//主要担当荷主名
						tableModel_ms01.addRow(SetOb);
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
					String TgtWhCd = "";
					String TgtShippingCompanyCd = "";
					String TgtUser = "";
					for(int i=0;i<RowCount;i++) {
						if((boolean)tableModel_ms01.getValueAt(i, 0)) {
							TgtWhCd 				= ""+tableModel_ms01.getValueAt(i, 1);	if(null==TgtWhCd				) {TgtWhCd="";}
							TgtShippingCompanyCd 	= ""+tableModel_ms01.getValueAt(i, 2);	if(null==TgtShippingCompanyCd	) {TgtShippingCompanyCd="";}
							TgtUser 				= ""+tableModel_ms01.getValueAt(i, 3);	if(null==TgtUser				) {TgtUser="";}
						}
					}
					if(!"".equals(TgtUser)) {
						SetX=main_fm.getX();
						SetY=main_fm.getY();

						main_fm.setVisible(false);
						main_fm.dispose();
						WM00031UserMstRenewAndCreate.UserMstRenewAndCreate(0,0,TgtWhCd,TgtShippingCompanyCd,TgtUser);
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
					WM00031UserMstRenewAndCreate.UserMstRenewAndCreate(0,0,"","","");
					
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
							if((Boolean)tb01.getValueAt(i,0)){
								tableModel_ms01.setValueAt(setBL, i, 0);
							}
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
					B10010TableControl.TableOutPutCsv("出力先選択","ユーザーマスタ検索結果",tb01);
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