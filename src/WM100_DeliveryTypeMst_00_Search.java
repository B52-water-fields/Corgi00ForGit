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

public class WM100_DeliveryTypeMst_00_Search{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	
	public static void DeliveryTypeMstSearch(int x,int y) {
		A00000_Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		final JFrame main_fm = B100_FrameParts.FrameCreate(x,y,650,750,"Corgi00運送タイプマスタ検索","");
		JLabel userinfo = B100_FrameParts.UserInfo();
		JButton exit_btn = B100_FrameParts.ExitBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		
		//検索条件パネル
		JPanel PN_Search = B100_FrameParts.JPanelSet(10,40,620,160,"White");
		JLabel PN_SearchLabel = B100_FrameParts.JLabelSet(10,0,150,20,"検索条件",11,0);
		PN_Search.add(PN_SearchLabel);
		main_fm.add(PN_Search);

		JLabel LB_SearchDeliveryTypeNo		= B100_FrameParts.JLabelSet(  0, 25,100,20,"タイプNo:"	,11,1);
		JLabel LB_SearchDeliveryTypeCd		= B100_FrameParts.JLabelSet(  0, 50,100,20,"運送タイプCD:"	,11,1);
		JLabel LB_SearchDeliveryTypeName	= B100_FrameParts.JLabelSet(  0, 75,100,20,"運送タイプ名:"	,11,1);
		
		String[] TypeNoList = {"未指定","01","02","03","04","05"};
		
		final JComboBox  TB_SearchDeliveryTypeNo	= B100_FrameParts.JComboBoxSet( 100, 25,100,20,TypeNoList,11);	//タイプNo
		final JTextField TB_SearchDeliveryTypeCd	= B100_FrameParts.JTextFieldSet(100, 50,100,20,"",11,0);			//運送タイプCD
		final JTextField TB_SearchDeliveryTypeName	= B100_FrameParts.JTextFieldSet(100, 75,100,20,"",11,0);			//運送タイプ名
		
		JLabel LB2_SearchDeliveryTypeNo		= B100_FrameParts.JLabelSet(200, 25, 50,20,""			,11,0);
		JLabel LB2_SearchDeliveryTypeCd		= B100_FrameParts.JLabelSet(200, 50, 50,20,"と一致"	,11,0);
		JLabel LB2_SearchDeliveryTypeName	= B100_FrameParts.JLabelSet(200, 75, 50,20,"を含む"	,11,0);

		PN_Search.add(LB_SearchDeliveryTypeNo);
		PN_Search.add(LB_SearchDeliveryTypeCd);
		PN_Search.add(LB_SearchDeliveryTypeName);
		
		PN_Search.add(TB_SearchDeliveryTypeNo);		//タイプNo
		PN_Search.add(TB_SearchDeliveryTypeCd);		//運送タイプCD
		PN_Search.add(TB_SearchDeliveryTypeName);	//運送タイプ名
		
		PN_Search.add(LB2_SearchDeliveryTypeNo);
		PN_Search.add(LB2_SearchDeliveryTypeCd);
		PN_Search.add(LB2_SearchDeliveryTypeName);
		
		//検索ボタン
		JButton SearchBtn = B100_FrameParts.BtnSet(100,125,100,20,"検索",11);
		PN_Search.add(SearchBtn);
		
		Object[][] RtSettingDeliveryTypeMstRt = M100_DeliveryTypeMstRt.RtSettingDeliveryTypeMstRt();
		
		String[] columnNames01 = new String[RtSettingDeliveryTypeMstRt.length+1];
		
		columnNames01[0] = "Fg";
		for(int i=0;i<RtSettingDeliveryTypeMstRt.length;i++) {
			columnNames01[1+i] = ""+RtSettingDeliveryTypeMstRt[i][3];
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
		
		for(int i=0;i<RtSettingDeliveryTypeMstRt.length;i++) {
			if("int".equals((String)RtSettingDeliveryTypeMstRt[i][2])||"float".equals((String)RtSettingDeliveryTypeMstRt[i][2])) {
				column = columnModel01.getColumn(1+i);	column.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.rightCellRenderer());
			}else {
				column = columnModel01.getColumn(1+i);	column.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.leftCellRenderer());
			}
		}
		
		//スクロール用設定
		JScrollPane scpn01 = B100_FrameParts.JScrollPaneSet(10,210,620,395,tb01);
		main_fm.add(scpn01);
		
		
		main_fm.setVisible(true);
		RenewFg = true;
		
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
		
		//CSVボタン
		JButton ExcelBtn = B100_FrameParts.BtnSet(		370,660,100,20,"Excel出力",11);
		main_fm.add(ExcelBtn);
		
