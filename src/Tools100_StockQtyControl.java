import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Tools100_StockQtyControl{
	//対象ロケ、変動させる数量を引き渡して在庫数の増減をする
	//コピペ用
	/*
	Object[][] SetData = new Object[1][Tools100_StockQtyControl.StockQtyControlDataLayout().length];
	
	SetData[0][Tools100_StockQtyControl.ColClCd] 		= A00000_Main.ClCd;
	SetData[0][Tools100_StockQtyControl.ColWhCd] 		= A00000_Main.ClWh;
	SetData[0][Tools100_StockQtyControl.ColLoc]			= TgtLoc;
	SetData[0][Tools100_StockQtyControl.ColItemCd]		= TgtItem;
	SetData[0][Tools100_StockQtyControl.ColLot] 		= TgtLot;
	SetData[0][Tools100_StockQtyControl.ColExpdate] 	= TgtExpdate;
	SetData[0][Tools100_StockQtyControl.ColActualDate] 	= TgtActualDate;
	SetData[0][Tools100_StockQtyControl.ColControlQty] 	= -100;
	
	Object[][] StockQtyControlErr = Tools100_StockQtyControl.StockQtyControl(SetData) ;
	
	if("EntryData".equals((String)StockQtyControlErr[i][Tools100_StockQtyControl.RtColErrType])){
		int EntryRow			= (int)StockQtyControlErr[i][Tools100_StockQtyControl.RtColErrRow];
		int BeforeQty			= (int)StockQtyControlErr[i][Tools100_StockQtyControl.RtColBeforeQty];
		int BeforeShipPlanQty	= (int)StockQtyControlErr[i][Tools100_StockQtyControl.RtColBeforeShipPlanQty];
		int BeforePossibleQty	= (int)StockQtyControlErr[i][Tools100_StockQtyControl.RtColBeforePossibleQty];
		int AdjustQty			= (int)StockQtyControlErr[i][Tools100_StockQtyControl.RtColAdjustQty];
		int AfterQty			= (int)StockQtyControlErr[i][Tools100_StockQtyControl.RtColAfterQty];
		int AfterShipPlanQtyQty	= (int)StockQtyControlErr[i][Tools100_StockQtyControl.RtColAfterShipPlanQty];
		int AfterPossibleQtyQty	= (int)StockQtyControlErr[i][Tools100_StockQtyControl.RtColAfterPossibleQty];
	}else{
		String 	ErrType = (String)StockQtyControlErr[i][Tools100_StockQtyControl.RtColErrType];
		String 	ErrVol 	= (String)StockQtyControlErr[i][Tools100_StockQtyControl.RtColErrVol];
		int 	ErrRow	= (int)StockQtyControlErr[i][Tools100_StockQtyControl.RtColErrRow];
	}
	*/
	
	/*----------------------------------------------------------------------------------------------------------------------------
	投入データに対してエラータイプ　エラーになった投入データの値　投入行番号　の配列からなるエラー情報を返却する
	※エラー発生時は当該情報は在庫に影響を及ぼしません
	"NoLocErr"		：ロケーションマスタにないデータを投入した
	"NoItemErr"		：商品マスタにないデータを投入した
	"OverQtyErr"	：マイナス在庫が発生するデータを投入した
	"EntryData"		：登録成功
	----------------------------------------------------------------------------------------------------------------------------*/
	
	public static Object[][] StockQtyControlDataLayout() {
		Object[][] Layout = {
							 {"ClCd"		,ColClCd			,"String"	,"荷主コード"	,""}
							,{"WhCd"		,ColWhCd			,"String"	,"倉庫コード"	,""}
							,{"Loc"			,ColLoc			,"String"	,"ロケーション"	,""}
							,{"ItemCd"		,ColItemCd			,"String"	,"商品コード"	,""}
							,{"Lot"			,ColLot			,"String"	,"ロット"		,""}
							,{"Expdate"		,ColExpdate		,"Date"		,"消費期限"		,""}
							,{"ActualDate"	,ColActualDate	,"Date"		,"入荷実績日"	,""}
							,{"ControlQty"	,ColControlQty	,"int"		,"調整数"		,""}
							};
		return Layout;
	}
	
	public static Object[][] RtStockQtyControl(){
		Object[][] Layout = {
							 {"ErrType"				,RtColErrType				,"String"	,"データ種別"		,""}
							,{"ErrVol"				,RtColErrVol				,"String"	,"エラー事由値"		,""}
							,{"ErrRow"				,RtColErrRow				,"int"		,"エラー行番号"		,""}
							,{"BeforeQty"			,RtColBeforeQty 			,"int"		,"調整前総数"		,""}
							,{"BeforeShipPlanQty"	,RtColBeforeShipPlanQty	,"int"		,"調整前引当済数"	,""}
							,{"BeforePossibleQty"	,RtColBeforePossibleQty	,"int"		,"調整前出荷可能数"	,""}
							,{"AdjustQty"			,RtColAdjustQty			,"int"		,"調整数"			,""}
							,{"AfterQty"			,RtColAfterQty			,"int"		,"調整後総数"		,""}
							,{"AfterShipPlanQty"	,RtColAfterShipPlanQty	,"int"		,"調整後引当済数"	,""}
							,{"AfterPossibleQty"	,RtColAfterPossibleQty	,"int"		,"調整後出荷可能数"	,""}
							};
		return Layout;
	}
	
	
	
	static final int ColClCd			= 0;
	static final int ColWhCd			= 1;
	static final int ColLoc			= 2;
	static final int ColItemCd			= 3;
	static final int ColLot			= 4;
	static final int ColExpdate		= 5;
	static final int ColActualDate	= 6;
	static final int ColControlQty	= 7;
	
	static final int LocSearchClCd	= 1;
	static final int LocSearchWhCd	= 2;
	static final int LocSearchLoc		= 3;
	
	static final int ItemSearchClCd 			= 1;
	static final int ItemSearchItemCd 		= 2;
	
	static final int StockSearchClCd			= 1;
	static final int StockSearchWhCd			= 2;
	static final int StockSearchLoc			= 3;
	static final int StockSearchItemCd		= 4;
	static final int StockSearchLot			= 5;
	static final int StockSearchExpdate		= 6;
	static final int StockSearchActualDate	= 7;
	
	static final int StockUpdateAddQty         	= 1;
	static final int StockUpdateAddPossibleQty 	= 2;
	static final int StockUpdateUpdateDate  		= 3;
	static final int StockUpdateUpdateUser  		= 4;
	static final int StockUpdateClCd				= 5;
	static final int StockUpdateWhCd				= 6;
	static final int StockUpdateLoc				= 7;
	static final int StockUpdateItemCd			= 8;
	static final int StockUpdateLot				= 9;
	static final int StockUpdateExpdate			=10;
	static final int StockUpdateActualDate		=11;
	
	static final int StockInsertClCd			= 1;
	static final int StockInsertWhCd			= 2;
	static final int StockInsertLoc			= 3;
	static final int StockInsertItemCd		= 4;
	static final int StockInsertLot			= 5;
	static final int StockInsertExpdate		= 6;
	static final int StockInsertActualDate	= 7;
	static final int StockInsertQty			= 8;
	static final int StockInsertShipPlanQty	= 9;
	static final int StockInsertPossibleQty	=10;
	static final int StockInsertItemName		=11;
	static final int StockInsertClItemCd		=12;
	static final int StockInsertJanCd			=13;
	static final int StockInsertItemMdNo		=14;
	static final int StockInsertEntryDate	=15;
	static final int StockInsertUpdateDate	=16;
	static final int StockInsertEntryUser	=17;
	static final int StockInsertUpdateUser	=18;
	
	static final int RtColErrType				= 0;
	static final int RtColErrVol				= 1;
	static final int RtColErrRow				= 2;
	static final int RtColBeforeQty 			= 3;
	static final int RtColBeforeShipPlanQty	= 4;
	static final int RtColBeforePossibleQty	= 5;
	static final int RtColAdjustQty			= 6;
	static final int RtColAfterQty			= 7;
	static final int RtColAfterShipPlanQty	= 8;
	static final int RtColAfterPossibleQty	= 9;
	
	public static Object[][] StockQtyControl(Object[][] TgtData) {
		Object[][] rt = new Object[0][RtStockQtyControl().length];
		if(null!=TgtData && 0<TgtData.length) {
			String now_dtm = B100_DateTimeControl.dtmString2(B100_DateTimeControl.dtm()[1])[1];
			
			A100_DbConnect.DB_CONN("WANKO");
			PreparedStatement LocMstSearch 	= LocMstSearch();
			PreparedStatement StockSearch 	= StockSearch();
			PreparedStatement ItemMstSearch	= ItemMstSearch();
			PreparedStatement StockUpdate	= StockUpdate();
			PreparedStatement StockInsert	= StockInsert();
			
			ResultSet LocMst 	= null;
			ResultSet ItemMst 	= null;
			ResultSet Stock 	= null;
			
			
			ArrayList<String>  NoLocErr			= new ArrayList<String>();
			ArrayList<Integer> NoLocErrRow		= new ArrayList<Integer>();
			
			ArrayList<String>  NoItemErr		= new ArrayList<String>();
			ArrayList<Integer> NoItemErrRow		= new ArrayList<Integer>();
			
			ArrayList<String>  OverQtyErr		= new ArrayList<String>();
			ArrayList<Integer> OverQtyErrRow	= new ArrayList<Integer>();
			
			ArrayList<Integer> EntryRow					= new ArrayList<Integer>();
			ArrayList<Integer> EntryBeforeQty			= new ArrayList<Integer>();
			ArrayList<Integer> EntryBeforeShipPlanQty	= new ArrayList<Integer>();
			ArrayList<Integer> EntryBeforePossibleQty	= new ArrayList<Integer>();
			ArrayList<Integer> EntryAdjustQty			= new ArrayList<Integer>();
			ArrayList<Integer> EntryAfterQty			= new ArrayList<Integer>();
			ArrayList<Integer> EntryAfterShipPlanQty	= new ArrayList<Integer>();
			ArrayList<Integer> EntryAfterPossibleQty	= new ArrayList<Integer>();
			ArrayList<String>  EntryControlKey			= new ArrayList<String>();
			
			//必須チェック⇒在庫更新
			try {
				for(int i=0;i<TgtData.length;i++) {
					TgtData[i][ColClCd] 		= B100_TextControl.Trim((String)TgtData[i][ColClCd]);
					TgtData[i][ColWhCd] 		= B100_TextControl.Trim((String)TgtData[i][ColWhCd]);
					TgtData[i][ColLoc] 		= B100_TextControl.Trim((String)TgtData[i][ColLoc]);
					TgtData[i][ColItemCd] 		= B100_TextControl.Trim((String)TgtData[i][ColItemCd]);
					TgtData[i][ColLot] 		= B100_TextControl.Trim((String)TgtData[i][ColLot]);
					TgtData[i][ColExpdate] 	= B100_TextControl.TextToDate((String)TgtData[i][ColExpdate]);
					TgtData[i][ColActualDate] = B100_TextControl.TextToDate((String)TgtData[i][ColActualDate]);
				
					int LocType = 0;
					boolean ShipPlovisionUnTgt = false;
					boolean StockHit = false;
				
					//ロケーションマスタ検索
					LocMstSearch.setString(LocSearchClCd	,(String)TgtData[i][ColClCd]);
					LocMstSearch.setString(LocSearchWhCd	,(String)TgtData[i][ColWhCd]);
					LocMstSearch.setString(LocSearchLoc	,(String)TgtData[i][ColLoc]);
					
					LocMst = LocMstSearch.executeQuery();
					boolean LocErr = true;
					
					while (LocMst.next()) {
						LocType = LocMst.getInt("Type");
						LocErr = false;
					}
					
					if(LocErr) {
						NoLocErr.add((String)TgtData[i][ColLoc]);
						NoLocErrRow.add(i);
					}
					
					for(int i01=0;i01<B100_DefaultVariable.ShipPlovisionUnTgtList.length;i01++) {
						if(B100_DefaultVariable.ShipPlovisionUnTgtList[i01].equals(""+LocType)) {
							ShipPlovisionUnTgt	= true;
						}
					}
					
					//増減するロケが引当不可ロケなら出荷可能数に加算しない
					int AddQty 			= (int)TgtData[i][ColControlQty];
					int AddPossibleQty 	= (int)TgtData[i][ColControlQty];
					
					if(ShipPlovisionUnTgt) {
						AddPossibleQty = 0;
					}
					
					//商品検索
					ItemMstSearch.setString(ItemSearchClCd	,(String)TgtData[i][ColClCd]);
					ItemMstSearch.setString(ItemSearchItemCd	,(String)TgtData[i][ColItemCd]);
					
					ItemMst = ItemMstSearch.executeQuery();
					String ItemName = "";
					String ClItemCd = "";
					String JanCd 	= "";
					String ItemMdNo = "";
					boolean ItemErr = true;
					while (ItemMst.next()) {
						ItemName 	= ItemMst.getString("ItemName01");
						ClItemCd 	= ItemMst.getString("ClItemCd");
						JanCd 		= ItemMst.getString("JanCd");
						ItemMdNo 	= ItemMst.getString("ItemMdNo");
						ItemErr = false;
					}
					
					if(ItemErr) {
						NoItemErr.add((String)TgtData[i][ColItemCd]);
						NoItemErrRow.add(i);
					}
					
					//在庫検索
					StockSearch.setString(StockSearchClCd			,(String)TgtData[i][ColClCd]);
					StockSearch.setString(StockSearchWhCd			,(String)TgtData[i][ColWhCd]);
					StockSearch.setString(StockSearchLoc			,(String)TgtData[i][ColLoc]);
					StockSearch.setString(StockSearchItemCd		,(String)TgtData[i][ColItemCd]);
					StockSearch.setString(StockSearchLot			,(String)TgtData[i][ColLot]);
					StockSearch.setString(StockSearchExpdate		,(String)TgtData[i][ColExpdate]);
					StockSearch.setString(StockSearchActualDate	,(String)TgtData[i][ColActualDate]);
					
					Stock = StockSearch.executeQuery();
					int BeforeQty 				= 0;
					int BeforeShipPlanQty 		= 0;
					int BeforePossibleQtyQty 	= 0;
					while (Stock.next()) {
						BeforeQty 				= Stock.getInt("Qty");
						BeforeShipPlanQty		= Stock.getInt("ShipPlanQty");
						BeforePossibleQtyQty 	= Stock.getInt("PossibleQty");
						StockHit	= true;
					}
					
					boolean StockErr = false;
					
					if(0>BeforeQty+AddQty||0>BeforePossibleQtyQty+AddPossibleQty) {
						StockErr = true;
					}
					if(StockErr) {
						OverQtyErr.add(""+AddQty);
						OverQtyErrRow.add(i);
					}
					//エラーがなければ在庫更新or登録
					if(!LocErr&&!ItemErr&&!StockErr) {
						if(StockHit) {
							//在庫があるとき更新
							StockUpdate.setString(StockUpdateAddQty				,""+AddQty);
							StockUpdate.setString(StockUpdateAddPossibleQty		,""+AddPossibleQty);
							StockUpdate.setString(StockUpdateUpdateDate			,now_dtm);
							StockUpdate.setString(StockUpdateUpdateUser			,"(" + A00000_Main.LoginUserId + ")" + A00000_Main.LoginUserName);
							StockUpdate.setString(StockUpdateClCd					,(String)TgtData[i][ColClCd]);
							StockUpdate.setString(StockUpdateWhCd					,(String)TgtData[i][ColWhCd]);
							StockUpdate.setString(StockUpdateLoc					,(String)TgtData[i][ColLoc]);
							StockUpdate.setString(StockUpdateItemCd				,(String)TgtData[i][ColItemCd]);
							StockUpdate.setString(StockUpdateLot					,(String)TgtData[i][ColLot]);
							StockUpdate.setString(StockUpdateExpdate				,(String)TgtData[i][ColExpdate]);
							StockUpdate.setString(StockUpdateActualDate			,(String)TgtData[i][ColActualDate]);
							
							StockUpdate.executeUpdate();
							if(null==A100_DbConnect.session) {
								A100_DbConnect.conn.commit();
							}
						}else {
							//在庫がないとき新規登録
							StockInsert.setString(StockInsertClCd				,(String)TgtData[i][ColClCd]);
							StockInsert.setString(StockInsertWhCd				,(String)TgtData[i][ColWhCd]);
							StockInsert.setString(StockInsertLoc				,(String)TgtData[i][ColLoc]);
							StockInsert.setString(StockInsertItemCd			,(String)TgtData[i][ColItemCd]);
							StockInsert.setString(StockInsertLot				,(String)TgtData[i][ColLot]);
							StockInsert.setString(StockInsertExpdate			,(String)TgtData[i][ColExpdate]);
							StockInsert.setString(StockInsertActualDate		,(String)TgtData[i][ColActualDate]);
							StockInsert.setString(StockInsertQty				,""+AddQty);
							StockInsert.setString(StockInsertShipPlanQty		,"0");
							StockInsert.setString(StockInsertPossibleQty		,""+AddPossibleQty);
							StockInsert.setString(StockInsertItemName		,""+ItemName);
							StockInsert.setString(StockInsertClItemCd		,""+ClItemCd);
							StockInsert.setString(StockInsertJanCd			,""+JanCd);
							StockInsert.setString(StockInsertItemMdNo		,""+ItemMdNo);
							StockInsert.setString(StockInsertEntryDate		,now_dtm);
							StockInsert.setString(StockInsertUpdateDate		,now_dtm);
							StockInsert.setString(StockInsertEntryUser		,"(" + A00000_Main.LoginUserId + ")" + A00000_Main.LoginUserName);
							StockInsert.setString(StockInsertUpdateUser		,"(" + A00000_Main.LoginUserId + ")" + A00000_Main.LoginUserName);
							
							StockInsert.executeUpdate();
							if(null==A100_DbConnect.session) {
								A100_DbConnect.conn.commit();
							}
						}
						int AfterQty = BeforeQty + AddQty;
						EntryRow.add(i);
						EntryBeforeQty.add(BeforeQty);
						EntryBeforeShipPlanQty.add(BeforeShipPlanQty);
						EntryBeforePossibleQty.add(BeforePossibleQtyQty);
						EntryAdjustQty.add(AddQty);
						EntryAfterQty.add(AfterQty);
						EntryAfterShipPlanQty.add(BeforeShipPlanQty);
						EntryAfterPossibleQty.add(BeforePossibleQtyQty+AddPossibleQty);
					}
				}
				LocMst.close();
				ItemMst.close();
				Stock.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					if(null!=LocMst			){LocMst.close();}
					if(null!=ItemMst		){ItemMst.close();}
					if(null!=Stock			){Stock.close();}
					if(null!=LocMstSearch	){LocMstSearch.close();}
					if(null!=StockSearch	){StockSearch.close();}
					if(null!=ItemMstSearch	){ItemMstSearch.close();}
					if(null!=StockUpdate	){StockUpdate.close();}
					if(null!=StockInsert	){StockInsert.close();}
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		A100_DbConnect.close();
			
			int rtCount = 0;
			if(null!=NoLocErr&&0<NoLocErr.size()) {
				rtCount = rtCount + NoLocErr.size();
			}
			if(null!=NoItemErr&&0<NoItemErr.size()) {
				rtCount = rtCount + NoItemErr.size();
			}
			if(null!=OverQtyErr&&0<OverQtyErr.size()) {
				rtCount = rtCount + OverQtyErr.size();
			}
			if(null!=EntryRow&&0<EntryRow.size()) {
				rtCount = rtCount + EntryRow.size();
			}
			
			rt = new Object[rtCount][RtStockQtyControl().length];
			rtCount = 0;
			if(null!=NoLocErr&&0<NoLocErr.size()) {
				for(int i01=0;i01<NoLocErr.size();i01++) {
					rt[rtCount][RtColErrType] = "NoLocErr";
					rt[rtCount][RtColErrVol] 	= NoLocErr.get(i01);
					rt[rtCount][RtColErrRow] 	= NoLocErrRow.get(i01);
					
					rt[rtCount][RtColBeforeQty] 			= 0;
					rt[rtCount][RtColBeforeShipPlanQty] 	= 0;
					rt[rtCount][RtColBeforePossibleQty] 	= 0;
					rt[rtCount][RtColAdjustQty] 			= 0;
					rt[rtCount][RtColAfterQty] 			= 0;
					rt[rtCount][RtColAfterShipPlanQty]	= 0;
					rt[rtCount][RtColAfterPossibleQty]	= 0;
					rtCount = rtCount + 1;
				}
			}
			if(null!=NoItemErr&&0<NoItemErr.size()) {
				for(int i01=0;i01<NoItemErr.size();i01++) {
					rt[rtCount][RtColErrType] = "NoItemErr";
					rt[rtCount][RtColErrVol] 	= NoItemErr.get(i01);
					rt[rtCount][RtColErrRow] 	= NoItemErrRow.get(i01);
					
					rt[rtCount][RtColBeforeQty] 			= 0;
					rt[rtCount][RtColBeforeShipPlanQty] 	= 0;
					rt[rtCount][RtColBeforePossibleQty] 	= 0;
					rt[rtCount][RtColAdjustQty] 			= 0;
					rt[rtCount][RtColAfterQty] 			= 0;
					rt[rtCount][RtColAfterShipPlanQty]	= 0;
					rt[rtCount][RtColAfterPossibleQty]	= 0;
					rtCount = rtCount + 1;
				}
			}
			if(null!=OverQtyErr&&0<OverQtyErr.size()) {
				for(int i01=0;i01<OverQtyErr.size();i01++) {
					rt[rtCount][RtColErrType] = "OverQtyErr";
					rt[rtCount][RtColErrVol] 	= OverQtyErr.get(i01);
					rt[rtCount][RtColErrRow] 	= OverQtyErrRow.get(i01);
					
					rt[rtCount][RtColBeforeQty] 			= 0;
					rt[rtCount][RtColBeforeShipPlanQty] 	= 0;
					rt[rtCount][RtColBeforePossibleQty] 	= 0;
					rt[rtCount][RtColAdjustQty] 			= 0;
					rt[rtCount][RtColAfterQty] 			= 0;
					rt[rtCount][RtColAfterShipPlanQty]	= 0;
					rt[rtCount][RtColAfterPossibleQty]	= 0;
					rtCount = rtCount + 1;
				}
			}
			if(null!=EntryRow&&0<EntryRow.size()) {
				for(int i01=0;i01<EntryRow.size();i01++) {
					rt[rtCount][RtColErrType] = "EntryData";
					rt[rtCount][RtColErrVol] 	= "";
					rt[rtCount][RtColErrRow] 	= EntryRow.get(i01);
					
					rt[rtCount][RtColBeforeQty] 			= EntryBeforeQty.get(i01);
					rt[rtCount][RtColBeforeShipPlanQty] 	= EntryBeforeShipPlanQty.get(i01);
					rt[rtCount][RtColBeforePossibleQty] 	= EntryBeforePossibleQty.get(i01);
					rt[rtCount][RtColAdjustQty] 			= EntryAdjustQty.get(i01);
					rt[rtCount][RtColAfterQty] 			= EntryAfterQty.get(i01);
					rt[rtCount][RtColAfterShipPlanQty]	= EntryAfterShipPlanQty.get(i01);
					rt[rtCount][RtColAfterPossibleQty]	= EntryAfterPossibleQty.get(i01);
					rtCount = rtCount + 1;
				}
			}
		}
		return rt;
	}
	
	private static PreparedStatement LocMstSearch() {
		//ロケ検索
		PreparedStatement LocMstSearch = null;
		String sql = "select "
				+"(WM0010LOCATIONMST.ClCd) as ClCd ,\n"	//荷主コード
				+"(WM0010LOCATIONMST.WhCd) as WhCd ,\n"	//倉庫コード
				+"(WM0010LOCATIONMST.Loc)  as Loc  ,\n"	//ロケーション
				+"(WM0010LOCATIONMST.LocType) as Type  \n"	
				+ " from "+A00000_Main.MySqlDefaultSchemaWANKO+".WM0010LOCATIONMST \n"
				+ " where "
				+ " WM0010LOCATIONMST.ClCd     = ?\n"
				+ " and WM0010LOCATIONMST.WhCd = ?\n"
				+ " and WM0010LOCATIONMST.Loc  = ?\n";
		//System.out.println(sql);
		try {
			LocMstSearch = A100_DbConnect.conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return LocMstSearch;
	}
	
	private static PreparedStatement StockSearch() {
		//在庫検索
		PreparedStatement StockSearch = null;
		String sql = "select "
				+"(WW0015Stock.ClCd)        as ClCd        ,\n"	//荷主コード
				+"(WW0015Stock.WhCd)        as WhCd        ,\n"	//倉庫コード
				+"(WW0015Stock.Loc)         as Loc         ,\n"	//ロケーション
				+"(WW0015Stock.ItemCd)      as ItemCd      ,\n"	//商品コード
				+"(WW0015Stock.Lot)         as Lot         ,\n"	//ロット
				+"(WW0015Stock.Expdate)     as Expdate     ,\n"	//消費期限
				+"(WW0015Stock.ActualDate)  as ActualDate  ,\n"	//入荷実績日
				+"(WW0015Stock.Qty)         as Qty         ,\n"	//数量
				+"(WW0015Stock.ShipPlanQty) as ShipPlanQty ,\n"	//引当済数
				+"(WW0015Stock.PossibleQty) as PossibleQty ,\n"	//出荷可能数
				+"(WW0015Stock.ItemName)    as ItemName    ,\n"	//商品名
				+"(WW0015Stock.ClItemCd)    as ClItemCd    ,\n"	//荷主商品コード
				+"(WW0015Stock.JanCd)       as JanCd       ,\n"	//ソースマーク_BCD（バラ）
				+"(WW0015Stock.ItemMdNo)    as ItemMdNo    ,\n"	//商品型番
				+"(WW0015Stock.EntryDate)   as EntryDate   ,\n"	//登録日時
				+"(WW0015Stock.UpdateDate)  as UpdateDate  ,\n"	//更新日時
				+"(WW0015Stock.EntryUser)   as EntryUser   ,\n"	//登録者
				+"(WW0015Stock.UpdateUser)  as UpdateUser   \n"	//更新者
				+ " from "+A00000_Main.MySqlDefaultSchemaWANKO+".WW0015Stock"
				+ " where "
				+ " WW0015Stock.ClCd            = ?\n"
				+ " and WW0015Stock.WhCd        = ?\n"
				+ " and WW0015Stock.Loc         = ?\n"
				+ " and WW0015Stock.ItemCd      = ?\n"
				+ " and WW0015Stock.Lot         = ?\n"
				+ " and WW0015Stock.Expdate     = ?\n"
				+ " and WW0015Stock.ActualDate  = ?\n";
		//System.out.println(sql);
		try {
			StockSearch = A100_DbConnect.conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return StockSearch;
	}
	
	private static PreparedStatement ItemMstSearch() {
		PreparedStatement ItemMstSearch = null;
		String sql = "select "
				+"(KM0060_ITEMMST.ClGpCd)     as ClGpCd,\n"					//荷主グループコード
				+"(KM0060_ITEMMST.ItemCd)     as ItemCd,\n"					//商品コード
				+"(KM0060_ITEMMST.ClItemCd)   as ClItemCd,\n"				//荷主商品コード
				+"(KM0060_ITEMMST.ItemName01) as ItemName01,\n"				//商品表記名
				+"(KM0060_ITEMMST.ItemName02) as ItemName02,\n"				//商品正式名
				+"(KM0060_ITEMMST.ItemName03) as ItemName03,\n"				//商品略名
				+"(KM0060_ITEMMST.JanCd) as JanCd,\n"						//JANCD
				+"(KM0061_ITEMMSTSUB.ItemMDNo) as ItemMDNo\n"				//商品モデル番号（型番）
				+ " from "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0060_ITEMMST \n"
				+ " left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0061_ITEMMSTSUB \n"
				+ " on("
				+ " KM0060_ITEMMST.ClGpCd = KM0061_ITEMMSTSUB.ClGpCd and KM0060_ITEMMST.ItemCd = KM0061_ITEMMSTSUB.ItemCd"
				+ ")\n"
				+ " left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0030_CLIENTMST"
				+ " on("
				+ " KM0060_ITEMMST.ClGpCd = KM0030_CLIENTMST.ClGpCD"
				+ ")\n"
				+ " where "
				+ "KM0030_CLIENTMST.cl_cd = ?\n"
				+ " and KM0060_ITEMMST.ItemCd = ?";
		//System.out.println(sql);
		try {
			ItemMstSearch = A100_DbConnect.conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ItemMstSearch;
	}
	private static PreparedStatement StockUpdate() {
		PreparedStatement StockUpdate = null;
		String sql = "update "+A00000_Main.MySqlDefaultSchemaWANKO+".WW0015Stock "
						+" set "
						+" Qty         = WW0015Stock.Qty         +?"	//数量
						+",PossibleQty = WW0015Stock.PossibleQty +?"	//出荷可能数
						+",UpdateDate  = ?"	//更新日時
						+",UpdateUser  = ?"	//更新者
						+ " where "
						+ " ClCd            = ?\n"
						+ " and WhCd        = ?\n"
						+ " and Loc         = ?\n"
						+ " and ItemCd      = ?\n"
						+ " and Lot         = ?\n"
						+ " and Expdate     = ?\n"
						+ " and ActualDate  = ?\n";
		//System.out.println(sql);
		try {
			StockUpdate = A100_DbConnect.conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return StockUpdate;
	}
	private static PreparedStatement StockInsert() {
		PreparedStatement StockInsert = null;
		String sql = "insert into "+A00000_Main.MySqlDefaultSchemaWANKO+".WW0015Stock("
						+"ClCd,\n"			//荷主コード
						+"WhCd,\n"			//倉庫コード
						+"Loc,\n"			//ロケーション
						+"ItemCd,\n"		//商品コード
						+"Lot,\n"			//ロット
						+"Expdate,\n"		//消費期限
						+"ActualDate,\n"	//入荷実績日
						+"Qty,\n"			//数量
						+"ShipPlanQty,\n"	//引当済数
						+"PossibleQty,\n"	//出荷可能数
						+"ItemName,\n"		//商品名
						+"ClItemCd,\n"		//荷主商品コード
						+"JanCd,\n"			//ソースマーク_BCD（バラ）
						+"ItemMdNo,\n"		//商品型番
						+"EntryDate,\n"		//登録日時
						+"UpdateDate,\n"	//更新日時
						+"EntryUser,\n"		//登録者
						+"UpdateUser\n"		//更新者
						+") values ("
						+"?,\n"		//荷主コード
						+"?,\n"		//倉庫コード
						+"?,\n"		//ロケーション
						+"?,\n"		//商品コード
						+"?,\n"		//ロット
						+"?,\n"		//消費期限
						+"?,\n"		//入荷実績日
						+"?,\n"		//数量
						+"?,\n"		//引当済数
						+"?,\n"		//出荷可能数
						+"?,\n"		//商品名
						+"?,\n"		//荷主商品コード
						+"?,\n"		//ソースマーク_BCD（バラ）
						+"?,\n"		//商品型番
						+"?,\n"		//登録日時
						+"?,\n"		//更新日時
						+"?,\n"		//登録者
						+"?\n"		//更新者
						+")"
						;
		try {
			StockInsert = A100_DbConnect.conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return StockInsert;
	}
}