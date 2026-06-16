import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class T100PrintControlRt{
	//印刷済み管理
	/*
	コピペ用
	ArrayList<String> SearchPrintCd			= new ArrayList<String>();	//印刷帳票CD
	ArrayList<String> SearchOkuriNo			= new ArrayList<String>();	//送り状番号等
	ArrayList<String> SearchKey01			= new ArrayList<String>();	//サブキー01
	ArrayList<String> SearchKey02			= new ArrayList<String>();	//サブキー02
	ArrayList<String> SearchKey03			= new ArrayList<String>();	//サブキー03
	ArrayList<String> SearchKey04			= new ArrayList<String>();	//サブキー04
	ArrayList<String> SearchEntryDateStr	= new ArrayList<String>();	//登録日
	ArrayList<String> SearchUpdateDateStr	= new ArrayList<String>();	//更新日
	ArrayList<String> SearchEntryDateEnd	= new ArrayList<String>();	//登録日
	ArrayList<String> SearchUpdateDateEnd	= new ArrayList<String>();	//更新日
	ArrayList<String> SearchEntryUser		= new ArrayList<String>();	//登録者
	ArrayList<String> SearchUpdateUser		= new ArrayList<String>();	//更新者
	boolean AllSearch = false;
	
	Object[][] PrintControlRt	= T100PrintControlRt.PrintControlRt(
												SearchPrintCd,			//印刷帳票CD
												SearchOkuriNo,			//送り状番号等
												SearchKey01,			//サブキー01
												SearchKey02,			//サブキー02
												SearchKey03,			//サブキー03
												SearchKey04,			//サブキー04
												SearchEntryDateStr,		//登録日
												SearchUpdateDateStr,	//更新日
												SearchEntryDateEnd,		//登録日
												SearchUpdateDateEnd,	//更新日
												SearchEntryUser,		//登録者
												SearchUpdateUser,		//更新者
												AllSearch);
												
	String GetPrintCd		= (String)PrintControlRt[i][T100PrintControlRt.ColPrintCd];		//印刷帳票CD
	String GetOkuriNo		= (String)PrintControlRt[i][T100PrintControlRt.ColOkuriNo];		//送り状番号等
	String GetKey01			= (String)PrintControlRt[i][T100PrintControlRt.ColKey01];			//サブキー01
	String GetKey02			= (String)PrintControlRt[i][T100PrintControlRt.ColKey02];			//サブキー02
	String GetKey03			= (String)PrintControlRt[i][T100PrintControlRt.ColKey03];			//サブキー03
	String GetKey04			= (String)PrintControlRt[i][T100PrintControlRt.ColKey04];			//サブキー04
	String GetEntryDate		= (String)PrintControlRt[i][T100PrintControlRt.ColEntryDate];		//登録日
	String GetUpdateDate	= (String)PrintControlRt[i][T100PrintControlRt.ColUpdateDate];	//更新日
	String GetEntryUser		= (String)PrintControlRt[i][T100PrintControlRt.ColEntryUser];		//登録者
	String GetUpdateUser	= (String)PrintControlRt[i][T100PrintControlRt.ColUpdateUser];	//更新者
	
	*/
	
	static final int ColPrintCd		= 0;	//印刷帳票CD
	static final int ColOkuriNo		= 1;	//送り状番号等
	static final int ColKey01			= 2;	//サブキー01
	static final int ColKey02			= 3;	//サブキー02
	static final int ColKey03			= 4;	//サブキー03
	static final int ColKey04			= 5;	//サブキー04
	static final int ColEntryDate		= 6;	//登録日
	static final int ColUpdateDate	= 7;	//更新日
	static final int ColEntryUser		= 8;	//登録者
	static final int ColUpdateUser	= 9;	//更新者
	
	public static Object[][] RtPrintControlRt(){
		Object[][] RtPrintControlRt = {
					 {"PrintCd"		,ColPrintCd		,"String"	,"印刷帳票CD"}
					,{"OkuriNo"		,ColOkuriNo		,"String"	,"送り状番号等"}
					,{"Key01"		,ColKey01			,"String"	,"サブキー01"}
					,{"Key02"		,ColKey02			,"String"	,"サブキー02"}
					,{"Key03"		,ColKey03			,"String"	,"サブキー03"}
					,{"Key04"		,ColKey04			,"String"	,"サブキー04"}
					,{"EntryDate"	,ColEntryDate		,"DateTime"	,"登録日"}
					,{"UpdateDate"	,ColUpdateDate	,"DateTime"	,"更新日"}
					,{"EntryUser"	,ColEntryUser		,"String"	,"登録者"}
					,{"UpdateUser"	,ColUpdateUser	,"String"	,"更新者"}
					};
		
		return RtPrintControlRt;
	}
	
	public static Object[][] PrintControlRt(
			ArrayList<String> SearchPrintCd,		//印刷帳票CD
			ArrayList<String> SearchOkuriNo,		//送り状番号等
			ArrayList<String> SearchKey01,			//サブキー01
			ArrayList<String> SearchKey02,			//サブキー02
			ArrayList<String> SearchKey03,			//サブキー03
			ArrayList<String> SearchKey04,			//サブキー04
			ArrayList<String> SearchEntryDateStr,	//登録日
			ArrayList<String> SearchUpdateDateStr,	//更新日
			ArrayList<String> SearchEntryDateEnd,	//登録日
			ArrayList<String> SearchUpdateDateEnd,	//更新日
			ArrayList<String> SearchEntryUser,		//登録者
			ArrayList<String> SearchUpdateUser,		//更新者
			boolean AllSearch){
		
		SearchPrintCd		= B100ArrayListControl.ArryListStringUniqueList(SearchPrintCd);			//印刷帳票CD
		SearchOkuriNo		= B100ArrayListControl.ArryListStringUniqueList(SearchOkuriNo);			//送り状番号等
		SearchKey01			= B100ArrayListControl.ArryListStringUniqueList(SearchKey01);				//サブキー01
		SearchKey02			= B100ArrayListControl.ArryListStringUniqueList(SearchKey02);				//サブキー02
		SearchKey03			= B100ArrayListControl.ArryListStringUniqueList(SearchKey03);				//サブキー03
		SearchKey04			= B100ArrayListControl.ArryListStringUniqueList(SearchKey04);				//サブキー04
		SearchEntryDateStr	= B100ArrayListControl.ArryListStringUniqueList(SearchEntryDateStr);		//登録日開始
		SearchUpdateDateStr	= B100ArrayListControl.ArryListStringUniqueList(SearchUpdateDateStr);		//更新日開始
		SearchEntryDateEnd	= B100ArrayListControl.ArryListStringUniqueList(SearchEntryDateEnd);		//登録日終了
		SearchUpdateDateEnd	= B100ArrayListControl.ArryListStringUniqueList(SearchUpdateDateEnd);		//更新日終了
		SearchEntryUser		= B100ArrayListControl.ArryListStringUniqueList(SearchEntryUser);			//登録者
		SearchUpdateUser	= B100ArrayListControl.ArryListStringUniqueList(SearchUpdateUser);		//更新者
		
		Object[][] rt = new Object[0][RtPrintControlRt().length];
		boolean SearchKick = false;
		if(AllSearch) {SearchKick = true;}
		String sql = "select "
					+ "(KT0040_PrintControl.PrintCd) as PrintCd,\n"			//印刷帳票CD
					+ "(KT0040_PrintControl.OkuriNo) as OkuriNo,\n"			//送り状番号等
					+ "(KT0040_PrintControl.Key01) as Key01,\n"				//サブキー01
					+ "(KT0040_PrintControl.Key02) as Key02,\n"				//サブキー02
					+ "(KT0040_PrintControl.Key03) as Key03,\n"				//サブキー03
					+ "(KT0040_PrintControl.Key04) as Key04,\n"				//サブキー04
					+ "(KT0040_PrintControl.EntryDate) as EntryDate,\n"		//登録日
					+ "(KT0040_PrintControl.UpdateDate) as UpdateDate,\n"	//更新日
					+ "(KT0040_PrintControl.EntryUser) as EntryUser,\n"		//登録者
					+ "(KT0040_PrintControl.UpdateUser) as UpdateUser\n"	//更新者
					+ " from "+A00000Main.MySqlDefaultSchemaNYANKO+".KT0040_PrintControl\n"
					+ " where 1=1\n"
					;
		
		if(null!=SearchPrintCd && 0<SearchPrintCd.size()){					//印刷帳票CD
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchPrintCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0040_PrintControl.PrintCd = ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchOkuriNo && 0<SearchOkuriNo.size()){					//送り状番号等
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchOkuriNo.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0040_PrintControl.OkuriNo = ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchKey01 && 0<SearchKey01.size()){						//サブキー01
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchKey01.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0040_PrintControl.Key01 = ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchKey02 && 0<SearchKey02.size()){						//サブキー02
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchKey02.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0040_PrintControl.Key02 = ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchKey03 && 0<SearchKey03.size()){						//サブキー03
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchKey03.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0040_PrintControl.Key03 = ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchKey04 && 0<SearchKey04.size()){						//サブキー04
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchKey04.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0040_PrintControl.Key04 = ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchEntryDateStr && 0<SearchEntryDateStr.size()){		//登録日開始
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchEntryDateStr.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0040_PrintControl.EntryDate >= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchUpdateDateStr && 0<SearchUpdateDateStr.size()){		//更新日開始
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchUpdateDateStr.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0040_PrintControl.UpdateDate >= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchEntryDateEnd && 0<SearchEntryDateEnd.size()){		//登録日終了
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchEntryDateEnd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0040_PrintControl.EntryDate <= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchUpdateDateEnd && 0<SearchUpdateDateEnd.size()){		//更新日終了
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchUpdateDateEnd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0040_PrintControl.UpdateDate <= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchEntryUser && 0<SearchEntryUser.size()){				//登録者
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchEntryUser.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0040_PrintControl.EntryUser like ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchUpdateUser && 0<SearchUpdateUser.size()){			//更新者
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchUpdateUser.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0040_PrintControl.UpdateUser like ?";
			}
			sql = sql + ")\n";
		}
		sql = sql + " order by KT0040_PrintControl.PrintCd,KT0040_PrintControl.OkuriNo,KT0040_PrintControl.Key01,KT0040_PrintControl.Key02,KT0040_PrintControl.Key03,KT0040_PrintControl.Key04";
		
		if(SearchKick) {
			A100DbConnect.DB_CONN("NYANKO");
			ResultSet rset01 = null;
			PreparedStatement stmt01 = null;
			try {
				stmt01 = A100DbConnect.conn.prepareStatement(sql);
				int StmtCount = 0;
				
				if(null!=SearchPrintCd && 0<SearchPrintCd.size()){					//印刷帳票CD
					for(int i=0;i<SearchPrintCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchPrintCd.get(i)+"");
					}
				}
				if(null!=SearchOkuriNo && 0<SearchOkuriNo.size()){					//送り状番号等
					for(int i=0;i<SearchOkuriNo.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchOkuriNo.get(i)+"");
					}
				}
				if(null!=SearchKey01 && 0<SearchKey01.size()){						//サブキー01
					for(int i=0;i<SearchKey01.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchKey01.get(i)+"");
					}
				}
				if(null!=SearchKey02 && 0<SearchKey02.size()){						//サブキー02
					for(int i=0;i<SearchKey02.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchKey02.get(i)+"");
					}
				}
				if(null!=SearchKey03 && 0<SearchKey03.size()){						//サブキー03
					for(int i=0;i<SearchKey03.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchKey03.get(i)+"");
					}
				}
				if(null!=SearchKey04 && 0<SearchKey04.size()){						//サブキー04
					for(int i=0;i<SearchKey04.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchKey04.get(i)+"");
					}
				}
				if(null!=SearchEntryDateStr && 0<SearchEntryDateStr.size()){		//登録日開始
					for(int i=0;i<SearchEntryDateStr.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchEntryDateStr.get(i)+"");
					}
				}
				if(null!=SearchUpdateDateStr && 0<SearchUpdateDateStr.size()){		//更新日開始
					for(int i=0;i<SearchUpdateDateStr.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchUpdateDateStr.get(i)+"");
					}
				}
				if(null!=SearchEntryDateEnd && 0<SearchEntryDateEnd.size()){		//登録日終了
					for(int i=0;i<SearchEntryDateEnd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchEntryDateEnd.get(i)+"");
					}
				}
				if(null!=SearchUpdateDateEnd && 0<SearchUpdateDateEnd.size()){		//更新日終了
					for(int i=0;i<SearchUpdateDateEnd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchUpdateDateEnd.get(i)+"");
					}
				}
				if(null!=SearchEntryUser && 0<SearchEntryUser.size()){				//登録者
					for(int i=0;i<SearchEntryUser.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchEntryUser.get(i)+"%");
					}
				}
				if(null!=SearchUpdateUser && 0<SearchUpdateUser.size()){			//更新者
					for(int i=0;i<SearchUpdateUser.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchUpdateUser.get(i)+"%");
					}
				}
		
				rset01 = stmt01.executeQuery();
				
				int counter = 0;
				rset01.beforeFirst();
				while (rset01.next()) {
					counter=counter+1;
				}
				rt = new Object[counter][RtPrintControlRt().length];
				counter = 0;
				rset01.beforeFirst();
				while (rset01.next()) {
					if(null==rset01.getString("PrintCd"			)){rt[counter][ColPrintCd]			="";}else{rt[counter][ColPrintCd]		=rset01.getString("PrintCd");}			//印刷帳票CD
					if(null==rset01.getString("OkuriNo"			)){rt[counter][ColOkuriNo]			="";}else{rt[counter][ColOkuriNo]		=rset01.getString("OkuriNo");}			//送り状番号等
					if(null==rset01.getString("Key01"			)){rt[counter][ColKey01]			="";}else{rt[counter][ColKey01]		=rset01.getString("Key01");}			//サブキー01
					if(null==rset01.getString("Key02"			)){rt[counter][ColKey02]			="";}else{rt[counter][ColKey02]		=rset01.getString("Key02");}			//サブキー02
					if(null==rset01.getString("Key03"			)){rt[counter][ColKey03]			="";}else{rt[counter][ColKey03]		=rset01.getString("Key03");}			//サブキー03
					if(null==rset01.getString("Key04"			)){rt[counter][ColKey04]			="";}else{rt[counter][ColKey04]		=rset01.getString("Key04");}			//サブキー04
					if(null==rset01.getTimestamp("EntryDate"	)){rt[counter][ColEntryDate]		="";}else{rt[counter][ColEntryDate]	=B100DateTimeControl.dtmString2(rset01.getTimestamp("EntryDate"))[1];}	//登録日
					if(null==rset01.getTimestamp("UpdateDate"	)){rt[counter][ColUpdateDate]		="";}else{rt[counter][ColUpdateDate]	=B100DateTimeControl.dtmString2(rset01.getTimestamp("UpdateDate"))[1];}	//更新日
					if(null==rset01.getString("EntryUser"		)){rt[counter][ColEntryUser]		="";}else{rt[counter][ColEntryUser]	=rset01.getString("EntryUser");}		//登録者
					if(null==rset01.getString("UpdateUser"		)){rt[counter][ColUpdateUser]		="";}else{rt[counter][ColUpdateUser]	=rset01.getString("UpdateUser");}		//更新者
					
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
			A100DbConnect.close();
		}
		
		
		return rt;
	}
}