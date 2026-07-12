import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class M100_CarMstRt{
	/*
	コピペ用
	ArrayList<String> SearchWHCD = new ArrayList<String>();
	ArrayList<String> SearchShippingCompanyCd = new ArrayList<String>();
	ArrayList<String> SearchCarCd = new ArrayList<String>();
	ArrayList<String> SearchCarName = new ArrayList<String>();
	ArrayList<String> SearchDelFg = new ArrayList<String>();
	boolean AllSearch = false;
	
	Object[][] CarMstRt = M100_CarMstRt.CarMstRt(
			SearchWHCD,
			SearchShippingCompanyCd,
			SearchCarCd,
			SearchCarName,
			SearchDelFg,
			AllSearch);
			
	String GetWHCD					= (String)CarMstRt[i][M100_CarMstRt.ColWHCD];					//担当倉庫
	String GetShippingCompanyCd		= (String)CarMstRt[i][M100_CarMstRt.ColShippingCompanyCd];		//運送会社CD
	String GetShippingCompanyName01	= (String)CarMstRt[i][M100_CarMstRt.ColShippingCompanyName01];	//運送会社表記名
	String GetShippingCompanyName02	= (String)CarMstRt[i][M100_CarMstRt.ColShippingCompanyName02];	//運送会社正式名
	String GetShippingCompanyName03	= (String)CarMstRt[i][M100_CarMstRt.ColShippingCompanyName03];	//運送会社略名
	String GetCarCd					= (String)CarMstRt[i][M100_CarMstRt.ColCarCd];					//車輛CD
	String GetCarName01				= (String)CarMstRt[i][M100_CarMstRt.ColCarName01];				//車輛表記名
	String GetCarName02				= (String)CarMstRt[i][M100_CarMstRt.ColCarName02];				//車輛正式名
	String GetCarName03				= (String)CarMstRt[i][M100_CarMstRt.ColCarName03];				//車輛略名
	String GetDriverCd				= (String)CarMstRt[i][M100_CarMstRt.ColDriverCd];				//乗務員CD
	String GetUserName01			= (String)CarMstRt[i][M100_CarMstRt.ColUserName01];				//ユーザー名1
	String GetUserName02			= (String)CarMstRt[i][M100_CarMstRt.ColUserName02];				//ユーザー名2
	String GetUserName03			= (String)CarMstRt[i][M100_CarMstRt.ColUserName03];				//ユーザー名3
	String GetPTMSCD				= (String)CarMstRt[i][M100_CarMstRt.ColPTMSCD];					//基幹システム車輛コード
	String GetEntryDate				= (String)CarMstRt[i][M100_CarMstRt.ColEntryDate];				//データ登録日時
	String GetUpdateDate			= (String)CarMstRt[i][M100_CarMstRt.ColUpdateDate];				//データ更新日時
	String GetEntryUser				= (String)CarMstRt[i][M100_CarMstRt.ColEntryUser];				//登録者コード
	String GetUpdateUser			= (String)CarMstRt[i][M100_CarMstRt.ColUpdateUser];				//更新者コード
	int GetDelFg					= (String)CarMstRt[i][M100_CarMstRt.ColDelFg];					//削除フラグ
	String GetWHName				= (String)CarMstRt[i][M100_CarMstRt.ColWHName];					//倉庫名
			
	*/
	//戻り値カラム
	static final  int ColWHName					= (int) 0;	//倉庫名
	static final  int ColShippingCompanyName01	= (int) 1;	//運送会社表記名
	static final  int ColCarCd						= (int) 2;	//車輛CD
	static final  int ColCarName01				= (int) 3;	//車輛表記名
	static final  int ColCarName02				= (int) 4;	//車輛正式名
	static final  int ColCarName03				= (int) 5;	//車輛略名
	static final  int ColDriverCd					= (int) 6;	//乗務員CD
	static final  int ColUserName01				= (int) 7;	//ユーザー名1
	static final  int ColUserName02				= (int) 8;	//ユーザー名2
	static final  int ColUserName03				= (int) 9;	//ユーザー名3
	static final  int ColPTMSCD					= (int)10;	//基幹システム車輛コード
	static final  int ColWHCD						= (int)11;	//担当倉庫
	static final  int ColShippingCompanyCd		= (int)12;	//運送会社CD
	static final  int ColShippingCompanyName02	= (int)13;	//運送会社正式名
	static final  int ColShippingCompanyName03	= (int)14;	//運送会社略名
	static final  int ColEntryDate				= (int)15;	//データ登録日時
	static final  int ColUpdateDate				= (int)16;	//データ更新日時
	static final  int ColEntryUser				= (int)17;	//登録者コード
	static final  int ColUpdateUser				= (int)18;	//更新者コード
	static final  int ColDelFg						= (int)19;	//削除フラグ
	
	
	public static Object[][] RtSettingCarMstRt(){
		Object[][] RtSettingCarMstRt = {
				 {"WHCD"					,ColWHCD						,"String"	,"担当倉庫"					,"Key"}
				,{"ShippingCompanyCd"		,ColShippingCompanyCd		,"String"	,"運送会社CD"				,"Key"}
				,{"ShippingCompanyName01"	,ColShippingCompanyName01	,"String"	,"運送会社表記名"			,""}
				,{"ShippingCompanyName02"	,ColShippingCompanyName02	,"String"	,"運送会社正式名"			,""}
				,{"ShippingCompanyName03"	,ColShippingCompanyName03	,"String"	,"運送会社略名"				,""}
				,{"CarCd"					,ColCarCd						,"String"	,"車輛CD"					,"Key"}
				,{"CarName01"				,ColCarName01					,"String"	,"車輛表記名"				,""}
				,{"CarName02"				,ColCarName02					,"String"	,"車輛正式名"				,""}
				,{"CarName03"				,ColCarName03					,"String"	,"車輛略名"					,""}
				,{"DriverCd"				,ColDriverCd					,"String"	,"乗務員CD"					,""}
				,{"UserName01"				,ColUserName01				,"String"	,"ユーザー名1"				,""}
				,{"UserName02"				,ColUserName02				,"String"	,"ユーザー名2"				,""}
				,{"UserName03"				,ColUserName03				,"String"	,"ユーザー名3"				,""}
				,{"PTMSCD"					,ColPTMSCD						,"String"	,"基幹システム車輛コード"	,""}
				,{"EntryDate"				,ColEntryDate					,"DateTime"	,"データ登録日時"			,""}
				,{"UpdateDate"				,ColUpdateDate				,"DateTime"	,"データ更新日時"			,""}
				,{"EntryUser"				,ColEntryUser					,"String"	,"登録者コード"				,""}
				,{"UpdateUser"				,ColUpdateUser				,"String"	,"更新者コード"				,""}
				,{"DelFg"					,ColDelFg						,"int"		,"削除フラグ"				,""}
				,{"WHName"					,ColWHName						,"String"	,"倉庫名"					,""}
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
		
		SearchWHCD				= B100_ArrayListControl.ArryListStringUniqueList(SearchWHCD);
		SearchShippingCompanyCd	= B100_ArrayListControl.ArryListStringUniqueList(SearchShippingCompanyCd);
		SearchCarCd				= B100_ArrayListControl.ArryListStringUniqueList(SearchCarCd);
		SearchCarName			= B100_ArrayListControl.ArryListStringUniqueList(SearchCarName);
		SearchDelFg				= B100_ArrayListControl.ArryListStringUniqueList(SearchDelFg);
		
		Object[][] rt = new Object[0][RtSettingCarMstRt().length];
		boolean SearchKick = false;
		if(AllSearch) {SearchKick = true;}
		
		String sql = "select \n"
			+"(KM0071_CARMST.WHCD) as WHCD,\n"													//担当倉庫
			+"(KM0010_WHMST.WHName) as WHName,\n"												//倉庫名
			+"(KM0071_CARMST.ShippingCompanyCd) as ShippingCompanyCd,\n"						//運送会社CD
			+"(KM0070_SHIPPINGCOMPANYMST.ShippingCompanyName01) as ShippingCompanyName01,\n"	//運送会社表記名
			+"(KM0070_SHIPPINGCOMPANYMST.ShippingCompanyName02) as ShippingCompanyName02,\n"	//運送会社正式名
			+"(KM0070_SHIPPINGCOMPANYMST.ShippingCompanyName03) as ShippingCompanyName03,\n"	//運送会社略名
			+"(KM0071_CARMST.CarCd) as CarCd,\n"				//車輛CD
			+"(KM0071_CARMST.CarName01) as CarName01,\n"		//車輛表記名
			+"(KM0071_CARMST.CarName02) as CarName02,\n"		//車輛正式名
			+"(KM0071_CARMST.CarName03) as CarName03,\n"		//車輛略名
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
			+" from "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0071_CARMST\n"
			+" left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0070_SHIPPINGCOMPANYMST"
			+" on("
			+" "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0071_CARMST.ShippingCompanyCd = "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0070_SHIPPINGCOMPANYMST.ShippingCompanyCd"
			+")\n"
			+" left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0020_USERMST"
			+" on("
			+" "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0071_CARMST.WHCD = "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0020_USERMST.WHCD"
			+" and "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0071_CARMST.ShippingCompanyCd = "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0020_USERMST.ShippingCompanyCd"
			+" and "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0071_CARMST.DriverCd = "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0020_USERMST.UserCd"
			+")\n"
			+" left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST"
			+" on("
			+" "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0071_CARMST.WHCD = "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST.WHCD"
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
			A100_DbConnect.DB_CONN("NYANKO");
			ResultSet rset01 = null;
			PreparedStatement stmt01 = null;
			try {
				stmt01 = A100_DbConnect.conn.prepareStatement(sql);
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
				
				rt = B100_RtObjectCreate.B100_RtObjectCreate(rset01,RtSettingCarMstRt());
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
}