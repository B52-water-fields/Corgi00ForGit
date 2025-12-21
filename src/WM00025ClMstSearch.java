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

public class WM00025ClMstSearch{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	public static void ClMstSearch(int x,int y) {
		A00000Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		
		final JFrame main_fm = B00110FrameParts.FrameCreate(x,y,780,750,"Corgi00荷主検索","");
		JLabel userinfo = B00110FrameParts.UserInfo();
		JButton exit_btn = B00110FrameParts.ExitBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		
		//検索条件パネル
		JPanel PN_Search = B00110FrameParts.JPanelSet(10,40,740,175,"White");
		JLabel PN_SearchLabel = B00110FrameParts.JLabelSet(10,0,150,20,"検索条件",11,0);
		PN_Search.add(PN_SearchLabel);
		main_fm.add(PN_Search);
		
		JLabel LB_SearchClGpCD	  = B00110FrameParts.JLabelSet(  0, 25,100,20,"荷主Glp:"	,11,1);
		JLabel LB_SearchCLCD	  = B00110FrameParts.JLabelSet(  0, 50,100,20,"荷主CD:"	,11,1);
		JLabel LB_SearchWHCD	  = B00110FrameParts.JLabelSet(  0, 75,100,20,"担当倉庫:"	,11,1);
		JLabel LB_SearchCLName	  = B00110FrameParts.JLabelSet(  0,100,100,20,"荷主名:"	,11,1);
		JLabel LB_searchAdd		  = B00110FrameParts.JLabelSet(  0,125,100,20,"住所:"		,11,1);
		
		JLabel LB_SearchPost	  = B00110FrameParts.JLabelSet(350, 25,100,20,"郵便:"		,11,1);
		JLabel LB_SearchTel		  = B00110FrameParts.JLabelSet(350, 50,100,20,"Tel:"		,11,1);
		JLabel LB_SearchFax		  = B00110FrameParts.JLabelSet(350, 75,100,20,"Fax:"		,11,1);
		JLabel LB_SearchMail	  = B00110FrameParts.JLabelSet(350,100,100,20,"Mail:"		,11,1);
		JLabel LB_SearchCom		  = B00110FrameParts.JLabelSet(350,125,100,20,"コメント:"	,11,1);
		
		final JComboBox  TB_SearchClGpCD	 = B00110FrameParts.JComboBoxSet( 100, 25,200,20,B00100DefaultVariable.SearchClGpList[0],11);	//荷主Glp
		final JTextField TB_SearchCLCD		 = B00110FrameParts.JTextFieldSet(100, 50,100,20,"",11,0);	//荷主CD
		final JComboBox  TB_SearchWHCD	  	 = B00110FrameParts.JComboBoxSet( 100, 75,200,20,B00100DefaultVariable.SearchWhList[0],11);		//担当倉庫
		final JTextField TB_SearchCLName	 = B00110FrameParts.JTextFieldSet(100,100,200,20,"",11,0);	//荷主名
		final JTextField TB_searchAdd		 = B00110FrameParts.JTextFieldSet(100,125,200,20,"",11,0);	//住所

		final JTextField TB_SearchPost		 = B00110FrameParts.JTextFieldSet(450, 25,100,20,"",11,0);	//郵便
		final JTextField TB_SearchTel		 = B00110FrameParts.JTextFieldSet(450, 50,100,20,"",11,0);	//Tel
		final JTextField TB_SearchFax		 = B00110FrameParts.JTextFieldSet(450, 75,100,20,"",11,0);	//Fax
		final JTextField TB_SearchMail		 = B00110FrameParts.JTextFieldSet(450,100,100,20,"",11,0);	//Mail
		final JTextField TB_SearchCom		 = B00110FrameParts.JTextFieldSet(450,125,200,20,"",11,0);	//コメント
		
		JLabel LB2_SearchClGpCD	  = B00110FrameParts.JLabelSet(300, 25,100,20,"と一致"		,11,0);
		JLabel LB2_SearchCLCD	  = B00110FrameParts.JLabelSet(200, 50, 50,20,"と一致"		,11,0);
		JLabel LB2_SearchWHCD	  = B00110FrameParts.JLabelSet(300, 75, 50,20,"と一致"		,11,0);
		JLabel LB2_SearchCLName	  = B00110FrameParts.JLabelSet(300,100, 50,20,"を含む"		,11,0);
		JLabel LB2_searchAdd	  = B00110FrameParts.JLabelSet(300,125, 50,20,"を含む"		,11,0);
		
