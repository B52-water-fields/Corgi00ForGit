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

public class WM100_ClMst_00_Search{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	public static void ClMstSearch(int x,int y) {
		A00000_Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		
		final JFrame main_fm = B100_FrameParts.FrameCreate(x,y,780,750,"Corgi00荷主検索","");
		JLabel userinfo = B100_FrameParts.UserInfo();
		JButton exit_btn = B100_FrameParts.ExitBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		
		//検索条件パネル
		JPanel PN_Search = B100_FrameParts.JPanelSet(10,40,740,175,"White");
		JLabel PN_SearchLabel = B100_FrameParts.JLabelSet(10,0,150,20,"検索条件",11,0);
		PN_Search.add(PN_SearchLabel);
		main_fm.add(PN_Search);
		
		JLabel LB_SearchClGpCD	  = B100_FrameParts.JLabelSet(  0, 25,100,20,"荷主Glp:"	,11,1);
		JLabel LB_SearchCLCD	  = B100_FrameParts.JLabelSet(  0, 50,100,20,"荷主CD:"	,11,1);
		JLabel LB_SearchWHCD	  = B100_FrameParts.JLabelSet(  0, 75,100,20,"担当倉庫:"	,11,1);
		JLabel LB_SearchCLName	  = B100_FrameParts.JLabelSet(  0,100,100,20,"荷主名:"	,11,1);
		JLabel LB_searchAdd		  = B100_FrameParts.JLabelSet(  0,125,100,20,"住所:"		,11,1);
		
		JLabel LB_SearchPost	  = B100_FrameParts.JLabelSet(350, 25,100,20,"郵便:"		,11,1);
		JLabel LB_SearchTel		  = B100_FrameParts.JLabelSet(350, 50,100,20,"Tel:"		,11,1);
		JLabel LB_SearchFax		  = B100_FrameParts.JLabelSet(350, 75,100,20,"Fax:"		,11,1);
		JLabel LB_SearchMail	  = B100_FrameParts.JLabelSet(350,100,100,20,"Mail:"		,11,1);
		JLabel LB_SearchCom		  = B100_FrameParts.JLabelSet(350,125,100,20,"コメント:"	,11,1);
		
		final JComboBox  TB_SearchClGpCD	 = B100_FrameParts.JComboBoxSet( 100, 25,200,20,B100_DefaultVariable.SearchClGpList[0],11);	//荷主Glp
		final JTextField TB_SearchCLCD		 = B100_FrameParts.JTextFieldSet(100, 50,100,20,"",11,0);	//荷主CD
		final JComboBox  TB_SearchWHCD	  	 = B100_FrameParts.JComboBoxSet( 100, 75,200,20,B100_DefaultVariable.SearchWhList[0],11);		//担当倉庫
		final JTextField TB_SearchCLName	 = B100_FrameParts.JTextFieldSet(100,100,200,20,"",11,0);	//荷主名
		final JTextField TB_searchAdd		 = B100_FrameParts.JTextFieldSet(100,125,200,20,"",11,0);	//住所

		final JTextField TB_SearchPost		 = B100_FrameParts.JTextFieldSet(450, 25,100,20,"",11,0);	//郵便
		final JTextField TB_SearchTel		 = B100_FrameParts.JTextFieldSet(450, 50,100,20,"",11,0);	//Tel
		final JTextField TB_SearchFax		 = B100_FrameParts.JTextFieldSet(450, 75,100,20,"",11,0);	//Fax
		final JTextField TB_SearchMail		 = B100_FrameParts.JTextFieldSet(450,100,100,20,"",11,0);	//Mail
		final JTextField TB_SearchCom		 = B100_FrameParts.JTextFieldSet(450,125,200,20,"",11,0);	//コメント
		
		JLabel LB2_SearchClGpCD	  = B100_FrameParts.JLabelSet(300, 25,100,20,"と一致"		,11,0);
		JLabel LB2_SearchCLCD	  = B100_FrameParts.JLabelSet(200, 50, 50,20,"と一致"		,11,0);
		JLabel LB2_SearchWHCD	  = B100_FrameParts.JLabelSet(300, 75, 50,20,"と一致"		,11,0);
		JLabel LB2_SearchCLName	  = B100_FrameParts.JLabelSet(300,100, 50,20,"を含む"		,11,0);
		JLabel LB2_searchAdd	  = B100_FrameParts.JLabelSet(300,125, 50,20,"を含む"		,11,0);
		
