import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class A00001WorkMain{
	static int SetX;
	static int SetY;
	public static void WorkMain(int x,int y) {
		A00000Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}

		final JFrame main_fm = B100FrameParts.FrameCreate(x,y,820,750,"Corgi00業務メニュー","");
		
		JLabel userinfo = B100FrameParts.UserInfo();
		JButton exit_btn = B100FrameParts.ExitBtn();
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		
		/*************************************/
		/*************************************/
			//入荷パネル
			JPanel PN_AboutArrival 	= B100FrameParts.JPanelSet(				 10, 40,380,235,"NK");
			JLabel LB_AboutArrival 	= B100FrameParts.JLabelSet(				 10,  0,150,20,"入荷メニュー",11,0);
			PN_AboutArrival.add(LB_AboutArrival);
			
			//入荷予定検索
			JButton ArrivalPlanSearch = B100FrameParts.BtnSet(				 20, 25,120,20,"入荷予定検索",11);
			PN_AboutArrival.add(ArrivalPlanSearch);
		
		/*************************************/
		/*************************************/
			//出荷パネル
			JPanel PN_AboutDeliveryPlan 	= B100FrameParts.JPanelSet(		410, 40,380,235,"SPPlan");
			JLabel LB_AboutDeliveryPlan 	= B100FrameParts.JLabelSet(		 10,  0,150,20,"出荷予定メニュー",11,0);
			PN_AboutDeliveryPlan.add(LB_AboutDeliveryPlan);
		
		/*************************************/
		/*************************************/
			//在庫パネル
			JPanel PN_Stock 	= B100FrameParts.JPanelSet(					 10,280,380,235,"ZK");
			JLabel LB_Stock 	= B100FrameParts.JLabelSet(					 10,  0,150,20,"在庫メニュー",11,0);
			PN_Stock.add(LB_Stock);
			
			//在庫検索
			JButton StockSearch = B100FrameParts.BtnSet(						 20, 25,120,20,"在庫検索",11);
			PN_Stock.add(StockSearch);
		
		/*************************************/
		/*************************************/

		main_fm.add(PN_AboutArrival);
		main_fm.add(PN_AboutDeliveryPlan);
		main_fm.add(PN_Stock);
		
		//テスト用
		JButton TestBtn = B100FrameParts.BtnSet(20,680,120,20,"テスト",11);
		main_fm.add(TestBtn);
		
		
		
		main_fm.setVisible(true);
		
		//入荷予定検索
		ArrivalPlanSearch.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				WT100ArrivalPlanSearch.ArrivalPlanSearch(0,0);
			}
		});
		
		//在庫検索
		StockSearch.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				WT100StockSearch.StockSearch(0,0);
			}
		});

		//EXITボタン押下時の挙動
		exit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				A00001MainMenu.MainMenu(0, 0);
			}
		});
		
		//テスト用
		TestBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				
			}
		});
	}
}