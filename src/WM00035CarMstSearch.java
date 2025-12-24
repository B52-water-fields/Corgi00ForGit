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

public class WM00035CarMstSearch{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	public static void CarMstSearch(int x,int y) {
		A00000Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		
		final JFrame main_fm = B00110FrameParts.FrameCreate(x,y,860,750,"Corgi00車輛検索","");
		JLabel userinfo = B00110FrameParts.UserInfo();
		JButton exit_btn = B00110FrameParts.ExitBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		
		//検索条件パネル
		JPanel PN_Search = B00110FrameParts.JPanelSet(10,40,820,160,"White");
		JLabel PN_SearchLabel = B00110FrameParts.JLabelSet(10,0,150,20,"検索条件",11,0);
		PN_Search.add(PN_SearchLabel);
		main_fm.add(PN_Search);
		
		JLabel LB_SearchWHCD				= B00110FrameParts.JLabelSet(  0, 25,100,20,"所属倉庫:",	11,1);
		JLabel LB_SearchShippingCompanyCd	= B00110FrameParts.JLabelSet(  0, 50,100,20,"所属会社:",	11,1);
		JLabel LB_SearchCarCd				= B00110FrameParts.JLabelSet(  0, 75,100,20,"車輛CD:",		11,1);
		JLabel LB_SearchCarName				= B00110FrameParts.JLabelSet(  0,100,100,20,"車輛名:",		11,1);
		JLabel LB_SearchDelFg				= B00110FrameParts.JLabelSet(  0,125,100,20,"削除区分:",	11,1);
		
		final JComboBox  TB_SearchWHCD				= B00110FrameParts.JComboBoxSet( 100, 25,200,20,B00100DefaultVariable.SearchWhList[0],11);					//所属倉庫
		final JComboBox  TB_SearchShippingCompanyCd	= B00110FrameParts.JComboBoxSet( 100, 50,200,20,B00100DefaultVariable.SearchShippingCompanyList[0],11);	//所属会社
		final JTextField TB_SearchCarCd				= B00110FrameParts.JTextFieldSet(100, 75,100,20,"",11,0);	//車輛CD
		final JTextField TB_SearchCarName			= B00110FrameParts.JTextFieldSet(100,100,200,20,"",11,0);	//車輛名
		final JComboBox  TB_SearchDelFg				= B00110FrameParts.JComboBoxSet( 100,125,100,20,B00100DefaultVariable.SearchDelList[0],11);	//削除区分
		
		JLabel LB2_SearchWHCD				= B00110FrameParts.JLabelSet(  0, 25, 50,20,"",		11,0);
		JLabel LB2_SearchShippingCompanyCd	= B00110FrameParts.JLabelSet(  0, 50, 50,20,"",		11,0);
		JLabel LB2_SearchCarCd				= B00110FrameParts.JLabelSet(200, 75, 50,20,"と一致",	11,0);
		JLabel LB2_SearchCarName			= B00110FrameParts.JLabelSet(300,100, 50,20,"を含む",	11,0);
		JLabel LB2_SearchDelFg				= B00110FrameParts.JLabelSet(200,125, 50,20,"",		11,0);
		
		PN_Search.add(LB_SearchWHCD);
		PN_Search.add(LB_SearchShippingCompanyCd);
		PN_Search.add(LB_SearchCarCd);
		PN_Search.add(LB_SearchCarName);
		PN_Search.add(LB_SearchDelFg);
		
		PN_Search.add(TB_SearchWHCD);
		PN_Search.add(TB_SearchShippingCompanyCd);
		PN_Search.add(TB_SearchCarCd);
		PN_Search.add(TB_SearchCarName);
		PN_Search.add(TB_SearchDelFg);
		
		PN_Search.add(LB2_SearchWHCD);
		PN_Search.add(LB2_SearchShippingCompanyCd);
		PN_Search.add(LB2_SearchCarCd);
		PN_Search.add(LB2_SearchCarName);
		PN_Search.add(LB2_SearchDelFg);
		
		//検索ボタン
		JButton SearchBtn = B00110FrameParts.BtnSet(350,125,100,20,"検索",11);
		PN_Search.add(SearchBtn);
		
		
		Object[][] RtSettingCarMstRt = M00031CarMstRt.RtSettingCarMstRt();
		
		String[] columnNames01 = new String[RtSettingCarMstRt.length+1];
		
		columnNames01[0] = "Fg";
		for(int i=0;i<RtSettingCarMstRt.length;i++) {
			columnNames01[1+i] = ""+RtSettingCarMstRt[i][3];
		}
		
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
		
		for(int i=0;i<RtSettingCarMstRt.length;i++) {
			column = columnModel01.getColumn(1+i);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());
		}
		
		//スクロール用設定
		JScrollPane scpn01 = B00110FrameParts.JScrollPaneSet(10,210,820,395,tb01);
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
					