		JLabel LB2_SearchPost	  = B100_FrameParts.JLabelSet(550, 25, 50,20,"で始まる"	,11,0);
		JLabel LB2_SearchTel	  = B100_FrameParts.JLabelSet(550, 50, 50,20,"を含む"		,11,0);
		JLabel LB2_SearchFax	  = B100_FrameParts.JLabelSet(550, 75, 50,20,"を含む"		,11,0);
		JLabel LB2_SearchMail	  = B100_FrameParts.JLabelSet(550,100, 50,20,"を含む"		,11,0);
		JLabel LB2_SearchCom	  = B100_FrameParts.JLabelSet(650,125, 50,20,"を含む"		,11,0);
		
		PN_Search.add(LB_SearchClGpCD);
		PN_Search.add(LB_SearchCLCD);
		PN_Search.add(LB_SearchWHCD);
		PN_Search.add(LB_SearchCLName);
		PN_Search.add(LB_searchAdd);
		PN_Search.add(LB_SearchPost);
		PN_Search.add(LB_SearchTel);
		PN_Search.add(LB_SearchFax);
		PN_Search.add(LB_SearchMail);
		PN_Search.add(LB_SearchCom);
		
		PN_Search.add(TB_SearchClGpCD);
		PN_Search.add(TB_SearchCLCD);
		PN_Search.add(TB_SearchWHCD);
		PN_Search.add(TB_SearchCLName);
		PN_Search.add(TB_searchAdd);
		PN_Search.add(TB_SearchPost);
		PN_Search.add(TB_SearchTel);
		PN_Search.add(TB_SearchFax);
		PN_Search.add(TB_SearchMail);
		PN_Search.add(TB_SearchCom);
		
		PN_Search.add(LB2_SearchClGpCD);
		PN_Search.add(LB2_SearchCLCD);
		PN_Search.add(LB2_SearchWHCD);
		PN_Search.add(LB2_SearchCLName);
		PN_Search.add(LB2_searchAdd);
		PN_Search.add(LB2_SearchPost);
		PN_Search.add(LB2_SearchTel);
		PN_Search.add(LB2_SearchFax);
		PN_Search.add(LB2_SearchMail);
		PN_Search.add(LB2_SearchCom);
		
		//検索ボタン
		JButton SearchBtn = B100_FrameParts.BtnSet(450,150,100,20,"検索",11);
		PN_Search.add(SearchBtn);
		
		Object[][] RtSettingClMstRt=M100_ClMstRt.RtClMstRt();
		
		String[] columnNames01 = new String[RtSettingClMstRt.length+1];
		
		columnNames01[0] = "Fg";
		for(int i=0;i<RtSettingClMstRt.length;i++) {
			columnNames01[1+(int)RtSettingClMstRt[i][1]] = ""+RtSettingClMstRt[i][3];
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
		
		for(int i=0;i<RtSettingClMstRt.length;i++) {
			if("int".equals((String)RtSettingClMstRt[i][2])||"float".equals((String)RtSettingClMstRt[i][2])) {
				column = columnModel01.getColumn(1+(int)RtSettingClMstRt[i][1]);	column.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.rightCellRenderer());
			}else {
				column = columnModel01.getColumn(1+(int)RtSettingClMstRt[i][1]);	column.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.leftCellRenderer());
			}
		}
		
		//スクロール用設定
		JScrollPane scpn01 = B100_FrameParts.JScrollPaneSet(10,220,740,400,tb01);
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
					
					String GetSearchClGpCD 	= ""+B100_DefaultVariable.SearchClGpList[1][TB_SearchClGpCD.getSelectedIndex()];
					String GetSearchCLCD 	= TB_SearchCLCD.getText();
					String GetSearchWHCD 	= ""+B100_DefaultVariable.SearchWhList[1][TB_SearchWHCD.getSelectedIndex()];
					String GetSearchCLName 	= TB_SearchCLName.getText();
					String GetsearchAdd 	= TB_searchAdd.getText();
					String GetSearchPost 	= TB_SearchPost.getText();
					String GetSearchTel 	= TB_SearchTel.getText();
					String GetSearchFax 	= TB_SearchFax.getText();
					String GetSearchMail 	= TB_SearchMail.getText();
					String GetSearchCom 	= TB_SearchCom.getText();
					
					if(null==GetSearchClGpCD){GetSearchClGpCD = "";}
					if(null==GetSearchCLCD){GetSearchCLCD = "";}
					if(null==GetSearchWHCD){GetSearchWHCD = "";}
					if(null==GetSearchCLName){GetSearchCLName = "";}
					if(null==GetsearchAdd){GetsearchAdd = "";}
					if(null==GetSearchPost){GetSearchPost = "";}
					if(null==GetSearchTel){GetSearchTel = "";}
					if(null==GetSearchFax){GetSearchFax = "";}
					if(null==GetSearchMail){GetSearchMail = "";}
					if(null==GetSearchCom){GetSearchCom = "";}
					
