import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class M00110AdjustReasonRt{
	/*
	コピペ用
	ArrayList<String> SearchClCd 				= new ArrayList<String>();	//荷主コード
	ArrayList<String> SearchWhCd 				= new ArrayList<String>();	//倉庫コード
	ArrayList<String> SearchAdjustReasonCd		= new ArrayList<String>();	//調整理由コード
	ArrayList<String> SearchAdjustReasonName	= new ArrayList<String>();	//調整理由名
	boolean AllSearch = false;
	
	Object[][] AdjustReasonRt	= M00110AdjustReasonRt.AdjustReasonRt(
																SearchClCd,				//荷主コード
																SearchWhCd,				//倉庫コード
																SearchAdjustReasonCd,	//調整理由コード
																SearchAdjustReasonName,	//調整理由名
																AllSearch);
																
	String GetClCd				= (String)AdjustReasonRt[i][M00110AdjustReasonRt.ColClCd];				//荷主コード
	String GetCLName01			= (String)AdjustReasonRt[i][M00110AdjustReasonRt.ColCLName01];			//荷主名
	String GetWhCd				= (String)AdjustReasonRt[i][M00110AdjustReasonRt.ColWhCd];				//倉庫コード
	String GetWHName			= (String)AdjustReasonRt[i][M00110AdjustReasonRt.ColWHName];			//倉庫名
	String GetAdjustReasonCd	= (String)AdjustReasonRt[i][M00110AdjustReasonRt.ColAdjustReasonCd];	//調整理由コード
	String GetAdjustReasonName	= (String)AdjustReasonRt[i][M00110AdjustReasonRt.ColAdjustReasonName];	//調整理由名
	String GetEntryDate			= (String)AdjustReasonRt[i][M00110AdjustReasonRt.ColEntryDate];			//登録日
	String GetUpdateDate		= (String)AdjustReasonRt[i][M00110AdjustReasonRt.ColUpdateDate];		//更新日
	String GetEntryUser			= (String)AdjustReasonRt[i][M00110AdjustReasonRt.ColEntryUser];			//登録者
	String GetUpdateUser		= (String)AdjustReasonRt[i][M00110AdjustReasonRt.ColUpdateUser];		//更新者
	
	*/
	static final int ColClCd					=  0;	//荷主コード
	static final int ColCLName01				=  1;	//荷主名
	static final int ColWhCd					=  2;	//倉庫コード
	static final int ColWHName					=  3;	//倉庫名
	static final int ColAdjustReasonCd		=  4;	//調整理由コード
	static final int ColAdjustReasonName		=  5;	//調整理由名
	static final int ColEntryDate				=  6;	//登録日
	static final int ColUpdateDate			=  7;	//更新日
	static final int ColEntryUser				=  8;	//登録者
	static final int ColUpdateUser			=  9;	//更新者
	
	public static Object[][] RtAdjustReasonRt() {
		Object[][] RtAdjustReasonRt = {
					 {"ClCd"				,ColClCd				,"String"	,"荷主コード"		,"Key"}
					,{"CLName01"			,ColCLName01			,"String"	,"荷主名"			,""}
					,{"WhCd"				,ColWhCd				,"String"	,"倉庫コード"		,"Key"}
					,{"WHName"				,ColWHName				,"String"	,"倉庫名"			,""}
					,{"AdjustReasonCd"		,ColAdjustReasonCd	,"String"	,"調整理由コード"	,"Key"}
					,{"AdjustReasonName"	,ColAdjustReasonName	,"String"	,"調整理由名"		,""}
					,{"EntryDate"			,ColEntryDate			,"String"	,"登録日"			,""}
					,{"UpdateDate"			,ColUpdateDate		,"String"	,"更新日"			,""}
					,{"EntryUser"			,ColEntryUser			,"String"	,"登録者"			,""}
					,{"UpdateUser"			,ColUpdateUser		,"String"	,"更新者"			,""}
					};
		return RtAdjustReasonRt;
	}
	
	public static Object[][] AdjustReasonRt(
									ArrayList<String> SearchClCd,				//荷主コード
									ArrayList<String> SearchWhCd,				//倉庫コード
									ArrayList<String> SearchAdjustReasonCd,		//調整理由コード
									ArrayList<String> SearchAdjustReasonName,	//調整理由名
									boolean AllSearch) {
		SearchClCd				= B00150ArrayListControl.ArryListStringUniqueList(SearchClCd);				//荷主コード
		SearchWhCd				= B00150ArrayListControl.ArryListStringUniqueList(SearchWhCd);				//倉庫コード
		SearchAdjustReasonCd	= B00150ArrayListControl.ArryListStringUniqueList(SearchAdjustReasonCd);	//調整理由コード
		SearchAdjustReasonName	= B00150ArrayListControl.ArryListStringUniqueList(SearchAdjustReasonName);	//調整理由名
		
		Object [][]rt = new Object[0][RtAdjustReasonRt().length];
		boolean SearchKick = false;
		if(AllSearch) {SearchKick = true;}
		
		String sql = "select "
				+"(WM0020AdjustReason.ClCd) as ClCd,\n"							//荷主コード
				+"(KM0030_CLIENTMST.CLName01) as CLName01,\n"					//荷主名
				+"(WM0020AdjustReason.WhCd) as WhCd,\n"							//倉庫コード
				+"(KM0010_WHMST.WHName) as WHName,\n"							//倉庫名
				+"(WM0020AdjustReason.AdjustReasonCd) as AdjustReasonCd,\n"		//調整理由コード
				+"(WM0020AdjustReason.AdjustReasonName) as AdjustReasonName,\n"	//調整理由名
				+"(WM0020AdjustReason.EntryDate) as EntryDate,\n"				//登録日
				+"(WM0020AdjustReason.UpdateDate) as UpdateDate,\n"				//更新日
				+"(WM0020AdjustReason.EntryUser) as EntryUser,\n"				//登録者
				+"(WM0020AdjustReason.UpdateUser) as UpdateUser\n"				//更新者
				+ " from "+A00000Main.MySqlDefaultSchemaWANKO+".WM0020AdjustReason"
				+ " left outer join "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0030_CLIENTMST"
				+ " on("
				+ " WM0020AdjustReason.ClCd = KM0030_CLIENTMST.cl_cd"
				+ ")\n"
				+ " left outer join "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST"
				+ " on("
				+ " WM0020AdjustReason.WhCd = KM0010_WHMST.WHCD"
				+ ")\n"
				+ " where 1=1";
		
		if(null!=SearchClCd && 0<SearchClCd.size()){							//荷主コード
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchClCd.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + " WM0020AdjustReason.ClCd = ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchWhCd && 0<SearchWhCd.size()){							//倉庫コード
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchWhCd.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + " WM0020AdjustReason.WhCd = ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchAdjustReasonCd && 0<SearchAdjustReasonCd.size()){		//調整理由コード
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchAdjustReasonCd.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + " WM0020AdjustReason.AdjustReasonCd = ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchAdjustReasonName && 0<SearchAdjustReasonName.size()){	//調整理由名
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchAdjustReasonName.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + " WM0020AdjustReason.AdjustReasonName like ?";
			}
			sql = sql + ")";
		}
		
		sql = sql +" order by WM0020AdjustReason.ClCd,WM0020AdjustReason.WhCd,WM0020AdjustReason.AdjustReasonCd";
		
		if(true==SearchKick) {
			A00010DbConnect.DB_CONN("WANKO");
			ResultSet rset01 = null;
			PreparedStatement stmt01 = null;
			try {
				stmt01 = A00010DbConnect.conn.prepareStatement(sql);
				int StmtCount = 0;
				
				if(null!=SearchClCd && 0<SearchClCd.size()){							//荷主コード
					for(int i=0;i<SearchClCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchClCd.get(i)+"");
					}
				}
				if(null!=SearchWhCd && 0<SearchWhCd.size()){							//倉庫コード
					for(int i=0;i<SearchWhCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchWhCd.get(i)+"");;
					}
				}
				if(null!=SearchAdjustReasonCd && 0<SearchAdjustReasonCd.size()){		//調整理由コード
					for(int i=0;i<SearchAdjustReasonCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchAdjustReasonCd.get(i)+"");
					}
				}
				if(null!=SearchAdjustReasonName && 0<SearchAdjustReasonName.size()){	//調整理由名
					for(int i=0;i<SearchAdjustReasonName.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchAdjustReasonName.get(i)+"%");
					}
				}
				
				rset01 = stmt01.executeQuery();
				
				int counter = 0;
				rset01.beforeFirst();
				while (rset01.next()) {
					counter=counter+1;
				}
				
				rt = new Object[counter][RtAdjustReasonRt().length];
				counter = 0;
				rset01.beforeFirst();
				while (rset01.next()) {
					if(null==rset01.getString("ClCd")				){rt[counter][ColClCd]					="";}else{rt[counter][ColClCd]					=rset01.getString("ClCd");}						//荷主コード
					if(null==rset01.getString("CLName01")			){rt[counter][ColCLName01]				="";}else{rt[counter][ColCLName01]				=rset01.getString("CLName01");}					//荷主名
					if(null==rset01.getString("WhCd")				){rt[counter][ColWhCd]					="";}else{rt[counter][ColWhCd]					=rset01.getString("WhCd");}						//倉庫コード
					if(null==rset01.getString("WHName")				){rt[counter][ColWHName]				="";}else{rt[counter][ColWHName]				=rset01.getString("WHName");}					//倉庫名
					if(null==rset01.getString("AdjustReasonCd")		){rt[counter][ColAdjustReasonCd]		="";}else{rt[counter][ColAdjustReasonCd]		=rset01.getString("AdjustReasonCd");}			//調整理由コード
					if(null==rset01.getString("AdjustReasonName")	){rt[counter][ColAdjustReasonName]	="";}else{rt[counter][ColAdjustReasonName]	=rset01.getString("AdjustReasonName");}			//調整理由名
					if(null==rset01.getTimestamp("EntryDate")		){rt[counter][ColEntryDate]			="";}else{rt[counter][ColEntryDate]			=B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("EntryDate"))[1];}	//登録日
					if(null==rset01.getTimestamp("UpdateDate")		){rt[counter][ColUpdateDate]			="";}else{rt[counter][ColUpdateDate]			=B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("UpdateDate"))[1];}	//更新日
					if(null==rset01.getString("EntryUser")			){rt[counter][ColEntryUser]			="";}else{rt[counter][ColEntryUser]			=rset01.getString("EntryUser");}				//登録者
					if(null==rset01.getString("UpdateUser")			){rt[counter][ColUpdateUser]			="";}else{rt[counter][ColUpdateUser]			=rset01.getString("UpdateUser");}				//更新者
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
	
	//在庫調整理由コードを自動採番する
	public static String[] NewAdjustReasonCdGet(int NeedCount) {
		//在庫調整理由マスタ取得
		ArrayList<String> SearchClCd 				= new ArrayList<String>();	//荷主コード
		ArrayList<String> SearchWhCd 				= new ArrayList<String>();	//倉庫コード
		ArrayList<String> SearchAdjustReasonCd		= new ArrayList<String>();	//調整理由コード
		ArrayList<String> SearchAdjustReasonName	= new ArrayList<String>();	//調整理由名
		boolean AllSearch = false;
		
		SearchClCd.add(A00000Main.ClCd);
		SearchWhCd.add(A00000Main.ClWh);
		
		Object[][] AdjustReasonRt	= M00110AdjustReasonRt.AdjustReasonRt(
																	SearchClCd,				//荷主コード
																	SearchWhCd,				//倉庫コード
																	SearchAdjustReasonCd,	//調整理由コード
																	SearchAdjustReasonName,	//調整理由名
																	AllSearch);
		int SpNo = 0;
    	
    	for(int i=0;i<AdjustReasonRt.length;i++) {
    		if(4<=(""+AdjustReasonRt[i][ColAdjustReasonCd]).length() &&"ATAJ".equals((""+AdjustReasonRt[i][ColAdjustReasonCd]).substring(0,4))) {
    			String WST = B00020ToolsTextControl.num_only_String(""+AdjustReasonRt[i][ColAdjustReasonCd]);
    			if("".equals(WST)){WST = "0";}
				int wint = Integer.parseInt(WST);
				if(SpNo<wint) {
					SpNo=wint;
				}
    		}
    	}
    	
    	String[] rt = new String[NeedCount];
    	int MaxCount = 999999999;
    	int wint = MaxCount+1;
    	String SetZero = (""+wint).substring(1,(""+wint).length());
    	for(int i=0;i<NeedCount;i++) {
    		SpNo = SpNo+1;
    		if(MaxCount<SpNo) {
    			rt[i] = "ATAJ"+SpNo;
    		}else {
		    	rt[i] = SetZero+SpNo;
		    	rt[i] = "ATAJ"+rt[i].substring(rt[i].length()-SetZero.length(),rt[i].length());
    		}
    	}
    	
    	return rt;
	}
}