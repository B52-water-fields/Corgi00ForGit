import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class M00011ClMstRt{
	//戻り値カラム
	public static Object[][] RtSettingClMstRt(){
		Object[][] RtSettingClMstRt = {
				 {"cl_cd"		,(int)  0	,"String"	,"荷主CD"}
				,{"ClGpCD"		,(int)  1	,"String"	,"荷主グループCD"}
				,{"ClGpName"	,(int)  2	,"String"	,"グループ名1"}
				,{"WHCD"		,(int)  3	,"String"	,"担当倉庫"}
				,{"WHName"		,(int)  4	,"String"	,"担当倉庫名"}
				,{"CLName01"	,(int)  5	,"String"	,"荷主名1"}
				,{"CLName02"	,(int)  6	,"String"	,"荷主名2"}
				,{"CLName03"	,(int)  7	,"String"	,"荷主名3"}
				,{"Post"		,(int)  8	,"String"	,"郵便番号"}
				,{"Add01"		,(int)  9	,"String"	,"住所1"}
				,{"Add02"		,(int) 10	,"String"	,"住所2"}
				,{"Add03"		,(int) 11	,"String"	,"住所3"}
				,{"Tel"			,(int) 12	,"String"	,"電話番号"}
				,{"Fax"			,(int) 13	,"String"	,"FAX"}
				,{"Mail"		,(int) 14	,"String"	,"メールアドレス"}
				,{"Com01"		,(int) 15	,"String"	,"コメント1"}
				,{"Com02"		,(int) 16	,"String"	,"コメント2"}
				,{"Com03"		,(int) 17	,"String"	,"コメント3"}
				,{"ShimeDate"	,(int) 18	,"int"		,"締日"}
				,{"ShimeBasis"	,(int) 19	,"int"		,"請求基準"}
				,{"EntryDate"	,(int) 20	,"String"	,"データ登録日時"}
				,{"UpdateDate"	,(int) 21	,"String"	,"データ更新日時"}
				,{"EntryUser"	,(int) 22	,"String"	,"登録者コード"}
				,{"UpdateUser"	,(int) 23	,"String"	,"更新者コード"}
				,{"PTMSCD"		,(int) 24	,"String"	,"基幹システム荷主コード"}
				};
		
		return RtSettingClMstRt;
	}
	
	public static Object[][] ClMstRt(
			ArrayList<String> SearchClGpCD,
			ArrayList<String> SearchCLCD,
			ArrayList<String> SearchCLName,
			ArrayList<String> SearchPost,
			ArrayList<String> searchAdd,
			ArrayList<String> SearchTel,
			ArrayList<String> SearchFax,
			ArrayList<String> SearchMail,
			ArrayList<String> SearchCom,
			ArrayList<String> SearchWHCD,
			boolean AllSearch){
		//検索条件を受け取って一致する荷主マスタの配列を返却する
		//AllSearch false なら検索条件何も指定されていなければ検索しない
		Object[][] rt = new Object[0][25];
		boolean SearchKick = false;
		if(AllSearch) {SearchKick = true;}
		String sql =  "select "
		+"(KM0030_CLIENTMST.cl_cd) as cl_cd,"				//荷主CD
		+"(KM0030_CLIENTMST.ClGpCD) as ClGpCD,"				//荷主グループCD
		+"(KM0031_CLIENT_GROUP.ClGpName01) as ClGpName,"	//グループ名1
		+"(KM0030_CLIENTMST.WHCD) as WHCD,"					//担当倉庫
		+"(KM0010_WHMST.WHName) as WHName,"					//担当倉庫名
		+"(KM0030_CLIENTMST.CLName01) as CLName01,"			//荷主名1
		+"(KM0030_CLIENTMST.CLName02) as CLName02,"			//荷主名2
		+"(KM0030_CLIENTMST.CLName03) as CLName03,"			//荷主名3
		+"(KM0030_CLIENTMST.Post) as Post,"					//郵便番号
		+"(KM0030_CLIENTMST.Add01) as Add01,"				//住所1
		+"(KM0030_CLIENTMST.Add02) as Add02,"				//住所2
		+"(KM0030_CLIENTMST.Add03) as Add03,"				//住所3
		+"(KM0030_CLIENTMST.Tel) as Tel,"					//電話番号
		+"(KM0030_CLIENTMST.Fax) as Fax,"					//FAX
		+"(KM0030_CLIENTMST.Mail) as Mail,"					//メールアドレス
		+"(KM0030_CLIENTMST.Com01) as Com01,"				//コメント1
		+"(KM0030_CLIENTMST.Com02) as Com02,"				//コメント2
		+"(KM0030_CLIENTMST.Com03) as Com03,"				//コメント3
		+"(KM0030_CLIENTMST.ShimeDate) as ShimeDate,"		//締日
		+"(KM0030_CLIENTMST.ShimeBasis) as ShimeBasis,"		//請求基準
		+"(KM0030_CLIENTMST.EntryDate) as EntryDate,"		//データ登録日時
		+"(KM0030_CLIENTMST.UpdateDate) as UpdateDate,"		//データ更新日時
		+"(KM0030_CLIENTMST.EntryUser) as EntryUser,"		//登録者コード
		+"(KM0030_CLIENTMST.UpdateUser) as UpdateUser,"		//更新者コード
		+"(KM0030_CLIENTMST.PTMSCD) as PTMSCD"				//基幹システム荷主コード
		+" from "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0030_CLIENTMST"
		+ " left outer join "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP "
		+ " on("+A00000Main.MySqlDefaultSchemaNYANKO+".KM0030_CLIENTMST.ClGpCD = "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.ClGpCD)"
		+ " left outer join "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST"
		+ " on("+A00000Main.MySqlDefaultSchemaNYANKO+".KM0030_CLIENTMST.WHCD = "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST.WHCD)"
		+ " where "
		+ " KM0030_CLIENTMST.DelFg <> 1 ";

		if(null !=SearchClGpCD && SearchClGpCD.size()>0) {
			SearchKick = true;
			sql = sql +"and(";
			for(int i=0;i<SearchClGpCD.size();i++) {
				if(i>0) {sql=sql+" or ";}
				sql = sql + "KM0030_CLIENTMST.ClGpCD ='" + SearchClGpCD.get(i) + "'";
			}
			sql = sql +")";
		}
		
		if(null !=SearchWHCD && SearchWHCD.size()>0) {
			SearchKick = true;
			sql = sql +"and(";
			for(int i=0;i<SearchWHCD.size();i++) {
				if(i>0) {sql=sql+" or ";}
				sql = sql + "KM0030_CLIENTMST.WHCD ='" + SearchWHCD.get(i) + "'";
			}
			sql = sql +")";
		}

		if(null !=SearchCLCD && SearchCLCD.size()>0) {
			SearchKick = true;
			sql = sql +"and(";
			for(int i=0;i<SearchCLCD.size();i++) {
				if(i>0) {sql=sql+" or ";}
				sql = sql + "KM0030_CLIENTMST.cl_cd ='" + SearchCLCD.get(i) + "'";
			}
			sql = sql +")";
		}
		if(null !=SearchCLName && SearchCLName.size()>0) {
			SearchKick = true;
			sql = sql +"and(";
			for(int i=0;i<SearchCLName.size();i++) {
				if(i>0) {sql=sql+" or ";}
				sql = sql + "KM0030_CLIENTMST.CLName01 like '%" + SearchCLName.get(i) + "%'";
				sql = sql + " or KM0030_CLIENTMST.CLName02 like '%" + SearchCLName.get(i) + "%'";
				sql = sql + " or KM0030_CLIENTMST.CLName03 like '%" + SearchCLName.get(i) + "%'";
			}
			sql = sql +")";
		}
		if(null !=SearchPost && SearchPost.size()>0) {
			SearchKick = true;
			sql = sql +"and(";
			for(int i=0;i<SearchPost.size();i++) {
				if(i>0) {sql=sql+" or ";}
				sql = sql + "KM0030_CLIENTMST.Post like '" + SearchPost.get(i) + "%'";
			}
			sql = sql +")";
		}
		if(null !=searchAdd && searchAdd.size()>0) {
			SearchKick = true;
			sql = sql +"and(";
			for(int i=0;i<searchAdd.size();i++) {
				if(i>0) {sql=sql+" or ";}
				sql = sql + "KM0030_CLIENTMST.Add01 like '%" + searchAdd.get(i) + "%'";
				sql = sql + " or KM0030_CLIENTMST.Add02 like '%" + searchAdd.get(i) + "%'";
				sql = sql + " or KM0030_CLIENTMST.Add03 like '%" + searchAdd.get(i) + "%'";
			}
			sql = sql +")";
		}
		if(null !=SearchTel && SearchTel.size()>0) {
			SearchKick = true;
			sql = sql +"and(";
			for(int i=0;i<SearchTel.size();i++) {
				if(i>0) {sql=sql+" or ";}
				sql = sql + "KM0030_CLIENTMST.Tel like '%" + SearchTel.get(i) + "%'";
			}
			sql = sql +")";
		}
		if(null !=SearchFax && SearchFax.size()>0) {
			SearchKick = true;
			sql = sql +"and(";
			for(int i=0;i<SearchFax.size();i++) {
				if(i>0) {sql=sql+" or ";}
				sql = sql + "KM0030_CLIENTMST.Fax like '%" + SearchFax.get(i) + "%'";
			}
			sql = sql +")";
		}
		if(null !=SearchMail && SearchMail.size()>0) {
			SearchKick = true;
			sql = sql +"and(";
			for(int i=0;i<SearchMail.size();i++) {
				if(i>0) {sql=sql+" or ";}
				sql = sql + "KM0030_CLIENTMST.Mail like '%" + SearchMail.get(i) + "%'";
			}
			sql = sql +")";
		}
		if(null !=SearchCom && SearchCom.size()>0) {
			SearchKick = true;
			sql = sql +"and(";
			for(int i=0;i<SearchCom.size();i++) {
				if(i>0) {sql=sql+" or ";}
				sql = sql + "KM0030_CLIENTMST.Com01 like '%" + SearchCom.get(i) + "%'";
				sql = sql + " or KM0030_CLIENTMST.Com02 like '%" + SearchCom.get(i) + "%'";
				sql = sql + " or KM0030_CLIENTMST.Com03 like '%" + SearchCom.get(i) + "%'";
			}
			sql = sql +")";
		}
		sql = sql + " order by KM0030_CLIENTMST.cl_cd";
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

				rt = new Object[counter][25];
				counter = 0;
				rset01.beforeFirst();
				while (rset01.next()) {
					if(null==rset01.getString("cl_cd")){rt[counter][0]="";}else{rt[counter][0]=rset01.getString("cl_cd");}				//荷主CD
					if(null==rset01.getString("ClGpCD")){rt[counter][1]="";}else{rt[counter][1]=rset01.getString("ClGpCD");}			//荷主グループCD
					if(null==rset01.getString("ClGpName")){rt[counter][2]="";}else{rt[counter][2]=rset01.getString("ClGpName");}		//グループ名1
					if(null==rset01.getString("WHCD")){rt[counter][3]="";}else{rt[counter][3]=rset01.getString("WHCD");}				//担当倉庫
					if(null==rset01.getString("WHName")){rt[counter][4]="";}else{rt[counter][4]=rset01.getString("WHName");}			//担当倉庫名
					if(null==rset01.getString("CLName01")){rt[counter][5]="";}else{rt[counter][5]=rset01.getString("CLName01");}		//荷主名1
					if(null==rset01.getString("CLName02")){rt[counter][6]="";}else{rt[counter][6]=rset01.getString("CLName02");}		//荷主名2
					if(null==rset01.getString("CLName03")){rt[counter][7]="";}else{rt[counter][7]=rset01.getString("CLName03");}		//荷主名3
					if(null==rset01.getString("Post")){rt[counter][8]="";}else{rt[counter][8]=rset01.getString("Post");}				//郵便番号
					if(null==rset01.getString("Add01")){rt[counter][9]="";}else{rt[counter][9]=rset01.getString("Add01");}				//住所1
					if(null==rset01.getString("Add02")){rt[counter][10]="";}else{rt[counter][10]=rset01.getString("Add02");}			//住所2
					if(null==rset01.getString("Add03")){rt[counter][11]="";}else{rt[counter][11]=rset01.getString("Add03");}			//住所3
					if(null==rset01.getString("Tel")){rt[counter][12]="";}else{rt[counter][12]=rset01.getString("Tel");}				//電話番号
					if(null==rset01.getString("Fax")){rt[counter][13]="";}else{rt[counter][13]=rset01.getString("Fax");}				//FAX
					if(null==rset01.getString("Mail")){rt[counter][14]="";}else{rt[counter][14]=rset01.getString("Mail");}				//メールアドレス
					if(null==rset01.getString("Com01")){rt[counter][15]="";}else{rt[counter][15]=rset01.getString("Com01");}			//コメント1
					if(null==rset01.getString("Com02")){rt[counter][16]="";}else{rt[counter][16]=rset01.getString("Com02");}			//コメント2
					if(null==rset01.getString("Com03")){rt[counter][17]="";}else{rt[counter][17]=rset01.getString("Com03");}			//コメント3
					rt[counter][18]="" + rset01.getInt("ShimeDate");	//締日
					rt[counter][19]="" + rset01.getInt("ShimeBasis");	//請求基準
					if(null==rset01.getTimestamp("EntryDate")){rt[counter][20]="";}else{rt[counter][20]=B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("EntryDate"))[1];}	//データ登録日時
					if(null==rset01.getTimestamp("UpdateDate")){rt[counter][21]="";}else{rt[counter][21]=B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("UpdateDate"))[1];}	//データ更新日時
					if(null==rset01.getString("EntryUser")){rt[counter][22]="";}else{rt[counter][22]=rset01.getString("EntryUser");}	//登録者コード
					if(null==rset01.getString("UpdateUser")){rt[counter][23]="";}else{rt[counter][23]=rset01.getString("UpdateUser");}	//更新者コード
					if(null==rset01.getString("PTMSCD")){rt[counter][24]="";}else{rt[counter][24]=rset01.getString("PTMSCD");}			//基幹システム荷主コード
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
	
	public static String[] NewClCdGet(int NeedCount) {
		ArrayList<String> SearchClGpCD = new ArrayList<String>();
		ArrayList<String> SearchCLCD = new ArrayList<String>();
    	ArrayList<String> SearchCLName = new ArrayList<String>();
    	ArrayList<String> SearchPost = new ArrayList<String>();
    	ArrayList<String> searchAdd = new ArrayList<String>();
		ArrayList<String> SearchTel = new ArrayList<String>();
		ArrayList<String> SearchFax = new ArrayList<String>();
		ArrayList<String> SearchMail = new ArrayList<String>(); 
		ArrayList<String> SearchCom = new ArrayList<String>();
		ArrayList<String> SearchWHCD = new ArrayList<String>();
		boolean AllSearch = true;
    	
    	Object[][] ClMstRt = ClMstRt(
    			SearchClGpCD,SearchCLCD, SearchCLName, SearchPost, searchAdd,
				SearchTel, SearchFax, SearchMail,  SearchCom, SearchWHCD, AllSearch);
    	
    	int ClientNo = 0;
    	
    	for(int i=0;i<ClMstRt.length;i++) {
    		if("ATCL".equals((""+ClMstRt[i][0]).substring(0,4))&&11==(""+ClMstRt[i][0]).length()) {
    			String WST = B00020ToolsTextControl.num_only_String(""+ClMstRt[i][0]);
    			if(7==WST.length()) {
    				int wint = Integer.parseInt(WST);
    				if(ClientNo<wint) {
    					ClientNo=wint;
    				}
    			}
    		}
    	}

    	String[] rt = new String[NeedCount];
    	for(int i=0;i<NeedCount;i++) {
    		ClientNo = ClientNo+1;
	    	rt[i] = "0000000"+ClientNo;
	    	rt[i] = "ATCL"+rt[i].substring(rt[i].length()-7,rt[i].length());
    	}
    	
    	return rt;
	}
}