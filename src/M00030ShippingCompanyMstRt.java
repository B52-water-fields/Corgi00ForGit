import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
public class M00030ShippingCompanyMstRt{
	public static Object[][] ShippingCompanyMstRt(
			ArrayList SearchShippingCompanyCd,ArrayList SearchCompanyName,ArrayList SearchPost,ArrayList SearchAdd,
			ArrayList SearchTel,ArrayList SearchFax,ArrayList SearchMail,ArrayList SearchCom,boolean AllSearch){
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
				+ " from KM0070_SHIPPINGCOMPANYMST"
				+ " where 1=1 ";
		
		if(null!=SearchShippingCompanyCd && 0<SearchShippingCompanyCd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchShippingCompanyCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + "KM0070_SHIPPINGCOMPANYMST.ShippingCompanyCd='"+SearchShippingCompanyCd.get(i)+"'";
			}
			sql = sql + ")";
		}
		if(null!=SearchCompanyName && 0<SearchCompanyName.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchCompanyName.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + "KM0070_SHIPPINGCOMPANYMST.ShippingCompanyName01 like '%"+SearchCompanyName.get(i)+"%'";
				sql = sql + " or KM0070_SHIPPINGCOMPANYMST.ShippingCompanyName02 like '%"+SearchCompanyName.get(i)+"%'";
				sql = sql + " or KM0070_SHIPPINGCOMPANYMST.ShippingCompanyName03 like '%"+SearchCompanyName.get(i)+"%'";
			}
			sql = sql + ")";
		}
		if(null!=SearchPost && 0<SearchPost.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchPost.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + "KM0070_SHIPPINGCOMPANYMST.Post like '%"+B00020ToolsTextControl.num_only_String(""+SearchPost.get(i))+"%'";
			}
			sql = sql + ")";
		}
		if(null!=SearchAdd && 0<SearchAdd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchAdd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + "KM0070_SHIPPINGCOMPANYMST.Add01 like '%"+SearchAdd.get(i)+"%'";
				sql = sql + " or KM0070_SHIPPINGCOMPANYMST.Add02 like '%"+SearchAdd.get(i)+"%'";
				sql = sql + " or KM0070_SHIPPINGCOMPANYMST.Add03 like '%"+SearchAdd.get(i)+"%'";
			}
			sql = sql + ")";
		}
		if(null!=SearchTel && 0<SearchTel.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchTel.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + "KM0070_SHIPPINGCOMPANYMST.Tel like '%"+B00020ToolsTextControl.num_only_String(""+SearchTel.get(i))+"%'";
			}
			sql = sql + ")";
		}
		if(null!=SearchFax && 0<SearchFax.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchFax.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + "KM0070_SHIPPINGCOMPANYMST.Fax like '%"+B00020ToolsTextControl.num_only_String(""+SearchFax.get(i))+"%'";
			}
			sql = sql + ")";
		}
		if(null!=SearchMail && 0<SearchMail.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchMail.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + "KM0070_SHIPPINGCOMPANYMST.Mail like '%"+SearchMail.get(i)+"%'";
			}
			sql = sql + ")";
		}
		if(null!=SearchCom && 0<SearchCom.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchCom.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + "KM0070_SHIPPINGCOMPANYMST.Com01 like '%"+SearchCom.get(i)+"%'";
				sql = sql + " or KM0070_SHIPPINGCOMPANYMST.Com02 like '%"+SearchCom.get(i)+"%'";
				sql = sql + " or KM0070_SHIPPINGCOMPANYMST.Com03 like '%"+SearchCom.get(i)+"%'";
			}
			sql = sql + ")";
		}
		//System.out.println(sql);
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

				rt = new Object[counter][22];
				counter = 0;
				rset01.beforeFirst();
				while (rset01.next()) {
					if(null==rset01.getString("ShippingCompanyCd")){rt[counter][0]="";}else{rt[counter][0]=rset01.getString("ShippingCompanyCd");}			//運送会社CD
					if(null==rset01.getString("ShippingCompanyName01")){rt[counter][1]="";}else{rt[counter][1]=rset01.getString("ShippingCompanyName01");}	//運送会社名1
					if(null==rset01.getString("ShippingCompanyName02")){rt[counter][2]="";}else{rt[counter][2]=rset01.getString("ShippingCompanyName02");}	//運送会社名2
					if(null==rset01.getString("ShippingCompanyName03")){rt[counter][3]="";}else{rt[counter][3]=rset01.getString("ShippingCompanyName03");}	//運送会社名3
					if(null==rset01.getString("Post")){rt[counter][4]="";}else{rt[counter][4]=rset01.getString("Post");}						//運送会社郵便
					if(null==rset01.getString("Add01")){rt[counter][5]="";}else{rt[counter][5]=rset01.getString("Add01");}						//運送会社住所1
					if(null==rset01.getString("Add02")){rt[counter][6]="";}else{rt[counter][6]=rset01.getString("Add02");}						//運送会社住所2
					if(null==rset01.getString("Add03")){rt[counter][7]="";}else{rt[counter][7]=rset01.getString("Add03");}						//運送会社住所3
					if(null==rset01.getString("Tel")){rt[counter][8]="";}else{rt[counter][8]=rset01.getString("Tel");}							//運送会社電話
					if(null==rset01.getString("Fax")){rt[counter][9]="";}else{rt[counter][9]=rset01.getString("Fax");}							//運送会社FAX
					if(null==rset01.getString("Mail")){rt[counter][10]="";}else{rt[counter][10]=rset01.getString("Mail");}						//運送会社MAIL
					if(null==rset01.getString("Com01")){rt[counter][11]="";}else{rt[counter][11]=rset01.getString("Com01");}					//コメント1
					if(null==rset01.getString("Com02")){rt[counter][12]="";}else{rt[counter][12]=rset01.getString("Com02");}					//コメント2
					if(null==rset01.getString("Com03")){rt[counter][13]="";}else{rt[counter][13]=rset01.getString("Com03");}					//コメント3
					if(null==rset01.getString("ShimeDate")){rt[counter][14]="";}else{rt[counter][14]=rset01.getString("ShimeDate");}			//締日
					if(null==rset01.getString("ShimeBasis")){rt[counter][15]="";}else{rt[counter][15]=rset01.getString("ShimeBasis");}			//請求基準
					if(null==rset01.getTimestamp("EntryDate")){rt[counter][16]="";}else{rt[counter][16]=B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("EntryDate"))[1];}		//データ登録日時
					if(null==rset01.getTimestamp("UpdateDate")){rt[counter][17]="";}else{rt[counter][17]=B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("UpdateDate"))[1];}		//データ更新日時
					if(null==rset01.getString("EntryUser")){rt[counter][18]="";}else{rt[counter][18]=rset01.getString("EntryUser");}			//登録者コード
					if(null==rset01.getString("UpdateUser")){rt[counter][19]="";}else{rt[counter][19]=rset01.getString("UpdateUser");}			//更新者コード
					if(null==rset01.getString("PTMSCD")){rt[counter][20]="";}else{rt[counter][20]=rset01.getString("PTMSCD");}					//基幹システム傭車コード
					if(null==rset01.getString("ExportDataType")){rt[counter][21]="";}else{rt[counter][21]=rset01.getString("ExportDataType");}	//データ抽出タイプ
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