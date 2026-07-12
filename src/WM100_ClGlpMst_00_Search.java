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

public class WM100_ClGlpMst_00_Search{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	public static void ClGlpMstSearch(int x,int y) {
		A00000_Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		
		final JFrame main_fm = B100_FrameParts.FrameCreate(x,y,780,750,"Corgi00荷主グループ検索","");
		JLabel userinfo = B100_FrameParts.UserInfo();
		JButton exit_btn = B100_FrameParts.ExitBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		
		//検索条件パネル
		JPanel PN_Search 		= B100_FrameParts.JPanelSet(10,40,740,150,"White");
		JLabel PN_SearchLabel 	= B100_FrameParts.JLabelSet(10,0,150,20,"検索条件",11,0);
		PN_Search.add(PN_SearchLabel);
		main_fm.add(PN_Search);
		
		JLabel LB_SearchClGpCD  	= B100_FrameParts.JLabelSet(				  0, 25,100,20,"荷主GlpCD:"	,11,1);
		JLabel LB_SearchCLGpName  	= B100_FrameParts.JLabelSet(				  0, 50,100,20,"名称:"		,11,1);
		JLabel LB_SearchPost  		= B100_FrameParts.JLabelSet(				  0, 75,100,20,"郵便番号:"	,11,1);
		JLabel LB_SearchAdd 		= B100_FrameParts.JLabelSet(				  0,100,100,20,"住所:"		,11,1);
		JLabel LB_SearchCom  		= B100_FrameParts.JLabelSet(				  0,125,100,20,"コメント:"	,11,1);
		
		JLabel LB_SearchTel  		= B100_FrameParts.JLabelSet(				350, 25,100,20,"Tel:"		,11,1);
		JLabel LB_SearchFax  		= B100_FrameParts.JLabelSet(				350, 50,100,20,"Fax:"		,11,1);
		JLabel LB_SearchMail  		= B100_FrameParts.JLabelSet(				350, 75,100,20,"Mail:"		,11,1);
		
		final JTextField TB_SearchClGpCD = B100_FrameParts.JTextFieldSet(		100, 25,100,20,"",11,0);
		final JTextField TB_SearchCLGpName = B100_FrameParts.JTextFieldSet(	100, 50,150,20,"",11,0);
		final JTextField TB_SearchPost = B100_FrameParts.JTextFieldSet(		100, 75,100,20,"",11,0);
		final JTextField TB_SearchAdd = B100_FrameParts.JTextFieldSet(		100,100,150,20,"",11,0);
		final JTextField TB_SearchCom  = B100_FrameParts.JTextFieldSet(		100,125,150,20,"",11,0);
		
		final JTextField TB_SearchTel = B100_FrameParts.JTextFieldSet(		450, 25,100,20,"",11,0);
		final JTextField TB_SearchFax  = B100_FrameParts.JTextFieldSet(		450, 50,100,20,"",11,0);
		final JTextField TB_SearchMail = B100_FrameParts.JTextFieldSet(		450, 75,150,20,"",11,0);
		
		JLabel LB2_SearchClGpCD  = B100_FrameParts.JLabelSet(					200, 25,100,20,"と一致"	,11,0);
		JLabel LB2_SearchCLGpName  = B100_FrameParts.JLabelSet(	 			250, 50,100,20,"を含む"	,11,0);
		JLabel LB2_SearchPost  = B100_FrameParts.JLabelSet(		 			200, 75,100,20,"で始まる"	,11,0);
		JLabel LB2_SearchAdd  = B100_FrameParts.JLabelSet(		 				250,100,100,20,"を含む"	,11,0);
		JLabel LB2_SearchCom  = B100_FrameParts.JLabelSet(		 				250,125,100,20,"を含む"	,11,0);
		
		JLabel LB2_SearchTel  = B100_FrameParts.JLabelSet(		 				550, 25,100,20,"を含む"	,11,0);
		JLabel LB2_SearchFax  = B100_FrameParts.JLabelSet(		 				550, 50,100,20,"を含む"	,11,0);
		JLabel LB2_SearchMail  = B100_FrameParts.JLabelSet(		 			600, 75,100,20,"を含む"	,11,0);
		
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
		JButton SearchBtn = B100_FrameParts.BtnSet(450,125,100,20,"検索",11);
		PN_Search.add(SearchBtn);
		
		Object[][] RtSettingClGpMstRt= M100_ClGpMstRt.RtClGpMstRt();
		
		String[] columnNames01 = new String[RtSettingClGpMstRt.length+1];
		
		columnNames01[0] = "Fg";
		for(int i=0;i<RtSettingClGpMstRt.length;i++) {
			columnNames01[1+(int)RtSettingClGpMstRt[i][1]] = ""+RtSettingClGpMstRt[i][3];
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
		
		for(int i=0;i<RtSettingClGpMstRt.length;i++) {
			if("int".equals((String)RtSettingClGpMstRt[i][2])||"float".equals((String)RtSettingClGpMstRt[i][2])) {
				column = columnModel01.getColumn(1+(int)RtSettingClGpMstRt[i][1]);	column.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.rightCellRenderer());
			}else {
				column = columnModel01.getColumn(1+(int)RtSettingClGpMstRt[i][1]);	column.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.leftCellRenderer());
			}
		}
		//スクロール用設定
		JScrollPane scpn01 = B100_FrameParts.JScrollPaneSet(10,200,740,420,tb01);
		main_fm.add(scpn01);
		
