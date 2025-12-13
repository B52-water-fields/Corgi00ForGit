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

public class WM00010WhMstSearch{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	public static void WhMstSearch(int x,int y) {
		A00000Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		
		final JFrame main_fm = B00110FrameParts.FrameCreate(x,y,780,750,"Corgi00倉庫（事業所）検索","");
		JLabel userinfo = B00110FrameParts.UserInfo();
		JButton exit_btn = B00110FrameParts.ExitBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		
		//検索条件パネル
		JPanel PN_Search = B00110FrameParts.JPanelSet(10,40,740,150,"White");
		JLabel PN_SearchLabel = B00110FrameParts.JLabelSet(10,0,150,20,"検索条件",11,0);
		PN_Search.add(PN_SearchLabel);
		main_fm.add(PN_Search);
		
		//検索条件
		JLabel LB_SearchWHCD  = B00110FrameParts.JLabelSet(	  0, 25,100,20,"倉庫CD:",11,1);
		JLabel LB_SearchWHName  = B00110FrameParts.JLabelSet(	  0, 50,100,20,"倉庫名:",11,1);
		JLabel LB_SearchPost  = B00110FrameParts.JLabelSet(	  0, 75,100,20,"郵便番号:",11,1);
		JLabel LB_SearchAdd  = B00110FrameParts.JLabelSet(		  0,100,100,20,"住所:",11,1);
		JLabel LB_SearchCom  = B00110FrameParts.JLabelSet(		  0,125,100,20,"コメント:",11,1);
		
		JLabel LB_SearchTel  = B00110FrameParts.JLabelSet(		350, 25,100,20,"Tel:",11,1);
		JLabel LB_SearchFax  = B00110FrameParts.JLabelSet(		350, 50,100,20,"Fax:",11,1);
		JLabel LB_SearchMail  = B00110FrameParts.JLabelSet(	350, 75,100,20,"Mail:",11,1);		
		JLabel LB_SearchPTMSCD  = B00110FrameParts.JLabelSet(	350,100,100,20,"基幹SysCD:",11,1);
		
		final JTextField TB_SearchWHCD  = B00110FrameParts.JTextFieldSet(		100, 25,100,20,"",11,0);
		final JTextField TB_SearchWHName  = B00110FrameParts.JTextFieldSet(	100, 50,150,20,"",11,0);
		final JTextField TB_SearchPost  = B00110FrameParts.JTextFieldSet(		100, 75,100,20,"",11,0);
		final JTextField TB_SearchAdd  = B00110FrameParts.JTextFieldSet(		100,100,150,20,"",11,0);
		final JTextField TB_SearchCom  = B00110FrameParts.JTextFieldSet(		100,125,150,20,"",11,0);
		
		final JTextField TB_SearchTel  = B00110FrameParts.JTextFieldSet(		450, 25,100,20,"",11,0);
		final JTextField TB_SearchFax  = B00110FrameParts.JTextFieldSet(		450, 50,100,20,"",11,0);
		final JTextField TB_SearchMail  = B00110FrameParts.JTextFieldSet(		450, 75,100,20,"",11,0);		
		final JTextField TB_SearchPTMSCD  = B00110FrameParts.JTextFieldSet(	450,100,100,20,"",11,0);
		
		JLabel LB2_SearchWHCD  = B00110FrameParts.JLabelSet(	200, 25,100,20,"と一致"   ,11,0);
		JLabel LB2_SearchWHName  = B00110FrameParts.JLabelSet(	250, 50,100,20,"を含む"   ,11,0);
		JLabel LB2_SearchPost  = B00110FrameParts.JLabelSet(	200, 75,100,20,"で始まる" ,11,0);
		JLabel LB2_SearchAdd  = B00110FrameParts.JLabelSet(	250,100,100,20,"を含む"   ,11,0);
		JLabel LB2_SearchCom  = B00110FrameParts.JLabelSet(	250,125,100,20,"を含む"   ,11,0);
				
		JLabel LB2_SearchTel  = B00110FrameParts.JLabelSet(	550, 25,100,20,"を含む"   ,11,0);
		JLabel LB2_SearchFax  = B00110FrameParts.JLabelSet(	550, 50,100,20,"を含む"   ,11,0);
		JLabel LB2_SearchMail  = B00110FrameParts.JLabelSet(	550, 75,100,20,"を含む"   ,11,0);
		JLabel LB2_SearchPTMSCD  = B00110FrameParts.JLabelSet(	550,100,100,20,"と一致"   ,11,0);
		
