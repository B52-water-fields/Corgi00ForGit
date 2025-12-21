import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JButton;
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

public class WM00020ClGlpMstSearch{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	public static void ClGlpMstSearch(int x,int y) {
		A00000Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		
		final JFrame main_fm = B00110FrameParts.FrameCreate(x,y,780,750,"Corgi00荷主グループ検索","");
		JLabel userinfo = B00110FrameParts.UserInfo();
		JButton exit_btn = B00110FrameParts.ExitBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		
		//検索条件パネル
		JPanel PN_Search = B00110FrameParts.JPanelSet(10,40,740,150,"White");
		JLabel PN_SearchLabel = B00110FrameParts.JLabelSet(10,0,150,20,"検索条件",11,0);
		PN_Search.add(PN_SearchLabel);
		main_fm.add(PN_Search);
		
		JLabel LB_SearchClGpCD  = B00110FrameParts.JLabelSet(		  0, 25,100,20,"荷主GlpCD:"	,11,1);
		JLabel LB_SearchCLGpName  = B00110FrameParts.JLabelSet(	  0, 50,100,20,"名称:"		,11,1);
		JLabel LB_SearchPost  = B00110FrameParts.JLabelSet(		  0, 75,100,20,"郵便番号:"	,11,1);
		JLabel LB_SearchAdd  = B00110FrameParts.JLabelSet(			  0,100,100,20,"住所:"		,11,1);
		JLabel LB_SearchCom  = B00110FrameParts.JLabelSet(			  0,125,100,20,"コメント:"	,11,1);
		
		JLabel LB_SearchTel  = B00110FrameParts.JLabelSet(			350, 25,100,20,"Tel:"		,11,1);
		JLabel LB_SearchFax  = B00110FrameParts.JLabelSet(			350, 50,100,20,"Fax:"		,11,1);
		JLabel LB_SearchMail  = B00110FrameParts.JLabelSet(		350, 75,100,20,"Mail:"		,11,1);
		
		final JTextField TB_SearchClGpCD = B00110FrameParts.JTextFieldSet(	100, 25,100,20,"",11,0);
		final JTextField TB_SearchCLGpName = B00110FrameParts.JTextFieldSet(	100, 50,150,20,"",11,0);
		final JTextField TB_SearchPost = B00110FrameParts.JTextFieldSet(		100, 75,100,20,"",11,0);
		final JTextField TB_SearchAdd = B00110FrameParts.JTextFieldSet(		100,100,150,20,"",11,0);
		final JTextField TB_SearchCom  = B00110FrameParts.JTextFieldSet(		100,125,150,20,"",11,0);
		
		final JTextField TB_SearchTel = B00110FrameParts.JTextFieldSet(		450, 25,100,20,"",11,0);
		final JTextField TB_SearchFax  = B00110FrameParts.JTextFieldSet(		450, 50,100,20,"",11,0);
		final JTextField TB_SearchMail = B00110FrameParts.JTextFieldSet(		450, 75,150,20,"",11,0);
		
		JLabel LB2_SearchClGpCD  = B00110FrameParts.JLabelSet(		 200, 25,100,20,"と一致"	,11,0);
		JLabel LB2_SearchCLGpName  = B00110FrameParts.JLabelSet(	 250, 50,100,20,"を含む"	,11,0);
		JLabel LB2_SearchPost  = B00110FrameParts.JLabelSet(		 200, 75,100,20,"で始まる"	,11,0);
		JLabel LB2_SearchAdd  = B00110FrameParts.JLabelSet(		 250,100,100,20,"を含む"	,11,0);
		JLabel LB2_SearchCom  = B00110FrameParts.JLabelSet(		 250,125,100,20,"を含む"	,11,0);
		
		JLabel LB2_SearchTel  = B00110FrameParts.JLabelSet(		 550, 25,100,20,"を含む"	,11,0);
		JLabel LB2_SearchFax  = B00110FrameParts.JLabelSet(		 550, 50,100,20,"を含む"	,11,0);
		JLabel LB2_SearchMail  = B00110FrameParts.JLabelSet(		 600, 75,100,20,"を含む"	,11,0);
		
