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

public class WM100_AdjustReasonMst_00_Search{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	public static void AdjustReasonMstSearch(int x,int y) {
		A00000_Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		
		final JFrame main_fm = B100_FrameParts.FrameCreate(x,y,900,750,"Corgi00在庫調整理由マスタ検索","");
		JLabel userinfo = B100_FrameParts.UserInfo();
		JButton exit_btn = B100_FrameParts.ExitBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		
		//検索条件パネル
		JPanel PN_Search 		= B100_FrameParts.JPanelSet(10,40,880,150,"White");
		JLabel PN_SearchLabel 	= B100_FrameParts.JLabelSet(10,0,150,20,"検索条件",11,0);
		
		PN_Search.add(PN_SearchLabel);
		
		//検索条件
		JLabel LB_SearchClCd				= B100_FrameParts.JLabelSet(	  0, 25,130,20,"荷主コード:"		,11,1);
		JLabel LB_SearchWhCd				= B100_FrameParts.JLabelSet(	  0, 50,130,20,"倉庫コード:"		,11,1);
		JLabel LB_SearchAdjustReasonCd		= B100_FrameParts.JLabelSet(	  0, 75,130,20,"調整理由コード:"	,11,1);
		JLabel LB_SearchAdjustReasonName	= B100_FrameParts.JLabelSet(	  0,100,130,20,"調整理由名:"		,11,1);
		
		final JComboBox   TB_SearchClWh				= B100_FrameParts.JComboBoxSet(	130, 25,250,20,B100_DefaultVariable.SearchWhList[0],11);		//倉庫コード
		final JComboBox   TB_SearchClCd				= B100_FrameParts.JComboBoxSet(	130, 50,250,20,B100_DefaultVariable.SearchClList[0],11);		//荷主コード
		final JTextField  TB_SearchAdjustReasonCd	= B100_FrameParts.JTextFieldSet( 130, 75,100,20,"",11,0);	//調整理由コード:"	,11,1);
		final JTextField  TB_SearchAdjustReasonName	= B100_FrameParts.JTextFieldSet( 130,100,100,20,"",11,0);	//調整理由名:"		,11,1);
		
		JLabel LB2_SearchAdjustReasonCd		= B100_FrameParts.JLabelSet(	230, 75,130,20,"と一致"	,11,0);
		JLabel LB2_SearchAdjustReasonName	= B100_FrameParts.JLabelSet(	230,100,130,20,"を含む"	,11,0);
		
		//検索ボタン
		JButton SearchBtn = B100_FrameParts.BtnSet(130,125,100,20,"検索",11);
		PN_Search.add(SearchBtn);
		
		for(int i=0;i<B100_DefaultVariable.SearchWhList[1].length;i++) {
			if(B100_DefaultVariable.SearchWhList[1][i].equals(A00000_Main.ClWh)) {
				TB_SearchClWh.setSelectedIndex(i);
			}
		}
		
		for(int i=0;i<B100_DefaultVariable.SearchClList[1].length;i++) {
			if(B100_DefaultVariable.SearchClList[1][i].equals(A00000_Main.ClCd)) {
				TB_SearchClCd.setSelectedIndex(i);
			}
		}
		TB_SearchClWh.setEnabled(false);		//担当倉庫
		TB_SearchClCd.setEnabled(false);		//荷主CD
		
		PN_Search.add(LB_SearchClCd);
		PN_Search.add(LB_SearchWhCd);
		PN_Search.add(LB_SearchAdjustReasonCd);
		PN_Search.add(LB_SearchAdjustReasonName);
		
		PN_Search.add(TB_SearchClWh);
		PN_Search.add(TB_SearchClCd);
		PN_Search.add(TB_SearchAdjustReasonCd);
		PN_Search.add(TB_SearchAdjustReasonName);
		
		PN_Search.add(LB2_SearchAdjustReasonCd);
		PN_Search.add(LB2_SearchAdjustReasonName);
		
		main_fm.add(PN_Search);
		
