import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

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
		
		final JFrame main_fm = B100_FrameParts.FrameCreate(x,y,1200,800,"Corgi00入荷予定登録・修正","NK");
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
		JLabel LB_ActualDate	= B100_FrameParts.JLabelSet(  0,200,130,20,"入荷実績日:",		11,1);
		
		JLabel LB_FixFg			= B100_FrameParts.JLabelSet(240,125,100,20,"状況:",			11,1);
		JLabel LB_ArCom01		= B100_FrameParts.JLabelSet(240,150,100,20,"コメント1:",		11,1);
		JLabel LB_ArCom02		= B100_FrameParts.JLabelSet(240,175,100,20,"コメント2:",		11,1);
		JLabel LB_ArCom03		= B100_FrameParts.JLabelSet(240,200,100,20,"コメント3:",		11,1);
		
		JLabel LB_EntryDate		= B100_FrameParts.JLabelSet(540, 40,100,20,"登録日:",			11,1);
		JLabel LB_EntryUser		= B100_FrameParts.JLabelSet(540, 40,100,20,"登録者:",			11,1);
		JLabel LB_UpdateDate	= B100_FrameParts.JLabelSet(540, 40,100,20,"更新日:",			11,1);
		JLabel LB_UpdateUser	= B100_FrameParts.JLabelSet(540, 40,100,20,"更新者:",			11,1);
		


		
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