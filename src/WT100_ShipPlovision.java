import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WT100_ShipPlovision{
	//在庫引き当て
	
	static final int StockItemLotExpDateSumRt_Col_ClCd				= (int) 0;		//荷主コード
	static final int StockItemLotExpDateSumRt_Col_CLName			= (int) 1;		//荷主表記名
	static final int StockItemLotExpDateSumRt_Col_WhCd				= (int) 2;		//倉庫コード
	static final int StockItemLotExpDateSumRt_Col_ClWHName		= (int) 3;		//担当倉庫名
	static final int StockItemLotExpDateSumRt_Col_ClGpCD			= (int) 4;		//荷主グループCD
	static final int StockItemLotExpDateSumRt_Col_ClGpName		= (int) 5;		//グループ名1
	static final int StockItemLotExpDateSumRt_Col_ItemCd			= (int) 6;		//商品コード
	static final int StockItemLotExpDateSumRt_Col_Lot				= (int) 7;		//ロット
	static final int StockItemLotExpDateSumRt_Col_Expdate			= (int) 8;		//消費期限
	static final int StockItemLotExpDateSumRt_Col_Qty				= (int) 9;		//数量
	static final int StockItemLotExpDateSumRt_Col_ShipPlanQty		= (int)10;		//引当済数
	static final int StockItemLotExpDateSumRt_Col_PossibleQty		= (int)11;		//出荷可能数
	static final int StockItemLotExpDateSumRt_Col_ItemName		= (int)12;		//商品名
	static final int StockItemLotExpDateSumRt_Col_ItemName01		= (int)13;		//商品表記名
	static final int StockItemLotExpDateSumRt_Col_ItemName02		= (int)14;		//商品正式名
	static final int StockItemLotExpDateSumRt_Col_ItemName03		= (int)15;		//商品略名
	static final int StockItemLotExpDateSumRt_Col_CtUnitQty		= (int)16;		//カートン入数
	static final int StockItemLotExpDateSumRt_Col_CsUnitQty		= (int)17;		//ケース入数
	static final int StockItemLotExpDateSumRt_Col_PlUnitQty		= (int)18;		//パレット入数
	static final int StockItemLotExpDateSumRt_Col_UnitName		= (int)19;		//商品単位
	static final int StockItemLotExpDateSumRt_Col_CtUnitName		= (int)20;		//カートン商品単位
	static final int StockItemLotExpDateSumRt_Col_CsUnitName		= (int)21;		//ケース商品単位
	static final int StockItemLotExpDateSumRt_Col_PlUnitName		= (int)22;		//パレット商品単位
	
	public static void ShipPlovision(String TgtWhCd,String TgtClCd,ArrayList<String> TgtOkurino) {
		if(null==TgtWhCd||"".equals(TgtWhCd)) {TgtWhCd=A00000_Main.ClWh;}
		if(null==TgtClCd||"".equals(TgtClCd)) {TgtClCd=A00000_Main.ClCd;}
		
		
		
	}
	
	private static Object[][] OkuriItemLotExpDateSumRt(){
		//未引当出荷指示データの商品・ロット賞味期限別数量を取得する
		Object[][] Rt = new Object[0][100];
		
		
		return Rt;
	}
	
	
	
	private static PreparedStatement StockItemLotExpDateSumRt() {
		//商品・賞味期限別在庫を検索する
		Object[][] Rt = new Object[0][100];
		String sql = "select \n"
				+"(WW0015Stock.ClCd) as ClCd,\n"						//荷主コード
				+"max(KM0030_CLIENTMST.CLName01) as CLName,\n"			//荷主表記名
				+"(WW0015Stock.WhCd) as WhCd,\n"						//倉庫コード
				+"max(KM0010_WHMST.WHName) as ClWHName,\n"				//担当倉庫名
				+"max(KM0030_CLIENTMST.ClGpCD) as ClGpCD,\n"			//荷主グループCD
				+"max(KM0031_CLIENT_GROUP.ClGpName01) as ClGpName,\n"	//グループ名1
				+"(WW0015Stock.ItemCd) as ItemCd,\n"					//商品コード
				+"(WW0015Stock.Lot) as Lot,\n"							//ロット
				+"(WW0015Stock.Expdate) as Expdate,\n"					//消費期限
				+"sum(WW0015Stock.Qty) as Qty,\n"						//数量
				+"sum(WW0015Stock.ShipPlanQty) as ShipPlanQty,\n"		//引当済数
				+"sum(WW0015Stock.PossibleQty) as PossibleQty,\n"		//出荷可能数
				+"max(WW0015Stock.ItemName) as ItemName,\n"				//商品名
				+"max(KM0060_ITEMMST.ItemName01) as ItemName01,\n"		//商品表記名
				+"max(KM0060_ITEMMST.ItemName02) as ItemName02,\n"		//商品正式名
				+"max(KM0060_ITEMMST.ItemName03) as ItemName03,\n"		//商品略名
				+"max(KM0061_ITEMMSTSUB.CtQty) as CtUnitQty,\n"			//カートン入数
				+"max(KM0061_ITEMMSTSUB.CsQty) as CsUnitQty,\n"			//ケース入数
				+"max(KM0061_ITEMMSTSUB.PlQty) as PlUnitQty,\n"			//パレット入数
				+"max(KM0060_ITEMMST.UnitName) as UnitName,\n"			//商品単位
				+"max(KM0061_ITEMMSTSUB.CtUnitName) as CtUnitName,\n"	//カートン商品単位
				+"max(KM0061_ITEMMSTSUB.CsUnitName) as CsUnitName,\n"	//ケース商品単位
				+"max(KM0061_ITEMMSTSUB.PlUnitName) as PlUnitName,\n"	//パレット商品単位
				+ " from "+A00000_Main.MySqlDefaultSchemaWANKO+".WW0015Stock"
				+ " left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0030_CLIENTMST"
				+ " on("
				+ " WW0015Stock.WhCd = KM0030_CLIENTMST.WHCD"
				+ " and WW0015Stock.ClCd = KM0030_CLIENTMST.cl_cd"
				+ ")\n"
				+ " left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP"
				+ " on("
				+ " KM0030_CLIENTMST.ClGpCD = KM0031_CLIENT_GROUP.ClGpCD"
				+ ")\n"
				+ " left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST"
				+ " on("
				+ " WW0015Stock.WhCd = KM0010_WHMST.WHCD"
				+ ")\n"
				+ " left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0060_ITEMMST"
				+ " on("
				+ " KM0030_CLIENTMST.ClGpCD = KM0060_ITEMMST.ClGpCd"
				+ " and WW0015Stock.ItemCd = KM0060_ITEMMST.ItemCd"
				+ ")\n"
				+ " left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0061_ITEMMSTSUB \n"
				+ " on("
				+ " KM0060_ITEMMST.ClGpCd = KM0061_ITEMMSTSUB.ClGpCd"
				+ " and KM0060_ITEMMST.ItemCd = KM0061_ITEMMSTSUB.ItemCd"
				+ ")\n"
				+ " where WW0015Stock.WhCd="+A00000_Main.ClWh+"\n"
				+ " and WW0015Stock.ClCd="+A00000_Main.ClCd+"\n"
				+ " and WW0015Stock.ItemCd = ? \n";
		
		sql = sql + " group by WW0015Stock.WhCd,WW0015Stock.ClCd,WW0015Stock.ItemCd,WW0015Stock.Expdate,WW0015Stock.Lot";
		
		A100_DbConnect.DB_CONN("WANKO");
		ResultSet rset01 = null;
		PreparedStatement stmt01 = null;
		try {
			stmt01 = A100_DbConnect.conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stmt01;
	}
}