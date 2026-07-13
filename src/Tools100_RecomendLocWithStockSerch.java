import java.util.ArrayList;

public class Tools100_RecomendLocWithStockSerch{
	
	static final int ColItemCd 				= 0;
	static final int ColLot 					= 1;
	static final int ColExpdate 				= 2;
	static final int ColRecomendLoc 			= 3;
	static final int ColRecomendLocName 		= 4;
	static final int ColWhCd 					= 5;
	static final int ColClCd 					= 6;
	static final int ColClGp 					= 7;
	static final int ColMsg 					= 8;
	static final int ColMstRecomendLoc 		= 9;
	static final int ColMstRecomendLocName 	=10;
	
	static final int InColItemCd 			= 0;
	static final int InColLot 				= 1;
	static final int InColExpdate 		= 2;
	
	//対象倉庫・荷主　対象商品一覧を受け取って推奨ロケを返却する
	//対象商品一覧と同じ行数の配列で返却します
	/*
	推奨ロケ返却する優先順位は
	1.ドッペルゲンガーが保管ロケにいる
				同一商品・同一ロット・同一賞味の在庫が保管ロケにある場合当該ロケ中のもっとも値の小さいロケを推奨
	2.ドッペルゲンガーが出荷ロケにいる
				同一商品・同一ロット・同一賞味の在庫が出荷ロケにある場合当該ロケ中のもっとも値の小さいロケを推奨
	3.親友が保管ロケにいる
				同一商品・同一賞味の在庫が保管ロケにある場合当該ロケ中のもっとも値の小さいロケを推奨
	4.親友が出荷ロケにいる
				同一商品・同一賞味の在庫が出荷ロケにある場合当該ロケ中のもっとも値の小さいロケを推奨
	5.友人が保管ロケにいる
				同一商品が保管ロケにある場合当該ロケ中のもっとも値の小さいロケを推奨
	6.友人が出荷ロケにいる
				同一商品が出荷ロケにある場合当該ロケ中のもっとも値の小さいロケを推奨
	7.自治体が居住地推奨
				WW00630ItemRecomendLocにてその荷主指定の推奨ロケが設定されている
	8.国が居住地推奨し自治体が居住地準備してる
				KM0061_ITEMMSTSUBにてその荷主グループ指定の推奨ロケが設定されていて、当該荷主にそのロケが存在する
	9.完全に異邦人
				どれにも当てはまらない場合、空白文字列を返却
	
	*/
	public static Object[] RtRecomendLocWithStockSerch() {
		//返却情報定義
		Object[][] Rt = {
				 {"ItemCd"				,ColItemCd					,"String"	,"商品CD"			,""}
				,{"Lot"					,ColLot 					,"String"	,"ロット"			,""}
				,{"Expdate"				,ColExpdate 				,"String"	,"賞味期限"			,""}
				,{"RecomendLoc"			,ColRecomendLoc 			,"String"	,"推奨ロケ"			,""}
				,{"RecomendLocName"		,ColRecomendLocName 		,"String"	,"推奨ロケ名"		,""}
				,{"WhCd"				,ColWhCd 					,"String"	,"倉庫CD"			,""}
				,{"ClCd"				,ColClCd 					,"String"	,"荷主CD"			,""}
				,{"ClGp"				,ColClGp 					,"String"	,"荷主グループ"		,""}
				,{"Msg"					,ColMsg 					,"String"	,"推奨メッセージ"	,""}
				,{"MstRecomendLoc"		,ColMstRecomendLoc 		,"String"	,"マスタ推奨ロケ"	,""}
				,{"MstRecomendLocName"	,ColMstRecomendLocName 	,"String"	,"マスタ推奨ロケ名"	,""}
				};
		return Rt;
	}
	
	public static Object[] InRecomendLocWithStockSerch() {
		//投入情報定義
		Object[][] Rt = {
				 {"ItemCd"			,InColItemCd 		,"String"	,"商品CD"	,""}
				,{"Lot"				,InColLot 			,"Date"		,"ロット"	,""}	//※YYYY/MM/DD　文字列
				,{"Expdate"			,InColExpdate 	,"Date"		,"賞味期限"	,""}	//※YYYY/MM/DD　文字列
				};
		return Rt;
	}
	