					String GetSearchWHCD				= ""+B00100DefaultVariable.SearchWhList[1][TB_SearchWHCD.getSelectedIndex()];								//所属倉庫
					String GetSearchShippingCompanyCd	= ""+B00100DefaultVariable.SearchShippingCompanyList[1][TB_SearchShippingCompanyCd.getSelectedIndex()];	//所属会社
					String GetSearchCarCd= TB_SearchCarCd.getText();
					String GetSearchCarName= TB_SearchCarName.getText();
					String GetSearchDelFg				= ""+B00100DefaultVariable.SearchDelList[1][TB_SearchDelFg.getSelectedIndex()];			//削除区分
					
					if(null==GetSearchWHCD				){GetSearchWHCD = "";}
					if(null==GetSearchShippingCompanyCd	){GetSearchShippingCompanyCd = "";}
					if(null==GetSearchCarCd				){GetSearchCarCd = "";}
					if(null==GetSearchCarName			){GetSearchCarName = "";}
					if(null==GetSearchDelFg				){GetSearchDelFg = "";}
					
					GetSearchWHCD	 			= B00020ToolsTextControl.Trim(GetSearchWHCD);
					GetSearchShippingCompanyCd	= B00020ToolsTextControl.Trim(GetSearchShippingCompanyCd);
					GetSearchCarCd	 			= B00020ToolsTextControl.Trim(GetSearchCarCd);
					GetSearchCarName	 		= B00020ToolsTextControl.Trim(GetSearchCarName);
					GetSearchDelFg	 			= B00020ToolsTextControl.Trim(GetSearchDelFg);
					
					TB_SearchCarCd.setText(GetSearchCarCd);
					TB_SearchCarName.setText(GetSearchCarName);
					
					ArrayList<String> SearchWHCD = new ArrayList<String>();
					ArrayList<String> SearchShippingCompanyCd = new ArrayList<String>();
					ArrayList<String> SearchCarCd = new ArrayList<String>();
					ArrayList<String> SearchCarName = new ArrayList<String>();
					ArrayList<String> SearchDelFg = new ArrayList<String>();
					boolean AllSearch = true;
					
					if(!"".equals(GetSearchWHCD)){SearchWHCD.add(GetSearchWHCD);}
					if(!"".equals(GetSearchShippingCompanyCd)){SearchShippingCompanyCd.add(GetSearchShippingCompanyCd);}
					if(!"".equals(GetSearchCarCd)){SearchCarCd.add(GetSearchCarCd);}
					if(!"".equals(GetSearchCarName)){SearchCarName.add(GetSearchCarName);}
					if(!"".equals(GetSearchDelFg)){SearchDelFg.add(GetSearchDelFg);}
					
					Object[][] CarMstRt = M00031CarMstRt.CarMstRt(
									SearchWHCD,
									SearchShippingCompanyCd,
									SearchCarCd,
									SearchCarName,
									SearchDelFg,
									AllSearch);
					
					for(int i=0;i<CarMstRt.length;i++) {
						Object[] SetOb= new Object[CarMstRt[i].length+1];
						SetOb[0] = false;
						for(int i01=0;i01<CarMstRt[i].length;i01++) {
							SetOb[i01+1] = CarMstRt[i][i01];
						}
						tableModel_ms01.addRow(SetOb);;
					}
					if(0<CarMstRt.length) {
						B10010TableControl.AddSortON(tb01,tableModel_ms01);
					}else {
						B10010TableControl.AddSortOFF(tb01,tableModel_ms01);
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
					String TgtCar = "";
					for(int i=0;i<RowCount;i++) {
						if((boolean)tableModel_ms01.getValueAt(i, 0)) {
							TgtWhCd 				= ""+tableModel_ms01.getValueAt(i, 1);	if(null==TgtWhCd				) {TgtWhCd="";}
							TgtShippingCompanyCd 	= ""+tableModel_ms01.getValueAt(i, 2);	if(null==TgtShippingCompanyCd	) {TgtShippingCompanyCd="";}
							TgtCar 					= ""+tableModel_ms01.getValueAt(i, 6);	if(null==TgtCar					) {TgtCar="";}
						}
					}
					if(!"".equals(TgtCar)) {
						SetX=main_fm.getX();
						SetY=main_fm.getY();

						main_fm.setVisible(false);
						main_fm.dispose();
						WM00036CarMstRenewAndCreate.CarMstRenewAndCreate(0,0,TgtWhCd,TgtShippingCompanyCd,TgtCar);
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
					WM00036CarMstRenewAndCreate.CarMstRenewAndCreate(0,0,"","","");
					
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
					B10010TableControl.TableOutPutCsv("出力先選択","車輛マスタ検索結果",tb01);
					RenewFg = true;
				}
			}
		});
		
		//エクセル出力ボタン押下時の挙動
		ExcelBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					B10010TableControl.TableOutPutExcel("出力先選択","車輛マスタ検索結果",tb01);
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