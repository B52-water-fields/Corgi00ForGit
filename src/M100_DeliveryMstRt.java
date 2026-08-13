import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class M100_DeliveryMstRt{
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
	
	Object[][] DeliveryMstRt = M100_DeliveryMstRt.DeliveryMstRt(
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
		
	String GetDECD				= (String)DeliveryMstRt[i][M100_DeliveryMstRt.ColDECD];				//届先CD
	String GetDepartmentCd		= (String)DeliveryMstRt[i][M100_DeliveryMstRt.ColDepartmentCd];		//部署CD
	String GetDEName01			= (String)DeliveryMstRt[i][M100_DeliveryMstRt.ColDEName01];			//届先表記名
	String GetDEName02			= (String)DeliveryMstRt[i][M100_DeliveryMstRt.ColDEName02];			//届先正式名
	String GetDEName03			= (String)DeliveryMstRt[i][M100_DeliveryMstRt.ColDEName03];			//届先略名
	String GetPost				= (String)DeliveryMstRt[i][M100_DeliveryMstRt.ColPost];				//届先郵便
	String GetAdd01				= (String)DeliveryMstRt[i][M100_DeliveryMstRt.ColAdd01];			//届先住所1
	String GetAdd02				= (String)DeliveryMstRt[i][M100_DeliveryMstRt.ColAdd02];			//届先住所2
	String GetAdd03				= (String)DeliveryMstRt[i][M100_DeliveryMstRt.ColAdd03];			//届先住所3
	String GetTel				= (String)DeliveryMstRt[i][M100_DeliveryMstRt.ColTel];				//届先電話
	String GetFax				= (String)DeliveryMstRt[i][M100_DeliveryMstRt.ColFax];				//届先FAX
	String GetMail				= (String)DeliveryMstRt[i][M100_DeliveryMstRt.ColMail];				//届先MAIL
	String GetCom01				= (String)DeliveryMstRt[i][M100_DeliveryMstRt.ColCom01];			//コメント1
	String GetCom02				= (String)DeliveryMstRt[i][M100_DeliveryMstRt.ColCom02];			//コメント2
	String GetCom03				= (String)DeliveryMstRt[i][M100_DeliveryMstRt.ColCom03];			//コメント3
	String GetPrefecturesCd		= (String)DeliveryMstRt[i][M100_DeliveryMstRt.ColPrefecturesCd];	//JIS県CD2桁
	String GetMunicipalityCd	= (String)DeliveryMstRt[i][M100_DeliveryMstRt.ColMunicipalityCd];	//JIS市区町村CD5桁
	String GetPTMSCD			= (String)DeliveryMstRt[i][M100_DeliveryMstRt.ColPTMSCD];			//基幹システム発着地コード
	String GetEntryDate			= (String)DeliveryMstRt[i][M100_DeliveryMstRt.ColEntryDate];		//データ登録日時
	String GetUpdateDate		= (String)DeliveryMstRt[i][M100_DeliveryMstRt.ColUpdateDate];		//データ更新日時
	String GetEntryUser			= (String)DeliveryMstRt[i][M100_DeliveryMstRt.ColEntryUser];		//登録者コード
	String GetUpdateUser		= (String)DeliveryMstRt[i][M100_DeliveryMstRt.ColUpdateUser];		//更新者コード
	String GetFirstClient		= (String)DeliveryMstRt[i][M100_DeliveryMstRt.ColFirstClient];		//登録した荷主CD
	String GetLastClient		= (String)DeliveryMstRt[i][M100_DeliveryMstRt.ColLastClient];		//更新した荷主CD
	int GetDelFg				= (int)DeliveryMstRt[i][M100_DeliveryMstRt.ColDelFg];				//削除区分
	String GetFirstClientName	= (String)DeliveryMstRt[i][M100_DeliveryMstRt.ColFirstClientName];	//登録した荷主名
	String GetLastClientName	= (String)DeliveryMstRt[i][M100_DeliveryMstRt.ColLastClientName];	//登録した荷主名
		
	*/
	
	//戻り値カラム
	static final  int ColDECD				= (int) 0;	//届先CD
	static final  int ColDepartmentCd		= (int) 1;	//部署CD
	static final  int ColDEName01			= (int) 2;	//届先表記名
	static final  int ColDEName02			= (int) 3;	//届先正式名
	static final  int ColDEName03			= (int) 4;	//届先略名
	static final  int ColPost				= (int) 5;	//届先郵便
	static final  int ColAdd01				= (int) 6;	//届先住所1
	static final  int ColAdd02				= (int) 7;	//届先住所2
	static final  int ColAdd03				= (int) 8;	//届先住所3
	static final  int ColTel				= (int) 9;	//届先電話
	static final  int ColFax				= (int)10;	//届先FAX
	static final  int ColMail				= (int)11;	//届先MAIL
	static final  int ColCom01				= (int)12;	//コメント1
	static final  int ColCom02				= (int)13;	//コメント2
	static final  int ColCom03				= (int)14;	//コメント3
	static final  int ColPrefecturesCd	= (int)15;	//JIS県CD2桁
	static final  int ColMunicipalityCd	= (int)16;	//JIS市区町村CD5桁
	static final  int ColPTMSCD			= (int)17;	//基幹システム発着地コード
	static final  int ColEntryDate		= (int)18;	//データ登録日時
	static final  int ColUpdateDate		= (int)19;	//データ更新日時
	static final  int ColEntryUser		= (int)20;	//登録者コード
	static final  int ColUpdateUser		= (int)21;	//更新者コード
	static final  int ColFirstClient		= (int)22;	//登録した荷主CD
	static final  int ColLastClient		= (int)23;	//更新した荷主CD
	static final  int ColDelFg				= (int)24;	//削除区分
	static final  int ColFirstClientName	= (int)25;	//登録した荷主名
	static final  int ColLastClientName	= (int)26;	//登録した荷主名
	
	//検索値カラム
	static final  int ColSearchDECD				= (int) 0;	//検索条件届先CD
	static final  int ColSearchDepartmentCd		= (int) 1;	//検索条件届先部署CD
	static final  int ColSearchDEName				= (int) 2;	//検索条件届先名
	static final  int ColSearchPost				= (int) 3;	//検索条件届先郵便
	static final  int ColSearchAdd				= (int) 4;	//検索条件届先住所
	static final  int ColSearchTel				= (int) 5;	//検索条件届先TEL
	static final  int ColSearchFax				= (int) 6;	//検索条件届先FAX
	static final  int ColSearchMail				= (int) 7;	//検索条件届先MAIL
	static final  int ColSearchCom				= (int) 8;	//検索条件届先コメント
	static final  int ColSearchPrefecturesCd		= (int) 9;	//検索条件届先県CD
	static final  int ColSearchMunicipalityCd	= (int)10;	//検索条件届先市区町村CD
	static final  int ColSearchDelFg				= (int)11;	//検索条件削除区分
	
	
	public static Object[][] RtDeliveryMstRt(){
		Object[][] RtSettingDeliveryMstRt = {
				 {"DECD"			,ColDECD				,"String"	,"届先CD"					,"Key"}
				,{"DepartmentCd"	,ColDepartmentCd		,"String"	,"部署CD"					,"Key"}
				,{"DEName01"		,ColDEName01			,"String"	,"届先表記名"				,""}
				,{"DEName02"		,ColDEName02			,"String"	,"届先正式名"				,""}
				,{"DEName03"		,ColDEName03			,"String"	,"届先略名"					,""}
				,{"Post"			,ColPost				,"String"	,"届先郵便"					,""}
				,{"Add01"			,ColAdd01				,"String"	,"届先住所1"				,""}
				,{"Add02"			,ColAdd02				,"String"	,"届先住所2"				,""}
				,{"Add03"			,ColAdd03				,"String"	,"届先住所3"				,""}
				,{"Tel"				,ColTel				,"String"	,"届先電話"					,""}
				,{"Fax"				,ColFax				,"String"	,"届先FAX"					,""}
				,{"Mail"			,ColMail				,"String"	,"届先MAIL"					,""}
				,{"Com01"			,ColCom01				,"String"	,"コメント1"				,""}
				,{"Com02"			,ColCom02				,"String"	,"コメント2"				,""}
				,{"Com03"			,ColCom03				,"String"	,"コメント3"				,""}
				,{"PrefecturesCd"	,ColPrefecturesCd		,"String"	,"JIS県CD2桁"				,""}
				,{"MunicipalityCd"	,ColMunicipalityCd	,"String"	,"JIS市区町村CD5桁"			,""}
				,{"PTMSCD"			,ColPTMSCD				,"String"	,"基幹SYSCD"				,""}
				,{"EntryDate"		,ColEntryDate			,"DateTime"	,"データ登録日時"			,""}
				,{"UpdateDate"		,ColUpdateDate		,"DateTime"	,"データ更新日時"			,""}
				,{"EntryUser"		,ColEntryUser			,"String"	,"登録者コード"				,""}
				,{"UpdateUser"		,ColUpdateUser		,"String"	,"更新者コード"				,""}
				,{"FirstClient"		,ColFirstClient		,"String"	,"登録荷主CD"				,""}
				,{"LastClient"		,ColLastClient		,"String"	,"更新荷主CD"				,""}
				,{"DelFg"			,ColDelFg				,"int"		,"削除区分"					,""}
				,{"FirstClientName"	,ColFirstClientName	,"String"	,"登録荷主名"				,""}
				,{"LastClientName"	,ColLastClientName	,"String"	,"登録荷主名"				,""}
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

		Object[][] Definition = {
				 {"String"		,SearchDECD				,"Exact"			,ColSearchDECD				,""										,"検索条件届先CD"			,""}
				,{"String"		,SearchDepartmentCd		,"Exact"			,ColSearchDepartmentCd		,""										,"検索条件届先部署CD"		,""}
				,{"String"		,SearchDEName			,"Partial"			,ColSearchDEName				,""										,"検索条件届先名"			,""}
				,{"String"		,SearchPost				,"Prefix"			,ColSearchPost				,""										,"検索条件届先郵便"			,""}
				,{"String"		,SearchAdd				,"Partial"			,ColSearchAdd					,""										,"検索条件届先住所"			,""}
				,{"String"		,SearchTel				,"Partial"			,ColSearchTel					,""										,"検索条件届先TEL"			,""}
				,{"String"		,SearchFax				,"Partial"			,ColSearchFax					,""										,"検索条件届先FAX"			,""}
				,{"String"		,SearchMail				,"Partial"			,ColSearchMail				,""										,"検索条件届先MAIL"			,""}
				,{"String"		,SearchCom				,"Partial"			,ColSearchCom					,""										,"検索条件届先コメント"		,""}
				,{"String"		,SearchPrefecturesCd	,"Exact"			,ColSearchPrefecturesCd		,""										,"検索条件届先県CD"			,""}
				,{"String"		,SearchMunicipalityCd	,"Exact"			,ColSearchMunicipalityCd		,""										,"検索条件届先市区町村CD"	,""}
				,{"String"		,SearchDelFg			,"Exact"			,ColSearchDelFg				,B100_DefaultVariable.SearchDelList	,"検索条件削除区分"			,""}
				};
		/*
		日付系検索最小は念のため00:00:00扱い
		日付系検索項目最大は一日進めて00:00:00扱い
		検索条件の重複除去
		*/
		Definition	= B100_ArraySearchControl.SearchDefinitionControl(Definition);
		
		for(int i=0;i<Definition.length;i++) {
			switch((int)Definition[i][3]) {
				case ColSearchDECD:	
					SearchDECD				= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchDepartmentCd:	
					SearchDepartmentCd		= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchDEName:	
					SearchDEName			= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchPost:	
					SearchPost				= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchAdd:	
					SearchAdd				= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchTel:	
					SearchTel				= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchFax:	
					SearchFax				= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchMail:	
					SearchMail				= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchCom:	
					SearchCom				= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchPrefecturesCd:	
					SearchPrefecturesCd		= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchMunicipalityCd:	
					SearchMunicipalityCd	= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchDelFg:	
					SearchDelFg				= (ArrayList<String>)Definition[i][1];
					break;
				default:
					break;
			}
		}
		
		Object[][] Rt	= DeliveryMstRtMain(
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
				SearcNotJis,			//検索条件JIS由来除く
				SearchTelExactMatch,	//電話番号完全一致
				AllSearch
				);
		return Rt;
	}
	
	private static Object[][] DeliveryMstRtMain(
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
		
		Object[][] rt = new Object[0][RtDeliveryMstRt().length];
		boolean SearchKick = false;
		
		if(AllSearch) {SearchKick=true;}
		
		String sql = " select "
			+"(KM0040_DELIVERYMST.DECD) as DECD,\n"						//届先コード
			+"(KM0040_DELIVERYMST.DepartmentCd) as DepartmentCd,\n"		//部署CD
			+"(KM0040_DELIVERYMST.DEName01) as DEName01,\n"				//届先表記名
			+"(KM0040_DELIVERYMST.DEName02) as DEName02,\n"				//届先正式名
			+"(KM0040_DELIVERYMST.DEName03) as DEName03,\n"				//届先略名
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
			
			+" from "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0040_DELIVERYMST"
			+" left outer join " +A00000_Main.MySqlDefaultSchemaNYANKO + ".KM0030_CLIENTMST as FCL"
			+" on("
			+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0040_DELIVERYMST.FirstClient = FCL.cl_cd"
			+")\n"
			+" left outer join " +A00000_Main.MySqlDefaultSchemaNYANKO + ".KM0030_CLIENTMST as LCL"
			+" on("
			+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0040_DELIVERYMST.FirstClient = LCL.cl_cd"
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
			A100_DbConnect.DB_CONN("NYANKO");
			ResultSet rset01 = null;
			PreparedStatement stmt01 = null;
			try {
				stmt01 = A100_DbConnect.conn.prepareStatement(sql);
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
				
				rt = B100_RtObjectCreate.B100_RtObjectCreate(rset01,RtDeliveryMstRt());
				
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
    		if(2<(""+DeliveryMstRt[i][M100_DeliveryMstRt.ColDECD]).length()&&"AT".equals((""+DeliveryMstRt[i][M100_DeliveryMstRt.ColDECD]).substring(0,2))) {
    			String WST = B100_TextControl.num_only_String(""+DeliveryMstRt[i][M100_DeliveryMstRt.ColDECD]);
    			if("".equals(WST)){WST = "0";}
    			int wint = Integer.parseInt(WST);
				if(DENo<wint) {
					DENo=wint;
				}
    		}
    	}

    	String[] rt = new String[NeedCount];
    	int MaxCount = 999999999;
    	int wint = MaxCount+1;
    	String SetZero = (""+wint).substring(1,(""+wint).length());
    	for(int i=0;i<NeedCount;i++) {
    		DENo = DENo+1; 	
    		if(MaxCount<DENo) {
    			rt[i] = "AT"+DENo;
    		}else {
		    	rt[i] = SetZero+DENo;
		    	rt[i] = "AT"+rt[i].substring(rt[i].length()-SetZero.length(),rt[i].length());
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
		Object[][] DeliveryMstRt = M100_DeliveryMstRt.DeliveryMstRt(
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
			String WST = B100_TextControl.num_only_String(""+DeliveryMstRt[i][M100_DeliveryMstRt.ColDepartmentCd]);
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