	public static String[][] RecomendLocWithStockSerch(String TgtWhCd,String TgtClCd,String[][] TgtItemCd ) {
		//対象倉庫・荷主　対象商品一覧を受け取って推奨ロケを返却する
		String[][] rt = new String[0][RtRecomendLocWithStockSerch().length];
		
		if(null==TgtWhCd||"".equals(TgtWhCd)) {TgtWhCd	=	A00000_Main.ClWh;}
		if(null==TgtClCd||"".equals(TgtClCd)) {TgtClCd	=	A00000_Main.ClCd;}
		String TgtClGp	= "";
		
		
		Object[][] ClMstRt	= ClMstRt(TgtClCd);
		if(0<ClMstRt.length) {TgtClGp = (String)ClMstRt[0][M100_ClMstRt.ColClGpCD];}
		
		if(null!=TgtItemCd && 0<TgtItemCd.length) {
			rt = new String[TgtItemCd.length][RtRecomendLocWithStockSerch().length];
			for(int i=0;i<TgtItemCd.length;i++) {
				if(null==TgtItemCd[i][InColItemCd]		) {TgtItemCd[i][InColItemCd]	= "";}
				if(null==TgtItemCd[i][InColLot]		) {TgtItemCd[i][InColLot]		= "";}
				if(null==TgtItemCd[i][InColExpdate]	) {TgtItemCd[i][InColExpdate]	= "";}
			}
			
			Object[][] StockRt				= StockRt(TgtWhCd,TgtClCd,TgtItemCd);
			Object[][] ItemRecomendLocMstRt	= ItemRecomendLocMstRt(TgtWhCd,TgtClCd,TgtItemCd);
			Object[][] ItemMstRt			= ItemMstRt(TgtClGp,TgtItemCd);
			
			for(int i=0;i<TgtItemCd.length;i++) {
				rt[i][ColItemCd]				= TgtItemCd[i][InColItemCd];
				rt[i][ColLot]					= TgtItemCd[i][InColLot];
				rt[i][ColExpdate]				= TgtItemCd[i][InColExpdate];
				rt[i][ColRecomendLoc]			= "";
				rt[i][ColRecomendLocName]	= "";
				rt[i][ColWhCd]					= TgtWhCd;
				rt[i][ColClCd]					= TgtClCd;
				rt[i][ColClGp]					= TgtClGp;
				rt[i][ColMsg]					= "";
				rt[i][ColMstRecomendLoc]		= "";
				rt[i][ColMstRecomendLocName]	= "";
				
				boolean KickFg	= true;
				int HitNo=-1;
				if(KickFg) {
					//1.ドッペルゲンガーが保管ロケにいる
					for(int i01=0;i01<StockRt.length;i01++) {
						if(rt[i][ColItemCd].equals((String)StockRt[i01][T100_StockRt.ColItemCd])
							&& rt[i][ColLot].equals((String)StockRt[i01][T100_StockRt.ColLot])
							&& rt[i][ColExpdate].equals((String)StockRt[i01][T100_StockRt.ColExpdate])
							&& 1 == (int)StockRt[i01][T100_StockRt.ColType]
							) {
							rt[i][ColRecomendLoc]			= (String)StockRt[i01][T100_StockRt.ColLoc];
							rt[i][ColRecomendLocName]	= (String)StockRt[i01][T100_StockRt.ColLocName];
							rt[i][ColMsg] = "保管ロケにドッペル";
							i01	= StockRt.length;
							KickFg	= false;
						}
					}

				}
				if(KickFg) {
					//2.ドッペルゲンガーが出荷ロケにいる
					for(int i01=0;i01<StockRt.length;i01++) {
						if(rt[i][ColItemCd].equals((String)StockRt[i01][T100_StockRt.ColItemCd])
							&& rt[i][ColLot].equals((String)StockRt[i01][T100_StockRt.ColLot])
							&& rt[i][ColExpdate].equals((String)StockRt[i01][T100_StockRt.ColExpdate])
							&& 0 == (int)StockRt[i01][T100_StockRt.ColType]
							) {
							rt[i][ColRecomendLoc]			= (String)StockRt[i01][T100_StockRt.ColLoc];
							rt[i][ColRecomendLocName]	= (String)StockRt[i01][T100_StockRt.ColLocName];
							rt[i][ColMsg] = "出荷ロケにドッペル";
							i01	= StockRt.length;
							KickFg	= false;
						}
					}
				}
				if(KickFg) {
					//3.親友が保管ロケにいる
					for(int i01=0;i01<StockRt.length;i01++) {
						if(rt[i][ColItemCd].equals((String)StockRt[i01][T100_StockRt.ColItemCd])
							&& rt[i][ColExpdate].equals((String)StockRt[i01][T100_StockRt.ColExpdate])
							&& 1 == (int)StockRt[i01][T100_StockRt.ColType]
							) {
							rt[i][ColRecomendLoc]			= (String)StockRt[i01][T100_StockRt.ColLoc];
							rt[i][ColRecomendLocName]	= (String)StockRt[i01][T100_StockRt.ColLocName];
							rt[i][ColMsg] = "保管ロケに親友";
							i01	= StockRt.length;
							KickFg	= false;
						}
					}
				}
				if(KickFg) {
					//4.親友が出荷ロケにいる
					for(int i01=0;i01<StockRt.length;i01++) {
						if(rt[i][ColItemCd].equals((String)StockRt[i01][T100_StockRt.ColItemCd])
							&& rt[i][ColExpdate].equals((String)StockRt[i01][T100_StockRt.ColExpdate])
							&& 0 == (int)StockRt[i01][T100_StockRt.ColType]
							) {
							rt[i][ColRecomendLoc]			= (String)StockRt[i01][T100_StockRt.ColLoc];
							rt[i][ColRecomendLocName]	= (String)StockRt[i01][T100_StockRt.ColLocName];
							rt[i][ColMsg] = "出荷ロケに親友";
							i01	= StockRt.length;
							KickFg	= false;
						}
					}
				}
				if(KickFg) {
					//5.友人が保管ロケにいる
					for(int i01=0;i01<StockRt.length;i01++) {
						if(rt[i][ColItemCd].equals((String)StockRt[i01][T100_StockRt.ColItemCd])
							&& 1 == (int)StockRt[i01][T100_StockRt.ColType]
							) {
							rt[i][ColRecomendLoc]			= (String)StockRt[i01][T100_StockRt.ColLoc];
							rt[i][ColRecomendLocName]	= (String)StockRt[i01][T100_StockRt.ColLocName];
							rt[i][ColMsg] = "保管ロケに友人";
							i01	= StockRt.length;
							KickFg	= false;
						}
					}
				}
				if(KickFg) {
					//6.友人が出荷ロケにいる
					for(int i01=0;i01<StockRt.length;i01++) {
						if(rt[i][ColItemCd].equals((String)StockRt[i01][T100_StockRt.ColItemCd])
							&& 0 == (int)StockRt[i01][T100_StockRt.ColType]
							) {
							rt[i][ColRecomendLoc]			= (String)StockRt[i01][T100_StockRt.ColLoc];
							rt[i][ColRecomendLocName]	= (String)StockRt[i01][T100_StockRt.ColLocName];
							rt[i][ColMsg] = "出荷ロケに友人";
							i01	= StockRt.length;
							KickFg	= false;
						}
					}
				}
				//以下はマスタ由来の推奨ロケを格納　ロケーションマスタに存在し、かつ在庫由来の推奨ロケが格納されていない場合、後ほど推奨ロケにも値格納
				//7.自治体が居住地推奨
				boolean ClUnRecomendFg = true;
				for(int i01=0;i01<ItemRecomendLocMstRt.length;i01++) {
					if(rt[i][ColItemCd].equals((String)ItemRecomendLocMstRt[i01][M100_ItemRecomendLocMstRt.ColItemCd])) {
						rt[i][ColMstRecomendLoc]		= (String)ItemRecomendLocMstRt[i01][M100_ItemRecomendLocMstRt.ColRecomendLoc];
						rt[i][ColMstRecomendLocName]	= (String)ItemRecomendLocMstRt[i01][M100_ItemRecomendLocMstRt.ColLocName];
						ClUnRecomendFg = false;
						if(KickFg) {
							rt[i][ColMsg] = "荷主個別マスタで推奨";
							i01	= ItemRecomendLocMstRt.length;
							KickFg	= false;
						}
					}
				}
				//8.国が居住地推奨してる
				if(ClUnRecomendFg) {
					for(int i01=0;i01<ItemMstRt.length;i01++) {
						if(rt[i][ColItemCd].equals((String)ItemMstRt[i01][M100_ItemMstRt.ColItemCd])) {
							rt[i][ColMstRecomendLoc]		= (String)ItemMstRt[i01][M100_ItemMstRt.ColRecomendLoc];
							rt[i][ColMstRecomendLocName]	= "";
							if(KickFg) {
								rt[i][ColMsg] = "荷主グループで推奨";
								i01	= ItemMstRt.length;
								KickFg	= false;
							}
						}
					}
				}
			}
			//推奨ロケがロケーションマスタに存在しなければ空白で置換
			rt	= LocCheck(TgtWhCd,TgtClCd,rt);
		}
		
		return rt;
	}
	
