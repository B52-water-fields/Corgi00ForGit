import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class M10010PostMstRt{
	
	public static Object[][] RtSettingPostRt(){
		//戻り値カラム
		/*
		コピペ用
		ArrayList<String> SearchPOST = new ArrayList<String>();
		ArrayList<String> SearchAdd = new ArrayList<String>();
		boolean AllSearch = false;
		boolean PostPerfectMatch = false;
		
		Object[][] PostRt = M10010PostMstRt.PostRt(
												SearchPOST,
												SearchAdd,
												AllSearch,
												PostPerfectMatch);
		*/
		Object[][] RtSettingPostRt = {
				 {"POST"			,(int) 0	,"String"	,"郵便番号"}
				,{"PREFECTURES"		,(int) 1	,"String"	,"県"}
				,{"MUNICI01"		,(int) 2	,"String"	,"市区町村"}
				,{"MUNICI02"		,(int) 3	,"String"	,"町丁目"}
				,{"MUNICIPALITY_CD"	,(int) 4	,"String"	,"市区町村CD"}
				};
		
		return RtSettingPostRt;
	}
	
	public static Object[][] RtSettingMunicipalityRt(){
		//市区町村マスタ戻り値
		/*
		コピペ用		
		ArrayList<String> SearchName = new ArrayList<String>();
		ArrayList<String> SearchMunicipalityCd = new ArrayList<String>();
		boolean AllSearch = false;
		
		Object[][] MunicipalityRt = M10010PostMstRt.MunicipalityRt(
																SearchName,
																SearchMunicipalityCd,
																AllSearch);
		*/
		Object[][] RtSettingMunicipalityRt = {
				{"PREFECTURES"		,(int) 0	,"String"	,"県"}
				,{"MUNICI01"		,(int) 1	,"String"	,"市区町村"}
				,{"MUNICIPALITY_CD"	,(int) 2	,"String"	,"市区町村CD"}
				};
		
		return RtSettingMunicipalityRt;
	}
	public static Object[][] RtSettingPrefecuturesRt(){
		//県マスタ戻り値
		/*
		コピペ用
		Object[][] PrefecuturesRt = M10010PostMstRt.PrefecuturesRt();
		*/
		Object[][] RtSettingPrefecuturesRt = {
				{"PREFECTURES_CD"	,(int) 0	,"String"	,"県CD"}
				,{"PREFECTURES"		,(int) 1	,"String"	,"県名"}
				};
		
		return RtSettingPrefecuturesRt;
	}
	
	public static Object[][] RtAddToMunicipality(){
		//住所一覧に対してJIS判定して結果を返却する
		/*
		コピペ用
		String[] AddList = new String[0];
		
		Object[][] AddToMunicipality = M10010PostMstRt.AddToMunicipality(
																	AddList);
		*/
		Object[][] RtAddToMunicipality = {
				{"TgtAdd"				,(int) 0	,"String"	,"対象住所"}
				,{"MUNICIPALITY_CD"		,(int) 1	,"String"	,"判定JISCD"}
				};
		return RtAddToMunicipality;
		
	}
	
	
	public static Object[][] PostRt(
			ArrayList<String> SearchPOST,
			ArrayList<String> SearchAdd,
			boolean AllSearch
			,boolean PostPerfectMatch){
		//郵便番号を受け取って郵便番号マスタから検索結果を返却する
		Object[][] rt = new Object[0][5];

		boolean SearchKick = false;
		if(AllSearch) {SearchKick = true;}

		String sql = "select "
				+"("+A00000Main.MySqlDefaultSchemaPOST+".M0010_PostMst.POST) as POST,\n"
				+"("+A00000Main.MySqlDefaultSchemaPOST+".M0010_PostMst.PREFECTURES) as PREFECTURES,\n"
				+"("+A00000Main.MySqlDefaultSchemaPOST+".M0010_PostMst.MUNICI01) as MUNICI01,\n"
				+"("+A00000Main.MySqlDefaultSchemaPOST+".M0010_PostMst.MUNICI02) as MUNICI02,\n"
				+"("+A00000Main.MySqlDefaultSchemaPOST+".M0010_PostMst.MUNICIPALITY_CD) as MUNICIPALITY_CD\n"
				+ " from "+A00000Main.MySqlDefaultSchemaPOST+".M0010_PostMst\n"
				+ " where 1=1\n";
		if(null!=SearchPOST && 0<SearchPOST.size()){
			SearchKick = true;
			if(PostPerfectMatch) {
				for(int i=0;i<SearchPOST.size();i++) {
					String WST = ""+SearchPOST.get(i);
					WST = B00020ToolsTextControl.num_only_String(WST);
					if(i==0) {sql = sql + " and(";}else {sql = sql + " or ";}
					sql = sql + ""+A00000Main.MySqlDefaultSchemaPOST+".M0010_PostMst.POST = ?\n";
				}
			}else {
				for(int i=0;i<SearchPOST.size();i++) {
					String WST = ""+SearchPOST.get(i);
					WST = B00020ToolsTextControl.num_only_String(WST);
					if(i==0) {sql = sql + " and(";}else {sql = sql + " or ";}
					sql = sql + ""+A00000Main.MySqlDefaultSchemaPOST+".M0010_PostMst.POST like ?\n";
				}
			}
			sql=sql+")";
		}

		if(null!=SearchAdd && 0<SearchAdd.size()){
			SearchKick = true;
			for(int i=0;i<SearchAdd.size();i++) {
				if(i==0) {sql = sql + " and(";}else {sql = sql + " or ";}
				
				sql = sql + "CONCAT ("+A00000Main.MySqlDefaultSchemaPOST+".M0010_PostMst.PREFECTURES\n";
				sql = sql + ","+A00000Main.MySqlDefaultSchemaPOST+".M0010_PostMst.MUNICI01\n";
				sql = sql + ","+A00000Main.MySqlDefaultSchemaPOST+".M0010_PostMst.MUNICI02) like ?\n";
			}
			sql=sql+")";
		}
		
		//System.out.println(sql);
		if(SearchKick) {
			A00010DbConnect.DB_CONN("POST");
			
			ResultSet rset01 = null;
			PreparedStatement stmt01 = null;
			try {
				stmt01 = A00010DbConnect.conn.prepareStatement(sql);
				int StmtCount = 0;
				if(null!=SearchPOST && 0<SearchPOST.size()){
					if(PostPerfectMatch) {
						for(int i=0;i<SearchPOST.size();i++) {
							String WST = ""+SearchPOST.get(i);
							WST = B00020ToolsTextControl.num_only_String(WST);
							
							StmtCount = StmtCount+1;
							stmt01.setString(StmtCount, WST);
						}
					}else {
						for(int i=0;i<SearchPOST.size();i++) {
							String WST = ""+SearchPOST.get(i);
							WST = B00020ToolsTextControl.num_only_String(WST);
							StmtCount = StmtCount+1;
							stmt01.setString(StmtCount, WST+"%");
						}
					}
				}

				if(null!=SearchAdd && 0<SearchAdd.size()){
					SearchKick = true;
					for(int i=0;i<SearchAdd.size();i++) {
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchAdd.get(i)+"%");
					}
				}				
				rset01 = stmt01.executeQuery();
				
				int counter = 0;
				rset01.beforeFirst();
				while (rset01.next()) {
					counter=counter+1;
				}

				rt = new Object[counter][5];
				counter = 0;
				rset01.beforeFirst();
				while (rset01.next()) {
					if(null==rset01.getString("POST")){rt[counter][0]="";}else{rt[counter][0]=rset01.getString("POST");}						//郵便番号
					if(null==rset01.getString("PREFECTURES")){rt[counter][1]="";}else{rt[counter][1]=rset01.getString("PREFECTURES");}			//県
					if(null==rset01.getString("MUNICI01")){rt[counter][2]="";}else{rt[counter][2]=rset01.getString("MUNICI01");}				//市区町村
					if(null==rset01.getString("MUNICI02")){rt[counter][3]="";}else{rt[counter][3]=rset01.getString("MUNICI02");}				//町丁目
					if(null==rset01.getString("MUNICIPALITY_CD")){rt[counter][4]="";}else{rt[counter][4]=rset01.getString("MUNICIPALITY_CD");}	//市区町村CD
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
	public static Object[][] MunicipalityRt(ArrayList<String> SearchName,ArrayList<String> SearchMunicipalityCd,boolean AllSearch){
	//市区町村マスタ返却
		Object[][] rt = new Object[0][3];
		boolean KickFg=false;
		if(AllSearch) {KickFg=true;}
		String sql = "select "
				+"max("+A00000Main.MySqlDefaultSchemaPOST+".M0010_PostMst.PREFECTURES) as PREFECTURES,"
				+"max("+A00000Main.MySqlDefaultSchemaPOST+".M0010_PostMst.MUNICI01) as MUNICI01,"
				+"("+A00000Main.MySqlDefaultSchemaPOST+".M0010_PostMst.MUNICIPALITY_CD) as MUNICIPALITY_CD"
				+ " from "+A00000Main.MySqlDefaultSchemaPOST+".M0010_PostMst"
				+ " where 1=1";
		if(null!=SearchName&&0<SearchName.size()) {
			KickFg=true;
			sql = sql + " and(";
			for(int i=0;i<SearchName.size();i++) {
				if(i>0) {
					sql = sql + " or ";
				}
				sql = sql + "CONCAT ("+A00000Main.MySqlDefaultSchemaPOST+".M0010_PostMst.PREFECTURES,"
							+A00000Main.MySqlDefaultSchemaPOST+".M0010_PostMst.MUNICI01,"
							+A00000Main.MySqlDefaultSchemaPOST+".M0010_PostMst.MUNICI02) like ?";
			}
			sql = sql+")";
		}
		
		if(null!=SearchMunicipalityCd&&0<SearchMunicipalityCd.size()) {
			KickFg=true;
			sql = sql + " and(";
			for(int i=0;i<SearchMunicipalityCd.size();i++) {
				if(i>0) {
					sql = sql + " or ";
				}
				sql = sql + A00000Main.MySqlDefaultSchemaPOST+".M0010_PostMst.MUNICIPALITY_CD = ?";
			}
			sql = sql+")";
		}
		
		sql = sql + " group by "+A00000Main.MySqlDefaultSchemaPOST+".M0010_PostMst.MUNICIPALITY_CD order by "+A00000Main.MySqlDefaultSchemaPOST+".M0010_PostMst.MUNICIPALITY_CD";
		//System.out.println(sql);

		if(KickFg) {
			A00010DbConnect.DB_CONN("POST");
			ResultSet rset01 = null;
			PreparedStatement stmt01 = null;
			try {
				stmt01 = A00010DbConnect.conn.prepareStatement(sql);
				int StmtCount = 0;
				if(null!=SearchName&&0<SearchName.size()) {
					KickFg=true;
					sql = sql + " and(";
					for(int i=0;i<SearchName.size();i++) {
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchName.get(i)+"%");
					}
				}
				
				if(null!=SearchMunicipalityCd&&0<SearchMunicipalityCd.size()) {
					KickFg=true;
					sql = sql + " and(";
					for(int i=0;i<SearchMunicipalityCd.size();i++) {
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchMunicipalityCd.get(i));
					}
				}
				rset01 = stmt01.executeQuery();
				
				int counter = 0;
				rset01.beforeFirst();
				while (rset01.next()) {
					counter=counter+1;
				}
	
				rt = new Object[counter][3];
				counter = 0;
				rset01.beforeFirst();
				while (rset01.next()) {
					if(null==rset01.getString("PREFECTURES")){rt[counter][0]="";}else{rt[counter][0]=rset01.getString("PREFECTURES");}			//県
					if(null==rset01.getString("MUNICI01")){rt[counter][1]="";}else{rt[counter][1]=rset01.getString("MUNICI01");}				//市区町村
					if(null==rset01.getString("MUNICIPALITY_CD")){rt[counter][2]="";}else{rt[counter][2]=rset01.getString("MUNICIPALITY_CD");}	//市区町村CD
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
	public static Object[][] PrefecuturesRt(){
	//県マスタ返却

		Object[][] rt = new Object[0][2];
		boolean KickFg=false;
		KickFg=true;
		String sql = "select "
				+"("+A00000Main.MySqlDefaultSchemaPOST+".M0010_PostMst.PREFECTURES) as PREFECTURES,"
				+"("+A00000Main.MySqlDefaultSchemaPOST+".M0010_PostMst.MUNICIPALITY_CD) as MUNICIPALITY_CD,"
				+"LEFT("+A00000Main.MySqlDefaultSchemaPOST+".M0010_PostMst.MUNICIPALITY_CD,2) as PREFECTURES_CD"
				+ " from "+A00000Main.MySqlDefaultSchemaPOST+".M0010_PostMst"
				+ " where 1=1";
		
		sql = " select "
				+ "max(SD.PREFECTURES) as PREFECTURES,"
				+ "(SD.PREFECTURES_CD) as PREFECTURES_CD"
				+ " from ("+ sql + ") as SD";
		
		sql = sql + " group by PREFECTURES_CD order by PREFECTURES_CD";

		//System.out.println(sql);
		if(KickFg) {
			A00010DbConnect.DB_CONN("POST");
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
	
				rt = new Object[counter][2];
				counter = 0;
				rset01.beforeFirst();
				while (rset01.next()) {
					if(null==rset01.getString("PREFECTURES_CD")){rt[counter][0]="";}else{rt[counter][0]=rset01.getString("PREFECTURES_CD");}		//県CD
					if(null==rset01.getString("PREFECTURES")){rt[counter][1]="";}else{rt[counter][1]=rset01.getString("PREFECTURES");}				//県名
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
	
	//同一市区町村名の市区町村を場外した市区町村リストを返却する
	//伊達市（北海道 or 福島県） と　府中市(東京都 or 広島県) のような例が除外されます
	private static Object[][] UniqueMunicipalityList(){
		Object[][] rt = new Object[0][2];
		
		String sql = ""
		+"select\n"
		+"	(SD.MUNICI01)        as MUNICI01,\n"
		+"	(SD.MUNICIPALITY_CD) as MUNICIPALITY_CD\n"
		+"from(\n"
		+"	select\n"
		+"	count(Ms.MUNICI01)        as MunisipalityCount,\n"
		+"	     (Ms.MUNICI01)        as MUNICI01,\n"
		+"	max(  Ms.MUNICIPALITY_CD) as MUNICIPALITY_CD\n"
		+"	from(\n"
		+"		select \n"
		+"		max("+A00000Main.MySqlDefaultSchemaPOST+".M0010_PostMst.PREFECTURES) as PREFECTURES,\n"
		+"		max("+A00000Main.MySqlDefaultSchemaPOST+".M0010_PostMst.MUNICI01) as MUNICI01,\n"
		+"		   ("+A00000Main.MySqlDefaultSchemaPOST+".M0010_PostMst.MUNICIPALITY_CD) as MUNICIPALITY_CD\n"
		+"		from "+A00000Main.MySqlDefaultSchemaPOST+".M0010_PostMst \n"
		+"		where "+A00000Main.MySqlDefaultSchemaPOST+".M0010_PostMst.MUNICIPALITY_CD <> '00000' \n"
		+"		group by "+A00000Main.MySqlDefaultSchemaPOST+".M0010_PostMst.MUNICIPALITY_CD\n"
		+"	) as Ms\n"
		+"	where 1=1\n"
		+"	group by Ms.MUNICI01\n"
		+") as SD\n"
		+"where 1=1\n"
		+"	and SD.MunisipalityCount = 1;\n";
		//System.out.println(sql);
		A00010DbConnect.DB_CONN("POST");
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

			rt = new Object[counter][2];
			counter = 0;
			rset01.beforeFirst();
			while (rset01.next()) {
				if(null==rset01.getString("MUNICI01")){rt[counter][0]="";}else{rt[counter][0]=rset01.getString("MUNICI01");}				//市区町村名
				if(null==rset01.getString("MUNICIPALITY_CD")){rt[counter][1]="";}else{rt[counter][1]=rset01.getString("MUNICIPALITY_CD");}	//市区町村CD
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
		return rt;
	}
	
	private static Object[][] LastCharange() {
		Object[][] WRT = new Object[0][2];
		
		String sql = ""
		+"select\n"
		+"	(SD.MUNICI01)        as MUNICI01,\n"
		+"	(SD.MUNICIPALITY_CD) as MUNICIPALITY_CD\n"
		+"from(\n"
		+"	select\n"
		+"	count(Ms.MUNICI01)        as MunisipalityCount,\n"
		+"	     (Ms.MUNICI01)        as MUNICI01,\n"
		+"	max(  Ms.MUNICIPALITY_CD) as MUNICIPALITY_CD\n"
		+"	from(\n"
		+"		select \n"
		+"		max("+A00000Main.MySqlDefaultSchemaPOST+".M0010_PostMst.PREFECTURES) as PREFECTURES,\n"
		+"		max("+A00000Main.MySqlDefaultSchemaPOST+".M0010_PostMst.MUNICI01) as MUNICI01,\n"
		+"		   ("+A00000Main.MySqlDefaultSchemaPOST+".M0010_PostMst.MUNICIPALITY_CD) as MUNICIPALITY_CD\n"
		+"		from "+A00000Main.MySqlDefaultSchemaPOST+".M0010_PostMst \n"
		+"		where "+A00000Main.MySqlDefaultSchemaPOST+".M0010_PostMst.MUNICIPALITY_CD <> '00000' \n"
		+"            and("+A00000Main.MySqlDefaultSchemaPOST+".M0010_PostMst.MUNICI01 like '%区' "
		+"            or "+A00000Main.MySqlDefaultSchemaPOST+".M0010_PostMst.MUNICI01 like '%町' "
		+"            or "+A00000Main.MySqlDefaultSchemaPOST+".M0010_PostMst.MUNICI01 like '%村' "
		+"                )\n"
		+"		group by "+A00000Main.MySqlDefaultSchemaPOST+".M0010_PostMst.MUNICIPALITY_CD\n"
		+"	) as Ms\n"
		+"	where 1=1\n"
		+"	group by Ms.MUNICI01\n"
		+") as SD\n"
		+"where 1=1\n"
		+"	and SD.MunisipalityCount = 1;\n";
		//System.out.println(sql);
		A00010DbConnect.DB_CONN("POST");
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

			WRT = new Object[counter][2];
			counter = 0;
			rset01.beforeFirst();
			while (rset01.next()) {
				if(null==rset01.getString("MUNICI01")){WRT[counter][0]="";}else{WRT[counter][0]=rset01.getString("MUNICI01");}					//市区町村名
				if(null==rset01.getString("MUNICIPALITY_CD")){WRT[counter][1]="";}else{WRT[counter][1]=rset01.getString("MUNICIPALITY_CD");}	//市区町村CD
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
		
		//市区町村名が"区""町""村"で終わる市区町村をリストアップ "市区","郡町","郡村"で終わる住所は判定捨てる
		ArrayList<String> WCAdd = new ArrayList<String>();
		ArrayList<String> WCJis = new ArrayList<String>();
		for(int i=0;i<WRT.length;i++) {
			if(0<(""+WRT[i][0]).length() && ("区").equals((""+WRT[i][0]).substring((""+WRT[i][0]).length()-1,(""+WRT[i][0]).length()))) {
				if(1<(""+WRT[i][0]).length() && ("市区").equals((""+WRT[i][0]).substring((""+WRT[i][0]).length()-2,(""+WRT[i][0]).length()))) {
					
				}else {
					//市でスプリット
					String[] WST = (""+WRT[i][0]).split("市");
					WCAdd.add(WST[WST.length-1]);
					WCJis.add(""+WRT[i][1]);
				}
			}
			if(0<(""+WRT[i][0]).length() && ("町").equals((""+WRT[i][0]).substring((""+WRT[i][0]).length()-1,(""+WRT[i][0]).length()))) {
				if(1<(""+WRT[i][0]).length() && ("郡町").equals((""+WRT[i][0]).substring((""+WRT[i][0]).length()-2,(""+WRT[i][0]).length()))) {
					
				}else {
					//郡でスプリット
					String[] WST = (""+WRT[i][0]).split("郡");
					WCAdd.add(WST[WST.length-1]);
					WCJis.add(""+WRT[i][1]);
				}
			}
			if(0<(""+WRT[i][0]).length() && ("村").equals((""+WRT[i][0]).substring((""+WRT[i][0]).length()-1,(""+WRT[i][0]).length()))) {
				if(1<(""+WRT[i][0]).length() && ("郡村").equals((""+WRT[i][0]).substring((""+WRT[i][0]).length()-2,(""+WRT[i][0]).length()))) {
					
				}else {
					//郡でスプリット
					String[] WST = (""+WRT[i][0]).split("郡");
					WCAdd.add(WST[WST.length-1]);
					WCJis.add(""+WRT[i][1]);
				}
			}
		}
		ArrayList<String> CAdd = new ArrayList<String>();
		ArrayList<String> CJis = new ArrayList<String>();
		if(null!=WCAdd&&0<WCAdd.size()) {
			//名称重複のない"区""町""村"ならJIS特定に使う
			for(int i01=0;i01<WCAdd.size();i01++) {
				boolean UnHitFg = true;
				for(int i02=0;i02<WCAdd.size();i02++) {
					if(i01!=i02&&WCAdd.get(i01).equals(WCAdd.get(i02))) {
						UnHitFg = false;
						i02=WCAdd.size()+1;
					}
				}
				if(UnHitFg) {
					CAdd.add(WCAdd.get(i01));
					CJis.add(WCJis.get(i01));
				}else {
					//System.out.println(WCAdd.get(i01));
				}
			}
		}
		Object[][] rt = new Object[0][2];
		if(null!=CAdd && 0<CAdd.size()) {
			rt = new Object[CAdd.size()][2];
			for(int i=0;i<CAdd.size();i++) {
				rt[i][0] = CAdd.get(i);
				rt[i][1] = CJis.get(i);
			}
		}
		return rt;
	}
	
	//住所一覧を受け取って対応するJIS市区町村CDを返却する
	public static String[][] AddToMunicipality(String[] AddList){
		
		if(null==AddList) {AddList = new String[0];}
		String[][] rt = new String[AddList.length][2];
		
		ArrayList<String> SearchName = new ArrayList<String>();
		ArrayList<String> SearchMunicipalityCd = new ArrayList<String>();
		boolean AllSearch = true;
		Object[][] MunicipalityRt = MunicipalityRt(SearchName,SearchMunicipalityCd,AllSearch);
		
		Object[][] UniqueMunicipalityList = UniqueMunicipalityList();
		
		Object[][] LastCharange = LastCharange();
		
		
		for(int i=0;i<AddList.length;i++) {
			boolean UnHitFg = true;
			if(null==AddList[i]) {AddList[i]="";}
			rt[i][0] = ""+AddList[i];
			rt[i][1] = "";
			
			for(int i01=0;i01<MunicipalityRt.length;i01++) {
				//〇〇県××市を含む場合市区町村特定できたものとする
				if(!(rt[i][0].equals(rt[i][0].replace(""+MunicipalityRt[i01][0], "")))
						&&!(rt[i][0].equals(rt[i][0].replace(""+MunicipalityRt[i01][1], "")))
						) {
					rt[i][1] = ""+MunicipalityRt[i01][2];
					UnHitFg  = false;
					i01=MunicipalityRt.length+1;
				}
			}
			
			if(UnHitFg) {
				//××市で始まる場合特定できたものとする
				for(int i01=0;i01<UniqueMunicipalityList.length;i01++) {
					if(0<(""+UniqueMunicipalityList[i01][0]).length()&&rt[i][0].length()>=(""+UniqueMunicipalityList[i01][0]).length()) {
						
						String WST = rt[i][0].substring(0,(""+UniqueMunicipalityList[i01][0]).length());
						
						if((""+UniqueMunicipalityList[i01][0]).equals(WST)){
							rt[i][1] = ""+UniqueMunicipalityList[i01][1];
							UnHitFg  = false;
							i01=UniqueMunicipalityList.length+1;
						}
					}
				}
			}
			
			if(UnHitFg) {
				//△△区　△△町 △△村で始まる場合特定できたものとする
				for(int i01=0;i01<LastCharange.length;i01++) {
					String WST = rt[i][0].substring(0,(""+LastCharange[i01][0]).length());
					if((""+LastCharange[i01][0]).equals(WST)){
						rt[i][1] = ""+LastCharange[i01][1];
						UnHitFg  = false;
						i01=LastCharange.length+1;
					}
				}
			}
		}
		//System.out.println(rt[0][1]);
		return rt;
	}
}