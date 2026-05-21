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

public class WT0001003ArrivalPlanSomeEntry{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	public static void ArrivalPlanSomeEntry(int x,int y) {
		A00000Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		
		String Nextday = B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.ndate_after(B00050ToolsDateTimeControl.dtm()[1],1))[0];
		
		final JFrame main_fm = B00110FrameParts.FrameCreate(x,y,900,750,"Corgi00入荷予定検索","");
		JLabel userinfo = B00110FrameParts.UserInfo();
		JButton exit_btn = B00110FrameParts.ExitBtn();
		JButton entry_btn = B00110FrameParts.EntryBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		main_fm.add(entry_btn);
		
		JLabel LB_PlanDate 	= B00110FrameParts.JLabelSet(  0, 50,100,20,"入荷予定日:"	,11,1);
		JLabel LB_SpCd 		= B00110FrameParts.JLabelSet(210, 50, 90,20,"仕入先CD:"	,11,1);
		JLabel LB_ClWh 		= B00110FrameParts.JLabelSet(560, 50, 90,20,"担当倉庫:"	,11,1);
		JLabel LB_ClCd 		= B00110FrameParts.JLabelSet(560, 75, 90,20,"荷主CD:"		,11,1);
		
		final JFormattedTextField TB_PlanDate		= B00110FrameParts.JFormattedTextFieldSet(	100,50, 70,20,Nextday	,11,0,"YYYY/MM/DD");		//入荷予定日
		//予定日進む戻るボタン
		JButton PlanDateAfterBtn	= B00110FrameParts.BtnSet(										170,50, 40,10,"▲",6);
		JButton PlanDateBeforeBtn	= B00110FrameParts.BtnSet(										170,60, 40,10,"▼",6);
		
		final JComboBox TB_SpCd	= B00110FrameParts.JComboBoxSet(	300, 50,250,20,B00100DefaultVariable.SupplierList[0],11);		//仕入先
		final JComboBox TB_ClWh	= B00110FrameParts.JComboBoxSet(	650, 50,200,20,B00100DefaultVariable.WhList[0],11);			//担当倉庫
		final JComboBox TB_ClCd	= B00110FrameParts.JComboBoxSet(	650, 75,200,20,B00100DefaultVariable.ClList[0],11);			//荷主CD
		
		TB_ClWh.setSelectedIndex(GetSelectIndex(B00100DefaultVariable.WhList[1],A00000Main.ClWh));
		TB_ClCd.setSelectedIndex(GetSelectIndex(B00100DefaultVariable.ClList[1],A00000Main.ClCd));
		
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
		
		JLabel LB_ClArrNo	= B00110FrameParts.JLabelSet(  60,100,150,20,"荷主予定番号"		,11,2);
		JLabel LB_ClItemCd	= B00110FrameParts.JLabelSet( 220,100,150,20,"荷主商品コード"		,11,2);
		JLabel LB_lot		= B00110FrameParts.JLabelSet( 380,100,150,20,"ロット"				,11,2);
		JLabel LB_ExpDate	= B00110FrameParts.JLabelSet( 540,100,150,20,"消費期限"			,11,2);
		JLabel LB_PlanQty	= B00110FrameParts.JLabelSet( 700,100,150,20,"予定数量"			,11,2);
		
		final JTextArea TB_ClArrNo	= B00110FrameParts.JTextAreaSet(11);
		final JTextArea TB_ClItemCd	= B00110FrameParts.JTextAreaSet(11);
		final JTextArea TB_lot		= B00110FrameParts.JTextAreaSet(11);
		final JTextArea TB_ExpDate	= B00110FrameParts.JTextAreaSet(11);
		final JTextArea TB_PlanQty	= B00110FrameParts.JTextAreaSet(11);
		
		//スクロール用設定
		JScrollPane SPClArrNo 	= B00110FrameParts.JScrollPaneSet( 60,125,150,500,TB_ClArrNo);
		JScrollPane SPClItemCd 	= B00110FrameParts.JScrollPaneSet(220,125,150,500,TB_ClItemCd);
		JScrollPane SPlot 		= B00110FrameParts.JScrollPaneSet(380,125,150,500,TB_lot);
		JScrollPane SPExpDate 	= B00110FrameParts.JScrollPaneSet(540,125,150,500,TB_ExpDate);
		JScrollPane SPPlanQty 	= B00110FrameParts.JScrollPaneSet(700,125,150,500,TB_PlanQty);
		
		main_fm.add(LB_ClArrNo);
		main_fm.add(LB_ClItemCd);
		main_fm.add(LB_lot);
		main_fm.add(LB_ExpDate);
		main_fm.add(LB_PlanQty);
		
