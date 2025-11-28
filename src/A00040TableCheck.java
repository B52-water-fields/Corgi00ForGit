import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class A00040TableCheck{
	//必要テーブルをチェック→無ければ作る
	public static void TableCheck() {
		PostDBCheck();		//郵便番号データベースの必要なテーブルを確認する
		NyankoDBCheck();	//NYANKOデータベースの必要なテーブルを確認する
		/*
		
		
		WankoDBCheck();		//WANKOデータベースの必要なテーブルを確認する
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
	//NYANKOデータベースの必要なテーブルを確認する
	private static void NyankoDBCheck() {
		String[] TableName=TabeleList("NANKO");
		boolean KM0000_PARAMETERUnHitkFg = true; 
		boolean KM0005_MUNICUnHitkFg = true; 
		boolean KM0010_WHMSTUnHitkFg = true; 
		boolean KM0020_USERMSTUnHitkFg = true;
		
		
		
		for(int i=0;i<TableName.length;i++) {
			switch(TableName[i]){
				case "KM0000_PARAMETER":
					KM0000_PARAMETERUnHitkFg = false; 
					break;
				case "KM0005_MUNIC":
					KM0005_MUNICUnHitkFg = false; 
					break;
				case "KM0010_WHMST":
					KM0010_WHMSTUnHitkFg = false; 
					break;
				case "KM0020_USERMST":
					KM0020_USERMSTUnHitkFg = false; 
					break;
				default:
					break;
			}
		}
		
		if(KM0000_PARAMETERUnHitkFg) {
			String sql = KM0000_PARAMETERCreateSql();
			KickSql("NANKO",sql);
		}
		
		if(KM0005_MUNICUnHitkFg) {
			String sql = KM0005_MUNICCreateSql();
			KickSql("NANKO",sql);
		}
		
		if(KM0010_WHMSTUnHitkFg) {
			String sql = KM0010_WHMSTCreateSql();
			KickSql("NANKO",sql);
		}
		
		if(KM0020_USERMSTUnHitkFg) {
			String sql = KM0020_USERMSTCreateSql();
			KickSql("NANKO",sql);
		}
		
		//テーブルのフィールドチェック ⇒ 必要フィールドなければ作る
		String[] ColumnList = null;
		String[] NeedColmn = null;
		ArrayList<String> NoHitColumn = new ArrayList<String>();
		
		ColumnList = ColumnList("NANKO","KM0000_PARAMETER");
		NeedColmn = new String[27];

		NeedColmn[ 0] = "ParaCd";
		NeedColmn[ 1] = "ParaCdSeq";
		NeedColmn[ 2] = "ParaName";
		NeedColmn[ 3] = "ParaTxt01";
		NeedColmn[ 4] = "ParaTxt02";
		NeedColmn[ 5] = "ParaTxt03";
		NeedColmn[ 6] = "ParaTxt04";
		NeedColmn[ 7] = "ParaTxt05";
		NeedColmn[ 8] = "ParaTxt06";
		NeedColmn[ 9] = "ParaTxt07";
		NeedColmn[10] = "ParaTxt08";
		NeedColmn[11] = "ParaTxt09";
		NeedColmn[12] = "ParaTxt10";
		NeedColmn[13] = "ParaInt01";
		NeedColmn[14] = "ParaInt02";
		NeedColmn[15] = "ParaInt03";
		NeedColmn[16] = "ParaInt04";
		NeedColmn[17] = "ParaInt05";
		NeedColmn[18] = "ParaInt06";
		NeedColmn[19] = "ParaInt07";
		NeedColmn[20] = "ParaInt08";
		NeedColmn[21] = "ParaInt09";
		NeedColmn[22] = "ParaInt10";
		NeedColmn[23] = "EntryDate";
		NeedColmn[24] = "UpdateDate";
		NeedColmn[25] = "EntryUser";
		NeedColmn[26] = "UpdateUser";

		NoHitColumn = new ArrayList<String>();
		for(int i01=0;i01<NeedColmn.length;i01++) {
			boolean UnHitFg = true;
			for(int i02=0;i02<ColumnList.length;i02++) {
				if(NeedColmn[i01].equals(ColumnList[i02])) {
					UnHitFg = false;
					i02=ColumnList.length+1;
				}
			}
			if(UnHitFg) {
				NoHitColumn.add(NeedColmn[i01]);
			}
		}
		if(null!=NoHitColumn&&0<NoHitColumn.size()) {
			String sql = KM0000_PARAMETERAltherTableSql(NoHitColumn);
			KickSql("NANKO",sql);
		}
		
		
		ColumnList = ColumnList("NANKO","KM0005_MUNIC");
		NeedColmn = new String[3];
		NeedColmn[ 0] = "MunicCd";
		NeedColmn[ 1] = "Prefectures";
		NeedColmn[ 2] = "Munici01";
		
		NoHitColumn = new ArrayList<String>();
		for(int i01=0;i01<NeedColmn.length;i01++) {
			boolean UnHitFg = true;
			for(int i02=0;i02<ColumnList.length;i02++) {
				if(NeedColmn[i01].equals(ColumnList[i02])) {
					UnHitFg = false;
					i02=ColumnList.length+1;
				}
			}
			if(UnHitFg) {
				NoHitColumn.add(NeedColmn[i01]);
			}
		}
		if(null!=NoHitColumn&&0<NoHitColumn.size()) {
			String sql = KM0005_MUNICAltherTableSql(NoHitColumn);
			KickSql("NANKO",sql);
		}
		
		ColumnList = ColumnList("NANKO","KM0010_WHMST");
		NeedColmn = new String[17];
		NeedColmn[ 0] = "WHCD";
		NeedColmn[ 1] = "WHName";
		NeedColmn[ 2] = "Post";
		NeedColmn[ 3] = "Add01";
		NeedColmn[ 4] = "Add02";
		NeedColmn[ 5] = "Tel";
		NeedColmn[ 6] = "Fax";
		NeedColmn[ 7] = "Mail";
		NeedColmn[ 8] = "Com01";
		NeedColmn[ 9] = "Com02";
		NeedColmn[10] = "Com03";
		NeedColmn[11] = "PTMSCD";
		NeedColmn[12] = "EntryDate";
		NeedColmn[13] = "UpdateDate";
		NeedColmn[14] = "EntryUser";
		NeedColmn[15] = "UpdateUser";
		NeedColmn[16] = "DelFg";
		
		NoHitColumn = new ArrayList<String>();
		for(int i01=0;i01<NeedColmn.length;i01++) {
			boolean UnHitFg = true;
			for(int i02=0;i02<ColumnList.length;i02++) {
				if(NeedColmn[i01].equals(ColumnList[i02])) {
					UnHitFg = false;
					i02=ColumnList.length+1;
				}
			}
			if(UnHitFg) {
				NoHitColumn.add(NeedColmn[i01]);
			}
		}
		if(null!=NoHitColumn&&0<NoHitColumn.size()) {
			String sql = KM0010_WHMSTAltherTableSql(NoHitColumn);
			KickSql("NANKO",sql);
		}
		
		ColumnList = ColumnList("NANKO","KM0020_USERMST");
		NeedColmn = new String[25];
		
		NeedColmn[ 0] = "WHCD";
		NeedColmn[ 1] = "ShippingCompanyCd";
		NeedColmn[ 2] = "UserCd";
		NeedColmn[ 3] = "PassWord";
		NeedColmn[ 4] = "AuthorityFG";
		NeedColmn[ 5] = "CarCd";
		NeedColmn[ 6] = "UserName01";
		NeedColmn[ 7] = "UserName02";
		NeedColmn[ 8] = "UserName03";
		NeedColmn[ 9] = "Post";
		NeedColmn[10] = "Add01";
		NeedColmn[11] = "Add02";
		NeedColmn[12] = "Add03";
		NeedColmn[13] = "Tel";
		NeedColmn[14] = "Fax";
		NeedColmn[15] = "Mail";
		NeedColmn[16] = "Com01";
		NeedColmn[17] = "Com02";
		NeedColmn[18] = "Com03";
		NeedColmn[19] = "EntryDate";
		NeedColmn[20] = "UpdateDate";
		NeedColmn[21] = "EntryUser";
		NeedColmn[22] = "UpdateUser";
		NeedColmn[23] = "PTMSCD";
		NeedColmn[24] = "DelFg";
		
		NoHitColumn = new ArrayList<String>();
		for(int i01=0;i01<NeedColmn.length;i01++) {
			boolean UnHitFg = true;
			for(int i02=0;i02<ColumnList.length;i02++) {
				if(NeedColmn[i01].equals(ColumnList[i02])) {
					UnHitFg = false;
					i02=ColumnList.length+1;
				}
			}
			if(UnHitFg) {
				NoHitColumn.add(NeedColmn[i01]);
			}
		}
		if(null!=NoHitColumn&&0<NoHitColumn.size()) {
			String sql = KM0020_USERMSTAltherTableSql(NoHitColumn);
			KickSql("NANKO",sql);
		}
	}
	private static String KM0000_PARAMETERCreateSql() {
		//パラメータマスタテーブルを作るNYANKO
		String sql = ""
				+"CREATE TABLE `KM0000_PARAMETER` ("
				+"  `ParaCd` varchar(20) NOT NULL,"
				+"  `ParaCdSeq` int(11) NOT NULL,"
				+"  `ParaName` varchar(100) DEFAULT '',"
				+"  `ParaTxt01` varchar(200) DEFAULT '',"
				+"  `ParaTxt02` varchar(200) DEFAULT '',"
				+"  `ParaTxt03` varchar(200) DEFAULT '',"
				+"  `ParaTxt04` varchar(200) DEFAULT '',"
				+"  `ParaTxt05` varchar(200) DEFAULT '',"
				+"  `ParaTxt06` varchar(200) DEFAULT '',"
				+"  `ParaTxt07` varchar(200) DEFAULT '',"
				+"  `ParaTxt08` varchar(200) DEFAULT '',"
				+"  `ParaTxt09` varchar(200) DEFAULT '',"
				+"  `ParaTxt10` varchar(200) DEFAULT '',"
				+"  `ParaInt01` int(11) DEFAULT '0',"
				+"  `ParaInt02` int(11) DEFAULT '0',"
				+"  `ParaInt03` int(11) DEFAULT '0',"
				+"  `ParaInt04` int(11) DEFAULT '0',"
				+"  `ParaInt05` int(11) DEFAULT '0',"
				+"  `ParaInt06` int(11) DEFAULT '0',"
				+"  `ParaInt07` int(11) DEFAULT '0',"
				+"  `ParaInt08` int(11) DEFAULT '0',"
				+"  `ParaInt09` int(11) DEFAULT '0',"
				+"  `ParaInt10` int(11) DEFAULT '0',"
				+"  `EntryDate` datetime DEFAULT NULL,"
				+"  `UpdateDate` datetime DEFAULT NULL,"
				+"  `EntryUser` varchar(50) DEFAULT NULL,"
				+"  `UpdateUser` varchar(50) DEFAULT NULL,"
				+"  PRIMARY KEY (`ParaCd`,`ParaCdSeq`)"
				+") ENGINE=InnoDB DEFAULT CHARSET=utf8;";

		return sql;
	}
	private static String KM0000_PARAMETERAltherTableSql(ArrayList<String> NoHitColumn){
		String sql = ""
				+"ALTER TABLE "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0000_PARAMETER";
		for(int i=0;i<NoHitColumn.size();i++) {
			if(0<i) {sql = sql + ",";}
			switch(NoHitColumn.get(i)) {
				case "ParaCd":
					sql = sql + " ADD ParaCd varchar(20) NOT NULL";
					break;
				case "ParaCdSeq":
					sql = sql + " ADD ParaCdSeq int(11) NOT NULL";
					break;
				case "ParaName":
					sql = sql + " ADD ParaName varchar(100) DEFAULT ''";
					break;
				case "ParaTxt01":
					sql = sql + " ADD ParaTxt01 varchar(200) DEFAULT ''";
					break;
				case "ParaTxt02":
					sql = sql + " ADD ParaTxt02 varchar(200) DEFAULT ''";
					break;
				case "ParaTxt03":
					sql = sql + " ADD ParaTxt03 varchar(200) DEFAULT ''";
					break;
				case "ParaTxt04":
					sql = sql + " ADD ParaTxt04 varchar(200) DEFAULT ''";
					break;
				case "ParaTxt05":
					sql = sql + " ADD ParaTxt05 varchar(200) DEFAULT ''";
					break;
				case "ParaTxt06":
					sql = sql + " ADD ParaTxt06 varchar(200) DEFAULT ''";
					break;
				case "ParaTxt07":
					sql = sql + " ADD ParaTxt07 varchar(200) DEFAULT ''";
					break;
				case "ParaTxt08":
					sql = sql + " ADD ParaTxt08 varchar(200) DEFAULT ''";
					break;
				case "ParaTxt09":
					sql = sql + " ADD ParaTxt09 varchar(200) DEFAULT ''";
					break;
				case "ParaTxt10":
					sql = sql + " ADD ParaTxt10 varchar(200) DEFAULT ''";
					break;
				case "ParaInt01":
					sql = sql + " ADD ParaInt01 int(11) DEFAULT '0'";
					break;
				case "ParaInt02":
					sql = sql + " ADD ParaInt02 int(11) DEFAULT '0'";
					break;
				case "ParaInt03":
					sql = sql + " ADD ParaInt03 int(11) DEFAULT '0'";
					break;
				case "ParaInt04":
					sql = sql + " ADD ParaInt04 int(11) DEFAULT '0'";
					break;
				case "ParaInt05":
					sql = sql + " ADD ParaInt05 int(11) DEFAULT '0'";
					break;
				case "ParaInt06":
					sql = sql + " ADD ParaInt06 int(11) DEFAULT '0'";
					break;
				case "ParaInt07":
					sql = sql + " ADD ParaInt07 int(11) DEFAULT '0'";
					break;
				case "ParaInt08":
					sql = sql + " ADD ParaInt08 int(11) DEFAULT '0'";
					break;
				case "ParaInt09":
					sql = sql + " ADD ParaInt09 int(11) DEFAULT '0'";
					break;
				case "ParaInt10":
					sql = sql + " ADD ParaInt10 int(11) DEFAULT '0'";
					break;
				case "EntryDate":
					sql = sql + " ADD EntryDate datetime DEFAULT NULL";
					break;
				case "UpdateDate":
					sql = sql + " ADD UpdateDate datetime DEFAULT NULL";
					break;
				case "EntryUser":
					sql = sql + " ADD EntryUser varchar(50) DEFAULT NULL";
					break;
				case "UpdateUser":
					sql = sql + " ADD UpdateUser varchar(50) DEFAULT NULL";
					break;
				default:
					break;
			}
		}
		return sql;
	}
	
	
	private static String KM0005_MUNICCreateSql() {
		//市区町村マスタを作るNYANKO
		String sql = ""
				+"CREATE TABLE `KM0005_MUNIC` ("
				+"  `MunicCd` varchar(20) DEFAULT NULL,"
				+"  `Prefectures` varchar(50) DEFAULT NULL,"
				+"  `Munici01` varchar(50) DEFAULT NULL"
				+") ENGINE=InnoDB DEFAULT CHARSET=utf8;";
		return sql;
	}
	
	private static String KM0005_MUNICAltherTableSql(ArrayList<String> NoHitColumn){
		String sql = ""
				+"ALTER TABLE "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0005_MUNIC";
		for(int i=0;i<NoHitColumn.size();i++) {
			if(0<i) {sql = sql + ",";}
			switch(NoHitColumn.get(i)) {
				case "MunicCd":
					sql = sql + " ADD MunicCd varchar(20) DEFAULT NULL";
					break;
				case "Prefectures":
					sql = sql + " ADD Prefectures varchar(50) DEFAULT NULL";
					break;
				case "Munici01":
					sql = sql + " ADD Munici01 varchar(50) DEFAULT NULL";
					break;
				default:
					break;
			}
		}
		return sql;
	}
	
	private static String KM0010_WHMSTCreateSql() {
		//倉庫マスタを作るNYANKO
		String sql = ""
				+"CREATE TABLE `KM0010_WHMST` ("
				+"  `WHCD` varchar(20) NOT NULL,"
				+"  `WHName` varchar(50) DEFAULT NULL,"
				+"  `Post` varchar(20) DEFAULT NULL,"
				+"  `Add01` varchar(100) DEFAULT NULL,"
				+"  `Add02` varchar(100) DEFAULT NULL,"
				+"  `Tel` varchar(20) DEFAULT NULL,"
				+"  `Fax` varchar(20) DEFAULT NULL,"
				+"  `Mail` varchar(100) DEFAULT NULL,"
				+"  `Com01` varchar(100) DEFAULT NULL,"
				+"  `Com02` varchar(100) DEFAULT NULL,"
				+"  `Com03` varchar(100) DEFAULT NULL,"
				+"  `PTMSCD` varchar(20) DEFAULT NULL,"
				+"  `EntryDate` datetime DEFAULT NULL,"
				+"  `UpdateDate` datetime DEFAULT NULL,"
				+"  `EntryUser` varchar(50) DEFAULT NULL,"
				+"  `UpdateUser` varchar(50) DEFAULT NULL,"
				+"  `DelFg` int(11) DEFAULT '0',"
				+"  PRIMARY KEY (`WHCD`)"
				+") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='倉庫マスタ';";
		return sql;
	}
	
	private static String  KM0010_WHMSTAltherTableSql(ArrayList<String> NoHitColumn){
		String sql = ""
				+"ALTER TABLE "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST";
		for(int i=0;i<NoHitColumn.size();i++) {
			if(0<i) {sql = sql + ",";}
			switch(NoHitColumn.get(i)) {
				case "WHCD":
					sql = sql + " ADD WHCD varchar(20) NOT NULL";
					break;
				case "WHName":
					sql = sql + " ADD WHName varchar(50) DEFAULT NULL";
					break;
				case "Post":
					sql = sql + " ADD Post varchar(20) DEFAULT NULL";
					break;
				case "Add01":
					sql = sql + " ADD Add01 varchar(100) DEFAULT NULL";
					break;
				case "Add02":
					sql = sql + " ADD Add02 varchar(100) DEFAULT NULL";
					break;
				case "Tel":
					sql = sql + " ADD Tel varchar(20) DEFAULT NULL";
					break;
				case "Fax":
					sql = sql + " ADD Fax varchar(20) DEFAULT NULL";
					break;
				case "Mail":
					sql = sql + " ADD Mail varchar(100) DEFAULT NULL";
					break;
				case "Com01":
					sql = sql + " ADD Com01 varchar(100) DEFAULT NULL";
					break;
				case "Com02":
					sql = sql + " ADD Com02 varchar(100) DEFAULT NULL";
					break;
				case "Com03":
					sql = sql + " ADD Com03 varchar(100) DEFAULT NULL";
					break;
				case "PTMSCD":
					sql = sql + " ADD PTMSCD varchar(20) DEFAULT NULL";
					break;
				case "EntryDate":
					sql = sql + " ADD EntryDate datetime DEFAULT NULL";
					break;
				case "UpdateDate":
					sql = sql + " ADD UpdateDate datetime DEFAULT NULL";
					break;
				case "EntryUser":
					sql = sql + " ADD EntryUser varchar(50) DEFAULT NULL";
					break;
				case "UpdateUser":
					sql = sql + " ADD UpdateUser varchar(50) DEFAULT NULL";
					break;
				case "DelFg":
					sql = sql + " ADD DelFg int(11) DEFAULT '0'";
					break;
				default:
					break;
			}
		}
		return sql;
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
	private static String KM0020_USERMSTAltherTableSql(ArrayList<String> NoHitColumn){
		String sql = ""
				+"ALTER TABLE "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0020_USERMST";
		for(int i=0;i<NoHitColumn.size();i++) {
			if(0<i) {sql = sql + ",";}
			switch(NoHitColumn.get(i)) {
				case "WHCD":
					sql = sql + " ADD WHCD varchar(20) NOT NULL";
					break;
				case "ShippingCompanyCd":
					sql = sql + " ADD ShippingCompanyCd varchar(20) NOT NULL";
					break;
				case "UserCd":
					sql = sql + " ADD UserCd varchar(20) NOT NULL";
					break;
				case "PassWord":
					sql = sql + " ADD PassWord varchar(50) DEFAULT NULL";
					break;
				case "AuthorityFG":
					sql = sql + " ADD AuthorityFG int(11) NOT NULL DEFAULT '0'";
					break;
				case "CarCd":
					sql = sql + " ADD CarCd varchar(20) DEFAULT NULL";
					break;
				case "UserName01":
					sql = sql + " ADD UserName01 varchar(50) DEFAULT NULL";
					break;
				case "UserName02":
					sql = sql + " ADD UserName02 varchar(50) DEFAULT NULL";
					break;
				case "UserName03":
					sql = sql + " ADD UserName03 varchar(50) DEFAULT NULL";
					break;
				case "Post":
					sql = sql + " ADD Post varchar(20) DEFAULT NULL";
					break;
				case "Add01":
					sql = sql + " ADD Add01 varchar(100) DEFAULT NULL";
					break;
				case "Add02":
					sql = sql + " ADD Add02 varchar(100) DEFAULT NULL";
					break;
				case "Add03":
					sql = sql + " ADD Add03 varchar(100) DEFAULT NULL";
					break;
				case "Tel":
					sql = sql + " ADD Tel varchar(20) DEFAULT NULL";
					break;
				case "Fax":
					sql = sql + " ADD Fax varchar(20) DEFAULT NULL";
					break;
				case "Mail":
					sql = sql + " ADD Mail varchar(100) DEFAULT NULL";
					break;
				case "Com01":
					sql = sql + " ADD Com01 varchar(100) DEFAULT NULL";
					break;
				case "Com02":
					sql = sql + " ADD Com02 varchar(100) DEFAULT NULL";
					break;
				case "Com03":
					sql = sql + " ADD Com03 varchar(100) DEFAULT NULL";
					break;
				case "EntryDate":
					sql = sql + " ADD EntryDate datetime DEFAULT NULL";
					break;
				case "UpdateDate":
					sql = sql + " ADD UpdateDate datetime DEFAULT NULL";
					break;
				case "EntryUser":
					sql = sql + " ADD EntryUser varchar(50) DEFAULT NULL";
					break;
				case "UpdateUser":
					sql = sql + " ADD UpdateUser varchar(50) DEFAULT NULL";
					break;
				case "PTMSCD":
					sql = sql + " ADD PTMSCD varchar(20) DEFAULT NULL";
					break;
				case "DelFg":
					sql = sql + " ADD DelFg tinyint(1) NOT NULL DEFAULT '0'";
					break;
				default:
					break;
			}
		}
		return sql;
	}
	
	//郵便番号データベースの必要なテーブルを確認する
	private static void PostDBCheck() {
		String[] TableName=TabeleList("POST");
		
		boolean PostUnHitkFg = true; 
		
		for(int i=0;i<TableName.length;i++) {
			switch(TableName[i]){
				case "M0010_PostMst":
					PostUnHitkFg = false; 
					break;
				default:
					break;
			}
		}
		
		if(PostUnHitkFg) {
			String sql = PostMstTableCreateSql();
			KickSql("POST",sql);
		}
		
		//テーブルのフィールドチェック ⇒ 必要フィールドなければ作る
		String[] ColumnList = null;
		String[] NeedColmn = null;
		ColumnList = ColumnList("POST","M0010_PostMst");	//郵便番号マスタ
		NeedColmn = new String[5];
		NeedColmn[ 0] = "POST";
		NeedColmn[ 1] = "PREFECTURES";
		NeedColmn[ 2] = "MUNICI01";
		NeedColmn[ 3] = "MUNICI02";
		NeedColmn[ 4] = "MUNICIPALITY_CD";
		
		ArrayList<String> NoHitColumn = new ArrayList<String>();
		for(int i01=0;i01<NeedColmn.length;i01++) {
			boolean UnHitFg = true;
			for(int i02=0;i02<ColumnList.length;i02++) {
				if(NeedColmn[i01].equals(ColumnList[i02])) {
					UnHitFg = false;
					i02=ColumnList.length+1;
				}
			}
			if(UnHitFg) {
				NoHitColumn.add(NeedColmn[i01]);
			}
		}
		if(null!=NoHitColumn && 0<NoHitColumn.size()) {
			String sql = PostMstAltherTableSql(NoHitColumn);
			KickSql("POST",sql);
		}
	}
	
	private static String PostMstTableCreateSql() {
		//郵便番号マスタテーブルを作る
		String sql = ""
			+"CREATE TABLE `M0010_PostMst` ("
			+"  `POST` varchar(10) NOT NULL,"
			+"  `PREFECTURES` varchar(20) DEFAULT NULL,"
			+"  `MUNICI01` varchar(100) DEFAULT NULL,"
			+"  `MUNICI02` varchar(100) DEFAULT NULL,"
			+"  `MUNICIPALITY_CD` varchar(10) DEFAULT NULL,"
			+"  PRIMARY KEY (`POST`)"
			+") ENGINE=InnoDB DEFAULT CHARSET=utf8;";
		return sql;
	}
	
	private static String PostMstAltherTableSql(ArrayList<String> NoHitColumn) {
		//郵便番号マスタテーブルにフィールド追加する
		int counter = 0;
		String sql = ""
			+"ALTER TABLE "+A00000Main.MySqlDefaultSchemaPOST+".M0010_PostMst";
			for(int i=0;i<NoHitColumn.size();i++) {
				if(0<i) {sql = sql + ",";}
				switch(NoHitColumn.get(i)) {
					case "POST":
						sql = sql + " ADD POST varchar(10) NOT NULL";
						break;
					case "PREFECTURES":
						sql = sql + " ADD PREFECTURES varchar(20) DEFAULT NULL";
						break;
					case "MUNICI01":
						sql = sql + " ADD MUNICI01 varchar(100) DEFAULT NULL";
						break;
					case "MUNICI02":
						sql = sql + " ADD MUNICI02 varchar(100) DEFAULT NULL";
						break;
					case "MUNICIPALITY_CD":
						sql = sql + " ADD MUNICIPALITY_CD varchar(10) DEFAULT NULL";
						break;
					default:
						break;
				}
			}
		return sql;
	}
	
	private static String[] ColumnList(String TgtDB,String TgtTable) {
		//データベース・テーブルを指定してフィールド名一覧を返却する
		String[] ColumName=new String[0];
		A00010DbConnect.DB_CONN(TgtDB);
		ResultSet rset01 = null;
		PreparedStatement CreateTablestmt = null;
		Statement stmt01 = null;
		
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
		
		String sql = "SELECT COLUMN_NAME, DATA_TYPE,column_default,is_nullable\n"
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
			
			ColumName=new String[counter];
			
			counter = 0;
			rset01.beforeFirst();
			while (rset01.next()) {
				
				ColumName[counter] = rset01.getString("COLUMN_NAME");
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
		
		return ColumName;
	}
	
	//sql実行
	private static void KickSql(String TgtDB,String sql) {
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
}