		Object[][] RtAdjustReasonRt = M100_AdjustReasonRt.RtAdjustReasonRt();
		
		String[] columnNames01 = new String[RtAdjustReasonRt.length+1];
		
		columnNames01[0] = "Fg";
		for(int i=0;i<RtAdjustReasonRt.length;i++) {
			columnNames01[1+(int)RtAdjustReasonRt[i][1]] = ""+RtAdjustReasonRt[i][3];
		}
		
		//編集可能カラムの指定
		B100_TableControl.RenewTgt = new int[1];
		B100_TableControl.RenewTgt[0] = 0;

		final DefaultTableModel tableModel_ms01 = new B100_TableControl.MyTableModel01(columnNames01,0);
		
		final JTable tb01 = new JTable(tableModel_ms01);
		tb01.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tb01.setRowHeight(20*A00000_Main.Mul/A00000_Main.Div);
		tb01.setFont(new Font(A00000_Main.DefaultFont, Font.PLAIN, 12*A00000_Main.Mul/A00000_Main.Div));
		
		DefaultTableColumnModel columnModel01
		= (DefaultTableColumnModel)tb01.getColumnModel();
		
		//列幅初期設定 表示位置設定
		TableColumn column = null;
		
		column = columnModel01.getColumn( 0);	column.setPreferredWidth( 30*A00000_Main.Mul/A00000_Main.Div);	//FG
		
