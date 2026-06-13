import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class WT0001020StockAdjust{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	public static void StockAdjust(int x,int y,String TgtWhCd,String TgtClCd,String TgtLoc,String TgtItemCd,String TgtLot,String TgtExpdate,String TgtActualDate) {
		A00000Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		
		JLabel LB_ClCd				= B00110FrameParts.JLabelSet(  0, 50,150,20,"荷主:"				,11,1);
		JLabel LB_WhCd				= B00110FrameParts.JLabelSet(  0, 75,150,20,"倉庫:"				,11,1);
		
		JLabel LB_Loc				= B00110FrameParts.JLabelSet(  0,100,150,20,"ロケーション:"		,11,1);
		JLabel LB_LocName			= B00110FrameParts.JLabelSet(  0,125,150,20,"ロケーション名:"		,11,1);
		JLabel LB_LocType			= B00110FrameParts.JLabelSet(  0,150,150,20,"ロケータイプ:"		,11,1);
		JLabel LB_ItemCd			= B00110FrameParts.JLabelSet(  0,175,150,20,"商品コード:"			,11,1);
		JLabel LB_ItemName			= B00110FrameParts.JLabelSet(  0,200,150,20,"商品名:"				,11,1);
		JLabel LB_Lot				= B00110FrameParts.JLabelSet(  0,225,150,20,"ロット:"				,11,1);
		JLabel LB_Expdate			= B00110FrameParts.JLabelSet(  0,250,150,20,"消費期限:"			,11,1);
		JLabel LB_ActualDate		= B00110FrameParts.JLabelSet(  0,275,150,20,"入荷実績日:"			,11,1);
		JLabel LB_Qty				= B00110FrameParts.JLabelSet(  0,300,150,20,"調整前数量:"			,11,1);
		JLabel LB_ShipPlanQty		= B00110FrameParts.JLabelSet(  0,325,150,20,"調整前引当済数:"		,11,1);
		JLabel LB_PossibleQty		= B00110FrameParts.JLabelSet(  0,350,150,20,"調整前出荷可能数:"	,11,1);
		
		JLabel LB_AdjustQty			= B00110FrameParts.JLabelSet(  0,400,150,20,"調整数:"				,11,1);
		
		JLabel LB_AfterQty			= B00110FrameParts.JLabelSet(  0,450,150,20,"調整後数量:"			,11,1);
		JLabel LB_AfterShipPlanQty	= B00110FrameParts.JLabelSet(  0,475,150,20,"調整後引当済数:"		,11,1);
		JLabel LB_AfterPossibleQty	= B00110FrameParts.JLabelSet(  0,500,150,20,"調整後出荷可能数:"	,11,1);
		
		final JComboBox TB_ClCd		= B00110FrameParts.JComboBoxSet(	100, 50,200,20,B00100DefaultVariable.ClList[0],11);		//荷主コード
		final JComboBox TB_WhCd		= B00110FrameParts.JComboBoxSet(	100, 75,200,20,B00100DefaultVariable.WhList[0],11);		//倉庫コード
		
		final JTextField TB_Loc		= B00110FrameParts.JTextFieldSet(				440, 25,100,20,"",11,0);						//ロケーション
		final JTextField TB_LocName	= B00110FrameParts.JTextFieldSet(				440, 25,100,20,"",11,0);						//ロケーション名
		final JComboBox TB_LocType	= B00110FrameParts.JComboBoxSet(	100, 75,200,20,B00100DefaultVariable.LocType[0],11);		//ロケータイプ
		final JTextField TB_ItemCd	= B00110FrameParts.JTextFieldSet(				440, 25,100,20,"",11,0);						//商品コード
		final JTextField TB_ItemName= B00110FrameParts.JTextFieldSet(				440, 25,100,20,"",11,0);						//商品名
		final JTextField TB_Lot		= B00110FrameParts.JTextFieldSet(				440, 25,100,20,"",11,0);						//ロット
		final JFormattedTextField TB_Expdate			= B00110FrameParts.JFormattedTextFieldSet(	440,175, 70,20,"",11,0,"YYYY/MM/DD");		//消費期限
		final JFormattedTextField TB_ActualDate			= B00110FrameParts.JFormattedTextFieldSet(	440,175, 70,20,"",11,0,"YYYY/MM/DD");		//入荷実績日
		final JFormattedTextField TB_Qty				= B00110FrameParts.JFormattedTextFieldSet(	780, 25, 70,20,"",11,1,"####");				//調整前数量
		final JFormattedTextField TB_ShipPlanQty		= B00110FrameParts.JFormattedTextFieldSet(	780, 25, 70,20,"",11,1,"####");				//調整前引当済数
		final JFormattedTextField TB_PossibleQty		= B00110FrameParts.JFormattedTextFieldSet(	780, 25, 70,20,"",11,1,"####");				//調整前出荷可能数
		
		final JFormattedTextField TB_AdjustQty			= B00110FrameParts.JFormattedTextFieldSet(	780, 25, 70,20,"",11,1,"####");				//調整数
		
		final JFormattedTextField TB_AfterQty			= B00110FrameParts.JFormattedTextFieldSet(	780, 25, 70,20,"",11,1,"####");				//調整後数量
		final JFormattedTextField TB_AfterShipPlanQty	= B00110FrameParts.JFormattedTextFieldSet(	780, 25, 70,20,"",11,1,"####");				//調整後引当済数
		final JFormattedTextField TB_AfterPossibleQty	= B00110FrameParts.JFormattedTextFieldSet(	780, 25, 70,20,"",11,1,"####");				//調整後出荷可能数
		
		
		
		/******************************************
			現在の在庫情報を取得する
		******************************************/
		
		if(null==TgtWhCd		) {TgtWhCd			="";}
		if(null==TgtClCd		) {TgtClCd			="";}
		if(null==TgtLoc			) {TgtLoc			="";}
		if(null==TgtItemCd		) {TgtItemCd		="";}
		if(null==TgtLot			) {TgtLot			="";}
		if(null==TgtExpdate		) {TgtExpdate		="";}
		if(null==TgtActualDate	) {TgtActualDate	="";}
		
		if("".equals(TgtWhCd)) {TgtWhCd	=A00000Main.ClWh;}
		if("".equals(TgtClCd)) {TgtClCd	=A00000Main.ClCd;}
		
		
		String GetClCd			= TgtClCd;				//荷主コード
		String GetCLName		= "";					//荷主名1
		String GetWhCd			= TgtWhCd;				//倉庫コード
		String GetClWHName		= "";					//担当倉庫名
		String GetClGpCD		= "";					//荷主グループCD
		String GetClGpName		= "";					//グループ名1
		String GetLoc			= TgtLoc;				//ロケーション
		String GetLocName		= "";					//ロケーション名
		int GetType				= 0;					//ロケタイプ
		String GetItemCd		= TgtItemCd;			//商品コード
		String GetLot			= TgtLot;				//ロット
		String GetExpdate		= TgtExpdate;			//消費期限
		String GetActualDate	= TgtActualDate;		//入荷実績日
		int GetQty				= 0;					//総数量
		int GetShipPlanQty		= 0;					//引当済総数
		int GetPossibleQty		= 0;					//出荷可能総数
		String GetItemName		= "";					//商品名
		String GetItemName01	= "";					//商品名1
		String GetItemName02	= "";					//商品名2
		String GetItemName03	= "";					//商品名3
		String GetClItemCd		= "";					//荷主商品コード
		String GetJanCd			= "";					//ソースマーク_BCD（バラ）
		String GetItemMdNo		= "";					//商品型番
		int GetCtUnitQty		= 0;					//カートン入数
		int GetCsUnitQty		= 0;					//ケース入数
		int GetPlUnitQty		= 0;					//パレット入数
		String GetUnitName		= "";					//商品単位
		String GetCtUnitName	= "";					//カートン商品単位
		String GetCsUnitName	= "";					//ケース商品単位
		String GetPlUnitName	= "";					//パレット商品単位
		String GetEntryDate		= "";					//登録日時
		String GetUpdateDate	= "";					//更新日時
		String GetEntryUser		= "";					//登録者
		String GetUpdateUser	= "";					//更新者
		int GetBrQty			= 0;					//バラ数量
		int GetBrShipPlanQty	= 0;					//引当済バラ数
		int GetBrPossibleQty	= 0;					//出荷可能バラ数
		int GetCtQty			= 0;					//カートン数量
		int GetCtShipPlanQty	= 0;					//引当済カートン数
		int GetCtPossibleQty	= 0;					//出荷可能カートン数
		int GetCsQty			= 0;					//ケース数量
		int GetCsShipPlanQty	= 0;					//引当済ケース数
		int GetCsPossibleQty	= 0;					//出荷可能ケース数
		int GetPlQty			= 0;					//パレット数量
		int GetPlShipPlanQty	= 0;					//引当済パレット数
		int GetPlPossibleQty	= 0;					//出荷可能パレット数
		
		boolean MstCheckFg = true;
		
		if(!"".equals(TgtWhCd)
				&&!"".equals(TgtClCd)
				&&!"".equals(TgtLoc)
				&&!"".equals(TgtItemCd)
				&&!"".equals(TgtLot)
				&&!"".equals(TgtExpdate)
				&&!"".equals(TgtActualDate)
				) {
		
			ArrayList<String> SearchClCd				= new ArrayList<String>();			//荷主コード
			ArrayList<String> SearchWhCd				= new ArrayList<String>();			//倉庫コード
			ArrayList<String> SearchClGpCD				= new ArrayList<String>();			//荷主グループCD
			ArrayList<String> SearchLoc					= new ArrayList<String>();			//ロケーション
			ArrayList<Integer>SearchType				= new ArrayList<Integer>();			//ロケタイプ　0:通常　1:保管　8:入荷時　9:引当禁止
			ArrayList<String> SearchItemCd				= new ArrayList<String>();			//商品コード
			ArrayList<String> SearchLot					= new ArrayList<String>();			//ロット
			ArrayList<String> SearchExpdateMin			= new ArrayList<String>();			//消費期限最小
			ArrayList<String> SearchExpdateMax			= new ArrayList<String>();			//消費期限最大
			ArrayList<String> SearchActualDateMin		= new ArrayList<String>();			//入荷実績日最小
			ArrayList<String> SearchActualDateMax		= new ArrayList<String>();			//入荷実績日最大
			ArrayList<Integer> SearchQtyMin				= new ArrayList<Integer>();			//数量最小
			ArrayList<Integer> SearchQtyMax				= new ArrayList<Integer>();			//数量最大
			ArrayList<Integer> SearchShipPlanQtyMin		= new ArrayList<Integer>();			//引当済数最小
			ArrayList<Integer> SearchShipPlanQtyMax		= new ArrayList<Integer>();			//引当済数最大
			ArrayList<Integer> SearchPossibleQtyMin		= new ArrayList<Integer>();			//出荷可能数最小
			ArrayList<Integer> SearchPossibleQtyMax		= new ArrayList<Integer>();			//出荷可能数最大
			ArrayList<String> SearchItemName			= new ArrayList<String>();			//商品名
			ArrayList<String> SearchClItemCd			= new ArrayList<String>();			//荷主商品コード
			ArrayList<String> SearchJanCd				= new ArrayList<String>();			//ソースマーク_BCD（バラ）
			ArrayList<String> SearchItemMdNo			= new ArrayList<String>();			//商品型番
			boolean LocExactMatch = false;													//ロケーション完全一致
			boolean AllSearch = false;														//全件検索
			boolean SortItemcdMode = false;													//商品CDでソート
			
			if(!"".equals(TgtWhCd		)) {SearchWhCd.add(				TgtWhCd);}
			if(!"".equals(TgtClCd		)) {SearchClCd.add(				TgtClCd);}
			if(!"".equals(TgtLoc		)) {SearchLoc.add(				TgtLoc);}
			if(!"".equals(TgtItemCd		)) {SearchItemCd.add(			TgtItemCd);}
			if(!"".equals(TgtLot		)) {SearchLot.add(				TgtLot);}
			if(!"".equals(TgtExpdate	)) {SearchExpdateMin.add(		TgtExpdate);}
			if(!"".equals(TgtActualDate	)) {SearchActualDateMin.add(	TgtActualDate);}
			if(!"".equals(TgtExpdate	)) {SearchExpdateMax.add(		TgtExpdate);}
			if(!"".equals(TgtActualDate	)) {SearchActualDateMax.add(	TgtActualDate);}
			
			Object[][] StockRt= T00030StockRt.StockRt(
					SearchClCd,				//荷主コード
					SearchWhCd,				//倉庫コード
					SearchClGpCD,			//荷主グループCD
					SearchLoc,				//ロケーション
					SearchType,				//ロケタイプ　0:通常　1:保管　8:入荷時　9:引当禁止
					SearchItemCd,			//商品コード
					SearchLot,				//ロット
					SearchExpdateMin,		//消費期限最小
					SearchExpdateMax,		//消費期限最大
					SearchActualDateMin,	//入荷実績日最小
					SearchActualDateMax,	//入荷実績日最大
					SearchQtyMin,			//数量最小
					SearchQtyMax,			//数量最大
					SearchShipPlanQtyMin,	//引当済数最小
					SearchShipPlanQtyMax,	//引当済数最大
					SearchPossibleQtyMin,	//出荷可能数最小
					SearchPossibleQtyMax,	//出荷可能数最大
					SearchItemName,			//商品名
					SearchClItemCd,			//荷主商品コード
					SearchJanCd,			//ソースマーク_BCD（バラ）
					SearchItemMdNo,			//商品型番
					LocExactMatch,			//ロケーション完全一致
					AllSearch,
					SortItemcdMode);
			if(1==StockRt.length) {
				GetClCd				= (String)StockRt[0][T00030StockRt.ColClCd];			//荷主コード
				GetCLName			= (String)StockRt[0][T00030StockRt.ColCLName];			//荷主名1
				GetWhCd				= (String)StockRt[0][T00030StockRt.ColWhCd];			//倉庫コード
				GetClWHName			= (String)StockRt[0][T00030StockRt.ColClWHName];		//担当倉庫名
				GetClGpCD			= (String)StockRt[0][T00030StockRt.ColClGpCD];			//荷主グループCD
				GetClGpName			= (String)StockRt[0][T00030StockRt.ColClGpName];		//グループ名1
				GetLoc				= (String)StockRt[0][T00030StockRt.ColLoc];			//ロケーション
				GetLocName			= (String)StockRt[0][T00030StockRt.ColLocName];		//ロケーション名
				GetType				= (int)StockRt[0][T00030StockRt.ColType];				//ロケタイプ
				GetItemCd			= (String)StockRt[0][T00030StockRt.ColItemCd];			//商品コード
				GetLot				= (String)StockRt[0][T00030StockRt.ColLot];			//ロット
				GetExpdate			= (String)StockRt[0][T00030StockRt.ColExpdate];		//消費期限
				GetActualDate		= (String)StockRt[0][T00030StockRt.ColActualDate];	//入荷実績日
				GetQty				= (int)StockRt[0][T00030StockRt.ColQty];				//総数量
				GetShipPlanQty		= (int)StockRt[0][T00030StockRt.ColShipPlanQty];		//引当済総数
				GetPossibleQty		= (int)StockRt[0][T00030StockRt.ColPossibleQty];		//出荷可能総数
				GetItemName			= (String)StockRt[0][T00030StockRt.ColItemName];		//商品名
				GetItemName01		= (String)StockRt[0][T00030StockRt.ColItemName01];	//商品名1
				GetItemName02		= (String)StockRt[0][T00030StockRt.ColItemName02];	//商品名2
				GetItemName03		= (String)StockRt[0][T00030StockRt.ColItemName03];	//商品名3
				GetClItemCd			= (String)StockRt[0][T00030StockRt.ColClItemCd];		//荷主商品コード
				GetJanCd			= (String)StockRt[0][T00030StockRt.ColJanCd];			//ソースマーク_BCD（バラ）
				GetItemMdNo			= (String)StockRt[0][T00030StockRt.ColItemMdNo];		//商品型番
				GetCtUnitQty		= (int)StockRt[0][T00030StockRt.ColCtUnitQty];		//カートン入数
				GetCsUnitQty		= (int)StockRt[0][T00030StockRt.ColCsUnitQty];		//ケース入数
				GetPlUnitQty		= (int)StockRt[0][T00030StockRt.ColPlUnitQty];		//パレット入数
				GetUnitName			= (String)StockRt[0][T00030StockRt.ColUnitName];		//商品単位
				GetCtUnitName		= (String)StockRt[0][T00030StockRt.ColCtUnitName];	//カートン商品単位
				GetCsUnitName		= (String)StockRt[0][T00030StockRt.ColCsUnitName];	//ケース商品単位
				GetPlUnitName		= (String)StockRt[0][T00030StockRt.ColPlUnitName];	//パレット商品単位
				GetEntryDate		= (String)StockRt[0][T00030StockRt.ColEntryDate];		//登録日時
				GetUpdateDate		= (String)StockRt[0][T00030StockRt.ColUpdateDate];	//更新日時
				GetEntryUser		= (String)StockRt[0][T00030StockRt.ColEntryUser];		//登録者
				GetUpdateUser		= (String)StockRt[0][T00030StockRt.ColUpdateUser];	//更新者
				GetBrQty			= (int)StockRt[0][T00030StockRt.ColBrQty];				//バラ数量
				GetBrShipPlanQty	= (int)StockRt[0][T00030StockRt.ColBrShipPlanQty];	//引当済バラ数
				GetBrPossibleQty	= (int)StockRt[0][T00030StockRt.ColBrPossibleQty];	//出荷可能バラ数
				GetCtQty			= (int)StockRt[0][T00030StockRt.ColCtQty];				//カートン数量
				GetCtShipPlanQty	= (int)StockRt[0][T00030StockRt.ColCtShipPlanQty];	//引当済カートン数
				GetCtPossibleQty	= (int)StockRt[0][T00030StockRt.ColCtPossibleQty];	//出荷可能カートン数
				GetCsQty			= (int)StockRt[0][T00030StockRt.ColCsQty];				//ケース数量
				GetCsShipPlanQty	= (int)StockRt[0][T00030StockRt.ColCsShipPlanQty];	//引当済ケース数
				GetCsPossibleQty	= (int)StockRt[0][T00030StockRt.ColCsPossibleQty];	//出荷可能ケース数
				GetPlQty			= (int)StockRt[0][T00030StockRt.ColPlQty];				//パレット数量
				GetPlShipPlanQty	= (int)StockRt[0][T00030StockRt.ColPlShipPlanQty];	//引当済パレット数
				GetPlPossibleQty	= (int)StockRt[0][T00030StockRt.ColPlPossibleQty];	//出荷可能パレット数
				
				MstCheckFg = false;
			}
		}
		if(MstCheckFg) {
			//在庫検索結果で対象特定されていなければ　マスタ情報を引っ張る
			
			//荷主コードを元に荷主情報取得
			Object[][] ClMstRt = ClMstRt(GetClCd);
			if(1==ClMstRt.length) {
				GetClCd			=(String)ClMstRt[0][M00011ClMstRt.Colcl_cd];		//荷主CD
				GetCLName		=(String)ClMstRt[0][M00011ClMstRt.ColCLName01];	//荷主名1
				GetClGpCD		=(String)ClMstRt[0][M00011ClMstRt.ColClGpCD];		//荷主グループCD
				GetClGpName		=(String)ClMstRt[0][M00011ClMstRt.ColClGpName];	//グループ名1
			}
			
			//商品マスタ
			
			
			
			
		}
		/******************************************
			現在の在庫情報を取得するここまで
		******************************************/
		
		final JFrame main_fm = B00110FrameParts.FrameCreate(x,y,1200,750,"Corgi00在庫調整","ZK");
		JLabel userinfo = B00110FrameParts.UserInfo();
		JButton exit_btn = B00110FrameParts.ExitBtn();
		JButton entry_btn = B00110FrameParts.EntryBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		
		
		
		
		TB_ClCd.setSelectedIndex(	GetSelectIndex(B00100DefaultVariable.ClList[1]	,GetClCd ) );		//荷主コード
		TB_WhCd.setSelectedIndex(	GetSelectIndex(B00100DefaultVariable.ClList[1]	,GetWhCd ) );		//倉庫コード
		TB_LocType.setSelectedIndex(GetSelectIndex(B00100DefaultVariable.LocType[1]	,GetType+"" ) );	//ロケータイプ
		TB_ClCd.setEnabled(false);		//荷主コード
		TB_WhCd.setEnabled(false);		//倉庫コード
		
		
		
		
		RenewFg = true;
		main_fm.setVisible(true);
		
		//EXITボタン押下時の挙動
		exit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				WT0001010StockSearch.StockSearch(0,0);
			}
		});
	}
	
	
	private static Object ItemMstRt() {
		ArrayList<String> SearchClGpCd 				= new ArrayList<String>();	//荷主グループコード
		ArrayList<String> SearchItemCd 				= new ArrayList<String>();	//商品コード
		ArrayList<String> SearchCLItemCd 			= new ArrayList<String>();	//荷主商品コード
		ArrayList<String> SearchItemName 			= new ArrayList<String>();	//商品名
		ArrayList<String> SearchDeliveryTypeCd01 	= new ArrayList<String>();	//運送タイプコード01
		ArrayList<String> SearchDeliveryTypeCd02 	= new ArrayList<String>();	//運送タイプコード02
		ArrayList<String> SearchDeliveryTypeCd03 	= new ArrayList<String>();	//運送タイプコード03
		ArrayList<String> SearchDeliveryTypeCd04 	= new ArrayList<String>();	//運送タイプコード04
		ArrayList<String> SearchDeliveryTypeCd05 	= new ArrayList<String>();	//運送タイプコード05
		ArrayList<String> SearchItemMDNo 			= new ArrayList<String>();	//商品モデル番号（型番）
		ArrayList<String> SearchCategoryCd 			= new ArrayList<String>();	//商品カテゴリCD
		ArrayList<String> SearchCategoryName 		= new ArrayList<String>();	//商品カテゴリ名
		ArrayList<String> SearchItemColorCd 		= new ArrayList<String>();	//商品カラーコード
		ArrayList<String> SearchItemColorName 		= new ArrayList<String>();	//商品カラー名
		ArrayList<String> SearchItemSizeCd 			= new ArrayList<String>();	//商品サイズコード
		ArrayList<String> SearchItemSizeName 		= new ArrayList<String>();	//商品サイズ名
		ArrayList<String> SearchJanCd 				= new ArrayList<String>();	//JANCD
		ArrayList<String> SearchTildFG 				= new ArrayList<String>();	//温度区分
		ArrayList<String> SearchTildName 			= new ArrayList<String>();	//温度区分名
		ArrayList<String> SearchDelFg 				= new ArrayList<String>();	//削除フラグ
		boolean AllSearch = false;
		
		Object[][] ItemMstRt = M00070ItemMstRt.ItemMstRt(
				SearchClGpCd,			//荷主グループコード
				SearchItemCd,			//商品コード
				SearchCLItemCd,			//荷主商品コード
				SearchItemName,			//商品名
				SearchDeliveryTypeCd01,	//運送タイプコード01
				SearchDeliveryTypeCd02,	//運送タイプコード02
				SearchDeliveryTypeCd03,	//運送タイプコード03
				SearchDeliveryTypeCd04,	//運送タイプコード04
				SearchDeliveryTypeCd05,	//運送タイプコード05
				SearchItemMDNo,			//商品モデル番号（型番）
				SearchCategoryCd,		//商品カテゴリCD
				SearchCategoryName,		//商品カテゴリ名
				SearchItemColorCd,		//商品カラーコード
				SearchItemColorName,	//商品カラー名
				SearchItemSizeCd,		//商品サイズコード
				SearchItemSizeName,		//商品サイズ名
				SearchJanCd,			//JANCD
				SearchTildFG,			//温度区分
				SearchTildName,			//温度区分名
				SearchDelFg,			//削除フラグ
				AllSearch);
		
		return ItemMstRt;
	}
	
	private static Object[][] ClMstRt(String TgtClCd){
		ArrayList<String> SearchClGpCD = new ArrayList<String>();
		ArrayList<String> SearchCLCD = new ArrayList<String>();
		ArrayList<String> SearchCLName = new ArrayList<String>();
		ArrayList<String> SearchPost = new ArrayList<String>();
		ArrayList<String> searchAdd = new ArrayList<String>();
		ArrayList<String> SearchTel = new ArrayList<String>();
		ArrayList<String> SearchFax = new ArrayList<String>();
		ArrayList<String> SearchMail = new ArrayList<String>();
		ArrayList<String> SearchCom = new ArrayList<String>();
		ArrayList<String> SearchWHCD = new ArrayList<String>();
		boolean AllSearch = false;
		
		SearchCLCD.add(TgtClCd);
		
		Object[][] ClMstRt = M00011ClMstRt.ClMstRt(
			SearchClGpCD,
			SearchCLCD,
			SearchCLName,
			SearchPost,
			searchAdd,
			SearchTel,
			SearchFax,
			SearchMail,
			SearchCom,
			SearchWHCD,
			AllSearch);
		return ClMstRt;
	}
	
	private static int GetSelectIndex(String[] SelectList,String TgtData ) {
		int rt = 0;
		for(int i=0;i<SelectList.length;i++) {
			if(TgtData.equals(SelectList[i])) {
				rt	= i;
				i	= SelectList.length+1;
			}
		}
		return rt;
	}
}