import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class M00041DeliveryComversionMstRt{
	public static Object[][] DeliveryComversionMstRt(ArrayList SearchClGpCD,ArrayList SearchCldeCD,ArrayList SearchDECD,ArrayList SearchDepartmentCd,
			ArrayList SearchName,ArrayList SearchAdd,ArrayList SearchPost,ArrayList SearchTel,ArrayList SearchFax,
			ArrayList SearchMail,ArrayList SearchCom,boolean AllSearch){
		Object[][] rt = new Object[0][32];
		boolean SearchKick = false;
		if(AllSearch) {SearchKick = true;}
		
		String sql = "select "
				+"(KM0041_DELIVERY_COMVERSIONMST.ClGpCD) as ClGpCD,\n"				//荷主コード
				+"(KM0041_DELIVERY_COMVERSIONMST.CL_DECD) as CL_DECD,\n"			//荷主届け先コード
				+"(KM0041_DELIVERY_COMVERSIONMST.DECD) as DECD,\n"					//宮田運輸届け先コード
				+"(KM0041_DELIVERY_COMVERSIONMST.DepartmentCd) as DepartmentCd,\n"	//部署CD
				+"(KM0040_DELIVERYMST.DEName01) as DEName01,\n"						//納品先名1
				+"(KM0040_DELIVERYMST.DEName02) as DEName02,\n"						//納品先名2
				+"(KM0040_DELIVERYMST.DEName03) as DEName03,\n"						//納品先名3
				+"(KM0040_DELIVERYMST.Post) as Post,\n"								//納品先郵便
				+"(KM0040_DELIVERYMST.Add01) as Add01,\n"							//納品先住所1
				+"(KM0040_DELIVERYMST.Add02) as Add02,\n"							//納品先住所2
				+"(KM0040_DELIVERYMST.Add03) as Add03,\n"							//納品先住所3
				+"(KM0040_DELIVERYMST.Tel) as Tel,\n"								//納品先電話
				+"(KM0040_DELIVERYMST.Fax) as Fax,\n"								//納品先FAX
				+"(KM0040_DELIVERYMST.Mail) as Mail,\n"								//納品先MAIL
				+"(KM0040_DELIVERYMST.Com01) as DeCom01,\n"							//(届け先マスタ)コメント1
				+"(KM0040_DELIVERYMST.Com02) as DeCom02,\n"							//(届け先マスタ)コメント2
				+"(KM0040_DELIVERYMST.Com03) as DeCom03,\n"							//(届け先マスタ)コメント3
				+"(KM0040_DELIVERYMST.PrefecturesCd) as PrefecturesCd,\n"			//JIS県CD2桁
				+"(KM0040_DELIVERYMST.MunicipalityCd) as MunicipalityCd,\n"			//JIS市区町村CD5桁
				+"(KM0040_DELIVERYMST.PTMSCD) as PTMSCD,\n"							//基幹システム発着地コード
				+"(KM0041_DELIVERY_COMVERSIONMST.SetName) as SetName,\n"			//送り状登録名
				+"(KM0041_DELIVERY_COMVERSIONMST.Com01) as Com01,\n"				//コメント01
				+"(KM0041_DELIVERY_COMVERSIONMST.Com02) as Com02,\n"				//コメント02
				+"(KM0041_DELIVERY_COMVERSIONMST.Com03) as Com03,\n"				//コメント03
				+"(KM0041_DELIVERY_COMVERSIONMST.Com04) as Com04,\n"				//コメント04
				+"(KM0041_DELIVERY_COMVERSIONMST.Com05) as Com05,\n"				//コメント05
				+"(KM0041_DELIVERY_COMVERSIONMST.EntryDate) as EntryDate,\n"		//データ登録日時
				+"(KM0041_DELIVERY_COMVERSIONMST.UpdateDate) as UpdateDate,\n"		//データ更新日時
				+"(KM0041_DELIVERY_COMVERSIONMST.EntryUser) as EntryUser,\n"		//登録者コード
				+"(KM0041_DELIVERY_COMVERSIONMST.UpdateUser) as UpdateUser,\n"		//更新者コード
				+"(KM0041_DELIVERY_COMVERSIONMST.MstPriorityFirstFg) as MstPriorityFirstFg \n"	//マスタ優先フラグ
				+ " from KM0041_DELIVERY_COMVERSIONMST"
				+ " left outer join KM0040_DELIVERYMST"
				+ " on (KM0041_DELIVERY_COMVERSIONMST.DECD = KM0040_DELIVERYMST.DECD"
				+ " and KM0041_DELIVERY_COMVERSIONMST.DepartmentCd = KM0040_DELIVERYMST.DepartmentCd)"
				+ " where 1=1 ";
		
		if(null!=SearchClGpCD&&0<SearchClGpCD.size()) {
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchClGpCD.size();i++) {
				if(0<i) {sql = sql + " or ";}
				sql = sql + "KM0041_DELIVERY_COMVERSIONMST.ClGpCD = '"+SearchClGpCD.get(i)+"'\n";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchCldeCD&&0<SearchCldeCD.size()) {
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchCldeCD.size();i++) {
				if(0<i) {sql = sql + " or ";}
				sql = sql + "KM0041_DELIVERY_COMVERSIONMST.CL_DECD = '"+SearchCldeCD.get(i)+"'\n";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchDECD&&0<SearchDECD.size()) {
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchDECD.size();i++) {
				if(0<i) {sql = sql + " or ";}
				sql = sql + "KM0041_DELIVERY_COMVERSIONMST.DECD = '"+SearchDECD.get(i)+"'\n";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchDepartmentCd&&0<SearchDepartmentCd.size()) {
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchDepartmentCd.size();i++) {
				if(0<i) {sql = sql + " or ";}
				sql = sql + "KM0041_DELIVERY_COMVERSIONMST.DepartmentCd = '"+SearchDepartmentCd.get(i)+"'\n";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchName&&0<SearchName.size()) {
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchName.size();i++) {
				if(0<i) {sql = sql + " or ";}
				sql = sql + "KM0040_DELIVERYMST.DEName01 like '%"+SearchName.get(i)+"%'\n";
				sql = sql + " or KM0040_DELIVERYMST.DEName02 like '%"+SearchName.get(i)+"%'\n";
				sql = sql + " or KM0040_DELIVERYMST.DEName03 like '%"+SearchName.get(i)+"%'\n";
				sql = sql + " or KM0041_DELIVERY_COMVERSIONMST.SetName like '%"+SearchName.get(i)+"%'\n";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchAdd&&0<SearchAdd.size()) {
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchAdd.size();i++) {
				if(0<i) {sql = sql + " or ";}
				sql = sql + "KM0040_DELIVERYMST.Add01 like '%"+SearchAdd.get(i)+"%'\n";
				sql = sql + " or KM0040_DELIVERYMST.Add02 like '%"+SearchAdd.get(i)+"%'\n";
				sql = sql + " or KM0040_DELIVERYMST.Add03 like '%"+SearchAdd.get(i)+"%'\n";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchPost&&0<SearchPost.size()) {
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchPost.size();i++) {
				if(0<i) {sql = sql + " or ";}
				sql = sql + "KM0040_DELIVERYMST.Post like '"+SearchPost.get(i)+"%'\n";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchTel&&0<SearchTel.size()) {
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchTel.size();i++) {
				if(0<i) {sql = sql + " or ";}
				sql = sql + "KM0040_DELIVERYMST.Tel like '%"+SearchTel.get(i)+"%'\n";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchFax&&0<SearchFax.size()) {
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchFax.size();i++) {
				if(0<i) {sql = sql + " or ";}
				sql = sql + "KM0040_DELIVERYMST.Fax like '%"+SearchFax.get(i)+"%'\n";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchMail&&0<SearchMail.size()) {
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchMail.size();i++) {
				if(0<i) {sql = sql + " or ";}
				sql = sql + "KM0040_DELIVERYMST.Mail like '%"+SearchMail.get(i)+"%'\n";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchCom&&0<SearchCom.size()) {
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchCom.size();i++) {
				if(0<i) {sql = sql + " or ";}
				sql = sql + "KM0040_DELIVERYMST.Com01 like '%"+SearchCom.get(i)+"%'\n";
				sql = sql + " or KM0040_DELIVERYMST.Com02 like '%"+SearchCom.get(i)+"%'\n";
				sql = sql + " or KM0040_DELIVERYMST.Com03 like '%"+SearchCom.get(i)+"%'\n";
				sql = sql + " or KM0041_DELIVERY_COMVERSIONMST.Com01 like '%"+SearchCom.get(i)+"%'\n";
				sql = sql + " or KM0041_DELIVERY_COMVERSIONMST.Com02 like '%"+SearchCom.get(i)+"%'\n";
				sql = sql + " or KM0041_DELIVERY_COMVERSIONMST.Com03 like '%"+SearchCom.get(i)+"%'\n";
				sql = sql + " or KM0041_DELIVERY_COMVERSIONMST.Com04 like '%"+SearchCom.get(i)+"%'\n";
				sql = sql + " or KM0041_DELIVERY_COMVERSIONMST.Com05 like '%"+SearchCom.get(i)+"%'\n";
			}
			sql = sql + ")";
		}
		
		sql = sql + "order by KM0041_DELIVERY_COMVERSIONMST.ClGpCD,KM0041_DELIVERY_COMVERSIONMST.CL_DECD,KM0041_DELIVERY_COMVERSIONMST.DepartmentCd";	
		//System.out.println(sql);
		
		if(SearchKick) {
			A00010DbConnect.DB_CONN("NANKO");
			ResultSet rset01 = null;
			Statement stmt01 = null;
			try {
				stmt01 = A00010DbConnect.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					      ResultSet.CONCUR_UPDATABLE);
				rset01 = stmt01.executeQuery(sql);
				int counter = 0;
				rset01.beforeFirst();
				while (rset01.next()) {
					counter=counter+1;
				}
				rt = new Object[counter][31];
				counter = 0;
				rset01.beforeFirst();
				while (rset01.next()) {
					if(null==rset01.getString("ClGpCD")) {rt[counter][0] = "";}else {rt[counter][0] = rset01.getString("ClGpCD");}						//荷主グループコード
					if(null==rset01.getString("CL_DECD")) {rt[counter][1] = "";}else {rt[counter][1] = rset01.getString("CL_DECD");}					//荷主届け先コード
					if(null==rset01.getString("DECD")) {rt[counter][2] = "";}else {rt[counter][2] = rset01.getString("DECD");}							//宮田運輸届け先コード
					if(null==rset01.getString("DepartmentCd")) {rt[counter][3] = "";}else {rt[counter][3] = rset01.getString("DepartmentCd");}			//部署CD
					if(null==rset01.getString("DEName01")) {rt[counter][4] = "";}else {rt[counter][4] = rset01.getString("DEName01");}					//納品先名1
					if(null==rset01.getString("DEName02")) {rt[counter][5] = "";}else {rt[counter][5] = rset01.getString("DEName02");}					//納品先名2
					if(null==rset01.getString("DEName03")) {rt[counter][6] = "";}else {rt[counter][6] = rset01.getString("DEName03");}					//納品先名3
					if(null==rset01.getString("Post")) {rt[counter][7] = "";}else {rt[counter][7] = rset01.getString("Post");}							//納品先郵便
					if(null==rset01.getString("Add01")) {rt[counter][8] = "";}else {rt[counter][8] = rset01.getString("Add01");}						//納品先住所1
					if(null==rset01.getString("Add02")) {rt[counter][9] = "";}else {rt[counter][9] = rset01.getString("Add02");}						//納品先住所2
					if(null==rset01.getString("Add03")) {rt[counter][10] = "";}else {rt[counter][10] = rset01.getString("Add03");}						//納品先住所3
					if(null==rset01.getString("Tel")) {rt[counter][11] = "";}else {rt[counter][11] = rset01.getString("Tel");}							//納品先電話
					if(null==rset01.getString("Fax")) {rt[counter][12] = "";}else {rt[counter][12] = rset01.getString("Fax");}							//納品先FAX
					if(null==rset01.getString("Mail")) {rt[counter][13] = "";}else {rt[counter][13] = rset01.getString("Mail");}						//納品先MAIL
					if(null==rset01.getString("DeCom01")) {rt[counter][14] = "";}else {rt[counter][14] = rset01.getString("DeCom01");}					//(届け先マスタ)コメント1
					if(null==rset01.getString("DeCom02")) {rt[counter][15] = "";}else {rt[counter][15] = rset01.getString("DeCom02");}					//(届け先マスタ)コメント2
					if(null==rset01.getString("DeCom03")) {rt[counter][16] = "";}else {rt[counter][16] = rset01.getString("DeCom03");}					//(届け先マスタ)コメント3
					if(null==rset01.getString("PrefecturesCd")) {rt[counter][17] = "";}else {rt[counter][17] = rset01.getString("PrefecturesCd");}		//JIS県CD2桁
					if(null==rset01.getString("MunicipalityCd")) {rt[counter][18] = "";}else {rt[counter][18] = rset01.getString("MunicipalityCd");}	//JIS市区町村CD5桁
					if(null==rset01.getString("PTMSCD")) {rt[counter][19] = "";}else {rt[counter][19] = rset01.getString("PTMSCD");}					//基幹システム発着地コード
					if(null==rset01.getString("SetName")) {rt[counter][20] = "";}else {rt[counter][20] = rset01.getString("SetName");}					//送り状登録名
					if(null==rset01.getString("Com01")) {rt[counter][21] = "";}else {rt[counter][21] = rset01.getString("Com01");}						//コメント01
					if(null==rset01.getString("Com02")) {rt[counter][22] = "";}else {rt[counter][22] = rset01.getString("Com02");}						//コメント02
					if(null==rset01.getString("Com03")) {rt[counter][23] = "";}else {rt[counter][23] = rset01.getString("Com03");}						//コメント03
					if(null==rset01.getString("Com04")) {rt[counter][24] = "";}else {rt[counter][24] = rset01.getString("Com04");}						//コメント04
					if(null==rset01.getString("Com05")) {rt[counter][25] = "";}else {rt[counter][25] = rset01.getString("Com05");}						//コメント05
					if(null==rset01.getTimestamp("EntryDate")){rt[counter][26] = "";}else{rt[counter][26] = B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("EntryDate"))[1];}	//データ登録日時
					if(null==rset01.getTimestamp("UpdateDate")){rt[counter][27] = "";}else{rt[counter][27] = B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("UpdateDate"))[1];}	//データ更新日時
					if(null==rset01.getString("EntryUser")) {rt[counter][28] = "";}else {rt[counter][28] = rset01.getString("EntryUser");}				//登録者コード
					if(null==rset01.getString("UpdateUser")) {rt[counter][29] = "";}else {rt[counter][29] = rset01.getString("UpdateUser");}			//更新者コード
					rt[counter][30] = rset01.getInt("MstPriorityFirstFg");																				//マスタ優先フラグ
					counter=counter+1;
				}
				if(rset01!=null){rset01.close();}
				if(stmt01!=null){stmt01.close();}
			}catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					if(rset01!=null){rset01.close();}
					if(stmt01!=null){stmt01.close();}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			A00010DbConnect.close();
		}
		return rt;
	}
}