	private static String[][] LocCheck(String TgtWhCd,String TgtClCd,String[][] CheckData) {
		//マスタで指定したロケは別荷主コードのロケが推奨されている可能性があるのでチェック
		ArrayList<String> SearchClCd 	= new ArrayList<String>();	//荷主コード
		ArrayList<String> SearchWhCd 	= new ArrayList<String>();	//倉庫コード
		ArrayList<String> SearchLoc 	= new ArrayList<String>();	//ロケーション
		ArrayList<String> SearchLocName = new ArrayList<String>();	//ロケーション名
		ArrayList<String> SearchType 	= new ArrayList<String>();	//ロケタイプ
		boolean LocExactMatch = true;	//ロケーション完全一致
		boolean AllSearch = false;
		
		SearchClCd.add(TgtClCd);
		SearchWhCd.add(TgtWhCd);
		
		for(int i=0;i<CheckData.length;i++) {
			if(!"".equals(CheckData[i][ColMstRecomendLoc])) {
				SearchLoc.add(CheckData[i][ColMstRecomendLoc]);
			}
		}
		
		Object[][] LocationMstRt = M100_LocationMstRt.LocationMstRt(
				SearchClCd,		//荷主コード
				SearchWhCd,		//倉庫コード
				SearchLoc,		//ロケーション
				SearchLocName,	//ロケーション名
				SearchType,		//ロケタイプ
				LocExactMatch,	//ロケーション完全一致
				AllSearch);
		
		
		for(int i=0;i<CheckData.length;i++) {
			if(!"".equals(CheckData[i][ColMstRecomendLoc])) {
				boolean UnHitFg = true;
				for(int i01=0;i01<LocationMstRt.length;i01++) {
					if(CheckData[i][ColMstRecomendLoc].equals((String)LocationMstRt[i01][M100_LocationMstRt.ColLoc])) {
						CheckData[i][ColMstRecomendLocName]	= (String)LocationMstRt[i01][M100_LocationMstRt.ColLocName];
						UnHitFg = false;
					}
				}
				if(UnHitFg) {
					CheckData[i][ColMstRecomendLoc]		= "";
					CheckData[i][ColMstRecomendLocName]	= "";
					if("".equals(CheckData[i][ColRecomendLoc])) {
						CheckData[i][ColMsg] = "";
					}
				}
				if("".equals(CheckData[i][ColRecomendLoc])) {
					CheckData[i][ColRecomendLoc]		= CheckData[i][ColMstRecomendLoc];
					CheckData[i][ColRecomendLocName]	= CheckData[i][ColMstRecomendLocName];
				}
			}
		}
		
		return CheckData;
	}
	
	
	private static Object[][] ItemMstRt(String TgtClGp,String[][] TgtItemCd){
		Object[][] ItemMstRt	= new Object[0][0];
		
		if(!"".equals(TgtClGp)&&(null!=TgtItemCd && 0<TgtItemCd.length)) {
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
			
			SearchClGpCd.add(TgtClGp);
			
			for(int i=0;i<TgtItemCd.length;i++) {
				SearchItemCd.add(TgtItemCd[i][InColItemCd]);
			}
			
			ItemMstRt = M100_ItemMstRt.ItemMstRt(
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
			}
		return ItemMstRt;
	}
	
	
	private static Object[][] ItemRecomendLocMstRt(String TgtWhCd,String TgtClCd,String[][] TgtItemCd){
		Object[][] ItemRecomendLocMstRt	= new Object[0][0];
		
		if(!"".equals(TgtWhCd)&&!"".equals(TgtClCd)&&(null!=TgtItemCd && 0<TgtItemCd.length)) {
			ArrayList<String> SearchClCd		= new ArrayList<String>();	//荷主コード
			ArrayList<String> SearchClWh		= new ArrayList<String>();	//担当倉庫コード
			ArrayList<String> SearchClGpCD		= new ArrayList<String>();	//荷主グループCD
			ArrayList<String> SearchItemCd		= new ArrayList<String>();	//商品コード
			ArrayList<String> SearchItemName01	= new ArrayList<String>();	//商品表記名
			ArrayList<String> SearchRecomendLoc	= new ArrayList<String>();	//推奨ロケ
			ArrayList<String> SearchLocName		= new ArrayList<String>();	//ロケーション名
			ArrayList<Integer> SearchType		= new ArrayList<Integer>();	//ロケタイプ　0:通常　1:保管　8:入荷時　9:引当禁止
			boolean LocExactMatch = true;									//ロケーション完全一致
			boolean AllSearch = false;
			
			SearchClWh.add(TgtWhCd);
			SearchClCd.add(TgtClCd);
			
			for(int i=0;i<TgtItemCd.length;i++) {
				SearchItemCd.add(TgtItemCd[i][InColItemCd]);
			}
			
			ItemRecomendLocMstRt	= M100_ItemRecomendLocMstRt.ItemRecomendLocMstRt(
					SearchClCd,			//荷主コード
					SearchClWh,			//担当倉庫コード
					SearchClGpCD,		//荷主グループCD
					SearchItemCd,		//商品コード
					SearchItemName01,	//商品表記名
					SearchRecomendLoc,	//推奨ロケ
					SearchLocName,		//ロケーション名
					SearchType,			//ロケタイプ　0:通常　1:保管　8:入荷時　9:引当禁止
					LocExactMatch,		//ロケーション完全一致
					AllSearch);
		}
		
		return ItemRecomendLocMstRt;
	}
	
	private static Object[][] StockRt(String TgtWhCd,String TgtClCd,String[][] TgtItemCd){
		Object[][] StockRt = new Object[0][0];
		
		if(!"".equals(TgtWhCd)&&!"".equals(TgtClCd)&&(null!=TgtItemCd && 0<TgtItemCd.length)) {
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
			
			SearchType.add(0);	//出荷ロケと保管ロケに絞る
			SearchType.add(1);	//出荷ロケと保管ロケに絞る
			
			SearchWhCd.add(TgtWhCd);
			SearchClCd.add(TgtClCd);
			
			for(int i=0;i<TgtItemCd.length;i++) {
				SearchItemCd.add(TgtItemCd[i][InColItemCd]);
			}
	
			StockRt= T100_StockRt.StockRt(
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
		}
		return StockRt;
	}
	
	private static Object[][] ClMstRt(String ClCd){
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
		
		SearchCLCD.add(ClCd);
		
		Object[][] ClMstRt = M100_ClMstRt.ClMstRt(
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
}