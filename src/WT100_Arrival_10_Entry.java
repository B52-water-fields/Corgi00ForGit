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
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class WT100_Arrival_10_Entry{
	//入荷実績登録する
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	
	static final int PlanMsViewColMsNo 				=  1;		//明細番号
	static final int PlanMsViewColItemCd 			=  2;		//商品コード
	static final int PlanMsViewColClItemCd 			=  3;		//荷主商品コード
	static final int PlanMsViewColItemName 			=  4;		//商品名
	static final int PlanMsViewColLot 				=  5;		//ロット
	static final int PlanMsViewColExpDate 			=  6;		//消費期限
	static final int PlanMsViewColRemainingPlanQty	=  7;		//予定残
	static final int PlanMsViewCoEntryQty			=  8;		//今回入荷数
	static final int PlanMsViewColPlanQty 			=  9;		//予定数量
	static final int PlanMsViewColActualQty 			= 10;		//実績数
	static final int PlanMsViewColActualDate 		= 11;		//入荷日
	static final int PlanMsViewColCom01 				= 12;		//コメント1
	static final int PlanMsViewColCom02 				= 13;		//コメント2
	static final int PlanMsViewColJanCd 				= 14;		//JANCD
	static final int PlanMsViewColItemMdNo 			= 15;		//商品型番
	static final int PlanMsViewColEntryDate 			= 16;		//登録日
	static final int PlanMsViewColUpdateDate 		= 17;		//更新日
	static final int PlanMsViewColEntryUser 			= 18;		//登録者
	static final int PlanMsViewColUpdateUser 		= 19;		//更新者
	
	static final int EntryColMsNo					=  1;		//明細番号
	static final int EntryColMsSeq				=  2;		//明細Seq番号
	static final int EntryColItemCd				=  3;		//商品コード
	static final int EntryColClItemCd				=  4;		//荷主商品コード
	static final int EntryColItemName				=  5;		//商品名
	static final int EntryColLot					=  6;		//ロット
	static final int EntryColExpDate				=  7;		//消費期限
	static final int EntryColRemainingPlanQty	=  8;		//予定残
	static final int EntryColEntryQty				=  9;		//今回入荷数
	static final int EntryColPlanQty				= 10;		//予定数量
	static final int EntryColActualQty			= 11;		//実績数
	static final int EntryColActualDate			= 12;		//入荷日
	static final int EntryColCom01				= 13;		//コメント1
	static final int EntryColCom02				= 14;		//コメント2
	static final int EntryColJanCd				= 15;		//JanCd(バラ)
	static final int EntryColItemMdNo				= 16;		//商品型番
	
	public static void ArrivalEntry(int x,int y,String TgtWhCd,String TgtClCd,String TgtArrNo) {
		RenewFg = false;
		A00000_Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		
		if(null==TgtWhCd	) {TgtWhCd	= "";}
		if(null==TgtClCd	) {TgtClCd	= "";}
		if(null==TgtArrNo	) {TgtArrNo	= "";}
		
		if("".equals(TgtWhCd)) {TgtWhCd	= A00000_Main.ClWh;}
		if("".equals(TgtClCd)) {TgtClCd	= A00000_Main.ClCd;}
		
		final JFrame main_fm = B100_FrameParts.FrameCreate(x,y,1300,800,"Corgi00入荷実績登録　WT100_Arrival_10_Entry","NK");
		JLabel userinfo = B100_FrameParts.UserInfo();
		JButton exit_btn = B100_FrameParts.ExitBtn();
		JButton entry_btn = B100_FrameParts.EntryBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		main_fm.add(entry_btn);
		
		
		//検索条件パネル
		JPanel PN_Hd 		= B100_FrameParts.JPanelSet(10,40,1260,125,"White");
		JLabel PN_HdLabel 	= B100_FrameParts.JLabelSet(0,0,100,20,"予定ヘッダ",11,0);
				
		JLabel LB_ClWh			= B100_FrameParts.JLabelSet(  0, 25,100,20,"担当倉庫:",		11,1);
		JLabel LB_ClCd			= B100_FrameParts.JLabelSet(  0, 50,100,20,"荷主CD:",			11,1);
		JLabel LB_SpCd			= B100_FrameParts.JLabelSet(  0, 75,100,20,"仕入先CD:",		11,1);
		JLabel LB_FixFg			= B100_FrameParts.JLabelSet(  0,100,100,20,"状況:",			11,1);
		
		JLabel LB_ArrNo			= B100_FrameParts.JLabelSet(340, 25,100,20,"入荷予定NO:",		11,1);
		JLabel LB_ClArrNo		= B100_FrameParts.JLabelSet(340, 50,100,20,"荷主予定番号:",	11,1);
		JLabel LB_PlanDate		= B100_FrameParts.JLabelSet(340, 75,100,20,"入荷予定日:",		11,1);
		JLabel LB_HdActualDate	= B100_FrameParts.JLabelSet(340,100,100,20,"入荷実績日:",		11,1);
		
		JLabel LB_ArCom01		= B100_FrameParts.JLabelSet(580, 25,100,20,"コメント1:",		11,1);
		JLabel LB_ArCom02		= B100_FrameParts.JLabelSet(580, 50,100,20,"コメント2:",		11,1);
		JLabel LB_ArCom03		= B100_FrameParts.JLabelSet(580, 75,100,20,"コメント3:",		11,1);
		
		JLabel LB_HdEntryDate	= B100_FrameParts.JLabelSet(880, 25,100,20,"登録日:",			11,1);
		JLabel LB_HdEntryUser	= B100_FrameParts.JLabelSet(880, 50,100,20,"登録者:",			11,1);
		JLabel LB_HdUpdateDate	= B100_FrameParts.JLabelSet(880, 75,100,20,"更新日:",			11,1);
		JLabel LB_HdUpdateUser	= B100_FrameParts.JLabelSet(880,100,100,20,"更新者:",			11,1);
		
		final JComboBox TB_ClWh						= B100_FrameParts.JComboBoxSet(				100, 25,240,20,B100_DefaultVariable.WhList[0],11);						//ヘッダ担当倉庫
		final JComboBox TB_ClCd						= B100_FrameParts.JComboBoxSet(				100, 50,240,20,B100_DefaultVariable.ClList[0],11);						//ヘッダ荷主CD
		final JComboBox TB_SpCd						= B100_FrameParts.JComboBoxSet(				100, 75,240,20,B100_DefaultVariable.SupplierList[0],11);				//ヘッダ仕入先
		final JComboBox TB_FixFg					= B100_FrameParts.JComboBoxSet(				100,100,150,20,B100_DefaultVariable.ArryvalFixFgList[0],11);			//状況
		
		final JTextField TB_ArrNo					= B100_FrameParts.JTextFieldSet(				440, 25,100,20,"",11,0);												//入荷予定NO
		final JTextField TB_ClArrNo					= B100_FrameParts.JTextFieldSet(				440, 50,100,20,"",11,0);												//荷主予定番号
		final JFormattedTextField TB_PlanDate		= B100_FrameParts.JFormattedTextFieldSet(	440, 75, 70,20,"",11,0,"YYYY/MM/DD");									//入荷予定日
		final JFormattedTextField TB_HdActualDate	= B100_FrameParts.JFormattedTextFieldSet(	440,100,140,20,"",11,0,"YYYY/MM/DD HH:MM:SS");							//入荷実績日
		
		final JTextField TB_ArCom01					= B100_FrameParts.JTextFieldSet(				680, 25,200,20,"",11,0);												//コメント1
		final JTextField TB_ArCom02					= B100_FrameParts.JTextFieldSet(				680, 50,200,20,"",11,0);												//コメント2
		final JTextField TB_ArCom03					= B100_FrameParts.JTextFieldSet(				680, 75,200,20,"",11,0);												//コメント3
		
		final JTextField TB_HdEntryDate				= B100_FrameParts.JTextFieldSet(				980, 25,200,20,"",11,0);												//登録日
		final JTextField TB_HdUpdateDate			= B100_FrameParts.JTextFieldSet(				980, 50,200,20,"",11,0);												//更新日
		final JTextField TB_HdEntryUser				= B100_FrameParts.JTextFieldSet(				980, 75,200,20,"",11,0);												//登録者
		final JTextField TB_HdUpdateUser			= B100_FrameParts.JTextFieldSet(				980,100,200,20,"",11,0);												//更新者
				
		//予定明細
		String[] PlanMscolumnNames = {
				 "Fg"
				,"明細番号"
				,"商品コード"
				,"荷主商品コード"
				,"商品名"
				,"ロット"
				,"消費期限"
				,"予定残"
				,"今回入荷数"
				,"予定数量"
				,"実績数"
				,"入荷日"
				,"コメント1"
				,"コメント2"
				,"JANCD（バラ）"
				,"商品型番"
				,"登録日"
				,"更新日"
				,"登録者"
				,"更新者"
				};

		final DefaultTableModel PlanMstableModel = new B100_TableControl.MyTableModel00(PlanMscolumnNames,0);
		
		final JTable PlanMstb = new JTable(PlanMstableModel);
		PlanMstb.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		PlanMstb.setRowHeight(20*A00000_Main.Mul/A00000_Main.Div);
		PlanMstb.setFont(new Font(A00000_Main.DefaultFont, Font.PLAIN, 12*A00000_Main.Mul/A00000_Main.Div));
		
		DefaultTableColumnModel PlanMscolumnModel
		= (DefaultTableColumnModel)PlanMstb.getColumnModel();
		
		//列幅初期設定 表示位置設定
		TableColumn PlanMscolumn = null;
		
		PlanMscolumn = PlanMscolumnModel.getColumn(0);									PlanMscolumn.setPreferredWidth( 30*A00000_Main.Mul/A00000_Main.Div);	//FG
		PlanMscolumn = PlanMscolumnModel.getColumn(PlanMsViewColMsNo);				PlanMscolumn.setPreferredWidth( 30*A00000_Main.Mul/A00000_Main.Div);	PlanMscolumn.setCellRenderer(B100_FrameParts.rightCellRenderer());		//明細番号
		PlanMscolumn = PlanMscolumnModel.getColumn(PlanMsViewColItemCd);				PlanMscolumn.setPreferredWidth( 70*A00000_Main.Mul/A00000_Main.Div);	PlanMscolumn.setCellRenderer(B100_FrameParts.leftCellRenderer());		//商品コード
		PlanMscolumn = PlanMscolumnModel.getColumn(PlanMsViewColClItemCd);			PlanMscolumn.setPreferredWidth( 70*A00000_Main.Mul/A00000_Main.Div);	PlanMscolumn.setCellRenderer(B100_FrameParts.leftCellRenderer());		//荷主商品コード
		PlanMscolumn = PlanMscolumnModel.getColumn(PlanMsViewColItemName);			PlanMscolumn.setPreferredWidth( 70*A00000_Main.Mul/A00000_Main.Div);	PlanMscolumn.setCellRenderer(B100_FrameParts.leftCellRenderer());		//商品名
		PlanMscolumn = PlanMscolumnModel.getColumn(PlanMsViewColLot);					PlanMscolumn.setPreferredWidth( 70*A00000_Main.Mul/A00000_Main.Div);	PlanMscolumn.setCellRenderer(B100_FrameParts.leftCellRenderer());		//ロット
		PlanMscolumn = PlanMscolumnModel.getColumn(PlanMsViewColExpDate);			PlanMscolumn.setPreferredWidth( 70*A00000_Main.Mul/A00000_Main.Div);	PlanMscolumn.setCellRenderer(B100_FrameParts.leftCellRenderer());		//消費期限
		PlanMscolumn = PlanMscolumnModel.getColumn(PlanMsViewColRemainingPlanQty);	PlanMscolumn.setPreferredWidth( 70*A00000_Main.Mul/A00000_Main.Div);	PlanMscolumn.setCellRenderer(B100_FrameParts.rightCellRenderer());	//予定残
		PlanMscolumn = PlanMscolumnModel.getColumn(PlanMsViewCoEntryQty);			PlanMscolumn.setPreferredWidth( 70*A00000_Main.Mul/A00000_Main.Div);	PlanMscolumn.setCellRenderer(B100_FrameParts.rightCellRenderer());	//今回入荷数
		PlanMscolumn = PlanMscolumnModel.getColumn(PlanMsViewColPlanQty);			PlanMscolumn.setPreferredWidth( 70*A00000_Main.Mul/A00000_Main.Div);	PlanMscolumn.setCellRenderer(B100_FrameParts.rightCellRenderer());	//予定数量
		PlanMscolumn = PlanMscolumnModel.getColumn(PlanMsViewColActualQty);			PlanMscolumn.setPreferredWidth( 70*A00000_Main.Mul/A00000_Main.Div);	PlanMscolumn.setCellRenderer(B100_FrameParts.rightCellRenderer());	//実績数
		PlanMscolumn = PlanMscolumnModel.getColumn(PlanMsViewColActualDate);			PlanMscolumn.setPreferredWidth( 70*A00000_Main.Mul/A00000_Main.Div);	PlanMscolumn.setCellRenderer(B100_FrameParts.leftCellRenderer());		//入荷日
		PlanMscolumn = PlanMscolumnModel.getColumn(PlanMsViewColCom01);				PlanMscolumn.setPreferredWidth( 70*A00000_Main.Mul/A00000_Main.Div);	PlanMscolumn.setCellRenderer(B100_FrameParts.leftCellRenderer());		//コメント1
		PlanMscolumn = PlanMscolumnModel.getColumn(PlanMsViewColCom02);				PlanMscolumn.setPreferredWidth( 70*A00000_Main.Mul/A00000_Main.Div);	PlanMscolumn.setCellRenderer(B100_FrameParts.leftCellRenderer());		//コメント2
		PlanMscolumn = PlanMscolumnModel.getColumn(PlanMsViewColJanCd);				PlanMscolumn.setPreferredWidth( 70*A00000_Main.Mul/A00000_Main.Div);	PlanMscolumn.setCellRenderer(B100_FrameParts.leftCellRenderer());		//JANCD（バラ）
		PlanMscolumn = PlanMscolumnModel.getColumn(PlanMsViewColItemMdNo);			PlanMscolumn.setPreferredWidth( 70*A00000_Main.Mul/A00000_Main.Div);	PlanMscolumn.setCellRenderer(B100_FrameParts.leftCellRenderer());		//商品型番
		PlanMscolumn = PlanMscolumnModel.getColumn(PlanMsViewColEntryDate);			PlanMscolumn.setPreferredWidth( 70*A00000_Main.Mul/A00000_Main.Div);	PlanMscolumn.setCellRenderer(B100_FrameParts.leftCellRenderer());		//登録日
		PlanMscolumn = PlanMscolumnModel.getColumn(PlanMsViewColUpdateDate);			PlanMscolumn.setPreferredWidth( 70*A00000_Main.Mul/A00000_Main.Div);	PlanMscolumn.setCellRenderer(B100_FrameParts.leftCellRenderer());		//更新日
		PlanMscolumn = PlanMscolumnModel.getColumn(PlanMsViewColEntryUser);			PlanMscolumn.setPreferredWidth( 70*A00000_Main.Mul/A00000_Main.Div);	PlanMscolumn.setCellRenderer(B100_FrameParts.leftCellRenderer());		//登録者
		PlanMscolumn = PlanMscolumnModel.getColumn(PlanMsViewColUpdateUser);			PlanMscolumn.setPreferredWidth( 70*A00000_Main.Mul/A00000_Main.Div);	PlanMscolumn.setCellRenderer(B100_FrameParts.leftCellRenderer());		//更新者
		
		//スクロール用設定
		JLabel PlanMsMsg 				= B100_FrameParts.JLabelSet(		650,170,200,20,"予定情報",11,0);
		main_fm.add(PlanMsMsg);
		JScrollPane 	PlanMsscpn01 	= B100_FrameParts.JScrollPaneSet(	650,190,620,200,	PlanMstb);
		main_fm.add(PlanMsscpn01);
		
		//実績明細
		String[] EntryMscolumnNames = {
				 "Fg"
				,"明細番号"
				,"明細Seq番号"
				,"商品コード"
				,"荷主商品コード"
				,"商品名"
				,"ロット"
				,"消費期限"
				,"予定残"
				,"今回入荷数"
				,"予定数量"
				,"実績数"
				,"入荷日"
				,"コメント1"
				,"コメント2"
				,"JanCd(バラ)"
				,"商品型番"};
		
		final DefaultTableModel EntryMstableModel = new B100_TableControl.MyTableModel00(EntryMscolumnNames,0);
		
		final JTable EntryMstb = new JTable(EntryMstableModel);
		EntryMstb.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		EntryMstb.setRowHeight(20*A00000_Main.Mul/A00000_Main.Div);
		EntryMstb.setFont(new Font(A00000_Main.DefaultFont, Font.PLAIN, 12*A00000_Main.Mul/A00000_Main.Div));
		
		DefaultTableColumnModel EntryMscolumnModel
		= (DefaultTableColumnModel)EntryMstb.getColumnModel();
		
		//列幅初期設定 表示位置設定
		TableColumn EntryMscolumn = null;
		EntryMscolumn = EntryMscolumnModel.getColumn(0);							EntryMscolumn.setPreferredWidth( 30*A00000_Main.Mul/A00000_Main.Div);	//FG
		EntryMscolumn = EntryMscolumnModel.getColumn(EntryColMsNo);				EntryMscolumn.setPreferredWidth( 30*A00000_Main.Mul/A00000_Main.Div);	EntryMscolumn.setCellRenderer(B100_FrameParts.rightCellRenderer());	//明細番号
		EntryMscolumn = EntryMscolumnModel.getColumn(EntryColMsSeq);				EntryMscolumn.setPreferredWidth( 30*A00000_Main.Mul/A00000_Main.Div);	EntryMscolumn.setCellRenderer(B100_FrameParts.rightCellRenderer());	//明細Seq番号
		EntryMscolumn = EntryMscolumnModel.getColumn(EntryColItemCd);				EntryMscolumn.setPreferredWidth( 70*A00000_Main.Mul/A00000_Main.Div);	EntryMscolumn.setCellRenderer(B100_FrameParts.leftCellRenderer());	//商品コード
		EntryMscolumn = EntryMscolumnModel.getColumn(EntryColClItemCd);			EntryMscolumn.setPreferredWidth( 70*A00000_Main.Mul/A00000_Main.Div);	EntryMscolumn.setCellRenderer(B100_FrameParts.leftCellRenderer());	//荷主商品コード
		EntryMscolumn = EntryMscolumnModel.getColumn(EntryColItemName);			EntryMscolumn.setPreferredWidth( 70*A00000_Main.Mul/A00000_Main.Div);	EntryMscolumn.setCellRenderer(B100_FrameParts.leftCellRenderer());	//商品名
		EntryMscolumn = EntryMscolumnModel.getColumn(EntryColLot);					EntryMscolumn.setPreferredWidth( 70*A00000_Main.Mul/A00000_Main.Div);	EntryMscolumn.setCellRenderer(B100_FrameParts.leftCellRenderer());	//ロット
		EntryMscolumn = EntryMscolumnModel.getColumn(EntryColExpDate);			EntryMscolumn.setPreferredWidth( 70*A00000_Main.Mul/A00000_Main.Div);	EntryMscolumn.setCellRenderer(B100_FrameParts.leftCellRenderer());	//消費期限
		EntryMscolumn = EntryMscolumnModel.getColumn(EntryColRemainingPlanQty);	EntryMscolumn.setPreferredWidth( 70*A00000_Main.Mul/A00000_Main.Div);	EntryMscolumn.setCellRenderer(B100_FrameParts.rightCellRenderer());	//予定残
		EntryMscolumn = EntryMscolumnModel.getColumn(EntryColEntryQty);			EntryMscolumn.setPreferredWidth( 70*A00000_Main.Mul/A00000_Main.Div);	EntryMscolumn.setCellRenderer(B100_FrameParts.rightCellRenderer());	//今回入荷数
		EntryMscolumn = EntryMscolumnModel.getColumn(EntryColPlanQty);			EntryMscolumn.setPreferredWidth( 70*A00000_Main.Mul/A00000_Main.Div);	EntryMscolumn.setCellRenderer(B100_FrameParts.rightCellRenderer());	//予定数量
		EntryMscolumn = EntryMscolumnModel.getColumn(EntryColActualQty);			EntryMscolumn.setPreferredWidth( 70*A00000_Main.Mul/A00000_Main.Div);	EntryMscolumn.setCellRenderer(B100_FrameParts.rightCellRenderer());	//実績数
		EntryMscolumn = EntryMscolumnModel.getColumn(EntryColActualDate);		EntryMscolumn.setPreferredWidth( 70*A00000_Main.Mul/A00000_Main.Div);	EntryMscolumn.setCellRenderer(B100_FrameParts.leftCellRenderer());	//入荷日
		EntryMscolumn = EntryMscolumnModel.getColumn(EntryColCom01);				EntryMscolumn.setPreferredWidth( 70*A00000_Main.Mul/A00000_Main.Div);	EntryMscolumn.setCellRenderer(B100_FrameParts.leftCellRenderer());	//コメント1
		EntryMscolumn = EntryMscolumnModel.getColumn(EntryColCom02);				EntryMscolumn.setPreferredWidth( 70*A00000_Main.Mul/A00000_Main.Div);	EntryMscolumn.setCellRenderer(B100_FrameParts.leftCellRenderer());	//コメント2
		EntryMscolumn = EntryMscolumnModel.getColumn(EntryColJanCd);				EntryMscolumn.setPreferredWidth( 70*A00000_Main.Mul/A00000_Main.Div);	EntryMscolumn.setCellRenderer(B100_FrameParts.leftCellRenderer());	//JanCd(バラ)
		EntryMscolumn = EntryMscolumnModel.getColumn(EntryColItemMdNo);			EntryMscolumn.setPreferredWidth( 70*A00000_Main.Mul/A00000_Main.Div);	EntryMscolumn.setCellRenderer(B100_FrameParts.leftCellRenderer());	//商品型番
		

		//スクロール用設定
		JLabel EntryMsMsg 				= B100_FrameParts.JLabelSet(		650,390,200,20,"登録情報",11,0);
		main_fm.add(EntryMsMsg);
		JScrollPane 	EntryMsscpn01 	= B100_FrameParts.JScrollPaneSet(	650,410,620,200,	EntryMstb);
		main_fm.add(EntryMsscpn01);
		
		
		
		//登録用
		JLabel PN_EntryLabel 	= B100_FrameParts.JLabelSet( 10,170,200,20,"実績登録",11,0);
		main_fm.add(PN_EntryLabel);
		JPanel PN_Entry 		= B100_FrameParts.JPanelSet( 10,190,620,420,"White");
		
		JLabel LB_EntryItemCd	= B100_FrameParts.JLabelSet(  0, 25,100,20,"商品コード:",		11,1);
		/*
		,{"ClItemCd"	,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"荷主商品コード"}
		,{"JanCd"		,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"JanCd(バラ)"}
		,{"ItemMdNo"	,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"商品型番"}
		,{"ItemName"	,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"商品名"}
		,{"Lot"			,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"ロット"}
		,{"ExpDate"		,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"消費期限"}
		,{"PlanQty"		,"int"		,(int)11	,""		,(boolean)false	,"0"	,"予定数量"}
		,{"ActualQty"	,"int"		,(int)11	,""		,(boolean)false	,"0"	,"実績数"}
		*/
		
		
		
		
		TB_ArrNo.setBackground(B100_FrameParts.SelectColer("Entry"));
		
		TB_ClWh.setEnabled(false);
		TB_ClCd.setEnabled(false);
		TB_SpCd.setEnabled(false);
		TB_FixFg.setEnabled(false);
		
		TB_ArrNo.setEditable(true);
		TB_ClArrNo.setEditable(false);
		TB_PlanDate.setEditable(false);
		TB_HdActualDate.setEditable(false);
		
		TB_ArCom01.setEditable(false);
		TB_ArCom02.setEditable(false);
		TB_ArCom03.setEditable(false);
		
		TB_HdEntryDate.setEditable(false);
		TB_HdUpdateDate.setEditable(false);
		TB_HdEntryUser.setEditable(false);
		TB_HdUpdateUser.setEditable(false);
		
		
		PN_Hd.add(PN_HdLabel);
		
		PN_Hd.add(LB_ClWh);
		PN_Hd.add(LB_ClCd);
		PN_Hd.add(LB_SpCd);
		PN_Hd.add(LB_ArrNo);
		PN_Hd.add(LB_ClArrNo);
		PN_Hd.add(LB_PlanDate);
		PN_Hd.add(LB_HdActualDate);
		
		PN_Hd.add(LB_FixFg);
		PN_Hd.add(LB_ArCom01);
		PN_Hd.add(LB_ArCom02);
		PN_Hd.add(LB_ArCom03);
		
		PN_Hd.add(LB_HdEntryDate);
		PN_Hd.add(LB_HdEntryUser);
		PN_Hd.add(LB_HdUpdateDate);
		PN_Hd.add(LB_HdUpdateUser);
		
		PN_Hd.add(TB_ClWh);
		PN_Hd.add(TB_ClCd);
		PN_Hd.add(TB_SpCd);
		PN_Hd.add(TB_ArrNo);
		PN_Hd.add(TB_ClArrNo);
		PN_Hd.add(TB_PlanDate);
		PN_Hd.add(TB_HdActualDate);

		PN_Hd.add(TB_FixFg);
		PN_Hd.add(TB_ArCom01);
		PN_Hd.add(TB_ArCom02);
		PN_Hd.add(TB_ArCom03);
		
		PN_Hd.add(TB_HdEntryDate);
		PN_Hd.add(TB_HdUpdateDate);
		PN_Hd.add(TB_HdEntryUser);
		PN_Hd.add(TB_HdUpdateUser);
		
		main_fm.add(PN_Hd);
		main_fm.add(PN_Entry);
		
		RenewFg = true;
		main_fm.setVisible(true);
		
		//EXITボタン押下時の挙動
		exit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();
				
				main_fm.setVisible(false);
				main_fm.dispose();
				WT100_ArrivalPlan_00_Search.ArrivalPlanSearch(0,0);
			}
		});
	}
	
	private static Object[][] ArrivalPlanHdRt(String TgtWhCd,String TgtClCd,String TgtArrNo){
		ArrayList<String> SearchClWh 			= new ArrayList<String>();		//ヘッダ担当倉庫
		ArrayList<String> SearchClCd 			= new ArrayList<String>();		//ヘッダ荷主CD
		ArrayList<String> SearchCLName01 		= new ArrayList<String>();		//ヘッダ荷主名
		ArrayList<String> SearchClGpCD 			= new ArrayList<String>();		//ヘッダ荷主グループCD
		ArrayList<String> SearchCLGpName01 		= new ArrayList<String>();		//ヘッダ荷主グループ標記名
		ArrayList<String> SearchArrNo 			= new ArrayList<String>();		//ヘッダ入荷予定NO
		ArrayList<String> SearchClArrNo 		= new ArrayList<String>();		//ヘッダ荷主予定番号
		ArrayList<String> SearchPlanDateMin 	= new ArrayList<String>();		//ヘッダ入荷予定日最小
		ArrayList<String> SearchPlanDateMax 	= new ArrayList<String>();		//ヘッダ入荷予定日最大
		ArrayList<String> SearchHdActualDateMin = new ArrayList<String>();		//ヘッダ入荷実績日最小
		ArrayList<String> SearchHdActualDateMax	= new ArrayList<String>();		//ヘッダ入荷実績日最大
		ArrayList<String> SearchSpCd 			= new ArrayList<String>();		//ヘッダ仕入先CD
		ArrayList<String> SearchSpName 			= new ArrayList<String>();		//ヘッダ仕入先名
		ArrayList<String> SearchSpPost 			= new ArrayList<String>();		//ヘッダ仕入先郵便
		ArrayList<String> SearchSpAdd 			= new ArrayList<String>();		//ヘッダ仕入先住所
		ArrayList<String> SearchSpTel 			= new ArrayList<String>();		//ヘッダ仕入先電話
		ArrayList<String> SearchArCom 			= new ArrayList<String>();		//ヘッダコメント
		ArrayList<Integer> SearchFixFg 			= new ArrayList<Integer>();		//ヘッダ状況
				
		ArrayList<Integer> SearchMsNoMin 		= new ArrayList<Integer>();		//明細番号最小
		ArrayList<Integer> SearchMsNoMax 		= new ArrayList<Integer>();		//明細番号最大
		ArrayList<String> SearchItemCd 			= new ArrayList<String>();		//商品コード
		ArrayList<String> SearchClItemCd 		= new ArrayList<String>();		//荷主商品コード
		ArrayList<String> SearchJanCd 			= new ArrayList<String>();		//JANCD（バラ）
		ArrayList<String> SearchItemMdNo 		= new ArrayList<String>();		//商品型番
		ArrayList<String> SearchItemName 		= new ArrayList<String>();		//商品名
		ArrayList<String> Searchlot 			= new ArrayList<String>();		//ロット
		ArrayList<String> SearchExpDateMin 		= new ArrayList<String>();		//消費期限最小
		ArrayList<String> SearchExpDateMax 		= new ArrayList<String>();		//消費期限最大
		ArrayList<Integer> SearchPlanQtyMin 	= new ArrayList<Integer>();		//予定数量最小
		ArrayList<Integer> SearchPlanQtyMax 	= new ArrayList<Integer>();		//予定数量最大
		ArrayList<Integer> SearchActualQtyMin 	= new ArrayList<Integer>();		//実績数最小
		ArrayList<Integer> SearchActualQtyMax 	= new ArrayList<Integer>();		//実績数最大
		ArrayList<String> SearchActualDateMin 	= new ArrayList<String>();		//入荷日最小
		ArrayList<String> SearchActualDateMax 	= new ArrayList<String>();		//入荷日最大
		ArrayList<String> SearchCom 			= new ArrayList<String>();		//コメント
		ArrayList<String> SearchEntryDateMin 	= new ArrayList<String>();		//登録日最小
		ArrayList<String> SearchEntryDateMax 	= new ArrayList<String>();		//登録日最大
		ArrayList<String> SearchUpdateDateMin 	= new ArrayList<String>();		//更新日最小
		ArrayList<String> SearchUpdateDateMax 	= new ArrayList<String>();		//更新日最大
		ArrayList<String> SearchEntryUser 		= new ArrayList<String>();		//登録者
		ArrayList<String> SearchUpdateUser 		= new ArrayList<String>();		//更新者
		boolean AllSearch = false;
		
		if(null==TgtWhCd	) {TgtWhCd	= "";}
		if(null==TgtClCd	) {TgtClCd	= "";}
		if(null==TgtArrNo	) {TgtArrNo	= "";}
		
		if("".equals(TgtWhCd)) {TgtWhCd	= A00000_Main.ClWh;}
		if("".equals(TgtClCd)) {TgtClCd	= A00000_Main.ClCd;}
		
		if(!"".equals(TgtArrNo)) {
			SearchClWh.add(TgtWhCd);
			SearchClCd.add(TgtClCd);
			SearchArrNo.add(TgtArrNo);
		}
		
		Object[][] ArrivalPlanHdRt = T100_ArrivalPlanHdRt.ArrivalPlanHdRt(
				SearchClWh,				//ヘッダ担当倉庫
				SearchClCd,				//ヘッダ荷主CD
				SearchCLName01,			//ヘッダ荷主名
				SearchClGpCD,			//ヘッダ荷主グループCD
				SearchCLGpName01,		//ヘッダ荷主グループ標記名
				SearchArrNo,			//ヘッダ入荷予定NO
				SearchClArrNo,			//ヘッダ荷主予定番号
				SearchPlanDateMin,		//ヘッダ入荷予定日最小
				SearchPlanDateMax,		//ヘッダ入荷予定日最大
				SearchHdActualDateMin,	//ヘッダ入荷実績日最小
				SearchHdActualDateMax,	//ヘッダ入荷実績日最大
				SearchSpCd,				//ヘッダ仕入先CD
				SearchSpName,			//ヘッダ仕入先名
				SearchSpPost,			//ヘッダ仕入先郵便
				SearchSpAdd,			//ヘッダ仕入先住所
				SearchSpTel,			//ヘッダ仕入先電話
				SearchArCom,			//ヘッダコメント
				SearchFixFg,			//ヘッダ状況
						
				SearchMsNoMin,			//明細番号最小
				SearchMsNoMax,			//明細番号最大
				SearchItemCd,			//商品コード
				SearchClItemCd,			//荷主商品コード
				SearchJanCd,			//JANCD（バラ）
				SearchItemMdNo,			//商品型番
				SearchItemName,			//商品名
				Searchlot,				//ロット
				SearchExpDateMin,		//消費期限最小
				SearchExpDateMax,		//消費期限最大
				SearchPlanQtyMin,		//予定数量最小
				SearchPlanQtyMax,		//予定数量最大
				SearchActualQtyMin,		//実績数最小
				SearchActualQtyMax,		//実績数最大
				SearchActualDateMin,	//入荷日最小
				SearchActualDateMax,	//入荷日最大
				SearchCom,				//コメント
				SearchEntryDateMin,		//登録日最小
				SearchEntryDateMax,		//登録日最大
				SearchUpdateDateMin,	//更新日最小
				SearchUpdateDateMax,	//更新日最大
				SearchEntryUser,		//登録者
				SearchUpdateUser,		//更新者
				AllSearch);
		
		return ArrivalPlanHdRt;
	}
	
	private static Object[][] ArrivalPlanMsRt(String TgtWhCd,String TgtClCd,String TgtArrNo){
		ArrayList<String> SearchClWh			= new ArrayList<String>();		//ヘッダ担当倉庫
		ArrayList<String> SearchClCd			= new ArrayList<String>();		//ヘッダ荷主CD
		ArrayList<String> SearchCLName01		= new ArrayList<String>();		//ヘッダ荷主名
		ArrayList<String> SearchClGpCD			= new ArrayList<String>();		//ヘッダ荷主グループCD
		ArrayList<String> SearchCLGpName01		= new ArrayList<String>();		//ヘッダ荷主グループ標記名
		ArrayList<String> SearchArrNo			= new ArrayList<String>();		//ヘッダ入荷予定NO
		ArrayList<String> SearchClArrNo			= new ArrayList<String>();		//ヘッダ荷主予定番号
		ArrayList<String> SearchPlanDateMin		= new ArrayList<String>();		//ヘッダ入荷予定日
		ArrayList<String> SearchPlanDateMax		= new ArrayList<String>();		//ヘッダ入荷予定日
		ArrayList<String> SearchHdActualDateMin	= new ArrayList<String>();		//ヘッダ入荷実績日
		ArrayList<String> SearchHdActualDateMax	= new ArrayList<String>();		//ヘッダ入荷実績日
		ArrayList<String> SearchSpCd			= new ArrayList<String>();		//ヘッダ仕入先CD
		ArrayList<String> SearchSpName			= new ArrayList<String>();		//ヘッダ仕入先名
		ArrayList<String> SearchSpPost			= new ArrayList<String>();		//ヘッダ仕入先郵便
		ArrayList<String> SearchSpAdd			= new ArrayList<String>();		//ヘッダ仕入先住所
		ArrayList<String> SearchSpTel			= new ArrayList<String>();		//ヘッダ仕入先電話
		ArrayList<String> SearchArCom			= new ArrayList<String>();		//ヘッダコメント
		ArrayList<Integer> SearchFixFg			= new ArrayList<Integer>();		//ヘッダ状況
				
		ArrayList<Integer> SearchMsNoMin		= new ArrayList<Integer>();		//明細番号最小
		ArrayList<Integer> SearchMsNoMax		= new ArrayList<Integer>();		//明細番号最大
		ArrayList<String> SearchItemCd			= new ArrayList<String>();		//商品コード
		ArrayList<String> SearchClItemCd		= new ArrayList<String>();		//荷主商品コード
		ArrayList<String> SearchJanCd			= new ArrayList<String>();		//JANCD（バラ）
		ArrayList<String> SearchItemMdNo		= new ArrayList<String>();		//商品型番
		ArrayList<String> SearchItemName		= new ArrayList<String>();		//商品名
		ArrayList<String> Searchlot				= new ArrayList<String>();		//ロット
		ArrayList<String> SearchExpDateMin		= new ArrayList<String>();		//消費期限最小
		ArrayList<String> SearchExpDateMax		= new ArrayList<String>();		//消費期限最大
		ArrayList<Integer> SearchPlanQtyMin		= new ArrayList<Integer>();		//予定数量最小
		ArrayList<Integer> SearchPlanQtyMax		= new ArrayList<Integer>();		//予定数量最大
		ArrayList<Integer> SearchActualQtyMin	= new ArrayList<Integer>();		//実績数
		ArrayList<Integer> SearchActualQtyMax	= new ArrayList<Integer>();		//実績数
		ArrayList<String> SearchActualDateMin	= new ArrayList<String>();		//入荷日
		ArrayList<String> SearchActualDateMax	= new ArrayList<String>();		//入荷日
		ArrayList<String> SearchCom				= new ArrayList<String>();		//コメント
		ArrayList<String> SearchEntryDateMin	= new ArrayList<String>();		//登録日
		ArrayList<String> SearchEntryDateMax	= new ArrayList<String>();		//登録日
		ArrayList<String> SearchUpdateDateMin	= new ArrayList<String>();		//更新日
		ArrayList<String> SearchUpdateDateMax	= new ArrayList<String>();		//更新日
		ArrayList<String> SearchEntryUser		= new ArrayList<String>();		//登録者
		ArrayList<String> SearchUpdateUser		= new ArrayList<String>();		//更新者
		boolean AllSearch = false;
		
		if(null==TgtWhCd	) {TgtWhCd	= "";}
		if(null==TgtClCd	) {TgtClCd	= "";}
		if(null==TgtArrNo	) {TgtArrNo	= "";}
		
		if("".equals(TgtWhCd)) {TgtWhCd	= A00000_Main.ClWh;}
		if("".equals(TgtClCd)) {TgtClCd	= A00000_Main.ClCd;}
		
		if(!"".equals(TgtArrNo)) {
			SearchClWh.add(TgtWhCd);
			SearchClCd.add(TgtClCd);
			SearchArrNo.add(TgtArrNo);
			SearchFixFg.add(0);
			SearchFixFg.add(2);
		}
		
		Object[][] ArrivalPlanMsRt	= T100_ArrivalPlanMsRt.ArrivalPlanMsRt(
				SearchClWh,					//ヘッダ担当倉庫
				SearchClCd,					//ヘッダ荷主CD
				SearchCLName01,				//ヘッダ荷主名
				SearchClGpCD,				//ヘッダ荷主グループCD
				SearchCLGpName01,			//ヘッダ荷主グループ標記名
				SearchArrNo,				//ヘッダ入荷予定NO
				SearchClArrNo,				//ヘッダ荷主予定番号
				SearchPlanDateMin,			//ヘッダ入荷予定日
				SearchPlanDateMax,			//ヘッダ入荷予定日
				SearchHdActualDateMin,		//ヘッダ入荷実績日
				SearchHdActualDateMax,		//ヘッダ入荷実績日
				SearchSpCd,					//ヘッダ仕入先CD
				SearchSpName,				//ヘッダ仕入先名
				SearchSpPost,				//ヘッダ仕入先郵便
				SearchSpAdd,				//ヘッダ仕入先住所
				SearchSpTel,				//ヘッダ仕入先電話
				SearchArCom,				//ヘッダコメント
				SearchFixFg,				//ヘッダ状況
						
				SearchMsNoMin,				//明細番号最小
				SearchMsNoMax,				//明細番号最大
				SearchItemCd,				//商品コード
				SearchClItemCd,				//荷主商品コード
				SearchJanCd,				//JANCD（バラ）
				SearchItemMdNo,				//商品型番
				SearchItemName,				//商品名
				Searchlot,					//ロット
				SearchExpDateMin,			//消費期限最小
				SearchExpDateMax,			//消費期限最大
				SearchPlanQtyMin,			//予定数量最小
				SearchPlanQtyMax,			//予定数量最大
				SearchActualQtyMin,			//実績数
				SearchActualQtyMax,			//実績数
				SearchActualDateMin,		//入荷日
				SearchActualDateMax,		//入荷日
				SearchCom,					//コメント
				SearchEntryDateMin,			//登録日
				SearchEntryDateMax,			//登録日
				SearchUpdateDateMin,		//更新日
				SearchUpdateDateMax,		//更新日
				SearchEntryUser,			//登録者
				SearchUpdateUser,			//更新者
				AllSearch);
		
		return ArrivalPlanMsRt;
	}
	
	
	
}