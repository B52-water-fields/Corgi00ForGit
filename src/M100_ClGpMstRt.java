import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class M100_ClGpMstRt{
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
	
	Object[][] ClGpMstRt = M100_ClGpMstRt.ClGpMstRt(
			SearchClGpCD,
			SearchCLGpName,
			SearchPost,
			SearchAdd,
			SearchTel,
			SearchFax,
			SearchMail,
			SearchCom,
			AllSearch);
			
	String GetClGpCD		= (String)ClGpMstRt[i][M100_ClGpMstRt.ColClGpCD]		//荷主グループCD
	String GetCLGpName01	= (String)ClGpMstRt[i][M100_ClGpMstRt.ColCLGpName01]	//荷主グループ表記名
	String GetCLGpName02	= (String)ClGpMstRt[i][M100_ClGpMstRt.ColCLGpName02]	//荷主グループ正式名
	String GetCLGpName03	= (String)ClGpMstRt[i][M100_ClGpMstRt.ColCLGpName03]	//荷主グループ略名
	String GetPost			= (String)ClGpMstRt[i][M100_ClGpMstRt.ColPost]			//郵便番号
	String GetAdd01			= (String)ClGpMstRt[i][M100_ClGpMstRt.ColAdd01]			//住所1
	String GetAdd02			= (String)ClGpMstRt[i][M100_ClGpMstRt.ColAdd02]			//住所2
	String GetAdd03			= (String)ClGpMstRt[i][M100_ClGpMstRt.ColAdd03]			//住所3
	String GetTel			= (String)ClGpMstRt[i][M100_ClGpMstRt.ColTel]			//電話番号
	String GetFax			= (String)ClGpMstRt[i][M100_ClGpMstRt.ColFax]			//FAX
	String GetMail			= (String)ClGpMstRt[i][M100_ClGpMstRt.ColMail]			//メールアドレス
	String GetCom01			= (String)ClGpMstRt[i][M100_ClGpMstRt.ColCom01]			//コメント1
	String GetCom02			= (String)ClGpMstRt[i][M100_ClGpMstRt.ColCom02]			//コメント2
	String GetCom03			= (String)ClGpMstRt[i][M100_ClGpMstRt.ColCom03]			//コメント3
	String GetEntryDate		= (String)ClGpMstRt[i][M100_ClGpMstRt.ColEntryDate]		//データ登録日時
	String GetUpdateDate	= (String)ClGpMstRt[i][M100_ClGpMstRt.ColUpdateDate]	//データ更新日時
	String GetEntryUser		= (String)ClGpMstRt[i][M100_ClGpMstRt.ColEntryUser]		//登録者コード
	String GetUpdateUser	= (String)ClGpMstRt[i][M100_ClGpMstRt.ColUpdateUser]	//更新者コード
	String GetPassWord		= (String)ClGpMstRt[i][M100_ClGpMstRt.ColPassWord]		//パスワード
	*/
	//戻り値カラム
	static final  int ColClGpCD		= (int) 0;	//荷主グループCD
	static final  int ColCLGpName01	= (int) 1;	//荷主グループ表記名
	static final  int ColCLGpName02	= (int) 2;	//荷主グループ正式名
	static final  int ColCLGpName03	= (int) 3;	//荷主グループ略名
	static final  int ColPost			= (int) 4;	//郵便番号
	static final  int ColAdd01			= (int) 5;	//住所1
	static final  int ColAdd02			= (int) 6;	//住所2
	static final  int ColAdd03			= (int) 7;	//住所3
	static final  int ColTel			= (int) 8;	//電話番号
	static final  int ColFax			= (int) 9;	//FAX"}
	static final  int ColMail			= (int)10;	//メールアドレス
	static final  int ColCom01			= (int)11;	//コメント1
	static final  int ColCom02			= (int)12;	//コメント2
	static final  int ColCom03			= (int)13;	//コメント3
	static final  int ColEntryDate	= (int)14;	//データ登録日時
	static final  int ColUpdateDate	= (int)15;	//データ更新日時
	static final  int ColEntryUser	= (int)16;	//"登録者コード
	static final  int ColUpdateUser	= (int)17;	//更新者コード
	static final  int ColPassWord		= (int)18;	//パスワード
	
	public static Object[][] RtSettingClGpMstRt(){
		Object[][] RtSettingClGpMstRt = {
				 {"ClGpCD"		,ColClGpCD			,"String"	,"荷主グループCD"		,"Key"}
				,{"CLGpName01"	,ColCLGpName01	,"String"	,"荷主グループ表記名"	,""}
				,{"CLGpName02"	,ColCLGpName02	,"String"	,"荷主グループ正式名"	,""}
				,{"CLGpName03"	,ColCLGpName03	,"String"	,"荷主グループ略名"		,""}
				,{"Post"		,ColPost			,"String"	,"郵便番号"				,""}
				,{"Add01"		,ColAdd01			,"String"	,"住所1"				,""}
				,{"Add02"		,ColAdd02			,"String"	,"住所2"				,""}
				,{"Add03"		,ColAdd03			,"String"	,"住所3"				,""}
				,{"Tel"			,ColTel			,"String"	,"電話番号"				,""}
				,{"Fax"			,ColFax			,"String"	,"FAX"					,""}
				,{"Mail"		,ColMail			,"String"	,"メールアドレス"		,""}
				,{"Com01"		,ColCom01			,"String"	,"コメント1"			,""}
				,{"Com02"		,ColCom02			,"String"	,"コメント2"			,""}
				,{"Com03"		,ColCom03			,"String"	,"コメント3"			,""}
				,{"EntryDate"	,ColEntryDate		,"String"	,"データ登録日時"		,""}
				,{"UpdateDate"	,ColUpdateDate	,"String"	,"データ更新日時"		,""}
				,{"EntryUser"	,ColEntryUser		,"String"	,"登録者コード"			,""}
				,{"UpdateUser"	,ColUpdateUser	,"String"	,"更新者コード"			,""}
				,{"PassWord"	,ColPassWord		,"String"	,"パスワード"			,""}
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
		
		SearchClGpCD	= B100_ArrayListControl.ArryListStringUniqueList(SearchClGpCD);
		SearchCLGpName	= B100_ArrayListControl.ArryListStringUniqueList(SearchCLGpName);
		SearchPost		= B100_ArrayListControl.ArryListStringUniqueList(SearchPost);
		SearchAdd		= B100_ArrayListControl.ArryListStringUniqueList(SearchAdd);
		SearchTel		= B100_ArrayListControl.ArryListStringUniqueList(SearchTel);
		SearchFax		= B100_ArrayListControl.ArryListStringUniqueList(SearchFax);
		SearchMail		= B100_ArrayListControl.ArryListStringUniqueList(SearchMail);
		SearchCom		= B100_ArrayListControl.ArryListStringUniqueList(SearchCom);
		
		boolean SearchKick = false;
		if(AllSearch) {SearchKick = true;}
		Object[][] rt = new Object[0][RtSettingClGpMstRt().length];
		
		String sql = "select "
				+"("+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.ClGpCD) as ClGpCD,\n"			//荷主グループCD
				+"("+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.CLGpName01) as CLGpName01,\n"	//荷主表記名
				+"("+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.CLGpName02) as CLGpName02,\n"	//荷主正式名
				+"("+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.CLGpName03) as CLGpName03,\n"	//荷主略名
				+"("+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.Post) as Post,\n"				//郵便番号
				+"("+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.Add01) as Add01,\n"				//住所1
				+"("+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.Add02) as Add02,\n"				//住所2
				+"("+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.Add03) as Add03,\n"				//住所3
				+"("+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.Tel) as Tel,\n"					//電話番号
				+"("+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.Fax) as Fax,\n"					//FAX
				+"("+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.Mail) as Mail,\n"				//メールアドレス
				+"("+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.Com01) as Com01,\n"				//コメント1
				+"("+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.Com02) as Com02,\n"				//コメント2
				+"("+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.Com03) as Com03,\n"				//コメント3
				+"("+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.EntryDate) as EntryDate,\n"		//データ登録日時
				+"("+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.UpdateDate) as UpdateDate,\n"	//データ更新日時
				+"("+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.EntryUser) as EntryUser,\n"		//登録者コード
				+"("+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.UpdateUser) as UpdateUser,\n"	//更新者コード
				+"("+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.PassWord) as PassWord \n"		//パスワード
				+" from "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP "
				+" where 1=1 ";

		if(null!=SearchClGpCD && 0<SearchClGpCD.size()) {
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchClGpCD.size();i++) {
				if(0<i) {sql = sql+" or ";}
				sql = sql + " "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.ClGpCD = ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchCLGpName && 0<SearchCLGpName.size()) {
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchCLGpName.size();i++) {
				if(0<i) {sql = sql+" or ";}
				sql = sql + " "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.CLGpName01 like ?";
				sql = sql + " or "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.CLGpName02 like ?";
				sql = sql + " or "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.CLGpName03 like ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchPost && 0<SearchPost.size()) {
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchPost.size();i++) {
				if(0<i) {sql = sql+" or ";}
				sql = sql + " "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.Post like ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchAdd && 0<SearchAdd.size()) {
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchAdd.size();i++) {
				if(0<i) {sql = sql+" or ";}
				sql = sql + " CONCAT ("+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.Add01";
				sql = sql + " , "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.Add02";
				sql = sql + " , "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.Add03) like ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchTel && 0<SearchTel.size()) {
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchTel.size();i++) {
				if(0<i) {sql = sql+" or ";}
				sql = sql + " "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.Tel like ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchFax && 0<SearchFax.size()) {
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchFax.size();i++) {
				if(0<i) {sql = sql+" or ";}
				sql = sql + " "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.Fax like ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchMail && 0<SearchMail.size()) {
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchMail.size();i++) {
				if(0<i) {sql = sql+" or ";}
				sql = sql + " "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.Mail like ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchCom && 0<SearchCom.size()) {
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchCom.size();i++) {
				if(0<i) {sql = sql+" or ";}
				sql = sql + " "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.Com01 like ?";
				sql = sql + " or "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.Com02 like ?";
				sql = sql + " or "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.Com03 like ?";
			}
			sql = sql + ")";
		}
		sql = sql + "order by "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP.ClGpCD";

		//System.out.println(sql);
		if(SearchKick) {
			A100_DbConnect.DB_CONN("NYANKO");
			
			ResultSet rset01 = null;
			PreparedStatement stmt01 = null;
			try {
				stmt01 = A100_DbConnect.conn.prepareStatement(sql);
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
				
				rt = B100_RtObjectCreate.B100_RtObjectCreate(rset01,RtSettingClGpMstRt());
				
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
		
		Object[][] ClGpMstRt = M100_ClGpMstRt.ClGpMstRt(
						SearchClGpCD,SearchCLGpName,SearchPost,
						SearchAdd,SearchTel,SearchFax,SearchMail,SearchCom,AllSearch);
		
		int ClGpNo = 0;
    	
    	for(int i=0;i<ClGpMstRt.length;i++) {
    		if(4<(""+ClGpMstRt[i][M100_ClGpMstRt.ColClGpCD]).length()&&"ATGR".equals((""+ClGpMstRt[i][M100_ClGpMstRt.ColClGpCD]).substring(0,4))) {
    			String WST = B100_TextControl.num_only_String(""+ClGpMstRt[i][M100_ClGpMstRt.ColClGpCD]);
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