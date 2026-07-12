import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class M100_LocationMstRt{
	/*
	コピペ用
	ArrayList<String> SearchClCd 	= new ArrayList<String>();	//荷主コード
	ArrayList<String> SearchWhCd 	= new ArrayList<String>();	//倉庫コード
	ArrayList<String> SearchLoc 	= new ArrayList<String>();	//ロケーション
	ArrayList<String> SearchLocName = new ArrayList<String>();	//ロケーション名
	ArrayList<String> SearchType 	= new ArrayList<String>();	//ロケタイプ
	boolean LocExactMatch = false;	//ロケーション完全一致
	boolean AllSearch = false;
	
	Object[][] LocationMstRt = M100_LocationMstRt.LocationMstRt(
			SearchClCd,		//荷主コード
			SearchWhCd,		//倉庫コード
			SearchLoc,		//ロケーション
			SearchLocName,	//ロケーション名
			SearchType,		//ロケタイプ
			LocExactMatch,	//ロケーション完全一致
			AllSearch);
			
	String GetClCd			= (String)LocationMstRt[i][M100_LocationMstRt.ColClCd];		//荷主コード
	String GetCLName01		= (String)LocationMstRt[i][M100_LocationMstRt.ColCLName01];	//荷主表記名
	String GetWhCd			= (String)LocationMstRt[i][M100_LocationMstRt.ColWhCd];		//倉庫コード
	String GetWHName		= (String)LocationMstRt[i][M100_LocationMstRt.ColWHName];		//拠点倉庫名
	String GetLoc			= (String)LocationMstRt[i][M100_LocationMstRt.ColLoc];			//ロケーション
	String GetLocName		= (String)LocationMstRt[i][M100_LocationMstRt.ColLocName];		//ロケーション名
	int GetType				= (int)LocationMstRt[i][M100_LocationMstRt.ColType];			//ロケタイプ
	String GetEntryDate		= (String)LocationMstRt[i][M100_LocationMstRt.ColEntryDate];	//登録日
	String GetUpdateDate	= (String)LocationMstRt[i][M100_LocationMstRt.ColUpdateDate];	//更新日
	String GetEntryUser		= (String)LocationMstRt[i][M100_LocationMstRt.ColEntryUser];	//登録者
	String GetUpdateUser	= (String)LocationMstRt[i][M100_LocationMstRt.ColUpdateUser];	//更新者
	*/
	
	//戻り値カラム
	static final int ColLoc			= (int) 0;	//ロケーション
	static final int ColLocName		= (int) 1;	//ロケーション名
	static final int ColType			= (int) 2;	//ロケタイプ
	static final int ColClCd			= (int) 3;	//荷主コード
	static final int ColCLName01		= (int) 4;	//荷主表記名
	static final int ColWhCd			= (int) 5;	//倉庫コード
	static final int ColWHName			= (int) 6;	//拠点倉庫名
	static final int ColEntryDate		= (int) 7;	//登録日
	static final int ColUpdateDate	= (int) 8;	//更新日
	static final int ColEntryUser		= (int) 9;	//登録者
	static final int ColUpdateUser	= (int)10;	//更新者
	
	public static Object[][] RtSettingLocationMstRt(){
		Object[][] RtSettingLocationMstRt = {
				 {"ClCd"		,ColClCd			,"String"	,"荷主コード"		,"Key"}
				,{"CLName01"	,ColCLName01		,"String"	,"荷主表記名"		,""}
				,{"WhCd"		,ColWhCd			,"String"	,"倉庫コード"		,"Key"}
				,{"WHName"		,ColWHName			,"String"	,"拠点倉庫名"		,""}
				,{"Loc"			,ColLoc			,"String"	,"ロケーション"		,"Key"}
				,{"LocName"		,ColLocName		,"String"	,"ロケーション名"	,""}
				,{"LocType"		,ColType			,"int"		,"ロケタイプ"		,""}
				,{"EntryDate"	,ColEntryDate		,"DateTime"	,"登録日"			,""}
				,{"UpdateDate"	,ColUpdateDate	,"DateTime"	,"更新日"			,""}
				,{"EntryUser"	,ColEntryUser		,"String"	,"登録者"			,""}
				,{"UpdateUser"	,ColUpdateUser	,"String"	,"更新者"			,""}
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
		
		SearchClCd		= B100_ArrayListControl.ArryListStringUniqueList(SearchClCd);		//荷主コード
		SearchWhCd		= B100_ArrayListControl.ArryListStringUniqueList(SearchWhCd);		//倉庫コード
		SearchLoc		= B100_ArrayListControl.ArryListStringUniqueList(SearchLoc);		//ロケーション
		SearchLocName	= B100_ArrayListControl.ArryListStringUniqueList(SearchLocName);	//ロケーション名
		SearchType		= B100_ArrayListControl.ArryListStringUniqueList(SearchType);		//ロケタイプ
		
		Object[][] rt = new Object[0][RtSettingLocationMstRt().length];
		boolean SearchKick = false;
		if(AllSearch) {SearchKick = true;}
				
		String sql = " select \n"
				+"(WM0010LOCATIONMST.ClCd) as ClCd,\n"				//荷主コード
				+"(KM0030_CLIENTMST.CLName01) as CLName01,\n"		//荷主表記名
				+"(WM0010LOCATIONMST.WhCd) as WhCd,\n"				//倉庫コード
				+"(KM0010_WHMST.WHName) as WHName,\n"				//拠点倉庫名
				+"(WM0010LOCATIONMST.Loc) as Loc,\n"				//ロケーション
				+"(WM0010LOCATIONMST.LocName) as LocName,\n"		//ロケーション名
				+"(WM0010LOCATIONMST.EntryDate) as EntryDate,\n"	//登録日
				+"(WM0010LOCATIONMST.UpdateDate) as UpdateDate,\n"	//更新日
				+"(WM0010LOCATIONMST.EntryUser) as EntryUser,\n"	//登録者
				+"(WM0010LOCATIONMST.UpdateUser) as UpdateUser,\n"	//更新者
				+"(WM0010LOCATIONMST.LocType) as LocType\n"				//ロケタイプ　0:通常　1:保管　8:入荷時　9:引当禁止
				+" from "+A00000_Main.MySqlDefaultSchemaWANKO+".WM0010LOCATIONMST \n"
				+ " left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0030_CLIENTMST"
				+ " on("
				+ " WM0010LOCATIONMST.ClCd = KM0030_CLIENTMST.cl_cd"
				+ ")\n"
				+ " left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST"
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
				sql = sql + " WM0010LOCATIONMST.LocType = ?";
			}
			sql = sql + ")";
		}
		
		sql = sql + " order by WM0010LOCATIONMST.ClCd,WM0010LOCATIONMST.WhCd,WM0010LOCATIONMST.Loc";
		//System.out.println(sql);
		if(SearchKick) {
			A100_DbConnect.DB_CONN("NYANKO");
			ResultSet rset01 = null;
			PreparedStatement stmt01 = null;
			try {
				stmt01 = A100_DbConnect.conn.prepareStatement(sql);
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
				
				rt = B100_RtObjectCreate.B100_RtObjectCreate(rset01,RtSettingLocationMstRt());
				
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