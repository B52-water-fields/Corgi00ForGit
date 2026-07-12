import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class M100_ClMstRt{
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
	
	Object[][] ClMstRt = M100_ClMstRt.ClMstRt(
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
		
	String Getcl_cd			=(String)ClMstRt[i][M100_ClMstRt.Colcl_cd];			//荷主CD
	String GetClGpCD		=(String)ClMstRt[i][M100_ClMstRt.ColClGpCD];		//荷主グループCD
	String GetClGpName		=(String)ClMstRt[i][M100_ClMstRt.ColClGpName];		//グループ表記名
	String GetWHCD			=(String)ClMstRt[i][M100_ClMstRt.ColWHCD];			//担当倉庫
	String GetWHName		=(String)ClMstRt[i][M100_ClMstRt.ColWHName];		//担当倉庫名
	String GetCLName01		=(String)ClMstRt[i][M100_ClMstRt.ColCLName01];		//荷主表記名
	String GetCLName02		=(String)ClMstRt[i][M100_ClMstRt.ColCLName02];		//荷主正式名
	String GetCLName03		=(String)ClMstRt[i][M100_ClMstRt.ColCLName03];		//荷主略名
	String GetPost			=(String)ClMstRt[i][M100_ClMstRt.ColPost];			//郵便番号
	String GetAdd01			=(String)ClMstRt[i][M100_ClMstRt.ColAdd01];			//住所1
	String GetAdd02			=(String)ClMstRt[i][M100_ClMstRt.ColAdd02];			//住所2
	String GetAdd03			=(String)ClMstRt[i][M100_ClMstRt.ColAdd03];			//住所3
	String GetTel			=(String)ClMstRt[i][M100_ClMstRt.ColTel];			//電話番号
	String GetFax			=(String)ClMstRt[i][M100_ClMstRt.ColFax];			//FAX
	String GetMail			=(String)ClMstRt[i][M100_ClMstRt.ColMail];			//メールアドレス
	String GetCom01			=(String)ClMstRt[i][M100_ClMstRt.ColCom01];			//コメント1
	String GetCom02			=(String)ClMstRt[i][M100_ClMstRt.ColCom02];			//コメント2
	String GetCom03			=(String)ClMstRt[i][M100_ClMstRt.ColCom03];			//コメント3
	int GetShimeDate		=(int)ClMstRt[i][M100_ClMstRt.ColShimeDate];		//締日
	int GetShimeBasis		=(int)ClMstRt[i][M100_ClMstRt.ColShimeBasis];		//請求基準
	String GetEntryDate		=(String)ClMstRt[i][M100_ClMstRt.ColEntryDate];		//データ登録日時
	String GetUpdateDate	=(String)ClMstRt[i][M100_ClMstRt.ColUpdateDate];	//データ更新日時
	String GetEntryUser		=(String)ClMstRt[i][M100_ClMstRt.ColEntryUser];		//登録者コード
	String GetUpdateUser	=(String)ClMstRt[i][M100_ClMstRt.ColUpdateUser];	//更新者コード
	String GetPTMSCD		=(String)ClMstRt[i][M100_ClMstRt.ColPTMSCD];		//基幹システム荷主コード
	*/
	
	//戻り値カラム
	
	static final  int ColWHName		= (int)  0;	//担当倉庫名
	static final  int ColClGpName		= (int)  1;	//グループ名1
	static final  int ColCLName01		= (int)  2;	//荷主表記名
	static final  int ColCLName02		= (int)  3;	//荷主正式名
	static final  int ColCLName03		= (int)  4;	//荷主略名
	static final  int Colcl_cd			= (int)  5;	//荷主CD
	static final  int ColClGpCD		= (int)  6;	//荷主グループCD
	static final  int ColWHCD			= (int)  7;	//担当倉庫
	static final  int ColPost			= (int)  8;	//郵便番号
	static final  int ColAdd01			= (int)  9;	//住所1
	static final  int ColAdd02			= (int) 10;	//住所2
	static final  int ColAdd03			= (int) 11;	//住所3
	static final  int ColTel			= (int) 12;	//電話番号
	static final  int ColFax			= (int) 13;	//FAX
	static final  int ColMail			= (int) 14;	//メールアドレス
	static final  int ColCom01			= (int) 15;	//コメント1
	static final  int ColCom02			= (int) 16;	//コメント2
	static final  int ColCom03			= (int) 17;	//コメント3
	static final  int ColShimeDate	= (int) 18;	//締日
	static final  int ColShimeBasis	= (int) 19;	//請求基準
	static final  int ColEntryDate	= (int) 20;	//データ登録日時
	static final  int ColUpdateDate	= (int) 21;	//データ更新日時
	static final  int ColEntryUser	= (int) 22;	//登録者コード
	static final  int ColUpdateUser	= (int) 23;	//更新者コード
	static final  int ColPTMSCD		= (int) 24;	//基幹システム荷主コード
	
	public static Object[][] RtClMstRt(){
		Object[][] RtSettingClMstRt = {
				 {"cl_cd"		,Colcl_cd			,"String"	,"荷主CD"			,"Key"}
				,{"ClGpCD"		,ColClGpCD			,"String"	,"荷主グループCD"	,""}
				,{"ClGpName"	,ColClGpName		,"String"	,"グループ表記名"	,""}
				,{"WHCD"		,ColWHCD			,"String"	,"担当倉庫"			,""}
				,{"WHName"		,ColWHName			,"String"	,"担当倉庫名"		,""}
				,{"CLName01"	,ColCLName01		,"String"	,"荷主表記名"		,""}
				,{"CLName02"	,ColCLName02		,"String"	,"荷主正式名"		,""}
				,{"CLName03"	,ColCLName03		,"String"	,"荷主略名"			,""}
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
				,{"ShimeDate"	,ColShimeDate		,"int"		,"締日"				,""}
				,{"ShimeBasis"	,ColShimeBasis	,"int"		,"請求基準"			,""}
				,{"EntryDate"	,ColEntryDate		,"DateTime"	,"データ登録日時"	,""}
				,{"UpdateDate"	,ColUpdateDate	,"DateTime"	,"データ更新日時"	,""}
				,{"EntryUser"	,ColEntryUser		,"String"	,"登録者コード"		,""}
				,{"UpdateUser"	,ColUpdateUser	,"String"	,"更新者コード"		,""}
				,{"PTMSCD"		,ColPTMSCD			,"String"	,"基幹Sys荷主CD"	,""}
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
		SearchClGpCD	= B100_ArrayListControl.ArryListStringUniqueList(SearchClGpCD);
		SearchCLCD		= B100_ArrayListControl.ArryListStringUniqueList(SearchCLCD);
		SearchCLName	= B100_ArrayListControl.ArryListStringUniqueList(SearchCLName);
		SearchPost		= B100_ArrayListControl.ArryListStringUniqueList(SearchPost);
		searchAdd		= B100_ArrayListControl.ArryListStringUniqueList(searchAdd);
		SearchTel		= B100_ArrayListControl.ArryListStringUniqueList(SearchTel);
		SearchFax		= B100_ArrayListControl.ArryListStringUniqueList(SearchFax);
		SearchMail		= B100_ArrayListControl.ArryListStringUniqueList(SearchMail);
		SearchCom		= B100_ArrayListControl.ArryListStringUniqueList(SearchCom);
		SearchWHCD		= B100_ArrayListControl.ArryListStringUniqueList(SearchWHCD);
		
		
		Object[][] rt = new Object[0][RtClMstRt().length];
		boolean SearchKick = false;
		if(AllSearch) {SearchKick = true;}
		String sql =  "select "
		+"(KM0030_CLIENTMST.cl_cd) as cl_cd,"				//荷主CD
		+"(KM0030_CLIENTMST.ClGpCD) as ClGpCD,"				//荷主グループCD
		+"(KM0031_CLIENT_GROUP.ClGpName01) as ClGpName,"	//グループ名1
		+"(KM0030_CLIENTMST.WHCD) as WHCD,"					//担当倉庫
		+"(KM0010_WHMST.WHName) as WHName,"					//担当倉庫名
		+"(KM0030_CLIENTMST.CLName01) as CLName01,"			//荷主表記名
		+"(KM0030_CLIENTMST.CLName02) as CLName02,"			//荷主正式名
		+"(KM0030_CLIENTMST.CLName03) as CLName03,"			//荷主略名
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
		+" from "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0030_CLIENTMST"
		+ " left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP "
		+ " on("+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0030_CLIENTMST.ClGpCD = "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.ClGpCD)"
		+ " left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST"
		+ " on("+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0030_CLIENTMST.WHCD = "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST.WHCD)"
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
			A100_DbConnect.DB_CONN("NYANKO");
			
			ResultSet rset01 = null;
			PreparedStatement stmt01 = null;
			try {
				stmt01 = A100_DbConnect.conn.prepareStatement(sql);
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
				
				rt = B100_RtObjectCreate.B100_RtObjectCreate(rset01,RtClMstRt());
				
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
			A100_DbConnect.close();
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
    		if(4<(""+ClMstRt[i][M100_ClMstRt.Colcl_cd]).length()&&"ATCL".equals((""+ClMstRt[i][M100_ClMstRt.Colcl_cd]).substring(0,4))) {
    			String WST = B100_TextControl.num_only_String(""+ClMstRt[i][M100_ClMstRt.Colcl_cd]);
    			if("".equals(WST)){WST = "0";}
				int wint = Integer.parseInt(WST);
				if(ClientNo<wint) {
					ClientNo=wint;
				}
    		}
    	}

    	String[] rt = new String[NeedCount];
    	int MaxCount = 999999999;
    	int wint = MaxCount+1;
    	String SetZero = (""+wint).substring(1,(""+wint).length());
    	for(int i=0;i<NeedCount;i++) {
    		ClientNo = ClientNo+1;
	    	if(MaxCount<ClientNo) {
    			rt[i] = "ATCL"+ClientNo;
    		}else {
		    	rt[i] = SetZero+ClientNo;
		    	rt[i] = "ATCL"+rt[i].substring(rt[i].length()-SetZero.length(),rt[i].length());
    		}
    	}
    	
    	return rt;
	}
}