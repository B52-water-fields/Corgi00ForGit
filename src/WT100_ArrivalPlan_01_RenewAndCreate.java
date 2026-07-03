import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
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

public class WT100_ArrivalPlan_01_RenewAndCreate{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	public static void ArrivalPlanRenewAndCreate(int x,int y,String TgtWhCd,String TgtClCd,String TgtArrNo) {
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
		
		final JFrame main_fm = B100_FrameParts.FrameCreate(x,y,900,800,"Corgi00入荷予定登録・修正","NK");
		JLabel userinfo = B100_FrameParts.UserInfo();
		JButton exit_btn = B100_FrameParts.ExitBtn();
		JButton entry_btn = B100_FrameParts.EntryBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		main_fm.add(entry_btn);
		
		JLabel LB_ClWh			= B100_FrameParts.JLabelSet(  0, 50,130,20,"担当倉庫:",		11,1);
		JLabel LB_ClCd			= B100_FrameParts.JLabelSet(  0, 75,130,20,"荷主CD:",			11,1);
		JLabel LB_SpCd			= B100_FrameParts.JLabelSet(  0,100,130,20,"仕入先CD:",		11,1);
		JLabel LB_ArrNo			= B100_FrameParts.JLabelSet(  0,125,130,20,"入荷予定NO:",		11,1);
		JLabel LB_ClArrNo		= B100_FrameParts.JLabelSet(  0,150,130,20,"荷主予定番号:",	11,1);
		JLabel LB_PlanDate		= B100_FrameParts.JLabelSet(  0,175,130,20,"入荷予定日:",		11,1);
		JLabel LB_HdActualDate	= B100_FrameParts.JLabelSet(  0,200,130,20,"入荷実績日:",		11,1);
		
		JLabel LB_FixFg			= B100_FrameParts.JLabelSet(240,125,100,20,"状況:",			11,1);
		JLabel LB_ArCom01		= B100_FrameParts.JLabelSet(240,150,100,20,"コメント1:",		11,1);
		JLabel LB_ArCom02		= B100_FrameParts.JLabelSet(240,175,100,20,"コメント2:",		11,1);
		JLabel LB_ArCom03		= B100_FrameParts.JLabelSet(240,200,100,20,"コメント3:",		11,1);
		
		JLabel LB_HdEntryDate	= B100_FrameParts.JLabelSet(540,125,100,20,"登録日:",			11,1);
		JLabel LB_HdEntryUser	= B100_FrameParts.JLabelSet(540,150,100,20,"登録者:",			11,1);
		JLabel LB_HdUpdateDate	= B100_FrameParts.JLabelSet(540,175,100,20,"更新日:",			11,1);
		JLabel LB_HdUpdateUser	= B100_FrameParts.JLabelSet(540,200,100,20,"更新者:",			11,1);
		
		final JComboBox TB_ClWh						= B100_FrameParts.JComboBoxSet(				130, 50,240,20,B100_DefaultVariable.WhList[0],11);						//ヘッダ担当倉庫
		final JComboBox TB_ClCd						= B100_FrameParts.JComboBoxSet(				130, 75,240,20,B100_DefaultVariable.ClList[0],11);						//ヘッダ荷主CD
		final JComboBox TB_SpCd						= B100_FrameParts.JComboBoxSet(				130,100,240,20,B100_DefaultVariable.SupplierList[0],11);				//ヘッダ仕入先
		final JTextField TB_ArrNo					= B100_FrameParts.JTextFieldSet(				130,125,100,20,"",11,0);												//入荷予定NO
		final JTextField TB_ClArrNo					= B100_FrameParts.JTextFieldSet(				130,150,100,20,"",11,0);												//荷主予定番号
		final JFormattedTextField TB_PlanDate		= B100_FrameParts.JFormattedTextFieldSet(	130,175, 70,20,"",11,0,"YYYY/MM/DD");									//入荷予定日
		final JFormattedTextField TB_HdActualDate	= B100_FrameParts.JFormattedTextFieldSet(	130,200, 70,20,"",11,0,"YYYY/MM/DD");									//入荷実績日

		final JComboBox TB_FixFg					= B100_FrameParts.JComboBoxSet(				340,125,150,20,B100_DefaultVariable.ArryvalFixFgList[0],11);	//状況
		final JTextField TB_ArCom01					= B100_FrameParts.JTextFieldSet(				340,150,240,20,"",11,0);												//コメント1
		final JTextField TB_ArCom02					= B100_FrameParts.JTextFieldSet(				340,175,240,20,"",11,0);												//コメント2
		final JTextField TB_ArCom03					= B100_FrameParts.JTextFieldSet(				340,200,240,20,"",11,0);												//コメント3
		