		PN_Search.add(LB_SearchClGpCD);
		PN_Search.add(LB_SearchCLGpName);
		PN_Search.add(LB_SearchPost);
		PN_Search.add(LB_SearchAdd);
		PN_Search.add(LB_SearchCom);
		
		PN_Search.add(LB_SearchTel);
		PN_Search.add(LB_SearchFax);
		PN_Search.add(LB_SearchMail);
		
		PN_Search.add(TB_SearchClGpCD);
		PN_Search.add(TB_SearchCLGpName);
		PN_Search.add(TB_SearchPost);
		PN_Search.add(TB_SearchAdd);
		PN_Search.add(TB_SearchCom);
		
		PN_Search.add(TB_SearchTel);
		PN_Search.add(TB_SearchFax);
		PN_Search.add(TB_SearchMail);
		
		PN_Search.add(LB2_SearchClGpCD);
		PN_Search.add(LB2_SearchCLGpName);
		PN_Search.add(LB2_SearchPost);
		PN_Search.add(LB2_SearchAdd);
		PN_Search.add(LB2_SearchCom);
		
		PN_Search.add(LB2_SearchTel);
		PN_Search.add(LB2_SearchFax);
		PN_Search.add(LB2_SearchMail);
		
		//検索ボタン
		JButton SearchBtn = B00110FrameParts.BtnSet(450,125,100,20,"検索",11);
		PN_Search.add(SearchBtn);
		
