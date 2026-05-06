import java.util.ArrayList;

public class M00120ItemRecomendLocMstRt{
	//荷主ー商品ごとの推奨ロケを返却する
	static final int ColClCd			= (int) 0;	//荷主コード
	static final int ColCLName01		= (int) 1;	//荷主名1
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
	
	public static Object[][] RtItemRecomendLocMstRt() {
		Object[][] RtItemRecomendLocMstRt = {
					 {"ClCd"		,ColClCd			,"String"	,"荷主コード"		,"key"}
					,{"CLName01"	,ColCLName01		,"String"	,"荷主名1"			,""}
					,{"ClWh"		,ColClWh			,"String"	,"担当倉庫コード"	,"key"}
					,{"ClWHName"	,ColClWHName		,"String"	,"担当倉庫名"		,""}
					,{"ClGpCD"		,ColClGpCD			,"String"	,"荷主グループCD"	,""}
					,{"ClGpName"	,ColClGpName		,"String"	,"グループ名1"		,""}
					,{"ItemCd"		,ColItemCd			,"String"	,"商品コード"		,"key"}
					,{"ItemName01"	,ColItemName01	,"String"	,"商品名1"			,""}
					,{"RecomendLoc"	,ColRecomendLoc	,"String"	,"推奨ロケ"			,""}
					,{"LocName"		,ColLocName		,"String"	,"ロケーション名"	,""}
					,{"Type"		,ColType			,"int"		,"ロケタイプ"		,""}
					,{"EntryDate"	,ColEntryDate		,"String"	,"データ登録日時"	,""}
					,{"UpdateDate"	,ColUpdateDate	,"String"	,"データ更新日時"	,""}
					,{"EntryUser"	,ColEntryUser		,"String"	,"登録者"			,""}
					,{"UpdateUser"	,ColUpdateUser	,"String"	,"更新者"			,""}
					};
		return RtItemRecomendLocMstRt;
	}
	
