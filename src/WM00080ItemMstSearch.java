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

public class WM00080ItemMstSearch{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	
	static String[][] PrefecturesCdList;
	static String[][] MunicipalityCd;
	public static void ItemMstSearch(int x,int y) {
		A00000Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		
		final JFrame main_fm = B00110FrameParts.FrameCreate(x,y,1300,750,"Corgi00商品マスタ検索","");
		JLabel userinfo = B00110FrameParts.UserInfo();
		JButton exit_btn = B00110FrameParts.ExitBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		
		//検索条件パネル
		JPanel PN_Search = B00110FrameParts.JPanelSet(10,40,1280,220,"White");
		JLabel PN_SearchLabel = B00110FrameParts.JLabelSet(10,0,150,20,"検索条件",11,0);
		
		//検索条件
		JLabel LB_SearchClGpCd  						= B00110FrameParts.JLabelSet(		  0, 25,130,20,"荷主グループコード:"	,11,1);
		final JComboBox   TB_SearchClGpCd				= B00110FrameParts.JComboBoxSet(	130, 25,180,20,B00100DefaultVariable.SearchClGpList[0],11);	//荷主グループコード
		JLabel LB_SearchItemCd  						= B00110FrameParts.JLabelSet(		  0, 50,130,20,"商品コード:"			,11,1);
		final JTextField TB_SearchItemCd  				= B00110FrameParts.JTextFieldSet(	130, 50,100,20,""						,11,0);	//商品コード
		JLabel LB2_SearchItemCd  						= B00110FrameParts.JLabelSet(		230, 50, 80,20,"と一致"					,11,0);	//商品コード
		JLabel LB_SearchCLItemCd  						= B00110FrameParts.JLabelSet(		  0, 75,130,20,"荷主商品コード:"		,11,1);
		final JTextField TB_SearchCLItemCd  			= B00110FrameParts.JTextFieldSet(	130, 75,100,20,""						,11,0);	//荷主商品コード
		JLabel LB2_SearchCLItemCd  						= B00110FrameParts.JLabelSet(		230, 75, 80,20,"と一致"					,11,0);	//荷主商品コード
		JLabel LB_SearchItemName  						= B00110FrameParts.JLabelSet(		  0,100,130,20,"商品名:"				,11,1);
		final JTextField TB_SearchItemName  			= B00110FrameParts.JTextFieldSet(	130,100,100,20,""						,11,0);	//商品名
		JLabel LB2_SearchItemName  						= B00110FrameParts.JLabelSet(		230,100, 80,20,"を含む"					,11,0);	//商品名
		JLabel LB_SearchJanCd  							= B00110FrameParts.JLabelSet(		  0,125,130,20,"JANCD:"					,11,1);
		final JTextField TB_SearchJanCd  				= B00110FrameParts.JTextFieldSet(	130,125,100,20,""						,11,0);	//JANCD
		JLabel LB2_SearchJanCd  						= B00110FrameParts.JLabelSet(		230,125, 80,20,"と一致"					,11,0);	//JANCD
		JLabel LB_SearchDelFg  							= B00110FrameParts.JLabelSet(		  0,150,130,20,"削除区分:"				,11,1);
		final JComboBox   TB_SearchDelFg				= B00110FrameParts.JComboBoxSet(	130,150,180,20,B00100DefaultVariable.SearchDelList[0],11);	//削除区分
		
		JLabel LB_SearchDeliveryTypeCd01  				= B00110FrameParts.JLabelSet(		310, 25,130,20,"運送タイプコード01:"	,11,1);
		final JComboBox   TB_SearchDeliveryTypeCd01  	= B00110FrameParts.JComboBoxSet(	440, 25,180,20,B00100DefaultVariable.SearchDeliveryType01[0],11);	//運送タイプコード01
		JLabel LB_SearchDeliveryTypeCd02  				= B00110FrameParts.JLabelSet(		310, 50,130,20,"運送タイプコード02:"	,11,1);
		final JComboBox   TB_SearchDeliveryTypeCd02  	= B00110FrameParts.JComboBoxSet(	440, 50,180,20,B00100DefaultVariable.SearchDeliveryType02[0],11);	//運送タイプコード02
		JLabel LB_SearchDeliveryTypeCd03  				= B00110FrameParts.JLabelSet(		310, 75,130,20,"運送タイプコード03:"	,11,1);
		final JComboBox   TB_SearchDeliveryTypeCd03  	= B00110FrameParts.JComboBoxSet(	440, 75,180,20,B00100DefaultVariable.SearchDeliveryType03[0],11);	//運送タイプコード03
		JLabel LB_SearchDeliveryTypeCd04  				= B00110FrameParts.JLabelSet(		310,100,130,20,"運送タイプコード04:"	,11,1);
		final JComboBox   TB_SearchDeliveryTypeCd04  	= B00110FrameParts.JComboBoxSet(	440,100,180,20,B00100DefaultVariable.SearchDeliveryType04[0],11);	//運送タイプコード04
		JLabel LB_SearchDeliveryTypeCd05  				= B00110FrameParts.JLabelSet(		310,125,130,20,"運送タイプコード05:"	,11,1);
		final JComboBox   TB_SearchDeliveryTypeCd05  	= B00110FrameParts.JComboBoxSet(	440,125,180,20,B00100DefaultVariable.SearchDeliveryType05[0],11);	//運送タイプコード05
		JLabel LB_SearchTildFG  						= B00110FrameParts.JLabelSet(		310,150,130,20,"温度区分:"				,11,1);
		final JComboBox   TB_SearchTildFG				= B00110FrameParts.JComboBoxSet(	440,150,180,20,B00100DefaultVariable.SearchTildFG[0],11);				//温度区分
		JLabel LB_SearchTildName  						= B00110FrameParts.JLabelSet(		310,175,130,20,"温度区分名:"			,11,1);
		final JTextField TB_SearchTildName  			= B00110FrameParts.JTextFieldSet(	440,175,100,20,""						,11,0);	//温度区分名
		JLabel LB2_SearchTildName  						= B00110FrameParts.JLabelSet(		540,175, 80,20,"を含む"					,11,0);	//温度区分名
		
		JLabel LB_SearchItemMDNo  						= B00110FrameParts.JLabelSet(		620, 25,130,20,"商品モデル番号（型番）:",11,1);
		final JTextField TB_SearchItemMDNo  			= B00110FrameParts.JTextFieldSet(	750, 25,100,20,""						,11,0);	//商品モデル番号（型番）
		JLabel LB2_SearchItemMDNo  						= B00110FrameParts.JLabelSet(		850, 25, 80,20,"と一致"					,11,0);	//商品モデル番号（型番）
		JLabel LB_SearchCategoryCd  					= B00110FrameParts.JLabelSet(		620, 50,130,20,"商品カテゴリCD:"		,11,1);
		final JTextField TB_SearchCategoryCd  			= B00110FrameParts.JTextFieldSet(	750, 50,100,20,""						,11,0);	//商品カテゴリCD
		JLabel LB2_SearchCategoryCd  					= B00110FrameParts.JLabelSet(		850, 50, 80,20,"と一致"					,11,0);	//商品カテゴリCD
		JLabel LB_SearchCategoryName 	 				= B00110FrameParts.JLabelSet(		620, 75,130,20,"商品カテゴリ名:"		,11,1);
		final JTextField TB_SearchCategoryName 	 		= B00110FrameParts.JTextFieldSet(	750, 75,100,20,""						,11,0);	//商品カテゴリ名
		JLabel LB2_SearchCategoryName 	 				= B00110FrameParts.JLabelSet(		850, 75, 80,20,"を含む"					,11,0);	//商品カテゴリ名
		JLabel LB_SearchItemColorCd  					= B00110FrameParts.JLabelSet(		620,100,130,20,"商品カラーコード:"		,11,1);
		final JTextField TB_SearchItemColorCd  			= B00110FrameParts.JTextFieldSet(	750,100,100,20,""						,11,0);	//商品カラーコード
		JLabel LB2_SearchItemColorCd  					= B00110FrameParts.JLabelSet(		850,100, 80,20,"と一致"					,11,0);	//商品カラーコード
		JLabel LB_SearchItemColorName  					= B00110FrameParts.JLabelSet(		620,125,130,20,"商品カラー名:"			,11,1);
		final JTextField TB_SearchItemColorName  		= B00110FrameParts.JTextFieldSet(	750,125,100,20,""						,11,0);	//商品カラー名
		JLabel LB2_SearchItemColorName  				= B00110FrameParts.JLabelSet(		850,125, 80,20,"を含む"					,11,0);	//商品カラー名
		JLabel LB_SearchItemSizeCd  					= B00110FrameParts.JLabelSet(		620,150,130,20,"商品サイズコード:"		,11,1);
		final JTextField TB_SearchItemSizeCd  			= B00110FrameParts.JTextFieldSet(	750,150,100,20,""						,11,0);	//商品サイズコード
		JLabel LB2_SearchItemSizeCd  					= B00110FrameParts.JLabelSet(		850,150, 80,20,"と一致"					,11,0);	//商品サイズコード
		JLabel LB_SearchItemSizeName  					= B00110FrameParts.JLabelSet(		620,175,130,20,"商品サイス名:"			,11,1);
		final JTextField TB_SearchItemSizeName  		= B00110FrameParts.JTextFieldSet( 750,175,100,20,""						,11,0);	//商品サイス名
		JLabel LB2_SearchItemSizeName  					= B00110FrameParts.JLabelSet(	    850,175, 80,20,"を含む"					,11,0);	//商品サイス名
		
		for(int i=0;i<B00100DefaultVariable.SearchClGpList[1].length;i++) {
			if(A00000Main.ClGp.equals(B00100DefaultVariable.SearchClGpList[1][i])) {
				TB_SearchClGpCd.setSelectedIndex(i);
			}
		}
		
		if("9".equals(A00000Main.LoginUserAuthorityFG)) {
			//管理者以外は荷主グループコード変更禁止
		}else {
			TB_SearchClGpCd.setEnabled(RenewFg);
		}
		
		PN_Search.add(PN_SearchLabel);
		PN_Search.add(LB_SearchClGpCd);
		PN_Search.add(TB_SearchClGpCd);
		PN_Search.add(LB_SearchItemCd);
		PN_Search.add(TB_SearchItemCd);
		PN_Search.add(LB2_SearchItemCd);
		PN_Search.add(LB_SearchCLItemCd);
		PN_Search.add(TB_SearchCLItemCd);
		PN_Search.add(LB2_SearchCLItemCd);
		PN_Search.add(LB_SearchItemName);
		PN_Search.add(TB_SearchItemName);
		PN_Search.add(LB2_SearchItemName);
		PN_Search.add(LB_SearchDeliveryTypeCd01);
		PN_Search.add(TB_SearchDeliveryTypeCd01);
		PN_Search.add(LB_SearchDeliveryTypeCd02);
		PN_Search.add(TB_SearchDeliveryTypeCd02);
		PN_Search.add(LB_SearchDeliveryTypeCd03);
		PN_Search.add(TB_SearchDeliveryTypeCd03);
		PN_Search.add(LB_SearchDeliveryTypeCd04);
		PN_Search.add(TB_SearchDeliveryTypeCd04);
		PN_Search.add(LB_SearchDeliveryTypeCd05);
		PN_Search.add(TB_SearchDeliveryTypeCd05);
		PN_Search.add(LB_SearchTildFG);
		PN_Search.add(TB_SearchTildFG);
		PN_Search.add(LB_SearchTildName);
		PN_Search.add(TB_SearchTildName);
		PN_Search.add(LB2_SearchTildName);
		PN_Search.add(LB_SearchItemMDNo);
		PN_Search.add(TB_SearchItemMDNo);
		PN_Search.add(LB2_SearchItemMDNo);
		PN_Search.add(LB_SearchCategoryCd);
		PN_Search.add(TB_SearchCategoryCd);
		PN_Search.add(LB2_SearchCategoryCd);
		PN_Search.add(LB_SearchCategoryName);
		PN_Search.add(TB_SearchCategoryName);
		PN_Search.add(LB2_SearchCategoryName);
		PN_Search.add(LB_SearchItemColorCd);
		PN_Search.add(TB_SearchItemColorCd);
		PN_Search.add(LB2_SearchItemColorCd);
		PN_Search.add(LB_SearchItemColorName);
		PN_Search.add(TB_SearchItemColorName);
		PN_Search.add(LB2_SearchItemColorName);
		PN_Search.add(LB_SearchItemSizeCd);
		PN_Search.add(TB_SearchItemSizeCd);
		PN_Search.add(LB2_SearchItemSizeCd);
		PN_Search.add(LB_SearchItemSizeName);
		PN_Search.add(TB_SearchItemSizeName);
		PN_Search.add(LB2_SearchItemSizeName);
		PN_Search.add(LB_SearchJanCd);
		PN_Search.add(TB_SearchJanCd);
		PN_Search.add(LB2_SearchJanCd);
		PN_Search.add(LB_SearchDelFg);
		PN_Search.add(TB_SearchDelFg);
		
		//検索ボタン
		JButton SearchBtn = B00110FrameParts.BtnSet(1050,175,100,20,"検索",11);
		PN_Search.add(SearchBtn);
		
		main_fm.add(PN_Search);
		
		Object[][] RtSettingItemMstRt = M00070ItemMstRt.RtSettingItemMstRt();
		
		String[] columnNames01 = new String[RtSettingItemMstRt.length+1];
		
		columnNames01[0] = "Fg";
		for(int i=0;i<RtSettingItemMstRt.length;i++) {
			columnNames01[1+i] = ""+RtSettingItemMstRt[i][3];
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
		
		for(int i=0;i<RtSettingItemMstRt.length;i++) {
			column = columnModel01.getColumn(1+i);	column.setPreferredWidth( 90*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());
		}
		
		//スクロール用設定
		JScrollPane scpn01 = B00110FrameParts.JScrollPaneSet(10,270,1280,350,tb01);
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
					
					String GetSearchClGpCd = B00100DefaultVariable.SearchClGpList[1][TB_SearchClGpCd.getSelectedIndex()];	//荷主グループコード
					String GetSearchItemCd = TB_SearchItemCd.getText();		//商品コード
					String GetSearchCLItemCd = TB_SearchCLItemCd.getText();	//荷主商品コード
					String GetSearchItemName = TB_SearchItemName.getText();	//商品名
					String GetSearchJanCd = TB_SearchJanCd.getText();		//JANCD
					String GetSearchDelFg = B00100DefaultVariable.SearchDelList[1][TB_SearchDelFg.getSelectedIndex()];		//削除区分
					String GetSearchDeliveryTypeCd01 = B00100DefaultVariable.SearchDeliveryType01[1][TB_SearchDeliveryTypeCd01.getSelectedIndex()];	//運送タイプコード01
					String GetSearchDeliveryTypeCd02 = B00100DefaultVariable.SearchDeliveryType02[1][TB_SearchDeliveryTypeCd02.getSelectedIndex()];	//運送タイプコード02
					String GetSearchDeliveryTypeCd03 = B00100DefaultVariable.SearchDeliveryType03[1][TB_SearchDeliveryTypeCd03.getSelectedIndex()];	//運送タイプコード03
					String GetSearchDeliveryTypeCd04 = B00100DefaultVariable.SearchDeliveryType04[1][TB_SearchDeliveryTypeCd04.getSelectedIndex()];	//運送タイプコード04
					String GetSearchDeliveryTypeCd05 = B00100DefaultVariable.SearchDeliveryType05[1][TB_SearchDeliveryTypeCd05.getSelectedIndex()];	//運送タイプコード05
					String GetSearchTildFG = B00100DefaultVariable.SearchTildFG[1][TB_SearchTildFG.getSelectedIndex()];		//温度区分
					String GetSearchTildName = TB_SearchTildName.getText();				//温度区分名
					String GetSearchItemMDNo = TB_SearchItemMDNo.getText();				//商品モデル番号（型番）
					String GetSearchCategoryCd = TB_SearchCategoryCd.getText();			//商品カテゴリCD
					String GetSearchCategoryName = TB_SearchCategoryName.getText();		//商品カテゴリ名
					String GetSearchItemColorCd = TB_SearchItemColorCd.getText();		//商品カラーコード
					String GetSearchItemColorName = TB_SearchItemColorName.getText();	//商品カラー名
					String GetSearchItemSizeCd = TB_SearchItemSizeCd.getText();			//商品サイズコード
					String GetSearchItemSizeName = TB_SearchItemSizeName.getText();		//商品サイス名
					
					if(null==GetSearchClGpCd			) {GetSearchClGpCd= "";}			//荷主グループコード
					if(null==GetSearchItemCd			) {GetSearchItemCd= "";}			//商品コード
					if(null==GetSearchCLItemCd			) {GetSearchCLItemCd= "";}			//荷主商品コード
					if(null==GetSearchItemName			) {GetSearchItemName= "";}			//商品名
					if(null==GetSearchJanCd				) {GetSearchJanCd= "";}				//JANCD
					if(null==GetSearchDelFg				) {GetSearchDelFg= "";}				//削除区分
					if(null==GetSearchDeliveryTypeCd01	) {GetSearchDeliveryTypeCd01= "";}	//運送タイプコード01
					if(null==GetSearchDeliveryTypeCd02	) {GetSearchDeliveryTypeCd02= "";}	//運送タイプコード02
					if(null==GetSearchDeliveryTypeCd03	) {GetSearchDeliveryTypeCd03= "";}	//運送タイプコード03
					if(null==GetSearchDeliveryTypeCd04	) {GetSearchDeliveryTypeCd04= "";}	//運送タイプコード04
					if(null==GetSearchDeliveryTypeCd05	) {GetSearchDeliveryTypeCd05= "";}	//運送タイプコード05
					if(null==GetSearchTildFG			) {GetSearchTildFG= "";}			//温度区分
					if(null==GetSearchTildName			) {GetSearchTildName= "";}			//温度区分名
					if(null==GetSearchItemMDNo			) {GetSearchItemMDNo= "";}			//商品モデル番号（型番）
					if(null==GetSearchCategoryCd		) {GetSearchCategoryCd= "";}		//商品カテゴリCD
					if(null==GetSearchCategoryName		) {GetSearchCategoryName= "";}		//商品カテゴリ名
					if(null==GetSearchItemColorCd		) {GetSearchItemColorCd= "";}		//商品カラーコード
					if(null==GetSearchItemColorName		) {GetSearchItemColorName= "";}		//商品カラー名
					if(null==GetSearchItemSizeCd		) {GetSearchItemSizeCd= "";}		//商品サイズコード
					if(null==GetSearchItemSizeName		) {GetSearchItemSizeName= "";}		//商品サイス名
					
					GetSearchClGpCd				= B00020ToolsTextControl.Trim(GetSearchClGpCd);		//荷主グループコード
					GetSearchItemCd				= B00020ToolsTextControl.Trim(GetSearchItemCd);				//商品コード
					GetSearchCLItemCd			= B00020ToolsTextControl.Trim(GetSearchCLItemCd);			//荷主商品コード
					GetSearchItemName			= B00020ToolsTextControl.Trim(GetSearchItemName);			//商品名
					GetSearchJanCd				= B00020ToolsTextControl.Trim(GetSearchJanCd);				//JANCD
					GetSearchDelFg				= B00020ToolsTextControl.Trim(GetSearchDelFg);				//削除区分
					GetSearchDeliveryTypeCd01	= B00020ToolsTextControl.Trim(GetSearchDeliveryTypeCd01);	//運送タイプコード01
					GetSearchDeliveryTypeCd02	= B00020ToolsTextControl.Trim(GetSearchDeliveryTypeCd02);	//運送タイプコード02
					GetSearchDeliveryTypeCd03	= B00020ToolsTextControl.Trim(GetSearchDeliveryTypeCd03);	//運送タイプコード03
					GetSearchDeliveryTypeCd04	= B00020ToolsTextControl.Trim(GetSearchDeliveryTypeCd04);	//運送タイプコード04
					GetSearchDeliveryTypeCd05	= B00020ToolsTextControl.Trim(GetSearchDeliveryTypeCd05);	//運送タイプコード05
					GetSearchTildFG				= B00020ToolsTextControl.Trim(GetSearchTildFG);				//温度区分
					GetSearchTildName			= B00020ToolsTextControl.Trim(GetSearchTildName);			//温度区分名
					GetSearchItemMDNo			= B00020ToolsTextControl.Trim(GetSearchItemMDNo);			//商品モデル番号（型番）
					GetSearchCategoryCd			= B00020ToolsTextControl.Trim(GetSearchCategoryCd);			//商品カテゴリCD
					GetSearchCategoryName		= B00020ToolsTextControl.Trim(GetSearchCategoryName);		//商品カテゴリ名
					GetSearchItemColorCd		= B00020ToolsTextControl.Trim(GetSearchItemColorCd);		//商品カラーコード
					GetSearchItemColorName		= B00020ToolsTextControl.Trim(GetSearchItemColorName);		//商品カラー名
					GetSearchItemSizeCd			= B00020ToolsTextControl.Trim(GetSearchItemSizeCd);			//商品サイズコード
					GetSearchItemSizeName		= B00020ToolsTextControl.Trim(GetSearchItemSizeName);		//商品サイス名
					
					ArrayList<String> SearchClGpCd = new ArrayList<String>();			//荷主グループコード
					ArrayList<String> SearchItemCd = new ArrayList<String>();			//商品コード
					ArrayList<String> SearchCLItemCd = new ArrayList<String>();			//荷主商品コード
					ArrayList<String> SearchItemName = new ArrayList<String>();			//商品名
					ArrayList<String> SearchDeliveryTypeCd01 = new ArrayList<String>();	//運送タイプコード01
					ArrayList<String> SearchDeliveryTypeCd02 = new ArrayList<String>();	//運送タイプコード02
					ArrayList<String> SearchDeliveryTypeCd03 = new ArrayList<String>();	//運送タイプコード03
					ArrayList<String> SearchDeliveryTypeCd04 = new ArrayList<String>();	//運送タイプコード04
					ArrayList<String> SearchDeliveryTypeCd05 = new ArrayList<String>();	//運送タイプコード05
					ArrayList<String> SearchItemMDNo = new ArrayList<String>();			//商品モデル番号（型番）
					ArrayList<String> SearchCategoryCd = new ArrayList<String>();		//商品カテゴリCD
					ArrayList<String> SearchCategoryName = new ArrayList<String>();		//商品カテゴリ名
					ArrayList<String> SearchItemColorCd = new ArrayList<String>();		//商品カラーコード
					ArrayList<String> SearchItemColorName = new ArrayList<String>();	//商品カラー名
					ArrayList<String> SearchItemSizeCd = new ArrayList<String>();		//商品サイズコード
					ArrayList<String> SearchItemSizeName = new ArrayList<String>();		//商品サイス名
					ArrayList<String> SearchJanCd = new ArrayList<String>();			//JANCD
					ArrayList<String> SearchTildFG = new ArrayList<String>();			//温度区分
					ArrayList<String> SearchTildName = new ArrayList<String>();			//温度区分名
					ArrayList<String> SearchDelFg = new ArrayList<String>();			//削除フラグ
					boolean AllSearch = true;
					
					if(!"".equals(GetSearchClGpCd			)){SearchClGpCd.add(GetSearchClGpCd);}			//荷主グループコード
					if(!"".equals(GetSearchItemCd			)){SearchItemCd.add(GetSearchItemCd);}						//商品コード
					if(!"".equals(GetSearchCLItemCd			)){SearchCLItemCd.add(GetSearchCLItemCd);}					//荷主商品コード
					if(!"".equals(GetSearchItemName			)){SearchItemName.add(GetSearchItemName);}					//商品名
					if(!"".equals(GetSearchJanCd			)){SearchJanCd.add(GetSearchJanCd);}						//JANCD
					if(!"".equals(GetSearchDelFg			)){SearchDelFg.add(GetSearchDelFg);}						//削除区分
					if(!"".equals(GetSearchDeliveryTypeCd01	)){SearchDeliveryTypeCd01.add(GetSearchDeliveryTypeCd01);}	//運送タイプコード01
					if(!"".equals(GetSearchDeliveryTypeCd02	)){SearchDeliveryTypeCd02.add(GetSearchDeliveryTypeCd02);}	//運送タイプコード02
					if(!"".equals(GetSearchDeliveryTypeCd03	)){SearchDeliveryTypeCd03.add(GetSearchDeliveryTypeCd03);}	//運送タイプコード03
					if(!"".equals(GetSearchDeliveryTypeCd04	)){SearchDeliveryTypeCd04.add(GetSearchDeliveryTypeCd04);}	//運送タイプコード04
					if(!"".equals(GetSearchDeliveryTypeCd05	)){SearchDeliveryTypeCd05.add(GetSearchDeliveryTypeCd05);}	//運送タイプコード05
					if(!"".equals(GetSearchTildFG			)){SearchTildFG.add(GetSearchTildFG);}						//温度区分
					if(!"".equals(GetSearchTildName			)){SearchTildName.add(GetSearchTildName);}					//温度区分名
					if(!"".equals(GetSearchItemMDNo			)){SearchItemMDNo.add(GetSearchItemMDNo);}					//商品モデル番号（型番）
					if(!"".equals(GetSearchCategoryCd		)){SearchCategoryCd.add(GetSearchCategoryCd);}				//商品カテゴリCD
					if(!"".equals(GetSearchCategoryName		)){SearchCategoryName.add(GetSearchCategoryName);}			//商品カテゴリ名
					if(!"".equals(GetSearchItemColorCd		)){SearchItemColorCd.add(GetSearchItemColorCd);}			//商品カラーコード
					if(!"".equals(GetSearchItemColorName	)){SearchItemColorName.add(GetSearchItemColorName);}		//商品カラー名
					if(!"".equals(GetSearchItemSizeCd		)){SearchItemSizeCd.add(GetSearchItemSizeCd);}				//商品サイズコード
					if(!"".equals(GetSearchItemSizeName		)){SearchItemSizeName.add(GetSearchItemSizeName);}			//商品サイス名
					
					Object[][] ItemMstRt = M00070ItemMstRt.ItemMstRt(
							SearchClGpCd,			//荷主グループコード
							SearchItemCd,			//商品コード
							SearchCLItemCd,			//荷主商品コード
							SearchItemName,			//商品名
							SearchDeliveryTypeCd01,	//運送タイプコード01
							SearchDeliveryTypeCd02,	//運送タイプコード02
							SearchDeliveryTypeCd03,	//運送タイプコード03
							SearchDeliveryTypeCd04,	//運送タイプコード04
							SearchDeliveryTypeCd05,	//運送タイプコード05
							SearchItemMDNo,			//商品モデル番号（型番）
							SearchCategoryCd,		//商品カテゴリCD
							SearchCategoryName,		//商品カテゴリ名
							SearchItemColorCd,		//商品カラーコード
							SearchItemColorName,	//商品カラー名
							SearchItemSizeCd,		//商品サイズコード
							SearchItemSizeName,		//商品サイス名
							SearchJanCd,			//JANCD
							SearchTildFG,			//温度区分
							SearchTildName,			//温度区分名
							SearchDelFg,			//削除フラグ
							AllSearch);
					
					for(int i=0;i<ItemMstRt.length;i++) {
						Object[] SetOb = new Object[ItemMstRt[i].length+1];
						SetOb[0] = false;
						for(int i01=0;i01<ItemMstRt[i].length;i01++) {
							SetOb[i01+1] = ""+ItemMstRt[i][i01];
						}
						tableModel_ms01.addRow(SetOb);
					}
					if(0<ItemMstRt.length) {
						B10010TableControl.AddSortON(tb01,tableModel_ms01);
					}else {
						B10010TableControl.AddSortOFF(tb01,tableModel_ms01);
					}
					RenewFg = true;
				}
			}
		});
		//Excel取込ボタン押下時の挙動
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
						WM00082ItemMstExcelEntry.ItemMstExcelEntry(0,0,Selected);
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
					String TgtClgpCd = "";
					String TgtItemCd = "";
					
					for(int i=0;i<RowCount;i++) {
						if((boolean)tableModel_ms01.getValueAt(i, 0)) {
							TgtClgpCd 	= ""+tableModel_ms01.getValueAt(i, 1);
							TgtItemCd 	= ""+tableModel_ms01.getValueAt(i, 3);
						}
					}
					if(!"".equals(TgtClgpCd) && !"".equals(TgtItemCd)) {
						SetX=main_fm.getX();
						SetY=main_fm.getY();
		
						main_fm.setVisible(false);
						main_fm.dispose();
						
						WM00081ItemMstRenewAndCreate.ItemMstRenewAndCreate(0,0,TgtClgpCd,TgtItemCd);
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
					
					WM00081ItemMstRenewAndCreate.ItemMstRenewAndCreate(0,0,"","");
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
					B10010TableControl.TableOutPutCsv("出力先選択","商品マスタ検索結果",tb01);
					RenewFg = true;
				}
			}
		});
		
		//エクセル出力ボタン押下時の挙動
		ExcelBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					B10010TableControl.TableOutPutExcel("出力先選択","商品マスタ検索結果",tb01);
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