import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
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

public class WM00100SupplierMstSearch{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	public static void SupplierMstSearch(int x,int y) {
		A00000Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		
		final JFrame main_fm = B00110FrameParts.FrameCreate(x,y,900,750,"Corgi00商品変換マスタ検索","");
		JLabel userinfo = B00110FrameParts.UserInfo();
		JButton exit_btn = B00110FrameParts.ExitBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		
		//検索条件パネル
		JPanel PN_Search = B00110FrameParts.JPanelSet(10,40,880,300,"White");
		JLabel PN_SearchLabel = B00110FrameParts.JLabelSet(10,0,150,20,"検索条件",11,0);
		
		PN_Search.add(PN_SearchLabel);
		
		//検索条件
		JLabel LB_SearchClWh			= B00110FrameParts.JLabelSet(	  0, 25,130,20,"担当倉庫:"				,11,1);
		JLabel LB_SearchClCd			= B00110FrameParts.JLabelSet(	  0, 50,130,20,"荷主CD:"				,11,1);
		JLabel LB_SearchSPCd			= B00110FrameParts.JLabelSet(	  0, 75,130,20,"仕入先コード:"			,11,1);
		JLabel LB_SearchSPName			= B00110FrameParts.JLabelSet(	  0,100,130,20,"仕入先名:"				,11,1);
		JLabel LB_SearchSPPost			= B00110FrameParts.JLabelSet(	  0,125,130,20,"仕入先郵便:"			,11,1);
		JLabel LB_SearchSPAdd			= B00110FrameParts.JLabelSet(	  0,150,130,20,"仕入先住所:"			,11,1);
		JLabel LB_SearchSPTel			= B00110FrameParts.JLabelSet(	  0,175,130,20,"仕入先電話:"			,11,1);
		JLabel LB_SearchSPFax			= B00110FrameParts.JLabelSet(	  0,200,130,20,"仕入先FAX:"				,11,1);
		JLabel LB_SearchSPMail			= B00110FrameParts.JLabelSet(	  0,225,130,20,"仕入先MAIL:"			,11,1);
		JLabel LB_SearchCom				= B00110FrameParts.JLabelSet(	  0,250,130,20,"コメント:"				,11,1);
		
		JLabel LB_SearchPTMSCDBMN		= B00110FrameParts.JLabelSet(	330, 75,130,20,"基幹SysCd（部門）:"		,10,1);
		JLabel LB_SearchPTMSCDNINUSHI	= B00110FrameParts.JLabelSet(	330,100,130,20,"基幹SysCd（荷主）:"		,10,1);
		JLabel LB_SearchPaySite		= B00110FrameParts.JLabelSet(		330,125,130,20,"支払サイト開始:"		,11,1);
		JLabel LB_SearchPayDate		= B00110FrameParts.JLabelSet(		330,150,130,20,"支払日開始:"			,11,1);
		JLabel LB_SearchShimeDate	= B00110FrameParts.JLabelSet(		330,175,130,20,"締め日開始:"			,11,1);
		JLabel LB_SearchDECD			= B00110FrameParts.JLabelSet(	330,200,130,20,"納品先コード:"			,11,1);
		JLabel LB_SearchDepartmentCd	= B00110FrameParts.JLabelSet(	330,225,130,20,"部署CD:"				,11,1);
		
