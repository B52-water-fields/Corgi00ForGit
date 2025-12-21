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

public class WM00040ShippingCompanyMstSearch{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	public static void ShippingCompanyMstSearch(int x,int y) {
		A00000Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		final JFrame main_fm = B00110FrameParts.FrameCreate(x,y,850,750,"Corgi00運送会社検索","");
		JLabel userinfo = B00110FrameParts.UserInfo();
		JButton exit_btn = B00110FrameParts.ExitBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		
		//検索条件パネル
		JPanel PN_Search = B00110FrameParts.JPanelSet(10,40,820,160,"White");
		JLabel PN_SearchLabel = B00110FrameParts.JLabelSet(10,0,150,20,"検索条件",11,0);
		PN_Search.add(PN_SearchLabel);
		main_fm.add(PN_Search);

		JLabel LB_SearchShippingCompanyCd	= B00110FrameParts.JLabelSet(  0, 25,100,20,"運送会社CD:"	,11,1);
		JLabel LB_SearchCompanyName			= B00110FrameParts.JLabelSet(  0, 50,100,20,"運送会社名:"	,11,1);
		JLabel LB_SearchPost				= B00110FrameParts.JLabelSet(  0, 75,100,20,"郵便番号:"	,11,1);
		JLabel LB_SearchAdd					= B00110FrameParts.JLabelSet(  0,100,100,20,"住所:"		,11,1);
		JLabel LB_SearchTel					= B00110FrameParts.JLabelSet(250, 25,100,20,"Tel:"			,11,1);
		JLabel LB_SearchFax					= B00110FrameParts.JLabelSet(250, 50,100,20,"Fax:"			,11,1);
		JLabel LB_SearchMail				= B00110FrameParts.JLabelSet(250, 75,100,20,"Mail:"		,11,1);
		JLabel LB_SearchCom					= B00110FrameParts.JLabelSet(250,100,100,20,"コメント:"	,11,1);
		
		final JTextField TB_SearchShippingCompanyCd	= B00110FrameParts.JTextFieldSet(	100, 25,100,20,"",11,0);	//運送会社CD
		final JTextField TB_SearchCompanyName		= B00110FrameParts.JTextFieldSet(	100, 50,100,20,"",11,0);	//運送会社名
		final JTextField TB_SearchPost				= B00110FrameParts.JTextFieldSet(	100, 75,100,20,"",11,0);	//郵便番号
		final JTextField TB_SearchAdd				= B00110FrameParts.JTextFieldSet(	100,100,100,20,"",11,0);	//住所
		final JTextField TB_SearchTel				= B00110FrameParts.JTextFieldSet(	350, 25,100,20,"",11,0);	//Tel
		final JTextField TB_SearchFax				= B00110FrameParts.JTextFieldSet(	350, 50,100,20,"",11,0);	//Fax
		final JTextField TB_SearchMail				= B00110FrameParts.JTextFieldSet(	350, 75,100,20,"",11,0);	//Mail
		final JTextField TB_SearchCom				= B00110FrameParts.JTextFieldSet(	350,100,100,20,"",11,0);	//コメント
		
		JLabel LB2_SearchShippingCompanyCd	= B00110FrameParts.JLabelSet(200, 25, 50,20,"と一致"	,11,0);
		JLabel LB2_SearchCompanyName		= B00110FrameParts.JLabelSet(200, 50, 50,20,"を含む"	,11,0);
		JLabel LB2_SearchPost				= B00110FrameParts.JLabelSet(200, 75, 50,20,"で始まる"	,11,0);
		JLabel LB2_SearchAdd				= B00110FrameParts.JLabelSet(200,100, 50,20,"を含む"	,11,0);
		JLabel LB2_SearchTel				= B00110FrameParts.JLabelSet(450, 25, 50,20,"を含む"	,11,0);
		JLabel LB2_SearchFax				= B00110FrameParts.JLabelSet(450, 50, 50,20,"を含む"	,11,0);
		JLabel LB2_SearchMail				= B00110FrameParts.JLabelSet(450, 75, 50,20,"を含む"	,11,0);
		JLabel LB2_SearchCom				= B00110FrameParts.JLabelSet(450,100, 50,20,"を含む"	,11,0);
		
