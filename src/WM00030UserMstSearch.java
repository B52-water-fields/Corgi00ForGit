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
		
		final JFrame main_fm = B00110FrameParts.FrameCreate(x,y,780,750,"Corgi00ユーザー検索","");
		JLabel userinfo = B00110FrameParts.UserInfo();
		JButton exit_btn = B00110FrameParts.ExitBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		
		//検索条件パネル
		JPanel PN_Search = B00110FrameParts.JPanelSet(10,40,740,175,"White");
		JLabel PN_SearchLabel = B00110FrameParts.JLabelSet(10,0,150,20,"検索条件",11,0);
		PN_Search.add(PN_SearchLabel);
		main_fm.add(PN_Search);
		
		String[] columnNames01 = {
				"FG"
				,"倉庫コード"
				,"倉庫名"
				,"運送会社CD"
				,"運送会社名"
				,"ユーザーCD"
				,"パスワード"
				,"権限区分"
				,"標準車輛CD"
				,"車両名称01"
				,"車両名称02"
				,"車両名称03"
				,"ユーザー名1"
				,"ユーザー名2"
				,"ユーザー名3"
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
		column = columnModel01.getColumn( 1);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//倉庫コード
		column = columnModel01.getColumn( 2);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//倉庫名
		column = columnModel01.getColumn( 3);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//運送会社CD
		column = columnModel01.getColumn( 4);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//運送会社名
		column = columnModel01.getColumn( 5);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//ユーザーCD
		column = columnModel01.getColumn( 6);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//パスワード
		column = columnModel01.getColumn( 7);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//権限区分
		column = columnModel01.getColumn( 8);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//標準車輛CD
		column = columnModel01.getColumn( 9);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//車両名称01
		column = columnModel01.getColumn(10);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//車両名称02
		column = columnModel01.getColumn(11);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//車両名称03
		column = columnModel01.getColumn(12);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//ユーザー名1
		column = columnModel01.getColumn(13);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//ユーザー名2
		column = columnModel01.getColumn(14);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//ユーザー名3
		column = columnModel01.getColumn(15);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//郵便番号
		column = columnModel01.getColumn(16);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//住所1
		column = columnModel01.getColumn(17);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//住所2
		column = columnModel01.getColumn(18);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//住所3
		column = columnModel01.getColumn(19);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//電話番号
		column = columnModel01.getColumn(20);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//FAX
		column = columnModel01.getColumn(21);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//メールアドレス
		column = columnModel01.getColumn(22);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//コメント1
		column = columnModel01.getColumn(23);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//コメント2
		column = columnModel01.getColumn(24);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//コメント3
		column = columnModel01.getColumn(25);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//登録者コード
		column = columnModel01.getColumn(26);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//更新者コード
		column = columnModel01.getColumn(27);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//データ登録日時
		column = columnModel01.getColumn(28);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//データ更新日時
		column = columnModel01.getColumn(29);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//基幹システムユーザーコード
		column = columnModel01.getColumn(30);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//削除区分
		
		//スクロール用設定
		JScrollPane scpn01 = B00110FrameParts.JScrollPaneSet(10,220,740,400,tb01);
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
		JButton SearchBtn = B00110FrameParts.BtnSet(450,150,100,20,"検索",11);
		PN_Search.add(SearchBtn);
		
		main_fm.setVisible(true);
		
		RenewFg = true;
		
		//修正ボタン押下時の挙動
		RenewBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					int RowCount = tableModel_ms01.getRowCount();
					String TgtUser = "";
					for(int i=0;i<RowCount;i++) {
						if((boolean)tableModel_ms01.getValueAt(i, 0)) {
							TgtUser = ""+tableModel_ms01.getValueAt(i, 1);	if(null==TgtUser) {TgtUser="";}
						}
					}
					if(!"".equals(TgtUser)) {
						SetX=main_fm.getX();
						SetY=main_fm.getY();

						main_fm.setVisible(false);
						main_fm.dispose();
						WM00031UserMstRenewAndCreate.UserMstRenewAndCreate(0,0,TgtUser);
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
					WM00031UserMstRenewAndCreate.UserMstRenewAndCreate(0,0,"");
					
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
						String FileName = "倉庫マスタ検索結果"+NowDTM+".csv";
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