		final JComboBox   TB_SearchClWh						= B00110FrameParts.JComboBoxSet(				130, 25,250,20,B00100DefaultVariable.SearchWhList[0],11);		//担当倉庫
		final JComboBox   TB_SearchClCd						= B00110FrameParts.JComboBoxSet(				130, 50,250,20,B00100DefaultVariable.SearchClList[0],11);		//荷主CD
		final JTextField  TB_SearchSPCd						= B00110FrameParts.JTextFieldSet( 			130, 75,100,20,"",11,0);	//仕入先コード
		final JTextField  TB_SearchSPName					= B00110FrameParts.JTextFieldSet( 			130,100,100,20,"",11,0);	//仕入先名
		final JTextField  TB_SearchSPPost					= B00110FrameParts.JTextFieldSet( 			130,125,100,20,"",11,0);	//仕入先郵便
		final JTextField  TB_SearchSPAdd					= B00110FrameParts.JTextFieldSet( 			130,150,100,20,"",11,0);	//仕入先住所
		final JTextField  TB_SearchSPTel					= B00110FrameParts.JTextFieldSet( 			130,175,100,20,"",11,0);	//仕入先電話
		final JTextField  TB_SearchSPFax					= B00110FrameParts.JTextFieldSet( 			130,200,100,20,"",11,0);	//仕入先FAX
		final JTextField  TB_SearchSPMail					= B00110FrameParts.JTextFieldSet( 			130,225,100,20,"",11,0);	//仕入先MAIL
		final JTextField  TB_SearchCom						= B00110FrameParts.JTextFieldSet( 			130,250,100,20,"",11,0);	//コメント
		
		final JTextField  TB_SearchPTMSCDBMN				= B00110FrameParts.JTextFieldSet( 			460, 75,100,20,"",11,0);	//基幹Sysコード（部門）
		final JTextField  TB_SearchPTMSCDNINUSHI			= B00110FrameParts.JTextFieldSet( 			460,100,100,20,"",11,0);	//基幹Sysコード（荷主）
		final JFormattedTextField TB_SearchPaySiteStr		= B00110FrameParts.JFormattedTextFieldSet(	460,125, 60,20,"0",11,1,"#,###");	//支払いサイト（月数）開始
		final JFormattedTextField TB_SearchPaySiteEnd		= B00110FrameParts.JFormattedTextFieldSet(	560,125, 60,20,"99",11,1,"#,###");	//支払いサイト（月数）終了
		final JComboBox   TB_SearchPayDateStr				= B00110FrameParts.JComboBoxSet(				460,150, 60,20,B00100DefaultVariable.ShimeDateList[0],11);		//支払日（日＝99）開始
		final JComboBox   TB_SearchPayDateEnd				= B00110FrameParts.JComboBoxSet(				560,150, 60,20,B00100DefaultVariable.ShimeDateList[0],11);		//支払日（日＝99）終了
		final JComboBox   TB_SearchShimeDateStr				= B00110FrameParts.JComboBoxSet(				460,175, 60,20,B00100DefaultVariable.ShimeDateList[0],11);		//締め日（末日＝99）開始
		final JComboBox   TB_SearchShimeDateEnd				= B00110FrameParts.JComboBoxSet(				560,175, 60,20,B00100DefaultVariable.ShimeDateList[0],11);		//締め日（末日＝99）終了
		final JTextField  TB_SearchDECD						= B00110FrameParts.JTextFieldSet(				460,200,100,20,"",11,0);	//納品先コード
		final JTextField  TB_SearchDepartmentCd				= B00110FrameParts.JTextFieldSet( 			460,225,100,20,"",11,0);	//部署CD
		
		JLabel LB2_SearchSPCd			= B00110FrameParts.JLabelSet(	230, 75,100,20,"と一致"		,11,0);
		JLabel LB2_SearchSPName			= B00110FrameParts.JLabelSet(	230,100,100,20,"を含む"		,11,0);
		JLabel LB2_SearchSPPost			= B00110FrameParts.JLabelSet(	230,125,100,20,"で始まる"	,11,0);
		JLabel LB2_SearchSPAdd			= B00110FrameParts.JLabelSet(	230,150,100,20,"を含む"		,11,0);
		JLabel LB2_SearchSPTel			= B00110FrameParts.JLabelSet(	230,175,100,20,"を含む"		,11,0);
		JLabel LB2_SearchSPFax			= B00110FrameParts.JLabelSet(	230,200,100,20,"を含む"		,11,0);
		JLabel LB2_SearchSPMail			= B00110FrameParts.JLabelSet(	230,225,100,20,"を含む"		,11,0);
		JLabel LB2_SearchCom			= B00110FrameParts.JLabelSet(	230,250,100,20,"を含む"		,11,0);
		
