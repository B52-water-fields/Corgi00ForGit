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

public class WT100_ArrivalPlan_04_SomeEntry{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	public static void ArrivalPlanSomeEntry(int x,int y) {
		A00000_Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		
		String Nextday = B100_DateTimeControl.dtmString2(B100_DateTimeControl.ndate_after(B100_DateTimeControl.dtm()[1],1))[0];
		
		final JFrame main_fm = B100_FrameParts.FrameCreate(x,y,900,750,"Corgi00入荷予定検索","NK");
		JLabel userinfo = B100_FrameParts.UserInfo();
		JButton exit_btn = B100_FrameParts.ExitBtn();
		JButton entry_btn = B100_FrameParts.EntryBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		main_fm.add(entry_btn);
		
		JLabel LB_PlanDate 	= B100_FrameParts.JLabelSet(  0, 50,100,20,"入荷予定日:"	,11,1);
		JLabel LB_SpCd 		= B100_FrameParts.JLabelSet(210, 50, 90,20,"仕入先CD:"	,11,1);
		JLabel LB_ClWh 		= B100_FrameParts.JLabelSet(560, 50, 90,20,"担当倉庫:"	,11,1);
		JLabel LB_ClCd 		= B100_FrameParts.JLabelSet(560, 75, 90,20,"荷主CD:"		,11,1);
		
		final JFormattedTextField TB_PlanDate		= B100_FrameParts.JFormattedTextFieldSet(	100,50, 70,20,Nextday	,11,0,"YYYY/MM/DD");		//入荷予定日
		//予定日進む戻るボタン
		JButton PlanDateAfterBtn	= B100_FrameParts.BtnSet(										170,50, 40,10,"▲",6);
		JButton PlanDateBeforeBtn	= B100_FrameParts.BtnSet(										170,60, 40,10,"▼",6);
		
		final JComboBox TB_SpCd	= B100_FrameParts.JComboBoxSet(	300, 50,250,20,B100_DefaultVariable.SupplierList[0],11);		//仕入先
		final JComboBox TB_ClWh	= B100_FrameParts.JComboBoxSet(	650, 50,200,20,B100_DefaultVariable.WhList[0],11);			//担当倉庫
		final JComboBox TB_ClCd	= B100_FrameParts.JComboBoxSet(	650, 75,200,20,B100_DefaultVariable.ClList[0],11);			//荷主CD
		
		TB_ClWh.setSelectedIndex(GetSelectIndex(B100_DefaultVariable.WhList[1],A00000_Main.ClWh));
		TB_ClCd.setSelectedIndex(GetSelectIndex(B100_DefaultVariable.ClList[1],A00000_Main.ClCd));
		
		TB_ClWh.setEnabled(false);
		TB_ClCd.setEnabled(false);
		
		main_fm.add(LB_PlanDate);
		main_fm.add(LB_ClWh);
		main_fm.add(LB_ClCd);
		main_fm.add(LB_SpCd);
		
		main_fm.add(TB_PlanDate);
		main_fm.add(PlanDateAfterBtn);
		main_fm.add(PlanDateBeforeBtn);
		main_fm.add(TB_ClWh);
		main_fm.add(TB_ClCd);
		main_fm.add(TB_SpCd);
		
		JLabel LB_ClArrNo	= B100_FrameParts.JLabelSet(  30,100,100,20,"荷主予定番号"		,11,2);
		JLabel LB_ClItemCd	= B100_FrameParts.JLabelSet( 150,100,100,20,"荷主商品コード"		,11,2);
		JLabel LB_lot		= B100_FrameParts.JLabelSet( 270,100,100,20,"ロット"				,11,2);
		JLabel LB_ExpDate	= B100_FrameParts.JLabelSet( 390,100,100,20,"消費期限"			,11,2);
		JLabel LB_PlanQty	= B100_FrameParts.JLabelSet( 510,100,100,20,"予定数量"			,11,2);
		JLabel LB_HdCom		= B100_FrameParts.JLabelSet( 630,100,100,20,"ヘッダコメント"		,11,2);
		JLabel LB_MsCom		= B100_FrameParts.JLabelSet( 750,100,100,20,"明細コメント"		,11,2);
		
		final JTextArea TB_ClArrNo	= B100_FrameParts.JTextAreaSet(11);
		final JTextArea TB_ClItemCd	= B100_FrameParts.JTextAreaSet(11);
		final JTextArea TB_lot		= B100_FrameParts.JTextAreaSet(11);
		final JTextArea TB_ExpDate	= B100_FrameParts.JTextAreaSet(11);
		final JTextArea TB_PlanQty	= B100_FrameParts.JTextAreaSet(11);
		final JTextArea TB_HdCom	= B100_FrameParts.JTextAreaSet(11);
		final JTextArea TB_MsCom	= B100_FrameParts.JTextAreaSet(11);
		
