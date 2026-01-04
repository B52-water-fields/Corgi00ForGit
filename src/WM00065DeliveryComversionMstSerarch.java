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

public class WM00065DeliveryComversionMstSerarch{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	
	static String[][] PrefecturesCdList;
	static String[][] MunicipalityCd;
	public static void DeliveryComversionMstSerarch(int x,int y) {
		A00000Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		
		final JFrame main_fm = B00110FrameParts.FrameCreate(x,y,900,750,"Corgi00届先変換マスタ検索","");
		JLabel userinfo = B00110FrameParts.UserInfo();
		JButton exit_btn = B00110FrameParts.ExitBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		
		//検索条件パネル
		JPanel PN_Search = B00110FrameParts.JPanelSet(10,40,870,180,"White");
		JLabel PN_SearchLabel = B00110FrameParts.JLabelSet(10,0,150,20,"検索条件",11,0);
		PN_Search.add(PN_SearchLabel);
		main_fm.add(PN_Search);
		
		JLabel LB_SearchClGpCD				= B00110FrameParts.JLabelSet(  0, 25,100,20,"荷主グループCD:"	,11,1);
		JLabel LB_SearchCLGpName			= B00110FrameParts.JLabelSet(  0, 50,100,20,"荷主グループ名:"	,11,1);
		JLabel LB_SearchCL_DECD				= B00110FrameParts.JLabelSet(  0, 75,100,20,"荷主届先CD:"		,11,1);
		JLabel LB_SearchDECD				= B00110FrameParts.JLabelSet(  0,100,100,20,"届先CD:"			,11,1);
		JLabel LB_SearchDepartmentCd		= B00110FrameParts.JLabelSet(  0,125,100,20,"届先部署CD:"		,11,1);
		
		JLabel LB_SearchDEName				= B00110FrameParts.JLabelSet(300, 25, 50,20,"届先名:"			,11,1);
		JLabel LB_SearchPost				= B00110FrameParts.JLabelSet(300, 50, 50,20,"郵便:"			,11,1);
		JLabel LB_SearchAdd					= B00110FrameParts.JLabelSet(300, 75, 50,20,"住所:"			,11,1);
		JLabel LB_SearchTel					= B00110FrameParts.JLabelSet(300,100, 50,20,"電話:"			,11,1);
		JLabel LB_SearchFax					= B00110FrameParts.JLabelSet(300,125, 50,20,"FAX:"				,11,1);
		JLabel LB_SearchMail				= B00110FrameParts.JLabelSet(300,150, 50,20,"MAIL:"			,11,1);
		
		JLabel LB_SearchSetName				= B00110FrameParts.JLabelSet(500, 25,100,20,"送り状登録名:"	,11,1);
		JLabel LB_SearchCom					= B00110FrameParts.JLabelSet(500, 50,100,20,"コメント:"		,11,1);
		JLabel LB_SearchDelFg				= B00110FrameParts.JLabelSet(500, 75,100,20,"削除区分:"		,11,1);
		JLabel LB_SearchMstPriorityFirstFg	= B00110FrameParts.JLabelSet(500,100,100,20,"届先マスタ優先:"	,11,1);
		
		String[] PriorityFirstFg = {"未指定","データ優先","マスタ優先"};
		final JTextField  TB_SearchClGpCD			= B00110FrameParts.JTextFieldSet(	100, 25,100,20,"",11,0);			//荷主グループCD
		final JTextField  TB_SearchCLGpName			= B00110FrameParts.JTextFieldSet(	100, 50,200,20,"",10,0);			//荷主グループ名
		final JTextField  TB_SearchCL_DECD			= B00110FrameParts.JTextFieldSet(	100, 75,100,20,"",11,0);			//荷主届先CD
		final JTextField  TB_SearchDECD				= B00110FrameParts.JTextFieldSet(	100,100,100,20,"",11,0);			//届先CD
		final JTextField  TB_SearchDepartmentCd		= B00110FrameParts.JTextFieldSet(	100,125,100,20,"",11,0);			//届先部署CD
		
		final JTextField  TB_SearchDEName			= B00110FrameParts.JTextFieldSet(	350, 25,100,20,"",11,0);			//届先名
		final JTextField  TB_SearchPost				= B00110FrameParts.JTextFieldSet(	350, 50,100,20,"",11,0);			//郵便
		final JTextField  TB_SearchAdd				= B00110FrameParts.JTextFieldSet(	350, 75,100,20,"",11,0);			//住所
		final JTextField  TB_SearchTel				= B00110FrameParts.JTextFieldSet(	350,100,100,20,"",11,0);			//電話
		final JTextField  TB_SearchFax				= B00110FrameParts.JTextFieldSet(	350,125,100,20,"",11,0);			//FAX
		final JTextField  TB_SearchMail				= B00110FrameParts.JTextFieldSet(	350,150,100,20,"",11,0);			//MAIL
		
		final JTextField  TB_SearchSetName			= B00110FrameParts.JTextFieldSet(	600, 25,100,20,"",11,0);			//送り状登録名
		final JTextField  TB_SearchCom				= B00110FrameParts.JTextFieldSet(	600, 50,100,20,"",11,0);			//コメント
		final JComboBox   TB_SearchDelFg= B00110FrameParts.JComboBoxSet( 				600, 75,100,20,B00100DefaultVariable.SearchDelList[0],11);	//削除区分
		final JComboBox   TB_SearchMstPriorityFirstFg= B00110FrameParts.JComboBoxSet( 600,100,100,20,PriorityFirstFg,11);			//届先マスタ優先
		
		JLabel LB2_SearchClGpCD				= B00110FrameParts.JLabelSet(200, 25, 50,20,"と一致"	,11,0);
		//JLabel LB2_SearchCLGpName			= B00110FrameParts.JLabelSet(200, 50, 50,20,""			,11,0);
		JLabel LB2_SearchCL_DECD			= B00110FrameParts.JLabelSet(200, 75, 50,20,"と一致"	,11,0);
		JLabel LB2_SearchDECD				= B00110FrameParts.JLabelSet(200,100, 50,20,"と一致"	,11,0);
		JLabel LB2_SearchDepartmentCd		= B00110FrameParts.JLabelSet(200,125, 50,20,"と一致"	,11,0);
		
		JLabel LB2_SearchDEName				= B00110FrameParts.JLabelSet(450, 25, 50,20,"を含む"	,11,0);
		JLabel LB2_SearchPost				= B00110FrameParts.JLabelSet(450, 50, 50,20,"で始まる"	,11,0);
		JLabel LB2_SearchAdd				= B00110FrameParts.JLabelSet(450, 75, 50,20,"を含む"	,11,0);
		JLabel LB2_SearchTel				= B00110FrameParts.JLabelSet(450,100, 50,20,"を含む"	,11,0);
		JLabel LB2_SearchFax				= B00110FrameParts.JLabelSet(450,125, 50,20,"を含む"	,11,0);
		JLabel LB2_SearchMail				= B00110FrameParts.JLabelSet(450,150, 50,20,"を含む"	,11,0);
		
		JLabel LB2_SearchSetName			= B00110FrameParts.JLabelSet(700, 25, 50,20,"を含む"	,11,0);
		JLabel LB2_SearchCom				= B00110FrameParts.JLabelSet(700, 50, 50,20,"を含む"	,11,0);
		JLabel LB2_SearchDelFg				= B00110FrameParts.JLabelSet(700, 75, 50,20,""			,11,0);
		JLabel LB2_SearchMstPriorityFirstFg	= B00110FrameParts.JLabelSet(700,100, 50,20,""			,11,0);
		
		TB_SearchClGpCD.setText(A00000Main.ClGp);
		if(!"".equals(A00000Main.ClGp)) {
			ArrayList<String> SearchClGpCD 		= new ArrayList<String>();
			ArrayList<String> SearchCLGpName 	= new ArrayList<String>();
			ArrayList<String> SearchPost 		= new ArrayList<String>();
			ArrayList<String> SearchAdd 		= new ArrayList<String>();
			ArrayList<String> SearchTel 		= new ArrayList<String>();
			ArrayList<String> SearchFax 		= new ArrayList<String>();
			ArrayList<String> SearchMail 		= new ArrayList<String>();
			ArrayList<String> SearchCom 		= new ArrayList<String>();
			boolean AllSearch = false;
			
			SearchClGpCD.add(A00000Main.ClGp);
			
			Object[][] ClGpMstRt = M00010ClGpMstRt.ClGpMstRt(
					SearchClGpCD,
					SearchCLGpName,
					SearchPost,
					SearchAdd,
					SearchTel,
					SearchFax,
					SearchMail,
					SearchCom,
					AllSearch);
			if(0<ClGpMstRt.length) {
				TB_SearchCLGpName.setText(""+ClGpMstRt[0][1]);
			}
		}
		TB_SearchClGpCD.setEditable(false);
		TB_SearchCLGpName.setEditable(false);
		
		PN_Search.add(LB_SearchClGpCD);
		PN_Search.add(LB_SearchCLGpName);
		PN_Search.add(LB_SearchCL_DECD);
		PN_Search.add(LB_SearchDECD);
		PN_Search.add(LB_SearchDepartmentCd);
		PN_Search.add(LB_SearchDEName);
		PN_Search.add(LB_SearchPost);
		PN_Search.add(LB_SearchAdd);
		PN_Search.add(LB_SearchTel);
		PN_Search.add(LB_SearchFax);
		PN_Search.add(LB_SearchMail);
		PN_Search.add(LB_SearchSetName);
		PN_Search.add(LB_SearchCom);
		PN_Search.add(LB_SearchDelFg);
		PN_Search.add(LB_SearchMstPriorityFirstFg);
		
		PN_Search.add(TB_SearchClGpCD);
		PN_Search.add(TB_SearchCLGpName);
		PN_Search.add(TB_SearchCL_DECD);
		PN_Search.add(TB_SearchDECD);
		PN_Search.add(TB_SearchDepartmentCd);
		PN_Search.add(TB_SearchDEName);
		PN_Search.add(TB_SearchPost);
		PN_Search.add(TB_SearchAdd);
		PN_Search.add(TB_SearchTel);
		PN_Search.add(TB_SearchFax);
		PN_Search.add(TB_SearchMail);
		PN_Search.add(TB_SearchSetName);
		PN_Search.add(TB_SearchCom);
		PN_Search.add(TB_SearchDelFg);
		PN_Search.add(TB_SearchMstPriorityFirstFg);
		
		PN_Search.add(LB2_SearchClGpCD);
		//PN_Search.add(LB2_SearchCLGpName);
		PN_Search.add(LB2_SearchCL_DECD);
		PN_Search.add(LB2_SearchDECD);
		PN_Search.add(LB2_SearchDepartmentCd);
		PN_Search.add(LB2_SearchDEName);
		PN_Search.add(LB2_SearchPost);
		PN_Search.add(LB2_SearchAdd);
		PN_Search.add(LB2_SearchTel);
		PN_Search.add(LB2_SearchFax);
		PN_Search.add(LB2_SearchMail);
		PN_Search.add(LB2_SearchSetName);
		PN_Search.add(LB2_SearchCom);
		PN_Search.add(LB2_SearchDelFg);
		PN_Search.add(LB2_SearchMstPriorityFirstFg);

		//検索ボタン
		JButton SearchBtn = B00110FrameParts.BtnSet(600,150,100,20,"検索",11);
		PN_Search.add(SearchBtn);
		
		Object[][] RtDeliveryComversionMstRt = M00060DeliveryComversionMstRt.RtDeliveryComversionMstRt();
		
		String[] columnNames01 = new String[RtDeliveryComversionMstRt.length+1];
		
		columnNames01[0] = "Fg";
		for(int i=0;i<RtDeliveryComversionMstRt.length;i++) {
			columnNames01[1+i] = ""+RtDeliveryComversionMstRt[i][3];
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
		
		for(int i=0;i<RtDeliveryComversionMstRt.length;i++) {
			column = columnModel01.getColumn(1+i);	column.setPreferredWidth( 90*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());
		}
		
		//スクロール用設定
		JScrollPane scpn01 = B00110FrameParts.JScrollPaneSet(10,230,870,375,tb01);
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
		
		//Excel出力ボタン
		JButton ExcelBtn = B00110FrameParts.BtnSet(		370,660,100,20,"Excel出力",11);
		main_fm.add(ExcelBtn);
		
		//Excel取込ボタン
		JButton ExcelEntryBtn = B00110FrameParts.BtnSet(	490,660,100,20,"Excel取込",11);
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
					
					String GetSearchClGpCD 				= TB_SearchClGpCD.getText();
					String GetSearchCL_DECD 			= TB_SearchCL_DECD.getText();
					String GetSearchDECD 				= TB_SearchDECD.getText();
					String GetSearchDepartmentCd 		= TB_SearchDepartmentCd.getText();
					String GetSearchDEName 				= TB_SearchDEName.getText();
					String GetSearchPost 				= TB_SearchPost.getText();
					String GetSearchAdd 				= TB_SearchAdd.getText();
					String GetSearchTel 				= TB_SearchTel.getText();
					String GetSearchFax 				= TB_SearchFax.getText();
					String GetSearchMail 				= TB_SearchMail.getText();
					String GetSearchSetName 			= TB_SearchSetName.getText();
					String GetSearchCom 				= TB_SearchCom.getText();
					String GetSearchDelFg 				= B00100DefaultVariable.SearchDelList[1][TB_SearchDelFg.getSelectedIndex()];
					String GetSearchMstPriorityFirstFg	= "";
					
					switch(TB_SearchMstPriorityFirstFg.getSelectedIndex()) {
						case 0:
							GetSearchMstPriorityFirstFg	= "";
							break;
						case 1:
							GetSearchMstPriorityFirstFg	= "0";
							break;
						case 2:
							GetSearchMstPriorityFirstFg	= "1";
							break;
						default:
							GetSearchMstPriorityFirstFg	= "";
							break;
					}
					
					if(null==GetSearchClGpCD				){GetSearchClGpCD				= "";}
					if(null==GetSearchCL_DECD				){GetSearchCL_DECD				= "";}
					if(null==GetSearchDECD					){GetSearchDECD					= "";}
					if(null==GetSearchDepartmentCd			){GetSearchDepartmentCd			= "";}
					if(null==GetSearchDEName				){GetSearchDEName				= "";}
					if(null==GetSearchPost					){GetSearchPost					= "";}
					if(null==GetSearchAdd					){GetSearchAdd					= "";}
					if(null==GetSearchTel					){GetSearchTel					= "";}
					if(null==GetSearchFax					){GetSearchFax					= "";}
					if(null==GetSearchMail					){GetSearchMail					= "";}
					if(null==GetSearchSetName				){GetSearchSetName				= "";}
					if(null==GetSearchCom					){GetSearchCom					= "";}
					if(null==GetSearchDelFg					){GetSearchDelFg				= "";}
					if(null==GetSearchMstPriorityFirstFg	){GetSearchMstPriorityFirstFg	= "";}
					
					GetSearchClGpCD				= B00020ToolsTextControl.Trim(GetSearchClGpCD);
					GetSearchCL_DECD			= B00020ToolsTextControl.Trim(GetSearchCL_DECD);
					GetSearchDECD				= B00020ToolsTextControl.Trim(GetSearchDECD);
					GetSearchDepartmentCd		= B00020ToolsTextControl.Trim(GetSearchDepartmentCd);
					GetSearchDEName				= B00020ToolsTextControl.Trim(GetSearchDEName);
					GetSearchPost				= B00020ToolsTextControl.Trim(GetSearchPost);
					GetSearchAdd				= B00020ToolsTextControl.Trim(GetSearchAdd);
					GetSearchTel				= B00020ToolsTextControl.Trim(GetSearchTel);
					GetSearchFax				= B00020ToolsTextControl.Trim(GetSearchFax);
					GetSearchMail				= B00020ToolsTextControl.Trim(GetSearchMail);
					GetSearchSetName			= B00020ToolsTextControl.Trim(GetSearchSetName);
					GetSearchCom				= B00020ToolsTextControl.Trim(GetSearchCom);
					GetSearchDelFg				= B00020ToolsTextControl.Trim(GetSearchDelFg);
					GetSearchMstPriorityFirstFg	= B00020ToolsTextControl.Trim(GetSearchMstPriorityFirstFg);
					
					GetSearchPost				= B00020ToolsTextControl.num_only_String(GetSearchPost);
					GetSearchTel				= B00020ToolsTextControl.num_only_String(GetSearchTel);
					GetSearchFax				= B00020ToolsTextControl.num_only_String(GetSearchFax);
					GetSearchDelFg				= B00020ToolsTextControl.num_only_String(GetSearchDelFg);
					GetSearchMstPriorityFirstFg	= B00020ToolsTextControl.num_only_String(GetSearchMstPriorityFirstFg);
					
					ArrayList<String> SearchClGpCD 				= new ArrayList<String>();
					ArrayList<String> SearchCLGpName 			= new ArrayList<String>();
					ArrayList<String> SearchCL_DECD 			= new ArrayList<String>();
					ArrayList<String> SearchDECD 				= new ArrayList<String>();
					ArrayList<String> SearchDepartmentCd 		= new ArrayList<String>();
					ArrayList<String> SearchDEName 				= new ArrayList<String>();
					ArrayList<String> SearchPost 				= new ArrayList<String>();
					ArrayList<String> SearchAdd 				= new ArrayList<String>();
					ArrayList<String> SearchTel 				= new ArrayList<String>();
					ArrayList<String> SearchFax 				= new ArrayList<String>();
					ArrayList<String> SearchMail 				= new ArrayList<String>();
					ArrayList<String> SearchSetName 			= new ArrayList<String>();
					ArrayList<String> SearchCom 				= new ArrayList<String>();
					ArrayList<String> SearchDelFg 				= new ArrayList<String>();
					ArrayList<String> SearchMstPriorityFirstFg	= new ArrayList<String>();
					boolean AllSearch = true;
					
					if(!"".equals(GetSearchClGpCD				)){SearchClGpCD.add(				GetSearchClGpCD);}
					if(!"".equals(GetSearchCL_DECD				)){SearchCL_DECD.add(				GetSearchCL_DECD);}
					if(!"".equals(GetSearchDECD					)){SearchDECD.add(					GetSearchDECD);}
					if(!"".equals(GetSearchDepartmentCd			)){SearchDepartmentCd.add(			GetSearchDepartmentCd);}
					if(!"".equals(GetSearchDEName				)){SearchDEName.add(				GetSearchDEName);}
					if(!"".equals(GetSearchPost					)){SearchPost.add(					GetSearchPost);}
					if(!"".equals(GetSearchAdd					)){SearchAdd.add(					GetSearchAdd);}
					if(!"".equals(GetSearchTel					)){SearchTel.add(					GetSearchTel);}
					if(!"".equals(GetSearchFax					)){SearchFax.add(					GetSearchFax);}
					if(!"".equals(GetSearchMail					)){SearchMail.add(					GetSearchMail);}
					if(!"".equals(GetSearchSetName				)){SearchSetName.add(				GetSearchSetName);}
					if(!"".equals(GetSearchCom					)){SearchCom.add(					GetSearchCom);}
					if(!"".equals(GetSearchDelFg				)){SearchDelFg.add(					GetSearchDelFg);}
					if(!"".equals(GetSearchMstPriorityFirstFg	)){SearchMstPriorityFirstFg.add(	GetSearchMstPriorityFirstFg);}
					
					Object[][] DeliveryComversionMstRt = M00060DeliveryComversionMstRt.DeliveryComversionMstRt(
										SearchClGpCD,
										SearchCLGpName,
										SearchCL_DECD,
										SearchDECD,
										SearchDepartmentCd,
										SearchDEName,
										SearchPost,
										SearchAdd,
										SearchTel,
										SearchFax,
										SearchMail,
										SearchSetName,
										SearchCom,
										SearchDelFg,
										SearchMstPriorityFirstFg,
										AllSearch);
					
					for(int i=0;i<DeliveryComversionMstRt.length;i++) {
						Object[] SetOb= new Object[DeliveryComversionMstRt[i].length+1];
						SetOb[0] = false;
						for(int i01=0;i01<DeliveryComversionMstRt[i].length;i01++) {
							SetOb[i01+1] = DeliveryComversionMstRt[i][i01];
						}
						tableModel_ms01.addRow(SetOb);
					}
					if(0<DeliveryComversionMstRt.length) {
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
					for(int i=0;i<RowCount;i++) {
						if((boolean)tableModel_ms01.getValueAt(i, 0)) {
							SetX=main_fm.getX();
							SetY=main_fm.getY();

							main_fm.setVisible(false);
							main_fm.dispose();
							
							WM00066DeliveryComversionMstRenewAndCreate.DeliveryComversionMstRenewAndCreate(0,0,""+tableModel_ms01.getValueAt(i, 1),""+tableModel_ms01.getValueAt(i, 3));
						}
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
					
					WM00066DeliveryComversionMstRenewAndCreate.DeliveryComversionMstRenewAndCreate(0,0,"","");
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
					B10010TableControl.TableOutPutCsv("出力先選択","届先変換マスタ検索結果",tb01);
					RenewFg = true;
				}
			}
		});
		
		//エクセル出力ボタン押下時の挙動
		ExcelBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					B10010TableControl.TableOutPutExcel("出力先選択","届先変換マスタ検索結果",tb01);
					RenewFg = true;
				}
			}
		});
		
		//Excel取込ボタン
		ExcelEntryBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					String MSG = "エクセルファイル選択";
					String[] file_type = {".xlsx"};
					String file_type_name = "エクセルファイル";
					String Selected = B00090FileSelect.FileSelect(MSG,file_type,file_type_name);
					
					if(null!=Selected && !Selected.equals(Selected.replace(".xlsx", ""))) {
						SetX=main_fm.getX();
						SetY=main_fm.getY();

						main_fm.setVisible(false);
						main_fm.dispose();
						WM00067DeliveryComversionMstExcelEntry.DeliveryComversionMstExcelEntry(0,0,Selected);
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