import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class M00040DeliveryMstRt{
	public static Object[][] DeliveryMstRt(
			ArrayList SearchDECD,			//検索条件届先CD
			ArrayList SearchDepartmentCd,	//検索条件届先部署CD
			ArrayList SearchDEName,			//検索条件届先名
			ArrayList SearchPost,			//検索条件届先郵便
			ArrayList SearchAdd,			//検索条件届先住所
			ArrayList SearchTel,			//検索条件届先TEL
			ArrayList SearchFax,			//検索条件届先FAX
			ArrayList SearchMail,			//検索条件届先MAIL
			ArrayList SearchCom,			//検索条件届先コメント
			ArrayList SearchPrefecturesCd,	//検索条件届先県CD
			ArrayList SearchMunicipalityCd,	//検索条件届先市区町村CD
			boolean AllSearch
			){
		Object[][] rt = new Object[0][25];
		boolean SearchKick = false;
		if(AllSearch) {SearchKick=true;}
		
		String sql = " select "
			+"(KM0040_DELIVERYMST.DECD) as DECD,\n"						//納品先コード
			+"(KM0040_DELIVERYMST.DepartmentCd) as DepartmentCd,\n"		//部署CD
			+"(KM0040_DELIVERYMST.DEName01) as DEName01,\n"				//納品先名1
			+"(KM0040_DELIVERYMST.DEName02) as DEName02,\n"				//納品先名2
			+"(KM0040_DELIVERYMST.DEName03) as DEName03,\n"				//納品先名3
			+"(KM0040_DELIVERYMST.Post) as Post,\n"						//納品先郵便
			+"(KM0040_DELIVERYMST.Add01) as Add01,\n"					//納品先住所1
			+"(KM0040_DELIVERYMST.Add02) as Add02,\n"					//納品先住所2
			+"(KM0040_DELIVERYMST.Add03) as Add03,\n"					//納品先住所3
			+"(KM0040_DELIVERYMST.Tel) as Tel,\n"						//納品先電話
			+"(KM0040_DELIVERYMST.Fax) as Fax,\n"						//納品先FAX
			+"(KM0040_DELIVERYMST.Mail) as Mail,\n"						//納品先MAIL
			+"(KM0040_DELIVERYMST.Com01) as Com01,\n"					//コメント1
			+"(KM0040_DELIVERYMST.Com02) as Com02,\n"					//コメント2
			+"(KM0040_DELIVERYMST.Com03) as Com03,\n"					//コメント3
			+"(KM0040_DELIVERYMST.PrefecturesCd) as PrefecturesCd,\n"	//JIS県CD2桁
			+"(KM0040_DELIVERYMST.MunicipalityCd) as MunicipalityCd,\n"	//JIS市区町村CD5桁
			+"(KM0040_DELIVERYMST.PTMSCD) as PTMSCD,\n"					//基幹システム発着地コード
			+"(KM0040_DELIVERYMST.EntryDate) as EntryDate,\n"			//データ登録日時
			+"(KM0040_DELIVERYMST.UpdateDate) as UpdateDate,\n"			//データ更新日時
			+"(KM0040_DELIVERYMST.EntryUser) as EntryUser,\n"			//登録者コード
			+"(KM0040_DELIVERYMST.UpdateUser) as UpdateUser,\n"			//更新者コード
			+"(KM0040_DELIVERYMST.FirstClient) as FirstClient,\n"		//登録した荷主CD
			+"(KM0040_DELIVERYMST.LastClient) as LastClient,\n"			//更新した荷主CD
			+"(KM0040_DELIVERYMST.DelFg) as DelFg\n"					//削除区分
			
			+" from KM0040_DELIVERYMST"
			+" where 1=1\n";
		
		if(null!=SearchDECD && 0<SearchDECD.size()){						//検索条件届先CD
			SearchKick=true;
			sql = sql + " and(";
			for(int i=0;i<SearchDECD.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + "KM0040_DELIVERYMST.DECD ='"+SearchDECD.get(i)+"'";
			}
			sql= sql + ")\n";
		}
		if(null!=SearchDepartmentCd && 0<SearchDepartmentCd.size()){		//検索条件届先部署CD
			SearchKick=true;
			sql = sql + " and(";
			for(int i=0;i<SearchDepartmentCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + "KM0040_DELIVERYMST.DepartmentCd ='"+SearchDepartmentCd.get(i)+"'";
			}
			sql= sql + ")\n";
		}
		if(null!=SearchDEName && 0<SearchDEName.size()){					//検索条件届先名
			SearchKick=true;
			sql = sql + " and(";
			for(int i=0;i<SearchDEName.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + "KM0040_DELIVERYMST.DEName01 like '%"+SearchDEName.get(i)+"%'";
				sql = sql + " or KM0040_DELIVERYMST.DEName02 like '%"+SearchDEName.get(i)+"%'";
				sql = sql + " or KM0040_DELIVERYMST.DEName03 like '%"+SearchDEName.get(i)+"%'";
			}
			sql= sql + ")\n";
		}
		if(null!=SearchPost && 0<SearchPost.size()){						//検索条件届先郵便
			SearchKick=true;
			sql = sql + " and(";
			for(int i=0;i<SearchPost.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + "KM0040_DELIVERYMST.Post like '"+SearchPost.get(i)+"%'";
			}
			sql= sql + ")\n";
		}
		if(null!=SearchAdd && 0<SearchAdd.size()){						//検索条件届先住所
			SearchKick=true;
			sql = sql + " and(";
			for(int i=0;i<SearchAdd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + "KM0040_DELIVERYMST.Add01 like '%"+SearchAdd.get(i)+"%'";
				sql = sql + " or KM0040_DELIVERYMST.Add02 like '%"+SearchAdd.get(i)+"%'";
				sql = sql + " or KM0040_DELIVERYMST.Add03 like '%"+SearchAdd.get(i)+"%'";
			}
			sql= sql + ")\n";
		}
		if(null!=SearchTel && 0<SearchTel.size()){						//検索条件届先TEL
			SearchKick=true;
			sql = sql + " and(";
			for(int i=0;i<SearchTel.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + "KM0040_DELIVERYMST.Tel like '%"+SearchTel.get(i)+"%'";
			}
			sql= sql + ")\n";
		}
		if(null!=SearchFax && 0<SearchFax.size()){						//検索条件届先FAX
			SearchKick=true;
			sql = sql + " and(";
			for(int i=0;i<SearchFax.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + "KM0040_DELIVERYMST.Fax like '%"+SearchFax.get(i)+"%'";
			}
			sql= sql + ")\n";
		}
		if(null!=SearchMail && 0<SearchMail.size()){						//検索条件届先MAIL
			SearchKick=true;
			sql = sql + " and(";
			for(int i=0;i<SearchMail.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + "KM0040_DELIVERYMST.Mail like '%"+SearchMail.get(i)+"%'";
			}
			sql= sql + ")\n";
		}
		if(null!=SearchCom && 0<SearchCom.size()){						//検索条件届先コメント
			SearchKick=true;
			sql = sql + " and(";
			for(int i=0;i<SearchCom.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + "KM0040_DELIVERYMST.Com01 like '%"+SearchCom.get(i)+"%'";
				sql = sql + " or KM0040_DELIVERYMST.Com02 like '%"+SearchCom.get(i)+"%'";
				sql = sql + " or KM0040_DELIVERYMST.Com03 like '%"+SearchCom.get(i)+"%'";
			}
			sql= sql + ")\n";
		}
		if(null!=SearchPrefecturesCd && 0<SearchPrefecturesCd.size()){	//検索条件届先県CD
			SearchKick=true;
			sql = sql + " and(";
			for(int i=0;i<SearchPrefecturesCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + "KM0040_DELIVERYMST.PrefecturesCd ='"+SearchPrefecturesCd.get(i)+"'";
			}
			sql= sql + ")";
		}
		if(null!=SearchMunicipalityCd && 0<SearchMunicipalityCd.size()){	//検索条件届先市区町村CD
			SearchKick=true;
			sql = sql + " and(";
			for(int i=0;i<SearchMunicipalityCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + "KM0040_DELIVERYMST.MunicipalityCd ='"+SearchMunicipalityCd.get(i)+"'";
			}
			sql= sql + ")\n";
		}
		
		
		if(AllSearch) {
		}else {
			sql = sql + " and KM0040_DELIVERYMST.DelFg = '0'";
		}
		
		sql = sql + " order by KM0040_DELIVERYMST.DECD,KM0040_DELIVERYMST.DepartmentCd";
		
		//System.out.println(sql);
		if(SearchKick) {
			A00010DbConnect.DB_CONN("NYANKO");
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

				rt = new Object[counter][25];
				counter = 0;
				rset01.beforeFirst();
				while (rset01.next()) {
					if(null==rset01.getString("DECD")){rt[counter][0] = "";}else{rt[counter][0] = rset01.getString("DECD");}						//納品先コード
					if(null==rset01.getString("DepartmentCd")){rt[counter][1] = "";}else{rt[counter][1] = rset01.getString("DepartmentCd");}		//部署CD
					if(null==rset01.getString("DEName01")){rt[counter][2] = "";}else{rt[counter][2] = rset01.getString("DEName01");}				//納品先名1
					if(null==rset01.getString("DEName02")){rt[counter][3] = "";}else{rt[counter][3] = rset01.getString("DEName02");}				//納品先名2
					if(null==rset01.getString("DEName03")){rt[counter][4] = "";}else{rt[counter][4] = rset01.getString("DEName03");}				//納品先名3
					if(null==rset01.getString("Post")){rt[counter][5] = "";}else{rt[counter][5] = rset01.getString("Post");	}						//納品先郵便
					if(null==rset01.getString("Add01")){rt[counter][6] = "";}else{rt[counter][6] = rset01.getString("Add01");}						//納品先住所1
					if(null==rset01.getString("Add02")){rt[counter][7] = "";}else{rt[counter][7] = rset01.getString("Add02");}						//納品先住所2
					if(null==rset01.getString("Add03")){rt[counter][8] = "";}else{rt[counter][8] = rset01.getString("Add03");}						//納品先住所3
					if(null==rset01.getString("Tel")){rt[counter][9] = "";}else{rt[counter][9] = rset01.getString("Tel");}							//納品先電話
					if(null==rset01.getString("Fax")){rt[counter][10] = "";}else{rt[counter][10] = rset01.getString("Fax");}						//納品先FAX
					if(null==rset01.getString("Mail")){rt[counter][11] = "";}else{rt[counter][11] = rset01.getString("Mail");}						//納品先MAIL
					if(null==rset01.getString("Com01")){rt[counter][12] = "";}else{rt[counter][12] = rset01.getString("Com01");}					//コメント1
					if(null==rset01.getString("Com02")){rt[counter][13] = "";}else{rt[counter][13] = rset01.getString("Com02");}					//コメント2
					if(null==rset01.getString("Com03")){rt[counter][14] = "";}else{rt[counter][14] = rset01.getString("Com03");}					//コメント3
					if(null==rset01.getString("PrefecturesCd")){rt[counter][15] = "";}else{rt[counter][15] = rset01.getString("PrefecturesCd");}	//JIS県CD2桁
					if(null==rset01.getString("MunicipalityCd")){rt[counter][16] = "";}else{rt[counter][16] = rset01.getString("MunicipalityCd");}	//JIS市区町村CD5桁
					if(null==rset01.getString("PTMSCD")){rt[counter][17] = "";}else{rt[counter][17] = rset01.getString("PTMSCD");}					//基幹システム発着地コード
					if(null==rset01.getTimestamp("EntryDate")){rt[counter][18] = "";}else{rt[counter][18] = B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("EntryDate"))[1];}				//データ登録日時
					if(null==rset01.getTimestamp("UpdateDate")){rt[counter][19] = "";}else{rt[counter][19] = B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("UpdateDate"))[1];}				//データ更新日時
					if(null==rset01.getString("EntryUser")){rt[counter][20] = "";}else{rt[counter][20] = rset01.getString("EntryUser");}			//登録者コード
					if(null==rset01.getString("UpdateUser")){rt[counter][21] = "";}else{rt[counter][21] = rset01.getString("UpdateUser");}			//更新者コード
					if(null==rset01.getString("FirstClient")){rt[counter][22] = "";}else{rt[counter][22] = rset01.getString("FirstClient");}		//登録した荷主CD
					if(null==rset01.getString("LastClient")){rt[counter][23] = "";}else{rt[counter][23] = rset01.getString("LastClient");}			//更新した荷主CD
					rt[counter][24] = rset01.getInt("DelFg");				//削除区分

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