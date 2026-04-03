import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class M00090LocationMstRt{
	/*
	コピペ用
	ArrayList<String> SearchClCd 	= new ArrayList<String>();	//荷主コード
	ArrayList<String> SearchWhCd 	= new ArrayList<String>();	//倉庫コード
	ArrayList<String> SearchLoc 	= new ArrayList<String>();	//ロケーション
	ArrayList<String> SearchLocName = new ArrayList<String>();	//ロケーション名
	ArrayList<String> SearchType 	= new ArrayList<String>();	//ロケタイプ
	boolean LocExactMatch = false;	//ロケーション完全一致
	boolean AllSearch = false;
	
	Object[][] LocationMstRt = M00090LocationMstRt.LocationMstRt(
			SearchClCd,		//荷主コード
			SearchWhCd,		//倉庫コード
			SearchLoc,		//ロケーション
			SearchLocName,	//ロケーション名
			SearchType,		//ロケタイプ
			LocExactMatch,	//ロケーション完全一致
			AllSearch);
			
	String GetClCd			= (String)LocationMstRt[i][M00090LocationMstRt.ColClCd];		//荷主コード
	String GetCLName01		= (String)LocationMstRt[i][M00090LocationMstRt.ColCLName01];	//荷主名1
	String GetWhCd			= (String)LocationMstRt[i][M00090LocationMstRt.ColWhCd];		//倉庫コード
	String GetWHName		= (String)LocationMstRt[i][M00090LocationMstRt.ColWHName];		//拠点倉庫名
	String GetLoc			= (String)LocationMstRt[i][M00090LocationMstRt.ColLoc];			//ロケーション
	String GetLocName		= (String)LocationMstRt[i][M00090LocationMstRt.ColLocName];		//ロケーション名
	int GetType				= (int)LocationMstRt[i][M00090LocationMstRt.ColType];			//ロケタイプ
	String GetEntryDate		= (String)LocationMstRt[i][M00090LocationMstRt.ColEntryDate];	//登録日
	String GetUpdateDate	= (String)LocationMstRt[i][M00090LocationMstRt.ColUpdateDate];	//更新日
	String GetEntryUser		= (String)LocationMstRt[i][M00090LocationMstRt.ColEntryUser];	//登録者
	String GetUpdateUser	= (String)LocationMstRt[i][M00090LocationMstRt.ColUpdateUser];	//更新者
	
	*/
	
	//戻り値カラム
	static int ColClCd			= (int) 0;	//荷主コード
	static int ColCLName01		= (int) 1;	//荷主名1
	static int ColWhCd			= (int) 2;	//倉庫コード
	static int ColWHName		= (int) 3;	//拠点倉庫名
	static int ColLoc			= (int) 4;	//ロケーション
	static int ColLocName		= (int) 5;	//ロケーション名
	static int ColType			= (int) 6;	//ロケタイプ
	static int ColEntryDate	= (int) 7;	//登録日
	static int ColUpdateDate	= (int) 8;	//更新日
	static int ColEntryUser	= (int) 9;	//登録者
	static int ColUpdateUser	= (int)10;	//更新者
	
	public static Object[][] RtSettingLocationMstRt(){
		Object[][] RtSettingLocationMstRt = {
				 {"ClCd"		,ColClCd			,"String"	,"荷主コード"}
				,{"CLName01"	,ColCLName01		,"String"	,"荷主名1"}
				,{"WhCd"		,ColWhCd			,"String"	,"倉庫コード"}
				,{"WHName"		,ColWHName			,"String"	,"拠点倉庫名"}
				,{"Loc"			,ColLoc			,"String"	,"ロケーション"}
				,{"LocName"		,ColLocName		,"String"	,"ロケーション名"}
				,{"Type"		,ColType			,"int"		,"ロケタイプ"}
				,{"EntryDate"	,ColEntryDate		,"String"	,"登録日"}
				,{"UpdateDate"	,ColUpdateDate	,"String"	,"更新日"}
				,{"EntryUser"	,ColEntryUser		,"String"	,"登録者"}
				,{"UpdateUser"	,ColUpdateUser	,"String"	,"更新者"}
				};
		
		return RtSettingLocationMstRt;
	}
	
	public static Object[][] LocationMstRt(
			ArrayList<String> SearchClCd,		//荷主コード
			ArrayList<String> SearchWhCd,		//倉庫コード
			ArrayList<String> SearchLoc,		//ロケーション
			ArrayList<String> SearchLocName,	//ロケーション名
			ArrayList<String> SearchType,		//ロケタイプ
			boolean LocExactMatch,	//ロケーション完全一致
			boolean AllSearch){
		
		SearchClCd		= B00150ArrayListControl.ArryListStringUniqueList(SearchClCd);		//荷主コード
		SearchWhCd		= B00150ArrayListControl.ArryListStringUniqueList(SearchWhCd);		//倉庫コード
		SearchLoc		= B00150ArrayListControl.ArryListStringUniqueList(SearchLoc);		//ロケーション
		SearchLocName	= B00150ArrayListControl.ArryListStringUniqueList(SearchLocName);	//ロケーション名
		SearchType		= B00150ArrayListControl.ArryListStringUniqueList(SearchType);		//ロケタイプ
		
		Object[][] rt = new Object[0][11];
		boolean SearchKick = false;
		if(AllSearch) {SearchKick = true;}
		
		String sql = " select \n"
				+"(WM0010LOCATIONMST.ClCd) as ClCd,\n"				//荷主コード
				+"(KM0030_CLIENTMST.CLName01) as CLName01,\n"		//荷主名1
				+"(WM0010LOCATIONMST.WhCd) as WhCd,\n"				//倉庫コード
				+"(KM0010_WHMST.WHName) as WHName,\n"				//拠点倉庫名
				+"(WM0010LOCATIONMST.Loc) as Loc,\n"				//ロケーション
				+"(WM0010LOCATIONMST.LocName) as LocName,\n"		//ロケーション名
				+"(WM0010LOCATIONMST.EntryDate) as EntryDate,\n"	//登録日
				+"(WM0010LOCATIONMST.UpdateDate) as UpdateDate,\n"	//更新日
				+"(WM0010LOCATIONMST.EntryUser) as EntryUser,\n"	//登録者
				+"(WM0010LOCATIONMST.UpdateUser) as UpdateUser,\n"	//更新者
				+"(WM0010LOCATIONMST.Type) as Type\n"				//ロケタイプ　0:通常　1:保管　8:入荷時　9:引当禁止
				+" from "+A00000Main.MySqlDefaultSchemaWANKO+".WM0010LOCATIONMST \n"
				+ " left outer join "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0030_CLIENTMST"
				+ " on("
				+ " WM0010LOCATIONMST.ClCd = KM0030_CLIENTMST.cl_cd"
				+ ")\n"
				+ " left outer join "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST"
				+ " on("
				+ " WM0010LOCATIONMST.WhCd = KM0010_WHMST.WHCD"
				+ ")\n"
				+" where 1=1 \n";
		
		if(null!=SearchClCd&&0<SearchClCd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchClCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WM0010LOCATIONMST.ClCd = ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchWhCd&&0<SearchWhCd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchWhCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WM0010LOCATIONMST.WhCd = ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchLoc&&0<SearchLoc.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchLoc.size();i++){
				if(0<i){sql = sql + " or ";}
				if(LocExactMatch) {
					sql = sql + " WM0010LOCATIONMST.Loc = ?";
				}else {
					sql = sql + " WM0010LOCATIONMST.Loc like ?";
				}
			}
			sql = sql + ")";
		}
		
		if(null!=SearchLocName&&0<SearchLocName.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchLocName.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WM0010LOCATIONMST.LocName like ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchType&&0<SearchType.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchType.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WM0010LOCATIONMST.Type = ?";
			}
			sql = sql + ")";
		}
		
		sql = sql + " order by WM0010LOCATIONMST.ClCd,WM0010LOCATIONMST.WhCd,WM0010LOCATIONMST.Loc";
		//System.out.println(sql);
		if(SearchKick) {
			A00010DbConnect.DB_CONN("NYANKO");
			ResultSet rset01 = null;
			PreparedStatement stmt01 = null;
			try {
				stmt01 = A00010DbConnect.conn.prepareStatement(sql);
				int StmtCount = 0;
				
				if(null!=SearchClCd&&0<SearchClCd.size()){
					for(int i=0;i<SearchClCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchClCd.get(i)+"");
					}
				}
				
				if(null!=SearchWhCd&&0<SearchWhCd.size()){
					for(int i=0;i<SearchWhCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchWhCd.get(i)+"");
					}
				}
				
				if(null!=SearchLoc&&0<SearchLoc.size()){
					for(int i=0;i<SearchLoc.size();i++){
						if(LocExactMatch) {
							StmtCount = StmtCount+1;
							stmt01.setString(StmtCount, ""+SearchLoc.get(i)+"");
						}else {
							StmtCount = StmtCount+1;
							stmt01.setString(StmtCount, ""+SearchLoc.get(i)+"%");
						}
					}
				}
				
				if(null!=SearchLocName&&0<SearchLocName.size()){
					for(int i=0;i<SearchLocName.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchLocName.get(i)+"%");
					}
				}
				
				if(null!=SearchType&&0<SearchType.size()){
					for(int i=0;i<SearchType.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchType.get(i)+"");
					}
				}
				rset01 = stmt01.executeQuery();
				
				int counter = 0;
				rset01.beforeFirst();
				while (rset01.next()) {
					counter=counter+1;
				}
				rt = new Object[counter][11];
				counter = 0;
				rset01.beforeFirst();
				while (rset01.next()) {
					if(null==rset01.getString("ClCd")){				rt[counter][ColClCd]			="";}else{rt[counter][ColClCd]			=rset01.getString("ClCd");}				//荷主コード
					if(null==rset01.getString("CLName01")){			rt[counter][ColCLName01]		="";}else{rt[counter][ColCLName01]		=rset01.getString("CLName01");}			//荷主名1
					if(null==rset01.getString("WhCd")){				rt[counter][ColWhCd]			="";}else{rt[counter][ColWhCd]			=rset01.getString("WhCd");}				//倉庫コード
					if(null==rset01.getString("WHName")){			rt[counter][ColWHName]			="";}else{rt[counter][ColWHName]		=rset01.getString("WHName");}			//拠点倉庫名
					if(null==rset01.getString("Loc")){				rt[counter][ColLoc]			="";}else{rt[counter][ColLoc]			=rset01.getString("Loc");}				//ロケーション
					if(null==rset01.getString("LocName")){			rt[counter][ColLocName]		="";}else{rt[counter][ColLocName]		=rset01.getString("LocName");}			//ロケーション名
					rt[counter][ColType]=rset01.getInt("Type");																													//ロケタイプ　0:通常　1:保管　8:入荷時　9:引当禁止
					if(null==rset01.getTimestamp("EntryDate")){		rt[counter][ColEntryDate]		="";}else{rt[counter][ColEntryDate]	=B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("EntryDate"))[1];}			//登録日
					if(null==rset01.getTimestamp("UpdateDate")){	rt[counter][ColUpdateDate]	="";}else{rt[counter][ColUpdateDate]	=B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("UpdateDate"))[1];}			//更新日
					if(null==rset01.getString("EntryUser")){		rt[counter][ColEntryUser]		="";}else{rt[counter][ColEntryUser]	=rset01.getString("EntryUser");}		//登録者
					if(null==rset01.getString("UpdateUser")){		rt[counter][ColUpdateUser]	="";}else{rt[counter][ColUpdateUser]	=rset01.getString("UpdateUser");}		//更新者
					
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