		for(int i=0;i<RtAdjustReasonRt.length;i++) {
			if("int".equals((String)RtAdjustReasonRt[i][2])||"float".equals((String)RtAdjustReasonRt[i][2])) {
				column = columnModel01.getColumn(1+(int)RtAdjustReasonRt[i][1]);	column.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.rightCellRenderer());
			}else {
				column = columnModel01.getColumn(1+(int)RtAdjustReasonRt[i][1]);	column.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.leftCellRenderer());
			}
		}
		//スクロール用設定
		JScrollPane scpn01 = B100_FrameParts.JScrollPaneSet(10,200,870,425,tb01);
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
		
		//Excel出力ボタン
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
					int RowCount = tableModel_ms01.getRowCount();
					for(int i=0;i<RowCount;i++) {
						tableModel_ms01.removeRow(0);
					}
					
					String GetSearchClCd				= B100_DefaultVariable.SearchClList[1][TB_SearchClCd.getSelectedIndex()];	//荷主CD
					String GetSearchWhCd				= B100_DefaultVariable.SearchWhList[1][TB_SearchClWh.getSelectedIndex()];	//倉庫CD
					String GetSearchAdjustReasonCd		= TB_SearchAdjustReasonCd.getText();	//調整理由コード
					String GetSearchAdjustReasonName	= TB_SearchAdjustReasonName.getText();	//調整理由名
					
					if(null==GetSearchClCd				){GetSearchClCd				= "";}	//荷主CD
					if(null==GetSearchWhCd				){GetSearchWhCd				= "";}	//倉庫CD
					if(null==GetSearchAdjustReasonCd	){GetSearchAdjustReasonCd	= "";}	//調整理由コード
					if(null==GetSearchAdjustReasonName	){GetSearchAdjustReasonName	= "";}	//調整理由名
					
					GetSearchClCd				= B100_TextControl.Trim(GetSearchClCd);				//荷主CD
					GetSearchWhCd				= B100_TextControl.Trim(GetSearchWhCd);				//倉庫CD
					GetSearchAdjustReasonCd		= B100_TextControl.Trim(GetSearchAdjustReasonCd);		//調整理由コード
					GetSearchAdjustReasonName	= B100_TextControl.Trim(GetSearchAdjustReasonName);	//調整理由名
					
					ArrayList<String> SearchClCd				= new ArrayList<String>();	//荷主CD
					ArrayList<String> SearchWhCd				= new ArrayList<String>();	//倉庫CD
					ArrayList<String> SearchAdjustReasonCd		= new ArrayList<String>();	//調整理由コード
					ArrayList<String> SearchAdjustReasonName	= new ArrayList<String>();	//調整理由名
					boolean AllSearch = false;
					
					if(!"".equals(GetSearchClCd)) {
						SearchClCd.add(GetSearchClCd);
					}
					if(!"".equals(GetSearchWhCd)) {
						SearchWhCd.add(GetSearchWhCd);
					}
					if(!"".equals(GetSearchAdjustReasonCd)) {
						SearchAdjustReasonCd.add(GetSearchAdjustReasonCd);
					}
					if(!"".equals(GetSearchAdjustReasonName)) {
						SearchAdjustReasonName.add(GetSearchAdjustReasonName);
					}
					
					Object[][] AdjustReasonRt = M100_AdjustReasonRt.AdjustReasonRt(
							SearchClCd,					//荷主CD
							SearchWhCd,					//倉庫CD
							SearchAdjustReasonCd,		//調整理由コード
							SearchAdjustReasonName,		//調整理由名
							AllSearch);
					
					for(int i=0;i<AdjustReasonRt.length;i++) {
						Object[] SetOb = new Object[AdjustReasonRt[i].length+1];
						SetOb[0] = false;
						for(int i01=0;i01<AdjustReasonRt[i].length;i01++) {
							SetOb[i01+1] = AdjustReasonRt[i][i01];
						}
						tableModel_ms01.addRow(SetOb);
					}
					
					if(0<AdjustReasonRt.length) {
						B100_TableControl.AddSortON(tb01,tableModel_ms01);
					}else {
						B100_TableControl.AddSortOFF(tb01,tableModel_ms01);
					}
					
					RenewFg = true;
				}
			}
		});
		//修正ボタン
		RenewBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					int RowCount = tableModel_ms01.getRowCount();
					String TgtClCd 				= "";
					String TgtWhCd 				= "";
					String TgtAdjustReasonCd 	= "";
					boolean KickFg = false;
					for(int i=0;i<RowCount;i++) {
						if((boolean)tableModel_ms01.getValueAt(i, 0)) {
							TgtClCd 			= ""+tableModel_ms01.getValueAt(i, M100_AdjustReasonRt.ColClCd+1);
							TgtWhCd 			= ""+tableModel_ms01.getValueAt(i, M100_AdjustReasonRt.ColWhCd+1);
							TgtAdjustReasonCd 	= ""+tableModel_ms01.getValueAt(i, M100_AdjustReasonRt.ColAdjustReasonCd+1);
							KickFg = true;
						}
					}
					if(KickFg) {
						SetX=main_fm.getX();
						SetY=main_fm.getY();

						main_fm.setVisible(false);
						main_fm.dispose();
						
						//仮置き：実際のメソッド名に合わせて調整
						WM100_AdjustReasonMst_01_RenewAndCreate.AdjustReasonMstRenewAndCreate(0,0,TgtClCd,TgtWhCd,TgtAdjustReasonCd);
					}
					RenewFg = true;
				}
			}
		});
		
		//新規登録ボタン
		CreateBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					SetX=main_fm.getX();
					SetY=main_fm.getY();

					main_fm.setVisible(false);
					main_fm.dispose();
					
					String TgtClCd 				= A00000_Main.ClCd;
					String TgtWhCd 				= A00000_Main.ClWh;
					String TgtAdjustReasonCd 	= "";
					
					//仮置き：実際のメソッド名に合わせて調整
					WM100_AdjustReasonMst_01_RenewAndCreate.AdjustReasonMstRenewAndCreate(0,0,TgtClCd,TgtWhCd,TgtAdjustReasonCd);
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
					B100_TableControl.TableOutPutCsv("出力先選択","在庫調整理由マスタ検索結果",tb01);
					RenewFg = true;
				}
			}
		});
		
		//エクセル出力ボタン押下時の挙動
		ExcelBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					B100_TableControl.TableOutPutExcel("出力先選択","在庫調整理由マスタ検索結果",tb01);
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