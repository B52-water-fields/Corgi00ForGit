import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class M00040DeliveryMstRt{
	//戻り値カラム
	public static Object[][] RtSettingDeliveryMstRt(){
		/*
		コピペ用
		ArrayList<String> SearchDECD = new ArrayList<String>();
		ArrayList<String> SearchDepartmentCd = new ArrayList<String>();
		ArrayList<String> SearchDEName = new ArrayList<String>();
		ArrayList<String> SearchPost = new ArrayList<String>();
		ArrayList<String> SearchAdd = new ArrayList<String>();
		ArrayList<String> SearchTel = new ArrayList<String>();
		ArrayList<String> SearchFax = new ArrayList<String>();
		ArrayList<String> SearchMail = new ArrayList<String>();
		ArrayList<String> SearchCom = new ArrayList<String>();
		ArrayList<String> SearchPrefecturesCd = new ArrayList<String>();
		ArrayList<String> SearchMunicipalityCd = new ArrayList<String>();
		ArrayList<String> SearchDelFg = new ArrayList<String>();
		boolean AllSearch = false;
		
		M00040DeliveryMstRt.DeliveryMstRt(
			SearchDECD,
			SearchDepartmentCd,
			SearchDEName,
			SearchPost,
			SearchAdd,
			SearchTel,
			SearchFax,
			SearchMail,
			SearchCom,
			SearchPrefecturesCd,
			SearchMunicipalityCd,
			SearchDelFg,
			AllSearch
			);
		*/
		Object[][] RtSettingDeliveryMstRt = {
				 {"DECD"			,(int) 0	,"String"	,"納品先コード"}
				,{"DepartmentCd"	,(int) 1	,"String"	,"部署CD"}
				,{"DEName01"		,(int) 2	,"String"	,"納品先名1"}
				,{"DEName02"		,(int) 3	,"String"	,"納品先名2"}
				,{"DEName03"		,(int) 4	,"String"	,"納品先名3"}
				,{"Post"			,(int) 5	,"String"	,"納品先郵便"}
				,{"Add01"			,(int) 6	,"String"	,"納品先住所1"}
				,{"Add02"			,(int) 7	,"String"	,"納品先住所2"}
				,{"Add03"			,(int) 8	,"String"	,"納品先住所3"}
				,{"Tel"				,(int) 9	,"String"	,"納品先電話"}
				,{"Fax"				,(int)10	,"String"	,"納品先FAX"}
				,{"Mail"			,(int)11	,"String"	,"納品先MAIL"}
				,{"Com01"			,(int)12	,"String"	,"コメント1"}
				,{"Com02"			,(int)13	,"String"	,"コメント2"}
				,{"Com03"			,(int)14	,"String"	,"コメント3"}
				,{"PrefecturesCd"	,(int)15	,"String"	,"JIS県CD2桁"}
				,{"MunicipalityCd"	,(int)16	,"String"	,"JIS市区町村CD5桁"}
				,{"PTMSCD"			,(int)17	,"String"	,"基幹システム発着地コード"}
				,{"EntryDate"		,(int)18	,"String"	,"データ登録日時"}
				,{"UpdateDate"		,(int)19	,"String"	,"データ更新日時"}
				,{"EntryUser"		,(int)20	,"String"	,"登録者コード"}
				,{"UpdateUser"		,(int)21	,"String"	,"更新者コード"}
				,{"FirstClient"		,(int)22	,"String"	,"登録した荷主CD"}
				,{"LastClient"		,(int)23	,"String"	,"更新した荷主CD"}
				,{"DelFg"			,(int)24	,"int"		,"削除区分"}
				,{"FirstClientName"	,(int)25	,"String"	,"登録した荷主名"}
				,{"LastClientName"	,(int)26	,"String"	,"登録した荷主名"}
				};
		
		return RtSettingDeliveryMstRt;
	}
	
	public static Object[][] DeliveryMstRt(
			ArrayList<String> SearchDECD,			//検索条件届先CD
			ArrayList<String> SearchDepartmentCd,	//検索条件届先部署CD
			ArrayList<String> SearchDEName,			//検索条件届先名
			ArrayList<String> SearchPost,			//検索条件届先郵便
			ArrayList<String> SearchAdd,			//検索条件届先住所
			ArrayList<String> SearchTel,			//検索条件届先TEL
			ArrayList<String> SearchFax,			//検索条件届先FAX
			ArrayList<String> SearchMail,			//検索条件届先MAIL
			ArrayList<String> SearchCom,			//検索条件届先コメント
			ArrayList<String> SearchPrefecturesCd,	//検索条件届先県CD
			ArrayList<String> SearchMunicipalityCd,	//検索条件届先市区町村CD
			ArrayList<String> SearchDelFg,			//検索条件削除区分
			boolean AllSearch
			){
		Object[][] rt = new Object[0][27];
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
			+"(KM0040_DELIVERYMST.DelFg) as DelFg,\n"					//削除区分
			+"(FCL.CLName01) as FirstClientName,\n"						//登録した荷主名
			+"(LCL.CLName01) as LastClientName\n"						//登録した荷主名
			
			+" from "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0040_DELIVERYMST"
			+" left outer join " +A00000Main.MySqlDefaultSchemaNYANKO + ".KM0030_CLIENTMST as FCL"
			+" on("
			+A00000Main.MySqlDefaultSchemaNYANKO+".KM0040_DELIVERYMST.FirstClient = FCL.cl_cd"
			+")\n"
			+" left outer join " +A00000Main.MySqlDefaultSchemaNYANKO + ".KM0030_CLIENTMST as LCL"
			+" on("
			+A00000Main.MySqlDefaultSchemaNYANKO+".KM0040_DELIVERYMST.FirstClient = LCL.cl_cd"
			+")\n"
			+" where 1=1\n";
		
		if(null!=SearchDECD && 0<SearchDECD.size()){					//検索条件届先CD
			SearchKick=true;
			sql = sql + " and(";
			for(int i=0;i<SearchDECD.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + "KM0040_DELIVERYMST.DECD = ?";
			}
			sql= sql + ")\n";
		}
		if(null!=SearchDepartmentCd && 0<SearchDepartmentCd.size()){	//検索条件届先部署CD
			SearchKick=true;
			sql = sql + " and(";
			for(int i=0;i<SearchDepartmentCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + "KM0040_DELIVERYMST.DepartmentCd = ?";
			}
			sql= sql + ")\n";
		}
		if(null!=SearchDEName && 0<SearchDEName.size()){				//検索条件届先名
			SearchKick=true;
			sql = sql + " and(";
			for(int i=0;i<SearchDEName.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + "KM0040_DELIVERYMST.DEName01 like ?";
				sql = sql + " or KM0040_DELIVERYMST.DEName02 like ?";
				sql = sql + " or KM0040_DELIVERYMST.DEName03 like ?";
			}
			sql= sql + ")\n";
		}
		if(null!=SearchPost && 0<SearchPost.size()){					//検索条件届先郵便
			SearchKick=true;
			sql = sql + " and(";
			for(int i=0;i<SearchPost.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + "KM0040_DELIVERYMST.Post like ?";
			}
			sql= sql + ")\n";
		}
		if(null!=SearchAdd && 0<SearchAdd.size()){						//検索条件届先住所
			SearchKick=true;
			sql = sql + " and(";
			for(int i=0;i<SearchAdd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " CONCAT (KM0040_DELIVERYMST.Add01";
				sql = sql + " , KM0040_DELIVERYMST.Add02";
				sql = sql + " , KM0040_DELIVERYMST.Add03) like ?";
			}
			sql= sql + ")\n";
		}
		if(null!=SearchTel && 0<SearchTel.size()){						//検索条件届先TEL
			SearchKick=true;
			sql = sql + " and(";
			for(int i=0;i<SearchTel.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + "KM0040_DELIVERYMST.Tel like ?";
			}
			sql= sql + ")\n";
		}
		if(null!=SearchFax && 0<SearchFax.size()){						//検索条件届先FAX
			SearchKick=true;
			sql = sql + " and(";
			for(int i=0;i<SearchFax.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + "KM0040_DELIVERYMST.Fax like ?";
			}
			sql= sql + ")\n";
		}
		if(null!=SearchMail && 0<SearchMail.size()){					//検索条件届先MAIL
			SearchKick=true;
			sql = sql + " and(";
			for(int i=0;i<SearchMail.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + "KM0040_DELIVERYMST.Mail like ?";
			}
			sql= sql + ")\n";
		}
		if(null!=SearchCom && 0<SearchCom.size()){						//検索条件届先コメント
			SearchKick=true;
			sql = sql + " and(";
			for(int i=0;i<SearchCom.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + "KM0040_DELIVERYMST.Com01 like ?";
				sql = sql + " or KM0040_DELIVERYMST.Com02 like ?";
				sql = sql + " or KM0040_DELIVERYMST.Com03 like ?";
			}
			sql= sql + ")\n";
		}
		if(null!=SearchPrefecturesCd && 0<SearchPrefecturesCd.size()){	//検索条件届先県CD
			SearchKick=true;
			sql = sql + " and(";
			for(int i=0;i<SearchPrefecturesCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + "KM0040_DELIVERYMST.PrefecturesCd = ?";
			}
			sql= sql + ")";
		}
		if(null!=SearchMunicipalityCd && 0<SearchMunicipalityCd.size()){//検索条件届先市区町村CD
			SearchKick=true;
			sql = sql + " and(";
			for(int i=0;i<SearchMunicipalityCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + "KM0040_DELIVERYMST.MunicipalityCd = ?";
			}
			sql= sql + ")\n";
		}
		if(null!=SearchDelFg && 0<SearchDelFg.size()){					//検索条件届先市区町村CD
			SearchKick=true;
			sql = sql + " and(";
			for(int i=0;i<SearchDelFg.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + "KM0040_DELIVERYMST.DelFg = ?";
			}
			sql= sql + ")\n";
		}
		
		sql = sql + " order by KM0040_DELIVERYMST.DECD,KM0040_DELIVERYMST.DepartmentCd";
		
		//System.out.println(sql);
		if(SearchKick) {
			A00010DbConnect.DB_CONN("NYANKO");
			ResultSet rset01 = null;
			PreparedStatement stmt01 = null;
			try {
				stmt01 = A00010DbConnect.conn.prepareStatement(sql);
				int StmtCount = 0;
				
				if(null!=SearchDECD && 0<SearchDECD.size()){
					for(int i=0;i<SearchDECD.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchDECD.get(i)+"");
					}
				}
				if(null!=SearchDepartmentCd && 0<SearchDepartmentCd.size()){
					for(int i=0;i<SearchDepartmentCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchDepartmentCd.get(i)+"");
					}
				}
				if(null!=SearchDEName && 0<SearchDEName.size()){
					for(int i=0;i<SearchDEName.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchDEName.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchDEName.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchDEName.get(i)+"%");
					}
				}
				if(null!=SearchPost && 0<SearchPost.size()){
					for(int i=0;i<SearchPost.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchPost.get(i)+"%");
					}
				}
				if(null!=SearchAdd && 0<SearchAdd.size()){
					for(int i=0;i<SearchAdd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchAdd.get(i)+"%");
					}
				}
				if(null!=SearchTel && 0<SearchTel.size()){
					for(int i=0;i<SearchTel.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchTel.get(i)+"%");
					}
				}
				if(null!=SearchFax && 0<SearchFax.size()){
					for(int i=0;i<SearchFax.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchFax.get(i)+"%");
					}
				}
				if(null!=SearchMail && 0<SearchMail.size()){
					for(int i=0;i<SearchMail.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchMail.get(i)+"%");
					}
				}
				if(null!=SearchCom && 0<SearchCom.size()){
					for(int i=0;i<SearchCom.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchCom.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchCom.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchCom.get(i)+"%");
					}
				}
				if(null!=SearchPrefecturesCd && 0<SearchPrefecturesCd.size()){
					for(int i=0;i<SearchPrefecturesCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchPrefecturesCd.get(i)+"");
					}
				}
				if(null!=SearchMunicipalityCd && 0<SearchMunicipalityCd.size()){
					for(int i=0;i<SearchMunicipalityCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchMunicipalityCd.get(i)+"");
					}
				}
				if(null!=SearchDelFg && 0<SearchDelFg.size()){
					for(int i=0;i<SearchDelFg.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchDelFg.get(i)+"");
					}
				}
				rset01 = stmt01.executeQuery();
				
				int counter = 0;
				rset01.beforeFirst();
				while (rset01.next()) {
					counter=counter+1;
				}

				rt = new Object[counter][27];
				counter = 0;
				rset01.beforeFirst();
				while (rset01.next()) {
					if(null==rset01.getString("DECD")){				rt[counter][ 0] = "";}else{rt[counter][ 0] = rset01.getString("DECD");}				//納品先コード
					if(null==rset01.getString("DepartmentCd")){		rt[counter][ 1] = "";}else{rt[counter][ 1] = rset01.getString("DepartmentCd");}		//部署CD
					if(null==rset01.getString("DEName01")){			rt[counter][ 2] = "";}else{rt[counter][ 2] = rset01.getString("DEName01");}			//納品先名1
					if(null==rset01.getString("DEName02")){			rt[counter][ 3] = "";}else{rt[counter][ 3] = rset01.getString("DEName02");}			//納品先名2
					if(null==rset01.getString("DEName03")){			rt[counter][ 4] = "";}else{rt[counter][ 4] = rset01.getString("DEName03");}			//納品先名3
					if(null==rset01.getString("Post")){				rt[counter][ 5] = "";}else{rt[counter][ 5] = rset01.getString("Post");	}			//納品先郵便
					if(null==rset01.getString("Add01")){			rt[counter][ 6] = "";}else{rt[counter][ 6] = rset01.getString("Add01");}			//納品先住所1
					if(null==rset01.getString("Add02")){			rt[counter][ 7] = "";}else{rt[counter][ 7] = rset01.getString("Add02");}			//納品先住所2
					if(null==rset01.getString("Add03")){			rt[counter][ 8] = "";}else{rt[counter][ 8] = rset01.getString("Add03");}			//納品先住所3
					if(null==rset01.getString("Tel")){				rt[counter][ 9] = "";}else{rt[counter][ 9] = rset01.getString("Tel");}				//納品先電話
					if(null==rset01.getString("Fax")){				rt[counter][10] = "";}else{rt[counter][10] = rset01.getString("Fax");}				//納品先FAX
					if(null==rset01.getString("Mail")){				rt[counter][11] = "";}else{rt[counter][11] = rset01.getString("Mail");}				//納品先MAIL
					if(null==rset01.getString("Com01")){			rt[counter][12] = "";}else{rt[counter][12] = rset01.getString("Com01");}			//コメント1
					if(null==rset01.getString("Com02")){			rt[counter][13] = "";}else{rt[counter][13] = rset01.getString("Com02");}			//コメント2
					if(null==rset01.getString("Com03")){			rt[counter][14] = "";}else{rt[counter][14] = rset01.getString("Com03");}			//コメント3
					if(null==rset01.getString("PrefecturesCd")){	rt[counter][15] = "";}else{rt[counter][15] = rset01.getString("PrefecturesCd");}	//JIS県CD2桁
					if(null==rset01.getString("MunicipalityCd")){	rt[counter][16] = "";}else{rt[counter][16] = rset01.getString("MunicipalityCd");}	//JIS市区町村CD5桁
					if(null==rset01.getString("PTMSCD")){			rt[counter][17] = "";}else{rt[counter][17] = rset01.getString("PTMSCD");}			//基幹システム発着地コード
					if(null==rset01.getTimestamp("EntryDate")){		rt[counter][18] = "";}else{rt[counter][18] = B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("EntryDate"))[1];}		//データ登録日時
					if(null==rset01.getTimestamp("UpdateDate")){	rt[counter][19] = "";}else{rt[counter][19] = B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("UpdateDate"))[1];}		//データ更新日時
					if(null==rset01.getString("EntryUser")){		rt[counter][20] = "";}else{rt[counter][20] = rset01.getString("EntryUser");}		//登録者コード
					if(null==rset01.getString("UpdateUser")){		rt[counter][21] = "";}else{rt[counter][21] = rset01.getString("UpdateUser");}		//更新者コード
					if(null==rset01.getString("FirstClient")){		rt[counter][22] = "";}else{rt[counter][22] = rset01.getString("FirstClient");}		//登録した荷主CD
					if(null==rset01.getString("LastClient")){		rt[counter][23] = "";}else{rt[counter][23] = rset01.getString("LastClient");}		//更新した荷主CD
					rt[counter][24] = rset01.getInt("DelFg");				//削除区分
					if(null==rset01.getString("FirstClientName")){	rt[counter][25] = "";}else{rt[counter][25] = rset01.getString("FirstClientName");}	//登録した荷主名
					if(null==rset01.getString("LastClientName")){	rt[counter][26] = "";}else{rt[counter][26] = rset01.getString("LastClientName");}	//登録した荷主名
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
	
	public static String[] DeliveryCdGet(int NeedCount) {
		ArrayList<String> SearchDECD = new ArrayList<String>();
		ArrayList<String> SearchDepartmentCd = new ArrayList<String>();
		ArrayList<String> SearchDEName = new ArrayList<String>();
		ArrayList<String> SearchPost = new ArrayList<String>();
		ArrayList<String> SearchAdd = new ArrayList<String>();
		ArrayList<String> SearchTel = new ArrayList<String>();
		ArrayList<String> SearchFax = new ArrayList<String>();
		ArrayList<String> SearchMail = new ArrayList<String>();
		ArrayList<String> SearchCom = new ArrayList<String>();
		ArrayList<String> SearchPrefecturesCd = new ArrayList<String>();
		ArrayList<String> SearchMunicipalityCd = new ArrayList<String>();
		ArrayList<String> SearchDelFg = new ArrayList<String>();
		boolean AllSearch = true;
    	
    	Object[][] DeliveryMstRt = DeliveryMstRt(
    			SearchDECD,				//検索条件届先CD
    			SearchDepartmentCd,		//検索条件届先部署CD
    			SearchDEName,			//検索条件届先名
    			SearchPost,				//検索条件届先郵便
    			SearchAdd,				//検索条件届先住所
    			SearchTel,				//検索条件届先TEL
    			SearchFax,				//検索条件届先FAX
    			SearchMail,				//検索条件届先MAIL
    			SearchCom,				//検索条件届先コメント
    			SearchPrefecturesCd,	//検索条件届先県CD
    			SearchMunicipalityCd,	//検索条件届先市区町村CD
    			SearchDelFg,			//検索条件削除区分
    			AllSearch
    			);
    	
    	int DENo = 0;
    	
    	for(int i=0;i<DeliveryMstRt.length;i++) {
    		if("AT".equals((""+DeliveryMstRt[i][0]).substring(0,2))&&11==(""+DeliveryMstRt[i][0]).length()) {
    			String WST = B00020ToolsTextControl.num_only_String(""+DeliveryMstRt[i][0]);
    			if(9==WST.length()) {
    				int wint = Integer.parseInt(WST);
    				if(DENo<wint) {
    					DENo=wint;
    				}
    			}
    		}
    	}

    	String[] rt = new String[NeedCount];
    	for(int i=0;i<NeedCount;i++) {
    		DENo = DENo+1;
	    	rt[i] = "000000000"+DENo;
	    	rt[i] = "AT"+rt[i].substring(rt[i].length()-9,rt[i].length());
    	}
    	
    	return rt;
	}
	
	public static String NewDepartmentCd(String GetDECD) {
		ArrayList<String> SearchDECD = new ArrayList<String>();
		ArrayList<String> SearchDepartmentCd = new ArrayList<String>();
		ArrayList<String> SearchDEName = new ArrayList<String>();
		ArrayList<String> SearchPost = new ArrayList<String>();
		ArrayList<String> SearchAdd = new ArrayList<String>();
		ArrayList<String> SearchTel = new ArrayList<String>();
		ArrayList<String> SearchFax = new ArrayList<String>();
		ArrayList<String> SearchMail = new ArrayList<String>();
		ArrayList<String> SearchCom = new ArrayList<String>();
		ArrayList<String> SearchPrefecturesCd = new ArrayList<String>();
		ArrayList<String> SearchMunicipalityCd = new ArrayList<String>();
		ArrayList<String> SearchDelFg = new ArrayList<String>();
		boolean AllSearch = false;
		
		SearchDECD.add(GetDECD);
		Object[][] DeliveryMstRt = M00040DeliveryMstRt.DeliveryMstRt(
				SearchDECD,	
				SearchDepartmentCd,
				SearchDEName,
				SearchPost,
				SearchAdd,
				SearchTel,
				SearchFax,
				SearchMail,
				SearchCom,
				SearchPrefecturesCd,
				SearchMunicipalityCd,
				SearchDelFg,
				AllSearch
				);
		int DeptNo = 0;
		for(int i=0;i<DeliveryMstRt.length;i++) {
			String WST = B00020ToolsTextControl.num_only_String(""+DeliveryMstRt[i][ 1]);
			if("".equals(WST)) {WST = "0";}
			int WINT = Integer.parseInt(WST);
			if(WINT>DeptNo) {
				DeptNo = WINT;
			}
		}
		DeptNo = DeptNo+1;
		String WST = "0000"+DeptNo;
		WST = WST.substring(WST.length()-4,WST.length());
		if(9999<DeptNo) {
			WST = ""+DeptNo;
		}
		return WST;
	}
}