		PN_Search.add(LB_SearchShippingCompanyCd);
		PN_Search.add(LB_SearchCompanyName);
		PN_Search.add(LB_SearchPost);
		PN_Search.add(LB_SearchAdd);
		PN_Search.add(LB_SearchTel);
		PN_Search.add(LB_SearchFax);
		PN_Search.add(LB_SearchMail);
		PN_Search.add(LB_SearchCom);
		
		PN_Search.add(TB_SearchShippingCompanyCd);
		PN_Search.add(TB_SearchCompanyName);
		PN_Search.add(TB_SearchPost);
		PN_Search.add(TB_SearchAdd);
		PN_Search.add(TB_SearchTel);
		PN_Search.add(TB_SearchFax);
		PN_Search.add(TB_SearchMail);
		PN_Search.add(TB_SearchCom);
		
		PN_Search.add(LB2_SearchShippingCompanyCd);
		PN_Search.add(LB2_SearchCompanyName);
		PN_Search.add(LB2_SearchPost);
		PN_Search.add(LB2_SearchAdd);
		PN_Search.add(LB2_SearchTel);
		PN_Search.add(LB2_SearchFax);
		PN_Search.add(LB2_SearchMail);
		PN_Search.add(LB2_SearchCom);
		
		//検索ボタン
		JButton SearchBtn = B00110FrameParts.BtnSet(350,125,100,20,"検索",11);
		PN_Search.add(SearchBtn);
		
		
		Object[][] RtSettingShippingCompanyMstRt = M00030ShippingCompanyMstRt.RtSettingShippingCompanyMstRt();
		
		String[] columnNames01 = new String[RtSettingShippingCompanyMstRt.length+1];
		
		columnNames01[0] = "Fg";
		for(int i=0;i<RtSettingShippingCompanyMstRt.length;i++) {
			columnNames01[1+i] = ""+RtSettingShippingCompanyMstRt[i][3];
		}
		
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
		
		for(int i=0;i<RtSettingShippingCompanyMstRt.length;i++) {
			column = columnModel01.getColumn(1+i);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());
		}
		
		//スクロール用設定
		JScrollPane scpn01 = B00110FrameParts.JScrollPaneSet(10,210,820,425,tb01);
		main_fm.add(scpn01);
		
		main_fm.setVisible(true);
		RenewFg = true;
		
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
		
		//検索ボタン押下時の挙動
		SearchBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					
					int RowCount = tableModel_ms01.getRowCount();
					for(int i=0;i<RowCount;i++) {
						tableModel_ms01.removeRow(0);
					}
					
					String GetSearchShippingCompanyCd	= TB_SearchShippingCompanyCd.getText();
					String GetSearchCompanyName			= TB_SearchCompanyName.getText();
					String GetSearchPost				= TB_SearchPost.getText();
					String GetSearchAdd					= TB_SearchAdd.getText();
					String GetSearchTel					= TB_SearchTel.getText();
					String GetSearchFax					= TB_SearchFax.getText();
					String GetSearchMail				= TB_SearchMail.getText();
					String GetSearchCom					= TB_SearchCom.getText();
					
					if(null==GetSearchShippingCompanyCd	){GetSearchShippingCompanyCd = "";}
					if(null==GetSearchCompanyName		){GetSearchCompanyName = "";}
					if(null==GetSearchPost				){GetSearchPost = "";}
					if(null==GetSearchAdd				){GetSearchAdd = "";}
					if(null==GetSearchTel				){GetSearchTel = "";}
					if(null==GetSearchFax				){GetSearchFax = "";}
					if(null==GetSearchMail				){GetSearchMail = "";}
					if(null==GetSearchCom				){GetSearchCom = "";}
					
