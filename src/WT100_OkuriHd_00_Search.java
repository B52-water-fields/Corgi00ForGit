import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WT100_OkuriHd_00_Search{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	
	static boolean MsViewMode;
	public static void OkuriHdSearch(int x,int y) {
		A00000_Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		MsViewMode = false;
		final JFrame main_fm = B100_FrameParts.FrameCreate(x,y,1200,800,"Corgi00出荷指示検索　WT100_OkuriHd_00_Search","SP");
		JLabel userinfo = B100_FrameParts.UserInfo();
		JButton exit_btn = B100_FrameParts.ExitBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		
		//検索条件パネル
		JPanel PN_Search = B100_FrameParts.JPanelSet(10,40,1160,300,"White");
		JLabel PN_SearchLabel = B100_FrameParts.JLabelSet(0,0,100,20,"検索条件",11,0);
		
		//検索条件
		
		
		
		
		
		
		
		
		
		
		main_fm.setVisible(true);
		RenewFg = true;
		
		
		//EXITボタン押下時の挙動
		exit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();
				/*
				Ms_fm.setVisible(false);
				Ms_fm.dispose();
				*/
				main_fm.setVisible(false);
				main_fm.dispose();
				A00001_WorkMain.WorkMain(0,0);
			}
		});
	}
}