					GetSearchPost = B100_TextControl.num_only_String(GetSearchPost);
					GetSearchTel  = B100_TextControl.num_only_String(GetSearchTel);
					GetSearchFax  = B100_TextControl.num_only_String(GetSearchFax);
					
					ArrayList<String> SearchClGpCD = new ArrayList<String>();
					ArrayList<String> SearchCLCD = new ArrayList<String>();
					ArrayList<String> SearchCLName = new ArrayList<String>();
					ArrayList<String> SearchPost = new ArrayList<String>();
					ArrayList<String> searchAdd = new ArrayList<String>();
					ArrayList<String> SearchTel = new ArrayList<String>();
					ArrayList<String> SearchFax = new ArrayList<String>();
					ArrayList<String> SearchMail = new ArrayList<String>(); 
					ArrayList<String> SearchCom = new ArrayList<String>();
					ArrayList<String> SearchWHCD = new ArrayList<String>();
					boolean AllSearch = true;
					
					if(!"".equals(GetSearchClGpCD)){SearchClGpCD.add(GetSearchClGpCD);}
					if(!"".equals(GetSearchCLCD)){SearchCLCD.add(GetSearchCLCD);}
					if(!"".equals(GetSearchWHCD)){SearchWHCD.add(GetSearchWHCD);}
					if(!"".equals(GetSearchCLName)){SearchCLName.add(GetSearchCLName);}
					if(!"".equals(GetsearchAdd)){searchAdd.add(GetsearchAdd);}
					if(!"".equals(GetSearchPost)){SearchPost.add(GetSearchPost);}
					if(!"".equals(GetSearchTel)){SearchTel.add(GetSearchTel);}
					if(!"".equals(GetSearchFax)){SearchFax.add(GetSearchFax);}
					if(!"".equals(GetSearchMail)){SearchMail.add(GetSearchMail);}
					if(!"".equals(GetSearchCom)){SearchCom.add(GetSearchCom);}
					
					
					TB_SearchCLCD.setText(GetSearchCLCD);
					TB_SearchCLName.setText(GetSearchCLName);
					TB_searchAdd.setText(GetsearchAdd);
					TB_SearchPost.setText(GetSearchPost);
					TB_SearchTel.setText(GetSearchTel);
					TB_SearchFax.setText(GetSearchFax);
					TB_SearchMail.setText(GetSearchMail);
					TB_SearchCom.setText(GetSearchCom);
					
					Object[][] ClMstRt = M100_ClMstRt.ClMstRt(
								SearchClGpCD,SearchCLCD,SearchCLName,SearchPost,searchAdd,
								SearchTel,SearchFax,SearchMail,SearchCom,SearchWHCD,AllSearch);
					
					Object[][] RtSettingClMstRt	= M100_ClMstRt.RtClMstRt();
					
					for(int i=0;i<ClMstRt.length;i++) {
						Object[] SetOb = new Object[RtSettingClMstRt.length+1];
						SetOb[ 0] = false;
						for(int i01=0;i01<RtSettingClMstRt.length;i01++) {
							SetOb[1+(int)RtSettingClMstRt[i01][1]]=""+ClMstRt[i][(int)RtSettingClMstRt[i01][1]];
						}
						
						MainFmTableModel.addRow(SetOb);
					}
					
					if(0<ClMstRt.length) {
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
					String TgtCl = "";
					for(int i=0;i<RowCount;i++) {
						if((boolean)MainFmTableModel.getValueAt(i, 0)) {
							TgtCl = ""+MainFmTableModel.getValueAt(i, M100_ClMstRt.Colcl_cd+1);	if(null==TgtCl) {TgtCl="";}
						}
					}
					if(!"".equals(TgtCl)) {
						SetX=main_fm.getX();
						SetY=main_fm.getY();

						main_fm.setVisible(false);
						main_fm.dispose();
						WM100_ClMst_01_RenewAndCreate.ClMstRenewAndCreate(0,0,TgtCl);
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
					WM100_ClMst_01_RenewAndCreate.ClMstRenewAndCreate(0,0,"");
					
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
					B100_TableControl.TableOutPutCsv("出力先選択","荷主マスタ検索結果",tb01);
					RenewFg = true;
				}
			}
		});
		
		//エクセル出力ボタン押下時の挙動
		ExcelBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					B100_TableControl.TableOutPutExcel("出力先選択","荷主マスタ検索結果",tb01);
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