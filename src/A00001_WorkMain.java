import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

public class A00001_WorkMain{
	static int SetX;
	static int SetY;
	public static void WorkMain(int x,int y) {
		A00000_Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}

		final JFrame main_fm = B100_FrameParts.FrameCreate(x,y,820,750,"Corgi00業務メニュー","");
		
		JLabel userinfo = B100_FrameParts.UserInfo();
		JButton exit_btn = B100_FrameParts.ExitBtn();
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		
		/*************************************/
		/*************************************/
			//入荷パネル
			JPanel PN_AboutArrival 	= B100_FrameParts.JPanelSet(				 10, 40,380,210,"NK");
			JLabel LB_AboutArrival 	= B100_FrameParts.JLabelSet(				 10,  0,150,20,"入荷メニュー",11,0);
			PN_AboutArrival.add(LB_AboutArrival);
			
			//入荷予定検索
			JButton ArrivalPlanSearch = B100_FrameParts.BtnSet(				 20, 25,120,20,"入荷予定検索",11);
			PN_AboutArrival.add(ArrivalPlanSearch);
			
			//入荷検品
			JButton Arrival = B100_FrameParts.BtnSet(							 20, 50,120,20,"入荷検品",11);
			PN_AboutArrival.add(Arrival);
			
			//強制入庫
			JButton ArrivalForceEntry = B100_FrameParts.BtnSet(				 20,125,120,20,"強制入庫",11);
			PN_AboutArrival.add(ArrivalForceEntry);
			
			//入荷実績検索
			JButton ArrivalSearch = B100_FrameParts.BtnSet(					150, 25,120,20,"入荷実績検索",11);
			PN_AboutArrival.add(ArrivalSearch);
		
		/*************************************/
		/*************************************/
			//出荷予定パネル
			JPanel PN_AboutDeliveryPlan 	= B100_FrameParts.JPanelSet(		410, 40,380,210,"SPPlan");
			JLabel LB_AboutDeliveryPlan 	= B100_FrameParts.JLabelSet(		 10,  0,150,20,"出荷予定メニュー",11,0);
			PN_AboutDeliveryPlan.add(LB_AboutDeliveryPlan);
		
		/*************************************/
		/*************************************/
			//在庫パネル
			JPanel PN_Stock 	= B100_FrameParts.JPanelSet(					 10,260,380,210,"ZK");
			JLabel LB_Stock 	= B100_FrameParts.JLabelSet(					 10,  0,150,20,"在庫メニュー",11,0);
			PN_Stock.add(LB_Stock);
			
			//在庫検索
			JButton StockSearch = B100_FrameParts.BtnSet(						 20, 25,120,20,"在庫検索",11);
			PN_Stock.add(StockSearch);
			
			//在庫調整履歴
			JButton StockAdjust = B100_FrameParts.BtnSet(						 20, 50,120,20,"在庫調整履歴",11);
			PN_Stock.add(StockAdjust);
			
			//在庫移動履歴
			JButton StockMove = B100_FrameParts.BtnSet(						 20, 75,120,20,"在庫移動履歴",11);
			PN_Stock.add(StockMove);
		
		/*************************************/
		/*************************************/
			//出荷パネル
			JPanel PN_Ship 	= B100_FrameParts.JPanelSet(						 10,480,380,210,"SP");
			JLabel LB_Ship 	= B100_FrameParts.JLabelSet(						 10,  0,150,20,"出荷メニュー",11,0);
			PN_Ship.add(LB_Ship);

		main_fm.add(PN_AboutArrival);
		main_fm.add(PN_AboutDeliveryPlan);
		main_fm.add(PN_Stock);
		main_fm.add(PN_Ship);
		
		//テスト用
		JButton TestBtn = B100_FrameParts.BtnSet(20,700,120,20,"テスト",11);
		main_fm.add(TestBtn);
		
		
		
		main_fm.setVisible(true);
		
		//入荷予定検索
		ArrivalPlanSearch.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				WT100_ArrivalPlan_00_Search.ArrivalPlanSearch(0,0);
			}
		});
		
		//入荷実績検索
		ArrivalSearch.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				WT100_Arrival_00_Search.ArrivalSearch(0,0);
			}
		});
		
		//入荷検品
		Arrival.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				WT100_Arrival_10_Entry.ArrivalEntry(0,0,A00000_Main.ClWh,A00000_Main.ClCd,null);
			}
		});
		
		//強制入庫
		ArrivalForceEntry.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				WT100_Arrival_20_ForceEntry.ArrivalForceEntry(0,0);
			}
		});
		
		//在庫検索
		StockSearch.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				WT100_Stock_00_Search.StockSearch(0,0);
			}
		});
		
		//在庫調整履歴
		StockAdjust.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				WT100_StockAdjust_00_Search.StockAdjustSearch(0,0);
			}
		});
		
		//在庫移動履歴
		StockMove.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				WT100_StockMove_00_Search.StockMoveSearch(0, 0);
			}
		});

		//EXITボタン押下時の挙動
		exit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				
				A00001_MainMenu.MainMenu(0, 0);
			}
		});
		
		//テスト用
		TestBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				Object[] DeliveryMstSearchSubFm = WT200_DeliveryMstSearchSubFm.DeliveryMstSearchSubFm(0, 0, A00000_Main.ClWh, A00000_Main.ClCd, "ZK");
				
				((JFrame)DeliveryMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtJFrame]).setVisible(true);
				((JButton)DeliveryMstSearchSubFm[WT200_DeliveryMstSearchSubFm.EntryBtn]).addActionListener(new AbstractAction(){
					public void actionPerformed(ActionEvent e){
						int RowCount = ((DefaultTableModel)DeliveryMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtDefaultTableModel]).getRowCount();
						for(int i01=0;i01<RowCount;i01++) {
							System.out.println(((DefaultTableModel)DeliveryMstSearchSubFm[WT200_DeliveryMstSearchSubFm.RtDefaultTableModel]).getValueAt(i01, 1+M100_DeliveryMstRt.ColDEName01));
						}
					}
				});
				
				
			}
		});
	}
}