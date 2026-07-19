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

public class WT100_Stock_20_Move{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	
	public static void StockMove(int x,int y,
			final String HandoverWhCd,final String HandoverClCd,final String HandoverLoc,final String HandoverItemCd,final String HandoverLot,final String HandoverExpdate,final String HandoverActualDate) {
		//引き継いだ移動元ロケ在庫から移動先ロケを指定して在庫移動を実行する
		A00000_Main.LoginCheck();//
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		
		NumberFormat ni = NumberFormat.getNumberInstance();
		boolean ErrFg = false;
		
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
		
		//移動元在庫取得
		Object[][] FromStockData	= StockRt(
										TgtWhCd,
										TgtClCd,
										TgtLoc,
										TgtItemCd,
										TgtLot,
										TgtExpdate,
										TgtActualDate
										);
		final JFrame main_fm 	= B100_FrameParts.FrameCreate(x,y,750,750,"Corgi00在庫移動　StockMove","ZK");
		JLabel userinfo 		= B100_FrameParts.UserInfo();
		JButton exit_btn 		= B100_FrameParts.ExitBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		
		/*移動商品情報*/
		JLabel LB_ClCd						= B100_FrameParts.JLabelSet(		  0, 50,100,20,"荷主:"				,11,1);
		JLabel LB_WhCd						= B100_FrameParts.JLabelSet(		  0, 75,100,20,"倉庫:"				,11,1);
		
		JLabel LB_ItemCd					= B100_FrameParts.JLabelSet(		  0,100,100,20,"商品CD:"			,10,1);
		JLabel LB_Lot						= B100_FrameParts.JLabelSet(		  0,125,100,20,"ロット:"			,10,1);
		JLabel LB_Expdate					= B100_FrameParts.JLabelSet(		  0,150,100,20,"消費期限:"			,10,1);
		JLabel LB_ActualDate				= B100_FrameParts.JLabelSet(		  0,175,100,20,"入荷実績日:"		,10,1);
		
		JLabel LB_PlUnitQty					= B100_FrameParts.JLabelSet(		200,125,200,20,"パレット入数(バラ換算):"	,10,1);
		JLabel LB_CsUnitQty					= B100_FrameParts.JLabelSet(		200,150,200,20,"ケース入数(バラ換算):"		,10,1);
		JLabel LB_CtUnitQty					= B100_FrameParts.JLabelSet(		200,175,200,20,"カートン入数(バラ換算):"	,10,1);
		
		
		JLabel LB_FromMsg					= B100_FrameParts.JLabelSet(		100,225,100,20,"移動元現在庫"		,10,2);
		JLabel LB_ToMsg						= B100_FrameParts.JLabelSet(		300,225,100,20,"移動先現在庫"		,10,2);
		JLabel LB_MovePossibleMsg			= B100_FrameParts.JLabelSet(		100,400,100,20,"移動可能数"			,10,2);
		JLabel LB_MoveMsg					= B100_FrameParts.JLabelSet(		500,400,100,20,"移動数"				,10,2);
		JLabel LB_MoveAfterMsg				= B100_FrameParts.JLabelSet(		300,400,100,20,"移動後総数"			,10,2);
		JLabel LB_FromAfterMsg				= B100_FrameParts.JLabelSet(		100,550,100,20,"移動後在庫"			,10,2);
		JLabel LB_ToAfterMsg				= B100_FrameParts.JLabelSet(		300,550,100,20,"移動後在庫"			,10,2);
		
		
		JLabel LB_Loc						= B100_FrameParts.JLabelSet(		  0,250,100,20,"ロケーション:"		,10,1);
		JLabel LB_LocName					= B100_FrameParts.JLabelSet(		  0,275,100,20,"ロケ名称:"			,10,1);
		JLabel LB_Type						= B100_FrameParts.JLabelSet(		  0,300,100,20,"ロケタイプ:"		,10,1);
		JLabel LB_Qty						= B100_FrameParts.JLabelSet(		  0,325,100,20,"総数量:"			,10,1);
		JLabel LB_ShipPlanQty				= B100_FrameParts.JLabelSet(		  0,350,100,20,"引当済数:"			,10,1);
		JLabel LB_PossibleQty				= B100_FrameParts.JLabelSet(		  0,375,100,20,"出荷可能数:"		,10,1);
		
		JLabel LB_BrTotalQty				= B100_FrameParts.JLabelSet(		  0,425,100,20,"バラ総数:"			,10,1);
		JLabel LB_PlQty						= B100_FrameParts.JLabelSet(		  0,450,100,20,"パレット数:"		,10,1);
		JLabel LB_CsQty						= B100_FrameParts.JLabelSet(		  0,475,100,20,"ケース数:"			,10,1);
		JLabel LB_CtQty						= B100_FrameParts.JLabelSet(		  0,500,100,20,"カートン数:"		,10,1);
		JLabel LB_BrQty						= B100_FrameParts.JLabelSet(		  0,525,100,20,"バラ数:"			,10,1);
		
		JLabel LB_AfterQty					= B100_FrameParts.JLabelSet(		  0,575,100,20,"総数量:"			,10,1);
		JLabel LB_AfterShipPlanQty			= B100_FrameParts.JLabelSet(		  0,600,100,20,"引当済数:"			,10,1);
		JLabel LB_AfterPossibleQty			= B100_FrameParts.JLabelSet(		  0,625,100,20,"出荷可能数:"		,10,1);
		
		JLabel LB_MoveCom					= B100_FrameParts.JLabelSet(		500,580,100,20,"コメント"			,10,0);
		
		
		/*移動商品情報*/
		final JComboBox TB_ClCd						= B100_FrameParts.JComboBoxSet(				100, 50,250,20,B100_DefaultVariable.ClList[0],11);
		final JComboBox TB_WhCd						= B100_FrameParts.JComboBoxSet(				100, 75,250,20,B100_DefaultVariable.WhList[0],11);
		
		TB_ClCd.setSelectedIndex(	B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.ClList[1]		,TgtClCd ,true) );
		TB_WhCd.setSelectedIndex(	B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.ClList[1]		,TgtWhCd ,true) );
		
		
		final JTextField TB_ItemCd					= B100_FrameParts.JTextFieldSet(				100,100,100,20,TgtItemCd			,12,0);
		final JTextField TB_ItemName				= B100_FrameParts.JTextFieldSet(				200,100,300,20,""					,12,0);
		final JTextField TB_Lot						= B100_FrameParts.JTextFieldSet(				100,125,100,20,TgtLot				,12,0);
		final JFormattedTextField TB_Expdate		= B100_FrameParts.JFormattedTextFieldSet(	100,150,100,20,TgtExpdate			,12,0,"YYYY/MM/DD");
		final JFormattedTextField TB_ActualDate		= B100_FrameParts.JFormattedTextFieldSet(	100,175,100,20,TgtActualDate		,12,0,"YYYY/MM/DD");
		
		final JFormattedTextField TB_PlUnitQty		= B100_FrameParts.JFormattedTextFieldSet(	400,125,100,20,""+ni.format(0)		,12,1,"#,###");
		final JFormattedTextField TB_CsUnitQty		= B100_FrameParts.JFormattedTextFieldSet(	400,150,100,20,""+ni.format(0)		,12,1,"#,###");
		final JFormattedTextField TB_CtUnitQty		= B100_FrameParts.JFormattedTextFieldSet(	400,175,100,20,""+ni.format(0)		,12,1,"#,###");
		
		/*移動元情報*/
		final JTextField TB_FromLoc					= B100_FrameParts.JTextFieldSet(				100,250,100,20,TgtLoc				,12,0);
		final JTextField TB_FromLocName				= B100_FrameParts.JTextFieldSet(				100,275,100,20,""					,12,0);
		final JComboBox TB_FromType					= B100_FrameParts.JComboBoxSet(				100,300,100,20,B100_DefaultVariable.LocType[0],11);
		final JFormattedTextField TB_FromQty		= B100_FrameParts.JFormattedTextFieldSet(	100,325,100,20,""+ni.format(0)		,12,1,"#,###");
		final JFormattedTextField TB_FromShipPlanQty= B100_FrameParts.JFormattedTextFieldSet(	100,350,100,20,""+ni.format(0)		,12,1,"#,###");
		final JFormattedTextField TB_FromPossibleQty= B100_FrameParts.JFormattedTextFieldSet(	100,375,100,20,""+ni.format(0)		,12,1,"#,###");
		JLabel TB_FromQtyUnitName					= B100_FrameParts.JLabelSet(		  			200,325, 60,20,""					,10,0);
		JLabel TB_FromShipPlanQtyUnitName			= B100_FrameParts.JLabelSet(					200,350, 60,20,""					,10,0);
		JLabel TB_FromPossibleQtyUnitName			= B100_FrameParts.JLabelSet(					200,375, 60,20,""					,10,0);
		
		final JFormattedTextField TB_FromBrTotalQty	= B100_FrameParts.JFormattedTextFieldSet(	100,425,100,20,""+ni.format(0)		,12,1,"#,###");
		final JFormattedTextField TB_FromPlQty		= B100_FrameParts.JFormattedTextFieldSet(	100,450,100,20,""+ni.format(0)		,12,1,"#,###");
		final JFormattedTextField TB_FromCsQty		= B100_FrameParts.JFormattedTextFieldSet(	100,475,100,20,""+ni.format(0)		,12,1,"#,###");
		final JFormattedTextField TB_FromCtQty		= B100_FrameParts.JFormattedTextFieldSet(	100,500,100,20,""+ni.format(0)		,12,1,"#,###");
		final JFormattedTextField TB_FromBrQty		= B100_FrameParts.JFormattedTextFieldSet(	100,525,100,20,""+ni.format(0)		,12,1,"#,###");
		
		JLabel TB_FromBrTotalQtyUnitName			= B100_FrameParts.JLabelSet(					200,425, 60,20,""					,10,0);
		JLabel TB_FromPlQtyUnitName					= B100_FrameParts.JLabelSet(					200,450, 60,20,""					,10,0);
		JLabel TB_FromCsQtyUnitName					= B100_FrameParts.JLabelSet(					200,475, 60,20,""					,10,0);
		JLabel TB_FromCtQtyUnitName					= B100_FrameParts.JLabelSet(					200,500, 60,20,""					,10,0);
		JLabel TB_FromBrQtyUnitName					= B100_FrameParts.JLabelSet(					200,525, 60,20,""					,10,0);
		
		final JFormattedTextField TB_FromAfterQty			= B100_FrameParts.JFormattedTextFieldSet(	100,575,100,20,""+ni.format(0)			,12,1,"#,###");
		final JFormattedTextField TB_FromAfterShipPlanQty	= B100_FrameParts.JFormattedTextFieldSet(	100,600,100,20,""+ni.format(0)			,12,1,"#,###");
		final JFormattedTextField TB_FromAfterPossibleQty	= B100_FrameParts.JFormattedTextFieldSet(	100,625,100,20,""+ni.format(0)			,12,1,"#,###");
		JLabel TB_FromAfterQtyUnitName						= B100_FrameParts.JLabelSet(		  			200,575, 60,20,""			,10,0);
		JLabel TB_FromAfterShipPlanQtyUnitName				= B100_FrameParts.JLabelSet(					200,600, 60,20,""			,10,0);
		JLabel TB_FromAfterPossibleQtyUnitName				= B100_FrameParts.JLabelSet(					200,625, 60,20,""			,10,0);
		
		JLabel LB_Msg	= B100_FrameParts.JLabelSet(200,250,100,20,"⇒⇒⇒",10,2);
		
		/*移動先情報*/
		final JTextField TB_ToLoc					= B100_FrameParts.JTextFieldSet(				300,250,100,20,""						,12,0);
		final JTextField TBToLocName				= B100_FrameParts.JTextFieldSet(				300,275,100,20,""						,12,0);
		final JComboBox TB_ToType					= B100_FrameParts.JComboBoxSet(				300,300,100,20,B100_DefaultVariable.LocType[0],11);
		final JFormattedTextField TB_ToQty			= B100_FrameParts.JFormattedTextFieldSet(	300,325,100,20,""+ni.format(0)			,12,1,"#,###");
		final JFormattedTextField TB_ToShipPlanQty	= B100_FrameParts.JFormattedTextFieldSet(	300,350,100,20,""+ni.format(0)			,12,1,"#,###");
		final JFormattedTextField TB_ToPossibleQty	= B100_FrameParts.JFormattedTextFieldSet(	300,375,100,20,""+ni.format(0)			,12,1,"#,###");
		JLabel TB_ToQtyUnitName						= B100_FrameParts.JLabelSet(		  			400,325, 60,20,""		,10,0);
		JLabel TB_ToShipPlanQtyUnitName				= B100_FrameParts.JLabelSet(					400,350, 60,20,""		,10,0);
		JLabel TB_ToPossibleQtyUnitName				= B100_FrameParts.JLabelSet(					400,375, 60,20,""		,10,0);
		
		final JFormattedTextField TB_ToBrTotalQty	= B100_FrameParts.JFormattedTextFieldSet(	300,425,100,20,""+ni.format(0)			,12,1,"#,###");
		final JFormattedTextField TB_ToPlQty		= B100_FrameParts.JFormattedTextFieldSet(	300,450,100,20,""+ni.format(0)			,12,1,"#,###");
		final JFormattedTextField TB_ToCsQty		= B100_FrameParts.JFormattedTextFieldSet(	300,475,100,20,""+ni.format(0)			,12,1,"#,###");
		final JFormattedTextField TB_ToCtQty		= B100_FrameParts.JFormattedTextFieldSet(	300,500,100,20,""+ni.format(0)			,12,1,"#,###");
		final JFormattedTextField TB_ToBrQty		= B100_FrameParts.JFormattedTextFieldSet(	300,525,100,20,""+ni.format(0)			,12,1,"#,###");
		
		JLabel TB_ToBrTotalQtyUnitName				= B100_FrameParts.JLabelSet(					400,425, 60,20,""						,10,0);
		JLabel TB_ToPlQtyUnitName					= B100_FrameParts.JLabelSet(					400,450, 60,20,""						,10,0);
		JLabel TB_ToCsQtyUnitName					= B100_FrameParts.JLabelSet(					400,475, 60,20,""						,10,0);
		JLabel TB_ToCtQtyUnitName					= B100_FrameParts.JLabelSet(					400,500, 60,20,""						,10,0);
		JLabel TB_ToBrQtyUnitName					= B100_FrameParts.JLabelSet(					400,525, 60,20,""						,10,0);
		
		final JFormattedTextField TB_ToAfterQty			= B100_FrameParts.JFormattedTextFieldSet(	300,575,100,20,""+ni.format(0)		,12,1,"#,###");
		final JFormattedTextField TB_ToAfterShipPlanQty	= B100_FrameParts.JFormattedTextFieldSet(	300,600,100,20,""+ni.format(0)		,12,1,"#,###");
		final JFormattedTextField TB_ToAfterPossibleQty	= B100_FrameParts.JFormattedTextFieldSet(	300,625,100,20,""+ni.format(0)		,12,1,"#,###");
		JLabel TB_ToAfterQtyUnitName					= B100_FrameParts.JLabelSet(		  			400,575, 60,20,""					,10,0);
		JLabel TB_ToAfterShipPlanQtyUnitName			= B100_FrameParts.JLabelSet(					400,600, 60,20,""					,10,0);
		JLabel TB_ToAfterPossibleQtyUnitName			= B100_FrameParts.JLabelSet(					400,625, 60,20,""					,10,0);
		
		/*移動数情報*/
		final JFormattedTextField TB_MoveBrTotalQty	= B100_FrameParts.JFormattedTextFieldSet(	500,425,100,20,""+ni.format(0)			,12,1,"#,###");
		final JFormattedTextField TB_MovePlQty		= B100_FrameParts.JFormattedTextFieldSet(	500,450,100,20,""+ni.format(0)			,12,1,"#,###");
		final JFormattedTextField TB_MoveCsQty		= B100_FrameParts.JFormattedTextFieldSet(	500,475,100,20,""+ni.format(0)			,12,1,"#,###");
		final JFormattedTextField TB_MoveCtQty		= B100_FrameParts.JFormattedTextFieldSet(	500,500,100,20,""+ni.format(0)			,12,1,"#,###");
		final JFormattedTextField TB_MoveBrQty		= B100_FrameParts.JFormattedTextFieldSet(	500,525,100,20,""+ni.format(0)			,12,1,"#,###");
		
		JLabel TB_MoveBrTotalQtyUnitName			= B100_FrameParts.JLabelSet(					600,425, 60,20,""						,10,0);
		JLabel TB_MovePlQtyUnitName					= B100_FrameParts.JLabelSet(					600,450, 60,20,""						,10,0);
		JLabel TB_MoveCsQtyUnitName					= B100_FrameParts.JLabelSet(					600,475, 60,20,""						,10,0);
		JLabel TB_MoveCtQtyUnitName					= B100_FrameParts.JLabelSet(					600,500, 60,20,""						,10,0);
		JLabel TB_MoveBrQtyUnitName					= B100_FrameParts.JLabelSet(					600,525, 60,20,""						,10,0);
		
		final JTextField TB_Com01					= B100_FrameParts.JTextFieldSet(				500,600,200,20,""						,12,0);
		final JTextField TB_Com02					= B100_FrameParts.JTextFieldSet(				500,625,200,20,""						,12,0);
		final JTextField TB_Com03					= B100_FrameParts.JTextFieldSet(				500,650,200,20,""						,12,0);
		
		final JCheckBox TB_EntryMode 				= B100_FrameParts.JCheckBoxSet(				500,550,100,20,"荷姿別で移動",10);
		main_fm.add(TB_EntryMode);
		JButton EntryBtn 							= B100_FrameParts.AnyBtn(							675,"移動実行",11);
		main_fm.add(EntryBtn);
		
		//ロケ検索ボタン
		JButton LocSearchBtn = B100_FrameParts.BtnSet(420,250,80,20,"ロケ検索",11);
		main_fm.add(LocSearchBtn);
		
		final Object[] ArgumentSet = {
				/*移動商品情報*/
				TB_ClCd,
				TB_WhCd,
				
				TB_ItemCd,
				TB_ItemName,
				TB_Lot,
				TB_Expdate,
				TB_ActualDate,
				
				TB_PlUnitQty,
				TB_CsUnitQty,
				TB_CtUnitQty,
				
				/*移動元情報*/
				TB_FromLoc,
				TB_FromLocName,
				TB_FromType,
				TB_FromQty,
				TB_FromShipPlanQty,
				TB_FromPossibleQty,
				TB_FromQtyUnitName,
				TB_FromShipPlanQtyUnitName,
				TB_FromPossibleQtyUnitName,
				
				TB_FromBrTotalQty,
				TB_FromPlQty,
				TB_FromCsQty,
				TB_FromCtQty,
				TB_FromBrQty,
				
				TB_FromBrTotalQtyUnitName,
				TB_FromPlQtyUnitName,
				TB_FromCsQtyUnitName,
				TB_FromCtQtyUnitName,
				TB_FromBrQtyUnitName,
				
				TB_FromAfterQty,
				TB_FromAfterShipPlanQty,
				TB_FromAfterPossibleQty,
				TB_FromAfterQtyUnitName,
				TB_FromAfterShipPlanQtyUnitName,
				TB_FromAfterPossibleQtyUnitName,
				
				/*移動先情報*/
				TB_ToLoc,
				TBToLocName,
				TB_ToType,
				TB_ToQty,
				TB_ToShipPlanQty,
				TB_ToPossibleQty,
				TB_ToQtyUnitName,
				TB_ToShipPlanQtyUnitName,
				TB_ToPossibleQtyUnitName,
				
				TB_ToBrTotalQty,
				TB_ToPlQty,
				TB_ToCsQty,
				TB_ToCtQty,
				TB_ToBrQty,
				
				TB_ToBrTotalQtyUnitName,
				TB_ToPlQtyUnitName,
				TB_ToCsQtyUnitName,
				TB_ToCtQtyUnitName,
				TB_ToBrQtyUnitName,
				
				TB_ToAfterQty,
				TB_ToAfterShipPlanQty,
				TB_ToAfterPossibleQty,
				TB_ToAfterQtyUnitName,
				TB_ToAfterShipPlanQtyUnitName,
				TB_ToAfterPossibleQtyUnitName,
				
				/*移動数情報*/
				TB_MoveBrTotalQty,
				TB_MovePlQty,
				TB_MoveCsQty,
				TB_MoveCtQty,
				TB_MoveBrQty,
				
				TB_MoveBrTotalQtyUnitName,
				TB_MovePlQtyUnitName,
				TB_MoveCsQtyUnitName,
				TB_MoveCtQtyUnitName,
				TB_MoveBrQtyUnitName,
				TB_EntryMode
				};
		
		//移動元在庫が無い又は1件以上検索される場合は在庫検索に戻る
		if(1!=FromStockData.length){
			JOptionPane.showMessageDialog(null, "移動元在庫が特定できません");
			ErrFg = true;
		}else {
			ArrayList<String> ErrMsg = SetAfterVolArgumentControl(ArgumentSet);
			if(null!=ErrMsg && 0<ErrMsg.size()) {
				ErrFg = true;
				String WST = "以下のエラーが発生しています";
				for(int i=0;i<ErrMsg.size();i++) {
					WST	= WST + "\n" + ErrMsg.get(i);
				}
				JOptionPane.showMessageDialog(null, WST);
			}
		}
		
		
		TB_ClCd.setEnabled(false);
		TB_WhCd.setEnabled(false);
		
		TB_ItemCd.setEditable(false);
		TB_ItemName.setEditable(false);
		TB_Lot.setEditable(false);
		TB_Expdate.setEditable(false);
		TB_ActualDate.setEditable(false);
		
		TB_PlUnitQty.setEditable(false);
		TB_CsUnitQty.setEditable(false);
		TB_CtUnitQty.setEditable(false);
		
		/*移動元情報*/
		TB_FromLoc.setEditable(false);
		TB_FromLocName.setEditable(false);
		TB_FromType.setEnabled(false);
		TB_FromQty.setEditable(false);
		TB_FromShipPlanQty.setEditable(false);
		TB_FromPossibleQty.setEditable(false);
		
		TB_FromBrTotalQty.setEditable(false);
		TB_FromPlQty.setEditable(false);
		TB_FromCsQty.setEditable(false);
		TB_FromCtQty.setEditable(false);
		TB_FromBrQty.setEditable(false);
		
		TB_FromAfterQty.setEditable(false);
		TB_FromAfterShipPlanQty.setEditable(false);
		TB_FromAfterPossibleQty.setEditable(false);
		
		/*移動先情報*/
		TB_ToLoc.setEditable(true);
		TBToLocName.setEditable(false);
		TB_ToType.setEnabled(false);
		TB_ToQty.setEditable(false);
		TB_ToShipPlanQty.setEditable(false);
		TB_ToPossibleQty.setEditable(false);
		
		TB_ToBrTotalQty.setEditable(false);
		TB_ToPlQty.setEditable(false);
		TB_ToCsQty.setEditable(false);
		TB_ToCtQty.setEditable(false);
		TB_ToBrQty.setEditable(false);
		
		TB_ToAfterQty.setEditable(false);
		TB_ToAfterShipPlanQty.setEditable(false);
		TB_ToAfterPossibleQty.setEditable(false);
		
		/*移動数情報*/
		TB_MoveBrTotalQty.setEditable(true);
		TB_MovePlQty.setEditable(false);
		TB_MoveCsQty.setEditable(false);
		TB_MoveCtQty.setEditable(false);
		TB_MoveBrQty.setEditable(false);
		
		/*入力ボックス背景色変える*/
		TB_ToLoc.setBackground(B100_FrameParts.SelectColer("Entry"));
		TB_MoveBrTotalQty.setBackground(B100_FrameParts.SelectColer("Entry"));
		TB_MovePlQty.setBackground(B100_FrameParts.SelectColer("NoEntry"));
		TB_MoveCsQty.setBackground(B100_FrameParts.SelectColer("NoEntry"));
		TB_MoveCtQty.setBackground(B100_FrameParts.SelectColer("NoEntry"));
		TB_MoveBrQty.setBackground(B100_FrameParts.SelectColer("NoEntry"));
		
		main_fm.add(LB_ClCd);
		main_fm.add(LB_WhCd);
		
		
		main_fm.add(LB_ItemCd);
		main_fm.add(LB_Lot);
		main_fm.add(LB_Expdate);
		main_fm.add(LB_ActualDate);
		
		main_fm.add(LB_PlUnitQty);
		main_fm.add(LB_CsUnitQty);
		main_fm.add(LB_CtUnitQty);
		
		main_fm.add(LB_Loc);
		main_fm.add(LB_LocName);
		main_fm.add(LB_Type);
		main_fm.add(LB_Qty);
		main_fm.add(LB_ShipPlanQty);
		main_fm.add(LB_PossibleQty);
		
		main_fm.add(LB_FromMsg);
		main_fm.add(LB_ToMsg);
		main_fm.add(LB_MovePossibleMsg);
		main_fm.add(LB_MoveMsg);
		main_fm.add(LB_MoveAfterMsg);
		main_fm.add(LB_FromAfterMsg);
		main_fm.add(LB_ToAfterMsg);
		
		main_fm.add(LB_BrTotalQty);
		main_fm.add(LB_PlQty);
		main_fm.add(LB_CsQty);
		main_fm.add(LB_CtQty);
		main_fm.add(LB_BrQty);
		
		main_fm.add(LB_AfterQty);
		main_fm.add(LB_AfterShipPlanQty);
		main_fm.add(LB_AfterPossibleQty);
		
		main_fm.add(LB_MoveCom);
		
		main_fm.add(TB_ClCd);
		main_fm.add(TB_WhCd);
		main_fm.add(TB_ItemCd);
		main_fm.add(TB_ItemName);
		main_fm.add(TB_Lot);
		main_fm.add(TB_Expdate);
		main_fm.add(TB_ActualDate);
		
		main_fm.add(TB_PlUnitQty);
		main_fm.add(TB_CsUnitQty);
		main_fm.add(TB_CtUnitQty);
		
		main_fm.add(TB_FromLoc);
		main_fm.add(TB_FromType);
		main_fm.add(TB_FromLocName);
		main_fm.add(TB_FromQty);
		main_fm.add(TB_FromShipPlanQty);
		main_fm.add(TB_FromPossibleQty);
		main_fm.add(TB_FromQtyUnitName);
		main_fm.add(TB_FromShipPlanQtyUnitName);
		main_fm.add(TB_FromPossibleQtyUnitName);
		
		main_fm.add(TB_FromBrTotalQty);
		main_fm.add(TB_FromPlQty);
		main_fm.add(TB_FromCsQty);
		main_fm.add(TB_FromCtQty);
		main_fm.add(TB_FromBrQty);
		main_fm.add(TB_FromBrTotalQtyUnitName);
		main_fm.add(TB_FromPlQtyUnitName);
		main_fm.add(TB_FromCsQtyUnitName);
		main_fm.add(TB_FromCtQtyUnitName);
		main_fm.add(TB_FromBrQtyUnitName);
		
		main_fm.add(TB_FromAfterQty);
		main_fm.add(TB_FromAfterShipPlanQty);
		main_fm.add(TB_FromAfterPossibleQty);
		main_fm.add(TB_FromAfterQtyUnitName);
		main_fm.add(TB_FromAfterShipPlanQtyUnitName);
		main_fm.add(TB_FromAfterPossibleQtyUnitName);
		
		main_fm.add(LB_Msg);
		
		main_fm.add(TB_ToLoc);
		main_fm.add(TBToLocName);
		main_fm.add(TB_ToType);
		main_fm.add(TB_ToQty);
		main_fm.add(TB_ToShipPlanQty);
		main_fm.add(TB_ToPossibleQty);
		main_fm.add(TB_ToQtyUnitName);
		main_fm.add(TB_ToShipPlanQtyUnitName);
		main_fm.add(TB_ToPossibleQtyUnitName);
		
		main_fm.add(TB_ToBrTotalQty);
		main_fm.add(TB_ToPlQty);
		main_fm.add(TB_ToCsQty);
		main_fm.add(TB_ToCtQty);
		main_fm.add(TB_ToBrQty);
		main_fm.add(TB_ToBrTotalQtyUnitName);
		main_fm.add(TB_ToPlQtyUnitName);
		main_fm.add(TB_ToCsQtyUnitName);
		main_fm.add(TB_ToCtQtyUnitName);
		main_fm.add(TB_ToBrQtyUnitName);
		
		main_fm.add(TB_MoveBrTotalQty);
		main_fm.add(TB_MovePlQty);
		main_fm.add(TB_MoveCsQty);
		main_fm.add(TB_MoveCtQty);
		main_fm.add(TB_MoveBrQty);
		
		main_fm.add(TB_ToAfterQty);
		main_fm.add(TB_ToAfterShipPlanQty);
		main_fm.add(TB_ToAfterPossibleQty);
		main_fm.add(TB_ToAfterQtyUnitName);
		main_fm.add(TB_ToAfterShipPlanQtyUnitName);
		main_fm.add(TB_ToAfterPossibleQtyUnitName);
		
		main_fm.add(TB_MoveBrTotalQtyUnitName);
		main_fm.add(TB_MovePlQtyUnitName);
		main_fm.add(TB_MoveCsQtyUnitName);
		main_fm.add(TB_MoveCtQtyUnitName);
		main_fm.add(TB_MoveBrQtyUnitName);
		
		main_fm.add(TB_Com01);
		main_fm.add(TB_Com02);
		main_fm.add(TB_Com03);
			
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
		
		Object[][] RtSettingLocationMstRt = M100_LocationMstRt.RtLocationMstRt();
		
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
		
		RenewFg = true;
		
		if(ErrFg) {
			WT100_Stock_00_Search.StockSearch(0,0);
		}else{
			main_fm.setVisible(true);
		}
		//移動実行ボタン押下時の挙動
		EntryBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				String GetClCd				= B100_TextControl.Trim(B100_DefaultVariable.ClList[1][TB_ClCd.getSelectedIndex()]);
				String GetWhCd				= B100_TextControl.Trim(B100_DefaultVariable.WhList[1][TB_WhCd.getSelectedIndex()]);
				String GetItemCd			= B100_TextControl.Trim(TB_ItemCd.getText());
				String GetLot				= B100_TextControl.Trim(TB_Lot.getText());
				String GetExpDate			= B100_TextControl.TextToDate(TB_Expdate.getText());
				String GetActualDate		= B100_TextControl.TextToDate(TB_ActualDate.getText());
				
				String GetFromLoc			= B100_TextControl.Trim(TB_FromLoc.getText());
				String GetToLoc				= B100_TextControl.Trim(TB_ToLoc.getText());
				
				int GetMoveBrTotalQty		= B100_TextControl.TextToInt(TB_MoveBrTotalQty.getText());
				
				String GetCom01				= B100_TextControl.Trim(TB_Com01.getText());
				String GetCom02				= B100_TextControl.Trim(TB_Com02.getText());
				String GetCom03				= B100_TextControl.Trim(TB_Com03.getText());
				
				boolean KickFg = EntryCheck(
						GetClCd,
						GetWhCd,
						GetItemCd,
						GetLot,
						GetExpDate,
						GetActualDate,
						
						GetFromLoc,
						GetToLoc,
						
						GetMoveBrTotalQty,
						
						GetCom01,
						GetCom02,
						GetCom03
						);
				
				if(KickFg) {
					SetX=main_fm.getX();
					SetY=main_fm.getY();
	
					Loc_fm.setVisible(false);
					Loc_fm.dispose();
					
					main_fm.setVisible(false);
					main_fm.dispose();
					WT100_Stock_00_Search.StockSearch(0,0);
				}
			}
		});
		
		//総移動数フォーカス消失時の挙動
		TB_MoveBrTotalQty.addFocusListener(new FocusAdapter(){
			@Override
			public void focusLost(FocusEvent e){
				if(RenewFg) {
					RenewFg = false;
					int GetMoveTotalQty 	= B100_TextControl.TextToInt(TB_MoveBrTotalQty.getText());
					int GetPlUnitQty		= B100_TextControl.TextToInt(TB_PlUnitQty.getText());
					int GetCsUnitQty		= B100_TextControl.TextToInt(TB_CsUnitQty.getText());
					int GetCtUnitQty		= B100_TextControl.TextToInt(TB_CtUnitQty.getText());
					
					int SetPlQty = 0;
					int SetCsQty = 0;
					int SetCtQty = 0;
					int SetBrQty = GetMoveTotalQty;
					
					if(0<GetPlUnitQty) {
						SetPlQty = (int)(SetBrQty/GetPlUnitQty);
						SetBrQty = SetBrQty%GetPlUnitQty;
					}
					if(0<GetCsUnitQty) {
						SetCsQty = (int)(SetBrQty/GetCsUnitQty);
						SetBrQty = SetBrQty%GetCsUnitQty;
					}
					if(0<GetCtUnitQty) {
						SetCtQty = (int)(SetBrQty/GetCtUnitQty);
						SetBrQty = SetBrQty%GetCtUnitQty;
					}
					
					TB_MovePlQty.setText(""+ni.format(SetPlQty));
					TB_MoveCsQty.setText(""+ni.format(SetCsQty));
					TB_MoveCtQty.setText(""+ni.format(SetCtQty));
					TB_MoveBrQty.setText(""+ni.format(SetBrQty));
					
					ArrayList<String> ErrMsg = SetAfterVolArgumentControl(ArgumentSet);
					
					RenewFg = true;
				}
			}
		});
		
		//パレット移動数フォーカス消失時の挙動
		TB_MovePlQty.addFocusListener(new FocusAdapter(){
			@Override
			public void focusLost(FocusEvent e){
				if(RenewFg) {
					RenewFg = false;
					int GetMovePlQty		= B100_TextControl.TextToInt(TB_MovePlQty.getText());
					int GetMoveCsQty		= B100_TextControl.TextToInt(TB_MoveCsQty.getText());
					int GetMoveCtQty		= B100_TextControl.TextToInt(TB_MoveCtQty.getText());
					int GetMoveBrQty		= B100_TextControl.TextToInt(TB_MoveBrQty.getText());
					
					int GetPlUnitQty		= B100_TextControl.TextToInt(TB_PlUnitQty.getText());
					int GetCsUnitQty		= B100_TextControl.TextToInt(TB_CsUnitQty.getText());
					int GetCtUnitQty		= B100_TextControl.TextToInt(TB_CtUnitQty.getText());
					
					int SetTotalQty = GetMovePlQty*GetPlUnitQty+GetMoveCsQty*GetCsUnitQty+GetMoveCtQty*GetCtUnitQty+GetMoveBrQty;
					
					TB_MoveBrTotalQty.setText(""+ni.format(SetTotalQty));
					
					ArrayList<String> ErrMsg = SetAfterVolArgumentControl(ArgumentSet);
					
					RenewFg = true;
				}
			}
		});
		
		//ケース移動数フォーカス消失時の挙動
		TB_MoveCsQty.addFocusListener(new FocusAdapter(){
			@Override
			public void focusLost(FocusEvent e){
				if(RenewFg) {
					RenewFg = false;
					int GetMovePlQty		= B100_TextControl.TextToInt(TB_MovePlQty.getText());
					int GetMoveCsQty		= B100_TextControl.TextToInt(TB_MoveCsQty.getText());
					int GetMoveCtQty		= B100_TextControl.TextToInt(TB_MoveCtQty.getText());
					int GetMoveBrQty		= B100_TextControl.TextToInt(TB_MoveBrQty.getText());
					
					int GetPlUnitQty		= B100_TextControl.TextToInt(TB_PlUnitQty.getText());
					int GetCsUnitQty		= B100_TextControl.TextToInt(TB_CsUnitQty.getText());
					int GetCtUnitQty		= B100_TextControl.TextToInt(TB_CtUnitQty.getText());
					
					int SetTotalQty = GetMovePlQty*GetPlUnitQty+GetMoveCsQty*GetCsUnitQty+GetMoveCtQty*GetCtUnitQty+GetMoveBrQty;
					
					TB_MoveBrTotalQty.setText(""+ni.format(SetTotalQty));
					
					ArrayList<String> ErrMsg = SetAfterVolArgumentControl(ArgumentSet);
					
					RenewFg = true;
				}
			}
		});
		//カートン移動数フォーカス消失時の挙動
		TB_MoveCtQty.addFocusListener(new FocusAdapter(){
			@Override
			public void focusLost(FocusEvent e){
				if(RenewFg) {
					RenewFg = false;
					int GetMovePlQty		= B100_TextControl.TextToInt(TB_MovePlQty.getText());
					int GetMoveCsQty		= B100_TextControl.TextToInt(TB_MoveCsQty.getText());
					int GetMoveCtQty		= B100_TextControl.TextToInt(TB_MoveCtQty.getText());
					int GetMoveBrQty		= B100_TextControl.TextToInt(TB_MoveBrQty.getText());
					
					int GetPlUnitQty		= B100_TextControl.TextToInt(TB_PlUnitQty.getText());
					int GetCsUnitQty		= B100_TextControl.TextToInt(TB_CsUnitQty.getText());
					int GetCtUnitQty		= B100_TextControl.TextToInt(TB_CtUnitQty.getText());
					
					int SetTotalQty = GetMovePlQty*GetPlUnitQty+GetMoveCsQty*GetCsUnitQty+GetMoveCtQty*GetCtUnitQty+GetMoveBrQty;
					
					TB_MoveBrTotalQty.setText(""+ni.format(SetTotalQty));
					
					ArrayList<String> ErrMsg = SetAfterVolArgumentControl(ArgumentSet);
					
					RenewFg = true;
				}
			}
		});
		
		//バラ移動数フォーカス消失時の挙動
		TB_MoveBrQty.addFocusListener(new FocusAdapter(){
			@Override
			public void focusLost(FocusEvent e){
				if(RenewFg) {
					RenewFg = false;
					int GetMovePlQty		= B100_TextControl.TextToInt(TB_MovePlQty.getText());
					int GetMoveCsQty		= B100_TextControl.TextToInt(TB_MoveCsQty.getText());
					int GetMoveCtQty		= B100_TextControl.TextToInt(TB_MoveCtQty.getText());
					int GetMoveBrQty		= B100_TextControl.TextToInt(TB_MoveBrQty.getText());
					
					int GetPlUnitQty		= B100_TextControl.TextToInt(TB_PlUnitQty.getText());
					int GetCsUnitQty		= B100_TextControl.TextToInt(TB_CsUnitQty.getText());
					int GetCtUnitQty		= B100_TextControl.TextToInt(TB_CtUnitQty.getText());
					
					int SetTotalQty = GetMovePlQty*GetPlUnitQty+GetMoveCsQty*GetCsUnitQty+GetMoveCtQty*GetCtUnitQty+GetMoveBrQty;
					
					TB_MoveBrTotalQty.setText(""+ni.format(SetTotalQty));
					
					ArrayList<String> ErrMsg = SetAfterVolArgumentControl(ArgumentSet);
					
					RenewFg = true;
				}
			}
		});
		
		//移動先ロケーションフォーカス消失時の挙動
		TB_ToLoc.addFocusListener(new FocusAdapter(){
			@Override
			public void focusLost(FocusEvent e){
				if(RenewFg) {
					RenewFg = false;
					
					ArrayList<String> ErrMsg = SetAfterVolArgumentControl(ArgumentSet);
					
					if(null!=ErrMsg && 0<ErrMsg.size()) {
						String WST = "以下のエラーが発生しています";
						for(int i=0;i<ErrMsg.size();i++) {
							WST	= WST + "\n" + ErrMsg.get(i);
						}
						JOptionPane.showMessageDialog(null, WST);
						
						SetX=main_fm.getX();
						SetY=main_fm.getY();

						Loc_fm.setVisible(false);
						Loc_fm.dispose();
						
						main_fm.setVisible(false);
						main_fm.dispose();
						WT100_Stock_00_Search.StockSearch(0,0);
					}
					
					RenewFg = true;
				}
			}
		});
		
		//ロケ検索ボタン押下時の挙動
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
		
		//ロケーションマスタ検索ボタン押下時の挙動
		LocSearchKickBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					int RowCount = tableModel_msLoc.getRowCount();
					for(int i=0;i<RowCount;i++) {
						tableModel_msLoc.removeRow(0);
					}
					String SearchTgtClCd	= B100_TextControl.Trim(B100_DefaultVariable.ClList[1][TB_ClCd.getSelectedIndex()]);	//荷主コード
					String SearchTgtWhCd	= B100_TextControl.Trim(B100_DefaultVariable.WhList[1][TB_WhCd.getSelectedIndex()]);	//倉庫コード
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
		//ロケーションマスタ検索登録ボタン押下時の挙動
		LocEntry_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					int row_count 	= tableModel_msLoc.getRowCount();
					boolean SetFg 	= false;
					String GetLoc	= "";
					
					for(int i=0;i<row_count;i++){
						if((boolean)tableModel_msLoc.getValueAt(i, 0)) {
							SetFg = true;
							GetLoc = ""+tableModel_msLoc.getValueAt(i, 1+M100_LocationMstRt.ColLoc);
							TB_ToLoc.setText(GetLoc);
						}
					}
					
					if(SetFg) {
						Loc_fm.setVisible(false);
						ArrayList<String> ErrMsg = SetAfterVolArgumentControl(ArgumentSet);
						
						if(null!=ErrMsg && 0<ErrMsg.size()) {
							String WST = "以下のエラーが発生しています";
							for(int i=0;i<ErrMsg.size();i++) {
								WST	= WST + "\n" + ErrMsg.get(i);
							}
							JOptionPane.showMessageDialog(null, WST);
							
							SetX=main_fm.getX();
							SetY=main_fm.getY();

							Loc_fm.setVisible(false);
							Loc_fm.dispose();
							
							main_fm.setVisible(false);
							main_fm.dispose();
							WT100_Stock_00_Search.StockSearch(0,0);
						}
					}
					RenewFg = true;
				}
			}
		});
		
		//ロケーションマスタ検索チェックボックス操作時の挙動
		tableModel_msLoc.addTableModelListener(new TableModelListener(){
			public void tableChanged(TableModelEvent e){
				if(RenewFg) {
					RenewFg = false;
					int row_count = tableModel_msLoc.getRowCount();
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
		
		//ロケーションマスタ検索EXITボタン押下時の挙動
		LocExit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				Loc_fm.setVisible(false);
			}
		});

		//入力モード切替
		TB_EntryMode.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					if(TB_EntryMode.isSelected()) {
						TB_MoveBrTotalQty.setEditable(false);
						TB_MoveBrTotalQty.setBackground(B100_FrameParts.SelectColer("NoEntry"));
						
						int GetTB_PlUnitQty	= B100_TextControl.TextToInt(TB_PlUnitQty.getText());
						int GetTB_CsUnitQty	= B100_TextControl.TextToInt(TB_CsUnitQty.getText());
						int GetTB_CtUnitQty	= B100_TextControl.TextToInt(TB_CtUnitQty.getText());
						if(0<GetTB_PlUnitQty) {
							TB_MovePlQty.setEditable(true);
							TB_MovePlQty.setBackground(B100_FrameParts.SelectColer("Entry"));
						}
						
						if(0<GetTB_CsUnitQty) {
							TB_MoveCsQty.setEditable(true);
							TB_MoveCsQty.setBackground(B100_FrameParts.SelectColer("Entry"));
						}
						if(0<GetTB_CtUnitQty) {
							TB_MoveCtQty.setEditable(true);
							TB_MoveCtQty.setBackground(B100_FrameParts.SelectColer("Entry"));
						}
						TB_MoveBrQty.setEditable(true);
						TB_MoveBrQty.setBackground(B100_FrameParts.SelectColer("Entry"));
					}else {
						TB_MoveBrTotalQty.setEditable(true);
						TB_MoveBrTotalQty.setBackground(B100_FrameParts.SelectColer("Entry"));
						
						TB_MovePlQty.setEditable(false);
						TB_MoveCsQty.setEditable(false);
						TB_MoveCtQty.setEditable(false);
						TB_MoveBrQty.setEditable(false);
						
						TB_MovePlQty.setBackground(B100_FrameParts.SelectColer("NoEntry"));
						TB_MoveCsQty.setBackground(B100_FrameParts.SelectColer("NoEntry"));
						TB_MoveCtQty.setBackground(B100_FrameParts.SelectColer("NoEntry"));
						TB_MoveBrQty.setBackground(B100_FrameParts.SelectColer("NoEntry"));
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

				Loc_fm.setVisible(false);
				Loc_fm.dispose();
				
				main_fm.setVisible(false);
				main_fm.dispose();
				WT100_Stock_00_Search.StockSearch(0,0);
			}
		});
	}
	
	private static ArrayList<String> SetAfterVolArgumentControl(Object[] ArgumentSet) {
		int Col_TB_ClCd							=  0;
		int Col_TB_WhCd							=  1;
		int Col_TB_ItemCd						=  2;
		int Col_TB_ItemName						=  3;
		int Col_TB_Lot							=  4;
		int Col_TB_Expdate						=  5;
		int Col_TB_ActualDate					=  6;
		int Col_TB_PlUnitQty					=  7;
		int Col_TB_CsUnitQty					=  8;
		int Col_TB_CtUnitQty					=  9;
		int Col_TB_FromLoc						= 10;
		int Col_TB_FromLocName					= 11;
		int Col_TB_FromType						= 12;
		int Col_TB_FromQty						= 13;
		int Col_TB_FromShipPlanQty				= 14;
		int Col_TB_FromPossibleQty				= 15;
		int Col_TB_FromQtyUnitName				= 16;
		int Col_TB_FromShipPlanQtyUnitName		= 17;
		int Col_TB_FromPossibleQtyUnitName		= 18;
		int Col_TB_FromBrTotalQty				= 19;
		int Col_TB_FromPlQty					= 20;
		int Col_TB_FromCsQty					= 21;
		int Col_TB_FromCtQty					= 22;
		int Col_TB_FromBrQty					= 23;
		int Col_TB_FromBrTotalQtyUnitName		= 24;
		int Col_TB_FromPlQtyUnitName			= 25;
		int Col_TB_FromCsQtyUnitName			= 26;
		int Col_TB_FromCtQtyUnitName			= 27;
		int Col_TB_FromBrQtyUnitName			= 28;
		int Col_TB_FromAfterQty					= 29;
		int Col_TB_FromAfterShipPlanQty			= 30;
		int Col_TB_FromAfterPossibleQty			= 31;
		int Col_TB_FromAfterQtyUnitName			= 32;
		int Col_TB_FromAfterShipPlanQtyUnitName	= 33;
		int Col_TB_FromAfterPossibleQtyUnitName	= 34;
		int Col_TB_ToLoc						= 35;
		int Col_TBToLocName						= 36;
		int Col_TB_ToType						= 37;
		int Col_TB_ToQty						= 38;
		int Col_TB_ToShipPlanQty				= 39;
		int Col_TB_ToPossibleQty				= 40;
		int Col_TB_ToQtyUnitName				= 41;
		int Col_TB_ToShipPlanQtyUnitName		= 42;
		int Col_TB_ToPossibleQtyUnitName		= 43;
		int Col_TB_ToBrTotalQty					= 44;
		int Col_TB_ToPlQty						= 45;
		int Col_TB_ToCsQty						= 46;
		int Col_TB_ToCtQty						= 47;
		int Col_TB_ToBrQty						= 48;
		int Col_TB_ToBrTotalQtyUnitName			= 49;
		int Col_TB_ToPlQtyUnitName				= 50;
		int Col_TB_ToCsQtyUnitName				= 51;
		int Col_TB_ToCtQtyUnitName				= 52;
		int Col_TB_ToBrQtyUnitName				= 53;
		int Col_TB_ToAfterQty					= 54;
		int Col_TB_ToAfterShipPlanQty			= 55;
		int Col_TB_ToAfterPossibleQty			= 56;
		int Col_TB_ToAfterQtyUnitName			= 57;
		int Col_TB_ToAfterShipPlanQtyUnitName	= 58;
		int Col_TB_ToAfterPossibleQtyUnitName	= 59;
		int Col_TB_MoveBrTotalQty				= 60;
		int Col_TB_MovePlQty					= 61;
		int Col_TB_MoveCsQty					= 62;
		int Col_TB_MoveCtQty					= 63;
		int Col_TB_MoveBrQty					= 64;
		int Col_TB_MoveBrTotalQtyUnitName		= 65;
		int Col_TB_MovePlQtyUnitName			= 66;
		int Col_TB_MoveCsQtyUnitName			= 67;
		int Col_TB_MoveCtQtyUnitName			= 68;
		int Col_TB_MoveBrQtyUnitName			= 69;
		int Col_TB_EntryMode					= 70;
		
		ArrayList<String> ErrMsg	= SetAfterVolSet(
				/*移動商品情報*/
				(JComboBox)ArgumentSet[Col_TB_ClCd],
				(JComboBox)ArgumentSet[Col_TB_WhCd],
				
				(JTextField)ArgumentSet[Col_TB_ItemCd],
				(JTextField)ArgumentSet[Col_TB_ItemName],
				(JTextField)ArgumentSet[Col_TB_Lot],
				(JFormattedTextField)ArgumentSet[Col_TB_Expdate],
				(JFormattedTextField)ArgumentSet[Col_TB_ActualDate],
				
				(JFormattedTextField)ArgumentSet[Col_TB_PlUnitQty],
				(JFormattedTextField)ArgumentSet[Col_TB_CsUnitQty],
				(JFormattedTextField)ArgumentSet[Col_TB_CtUnitQty],
				
				/*移動元情報*/
				(JTextField)ArgumentSet[Col_TB_FromLoc],
				(JTextField)ArgumentSet[Col_TB_FromLocName],
				(JComboBox)ArgumentSet[Col_TB_FromType],
				(JFormattedTextField)ArgumentSet[Col_TB_FromQty],
				(JFormattedTextField)ArgumentSet[Col_TB_FromShipPlanQty],
				(JFormattedTextField)ArgumentSet[Col_TB_FromPossibleQty],
				(JLabel)ArgumentSet[Col_TB_FromQtyUnitName],
				(JLabel)ArgumentSet[Col_TB_FromShipPlanQtyUnitName],
				(JLabel)ArgumentSet[Col_TB_FromPossibleQtyUnitName],
				
				(JFormattedTextField)ArgumentSet[Col_TB_FromBrTotalQty],
				(JFormattedTextField)ArgumentSet[Col_TB_FromPlQty],
				(JFormattedTextField)ArgumentSet[Col_TB_FromCsQty],
				(JFormattedTextField)ArgumentSet[Col_TB_FromCtQty],
				(JFormattedTextField)ArgumentSet[Col_TB_FromBrQty],
				
				(JLabel)ArgumentSet[Col_TB_FromBrTotalQtyUnitName],
				(JLabel)ArgumentSet[Col_TB_FromPlQtyUnitName],
				(JLabel)ArgumentSet[Col_TB_FromCsQtyUnitName],
				(JLabel)ArgumentSet[Col_TB_FromCtQtyUnitName],
				(JLabel)ArgumentSet[Col_TB_FromBrQtyUnitName],
				
				(JFormattedTextField)ArgumentSet[Col_TB_FromAfterQty],
				(JFormattedTextField)ArgumentSet[Col_TB_FromAfterShipPlanQty],
				(JFormattedTextField)ArgumentSet[Col_TB_FromAfterPossibleQty],
				(JLabel)ArgumentSet[Col_TB_FromAfterQtyUnitName],
				(JLabel)ArgumentSet[Col_TB_FromAfterShipPlanQtyUnitName],
				(JLabel)ArgumentSet[Col_TB_FromAfterPossibleQtyUnitName],
				
				/*移動先情報*/
				(JTextField)ArgumentSet[Col_TB_ToLoc],
				(JTextField)ArgumentSet[Col_TBToLocName],
				(JComboBox)ArgumentSet[Col_TB_ToType],
				(JFormattedTextField)ArgumentSet[Col_TB_ToQty],
				(JFormattedTextField)ArgumentSet[Col_TB_ToShipPlanQty],
				(JFormattedTextField)ArgumentSet[Col_TB_ToPossibleQty],
				(JLabel)ArgumentSet[Col_TB_ToQtyUnitName],
				(JLabel)ArgumentSet[Col_TB_ToShipPlanQtyUnitName],
				(JLabel)ArgumentSet[Col_TB_ToPossibleQtyUnitName],
				
				(JFormattedTextField)ArgumentSet[Col_TB_ToBrTotalQty],
				(JFormattedTextField)ArgumentSet[Col_TB_ToPlQty],
				(JFormattedTextField)ArgumentSet[Col_TB_ToCsQty],
				(JFormattedTextField)ArgumentSet[Col_TB_ToCtQty],
				(JFormattedTextField)ArgumentSet[Col_TB_ToBrQty],
				
				(JLabel)ArgumentSet[Col_TB_ToBrTotalQtyUnitName],
				(JLabel)ArgumentSet[Col_TB_ToPlQtyUnitName],
				(JLabel)ArgumentSet[Col_TB_ToCsQtyUnitName],
				(JLabel)ArgumentSet[Col_TB_ToCtQtyUnitName],
				(JLabel)ArgumentSet[Col_TB_ToBrQtyUnitName],
				
				(JFormattedTextField)ArgumentSet[Col_TB_ToAfterQty],
				(JFormattedTextField)ArgumentSet[Col_TB_ToAfterShipPlanQty],
				(JFormattedTextField)ArgumentSet[Col_TB_ToAfterPossibleQty],
				(JLabel)ArgumentSet[Col_TB_ToAfterQtyUnitName],
				(JLabel)ArgumentSet[Col_TB_ToAfterShipPlanQtyUnitName],
				(JLabel)ArgumentSet[Col_TB_ToAfterPossibleQtyUnitName],
				
				/*移動数情報*/
				(JFormattedTextField)ArgumentSet[Col_TB_MoveBrTotalQty],
				(JFormattedTextField)ArgumentSet[Col_TB_MovePlQty],
				(JFormattedTextField)ArgumentSet[Col_TB_MoveCsQty],
				(JFormattedTextField)ArgumentSet[Col_TB_MoveCtQty],
				(JFormattedTextField)ArgumentSet[Col_TB_MoveBrQty],
				
				(JLabel)ArgumentSet[Col_TB_MoveBrTotalQtyUnitName],
				(JLabel)ArgumentSet[Col_TB_MovePlQtyUnitName],
				(JLabel)ArgumentSet[Col_TB_MoveCsQtyUnitName],
				(JLabel)ArgumentSet[Col_TB_MoveCtQtyUnitName],
				(JLabel)ArgumentSet[Col_TB_MoveBrQtyUnitName],
				(JCheckBox)ArgumentSet[Col_TB_EntryMode]
				);
		
		return ErrMsg;
		
	}
	
	//入力内容に応じて表示内容を制御する
	private static ArrayList<String> SetAfterVolSet(
			/*移動商品情報*/
			JComboBox TB_ClCd,
			JComboBox TB_WhCd,
			
			JTextField TB_ItemCd,
			JTextField TB_ItemName,
			JTextField TB_Lot,
			JFormattedTextField TB_Expdate,
			JFormattedTextField TB_ActualDate,
			
			JFormattedTextField TB_PlUnitQty,
			JFormattedTextField TB_CsUnitQty,
			JFormattedTextField TB_CtUnitQty,
			
			/*移動元情報*/
			JTextField TB_FromLoc,
			JTextField TB_FromLocName,
			JComboBox TB_FromType,
			JFormattedTextField TB_FromQty,
			JFormattedTextField TB_FromShipPlanQty,
			JFormattedTextField TB_FromPossibleQty,
			JLabel TB_FromQtyUnitName,
			JLabel TB_FromShipPlanQtyUnitName,
			JLabel TB_FromPossibleQtyUnitName,
			
			JFormattedTextField TB_FromBrTotalQty,
			JFormattedTextField TB_FromPlQty,
			JFormattedTextField TB_FromCsQty,
			JFormattedTextField TB_FromCtQty,
			JFormattedTextField TB_FromBrQty,
			
			JLabel TB_FromBrTotalQtyUnitName,
			JLabel TB_FromPlQtyUnitName,
			JLabel TB_FromCsQtyUnitName,
			JLabel TB_FromCtQtyUnitName,
			JLabel TB_FromBrQtyUnitName,
			
			JFormattedTextField TB_FromAfterQty,
			JFormattedTextField TB_FromAfterShipPlanQty,
			JFormattedTextField TB_FromAfterPossibleQty,
			JLabel TB_FromAfterQtyUnitName,
			JLabel TB_FromAfterShipPlanQtyUnitName,
			JLabel TB_FromAfterPossibleQtyUnitName,
			
			/*移動先情報*/
			JTextField TB_ToLoc,
			JTextField TBToLocName,
			JComboBox TB_ToType,
			JFormattedTextField TB_ToQty,
			JFormattedTextField TB_ToShipPlanQty,
			JFormattedTextField TB_ToPossibleQty,
			JLabel TB_ToQtyUnitName,
			JLabel TB_ToShipPlanQtyUnitName,
			JLabel TB_ToPossibleQtyUnitName,
			
			JFormattedTextField TB_ToBrTotalQty,
			JFormattedTextField TB_ToPlQty,
			JFormattedTextField TB_ToCsQty,
			JFormattedTextField TB_ToCtQty,
			JFormattedTextField TB_ToBrQty,
			
			JLabel TB_ToBrTotalQtyUnitName,
			JLabel TB_ToPlQtyUnitName,
			JLabel TB_ToCsQtyUnitName,
			JLabel TB_ToCtQtyUnitName,
			JLabel TB_ToBrQtyUnitName,
			
			JFormattedTextField TB_ToAfterQty,
			JFormattedTextField TB_ToAfterShipPlanQty,
			JFormattedTextField TB_ToAfterPossibleQty,
			JLabel TB_ToAfterQtyUnitName,
			JLabel TB_ToAfterShipPlanQtyUnitName,
			JLabel TB_ToAfterPossibleQtyUnitName,
			
			/*移動数情報*/
			JFormattedTextField TB_MoveBrTotalQty,
			JFormattedTextField TB_MovePlQty,
			JFormattedTextField TB_MoveCsQty,
			JFormattedTextField TB_MoveCtQty,
			JFormattedTextField TB_MoveBrQty,
			
			JLabel TB_MoveBrTotalQtyUnitName,
			JLabel TB_MovePlQtyUnitName,
			JLabel TB_MoveCsQtyUnitName,
			JLabel TB_MoveCtQtyUnitName,
			JLabel TB_MoveBrQtyUnitName,
			JCheckBox TB_EntryMode
			) {
		
		ArrayList<String> ErrMsg = new ArrayList<String>();
		NumberFormat ni = NumberFormat.getNumberInstance();
		
		String GetClCd			= B100_TextControl.Trim(B100_DefaultVariable.ClList[1][TB_ClCd.getSelectedIndex()]);
		String GetWhCd			= B100_TextControl.Trim(B100_DefaultVariable.WhList[1][TB_WhCd.getSelectedIndex()]);
		String GetFromLoc		= B100_TextControl.Trim(TB_FromLoc.getText());
		String GetItemCd		= B100_TextControl.Trim(TB_ItemCd.getText());
		String GetLot			= B100_TextControl.Trim(TB_Lot.getText());
		String GetExpdate		= B100_TextControl.Trim(TB_Expdate.getText());
		String GetActualDate	= B100_TextControl.Trim(TB_ActualDate.getText());
		
		String GetToLoc			= B100_TextControl.Trim(TB_ToLoc.getText());
		int MoveQty 			= B100_TextControl.TextToInt(TB_MoveBrTotalQty.getText());
		int MovePlQty 			= 0;
		int MoveCsQty 			= 0;
		int MoveCtQty 			= 0;
		int MoveBrQty 			= MoveQty;
		
		//移動先数量情報一旦初期化
		TBToLocName.setText("");
		TB_ToType.setSelectedIndex(0);
		TB_ToQty.setText(""+ni.format(0));
		TB_ToShipPlanQty.setText(""+ni.format(0));
		TB_ToPossibleQty.setText(""+ni.format(0));
		
		TB_ToBrTotalQty.setText(""+ni.format(0));
		TB_ToPlQty.setText(""+ni.format(0));
		TB_ToCsQty.setText(""+ni.format(0));
		TB_ToCtQty.setText(""+ni.format(0));
		TB_ToBrQty.setText(""+ni.format(0));
		
		TB_ToAfterQty.setText(""+ni.format(0));
		TB_ToAfterShipPlanQty.setText(""+ni.format(0));
		TB_ToAfterPossibleQty.setText(""+ni.format(0));
		
		//移動先ロケ情報をセット
		if(!"".equals(GetToLoc)) {
			Object[][] ToLocData	= LocSearch(
						GetClCd,
						GetWhCd,
						GetToLoc,
						"",
						""
						);
			if(1==ToLocData.length) {
				TBToLocName.setText((String)ToLocData[0][M100_LocationMstRt.ColLocName]);
				TB_ToType.setSelectedIndex(B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.LocType[1]	,""+(int)ToLocData[0][M100_LocationMstRt.ColType] ,true) );
			}else {
				TB_ToLoc.setText("");
				GetToLoc	= "";
			}
			
		}
		
		Object[][] FromStockData	= StockRt(
				GetWhCd,
				GetClCd,
				GetFromLoc,
				GetItemCd,
				GetLot,
				GetExpdate,
				GetActualDate
				);
		
		Object[][] ToStockData	= null;
		
		if(!"".equals(GetToLoc)) {
			ToStockData= StockRt(
					GetWhCd,
					GetClCd,
					GetToLoc,
					GetItemCd,
					GetLot,
					GetExpdate,
					GetActualDate
					);
		}
		
		if(1==FromStockData.length) {
			String GetFromClCd			= (String)FromStockData[0][T100_StockRt.ColClCd];				//荷主コード
			String GetFromCLName		= (String)FromStockData[0][T100_StockRt.ColCLName];			//荷主表記名
			String GetFromWhCd			= (String)FromStockData[0][T100_StockRt.ColWhCd];				//倉庫コード
			String GetFromClWHName		= (String)FromStockData[0][T100_StockRt.ColClWHName];			//担当倉庫名
			String GetFromClGpCD		= (String)FromStockData[0][T100_StockRt.ColClGpCD];			//荷主グループCD
			String GetFromClGpName		= (String)FromStockData[0][T100_StockRt.ColClGpName];			//グループ名1
				   GetFromLoc			= (String)FromStockData[0][T100_StockRt.ColLoc];				//ロケーション
			String GetFromLocName		= (String)FromStockData[0][T100_StockRt.ColLocName];			//ロケーション名
			int GetFromType				= (int)FromStockData[0][T100_StockRt.ColType];					//ロケタイプ
			String GetFromItemCd		= (String)FromStockData[0][T100_StockRt.ColItemCd];			//商品コード
			String GetFromLot			= (String)FromStockData[0][T100_StockRt.ColLot];				//ロット
			String GetFromExpdate		= (String)FromStockData[0][T100_StockRt.ColExpdate];			//消費期限
			String GetFromActualDate	= (String)FromStockData[0][T100_StockRt.ColActualDate];		//入荷実績日
			int GetFromQty				= (int)FromStockData[0][T100_StockRt.ColQty];					//総数量
			int GetFromShipPlanQty		= (int)FromStockData[0][T100_StockRt.ColShipPlanQty];			//引当済総数
			int GetFromPossibleQty		= (int)FromStockData[0][T100_StockRt.ColPossibleQty];			//出荷可能総数
			String GetFromItemName		= (String)FromStockData[0][T100_StockRt.ColItemName];			//商品名
			String GetFromItemName01	= (String)FromStockData[0][T100_StockRt.ColItemName01];		//商品表記名
			String GetFromItemName02	= (String)FromStockData[0][T100_StockRt.ColItemName02];		//商品正式名
			String GetFromItemName03	= (String)FromStockData[0][T100_StockRt.ColItemName03];		//商品略名
			String GetFromClItemCd		= (String)FromStockData[0][T100_StockRt.ColClItemCd];			//荷主商品コード
			String GetFromJanCd			= (String)FromStockData[0][T100_StockRt.ColJanCd];				//ソースマーク_BCD（バラ）
			String GetFromItemMdNo		= (String)FromStockData[0][T100_StockRt.ColItemMdNo];			//商品型番
			int GetFromCtUnitQty		= (int)FromStockData[0][T100_StockRt.ColCtUnitQty];			//カートン入数
			int GetFromCsUnitQty		= (int)FromStockData[0][T100_StockRt.ColCsUnitQty];			//ケース入数
			int GetFromPlUnitQty		= (int)FromStockData[0][T100_StockRt.ColPlUnitQty];			//パレット入数
			String GetFromUnitName		= (String)FromStockData[0][T100_StockRt.ColUnitName];			//商品単位
			String GetFromCtUnitName	= (String)FromStockData[0][T100_StockRt.ColCtUnitName];		//カートン商品単位
			String GetFromCsUnitName	= (String)FromStockData[0][T100_StockRt.ColCsUnitName];		//ケース商品単位
			String GetFromPlUnitName	= (String)FromStockData[0][T100_StockRt.ColPlUnitName];		//パレット商品単位
			String GetFromEntryDate		= (String)FromStockData[0][T100_StockRt.ColEntryDate];		//登録日時
			String GetFromUpdateDate	= (String)FromStockData[0][T100_StockRt.ColUpdateDate];		//更新日時
			String GetFromEntryUser		= (String)FromStockData[0][T100_StockRt.ColEntryUser];		//登録者
			String GetFromUpdateUser	= (String)FromStockData[0][T100_StockRt.ColUpdateUser];		//更新者
			int GetFromBrQty			= (int)FromStockData[0][T100_StockRt.ColBrQty];				//バラ数量
			int GetFromBrShipPlanQty	= (int)FromStockData[0][T100_StockRt.ColBrShipPlanQty];		//引当済バラ数
			int GetFromBrPossibleQty	= (int)FromStockData[0][T100_StockRt.ColBrPossibleQty];		//出荷可能バラ数
			int GetFromCtQty			= (int)FromStockData[0][T100_StockRt.ColCtQty];				//カートン数量
			int GetFromCtShipPlanQty	= (int)FromStockData[0][T100_StockRt.ColCtShipPlanQty];		//引当済カートン数
			int GetFromCtPossibleQty	= (int)FromStockData[0][T100_StockRt.ColCtPossibleQty];		//出荷可能カートン数
			int GetFromCsQty			= (int)FromStockData[0][T100_StockRt.ColCsQty];				//ケース数量
			int GetFromCsShipPlanQty	= (int)FromStockData[0][T100_StockRt.ColCsShipPlanQty];		//引当済ケース数
			int GetFromCsPossibleQty	= (int)FromStockData[0][T100_StockRt.ColCsPossibleQty];		//出荷可能ケース数
			int GetFromPlQty			= (int)FromStockData[0][T100_StockRt.ColPlQty];				//パレット数量
			int GetFromPlShipPlanQty	= (int)FromStockData[0][T100_StockRt.ColPlShipPlanQty];		//引当済パレット数
			int GetFromPlPossibleQty	= (int)FromStockData[0][T100_StockRt.ColPlPossibleQty];		//出荷可能パレット数
			
			
			int MovePossibleBrTotalQty	= GetFromQty-GetFromShipPlanQty;
			int MovePossiblePlQty		= 0;
			int MovePossibleCsQty		= 0;
			int MovePossibleCtQty		= 0;
			int MovePossibleBrQty		= GetFromQty-GetFromShipPlanQty;
			
			if(0<GetFromPlUnitQty) {
				MovePossiblePlQty 	= (int)(MovePossibleBrQty/GetFromPlUnitQty);
				MovePossibleBrQty 	= (int)(MovePossibleBrQty%GetFromPlUnitQty);
				
				MovePlQty			= (int)(MoveBrQty/GetFromPlUnitQty);
				MoveBrQty			= (int)(MoveBrQty%GetFromPlUnitQty);
			}
			
			if(0<GetFromCsUnitQty) {
				MovePossibleCsQty 	= (int)(MovePossibleBrQty/GetFromCsUnitQty);
				MovePossibleBrQty 	= (int)(MovePossibleBrQty%GetFromCsUnitQty);
				
				MoveCsQty			= (int)(MoveBrQty/GetFromCsUnitQty);
				MoveBrQty			= (int)(MoveBrQty%GetFromCsUnitQty);
			}
			
			if(0<GetFromCtUnitQty) {
				MovePossibleCtQty 	= (int)(MovePossibleBrQty/GetFromCtUnitQty);
				MovePossibleBrQty 	= (int)(MovePossibleBrQty%GetFromCtUnitQty);
				
				MoveCtQty			= (int)(MoveBrQty/GetFromCtUnitQty);
				MoveBrQty			= (int)(MoveBrQty%GetFromCtUnitQty);
			}
			
			TB_ItemCd.setText(GetFromItemCd);
			TB_ItemName.setText(GetFromItemName01);
			TB_Lot.setText(GetFromLot);
			TB_Expdate.setText(GetFromExpdate);
			TB_ActualDate.setText(GetFromActualDate);
			
			TB_PlUnitQty.setText(""+ni.format(GetFromPlUnitQty));
			TB_CsUnitQty.setText(""+ni.format(GetFromCsUnitQty));
			TB_CtUnitQty.setText(""+ni.format(GetFromCtUnitQty));
			
			TB_FromLoc.setText(GetFromLoc);
			TB_FromLocName.setText(GetFromLocName);
			TB_FromQty.setText(""+ni.format(GetFromQty));
			TB_FromShipPlanQty.setText(""+ni.format(GetFromShipPlanQty));
			TB_FromPossibleQty.setText(""+ni.format(GetFromPossibleQty));
			TB_FromQtyUnitName.setText(GetFromUnitName);
			TB_FromShipPlanQtyUnitName.setText(GetFromUnitName);
			TB_FromPossibleQtyUnitName.setText(GetFromUnitName);
			
			TB_FromBrTotalQty.setText(""+ni.format(MovePossibleBrTotalQty));
			TB_FromPlQty.setText(""+ni.format(MovePossiblePlQty));
			TB_FromCsQty.setText(""+ni.format(MovePossibleCsQty));
			TB_FromCtQty.setText(""+ni.format(MovePossibleCtQty));
			TB_FromBrQty.setText(""+ni.format(MovePossibleBrQty));
			
			TB_FromBrTotalQtyUnitName.setText(GetFromUnitName);
			TB_FromPlQtyUnitName.setText(GetFromPlUnitName);
			TB_FromCsQtyUnitName.setText(GetFromCsUnitName);
			TB_FromCtQtyUnitName.setText(GetFromCtUnitName);
			TB_FromBrQtyUnitName.setText(GetFromUnitName);
			
			TB_FromAfterQty.setText(""+ni.format(GetFromQty));
			TB_FromAfterShipPlanQty.setText(""+ni.format(GetFromShipPlanQty));
			TB_FromAfterPossibleQty.setText(""+ni.format(GetFromPossibleQty));
			TB_FromAfterQtyUnitName.setText(GetFromUnitName);
			TB_FromAfterShipPlanQtyUnitName.setText(GetFromUnitName);
			TB_FromAfterPossibleQtyUnitName.setText(GetFromUnitName);
			
			TB_ToQtyUnitName.setText(GetFromUnitName);
			TB_ToShipPlanQtyUnitName.setText(GetFromUnitName);
			TB_ToPossibleQtyUnitName.setText(GetFromUnitName);
			
			TB_ToBrTotalQtyUnitName.setText(GetFromUnitName);
			TB_ToPlQtyUnitName.setText(GetFromPlUnitName);
			TB_ToCsQtyUnitName.setText(GetFromCsUnitName);
			TB_ToCtQtyUnitName.setText(GetFromCtUnitName);
			TB_ToBrQtyUnitName.setText(GetFromUnitName);
			
			TB_ToAfterQtyUnitName.setText(GetFromUnitName);
			TB_ToAfterShipPlanQtyUnitName.setText(GetFromUnitName);
			TB_ToAfterPossibleQtyUnitName.setText(GetFromUnitName);
			
			TB_MoveBrTotalQtyUnitName.setText(GetFromUnitName);
			TB_MovePlQtyUnitName.setText(GetFromPlUnitName);
			TB_MoveCsQtyUnitName.setText(GetFromCsUnitName);
			TB_MoveCtQtyUnitName.setText(GetFromCtUnitName);
			TB_MoveBrQtyUnitName.setText(GetFromUnitName);
			
			
			TB_ClCd.setSelectedIndex(	B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.ClList[1]		,GetFromClCd ,true) );
			TB_WhCd.setSelectedIndex(	B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.WhList[1]		,GetFromWhCd ,true) );
			TB_FromType.setSelectedIndex(B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.LocType[1]	,GetFromType+"" ,true) );
			
			TB_MovePlQty.setText(""+ni.format(MovePlQty));
			TB_MoveCsQty.setText(""+ni.format(MoveCsQty));
			TB_MoveCtQty.setText(""+ni.format(MoveCtQty));
			TB_MoveBrQty.setText(""+ni.format(MoveBrQty));
			
			
			if(null==ToStockData||0==ToStockData.length) {
				
			}else if(1<ToStockData.length) {
				ErrMsg.add("なぜか移動先在庫が複数検索されました");
			}else {
				String GetToClCd		= (String)ToStockData[0][T100_StockRt.ColClCd];			//荷主コード
				String GetToCLName		= (String)ToStockData[0][T100_StockRt.ColCLName];			//荷主表記名
				String GetToWhCd		= (String)ToStockData[0][T100_StockRt.ColWhCd];			//倉庫コード
				String GetToClWHName	= (String)ToStockData[0][T100_StockRt.ColClWHName];		//担当倉庫名
				String GetToClGpCD		= (String)ToStockData[0][T100_StockRt.ColClGpCD];			//荷主グループCD
				String GetToClGpName	= (String)ToStockData[0][T100_StockRt.ColClGpName];		//グループ名1
					   GetToLoc			= (String)ToStockData[0][T100_StockRt.ColLoc];				//ロケーション
				String GetToLocName		= (String)ToStockData[0][T100_StockRt.ColLocName];			//ロケーション名
				int GetToType			= (int)ToStockData[0][T100_StockRt.ColType];				//ロケタイプ
				String GetToItemCd		= (String)ToStockData[0][T100_StockRt.ColItemCd];			//商品コード
				String GetToLot			= (String)ToStockData[0][T100_StockRt.ColLot];				//ロット
				String GetToExpdate		= (String)ToStockData[0][T100_StockRt.ColExpdate];			//消費期限
				String GetToActualDate	= (String)ToStockData[0][T100_StockRt.ColActualDate];		//入荷実績日
				int GetToQty			= (int)ToStockData[0][T100_StockRt.ColQty];				//総数量
				int GetToShipPlanQty	= (int)ToStockData[0][T100_StockRt.ColShipPlanQty];		//引当済総数
				int GetToPossibleQty	= (int)ToStockData[0][T100_StockRt.ColPossibleQty];		//出荷可能総数
				String GetToItemName	= (String)ToStockData[0][T100_StockRt.ColItemName];		//商品名
				String GetToItemName01	= (String)ToStockData[0][T100_StockRt.ColItemName01];		//商品表記名
				String GetToItemName02	= (String)ToStockData[0][T100_StockRt.ColItemName02];		//商品正式名
				String GetToItemName03	= (String)ToStockData[0][T100_StockRt.ColItemName03];		//商品略名
				String GetToClItemCd	= (String)ToStockData[0][T100_StockRt.ColClItemCd];		//荷主商品コード
				String GetToJanCd		= (String)ToStockData[0][T100_StockRt.ColJanCd];			//ソースマーク_BCD（バラ）
				String GetToItemMdNo	= (String)ToStockData[0][T100_StockRt.ColItemMdNo];		//商品型番
				int GetToCtUnitQty		= (int)ToStockData[0][T100_StockRt.ColCtUnitQty];			//カートン入数
				int GetToCsUnitQty		= (int)ToStockData[0][T100_StockRt.ColCsUnitQty];			//ケース入数
				int GetToPlUnitQty		= (int)ToStockData[0][T100_StockRt.ColPlUnitQty];			//パレット入数
				String GetToUnitName	= (String)ToStockData[0][T100_StockRt.ColUnitName];		//商品単位
				String GetToCtUnitName	= (String)ToStockData[0][T100_StockRt.ColCtUnitName];		//カートン商品単位
				String GetToCsUnitName	= (String)ToStockData[0][T100_StockRt.ColCsUnitName];		//ケース商品単位
				String GetToPlUnitName	= (String)ToStockData[0][T100_StockRt.ColPlUnitName];		//パレット商品単位
				String GetToEntryDate	= (String)ToStockData[0][T100_StockRt.ColEntryDate];		//登録日時
				String GetToUpdateDate	= (String)ToStockData[0][T100_StockRt.ColUpdateDate];		//更新日時
				String GetToEntryUser	= (String)ToStockData[0][T100_StockRt.ColEntryUser];		//登録者
				String GetToUpdateUser	= (String)ToStockData[0][T100_StockRt.ColUpdateUser];		//更新者
				int GetToBrQty			= (int)ToStockData[0][T100_StockRt.ColBrQty];				//バラ数量
				int GetToBrShipPlanQty	= (int)ToStockData[0][T100_StockRt.ColBrShipPlanQty];		//引当済バラ数
				int GetToBrPossibleQty	= (int)ToStockData[0][T100_StockRt.ColBrPossibleQty];		//出荷可能バラ数
				int GetToCtQty			= (int)ToStockData[0][T100_StockRt.ColCtQty];				//カートン数量
				int GetToCtShipPlanQty	= (int)ToStockData[0][T100_StockRt.ColCtShipPlanQty];		//引当済カートン数
				int GetToCtPossibleQty	= (int)ToStockData[0][T100_StockRt.ColCtPossibleQty];		//出荷可能カートン数
				int GetToCsQty			= (int)ToStockData[0][T100_StockRt.ColCsQty];				//ケース数量
				int GetToCsShipPlanQty	= (int)ToStockData[0][T100_StockRt.ColCsShipPlanQty];		//引当済ケース数
				int GetToCsPossibleQty	= (int)ToStockData[0][T100_StockRt.ColCsPossibleQty];		//出荷可能ケース数
				int GetToPlQty			= (int)ToStockData[0][T100_StockRt.ColPlQty];				//パレット数量
				int GetToPlShipPlanQty	= (int)ToStockData[0][T100_StockRt.ColPlShipPlanQty];		//引当済パレット数
				int GetToPlPossibleQty	= (int)ToStockData[0][T100_StockRt.ColPlPossibleQty];		//出荷可能パレット数
				
				TB_ToQty.setText(""+ni.format(GetToQty));
				TB_ToShipPlanQty.setText(""+ni.format(GetToShipPlanQty));
				TB_ToPossibleQty.setText(""+ni.format(GetToPossibleQty));
				
				TB_ToBrTotalQty.setText(""+ni.format(GetToQty));
				TB_ToPlQty.setText(""+ni.format(GetToPlQty));
				TB_ToCsQty.setText(""+ni.format(GetToCsQty));
				TB_ToCtQty.setText(""+ni.format(GetToCtQty));
				TB_ToBrQty.setText(""+ni.format(GetToBrQty));
				
				TB_ToAfterQty.setText(""+ni.format(GetToQty));
				TB_ToAfterShipPlanQty.setText(""+ni.format(GetToShipPlanQty));
				TB_ToAfterPossibleQty.setText(""+ni.format(GetToPossibleQty));
				
				TBToLocName.setText(GetToLocName);
				TB_ToType.setSelectedIndex(B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.LocType[1]	,GetToType+"" ,true) );
			}
		}else {
			ErrMsg.add("移動元在庫が特定できません");
		}
		
		//移動後在庫を計算
		boolean ShipPlovisionTgtFg = true;
		
		String[] ShipPlovisionUnTgtList = B100_DefaultVariable.ShipPlovisionUnTgtList;
		
		int GetTB_MoveBrTotalQty		= B100_TextControl.TextToInt(TB_MoveBrTotalQty.getText());
		int GetTB_MovePlQty				= B100_TextControl.TextToInt(TB_MovePlQty.getText());
		int GetTB_MoveCsQty				= B100_TextControl.TextToInt(TB_MoveCsQty.getText());
		int GetTB_MoveCtQty				= B100_TextControl.TextToInt(TB_MoveCtQty.getText());
		int GetTB_MoveBrQty				= B100_TextControl.TextToInt(TB_MoveBrQty.getText());
		
		if(0<GetTB_MoveBrTotalQty) {
			TB_MoveBrTotalQty.setForeground(B100_FrameParts.SelectColer("Red"));
			TB_MovePlQty.setForeground(B100_FrameParts.SelectColer("Red"));
			TB_MoveCsQty.setForeground(B100_FrameParts.SelectColer("Red"));
			TB_MoveCtQty.setForeground(B100_FrameParts.SelectColer("Red"));
			TB_MoveBrQty.setForeground(B100_FrameParts.SelectColer("Red"));
			
			TB_FromAfterQty.setForeground(B100_FrameParts.SelectColer("Red"));
			TB_FromAfterShipPlanQty.setForeground(B100_FrameParts.SelectColer("Red"));
			TB_FromAfterPossibleQty.setForeground(B100_FrameParts.SelectColer("Red"));
			
			TB_ToBrTotalQty.setForeground(B100_FrameParts.SelectColer("Red"));
			TB_ToPlQty.setForeground(B100_FrameParts.SelectColer("Red"));
			TB_ToCsQty.setForeground(B100_FrameParts.SelectColer("Red"));
			TB_ToCtQty.setForeground(B100_FrameParts.SelectColer("Red"));
			TB_ToBrQty.setForeground(B100_FrameParts.SelectColer("Red"));
			
			TB_ToAfterQty.setForeground(B100_FrameParts.SelectColer("Red"));
			TB_ToAfterShipPlanQty.setForeground(B100_FrameParts.SelectColer("Red"));
			TB_ToAfterPossibleQty.setForeground(B100_FrameParts.SelectColer("Red"));
		}else {
			TB_MoveBrTotalQty.setForeground(B100_FrameParts.SelectColer("Black"));
			TB_MovePlQty.setForeground(B100_FrameParts.SelectColer("Black"));
			TB_MoveCsQty.setForeground(B100_FrameParts.SelectColer("Black"));
			TB_MoveCtQty.setForeground(B100_FrameParts.SelectColer("Black"));
			TB_MoveBrQty.setForeground(B100_FrameParts.SelectColer("Black"));
			
			TB_FromAfterQty.setForeground(B100_FrameParts.SelectColer("Black"));
			TB_FromAfterShipPlanQty.setForeground(B100_FrameParts.SelectColer("Black"));
			TB_FromAfterPossibleQty.setForeground(B100_FrameParts.SelectColer("Black"));
			
			TB_ToBrTotalQty.setForeground(B100_FrameParts.SelectColer("Black"));
			TB_ToPlQty.setForeground(B100_FrameParts.SelectColer("Black"));
			TB_ToCsQty.setForeground(B100_FrameParts.SelectColer("Black"));
			TB_ToCtQty.setForeground(B100_FrameParts.SelectColer("Black"));
			TB_ToBrQty.setForeground(B100_FrameParts.SelectColer("Black"));
			
			TB_ToAfterQty.setForeground(B100_FrameParts.SelectColer("Black"));
			TB_ToAfterShipPlanQty.setForeground(B100_FrameParts.SelectColer("Black"));
			TB_ToAfterPossibleQty.setForeground(B100_FrameParts.SelectColer("Black"));
		}
		
		String GetTB_FromType			= B100_DefaultVariable.LocType[1][TB_FromType.getSelectedIndex()];
		
		int GetTB_FromAfterQty			= B100_TextControl.TextToInt(TB_FromAfterQty.getText());
		int GetTB_FromAfterShipPlanQty	= B100_TextControl.TextToInt(TB_FromAfterShipPlanQty.getText());
		int GetTB_FromAfterPossibleQty	= B100_TextControl.TextToInt(TB_FromAfterPossibleQty.getText());
		
		ShipPlovisionTgtFg = true;
		for(int i=0;i<B100_DefaultVariable.ShipPlovisionUnTgtList.length;i++) {
			if(GetTB_FromType.equals(B100_DefaultVariable.ShipPlovisionUnTgtList[i])) {
				ShipPlovisionTgtFg = false;
			}
		}
		if(ShipPlovisionTgtFg) {
			GetTB_FromAfterQty			= GetTB_FromAfterQty - GetTB_MoveBrTotalQty;
			GetTB_FromAfterPossibleQty	= GetTB_FromAfterPossibleQty - GetTB_MoveBrTotalQty;
		}else {
			GetTB_FromAfterQty			= GetTB_FromAfterQty - GetTB_MoveBrTotalQty;
		}
		TB_FromAfterQty.setText(""+ni.format(GetTB_FromAfterQty));
		TB_FromAfterShipPlanQty.setText(""+ni.format(GetTB_FromAfterShipPlanQty));
		TB_FromAfterPossibleQty.setText(""+ni.format(GetTB_FromAfterPossibleQty));
		
		String GetTB_ToType				= B100_DefaultVariable.LocType[1][TB_ToType.getSelectedIndex()];
		
		int GetTB_ToBrTotalQty			= B100_TextControl.TextToInt(TB_ToBrTotalQty.getText());
		int GetTB_ToPlQty				= B100_TextControl.TextToInt(TB_ToPlQty.getText());
		int GetTB_ToCsQty				= B100_TextControl.TextToInt(TB_ToCsQty.getText());
		int GetTB_ToCtQty				= B100_TextControl.TextToInt(TB_ToCtQty.getText());
		int GetTB_ToBrQty				= B100_TextControl.TextToInt(TB_ToBrQty.getText());
		
		GetTB_ToBrTotalQty				= GetTB_ToBrTotalQty + GetTB_MoveBrTotalQty;
		GetTB_ToPlQty					= GetTB_ToPlQty + GetTB_MovePlQty;
		GetTB_ToCsQty					= GetTB_ToCsQty + GetTB_MoveCsQty;
		GetTB_ToCtQty					= GetTB_ToCtQty + GetTB_MoveCtQty;
		GetTB_ToBrQty					= GetTB_ToBrQty + GetTB_MoveBrQty;
		
		TB_ToBrTotalQty.setText(""+ni.format(GetTB_ToBrTotalQty));
		TB_ToPlQty.setText(""+ni.format(GetTB_ToPlQty));
		TB_ToCsQty.setText(""+ni.format(GetTB_ToCsQty));
		TB_ToCtQty.setText(""+ni.format(GetTB_ToCtQty));
		TB_ToBrQty.setText(""+ni.format(GetTB_ToBrQty));
		
		int GetTB_ToAfterQty			= B100_TextControl.TextToInt(TB_ToAfterQty.getText());
		int GetTB_ToAfterShipPlanQty	= B100_TextControl.TextToInt(TB_ToAfterShipPlanQty.getText());
		int GetTB_ToAfterPossibleQty	= B100_TextControl.TextToInt(TB_ToAfterPossibleQty.getText());
		
		ShipPlovisionTgtFg = true;
		for(int i=0;i<B100_DefaultVariable.ShipPlovisionUnTgtList.length;i++) {
			if(GetTB_ToType.equals(B100_DefaultVariable.ShipPlovisionUnTgtList[i])) {
				ShipPlovisionTgtFg = false;
			}
		}
		if(ShipPlovisionTgtFg) {
			GetTB_ToAfterQty			= GetTB_ToAfterQty + GetTB_MoveBrTotalQty;
			GetTB_ToAfterPossibleQty	= GetTB_ToAfterPossibleQty + GetTB_MoveBrTotalQty;
		}else {
			GetTB_ToAfterQty			= GetTB_ToAfterQty + GetTB_MoveBrTotalQty;
		}
		TB_ToAfterQty.setText(""+ni.format(GetTB_ToAfterQty));
		TB_ToAfterShipPlanQty.setText(""+ni.format(GetTB_ToAfterShipPlanQty));
		TB_ToAfterPossibleQty.setText(""+ni.format(GetTB_ToAfterPossibleQty));
		
		
		if(TB_EntryMode.isSelected()) {
			TB_MoveBrTotalQty.setEditable(false);
			TB_MoveBrTotalQty.setBackground(B100_FrameParts.SelectColer("NoEntry"));
			
			int GetTB_PlUnitQty	= B100_TextControl.TextToInt(TB_PlUnitQty.getText());
			int GetTB_CsUnitQty	= B100_TextControl.TextToInt(TB_CsUnitQty.getText());
			int GetTB_CtUnitQty	= B100_TextControl.TextToInt(TB_CtUnitQty.getText());
			if(0<GetTB_PlUnitQty) {
				TB_MovePlQty.setEditable(true);
				TB_MovePlQty.setBackground(B100_FrameParts.SelectColer("Entry"));
			}
			
			if(0<GetTB_CsUnitQty) {
				TB_MoveCsQty.setEditable(true);
				TB_MoveCsQty.setBackground(B100_FrameParts.SelectColer("Entry"));
			}
			if(0<GetTB_CtUnitQty) {
				TB_MoveCtQty.setEditable(true);
				TB_MoveCtQty.setBackground(B100_FrameParts.SelectColer("Entry"));
			}
			TB_MoveBrQty.setEditable(true);
			TB_MoveBrQty.setBackground(B100_FrameParts.SelectColer("Entry"));
		}else {
			TB_MoveBrTotalQty.setEditable(true);
			TB_MoveBrTotalQty.setBackground(B100_FrameParts.SelectColer("Entry"));
			
			TB_MovePlQty.setEditable(false);
			TB_MoveCsQty.setEditable(false);
			TB_MoveCtQty.setEditable(false);
			TB_MoveBrQty.setEditable(false);
			
			TB_MovePlQty.setBackground(B100_FrameParts.SelectColer("NoEntry"));
			TB_MoveCsQty.setBackground(B100_FrameParts.SelectColer("NoEntry"));
			TB_MoveCtQty.setBackground(B100_FrameParts.SelectColer("NoEntry"));
			TB_MoveBrQty.setBackground(B100_FrameParts.SelectColer("NoEntry"));
		}
		
		return ErrMsg;
	}
	
	private static boolean EntryCheck(
			String GetClCd,
			String GetWhCd,
			String GetItemCd,
			String GetLot,
			String GetExpDate,
			String GetActualDate,
			
			String GetFromLoc,
			String GetToLoc,
			
			int GetMoveBrTotalQty,
			
			String GetCom01,
			String GetCom02,
			String GetCom03
			) {
		
		if(null==GetClCd		){GetClCd		= "";}
		if(null==GetWhCd		){GetWhCd		= "";}
		if(null==GetItemCd		){GetItemCd		= "";}
		if(null==GetLot			){GetLot		= "";}
		if(null==GetExpDate		){GetExpDate	= "";}
		if(null==GetActualDate	){GetActualDate	= "";}
		if(null==GetFromLoc		){GetFromLoc	= "";}
		if(null==GetToLoc		){GetToLoc		= "";}
		if(null==GetCom01		){GetCom01		= "";}
		if(null==GetCom02		){GetCom02		= "";}
		if(null==GetCom03		){GetCom03		= "";}
		
		ArrayList<String> ErrMsg= new ArrayList<String>();
		
		if(0>GetMoveBrTotalQty) {
			ErrMsg.add("移動数マイナスはやめてください");
		}
		if(0==GetMoveBrTotalQty) {
			ErrMsg.add("移動数0は意味がありません");
		}
		if("".equals(GetClCd	)) {
			ErrMsg.add("荷主コードが設定されていません");
		}
		if("".equals(GetWhCd	)) {
			ErrMsg.add("倉庫コードが設定されていません");
		}
		if("".equals(GetFromLoc	)) {
			ErrMsg.add("移動元ロケ定義が不正です");
		}else {
			Object[][] LocSearch = LocSearch(
					GetClCd,
					GetWhCd,
					GetFromLoc,
					"",
					""
					);
			if(1!=LocSearch.length) {
				ErrMsg.add("移動元ロケ定義が不正です");
			}
		}
		if("".equals(GetToLoc	)) {
			ErrMsg.add("移動先ロケ定義が不正です");
		}else {
			Object[][] LocSearch = LocSearch(
					GetClCd,
					GetWhCd,
					GetToLoc,
					"",
					""
					);
			if(1!=LocSearch.length) {
				ErrMsg.add("移動先ロケ定義が不正です");
			}
		}
		if(GetFromLoc.equals(GetToLoc	)) {
			ErrMsg.add("同じロケに移動するのは無意味じゃよ");
		}
		
		Object[][] FromStockRt	= StockRt(
									GetWhCd,
									GetClCd,
									GetFromLoc,
									GetItemCd,
									GetLot,
									GetExpDate,
									GetActualDate);
		if(1!=FromStockRt.length) {
			ErrMsg.add("移動元在庫が特定できません");
		}else {
			if(GetMoveBrTotalQty>(int)FromStockRt[0][T100_StockRt.ColQty]-(int)FromStockRt[0][T100_StockRt.ColShipPlanQty]) {
				ErrMsg.add("移動数が移動可能数を超えています");
			}
		}
		Object[][] ToStockRt	= StockRt(
				GetWhCd,
				GetClCd,
				GetToLoc,
				GetItemCd,
				GetLot,
				GetExpDate,
				GetActualDate);
		if(1<ToStockRt.length) {
			ErrMsg.add("なぜか移動先在庫が複数検索されました");
		}
		
		boolean EntryFg = true;
		if(null!=ErrMsg && 0<ErrMsg.size()) {
			EntryFg = false;
			String WST = "以下のエラーが発生しています";
			for(int i=0;i<ErrMsg.size();i++) {
				WST	= WST + "\n" + ErrMsg.get(i);
			}
			JOptionPane.showMessageDialog(null, WST);
		}else {
			//在庫の増減する
			Object[][] SetData = new Object[2][Tools100_StockQtyControl.StockQtyControlDataLayout().length];
			
			SetData[0][Tools100_StockQtyControl.ColClCd] 			= GetClCd;
			SetData[0][Tools100_StockQtyControl.ColWhCd] 			= GetWhCd;
			SetData[0][Tools100_StockQtyControl.ColLoc]			= GetFromLoc;
			SetData[0][Tools100_StockQtyControl.ColItemCd]			= GetItemCd;
			SetData[0][Tools100_StockQtyControl.ColLot] 			= GetLot;
			SetData[0][Tools100_StockQtyControl.ColExpdate] 		= GetExpDate;
			SetData[0][Tools100_StockQtyControl.ColActualDate] 	= GetActualDate;
			SetData[0][Tools100_StockQtyControl.ColControlQty] 	= -GetMoveBrTotalQty;
			
			SetData[1][Tools100_StockQtyControl.ColClCd] 			= GetClCd;
			SetData[1][Tools100_StockQtyControl.ColWhCd] 			= GetWhCd;
			SetData[1][Tools100_StockQtyControl.ColLoc]			= GetToLoc;
			SetData[1][Tools100_StockQtyControl.ColItemCd]			= GetItemCd;
			SetData[1][Tools100_StockQtyControl.ColLot] 			= GetLot;
			SetData[1][Tools100_StockQtyControl.ColExpdate] 		= GetExpDate;
			SetData[1][Tools100_StockQtyControl.ColActualDate] 	= GetActualDate;
			SetData[1][Tools100_StockQtyControl.ColControlQty] 	= GetMoveBrTotalQty;
			
			Object[][] StockQtyControlErr = Tools100_StockQtyControl.StockQtyControl(SetData) ;
			String ErrString = "謎エラー：タイミングが超悪かったようです。在庫ぶっ壊れたかも";
			boolean ErrView = false;
			
			int BeforeFromQty			= 0;	//（移動前）移動元在庫数
			int BeforeFromPlanQty		= 0;	//（移動前）移動元引当済数
			int BeforeFromPossibleQty	= 0;	//（移動前）移動元出荷可能数
			int BeforeToQty				= 0;	//（移動前）移動先在庫数
			int BeforeToPlanQty			= 0;	//（移動前）移動先引当済数
			int BeforeToPossibleQty		= 0;	//（移動前）移動先出荷可能数
			int AfterFromQty			= 0;	//（移動後）移動元在庫数
			int AfterFromPlanQty		= 0;	//（移動後）移動元引当済数
			int AfterFromPossibleQty	= 0;	//（移動後）移動元出荷可能数
			int AfterToQty				= 0;	//（移動後）移動先在庫数
			int AfterToPlanQty			= 0;	//（移動後）移動先引当済数
			int AfterToPossibleQty		= 0;	//（移動後）移動先出荷可能数
			
			for(int i=0;i<StockQtyControlErr.length;i++) {
				if("EntryData".toUpperCase().equals(((String)StockQtyControlErr[i][Tools100_StockQtyControl.RtColErrType]).toUpperCase())){
					int EntryRow			= (int)StockQtyControlErr[i][Tools100_StockQtyControl.RtColErrRow];
					int BeforeQty			= (int)StockQtyControlErr[i][Tools100_StockQtyControl.RtColBeforeQty];
					int BeforeShipPlanQty	= (int)StockQtyControlErr[i][Tools100_StockQtyControl.RtColBeforeShipPlanQty];
					int BeforePossibleQty	= (int)StockQtyControlErr[i][Tools100_StockQtyControl.RtColBeforePossibleQty];
					int AdjustQty			= (int)StockQtyControlErr[i][Tools100_StockQtyControl.RtColAdjustQty];
					int AfterQty			= (int)StockQtyControlErr[i][Tools100_StockQtyControl.RtColAfterQty];
					int AfterShipPlanQtyQty	= (int)StockQtyControlErr[i][Tools100_StockQtyControl.RtColAfterShipPlanQty];
					int AfterPossibleQtyQty	= (int)StockQtyControlErr[i][Tools100_StockQtyControl.RtColAfterPossibleQty];
					
					switch(EntryRow) {
						case 0:
							BeforeFromQty			= BeforeQty;			//（移動前）移動元在庫数
							BeforeFromPlanQty		= BeforeShipPlanQty;	//（移動前）移動元引当済数
							BeforeFromPossibleQty	= BeforePossibleQty;	//（移動前）移動元出荷可能数
							AfterFromQty			= AfterQty;				//（移動後）移動元在庫数
							AfterFromPlanQty		= AfterShipPlanQtyQty;	//（移動後）移動元引当済数
							AfterFromPossibleQty	= AfterPossibleQtyQty;	//（移動後）移動元出荷可能数
							break;
						case 1:
							BeforeToQty				= BeforeQty;			//（移動前）移動先在庫数
							BeforeToPlanQty			= BeforeShipPlanQty;	//（移動前）移動先引当済数
							BeforeToPossibleQty		= BeforePossibleQty;	//（移動前）移動先出荷可能数
							AfterToQty				= AfterQty;				//（移動後）移動先在庫数
							AfterToPlanQty			= AfterShipPlanQtyQty;	//（移動後）移動先引当済数
							AfterToPossibleQty		= AfterPossibleQtyQty;	//（移動後）移動先出荷可能数
							break;
						default:
							break;
					}
				}else{
					String 	ErrType = (String)StockQtyControlErr[i][Tools100_StockQtyControl.RtColErrType];
					String 	ErrVol 	= (String)StockQtyControlErr[i][Tools100_StockQtyControl.RtColErrVol];
					int 	ErrRow	= (int)StockQtyControlErr[i][Tools100_StockQtyControl.RtColErrRow];
					
					ErrString = ErrString + "\n"+ErrType;
					ErrView = true;
				}
			}
			if(ErrView) {
				JOptionPane.showMessageDialog(null, ErrString);
				EntryFg = false;
			}else {
				//在庫移動データ格納用
				String now_dtm 	= B100_DateTimeControl.dtmString2(B100_DateTimeControl.dtm()[1])[1];
				int[] MoveNoRt	= Tools100_StockMoveNoGet.MoveNoRt(1);
				
				Object[][] SetString = {
							 {"ClCd"					,"1"	,"0"	,"KEY"	,GetClCd					}	//荷主コード
							,{"WhCd"					,"1"	,"0"	,"KEY"	,GetWhCd					}	//倉庫コード
							,{"MoveNo"					,"1"	,"0"	,"KEY"	,""+MoveNoRt[0]				}	//調整番号
							,{"FromLoc"					,"1"	,"0"	,""		,GetFromLoc					}	//移動元ロケ
							,{"ToLoc"					,"1"	,"0"	,""		,GetToLoc					}	//移動先ロケ
							,{"ItemCd"					,"1"	,"0"	,""		,GetItemCd					}	//商品CD
							,{"ItemName"				,"1"	,"0"	,""		,(String)FromStockRt[0][T100_StockRt.ColItemName]	}	//商品名
							,{"Lot"						,"1"	,"0"	,""		,GetLot						}	//ロット
							,{"ExpDate"					,"1"	,"0"	,""		,GetExpDate					}	//賞味期限
							,{"ActualDate"				,"1"	,"0"	,""		,GetActualDate				}	//入荷日
							,{"BeforeFromQty"			,"1"	,"0"	,""		,""+BeforeFromQty			}	//（移動前）移動元在庫数
							,{"BeforeFromPlanQty"		,"1"	,"0"	,""		,""+BeforeFromPlanQty		}	//（移動前）移動元引当済数
							,{"BeforeFromPossibleQty"	,"1"	,"0"	,""		,""+BeforeFromPossibleQty	}	//（移動前）移動元出荷可能数
							,{"BeforeToQty"				,"1"	,"0"	,""		,""+BeforeToQty				}	//（移動前）移動先在庫数
							,{"BeforeToPlanQty"			,"1"	,"0"	,""		,""+BeforeToPlanQty			}	//（移動前）移動先引当済数
							,{"BeforeToPossibleQty"		,"1"	,"0"	,""		,""+BeforeToPossibleQty		}	//（移動前）移動先出荷可能数
							,{"MoveQty"					,"1"	,"0"	,""		,""+GetMoveBrTotalQty		}	//移動数
							,{"AfterFromQty"			,"1"	,"0"	,""		,""+AfterFromQty			}	//（移動後）移動元在庫数
							,{"AfterFromPlanQty"		,"1"	,"0"	,""		,""+AfterFromPlanQty		}	//（移動後）移動元引当済数
							,{"AfterFromPossibleQty"	,"1"	,"0"	,""		,""+AfterFromPossibleQty	}	//（移動後）移動元出荷可能数
							,{"AfterToQty"				,"1"	,"0"	,""		,""+AfterToQty				}	//（移動後）移動先在庫数
							,{"AfterToPlanQty"			,"1"	,"0"	,""		,""+AfterToPlanQty			}	//（移動後）移動先引当済数
							,{"AfterToPossibleQty"		,"1"	,"0"	,""		,""+AfterToPossibleQty		}	//（移動後）移動先出荷可能数
							,{"MoveCom01"				,"1"	,"0"	,""		,GetCom01					}	//移動コメント01
							,{"MoveCom02"				,"1"	,"0"	,""		,GetCom02					}	//移動コメント02
							,{"MoveCom03"				,"1"	,"0"	,""		,GetCom03					}	//移動コメント03
							,{"EntryDate"				,"1"	,"0"	,""		,now_dtm					}	//登録日
							,{"UpdateDate"				,"1"	,"0"	,""		,now_dtm					}	//更新日
							,{"EntryUser"				,"1"	,"0"	,""		,"(" + A00000_Main.LoginUserId + ")" + A00000_Main.LoginUserName	}	//登録者
							,{"UpdateUser"				,"1"	,"0"	,""		,"(" + A00000_Main.LoginUserId + ")" + A00000_Main.LoginUserName	}	//更新者
							};
				String tgt_table = "WW0017StockMove";
				String TgtDB = "WANKO";
				int non_msg_fg = 1;
				
				A100_InsertUpdateSQL.InsertUpdateOneRecord(SetString,tgt_table,TgtDB,non_msg_fg);
				EntryFg = true;
			}
		}
		return EntryFg;
	}
	
	private static Object[][] StockRt(
			String TgtWhCd,
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
		
		if(!"".equals(TgtWhCd		)) {SearchWhCd.add(				TgtWhCd);}
		if(!"".equals(TgtClCd		)) {SearchClCd.add(				TgtClCd);}
		if(!"".equals(TgtLoc		)) {SearchLoc.add(				TgtLoc);}
		if(!"".equals(TgtItemCd		)) {SearchItemCd.add(			TgtItemCd);}
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
}