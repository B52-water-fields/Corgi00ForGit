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

		final JFrame main_fm = B100_FrameParts.FrameCreate(x,y,820,770,"Corgi00業務メニュー","");
		
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
		
		//出荷指示検索
		JButton OkuriHdSearch = B100_FrameParts.BtnSet(					 20, 25,120,20,"出荷指示検索",11);
		PN_Ship.add(OkuriHdSearch);
		
		//出荷指示一括登録
		JButton OkuriSomeEntry = B100_FrameParts.BtnSet(					 20, 50,120,20,"出荷指示一括登録",9);
		PN_Ship.add(OkuriSomeEntry);
		
		//強制出荷
		JButton ShipForceEntry = B100_FrameParts.BtnSet(					 20,125,120,20,"強制出庫",11);
		PN_Ship.add(ShipForceEntry);
			
		/*************************************/
		/*************************************/
		//その他パネル
		JPanel PN_Other 	= B100_FrameParts.JPanelSet(					410,480,380,210,"White");
		JLabel LB_Other 	= B100_FrameParts.JLabelSet(					 10,  0,150,20,"その他メニュー",11,0);
		PN_Other.add(LB_Other);

		main_fm.add(PN_AboutArrival);
		main_fm.add(PN_AboutDeliveryPlan);
		main_fm.add(PN_Stock);
		main_fm.add(PN_Ship);
		main_fm.add(PN_Other);
		
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
		
		//出荷指示検索
		OkuriHdSearch.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				WT100_OkuriHd_00_Search.OkuriHdSearch(0,0);
			}
		});
		
		//出荷指示一括登録
		OkuriSomeEntry.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				WT100_OkuriData_04_SomeEntry.OkuriDataSomeEntry(0,0);
			}
		});
		
		//強制出荷
		ShipForceEntry.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				String TgtLoc			= "";
				String TgtItemCd		= "";
				String TgtLot			= "";
				String TgtExpDate		= "";
				String TgtActualDate	= "";
				WT100_Ship_20_ForceEntry.ShipForceEntry(0,0,A00000_Main.ClWh,A00000_Main.ClCd,TgtLoc,TgtItemCd,TgtLot,TgtExpDate,TgtActualDate);
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
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> Searchcl_cd				= new ArrayList<String>();			//荷主コード
				ArrayList<String> SearchInvoiceWHCD			= new ArrayList<String>();			//倉庫コード
				ArrayList<String> SearchOkuriNo				= new ArrayList<String>();			//送り状番号
				ArrayList<Integer> SearchMsNoMin			= new ArrayList<Integer>();			//明細番号
				ArrayList<Integer> SearchSeqMin				= new ArrayList<Integer>();			//引当枝番
				ArrayList<Integer> SearchMsNoMax			= new ArrayList<Integer>();			//明細番号
				ArrayList<Integer> SearchSeqMax				= new ArrayList<Integer>();			//引当枝番
				ArrayList<String> SearchOrderItemCd			= new ArrayList<String>();			//商品コード
				ArrayList<String> SearchOrderItemName		= new ArrayList<String>();			//商品表記名
				ArrayList<String> SearchOrderLot			= new ArrayList<String>();			//受注ロット指定
				ArrayList<String> SearchOrderExpDateStr		= new ArrayList<String>();			//受注賞味期限指定
				ArrayList<String> SearchOrderExpDateEnd		= new ArrayList<String>();			//受注賞味期限指定
				ArrayList<Integer> SearchOrderQtyMin		= new ArrayList<Integer>();			//受注個数
				ArrayList<Integer> SearchOrderQtyMax		= new ArrayList<Integer>();			//受注個数
				ArrayList<String> SearchShipWhCd			= new ArrayList<String>();			//出荷倉庫コード
				ArrayList<String> SearchShipLoc				= new ArrayList<String>();			//ロケーション
				ArrayList<String> SearchShipItemCd			= new ArrayList<String>();			//商品コード
				ArrayList<String> SearchShipLot				= new ArrayList<String>();			//ロット
				ArrayList<String> SearchShipExpdateStr		= new ArrayList<String>();			//消費期限
				ArrayList<String> SearchShipActualDateStr	= new ArrayList<String>();			//入荷実績日
				ArrayList<String> SearchShipExpdateEnd		= new ArrayList<String>();			//消費期限
				ArrayList<String> SearchShipActualDateEnd	= new ArrayList<String>();			//入荷実績日
				ArrayList<String> SearchShipQtyMin			= new ArrayList<String>();			//引当数量
				ArrayList<String> SearchShipQtyMax			= new ArrayList<String>();			//引当数量
				ArrayList<String> SearchFixFg				= new ArrayList<String>();			//引落済フラグ
				ArrayList<Integer> SearchPackingType		= new ArrayList<Integer>();			//荷姿タイプ
				ArrayList<Integer> SearchPackingQtyMin		= new ArrayList<Integer>();			//荷姿数量
				ArrayList<Integer> SearchPackingQtyMax		= new ArrayList<Integer>();			//荷姿数量
				ArrayList<Integer> SearchBRShipQtyMin		= new ArrayList<Integer>();			//バラ数量
				ArrayList<Integer> SearchCTShipQtyMin		= new ArrayList<Integer>();			//カートン数量
				ArrayList<Integer> SearchCSShipQtyMin		= new ArrayList<Integer>();			//ケース数量
				ArrayList<Integer> SearchPLShipQtyMin		= new ArrayList<Integer>();			//パレット数量
				ArrayList<Integer> SearchBRShipQtyMax		= new ArrayList<Integer>();			//バラ数量
				ArrayList<Integer> SearchCTShipQtyMax		= new ArrayList<Integer>();			//カートン数量
				ArrayList<Integer> SearchCSShipQtyMax		= new ArrayList<Integer>();			//ケース数量
				ArrayList<Integer> SearchPLShipQtyMax		= new ArrayList<Integer>();			//パレット数量
				ArrayList<String> SearchEntryDateStr		= new ArrayList<String>();			//登録日時
				ArrayList<String> SearchUpdateDateStr		= new ArrayList<String>();			//更新日時
				ArrayList<String> SearchEntryDateEnd		= new ArrayList<String>();			//登録日時
				ArrayList<String> SearchUpdateDateEnd		= new ArrayList<String>();			//更新日時
				ArrayList<String> SearchEntryUser			= new ArrayList<String>();			//登録者
				ArrayList<String> SearchUpdateUser			= new ArrayList<String>();			//更新者
					
				ArrayList<String> SearchClDeliNo			= new ArrayList<String>();			//荷主管理番号
				ArrayList<String> SearchPlanDateStr			= new ArrayList<String>();			//出荷予定日
				ArrayList<String> SearchShipDateStr			= new ArrayList<String>();			//出荷実績日
				ArrayList<String> SearchPlanDateEnd			= new ArrayList<String>();			//出荷予定日
				ArrayList<String> SearchShipDateEnd			= new ArrayList<String>();			//出荷実績日
					
				ArrayList<String> SearchDeliCd				= new ArrayList<String>();			//荷届先コード
				ArrayList<String> SearchClDeliCd			= new ArrayList<String>();			//荷主荷届先コード
				ArrayList<String> SearchDeliDepartmentCd	= new ArrayList<String>();			//部署CD
				ArrayList<String> SearchDeliName			= new ArrayList<String>();			//荷届先名
				ArrayList<String> SearchDeliPost			= new ArrayList<String>();			//荷届先郵便番号
				ArrayList<String> SearchDeliAdd				= new ArrayList<String>();			//荷届先住所
				ArrayList<String> SearchDeliTel				= new ArrayList<String>();			//荷届先TEL
				ArrayList<String> SearchDeliFax				= new ArrayList<String>();			//荷届先FAX
				ArrayList<String> SearchDeliMail			= new ArrayList<String>();			//荷届先MAIL
				ArrayList<String> SearchDeliMunicCd			= new ArrayList<String>();			//荷届先市区町村CD
					 
				ArrayList<String> SearchClGpCD				= new ArrayList<String>();			//荷主グループCD
				ArrayList<String> SearchClCd				= new ArrayList<String>();			//荷主CD
				ArrayList<String> SearchPickupWhCd			= new ArrayList<String>();			//集荷倉庫CD
				ArrayList<String> SearchPurposeFG			= new ArrayList<String>();			//目的フラグ
				ArrayList<String> SearchSPPlanDateStr		= new ArrayList<String>();			//着日指定
				ArrayList<String> SearchSPDateStr			= new ArrayList<String>();			//着日実績
				ArrayList<String> SearchSPPlanDateEnd		= new ArrayList<String>();			//着日指定
				ArrayList<String> SearchSPDateEnd			= new ArrayList<String>();			//着日実績

				ArrayList<String> SearchCategoryCd			= new ArrayList<String>();			//商品カテゴリCD
				ArrayList<String> SearchCategoryName		= new ArrayList<String>();			//商品カテゴリ名
				ArrayList<String> SearchItemColorCd			= new ArrayList<String>();			//商品カラーコード
				ArrayList<String> SearchItemColorName		= new ArrayList<String>();			//商品カラー名
				ArrayList<String> SearchItemSizeCd			= new ArrayList<String>();			//商品サイズコード
				ArrayList<String> SearchItemSizeName		= new ArrayList<String>();			//商品サイズ名
				boolean AllSearch = false;
				
				Object[][] ShipPlovisionRt	= T100_ShipPlovisionRt.ShipPlovisionRt(
									 Searchcl_cd				//荷主コード
									,SearchInvoiceWHCD			//倉庫コード
									,SearchOkuriNo				//送り状番号
									,SearchMsNoMin				//明細番号
									,SearchSeqMin				//引当枝番
									,SearchMsNoMax				//明細番号
									,SearchSeqMax				//引当枝番
									,SearchOrderItemCd			//商品コード
									,SearchOrderItemName			//商品表記名
									,SearchOrderLot				//受注ロット指定
									,SearchOrderExpDateStr		//受注賞味期限指定
									,SearchOrderExpDateEnd		//受注賞味期限指定
									,SearchOrderQtyMin			//受注個数
									,SearchOrderQtyMax			//受注個数
									,SearchShipWhCd				//出荷倉庫コード
									,SearchShipLoc				//ロケーション
									,SearchShipItemCd			//商品コード
									,SearchShipLot				//ロット
									,SearchShipExpdateStr		//消費期限
									,SearchShipActualDateStr	//入荷実績日
									,SearchShipExpdateEnd		//消費期限
									,SearchShipActualDateEnd	//入荷実績日
									,SearchShipQtyMin			//引当数量
									,SearchShipQtyMax			//引当数量
									,SearchFixFg				//引落済フラグ
									,SearchPackingType			//荷姿タイプ
									,SearchPackingQtyMin		//荷姿数量
									,SearchPackingQtyMax		//荷姿数量
									,SearchBRShipQtyMin			//バラ数量
									,SearchCTShipQtyMin			//カートン数量
									,SearchCSShipQtyMin			//ケース数量
									,SearchPLShipQtyMin			//パレット数量
									,SearchBRShipQtyMax			//バラ数量
									,SearchCTShipQtyMax			//カートン数量
									,SearchCSShipQtyMax			//ケース数量
									,SearchPLShipQtyMax			//パレット数量
									,SearchEntryDateStr			//登録日時
									,SearchUpdateDateStr		//更新日時
									,SearchEntryDateEnd			//登録日時
									,SearchUpdateDateEnd		//更新日時
									,SearchEntryUser			//登録者
									,SearchUpdateUser			//更新者
										
									,SearchClDeliNo				//荷主管理番号
									,SearchPlanDateStr			//出荷予定日
									,SearchShipDateStr			//出荷実績日
									,SearchPlanDateEnd			//出荷予定日
									,SearchShipDateEnd			//出荷実績日
										
									,SearchDeliCd				//荷届先コード
									,SearchClDeliCd				//荷主荷届先コード
									,SearchDeliDepartmentCd		//部署CD
									,SearchDeliName				//荷届先名
									,SearchDeliPost				//荷届先郵便番号
									,SearchDeliAdd				//荷届先住所
									,SearchDeliTel				//荷届先TEL
									,SearchDeliFax				//荷届先FAX
									,SearchDeliMail				//荷届先MAIL
									,SearchDeliMunicCd			//荷届先市区町村CD
										 
									,SearchClGpCD				//荷主グループCD
									,SearchClCd					//荷主CD
									,SearchPickupWhCd			//集荷倉庫CD
									,SearchPurposeFG			//目的フラグ
									,SearchSPPlanDateStr		//着日指定
									,SearchSPDateStr			//着日実績
									,SearchSPPlanDateEnd		//着日指定
									,SearchSPDateEnd			//着日実績

									,SearchCategoryCd			//商品カテゴリCD
									,SearchCategoryName			//商品カテゴリ名
									,SearchItemColorCd			//商品カラーコード
									,SearchItemColorName		//商品カラー名
									,SearchItemSizeCd			//商品サイズコード
									,SearchItemSizeName			//商品サイズ名
									,AllSearch
									);
			
			}
		});
	}
}