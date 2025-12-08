import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class M00020UserMstRt{
	public static Object[][] UserMstRt(ArrayList SearchWHCD,ArrayList SearchShippingCompanyCd,ArrayList SearchAuthorityFG,
			ArrayList SearchUserCd,ArrayList SearchUserName,ArrayList SearchCarCd,ArrayList SearchCarName,
			ArrayList SearchPost,ArrayList SearchAdd,ArrayList SearchTel,ArrayList SearchFax,ArrayList SearchMail,ArrayList SearchCom,
			boolean AllSearch){
		Object[][] rt = new Object[0][29];
		boolean SearchKick = false;
		if(AllSearch) {SearchKick = true;}
		
		String sql = "select \n"
				+"(KM0020_USERMST.WHCD) as WHCD,\n"													//倉庫コード
				+"(KM0010_WHMST.WHName) as WHName,\n"												//倉庫名
				+"(KM0020_USERMST.ShippingCompanyCd) as ShippingCompanyCd,\n"						//運送会社CD
				+"(KM0070_SHIPPINGCOMPANYMST.ShippingCompanyName01) as ShippingCompanyName01,\n"	//運送会社名
				+"(KM0020_USERMST.UserCd) as UserCd,\n"												//ユーザーCD
				+"(KM0020_USERMST.PassWord) as PassWord,\n"											//パスワード
				+"(KM0020_USERMST.AuthorityFG) as AuthorityFG,\n"									//権限区分
				+"(KM0020_USERMST.CarCd) as CarCd,\n"												//標準車輛CD
				+"(KM0071_CARMST.CarName01) as CarName01,\n"										//車両名称01
				+"(KM0071_CARMST.CarName02) as CarName02,\n"										//車両名称02
				+"(KM0071_CARMST.CarName03) as CarName03,\n"										//車両名称03
				+"(KM0020_USERMST.UserName01) as UserName01,\n"										//ユーザー名1
				+"(KM0020_USERMST.UserName02) as UserName02,\n"										//ユーザー名2
				+"(KM0020_USERMST.UserName03) as UserName03,\n"										//ユーザー名3
				+"(KM0020_USERMST.Post) as Post,\n"													//郵便番号
				+"(KM0020_USERMST.Add01) as Add01,\n"												//住所1
				+"(KM0020_USERMST.Add02) as Add02,\n"												//住所2
				+"(KM0020_USERMST.Add03) as Add03,\n"												//住所3
				+"(KM0020_USERMST.Tel) as Tel,\n"													//電話番号
				+"(KM0020_USERMST.Fax) as Fax,\n"													//FAX
				+"(KM0020_USERMST.Mail) as Mail,\n"													//メールアドレス
				+"(KM0020_USERMST.Com01) as Com01,\n"												//コメント1
				+"(KM0020_USERMST.Com02) as Com02,\n"												//コメント2
				+"(KM0020_USERMST.Com03) as Com03,\n"												//コメント3
				+"(KM0020_USERMST.EntryDate) as EntryDate,\n"										//登録者コード
				+"(KM0020_USERMST.UpdateDate) as UpdateDate,\n"										//更新者コード
				+"(KM0020_USERMST.EntryUser) as EntryUser,\n"										//データ登録日時
				+"(KM0020_USERMST.UpdateUser) as UpdateUser,\n"										//データ更新日時
				+"(KM0020_USERMST.PTMSCD) as PTMSCD,\n"												//基幹システムユーザーコード
				+"(KM0020_USERMST.DelFg) as DelFg\n"												//削除区分
				+ " from KM0020_USERMST \n"
				+ " left outer join KM0070_SHIPPINGCOMPANYMST\n"
				+ " on(KM0020_USERMST.ShippingCompanyCd = KM0070_SHIPPINGCOMPANYMST.ShippingCompanyCd)\n"
				+ " left outer join KM0071_CARMST\n"
				+ " on(\n"
				+ " KM0020_USERMST.WHCD = KM0071_CARMST.WHCD\n"
				+ "	and KM0020_USERMST.ShippingCompanyCd=KM0071_CARMST.ShippingCompanyCd\n"
				+ " and KM0020_USERMST.CarCd = KM0071_CARMST.CarCd\n"
				+ " )\n"
				+ " left outer join KM0010_WHMST"
				+ " on(\n"
				+ " KM0020_USERMST.WHCD = KM0010_WHMST.WHCD"
				+ " )\n"
				+ " where 1=1";	
		
		if(null!=SearchWHCD && 0<SearchWHCD.size()) {
			SearchKick=true;
			sql = sql + " and(";
			for(int i=0;i<SearchWHCD.size();i++) {
				if(0<i) {sql=sql+" or ";}
				sql = sql + " KM0020_USERMST.WHCD ='"+SearchWHCD.get(i)+"'";
			}
			sql=sql+")";
		}
		if(null!=SearchShippingCompanyCd && 0<SearchShippingCompanyCd.size()) {
			SearchKick=true;
			sql = sql + " and(";
			for(int i=0;i<SearchShippingCompanyCd.size();i++) {
				if(0<i) {sql=sql+" or ";}
				sql = sql + " KM0020_USERMST.ShippingCompanyCd ='"+SearchShippingCompanyCd.get(i)+"'";
			}
			sql=sql+")";
		}
		if(null!=SearchAuthorityFG && 0<SearchAuthorityFG.size()) {
			SearchKick=true;
			sql = sql + " and(";
			for(int i=0;i<SearchAuthorityFG.size();i++) {
				if(0<i) {sql=sql+" or ";}
				sql = sql + " KM0020_USERMST.AuthorityFG ='"+SearchAuthorityFG.get(i)+"'";
			}
			sql=sql+")";
		}
		if(null!=SearchUserCd && 0<SearchUserCd.size()) {
			SearchKick=true;
			sql = sql + " and(";
			for(int i=0;i<SearchUserCd.size();i++) {
				if(0<i) {sql=sql+" or ";}
				sql = sql + " KM0020_USERMST.UserCd ='"+SearchUserCd.get(i)+"'";
			}
			sql=sql+")";
		}
		if(null!=SearchUserName && 0<SearchUserName.size()) {
			SearchKick=true;
			sql = sql + " and(";
			for(int i=0;i<SearchUserName.size();i++) {
				if(0<i) {sql=sql+" or ";}
				sql = sql + " KM0020_USERMST.UserName01 like'%"+SearchUserName.get(i)+"%'";
				sql = sql + " or KM0020_USERMST.UserName02 like'%"+SearchUserName.get(i)+"%'";
				sql = sql + " or KM0020_USERMST.UserName03 like'%"+SearchUserName.get(i)+"%'";
			}
			sql=sql+")";
		}
		if(null!=SearchCarCd && 0<SearchCarCd.size()) {
			SearchKick=true;
			sql = sql + " and(";
			for(int i=0;i<SearchCarCd.size();i++) {
				if(0<i) {sql=sql+" or ";}
				sql = sql + " KM0020_USERMST.CarCd ='"+SearchCarCd.get(i)+"'";
			}
			sql=sql+")";
		}
		if(null!=SearchCarName && 0<SearchCarName.size()) {
			SearchKick=true;
			sql = sql + " and(";
			for(int i=0;i<SearchCarName.size();i++) {
				if(0<i) {sql=sql+" or ";}
				sql = sql + " KM0071_CARMST.CarName01 like '%"+SearchCarName.get(i)+"%'";
				sql = sql + " or KM0071_CARMST.CarName02 like '%"+SearchCarName.get(i)+"%'";
				sql = sql + " or KM0071_CARMST.CarName03 like '%"+SearchCarName.get(i)+"%'";
			}
			sql=sql+")";
		}
		if(null!=SearchPost && 0<SearchPost.size()) {
			SearchKick=true;
			sql = sql + " and(";
			for(int i=0;i<SearchPost.size();i++) {
				if(0<i) {sql=sql+" or ";}
				sql = sql + " KM0020_USERMST.Post like'"+SearchPost.get(i)+"%'";
			}
			sql=sql+")";
		}
		if(null!=SearchAdd && 0<SearchAdd.size()) {
			SearchKick=true;
			sql = sql + " and(";
			for(int i=0;i<SearchAdd.size();i++) {
				if(0<i) {sql=sql+" or ";}
				sql = sql + " KM0020_USERMST.Add01 like'%"+SearchAdd.get(i)+"%'";
				sql = sql + " or KM0020_USERMST.Add02 like'%"+SearchAdd.get(i)+"%'";
				sql = sql + " or KM0020_USERMST.Add03 like'%"+SearchAdd.get(i)+"%'";
			}
			sql=sql+")";
		}
		if(null!=SearchTel && 0<SearchTel.size()) {
			SearchKick=true;
			sql = sql + " and(";
			for(int i=0;i<SearchTel.size();i++) {
				if(0<i) {sql=sql+" or ";}
				sql = sql + " KM0020_USERMST.Tel like '%"+SearchTel.get(i)+"%'";
			}
			sql=sql+")";
		}
		if(null!=SearchFax && 0<SearchFax.size()) {
			SearchKick=true;
			sql = sql + " and(";
			for(int i=0;i<SearchFax.size();i++) {
				if(0<i) {sql=sql+" or ";}
				sql = sql + " KM0020_USERMST.Fax like '%"+SearchFax.get(i)+"%'";
			}
			sql=sql+")";
		}
		if(null!=SearchMail && 0<SearchMail.size()) {
			SearchKick=true;
			sql = sql + " and(";
			for(int i=0;i<SearchMail.size();i++) {
				if(0<i) {sql=sql+" or ";}
				sql = sql + " KM0020_USERMST.Mail like '%"+SearchMail.get(i)+"%'";
			}
			sql=sql+")";
		}
		if(null!=SearchCom && 0<SearchCom.size()) {
			SearchKick=true;
			sql = sql + " and(";
			for(int i=0;i<SearchCom.size();i++) {
				if(0<i) {sql=sql+" or ";}
				sql = sql + " KM0020_USERMST.Com01 like '%"+SearchCom.get(i)+"%'";
				sql = sql + " or KM0020_USERMST.Com02 like '%"+SearchCom.get(i)+"%'";
				sql = sql + " or KM0020_USERMST.Com03 like '%"+SearchCom.get(i)+"%'";
			}
			sql=sql+")";
		}
		
		if(AllSearch) {
			
		}else {
			sql = sql + " and KM0020_USERMST.DelFg = 0";
		}
		
		sql = sql + " order by KM0020_USERMST.ShippingCompanyCd,KM0020_USERMST.UserCd";
		//System.out.println(sql);
		if(SearchKick) {
			A00010DbConnect.DB_CONN("NANKO");
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
				rt = new Object[counter][30];
				counter = 0;
				rset01.beforeFirst();
				while (rset01.next()) {
					if(null==rset01.getString("WHCD")){rt[counter][0]="";}else{rt[counter][0]=rset01.getString("WHCD");}													//倉庫コード
					if(null==rset01.getString("ShippingCompanyCd")){rt[counter][1]="";}else{rt[counter][1]=rset01.getString("ShippingCompanyCd");}							//運送会社CD
					if(null==rset01.getString("ShippingCompanyName01")){rt[counter][2]="";}else{rt[counter][2]=rset01.getString("ShippingCompanyName01");}					//運送会社名
					if(null==rset01.getString("UserCd")){rt[counter][3]="";}else{rt[counter][3]=rset01.getString("UserCd");}												//ユーザーCD
					if(null==rset01.getString("PassWord")){rt[counter][4]="";}else{rt[counter][4]=rset01.getString("PassWord");}											//パスワード
					if(null==rset01.getString("AuthorityFG")){rt[counter][5]="";}else{rt[counter][5]=rset01.getString("AuthorityFG");}										//権限区分
					if(null==rset01.getString("CarCd")){rt[counter][6]="";}else{rt[counter][6]=rset01.getString("CarCd");}													//標準車輛CD
					if(null==rset01.getString("CarName01")){rt[counter][7]="";}else{rt[counter][7]=rset01.getString("CarName01");}											//車両名称01
					if(null==rset01.getString("CarName02")){rt[counter][8]="";}else{rt[counter][8]=rset01.getString("CarName02");}											//車両名称02
					if(null==rset01.getString("CarName03")){rt[counter][9]="";}else{rt[counter][9]=rset01.getString("CarName03");}											//車両名称03
					if(null==rset01.getString("UserName01")){rt[counter][10]="";}else{rt[counter][10]=rset01.getString("UserName01");}										//ユーザー名1
					if(null==rset01.getString("UserName02")){rt[counter][11]="";}else{rt[counter][11]=rset01.getString("UserName02");}										//ユーザー名2
					if(null==rset01.getString("UserName03")){rt[counter][12]="";}else{rt[counter][12]=rset01.getString("UserName03");}										//ユーザー名3
					if(null==rset01.getString("Post")){rt[counter][13]="";}else{rt[counter][13]=rset01.getString("Post");}													//郵便番号
					if(null==rset01.getString("Add01")){rt[counter][14]="";}else{rt[counter][14]=rset01.getString("Add01");}												//住所1
					if(null==rset01.getString("Add02")){rt[counter][15]="";}else{rt[counter][15]=rset01.getString("Add02");}												//住所2
					if(null==rset01.getString("Add03")){rt[counter][16]="";}else{rt[counter][16]=rset01.getString("Add03");}												//住所3
					if(null==rset01.getString("Tel")){rt[counter][17]="";}else{rt[counter][17]=rset01.getString("Tel");}													//電話番号
					if(null==rset01.getString("Fax")){rt[counter][18]="";}else{rt[counter][18]=rset01.getString("Fax");}													//FAX
					if(null==rset01.getString("Mail")){rt[counter][19]="";}else{rt[counter][19]=rset01.getString("Mail");}													//メールアドレス
					if(null==rset01.getString("Com01")){rt[counter][20]="";}else{rt[counter][20]=rset01.getString("Com01");}												//コメント1
					if(null==rset01.getString("Com02")){rt[counter][21]="";}else{rt[counter][21]=rset01.getString("Com02");}												//コメント2
					if(null==rset01.getString("Com03")){rt[counter][22]="";}else{rt[counter][22]=rset01.getString("Com03");}												//コメント3
					if(null==rset01.getTimestamp("EntryDate")){rt[counter][23]="";}else{rt[counter][23]=B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("EntryDate"))[1];}	//データ登録日時
					if(null==rset01.getTimestamp("UpdateDate")){rt[counter][24]="";}else{rt[counter][24]=B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("UpdateDate"))[1];}	//データ更新日時
					if(null==rset01.getString("EntryUser")){rt[counter][25]="";}else{rt[counter][25]=rset01.getString("EntryUser");}		//登録者コード
					if(null==rset01.getString("UpdateUser")){rt[counter][26]="";}else{rt[counter][26]=rset01.getString("UpdateUser");}		//更新者コード
					if(null==rset01.getString("PTMSCD")){rt[counter][27]="";}else{rt[counter][27]=rset01.getString("PTMSCD");}												//基幹システムユーザーコード
					rt[counter][28] = rset01.getInt("DelFg");//削除区分
					if(null==rset01.getString("WHName")){rt[counter][29]="";}else{rt[counter][29]=rset01.getString("WHName");}												//倉庫名
					
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