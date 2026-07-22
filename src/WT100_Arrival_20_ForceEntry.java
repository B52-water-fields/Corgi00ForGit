import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


public class WT100_Arrival_20_ForceEntry{
	//強制入庫（入荷予定なしで入荷実績登録する）※登録時に入荷予定も”1:入荷済み”ステータスで生成する
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	
	static final int ColTbleFg				=  0;
	static final int ColTbleMsNo			=  1;
	static final int ColTbleItemCd		=  2;
	static final int ColTbleItemName		=  3;
	static final int ColTblelLot			=  4;
	static final int ColTbleExpDate		=  5;
	static final int ColTbleActualQty		=  6;
	static final int ColTbleLoc			=  7;
	static final int ColTbleCom01			=  8;
	static final int ColTbleCom02			=  9;
	
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
		TB_SpCd.setBackground(B100_FrameParts.SelectColer("Entry"));
		
		//入力パネル
		JPanel PN_Entry 	= B100_FrameParts.JPanelSet(	10,150,600,400,"White");
		JLabel PN_EntryMsg 	= B100_FrameParts.JLabelSet(	10,125,100, 20,"入力情報",11,0);
		main_fm.add(PN_EntryMsg);
		
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
		JLabel LB_RecomendLoc		= B100_FrameParts.JLabelSet(	450,275,100,20,"推奨ロケ"			,11,2);

		JLabel LB_HdCom01			= B100_FrameParts.JLabelSet(					860,575,100,20,"全体コメント01:"	,10,1);
		JLabel LB_HdCom02			= B100_FrameParts.JLabelSet(					860,600,100,20,"全体コメント02:"	,10,1);
		JLabel LB_HdCom03			= B100_FrameParts.JLabelSet(					860,625,100,20,"全体コメント03:"	,10,1);
		
		final JFormattedTextField TB_Row		= B100_FrameParts.JFormattedTextFieldSet(	100, 25, 70,20,"0"			,12,1,"#,###");
		final JTextField TB_ItemCd				= B100_FrameParts.JTextFieldSet(				100, 50,100,20,""			,12,0);
		final JLabel TB_ItemName				= B100_FrameParts.JLabelSet(	 				100, 75,300,20,""			,10,0);
		JButton ItemSearchBtn					= B100_FrameParts.BtnSet(						220, 50, 90,20,"商品検索"	,11);
		final JTextField TB_Lot					= B100_FrameParts.JTextFieldSet(				100,100,100,20,""			,12,0);
		final JFormattedTextField TB_Expdate	= B100_FrameParts.JFormattedTextFieldSet(	100,125, 70,20,B100_DefaultVariable.DefaultExpDate			,12,0,"YYYY/MM/DD");
		JButton ExpdateAfterBtn					= B100_FrameParts.BtnSet(						220,125, 40,10,"▲"	,6);
		JButton ExpdateBeforeBtn				= B100_FrameParts.BtnSet(						220,135, 40,10,"▼"	,6);
		JButton ExpdateMonthBeforeBtn			= B100_FrameParts.BtnSet(						270,125, 40,20,"<<"	,6);
		JButton ExpdateTodayBtn					= B100_FrameParts.BtnSet(						310,125, 40,20,"■"	,6);
		JButton ExpdateMonthAfterBtn			= B100_FrameParts.BtnSet(						350,125, 40,20,">>"	,6);
		JButton ExpdateDefaultBtn				= B100_FrameParts.BtnSet(						400,125, 40,20,"!"	,12);
		
		final JFormattedTextField TB_Qty		= B100_FrameParts.JFormattedTextFieldSet(	100,150, 70,20,""				,12,1,"#,###");
		final JLabel TB_QtyUnitName				= B100_FrameParts.JLabelSet(	 				170,150, 60,20,""				,10,0);
		final JCheckBox TB_EntryMode 			= B100_FrameParts.JCheckBoxSet(				100,175,100,20,"荷姿別で入力"	,10);
		
		final JFormattedTextField TB_PlQty		= B100_FrameParts.JFormattedTextFieldSet(	100,200, 70,20,"0"			,12,1,"#,###");
		final JFormattedTextField TB_CsQty		= B100_FrameParts.JFormattedTextFieldSet(	100,225, 70,20,"0"			,12,1,"#,###");
		final JFormattedTextField TB_CtQty		= B100_FrameParts.JFormattedTextFieldSet(	100,250, 70,20,"0"			,12,1,"#,###");
		final JFormattedTextField TB_BrQty		= B100_FrameParts.JFormattedTextFieldSet(	100,275, 70,20,"0"			,12,1,"#,###");
		
		final JLabel TB_PlUnitName				= B100_FrameParts.JLabelSet(					170,200, 60,20,""			,10,0);
		final JLabel TB_CsUnitName				= B100_FrameParts.JLabelSet(					170,225, 60,20,""			,10,0);
		final JLabel TB_CtUnitName				= B100_FrameParts.JLabelSet(					170,250, 60,20,""			,10,0);
		final JLabel TB_BrUnitName				= B100_FrameParts.JLabelSet(					170,275, 60,20,""			,10,0);
		
		
		final JFormattedTextField TB_PlUnitQty	= B100_FrameParts.JFormattedTextFieldSet(	230,200, 70,20,"0"			,10,1,"#,###");
		final JFormattedTextField TB_CsUnitQty	= B100_FrameParts.JFormattedTextFieldSet(	230,225, 70,20,"0"			,10,1,"#,###");
		final JFormattedTextField TB_CtUnitQty	= B100_FrameParts.JFormattedTextFieldSet(	230,250, 70,20,"0"			,10,1,"#,###");
		
		final JTextField TB_Loc					= B100_FrameParts.JTextFieldSet(				100,325,100,20,""			,12,0);
		JButton LocSearchBtn					= B100_FrameParts.BtnSet(						220,325, 90,20,"ロケ検索"	,11);
		
		final JTextField TB_DefaultArrivalLoc	= B100_FrameParts.JTextFieldSet(				450,300,100,20,B100_DefaultVariable.DefaultArrivalLoc			,12,0);
		final JTextField TB_MstRecomendLoc		= B100_FrameParts.JTextFieldSet(				450,325,100,20,""			,12,0);
		final JTextField TB_StockRecomendLoc	= B100_FrameParts.JTextFieldSet(				450,350,100,20,""			,12,0);
		
		JButton DefaultArrivalLoc				= B100_FrameParts.BtnSet(						350,300, 90,20,"←入荷ロケ"	,10);
		JButton MstRecomendLocSetBtn			= B100_FrameParts.BtnSet(						350,325, 90,20,"←MST推奨"	,10);
		JButton StockRecomendLocSetBtn			= B100_FrameParts.BtnSet(						350,350, 90,20,"←在庫優先"	,10);
		
		JButton MsEntryBtn						= B100_FrameParts.BtnSet(						100,350,100,20,"明細確定"	,11);
		
		JLabel LB_Com							= B100_FrameParts.JLabelSet(					350,175,200,20,"明細コメント"	,10,0);
		final JTextField TB_Com01				= B100_FrameParts.JTextFieldSet(				350,200,200,20,""				,12,0);
		final JTextField TB_Com02				= B100_FrameParts.JTextFieldSet(				350,225,200,20,""				,12,0);
		
		final JTextField TB_HdCom01				= B100_FrameParts.JTextFieldSet(				960,575,300,20,""				,12,0);
		final JTextField TB_HdCom02				= B100_FrameParts.JTextFieldSet(				960,600,300,20,""				,12,0);
		final JTextField TB_HdCom03				= B100_FrameParts.JTextFieldSet(				960,625,300,20,""				,12,0);
		
		TB_ItemCd.setBackground(B100_FrameParts.SelectColer("Entry"));
		TB_Lot.setBackground(B100_FrameParts.SelectColer("Entry"));
		TB_Expdate.setBackground(B100_FrameParts.SelectColer("Entry"));
		TB_Qty.setBackground(B100_FrameParts.SelectColer("Entry"));
		TB_Loc.setBackground(B100_FrameParts.SelectColer("Entry"));
		TB_Com01.setBackground(B100_FrameParts.SelectColer("Entry"));
		TB_Com02.setBackground(B100_FrameParts.SelectColer("Entry"));
		TB_HdCom01.setBackground(B100_FrameParts.SelectColer("Entry"));
		TB_HdCom02.setBackground(B100_FrameParts.SelectColer("Entry"));
		TB_HdCom03.setBackground(B100_FrameParts.SelectColer("Entry"));
		
		
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
		
