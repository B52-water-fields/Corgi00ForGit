import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

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
		
		
		TB_FixFg.setEnabled(false);
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
}