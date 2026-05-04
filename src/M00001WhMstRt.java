import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class M00001WhMstRt{
	/*
	コピペ用
	ArrayList<String> SearchWHCD 	= new ArrayList<String>();
	ArrayList<String> SearchWHName 	= new ArrayList<String>();
	ArrayList<String> SearchPost 	= new ArrayList<String>();
	ArrayList<String> SearchAdd 	= new ArrayList<String>();
	ArrayList<String> SearchTel 	= new ArrayList<String>();
	ArrayList<String> SearchFax 	= new ArrayList<String>();
	ArrayList<String> SearchMail 	= new ArrayList<String>();
	ArrayList<String> SearchCom 	= new ArrayList<String>();
	ArrayList<String> SearchPTMSCD 	= new ArrayList<String>();
	boolean AllSearch = false;
	
	Object[][] WhMstRt = M00001WhMstRt.WhMstRt(
			SearchWHCD,
			SearchWHName,
			SearchPost,
			SearchAdd,
			SearchTel,
			SearchFax,
			SearchMail,
			SearchCom,
			SearchPTMSCD,
			AllSearch);
			
	String GetNoWHCD		= (String)WhMstRt[i][M00001WhMstRt.ColNoWHCD];			//倉庫コード
	String GetNoWHName		= (String)WhMstRt[i][M00001WhMstRt.ColNoWHName];		//拠点倉庫名
	String GetNoPost		= (String)WhMstRt[i][M00001WhMstRt.ColNoPost];			//拠点倉庫郵便番号
	String GetNoAdd01		= (String)WhMstRt[i][M00001WhMstRt.ColNoAdd01];			//拠点倉庫住所1
	String GetNoAdd02		= (String)WhMstRt[i][M00001WhMstRt.ColNoAdd02];			//拠点倉庫住所2
	String GetNoTel			= (String)WhMstRt[i][M00001WhMstRt.ColNoTel];			//拠点倉庫電話
	String GetNoFax			= (String)WhMstRt[i][M00001WhMstRt.ColNoFax];			//拠点倉庫FAX
	String GetNoMail		= (String)WhMstRt[i][M00001WhMstRt.ColNoMail];			//拠点倉庫MAIL
	String GetNoCom01		= (String)WhMstRt[i][M00001WhMstRt.ColNoCom01];			//コメント１
	String GetNoCom02		= (String)WhMstRt[i][M00001WhMstRt.ColNoCom02];			//コメント２
	String GetNoCom03		= (String)WhMstRt[i][M00001WhMstRt.ColNoCom03];			//コメント３
	String GetNoPTMSCD		= (String)WhMstRt[i][M00001WhMstRt.ColNoPTMSCD];		//基幹システム連携用事業所CD
	String GetNoEntryDate	= (String)WhMstRt[i][M00001WhMstRt.ColNoEntryDate];		//データ登録日時
	String GetNoUpdateDate	= (String)WhMstRt[i][M00001WhMstRt.ColNoUpdateDate];	//データ更新日時
	String GetNoEntryUser	= (String)WhMstRt[i][M00001WhMstRt.ColNoEntryUser];		//登録者
	String GetNoUpdateUser	= (String)WhMstRt[i][M00001WhMstRt.ColNoUpdateUser];	//更新者
			
	
	*/
	//戻り値カラム
	static final  int ColNoWHCD 			= (int) 0;	//倉庫コード
	static final  int ColNoWHName 		= (int) 1;	//拠点倉庫名
	static final  int ColNoPost 			= (int) 2;	//拠点倉庫郵便番号
	static final  int ColNoAdd01 			= (int) 3;	//拠点倉庫住所1
	static final  int ColNoAdd02 			= (int) 4;	//拠点倉庫住所2
	static final  int ColNoTel 			= (int) 5;	//拠点倉庫電話
	static final  int ColNoFax 			= (int) 6;	//拠点倉庫FAX
	static final  int ColNoMail 			= (int) 7;	//拠点倉庫MAIL
	static final  int ColNoCom01 			= (int) 8;	//コメント１
	static final  int ColNoCom02 			= (int) 9;	//コメント２
	static final  int ColNoCom03 			= (int)10;	//コメント３
	static final  int ColNoPTMSCD 		= (int)11;	//基幹システム連携用事業所CD
	static final  int ColNoEntryDate 	= (int)12;	//データ登録日時
	static final  int ColNoUpdateDate 	= (int)13;	//データ更新日時
	static final  int ColNoEntryUser 	= (int)14;	//登録者
	static final  int ColNoUpdateUser 	= (int)15;	//更新者
	
	public static Object[][] RtSettingWhMstRt(){
		Object[][] RtSettingWhMstRt = {
				 {"WHCD"		,ColNoWHCD			,"String"	,"倉庫コード"					,"Key"}
				,{"WHName"		,ColNoWHName		,"String"	,"拠点倉庫名"					,""}
				,{"Post"		,ColNoPost			,"String"	,"拠点倉庫郵便番号"				,""}
				,{"Add01"		,ColNoAdd01		,"String"	,"拠点倉庫住所1"				,""}
				,{"Add02"		,ColNoAdd02		,"String"	,"拠点倉庫住所2"				,""}
				,{"Tel"			,ColNoTel			,"String"	,"拠点倉庫電話"					,""}
				,{"Fax"			,ColNoFax			,"String"	,"拠点倉庫FAX"					,""}
				,{"Mail"		,ColNoMail			,"String"	,"拠点倉庫MAIL"					,""}
				,{"Com01"		,ColNoCom01		,"String"	,"コメント１"					,""}
				,{"Com02"		,ColNoCom02		,"String"	,"コメント２"					,""}
				,{"Com03"		,ColNoCom03		,"String"	,"コメント３"					,""}
				,{"PTMSCD"		,ColNoPTMSCD		,"String"	,"基幹システム連携用事業所CD"	,""}
				,{"EntryDate"	,ColNoEntryDate	,"String"	,"データ登録日時"				,""}
				,{"UpdateDate"	,ColNoUpdateDate	,"String"	,"データ更新日時"				,""}
				,{"EntryUser"	,ColNoEntryUser	,"String"	,"登録者"						,""}
				,{"UpdateUser"	,ColNoUpdateUser	,"String"	,"更新者"						,""}
				};
		
		return RtSettingWhMstRt;
	}
	public static Object[][] WhMstRt(
			ArrayList<String> SearchWHCD,
			ArrayList<String> SearchWHName,
			ArrayList<String> SearchPost,
			ArrayList<String> SearchAdd,
			ArrayList<String> SearchTel,
			ArrayList<String> SearchFax,
			ArrayList<String> SearchMail,
			ArrayList<String> SearchCom,
			ArrayList<String> SearchPTMSCD,
			boolean AllSearch){
		
		SearchWHCD		= B00150ArrayListControl.ArryListStringUniqueList(SearchWHCD);
		SearchWHName	= B00150ArrayListControl.ArryListStringUniqueList(SearchWHName);
		SearchPost		= B00150ArrayListControl.ArryListStringUniqueList(SearchPost);
		SearchAdd		= B00150ArrayListControl.ArryListStringUniqueList(SearchAdd);
		SearchTel		= B00150ArrayListControl.ArryListStringUniqueList(SearchTel);
		SearchFax		= B00150ArrayListControl.ArryListStringUniqueList(SearchFax);
		SearchMail		= B00150ArrayListControl.ArryListStringUniqueList(SearchMail);
		SearchCom		= B00150ArrayListControl.ArryListStringUniqueList(SearchCom);
		SearchPTMSCD	= B00150ArrayListControl.ArryListStringUniqueList(SearchPTMSCD);
		
		Object[][] rt = new Object[0][16];
		boolean SearchKick = false;
		if(AllSearch) {SearchKick = true;}
		String sql = " select "
				+"("+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST.WHCD) as WHCD,\n"				//倉庫コード
				+"("+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST.WHName) as WHName,\n"			//拠点倉庫名
				+"("+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST.Post) as Post,\n"				//拠点倉庫郵便番号
				+"("+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST.Add01) as Add01,\n"				//拠点倉庫住所1
				+"("+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST.Add02) as Add02,\n"				//拠点倉庫住所2
				+"("+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST.Tel) as Tel,\n"					//拠点倉庫電話
				+"("+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST.Fax) as Fax,\n"					//拠点倉庫FAX
				+"("+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST.Mail) as Mail,\n"				//拠点倉庫MAIL
				+"("+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST.Com01) as Com01,\n"				//コメント１
				+"("+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST.Com02) as Com02,\n"				//コメント２
				+"("+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST.Com03) as Com03,\n"				//コメント３
				+"("+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST.PTMSCD) as PTMSCD,\n"			//基幹システム連携用事業所CD
				+"("+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST.EntryDate) as EntryDate,\n"		//登録者コード
				+"("+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST.UpdateDate) as UpdateDate,\n"	//更新者コード
				+"("+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST.EntryUser) as EntryUser,\n"		//データ登録日時
				+"("+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST.UpdateUser) as UpdateUser\n"	//データ更新日時
				+ " from "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST"
				+ " where 1=1";
		
		if(null!=SearchWHCD&&0<SearchWHCD.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchWHCD.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST.WHCD = ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchWHName&&0<SearchWHName.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchWHName.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST.WHName like ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchPost&&0<SearchPost.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchPost.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST.Post like ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchAdd&&0<SearchAdd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchAdd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " CONCAT ("+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST.Add01";
				sql = sql + " , "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST.Add02) like ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchTel&&0<SearchTel.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchTel.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST.Tel like ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchFax&&0<SearchFax.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchFax.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST.Fax like ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchMail&&0<SearchMail.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchMail.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST.Mail like ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchCom&&0<SearchCom.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchCom.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST.Com01 like ?";
				sql = sql + " or "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST.Com02 like ?";
				sql = sql + " or "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST.Com03 like ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchPTMSCD&&0<SearchPTMSCD.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchPTMSCD.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST.PTMSCD = ?";
			}
			sql = sql + ")";
		}
		
		sql = sql + " order by "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST.WHCD";
		
		if(SearchKick) {
			A00010DbConnect.DB_CONN("NYANKO");
			ResultSet rset01 = null;
			PreparedStatement stmt01 = null;
			try {
				stmt01 = A00010DbConnect.conn.prepareStatement(sql);
				int StmtCount = 0;
				
				if(null!=SearchWHCD&&0<SearchWHCD.size()){
					for(int i=0;i<SearchWHCD.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchWHCD.get(i)+"");
					}
				}
				if(null!=SearchWHName&&0<SearchWHName.size()){;
					for(int i=0;i<SearchWHName.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchWHName.get(i)+"%");
					}
				}
				if(null!=SearchPost&&0<SearchPost.size()){
					for(int i=0;i<SearchPost.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, SearchPost.get(i)+"%");
					}
				}
				if(null!=SearchAdd&&0<SearchAdd.size()){
					for(int i=0;i<SearchAdd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchAdd.get(i)+"%");
					}
				}
				if(null!=SearchTel&&0<SearchTel.size()){
					for(int i=0;i<SearchTel.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchTel.get(i)+"%");
					}
				}
				if(null!=SearchFax&&0<SearchFax.size()){
					for(int i=0;i<SearchFax.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchFax.get(i)+"%");
					}
				}
				if(null!=SearchMail&&0<SearchMail.size()){
					for(int i=0;i<SearchMail.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchMail.get(i)+"%");
					}
				}
				
				if(null!=SearchCom&&0<SearchCom.size()){
					for(int i=0;i<SearchCom.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchCom.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchCom.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchCom.get(i)+"%");
					}
				}
				if(null!=SearchPTMSCD&&0<SearchPTMSCD.size()){
					for(int i=0;i<SearchPTMSCD.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, SearchPTMSCD.get(i));
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
					if(null==rset01.getString("WHCD")){			rt[counter][ColNoWHCD]			="";}else{rt[counter][ColNoWHCD]			=rset01.getString("WHCD");}			//倉庫コード
					if(null==rset01.getString("WHName")){		rt[counter][ColNoWHName]		="";}else{rt[counter][ColNoWHName]			=rset01.getString("WHName");}		//拠点倉庫名
					if(null==rset01.getString("Post")){			rt[counter][ColNoPost]			="";}else{rt[counter][ColNoPost]			=rset01.getString("Post");}			//拠点倉庫郵便番号
					if(null==rset01.getString("Add01")){		rt[counter][ColNoAdd01]		="";}else{rt[counter][ColNoAdd01]			=rset01.getString("Add01");}		//拠点倉庫住所1
					if(null==rset01.getString("Add02")){		rt[counter][ColNoAdd02]		="";}else{rt[counter][ColNoAdd02]			=rset01.getString("Add02");}		//拠点倉庫住所2
					if(null==rset01.getString("Tel")){			rt[counter][ColNoTel]			="";}else{rt[counter][ColNoTel]			=rset01.getString("Tel");}			//拠点倉庫電話
					if(null==rset01.getString("Fax")){			rt[counter][ColNoFax]			="";}else{rt[counter][ColNoFax]			=rset01.getString("Fax");}			//拠点倉庫FAX
					if(null==rset01.getString("Mail")){			rt[counter][ColNoMail]			="";}else{rt[counter][ColNoMail]			=rset01.getString("Mail");}			//拠点倉庫MAIL
					if(null==rset01.getString("Com01")){		rt[counter][ColNoCom01]		="";}else{rt[counter][ColNoCom01]			=rset01.getString("Com01");}		//コメント１
					if(null==rset01.getString("Com02")){		rt[counter][ColNoCom02]		="";}else{rt[counter][ColNoCom02]			=rset01.getString("Com02");}		//コメント２
					if(null==rset01.getString("Com03")){		rt[counter][ColNoCom03]		="";}else{rt[counter][ColNoCom03]			=rset01.getString("Com03");}		//コメント３
					if(null==rset01.getString("PTMSCD")){		rt[counter][ColNoPTMSCD]		="";}else{rt[counter][ColNoPTMSCD]			=rset01.getString("PTMSCD");}		//基幹システム連携用事業所CD
					if(null==rset01.getTimestamp("EntryDate")){	rt[counter][ColNoEntryDate]	="";}else{rt[counter][ColNoEntryDate]		=B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("EntryDate"))[1];}			//データ登録日時
					if(null==rset01.getTimestamp("UpdateDate")){rt[counter][ColNoUpdateDate]	="";}else{rt[counter][ColNoUpdateDate]	=B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("UpdateDate"))[1];}		//データ更新日時
					if(null==rset01.getString("EntryUser")){	rt[counter][ColNoEntryUser]	="";}else{rt[counter][ColNoEntryUser]		=rset01.getString("EntryUser");}			//登録者
					if(null==rset01.getString("UpdateUser")){	rt[counter][ColNoUpdateUser]	="";}else{rt[counter][ColNoUpdateUser]	=rset01.getString("UpdateUser");}		//更新者
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
	
	//倉庫コードを自動採番する
	public static String[] NewWhCdGet(int NeedCount) {
		//倉庫マスタ取得
		ArrayList SearchWHCD = new ArrayList();
		ArrayList SearchWHName = new ArrayList();
		ArrayList SearchPost = new ArrayList();
		ArrayList SearchAdd = new ArrayList();
		ArrayList SearchTel = new ArrayList();
		ArrayList SearchFax = new ArrayList();
		ArrayList SearchMail = new ArrayList();
		ArrayList SearchCom = new ArrayList();
		ArrayList SearchPTMSCD = new ArrayList();
		boolean AllSearch = true;
		
		Object[][] WhMstRt = M00001WhMstRt.WhMstRt(
					SearchWHCD, SearchWHName, SearchPost,
					SearchAdd, SearchTel, SearchFax, SearchMail,
					SearchCom, SearchPTMSCD,AllSearch);
		
		int WhNo = 0;
    	
    	for(int i=0;i<WhMstRt.length;i++) {
    		if(4<(""+WhMstRt[i][M00001WhMstRt.ColNoWHCD]).length()&&"ATWH".equals((""+WhMstRt[i][M00001WhMstRt.ColNoWHCD]).substring(0,4))) {
    			String WST = B00020ToolsTextControl.num_only_String(""+WhMstRt[i][M00001WhMstRt.ColNoWHCD]);
    			if("".equals(WST)){WST = "0";}
				int wint = Integer.parseInt(WST);
				if(WhNo<wint) {
					WhNo=wint;
				}
    		}
    	}
    	
    	String[] rt = new String[NeedCount];
    	int MaxCount = 999999999;
    	int wint = MaxCount+1;
    	String SetZero = (""+wint).substring(1,(""+wint).length());
    	for(int i=0;i<NeedCount;i++) {
	    	WhNo = WhNo+1;
    		if(MaxCount<WhNo) {
    			rt[i] = "ATWH"+WhNo;
    		}else {
		    	rt[i] = SetZero+WhNo;
		    	rt[i] = "ATWH"+rt[i].substring(rt[i].length()-SetZero.length(),rt[i].length());
    		}
    	}
    	
    	return rt;
	}
	
}