		JLabel LB2_SearchPTMSCDBMN		= B00110FrameParts.JLabelSet(	560, 75,100,20,"と一致"		,11,0);
		JLabel LB2_SearchPTMSCDNINUSHI	= B00110FrameParts.JLabelSet(	560,100,100,20,"と一致"		,11,0);
		JLabel LB2_SearchPaySite		= B00110FrameParts.JLabelSet(	520,125,40,20,"～"			,11,2);
		JLabel LB2_SearchPayDate		= B00110FrameParts.JLabelSet(	520,150,40,20,"～"			,11,2);
		JLabel LB2_SearchShimeDate		= B00110FrameParts.JLabelSet(	520,175,40,20,"～"			,11,2);
		JLabel LB2_SearchDECD			= B00110FrameParts.JLabelSet(	560,200,100,20,"と一致"		,11,0);
		JLabel LB2_SearchDepartmentCd	= B00110FrameParts.JLabelSet(	560,225,100,20,"と一致"		,11,0);
		
		//検索ボタン
		JButton SearchBtn = B00110FrameParts.BtnSet(460,250,100,20,"検索",11);
		PN_Search.add(SearchBtn);
		
		for(int i=0;i<B00100DefaultVariable.SearchWhList[1].length;i++) {
			if(B00100DefaultVariable.SearchWhList[1][i].equals(A00000Main.ClWh)) {
				TB_SearchClWh.setSelectedIndex(i);
			}
		}
		
		for(int i=0;i<B00100DefaultVariable.SearchClList[1].length;i++) {
			if(B00100DefaultVariable.SearchClList[1][i].equals(A00000Main.ClCd)) {
				TB_SearchClCd.setSelectedIndex(i);
			}
		}
		TB_SearchClWh.setEnabled(false);		//担当倉庫
		TB_SearchClCd.setEnabled(false);		//荷主CD
		
		TB_SearchPaySiteStr.setText("0");
		TB_SearchPaySiteEnd.setText("99");
		TB_SearchPayDateStr.setSelectedIndex(0);
		TB_SearchPayDateEnd.setSelectedIndex(B00100DefaultVariable.ShimeDateList[0].length-1);
		TB_SearchShimeDateStr.setSelectedIndex(0);
		TB_SearchShimeDateEnd.setSelectedIndex(B00100DefaultVariable.ShimeDateList[0].length-1);
		
		PN_Search.add(LB_SearchClWh);
		PN_Search.add(LB_SearchClCd);
		PN_Search.add(LB_SearchSPCd);
		PN_Search.add(LB_SearchSPName);
		PN_Search.add(LB_SearchSPPost);
		PN_Search.add(LB_SearchSPAdd);
		PN_Search.add(LB_SearchSPTel);
		PN_Search.add(LB_SearchSPFax);
		PN_Search.add(LB_SearchSPMail);
		PN_Search.add(LB_SearchCom);
		PN_Search.add(LB_SearchPTMSCDBMN);
		PN_Search.add(LB_SearchPTMSCDNINUSHI);
		PN_Search.add(LB_SearchPaySite);
		PN_Search.add(LB_SearchPayDate);
		PN_Search.add(LB_SearchShimeDate);
		PN_Search.add(LB_SearchDECD);
		PN_Search.add(LB_SearchDepartmentCd);
		
