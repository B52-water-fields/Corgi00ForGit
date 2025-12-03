import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class A00050OldDataTableCheck{
	//
	//トランザクションデータバックアップ用テーブルを作成する
	// ==========================================================================
    //  A00050OldDataTableCheck（冥界の王／バックアップテーブル自動治癒エンジン）
    // ==========================================================================
    //
    //  このクラスは、ユーザーID「zeus」ログイン時に起動します。
    //  （ユーザーマスタと zeus ユーザーはシステム起動時に自動降臨します）
    //
    //  ■役割
    //    バックアップ対象として指定されたテーブルにに対して
	//	　対象テーブルのレイアウトチェックを行い、バックアップ用スキーマにテーブルを作成・レイアウトの同期を行います
    //      ・存在チェック
    //      ・未存在時の CREATE TABLE
    //      ・必要カラムの存在チェック
    //      ・不足カラムの ALTER TABLE ADD
    //    を一括で行います
    //
    //  ■この神殿で起きる自動治癒
    //      テーブルが DROP された     → CREATE TABLE で復元
    //      テーブル名が変更された     → CREATE TABLE で復元
    //      カラムが削除された         → ALTER TABLE ADD で復元
    //      カラム名が変更された       → ALTER TABLE ADD で復元
    //
    //    ※すべて自動復元され、システム構造の整合性が保たれます。
    //
    //  ■ただし治癒しないもの
    //      ・データ型の変更（内臓に相当するため非対象）
    //      ・既存データの復元（神殿は外傷のみ治癒します）
    //      ・余分に追加されたカラムの削除（“削除の儀式”は行いません）
    //
    // ==========================================================================
	
	public static void OldDataTableCheck() {
		//バックアップ対象テーブルをいかに羅列すると、バックアップ用スキーマにバックアップ用テーブルの自動生成＆最新レイアウトとフィールド一致させる
		//※ファイルタイプ等は最新に合わせて変更「されません」
		
		
		//↓↓↓↓↓↓↓対象テーブルの羅列ここから↓↓↓↓↓↓↓
		
		String[][] TGtTable = new String[26][2];
		
		//配送管理系データベース　NYANKO		
		TGtTable[ 0][0] = "NYANKO";	TGtTable[ 0][1] = "KT0010_OKURI_HD";
		TGtTable[ 1][0] = "NYANKO";	TGtTable[ 1][1] = "KT0011_OKURI_MS";
		TGtTable[ 2][0] = "NYANKO";	TGtTable[ 2][1] = "KT0012_OKURI_PROGRESS";
		TGtTable[ 3][0] = "NYANKO";	TGtTable[ 3][1] = "KT0013_SEIKYU";
		TGtTable[ 4][0] = "NYANKO";	TGtTable[ 4][1] = "KT0023_SHIHARAI";
		TGtTable[ 5][0] = "NYANKO";	TGtTable[ 5][1] = "KT0040_PrintControl";
		TGtTable[ 6][0] = "NYANKO";	TGtTable[ 6][1] = "KT020_HAISHA_HD";
		TGtTable[ 7][0] = "NYANKO";	TGtTable[ 7][1] = "KT021_HAISHA_MS";
		
		//在庫管理系データベース　WANKO
		TGtTable[ 8][0] = "WANKO";	TGtTable[ 8][1] = "WW0010ArrivalPlanHd";
		TGtTable[ 9][0] = "WANKO";	TGtTable[ 9][1] = "WW0011ArrivalPlanMs";
		TGtTable[10][0] = "WANKO";	TGtTable[10][1] = "WW0012ArrivalHd";
		TGtTable[11][0] = "WANKO";	TGtTable[11][1] = "WW0013ArrivaMs";
		TGtTable[12][0] = "WANKO";	TGtTable[12][1] = "WW0015Stock";
		TGtTable[13][0] = "WANKO";	TGtTable[13][1] = "WW0016StockAdjust";
		TGtTable[14][0] = "WANKO";	TGtTable[14][1] = "WW0020ShipPlovision";
		TGtTable[15][0] = "WANKO";	TGtTable[15][1] = "WW0025BerthReservation";
		TGtTable[16][0] = "WANKO";	TGtTable[16][1] = "WW013101WhFeeInHd";
		TGtTable[17][0] = "WANKO";	TGtTable[17][1] = "WW013102WhFeeInMs";
		TGtTable[18][0] = "WANKO";	TGtTable[18][1] = "WW013201WhFeeOutHd";
		TGtTable[19][0] = "WANKO";	TGtTable[19][1] = "WW013202WhFeeOutMs";
		TGtTable[20][0] = "WANKO";	TGtTable[20][1] = "WW013301WhFeeStockHd";
		TGtTable[21][0] = "WANKO";	TGtTable[21][1] = "WW013302WhFeeStockMs";
		TGtTable[22][0] = "WANKO";	TGtTable[22][1] = "WW013401WhFeeAdjustHd";
		TGtTable[23][0] = "WANKO";	TGtTable[23][1] = "WW013402WhFeeAdjustMs";
		TGtTable[24][0] = "WANKO";	TGtTable[24][1] = "WW013501WhFeeOther";
		TGtTable[25][0] = "WANKO";	TGtTable[25][1] = "WW014001WhFeeInvoice";
		
		//↑↑↑↑↑↑↑対象テーブルの羅列ここまで↑↑↑↑↑↑↑
		
		//バックアップスキーマにテーブルが無ければ作る
		String[] TableList = TableList("OLD");
		
		for(int i01=0;i01<TGtTable.length;i01++) {
			boolean UnHitFg = true;
			for(int i02=0;i02<TableList.length;i02++) {
				if(TGtTable[i01][1].equals(TableList[i02])) {
					UnHitFg = false;
					i02=TableList.length+1;
				}
			}
			
			if(UnHitFg) {
				String[][] TableLayout = ColumnList(TGtTable[i01][0],TGtTable[i01][1]);
				CreateTable("OLD",TableLayout);
			}
		}
		
		//バックアップテーブルにフィールドが不足していたら追加する
		for(int i01=0;i01<TGtTable.length;i01++) {
			String[][] FromTableLayout = ColumnList(TGtTable[i01][0],TGtTable[i01][1]);
			String[][] ToTableLayout = ColumnList("OLD",TGtTable[i01][1]);
			if(null!=FromTableLayout && 0<FromTableLayout.length ) {
				AlterTable("OLD",FromTableLayout,ToTableLayout);
			}
		}
	}
	
	private static void CreateTable(String TgtDB,String[][] TableLayout) {
		//バックアップデータ作成先のスキーマ、テーブルのレイアウトを受けてテーブル作成用のSQL文生成⇒実行
		String MySqlDefaultSchema = ""+A00000Main.MySqlDefaultSchemaOLD;

		if("WANKO".equals(TgtDB)) {
			MySqlDefaultSchema = A00000Main.MySqlDefaultSchemaWANKO;
		}else if("NYANKO".equals(TgtDB)){
			MySqlDefaultSchema = A00000Main.MySqlDefaultSchemaNYANKO;
			
		}else if("POST".equals(TgtDB)){
			MySqlDefaultSchema = A00000Main.MySqlDefaultSchemaPOST;
			
		}else if("OLD".equals(TgtDB)) {
			MySqlDefaultSchema = A00000Main.MySqlDefaultSchemaOLD;
			
		}else {
			
		}
		
		String sql = "CREATE TABLE `"+TableLayout[0][ 2]+"` (";
			for(int i=0;i<TableLayout.length;i++) {
				if(0<i) {sql = sql+",";}
				sql = sql + " `"+TableLayout[i][ 3]+"` "+TableLayout[i][15]+" DEFAULT NULL COMMENT '"+TableLayout[i][19]+"'";
				//COLUMN_NAME,COLUMN_TYPE
			}
			sql = sql + ", `BackUpDate` datetime DEFAULT NULL  COMMENT ''"
						+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=''";
			
		//sql実行
		A00010DbConnect.DB_CONN(TgtDB);
		ResultSet rset01 = null;
		PreparedStatement CreateTablestmt = null;
		Statement stmt01 = null;
		try {
			CreateTablestmt = A00010DbConnect.conn.prepareStatement(sql);
			CreateTablestmt.executeUpdate();
			if(null!=CreateTablestmt) {CreateTablestmt.close();}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(null!=rset01){rset01.close();}
				if(null!=CreateTablestmt) {CreateTablestmt.close();}
				if(null!=stmt01){stmt01.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		A00010DbConnect.close();
	}
	
	private static void AlterTable(String TgtDB,String[][] FromTableLayout,String[][] ToTableLayout) {
		String MySqlDefaultSchema = SelectScema(TgtDB);
		
		boolean KickFg = false;
		String sql = ""
				+"ALTER TABLE "+MySqlDefaultSchema+"."+FromTableLayout[0][2];
		
		for(int i01=0;i01<FromTableLayout.length;i01++) {
			boolean UnHitFg = true;
			for(int i02=0;i02<ToTableLayout.length;i02++) {
				if(FromTableLayout[i01][3].equals(ToTableLayout[i02][3])) {
					UnHitFg = false;
					i02=ToTableLayout.length+1;
				}
			}
			if(UnHitFg) {
				if(KickFg) {sql=sql+",";}
				sql = sql + " ADD `" + FromTableLayout[i01][3] +"` "+FromTableLayout[i01][15]+" DEFAULT NULL COMMENT '"+FromTableLayout[i01][19].replace("'", "")+"'";

				KickFg = true;
			}
		}
		
		boolean UnHitFg = true;
		for(int i02=0;i02<ToTableLayout.length;i02++) {
			
			if("BackUpDate".equals(ToTableLayout[i02][3])) {
				UnHitFg = false;
				i02=ToTableLayout.length+1;
			}
		}
		if(UnHitFg) {
			if(KickFg) {sql=sql+",";}
			sql = sql+" ADD BackUpDate datetime DEFAULT NULL  COMMENT ''";
			KickFg = true;
		}
		sql=sql+";";
		
		if(KickFg) {
			//sql実行
			A00010DbConnect.DB_CONN(TgtDB);
			ResultSet rset01 = null;
			PreparedStatement CreateTablestmt = null;
			Statement stmt01 = null;
			try {
				CreateTablestmt = A00010DbConnect.conn.prepareStatement(sql);
				CreateTablestmt.executeUpdate();
				if(null!=CreateTablestmt) {CreateTablestmt.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					if(null!=rset01){rset01.close();}
					if(null!=CreateTablestmt) {CreateTablestmt.close();}
					if(null!=stmt01){stmt01.close();}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			A00010DbConnect.close();
		}
	}
	
	
	private static String[][] ColumnList(String TgtDB,String TgtTable) {
		//データベース・テーブルを指定してフィールド名一覧を返却する
		String[][] rt=new String[0][0];
		A00010DbConnect.DB_CONN(TgtDB);
		ResultSet rset01 = null;
		PreparedStatement CreateTablestmt = null;
		Statement stmt01 = null;
		
		String MySqlDefaultSchema = SelectScema(TgtDB);
		
		String sql = "select "
					+"TABLE_CATALOG,"
					+"TABLE_SCHEMA,"
					+"TABLE_NAME,"
					+"COLUMN_NAME,"
					+"ORDINAL_POSITION,"
					+"COLUMN_DEFAULT,"
					+"IS_NULLABLE,"
					+"DATA_TYPE,"
					+"CHARACTER_MAXIMUM_LENGTH,"
					+"CHARACTER_OCTET_LENGTH,"
					+"NUMERIC_PRECISION,"
					+"NUMERIC_SCALE,"
					+"DATETIME_PRECISION,"
					+"CHARACTER_SET_NAME,"
					+"COLLATION_NAME,"
					+"COLUMN_TYPE,"
					+"COLUMN_KEY,"
					+"EXTRA,"
					+"PRIVILEGES,"
					+"COLUMN_COMMENT,"
					+"GENERATION_EXPRESSION"
				+ " FROM INFORMATION_SCHEMA.COLUMNS\n"
				+ " WHERE TABLE_SCHEMA = '"+MySqlDefaultSchema+"'\n"
				+ " AND TABLE_NAME = '"+TgtTable+"'";

		try {
			stmt01 = A00010DbConnect.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
				      ResultSet.CONCUR_UPDATABLE);
			rset01 = stmt01.executeQuery(sql);
			
			int counter = 0;
			rset01.beforeFirst();
			while (rset01.next()) {
				counter=counter+1;
			}
			
			rt=new String[counter][21];
			
			counter = 0;
			rset01.beforeFirst();
			while (rset01.next()) {

				if(null==rset01.getString("TABLE_CATALOG")) 			{rt[counter][ 0] = "null";} else {rt[counter][ 0] = rset01.getString("TABLE_CATALOG");}
				if(null==rset01.getString("TABLE_SCHEMA"))				{rt[counter][ 1] = "null";} else {rt[counter][ 1] = rset01.getString("TABLE_SCHEMA");}
				if(null==rset01.getString("TABLE_NAME"))				{rt[counter][ 2] = "null";} else {rt[counter][ 2] = rset01.getString("TABLE_NAME");}
				if(null==rset01.getString("COLUMN_NAME"))				{rt[counter][ 3] = "null";} else {rt[counter][ 3] = rset01.getString("COLUMN_NAME");}
				if(null==rset01.getString("ORDINAL_POSITION"))			{rt[counter][ 4] = "null";} else {rt[counter][ 4] = rset01.getString("ORDINAL_POSITION");}
				if(null==rset01.getString("COLUMN_DEFAULT"))			{rt[counter][ 5] = "null";} else {rt[counter][ 5] = rset01.getString("COLUMN_DEFAULT");}
				if(null==rset01.getString("IS_NULLABLE"))				{rt[counter][ 6] = "null";} else {rt[counter][ 6] = rset01.getString("IS_NULLABLE");}
				if(null==rset01.getString("DATA_TYPE"))					{rt[counter][ 7] = "null";} else {rt[counter][ 7] = rset01.getString("DATA_TYPE");}
				if(null==rset01.getString("CHARACTER_MAXIMUM_LENGTH"))	{rt[counter][ 8] = "null";} else {rt[counter][ 8] = rset01.getString("CHARACTER_MAXIMUM_LENGTH");}
				if(null==rset01.getString("CHARACTER_OCTET_LENGTH"))	{rt[counter][ 9] = "null";} else {rt[counter][ 9] = rset01.getString("CHARACTER_OCTET_LENGTH");}
				if(null==rset01.getString("NUMERIC_PRECISION"))			{rt[counter][10] = "null";} else {rt[counter][10] = rset01.getString("NUMERIC_PRECISION");}
				if(null==rset01.getString("NUMERIC_SCALE"))				{rt[counter][11] = "null";} else {rt[counter][11] = rset01.getString("NUMERIC_SCALE");}
				if(null==rset01.getString("DATETIME_PRECISION"))		{rt[counter][12] = "null";} else {rt[counter][12] = rset01.getString("DATETIME_PRECISION");}
				if(null==rset01.getString("CHARACTER_SET_NAME"))		{rt[counter][13] = "null";} else {rt[counter][13] = rset01.getString("CHARACTER_SET_NAME");}
				if(null==rset01.getString("COLLATION_NAME"))			{rt[counter][14] = "null";} else {rt[counter][14] = rset01.getString("COLLATION_NAME");}
				if(null==rset01.getString("COLUMN_TYPE"))				{rt[counter][15] = "null";} else {rt[counter][15] = rset01.getString("COLUMN_TYPE");}
				if(null==rset01.getString("COLUMN_KEY"))				{rt[counter][16] = "null";} else {rt[counter][16] = rset01.getString("COLUMN_KEY");}
				if(null==rset01.getString("EXTRA"))						{rt[counter][17] = "null";} else {rt[counter][17] = rset01.getString("EXTRA");}
				if(null==rset01.getString("PRIVILEGES"))				{rt[counter][18] = "null";} else {rt[counter][18] = rset01.getString("PRIVILEGES");}
				if(null==rset01.getString("COLUMN_COMMENT"))			{rt[counter][19] = "null";} else {rt[counter][19] = rset01.getString("COLUMN_COMMENT");}
				if(null==rset01.getString("GENERATION_EXPRESSION"))		{rt[counter][20] = "null";} else {rt[counter][20] = rset01.getString("GENERATION_EXPRESSION");}
				
				counter=counter+1;
			}
			
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(null!=rset01){rset01.close();}
				if(null!=CreateTablestmt) {CreateTablestmt.close();}
				if(null!=stmt01){stmt01.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		A00010DbConnect.close();
		
		return rt;
	}
	
	private static String[] TableList(String TgtDB) {
		//データベースのテーブル一覧を返却する
		String[] TableName=new String[0];
		A00010DbConnect.DB_CONN(TgtDB);
		ResultSet rset01 = null;
		PreparedStatement CreateTablestmt = null;
		Statement stmt01 = null;
		
		String sql = "SELECT database()";
		
		try {
			stmt01 = A00010DbConnect.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
				      ResultSet.CONCUR_UPDATABLE);
			rset01 = stmt01.executeQuery(sql);
			int counter = 0;
			rset01.beforeFirst();
			while (rset01.next()) {
				counter=counter+1;
			}
			String[] DB_Name = new String[counter];
			counter = 0;
			rset01.beforeFirst();
			while (rset01.next()) {
				DB_Name[counter] = rset01.getString(1);
				counter=counter+1;
			}
			
			String MySqlDefaultSchema = SelectScema(TgtDB);
			
			if(0<DB_Name.length && MySqlDefaultSchema.equals(DB_Name[0])) {
				rset01 = null;
				stmt01 = null;
				sql = "show tables";
				stmt01 = A00010DbConnect.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					      ResultSet.CONCUR_UPDATABLE);
				rset01 = stmt01.executeQuery(sql);
				counter = 0;
				rset01.beforeFirst();
				while (rset01.next()) {
					counter=counter+1;
				}
				
				TableName = new String[counter];
				counter = 0;
				rset01.beforeFirst();
				while (rset01.next()) {
					TableName[counter] = rset01.getString(1);
					counter=counter+1;
				}
			}
			
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(null!=rset01){rset01.close();}
				if(null!=CreateTablestmt) {CreateTablestmt.close();}
				if(null!=stmt01){stmt01.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		A00010DbConnect.close();
		return TableName;
	}
	
	private static String SelectScema(String TgtDB) {
		String MySqlDefaultSchema = A00000Main.MySqlDefaultSchemaWANKO;
		if("WANKO".equals(TgtDB)) {
			MySqlDefaultSchema = A00000Main.MySqlDefaultSchemaWANKO;
		}else if("NYANKO".equals(TgtDB)){
			MySqlDefaultSchema = A00000Main.MySqlDefaultSchemaNYANKO;
			
		}else if("POST".equals(TgtDB)){
			MySqlDefaultSchema = A00000Main.MySqlDefaultSchemaPOST;
			
		}else if("OLD".equals(TgtDB)) {
			MySqlDefaultSchema = A00000Main.MySqlDefaultSchemaOLD;
			
		}else {
			
		}
		return MySqlDefaultSchema;
		
	}
}