import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class M00031CarMstRt{
	/*
	コピペ用
	ArrayList<String> SearchWHCD = new ArrayList<String>();
	ArrayList<String> SearchShippingCompanyCd = new ArrayList<String>();
	ArrayList<String> SearchCarCd = new ArrayList<String>();
	ArrayList<String> SearchCarName = new ArrayList<String>();
	ArrayList<String> SearchDelFg = new ArrayList<String>();
	boolean AllSearch = false;
	
	Object[][] CarMstRt = M00031CarMstRt.CarMstRt(
			SearchWHCD,
			SearchShippingCompanyCd,
			SearchCarCd,
			SearchCarName,
			SearchDelFg,
			AllSearch);
			
	String GetWHCD					= (String)CarMstRt[i][ColWHCD];						//担当倉庫
	String GetShippingCompanyCd		= (String)CarMstRt[i][ColShippingCompanyCd];		//運送会社CD
	String GetShippingCompanyName01	= (String)CarMstRt[i][ColShippingCompanyName01];	//運送会社名1
	String GetShippingCompanyName02	= (String)CarMstRt[i][ColShippingCompanyName02];	//運送会社名2
	String GetShippingCompanyName03	= (String)CarMstRt[i][ColShippingCompanyName03];	//運送会社名3
	String GetCarCd					= (String)CarMstRt[i][ColCarCd];					//車輛CD
	String GetCarName01				= (String)CarMstRt[i][ColCarName01];				//車輛名01
	String GetCarName02				= (String)CarMstRt[i][ColCarName02];				//車輛名02
	String GetCarName03				= (String)CarMstRt[i][ColCarName03];				//車輛名03
	String GetDriverCd				= (String)CarMstRt[i][ColDriverCd];					//乗務員CD
	String GetUserName01			= (String)CarMstRt[i][ColUserName01];				//ユーザー名1
	String GetUserName02			= (String)CarMstRt[i][ColUserName02];				//ユーザー名2
	String GetUserName03			= (String)CarMstRt[i][ColUserName03];				//ユーザー名3
	String GetPTMSCD				= (String)CarMstRt[i][ColPTMSCD];					//基幹システム車輛コード
	String GetEntryDate				= (String)CarMstRt[i][ColEntryDate];				//データ登録日時
	String GetUpdateDate			= (String)CarMstRt[i][ColUpdateDate];				//データ更新日時
	String GetEntryUser				= (String)CarMstRt[i][ColEntryUser];				//登録者コード
	String GetUpdateUser			= (String)CarMstRt[i][ColUpdateUser];				//更新者コード
	int GetDelFg					= (String)CarMstRt[i][ColDelFg];					//削除フラグ
	String GetWHName				= (String)CarMstRt[i][ColWHName];					//倉庫名
			
	*/
	//戻り値カラム
	static int ColWHCD						= (int) 0;	//担当倉庫
	static int ColShippingCompanyCd		= (int) 1;	//運送会社CD
	static int ColShippingCompanyName01	= (int) 2;	//運送会社名1
	static int ColShippingCompanyName02	= (int) 3;	//運送会社名2
	static int ColShippingCompanyName03	= (int) 4;	//運送会社名3
	static int ColCarCd					= (int) 5;	//車輛CD
	static int ColCarName01				= (int) 6;	//車輛名01
	static int ColCarName02				= (int) 7;	//車輛名02
	static int ColCarName03				= (int) 8;	//車輛名03
	static int ColDriverCd					= (int) 9;	//乗務員CD
	static int ColUserName01				= (int)10;	//ユーザー名1
	static int ColUserName02				= (int)11;	//ユーザー名2
	static int ColUserName03				= (int)12;	//ユーザー名3
	static int ColPTMSCD					= (int)13;	//基幹システム車輛コード
	static int ColEntryDate				= (int)14;	//データ登録日時
	static int ColUpdateDate				= (int)15;	//データ更新日時
	static int ColEntryUser				= (int)16;	//登録者コード
	static int ColUpdateUser				= (int)17;	//更新者コード
	static int ColDelFg					= (int)18;	//削除フラグ
	static int ColWHName					= (int)19;	//倉庫名
	
	public static Object[][] RtSettingCarMstRt(){
		Object[][] RtSettingCarMstRt = {
				 {"WHCD"					,ColWHCD						,"String"	,"担当倉庫"}
				,{"ShippingCompanyCd"		,ColShippingCompanyCd		,"String"	,"運送会社CD"}
				,{"ShippingCompanyName01"	,ColShippingCompanyName01	,"String"	,"運送会社名1"}
				,{"ShippingCompanyName02"	,ColShippingCompanyName02	,"String"	,"運送会社名2"}
				,{"ShippingCompanyName03"	,ColShippingCompanyName03	,"String"	,"運送会社名3"}
				,{"CarCd"					,ColCarCd						,"String"	,"車輛CD"}
				,{"CarName01"				,ColCarName01					,"String"	,"車輛名01"}
				,{"CarName02"				,ColCarName02					,"String"	,"車輛名02"}
				,{"CarName03"				,ColCarName03					,"String"	,"車輛名03"}
				,{"DriverCd"				,ColDriverCd					,"String"	,"乗務員CD"}
				,{"UserName01"				,ColUserName01				,"String"	,"ユーザー名1"}
				,{"UserName02"				,ColUserName02				,"String"	,"ユーザー名2"}
				,{"UserName03"				,ColUserName03				,"String"	,"ユーザー名3"}
				,{"PTMSCD"					,ColPTMSCD						,"String"	,"基幹システム車輛コード"}
				,{"EntryDate"				,ColEntryDate					,"String"	,"データ登録日時"}
				,{"UpdateDate"				,ColUpdateDate				,"String"	,"データ更新日時"}
				,{"EntryUser"				,ColEntryUser					,"String"	,"登録者コード"}
				,{"UpdateUser"				,ColUpdateUser				,"String"	,"更新者コード"}
				,{"DelFg"					,ColDelFg						,"int"		,"削除フラグ"}
				,{"WHName"					,ColWHName						,"String"	,"倉庫名"}
				};
		
		return RtSettingCarMstRt;
	}

	public static Object[][] CarMstRt(
			ArrayList<String> SearchWHCD,
			ArrayList<String> SearchShippingCompanyCd,
			ArrayList<String> SearchCarCd,
			ArrayList<String> SearchCarName,
			ArrayList<String> SearchDelFg,
			boolean AllSearch){
		
		SearchWHCD				= B00150ArrayListControl.ArryListStringUniqueList(SearchWHCD);
		SearchShippingCompanyCd	= B00150ArrayListControl.ArryListStringUniqueList(SearchShippingCompanyCd);
		SearchCarCd				= B00150ArrayListControl.ArryListStringUniqueList(SearchCarCd);
		SearchCarName			= B00150ArrayListControl.ArryListStringUniqueList(SearchCarName);
		SearchDelFg				= B00150ArrayListControl.ArryListStringUniqueList(SearchDelFg);
		
		Object[][] rt = new Object[0][19];
		boolean SearchKick = false;
		if(AllSearch) {SearchKick = true;}
		
		String sql = "select \n"
			+"(KM0071_CARMST.WHCD) as WHCD,\n"													//担当倉庫
			+"(KM0010_WHMST.WHName) as WHName,\n"												//倉庫名
			+"(KM0071_CARMST.ShippingCompanyCd) as ShippingCompanyCd,\n"						//運送会社CD
			+"(KM0070_SHIPPINGCOMPANYMST.ShippingCompanyName01) as ShippingCompanyName01,\n"	//運送会社名1
			+"(KM0070_SHIPPINGCOMPANYMST.ShippingCompanyName02) as ShippingCompanyName02,\n"	//運送会社名2
			+"(KM0070_SHIPPINGCOMPANYMST.ShippingCompanyName03) as ShippingCompanyName03,\n"	//運送会社名3
			+"(KM0071_CARMST.CarCd) as CarCd,\n"				//車輛CD
			+"(KM0071_CARMST.CarName01) as CarName01,\n"		//車輛名01
			+"(KM0071_CARMST.CarName02) as CarName02,\n"		//車輛名02
			+"(KM0071_CARMST.CarName03) as CarName03,\n"		//車輛名03
			+"(KM0071_CARMST.DriverCd) as DriverCd,\n"			//乗務員CD
			+"(KM0020_USERMST.UserName01) as UserName01,\n"		//ユーザー名1
			+"(KM0020_USERMST.UserName02) as UserName02,\n"		//ユーザー名2
			+"(KM0020_USERMST.UserName03) as UserName03,\n"		//ユーザー名3
			+"(KM0071_CARMST.PTMSCD) as PTMSCD,\n"				//基幹システム車輛コード
			+"(KM0071_CARMST.EntryDate) as EntryDate,\n"		//データ登録日時
			+"(KM0071_CARMST.UpdateDate) as UpdateDate,\n"		//データ更新日時
			+"(KM0071_CARMST.EntryUser) as EntryUser,\n"		//登録者コード
			+"(KM0071_CARMST.UpdateUser) as UpdateUser,\n"		//更新者コード
			+"(KM0071_CARMST.DelFg) as DelFg\n"					//削除フラグ
			+" from "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0071_CARMST\n"
			+" left outer join "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0070_SHIPPINGCOMPANYMST"
			+" on("
			+" "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0071_CARMST.ShippingCompanyCd = "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0070_SHIPPINGCOMPANYMST.ShippingCompanyCd"
			+")\n"
			+" left outer join "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0020_USERMST"
			+" on("
			+" "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0071_CARMST.WHCD = "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0020_USERMST.WHCD"
			+" and "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0071_CARMST.ShippingCompanyCd = "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0020_USERMST.ShippingCompanyCd"
			+" and "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0071_CARMST.DriverCd = "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0020_USERMST.UserCd"
			+")\n"
			+" left outer join "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST"
			+" on("
			+" "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0071_CARMST.WHCD = "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST.WHCD"
			+")\n"
			+" where 1=1 ";
		
		if(null!=SearchWHCD && 0<SearchWHCD.size()){
			SearchKick = true;
			for(int i=0;i<SearchWHCD.size();i++){
				if(0==i){
					sql = sql + "\n and(";
				}else{
					sql = sql + " or ";
				}
				sql = sql + "KM0071_CARMST.WHCD = ?";
			}
			sql = sql +	")";
		}
		if(null!=SearchShippingCompanyCd && 0<SearchShippingCompanyCd.size()){
			SearchKick = true;
			for(int i=0;i<SearchShippingCompanyCd.size();i++){
				if(0==i){
					sql = sql + "\n and(";
				}else{
					sql = sql + " or ";
				}
				sql = sql + "KM0071_CARMST.ShippingCompanyCd = ?";
			}
			sql = sql +	")";
		}
		if(null!=SearchCarCd && 0<SearchCarCd.size()){
			SearchKick = true;
			for(int i=0;i<SearchCarCd.size();i++){
				if(0==i){
					sql = sql + "\n and(";
				}else{
					sql = sql + " or ";
				}
				sql = sql + "KM0071_CARMST.CarCd = ?";
			}
			sql = sql +	")";
		}
		if(null!=SearchCarName && 0<SearchCarName.size()){
			SearchKick = true;
			for(int i=0;i<SearchCarName.size();i++){
				if(0==i){
					sql = sql + "\n and(";
				}else{
					sql = sql + " or ";
				}
				sql = sql + "KM0071_CARMST.CarName01 like ?";
				sql = sql + " or KM0071_CARMST.CarName02 like ?";
				sql = sql + " or KM0071_CARMST.CarName03 like ?";
			}
			sql = sql +	")";
		}
		
		if(null!=SearchDelFg && 0<SearchDelFg.size()){
			SearchKick = true;
			for(int i=0;i<SearchDelFg.size();i++){
				if(0==i){
					sql = sql + "\n and(";
				}else{
					sql = sql + " or ";
				}
				sql = sql + "KM0071_CARMST.DelFg = ?";
			}
			sql = sql +	")";
		}
		
		sql = sql + " order by KM0071_CARMST.WHCD,KM0071_CARMST.ShippingCompanyCd,KM0071_CARMST.CarCd";
		
		if(SearchKick) {
			A00010DbConnect.DB_CONN("NYANKO");
			ResultSet rset01 = null;
			PreparedStatement stmt01 = null;
			try {
				stmt01 = A00010DbConnect.conn.prepareStatement(sql);
				int StmtCount = 0;
				
				if(null!=SearchWHCD && 0<SearchWHCD.size()){
					for(int i=0;i<SearchWHCD.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchWHCD.get(i)+"");
					}
				}
				if(null!=SearchShippingCompanyCd && 0<SearchShippingCompanyCd.size()){
					for(int i=0;i<SearchShippingCompanyCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchShippingCompanyCd.get(i)+"");
					}
				}
				if(null!=SearchCarCd && 0<SearchCarCd.size()){
					for(int i=0;i<SearchCarCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchCarCd.get(i)+"");
					}
				}
				if(null!=SearchCarName && 0<SearchCarName.size()){
					for(int i=0;i<SearchCarName.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchCarName.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchCarName.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchCarName.get(i)+"%");
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

				rt = new Object[counter][20];
				counter = 0;
				rset01.beforeFirst();
				while (rset01.next()) {
					if(null==rset01.getString("WHCD")){					rt[counter][ColWHCD] 						= "";}else{rt[counter][ColWHCD] 						= rset01.getString("WHCD");}					//担当倉庫
					if(null==rset01.getString("ShippingCompanyCd")){	rt[counter][ColShippingCompanyCd] 		= "";}else{rt[counter][ColShippingCompanyCd] 		= rset01.getString("ShippingCompanyCd");}		//運送会社CD
					if(null==rset01.getString("ShippingCompanyName01")){rt[counter][ColShippingCompanyName01] 	= "";}else{rt[counter][ColShippingCompanyName01] 	= rset01.getString("ShippingCompanyName01");}	//運送会社名1
					if(null==rset01.getString("ShippingCompanyName02")){rt[counter][ColShippingCompanyName02] 	= "";}else{rt[counter][ColShippingCompanyName02] 	= rset01.getString("ShippingCompanyName02");}	//運送会社名2
					if(null==rset01.getString("ShippingCompanyName03")){rt[counter][ColShippingCompanyName03] 	= "";}else{rt[counter][ColShippingCompanyName03] 	= rset01.getString("ShippingCompanyName03");}	//運送会社名3
					if(null==rset01.getString("CarCd")){				rt[counter][ColCarCd] 						= "";}else{rt[counter][ColCarCd] 						= rset01.getString("CarCd");}					//車輛CD
					if(null==rset01.getString("CarName01")){			rt[counter][ColCarName01] 				= "";}else{rt[counter][ColCarName01] 					= rset01.getString("CarName01");}				//車輛名01
					if(null==rset01.getString("CarName02")){			rt[counter][ColCarName02] 				= "";}else{rt[counter][ColCarName02] 					= rset01.getString("CarName02");}				//車輛名02
					if(null==rset01.getString("CarName03")){			rt[counter][ColCarName03] 				= "";}else{rt[counter][ColCarName03] 					= rset01.getString("CarName03");}				//車輛名03
					if(null==rset01.getString("DriverCd")){				rt[counter][ColDriverCd] 					= "";}else{rt[counter][ColDriverCd] 					= rset01.getString("DriverCd");}				//乗務員CD
					if(null==rset01.getString("UserName01")){			rt[counter][ColUserName01] 				= "";}else{rt[counter][ColUserName01] 				= rset01.getString("UserName01");}				//ユーザー名1
					if(null==rset01.getString("UserName02")){			rt[counter][ColUserName02] 				= "";}else{rt[counter][ColUserName02] 				= rset01.getString("UserName02");}				//ユーザー名2
					if(null==rset01.getString("UserName03")){			rt[counter][ColUserName03] 				= "";}else{rt[counter][ColUserName03] 				= rset01.getString("UserName03");}				//ユーザー名3
					if(null==rset01.getString("PTMSCD")){				rt[counter][ColPTMSCD] 					= "";}else{rt[counter][ColPTMSCD] 						= rset01.getString("PTMSCD");}					//基幹システム車輛コード
					if(null==rset01.getTimestamp("EntryDate")){			rt[counter][ColEntryDate] 				= "";}else{rt[counter][ColEntryDate] 					= B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("EntryDate"))[1];}		//データ登録日時
					if(null==rset01.getTimestamp("UpdateDate")){		rt[counter][ColUpdateDate] 				= "";}else{rt[counter][ColUpdateDate] 				= B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("UpdateDate"))[1];}	//データ更新日時
					if(null==rset01.getString("EntryUser")){			rt[counter][ColEntryUser] 				= "";}else{rt[counter][ColEntryUser] 					= rset01.getString("EntryUser");}				//登録者コード
					if(null==rset01.getString("UpdateUser")){			rt[counter][ColUpdateUser] 				= "";}else{rt[counter][ColUpdateUser] 				= rset01.getString("UpdateUser");}				//更新者コード
					rt[counter][ColDelFg] = rset01.getInt("DelFg");	//削除フラグ
					if(null==rset01.getString("WHName")){				rt[counter][ColWHName]						= "";}else{rt[counter][ColWHName]						=rset01.getString("WHName");}					//倉庫名
					
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
}