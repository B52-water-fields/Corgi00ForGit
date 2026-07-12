import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class M100_ItemComversionMstRt{
	/*
	コピペ用
	ArrayList<String> SearchClGpCd = new ArrayList<String>();		//荷主グループコード
	ArrayList<String> SearchClCd = new ArrayList<String>();			//荷主コード
	ArrayList<String> SearchItemCd = new ArrayList<String>();		//商品コード
	ArrayList<String> SearchCLItemCd = new ArrayList<String>();		//荷主商品コード
	ArrayList<String> SearchItemName = new ArrayList<String>();		//商品名
	boolean AllSearch = false;
	
	Object[][] ItemComversionMstRt = M100_ItemComversionMstRt.ItemComversionMstRt(
			SearchClGpCd,			//荷主グループコード
			SearchClCd,				//荷主コード
			SearchItemCd,			//商品コード
			SearchCLItemCd,			//荷主商品コード
			SearchItemName,			//商品名
			AllSearch);
			
	String GetClGpCd		= (String)ItemComversionMstRt[i][M100_ItemComversionMstRt.ColClGpCd];		//荷主グループコード
	String GetCLGpName01	= (String)ItemComversionMstRt[i][M100_ItemComversionMstRt.ColCLGpName01];	//荷主グループ標記名
	String GetClCd			= (String)ItemComversionMstRt[i][M100_ItemComversionMstRt.ColClCd];			//荷主コード
	String GetCLName01		= (String)ItemComversionMstRt[i][M100_ItemComversionMstRt.ColCLName01];		//荷主名
	String GetClItemCd		= (String)ItemComversionMstRt[i][M100_ItemComversionMstRt.ColClItemCd];		//荷主商品コード
	String GetItemCd		= (String)ItemComversionMstRt[i][M100_ItemComversionMstRt.ColItemCd];		//変換先商品コード
	String GetPackingType	= (int)ItemComversionMstRt[i][M100_ItemComversionMstRt.ColPackingType];		//荷姿タイプ
	String GetItemName01	= (String)ItemComversionMstRt[i][M100_ItemComversionMstRt.ColItemName01];	//商品表記名
	String GetItemName02	= (String)ItemComversionMstRt[i][M100_ItemComversionMstRt.ColItemName02];	//商品正式名
	String GetItemName03	= (String)ItemComversionMstRt[i][M100_ItemComversionMstRt.ColItemName03];	//商品略名
	String GetCtName		= (String)ItemComversionMstRt[i][M100_ItemComversionMstRt.ColCtName];		//カートン商品名称
	String GetCsName		= (String)ItemComversionMstRt[i][M100_ItemComversionMstRt.ColCsName];		//ケース商品名称
	String GetPlName		= (String)ItemComversionMstRt[i][M100_ItemComversionMstRt.ColPlName];		//パレット商品名称
	int GetCtQty			= (int)ItemComversionMstRt[i][M100_ItemComversionMstRt.ColCtQty];			//カートン入数
	int GetCsQty			= (int)ItemComversionMstRt[i][M100_ItemComversionMstRt.ColCsQty];			//ケース入数
	int GetPlQty			= (int)ItemComversionMstRt[i][M100_ItemComversionMstRt.ColPlQty];			//パレット入数
	String GetUnitName		= (String)ItemComversionMstRt[i][M100_ItemComversionMstRt.ColUnitName];		//商品単位
	String GetCtUnitName	= (String)ItemComversionMstRt[i][M100_ItemComversionMstRt.ColCtUnitName];	//カートン商品単位
	String GetCsUnitName	= (String)ItemComversionMstRt[i][M100_ItemComversionMstRt.ColCsUnitName];	//ケース商品単位
	String GetPlUnitName	= (String)ItemComversionMstRt[i][M100_ItemComversionMstRt.ColPlUnitName];	//パレット商品単位
	
	*/
	//戻り値カラム
	static final int ColClGpCd			= (int) 0;	//荷主グループコード
	static final int ColCLGpName01	= (int) 1;	//荷主グループ標記名
	static final int ColClCd			= (int) 2;	//荷主コード
	static final int ColCLName01		= (int) 3;	//荷主名
	static final int ColClItemCd		= (int) 4;	//荷主商品コード
	static final int ColItemCd			= (int) 5;	//変換先商品コード
	static final int ColPackingType	= (int) 6;	//荷姿タイプ
	static final int ColItemName01	= (int) 7;	//商品表記名
	static final int ColItemName02	= (int) 8;	//商品正式名
	static final int ColItemName03	= (int) 9;	//商品略名
	static final int ColCtName			= (int)10;	//カートン商品名称
	static final int ColCsName			= (int)11;	//ケース商品名称
	static final int ColPlName			= (int)12;	//パレット商品名称
	static final int ColCtQty			= (int)13;	//カートン入数
	static final int ColCsQty			= (int)14;	//ケース入数
	static final int ColPlQty			= (int)15;	//パレット入数
	static final int ColUnitName		= (int)16;	//商品単位
	static final int ColCtUnitName	= (int)17;	//カートン商品単位
	static final int ColCsUnitName	= (int)18;	//ケース商品単位
	static final int ColPlUnitName	= (int)19;	//パレット商品単位
	
	public static Object[][] RtItemComversionMstRt(){
		Object[][] RtItemComversionMstRt = {
				 {"ClGpCd"			,ColClGpCd			,"String"	,"荷主グループCD"		,"Key"}
				,{"CLGpName01"		,ColCLGpName01	,"String"	,"荷主グループ標記名"	,""}
				,{"ClCd"			,ColClCd			,"String"	,"荷主CD"				,"Key"}
				,{"CLName01"		,ColCLName01		,"String"	,"荷主名"				,""}
				,{"ClItemCd"		,ColClItemCd		,"String"	,"荷主商品CD"			,"Key"}
				,{"ItemCd"			,ColItemCd			,"String"	,"変換先商品CD"			,""}
				,{"PackingType"		,ColPackingType	,"int"		,"荷姿タイプ"			,""}
				,{"ItemName01"		,ColItemName01	,"String"	,"商品表記名"			,""}
				,{"ItemName02"		,ColItemName02	,"String"	,"商品正式名"			,""}
				,{"ItemName03"		,ColItemName03	,"String"	,"商品略名"				,""}
				,{"CtName"			,ColCtName			,"String"	,"カートン商品名称"		,""}
				,{"CsName"			,ColCsName			,"String"	,"ケース商品名称"		,""}
				,{"PlName"			,ColPlName			,"String"	,"パレット商品名称"		,""}
				,{"CtQty"			,ColCtQty			,"int"		,"カートン入数"			,""}
				,{"CsQty"			,ColCsQty			,"int"		,"ケース入数"			,""}
				,{"PlQty"			,ColPlQty			,"int"		,"パレット入数"			,""}
				,{"UnitName"		,ColUnitName		,"String"	,"商品単位"				,""}
				,{"CtUnitName"		,ColCtUnitName	,"String"	,"カートン商品単位"		,""}
				,{"CsUnitName"		,ColCsUnitName	,"String"	,"ケース商品単位"		,""}
				,{"PlUnitName"		,ColPlUnitName	,"String"	,"パレット商品単位"		,""}
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
		
		SearchClGpCd	= B100_ArrayListControl.ArryListStringUniqueList(SearchClGpCd);
		SearchClCd		= B100_ArrayListControl.ArryListStringUniqueList(SearchClCd);
		SearchItemCd	= B100_ArrayListControl.ArryListStringUniqueList(SearchItemCd);
		SearchCLItemCd	= B100_ArrayListControl.ArryListStringUniqueList(SearchCLItemCd);
		SearchItemName	= B100_ArrayListControl.ArryListStringUniqueList(SearchItemName);
		
		Object[][] rt = new Object[0][RtItemComversionMstRt().length];
		boolean SearchKick = false;
		if(AllSearch) {SearchKick = true;}
		
		String sql = " select \n"
				+"(KM0062_ItemComversionMst.ClGpCd) as ClGpCd,\n"			//荷主グループコード
				+"(KM0031_CLIENT_GROUP.CLGpName01) as CLGpName01,\n"		//荷主グループ標記名
				+"(KM0062_ItemComversionMst.ClCd) as ClCd,\n"				//荷主コード
				+"(KM0030_CLIENTMST.CLName01) as CLName01,\n"				//荷主名
				+"(KM0062_ItemComversionMst.ClItemCd) as ClItemCd,\n"		//荷主商品コード
				+"(KM0062_ItemComversionMst.ItemCd) as ItemCd,\n"			//変換先商品コード
				+"(KM0062_ItemComversionMst.PackingType) as PackingType,\n"	//荷姿タイプ
				+"(KM0060_ITEMMST.ItemName01) as ItemName01,\n"				//商品表記名
				+"(KM0060_ITEMMST.ItemName02) as ItemName02,\n"				//商品正式名
				+"(KM0060_ITEMMST.ItemName03) as ItemName03,\n"				//商品略名
				+"(KM0061_ITEMMSTSUB.CtName) as CtName,\n"					//カートン商品名称
				+"(KM0061_ITEMMSTSUB.CsName) as CsName,\n"					//ケース商品名称
				+"(KM0061_ITEMMSTSUB.PlName) as PlName,\n"					//パレット商品名称
				+"(KM0061_ITEMMSTSUB.CtQty) as CtQty,\n"					//カートン入数
				+"(KM0061_ITEMMSTSUB.CsQty) as CsQty,\n"					//ケース入数
				+"(KM0061_ITEMMSTSUB.PlQty) as PlQty,\n"					//パレット入数
				+"(KM0060_ITEMMST.UnitName) as UnitName,\n"					//商品単位
				+"(KM0061_ITEMMSTSUB.CtUnitName) as CtUnitName,\n"			//カートン商品単位
				+"(KM0061_ITEMMSTSUB.CsUnitName) as CsUnitName,\n"			//ケース商品単位
				+"(KM0061_ITEMMSTSUB.PlUnitName) as PlUnitName\n"			//パレット商品単位
				+" from "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0062_ItemComversionMst \n"
				+" left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0060_ITEMMST \n"
				+"on(KM0062_ItemComversionMst.ClGpCd = KM0060_ITEMMST.ClGpCd and KM0062_ItemComversionMst.ItemCd = KM0060_ITEMMST.ItemCd"
				+")\n"
				+ " left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0061_ITEMMSTSUB \n"
				+ " on("
				+ " KM0062_ItemComversionMst.ClGpCd = KM0061_ITEMMSTSUB.ClGpCd and KM0062_ItemComversionMst.ItemCd = KM0061_ITEMMSTSUB.ItemCd"
				+ ")\n"
				+ " left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP"
				+ " on("
				+ " KM0062_ItemComversionMst.ClGpCd = KM0031_CLIENT_GROUP.ClGpCD"
				+ ")\n"
				+ " left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0030_CLIENTMST"
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
			A100_DbConnect.DB_CONN("NYANKO");
			ResultSet rset01 = null;
			PreparedStatement stmt01 = null;
			try {
				stmt01 = A100_DbConnect.conn.prepareStatement(sql);
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
				
				rt = B100_RtObjectCreate.B100_RtObjectCreate(rset01,RtItemComversionMstRt());
				
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
			A100_DbConnect.close();
		}
		return rt;
	}
}