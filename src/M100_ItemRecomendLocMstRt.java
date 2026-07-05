import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class M100_ItemRecomendLocMstRt{
	//荷主ー商品ごとの推奨ロケを返却する
	/*
	コピペ用
	ArrayList<String> SearchClCd		= new ArrayList<String>();	//荷主コード
	ArrayList<String> SearchClWh		= new ArrayList<String>();	//担当倉庫コード
	ArrayList<String> SearchClGpCD		= new ArrayList<String>();	//荷主グループCD
	ArrayList<String> SearchItemCd		= new ArrayList<String>();	//商品コード
	ArrayList<String> SearchItemName01	= new ArrayList<String>();	//商品名1
	ArrayList<String> SearchRecomendLoc	= new ArrayList<String>();	//推奨ロケ
	ArrayList<String> SearchLocName		= new ArrayList<String>();	//ロケーション名
	ArrayList<Integer> SearchType		= new ArrayList<Integer>();	//ロケタイプ　0:通常　1:保管　8:入荷時　9:引当禁止
	boolean LocExactMatch = false;									//ロケーション完全一致
	boolean AllSearch = false;
	
	Object[][] ItemRecomendLocMstRt	= M100_ItemRecomendLocMstRt.ItemRecomendLocMstRt(
			SearchClCd,			//荷主コード
			SearchClWh,			//担当倉庫コード
			SearchClGpCD,		//荷主グループCD
			SearchItemCd,		//商品コード
			SearchItemName01,	//商品名1
			SearchRecomendLoc,	//推奨ロケ
			SearchLocName,		//ロケーション名
			SearchType,			//ロケタイプ　0:通常　1:保管　8:入荷時　9:引当禁止
			LocExactMatch,		//ロケーション完全一致
			AllSearch);
			
	String GetClCd			= (String)ItemRecomendLocMstRt[i][M100_ItemRecomendLocMstRt.ColClCd];			//荷主コード
	String GetCLName		= (String)ItemRecomendLocMstRt[i][M100_ItemRecomendLocMstRt.ColCLName];		//荷主表記名
	String GetClWh			= (String)ItemRecomendLocMstRt[i][M100_ItemRecomendLocMstRt.ColClWh];			//担当倉庫コード
	String GetClWHName		= (String)ItemRecomendLocMstRt[i][M100_ItemRecomendLocMstRt.ColClWHName];		//担当倉庫名
	String GetClGpCD		= (String)ItemRecomendLocMstRt[i][M100_ItemRecomendLocMstRt.ColClGpCD];		//荷主グループCD
	String GetClGpName		= (String)ItemRecomendLocMstRt[i][M100_ItemRecomendLocMstRt.ColClGpName];		//グループ名1
	String GetItemCd		= (String)ItemRecomendLocMstRt[i][M100_ItemRecomendLocMstRt.ColItemCd];		//商品コード
	String GetItemName01	= (String)ItemRecomendLocMstRt[i][M100_ItemRecomendLocMstRt.ColItemName01];	//商品名1
	String GetRecomendLoc	= (String)ItemRecomendLocMstRt[i][M100_ItemRecomendLocMstRt.ColRecomendLoc];	//推奨ロケ
	String GetLocName		= (String)ItemRecomendLocMstRt[i][M100_ItemRecomendLocMstRt.ColLocName];		//ロケーション名
	int    GetType			= (int)ItemRecomendLocMstRt[i][M100_ItemRecomendLocMstRt.ColType];				//ロケタイプ　0:通常　1:保管　8:入荷時　9:引当禁止
	String GetEntryDate		= (String)ItemRecomendLocMstRt[i][M100_ItemRecomendLocMstRt.ColEntryDate];		//データ登録日時
	String GetUpdateDate	= (String)ItemRecomendLocMstRt[i][M100_ItemRecomendLocMstRt.ColUpdateDate];	//データ更新日時
	String GetEntryUser		= (String)ItemRecomendLocMstRt[i][M100_ItemRecomendLocMstRt.ColEntryUser];		//登録者
	String GetUpdateUser	= (String)ItemRecomendLocMstRt[i][M100_ItemRecomendLocMstRt.ColUpdateUser];	//更新者
	String GetItemSubRecomendLoc	= (String)ItemRecomendLocMstRt[i][M100_ItemRecomendLocMstRt.ColItemSubRecomendLoc];	//商品サブマスタ推奨ロケ
	*/
	
	/*
	荷主コードと商品CDを受け取ってWW00630ItemRecomendLocの推奨ロケとKM0061_ITEMMSTSUBの推奨ロケを返す場合
	※KM0061_ITEMMSTSUBの推奨ロケが荷主個別設定されるロケーションマスタに存在しなくても値を格納します
	コピペ用
	String ClCd = A00000_Main.ClCd;
	ArrayList<String> ItemCd= new ArrayList<String>();	//商品CD
	boolean ItemMstRecomendLocExistenceOnly = true;	//商品サブマスタ推奨ロケは対象荷主のロケーションマスタに存在する場合のみ値格納
	
	Object[][] ItemRecomendLocFromItemCd = M100_ItemRecomendLocMstRt.ItemRecomendLocFromItemCd(ClCd,ItemCd,ItemMstRecomendLocExistenceOnly);
	
	String GetClCd			= (String)ItemRecomendLocFromItemCd[i][M100_ItemRecomendLocMstRt.ColClCd];			//荷主コード
	String GetCLName		= (String)ItemRecomendLocFromItemCd[i][M100_ItemRecomendLocMstRt.ColCLName];		//荷主表記名
	String GetClWh			= (String)ItemRecomendLocFromItemCd[i][M100_ItemRecomendLocMstRt.ColClWh];			//担当倉庫コード
	String GetClWHName		= (String)ItemRecomendLocFromItemCd[i][M100_ItemRecomendLocMstRt.ColClWHName];		//担当倉庫名
	String GetClGpCD		= (String)ItemRecomendLocFromItemCd[i][M100_ItemRecomendLocMstRt.ColClGpCD];		//荷主グループCD
	String GetClGpName		= (String)ItemRecomendLocFromItemCd[i][M100_ItemRecomendLocMstRt.ColClGpName];		//グループ名1
	String GetItemCd		= (String)ItemRecomendLocFromItemCd[i][M100_ItemRecomendLocMstRt.ColItemCd];		//商品コード
	String GetItemName01	= (String)ItemRecomendLocFromItemCd[i][M100_ItemRecomendLocMstRt.ColItemName01];	//商品名1
	String GetRecomendLoc	= (String)ItemRecomendLocFromItemCd[i][M100_ItemRecomendLocMstRt.ColRecomendLoc];	//推奨ロケ
	String GetLocName		= (String)ItemRecomendLocFromItemCd[i][M100_ItemRecomendLocMstRt.ColLocName];		//ロケーション名
	int    GetType			= (int)ItemRecomendLocFromItemCd[i][M100_ItemRecomendLocMstRt.ColType];			//ロケタイプ　0:通常　1:保管　8:入荷時　9:引当禁止
	String GetEntryDate		= (String)ItemRecomendLocFromItemCd[i][M100_ItemRecomendLocMstRt.ColEntryDate];	//データ登録日時
	String GetUpdateDate	= (String)ItemRecomendLocFromItemCd[i][M100_ItemRecomendLocMstRt.ColUpdateDate];	//データ更新日時
	String GetEntryUser		= (String)ItemRecomendLocFromItemCd[i][M100_ItemRecomendLocMstRt.ColEntryUser];	//登録者
	String GetUpdateUser	= (String)ItemRecomendLocFromItemCd[i][M100_ItemRecomendLocMstRt.ColUpdateUser];	//更新者
	String GetItemSubRecomendLoc	= (String)ItemRecomendLocFromItemCd[i][M100_ItemRecomendLocMstRt.ColItemSubRecomendLoc];	//商品サブマスタ推奨ロケ
	
	*/
	
	static final int ColClCd			= (int) 0;	//荷主コード
	static final int ColCLName			= (int) 1;	//荷主表記名
	static final int ColClWh			= (int) 2;	//担当倉庫コード
	static final int ColClWHName		= (int) 3;	//担当倉庫名
	static final int ColClGpCD			= (int) 4;	//荷主グループCD
	static final int ColClGpName		= (int) 5;	//グループ名1
	static final int ColItemCd			= (int) 6;	//商品コード
	static final int ColItemName01	= (int) 7;	//商品名1
	static final int ColRecomendLoc	= (int) 8;	//推奨ロケ
	static final int ColLocName		= (int) 9;	//ロケーション名
	static final int ColType			= (int)10;	//ロケタイプ　0:通常　1:保管　8:入荷時　9:引当禁止
	static final int ColEntryDate		= (int)11;	//データ登録日時
	static final int ColUpdateDate	= (int)12;	//データ更新日時
	static final int ColEntryUser		= (int)13;	//登録者
	static final int ColUpdateUser	= (int)14;	//更新者
	static final int ColItemSubRecomendLoc = (int)15;	//商品サブマスタ推奨ロケ　※ItemRecomendLocMstRtではWW00630ItemRecomendLocに登録がない情報は返却されません
	
	public static Object[][] RtItemRecomendLocMstRt() {
		Object[][] RtItemRecomendLocMstRt = {
					 {"ClCd"		,ColClCd			,"String"	,"荷主コード"		,"key"}
					,{"CLName"		,ColCLName			,"String"	,"荷主名"			,""}
					,{"ClWh"		,ColClWh			,"String"	,"担当倉庫コード"	,"key"}
					,{"ClWHName"	,ColClWHName		,"String"	,"担当倉庫名"		,""}
					,{"ClGpCD"		,ColClGpCD			,"String"	,"荷主グループCD"	,""}
					,{"ClGpName"	,ColClGpName		,"String"	,"グループ名"		,""}
					,{"ItemCd"		,ColItemCd			,"String"	,"商品コード"		,"key"}
					,{"ItemName01"	,ColItemName01	,"String"	,"商品名1"			,""}
					,{"RecomendLoc"	,ColRecomendLoc	,"String"	,"推奨ロケ"			,""}
					,{"LocName"		,ColLocName		,"String"	,"ロケーション名"	,""}
					,{"Type"		,ColType			,"int"		,"ロケタイプ"		,""}
					,{"EntryDate"	,ColEntryDate		,"String"	,"データ登録日時"	,""}
					,{"UpdateDate"	,ColUpdateDate	,"String"	,"データ更新日時"	,""}
					,{"EntryUser"	,ColEntryUser		,"String"	,"登録者"			,""}
					,{"UpdateUser"	,ColUpdateUser	,"String"	,"更新者"			,""}
					,{"ItemSubRecomendLoc"	,ColItemSubRecomendLoc,"String"	,"商品サブマスタ推奨ロケ",""}
					};
		return RtItemRecomendLocMstRt;
	}
	
	public static Object[][] ItemRecomendLocMstRt(
			ArrayList<String> SearchClCd,			//荷主コード
			ArrayList<String> SearchClWh,			//担当倉庫コード
			ArrayList<String> SearchClGpCD,			//荷主グループCD
			ArrayList<String> SearchItemCd,			//商品コード
			ArrayList<String> SearchItemName01,		//商品名1
			ArrayList<String> SearchRecomendLoc,	//推奨ロケ
			ArrayList<String> SearchLocName,		//ロケーション名
			ArrayList<Integer> SearchType,			//ロケタイプ　0:通常　1:保管　8:入荷時　9:引当禁止
			boolean LocExactMatch,					//ロケーション完全一致
			boolean AllSearch){
		
		SearchClCd			= B100_ArrayListControl.ArryListStringUniqueList(SearchClCd);					//荷主コード
		SearchClWh			= B100_ArrayListControl.ArryListStringUniqueList(SearchClWh);					//担当倉庫コード
		SearchClGpCD		= B100_ArrayListControl.ArryListStringUniqueList(SearchClGpCD);				//荷主グループCD
		SearchItemCd		= B100_ArrayListControl.ArryListStringUniqueList(SearchItemCd);				//商品コード
		SearchItemName01	= B100_ArrayListControl.ArryListStringUniqueList(SearchItemName01);			//商品名1
		SearchRecomendLoc	= B100_ArrayListControl.ArryListStringUniqueList(SearchRecomendLoc);			//推奨ロケ
		SearchLocName		= B100_ArrayListControl.ArryListStringUniqueList(SearchLocName);				//ロケーション名
		SearchType			= B100_ArrayListControl.ArryListIntegerUniqueList(SearchType);					//ロケタイプ　0:通常　1:保管　8:入荷時　9:引当禁止
		
		boolean SearchKick = false;
		if(AllSearch) {SearchKick = true;}
		
		Object[][] rt = new Object[0][RtItemRecomendLocMstRt().length];
		String sql = "select "
				+"(WW00630ItemRecomendLoc.ClCd) as ClCd,\n"					//荷主コード
				+"(KM0030_CLIENTMST.CLName01) as CLName,\n"					//荷主表記名
				+"(WW00630ItemRecomendLoc.ClWh) as ClWh,\n"					//担当倉庫コード
				+"(KM0010_WHMST.WHName) as ClWHName,\n"						//担当倉庫名
				+"(KM0030_CLIENTMST.ClGpCD) as ClGpCD,\n"					//荷主グループCD
				+"(KM0031_CLIENT_GROUP.ClGpName01) as ClGpName,\n"			//グループ名1
				+"(WW00630ItemRecomendLoc.ItemCd) as ItemCd,\n"				//商品コード
				+"(KM0060_ITEMMST.ItemName01) as ItemName01,\n"				//商品名1
				+"(WW00630ItemRecomendLoc.RecomendLoc) as RecomendLoc,\n"	//推奨ロケ
				+"(WM0010LOCATIONMST.LocName) as LocName,\n"				//ロケーション名
				+"(WM0010LOCATIONMST.LocType) as Type,\n"						//ロケタイプ　0:通常　1:保管　8:入荷時　9:引当禁止
				+"(WW00630ItemRecomendLoc.EntryDate) as EntryDate,\n"		//データ登録日時
				+"(WW00630ItemRecomendLoc.UpdateDate) as UpdateDate,\n"		//データ更新日時
				+"(WW00630ItemRecomendLoc.EntryUser) as EntryUser,\n"		//登録者
				+"(WW00630ItemRecomendLoc.UpdateUser) as UpdateUser,\n"		//更新者
				+"(KM0061_ITEMMSTSUB.RecomendLoc) as ItemSubRecomendLoc\n"	//商品サブマスタ推奨ロケ
				+ " from "+A00000_Main.MySqlDefaultSchemaWANKO+".WW00630ItemRecomendLoc"
				+ " left outer join "+A00000_Main.MySqlDefaultSchemaWANKO+".WM0010LOCATIONMST"
				+ " on("
				+ " WW00630ItemRecomendLoc.ClCd = WM0010LOCATIONMST.ClCd"
				+ " and WW00630ItemRecomendLoc.ClWh = WM0010LOCATIONMST.WhCd"
				+ " and WW00630ItemRecomendLoc.RecomendLoc= WM0010LOCATIONMST.Loc"
				+ ")\n"
				+ " left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0030_CLIENTMST"
				+ " on("
				+ " WW00630ItemRecomendLoc.ClWh = KM0030_CLIENTMST.WHCD"
				+ " and WW00630ItemRecomendLoc.ClCd = KM0030_CLIENTMST.cl_cd"
				+ ")\n"
				+ " left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP"
				+ " on("
				+ " KM0030_CLIENTMST.ClGpCD = KM0031_CLIENT_GROUP.ClGpCD"
				+ ")\n"
				+ " left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST"
				+ " on("
				+ " WW00630ItemRecomendLoc.ClWh = KM0010_WHMST.WHCD"
				+ ")\n"
				+ " left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0060_ITEMMST"
				+ " on("
				+ " KM0030_CLIENTMST.ClGpCD = KM0060_ITEMMST.ClGpCd"
				+ " and WW00630ItemRecomendLoc.ItemCd = KM0060_ITEMMST.ItemCd"
				+ ")\n"
				+ " left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0061_ITEMMSTSUB \n"
				+ " on("
				+ " KM0060_ITEMMST.ClGpCd = KM0061_ITEMMSTSUB.ClGpCd and KM0060_ITEMMST.ItemCd = KM0061_ITEMMSTSUB.ItemCd"
				+ ")\n"
				+ " where 1=1 \n";
		if(null!=SearchClCd && 0<SearchClCd.size()) {
			SearchKick = true;

			sql = sql + " and(";
			for(int i=0;i<SearchClCd.size();i++) {
				if(0<i) {sql = sql + " or ";}
				sql = sql + " WW00630ItemRecomendLoc.ClCd = ?";
			}
			sql = sql + ")\n";
		}

		if(null!=SearchClWh && 0<SearchClWh.size()) {
			SearchKick = true;

			sql = sql + " and(";
			for(int i=0;i<SearchClWh.size();i++) {
				if(0<i) {sql = sql + " or ";}
				sql = sql + " WW00630ItemRecomendLoc.ClWh = ?";
			}
			sql = sql + ")\n";
		}

		if(null!=SearchItemCd && 0<SearchItemCd.size()) {
			SearchKick = true;

			sql = sql + " and(";
			for(int i=0;i<SearchItemCd.size();i++) {
				if(0<i) {sql = sql + " or ";}
				sql = sql + " WW00630ItemRecomendLoc.ItemCd = ?";
			}
			sql = sql + ")\n";
		}

		if(null!=SearchRecomendLoc && 0<SearchRecomendLoc.size()) {
			SearchKick = true;

			sql = sql + " and(";
			for(int i=0;i<SearchRecomendLoc.size();i++) {
				if(0<i) {sql = sql + " or ";}
				if(LocExactMatch) {
					sql = sql + " WW00630ItemRecomendLoc.RecomendLoc = ?";
				}else {
					sql = sql + " WW00630ItemRecomendLoc.RecomendLoc like ?";
				}
			}
			sql = sql + ")\n";
		}
		if(null!=SearchClGpCD && 0<SearchClGpCD.size()) {
			SearchKick = true;

			sql = sql + " and(";
			for(int i=0;i<SearchClGpCD.size();i++) {
				if(0<i) {sql = sql + " or ";}
				sql = sql + " KM0030_CLIENTMST.ClGpCD = ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchItemName01 && 0<SearchItemName01.size()) {
			SearchKick = true;

			sql = sql + " and(";
			for(int i=0;i<SearchItemName01.size();i++) {
				if(0<i) {sql = sql + " or ";}
				sql = sql + " KM0060_ITEMMST.ItemName01 like ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchLocName && 0<SearchLocName.size()) {
			SearchKick = true;

			sql = sql + " and(";
			for(int i=0;i<SearchLocName.size();i++) {
				if(0<i) {sql = sql + " or ";}
				sql = sql + " WM0010LOCATIONMST.LocName like ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchType && 0<SearchType.size()) {
			SearchKick = true;

			sql = sql + " and(";
			for(int i=0;i<SearchType.size();i++) {
				if(0<i) {sql = sql + " or ";}
				sql = sql + " WM0010LOCATIONMST.LocType = ?";
			}
			sql = sql + ")\n";
		}
		
		sql = sql + " order by WW00630ItemRecomendLoc.ClCd,WW00630ItemRecomendLoc.ClWh,WW00630ItemRecomendLoc.ItemCd";
		//System.out.println(sql);

		if(true==SearchKick) {
			A100_DbConnect.DB_CONN("WANKO");
			ResultSet rset01 = null;
			PreparedStatement stmt01 = null;
			try {
				stmt01 = A100_DbConnect.conn.prepareStatement(sql);
				int StmtCount = 0;
				if(null!=SearchClCd && 0<SearchClCd.size()) {
					for(int i=0;i<SearchClCd.size();i++) {
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchClCd.get(i)+"");
					}
				}
		
				if(null!=SearchClWh && 0<SearchClWh.size()) {
					for(int i=0;i<SearchClWh.size();i++) {
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchClWh.get(i)+"");
					}
				}
		
				if(null!=SearchItemCd && 0<SearchItemCd.size()) {
					for(int i=0;i<SearchItemCd.size();i++) {
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchItemCd.get(i)+"");
					}
				}
		
				if(null!=SearchRecomendLoc && 0<SearchRecomendLoc.size()) {
					for(int i=0;i<SearchRecomendLoc.size();i++) {
						StmtCount = StmtCount+1;
						if(LocExactMatch) {
							stmt01.setString(StmtCount, ""+SearchRecomendLoc.get(i)+"");
						}else {
							stmt01.setString(StmtCount, ""+SearchRecomendLoc.get(i)+"%");
						}
					}
				}
				if(null!=SearchClGpCD && 0<SearchClGpCD.size()) {
					for(int i=0;i<SearchClGpCD.size();i++) {
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchClGpCD.get(i)+"");
					}
				}
				if(null!=SearchItemName01 && 0<SearchItemName01.size()) {
					for(int i=0;i<SearchItemName01.size();i++) {
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchItemName01.get(i)+"%");
					}
				}
				if(null!=SearchLocName && 0<SearchLocName.size()) {
					for(int i=0;i<SearchLocName.size();i++) {
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchLocName.get(i)+"%");
					}
				}
				if(null!=SearchType && 0<SearchType.size()) {
					for(int i=0;i<SearchType.size();i++) {
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchType.get(i)+"");
					}
				}
				
				rset01 = stmt01.executeQuery();
				
				int counter = 0;
				rset01.beforeFirst();
				while (rset01.next()) {
					counter=counter+1;
				}
				
				rt = new Object[counter][RtItemRecomendLocMstRt().length];
				counter = 0;
				rset01.beforeFirst();
				while (rset01.next()) {
					if(null==rset01.getString("ClCd"				)){rt[counter][ColClCd]			="";}else{rt[counter][ColClCd]			=rset01.getString("ClCd");}			//荷主コード
					if(null==rset01.getString("CLName"				)){rt[counter][ColCLName]			="";}else{rt[counter][ColCLName]		=rset01.getString("CLName");}		//荷主表記名
					if(null==rset01.getString("ClWHName"			)){rt[counter][ColClWh]			="";}else{rt[counter][ColClWh]			=rset01.getString("ClWh");}			//担当倉庫コード
					if(null==rset01.getString("ClWHName"			)){rt[counter][ColClWHName]		="";}else{rt[counter][ColClWHName]		=rset01.getString("ClWHName");}		//担当倉庫名
					if(null==rset01.getString("ClGpCD"				)){rt[counter][ColClGpCD]			="";}else{rt[counter][ColClGpCD]		=rset01.getString("ClGpCD");}		//荷主グループCD
					if(null==rset01.getString("ClGpName"			)){rt[counter][ColClGpName]		="";}else{rt[counter][ColClGpName]		=rset01.getString("ClGpName");}		//グループ名1
					if(null==rset01.getString("ItemCd"				)){rt[counter][ColItemCd]			="";}else{rt[counter][ColItemCd]		=rset01.getString("ItemCd");}		//商品コード
					if(null==rset01.getString("ItemName01"			)){rt[counter][ColItemName01]		="";}else{rt[counter][ColItemName01]	=rset01.getString("ItemName01");}	//商品名1
					if(null==rset01.getString("RecomendLoc"			)){rt[counter][ColRecomendLoc]	="";}else{rt[counter][ColRecomendLoc]	=rset01.getString("RecomendLoc");}	//推奨ロケ
					if(null==rset01.getString("LocName"				)){rt[counter][ColLocName]			="";}else{rt[counter][ColLocName]		=rset01.getString("LocName");}		//ロケーション名
					rt[counter][ColType]= rset01.getInt("Type");						//ロケタイプ　0:通常　1:保管　8:入荷時　9:引当禁止
					if(null==rset01.getTimestamp("EntryDate")		){rt[counter][ColEntryDate]		="";}else{rt[counter][ColEntryDate]	=B100_DateTimeControl.dtmString2(rset01.getTimestamp("EntryDate"))[1];}	//登録日
					if(null==rset01.getTimestamp("UpdateDate")		){rt[counter][ColUpdateDate]		="";}else{rt[counter][ColUpdateDate]	=B100_DateTimeControl.dtmString2(rset01.getTimestamp("UpdateDate"))[1];}	//更新日
					if(null==rset01.getString("EntryUser")			){rt[counter][ColEntryUser]		="";}else{rt[counter][ColEntryUser]	=rset01.getString("EntryUser");}						//登録者
					if(null==rset01.getString("UpdateUser")			){rt[counter][ColUpdateUser]		="";}else{rt[counter][ColUpdateUser]	=rset01.getString("UpdateUser");}						//更新者
					if(null==rset01.getString("ItemSubRecomendLoc")	){rt[counter][ColItemSubRecomendLoc]="";}else{rt[counter][ColItemSubRecomendLoc]	=rset01.getString("ItemSubRecomendLoc");}	//商品サブマスタ推奨ロケ",""}
					
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
			A100_DbConnect.close();
		}
		return rt;
	}
	
	public static Object[][] ItemRecomendLocFromItemCd(String ClCd,ArrayList<String> ItemCd,boolean ItemMstRecomendLocExistenceOnly) {
		//荷主コードと商品CDを受け取ってWW00630ItemRecomendLocの推奨ロケとKM0061_ITEMMSTSUBの推奨ロケを返す
		int RtRowCount = 0;
		if(null!=ItemCd) {RtRowCount=ItemCd.size();}
		Object[][] rt = new Object[RtRowCount][RtItemRecomendLocMstRt().length];
		
		//荷主マスタ取得
		String SetCLName	="";		//荷主表記名
		String SetClWh		="";		//担当倉庫コード
		String SetClWHName	="";		//担当倉庫名
		String SetClGpCD	="";		//荷主グループCD
		String SetClGpName	="";		//グループ名1
		
		if(null == ClCd) {ClCd="";}	if("".equals(ClCd)) {ClCd=A00000_Main.ClCd;}
		
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
		if(0<ClMstRt.length) {
			SetCLName	=(String)ClMstRt[0][M100_ClMstRt.ColCLName01];		//荷主表記名
			SetClWh		=(String)ClMstRt[0][M100_ClMstRt.ColWHCD];			//担当倉庫コード
			SetClWHName	=(String)ClMstRt[0][M100_ClMstRt.ColWHName];			//担当倉庫名
			SetClGpCD	=(String)ClMstRt[0][M100_ClMstRt.ColClGpCD];			//荷主グループCD
			SetClGpName	=(String)ClMstRt[0][M100_ClMstRt.ColClGpName];		//グループ名1
		}
		
		ArrayList<String> SearchClCd		= new ArrayList<String>();	//荷主コード
		ArrayList<String> SearchClWh		= new ArrayList<String>();	//担当倉庫コード
		SearchClGpCD		= new ArrayList<String>();	//荷主グループCD
		ArrayList<String> SearchItemCd		= new ArrayList<String>();	//商品コード
		ArrayList<String> SearchItemName01	= new ArrayList<String>();	//商品名1
		ArrayList<String> SearchRecomendLoc	= new ArrayList<String>();	//推奨ロケ
		ArrayList<String> SearchLocName		= new ArrayList<String>();	//ロケーション名
		ArrayList<Integer> SearchType		= new ArrayList<Integer>();	//ロケタイプ　0:通常　1:保管　8:入荷時　9:引当禁止
		boolean LocExactMatch = false;									//ロケーション完全一致
		AllSearch = false;
		
		SearchClCd.add(ClCd);
		
		for(int i=0;i<RtRowCount;i++) {
			SearchItemCd.add(ItemCd.get(i));
		}
		
		Object[][] ItemRecomendLocMstRt	= M100_ItemRecomendLocMstRt.ItemRecomendLocMstRt(
				SearchClCd,			//荷主コード
				SearchClWh,			//担当倉庫コード
				SearchClGpCD,		//荷主グループCD
				SearchItemCd,		//商品コード
				SearchItemName01,	//商品名1
				SearchRecomendLoc,	//推奨ロケ
				SearchLocName,		//ロケーション名
				SearchType,			//ロケタイプ　0:通常　1:保管　8:入荷時　9:引当禁止
				LocExactMatch,		//ロケーション完全一致
				AllSearch);
		
		ArrayList<String> RetryItemList = new ArrayList<String>();
		ArrayList<Integer> RetryRow = new ArrayList<Integer>();
			
		for(int i01=0;i01<ItemCd.size();i01++) {
			rt[i01][ColClCd]			=ClCd;				//荷主コード
			rt[i01][ColCLName]			=SetCLName;			//荷主表記名
			rt[i01][ColClWh]			=SetClWh;			//担当倉庫コード
			rt[i01][ColClWHName]		=SetClWHName;		//担当倉庫名
			rt[i01][ColClGpCD]			=SetClGpCD;			//荷主グループCD
			rt[i01][ColClGpName]		=SetClGpName;		//グループ名1
			rt[i01][ColItemCd]			=ItemCd.get(i01);	//商品コード
			rt[i01][ColItemName01]	="";		//商品名1
			rt[i01][ColRecomendLoc]	="";		//推奨ロケ
			rt[i01][ColLocName]		="";		//ロケーション名
			rt[i01][ColType]	=0;					//ロケタイプ　0:通常　1:保管　8:入荷時　9:引当禁止
			rt[i01][ColEntryDate]		="";		//登録日
			rt[i01][ColUpdateDate]	="";		//更新日
			rt[i01][ColEntryUser]		="";		//登録者
			rt[i01][ColUpdateUser]	="";		//更新者
			rt[i01][ColItemSubRecomendLoc]="";	//商品サブマスタ推奨ロケ",""}
			boolean UnHitFg = true;
			
			for(int i=0;i<ItemRecomendLocMstRt.length;i++) {
				String GetClCd			= (String)ItemRecomendLocMstRt[i][M100_ItemRecomendLocMstRt.ColClCd];			//荷主コード
				String GetCLName		= (String)ItemRecomendLocMstRt[i][M100_ItemRecomendLocMstRt.ColCLName];		//荷主表記名
				String GetClWh			= (String)ItemRecomendLocMstRt[i][M100_ItemRecomendLocMstRt.ColClWh];			//担当倉庫コード
				String GetClWHName		= (String)ItemRecomendLocMstRt[i][M100_ItemRecomendLocMstRt.ColClWHName];		//担当倉庫名
				String GetClGpCD		= (String)ItemRecomendLocMstRt[i][M100_ItemRecomendLocMstRt.ColClGpCD];		//荷主グループCD
				String GetClGpName		= (String)ItemRecomendLocMstRt[i][M100_ItemRecomendLocMstRt.ColClGpName];		//グループ名1
				String GetItemCd		= (String)ItemRecomendLocMstRt[i][M100_ItemRecomendLocMstRt.ColItemCd];		//商品コード
				String GetItemName01	= (String)ItemRecomendLocMstRt[i][M100_ItemRecomendLocMstRt.ColItemName01];	//商品名1
				String GetRecomendLoc	= (String)ItemRecomendLocMstRt[i][M100_ItemRecomendLocMstRt.ColRecomendLoc];	//推奨ロケ
				String GetLocName		= (String)ItemRecomendLocMstRt[i][M100_ItemRecomendLocMstRt.ColLocName];		//ロケーション名
				int  GetType			= (int)ItemRecomendLocMstRt[i][M100_ItemRecomendLocMstRt.ColType];			//ロケタイプ　0:通常　1:保管　8:入荷時　9:引当禁止
				String GetEntryDate		= (String)ItemRecomendLocMstRt[i][M100_ItemRecomendLocMstRt.ColEntryDate];	//データ登録日時
				String GetUpdateDate	= (String)ItemRecomendLocMstRt[i][M100_ItemRecomendLocMstRt.ColUpdateDate];	//データ更新日時
				String GetEntryUser		= (String)ItemRecomendLocMstRt[i][M100_ItemRecomendLocMstRt.ColEntryUser];	//登録者
				String GetUpdateUser	= (String)ItemRecomendLocMstRt[i][M100_ItemRecomendLocMstRt.ColUpdateUser];	//更新者
				String GetItemSubRecomendLoc	= (String)ItemRecomendLocMstRt[i][M100_ItemRecomendLocMstRt.ColItemSubRecomendLoc];	//商品サブマスタ推奨ロケ
				
				
				if(GetClCd.equals(ClCd)&&GetItemCd.equals(ItemCd.get(i01))) {
					rt[i01][ColClCd]			=ClCd;					//荷主コード
					rt[i01][ColCLName]			=GetCLName;				//荷主表記名
					rt[i01][ColClWh]			=GetClWh;				//担当倉庫コード
					rt[i01][ColClWHName]		=GetClWHName;			//担当倉庫名
					rt[i01][ColClGpCD]			=GetClGpCD;				//荷主グループCD
					rt[i01][ColClGpName]		=GetClGpName;			//グループ名1
					rt[i01][ColItemCd]			=ItemCd.get(i01);		//商品コード
					rt[i01][ColItemName01]	=GetItemName01;			//商品名1
					rt[i01][ColRecomendLoc]	=GetRecomendLoc;		//推奨ロケ
					rt[i01][ColLocName]		=GetLocName;			//ロケーション名
					rt[i01][ColType]			=GetType;				//ロケタイプ　0:通常　1:保管　8:入荷時　9:引当禁止
					rt[i01][ColEntryDate]		=GetEntryDate;			//登録日
					rt[i01][ColUpdateDate]	=GetUpdateDate;			//更新日
					rt[i01][ColEntryUser]		=GetEntryUser;			//登録者
					rt[i01][ColUpdateUser]	=GetUpdateUser;			//更新者
					rt[i01][ColItemSubRecomendLoc]=GetItemSubRecomendLoc;	//商品サブマスタ推奨ロケ",""}
					
					UnHitFg = false;
					i=ItemRecomendLocMstRt.length+1;
				}
			}

			if(UnHitFg) {
				RetryItemList.add(ItemCd.get(i01));
				RetryRow.add(i01);
			}
		}
		if(null!=RetryItemList && 0<RetryItemList.size()) {
			//推奨ロケマスタ由来で紹鴎取得できなければ商品マスタ由来で情報取得
			ArrayList<String> SearchClGpCd 				= new ArrayList<String>();	//荷主グループコード
			SearchItemCd = RetryItemList;	//商品コード
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
			AllSearch = false;
			
			SearchClGpCd.add(SetClGpCD);
			
			Object[][] ItemMstRt = M100_ItemMstRt.ItemMstRt(
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
			
			for(int i01=0;i01<RetryRow.size();i01++) {
				for(int i=0;i<ItemMstRt.length;i++) {
					String GetItemCd				= (String)ItemMstRt[i][M100_ItemMstRt.ColItemCd];				//商品コード
					String GetClGpCd				= (String)ItemMstRt[i][M100_ItemMstRt.ColClGpCd];				//荷主グループコード
					String GetCLGpName01			= (String)ItemMstRt[i][M100_ItemMstRt.ColCLGpName01];		//荷主グループ名1
					String GetItemName01			= (String)ItemMstRt[i][M100_ItemMstRt.ColItemName01];		//商品名1
					String GetRecomendLoc			= (String)ItemMstRt[i][M100_ItemMstRt.ColRecomendLoc];		//推奨ロケ
					String GetEntryDate				= (String)ItemMstRt[i][M100_ItemMstRt.ColEntryDate];			//データ登録日時
					String GetUpdateDate			= (String)ItemMstRt[i][M100_ItemMstRt.ColUpdateDate];		//データ更新日時
					String GetEntryUser				= (String)ItemMstRt[i][M100_ItemMstRt.ColEntryUser];			//登録者コード
					String GetUpdateUser			= (String)ItemMstRt[i][M100_ItemMstRt.ColUpdateUser];		//更新者コード
					
					if(GetItemCd.equals((String)rt[RetryRow.get(i01)][ColItemCd])) {
						rt[RetryRow.get(i01)][ColClCd]			=ClCd;					//荷主コード
						rt[RetryRow.get(i01)][ColCLName]		=SetCLName;				//荷主表記名
						rt[RetryRow.get(i01)][ColClWh]			=SetClWh;				//担当倉庫コード
						rt[RetryRow.get(i01)][ColClWHName]		=SetClWHName;			//担当倉庫名
						rt[RetryRow.get(i01)][ColClGpCD]		=GetClGpCd;				//荷主グループCD
						rt[RetryRow.get(i01)][ColClGpName]		=GetCLGpName01;			//グループ名1
						rt[RetryRow.get(i01)][ColItemCd]		=ItemCd.get(i01);		//商品コード
						rt[RetryRow.get(i01)][ColItemName01]	=GetItemName01;			//商品名1
						rt[RetryRow.get(i01)][ColRecomendLoc]	="";					//推奨ロケ
						rt[RetryRow.get(i01)][ColLocName]		="";					//ロケーション名
						rt[RetryRow.get(i01)][ColType]			=0;						//ロケタイプ　0:通常　1:保管　8:入荷時　9:引当禁止
						rt[RetryRow.get(i01)][ColEntryDate]	=GetEntryDate;			//登録日
						rt[RetryRow.get(i01)][ColUpdateDate]	=GetUpdateDate;			//更新日
						rt[RetryRow.get(i01)][ColEntryUser]	=GetEntryUser;			//登録者
						rt[RetryRow.get(i01)][ColUpdateUser]	=GetUpdateUser;			//更新者
						rt[RetryRow.get(i01)][ColItemSubRecomendLoc]=GetRecomendLoc;	//商品サブマスタ推奨ロケ
					}
				}
			}
			if(ItemMstRecomendLocExistenceOnly) {
				//商品サブマスタ由来の推奨ロケがロケーションマスタに存在しなければ空白で置換
				SearchClCd 	= new ArrayList<String>();							//荷主コード
				ArrayList<String> SearchWhCd 	= new ArrayList<String>();		//倉庫コード
				ArrayList<String> SearchLoc 	= new ArrayList<String>();		//ロケーション
				SearchLocName = new ArrayList<String>();						//ロケーション名
				ArrayList<String> SearchLocType 	= new ArrayList<String>();	//ロケタイプ
				LocExactMatch = true;											//ロケーション完全一致
				AllSearch = false;
				
				SearchClCd.add(ClCd);
				for(int i=0;i<rt.length;i++) {
					if(!"".equals((String)rt[i][ColItemSubRecomendLoc])) {
						SearchLoc.add((String)rt[i][ColItemSubRecomendLoc]);
					}
				}
				
				Object[][] LocationMstRt = M100_LocationMstRt.LocationMstRt(
						SearchClCd,		//荷主コード
						SearchWhCd,		//倉庫コード
						SearchLoc,		//ロケーション
						SearchLocName,	//ロケーション名
						SearchLocType,	//ロケタイプ
						LocExactMatch,	//ロケーション完全一致
						AllSearch);
				for(int i01=0;i01<rt.length;i01++) {
					String CheckLoc = (String)rt[i01][ColItemSubRecomendLoc];
					boolean UnHitFg = true;
					if(!"".equals(CheckLoc)) {
						for(int i=0;i<LocationMstRt.length;i++) {
							String GetLoc			= (String)LocationMstRt[i][M100_LocationMstRt.ColLoc];			//ロケーション
							if(CheckLoc.equals(GetLoc)) {
								UnHitFg = false;
								i=LocationMstRt.length+1;
							}
						}
					}
					if(UnHitFg) {
						rt[i01][ColItemSubRecomendLoc]="";	
					}
				}
			}
		}
		return rt;
	}
	
}