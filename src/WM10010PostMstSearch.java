import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
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

public class WM10010PostMstSearch{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	public static void PostMstSearch(int x,int y) {
		A00000Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;

		final JFrame main_fm = B00110FrameParts.FrameCreate(x,y,780,750,"Corgi00郵便番号検索","");
		JLabel userinfo = B00110FrameParts.UserInfo();
		JButton exit_btn = B00110FrameParts.ExitBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		
		//検索条件パネル
		JPanel PN_Search = B00110FrameParts.JPanelSet(10,40,740,100,"White");
		JLabel PN_SearchLabel = B00110FrameParts.JLabelSet(10,0,150,20,"検索条件",11,0);
		PN_Search.add(PN_SearchLabel);
		main_fm.add(PN_Search);
		
		//検索条件
		JLabel LB_SearchPOST  = B00110FrameParts.JLabelSet(	                  0,25,100,20,"郵便番号:",11,1);
		JLabel LB_SearchAdd   = B00110FrameParts.JLabelSet(	                  0,50,100,20,"住所:"    ,11,1);
		final JTextField TB_SearchPOST  = B00110FrameParts.JTextFieldSet(		100,25,100,20,"",11,0);
		final JTextField TB_SearchAdd   =B00110FrameParts.JTextFieldSet(		100,50,100,20,"",11,0);
		JLabel LB2_SearchPOST  = B00110FrameParts.JLabelSet(	                200,25,100,20,"で始まる" ,11,0);
		JLabel LB2_SearchAdd   = B00110FrameParts.JLabelSet(	                200,50,100,20,"を含む"   ,11,0);
		
		PN_Search.add(LB_SearchPOST);
		PN_Search.add(LB_SearchAdd);
		PN_Search.add(TB_SearchPOST);
		PN_Search.add(TB_SearchAdd);
		PN_Search.add(LB2_SearchPOST);
		PN_Search.add(LB2_SearchAdd);
		
		//検索ボタン
		JButton SearchBtn = B00110FrameParts.BtnSet(100,75,100,20,"検索",11);
		PN_Search.add(SearchBtn);
		
		String[] columnNames01 = {
				"FG"
				,"郵便番号"
				,"県"
				,"市区町村"
				,"町丁目"
				,"市区町村CD"
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
		column = columnModel01.getColumn( 1);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//郵便番号
		column = columnModel01.getColumn( 2);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//県
		column = columnModel01.getColumn( 3);	column.setPreferredWidth(200*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//市区町村
		column = columnModel01.getColumn( 4);	column.setPreferredWidth(200*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//町丁目
		column = columnModel01.getColumn( 5);	column.setPreferredWidth(120*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//市区町村CD
		
		//スクロール用設定
		JScrollPane scpn01 = B00110FrameParts.JScrollPaneSet(10,160,740,460,tb01);
		main_fm.add(scpn01);
		
		//CSVボタン
		JButton CsvBtn = B00110FrameParts.BtnSet(10,660,100,20,"csv出力",11);
		main_fm.add(CsvBtn);
		
		JLabel LB_RenewBtn  = B00110FrameParts.JLabelSet( 130,640,100,20,"チェック行を" ,11,2);
		main_fm.add(LB_RenewBtn);
		
		//修正ボタン
		JButton RenewBtn = B00110FrameParts.BtnSet(		130,660,100,20,"修正",11);
		main_fm.add(RenewBtn);
		
		//新規登録ボタン
		JButton CreateBtn = B00110FrameParts.BtnSet(		250,660,100,20,"新規登録",11);
		main_fm.add(CreateBtn);
		
		//一括登録ボタン
		JButton CreateSumBtn = B00110FrameParts.BtnSet(	370,660,100,20,"一括登録",11);
		main_fm.add(CreateSumBtn);
		
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
					
					String GetSearchPOST = TB_SearchPOST.getText();	if(null==GetSearchPOST) {GetSearchPOST="";}
					String GetSearchAdd = TB_SearchAdd.getText();	if(null==GetSearchAdd) {GetSearchAdd="";}
					
					ArrayList SearchPOST = new ArrayList();
					ArrayList SearchAdd = new ArrayList();
					boolean AllSearch = true;
					
					if(!"".equals(GetSearchPOST)) {
						SearchPOST.add(GetSearchPOST);
					}
					
					if(!"".equals(GetSearchAdd)) {
						SearchAdd.add(GetSearchAdd);
					}
					
					Object[][] PostRt = M10010PostMstRt.PostRt(SearchPOST,SearchAdd,AllSearch);
					
					for(int i=0;i<PostRt.length;i++) {
						Object[] SetOb = new Object[6];
						SetOb[0] = false;
						SetOb[1] = ""+PostRt[i][0];	//郵便番号
						SetOb[2] = ""+PostRt[i][1];	//県
						SetOb[3] = ""+PostRt[i][2];	//市区町村
						SetOb[4] = ""+PostRt[i][3];	//町丁目
						SetOb[5] = ""+PostRt[i][4];	//市区町村CD
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
					String TgtPost = "";
					for(int i=0;i<RowCount;i++) {
						if((boolean)tableModel_ms01.getValueAt(i, 0)) {
							TgtPost = ""+tableModel_ms01.getValueAt(i, 1);	if(null==TgtPost) {TgtPost="";}
						}
					}
					if(!"".equals(TgtPost)) {
						SetX=main_fm.getX();
						SetY=main_fm.getY();

						main_fm.setVisible(false);
						main_fm.dispose();
						WM10011PostMstRenewAndCreate.PostMstRenewAndCreate(0,0,TgtPost);
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
					WM10011PostMstRenewAndCreate.PostMstRenewAndCreate(0,0,"");
					
					RenewFg = true;
				}
			}
		});
		
		//一括登録ボタン押下時の挙動
		CreateSumBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					
					SetX=main_fm.getX();
					SetY=main_fm.getY();

					main_fm.setVisible(false);
					main_fm.dispose();
					WM10012PostMstCreateSum.PostMstCreateSum(0,0);
					
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
							if("true".equals(""+tb01.getValueAt(i,0))){
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
					String Selected = B00080FolderSelect.FolderSelect("出力先選択");
					if(null!=Selected) {
						int row_count = tb01.getRowCount();
						int col_count = tb01.getColumnCount();
						String NowDTM=B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[1].replace(" ", "").replace("/", "").replace(":", "");
						String FileName = "郵便番号検索結果"+NowDTM+".csv";
						ArrayList<String> OutString = new ArrayList<String>();
						
						String SetSt = "";
						for(int i=0;i<col_count;i++) {
							//項目名取得
							SetSt=SetSt+tb01.getColumnName(i)+",";
						}
						OutString.add(SetSt);
						for(int i=0;i<row_count;i++){
							SetSt = "";
							for(int i01=0;i01<col_count;i01++) {
								//項目値取得カンマ除去 改行コード除去
								String WST = ""+tb01.getValueAt(i,i01);
								SetSt=SetSt+WST.replace(",", "").replace("\n", "").replace("\"", "")+",";
							}
							OutString.add(SetSt);
						}
						String now_dtm = B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[1];
						now_dtm=now_dtm.replace("/", "").replace(":", "").replace(" ", "");
						FileName = now_dtm + FileName;

						//ファイルに出力
						String FP = Selected+"\\"+FileName;
						B00030ToolsTextExport.txt_exp2(OutString,FP,"UTF-8");

						//ファイル開く
						File file = new File(FP);
						Desktop desktop = Desktop.getDesktop();
						try {
							desktop.open(file);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
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