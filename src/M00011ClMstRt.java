import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class M00011ClMstRt{
	/*
	コピペ用
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
	boolean AllSearch = false;
	
	Object[][] ClMstRt = M00011ClMstRt.ClMstRt(
		SearchClGpCD,
		SearchCLCD,
		SearchCLName,
		SearchPost,
		searchAdd,
		SearchTel,
		SearchFax,
		SearchMail,
		SearchCom,
		SearchWHCD,
		AllSearch);
		
	String Getcl_cd			=(String)ClMstRt[i][M00011ClMstRt.Colcl_cd];		//荷主CD
	String GetClGpCD		=(String)ClMstRt[i][M00011ClMstRt.ColClGpCD];		//荷主グループCD
	String GetClGpName		=(String)ClMstRt[i][M00011ClMstRt.ColClGpName];		//グループ名1
	String GetWHCD			=(String)ClMstRt[i][M00011ClMstRt.ColWHCD];			//担当倉庫
	String GetWHName		=(String)ClMstRt[i][M00011ClMstRt.ColWHName];		//担当倉庫名
	String GetCLName01		=(String)ClMstRt[i][M00011ClMstRt.ColCLName01];		//荷主名1
	String GetCLName02		=(String)ClMstRt[i][M00011ClMstRt.ColCLName02];		//荷主名2
	String GetCLName03		=(String)ClMstRt[i][M00011ClMstRt.ColCLName03];		//荷主名3
	String GetPost			=(String)ClMstRt[i][M00011ClMstRt.ColPost];			//郵便番号
	String GetAdd01			=(String)ClMstRt[i][M00011ClMstRt.ColAdd01];		//住所1
	String GetAdd02			=(String)ClMstRt[i][M00011ClMstRt.ColAdd02];		//住所2
	String GetAdd03			=(String)ClMstRt[i][M00011ClMstRt.ColAdd03];		//住所3
	String GetTel			=(String)ClMstRt[i][M00011ClMstRt.ColTel];			//電話番号
	String GetFax			=(String)ClMstRt[i][M00011ClMstRt.ColFax];			//FAX
	String GetMail			=(String)ClMstRt[i][M00011ClMstRt.ColMail];			//メールアドレス
	String GetCom01			=(String)ClMstRt[i][M00011ClMstRt.ColCom01];		//コメント1
	String GetCom02			=(String)ClMstRt[i][M00011ClMstRt.ColCom02];		//コメント2
	String GetCom03			=(String)ClMstRt[i][M00011ClMstRt.ColCom03];		//コメント3
	int GetShimeDate		=(int)ClMstRt[i][M00011ClMstRt.ColShimeDate];		//締日
	int GetShimeBasis		=(int)ClMstRt[i][M00011ClMstRt.ColShimeBasis];		//請求基準
	String GetEntryDate		=(String)ClMstRt[i][M00011ClMstRt.ColEntryDate];	//データ登録日時
	String GetUpdateDate	=(String)ClMstRt[i][M00011ClMstRt.ColUpdateDate];	//データ更新日時
	String GetEntryUser		=(String)ClMstRt[i][M00011ClMstRt.ColEntryUser];	//登録者コード
	String GetUpdateUser	=(String)ClMstRt[i][M00011ClMstRt.ColUpdateUser];	//更新者コード
	String GetPTMSCD		=(String)ClMstRt[i][M00011ClMstRt.ColPTMSCD];		//基幹システム荷主コード
	*/
	
	//戻り値カラム
	static int Colcl_cd		= (int)  0;	//荷主CD
	static int ColClGpCD		= (int)  1;	//荷主グループCD
	static int ColClGpName		= (int)  2;	//グループ名1
	static int ColWHCD			= (int)  3;	//担当倉庫
	static int ColWHName		= (int)  4;	//担当倉庫名
	static int ColCLName01		= (int)  5;	//荷主名1
	static int ColCLName02		= (int)  6;	//荷主名2
	static int ColCLName03		= (int)  7;	//荷主名3
	static int ColPost			= (int)  8;	//郵便番号
	static int ColAdd01		= (int)  9;	//住所1
	static int ColAdd02		= (int) 10;	//住所2
	static int ColAdd03		= (int) 11;	//住所3
	static int ColTel			= (int) 12;	//電話番号
	static int ColFax			= (int) 13;	//FAX
	static int ColMail			= (int) 14;	//メールアドレス
	static int ColCom01		= (int) 15;	//コメント1
	static int ColCom02		= (int) 16;	//コメント2
	static int ColCom03		= (int) 17;	//コメント3
	static int ColShimeDate	= (int) 18;	//締日
	static int ColShimeBasis	= (int) 19;	//請求基準
	static int ColEntryDate	= (int) 20;	//データ登録日時
	static int ColUpdateDate	= (int) 21;	//データ更新日時
	static int ColEntryUser	= (int) 22;	//登録者コード
	static int ColUpdateUser	= (int) 23;	//更新者コード
	static int ColPTMSCD		= (int) 24;	//基幹システム荷主コード
	
	public static Object[][] RtSettingClMstRt(){
		Object[][] RtSettingClMstRt = {
				 {"cl_cd"		,Colcl_cd			,"String"	,"荷主CD"}
				,{"ClGpCD"		,ColClGpCD			,"String"	,"荷主グループCD"}
				,{"ClGpName"	,ColClGpName		,"String"	,"グループ名1"}
				,{"WHCD"		,ColWHCD			,"String"	,"担当倉庫"}
				,{"WHName"		,ColWHName			,"String"	,"担当倉庫名"}
				,{"CLName01"	,ColCLName01		,"String"	,"荷主名1"}
				,{"CLName02"	,ColCLName02		,"String"	,"荷主名2"}
				,{"CLName03"	,ColCLName03		,"String"	,"荷主名3"}
				,{"Post"		,ColPost			,"String"	,"郵便番号"}
				,{"Add01"		,ColAdd01			,"String"	,"住所1"}
				,{"Add02"		,ColAdd02			,"String"	,"住所2"}
				,{"Add03"		,ColAdd03			,"String"	,"住所3"}
				,{"Tel"			,ColTel			,"String"	,"電話番号"}
				,{"Fax"			,ColFax			,"String"	,"FAX"}
				,{"Mail"		,ColMail			,"String"	,"メールアドレス"}
				,{"Com01"		,ColCom01			,"String"	,"コメント1"}
				,{"Com02"		,ColCom02			,"String"	,"コメント2"}
				,{"Com03"		,ColCom03			,"String"	,"コメント3"}
				,{"ShimeDate"	,ColShimeDate		,"int"		,"締日"}
				,{"ShimeBasis"	,ColShimeBasis	,"int"		,"請求基準"}
				,{"EntryDate"	,ColEntryDate		,"String"	,"データ登録日時"}
				,{"UpdateDate"	,ColUpdateDate	,"String"	,"データ更新日時"}
				,{"EntryUser"	,ColEntryUser		,"String"	,"登録者コード"}
				,{"UpdateUser"	,ColUpdateUser	,"String"	,"更新者コード"}
				,{"PTMSCD"		,ColPTMSCD			,"String"	,"基幹システム荷主コード"}
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
		SearchClGpCD	= B00150ArrayListControl.ArryListStringUniqueList(SearchClGpCD);
		SearchCLCD		= B00150ArrayListControl.ArryListStringUniqueList(SearchCLCD);
		SearchCLName	= B00150ArrayListControl.ArryListStringUniqueList(SearchCLName);
		SearchPost		= B00150ArrayListControl.ArryListStringUniqueList(SearchPost);
		searchAdd		= B00150ArrayListControl.ArryListStringUniqueList(searchAdd);
		SearchTel		= B00150ArrayListControl.ArryListStringUniqueList(SearchTel);
		SearchFax		= B00150ArrayListControl.ArryListStringUniqueList(SearchFax);
		SearchMail		= B00150ArrayListControl.ArryListStringUniqueList(SearchMail);
		SearchCom		= B00150ArrayListControl.ArryListStringUniqueList(SearchCom);
		SearchWHCD		= B00150ArrayListControl.ArryListStringUniqueList(SearchWHCD);
		
		
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
				sql = sql + "KM0030_CLIENTMST.ClGpCD =?";
			}
			sql = sql +")";
		}
		
		if(null !=SearchWHCD && SearchWHCD.size()>0) {
			SearchKick = true;
			sql = sql +"and(";
			for(int i=0;i<SearchWHCD.size();i++) {
				if(i>0) {sql=sql+" or ";}
				sql = sql + "KM0030_CLIENTMST.WHCD =?";
			}
			sql = sql +")";
		}

		if(null !=SearchCLCD && SearchCLCD.size()>0) {
			SearchKick = true;
			sql = sql +"and(";
			for(int i=0;i<SearchCLCD.size();i++) {
				if(i>0) {sql=sql+" or ";}
				sql = sql + "KM0030_CLIENTMST.cl_cd =?";
			}
			sql = sql +")";
		}
		if(null !=SearchCLName && SearchCLName.size()>0) {
			SearchKick = true;
			sql = sql +"and(";
			for(int i=0;i<SearchCLName.size();i++) {
				if(i>0) {sql=sql+" or ";}
				sql = sql + "KM0030_CLIENTMST.CLName01 like ?";
				sql = sql + " or KM0030_CLIENTMST.CLName02 like ?";
				sql = sql + " or KM0030_CLIENTMST.CLName03 like ?";
			}
			sql = sql +")";
		}
		if(null !=SearchPost && SearchPost.size()>0) {
			SearchKick = true;
			sql = sql +"and(";
			for(int i=0;i<SearchPost.size();i++) {
				if(i>0) {sql=sql+" or ";}
				sql = sql + "KM0030_CLIENTMST.Post like ?";
			}
			sql = sql +")";
		}
		if(null !=searchAdd && searchAdd.size()>0) {
			SearchKick = true;
			sql = sql +"and(";
			for(int i=0;i<searchAdd.size();i++) {
				if(i>0) {sql=sql+" or ";}
				sql = sql + " CONCAT (KM0030_CLIENTMST.Add0";
				sql = sql + " , KM0030_CLIENTMST.Add02";
				sql = sql + " , KM0030_CLIENTMST.Add03) like ?";
			}
			sql = sql +")";
		}
		if(null !=SearchTel && SearchTel.size()>0) {
			SearchKick = true;
			sql = sql +"and(";
			for(int i=0;i<SearchTel.size();i++) {
				if(i>0) {sql=sql+" or ";}
				sql = sql + "KM0030_CLIENTMST.Tel like ?";
			}
			sql = sql +")";
		}
		if(null !=SearchFax && SearchFax.size()>0) {
			SearchKick = true;
			sql = sql +"and(";
			for(int i=0;i<SearchFax.size();i++) {
				if(i>0) {sql=sql+" or ";}
				sql = sql + "KM0030_CLIENTMST.Fax like ?";
			}
			sql = sql +")";
		}
		if(null !=SearchMail && SearchMail.size()>0) {
			SearchKick = true;
			sql = sql +"and(";
			for(int i=0;i<SearchMail.size();i++) {
				if(i>0) {sql=sql+" or ";}
				sql = sql + "KM0030_CLIENTMST.Mail like ?";
			}
			sql = sql +")";
		}
		if(null !=SearchCom && SearchCom.size()>0) {
			SearchKick = true;
			sql = sql +"and(";
			for(int i=0;i<SearchCom.size();i++) {
				if(i>0) {sql=sql+" or ";}
				sql = sql + "KM0030_CLIENTMST.Com01 like ?";
				sql = sql + " or KM0030_CLIENTMST.Com02 like ?";
				sql = sql + " or KM0030_CLIENTMST.Com03 like ?";
			}
			sql = sql +")";
		}
		sql = sql + " order by KM0030_CLIENTMST.cl_cd";
		//System.out.println(sql);
		if(SearchKick) {
			A00010DbConnect.DB_CONN("NYANKO");
			
			ResultSet rset01 = null;
			PreparedStatement stmt01 = null;
			try {
				stmt01 = A00010DbConnect.conn.prepareStatement(sql);
				int StmtCount = 0;
				
				if(null !=SearchClGpCD && SearchClGpCD.size()>0) {
					for(int i=0;i<SearchClGpCD.size();i++) {
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchClGpCD.get(i) +"");
					}
				}
				
				if(null !=SearchWHCD && SearchWHCD.size()>0) {
					for(int i=0;i<SearchWHCD.size();i++) {
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchWHCD.get(i) +"");
					}
				}

				if(null !=SearchCLCD && SearchCLCD.size()>0) {
					for(int i=0;i<SearchCLCD.size();i++) {
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchCLCD.get(i) +"");
					}
				}
				if(null !=SearchCLName && SearchCLName.size()>0) {
					for(int i=0;i<SearchCLName.size();i++) {
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchCLName.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchCLName.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchCLName.get(i)+"%");
					}
				}
				if(null !=SearchPost && SearchPost.size()>0) {
					for(int i=0;i<SearchPost.size();i++) {
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchPost.get(i)+"%");
					}
				}
				if(null !=searchAdd && searchAdd.size()>0) {
					for(int i=0;i<searchAdd.size();i++) {
						if(i>0) {sql=sql+" or ";}
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+searchAdd.get(i)+"%");
					}
				}
				if(null !=SearchTel && SearchTel.size()>0) {
					for(int i=0;i<SearchTel.size();i++) {
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchTel.get(i)+"%");
					}
				}
				if(null !=SearchFax && SearchFax.size()>0) {
					for(int i=0;i<SearchFax.size();i++) {
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchFax.get(i)+"%");
					}
				}
				if(null !=SearchMail && SearchMail.size()>0) {
					for(int i=0;i<SearchMail.size();i++) {
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchMail.get(i)+"%");
					}
				}
				if(null !=SearchCom && SearchCom.size()>0) {
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

				rt = new Object[counter][25];
				counter = 0;
				rset01.beforeFirst();
				while (rset01.next()) {
					if(null==rset01.getString("cl_cd")){		rt[counter][Colcl_cd]			="";}else{rt[counter][Colcl_cd]		=rset01.getString("cl_cd");}		//荷主CD
					if(null==rset01.getString("ClGpCD")){		rt[counter][ColClGpCD]			="";}else{rt[counter][ColClGpCD]		=rset01.getString("ClGpCD");}		//荷主グループCD
					if(null==rset01.getString("ClGpName")){		rt[counter][ColClGpName]		="";}else{rt[counter][ColClGpName]		=rset01.getString("ClGpName");}		//グループ名1
					if(null==rset01.getString("WHCD")){			rt[counter][ColWHCD]			="";}else{rt[counter][ColWHCD]			=rset01.getString("WHCD");}			//担当倉庫
					if(null==rset01.getString("WHName")){		rt[counter][ColWHName]			="";}else{rt[counter][ColWHName]		=rset01.getString("WHName");}		//担当倉庫名
					if(null==rset01.getString("CLName01")){		rt[counter][ColCLName01]		="";}else{rt[counter][ColCLName01]		=rset01.getString("CLName01");}		//荷主名1
					if(null==rset01.getString("CLName02")){		rt[counter][ColCLName02]		="";}else{rt[counter][ColCLName02]		=rset01.getString("CLName02");}		//荷主名2
					if(null==rset01.getString("CLName03")){		rt[counter][ColCLName03]		="";}else{rt[counter][ColCLName03]		=rset01.getString("CLName03");}		//荷主名3
					if(null==rset01.getString("Post")){			rt[counter][ColPost]			="";}else{rt[counter][ColPost]			=rset01.getString("Post");}			//郵便番号
					if(null==rset01.getString("Add01")){		rt[counter][ColAdd01]			="";}else{rt[counter][ColAdd01]		=rset01.getString("Add01");}		//住所1
					if(null==rset01.getString("Add02")){		rt[counter][ColAdd02]			="";}else{rt[counter][ColAdd02]		=rset01.getString("Add02");}		//住所2
					if(null==rset01.getString("Add03")){		rt[counter][ColAdd03]			="";}else{rt[counter][ColAdd03]		=rset01.getString("Add03");}		//住所3
					if(null==rset01.getString("Tel")){			rt[counter][ColTel]			="";}else{rt[counter][ColTel]			=rset01.getString("Tel");}			//電話番号
					if(null==rset01.getString("Fax")){			rt[counter][ColFax]			="";}else{rt[counter][ColFax]			=rset01.getString("Fax");}			//FAX
					if(null==rset01.getString("Mail")){			rt[counter][ColMail]			="";}else{rt[counter][ColMail]			=rset01.getString("Mail");}			//メールアドレス
					if(null==rset01.getString("Com01")){		rt[counter][ColCom01]			="";}else{rt[counter][ColCom01]		=rset01.getString("Com01");}		//コメント1
					if(null==rset01.getString("Com02")){		rt[counter][ColCom02]			="";}else{rt[counter][ColCom02]		=rset01.getString("Com02");}		//コメント2
					if(null==rset01.getString("Com03")){		rt[counter][ColCom03]			="";}else{rt[counter][ColCom03]		=rset01.getString("Com03");}		//コメント3
					rt[counter][ColShimeDate]="" + rset01.getInt("ShimeDate");	//締日
					rt[counter][ColShimeBasis]="" + rset01.getInt("ShimeBasis");	//請求基準
					if(null==rset01.getTimestamp("EntryDate")){	rt[counter][ColEntryDate]		="";}else{rt[counter][ColEntryDate]	=B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("EntryDate"))[1];}	//データ登録日時
					if(null==rset01.getTimestamp("UpdateDate")){rt[counter][ColUpdateDate]	="";}else{rt[counter][ColUpdateDate]	=B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("UpdateDate"))[1];}	//データ更新日時
					if(null==rset01.getString("EntryUser")){	rt[counter][ColEntryUser]		="";}else{rt[counter][ColEntryUser]	=rset01.getString("EntryUser");}	//登録者コード
					if(null==rset01.getString("UpdateUser")){	rt[counter][ColUpdateUser]	="";}else{rt[counter][ColUpdateUser]	=rset01.getString("UpdateUser");}	//更新者コード
					if(null==rset01.getString("PTMSCD")){		rt[counter][ColPTMSCD]			="";}else{rt[counter][ColPTMSCD]		=rset01.getString("PTMSCD");}		//基幹システム荷主コード
					
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
    		if("ATCL".equals((""+ClMstRt[i][M00011ClMstRt.Colcl_cd]).substring(0,4))&&11==(""+ClMstRt[i][M00011ClMstRt.Colcl_cd]).length()) {
    			String WST = B00020ToolsTextControl.num_only_String(""+ClMstRt[i][M00011ClMstRt.Colcl_cd]);
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