public class T00030StockRt{
	
	static final int ColClCd			= (int)0;		//荷主コード
	static final int ColCLName			= (int)1;		//荷主名1
	static final int ColWhCd			= (int)2;		//倉庫コード
	static final int ColClWHName		= (int)3;		//担当倉庫名
	static final int ColClGpCD			= (int)4;		//荷主グループCD
	static final int ColClGpName		= (int)5;		//グループ名1
	static final int ColLoc			= (int)6;		//ロケーション
	static final int ColLocName		= (int)7;		//ロケーション名
	static final int ColType			= (int)8;		//ロケタイプ
	static final int ColItemCd			= (int)9;		//商品コード
	static final int ColLot			= (int)10;		//ロット
	static final int ColExpdate		= (int)11;		//消費期限
	static final int ColActualDate	= (int)12;		//入荷実績日
	static final int ColQty			= (int)13;		//数量
	static final int ColShipPlanQty	= (int)14;		//引当済数
	static final int ColPossibleQty	= (int)15;		//出荷可能数
	static final int ColItemName		= (int)16;		//商品名
	static final int ColItemName01	= (int)17;		//商品名1
	static final int ColItemName02	= (int)18;		//商品名2
	static final int ColItemName03	= (int)19;		//商品名3
	static final int ColClItemCd		= (int)20;		//荷主商品コード
	static final int ColJanCd			= (int)21;		//ソースマーク_BCD（バラ）
	static final int ColItemMdNo		= (int)22;		//商品型番
	static final int ColCtUnitQty		= (int)23;		//カートン入数
	static final int ColCsUnitQty		= (int)24;		//ケース入数
	static final int ColPlUnitQty		= (int)25;		//パレット入数
	static final int ColUnitName		= (int)26;		//商品単位
	static final int ColCtUnitName	= (int)27;		//カートン商品単位
	static final int ColCsUnitName	= (int)28;		//ケース商品単位
	static final int ColPlUnitName	= (int)29;		//パレット商品単位
	static final int ColEntryDate		= (int)30;		//登録日時
	static final int ColUpdateDate	= (int)31;		//更新日時
	static final int ColEntryUser		= (int)32;		//登録者
	static final int ColUpdateUser	= (int)33;		//更新者
	
	public static Object[][] RtStockRt(){
		Object[][] RtStockRt = {
				 {"ClCd"		,ColClCd			,"String"	,"荷主コード"					,"key"}
				,{"CLName"		,ColCLName			,"String"	,"荷主名1"						,""}
				,{"WhCd"		,ColWhCd			,"String"	,"倉庫コード"					,"key"}
				,{"ClWHName"	,ColClWHName		,"String"	,"担当倉庫名"					,""}
				,{"ClGpCD"		,ColClGpCD			,"String"	,"荷主グループCD"				,""}
				,{"ClGpName"	,ColClGpName		,"String"	,"グループ名1"					,""}
				,{"Loc"			,ColLoc			,"String"	,"ロケーション"					,"key"}
				,{"LocName"		,ColLocName		,"String"	,"ロケーション名"				,""}
				,{"Type"		,ColType			,"int"		,"ロケタイプ"					,""}
				,{"ItemCd"		,ColItemCd			,"String"	,"商品コード"					,"key"}
				,{"Lot"			,ColLot			,"String"	,"ロット"						,"key"}
				,{"Expdate"		,ColExpdate		,"DateTime"	,"消費期限"						,"key"}
				,{"ActualDate"	,ColActualDate	,"DateTime"	,"入荷実績日"					,"key"}
				,{"Qty"			,ColQty			,"int"		,"数量"							,""}
				,{"ShipPlanQty"	,ColShipPlanQty	,"int"		,"引当済数"						,""}
				,{"PossibleQty"	,ColPossibleQty	,"int"		,"出荷可能数"					,""}
				,{"ItemName"	,ColItemName		,"String"	,"商品名"						,""}
				,{"ItemName01"	,ColItemName01	,"String"	,"商品名1"						,""}
				,{"ItemName02"	,ColItemName02	,"String"	,"商品名2"						,""}
				,{"ItemName03"	,ColItemName03	,"String"	,"商品名3"						,""}
				,{"ClItemCd"	,ColClItemCd		,"String"	,"荷主商品コード"				,""}
				,{"JanCd"		,ColJanCd			,"String"	,"ソースマーク_BCD（バラ）"		,""}
				,{"ItemMdNo"	,ColItemMdNo		,"String"	,"商品型番"						,""}
				,{"CtUnitQty"	,ColCtUnitQty		,"String"	,"カートン入数"					,""}
				,{"CsUnitQty"	,ColCsUnitQty		,"String"	,"ケース入数"					,""}
				,{"PlUnitQty"	,ColPlUnitQty		,"String"	,"パレット入数"					,""}
				,{"UnitName"	,ColUnitName		,"String"	,"商品単位"						,""}
				,{"CtUnitName"	,ColCtUnitName	,"String"	,"カートン商品単位"				,""}
				,{"CsUnitName"	,ColCsUnitName	,"String"	,"ケース商品単位"				,""}
				,{"PlUnitName"	,ColPlUnitName	,"String"	,"パレット商品単位"				,""}
				,{"EntryDate"	,ColEntryDate		,"DateTime"	,"登録日時"						,""}
				,{"UpdateDate"	,ColUpdateDate	,"DateTime"	,"更新日時"						,""}
				,{"EntryUser"	,ColEntryUser		,"String"	,"登録者"						,""}
				,{"UpdateUser"	,ColUpdateUser	,"String"	,"更新者"						,""}
				};
		return RtStockRt;
	}
	
