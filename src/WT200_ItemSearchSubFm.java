import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class WT200_ItemSearchSubFm{
	//検索子画面として商品マスタ検索する
	//業務系の画面から召喚される
	
	static boolean RenewFg;
	
	static final int RtJFrame				= 0;
	static final int RtDefaultTableModel	= 1;
	static final int RtJTable				= 2;
	static final int EntryBtn				= 3;
	
	public static Object[] ItemSearchSubFm(int x,int y,String ClWh,String ClCd,String BackGroundColor){
		RenewFg=false;
		if(null==ClWh) {ClWh="";}
		if(null==ClCd) {ClCd="";}
		if("".equals(ClWh)) {ClWh=A00000_Main.ClWh;}
		if("".equals(ClCd)) {ClCd=A00000_Main.ClCd;}
		
		
		final JFrame Item_fm 	= B100_FrameParts.FrameCreate(x+10,y+10,800,750,"Corgi00商品検索　WT200_ItemSearchSubFm",BackGroundColor);
		JLabel 	ItemUserinfo 	= B100_FrameParts.UserInfo();
		JButton ItemExit_btn 	= B100_FrameParts.ExitBtn();
		JButton ItemEntry_btn 	= B100_FrameParts.EntryBtn();
		
		Item_fm.add(ItemUserinfo);
		Item_fm.add(ItemExit_btn);
		Item_fm.add(ItemEntry_btn);
		
		JLabel LB_WhCd				= B100_FrameParts.JLabelSet(	 0, 50,100,20,"倉庫:"				,11,1);
		JLabel LB_ClCd				= B100_FrameParts.JLabelSet(	 0, 75,100,20,"荷主:"				,11,1);
		
		final JComboBox TB_WhCd		= B100_FrameParts.JComboBoxSet(				100, 50,300,20,B100_DefaultVariable.WhList[0],11);	//倉庫コード
		final JComboBox TB_ClCd		= B100_FrameParts.JComboBoxSet(				100, 75,300,20,B100_DefaultVariable.ClList[0],11);	//荷主コード
		
		JLabel LB_SearchItemCd  						= B100_FrameParts.JLabelSet(		  0,100,130,20,"商品コード:"			,11,1);
		JLabel LB_SearchClItemCd  						= B100_FrameParts.JLabelSet(		  0,125,130,20,"荷主商品コード:"		,11,1);
		JLabel LB_SearchItemName  						= B100_FrameParts.JLabelSet(		  0,150,130,20,"商品名:"				,11,1);
		
		final JTextField TB_SearchItemCd  				= B100_FrameParts.JTextFieldSet(	130,100,100,20,""						,11,0);	//商品コード
		final JTextField TB_SearchClItemCd  			= B100_FrameParts.JTextFieldSet(	130,125,100,20,""						,11,0);	//荷主商品コード
		final JTextField TB_SearchItemName  			= B100_FrameParts.JTextFieldSet(	130,150,100,20,""						,11,0);	//商品名
		
		JLabel LB2_SearchItemCd  						= B100_FrameParts.JLabelSet(		230,100, 80,20,"と一致"					,11,0);	//商品コード
		JLabel LB2_SearchClItemCd  						= B100_FrameParts.JLabelSet(		230,125, 80,20,"と一致"					,11,0);	//商品コード
		JLabel LB2_SearchItemName  						= B100_FrameParts.JLabelSet(		230,150, 80,20,"を含む"					,11,0);	//商品名
		
		JButton ItemSearchKickBtn						= B100_FrameParts.BtnSet(			130,175, 90,20,"検索",11);
		
		TB_WhCd.setSelectedIndex(	B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.WhList[1]		,ClWh ,true) );		//倉庫コード
		TB_ClCd.setSelectedIndex(	B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.ClList[1]		,ClCd ,true) );		//荷主コード
		
		
		TB_WhCd.setEnabled(false);
		TB_ClCd.setEnabled(false);
		
		
		Item_fm.add(LB_ClCd);
		Item_fm.add(LB_WhCd);
		Item_fm.add(LB_SearchItemCd);
		Item_fm.add(LB_SearchClItemCd);
		Item_fm.add(LB_SearchItemName);
		Item_fm.add(TB_ClCd);
		Item_fm.add(TB_WhCd);
		Item_fm.add(TB_SearchItemCd);
		Item_fm.add(TB_SearchClItemCd);
		Item_fm.add(TB_SearchItemName);
		Item_fm.add(LB2_SearchItemCd);
		Item_fm.add(LB2_SearchClItemCd);
		Item_fm.add(LB2_SearchItemName);
		Item_fm.add(ItemSearchKickBtn);
		
		Object[][] RtSettingItemMstRt = M100_ItemMstRt.RtItemMstRt();
		
		String[] columnNamesItem = new String[RtSettingItemMstRt.length+1];
		
		columnNamesItem[0] = "Fg";
		for(int i=0;i<RtSettingItemMstRt.length;i++) {
			columnNamesItem[1+(int)RtSettingItemMstRt[i][1]] = ""+RtSettingItemMstRt[i][3];
		}
		
		//編集可能カラムの指定
		B100_TableControl.RenewTgt = new int[1];
		B100_TableControl.RenewTgt[0] = 0;

		final DefaultTableModel tableModel_msItem = new B100_TableControl.MyTableModel01(columnNamesItem,0);
		
		final JTable tbItem = new JTable(tableModel_msItem);
		tbItem.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tbItem.setRowHeight(20*A00000_Main.Mul/A00000_Main.Div);
		tbItem.setFont(new Font(A00000_Main.DefaultFont, Font.PLAIN, 12*A00000_Main.Mul/A00000_Main.Div));
		
		DefaultTableColumnModel columnModelItem
		= (DefaultTableColumnModel)tbItem.getColumnModel();
		
		//列幅初期設定 表示位置設定
		TableColumn column = null;
		
		column = columnModelItem.getColumn( 0);	column.setPreferredWidth( 30*A00000_Main.Mul/A00000_Main.Div);	//FG
		
		for(int i=0;i<RtSettingItemMstRt.length;i++) {
			if("int".equals((String)RtSettingItemMstRt[i][2])||"float".equals((String)RtSettingItemMstRt[i][2])) {
				column = columnModelItem.getColumn(1+(int)RtSettingItemMstRt[i][1]);	column.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.rightCellRenderer());
			}else {
				column = columnModelItem.getColumn(1+(int)RtSettingItemMstRt[i][1]);	column.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.leftCellRenderer());
			}
		}
		
		//スクロール用設定
		JScrollPane scpnItem = B100_FrameParts.JScrollPaneSet(10,225,760,380,tbItem);
		Item_fm.add(scpnItem);
		
		RenewFg=true;
		
		//商品マスタ検索ボタン押下時の挙動
		ItemSearchKickBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					int RowCount = tableModel_msItem.getRowCount();
					for(int i=0;i<RowCount;i++) {
						tableModel_msItem.removeRow(0);
					}
					String SearchTgtClCd		= B100_TextControl.Trim(B100_DefaultVariable.ClList[1][TB_ClCd.getSelectedIndex()]);	//荷主コード
					String SearchTgtWhCd		= B100_TextControl.Trim(B100_DefaultVariable.WhList[1][TB_WhCd.getSelectedIndex()]);	//倉庫コード
					String SearchTgtClGp		="";
					Object[][] ClMstRt = ClMstRt(SearchTgtClCd);
					if(1==ClMstRt.length) {
						SearchTgtClGp		=(String)ClMstRt[0][M100_ClMstRt.ColClGpCD];		//荷主グループCD
					}
					String SearchTgtItemCd		= B100_TextControl.Trim(TB_SearchItemCd.getText());
					String SearchTgtClItemCd	= B100_TextControl.Trim(TB_SearchClItemCd.getText());
					String SearchTgtItemName	= B100_TextControl.Trim(TB_SearchItemName.getText());
					
					Object[][] ItemSearch= ItemSearch(
												SearchTgtClGp,
												SearchTgtItemCd,
												SearchTgtClItemCd,
												SearchTgtItemName
												);
					for(int i=0;i<ItemSearch.length;i++) {
						Object[] SetOb = new Object[ItemSearch[i].length+1];
						SetOb[0] = false;
						for(int i01=0;i01<ItemSearch[i].length;i01++) {
							SetOb[i01+1] = ""+ItemSearch[i][i01];
						}
						tableModel_msItem.addRow(SetOb);
					}
					Item_fm.setVisible(true);
					RenewFg = true;
				}
			}
		});
		
		//商品マスタ検索チェックボックス操作時の挙動
		tableModel_msItem.addTableModelListener(new TableModelListener(){
			public void tableChanged(TableModelEvent e){
				if(RenewFg) {
					RenewFg = false;
					int row_count = tbItem.getRowCount();
					Boolean setBL=Boolean.valueOf(false);
					for(int i=0;i<row_count;i++){
						if(i!=e.getFirstRow()){
							tableModel_msItem.setValueAt(setBL, i, 0);
						}else {
	
						}
					}
					RenewFg = true;
				}
			}
		});
		
		ItemExit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				Item_fm.setVisible(false);
			}
		});
		
		Object[] Rt = {
				Item_fm
				,tableModel_msItem
				,tbItem
				,ItemEntry_btn
				};
		return Rt;
		
	}
	
	private static Object[][] ClMstRt(String TgtClCd){
		ArrayList<String> SearchClGpCD = new ArrayList<String>();
		ArrayList<String> SearchCLCD = new ArrayList<String>();
		ArrayList<String> SearchCLName = new ArrayList<String>();
		ArrayList<String> SearchPost = new ArrayList<String>();
		ArrayList<String> searchAdd = new ArrayList<String>();
		ArrayList<String> SearchTel = new ArrayList<String>();
		ArrayList<String> SearchFax = new ArrayList<String>();
		ArrayList<String> SearchMail = new ArrayList<String>();
		ArrayList<String> SearchCom = new ArrayList<String>();
		ArrayList<String> SearchWHCD = new ArrayList<String>();
		boolean AllSearch = false;
		
		SearchCLCD.add(TgtClCd);
		
		Object[][] ClMstRt = M100_ClMstRt.ClMstRt(
			SearchClGpCD,
			SearchCLCD,
			SearchCLName,
			SearchPost,
			searchAdd,
			SearchTel,
			SearchFax,
			SearchMail,
			SearchCom,
			SearchWHCD,
			AllSearch);
		return ClMstRt;
	}
	
	private static Object[][] ItemSearch(
			String SearchTgtClGp,
			String SearchTgtItemCd,
			String SearchTgtClItemCd,
			String SearchTgtItemName
			){
		ArrayList<String> SearchClCd				= new ArrayList<String>();	//荷主コード
		ArrayList<String> SearchClGpCd 				= new ArrayList<String>();	//荷主グループコード
		ArrayList<String> SearchItemCd 				= new ArrayList<String>();	//商品コード
		ArrayList<String> SearchClItemCd 			= new ArrayList<String>();	//荷主商品コード
		ArrayList<String> SearchItemName 			= new ArrayList<String>();	//商品名
		ArrayList<String> SearchDeliveryTypeCd01 	= new ArrayList<String>();	//運送タイプコード01
		ArrayList<String> SearchDeliveryTypeCd02 	= new ArrayList<String>();	//運送タイプコード02
		ArrayList<String> SearchDeliveryTypeCd03 	= new ArrayList<String>();	//運送タイプコード03
		ArrayList<String> SearchDeliveryTypeCd04 	= new ArrayList<String>();	//運送タイプコード04
		ArrayList<String> SearchDeliveryTypeCd05 	= new ArrayList<String>();	//運送タイプコード05
		ArrayList<String> SearchItemMDNo 			= new ArrayList<String>();	//商品モデル番号（型番）
		ArrayList<String> SearchCategoryCd 			= new ArrayList<String>();	//商品カテゴリCD
		ArrayList<String> SearchCategoryName 		= new ArrayList<String>();	//商品カテゴリ名
		ArrayList<String> SearchItemColorCd 		= new ArrayList<String>();	//商品カラーコード
		ArrayList<String> SearchItemColorName 		= new ArrayList<String>();	//商品カラー名
		ArrayList<String> SearchItemSizeCd 			= new ArrayList<String>();	//商品サイズコード
		ArrayList<String> SearchItemSizeName 		= new ArrayList<String>();	//商品サイズ名
		ArrayList<String> SearchJanCd 				= new ArrayList<String>();	//JANCD
		ArrayList<String> SearchTildFG 				= new ArrayList<String>();	//温度区分
		ArrayList<String> SearchTildName 			= new ArrayList<String>();	//温度区分名
		ArrayList<String> SearchDelFg 				= new ArrayList<String>();	//削除フラグ
		boolean AllSearch = true;
		
		if(!"".equals(SearchTgtClGp		)) {SearchClGpCd.add(	SearchTgtClGp);}
		if(!"".equals(SearchTgtItemCd	)) {SearchItemCd.add(	SearchTgtItemCd);}
		if(!"".equals(SearchTgtClItemCd	)) {SearchClItemCd.add(	SearchTgtClItemCd);}
		if(!"".equals(SearchTgtItemName	)) {SearchItemName.add(	SearchTgtItemName);}
		
		Object[][] ItemMstRt = M100_ItemMstRt.ItemMstRt(
				SearchClCd,			//荷主コード
				SearchClGpCd,			//荷主グループコード
				SearchItemCd,			//商品コード
				SearchClItemCd,			//荷主商品コード
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
				SearchItemSizeName,		//商品サイズ名
				SearchJanCd,			//JANCD
				SearchTildFG,			//温度区分
				SearchTildName,			//温度区分名
				SearchDelFg,			//削除フラグ
				AllSearch);
		
		return ItemMstRt;
	}
}