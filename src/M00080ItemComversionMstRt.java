import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class M00080ItemComversionMstRt{
	/*
	コピペ用
	ArrayList<String> SearchClGpCd = new ArrayList<String>();		//荷主グループコード
	ArrayList<String> SearchClCd = new ArrayList<String>();			//荷主コード
	ArrayList<String> SearchItemCd = new ArrayList<String>();		//商品コード
	ArrayList<String> SearchCLItemCd = new ArrayList<String>();		//荷主商品コード
	ArrayList<String> SearchItemName = new ArrayList<String>();		//商品名
	boolean AllSearch = false;
	
	Object[][] ItemComversionMstRt = M00080ItemComversionMstRt.ItemComversionMstRt(
			SearchClGpCd,			//荷主グループコード
			SearchClCd,				//荷主コード
			SearchItemCd,			//商品コード
			SearchCLItemCd,			//荷主商品コード
			SearchItemName,			//商品名
			AllSearch);
	
	*/
	//戻り値カラム
	public static Object[][] RtItemComversionMstRt(){
		Object[][] RtItemComversionMstRt = {
				 {"ClGpCd"			,(int) 0	,"String"	,"荷主グループコード"}
				,{"CLGpName01"		,(int) 1	,"String"	,"荷主グループ名1"}
				,{"ClCd"			,(int) 2	,"String"	,"荷主コード"}
				,{"CLName01"		,(int) 3	,"String"	,"荷主名"}
				,{"ClItemCd"		,(int) 4	,"String"	,"荷主商品コード"}
				,{"ItemCd"			,(int) 5	,"String"	,"変換先商品コード"}
				,{"PackingType"		,(int) 6	,"String"	,"荷姿タイプ"}
				,{"ItemName01"		,(int) 7	,"String"	,"商品名1"}
				,{"ItemName02"		,(int) 8	,"String"	,"商品名2"}
				,{"ItemName03"		,(int) 9	,"String"	,"商品名3"}
				,{"CtName"			,(int)10	,"String"	,"カートン商品名称"}
				,{"CsName"			,(int)11	,"String"	,"ケース商品名称"}
				,{"PlName"			,(int)12	,"String"	,"パレット商品名称"}
				,{"CtQty"			,(int)13	,"int"		,"カートン入数"}
				,{"CsQty"			,(int)14	,"int"		,"ケース入数"}
				,{"PlQty"			,(int)15	,"int"		,"パレット入数"}
				};
		return RtItemComversionMstRt;
	}
	public static Object[][] ItemComversionMstRt(
			ArrayList<String> SearchClGpCd,				//荷主グループコード
			ArrayList<String> SearchClCd,				//荷主コード
			ArrayList<String> SearchItemCd,				//商品コード
			ArrayList<String> SearchCLItemCd,			//荷主商品コード
			ArrayList<String> SearchItemName,			//商品名
			boolean AllSearch){
		
		SearchClGpCd	= B00150ArrayListControl.ArryListStringUniqueList(SearchClGpCd);
		SearchClCd		= B00150ArrayListControl.ArryListStringUniqueList(SearchClCd);
		SearchItemCd	= B00150ArrayListControl.ArryListStringUniqueList(SearchItemCd);
		SearchCLItemCd	= B00150ArrayListControl.ArryListStringUniqueList(SearchCLItemCd);
		SearchItemName	= B00150ArrayListControl.ArryListStringUniqueList(SearchItemName);
		
		Object[][] rt = new Object[0][16];
		boolean SearchKick = false;
		if(AllSearch) {SearchKick = true;}
		
		String sql = " select \n"
				+"(KM0062_ItemComversionMst.ClGpCd) as ClGpCd,\n"			//荷主グループコード
				+"(KM0031_CLIENT_GROUP.CLGpName01) as CLGpName01,\n"		//荷主グループ名1
				+"(KM0062_ItemComversionMst.ClCd) as ClCd,\n"				//荷主コード
				+"(KM0030_CLIENTMST.CLName01) as CLName01,\n"				//荷主名
				+"(KM0062_ItemComversionMst.ClItemCd) as ClItemCd,\n"		//荷主商品コード
				+"(KM0062_ItemComversionMst.ItemCd) as ItemCd,\n"			//変換先商品コード
				+"(KM0062_ItemComversionMst.PackingType) as PackingType,\n"	//荷姿タイプ
				+"(KM0060_ITEMMST.ItemName01) as ItemName01,\n"				//商品名1
				+"(KM0060_ITEMMST.ItemName02) as ItemName02,\n"				//商品名2
				+"(KM0060_ITEMMST.ItemName03) as ItemName03,\n"				//商品名3
				+"(KM0061_ITEMMSTSUB.CtName) as CtName,\n"					//カートン商品名称
				+"(KM0061_ITEMMSTSUB.CsName) as CsName,\n"					//ケース商品名称
				+"(KM0061_ITEMMSTSUB.PlName) as PlName,\n"					//パレット商品名称
				+"(KM0061_ITEMMSTSUB.CtQty) as CtQty,\n"					//カートン入数
				+"(KM0061_ITEMMSTSUB.CsQty) as CsQty,\n"					//ケース入数
				+"(KM0061_ITEMMSTSUB.PlQty) as PlQty\n"						//パレット入数
				+" from "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0062_ItemComversionMst \n"
				+" left outer join "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0060_ITEMMST \n"
				+"on(KM0062_ItemComversionMst.ClGpCd = KM0060_ITEMMST.ClGpCd and KM0062_ItemComversionMst.ItemCd = KM0060_ITEMMST.ItemCd"
				+")\n"
				+ " left outer join "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0061_ITEMMSTSUB \n"
				+ " on("
				+ " KM0062_ItemComversionMst.ClGpCd = KM0061_ITEMMSTSUB.ClGpCd and KM0062_ItemComversionMst.ItemCd = KM0061_ITEMMSTSUB.ItemCd"
				+ ")\n"
				+ " left outer join "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP"
				+ " on("
				+ " KM0062_ItemComversionMst.ClGpCd = KM0031_CLIENT_GROUP.ClGpCD"
				+ ")\n"
				+ " left outer join "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0030_CLIENTMST"
				+ " on("
				+ " KM0062_ItemComversionMst.ClGpCd = KM0030_CLIENTMST.ClGpCD"
				+ " and KM0062_ItemComversionMst.ClCd = KM0030_CLIENTMST.cl_cd"
				+ ")\n"
				+" where 1=1 \n";
		
		if(null!=SearchClGpCd&&0<SearchClGpCd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchClGpCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KM0062_ItemComversionMst.ClGpCd = ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchClCd&&0<SearchClCd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchClCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KM0062_ItemComversionMst.ClCd = ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchItemCd&&0<SearchItemCd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchItemCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KM0062_ItemComversionMst.ItemCd = ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchCLItemCd&&0<SearchCLItemCd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchCLItemCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KM0062_ItemComversionMst.ClItemCd = ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchItemName&&0<SearchItemName.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchItemName.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KM0060_ITEMMST.ItemName01 like ?";
				sql = sql + " or KM0060_ITEMMST.ItemName02 like ?";
				sql = sql + " or KM0060_ITEMMST.ItemName03 like ?";
				sql = sql + " or KM0061_ITEMMSTSUB.CtName like ?";
				sql = sql + " or KM0061_ITEMMSTSUB.CsName like ?";
				sql = sql + " or KM0061_ITEMMSTSUB.PlName like ?";
			}
			sql = sql + ")";
		}
		sql = sql+" order by KM0062_ItemComversionMst.ClGpCd,KM0062_ItemComversionMst.ClCd,KM0062_ItemComversionMst.ClItemCd\n";
		if(SearchKick) {
			A00010DbConnect.DB_CONN("NYANKO");
			ResultSet rset01 = null;
			PreparedStatement stmt01 = null;
			try {
				stmt01 = A00010DbConnect.conn.prepareStatement(sql);
				int StmtCount = 0;
				
				if(null!=SearchClGpCd&&0<SearchClGpCd.size()){
					for(int i=0;i<SearchClGpCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchClGpCd.get(i)+"");
					}
				}
				if(null!=SearchClCd&&0<SearchClCd.size()){
					for(int i=0;i<SearchClCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchClCd.get(i)+"");
					}
				}
				if(null!=SearchItemCd&&0<SearchItemCd.size()){
					for(int i=0;i<SearchItemCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchItemCd.get(i)+"");
					}
				}
				if(null!=SearchCLItemCd&&0<SearchCLItemCd.size()){
					for(int i=0;i<SearchCLItemCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchCLItemCd.get(i)+"");
					}
				}
				if(null!=SearchItemName&&0<SearchItemName.size()){
					for(int i=0;i<SearchItemName.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchItemName.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchItemName.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchItemName.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchItemName.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchItemName.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchItemName.get(i)+"%");
					}
				}
				rset01 = stmt01.executeQuery();
				
				int counter = 0;
				rset01.beforeFirst();
				while (rset01.next()) {
					counter=counter+1;
				}
				rt = new Object[counter][16];
				counter = 0;
				rset01.beforeFirst();
				while (rset01.next()) {
					if(null==rset01.getString("ClGpCd")){		rt[counter][ 0]="";}else{rt[counter][ 0]=rset01.getString("ClGpCd");}		//荷主グループコード
					if(null==rset01.getString("CLGpName01")){	rt[counter][ 1]="";}else{rt[counter][ 1]=rset01.getString("CLGpName01");}	//荷主グループ名1
					if(null==rset01.getString("ClCd")){			rt[counter][ 2]="";}else{rt[counter][ 2]=rset01.getString("ClCd");}			//荷主コード
					if(null==rset01.getString("CLName01")){		rt[counter][ 3]="";}else{rt[counter][ 3]=rset01.getString("CLName01");}		//荷主名
					if(null==rset01.getString("ClItemCd")){		rt[counter][ 4]="";}else{rt[counter][ 4]=rset01.getString("ClItemCd");}		//荷主商品コード
					if(null==rset01.getString("ItemCd")){		rt[counter][ 5]="";}else{rt[counter][ 5]=rset01.getString("ItemCd");}		//変換先商品コード
					if(null==rset01.getString("PackingType")){	rt[counter][ 6]="";}else{rt[counter][ 6]=rset01.getString("PackingType");}	//荷姿タイプ
					if(null==rset01.getString("ItemName01")){	rt[counter][ 7]="";}else{rt[counter][ 7]=rset01.getString("ItemName01");}	//商品名1
					if(null==rset01.getString("ItemName02")){	rt[counter][ 8]="";}else{rt[counter][ 8]=rset01.getString("ItemName02");}	//商品名2
					if(null==rset01.getString("ItemName03")){	rt[counter][ 9]="";}else{rt[counter][ 9]=rset01.getString("ItemName03");}	//商品名3
					if(null==rset01.getString("CtName")){		rt[counter][10]="";}else{rt[counter][10]=rset01.getString("CtName");}		//カートン商品名称
					if(null==rset01.getString("CsName")){		rt[counter][11]="";}else{rt[counter][11]=rset01.getString("CsName");}		//ケース商品名称
					if(null==rset01.getString("PlName")){		rt[counter][12]="";}else{rt[counter][12]=rset01.getString("PlName");}		//パレット商品名称
					rt[counter][13]=rset01.getInt("CtQty");					//カートン入数
					rt[counter][14]=rset01.getInt("CsQty");					//ケース入数
					rt[counter][15]=rset01.getInt("PlQty");					//パレット入数
					
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