		String[] columnNames01 = {
				"FG"
				,"荷主グループCD"
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
				,"データ登録日時"
				,"データ更新日時"
				,"登録者コード"
				,"更新者コード"
				,"パスワード"
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
		column = columnModel01.getColumn( 1);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//荷主グループCD"
		column = columnModel01.getColumn( 2);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//荷主名1
		column = columnModel01.getColumn( 3);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//荷主名2
		column = columnModel01.getColumn( 4);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//荷主名3
		column = columnModel01.getColumn( 5);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//郵便番号
		column = columnModel01.getColumn( 6);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//住所1
		column = columnModel01.getColumn( 7);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//住所
		column = columnModel01.getColumn( 8);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//住所3
		column = columnModel01.getColumn( 9);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//電話番号
		column = columnModel01.getColumn(10);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//FAX
		column = columnModel01.getColumn(11);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//メールアドレス
		column = columnModel01.getColumn(12);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//コメント1
		column = columnModel01.getColumn(13);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//コメント2
		column = columnModel01.getColumn(14);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//コメント3
		column = columnModel01.getColumn(15);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//データ登録日時
		column = columnModel01.getColumn(16);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//データ更新日時
		column = columnModel01.getColumn(17);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//登録者コード
		column = columnModel01.getColumn(18);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//更新者コード
		column = columnModel01.getColumn(19);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//パスワード
		
		//スクロール用設定
		JScrollPane scpn01 = B00110FrameParts.JScrollPaneSet(10,200,740,420,tb01);
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

					String GetSearchClGpCD = TB_SearchClGpCD.getText();		if(null==GetSearchClGpCD){GetSearchClGpCD="";}
					String GetSearchCLGpName = TB_SearchCLGpName .getText();if(null==GetSearchCLGpName){GetSearchCLGpName="";}
					String GetSearchPost = TB_SearchClGpCD.getText();		if(null==GetSearchPost){GetSearchPost="";}
					String GetSearchAdd = TB_SearchClGpCD.getText();		if(null==GetSearchAdd){GetSearchAdd="";}
					String GetSearchCom = TB_SearchClGpCD.getText();		if(null==GetSearchCom){GetSearchCom="";}
					
					String GetSearchTel = TB_SearchClGpCD.getText();		if(null==GetSearchTel){GetSearchTel="";}
					String GetSearchFax = TB_SearchClGpCD.getText();		if(null==GetSearchFax){GetSearchFax="";}
					String GetSearchMail = TB_SearchClGpCD.getText();		if(null==GetSearchMail){GetSearchMail="";}
					
					GetSearchPost = B00020ToolsTextControl.num_only_String(GetSearchPost);
					GetSearchTel  = B00020ToolsTextControl.num_only_String(GetSearchTel);
					GetSearchFax  = B00020ToolsTextControl.num_only_String(GetSearchFax);
					
					ArrayList<String> SearchClGpCD = new ArrayList<String>();
					ArrayList<String> SearchCLGpName = new ArrayList<String>();
					ArrayList<String> SearchPost = new ArrayList<String>();
					ArrayList<String> SearchAdd = new ArrayList<String>();
					ArrayList<String> SearchTel = new ArrayList<String>();
					ArrayList<String> SearchFax = new ArrayList<String>();
					ArrayList<String> SearchMail = new ArrayList<String>();
					ArrayList<String> SearchCom = new ArrayList<String>();
					boolean AllSearch = true;
					
					if(!"".equals(GetSearchClGpCD)){SearchClGpCD.add(GetSearchClGpCD);}
					if(!"".equals(GetSearchCLGpName)){SearchCLGpName.add(GetSearchCLGpName);}
					if(!"".equals(GetSearchPost)){SearchPost.add(GetSearchPost);}
					if(!"".equals(GetSearchAdd)){SearchAdd.add(GetSearchAdd);}
					if(!"".equals(GetSearchCom)){SearchCom.add(GetSearchCom);}
					
					if(!"".equals(GetSearchTel)){SearchTel.add(GetSearchTel);}
					if(!"".equals(GetSearchFax)){SearchFax.add(GetSearchFax);}
					if(!"".equals(GetSearchMail)){SearchMail.add(GetSearchMail);}

					Object[][] ClGpMstRt = M00010ClGpMstRt.ClGpMstRt(
								SearchClGpCD,SearchCLGpName,SearchPost,
								SearchAdd,SearchTel,SearchFax,SearchMail,SearchCom,AllSearch);
					
					for(int i=0;i<ClGpMstRt.length;i++) {
						Object[] SetOb = new Object[20];
						SetOb[ 0] = false;
						SetOb[ 1] = ""+ClGpMstRt[i][ 0];
						SetOb[ 2] = ""+ClGpMstRt[i][ 1];
						SetOb[ 3] = ""+ClGpMstRt[i][ 2];
						SetOb[ 4] = ""+ClGpMstRt[i][ 3];
						SetOb[ 5] = ""+ClGpMstRt[i][ 4];
						SetOb[ 6] = ""+ClGpMstRt[i][ 5];
						SetOb[ 7] = ""+ClGpMstRt[i][ 6];
						SetOb[ 8] = ""+ClGpMstRt[i][ 7];
						SetOb[ 9] = ""+ClGpMstRt[i][ 8];
						SetOb[10] = ""+ClGpMstRt[i][ 9];
						SetOb[11] = ""+ClGpMstRt[i][10];
						SetOb[12] = ""+ClGpMstRt[i][11];
						SetOb[13] = ""+ClGpMstRt[i][12];
						SetOb[14] = ""+ClGpMstRt[i][13];
						SetOb[15] = ""+ClGpMstRt[i][14];
						SetOb[16] = ""+ClGpMstRt[i][15];
						SetOb[17] = ""+ClGpMstRt[i][16];
						SetOb[18] = ""+ClGpMstRt[i][17];
						SetOb[19] = ""+ClGpMstRt[i][18];
						
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
					String TgtClGp = "";
					for(int i=0;i<RowCount;i++) {
						if((boolean)tableModel_ms01.getValueAt(i, 0)) {
							TgtClGp = ""+tableModel_ms01.getValueAt(i, 1);	if(null==TgtClGp) {TgtClGp="";}
						}
					}
					if(!"".equals(TgtClGp)) {
						SetX=main_fm.getX();
						SetY=main_fm.getY();

						main_fm.setVisible(false);
						main_fm.dispose();
						WM00021ClGpMstRenewAndCrwate.ClGpMstRenewAndCrwate(0,0,TgtClGp);
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
					WM00021ClGpMstRenewAndCrwate.ClGpMstRenewAndCrwate(0,0,"");
					
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
					B10010TableControl.TableOutPutCsv("出力先選択","荷主グループマスタ検索結果",tb01);
					RenewFg = true;
				}
			}
		});
		
		//エクセル出力ボタン押下時の挙動
		ExcelBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					B10010TableControl.TableOutPutExcel("出力先選択","荷主グループマスタ検索結果",tb01);
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