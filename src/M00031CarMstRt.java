import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
public class M00031CarMstRt{
	//戻り値カラム
	public static Object[][] RtSettingCarMstRt(){
		Object[][] RtSettingCarMstRt = {
				 {"WHCD"					,(int) 0	,"String"	,"担当倉庫"}
				,{"ShippingCompanyCd"		,(int) 1	,"String"	,"運送会社CD"}
				,{"ShippingCompanyName01"	,(int) 2	,"String"	,"運送会社名1"}
				,{"ShippingCompanyName02"	,(int) 3	,"String"	,"運送会社名2"}
				,{"ShippingCompanyName03"	,(int) 4	,"String"	,"運送会社名3"}
				,{"CarCd"					,(int) 5	,"String"	,"車輛CD"}
				,{"CarName01"				,(int) 6	,"String"	,"車輛名01"}
				,{"CarName02"				,(int) 7	,"String"	,"車輛名02"}
				,{"CarName03"				,(int) 8	,"String"	,"車輛名03"}
				,{"DriverCd"				,(int) 9	,"String"	,"乗務員CD"}
				,{"UserName01"				,(int)10	,"String"	,"ユーザー名1"}
				,{"UserName02"				,(int)11	,"String"	,"ユーザー名2"}
				,{"UserName03"				,(int)12	,"String"	,"ユーザー名3"}
				,{"PTMSCD"					,(int)13	,"String"	,"基幹システム車輛コード"}
				,{"EntryDate"				,(int)14	,"String"	,"データ登録日時"}
				,{"UpdateDate"				,(int)15	,"String"	,"データ更新日時"}
				,{"EntryUser"				,(int)16	,"String"	,"登録者コード"}
				,{"UpdateUser"				,(int)17	,"String"	,"更新者コード"}
				,{"DelFg"					,(int)18	,"int"		,"削除フラグ"}
				,{"WHName"					,(int)19	,"String"	,"倉庫名"}
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
				String Wst = ""+SearchWHCD.get(i);
				sql = sql + "KM0071_CARMST.WHCD = '"+Wst+"'";
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
				String Wst = ""+SearchShippingCompanyCd.get(i);
				sql = sql + "KM0071_CARMST.ShippingCompanyCd = '"+Wst+"'";
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
				String Wst = ""+SearchCarCd.get(i);
				sql = sql + "KM0071_CARMST.CarCd = '"+Wst+"'";
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
				String Wst = ""+SearchCarName.get(i);
				sql = sql + "KM0071_CARMST.CarName01 like '%"+Wst+"%'";
				sql = sql + " or KM0071_CARMST.CarName02 like '%"+Wst+"%'";
				sql = sql + " or KM0071_CARMST.CarName03 like '%"+Wst+"%'";
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
				String Wst = ""+SearchDelFg.get(i);
				sql = sql + "KM0071_CARMST.DelFg = '"+Wst+"'";
			}
			sql = sql +	")";
		}
		
		sql = sql + " order by KM0071_CARMST.WHCD,KM0071_CARMST.ShippingCompanyCd,KM0071_CARMST.CarCd";
		
		if(SearchKick) {
			A00010DbConnect.DB_CONN("NYANKO");
			ResultSet rset01 = null;
			Statement stmt01 = null;
			try {
				stmt01 = A00010DbConnect.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					      ResultSet.CONCUR_UPDATABLE);
				rset01 = stmt01.executeQuery(sql);
				int counter = 0;
				rset01.beforeFirst();
				while (rset01.next()) {
					counter=counter+1;
				}

				rt = new Object[counter][20];
				counter = 0;
				rset01.beforeFirst();
				while (rset01.next()) {
					if(null==rset01.getString("WHCD")){rt[counter][0] = "";}else{rt[counter][0] = rset01.getString("WHCD");}									//担当倉庫
					if(null==rset01.getString("ShippingCompanyCd")){rt[counter][1] = "";}else{rt[counter][1] = rset01.getString("ShippingCompanyCd");}			//運送会社CD
					if(null==rset01.getString("ShippingCompanyName01")){rt[counter][2] = "";}else{rt[counter][2] = rset01.getString("ShippingCompanyName01");}	//運送会社名1
					if(null==rset01.getString("ShippingCompanyName02")){rt[counter][3] = "";}else{rt[counter][3] = rset01.getString("ShippingCompanyName02");}	//運送会社名2
					if(null==rset01.getString("ShippingCompanyName03")){rt[counter][4] = "";}else{rt[counter][4] = rset01.getString("ShippingCompanyName03");}	//運送会社名3
					if(null==rset01.getString("CarCd")){rt[counter][5] = "";}else{rt[counter][5] = rset01.getString("CarCd");}						//車輛CD
					if(null==rset01.getString("CarName01")){rt[counter][6] = "";}else{rt[counter][6] = rset01.getString("CarName01");}				//車輛名01
					if(null==rset01.getString("CarName02")){rt[counter][7] = "";}else{rt[counter][7] = rset01.getString("CarName02");}				//車輛名02
					if(null==rset01.getString("CarName03")){rt[counter][8] = "";}else{rt[counter][8] = rset01.getString("CarName03");}				//車輛名03
					if(null==rset01.getString("DriverCd")){rt[counter][9] = "";}else{rt[counter][9] = rset01.getString("DriverCd");}				//乗務員CD
					if(null==rset01.getString("UserName01")){rt[counter][10] = "";}else{rt[counter][10] = rset01.getString("UserName01");}			//ユーザー名1
					if(null==rset01.getString("UserName02")){rt[counter][11] = "";}else{rt[counter][11] = rset01.getString("UserName02");}			//ユーザー名2
					if(null==rset01.getString("UserName03")){rt[counter][12] = "";}else{rt[counter][12] = rset01.getString("UserName03");}			//ユーザー名3
					if(null==rset01.getString("PTMSCD")){rt[counter][13] = "";}else{rt[counter][13] = rset01.getString("PTMSCD");}			//基幹システム車輛コード
					if(null==rset01.getTimestamp("EntryDate")){rt[counter][14] = "";}else{rt[counter][14] = B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("EntryDate"))[1];}	//データ登録日時
					if(null==rset01.getTimestamp("UpdateDate")){rt[counter][15] = "";}else{rt[counter][15] = B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("UpdateDate"))[1];}	//データ更新日時
					if(null==rset01.getString("EntryUser")){rt[counter][16] = "";}else{rt[counter][16] = rset01.getString("EntryUser");}	//登録者コード
					if(null==rset01.getString("UpdateUser")){rt[counter][17] = "";}else{rt[counter][17] = rset01.getString("UpdateUser");}	//更新者コード
					rt[counter][18] = rset01.getInt("DelFg");	//削除フラグ
					if(null==rset01.getString("WHName")){rt[counter][19]="";}else{rt[counter][19]=rset01.getString("WHName");}												//倉庫名
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