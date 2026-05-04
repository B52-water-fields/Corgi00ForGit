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
			
	String GetClGpCd		= (String)ItemComversionMstRt[i][M00080ItemComversionMstRt.ColClGpCd];		//荷主グループコード
	String GetCLGpName01	= (String)ItemComversionMstRt[i][M00080ItemComversionMstRt.ColCLGpName01];	//荷主グループ名1
	String GetClCd			= (String)ItemComversionMstRt[i][M00080ItemComversionMstRt.ColClCd];		//荷主コード
	String GetCLName01		= (String)ItemComversionMstRt[i][M00080ItemComversionMstRt.ColCLName01];	//荷主名
	String GetClItemCd		= (String)ItemComversionMstRt[i][M00080ItemComversionMstRt.ColClItemCd];	//荷主商品コード
	String GetItemCd		= (String)ItemComversionMstRt[i][M00080ItemComversionMstRt.ColItemCd];		//変換先商品コード
	String GetPackingType	= (String)ItemComversionMstRt[i][M00080ItemComversionMstRt.ColPackingType];	//荷姿タイプ
	String GetItemName01	= (String)ItemComversionMstRt[i][M00080ItemComversionMstRt.ColItemName01];	//商品名1
	String GetItemName02	= (String)ItemComversionMstRt[i][M00080ItemComversionMstRt.ColItemName02];	//商品名2
	String GetItemName03	= (String)ItemComversionMstRt[i][M00080ItemComversionMstRt.ColItemName03];	//商品名3
	String GetCtName		= (String)ItemComversionMstRt[i][M00080ItemComversionMstRt.ColCtName];		//カートン商品名称
	String GetCsName		= (String)ItemComversionMstRt[i][M00080ItemComversionMstRt.ColCsName];		//ケース商品名称
	String GetPlName		= (String)ItemComversionMstRt[i][M00080ItemComversionMstRt.ColPlName];		//パレット商品名称
	int GetCtQty			= (int)ItemComversionMstRt[i][M00080ItemComversionMstRt.ColCtQty];			//カートン入数
	int GetCsQty			= (int)ItemComversionMstRt[i][M00080ItemComversionMstRt.ColCsQty];			//ケース入数
	int GetPlQty			= (int)ItemComversionMstRt[i][M00080ItemComversionMstRt.ColPlQty];			//パレット入数
	
	*/
	//戻り値カラム
	static final int ColClGpCd			= (int) 0;	//荷主グループコード
	static final int ColCLGpName01	= (int) 1;	//荷主グループ名1
	static final int ColClCd			= (int) 2;	//荷主コード
	static final int ColCLName01		= (int) 3;	//荷主名
	static final int ColClItemCd		= (int) 4;	//荷主商品コード
	static final int ColItemCd			= (int) 5;	//変換先商品コード
	static final int ColPackingType	= (int) 6;	//荷姿タイプ
	static final int ColItemName01	= (int) 7;	//商品名1
	static final int ColItemName02	= (int) 8;	//商品名2
	static final int ColItemName03	= (int) 9;	//商品名3
	static final int ColCtName			= (int)10;	//カートン商品名称
	static final int ColCsName			= (int)11;	//ケース商品名称
	static final int ColPlName			= (int)12;	//パレット商品名称
	static final int ColCtQty			= (int)13;	//カートン入数
	static final int ColCsQty			= (int)14;	//ケース入数
	static final int ColPlQty			= (int)15;	//パレット入数
	
	public static Object[][] RtItemComversionMstRt(){
		Object[][] RtItemComversionMstRt = {
				 {"ClGpCd"			,ColClGpCd			,"String"	,"荷主グループコード"}
				,{"CLGpName01"		,ColCLGpName01	,"String"	,"荷主グループ名1"}
				,{"ClCd"			,ColClCd			,"String"	,"荷主コード"}
				,{"CLName01"		,ColCLName01		,"String"	,"荷主名"}
				,{"ClItemCd"		,ColClItemCd		,"String"	,"荷主商品コード"}
				,{"ItemCd"			,ColItemCd			,"String"	,"変換先商品コード"}
				,{"PackingType"		,ColPackingType	,"String"	,"荷姿タイプ"}
				,{"ItemName01"		,ColItemName01	,"String"	,"商品名1"}
				,{"ItemName02"		,ColItemName02	,"String"	,"商品名2"}
				,{"ItemName03"		,ColItemName03	,"String"	,"商品名3"}
				,{"CtName"			,ColCtName			,"String"	,"カートン商品名称"}
				,{"CsName"			,ColCsName			,"String"	,"ケース商品名称"}
				,{"PlName"			,ColPlName			,"String"	,"パレット商品名称"}
				,{"CtQty"			,ColCtQty			,"int"		,"カートン入数"}
				,{"CsQty"			,ColCsQty			,"int"		,"ケース入数"}
				,{"PlQty"			,ColPlQty			,"int"		,"パレット入数"}
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
					if(null==rset01.getString("ClGpCd")){		rt[counter][ColClGpCd]			="";}else{rt[counter][ColClGpCd]		=rset01.getString("ClGpCd");}		//荷主グループコード
					if(null==rset01.getString("CLGpName01")){	rt[counter][ColCLGpName01]	="";}else{rt[counter][ColCLGpName01]	=rset01.getString("CLGpName01");}	//荷主グループ名1
					if(null==rset01.getString("ClCd")){			rt[counter][ColClCd]			="";}else{rt[counter][ColClCd]			=rset01.getString("ClCd");}			//荷主コード
					if(null==rset01.getString("CLName01")){		rt[counter][ColCLName01]		="";}else{rt[counter][ColCLName01]		=rset01.getString("CLName01");}		//荷主名
					if(null==rset01.getString("ClItemCd")){		rt[counter][ColClItemCd]		="";}else{rt[counter][ColClItemCd]		=rset01.getString("ClItemCd");}		//荷主商品コード
					if(null==rset01.getString("ItemCd")){		rt[counter][ColItemCd]			="";}else{rt[counter][ColItemCd]		=rset01.getString("ItemCd");}		//変換先商品コード
					if(null==rset01.getString("PackingType")){	rt[counter][ColPackingType]	="";}else{rt[counter][ColPackingType]	=rset01.getString("PackingType");}	//荷姿タイプ
					if(null==rset01.getString("ItemName01")){	rt[counter][ColItemName01]	="";}else{rt[counter][ColItemName01]	=rset01.getString("ItemName01");}	//商品名1
					if(null==rset01.getString("ItemName02")){	rt[counter][ColItemName02]	="";}else{rt[counter][ColItemName02]	=rset01.getString("ItemName02");}	//商品名2
					if(null==rset01.getString("ItemName03")){	rt[counter][ColItemName03]	="";}else{rt[counter][ColItemName03]	=rset01.getString("ItemName03");}	//商品名3
					if(null==rset01.getString("CtName")){		rt[counter][ColCtName]			="";}else{rt[counter][ColCtName]		=rset01.getString("CtName");}		//カートン商品名称
					if(null==rset01.getString("CsName")){		rt[counter][ColCsName]			="";}else{rt[counter][ColCsName]		=rset01.getString("CsName");}		//ケース商品名称
					if(null==rset01.getString("PlName")){		rt[counter][ColPlName]			="";}else{rt[counter][ColPlName]		=rset01.getString("PlName");}		//パレット商品名称
					rt[counter][ColCtQty]=rset01.getInt("CtQty");					//カートン入数
					rt[counter][ColCsQty]=rset01.getInt("CsQty");					//ケース入数
					rt[counter][ColPlQty]=rset01.getInt("PlQty");					//パレット入数
					
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