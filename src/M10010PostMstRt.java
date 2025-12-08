import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class M10010PostMstRt{
	public static Object[][] PostRt(
			ArrayList SearchPOST,
			ArrayList SearchAdd,
			boolean AllSearch){
		//郵便番号を受け取って郵便番号マスタから検索結果を返却する
		Object[][] rt = new Object[0][5];

		boolean SearchKick = false;
		if(AllSearch) {SearchKick = true;}

		String sql = "select "
				+"(M0010_PostMst.POST) as POST,"
				+"(M0010_PostMst.PREFECTURES) as PREFECTURES,"
				+"(M0010_PostMst.MUNICI01) as MUNICI01,"
				+"(M0010_PostMst.MUNICI02) as MUNICI02,"
				+"(M0010_PostMst.MUNICIPALITY_CD) as MUNICIPALITY_CD"
				+ " from M0010_PostMst"
				+ " where 1=1";
		if(null!=SearchPOST && 0<SearchPOST.size()){
			SearchKick = true;
			for(int i=0;i<SearchPOST.size();i++) {
				String WST = ""+SearchPOST.get(i);
				WST = B00020ToolsTextControl.num_only_String(WST);
				if(i==0) {sql = sql + " and(";}else {sql = sql + " or ";}
				sql = sql + "M0010_PostMst.POST like '"+WST+"%'";
			}
			sql=sql+")";
		}

		if(null!=SearchAdd && 0<SearchAdd.size()){
			SearchKick = true;
			for(int i=0;i<SearchAdd.size();i++) {
				if(i==0) {sql = sql + " and(";}else {sql = sql + " or ";}
				sql = sql + "M0010_PostMst.PREFECTURES like '%"+SearchAdd.get(i)+"%'";
				sql = sql + " or M0010_PostMst.MUNICI01 like '%"+SearchAdd.get(i)+"%'";
				sql = sql + " or M0010_PostMst.MUNICI02 like '%"+SearchAdd.get(i)+"%'";
			}
			sql=sql+")";
		}
		if(SearchKick) {
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
	public static Object[][] MunicipalityRt(ArrayList SearchName,boolean AllSearch){
	//市区町村マスタ返却
		Object[][] rt = new Object[0][3];
		boolean KickFg=false;
		if(AllSearch) {KickFg=true;}
		String sql = "select "
				+"max(M0010_PostMst.PREFECTURES) as PREFECTURES,"
				+"max(M0010_PostMst.MUNICI01) as MUNICI01,"
				+"(M0010_PostMst.MUNICIPALITY_CD) as MUNICIPALITY_CD"
				+ " from M0010_PostMst"
				+ " where 1=1";
		if(null!=SearchName&&0<SearchName.size()) {
			KickFg=true;
			sql = sql + " and(";
			for(int i=0;i<SearchName.size();i++) {
				if(i>0) {
					sql = sql + " or ";
				}
				sql = sql + "CONCAT (M0010_PostMst.PREFECTURES,M0010_PostMst.MUNICI01,M0010_PostMst.MUNICI02) like '%"+SearchName.get(i)+"%'";
			}
			sql = sql+")";
		}
		
		sql = sql + " group by M0010_PostMst.MUNICIPALITY_CD order by M0010_PostMst.MUNICIPALITY_CD";
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
	public static Object[][] PrefecuturesRt(ArrayList SearchName,boolean AllSearch){
		//県マスタ返却
			Object[][] rt = new Object[0][2];
			boolean KickFg=false;
			if(AllSearch) {KickFg=true;}
			String sql = "select "
					+"(M0010_PostMst.PREFECTURES) as PREFECTURES,"
					+"(M0010_PostMst.MUNICIPALITY_CD) as MUNICIPALITY_CD,"
					+"LEFT(M0010_PostMst.MUNICIPALITY_CD,2) as PREFECTURES_CD"
					+ " from M0010_PostMst"
					+ " where 1=1";
			if(null!=SearchName&&0<SearchName.size()) {
				KickFg=true;
				sql = sql + " and(";
				for(int i=0;i<SearchName.size();i++) {
					if(i>0) {
						sql = sql + " or ";
					}
					sql = sql + "M0010_PostMst.PREFECTURES like '%"+SearchName.get(i)+"%'";
				}
				sql = sql+")";
			}
			
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
}