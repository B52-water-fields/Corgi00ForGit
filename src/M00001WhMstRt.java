import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class M00001WhMstRt{
	public static Object[][] WhMstRt(
			ArrayList<String> SearchWHCD,
			ArrayList<String> SearchWHName,
			ArrayList<String> SearchPost,
			ArrayList<String> SearchAdd,
			ArrayList<String> SearchTel,
			ArrayList<String> SearchFax,
			ArrayList<String> SearchMail,
			ArrayList<String> SearchCom,
			ArrayList<String> SearchPTMSCD,
			boolean AllSearch){
		Object[][] rt = new Object[0][16];
		boolean SearchKick = false;
		if(AllSearch) {SearchKick = true;}
		String sql = " select "
				+"("+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST.WHCD) as WHCD,\n"				//倉庫コード
				+"("+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST.WHName) as WHName,\n"			//拠点倉庫名
				+"("+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST.Post) as Post,\n"				//拠点倉庫郵便番号
				+"("+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST.Add01) as Add01,\n"				//拠点倉庫住所1
				+"("+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST.Add02) as Add02,\n"				//拠点倉庫住所2
				+"("+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST.Tel) as Tel,\n"					//拠点倉庫電話
				+"("+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST.Fax) as Fax,\n"					//拠点倉庫FAX
				+"("+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST.Mail) as Mail,\n"				//拠点倉庫MAIL
				+"("+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST.Com01) as Com01,\n"				//コメント１
				+"("+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST.Com02) as Com02,\n"				//コメント２
				+"("+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST.Com03) as Com03,\n"				//コメント３
				+"("+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST.PTMSCD) as PTMSCD,\n"			//基幹システム連携用事業所CD
				+"("+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST.EntryDate) as EntryDate,\n"		//登録者コード
				+"("+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST.UpdateDate) as UpdateDate,\n"	//更新者コード
				+"("+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST.EntryUser) as EntryUser,\n"		//データ登録日時
				+"("+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST.UpdateUser) as UpdateUser\n"	//データ更新日時
				+ " from "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST"
				+ " where 1=1";
		
		if(null!=SearchWHCD&&0<SearchWHCD.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchWHCD.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST.WHCD = '"+SearchWHCD.get(i)+"'";
			}
			sql = sql + ")";
		}
		if(null!=SearchWHName&&0<SearchWHName.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchWHName.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST.WHName like '%"+SearchWHName.get(i)+"%'";
			}
			sql = sql + ")";
		}
		if(null!=SearchPost&&0<SearchPost.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchPost.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST.Post like '"+SearchPost.get(i)+"%'";
			}
			sql = sql + ")";
		}
		if(null!=SearchAdd&&0<SearchAdd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchAdd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST.Add01 like '%"+SearchAdd.get(i)+"%'";
				sql = sql + " or "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST.Add02 like '%"+SearchAdd.get(i)+"%'";
			}
			sql = sql + ")";
		}
		if(null!=SearchTel&&0<SearchTel.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchTel.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST.Tel like '%"+SearchTel.get(i)+"%'";
			}
			sql = sql + ")";
		}
		if(null!=SearchFax&&0<SearchFax.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchFax.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST.Fax like '%"+SearchFax.get(i)+"%'";
			}
			sql = sql + ")";
		}
		if(null!=SearchMail&&0<SearchMail.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchMail.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST.Mail like '%"+SearchMail.get(i)+"%'";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchCom&&0<SearchCom.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchCom.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST.Com01 like '%"+SearchCom.get(i)+"%'";
				sql = sql + " or "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST.Com02 like '%"+SearchCom.get(i)+"%'";
				sql = sql + " or "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST.Com03 like '%"+SearchCom.get(i)+"%'";
			}
			sql = sql + ")";
		}
		if(null!=SearchPTMSCD&&0<SearchPTMSCD.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchPTMSCD.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST.PTMSCD = '"+SearchPTMSCD.get(i)+"'";
			}
			sql = sql + ")";
		}
		
		sql = sql + " order by "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST.WHCD";
		
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
				rt = new Object[counter][16];
				counter = 0;
				rset01.beforeFirst();
				while (rset01.next()) {
					if(null==rset01.getString("WHCD")){rt[counter][0]="";}else{rt[counter][0]=rset01.getString("WHCD");}				//倉庫コード
					if(null==rset01.getString("WHName")){rt[counter][1]="";}else{rt[counter][1]=rset01.getString("WHName");}			//拠点倉庫名
					if(null==rset01.getString("Post")){rt[counter][2]="";}else{rt[counter][2]=rset01.getString("Post");}				//拠点倉庫郵便番号
					if(null==rset01.getString("Add01")){rt[counter][3]="";}else{rt[counter][3]=rset01.getString("Add01");}				//拠点倉庫住所1
					if(null==rset01.getString("Add02")){rt[counter][4]="";}else{rt[counter][4]=rset01.getString("Add02");}				//拠点倉庫住所2
					if(null==rset01.getString("Tel")){rt[counter][5]="";}else{rt[counter][5]=rset01.getString("Tel");}					//拠点倉庫電話
					if(null==rset01.getString("Fax")){rt[counter][6]="";}else{rt[counter][6]=rset01.getString("Fax");}					//拠点倉庫FAX
					if(null==rset01.getString("Mail")){rt[counter][7]="";}else{rt[counter][7]=rset01.getString("Mail");}				//拠点倉庫MAIL
					if(null==rset01.getString("Com01")){rt[counter][8]="";}else{rt[counter][8]=rset01.getString("Com01");}				//コメント１
					if(null==rset01.getString("Com02")){rt[counter][9]="";}else{rt[counter][9]=rset01.getString("Com02");}				//コメント２
					if(null==rset01.getString("Com03")){rt[counter][10]="";}else{rt[counter][10]=rset01.getString("Com03");}			//コメント３
					if(null==rset01.getString("PTMSCD")){rt[counter][11]="";}else{rt[counter][11]=rset01.getString("PTMSCD");}			//基幹システム連携用事業所CD
					if(null==rset01.getTimestamp("EntryDate")){rt[counter][12]="";}else{rt[counter][12]=B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("EntryDate"))[1];}			//データ登録日時
					if(null==rset01.getTimestamp("UpdateDate")){rt[counter][13]="";}else{rt[counter][13]=B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("UpdateDate"))[1];}			//データ更新日時
					if(null==rset01.getString("EntryUser")){rt[counter][14]="";}else{rt[counter][14]=rset01.getString("EntryUser");}		//登録者
					if(null==rset01.getString("UpdateUser")){rt[counter][15]="";}else{rt[counter][15]=rset01.getString("UpdateUser");}		//更新者
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
	
	//倉庫コードを自動採番する
	public static String NewWhCdGet() {
		//倉庫マスタ取得
		ArrayList SearchWHCD = new ArrayList();
		ArrayList SearchWHName = new ArrayList();
		ArrayList SearchPost = new ArrayList();
		ArrayList SearchAdd = new ArrayList();
		ArrayList SearchTel = new ArrayList();
		ArrayList SearchFax = new ArrayList();
		ArrayList SearchMail = new ArrayList();
		ArrayList SearchCom = new ArrayList();
		ArrayList SearchPTMSCD = new ArrayList();
		boolean AllSearch = true;
		
		Object[][] WhMstRt = M00001WhMstRt.WhMstRt(
					SearchWHCD, SearchWHName, SearchPost,
					SearchAdd, SearchTel, SearchFax, SearchMail,
					SearchCom, SearchPTMSCD,AllSearch);
		
		int WhNo = 0;
    	
    	for(int i=0;i<WhMstRt.length;i++) {
    		if("ATWH".equals((""+WhMstRt[i][0]).substring(0,4))&&11==(""+WhMstRt[i][0]).length()) {
    			String WST = B00020ToolsTextControl.num_only_String(""+WhMstRt[i][0]);
    			if(7==WST.length()) {
    				int wint = Integer.parseInt(WST);
    				if(WhNo<wint) {
    					WhNo=wint;
    				}
    			}
    		}
    	}
    	WhNo = WhNo+1;
    	String rt = "0000000"+WhNo;
    	rt = "ATWH"+rt.substring(rt.length()-7,rt.length());
    	
    	return rt;
	}
	
}