import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class A00040TableCheck{
	//必要テーブルをチェック→無ければ作る
	public static void TableCheck() {
		/*
		PostDBCheck();		//郵便番号データベースの必要なテーブルを確認する
		NyankoDBCheck();	//NYANKOデータベースの必要なテーブルを確認する
		WankoDBCheck();	//WANKOデータベースの必要なテーブルを確認する
		OldDBCheck();		//OLDデータベースの必要なテーブルを確認する
		*/
		
	}
	public static void UserMstCreate() {
		//ユーザーマスタテーブル作る
		String[] TableName = TabeleList("NYANKO");
		boolean KM0020_USERMSTUnHitkFg = true;
		for(int i=0;i<TableName.length;i++) {
			switch(TableName[i]){
				case "KM0020_USERMST":
					KM0020_USERMSTUnHitkFg = false; 
					break;
				default :break;
			}
		}
		if(KM0020_USERMSTUnHitkFg) {
			A00010DbConnect.DB_CONN("NYANKO");
			ResultSet rset01 = null;
			PreparedStatement CreateTablestmt = null;
			Statement stmt01 = null;
			
			String sql = KM0020_USERMSTCreateSql();
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
	
	private static String[] TabeleList(String TgtDB) {
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
			
			String MySqlDefaultSchema = "";
			if("WANKO".equals(TgtDB)) {
				MySqlDefaultSchema = A00000Main.MySqlDefaultSchemaWANKO;
			}else if("NANKO".equals(TgtDB)){
				MySqlDefaultSchema = A00000Main.MySqlDefaultSchemaNYANKO;
				
			}else if("POST".equals(TgtDB)){
				MySqlDefaultSchema = A00000Main.MySqlDefaultSchemaPOST;
				
			}else if("NYANKO".equals(TgtDB)){
				MySqlDefaultSchema = A00000Main.MySqlDefaultSchemaNYANKO;
				
			}else if("OLD".equals(TgtDB)) {
				MySqlDefaultSchema = A00000Main.MySqlDefaultSchemaOLD;
				
			}else {
				
			}
			
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
	
	private static String KM0020_USERMSTCreateSql() {
		//ユーザーマスタを作るNYANKO
		String sql = ""
				+"CREATE TABLE `KM0020_USERMST` ("
				+"  `WHCD` varchar(20) NOT NULL,"
				+"  `ShippingCompanyCd` varchar(20) NOT NULL,"
				+"  `UserCd` varchar(20) NOT NULL,"
				+"  `PassWord` varchar(50) DEFAULT NULL,"
				+"  `AuthorityFG` int(11) NOT NULL DEFAULT '0',"
				+"  `CarCd` varchar(20) DEFAULT NULL,"
				+"  `UserName01` varchar(50) DEFAULT NULL,"
				+"  `UserName02` varchar(50) DEFAULT NULL,"
				+"  `UserName03` varchar(50) DEFAULT NULL,"
				+"  `Post` varchar(20) DEFAULT NULL,"
				+"  `Add01` varchar(100) DEFAULT NULL,"
				+"  `Add02` varchar(100) DEFAULT NULL,"
				+"  `Add03` varchar(100) DEFAULT NULL,"
				+"  `Tel` varchar(20) DEFAULT NULL,"
				+"  `Fax` varchar(20) DEFAULT NULL,"
				+"  `Mail` varchar(100) DEFAULT NULL,"
				+"  `Com01` varchar(100) DEFAULT NULL,"
				+"  `Com02` varchar(100) DEFAULT NULL,"
				+"  `Com03` varchar(100) DEFAULT NULL,"
				+"  `EntryDate` datetime DEFAULT NULL,"
				+"  `UpdateDate` datetime DEFAULT NULL,"
				+"  `EntryUser` varchar(50) DEFAULT NULL,"
				+"  `UpdateUser` varchar(50) DEFAULT NULL,"
				+"  `PTMSCD` varchar(20) DEFAULT NULL,"
				+"  `DelFg` tinyint(1) NOT NULL DEFAULT '0',"
				+"  PRIMARY KEY (`WHCD`,`ShippingCompanyCd`,`UserCd`)"
				+") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ユーザー（乗務員）マスタ';";
		return sql;
	}
}