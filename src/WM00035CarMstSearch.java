import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WM00035CarMstSearch{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	public static void CarMstSearch(int x,int y) {
		A00000Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		
		final JFrame main_fm = B00110FrameParts.FrameCreate(x,y,860,750,"Corgi00車輛検索","");
		JLabel userinfo = B00110FrameParts.UserInfo();
		JButton exit_btn = B00110FrameParts.ExitBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		
		//検索条件パネル
		JPanel PN_Search = B00110FrameParts.JPanelSet(10,40,820,180,"White");
		JLabel PN_SearchLabel = B00110FrameParts.JLabelSet(10,0,150,20,"検索条件",11,0);
		PN_Search.add(PN_SearchLabel);
		main_fm.add(PN_Search);
		/*
		JLabel LB_SearchWHCD,
		JLabel LB_SearchShippingCompanyCd,
		JLabel LB_SearchCarCd,
		JLabel LB_SearchCarName,
		
		/*
		M00031CarMstRt.CarMstRt(
			ArrayList<String> SearchWHCD,
			ArrayList<String> SearchShippingCompanyCd,
			ArrayList<String> SearchCarCd,
			ArrayList<String> SearchCarName,
			boolean AllSearch)
		
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
				W00020MstMain.MstMain(0, 0);
			}
		});
	}
}