import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class W00030WorkMain{
	static int SetX;
	static int SetY;
	public static void WorkMain(int x,int y) {
		A00000Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}

		final JFrame main_fm = B00110FrameParts.FrameCreate(x,y,820,750,"Corgi00業務メニュー","");
		
		JLabel userinfo = B00110FrameParts.UserInfo();
		JButton exit_btn = B00110FrameParts.ExitBtn();
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		
		//入荷パネル
		JPanel PN_AboutArrival 	= B00110FrameParts.JPanelSet(		 10, 40,380,235,"NK");
		JLabel LB_AboutArrival 	= B00110FrameParts.JLabelSet(		 10,  0,150,20,"入荷メニュー",11,0);
		PN_AboutArrival.add(LB_AboutArrival);
		
		//出荷パネル
		JPanel PN_AboutDeliveryPlan 	= B00110FrameParts.JPanelSet(		410, 40,380,235,"SPPlan");
		JLabel LB_AboutDeliveryPlan 	= B00110FrameParts.JLabelSet(		 10,  0,150,20,"出荷予定メニュー",11,0);
		PN_AboutDeliveryPlan.add(LB_AboutDeliveryPlan);
		
		//入荷予定検索
		JButton ArrivalPlanSearch = B00110FrameParts.BtnSet(	 20, 25,120,20,"入荷予定",11);
		PN_AboutArrival.add(ArrivalPlanSearch);

		main_fm.add(PN_AboutArrival);
		main_fm.add(PN_AboutDeliveryPlan);
		
		//テスト用
		JButton TestBtn = B00110FrameParts.BtnSet(20,680,120,20,"テスト",11);
		main_fm.add(TestBtn);
		
		
		
		main_fm.setVisible(true);
		
		//入荷予定検索
		ArrivalPlanSearch.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				WT0001000ArrivalPlanSearch.ArrivalPlanSearch(0,0);
			}
		});
		
		//テスト用
		TestBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				B00180PdfControl.PdfCreate("C:\\MIZUNO\\WMS\\TEST\\TestPdf.pdf",false);
			}
		});

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