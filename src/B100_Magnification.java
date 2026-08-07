import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class B100_Magnification{
	static int SetX;
	static int SetY;
	public static void Magnification(int x,int y) {
		A00000_Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		
		final JFrame main_fm = B100_FrameParts.FrameCreate(x,y,500,200,"Corgi倍率調整　B100_Magnification","");
		JLabel userinfo = B100_FrameParts.UserInfo();
		JButton exit_btn = B100_FrameParts.ExitBtn();
		JButton entry_btn = B100_FrameParts.EntryBtn();

		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		main_fm.add(entry_btn);
		
		JLabel LB_Magnification						= B100_FrameParts.JLabelSet(					  0, 75,100,20,"表示枚率:"		,11,1);
		final JFormattedTextField TB_Magnification	= B100_FrameParts.JFormattedTextFieldSet(	100, 75, 70,20,"",11,1,"####");
		TB_Magnification.setText(""+A00000_Main.Mul);
		
		main_fm.add(LB_Magnification);
		main_fm.add(TB_Magnification);
		
		main_fm.setVisible(true);
		
		entry_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				int GetMul = B100_TextControl.TextToInt(TB_Magnification.getText());
				if(20>=GetMul) {GetMul=100;}
				A00000_Main.Mul	= GetMul;
				
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				A00001_MainMenu.MainMenu(0,0);
			}
		});
		
		exit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				A00001_MainMenu.MainMenu(0,0);
			}
		});
	}
}