		PN_Search.add(TB_SearchClWh);
		PN_Search.add(TB_SearchClCd);
		PN_Search.add(TB_SearchSPCd);
		PN_Search.add(TB_SearchSPName);
		PN_Search.add(TB_SearchSPPost);
		PN_Search.add(TB_SearchSPAdd);
		PN_Search.add(TB_SearchSPTel);
		PN_Search.add(TB_SearchSPFax);
		PN_Search.add(TB_SearchSPMail);
		PN_Search.add(TB_SearchCom);
		PN_Search.add(TB_SearchPTMSCDBMN);
		PN_Search.add(TB_SearchPTMSCDNINUSHI);
		PN_Search.add(TB_SearchPaySiteStr);
		PN_Search.add(TB_SearchPaySiteEnd);
		PN_Search.add(TB_SearchPayDateStr);
		PN_Search.add(TB_SearchPayDateEnd);
		PN_Search.add(TB_SearchShimeDateStr);
		PN_Search.add(TB_SearchShimeDateEnd);
		PN_Search.add(TB_SearchDECD);
		PN_Search.add(TB_SearchDepartmentCd);
		
		PN_Search.add(LB2_SearchSPCd);
		PN_Search.add(LB2_SearchSPName);
		PN_Search.add(LB2_SearchSPPost);
		PN_Search.add(LB2_SearchSPAdd);
		PN_Search.add(LB2_SearchSPTel);
		PN_Search.add(LB2_SearchSPFax);
		PN_Search.add(LB2_SearchSPMail);
		PN_Search.add(LB2_SearchCom);
		PN_Search.add(LB2_SearchPTMSCDBMN);
		PN_Search.add(LB2_SearchPTMSCDNINUSHI);
		PN_Search.add(LB2_SearchPaySite);
		PN_Search.add(LB2_SearchPayDate);
		PN_Search.add(LB2_SearchShimeDate);
		PN_Search.add(LB2_SearchDECD);
		PN_Search.add(LB2_SearchDepartmentCd);
		
		main_fm.add(PN_Search);
		
		Object[][] RtSupplierRt = M00100SupplierRt.RtSupplierRt();
		
		String[] columnNames01 = new String[RtSupplierRt.length+1];
		