		PN_Search.add(LB_SearchWHCD);
		PN_Search.add(LB_SearchWHName);
		PN_Search.add(LB_SearchPost);
		PN_Search.add(LB_SearchAdd);
		PN_Search.add(LB_SearchCom);
		
		PN_Search.add(LB_SearchTel);
		PN_Search.add(LB_SearchFax);
		PN_Search.add(LB_SearchMail);		
		PN_Search.add(LB_SearchPTMSCD);
		
		PN_Search.add(TB_SearchWHCD);
		PN_Search.add(TB_SearchWHName);
		PN_Search.add(TB_SearchPost);
		PN_Search.add(TB_SearchAdd);
		PN_Search.add(TB_SearchCom);
		
		PN_Search.add(TB_SearchTel);
		PN_Search.add(TB_SearchFax);
		PN_Search.add(TB_SearchMail);		
		PN_Search.add(TB_SearchPTMSCD);
		
		PN_Search.add(LB2_SearchWHCD);
		PN_Search.add(LB2_SearchWHName);
		PN_Search.add(LB2_SearchPost);
		PN_Search.add(LB2_SearchAdd);
		PN_Search.add(LB2_SearchCom);
		
		PN_Search.add(LB2_SearchTel);
		PN_Search.add(LB2_SearchFax);
		PN_Search.add(LB2_SearchMail);
		PN_Search.add(LB2_SearchPTMSCD);
		
		main_fm.setVisible(true);
		//検索ボタン
		JButton SearchBtn = B00110FrameParts.BtnSet(450,125,100,20,"検索",11);
		PN_Search.add(SearchBtn);
		
