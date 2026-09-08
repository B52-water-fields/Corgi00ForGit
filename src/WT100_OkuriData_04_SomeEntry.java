import java.awt.event.ActionEvent;
import java.sql.Timestamp;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class WT100_OkuriData_04_SomeEntry{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	public static void OkuriDataSomeEntry(int x,int y) {
		A00000_Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		String Nextday = B100_DateTimeControl.dtmString2(B100_DateTimeControl.ndate_after(B100_DateTimeControl.dtm()[1],1))[0];
		
		final JFrame main_fm = B100_FrameParts.FrameCreate(x,y,1000,750,"Corgi00出荷予定一括登録　WT100_OkuriData_04_SomeEntry","SP");
		JLabel userinfo = B100_FrameParts.UserInfo();
		JButton exit_btn = B100_FrameParts.ExitBtn();
		JButton entry_btn = B100_FrameParts.EntryBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		main_fm.add(entry_btn);
		
		JLabel LB_PlanDate 	= B100_FrameParts.JLabelSet(  0, 40,100,20,"出荷予定日:"	,11,1);
		JLabel LB_ClWh 		= B100_FrameParts.JLabelSet(560, 40, 90,20,"担当倉庫:"		,11,1);
		JLabel LB_ClCd 		= B100_FrameParts.JLabelSet(560, 65, 90,20,"荷主CD:"		,11,1);
		
		final JFormattedTextField TB_PlanDate		= B100_FrameParts.JFormattedTextFieldSet(	100,40, 70,20,Nextday	,11,0,"YYYY/MM/DD");		//出荷予定日
		//予定日進む戻るボタン
		JButton PlanDateAfterBtn	= B100_FrameParts.BtnSet(										170,40, 40,10,"▲",6);
		JButton PlanDateBeforeBtn	= B100_FrameParts.BtnSet(										170,50, 40,10,"▼",6);
		
		final JComboBox TB_ClWh	= B100_FrameParts.JComboBoxSet(	650, 40,300,20,B100_DefaultVariable.WhList[0],11);			//担当倉庫
		final JComboBox TB_ClCd	= B100_FrameParts.JComboBoxSet(	650, 65,300,20,B100_DefaultVariable.ClList[0],11);			//荷主CD
		
		TB_ClWh.setSelectedIndex(B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.WhList[1],A00000_Main.ClWh,true));
		TB_ClCd.setSelectedIndex(B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.ClList[1],A00000_Main.ClCd,true));
		
		TB_ClWh.setEnabled(false);
		TB_ClCd.setEnabled(false);
		
		main_fm.add(LB_PlanDate);
		main_fm.add(LB_ClWh);
		main_fm.add(LB_ClCd);
		
		main_fm.add(TB_PlanDate);
		main_fm.add(PlanDateAfterBtn);
		main_fm.add(PlanDateBeforeBtn);
		main_fm.add(TB_ClWh);
		main_fm.add(TB_ClCd);
		
		//予定日進む戻るボタン押下事の挙動
		PlanDateAfterBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				String NowDate = B100_DateTimeControl.dtmString2(B100_DateTimeControl.dtm()[1])[0];
				String GetDate = TB_PlanDate.getText();
				String SetDate = NowDate;
				if(null==GetDate||"".equals(GetDate)) {
				}else {
					Timestamp WT	= B100_DateTimeControl.dtmTimestamp2(GetDate)[0];
					WT				= B100_DateTimeControl.ndate_after(WT, 1);
					SetDate			= B100_DateTimeControl.dtmString2(WT)[0];
				}
				TB_PlanDate.setText(SetDate);
			}
		});
		PlanDateBeforeBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				String NowDate = B100_DateTimeControl.dtmString2(B100_DateTimeControl.dtm()[1])[0];
				String GetDate = TB_PlanDate.getText();
				String SetDate = NowDate;
				if(null==GetDate||"".equals(GetDate)) {
				}else {
					Timestamp WT	= B100_DateTimeControl.dtmTimestamp2(GetDate)[0];
					WT				= B100_DateTimeControl.ndate_before(WT, 1);
					SetDate			= B100_DateTimeControl.dtmString2(WT)[0];
				}
				TB_PlanDate.setText(SetDate);
			}
		});
		
		JLabel LB_Msg		= B100_FrameParts.JLabelSet(  30,65,500,20,"バラ以外の荷主商品コードを指定する場合、荷姿数量で指定してください"		,11,0);
		JLabel LB_Msg2		= B100_FrameParts.JLabelSet(  30,85,500,20,"EX)荷主商品CD:A001-ctが、商品:CDA001　入数:6　なら予定数10は60に換算されます"		,11,0);
		LB_Msg.setForeground(B100_FrameParts.SelectColer("Red"));
		main_fm.add(LB_Msg);
		LB_Msg2.setForeground(B100_FrameParts.SelectColer("Red"));
		main_fm.add(LB_Msg2);
		
		JLabel LB_ClDeliNo	= B100_FrameParts.JLabelSet(  30,120,100,20,"荷主予定番号"		,11,2);
		JLabel LB_ClDeCd	= B100_FrameParts.JLabelSet( 150,120,100,20,"荷主届先"			,11,2);
		JLabel LB_ClItemCd	= B100_FrameParts.JLabelSet( 270,120,100,20,"荷主商品コード"	,11,2);
		JLabel LB_lot		= B100_FrameParts.JLabelSet( 390,120,100,20,"ロット"			,11,2);
		JLabel LB_ExpDate	= B100_FrameParts.JLabelSet( 510,120,100,20,"消費期限"			,11,2);
		JLabel LB_PlanQty	= B100_FrameParts.JLabelSet( 630,120,100,20,"予定数量"			,11,2);
		JLabel LB_HdCom		= B100_FrameParts.JLabelSet( 750,120,100,20,"ヘッダコメント"	,11,2);
		JLabel LB_MsCom		= B100_FrameParts.JLabelSet( 870,120,100,20,"明細コメント"		,11,2);
		
		final JTextArea TB_ClDeliNo	= B100_FrameParts.JTextAreaSet(11);
		final JTextArea TB_ClDeCd	= B100_FrameParts.JTextAreaSet(11);
		final JTextArea TB_ClItemCd	= B100_FrameParts.JTextAreaSet(11);
		final JTextArea TB_lot		= B100_FrameParts.JTextAreaSet(11);
		final JTextArea TB_ExpDate	= B100_FrameParts.JTextAreaSet(11);
		final JTextArea TB_PlanQty	= B100_FrameParts.JTextAreaSet(11);
		final JTextArea TB_HdCom	= B100_FrameParts.JTextAreaSet(11);
		final JTextArea TB_MsCom	= B100_FrameParts.JTextAreaSet(11);
		
		JScrollPane SPClDeliNo	= B100_FrameParts.JScrollPaneSet(  30,140,100,500,TB_ClDeliNo);
		JScrollPane SPClDeCd	= B100_FrameParts.JScrollPaneSet( 150,140,100,500,TB_ClDeCd);
		JScrollPane SPClItemCd	= B100_FrameParts.JScrollPaneSet( 270,140,100,500,TB_ClItemCd);
		JScrollPane SPlot		= B100_FrameParts.JScrollPaneSet( 390,140,100,500,TB_lot);
		JScrollPane SPExpDate	= B100_FrameParts.JScrollPaneSet( 510,140,100,500,TB_ExpDate);
		JScrollPane SPPlanQty	= B100_FrameParts.JScrollPaneSet( 630,140,100,500,TB_PlanQty);
		JScrollPane SPHdCom		= B100_FrameParts.JScrollPaneSet( 750,140,100,500,TB_HdCom);
		JScrollPane SPMsCom		= B100_FrameParts.JScrollPaneSet( 870,140,100,500,TB_MsCom);
		
		TB_ClDeCd.setBackground(B100_FrameParts.SelectColer("Entry"));
		TB_ClItemCd.setBackground(B100_FrameParts.SelectColer("Entry"));
		TB_PlanQty.setBackground(B100_FrameParts.SelectColer("Entry"));
		
		main_fm.add(LB_ClDeliNo);
		main_fm.add(LB_ClDeCd);
		main_fm.add(LB_ClItemCd);
		main_fm.add(LB_lot);
		main_fm.add(LB_ExpDate);
		main_fm.add(LB_PlanQty);
		main_fm.add(LB_HdCom);
		main_fm.add(LB_MsCom);
		
		main_fm.add(SPClDeliNo);
		main_fm.add(SPClDeCd);
		main_fm.add(SPClItemCd);
		main_fm.add(SPlot);
		main_fm.add(SPExpDate);
		main_fm.add(SPPlanQty);
		main_fm.add(SPHdCom);
		main_fm.add(SPMsCom);
		
		RenewFg = true;
		main_fm.setVisible(true);
		//登録ボタン押下時の挙動
		entry_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				String GetPlanDate	= TB_PlanDate.getText();		//出荷予定日
				String GetClWh		= B100_DefaultVariable.WhList[1][TB_ClWh.getSelectedIndex()];			//担当倉庫
				String GetClCd		= B100_DefaultVariable.ClList[1][TB_ClCd.getSelectedIndex()];			//荷主CD
				
				String GetClDeliNo	= TB_ClDeliNo.getText();
				String GetClDeCd	= TB_ClDeCd.getText();
				String GetClItemCd	= TB_ClItemCd.getText();
				String Getlot		= TB_lot.getText();
				String GetExpDate	= TB_ExpDate.getText();
				String GetPlanQty	= TB_PlanQty.getText();
				String GetHdCom		= TB_HdCom.getText();
				String GetMsCom		= TB_MsCom.getText();
				
				String[] ArrayClDeliNo	= GetClDeliNo.split("\n");
				String[] ArrayClDeCd	= GetClDeCd.split("\n");
				String[] ArrayClItemCd	= GetClItemCd.split("\n");
				String[] Arraylot		= Getlot.split("\n");
				String[] ArrayExpDate	= GetExpDate.split("\n");
				String[] ArrayPlanQty	= GetPlanQty.split("\n");
				String[] ArrayHdCom		= GetHdCom.split("\n");
				String[] ArrayMsCom		= GetMsCom.split("\n");
				
				int RowCount = ArrayClDeliNo.length;
				if(RowCount<ArrayClDeCd.length		) {RowCount = ArrayClDeCd.length;}
				if(RowCount<ArrayClItemCd.length	) {RowCount = ArrayClItemCd.length;}
				if(RowCount<Arraylot.length			) {RowCount = Arraylot.length;}
				if(RowCount<ArrayExpDate.length		) {RowCount = ArrayExpDate.length;}
				if(RowCount<ArrayPlanQty.length		) {RowCount = ArrayPlanQty.length;}
				if(RowCount<ArrayHdCom.length		) {RowCount = ArrayHdCom.length;}
				if(RowCount<ArrayMsCom.length		) {RowCount = ArrayMsCom.length;}
				
				String[][] SetData = new String[RowCount][WT100_ArrivalPlan_05_ArrayEntrySourceDataView.RtArrivalPlanArrayEntrySourceDataView().length];
				/*
				for(int i=0;i<RowCount;i++) {
					SetData[i][WT100_ArrivalPlan_05_ArrayEntrySourceDataView.ColClWh]		= GetClWh;		//担当倉庫
					SetData[i][WT100_ArrivalPlan_05_ArrayEntrySourceDataView.ColClCd]		= GetClCd;		//荷主CD
					SetData[i][WT100_ArrivalPlan_05_ArrayEntrySourceDataView.ColClArrNo]	= "";			//荷主予定番号
					SetData[i][WT100_ArrivalPlan_05_ArrayEntrySourceDataView.ColPlanDate]	= GetPlanDate;	//入荷予定日
					SetData[i][WT100_ArrivalPlan_05_ArrayEntrySourceDataView.ColClItemCd]	= "";			//荷主商品CD
					SetData[i][WT100_ArrivalPlan_05_ArrayEntrySourceDataView.ColPlanQty]	= "";			//数量
					SetData[i][WT100_ArrivalPlan_05_ArrayEntrySourceDataView.ColSPCd]		= GetSpCd;		//仕入先CD
					SetData[i][WT100_ArrivalPlan_05_ArrayEntrySourceDataView.ColLot]		= "";			//ロット
					SetData[i][WT100_ArrivalPlan_05_ArrayEntrySourceDataView.ColExpDate]	= "";			//賞味期限
					SetData[i][WT100_ArrivalPlan_05_ArrayEntrySourceDataView.ColHdCom01]	= "";			//ヘッダコメント01
					SetData[i][WT100_ArrivalPlan_05_ArrayEntrySourceDataView.ColMsCom01]	= "";			//明細コメント01
					
					if(i<ArrayGetClArrNo.length) {
						SetData[i][WT100_ArrivalPlan_05_ArrayEntrySourceDataView.ColClArrNo]	= ArrayGetClArrNo[i];			//荷主予定番号
					}
					if(i<ArrayGetClItemCd.length) {
						SetData[i][WT100_ArrivalPlan_05_ArrayEntrySourceDataView.ColClItemCd]	= ArrayGetClItemCd[i];			//荷主商品CD
					}
					if(i<ArrayGetlot.length) {
						SetData[i][WT100_ArrivalPlan_05_ArrayEntrySourceDataView.ColLot]		= ArrayGetlot[i];				//ロット
					}
					if(i<ArrayGetExpDate.length) {
						SetData[i][WT100_ArrivalPlan_05_ArrayEntrySourceDataView.ColExpDate]	= ArrayGetExpDate[i];			//賞味期限
					}
					if(i<ArrayGetPlanQty.length) {
						SetData[i][WT100_ArrivalPlan_05_ArrayEntrySourceDataView.ColPlanQty]	= ArrayGetPlanQty[i];			//数量
					}
					if(i<ArrayGetHdCom.length) {
						SetData[i][WT100_ArrivalPlan_05_ArrayEntrySourceDataView.ColHdCom01]	= ArrayGetHdCom[i];				//ヘッダコメント
					}
					if(i<ArrayGetMsCom.length) {
						SetData[i][WT100_ArrivalPlan_05_ArrayEntrySourceDataView.ColMsCom01]	= ArrayGetMsCom[i];				//明細コメント
					}
				}
				if(0<RowCount) {
					SetX=main_fm.getX();
					SetY=main_fm.getY();

					main_fm.setVisible(false);
					main_fm.dispose();
					WT100_ArrivalPlan_05_ArrayEntrySourceDataView.ArrivalPlanArrayEntrySourceDataView(SetX+10,SetY+10,SetData);
				}
				*/
			}
		});
		
		//EXITボタン押下時の挙動
		exit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				WT100_OkuriHd_00_Search.OkuriHdSearch(0,0);
			}
		});
	}
}