					GetSearchShippingCompanyCd	= B00020ToolsTextControl.Trim(GetSearchShippingCompanyCd);
					GetSearchCompanyName		= B00020ToolsTextControl.Trim(GetSearchCompanyName);
					GetSearchPost				= B00020ToolsTextControl.Trim(GetSearchPost);
					GetSearchAdd				= B00020ToolsTextControl.Trim(GetSearchAdd);
					GetSearchTel				= B00020ToolsTextControl.Trim(GetSearchTel);
					GetSearchFax				= B00020ToolsTextControl.Trim(GetSearchFax);
					GetSearchMail				= B00020ToolsTextControl.Trim(GetSearchMail);
					GetSearchCom				= B00020ToolsTextControl.Trim(GetSearchCom);
					
					ArrayList<String> SearchShippingCompanyCd = new ArrayList<String>();
					ArrayList<String> SearchCompanyName = new ArrayList<String>();
					ArrayList<String> SearchPost = new ArrayList<String>();
					ArrayList<String> SearchAdd = new ArrayList<String>();
					ArrayList<String> SearchTel = new ArrayList<String>();
					ArrayList<String> SearchFax = new ArrayList<String>();
					ArrayList<String> SearchMail = new ArrayList<String>();
					ArrayList<String> SearchCom = new ArrayList<String>();
					boolean AllSearch = true;
					
					if(!"".equals(GetSearchShippingCompanyCd	)){SearchShippingCompanyCd.add(GetSearchShippingCompanyCd);}
					if(!"".equals(GetSearchCompanyName			)){SearchCompanyName.add(GetSearchCompanyName);}
					if(!"".equals(GetSearchPost					)){SearchPost.add(GetSearchPost);}
					if(!"".equals(GetSearchAdd					)){SearchAdd.add(GetSearchAdd);}
					if(!"".equals(GetSearchTel					)){SearchTel.add(GetSearchTel);}
					if(!"".equals(GetSearchFax					)){SearchFax.add(GetSearchFax);}
					if(!"".equals(GetSearchMail					)){SearchMail.add(GetSearchMail);}
					if(!"".equals(GetSearchCom					)){SearchCom.add(GetSearchCom);}
					
					Object[][] ShippingCompanyMstRt = M00030ShippingCompanyMstRt.ShippingCompanyMstRt(
							SearchShippingCompanyCd,
							SearchCompanyName,
							SearchPost,
							SearchAdd,
							SearchTel,
							SearchFax,
							SearchMail,
							SearchCom,
							AllSearch);
					
					for(int i=0;i<ShippingCompanyMstRt.length;i++) {
						Object[] SetOb= new Object[ShippingCompanyMstRt[i].length+1];
						SetOb[0] = false;
						for(int i01=0;i01<ShippingCompanyMstRt[i].length;i01++) {
							SetOb[i01+1] = ShippingCompanyMstRt[i][i01];
						}
						tableModel_ms01.addRow(SetOb);;
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
					String TgtShippingCompanyCd = "";
					for(int i=0;i<RowCount;i++) {
						if((boolean)tableModel_ms01.getValueAt(i, 0)) {
							TgtShippingCompanyCd 	= ""+tableModel_ms01.getValueAt(i, 1);	if(null==TgtShippingCompanyCd	) {TgtShippingCompanyCd="";}
						}
					}
					if(!"".equals(TgtShippingCompanyCd)) {
						SetX=main_fm.getX();
						SetY=main_fm.getY();

						main_fm.setVisible(false);
						main_fm.dispose();
						WM00041ShippingCompanyMstRenewAndCreate.ShippingCompanyMstRenewAndCreate(0,0,TgtShippingCompanyCd);
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
				WM00041ShippingCompanyMstRenewAndCreate.ShippingCompanyMstRenewAndCreate(0,0,"");
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
					B10010TableControl.TableOutPutCsv("出力先選択","運送会社マスタ検索結果",tb01);
					RenewFg = true;
				}
			}
		});
		
		//エクセル出力ボタン押下時の挙動
		ExcelBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					B10010TableControl.TableOutPutExcel("出力先選択","運送会社マスタ検索結果",tb01);
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