		//CSVボタン
		JButton CsvBtn = B100_FrameParts.BtnSet(			 10,660,100,20,"csv出力",11);
		main_fm.add(CsvBtn);
		
		JLabel LB_RenewBtn  = B100_FrameParts.JLabelSet(	130,640,100,20,"チェック行を" ,11,2);
		main_fm.add(LB_RenewBtn);
		
		//修正ボタン
		JButton RenewBtn = B100_FrameParts.BtnSet(			130,660,100,20,"修正",11);
		main_fm.add(RenewBtn);
		
		//新規登録ボタン
		JButton CreateBtn = B100_FrameParts.BtnSet(		250,660,100,20,"新規登録",11);
		main_fm.add(CreateBtn);
		
		//Excelボタン
		JButton ExcelBtn = B100_FrameParts.BtnSet(			370,660,100,20,"Excel出力",11);
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

					String GetSearchClGpCD = TB_SearchClGpCD.getText();		if(null==GetSearchClGpCD){GetSearchClGpCD="";}
					String GetSearchCLGpName = TB_SearchCLGpName .getText();if(null==GetSearchCLGpName){GetSearchCLGpName="";}
					String GetSearchPost = TB_SearchPost.getText();		if(null==GetSearchPost){GetSearchPost="";}
					String GetSearchAdd = TB_SearchAdd.getText();		if(null==GetSearchAdd){GetSearchAdd="";}
					String GetSearchCom = TB_SearchCom.getText();		if(null==GetSearchCom){GetSearchCom="";}
					
					String GetSearchTel = TB_SearchTel.getText();		if(null==GetSearchTel){GetSearchTel="";}
					String GetSearchFax = TB_SearchFax.getText();		if(null==GetSearchFax){GetSearchFax="";}
					String GetSearchMail = TB_SearchMail.getText();		if(null==GetSearchMail){GetSearchMail="";}
					
					GetSearchPost = B100_TextControl.num_only_String(GetSearchPost);
					GetSearchTel  = B100_TextControl.num_only_String(GetSearchTel);
					GetSearchFax  = B100_TextControl.num_only_String(GetSearchFax);
					
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
					
					TB_SearchClGpCD.setText(GetSearchClGpCD);
					TB_SearchCLGpName.setText(GetSearchCLGpName);
					TB_SearchPost.setText(GetSearchPost);
					TB_SearchAdd.setText(GetSearchAdd);
					TB_SearchCom.setText(GetSearchCom);
					
					TB_SearchTel.setText(GetSearchTel);
					TB_SearchFax.setText(GetSearchFax);
					TB_SearchMail.setText(GetSearchMail);

					Object[][] ClGpMstRt = M100_ClGpMstRt.ClGpMstRt(
								SearchClGpCD,SearchCLGpName,SearchPost,
								SearchAdd,SearchTel,SearchFax,SearchMail,SearchCom,AllSearch);
					
					Object[][] RtSettingClGpMstRt= M100_ClGpMstRt.RtClGpMstRt();
					
					for(int i=0;i<ClGpMstRt.length;i++) {
						
						Object[] SetOb = new Object[RtSettingClGpMstRt.length+1];
						SetOb[ 0] = false;
						for(int i01=0;i01<RtSettingClGpMstRt.length;i01++) {
							SetOb[1+(int)RtSettingClGpMstRt[i01][1]] = ""+ClGpMstRt[i][(int)RtSettingClGpMstRt[i01][1]];
						}
						
						MainFmTableModel.addRow(SetOb);
					}
					if(0<ClGpMstRt.length) {
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
					String TgtClGp = "";
					for(int i=0;i<RowCount;i++) {
						if((boolean)MainFmTableModel.getValueAt(i, 0)) {
							TgtClGp = ""+MainFmTableModel.getValueAt(i, 1+M100_ClGpMstRt.ColClGpCD);	if(null==TgtClGp) {TgtClGp="";}
						}
					}
					if(!"".equals(TgtClGp)) {
						SetX=main_fm.getX();
						SetY=main_fm.getY();

						main_fm.setVisible(false);
						main_fm.dispose();
						WM100_ClGpMst_01_RenewAndCrwate.ClGpMstRenewAndCrwate(0,0,TgtClGp);
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
					WM100_ClGpMst_01_RenewAndCrwate.ClGpMstRenewAndCrwate(0,0,"");
					
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
					B100_TableControl.TableOutPutCsv("出力先選択","荷主グループマスタ検索結果",tb01);
					RenewFg = true;
				}
			}
		});
		
		//エクセル出力ボタン押下時の挙動
		ExcelBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					B100_TableControl.TableOutPutExcel("出力先選択","荷主グループマスタ検索結果",tb01);
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