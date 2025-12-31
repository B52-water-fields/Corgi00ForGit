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
	*/
	//戻り値カラム
	public static Object[][] RtSettingUserMstRt(){
		Object[][] RtSettingUserMstRt = {
				 {"WHCD"					,(int) 0	,"String"	,"倉庫コード"}
				,{"ShippingCompanyCd"		,(int) 1	,"String"	,"運送会社CD"}
				,{"ShippingCompanyName01"	,(int) 2	,"String"	,"運送会社名"}
				,{"UserCd"					,(int) 3	,"String"	,"ユーザーCD"}
				,{"PassWord"				,(int) 4	,"String"	,"パスワード"}
				,{"AuthorityFG"				,(int) 5	,"String"	,"権限区分"}
				,{"CarCd"					,(int) 6	,"String"	,"標準車輛CD"}
				,{"CarName01"				,(int) 7	,"String"	,"車両名称01"}
				,{"CarName02"				,(int) 8	,"String"	,"車両名称02"}
				,{"CarName03"				,(int) 9	,"String"	,"車両名称03"}
				,{"UserName01"				,(int)10	,"String"	,"ユーザー名1"}
				,{"UserName02"				,(int)11	,"String"	,"ユーザー名2"}
				,{"UserName03"				,(int)12	,"String"	,"ユーザー名3"}
				,{"Post"					,(int)13	,"String"	,"郵便番号"}
				,{"Add01"					,(int)14	,"String"	,"住所1"}
				,{"Add02"					,(int)15	,"String"	,"住所2"}
				,{"Add03"					,(int)16	,"String"	,"住所3"}
				,{"Tel"						,(int)17	,"String"	,"電話番号"}
				,{"Fax"						,(int)18	,"String"	,"FAX"}
				,{"Mail"					,(int)19	,"String"	,"メールアドレス"}
				,{"Com01"					,(int)20	,"String"	,"コメント1"}
				,{"Com02"					,(int)21	,"String"	,"コメント2"}
				,{"Com03"					,(int)22	,"String"	,"コメント3"}
				,{"EntryDate"				,(int)23	,"String"	,"データ登録日時"}
				,{"UpdateDate"				,(int)24	,"String"	,"データ更新日時"}
				,{"EntryUser"				,(int)25	,"String"	,"登録者コード"}
				,{"UpdateUser"				,(int)26	,"String"	,"更新者コード"}
				,{"PTMSCD"					,(int)27	,"String"	,"基幹システムユーザーコード"}
				,{"DelFg"					,(int)28	,"int"		,"削除区分"}
				,{"WHName"					,(int)29	,"String"	,"倉庫名"}
				,{"MainClient"				,(int)30	,"String"	,"主要担当荷主CD"}
				,{"CLName01"				,(int)31	,"String"	,"主要担当荷主名"}
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
					if(null==rset01.getString("WHCD")){					rt[counter][ 0]="";}else{rt[counter][ 0]=rset01.getString("WHCD");}										//倉庫コード
					if(null==rset01.getString("ShippingCompanyCd")){	rt[counter][ 1]="";}else{rt[counter][ 1]=rset01.getString("ShippingCompanyCd");}						//運送会社CD
					if(null==rset01.getString("ShippingCompanyName01")){rt[counter][ 2]="";}else{rt[counter][ 2]=rset01.getString("ShippingCompanyName01");}					//運送会社名
					if(null==rset01.getString("UserCd")){				rt[counter][ 3]="";}else{rt[counter][ 3]=rset01.getString("UserCd");}									//ユーザーCD
					if(null==rset01.getString("PassWord")){				rt[counter][ 4]="";}else{rt[counter][ 4]=rset01.getString("PassWord");}									//パスワード
					rt[counter][ 5]=rset01.getInt("AuthorityFG");//権限区分
					if(null==rset01.getString("CarCd")){				rt[counter][ 6]="";}else{rt[counter][ 6]=rset01.getString("CarCd");}									//標準車輛CD
					if(null==rset01.getString("CarName01")){			rt[counter][ 7]="";}else{rt[counter][ 7]=rset01.getString("CarName01");}								//車両名称01
					if(null==rset01.getString("CarName02")){			rt[counter][ 8]="";}else{rt[counter][ 8]=rset01.getString("CarName02");}								//車両名称02
					if(null==rset01.getString("CarName03")){			rt[counter][ 9]="";}else{rt[counter][ 9]=rset01.getString("CarName03");}								//車両名称03
					if(null==rset01.getString("UserName01")){			rt[counter][10]="";}else{rt[counter][10]=rset01.getString("UserName01");}								//ユーザー名1
					if(null==rset01.getString("UserName02")){			rt[counter][11]="";}else{rt[counter][11]=rset01.getString("UserName02");}								//ユーザー名2
					if(null==rset01.getString("UserName03")){			rt[counter][12]="";}else{rt[counter][12]=rset01.getString("UserName03");}								//ユーザー名3
					if(null==rset01.getString("Post")){					rt[counter][13]="";}else{rt[counter][13]=rset01.getString("Post");}										//郵便番号
					if(null==rset01.getString("Add01")){				rt[counter][14]="";}else{rt[counter][14]=rset01.getString("Add01");}									//住所1
					if(null==rset01.getString("Add02")){				rt[counter][15]="";}else{rt[counter][15]=rset01.getString("Add02");}									//住所2
					if(null==rset01.getString("Add03")){				rt[counter][16]="";}else{rt[counter][16]=rset01.getString("Add03");}									//住所3
					if(null==rset01.getString("Tel")){					rt[counter][17]="";}else{rt[counter][17]=rset01.getString("Tel");}										//電話番号
					if(null==rset01.getString("Fax")){					rt[counter][18]="";}else{rt[counter][18]=rset01.getString("Fax");}										//FAX
					if(null==rset01.getString("Mail")){					rt[counter][19]="";}else{rt[counter][19]=rset01.getString("Mail");}										//メールアドレス
					if(null==rset01.getString("Com01")){				rt[counter][20]="";}else{rt[counter][20]=rset01.getString("Com01");}									//コメント1
					if(null==rset01.getString("Com02")){				rt[counter][21]="";}else{rt[counter][21]=rset01.getString("Com02");}									//コメント2
					if(null==rset01.getString("Com03")){				rt[counter][22]="";}else{rt[counter][22]=rset01.getString("Com03");}									//コメント3
					if(null==rset01.getTimestamp("EntryDate")){			rt[counter][23]="";}else{rt[counter][23]=B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("EntryDate"))[1];}	//データ登録日時
					if(null==rset01.getTimestamp("UpdateDate")){		rt[counter][24]="";}else{rt[counter][24]=B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("UpdateDate"))[1];}	//データ更新日時
					if(null==rset01.getString("EntryUser")){			rt[counter][25]="";}else{rt[counter][25]=rset01.getString("EntryUser");}								//登録者コード
					if(null==rset01.getString("UpdateUser")){			rt[counter][26]="";}else{rt[counter][26]=rset01.getString("UpdateUser");}								//更新者コード
					if(null==rset01.getString("PTMSCD")){				rt[counter][27]="";}else{rt[counter][27]=rset01.getString("PTMSCD");}									//基幹システムユーザーコード
					rt[counter][28] = rset01.getInt("DelFg");//削除区分
					if(null==rset01.getString("WHName")){				rt[counter][29]="";}else{rt[counter][29]=rset01.getString("WHName");}									//倉庫名
					if(null==rset01.getString("MainClient")){			rt[counter][30]="";}else{rt[counter][30]=rset01.getString("MainClient");}								//主要担当荷主CD
					if(null==rset01.getString("CLName01")){				rt[counter][31]="";}else{rt[counter][31]=rset01.getString("CLName01");}									//主要担当荷主名
					
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
    		if("ATUS".equals((""+UserMstRt[i][0]).substring(0,4))&&11==(""+UserMstRt[i][0]).length()) {
    			String WST = B00020ToolsTextControl.num_only_String(""+UserMstRt[i][0]);
    			if(7==WST.length()) {
    				int wint = Integer.parseInt(WST);
    				if(UserNo<wint) {
    					UserNo=wint;
    				}
    			}
    		}
    	}

    	String[] rt = new String[NeedCount];
    	for(int i=0;i<NeedCount;i++) {
    		UserNo = UserNo+1;
	    	rt[i] = "0000000"+UserNo;
	    	rt[i] = "ATUS"+rt[i].substring(rt[i].length()-7,rt[i].length());
    	}
    	
    	return rt;
	}
	
	
}