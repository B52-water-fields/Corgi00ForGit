import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class M100_UserMstRt{
	/*
	コピペ用
	ArrayList<String> SearchWHCD = new ArrayList<String>();
	ArrayList<String> SearchShippingCompanyCd = new ArrayList<String>();
	ArrayList<String> SearchAuthorityFG = new ArrayList<String>();
	ArrayList<String> SearchUserCd = new ArrayList<String>();
	ArrayList<String> SearchUserName = new ArrayList<String>();
	ArrayList<String> SearchCarCd = new ArrayList<String>();
	ArrayList<String> SearchCarName = new ArrayList<String>();
	ArrayList<String> SearchPost = new ArrayList<String>();
	ArrayList<String> SearchAdd = new ArrayList<String>();
	ArrayList<String> SearchTel = new ArrayList<String>();
	ArrayList<String> SearchFax = new ArrayList<String>();
	ArrayList<String> SearchMail = new ArrayList<String>();
	ArrayList<String> SearchCom = new ArrayList<String>();
	ArrayList<String> SearchDelFg = new ArrayList<String>();
	boolean AllSearch = false;
	
	Object[][] UserMstRt = M100_UserMstRt.UserMstRt(
			SearchWHCD,
			SearchShippingCompanyCd,
			SearchAuthorityFG,
			SearchUserCd,
			SearchUserName,
			SearchCarCd,
			SearchCarName,
			SearchPost,
			SearchAdd,
			SearchTel,
			SearchFax,
			SearchMail,
			SearchCom,
			SearchDelFg,
			AllSearch);
	
	String GetWHCD					= (String)UserMstRt[i][M100_UserMstRt.ColWHCD];						//倉庫コード
	String GetShippingCompanyCd		= (String)UserMstRt[i][M100_UserMstRt.ColShippingCompanyCd];		//運送会社CD
	String GetShippingCompanyName01	= (String)UserMstRt[i][M100_UserMstRt.ColShippingCompanyName01];	//運送会社名
	String GetUserCd				= (String)UserMstRt[i][M100_UserMstRt.ColUserCd];					//ユーザーCD
	String GetPassWord				= (String)UserMstRt[i][M100_UserMstRt.ColPassWord];					//パスワード
	int AuthorityFG					= (int)UserMstRt[i][M100_UserMstRt.ColAuthorityFG];					//権限区分
	String GetCarCd					= (String)UserMstRt[i][M100_UserMstRt.ColCarCd];					//標準車輛CD
	String GetCarName01				= (String)UserMstRt[i][M100_UserMstRt.ColCarName01];				//車両名称01
	String GetCarName02				= (String)UserMstRt[i][M100_UserMstRt.ColCarName02];				//車両名称02
	String GetCarName03				= (String)UserMstRt[i][M100_UserMstRt.ColCarName03];				//車両名称03
	String GetUserName01			= (String)UserMstRt[i][M100_UserMstRt.ColUserName01];				//ユーザー名1
	String GetUserName02			= (String)UserMstRt[i][M100_UserMstRt.ColUserName02];				//ユーザー名2
	String GetUserName03			= (String)UserMstRt[i][M100_UserMstRt.ColUserName03];				//ユーザー名3
	String GetPost					= (String)UserMstRt[i][M100_UserMstRt.ColPost];						//郵便番号
	String GetAdd01					= (String)UserMstRt[i][M100_UserMstRt.ColAdd01];					//住所1
	String GetAdd02					= (String)UserMstRt[i][M100_UserMstRt.ColAdd02];					//住所2
	String GetAdd03					= (String)UserMstRt[i][M100_UserMstRt.ColAdd03];					//住所3
	String GetTel					= (String)UserMstRt[i][M100_UserMstRt.ColTel];						//電話番号
	String GetFax					= (String)UserMstRt[i][M100_UserMstRt.ColFax];						//FAX
	String GetMail					= (String)UserMstRt[i][M100_UserMstRt.ColMail];						//メールアドレス
	String GetCom01					= (String)UserMstRt[i][M100_UserMstRt.ColCom01];					//コメント1
	String GetCom02					= (String)UserMstRt[i][M100_UserMstRt.ColCom02];					//コメント2
	String GetCom03					= (String)UserMstRt[i][M100_UserMstRt.ColCom03];					//コメント3
	String GetEntryDate				= (String)UserMstRt[i][M100_UserMstRt.ColEntryDate];				//データ登録日時
	String GetUpdateDate			= (String)UserMstRt[i][M100_UserMstRt.ColUpdateDate];				//データ更新日時
	String GetEntryUser				= (String)UserMstRt[i][M100_UserMstRt.ColEntryUser];				//登録者コード
	String GetUpdateUser			= (String)UserMstRt[i][M100_UserMstRt.ColUpdateUser];				//更新者コード
	String GetPTMSCD				= (String)UserMstRt[i][M100_UserMstRt.ColPTMSCD];					//基幹システムユーザーコード
	int GetDelFg					= (int)UserMstRt[i][M100_UserMstRt.ColDelFg];						//削除区分
	String GetWHName				= (String)UserMstRt[i][M100_UserMstRt.ColWHName];					//倉庫名
	String GetMainClient			= (String)UserMstRt[i][M100_UserMstRt.ColMainClient];				//主要担当荷主CD
	String GetCLName01				= (String)UserMstRt[i][M100_UserMstRt.ColCLName01];				//主要担当荷主名
	
	*/
	
	//戻り値カラム
	static final  int ColWHCD						= (int) 0;	//倉庫コード
	static final  int ColShippingCompanyCd		= (int) 1;	//運送会社CD
	static final  int ColShippingCompanyName01	= (int) 2;	//運送会社名
	static final  int ColUserCd					= (int) 3;	//ユーザーCD
	static final  int ColPassWord					= (int) 4;	//パスワード
	static final  int ColAuthorityFG				= (int) 5;	//権限区分
	static final  int ColUserName01				= (int) 6;	//ユーザー名1
	static final  int ColUserName02				= (int) 7;	//ユーザー名2
	static final  int ColUserName03				= (int) 8;	//ユーザー名3
	static final  int ColCarCd						= (int) 9;	//標準車輛CD
	static final  int ColCarName01				= (int)10;	//車両名称01
	static final  int ColCarName02				= (int)11;	//車両名称02
	static final  int ColCarName03				= (int)12;	//車両名称03
	static final  int ColPost						= (int)13;	//郵便番号
	static final  int ColAdd01						= (int)14;	//住所1
	static final  int ColAdd02						= (int)15;	//住所2
	static final  int ColAdd03						= (int)16;	//住所3
	static final  int ColTel						= (int)17;	//電話番号
	static final  int ColFax						= (int)18;	//FAX
	static final  int ColMail						= (int)19;	//メールアドレス
	static final  int ColCom01						= (int)20;	//コメント1
	static final  int ColCom02						= (int)21;	//コメント2
	static final  int ColCom03						= (int)22;	//コメント3
	static final  int ColEntryDate				= (int)23;	//データ登録日時
	static final  int ColUpdateDate				= (int)24;	//データ更新日時
	static final  int ColEntryUser				= (int)25;	//登録者コード
	static final  int ColUpdateUser				= (int)26;	//更新者コード
	static final  int ColPTMSCD					= (int)27;	//基幹システムユーザーコード
	static final  int ColDelFg						= (int)28;	//削除区分
	static final  int ColWHName					= (int)29;	//倉庫名
	static final  int ColMainClient				= (int)30;	//主要担当荷主CD
	static final  int ColCLName01					= (int)31;	//主要担当荷主名
	
	public static Object[][] RtUserMstRt(){
		Object[][] RtSettingUserMstRt = {
				 {"WHCD"					,ColWHCD						,"String"	,"倉庫コード"			,"Key"}
				,{"ShippingCompanyCd"		,ColShippingCompanyCd		,"String"	,"運送会社CD"			,"Key"}
				,{"ShippingCompanyName01"	,ColShippingCompanyName01	,"String"	,"運送会社名"			,""}
				,{"UserCd"					,ColUserCd						,"String"	,"ユーザーCD"			,"Key"}
				,{"PassWord"				,ColPassWord					,"String"	,"パスワード"			,""}
				,{"AuthorityFG"				,ColAuthorityFG				,"int"		,"権限区分"				,""}
				,{"CarCd"					,ColCarCd						,"String"	,"標準車輛CD"			,""}
				,{"CarName01"				,ColCarName01					,"String"	,"車両表記名"			,""}
				,{"CarName02"				,ColCarName02					,"String"	,"車両正式名"			,""}
				,{"CarName03"				,ColCarName03					,"String"	,"車両略名"				,""}
				,{"UserName01"				,ColUserName01				,"String"	,"ユーザー名1"			,""}
				,{"UserName02"				,ColUserName02				,"String"	,"ユーザー名2"			,""}
				,{"UserName03"				,ColUserName03				,"String"	,"ユーザー名3"			,""}
				,{"Post"					,ColPost						,"String"	,"郵便番号"				,""}
				,{"Add01"					,ColAdd01						,"String"	,"住所1"				,""}
				,{"Add02"					,ColAdd02						,"String"	,"住所2"				,""}
				,{"Add03"					,ColAdd03						,"String"	,"住所3"				,""}
				,{"Tel"						,ColTel						,"String"	,"電話番号"				,""}
				,{"Fax"						,ColFax						,"String"	,"FAX"					,""}
				,{"Mail"					,ColMail						,"String"	,"メールアドレス"		,""}
				,{"Com01"					,ColCom01						,"String"	,"コメント1"			,""}
				,{"Com02"					,ColCom02						,"String"	,"コメント2"			,""}
				,{"Com03"					,ColCom03						,"String"	,"コメント3"			,""}
				,{"EntryDate"				,ColEntryDate					,"String"	,"データ登録日時"		,""}
				,{"UpdateDate"				,ColUpdateDate				,"String"	,"データ更新日時"		,""}
				,{"EntryUser"				,ColEntryUser					,"String"	,"登録者コード"			,""}
				,{"UpdateUser"				,ColUpdateUser				,"String"	,"更新者コード"			,""}
				,{"PTMSCD"					,ColPTMSCD						,"String"	,"基幹SYSユーザーCD"	,""}
				,{"DelFg"					,ColDelFg						,"int"		,"削除区分"				,""}
				,{"WHName"					,ColWHName						,"String"	,"倉庫名"				,""}
				,{"MainClient"				,ColMainClient				,"String"	,"主要担当荷主CD"		,""}
				,{"CLName01"				,ColCLName01					,"String"	,"主要担当荷主名"		,""}
				};
		
		return RtSettingUserMstRt;
	}
	public static Object[][] UserMstRt(
			ArrayList<String> SearchWHCD,
			ArrayList<String> SearchShippingCompanyCd,
			ArrayList<String> SearchAuthorityFG,
			ArrayList<String> SearchUserCd,
			ArrayList<String> SearchUserName,
			ArrayList<String> SearchCarCd,
			ArrayList<String> SearchCarName,
			ArrayList<String> SearchPost,
			ArrayList<String> SearchAdd,
			ArrayList<String> SearchTel,
			ArrayList<String> SearchFax,
			ArrayList<String> SearchMail,
			ArrayList<String> SearchCom,
			ArrayList<String> SearchDelFg,
			boolean AllSearch){
		
		SearchWHCD				= B100_ArrayListControl.ArryListStringUniqueList(SearchWHCD);
		SearchShippingCompanyCd	= B100_ArrayListControl.ArryListStringUniqueList(SearchShippingCompanyCd);
		SearchAuthorityFG		= B100_ArrayListControl.ArryListStringUniqueList(SearchAuthorityFG);
		SearchUserCd			= B100_ArrayListControl.ArryListStringUniqueList(SearchUserCd);
		SearchUserName			= B100_ArrayListControl.ArryListStringUniqueList(SearchUserName);
		SearchCarCd				= B100_ArrayListControl.ArryListStringUniqueList(SearchCarCd);
		SearchCarName			= B100_ArrayListControl.ArryListStringUniqueList(SearchCarName);
		SearchPost				= B100_ArrayListControl.ArryListStringUniqueList(SearchPost);
		SearchAdd				= B100_ArrayListControl.ArryListStringUniqueList(SearchAdd);
		SearchTel				= B100_ArrayListControl.ArryListStringUniqueList(SearchTel);
		SearchFax				= B100_ArrayListControl.ArryListStringUniqueList(SearchFax);
		SearchMail				= B100_ArrayListControl.ArryListStringUniqueList(SearchMail);
		SearchCom				= B100_ArrayListControl.ArryListStringUniqueList(SearchCom);
		SearchDelFg				= B100_ArrayListControl.ArryListStringUniqueList(SearchDelFg);
		
		Object[][] rt = new Object[0][RtUserMstRt().length];
		boolean SearchKick = false;
		if(AllSearch) {SearchKick = true;}
		
		String sql = "select \n"
				+"(KM0020_USERMST.WHCD) as WHCD,\n"													//倉庫コード
				+"(KM0010_WHMST.WHName) as WHName,\n"												//倉庫名
				+"(KM0020_USERMST.ShippingCompanyCd) as ShippingCompanyCd,\n"						//運送会社CD
				+"(KM0070_SHIPPINGCOMPANYMST.ShippingCompanyName01) as ShippingCompanyName01,\n"	//運送会社名
				+"(KM0020_USERMST.UserCd) as UserCd,\n"												//ユーザーCD
				+"(KM0020_USERMST.PassWord) as PassWord,\n"											//パスワード
				+"(KM0020_USERMST.AuthorityFG) as AuthorityFG,\n"									//権限区分
				+"(KM0020_USERMST.CarCd) as CarCd,\n"												//標準車輛CD
				+"(KM0071_CARMST.CarName01) as CarName01,\n"										//車両名称01
				+"(KM0071_CARMST.CarName02) as CarName02,\n"										//車両名称02
				+"(KM0071_CARMST.CarName03) as CarName03,\n"										//車両名称03
				+"(KM0020_USERMST.UserName01) as UserName01,\n"										//ユーザー名1
				+"(KM0020_USERMST.UserName02) as UserName02,\n"										//ユーザー名2
				+"(KM0020_USERMST.UserName03) as UserName03,\n"										//ユーザー名3
				+"(KM0020_USERMST.Post) as Post,\n"													//郵便番号
				+"(KM0020_USERMST.Add01) as Add01,\n"												//住所1
				+"(KM0020_USERMST.Add02) as Add02,\n"												//住所2
				+"(KM0020_USERMST.Add03) as Add03,\n"												//住所3
				+"(KM0020_USERMST.Tel) as Tel,\n"													//電話番号
				+"(KM0020_USERMST.Fax) as Fax,\n"													//FAX
				+"(KM0020_USERMST.Mail) as Mail,\n"													//メールアドレス
				+"(KM0020_USERMST.Com01) as Com01,\n"												//コメント1
				+"(KM0020_USERMST.Com02) as Com02,\n"												//コメント2
				+"(KM0020_USERMST.Com03) as Com03,\n"												//コメント3
				+"(KM0020_USERMST.EntryDate) as EntryDate,\n"										//登録者コード
				+"(KM0020_USERMST.UpdateDate) as UpdateDate,\n"										//更新者コード
				+"(KM0020_USERMST.EntryUser) as EntryUser,\n"										//データ登録日時
				+"(KM0020_USERMST.UpdateUser) as UpdateUser,\n"										//データ更新日時
				+"(KM0020_USERMST.PTMSCD) as PTMSCD,\n"												//基幹システムユーザーコード
				+"(KM0020_USERMST.DelFg) as DelFg,\n"												//削除区分
				+"(KM0020_USERMST.MainClient) as MainClient,\n"										//主要担当荷主CD
				+"(KM0030_CLIENTMST.CLName01) as CLName01\n"										//主要担当荷主名

				+ " from "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0020_USERMST \n"
				+ " left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0070_SHIPPINGCOMPANYMST\n"
				+ " on("+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0020_USERMST.ShippingCompanyCd = "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0070_SHIPPINGCOMPANYMST.ShippingCompanyCd)\n"
				+ " left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0071_CARMST\n"
				+ " on(\n"
				+ " "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0020_USERMST.WHCD = "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0071_CARMST.WHCD\n"
				+ "	and "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0020_USERMST.ShippingCompanyCd="+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0071_CARMST.ShippingCompanyCd\n"
				+ " and "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0020_USERMST.CarCd = "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0071_CARMST.CarCd\n"
				+ " )\n"
				+ " left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST"
				+ " on(\n"
				+ " "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0020_USERMST.WHCD = "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST.WHCD"
				+ " )\n"
				+ " left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0030_CLIENTMST"
				+ " on(\n"
				+ " "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0020_USERMST.MainClient = "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0030_CLIENTMST.cl_cd"
				+ " )\n"
				+ " where 1=1";	
		
		if(null!=SearchWHCD && 0<SearchWHCD.size()) {
			SearchKick=true;
			sql = sql + " and(";
			for(int i=0;i<SearchWHCD.size();i++) {
				if(0<i) {sql=sql+" or ";}
				sql = sql + " KM0020_USERMST.WHCD = ?";
			}
			sql=sql+")";
		}
		if(null!=SearchShippingCompanyCd && 0<SearchShippingCompanyCd.size()) {
			SearchKick=true;
			sql = sql + " and(";
			for(int i=0;i<SearchShippingCompanyCd.size();i++) {
				if(0<i) {sql=sql+" or ";}
				sql = sql + " KM0020_USERMST.ShippingCompanyCd = ?";
			}
			sql=sql+")";
		}
		if(null!=SearchAuthorityFG && 0<SearchAuthorityFG.size()) {
			SearchKick=true;
			sql = sql + " and(";
			for(int i=0;i<SearchAuthorityFG.size();i++) {
				if(0<i) {sql=sql+" or ";}
				sql = sql + " KM0020_USERMST.AuthorityFG = ?";
			}
			sql=sql+")";
		}
		if(null!=SearchUserCd && 0<SearchUserCd.size()) {
			SearchKick=true;
			sql = sql + " and(";
			for(int i=0;i<SearchUserCd.size();i++) {
				if(0<i) {sql=sql+" or ";}
				sql = sql + " KM0020_USERMST.UserCd = ?";
			}
			sql=sql+")";
		}
		if(null!=SearchUserName && 0<SearchUserName.size()) {
			SearchKick=true;
			sql = sql + " and(";
			for(int i=0;i<SearchUserName.size();i++) {
				if(0<i) {sql=sql+" or ";}
				sql = sql + " KM0020_USERMST.UserName01 like ?";
				sql = sql + " or KM0020_USERMST.UserName02 like ?";
				sql = sql + " or KM0020_USERMST.UserName03 like ?";
			}
			sql=sql+")";
		}
		if(null!=SearchCarCd && 0<SearchCarCd.size()) {
			SearchKick=true;
			sql = sql + " and(";
			for(int i=0;i<SearchCarCd.size();i++) {
				if(0<i) {sql=sql+" or ";}
				sql = sql + " KM0020_USERMST.CarCd = ?";
			}
			sql=sql+")";
		}
		if(null!=SearchCarName && 0<SearchCarName.size()) {
			SearchKick=true;
			sql = sql + " and(";
			for(int i=0;i<SearchCarName.size();i++) {
				if(0<i) {sql=sql+" or ";}
				sql = sql + " KM0071_CARMST.CarName01 like ?";
				sql = sql + " or KM0071_CARMST.CarName02 like ?";
				sql = sql + " or KM0071_CARMST.CarName03 like ?";
			}
			sql=sql+")";
		}
		if(null!=SearchPost && 0<SearchPost.size()) {
			SearchKick=true;
			sql = sql + " and(";
			for(int i=0;i<SearchPost.size();i++) {
				if(0<i) {sql=sql+" or ";}
				sql = sql + " KM0020_USERMST.Post like ?";
			}
			sql=sql+")";
		}
		if(null!=SearchAdd && 0<SearchAdd.size()) {
			SearchKick=true;
			sql = sql + " and(";
			for(int i=0;i<SearchAdd.size();i++) {
				if(0<i) {sql=sql+" or ";}
				sql = sql + " CONCAT (KM0020_USERMST.Add01";
				sql = sql + " , KM0020_USERMST.Add02";
				sql = sql + " , KM0020_USERMST.Add03) like ?";
			}
			sql=sql+")";
		}
		if(null!=SearchTel && 0<SearchTel.size()) {
			SearchKick=true;
			sql = sql + " and(";
			for(int i=0;i<SearchTel.size();i++) {
				if(0<i) {sql=sql+" or ";}
				sql = sql + " KM0020_USERMST.Tel like ?";
			}
			sql=sql+")";
		}
		if(null!=SearchFax && 0<SearchFax.size()) {
			SearchKick=true;
			sql = sql + " and(";
			for(int i=0;i<SearchFax.size();i++) {
				if(0<i) {sql=sql+" or ";}
				sql = sql + " KM0020_USERMST.Fax like ?";
			}
			sql=sql+")";
		}
		if(null!=SearchMail && 0<SearchMail.size()) {
			SearchKick=true;
			sql = sql + " and(";
			for(int i=0;i<SearchMail.size();i++) {
				if(0<i) {sql=sql+" or ";}
				sql = sql + " KM0020_USERMST.Mail like ?";
			}
			sql=sql+")";
		}
		if(null!=SearchCom && 0<SearchCom.size()) {
			SearchKick=true;
			sql = sql + " and(";
			for(int i=0;i<SearchCom.size();i++) {
				if(0<i) {sql=sql+" or ";}
				sql = sql + " KM0020_USERMST.Com01 like ?";
				sql = sql + " or KM0020_USERMST.Com02 like ?";
				sql = sql + " or KM0020_USERMST.Com03 like ?";
			}
			sql=sql+")";
		}
		
		if(null!=SearchDelFg && 0<SearchDelFg.size()) {
			SearchKick=true;
			sql = sql + " and(";
			for(int i=0;i<SearchDelFg.size();i++) {
				if(0<i) {sql=sql+" or ";}
				sql = sql + " KM0020_USERMST.DelFg = ?";
			}
			sql=sql+")";
		}
		
		sql = sql + " order by KM0020_USERMST.ShippingCompanyCd,KM0020_USERMST.UserCd";
		//System.out.println(sql);
		if(SearchKick) {
			A100_DbConnect.DB_CONN("NYANKO");
			ResultSet rset01 = null;
			PreparedStatement stmt01 = null;
			try {
				stmt01 = A100_DbConnect.conn.prepareStatement(sql);
				int StmtCount = 0;
				
				if(null!=SearchWHCD && 0<SearchWHCD.size()) {
					for(int i=0;i<SearchWHCD.size();i++) {
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchWHCD.get(i)+"");
					}
				}
				if(null!=SearchShippingCompanyCd && 0<SearchShippingCompanyCd.size()) {
					for(int i=0;i<SearchShippingCompanyCd.size();i++) {
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchShippingCompanyCd.get(i)+"");
					}
				}
				if(null!=SearchAuthorityFG && 0<SearchAuthorityFG.size()) {
					for(int i=0;i<SearchAuthorityFG.size();i++) {
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchAuthorityFG.get(i)+"");
					}
				}
				if(null!=SearchUserCd && 0<SearchUserCd.size()) {
					for(int i=0;i<SearchUserCd.size();i++) {
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchUserCd.get(i)+"");
					}
				}
				if(null!=SearchUserName && 0<SearchUserName.size()) {
					for(int i=0;i<SearchUserName.size();i++) {
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchUserName.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchUserName.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchUserName.get(i)+"%");
					}
				}
				if(null!=SearchCarCd && 0<SearchCarCd.size()) {
					for(int i=0;i<SearchCarCd.size();i++) {
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchCarCd.get(i)+"");
					}
				}
				if(null!=SearchCarName && 0<SearchCarName.size()) {
					for(int i=0;i<SearchCarName.size();i++) {
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchCarName.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchCarName.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchCarName.get(i)+"%");
					}
				}
				if(null!=SearchPost && 0<SearchPost.size()) {
					for(int i=0;i<SearchPost.size();i++) {
						if(0<i) {sql=sql+" or ";}
						sql = sql + " KM0020_USERMST.Post like'"+SearchPost.get(i)+"%'";
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
				
				if(null!=SearchDelFg && 0<SearchDelFg.size()) {
					for(int i=0;i<SearchDelFg.size();i++) {
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchDelFg.get(i)+"");
					}
				}
				rset01 = stmt01.executeQuery();
				
				rt = B100_RtObjectCreate.B100_RtObjectCreate(rset01,RtUserMstRt());
				
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
	public static String[] NewUserCdGet(int NeedCount) {
		ArrayList<String> SearchWHCD = new ArrayList<String>();
		ArrayList<String> SearchShippingCompanyCd = new ArrayList<String>();
		ArrayList<String> SearchAuthorityFG = new ArrayList<String>();
		ArrayList<String> SearchUserCd = new ArrayList<String>();
		ArrayList<String> SearchUserName = new ArrayList<String>();
		ArrayList<String> SearchCarCd = new ArrayList<String>();
		ArrayList<String> SearchCarName = new ArrayList<String>();
		ArrayList<String> SearchPost = new ArrayList<String>();
		ArrayList<String> SearchAdd = new ArrayList<String>();
		ArrayList<String> SearchTel = new ArrayList<String>();
		ArrayList<String> SearchFax = new ArrayList<String>();
		ArrayList<String> SearchMail = new ArrayList<String>();
		ArrayList<String> SearchCom = new ArrayList<String>();
		ArrayList<String> SearchDelFg = new ArrayList<String>();
		boolean AllSearch = true;
    	
    	Object[][] UserMstRt = UserMstRt(SearchWHCD,SearchShippingCompanyCd,SearchAuthorityFG,
    			SearchUserCd,SearchUserName,SearchCarCd,SearchCarName,
    			SearchPost,SearchAdd,SearchTel,SearchFax,SearchMail,SearchCom,SearchDelFg,
    			AllSearch);
    	
    	int UserNo = 0;
    	
    	for(int i=0;i<UserMstRt.length;i++) {
    		if(4<(""+UserMstRt[i][M100_UserMstRt.ColUserCd]).length() && "ATUS".equals((""+UserMstRt[i][M100_UserMstRt.ColUserCd]).substring(0,4))) {
    			String WST = B100_TextControl.num_only_String(""+UserMstRt[i][M100_UserMstRt.ColUserCd]);
    			if("".equals(WST)){WST = "0";}
				int wint = Integer.parseInt(WST);
				if(UserNo<wint) {
					UserNo=wint;
				}
    		}
    	}

    	String[] rt = new String[NeedCount];
    	int MaxCount = 999999999;
    	int wint = MaxCount+1;
    	String SetZero = (""+wint).substring(1,(""+wint).length());
    	for(int i=0;i<NeedCount;i++) {
    		UserNo = UserNo+1;
	    	if(MaxCount<UserNo) {
    			rt[i] = "ATUS"+UserNo;
    		}else {
		    	rt[i] = SetZero+UserNo;
		    	rt[i] = "ATUS"+rt[i].substring(rt[i].length()-SetZero.length(),rt[i].length());
    		}
    	}
    	
    	return rt;
	}
	
	
}