		JLabel LB2_SearchPost	  = B00110FrameParts.JLabelSet(550, 25, 50,20,"で始まる"	,11,0);
		JLabel LB2_SearchTel	  = B00110FrameParts.JLabelSet(550, 50, 50,20,"を含む"		,11,0);
		JLabel LB2_SearchFax	  = B00110FrameParts.JLabelSet(550, 75, 50,20,"を含む"		,11,0);
		JLabel LB2_SearchMail	  = B00110FrameParts.JLabelSet(550,100, 50,20,"を含む"		,11,0);
		JLabel LB2_SearchCom	  = B00110FrameParts.JLabelSet(650,125, 50,20,"を含む"		,11,0);
		
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
		JButton SearchBtn = B00110FrameParts.BtnSet(450,150,100,20,"検索",11);
		PN_Search.add(SearchBtn);
		
		String[] columnNames01 = {
				"FG"
				,"荷主CD"
				,"荷主グループCD"
				,"グループ名1"
				,"担当倉庫"
				,"担当倉庫名"
				,"荷主名1"
				,"荷主名2"
				,"荷主名3"
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
				,"締日"
				,"請求基準"
				,"データ登録日時"
				,"データ更新日時"
				,"登録者コード"
				,"更新者コード"
				,"基幹システム荷主コード"
				};
		
		//編集可能カラムの指定
		B10010TableControl.RenewTgt = new int[1];
		B10010TableControl.RenewTgt[0] = 0;
		final DefaultTableModel tableModel_ms01 = new B10010TableControl.MyTableModel01(columnNames01,0);
		
		final JTable tb01 = new JTable(tableModel_ms01);
		tb01.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tb01.setRowHeight(20*A00000Main.Mul/A00000Main.Div);
		tb01.setFont(new Font(A00000Main.DefaultFont, Font.PLAIN, 12*A00000Main.Mul/A00000Main.Div));
		
		B10010TableControl.AddSort(tb01,tableModel_ms01);
		
		DefaultTableColumnModel columnModel01
		= (DefaultTableColumnModel)tb01.getColumnModel();
		
