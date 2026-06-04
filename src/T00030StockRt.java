public class T00030StockRt{
	public static Object[][] StockRt(){
		Object[][] rt = new Object[0][17];
		
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
		
		System.out.println(sql);
		
		return rt;
	}
}