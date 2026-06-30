import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class WT100_Stock_10_Adjust{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	
	public static void StockAdjust(int x,int y,
			final String HandoverWhCd,final String HandoverClCd,final String HandoverLoc,final String HandoverItemCd,final String HandoverLot,final String HandoverExpdate,final String HandoverActualDate
			) {
		A00000_Main.LoginCheck();//
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		
		String TgtWhCd		= HandoverWhCd;
		String TgtClCd		= HandoverClCd;
		String TgtLoc		= HandoverLoc;
		String TgtItemCd	= HandoverItemCd;
		String TgtLot		= HandoverLot;
		String TgtExpdate	= HandoverExpdate;
		String TgtActualDate= HandoverActualDate;
		
		if(null==TgtWhCd		) {TgtWhCd			="";}
		if(null==TgtClCd		) {TgtClCd			="";}
		if(null==TgtLoc			) {TgtLoc			="";}
		if(null==TgtItemCd		) {TgtItemCd		="";}
		if(null==TgtLot			) {TgtLot			="";}
		if(null==TgtExpdate		) {TgtExpdate		="";}
		if(null==TgtActualDate	) {TgtActualDate	="";}
		
		if("".equals(TgtWhCd)) {TgtWhCd	=A00000_Main.ClWh;}
		if("".equals(TgtClCd)) {TgtClCd	=A00000_Main.ClCd;}
		
		Object[] StockData	= StockDataRt(
										TgtWhCd,
										TgtClCd,
										TgtLoc,
										TgtItemCd,
										TgtLot,
										TgtExpdate,
										TgtActualDate
										);
		
		final JFrame main_fm 	= B100_FrameParts.FrameCreate(x,y,800,750,"Corgi00在庫調整","ZK");
		JLabel userinfo 		= B100_FrameParts.UserInfo();
		JButton exit_btn 		= B100_FrameParts.ExitBtn();
		JButton entry_btn 		= B100_FrameParts.EntryBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		main_fm.add(entry_btn);

		JLabel LB_ClCd									= B100_FrameParts.JLabelSet(					  0, 50,150,20,"荷主:"				,11,1);
		JLabel LB_WhCd									= B100_FrameParts.JLabelSet(					  0, 75,150,20,"倉庫:"				,11,1);
		
		JLabel LB_Loc									= B100_FrameParts.JLabelSet(					  0,100,150,20,"ロケーション:"		,11,1);
		JLabel LB_LocName								= B100_FrameParts.JLabelSet(					  0,125,150,20,"ロケーション名:"	,11,1);
		JLabel LB_LocType								= B100_FrameParts.JLabelSet(					  0,150,150,20,"ロケータイプ:"		,11,1);
		JLabel LB_ItemCd								= B100_FrameParts.JLabelSet(					  0,175,150,20,"商品コード:"		,11,1);
		JLabel LB_ItemName								= B100_FrameParts.JLabelSet(					  0,200,150,20,"商品名:"			,11,1);
		JLabel LB_Lot									= B100_FrameParts.JLabelSet(					  0,225,150,20,"ロット:"			,11,1);
		JLabel LB_Expdate								= B100_FrameParts.JLabelSet(					  0,250,150,20,"消費期限:"			,11,1);
		JLabel LB_ActualDate							= B100_FrameParts.JLabelSet(					  0,275,150,20,"入荷実績日:"		,11,1);
		
		JLabel LB_CtUnitQty								= B100_FrameParts.JLabelSet(					250,250,150,20,"カートン入数(バラ換算):"	,11,1);
		JLabel LB_CsUnitQty								= B100_FrameParts.JLabelSet(					250,275,150,20,"ケース入数(バラ換算):"		,11,1);
		JLabel LB_PlUnitQty								= B100_FrameParts.JLabelSet(					250,300,150,20,"パレット入数(バラ換算):"	,11,1);
		
		final JFormattedTextField TB_CtUnitQty			= B100_FrameParts.JFormattedTextFieldSet(	400,250,70,20,""	,12,1,"#,###");
		final JFormattedTextField TB_CsUnitQty			= B100_FrameParts.JFormattedTextFieldSet(	400,275,70,20,""	,12,1,"#,###");
		final JFormattedTextField TB_PlUnitQty			= B100_FrameParts.JFormattedTextFieldSet(	400,300,70,20,""	,12,1,"#,###");
		
		JLabel LB_Qty									= B100_FrameParts.JLabelSet(					300,450,150,20,"調整前総数量:"		,11,1);
		JLabel LB_ShipPlanQty							= B100_FrameParts.JLabelSet(					300,475,150,20,"調整前引当済数:"	,11,1);
		JLabel LB_PossibleQty							= B100_FrameParts.JLabelSet(					300,500,150,20,"調整前出荷可能数:"	,11,1);
		
		JLabel LB_Msg									= B100_FrameParts.JLabelSet(					520,475, 70,20,"⇒⇒⇒"				,11,2);
		
		JLabel LB_AdjustQty								= B100_FrameParts.JLabelSet(					  0,325,150,20,"バラ換算調整数:"	,11,1);
		JLabel LB_AdjustReason 							= B100_FrameParts.JLabelSet(					380,325,100,20,"調整理由:"			,11,1);
		
		JLabel LB_AdjustCom01							= B100_FrameParts.JLabelSet(					380,350,100,20,"コメント01:"		,11,1);
		JLabel LB_AdjustCom02							= B100_FrameParts.JLabelSet(					380,375,100,20,"コメント02:"		,11,1);
		JLabel LB_AdjustCom03							= B100_FrameParts.JLabelSet(					380,400,100,20,"コメント03:"		,11,1);
		
		JLabel LB_PlAdjustQty							= B100_FrameParts.JLabelSet(					130,350,100,20,"パレット:"			,11,1);
		JLabel LB_CsAdjustQty							= B100_FrameParts.JLabelSet(					130,375,100,20,"ケース:"			,11,1);
		JLabel LB_CtAdjustQty							= B100_FrameParts.JLabelSet(					130,400,100,20,"カートン:"			,11,1);
		JLabel LB_BrAdjustQty							= B100_FrameParts.JLabelSet(					130,425,100,20,"バラ:"				,11,1);
		
		final JLabel LB_PlAdjustUnitName				= B100_FrameParts.JLabelSet(					300,350,100,20,""					,11,0);
		final JLabel LB_CsAdjustUnitName				= B100_FrameParts.JLabelSet(					300,375,100,20,""					,11,0);
		final JLabel LB_CtAdjustUnitName				= B100_FrameParts.JLabelSet(					300,400,100,20,""					,11,0);
		final JLabel LB_BrAdjustUnitName				= B100_FrameParts.JLabelSet(					300,425,100,20,""					,11,0);
		
		JLabel LB_AfterQty								= B100_FrameParts.JLabelSet(					660,450,150,20,":調整後総数量"		,11,0);
		JLabel LB_AfterShipPlanQty						= B100_FrameParts.JLabelSet(					660,475,150,20,":調整後引当済数"	,11,0);
		JLabel LB_AfterPossibleQty						= B100_FrameParts.JLabelSet(					660,500,150,20,":調整後出荷可能数"	,11,0);
		
		final JComboBox TB_ClCd							= B100_FrameParts.JComboBoxSet(				150, 50,200,20,B100_DefaultVariable.ClList[0],11);	//荷主コード
		final JComboBox TB_WhCd							= B100_FrameParts.JComboBoxSet(				150, 75,200,20,B100_DefaultVariable.WhList[0],11);	//倉庫コード
		
		final JTextField TB_Loc							= B100_FrameParts.JTextFieldSet(				150,100,100,20,""	,12,0);								//ロケーション
		final JTextField TB_LocName						= B100_FrameParts.JTextFieldSet(				150,125,200,20,""	,12,0);								//ロケーション名
		final JComboBox TB_LocType						= B100_FrameParts.JComboBoxSet(				150,150,200,20,B100_DefaultVariable.LocType[0],12);	//ロケータイプ
		final JTextField TB_ItemCd						= B100_FrameParts.JTextFieldSet(				150,175,100,20,""	,12,0);								//商品コード
		final JTextField TB_ItemName					= B100_FrameParts.JTextFieldSet(				150,200,200,20,""	,12,0);								//商品名
		final JTextField TB_Lot							= B100_FrameParts.JTextFieldSet(				150,225,200,20,""	,12,0);								//ロット
		final JFormattedTextField TB_Expdate			= B100_FrameParts.JFormattedTextFieldSet(	150,250, 70,20,""	,12,0,"YYYY/MM/DD");				//消費期限
		final JFormattedTextField TB_ActualDate			= B100_FrameParts.JFormattedTextFieldSet(	150,275, 70,20,""	,12,0,"YYYY/MM/DD");				//入荷実績日
		
		final JFormattedTextField TB_Qty				= B100_FrameParts.JFormattedTextFieldSet(	450,450, 70,20,"0"	,12,1,"#,###");	//調整前数量
		final JFormattedTextField TB_ShipPlanQty		= B100_FrameParts.JFormattedTextFieldSet(	450,475, 70,20,"0"	,12,1,"#,###");	//調整前引当済数
		final JFormattedTextField TB_PossibleQty		= B100_FrameParts.JFormattedTextFieldSet(	450,500, 70,20,"0"	,12,1,"#,###");	
		
		final JFormattedTextField TB_AdjustQty			= B100_FrameParts.JFormattedTextFieldSet(	150,325, 70,20,"0"	,12,1,"#,###");	//調整数
		final JCheckBox TB_EntryMode 					= B100_FrameParts.JCheckBoxSet(				230,325,150,20,"荷姿別で調整",11);
		final JComboBox TB_AdjustReason 				= B100_FrameParts.JComboBoxSet(				480,325,200,20,B100_DefaultVariable.AdjustReasonList[0],12);	//調整理由
		final JTextField TB_AdjustCom01					= B100_FrameParts.JTextFieldSet(				480,350,200,20,""	,12,0);			//コメント01
		final JTextField TB_AdjustCom02					= B100_FrameParts.JTextFieldSet(				480,375,200,20,""	,12,0);			//コメント02
		final JTextField TB_AdjustCom03					= B100_FrameParts.JTextFieldSet(				480,400,200,20,""	,12,0);			//コメント03

		final JFormattedTextField TB_PlAdjustQty		= B100_FrameParts.JFormattedTextFieldSet(	230,350, 70,20,"0"	,12,1,"#,###");
		final JFormattedTextField TB_CsAdjustQty		= B100_FrameParts.JFormattedTextFieldSet(	230,375, 70,20,"0"	,12,1,"#,###");
		final JFormattedTextField TB_CtAdjustQty		= B100_FrameParts.JFormattedTextFieldSet(	230,400, 70,20,"0"	,12,1,"#,###");
		final JFormattedTextField TB_BrAdjustQty		= B100_FrameParts.JFormattedTextFieldSet(	230,425, 70,20,"0"	,12,1,"#,###");
		
		final JFormattedTextField TB_AfterQty			= B100_FrameParts.JFormattedTextFieldSet(	590,450, 70,20,"0"	,12,1,"#,###");	//調整後数量
		final JFormattedTextField TB_AfterShipPlanQty	= B100_FrameParts.JFormattedTextFieldSet(	590,475, 70,20,"0"	,12,1,"#,###");	//調整後引当済数
		final JFormattedTextField TB_AfterPossibleQty	= B100_FrameParts.JFormattedTextFieldSet(	590,500, 70,20,"0"	,12,1,"#,###");	//調整後出荷可能数
		
		TB_ClCd.setSelectedIndex(	B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.ClList[1]		,(String)StockData[T100_StockRt.ColClCd] ,true) );		//荷主コード
		TB_WhCd.setSelectedIndex(	B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.ClList[1]		,(String)StockData[T100_StockRt.ColWhCd] ,true) );		//倉庫コード
		TB_LocType.setSelectedIndex(B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.LocType[1]		,(int)StockData[T100_StockRt.ColType]+"" ,true) );		//ロケタイプ
		
		TB_ClCd			.setBackground(B100_FrameParts.SelectColer("NoEntry"));
		TB_WhCd			.setBackground(B100_FrameParts.SelectColer("NoEntry"));
		TB_LocType		.setBackground(B100_FrameParts.SelectColer("NoEntry"));
		TB_AdjustReason	.setBackground(B100_FrameParts.SelectColer("Entry"));
		TB_AdjustCom01	.setBackground(B100_FrameParts.SelectColer("Entry"));
		TB_AdjustCom02	.setBackground(B100_FrameParts.SelectColer("Entry"));
		TB_AdjustCom03	.setBackground(B100_FrameParts.SelectColer("Entry"));
		
		JLabel LB_BeforeTotalQty						= B100_FrameParts.JLabelSet(					450,525, 70,20,"調整前総数"			,11,2);
		JLabel LB_AfterTotalQty							= B100_FrameParts.JLabelSet(					590,525, 70,20,"調整後総数"			,11,2);
		JLabel LB_Msg2									= B100_FrameParts.JLabelSet(					520,525, 70,20,"⇒⇒⇒"				,11,2);
		
		final JFormattedTextField TB_BeforePlQty		= B100_FrameParts.JFormattedTextFieldSet(	450,550, 70,20,"0"	,12,1,"#,###");
		final JFormattedTextField TB_BeforeCsQty		= B100_FrameParts.JFormattedTextFieldSet(	450,575, 70,20,"0"	,12,1,"#,###");
		final JFormattedTextField TB_BeforeCtQty		= B100_FrameParts.JFormattedTextFieldSet(	450,600, 70,20,"0"	,12,1,"#,###");
		final JFormattedTextField TB_BeforeBrQty		= B100_FrameParts.JFormattedTextFieldSet(	450,625, 70,20,"0"	,12,1,"#,###");
		final JLabel TB_BeforePlUnitName				= B100_FrameParts.JLabelSet(					520,550, 70,20,""	,12,0);
		final JLabel TB_BeforeCsUnitName				= B100_FrameParts.JLabelSet(					520,575, 70,20,""	,12,0);
		final JLabel TB_BeforeCtUnitName				= B100_FrameParts.JLabelSet(					520,600, 70,20,""	,12,0);
		final JLabel TB_BeforeBrUnitName				= B100_FrameParts.JLabelSet(					520,625, 70,20,""	,12,0);
		
		final JFormattedTextField TB_AfterPlQty			= B100_FrameParts.JFormattedTextFieldSet(	590,550, 70,20,"0"	,12,1,"#,###");
		final JFormattedTextField TB_AfterCsQty			= B100_FrameParts.JFormattedTextFieldSet(	590,575, 70,20,"0"	,12,1,"#,###");
		final JFormattedTextField TB_AfterCtQty			= B100_FrameParts.JFormattedTextFieldSet(	590,600, 70,20,"0"	,12,1,"#,###");
		final JFormattedTextField TB_AfterBrQty			= B100_FrameParts.JFormattedTextFieldSet(	590,625, 70,20,"0"	,12,1,"#,###");
		final JLabel TB_AfterPlUnitName					= B100_FrameParts.JLabelSet(					660,550, 70,20,""	,12,0);
		final JLabel TB_AfterCsUnitName					= B100_FrameParts.JLabelSet(					660,575, 70,20,""	,12,0);
		final JLabel TB_AfterCtUnitName					= B100_FrameParts.JLabelSet(					660,600, 70,20,""	,12,0);
		final JLabel TB_AfterBrUnitName					= B100_FrameParts.JLabelSet(					660,625, 70,20,""	,12,0);
		
		TB_CtUnitQty.setBackground(B100_FrameParts.SelectColer("NoEntry"));
		TB_CsUnitQty.setBackground(B100_FrameParts.SelectColer("NoEntry"));
		TB_PlUnitQty.setBackground(B100_FrameParts.SelectColer("NoEntry"));
		TB_Qty.setBackground(B100_FrameParts.SelectColer("NoEntry"));
		TB_ShipPlanQty.setBackground(B100_FrameParts.SelectColer("NoEntry"));
		TB_PossibleQty.setBackground(B100_FrameParts.SelectColer("NoEntry"));
		TB_AfterQty.setBackground(B100_FrameParts.SelectColer("NoEntry"));
		TB_AfterShipPlanQty.setBackground(B100_FrameParts.SelectColer("NoEntry"));
		TB_AfterPossibleQty.setBackground(B100_FrameParts.SelectColer("NoEntry"));
		
		TB_BeforePlQty.setBackground(B100_FrameParts.SelectColer("NoEntry"));
		TB_BeforeCsQty.setBackground(B100_FrameParts.SelectColer("NoEntry"));
		TB_BeforeCtQty.setBackground(B100_FrameParts.SelectColer("NoEntry"));
		TB_BeforeBrQty.setBackground(B100_FrameParts.SelectColer("NoEntry"));
		TB_AfterPlQty.setBackground(B100_FrameParts.SelectColer("NoEntry"));
		TB_AfterCsQty.setBackground(B100_FrameParts.SelectColer("NoEntry"));
		TB_AfterCtQty.setBackground(B100_FrameParts.SelectColer("NoEntry"));
		TB_AfterBrQty.setBackground(B100_FrameParts.SelectColer("NoEntry"));
		
		TB_ClCd.setEnabled(false);		//荷主コード
		TB_WhCd.setEnabled(false);		//倉庫コード
		TB_LocType.setEnabled(false);
		TB_LocName.setEditable(false);
		TB_ItemName.setEditable(false);
		
		TB_Qty.setEditable(false);
		TB_ShipPlanQty.setEditable(false);
		TB_PossibleQty.setEditable(false);
		TB_CtUnitQty.setEditable(false);
		TB_CsUnitQty.setEditable(false);
		TB_PlUnitQty.setEditable(false);
		TB_PlAdjustQty.setEditable(false);
		TB_CsAdjustQty.setEditable(false);
		TB_CtAdjustQty.setEditable(false);
		TB_BrAdjustQty.setEditable(false);
		
		TB_BeforePlQty.setEditable(false);
		TB_BeforeCsQty.setEditable(false);
		TB_BeforeCtQty.setEditable(false);
		TB_BeforeBrQty.setEditable(false);
		TB_AfterPlQty.setEditable(false);
		TB_AfterCsQty.setEditable(false);
		TB_AfterCtQty.setEditable(false);
		TB_AfterBrQty.setEditable(false);
		
		FramePartsVolSet(
				StockData,
				TB_CtUnitQty,
				TB_CsUnitQty,
				TB_PlUnitQty,
				LB_PlAdjustUnitName,
				LB_CsAdjustUnitName,
				LB_CtAdjustUnitName,
				LB_BrAdjustUnitName,
				TB_ClCd,
				TB_WhCd,
				TB_Loc,
				TB_LocName,
				TB_LocType,
				TB_ItemCd,
				TB_ItemName,
				TB_Lot,
				TB_Expdate,
				TB_ActualDate,
				TB_Qty,
				TB_ShipPlanQty,
				TB_PossibleQty,
				TB_EntryMode,
				TB_AfterQty,
				TB_AfterShipPlanQty,
				TB_AfterPossibleQty,
				TB_BeforePlQty,
				TB_BeforeCsQty,
				TB_BeforeCtQty,
				TB_BeforeBrQty,
				TB_BeforePlUnitName,
				TB_BeforeCsUnitName,
				TB_BeforeCtUnitName,
				TB_BeforeBrUnitName,
				TB_AfterPlQty,
				TB_AfterCsQty,
				TB_AfterCtQty,
				TB_AfterBrQty,
				TB_AfterPlUnitName,
				TB_AfterCsUnitName,
				TB_AfterCtUnitName,
				TB_AfterBrUnitName
				);
		
		AdjustFromQty(
				TB_CtUnitQty,
				TB_CsUnitQty,
				TB_PlUnitQty,
				TB_AdjustQty,
				TB_PlAdjustQty,
				TB_CsAdjustQty,
				TB_CtAdjustQty,
				TB_BrAdjustQty,
				TB_Qty,
				TB_ShipPlanQty,
				TB_PossibleQty,
				TB_LocType,
				TB_AfterQty,
				TB_AfterShipPlanQty,
				TB_AfterPossibleQty,
				TB_BeforePlQty,
				TB_BeforeCsQty,
				TB_BeforeCtQty,
				TB_BeforeBrQty,
				TB_AfterPlQty,
				TB_AfterCsQty,
				TB_AfterCtQty,
				TB_AfterBrQty
				);
		
		AdjustModeControl(
				TB_EntryMode,
				TB_CtUnitQty,
				TB_CsUnitQty,
				TB_PlUnitQty,
				TB_AdjustQty,
				TB_PlAdjustQty,
				TB_CsAdjustQty,
				TB_CtAdjustQty,
				TB_BrAdjustQty
				);
		
		TB_AfterQty.setEditable(false);
		TB_AfterShipPlanQty.setEditable(false);
		TB_AfterPossibleQty.setEditable(false);
		
		main_fm.add(LB_ClCd);
		main_fm.add(LB_WhCd);
		main_fm.add(LB_Loc);
		main_fm.add(LB_LocName);
		main_fm.add(LB_LocType);
		main_fm.add(LB_ItemCd);
		main_fm.add(LB_ItemName);
		main_fm.add(LB_Lot);
		main_fm.add(LB_Expdate);
		main_fm.add(LB_ActualDate);
		main_fm.add(LB_Qty);
		main_fm.add(LB_ShipPlanQty);
		main_fm.add(LB_PossibleQty);
		main_fm.add(LB_Msg);
		main_fm.add(LB_AdjustQty);
		main_fm.add(LB_AdjustReason);
		main_fm.add(LB_AdjustCom01);
		main_fm.add(LB_AdjustCom02);
		main_fm.add(LB_AdjustCom03);
		main_fm.add(LB_PlAdjustQty);
		main_fm.add(LB_CsAdjustQty);
		main_fm.add(LB_CtAdjustQty);
		main_fm.add(LB_BrAdjustQty);
		main_fm.add(LB_PlAdjustUnitName);
		main_fm.add(LB_CsAdjustUnitName);
		main_fm.add(LB_CtAdjustUnitName);
		main_fm.add(LB_BrAdjustUnitName);
		main_fm.add(LB_AfterQty);
		main_fm.add(LB_AfterShipPlanQty);
		main_fm.add(LB_AfterPossibleQty);
		main_fm.add(LB_BeforeTotalQty);
		main_fm.add(LB_AfterTotalQty);
		
		main_fm.add(LB_CtUnitQty);
		main_fm.add(LB_CsUnitQty);
		main_fm.add(LB_PlUnitQty);
		main_fm.add(TB_CtUnitQty);
		main_fm.add(TB_CsUnitQty);
		main_fm.add(TB_PlUnitQty);
		
		main_fm.add(TB_ClCd);
		main_fm.add(TB_WhCd);
		main_fm.add(TB_Loc);
		main_fm.add(TB_LocName);
		main_fm.add(TB_LocType);
		main_fm.add(TB_ItemCd);
		main_fm.add(TB_ItemName);
		main_fm.add(TB_Lot);
		main_fm.add(TB_Expdate);
		main_fm.add(TB_ActualDate);
		main_fm.add(TB_Qty);
		main_fm.add(TB_ShipPlanQty);
		main_fm.add(TB_PossibleQty);
		main_fm.add(TB_AdjustQty);
		main_fm.add(TB_EntryMode);
		main_fm.add(TB_AdjustReason);
		main_fm.add(TB_AdjustCom01);
		main_fm.add(TB_AdjustCom02);
		main_fm.add(TB_AdjustCom03);
		main_fm.add(TB_PlAdjustQty);
		main_fm.add(TB_CsAdjustQty);
		main_fm.add(TB_CtAdjustQty);
		main_fm.add(TB_BrAdjustQty);
		main_fm.add(TB_AfterQty);
		main_fm.add(TB_AfterShipPlanQty);
		main_fm.add(TB_AfterPossibleQty);
		
		main_fm.add(TB_BeforePlQty);
		main_fm.add(TB_BeforeCsQty);
		main_fm.add(TB_BeforeCtQty);
		main_fm.add(TB_BeforeBrQty);
		main_fm.add(TB_BeforePlUnitName);
		main_fm.add(TB_BeforeCsUnitName);
		main_fm.add(TB_BeforeCtUnitName);
		main_fm.add(TB_BeforeBrUnitName);
		
		main_fm.add(TB_AfterPlQty);
		main_fm.add(TB_AfterCsQty);
		main_fm.add(TB_AfterCtQty);
		main_fm.add(TB_AfterBrQty);
		main_fm.add(TB_AfterPlUnitName);
		main_fm.add(TB_AfterCsUnitName);
		main_fm.add(TB_AfterCtUnitName);
		main_fm.add(TB_AfterBrUnitName);
		
		JButton LocSearchBtn	= B100_FrameParts.BtnSet(	260,100, 90,20,"ロケ検索",11);		
		final JFrame Loc_fm 	= B100_FrameParts.FrameCreate(x+10,y+10,800,750,"Corgi00在庫調整(ロケ検索)","ZK");
		JLabel 	LocUserinfo 	= B100_FrameParts.UserInfo();
		JButton LocExit_btn 	= B100_FrameParts.ExitBtn();
		JButton LocEntry_btn 	= B100_FrameParts.EntryBtn();
		
		Loc_fm.add(LocUserinfo);
		Loc_fm.add(LocExit_btn);
		Loc_fm.add(LocEntry_btn);
		JLabel LB_SearchLoc					= B100_FrameParts.JLabelSet(		  0, 50,130,20,"ロケーション:"		,11,1);
		JLabel LB_SearchLocName				= B100_FrameParts.JLabelSet(		  0, 75,130,20,"ロケーション名:"	,11,1);
		JLabel LB_SearchType				= B100_FrameParts.JLabelSet(		  0,100,130,20,"ロケタイプ:"		,11,1);
		
		final JTextField  TB_SearchLoc		= B100_FrameParts.JTextFieldSet(	130, 50,100,20,"",12,0);									//ロケーション
		final JTextField  TB_SearchLocName	= B100_FrameParts.JTextFieldSet(	130, 75,100,20,"",12,0);									//ロケーション名
		final JComboBox   TB_SearchType		= B100_FrameParts.JComboBoxSet( 	130,100,100,20,B100_DefaultVariable.SearchLocType[0],12);	//ロケタイプ
		
		JLabel LB2_SearchLoc				= B100_FrameParts.JLabelSet(	  	230, 50,100,20,"で始まる"	,11,0);
		JLabel LB2_SearchLocName			= B100_FrameParts.JLabelSet(  		230, 75,100,20,"を含む"		,11,0);
		
		JButton LocSearchKickBtn			= B100_FrameParts.BtnSet(			130,150, 90,20,"検索",11);
		
		Loc_fm.add(LB_SearchLoc);
		Loc_fm.add(LB_SearchLocName);
		Loc_fm.add(LB_SearchType);
		Loc_fm.add(TB_SearchLoc);
		Loc_fm.add(TB_SearchLocName);
		Loc_fm.add(TB_SearchType);
		Loc_fm.add(LB2_SearchLoc);
		Loc_fm.add(LB2_SearchLocName);
		Loc_fm.add(LocSearchKickBtn);
		
		Object[][] RtSettingLocationMstRt = M100_LocationMstRt.RtSettingLocationMstRt();
		
		String[] columnNamesLoc = new String[RtSettingLocationMstRt.length+1];
		
		columnNamesLoc[0] = "Fg";
		for(int i=0;i<RtSettingLocationMstRt.length;i++) {
			columnNamesLoc[1+(int)RtSettingLocationMstRt[i][1]] = ""+RtSettingLocationMstRt[i][3];
		}
		
		//編集可能カラムの指定
		B100_TableControl.RenewTgt = new int[1];
		B100_TableControl.RenewTgt[0] = 0;

		final DefaultTableModel tableModel_msLoc = new B100_TableControl.MyTableModel01(columnNamesLoc,0);
		
		final JTable tbLoc = new JTable(tableModel_msLoc);
		tbLoc.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tbLoc.setRowHeight(20*A00000_Main.Mul/A00000_Main.Div);
		tbLoc.setFont(new Font(A00000_Main.DefaultFont, Font.PLAIN, 12*A00000_Main.Mul/A00000_Main.Div));
		
		DefaultTableColumnModel columnModelLoc
		= (DefaultTableColumnModel)tbLoc.getColumnModel();
		
		//列幅初期設定 表示位置設定
		TableColumn column = null;
		
		column = columnModelLoc.getColumn( 0);	column.setPreferredWidth( 30*A00000_Main.Mul/A00000_Main.Div);	//FG
		
		for(int i=0;i<RtSettingLocationMstRt.length;i++) {
			if("int".equals((String)RtSettingLocationMstRt[i][2])||"float".equals((String)RtSettingLocationMstRt[i][2])) {
				column = columnModelLoc.getColumn(1+(int)RtSettingLocationMstRt[i][1]);	column.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.rightCellRenderer());
			}else {
				column = columnModelLoc.getColumn(1+(int)RtSettingLocationMstRt[i][1]);	column.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.leftCellRenderer());
			}
		}
		
		//スクロール用設定
		JScrollPane scpnLoc = B100_FrameParts.JScrollPaneSet(10,230,870,395,tbLoc);
		Loc_fm.add(scpnLoc);
		
		JButton ItemSearchBtn	= B100_FrameParts.BtnSet(	260,175, 90,20,"商品検索",11);
		final JFrame Item_fm 	= B100_FrameParts.FrameCreate(x+10,y+10,800,750,"Corgi00在庫調整(商品検索)","ZK");
		JLabel 	ItemUserinfo 	= B100_FrameParts.UserInfo();
		JButton ItemExit_btn 	= B100_FrameParts.ExitBtn();
		JButton ItemEntry_btn 	= B100_FrameParts.EntryBtn();
		
		Item_fm.add(ItemUserinfo);
		Item_fm.add(ItemExit_btn);
		Item_fm.add(ItemEntry_btn);
		JLabel LB_SearchItemCd  						= B100_FrameParts.JLabelSet(		  0, 50,130,20,"商品コード:"			,11,1);
		JLabel LB_SearchItemName  						= B100_FrameParts.JLabelSet(		  0, 75,130,20,"商品名:"				,11,1);
		
		final JTextField TB_SearchItemCd  				= B100_FrameParts.JTextFieldSet(	130, 50,100,20,""						,11,0);	//商品コード
		final JTextField TB_SearchItemName  			= B100_FrameParts.JTextFieldSet(	130, 75,100,20,""						,11,0);	//商品名
		
		JLabel LB2_SearchItemCd  						= B100_FrameParts.JLabelSet(		230, 50, 80,20,"と一致"					,11,0);	//商品コード
		JLabel LB2_SearchItemName  						= B100_FrameParts.JLabelSet(		230, 75, 80,20,"を含む"					,11,0);	//商品名
		
		JButton ItemSearchKickBtn						= B100_FrameParts.BtnSet(			130,125, 90,20,"検索",11);
		
		Item_fm.add(LB_SearchItemCd);
		Item_fm.add(LB_SearchItemName);
		Item_fm.add(TB_SearchItemCd);
		Item_fm.add(TB_SearchItemName);
		Item_fm.add(LB2_SearchItemCd);
		Item_fm.add(LB2_SearchItemName);
		Item_fm.add(ItemSearchKickBtn);
		
		Object[][] RtSettingItemMstRt = M100_ItemMstRt.RtSettingItemMstRt();
		
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
		column = null;
		
		column = columnModelItem.getColumn( 0);	column.setPreferredWidth( 30*A00000_Main.Mul/A00000_Main.Div);	//FG
		
		for(int i=0;i<RtSettingItemMstRt.length;i++) {
			if("int".equals((String)RtSettingItemMstRt[i][2])||"float".equals((String)RtSettingItemMstRt[i][2])) {
				column = columnModelItem.getColumn(1+(int)RtSettingItemMstRt[i][1]);	column.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.rightCellRenderer());
			}else {
				column = columnModelItem.getColumn(1+(int)RtSettingItemMstRt[i][1]);	column.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.leftCellRenderer());
			}
		}
		
		//スクロール用設定
		JScrollPane scpnItem = B100_FrameParts.JScrollPaneSet(10,270,1280,350,tbItem);
		Item_fm.add(scpnItem);
		
		main_fm.add(LocSearchBtn);
		main_fm.add(ItemSearchBtn);
		
		RenewFg = true;
		main_fm.setVisible(true);
		
		
		//ロケーションマスタ検索画面表示
		LocSearchBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					int RowCount = tableModel_msLoc.getRowCount();
					for(int i=0;i<RowCount;i++) {
						tableModel_msLoc.removeRow(0);
					}
					TB_SearchLoc.setText("");
					TB_SearchLocName.setText("");
					TB_SearchType.setSelectedIndex(0);
					
					Loc_fm.setVisible(true);
					RenewFg = true;
				}
			}
		});
		
		//商品マスタ検索画面表示
		ItemSearchBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					int RowCount = tableModel_msItem.getRowCount();
					for(int i=0;i<RowCount;i++) {
						tableModel_msItem.removeRow(0);
					}
					TB_SearchItemCd.setText("");
					TB_SearchItemName.setText("");
					Item_fm.setVisible(true);
					RenewFg = true;
				}
			}
		});
		//ロケーションマスタ選択ボタン押下時の挙動
		LocEntry_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					int RowCount = tableModel_msLoc.getRowCount();
					String GetLoc = "";
					
					for(int i=0;i<RowCount;i++) {
						if((boolean)tableModel_msLoc.getValueAt(i, 0)) {
							GetLoc = ""+tableModel_msLoc.getValueAt(i, M100_LocationMstRt.ColLoc+1);
						}
					}
					
					TB_Loc.setText(GetLoc);
					TB_Loc.repaint();
					Loc_fm.setVisible(false);
					
					String TgtClCd			= B100_TextControl.Trim(B100_DefaultVariable.ClList[1][TB_ClCd.getSelectedIndex()]);	//荷主コード
					String TgtWhCd			= B100_TextControl.Trim(B100_DefaultVariable.WhList[1][TB_WhCd.getSelectedIndex()]);	//倉庫コード
					String TgtLoc			= B100_TextControl.Trim(TB_Loc.getText());							//ロケーション
					String TgtItemCd		= B100_TextControl.Trim(TB_ItemCd.getText());						//商品コード
					String TgtLot			= B100_TextControl.Trim(TB_Lot.getText());							//ロット
					String TgtExpdate		= B100_TextControl.TextToDate(TB_Expdate.getText());				//消費期限
					String TgtActualDate	= B100_TextControl.TextToDate(TB_ActualDate.getText());			//入荷実績日
					
					Object[] StockData	= StockDataRt(
							TgtWhCd,
							TgtClCd,
							TgtLoc,
							TgtItemCd,
							TgtLot,
							TgtExpdate,
							TgtActualDate
							);
					
					FramePartsVolSet(
							StockData,
							TB_CtUnitQty,
							TB_CsUnitQty,
							TB_PlUnitQty,
							LB_PlAdjustUnitName,
							LB_CsAdjustUnitName,
							LB_CtAdjustUnitName,
							LB_BrAdjustUnitName,
							TB_ClCd,
							TB_WhCd,
							TB_Loc,
							TB_LocName,
							TB_LocType,
							TB_ItemCd,
							TB_ItemName,
							TB_Lot,
							TB_Expdate,
							TB_ActualDate,
							TB_Qty,
							TB_ShipPlanQty,
							TB_PossibleQty,
							TB_EntryMode,
							TB_AfterQty,
							TB_AfterShipPlanQty,
							TB_AfterPossibleQty,
							TB_BeforePlQty,
							TB_BeforeCsQty,
							TB_BeforeCtQty,
							TB_BeforeBrQty,
							TB_BeforePlUnitName,
							TB_BeforeCsUnitName,
							TB_BeforeCtUnitName,
							TB_BeforeBrUnitName,
							TB_AfterPlQty,
							TB_AfterCsQty,
							TB_AfterCtQty,
							TB_AfterBrQty,
							TB_AfterPlUnitName,
							TB_AfterCsUnitName,
							TB_AfterCtUnitName,
							TB_AfterBrUnitName
							);
					
					AdjustFromQty(
							TB_CtUnitQty,
							TB_CsUnitQty,
							TB_PlUnitQty,
							TB_AdjustQty,
							TB_PlAdjustQty,
							TB_CsAdjustQty,
							TB_CtAdjustQty,
							TB_BrAdjustQty,
							TB_Qty,
							TB_ShipPlanQty,
							TB_PossibleQty,
							TB_LocType,
							TB_AfterQty,
							TB_AfterShipPlanQty,
							TB_AfterPossibleQty,
							TB_BeforePlQty,
							TB_BeforeCsQty,
							TB_BeforeCtQty,
							TB_BeforeBrQty,
							TB_AfterPlQty,
							TB_AfterCsQty,
							TB_AfterCtQty,
							TB_AfterBrQty
							);
					
					AdjustModeControl(
							TB_EntryMode,
							TB_CtUnitQty,
							TB_CsUnitQty,
							TB_PlUnitQty,
							TB_AdjustQty,
							TB_PlAdjustQty,
							TB_CsAdjustQty,
							TB_CtAdjustQty,
							TB_BrAdjustQty
							);
					
					EntryCheckTB_ColorControl(
							HandoverWhCd,
							HandoverClCd,
							HandoverLoc,
							HandoverItemCd,
							HandoverLot,
							HandoverExpdate,
							HandoverActualDate,
							TB_ClCd,
							TB_WhCd,
							TB_Loc,
							TB_ItemCd,
							TB_Lot,
							TB_Expdate,
							TB_ActualDate,
							TB_Qty,
							TB_ShipPlanQty,
							TB_PossibleQty,
							TB_AfterQty,
							TB_AfterShipPlanQty,
							TB_AfterPossibleQty,
							TB_BeforePlQty,
							TB_BeforeCsQty,
							TB_BeforeCtQty,
							TB_BeforeBrQty,
							TB_AfterPlQty,
							TB_AfterCsQty,
							TB_AfterCtQty,
							TB_AfterBrQty,
							TB_AdjustQty,
							TB_PlAdjustQty,
							TB_CsAdjustQty,
							TB_CtAdjustQty,
							TB_BrAdjustQty
							);
					RenewFg = true;
				}
			}
		});
		//商品マスタ選択ボタン押下時の挙動
		ItemEntry_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					int RowCount = tableModel_msItem.getRowCount();
					String GetItemCd = "";
					
					for(int i=0;i<RowCount;i++) {
						if((boolean)tableModel_msItem.getValueAt(i, 0)) {
							GetItemCd = ""+tableModel_msItem.getValueAt(i, M100_ItemMstRt.ColItemCd+1);
						}
					}
					TB_ItemCd.setText(GetItemCd);
					Item_fm.setVisible(false);
					
					String TgtClCd			= B100_TextControl.Trim(B100_DefaultVariable.ClList[1][TB_ClCd.getSelectedIndex()]);	//荷主コード
					String TgtWhCd			= B100_TextControl.Trim(B100_DefaultVariable.WhList[1][TB_WhCd.getSelectedIndex()]);	//倉庫コード
					String TgtLoc			= B100_TextControl.Trim(TB_Loc.getText());							//ロケーション
					String TgtItemCd		= B100_TextControl.Trim(TB_ItemCd.getText());						//商品コード
					String TgtLot			= B100_TextControl.Trim(TB_Lot.getText());							//ロット
					String TgtExpdate		= B100_TextControl.TextToDate(TB_Expdate.getText());				//消費期限
					String TgtActualDate	= B100_TextControl.TextToDate(TB_ActualDate.getText());			//入荷実績日
					
					Object[] StockData	= StockDataRt(
							TgtWhCd,
							TgtClCd,
							TgtLoc,
							TgtItemCd,
							TgtLot,
							TgtExpdate,
							TgtActualDate
							);
					
					FramePartsVolSet(
							StockData,
							TB_CtUnitQty,
							TB_CsUnitQty,
							TB_PlUnitQty,
							LB_PlAdjustUnitName,
							LB_CsAdjustUnitName,
							LB_CtAdjustUnitName,
							LB_BrAdjustUnitName,
							TB_ClCd,
							TB_WhCd,
							TB_Loc,
							TB_LocName,
							TB_LocType,
							TB_ItemCd,
							TB_ItemName,
							TB_Lot,
							TB_Expdate,
							TB_ActualDate,
							TB_Qty,
							TB_ShipPlanQty,
							TB_PossibleQty,
							TB_EntryMode,
							TB_AfterQty,
							TB_AfterShipPlanQty,
							TB_AfterPossibleQty,
							TB_BeforePlQty,
							TB_BeforeCsQty,
							TB_BeforeCtQty,
							TB_BeforeBrQty,
							TB_BeforePlUnitName,
							TB_BeforeCsUnitName,
							TB_BeforeCtUnitName,
							TB_BeforeBrUnitName,
							TB_AfterPlQty,
							TB_AfterCsQty,
							TB_AfterCtQty,
							TB_AfterBrQty,
							TB_AfterPlUnitName,
							TB_AfterCsUnitName,
							TB_AfterCtUnitName,
							TB_AfterBrUnitName
							);
					
					AdjustFromQty(
							TB_CtUnitQty,
							TB_CsUnitQty,
							TB_PlUnitQty,
							TB_AdjustQty,
							TB_PlAdjustQty,
							TB_CsAdjustQty,
							TB_CtAdjustQty,
							TB_BrAdjustQty,
							TB_Qty,
							TB_ShipPlanQty,
							TB_PossibleQty,
							TB_LocType,
							TB_AfterQty,
							TB_AfterShipPlanQty,
							TB_AfterPossibleQty,
							TB_BeforePlQty,
							TB_BeforeCsQty,
							TB_BeforeCtQty,
							TB_BeforeBrQty,
							TB_AfterPlQty,
							TB_AfterCsQty,
							TB_AfterCtQty,
							TB_AfterBrQty
							);
					
					AdjustModeControl(
							TB_EntryMode,
							TB_CtUnitQty,
							TB_CsUnitQty,
							TB_PlUnitQty,
							TB_AdjustQty,
							TB_PlAdjustQty,
							TB_CsAdjustQty,
							TB_CtAdjustQty,
							TB_BrAdjustQty
							);
					EntryCheckTB_ColorControl(
							HandoverWhCd,
							HandoverClCd,
							HandoverLoc,
							HandoverItemCd,
							HandoverLot,
							HandoverExpdate,
							HandoverActualDate,
							TB_ClCd,
							TB_WhCd,
							TB_Loc,
							TB_ItemCd,
							TB_Lot,
							TB_Expdate,
							TB_ActualDate,
							TB_Qty,
							TB_ShipPlanQty,
							TB_PossibleQty,
							TB_AfterQty,
							TB_AfterShipPlanQty,
							TB_AfterPossibleQty,
							TB_BeforePlQty,
							TB_BeforeCsQty,
							TB_BeforeCtQty,
							TB_BeforeBrQty,
							TB_AfterPlQty,
							TB_AfterCsQty,
							TB_AfterCtQty,
							TB_AfterBrQty,
							TB_AdjustQty,
							TB_PlAdjustQty,
							TB_CsAdjustQty,
							TB_CtAdjustQty,
							TB_BrAdjustQty
							);
					RenewFg = true;
				}
			}
		});
		
		//ロケーションマスタ検索ボタン押下時の挙動
		LocSearchKickBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					int RowCount = tableModel_msLoc.getRowCount();
					for(int i=0;i<RowCount;i++) {
						tableModel_msLoc.removeRow(0);
					}
					String SearchTgtClCd			= B100_TextControl.Trim(B100_DefaultVariable.ClList[1][TB_ClCd.getSelectedIndex()]);	//荷主コード
					String SearchTgtWhCd			= B100_TextControl.Trim(B100_DefaultVariable.WhList[1][TB_WhCd.getSelectedIndex()]);	//倉庫コード
					String SearchTgtLoc		= B100_TextControl.Trim(TB_SearchLoc.getText());
					String SearchTgtLocName	= B100_TextControl.Trim(TB_SearchLocName.getText());
					String SearchTgtType 	= B100_TextControl.Trim(B100_DefaultVariable.SearchLocType[1][TB_SearchType.getSelectedIndex()]);
					
					Object[][] LocSearch = LocSearch(
												SearchTgtClCd,
												SearchTgtWhCd,
												SearchTgtLoc,
												SearchTgtLocName,
												SearchTgtType
												);
					for(int i=0;i<LocSearch.length;i++) {
						Object[] SetOb = new Object[LocSearch[i].length+1];
						SetOb[0] = false;
						for(int i01=0;i01<LocSearch[i].length;i01++) {
							SetOb[i01+1] = ""+LocSearch[i][i01];
						}
						tableModel_msLoc.addRow(SetOb);
					}
					Loc_fm.setVisible(true);
					RenewFg = true;
				}
			}
		});
		
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
					String SearchTgtItemName	= B100_TextControl.Trim(TB_SearchItemName.getText());
					
					Object[][] ItemSearch= ItemSearch(
												SearchTgtClGp,
												SearchTgtItemCd,
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
		//ロケーションマスタ検索チェックボックス操作時の挙動
		tableModel_msLoc.addTableModelListener(new TableModelListener(){
			public void tableChanged(TableModelEvent e){
				if(RenewFg) {
					RenewFg = false;
					int row_count = tbLoc.getRowCount();
					Boolean setBL=Boolean.valueOf(false);
					for(int i=0;i<row_count;i++){
						if(i!=e.getFirstRow()){
							tableModel_msLoc.setValueAt(setBL, i, 0);
						}else {
	
						}
					}
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
		//ロケーションマスタ検索EXITボタン押下時の挙動
		LocExit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				Loc_fm.setVisible(false);
				Loc_fm.dispose();
			}
		});
		//商品マスタ検索EXITボタン押下時の挙動
		ItemExit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				Item_fm.setVisible(false);
				Item_fm.dispose();
			}
		});
		
		
		//登録ボタン押下時の挙動
		entry_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					String SetClCd		= B100_TextControl.Trim(B100_DefaultVariable.ClList[1][TB_ClCd.getSelectedIndex()]);	//荷主コード
					String SetWhCd		= B100_TextControl.Trim(B100_DefaultVariable.WhList[1][TB_WhCd.getSelectedIndex()]);	//倉庫コード
					
					String SetLoc				= B100_TextControl.Trim(TB_Loc.getText());						//ロケーション
					String SetLocName			= B100_TextControl.Trim(TB_LocName.getText());					//ロケーション名
					String SetLocType			= B100_TextControl.Trim(B100_DefaultVariable.LocType[1][TB_LocType.getSelectedIndex()]);	//ロケータイプ
					String SetItemCd			= B100_TextControl.Trim(TB_ItemCd.getText());					//商品コード
					String SetItemName			= B100_TextControl.Trim(TB_ItemName.getText());					//商品名
					String SetLot				= B100_TextControl.Trim(TB_Lot.getText());						//ロット
					String SetExpdate			= B100_TextControl.Trim(TB_Expdate.getText());					//消費期限
					String SetActualDate		= B100_TextControl.Trim(TB_ActualDate.getText());				//入荷実績日
					String SetAdjustReason		= B100_TextControl.Trim(B100_DefaultVariable.AdjustReasonList[1][TB_AdjustReason.getSelectedIndex()]);	//調整理由
					String SetAdjustReasonName	= B100_TextControl.Trim(B100_DefaultVariable.AdjustReasonList[2][TB_AdjustReason.getSelectedIndex()]);	//調整理由名
					String SetAdjustCom01		= B100_TextControl.Trim(TB_AdjustCom01.getText());				//コメント01
					String SetAdjustCom02		= B100_TextControl.Trim(TB_AdjustCom02.getText());				//コメント02
					String SetAdjustCom03		= B100_TextControl.Trim(TB_AdjustCom03.getText());				//コメント03
					
					int SetQty				= B100_TextControl.TextToInt(TB_Qty.getText());				//調整前数量
					int SetShipPlanQty		= B100_TextControl.TextToInt(TB_ShipPlanQty.getText());		//調整前引当済数
					int SetPossibleQty		= B100_TextControl.TextToInt(TB_PossibleQty.getText());	
					
					int SetAdjustQty		= B100_TextControl.TextToInt(TB_AdjustQty.getText());			//調整数
					int SetEntryMode		= B100_TextControl.TextToInt(TB_EntryMode.getText());
					
					int SetPlAdjustQty		= B100_TextControl.TextToInt(TB_PlAdjustQty.getText());
					int SetCsAdjustQty		= B100_TextControl.TextToInt(TB_CsAdjustQty.getText());
					int SetCtAdjustQty		= B100_TextControl.TextToInt(TB_CtAdjustQty.getText());
					int SetBrAdjustQty		= B100_TextControl.TextToInt(TB_BrAdjustQty.getText());
					
					int SetAfterQty			= B100_TextControl.TextToInt(TB_AfterQty.getText());			//調整後数量
					int SetAfterShipPlanQty	= B100_TextControl.TextToInt(TB_AfterShipPlanQty.getText());	//調整後引当済数
					int SetAfterPossibleQty	= B100_TextControl.TextToInt(TB_AfterPossibleQty.getText());	//調整後出荷可能数
					
					if(0>SetAfterQty||0>SetAfterPossibleQty) {
						JOptionPane.showMessageDialog(null, "調整数が調整可能数を超えておる");
					}else {
						//在庫更新
						Object[][] SetData = new Object[1][Tools100_StockQtyControl.StockQtyControlDataLayout().length];
						
						SetData[0][Tools100_StockQtyControl.ColClCd] 			= SetClCd;
						SetData[0][Tools100_StockQtyControl.ColWhCd] 			= SetWhCd;
						SetData[0][Tools100_StockQtyControl.ColLoc]			= SetLoc;
						SetData[0][Tools100_StockQtyControl.ColItemCd]			= SetItemCd;
						SetData[0][Tools100_StockQtyControl.ColLot] 			= SetLot;
						SetData[0][Tools100_StockQtyControl.ColExpdate] 		= SetExpdate;
						SetData[0][Tools100_StockQtyControl.ColActualDate] 	= SetActualDate;
						SetData[0][Tools100_StockQtyControl.ColControlQty] 	= SetAdjustQty;
						
						Object[][] StockQtyControlErr = Tools100_StockQtyControl.StockQtyControl(SetData) ;
						if(0<StockQtyControlErr.length) {
							String ErrMsg = "";
							boolean EntryFg = false;
							int EntryRow			= 0;
							int BeforeQty			= 0;
							int BeforeShipPlanQty	= 0;
							int BeforePossibleQty	= 0;
							int AdjustQty			= 0;
							int AfterQty			= 0;
							
							
							for(int i01=0;i01<StockQtyControlErr.length;i01++) {
								if("EntryData".equals((String)StockQtyControlErr[i01][Tools100_StockQtyControl.RtColErrType])){
									EntryRow			= (int)StockQtyControlErr[i01][Tools100_StockQtyControl.RtColErrRow];
									BeforeQty			= (int)StockQtyControlErr[i01][Tools100_StockQtyControl.RtColBeforeQty];
									BeforeShipPlanQty	= (int)StockQtyControlErr[i01][Tools100_StockQtyControl.RtColBeforeShipPlanQty];
									BeforePossibleQty	= (int)StockQtyControlErr[i01][Tools100_StockQtyControl.RtColBeforePossibleQty];
									AdjustQty			= (int)StockQtyControlErr[i01][Tools100_StockQtyControl.RtColAdjustQty];
									AfterQty			= (int)StockQtyControlErr[i01][Tools100_StockQtyControl.RtColAfterQty];
									EntryFg = true;
								}else{
									String 	ErrType = (String)StockQtyControlErr[i01][Tools100_StockQtyControl.RtColErrType];
									String 	ErrVol 	= (String)StockQtyControlErr[i01][Tools100_StockQtyControl.RtColErrVol];
									int 	ErrRow	= (int)StockQtyControlErr[i01][Tools100_StockQtyControl.RtColErrRow]+1;
									if(!"".equals(ErrMsg)) {
										ErrMsg=ErrMsg+"\n";
									}
									ErrMsg = ErrMsg+ErrRow+"行目エラー  "+ErrType+":"+ErrVol;
								}
							}
							if(EntryFg) {
								//在庫調整結果を登録する
								int[] AdjustNoRt = Tools100_Adjust.AdjustNoRt(1);
								String now_dtm = B100_DateTimeControl.dtmString2(B100_DateTimeControl.dtm()[1])[1];
								
								Object[][] SetString = {
										 	 {"ClCd"				,"1"	,"0"	,"Key"	,""+SetClCd}				//荷主コード
											,{"WhCd"				,"1"	,"0"	,"Key"	,""+SetWhCd}				//倉庫コード
											,{"AdjustNo"			,"1"	,"0"	,"Key"	,""+AdjustNoRt[0]}			//調整番号
											,{"AdjustReasonCd"		,"1"	,"0"	,""		,""+SetAdjustReason}		//調整理由コード
											,{"AdjustReasonName"	,"1"	,"0"	,""		,""+SetAdjustReasonName}	//調整理由名
											,{"Adjustdate"			,"1"	,"0"	,""		,now_dtm}					//調整日
											,{"Loc"					,"1"	,"0"	,""		,""+SetLoc}					//調整元ロケ
											,{"ItemCd"				,"1"	,"0"	,""		,""+SetItemCd}				//調整元商品CD
											,{"ItemName"			,"1"	,"0"	,""		,""+SetItemName}			//調整元商品名
											,{"Lot"					,"1"	,"0"	,""		,""+SetLot}					//調整元ロット
											,{"ExpDate"				,"1"	,"0"	,""		,""+SetExpdate}				//調整元賞味期限
											,{"ActualDate"			,"1"	,"0"	,""		,""+SetActualDate}			//調整元入荷日
											,{"BeforeQty"			,"1"	,"0"	,""		,""+BeforeQty}				//調整元在庫数
											,{"ShipPlanQty"			,"1"	,"0"	,""		,""+BeforeShipPlanQty}		//調整元引当済数
											,{"PossibleQty"			,"1"	,"0"	,""		,""+BeforePossibleQty}		//調整元出荷可能数
											,{"AdjustQty"			,"1"	,"0"	,""		,""+AdjustQty}				//調整数
											,{"AdjustCom01"			,"1"	,"0"	,""		,""+SetAdjustCom01}			//調整理由コメント01
											,{"AdjustCom02"			,"1"	,"0"	,""		,""+SetAdjustCom02}			//調整理由コメント02
											,{"AdjustCom03"			,"1"	,"0"	,""		,""+SetAdjustCom03}			//調整理由コメント03
											,{"AfterQty"			,"1"	,"0"	,""		,""+AfterQty}				//調整後在庫数
											,{"EntryDate"			,"1"	,"0"	,""		,now_dtm}					//登録日
											,{"UpdateDate"			,"1"	,"0"	,""		,now_dtm}					//更新日
											,{"EntryUser"			,"1"	,"0"	,""		,"(" + A00000_Main.LoginUserId + ")" + A00000_Main.LoginUserName}	//登録者
											,{"UpdateUser"			,"1"	,"0"	,""		,"(" + A00000_Main.LoginUserId + ")" + A00000_Main.LoginUserName}	//更新者
											};
								
								String tgt_table = "WW0016StockAdjust";
								String TgtDB = "WANKO";
								int non_msg_fg = 1;
								
								A100_InsertUpdateSQL.InsertUpdateOneRecord(SetString,tgt_table,TgtDB,non_msg_fg);
								
								SetX=main_fm.getX();
								SetY=main_fm.getY();
								
								Loc_fm.setVisible(false);
								Loc_fm.dispose();
								
								Item_fm.setVisible(false);
								Item_fm.dispose();

								main_fm.setVisible(false);
								main_fm.dispose();
								WT100_Stock_00_Search.StockSearch(0,0);
							}else {
								JOptionPane.showMessageDialog(null, ErrMsg);
							}
						}
					}
					RenewFg = true;
				}
			}
		});
		
		//入荷実績日フォーカス消失時の挙動
		TB_ActualDate.addFocusListener(new FocusAdapter(){
			@Override
			public void focusLost(FocusEvent e){
				if(RenewFg) {
					RenewFg = false;
					String TgtClCd			= B100_TextControl.Trim(B100_DefaultVariable.ClList[1][TB_ClCd.getSelectedIndex()]);	//荷主コード
					String TgtWhCd			= B100_TextControl.Trim(B100_DefaultVariable.WhList[1][TB_WhCd.getSelectedIndex()]);	//倉庫コード
					String TgtLoc			= B100_TextControl.Trim(TB_Loc.getText());							//ロケーション
					String TgtItemCd		= B100_TextControl.Trim(TB_ItemCd.getText());						//商品コード
					String TgtLot			= B100_TextControl.Trim(TB_Lot.getText());							//ロット
					String TgtExpdate		= B100_TextControl.TextToDate(TB_Expdate.getText());				//消費期限
					String TgtActualDate	= B100_TextControl.TextToDate(TB_ActualDate.getText());			//入荷実績日
					
					
					Object[] StockData	= StockDataRt(
							TgtWhCd,
							TgtClCd,
							TgtLoc,
							TgtItemCd,
							TgtLot,
							TgtExpdate,
							TgtActualDate
							);
					
					FramePartsVolSet(
							StockData,
							TB_CtUnitQty,
							TB_CsUnitQty,
							TB_PlUnitQty,
							LB_PlAdjustUnitName,
							LB_CsAdjustUnitName,
							LB_CtAdjustUnitName,
							LB_BrAdjustUnitName,
							TB_ClCd,
							TB_WhCd,
							TB_Loc,
							TB_LocName,
							TB_LocType,
							TB_ItemCd,
							TB_ItemName,
							TB_Lot,
							TB_Expdate,
							TB_ActualDate,
							TB_Qty,
							TB_ShipPlanQty,
							TB_PossibleQty,
							TB_EntryMode,
							TB_AfterQty,
							TB_AfterShipPlanQty,
							TB_AfterPossibleQty,
							TB_BeforePlQty,
							TB_BeforeCsQty,
							TB_BeforeCtQty,
							TB_BeforeBrQty,
							TB_BeforePlUnitName,
							TB_BeforeCsUnitName,
							TB_BeforeCtUnitName,
							TB_BeforeBrUnitName,
							TB_AfterPlQty,
							TB_AfterCsQty,
							TB_AfterCtQty,
							TB_AfterBrQty,
							TB_AfterPlUnitName,
							TB_AfterCsUnitName,
							TB_AfterCtUnitName,
							TB_AfterBrUnitName
							);
					
					AdjustFromQty(
							TB_CtUnitQty,
							TB_CsUnitQty,
							TB_PlUnitQty,
							TB_AdjustQty,
							TB_PlAdjustQty,
							TB_CsAdjustQty,
							TB_CtAdjustQty,
							TB_BrAdjustQty,
							TB_Qty,
							TB_ShipPlanQty,
							TB_PossibleQty,
							TB_LocType,
							TB_AfterQty,
							TB_AfterShipPlanQty,
							TB_AfterPossibleQty,
							TB_BeforePlQty,
							TB_BeforeCsQty,
							TB_BeforeCtQty,
							TB_BeforeBrQty,
							TB_AfterPlQty,
							TB_AfterCsQty,
							TB_AfterCtQty,
							TB_AfterBrQty
							);
					
					AdjustModeControl(
							TB_EntryMode,
							TB_CtUnitQty,
							TB_CsUnitQty,
							TB_PlUnitQty,
							TB_AdjustQty,
							TB_PlAdjustQty,
							TB_CsAdjustQty,
							TB_CtAdjustQty,
							TB_BrAdjustQty
							);
					EntryCheckTB_ColorControl(
							HandoverWhCd,
							HandoverClCd,
							HandoverLoc,
							HandoverItemCd,
							HandoverLot,
							HandoverExpdate,
							HandoverActualDate,
							TB_ClCd,
							TB_WhCd,
							TB_Loc,
							TB_ItemCd,
							TB_Lot,
							TB_Expdate,
							TB_ActualDate,
							TB_Qty,
							TB_ShipPlanQty,
							TB_PossibleQty,
							TB_AfterQty,
							TB_AfterShipPlanQty,
							TB_AfterPossibleQty,
							TB_BeforePlQty,
							TB_BeforeCsQty,
							TB_BeforeCtQty,
							TB_BeforeBrQty,
							TB_AfterPlQty,
							TB_AfterCsQty,
							TB_AfterCtQty,
							TB_AfterBrQty,
							TB_AdjustQty,
							TB_PlAdjustQty,
							TB_CsAdjustQty,
							TB_CtAdjustQty,
							TB_BrAdjustQty
							);
					RenewFg = true;
				}
			}
		});
		
		//賞味期限フォーカス消失時の挙動
		TB_Expdate.addFocusListener(new FocusAdapter(){
			@Override
			public void focusLost(FocusEvent e){
				if(RenewFg) {
					RenewFg = false;
					String TgtClCd			= B100_TextControl.Trim(B100_DefaultVariable.ClList[1][TB_ClCd.getSelectedIndex()]);	//荷主コード
					String TgtWhCd			= B100_TextControl.Trim(B100_DefaultVariable.WhList[1][TB_WhCd.getSelectedIndex()]);	//倉庫コード
					String TgtLoc			= B100_TextControl.Trim(TB_Loc.getText());							//ロケーション
					String TgtItemCd		= B100_TextControl.Trim(TB_ItemCd.getText());						//商品コード
					String TgtLot			= B100_TextControl.Trim(TB_Lot.getText());							//ロット
					String TgtExpdate		= B100_TextControl.TextToDate(TB_Expdate.getText());				//消費期限
					String TgtActualDate	= B100_TextControl.TextToDate(TB_ActualDate.getText());			//入荷実績日
					
					
					Object[] StockData	= StockDataRt(
							TgtWhCd,
							TgtClCd,
							TgtLoc,
							TgtItemCd,
							TgtLot,
							TgtExpdate,
							TgtActualDate
							);
					
					FramePartsVolSet(
							StockData,
							TB_CtUnitQty,
							TB_CsUnitQty,
							TB_PlUnitQty,
							LB_PlAdjustUnitName,
							LB_CsAdjustUnitName,
							LB_CtAdjustUnitName,
							LB_BrAdjustUnitName,
							TB_ClCd,
							TB_WhCd,
							TB_Loc,
							TB_LocName,
							TB_LocType,
							TB_ItemCd,
							TB_ItemName,
							TB_Lot,
							TB_Expdate,
							TB_ActualDate,
							TB_Qty,
							TB_ShipPlanQty,
							TB_PossibleQty,
							TB_EntryMode,
							TB_AfterQty,
							TB_AfterShipPlanQty,
							TB_AfterPossibleQty,
							TB_BeforePlQty,
							TB_BeforeCsQty,
							TB_BeforeCtQty,
							TB_BeforeBrQty,
							TB_BeforePlUnitName,
							TB_BeforeCsUnitName,
							TB_BeforeCtUnitName,
							TB_BeforeBrUnitName,
							TB_AfterPlQty,
							TB_AfterCsQty,
							TB_AfterCtQty,
							TB_AfterBrQty,
							TB_AfterPlUnitName,
							TB_AfterCsUnitName,
							TB_AfterCtUnitName,
							TB_AfterBrUnitName
							);
					
					AdjustFromQty(
							TB_CtUnitQty,
							TB_CsUnitQty,
							TB_PlUnitQty,
							TB_AdjustQty,
							TB_PlAdjustQty,
							TB_CsAdjustQty,
							TB_CtAdjustQty,
							TB_BrAdjustQty,
							TB_Qty,
							TB_ShipPlanQty,
							TB_PossibleQty,
							TB_LocType,
							TB_AfterQty,
							TB_AfterShipPlanQty,
							TB_AfterPossibleQty,
							TB_BeforePlQty,
							TB_BeforeCsQty,
							TB_BeforeCtQty,
							TB_BeforeBrQty,
							TB_AfterPlQty,
							TB_AfterCsQty,
							TB_AfterCtQty,
							TB_AfterBrQty
							);
					
					AdjustModeControl(
							TB_EntryMode,
							TB_CtUnitQty,
							TB_CsUnitQty,
							TB_PlUnitQty,
							TB_AdjustQty,
							TB_PlAdjustQty,
							TB_CsAdjustQty,
							TB_CtAdjustQty,
							TB_BrAdjustQty
							);
					EntryCheckTB_ColorControl(
							HandoverWhCd,
							HandoverClCd,
							HandoverLoc,
							HandoverItemCd,
							HandoverLot,
							HandoverExpdate,
							HandoverActualDate,
							TB_ClCd,
							TB_WhCd,
							TB_Loc,
							TB_ItemCd,
							TB_Lot,
							TB_Expdate,
							TB_ActualDate,
							TB_Qty,
							TB_ShipPlanQty,
							TB_PossibleQty,
							TB_AfterQty,
							TB_AfterShipPlanQty,
							TB_AfterPossibleQty,
							TB_BeforePlQty,
							TB_BeforeCsQty,
							TB_BeforeCtQty,
							TB_BeforeBrQty,
							TB_AfterPlQty,
							TB_AfterCsQty,
							TB_AfterCtQty,
							TB_AfterBrQty,
							TB_AdjustQty,
							TB_PlAdjustQty,
							TB_CsAdjustQty,
							TB_CtAdjustQty,
							TB_BrAdjustQty
							);
					RenewFg = true;
				}
			}
		});
		
		//ロットフォーカス消失時の挙動
		TB_Lot.addFocusListener(new FocusAdapter(){
			@Override
			public void focusLost(FocusEvent e){
				if(RenewFg) {
					RenewFg = false;
					String TgtClCd			= B100_TextControl.Trim(B100_DefaultVariable.ClList[1][TB_ClCd.getSelectedIndex()]);	//荷主コード
					String TgtWhCd			= B100_TextControl.Trim(B100_DefaultVariable.WhList[1][TB_WhCd.getSelectedIndex()]);	//倉庫コード
					String TgtLoc			= B100_TextControl.Trim(TB_Loc.getText());							//ロケーション
					String TgtItemCd		= B100_TextControl.Trim(TB_ItemCd.getText());						//商品コード
					String TgtLot			= B100_TextControl.Trim(TB_Lot.getText());							//ロット
					String TgtExpdate		= B100_TextControl.TextToDate(TB_Expdate.getText());				//消費期限
					String TgtActualDate	= B100_TextControl.TextToDate(TB_ActualDate.getText());			//入荷実績日
					
					
					Object[] StockData	= StockDataRt(
							TgtWhCd,
							TgtClCd,
							TgtLoc,
							TgtItemCd,
							TgtLot,
							TgtExpdate,
							TgtActualDate
							);
					
					FramePartsVolSet(
							StockData,
							TB_CtUnitQty,
							TB_CsUnitQty,
							TB_PlUnitQty,
							LB_PlAdjustUnitName,
							LB_CsAdjustUnitName,
							LB_CtAdjustUnitName,
							LB_BrAdjustUnitName,
							TB_ClCd,
							TB_WhCd,
							TB_Loc,
							TB_LocName,
							TB_LocType,
							TB_ItemCd,
							TB_ItemName,
							TB_Lot,
							TB_Expdate,
							TB_ActualDate,
							TB_Qty,
							TB_ShipPlanQty,
							TB_PossibleQty,
							TB_EntryMode,
							TB_AfterQty,
							TB_AfterShipPlanQty,
							TB_AfterPossibleQty,
							TB_BeforePlQty,
							TB_BeforeCsQty,
							TB_BeforeCtQty,
							TB_BeforeBrQty,
							TB_BeforePlUnitName,
							TB_BeforeCsUnitName,
							TB_BeforeCtUnitName,
							TB_BeforeBrUnitName,
							TB_AfterPlQty,
							TB_AfterCsQty,
							TB_AfterCtQty,
							TB_AfterBrQty,
							TB_AfterPlUnitName,
							TB_AfterCsUnitName,
							TB_AfterCtUnitName,
							TB_AfterBrUnitName
							);
					
					AdjustFromQty(
							TB_CtUnitQty,
							TB_CsUnitQty,
							TB_PlUnitQty,
							TB_AdjustQty,
							TB_PlAdjustQty,
							TB_CsAdjustQty,
							TB_CtAdjustQty,
							TB_BrAdjustQty,
							TB_Qty,
							TB_ShipPlanQty,
							TB_PossibleQty,
							TB_LocType,
							TB_AfterQty,
							TB_AfterShipPlanQty,
							TB_AfterPossibleQty,
							TB_BeforePlQty,
							TB_BeforeCsQty,
							TB_BeforeCtQty,
							TB_BeforeBrQty,
							TB_AfterPlQty,
							TB_AfterCsQty,
							TB_AfterCtQty,
							TB_AfterBrQty
							);
					
					AdjustModeControl(
							TB_EntryMode,
							TB_CtUnitQty,
							TB_CsUnitQty,
							TB_PlUnitQty,
							TB_AdjustQty,
							TB_PlAdjustQty,
							TB_CsAdjustQty,
							TB_CtAdjustQty,
							TB_BrAdjustQty
							);
					EntryCheckTB_ColorControl(
							HandoverWhCd,
							HandoverClCd,
							HandoverLoc,
							HandoverItemCd,
							HandoverLot,
							HandoverExpdate,
							HandoverActualDate,
							TB_ClCd,
							TB_WhCd,
							TB_Loc,
							TB_ItemCd,
							TB_Lot,
							TB_Expdate,
							TB_ActualDate,
							TB_Qty,
							TB_ShipPlanQty,
							TB_PossibleQty,
							TB_AfterQty,
							TB_AfterShipPlanQty,
							TB_AfterPossibleQty,
							TB_BeforePlQty,
							TB_BeforeCsQty,
							TB_BeforeCtQty,
							TB_BeforeBrQty,
							TB_AfterPlQty,
							TB_AfterCsQty,
							TB_AfterCtQty,
							TB_AfterBrQty,
							TB_AdjustQty,
							TB_PlAdjustQty,
							TB_CsAdjustQty,
							TB_CtAdjustQty,
							TB_BrAdjustQty
							);
					RenewFg = true;
				}
			}
		});
		
		//ロケーションコードフォーカス消失時の挙動
		TB_Loc.addFocusListener(new FocusAdapter(){
			@Override
			public void focusLost(FocusEvent e){
				if(RenewFg) {
					RenewFg = false;
					String TgtClCd			= B100_TextControl.Trim(B100_DefaultVariable.ClList[1][TB_ClCd.getSelectedIndex()]);	//荷主コード
					String TgtWhCd			= B100_TextControl.Trim(B100_DefaultVariable.WhList[1][TB_WhCd.getSelectedIndex()]);	//倉庫コード
					String TgtLoc			= B100_TextControl.Trim(TB_Loc.getText());							//ロケーション
					String TgtItemCd		= B100_TextControl.Trim(TB_ItemCd.getText());						//商品コード
					String TgtLot			= B100_TextControl.Trim(TB_Lot.getText());							//ロット
					String TgtExpdate		= B100_TextControl.TextToDate(TB_Expdate.getText());				//消費期限
					String TgtActualDate	= B100_TextControl.TextToDate(TB_ActualDate.getText());			//入荷実績日
					
					
					Object[] StockData	= StockDataRt(
							TgtWhCd,
							TgtClCd,
							TgtLoc,
							TgtItemCd,
							TgtLot,
							TgtExpdate,
							TgtActualDate
							);
					
					FramePartsVolSet(
							StockData,
							TB_CtUnitQty,
							TB_CsUnitQty,
							TB_PlUnitQty,
							LB_PlAdjustUnitName,
							LB_CsAdjustUnitName,
							LB_CtAdjustUnitName,
							LB_BrAdjustUnitName,
							TB_ClCd,
							TB_WhCd,
							TB_Loc,
							TB_LocName,
							TB_LocType,
							TB_ItemCd,
							TB_ItemName,
							TB_Lot,
							TB_Expdate,
							TB_ActualDate,
							TB_Qty,
							TB_ShipPlanQty,
							TB_PossibleQty,
							TB_EntryMode,
							TB_AfterQty,
							TB_AfterShipPlanQty,
							TB_AfterPossibleQty,
							TB_BeforePlQty,
							TB_BeforeCsQty,
							TB_BeforeCtQty,
							TB_BeforeBrQty,
							TB_BeforePlUnitName,
							TB_BeforeCsUnitName,
							TB_BeforeCtUnitName,
							TB_BeforeBrUnitName,
							TB_AfterPlQty,
							TB_AfterCsQty,
							TB_AfterCtQty,
							TB_AfterBrQty,
							TB_AfterPlUnitName,
							TB_AfterCsUnitName,
							TB_AfterCtUnitName,
							TB_AfterBrUnitName
							);
					
					AdjustFromQty(
							TB_CtUnitQty,
							TB_CsUnitQty,
							TB_PlUnitQty,
							TB_AdjustQty,
							TB_PlAdjustQty,
							TB_CsAdjustQty,
							TB_CtAdjustQty,
							TB_BrAdjustQty,
							TB_Qty,
							TB_ShipPlanQty,
							TB_PossibleQty,
							TB_LocType,
							TB_AfterQty,
							TB_AfterShipPlanQty,
							TB_AfterPossibleQty,
							TB_BeforePlQty,
							TB_BeforeCsQty,
							TB_BeforeCtQty,
							TB_BeforeBrQty,
							TB_AfterPlQty,
							TB_AfterCsQty,
							TB_AfterCtQty,
							TB_AfterBrQty
							);
					
					AdjustModeControl(
							TB_EntryMode,
							TB_CtUnitQty,
							TB_CsUnitQty,
							TB_PlUnitQty,
							TB_AdjustQty,
							TB_PlAdjustQty,
							TB_CsAdjustQty,
							TB_CtAdjustQty,
							TB_BrAdjustQty
							);
					EntryCheckTB_ColorControl(
							HandoverWhCd,
							HandoverClCd,
							HandoverLoc,
							HandoverItemCd,
							HandoverLot,
							HandoverExpdate,
							HandoverActualDate,
							TB_ClCd,
							TB_WhCd,
							TB_Loc,
							TB_ItemCd,
							TB_Lot,
							TB_Expdate,
							TB_ActualDate,
							TB_Qty,
							TB_ShipPlanQty,
							TB_PossibleQty,
							TB_AfterQty,
							TB_AfterShipPlanQty,
							TB_AfterPossibleQty,
							TB_BeforePlQty,
							TB_BeforeCsQty,
							TB_BeforeCtQty,
							TB_BeforeBrQty,
							TB_AfterPlQty,
							TB_AfterCsQty,
							TB_AfterCtQty,
							TB_AfterBrQty,
							TB_AdjustQty,
							TB_PlAdjustQty,
							TB_CsAdjustQty,
							TB_CtAdjustQty,
							TB_BrAdjustQty
							);
					RenewFg = true;
				}
			}
		});
		
		//商品コードフォーカス消失時の挙動
		TB_ItemCd.addFocusListener(new FocusAdapter(){
			@Override
			public void focusLost(FocusEvent e){
				if(RenewFg) {
					RenewFg = false;
					String TgtClCd			= B100_TextControl.Trim(B100_DefaultVariable.ClList[1][TB_ClCd.getSelectedIndex()]);	//荷主コード
					String TgtWhCd			= B100_TextControl.Trim(B100_DefaultVariable.WhList[1][TB_WhCd.getSelectedIndex()]);	//倉庫コード
					String TgtLoc			= B100_TextControl.Trim(TB_Loc.getText());							//ロケーション
					String TgtItemCd		= B100_TextControl.Trim(TB_ItemCd.getText());						//商品コード
					String TgtLot			= B100_TextControl.Trim(TB_Lot.getText());							//ロット
					String TgtExpdate		= B100_TextControl.TextToDate(TB_Expdate.getText());				//消費期限
					String TgtActualDate	= B100_TextControl.TextToDate(TB_ActualDate.getText());			//入荷実績日
					
					
					Object[] StockData	= StockDataRt(
							TgtWhCd,
							TgtClCd,
							TgtLoc,
							TgtItemCd,
							TgtLot,
							TgtExpdate,
							TgtActualDate
							);
					
					FramePartsVolSet(
							StockData,
							TB_CtUnitQty,
							TB_CsUnitQty,
							TB_PlUnitQty,
							LB_PlAdjustUnitName,
							LB_CsAdjustUnitName,
							LB_CtAdjustUnitName,
							LB_BrAdjustUnitName,
							TB_ClCd,
							TB_WhCd,
							TB_Loc,
							TB_LocName,
							TB_LocType,
							TB_ItemCd,
							TB_ItemName,
							TB_Lot,
							TB_Expdate,
							TB_ActualDate,
							TB_Qty,
							TB_ShipPlanQty,
							TB_PossibleQty,
							TB_EntryMode,
							TB_AfterQty,
							TB_AfterShipPlanQty,
							TB_AfterPossibleQty,
							TB_BeforePlQty,
							TB_BeforeCsQty,
							TB_BeforeCtQty,
							TB_BeforeBrQty,
							TB_BeforePlUnitName,
							TB_BeforeCsUnitName,
							TB_BeforeCtUnitName,
							TB_BeforeBrUnitName,
							TB_AfterPlQty,
							TB_AfterCsQty,
							TB_AfterCtQty,
							TB_AfterBrQty,
							TB_AfterPlUnitName,
							TB_AfterCsUnitName,
							TB_AfterCtUnitName,
							TB_AfterBrUnitName
							);
					
					AdjustFromQty(
							TB_CtUnitQty,
							TB_CsUnitQty,
							TB_PlUnitQty,
							TB_AdjustQty,
							TB_PlAdjustQty,
							TB_CsAdjustQty,
							TB_CtAdjustQty,
							TB_BrAdjustQty,
							TB_Qty,
							TB_ShipPlanQty,
							TB_PossibleQty,
							TB_LocType,
							TB_AfterQty,
							TB_AfterShipPlanQty,
							TB_AfterPossibleQty,
							TB_BeforePlQty,
							TB_BeforeCsQty,
							TB_BeforeCtQty,
							TB_BeforeBrQty,
							TB_AfterPlQty,
							TB_AfterCsQty,
							TB_AfterCtQty,
							TB_AfterBrQty
							);
					
					AdjustModeControl(
							TB_EntryMode,
							TB_CtUnitQty,
							TB_CsUnitQty,
							TB_PlUnitQty,
							TB_AdjustQty,
							TB_PlAdjustQty,
							TB_CsAdjustQty,
							TB_CtAdjustQty,
							TB_BrAdjustQty
							);
					EntryCheckTB_ColorControl(
							HandoverWhCd,
							HandoverClCd,
							HandoverLoc,
							HandoverItemCd,
							HandoverLot,
							HandoverExpdate,
							HandoverActualDate,
							TB_ClCd,
							TB_WhCd,
							TB_Loc,
							TB_ItemCd,
							TB_Lot,
							TB_Expdate,
							TB_ActualDate,
							TB_Qty,
							TB_ShipPlanQty,
							TB_PossibleQty,
							TB_AfterQty,
							TB_AfterShipPlanQty,
							TB_AfterPossibleQty,
							TB_BeforePlQty,
							TB_BeforeCsQty,
							TB_BeforeCtQty,
							TB_BeforeBrQty,
							TB_AfterPlQty,
							TB_AfterCsQty,
							TB_AfterCtQty,
							TB_AfterBrQty,
							TB_AdjustQty,
							TB_PlAdjustQty,
							TB_CsAdjustQty,
							TB_CtAdjustQty,
							TB_BrAdjustQty
							);
					RenewFg = true;
				}
			}
		});
		
		//調整数フォーカス消失時の挙動
		TB_AdjustQty.addFocusListener(new FocusAdapter(){
			@Override
			public void focusLost(FocusEvent e){
				if(RenewFg) {
					RenewFg = false;
					AdjustFromQty(
							TB_CtUnitQty,
							TB_CsUnitQty,
							TB_PlUnitQty,
							TB_AdjustQty,
							TB_PlAdjustQty,
							TB_CsAdjustQty,
							TB_CtAdjustQty,
							TB_BrAdjustQty,
							TB_Qty,
							TB_ShipPlanQty,
							TB_PossibleQty,
							TB_LocType,
							TB_AfterQty,
							TB_AfterShipPlanQty,
							TB_AfterPossibleQty,
							TB_BeforePlQty,
							TB_BeforeCsQty,
							TB_BeforeCtQty,
							TB_BeforeBrQty,
							TB_AfterPlQty,
							TB_AfterCsQty,
							TB_AfterCtQty,
							TB_AfterBrQty
							);
					EntryCheckTB_ColorControl(
							HandoverWhCd,
							HandoverClCd,
							HandoverLoc,
							HandoverItemCd,
							HandoverLot,
							HandoverExpdate,
							HandoverActualDate,
							TB_ClCd,
							TB_WhCd,
							TB_Loc,
							TB_ItemCd,
							TB_Lot,
							TB_Expdate,
							TB_ActualDate,
							TB_Qty,
							TB_ShipPlanQty,
							TB_PossibleQty,
							TB_AfterQty,
							TB_AfterShipPlanQty,
							TB_AfterPossibleQty,
							TB_BeforePlQty,
							TB_BeforeCsQty,
							TB_BeforeCtQty,
							TB_BeforeBrQty,
							TB_AfterPlQty,
							TB_AfterCsQty,
							TB_AfterCtQty,
							TB_AfterBrQty,
							TB_AdjustQty,
							TB_PlAdjustQty,
							TB_CsAdjustQty,
							TB_CtAdjustQty,
							TB_BrAdjustQty
							);
					RenewFg = true;
				}
			}
		});
		//調整数パレットフォーカス消失時の挙動
		TB_PlAdjustQty.addFocusListener(new FocusAdapter(){
			@Override
			public void focusLost(FocusEvent e){
				if(RenewFg) {
					RenewFg = false;
					AdjustFromUnitQty(
							TB_CtUnitQty,
							TB_CsUnitQty,
							TB_PlUnitQty,
							TB_AdjustQty,
							TB_PlAdjustQty,
							TB_CsAdjustQty,
							TB_CtAdjustQty,
							TB_BrAdjustQty,
							TB_Qty,
							TB_ShipPlanQty,
							TB_PossibleQty,
							TB_LocType,
							TB_AfterQty,
							TB_AfterShipPlanQty,
							TB_AfterPossibleQty,
							TB_BeforePlQty,
							TB_BeforeCsQty,
							TB_BeforeCtQty,
							TB_BeforeBrQty,
							TB_AfterPlQty,
							TB_AfterCsQty,
							TB_AfterCtQty,
							TB_AfterBrQty
							);
					EntryCheckTB_ColorControl(
							HandoverWhCd,
							HandoverClCd,
							HandoverLoc,
							HandoverItemCd,
							HandoverLot,
							HandoverExpdate,
							HandoverActualDate,
							TB_ClCd,
							TB_WhCd,
							TB_Loc,
							TB_ItemCd,
							TB_Lot,
							TB_Expdate,
							TB_ActualDate,
							TB_Qty,
							TB_ShipPlanQty,
							TB_PossibleQty,
							TB_AfterQty,
							TB_AfterShipPlanQty,
							TB_AfterPossibleQty,
							TB_BeforePlQty,
							TB_BeforeCsQty,
							TB_BeforeCtQty,
							TB_BeforeBrQty,
							TB_AfterPlQty,
							TB_AfterCsQty,
							TB_AfterCtQty,
							TB_AfterBrQty,
							TB_AdjustQty,
							TB_PlAdjustQty,
							TB_CsAdjustQty,
							TB_CtAdjustQty,
							TB_BrAdjustQty
							);
					RenewFg = true;
				}
			}
		});
		//調整数ケースフォーカス消失時の挙動
		TB_CsAdjustQty.addFocusListener(new FocusAdapter(){
			@Override
			public void focusLost(FocusEvent e){
				if(RenewFg) {
					RenewFg = false;
					AdjustFromUnitQty(
							TB_CtUnitQty,
							TB_CsUnitQty,
							TB_PlUnitQty,
							TB_AdjustQty,
							TB_PlAdjustQty,
							TB_CsAdjustQty,
							TB_CtAdjustQty,
							TB_BrAdjustQty,
							TB_Qty,
							TB_ShipPlanQty,
							TB_PossibleQty,
							TB_LocType,
							TB_AfterQty,
							TB_AfterShipPlanQty,
							TB_AfterPossibleQty,
							TB_BeforePlQty,
							TB_BeforeCsQty,
							TB_BeforeCtQty,
							TB_BeforeBrQty,
							TB_AfterPlQty,
							TB_AfterCsQty,
							TB_AfterCtQty,
							TB_AfterBrQty
							);
					EntryCheckTB_ColorControl(
							HandoverWhCd,
							HandoverClCd,
							HandoverLoc,
							HandoverItemCd,
							HandoverLot,
							HandoverExpdate,
							HandoverActualDate,
							TB_ClCd,
							TB_WhCd,
							TB_Loc,
							TB_ItemCd,
							TB_Lot,
							TB_Expdate,
							TB_ActualDate,
							TB_Qty,
							TB_ShipPlanQty,
							TB_PossibleQty,
							TB_AfterQty,
							TB_AfterShipPlanQty,
							TB_AfterPossibleQty,
							TB_BeforePlQty,
							TB_BeforeCsQty,
							TB_BeforeCtQty,
							TB_BeforeBrQty,
							TB_AfterPlQty,
							TB_AfterCsQty,
							TB_AfterCtQty,
							TB_AfterBrQty,
							TB_AdjustQty,
							TB_PlAdjustQty,
							TB_CsAdjustQty,
							TB_CtAdjustQty,
							TB_BrAdjustQty
							);
					RenewFg = true;
				}
			}
		});
		//調整数カートンフォーカス消失時の挙動
		TB_CtAdjustQty.addFocusListener(new FocusAdapter(){
			@Override
			public void focusLost(FocusEvent e){
				if(RenewFg) {
					RenewFg = false;
					AdjustFromUnitQty(
							TB_CtUnitQty,
							TB_CsUnitQty,
							TB_PlUnitQty,
							TB_AdjustQty,
							TB_PlAdjustQty,
							TB_CsAdjustQty,
							TB_CtAdjustQty,
							TB_BrAdjustQty,
							TB_Qty,
							TB_ShipPlanQty,
							TB_PossibleQty,
							TB_LocType,
							TB_AfterQty,
							TB_AfterShipPlanQty,
							TB_AfterPossibleQty,
							TB_BeforePlQty,
							TB_BeforeCsQty,
							TB_BeforeCtQty,
							TB_BeforeBrQty,
							TB_AfterPlQty,
							TB_AfterCsQty,
							TB_AfterCtQty,
							TB_AfterBrQty
							);
					EntryCheckTB_ColorControl(
							HandoverWhCd,
							HandoverClCd,
							HandoverLoc,
							HandoverItemCd,
							HandoverLot,
							HandoverExpdate,
							HandoverActualDate,
							TB_ClCd,
							TB_WhCd,
							TB_Loc,
							TB_ItemCd,
							TB_Lot,
							TB_Expdate,
							TB_ActualDate,
							TB_Qty,
							TB_ShipPlanQty,
							TB_PossibleQty,
							TB_AfterQty,
							TB_AfterShipPlanQty,
							TB_AfterPossibleQty,
							TB_BeforePlQty,
							TB_BeforeCsQty,
							TB_BeforeCtQty,
							TB_BeforeBrQty,
							TB_AfterPlQty,
							TB_AfterCsQty,
							TB_AfterCtQty,
							TB_AfterBrQty,
							TB_AdjustQty,
							TB_PlAdjustQty,
							TB_CsAdjustQty,
							TB_CtAdjustQty,
							TB_BrAdjustQty
							);
					RenewFg = true;
				}
			}
		});
		//調整数バラフォーカス消失時の挙動
		TB_BrAdjustQty.addFocusListener(new FocusAdapter(){
			@Override
			public void focusLost(FocusEvent e){
				if(RenewFg) {
					RenewFg = false;
					AdjustFromUnitQty(
							TB_CtUnitQty,
							TB_CsUnitQty,
							TB_PlUnitQty,
							TB_AdjustQty,
							TB_PlAdjustQty,
							TB_CsAdjustQty,
							TB_CtAdjustQty,
							TB_BrAdjustQty,
							TB_Qty,
							TB_ShipPlanQty,
							TB_PossibleQty,
							TB_LocType,
							TB_AfterQty,
							TB_AfterShipPlanQty,
							TB_AfterPossibleQty,
							TB_BeforePlQty,
							TB_BeforeCsQty,
							TB_BeforeCtQty,
							TB_BeforeBrQty,
							TB_AfterPlQty,
							TB_AfterCsQty,
							TB_AfterCtQty,
							TB_AfterBrQty
							);
					EntryCheckTB_ColorControl(
							HandoverWhCd,
							HandoverClCd,
							HandoverLoc,
							HandoverItemCd,
							HandoverLot,
							HandoverExpdate,
							HandoverActualDate,
							TB_ClCd,
							TB_WhCd,
							TB_Loc,
							TB_ItemCd,
							TB_Lot,
							TB_Expdate,
							TB_ActualDate,
							TB_Qty,
							TB_ShipPlanQty,
							TB_PossibleQty,
							TB_AfterQty,
							TB_AfterShipPlanQty,
							TB_AfterPossibleQty,
							TB_BeforePlQty,
							TB_BeforeCsQty,
							TB_BeforeCtQty,
							TB_BeforeBrQty,
							TB_AfterPlQty,
							TB_AfterCsQty,
							TB_AfterCtQty,
							TB_AfterBrQty,
							TB_AdjustQty,
							TB_PlAdjustQty,
							TB_CsAdjustQty,
							TB_CtAdjustQty,
							TB_BrAdjustQty
							);
					RenewFg = true;
				}
			}
		});
		
		TB_EntryMode.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					AdjustModeControl(
							TB_EntryMode,
							TB_CtUnitQty,
							TB_CsUnitQty,
							TB_PlUnitQty,
							TB_AdjustQty,
							TB_PlAdjustQty,
							TB_CsAdjustQty,
							TB_CtAdjustQty,
							TB_BrAdjustQty
							);
					RenewFg = true;
				}
			}
		});
		
		//EXITボタン押下時の挙動
		exit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();
				
				Loc_fm.setVisible(false);
				Loc_fm.dispose();
				
				Item_fm.setVisible(false);
				Item_fm.dispose();

				main_fm.setVisible(false);
				main_fm.dispose();
				WT100_Stock_00_Search.StockSearch(0,0);
			}
		});
	}
	
	private static void EntryCheckTB_ColorControl(
			String HandoverWhCd,
			String HandoverClCd,
			String HandoverLoc,
			String HandoverItemCd,
			String HandoverLot,
			String HandoverExpdate,
			String HandoverActualDate,
			JComboBox TB_ClCd,
			JComboBox TB_WhCd,
			JTextField TB_Loc,
			JTextField TB_ItemCd,
			JTextField TB_Lot,
			JFormattedTextField TB_Expdate,
			JFormattedTextField TB_ActualDate,
			JFormattedTextField TB_Qty,
			JFormattedTextField TB_ShipPlanQty,
			JFormattedTextField TB_PossibleQty,
			JFormattedTextField TB_AfterQty,
			JFormattedTextField TB_AfterShipPlanQty,
			JFormattedTextField TB_AfterPossibleQty,
			JFormattedTextField TB_BeforePlQty,
			JFormattedTextField TB_BeforeCsQty,
			JFormattedTextField TB_BeforeCtQty,
			JFormattedTextField TB_BeforeBrQty,
			JFormattedTextField TB_AfterPlQty,
			JFormattedTextField TB_AfterCsQty,
			JFormattedTextField TB_AfterCtQty,
			JFormattedTextField TB_AfterBrQty,
			JFormattedTextField TB_AdjustQty,
			JFormattedTextField TB_PlAdjustQty,
			JFormattedTextField TB_CsAdjustQty,
			JFormattedTextField TB_CtAdjustQty,
			JFormattedTextField TB_BrAdjustQty
			) {
		String TgtWhCd			= B100_TextControl.Trim(B100_DefaultVariable.WhList[1][TB_WhCd.getSelectedIndex()]);	//倉庫コード
		String TgtClCd			= B100_TextControl.Trim(B100_DefaultVariable.ClList[1][TB_ClCd.getSelectedIndex()]);	//荷主コード
		String TgtLoc			= B100_TextControl.Trim(TB_Loc.getText());
		String TgtItemCd		= B100_TextControl.Trim(TB_ItemCd.getText());
		String TgtLot			= B100_TextControl.Trim(TB_Lot.getText());
		String TgtExpdate		= B100_TextControl.TextToDate(TB_Expdate.getText());
		String TgtActualDate	= B100_TextControl.TextToDate(TB_ActualDate.getText());
		int TgtQty				= B100_TextControl.TextToInt(TB_Qty.getText());
		int TgtShipPlanQty		= B100_TextControl.TextToInt(TB_ShipPlanQty.getText());
		int TgtPossibleQty		= B100_TextControl.TextToInt(TB_PossibleQty.getText());
		int TgtAfterQty			= B100_TextControl.TextToInt(TB_AfterQty.getText());
		int TgtAfterShipPlanQty	= B100_TextControl.TextToInt(TB_AfterShipPlanQty.getText());
		int TgtAfterPossibleQty	= B100_TextControl.TextToInt(TB_AfterPossibleQty.getText());
		int TgtBeforePlQty		= B100_TextControl.TextToInt(TB_BeforePlQty.getText());
		int TgtBeforeCsQty		= B100_TextControl.TextToInt(TB_BeforeCsQty.getText());
		int TgtBeforeCtQty		= B100_TextControl.TextToInt(TB_BeforeCtQty.getText());
		int TgtBeforeBrQty		= B100_TextControl.TextToInt(TB_BeforeBrQty.getText());
		int TgtAfterPlQty		= B100_TextControl.TextToInt(TB_AfterPlQty.getText());
		int TgtAfterCsQty		= B100_TextControl.TextToInt(TB_AfterCsQty.getText());
		int TgtAfterCtQty		= B100_TextControl.TextToInt(TB_AfterCtQty.getText());
		int TgtAfterBrQty		= B100_TextControl.TextToInt(TB_AfterBrQty.getText());
		int TgtAdjustQty		= B100_TextControl.TextToInt(TB_AdjustQty.getText());
		int TgtPlAdjustQty		= B100_TextControl.TextToInt(TB_PlAdjustQty.getText());
		int TgtCsAdjustQty		= B100_TextControl.TextToInt(TB_CsAdjustQty.getText());
		int TgtCtAdjustQty		= B100_TextControl.TextToInt(TB_CtAdjustQty.getText());
		int TgtBrAdjustQty		= B100_TextControl.TextToInt(TB_BrAdjustQty.getText());
		
		TB_WhCd.setForeground(B100_FrameParts.SelectColer("Black"));
		TB_ClCd.setForeground(B100_FrameParts.SelectColer("Black"));
		TB_Loc.setForeground(B100_FrameParts.SelectColer("Black"));
		TB_ItemCd.setForeground(B100_FrameParts.SelectColer("Black"));
		TB_Lot.setForeground(B100_FrameParts.SelectColer("Black"));
		TB_Expdate.setForeground(B100_FrameParts.SelectColer("Black"));
		TB_ActualDate.setForeground(B100_FrameParts.SelectColer("Black"));
		
		
		TB_AfterQty.setForeground(B100_FrameParts.SelectColer("Black"));
		TB_AfterShipPlanQty.setForeground(B100_FrameParts.SelectColer("Black"));
		TB_AfterPossibleQty.setForeground(B100_FrameParts.SelectColer("Black"));
		TB_AfterPlQty.setForeground(B100_FrameParts.SelectColer("Black"));
		TB_AfterCsQty.setForeground(B100_FrameParts.SelectColer("Black"));
		TB_AfterCtQty.setForeground(B100_FrameParts.SelectColer("Black"));
		TB_AfterBrQty.setForeground(B100_FrameParts.SelectColer("Black"));
		
		TB_AdjustQty.setForeground(B100_FrameParts.SelectColer("Black"));
		TB_PlAdjustQty.setForeground(B100_FrameParts.SelectColer("Black"));
		TB_CsAdjustQty.setForeground(B100_FrameParts.SelectColer("Black"));
		TB_CtAdjustQty.setForeground(B100_FrameParts.SelectColer("Black"));
		TB_BrAdjustQty.setForeground(B100_FrameParts.SelectColer("Black"));
		
		
		if(null!=HandoverWhCd && !HandoverWhCd.equals(TgtWhCd)){
			TB_WhCd.setForeground(B100_FrameParts.SelectColer("Red"));
		}
		if(null!=HandoverClCd && !HandoverClCd.equals(TgtClCd)){
			TB_ClCd.setForeground(B100_FrameParts.SelectColer("Red"));
		}
		if(null!=HandoverLoc && !HandoverLoc.equals(TgtLoc)){
			TB_Loc.setForeground(B100_FrameParts.SelectColer("Red"));
		}
		if(null!=HandoverItemCd && !HandoverItemCd.equals(TgtItemCd)){
			TB_ItemCd.setForeground(B100_FrameParts.SelectColer("Red"));
		}
		if(null!=HandoverLot && !HandoverLot.equals(TgtLot)){
			TB_Lot.setForeground(B100_FrameParts.SelectColer("Red"));
		}
		if(null!=HandoverExpdate && !HandoverExpdate.equals(TgtExpdate)){
			TB_Expdate.setForeground(B100_FrameParts.SelectColer("Red"));
		}
		if(null!=HandoverActualDate && !HandoverActualDate.equals(TgtActualDate)){
			TB_ActualDate.setForeground(B100_FrameParts.SelectColer("Red"));
		}
		
		if(TgtQty!=TgtAfterQty){
			TB_AfterQty.setForeground(B100_FrameParts.SelectColer("Red"));
		}
		if(TgtShipPlanQty!=TgtAfterShipPlanQty){
			TB_AfterShipPlanQty.setForeground(B100_FrameParts.SelectColer("Red"));
		}
		if(TgtPossibleQty!=TgtAfterPossibleQty){
			TB_AfterPossibleQty.setForeground(B100_FrameParts.SelectColer("Red"));
		}
		if(TgtBeforePlQty!=TgtAfterPlQty){
			TB_AfterPlQty.setForeground(B100_FrameParts.SelectColer("Red"));
		}
		if(TgtBeforeCsQty!=TgtAfterCsQty){
			TB_AfterCsQty.setForeground(B100_FrameParts.SelectColer("Red"));
		}
		if(TgtBeforeCtQty!=TgtAfterCtQty){
			TB_AfterCtQty.setForeground(B100_FrameParts.SelectColer("Red"));
		}
		if(TgtBeforeBrQty!=TgtAfterBrQty){
			TB_AfterBrQty.setForeground(B100_FrameParts.SelectColer("Red"));
		}
		
		if(0!=TgtAdjustQty){
			TB_AdjustQty.setForeground(B100_FrameParts.SelectColer("Red"));
		}
		if(0!=TgtPlAdjustQty){
			TB_PlAdjustQty.setForeground(B100_FrameParts.SelectColer("Red"));
		}
		if(0!=TgtCsAdjustQty){
			TB_CsAdjustQty.setForeground(B100_FrameParts.SelectColer("Red"));
		}
		if(0!=TgtCtAdjustQty){
			TB_CtAdjustQty.setForeground(B100_FrameParts.SelectColer("Red"));
		}
		if(0!=TgtBrAdjustQty){
			TB_BrAdjustQty.setForeground(B100_FrameParts.SelectColer("Red"));
		}
	}
	
	
	
	private static void FramePartsVolSet(
			Object[] StockData,
			JFormattedTextField TB_CtUnitQty,
			JFormattedTextField TB_CsUnitQty,
			JFormattedTextField TB_PlUnitQty,
			JLabel LB_PlAdjustUnitName,
			JLabel LB_CsAdjustUnitName,
			JLabel LB_CtAdjustUnitName,
			JLabel LB_BrAdjustUnitName,
			JComboBox TB_ClCd,
			JComboBox TB_WhCd,
			JTextField TB_Loc,
			JTextField TB_LocName,
			JComboBox TB_LocType,
			JTextField TB_ItemCd,
			JTextField TB_ItemName,
			JTextField TB_Lot,
			JFormattedTextField TB_Expdate,
			JFormattedTextField TB_ActualDate,
			JFormattedTextField TB_Qty,
			JFormattedTextField TB_ShipPlanQty,
			JFormattedTextField TB_PossibleQty,
			JCheckBox TB_EntryMode,
			JFormattedTextField TB_AfterQty,
			JFormattedTextField TB_AfterShipPlanQty,
			JFormattedTextField TB_AfterPossibleQty,
			JFormattedTextField TB_BeforePlQty,
			JFormattedTextField TB_BeforeCsQty,
			JFormattedTextField TB_BeforeCtQty,
			JFormattedTextField TB_BeforeBrQty,
			JLabel TB_BeforePlUnitName,
			JLabel TB_BeforeCsUnitName,
			JLabel TB_BeforeCtUnitName,
			JLabel TB_BeforeBrUnitName,
			JFormattedTextField TB_AfterPlQty,
			JFormattedTextField TB_AfterCsQty,
			JFormattedTextField TB_AfterCtQty,
			JFormattedTextField TB_AfterBrQty,
			JLabel TB_AfterPlUnitName,
			JLabel TB_AfterCsUnitName,
			JLabel TB_AfterCtUnitName,
			JLabel TB_AfterBrUnitName
			) {
		NumberFormat ni = NumberFormat.getNumberInstance();
		TB_CtUnitQty		.setText(""	+ni.format((int)StockData[T100_StockRt.ColCtUnitQty]));
		TB_CsUnitQty		.setText(""	+ni.format((int)StockData[T100_StockRt.ColCsUnitQty]));
		TB_PlUnitQty		.setText(""	+ni.format((int)StockData[T100_StockRt.ColPlUnitQty]));
		LB_PlAdjustUnitName	.setText((String)StockData[T100_StockRt.ColPlUnitName]);
		LB_CsAdjustUnitName	.setText((String)StockData[T100_StockRt.ColCsUnitName]);
		LB_CtAdjustUnitName	.setText((String)StockData[T100_StockRt.ColCtUnitName]);
		LB_BrAdjustUnitName	.setText((String)StockData[T100_StockRt.ColUnitName]);
		TB_Loc				.setText((String)StockData[T100_StockRt.ColLoc]);
		TB_LocName			.setText((String)StockData[T100_StockRt.ColLocName]);
		TB_ItemCd			.setText((String)StockData[T100_StockRt.ColItemCd]);
		TB_ItemName			.setText((String)StockData[T100_StockRt.ColItemName01]);
		TB_Lot				.setText((String)StockData[T100_StockRt.ColLot]);
		TB_Expdate			.setText((String)StockData[T100_StockRt.ColExpdate]);
		TB_ActualDate		.setText((String)StockData[T100_StockRt.ColActualDate]);
		TB_Qty				.setText(""+ni.format((int)StockData[T100_StockRt.ColQty]));
		TB_ShipPlanQty		.setText(""+ni.format((int)StockData[T100_StockRt.ColShipPlanQty]));
		TB_PossibleQty		.setText(""+ni.format((int)StockData[T100_StockRt.ColPossibleQty]));
		TB_AfterQty			.setText(""+ni.format((int)StockData[T100_StockRt.ColQty]));
		TB_AfterShipPlanQty	.setText(""+ni.format((int)StockData[T100_StockRt.ColShipPlanQty]));
		TB_AfterPossibleQty	.setText(""+ni.format((int)StockData[T100_StockRt.ColPossibleQty]));
		
		TB_ClCd				.setSelectedIndex(	B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.ClList[1]	,(String)StockData[T100_StockRt.ColClCd] ,true) );		//荷主コード
		TB_WhCd				.setSelectedIndex(	B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.ClList[1]	,(String)StockData[T100_StockRt.ColWhCd] ,true) );		//倉庫コード
		TB_LocType			.setSelectedIndex(	B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.LocType[1]	,(int)StockData[T100_StockRt.ColType]+"" ,true) );		//ロケタイプ
		
		//入力可能なテキストBOX背景色指定
		TB_Loc		.setBackground(B100_FrameParts.SelectColer("Entry"));
		TB_ItemCd	.setBackground(B100_FrameParts.SelectColer("Entry"));
		TB_Lot		.setBackground(B100_FrameParts.SelectColer("Entry"));
		TB_Expdate	.setBackground(B100_FrameParts.SelectColer("Entry"));
		
		//入力不可テキストBOX背景色設定
		TB_LocName	.setBackground(B100_FrameParts.SelectColer("NoEntry"));
		TB_ItemName	.setBackground(B100_FrameParts.SelectColer("NoEntry"));
		
		//入荷日管理しないなら入荷実績日操作拒否
		if(B100_DefaultVariable.ActualDateUnControl) {
			TB_ActualDate.setEditable(false);
			TB_ActualDate.setBackground(B100_FrameParts.SelectColer("NoEntry"));
		}else {
			TB_ActualDate.setBackground(B100_FrameParts.SelectColer("Entry"));
		}
		
		//荷姿入数設定されていなければ荷姿調整拒否
		if(0>=(int)StockData[T100_StockRt.ColCtUnitQty]&&0>=(int)StockData[T100_StockRt.ColCsUnitQty]&&0>=(int)StockData[T100_StockRt.ColPlUnitQty]) {
			TB_EntryMode.setSelected(false);
			TB_EntryMode.setEnabled(false);
		}else {
			TB_EntryMode.setSelected(false);
			TB_EntryMode.setEnabled(true);
		}
		int Qty		= (int)StockData[T100_StockRt.ColQty];
		int PlQty	= 0;
		int CsQty	= 0;
		int CtQty	= 0;
		int BrQty	= (int)StockData[T100_StockRt.ColQty];
		
		if(0<(int)StockData[T100_StockRt.ColPlUnitQty]) {
			PlQty = (int)(BrQty/((int)StockData[T100_StockRt.ColPlUnitQty]));
			BrQty = (int)(BrQty%((int)StockData[T100_StockRt.ColPlUnitQty]));
		}
		if(0<(int)StockData[T100_StockRt.ColCsUnitQty]) {
			CsQty = (int)(BrQty/((int)StockData[T100_StockRt.ColCsUnitQty]));
			BrQty = (int)(BrQty%((int)StockData[T100_StockRt.ColCsUnitQty]));
		}
		if(0<(int)StockData[T100_StockRt.ColCtUnitQty]) {
			CtQty = (int)(BrQty/((int)StockData[T100_StockRt.ColCtUnitQty]));
			BrQty = (int)(BrQty%((int)StockData[T100_StockRt.ColCtUnitQty]));
		}
		
		TB_BeforePlQty.setText(""+ni.format(PlQty));
		TB_BeforeCsQty.setText(""+ni.format(CsQty));
		TB_BeforeCtQty.setText(""+ni.format(CtQty));
		TB_BeforeBrQty.setText(""+ni.format(BrQty));
		TB_BeforePlUnitName.setText((String)StockData[T100_StockRt.ColPlUnitName]);
		TB_BeforeCsUnitName.setText((String)StockData[T100_StockRt.ColCsUnitName]);
		TB_BeforeCtUnitName.setText((String)StockData[T100_StockRt.ColCtUnitName]);
		TB_BeforeBrUnitName.setText((String)StockData[T100_StockRt.ColUnitName]);
		
		TB_AfterPlQty.setText(""+ni.format(PlQty));
		TB_AfterCsQty.setText(""+ni.format(CsQty));
		TB_AfterCtQty.setText(""+ni.format(CtQty));
		TB_AfterBrQty.setText(""+ni.format(BrQty));
		TB_AfterPlUnitName.setText((String)StockData[T100_StockRt.ColPlUnitName]);
		TB_AfterCsUnitName.setText((String)StockData[T100_StockRt.ColCsUnitName]);
		TB_AfterCtUnitName.setText((String)StockData[T100_StockRt.ColCtUnitName]);
		TB_AfterBrUnitName.setText((String)StockData[T100_StockRt.ColUnitName]);
	}
	
	private static void AdjustModeControl(
			JCheckBox TB_EntryMode,
			JFormattedTextField TB_CtUnitQty,
			JFormattedTextField TB_CsUnitQty,
			JFormattedTextField TB_PlUnitQty,
			JFormattedTextField TB_AdjustQty,
			JFormattedTextField TB_PlAdjustQty,
			JFormattedTextField TB_CsAdjustQty,
			JFormattedTextField TB_CtAdjustQty,
			JFormattedTextField TB_BrAdjustQty
			) {
		int  SetCtUnitQty	= B100_TextControl.TextToInt(TB_CtUnitQty.getText());
		int  SetCsUnitQty	= B100_TextControl.TextToInt(TB_CsUnitQty.getText());
		int  SetPlUnitQty	= B100_TextControl.TextToInt(TB_PlUnitQty.getText());
		
		if(TB_EntryMode.isSelected()) {
			if(0<SetPlUnitQty){
				TB_PlAdjustQty.setEditable(true);
				TB_PlAdjustQty.setBackground(B100_FrameParts.SelectColer("Entry"));
			}else {
				TB_PlAdjustQty.setEditable(false);
				TB_PlAdjustQty.setBackground(B100_FrameParts.SelectColer("NoEntry"));
			}
			
			if(0<SetCsUnitQty){
				TB_CsAdjustQty.setEditable(true);
				TB_CsAdjustQty.setBackground(B100_FrameParts.SelectColer("Entry"));
			}else {
				TB_CsAdjustQty.setEditable(false);
				TB_CsAdjustQty.setBackground(B100_FrameParts.SelectColer("NoEntry"));
			}
			
			if(0<SetCtUnitQty){
				TB_CtAdjustQty.setEditable(true);
				TB_CtAdjustQty.setBackground(B100_FrameParts.SelectColer("Entry"));
			}else {
				TB_CtAdjustQty.setEditable(false);
				TB_CtAdjustQty.setBackground(B100_FrameParts.SelectColer("NoEntry"));
			}
			
			TB_BrAdjustQty.setEditable(true);
			TB_BrAdjustQty.setBackground(B100_FrameParts.SelectColer("Entry"));
			
			TB_AdjustQty.setEditable(false);
			TB_AdjustQty.setBackground(B100_FrameParts.SelectColer("NoEntry"));
			
		}else {
			TB_PlAdjustQty.setEditable(false);
			TB_PlAdjustQty.setBackground(B100_FrameParts.SelectColer("NoEntry"));
			
			TB_CsAdjustQty.setEditable(false);
			TB_CsAdjustQty.setBackground(B100_FrameParts.SelectColer("NoEntry"));
			
			TB_CtAdjustQty.setEditable(false);
			TB_CtAdjustQty.setBackground(B100_FrameParts.SelectColer("NoEntry"));
			
			TB_BrAdjustQty.setEditable(false);
			TB_BrAdjustQty.setBackground(B100_FrameParts.SelectColer("NoEntry"));
			
			TB_AdjustQty.setEditable(true);
			TB_AdjustQty.setBackground(B100_FrameParts.SelectColer("Entry"));
		}
	}
	private static void AdjustFromQty(
			JFormattedTextField TB_CtUnitQty,
			JFormattedTextField TB_CsUnitQty,
			JFormattedTextField TB_PlUnitQty,
			JFormattedTextField TB_AdjustQty,
			JFormattedTextField TB_PlAdjustQty,
			JFormattedTextField TB_CsAdjustQty,
			JFormattedTextField TB_CtAdjustQty,
			JFormattedTextField TB_BrAdjustQty,
			JFormattedTextField TB_Qty,
			JFormattedTextField TB_ShipPlanQty,
			JFormattedTextField TB_PossibleQty,
			JComboBox TB_LocType,
			JFormattedTextField TB_AfterQty,
			JFormattedTextField TB_AfterShipPlanQty,
			JFormattedTextField TB_AfterPossibleQty,
			JFormattedTextField TB_BeforePlQty,
			JFormattedTextField TB_BeforeCsQty,
			JFormattedTextField TB_BeforeCtQty,
			JFormattedTextField TB_BeforeBrQty,
			JFormattedTextField TB_AfterPlQty,
			JFormattedTextField TB_AfterCsQty,
			JFormattedTextField TB_AfterCtQty,
			JFormattedTextField TB_AfterBrQty
			) {
		NumberFormat ni = NumberFormat.getNumberInstance();
		int  SetPlUnitQty	= B100_TextControl.TextToInt(TB_PlUnitQty.getText());
		int  SetCsUnitQty	= B100_TextControl.TextToInt(TB_CsUnitQty.getText());
		int  SetCtUnitQty	= B100_TextControl.TextToInt(TB_CtUnitQty.getText());
		
		int AdjustQty	= B100_TextControl.TextToInt(TB_AdjustQty.getText());
		int PlAdjustQty	= B100_TextControl.TextToInt(TB_PlAdjustQty.getText());
		int CsAdjustQty	= B100_TextControl.TextToInt(TB_CsAdjustQty.getText());
		int CtAdjustQty	= B100_TextControl.TextToInt(TB_CtAdjustQty.getText());
		
		int BeforeQty				= B100_TextControl.TextToInt(TB_Qty.getText());
		int BeforeShipPlanQty		= B100_TextControl.TextToInt(TB_ShipPlanQty.getText());
		int BeforePossibleQty		= B100_TextControl.TextToInt(TB_PossibleQty.getText());
		
		int LocType	= B100_TextControl.TextToInt(B100_DefaultVariable.LocType[1][TB_LocType.getSelectedIndex()]);	//ロケータイプ
		
		int AfterQty				= BeforeQty+AdjustQty;
		int AfterShipPlanQty		= BeforeShipPlanQty;
		
		int AfterPossibleQty		= BeforePossibleQty;
		
		boolean ShipTgtLoc = true;
		for(int i=0;i<B100_DefaultVariable.ShipPlovisionUnTgtList.length;i++) {
			if(B100_DefaultVariable.ShipPlovisionUnTgtList[i].equals(""+LocType)) {
				ShipTgtLoc = false;
			}
		}
		if(ShipTgtLoc) {
			AfterPossibleQty		= BeforePossibleQty+AdjustQty;
		}
		
		TB_AfterQty.setText(""+ni.format(AfterQty));
		TB_AfterShipPlanQty.setText(""+ni.format(AfterShipPlanQty));
		TB_AfterPossibleQty.setText(""+ni.format(AfterPossibleQty));
		
		if(0<SetPlUnitQty) {
			PlAdjustQty = (int)(AdjustQty/SetPlUnitQty);
			AdjustQty = AdjustQty%SetPlUnitQty;
			TB_PlAdjustQty.setText(""+ni.format(PlAdjustQty));
		}
		if(0<SetCsUnitQty) {
			CsAdjustQty = (int)(AdjustQty/SetCsUnitQty);
			AdjustQty = AdjustQty%SetCsUnitQty;
			TB_CsAdjustQty.setText(""+ni.format(CsAdjustQty));
		}
		if(0<SetCtUnitQty) {
			CtAdjustQty = (int)(AdjustQty/SetCtUnitQty);
			AdjustQty = AdjustQty%SetCtUnitQty;
			TB_CtAdjustQty.setText(""+ni.format(CtAdjustQty));
		}
		TB_BrAdjustQty.setText(""+ni.format(AdjustQty));
		
		int BeforePlQty=0;
		int BeforeCsQty=0;
		int BeforeCtQty=0;
		int BeforeBrQty=BeforeQty;
		
		if(0<SetPlUnitQty) {
			BeforePlQty = (int)(BeforeBrQty/SetPlUnitQty);
			BeforeBrQty = (int)(BeforeBrQty%SetPlUnitQty);
		}
		if(0<SetCsUnitQty) {
			BeforeCsQty = (int)(BeforeBrQty/SetCsUnitQty);
			BeforeBrQty = (int)(BeforeBrQty%SetCsUnitQty);
		}
		if(0<SetCtUnitQty) {
			BeforeCtQty = (int)(BeforeBrQty/SetCtUnitQty);
			BeforeBrQty = (int)(BeforeBrQty%SetCtUnitQty);
		}
		
		TB_BeforePlQty.setText(""+ni.format(BeforePlQty));
		TB_BeforeCsQty.setText(""+ni.format(BeforeCsQty));
		TB_BeforeCtQty.setText(""+ni.format(BeforeCtQty));
		TB_BeforeBrQty.setText(""+ni.format(BeforeBrQty));
		
		int AfterPlQty=0;
		int AfterCsQty=0;
		int AfterCtQty=0;
		int AfterBrQty=AfterQty;
		if(0<SetPlUnitQty) {
			AfterPlQty = (int)(AfterBrQty/SetPlUnitQty);
			AfterBrQty = (int)(AfterBrQty%SetPlUnitQty);
		}
		if(0<SetCsUnitQty) {
			AfterCsQty = (int)(AfterBrQty/SetCsUnitQty);
			AfterBrQty = (int)(AfterBrQty%SetCsUnitQty);
		}
		if(0<SetCtUnitQty) {
			AfterCtQty = (int)(AfterBrQty/SetCtUnitQty);
			AfterBrQty = (int)(AfterBrQty%SetCtUnitQty);
		}
		
		TB_AfterPlQty.setText(""+ni.format(AfterPlQty));
		TB_AfterCsQty.setText(""+ni.format(AfterCsQty));
		TB_AfterCtQty.setText(""+ni.format(AfterCtQty));
		TB_AfterBrQty.setText(""+ni.format(AfterBrQty));
	}
	
	private static void AdjustFromUnitQty(
			JFormattedTextField TB_CtUnitQty,
			JFormattedTextField TB_CsUnitQty,
			JFormattedTextField TB_PlUnitQty,
			JFormattedTextField TB_AdjustQty,
			JFormattedTextField TB_PlAdjustQty,
			JFormattedTextField TB_CsAdjustQty,
			JFormattedTextField TB_CtAdjustQty,
			JFormattedTextField TB_BrAdjustQty,
			JFormattedTextField TB_Qty,
			JFormattedTextField TB_ShipPlanQty,
			JFormattedTextField TB_PossibleQty,
			JComboBox TB_LocType,
			JFormattedTextField TB_AfterQty,
			JFormattedTextField TB_AfterShipPlanQty,
			JFormattedTextField TB_AfterPossibleQty,
			JFormattedTextField TB_BeforePlQty,
			JFormattedTextField TB_BeforeCsQty,
			JFormattedTextField TB_BeforeCtQty,
			JFormattedTextField TB_BeforeBrQty,
			JFormattedTextField TB_AfterPlQty,
			JFormattedTextField TB_AfterCsQty,
			JFormattedTextField TB_AfterCtQty,
			JFormattedTextField TB_AfterBrQty
			) {
		NumberFormat ni = NumberFormat.getNumberInstance();
		int  SetPlUnitQty	= B100_TextControl.TextToInt(TB_PlUnitQty.getText());
		int  SetCsUnitQty	= B100_TextControl.TextToInt(TB_CsUnitQty.getText());
		int  SetCtUnitQty	= B100_TextControl.TextToInt(TB_CtUnitQty.getText());
		
		int AdjustQty	= B100_TextControl.TextToInt(TB_AdjustQty.getText());
		int PlAdjustQty	= B100_TextControl.TextToInt(TB_PlAdjustQty.getText());
		int CsAdjustQty	= B100_TextControl.TextToInt(TB_CsAdjustQty.getText());
		int CtAdjustQty	= B100_TextControl.TextToInt(TB_CtAdjustQty.getText());
		int BrAdjustQty	= B100_TextControl.TextToInt(TB_BrAdjustQty.getText());
		
		AdjustQty = PlAdjustQty*SetPlUnitQty+CsAdjustQty*SetCsUnitQty+CtAdjustQty*SetCtUnitQty+BrAdjustQty;
		TB_AdjustQty.setText(""+ni.format(AdjustQty));
		
		int BeforeQty				= B100_TextControl.TextToInt(TB_Qty.getText());
		int BeforeShipPlanQty		= B100_TextControl.TextToInt(TB_ShipPlanQty.getText());
		int BeforePossibleQty		= B100_TextControl.TextToInt(TB_PossibleQty.getText());
		
		int LocType	= B100_TextControl.TextToInt(B100_DefaultVariable.LocType[1][TB_LocType.getSelectedIndex()]);	//ロケタイプ
		
		int AfterQty				= BeforeQty+AdjustQty;
		int AfterShipPlanQty		= BeforeShipPlanQty;
		
		int AfterPossibleQty		= BeforePossibleQty;
		
		boolean ShipTgtLoc = true;
		for(int i=0;i<B100_DefaultVariable.ShipPlovisionUnTgtList.length;i++) {
			if(B100_DefaultVariable.ShipPlovisionUnTgtList[i].equals(""+LocType)) {
				ShipTgtLoc = false;
			}
		}
		if(ShipTgtLoc) {
			AfterPossibleQty		= BeforePossibleQty+AdjustQty;
		}
		TB_AfterQty.setText(""+ni.format(AfterQty));
		TB_AfterShipPlanQty.setText(""+ni.format(AfterShipPlanQty));
		TB_AfterPossibleQty.setText(""+ni.format(AfterPossibleQty));
		
		int BeforePlQty=0;
		int BeforeCsQty=0;
		int BeforeCtQty=0;
		int BeforeBrQty=BeforeQty;
		
		if(0<SetPlUnitQty) {
			BeforePlQty = (int)(BeforeBrQty/SetPlUnitQty);
			BeforeBrQty = (int)(BeforeBrQty%SetPlUnitQty);
		}
		if(0<SetCsUnitQty) {
			BeforeCsQty = (int)(BeforeBrQty/SetCsUnitQty);
			BeforeBrQty = (int)(BeforeBrQty%SetCsUnitQty);
		}
		if(0<SetCtUnitQty) {
			BeforeCtQty = (int)(BeforeBrQty/SetCtUnitQty);
			BeforeBrQty = (int)(BeforeBrQty%SetCtUnitQty);
		}
		
		TB_BeforePlQty.setText(""+ni.format(BeforePlQty));
		TB_BeforeCsQty.setText(""+ni.format(BeforeCsQty));
		TB_BeforeCtQty.setText(""+ni.format(BeforeCtQty));
		TB_BeforeBrQty.setText(""+ni.format(BeforeBrQty));
		
		int AfterPlQty=0;
		int AfterCsQty=0;
		int AfterCtQty=0;
		int AfterBrQty=AfterQty;
		if(0<SetPlUnitQty) {
			AfterPlQty = (int)(AfterBrQty/SetPlUnitQty);
			AfterBrQty = (int)(AfterBrQty%SetPlUnitQty);
		}
		if(0<SetCsUnitQty) {
			AfterCsQty = (int)(AfterBrQty/SetCsUnitQty);
			AfterBrQty = (int)(AfterBrQty%SetCsUnitQty);
		}
		if(0<SetCtUnitQty) {
			AfterCtQty = (int)(AfterBrQty/SetCtUnitQty);
			AfterBrQty = (int)(AfterBrQty%SetCtUnitQty);
		}
		
		TB_AfterPlQty.setText(""+ni.format(AfterPlQty));
		TB_AfterCsQty.setText(""+ni.format(AfterCsQty));
		TB_AfterCtQty.setText(""+ni.format(AfterCtQty));
		TB_AfterBrQty.setText(""+ni.format(AfterBrQty));
		
	}
	
	private static Object[] StockDataRt(
								String TgtWhCd,
								String TgtClCd,
								String TgtLoc,
								String TgtItemCd,
								String TgtLot,
								String TgtExpdate,
								String TgtActualDate
								) {
		//在庫情報返却、在庫情報がない場合在庫ゼロと同等の在庫情報をマスタ情報から作る
		Object[] StockData= new Object[T100_StockRt.RtStockRt().length];
		
		StockData[T100_StockRt.ColClCd]			= TgtClCd;				//荷主コード
		StockData[T100_StockRt.ColCLName]			= "";					//荷主名1
		StockData[T100_StockRt.ColWhCd]			= TgtWhCd;				//倉庫コード
		StockData[T100_StockRt.ColClWHName]		= "";					//担当倉庫名
		StockData[T100_StockRt.ColClGpCD]			= "";					//荷主グループCD
		StockData[T100_StockRt.ColClGpName]		= "";					//グループ名1
		StockData[T100_StockRt.ColLoc]				= TgtLoc;				//ロケーション
		StockData[T100_StockRt.ColLocName]			= "";					//ロケーション名
		StockData[T100_StockRt.ColType]			= 0;					//ロケタイプ
		StockData[T100_StockRt.ColItemCd]			= TgtItemCd;			//商品コード
		StockData[T100_StockRt.ColLot]				= TgtLot;				//ロット
		StockData[T100_StockRt.ColExpdate]			= TgtExpdate;			//消費期限
		StockData[T100_StockRt.ColActualDate]		= TgtActualDate;		//入荷実績日
		StockData[T100_StockRt.ColQty]				= 0;					//総数量
		StockData[T100_StockRt.ColShipPlanQty]	= 0;					//引当済総数
		StockData[T100_StockRt.ColPossibleQty]	= 0;					//出荷可能総数
		StockData[T100_StockRt.ColItemName]		= "";					//商品名
		StockData[T100_StockRt.ColItemName01]		= "";					//商品名1
		StockData[T100_StockRt.ColItemName02]		= "";					//商品名2
		StockData[T100_StockRt.ColItemName03]		= "";					//商品名3
		StockData[T100_StockRt.ColClItemCd]		= "";					//荷主商品コード
		StockData[T100_StockRt.ColJanCd]			= "";					//ソースマーク_BCD（バラ）
		StockData[T100_StockRt.ColItemMdNo]		= "";					//商品型番
		StockData[T100_StockRt.ColCtUnitQty]		= 0;					//カートン入数
		StockData[T100_StockRt.ColCsUnitQty]		= 0;					//ケース入数
		StockData[T100_StockRt.ColPlUnitQty]		= 0;					//パレット入数
		StockData[T100_StockRt.ColUnitName]		= "";					//商品単位
		StockData[T100_StockRt.ColCtUnitName]		= "";					//カートン商品単位
		StockData[T100_StockRt.ColCsUnitName]		= "";					//ケース商品単位
		StockData[T100_StockRt.ColPlUnitName]		= "";					//パレット商品単位
		StockData[T100_StockRt.ColEntryDate]		= "";					//登録日時
		StockData[T100_StockRt.ColUpdateDate]		= "";					//更新日時
		StockData[T100_StockRt.ColEntryUser]		= "";					//登録者
		StockData[T100_StockRt.ColUpdateUser]		= "";					//更新者
		StockData[T100_StockRt.ColBrQty]			= 0;					//バラ数量
		StockData[T100_StockRt.ColBrShipPlanQty]	= 0;					//引当済バラ数
		StockData[T100_StockRt.ColBrPossibleQty]	= 0;					//出荷可能バラ数
		StockData[T100_StockRt.ColCtQty]			= 0;					//カートン数量
		StockData[T100_StockRt.ColCtShipPlanQty]	= 0;					//引当済カートン数
		StockData[T100_StockRt.ColCtPossibleQty]	= 0;					//出荷可能カートン数
		StockData[T100_StockRt.ColCsQty]			= 0;					//ケース数量
		StockData[T100_StockRt.ColCsShipPlanQty]	= 0;					//引当済ケース数
		StockData[T100_StockRt.ColCsPossibleQty]	= 0;					//出荷可能ケース数
		StockData[T100_StockRt.ColPlQty]			= 0;					//パレット数量
		StockData[T100_StockRt.ColPlShipPlanQty]	= 0;					//引当済パレット数
		StockData[T100_StockRt.ColPlPossibleQty]	= 0;					//出荷可能パレット数
		boolean MstCheckFg = true;
		
		if(!"".equals(TgtWhCd)
				&&!"".equals(TgtClCd)
				&&!"".equals(TgtLoc)
				&&!"".equals(TgtItemCd)
				&&!"".equals(TgtExpdate)
				&&!"".equals(TgtActualDate)
				) {
			
			Object[][] StockRt= StockRt(
										TgtWhCd,
										TgtClCd,
										TgtLoc,
										TgtItemCd,
										TgtLot,
										TgtExpdate,
										TgtActualDate
										);
			if(1==StockRt.length) {
				StockData = StockRt[0];
				
				MstCheckFg = false;
			}
		}
		if(MstCheckFg) {
			//在庫検索結果で対象特定されていなければ　マスタ情報を引っ張る
			
			//荷主コードを元に荷主情報取得
			Object[][] ClMstRt = ClMstRt((String)StockData[T100_StockRt.ColClCd]);
			if(1==ClMstRt.length) {
				StockData[T100_StockRt.ColClCd]		=(String)ClMstRt[0][M100_ClMstRt.Colcl_cd];		//荷主CD
				StockData[T100_StockRt.ColCLName]		=(String)ClMstRt[0][M100_ClMstRt.ColCLName01];		//荷主名1
				StockData[T100_StockRt.ColClGpCD]		=(String)ClMstRt[0][M100_ClMstRt.ColClGpCD];		//荷主グループCD
				StockData[T100_StockRt.ColClGpName]	=(String)ClMstRt[0][M100_ClMstRt.ColClGpName];		//グループ名1
			}
			
			//倉庫マスタ
			Object[][] WhMstRt = WhMstRt((String)StockData[T100_StockRt.ColWhCd]);
			if(1==WhMstRt.length){
				StockData[T100_StockRt.ColWhCd]		= (String)WhMstRt[0][M100_WhMstRt.ColNoWHCD];			//倉庫コード
				StockData[T100_StockRt.ColClWHName]	= (String)WhMstRt[0][M100_WhMstRt.ColNoWHName];		//担当倉庫名
			}
			
			//商品マスタ
			Object[][] ItemMstRt = ItemMstRt((String)StockData[T100_StockRt.ColClGpCD],(String)StockData[T100_StockRt.ColItemCd]);
			if(1==ItemMstRt.length) {
				StockData[T100_StockRt.ColItemCd]			= (String)ItemMstRt[0][M100_ItemMstRt.ColItemCd];			//商品コード
				StockData[T100_StockRt.ColItemName]		= (String)ItemMstRt[0][M100_ItemMstRt.ColItemName01];		//商品名
				StockData[T100_StockRt.ColItemName01]		= (String)ItemMstRt[0][M100_ItemMstRt.ColItemName01];		//商品名1
				StockData[T100_StockRt.ColItemName02]		= (String)ItemMstRt[0][M100_ItemMstRt.ColItemName02];		//商品名2
				StockData[T100_StockRt.ColItemName03]		= (String)ItemMstRt[0][M100_ItemMstRt.ColItemName03];		//商品名3
				StockData[T100_StockRt.ColClItemCd]		= (String)ItemMstRt[0][M100_ItemMstRt.ColCLItemCd];		//荷主商品コード
				StockData[T100_StockRt.ColJanCd]			= (String)ItemMstRt[0][M100_ItemMstRt.ColJanCd];			//ソースマーク_BCD（バラ）
				StockData[T100_StockRt.ColItemMdNo]		= (String)ItemMstRt[0][M100_ItemMstRt.ColItemMDNo];		//商品型番
				StockData[T100_StockRt.ColCtUnitQty]		= (int)ItemMstRt[0][M100_ItemMstRt.ColCtQty];				//カートン入数
				StockData[T100_StockRt.ColCsUnitQty]		= (int)ItemMstRt[0][M100_ItemMstRt.ColCsQty];				//ケース入数
				StockData[T100_StockRt.ColPlUnitQty]		= (int)ItemMstRt[0][M100_ItemMstRt.ColPlQty];				//パレット入数
				StockData[T100_StockRt.ColUnitName]		= (String)ItemMstRt[0][M100_ItemMstRt.ColUnitName];		//商品単位
				StockData[T100_StockRt.ColCtUnitName]		= (String)ItemMstRt[0][M100_ItemMstRt.ColCtUnitName];		//カートン商品単位
				StockData[T100_StockRt.ColCsUnitName]		= (String)ItemMstRt[0][M100_ItemMstRt.ColCsUnitName];		//ケース商品単位
				StockData[T100_StockRt.ColPlUnitName]		= (String)ItemMstRt[0][M100_ItemMstRt.ColPlUnitName];		//パレット商品単位
			}
			
			//ロケーションマスタ
			Object[][] LocationMstRt = LocationMstRt((String)StockData[T100_StockRt.ColClCd],(String)StockData[T100_StockRt.ColWhCd],(String)StockData[T100_StockRt.ColLoc]);
			if(1==LocationMstRt.length) {
				StockData[T100_StockRt.ColLoc]				= (String)LocationMstRt[0][M100_LocationMstRt.ColLoc];			//ロケーション
				StockData[T100_StockRt.ColLocName]			= (String)LocationMstRt[0][M100_LocationMstRt.ColLocName];		//ロケーション名
				StockData[T100_StockRt.ColType]			= (int)LocationMstRt[0][M100_LocationMstRt.ColType];			//ロケタイプ
			}
		}
		//入荷実績日空白で、入荷日管理しない場合デフォルト入荷実績日セット
		if(B100_DefaultVariable.ActualDateUnControl && "".equals((String)StockData[T100_StockRt.ColActualDate])) {
			StockData[T100_StockRt.ColActualDate] = B100_DefaultVariable.DefaultActualDate;
		}
		//賞味期限空白の場合、一旦デフォルト賞味期限セット
		if("".equals((String)StockData[T100_StockRt.ColExpdate])) {
			StockData[T100_StockRt.ColExpdate] = B100_DefaultVariable.DefaultExpDate;
		}
		return StockData;
	}
	
	private static Object[][] StockRt(String TgtWhCd,
			String TgtClCd,
			String TgtLoc,
			String TgtItemCd,
			String TgtLot,
			String TgtExpdate,
			String TgtActualDate){
		ArrayList<String> SearchClCd				= new ArrayList<String>();			//荷主コード
		ArrayList<String> SearchWhCd				= new ArrayList<String>();			//倉庫コード
		ArrayList<String> SearchClGpCD				= new ArrayList<String>();			//荷主グループCD
		ArrayList<String> SearchLoc					= new ArrayList<String>();			//ロケーション
		ArrayList<Integer>SearchType				= new ArrayList<Integer>();			//ロケタイプ　0:通常　1:保管　8:入荷時　9:引当禁止
		ArrayList<String> SearchItemCd				= new ArrayList<String>();			//商品コード
		ArrayList<String> SearchLot					= new ArrayList<String>();			//ロット
		ArrayList<String> SearchExpdateMin			= new ArrayList<String>();			//消費期限最小
		ArrayList<String> SearchExpdateMax			= new ArrayList<String>();			//消費期限最大
		ArrayList<String> SearchActualDateMin		= new ArrayList<String>();			//入荷実績日最小
		ArrayList<String> SearchActualDateMax		= new ArrayList<String>();			//入荷実績日最大
		ArrayList<Integer> SearchQtyMin				= new ArrayList<Integer>();			//数量最小
		ArrayList<Integer> SearchQtyMax				= new ArrayList<Integer>();			//数量最大
		ArrayList<Integer> SearchShipPlanQtyMin		= new ArrayList<Integer>();			//引当済数最小
		ArrayList<Integer> SearchShipPlanQtyMax		= new ArrayList<Integer>();			//引当済数最大
		ArrayList<Integer> SearchPossibleQtyMin		= new ArrayList<Integer>();			//出荷可能数最小
		ArrayList<Integer> SearchPossibleQtyMax		= new ArrayList<Integer>();			//出荷可能数最大
		ArrayList<String> SearchItemName			= new ArrayList<String>();			//商品名
		ArrayList<String> SearchClItemCd			= new ArrayList<String>();			//荷主商品コード
		ArrayList<String> SearchJanCd				= new ArrayList<String>();			//ソースマーク_BCD（バラ）
		ArrayList<String> SearchItemMdNo			= new ArrayList<String>();			//商品型番
		boolean LocExactMatch = true;													//ロケーション完全一致
		boolean AllSearch = false;														//全件検索
		boolean SortItemcdMode = false;													//商品CDでソート
		
		SearchWhCd.add(				TgtWhCd);
		SearchClCd.add(				TgtClCd);
		SearchLoc.add(				TgtLoc);
		SearchItemCd.add(			TgtItemCd);
		SearchLot.add(				TgtLot);
		if(!"".equals(TgtExpdate	)) {SearchExpdateMin.add(		TgtExpdate);}
		if(!"".equals(TgtActualDate	)) {SearchActualDateMin.add(	TgtActualDate);}
		if(!"".equals(TgtExpdate	)) {SearchExpdateMax.add(		TgtExpdate);}
		if(!"".equals(TgtActualDate	)) {SearchActualDateMax.add(	TgtActualDate);}
		
		Object[][] StockRt= T100_StockRt.StockRt(
				SearchClCd,				//荷主コード
				SearchWhCd,				//倉庫コード
				SearchClGpCD,			//荷主グループCD
				SearchLoc,				//ロケーション
				SearchType,				//ロケタイプ　0:通常　1:保管　8:入荷時　9:引当禁止
				SearchItemCd,			//商品コード
				SearchLot,				//ロット
				SearchExpdateMin,		//消費期限最小
				SearchExpdateMax,		//消費期限最大
				SearchActualDateMin,	//入荷実績日最小
				SearchActualDateMax,	//入荷実績日最大
				SearchQtyMin,			//数量最小
				SearchQtyMax,			//数量最大
				SearchShipPlanQtyMin,	//引当済数最小
				SearchShipPlanQtyMax,	//引当済数最大
				SearchPossibleQtyMin,	//出荷可能数最小
				SearchPossibleQtyMax,	//出荷可能数最大
				SearchItemName,			//商品名
				SearchClItemCd,			//荷主商品コード
				SearchJanCd,			//ソースマーク_BCD（バラ）
				SearchItemMdNo,			//商品型番
				LocExactMatch,			//ロケーション完全一致
				AllSearch,
				SortItemcdMode);
		
		return StockRt;
	}
	private static Object[][] LocSearch(
								String SearchTgtClCd,
								String SearchTgtWhCd,
								String SearchTgtLoc,
								String SearchTgtLocName,
								String SearchTgtType
								){
		ArrayList<String> SearchClCd 	= new ArrayList<String>();	//荷主コード
		ArrayList<String> SearchWhCd 	= new ArrayList<String>();	//倉庫コード
		ArrayList<String> SearchLoc 	= new ArrayList<String>();	//ロケーション
		ArrayList<String> SearchLocName = new ArrayList<String>();	//ロケーション名
		ArrayList<String> SearchType 	= new ArrayList<String>();	//ロケタイプ
		boolean LocExactMatch = false;	//ロケーション完全一致
		boolean AllSearch = true;
		
		if(!"".equals(SearchTgtClCd		)) {SearchClCd.add(		SearchTgtClCd);}
		if(!"".equals(SearchTgtWhCd		)) {SearchWhCd.add(		SearchTgtWhCd);}
		if(!"".equals(SearchTgtLoc		)) {SearchLoc.add(		SearchTgtLoc);}
		if(!"".equals(SearchTgtLocName	)) {SearchLocName.add(	SearchTgtLocName);}
		if(!"".equals(SearchTgtType		)) {SearchType.add(		SearchTgtType);}
		
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
	
	
	private static Object[][] LocationMstRt(String ClCd,String WhCd,String Loc){
		ArrayList<String> SearchClCd 	= new ArrayList<String>();	//荷主コード
		ArrayList<String> SearchWhCd 	= new ArrayList<String>();	//倉庫コード
		ArrayList<String> SearchLoc 	= new ArrayList<String>();	//ロケーション
		ArrayList<String> SearchLocName = new ArrayList<String>();	//ロケーション名
		ArrayList<String> SearchType 	= new ArrayList<String>();	//ロケタイプ
		boolean LocExactMatch = true;	//ロケーション完全一致
		boolean AllSearch = false;
		
		SearchClCd.add(ClCd);
		SearchWhCd.add(WhCd);
		SearchLoc.add(Loc);
		
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
	
	private static Object[][] WhMstRt(String WHCD){
		ArrayList<String> SearchWHCD 	= new ArrayList<String>();
		ArrayList<String> SearchWHName 	= new ArrayList<String>();
		ArrayList<String> SearchPost 	= new ArrayList<String>();
		ArrayList<String> SearchAdd 	= new ArrayList<String>();
		ArrayList<String> SearchTel 	= new ArrayList<String>();
		ArrayList<String> SearchFax 	= new ArrayList<String>();
		ArrayList<String> SearchMail 	= new ArrayList<String>();
		ArrayList<String> SearchCom 	= new ArrayList<String>();
		ArrayList<String> SearchPTMSCD 	= new ArrayList<String>();
		boolean AllSearch = false;
		
		SearchWHCD.add(WHCD);
		
		Object[][] WhMstRt = M100_WhMstRt.WhMstRt(
				SearchWHCD,
				SearchWHName,
				SearchPost,
				SearchAdd,
				SearchTel,
				SearchFax,
				SearchMail,
				SearchCom,
				SearchPTMSCD,
				AllSearch);
		return WhMstRt;
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
									String SearchTgtItemName
									){
		ArrayList<String> SearchClGpCd 				= new ArrayList<String>();	//荷主グループコード
		ArrayList<String> SearchItemCd 				= new ArrayList<String>();	//商品コード
		ArrayList<String> SearchCLItemCd 			= new ArrayList<String>();	//荷主商品コード
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
		if(!"".equals(SearchTgtItemName	)) {SearchItemName.add(	SearchTgtItemName);}
		
		Object[][] ItemMstRt = M100_ItemMstRt.ItemMstRt(
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
				SearchItemSizeName,		//商品サイズ名
				SearchJanCd,			//JANCD
				SearchTildFG,			//温度区分
				SearchTildName,			//温度区分名
				SearchDelFg,			//削除フラグ
				AllSearch);
		
		return ItemMstRt;
	}
	
	private static Object[][] ItemMstRt(String ClGpCd,String ItemCd){
		ArrayList<String> SearchClGpCd 				= new ArrayList<String>();	//荷主グループコード
		ArrayList<String> SearchItemCd 				= new ArrayList<String>();	//商品コード
		ArrayList<String> SearchCLItemCd 			= new ArrayList<String>();	//荷主商品コード
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
		
		SearchClGpCd.add(ClGpCd);
		SearchItemCd.add(ItemCd);
		
		Object[][] ItemMstRt = M100_ItemMstRt.ItemMstRt(
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
				SearchItemSizeName,		//商品サイズ名
				SearchJanCd,			//JANCD
				SearchTildFG,			//温度区分
				SearchTildName,			//温度区分名
				SearchDelFg,			//削除フラグ
				AllSearch);
		
		return ItemMstRt;
	}
}