import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class WM00061DeliveryMstRenewAndCreate{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	public static void DeliveryMstRenewAndCreate(int x,int y,String DECD,String DepartmentCd) {
		A00000Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		
		if(null==DECD) {DECD = "";}
		if(null==DepartmentCd) {DepartmentCd = "";}
		
		final JFrame main_fm = B00110FrameParts.FrameCreate(x,y,500,350,"Corgi00届先マスタ登録・更新","");
		JLabel userinfo = B00110FrameParts.UserInfo();
		JButton exit_btn = B00110FrameParts.ExitBtn();
		JButton entry_btn = B00110FrameParts.EntryBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		main_fm.add(entry_btn);
		
		/*
		
		*/
		
		main_fm.setVisible(true);
		RenewFg = true;
		
		//EXITボタン押下時の挙動
		exit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				WM00060DeliveryMstSearch.DeliveryMstSearch(0, 0);
			}
		});
	}
}