		TB_DefaultArrivalLoc.setEditable(false);
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
		DefaultArrivalLoc.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				String SetLoc = B100_TextControl.Trim(TB_DefaultArrivalLoc.getText());
				TB_Loc.setText(SetLoc);
			}
		});
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
		main_fm.add(LB_HdCom01);
		main_fm.add(LB_HdCom02);
		main_fm.add(LB_HdCom03);
		
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
		PN_Entry.add(LB_Com);
		
		main_fm.add(TB_ClCd);
		main_fm.add(TB_WhCd);
		main_fm.add(TB_SpCd);
		main_fm.add(TB_HdCom01);
		main_fm.add(TB_HdCom02);
		main_fm.add(TB_HdCom03);
		
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
		PN_Entry.add(TB_DefaultArrivalLoc);
		PN_Entry.add(TB_MstRecomendLoc);
		PN_Entry.add(TB_StockRecomendLoc);
		PN_Entry.add(TB_Com01);
		PN_Entry.add(TB_Com02);
		
		
		PN_Entry.add(ItemSearchBtn);
		PN_Entry.add(LocSearchBtn);
		PN_Entry.add(ExpdateAfterBtn);
		PN_Entry.add(ExpdateBeforeBtn);
		PN_Entry.add(ExpdateMonthBeforeBtn);
		PN_Entry.add(ExpdateTodayBtn);
		PN_Entry.add(ExpdateMonthAfterBtn);
		PN_Entry.add(ExpdateDefaultBtn);
		PN_Entry.add(DefaultArrivalLoc);
		PN_Entry.add(MstRecomendLocSetBtn);
		PN_Entry.add(StockRecomendLocSetBtn);
		PN_Entry.add(MsEntryBtn);
		
		main_fm.add(PN_Entry);
		
		final Object[] ItemSerachSet	= WT200_ItemSearchSubFm.ItemSearchSubFm(main_fm.getX()+100,main_fm.getY()+10,B100_DefaultVariable.WhList[1][TB_WhCd.getSelectedIndex()],B100_DefaultVariable.ClList[1][TB_ClCd.getSelectedIndex()],"NK");
		final Object[] LocSerachSet		= WT200_LocSearchSubFm.LocSearchSubFm(main_fm.getX()+120,main_fm.getY()+30,B100_DefaultVariable.WhList[1][TB_WhCd.getSelectedIndex()],B100_DefaultVariable.ClList[1][TB_ClCd.getSelectedIndex()],"NK");
		
		String[] Title = B100_RtObjectCreate.RtTitleName(T100_ArrivalMsRt.RtArrivalMsRt());
		final Object[][] NeedCol = {
				 {Title[T100_ArrivalMsRt.ColMsNo]			, 0}
				,{Title[T100_ArrivalMsRt.ColItemCd]		, 1}
				,{Title[T100_ArrivalMsRt.ColItemName]		, 1}
				,{Title[T100_ArrivalMsRt.ColLot]			, 1}
				,{Title[T100_ArrivalMsRt.ColExpDate]		, 2}
				,{Title[T100_ArrivalMsRt.ColActualQty]	, 0}
				,{"格納ロケ"								, 1}
				,{Title[T100_ArrivalMsRt.ColCom01]			, 1}
				,{Title[T100_ArrivalMsRt.ColCom02]			, 1}
				};//フィールド名,フィールドタイプ(0:数値 1:文字列 2:日付時刻)
		
		
		String[] columnNames01 = new String[NeedCol.length+1];
		columnNames01[0]	= "Fg";
		for(int i=0;i<NeedCol.length;i++) {
			columnNames01[i+1]	= (String)NeedCol[i][0];
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
		column = columnModel01.getColumn( 0);	column.setPreferredWidth( 30*A00000_Main.Mul/A00000_Main.Div);
		for(int i=1;i<NeedCol.length;i++) {
			if(0==(int)NeedCol[i][1]) {
				column = columnModel01.getColumn(i+1);	column.setPreferredWidth(70*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.rightCellRenderer());	
			}else {
				column = columnModel01.getColumn(i+1);	column.setPreferredWidth(80*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.leftCellRenderer());	
			}
		}
		
		JScrollPane scpn01 = B100_FrameParts.JScrollPaneSet(	620,150,640,400,tb01);
		main_fm.add(scpn01);
		JLabel scpn01Msg 	= B100_FrameParts.JLabelSet(		620,125,100, 20,"登録情報",11,0);
		main_fm.add(scpn01Msg);
		
		main_fm.setVisible(true);
		RenewFg = true;
		
		//商品コードによって制御されるオブジェクト
		final Object[] ItemCdControlSet	= {
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
		//数量入力によって制御されるオブジェクト
		final Object[] QtyControlSet	= {
					 TB_PlUnitQty
					,TB_CsUnitQty
					,TB_CtUnitQty
					,TB_Qty
					,TB_PlQty
					,TB_CsQty
					,TB_CtQty
					,TB_BrQty
					};
		//登録時に制御されるオブジェクト
		final Object[] EntrySet			= {
					 TB_WhCd
					,TB_ClCd
					,TB_SpCd
					,TB_HdCom01
					,TB_HdCom02
					,TB_HdCom03
					,tb01
					};
		
		
		//登録ボタン押下時の挙動
		entry_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					//入力画面商品CDが空白”でない”場合入力中を疑う
					String GetItemCd =  B100_TextControl.Trim(TB_ItemCd.getText());
					if(!"".equals(GetItemCd)) {
						JOptionPane.showMessageDialog(null, "商品CDに入力中の痕跡があります、確認したまえ");
					}else {
						boolean ErrMsgFg	= DataEntry(EntrySet);
						if(ErrMsgFg) {
							
						}else {
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
					}
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
					boolean CheckOn = false;
					for(int i=0;i<row_count;i++){
						if(i!=e.getFirstRow()){
							MainFmTableModel.setValueAt(setBL, i, 0);
						}else {
							if((boolean)MainFmTableModel.getValueAt(i, 0)) {
								CheckOn = true;
								String GetTbleMsNo		= ""+MainFmTableModel.getValueAt(i,ColTbleMsNo);
								String GetTbleItemCd	= ""+MainFmTableModel.getValueAt(i,ColTbleItemCd);
								String GetTbleItemName	= ""+MainFmTableModel.getValueAt(i,ColTbleItemName);
								String GetTblelLot		= ""+MainFmTableModel.getValueAt(i,ColTblelLot);
								String GetTbleExpDate	= ""+MainFmTableModel.getValueAt(i,ColTbleExpDate);
								String GetTbleActualQty	= ""+MainFmTableModel.getValueAt(i,ColTbleActualQty);
								String GetTbleLoc		= ""+MainFmTableModel.getValueAt(i,ColTbleLoc);
								String GetTbleCom01		= ""+MainFmTableModel.getValueAt(i,ColTbleCom01);
								String GetTbleCom02		= ""+MainFmTableModel.getValueAt(i,ColTbleCom02);
								
								TB_Row.setText(GetTbleMsNo);
								TB_ItemCd.setText(GetTbleItemCd);
								TB_Lot.setText(GetTblelLot);
								TB_Expdate.setText(GetTbleExpDate);
								TB_Qty.setText(GetTbleActualQty);
								TB_Loc.setText(GetTbleLoc);
								TB_Com01.setText(GetTbleCom01);
								TB_Com02.setText(GetTbleCom02);
							}
						}
					}
					if(!CheckOn) {
						TB_Row.setText("0");
						TB_ItemCd.setText("");
						TB_Lot.setText("");
						TB_Expdate.setText(B100_DefaultVariable.DefaultExpDate);
						TB_Qty.setText("0");
						TB_Com01.setText("");
						TB_Com02.setText("");
						TB_Loc.setText("");
					}
					ItemSelectControl(ItemCdControlSet);
					
					RenewFg = true;
				}
			}
		});
		//明細確定ボタン押下時の挙動
		MsEntryBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					int TgtRow = -1;
					int MaxMsNo = 0;
					boolean UnHitFg = true;
					boolean KickFg 	= true;
					int GetRow			= B100_TextControl.TextToInt(TB_Row.getText());
					String GetItemCd	= B100_TextControl.Trim(TB_ItemCd.getText());
					String GetItemName	= B100_TextControl.Trim(TB_ItemName.getText());
					String GetLot		= B100_TextControl.Trim(TB_Lot.getText());
					String GetExpdate	= B100_TextControl.TextToDate(TB_Expdate.getText());
					int GetQty			= B100_TextControl.TextToInt(TB_Qty.getText());
					String GetLoc		= B100_TextControl.Trim(TB_Loc.getText());
					String GetCom01		= B100_TextControl.Trim(TB_Com01.getText());
					String GetCom02		= B100_TextControl.Trim(TB_Com02.getText());
					
					String GetClCd		= B100_DefaultVariable.ClList[1][TB_ClCd.getSelectedIndex()];
					
					if("".equals(GetExpdate	)) {GetExpdate	= B100_DefaultVariable.DefaultExpDate;}
					Object[][] LocationMstRt	= LocationMstRt(GetClCd,GetLoc);
					boolean LocUnHitFg = true;
					if(1==LocationMstRt.length) {LocUnHitFg=false;}
					
					if(LocUnHitFg) {
						int option = JOptionPane.showConfirmDialog(null, "ロケーションの特定ができません\n入荷時ロケで設定します？","登録確認", JOptionPane.YES_NO_OPTION,
							      JOptionPane.WARNING_MESSAGE);
						if (option == JOptionPane.YES_OPTION){
							GetLoc	= B100_DefaultVariable.DefaultArrivalLoc;
							TB_Loc.setText(B100_DefaultVariable.DefaultArrivalLoc);
						}else {
							KickFg = false;
						}
					}
					int row_count = MainFmTableModel.getRowCount();
					for(int i=0;i<row_count;i++){
						int GetTbleMsNo		= B100_TextControl.TextToInt(""+MainFmTableModel.getValueAt(i,ColTbleMsNo));
						if(MaxMsNo<GetTbleMsNo) {MaxMsNo=GetTbleMsNo;}
						if(GetRow==GetTbleMsNo) {TgtRow=i;	UnHitFg=false;}
						MainFmTableModel.setValueAt(false, i, 0);
						
					}
					
					if(KickFg&&!"".equals(GetItemCd)&&0<=GetQty) {
						KickFg = true;
						if(UnHitFg) {
							Object[] SetOb= new Object[1+NeedCol.length];
							MaxMsNo=MaxMsNo+1;
							SetOb[ColTbleFg]			= false;
							SetOb[ColTbleMsNo]			= MaxMsNo;
							SetOb[ColTbleItemCd]		= GetItemCd;
							SetOb[ColTbleItemName]	= GetItemName;
							SetOb[ColTblelLot]			= GetLot;
							SetOb[ColTbleExpDate]		= GetExpdate;
							SetOb[ColTbleActualQty]	= ""+GetQty;
							SetOb[ColTbleLoc]			= GetLoc;
							SetOb[ColTbleCom01]		= GetCom01;
							SetOb[ColTbleCom02]		= GetCom02;
							MainFmTableModel.addRow(SetOb);
						}else {
							MainFmTableModel.setValueAt(""+GetRow		, TgtRow, ColTbleMsNo);
							MainFmTableModel.setValueAt(GetItemCd		, TgtRow, ColTbleItemCd);
							MainFmTableModel.setValueAt(GetItemName		, TgtRow, ColTbleItemName);
							MainFmTableModel.setValueAt(GetLot			, TgtRow, ColTblelLot);
							MainFmTableModel.setValueAt(GetExpdate		, TgtRow, ColTbleExpDate);
							MainFmTableModel.setValueAt(""+GetQty		, TgtRow, ColTbleActualQty);
							MainFmTableModel.setValueAt(GetLoc			, TgtRow, ColTbleLoc);
							MainFmTableModel.setValueAt(GetCom01		, TgtRow, ColTbleCom01);
							MainFmTableModel.setValueAt(GetCom02		, TgtRow, ColTbleCom02);
						}
					}else {
						KickFg = false;
					}
					if(KickFg) {
						TB_Row.setText("0");
						TB_ItemCd.setText("");
						TB_Lot.setText("");
						TB_Expdate.setText(B100_DefaultVariable.DefaultExpDate);
						TB_Qty.setText("0");
						TB_Com01.setText("");
						TB_Com02.setText("");
						TB_Loc.setText("");
						ItemSelectControl(ItemCdControlSet);
					}else {
						JOptionPane.showMessageDialog(null, "商品CDと数量、格納ロケは必須だよ");
					}
					
					RenewFg = true;
				}
			}
		});
		
		//数量フォーカス消失時の挙動
		TB_Qty.addFocusListener(new FocusAdapter(){
			@Override
			public void focusLost(FocusEvent e){
				if(RenewFg) {
					RenewFg = false;
					QtyControl(QtyControlSet);
					RenewFg = true;
				}
			}
		});
		//パレット数量フォーカス消失時の挙動
		TB_PlQty.addFocusListener(new FocusAdapter(){
			@Override
			public void focusLost(FocusEvent e){
				if(RenewFg) {
					RenewFg = false;
					UnitQtyControl(QtyControlSet);
					QtyControl(QtyControlSet);
					RenewFg = true;
				}
			}
		});
		//ケース数量フォーカス消失時の挙動
		TB_CsQty.addFocusListener(new FocusAdapter(){
			@Override
			public void focusLost(FocusEvent e){
				if(RenewFg) {
					RenewFg = false;
					UnitQtyControl(QtyControlSet);
					QtyControl(QtyControlSet);
					RenewFg = true;
				}
			}
		});
		//カートン数量フォーカス消失時の挙動
		TB_CtQty.addFocusListener(new FocusAdapter(){
			@Override
			public void focusLost(FocusEvent e){
				if(RenewFg) {
					RenewFg = false;
					UnitQtyControl(QtyControlSet);
					QtyControl(QtyControlSet);
					RenewFg = true;
				}
			}
		});
		//バラ数量フォーカス消失時の挙動
		TB_BrQty.addFocusListener(new FocusAdapter(){
			@Override
			public void focusLost(FocusEvent e){
				if(RenewFg) {
					RenewFg = false;
					UnitQtyControl(QtyControlSet);
					QtyControl(QtyControlSet);
					RenewFg = true;
				}
			}
		});
		
		//登録モード操作時の挙動
		TB_EntryMode.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					ItemSelectControl(ItemCdControlSet);
					RenewFg = true;
				}
			}
		});
		
		
		//商品CDフォーカス消失時の挙動
		TB_ItemCd.addFocusListener(new FocusAdapter(){
			@Override
			public void focusLost(FocusEvent e){
				if(RenewFg) {
					RenewFg = false;
					ItemSelectControl(ItemCdControlSet);
					RenewFg = true;
				}
			}
		});
		
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
	
	private static boolean DataEntry(Object[] EntrySet) {
		int ColWhCd		= 0;
		int ColClCd		= 1;
		int ColSpCd		= 2;
		int ColHdCom01	= 3;
		int ColHdCom02	= 4;
		int ColHdCom03	= 5;
		int Coltb01		= 6;
		boolean ErrMsgFg = false;
		ArrayList<String> ErrMsg = new ArrayList<String>();
		//値取得
		String GetWhCd	= B100_DefaultVariable.WhList[1][((JComboBox)EntrySet[ColWhCd]).getSelectedIndex()];
		String GetClCd	= B100_DefaultVariable.ClList[1][((JComboBox)EntrySet[ColClCd]).getSelectedIndex()];
		String GetSpCd	= B100_DefaultVariable.SupplierList[1][((JComboBox)EntrySet[ColSpCd]).getSelectedIndex()];
		
		String GetCom01	= ((JTextField)EntrySet[ColHdCom01]).getText();
		String GetCom02	= ((JTextField)EntrySet[ColHdCom02]).getText();
		String GetCom03	= ((JTextField)EntrySet[ColHdCom03]).getText();
		
		String[][] GetMsData		= B100_TableControl.TableDataRt((JTable)EntrySet[Coltb01]);
		
		//明細から在庫数増やすためのデータ
		Object[][] StockQtyControlSetData = new Object[GetMsData.length][Tools100_StockQtyControl.StockQtyControlDataLayout().length];
		
		//入荷予定番号1件採番
		int[] ArrivalPlanNoGet	= Tools100_ArrivalPlanNoGet.ArrivalPlanNoRt(1);
		
		//仕入れ先マスタ情報取得
		Object[][] M100_SupplierRt	= M100_SupplierRt(GetWhCd,GetClCd,GetSpCd);
		
		//商品マスタ情報取得
		//移動データ生成必要（入荷時ロケ以外に格納している）行数取得※ついでに入荷時ロケで設定
		ArrayList<String> SearchItemCd	= new ArrayList<String>();
		
		//入荷時ロケ以外がロケーションになっていれば入荷時ロケ⇒格納ロケへの移動データが必要
		int MoveCount	= 0;
		
		for(int i=0;i<GetMsData.length;i++) {
			String GetFg		= B100_TextControl.Trim(""+GetMsData[i][ColTbleFg]);
			String GetMsNo		= B100_TextControl.Trim(""+GetMsData[i][ColTbleMsNo]);
			String GetItemCd	= B100_TextControl.Trim(""+GetMsData[i][ColTbleItemCd]);
			String GetItemName	= B100_TextControl.Trim(""+GetMsData[i][ColTbleItemName]);
			String GetLot		= B100_TextControl.Trim(""+GetMsData[i][ColTblelLot]);
			String GetExpDate	= B100_TextControl.Trim(""+GetMsData[i][ColTbleExpDate]);
			int GetActualQty	= B100_TextControl.TextToInt(""+GetMsData[i][ColTbleActualQty]);
			String GetLoc		= B100_TextControl.Trim(""+GetMsData[i][ColTbleLoc]);
			String GetMsCom01	= B100_TextControl.Trim(""+GetMsData[i][ColTbleCom01]);
			String GetMsCom02	= B100_TextControl.Trim(""+GetMsData[i][ColTbleCom02]);
			
			if("".equals(GetExpDate	)) {GetExpDate	= B100_DefaultVariable.DefaultExpDate;}
			if("".equals(GetLoc		)) {GetLoc		= B100_DefaultVariable.DefaultArrivalLoc	;}
			
			
			if(!B100_DefaultVariable.DefaultArrivalLoc.equals(GetLoc)) {
				MoveCount	= MoveCount+1;
			}
			
			SearchItemCd.add(GetItemCd);
			
		}
		
		//実格納ロケに在庫移動するためのデータ
		Object[][] MoveData = new Object[MoveCount][Tools100_StockMoveControl.StockMoveControlLayout().length];
		MoveCount	= 0;
		
		//商品マスタ情報取得
		Object[][] ItemMstRt	= ItemMstRtArray(GetClCd,SearchItemCd);
		
		
		//登録データ設定
		String now_date = B100_DateTimeControl.dtmString2(B100_DateTimeControl.dtm()[1])[0];
		String now_dtm 	= B100_DateTimeControl.dtmString2(B100_DateTimeControl.dtm()[1])[1];
		
		String ArrivalPlanHd_tgt_table 	= "WW0010ArrivalPlanHd";
		String ArrivalHd_tgt_table 		= "WW0012ArrivalHd";
		String ArrivalPlanMs_tgt_table	= "WW0011ArrivalPlanMs";
		String ArrivalMs_tgt_table 		= "WW0013ArrivalMs";
		
		
		String TgtDB = "WANKO";
		int non_msg_fg = 1;
		
		String[] ArrivalPlanHd_ClWh			= new String[1];	//担当倉庫
 		String[] ArrivalPlanHd_ClCd			= new String[1];	//荷主CD
		String[] ArrivalPlanHd_ArrNo		= new String[1];	//入荷予定NO
		String[] ArrivalPlanHd_ClArrNo		= new String[1];	//荷主予定番号
		String[] ArrivalPlanHd_PlanDate		= new String[1];	//入荷予定日
		String[] ArrivalPlanHd_ActualDate	= new String[1];	//入荷実績日
		String[] ArrivalPlanHd_SpCd			= new String[1];	//仕入先CD
		String[] ArrivalPlanHd_SpName01		= new String[1];	//仕入先名01
		String[] ArrivalPlanHd_SpName02		= new String[1];	//仕入先名02
		String[] ArrivalPlanHd_SpName03		= new String[1];	//仕入先名03
		String[] ArrivalPlanHd_SpPost		= new String[1];	//仕入先郵便
		String[] ArrivalPlanHd_SpAdd01		= new String[1];	//仕入先住所01
		String[] ArrivalPlanHd_SpAdd02		= new String[1];	//仕入先住所02
		String[] ArrivalPlanHd_SpAdd03		= new String[1];	//仕入先住所03
		String[] ArrivalPlanHd_SpTel		= new String[1];	//仕入先電話
		String[] ArrivalPlanHd_ArCom01		= new String[1];	//コメント1
		String[] ArrivalPlanHd_ArCom02		= new String[1];	//コメント2
		String[] ArrivalPlanHd_ArCom03		= new String[1];	//コメント3
		String[] ArrivalPlanHd_EntryDate	= new String[1];	//登録日
		String[] ArrivalPlanHd_UpdateDate	= new String[1];	//更新日
		String[] ArrivalPlanHd_EntryUser	= new String[1];	//登録者
		String[] ArrivalPlanHd_UpdateUser	= new String[1];	//更新者
		String[] ArrivalPlanHd_FixFg		= new String[1];	//ステータス
		
		String[] ArrivalHd_ClWh				= new String[1];	//担当倉庫
		String[] ArrivalHd_ClCd				= new String[1];	//荷主CD
		String[] ArrivalHd_ArrNo			= new String[1];	//入荷予定NO
		String[] ArrivalHd_ArrCount			= new String[1];	//入荷予定枝番
		String[] ArrivalHd_ClArrNo			= new String[1];	//荷主予定番号
 		String[] ArrivalHd_PlanDate			= new String[1];	//入荷予定日
		String[] ArrivalHd_ActualDate		= new String[1];	//入荷実績日
		String[] ArrivalHd_SpCd				= new String[1];	//仕入先CD
		String[] ArrivalHd_SpName01			= new String[1];	//仕入先名01
		String[] ArrivalHd_SpName02			= new String[1];	//仕入先名02
		String[] ArrivalHd_SpName03			= new String[1];	//仕入先名03
		String[] ArrivalHd_SpPost			= new String[1];	//仕入先郵便
		String[] ArrivalHd_SpAdd01			= new String[1];	//仕入先住所01
		String[] ArrivalHd_SpAdd02			= new String[1];	//仕入先住所02
		String[] ArrivalHd_SpAdd03			= new String[1];	//仕入先住所03
		String[] ArrivalHd_SpTel			= new String[1];	//仕入先電話
		String[] ArrivalHd_ArCom01			= new String[1];	//コメント1
		String[] ArrivalHd_ArCom02			= new String[1];	//コメント2
		String[] ArrivalHd_ArCom03			= new String[1];	//コメント3
		String[] ArrivalHd_EntryDate		= new String[1];	//登録日
		String[] ArrivalHd_UpdateDate		= new String[1];	//更新日
		String[] ArrivalHd_EntryUser		= new String[1];	//登録者
		String[] ArrivalHd_UpdateUser		= new String[1];	//更新者
		
		
		String[] ArrivalPlanMs_ClWh			= new String[GetMsData.length];	//担当倉庫
		String[] ArrivalPlanMs_ClCd			= new String[GetMsData.length];	//荷主CD
		String[] ArrivalPlanMs_ArrNo		= new String[GetMsData.length];	//入荷予定NO
		String[] ArrivalPlanMs_MsNo			= new String[GetMsData.length];	//明細番号
		String[] ArrivalPlanMs_ItemCd		= new String[GetMsData.length];	//商品コード
		String[] ArrivalPlanMs_ClItemCd		= new String[GetMsData.length];	//荷主商品コード
		String[] ArrivalPlanMs_JanCd		= new String[GetMsData.length];	//JanCd（バラ）
		String[] ArrivalPlanMs_ItemMdNo		= new String[GetMsData.length];	//商品型番
		String[] ArrivalPlanMs_ItemName		= new String[GetMsData.length];	//商品名
		String[] ArrivalPlanMs_lot			= new String[GetMsData.length];	//ロット
		String[] ArrivalPlanMs_ExpDate		= new String[GetMsData.length];	//消費期限
		String[] ArrivalPlanMs_PlanQty		= new String[GetMsData.length];	//予定数量
 		String[] ArrivalPlanMs_ActualQty	= new String[GetMsData.length];	//実績数
		String[] ArrivalPlanMs_ActualDate	= new String[GetMsData.length];	//入荷日
		String[] ArrivalPlanMs_Com01		= new String[GetMsData.length];	//コメント1
		String[] ArrivalPlanMs_Com02		= new String[GetMsData.length];	//コメント2
		String[] ArrivalPlanMs_EntryDate	= new String[GetMsData.length];	//登録日
		String[] ArrivalPlanMs_UpdateDate	= new String[GetMsData.length];	//更新日
		String[] ArrivalPlanMs_EntryUser	= new String[GetMsData.length];	//登録者
		String[] ArrivalPlanMs_UpdateUser	= new String[GetMsData.length];	//更新者
		
		String[] ArrivalMs_ClWh				= new String[GetMsData.length];	//担当倉庫
		String[] ArrivalMs_ClCd				= new String[GetMsData.length];	//荷主CD
		String[] ArrivalMs_ArrNo			= new String[GetMsData.length];	//入荷予定NO
		String[] ArrivalMs_ArrCount			= new String[GetMsData.length];	//入荷予定枝番
		String[] ArrivalMs_MsNo				= new String[GetMsData.length];	//明細番号
		String[] ArrivalMs_MsSeq			= new String[GetMsData.length];	//明細Seq番号
		String[] ArrivalMs_ItemCd			= new String[GetMsData.length];	//商品コード
		String[] ArrivalMs_ClItemCd			= new String[GetMsData.length];	//荷主商品コード
		String[] ArrivalMs_JanCd			= new String[GetMsData.length];	//JanCd(バラ)
		String[] ArrivalMs_ItemMdNo			= new String[GetMsData.length];	//商品型番
		String[] ArrivalMs_ItemName			= new String[GetMsData.length];	//商品名
		String[] ArrivalMs_Lot				= new String[GetMsData.length];	//ロット
		String[] ArrivalMs_ExpDate			= new String[GetMsData.length];	//消費期限
		String[] ArrivalMs_PlanQty			= new String[GetMsData.length];	//予定数量
		String[] ArrivalMs_ActualQty		= new String[GetMsData.length];	//実績数
		String[] ArrivalMs_ActualDate		= new String[GetMsData.length];	//入荷日
		String[] ArrivalMs_Com01			= new String[GetMsData.length];	//コメント1
		String[] ArrivalMs_Com02			= new String[GetMsData.length];	//コメント2
		String[] ArrivalMs_EntryDate		= new String[GetMsData.length];	//登録日
		String[] ArrivalMs_UpdateDate		= new String[GetMsData.length];	//更新日
		String[] ArrivalMs_EntryUser		= new String[GetMsData.length];	//登録者
		String[] ArrivalMs_UpdateUser		= new String[GetMsData.length];	//更新者
		
		//ヘッダ項目値格納
		ArrivalPlanHd_ClWh[0]			= GetWhCd;					//担当倉庫
 		ArrivalPlanHd_ClCd[0]			= GetClCd;					//荷主CD
		ArrivalPlanHd_ArrNo[0]			= ""+ArrivalPlanNoGet[0];	//入荷予定NO
		ArrivalPlanHd_ClArrNo[0]		= "";						//荷主予定番号
		ArrivalPlanHd_PlanDate[0]		= now_date;					//入荷予定日
		ArrivalPlanHd_ActualDate[0]		= now_dtm;					//入荷実績日
		ArrivalPlanHd_SpCd[0]			= GetSpCd;					//仕入先CD
		ArrivalPlanHd_SpName01[0]		= "";		//仕入先名01
		ArrivalPlanHd_SpName02[0]		= "";		//仕入先名02
		ArrivalPlanHd_SpName03[0]		= "";		//仕入先名03
		ArrivalPlanHd_SpPost[0]			= "";		//仕入先郵便
		ArrivalPlanHd_SpAdd01[0]		= "";		//仕入先住所01
		ArrivalPlanHd_SpAdd02[0]		= "";		//仕入先住所02
		ArrivalPlanHd_SpAdd03[0]		= "";		//仕入先住所03
		ArrivalPlanHd_SpTel[0]			= "";		//仕入先電話
		ArrivalPlanHd_ArCom01[0]		= GetCom01;	//コメント1
		ArrivalPlanHd_ArCom02[0]		= GetCom02;	//コメント2
		ArrivalPlanHd_ArCom03[0]		= GetCom03;	//コメント3
		ArrivalPlanHd_EntryDate[0]		= now_dtm;	//登録日
		ArrivalPlanHd_UpdateDate[0]		= now_dtm;	//更新日
		ArrivalPlanHd_EntryUser[0]		= "(" + A00000_Main.LoginUserId + ")" + A00000_Main.LoginUserName;	//登録者
		ArrivalPlanHd_UpdateUser[0]		= "(" + A00000_Main.LoginUserId + ")" + A00000_Main.LoginUserName;	//更新者
		ArrivalPlanHd_FixFg[0]			= "1";	//ステータス
		
		ArrivalHd_ClWh[0]				= GetWhCd;					//担当倉庫
		ArrivalHd_ClCd[0]				= GetClCd;					//荷主CD
		ArrivalHd_ArrNo[0]				= ""+ArrivalPlanNoGet[0];	//入荷予定NO
		ArrivalHd_ArrCount[0]			= "0";						//入荷予定枝番
		ArrivalHd_ClArrNo[0]			= "";						//荷主予定番号
 		ArrivalHd_PlanDate[0]			= now_date;					//入荷予定日
		ArrivalHd_ActualDate[0]			= now_dtm;					//入荷実績日
		ArrivalHd_SpCd[0]				= GetSpCd;					//仕入先CD
		ArrivalHd_SpName01[0]			= "";		//仕入先名01
		ArrivalHd_SpName02[0]			= "";		//仕入先名02
		ArrivalHd_SpName03[0]			= "";		//仕入先名03
		ArrivalHd_SpPost[0]				= "";		//仕入先郵便
		ArrivalHd_SpAdd01[0]			= "";		//仕入先住所01
		ArrivalHd_SpAdd02[0]			= "";		//仕入先住所02
		ArrivalHd_SpAdd03[0]			= "";		//仕入先住所03
		ArrivalHd_SpTel[0]				= "";		//仕入先電話
		ArrivalHd_ArCom01[0]			= GetCom01;	//コメント1
		ArrivalHd_ArCom02[0]			= GetCom02;	//コメント2
		ArrivalHd_ArCom03[0]			= GetCom03;	//コメント3
		ArrivalHd_EntryDate[0]			= now_dtm;	//登録日
		ArrivalHd_UpdateDate[0]			= now_dtm;	//更新日
		ArrivalHd_EntryUser[0]			= "(" + A00000_Main.LoginUserId + ")" + A00000_Main.LoginUserName;	//登録者
		ArrivalHd_UpdateUser[0]			= "(" + A00000_Main.LoginUserId + ")" + A00000_Main.LoginUserName;	//更新者
		
		if(1==M100_SupplierRt.length) {
			ArrivalPlanHd_SpName01[0]		= ""+M100_SupplierRt[0][M100_ShippingCompanyMstRt.ColShippingCompanyName01];		//仕入先名01
			ArrivalPlanHd_SpName02[0]		= ""+M100_SupplierRt[0][M100_ShippingCompanyMstRt.ColShippingCompanyName02];		//仕入先名02
			ArrivalPlanHd_SpName03[0]		= ""+M100_SupplierRt[0][M100_ShippingCompanyMstRt.ColShippingCompanyName03];		//仕入先名03
			ArrivalPlanHd_SpPost[0]			= ""+M100_SupplierRt[0][M100_ShippingCompanyMstRt.ColPost];		//仕入先郵便
			ArrivalPlanHd_SpAdd01[0]		= ""+M100_SupplierRt[0][M100_ShippingCompanyMstRt.ColAdd01];		//仕入先住所01
			ArrivalPlanHd_SpAdd02[0]		= ""+M100_SupplierRt[0][M100_ShippingCompanyMstRt.ColAdd02];		//仕入先住所02
			ArrivalPlanHd_SpAdd03[0]		= ""+M100_SupplierRt[0][M100_ShippingCompanyMstRt.ColAdd03];		//仕入先住所03
			ArrivalPlanHd_SpTel[0]			= ""+M100_SupplierRt[0][M100_ShippingCompanyMstRt.ColTel];			//仕入先電話
			
			ArrivalHd_SpName01[0]			= ""+M100_SupplierRt[0][M100_ShippingCompanyMstRt.ColShippingCompanyName01];		//仕入先名01
			ArrivalHd_SpName02[0]			= ""+M100_SupplierRt[0][M100_ShippingCompanyMstRt.ColShippingCompanyName02];		//仕入先名02
			ArrivalHd_SpName03[0]			= ""+M100_SupplierRt[0][M100_ShippingCompanyMstRt.ColShippingCompanyName03];		//仕入先名03
			ArrivalHd_SpPost[0]				= ""+M100_SupplierRt[0][M100_ShippingCompanyMstRt.ColPost];		//仕入先郵便
			ArrivalHd_SpAdd01[0]			= ""+M100_SupplierRt[0][M100_ShippingCompanyMstRt.ColAdd01];		//仕入先住所01
			ArrivalHd_SpAdd02[0]			= ""+M100_SupplierRt[0][M100_ShippingCompanyMstRt.ColAdd02];		//仕入先住所02
			ArrivalHd_SpAdd03[0]			= ""+M100_SupplierRt[0][M100_ShippingCompanyMstRt.ColAdd03];		//仕入先住所03
			ArrivalHd_SpTel[0]				= ""+M100_SupplierRt[0][M100_ShippingCompanyMstRt.ColTel];			//仕入先電話
		}else {
			ErrMsgFg = true;
			ErrMsg.add("仕入先が特定できませんでした");
		}
		
		//明細項目値格納
		for(int i=0;i<GetMsData.length;i++) {
			String GetFg		= B100_TextControl.Trim(""+GetMsData[i][ColTbleFg]);
			String GetMsNo		= B100_TextControl.Trim(""+GetMsData[i][ColTbleMsNo]);
			String GetItemCd	= B100_TextControl.Trim(""+GetMsData[i][ColTbleItemCd]);
			String GetItemName	= B100_TextControl.Trim(""+GetMsData[i][ColTbleItemName]);
			String GetLot		= B100_TextControl.Trim(""+GetMsData[i][ColTblelLot]);
			String GetExpDate	= B100_TextControl.Trim(""+GetMsData[i][ColTbleExpDate]);
			int GetActualQty	= B100_TextControl.TextToInt(""+GetMsData[i][ColTbleActualQty]);
			String GetLoc		= B100_TextControl.Trim(""+GetMsData[i][ColTbleLoc]);
			String GetMsCom01	= B100_TextControl.Trim(""+GetMsData[i][ColTbleCom01]);
			String GetMsCom02	= B100_TextControl.Trim(""+GetMsData[i][ColTbleCom02]);
			
			if("".equals(GetExpDate	)) {GetExpDate	= B100_DefaultVariable.DefaultExpDate;}
			if("".equals(GetLoc		)) {GetLoc		= B100_DefaultVariable.DefaultArrivalLoc	;}
			
			int TgtItemMstRow = B100_ArrayListControl.ObjectGetRow(ItemMstRt,GetItemCd,M100_ItemMstRt.ColItemCd,false);
			int MsNo = i+1;
			
			String StockActualDate	= now_date;
			//在庫の入荷日管理しない場合デフォルト入荷実績日をセット
			if(B100_DefaultVariable.ActualDateUnControl) {
				StockActualDate	= B100_DefaultVariable.DefaultActualDate;
			}else {
				
			}
			
			ArrivalPlanMs_ClWh[i]			= GetWhCd;					//担当倉庫
			ArrivalPlanMs_ClCd[i]			= GetClCd;					//荷主CD
			ArrivalPlanMs_ArrNo[i]			= ""+ArrivalPlanNoGet[0];	//入荷予定NO
			ArrivalPlanMs_MsNo[i]			= ""+MsNo;					//明細番号
			ArrivalPlanMs_ItemCd[i]			= GetItemCd;				//商品コード
			ArrivalPlanMs_ClItemCd[i]		= "";						//荷主商品コード
			ArrivalPlanMs_JanCd[i]			= "";						//JanCd（バラ）
			ArrivalPlanMs_ItemMdNo[i]		= "";						//商品型番
			ArrivalPlanMs_ItemName[i]		= "";						//商品名
			ArrivalPlanMs_lot[i]			= GetLot;					//ロット
			ArrivalPlanMs_ExpDate[i]		= GetExpDate;				//消費期限
			ArrivalPlanMs_PlanQty[i]		= ""+GetActualQty;			//予定数量
	 		ArrivalPlanMs_ActualQty[i]		= ""+GetActualQty;			//実績数
			ArrivalPlanMs_ActualDate[i]		= StockActualDate;			//入荷日
			ArrivalPlanMs_Com01[i]			= GetMsCom01;				//コメント1
			ArrivalPlanMs_Com02[i]			= GetMsCom02;				//コメント2
			ArrivalPlanMs_EntryDate[i]		= now_dtm;					//登録日
			ArrivalPlanMs_UpdateDate[i]		= now_dtm;					//更新日
			ArrivalPlanMs_EntryUser[i]		= "(" + A00000_Main.LoginUserId + ")" + A00000_Main.LoginUserName;	//登録者
			ArrivalPlanMs_UpdateUser[i]		= "(" + A00000_Main.LoginUserId + ")" + A00000_Main.LoginUserName;	//更新者
			
			ArrivalMs_ClWh[i]				= GetWhCd;					//担当倉庫
			ArrivalMs_ClCd[i]				= GetClCd;					//荷主CD
			ArrivalMs_ArrNo[i]				= ""+ArrivalPlanNoGet[0];	//入荷予定NO
			ArrivalMs_ArrCount[i]			= "0";						//入荷予定枝番
			ArrivalMs_MsNo[i]				= ""+MsNo;					//明細番号
			ArrivalMs_MsSeq[i]				= "0";						//明細Seq番号
			ArrivalMs_ItemCd[i]				= GetItemCd;				//商品コード
			ArrivalMs_ClItemCd[i]			= "";						//荷主商品コード
			ArrivalMs_JanCd[i]				= "";						//JanCd(バラ)
			ArrivalMs_ItemMdNo[i]			= "";						//商品型番
			ArrivalMs_ItemName[i]			= "";						//商品名
			ArrivalMs_Lot[i]				= GetLot;					//ロット
			ArrivalMs_ExpDate[i]			= GetExpDate;				//消費期限
			ArrivalMs_PlanQty[i]			= ""+GetActualQty;			//予定数量
			ArrivalMs_ActualQty[i]			= ""+GetActualQty;			//実績数
			ArrivalMs_ActualDate[i]			= StockActualDate;			//入荷日
			ArrivalMs_Com01[i]				= GetMsCom01;				//コメント1
			ArrivalMs_Com02[i]				= GetMsCom02;				//コメント2
			ArrivalMs_EntryDate[i]			= now_dtm;					//登録日
			ArrivalMs_UpdateDate[i]			= now_dtm;					//更新日
			ArrivalMs_EntryUser[i]			= "(" + A00000_Main.LoginUserId + ")" + A00000_Main.LoginUserName;	//登録者
			ArrivalMs_UpdateUser[i]			= "(" + A00000_Main.LoginUserId + ")" + A00000_Main.LoginUserName;	//更新者
			
			if(0>TgtItemMstRow) {
				ErrMsgFg = true;
				ErrMsg.add(MsNo+"行目エラー:商品Cdで商品が特定できませんでした");
			}else {
				ArrivalPlanMs_ClItemCd[i]		= (String)ItemMstRt[TgtItemMstRow][M100_ItemMstRt.ColClItemCd];		//荷主商品コード
				ArrivalPlanMs_JanCd[i]			= (String)ItemMstRt[TgtItemMstRow][M100_ItemMstRt.ColJanCd];			//JanCd（バラ）
				ArrivalPlanMs_ItemMdNo[i]		= (String)ItemMstRt[TgtItemMstRow][M100_ItemMstRt.ColItemMDNo];		//商品型番
				ArrivalPlanMs_ItemName[i]		= (String)ItemMstRt[TgtItemMstRow][M100_ItemMstRt.ColItemName01];		//商品名
				
				ArrivalMs_ClItemCd[i]			= (String)ItemMstRt[TgtItemMstRow][M100_ItemMstRt.ColClItemCd];		//荷主商品コード
				ArrivalMs_JanCd[i]				= (String)ItemMstRt[TgtItemMstRow][M100_ItemMstRt.ColJanCd];			//JanCd(バラ)
				ArrivalMs_ItemMdNo[i]			= (String)ItemMstRt[TgtItemMstRow][M100_ItemMstRt.ColItemMDNo];		//商品型番
				ArrivalMs_ItemName[i]			= (String)ItemMstRt[TgtItemMstRow][M100_ItemMstRt.ColItemName01];		//商品名
			}
			
			//入荷時ロケに在庫増やすためのデータ
			StockQtyControlSetData[i][Tools100_StockQtyControl.ColClCd]		= GetClCd;				//荷主コード
			StockQtyControlSetData[i][Tools100_StockQtyControl.ColWhCd]		= GetWhCd;				//倉庫コード
			StockQtyControlSetData[i][Tools100_StockQtyControl.ColLoc]			= B100_DefaultVariable.DefaultArrivalLoc;		//ロケーション
			StockQtyControlSetData[i][Tools100_StockQtyControl.ColItemCd]		= GetItemCd;			//商品コード
			StockQtyControlSetData[i][Tools100_StockQtyControl.ColLot]			= GetLot;				//ロット
			StockQtyControlSetData[i][Tools100_StockQtyControl.ColExpdate]		= GetExpDate;			//消費期限
			StockQtyControlSetData[i][Tools100_StockQtyControl.ColActualDate]	= StockActualDate;		//入荷実績日
			StockQtyControlSetData[i][Tools100_StockQtyControl.ColControlQty]	= GetActualQty;			//調整数
			
			//実ロケに格納（移動するためのデータ）
			if(!B100_DefaultVariable.DefaultArrivalLoc.equals(GetLoc)) {
				MoveData[MoveCount][Tools100_StockMoveControl.ColClCd]			= GetClCd;			//荷主コード
				MoveData[MoveCount][Tools100_StockMoveControl.ColWhCd]			= GetWhCd;			//倉庫コード
				MoveData[MoveCount][Tools100_StockMoveControl.ColFromLoc]		= B100_DefaultVariable.DefaultArrivalLoc;	//移動元ロケーション
				MoveData[MoveCount][Tools100_StockMoveControl.ColToLoc]		= GetLoc;			//移動先ロケーション
				MoveData[MoveCount][Tools100_StockMoveControl.ColItemCd]		= GetItemCd;		//商品コード
				MoveData[MoveCount][Tools100_StockMoveControl.ColLot]			= GetLot;			//ロット
				MoveData[MoveCount][Tools100_StockMoveControl.ColExpdate]		= GetExpDate;		//消費期限
				MoveData[MoveCount][Tools100_StockMoveControl.ColActualDate]	= StockActualDate;	//入荷実績日
				MoveData[MoveCount][Tools100_StockMoveControl.ColMoveQty]		= GetActualQty;		//移動数
				MoveData[MoveCount][Tools100_StockMoveControl.ColMoveCom01]	= "強制入荷";		//移動コメント01
				MoveData[MoveCount][Tools100_StockMoveControl.ColMoveCom02]	= "直接格納";		//移動コメント02
				MoveData[MoveCount][Tools100_StockMoveControl.ColMoveCom03]	= "";				//移動コメント03
				
				MoveCount	= MoveCount+1;
			}
		}
		
		Object[][] ArrivalPlanHd_SetString	= {
						 {"ClWh"		,"1" ,"0" ,"Key" ,ArrivalPlanHd_ClWh}		//担当倉庫
				 		,{"ClCd"		,"1" ,"0" ,"Key" ,ArrivalPlanHd_ClCd}		//荷主CD
						,{"ArrNo"		,"1" ,"0" ,"Key" ,ArrivalPlanHd_ArrNo}		//入荷予定NO
						,{"ClArrNo"		,"1" ,"0" ,"" ,ArrivalPlanHd_ClArrNo}		//荷主予定番号
						,{"PlanDate"	,"1" ,"0" ,"" ,ArrivalPlanHd_PlanDate}		//入荷予定日
						,{"ActualDate"	,"1" ,"0" ,"" ,ArrivalPlanHd_ActualDate}	//入荷実績日
						,{"SpCd"		,"1" ,"0" ,"" ,ArrivalPlanHd_SpCd}			//仕入先CD
						,{"SpName01"	,"1" ,"0" ,"" ,ArrivalPlanHd_SpName01}		//仕入先名01
						,{"SpName02"	,"1" ,"0" ,"" ,ArrivalPlanHd_SpName02}		//仕入先名02
						,{"SpName03"	,"1" ,"0" ,"" ,ArrivalPlanHd_SpName03}		//仕入先名03
						,{"SpPost"		,"1" ,"0" ,"" ,ArrivalPlanHd_SpPost}		//仕入先郵便
						,{"SpAdd01"		,"1" ,"0" ,"" ,ArrivalPlanHd_SpAdd01}		//仕入先住所01
						,{"SpAdd02"		,"1" ,"0" ,"" ,ArrivalPlanHd_SpAdd02}		//仕入先住所02
						,{"SpAdd03"		,"1" ,"0" ,"" ,ArrivalPlanHd_SpAdd03}		//仕入先住所03
						,{"SpTel"		,"1" ,"0" ,"" ,ArrivalPlanHd_SpTel}			//仕入先電話
						,{"ArCom01"		,"1" ,"0" ,"" ,ArrivalPlanHd_ArCom01}		//コメント1
						,{"ArCom02"		,"1" ,"0" ,"" ,ArrivalPlanHd_ArCom02}		//コメント2
						,{"ArCom03"		,"1" ,"0" ,"" ,ArrivalPlanHd_ArCom03}		//コメント3
						,{"EntryDate"	,"1" ,"0" ,"" ,ArrivalPlanHd_EntryDate}		//登録日
						,{"UpdateDate"	,"1" ,"0" ,"" ,ArrivalPlanHd_UpdateDate}	//更新日
						,{"EntryUser"	,"1" ,"0" ,"" ,ArrivalPlanHd_EntryUser}		//登録者
						,{"UpdateUser"	,"1" ,"0" ,"" ,ArrivalPlanHd_UpdateUser}	//更新者
						,{"FixFg"		,"1" ,"0" ,"" ,ArrivalPlanHd_FixFg}			//ステータス
						};
		
		Object[][] ArrivalPlanMs_SetString	= {
						 {"ClWh"		,"1" ,"0" ,"Key"	,ArrivalPlanMs_ClWh}		//担当倉庫
						,{"ClCd"		,"1" ,"0" ,"Key"	,ArrivalPlanMs_ClCd}		//荷主CD
						,{"ArrNo"		,"1" ,"0" ,"Key"	,ArrivalPlanMs_ArrNo}		//入荷予定NO
						,{"MsNo"		,"1" ,"0" ,"Key"	,ArrivalPlanMs_MsNo}		//明細番号
						,{"ItemCd"		,"1" ,"0" ,"" 		,ArrivalPlanMs_ItemCd}		//商品コード
						,{"ClItemCd"	,"1" ,"0" ,"" 		,ArrivalPlanMs_ClItemCd}	//荷主商品コード
						,{"JanCd"		,"1" ,"0" ,"" 		,ArrivalPlanMs_JanCd}		//JanCd（バラ）
						,{"ItemMdNo"	,"1" ,"0" ,"" 		,ArrivalPlanMs_ItemMdNo}	//商品型番
						,{"ItemName"	,"1" ,"0" ,"" 		,ArrivalPlanMs_ItemName}	//商品名
						,{"lot"			,"1" ,"0" ,"" 		,ArrivalPlanMs_lot}			//ロット
						,{"ExpDate"		,"1" ,"0" ,"" 		,ArrivalPlanMs_ExpDate}		//消費期限
						,{"PlanQty"		,"1" ,"0" ,"" 		,ArrivalPlanMs_PlanQty}		//予定数量
				 		,{"ActualQty"	,"1" ,"0" ,"" 		,ArrivalPlanMs_ActualQty}	//実績数
						,{"ActualDate"	,"1" ,"0" ,"" 		,ArrivalPlanMs_ActualDate}	//入荷日
						,{"Com01"		,"1" ,"0" ,"" 		,ArrivalPlanMs_Com01}		//コメント1
						,{"Com02"		,"1" ,"0" ,"" 		,ArrivalPlanMs_Com02}		//コメント2
						,{"EntryDate"	,"1" ,"0" ,"" 		,ArrivalPlanMs_EntryDate}	//登録日
						,{"UpdateDate"	,"1" ,"0" ,"" 		,ArrivalPlanMs_UpdateDate}	//更新日
						,{"EntryUser"	,"1" ,"0" ,"" 		,ArrivalPlanMs_EntryUser}	//登録者
						,{"UpdateUser"	,"1" ,"0" ,"" 		,ArrivalPlanMs_UpdateUser}	//更新者
						};
		
		Object[][] ArrivalHd_SetString	= {
						 {"ClWh"		,"1" ,"0" ,"Key"	,ArrivalHd_ClWh}		//担当倉庫
						,{"ClCd"		,"1" ,"0" ,"Key"	,ArrivalHd_ClCd}		//荷主CD
						,{"ArrNo"		,"1" ,"0" ,"Key"	,ArrivalHd_ArrNo}		//入荷予定NO
						,{"ArrCount"	,"1" ,"0" ,"Key"	,ArrivalHd_ArrCount}	//入荷予定枝番
						,{"ClArrNo"		,"1" ,"0" ,"" 		,ArrivalHd_ClArrNo}		//荷主予定番号
				 		,{"PlanDate"	,"1" ,"0" ,"" 		,ArrivalHd_PlanDate}	//入荷予定日
						,{"ActualDate"	,"1" ,"0" ,"" 		,ArrivalHd_ActualDate}	//入荷実績日
						,{"SpCd"		,"1" ,"0" ,"" 		,ArrivalHd_SpCd}		//仕入先CD
						,{"SpName01"	,"1" ,"0" ,"" 		,ArrivalHd_SpName01}	//仕入先名01
						,{"SpName02"	,"1" ,"0" ,"" 		,ArrivalHd_SpName02}	//仕入先名02
						,{"SpName03"	,"1" ,"0" ,"" 		,ArrivalHd_SpName03}	//仕入先名03
						,{"SpPost"		,"1" ,"0" ,"" 		,ArrivalHd_SpPost}		//仕入先郵便
						,{"SpAdd01"		,"1" ,"0" ,"" 		,ArrivalHd_SpAdd01}		//仕入先住所01
						,{"SpAdd02"		,"1" ,"0" ,"" 		,ArrivalHd_SpAdd02}		//仕入先住所02
						,{"SpAdd03"		,"1" ,"0" ,"" 		,ArrivalHd_SpAdd03}		//仕入先住所03
						,{"SpTel"		,"1" ,"0" ,"" 		,ArrivalHd_SpTel}		//仕入先電話
						,{"ArCom01"		,"1" ,"0" ,"" 		,ArrivalHd_ArCom01}		//コメント1
						,{"ArCom02"		,"1" ,"0" ,"" 		,ArrivalHd_ArCom02}		//コメント2
						,{"ArCom03"		,"1" ,"0" ,"" 		,ArrivalHd_ArCom03}		//コメント3
						,{"EntryDate"	,"1" ,"0" ,"" 		,ArrivalHd_EntryDate}	//登録日
						,{"UpdateDate"	,"1" ,"0" ,"" 		,ArrivalHd_UpdateDate}	//更新日
						,{"EntryUser"	,"1" ,"0" ,"" 		,ArrivalHd_EntryUser}	//登録者
						,{"UpdateUser"	,"1" ,"0" ,"" 		,ArrivalHd_UpdateUser}	//更新者
						};
						
		Object[][] ArrivalMs_SetString	= {
						 {"ClWh"		,"1" ,"0" ,"Key"	,ArrivalMs_ClWh}		//担当倉庫
						,{"ClCd"		,"1" ,"0" ,"Key"	,ArrivalMs_ClCd}		//荷主CD
						,{"ArrNo"		,"1" ,"0" ,"Key"	,ArrivalMs_ArrNo}		//入荷予定NO
						,{"ArrCount"	,"1" ,"0" ,"Key"	,ArrivalMs_ArrCount}	//入荷予定枝番
						,{"MsNo"		,"1" ,"0" ,"Key"	,ArrivalMs_MsNo}		//明細番号
						,{"MsSeq"		,"1" ,"0" ,"Key"	,ArrivalMs_MsSeq}		//明細Seq番号
						,{"ItemCd"		,"1" ,"0" ,"" 		,ArrivalMs_ItemCd	}	//商品コード
						,{"ClItemCd"	,"1" ,"0" ,"" 		,ArrivalMs_ClItemCd}	//荷主商品コード
						,{"JanCd"		,"1" ,"0" ,"" 		,ArrivalMs_JanCd}		//JanCd(バラ)
						,{"ItemMdNo"	,"1" ,"0" ,"" 		,ArrivalMs_ItemMdNo}	//商品型番
						,{"ItemName"	,"1" ,"0" ,"" 		,ArrivalMs_ItemName}	//商品名
						,{"Lot"			,"1" ,"0" ,"" 		,ArrivalMs_Lot}			//ロット
						,{"ExpDate"		,"1" ,"0" ,"" 		,ArrivalMs_ExpDate}		//消費期限
						,{"PlanQty"		,"1" ,"0" ,"" 		,ArrivalMs_PlanQty}		//予定数量
						,{"ActualQty"	,"1" ,"0" ,"" 		,ArrivalMs_ActualQty}	//実績数
						,{"ActualDate"	,"1" ,"0" ,"" 		,ArrivalMs_ActualDate}	//入荷日
						,{"Com01"		,"1" ,"0" ,"" 		,ArrivalMs_Com01}		//コメント1
						,{"Com02"		,"1" ,"0" ,"" 		,ArrivalMs_Com02}		//コメント2
						,{"EntryDate"	,"1" ,"0" ,"" 		,ArrivalMs_EntryDate}	//登録日
						,{"UpdateDate"	,"1" ,"0" ,"" 		,ArrivalMs_UpdateDate}	//更新日
						,{"EntryUser"	,"1" ,"0" ,"" 		,ArrivalMs_EntryUser}	//登録者
						,{"UpdateUser"	,"1" ,"0" ,"" 		,ArrivalMs_UpdateUser}	//更新者
						};
		if(!ErrMsgFg) {
			Tools100_StockQtyControl.StockQtyControl(StockQtyControlSetData) ;
			Tools100_StockMoveControl.StockMoveControl(MoveData);
			
			A100_InsertUpdateSQL.InsertUpdateSomeRecord(ArrivalPlanHd_SetString	,ArrivalPlanHd_tgt_table	,TgtDB	,non_msg_fg);
			A100_InsertUpdateSQL.InsertUpdateSomeRecord(ArrivalHd_SetString		,ArrivalHd_tgt_table		,TgtDB	,non_msg_fg);
			A100_InsertUpdateSQL.InsertUpdateSomeRecord(ArrivalPlanMs_SetString	,ArrivalPlanMs_tgt_table	,TgtDB	,non_msg_fg);
			A100_InsertUpdateSQL.InsertUpdateSomeRecord(ArrivalMs_SetString		,ArrivalMs_tgt_table		,TgtDB	,non_msg_fg);
		}else {
			ErrView(ErrMsg);
		}
		
		return ErrMsgFg;
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
		
		NumberFormat ni = NumberFormat.getNumberInstance();
		
		//処理前の値を格納
		String GetWhCd 				= B100_DefaultVariable.WhList[1][((JComboBox)ItemCdControlSet[ColWhCd]).getSelectedIndex()];
		String GetClCd 				= B100_DefaultVariable.ClList[1][((JComboBox)ItemCdControlSet[ColClCd]).getSelectedIndex()];
		String GetSpCd 				= B100_DefaultVariable.SupplierList[1][((JComboBox)ItemCdControlSet[ColSpCd]).getSelectedIndex()];
		
		String GetItemCd			= B100_TextControl.Trim(((JTextField)ItemCdControlSet[ColItemCd]).getText());
		String GetItemName			= B100_TextControl.Trim(((JLabel)ItemCdControlSet[ColItemName]).getText());
		String GetLot				= B100_TextControl.Trim(((JTextField)ItemCdControlSet[ColLot]).getText());
		String GetExpdate			= B100_TextControl.TextToDate(((JFormattedTextField)ItemCdControlSet[ColExpdate]).getText());
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
		
		//商品マスタがうまく検索できなかった場合に格納する値をセット
		String SetItemCd 			= "";
		String SetItemName			= "";
		String SetQtyUnitName		= "";
		
		int SetPlQty				= 0;
		int SetCsQty				= 0;
		int SetCtQty				= 0;
		int SetBrQty				= GetQty;
		
		String SetPlUnitName		= "";
		String SetCsUnitName		= "";
		String SetCtUnitName		= "";
		String SetBrUnitName		= "";
		
		int SetPlUnitQty			= 0;
		int SetCsUnitQty			= 0;
		int SetCtUnitQty			= 0;
		
		String SetMstRecomendLoc	= "";
		String SetStockRecomendLoc	= "";
		
		//商品マスタ情報取得
		if(!"".equals(GetItemCd)) {
			Object[][] ItemMstRt	= ItemMstRt(GetClCd,GetItemCd);
			if(1==ItemMstRt.length) {
				SetItemCd 			= (String)ItemMstRt[0][M100_ItemMstRt.ColItemCd];
				SetItemName			= (String)ItemMstRt[0][M100_ItemMstRt.ColItemName01];
				SetQtyUnitName		= (String)ItemMstRt[0][M100_ItemMstRt.ColUnitName];
				
				SetPlUnitQty		= (int)ItemMstRt[0][M100_ItemMstRt.ColPlQty];
				SetCsUnitQty		= (int)ItemMstRt[0][M100_ItemMstRt.ColCsQty];
				SetCtUnitQty		= (int)ItemMstRt[0][M100_ItemMstRt.ColCtQty];
				
				SetPlUnitName		= (String)ItemMstRt[0][M100_ItemMstRt.ColPlUnitName];
				SetCsUnitName		= (String)ItemMstRt[0][M100_ItemMstRt.ColCsUnitName];
				SetCtUnitName		= (String)ItemMstRt[0][M100_ItemMstRt.ColCtUnitName];
				SetBrUnitName		= (String)ItemMstRt[0][M100_ItemMstRt.ColUnitName];
				
				String[][] TgtItemCd	= new String[1][Tools100_RecomendLocWithStockSerch.InRecomendLocWithStockSerch().length];
				TgtItemCd[0][Tools100_RecomendLocWithStockSerch.InColItemCd]		= SetItemCd;
				TgtItemCd[0][Tools100_RecomendLocWithStockSerch.InColLot]			= GetLot;
				TgtItemCd[0][Tools100_RecomendLocWithStockSerch.InColExpdate]		= GetExpdate;
				
				
				String[][] RecomendLocWithStockSerch	= Tools100_RecomendLocWithStockSerch.RecomendLocWithStockSerch(GetWhCd,GetClCd,TgtItemCd);
				
				SetMstRecomendLoc	= RecomendLocWithStockSerch[0][Tools100_RecomendLocWithStockSerch.ColMstRecomendLoc];
				SetStockRecomendLoc	= RecomendLocWithStockSerch[0][Tools100_RecomendLocWithStockSerch.ColRecomendLoc];
			}
		}
		if(0<SetPlUnitQty) {
			SetPlQty	= (int)(SetBrQty/SetPlUnitQty);
			SetBrQty	= (int)(SetBrQty%SetPlUnitQty);
		}else {
			SetPlUnitQty	= 0;
		}
		if(0<SetCsUnitQty) {
			SetCsQty	= (int)(SetBrQty/SetCsUnitQty);
			SetBrQty	= (int)(SetBrQty%SetCsUnitQty);
		}else {
			SetCsUnitQty	= 0;
		}
		if(0<SetCtUnitQty) {
			SetCtQty	= (int)(SetBrQty/SetCtUnitQty);
			SetBrQty	= (int)(SetBrQty%SetCtUnitQty);
		}else {
			SetCtUnitQty	= 0;
		}
		
		((JTextField)ItemCdControlSet[ColItemCd]).setText(SetItemCd);
		((JLabel)ItemCdControlSet[ColItemName]).setText(SetItemName);
		((JLabel)ItemCdControlSet[ColQtyUnitName]).setText(SetQtyUnitName);
		
		((JFormattedTextField)ItemCdControlSet[ColPlQty]).setText(""+ni.format(SetPlQty));
		((JFormattedTextField)ItemCdControlSet[ColCsQty]).setText(""+ni.format(SetCsQty));
		((JFormattedTextField)ItemCdControlSet[ColCtQty]).setText(""+ni.format(SetCtQty));
		((JFormattedTextField)ItemCdControlSet[ColBrQty]).setText(""+ni.format(SetBrQty));
		
		((JLabel)ItemCdControlSet[ColPlUnitName]).setText(SetPlUnitName);
		((JLabel)ItemCdControlSet[ColCsUnitName]).setText(SetCsUnitName);
		((JLabel)ItemCdControlSet[ColCtUnitName]).setText(SetCtUnitName);
		((JLabel)ItemCdControlSet[ColBrUnitName]).setText(SetBrUnitName);
		
		((JFormattedTextField)ItemCdControlSet[ColPlUnitQty]).setText(""+ni.format(SetPlUnitQty));
		((JFormattedTextField)ItemCdControlSet[ColCsUnitQty]).setText(""+ni.format(SetCsUnitQty));
		((JFormattedTextField)ItemCdControlSet[ColCtUnitQty]).setText(""+ni.format(SetCtUnitQty));
		
		((JTextField)ItemCdControlSet[ColMstRecomendLoc]).setText(SetMstRecomendLoc);
		((JTextField)ItemCdControlSet[ColStockRecomendLoc]).setText(SetStockRecomendLoc);
		
		if(GetEntryMode) {
			((JFormattedTextField)ItemCdControlSet[ColQty]).setEditable(false);
			((JFormattedTextField)ItemCdControlSet[ColQty]).setBackground(B100_FrameParts.SelectColer("NoEntry"));
			
			if(0<SetPlUnitQty) {
				((JFormattedTextField)ItemCdControlSet[ColPlQty]).setEditable(true);
				((JFormattedTextField)ItemCdControlSet[ColPlQty]).setBackground(B100_FrameParts.SelectColer("Entry"));
			}else {
				((JFormattedTextField)ItemCdControlSet[ColPlQty]).setEditable(false);
				((JFormattedTextField)ItemCdControlSet[ColPlQty]).setBackground(B100_FrameParts.SelectColer("NoEntry"));
			}
			if(0<SetCsUnitQty) {
				((JFormattedTextField)ItemCdControlSet[ColCsQty]).setEditable(true);
				((JFormattedTextField)ItemCdControlSet[ColCsQty]).setBackground(B100_FrameParts.SelectColer("Entry"));
			}else {
				((JFormattedTextField)ItemCdControlSet[ColCsQty]).setEditable(false);
				((JFormattedTextField)ItemCdControlSet[ColCsQty]).setBackground(B100_FrameParts.SelectColer("NoEntry"));
			}
			if(0<SetCtUnitQty) {
				((JFormattedTextField)ItemCdControlSet[ColCtQty]).setEditable(true);
				((JFormattedTextField)ItemCdControlSet[ColCtQty]).setBackground(B100_FrameParts.SelectColer("Entry"));
			}else {
				((JFormattedTextField)ItemCdControlSet[ColCtQty]).setEditable(false);
				((JFormattedTextField)ItemCdControlSet[ColCtQty]).setBackground(B100_FrameParts.SelectColer("NoEntry"));
			}
			((JFormattedTextField)ItemCdControlSet[ColBrQty]).setEditable(true);
			((JFormattedTextField)ItemCdControlSet[ColBrQty]).setBackground(B100_FrameParts.SelectColer("Entry"));
		}else {
			((JFormattedTextField)ItemCdControlSet[ColQty]).setEditable(true);
			((JFormattedTextField)ItemCdControlSet[ColPlQty]).setEditable(false);
			((JFormattedTextField)ItemCdControlSet[ColCsQty]).setEditable(false);
			((JFormattedTextField)ItemCdControlSet[ColCtQty]).setEditable(false);
			((JFormattedTextField)ItemCdControlSet[ColBrQty]).setEditable(false);
			
			((JFormattedTextField)ItemCdControlSet[ColQty]).setBackground(B100_FrameParts.SelectColer("Entry"));
			((JFormattedTextField)ItemCdControlSet[ColPlQty]).setBackground(B100_FrameParts.SelectColer("NoEntry"));
			((JFormattedTextField)ItemCdControlSet[ColCsQty]).setBackground(B100_FrameParts.SelectColer("NoEntry"));
			((JFormattedTextField)ItemCdControlSet[ColCtQty]).setBackground(B100_FrameParts.SelectColer("NoEntry"));
			((JFormattedTextField)ItemCdControlSet[ColBrQty]).setBackground(B100_FrameParts.SelectColer("NoEntry"));
		}
		
	}
	
	private static void QtyControl(Object[] QtyControlSet) {
		int ColPlUnitQty 	= 0;
		int ColCsUnitQty 	= 1;
		int ColCtUnitQty 	= 2;
		int ColQty 			= 3;
		int ColPlQty 		= 4;
		int ColCsQty 		= 5;
		int ColCtQty 		= 6;
		int ColBrQty 		= 7;
		//処理前の値を格納
		int GetPlUnitQty	= B100_TextControl.TextToInt(((JFormattedTextField)QtyControlSet[ColPlUnitQty]).getText());
		int GetCsUnitQty	= B100_TextControl.TextToInt(((JFormattedTextField)QtyControlSet[ColCsUnitQty]).getText());
		int GetCtUnitQty	= B100_TextControl.TextToInt(((JFormattedTextField)QtyControlSet[ColCtUnitQty]).getText());
		int GetQty			= B100_TextControl.TextToInt(((JFormattedTextField)QtyControlSet[ColQty]).getText());
		int GetPlQty		= B100_TextControl.TextToInt(((JFormattedTextField)QtyControlSet[ColPlQty]).getText());
		int GetCsQty		= B100_TextControl.TextToInt(((JFormattedTextField)QtyControlSet[ColCsQty]).getText());
		int GetCtQty		= B100_TextControl.TextToInt(((JFormattedTextField)QtyControlSet[ColCtQty]).getText());
		int GetBrQty		= B100_TextControl.TextToInt(((JFormattedTextField)QtyControlSet[ColBrQty]).getText());
		
		int SetPlQty		= 0;
		int SetCsQty		= 0;
		int SetCtQty		= 0;
		int SetBrQty		= GetQty;
		NumberFormat ni = NumberFormat.getNumberInstance();
		
		if(0<GetPlUnitQty) {
			SetPlQty	= (int)(SetBrQty/GetPlUnitQty);
			SetBrQty	= (int)(SetBrQty%GetPlUnitQty);
		}
		if(0<GetCsUnitQty) {
			SetCsQty	= (int)(SetBrQty/GetCsUnitQty);
			SetBrQty	= (int)(SetBrQty%GetCsUnitQty);
		}
		if(0<GetCtUnitQty) {
			SetCtQty	= (int)(SetBrQty/GetCtUnitQty);
			SetBrQty	= (int)(SetBrQty%GetCtUnitQty);
		}
		
		((JFormattedTextField)QtyControlSet[ColQty]).setText(""+ni.format(GetQty));
		((JFormattedTextField)QtyControlSet[ColPlQty]).setText(""+ni.format(SetPlQty));
		((JFormattedTextField)QtyControlSet[ColCsQty]).setText(""+ni.format(SetCsQty));
		((JFormattedTextField)QtyControlSet[ColCtQty]).setText(""+ni.format(SetCtQty));
		((JFormattedTextField)QtyControlSet[ColBrQty]).setText(""+ni.format(SetBrQty));
	}
	
	private static void UnitQtyControl(Object[] QtyControlSet) {
		int ColPlUnitQty 	= 0;
		int ColCsUnitQty 	= 1;
		int ColCtUnitQty 	= 2;
		int ColQty 			= 3;
		int ColPlQty 		= 4;
		int ColCsQty 		= 5;
		int ColCtQty 		= 6;
		int ColBrQty 		= 7;
		//処理前の値を格納
		int GetPlUnitQty	= B100_TextControl.TextToInt(((JFormattedTextField)QtyControlSet[ColPlUnitQty]).getText());
		int GetCsUnitQty	= B100_TextControl.TextToInt(((JFormattedTextField)QtyControlSet[ColCsUnitQty]).getText());
		int GetCtUnitQty	= B100_TextControl.TextToInt(((JFormattedTextField)QtyControlSet[ColCtUnitQty]).getText());
		int GetQty			= B100_TextControl.TextToInt(((JFormattedTextField)QtyControlSet[ColQty]).getText());
		int GetPlQty		= B100_TextControl.TextToInt(((JFormattedTextField)QtyControlSet[ColPlQty]).getText());
		int GetCsQty		= B100_TextControl.TextToInt(((JFormattedTextField)QtyControlSet[ColCsQty]).getText());
		int GetCtQty		= B100_TextControl.TextToInt(((JFormattedTextField)QtyControlSet[ColCtQty]).getText());
		int GetBrQty		= B100_TextControl.TextToInt(((JFormattedTextField)QtyControlSet[ColBrQty]).getText());
		
		int SetQty			= GetPlUnitQty*GetPlQty+GetCsUnitQty*GetCsQty+GetCtUnitQty*GetCtQty+GetBrQty;
		NumberFormat ni = NumberFormat.getNumberInstance();
		
		((JFormattedTextField)QtyControlSet[ColQty]).setText(""+ni.format(SetQty));
		((JFormattedTextField)QtyControlSet[ColPlQty]).setText(""+ni.format(GetPlQty));
		((JFormattedTextField)QtyControlSet[ColCsQty]).setText(""+ni.format(GetCsQty));
		((JFormattedTextField)QtyControlSet[ColCtQty]).setText(""+ni.format(GetCtQty));
		((JFormattedTextField)QtyControlSet[ColBrQty]).setText(""+ni.format(GetBrQty));
	}
	
	private static Object[][] LocationMstRt(String ClCd,String Loc){
		if(null==ClCd) {ClCd="";}
		if(null==Loc) {Loc="";}
		
		ArrayList<String> SearchClCd 	= new ArrayList<String>();	//荷主コード
		ArrayList<String> SearchWhCd 	= new ArrayList<String>();	//倉庫コード
		ArrayList<String> SearchLoc 	= new ArrayList<String>();	//ロケーション
		ArrayList<String> SearchLocName = new ArrayList<String>();	//ロケーション名
		ArrayList<String> SearchType 	= new ArrayList<String>();	//ロケタイプ
		boolean LocExactMatch = true;	//ロケーション完全一致
		boolean AllSearch = false;
		if(!"".equals(ClCd)) {
			SearchClCd.add(ClCd);
		}
		if(!"".equals(Loc)) {
			SearchLoc.add(Loc);
		}
		Object[][] LocationMstRt = M100_LocationMstRt.LocationMstRt(
				SearchClCd,		//荷主コード
				SearchWhCd,		//倉庫コード
				SearchLoc,		//ロケーション
				SearchLocName,	//ロケーション名
				SearchType,		//ロケタイプ
				LocExactMatch,	//ロケーション完全一致
				AllSearch);
		
		return LocationMstRt;
	}
	
	private static Object[][] M100_SupplierRt(String TgtClWh,String TgtClCd,String TgtSPCd){
		if(null==TgtClWh) {TgtClWh	= "";}
		if(null==TgtClCd) {TgtClCd	= "";}
		if(null==TgtSPCd) {TgtSPCd	= "";}
		
		ArrayList<String> SearchClWh = new ArrayList<String>(); 			//担当倉庫
		ArrayList<String> SearchClCd = new ArrayList<String>();				//荷主CD
		ArrayList<String> SearchSPCd = new ArrayList<String>();				//仕入先コード
		ArrayList<String> SearchSPName = new ArrayList<String>();			//仕入先名
		ArrayList<String> SearchSPPost = new ArrayList<String>();			//仕入先郵便
		ArrayList<String> SearchSPAdd = new ArrayList<String>();			//仕入先住所
		ArrayList<String> SearchSPTel = new ArrayList<String>();			//仕入先電話
		ArrayList<String> SearchSPFax = new ArrayList<String>();			//仕入先FAX
		ArrayList<String> SearchSPMail = new ArrayList<String>();			//仕入先MAIL
		ArrayList<String> SearchCom = new ArrayList<String>();				//コメント
		ArrayList<String> SearchPTMSCDBMN = new ArrayList<String>();		//基幹Sysコード（部門）
		ArrayList<String> SearchPTMSCDNINUSHI = new ArrayList<String>();	//基幹Sysコード（荷主）
		ArrayList<Integer> SearchPaySiteStr = new ArrayList<Integer>();		//支払いサイト（月数）開始
		ArrayList<Integer> SearchPayDateStr = new ArrayList<Integer>();		//支払日（日＝99）開始
		ArrayList<Integer> SearchShimeDateStr = new ArrayList<Integer>();	//締め日（末日＝99）開始
		ArrayList<Integer> SearchPaySiteEnd = new ArrayList<Integer>();		//支払いサイト（月数）終了
		ArrayList<Integer> SearchPayDateEnd = new ArrayList<Integer>();		//支払日（日＝99）終了
		ArrayList<Integer> SearchShimeDateEnd = new ArrayList<Integer>();	//締め日（末日＝99）終了
		ArrayList<String> SearchDECD = new ArrayList<String>();				//納品先コード
		ArrayList<String> SearchDepartmentCd = new ArrayList<String>();		//部署CD
		boolean AllSearch = false;
		
		SearchClWh.add(TgtClWh);
		SearchClCd.add(TgtClCd);
		SearchSPCd.add(TgtSPCd);
		
		Object[][] SupplierRt = M100_SupplierRt.SupplierRt(
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
		
		return SupplierRt;
	}
	private static Object[][] ItemMstRtArray(String ClCd,ArrayList<String> SearchItemCd){
		if(null==ClCd) {ClCd="";}
		
		ArrayList<String> SearchClCd				= new ArrayList<String>();	//荷主コード
		ArrayList<String> SearchClGpCd 				= new ArrayList<String>();	//荷主グループコード
		//ArrayList<String> SearchItemCd 				= new ArrayList<String>();	//商品コード
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
	private static void ErrView(ArrayList<String>ErrMsg) {
		//必要フォルダを生成する
		String FLD_PATH = A00000_Main.MainFLD+"\\ArrivalControl";
		B100_FolderCheck.FLD_CHECK(FLD_PATH);
		FLD_PATH = A00000_Main.MainFLD+"\\ArrivalControl\\ArrivalForceEntry";
		B100_FolderCheck.FLD_CHECK(FLD_PATH);
		FLD_PATH = A00000_Main.MainFLD+"\\ArrivalControl\\ArrivalForceEntry\\Err";
		B100_FolderCheck.FLD_CHECK(FLD_PATH);
		FLD_PATH = A00000_Main.MainFLD+"\\ArrivalControl\\ArrivalForceEntry\\BK";
		B100_FolderCheck.FLD_CHECK(FLD_PATH);
		
		//ファイルに出力
		String NowDTM=B100_DateTimeControl.dtmString2(B100_DateTimeControl.dtm()[1])[1].replace(" ", "").replace("/", "").replace(":", "");
		
		FLD_PATH = A00000_Main.MainFLD+"\\ArrivalControl\\ArrivalForceEntry\\Err";
		
		String ErrFP = FLD_PATH+"\\ERR"+NowDTM+".txt";
		
		B100_TextExport.txt_exp2(ErrMsg, ErrFP,"UTF-8");
		
		//古いエラーデータ削除
		B100_FolderCheck.ToolsOldFileDeleteWhereFileName(FLD_PATH ,"ERR",B100_DefaultVariable.ErrTxtDelete);
		
		//ファイル開く
		File file = new File(ErrFP);
		Desktop desktop = Desktop.getDesktop();
		try {
			desktop.open(file);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}