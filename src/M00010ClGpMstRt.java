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
			
	String GetClGpCD		= (String)ClGpMstRt[i][M00010ClGpMstRt.ColClGpCD]		//荷主グループCD
	String GetCLGpName01	= (String)ClGpMstRt[i][M00010ClGpMstRt.ColCLGpName01]	//荷主グループ名1
	String GetCLGpName02	= (String)ClGpMstRt[i][M00010ClGpMstRt.ColCLGpName02]	//荷主グループ名2
	String GetCLGpName03	= (String)ClGpMstRt[i][M00010ClGpMstRt.ColCLGpName03]	//荷主グループ名3
	String GetPost			= (String)ClGpMstRt[i][M00010ClGpMstRt.ColPost]			//郵便番号
	String GetAdd01			= (String)ClGpMstRt[i][M00010ClGpMstRt.ColAdd01]		//住所1
	String GetAdd02			= (String)ClGpMstRt[i][M00010ClGpMstRt.ColAdd02]		//住所2
	String GetAdd03			= (String)ClGpMstRt[i][M00010ClGpMstRt.ColAdd03]		//住所3
	String GetTel			= (String)ClGpMstRt[i][M00010ClGpMstRt.ColTel]			//電話番号
	String GetFax			= (String)ClGpMstRt[i][M00010ClGpMstRt.ColFax]			//FAX
	String GetMail			= (String)ClGpMstRt[i][M00010ClGpMstRt.ColMail]			//メールアドレス
	String GetCom01			= (String)ClGpMstRt[i][M00010ClGpMstRt.ColCom01]		//コメント1
	String GetCom02			= (String)ClGpMstRt[i][M00010ClGpMstRt.ColCom02]		//コメント2
	String GetCom03			= (String)ClGpMstRt[i][M00010ClGpMstRt.ColCom03]		//コメント3
	String GetEntryDate		= (String)ClGpMstRt[i][M00010ClGpMstRt.ColEntryDate]	//データ登録日時
	String GetUpdateDate	= (String)ClGpMstRt[i][M00010ClGpMstRt.ColUpdateDate]	//データ更新日時
	String GetEntryUser		= (String)ClGpMstRt[i][M00010ClGpMstRt.ColEntryUser]	//登録者コード
	String GetUpdateUser	= (String)ClGpMstRt[i][M00010ClGpMstRt.ColUpdateUser]	//更新者コード
	String GetPassWord		= (String)ClGpMstRt[i][M00010ClGpMstRt.ColPassWord]		//パスワード
	*/
	//戻り値カラム
	static int ColClGpCD		= (int) 0;	//荷主グループCD
	static int ColCLGpName01	= (int) 1;	//荷主グループ名1
	static int ColCLGpName02	= (int) 2;	///荷主グループ名2
	static int ColCLGpName03	= (int) 3;	//荷主グループ名3
	static int ColPost			= (int) 4;	//郵便番号
	static int ColAdd01		= (int) 5;	//住所1
	static int ColAdd02		= (int) 6;	//住所2
	static int ColAdd03		= (int) 7;	//住所3
	static int ColTel			= (int) 8;	//電話番号
	static int ColFax			= (int) 9;	//FAX"}
	static int ColMail			= (int)10;	//メールアドレス
	static int ColCom01		= (int)11;	//コメント1
	static int ColCom02		= (int)12;	//コメント2
	static int ColCom03		= (int)13;	//コメント3
	static int ColEntryDate	= (int)14;	//データ登録日時
	static int ColUpdateDate	= (int)15;	//データ更新日時
	static int ColEntryUser	= (int)16;	//"登録者コード
	static int ColUpdateUser	= (int)17;	//更新者コード
	static int ColPassWord		= (int)18;	//パスワード
	
	public static Object[][] RtSettingClGpMstRt(){
		Object[][] RtSettingClGpMstRt = {
				 {"ClGpCD"		,ColClGpCD			,"String"	,"荷主グループCD"	,"Key"}
				,{"CLGpName01"	,ColCLGpName01	,"String"	,"荷主グループ名1"	,""}
				,{"CLGpName02"	,ColCLGpName02	,"String"	,"荷主グループ名2"	,""}
				,{"CLGpName03"	,ColCLGpName03	,"String"	,"荷主グループ名3"	,""}
				,{"Post"		,ColPost			,"String"	,"郵便番号"			,""}
				,{"Add01"		,ColAdd01			,"String"	,"住所1"			,""}
				,{"Add02"		,ColAdd02			,"String"	,"住所2"			,""}
				,{"Add03"		,ColAdd03			,"String"	,"住所3"			,""}
				,{"Tel"			,ColTel			,"String"	,"電話番号"			,""}
				,{"Fax"			,ColFax			,"String"	,"FAX"				,""}
				,{"Mail"		,ColMail			,"String"	,"メールアドレス"	,""}
				,{"Com01"		,ColCom01			,"String"	,"コメント1"		,""}
				,{"Com02"		,ColCom02			,"String"	,"コメント2"		,""}
				,{"Com03"		,ColCom03			,"String"	,"コメント3"		,""}
				,{"EntryDate"	,ColEntryDate		,"String"	,"データ登録日時"	,""}
				,{"UpdateDate"	,ColUpdateDate	,"String"	,"データ更新日時"	,""}
				,{"EntryUser"	,ColEntryUser		,"String"	,"登録者コード"		,""}
				,{"UpdateUser"	,ColUpdateUser	,"String"	,"更新者コード"		,""}
				,{"PassWord"	,ColPassWord		,"String"	,"パスワード"		,""}
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
		
		SearchClGpCD	= B00150ArrayListControl.ArryListStringUniqueList(SearchClGpCD);
		SearchCLGpName	= B00150ArrayListControl.ArryListStringUniqueList(SearchCLGpName);
		SearchPost		= B00150ArrayListControl.ArryListStringUniqueList(SearchPost);
		SearchAdd		= B00150ArrayListControl.ArryListStringUniqueList(SearchAdd);
		SearchTel		= B00150ArrayListControl.ArryListStringUniqueList(SearchTel);
		SearchFax		= B00150ArrayListControl.ArryListStringUniqueList(SearchFax);
		SearchMail		= B00150ArrayListControl.ArryListStringUniqueList(SearchMail);
		SearchCom		= B00150ArrayListControl.ArryListStringUniqueList(SearchCom);
		
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
					if(null==rset01.getString("ClGpCD")){		rt[counter][ColClGpCD] 		= "";}else{rt[counter][ColClGpCD] 		= rset01.getString("ClGpCD");}		//荷主グループCD
					if(null==rset01.getString("CLGpName01")){	rt[counter][ColCLGpName01] 	= "";}else{rt[counter][ColCLGpName01]	= rset01.getString("CLGpName01");}	//荷主グループ名1
					if(null==rset01.getString("CLGpName02")){	rt[counter][ColCLGpName02] 	= "";}else{rt[counter][ColCLGpName02]	= rset01.getString("CLGpName02");}	//荷主グループ名2
					if(null==rset01.getString("CLGpName03")){	rt[counter][ColCLGpName03] 	= "";}else{rt[counter][ColCLGpName03]	= rset01.getString("CLGpName03");}	//荷主グループ名3
					if(null==rset01.getString("Post")){			rt[counter][ColPost] 			= "";}else{rt[counter][ColPost] 		= rset01.getString("Post");}		//郵便番号
					if(null==rset01.getString("Add01")){		rt[counter][ColAdd01] 			= "";}else{rt[counter][ColAdd01] 		= rset01.getString("Add01");}		//住所1
					if(null==rset01.getString("Add02")){		rt[counter][ColAdd02] 			= "";}else{rt[counter][ColAdd02]		= rset01.getString("Add02");}		//住所2
					if(null==rset01.getString("Add03")){		rt[counter][ColAdd03] 			= "";}else{rt[counter][ColAdd03]		= rset01.getString("Add03");}		//住所3
					if(null==rset01.getString("Tel")){			rt[counter][ColTel] 			= "";}else{rt[counter][ColTel]			= rset01.getString("Tel");}			//電話番号
					if(null==rset01.getString("Fax")){			rt[counter][ColFax] 			= "";}else{rt[counter][ColFax]			= rset01.getString("Fax");}			//FAX
					if(null==rset01.getString("Mail")){			rt[counter][ColMail] 			= "";}else{rt[counter][ColMail]		= rset01.getString("Mail");}		//メールアドレス
					if(null==rset01.getString("Com01")){		rt[counter][ColCom01] 			= "";}else{rt[counter][ColCom01]		= rset01.getString("Com01");}		//コメント1
					if(null==rset01.getString("Com02")){		rt[counter][ColCom02] 			= "";}else{rt[counter][ColCom02]		= rset01.getString("Com02");}		//コメント2
					if(null==rset01.getString("Com03")){		rt[counter][ColCom03] 			= "";}else{rt[counter][ColCom03]		= rset01.getString("Com03");}		//コメント3
					if(null==rset01.getTimestamp("EntryDate")){	rt[counter][ColEntryDate] 	= "";}else{rt[counter][ColEntryDate]	= B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("EntryDate"))[1];}		//データ登録日時
					if(null==rset01.getTimestamp("UpdateDate")){rt[counter][ColUpdateDate] 	= "";}else{rt[counter][ColUpdateDate]	= B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("UpdateDate"))[1];}		//データ更新日時
					if(null==rset01.getString("EntryUser")){	rt[counter][ColEntryUser] 	= "";}else{rt[counter][ColEntryUser]	= rset01.getString("EntryUser");}	//登録者コード
					if(null==rset01.getString("UpdateUser")){	rt[counter][ColUpdateUser] 	= "";}else{rt[counter][ColUpdateUser]	= rset01.getString("UpdateUser");}	//更新者コード
					if(null==rset01.getString("PassWord")){		rt[counter][ColPassWord] 		= "";}else{rt[counter][ColPassWord]	= rset01.getString("PassWord");}		//パスワード
					
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
    		if(4<(""+ClGpMstRt[i][M00010ClGpMstRt.ColClGpCD]).length()&&"ATGR".equals((""+ClGpMstRt[i][M00010ClGpMstRt.ColClGpCD]).substring(0,4))) {
    			String WST = B00020ToolsTextControl.num_only_String(""+ClGpMstRt[i][M00010ClGpMstRt.ColClGpCD]);
    			if("".equals(WST)){WST = "0";}
				int wint = Integer.parseInt(WST);
				if(ClGpNo<wint) {
					ClGpNo=wint;
				}
    		}
    	}
    	
    	String[] rt = new String[NeedCount];
    	int MaxCount = 999999999;
    	int wint = MaxCount+1;
    	String SetZero = (""+wint).substring(1,(""+wint).length());
    	for(int i=0;i<NeedCount;i++) {
    		ClGpNo = ClGpNo+1;
    		if(MaxCount<ClGpNo) {
    			rt[i] = "ATGR"+ClGpNo;
    		}else {
		    	rt[i] = SetZero+ClGpNo;
		    	rt[i] = "ATGR"+rt[i].substring(rt[i].length()-SetZero.length(),rt[i].length());
    		}
    	}
    	
    	return rt;
	}
}