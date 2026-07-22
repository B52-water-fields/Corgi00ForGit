import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class A00001_WorkMain{
	static int SetX;
	static int SetY;
	public static void WorkMain(int x,int y) {
		A00000_Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}

		final JFrame main_fm = B100_FrameParts.FrameCreate(x,y,820,850,"Corgi00業務メニュー","");
		
		JLabel userinfo = B100_FrameParts.UserInfo();
		JButton exit_btn = B100_FrameParts.ExitBtn();
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		
		/*************************************/
		/*************************************/
			//入荷パネル
			JPanel PN_AboutArrival 	= B100_FrameParts.JPanelSet(				 10, 40,380,235,"NK");
			JLabel LB_AboutArrival 	= B100_FrameParts.JLabelSet(				 10,  0,150,20,"入荷メニュー",11,0);
			PN_AboutArrival.add(LB_AboutArrival);
			
			//入荷予定検索
			JButton ArrivalPlanSearch = B100_FrameParts.BtnSet(				 20, 25,120,20,"入荷予定検索",11);
			PN_AboutArrival.add(ArrivalPlanSearch);
			
			//強制入庫
			JButton ArrivalForceEntry = B100_FrameParts.BtnSet(				 20, 75,120,20,"強制入庫",11);
			PN_AboutArrival.add(ArrivalForceEntry);
		
		/*************************************/
		/*************************************/
			//出荷予定パネル
			JPanel PN_AboutDeliveryPlan 	= B100_FrameParts.JPanelSet(		410, 40,380,235,"SPPlan");
			JLabel LB_AboutDeliveryPlan 	= B100_FrameParts.JLabelSet(		 10,  0,150,20,"出荷予定メニュー",11,0);
			PN_AboutDeliveryPlan.add(LB_AboutDeliveryPlan);
		
		/*************************************/
		/*************************************/
			//在庫パネル
			JPanel PN_Stock 	= B100_FrameParts.JPanelSet(					 10,280,380,235,"ZK");
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
			JPanel PN_Ship 	= B100_FrameParts.JPanelSet(						 10,520,380,235,"SP");
			JLabel LB_Ship 	= B100_FrameParts.JLabelSet(						 10,  0,150,20,"出荷メニュー",11,0);
			PN_Ship.add(LB_Ship);

		main_fm.add(PN_AboutArrival);
		main_fm.add(PN_AboutDeliveryPlan);
		main_fm.add(PN_Stock);
		main_fm.add(PN_Ship);
		
		//テスト用
		JButton TestBtn = B100_FrameParts.BtnSet(20,760,120,20,"テスト",11);
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
				ArrayList<String> SearchClWh=new ArrayList<String>();
				ArrayList<String> SearchClCd=new ArrayList<String>();
				ArrayList<String> SearchClGpCD=new ArrayList<String>();			//ヘッダ荷主グループCD
				ArrayList<String> SearchArrNo=new ArrayList<String>();			//入荷予定NO
				ArrayList<Integer> SearchArrCountMin=new ArrayList<Integer>();	//入荷予定枝番最小
				ArrayList<Integer> SearchArrCountMax=new ArrayList<Integer>();	//入荷予定枝番最大
				ArrayList<String> SearchClArrNo=new ArrayList<String>();		//荷主予定番号
				ArrayList<String> SearchPlanDateMin=new ArrayList<String>();	//入荷予定日最小
				ArrayList<String> SearchPlanDateMax=new ArrayList<String>();	//入荷予定日最大
				ArrayList<String> SearchActualDateMin=new ArrayList<String>();	//入荷実績日最小
				ArrayList<String> SearchActualDateMax=new ArrayList<String>();	//入荷実績日最大
				ArrayList<String> SearchSpCd=new ArrayList<String>();			//仕入先CD
				ArrayList<String> SearchCom=new ArrayList<String>();			//コメント
				ArrayList<String> SearchEntryDateMin=new ArrayList<String>();	//登録日最小
				ArrayList<String> SearchEntryDateMax=new ArrayList<String>();	//登録日最大
				ArrayList<String> SearchUpdateDateMin=new ArrayList<String>();	//更新日最小
				ArrayList<String> SearchUpdateDateMax=new ArrayList<String>();	//更新日最大
				ArrayList<String> SearchEntryUser=new ArrayList<String>();		//登録者
				ArrayList<String> SearchUpdateUser=new ArrayList<String>();		//更新者
				
				//明細WW0013ArrivaMs由来
				ArrayList<String> SearchItemCd=new ArrayList<String>();			//商品コード
				ArrayList<String> SearchClItemCd=new ArrayList<String>();		//荷主商品コード
				ArrayList<String> SearchItemName=new ArrayList<String>();		//商品名
				ArrayList<String> SearchLot=new ArrayList<String>();			//ロット
				ArrayList<String> SearchExpDateMin=new ArrayList<String>();		//消費期限最小
				ArrayList<String> SearchExpDateMax=new ArrayList<String>();		//消費期限最大
				
				
				
				SearchClItemCd.add("123");
				SearchClCd.add("AT0000001");
				T100_ArrivalMsRt.ArrivalMsRt(
						SearchClWh,			//担当倉庫
						SearchClCd,			//荷主CD
						SearchClGpCD,			//ヘッダ荷主グループCD
						SearchArrNo,			//入荷予定NO
						SearchArrCountMin,	//入荷予定枝番最小
						SearchArrCountMax,	//入荷予定枝番最大
						SearchClArrNo,		//荷主予定番号
						SearchPlanDateMin,	//入荷予定日最小
						SearchPlanDateMax,	//入荷予定日最大
						SearchActualDateMin,	//入荷実績日最小
						SearchActualDateMax,	//入荷実績日最大
						SearchSpCd,			//仕入先CD
						SearchCom,			//コメント
						SearchEntryDateMin,	//登録日最小
						SearchEntryDateMax,	//登録日最大
						SearchUpdateDateMin,	//更新日最小
						SearchUpdateDateMax,	//更新日最大
						SearchEntryUser,		//登録者
						SearchUpdateUser,		//更新者
						
						//明細WW0013ArrivaMs由来
						SearchItemCd,			//商品コード
						SearchClItemCd,		//荷主商品コード
						SearchItemName,		//商品名
						SearchLot,			//ロット
						SearchExpDateMin,		//消費期限最小
						SearchExpDateMax,		//消費期限最大
						true);
			}
		});
	}
}