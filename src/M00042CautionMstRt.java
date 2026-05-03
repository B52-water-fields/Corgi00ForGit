import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class M00042CautionMstRt{
	/*
	コピペ用
	ArrayList<String> SearchCautionCd 		= new ArrayList<String>();
	ArrayList<String> SearchClGpCD 			= new ArrayList<String>();
	ArrayList<String> SearchDECD 			= new ArrayList<String>();
	ArrayList<String> SearchDepartmentCd 	= new ArrayList<String>();
	ArrayList<String> SearchCautionTiming 	= new ArrayList<String>();
	ArrayList<String> SearchCautionName 	= new ArrayList<String>();
	ArrayList<String> SearchCaution 		= new ArrayList<String>();
	ArrayList<String> SearchDeName 			= new ArrayList<String>();
	boolean AllSearch = false;
	
	Object[][] CautionMstRt = M00042CautionMstRt.CautionMstRt(
			SearchCautionCd,
			SearchClGpCD,
			SearchDECD,
			SearchDepartmentCd,
			SearchCautionTiming,
			SearchCautionName,
			SearchCaution,
			SearchDeName,
			AllSearch);
			
	String GetCautionCd		= (String)CautionMstRt[i][ColCautionCd];	//注意事項コード
	String GetClGpCD		= (String)CautionMstRt[i][ColClGpCD];		//荷主グループコード
	String GetCLGpName01	= (String)CautionMstRt[i][ColCLGpName01];	//荷主グループ名
	String GetDECD			= (String)CautionMstRt[i][ColDECD];			//届先コード
	String GetDepartmentCd	= (String)CautionMstRt[i][ColDepartmentCd];	//部署CD
	String GetDEName01		= (String)CautionMstRt[i][ColDEName01];		//届先名
	int GetCautionTiming	= (int)CautionMstRt[i][ColCautionTiming];	//注意事項タイミング
	String GetCautionName	= (String)CautionMstRt[i][ColCautionName];	//注意事項名
	String GetCaution		= (String)CautionMstRt[i][ColCaution];		//注意事項内容
	String GetEntryDate		= (String)CautionMstRt[i][ColEntryDate];	//データ登録日時
	String GetUpdateDate	= (String)CautionMstRt[i][ColUpdateDate];	//データ更新日時
	String GetEntryUser		= (String)CautionMstRt[i][ColEntryUser];	//登録者コード
	String GetUpdateUser	= (String)CautionMstRt[i][ColUpdateUser];	//更新者コード
	String GetAdd01			= (String)CautionMstRt[i][ColAdd01];		//届先住所1
	String GetAdd02			= (String)CautionMstRt[i][ColAdd02];		//届先住所2
	String GetAdd03			= (String)CautionMstRt[i][ColAdd03];		//届先住所3
	
	*/
	//戻り値カラム
	static int ColCautionCd		= (int) 0;	//注意事項コード
	static int ColClGpCD			= (int) 1;	//荷主グループコード
	static int ColCLGpName01		= (int) 2;	//荷主グループ名
	static int ColDECD				= (int) 3;	//届先コード
	static int ColDepartmentCd	= (int) 4;	//部署CD
	static int ColDEName01			= (int) 5;	//届先名
	static int ColCautionTiming	= (int) 6;	//注意事項タイミング
	static int ColCautionName		= (int) 7;	//注意事項名
	static int ColCaution			= (int) 8;	//注意事項内容
	static int ColEntryDate		= (int) 9;	//データ登録日時
	static int ColUpdateDate		= (int)10;	//データ更新日時
	static int ColEntryUser		= (int)11;	//登録者コード
	static int ColUpdateUser		= (int)12;	//更新者コード
	static int ColAdd01			= (int)13;	//届先住所1
	static int ColAdd02			= (int)14;	//届先住所2
	static int ColAdd03			= (int)15;	//届先住所3
	
	public static Object[][] RtSettingCautionMstRt(){
		Object[][] RtSettingCautionMstRt = {
				 {"CautionCd"		,ColCautionCd		,"String"	,"注意事項コード"}
				,{"ClGpCD"			,ColClGpCD			,"String"	,"荷主グループコード"}
				,{"CLGpName01"		,ColCLGpName01	,"String"	,"荷主グループ名"}
				,{"DECD"			,ColDECD			,"String"	,"届先コード"}
				,{"DepartmentCd"	,ColDepartmentCd	,"String"	,"部署CD"}
				,{"DEName01"		,ColDEName01		,"String"	,"届先名"}
				,{"CautionTiming"	,ColCautionTiming	,"int"		,"注意事項タイミング"}
				,{"CautionName"		,ColCautionName	,"String"	,"注意事項名"}
				,{"Caution"			,ColCaution		,"String"	,"注意事項内容"}
				,{"EntryDate"		,ColEntryDate		,"String"	,"データ登録日時"}
				,{"UpdateDate"		,ColUpdateDate	,"String"	,"データ更新日時"}
				,{"EntryUser"		,ColEntryUser		,"String"	,"登録者コード"}
				,{"UpdateUser"		,ColUpdateUser	,"String"	,"更新者コード"}
				,{"Add01"			,ColAdd01			,"String"	,"届先住所1"}
				,{"Add02"			,ColAdd02			,"String"	,"届先住所2"}
				,{"Add03"			,ColAdd03			,"String"	,"届先住所3"}
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
		
		SearchCautionCd		= B00150ArrayListControl.ArryListStringUniqueList(SearchCautionCd);
		SearchClGpCD		= B00150ArrayListControl.ArryListStringUniqueList(SearchClGpCD);
		SearchDECD			= B00150ArrayListControl.ArryListStringUniqueList(SearchDECD);
		SearchDepartmentCd	= B00150ArrayListControl.ArryListStringUniqueList(SearchDepartmentCd);
		SearchCautionTiming	= B00150ArrayListControl.ArryListStringUniqueList(SearchCautionTiming);
		SearchCautionName	= B00150ArrayListControl.ArryListStringUniqueList(SearchCautionName);
		SearchCaution		= B00150ArrayListControl.ArryListStringUniqueList(SearchCaution);
		SearchDeName		= B00150ArrayListControl.ArryListStringUniqueList(SearchDeName);
		
		
		Object[][] rt = new Object[0][13];
		boolean SearchKick = false;
		if(AllSearch) {SearchKick = true;}
		
		String sql = " select \n"
				+"(KM0090_CAUTION.CautionCd) as CautionCd,\n"			//注意事項コード
				+"max(KM0090_CAUTION.ClGpCD) as ClGpCD,\n"				//荷主グループコード
				+"max(KM0031_CLIENT_GROUP.CLGpName01) as CLGpName01,\n"	//荷主グループ名
				+"(KM0090_CAUTION.DECD) as DECD,\n"						//届先コード
				+"(KM0090_CAUTION.DepartmentCd) as DepartmentCd,\n"		//部署CD
				+"max(KM0040_DELIVERYMST.DEName01) as DEName01,\n"		//届先名
				+"(KM0090_CAUTION.CautionTiming) as CautionTiming,\n"	//注意事項タイミング
				+"max(KM0090_CAUTION.CautionName) as CautionName,\n"	//注意事項名
				+"max(KM0090_CAUTION.Caution) as Caution,\n"			//注意事項内容
				+"max(KM0090_CAUTION.EntryDate) as EntryDate,\n"		//データ登録日時
				+"max(KM0090_CAUTION.UpdateDate) as UpdateDate,\n"		//データ更新日時
				+"max(KM0090_CAUTION.EntryUser) as EntryUser,\n"		//登録者コード
				+"max(KM0090_CAUTION.UpdateUser) as UpdateUser,\n"		//更新者コード
				+"max(KM0040_DELIVERYMST.Add01) as Add01,\n"			//届先住所1
				+"max(KM0040_DELIVERYMST.Add02) as Add02,\n"			//届先住所2
				+"max(KM0040_DELIVERYMST.Add03) as Add03\n"				//届先住所3
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
		sql = sql + " group by KM0090_CAUTION.CautionCd,KM0090_CAUTION.CautionTiming,KM0090_CAUTION.DECD,KM0090_CAUTION.DepartmentCd\n";
		sql = sql + " order by KM0090_CAUTION.CautionCd,KM0090_CAUTION.CautionTiming,KM0090_CAUTION.DECD,KM0090_CAUTION.DepartmentCd\n";
		
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
	
				rt = new Object[counter][16];
				counter = 0;
				rset01.beforeFirst();
				while (rset01.next()) {
					if(null==rset01.getString("CautionCd")){	rt[counter][ColCautionCd]		="";}else{rt[counter][ColCautionCd]		=rset01.getString("CautionCd");}					//注意事項コード
					if(null==rset01.getString("ClGpCD")){		rt[counter][ColClGpCD]			="";}else{rt[counter][ColClGpCD]			=rset01.getString("ClGpCD");}						//荷主グループコード
					if(null==rset01.getString("CLGpName01")){	rt[counter][ColCLGpName01]	="";}else{rt[counter][ColCLGpName01]		=rset01.getString("CLGpName01");}					//荷主グループ名
					if(null==rset01.getString("DECD")){			rt[counter][ColDECD]			="";}else{rt[counter][ColDECD]				=rset01.getString("DECD");}							//届先コード
					if(null==rset01.getString("DepartmentCd")){	rt[counter][ColDepartmentCd]	="";}else{rt[counter][ColDepartmentCd]	=rset01.getString("DepartmentCd");}					//部署CD
					if(null==rset01.getString("DEName01")){		rt[counter][ColDEName01]		="";}else{rt[counter][ColDEName01]			=rset01.getString("DEName01");}						//届先名
					rt[counter][ColCautionTiming]=rset01.getInt("CautionTiming");																												//注意事項タイミング
					if(null==rset01.getString("CautionName")){	rt[counter][ColCautionName]	="";}else{rt[counter][ColCautionName]		=rset01.getString("CautionName");}					//注意事項名
					if(null==rset01.getString("Caution")){		rt[counter][ColCaution]		="";}else{rt[counter][ColCaution]			=rset01.getString("Caution");}						//注意事項内容
					if(null==rset01.getTimestamp("EntryDate")){	rt[counter][ColEntryDate]		="";}else{rt[counter][ColEntryDate]		=B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("EntryDate"))[1];}		//データ登録日時
					if(null==rset01.getTimestamp("UpdateDate")){rt[counter][ColUpdateDate]	="";}else{rt[counter][ColUpdateDate]		=B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("UpdateDate"))[1];}		//データ更新日時
					if(null==rset01.getString("EntryUser")){	rt[counter][ColEntryUser]		="";}else{rt[counter][ColEntryUser]		=rset01.getString("EntryUser");}					//登録者コード
					if(null==rset01.getString("UpdateUser")){	rt[counter][ColUpdateUser]	="";}else{rt[counter][ColUpdateUser]		=rset01.getString("UpdateUser");}					//更新者コード
					if(null==rset01.getString("Add01")){		rt[counter][ColAdd01]			="";}else{rt[counter][ColAdd01]			=rset01.getString("Add01");}						//届先住所1
					if(null==rset01.getString("Add02")){		rt[counter][ColAdd02]			="";}else{rt[counter][ColAdd02]			=rset01.getString("Add02");}						//届先住所2
					if(null==rset01.getString("Add03")){		rt[counter][ColAdd03]			="";}else{rt[counter][ColAdd03]			=rset01.getString("Add03");}						//届先住所3
					
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
	
	public static String[] NewCautionCdGet(int NeedCount) {
		ArrayList<String> SearchCautionCd = new ArrayList<String>();
		ArrayList<String> SearchClGpCD = new ArrayList<String>();
		ArrayList<String> SearchDECD = new ArrayList<String>();
		ArrayList<String> SearchDepartmentCd = new ArrayList<String>();
		ArrayList<String> SearchCautionTiming = new ArrayList<String>();
		ArrayList<String> SearchCautionName = new ArrayList<String>();
		ArrayList<String> SearchCaution = new ArrayList<String>();
		ArrayList<String> SearchDeName = new ArrayList<String>();
		boolean AllSearch = true;
		
		Object[][] CautionMstRt = M00042CautionMstRt.CautionMstRt(
				SearchCautionCd,
				SearchClGpCD,
				SearchDECD,
				SearchDepartmentCd,
				SearchCautionTiming,
				SearchCautionName,
				SearchCaution,
				SearchDeName,
				AllSearch);
    	
    	int CautionNo = 0;
    	
    	for(int i=0;i<CautionMstRt.length;i++) {
    		if(4<(""+CautionMstRt[i][M00042CautionMstRt.ColCautionCd]).length()&&"ATCT".equals((""+CautionMstRt[i][M00042CautionMstRt.ColCautionCd]).substring(0,4))) {
    			String WST = B00020ToolsTextControl.num_only_String(""+CautionMstRt[i][M00042CautionMstRt.ColCautionCd]);
    			if(9==WST.length()) {
    				int wint = Integer.parseInt(WST);
    				if(CautionNo<wint) {
    					CautionNo=wint;
    				}
    			}
    		}
    	}

    	String[] rt = new String[NeedCount];
    	int MaxCount = 999999999;
    	int wint = MaxCount+1;
    	String SetZero = (""+wint).substring(1,(""+wint).length());
    	for(int i=0;i<NeedCount;i++) {
    		CautionNo = CautionNo+1;
    		if(MaxCount<CautionNo) {
    			rt[i] = "ATCT"+CautionNo;
    		}else {
		    	rt[i] = SetZero+CautionNo;
		    	rt[i] = "ATCT"+rt[i].substring(rt[i].length()-SetZero.length(),rt[i].length());
    		}
    	}
    	
    	return rt;
	}
}