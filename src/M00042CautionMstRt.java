import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class M00042CautionMstRt{
	//戻り値カラム
	public static Object[][] RtSettingCautionMstRt(){
		Object[][] RtSettingCautionMstRt = {
				 {"CautionCd"		,(int) 0	,"String"	,"注意事項コード"}
				,{"ClGpCD"			,(int) 1	,"String"	,"荷主グループコード"}
				,{"CLGpName01"		,(int) 2	,"String"	,"荷主グループ名"}
				,{"DECD"			,(int) 3	,"String"	,"納品先コード"}
				,{"DepartmentCd"	,(int) 4	,"String"	,"部署CD"}
				,{"DEName01"		,(int) 5	,"String"	,"届先名"}
				,{"CautionTiming"	,(int) 6	,"int"		,"注意事項タイミング"}
				,{"CautionName"		,(int) 7	,"String"	,"注意事項名"}
				,{"Caution"			,(int) 8	,"String"	,"注意事項内容"}
				,{"EntryDate"		,(int) 9	,"String"	,"データ登録日時"}
				,{"UpdateDate"		,(int)10	,"String"	,"データ更新日時"}
				,{"EntryUser"		,(int)11	,"String"	,"登録者コード"}
				,{"UpdateUser"		,(int)12	,"String"	,"更新者コード"}
				};
		
		return RtSettingCautionMstRt;
	}
	
	public static Object[][] CautionMstRt(
			ArrayList<String> SearchCautionCd,
			ArrayList<String> SearchClGpCD,
			ArrayList<String> SearchDECD,
			ArrayList<String> SearchDepartmentCd,
			ArrayList<String> SearchCautionTiming,
			ArrayList<String> SearchCautionName,
			ArrayList<String> SearchCaution,
			ArrayList<String> SearchDeName,
			boolean AllSearch){
		Object[][] rt = new Object[0][13];
		boolean SearchKick = false;
		if(AllSearch) {SearchKick = true;}
		
		String sql = " select \n"
				+"(KM0090_CAUTION.CautionCd) as CautionCd,\n"			//注意事項コード
				+"(KM0090_CAUTION.ClGpCD) as ClGpCD,\n"					//荷主グループコード
				+"(KM0031_CLIENT_GROUP.CLGpName01) as CLGpName01,\n"	//荷主グループ名
				+"(KM0090_CAUTION.DECD) as DECD,\n"						//納品先コード
				+"(KM0090_CAUTION.DepartmentCd) as DepartmentCd,\n"		//部署CD
				+"(KM0040_DELIVERYMST.DEName01) as DEName01,\n"			//届先名
				+"(KM0090_CAUTION.CautionTiming) as CautionTiming,\n"	//注意事項タイミング
				+"(KM0090_CAUTION.CautionName) as CautionName,\n"		//注意事項名
				+"(KM0090_CAUTION.Caution) as Caution,\n"				//注意事項内容
				+"(KM0090_CAUTION.EntryDate) as EntryDate,\n"			//データ登録日時
				+"(KM0090_CAUTION.UpdateDate) as UpdateDate,\n"			//データ更新日時
				+"(KM0090_CAUTION.EntryUser) as EntryUser,\n"			//登録者コード
				+"(KM0090_CAUTION.UpdateUser) as UpdateUser\n"			//更新者コード
				+ " from "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0090_CAUTION\n"
				+ " left outer join "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP"
				+ " on("
				+ " "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0090_CAUTION.ClGpCD = "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.ClGpCD"
				+ " )"
				+ " left outer join "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0040_DELIVERYMST"
				+ " on("
				+ " "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0090_CAUTION.DECD = "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0040_DELIVERYMST.DECD"
				+ " )"
				+ " where 1=1\n";
		
		if(null!=SearchCautionCd && 0<SearchCautionCd.size()){
			SearchKick = true;
			sql=sql+" and(";
			for(int i=0;i<SearchCautionCd.size();i++){
				if(0<i){sql = sql+" or ";}
				sql = sql + " KM0090_CAUTION.CautionCd = ?";
			}
			sql=sql+")";
		}
		if(null!= SearchClGpCD && 0<SearchClGpCD.size()){
			SearchKick = true;
			sql=sql+" and(";
			for(int i=0;i<SearchClGpCD.size();i++){
				if(0<i){sql = sql+" or ";}
				sql = sql + " KM0090_CAUTION.ClGpCD = ?";
			}
			sql=sql+")";
		}
		if(null!=SearchDECD && 0<SearchDECD.size()){
			SearchKick = true;
			sql=sql+" and(";
			for(int i=0;i<SearchDECD.size();i++){
				if(0<i){sql = sql+" or ";}
				sql = sql + " KM0090_CAUTION.DECD = ?";
			}
			sql=sql+")";
		}
		if(null!= SearchDepartmentCd && 0<SearchDepartmentCd.size()){
			SearchKick = true;
			sql=sql+" and(";
			for(int i=0;i<SearchDepartmentCd.size();i++){
				if(0<i){sql = sql+" or ";}
				sql = sql + " KM0090_CAUTION.DepartmentCd = ?";
			}
			sql=sql+")";
		}
		if(null!=SearchCautionTiming && 0<SearchCautionTiming.size()){
			SearchKick = true;
			sql=sql+" and(";
			for(int i=0;i<SearchCautionTiming.size();i++){
				if(0<i){sql = sql+" or ";}
				sql = sql + " KM0090_CAUTION.CautionTiming = ?";
			}
			sql=sql+")";
		}
		if(null!= SearchCautionName && 0<SearchCautionName.size()){
			SearchKick = true;
			sql=sql+" and(";
			for(int i=0;i<SearchCautionName.size();i++){
				if(0<i){sql = sql+" or ";}
				sql = sql + " KM0090_CAUTION.CautionName like ?";
			}
			sql=sql+")";
		}
		if(null!=SearchCaution && 0<SearchCaution.size()){
			SearchKick = true;
			sql=sql+" and(";
			for(int i=0;i<SearchCaution.size();i++){
				if(0<i){sql = sql+" or ";}
				sql = sql + " KM0090_CAUTION.Caution like ?";
			}
			sql=sql+")";
		}		
		if(null!=SearchDeName && 0<SearchDeName.size()){
			SearchKick = true;
			sql=sql+" and(";
			for(int i=0;i<SearchDeName.size();i++){
				if(0<i){sql = sql+" or ";}
				sql = sql + " KM0040_DELIVERYMST.DEName01 like ?";
				sql = sql + " or KM0040_DELIVERYMST.DEName02 like ?";
				sql = sql + " or KM0040_DELIVERYMST.DEName03 like ?";
			}
			sql=sql+")";
		}
		sql = sql + " order by KM0090_CAUTION.CautionCd,KM0090_CAUTION.CautionTiming\n";
		
		if(SearchKick) {
			//System.out.println(sql);
			A00010DbConnect.DB_CONN("NYANKO");
			ResultSet rset01 = null;
			PreparedStatement stmt01 = null;
			try {
				stmt01 = A00010DbConnect.conn.prepareStatement(sql);
				int StmtCount = 0;
				
				if(null!=SearchCautionCd && 0<SearchCautionCd.size()){
					for(int i=0;i<SearchCautionCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchCautionCd.get(i)+"");
					}
				}
				if(null!= SearchClGpCD && 0<SearchClGpCD.size()){
					for(int i=0;i<SearchClGpCD.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchClGpCD.get(i)+"");
					}
				}
				if(null!=SearchDECD && 0<SearchDECD.size()){
					for(int i=0;i<SearchDECD.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchDECD.get(i)+"");
					}
				}
				if(null!= SearchDepartmentCd && 0<SearchDepartmentCd.size()){
					for(int i=0;i<SearchDepartmentCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchDepartmentCd.get(i)+"");
					}
				}
				if(null!=SearchCautionTiming && 0<SearchCautionTiming.size()){
					for(int i=0;i<SearchCautionTiming.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchCautionTiming.get(i)+"");
					}
				}
				if(null!= SearchCautionName && 0<SearchCautionName.size()){
					for(int i=0;i<SearchCautionName.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchCautionName.get(i)+"%");
					}
				}
				if(null!=SearchCaution && 0<SearchCaution.size()){
					for(int i=0;i<SearchCaution.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchCaution.get(i)+"%");
					}
				}		
				if(null!=SearchDeName && 0<SearchDeName.size()){
					for(int i=0;i<SearchDeName.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchDeName.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchDeName.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchDeName.get(i)+"%");
					}
				}
				rset01 = stmt01.executeQuery();
				
				int counter = 0;
				rset01.beforeFirst();
				while (rset01.next()) {
					counter=counter+1;
				}
	
				rt = new Object[counter][13];
				counter = 0;
				rset01.beforeFirst();
				while (rset01.next()) {
					if(null==rset01.getString("CautionCd")){rt[counter][0]="";}else{rt[counter][0]=rset01.getString("CautionCd");}												//注意事項コード
					if(null==rset01.getString("ClGpCD")){rt[counter][1]="";}else{rt[counter][1]=rset01.getString("ClGpCD");}													//荷主グループコード
					if(null==rset01.getString("CLGpName01")){rt[counter][2]="";}else{rt[counter][2]=rset01.getString("CLGpName01");}											//荷主グループ名
					if(null==rset01.getString("DECD")){rt[counter][3]="";}else{rt[counter][3]=rset01.getString("DECD");}														//納品先コード
					if(null==rset01.getString("DepartmentCd")){rt[counter][4]="";}else{rt[counter][4]=rset01.getString("DepartmentCd");}										//部署CD
					if(null==rset01.getString("DEName01")){rt[counter][5]="";}else{rt[counter][5]=rset01.getString("DEName01");}												//届先名
					rt[counter][6]=rset01.getInt("CautionTiming");																												//注意事項タイミング
					if(null==rset01.getString("CautionName")){rt[counter][7]="";}else{rt[counter][7]=rset01.getString("CautionName");}											//注意事項名
					if(null==rset01.getString("Caution")){rt[counter][8]="";}else{rt[counter][8]=rset01.getString("Caution");}													//注意事項内容
					if(null==rset01.getTimestamp("EntryDate")){rt[counter][9]="";}else{rt[counter][9]=B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("EntryDate"))[1];}			//データ登録日時
					if(null==rset01.getTimestamp("UpdateDate")){rt[counter][10]="";}else{rt[counter][10]=B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("UpdateDate"))[1];}		//データ更新日時
					if(null==rset01.getString("EntryUser")){rt[counter][11]="";}else{rt[counter][11]=rset01.getString("EntryUser");}											//登録者コード
					if(null==rset01.getString("UpdateUser")){rt[counter][12]="";}else{rt[counter][12]=rset01.getString("UpdateUser");}											//更新者コード
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