		//スクロール用設定
		JScrollPane SPClArrNo 	= B100_FrameParts.JScrollPaneSet( 30,125,100,500,TB_ClArrNo);
		JScrollPane SPClItemCd 	= B100_FrameParts.JScrollPaneSet(150,125,100,500,TB_ClItemCd);
		JScrollPane SPlot 		= B100_FrameParts.JScrollPaneSet(270,125,100,500,TB_lot);
		JScrollPane SPExpDate 	= B100_FrameParts.JScrollPaneSet(390,125,100,500,TB_ExpDate);
		JScrollPane SPPlanQty 	= B100_FrameParts.JScrollPaneSet(510,125,100,500,TB_PlanQty);
		JScrollPane SPHdCom 	= B100_FrameParts.JScrollPaneSet(630,125,100,500,TB_HdCom);
		JScrollPane SPMsCom 	= B100_FrameParts.JScrollPaneSet(750,125,100,500,TB_MsCom);
		
		main_fm.add(LB_ClArrNo);
		main_fm.add(LB_ClItemCd);
		main_fm.add(LB_lot);
		main_fm.add(LB_ExpDate);
		main_fm.add(LB_PlanQty);
		main_fm.add(LB_HdCom);
		main_fm.add(LB_MsCom);
		
		main_fm.add(SPClArrNo);
		main_fm.add(SPClItemCd);
		main_fm.add(SPlot);
		main_fm.add(SPExpDate);
		main_fm.add(SPPlanQty);
		main_fm.add(SPHdCom);
		main_fm.add(SPMsCom);
		
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
		
		final JTextArea TB_ClPlanNo = B100_FrameParts.JTextAreaSet(11);
		
		RenewFg = true;
		main_fm.setVisible(true);
		
		//登録ボタン押下時の挙動
		entry_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				String GetPlanDate	= TB_PlanDate.getText();		//入荷予定日
				String GetSpCd		= B100_DefaultVariable.SupplierList[1][TB_SpCd.getSelectedIndex()];	//仕入先
				String GetClWh		= B100_DefaultVariable.WhList[1][TB_ClWh.getSelectedIndex()];			//担当倉庫
				String GetClCd		= B100_DefaultVariable.ClList[1][TB_ClCd.getSelectedIndex()];			//荷主CD
				
				
				String GetClArrNo	= TB_ClArrNo.getText();
				String GetClItemCd	= TB_ClItemCd.getText();
				String Getlot		= TB_lot.getText();
				String GetExpDate	= TB_ExpDate.getText();
				String GetPlanQty	= TB_PlanQty.getText();
				String GetHdCom		= TB_HdCom.getText();
				String GetMsCom		= TB_MsCom.getText();
				
				String[] ArrayGetClArrNo 	= GetClArrNo.split("\n");
				String[] ArrayGetClItemCd	= GetClItemCd.split("\n");
				String[] ArrayGetlot		= Getlot.split("\n");
				String[] ArrayGetExpDate	= GetExpDate.split("\n");
				String[] ArrayGetPlanQty	= GetPlanQty.split("\n");
				String[] ArrayGetHdCom		= GetHdCom.split("\n");
				String[] ArrayGetMsCom		= GetMsCom.split("\n");
				
				int RowCount = ArrayGetClArrNo.length;
				if(RowCount<ArrayGetClItemCd.length	) {RowCount = ArrayGetClItemCd.length;}
				if(RowCount<ArrayGetlot.length		) {RowCount = ArrayGetlot.length;}
				if(RowCount<ArrayGetExpDate.length	) {RowCount = ArrayGetExpDate.length;}
				if(RowCount<ArrayGetPlanQty.length	) {RowCount = ArrayGetPlanQty.length;}
				if(RowCount<ArrayGetHdCom.length	) {RowCount = ArrayGetHdCom.length;}
				if(RowCount<ArrayGetMsCom.length	) {RowCount = ArrayGetMsCom.length;}
				
				String[][] SetData = new String[RowCount][WT100_ArrivalPlan_05_ArrayEntrySourceDataView.RtArrivalPlanArrayEntrySourceDataView().length];
				
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
	
	private static int GetSelectIndex(String[] SelectList,String TgtData ) {
		int rt = 0;
		for(int i=0;i<SelectList.length;i++) {
			if(TgtData.equals(SelectList[i])) {
				rt	= i;
				i	= SelectList.length+1;
			}
		}
		return rt;
	}
	
	
}