	public static Object[][] StockRt(){
		Object[][] rt = new Object[0][RtStockRt().length];
		
		String sql = "select \n"
				+"(WW0015Stock.ClCd) as ClCd,\n"						//荷主コード
				+"(KM0030_CLIENTMST.CLName01) as CLName,\n"				//荷主名1
				+"(WW0015Stock.WhCd) as WhCd,\n"						//倉庫コード
				+"(KM0010_WHMST.WHName) as ClWHName,\n"					//担当倉庫名
				+"(KM0030_CLIENTMST.ClGpCD) as ClGpCD,\n"				//荷主グループCD
				+"(KM0031_CLIENT_GROUP.ClGpName01) as ClGpName,\n"		//グループ名1
				+"(WW0015Stock.Loc) as Loc,\n"							//ロケーション
				+"(WM0010LOCATIONMST.LocName) as LocName,\n"			//ロケーション名
				+"(WM0010LOCATIONMST.Type) as Type,\n"					//ロケタイプ　0:通常　1:保管　8:入荷時　9:引当禁止
				+"(WW0015Stock.ItemCd) as ItemCd,\n"					//商品コード
				+"(WW0015Stock.Lot) as Lot,\n"							//ロット
				+"(WW0015Stock.Expdate) as Expdate,\n"					//消費期限
				+"(WW0015Stock.ActualDate) as ActualDate,\n"			//入荷実績日
				+"(WW0015Stock.Qty) as Qty,\n"							//数量
				+"(WW0015Stock.ShipPlanQty) as ShipPlanQty,\n"			//引当済数
				+"(WW0015Stock.PossibleQty) as PossibleQty,\n"			//出荷可能数
				+"(WW0015Stock.ItemName) as ItemName,\n"				//商品名
				+"(KM0060_ITEMMST.ItemName01) as ItemName01,\n"			//商品名1
				+"(KM0060_ITEMMST.ItemName02) as ItemName02,\n"			//商品名2
				+"(KM0060_ITEMMST.ItemName03) as ItemName03,\n"			//商品名3
				+"(WW0015Stock.ClItemCd) as ClItemCd,\n"				//荷主商品コード
				+"(WW0015Stock.JanCd) as JanCd,\n"						//ソースマーク_BCD（バラ）
				+"(WW0015Stock.ItemMdNo) as ItemMdNo,\n"				//商品型番
				+"(KM0061_ITEMMSTSUB.CtQty) as CtUnitQty,\n"			//カートン入数
				+"(KM0061_ITEMMSTSUB.CsQty) as CsUnitQty,\n"			//ケース入数
				+"(KM0061_ITEMMSTSUB.PlQty) as PlUnitQty,\n"			//パレット入数
				+"(KM0060_ITEMMST.UnitName) as UnitName,\n"				//商品単位
				+"(KM0061_ITEMMSTSUB.CtUnitName) as CtUnitName,\n"		//カートン商品単位
				+"(KM0061_ITEMMSTSUB.CsUnitName) as CsUnitName,\n"		//ケース商品単位
				+"(KM0061_ITEMMSTSUB.PlUnitName) as PlUnitName,\n"		//パレット商品単位
				+"(WW0015Stock.EntryDate) as EntryDate,\n"				//登録日時
				+"(WW0015Stock.UpdateDate) as UpdateDate,\n"			//更新日時
				+"(WW0015Stock.EntryUser) as EntryUser,\n"				//登録者
				+"(WW0015Stock.UpdateUser) as UpdateUser\n"				//更新者
				+ " from "+A00000Main.MySqlDefaultSchemaWANKO+".WW0015Stock"
				+ " left outer join "+A00000Main.MySqlDefaultSchemaWANKO+".WM0010LOCATIONMST"
				+ " on("
				+ " WW0015Stock.ClCd = WM0010LOCATIONMST.ClCd"
				+ " and WW0015Stock.WhCd = WM0010LOCATIONMST.WhCd"
				+ " and WW0015Stock.Loc= WM0010LOCATIONMST.Loc"
				+ ")\n"
				+ " left outer join "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0030_CLIENTMST"
				+ " on("
				+ " WW0015Stock.WhCd = KM0030_CLIENTMST.WHCD"
				+ " and WW0015Stock.ClCd = KM0030_CLIENTMST.cl_cd"
				+ ")\n"
				+ " left outer join "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP"
				+ " on("
				+ " KM0030_CLIENTMST.ClGpCD = KM0031_CLIENT_GROUP.ClGpCD"
				+ ")\n"
				+ " left outer join "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST"
				+ " on("
				+ " WW0015Stock.WhCd = KM0010_WHMST.WHCD"
				+ ")\n"
				+ " left outer join "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0060_ITEMMST"
				+ " on("
				+ " KM0030_CLIENTMST.ClGpCD = KM0060_ITEMMST.ClGpCd"
				+ " and WW0015Stock.ItemCd = KM0060_ITEMMST.ItemCd"
				+ ")\n"
				+ " left outer join "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0061_ITEMMSTSUB \n"
				+ " on("
				+ " KM0060_ITEMMST.ClGpCd = KM0061_ITEMMSTSUB.ClGpCd"
				+ " and KM0060_ITEMMST.ItemCd = KM0061_ITEMMSTSUB.ItemCd"
				+ ")\n";
		
		//System.out.println(sql);
		
		return rt;
	}
}