import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class A00040TableCheck{
	//必要テーブルをチェック→無ければ作る
	// ================================================================
    //  A00040TableCheck（創世記／テーブル自動治癒エンジン）
    // ================================================================
    //
    //  このクラスは、ユーザーID「zeus」ログイン時に起動します。
    //  （ユーザーマスタと zeus ユーザーはシステム起動時に自動降臨します）
    //
    //  ■役割
    //    システムで利用する全テーブルについて、
    //      ・存在チェック
    //      ・未存在時の CREATE TABLE
    //      ・必要カラムの存在チェック
    //      ・不足カラムの ALTER TABLE ADD
    //    を一括で行う、“Corgiが信じる正しいデータベース定義書”です。
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
    //  ■追加開発時の注意
    //      データベース構造に変更があった場合、
    //      必ずこのクラスにも定義を追記してください。
    //      このクラスこそがスキーマの唯一の真実となります。
    //
    // ================================================================

	public static void TableCheck() {
		
		NyankoDBCheck();	//NYANKOデータベースの必要なテーブルを確認する
		WankoDBCheck();		//WANKOデータベースの必要なテーブルを確認する
		PostDBCheck();		//郵便番号データベースの必要なテーブルを確認する
		/*
		
		
		
		OldDBCheck();		//OLDデータベースの必要なテーブルを確認する
		*/
		
	}
	public static void UserMstCreate() {
		//ユーザーマスタテーブル作る
		String[] TableName = TabeleList("NYANKO");
		boolean KM0020_USERMSTUnHitFg = true;
		for(int i=0;i<TableName.length;i++) {
			switch(TableName[i]){
				case "KM0020_USERMST":
					KM0020_USERMSTUnHitFg = false; 
					break;
				default :break;
			}
		}
		if(KM0020_USERMSTUnHitFg) {
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
		boolean KM0000_PARAMETERUnHitFg = true; 
		boolean KM0005_MUNICUnHitFg = true; 
		boolean KM0010_WHMSTUnHitFg = true; 
		boolean KM0020_USERMSTUnHitFg = true;
		boolean KM0030_CLIENTMSTUnHitFg = true; 
		boolean KM0031_CLIENT_GROUPUnHitFg = true; 
		boolean KM0040_DELIVERYMSTUnHitFg = true; 
		boolean KM0041_DELIVERY_COMVERSIONMSTUnHitFg = true; 
		boolean KM0050_DELIVERY_TYPEMSTUnHitFg = true; 
		boolean KM0060_ITEMMSTUnHitFg = true; 
		boolean KM0061_ITEMMSTSUBUnHitFg = true; 
		boolean KM0062_ItemComversionMstUnHitFg = true; 
		boolean KM0070_SHIPPINGCOMPANYMSTUnHitFg = true; 
		boolean KM0071_CARMSTUnHitFg = true; 
		boolean KM0080_FEEBASEMSTUnHitFg = true; 
		boolean KM0081_FEEMSTUnHitFg = true; 
		boolean KM0082_FEELOGICTYPEMSTUnHitFg = true; 
		boolean KM0090_CAUTIONUnHitFg = true; 
		boolean KM0090_PAYBASEMSTUnHitFg = true; 
		boolean KM0091_PAYMSTUnHitFg = true; 
		boolean KM0100_OKURIPROGRESSMSTUnHitFg = true; 
		boolean KM0110_CALLPGMSTUnHitFg = true; 
		boolean KM0122_CourseGlpMSTUnHitFg = true;
		boolean KM0123_CourseMSTUnHitFg = true; 
		boolean KM0124_CourseDeliveryMSTUnHitFg = true; 
		boolean KM0125_MyDriverMSTUnHitFg = true; 
		boolean KM0126_MyDriverListUnHitFg = true;
		boolean KT0010_OKURI_HDUnHitFg = true; 
		boolean KT0011_OKURI_MSUnHitFg = true; 
		boolean KT0012_OKURI_PROGRESSUnHitFg = true; 
		boolean KT0013_SEIKYUUnHitFg = true; 
		boolean KT0023_SHIHARAIUnHitFg = true; 
		boolean KT0040_PrintControlUnHitFg = true; 
		boolean KT020_HAISHA_HDUnHitFg = true; 
		boolean KT021_HAISHA_MSUnHitFg = true; 
		
		for(int i=0;i<TableName.length;i++) {
			switch(TableName[i]){
				case "KM0000_PARAMETER":
					KM0000_PARAMETERUnHitFg = false; 
					break;
				case "KM0005_MUNIC":
					KM0005_MUNICUnHitFg = false; 
					break;
				case "KM0010_WHMST":
					KM0010_WHMSTUnHitFg = false; 
					break;
				case "KM0020_USERMST":
					KM0020_USERMSTUnHitFg = false; 
					break;
				case "KM0030_CLIENTMST":
					KM0030_CLIENTMSTUnHitFg = false; 
					break;
				case "KM0031_CLIENT_GROUP":
					KM0031_CLIENT_GROUPUnHitFg = false; 
					break;
				case "KM0040_DELIVERYMST":
					KM0040_DELIVERYMSTUnHitFg = false; 
					break;
				case "KM0041_DELIVERY_COMVERSIONMST":
					KM0041_DELIVERY_COMVERSIONMSTUnHitFg = false; 
					break;
				case "KM0050_DELIVERY_TYPEMST":
					KM0050_DELIVERY_TYPEMSTUnHitFg = false; 
					break;
				case "KM0060_ITEMMST":
					KM0060_ITEMMSTUnHitFg = false; 
					break;
				case "KM0061_ITEMMSTSUB":
					KM0061_ITEMMSTSUBUnHitFg = false; 
					break;
				case "KM0062_ItemComversionMst":
					KM0062_ItemComversionMstUnHitFg = false; 
					break;
				case "KM0070_SHIPPINGCOMPANYMST":
					KM0070_SHIPPINGCOMPANYMSTUnHitFg = false; 
					break;
				case "KM0071_CARMST":
					KM0071_CARMSTUnHitFg = false; 
					break;
				case "KM0080_FEEBASEMST":
					KM0080_FEEBASEMSTUnHitFg = false; 
					break;
				case "KM0081_FEEMST":
					KM0081_FEEMSTUnHitFg = false; 
					break;
				case "KM0082_FEELOGICTYPEMST":
					KM0082_FEELOGICTYPEMSTUnHitFg = false; 
					break;
				case "KM0090_CAUTION":
					KM0090_CAUTIONUnHitFg = false; 
					break;
				case "KM0090_PAYBASEMST":
					KM0090_PAYBASEMSTUnHitFg = false; 
					break;
				case "KM0091_PAYMST":
					KM0091_PAYMSTUnHitFg = false; 
					break;
				case "KM0100_OKURIPROGRESSMST":
					KM0100_OKURIPROGRESSMSTUnHitFg = false; 
					break;
				case "KM0110_CALLPGMST":
					KM0110_CALLPGMSTUnHitFg = false; 
					break;
				case "KM0122_CourseGlpMST":
					KM0122_CourseGlpMSTUnHitFg = false;
					break;
				case "KM0123_CourseMST":
					KM0123_CourseMSTUnHitFg = false;
					break;
				case "KM0124_CourseDeliveryMST":
					KM0124_CourseDeliveryMSTUnHitFg = false;
					break;
				case "KM0125_MyDriverMST":
					KM0125_MyDriverMSTUnHitFg = false;
					break;
				case "KM0126_MyDriverList":
					KM0126_MyDriverListUnHitFg = false;
					break;
				case "KT0010_OKURI_HD":
					KT0010_OKURI_HDUnHitFg = false; 
					break;
				case "KT0011_OKURI_MS":
					KT0011_OKURI_MSUnHitFg = false; 
					break;
				case "KT0012_OKURI_PROGRESS":
					KT0012_OKURI_PROGRESSUnHitFg = false; 
					break;
				case "KT0013_SEIKYU":
					KT0013_SEIKYUUnHitFg = false; 
					break;
				case "KT0023_SHIHARAI":
					KT0023_SHIHARAIUnHitFg = false; 
					break;
				case "KT0040_PrintControl":
					KT0040_PrintControlUnHitFg = false;
					break;
				case "KT020_HAISHA_HD":
					KT020_HAISHA_HDUnHitFg = false; 
					break;
				case "KT021_HAISHA_MS":
					KT021_HAISHA_MSUnHitFg = false; 
					break;
				default:
					break;
			}
		}
		
		if(KM0000_PARAMETERUnHitFg) {
			String sql = KM0000_PARAMETERCreateSql();
			KickSql("NANKO",sql);
		}
		
		if(KM0005_MUNICUnHitFg) {
			String sql = KM0005_MUNICCreateSql();
			KickSql("NANKO",sql);
		}
		
		if(KM0010_WHMSTUnHitFg) {
			String sql = KM0010_WHMSTCreateSql();
			KickSql("NANKO",sql);
		}
		
		if(KM0020_USERMSTUnHitFg) {
			String sql = KM0020_USERMSTCreateSql();
			KickSql("NANKO",sql);
		}
		if(KM0030_CLIENTMSTUnHitFg) {
			String sql = KM0030_CLIENTMSTCreateSql();
			KickSql("NANKO",sql);
		}
		if(KM0031_CLIENT_GROUPUnHitFg) {
			String sql = KM0031_CLIENT_GROUPCreateSql();
			KickSql("NANKO",sql);
		}
		if(KM0040_DELIVERYMSTUnHitFg) {
			String sql = KM0040_DELIVERYMSTCreateSql();
			KickSql("NANKO",sql);
		}
		if(KM0041_DELIVERY_COMVERSIONMSTUnHitFg) {
			String sql = KM0041_DELIVERY_COMVERSIONMSTCreateSql();
			KickSql("NANKO",sql);
		}
		if(KM0050_DELIVERY_TYPEMSTUnHitFg) {
			String sql = KM0050_DELIVERY_TYPEMSTCreateSql();
			KickSql("NANKO",sql);
		}
		if(KM0060_ITEMMSTUnHitFg) {
			String sql = KM0060_ITEMMSTCreateSql();
			KickSql("NANKO",sql);
		}
		if(KM0061_ITEMMSTSUBUnHitFg) {
			String sql = KM0061_ITEMMSTSUBCreateSql();
			KickSql("NANKO",sql);
		}
		if(KM0062_ItemComversionMstUnHitFg) {
			String sql = KM0062_ItemComversionMstCreateSql();
			KickSql("NANKO",sql);
		}
		if(KM0070_SHIPPINGCOMPANYMSTUnHitFg) {
			String sql = KM0070_SHIPPINGCOMPANYMSTCreateSql();
			KickSql("NANKO",sql);
		}
		if(KM0071_CARMSTUnHitFg) {
			String sql = KM0071_CARMSTCreateSql();
			KickSql("NANKO",sql);
		}
		if(KM0080_FEEBASEMSTUnHitFg) {
			String sql = KM0080_FEEBASEMSTCreateSql();
			KickSql("NANKO",sql);
		}
		if(KM0081_FEEMSTUnHitFg) {
			String sql = KM0081_FEEMSTCreateSql();
			KickSql("NANKO",sql);
		}
		if(KM0082_FEELOGICTYPEMSTUnHitFg) {
			String sql = KM0082_FEELOGICTYPEMSTCreateSql();
			KickSql("NANKO",sql);
		}
		if(KM0090_CAUTIONUnHitFg) {
			String sql = KM0090_CAUTIONCreateSql();
			KickSql("NANKO",sql);
		}
		if(KM0090_PAYBASEMSTUnHitFg) {
			String sql = KM0090_PAYBASEMSTCreateSql();
			KickSql("NANKO",sql);
		}
		if(KM0091_PAYMSTUnHitFg) {
			String sql = KM0091_PAYMSTCreateSql();
			KickSql("NANKO",sql);
		}
		if(KM0100_OKURIPROGRESSMSTUnHitFg) {
			String sql = KM0100_OKURIPROGRESSMSTCreateSql();
			KickSql("NANKO",sql);
		}
		if(KM0110_CALLPGMSTUnHitFg) {
			String sql = KM0110_CALLPGMSTCreateSql();
			KickSql("NANKO",sql);
		}
		if(KM0122_CourseGlpMSTUnHitFg) {
			String sql = KM0122_CourseGlpMSTCreateSql();
			KickSql("NANKO",sql);
		}
		if(KM0123_CourseMSTUnHitFg) {
			String sql = KM0123_CourseMSTCreateSql();
			KickSql("NANKO",sql);
		}
		if(KM0124_CourseDeliveryMSTUnHitFg) {
			String sql = KM0124_CourseDeliveryMSTCreateSql();
			KickSql("NANKO",sql);
		}
		if(KM0125_MyDriverMSTUnHitFg) {
			String sql = KM0125_MyDriverMSTCreateSql();
			KickSql("NANKO",sql);
		}
		if(KM0126_MyDriverListUnHitFg) {
			String sql = KM0126_MyDriverListCreateSql();
			KickSql("NANKO",sql);
		}
		if(KT0010_OKURI_HDUnHitFg) {
			String sql = KT0010_OKURI_HDCreateSql();
			KickSql("NANKO",sql);
		}
		if(KT0011_OKURI_MSUnHitFg) {
			String sql = KT0011_OKURI_MSCreateSql();
			KickSql("NANKO",sql);
		}
		if(KT0012_OKURI_PROGRESSUnHitFg) {
			String sql = KT0012_OKURI_PROGRESSCreateSql();
			KickSql("NANKO",sql);
		}
		if(KT0013_SEIKYUUnHitFg) {
			String sql = KT0013_SEIKYUCreateSql();
			KickSql("NANKO",sql);
		}
		if(KT0023_SHIHARAIUnHitFg) {
			String sql = KT0023_SHIHARAICreateSql();
			KickSql("NANKO",sql);
		}
		if(KT0040_PrintControlUnHitFg) {
			String sql = KT0040_PrintControlCreateSql();
			KickSql("NANKO",sql);
		}
		if(KT020_HAISHA_HDUnHitFg) {
			String sql = KT020_HAISHA_HDCreateSql();
			KickSql("NANKO",sql);
		}
		if(KT021_HAISHA_MSUnHitFg) {
			String sql = KT021_HAISHA_MSCreateSql();
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

		ColumnList = ColumnList("NANKO","KM0090_PAYBASEMST");
		NeedColmn = new String[54];
		NeedColmn[ 0] = "ShippingCompanyCd";
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
		NeedColmn[52] = "AddDeliFee03UnitFee";
		NeedColmn[53] = "SummaryType";
		
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
			String sql = KM0090_PAYBASEMSTAltherTableSql(NoHitColumn);
			KickSql("NANKO",sql);
		}
		
		ColumnList = ColumnList("NANKO","KM0091_PAYMST");
		NeedColmn = new String[14];
		NeedColmn[ 0] = "WHCD";
		NeedColmn[ 1] = "ShippingCompanyCd";
		NeedColmn[ 2] = "CarCd";
		NeedColmn[ 3] = "DeliveryTypeCd";
		NeedColmn[ 4] = "DeliveryTypeCd02";
		NeedColmn[ 5] = "DeliveryTypeCd03";
		NeedColmn[ 6] = "DeliveryTypeCd04";
		NeedColmn[ 7] = "DeliveryTypeCd05";
		NeedColmn[ 8] = "PatternCD";
		NeedColmn[ 9] = "EntryDate";
		NeedColmn[10] = "UpdateDate";
		NeedColmn[11] = "EntryUser";
		NeedColmn[12] = "UpdateUser";
		NeedColmn[13] = "DelFg";
		
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
			String sql = KM0091_PAYMSTAltherTableSql(NoHitColumn);
			KickSql("NANKO",sql);
		}
		
		ColumnList = ColumnList("NANKO","KM0100_OKURIPROGRESSMST");
		NeedColmn = new String[7];
		NeedColmn[ 0] = "ProgressCd";
		NeedColmn[ 1] = "ProgressName";
		NeedColmn[ 2] = "EntryDate";
		NeedColmn[ 3] = "UpdateDate";
		NeedColmn[ 4] = "EntryUser";
		NeedColmn[ 5] = "UpdateUser";
		NeedColmn[ 6] = "DelFg";
		
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
			String sql = KM0100_OKURIPROGRESSMSTAltherTableSql(NoHitColumn);
			KickSql("NANKO",sql);
		}
		
		ColumnList = ColumnList("NANKO","KM0110_CALLPGMST");
		NeedColmn = new String[8];
		NeedColmn[ 0] = "PGCd";
		NeedColmn[ 1] = "PGName";
		NeedColmn[ 2] = "CallScene";
		NeedColmn[ 3] = "EntryDate";
		NeedColmn[ 4] = "UpdateDate";
		NeedColmn[ 5] = "EntryUser";
		NeedColmn[ 6] = "UpdateUser";
		NeedColmn[ 7] = "DelFg";
		
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
			String sql = KM0110_CALLPGMSTAltherTableSql(NoHitColumn);
			KickSql("NANKO",sql);
		}

		ColumnList = ColumnList("NANKO","KM0122_CourseGlpMST");
		NeedColmn = new String[7];
		NeedColmn[ 0] = "WhCd";
		NeedColmn[ 1] = "CourseGpCd";
		NeedColmn[ 2] = "CourseGpName";
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
			String sql = KM0122_CourseGlpMSTAltherTableSql(NoHitColumn);
			KickSql("NANKO",sql);
		}
	
		ColumnList = ColumnList("NANKO","KM0123_CourseMST");
		NeedColmn = new String[13];
		NeedColmn[ 0] = "WhCd";
		NeedColmn[ 1] = "CourseGpCd";
		NeedColmn[ 2] = "CourseCd";
		NeedColmn[ 3] = "CourseName";
		NeedColmn[ 4] = "DriverWhCd";
		NeedColmn[ 5] = "DriverCompanyCd";
		NeedColmn[ 6] = "DriverCd";
		NeedColmn[ 7] = "SetBinNo";
		NeedColmn[ 8] = "EntryDate";
		NeedColmn[ 9] = "UpdateDate";
		NeedColmn[10] = "EntryUser";
		NeedColmn[11] = "UpdateUser";
		NeedColmn[12] = "OnePitOneDeliveryFg";
		
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
			String sql = KM0123_CourseMSTAltherTableSql(NoHitColumn);
			KickSql("NANKO",sql);
		}
	
		ColumnList = ColumnList("NANKO","KM0124_CourseDeliveryMST");
		NeedColmn = new String[9];
		NeedColmn[ 0] = "WhCd";
		NeedColmn[ 1] = "CourseGpCd";
		NeedColmn[ 2] = "CourseCd";
		NeedColmn[ 3] = "DeliCd";
		NeedColmn[ 4] = "EntryDate";
		NeedColmn[ 5] = "UpdateDate";
		NeedColmn[ 6] = "EntryUser";
		NeedColmn[ 7] = "UpdateUser";
		NeedColmn[ 8] = "CourseOrder";
		
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
			String sql = KM0124_CourseDeliveryMSTAltherTableSql(NoHitColumn);
			KickSql("NANKO",sql);
		}

		ColumnList = ColumnList("NANKO","KM0125_MyDriverMST");
		NeedColmn = new String[9];
		NeedColmn[ 0] = "MyDriverListCd";
		NeedColmn[ 1] = "MyDriverListName";
		NeedColmn[ 2] = "UseWHCD";
		NeedColmn[ 3] = "UseShippingCompanyCd";
		NeedColmn[ 4] = "UseUserCd";
		NeedColmn[ 5] = "EntryDate";
		NeedColmn[ 6] = "UpdateDate";
		NeedColmn[ 7] = "EntryUser";
		NeedColmn[ 8] = "UpdateUser";
		
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
			String sql = KM0125_MyDriverMSTAltherTableSql(NoHitColumn);
			KickSql("NANKO",sql);
		}
	
		ColumnList = ColumnList("NANKO","KM0126_MyDriverList");
		NeedColmn = new String[11];
		NeedColmn[ 0] = "MyDriverListCd";
		NeedColmn[ 1] = "MyDriverCd";
		NeedColmn[ 2] = "DriverWHCD";
		NeedColmn[ 3] = "DriverShippingCompanyCd";
		NeedColmn[ 4] = "DriverUserCd";
		NeedColmn[ 5] = "DriverCallName";
		NeedColmn[ 6] = "SetBinNo";
		NeedColmn[ 7] = "EntryDate";
		NeedColmn[ 8] = "UpdateDate";
		NeedColmn[ 9] = "EntryUser";
		NeedColmn[10] = "UpdateUser";
		
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
			String sql = KM0126_MyDriverListAltherTableSql(NoHitColumn);
			KickSql("NANKO",sql);
		}

		ColumnList = ColumnList("NANKO","KT0010_OKURI_HD");
		NeedColmn = new String[99];
		NeedColmn[ 0] = "cl_cd";
		NeedColmn[ 1] = "InvoiceWHCD";
		NeedColmn[ 2] = "OkuriNo";
		NeedColmn[ 3] = "ClDeliNo";
		NeedColmn[ 4] = "PickupWHCD";
		NeedColmn[ 5] = "PurposeFG";
		NeedColmn[ 6] = "PlanDate";
		NeedColmn[ 7] = "ShipDate";
		NeedColmn[ 8] = "SPPlanDate";
		NeedColmn[ 9] = "SPDate";
		NeedColmn[10] = "SPTimeFG";
		NeedColmn[11] = "SPTimeStr";
		NeedColmn[12] = "SPTimeEnd";
		NeedColmn[13] = "TotalWeight";
		NeedColmn[14] = "TotalSize";
		NeedColmn[15] = "TotalQty";
		NeedColmn[16] = "DeliveryTypeCd";
		NeedColmn[17] = "DeliTypeName";
		NeedColmn[18] = "DeliveryTypeCd02";
		NeedColmn[19] = "DeliTypeName02";
		NeedColmn[20] = "DeliveryTypeCd03";
		NeedColmn[21] = "DeliTypeName03";
		NeedColmn[22] = "DeliveryTypeCd04";
		NeedColmn[23] = "DeliTypeName04";
		NeedColmn[24] = "DeliveryTypeCd05";
		NeedColmn[25] = "DeliTypeName05";
		NeedColmn[26] = "CodFG";
		NeedColmn[27] = "CodPayTotal";
		NeedColmn[28] = "CodPay";
		NeedColmn[29] = "CodConsumptionTax";
		NeedColmn[30] = "ChildrenFG";
		NeedColmn[31] = "ParentOkuriNo";
		NeedColmn[32] = "NiokuriCd";
		NeedColmn[33] = "NiokuriDepartmentCd";
		NeedColmn[34] = "NiokuriName01";
		NeedColmn[35] = "NiokuriName02";
		NeedColmn[36] = "NiokuriName03";
		NeedColmn[37] = "NiokuriPost";
		NeedColmn[38] = "NiokuriAdd01";
		NeedColmn[39] = "NiokuriAdd02";
		NeedColmn[40] = "NiokuriAdd03";
		NeedColmn[41] = "NioKuriTel";
		NeedColmn[42] = "NioKuriFax";
		NeedColmn[43] = "NioKuriMail";
		NeedColmn[44] = "NiokuriMunicCd";
		NeedColmn[45] = "DeliCd";
		NeedColmn[46] = "ClDeliCd";
		NeedColmn[47] = "DeliDepartmentCd";
		NeedColmn[48] = "DeliName01";
		NeedColmn[49] = "DeliName02";
		NeedColmn[50] = "DeliName03";
		NeedColmn[51] = "DeliPost";
		NeedColmn[52] = "DeliAdd01";
		NeedColmn[53] = "DeliAdd02";
		NeedColmn[54] = "DeliAdd03";
		NeedColmn[55] = "DeliTel";
		NeedColmn[56] = "DeliFax";
		NeedColmn[57] = "DeliMail";
		NeedColmn[58] = "DeliMunicCd";
		NeedColmn[59] = "Com01";
		NeedColmn[60] = "Com02";
		NeedColmn[61] = "Com03";
		NeedColmn[62] = "Com04";
		NeedColmn[63] = "Com05";
		NeedColmn[64] = "Status";
		NeedColmn[65] = "TaxFg";
		NeedColmn[66] = "TaxRate";
		NeedColmn[67] = "DeliFee";
		NeedColmn[68] = "AddDeliFee01";
		NeedColmn[69] = "AddDeliFee02";
		NeedColmn[70] = "AddDeliFee03";
		NeedColmn[71] = "HaighWayFee01";
		NeedColmn[72] = "HaighWayFee02";
		NeedColmn[73] = "ConsumptionTax";
		NeedColmn[74] = "WithOutTaxTotal";
		NeedColmn[75] = "TotalFee";
		NeedColmn[76] = "FeeFixFG";
		NeedColmn[77] = "FeeFixDate";
		NeedColmn[78] = "ReceiptStampFG";
		NeedColmn[79] = "ReceiptStampDate";
		NeedColmn[80] = "InvoiceStatus";
		NeedColmn[81] = "EntryDate";
		NeedColmn[82] = "UpdateDate";
		NeedColmn[83] = "EntryUser";
		NeedColmn[84] = "UpdateUser";
		NeedColmn[85] = "EntryPG";
		NeedColmn[86] = "UpdatePG";
		NeedColmn[87] = "UseFeeBasePtCd";
		NeedColmn[88] = "WmsStatus";
		NeedColmn[89] = "WmsShipDate";
		NeedColmn[90] = "CourseGpCd";
		NeedColmn[91] = "CourseCD";
		NeedColmn[92] = "CourseCDEda";
		NeedColmn[93] = "PitGrp";
		NeedColmn[94] = "Pit01";
		NeedColmn[95] = "Pit02";
		NeedColmn[96] = "Pit03";
		NeedColmn[97] = "Pit04";
		NeedColmn[98] = "Pit05";
		
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
			String sql = KT0010_OKURI_HDAltherTableSql(NoHitColumn);
			KickSql("NANKO",sql);
		}

		ColumnList = ColumnList("NANKO","KT0011_OKURI_MS");
		NeedColmn = new String[40];
		NeedColmn[ 0] = "cl_cd";
		NeedColmn[ 1] = "InvoiceWHCD";
		NeedColmn[ 2] = "OkuriNo";
		NeedColmn[ 3] = "MsNo";
		NeedColmn[ 4] = "DeliNo";
		NeedColmn[ 5] = "DelliMsNo";
		NeedColmn[ 6] = "ClOrderNo";
		NeedColmn[ 7] = "ClGpCd";
		NeedColmn[ 8] = "ItemCd";
		NeedColmn[ 9] = "ItemName01";
		NeedColmn[10] = "ItemName02";
		NeedColmn[11] = "ItemName03";
		NeedColmn[12] = "UnitWeight";
		NeedColmn[13] = "UnitSize";
		NeedColmn[14] = "Qty";
		NeedColmn[15] = "PackingQty";
		NeedColmn[16] = "UnitName";
		NeedColmn[17] = "SubTotalWeight";
		NeedColmn[18] = "SubTotalSize";
		NeedColmn[19] = "UnitPrice";
		NeedColmn[20] = "SubTotalPrice";
		NeedColmn[21] = "CategoryCd";
		NeedColmn[22] = "CategoryName";
		NeedColmn[23] = "TildFG";
		NeedColmn[24] = "TildName";
		NeedColmn[25] = "Com01";
		NeedColmn[26] = "Com02";
		NeedColmn[27] = "Com03";
		NeedColmn[28] = "Com04";
		NeedColmn[29] = "Com05";
		NeedColmn[30] = "EntryDate";
		NeedColmn[31] = "UpdateDate";
		NeedColmn[32] = "EntryUser";
		NeedColmn[33] = "UpdateUser";
		NeedColmn[34] = "Lot";
		NeedColmn[35] = "ExpDate";
		NeedColmn[36] = "PackingType";
		NeedColmn[37] = "ClItemCd";
		NeedColmn[38] = "ItemMDNo";
		NeedColmn[39] = "JanCd";
		
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
			String sql = KT0011_OKURI_MSAltherTableSql(NoHitColumn);
			KickSql("NANKO",sql);
		}

		ColumnList = ColumnList("NANKO","KT0012_OKURI_PROGRESS");
		NeedColmn = new String[15];
		NeedColmn[ 0] = "cl_cd";
		NeedColmn[ 1] = "InvoiceWHCD";
		NeedColmn[ 2] = "OkuriNo";
		NeedColmn[ 3] = "ProgressNo";
		NeedColmn[ 4] = "ProgressCd";
		NeedColmn[ 5] = "ProgressName";
		NeedColmn[ 6] = "Com01";
		NeedColmn[ 7] = "Com02";
		NeedColmn[ 8] = "Com03";
		NeedColmn[ 9] = "Com04";
		NeedColmn[10] = "Com05";
		NeedColmn[11] = "EntryDate";
		NeedColmn[12] = "UpdateDate";
		NeedColmn[13] = "EntryUser";
		NeedColmn[14] = "UpdateUser";
		
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
			String sql = KT0012_OKURI_PROGRESSAltherTableSql(NoHitColumn);
			KickSql("NANKO",sql);
		}

		ColumnList = ColumnList("NANKO","KT0013_SEIKYU");
		NeedColmn = new String[28];
		NeedColmn[ 0] = "cl_cd";
		NeedColmn[ 1] = "InvoiceWHCD";
		NeedColmn[ 2] = "SeikyuNo";
		NeedColmn[ 3] = "OkuriNo";
		NeedColmn[ 4] = "SeikyuDate";
		NeedColmn[ 5] = "ShimeDate";
		NeedColmn[ 6] = "TaxFg";
		NeedColmn[ 7] = "TaxRate";
		NeedColmn[ 8] = "DeliFee";
		NeedColmn[ 9] = "AddDeliFee01";
		NeedColmn[10] = "AddDeliFee02";
		NeedColmn[11] = "AddDeliFee03";
		NeedColmn[12] = "HaighWayFee01";
		NeedColmn[13] = "HaighWayFee02";
		NeedColmn[14] = "ConsumptionTax";
		NeedColmn[15] = "WithOutTaxTotal";
		NeedColmn[16] = "TotalFee";
		NeedColmn[17] = "Com01";
		NeedColmn[18] = "Com02";
		NeedColmn[19] = "Com03";
		NeedColmn[20] = "Com04";
		NeedColmn[21] = "Com05";
		NeedColmn[22] = "EntryDate";
		NeedColmn[23] = "UpdateDate";
		NeedColmn[24] = "EntryUser";
		NeedColmn[25] = "UpdateUser";
		NeedColmn[26] = "PrtFg";
		NeedColmn[27] = "DataOutFg";
		
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
			String sql = KT0013_SEIKYUAltherTableSql(NoHitColumn);
			KickSql("NANKO",sql);
		}

		ColumnList = ColumnList("NANKO","KT0023_SHIHARAI");
		NeedColmn = new String[21];
		NeedColmn[ 0] = "DeliWHCD";
		NeedColmn[ 1] = "DeliCompCd";
		NeedColmn[ 2] = "ShiharaiNo";
		NeedColmn[ 3] = "HaishaNo";
		NeedColmn[ 4] = "ShiharaiDate";
		NeedColmn[ 5] = "ShimeDate";
		NeedColmn[ 6] = "TaxFg";
		NeedColmn[ 7] = "TaxRate";
		NeedColmn[ 8] = "DeliPay";
		NeedColmn[ 9] = "AddDeliPay01";
		NeedColmn[10] = "AddDeliPay02";
		NeedColmn[11] = "AddDeliPay03";
		NeedColmn[12] = "HaighWayPay01";
		NeedColmn[13] = "HaighWayPay02";
		NeedColmn[14] = "ConsumptionTax";
		NeedColmn[15] = "WithOutTaxTotal";
		NeedColmn[16] = "TotalPay";
		NeedColmn[17] = "EntryDate";
		NeedColmn[18] = "UpdateDate";
		NeedColmn[19] = "EntryUser";
		NeedColmn[20] = "UpdateUser";
		
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
			String sql = KT0023_SHIHARAIAltherTableSql(NoHitColumn);
			KickSql("NANKO",sql);
		}
	
		ColumnList = ColumnList("NANKO","KT0040_PrintControl");
		NeedColmn = new String[10];
		NeedColmn[ 0] = "PrintCd";
		NeedColmn[ 1] = "OkuriNo";
		NeedColmn[ 2] = "Key01";
		NeedColmn[ 3] = "Key02";
		NeedColmn[ 4] = "Key03";
		NeedColmn[ 5] = "Key04";
		NeedColmn[ 6] = "EntryDate";
		NeedColmn[ 7] = "UpdateDate";
		NeedColmn[ 8] = "EntryUser";
		NeedColmn[ 9] = "UpdateUser";
		
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
			String sql = KT0040_PrintControlAltherTableSql(NoHitColumn);
			KickSql("NANKO",sql);
		}

		ColumnList = ColumnList("NANKO","KT020_HAISHA_HD");
		NeedColmn = new String[47];
		NeedColmn[ 0] = "DeliWHCD";
		NeedColmn[ 1] = "HaishaNo";
		NeedColmn[ 2] = "PlanDate";
		NeedColmn[ 3] = "ShipDate";
		NeedColmn[ 4] = "SPPlanDate";
		NeedColmn[ 5] = "SPDate";
		NeedColmn[ 6] = "TotalWeight";
		NeedColmn[ 7] = "TotalSize";
		NeedColmn[ 8] = "TotalQty";
		NeedColmn[ 9] = "DeliveryTypeCd";
		NeedColmn[10] = "DeliTypeName";
		NeedColmn[11] = "DeliveryTypeCd02";
		NeedColmn[12] = "DeliTypeName02";
		NeedColmn[13] = "DeliveryTypeCd03";
		NeedColmn[14] = "DeliTypeName03";
		NeedColmn[15] = "DeliveryTypeCd04";
		NeedColmn[16] = "DeliTypeName04";
		NeedColmn[17] = "DeliveryTypeCd05";
		NeedColmn[18] = "DeliTypeName05";
		NeedColmn[19] = "DeliCompCd";
		NeedColmn[20] = "DeliCompName";
		NeedColmn[21] = "CarCd";
		NeedColmn[22] = "CarName";
		NeedColmn[23] = "DriverCd";
		NeedColmn[24] = "DriverName";
		NeedColmn[25] = "Status";
		NeedColmn[26] = "TaxFg";
		NeedColmn[27] = "TaxRate";
		NeedColmn[28] = "DeliPay";
		NeedColmn[29] = "AddDeliPay01";
		NeedColmn[30] = "AddDeliPay02";
		NeedColmn[31] = "AddDeliPay03";
		NeedColmn[32] = "HaighWayPay01";
		NeedColmn[33] = "HaighWayPay02";
		NeedColmn[34] = "ConsumptionTax";
		NeedColmn[35] = "WithOutTaxTotal";
		NeedColmn[36] = "TotalPay";
		NeedColmn[37] = "PayFixFG";
		NeedColmn[38] = "PayFixDate";
		NeedColmn[39] = "InvoiceStatus";
		NeedColmn[40] = "ChildrenFG";
		NeedColmn[41] = "ParentHaishaNo";
		NeedColmn[42] = "EntryDate";
		NeedColmn[43] = "UpdateDate";
		NeedColmn[44] = "EntryUser";
		NeedColmn[45] = "UpdateUser";
		NeedColmn[46] = "UsePayBasePtCd";
		
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
			String sql = KT020_HAISHA_HDAltherTableSql(NoHitColumn);
			KickSql("NANKO",sql);
		}

		ColumnList = ColumnList("NANKO","KT021_HAISHA_MS");
		NeedColmn = new String[22];
		NeedColmn[ 0] = "DeliWHCD";
		NeedColmn[ 1] = "HaishaNo";
		NeedColmn[ 2] = "BinNo";
		NeedColmn[ 3] = "MsNo";
		NeedColmn[ 4] = "InvoiceWHCD";
		NeedColmn[ 5] = "cl_cd";
		NeedColmn[ 6] = "OkuriNo";
		NeedColmn[ 7] = "Status";
		NeedColmn[ 8] = "FG01";
		NeedColmn[ 9] = "FG02";
		NeedColmn[10] = "FG03";
		NeedColmn[11] = "FG04";
		NeedColmn[12] = "FG05";
		NeedColmn[13] = "Com01";
		NeedColmn[14] = "Com02";
		NeedColmn[15] = "Com03";
		NeedColmn[16] = "Com04";
		NeedColmn[17] = "Com05";
		NeedColmn[18] = "EntryDate";
		NeedColmn[19] = "UpdateDate";
		NeedColmn[20] = "EntryUser";
		NeedColmn[21] = "UpdateUser";
		
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
			String sql = KT021_HAISHA_MSAltherTableSql(NoHitColumn);
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
		sql = sql + ";";
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
		sql = sql + ";";
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
		sql = sql + ";";
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
		sql = sql + ";";
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
		sql = sql + ";";
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
		sql = sql + ";";
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
		sql = sql + ";";
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
		sql = sql + ";";
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
		sql = sql + ";";
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
		sql = sql + ";";
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
		sql = sql + ";";
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
		sql = sql + ";";
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
		sql = sql + ";";
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
		sql = sql + ";";
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
		sql = sql + ";";
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
		sql = sql + ";";
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
		sql = sql + ";";
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
		sql = sql + ";";
		return sql;
	}
	private static String KM0090_PAYBASEMSTCreateSql() {
		//下払運賃基本タリフマスタを作るNYANKO
		String sql = ""
				+"CREATE TABLE `KM0090_PAYBASEMST` ("
				+"  `ShippingCompanyCd` varchar(20) NOT NULL,"
				+"  `DeliveryTypeCd` varchar(20) NOT NULL,"
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
				+"  `DelFg` int(11) DEFAULT '0',"
				+"  `AddDeliFee03UnitFee` float DEFAULT '0',"
				+"  `SummaryType` int(11) DEFAULT '0',"
				+"  PRIMARY KEY (`ShippingCompanyCd`,`DeliveryTypeCd`,`DeliveryTypeCd02`,`DeliveryTypeCd03`,`DeliveryTypeCd04`,`DeliveryTypeCd05`,`PatternCD`)"
				+") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='下払運賃基本タリフマスタ';";
		return sql;
	}

	private static String KM0090_PAYBASEMSTAltherTableSql(ArrayList<String> NoHitColumn){
		String sql = ""
				+"ALTER TABLE "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0090_PAYBASEMST";
		for(int i=0;i<NoHitColumn.size();i++) {
			if(0<i) {sql = sql + ",";}
			switch(NoHitColumn.get(i)) {
				case "ShippingCompanyCd":
					sql = sql + " ADD ShippingCompanyCd varchar(20) NOT NULL";
					break;
				case "DeliveryTypeCd":
					sql = sql + " ADD DeliveryTypeCd varchar(20) NOT NULL";
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
					sql = sql + " ADD DelFg int(11) DEFAULT '0'";
					break;
				case "AddDeliFee03UnitFee":
					sql = sql + " ADD AddDeliFee03UnitFee float DEFAULT '0'";
					break;
				case "SummaryType":
					sql = sql + " ADD SummaryType int(11) DEFAULT '0'";
					break;
				default:
					break;
			}
		}
		sql = sql + ";";
		return sql;
	}


	private static String KM0091_PAYMSTCreateSql() {
		//下払タリフマスタを作るNYANKO
		String sql = ""
				+"CREATE TABLE `KM0091_PAYMST` ("
				+"  `WHCD` varchar(20) NOT NULL,"
				+"  `ShippingCompanyCd` varchar(20) NOT NULL,"
				+"  `CarCd` varchar(20) NOT NULL,"
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
				+"  `DelFg` int(11) DEFAULT '0',"
				+"  PRIMARY KEY (`WHCD`,`ShippingCompanyCd`,`CarCd`,`DeliveryTypeCd`,`DeliveryTypeCd02`,`DeliveryTypeCd03`,`DeliveryTypeCd04`,`DeliveryTypeCd05`)"
				+") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='下払タリフマスタ';";
		return sql;
	}

	private static String KM0091_PAYMSTAltherTableSql(ArrayList<String> NoHitColumn){
		String sql = ""
				+"ALTER TABLE "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0091_PAYMST";
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
				case "DelFg":
					sql = sql + " ADD DelFg int(11) DEFAULT '0'";
					break;
				default:
					break;
			}
		}
		sql = sql + ";";
		return sql;
	}

	
	private static String KM0100_OKURIPROGRESSMSTCreateSql() {
		//進捗状況マスタを作るNYANKO
		String sql = ""
				+"CREATE TABLE `KM0100_OKURIPROGRESSMST` ("
				+"  `ProgressCd` varchar(20) NOT NULL,"
				+"  `ProgressName` varchar(100) NOT NULL,"
				+"  `EntryDate` datetime DEFAULT NULL,"
				+"  `UpdateDate` datetime DEFAULT NULL,"
				+"  `EntryUser` varchar(50) DEFAULT NULL,"
				+"  `UpdateUser` varchar(50) DEFAULT NULL,"
				+"  `DelFg` int(11) DEFAULT '0',"
				+"  PRIMARY KEY (`ProgressCd`)"
				+") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='進捗状況マスタ';";
		return sql;
	}

	private static String KM0100_OKURIPROGRESSMSTAltherTableSql(ArrayList<String> NoHitColumn){
		String sql = ""
				+"ALTER TABLE "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0100_OKURIPROGRESSMST";
		for(int i=0;i<NoHitColumn.size();i++) {
			if(0<i) {sql = sql + ",";}
			switch(NoHitColumn.get(i)) {
				case "ProgressCd":
					sql = sql + " ADD ProgressCd varchar(20) NOT NULL";
					break;
				case "ProgressName":
					sql = sql + " ADD ProgressName varchar(100) NOT NULL";
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
		sql = sql + ";";
		return sql;
	}

	
	private static String KM0110_CALLPGMSTCreateSql() {
		//呼び出しプログラムマスタを作るNYANKO
		String sql = ""
				+"CREATE TABLE `KM0110_CALLPGMST` ("
				+"  `PGCd` varchar(20) NOT NULL,"
				+"  `PGName` varchar(100) NOT NULL,"
				+"  `CallScene` int(11) NOT NULL DEFAULT '0',"
				+"  `EntryDate` datetime DEFAULT NULL,"
				+"  `UpdateDate` datetime DEFAULT NULL,"
				+"  `EntryUser` varchar(50) DEFAULT NULL,"
				+"  `UpdateUser` varchar(50) DEFAULT NULL,"
				+"  `DelFg` int(11) DEFAULT '0',"
				+"  PRIMARY KEY (`PGCd`)"
				+") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='呼び出しプログラムマスタ';";
		return sql;
	}

	private static String KM0110_CALLPGMSTAltherTableSql(ArrayList<String> NoHitColumn){
		String sql = ""
				+"ALTER TABLE "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0110_CALLPGMST";
		for(int i=0;i<NoHitColumn.size();i++) {
			if(0<i) {sql = sql + ",";}
			switch(NoHitColumn.get(i)) {
				case "PGCd":
					sql = sql + " ADD PGCd varchar(20) NOT NULL";
					break;
				case "PGName":
					sql = sql + " ADD PGName varchar(100) NOT NULL";
					break;
				case "CallScene":
					sql = sql + " ADD CallScene int(11) NOT NULL DEFAULT '0'";
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
		sql = sql + ";";
		return sql;
	}
	private static String KM0122_CourseGlpMSTCreateSql() {
		//一次配車配送コースグループマスタを作るNYANKO
		String sql = ""
			+"CREATE TABLE `KM0122_CourseGlpMST` ("
		  	+"  `WhCd` varchar(20) NOT NULL COMMENT '出発倉庫コード',"
		  	+"  `CourseGpCd` varchar(20) NOT NULL COMMENT 'コースグループコード',"
		  	+"  `CourseGpName` varchar(100) DEFAULT NULL DEFAULT '' COMMENT 'コースグループ名',"
		  	+"  `EntryDate` datetime DEFAULT NULL COMMENT '登録日',"
		  	+"  `UpdateDate` datetime DEFAULT NULL COMMENT '更新日',"
		  	+"  `EntryUser` varchar(50) DEFAULT NULL COMMENT '登録者',"
		  	+"  `UpdateUser` varchar(50) DEFAULT NULL COMMENT '更新者',"
		  	+"  PRIMARY KEY (`WhCd`,`CourseGpCd`)"
			+") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='配送コースグループマスタ';";

			return sql;
	}

	private static String KM0122_CourseGlpMSTAltherTableSql(ArrayList<String> NoHitColumn){
		String sql = ""
				+"ALTER TABLE "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0122_CourseGlpMST";
		for(int i=0;i<NoHitColumn.size();i++) {
			if(0<i) {sql = sql + ",";}
			switch(NoHitColumn.get(i)) {
				case "WhCd":
					sql = sql + " ADD WhCd varchar(20) NOT NULL";
					break;
			  	case "CourseGpCd":
					sql = sql + " ADD CourseGpCd varchar(20) NOT NULL";
					break;
			  	case "CourseGpName":
					sql = sql + " ADD CourseGpName varchar(100) DEFAULT NULL DEFAULT ''";
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
		sql = sql + ";";
		return sql;
	}
	
	
	private static String KM0123_CourseMSTCreateSql() {
		//配送コースマスタを作るNYANKO
		String sql = ""
				+"CREATE TABLE `KM0123_CourseMST` ("
			  	+"  `WhCd` varchar(20) NOT NULL COMMENT '出発倉庫コード',"
			  	+"  `CourseGpCd` varchar(20) NOT NULL COMMENT 'コースグループコード',"
			  	+"  `CourseCd` varchar(20) NOT NULL COMMENT 'コースCd',"
			  	+"  `CourseName` varchar(100) DEFAULT NULL COMMENT 'コース名',"
			  	+"  `DriverWhCd` varchar(20) DEFAULT NULL COMMENT '倉庫コード',"
			  	+"  `DriverCompanyCd` varchar(20) DEFAULT NULL COMMENT '運送会社CD',"
			  	+"  `DriverCd` varchar(20) DEFAULT NULL COMMENT '乗務員CD',"
			  	+"  `SetBinNo` int(11) DEFAULT '1' COMMENT '二次配車時にセットする便NO',"
			  	+"  `EntryDate` datetime DEFAULT NULL COMMENT '登録日',"
			  	+"  `UpdateDate` datetime DEFAULT NULL COMMENT '更新日',"
			  	+"  `EntryUser` varchar(50) DEFAULT NULL COMMENT '登録者',"
			  	+"  `UpdateUser` varchar(50) DEFAULT NULL COMMENT '更新者',"
			  	+"  `OnePitOneDeliveryFg` int(11) DEFAULT '0' COMMENT 'ピット割付フラグ',"
			  	+"  PRIMARY KEY (`WhCd`,`CourseGpCd`,`CourseCd`)"
				+"  ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='配送コースマスタ';";
		return sql;
	}

	private static String KM0123_CourseMSTAltherTableSql(ArrayList<String> NoHitColumn){
		String sql = ""
				+"ALTER TABLE "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0123_CourseMST";
		for(int i=0;i<NoHitColumn.size();i++) {
			if(0<i) {sql = sql + ",";}
			switch(NoHitColumn.get(i)) {
			  	case "WhCd":
					sql = sql + " ADD WhCd varchar(20) NOT NULL";
					break;
			  	case "CourseGpCd":
					sql = sql + " ADD CourseGpCd varchar(20) NOT NULL";
					break;
			  	case "CourseCd":
					sql = sql + " ADD CourseCd varchar(20) NOT NULL";
					break;
			  	case "CourseName":
					sql = sql + " ADD CourseName varchar(100) DEFAULT NULL";
					break;
			  	case "DriverWhCd":
					sql = sql + " ADD DriverWhCd varchar(20) DEFAULT NULL";
					break;
			  	case "DriverCompanyCd":
					sql = sql + " ADD DriverCompanyCd varchar(20) DEFAULT NULL";
					break;
			  	case "DriverCd":
					sql = sql + " ADD DriverCd varchar(20) DEFAULT NULL";
					break;
			  	case "SetBinNo":
					sql = sql + " ADD SetBinNo int(11) DEFAULT '1'";
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
			  	case "OnePitOneDeliveryFg":
					sql = sql + " ADD OnePitOneDeliveryFg int(11) DEFAULT '0'";
					break;
				default:
					break;
			}
		}
		sql = sql + ";";
		return sql;
	}


	private static String KM0124_CourseDeliveryMSTCreateSql() {
		//配送コース届先内訳マスタを作るNYANKO
		String sql = ""
				+"CREATE TABLE `KM0124_CourseDeliveryMST` ("
				+"  `WhCd` varchar(20) NOT NULL COMMENT '出発倉庫コード',"
				+"  `CourseGpCd` varchar(20) NOT NULL COMMENT 'コースグループコード',"
				+"  `CourseCd` varchar(20) DEFAULT NULL COMMENT 'コースCd',"
				+"  `DeliCd` varchar(20) NOT NULL COMMENT '届先Cd',"
				+"  `EntryDate` datetime DEFAULT NULL COMMENT '登録日',"
				+"  `UpdateDate` datetime DEFAULT NULL COMMENT '更新日',"
				+"  `EntryUser` varchar(50) DEFAULT NULL COMMENT '登録者',"
				+"  `UpdateUser` varchar(50) DEFAULT NULL COMMENT '更新者',"
				+"  `CourseOrder` int(11) NOT NULL DEFAULT '0' COMMENT 'コース配送順序',"
				+"  PRIMARY KEY (`WhCd`,`CourseGpCd`,`DeliCd`)"
				+") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='配送コース届先内訳マスタ';";
		return sql;
	}

	private static String KM0124_CourseDeliveryMSTAltherTableSql(ArrayList<String> NoHitColumn){
		String sql = ""
				+"ALTER TABLE "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0124_CourseDeliveryMST";
		for(int i=0;i<NoHitColumn.size();i++) {
			if(0<i) {sql = sql + ",";}
			switch(NoHitColumn.get(i)) {
				case "WhCd":
					sql = sql + " ADD WhCd varchar(20) NOT NULL";
					break;
				case "CourseGpCd":
					sql = sql + " ADD CourseGpCd varchar(20) NOT NULL";
					break;
				case "CourseCd":
					sql = sql + " ADD CourseCd varchar(20) DEFAULT NULL";
					break;
				case "DeliCd":
					sql = sql + " ADD DeliCd varchar(20) NOT NULL";
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
				case "CourseOrder":
					sql = sql + " ADD CourseOrder int(11) NOT NULL DEFAULT '0'";
					break;
				default:
					break;
			}
		}
		sql = sql + ";";
		return sql;
	}


	private static String KM0125_MyDriverMSTCreateSql(){
		//俺の乗務員リストマスタを作るNYANKO
		String sql = ""
				+"CREATE TABLE `KM0125_MyDriverMST` ("
				+"  `MyDriverListCd` varchar(20) NOT NULL DEFAULT '' COMMENT '乗務員リストコード',"
				+"  `MyDriverListName` varchar(100) NOT NULL DEFAULT '' COMMENT '乗務員リスト名',"
				+"  `UseWHCD` varchar(20) DEFAULT NULL COMMENT '利用ユーザー所属倉庫コード',"
				+"  `UseShippingCompanyCd` varchar(20) DEFAULT NULL COMMENT '利用ユーザー所属運送会社CD',"
				+"  `UseUserCd` varchar(20) DEFAULT NULL COMMENT '利用ユーザーCD',"
				+"  `EntryDate` datetime DEFAULT NULL COMMENT '登録日',"
				+"  `UpdateDate` datetime DEFAULT NULL COMMENT '更新日',"
				+"  `EntryUser` varchar(50) DEFAULT NULL COMMENT '登録者',"
				+"  `UpdateUser` varchar(50) DEFAULT NULL COMMENT '更新者',"
				+"  PRIMARY KEY (`MyDriverListCd`)"
				+") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='俺の乗務員リストマスタ';"
				;
		return sql;
	}

	private static String KM0125_MyDriverMSTAltherTableSql(ArrayList<String> NoHitColumn){
		String sql = ""
				+"ALTER TABLE "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0125_MyDriverMST";
		for(int i=0;i<NoHitColumn.size();i++) {
			if(0<i) {sql = sql + ",";}
			switch(NoHitColumn.get(i)) {
				case "MyDriverListCd":
					sql = sql + " ADD MyDriverListCd varchar(20) NOT NULL";
					break;
				case "MyDriverListName":
					sql = sql + " ADD MyDriverListName varchar(100) NOT NULL";
					break;
				case "UseWHCD":
					sql = sql + " ADD UseWHCD varchar(20) DEFAULT NULL";
					break;
				case "UseShippingCompanyCd":
					sql = sql + " ADD UseShippingCompanyCd varchar(20) DEFAULT NULL";
					break;
				case "UseUserCd":
					sql = sql + " ADD UseUserCd varchar(20) DEFAULT NULL";
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
		sql = sql + ";";
		return sql;
	}


	private static String KM0126_MyDriverListCreateSql(){
		//俺の乗務員リストを作るNYANKO
		String sql = ""
				+"CREATE TABLE `KM0126_MyDriverList` ("
				+"  `MyDriverListCd` varchar(20) NOT NULL DEFAULT '' COMMENT '乗務員リストコード',"
				+"  `MyDriverCd` varchar(20) NOT NULL DEFAULT '' COMMENT '乗務員呼出しコード',"
				+"  `DriverWHCD` varchar(20) DEFAULT NULL COMMENT '乗務員所属倉庫コード',"
				+"  `DriverShippingCompanyCd` varchar(20) DEFAULT NULL COMMENT '乗務員所属運送会社CD',"
				+"  `DriverUserCd` varchar(20) DEFAULT NULL COMMENT '乗務員ユーザーCD',"
				+"  `DriverCallName` varchar(100) DEFAULT NULL COMMENT '乗務員ユーザー呼称',"
				+"  `SetBinNo` int(11) DEFAULT '1' COMMENT '二次配車時にセットする便NO',"
				+"  `EntryDate` datetime DEFAULT NULL COMMENT '登録日',"
				+"  `UpdateDate` datetime DEFAULT NULL COMMENT '更新日',"
				+"  `EntryUser` varchar(50) DEFAULT NULL COMMENT '登録者',"
				+"  `UpdateUser` varchar(50) DEFAULT NULL COMMENT '更新者',"
				+"  PRIMARY KEY (`MyDriverListCd`,`MyDriverCd`)"
				+") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='俺の乗務員リスト';"
				;
		return sql;
	}

	private static String KM0126_MyDriverListAltherTableSql(ArrayList<String> NoHitColumn){
		String sql = ""
				+"ALTER TABLE "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0126_MyDriverList";
		for(int i=0;i<NoHitColumn.size();i++) {
			if(0<i) {sql = sql + ",";}
			switch(NoHitColumn.get(i)) {
				case "MyDriverListCd":
					sql = sql + " ADD MyDriverListCd varchar(20) NOT NULL DEFAULT ''";
					break;
				case "MyDriverCd":
					sql = sql + " ADD MyDriverCd varchar(20) NOT NULL DEFAULT ''";
					break;
				case "DriverWHCD":
					sql = sql + " ADD DriverWHCD varchar(20) DEFAULT NULL";
					break;
				case "DriverShippingCompanyCd":
					sql = sql + " ADD DriverShippingCompanyCd varchar(20) DEFAULT NULL";
					break;
				case "DriverUserCd":
					sql = sql + " ADD DriverUserCd varchar(20) DEFAULT NULL";
					break;
				case "DriverCallName":
					sql = sql + " ADD DriverCallName varchar(100) DEFAULT NULL";
					break;
				case "SetBinNo":
					sql = sql + " ADD SetBinNo int(11) DEFAULT '1'";
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
		sql = sql + ";";
		return sql;
	}
	
	private static String KT0010_OKURI_HDCreateSql() {
		//送り状ヘッダテーブルを作るNYANKO
		String sql = ""
				+"CREATE TABLE `KT0010_OKURI_HD` ("
				+"  `cl_cd` varchar(20) NOT NULL,"
				+"  `InvoiceWHCD` varchar(20) NOT NULL,"
				+"  `OkuriNo` int(11) NOT NULL,"
				+"  `ClDeliNo` varchar(50) DEFAULT NULL,"
				+"  `PickupWHCD` varchar(20) DEFAULT NULL,"
				+"  `PurposeFG` int(11) DEFAULT '0',"
				+"  `PlanDate` datetime DEFAULT NULL,"
				+"  `ShipDate` datetime DEFAULT NULL,"
				+"  `SPPlanDate` datetime DEFAULT NULL,"
				+"  `SPDate` datetime DEFAULT NULL,"
				+"  `SPTimeFG` varchar(20) DEFAULT NULL,"
				+"  `SPTimeStr` varchar(20) DEFAULT NULL,"
				+"  `SPTimeEnd` varchar(20) DEFAULT NULL,"
				+"  `TotalWeight` float DEFAULT '0',"
				+"  `TotalSize` float DEFAULT '0',"
				+"  `TotalQty` float DEFAULT '0',"
				+"  `DeliveryTypeCd` varchar(20) DEFAULT NULL,"
				+"  `DeliTypeName` varchar(50) DEFAULT NULL,"
				+"  `DeliveryTypeCd02` varchar(20) DEFAULT NULL,"
				+"  `DeliTypeName02` varchar(50) DEFAULT NULL,"
				+"  `DeliveryTypeCd03` varchar(20) DEFAULT NULL,"
				+"  `DeliTypeName03` varchar(50) DEFAULT NULL,"
				+"  `DeliveryTypeCd04` varchar(20) DEFAULT NULL,"
				+"  `DeliTypeName04` varchar(50) DEFAULT NULL,"
				+"  `DeliveryTypeCd05` varchar(20) DEFAULT NULL,"
				+"  `DeliTypeName05` varchar(50) DEFAULT NULL,"
				+"  `CodFG` int(11) DEFAULT '0',"
				+"  `CodPayTotal` int(11) DEFAULT '0',"
				+"  `CodPay` int(11) DEFAULT '0',"
				+"  `CodConsumptionTax` int(11) DEFAULT '0',"
				+"  `ChildrenFG` int(11) DEFAULT '0',"
				+"  `ParentOkuriNo` int(11) DEFAULT '0',"
				+"  `NiokuriCd` varchar(20) DEFAULT NULL,"
				+"  `NiokuriDepartmentCd` varchar(20) DEFAULT NULL,"
				+"  `NiokuriName01` varchar(50) DEFAULT NULL,"
				+"  `NiokuriName02` varchar(50) DEFAULT NULL,"
				+"  `NiokuriName03` varchar(50) DEFAULT NULL,"
				+"  `NiokuriPost` varchar(20) DEFAULT NULL,"
				+"  `NiokuriAdd01` varchar(100) DEFAULT NULL,"
				+"  `NiokuriAdd02` varchar(100) DEFAULT NULL,"
				+"  `NiokuriAdd03` varchar(100) DEFAULT NULL,"
				+"  `NioKuriTel` varchar(20) DEFAULT NULL,"
				+"  `NioKuriFax` varchar(20) DEFAULT NULL,"
				+"  `NioKuriMail` varchar(200) DEFAULT NULL,"
				+"  `NiokuriMunicCd` varchar(20) DEFAULT NULL,"
				+"  `DeliCd` varchar(20) DEFAULT NULL,"
				+"  `ClDeliCd` varchar(20) DEFAULT NULL,"
				+"  `DeliDepartmentCd` varchar(20) DEFAULT NULL,"
				+"  `DeliName01` varchar(50) DEFAULT NULL,"
				+"  `DeliName02` varchar(50) DEFAULT NULL,"
				+"  `DeliName03` varchar(50) DEFAULT NULL,"
				+"  `DeliPost` varchar(20) DEFAULT NULL,"
				+"  `DeliAdd01` varchar(100) DEFAULT NULL,"
				+"  `DeliAdd02` varchar(100) DEFAULT NULL,"
				+"  `DeliAdd03` varchar(100) DEFAULT NULL,"
				+"  `DeliTel` varchar(20) DEFAULT NULL,"
				+"  `DeliFax` varchar(20) DEFAULT NULL,"
				+"  `DeliMail` varchar(200) DEFAULT NULL,"
				+"  `DeliMunicCd` varchar(20) DEFAULT NULL,"
				+"  `Com01` varchar(300) DEFAULT NULL,"
				+"  `Com02` varchar(300) DEFAULT NULL,"
				+"  `Com03` varchar(300) DEFAULT NULL,"
				+"  `Com04` varchar(300) DEFAULT NULL,"
				+"  `Com05` varchar(300) DEFAULT NULL,"
				+"  `Status` int(11) DEFAULT '0',"
				+"  `TaxFg` int(11) DEFAULT '0',"
				+"  `TaxRate` int(11) DEFAULT '0',"
				+"  `DeliFee` int(11) DEFAULT '0',"
				+"  `AddDeliFee01` int(11) DEFAULT '0',"
				+"  `AddDeliFee02` int(11) DEFAULT '0',"
				+"  `AddDeliFee03` int(11) DEFAULT '0',"
				+"  `HaighWayFee01` int(11) DEFAULT '0',"
				+"  `HaighWayFee02` int(11) DEFAULT '0',"
				+"  `ConsumptionTax` int(11) DEFAULT '0',"
				+"  `WithOutTaxTotal` int(11) DEFAULT '0',"
				+"  `TotalFee` int(11) DEFAULT '0',"
				+"  `FeeFixFG` int(11) DEFAULT '0',"
				+"  `FeeFixDate` datetime DEFAULT NULL,"
				+"  `ReceiptStampFG` int(11) DEFAULT '0',"
				+"  `ReceiptStampDate` datetime DEFAULT NULL,"
				+"  `InvoiceStatus` int(11) DEFAULT '0',"
				+"  `EntryDate` datetime DEFAULT NULL,"
				+"  `UpdateDate` datetime DEFAULT NULL,"
				+"  `EntryUser` varchar(50) DEFAULT NULL,"
				+"  `UpdateUser` varchar(50) DEFAULT NULL,"
				+"  `EntryPG` varchar(50) DEFAULT NULL,"
				+"  `UpdatePG` varchar(50) DEFAULT NULL,"
				+"  `UseFeeBasePtCd` varchar(20) DEFAULT NULL,"
				+"  `WmsStatus` int(11) DEFAULT '0',"
				+"  `WmsShipDate` datetime DEFAULT NULL,"
				+"  `CourseGpCd` varchar(20) DEFAULT NULL,"
				+"  `CourseCD` varchar(20) DEFAULT NULL,"
				+"  `CourseCDEda` int(11) DEFAULT '0',"
				+"  `PitGrp` varchar(20) DEFAULT NULL,"
				+"  `Pit01` varchar(20) DEFAULT NULL,"
				+"  `Pit02` varchar(20) DEFAULT NULL,"
				+"  `Pit03` varchar(20) DEFAULT NULL,"
				+"  `Pit04` varchar(20) DEFAULT NULL,"
				+"  `Pit05` varchar(20) DEFAULT NULL,"
				+"  PRIMARY KEY (`OkuriNo`)"
				+") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='荷物を配達する単位でデータ生成される送り状データ';";
		return sql;
	}


	private static String KT0010_OKURI_HDAltherTableSql(ArrayList<String> NoHitColumn){
		String sql = ""
				+"ALTER TABLE "+A00000Main.MySqlDefaultSchemaNYANKO+".KT0010_OKURI_HD";
		for(int i=0;i<NoHitColumn.size();i++) {
			if(0<i) {sql = sql + ",";}
			switch(NoHitColumn.get(i)) {
				case "cl_cd":
					sql = sql + " ADD cl_cd varchar(20) NOT NULL";
					break;
				case "InvoiceWHCD":
					sql = sql + " ADD InvoiceWHCD varchar(20) NOT NULL";
					break;
				case "OkuriNo":
					sql = sql + " ADD OkuriNo int(11) NOT NULL";
					break;
				case "ClDeliNo":
					sql = sql + " ADD ClDeliNo varchar(50) DEFAULT NULL";
					break;
				case "PickupWHCD":
					sql = sql + " ADD PickupWHCD varchar(20) DEFAULT NULL";
					break;
				case "PurposeFG":
					sql = sql + " ADD PurposeFG int(11) DEFAULT '0'";
					break;
				case "PlanDate":
					sql = sql + " ADD PlanDate datetime DEFAULT NULL";
					break;
				case "ShipDate":
					sql = sql + " ADD ShipDate datetime DEFAULT NULL";
					break;
				case "SPPlanDate":
					sql = sql + " ADD SPPlanDate datetime DEFAULT NULL";
					break;
				case "SPDate":
					sql = sql + " ADD SPDate datetime DEFAULT NULL";
					break;
				case "SPTimeFG":
					sql = sql + " ADD SPTimeFG varchar(20) DEFAULT NULL";
					break;
				case "SPTimeStr":
					sql = sql + " ADD SPTimeStr varchar(20) DEFAULT NULL";
					break;
				case "SPTimeEnd":
					sql = sql + " ADD SPTimeEnd varchar(20) DEFAULT NULL";
					break;
				case "TotalWeight":
					sql = sql + " ADD TotalWeight float DEFAULT '0'";
					break;
				case "TotalSize":
					sql = sql + " ADD TotalSize float DEFAULT '0'";
					break;
				case "TotalQty":
					sql = sql + " ADD TotalQty float DEFAULT '0'";
					break;
				case "DeliveryTypeCd":
					sql = sql + " ADD DeliveryTypeCd varchar(20) DEFAULT NULL";
					break;
				case "DeliTypeName":
					sql = sql + " ADD DeliTypeName varchar(50) DEFAULT NULL";
					break;
				case "DeliveryTypeCd02":
					sql = sql + " ADD DeliveryTypeCd02 varchar(20) DEFAULT NULL";
					break;
				case "DeliTypeName02":
					sql = sql + " ADD DeliTypeName02 varchar(50) DEFAULT NULL";
					break;
				case "DeliveryTypeCd03":
					sql = sql + " ADD DeliveryTypeCd03 varchar(20) DEFAULT NULL";
					break;
				case "DeliTypeName03":
					sql = sql + " ADD DeliTypeName03 varchar(50) DEFAULT NULL";
					break;
				case "DeliveryTypeCd04":
					sql = sql + " ADD DeliveryTypeCd04 varchar(20) DEFAULT NULL";
					break;
				case "DeliTypeName04":
					sql = sql + " ADD DeliTypeName04 varchar(50) DEFAULT NULL";
					break;
				case "DeliveryTypeCd05":
					sql = sql + " ADD DeliveryTypeCd05 varchar(20) DEFAULT NULL";
					break;
				case "DeliTypeName05":
					sql = sql + " ADD DeliTypeName05 varchar(50) DEFAULT NULL";
					break;
				case "CodFG":
					sql = sql + " ADD CodFG int(11) DEFAULT '0'";
					break;
				case "CodPayTotal":
					sql = sql + " ADD CodPayTotal int(11) DEFAULT '0'";
					break;
				case "CodPay":
					sql = sql + " ADD CodPay int(11) DEFAULT '0'";
					break;
				case "CodConsumptionTax":
					sql = sql + " ADD CodConsumptionTax int(11) DEFAULT '0'";
					break;
				case "ChildrenFG":
					sql = sql + " ADD ChildrenFG int(11) DEFAULT '0'";
					break;
				case "ParentOkuriNo":
					sql = sql + " ADD ParentOkuriNo int(11) DEFAULT '0'";
					break;
				case "NiokuriCd":
					sql = sql + " ADD NiokuriCd varchar(20) DEFAULT NULL";
					break;
				case "NiokuriDepartmentCd":
					sql = sql + " ADD NiokuriDepartmentCd varchar(20) DEFAULT NULL";
					break;
				case "NiokuriName01":
					sql = sql + " ADD NiokuriName01 varchar(50) DEFAULT NULL";
					break;
				case "NiokuriName02":
					sql = sql + " ADD NiokuriName02 varchar(50) DEFAULT NULL";
					break;
				case "NiokuriName03":
					sql = sql + " ADD NiokuriName03 varchar(50) DEFAULT NULL";
					break;
				case "NiokuriPost":
					sql = sql + " ADD NiokuriPost varchar(20) DEFAULT NULL";
					break;
				case "NiokuriAdd01":
					sql = sql + " ADD NiokuriAdd01 varchar(100) DEFAULT NULL";
					break;
				case "NiokuriAdd02":
					sql = sql + " ADD NiokuriAdd02 varchar(100) DEFAULT NULL";
					break;
				case "NiokuriAdd03":
					sql = sql + " ADD NiokuriAdd03 varchar(100) DEFAULT NULL";
					break;
				case "NioKuriTel":
					sql = sql + " ADD NioKuriTel varchar(20) DEFAULT NULL";
					break;
				case "NioKuriFax":
					sql = sql + " ADD NioKuriFax varchar(20) DEFAULT NULL";
					break;
				case "NioKuriMail":
					sql = sql + " ADD NioKuriMail varchar(200) DEFAULT NULL";
					break;
				case "NiokuriMunicCd":
					sql = sql + " ADD NiokuriMunicCd varchar(20) DEFAULT NULL";
					break;
				case "DeliCd":
					sql = sql + " ADD DeliCd varchar(20) DEFAULT NULL";
					break;
				case "ClDeliCd":
					sql = sql + " ADD ClDeliCd varchar(20) DEFAULT NULL";
					break;
				case "DeliDepartmentCd":
					sql = sql + " ADD DeliDepartmentCd varchar(20) DEFAULT NULL";
					break;
				case "DeliName01":
					sql = sql + " ADD DeliName01 varchar(50) DEFAULT NULL";
					break;
				case "DeliName02":
					sql = sql + " ADD DeliName02 varchar(50) DEFAULT NULL";
					break;
				case "DeliName03":
					sql = sql + " ADD DeliName03 varchar(50) DEFAULT NULL";
					break;
				case "DeliPost":
					sql = sql + " ADD DeliPost varchar(20) DEFAULT NULL";
					break;
				case "DeliAdd01":
					sql = sql + " ADD DeliAdd01 varchar(100) DEFAULT NULL";
					break;
				case "DeliAdd02":
					sql = sql + " ADD DeliAdd02 varchar(100) DEFAULT NULL";
					break;
				case "DeliAdd03":
					sql = sql + " ADD DeliAdd03 varchar(100) DEFAULT NULL";
					break;
				case "DeliTel":
					sql = sql + " ADD DeliTel varchar(20) DEFAULT NULL";
					break;
				case "DeliFax":
					sql = sql + " ADD DeliFax varchar(20) DEFAULT NULL";
					break;
				case "DeliMail":
					sql = sql + " ADD DeliMail varchar(200) DEFAULT NULL";
					break;
				case "DeliMunicCd":
					sql = sql + " ADD DeliMunicCd varchar(20) DEFAULT NULL";
					break;
				case "Com01":
					sql = sql + " ADD Com01 varchar(300) DEFAULT NULL";
					break;
				case "Com02":
					sql = sql + " ADD Com02 varchar(300) DEFAULT NULL";
					break;
				case "Com03":
					sql = sql + " ADD Com03 varchar(300) DEFAULT NULL";
					break;
				case "Com04":
					sql = sql + " ADD Com04 varchar(300) DEFAULT NULL";
					break;
				case "Com05":
					sql = sql + " ADD Com05 varchar(300) DEFAULT NULL";
					break;
				case "Status":
					sql = sql + " ADD Status int(11) DEFAULT '0'";
					break;
				case "TaxFg":
					sql = sql + " ADD TaxFg int(11) DEFAULT '0'";
					break;
				case "TaxRate":
					sql = sql + " ADD TaxRate int(11) DEFAULT '0'";
					break;
				case "DeliFee":
					sql = sql + " ADD DeliFee int(11) DEFAULT '0'";
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
				case "ConsumptionTax":
					sql = sql + " ADD ConsumptionTax int(11) DEFAULT '0'";
					break;
				case "WithOutTaxTotal":
					sql = sql + " ADD WithOutTaxTotal int(11) DEFAULT '0'";
					break;
				case "TotalFee":
					sql = sql + " ADD TotalFee int(11) DEFAULT '0'";
					break;
				case "FeeFixFG":
					sql = sql + " ADD FeeFixFG int(11) DEFAULT '0'";
					break;
				case "FeeFixDate":
					sql = sql + " ADD FeeFixDate datetime DEFAULT NULL";
					break;
				case "ReceiptStampFG":
					sql = sql + " ADD ReceiptStampFG int(11) DEFAULT '0'";
					break;
				case "ReceiptStampDate":
					sql = sql + " ADD ReceiptStampDate datetime DEFAULT NULL";
					break;
				case "InvoiceStatus":
					sql = sql + " ADD InvoiceStatus int(11) DEFAULT '0'";
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
				case "EntryPG":
					sql = sql + " ADD EntryPG varchar(50) DEFAULT NULL";
					break;
				case "UpdatePG":
					sql = sql + " ADD UpdatePG varchar(50) DEFAULT NULL";
					break;
				case "UseFeeBasePtCd":
					sql = sql + " ADD UseFeeBasePtCd varchar(20) DEFAULT NULL";
					break;
				case "WmsStatus":
					sql = sql + " ADD WmsStatus int(11) DEFAULT '0'";
					break;
				case "WmsShipDate":
					sql = sql + " ADD WmsShipDate datetime DEFAULT NULL";
					break;
				case "CourseGpCd":
					sql = sql + " ADD CourseGpCd varchar(20) DEFAULT NULL";
					break;
				case "CourseCD":
					sql = sql + " ADD CourseCD varchar(20) DEFAULT NULL";
					break;
				case "CourseCDEda":
					sql = sql + " ADD CourseCDEda int(11) DEFAULT '0'";
					break;
				case "PitGrp":
					sql = sql + " ADD PitGrp varchar(20) DEFAULT NULL";
					break;
				case "Pit01":
					sql = sql + " ADD Pit01 varchar(20) DEFAULT NULL";
					break;
				case "Pit02":
					sql = sql + " ADD Pit02 varchar(20) DEFAULT NULL";
					break;
				case "Pit03":
					sql = sql + " ADD Pit03 varchar(20) DEFAULT NULL";
					break;
				case "Pit04":
					sql = sql + " ADD Pit04 varchar(20) DEFAULT NULL";
					break;
				case "Pit05":
					sql = sql + " ADD Pit05 varchar(20) DEFAULT NULL";
					break;
				default:
					break;
			}
		}
		sql = sql + ";";
		return sql;
	}


	private static String KT0011_OKURI_MSCreateSql() {
		//送り状明細テーブルを作るNYANKO
		String sql = ""
				+"CREATE TABLE `KT0011_OKURI_MS` ("
				+"  `cl_cd` varchar(20) NOT NULL,"
				+"  `InvoiceWHCD` varchar(20) NOT NULL,"
				+"  `OkuriNo` int(11) NOT NULL,"
				+"  `MsNo` int(11) NOT NULL,"
				+"  `DeliNo` varchar(20) DEFAULT '0',"
				+"  `DelliMsNo` int(11) DEFAULT '0',"
				+"  `ClOrderNo` varchar(50) DEFAULT NULL,"
				+"  `ClGpCd` varchar(20) DEFAULT NULL,"
				+"  `ItemCd` varchar(20) DEFAULT NULL,"
				+"  `ItemName01` varchar(100) DEFAULT NULL,"
				+"  `ItemName02` varchar(100) DEFAULT NULL,"
				+"  `ItemName03` varchar(100) DEFAULT NULL,"
				+"  `UnitWeight` float DEFAULT '0',"
				+"  `UnitSize` float DEFAULT '0',"
				+"  `Qty` float DEFAULT '0',"
				+"  `PackingQty` int(11) NOT NULL DEFAULT '0',"
				+"  `UnitName` varchar(20) NOT NULL,"
				+"  `SubTotalWeight` float DEFAULT '0',"
				+"  `SubTotalSize` float DEFAULT '0',"
				+"  `UnitPrice` float DEFAULT '0',"
				+"  `SubTotalPrice` float DEFAULT '0',"
				+"  `CategoryCd` varchar(20) DEFAULT NULL,"
				+"  `CategoryName` varchar(50) DEFAULT NULL,"
				+"  `TildFG` varchar(20) DEFAULT NULL,"
				+"  `TildName` varchar(50) DEFAULT NULL,"
				+"  `Com01` varchar(300) DEFAULT NULL,"
				+"  `Com02` varchar(300) DEFAULT NULL,"
				+"  `Com03` varchar(300) DEFAULT NULL,"
				+"  `Com04` varchar(300) DEFAULT NULL,"
				+"  `Com05` varchar(300) DEFAULT NULL,"
				+"  `EntryDate` datetime DEFAULT NULL,"
				+"  `UpdateDate` datetime DEFAULT NULL,"
				+"  `EntryUser` varchar(50) DEFAULT NULL,"
				+"  `UpdateUser` varchar(50) DEFAULT NULL,"
				+"  `Lot` varchar(20) DEFAULT NULL,"
				+"  `ExpDate` datetime DEFAULT NULL,"
				+"  `PackingType` int(11) NOT NULL DEFAULT '0',"
				+"  `ClItemCd` varchar(20) DEFAULT NULL,"
				+"  `ItemMDNo` varchar(20) DEFAULT NULL,"
				+"  `JanCd` varchar(20) DEFAULT NULL,"
				+"  PRIMARY KEY (`OkuriNo`,`MsNo`)"
				+") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='KT0010_OKURI_HD送り状データヘッダの内訳情報cl_cdとInvoiceWHCDでKT0010_OKURI_HD送り状データヘッダを特定';";
		return sql;
	}

	private static String KT0011_OKURI_MSAltherTableSql(ArrayList<String> NoHitColumn){
		String sql = ""
				+"ALTER TABLE "+A00000Main.MySqlDefaultSchemaNYANKO+".KT0011_OKURI_MS";
		for(int i=0;i<NoHitColumn.size();i++) {
			if(0<i) {sql = sql + ",";}
			switch(NoHitColumn.get(i)) {
				case "cl_cd":
					sql = sql + " ADD cl_cd varchar(20) NOT NULL";
					break;
				case "InvoiceWHCD":
					sql = sql + " ADD InvoiceWHCD varchar(20) NOT NULL";
					break;
				case "OkuriNo":
					sql = sql + " ADD OkuriNo int(11) NOT NULL";
					break;
				case "MsNo":
					sql = sql + " ADD MsNo int(11) NOT NULL";
					break;
				case "DeliNo":
					sql = sql + " ADD DeliNo varchar(20) DEFAULT '0'";
					break;
				case "DelliMsNo":
					sql = sql + " ADD DelliMsNo int(11) DEFAULT '0'";
					break;
				case "ClOrderNo":
					sql = sql + " ADD ClOrderNo varchar(50) DEFAULT NULL";
					break;
				case "ClGpCd":
					sql = sql + " ADD ClGpCd varchar(20) DEFAULT NULL";
					break;
				case "ItemCd":
					sql = sql + " ADD ItemCd varchar(20) DEFAULT NULL";
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
				case "UnitWeight":
					sql = sql + " ADD UnitWeight float DEFAULT '0'";
					break;
				case "UnitSize":
					sql = sql + " ADD UnitSize float DEFAULT '0'";
					break;
				case "Qty":
					sql = sql + " ADD Qty float DEFAULT '0'";
					break;
				case "PackingQty":
					sql = sql + " ADD PackingQty int(11) NOT NULL DEFAULT '0'";
					break;
				case "UnitName":
					sql = sql + " ADD UnitName varchar(20) NOT NULL";
					break;
				case "SubTotalWeight":
					sql = sql + " ADD SubTotalWeight float DEFAULT '0'";
					break;
				case "SubTotalSize":
					sql = sql + " ADD SubTotalSize float DEFAULT '0'";
					break;
				case "UnitPrice":
					sql = sql + " ADD UnitPrice float DEFAULT '0'";
					break;
				case "SubTotalPrice":
					sql = sql + " ADD SubTotalPrice float DEFAULT '0'";
					break;
				case "CategoryCd":
					sql = sql + " ADD CategoryCd varchar(20) DEFAULT NULL";
					break;
				case "CategoryName":
					sql = sql + " ADD CategoryName varchar(50) DEFAULT NULL";
					break;
				case "TildFG":
					sql = sql + " ADD TildFG varchar(20) DEFAULT NULL";
					break;
				case "TildName":
					sql = sql + " ADD TildName varchar(50) DEFAULT NULL";
					break;
				case "Com01":
					sql = sql + " ADD Com01 varchar(300) DEFAULT NULL";
					break;
				case "Com02":
					sql = sql + " ADD Com02 varchar(300) DEFAULT NULL";
					break;
				case "Com03":
					sql = sql + " ADD Com03 varchar(300) DEFAULT NULL";
					break;
				case "Com04":
					sql = sql + " ADD Com04 varchar(300) DEFAULT NULL";
					break;
				case "Com05":
					sql = sql + " ADD Com05 varchar(300) DEFAULT NULL";
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
				case "Lot":
					sql = sql + " ADD Lot varchar(20) DEFAULT NULL";
					break;
				case "ExpDate":
					sql = sql + " ADD ExpDate datetime DEFAULT NULL";
					break;
				case "PackingType":
					sql = sql + " ADD PackingType int(11) NOT NULL DEFAULT '0'";
					break;
				case "ClItemCd":
					sql = sql + " ADD ClItemCd varchar(20) DEFAULT NULL";
					break;
				case "ItemMDNo":
					sql = sql + " ADD ItemMDNo varchar(20) DEFAULT NULL";
					break;
				case "JanCd":
					sql = sql + " ADD JanCd varchar(20) DEFAULT NULL";
					break;
				default:
					break;
			}
		}
		sql = sql + ";";
		return sql;
	}


	private static String KT0012_OKURI_PROGRESSCreateSql() {
		//送り状配送進捗を作るNYANKO
		String sql = ""
				+"CREATE TABLE `KT0012_OKURI_PROGRESS` ("
				+"  `cl_cd` varchar(20) NOT NULL,"
				+"  `InvoiceWHCD` varchar(20) NOT NULL,"
				+"  `OkuriNo` int(11) NOT NULL,"
				+"  `ProgressNo` int(11) NOT NULL,"
				+"  `ProgressCd` varchar(20) NOT NULL,"
				+"  `ProgressName` varchar(100) NOT NULL,"
				+"  `Com01` varchar(300) DEFAULT NULL,"
				+"  `Com02` varchar(300) DEFAULT NULL,"
				+"  `Com03` varchar(300) DEFAULT NULL,"
				+"  `Com04` varchar(300) DEFAULT NULL,"
				+"  `Com05` varchar(300) DEFAULT NULL,"
				+"  `EntryDate` datetime DEFAULT NULL,"
				+"  `UpdateDate` datetime DEFAULT NULL,"
				+"  `EntryUser` varchar(50) DEFAULT NULL,"
				+"  `UpdateUser` varchar(50) DEFAULT NULL,"
				+"  PRIMARY KEY (`cl_cd`,`InvoiceWHCD`,`OkuriNo`,`ProgressNo`)"
				+") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='送り状単位の進捗情報を累積格納する EX）配達完了・遅配連絡等';";
		return sql;
	}

	private static String KT0012_OKURI_PROGRESSAltherTableSql(ArrayList<String> NoHitColumn){
		String sql = ""
				+"ALTER TABLE "+A00000Main.MySqlDefaultSchemaNYANKO+".KT0012_OKURI_PROGRESS";
		for(int i=0;i<NoHitColumn.size();i++) {
			if(0<i) {sql = sql + ",";}
			switch(NoHitColumn.get(i)) {
				case "cl_cd":
					sql = sql + " ADD cl_cd varchar(20) NOT NULL";
					break;
				case "InvoiceWHCD":
					sql = sql + " ADD InvoiceWHCD varchar(20) NOT NULL";
					break;
				case "OkuriNo":
					sql = sql + " ADD OkuriNo int(11) NOT NULL";
					break;
				case "ProgressNo":
					sql = sql + " ADD ProgressNo int(11) NOT NULL";
					break;
				case "ProgressCd":
					sql = sql + " ADD ProgressCd varchar(20) NOT NULL";
					break;
				case "ProgressName":
					sql = sql + " ADD ProgressName varchar(100) NOT NULL";
					break;
				case "Com01":
					sql = sql + " ADD Com01 varchar(300) DEFAULT NULL";
					break;
				case "Com02":
					sql = sql + " ADD Com02 varchar(300) DEFAULT NULL";
					break;
				case "Com03":
					sql = sql + " ADD Com03 varchar(300) DEFAULT NULL";
					break;
				case "Com04":
					sql = sql + " ADD Com04 varchar(300) DEFAULT NULL";
					break;
				case "Com05":
					sql = sql + " ADD Com05 varchar(300) DEFAULT NULL";
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
		sql = sql + ";";
		return sql;
	}


	private static String KT0013_SEIKYUCreateSql() {
		//運賃請求データを作るNYANKO
		String sql = ""
				+"CREATE TABLE `KT0013_SEIKYU` ("
				+"  `cl_cd` varchar(20) NOT NULL,"
				+"  `InvoiceWHCD` varchar(20) NOT NULL,"
				+"  `SeikyuNo` int(11) NOT NULL,"
				+"  `OkuriNo` int(11) NOT NULL,"
				+"  `SeikyuDate` datetime NOT NULL,"
				+"  `ShimeDate` datetime NOT NULL,"
				+"  `TaxFg` int(11) DEFAULT '0',"
				+"  `TaxRate` int(11) DEFAULT '0',"
				+"  `DeliFee` int(11) DEFAULT '0',"
				+"  `AddDeliFee01` int(11) DEFAULT '0',"
				+"  `AddDeliFee02` int(11) DEFAULT '0',"
				+"  `AddDeliFee03` int(11) DEFAULT '0',"
				+"  `HaighWayFee01` int(11) DEFAULT '0',"
				+"  `HaighWayFee02` int(11) DEFAULT '0',"
				+"  `ConsumptionTax` int(11) DEFAULT '0',"
				+"  `WithOutTaxTotal` int(11) DEFAULT '0',"
				+"  `TotalFee` int(11) DEFAULT '0',"
				+"  `Com01` varchar(300) DEFAULT NULL,"
				+"  `Com02` varchar(300) DEFAULT NULL,"
				+"  `Com03` varchar(300) DEFAULT NULL,"
				+"  `Com04` varchar(300) DEFAULT NULL,"
				+"  `Com05` varchar(300) DEFAULT NULL,"
				+"  `EntryDate` datetime DEFAULT NULL,"
				+"  `UpdateDate` datetime DEFAULT NULL,"
				+"  `EntryUser` varchar(50) DEFAULT NULL,"
				+"  `UpdateUser` varchar(50) DEFAULT NULL,"
				+"  `PrtFg` int(11) DEFAULT '0',"
				+"  `DataOutFg` int(11) DEFAULT '0',"
				+"  PRIMARY KEY (`cl_cd`,`InvoiceWHCD`,`SeikyuNo`,`OkuriNo`)"
				+") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='送り状単位で請求明細データを生成する';";
		return sql;
	}

	private static String KT0013_SEIKYUAltherTableSql(ArrayList<String> NoHitColumn){
		String sql = ""
				+"ALTER TABLE "+A00000Main.MySqlDefaultSchemaNYANKO+".KT0013_SEIKYU";
		for(int i=0;i<NoHitColumn.size();i++) {
			if(0<i) {sql = sql + ",";}
			switch(NoHitColumn.get(i)) {
				case "cl_cd":
					sql = sql + " ADD cl_cd varchar(20) NOT NULL";
					break;
				case "InvoiceWHCD":
					sql = sql + " ADD InvoiceWHCD varchar(20) NOT NULL";
					break;
				case "SeikyuNo":
					sql = sql + " ADD SeikyuNo int(11) NOT NULL";
					break;
				case "OkuriNo":
					sql = sql + " ADD OkuriNo int(11) NOT NULL";
					break;
				case "SeikyuDate":
					sql = sql + " ADD SeikyuDate datetime NOT NULL";
					break;
				case "ShimeDate":
					sql = sql + " ADD ShimeDate datetime NOT NULL";
					break;
				case "TaxFg":
					sql = sql + " ADD TaxFg int(11) DEFAULT '0'";
					break;
				case "TaxRate":
					sql = sql + " ADD TaxRate int(11) DEFAULT '0'";
					break;
				case "DeliFee":
					sql = sql + " ADD DeliFee int(11) DEFAULT '0'";
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
				case "ConsumptionTax":
					sql = sql + " ADD ConsumptionTax int(11) DEFAULT '0'";
					break;
				case "WithOutTaxTotal":
					sql = sql + " ADD WithOutTaxTotal int(11) DEFAULT '0'";
					break;
				case "TotalFee":
					sql = sql + " ADD TotalFee int(11) DEFAULT '0'";
					break;
				case "Com01":
					sql = sql + " ADD Com01 varchar(300) DEFAULT NULL";
					break;
				case "Com02":
					sql = sql + " ADD Com02 varchar(300) DEFAULT NULL";
					break;
				case "Com03":
					sql = sql + " ADD Com03 varchar(300) DEFAULT NULL";
					break;
				case "Com04":
					sql = sql + " ADD Com04 varchar(300) DEFAULT NULL";
					break;
				case "Com05":
					sql = sql + " ADD Com05 varchar(300) DEFAULT NULL";
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
				case "PrtFg":
					sql = sql + " ADD PrtFg int(11) DEFAULT '0'";
					break;
				case "DataOutFg":
					sql = sql + " ADD DataOutFg int(11) DEFAULT '0'";
					break;
				default:
					break;
			}
		}
		sql = sql + ";";
		return sql;
	}


	private static String KT0023_SHIHARAICreateSql() {
		//運賃支払データを作るNYANKO
		String sql = ""
				+"CREATE TABLE `KT0023_SHIHARAI` ("
				+"  `DeliWHCD` varchar(20) NOT NULL,"
				+"  `DeliCompCd` varchar(20) NOT NULL,"
				+"  `ShiharaiNo` int(11) NOT NULL,"
				+"  `HaishaNo` int(11) NOT NULL,"
				+"  `ShiharaiDate` datetime NOT NULL,"
				+"  `ShimeDate` datetime NOT NULL,"
				+"  `TaxFg` int(11) DEFAULT '0',"
				+"  `TaxRate` int(11) DEFAULT '0',"
				+"  `DeliPay` int(11) DEFAULT '0',"
				+"  `AddDeliPay01` int(11) DEFAULT '0',"
				+"  `AddDeliPay02` int(11) DEFAULT '0',"
				+"  `AddDeliPay03` int(11) DEFAULT '0',"
				+"  `HaighWayPay01` int(11) DEFAULT '0',"
				+"  `HaighWayPay02` int(11) DEFAULT '0',"
				+"  `ConsumptionTax` int(11) DEFAULT '0',"
				+"  `WithOutTaxTotal` int(11) DEFAULT '0',"
				+"  `TotalPay` int(11) DEFAULT '0',"
				+"  `EntryDate` datetime DEFAULT NULL,"
				+"  `UpdateDate` datetime DEFAULT NULL,"
				+"  `EntryUser` varchar(50) DEFAULT NULL,"
				+"  `UpdateUser` varchar(50) DEFAULT NULL,"
				+"  PRIMARY KEY (`DeliWHCD`,`DeliCompCd`,`ShiharaiNo`,`HaishaNo`)"
				+") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='配車単位(KT0020_HAISHA_HD)で支払データを生成する';";
		return sql;
	}

	private static String KT0023_SHIHARAIAltherTableSql(ArrayList<String> NoHitColumn){
		String sql = ""
				+"ALTER TABLE "+A00000Main.MySqlDefaultSchemaNYANKO+".KT0023_SHIHARAI";
		for(int i=0;i<NoHitColumn.size();i++) {
			if(0<i) {sql = sql + ",";}
			switch(NoHitColumn.get(i)) {
				case "DeliWHCD":
					sql = sql + " ADD DeliWHCD varchar(20) NOT NULL";
					break;
				case "DeliCompCd":
					sql = sql + " ADD DeliCompCd varchar(20) NOT NULL";
					break;
				case "ShiharaiNo":
					sql = sql + " ADD ShiharaiNo int(11) NOT NULL";
					break;
				case "HaishaNo":
					sql = sql + " ADD HaishaNo int(11) NOT NULL";
					break;
				case "ShiharaiDate":
					sql = sql + " ADD ShiharaiDate datetime NOT NULL";
					break;
				case "ShimeDate":
					sql = sql + " ADD ShimeDate datetime NOT NULL";
					break;
				case "TaxFg":
					sql = sql + " ADD TaxFg int(11) DEFAULT '0'";
					break;
				case "TaxRate":
					sql = sql + " ADD TaxRate int(11) DEFAULT '0'";
					break;
				case "DeliPay":
					sql = sql + " ADD DeliPay int(11) DEFAULT '0'";
					break;
				case "AddDeliPay01":
					sql = sql + " ADD AddDeliPay01 int(11) DEFAULT '0'";
					break;
				case "AddDeliPay02":
					sql = sql + " ADD AddDeliPay02 int(11) DEFAULT '0'";
					break;
				case "AddDeliPay03":
					sql = sql + " ADD AddDeliPay03 int(11) DEFAULT '0'";
					break;
				case "HaighWayPay01":
					sql = sql + " ADD HaighWayPay01 int(11) DEFAULT '0'";
					break;
				case "HaighWayPay02":
					sql = sql + " ADD HaighWayPay02 int(11) DEFAULT '0'";
					break;
				case "ConsumptionTax":
					sql = sql + " ADD ConsumptionTax int(11) DEFAULT '0'";
					break;
				case "WithOutTaxTotal":
					sql = sql + " ADD WithOutTaxTotal int(11) DEFAULT '0'";
					break;
				case "TotalPay":
					sql = sql + " ADD TotalPay int(11) DEFAULT '0'";
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
		sql = sql + ";";
		return sql;
	}

	
	private static String KT0040_PrintControlCreateSql(){
		//印刷制御を作るNYANKO
		String sql = ""
				+"CREATE TABLE `KT0040_PrintControl` ("
				+"  `PrintCd` varchar(50) NOT NULL COMMENT '印刷帳票CD',"
				+"  `OkuriNo` int(11) NOT NULL DEFAULT '0' COMMENT '送り状番号等',"
				+"  `Key01` int(11) NOT NULL DEFAULT '0' COMMENT 'サブキー01',"
				+"  `Key02` int(11) NOT NULL DEFAULT '0' COMMENT 'サブキー02',"
				+"  `Key03` int(11) NOT NULL DEFAULT '0' COMMENT 'サブキー03',"
				+"  `Key04` int(11) NOT NULL DEFAULT '0' COMMENT 'サブキー04',"
				+"  `EntryDate` datetime DEFAULT NULL COMMENT '登録日',"
				+"  `UpdateDate` datetime DEFAULT NULL COMMENT '更新日',"
				+"  `EntryUser` varchar(50) DEFAULT NULL COMMENT '登録者',"
				+"  `UpdateUser` varchar(50) DEFAULT NULL COMMENT '更新者',"
				+"  PRIMARY KEY (`PrintCd`,`OkuriNo`,`Key01`,`Key02`,`Key03`,`Key04`)"
				+") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='印刷制御';"
				;
		return sql;
	}

	private static String KT0040_PrintControlAltherTableSql(ArrayList<String> NoHitColumn){
		String sql = ""
				+"ALTER TABLE "+A00000Main.MySqlDefaultSchemaNYANKO+".KT0040_PrintControl";
		for(int i=0;i<NoHitColumn.size();i++) {
			if(0<i) {sql = sql + ",";}
			switch(NoHitColumn.get(i)) {
				case "PrintCd":
					sql = sql + " ADD PrintCd varchar(50) NOT NULL";
					break;
				case "OkuriNo":
					sql = sql + " ADD OkuriNo int(11) NOT NULL DEFAULT '0'";
					break;
				case "Key01":
					sql = sql + " ADD Key01 int(11) NOT NULL DEFAULT '0'";
					break;
				case "Key02":
					sql = sql + " ADD Key02 int(11) NOT NULL DEFAULT '0'";
					break;
				case "Key03":
					sql = sql + " ADD Key03 int(11) NOT NULL DEFAULT '0'";
					break;
				case "Key04":
					sql = sql + " ADD Key04 int(11) NOT NULL DEFAULT '0'";
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
		sql = sql + ";";
		return sql;
	}
	

	private static String KT020_HAISHA_HDCreateSql() {
		//配車ヘッダを作るNYANKO
		String sql = ""
				+"CREATE TABLE `KT020_HAISHA_HD` ("
				+"  `DeliWHCD` varchar(20) NOT NULL,"
				+"  `HaishaNo` int(11) NOT NULL,"
				+"  `PlanDate` datetime DEFAULT NULL,"
				+"  `ShipDate` datetime DEFAULT NULL,"
				+"  `SPPlanDate` datetime DEFAULT NULL,"
				+"  `SPDate` datetime DEFAULT NULL,"
				+"  `TotalWeight` float DEFAULT '0',"
				+"  `TotalSize` float DEFAULT '0',"
				+"  `TotalQty` int(11) DEFAULT '0',"
				+"  `DeliveryTypeCd` varchar(20) NOT NULL,"
				+"  `DeliTypeName` varchar(50) DEFAULT NULL,"
				+"  `DeliveryTypeCd02` varchar(20) NOT NULL,"
				+"  `DeliTypeName02` varchar(50) DEFAULT NULL,"
				+"  `DeliveryTypeCd03` varchar(20) NOT NULL,"
				+"  `DeliTypeName03` varchar(50) DEFAULT NULL,"
				+"  `DeliveryTypeCd04` varchar(20) NOT NULL,"
				+"  `DeliTypeName04` varchar(50) DEFAULT NULL,"
				+"  `DeliveryTypeCd05` varchar(20) NOT NULL,"
				+"  `DeliTypeName05` varchar(50) DEFAULT NULL,"
				+"  `DeliCompCd` varchar(20) DEFAULT NULL,"
				+"  `DeliCompName` varchar(100) DEFAULT NULL,"
				+"  `CarCd` varchar(20) DEFAULT NULL,"
				+"  `CarName` varchar(100) DEFAULT NULL,"
				+"  `DriverCd` varchar(20) DEFAULT NULL,"
				+"  `DriverName` varchar(100) DEFAULT NULL,"
				+"  `Status` int(11) DEFAULT '0',"
				+"  `TaxFg` int(11) DEFAULT '0',"
				+"  `TaxRate` int(11) DEFAULT '0',"
				+"  `DeliPay` int(11) DEFAULT '0',"
				+"  `AddDeliPay01` int(11) DEFAULT '0',"
				+"  `AddDeliPay02` int(11) DEFAULT '0',"
				+"  `AddDeliPay03` int(11) DEFAULT '0',"
				+"  `HaighWayPay01` int(11) DEFAULT '0',"
				+"  `HaighWayPay02` int(11) DEFAULT '0',"
				+"  `ConsumptionTax` int(11) DEFAULT '0',"
				+"  `WithOutTaxTotal` int(11) DEFAULT '0',"
				+"  `TotalPay` int(11) DEFAULT '0',"
				+"  `PayFixFG` int(11) DEFAULT '0',"
				+"  `PayFixDate` datetime DEFAULT NULL,"
				+"  `InvoiceStatus` int(11) DEFAULT '0',"
				+"  `ChildrenFG` int(11) DEFAULT '0',"
				+"  `ParentHaishaNo` int(11) DEFAULT '0',"
				+"  `EntryDate` datetime DEFAULT NULL,"
				+"  `UpdateDate` datetime DEFAULT NULL,"
				+"  `EntryUser` varchar(50) DEFAULT NULL,"
				+"  `UpdateUser` varchar(50) DEFAULT NULL,"
				+"  `UsePayBasePtCd` varchar(20) DEFAULT NULL,"
				+"  PRIMARY KEY (`DeliWHCD`,`HaishaNo`)"
				+") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='配車ヘッダ';";
		return sql;
	}

	private static String KT020_HAISHA_HDAltherTableSql(ArrayList<String> NoHitColumn){
		String sql = ""
				+"ALTER TABLE "+A00000Main.MySqlDefaultSchemaNYANKO+".KT020_HAISHA_HD";
		for(int i=0;i<NoHitColumn.size();i++) {
			if(0<i) {sql = sql + ",";}
			switch(NoHitColumn.get(i)) {
				case "DeliWHCD":
					sql = sql + " ADD DeliWHCD varchar(20) NOT NULL";
					break;
				case "HaishaNo":
					sql = sql + " ADD HaishaNo int(11) NOT NULL";
					break;
				case "PlanDate":
					sql = sql + " ADD PlanDate datetime DEFAULT NULL";
					break;
				case "ShipDate":
					sql = sql + " ADD ShipDate datetime DEFAULT NULL";
					break;
				case "SPPlanDate":
					sql = sql + " ADD SPPlanDate datetime DEFAULT NULL";
					break;
				case "SPDate":
					sql = sql + " ADD SPDate datetime DEFAULT NULL";
					break;
				case "TotalWeight":
					sql = sql + " ADD TotalWeight float DEFAULT '0'";
					break;
				case "TotalSize":
					sql = sql + " ADD TotalSize float DEFAULT '0'";
					break;
				case "TotalQty":
					sql = sql + " ADD TotalQty int(11) DEFAULT '0'";
					break;
				case "DeliveryTypeCd":
					sql = sql + " ADD DeliveryTypeCd varchar(20) NOT NULL";
					break;
				case "DeliTypeName":
					sql = sql + " ADD DeliTypeName varchar(50) DEFAULT NULL";
					break;
				case "DeliveryTypeCd02":
					sql = sql + " ADD DeliveryTypeCd02 varchar(20) NOT NULL";
					break;
				case "DeliTypeName02":
					sql = sql + " ADD DeliTypeName02 varchar(50) DEFAULT NULL";
					break;
				case "DeliveryTypeCd03":
					sql = sql + " ADD DeliveryTypeCd03 varchar(20) NOT NULL";
					break;
				case "DeliTypeName03":
					sql = sql + " ADD DeliTypeName03 varchar(50) DEFAULT NULL";
					break;
				case "DeliveryTypeCd04":
					sql = sql + " ADD DeliveryTypeCd04 varchar(20) NOT NULL";
					break;
				case "DeliTypeName04":
					sql = sql + " ADD DeliTypeName04 varchar(50) DEFAULT NULL";
					break;
				case "DeliveryTypeCd05":
					sql = sql + " ADD DeliveryTypeCd05 varchar(20) NOT NULL";
					break;
				case "DeliTypeName05":
					sql = sql + " ADD DeliTypeName05 varchar(50) DEFAULT NULL";
					break;
				case "DeliCompCd":
					sql = sql + " ADD DeliCompCd varchar(20) DEFAULT NULL";
					break;
				case "DeliCompName":
					sql = sql + " ADD DeliCompName varchar(100) DEFAULT NULL";
					break;
				case "CarCd":
					sql = sql + " ADD CarCd varchar(20) DEFAULT NULL";
					break;
				case "CarName":
					sql = sql + " ADD CarName varchar(100) DEFAULT NULL";
					break;
				case "DriverCd":
					sql = sql + " ADD DriverCd varchar(20) DEFAULT NULL";
					break;
				case "DriverName":
					sql = sql + " ADD DriverName varchar(100) DEFAULT NULL";
					break;
				case "Status":
					sql = sql + " ADD Status int(11) DEFAULT '0'";
					break;
				case "TaxFg":
					sql = sql + " ADD TaxFg int(11) DEFAULT '0'";
					break;
				case "TaxRate":
					sql = sql + " ADD TaxRate int(11) DEFAULT '0'";
					break;
				case "DeliPay":
					sql = sql + " ADD DeliPay int(11) DEFAULT '0'";
					break;
				case "AddDeliPay01":
					sql = sql + " ADD AddDeliPay01 int(11) DEFAULT '0'";
					break;
				case "AddDeliPay02":
					sql = sql + " ADD AddDeliPay02 int(11) DEFAULT '0'";
					break;
				case "AddDeliPay03":
					sql = sql + " ADD AddDeliPay03 int(11) DEFAULT '0'";
					break;
				case "HaighWayPay01":
					sql = sql + " ADD HaighWayPay01 int(11) DEFAULT '0'";
					break;
				case "HaighWayPay02":
					sql = sql + " ADD HaighWayPay02 int(11) DEFAULT '0'";
					break;
				case "ConsumptionTax":
					sql = sql + " ADD ConsumptionTax int(11) DEFAULT '0'";
					break;
				case "WithOutTaxTotal":
					sql = sql + " ADD WithOutTaxTotal int(11) DEFAULT '0'";
					break;
				case "TotalPay":
					sql = sql + " ADD TotalPay int(11) DEFAULT '0'";
					break;
				case "PayFixFG":
					sql = sql + " ADD PayFixFG int(11) DEFAULT '0'";
					break;
				case "PayFixDate":
					sql = sql + " ADD PayFixDate datetime DEFAULT NULL";
					break;
				case "InvoiceStatus":
					sql = sql + " ADD InvoiceStatus int(11) DEFAULT '0'";
					break;
				case "ChildrenFG":
					sql = sql + " ADD ChildrenFG int(11) DEFAULT '0'";
					break;
				case "ParentHaishaNo":
					sql = sql + " ADD ParentHaishaNo int(11) DEFAULT '0'";
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
				case "UsePayBasePtCd":
					sql = sql + " ADD UsePayBasePtCd varchar(20) DEFAULT NULL";
					break;
				default:
					break;
			}
		}
		sql = sql + ";";
		return sql;
	}


	private static String KT021_HAISHA_MSCreateSql() {
		//配車明細を作るNYANKO
		String sql = ""
				+"CREATE TABLE `KT021_HAISHA_MS` ("
				+"  `DeliWHCD` varchar(20) NOT NULL,"
				+"  `HaishaNo` int(11) NOT NULL,"
				+"  `BinNo` int(11) NOT NULL DEFAULT '1',"
				+"  `MsNo` int(11) NOT NULL,"
				+"  `InvoiceWHCD` varchar(20) NOT NULL,"
				+"  `cl_cd` varchar(20) NOT NULL,"
				+"  `OkuriNo` int(11) NOT NULL DEFAULT '0',"
				+"  `Status` int(11) DEFAULT '0',"
				+"  `FG01` int(11) DEFAULT '0',"
				+"  `FG02` int(11) DEFAULT '0',"
				+"  `FG03` int(11) DEFAULT '0',"
				+"  `FG04` int(11) DEFAULT '0',"
				+"  `FG05` int(11) DEFAULT '0',"
				+"  `Com01` varchar(300) DEFAULT NULL,"
				+"  `Com02` varchar(300) DEFAULT NULL,"
				+"  `Com03` varchar(300) DEFAULT NULL,"
				+"  `Com04` varchar(300) DEFAULT NULL,"
				+"  `Com05` varchar(300) DEFAULT NULL,"
				+"  `EntryDate` datetime DEFAULT NULL,"
				+"  `UpdateDate` datetime DEFAULT NULL,"
				+"  `EntryUser` varchar(50) DEFAULT NULL,"
				+"  `UpdateUser` varchar(50) DEFAULT NULL,"
				+"  PRIMARY KEY (`DeliWHCD`,`HaishaNo`,`BinNo`,`MsNo`)"
				+") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='配車明細';";
		return sql;
	}


	private static String KT021_HAISHA_MSAltherTableSql(ArrayList<String> NoHitColumn){
		String sql = ""
				+"ALTER TABLE "+A00000Main.MySqlDefaultSchemaNYANKO+".KT021_HAISHA_MS";
		for(int i=0;i<NoHitColumn.size();i++) {
			if(0<i) {sql = sql + ",";}
			switch(NoHitColumn.get(i)) {
				case "DeliWHCD":
					sql = sql + " ADD DeliWHCD varchar(20) NOT NULL";
					break;
				case "HaishaNo":
					sql = sql + " ADD HaishaNo int(11) NOT NULL";
					break;
				case "BinNo":
					sql = sql + " ADD BinNo int(11) NOT NULL DEFAULT '1'";
					break;
				case "MsNo":
					sql = sql + " ADD MsNo int(11) NOT NULL";
					break;
				case "InvoiceWHCD":
					sql = sql + " ADD InvoiceWHCD varchar(20) NOT NULL";
					break;
				case "cl_cd":
					sql = sql + " ADD cl_cd varchar(20) NOT NULL";
					break;
				case "OkuriNo":
					sql = sql + " ADD OkuriNo int(11) NOT NULL DEFAULT '0'";
					break;
				case "Status":
					sql = sql + " ADD Status int(11) DEFAULT '0'";
					break;
				case "FG01":
					sql = sql + " ADD FG01 int(11) DEFAULT '0'";
					break;
				case "FG02":
					sql = sql + " ADD FG02 int(11) DEFAULT '0'";
					break;
				case "FG03":
					sql = sql + " ADD FG03 int(11) DEFAULT '0'";
					break;
				case "FG04":
					sql = sql + " ADD FG04 int(11) DEFAULT '0'";
					break;
				case "FG05":
					sql = sql + " ADD FG05 int(11) DEFAULT '0'";
					break;
				case "Com01":
					sql = sql + " ADD Com01 varchar(300) DEFAULT NULL";
					break;
				case "Com02":
					sql = sql + " ADD Com02 varchar(300) DEFAULT NULL";
					break;
				case "Com03":
					sql = sql + " ADD Com03 varchar(300) DEFAULT NULL";
					break;
				case "Com04":
					sql = sql + " ADD Com04 varchar(300) DEFAULT NULL";
					break;
				case "Com05":
					sql = sql + " ADD Com05 varchar(300) DEFAULT NULL";
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
		sql = sql + ";";
		return sql;
	}
	
	//WANKOデータベースの必要なテーブルを確認する
	private static void WankoDBCheck() {
		String[] TableName=TabeleList("WANKO");
		
		boolean WM0000PARAMETERUnHitFg = true;
		boolean WM0010LOCATIONMSTUnHitFg = true;
		boolean WM0010SupplierUnHitFg = true;
		boolean WM0015_BerthMstUnHitFg = true;
		boolean WM0016_PitGlpMSTUnHitFg = true;
		boolean WM0017_PitMSTUnHitFg = true;
		boolean WM0020AdjustReasonUnHitFg = true;
		boolean WM0031WhFeeBaseMstInUnHitFg = true;
		boolean WM0032WhFeeBaseMstOutUnHitFg = true;
		boolean WM0033WhFeeBaseMstStockUnHitFg = true;
		boolean WM0034WhFeeBaseMstAdjustUnHitFg = true;
		boolean WW0010ArrivalPlanHdUnHitFg = true;
		boolean WW0011ArrivalPlanMsUnHitFg = true;
		boolean WW0012ArrivalHdUnHitFg = true;
		boolean WW0013ArrivaMsUnHitFg = true;
		boolean WW0015StockUnHitFg = true;
		boolean WW0016StockAdjustUnHitFg = true;
		boolean WW0020ShipPlovisionUnHitFg = true;
		boolean WW0025BerthReservationUnHitFg = true;
		boolean WW013101WhFeeInHdUnHitFg = true;
		boolean WW013102WhFeeInMsUnHitFg = true;
		boolean WW013201WhFeeOutHdUnHitFg = true;
		boolean WW013202WhFeeOutMsUnHitFg = true;
		boolean WW013301WhFeeStockHdUnHitFg = true;
		boolean WW013302WhFeeStockMsUnHitFg = true;
		boolean WW013401WhFeeAdjustHdUnHitFg = true;
		boolean WW013402WhFeeAdjustMsUnHitFg = true;
		boolean WW013501WhFeeOtherUnHitFg = true;
		boolean WW014001WhFeeInvoiceUnHitFg = true;

		for(int i=0;i<TableName.length;i++) {
			switch(TableName[i]){
				case "WM0000PARAMETER":
					WM0000PARAMETERUnHitFg = false; 
					break;
				case "WM0010LOCATIONMST":
					WM0010LOCATIONMSTUnHitFg = false; 
					break;
				case "WM0010Supplier":
					WM0010SupplierUnHitFg = false; 
					break;
				case "WM0015_BerthMst":
					WM0015_BerthMstUnHitFg = false; 
					break;
				case "WM0016_PitGlpMST":
					WM0016_PitGlpMSTUnHitFg = false;
					break;
				case "WM0017_PitMST":
					WM0017_PitMSTUnHitFg = false; 
					break;
				case "WM0020AdjustReason":
					WM0020AdjustReasonUnHitFg = false; 
					break;
				case "WM0031WhFeeBaseMstIn":
					WM0031WhFeeBaseMstInUnHitFg = false; 
					break;
				case "WM0032WhFeeBaseMstOut":
					WM0032WhFeeBaseMstOutUnHitFg = false; 
					break;
				case "WM0033WhFeeBaseMstStock":
					WM0033WhFeeBaseMstStockUnHitFg = false; 
					break;
				case "WM0034WhFeeBaseMstAdjust":
					WM0034WhFeeBaseMstAdjustUnHitFg = false; 
					break;
				case "WW0010ArrivalPlanHd":
					WW0010ArrivalPlanHdUnHitFg = false; 
					break;
				case "WW0011ArrivalPlanMs":
					WW0011ArrivalPlanMsUnHitFg = false; 
					break;
				case "WW0012ArrivalHd":
					WW0012ArrivalHdUnHitFg = false; 
					break;
				case "WW0013ArrivaMs":
					WW0013ArrivaMsUnHitFg = false; 
					break;
				case "WW0015Stock":
					WW0015StockUnHitFg = false; 
					break;
				case "WW0016StockAdjust":
					WW0016StockAdjustUnHitFg = false; 
					break;
				case "WW0020ShipPlovision":
					WW0020ShipPlovisionUnHitFg = false; 
					break;
				case "WW0025BerthReservation":
					WW0025BerthReservationUnHitFg = false; 
					break;
				case "WW013101WhFeeInHd":
					WW013101WhFeeInHdUnHitFg = false; 
					break;
				case "WW013102WhFeeInMs":
					WW013102WhFeeInMsUnHitFg = false; 
					break;
				case "WW013201WhFeeOutHd":
					WW013201WhFeeOutHdUnHitFg = false; 
					break;
				case "WW013202WhFeeOutMs":
					WW013202WhFeeOutMsUnHitFg = false; 
					break;
				case "WW013301WhFeeStockHd":
					WW013301WhFeeStockHdUnHitFg = false; 
					break;
				case "WW013302WhFeeStockMs":
					WW013302WhFeeStockMsUnHitFg = false; 
					break;
				case "WW013401WhFeeAdjustHd":
					WW013401WhFeeAdjustHdUnHitFg = false; 
					break;
				case "WW013402WhFeeAdjustMs":
					WW013402WhFeeAdjustMsUnHitFg = false; 
					break;
				case "WW013501WhFeeOther":
					WW013501WhFeeOtherUnHitFg = false; 
					break;
				case "WW014001WhFeeInvoice":
					WW014001WhFeeInvoiceUnHitFg = false; 
					break;
				default:
					break;
			}
		}
		if(WM0000PARAMETERUnHitFg) {
			String sql = WM0000PARAMETERTableCreateSql();
			KickSql("WANKO",sql);
		}
		if(WM0010LOCATIONMSTUnHitFg) {
			String sql = WM0010LOCATIONMSTTableCreateSql();
			KickSql("WANKO",sql);
		}
		if(WM0010SupplierUnHitFg) {
			String sql = WM0010SupplierTableCreateSql();
			KickSql("WANKO",sql);
		}
		if(WM0015_BerthMstUnHitFg) {
			String sql = WM0015_BerthMstTableCreateSql();
			KickSql("WANKO",sql);
		}
		if(WM0016_PitGlpMSTUnHitFg) {
			String sql = WM0016_PitGlpMSTTableCreateSql();
			KickSql("WANKO",sql);
		}
		if(WM0017_PitMSTUnHitFg) {
			String sql = WM0017_PitMSTTableCreateSql();
			KickSql("WANKO",sql);
		}
		if(WM0020AdjustReasonUnHitFg) {
			String sql = WM0020AdjustReasonTableCreateSql();
			KickSql("WANKO",sql);
		}
		if(WM0031WhFeeBaseMstInUnHitFg) {
			String sql = WM0031WhFeeBaseMstInTableCreateSql();
			KickSql("WANKO",sql);
		}
		if(WM0032WhFeeBaseMstOutUnHitFg) {
			String sql = WM0032WhFeeBaseMstOutTableCreateSql();
			KickSql("WANKO",sql);
		}
		if(WM0033WhFeeBaseMstStockUnHitFg) {
			String sql = WM0033WhFeeBaseMstStockTableCreateSql();
			KickSql("WANKO",sql);
		}
		if(WM0034WhFeeBaseMstAdjustUnHitFg) {
			String sql = WM0034WhFeeBaseMstAdjustTableCreateSql();
			KickSql("WANKO",sql);
		}
		if(WW0010ArrivalPlanHdUnHitFg) {
			String sql = WW0010ArrivalPlanHdTableCreateSql();
			KickSql("WANKO",sql);
		}
		if(WW0011ArrivalPlanMsUnHitFg) {
			String sql = WW0011ArrivalPlanMsTableCreateSql();
			KickSql("WANKO",sql);
		}
		if(WW0012ArrivalHdUnHitFg) {
			String sql = WW0012ArrivalHdTableCreateSql();
			KickSql("WANKO",sql);
		}
		if(WW0013ArrivaMsUnHitFg) {
			String sql = WW0013ArrivaMsTableCreateSql();
			KickSql("WANKO",sql);
		}
		if(WW0015StockUnHitFg) {
			String sql = WW0015StockTableCreateSql();
			KickSql("WANKO",sql);
		}
		if(WW0016StockAdjustUnHitFg) {
			String sql = WW0016StockAdjustTableCreateSql();
			KickSql("WANKO",sql);
		}
		if(WW0020ShipPlovisionUnHitFg) {
			String sql = WW0020ShipPlovisionTableCreateSql();
			KickSql("WANKO",sql);
		}
		if(WW0025BerthReservationUnHitFg) {
			String sql = WW0025BerthReservationTableCreateSql();
			KickSql("WANKO",sql);
		}
		if(WW013101WhFeeInHdUnHitFg) {
			String sql = WW013101WhFeeInHdTableCreateSql();
			KickSql("WANKO",sql);
		}
		if(WW013102WhFeeInMsUnHitFg) {
			String sql = WW013102WhFeeInMsTableCreateSql();
			KickSql("WANKO",sql);
		}
		if(WW013201WhFeeOutHdUnHitFg) {
			String sql = WW013201WhFeeOutHdTableCreateSql();
			KickSql("WANKO",sql);
		}
		if(WW013202WhFeeOutMsUnHitFg) {
			String sql = WW013202WhFeeOutMsTableCreateSql();
			KickSql("WANKO",sql);
		}
		if(WW013301WhFeeStockHdUnHitFg) {
			String sql = WW013301WhFeeStockHdTableCreateSql();
			KickSql("WANKO",sql);
		}
		if(WW013302WhFeeStockMsUnHitFg) {
			String sql = WW013302WhFeeStockMsTableCreateSql();
			KickSql("WANKO",sql);
		}
		if(WW013401WhFeeAdjustHdUnHitFg) {
			String sql = WW013401WhFeeAdjustHdTableCreateSql();
			KickSql("WANKO",sql);
		}
		if(WW013402WhFeeAdjustMsUnHitFg) {
			String sql = WW013402WhFeeAdjustMsTableCreateSql();
			KickSql("WANKO",sql);
		}
		if(WW013501WhFeeOtherUnHitFg) {
			String sql = WW013501WhFeeOtherTableCreateSql();
			KickSql("WANKO",sql);
		}
		if(WW014001WhFeeInvoiceUnHitFg) {
			String sql = WW014001WhFeeInvoiceTableCreateSql();
			KickSql("WANKO",sql);
		}
		
		//テーブルのフィールドチェック ⇒ 必要フィールドなければ作る
		String[] ColumnList = null;
		String[] NeedColmn = null;
		ArrayList<String> NoHitColumn = null;
		
		ColumnList = ColumnList("WANKO","WM0000PARAMETER");
		NeedColmn = new String[29];

		NeedColmn[ 0] = "ClWh";
		NeedColmn[ 1] = "ClCd";
		NeedColmn[ 2] = "ParaCd";
		NeedColmn[ 3] = "ParaCdSeq";
		NeedColmn[ 4] = "ParaName";
		NeedColmn[ 5] = "ParaTxt01";
		NeedColmn[ 6] = "ParaTxt02";
		NeedColmn[ 7] = "ParaTxt03";
		NeedColmn[ 8] = "ParaTxt04";
		NeedColmn[ 9] = "ParaTxt05";
		NeedColmn[10] = "ParaTxt06";
		NeedColmn[11] = "ParaTxt07";
		NeedColmn[12] = "ParaTxt08";
		NeedColmn[13] = "ParaTxt09";
		NeedColmn[14] = "ParaTxt10";
		NeedColmn[15] = "ParaInt01";
		NeedColmn[16] = "ParaInt02";
		NeedColmn[17] = "ParaInt03";
		NeedColmn[18] = "ParaInt04";
		NeedColmn[19] = "ParaInt05";
		NeedColmn[20] = "ParaInt06";
		NeedColmn[21] = "ParaInt07";
		NeedColmn[22] = "ParaInt08";
		NeedColmn[23] = "ParaInt09";
		NeedColmn[24] = "ParaInt10";
		NeedColmn[25] = "EntryDate";
		NeedColmn[26] = "UpdateDate";
		NeedColmn[27] = "EntryUser";
		NeedColmn[28] = "UpdateUser";
		
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
		if(null!=NoHitColumn && 0<NoHitColumn.size()) {
			String sql = WM0000PARAMETERAltherTableSql(NoHitColumn);
			KickSql("WANKO",sql);
		}
		
		ColumnList = ColumnList("WANKO","WM0010LOCATIONMST");
		NeedColmn = new String[8];

		NeedColmn[ 0] = "ClCd";
		NeedColmn[ 1] = "WhCd";
		NeedColmn[ 2] = "Loc";
		NeedColmn[ 3] = "EntryDate";
		NeedColmn[ 4] = "UpdateDate";
		NeedColmn[ 5] = "EntryUser";
		NeedColmn[ 6] = "UpdateUser";
		NeedColmn[ 7] = "Type";
				
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
		if(null!=NoHitColumn && 0<NoHitColumn.size()) {
			String sql = WM0010LOCATIONMSTAltherTableSql(NoHitColumn);
			KickSql("WANKO",sql);
		}

		
	}
	
	private static String WM0000PARAMETERTableCreateSql() {
		//パラメータマスタテーブルを作る
		String sql = ""
				+"CREATE TABLE `WM0000PARAMETER` ("
				+"  `ClWh` varchar(20) NOT NULL,"
				+"  `ClCd` varchar(20) NOT NULL,"
				+"  `ParaCd` varchar(20) NOT NULL,"
				+"  `ParaCdSeq` int(11) NOT NULL,"
				+"  `ParaName` varchar(200) DEFAULT '',"
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
				+"  PRIMARY KEY (`ClWh`,`ClCd`,`ParaCd`,`ParaCdSeq`)"
				+") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='WANKOパラメータマスタ';";
		return sql;
	}
	
	private static String WM0000PARAMETERAltherTableSql(ArrayList<String> NoHitColumn){
		String sql = ""
				+"ALTER TABLE "+A00000Main.MySqlDefaultSchemaWANKO+".WM0000PARAMETER";
		for(int i=0;i<NoHitColumn.size();i++) {
			if(0<i) {sql = sql + ",";}
			switch(NoHitColumn.get(i)) {
				case "ClWh":
					sql = sql + " ADD ClWh varchar(20) NOT NULL";
					break;
				case "ClCd":
					sql = sql + " ADD ClCd varchar(20) NOT NULL";
					break;
				case "ParaCd":
					sql = sql + " ADD ParaCd varchar(20) NOT NULL";
					break;
				case "ParaCdSeq":
					sql = sql + " ADD ParaCdSeq int(11) NOT NULL";
					break;
				case "ParaName":
					sql = sql + " ADD ParaName varchar(200) DEFAULT ''";
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
		sql = sql + ";";
		return sql;
	}
	
	
	private static String WM0010LOCATIONMSTTableCreateSql() {
		//ロケーションマスタテーブルを作る
		String sql = ""
				+"CREATE TABLE `WM0010LOCATIONMST` ("
				+"  `ClCd` varchar(20) NOT NULL,"
				+"  `WhCd` varchar(20) NOT NULL,"
				+"  `Loc` varchar(20) NOT NULL,"
				+"  `EntryDate` datetime DEFAULT NULL,"
				+"  `UpdateDate` datetime DEFAULT NULL,"
				+"  `EntryUser` varchar(50) NOT NULL,"
				+"  `UpdateUser` varchar(50) NOT NULL,"
				+"  `Type` float DEFAULT '0',"
				+"  PRIMARY KEY (`ClCd`,`WhCd`,`Loc`)"
				+") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ロケーションマスタ';";
		return sql;
	}

	private static String WM0010LOCATIONMSTAltherTableSql(ArrayList<String> NoHitColumn){
		String sql = ""
				+"ALTER TABLE "+A00000Main.MySqlDefaultSchemaWANKO+".WM0010LOCATIONMST";
		for(int i=0;i<NoHitColumn.size();i++) {
			if(0<i) {sql = sql + ",";}
			switch(NoHitColumn.get(i)) {
				case "ClCd":
					sql = sql + " ADD ClCd varchar(20) NOT NULL";
					break;
				case "WhCd":
					sql = sql + " ADD WhCd varchar(20) NOT NULL";
					break;
				case "Loc":
					sql = sql + " ADD Loc varchar(20) NOT NULL";
					break;
				case "EntryDate":
					sql = sql + " ADD EntryDate datetime DEFAULT NULL";
					break;
				case "UpdateDate":
					sql = sql + " ADD UpdateDate datetime DEFAULT NULL";
					break;
				case "EntryUser":
					sql = sql + " ADD EntryUser varchar(50) NOT NULL";
					break;
				case "UpdateUser":
					sql = sql + " ADD UpdateUser varchar(50) NOT NULL";
					break;
				case "Type":
					sql = sql + " ADD Type float DEFAULT '0'";
					break;
				default:
					break;
			}
		}
		sql = sql + ";";
		return sql;
	}
	

	private static String WM0010SupplierTableCreateSql() {
		//仕入先マスタテーブルを作る
		String sql = ""
				+"CREATE TABLE `WM0010Supplier` ("
				+"  `ClWh` varchar(20) NOT NULL,"
				+"  `ClCd` varchar(20) NOT NULL,"
				+"  `SPCd` varchar(20) NOT NULL,"
				+"  `SPName01` varchar(50) DEFAULT NULL,"
				+"  `SPName02` varchar(50) DEFAULT NULL,"
				+"  `SPName03` varchar(50) DEFAULT NULL,"
				+"  `SPPost` varchar(10) DEFAULT NULL,"
				+"  `SPAdd01` varchar(100) DEFAULT NULL,"
				+"  `SPAdd02` varchar(100) DEFAULT NULL,"
				+"  `SPAdd03` varchar(100) DEFAULT NULL,"
				+"  `SPTel` varchar(20) DEFAULT NULL,"
				+"  `SPFax` varchar(20) DEFAULT NULL,"
				+"  `SPMail` varchar(200) DEFAULT NULL,"
				+"  `Com01` varchar(100) DEFAULT NULL,"
				+"  `Com02` varchar(100) DEFAULT NULL,"
				+"  `Com03` varchar(100) DEFAULT NULL,"
				+"  `PTMSCDBMN` varchar(12) DEFAULT NULL,"
				+"  `PTMSCDNINUSHI` varchar(12) DEFAULT NULL,"
				+"  `PaySite` int(11) DEFAULT '1',"
				+"  `PayDate` int(11) DEFAULT '99',"
				+"  `ShimeDate` int(11) DEFAULT '99',"
				+"  `EntryDate` datetime DEFAULT NULL,"
				+"  `UpdateDate` datetime DEFAULT NULL,"
				+"  `EntryUser` varchar(50) DEFAULT NULL,"
				+"  `UpdateUser` varchar(50) DEFAULT NULL,"
				+"  `DECD` varchar(20) DEFAULT NULL,"
				+"  `DepartmentCd` varchar(20) DEFAULT NULL,"
				+"  PRIMARY KEY (`ClWh`,`ClCd`,`SPCd`)"
				+") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='仕入先マスタ';";
		return sql;
	}
	
	private static String WM0015_BerthMstTableCreateSql() {
		//出荷バースマスタテーブルを作る
		String sql = ""
				+"CREATE TABLE `WM0015_BerthMst` ("
				+"  `WhCd` varchar(20) NOT NULL,"
				+"  `BerthCd` varchar(20) NOT NULL,"
				+"  `BerthName` varchar(100) DEFAULT NULL,"
				+"  `ENTRY_DATE` datetime DEFAULT NULL,"
				+"  `UPDATE_DATE` datetime DEFAULT NULL,"
				+"  `ENTRY_USER` varchar(50) DEFAULT NULL,"
				+"  `UPDATE_USER` varchar(50) DEFAULT NULL,"
				+"  PRIMARY KEY (`WhCd`,`BerthCd`)"
				+") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='出荷バースマスタ';";
		return sql;
	}
	private static String WM0016_PitGlpMSTTableCreateSql() {
		//仕分けピットグループマスタテーブルを作る
		String sql = ""
				+"CREATE TABLE `WM0016_PitGlpMST` ("
			  	+"  `WhCd` varchar(20) NOT NULL COMMENT '出発倉庫コード',"
			  	+"  `PitGlpCd` varchar(20) NOT NULL COMMENT 'ピットグループコード',"
			  	+"  `PitGlpName` varchar(100) DEFAULT NULL COMMENT 'ピットグループ名',"
			  	+"  `EntryDate` datetime DEFAULT NULL COMMENT '登録日',"
			  	+"  `UpdateDate` datetime DEFAULT NULL COMMENT '更新日',"
			  	+"  `EntryUser` varchar(50) DEFAULT NULL COMMENT '登録者',"
			  	+"  `UpdateUser` varchar(50) DEFAULT NULL COMMENT '更新者',"
			  	+"  PRIMARY KEY (`WhCd`,`PitGlpCd`)"
				+")  ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='仕分けピットグループマスタ';";
		return sql;
	}
	
	private static String WM0017_PitMSTTableCreateSql() {
		//仕分けピットマスタテーブルを作る
		String sql = ""
				+"CREATE TABLE `WM0017_PitMST` ("
				+"  `WhCd` varchar(20) NOT NULL COMMENT '出発倉庫コード',"
				+"  `PitGlpCd` varchar(20) NOT NULL COMMENT 'ピットグループコード',"
				+"  `PitCd` varchar(20) NOT NULL COMMENT 'ピットコード',"
				+"  `PitName` varchar(100) DEFAULT NULL COMMENT 'ピット名',"
				+"  `Status` int(11) NOT NULL DEFAULT '0' COMMENT 'ピット占有状態',"
				+"  `TakeCoursePlanDate` datetime DEFAULT NULL COMMENT '占有中コース出荷予定日',"
				+"  `TakeCourseGpCd` varchar(20) DEFAULT NULL COMMENT '占有中コースグループコード',"
				+"  `TakeCourseCd` varchar(20) DEFAULT NULL COMMENT '占有中コースCd',"
				+"  `TakeCourseEda` int(11) NOT NULL DEFAULT '0' COMMENT '占有中コース枝番',"
				+"  `TakeCourseDeliveryCd` varchar(20) DEFAULT NULL COMMENT '占有中コース指定届先CD',"
				+"  `TakeCourseDptCd` varchar(20) DEFAULT NULL COMMENT '占有中コース指定届先部署CD',"
				+"  `TakeCourse01PlanDate` datetime DEFAULT NULL COMMENT '待機コース01出荷予定日',"
				+"  `TakeCourse01GpCd` varchar(20) DEFAULT NULL COMMENT '待機コース01グループコード',"
				+"  `TakeCourse01Cd` varchar(20) DEFAULT NULL COMMENT '待機コース01Cd',"
				+"  `TakeCourse01Eda` int(11) DEFAULT '0' COMMENT '待機コース01枝番',"
				+"  `TakeCourse01DeliveryCd` varchar(20) DEFAULT NULL COMMENT '待機コース01指定届先CD',"
				+"  `TakeCourse01DptCd` varchar(20) DEFAULT NULL COMMENT '待機コース01指定届先部署CD',"
				+"  `TakeCourse02PlanDate` datetime DEFAULT NULL COMMENT '待機コース02出荷予定日',"
				+"  `TakeCourse02GpCd` varchar(20) DEFAULT NULL COMMENT '待機コース02グループコード',"
				+"  `TakeCourse02Cd` varchar(20) DEFAULT NULL COMMENT '待機コース02Cd',"
				+"  `TakeCourse02Eda` int(11) DEFAULT '0' COMMENT '待機コース02枝番',"
				+"  `TakeCourse02DeliveryCd` varchar(20) DEFAULT NULL COMMENT '待機コース02指定届先CD',"
				+"  `TakeCourse02DptCd` varchar(20) DEFAULT NULL COMMENT '待機コース02指定届先部署CD',"
				+"  `EntryDate` datetime DEFAULT NULL COMMENT '登録日',"
				+"  `UpdateDate` datetime DEFAULT NULL COMMENT '更新日',"
				+"  `EntryUser` varchar(50) DEFAULT NULL COMMENT '登録者',"
				+"  `UpdateUser` varchar(50) DEFAULT NULL COMMENT '更新者',"
				+"  PRIMARY KEY (`WhCd`,`PitGlpCd`,`PitCd`)"
				+") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='仕分けピットマスタ';";
		return sql;
	}
	
	private static String WM0020AdjustReasonTableCreateSql() {
		//調整理由マスタテーブルを作る
		String sql = ""
				+"CREATE TABLE `WM0020AdjustReason` ("
				+"  `ClCd` varchar(20) NOT NULL,"
				+"  `WhCd` varchar(20) NOT NULL,"
				+"  `AdjustReasonCd` varchar(20) NOT NULL,"
				+"  `AdjustReasonName` varchar(100) DEFAULT NULL,"
				+"  `EntryDate` datetime DEFAULT NULL,"
				+"  `UpdateDate` datetime DEFAULT NULL,"
				+"  `EntryUser` varchar(50) DEFAULT NULL,"
				+"  `UpdateUser` varchar(50) DEFAULT NULL,"
				+"  PRIMARY KEY (`ClCd`,`WhCd`,`AdjustReasonCd`)"
				+") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='調整理由マスタ';";
		return sql;
	}
	
	private static String WM0031WhFeeBaseMstInTableCreateSql() {
		//倉庫入荷料単価マスタテーブルを作る
		String sql = ""
				+"CREATE TABLE `WM0031WhFeeBaseMstIn` ("
				+"  `ClCd` varchar(20) NOT NULL DEFAULT '' COMMENT '荷主コード',"
				+"  `WhCd` varchar(20) NOT NULL DEFAULT '' COMMENT '倉庫コード',"
				+"  `ArrivalFeeCd` varchar(20) NOT NULL DEFAULT '' COMMENT '入荷料金コード',"
				+"  `ArrivalFeeName` varchar(100) DEFAULT NULL COMMENT '入荷料金名',"
				+"  `DeliveryTypeCd01` varchar(20) DEFAULT NULL COMMENT '運送タイプコード01',"
				+"  `DeliveryTypeCd02` varchar(20) DEFAULT NULL COMMENT '運送タイプコード02',"
				+"  `DeliveryTypeCd03` varchar(20) DEFAULT NULL COMMENT '運送タイプコード03',"
				+"  `DeliveryTypeCd04` varchar(20) DEFAULT NULL COMMENT '運送タイプコード04',"
				+"  `DeliveryTypeCd05` varchar(20) DEFAULT NULL COMMENT '運送タイプコード05',"
				+"  `TildFG` varchar(20) DEFAULT NULL COMMENT '温度区分',"
				+"  `CategoryCd` varchar(20) DEFAULT NULL COMMENT '商品カテゴリCD',"
				+"  `ShimeDate` int(11) NOT NULL DEFAULT '99' COMMENT '締め日',"
				+"  `ArrivalBaseFee` int(11) NOT NULL DEFAULT '0' COMMENT '入荷基本料金',"
				+"  `ArrivalSlipFee` int(11) NOT NULL DEFAULT '0' COMMENT '入荷伝票基本料金',"
				+"  `ArrivalUnitFee` float NOT NULL DEFAULT '0' COMMENT '入荷料単価',"
				+"  `FeeUnit` int(11) NOT NULL DEFAULT '0' COMMENT '課金単位',"
				+"  `SummaryFg` int(11) NOT NULL DEFAULT '0' COMMENT '集計区分',"
				+"  `EntryDate` datetime DEFAULT NULL COMMENT '登録日',"
				+"  `UpdateDate` datetime DEFAULT NULL COMMENT '更新日',"
				+"  `EntryUser` varchar(50) DEFAULT NULL COMMENT '登録者',"
				+"  `UpdateUser` varchar(50) DEFAULT NULL COMMENT '更新者',"
				+"  PRIMARY KEY (`ClCd`,`WhCd`,`ArrivalFeeCd`)"
				+") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='倉庫入荷料単価マスタ';";
		return sql;
	}
	
	private static String WM0032WhFeeBaseMstOutTableCreateSql() {
		//倉庫出荷料単価マスタテーブルを作る
		String sql = ""
				+"CREATE TABLE `WM0032WhFeeBaseMstOut` ("
				+"  `ClCd` varchar(20) NOT NULL DEFAULT '' COMMENT '荷主コード',"
				+"  `WhCd` varchar(20) NOT NULL DEFAULT '' COMMENT '倉庫コード',"
				+"  `ShipFeeCd` varchar(20) NOT NULL DEFAULT '' COMMENT '出荷料金コード',"
				+"  `ShipFeeName` varchar(100) DEFAULT NULL COMMENT '出荷料金名',"
				+"  `DeliveryTypeCd01` varchar(20) DEFAULT NULL COMMENT '運送タイプコード01',"
				+"  `DeliveryTypeCd02` varchar(20) DEFAULT NULL COMMENT '運送タイプコード02',"
				+"  `DeliveryTypeCd03` varchar(20) DEFAULT NULL COMMENT '運送タイプコード03',"
				+"  `DeliveryTypeCd04` varchar(20) DEFAULT NULL COMMENT '運送タイプコード04',"
				+"  `DeliveryTypeCd05` varchar(20) DEFAULT NULL COMMENT '運送タイプコード05',"
				+"  `TildFG` varchar(20) DEFAULT NULL COMMENT '温度区分',"
				+"  `CategoryCd` varchar(20) DEFAULT NULL COMMENT '商品カテゴリCD',"
				+"  `ShimeDate` int(11) NOT NULL DEFAULT '99' COMMENT '締め日',"
				+"  `ShipBaseFee` int(11) NOT NULL DEFAULT '0' COMMENT '出荷基本料金',"
				+"  `ShipSlipFee` int(11) NOT NULL DEFAULT '0' COMMENT '出荷伝票基本料金',"
				+"  `ShipUnitFee` float NOT NULL DEFAULT '0' COMMENT '出荷料単価',"
				+"  `FeeUnit` int(11) NOT NULL DEFAULT '0' COMMENT '課金単位',"
				+"  `SummaryFg` int(11) NOT NULL DEFAULT '0' COMMENT '集計区分',"
				+"  `EntryDate` timestamp NULL DEFAULT NULL COMMENT '登録日',"
				+"  `UpdateDate` timestamp NULL DEFAULT NULL COMMENT '更新日',"
				+"  `EntryUser` varchar(50) DEFAULT NULL COMMENT '登録者',"
				+"  `UpdateUser` varchar(50) DEFAULT NULL COMMENT '更新者',"
				+"  PRIMARY KEY (`ClCd`,`WhCd`,`ShipFeeCd`)"
				+") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='倉庫出荷料単価マスタ';";
		return sql;
	}
	
	private static String WM0033WhFeeBaseMstStockTableCreateSql() {
		//倉庫保管料単価マスタテーブルを作る
		String sql = ""
				+"CREATE TABLE `WM0033WhFeeBaseMstStock` ("
				+"  `ClCd` varchar(20) NOT NULL DEFAULT '' COMMENT '荷主コード',"
				+"  `WhCd` varchar(20) NOT NULL DEFAULT '' COMMENT '倉庫コード',"
				+"  `StockFeeCd` varchar(20) NOT NULL DEFAULT '' COMMENT '保管料金コード',"
				+"  `StockFeeName` varchar(100) DEFAULT NULL COMMENT '保管料金名',"
				+"  `DeliveryTypeCd01` varchar(20) DEFAULT NULL COMMENT '運送タイプコード01',"
				+"  `DeliveryTypeCd02` varchar(20) DEFAULT NULL COMMENT '運送タイプコード02',"
				+"  `DeliveryTypeCd03` varchar(20) DEFAULT NULL COMMENT '運送タイプコード03',"
				+"  `DeliveryTypeCd04` varchar(20) DEFAULT NULL COMMENT '運送タイプコード04',"
				+"  `DeliveryTypeCd05` varchar(20) DEFAULT NULL COMMENT '運送タイプコード05',"
				+"  `TildFG` varchar(20) DEFAULT NULL COMMENT '温度区分',"
				+"  `CategoryCd` varchar(20) DEFAULT NULL COMMENT '商品カテゴリCD',"
				+"  `CuttingDate` varchar(100) DEFAULT NULL COMMENT '期締め設定',"
				+"  `ShimeDate` int(11) NOT NULL DEFAULT '99' COMMENT '締め日',"
				+"  `StockBaseFee` int(11) NOT NULL DEFAULT '0' COMMENT '保管基本料金',"
				+"  `StockUnitFee` float NOT NULL DEFAULT '0' COMMENT '保管料単価',"
				+"  `FeeUnit` int(11) NOT NULL DEFAULT '0' COMMENT '課金単位',"
				+"  `SummaryFg` int(11) NOT NULL DEFAULT '0' COMMENT '集計区分',"
				+"  `EntryDate` datetime DEFAULT NULL COMMENT '登録日',"
				+"  `UpdateDate` datetime DEFAULT NULL COMMENT '更新日',"
				+"  `EntryUser` varchar(50) DEFAULT NULL COMMENT '登録者',"
				+"  `UpdateUser` varchar(50) DEFAULT NULL COMMENT '更新者',"
				+"  PRIMARY KEY (`ClCd`,`WhCd`,`StockFeeCd`)"
				+") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='倉庫保管料単価マスタ';";
		return sql;
	}
	
	private static String WM0034WhFeeBaseMstAdjustTableCreateSql() {
		//倉庫在庫調整料単価マスタテーブルを作る
		String sql = ""
				+"CREATE TABLE `WM0034WhFeeBaseMstAdjust` ("
				+"  `ClCd` varchar(20) NOT NULL DEFAULT '' COMMENT '荷主コード',"
				+"  `WhCd` varchar(20) NOT NULL DEFAULT '' COMMENT '倉庫コード',"
				+"  `AdjustFeeCd` varchar(20) NOT NULL DEFAULT '' COMMENT '調整料金コード',"
				+"  `AdjustFeeName` varchar(100) DEFAULT NULL COMMENT '調整料金名',"
				+"  `DeliveryTypeCd01` varchar(20) DEFAULT NULL COMMENT '運送タイプコード01',"
				+"  `DeliveryTypeCd02` varchar(20) DEFAULT NULL COMMENT '運送タイプコード02',"
				+"  `DeliveryTypeCd03` varchar(20) DEFAULT NULL COMMENT '運送タイプコード03',"
				+"  `DeliveryTypeCd04` varchar(20) DEFAULT NULL COMMENT '運送タイプコード04',"
				+"  `DeliveryTypeCd05` varchar(20) DEFAULT NULL COMMENT '運送タイプコード05',"
				+"  `TildFG` varchar(20) DEFAULT NULL COMMENT '温度区分',"
				+"  `CategoryCd` varchar(20) DEFAULT NULL COMMENT '商品カテゴリCD',"
				+"  `AdjustReasonCd` varchar(20) DEFAULT NULL COMMENT '調整理由CD',"
				+"  `ShimeDate` int(11) NOT NULL DEFAULT '99' COMMENT '締め日',"
				+"  `AdjustBaseFee` int(11) NOT NULL DEFAULT '0' COMMENT '調整基本料金',"
				+"  `AdjustUnitFee` float NOT NULL DEFAULT '0' COMMENT '調整料単価',"
				+"  `FeeUnit` int(11) NOT NULL DEFAULT '0' COMMENT '課金単位',"
				+"  `SummaryFg` int(11) NOT NULL DEFAULT '0' COMMENT '集計区分',"
				+"  `EntryDate` datetime DEFAULT NULL COMMENT '登録日',"
				+"  `UpdateDate` datetime DEFAULT NULL COMMENT '更新日',"
				+"  `EntryUser` varchar(50) DEFAULT NULL COMMENT '登録者',"
				+"  `UpdateUser` varchar(50) DEFAULT NULL COMMENT '更新者',"
				+"  PRIMARY KEY (`ClCd`,`WhCd`,`AdjustFeeCd`)"
				+") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='倉庫在庫調整料単価マスタ';";
		return sql;
	}
	
	private static String WW0010ArrivalPlanHdTableCreateSql() {
		//入荷予定ヘッダテーブルを作る
		String sql = ""
				+"CREATE TABLE `WW0010ArrivalPlanHd` ("
				+"  `ClWh` varchar(20) NOT NULL,"
				+"  `ClCd` varchar(20) NOT NULL,"
				+"  `ArrNo` varchar(50) NOT NULL,"
				+"  `ClArrNo` varchar(50) DEFAULT NULL,"
				+"  `PlanDate` datetime DEFAULT NULL,"
				+"  `ActualDate` datetime DEFAULT NULL,"
				+"  `SpCd` varchar(20) DEFAULT NULL,"
				+"  `SpName01` varchar(100) DEFAULT NULL,"
				+"  `SpName02` varchar(100) DEFAULT NULL,"
				+"  `SpName03` varchar(100) DEFAULT NULL,"
				+"  `SpPost` varchar(20) DEFAULT NULL,"
				+"  `SpAdd01` varchar(100) DEFAULT NULL,"
				+"  `SpAdd02` varchar(100) DEFAULT NULL,"
				+"  `SpAdd03` varchar(100) DEFAULT NULL,"
				+"  `SpTel` varchar(20) DEFAULT NULL,"
				+"  `ArCom01` varchar(100) DEFAULT NULL,"
				+"  `ArCom02` varchar(100) DEFAULT NULL,"
				+"  `ArCom03` varchar(100) DEFAULT NULL,"
				+"  `EntryDate` datetime DEFAULT NULL,"
				+"  `UpdateDate` datetime DEFAULT NULL,"
				+"  `EntryUser` varchar(50) DEFAULT NULL,"
				+"  `UpdateUser` varchar(50) DEFAULT NULL,"
				+"  `FixFg` int(11) DEFAULT '0',"
				+"  PRIMARY KEY (`ClWh`,`ClCd`,`ArrNo`)"
				+") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='入荷予定ヘッダ';";
		return sql;
	}
	
	private static String WW0011ArrivalPlanMsTableCreateSql() {
		//入荷予定明細テーブルを作る
		String sql = ""
				+"CREATE TABLE `WW0011ArrivalPlanMs` ("
				+"  `ClWh` varchar(20) NOT NULL,"
				+"  `ClCd` varchar(20) NOT NULL,"
				+"  `ArrNo` varchar(20) NOT NULL,"
				+"  `MsNo` int(11) NOT NULL DEFAULT '1',"
				+"  `ItemCd` varchar(20) DEFAULT NULL,"
				+"  `ClItemCd` varchar(20) DEFAULT NULL,"
				+"  `JanCd` varchar(20) DEFAULT NULL,"
				+"  `ItemMdNo` varchar(20) DEFAULT NULL,"
				+"  `ItemName` varchar(100) DEFAULT NULL,"
				+"  `lot` varchar(50) DEFAULT NULL,"
				+"  `ExpDate` datetime DEFAULT NULL,"
				+"  `PlanQty` int(11) DEFAULT '0',"
				+"  `ActualQty` int(11) DEFAULT '0',"
				+"  `ActualDate` datetime DEFAULT NULL,"
				+"  `Com01` varchar(200) DEFAULT NULL,"
				+"  `Com02` varchar(200) DEFAULT NULL,"
				+"  `EntryDate` datetime DEFAULT NULL,"
				+"  `UpdateDate` datetime DEFAULT NULL,"
				+"  `EntryUser` varchar(50) DEFAULT NULL,"
				+"  `UpdateUser` varchar(50) DEFAULT NULL,"
				+"  PRIMARY KEY (`ClWh`,`ClCd`,`ArrNo`,`MsNo`)"
				+") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='入荷予定明細';";
		return sql;
	}
	private static String WW0012ArrivalHdTableCreateSql() {
		//入荷実績ヘッダテーブルを作る
		String sql = ""
				+"CREATE TABLE `WW0012ArrivalHd` ("
				+"  `ClWh` varchar(20) NOT NULL,"
				+"  `ClCd` varchar(20) NOT NULL,"
				+"  `ArrNo` varchar(50) NOT NULL,"
				+"  `ArrCount` int(11) NOT NULL DEFAULT '0',"
				+"  `ClArrNo` varchar(50) DEFAULT NULL,"
				+"  `PlanDate` datetime DEFAULT NULL,"
				+"  `ActualDate` datetime DEFAULT NULL,"
				+"  `SpCd` varchar(20) DEFAULT NULL,"
				+"  `SpName01` varchar(50) DEFAULT NULL,"
				+"  `SpName02` varchar(50) DEFAULT NULL,"
				+"  `SpName03` varchar(50) DEFAULT NULL,"
				+"  `SpPost` varchar(20) DEFAULT NULL,"
				+"  `SpAdd01` varchar(100) DEFAULT NULL,"
				+"  `SpAdd02` varchar(100) DEFAULT NULL,"
				+"  `SpAdd03` varchar(100) DEFAULT NULL,"
				+"  `SpTel` varchar(20) DEFAULT NULL,"
				+"  `ArCom01` varchar(100) DEFAULT NULL,"
				+"  `ArCom02` varchar(100) DEFAULT NULL,"
				+"  `ArCom03` varchar(100) DEFAULT NULL,"
				+"  `EntryDate` datetime DEFAULT NULL,"
				+"  `UpdateDate` datetime DEFAULT NULL,"
				+"  `EntryUser` varchar(50) DEFAULT NULL,"
				+"  `UpdateUser` varchar(50) DEFAULT NULL,"
				+"  PRIMARY KEY (`ClWh`,`ClCd`,`ArrNo`,`ArrCount`)"
				+") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='入荷実績ヘッダ';";
		return sql;
	}
	
	private static String WW0013ArrivaMsTableCreateSql() {
		//入荷実績明細テーブルを作る
		String sql = ""
				+"CREATE TABLE `WW0013ArrivaMs` ("
				+"  `ClWh` varchar(20) NOT NULL,"
				+"  `ClCd` varchar(20) NOT NULL,"
				+"  `ArrNo` varchar(20) NOT NULL,"
				+"  `ArrCount` int(11) NOT NULL DEFAULT '0',"
				+"  `MsNo` int(11) NOT NULL DEFAULT '1',"
				+"  `MsSeq` int(11) NOT NULL DEFAULT '0',"
				+"  `ItemCd` varchar(20) DEFAULT NULL,"
				+"  `ClItemCd` varchar(20) DEFAULT NULL,"
				+"  `JanCd` varchar(20) DEFAULT NULL,"
				+"  `ItemMdNo` varchar(20) DEFAULT NULL,"
				+"  `ItemName` varchar(100) DEFAULT NULL,"
				+"  `Lot` varchar(50) DEFAULT NULL,"
				+"  `ExpDate` datetime DEFAULT NULL,"
				+"  `PlanQty` int(11) DEFAULT '0',"
				+"  `ActualQty` int(11) DEFAULT '0',"
				+"  `ActualDate` datetime DEFAULT NULL,"
				+"  `Com01` varchar(200) DEFAULT NULL,"
				+"  `Com02` varchar(200) DEFAULT NULL,"
				+"  `EntryDate` datetime DEFAULT NULL,"
				+"  `UpdateDate` datetime DEFAULT NULL,"
				+"  `EntryUser` varchar(50) DEFAULT NULL,"
				+"  `UpdateUser` varchar(50) DEFAULT NULL,"
				+"  PRIMARY KEY (`ClWh`,`ClCd`,`ArrNo`,`ArrCount`,`MsNo`,`MsSeq`)"
				+") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='入荷実績明細';";
		return sql;
	}
	private static String WW0015StockTableCreateSql() {
		//在庫テーブルを作る
		String sql = ""
				+"CREATE TABLE `WW0015Stock` ("
				+"  `ClCd` varchar(20) NOT NULL,"
				+"  `WhCd` varchar(20) NOT NULL,"
				+"  `Loc` varchar(15) NOT NULL,"
				+"  `ItemCd` varchar(20) NOT NULL,"
				+"  `Lot` varchar(20) NOT NULL,"
				+"  `Expdate` datetime NOT NULL,"
				+"  `ActualDate` datetime NOT NULL,"
				+"  `Qty` int(11) DEFAULT '0',"
				+"  `ShipPlanQty` int(11) DEFAULT '0',"
				+"  `PossibleQty` int(11) DEFAULT '0',"
				+"  `ItemName` varchar(100) DEFAULT NULL,"
				+"  `ClItemCd` varchar(20) DEFAULT NULL,"
				+"  `JanCd` varchar(20) DEFAULT NULL,"
				+"  `ItemMdNo` varchar(20) DEFAULT NULL,"
				+"  `EntryDate` datetime DEFAULT NULL,"
				+"  `UpdateDate` datetime DEFAULT NULL,"
				+"  `EntryUser` varchar(50) DEFAULT NULL,"
				+"  `UpdateUser` varchar(50) DEFAULT NULL,"
				+"  PRIMARY KEY (`ClCd`,`WhCd`,`Loc`,`ItemCd`,`Lot`,`Expdate`,`ActualDate`)"
				+") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='在庫';";
		return sql;
	}
	
	private static String WW0016StockAdjustTableCreateSql() {
		//在庫調整テーブルを作る
		String sql = ""
				+"CREATE TABLE `WW0016StockAdjust` ("
				+"  `ClCd` varchar(20) NOT NULL,"
				+"  `WhCd` varchar(20) NOT NULL,"
				+"  `AdjustNo` int(11) NOT NULL,"
				+"  `AdjustReasonCd` varchar(20) DEFAULT NULL,"
				+"  `AdjustReasonName` varchar(100) DEFAULT NULL,"
				+"  `Adjustdate` datetime DEFAULT NULL,"
				+"  `Loc` varchar(15) DEFAULT NULL,"
				+"  `ItemCd` varchar(20) DEFAULT NULL,"
				+"  `ItemName` varchar(100) DEFAULT NULL,"
				+"  `Lot` varchar(20) DEFAULT NULL,"
				+"  `ExpDate` datetime DEFAULT NULL,"
				+"  `ActualDate` datetime DEFAULT NULL,"
				+"  `BeforeQty` int(11) DEFAULT '0',"
				+"  `ShipPlanQty` int(11) DEFAULT '0',"
				+"  `PossibleQty` int(11) DEFAULT '0',"
				+"  `AdjustQty` int(11) DEFAULT '0',"
				+"  `AdjustCom01` varchar(100) DEFAULT NULL,"
				+"  `AdjustCom02` varchar(100) DEFAULT NULL,"
				+"  `AdjustCom03` varchar(100) DEFAULT NULL,"
				+"  `AfterQty` int(11) DEFAULT '0',"
				+"  `EntryDate` datetime DEFAULT NULL,"
				+"  `UpdateDate` datetime DEFAULT NULL,"
				+"  `EntryUser` varchar(50) DEFAULT NULL,"
				+"  `UpdateUser` varchar(50) DEFAULT NULL,"
				+"  PRIMARY KEY (`ClCd`,`WhCd`,`AdjustNo`)"
				+") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='在庫調整';";
		return sql;
	}
	
	private static String WW0020ShipPlovisionTableCreateSql() {
		//引当結果テーブルを作る
		String sql = ""
				+"CREATE TABLE `WW0020ShipPlovision` ("
				+"  `cl_cd` varchar(20) NOT NULL,"
				+"  `InvoiceWHCD` varchar(20) NOT NULL,"
				+"  `OkuriNo` int(11) NOT NULL,"
				+"  `MsNo` int(11) NOT NULL,"
				+"  `Seq` int(11) NOT NULL,"
				+"  `OrderItemCd` varchar(20) DEFAULT NULL,"
				+"  `OrderItemName01` varchar(100) DEFAULT NULL,"
				+"  `OrderLot` varchar(20) DEFAULT NULL,"
				+"  `OrderExpDate` datetime DEFAULT NULL,"
				+"  `OrderQty` int(11) DEFAULT '0',"
				+"  `ShipWhCd` varchar(20) DEFAULT NULL,"
				+"  `ShipLoc` varchar(15) DEFAULT NULL,"
				+"  `ShipItemCd` varchar(20) DEFAULT NULL,"
				+"  `ShipLot` varchar(20) DEFAULT NULL,"
				+"  `ShipExpdate` datetime DEFAULT NULL,"
				+"  `ShipActualDate` datetime DEFAULT NULL,"
				+"  `ShipQty` int(11) DEFAULT '0',"
				+"  `EntryDate` datetime DEFAULT NULL,"
				+"  `UpdateDate` datetime DEFAULT NULL,"
				+"  `EntryUser` varchar(50) DEFAULT NULL,"
				+"  `UpdateUser` varchar(50) DEFAULT NULL,"
				+"  `FixFg` int(11) NOT NULL DEFAULT '0',"
				+"  `PackingType` int(11) NOT NULL DEFAULT '0',"
				+"  `PackingQty` int(11) NOT NULL DEFAULT '0',"
				+"  `UnitName` varchar(20) DEFAULT NULL,"
				+"  `PackingUnitQty` int(11) DEFAULT '1',"
				+"  `BRShipQty` int(11) DEFAULT '0',"
				+"  `CTShipQty` int(11) DEFAULT '0',"
				+"  `CSShipQty` int(11) DEFAULT '0',"
				+"  `PLShipQty` int(11) DEFAULT '0',"
				+"  `BRUnitName` varchar(20) DEFAULT NULL,"
				+"  `CTUnitName` varchar(20) DEFAULT NULL,"
				+"  `CSUnitName` varchar(20) DEFAULT NULL,"
				+"  `PLUnitName` varchar(20) DEFAULT NULL,"
				+"  PRIMARY KEY (`cl_cd`,`InvoiceWHCD`,`OkuriNo`,`MsNo`,`Seq`)"
				+") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='引当結果';";
		return sql;
	}
	
	private static String WW0025BerthReservationTableCreateSql() {
		//出荷バース予約テーブルを作る
		String sql = ""
				+"CREATE TABLE `WW0025BerthReservation` ("
				+"  `WhCd` varchar(20) NOT NULL,"
				+"  `BerthCd` varchar(20) NOT NULL,"
				+"  `ReserveDate` datetime NOT NULL,"
				+"  `ReserveTimeStr` int(11) NOT NULL DEFAULT '0',"
				+"  `ReserveTimeEnd` int(11) NOT NULL DEFAULT '0',"
				+"  `HaishaNo` int(11) NOT NULL DEFAULT '0',"
				+"  `EntryDate` datetime DEFAULT NULL,"
				+"  `UpdateDate` datetime DEFAULT NULL,"
				+"  `EntryUser` varchar(50) DEFAULT NULL,"
				+"  `UpdateUser` varchar(50) DEFAULT NULL,"
				+"  PRIMARY KEY (`HaishaNo`)"
				+") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='出荷バース予約';";
		return sql;
	}
	
	private static String WW013101WhFeeInHdTableCreateSql() {
		//倉庫入荷料ヘッダテーブルを作る
		String sql = ""
				+"CREATE TABLE `WW013101WhFeeInHd` ("
				+"  `ClCd` varchar(20) NOT NULL COMMENT '荷主コード',"
				+"  `WhCd` varchar(20) NOT NULL COMMENT '倉庫コード',"
				+"  `ArrivalFeeCd` varchar(20) NOT NULL COMMENT '入荷料金コード',"
				+"  `ArrivalFeeName` varchar(100) DEFAULT NULL COMMENT '入荷料金名',"
				+"  `DeliveryTypeCd01` varchar(20) DEFAULT NULL COMMENT '運送タイプコード01',"
				+"  `DeliveryTypeCd02` varchar(20) DEFAULT NULL COMMENT '運送タイプコード02',"
				+"  `DeliveryTypeCd03` varchar(20) DEFAULT NULL COMMENT '運送タイプコード03',"
				+"  `DeliveryTypeCd04` varchar(20) DEFAULT NULL COMMENT '運送タイプコード04',"
				+"  `DeliveryTypeCd05` varchar(20) DEFAULT NULL COMMENT '運送タイプコード05',"
				+"  `TildFG` varchar(20) DEFAULT NULL COMMENT '温度区分',"
				+"  `CategoryCd` varchar(20) DEFAULT NULL COMMENT '商品カテゴリCD',"
				+"  `ShimeDate` datetime NOT NULL COMMENT '締め日',"
				+"  `FeeUnit` int(11) NOT NULL DEFAULT '0' COMMENT '課金単位',"
				+"  `SummaryFg` int(11) NOT NULL DEFAULT '0' COMMENT '集計区分',"
				+"  `ArrivalBaseFee` int(11) NOT NULL DEFAULT '0' COMMENT '入荷基本料金',"
				+"  `ArrivalSlipFee` int(11) NOT NULL DEFAULT '0' COMMENT '入荷伝票基本料金',"
				+"  `ArrivalSlipFeeTotal` int(11) NOT NULL DEFAULT '0' COMMENT '入荷伝票基本料金合計',"
				+"  `ArrivalUnitFee` float NOT NULL DEFAULT '0' COMMENT '入荷料単価',"
				+"  `ArrivalQtyTotal` int(11) NOT NULL DEFAULT '0' COMMENT '入荷数合計',"
				+"  `ArrivalVolTotal` float NOT NULL DEFAULT '0' COMMENT '入荷量合計',"
				+"  `ArrivalFeeTotal` int(11) NOT NULL DEFAULT '0' COMMENT '入荷料',"
				+"  `TaxFg` int(11) NOT NULL DEFAULT '0' COMMENT '税区分',"
				+"  `TaxRate` int(11) NOT NULL DEFAULT '0' COMMENT '税率',"
				+"  `ConsumptionTax` int(11) NOT NULL DEFAULT '0' COMMENT '消費税',"
				+"  `WithOutTaxTotal` int(11) NOT NULL DEFAULT '0' COMMENT '税別合計金額',"
				+"  `TotalFee` int(11) NOT NULL DEFAULT '0' COMMENT '税込請求額合計',"
				+"  `FeeFixFg` int(11) NOT NULL DEFAULT '0' COMMENT '確定区分',"
				+"  `EntryDate` datetime DEFAULT NULL COMMENT '登録日',"
				+"  `UpdateDate` datetime DEFAULT NULL COMMENT '更新日',"
				+"  `EntryUser` varchar(50) DEFAULT NULL COMMENT '登録者',"
				+"  `UpdateUser` varchar(50) DEFAULT NULL COMMENT '更新者',"
				+"  `FeeNo` int(11) NOT NULL DEFAULT '0' COMMENT 'パラメータマスタから採番',"
				+"  `SlipCount` int(11) NOT NULL DEFAULT '0' COMMENT '伝票枚数',"
				+"  PRIMARY KEY (`ClCd`,`WhCd`,`ArrivalFeeCd`,`ShimeDate`)"
				+") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='倉庫入荷料ヘッダ';";
		return sql;
	}
	
	private static String WW013102WhFeeInMsTableCreateSql() {
		//倉庫入荷料明細テーブルを作る
		String sql = ""
				+"CREATE TABLE `WW013102WhFeeInMs` ("
				+"  `ClCd` varchar(20) NOT NULL COMMENT '荷主コード',"
				+"  `WhCd` varchar(20) NOT NULL COMMENT '倉庫コード',"
				+"  `ArrivalFeeCd` varchar(20) NOT NULL COMMENT '入荷料金コード',"
				+"  `ShimeDate` datetime NOT NULL COMMENT '締め日',"
				+"  `ArrNo` varchar(20) NOT NULL COMMENT '入荷予定NO',"
				+"  `ArrCount` int(11) NOT NULL COMMENT '入荷予定枝番',"
				+"  `MsSeq` int(11) NOT NULL COMMENT '明細シーケンシャル番号',"
				+"  `ClGpCD` varchar(20) DEFAULT NULL COMMENT '荷主グループ',"
				+"  `ItemCd` varchar(20) NOT NULL COMMENT '商品コード',"
				+"  `ClItemCd` varchar(20) NOT NULL COMMENT '荷主商品コード',"
				+"  `JanCd` varchar(20) NOT NULL COMMENT 'ソースマーク_BCD',"
				+"  `ItemMdNo` varchar(20) NOT NULL COMMENT '商品型番',"
				+"  `ItemName` varchar(100) NOT NULL COMMENT '商品名',"
				+"  `Lot` varchar(20) NOT NULL COMMENT 'ロット',"
				+"  `ExpDate` datetime NOT NULL COMMENT '消費期限',"
				+"  `PlanQty` int(11) NOT NULL DEFAULT '0' COMMENT '予定数量',"
				+"  `ActualQty` int(11) NOT NULL DEFAULT '0' COMMENT '実績数',"
				+"  `ActualDate` datetime NOT NULL COMMENT '入荷日',"
				+"  `Com01` varchar(200) NOT NULL COMMENT 'コメント1',"
				+"  `Com02` varchar(200) NOT NULL COMMENT 'コメント2',"
				+"  `ArrivalSlipFee` int(11) NOT NULL DEFAULT '0' COMMENT '入荷伝票基本料金',"
				+"  `ArrivalUnitFee` float NOT NULL DEFAULT '0' COMMENT '入荷料単価',"
				+"  `ItemWeight` float NOT NULL DEFAULT '0' COMMENT '商品重量',"
				+"  `ItemSize` float NOT NULL DEFAULT '0' COMMENT '商品サイズ',"
				+"  `FeeUnit` int(11) NOT NULL DEFAULT '0' COMMENT '課金単位',"
				+"  `ArrivalQty` int(11) NOT NULL DEFAULT '0' COMMENT '入荷数',"
				+"  `ArrivalVol` float NOT NULL DEFAULT '0' COMMENT '入荷量',"
				+"  `ArrivalFee` int(11) NOT NULL DEFAULT '0' COMMENT '入荷料',"
				+"  `EntryDate` datetime NOT NULL COMMENT '登録日',"
				+"  `UpdateDate` datetime NOT NULL COMMENT '更新日',"
				+"  `EntryUser` varchar(50) NOT NULL COMMENT '登録者',"
				+"  `UpdateUser` varchar(50) NOT NULL COMMENT '更新者',"
				+"  `ClArrNo` varchar(50) DEFAULT NULL COMMENT '荷主予定番号',"
				+"  `FeeNo` int(11) NOT NULL DEFAULT '0',"
				+"  PRIMARY KEY (`ClCd`,`WhCd`,`ArrivalFeeCd`,`ShimeDate`,`ArrNo`,`ArrCount`,`MsSeq`)"
				+") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='倉庫入荷料明細';";
		return sql;
	}
	
	private static String WW013201WhFeeOutHdTableCreateSql() {
		//倉庫出荷料ヘッダテーブルを作る
		String sql = ""
				+"CREATE TABLE `WW013201WhFeeOutHd` ("
				+"  `ClCd` varchar(20) NOT NULL COMMENT '荷主コード',"
				+"  `WhCd` varchar(20) NOT NULL COMMENT '倉庫コード',"
				+"  `ShipFeeCd` varchar(20) NOT NULL COMMENT '出荷料金コード',"
				+"  `ShipFeeName` varchar(100) NOT NULL COMMENT '出荷料金名',"
				+"  `DeliveryTypeCd01` varchar(20) NOT NULL COMMENT '運送タイプコード01',"
				+"  `DeliveryTypeCd02` varchar(20) NOT NULL COMMENT '運送タイプコード02',"
				+"  `DeliveryTypeCd03` varchar(20) NOT NULL COMMENT '運送タイプコード03',"
				+"  `DeliveryTypeCd04` varchar(20) NOT NULL COMMENT '運送タイプコード04',"
				+"  `DeliveryTypeCd05` varchar(20) NOT NULL COMMENT '運送タイプコード05',"
				+"  `TildFG` varchar(20) NOT NULL COMMENT '温度区分',"
				+"  `CategoryCd` varchar(20) NOT NULL COMMENT '商品カテゴリCD',"
				+"  `ShimeDate` datetime NOT NULL COMMENT '締め日',"
				+"  `FeeUnit` int(11) NOT NULL DEFAULT '0' COMMENT '課金単位',"
				+"  `SummaryFg` int(11) NOT NULL DEFAULT '0' COMMENT '集計区分',"
				+"  `ShipBaseFee` int(11) NOT NULL DEFAULT '0' COMMENT '出荷基本料金',"
				+"  `ShipSlipFee` int(11) NOT NULL DEFAULT '0' COMMENT '出荷伝票基本料金',"
				+"  `ShipSlipFeeTotal` int(11) NOT NULL DEFAULT '0' COMMENT '出荷伝票基本料金合計',"
				+"  `ShipUnitFee` float NOT NULL DEFAULT '0' COMMENT '出荷料単価',"
				+"  `ShipQtyTotal` int(11) NOT NULL DEFAULT '0' COMMENT '出荷数合計',"
				+"  `ShipVolTotal` float NOT NULL DEFAULT '0' COMMENT '出荷量合計',"
				+"  `ShipFeeTotal` int(11) NOT NULL DEFAULT '0' COMMENT '出荷料',"
				+"  `TaxFg` int(11) NOT NULL DEFAULT '0' COMMENT '税区分',"
				+"  `TaxRate` int(11) NOT NULL DEFAULT '0' COMMENT '税率',"
				+"  `ConsumptionTax` int(11) NOT NULL DEFAULT '0' COMMENT '消費税',"
				+"  `WithOutTaxTotal` int(11) NOT NULL DEFAULT '0' COMMENT '税別合計金額',"
				+"  `TotalFee` int(11) NOT NULL DEFAULT '0' COMMENT '税込請求額合計',"
				+"  `FeeFixFg` int(11) NOT NULL DEFAULT '0' COMMENT '確定区分',"
				+"  `EntryDate` datetime NOT NULL COMMENT '登録日',"
				+"  `UpdateDate` datetime NOT NULL COMMENT '更新日',"
				+"  `EntryUser` varchar(50) NOT NULL COMMENT '登録者',"
				+"  `UpdateUser` varchar(50) NOT NULL COMMENT '更新者',"
				+"  `FeeNo` int(11) NOT NULL DEFAULT '0',"
				+"  `SlipCount` int(11) NOT NULL DEFAULT '0' COMMENT '伝票枚数',"
				+"  PRIMARY KEY (`ClCd`,`WhCd`,`ShipFeeCd`,`ShimeDate`)"
				+") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='倉庫出荷料';";
		return sql;
	}
	
	private static String WW013202WhFeeOutMsTableCreateSql() {
		//倉庫出荷料明細テーブルを作る
		String sql = ""
				+"CREATE TABLE `WW013202WhFeeOutMs` ("
				+"  `ClCd` varchar(20) NOT NULL COMMENT '荷主コード',"
				+"  `WhCd` varchar(20) NOT NULL COMMENT '倉庫コード',"
				+"  `ShipFeeCd` varchar(20) NOT NULL COMMENT '出荷料金コード',"
				+"  `ShimeDate` datetime NOT NULL COMMENT '締め日',"
				+"  `OkuriNo` int(11) NOT NULL COMMENT '送り状番号',"
				+"  `MsNo` int(11) NOT NULL COMMENT '明細番号',"
				+"  `Seq` int(11) NOT NULL COMMENT '引当枝番',"
				+"  `ClGpCD` varchar(20) DEFAULT NULL COMMENT '荷主グループ',"
				+"  `OrderItemCd` varchar(20) DEFAULT NULL COMMENT '商品コード',"
				+"  `ClItemCd` varchar(20) DEFAULT NULL COMMENT '荷主商品コード',"
				+"  `OrderItemName01` varchar(100) DEFAULT NULL COMMENT '品名01',"
				+"  `OrderLot` varchar(20) DEFAULT NULL COMMENT '受注ロット指定',"
				+"  `OrderExpDate` datetime DEFAULT NULL COMMENT '受注賞味期限指定',"
				+"  `OrderQty` float NOT NULL DEFAULT '0' COMMENT '受注個数',"
				+"  `ShipWhCd` varchar(20) DEFAULT NULL COMMENT '倉庫コード',"
				+"  `ShipLoc` varchar(15) DEFAULT NULL COMMENT 'ロケーション',"
				+"  `ShipItemCd` varchar(20) DEFAULT NULL COMMENT '商品コード',"
				+"  `ShipLot` varchar(20) DEFAULT NULL COMMENT 'ロット',"
				+"  `ShipExpdate` datetime DEFAULT NULL COMMENT '消費期限',"
				+"  `ShipActualDate` datetime DEFAULT NULL COMMENT '入荷実績日',"
				+"  `WmsShipDate` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '倉庫出荷日',"
				+"  `FixFg` int(11) NOT NULL DEFAULT '0' COMMENT '引落済フラグ',"
				+"  `PackingType` int(11) NOT NULL DEFAULT '0' COMMENT '荷姿タイプ',"
				+"  `PackingQty` int(11) NOT NULL DEFAULT '0' COMMENT '荷姿数量',"
				+"  `UnitName` varchar(20) DEFAULT NULL COMMENT '荷姿単位',"
				+"  `PackingUnitQty` int(11) NOT NULL DEFAULT '0' COMMENT '荷姿単位のバラ入数',"
				+"  `BRShipQty` int(11) NOT NULL DEFAULT '0' COMMENT 'バラ数量',"
				+"  `CTShipQty` int(11) NOT NULL DEFAULT '0' COMMENT 'カートン数量',"
				+"  `CSShipQty` int(11) NOT NULL DEFAULT '0' COMMENT 'ケース数量',"
				+"  `PLShipQty` int(11) NOT NULL DEFAULT '0' COMMENT 'パレット数量',"
				+"  `BRUnitName` varchar(20) DEFAULT NULL COMMENT 'バラ単位名',"
				+"  `CTUnitName` varchar(20) DEFAULT NULL COMMENT 'カートン単位名',"
				+"  `CSUnitName` varchar(20) DEFAULT NULL COMMENT 'ケース単位名',"
				+"  `PLUnitName` varchar(20) DEFAULT NULL COMMENT 'パレット単位名',"
				+"  `ShipSlipFee` int(11) NOT NULL DEFAULT '0' COMMENT '出荷伝票基本料金',"
				+"  `ShipUnitFee` float NOT NULL DEFAULT '0' COMMENT '出荷料単価',"
				+"  `ItemWeight` float NOT NULL DEFAULT '0' COMMENT '商品重量',"
				+"  `ItemSize` float NOT NULL DEFAULT '0' COMMENT '商品サイズ',"
				+"  `FeeUnit` int(11) NOT NULL DEFAULT '0' COMMENT '課金単位',"
				+"  `ShipQty` int(11) NOT NULL DEFAULT '0' COMMENT '出荷数',"
				+"  `ShipVol` float NOT NULL DEFAULT '0' COMMENT '出荷量',"
				+"  `ShipFee` int(11) NOT NULL DEFAULT '0' COMMENT '出荷料',"
				+"  `EntryDate` datetime DEFAULT NULL COMMENT '登録日',"
				+"  `UpdateDate` datetime DEFAULT NULL COMMENT '更新日',"
				+"  `EntryUser` varchar(50) DEFAULT NULL COMMENT '登録者',"
				+"  `UpdateUser` varchar(50) DEFAULT NULL COMMENT '更新者',"
				+"  `ClDeliNo` varchar(50) DEFAULT NULL COMMENT '荷主管理番号',"
				+"  `ClOrderNo` varchar(50) DEFAULT NULL COMMENT '荷主管理番号',"
				+"  `NiokuriCd` varchar(20) DEFAULT NULL COMMENT '荷送り人コード',"
				+"  `NiokuriDepartmentCd` varchar(20) DEFAULT NULL COMMENT '部署CD',"
				+"  `NiokuriName01` varchar(50) DEFAULT NULL COMMENT '荷送り人名01',"
				+"  `NiokuriName02` varchar(50) DEFAULT NULL COMMENT '荷送り人名02',"
				+"  `NiokuriName03` varchar(50) DEFAULT NULL COMMENT '荷送り人名03',"
				+"  `NiokuriPost` varchar(20) DEFAULT NULL COMMENT '荷送り人郵便番号',"
				+"  `NiokuriAdd01` varchar(100) DEFAULT NULL COMMENT '荷送り人住所01',"
				+"  `NiokuriAdd02` varchar(100) DEFAULT NULL COMMENT '荷送り人住所02',"
				+"  `NiokuriAdd03` varchar(100) DEFAULT NULL COMMENT '荷送り人住所03',"
				+"  `NioKuriTel` varchar(20) DEFAULT NULL COMMENT '荷送り人TEL',"
				+"  `NioKuriFax` varchar(20) DEFAULT NULL COMMENT '荷送り人FAX',"
				+"  `NioKuriMail` varchar(200) DEFAULT NULL COMMENT '荷送り人MAIL',"
				+"  `NiokuriMunicCd` varchar(20) DEFAULT NULL COMMENT '荷送人市区町村CD',"
				+"  `DeliCd` varchar(20) DEFAULT NULL COMMENT '荷届け先コード',"
				+"  `ClDeliCd` varchar(20) DEFAULT NULL COMMENT '荷主荷届け先コード',"
				+"  `DeliDepartmentCd` varchar(20) DEFAULT NULL COMMENT '部署CD',"
				+"  `DeliName01` varchar(50) DEFAULT NULL COMMENT '荷届け先名01',"
				+"  `DeliName02` varchar(50) DEFAULT NULL COMMENT '荷届け先名02',"
				+"  `DeliName03` varchar(50) DEFAULT NULL COMMENT '荷届け先名03',"
				+"  `DeliPost` varchar(20) DEFAULT NULL COMMENT '荷届け先郵便番号',"
				+"  `DeliAdd01` varchar(100) DEFAULT NULL COMMENT '荷届け先住所01',"
				+"  `DeliAdd02` varchar(100) DEFAULT NULL COMMENT '荷届け先住所02',"
				+"  `DeliAdd03` varchar(100) DEFAULT NULL COMMENT '荷届け先住所03',"
				+"  `DeliTel` varchar(20) DEFAULT NULL COMMENT '荷届け先TEL',"
				+"  `DeliFax` varchar(20) DEFAULT NULL COMMENT '荷届け先FAX',"
				+"  `DeliMail` varchar(200) DEFAULT NULL COMMENT '荷届け先MAIL',"
				+"  `DeliMunicCd` varchar(20) DEFAULT NULL COMMENT '荷届先市区町村CD',"
				+"  `FeeNo` int(11) NOT NULL DEFAULT '0',"
				+"  PRIMARY KEY (`ClCd`,`WhCd`,`ShipFeeCd`,`ShimeDate`,`OkuriNo`,`MsNo`,`Seq`)"
				+") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='倉庫出荷料明細';";
		return sql;
	}
	
	private static String WW013301WhFeeStockHdTableCreateSql() {
		//倉庫保管料ヘッダテーブルを作る
		String sql = ""
				+"CREATE TABLE `WW013301WhFeeStockHd` ("
				+"  `ClCd` varchar(20) NOT NULL COMMENT '荷主コード',"
				+"  `WhCd` varchar(20) NOT NULL COMMENT '倉庫コード',"
				+"  `StockFeeCd` varchar(20) NOT NULL COMMENT '保管料金コード',"
				+"  `StockFeeName` varchar(100) DEFAULT NULL COMMENT '保管料金名',"
				+"  `DeliveryTypeCd01` varchar(20) DEFAULT NULL COMMENT '運送タイプコード01',"
				+"  `DeliveryTypeCd02` varchar(20) DEFAULT NULL COMMENT '運送タイプコード02',"
				+"  `DeliveryTypeCd03` varchar(20) DEFAULT NULL COMMENT '運送タイプコード03',"
				+"  `DeliveryTypeCd04` varchar(20) DEFAULT NULL COMMENT '運送タイプコード04',"
				+"  `DeliveryTypeCd05` varchar(20) DEFAULT NULL COMMENT '運送タイプコード05',"
				+"  `TildFG` varchar(20) DEFAULT NULL COMMENT '温度区分',"
				+"  `CategoryCd` varchar(20) DEFAULT NULL COMMENT '商品カテゴリCD',"
				+"  `ShimeDate` datetime NOT NULL COMMENT '締め日',"
				+"  `CuttingDate` datetime NOT NULL COMMENT '期締め日',"
				+"  `StockBaseFee` int(11) NOT NULL DEFAULT '0' COMMENT '保管基本料金',"
				+"  `StockUnitFee` float NOT NULL DEFAULT '0' COMMENT '保管料単価',"
				+"  `FeeUnit` int(11) NOT NULL DEFAULT '0' COMMENT '課金単位',"
				+"  `SummaryFg` int(11) NOT NULL DEFAULT '0' COMMENT '集計区分',"
				+"  `StrStockQtyTotal` int(11) NOT NULL DEFAULT '0' COMMENT '期初在庫数(バラ)',"
				+"  `ArrivalQtyTotal` int(11) NOT NULL DEFAULT '0' COMMENT '期中入荷数(バラ)',"
				+"  `FeeQtyTotal` int(11) NOT NULL DEFAULT '0' COMMENT '請求対象数(バラ）',"
				+"  `StrStockVolTotal` float NOT NULL DEFAULT '0' COMMENT '期初在庫量',"
				+"  `ArrivalVolTotal` float NOT NULL DEFAULT '0' COMMENT '期中入荷量',"
				+"  `FeeVolTotal` float NOT NULL DEFAULT '0' COMMENT '請求対象量',"
				+"  `StockFeeTotal` int(11) NOT NULL DEFAULT '0' COMMENT '保管料',"
				+"  `TaxFg` int(11) NOT NULL DEFAULT '0' COMMENT '税区分',"
				+"  `TaxRate` int(11) NOT NULL DEFAULT '0' COMMENT '税率',"
				+"  `ConsumptionTax` int(11) NOT NULL DEFAULT '0' COMMENT '消費税',"
				+"  `WithOutTaxTotal` int(11) NOT NULL DEFAULT '0' COMMENT '税別合計金額',"
				+"  `TotalFee` int(11) NOT NULL DEFAULT '0' COMMENT '税込請求額合計',"
				+"  `FeeFixFg` int(11) NOT NULL DEFAULT '0' COMMENT '確定区分',"
				+"  `EntryDate` datetime DEFAULT NULL COMMENT '登録日',"
				+"  `UpdateDate` datetime DEFAULT NULL COMMENT '更新日',"
				+"  `EntryUser` varchar(50) DEFAULT NULL COMMENT '登録者',"
				+"  `UpdateUser` varchar(50) DEFAULT NULL COMMENT '更新者',"
				+"  `FeeNo` int(11) NOT NULL DEFAULT '0',"
				+"  PRIMARY KEY (`ClCd`,`WhCd`,`StockFeeCd`,`ShimeDate`,`CuttingDate`)"
				+") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='倉庫保管料ヘッダ';";
		return sql;
	}
	
	private static String WW013302WhFeeStockMsTableCreateSql() {
		//倉庫保管料明細テーブルを作る
		String sql = ""
				+"CREATE TABLE `WW013302WhFeeStockMs` ("
				+"  `ClCd` varchar(20) NOT NULL COMMENT '荷主コード',"
				+"  `WhCd` varchar(20) NOT NULL COMMENT '倉庫コード',"
				+"  `StockFeeCd` varchar(20) NOT NULL COMMENT '保管料金コード',"
				+"  `ShimeDate` datetime NOT NULL COMMENT '締め日',"
				+"  `CuttingDate` datetime NOT NULL COMMENT '期締め日',"
				+"  `ClGpCD` varchar(20) NOT NULL COMMENT '荷主グループ',"
				+"  `ItemCd` varchar(20) NOT NULL COMMENT '商品コード',"
				+"  `Lot` varchar(20) NOT NULL COMMENT 'ロット',"
				+"  `Expdate` datetime NOT NULL COMMENT '消費期限',"
				+"  `ClItemCd` varchar(20) DEFAULT NULL COMMENT '荷主商品コード',"
				+"  `ItemName` varchar(100) DEFAULT NULL COMMENT '商品名',"
				+"  `StockUnitFee` float NOT NULL DEFAULT '0' COMMENT '保管料単価',"
				+"  `FeeUnit` int(11) NOT NULL DEFAULT '0' COMMENT '課金単位',"
				+"  `SummaryFg` int(11) NOT NULL DEFAULT '0' COMMENT '集計区分',"
				+"  `ItemWeight` float NOT NULL DEFAULT '0' COMMENT '商品重量',"
				+"  `ItemSize` float NOT NULL DEFAULT '0' COMMENT '商品サイズ',"
				+"  `StrStockQty` int(11) NOT NULL DEFAULT '0' COMMENT '期初在庫数(バラ)',"
				+"  `ArrivalQty` int(11) NOT NULL DEFAULT '0' COMMENT '期中入荷数(バラ)',"
				+"  `FeeQty` int(11) NOT NULL DEFAULT '0' COMMENT '請求対象数(バラ）',"
				+"  `StrStockVol` float NOT NULL DEFAULT '0' COMMENT '期初在庫量',"
				+"  `ArrivalVol` float NOT NULL DEFAULT '0' COMMENT '期中入荷量',"
				+"  `FeeVol` float NOT NULL DEFAULT '0' COMMENT '請求対象量',"
				+"  `StockFee` int(11) NOT NULL DEFAULT '0' COMMENT '保管料',"
				+"  `EntryDate` datetime DEFAULT NULL COMMENT '登録日',"
				+"  `UpdateDate` datetime DEFAULT NULL COMMENT '更新日',"
				+"  `EntryUser` varchar(50) DEFAULT NULL COMMENT '登録者',"
				+"  `UpdateUser` varchar(50) DEFAULT NULL COMMENT '更新者',"
				+"  `FeeNo` int(11) NOT NULL DEFAULT '0',"
				+"  `ShipQty` int(11) NOT NULL DEFAULT '0' COMMENT '期中出荷数',"
				+"  `AdjustQty` int(11) NOT NULL DEFAULT '0' COMMENT '期中調整数',"
				+"  `EndStockQty` int(11) NOT NULL DEFAULT '0' COMMENT '期末在庫数',"
				+"  PRIMARY KEY (`ClCd`,`WhCd`,`StockFeeCd`,`ShimeDate`,`CuttingDate`,`ClGpCD`,`ItemCd`,`Lot`,`Expdate`)"
				+") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='倉庫保管料明細';";
		return sql;
	}
	
	private static String WW013401WhFeeAdjustHdTableCreateSql() {
		//倉庫在庫調整料ヘッダテーブルを作る
		String sql = ""
				+"CREATE TABLE `WW013401WhFeeAdjustHd` ("
				+"  `ClCd` varchar(20) NOT NULL COMMENT '荷主コード',"
				+"  `WhCd` varchar(20) NOT NULL COMMENT '倉庫コード',"
				+"  `AdjustFeeCd` varchar(20) NOT NULL COMMENT '調整料金コード',"
				+"  `AdjustFeeName` varchar(100) DEFAULT NULL COMMENT '調整料金名',"
				+"  `DeliveryTypeCd01` varchar(20) DEFAULT NULL COMMENT '運送タイプコード01',"
				+"  `DeliveryTypeCd02` varchar(20) DEFAULT NULL COMMENT '運送タイプコード02',"
				+"  `DeliveryTypeCd03` varchar(20) DEFAULT NULL COMMENT '運送タイプコード03',"
				+"  `DeliveryTypeCd04` varchar(20) DEFAULT NULL COMMENT '運送タイプコード04',"
				+"  `DeliveryTypeCd05` varchar(20) DEFAULT NULL COMMENT '運送タイプコード05',"
				+"  `TildFG` varchar(20) DEFAULT NULL COMMENT '温度区分',"
				+"  `CategoryCd` varchar(20) DEFAULT NULL COMMENT '商品カテゴリCD',"
				+"  `AdjustReasonCd` varchar(20) DEFAULT NULL COMMENT '調整理由CD',"
				+"  `ShimeDate` datetime NOT NULL COMMENT '締め日',"
				+"  `AdjustBaseFee` int(11) NOT NULL DEFAULT '0' COMMENT '調整基本料金',"
				+"  `AdjustUnitFee` float NOT NULL DEFAULT '0' COMMENT '調整料単価',"
				+"  `FeeUnit` int(11) NOT NULL DEFAULT '0' COMMENT '課金単位',"
				+"  `SummaryFg` int(11) NOT NULL DEFAULT '0' COMMENT '集計区分',"
				+"  `AdjustQtyTotal` int(11) NOT NULL DEFAULT '0' COMMENT '調整数合計',"
				+"  `AdjustVolTotal` float NOT NULL DEFAULT '0' COMMENT '調整量合計',"
				+"  `AdjustFeeTotal` int(11) NOT NULL DEFAULT '0' COMMENT '調整料',"
				+"  `TaxFg` int(11) NOT NULL DEFAULT '0' COMMENT '税区分',"
				+"  `TaxRate` int(11) NOT NULL DEFAULT '0' COMMENT '税率',"
				+"  `ConsumptionTax` int(11) NOT NULL DEFAULT '0' COMMENT '消費税',"
				+"  `WithOutTaxTotal` int(11) NOT NULL DEFAULT '0' COMMENT '税別合計金額',"
				+"  `TotalFee` int(11) NOT NULL DEFAULT '0' COMMENT '税込請求額合計',"
				+"  `FeeFixFg` int(11) NOT NULL DEFAULT '0' COMMENT '確定区分',"
				+"  `EntryDate` datetime DEFAULT NULL COMMENT '登録日',"
				+"  `UpdateDate` datetime DEFAULT NULL COMMENT '更新日',"
				+"  `EntryUser` varchar(50) DEFAULT NULL COMMENT '登録者',"
				+"  `UpdateUser` varchar(50) DEFAULT NULL COMMENT '更新者',"
				+"  `FeeNo` int(11) NOT NULL DEFAULT '0',"
				+"  PRIMARY KEY (`ClCd`,`WhCd`,`AdjustFeeCd`,`ShimeDate`)"
				+") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='倉庫在庫調整料ヘッダ';";
		return sql;
	}
	
	private static String WW013402WhFeeAdjustMsTableCreateSql() {
		//倉庫在庫調整料明細テーブルを作る
		String sql = ""
				+"CREATE TABLE `WW013402WhFeeAdjustMs` ("
				+"  `ClCd` varchar(20) NOT NULL COMMENT '荷主コード',"
				+"  `WhCd` varchar(20) NOT NULL COMMENT '倉庫コード',"
				+"  `AdjustFeeCd` varchar(20) NOT NULL COMMENT '調整料金コード',"
				+"  `ShimeDate` datetime NOT NULL COMMENT '締め日',"
				+"  `AdjustNo` int(11) NOT NULL COMMENT '調整番号',"
				+"  `AdjustReasonCd` varchar(20) DEFAULT NULL COMMENT '調整理由コード',"
				+"  `AdjustReasonName` varchar(100) DEFAULT NULL COMMENT '調整理由名',"
				+"  `Adjustdate` datetime DEFAULT NULL COMMENT '調整日',"
				+"  `Wh` varchar(20) DEFAULT NULL COMMENT '調整元倉庫',"
				+"  `Loc` varchar(15) DEFAULT NULL COMMENT '調整元ロケ',"
				+"  `ClGpCD` varchar(20) DEFAULT NULL COMMENT '荷主グループ',"
				+"  `ItemCd` varchar(20) DEFAULT NULL COMMENT '調整元商品CD',"
				+"  `ClItemCd` varchar(20) DEFAULT NULL COMMENT '荷主商品コード',"
				+"  `ItemName` varchar(100) DEFAULT NULL COMMENT '調整元商品名',"
				+"  `Lot` varchar(20) DEFAULT NULL COMMENT '調整元ロット',"
				+"  `ExpDate` datetime DEFAULT NULL COMMENT '調整元賞味期限',"
				+"  `ActualDate` datetime DEFAULT NULL COMMENT '調整元入荷日',"
				+"  `BeforeQty` int(11) NOT NULL DEFAULT '0' COMMENT '調整元在庫数',"
				+"  `ShipPlanQty` int(11) NOT NULL DEFAULT '0' COMMENT '調整元引当済数',"
				+"  `PossibleQty` int(11) NOT NULL DEFAULT '0' COMMENT '調整元出荷可能数',"
				+"  `AdjustQty` int(11) NOT NULL DEFAULT '0' COMMENT '調整数',"
				+"  `AdjustCom01` varchar(100) DEFAULT NULL COMMENT '調整理由コメント01',"
				+"  `AdjustCom02` varchar(100) DEFAULT NULL COMMENT '調整理由コメント02',"
				+"  `AdjustCom03` varchar(100) DEFAULT NULL COMMENT '調整理由コメント03',"
				+"  `AfterQty` int(11) NOT NULL DEFAULT '0' COMMENT '調整後在庫数',"
				+"  `AdjustUnitFee` float NOT NULL DEFAULT '0' COMMENT '調整料単価',"
				+"  `FeeUnit` int(11) NOT NULL DEFAULT '0' COMMENT '課金単位',"
				+"  `SummaryFg` int(11) NOT NULL DEFAULT '0' COMMENT '集計区分',"
				+"  `ItemWeight` float NOT NULL DEFAULT '0' COMMENT '商品重量',"
				+"  `ItemSize` float NOT NULL DEFAULT '0' COMMENT '商品サイズ',"
				+"  `AdjustAbsoluteQty` int(11) NOT NULL DEFAULT '0' COMMENT '調整数（絶対値）',"
				+"  `AdjustVol` float NOT NULL DEFAULT '0' COMMENT '調整量',"
				+"  `AdjustFee` int(11) NOT NULL DEFAULT '0' COMMENT '調整料',"
				+"  `EntryDate` datetime DEFAULT NULL COMMENT '登録日',"
				+"  `UpdateDate` datetime DEFAULT NULL COMMENT '更新日',"
				+"  `EntryUser` varchar(50) DEFAULT NULL COMMENT '登録者',"
				+"  `UpdateUser` varchar(50) DEFAULT NULL COMMENT '更新者',"
				+"  `FeeNo` int(11) NOT NULL DEFAULT '0',"
				+"  PRIMARY KEY (`ClCd`,`WhCd`,`AdjustFeeCd`,`ShimeDate`,`AdjustNo`)"
				+") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='倉庫在庫調整料明細';";
		return sql;
	}
	
	private static String WW013501WhFeeOtherTableCreateSql() {
		//倉庫その他請求ヘッダテーブルを作る
		String sql = ""
				+"CREATE TABLE `WW013501WhFeeOther` ("
				+"  `ClCd` varchar(20) NOT NULL COMMENT '荷主コード',"
				+"  `WhCd` int(20) NOT NULL COMMENT '倉庫コード',"
				+"  `ShimeDate` datetime NOT NULL COMMENT '締め日',"
				+"  `Seq` int(11) NOT NULL COMMENT 'シーケンシャル番号',"
				+"  `OthereFeeName` varchar(200) DEFAULT NULL COMMENT '料金名',"
				+"  `OthereUnitFee` float NOT NULL DEFAULT '0' COMMENT '単価',"
				+"  `OthereQty` float NOT NULL DEFAULT '0' COMMENT '数量',"
				+"  `OthereFee` int(11) NOT NULL DEFAULT '0' COMMENT '金額',"
				+"  `TaxFg` int(11) NOT NULL DEFAULT '0' COMMENT '税区分',"
				+"  `TaxRate` int(11) NOT NULL DEFAULT '0' COMMENT '税率',"
				+"  `ConsumptionTax` int(11) NOT NULL DEFAULT '0' COMMENT '消費税',"
				+"  `WithOutTaxTotal` int(11) NOT NULL DEFAULT '0' COMMENT '税別合計金額',"
				+"  `TotalFee` int(11) NOT NULL DEFAULT '0' COMMENT '税込請求額合計',"
				+"  `FeeFixFg` int(11) NOT NULL DEFAULT '0' COMMENT '確定区分',"
				+"  `EntryDate` datetime DEFAULT NULL COMMENT '登録日',"
				+"  `UpdateDate` datetime DEFAULT NULL COMMENT '更新日',"
				+"  `EntryUser` varchar(50) DEFAULT NULL COMMENT '登録者',"
				+"  `UpdateUser` varchar(50) DEFAULT NULL COMMENT '更新者',"
				+"  `FeeNo` int(11) NOT NULL DEFAULT '0',"
				+"  PRIMARY KEY (`ClCd`,`WhCd`,`ShimeDate`,`Seq`)"
				+") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='倉庫その他請求ヘッダ';";
		return sql;
	}
	
	private static String WW014001WhFeeInvoiceTableCreateSql() {
		//倉庫その他請求明細テーブルを作る
		String sql = ""
				+"CREATE TABLE `WW014001WhFeeInvoice` ("
				+"  `ClCd` varchar(20) NOT NULL DEFAULT '' COMMENT '荷主コード',"
				+"  `WhCd` varchar(20) NOT NULL DEFAULT '' COMMENT '倉庫コード',"
				+"  `FeeNo` int(11) NOT NULL DEFAULT '0' COMMENT '請求番号',"
				+"  `FeeType` varchar(20) NOT NULL DEFAULT '' COMMENT '料金タイプ',"
				+"  `FeeCd` varchar(20) NOT NULL DEFAULT '' COMMENT '料金コード',"
				+"  `Seq` int(11) NOT NULL DEFAULT '0' COMMENT 'シーケンシャル番号',"
				+"  `FeeName` varchar(200) NOT NULL DEFAULT '' COMMENT '料金名',"
				+"  `ShimeDate` datetime NOT NULL COMMENT '締め日',"
				+"  `CuttingDate` datetime DEFAULT NULL COMMENT '期締め日',"
				+"  `BaseFee` int(11) NOT NULL DEFAULT '0' COMMENT '基本料金',"
				+"  `SlipFee` int(11) NOT NULL DEFAULT '0' COMMENT '伝票基本料金単価',"
				+"  `SlipFeeTotal` int(11) NOT NULL DEFAULT '0' COMMENT '伝票基本料金合計',"
				+"  `ShipUnitFee` float NOT NULL DEFAULT '0' COMMENT '料金単価',"
				+"  `VolTotal` float NOT NULL DEFAULT '0' COMMENT '量合計',"
				+"  `FeeUnit` int(11) NOT NULL DEFAULT '0' COMMENT '課金単位',"
				+"  `ShipFeeTotal` int(11) NOT NULL DEFAULT '0' COMMENT '料金',"
				+"  `TaxFg` int(11) NOT NULL DEFAULT '0' COMMENT '税区分',"
				+"  `TaxRate` int(11) NOT NULL DEFAULT '0' COMMENT '税率',"
				+"  `ConsumptionTax` int(11) NOT NULL DEFAULT '0' COMMENT '消費税',"
				+"  `WithOutTaxTotal` int(11) NOT NULL DEFAULT '0' COMMENT '税別合計金額',"
				+"  `TotalFee` int(11) NOT NULL DEFAULT '0' COMMENT '税込請求額合計',"
				+"  `PrtFg` int(11) NOT NULL DEFAULT '0' COMMENT '請求書印刷区分',"
				+"  `DataOutFg` int(11) NOT NULL DEFAULT '0' COMMENT '請求データ抽出区分',"
				+"  `AllTotalConsumptionTax` int(11) NOT NULL DEFAULT '0' COMMENT '請求単位の消費税',"
				+"  `AllTotalWithOutTaxTotal` int(11) NOT NULL DEFAULT '0' COMMENT '請求単位の税別合計金額',"
				+"  `AllTotalFee` int(11) DEFAULT '0' COMMENT '請求単位の税込請求額合計',"
				+"  `EntryDate` datetime DEFAULT NULL COMMENT '登録日',"
				+"  `UpdateDate` datetime DEFAULT NULL COMMENT '更新日',"
				+"  `EntryUser` varchar(50) DEFAULT NULL COMMENT '登録者',"
				+"  `UpdateUser` varchar(50) DEFAULT NULL COMMENT '更新者',"
				+"  `SlipCount` int(11) NOT NULL DEFAULT '0' COMMENT '伝票枚数',"
				+"  PRIMARY KEY (`ClCd`,`WhCd`,`FeeNo`,`FeeType`,`FeeCd`,`Seq`,`ShimeDate`,`TaxFg`,`TaxRate`)"
				+") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='倉庫その他請求明細';";
		return sql;
	}
	
	
	
	
	
	
	
	
	//郵便番号データベースの必要なテーブルを確認する
	private static void PostDBCheck() {
		String[] TableName=TabeleList("POST");
		
		boolean PostUnHitFg = true; 
		
		for(int i=0;i<TableName.length;i++) {
			switch(TableName[i]){
				case "M0010_PostMst":
					PostUnHitFg = false; 
					break;
				default:
					break;
			}
		}
		
		if(PostUnHitFg) {
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
		sql = sql + ";";
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