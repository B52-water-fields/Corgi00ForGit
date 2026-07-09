import java.awt.event.ActionEvent;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

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
		final JFrame main_fm 	= B100_FrameParts.FrameCreate(x,y,800,750,"Corgi00在庫移動","ZK");
		JLabel userinfo 		= B100_FrameParts.UserInfo();
		JButton exit_btn 		= B100_FrameParts.ExitBtn();
		JButton entry_btn 		= B100_FrameParts.EntryBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		main_fm.add(entry_btn);
		
		//移動元在庫が無い又は1件以上検索される場合は在庫検索に戻る
		if(1!=FromStockData.length){
			JOptionPane.showMessageDialog(null, "移動元在庫が特定できません");
			WT100_Stock_00_Search.StockSearch(0,0);
		}else {
			String GetFromClCd			= (String)FromStockData[0][T100_StockRt.ColClCd];				//荷主コード
			String GetFromCLName		= (String)FromStockData[0][T100_StockRt.ColCLName];			//荷主表記名
			String GetFromWhCd			= (String)FromStockData[0][T100_StockRt.ColWhCd];				//倉庫コード
			String GetFromClWHName		= (String)FromStockData[0][T100_StockRt.ColClWHName];			//担当倉庫名
			String GetFromClGpCD		= (String)FromStockData[0][T100_StockRt.ColClGpCD];			//荷主グループCD
			String GetFromClGpName		= (String)FromStockData[0][T100_StockRt.ColClGpName];			//グループ名1
			String GetFromLoc			= (String)FromStockData[0][T100_StockRt.ColLoc];				//ロケーション
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
				MovePossiblePlQty = (int)(MovePossibleBrQty/GetFromPlUnitQty);
				MovePossibleBrQty = (int)(MovePossibleBrQty%GetFromPlUnitQty);
			}
			if(0<GetFromCsUnitQty) {
				MovePossibleCsQty = (int)(MovePossibleBrQty/GetFromCsUnitQty);
				MovePossibleBrQty = (int)(MovePossibleBrQty%GetFromCsUnitQty);
			}
			if(0<GetFromCtUnitQty) {
				MovePossibleCtQty = (int)(MovePossibleBrQty/GetFromCtUnitQty);
				MovePossibleBrQty = (int)(MovePossibleBrQty%GetFromCtUnitQty);
			}
			
			/*移動商品情報*/
			JLabel LB_ClCd						= B100_FrameParts.JLabelSet(		  0, 50,100,20,"荷主:"				,11,1);
			JLabel LB_WhCd						= B100_FrameParts.JLabelSet(		  0, 75,100,20,"倉庫:"				,11,1);
			
			JLabel LB_ItemCd					= B100_FrameParts.JLabelSet(		  0,100,100,20,"商品CD:"			,10,1);
			JLabel LB_Lot						= B100_FrameParts.JLabelSet(		  0,125,100,20,"ロット:"			,10,1);
			JLabel LB_Expdate					= B100_FrameParts.JLabelSet(		  0,150,100,20,"消費期限:"			,10,1);
			JLabel LB_ActualDate				= B100_FrameParts.JLabelSet(		  0,175,100,20,"入荷実績日:"		,10,1);
			
			JLabel LB_PlUnitQty					= B100_FrameParts.JLabelSet(		300,125,100,20,"パレット入数:"		,10,1);
			JLabel LB_CsUnitQty					= B100_FrameParts.JLabelSet(		300,150,100,20,"ケース入数:"		,10,1);
			JLabel LB_CtUnitQty					= B100_FrameParts.JLabelSet(		300,175,100,20,"カートン入数:"		,10,1);
			
			
			JLabel LB_FromMsg					= B100_FrameParts.JLabelSet(		100,225,100,20,"移動元"				,10,2);
			JLabel LB_ToMsg						= B100_FrameParts.JLabelSet(		300,225,100,20,"移動先"				,10,2);
			JLabel LB_MovePossibleMsg			= B100_FrameParts.JLabelSet(		100,400,100,20,"移動可能数"			,10,2);
			JLabel LB_MoveMsg					= B100_FrameParts.JLabelSet(		500,400,100,20,"移動数"				,10,2);
			JLabel LB_MoveAfterMsg				= B100_FrameParts.JLabelSet(		300,400,100,20,"移動後総数"			,10,2);
			
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
			
			
			/*移動商品情報*/
			final JComboBox TB_ClCd						= B100_FrameParts.JComboBoxSet(				100, 50,250,20,B100_DefaultVariable.ClList[0],11);
			final JComboBox TB_WhCd						= B100_FrameParts.JComboBoxSet(				100, 75,250,20,B100_DefaultVariable.WhList[0],11);
			
			final JTextField TB_ItemCd					= B100_FrameParts.JTextFieldSet(				100,100,100,20,GetFromItemCd			,12,0);
			final JTextField TB_ItemName				= B100_FrameParts.JTextFieldSet(				200,100,300,20,GetFromItemName01		,12,0);
			final JTextField TB_Lot						= B100_FrameParts.JTextFieldSet(				100,125,100,20,GetFromLot				,12,0);
			final JFormattedTextField TB_Expdate		= B100_FrameParts.JFormattedTextFieldSet(	100,150,100,20,GetFromExpdate			,12,0,"YYYY/MM/DD");
			final JFormattedTextField TB_ActualDate		= B100_FrameParts.JFormattedTextFieldSet(	100,175,100,20,GetFromActualDate		,12,0,"YYYY/MM/DD");
			
			final JFormattedTextField TB_PlUnitQty		= B100_FrameParts.JFormattedTextFieldSet(	400,125,100,20,""+ni.format(GetFromPlUnitQty)		,12,1,"#,###");
			final JFormattedTextField TB_CsUnitQty		= B100_FrameParts.JFormattedTextFieldSet(	400,150,100,20,""+ni.format(GetFromCsUnitQty)		,12,1,"#,###");
			final JFormattedTextField TB_CtUnitQty		= B100_FrameParts.JFormattedTextFieldSet(	400,175,100,20,""+ni.format(GetFromCtUnitQty)		,12,1,"#,###");
			
			
			/*移動元情報*/
			final JTextField TB_FromLoc					= B100_FrameParts.JTextFieldSet(				100,250,100,20,GetFromLoc				,12,0);
			final JTextField TB_FromLocName				= B100_FrameParts.JTextFieldSet(				100,275,100,20,GetFromLocName			,12,0);
			final JComboBox TB_FromType					= B100_FrameParts.JComboBoxSet(				100,300,100,20,B100_DefaultVariable.LocType[0],11);
			final JFormattedTextField TB_FromQty		= B100_FrameParts.JFormattedTextFieldSet(	100,325,100,20,""+ni.format(GetFromQty)				,12,1,"#,###");
			final JFormattedTextField TB_FromShipPlanQty= B100_FrameParts.JFormattedTextFieldSet(	100,350,100,20,""+ni.format(GetFromShipPlanQty)		,12,1,"#,###");
			final JFormattedTextField TB_FromPossibleQty= B100_FrameParts.JFormattedTextFieldSet(	100,375,100,20,""+ni.format(GetFromPossibleQty)		,12,1,"#,###");
			JLabel TB_FromQtyUnitName					= B100_FrameParts.JLabelSet(		  			200,325, 60,20,GetFromUnitName			,10,0);
			JLabel TB_FromShipPlanQtyUnitName			= B100_FrameParts.JLabelSet(					200,350, 60,20,GetFromUnitName			,10,0);
			JLabel TB_FromPossibleQtyUnitName			= B100_FrameParts.JLabelSet(					200,375, 60,20,GetFromUnitName			,10,0);
			
			final JFormattedTextField TB_FromBrTotalQty	= B100_FrameParts.JFormattedTextFieldSet(	100,425,100,20,""+ni.format(MovePossibleBrTotalQty)	,12,1,"#,###");
			final JFormattedTextField TB_FromPlQty		= B100_FrameParts.JFormattedTextFieldSet(	100,450,100,20,""+ni.format(MovePossiblePlQty)		,12,1,"#,###");
			final JFormattedTextField TB_FromCsQty		= B100_FrameParts.JFormattedTextFieldSet(	100,475,100,20,""+ni.format(MovePossibleCsQty)		,12,1,"#,###");
			final JFormattedTextField TB_FromCtQty		= B100_FrameParts.JFormattedTextFieldSet(	100,500,100,20,""+ni.format(MovePossibleCtQty)		,12,1,"#,###");
			final JFormattedTextField TB_FromBrQty		= B100_FrameParts.JFormattedTextFieldSet(	100,525,100,20,""+ni.format(MovePossibleBrQty)		,12,1,"#,###");
			
			JLabel TB_FromBrTotalQtyUnitName			= B100_FrameParts.JLabelSet(					200,425, 60,20,GetFromUnitName			,10,0);
			JLabel TB_FromPlQtyUnitName					= B100_FrameParts.JLabelSet(					200,450, 60,20,GetFromPlUnitName		,10,0);
			JLabel TB_FromCsQtyUnitName					= B100_FrameParts.JLabelSet(					200,475, 60,20,GetFromCsUnitName		,10,0);
			JLabel TB_FromCtQtyUnitName					= B100_FrameParts.JLabelSet(					200,500, 60,20,GetFromCtUnitName		,10,0);
			JLabel TB_FromBrQtyUnitName					= B100_FrameParts.JLabelSet(					200,525, 60,20,GetFromUnitName			,10,0);
			
			/*移動先情報*/
			final JTextField TB_ToLoc					= B100_FrameParts.JTextFieldSet(				300,250,100,20,""						,12,0);
			final JTextField TBToLocName				= B100_FrameParts.JTextFieldSet(				300,275,100,20,""						,12,0);
			final JComboBox TB_ToType					= B100_FrameParts.JComboBoxSet(				300,300,100,20,B100_DefaultVariable.LocType[0],11);
			final JFormattedTextField TB_ToQty			= B100_FrameParts.JFormattedTextFieldSet(	300,325,100,20,""+ni.format(0)			,12,1,"#,###");
			final JFormattedTextField TB_ToShipPlanQty	= B100_FrameParts.JFormattedTextFieldSet(	300,350,100,20,""+ni.format(0)			,12,1,"#,###");
			final JFormattedTextField TB_ToPossibleQty	= B100_FrameParts.JFormattedTextFieldSet(	300,375,100,20,""+ni.format(0)			,12,1,"#,###");
			JLabel TB_ToQtyUnitName						= B100_FrameParts.JLabelSet(		  			400,325, 60,20,GetFromUnitName			,10,0);
			JLabel TB_ToShipPlanQtyUnitName				= B100_FrameParts.JLabelSet(					400,350, 60,20,GetFromUnitName			,10,0);
			JLabel TB_ToPossibleQtyUnitName				= B100_FrameParts.JLabelSet(					400,375, 60,20,GetFromUnitName			,10,0);
			
			final JFormattedTextField TB_ToBrTotalQty	= B100_FrameParts.JFormattedTextFieldSet(	300,425,100,20,""+ni.format(0)			,12,1,"#,###");
			final JFormattedTextField TB_ToPlQty		= B100_FrameParts.JFormattedTextFieldSet(	300,450,100,20,""+ni.format(0)			,12,1,"#,###");
			final JFormattedTextField TB_ToCsQty		= B100_FrameParts.JFormattedTextFieldSet(	300,475,100,20,""+ni.format(0)			,12,1,"#,###");
			final JFormattedTextField TB_ToCtQty		= B100_FrameParts.JFormattedTextFieldSet(	300,500,100,20,""+ni.format(0)			,12,1,"#,###");
			final JFormattedTextField TB_ToBrQty		= B100_FrameParts.JFormattedTextFieldSet(	300,525,100,20,""+ni.format(0)			,12,1,"#,###");
			
			JLabel TB_ToBrTotalQtyUnitName				= B100_FrameParts.JLabelSet(					400,425, 60,20,GetFromUnitName			,10,0);
			JLabel TB_ToPlQtyUnitName					= B100_FrameParts.JLabelSet(					400,450, 60,20,GetFromPlUnitName		,10,0);
			JLabel TB_ToCsQtyUnitName					= B100_FrameParts.JLabelSet(					400,475, 60,20,GetFromCsUnitName		,10,0);
			JLabel TB_ToCtQtyUnitName					= B100_FrameParts.JLabelSet(					400,500, 60,20,GetFromCtUnitName		,10,0);
			JLabel TB_ToBrQtyUnitName					= B100_FrameParts.JLabelSet(					400,525, 60,20,GetFromUnitName			,10,0);
			
			
			TB_ClCd.setSelectedIndex(	B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.ClList[1]		,GetFromClCd ,true) );
			TB_WhCd.setSelectedIndex(	B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.ClList[1]		,GetFromWhCd ,true) );
			TB_FromType.setSelectedIndex(B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.LocType[1]	,GetFromType+"" ,true) );
			
			
			
			
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
			
			/*入力ボックス背景色変える*/
			TB_ToLoc.setBackground(B100_FrameParts.SelectColer("Entry"));
			
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
			
			main_fm.add(LB_BrTotalQty);
			main_fm.add(LB_PlQty);
			main_fm.add(LB_CsQty);
			main_fm.add(LB_CtQty);
			main_fm.add(LB_BrQty);
			
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
			
		}
		
		
		RenewFg = true;
		
		if(1==FromStockData.length){
			main_fm.setVisible(true);
		}

		//EXITボタン押下時の挙動
		exit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				WT100_Stock_00_Search.StockSearch(0,0);
			}
		});
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
}