		String[] columnNames01 = {
				"FG"
				,"倉庫コード"
				,"拠点倉庫名"
				,"拠点倉庫郵便番号"
				,"拠点倉庫住所1"
				,"拠点倉庫住所2"
				,"拠点倉庫電話"
				,"拠点倉庫FAX"
				,"拠点倉庫MAIL"
				,"コメント１"
				,"コメント２"
				,"コメント３"
				,"基幹SYSCD"
				,"データ登録日時"
				,"データ更新日時"
				,"登録者"
				,"更新者"
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
		column = columnModel01.getColumn( 2);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//拠点倉庫名
		column = columnModel01.getColumn( 3);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//拠点倉庫郵便番号
		column = columnModel01.getColumn( 4);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//拠点倉庫住所1
		column = columnModel01.getColumn( 5);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//拠点倉庫住所2
		column = columnModel01.getColumn( 6);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//拠点倉庫電話
		column = columnModel01.getColumn( 7);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//拠点倉庫FAX
		column = columnModel01.getColumn( 8);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//拠点倉庫MAIL
		column = columnModel01.getColumn( 9);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//コメント１
		column = columnModel01.getColumn(10);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//コメント２
		column = columnModel01.getColumn(11);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//コメント３
		column = columnModel01.getColumn(12);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//基幹SYSCD
		column = columnModel01.getColumn(13);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//データ登録日時
		column = columnModel01.getColumn(14);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//データ更新日時
		column = columnModel01.getColumn(15);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//登録者
		column = columnModel01.getColumn(16);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//更新者
		
		//スクロール用設定
		JScrollPane scpn01 = B00110FrameParts.JScrollPaneSet(10,200,740,420,tb01);
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

					String GetSearchWHCD   = TB_SearchWHCD.getText();	if(null==GetSearchWHCD  ){GetSearchWHCD   = "";}
					String GetSearchWHName = TB_SearchWHName.getText();	if(null==GetSearchWHName){GetSearchWHName = "";}
					String GetSearchPost   = TB_SearchPost.getText();	if(null==GetSearchPost  ){GetSearchPost   = "";}
					String GetSearchAdd    = TB_SearchAdd.getText();	if(null==GetSearchAdd   ){GetSearchAdd    = "";}
					String GetSearchCom    = TB_SearchCom.getText();	if(null==GetSearchCom   ){GetSearchCom    = "";}
					String GetSearchTel    = TB_SearchTel.getText();	if(null==GetSearchTel   ){GetSearchTel    = "";}
					String GetSearchFax    = TB_SearchFax.getText();	if(null==GetSearchFax   ){GetSearchFax    = "";}
					String GetSearchMail   = TB_SearchMail.getText();	if(null==GetSearchMail  ){GetSearchMail   = "";}		
					String GetSearchPTMSCD = TB_SearchPTMSCD.getText();	if(null==GetSearchPTMSCD){GetSearchPTMSCD = "";}
					
					GetSearchPost = B00020ToolsTextControl.num_only_String(GetSearchPost);
					GetSearchTel  = B00020ToolsTextControl.num_only_String(GetSearchTel);
					GetSearchFax  = B00020ToolsTextControl.num_only_String(GetSearchFax);
					
					ArrayList<String> SearchWHCD = new ArrayList<String>();
					ArrayList<String> SearchWHName = new ArrayList<String>();
					ArrayList<String> SearchPost = new ArrayList<String>();
					ArrayList<String> SearchAdd = new ArrayList<String>();
					ArrayList<String> SearchTel = new ArrayList<String>();
					ArrayList<String> SearchFax = new ArrayList<String>();
					ArrayList<String> SearchMail = new ArrayList<String>();
					ArrayList<String> SearchCom = new ArrayList<String>();
					ArrayList<String> SearchPTMSCD = new ArrayList<String>();
					boolean AllSearch = true;
					
					if(!"".equals(GetSearchWHCD  )){SearchWHCD.add(  GetSearchWHCD);}
					if(!"".equals(GetSearchWHName)){SearchWHName.add(GetSearchWHName);}
					if(!"".equals(GetSearchPost  )){SearchPost.add(  GetSearchPost);}
					if(!"".equals(GetSearchAdd   )){SearchAdd.add(   GetSearchAdd);}
					if(!"".equals(GetSearchCom   )){SearchCom.add(   GetSearchCom);}
					if(!"".equals(GetSearchTel   )){SearchTel.add(   GetSearchTel);}
					if(!"".equals(GetSearchFax   )){SearchFax.add(   GetSearchFax);}
					if(!"".equals(GetSearchMail  )){SearchMail.add(  GetSearchMail);}		
					if(!"".equals(GetSearchPTMSCD)){SearchPTMSCD.add(GetSearchPTMSCD);}

					Object[][] WhMstRt = M00001WhMstRt.WhMstRt(
								SearchWHCD,SearchWHName,SearchPost,
								SearchAdd,SearchTel,SearchFax,SearchMail,
								SearchCom,SearchPTMSCD,
								AllSearch);
					
					for(int i=0;i<WhMstRt.length;i++) {
						Object[] SetOb = new Object[17];
						SetOb[ 0] = false;
						SetOb[ 1] = ""+WhMstRt[i][ 0];
						SetOb[ 2] = ""+WhMstRt[i][ 1];
						SetOb[ 3] = ""+WhMstRt[i][ 2];
						SetOb[ 4] = ""+WhMstRt[i][ 3];
						SetOb[ 5] = ""+WhMstRt[i][ 4];
						SetOb[ 6] = ""+WhMstRt[i][ 5];
						SetOb[ 7] = ""+WhMstRt[i][ 6];
						SetOb[ 8] = ""+WhMstRt[i][ 7];
						SetOb[ 9] = ""+WhMstRt[i][ 8];
						SetOb[10] = ""+WhMstRt[i][ 9];
						SetOb[11] = ""+WhMstRt[i][10];
						SetOb[12] = ""+WhMstRt[i][11];
						SetOb[13] = ""+WhMstRt[i][12];
						SetOb[14] = ""+WhMstRt[i][13];
						SetOb[15] = ""+WhMstRt[i][14];
						SetOb[16] = ""+WhMstRt[i][15];
						
						tableModel_ms01.addRow(SetOb);
					}
					
					RenewFg = true;
				}
			}
		});
		//修正ボタン
		RenewBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				RenewFg = false;
				String TgtWhCd = "";
				int RowCount = tableModel_ms01.getRowCount();
				for(int i=0;i<RowCount;i++) {
					if((boolean)tableModel_ms01.getValueAt(i, 0)) {
						TgtWhCd = ""+tableModel_ms01.getValueAt(i, 1);
					}
				}
				if(null!=TgtWhCd&&!"".equals(TgtWhCd)) {
					SetX=main_fm.getX();
					SetY=main_fm.getY();
	
					main_fm.setVisible(false);
					main_fm.dispose();
					WM00011WhMstRenewAndCreate.WhMstRenewAndCreate(0,0,TgtWhCd);
				}
				RenewFg = true;
			}
		});
				
		//新規登録ボタン
		CreateBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				RenewFg = false;
				String TgtWhCd = "";
				
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				WM00011WhMstRenewAndCreate.WhMstRenewAndCreate(0,0,TgtWhCd);
				RenewFg = true;
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
					B10010TableControl.TableOutPutCsv("出力先選択","倉庫マスタ検索結果",tb01);
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