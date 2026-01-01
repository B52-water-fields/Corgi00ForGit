import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class M00010ClGpMstRt{
	/*
	コピペ用
	ArrayList<String> SearchClGpCD 		= new ArrayList<String>();
	ArrayList<String> SearchCLGpName 	= new ArrayList<String>();
	ArrayList<String> SearchPost 		= new ArrayList<String>();
	ArrayList<String> SearchAdd 		= new ArrayList<String>();
	ArrayList<String> SearchTel 		= new ArrayList<String>();
	ArrayList<String> SearchFax 		= new ArrayList<String>();
	ArrayList<String> SearchMail 		= new ArrayList<String>();
	ArrayList<String> SearchCom 		= new ArrayList<String>();
	boolean AllSearch = false;
	
	Object[][] ClGpMstRt = M00010ClGpMstRt.ClGpMstRt(
			SearchClGpCD,
			SearchCLGpName,
			SearchPost,
			SearchAdd,
			SearchTel,
			SearchFax,
			SearchMail,
			SearchCom,
			AllSearch);
	*/
	//戻り値カラム
	public static Object[][] RtSettingClGpMstRt(){
		Object[][] RtSettingClGpMstRt = {
				 {"ClGpCD"		,(int) 0	,"String"	,"荷主グループCD"}
				,{"CLGpName01"	,(int) 1	,"String"	,"荷主グループ名1"}
				,{"CLGpName02"	,(int) 2	,"String"	,"/荷主グループ名2"}
				,{"CLGpName03"	,(int) 3	,"String"	,"荷主グループ名3"}
				,{"Post"		,(int) 4	,"String"	,"郵便番号"}
				,{"Add01"		,(int) 5	,"String"	,"住所1"}
				,{"Add02"		,(int) 6	,"String"	,"住所2"}
				,{"Add03"		,(int) 7	,"String"	,"住所3"}
				,{"Tel"			,(int) 8	,"String"	,"電話番号"}
				,{"Fax"			,(int) 9	,"String"	,"FAX"}
				,{"Mail"		,(int)10	,"String"	,"メールアドレス"}
				,{"Com01"		,(int)11	,"String"	,"コメント1"}
				,{"Com02"		,(int)12	,"String"	,"コメント2"}
				,{"Com03"		,(int)13	,"String"	,"コメント3"}
				,{"EntryDate"	,(int)14	,"String"	,"データ登録日時"}
				,{"UpdateDate"	,(int)15	,"String"	,"データ更新日時"}
				,{"EntryUser"	,(int)16	,"String"	,"登録者コード"}
				,{"UpdateUser"	,(int)17	,"String"	,"更新者コード"}
				,{"PassWord"	,(int)18	,"String"	,"パスワード"}
				};
		
		return RtSettingClGpMstRt;
	}
	
	public static Object[][] ClGpMstRt(
			ArrayList<String> SearchClGpCD,
			ArrayList<String> SearchCLGpName,
			ArrayList<String> SearchPost,
			ArrayList<String> SearchAdd,
			ArrayList<String> SearchTel,
			ArrayList<String> SearchFax,
			ArrayList<String> SearchMail,
			ArrayList<String> SearchCom,
			boolean AllSearch){
		boolean SearchKick = false;
		if(AllSearch) {SearchKick = true;}
		Object[][] rt = new Object[0][19];
		
		String sql = "select "
				+"("+A00000Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.ClGpCD) as ClGpCD,\n"			//荷主グループCD
				+"("+A00000Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.CLGpName01) as CLGpName01,\n"	//荷主名1
				+"("+A00000Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.CLGpName02) as CLGpName02,\n"	//荷主名2
				+"("+A00000Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.CLGpName03) as CLGpName03,\n"	//荷主名3
				+"("+A00000Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.Post) as Post,\n"				//郵便番号
				+"("+A00000Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.Add01) as Add01,\n"				//住所1
				+"("+A00000Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.Add02) as Add02,\n"				//住所2
				+"("+A00000Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.Add03) as Add03,\n"				//住所3
				+"("+A00000Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.Tel) as Tel,\n"					//電話番号
				+"("+A00000Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.Fax) as Fax,\n"					//FAX
				+"("+A00000Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.Mail) as Mail,\n"				//メールアドレス
				+"("+A00000Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.Com01) as Com01,\n"				//コメント1
				+"("+A00000Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.Com02) as Com02,\n"				//コメント2
				+"("+A00000Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.Com03) as Com03,\n"				//コメント3
				+"("+A00000Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.EntryDate) as EntryDate,\n"		//データ登録日時
				+"("+A00000Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.UpdateDate) as UpdateDate,\n"	//データ更新日時
				+"("+A00000Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.EntryUser) as EntryUser,\n"		//登録者コード
				+"("+A00000Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.UpdateUser) as UpdateUser,\n"	//更新者コード
				+"("+A00000Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.PassWord) as PassWord \n"		//パスワード
				+" from "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP "
				+" where 1=1 ";

		if(null!=SearchClGpCD && 0<SearchClGpCD.size()) {
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchClGpCD.size();i++) {
				if(0<i) {sql = sql+" or ";}
				sql = sql + " "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.ClGpCD = ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchCLGpName && 0<SearchCLGpName.size()) {
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchCLGpName.size();i++) {
				if(0<i) {sql = sql+" or ";}
				sql = sql + " "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.CLGpName01 like ?";
				sql = sql + " or "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.CLGpName02 like ?";
				sql = sql + " or "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.CLGpName03 like ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchPost && 0<SearchPost.size()) {
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchPost.size();i++) {
				if(0<i) {sql = sql+" or ";}
				sql = sql + " "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.Post like ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchAdd && 0<SearchAdd.size()) {
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchAdd.size();i++) {
				if(0<i) {sql = sql+" or ";}
				sql = sql + " CONCAT ("+A00000Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.Add01";
				sql = sql + " , "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.Add02";
				sql = sql + " , "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.Add03) like ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchTel && 0<SearchTel.size()) {
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchTel.size();i++) {
				if(0<i) {sql = sql+" or ";}
				sql = sql + " "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.Tel like ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchFax && 0<SearchFax.size()) {
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchFax.size();i++) {
				if(0<i) {sql = sql+" or ";}
				sql = sql + " "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.Fax like ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchMail && 0<SearchMail.size()) {
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchMail.size();i++) {
				if(0<i) {sql = sql+" or ";}
				sql = sql + " "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.Mail like ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchCom && 0<SearchCom.size()) {
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchCom.size();i++) {
				if(0<i) {sql = sql+" or ";}
				sql = sql + " "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.Com01 like ?";
				sql = sql + " or "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.Com02 like ?";
				sql = sql + " or "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.Com03 like ?";
			}
			sql = sql + ")";
		}
		sql = sql + "order by "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.ClGpCD";

		//System.out.println(sql);
		if(SearchKick) {
			A00010DbConnect.DB_CONN("NYANKO");
			
			ResultSet rset01 = null;
			PreparedStatement stmt01 = null;
			try {
				stmt01 = A00010DbConnect.conn.prepareStatement(sql);
				int StmtCount = 0;
				if(null!=SearchClGpCD && 0<SearchClGpCD.size()) {
					for(int i=0;i<SearchClGpCD.size();i++) {
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchClGpCD.get(i)+"");
					}
				}
				if(null!=SearchCLGpName && 0<SearchCLGpName.size()) {
					for(int i=0;i<SearchCLGpName.size();i++) {
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchCLGpName.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchCLGpName.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchCLGpName.get(i)+"%");
					}
				}
				if(null!=SearchPost && 0<SearchPost.size()) {
					for(int i=0;i<SearchPost.size();i++) {
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchPost.get(i)+"%");
					}
				}
				if(null!=SearchAdd && 0<SearchAdd.size()) {
					for(int i=0;i<SearchAdd.size();i++) {
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchAdd.get(i)+"%");
					}
				}
				if(null!=SearchTel && 0<SearchTel.size()) {
					for(int i=0;i<SearchTel.size();i++) {
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchTel.get(i)+"%");
					}
				}
				if(null!=SearchFax && 0<SearchFax.size()) {
					for(int i=0;i<SearchFax.size();i++) {
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchFax.get(i)+"%");
					}
				}
				if(null!=SearchMail && 0<SearchMail.size()) {
					for(int i=0;i<SearchMail.size();i++) {
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchMail.get(i)+"%");
					}
				}
				if(null!=SearchCom && 0<SearchCom.size()) {
					for(int i=0;i<SearchCom.size();i++) {
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchCom.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchCom.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchCom.get(i)+"%");
					}
				}
				rset01 = stmt01.executeQuery();
				
				int counter = 0;
				rset01.beforeFirst();
				while (rset01.next()) {
					counter=counter+1;
				}

				rt = new Object[counter][19];
				counter = 0;
				rset01.beforeFirst();
				while (rset01.next()) {
					if(null==rset01.getString("ClGpCD")){		rt[counter][ 0] = "";}else{rt[counter][ 0] = rset01.getString("ClGpCD");}		//荷主グループCD
					if(null==rset01.getString("CLGpName01")){	rt[counter][ 1] = "";}else{rt[counter][ 1] = rset01.getString("CLGpName01");}	//荷主グループ名1
					if(null==rset01.getString("CLGpName02")){	rt[counter][ 2] = "";}else{rt[counter][ 2] = rset01.getString("CLGpName02");}	//荷主グループ名2
					if(null==rset01.getString("CLGpName03")){	rt[counter][ 3] = "";}else{rt[counter][ 3] = rset01.getString("CLGpName03");}	//荷主グループ名3
					if(null==rset01.getString("Post")){			rt[counter][ 4] = "";}else{rt[counter][ 4] = rset01.getString("Post");}			//郵便番号
					if(null==rset01.getString("Add01")){		rt[counter][ 5] = "";}else{rt[counter][ 5] = rset01.getString("Add01");}		//住所1
					if(null==rset01.getString("Add02")){		rt[counter][ 6] = "";}else{rt[counter][ 6] = rset01.getString("Add02");}		//住所2
					if(null==rset01.getString("Add03")){		rt[counter][ 7] = "";}else{rt[counter][ 7] = rset01.getString("Add03");}		//住所3
					if(null==rset01.getString("Tel")){			rt[counter][ 8] = "";}else{rt[counter][ 8] = rset01.getString("Tel");}			//電話番号
					if(null==rset01.getString("Fax")){			rt[counter][ 9] = "";}else{rt[counter][ 9] = rset01.getString("Fax");}			//FAX
					if(null==rset01.getString("Mail")){			rt[counter][10] = "";}else{rt[counter][10] = rset01.getString("Mail");}			//メールアドレス
					if(null==rset01.getString("Com01")){		rt[counter][11] = "";}else{rt[counter][11] = rset01.getString("Com01");}		//コメント1
					if(null==rset01.getString("Com02")){		rt[counter][12] = "";}else{rt[counter][12] = rset01.getString("Com02");}		//コメント2
					if(null==rset01.getString("Com03")){		rt[counter][13] = "";}else{rt[counter][13] = rset01.getString("Com03");}		//コメント3
					if(null==rset01.getTimestamp("EntryDate")){	rt[counter][14] = "";}else{rt[counter][14] = B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("EntryDate"))[1];}		//データ登録日時
					if(null==rset01.getTimestamp("UpdateDate")){rt[counter][15] = "";}else{rt[counter][15] = B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("UpdateDate"))[1];}		//データ更新日時
					if(null==rset01.getString("EntryUser")){	rt[counter][16] = "";}else{rt[counter][16] = rset01.getString("EntryUser");}	//登録者コード
					if(null==rset01.getString("UpdateUser")){	rt[counter][17] = "";}else{rt[counter][17] = rset01.getString("UpdateUser");}	//更新者コード
					if(null==rset01.getString("PassWord")){		rt[counter][18] = "";}else{rt[counter][18] = rset01.getString("PassWord");}		//パスワード
					
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
	
	//荷主グループコードを自動採番する
	public static String[] NewWhCdGet(int NeedCount) {
		//荷主グループマスタ取得
		ArrayList<String> SearchClGpCD = new ArrayList<String>();
		ArrayList<String> SearchCLGpName = new ArrayList<String>();
		ArrayList<String> SearchPost = new ArrayList<String>();
		ArrayList<String> SearchAdd = new ArrayList<String>();
		ArrayList<String> SearchTel = new ArrayList<String>();
		ArrayList<String> SearchFax = new ArrayList<String>();
		ArrayList<String> SearchMail = new ArrayList<String>();
		ArrayList<String> SearchCom = new ArrayList<String>();
		boolean AllSearch = true;
		
		Object[][] ClGpMstRt = M00010ClGpMstRt.ClGpMstRt(
						SearchClGpCD,SearchCLGpName,SearchPost,
						SearchAdd,SearchTel,SearchFax,SearchMail,SearchCom,AllSearch);
		
		int ClGpNo = 0;
    	
    	for(int i=0;i<ClGpMstRt.length;i++) {
    		if("ATGR".equals((""+ClGpMstRt[i][0]).substring(0,4))&&11==(""+ClGpMstRt[i][0]).length()) {
    			String WST = B00020ToolsTextControl.num_only_String(""+ClGpMstRt[i][0]);
    			if(7==WST.length()) {
    				int wint = Integer.parseInt(WST);
    				if(ClGpNo<wint) {
    					ClGpNo=wint;
    				}
    			}
    		}
    	}
    	
    	String[] rt = new String[NeedCount];
    	for(int i=0;i<NeedCount;i++) {
    		ClGpNo = ClGpNo+1;
	    	rt[i] = "0000000"+ClGpNo;
	    	rt[i] = "ATGR"+rt[i].substring(rt[i].length()-7,rt[i].length());
    	}
    	
    	return rt;
	}
}