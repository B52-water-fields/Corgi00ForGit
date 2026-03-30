import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class M00030ShippingCompanyMstRt{
	/*
	コピペ用
	ArrayList<String> SearchShippingCompanyCd = new ArrayList<String>();
	ArrayList<String> SearchCompanyName = new ArrayList<String>();
	ArrayList<String> SearchPost = new ArrayList<String>();
	ArrayList<String> SearchAdd = new ArrayList<String>();
	ArrayList<String> SearchTel = new ArrayList<String>();
	ArrayList<String> SearchFax = new ArrayList<String>();
	ArrayList<String> SearchMail = new ArrayList<String>();
	ArrayList<String> SearchCom = new ArrayList<String>();
	boolean AllSearch = false;
	
	Object[][] ShippingCompanyMstRt = M00030ShippingCompanyMstRt.ShippingCompanyMstRt(
			SearchShippingCompanyCd,
			SearchCompanyName,
			SearchPost,
			SearchAdd,
			SearchTel,
			SearchFax,
			SearchMail,
			SearchCom,
			AllSearch);
			
	String GetShippingCompanyCd		=(String)ShippingCompanyMstRt[i][M00030ShippingCompanyMstRt.ColShippingCompanyCd];		//運送会社CD
	String GetShippingCompanyName01	=(String)ShippingCompanyMstRt[i][M00030ShippingCompanyMstRt.ColShippingCompanyName01];	//運送会社名1
	String GetShippingCompanyName02	=(String)ShippingCompanyMstRt[i][M00030ShippingCompanyMstRt.ColShippingCompanyName02];	//運送会社名2
	String GetShippingCompanyName03	=(String)ShippingCompanyMstRt[i][M00030ShippingCompanyMstRt.ColShippingCompanyName03];	//運送会社名3
	String GetPost					=(String)ShippingCompanyMstRt[i][M00030ShippingCompanyMstRt.ColPost];					//運送会社郵便
	String GetAdd01					=(String)ShippingCompanyMstRt[i][M00030ShippingCompanyMstRt.ColAdd01];					//運送会社住所1
	String GetAdd02					=(String)ShippingCompanyMstRt[i][M00030ShippingCompanyMstRt.ColAdd02];					//運送会社住所2
	String GetAdd03					=(String)ShippingCompanyMstRt[i][M00030ShippingCompanyMstRt.ColAdd03];					//運送会社住所3
	String GetTel					=(String)ShippingCompanyMstRt[i][M00030ShippingCompanyMstRt.ColTel];					//運送会社電話
	String GetFax					=(String)ShippingCompanyMstRt[i][M00030ShippingCompanyMstRt.ColFax];					//運送会社FAX
	String GetMail					=(String)ShippingCompanyMstRt[i][M00030ShippingCompanyMstRt.ColMail];					//運送会社MAIL
	String GetCom01					=(String)ShippingCompanyMstRt[i][M00030ShippingCompanyMstRt.ColCom01];					//コメント1
	String GetCom02					=(String)ShippingCompanyMstRt[i][M00030ShippingCompanyMstRt.ColCom02];					//コメント2
	String GetCom03					=(String)ShippingCompanyMstRt[i][M00030ShippingCompanyMstRt.ColCom03];					//コメント3
	int GetShimeDate				=(String)ShippingCompanyMstRt[i][M00030ShippingCompanyMstRt.ColShimeDate];				//締日
	int GetShimeBasis				=(String)ShippingCompanyMstRt[i][M00030ShippingCompanyMstRt.ColShimeBasis];				//請求基準
	String GetEntryDate				=(String)ShippingCompanyMstRt[i][M00030ShippingCompanyMstRt.ColEntryDate];				//データ登録日時
	String GetUpdateDate			=(String)ShippingCompanyMstRt[i][M00030ShippingCompanyMstRt.ColUpdateDate];				//データ更新日時
	String GetEntryUser				=(String)ShippingCompanyMstRt[i][M00030ShippingCompanyMstRt.ColEntryUser];				//登録者コード
	String GetUpdateUser			=(String)ShippingCompanyMstRt[i][M00030ShippingCompanyMstRt.ColUpdateUser];				//更新者コード
	String GetPTMSCD				=(String)ShippingCompanyMstRt[i][M00030ShippingCompanyMstRt.ColPTMSCD];					//基幹システム傭車コード
	String GetExportDataType		=(String)ShippingCompanyMstRt[i][M00030ShippingCompanyMstRt.ColExportDataType];			//データ抽出タイプ
	
	*/
	//戻り値カラム
	static int ColShippingCompanyCd		= (int) 0;	//運送会社CD
	static int ColShippingCompanyName01	= (int) 1;	//運送会社名1
	static int ColShippingCompanyName02	= (int) 2;	//運送会社名2
	static int ColShippingCompanyName03	= (int) 3;	//運送会社名3
	static int ColPost						= (int) 4;	//運送会社郵便
	static int ColAdd01					= (int) 5;	//運送会社住所1
	static int ColAdd02					= (int) 6;	//運送会社住所2
	static int ColAdd03					= (int) 7;	//運送会社住所3
	static int ColTel						= (int) 8;	//運送会社電話
	static int ColFax						= (int) 9;	//運送会社FAX
	static int ColMail						= (int)10;	//運送会社MAIL
	static int ColCom01					= (int)11;	//コメント1
	static int ColCom02					= (int)12;	//コメント2
	static int ColCom03					= (int)13;	//コメント3
	static int ColShimeDate				= (int)14;	//締日
	static int ColShimeBasis				= (int)15;	//請求基準
	static int ColEntryDate				= (int)16;	//データ登録日時
	static int ColUpdateDate				= (int)17;	//データ更新日時
	static int ColEntryUser				= (int)18;	//登録者コード
	static int ColUpdateUser				= (int)19;	//更新者コード
	static int ColPTMSCD					= (int)20;	//基幹システム傭車コード
	static int ColExportDataType			= (int)21;	//データ抽出タイプ
	
	public static Object[][] RtSettingShippingCompanyMstRt(){
		Object[][] RtSettingShippingCompanyMstRt = {
				 {"ShippingCompanyCd"		,ColShippingCompanyCd		,"String"	,"運送会社CD"}
				,{"ShippingCompanyName01"	,ColShippingCompanyName01	,"String"	,"運送会社名1"}
				,{"ShippingCompanyName02"	,ColShippingCompanyName02	,"String"	,"運送会社名2"}
				,{"ShippingCompanyName03"	,ColShippingCompanyName03	,"String"	,"運送会社名3"}
				,{"Post"					,ColPost						,"String"	,"運送会社郵便"}
				,{"Add01"					,ColAdd01						,"String"	,"運送会社住所1"}
				,{"Add02"					,ColAdd02						,"String"	,"運送会社住所2"}
				,{"Add03"					,ColAdd03						,"String"	,"運送会社住所3"}
				,{"Tel"						,ColTel						,"String"	,"運送会社電話"}
				,{"Fax"						,ColFax						,"String"	,"運送会社FAX"}
				,{"Mail"					,ColMail						,"String"	,"運送会社MAIL"}
				,{"Com01"					,ColCom01						,"String"	,"コメント1"}
				,{"Com02"					,ColCom02						,"String"	,"コメント2"}
				,{"Com03"					,ColCom03						,"String"	,"コメント3"}
				,{"ShimeDate"				,ColShimeDate					,"int"		,"締日"}
				,{"ShimeBasis"				,ColShimeBasis				,"int"		,"請求基準"}
				,{"EntryDate"				,ColEntryDate					,"String"	,"データ登録日時"}
				,{"UpdateDate"				,ColUpdateDate				,"String"	,"データ更新日時"}
				,{"EntryUser"				,ColEntryUser					,"String"	,"登録者コード"}
				,{"UpdateUser"				,ColUpdateUser				,"String"	,"更新者コード"}
				,{"PTMSCD"					,ColPTMSCD						,"String"	,"基幹システム傭車コード"}
				,{"ExportDataType"			,ColExportDataType			,"String"	,"データ抽出タイプ"}
				};
		
		return RtSettingShippingCompanyMstRt;
	}
	
	public static Object[][] ShippingCompanyMstRt(
			ArrayList<String> SearchShippingCompanyCd,
			ArrayList<String> SearchCompanyName,
			ArrayList<String> SearchPost,
			ArrayList<String> SearchAdd,
			ArrayList<String> SearchTel,
			ArrayList<String> SearchFax,
			ArrayList<String> SearchMail,
			ArrayList<String> SearchCom,
			boolean AllSearch){
		
		SearchShippingCompanyCd	= B00150ArrayListControl.ArryListStringUniqueList(SearchShippingCompanyCd);
		SearchCompanyName		= B00150ArrayListControl.ArryListStringUniqueList(SearchCompanyName);
		SearchPost				= B00150ArrayListControl.ArryListStringUniqueList(SearchPost);
		SearchAdd				= B00150ArrayListControl.ArryListStringUniqueList(SearchAdd);
		SearchTel				= B00150ArrayListControl.ArryListStringUniqueList(SearchTel);
		SearchFax				= B00150ArrayListControl.ArryListStringUniqueList(SearchFax);
		SearchMail				= B00150ArrayListControl.ArryListStringUniqueList(SearchMail);
		SearchCom				= B00150ArrayListControl.ArryListStringUniqueList(SearchCom);
		
		Object[][] rt = new Object[0][22];
		
		boolean SearchKick = false;
		if(AllSearch) {SearchKick = true;}
		
		String sql = "select"
				+"(KM0070_SHIPPINGCOMPANYMST.ShippingCompanyCd) as ShippingCompanyCd,\n"				//運送会社CD
				+"(KM0070_SHIPPINGCOMPANYMST.ShippingCompanyName01) as ShippingCompanyName01,\n"		//運送会社名1
				+"(KM0070_SHIPPINGCOMPANYMST.ShippingCompanyName02) as ShippingCompanyName02,\n"		//運送会社名2
				+"(KM0070_SHIPPINGCOMPANYMST.ShippingCompanyName03) as ShippingCompanyName03,\n"		//運送会社名3
				+"(KM0070_SHIPPINGCOMPANYMST.Post) as Post,\n"					//運送会社郵便
				+"(KM0070_SHIPPINGCOMPANYMST.Add01) as Add01,\n"				//運送会社住所1
				+"(KM0070_SHIPPINGCOMPANYMST.Add02) as Add02,\n"				//運送会社住所2
				+"(KM0070_SHIPPINGCOMPANYMST.Add03) as Add03,\n"				//運送会社住所3
				+"(KM0070_SHIPPINGCOMPANYMST.Tel) as Tel,\n"					//運送会社電話
				+"(KM0070_SHIPPINGCOMPANYMST.Fax) as Fax,\n"					//運送会社FAX
				+"(KM0070_SHIPPINGCOMPANYMST.Mail) as Mail,\n"					//運送会社MAIL
				+"(KM0070_SHIPPINGCOMPANYMST.Com01) as Com01,\n"				//コメント1
				+"(KM0070_SHIPPINGCOMPANYMST.Com02) as Com02,\n"				//コメント2
				+"(KM0070_SHIPPINGCOMPANYMST.Com03) as Com03,\n"				//コメント3
				+"(KM0070_SHIPPINGCOMPANYMST.ShimeDate) as ShimeDate,\n"		//締日
				+"(KM0070_SHIPPINGCOMPANYMST.ShimeBasis) as ShimeBasis,\n"		//請求基準
				+"(KM0070_SHIPPINGCOMPANYMST.EntryDate) as EntryDate,\n"		//データ登録日時
				+"(KM0070_SHIPPINGCOMPANYMST.UpdateDate) as UpdateDate,\n"		//データ更新日時
				+"(KM0070_SHIPPINGCOMPANYMST.EntryUser) as EntryUser,\n"		//登録者コード
				+"(KM0070_SHIPPINGCOMPANYMST.UpdateUser) as UpdateUser,\n"		//更新者コード
				+"(KM0070_SHIPPINGCOMPANYMST.PTMSCD) as PTMSCD,\n"				//基幹システム傭車コード
				+"(KM0070_SHIPPINGCOMPANYMST.ExportDataType) as ExportDataType\n"	//データ抽出タイプ
				+ " from "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0070_SHIPPINGCOMPANYMST"
				+ " where 1=1 ";
		
		if(null!=SearchShippingCompanyCd && 0<SearchShippingCompanyCd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchShippingCompanyCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + "KM0070_SHIPPINGCOMPANYMST.ShippingCompanyCd = ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchCompanyName && 0<SearchCompanyName.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchCompanyName.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + "KM0070_SHIPPINGCOMPANYMST.ShippingCompanyName01 like ?";
				sql = sql + " or KM0070_SHIPPINGCOMPANYMST.ShippingCompanyName02 like ?";
				sql = sql + " or KM0070_SHIPPINGCOMPANYMST.ShippingCompanyName03 like ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchPost && 0<SearchPost.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchPost.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + "KM0070_SHIPPINGCOMPANYMST.Post like ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchAdd && 0<SearchAdd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchAdd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " CONCAT (KM0070_SHIPPINGCOMPANYMST.Add01";
				sql = sql + " , KM0070_SHIPPINGCOMPANYMST.Add02";
				sql = sql + " , KM0070_SHIPPINGCOMPANYMST.Add03) like ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchTel && 0<SearchTel.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchTel.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + "KM0070_SHIPPINGCOMPANYMST.Tel like ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchFax && 0<SearchFax.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchFax.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + "KM0070_SHIPPINGCOMPANYMST.Fax like ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchMail && 0<SearchMail.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchMail.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + "KM0070_SHIPPINGCOMPANYMST.Mail like ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchCom && 0<SearchCom.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchCom.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + "KM0070_SHIPPINGCOMPANYMST.Com01 like ?";
				sql = sql + " or KM0070_SHIPPINGCOMPANYMST.Com02 like ?";
				sql = sql + " or KM0070_SHIPPINGCOMPANYMST.Com03 like ?";
			}
			sql = sql + ")";
		}
		//System.out.println(sql);
		if(SearchKick) {
			A00010DbConnect.DB_CONN("NYANKO");
			ResultSet rset01 = null;
			PreparedStatement stmt01 = null;
			try {
				stmt01 = A00010DbConnect.conn.prepareStatement(sql);
				int StmtCount = 0;
				
				if(null!=SearchShippingCompanyCd && 0<SearchShippingCompanyCd.size()){
					for(int i=0;i<SearchShippingCompanyCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchShippingCompanyCd.get(i)+"");
					}
				}
				if(null!=SearchCompanyName && 0<SearchCompanyName.size()){
					for(int i=0;i<SearchCompanyName.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchCompanyName.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchCompanyName.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchCompanyName.get(i)+"%");
					}
				}
				if(null!=SearchPost && 0<SearchPost.size()){
					for(int i=0;i<SearchPost.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+B00020ToolsTextControl.num_only_String(""+SearchPost.get(i))+"%");
					}
				}
				if(null!=SearchAdd && 0<SearchAdd.size()){
					for(int i=0;i<SearchAdd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchAdd.get(i)+"%");
					}
				}
				if(null!=SearchTel && 0<SearchTel.size()){
					for(int i=0;i<SearchTel.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+B00020ToolsTextControl.num_only_String(""+SearchTel.get(i))+"%");
					}
				}
				if(null!=SearchFax && 0<SearchFax.size()){
					for(int i=0;i<SearchFax.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+B00020ToolsTextControl.num_only_String(""+SearchFax.get(i))+"%");
					}
				}
				if(null!=SearchMail && 0<SearchMail.size()){
					for(int i=0;i<SearchMail.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchMail.get(i)+"%");
					}
				}
				if(null!=SearchCom && 0<SearchCom.size()){
					for(int i=0;i<SearchCom.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchCom.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchCom.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchCom.get(i)+"%");
					}
				}
				rset01 = stmt01.executeQuery();
				
				int counter = 0;
				rset01.beforeFirst();
				while (rset01.next()) {
					counter=counter+1;
				}

				rt = new Object[counter][22];
				counter = 0;
				rset01.beforeFirst();
				while (rset01.next()) {
					if(null==rset01.getString("ShippingCompanyCd")){		rt[counter][ColShippingCompanyCd]		="";}else{rt[counter][ColShippingCompanyCd]		=rset01.getString("ShippingCompanyCd");}		//運送会社CD
					if(null==rset01.getString("ShippingCompanyName01")){	rt[counter][ColShippingCompanyName01]	="";}else{rt[counter][ColShippingCompanyName01]	=rset01.getString("ShippingCompanyName01");}	//運送会社名1
					if(null==rset01.getString("ShippingCompanyName02")){	rt[counter][ColShippingCompanyName02]	="";}else{rt[counter][ColShippingCompanyName02]	=rset01.getString("ShippingCompanyName02");}	//運送会社名2
					if(null==rset01.getString("ShippingCompanyName03")){	rt[counter][ColShippingCompanyName03]	="";}else{rt[counter][ColShippingCompanyName03]	=rset01.getString("ShippingCompanyName03");}	//運送会社名3
					if(null==rset01.getString("Post")){						rt[counter][ColPost]						="";}else{rt[counter][ColPost]						=rset01.getString("Post");}						//運送会社郵便
					if(null==rset01.getString("Add01")){					rt[counter][ColAdd01]						="";}else{rt[counter][ColAdd01]					=rset01.getString("Add01");}					//運送会社住所1
					if(null==rset01.getString("Add02")){					rt[counter][ColAdd02]						="";}else{rt[counter][ColAdd02]					=rset01.getString("Add02");}					//運送会社住所2
					if(null==rset01.getString("Add03")){					rt[counter][ColAdd03]						="";}else{rt[counter][ColAdd03]					=rset01.getString("Add03");}					//運送会社住所3
					if(null==rset01.getString("Tel")){						rt[counter][ColTel]						="";}else{rt[counter][ColTel]						=rset01.getString("Tel");}						//運送会社電話
					if(null==rset01.getString("Fax")){						rt[counter][ColFax]						="";}else{rt[counter][ColFax]						=rset01.getString("Fax");}						//運送会社FAX
					if(null==rset01.getString("Mail")){						rt[counter][ColMail]						="";}else{rt[counter][ColMail]						=rset01.getString("Mail");}						//運送会社MAIL
					if(null==rset01.getString("Com01")){					rt[counter][ColCom01]						="";}else{rt[counter][ColCom01]					=rset01.getString("Com01");}					//コメント1
					if(null==rset01.getString("Com02")){					rt[counter][ColCom02]						="";}else{rt[counter][ColCom02]					=rset01.getString("Com02");}					//コメント2
					if(null==rset01.getString("Com03")){					rt[counter][ColCom03]						="";}else{rt[counter][ColCom03]					=rset01.getString("Com03");}					//コメント3
					rt[counter][ColShimeDate]=rset01.getInt("ShimeDate");				//締日
					rt[counter][ColShimeBasis]=rset01.getInt("ShimeBasis");			//請求基準
					if(null==rset01.getTimestamp("EntryDate")){				rt[counter][ColEntryDate]					="";}else{rt[counter][ColEntryDate]				=B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("EntryDate"))[1];}		//データ登録日時
					if(null==rset01.getTimestamp("UpdateDate")){			rt[counter][ColUpdateDate]				="";}else{rt[counter][ColUpdateDate]				=B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("UpdateDate"))[1];}		//データ更新日時
					if(null==rset01.getString("EntryUser")){				rt[counter][ColEntryUser]					="";}else{rt[counter][ColEntryUser]				=rset01.getString("EntryUser");}				//登録者コード
					if(null==rset01.getString("UpdateUser")){				rt[counter][ColUpdateUser]				="";}else{rt[counter][ColUpdateUser]				=rset01.getString("UpdateUser");}				//更新者コード
					if(null==rset01.getString("PTMSCD")){					rt[counter][ColPTMSCD]						="";}else{rt[counter][ColPTMSCD]					=rset01.getString("PTMSCD");}					//基幹システム傭車コード
					if(null==rset01.getString("ExportDataType")){			rt[counter][ColExportDataType]			="";}else{rt[counter][ColExportDataType]			=rset01.getString("ExportDataType");}			//データ抽出タイプ
					
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
	
	public static String[] NewShipingCompanyCdGet(int NeedCount) {
		ArrayList<String> SearchShippingCompanyCd = new ArrayList<String>();
		ArrayList<String> SearchCompanyName = new ArrayList<String>();
		ArrayList<String> SearchPost = new ArrayList<String>();
		ArrayList<String> SearchAdd = new ArrayList<String>();
		ArrayList<String> SearchTel = new ArrayList<String>();
		ArrayList<String> SearchFax = new ArrayList<String>();
		ArrayList<String> SearchMail = new ArrayList<String>();
		ArrayList<String> SearchCom = new ArrayList<String>();
		boolean AllSearch = true;
    	
    	Object[][] ShippingCompanyMstRt = ShippingCompanyMstRt(
    			SearchShippingCompanyCd,
    			SearchCompanyName,
    			SearchPost,
    			SearchAdd,
    			SearchTel,
    			SearchFax,
    			SearchMail,
    			SearchCom,
    			AllSearch);
    	
    	int SCNo = 0;
    	
    	for(int i=0;i<ShippingCompanyMstRt.length;i++) {
    		if("ATSC".equals((""+ShippingCompanyMstRt[i][M00030ShippingCompanyMstRt.ColShippingCompanyCd]).substring(0,4))&&11==(""+ShippingCompanyMstRt[i][M00030ShippingCompanyMstRt.ColShippingCompanyCd]).length()) {
    			String WST = B00020ToolsTextControl.num_only_String(""+ShippingCompanyMstRt[i][M00030ShippingCompanyMstRt.ColShippingCompanyCd]);
    			if(7==WST.length()) {
    				int wint = Integer.parseInt(WST);
    				if(SCNo<wint) {
    					SCNo=wint;
    				}
    			}
    		}
    	}

    	String[] rt = new String[NeedCount];
    	for(int i=0;i<NeedCount;i++) {
    		SCNo = SCNo+1;
	    	rt[i] = "0000000"+SCNo;
	    	rt[i] = "ATSC"+rt[i].substring(rt[i].length()-7,rt[i].length());
    	}
    	
    	return rt;
	}
	
}