		//列幅初期設定 表示位置設定
		TableColumn column = null;
		column = columnModel01.getColumn( 0);	column.setPreferredWidth( 30*A00000Main.Mul/A00000Main.Div);	//FG
		column = columnModel01.getColumn( 1);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//荷主CD
		column = columnModel01.getColumn( 2);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//荷主グループCD
		column = columnModel01.getColumn( 3);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//グループ名1
		column = columnModel01.getColumn( 4);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//担当倉庫
		column = columnModel01.getColumn( 5);	column.setPreferredWidth(150*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//担当倉庫名
		column = columnModel01.getColumn( 6);	column.setPreferredWidth(150*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//荷主名1
		column = columnModel01.getColumn( 7);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//荷主名2
		column = columnModel01.getColumn( 8);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//荷主名3
		column = columnModel01.getColumn( 9);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//郵便番号
		column = columnModel01.getColumn(10);	column.setPreferredWidth(150*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//住所1
		column = columnModel01.getColumn(11);	column.setPreferredWidth(150*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//住所2
		column = columnModel01.getColumn(12);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//住所3
		column = columnModel01.getColumn(13);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//電話番号
		column = columnModel01.getColumn(14);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//FAX
		column = columnModel01.getColumn(15);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//メールアドレス
		column = columnModel01.getColumn(16);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//コメント1
		column = columnModel01.getColumn(17);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//コメント2
		column = columnModel01.getColumn(18);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//コメント3
		column = columnModel01.getColumn(19);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.rightCellRenderer());	//締日
		column = columnModel01.getColumn(20);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.rightCellRenderer());	//請求基準
		column = columnModel01.getColumn(21);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//データ登録日時
		column = columnModel01.getColumn(22);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//データ更新日時
		column = columnModel01.getColumn(23);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//登録者コード
		column = columnModel01.getColumn(24);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//更新者コード
		column = columnModel01.getColumn(25);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//基幹システム荷主コード
		
		//スクロール用設定
		JScrollPane scpn01 = B00110FrameParts.JScrollPaneSet(10,220,740,400,tb01);
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
		
		//Excelボタン
		JButton ExcelBtn = B00110FrameParts.BtnSet(		370,660,100,20,"Excel出力",11);
		main_fm.add(ExcelBtn);
		
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
					
					String GetSearchClGpCD 	= ""+B00100DefaultVariable.SearchClGpList[1][TB_SearchClGpCD.getSelectedIndex()];
					String GetSearchCLCD 	= TB_SearchCLCD.getText();
					String GetSearchWHCD 	= ""+B00100DefaultVariable.SearchWhList[1][TB_SearchWHCD.getSelectedIndex()];
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
					
					GetSearchPost = B00020ToolsTextControl.num_only_String(GetSearchPost);
					GetSearchTel  = B00020ToolsTextControl.num_only_String(GetSearchTel);
					GetSearchFax  = B00020ToolsTextControl.num_only_String(GetSearchFax);
					
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
					
					Object[][] ClMstRt = M00011ClMstRt.ClMstRt(
								SearchClGpCD,SearchCLCD,SearchCLName,SearchPost,searchAdd,
								SearchTel,SearchFax,SearchMail,SearchCom,SearchWHCD,AllSearch);
					
					for(int i=0;i<ClMstRt.length;i++) {
						Object[] SetOb = new Object[26];
						SetOb[ 0] = false;
						SetOb[ 1] = ""+ClMstRt[i][ 0];
						SetOb[ 2] = ""+ClMstRt[i][ 1];
						SetOb[ 3] = ""+ClMstRt[i][ 2];
						SetOb[ 4] = ""+ClMstRt[i][ 3];
						SetOb[ 5] = ""+ClMstRt[i][ 4];
						SetOb[ 6] = ""+ClMstRt[i][ 5];
						SetOb[ 7] = ""+ClMstRt[i][ 6];
						SetOb[ 8] = ""+ClMstRt[i][ 7];
						SetOb[ 9] = ""+ClMstRt[i][ 8];
						SetOb[10] = ""+ClMstRt[i][ 9];
						SetOb[11] = ""+ClMstRt[i][10];
						SetOb[12] = ""+ClMstRt[i][11];
						SetOb[13] = ""+ClMstRt[i][12];
						SetOb[14] = ""+ClMstRt[i][13];
						SetOb[15] = ""+ClMstRt[i][14];
						SetOb[16] = ""+ClMstRt[i][15];
						SetOb[17] = ""+ClMstRt[i][16];
						SetOb[18] = ""+ClMstRt[i][17];
						SetOb[19] = ""+ClMstRt[i][18];
						SetOb[20] = ""+ClMstRt[i][19];
						SetOb[21] = ""+ClMstRt[i][20];
						SetOb[22] = ""+ClMstRt[i][21];
						SetOb[23] = ""+ClMstRt[i][22];
						SetOb[24] = ""+ClMstRt[i][23];
						SetOb[25] = ""+ClMstRt[i][24];
						
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
					String TgtCl = "";
					for(int i=0;i<RowCount;i++) {
						if((boolean)tableModel_ms01.getValueAt(i, 0)) {
							TgtCl = ""+tableModel_ms01.getValueAt(i, 1);	if(null==TgtCl) {TgtCl="";}
						}
					}
					if(!"".equals(TgtCl)) {
						SetX=main_fm.getX();
						SetY=main_fm.getY();

						main_fm.setVisible(false);
						main_fm.dispose();
						WM00026ClMstRenewAndCreate.ClMstRenewAndCreate(0,0,TgtCl);
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
					WM00026ClMstRenewAndCreate.ClMstRenewAndCreate(0,0,"");
					
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
					B10010TableControl.TableOutPutCsv("出力先選択","荷主マスタ検索結果",tb01);
					RenewFg = true;
				}
			}
		});
		
		//エクセル出力ボタン押下時の挙動
		ExcelBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					B10010TableControl.TableOutPutExcel("出力先選択","荷主マスタ検索結果",tb01);
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