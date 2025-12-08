import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class W00030WorkMain{
	static int SetX;
	static int SetY;
	public static void WorkMain(int x,int y) {
		A00000Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}

		final JFrame main_fm = B00110FrameParts.FrameCreate(x,y,700,750,"Corgi00業務メニュー","");
		
		JLabel userinfo = B00110FrameParts.UserInfo();
		JButton exit_btn = B00110FrameParts.ExitBtn();
		


		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		main_fm.setVisible(true);

		//EXITボタン押下時の挙動
		exit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				W00010MainMenu.MainMenu(0, 0);
			}
		});
	}
}