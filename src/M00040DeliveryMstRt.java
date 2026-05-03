import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class M00040DeliveryMstRt{
	/*
	コピペ用
	ArrayList<String> SearchDECD 			= new ArrayList<String>();
	ArrayList<String> SearchDepartmentCd 	= new ArrayList<String>();
	ArrayList<String> SearchDEName 			= new ArrayList<String>();
	ArrayList<String> SearchPost 			= new ArrayList<String>();
	ArrayList<String> SearchAdd 			= new ArrayList<String>();
	ArrayList<String> SearchTel 			= new ArrayList<String>();
	ArrayList<String> SearchFax 			= new ArrayList<String>();
	ArrayList<String> SearchMail 			= new ArrayList<String>();
	ArrayList<String> SearchCom 			= new ArrayList<String>();
	ArrayList<String> SearchPrefecturesCd 	= new ArrayList<String>();
	ArrayList<String> SearchMunicipalityCd 	= new ArrayList<String>();
	ArrayList<String> SearchDelFg 			= new ArrayList<String>();
	boolean SearcNotJis = true;
	boolean SearchTelExactMatch = false;
	boolean AllSearch = false;
	
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
		SearcNotJis,
		SearchTelExactMatch,
		AllSearch
		);
		
	String GetDECD				= DeliveryMstRt[i][M00040DeliveryMstRt.ColDECD];			//届先コード
	String GetDepartmentCd		= DeliveryMstRt[i][M00040DeliveryMstRt.ColDepartmentCd];	//部署CD
	String GetDEName01			= DeliveryMstRt[i][M00040DeliveryMstRt.ColDEName01];		//届先名1
	String GetDEName02			= DeliveryMstRt[i][M00040DeliveryMstRt.ColDEName02];		//届先名2
	String GetDEName03			= DeliveryMstRt[i][M00040DeliveryMstRt.ColDEName03];		//届先名3
	String GetPost				= DeliveryMstRt[i][M00040DeliveryMstRt.ColPost];			//届先郵便
	String GetAdd01				= DeliveryMstRt[i][M00040DeliveryMstRt.ColAdd01];			//届先住所1
	String GetAdd02				= DeliveryMstRt[i][M00040DeliveryMstRt.ColAdd02];			//届先住所2
	String GetAdd03				= DeliveryMstRt[i][M00040DeliveryMstRt.ColAdd03];			//届先住所3
	String GetTel				= DeliveryMstRt[i][M00040DeliveryMstRt.ColTel];				//届先電話
	String GetFax				= DeliveryMstRt[i][M00040DeliveryMstRt.ColFax];				//届先FAX
	String GetMail				= DeliveryMstRt[i][M00040DeliveryMstRt.ColMail];			//届先MAIL
	String GetCom01				= DeliveryMstRt[i][M00040DeliveryMstRt.ColCom01];			//コメント1
	String GetCom02				= DeliveryMstRt[i][M00040DeliveryMstRt.ColCom02];			//コメント2
	String GetCom03				= DeliveryMstRt[i][M00040DeliveryMstRt.ColCom03];			//コメント3
	String GetPrefecturesCd		= DeliveryMstRt[i][M00040DeliveryMstRt.ColPrefecturesCd];	//JIS県CD2桁
	String GetMunicipalityCd	= DeliveryMstRt[i][M00040DeliveryMstRt.ColMunicipalityCd];	//JIS市区町村CD5桁
	String GetPTMSCD			= DeliveryMstRt[i][M00040DeliveryMstRt.ColPTMSCD];			//基幹システム発着地コード
	String GetEntryDate			= DeliveryMstRt[i][M00040DeliveryMstRt.ColEntryDate];		//データ登録日時
	String GetUpdateDate		= DeliveryMstRt[i][M00040DeliveryMstRt.ColUpdateDate];		//データ更新日時
	String GetEntryUser			= DeliveryMstRt[i][M00040DeliveryMstRt.ColEntryUser];		//登録者コード
	String GetUpdateUser		= DeliveryMstRt[i][M00040DeliveryMstRt.ColUpdateUser];		//更新者コード
	String GetFirstClient		= DeliveryMstRt[i][M00040DeliveryMstRt.ColFirstClient];		//登録した荷主CD
	String GetLastClient		= DeliveryMstRt[i][M00040DeliveryMstRt.ColLastClient];		//更新した荷主CD
	String GetDelFg				= DeliveryMstRt[i][M00040DeliveryMstRt.ColDelFg];			//削除区分
	String GetFirstClientName	= DeliveryMstRt[i][M00040DeliveryMstRt.ColFirstClientName];	//登録した荷主名
	String GetLastClientName	= DeliveryMstRt[i][M00040DeliveryMstRt.ColLastClientName];	//登録した荷主名
		
	*/
	
	//戻り値カラム
	static int ColDECD				= (int) 0;	//届先コード
	static int ColDepartmentCd	= (int) 1;	//部署CD
	static int ColDEName01			= (int) 2;	//届先名1
	static int ColDEName02			= (int) 3;	//届先名2
	static int ColDEName03			= (int) 4;	//届先名3
	static int ColPost				= (int) 5;	//届先郵便
	static int ColAdd01			= (int) 6;	//届先住所1
	static int ColAdd02			= (int) 7;	//届先住所2
	static int ColAdd03			= (int) 8;	//届先住所3
	static int ColTel				= (int) 9;	//届先電話
	static int ColFax				= (int)10;	//届先FAX
	static int ColMail				= (int)11;	//届先MAIL
	static int ColCom01			= (int)12;	//コメント1
	static int ColCom02			= (int)13;	//コメント2
	static int ColCom03			= (int)14;	//コメント3
	static int ColPrefecturesCd	= (int)15;	//JIS県CD2桁
	static int ColMunicipalityCd	= (int)16;	//JIS市区町村CD5桁
	static int ColPTMSCD			= (int)17;	//基幹システム発着地コード
	static int ColEntryDate		= (int)18;	//データ登録日時
	static int ColUpdateDate		= (int)19;	//データ更新日時
	static int ColEntryUser		= (int)20;	//登録者コード
	static int ColUpdateUser		= (int)21;	//更新者コード
	static int ColFirstClient		= (int)22;	//登録した荷主CD
	static int ColLastClient		= (int)23;	//更新した荷主CD
	static int ColDelFg			= (int)24;	//削除区分
	static int ColFirstClientName= (int)25;	//登録した荷主名
	static int ColLastClientName	= (int)26;	//登録した荷主名
	
	public static Object[][] RtSettingDeliveryMstRt(){
		Object[][] RtSettingDeliveryMstRt = {
				 {"DECD"			,ColDECD				,"String"	,"届先コード"}
				,{"DepartmentCd"	,ColDepartmentCd		,"String"	,"部署CD"}
				,{"DEName01"		,ColDEName01			,"String"	,"届先名1"}
				,{"DEName02"		,ColDEName02			,"String"	,"届先名2"}
				,{"DEName03"		,ColDEName03			,"String"	,"届先名3"}
				,{"Post"			,ColPost				,"String"	,"届先郵便"}
				,{"Add01"			,ColAdd01				,"String"	,"届先住所1"}
				,{"Add02"			,ColAdd02				,"String"	,"届先住所2"}
				,{"Add03"			,ColAdd03				,"String"	,"届先住所3"}
				,{"Tel"				,ColTel				,"String"	,"届先電話"}
				,{"Fax"				,ColFax				,"String"	,"届先FAX"}
				,{"Mail"			,ColMail				,"String"	,"届先MAIL"}
				,{"Com01"			,ColCom01				,"String"	,"コメント1"}
				,{"Com02"			,ColCom02				,"String"	,"コメント2"}
				,{"Com03"			,ColCom03				,"String"	,"コメント3"}
				,{"PrefecturesCd"	,ColPrefecturesCd		,"String"	,"JIS県CD2桁"}
				,{"MunicipalityCd"	,ColMunicipalityCd	,"String"	,"JIS市区町村CD5桁"}
				,{"PTMSCD"			,ColPTMSCD				,"String"	,"基幹システム発着地コード"}
				,{"EntryDate"		,ColEntryDate			,"String"	,"データ登録日時"}
				,{"UpdateDate"		,ColUpdateDate		,"String"	,"データ更新日時"}
				,{"EntryUser"		,ColEntryUser			,"String"	,"登録者コード"}
				,{"UpdateUser"		,ColUpdateUser		,"String"	,"更新者コード"}
				,{"FirstClient"		,ColFirstClient		,"String"	,"登録した荷主CD"}
				,{"LastClient"		,ColLastClient		,"String"	,"更新した荷主CD"}
				,{"DelFg"			,ColDelFg				,"int"		,"削除区分"}
				,{"FirstClientName"	,ColFirstClientName	,"String"	,"登録した荷主名"}
				,{"LastClientName"	,ColLastClientName	,"String"	,"登録した荷主名"}
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
			boolean SearcNotJis,					//検索条件JIS由来除く
			boolean SearchTelExactMatch,			//電話番号完全一致
			boolean AllSearch
			){
		
		SearchDECD				= B00150ArrayListControl.ArryListStringUniqueList(SearchDECD);
		SearchDepartmentCd		= B00150ArrayListControl.ArryListStringUniqueList(SearchDepartmentCd);
		SearchDEName			= B00150ArrayListControl.ArryListStringUniqueList(SearchDEName);
		SearchPost				= B00150ArrayListControl.ArryListStringUniqueList(SearchPost);
		SearchAdd				= B00150ArrayListControl.ArryListStringUniqueList(SearchAdd);
		SearchTel				= B00150ArrayListControl.ArryListStringUniqueList(SearchTel);
		SearchFax				= B00150ArrayListControl.ArryListStringUniqueList(SearchFax);
		SearchMail				= B00150ArrayListControl.ArryListStringUniqueList(SearchMail);
		SearchCom				= B00150ArrayListControl.ArryListStringUniqueList(SearchCom);
		SearchPrefecturesCd		= B00150ArrayListControl.ArryListStringUniqueList(SearchPrefecturesCd);
		SearchMunicipalityCd	= B00150ArrayListControl.ArryListStringUniqueList(SearchMunicipalityCd);
		SearchDelFg				= B00150ArrayListControl.ArryListStringUniqueList(SearchDelFg);
		
		Object[][] rt = new Object[0][27];
		boolean SearchKick = false;
		
		if(AllSearch) {SearchKick=true;}
		
		String sql = " select "
			+"(KM0040_DELIVERYMST.DECD) as DECD,\n"						//届先コード
			+"(KM0040_DELIVERYMST.DepartmentCd) as DepartmentCd,\n"		//部署CD
			+"(KM0040_DELIVERYMST.DEName01) as DEName01,\n"				//届先名1
			+"(KM0040_DELIVERYMST.DEName02) as DEName02,\n"				//届先名2
			+"(KM0040_DELIVERYMST.DEName03) as DEName03,\n"				//届先名3
			+"(KM0040_DELIVERYMST.Post) as Post,\n"						//届先郵便
			+"(KM0040_DELIVERYMST.Add01) as Add01,\n"					//届先住所1
			+"(KM0040_DELIVERYMST.Add02) as Add02,\n"					//届先住所2
			+"(KM0040_DELIVERYMST.Add03) as Add03,\n"					//届先住所3
			+"(KM0040_DELIVERYMST.Tel) as Tel,\n"						//届先電話
			+"(KM0040_DELIVERYMST.Fax) as Fax,\n"						//届先FAX
			+"(KM0040_DELIVERYMST.Mail) as Mail,\n"						//届先MAIL
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
		
		if(SearcNotJis) {sql = sql+ " and KM0040_DELIVERYMST.DepartmentCd != 'JIS'\n";}
		
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
						if(SearchTelExactMatch){
							stmt01.setString(StmtCount, ""+SearchTel.get(i)+"");
						}else {
							stmt01.setString(StmtCount, "%"+SearchTel.get(i)+"%");
						}
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
					if(null==rset01.getString("DECD")){				rt[counter][ColDECD] 				= "";}else{rt[counter][ColDECD] 				= rset01.getString("DECD");}			//届先コード
					if(null==rset01.getString("DepartmentCd")){		rt[counter][ColDepartmentCd] 		= "";}else{rt[counter][ColDepartmentCd] 		= rset01.getString("DepartmentCd");}	//部署CD
					if(null==rset01.getString("DEName01")){			rt[counter][ColDEName01] 			= "";}else{rt[counter][ColDEName01] 			= rset01.getString("DEName01");}		//届先名1
					if(null==rset01.getString("DEName02")){			rt[counter][ColDEName02] 			= "";}else{rt[counter][ColDEName02] 			= rset01.getString("DEName02");}		//届先名2
					if(null==rset01.getString("DEName03")){			rt[counter][ColDEName03] 			= "";}else{rt[counter][ColDEName03] 			= rset01.getString("DEName03");}		//届先名3
					if(null==rset01.getString("Post")){				rt[counter][ColPost] 				= "";}else{rt[counter][ColPost] 				= rset01.getString("Post");	}			//届先郵便
					if(null==rset01.getString("Add01")){			rt[counter][ColAdd01] 				= "";}else{rt[counter][ColAdd01] 				= rset01.getString("Add01");}			//届先住所1
					if(null==rset01.getString("Add02")){			rt[counter][ColAdd02] 				= "";}else{rt[counter][ColAdd02] 				= rset01.getString("Add02");}			//届先住所2
					if(null==rset01.getString("Add03")){			rt[counter][ColAdd03] 				= "";}else{rt[counter][ColAdd03] 				= rset01.getString("Add03");}			//届先住所3
					if(null==rset01.getString("Tel")){				rt[counter][ColTel] 				= "";}else{rt[counter][ColTel] 				= rset01.getString("Tel");}				//届先電話
					if(null==rset01.getString("Fax")){				rt[counter][ColFax] 				= "";}else{rt[counter][ColFax] 				= rset01.getString("Fax");}				//届先FAX
					if(null==rset01.getString("Mail")){				rt[counter][ColMail] 				= "";}else{rt[counter][ColMail] 				= rset01.getString("Mail");}			//届先MAIL
					if(null==rset01.getString("Com01")){			rt[counter][ColCom01] 				= "";}else{rt[counter][ColCom01] 				= rset01.getString("Com01");}			//コメント1
					if(null==rset01.getString("Com02")){			rt[counter][ColCom02] 				= "";}else{rt[counter][ColCom02] 				= rset01.getString("Com02");}			//コメント2
					if(null==rset01.getString("Com03")){			rt[counter][ColCom03] 				= "";}else{rt[counter][ColCom03] 				= rset01.getString("Com03");}			//コメント3
					if(null==rset01.getString("PrefecturesCd")){	rt[counter][ColPrefecturesCd] 	= "";}else{rt[counter][ColPrefecturesCd] 		= rset01.getString("PrefecturesCd");}	//JIS県CD2桁
					if(null==rset01.getString("MunicipalityCd")){	rt[counter][ColMunicipalityCd] 	= "";}else{rt[counter][ColMunicipalityCd] 	= rset01.getString("MunicipalityCd");}	//JIS市区町村CD5桁
					if(null==rset01.getString("PTMSCD")){			rt[counter][ColPTMSCD] 			= "";}else{rt[counter][ColPTMSCD] 				= rset01.getString("PTMSCD");}			//基幹システム発着地コード
					if(null==rset01.getTimestamp("EntryDate")){		rt[counter][ColEntryDate] 		= "";}else{rt[counter][ColEntryDate] 			= B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("EntryDate"))[1];}		//データ登録日時
					if(null==rset01.getTimestamp("UpdateDate")){	rt[counter][ColUpdateDate] 		= "";}else{rt[counter][ColUpdateDate] 		= B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("UpdateDate"))[1];}	//データ更新日時
					if(null==rset01.getString("EntryUser")){		rt[counter][ColEntryUser] 		= "";}else{rt[counter][ColEntryUser] 			= rset01.getString("EntryUser");}		//登録者コード
					if(null==rset01.getString("UpdateUser")){		rt[counter][ColUpdateUser] 		= "";}else{rt[counter][ColUpdateUser] 		= rset01.getString("UpdateUser");}		//更新者コード
					if(null==rset01.getString("FirstClient")){		rt[counter][ColFirstClient] 		= "";}else{rt[counter][ColFirstClient] 		= rset01.getString("FirstClient");}		//登録した荷主CD
					if(null==rset01.getString("LastClient")){		rt[counter][ColLastClient] 		= "";}else{rt[counter][ColLastClient] 		= rset01.getString("LastClient");}		//更新した荷主CD
					rt[counter][ColDelFg] = rset01.getInt("DelFg");				//削除区分
					if(null==rset01.getString("FirstClientName")){	rt[counter][ColFirstClientName] 	= "";}else{rt[counter][ColFirstClientName] 	= rset01.getString("FirstClientName");}	//登録した荷主名
					if(null==rset01.getString("LastClientName")){	rt[counter][ColLastClientName] 	= "";}else{rt[counter][ColLastClientName] 	= rset01.getString("LastClientName");}	//登録した荷主名
					
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
		boolean SearcNotJis = true;
		boolean SearchTelExactMatch = false;
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
    			SearcNotJis,
    			SearchTelExactMatch,
    			AllSearch
    			);
    	
    	int DENo = 0;
    	
    	for(int i=0;i<DeliveryMstRt.length;i++) {
    		if(2<(""+DeliveryMstRt[i][M00040DeliveryMstRt.ColDECD]).length()&&"AT".equals((""+DeliveryMstRt[i][M00040DeliveryMstRt.ColDECD]).substring(0,2))) {
    			String WST = B00020ToolsTextControl.num_only_String(""+DeliveryMstRt[i][M00040DeliveryMstRt.ColDECD]);
    			if("".equals(WST)){WST = "0";}
    			int wint = Integer.parseInt(WST);
				if(DENo<wint) {
					DENo=wint;
				}
    		}
    	}

    	String[] rt = new String[NeedCount];
    	for(int i=0;i<NeedCount;i++) {
    		DENo = DENo+1; 	
    		if(999999999<DENo) {
    			rt[i] = "AT"+DENo;
    		}else {
		    	rt[i] = "000000000"+DENo;
		    	rt[i] = "AT"+rt[i].substring(rt[i].length()-9,rt[i].length());
    		}
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
		boolean SearcNotJis = false;
		boolean SearchTelExactMatch = false;
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
				SearcNotJis,
				SearchTelExactMatch,
				AllSearch
				);
		int DeptNo = 0;
		for(int i=0;i<DeliveryMstRt.length;i++) {
			String WST = B00020ToolsTextControl.num_only_String(""+DeliveryMstRt[i][M00040DeliveryMstRt.ColDepartmentCd]);
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