		final JTextField TB_HdEntryDate				= B100_FrameParts.JTextFieldSet(				640,125,200,20,"",11,0);												//登録日
		final JTextField TB_HdUpdateDate			= B100_FrameParts.JTextFieldSet(				640,150,200,20,"",11,0);												//更新日
		final JTextField TB_HdEntryUser				= B100_FrameParts.JTextFieldSet(				640,175,200,20,"",11,0);												//登録者
		final JTextField TB_HdUpdateUser			= B100_FrameParts.JTextFieldSet(				640,200,200,20,"",11,0);												//更新者
		
		//別伝票新規
		JButton NewOrder_btn	= B100_FrameParts.BtnSet(	400, 50,100,20,"別伝票新規",11);
		//伝票複写
		JButton OrderCopy_btn	= B100_FrameParts.BtnSet(	400, 75,100,20,"伝票複写",11);
		//キャンセルボタン
		JButton CancelBtn 		= B100_FrameParts.BtnSet(	400,100,100,20,"予定キャンセル",9);
		
		TB_ClWh.setSelectedIndex(B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.WhList[1]	,TgtWhCd,true));
		TB_ClCd.setSelectedIndex(B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.ClList[1]	,TgtClCd,true));
		TB_FixFg.setSelectedIndex(B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.ArryvalFixFgList[1]	,"0",true));
		TB_ArrNo.setText(TgtArrNo);
		
		TB_ClWh.setEnabled(false);
		TB_ClCd.setEnabled(false);
		TB_FixFg.setEnabled(false);
		TB_ArrNo.setEditable(false);
		TB_PlanDate.setEditable(true);
		TB_HdActualDate.setEditable(false);
		TB_ArCom01.setEditable(true);
		TB_ArCom02.setEditable(true);
		TB_ArCom03.setEditable(true);
		TB_HdEntryDate.setEditable(false);
		TB_HdUpdateDate.setEditable(false);
		TB_HdEntryUser.setEditable(false);
		TB_HdUpdateUser.setEditable(false);
		TB_HdActualDate.setEditable(false);
		
		
		//入荷予定日進む戻るボタン
		JButton TB_PlanDateAfterBtn		= B100_FrameParts.BtnSet(200,175, 40,10,"▲",6);
		JButton TB_PlanDateBeforeBtn	= B100_FrameParts.BtnSet(200,185, 40,10,"▼",6);
		//賞味期限日進む戻るボタン押下事の挙動
		TB_PlanDateAfterBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				B100_DateTimeControl.AfterDateSet(TB_PlanDate);
			}
		});
		TB_PlanDateBeforeBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				B100_DateTimeControl.BeforeDateSet(TB_PlanDate);
			}
		});
		
		main_fm.add(LB_ClWh);
		main_fm.add(LB_ClCd);
		main_fm.add(LB_SpCd);
		main_fm.add(LB_ArrNo);
		main_fm.add(LB_ClArrNo);
		main_fm.add(LB_PlanDate);
		main_fm.add(LB_HdActualDate);
		
		main_fm.add(LB_FixFg);
		main_fm.add(LB_ArCom01);
		main_fm.add(LB_ArCom02);
		main_fm.add(LB_ArCom03);
		
		main_fm.add(LB_HdEntryDate);
		main_fm.add(LB_HdEntryUser);
		main_fm.add(LB_HdUpdateDate);
		main_fm.add(LB_HdUpdateUser);
		
		main_fm.add(TB_ClWh);
		main_fm.add(TB_ClCd);
		main_fm.add(TB_SpCd);
		main_fm.add(TB_ArrNo);
		main_fm.add(TB_ClArrNo);
		main_fm.add(TB_PlanDate);
		main_fm.add(TB_HdActualDate);

		main_fm.add(TB_FixFg);
		main_fm.add(TB_ArCom01);
		main_fm.add(TB_ArCom02);
		main_fm.add(TB_ArCom03);
		
		main_fm.add(TB_HdEntryDate);
		main_fm.add(TB_HdUpdateDate);
		main_fm.add(TB_HdEntryUser);
		main_fm.add(TB_HdUpdateUser);
		
		Object[][] RtArrivalPlanMsRt = T100_ArrivalPlanMsRt.RtArrivalPlanMsRt();
		
		String[] columnNames01 = new String[RtArrivalPlanMsRt.length+1];
		
		columnNames01[0] = "Fg";
		for(int i=0;i<RtArrivalPlanMsRt.length;i++) {
			columnNames01[1+(int)RtArrivalPlanMsRt[i][1]] = ""+RtArrivalPlanMsRt[i][3];
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
		
		column = columnModel01.getColumn( 0);	column.setPreferredWidth( 30*A00000_Main.Mul/A00000_Main.Div);	//FG
		
		for(int i=0;i<RtArrivalPlanMsRt.length;i++) {
			if("int".equals((String)RtArrivalPlanMsRt[i][2])||"float".equals((String)RtArrivalPlanMsRt[i][2])) {
				column = columnModel01.getColumn(1+(int)RtArrivalPlanMsRt[i][1]);	column.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.rightCellRenderer());
			}else {
				column = columnModel01.getColumn(1+(int)RtArrivalPlanMsRt[i][1]);	column.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.leftCellRenderer());
			}
		}
		//スクロール用設定
		JScrollPane scpn01 = B100_FrameParts.JScrollPaneSet(10,250,860,200,tb01);
		main_fm.add(scpn01);
		
		//明細情報メンテナンス用
		JLabel LBMs_MsNo		= B100_FrameParts.JLabelSet(  0,475,130,20,"明細番号:"			,11,1);
		JLabel LBMs_ItemCd		= B100_FrameParts.JLabelSet(  0,500,130,20,"商品コード:"		,11,1);
		JLabel LBMs_ItemName	= B100_FrameParts.JLabelSet(  0,525,130,20,"商品名:"			,11,1);
		JLabel LBMs_lot			= B100_FrameParts.JLabelSet(  0,550,130,20,"ロット:"			,11,1);
		JLabel LBMs_ExpDate		= B100_FrameParts.JLabelSet(  0,575,130,20,"消費期限:"			,11,1);
		JLabel LBMs_PlanQty		= B100_FrameParts.JLabelSet(  0,600,130,20,"予定数量:"			,11,1);
		JLabel LBMs_ActualQty	= B100_FrameParts.JLabelSet(200,600, 60,20,"実績数:"			,11,1);
		JLabel LBMs_Com01		= B100_FrameParts.JLabelSet(  0,625,130,20,"コメント1:"		,11,1);
		JLabel LBMs_Com02		= B100_FrameParts.JLabelSet(  0,650,130,20,"コメント2:"		,11,1);
		
		
		JLabel LBMs_ClItemCd	= B100_FrameParts.JLabelSet(330,475,130,20,"荷主商品コード:"	,11,1);
		JLabel LBMs_ActualDate	= B100_FrameParts.JLabelSet(330,500,130,20,"入荷日:"			,11,1);
		JLabel LBMs_JanCd		= B100_FrameParts.JLabelSet(330,525,130,20,"JANCD:"			,11,1);
		JLabel LBMs_ItemMdNo	= B100_FrameParts.JLabelSet(330,550,130,20,"商品型番:"			,11,1);
		JLabel LBMs_EntryDate	= B100_FrameParts.JLabelSet(330,575,130,20,"登録日:"			,11,1);
		JLabel LBMs_UpdateDate	= B100_FrameParts.JLabelSet(330,600,130,20,"更新日:"			,11,1);
		JLabel LBMs_EntryUser	= B100_FrameParts.JLabelSet(330,625,130,20,"登録者:"			,11,1);
		JLabel LBMs_UpdateUser	= B100_FrameParts.JLabelSet(330,650,130,20,"更新者:"			,11,1);
		
		final JFormattedTextField TBMs_MsNo			= B100_FrameParts.JFormattedTextFieldSet(	130,475, 70,20,""	,11,1,"####");						//明細番号
		final JTextField TBMs_ItemCd				= B100_FrameParts.JTextFieldSet(				130,500,100,20,""	,11,0);								//商品コード
		final JTextField TBMs_ItemName				= B100_FrameParts.JTextFieldSet(				130,525,200,20,""	,11,0);								//商品名
		final JTextField TBMs_lot					= B100_FrameParts.JTextFieldSet(				130,550,100,20,""	,11,0);								//ロット
		final JFormattedTextField TBMs_ExpDate		= B100_FrameParts.JFormattedTextFieldSet(	130,575,100,20,""	,11,0,"YYYY/MM/DD");				//消費期限
		final JFormattedTextField TBMs_PlanQty		= B100_FrameParts.JFormattedTextFieldSet(	130,600, 70,20,""	,11,1,"####");						//予定数量
		final JFormattedTextField TBMs_ActualQty	= B100_FrameParts.JFormattedTextFieldSet(	260,600, 70,20,""	,11,1,"####");						//実績数
		final JTextField TBMs_Com01					= B100_FrameParts.JTextFieldSet(				130,625,200,20,""	,11,0);								//コメント1
		final JTextField TBMs_Com02					= B100_FrameParts.JTextFieldSet(				130,650,200,20,""	,11,0);								//コメント2
		
		final JTextField TBMs_ClItemCd				= B100_FrameParts.JTextFieldSet(				460,475,100,20,""	,11,0);								//荷主商品コード
		final JFormattedTextField TBMs_ActualDate	= B100_FrameParts.JFormattedTextFieldSet(	460,500,200,20,""	,11,0,"YYYY/MM/DD HH:MM:SS");		//入荷日
		final JTextField TBMs_JanCd					= B100_FrameParts.JTextFieldSet(				460,525,100,20,""	,11,0);								//JANCD（バラ）
		final JTextField TBMs_ItemMdNo				= B100_FrameParts.JTextFieldSet(				460,550,100,20,""	,11,0);								//商品型番
		final JFormattedTextField TBMs_EntryDate	= B100_FrameParts.JFormattedTextFieldSet(	460,575,200,20,""	,11,0,"YYYY/MM/DD HH:MM:SS");		//登録日
		final JFormattedTextField TBMs_UpdateDate	= B100_FrameParts.JFormattedTextFieldSet(	460,600,200,20,""	,11,0,"YYYY/MM/DD HH:MM:SS");		//更新日
		final JTextField TBMs_EntryUser				= B100_FrameParts.JTextFieldSet(				460,625,200,20,""	,11,0);								//登録者
		final JTextField TBMs_UpdateUser			= B100_FrameParts.JTextFieldSet(				460,650,200,20,""	,11,0);								//更新者
		
		//商品検索ボタン
		JButton MsItemSearchBtn 		= B100_FrameParts.BtnSet(	250,500,100,20,"商品検索",11);
		main_fm.add(MsItemSearchBtn);
		
		//修正仮反映ボタン
		JButton MsRenewBtn 				= B100_FrameParts.BtnSet(	230,675,100,20,"修正仮反映",11);
		main_fm.add(MsRenewBtn);
		//明細行削除ボタン
		JButton MsDeleteBtn 			= B100_FrameParts.BtnSet(	560,675,100,20,"行削除",11);
		main_fm.add(MsDeleteBtn);
		
		TBMs_MsNo.setEditable(false);
		TBMs_ItemCd.setEditable(true);
		TBMs_ItemName.setEditable(false);
		TBMs_lot.setEditable(true);
		TBMs_ExpDate.setEditable(true);
		TBMs_PlanQty.setEditable(true);
		TBMs_ActualQty.setEditable(false);
		TBMs_Com01.setEditable(true);
		TBMs_Com02.setEditable(true);
		
		TBMs_ClItemCd.setEditable(false);
		TBMs_ActualDate.setEditable(false);
		TBMs_JanCd.setEditable(false);
		TBMs_ItemMdNo.setEditable(false);
		TBMs_EntryDate.setEditable(false);
		TBMs_UpdateDate.setEditable(false);
		TBMs_EntryUser.setEditable(false);
		TBMs_UpdateUser.setEditable(false);
		
		//賞味期限進む戻るボタン
		JButton TBMs_ExpDateAfterBtn	= B100_FrameParts.BtnSet(230,575, 40,10,"▲",6);
		JButton TBMs_ExpDateBeforeBtn	= B100_FrameParts.BtnSet(230,585, 40,10,"▼",6);
		//賞味期限日進む戻るボタン押下事の挙動
		TBMs_ExpDateAfterBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				B100_DateTimeControl.AfterDateSet(TBMs_ExpDate);
			}
		});
		TBMs_ExpDateBeforeBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				B100_DateTimeControl.BeforeDateSet(TBMs_ExpDate);
			}
		});
		
		main_fm.add(LBMs_MsNo);
		main_fm.add(LBMs_ItemCd);
		main_fm.add(LBMs_ItemName);
		main_fm.add(LBMs_lot);
		main_fm.add(LBMs_ExpDate);
		main_fm.add(LBMs_PlanQty);
		main_fm.add(LBMs_ActualQty);
		main_fm.add(LBMs_Com01);
		main_fm.add(LBMs_Com02);
		
		main_fm.add(LBMs_ClItemCd);
		main_fm.add(LBMs_ActualDate);
		main_fm.add(LBMs_JanCd);
		main_fm.add(LBMs_ItemMdNo);
		main_fm.add(LBMs_EntryDate);
		main_fm.add(LBMs_UpdateDate);
		main_fm.add(LBMs_EntryUser);
		main_fm.add(LBMs_UpdateUser);
		
		main_fm.add(TBMs_MsNo);
		main_fm.add(TBMs_ItemCd);
		main_fm.add(TBMs_ItemName);
		main_fm.add(TBMs_lot);
		main_fm.add(TBMs_ExpDate);
		main_fm.add(TBMs_PlanQty);
		main_fm.add(TBMs_ActualQty);
		main_fm.add(TBMs_Com01);
		main_fm.add(TBMs_Com02);
		
		main_fm.add(TBMs_ClItemCd);
		main_fm.add(TBMs_ActualDate);
		main_fm.add(TBMs_JanCd);
		main_fm.add(TBMs_ItemMdNo);
		main_fm.add(TBMs_EntryDate);
		main_fm.add(TBMs_UpdateDate);
		main_fm.add(TBMs_EntryUser);
		main_fm.add(TBMs_UpdateUser);
		
		main_fm.add(TBMs_ExpDateAfterBtn);
		main_fm.add(TBMs_ExpDateBeforeBtn);
		
		
		if(!"".equals(TgtArrNo)) {
			Object[][] ArrivalPlanMsRt	= ArrivalPlanMsRt(TgtWhCd,TgtClCd,TgtArrNo);
			
			if(0<ArrivalPlanMsRt.length) {
				//別伝票新規
				main_fm.add(NewOrder_btn);
				//伝票複写
				main_fm.add(OrderCopy_btn);
				
				String GetClWh			= (String)ArrivalPlanMsRt[0][T100_ArrivalPlanMsRt.ColClWh];			//ヘッダ担当倉庫
				String GetClCd			= (String)ArrivalPlanMsRt[0][T100_ArrivalPlanMsRt.ColClCd];			//ヘッダ荷主CD
				String GetCLName01		= (String)ArrivalPlanMsRt[0][T100_ArrivalPlanMsRt.ColCLName01];		//ヘッダ荷主名
				String GetClGpCD		= (String)ArrivalPlanMsRt[0][T100_ArrivalPlanMsRt.ColClGpCD];			//ヘッダ荷主グループCD
				String GetCLGpName01	= (String)ArrivalPlanMsRt[0][T100_ArrivalPlanMsRt.ColCLGpName01];		//ヘッダ荷主グループ名1
				String GetArrNo			= (String)ArrivalPlanMsRt[0][T100_ArrivalPlanMsRt.ColArrNo];			//ヘッダ入荷予定NO
				String GetClArrNo		= (String)ArrivalPlanMsRt[0][T100_ArrivalPlanMsRt.ColClArrNo];			//ヘッダ荷主予定番号
				String GetPlanDate		= (String)ArrivalPlanMsRt[0][T100_ArrivalPlanMsRt.ColPlanDate];		//ヘッダ入荷予定日
				String GetHdActualDate	= (String)ArrivalPlanMsRt[0][T100_ArrivalPlanMsRt.ColHdActualDate];	//ヘッダ入荷実績日
				String GetSpCd			= (String)ArrivalPlanMsRt[0][T100_ArrivalPlanMsRt.ColSpCd];			//ヘッダ仕入先CD
				String GetSpName01		= (String)ArrivalPlanMsRt[0][T100_ArrivalPlanMsRt.ColSpName01];		//ヘッダ仕入先名01
				String GetSpName02		= (String)ArrivalPlanMsRt[0][T100_ArrivalPlanMsRt.ColSpName02];		//ヘッダ仕入先名02
				String GetSpName03		= (String)ArrivalPlanMsRt[0][T100_ArrivalPlanMsRt.ColSpName03];		//ヘッダ仕入先名03
				String GetSpPost		= (String)ArrivalPlanMsRt[0][T100_ArrivalPlanMsRt.ColSpPost];			//ヘッダ仕入先郵便
				String GetSpAdd01		= (String)ArrivalPlanMsRt[0][T100_ArrivalPlanMsRt.ColSpAdd01];			//ヘッダ仕入先住所01
				String GetSpAdd02		= (String)ArrivalPlanMsRt[0][T100_ArrivalPlanMsRt.ColSpAdd02];			//ヘッダ仕入先住所02
				String GetSpAdd03		= (String)ArrivalPlanMsRt[0][T100_ArrivalPlanMsRt.ColSpAdd03];			//ヘッダ仕入先住所03
				String GetSpTel			= (String)ArrivalPlanMsRt[0][T100_ArrivalPlanMsRt.ColSpTel];			//ヘッダ仕入先電話
				String GetArCom01		= (String)ArrivalPlanMsRt[0][T100_ArrivalPlanMsRt.ColArCom01];			//ヘッダコメント1
				String GetArCom02		= (String)ArrivalPlanMsRt[0][T100_ArrivalPlanMsRt.ColArCom02];			//ヘッダコメント2
				String GetArCom03		= (String)ArrivalPlanMsRt[0][T100_ArrivalPlanMsRt.ColArCom03];			//ヘッダコメント3
				String GetHdEntryDate	= (String)ArrivalPlanMsRt[0][T100_ArrivalPlanMsRt.ColHdEntryDate];	//ヘッダ登録日
				String GetHdUpdateDate	= (String)ArrivalPlanMsRt[0][T100_ArrivalPlanMsRt.ColHdUpdateDate];	//ヘッダ更新日
				String GetHdEntryUser	= (String)ArrivalPlanMsRt[0][T100_ArrivalPlanMsRt.ColHdEntryUser];	//ヘッダ登録者
				String GetHdUpdateUser	= (String)ArrivalPlanMsRt[0][T100_ArrivalPlanMsRt.ColHdUpdateUser];	//ヘッダ更新者
				int  GetFixFg			= (int)ArrivalPlanMsRt[0][T100_ArrivalPlanMsRt.ColFixFg];				//ヘッダ状況
				
				TB_ClWh.setSelectedIndex(B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.WhList[1]	,GetClWh,true));
				TB_ClCd.setSelectedIndex(B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.ClList[1]	,GetClCd,true));
				TB_FixFg.setSelectedIndex(B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.ArryvalFixFgList[1]	,""+GetFixFg,true));
				
				TB_ArrNo.setText(GetArrNo);
				TB_ClArrNo.setText(GetClArrNo);
				TB_PlanDate.setText(GetPlanDate);
				TB_HdActualDate.setText(GetHdActualDate);
				TB_ArCom01.setText(GetArCom01);
				TB_ArCom02.setText(GetArCom02);
				TB_ArCom03.setText(GetArCom03);
				TB_HdEntryDate.setText(GetHdEntryDate);
				TB_HdUpdateDate.setText(GetHdUpdateDate);
				TB_HdEntryUser.setText(GetHdEntryUser);
				TB_HdUpdateUser.setText(GetHdUpdateUser);
				
				if(0==GetFixFg) {
					TB_ClWh.setEnabled(false);
					TB_ClCd.setEnabled(false);
					TB_FixFg.setEnabled(false);
					TB_ArrNo.setEditable(false);
					TB_PlanDate.setEditable(true);
					TB_HdActualDate.setEditable(false);
					TB_ArCom01.setEditable(true);
					TB_ArCom02.setEditable(true);
					TB_ArCom03.setEditable(true);
					TB_HdEntryDate.setEditable(false);
					TB_HdUpdateDate.setEditable(false);
					TB_HdEntryUser.setEditable(false);
					TB_HdUpdateUser.setEditable(false);
					TB_HdActualDate.setEditable(false);
					
					TBMs_MsNo.setEditable(false);
					TBMs_ItemCd.setEditable(true);
					TBMs_ItemName.setEditable(false);
					TBMs_lot.setEditable(true);
					TBMs_ExpDate.setEditable(true);
					TBMs_PlanQty.setEditable(true);
					TBMs_ActualQty.setEditable(false);
					TBMs_Com01.setEditable(true);
					TBMs_Com02.setEditable(true);
					
					TBMs_ClItemCd.setEditable(false);
					TBMs_ActualDate.setEditable(false);
					TBMs_JanCd.setEditable(false);
					TBMs_ItemMdNo.setEditable(false);
					TBMs_EntryDate.setEditable(false);
					TBMs_UpdateDate.setEditable(false);
					TBMs_EntryUser.setEditable(false);
					TBMs_UpdateUser.setEditable(false);
					
					main_fm.add(CancelBtn);
				}else {
					TB_ClWh.setEnabled(false);
					TB_ClCd.setEnabled(false);
					TB_FixFg.setEnabled(false);
					TB_ArrNo.setEditable(false);
					TB_PlanDate.setEditable(false);
					TB_HdActualDate.setEditable(false);
					TB_ArCom01.setEditable(false);
					TB_ArCom02.setEditable(false);
					TB_ArCom03.setEditable(false);
					TB_HdEntryDate.setEditable(false);
					TB_HdUpdateDate.setEditable(false);
					TB_HdEntryUser.setEditable(false);
					TB_HdUpdateUser.setEditable(false);
					TB_HdActualDate.setEditable(false);
					
					TBMs_MsNo.setEditable(false);
					TBMs_ItemCd.setEditable(false);
					TBMs_ItemName.setEditable(false);
					TBMs_lot.setEditable(false);
					TBMs_ExpDate.setEditable(false);
					TBMs_PlanQty.setEditable(false);
					TBMs_ActualQty.setEditable(false);
					TBMs_Com01.setEditable(false);
					TBMs_Com02.setEditable(false);
					
					TBMs_ClItemCd.setEditable(false);
					TBMs_ActualDate.setEditable(false);
					TBMs_JanCd.setEditable(false);
					TBMs_ItemMdNo.setEditable(false);
					TBMs_EntryDate.setEditable(false);
					TBMs_UpdateDate.setEditable(false);
					TBMs_EntryUser.setEditable(false);
					TBMs_UpdateUser.setEditable(false);
					
					main_fm.remove(MsItemSearchBtn);
					main_fm.remove(MsRenewBtn);
					main_fm.remove(MsDeleteBtn);
					main_fm.remove(entry_btn);
				}
				
				for(int i=0;i<ArrivalPlanMsRt.length;i++) {
					Object[] SetOb = new Object[RtArrivalPlanMsRt.length+1];
					SetOb[0] = false;
					for(int i01=0;i01<RtArrivalPlanMsRt.length;i01++) {
						SetOb[1+i01] = ArrivalPlanMsRt[i][i01];
					}
					MainFmTableModel.addRow(SetOb);
				}
			}
		}

		
		
		RenewFg = true;
		main_fm.setVisible(true);
		
		//伝票複写ボタン押下時の挙動
		OrderCopy_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					//入荷予定番号・荷主入荷予定番号　実績情報によって変化する項目を未入荷状態にする
					
					TB_ArrNo.setText("");
					TB_ClArrNo.setText("");
					TB_FixFg.setSelectedIndex(B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.ArryvalFixFgList[1]	,"0",true));
					TB_HdActualDate.setText("");
					TB_HdEntryDate.setText("");
					TB_HdUpdateDate.setText("");
					TB_HdEntryUser.setText("");
					TB_HdUpdateUser.setText("");
					
					main_fm.remove(CancelBtn);
					main_fm.remove(OrderCopy_btn);
					main_fm.add(MsItemSearchBtn);
					main_fm.add(MsRenewBtn);
					main_fm.add(MsDeleteBtn);
					main_fm.add(entry_btn);
					
					int RowCount = MainFmTableModel.getRowCount();
					
					for(int i=0;i<RowCount;i++) {
						MainFmTableModel.setValueAt(false, i, 0);
						
						MainFmTableModel.setValueAt("0"	, i,	1+T100_ArrivalPlanMsRt.ColActualQty);			//実績数
						MainFmTableModel.setValueAt(""	, i,	1+T100_ArrivalPlanMsRt.ColActualDate);		//入荷日
						MainFmTableModel.setValueAt(""	, i,	1+T100_ArrivalPlanMsRt.ColEntryDate);			//登録日
						MainFmTableModel.setValueAt(""	, i,	1+T100_ArrivalPlanMsRt.ColUpdateDate);		//更新日
						MainFmTableModel.setValueAt(""	, i,	1+T100_ArrivalPlanMsRt.ColEntryUser);			//登録者
						MainFmTableModel.setValueAt(""	, i,	1+T100_ArrivalPlanMsRt.ColUpdateUser);		//更新者
					}
					TBMs_MsNo.setText("");
					TBMs_ItemCd.setText("");
					TBMs_ItemName.setText("");
					TBMs_lot.setText("");
					TBMs_ExpDate.setText("");
					TBMs_PlanQty.setText("");
					TBMs_ActualQty.setText("");
					TBMs_Com01.setText("");
					TBMs_Com02.setText("");
					
					TBMs_ClItemCd.setText("");
					TBMs_ActualDate.setText("");
					TBMs_JanCd.setText("");
					TBMs_ItemMdNo.setText("");
					TBMs_EntryDate.setText("");
					TBMs_UpdateDate.setText("");
					TBMs_EntryUser.setText("");
					TBMs_UpdateUser.setText("");
					
					main_fm.setVisible(false);
					main_fm.setVisible(true);
					
					RenewFg = true;
				}
			}
		});
		//チェックボックス操作時の挙動
		MainFmTableModel.addTableModelListener(new TableModelListener(){
			public void tableChanged(TableModelEvent e){
				if(RenewFg) {
					RenewFg = false;
					TBMs_MsNo.setText("");
					TBMs_ItemCd.setText("");
					TBMs_ItemName.setText("");
					TBMs_lot.setText("");
					TBMs_ExpDate.setText("");
					TBMs_PlanQty.setText("");
					TBMs_ActualQty.setText("");
					TBMs_Com01.setText("");
					TBMs_Com02.setText("");
					
					TBMs_ClItemCd.setText("");
					TBMs_ActualDate.setText("");
					TBMs_JanCd.setText("");
					TBMs_ItemMdNo.setText("");
					TBMs_EntryDate.setText("");
					TBMs_UpdateDate.setText("");
					TBMs_EntryUser.setText("");
					TBMs_UpdateUser.setText("");
					
					int row_count = MainFmTableModel.getRowCount();
					Boolean setBL=Boolean.valueOf(false);
					for(int i=0;i<row_count;i++){
						if(i!=e.getFirstRow()){
							MainFmTableModel.setValueAt(setBL, i, 0);
						}else {
							if((boolean)MainFmTableModel.getValueAt(i, 0)) {
								String GetMsNo			= ""+MainFmTableModel.getValueAt(i,1+T100_ArrivalPlanMsRt.ColMsNo);			//明細番号
								String GetItemCd		= ""+MainFmTableModel.getValueAt(i,1+T100_ArrivalPlanMsRt.ColItemCd);			//商品コード
								String GetClItemCd		= ""+MainFmTableModel.getValueAt(i,1+T100_ArrivalPlanMsRt.ColClItemCd);		//荷主商品コード
								String GetJanCd			= ""+MainFmTableModel.getValueAt(i,1+T100_ArrivalPlanMsRt.ColJanCd);			//JANCD（バラ）
								String GetItemMdNo		= ""+MainFmTableModel.getValueAt(i,1+T100_ArrivalPlanMsRt.ColItemMdNo);		//商品型番
								String GetItemName		= ""+MainFmTableModel.getValueAt(i,1+T100_ArrivalPlanMsRt.ColItemName);		//商品名
								String Getlot			= ""+MainFmTableModel.getValueAt(i,1+T100_ArrivalPlanMsRt.Collot);				//ロット
								String GetExpDate		= ""+MainFmTableModel.getValueAt(i,1+T100_ArrivalPlanMsRt.ColExpDate);			//消費期限
								String GetPlanQty		= ""+MainFmTableModel.getValueAt(i,1+T100_ArrivalPlanMsRt.ColPlanQty);			//予定数量
								String GetActualQty		= ""+MainFmTableModel.getValueAt(i,1+T100_ArrivalPlanMsRt.ColActualQty);		//実績数
								String GetActualDate	= ""+MainFmTableModel.getValueAt(i,1+T100_ArrivalPlanMsRt.ColActualDate);		//入荷日
								String GetCom01			= ""+MainFmTableModel.getValueAt(i,1+T100_ArrivalPlanMsRt.ColCom01);			//コメント1
								String GetCom02			= ""+MainFmTableModel.getValueAt(i,1+T100_ArrivalPlanMsRt.ColCom02);			//コメント2
								String GetEntryDate		= ""+MainFmTableModel.getValueAt(i,1+T100_ArrivalPlanMsRt.ColEntryDate);		//登録日
								String GetUpdateDate	= ""+MainFmTableModel.getValueAt(i,1+T100_ArrivalPlanMsRt.ColUpdateDate);		//更新日
								String GetEntryUser		= ""+MainFmTableModel.getValueAt(i,1+T100_ArrivalPlanMsRt.ColEntryUser);		//登録者
								String GetUpdateUser	= ""+MainFmTableModel.getValueAt(i,1+T100_ArrivalPlanMsRt.ColUpdateUser);		//更新者
								
								TBMs_MsNo.setText(GetMsNo);
								TBMs_ItemCd.setText(GetItemCd);
								TBMs_ItemName.setText(GetItemName);
								TBMs_lot.setText(Getlot);
								TBMs_ExpDate.setText(GetExpDate);
								TBMs_PlanQty.setText(GetPlanQty);
								TBMs_ActualQty.setText(GetActualQty);
								TBMs_Com01.setText(GetCom01);
								TBMs_Com02.setText(GetCom02);
								
								TBMs_ClItemCd.setText(GetClItemCd);
								TBMs_ActualDate.setText(GetActualDate);
								TBMs_JanCd.setText(GetJanCd);
								TBMs_ItemMdNo.setText(GetItemMdNo);
								TBMs_EntryDate.setText(GetEntryDate);
								TBMs_UpdateDate.setText(GetUpdateDate);
								TBMs_EntryUser.setText(GetEntryUser);
								TBMs_UpdateUser.setText(GetUpdateUser);
								
							}
						}
					}
					RenewFg = true;
				}
			}
		});
		
		//別伝票新規ボタン押下時の挙動
		NewOrder_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					String GetClWh= B100_DefaultVariable.WhList[1][TB_ClWh.getSelectedIndex()];						//ヘッダ担当倉庫
					String GetClCd= B100_DefaultVariable.ClList[1][TB_ClCd.getSelectedIndex()];						//ヘッダ荷主CD
					String GetArrNo = "";
					
					SetX=main_fm.getX();
					SetY=main_fm.getY();
					
					main_fm.setVisible(false);
					main_fm.dispose();
					
					ArrivalPlanRenewAndCreate(0,0,GetClWh,GetClCd,GetArrNo);
					
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
				WT100_ArrivalPlan_00_Search.ArrivalPlanSearch(0,0);
			}
		});
	}
	
	private static Object[][] ArrivalPlanMsRt(String TgtWhCd,String TgtClCd,String TgtArrNo){
		ArrayList<String> SearchClWh			= new ArrayList<String>();		//ヘッダ担当倉庫
		ArrayList<String> SearchClCd			= new ArrayList<String>();		//ヘッダ荷主CD
		ArrayList<String> SearchCLName01		= new ArrayList<String>();		//ヘッダ荷主名
		ArrayList<String> SearchClGpCD			= new ArrayList<String>();		//ヘッダ荷主グループCD
		ArrayList<String> SearchCLGpName01		= new ArrayList<String>();		//ヘッダ荷主グループ名1
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
		
		SearchClWh.add(TgtWhCd);
		SearchClCd.add(TgtClCd);
		SearchArrNo.add(TgtArrNo);
		
		Object[][] ArrivalPlanMsRt	= T100_ArrivalPlanMsRt.ArrivalPlanMsRt(
				SearchClWh,					//ヘッダ担当倉庫
				SearchClCd,					//ヘッダ荷主CD
				SearchCLName01,				//ヘッダ荷主名
				SearchClGpCD,				//ヘッダ荷主グループCD
				SearchCLGpName01,			//ヘッダ荷主グループ名1
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