public class Tools100_StockZeroDelete{
	//在庫数ゼロの在庫データを削除する
	public static void StockZeroDelete(String TgtClCd,String TgtWhCd) {
		String tgt_table = A00000_Main.MySqlDefaultSchemaWANKO+".WW0015Stock";
		String[] judg_field = new String[5];
		String[][] judg_data = new String[1][5];
		String TgtDB = "WANKO";
		
		judg_field[0]= "ClCd";			//荷主コード
		judg_field[1]= "WhCd";			//倉庫コード
		judg_field[2]= "Qty";			//数量
		judg_field[3]= "ShipPlanQty";	//引当済数
		judg_field[4]= "PossibleQty";	//出荷可能数
		
		judg_data[0][0]= TgtClCd;		//荷主コード
		judg_data[0][1]= TgtWhCd;		//倉庫コード
		judg_data[0][2]= "0";			//数量
		judg_data[0][3]= "0";			//引当済数
		judg_data[0][4]= "0";			//出荷可能数
		
		A100_DeleteSQL.DeleteSql(tgt_table,judg_field,judg_data,TgtDB);
	}
}