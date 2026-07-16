import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class WT100_Arrival_20_ForceEntry{
	//強制入庫（入荷予定なしで入荷実績登録する）※登録時に入荷予定も”1:入荷済み”ステータスで生成する
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	
	public static void ArrivalForceEntry(int x,int y) {
		A00000_Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;

		final JFrame main_fm 	= B100_FrameParts.FrameCreate(x,y,1300,800,"Corgi00強制入庫（予定なし入庫）　WT100_Arrival_20_ForceEntry","NK");
		JLabel userinfo 		= B100_FrameParts.UserInfo();
		JButton exit_btn 		= B100_FrameParts.ExitBtn();
		JButton entry_btn 		= B100_FrameParts.EntryBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		main_fm.add(entry_btn);
		
		//ヘッダ情報
		JLabel LB_WhCd				= B100_FrameParts.JLabelSet(	 0, 50,100,20,"倉庫:"				,11,1);
		JLabel LB_ClCd				= B100_FrameParts.JLabelSet(	 0, 75,100,20,"荷主:"				,11,1);
		JLabel LB_SpCd				= B100_FrameParts.JLabelSet(	 0,100,100,20,"仕入先:"				,11,1);
		final JComboBox TB_WhCd		= B100_FrameParts.JComboBoxSet(				100, 50,300,20,B100_DefaultVariable.WhList[0],11);	//倉庫
		final JComboBox TB_ClCd		= B100_FrameParts.JComboBoxSet(				100, 75,300,20,B100_DefaultVariable.ClList[0],11);	//荷主
		final JComboBox TB_SpCd		= B100_FrameParts.JComboBoxSet(				100,100,300,20,B100_DefaultVariable.SupplierList[0],12);	//仕入先
		
		
		//入力パネル
		JPanel PN_Entry 	= B100_FrameParts.JPanelSet(	10,150,600,400,"White");
		JLabel PN_EntryMsg 	= B100_FrameParts.JLabelSet(	10,  0,100, 20,"登録情報",11,0);
		PN_Entry.add(PN_EntryMsg);
		
		JLabel LB_Row				= B100_FrameParts.JLabelSet(	  0, 25,100,20,"行No:"				,11,1);
		
		JLabel LB_ItemCd			= B100_FrameParts.JLabelSet(	  0, 50,100,20,"商品CD:"			,11,1);
		
		JLabel LB_Lot				= B100_FrameParts.JLabelSet(	  0,100,100,20,"ロット:"			,11,1);
		JLabel LB_Expdate			= B100_FrameParts.JLabelSet(	  0,125,100,20,"賞味期限:"			,11,1);
		JLabel LB_Qty				= B100_FrameParts.JLabelSet(	  0,150,100,20,"入荷数量:"			,11,1);
		
		JLabel LB_PlQty				= B100_FrameParts.JLabelSet(	  0,200,100,20,"パレット数:"		,10,1);
		JLabel LB_CsQty				= B100_FrameParts.JLabelSet(	  0,225,100,20,"ケース数:"			,10,1);
		JLabel LB_CtQty				= B100_FrameParts.JLabelSet(	  0,250,100,20,"カートン数:"		,10,1);
		JLabel LB_BrQty				= B100_FrameParts.JLabelSet(	  0,275,100,20,"バラ数:"			,10,1);
		
		
		JLabel LB_UnitQty			= B100_FrameParts.JLabelSet(	230,175, 60,20,"入数"				,10,2);
		
		JLabel LB_Loc				= B100_FrameParts.JLabelSet(	  0,325,100,20,"格納ロケ:"			,11,1);
		
		JLabel LB_RecomendLoc		= B100_FrameParts.JLabelSet(	450,300,100,20,"推奨ロケ"			,11,2);
		
		final JFormattedTextField TB_Row		= B100_FrameParts.JFormattedTextFieldSet(	100, 25, 70,20,"0"			,12,1,"#,###");
		final JTextField TB_ItemCd				= B100_FrameParts.JTextFieldSet(				100, 50,100,20,""			,12,0);
		final JLabel TB_ItemName				= B100_FrameParts.JLabelSet(	 				100, 75,300,20,""			,10,0);
		JButton ItemSearchBtn					= B100_FrameParts.BtnSet(						220, 50, 90,20,"商品検索"	,11);
		final JTextField TB_Lot					= B100_FrameParts.JTextFieldSet(				100,100,100,20,""			,12,0);
		final JFormattedTextField TB_Expdate	= B100_FrameParts.JFormattedTextFieldSet(	100,125, 70,20,B100_DefaultVariable.DefaultExpDate	,12,0,"YYYY/MM/DD");
		JButton ExpdateAfterBtn					= B100_FrameParts.BtnSet(						220,125, 40,10,"▲"	,6);
		JButton ExpdateBeforeBtn				= B100_FrameParts.BtnSet(						220,135, 40,10,"▼"	,6);
		JButton ExpdateMonthBeforeBtn			= B100_FrameParts.BtnSet(						270,125, 40,20,"<<"	,6);
		JButton ExpdateTodayBtn					= B100_FrameParts.BtnSet(						310,125, 40,20,"■"	,6);
		JButton ExpdateMonthAfterBtn			= B100_FrameParts.BtnSet(						350,125, 40,20,">>"	,6);
		JButton ExpdateDefaultBtn				= B100_FrameParts.BtnSet(						400,125, 40,20,"!"	,12);
		
		final JFormattedTextField TB_Qty		= B100_FrameParts.JFormattedTextFieldSet(	100,150, 70,20,""				,12,1,"#,###");
		final JLabel TB_QtyUnitName				= B100_FrameParts.JLabelSet(	 				170,150, 60,20,""				,10,0);
		final JCheckBox TB_EntryMode 			= B100_FrameParts.JCheckBoxSet(				100,175,100,20,"荷姿別で入力"	,10);
		
		final JFormattedTextField TB_PlQty		= B100_FrameParts.JFormattedTextFieldSet(	100,200, 70,20,"0"		,12,1,"#,###");
		final JFormattedTextField TB_CsQty		= B100_FrameParts.JFormattedTextFieldSet(	100,225, 70,20,"0"		,12,1,"#,###");
		final JFormattedTextField TB_CtQty		= B100_FrameParts.JFormattedTextFieldSet(	100,250, 70,20,"0"		,12,1,"#,###");
		final JFormattedTextField TB_BrQty		= B100_FrameParts.JFormattedTextFieldSet(	100,275, 70,20,"0"		,12,1,"#,###");
		
		final JLabel TB_PlUnitName				= B100_FrameParts.JLabelSet(					170,200, 60,20,""			,10,0);
		final JLabel TB_CsUnitName				= B100_FrameParts.JLabelSet(					170,225, 60,20,""			,10,0);
		final JLabel TB_CtUnitName				= B100_FrameParts.JLabelSet(					170,250, 60,20,""			,10,0);
		final JLabel TB_BrUnitName				= B100_FrameParts.JLabelSet(					170,275, 60,20,""			,10,0);
		
		
		final JFormattedTextField TB_PlUnitQty	= B100_FrameParts.JFormattedTextFieldSet(	230,200, 70,20,"0"			,10,1,"#,###");
		final JFormattedTextField TB_CsUnitQty	= B100_FrameParts.JFormattedTextFieldSet(	230,225, 70,20,"0"			,10,1,"#,###");
		final JFormattedTextField TB_CtUnitQty	= B100_FrameParts.JFormattedTextFieldSet(	230,250, 70,20,"0"			,10,1,"#,###");
		
		final JTextField TB_Loc					= B100_FrameParts.JTextFieldSet(				100,325,100,20,B100_DefaultVariable.DefaultArrivalLoc			,12,0);
		JButton LocSearchBtn					= B100_FrameParts.BtnSet(						220,325, 90,20,"ロケ検索"	,11);
		
		final JTextField TB_MstRecomendLoc		= B100_FrameParts.JTextFieldSet(				450,325,100,20,""		,12,0);
		final JTextField TB_StockRecomendLoc	= B100_FrameParts.JTextFieldSet(				450,350,100,20,""		,12,0);
		
		JButton MstRecomendLocSetBtn			= B100_FrameParts.BtnSet(						350,325,90,20,"←MST推奨"	,10);
		JButton StockRecomendLocSetBtn			= B100_FrameParts.BtnSet(						350,350,90,20,"←在庫推奨"	,10);
		
		
		
		TB_WhCd.setSelectedIndex(	B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.WhList[1]		,A00000_Main.ClWh ,true) );		//倉庫
		TB_ClCd.setSelectedIndex(	B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.ClList[1]		,A00000_Main.ClCd ,true) );		//荷主
		
		TB_Row.setEnabled(false);
		TB_WhCd.setEnabled(false);
		TB_ClCd.setEnabled(false);
		
		TB_PlUnitQty.setEditable(false);
		TB_CsUnitQty.setEditable(false);
		TB_CtUnitQty.setEditable(false);
		
		TB_PlQty.setEditable(false);
		TB_CsQty.setEditable(false);
		TB_CtQty.setEditable(false);
		TB_BrQty.setEditable(false);
		
		TB_MstRecomendLoc.setEditable(false);
		TB_StockRecomendLoc.setEditable(false);
		
		//消費期限進む戻るボタン押下事の挙動
		ExpdateAfterBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				B100_DateTimeControl.AfterDateSet(TB_Expdate);
			}
		});
		ExpdateBeforeBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				B100_DateTimeControl.BeforeDateSet(TB_Expdate);
			}
		});
		ExpdateMonthBeforeBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				B100_DateTimeControl.BeforeMonthSet(TB_Expdate);
			}
		});
		ExpdateTodayBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				B100_DateTimeControl.TodaySet(TB_Expdate);
			}
		});
		ExpdateMonthAfterBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				B100_DateTimeControl.AfterMonthSet(TB_Expdate);
			}
		});
		ExpdateDefaultBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				TB_Expdate.setText(B100_DefaultVariable.DefaultExpDate);
			}
		});
		//推奨ロケを格納ロケにセット
		MstRecomendLocSetBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				String SetLoc = B100_TextControl.Trim(TB_MstRecomendLoc.getText());
				TB_Loc.setText(SetLoc);
			}
		});
		StockRecomendLocSetBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				String SetLoc = B100_TextControl.Trim(TB_StockRecomendLoc.getText());
				TB_Loc.setText(SetLoc);
			}
		});
		
		main_fm.add(LB_ClCd);
		main_fm.add(LB_WhCd);
		main_fm.add(LB_SpCd);
		
		PN_Entry.add(LB_Row);
		PN_Entry.add(LB_ItemCd);
		PN_Entry.add(LB_Lot);
		PN_Entry.add(LB_Expdate);
		PN_Entry.add(LB_Qty);
		PN_Entry.add(LB_PlQty);
		PN_Entry.add(LB_CsQty);
		PN_Entry.add(LB_CtQty);
		PN_Entry.add(LB_BrQty);
		PN_Entry.add(LB_UnitQty);
		PN_Entry.add(LB_Loc);
		PN_Entry.add(LB_RecomendLoc);
		
		main_fm.add(TB_ClCd);
		main_fm.add(TB_WhCd);
		main_fm.add(TB_SpCd);
		
		PN_Entry.add(TB_Row);
		PN_Entry.add(TB_ItemCd);
		PN_Entry.add(TB_ItemName);
		PN_Entry.add(TB_Lot);
		PN_Entry.add(TB_Expdate);
		PN_Entry.add(TB_Qty);
		PN_Entry.add(TB_QtyUnitName);
		PN_Entry.add(TB_EntryMode);
		PN_Entry.add(TB_PlQty);
		PN_Entry.add(TB_CsQty);
		PN_Entry.add(TB_CtQty);
		PN_Entry.add(TB_BrQty);
		PN_Entry.add(TB_PlUnitName);
		PN_Entry.add(TB_CsUnitName);
		PN_Entry.add(TB_CtUnitName);
		PN_Entry.add(TB_BrUnitName);
		PN_Entry.add(TB_PlUnitQty);
		PN_Entry.add(TB_CsUnitQty);
		PN_Entry.add(TB_CtUnitQty);
		PN_Entry.add(TB_Loc);
		PN_Entry.add(TB_MstRecomendLoc);
		PN_Entry.add(TB_StockRecomendLoc);
		
		PN_Entry.add(ItemSearchBtn);
		PN_Entry.add(LocSearchBtn);
		PN_Entry.add(ExpdateAfterBtn);
		PN_Entry.add(ExpdateBeforeBtn);
		PN_Entry.add(ExpdateMonthBeforeBtn);
		PN_Entry.add(ExpdateTodayBtn);
		PN_Entry.add(ExpdateMonthAfterBtn);
		PN_Entry.add(ExpdateDefaultBtn);
		PN_Entry.add(MstRecomendLocSetBtn);
		PN_Entry.add(StockRecomendLocSetBtn);
		
		//商品コードによって制御されるオブジェクト
		Object[] ItemCdControlSet	= {
					 TB_WhCd
					,TB_ClCd
					,TB_SpCd
					,TB_ItemCd
					,TB_ItemName
					,TB_Lot
					,TB_Expdate
					,TB_Qty
					,TB_QtyUnitName
					,TB_EntryMode
					,TB_PlQty
					,TB_CsQty
					,TB_CtQty
					,TB_BrQty
					,TB_PlUnitName
					,TB_CsUnitName
					,TB_CtUnitName
					,TB_BrUnitName
					,TB_PlUnitQty
					,TB_CsUnitQty
					,TB_CtUnitQty
					,TB_Loc
					,TB_MstRecomendLoc
					,TB_StockRecomendLoc
					};
		
		main_fm.add(PN_Entry);
		
		final Object[] ItemSerachSet	= WT200_ItemSearchSubFm.ItemSearchSubFm(main_fm.getX()+100,main_fm.getY()+10,B100_DefaultVariable.WhList[1][TB_WhCd.getSelectedIndex()],B100_DefaultVariable.ClList[1][TB_WhCd.getSelectedIndex()],"NK");
		final Object[] LocSerachSet		= WT200_LocSearchSubFm.LocSearchSubFm(main_fm.getX()+120,main_fm.getY()+30,B100_DefaultVariable.WhList[1][TB_WhCd.getSelectedIndex()],B100_DefaultVariable.ClList[1][TB_WhCd.getSelectedIndex()],"NK");
		
		
		main_fm.setVisible(true);
		RenewFg = true;
		
		//商品検索ボタン押下時の挙動
		ItemSearchBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					((JFrame)ItemSerachSet[WT200_ItemSearchSubFm.RtJFrame]).setVisible(true);
					RenewFg = true;
				}
			}
		});
		//ロケーション検索ボタン押下時の挙動
		LocSearchBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					((JFrame)LocSerachSet[WT200_ItemSearchSubFm.RtJFrame]).setVisible(true);
					RenewFg = true;
				}
			}
		});
		
		//商品検索サブ画面登録ボタン押下時の挙動
		((JButton)ItemSerachSet[WT200_ItemSearchSubFm.EntryBtn]).addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					int RowCount = ((DefaultTableModel)ItemSerachSet[WT200_ItemSearchSubFm.RtDefaultTableModel]).getRowCount();
					String SetItemCd = "";
					boolean KickFg = false;
					for(int i=0;i<RowCount;i++) {
						if((boolean)((DefaultTableModel)ItemSerachSet[WT200_ItemSearchSubFm.RtDefaultTableModel]).getValueAt(i, 0)) {
							SetItemCd = ""+((DefaultTableModel)ItemSerachSet[WT200_ItemSearchSubFm.RtDefaultTableModel]).getValueAt(i,1+M100_ItemMstRt.ColItemCd);
							KickFg = true;
							i=RowCount+1;
						}
					}
					if(KickFg) {
						TB_ItemCd.setText(SetItemCd);
						((JFrame)ItemSerachSet[WT200_ItemSearchSubFm.RtJFrame]).setVisible(false);
						ItemSelectControl(ItemCdControlSet);
					}
					RenewFg = true;
				}
			}
		});
		
		//ロケーション検索サブ画面登録ボタン押下時の挙動
		((JButton)LocSerachSet[WT200_LocSearchSubFm.EntryBtn]).addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					int RowCount = ((DefaultTableModel)LocSerachSet[WT200_LocSearchSubFm.RtDefaultTableModel]).getRowCount();
					String SetLoc = "";
					boolean KickFg = false;
					for(int i=0;i<RowCount;i++) {
						if((boolean)((DefaultTableModel)LocSerachSet[WT200_LocSearchSubFm.RtDefaultTableModel]).getValueAt(i, 0)) {
							SetLoc = ""+((DefaultTableModel)LocSerachSet[WT200_LocSearchSubFm.RtDefaultTableModel]).getValueAt(i,1+M100_LocationMstRt.ColLoc);
							KickFg = true;
							i=RowCount+1;
						}
					}
					if(KickFg) {
						TB_Loc.setText(SetLoc);
						((JFrame)LocSerachSet[WT200_LocSearchSubFm.RtJFrame]).setVisible(false);
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

				((JFrame)ItemSerachSet[WT200_ItemSearchSubFm.RtJFrame]).setVisible(false);
				((JFrame)ItemSerachSet[WT200_ItemSearchSubFm.RtJFrame]).dispose();
				
				((JFrame)LocSerachSet[WT200_ItemSearchSubFm.RtJFrame]).setVisible(false);
				((JFrame)LocSerachSet[WT200_ItemSearchSubFm.RtJFrame]).dispose();
				
				main_fm.setVisible(false);
				main_fm.dispose();
				A00001_WorkMain.WorkMain(0, 0);
			}
		});
	}
	
	private static void ItemSelectControl(Object[] ItemCdControlSet) {
		int ColWhCd				=  0;
		int ColClCd				=  1;
		int ColSpCd				=  2;
		int ColItemCd			=  3;
		int ColItemName			=  4;
		int ColLot				=  5;
		int ColExpdate			=  6;
		int ColQty				=  7;
		int ColQtyUnitName		=  8;
		int ColEntryMode		=  9;
		int ColPlQty			= 10;
		int ColCsQty			= 11;
		int ColCtQty			= 12;
		int ColBrQty			= 13;
		int ColPlUnitName		= 14;
		int ColCsUnitName		= 15;
		int ColCtUnitName		= 16;
		int ColBrUnitName		= 17;
		int ColPlUnitQty		= 18;
		int ColCsUnitQty		= 19;
		int ColCtUnitQty		= 20;
		int ColLoc				= 21;
		int ColMstRecomendLoc	= 22;
		int ColStockRecomendLoc	= 23;
		
		//処理前の値を格納
		String GetWhCd 				= B100_DefaultVariable.WhList[1][((JComboBox)ItemCdControlSet[ColWhCd]).getSelectedIndex()];
		String GetClCd 				= B100_DefaultVariable.ClList[1][((JComboBox)ItemCdControlSet[ColClCd]).getSelectedIndex()];
		String GetSpCd 				= B100_DefaultVariable.SupplierList[1][((JComboBox)ItemCdControlSet[ColSpCd]).getSelectedIndex()];
		
		String GetItemCd			= B100_TextControl.Trim(((JTextField)ItemCdControlSet[ColItemCd]).getText());
		String GetItemName			= B100_TextControl.Trim(((JLabel)ItemCdControlSet[ColItemName]).getText());
		String GetLot				= B100_TextControl.Trim(((JTextField)ItemCdControlSet[ColLot]).getText());
		String GetExpdate			= B100_TextControl.Trim(((JFormattedTextField)ItemCdControlSet[ColExpdate]).getText());
		int GetQty					= B100_TextControl.TextToInt(((JFormattedTextField)ItemCdControlSet[ColQty]).getText());
		String GetQtyUnitName		= B100_TextControl.Trim(((JLabel)ItemCdControlSet[ColQtyUnitName]).getText());
		boolean GetEntryMode		= ((JCheckBox)ItemCdControlSet[ColEntryMode]).isSelected();
		
		int GetPlQty				= B100_TextControl.TextToInt(((JFormattedTextField)ItemCdControlSet[ColPlQty]).getText());
		int GetCsQty				= B100_TextControl.TextToInt(((JFormattedTextField)ItemCdControlSet[ColCsQty]).getText());
		int GetCtQty				= B100_TextControl.TextToInt(((JFormattedTextField)ItemCdControlSet[ColCtQty]).getText());
		int GetBrQty				= B100_TextControl.TextToInt(((JFormattedTextField)ItemCdControlSet[ColBrQty]).getText());
		
		String GetPlUnitName		= B100_TextControl.Trim(((JLabel)ItemCdControlSet[ColPlUnitName]).getText());
		String GetCsUnitName		= B100_TextControl.Trim(((JLabel)ItemCdControlSet[ColCsUnitName]).getText());
		String GetCtUnitName		= B100_TextControl.Trim(((JLabel)ItemCdControlSet[ColCtUnitName]).getText());
		String GetBrUnitName		= B100_TextControl.Trim(((JLabel)ItemCdControlSet[ColBrUnitName]).getText());
		
		int GetPlUnitQty			= B100_TextControl.TextToInt(((JFormattedTextField)ItemCdControlSet[ColPlUnitQty]).getText());
		int GetCsUnitQty			= B100_TextControl.TextToInt(((JFormattedTextField)ItemCdControlSet[ColCsUnitQty]).getText());
		int GetCtUnitQty			= B100_TextControl.TextToInt(((JFormattedTextField)ItemCdControlSet[ColCtUnitQty]).getText());
		
		String GetLoc				= B100_TextControl.Trim(((JTextField)ItemCdControlSet[ColLoc]).getText());
		
		String GetMstRecomendLoc	= B100_TextControl.Trim(((JTextField)ItemCdControlSet[ColMstRecomendLoc]).getText());
		String GetStockRecomendLoc	= B100_TextControl.Trim(((JTextField)ItemCdControlSet[ColStockRecomendLoc]).getText());
		
		//商品マスタ情報取得
		if("".equals(GetItemCd)) {
			ItemMstRt(GetClCd,GetItemCd);
		}
		
		
	}
	
	private static Object[][] ItemMstRt(String ClCd,String ItemCd){
		if(null==ClCd) {ClCd="";}
		if(null==ItemCd) {ItemCd="";}
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
		boolean AllSearch = false;
		
		if(!"".equals(ClCd)) {
			SearchClCd.add(ClCd);
		}
		if(!"".equals(ItemCd)) {
			SearchItemCd.add(ItemCd);
		}
		
		
		Object[][] ItemMstRt = M100_ItemMstRt.ItemMstRt(
				SearchClCd,				//荷主コード
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