		main_fm.add(SPClArrNo);
		main_fm.add(SPClItemCd);
		main_fm.add(SPlot);
		main_fm.add(SPExpDate);
		main_fm.add(SPPlanQty);
		
		
		//予定日進む戻るボタン押下事の挙動
		PlanDateAfterBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				String NowDate = B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[0];
				String GetDate = TB_PlanDate.getText();
				String SetDate = NowDate;
				if(null==GetDate||"".equals(GetDate)) {
				}else {
					Timestamp WT	= B00050ToolsDateTimeControl.dtmTimestamp2(GetDate)[0];
					WT				= B00050ToolsDateTimeControl.ndate_after(WT, 1);
					SetDate			= B00050ToolsDateTimeControl.dtmString2(WT)[0];
				}
				TB_PlanDate.setText(SetDate);
			}
		});
		PlanDateBeforeBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				String NowDate = B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[0];
				String GetDate = TB_PlanDate.getText();
				String SetDate = NowDate;
				if(null==GetDate||"".equals(GetDate)) {
				}else {
					Timestamp WT	= B00050ToolsDateTimeControl.dtmTimestamp2(GetDate)[0];
					WT				= B00050ToolsDateTimeControl.ndate_before(WT, 1);
					SetDate			= B00050ToolsDateTimeControl.dtmString2(WT)[0];
				}
				TB_PlanDate.setText(SetDate);
			}
		});
		
		final JTextArea TB_ClPlanNo = B00110FrameParts.JTextAreaSet(11);
		
		RenewFg = true;
		main_fm.setVisible(true);
		
		//登録ボタン押下時の挙動
		entry_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				String GetPlanDate	= TB_PlanDate.getText();		//入荷予定日
				String GetSpCd		= B00100DefaultVariable.SupplierList[1][TB_SpCd.getSelectedIndex()];	//仕入先
				String GetClWh		= B00100DefaultVariable.WhList[1][TB_ClWh.getSelectedIndex()];			//担当倉庫
				String GetClCd		= B00100DefaultVariable.ClList[1][TB_ClCd.getSelectedIndex()];			//荷主CD
				
				
				String GetClArrNo	= TB_ClArrNo.getText();
				String GetClItemCd	= TB_ClItemCd.getText();
				String Getlot		= TB_lot.getText();
				String GetExpDate	= TB_ExpDate.getText();
				String GetPlanQty	= TB_PlanQty.getText();
				
				String[] ArrayGetClArrNo 	= GetClArrNo.split("\n");
				String[] ArrayGetClItemCd	= GetClItemCd.split("\n");
				String[] ArrayGetlot		= Getlot.split("\n");
				String[] ArrayGetExpDate	= GetExpDate.split("\n");
				String[] ArrayGetPlanQty	= GetPlanQty.split("\n");
				
				int RowCount = ArrayGetClArrNo.length;
				if(RowCount<ArrayGetClItemCd.length) {RowCount = ArrayGetClItemCd.length;}
				if(RowCount<ArrayGetlot.length) {RowCount = ArrayGetlot.length;}
				if(RowCount<ArrayGetExpDate.length) {RowCount = ArrayGetExpDate.length;}
				if(RowCount<ArrayGetPlanQty.length) {RowCount = ArrayGetPlanQty.length;}
				
				String[][] SetData = new String[RowCount][WT0001004ArrivalPlanArrayEntry.RtArrivalPlanArrayEntry().length];
				
				for(int i=0;i<RowCount;i++) {
					SetData[i][WT0001004ArrivalPlanArrayEntry.ColClWh]		= GetClWh;		//担当倉庫
					SetData[i][WT0001004ArrivalPlanArrayEntry.ColClCd]		= GetClCd;		//荷主CD
					SetData[i][WT0001004ArrivalPlanArrayEntry.ColClArrNo]	= "";			//荷主予定番号
					SetData[i][WT0001004ArrivalPlanArrayEntry.ColPlanDate]	= GetPlanDate;	//入荷予定日
					SetData[i][WT0001004ArrivalPlanArrayEntry.ColClItemCd]	= "";			//荷主商品CD
					SetData[i][WT0001004ArrivalPlanArrayEntry.ColPlanQty]	= "";			//数量
					SetData[i][WT0001004ArrivalPlanArrayEntry.ColSPCd]		= GetSpCd;		//仕入先CD
					SetData[i][WT0001004ArrivalPlanArrayEntry.ColLot]		= "";			//ロット
					SetData[i][WT0001004ArrivalPlanArrayEntry.ColExpDate]	= "";			//賞味期限
					
					if(i<ArrayGetClArrNo.length) {
						SetData[i][WT0001004ArrivalPlanArrayEntry.ColClArrNo]	= ArrayGetClArrNo[i];			//荷主予定番号
					}
					if(i<ArrayGetClItemCd.length) {
						SetData[i][WT0001004ArrivalPlanArrayEntry.ColClItemCd]	= ArrayGetClItemCd[i];			//荷主商品CD
					}
					if(i<ArrayGetlot.length) {
						SetData[i][WT0001004ArrivalPlanArrayEntry.ColLot]		= ArrayGetlot[i];				//ロット
					}
					if(i<ArrayGetExpDate.length) {
						SetData[i][WT0001004ArrivalPlanArrayEntry.ColExpDate]	= ArrayGetExpDate[i];			//賞味期限
					}
					if(i<ArrayGetPlanQty.length) {
						SetData[i][WT0001004ArrivalPlanArrayEntry.ColPlanQty]	= ArrayGetPlanQty[i];			//数量
					}
				}
				if(0<RowCount) {
					SetX=main_fm.getX();
					SetY=main_fm.getY();

					main_fm.setVisible(false);
					main_fm.dispose();
					WT0001004ArrivalPlanArrayEntry.ArrivalPlanArrayEntry(0,0,SetData);
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
				WT0001000ArrivalPlanSearch.ArrivalPlanSearch(0,0);
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