public class M00120ItemRecomendMstRt{
	//荷主ー商品ごとの推奨ロケを返却する
	
	static final int ColClCd			= (int) 0;	//荷主コード
	static final int ColCLName01		= (int) 1;	//荷主名
	static final int ColClWh			= (int) 2;	//担当倉庫コード
	static final int ColClWhName		= (int) 3;	//担当倉庫名
	static final int ColItemCd			= (int) 4;	//商品コード
	static final int ColItemName01		= (int) 5;	//商品名1
	static final int ColRecomendLoc	= (int) 6;	//推奨ロケ
	static final int ColLocName			= (int) 7;	//推奨ロケ名
	static final int ColType			= (int) 8;	//推奨ロケタイプ
	static final int ColEntryDate		= (int) 9;	//登録日時
	static final int ColUpdateDate		= (int)10;	//更新日時
	static final int ColEntryUser		= (int)11;	//登録者
	static final int ColUpdateUser		= (int)12;	//更新者
	
	public static Object[][] RtItemRecomendMstRt() {
		Object[][] RtAdjustReasonRt = {
				 	 {"ClCd"		,ColClCd			,"String"	,"荷主コード"		,"Key"}
					,{"ClName01"	,ColCLName01		,"String"	,"荷主名"			,""}
					,{"ClWh"		,ColClWh			,"String"	,"担当倉庫コード"	,"Key"}
					,{"ClWhName"	,ColClWhName		,"String"	,"担当倉庫名"		,""}
					,{"ItemCd"		,ColItemCd			,"String"	,"商品コード"		,"Key"}
					,{"ItemName01"	,ColItemName01		,"String"	,"商品名1"			,""}
					,{"RecomendLoc"	,ColRecomendLoc	,"String"	,"推奨ロケ"			,""}
					,{"LocName"		,ColLocName			,"String"	,"推奨ロケ名"		,""}
					,{"Type"		,ColType			,"int"		,"推奨ロケタイプ"	,""}
					,{"EntryDate"	,ColEntryDate		,"String"	,"登録日時"			,""}
					,{"UpdateDate"	,ColUpdateDate		,"String"	,"更新日時"			,""}
					,{"EntryUser"	,ColEntryUser		,"String"	,"登録者"			,""}
					,{"UpdateUser"	,ColUpdateUser		,"String"	,"更新者"			,""}
					};
		return RtAdjustReasonRt;
	}
	
	public static Object[][] ItemRecomendMstRt(
			
			
			boolean AllSearch){
		Object[][] rt = new Object[0][RtItemRecomendMstRt().length];
		String sql = "select "
				+ ""
				/*
				+"(WW00630ItemRecomend.ClCd) as ClCd,\n"	//荷主コード
				+"(WW00630ItemRecomend.ItemCd) as ItemCd,\n"	//商品コード
				+"(WW00630ItemRecomend.RecomendLoc) as RecomendLoc,\n"	//推奨ロケ
				+"(WW00630ItemRecomend.EntryDate) as EntryDate,\n"	//データ登録日時
				+"(WW00630ItemRecomend.UpdateDate) as UpdateDate,\n"	//データ更新日時
				+"(WW00630ItemRecomend.EntryUser) as EntryUser,\n"	//登録者
				+"(WW00630ItemRecomend.UpdateUser) as UpdateUser\n"	//更新者

				
				*/
				+ " from "+A00000Main.MySqlDefaultSchemaWANKO+".WW00630ItemRecomend"
				+ " left outer join "+A00000Main.MySqlDefaultSchemaWANKO+".WM0010LOCATIONMST"
				+ " on("
				+ " "
				+ ")\n"
				+ " left outer join "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0030_CLIENTMST"
				+ " on("
				+ " "
				+ ")\n"
				+ " where 1=1 \n";
		
		return rt;
	}
}