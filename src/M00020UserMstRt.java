import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class M00020UserMstRt{
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
	
	Object[][] UserMstRt = M00020UserMstRt.UserMstRt(
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
	
	String GetWHCD					= (String)UserMstRt[i][M00020UserMstRt.ColWHCD];					//倉庫コード
	String GetShippingCompanyCd		= (String)UserMstRt[i][M00020UserMstRt.ColShippingCompanyCd];		//運送会社CD
	String GetShippingCompanyName01	= (String)UserMstRt[i][M00020UserMstRt.ColShippingCompanyName01];	//運送会社名
	String GetUserCd				= (String)UserMstRt[i][M00020UserMstRt.ColUserCd];					//ユーザーCD
	String GetPassWord				= (String)UserMstRt[i][M00020UserMstRt.ColPassWord];				//パスワード
	int AuthorityFG					= (int)UserMstRt[i][M00020UserMstRt.ColAuthorityFG];				//権限区分
	String GetCarCd					= (String)UserMstRt[i][M00020UserMstRt.ColCarCd];					//標準車輛CD
	String GetCarName01				= (String)UserMstRt[i][M00020UserMstRt.ColCarName01];				//車両名称01
	String GetCarName02				= (String)UserMstRt[i][M00020UserMstRt.ColCarName02];				//車両名称02
	String GetCarName03				= (String)UserMstRt[i][M00020UserMstRt.ColCarName03];				//車両名称03
	String GetUserName01			= (String)UserMstRt[i][M00020UserMstRt.ColUserName01];				//ユーザー名1
	String GetUserName02			= (String)UserMstRt[i][M00020UserMstRt.ColUserName02];				//ユーザー名2
	String GetUserName03			= (String)UserMstRt[i][M00020UserMstRt.ColUserName03];				//ユーザー名3
	String GetPost					= (String)UserMstRt[i][M00020UserMstRt.ColPost];					//郵便番号
	String GetAdd01					= (String)UserMstRt[i][M00020UserMstRt.ColAdd01];					//住所1
	String GetAdd02					= (String)UserMstRt[i][M00020UserMstRt.ColAdd02];					//住所2
	String GetAdd03					= (String)UserMstRt[i][M00020UserMstRt.ColAdd03];					//住所3
	String GetTel					= (String)UserMstRt[i][M00020UserMstRt.ColTel];						//電話番号
	String GetFax					= (String)UserMstRt[i][M00020UserMstRt.ColFax];						//FAX
	String GetMail					= (String)UserMstRt[i][M00020UserMstRt.ColMail];					//メールアドレス
	String GetCom01					= (String)UserMstRt[i][M00020UserMstRt.ColCom01];					//コメント1
	String GetCom02					= (String)UserMstRt[i][M00020UserMstRt.ColCom02];					//コメント2
	String GetCom03					= (String)UserMstRt[i][M00020UserMstRt.ColCom03];					//コメント3
	String GetEntryDate				= (String)UserMstRt[i][M00020UserMstRt.ColEntryDate];				//データ登録日時
	String GetUpdateDate			= (String)UserMstRt[i][M00020UserMstRt.ColUpdateDate];				//データ更新日時
	String GetEntryUser				= (String)UserMstRt[i][M00020UserMstRt.ColEntryUser];				//登録者コード
	String GetUpdateUser			= (String)UserMstRt[i][M00020UserMstRt.ColUpdateUser];				//更新者コード
	String GetPTMSCD				= (String)UserMstRt[i][M00020UserMstRt.ColPTMSCD];					//基幹システムユーザーコード
	int GetDelFg					= (int)UserMstRt[i][M00020UserMstRt.ColDelFg];						//削除区分
	String GetWHName				= (String)UserMstRt[i][M00020UserMstRt.ColWHName];					//倉庫名
	String GetMainClient			= (String)UserMstRt[i][M00020UserMstRt.ColMainClient];				//主要担当荷主CD
	String GetCLName01				= (String)UserMstRt[i][M00020UserMstRt.ColCLName01];				//主要担当荷主名
	
	*/
	
	//戻り値カラム
	static int ColWHCD						= (int) 0;	//倉庫コード
	static int ColShippingCompanyCd		= (int) 1;	//運送会社CD
	static int ColShippingCompanyName01	= (int) 2;	//運送会社名
	static int ColUserCd					= (int) 3;	//ユーザーCD
	static int ColPassWord					= (int) 4;	//パスワード
	static int ColAuthorityFG				= (int) 5;	//権限区分
	static int ColCarCd					= (int) 6;	//標準車輛CD
	static int ColCarName01				= (int) 7;	//車両名称01
	static int ColCarName02				= (int) 8;	//車両名称02
	static int ColCarName03				= (int) 9;	//車両名称03
	static int ColUserName01				= (int)10;	//ユーザー名1
	static int ColUserName02				= (int)11;	//ユーザー名2
	static int ColUserName03				= (int)12;	//ユーザー名3
	static int ColPost						= (int)13;	//郵便番号
	static int ColAdd01					= (int)14;	//住所1
	static int ColAdd02					= (int)15;	//住所2
	static int ColAdd03					= (int)16;	//住所3
	static int ColTel						= (int)17;	//電話番号
	static int ColFax						= (int)18;	//FAX
	static int ColMail						= (int)19;	//メールアドレス
	static int ColCom01					= (int)20;	//コメント1
	static int ColCom02					= (int)21;	//コメント2
	static int ColCom03					= (int)22;	//コメント3
	static int ColEntryDate				= (int)23;	//データ登録日時
	static int ColUpdateDate				= (int)24;	//データ更新日時
	static int ColEntryUser				= (int)25;	//登録者コード
	static int ColUpdateUser				= (int)26;	//更新者コード
	static int ColPTMSCD					= (int)27;	//基幹システムユーザーコード
	static int ColDelFg					= (int)28;	//削除区分
	static int ColWHName					= (int)29;	//倉庫名
	static int ColMainClient				= (int)30;	//主要担当荷主CD
	static int ColCLName01					= (int)31;	//主要担当荷主名
	
	public static Object[][] RtSettingUserMstRt(){
		Object[][] RtSettingUserMstRt = {
				 {"WHCD"					,ColWHCD						,"String"	,"倉庫コード"}
				,{"ShippingCompanyCd"		,ColShippingCompanyCd		,"String"	,"運送会社CD"}
				,{"ShippingCompanyName01"	,ColShippingCompanyName01	,"String"	,"運送会社名"}
				,{"UserCd"					,ColUserCd						,"String"	,"ユーザーCD"}
				,{"PassWord"				,ColPassWord					,"String"	,"パスワード"}
				,{"AuthorityFG"				,ColAuthorityFG				,"int"		,"権限区分"}
				,{"CarCd"					,ColCarCd						,"String"	,"標準車輛CD"}
				,{"CarName01"				,ColCarName01					,"String"	,"車両名称01"}
				,{"CarName02"				,ColCarName02					,"String"	,"車両名称02"}
				,{"CarName03"				,ColCarName03					,"String"	,"車両名称03"}
				,{"UserName01"				,ColUserName01				,"String"	,"ユーザー名1"}
				,{"UserName02"				,ColUserName02				,"String"	,"ユーザー名2"}
				,{"UserName03"				,ColUserName03				,"String"	,"ユーザー名3"}
				,{"Post"					,ColPost						,"String"	,"郵便番号"}
				,{"Add01"					,ColAdd01						,"String"	,"住所1"}
				,{"Add02"					,ColAdd02						,"String"	,"住所2"}
				,{"Add03"					,ColAdd03						,"String"	,"住所3"}
				,{"Tel"						,ColTel						,"String"	,"電話番号"}
				,{"Fax"						,ColFax						,"String"	,"FAX"}
				,{"Mail"					,ColMail						,"String"	,"メールアドレス"}
				,{"Com01"					,ColCom01						,"String"	,"コメント1"}
				,{"Com02"					,ColCom02						,"String"	,"コメント2"}
				,{"Com03"					,ColCom03						,"String"	,"コメント3"}
				,{"EntryDate"				,ColEntryDate					,"String"	,"データ登録日時"}
				,{"UpdateDate"				,ColUpdateDate				,"String"	,"データ更新日時"}
				,{"EntryUser"				,ColEntryUser					,"String"	,"登録者コード"}
				,{"UpdateUser"				,ColUpdateUser				,"String"	,"更新者コード"}
				,{"PTMSCD"					,ColPTMSCD						,"String"	,"基幹システムユーザーコード"}
				,{"DelFg"					,ColDelFg						,"int"		,"削除区分"}
				,{"WHName"					,ColWHName						,"String"	,"倉庫名"}
				,{"MainClient"				,ColMainClient				,"String"	,"主要担当荷主CD"}
				,{"CLName01"				,ColCLName01					,"String"	,"主要担当荷主名"}
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
		
		SearchWHCD				= B00150ArrayListControl.ArryListStringUniqueList(SearchWHCD);
		SearchShippingCompanyCd	= B00150ArrayListControl.ArryListStringUniqueList(SearchShippingCompanyCd);
		SearchAuthorityFG		= B00150ArrayListControl.ArryListStringUniqueList(SearchAuthorityFG);
		SearchUserCd			= B00150ArrayListControl.ArryListStringUniqueList(SearchUserCd);
		SearchUserName			= B00150ArrayListControl.ArryListStringUniqueList(SearchUserName);
		SearchCarCd				= B00150ArrayListControl.ArryListStringUniqueList(SearchCarCd);
		SearchCarName			= B00150ArrayListControl.ArryListStringUniqueList(SearchCarName);
		SearchPost				= B00150ArrayListControl.ArryListStringUniqueList(SearchPost);
		SearchAdd				= B00150ArrayListControl.ArryListStringUniqueList(SearchAdd);
		SearchTel				= B00150ArrayListControl.ArryListStringUniqueList(SearchTel);
		SearchFax				= B00150ArrayListControl.ArryListStringUniqueList(SearchFax);
		SearchMail				= B00150ArrayListControl.ArryListStringUniqueList(SearchMail);
		SearchCom				= B00150ArrayListControl.ArryListStringUniqueList(SearchCom);
		SearchDelFg				= B00150ArrayListControl.ArryListStringUniqueList(SearchDelFg);
		
		Object[][] rt = new Object[0][32];
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

				+ " from "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0020_USERMST \n"
				+ " left outer join "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0070_SHIPPINGCOMPANYMST\n"
				+ " on("+A00000Main.MySqlDefaultSchemaNYANKO+".KM0020_USERMST.ShippingCompanyCd = "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0070_SHIPPINGCOMPANYMST.ShippingCompanyCd)\n"
				+ " left outer join "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0071_CARMST\n"
				+ " on(\n"
				+ " "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0020_USERMST.WHCD = "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0071_CARMST.WHCD\n"
				+ "	and "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0020_USERMST.ShippingCompanyCd="+A00000Main.MySqlDefaultSchemaNYANKO+".KM0071_CARMST.ShippingCompanyCd\n"
				+ " and "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0020_USERMST.CarCd = "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0071_CARMST.CarCd\n"
				+ " )\n"
				+ " left outer join "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST"
				+ " on(\n"
				+ " "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0020_USERMST.WHCD = "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST.WHCD"
				+ " )\n"
				+ " left outer join "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0030_CLIENTMST"
				+ " on(\n"
				+ " "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0020_USERMST.MainClient = "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0030_CLIENTMST.cl_cd"
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
			A00010DbConnect.DB_CONN("NYANKO");
			ResultSet rset01 = null;
			PreparedStatement stmt01 = null;
			try {
				stmt01 = A00010DbConnect.conn.prepareStatement(sql);
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
				
				int counter = 0;
				rset01.beforeFirst();
				while (rset01.next()) {
					counter=counter+1;
				}
				rt = new Object[counter][32];
				counter = 0;
				rset01.beforeFirst();
				while (rset01.next()) {
					if(null==rset01.getString("WHCD")){					rt[counter][ColWHCD]						="";}else{rt[counter][ColWHCD]						=rset01.getString("WHCD");}										//倉庫コード
					if(null==rset01.getString("ShippingCompanyCd")){	rt[counter][ColShippingCompanyCd]		="";}else{rt[counter][ColShippingCompanyCd]		=rset01.getString("ShippingCompanyCd");}						//運送会社CD
					if(null==rset01.getString("ShippingCompanyName01")){rt[counter][ColShippingCompanyName01]	="";}else{rt[counter][ColShippingCompanyName01]	=rset01.getString("ShippingCompanyName01");}					//運送会社名
					if(null==rset01.getString("UserCd")){				rt[counter][ColUserCd]						="";}else{rt[counter][ColUserCd]					=rset01.getString("UserCd");}									//ユーザーCD
					if(null==rset01.getString("PassWord")){				rt[counter][ColPassWord]					="";}else{rt[counter][ColPassWord]					=rset01.getString("PassWord");}									//パスワード
					rt[counter][ColAuthorityFG]=rset01.getInt("AuthorityFG");	//権限区分
					if(null==rset01.getString("CarCd")){				rt[counter][ColCarCd]						="";}else{rt[counter][ColCarCd]					=rset01.getString("CarCd");}									//標準車輛CD
					if(null==rset01.getString("CarName01")){			rt[counter][ColCarName01]					="";}else{rt[counter][ColCarName01]				=rset01.getString("CarName01");}								//車両名称01
					if(null==rset01.getString("CarName02")){			rt[counter][ColCarName02]					="";}else{rt[counter][ColCarName02]				=rset01.getString("CarName02");}								//車両名称02
					if(null==rset01.getString("CarName03")){			rt[counter][ColCarName03]					="";}else{rt[counter][ColCarName03]				=rset01.getString("CarName03");}								//車両名称03
					if(null==rset01.getString("UserName01")){			rt[counter][ColUserName01]				="";}else{rt[counter][ColUserName01]				=rset01.getString("UserName01");}								//ユーザー名1
					if(null==rset01.getString("UserName02")){			rt[counter][ColUserName02]				="";}else{rt[counter][ColUserName02]				=rset01.getString("UserName02");}								//ユーザー名2
					if(null==rset01.getString("UserName03")){			rt[counter][ColUserName03]				="";}else{rt[counter][ColUserName03]				=rset01.getString("UserName03");}								//ユーザー名3
					if(null==rset01.getString("Post")){					rt[counter][ColPost]						="";}else{rt[counter][ColPost]						=rset01.getString("Post");}										//郵便番号
					if(null==rset01.getString("Add01")){				rt[counter][ColAdd01]						="";}else{rt[counter][ColAdd01]					=rset01.getString("Add01");}									//住所1
					if(null==rset01.getString("Add02")){				rt[counter][ColAdd02]						="";}else{rt[counter][ColAdd02]					=rset01.getString("Add02");}									//住所2
					if(null==rset01.getString("Add03")){				rt[counter][ColAdd03]						="";}else{rt[counter][ColAdd03]					=rset01.getString("Add03");}									//住所3
					if(null==rset01.getString("Tel")){					rt[counter][ColTel]						="";}else{rt[counter][ColTel]						=rset01.getString("Tel");}										//電話番号
					if(null==rset01.getString("Fax")){					rt[counter][ColFax]						="";}else{rt[counter][ColFax]						=rset01.getString("Fax");}										//FAX
					if(null==rset01.getString("Mail")){					rt[counter][ColMail]						="";}else{rt[counter][ColMail]						=rset01.getString("Mail");}										//メールアドレス
					if(null==rset01.getString("Com01")){				rt[counter][ColCom01]						="";}else{rt[counter][ColCom01]					=rset01.getString("Com01");}									//コメント1
					if(null==rset01.getString("Com02")){				rt[counter][ColCom02]						="";}else{rt[counter][ColCom02]					=rset01.getString("Com02");}									//コメント2
					if(null==rset01.getString("Com03")){				rt[counter][ColCom03]						="";}else{rt[counter][ColCom03]					=rset01.getString("Com03");}									//コメント3
					if(null==rset01.getTimestamp("EntryDate")){			rt[counter][ColEntryDate]					="";}else{rt[counter][ColEntryDate]				=B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("EntryDate"))[1];}	//データ登録日時
					if(null==rset01.getTimestamp("UpdateDate")){		rt[counter][ColUpdateDate]				="";}else{rt[counter][ColUpdateDate]				=B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("UpdateDate"))[1];}	//データ更新日時
					if(null==rset01.getString("EntryUser")){			rt[counter][ColEntryUser]					="";}else{rt[counter][ColEntryUser]				=rset01.getString("EntryUser");}								//登録者コード
					if(null==rset01.getString("UpdateUser")){			rt[counter][ColUpdateUser]				="";}else{rt[counter][ColUpdateUser]				=rset01.getString("UpdateUser");}								//更新者コード
					if(null==rset01.getString("PTMSCD")){				rt[counter][ColPTMSCD]						="";}else{rt[counter][ColPTMSCD]					=rset01.getString("PTMSCD");}									//基幹システムユーザーコード
					rt[counter][ColDelFg] = rset01.getInt("DelFg");	//削除区分
					if(null==rset01.getString("WHName")){				rt[counter][ColWHName]						="";}else{rt[counter][ColWHName]					=rset01.getString("WHName");}									//倉庫名
					if(null==rset01.getString("MainClient")){			rt[counter][ColMainClient]				="";}else{rt[counter][ColMainClient]				=rset01.getString("MainClient");}								//主要担当荷主CD
					if(null==rset01.getString("CLName01")){				rt[counter][ColCLName01]					="";}else{rt[counter][ColCLName01]					=rset01.getString("CLName01");}									//主要担当荷主名
					
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
    		if(4<(""+UserMstRt[i][M00020UserMstRt.ColUserCd]).length() && "ATUS".equals((""+UserMstRt[i][M00020UserMstRt.ColUserCd]).substring(0,4))) {
    			String WST = B00020ToolsTextControl.num_only_String(""+UserMstRt[i][M00020UserMstRt.ColUserCd]);
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