	public static Object[][] RtItemRecomendLocMstRt(
			ArrayList<String> SearchClCd,			//荷主コード
			ArrayList<String> SearchClWh,			//担当倉庫コード
			ArrayList<String> SearchClGpCD,			//荷主グループCD
			ArrayList<String> SearchItemCd,			//商品コード
			ArrayList<String> SearchItemName01,		//商品名1
			ArrayList<String> SearchRecomendLoc,	//推奨ロケ
			ArrayList<String> SearchLocName,		//ロケーション名
			ArrayList<Integer> SearchType,			//ロケタイプ　0:通常　1:保管　8:入荷時　9:引当禁止
			boolean AllSearch){
		
		SearchClCd			= B00150ArrayListControl.ArryListStringUniqueList(SearchClCd);					//荷主コード
		SearchClWh			= B00150ArrayListControl.ArryListStringUniqueList(SearchClWh);					//担当倉庫コード
		SearchClGpCD		= B00150ArrayListControl.ArryListStringUniqueList(SearchClGpCD);				//荷主グループCD
		SearchItemCd		= B00150ArrayListControl.ArryListStringUniqueList(SearchItemCd);				//商品コード
		SearchItemName01	= B00150ArrayListControl.ArryListStringUniqueList(SearchItemName01);			//商品名1
		SearchRecomendLoc	= B00150ArrayListControl.ArryListStringUniqueList(SearchRecomendLoc);			//推奨ロケ
		SearchLocName		= B00150ArrayListControl.ArryListStringUniqueList(SearchLocName);				//ロケーション名
		SearchType			= B00150ArrayListControl.ArryListIntegerUniqueList(SearchType);					//ロケタイプ　0:通常　1:保管　8:入荷時　9:引当禁止
		
		boolean SearchKick = false;
		if(AllSearch) {SearchKick = true;}
		
		Object[][] rt = new Object[0][RtItemRecomendLocMstRt().length];
		String sql = "select "
				+"(WW00630ItemRecomendLoc.ClCd) as ClCd,\n"					//荷主コード
				+"(KM0030_CLIENTMST.CLName01) as CLName01,"					//荷主名1
				+"(WW00630ItemRecomendLoc.ClWh) as ClWh,\n"					//担当倉庫コード
				+"(KM0010_WHMST.WHName) as ClWHName,\n"						//担当倉庫名
				+"(KM0030_CLIENTMST.ClGpCD) as ClGpCD,"						//荷主グループCD
				+"(KM0031_CLIENT_GROUP.ClGpName01) as ClGpName,"			//グループ名1
				+"(WW00630ItemRecomendLoc.ItemCd) as ItemCd,\n"				//商品コード
				+"(KM0060_ITEMMST.ItemName01) as ItemName01,\n"				//商品名1
				+"(WW00630ItemRecomendLoc.RecomendLoc) as RecomendLoc,\n"	//推奨ロケ
				+"(WM0010LOCATIONMST.LocName) as LocName,\n"				//ロケーション名
				+"(WM0010LOCATIONMST.Type) as Type,\n"						//ロケタイプ　0:通常　1:保管　8:入荷時　9:引当禁止
				+"(WW00630ItemRecomendLoc.EntryDate) as EntryDate,\n"		//データ登録日時
				+"(WW00630ItemRecomendLoc.UpdateDate) as UpdateDate,\n"		//データ更新日時
				+"(WW00630ItemRecomendLoc.EntryUser) as EntryUser,\n"		//登録者
				+"(WW00630ItemRecomendLoc.UpdateUser) as UpdateUser\n"		//更新者
				+ " from "+A00000Main.MySqlDefaultSchemaWANKO+".WW00630ItemRecomendLoc"
				+ " left outer join "+A00000Main.MySqlDefaultSchemaWANKO+".WM0010LOCATIONMST"
				+ " on("
				+ " WW00630ItemRecomendLoc.ClCd = WM0010LOCATIONMST.ClCd"
				+ " and WW00630ItemRecomendLoc.ClWh = WM0010LOCATIONMST.WhCd"
				+ " and WW00630ItemRecomendLoc.RecomendLoc= WM0010LOCATIONMST.Loc"
				+ ")\n"
				+ " left outer join "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0030_CLIENTMST"
				+ " on("
				+ " WW00630ItemRecomendLoc.ClWh = KM0030_CLIENTMST.WHCD"
				+ " and WW00630ItemRecomendLoc.ClCd = KM0030_CLIENTMST.cl_cd"
				+ ")\n"
				+ " left outer join "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST"
				+ " on("
				+ " WW00630ItemRecomendLoc.ClWh = KM0010_WHMST.WHCD"
				+ ")\n"
				+ " left outer join "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0060_ITEMMST"
				+ " on("
				+ " KM0030_CLIENTMST.ClGpCD = KM0060_ITEMMST.ClGpCd"
				+ " and WW00630ItemRecomendLoc.ItemCd = KM0060_ITEMMST.ItemCd"
				+ ")\n"
				+ " where 1=1 \n";
		if(null!=SearchClCd && 0<SearchClCd.size()) {
			SearchKick = true;

			sql = sql + " and(";
			for(int i=0;i<SearchClCd.size();i++) {
				if(0<i) {sql = sql + " or ";}
				sql = sql + " WW00630ItemRecomendLoc.ClCd = ?";
			}
			sql = sql + ")";
		}

		if(null!=SearchClWh && 0<SearchClWh.size()) {
			SearchKick = true;

			sql = sql + " and(";
			for(int i=0;i<SearchClWh.size();i++) {
				if(0<i) {sql = sql + " or ";}
				sql = sql + " WW00630ItemRecomendLoc.ClWh = ?";
			}
			sql = sql + ")";
		}

		if(null!=SearchItemCd && 0<SearchItemCd.size()) {
			SearchKick = true;

			sql = sql + " and(";
			for(int i=0;i<SearchItemCd.size();i++) {
				if(0<i) {sql = sql + " or ";}
				sql = sql + " WW00630ItemRecomendLoc.ItemCd = ?";
			}
			sql = sql + ")";
		}

		if(null!=SearchRecomendLoc && 0<SearchRecomendLoc.size()) {
			SearchKick = true;

			sql = sql + " and(";
			for(int i=0;i<SearchRecomendLoc.size();i++) {
				if(0<i) {sql = sql + " or ";}
				sql = sql + " WW00630ItemRecomendLoc.RecomendLoc like ?";
			}
			sql = sql + ")";
		}
		
		sql = sql + " order by WW00630ItemRecomendLoc.ClCd,WW00630ItemRecomendLoc.ClWh,WW00630ItemRecomendLoc.ItemCd";
		
		
		return rt;
	}
}