		//検索ボタン押下時の挙動
		SearchBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					
					int RowCount = tableModel_ms01.getRowCount();
					for(int i=0;i<RowCount;i++) {
						tableModel_ms01.removeRow(0);
					}
					String GetSearchDeliveryTypeNo = "";	//タイプNo
					if(0<TB_SearchDeliveryTypeNo.getSelectedIndex()) {
						GetSearchDeliveryTypeNo = "" + TB_SearchDeliveryTypeNo.getSelectedIndex();
					}
					String GetSearchDeliveryTypeCd		= TB_SearchDeliveryTypeCd.getText();	//運送タイプCD
					String GetSearchDeliveryTypeName	= TB_SearchDeliveryTypeName.getText();	//運送タイプ名
					
					if(null==GetSearchDeliveryTypeNo	){GetSearchDeliveryTypeNo = "";}
					if(null==GetSearchDeliveryTypeCd	){GetSearchDeliveryTypeCd = "";}
					if(null==GetSearchDeliveryTypeName	){GetSearchDeliveryTypeName = "";}
					
					GetSearchDeliveryTypeNo		= B100_TextControl.Trim(GetSearchDeliveryTypeNo);
					GetSearchDeliveryTypeCd		= B100_TextControl.Trim(GetSearchDeliveryTypeCd);
					GetSearchDeliveryTypeName	= B100_TextControl.Trim(GetSearchDeliveryTypeName);
					
					GetSearchDeliveryTypeNo		= B100_TextControl.num_only_String(GetSearchDeliveryTypeNo);
					
					TB_SearchDeliveryTypeCd.setText(GetSearchDeliveryTypeCd);
					TB_SearchDeliveryTypeName.setText(GetSearchDeliveryTypeName);

					ArrayList<String> SearchDeliveryTypeNo = new ArrayList<String>();
					ArrayList<String> SearchDeliveryTypeCd = new ArrayList<String>();
					ArrayList<String> SearchDeliveryTypeName = new ArrayList<String>();
					boolean AllSearch = true;
					
					if(!"".equals(GetSearchDeliveryTypeNo	)){SearchDeliveryTypeNo.add(GetSearchDeliveryTypeNo);}
					if(!"".equals(GetSearchDeliveryTypeCd	)){SearchDeliveryTypeCd.add(GetSearchDeliveryTypeCd);}
					if(!"".equals(GetSearchDeliveryTypeName	)){SearchDeliveryTypeName.add(GetSearchDeliveryTypeName);}
					
					Object[][] DeliveryTypeMstRt = M100_DeliveryTypeMstRt.DeliveryTypeMstRt(
							SearchDeliveryTypeNo,
							SearchDeliveryTypeCd,
							SearchDeliveryTypeName,
							AllSearch);
					
					
					for(int i=0;i<DeliveryTypeMstRt.length;i++) {
						Object[] SetOb= new Object[DeliveryTypeMstRt[i].length+1];
						SetOb[0] = false;
						for(int i01=0;i01<DeliveryTypeMstRt[i].length;i01++) {
							SetOb[i01+1] = DeliveryTypeMstRt[i][i01];
						}
						tableModel_ms01.addRow(SetOb);;
					}
					if(0<DeliveryTypeMstRt.length) {
						B100_TableControl.AddSortON(tb01,tableModel_ms01);
					}else {
						B100_TableControl.AddSortOFF(tb01,tableModel_ms01);
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
					String TgtDeliveryTypeNo = "";
					String TgtDeliveryTypeCd = "";
					
					for(int i=0;i<RowCount;i++) {
						if((boolean)tableModel_ms01.getValueAt(i, 0)) {
							TgtDeliveryTypeNo 	= ""+tableModel_ms01.getValueAt(i, 1);	if(null==TgtDeliveryTypeNo	) {TgtDeliveryTypeNo="";}
							TgtDeliveryTypeCd 	= ""+tableModel_ms01.getValueAt(i, 2);	if(null==TgtDeliveryTypeCd	) {TgtDeliveryTypeCd="";}
						}
					}
					if(!"".equals(TgtDeliveryTypeNo) && !"".equals(TgtDeliveryTypeCd)) {
						SetX=main_fm.getX();
						SetY=main_fm.getY();

						main_fm.setVisible(false);
						main_fm.dispose();
						WM100_DeliveryTypeMst_01_RenewAndCreate.DeliveryTypeMstRenewAndCreate(0,0,TgtDeliveryTypeNo,TgtDeliveryTypeCd);
					}
					RenewFg = true;
				}
			}
		});
		
		//新規登録ボタン
		CreateBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				WM100_DeliveryTypeMst_01_RenewAndCreate.DeliveryTypeMstRenewAndCreate(0,0,"","");
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
					B100_TableControl.TableOutPutCsv("出力先選択","運送タイプマスタ検索結果",tb01);
					RenewFg = true;
				}
			}
		});
		
		//エクセル出力ボタン押下時の挙動
		ExcelBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					B100_TableControl.TableOutPutExcel("出力先選択","運送タイプマスタ検索結果",tb01);
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