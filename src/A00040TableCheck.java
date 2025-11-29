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
		boolean KM0030_CLIENTMSTUnHitkFg = true; 
		boolean KM0031_CLIENT_GROUPUnHitkFg = true; 
		boolean KM0040_DELIVERYMSTUnHitkFg = true; 
		boolean KM0041_DELIVERY_COMVERSIONMSTUnHitkFg = true; 
		boolean KM0050_DELIVERY_TYPEMSTUnHitkFg = true; 
		boolean KM0060_ITEMMSTUnHitkFg = true; 
		boolean KM0061_ITEMMSTSUBUnHitkFg = true; 
		boolean KM0062_ItemComversionMstUnHitkFg = true; 
		boolean KM0070_SHIPPINGCOMPANYMSTUnHitkFg = true; 
		boolean KM0071_CARMSTUnHitkFg = true; 
		boolean KM0080_FEEBASEMSTUnHitkFg = true; 
		boolean KM0081_FEEMSTUnHitkFg = true; 
		boolean KM0082_FEELOGICTYPEMSTUnHitkFg = true; 
		boolean KM0090_CAUTIONUnHitkFg = true; 
		
		
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
				case "KM0030_CLIENTMST":
					KM0030_CLIENTMSTUnHitkFg = false; 
					break;
				case "KM0031_CLIENT_GROUP":
					KM0031_CLIENT_GROUPUnHitkFg = false; 
					break;
				case "KM0040_DELIVERYMST":
					KM0040_DELIVERYMSTUnHitkFg = false; 
					break;
				case "KM0041_DELIVERY_COMVERSIONMST":
					KM0041_DELIVERY_COMVERSIONMSTUnHitkFg = false; 
					break;
				case "KM0050_DELIVERY_TYPEMST":
					KM0050_DELIVERY_TYPEMSTUnHitkFg = false; 
					break;
				case "KM0060_ITEMMST":
					KM0060_ITEMMSTUnHitkFg = false; 
					break;
				case "KM0061_ITEMMSTSUB":
					KM0061_ITEMMSTSUBUnHitkFg = false; 
					break;
				case "KM0062_ItemComversionMst":
					KM0062_ItemComversionMstUnHitkFg = false; 
					break;
				case "KM0070_SHIPPINGCOMPANYMST":
					KM0070_SHIPPINGCOMPANYMSTUnHitkFg = false; 
					break;
				case "KM0071_CARMST":
					KM0071_CARMSTUnHitkFg = false; 
					break;
				case "KM0080_FEEBASEMST":
					KM0080_FEEBASEMSTUnHitkFg = false; 
					break;
				case "KM0081_FEEMST":
					KM0081_FEEMSTUnHitkFg = false; 
					break;
				case "KM0082_FEELOGICTYPEMST":
					KM0082_FEELOGICTYPEMSTUnHitkFg = false; 
					break;
				case "KM0090_CAUTION":
					KM0090_CAUTIONUnHitkFg = false; 
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
		if(KM0030_CLIENTMSTUnHitkFg) {
			String sql = KM0030_CLIENTMSTCreateSql();
			KickSql("NANKO",sql);
		}
		if(KM0031_CLIENT_GROUPUnHitkFg) {
			String sql = KM0031_CLIENT_GROUPCreateSql();
			KickSql("NANKO",sql);
		}
		if(KM0040_DELIVERYMSTUnHitkFg) {
			String sql = KM0040_DELIVERYMSTCreateSql();
			KickSql("NANKO",sql);
		}
		if(KM0041_DELIVERY_COMVERSIONMSTUnHitkFg) {
			String sql = KM0041_DELIVERY_COMVERSIONMSTCreateSql();
			KickSql("NANKO",sql);
		}
		if(KM0050_DELIVERY_TYPEMSTUnHitkFg) {
			String sql = KM0050_DELIVERY_TYPEMSTCreateSql();
			KickSql("NANKO",sql);
		}
		if(KM0060_ITEMMSTUnHitkFg) {
			String sql = KM0060_ITEMMSTCreateSql();
			KickSql("NANKO",sql);
		}
		if(KM0061_ITEMMSTSUBUnHitkFg) {
			String sql = KM0061_ITEMMSTSUBCreateSql();
			KickSql("NANKO",sql);
		}
		if(KM0062_ItemComversionMstUnHitkFg) {
			String sql = KM0062_ItemComversionMstCreateSql();
			KickSql("NANKO",sql);
		}
		if(KM0070_SHIPPINGCOMPANYMSTUnHitkFg) {
			String sql = KM0070_SHIPPINGCOMPANYMSTCreateSql();
			KickSql("NANKO",sql);
		}
		if(KM0071_CARMSTUnHitkFg) {
			String sql = KM0071_CARMSTCreateSql();
			KickSql("NANKO",sql);
		}
		if(KM0080_FEEBASEMSTUnHitkFg) {
			String sql = KM0080_FEEBASEMSTCreateSql();
			KickSql("NANKO",sql);
		}
		if(KM0081_FEEMSTUnHitkFg) {
			String sql = KM0081_FEEMSTCreateSql();
			KickSql("NANKO",sql);
		}
		if(KM0082_FEELOGICTYPEMSTUnHitkFg) {
			String sql = KM0082_FEELOGICTYPEMSTCreateSql();
			KickSql("NANKO",sql);
		}
		if(KM0090_CAUTIONUnHitkFg) {
			String sql = KM0090_CAUTIONCreateSql();
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

		
		ColumnList = ColumnList("NANKO","KM0030_CLIENTMST");
		NeedColmn = new String[25];
		NeedColmn[ 0] = "cl_cd";
		NeedColmn[ 1] = "ClGpCD";
		NeedColmn[ 2] = "WHCD";
		NeedColmn[ 3] = "CLName01";
		NeedColmn[ 4] = "CLName02";
		NeedColmn[ 5] = "CLName03";
		NeedColmn[ 6] = "Post";
		NeedColmn[ 7] = "Add01";
		NeedColmn[ 8] = "Add02";
		NeedColmn[ 9] = "Add03";
		NeedColmn[10] = "Tel";
		NeedColmn[11] = "Fax";
		NeedColmn[12] = "Mail";
		NeedColmn[13] = "MunicCd";
		NeedColmn[14] = "Com01";
		NeedColmn[15] = "Com02";
		NeedColmn[16] = "Com03";
		NeedColmn[17] = "ShimeDate";
		NeedColmn[18] = "ShimeBasis";
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
			String sql = KM0030_CLIENTMSTAltherTableSql(NoHitColumn);
			KickSql("NANKO",sql);
		}

		
		ColumnList = ColumnList("NANKO","KM0031_CLIENT_GROUP");
		NeedColmn = new String[21];
		NeedColmn[ 0] = "ClGpCD";
		NeedColmn[ 1] = "ClGpName01";
		NeedColmn[ 2] = "ClGpName02";
		NeedColmn[ 3] = "ClGpName03";
		NeedColmn[ 4] = "Post";
		NeedColmn[ 5] = "Add01";
		NeedColmn[ 6] = "Add02";
		NeedColmn[ 7] = "Add03";
		NeedColmn[ 8] = "Tel";
		NeedColmn[ 9] = "Fax";
		NeedColmn[10] = "Mail";
		NeedColmn[11] = "Com01";
		NeedColmn[12] = "Com02";
		NeedColmn[13] = "Com03";
		NeedColmn[14] = "BankMemo";
		NeedColmn[15] = "EntryDate";
		NeedColmn[16] = "UpdateDate";
		NeedColmn[17] = "EntryUser";
		NeedColmn[18] = "UpdateUser";
		NeedColmn[19] = "DelFg";
		NeedColmn[20] = "PassWord";
		
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
			String sql = KM0031_CLIENT_GROUPAltherTableSql(NoHitColumn);
			KickSql("NANKO",sql);
		}
	
	
		ColumnList = ColumnList("NANKO","KM0040_DELIVERYMST");
		NeedColmn = new String[25];
		NeedColmn[ 0] = "DECD";
		NeedColmn[ 1] = "DepartmentCd";
		NeedColmn[ 2] = "DEName01";
		NeedColmn[ 3] = "DEName02";
		NeedColmn[ 4] = "DEName03";
		NeedColmn[ 5] = "Post";
		NeedColmn[ 6] = "Add01";
		NeedColmn[ 7] = "Add02";
		NeedColmn[ 8] = "Add03";
		NeedColmn[ 9] = "Tel";
		NeedColmn[10] = "Fax";
		NeedColmn[11] = "Mail";
		NeedColmn[12] = "Com01";
		NeedColmn[13] = "Com02";
		NeedColmn[14] = "Com03";
		NeedColmn[15] = "PrefecturesCd";
		NeedColmn[16] = "MunicipalityCd";
		NeedColmn[17] = "PTMSCD";
		NeedColmn[18] = "EntryDate";
		NeedColmn[19] = "UpdateDate";
		NeedColmn[20] = "EntryUser";
		NeedColmn[21] = "UpdateUser";
		NeedColmn[22] = "FirstClient";
		NeedColmn[23] = "LastClient";
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
			String sql = KM0040_DELIVERYMSTAltherTableSql(NoHitColumn);
			KickSql("NANKO",sql);
		}
	
	
		ColumnList = ColumnList("NANKO","KM0041_DELIVERY_COMVERSIONMST");
		NeedColmn = new String[16];
		NeedColmn[ 0] = "ClGpCD";
		NeedColmn[ 1] = "CL_DECD";
		NeedColmn[ 2] = "DECD";
		NeedColmn[ 3] = "DepartmentCd";
		NeedColmn[ 4] = "SetName";
		NeedColmn[ 5] = "Com01";
		NeedColmn[ 6] = "Com02";
		NeedColmn[ 7] = "Com03";
		NeedColmn[ 8] = "Com04";
		NeedColmn[ 9] = "Com05";
		NeedColmn[10] = "EntryDate";
		NeedColmn[11] = "UpdateDate";
		NeedColmn[12] = "EntryUser";
		NeedColmn[13] = "UpdateUser";
		NeedColmn[14] = "DelFg";
		NeedColmn[15] = "MstPriorityFirstFg";
		
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
			String sql = KM0041_DELIVERY_COMVERSIONMSTAltherTableSql(NoHitColumn);
			KickSql("NANKO",sql);
		}
		
	
		ColumnList = ColumnList("NANKO","KM0050_DELIVERY_TYPEMST");
		NeedColmn = new String[7];
		NeedColmn[ 0] = "DeliveryTypeNo";
		NeedColmn[ 1] = "DeliveryTypeCd";
		NeedColmn[ 2] = "DeliveryTypeName";
		NeedColmn[ 3] = "EntryDate";
		NeedColmn[ 4] = "UpdateDate";
		NeedColmn[ 5] = "EntryUser";
		NeedColmn[ 6] = "UpdateUser";
		
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
			String sql = KM0050_DELIVERY_TYPEMSTAltherTableSql(NoHitColumn);
			KickSql("NANKO",sql);
		}

		ColumnList = ColumnList("NANKO","KM0060_ITEMMST");
		NeedColmn = new String[21];
		NeedColmn[ 0] = "ClGpCD";
		NeedColmn[ 1] = "ItemCd";
		NeedColmn[ 2] = "CLItemCd";
		NeedColmn[ 3] = "ItemName01";
		NeedColmn[ 4] = "ItemName02";
		NeedColmn[ 5] = "ItemName03";
		NeedColmn[ 6] = "ItemWeight";
		NeedColmn[ 7] = "ItemSize";
		NeedColmn[ 8] = "DeliveryTypeCd";
		NeedColmn[ 9] = "DeliveryTypeCd02";
		NeedColmn[10] = "DeliveryTypeCd03";
		NeedColmn[11] = "DeliveryTypeCd04";
		NeedColmn[12] = "DeliveryTypeCd05";
		NeedColmn[13] = "EntryDate";
		NeedColmn[14] = "UpdateDate";
		NeedColmn[15] = "EntryUser";
		NeedColmn[16] = "UpdateUser";
		NeedColmn[17] = "DelFg";
		NeedColmn[18] = "PTMSCD";
		NeedColmn[19] = "JanCd";
		NeedColmn[20] = "UnitName";
		
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
			String sql = KM0060_ITEMMSTAltherTableSql(NoHitColumn);
			KickSql("NANKO",sql);
		}

		ColumnList = ColumnList("NANKO","KM0061_ITEMMSTSUB");
		NeedColmn = new String[43];
		NeedColmn[ 0] = "ClGpCd";
		NeedColmn[ 1] = "ItemCd";
		NeedColmn[ 2] = "CtQty";
		NeedColmn[ 3] = "CsQty";
		NeedColmn[ 4] = "PlQty";
		NeedColmn[ 5] = "CtJan";
		NeedColmn[ 6] = "CsJan";
		NeedColmn[ 7] = "PlJan";
		NeedColmn[ 8] = "CtName";
		NeedColmn[ 9] = "CsName";
		NeedColmn[10] = "PlName";
		NeedColmn[11] = "CtUnitName";
		NeedColmn[12] = "CsUnitName";
		NeedColmn[13] = "PlUnitName";
		NeedColmn[14] = "CtWeight";
		NeedColmn[15] = "CsWeight";
		NeedColmn[16] = "PlWeight";
		NeedColmn[17] = "CtSize";
		NeedColmn[18] = "CsSize";
		NeedColmn[19] = "PlSize";
		NeedColmn[20] = "RecomendLoc";
		NeedColmn[21] = "ItemMDNo";
		NeedColmn[22] = "CategoryCd";
		NeedColmn[23] = "CategoryName";
		NeedColmn[24] = "ItemColorCd";
		NeedColmn[25] = "ItemColorName";
		NeedColmn[26] = "ItemSizeCd";
		NeedColmn[27] = "ItemSizeName";
		NeedColmn[28] = "Com01";
		NeedColmn[29] = "Com02";
		NeedColmn[30] = "Com03";
		NeedColmn[31] = "EntryDate";
		NeedColmn[32] = "UpdateDate";
		NeedColmn[33] = "EntryUser";
		NeedColmn[34] = "UpdateUser";
		NeedColmn[35] = "TildFG";
		NeedColmn[36] = "TildName";
		NeedColmn[37] = "PictPass01";
		NeedColmn[38] = "PictPass02";
		NeedColmn[39] = "PictPass03";
		NeedColmn[40] = "PictPass04";
		NeedColmn[41] = "PictPass05";
		NeedColmn[42] = "ExpDateHowLong";
		
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
			String sql = KM0061_ITEMMSTSUBAltherTableSql(NoHitColumn);
			KickSql("NANKO",sql);
		}

		ColumnList = ColumnList("NANKO","KM0062_ItemComversionMst");
		NeedColmn = new String[5];
		NeedColmn[ 0] = "ClGpCd";
		NeedColmn[ 1] = "ClCd";
		NeedColmn[ 2] = "ClItemCd";
		NeedColmn[ 3] = "ItemCd";
		NeedColmn[ 4] = "PackingType";
		
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
			String sql = KM0062_ItemComversionMstAltherTableSql(NoHitColumn);
			KickSql("NANKO",sql);
		}

		ColumnList = ColumnList("NANKO","KM0070_SHIPPINGCOMPANYMST");
		NeedColmn = new String[23];
		NeedColmn[ 0] = "ShippingCompanyCd";
		NeedColmn[ 1] = "ShippingCompanyName01";
		NeedColmn[ 2] = "ShippingCompanyName02";
		NeedColmn[ 3] = "ShippingCompanyName03";
		NeedColmn[ 4] = "Post";
		NeedColmn[ 5] = "Add01";
		NeedColmn[ 6] = "Add02";
		NeedColmn[ 7] = "Add03";
		NeedColmn[ 8] = "Tel";
		NeedColmn[ 9] = "Fax";
		NeedColmn[10] = "Mail";
		NeedColmn[11] = "Com01";
		NeedColmn[12] = "Com02";
		NeedColmn[13] = "Com03";
		NeedColmn[14] = "ShimeDate";
		NeedColmn[15] = "ShimeBasis";
		NeedColmn[16] = "EntryDate";
		NeedColmn[17] = "UpdateDate";
		NeedColmn[18] = "EntryUser";
		NeedColmn[19] = "UpdateUser";
		NeedColmn[20] = "PTMSCD";
		NeedColmn[21] = "DelFg";
		NeedColmn[22] = "ExportDataType";
		
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
			String sql = KM0070_SHIPPINGCOMPANYMSTAltherTableSql(NoHitColumn);
			KickSql("NANKO",sql);
		}

		ColumnList = ColumnList("NANKO","KM0071_CARMST");
		NeedColmn = new String[13];
		NeedColmn[ 0] = "WHCD";
		NeedColmn[ 1] = "ShippingCompanyCd";
		NeedColmn[ 2] = "CarCd";
		NeedColmn[ 3] = "CarName01";
		NeedColmn[ 4] = "CarName02";
		NeedColmn[ 5] = "CarName03";
		NeedColmn[ 6] = "DriverCd";
		NeedColmn[ 7] = "PTMSCD";
		NeedColmn[ 8] = "EntryDate";
		NeedColmn[ 9] = "UpdateDate";
		NeedColmn[10] = "EntryUser";
		NeedColmn[11] = "UpdateUser";
		NeedColmn[12] = "DelFg";
		
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
			String sql = KM0071_CARMSTAltherTableSql(NoHitColumn);
			KickSql("NANKO",sql);
		}

		ColumnList = ColumnList("NANKO","KM0080_FEEBASEMST");
		NeedColmn = new String[54];
		NeedColmn[ 0] = "cl_cd";
		NeedColmn[ 1] = "DeliveryTypeCd";
		NeedColmn[ 2] = "DeliveryTypeCd02";
		NeedColmn[ 3] = "DeliveryTypeCd03";
		NeedColmn[ 4] = "DeliveryTypeCd04";
		NeedColmn[ 5] = "DeliveryTypeCd05";
		NeedColmn[ 6] = "PatternCD";
		NeedColmn[ 7] = "PatternName";
		NeedColmn[ 8] = "LogicType";
		NeedColmn[ 9] = "SizeFG";
		NeedColmn[10] = "BaseFee";
		NeedColmn[11] = "MinFee";
		NeedColmn[12] = "Range01";
		NeedColmn[13] = "RangeBaseFee01";
		NeedColmn[14] = "RangeUnitFee01";
		NeedColmn[15] = "Range02";
		NeedColmn[16] = "RangeBaseFee02";
		NeedColmn[17] = "RangeUnitFee02";
		NeedColmn[18] = "Range03";
		NeedColmn[19] = "RangeBaseFee03";
		NeedColmn[20] = "RangeUnitFee03";
		NeedColmn[21] = "Range04";
		NeedColmn[22] = "RangeBaseFee04";
		NeedColmn[23] = "RangeUnitFee04";
		NeedColmn[24] = "Range05";
		NeedColmn[25] = "RangeBaseFee05";
		NeedColmn[26] = "RangeUnitFee05";
		NeedColmn[27] = "Range06";
		NeedColmn[28] = "RangeBaseFee06";
		NeedColmn[29] = "RangeUnitFee06";
		NeedColmn[30] = "Range07";
		NeedColmn[31] = "RangeBaseFee07";
		NeedColmn[32] = "RangeUnitFee07";
		NeedColmn[33] = "Range08";
		NeedColmn[34] = "RangeBaseFee08";
		NeedColmn[35] = "RangeUnitFee08";
		NeedColmn[36] = "Range09";
		NeedColmn[37] = "RangeBaseFee09";
		NeedColmn[38] = "RangeUnitFee09";
		NeedColmn[39] = "Range10";
		NeedColmn[40] = "RangeBaseFee10";
		NeedColmn[41] = "RangeUnitFee10";
		NeedColmn[42] = "AddDeliFee01";
		NeedColmn[43] = "AddDeliFee02";
		NeedColmn[44] = "AddDeliFee03";
		NeedColmn[45] = "HaighWayFee01";
		NeedColmn[46] = "HaighWayFee02";
		NeedColmn[47] = "EntryDate";
		NeedColmn[48] = "UpdateDate";
		NeedColmn[49] = "EntryUser";
		NeedColmn[50] = "UpdateUser";
		NeedColmn[51] = "DelFg";
		NeedColmn[52] = "DelijudgeJisFg";
		NeedColmn[53] = "AddDeliFee03UnitFee";
		
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
			String sql = KM0080_FEEBASEMSTAltherTableSql(NoHitColumn);
			KickSql("NANKO",sql);
		}

		ColumnList = ColumnList("NANKO","KM0081_FEEMST");
		NeedColmn = new String[14];
		NeedColmn[ 0] = "cl_cd";
		NeedColmn[ 1] = "PickupWHCD";
		NeedColmn[ 2] = "DECD";
		NeedColmn[ 3] = "DepartmentCd";
		NeedColmn[ 4] = "DeliveryTypeCd";
		NeedColmn[ 5] = "DeliveryTypeCd02";
		NeedColmn[ 6] = "DeliveryTypeCd03";
		NeedColmn[ 7] = "DeliveryTypeCd04";
		NeedColmn[ 8] = "DeliveryTypeCd05";
		NeedColmn[ 9] = "PatternCD";
		NeedColmn[10] = "EntryDate";
		NeedColmn[11] = "UpdateDate";
		NeedColmn[12] = "EntryUser";
		NeedColmn[13] = "UpdateUser";
		
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
			String sql = KM0081_FEEMSTAltherTableSql(NoHitColumn);
			KickSql("NANKO",sql);
		}

		ColumnList = ColumnList("NANKO","KM0082_FEELOGICTYPEMST");
		NeedColmn = new String[10];
		NeedColmn[ 0] = "LogicType";
		NeedColmn[ 1] = "LogicTypeName";
		NeedColmn[ 2] = "PGCd";
		NeedColmn[ 3] = "PGName";
		NeedColmn[ 4] = "CallScene";
		NeedColmn[ 5] = "EntryDate";
		NeedColmn[ 6] = "UpdateDate";
		NeedColmn[ 7] = "EntryUser";
		NeedColmn[ 8] = "UpdateUser";
		NeedColmn[ 9] = "DelFg";
		
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
			String sql = KM0082_FEELOGICTYPEMSTAltherTableSql(NoHitColumn);
			KickSql("NANKO",sql);
		}

		ColumnList = ColumnList("NANKO","KM0090_CAUTION");
		NeedColmn = new String[12];
		NeedColmn[ 0] = "CautionCd";
		NeedColmn[ 1] = "ClGpCD";
		NeedColmn[ 2] = "DECD";
		NeedColmn[ 3] = "DepartmentCd";
		NeedColmn[ 4] = "CautionTiming";
		NeedColmn[ 5] = "CautionName";
		NeedColmn[ 6] = "Caution";
		NeedColmn[ 7] = "EntryDate";
		NeedColmn[ 8] = "UpdateDate";
		NeedColmn[ 9] = "EntryUser";
		NeedColmn[10] = "UpdateUser";
		NeedColmn[11] = "DelFg";
		
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
			String sql = KM0090_CAUTIONAltherTableSql(NoHitColumn);
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
	private static String KM0030_CLIENTMSTCreateSql() {
		//荷主マスタを作るNYANKO
		String sql = ""
				+"CREATE TABLE `KM0030_CLIENTMST` ("
				+"  `cl_cd` varchar(20) NOT NULL,"
				+"  `ClGpCD` varchar(50) DEFAULT NULL,"
				+"  `WHCD` varchar(20) NOT NULL,"
				+"  `CLName01` varchar(50) DEFAULT NULL,"
				+"  `CLName02` varchar(50) DEFAULT NULL,"
				+"  `CLName03` varchar(50) DEFAULT NULL,"
				+"  `Post` varchar(20) DEFAULT NULL,"
				+"  `Add01` varchar(100) DEFAULT NULL,"
				+"  `Add02` varchar(100) DEFAULT NULL,"
				+"  `Add03` varchar(100) DEFAULT NULL,"
				+"  `Tel` varchar(20) DEFAULT NULL,"
				+"  `Fax` varchar(20) DEFAULT NULL,"
				+"  `Mail` varchar(100) DEFAULT NULL,"
				+"  `MunicCd` varchar(20) DEFAULT NULL,"
				+"  `Com01` varchar(100) DEFAULT NULL,"
				+"  `Com02` varchar(100) DEFAULT NULL,"
				+"  `Com03` varchar(100) DEFAULT NULL,"
				+"  `ShimeDate` int(11) DEFAULT '99',"
				+"  `ShimeBasis` int(11) DEFAULT '0',"
				+"  `EntryDate` datetime DEFAULT NULL,"
				+"  `UpdateDate` datetime DEFAULT NULL,"
				+"  `EntryUser` varchar(50) DEFAULT NULL,"
				+"  `UpdateUser` varchar(50) DEFAULT NULL,"
				+"  `PTMSCD` varchar(20) DEFAULT NULL,"
				+"  `DelFg` int(11) NOT NULL DEFAULT '0',"
				+"  PRIMARY KEY (`cl_cd`)"
				+") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='荷主マスタ';";
		return sql;
	}
	
	private static String  KM0030_CLIENTMSTAltherTableSql(ArrayList<String> NoHitColumn){
			String sql = ""
					+"ALTER TABLE "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0030_CLIENTMST";
			for(int i=0;i<NoHitColumn.size();i++) {
				if(0<i) {sql = sql + ",";}
				switch(NoHitColumn.get(i)) {
					case "cl_cd":
						sql = sql + " ADD cl_cd varchar(20) NOT NULL";
						break;
					case "ClGpCD":
						sql = sql + " ADD ClGpCD varchar(50) DEFAULT NULL";
						break;
					case "WHCD":
						sql = sql + " ADD WHCD varchar(20) NOT NULL";
						break;
					case "CLName01":
						sql = sql + " ADD CLName01 varchar(50) DEFAULT NULL";
						break;
					case "CLName02":
						sql = sql + " ADD CLName02 varchar(50) DEFAULT NULL";
						break;
					case "CLName03":
						sql = sql + " ADD CLName03 varchar(50) DEFAULT NULL";
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
					case "MunicCd":
						sql = sql + " ADD MunicCd varchar(20) DEFAULT NULL";
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
					case "ShimeDate":
						sql = sql + " ADD ShimeDate int(11) DEFAULT '99'";
						break;
					case "ShimeBasis":
						sql = sql + " ADD ShimeBasis int(11) DEFAULT '0'";
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
						sql = sql + " ADD DelFg int(11) NOT NULL DEFAULT '0'";
						break;
					default:
						break;
				}
			}
			return sql;
	 }

	private static String KM0031_CLIENT_GROUPCreateSql() {
		//荷主グループマスタを作るNYANKO
		String sql = ""
				+"CREATE TABLE `KM0031_CLIENT_GROUP` ("
				+"  `ClGpCD` varchar(20) NOT NULL,"
				+"  `ClGpName01` varchar(50) DEFAULT NULL,"
				+"  `ClGpName02` varchar(50) DEFAULT NULL,"
				+"  `ClGpName03` varchar(50) DEFAULT NULL,"
				+"  `Post` varchar(20) DEFAULT NULL,"
				+"  `Add01` varchar(100) DEFAULT NULL,"
				+"  `Add02` varchar(100) DEFAULT NULL,"
				+"  `Add03` varchar(100) DEFAULT NULL,"
				+"  `Tel` varchar(20) DEFAULT NULL,"
				+"  `Fax` varchar(20) DEFAULT NULL,"
				+"  `Mail` varchar(200) DEFAULT NULL,"
				+"  `Com01` varchar(100) DEFAULT NULL,"
				+"  `Com02` varchar(100) DEFAULT NULL,"
				+"  `Com03` varchar(100) DEFAULT NULL,"
				+"  `BankMemo` varchar(300) DEFAULT NULL,"
				+"  `EntryDate` datetime DEFAULT NULL,"
				+"  `UpdateDate` datetime DEFAULT NULL,"
				+"  `EntryUser` varchar(50) DEFAULT NULL,"
				+"  `UpdateUser` varchar(50) DEFAULT NULL,"
				+"  `DelFg` tinyint(1) NOT NULL DEFAULT '0',"
				+"  `PassWord` varchar(20) NOT NULL,"
				+"  PRIMARY KEY (`ClGpCD`)"
				+") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='荷主グループマスタ';";
		return sql;
	}
	
	private static String  KM0031_CLIENT_GROUPAltherTableSql(ArrayList<String> NoHitColumn){
		String sql = ""
				+"ALTER TABLE "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP";
		for(int i=0;i<NoHitColumn.size();i++) {
			if(0<i) {sql = sql + ",";}
			switch(NoHitColumn.get(i)) {
				case "ClGpCD":
					sql = sql + " ADD ClGpCD varchar(20) NOT NULL";
					break;
				case "ClGpName01":
					sql = sql + " ADD ClGpName01 varchar(50) DEFAULT NULL";
					break;
				case "ClGpName02":
					sql = sql + " ADD ClGpName02 varchar(50) DEFAULT NULL";
					break;
				case "ClGpName03":
					sql = sql + " ADD ClGpName03 varchar(50) DEFAULT NULL";
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
					sql = sql + " ADD Mail varchar(200) DEFAULT NULL";
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
				case "BankMemo":
					sql = sql + " ADD BankMemo varchar(300) DEFAULT NULL";
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
					sql = sql + " ADD DelFg tinyint(1) NOT NULL DEFAULT '0'";
					break;
				case "PassWord":
					sql = sql + " ADD PassWord varchar(20) NOT NULL";
					break;
				default:
					break;
			}
		}
		return sql;
	}

	private static String KM0040_DELIVERYMSTCreateSql() {
		//届先マスタを作るNYANKO
		String sql = ""
				+"CREATE TABLE `KM0040_DELIVERYMST` ("
				+"  `DECD` varchar(20) NOT NULL,"
				+"  `DepartmentCd` varchar(20) NOT NULL,"
				+"  `DEName01` varchar(50) DEFAULT NULL,"
				+"  `DEName02` varchar(50) DEFAULT NULL,"
				+"  `DEName03` varchar(50) DEFAULT NULL,"
				+"  `Post` varchar(20) DEFAULT NULL,"
				+"  `Add01` varchar(100) DEFAULT NULL,"
				+"  `Add02` varchar(100) DEFAULT NULL,"
				+"  `Add03` varchar(100) DEFAULT NULL,"
				+"  `Tel` varchar(20) DEFAULT NULL,"
				+"  `Fax` varchar(20) DEFAULT NULL,"
				+"  `Mail` varchar(50) DEFAULT NULL,"
				+"  `Com01` varchar(100) DEFAULT NULL,"
				+"  `Com02` varchar(100) DEFAULT NULL,"
				+"  `Com03` varchar(100) DEFAULT NULL,"
				+"  `PrefecturesCd` varchar(10) DEFAULT NULL,"
				+"  `MunicipalityCd` varchar(10) DEFAULT NULL,"
				+"  `PTMSCD` varchar(20) DEFAULT NULL,"
				+"  `EntryDate` datetime DEFAULT NULL,"
				+"  `UpdateDate` datetime DEFAULT NULL,"
				+"  `EntryUser` varchar(50) DEFAULT NULL,"
				+"  `UpdateUser` varchar(50) DEFAULT NULL,"
				+"  `FirstClient` varchar(20) DEFAULT NULL,"
				+"  `LastClient` varchar(20) DEFAULT NULL,"
				+"  `DelFg` int(11) DEFAULT '0',"
				+"  PRIMARY KEY (`DECD`,`DepartmentCd`)"
				+") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='届先マスタ';";
		return sql;
	}
	
	private static String  KM0040_DELIVERYMSTAltherTableSql(ArrayList<String> NoHitColumn){
		String sql = ""
				+"ALTER TABLE "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0040_DELIVERYMST";
		for(int i=0;i<NoHitColumn.size();i++) {
			if(0<i) {sql = sql + ",";}
			switch(NoHitColumn.get(i)) {
				case "DECD":
					sql = sql + " ADD DECD varchar(20) NOT NULL";
					break;
				case "DepartmentCd":
					sql = sql + " ADD DepartmentCd varchar(20) NOT NULL";
					break;
				case "DEName01":
					sql = sql + " ADD DEName01 varchar(50) DEFAULT NULL";
					break;
				case "DEName02":
					sql = sql + " ADD DEName02 varchar(50) DEFAULT NULL";
					break;
				case "DEName03":
					sql = sql + " ADD DEName03 varchar(50) DEFAULT NULL";
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
					sql = sql + " ADD Mail varchar(50) DEFAULT NULL";
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
				case "PrefecturesCd":
					sql = sql + " ADD PrefecturesCd varchar(10) DEFAULT NULL";
					break;
				case "MunicipalityCd":
					sql = sql + " ADD MunicipalityCd varchar(10) DEFAULT NULL";
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
				case "FirstClient":
					sql = sql + " ADD FirstClient varchar(20) DEFAULT NULL";
					break;
				case "LastClient":
					sql = sql + " ADD LastClient varchar(20) DEFAULT NULL";
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

	private static String KM0041_DELIVERY_COMVERSIONMSTCreateSql() {
		//届先変換マスタを作るNYANKO
		String sql = ""
				+"CREATE TABLE `KM0041_DELIVERY_COMVERSIONMST` ("
				+"  `ClGpCD` varchar(20) NOT NULL,"
				+"  `CL_DECD` varchar(20) NOT NULL,"
				+"  `DECD` varchar(20) NOT NULL,"
				+"  `DepartmentCd` varchar(20) NOT NULL,"
				+"  `SetName` varchar(100) DEFAULT NULL,"
				+"  `Com01` varchar(100) DEFAULT NULL,"
				+"  `Com02` varchar(100) DEFAULT NULL,"
				+"  `Com03` varchar(100) DEFAULT NULL,"
				+"  `Com04` varchar(100) DEFAULT NULL,"
				+"  `Com05` varchar(100) DEFAULT NULL,"
				+"  `EntryDate` datetime DEFAULT NULL,"
				+"  `UpdateDate` datetime DEFAULT NULL,"
				+"  `EntryUser` varchar(50) DEFAULT NULL,"
				+"  `UpdateUser` varchar(50) DEFAULT NULL,"
				+"  `DelFg` int(11) DEFAULT '0',"
				+"  `MstPriorityFirstFg` int(11) DEFAULT '0',"
				+"  PRIMARY KEY (`ClGpCD`,`CL_DECD`)"
				+") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='届先変換マスタ';";
		return sql;
	}
	
	private static String  KM0041_DELIVERY_COMVERSIONMSTAltherTableSql(ArrayList<String> NoHitColumn){
		String sql = ""
				+"ALTER TABLE "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0041_DELIVERY_COMVERSIONMST";
		for(int i=0;i<NoHitColumn.size();i++) {
			if(0<i) {sql = sql + ",";}
			switch(NoHitColumn.get(i)) {
				case "ClGpCD":
					sql = sql + " ADD ClGpCD varchar(20) NOT NULL";
					break;
				case "CL_DECD":
					sql = sql + " ADD CL_DECD varchar(20) NOT NULL";
					break;
				case "DECD":
					sql = sql + " ADD DECD varchar(20) NOT NULL";
					break;
				case "DepartmentCd":
					sql = sql + " ADD DepartmentCd varchar(20) NOT NULL";
					break;
				case "SetName":
					sql = sql + " ADD SetName varchar(100) DEFAULT NULL";
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
				case "Com04":
					sql = sql + " ADD Com04 varchar(100) DEFAULT NULL";
					break;
				case "Com05":
					sql = sql + " ADD Com05 varchar(100) DEFAULT NULL";
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
				case "MstPriorityFirstFg":
					sql = sql + " ADD MstPriorityFirstFg int(11) DEFAULT '0'";
					break;
				default:
					break;
			}
		}
		return sql;
	}

	private static String KM0050_DELIVERY_TYPEMSTCreateSql() {
		//運送タイプマスタを作るNYANKO
		String sql = ""
				+"CREATE TABLE `KM0050_DELIVERY_TYPEMST` ("
				+"  `DeliveryTypeNo` int(11) NOT NULL,"
				+"  `DeliveryTypeCd` varchar(20) NOT NULL,"
				+"  `DeliveryTypeName` varchar(20) DEFAULT NULL,"
				+"  `EntryDate` datetime DEFAULT NULL,"
				+"  `UpdateDate` datetime DEFAULT NULL,"
				+"  `EntryUser` varchar(50) DEFAULT NULL,"
				+"  `UpdateUser` varchar(50) DEFAULT NULL,"
				+"  PRIMARY KEY (`DeliveryTypeNo`,`DeliveryTypeCd`)"
				+") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='運送タイプマスタ';";
		return sql;
	}
	
	private static String  KM0050_DELIVERY_TYPEMSTAltherTableSql(ArrayList<String> NoHitColumn){
		String sql = ""
				+"ALTER TABLE "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0050_DELIVERY_TYPEMST";
		for(int i=0;i<NoHitColumn.size();i++) {
			if(0<i) {sql = sql + ",";}
			switch(NoHitColumn.get(i)) {
				case "DeliveryTypeNo":
					sql = sql + " ADD DeliveryTypeNo int(11) NOT NULL";
					break;
				case "DeliveryTypeCd":
					sql = sql + " ADD DeliveryTypeCd varchar(20) NOT NULL";
					break;
				case "DeliveryTypeName":
					sql = sql + " ADD DeliveryTypeName varchar(20) DEFAULT NULL";
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
	
	private static String KM0060_ITEMMSTCreateSql() {
		//商品マスタを作るNYANKO
		String sql = ""
				+"CREATE TABLE `KM0060_ITEMMST` ("
				+"  `ClGpCD` varchar(20) NOT NULL,"
				+"  `ItemCd` varchar(20) NOT NULL,"
				+"  `CLItemCd` varchar(20) NOT NULL,"
				+"  `ItemName01` varchar(100) DEFAULT NULL,"
				+"  `ItemName02` varchar(100) DEFAULT NULL,"
				+"  `ItemName03` varchar(100) DEFAULT NULL,"
				+"  `ItemWeight` float DEFAULT '0',"
				+"  `ItemSize` float DEFAULT '0',"
				+"  `DeliveryTypeCd` varchar(20) DEFAULT NULL,"
				+"  `DeliveryTypeCd02` varchar(20) DEFAULT NULL,"
				+"  `DeliveryTypeCd03` varchar(20) DEFAULT NULL,"
				+"  `DeliveryTypeCd04` varchar(20) DEFAULT NULL,"
				+"  `DeliveryTypeCd05` varchar(20) DEFAULT NULL,"
				+"  `EntryDate` datetime DEFAULT NULL,"
				+"  `UpdateDate` datetime DEFAULT NULL,"
				+"  `EntryUser` varchar(50) DEFAULT NULL,"
				+"  `UpdateUser` varchar(50) DEFAULT NULL,"
				+"  `DelFg` int(11) DEFAULT '0',"
				+"  `PTMSCD` varchar(20) DEFAULT NULL,"
				+"  `JanCd` varchar(20) DEFAULT NULL,"
				+"  `UnitName` varchar(50) DEFAULT NULL,"
				+"  PRIMARY KEY (`ClGpCD`,`ItemCd`)"
				+") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品マスタ';";
		return sql;
	}

		private static String  KM0060_ITEMMSTAltherTableSql(ArrayList<String> NoHitColumn){
		String sql = ""
				+"ALTER TABLE "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0060_ITEMMST";
		for(int i=0;i<NoHitColumn.size();i++) {
			if(0<i) {sql = sql + ",";}
			switch(NoHitColumn.get(i)) {
				case "ClGpCD":
					sql = sql + " ADD ClGpCD varchar(20) NOT NULL";
					break;
				case "ItemCd":
					sql = sql + " ADD ItemCd varchar(20) NOT NULL";
					break;
				case "CLItemCd":
					sql = sql + " ADD CLItemCd varchar(20) NOT NULL";
					break;
				case "ItemName01":
					sql = sql + " ADD ItemName01 varchar(100) DEFAULT NULL";
					break;
				case "ItemName02":
					sql = sql + " ADD ItemName02 varchar(100) DEFAULT NULL";
					break;
				case "ItemName03":
					sql = sql + " ADD ItemName03 varchar(100) DEFAULT NULL";
					break;
				case "ItemWeight":
					sql = sql + " ADD ItemWeight float DEFAULT '0'";
					break;
				case "ItemSize":
					sql = sql + " ADD ItemSize float DEFAULT '0'";
					break;
				case "DeliveryTypeCd":
					sql = sql + " ADD DeliveryTypeCd varchar(20) DEFAULT NULL";
					break;
				case "DeliveryTypeCd02":
					sql = sql + " ADD DeliveryTypeCd02 varchar(20) DEFAULT NULL";
					break;
				case "DeliveryTypeCd03":
					sql = sql + " ADD DeliveryTypeCd03 varchar(20) DEFAULT NULL";
					break;
				case "DeliveryTypeCd04":
					sql = sql + " ADD DeliveryTypeCd04 varchar(20) DEFAULT NULL";
					break;
				case "DeliveryTypeCd05":
					sql = sql + " ADD DeliveryTypeCd05 varchar(20) DEFAULT NULL";
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
				case "PTMSCD":
					sql = sql + " ADD PTMSCD varchar(20) DEFAULT NULL";
					break;
				case "JanCd":
					sql = sql + " ADD JanCd varchar(20) DEFAULT NULL";
					break;
				case "UnitName":
					sql = sql + " ADD UnitName varchar(50) DEFAULT NULL";
					break;
				default:
					break;
			}
		}
		return sql;
	}
	

	private static String KM0061_ITEMMSTSUBCreateSql() {
		//商品サブマスタを作るNYANKO
		String sql = ""
				+"CREATE TABLE `KM0061_ITEMMSTSUB` ("
				+"  `ClGpCd` varchar(20) NOT NULL,"
				+"  `ItemCd` varchar(20) NOT NULL,"
				+"  `CtQty` int(11) DEFAULT '1',"
				+"  `CsQty` int(11) DEFAULT '1',"
				+"  `PlQty` int(11) DEFAULT '1',"
				+"  `CtJan` varchar(20) DEFAULT NULL,"
				+"  `CsJan` varchar(20) DEFAULT NULL,"
				+"  `PlJan` varchar(20) DEFAULT NULL,"
				+"  `CtName` varchar(100) DEFAULT NULL,"
				+"  `CsName` varchar(100) DEFAULT NULL,"
				+"  `PlName` varchar(100) DEFAULT NULL,"
				+"  `CtUnitName` varchar(20) DEFAULT NULL,"
				+"  `CsUnitName` varchar(20) DEFAULT NULL,"
				+"  `PlUnitName` varchar(20) DEFAULT NULL,"
				+"  `CtWeight` float DEFAULT '0',"
				+"  `CsWeight` float DEFAULT '0',"
				+"  `PlWeight` float DEFAULT '0',"
				+"  `CtSize` float DEFAULT '0',"
				+"  `CsSize` float DEFAULT '0',"
				+"  `PlSize` float DEFAULT '0',"
				+"  `RecomendLoc` varchar(20) DEFAULT NULL,"
				+"  `ItemMDNo` varchar(20) DEFAULT NULL,"
				+"  `CategoryCd` varchar(20) DEFAULT NULL,"
				+"  `CategoryName` varchar(50) DEFAULT NULL,"
				+"  `ItemColorCd` varchar(20) DEFAULT NULL,"
				+"  `ItemColorName` varchar(50) DEFAULT NULL,"
				+"  `ItemSizeCd` varchar(20) DEFAULT NULL,"
				+"  `ItemSizeName` varchar(50) DEFAULT NULL,"
				+"  `Com01` varchar(100) DEFAULT NULL,"
				+"  `Com02` varchar(100) DEFAULT NULL,"
				+"  `Com03` varchar(100) DEFAULT NULL,"
				+"  `EntryDate` datetime DEFAULT NULL,"
				+"  `UpdateDate` datetime DEFAULT NULL,"
				+"  `EntryUser` varchar(50) DEFAULT NULL,"
				+"  `UpdateUser` varchar(50) DEFAULT NULL,"
				+"  `TildFG` varchar(20) NOT NULL,"
				+"  `TildName` varchar(50) NOT NULL,"
				+"  `PictPass01` varchar(300) DEFAULT NULL,"
				+"  `PictPass02` varchar(300) DEFAULT NULL,"
				+"  `PictPass03` varchar(300) DEFAULT NULL,"
				+"  `PictPass04` varchar(300) DEFAULT NULL,"
				+"  `PictPass05` varchar(300) DEFAULT NULL,"
				+"  `ExpDateHowLong` int(11) DEFAULT '0',"
				+"  PRIMARY KEY (`ClGpCd`,`ItemCd`)"
				+") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品サブマスタ';";
		return sql;
	}

	private static String  KM0061_ITEMMSTSUBAltherTableSql(ArrayList<String> NoHitColumn){
		String sql = ""
				+"ALTER TABLE "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0061_ITEMMSTSUB";
		for(int i=0;i<NoHitColumn.size();i++) {
			if(0<i) {sql = sql + ",";}
			switch(NoHitColumn.get(i)) {
				case "ClGpCd":
					sql = sql + " ADD ClGpCd varchar(20) NOT NULL";
					break;
				case "ItemCd":
					sql = sql + " ADD ItemCd varchar(20) NOT NULL";
					break;
				case "CtQty":
					sql = sql + " ADD CtQty int(11) DEFAULT '1'";
					break;
				case "CsQty":
					sql = sql + " ADD CsQty int(11) DEFAULT '1'";
					break;
				case "PlQty":
					sql = sql + " ADD PlQty int(11) DEFAULT '1'";
					break;
				case "CtJan":
					sql = sql + " ADD CtJan varchar(20) DEFAULT NULL";
					break;
				case "CsJan":
					sql = sql + " ADD CsJan varchar(20) DEFAULT NULL";
					break;
				case "PlJan":
					sql = sql + " ADD PlJan varchar(20) DEFAULT NULL";
					break;
				case "CtName":
					sql = sql + " ADD CtName varchar(100) DEFAULT NULL";
					break;
				case "CsName":
					sql = sql + " ADD CsName varchar(100) DEFAULT NULL";
					break;
				case "PlName":
					sql = sql + " ADD PlName varchar(100) DEFAULT NULL";
					break;
				case "CtUnitName":
					sql = sql + " ADD CtUnitName varchar(20) DEFAULT NULL";
					break;
				case "CsUnitName":
					sql = sql + " ADD CsUnitName varchar(20) DEFAULT NULL";
					break;
				case "PlUnitName":
					sql = sql + " ADD PlUnitName varchar(20) DEFAULT NULL";
					break;
				case "CtWeight":
					sql = sql + " ADD CtWeight float DEFAULT '0'";
					break;
				case "CsWeight":
					sql = sql + " ADD CsWeight float DEFAULT '0'";
					break;
				case "PlWeight":
					sql = sql + " ADD PlWeight float DEFAULT '0'";
					break;
				case "CtSize":
					sql = sql + " ADD CtSize float DEFAULT '0'";
					break;
				case "CsSize":
					sql = sql + " ADD CsSize float DEFAULT '0'";
					break;
				case "PlSize":
					sql = sql + " ADD PlSize float DEFAULT '0'";
					break;
				case "RecomendLoc":
					sql = sql + " ADD RecomendLoc varchar(20) DEFAULT NULL";
					break;
				case "ItemMDNo":
					sql = sql + " ADD ItemMDNo varchar(20) DEFAULT NULL";
					break;
				case "CategoryCd":
					sql = sql + " ADD CategoryCd varchar(20) DEFAULT NULL";
					break;
				case "CategoryName":
					sql = sql + " ADD CategoryName varchar(50) DEFAULT NULL";
					break;
				case "ItemColorCd":
					sql = sql + " ADD ItemColorCd varchar(20) DEFAULT NULL";
					break;
				case "ItemColorName":
					sql = sql + " ADD ItemColorName varchar(50) DEFAULT NULL";
					break;
				case "ItemSizeCd":
					sql = sql + " ADD ItemSizeCd varchar(20) DEFAULT NULL";
					break;
				case "ItemSizeName":
					sql = sql + " ADD ItemSizeName varchar(50) DEFAULT NULL";
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
				case "TildFG":
					sql = sql + " ADD TildFG varchar(20) NOT NULL";
					break;
				case "TildName":
					sql = sql + " ADD TildName varchar(50) NOT NULL";
					break;
				case "PictPass01":
					sql = sql + " ADD PictPass01 varchar(300) DEFAULT NULL";
					break;
				case "PictPass02":
					sql = sql + " ADD PictPass02 varchar(300) DEFAULT NULL";
					break;
				case "PictPass03":
					sql = sql + " ADD PictPass03 varchar(300) DEFAULT NULL";
					break;
				case "PictPass04":
					sql = sql + " ADD PictPass04 varchar(300) DEFAULT NULL";
					break;
				case "PictPass05":
					sql = sql + " ADD PictPass05 varchar(300) DEFAULT NULL";
					break;
				case "ExpDateHowLong":
					sql = sql + " ADD ExpDateHowLong int(11) DEFAULT '0'";
					break;
				default:
					break;
			}
		}
		return sql;
	}


	private static String KM0062_ItemComversionMstCreateSql() {
		//商品変換マスタを作るNYANKO
		String sql = ""
				+"CREATE TABLE `KM0062_ItemComversionMst` ("
				+"  `ClGpCd` varchar(20) NOT NULL,"
				+"  `ClCd` varchar(20) NOT NULL,"
				+"  `ClItemCd` varchar(20) NOT NULL,"
				+"  `ItemCd` varchar(20) DEFAULT NULL,"
				+"  `PackingType` int(11) NOT NULL DEFAULT '0',"
				+"  PRIMARY KEY (`ClGpCd`,`ClCd`,`ClItemCd`)"
				+") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品変換マスタ';";
		return sql;
	}

	private static String  KM0062_ItemComversionMstAltherTableSql(ArrayList<String> NoHitColumn){
		String sql = ""
				+"ALTER TABLE "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0062_ItemComversionMst";
		for(int i=0;i<NoHitColumn.size();i++) {
			if(0<i) {sql = sql + ",";}
			switch(NoHitColumn.get(i)) {
				case "ClGpCd":
					sql = sql + " ADD ClGpCd varchar(20) NOT NULL";
					break;
				case "ClCd":
					sql = sql + " ADD ClCd varchar(20) NOT NULL";
					break;
				case "ClItemCd":
					sql = sql + " ADD ClItemCd varchar(20) NOT NULL";
					break;
				case "ItemCd":
					sql = sql + " ADD ItemCd varchar(20) DEFAULT NULL";
					break;
				case "PackingType":
					sql = sql + " ADD PackingType int(11) NOT NULL DEFAULT '0'";
					break;
				default:
					break;
			}
		}
		return sql;
	}


	private static String KM0070_SHIPPINGCOMPANYMSTCreateSql() {
		//運送会社マスタを作るNYANKO
		String sql = ""
				+"CREATE TABLE `KM0070_SHIPPINGCOMPANYMST` ("
				+"  `ShippingCompanyCd` varchar(20) NOT NULL,"
				+"  `ShippingCompanyName01` varchar(50) DEFAULT NULL,"
				+"  `ShippingCompanyName02` varchar(50) DEFAULT NULL,"
				+"  `ShippingCompanyName03` varchar(50) DEFAULT NULL,"
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
				+"  `ShimeDate` int(11) DEFAULT '0',"
				+"  `ShimeBasis` int(11) DEFAULT '0',"
				+"  `EntryDate` datetime DEFAULT NULL,"
				+"  `UpdateDate` datetime DEFAULT NULL,"
				+"  `EntryUser` varchar(50) DEFAULT NULL,"
				+"  `UpdateUser` varchar(50) DEFAULT NULL,"
				+"  `PTMSCD` varchar(20) DEFAULT NULL,"
				+"  `DelFg` tinyint(1) NOT NULL DEFAULT '0',"
				+"  `ExportDataType` varchar(20) DEFAULT NULL,"
				+"  PRIMARY KEY (`ShippingCompanyCd`)"
				+") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='運送会社マスタ';";
		return sql;
	}

	private static String  KM0070_SHIPPINGCOMPANYMSTAltherTableSql(ArrayList<String> NoHitColumn){
		String sql = ""
				+"ALTER TABLE "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0070_SHIPPINGCOMPANYMST";
		for(int i=0;i<NoHitColumn.size();i++) {
			if(0<i) {sql = sql + ",";}
			switch(NoHitColumn.get(i)) {
				case "ShippingCompanyCd":
					sql = sql + " ADD ShippingCompanyCd varchar(20) NOT NULL";
					break;
				case "ShippingCompanyName01":
					sql = sql + " ADD ShippingCompanyName01 varchar(50) DEFAULT NULL";
					break;
				case "ShippingCompanyName02":
					sql = sql + " ADD ShippingCompanyName02 varchar(50) DEFAULT NULL";
					break;
				case "ShippingCompanyName03":
					sql = sql + " ADD ShippingCompanyName03 varchar(50) DEFAULT NULL";
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
				case "ShimeDate":
					sql = sql + " ADD ShimeDate int(11) DEFAULT '0'";
					break;
				case "ShimeBasis":
					sql = sql + " ADD ShimeBasis int(11) DEFAULT '0'";
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
				case "ExportDataType":
					sql = sql + " ADD ExportDataType varchar(20) DEFAULT NULL";
					break;
				default:
					break;
			}
		}
		return sql;
	}


	private static String KM0071_CARMSTCreateSql() {
		//車輌マスタを作るNYANKO
		String sql = ""
				+"CREATE TABLE `KM0071_CARMST` ("
				+"  `WHCD` varchar(20) NOT NULL,"
				+"  `ShippingCompanyCd` varchar(20) NOT NULL,"
				+"  `CarCd` varchar(20) NOT NULL,"
				+"  `CarName01` varchar(100) DEFAULT NULL,"
				+"  `CarName02` varchar(100) DEFAULT NULL,"
				+"  `CarName03` varchar(100) DEFAULT NULL,"
				+"  `DriverCd` varchar(20) DEFAULT NULL,"
				+"  `PTMSCD` varchar(12) DEFAULT NULL,"
				+"  `EntryDate` datetime DEFAULT NULL,"
				+"  `UpdateDate` datetime DEFAULT NULL,"
				+"  `EntryUser` varchar(50) DEFAULT NULL,"
				+"  `UpdateUser` varchar(50) DEFAULT NULL,"
				+"  `DelFg` tinyint(1) NOT NULL DEFAULT '0',"
				+"  PRIMARY KEY (`WHCD`,`ShippingCompanyCd`,`CarCd`)"
				+") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='車輌マスタ';";
		return sql;
	}

	private static String  KM0071_CARMSTAltherTableSql(ArrayList<String> NoHitColumn){
		String sql = ""
				+"ALTER TABLE "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0071_CARMST";
		for(int i=0;i<NoHitColumn.size();i++) {
			if(0<i) {sql = sql + ",";}
			switch(NoHitColumn.get(i)) {
				case "WHCD":
					sql = sql + " ADD WHCD varchar(20) NOT NULL";
					break;
				case "ShippingCompanyCd":
					sql = sql + " ADD ShippingCompanyCd varchar(20) NOT NULL";
					break;
				case "CarCd":
					sql = sql + " ADD CarCd varchar(20) NOT NULL";
					break;
				case "CarName01":
					sql = sql + " ADD CarName01 varchar(100) DEFAULT NULL";
					break;
				case "CarName02":
					sql = sql + " ADD CarName02 varchar(100) DEFAULT NULL";
					break;
				case "CarName03":
					sql = sql + " ADD CarName03 varchar(100) DEFAULT NULL";
					break;
				case "DriverCd":
					sql = sql + " ADD DriverCd varchar(20) DEFAULT NULL";
					break;
				case "PTMSCD":
					sql = sql + " ADD PTMSCD varchar(12) DEFAULT NULL";
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
					sql = sql + " ADD DelFg tinyint(1) NOT NULL DEFAULT '0'";
					break;
				default:
					break;
			}
		}
		return sql;
	}


	private static String KM0080_FEEBASEMSTCreateSql() {
		//運賃基本タリフマスタを作るNYANKO
		String sql = ""
				+"CREATE TABLE `KM0080_FEEBASEMST` ("
				+"  `cl_cd` varchar(20) NOT NULL,"
				+"  `DeliveryTypeCd` varchar(20) NOT NULL DEFAULT '0',"
				+"  `DeliveryTypeCd02` varchar(20) NOT NULL DEFAULT '0',"
				+"  `DeliveryTypeCd03` varchar(20) NOT NULL DEFAULT '0',"
				+"  `DeliveryTypeCd04` varchar(20) NOT NULL DEFAULT '0',"
				+"  `DeliveryTypeCd05` varchar(20) NOT NULL DEFAULT '0',"
				+"  `PatternCD` varchar(20) NOT NULL,"
				+"  `PatternName` varchar(100) DEFAULT NULL,"
				+"  `LogicType` varchar(20) DEFAULT NULL,"
				+"  `SizeFG` int(11) DEFAULT '0',"
				+"  `BaseFee` int(11) DEFAULT '0',"
				+"  `MinFee` int(11) DEFAULT '0',"
				+"  `Range01` float DEFAULT '0',"
				+"  `RangeBaseFee01` int(11) DEFAULT '0',"
				+"  `RangeUnitFee01` float DEFAULT '0',"
				+"  `Range02` float DEFAULT '0',"
				+"  `RangeBaseFee02` int(11) DEFAULT '0',"
				+"  `RangeUnitFee02` float DEFAULT '0',"
				+"  `Range03` float DEFAULT '0',"
				+"  `RangeBaseFee03` int(11) DEFAULT '0',"
				+"  `RangeUnitFee03` float DEFAULT '0',"
				+"  `Range04` float DEFAULT '0',"
				+"  `RangeBaseFee04` int(11) DEFAULT '0',"
				+"  `RangeUnitFee04` float DEFAULT '0',"
				+"  `Range05` float DEFAULT '0',"
				+"  `RangeBaseFee05` int(11) DEFAULT '0',"
				+"  `RangeUnitFee05` float DEFAULT '0',"
				+"  `Range06` float DEFAULT '0',"
				+"  `RangeBaseFee06` int(11) DEFAULT '0',"
				+"  `RangeUnitFee06` float DEFAULT '0',"
				+"  `Range07` float DEFAULT '0',"
				+"  `RangeBaseFee07` int(11) DEFAULT '0',"
				+"  `RangeUnitFee07` float DEFAULT '0',"
				+"  `Range08` float DEFAULT '0',"
				+"  `RangeBaseFee08` int(11) DEFAULT '0',"
				+"  `RangeUnitFee08` float DEFAULT '0',"
				+"  `Range09` float DEFAULT '0',"
				+"  `RangeBaseFee09` int(11) DEFAULT '0',"
				+"  `RangeUnitFee09` float DEFAULT '0',"
				+"  `Range10` float DEFAULT '0',"
				+"  `RangeBaseFee10` int(11) DEFAULT '0',"
				+"  `RangeUnitFee10` float DEFAULT '0',"
				+"  `AddDeliFee01` int(11) DEFAULT '0',"
				+"  `AddDeliFee02` int(11) DEFAULT '0',"
				+"  `AddDeliFee03` int(11) DEFAULT '0',"
				+"  `HaighWayFee01` int(11) DEFAULT '0',"
				+"  `HaighWayFee02` int(11) DEFAULT '0',"
				+"  `EntryDate` datetime DEFAULT NULL,"
				+"  `UpdateDate` datetime DEFAULT NULL,"
				+"  `EntryUser` varchar(50) DEFAULT NULL,"
				+"  `UpdateUser` varchar(50) DEFAULT NULL,"
				+"  `DelFg` int(11) NOT NULL DEFAULT '0',"
				+"  `DelijudgeJisFg` int(11) NOT NULL DEFAULT '0',"
				+"  `AddDeliFee03UnitFee` float DEFAULT '0',"
				+"  PRIMARY KEY (`cl_cd`,`DeliveryTypeCd`,`DeliveryTypeCd02`,`DeliveryTypeCd03`,`DeliveryTypeCd04`,`DeliveryTypeCd05`,`PatternCD`)"
				+") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='運賃基本タリフマスタ';";
		return sql;
	}

	private static String  KM0080_FEEBASEMSTAltherTableSql(ArrayList<String> NoHitColumn){
		String sql = ""
				+"ALTER TABLE "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0080_FEEBASEMST";
		for(int i=0;i<NoHitColumn.size();i++) {
			if(0<i) {sql = sql + ",";}
			switch(NoHitColumn.get(i)) {
				case "cl_cd":
					sql = sql + " ADD cl_cd varchar(20) NOT NULL";
					break;
				case "DeliveryTypeCd":
					sql = sql + " ADD DeliveryTypeCd varchar(20) NOT NULL DEFAULT '0'";
					break;
				case "DeliveryTypeCd02":
					sql = sql + " ADD DeliveryTypeCd02 varchar(20) NOT NULL DEFAULT '0'";
					break;
				case "DeliveryTypeCd03":
					sql = sql + " ADD DeliveryTypeCd03 varchar(20) NOT NULL DEFAULT '0'";
					break;
				case "DeliveryTypeCd04":
					sql = sql + " ADD DeliveryTypeCd04 varchar(20) NOT NULL DEFAULT '0'";
					break;
				case "DeliveryTypeCd05":
					sql = sql + " ADD DeliveryTypeCd05 varchar(20) NOT NULL DEFAULT '0'";
					break;
				case "PatternCD":
					sql = sql + " ADD PatternCD varchar(20) NOT NULL";
					break;
				case "PatternName":
					sql = sql + " ADD PatternName varchar(100) DEFAULT NULL";
					break;
				case "LogicType":
					sql = sql + " ADD LogicType varchar(20) DEFAULT NULL";
					break;
				case "SizeFG":
					sql = sql + " ADD SizeFG int(11) DEFAULT '0'";
					break;
				case "BaseFee":
					sql = sql + " ADD BaseFee int(11) DEFAULT '0'";
					break;
				case "MinFee":
					sql = sql + " ADD MinFee int(11) DEFAULT '0'";
					break;
				case "Range01":
					sql = sql + " ADD Range01 float DEFAULT '0'";
					break;
				case "RangeBaseFee01":
					sql = sql + " ADD RangeBaseFee01 int(11) DEFAULT '0'";
					break;
				case "RangeUnitFee01":
					sql = sql + " ADD RangeUnitFee01 float DEFAULT '0'";
					break;
				case "Range02":
					sql = sql + " ADD Range02 float DEFAULT '0'";
					break;
				case "RangeBaseFee02":
					sql = sql + " ADD RangeBaseFee02 int(11) DEFAULT '0'";
					break;
				case "RangeUnitFee02":
					sql = sql + " ADD RangeUnitFee02 float DEFAULT '0'";
					break;
				case "Range03":
					sql = sql + " ADD Range03 float DEFAULT '0'";
					break;
				case "RangeBaseFee03":
					sql = sql + " ADD RangeBaseFee03 int(11) DEFAULT '0'";
					break;
				case "RangeUnitFee03":
					sql = sql + " ADD RangeUnitFee03 float DEFAULT '0'";
					break;
				case "Range04":
					sql = sql + " ADD Range04 float DEFAULT '0'";
					break;
				case "RangeBaseFee04":
					sql = sql + " ADD RangeBaseFee04 int(11) DEFAULT '0'";
					break;
				case "RangeUnitFee04":
					sql = sql + " ADD RangeUnitFee04 float DEFAULT '0'";
					break;
				case "Range05":
					sql = sql + " ADD Range05 float DEFAULT '0'";
					break;
				case "RangeBaseFee05":
					sql = sql + " ADD RangeBaseFee05 int(11) DEFAULT '0'";
					break;
				case "RangeUnitFee05":
					sql = sql + " ADD RangeUnitFee05 float DEFAULT '0'";
					break;
				case "Range06":
					sql = sql + " ADD Range06 float DEFAULT '0'";
					break;
				case "RangeBaseFee06":
					sql = sql + " ADD RangeBaseFee06 int(11) DEFAULT '0'";
					break;
				case "RangeUnitFee06":
					sql = sql + " ADD RangeUnitFee06 float DEFAULT '0'";
					break;
				case "Range07":
					sql = sql + " ADD Range07 float DEFAULT '0'";
					break;
				case "RangeBaseFee07":
					sql = sql + " ADD RangeBaseFee07 int(11) DEFAULT '0'";
					break;
				case "RangeUnitFee07":
					sql = sql + " ADD RangeUnitFee07 float DEFAULT '0'";
					break;
				case "Range08":
					sql = sql + " ADD Range08 float DEFAULT '0'";
					break;
				case "RangeBaseFee08":
					sql = sql + " ADD RangeBaseFee08 int(11) DEFAULT '0'";
					break;
				case "RangeUnitFee08":
					sql = sql + " ADD RangeUnitFee08 float DEFAULT '0'";
					break;
				case "Range09":
					sql = sql + " ADD Range09 float DEFAULT '0'";
					break;
				case "RangeBaseFee09":
					sql = sql + " ADD RangeBaseFee09 int(11) DEFAULT '0'";
					break;
				case "RangeUnitFee09":
					sql = sql + " ADD RangeUnitFee09 float DEFAULT '0'";
					break;
				case "Range10":
					sql = sql + " ADD Range10 float DEFAULT '0'";
					break;
				case "RangeBaseFee10":
					sql = sql + " ADD RangeBaseFee10 int(11) DEFAULT '0'";
					break;
				case "RangeUnitFee10":
					sql = sql + " ADD RangeUnitFee10 float DEFAULT '0'";
					break;
				case "AddDeliFee01":
					sql = sql + " ADD AddDeliFee01 int(11) DEFAULT '0'";
					break;
				case "AddDeliFee02":
					sql = sql + " ADD AddDeliFee02 int(11) DEFAULT '0'";
					break;
				case "AddDeliFee03":
					sql = sql + " ADD AddDeliFee03 int(11) DEFAULT '0'";
					break;
				case "HaighWayFee01":
					sql = sql + " ADD HaighWayFee01 int(11) DEFAULT '0'";
					break;
				case "HaighWayFee02":
					sql = sql + " ADD HaighWayFee02 int(11) DEFAULT '0'";
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
					sql = sql + " ADD DelFg int(11) NOT NULL DEFAULT '0'";
					break;
				case "DelijudgeJisFg":
					sql = sql + " ADD DelijudgeJisFg int(11) NOT NULL DEFAULT '0'";
					break;
				case "AddDeliFee03UnitFee":
					sql = sql + " ADD AddDeliFee03UnitFee float DEFAULT '0'";
					break;
				default:
					break;
			}
		}
		return sql;
	}


	private static String KM0081_FEEMSTCreateSql() {
		//運賃マスタを作るNYANKO
		String sql = ""
				+"CREATE TABLE `KM0081_FEEMST` ("
				+"  `cl_cd` varchar(20) NOT NULL,"
				+"  `PickupWHCD` varchar(20) NOT NULL,"
				+"  `DECD` varchar(20) NOT NULL,"
				+"  `DepartmentCd` varchar(20) NOT NULL,"
				+"  `DeliveryTypeCd` varchar(20) NOT NULL,"
				+"  `DeliveryTypeCd02` varchar(20) NOT NULL,"
				+"  `DeliveryTypeCd03` varchar(20) NOT NULL,"
				+"  `DeliveryTypeCd04` varchar(20) NOT NULL,"
				+"  `DeliveryTypeCd05` varchar(20) NOT NULL,"
				+"  `PatternCD` varchar(20) NOT NULL,"
				+"  `EntryDate` datetime DEFAULT NULL,"
				+"  `UpdateDate` datetime DEFAULT NULL,"
				+"  `EntryUser` varchar(50) DEFAULT NULL,"
				+"  `UpdateUser` varchar(50) DEFAULT NULL,"
				+"  PRIMARY KEY (`cl_cd`,`PickupWHCD`,`DECD`,`DepartmentCd`,`DeliveryTypeCd`,`DeliveryTypeCd02`,`DeliveryTypeCd03`,`DeliveryTypeCd04`,`DeliveryTypeCd05`)"
				+") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='運賃マスタ';";
		return sql;
	}

	private static String  KM0081_FEEMSTAltherTableSql(ArrayList<String> NoHitColumn){
		String sql = ""
				+"ALTER TABLE "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0081_FEEMST";
		for(int i=0;i<NoHitColumn.size();i++) {
			if(0<i) {sql = sql + ",";}
			switch(NoHitColumn.get(i)) {
				case "cl_cd":
					sql = sql + " ADD cl_cd varchar(20) NOT NULL";
					break;
				case "PickupWHCD":
					sql = sql + " ADD PickupWHCD varchar(20) NOT NULL";
					break;
				case "DECD":
					sql = sql + " ADD DECD varchar(20) NOT NULL";
					break;
				case "DepartmentCd":
					sql = sql + " ADD DepartmentCd varchar(20) NOT NULL";
					break;
				case "DeliveryTypeCd":
					sql = sql + " ADD DeliveryTypeCd varchar(20) NOT NULL";
					break;
				case "DeliveryTypeCd02":
					sql = sql + " ADD DeliveryTypeCd02 varchar(20) NOT NULL";
					break;
				case "DeliveryTypeCd03":
					sql = sql + " ADD DeliveryTypeCd03 varchar(20) NOT NULL";
					break;
				case "DeliveryTypeCd04":
					sql = sql + " ADD DeliveryTypeCd04 varchar(20) NOT NULL";
					break;
				case "DeliveryTypeCd05":
					sql = sql + " ADD DeliveryTypeCd05 varchar(20) NOT NULL";
					break;
				case "PatternCD":
					sql = sql + " ADD PatternCD varchar(20) NOT NULL";
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


	private static String KM0082_FEELOGICTYPEMSTCreateSql() {
		//運賃計算ロジックマスタを作るNYANKO
		String sql = ""
				+"CREATE TABLE `KM0082_FEELOGICTYPEMST` ("
				+"  `LogicType` varchar(20) NOT NULL,"
				+"  `LogicTypeName` varchar(100) NOT NULL,"
				+"  `PGCd` varchar(100) NOT NULL,"
				+"  `PGName` varchar(100) NOT NULL,"
				+"  `CallScene` int(11) NOT NULL,"
				+"  `EntryDate` datetime DEFAULT NULL,"
				+"  `UpdateDate` datetime DEFAULT NULL,"
				+"  `EntryUser` varchar(50) DEFAULT NULL,"
				+"  `UpdateUser` varchar(50) DEFAULT NULL,"
				+"  `DelFg` int(11) DEFAULT '0',"
				+"  PRIMARY KEY (`LogicType`)"
				+") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='運賃計算ロジックマスタ';";
		return sql;
	}

	private static String  KM0082_FEELOGICTYPEMSTAltherTableSql(ArrayList<String> NoHitColumn){
		String sql = ""
				+"ALTER TABLE "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0082_FEELOGICTYPEMST";
		for(int i=0;i<NoHitColumn.size();i++) {
			if(0<i) {sql = sql + ",";}
			switch(NoHitColumn.get(i)) {
				case "LogicType":
					sql = sql + " ADD LogicType varchar(20) NOT NULL";
					break;
				case "LogicTypeName":
					sql = sql + " ADD LogicTypeName varchar(100) NOT NULL";
					break;
				case "PGCd":
					sql = sql + " ADD PGCd varchar(100) NOT NULL";
					break;
				case "PGName":
					sql = sql + " ADD PGName varchar(100) NOT NULL";
					break;
				case "CallScene":
					sql = sql + " ADD CallScene int(11) NOT NULL";
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


	private static String KM0090_CAUTIONCreateSql() {
		//注意事項マスタを作るNYANKO
		String sql = ""
				+"CREATE TABLE `KM0090_CAUTION` ("
				+"  `CautionCd` varchar(20) NOT NULL,"
				+"  `ClGpCD` varchar(20) DEFAULT NULL,"
				+"  `DECD` varchar(20) NOT NULL,"
				+"  `DepartmentCd` varchar(20) DEFAULT NULL,"
				+"  `CautionTiming` int(11) NOT NULL DEFAULT '0',"
				+"  `CautionName` varchar(100) DEFAULT NULL,"
				+"  `Caution` varchar(300) DEFAULT NULL,"
				+"  `EntryDate` datetime DEFAULT NULL,"
				+"  `UpdateDate` datetime DEFAULT NULL,"
				+"  `EntryUser` varchar(50) DEFAULT NULL,"
				+"  `UpdateUser` varchar(50) DEFAULT NULL,"
				+"  `DelFg` int(11) DEFAULT '0',"
				+"  PRIMARY KEY (`CautionCd`,`DECD`)"
				+") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='注意事項マスタ';";
		return sql;
	}

	private static String  KM0090_CAUTIONAltherTableSql(ArrayList<String> NoHitColumn){
		String sql = ""
				+"ALTER TABLE "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0090_CAUTION";
		for(int i=0;i<NoHitColumn.size();i++) {
			if(0<i) {sql = sql + ",";}
			switch(NoHitColumn.get(i)) {
				case "CautionCd":
					sql = sql + " ADD CautionCd varchar(20) NOT NULL";
					break;
				case "ClGpCD":
					sql = sql + " ADD ClGpCD varchar(20) DEFAULT NULL";
					break;
				case "DECD":
					sql = sql + " ADD DECD varchar(20) NOT NULL";
					break;
				case "DepartmentCd":
					sql = sql + " ADD DepartmentCd varchar(20) DEFAULT NULL";
					break;
				case "CautionTiming":
					sql = sql + " ADD CautionTiming int(11) NOT NULL DEFAULT '0'";
					break;
				case "CautionName":
					sql = sql + " ADD CautionName varchar(100) DEFAULT NULL";
					break;
				case "Caution":
					sql = sql + " ADD Caution varchar(300) DEFAULT NULL";
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