		columnNames01[0] = "Fg";
		for(int i=0;i<RtSupplierRt.length;i++) {
			columnNames01[1+i] = ""+RtSupplierRt[i][3];
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
		
		for(int i=0;i<RtSupplierRt.length;i++) {
			if("int".equals((String)RtSupplierRt[i][2])||"float".equals((String)RtSupplierRt[i][2])) {
				column = columnModel01.getColumn(1+i);	column.setPreferredWidth( 90*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.rightCellRenderer());
			}else {
				column = columnModel01.getColumn(1+i);	column.setPreferredWidth( 90*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());
			}
		}
		//スクロール用設定
		JScrollPane scpn01 = B00110FrameParts.JScrollPaneSet(10,350,870,275,tb01);
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
					
					String GetSearchClWh			= B00100DefaultVariable.SearchWhList[1][TB_SearchClWh.getSelectedIndex()];		//担当倉庫
					String GetSearchClCd			= B00100DefaultVariable.SearchClList[1][TB_SearchClCd.getSelectedIndex()];		//荷主CD
					String GetSearchSPCd 			= TB_SearchSPCd.getText();			//仕入先コード
					String GetSearchSPName 			= TB_SearchSPName.getText();		//仕入先名
					String GetSearchSPPost 			= TB_SearchSPPost.getText();		//仕入先郵便
					String GetSearchSPAdd 			= TB_SearchSPAdd.getText();			//仕入先住所
					String GetSearchSPTel 			= TB_SearchSPTel.getText();			//仕入先電話
					String GetSearchSPFax 			= TB_SearchSPFax.getText();			//仕入先FAX
					String GetSearchSPMail 			= TB_SearchSPMail.getText();		//仕入先MAIL
					String GetSearchCom 			= TB_SearchCom.getText();			//コメント
					String GetSearchPTMSCDBMN 		= TB_SearchPTMSCDBMN.getText();		//基幹Sysコード（部門）
					String GetSearchPTMSCDNINUSHI 	= TB_SearchPTMSCDNINUSHI.getText();	//基幹Sysコード（荷主）
					String GetSearchPaySiteStr 		= TB_SearchPaySiteStr.getText();	//支払いサイト（月数）開始
					String GetSearchPaySiteEnd		= TB_SearchPaySiteEnd.getText();	//支払いサイト（月数）終了
					String GetSearchPayDateStr 		= B00100DefaultVariable.ShimeDateList[1][TB_SearchPayDateStr.getSelectedIndex()];		//支払日（日＝99）開始
					String GetSearchPayDateEnd 		= B00100DefaultVariable.ShimeDateList[1][TB_SearchPayDateEnd.getSelectedIndex()];		//支払日（日＝99）終了
					String GetSearchShimeDateStr 	= B00100DefaultVariable.ShimeDateList[1][TB_SearchShimeDateStr.getSelectedIndex()];	//締め日（末日＝99）開始
					String GetSearchShimeDateEnd 	= B00100DefaultVariable.ShimeDateList[1][TB_SearchShimeDateEnd.getSelectedIndex()];	//締め日（末日＝99）終了
					String GetSearchDECD 			= TB_SearchDECD.getText();			//納品先コード
					String GetSearchDepartmentCd 	= TB_SearchDepartmentCd.getText();	//部署CD
					
					if(null==GetSearchClWh			){GetSearchClWh				= "";}	//担当倉庫
					if(null==GetSearchClCd			){GetSearchClCd 			= "";}	//荷主CD
					if(null==GetSearchSPCd			){GetSearchSPCd 			= "";}	//仕入先コード
					if(null==GetSearchSPName		){GetSearchSPName 			= "";}	//仕入先名
					if(null==GetSearchSPPost		){GetSearchSPPost 			= "";}	//仕入先郵便
					if(null==GetSearchSPAdd			){GetSearchSPAdd 			= "";}	//仕入先住所
					if(null==GetSearchSPTel			){GetSearchSPTel 			= "";}	//仕入先電話
					if(null==GetSearchSPFax			){GetSearchSPFax 			= "";}	//仕入先FAX
					if(null==GetSearchSPMail		){GetSearchSPMail 			= "";}	//仕入先MAIL
					if(null==GetSearchCom			){GetSearchCom 				= "";}	//コメント
					if(null==GetSearchPTMSCDBMN		){GetSearchPTMSCDBMN 		= "";}	//基幹Sysコード（部門）
					if(null==GetSearchPTMSCDNINUSHI	){GetSearchPTMSCDNINUSHI 	= "";}	//基幹Sysコード（荷主）
					if(null==GetSearchPaySiteStr	){GetSearchPaySiteStr 		= "";}	//支払いサイト（月数）開始
					if(null==GetSearchPaySiteEnd	){GetSearchPaySiteEnd 		= "";}	//支払いサイト（月数）終了
					if(null==GetSearchPayDateStr	){GetSearchPayDateStr 		= "";}	//支払日（日＝99）開始
					if(null==GetSearchPayDateEnd	){GetSearchPayDateEnd 		= "";}	//支払日（日＝99）終了
					if(null==GetSearchShimeDateStr	){GetSearchShimeDateStr 	= "";}	//締め日（末日＝99）開始
					if(null==GetSearchShimeDateEnd	){GetSearchShimeDateEnd 	= "";}	//締め日（末日＝99）終了
					if(null==GetSearchDECD			){GetSearchDECD 			= "";}	//納品先コード
					if(null==GetSearchDepartmentCd	){GetSearchDepartmentCd 	= "";}	//部署CD
					
					GetSearchClWh			= B00020ToolsTextControl.Trim(GetSearchClWh);			//担当倉庫
					GetSearchClCd			= B00020ToolsTextControl.Trim(GetSearchClCd);			//荷主CD
					GetSearchSPCd 			= B00020ToolsTextControl.Trim(GetSearchSPCd);			//仕入先コード
					GetSearchSPName 		= B00020ToolsTextControl.Trim(GetSearchSPName);			//仕入先名
					GetSearchSPPost 		= B00020ToolsTextControl.Trim(GetSearchSPPost);			//仕入先郵便
					GetSearchSPAdd 			= B00020ToolsTextControl.Trim(GetSearchSPAdd);			//仕入先住所
					GetSearchSPTel 			= B00020ToolsTextControl.Trim(GetSearchSPTel);			//仕入先電話
					GetSearchSPFax 			= B00020ToolsTextControl.Trim(GetSearchSPFax);			//仕入先FAX
					GetSearchSPMail 		= B00020ToolsTextControl.Trim(GetSearchSPMail);			//仕入先MAIL
					GetSearchCom 			= B00020ToolsTextControl.Trim(GetSearchCom);			//コメント
					GetSearchPTMSCDBMN 		= B00020ToolsTextControl.Trim(GetSearchPTMSCDBMN);		//基幹Sysコード（部門）
					GetSearchPTMSCDNINUSHI 	= B00020ToolsTextControl.Trim(GetSearchPTMSCDNINUSHI);	//基幹Sysコード（荷主）
					GetSearchPaySiteStr 	= B00020ToolsTextControl.Trim(GetSearchPaySiteStr);		//支払いサイト（月数）開始
					GetSearchPaySiteEnd		= B00020ToolsTextControl.Trim(GetSearchPaySiteEnd);		//支払いサイト（月数）終了
					GetSearchPayDateStr 	= B00020ToolsTextControl.Trim(GetSearchPayDateStr);		//支払日（日＝99）開始
					GetSearchPayDateEnd 	= B00020ToolsTextControl.Trim(GetSearchPayDateEnd);		//支払日（日＝99）終了
					GetSearchShimeDateStr 	= B00020ToolsTextControl.Trim(GetSearchShimeDateStr);	//締め日（末日＝99）開始
					GetSearchShimeDateEnd 	= B00020ToolsTextControl.Trim(GetSearchShimeDateEnd);	//締め日（末日＝99）終了
					GetSearchDECD 			= B00020ToolsTextControl.Trim(GetSearchDECD);			//納品先コード
					GetSearchDepartmentCd 	= B00020ToolsTextControl.Trim(GetSearchDepartmentCd);	//部署CD

					GetSearchPaySiteStr 	= B00020ToolsTextControl.num_only_String02(GetSearchPaySiteStr);		//支払いサイト（月数）開始
					GetSearchPaySiteEnd		= B00020ToolsTextControl.num_only_String02(GetSearchPaySiteEnd);		//支払いサイト（月数）終了
					GetSearchPayDateStr 	= B00020ToolsTextControl.num_only_String02(GetSearchPayDateStr);		//支払日（日＝99）開始
					GetSearchPayDateEnd 	= B00020ToolsTextControl.num_only_String02(GetSearchPayDateEnd);		//支払日（日＝99）終了
					GetSearchShimeDateStr 	= B00020ToolsTextControl.num_only_String02(GetSearchShimeDateStr);	//締め日（末日＝99）開始
					GetSearchShimeDateEnd 	= B00020ToolsTextControl.num_only_String02(GetSearchShimeDateEnd);	//締め日（末日＝99）終了
					
					ArrayList<String> SearchClWh 			= new ArrayList<String>(); 		//担当倉庫
					ArrayList<String> SearchClCd 			= new ArrayList<String>();		//荷主CD
					ArrayList<String> SearchSPCd 			= new ArrayList<String>();		//仕入先コード
					ArrayList<String> SearchSPName 			= new ArrayList<String>();		//仕入先名
					ArrayList<String> SearchSPPost 			= new ArrayList<String>();		//仕入先郵便
					ArrayList<String> SearchSPAdd 			= new ArrayList<String>();		//仕入先住所
					ArrayList<String> SearchSPTel 			= new ArrayList<String>();		//仕入先電話
					ArrayList<String> SearchSPFax 			= new ArrayList<String>();		//仕入先FAX
					ArrayList<String> SearchSPMail 			= new ArrayList<String>();		//仕入先MAIL
					ArrayList<String> SearchCom 			= new ArrayList<String>();		//コメント
					ArrayList<String> SearchPTMSCDBMN 		= new ArrayList<String>();		//基幹Sysコード（部門）
					ArrayList<String> SearchPTMSCDNINUSHI 	= new ArrayList<String>();		//基幹Sysコード（荷主）
					ArrayList<Integer> SearchPaySiteStr 	= new ArrayList<Integer>();		//支払いサイト（月数）開始
					ArrayList<Integer> SearchPayDateStr 	= new ArrayList<Integer>();		//支払日（日＝99）開始
					ArrayList<Integer> SearchShimeDateStr 	= new ArrayList<Integer>();		//締め日（末日＝99）開始
					ArrayList<Integer> SearchPaySiteEnd 	= new ArrayList<Integer>();		//支払いサイト（月数）終了
					ArrayList<Integer> SearchPayDateEnd 	= new ArrayList<Integer>();		//支払日（日＝99）終了
					ArrayList<Integer> SearchShimeDateEnd 	= new ArrayList<Integer>();		//締め日（末日＝99）終了
					ArrayList<String> SearchDECD 			= new ArrayList<String>();		//納品先コード
					ArrayList<String> SearchDepartmentCd 	= new ArrayList<String>();		//部署CD
					boolean AllSearch = false;
					
					if(!"".equals(GetSearchClWh				)){SearchClWh.add(GetSearchClWh);}				//担当倉庫
					if(!"".equals(GetSearchClCd				)){SearchClCd.add(GetSearchClCd);}				//荷主CD
					if(!"".equals(GetSearchSPCd				)){SearchSPCd.add(GetSearchSPCd);}				//仕入先コード
					if(!"".equals(GetSearchSPName			)){SearchSPName.add(GetSearchSPName);}			//仕入先名
					if(!"".equals(GetSearchSPPost			)){SearchSPPost.add(GetSearchSPPost);}			//仕入先郵便
					if(!"".equals(GetSearchSPAdd			)){SearchSPAdd.add(GetSearchSPAdd);}			//仕入先住所
					if(!"".equals(GetSearchSPTel			)){SearchSPTel.add(GetSearchSPTel);}			//仕入先電話
					if(!"".equals(GetSearchSPFax			)){SearchSPFax.add(GetSearchSPFax);}			//仕入先FAX
					if(!"".equals(GetSearchSPMail			)){SearchSPMail.add(GetSearchSPMail);}			//仕入先MAIL
					if(!"".equals(GetSearchCom				)){SearchCom.add(GetSearchCom);}				//コメント
					if(!"".equals(GetSearchPTMSCDBMN		)){SearchPTMSCDBMN.add(GetSearchPTMSCDBMN);}			//基幹Sysコード（部門）
					if(!"".equals(GetSearchPTMSCDNINUSHI	)){SearchPTMSCDNINUSHI.add(GetSearchPTMSCDNINUSHI);}	//基幹Sysコード（荷主）
					if(!"".equals(GetSearchPaySiteStr		)){SearchPaySiteStr.add((int)(Float.parseFloat(GetSearchPaySiteStr)));}			//支払いサイト（月数）開始
					if(!"".equals(GetSearchPaySiteEnd		)){SearchPaySiteEnd.add((int)(Float.parseFloat(GetSearchPaySiteEnd)));}			//支払いサイト（月数）終了
					if(!"".equals(GetSearchPayDateStr		)){SearchPayDateStr.add((int)(Float.parseFloat(GetSearchPayDateStr)));}			//支払日（日＝99）開始
					if(!"".equals(GetSearchPayDateEnd		)){SearchPayDateEnd.add((int)(Float.parseFloat(GetSearchPayDateEnd)));}			//支払日（日＝99）終了
					if(!"".equals(GetSearchShimeDateStr		)){SearchShimeDateStr.add((int)(Float.parseFloat(GetSearchShimeDateStr)));}		//締め日（末日＝99）開始
					if(!"".equals(GetSearchShimeDateEnd		)){SearchShimeDateEnd.add((int)(Float.parseFloat(GetSearchShimeDateEnd)));}		//締め日（末日＝99）終了
					if(!"".equals(GetSearchDECD				)){SearchDECD.add(GetSearchDECD);}			//納品先コード
					if(!"".equals(GetSearchDepartmentCd		)){SearchDepartmentCd.add(GetSearchDepartmentCd);}			//部署CD
					
					Object[][] SupplierRt = M00100SupplierRt.SupplierRt(
							SearchClWh,				//担当倉庫
							SearchClCd,				//荷主CD
							SearchSPCd,				//仕入先コード
							SearchSPName,			//仕入先名
							SearchSPPost,			//仕入先郵便
							SearchSPAdd,			//仕入先住所
							SearchSPTel,			//仕入先電話
							SearchSPFax,			//仕入先FAX
							SearchSPMail,			//仕入先MAIL
							SearchCom,				//コメント
							SearchPTMSCDBMN,		//基幹Sysコード（部門）
							SearchPTMSCDNINUSHI,	//基幹Sysコード（荷主）
							SearchPaySiteStr,		//支払いサイト（月数）開始
							SearchPayDateStr,		//支払日（日＝99）開始
							SearchShimeDateStr,		//締め日（末日＝99）開始
							SearchPaySiteEnd,		//支払いサイト（月数）終了
							SearchPayDateEnd,		//支払日（日＝99）終了
							SearchShimeDateEnd,		//締め日（末日＝99）終了
							SearchDECD,				//納品先コード
							SearchDepartmentCd,		//部署CD
							AllSearch);
					
					for(int i=0;i<SupplierRt.length;i++) {
						Object[] SetOb = new Object[SupplierRt[i].length+1];
						SetOb[0] = false;
						for(int i01=0;i01<SupplierRt[i].length;i01++) {
							SetOb[i01+1] = SupplierRt[i][i01];
						}
						tableModel_ms01.addRow(SetOb);
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
					String TgtClCd 			= "";
					String TgtWhCd 			= "";
					String TgtSupplierCd 	= "";
					boolean KickFg = false;
					for(int i=0;i<RowCount;i++) {
						if((boolean)tableModel_ms01.getValueAt(i, 0)) {
							TgtClCd = ""+tableModel_ms01.getValueAt(i, M00100SupplierRt.ColClCd+1);
							TgtWhCd = ""+tableModel_ms01.getValueAt(i, M00100SupplierRt.ColClWh+1);
							TgtSupplierCd 	= ""+tableModel_ms01.getValueAt(i, M00100SupplierRt.ColSPCd+1);
							KickFg = true;
						}
					}
					if(KickFg) {
						SetX=main_fm.getX();
						SetY=main_fm.getY();

						main_fm.setVisible(false);
						main_fm.dispose();
						WM00101SupplierMstRenewAndCreate.SupplierMstRenewAndCreate(0,0,TgtClCd,TgtWhCd,TgtSupplierCd);
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
					
					String TgtClCd 			= "";
					String TgtWhCd 			= "";
					String TgtSupplierCd 	= "";
					WM00101SupplierMstRenewAndCreate.SupplierMstRenewAndCreate(0,0,TgtClCd,TgtWhCd,TgtSupplierCd);
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
						WM00102SupplierMstExcelEntry.SupplierMstExcelEntry(0,0,Selected);
					}
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
					B10010TableControl.TableOutPutCsv("出力先選択","仕入先マスタ検索結果",tb01);
					RenewFg = true;
				}
			}
		});
		
		//エクセル出力ボタン押下時の挙動
		ExcelBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					B10010TableControl.TableOutPutExcel("出力先選択","仕入先マスタ検索結果",tb01);
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