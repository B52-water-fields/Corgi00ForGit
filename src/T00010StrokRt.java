import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class T00010StrokRt{
	//在庫情報を素直に返却する※マスタ情報等とのJOIN行わない
	/*
	コピペ用
	ArrayList<String>  SearchClCd			= new ArrayList<String>();	//荷主コード
	ArrayList<String>  SearchWhCd			= new ArrayList<String>();	//倉庫コード
	ArrayList<String>  SearchLoc			= new ArrayList<String>();	//ロケーション
	ArrayList<String>  SearchItemCd			= new ArrayList<String>();	//商品コード
	ArrayList<String>  SearchLot			= new ArrayList<String>();	//ロット
	ArrayList<String>  SearchExpdateMin		= new ArrayList<String>();	//消費期限最小
	ArrayList<String>  SearchActualDateMin	= new ArrayList<String>();	//入荷実績日最小
	ArrayList<Integer> SearchQtyMin			= new ArrayList<Integer>();	//数量最小
	ArrayList<Integer> SearchShipPlanQtyMin	= new ArrayList<Integer>();	//引当済数最小
	ArrayList<Integer> SearchPossibleQtyMin	= new ArrayList<Integer>();	//出荷可能数最小
	ArrayList<String>  SearchExpdateMax		= new ArrayList<String>();	//消費期限最大
	ArrayList<String>  SearchActualDateMax	= new ArrayList<String>();	//入荷実績日最大
	ArrayList<Integer> SearchQtyMax			= new ArrayList<Integer>();	//数量最大
	ArrayList<Integer> SearchShipPlanQtyMax	= new ArrayList<Integer>();	//引当済数最大
	ArrayList<Integer> SearchPossibleQtyMax	= new ArrayList<Integer>();	//出荷可能数最大
	ArrayList<String>  SearchItemName		= new ArrayList<String>();	//商品名
	ArrayList<String>  SearchClItemCd		= new ArrayList<String>();	//荷主商品コード
	ArrayList<String>  SearchJanCd			= new ArrayList<String>();	//ソースマーク_BCD（バラ）
	ArrayList<String>  SearchItemMdNo		= new ArrayList<String>();	//商品型番
	boolean LocSortFg = true;					//ロケ順⇒商品CD⇒賞味期限⇒ロット⇒入荷日順で並べ替え　false なら商品CD⇒賞味期限⇒ロット⇒入荷日⇒ロケ
	boolean LocExactMatch = false;				//ロケーション完全一致
	boolean AllSearch = false;
	
	Object[][] LocationMstRt = T00010StrokRt.StrokRt(
			SearchClCd,				//荷主コード
			SearchWhCd,				//倉庫コード
			SearchLoc,				//ロケーション
			SearchItemCd,			//商品コード
			SearchLot,				//ロット
			SearchExpdateMin,		//消費期限最小
			SearchActualDateMin,	//入荷実績日最小
			SearchQtyMin,			//数量最小
			SearchShipPlanQtyMin,	//引当済数最小
			SearchPossibleQtyMin,	//出荷可能数最小
			SearchExpdateMax,		//消費期限最大
			SearchActualDateMax,	//入荷実績日最大
			SearchQtyMax,			//数量最大
			SearchShipPlanQtyMax,	//引当済数最大
			SearchPossibleQtyMax,	//出荷可能数最大
			SearchItemName,			//商品名
			SearchClItemCd,			//荷主商品コード
			SearchJanCd,			//ソースマーク_BCD（バラ）
			SearchItemMdNo,			//商品型番
			LocSortFg,				//ロケ順⇒商品CD⇒賞味期限⇒ロット⇒入荷日順で並べ替え　false なら商品CD⇒賞味期限⇒ロット⇒入荷日⇒ロケ
			LocExactMatch,			//ロケーション完全一致
			AllSearch);
	*/
	public static Object[][] RtStrokRt(){
		Object[][] RtSettingLocationMstRt = {
				 {"ClCd"			,(int) 0	,"String"	,"荷主コード"}
				,{"WhCd"			,(int) 1	,"String"	,"倉庫コード"}
				,{"Loc"				,(int) 2	,"String"	,"ロケーション"}
				,{"ItemCd"			,(int) 3	,"String"	,"商品コード"}
				,{"Lot"				,(int) 4	,"String"	,"ロット"}
				,{"Expdate"			,(int) 5	,"String"	,"消費期限"}
				,{"ActualDate"		,(int) 6	,"String"	,"入荷実績日"}
				,{"Qty"				,(int) 7	,"int"		,"数量"}
				,{"ShipPlanQty"		,(int) 8	,"int"		,"引当済数"}
				,{"PossibleQty"		,(int) 9	,"int"		,"出荷可能数"}
				,{"ItemName"		,(int)10	,"String"	,"商品名"}
				,{"ClItemCd"		,(int)11	,"String"	,"荷主商品コード"}
				,{"JanCd"			,(int)12	,"String"	,"ソースマーク_BCD（バラ）"}
				,{"ItemMdNo"		,(int)13	,"String"	,"商品型番"}
				,{"EntryDate"		,(int)14	,"String"	,"登録日時"}
				,{"UpdateDate"		,(int)15	,"String"	,"更新日時"}
				,{"EntryUser"		,(int)16	,"String"	,"登録者"}
				,{"UpdateUser"		,(int)17	,"String"	,"更新者"}
				};
		
		return RtSettingLocationMstRt;
	}
	
	//在庫情報を素直に返却する※マスタ情報等とのJOIN行わない
	public static Object[][] StrokRt(
			ArrayList<String>  SearchClCd,				//荷主コード
			ArrayList<String>  SearchWhCd,				//倉庫コード
			ArrayList<String>  SearchLoc,				//ロケーション
			ArrayList<String>  SearchItemCd,			//商品コード
			ArrayList<String>  SearchLot,				//ロット
			ArrayList<String>  SearchExpdateMin,		//消費期限最小
			ArrayList<String>  SearchActualDateMin,		//入荷実績日最小
			ArrayList<Integer> SearchQtyMin,			//数量最小
			ArrayList<Integer> SearchShipPlanQtyMin,	//引当済数最小
			ArrayList<Integer> SearchPossibleQtyMin,	//出荷可能数最小
			ArrayList<String>  SearchExpdateMax,		//消費期限最大
			ArrayList<String>  SearchActualDateMax,		//入荷実績日最大
			ArrayList<Integer> SearchQtyMax,			//数量最大
			ArrayList<Integer> SearchShipPlanQtyMax,	//引当済数最大
			ArrayList<Integer> SearchPossibleQtyMax,	//出荷可能数最大
			ArrayList<String>  SearchItemName,			//商品名
			ArrayList<String>  SearchClItemCd,			//荷主商品コード
			ArrayList<String>  SearchJanCd,				//ソースマーク_BCD（バラ）
			ArrayList<String>  SearchItemMdNo,			//商品型番
			boolean LocSortFg,		//ロケ順⇒商品CD⇒賞味期限⇒ロット⇒入荷日順で並べ替え　false なら商品CD⇒賞味期限⇒ロット⇒入荷日⇒ロケ
			boolean LocExactMatch,	//ロケーション完全一致
			boolean AllSearch){
		SearchClCd				= B00150ArrayListControl.ArryListStringUniqueList(SearchClCd);				//荷主コード
		SearchWhCd				= B00150ArrayListControl.ArryListStringUniqueList(SearchWhCd);				//倉庫コード
		SearchLoc				= B00150ArrayListControl.ArryListStringUniqueList(SearchLoc);				//ロケーション
		SearchItemCd			= B00150ArrayListControl.ArryListStringUniqueList(SearchItemCd);			//商品コード
		SearchLot				= B00150ArrayListControl.ArryListStringUniqueList(SearchLot);				//ロット
		SearchExpdateMin		= B00150ArrayListControl.ArryListStringUniqueList(SearchExpdateMin);		//消費期限最小
		SearchActualDateMin		= B00150ArrayListControl.ArryListStringUniqueList(SearchActualDateMin);		//入荷実績日最小
		SearchQtyMin			= B00150ArrayListControl.ArryListIntegerUniqueList(SearchQtyMin);			//数量最小
		SearchShipPlanQtyMin	= B00150ArrayListControl.ArryListIntegerUniqueList(SearchShipPlanQtyMin);	//引当済数最小
		SearchPossibleQtyMin	= B00150ArrayListControl.ArryListIntegerUniqueList(SearchPossibleQtyMin);	//出荷可能数最小
		SearchExpdateMax		= B00150ArrayListControl.ArryListStringUniqueList(SearchExpdateMax);		//消費期限最大
		SearchActualDateMax		= B00150ArrayListControl.ArryListStringUniqueList(SearchActualDateMax);		//入荷実績日最大
		SearchQtyMax			= B00150ArrayListControl.ArryListIntegerUniqueList(SearchQtyMax);			//数量最大
		SearchShipPlanQtyMax	= B00150ArrayListControl.ArryListIntegerUniqueList(SearchShipPlanQtyMax);	//引当済数最大
		SearchPossibleQtyMax	= B00150ArrayListControl.ArryListIntegerUniqueList(SearchPossibleQtyMax);	//出荷可能数最大
		SearchItemName			= B00150ArrayListControl.ArryListStringUniqueList(SearchItemName);			//商品名
		SearchClItemCd			= B00150ArrayListControl.ArryListStringUniqueList(SearchClItemCd);			//荷主商品コード
		SearchJanCd				= B00150ArrayListControl.ArryListStringUniqueList(SearchJanCd);				//ソースマーク_BCD（バラ）
		SearchItemMdNo			= B00150ArrayListControl.ArryListStringUniqueList(SearchItemMdNo);			//商品型番
		
		//日付項目　念のため時刻00：00：00とする。
		//検索条件最大の場合1日進めて<条件で検索可能にする
		if(null!=SearchExpdateMin && 0<SearchExpdateMin.size()){
			for(int i=0;i<SearchExpdateMin.size();i++){
				SearchExpdateMin.set(i, B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtmTimestamp2(SearchExpdateMin.get(i))[0])[0]);
			}
		}
		if(null!=SearchActualDateMin && 0<SearchActualDateMin.size()){
			for(int i=0;i<SearchActualDateMin.size();i++){
				SearchActualDateMin.set(i, B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtmTimestamp2(SearchActualDateMin.get(i))[0])[0]);
			}
		}
		if(null!=SearchExpdateMax && 0<SearchExpdateMax.size()){
			for(int i=0;i<SearchExpdateMax.size();i++){
				SearchExpdateMax.set(i, B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.ndate_after(B00050ToolsDateTimeControl.dtmTimestamp2(SearchExpdateMax.get(i))[0],1))[0]);
			}
		}
		if(null!=SearchActualDateMax && 0<SearchActualDateMax.size()){
			for(int i=0;i<SearchActualDateMax.size();i++){
				SearchActualDateMax.set(i, B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.ndate_after(B00050ToolsDateTimeControl.dtmTimestamp2(SearchActualDateMax.get(i))[0],1))[0]);
			}
		}

		Object[][] rt = new Object[0][18];
		boolean SearchKick = false;
		if(AllSearch) {SearchKick = true;}
		
		String sql = " select \n"
				+"(WW0015Stock.ClCd) as ClCd,\n"				//荷主コード
				+"(WW0015Stock.WhCd) as WhCd,\n"				//倉庫コード
				+"(WW0015Stock.Loc) as Loc,\n"					//ロケーション
				+"(WW0015Stock.ItemCd) as ItemCd,\n"			//商品コード
				+"(WW0015Stock.Lot) as Lot,\n"					//ロット
				+"(WW0015Stock.Expdate) as Expdate,\n"			//消費期限
				+"(WW0015Stock.ActualDate) as ActualDate,\n"	//入荷実績日
				+"(WW0015Stock.Qty) as Qty,\n"					//数量
				+"(WW0015Stock.ShipPlanQty) as ShipPlanQty,\n"	//引当済数
				+"(WW0015Stock.PossibleQty) as PossibleQty,\n"	//出荷可能数
				+"(WW0015Stock.ItemName) as ItemName,\n"		//商品名
				+"(WW0015Stock.ClItemCd) as ClItemCd,\n"		//荷主商品コード
				+"(WW0015Stock.JanCd) as JanCd,\n"				//ソースマーク_BCD（バラ）
				+"(WW0015Stock.ItemMdNo) as ItemMdNo,\n"		//商品型番
				+"(WW0015Stock.EntryDate) as EntryDate,\n"		//登録日時
				+"(WW0015Stock.UpdateDate) as UpdateDate,\n"	//更新日時
				+"(WW0015Stock.EntryUser) as EntryUser,\n"		//登録者
				+"(WW0015Stock.UpdateUser) as UpdateUser\n"		//更新者
				+" from "+A00000Main.MySqlDefaultSchemaWANKO+".WW0015Stock \n"
				+" where 1=1 \n";
		
		if(null!=SearchClCd&&0<SearchClCd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchClCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0015Stock.ClCd = ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchWhCd&&0<SearchWhCd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchWhCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0015Stock.WhCd = ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchLoc&&0<SearchLoc.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchLoc.size();i++){
				if(0<i){sql = sql + " or ";}
				if(LocExactMatch) {
					sql = sql + " WW0015Stock.Loc = ?";
				}else {
					sql = sql + " WW0015Stock.Loc Like ?";
				}
			}
			sql = sql + ")";
		}
		
		if(null!=SearchItemCd&&0<SearchItemCd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchItemCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0015Stock.ItemCd = ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchLot&&0<SearchLot.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchLot.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0015Stock.Lot = ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchExpdateMin&&0<SearchExpdateMin.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchExpdateMin.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0015Stock.Expdate >= ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchActualDateMin&&0<SearchActualDateMin.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchActualDateMin.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0015Stock.ActualDate >= ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchQtyMin&&0<SearchQtyMin.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchQtyMin.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0015Stock.Qty >= ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchShipPlanQtyMin&&0<SearchShipPlanQtyMin.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchShipPlanQtyMin.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0015Stock.ShipPlanQty >= ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchPossibleQtyMin&&0<SearchPossibleQtyMin.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchPossibleQtyMin.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0015Stock.PossibleQty >= ?";
			}
			sql = sql + ")";
		}
		

		if(null!=SearchExpdateMax&&0<SearchExpdateMax.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchExpdateMax.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0015Stock.Expdate < ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchActualDateMax&&0<SearchActualDateMax.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchActualDateMax.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0015Stock.ActualDate < ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchQtyMax&&0<SearchQtyMax.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchQtyMax.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0015Stock.Qty <= ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchShipPlanQtyMax&&0<SearchShipPlanQtyMax.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchShipPlanQtyMax.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0015Stock.ShipPlanQty <= ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchPossibleQtyMax&&0<SearchPossibleQtyMax.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchPossibleQtyMax.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0015Stock.PossibleQty <= ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchItemName&&0<SearchItemName.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchItemName.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0015Stock.ItemName Like ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchClItemCd&&0<SearchClItemCd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchClItemCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0015Stock.ClItemCd = ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchJanCd&&0<SearchJanCd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchJanCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0015Stock.JanCd = ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchItemMdNo&&0<SearchItemMdNo.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchItemMdNo.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0015Stock.ItemMdNo = ?";
			}
			sql = sql + ")";
		}
		
		if(LocSortFg) {
			sql = sql +" order by WW0015Stock.ClCd,WW0015Stock.WhCd,WW0015Stock.Loc,WW0015Stock.ItemCd,WW0015Stock.Expdate,WW0015Stock.Lot,WW0015Stock.ActualDate";
		}else {
			sql = sql +" order by WW0015Stock.ClCd,WW0015Stock.WhCd,WW0015Stock.ItemCd,WW0015Stock.Expdate,WW0015Stock.Lot,WW0015Stock.ActualDate,WW0015Stock.Loc";
		}
		
		if(SearchKick) {
			A00010DbConnect.DB_CONN("NYANKO");
			ResultSet rset01 = null;
			PreparedStatement stmt01 = null;
			try {
				stmt01 = A00010DbConnect.conn.prepareStatement(sql);
				int StmtCount = 0;
				
				if(null!=SearchClCd&&0<SearchClCd.size()){
					for(int i=0;i<SearchClCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchClCd.get(i)+"");
					}
				}
				
				if(null!=SearchWhCd&&0<SearchWhCd.size()){
					for(int i=0;i<SearchWhCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchWhCd.get(i)+"");
					}
				}
				
				if(null!=SearchLoc&&0<SearchLoc.size()){
					for(int i=0;i<SearchLoc.size();i++){
						if(LocExactMatch) {
							StmtCount = StmtCount+1;
							stmt01.setString(StmtCount, ""+SearchLoc.get(i)+"");
						}else {
							StmtCount = StmtCount+1;
							stmt01.setString(StmtCount, ""+SearchLoc.get(i)+"%");
						}
					}
				}
				
				if(null!=SearchItemCd&&0<SearchItemCd.size()){
					for(int i=0;i<SearchItemCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchItemCd.get(i)+"");
					}
				}
				
				if(null!=SearchLot&&0<SearchLot.size()){
					for(int i=0;i<SearchLot.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchLot.get(i)+"");
					}
				}
				
				if(null!=SearchExpdateMin&&0<SearchExpdateMin.size()){
					for(int i=0;i<SearchExpdateMin.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchExpdateMin.get(i)+"");
					}
				}
				
				if(null!=SearchActualDateMin&&0<SearchActualDateMin.size()){
					for(int i=0;i<SearchActualDateMin.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchActualDateMin.get(i)+"");
					}
				}
				
				if(null!=SearchQtyMin&&0<SearchQtyMin.size()){
					for(int i=0;i<SearchQtyMin.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchQtyMin.get(i)+"");
					}
				}
				
				if(null!=SearchShipPlanQtyMin&&0<SearchShipPlanQtyMin.size()){
					for(int i=0;i<SearchShipPlanQtyMin.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchShipPlanQtyMin.get(i)+"");
					}
				}
				
				if(null!=SearchPossibleQtyMin&&0<SearchPossibleQtyMin.size()){
					for(int i=0;i<SearchPossibleQtyMin.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchPossibleQtyMin.get(i)+"");
					}
				}
				
		
				if(null!=SearchExpdateMax&&0<SearchExpdateMax.size()){
					for(int i=0;i<SearchExpdateMax.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchExpdateMax.get(i)+"");
					}
				}
				
				if(null!=SearchActualDateMax&&0<SearchActualDateMax.size()){
					for(int i=0;i<SearchActualDateMax.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchActualDateMax.get(i)+"");
					}
				}
				
				if(null!=SearchQtyMax&&0<SearchQtyMax.size()){
					for(int i=0;i<SearchQtyMax.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchQtyMax.get(i)+"");
					}
				}
				
				if(null!=SearchShipPlanQtyMax&&0<SearchShipPlanQtyMax.size()){
					for(int i=0;i<SearchShipPlanQtyMax.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchShipPlanQtyMax.get(i)+"");
					}
				}
				
				if(null!=SearchPossibleQtyMax&&0<SearchPossibleQtyMax.size()){
					for(int i=0;i<SearchPossibleQtyMax.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchPossibleQtyMax.get(i)+"");
					}
				}
				
				if(null!=SearchItemName&&0<SearchItemName.size()){
					for(int i=0;i<SearchItemName.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchItemName.get(i)+"%");
					}
				}
				
				if(null!=SearchClItemCd&&0<SearchClItemCd.size()){
					for(int i=0;i<SearchClItemCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchClItemCd.get(i)+"");
					}
				}
				
				if(null!=SearchJanCd&&0<SearchJanCd.size()){
					for(int i=0;i<SearchJanCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchJanCd.get(i)+"");
					}
				}
				
				if(null!=SearchItemMdNo&&0<SearchItemMdNo.size()){
					for(int i=0;i<SearchItemMdNo.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchItemMdNo.get(i)+"");
					}
				}
				
				rset01 = stmt01.executeQuery();
				
				int counter = 0;
				rset01.beforeFirst();
				while (rset01.next()) {
					counter=counter+1;
				}
				rt = new Object[counter][18];
				counter = 0;
				rset01.beforeFirst();
				while (rset01.next()) {
					if(null==rset01.getString("ClCd")){				rt[counter][ 0]="";}else{rt[counter][ 0]=rset01.getString("ClCd");}			//荷主コード
					if(null==rset01.getString("WhCd")){				rt[counter][ 1]="";}else{rt[counter][ 1]=rset01.getString("WhCd");}			//倉庫コード
					if(null==rset01.getString("Loc")){				rt[counter][ 2]="";}else{rt[counter][ 2]=rset01.getString("Loc");}			//ロケーション
					if(null==rset01.getString("ItemCd")){			rt[counter][ 3]="";}else{rt[counter][ 3]=rset01.getString("ItemCd");}		//商品コード
					if(null==rset01.getString("Lot")){				rt[counter][ 4]="";}else{rt[counter][ 4]=rset01.getString("Lot");}			//ロット
					if(null==rset01.getString("Expdate")){			rt[counter][ 5]="";}else{rt[counter][ 5]=rset01.getString("Expdate");}		//消費期限
					if(null==rset01.getString("ActualDate")){		rt[counter][ 6]="";}else{rt[counter][ 6]=rset01.getString("ActualDate");}	//入荷実績日
					rt[counter][ 7]=rset01.getInt("Qty");				//数量
					rt[counter][ 8]=rset01.getInt("ShipPlanQty");		//引当済数
					rt[counter][ 9]=rset01.getInt("PossibleQty");		//出荷可能数
					if(null==rset01.getString("ItemName")){			rt[counter][10]="";}else{rt[counter][10]=rset01.getString("ItemName");}		//商品名
					if(null==rset01.getString("ClItemCd")){			rt[counter][11]="";}else{rt[counter][11]=rset01.getString("ClItemCd");}		//荷主商品コード
					if(null==rset01.getString("JanCd")){			rt[counter][12]="";}else{rt[counter][12]=rset01.getString("JanCd");}		//ソースマーク_BCD（バラ）
					if(null==rset01.getString("ItemMdNo")){			rt[counter][13]="";}else{rt[counter][13]=rset01.getString("ItemMdNo");}		//商品型番
					if(null==rset01.getTimestamp("EntryDate")){		rt[counter][14]="";}else{rt[counter][14]=B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("EntryDate"))[1];}		//登録日時
					if(null==rset01.getTimestamp("UpdateDate")){	rt[counter][15]="";}else{rt[counter][15]=B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("UpdateDate"))[1];}		//更新日時
					if(null==rset01.getString("EntryUser")){		rt[counter][16]="";}else{rt[counter][16]=rset01.getString("EntryUser");}	//登録者
					if(null==rset01.getString("UpdateUser")){		rt[counter][17]="";}else{rt[counter][17]=rset01.getString("UpdateUser");}	//更新者
					counter=counter+1;
				}
				
				if(rset01!=null){rset01.close();}
				if(stmt01!=null){stmt01.close();}
			}catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					if(rset01!=null){rset01.close();}
					if(stmt01!=null){stmt01.close();}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			A00010DbConnect.close();
		}
		return rt;
	}
}