import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class M00070ItemMstRt{
	/*
	コピペ用
	ArrayList<String> SearchClGpCd = new ArrayList<String>();			//荷主グループコード
	ArrayList<String> SearchItemCd = new ArrayList<String>();			//商品コード
	ArrayList<String> SearchCLItemCd = new ArrayList<String>();			//荷主商品コード
	ArrayList<String> SearchItemName = new ArrayList<String>();			//商品名
	ArrayList<String> SearchDeliveryTypeCd01 = new ArrayList<String>();	//運送タイプコード01
	ArrayList<String> SearchDeliveryTypeCd02 = new ArrayList<String>();	//運送タイプコード02
	ArrayList<String> SearchDeliveryTypeCd03 = new ArrayList<String>();	//運送タイプコード03
	ArrayList<String> SearchDeliveryTypeCd04 = new ArrayList<String>();	//運送タイプコード04
	ArrayList<String> SearchDeliveryTypeCd05 = new ArrayList<String>();	//運送タイプコード05
	ArrayList<String> SearchItemMDNo = new ArrayList<String>();			//商品モデル番号（型番）
	ArrayList<String> SearchCategoryCd = new ArrayList<String>();		//商品カテゴリCD
	ArrayList<String> SearchCategoryName = new ArrayList<String>();		//商品カテゴリ名
	ArrayList<String> SearchItemColorCd = new ArrayList<String>();		//商品カラーコード
	ArrayList<String> SearchItemColorName = new ArrayList<String>();	//商品カラー名
	ArrayList<String> SearchItemSizeCd = new ArrayList<String>();		//商品サイズコード
	ArrayList<String> SearchItemSizeName = new ArrayList<String>();		//商品サイス名
	ArrayList<String> SearchJanCd = new ArrayList<String>();			//JANCD
	ArrayList<String> SearchTildFG = new ArrayList<String>();			//温度区分
	ArrayList<String> SearchTildName = new ArrayList<String>();			//温度区分名
	ArrayList<String> SearchDelFg = new ArrayList<String>();			//削除フラグ
	boolean AllSearch = false;
	
	Object[][] ItemMstRt = M00070ItemMstRt.ItemMstRt(
			SearchClGpCd,			//荷主グループコード
			SearchItemCd,			//商品コード
			SearchCLItemCd,			//荷主商品コード
			SearchItemName,			//商品名
			SearchDeliveryTypeCd01,	//運送タイプコード01
			SearchDeliveryTypeCd02,	//運送タイプコード02
			SearchDeliveryTypeCd03,	//運送タイプコード03
			SearchDeliveryTypeCd04,	//運送タイプコード04
			SearchDeliveryTypeCd05,	//運送タイプコード05
			SearchItemMDNo,			//商品モデル番号（型番）
			SearchCategoryCd,		//商品カテゴリCD
			SearchCategoryName,		//商品カテゴリ名
			SearchItemColorCd,		//商品カラーコード
			SearchItemColorName,	//商品カラー名
			SearchItemSizeCd,		//商品サイズコード
			SearchItemSizeName,		//商品サイス名
			SearchJanCd,			//JANCD
			SearchTildFG,			//温度区分
			SearchTildName,			//温度区分名
			SearchDelFg,			//削除フラグ
			AllSearch);
	
	*/
	//戻り値カラム
	public static Object[][] RtSettingItemMstRt(){
		Object[][] RtSettingItemMstRt = {
				 {"ClGpCd"					,(int) 0	,"String"	,"荷主グループコード"}
				,{"CLGpName01"				,(int) 1	,"String"	,"荷主グループ名1"}
				,{"ItemCd"					,(int) 2	,"String"	,"商品コード"}
				,{"CLItemCd"				,(int) 3	,"String"	,"荷主商品コード"}
				,{"ItemName01"				,(int) 4	,"String"	,"商品名1"}
				,{"ItemName02"				,(int) 5	,"String"	,"商品名2"}
				,{"ItemName03"				,(int) 6	,"String"	,"商品名3"}
				,{"DeliveryTypeCd01"		,(int) 7	,"String"	,"運送タイプコード01"}
				,{"DeliveryTypeName01"		,(int) 8	,"String"	,"運送タイプ名01"}
				,{"DeliveryTypeCd02"		,(int) 9	,"String"	,"運送タイプコード02"}
				,{"DeliveryTypeName02"		,(int)10	,"String"	,"運送タイプ名02"}
				,{"DeliveryTypeCd03"		,(int)11	,"String"	,"運送タイプコード03"}
				,{"DeliveryTypeName03"		,(int)12	,"String"	,"運送タイプ名03"}
				,{"DeliveryTypeCd04"		,(int)13	,"String"	,"運送タイプコード04"}
				,{"DeliveryTypeName04"		,(int)14	,"String"	,"運送タイプ名04"}
				,{"DeliveryTypeCd05"		,(int)15	,"String"	,"運送タイプコード05"}
				,{"DeliveryTypeName05"		,(int)16	,"String"	,"運送タイプ名05"}
				,{"PTMSCD"					,(int)17	,"String"	,"基幹システム商品コード"}
				,{"CtQty"					,(int)18	,"int"		,"カートン入数"}
				,{"CsQty"					,(int)19	,"int"		,"ケース入数"}
				,{"PlQty"					,(int)20	,"int"		,"パレット入数"}
				,{"JanCd"					,(int)21	,"String"	,"JANCD"}
				,{"CtJan"					,(int)22	,"String"	,"カートンバーコード"}
				,{"CsJan"					,(int)23	,"String"	,"ケースバーコード"}
				,{"PlJan"					,(int)24	,"String"	,"パレットバーコード"}
				,{"CtName"					,(int)25	,"String"	,"カートン商品名称"}
				,{"CsName"					,(int)26	,"String"	,"ケース商品名称"}
				,{"PlName"					,(int)27	,"String"	,"パレット商品名称"}
				,{"UnitName"				,(int)28	,"String"	,"商品単位"}
				,{"CtUnitName"				,(int)29	,"String"	,"カートン商品単位"}
				,{"CsUnitName"				,(int)30	,"String"	,"ケース商品単位"}
				,{"PlUnitName"				,(int)31	,"String"	,"パレット商品単位"}
				,{"ItemWeight"				,(int)32	,"flost"	,"商品重量"}
				,{"CtWeight"				,(int)33	,"flost"	,"カートン重量"}
				,{"CsWeight"				,(int)34	,"flost"	,"ケース重量"}
				,{"PlWeight"				,(int)35	,"flost"	,"パレット重量"}
				,{"ItemSize"				,(int)36	,"flost"	,"商品サイズ"}
				,{"CtSize"					,(int)37	,"flost"	,"カートンサイズ"}
				,{"CsSize"					,(int)38	,"flost"	,"ケースサイズ"}
				,{"PlSize"					,(int)39	,"flost"	,"パレットサイズ"}
				,{"RecomendLoc"				,(int)40	,"String"	,"推奨ロケ"}
				,{"ItemMDNo"				,(int)41	,"String"	,"商品モデル番号（型番）"}
				,{"CategoryCd"				,(int)42	,"String"	,"商品カテゴリCD"}
				,{"CategoryName"			,(int)43	,"String"	,"商品カテゴリ名"}
				,{"ItemColorCd"				,(int)44	,"String"	,"商品カラーコード"}
				,{"ItemColorName"			,(int)45	,"String"	,"商品カラー名"}
				,{"ItemSizeCd"				,(int)46	,"String"	,"商品サイズコード"}
				,{"ItemSizeName"			,(int)47	,"String"	,"商品サイス名"}
				,{"Com01"					,(int)48	,"String"	,"コメント1"}
				,{"Com02"					,(int)49	,"String"	,"コメント2"}
				,{"Com03"					,(int)50	,"String"	,"コメント3"}
				,{"TildFG"					,(int)51	,"String"	,"温度区分"}
				,{"TildName"				,(int)52	,"String"	,"温度区分名"}
				,{"PictPass01"				,(int)53	,"String"	,"画像パス01"}
				,{"PictPass02"				,(int)54	,"String"	,"画像パス02"}
				,{"PictPass03"				,(int)55	,"String"	,"画像パス03"}
				,{"PictPass04"				,(int)56	,"String"	,"画像パス04"}
				,{"PictPass05"				,(int)57	,"String"	,"画像パス05"}
				,{"ExpDateHowLong"			,(int)58	,"int"		,"賞味期限日数"}
				,{"EntryDate"				,(int)59	,"String"	,"データ登録日時"}
				,{"UpdateDate"				,(int)60	,"String"	,"データ更新日時"}
				,{"EntryUser"				,(int)61	,"String"	,"登録者コード"}
				,{"UpdateUser"				,(int)62	,"String"	,"更新者コード"}
				,{"DelFg"					,(int)63	,"String"	,"削除フラグ"}
				};
		
		return RtSettingItemMstRt;
	}
	public static Object[][] ItemMstRt(
			ArrayList<String> SearchClGpCd,				//荷主グループコード
			ArrayList<String> SearchItemCd,				//商品コード
			ArrayList<String> SearchCLItemCd,			//荷主商品コード
			ArrayList<String> SearchItemName,			//商品名
			ArrayList<String> SearchDeliveryTypeCd01,	//運送タイプコード01
			ArrayList<String> SearchDeliveryTypeCd02,	//運送タイプコード02
			ArrayList<String> SearchDeliveryTypeCd03,	//運送タイプコード03
			ArrayList<String> SearchDeliveryTypeCd04,	//運送タイプコード04
			ArrayList<String> SearchDeliveryTypeCd05,	//運送タイプコード05
			ArrayList<String> SearchItemMDNo,			//商品モデル番号（型番）
			ArrayList<String> SearchCategoryCd,			//商品カテゴリCD
			ArrayList<String> SearchCategoryName,		//商品カテゴリ名
			ArrayList<String> SearchItemColorCd,		//商品カラーコード
			ArrayList<String> SearchItemColorName,		//商品カラー名
			ArrayList<String> SearchItemSizeCd,			//商品サイズコード
			ArrayList<String> SearchItemSizeName,		//商品サイス名
			ArrayList<String> SearchJanCd,				//JANCD
			ArrayList<String> SearchTildFG,				//温度区分
			ArrayList<String> SearchTildName,			//温度区分名
			ArrayList<String> SearchDelFg,				//削除フラグ
			boolean AllSearch){
		
		SearchClGpCd			= B00150ArrayListControl.ArryListStringUniqueList(SearchClGpCd);
		SearchItemCd			= B00150ArrayListControl.ArryListStringUniqueList(SearchItemCd);
		SearchCLItemCd			= B00150ArrayListControl.ArryListStringUniqueList(SearchCLItemCd);
		SearchItemName			= B00150ArrayListControl.ArryListStringUniqueList(SearchItemName);
		SearchDeliveryTypeCd01	= B00150ArrayListControl.ArryListStringUniqueList(SearchDeliveryTypeCd01);
		SearchDeliveryTypeCd02	= B00150ArrayListControl.ArryListStringUniqueList(SearchDeliveryTypeCd02);
		SearchDeliveryTypeCd03	= B00150ArrayListControl.ArryListStringUniqueList(SearchDeliveryTypeCd03);
		SearchDeliveryTypeCd04	= B00150ArrayListControl.ArryListStringUniqueList(SearchDeliveryTypeCd04);
		SearchDeliveryTypeCd05	= B00150ArrayListControl.ArryListStringUniqueList(SearchDeliveryTypeCd05);
		SearchItemMDNo			= B00150ArrayListControl.ArryListStringUniqueList(SearchItemMDNo);
		SearchCategoryCd		= B00150ArrayListControl.ArryListStringUniqueList(SearchCategoryCd);
		SearchCategoryName		= B00150ArrayListControl.ArryListStringUniqueList(SearchCategoryName);
		SearchItemColorCd		= B00150ArrayListControl.ArryListStringUniqueList(SearchItemColorCd);
		SearchItemColorName		= B00150ArrayListControl.ArryListStringUniqueList(SearchItemColorName);
		SearchItemSizeCd		= B00150ArrayListControl.ArryListStringUniqueList(SearchItemSizeCd);
		SearchItemSizeName		= B00150ArrayListControl.ArryListStringUniqueList(SearchItemSizeName);
		SearchJanCd				= B00150ArrayListControl.ArryListStringUniqueList(SearchJanCd);
		SearchTildFG			= B00150ArrayListControl.ArryListStringUniqueList(SearchTildFG);
		SearchTildName			= B00150ArrayListControl.ArryListStringUniqueList(SearchTildName);
		SearchDelFg				= B00150ArrayListControl.ArryListStringUniqueList(SearchDelFg);
		
		Object[][] rt = new Object[0][0];
		boolean SearchKick = false;
		if(AllSearch) {SearchKick = true;}
		
		String sql = " select \n"
				+"(KM0060_ITEMMST.ClGpCd) as ClGpCd,\n"						//荷主グループコード
				+"(KM0031_CLIENT_GROUP.CLGpName01) as CLGpName01,\n"		//荷主グループ名1
				+"(KM0060_ITEMMST.ItemCd) as ItemCd,\n"						//商品コード
				+"(KM0060_ITEMMST.CLItemCd) as CLItemCd,\n"					//荷主商品コード
				+"(KM0060_ITEMMST.ItemName01) as ItemName01,\n"				//商品名1
				+"(KM0060_ITEMMST.ItemName02) as ItemName02,\n"				//商品名2
				+"(KM0060_ITEMMST.ItemName03) as ItemName03,\n"				//商品名3
				+"(KM0060_ITEMMST.DeliveryTypeCd) as DeliveryTypeCd01,\n"	//運送タイプコード01
				+"(DT01.DeliveryTypeName) as DeliveryTypeName01,\n"			//運送タイプ名01
				+"(KM0060_ITEMMST.DeliveryTypeCd02) as DeliveryTypeCd02,\n"	//運送タイプコード02
				+"(DT02.DeliveryTypeName) as DeliveryTypeName02,\n"			//運送タイプ名02
				+"(KM0060_ITEMMST.DeliveryTypeCd03) as DeliveryTypeCd03,\n"	//運送タイプコード03
				+"(DT03.DeliveryTypeName) as DeliveryTypeName03,\n"			//運送タイプ名03
				+"(KM0060_ITEMMST.DeliveryTypeCd04) as DeliveryTypeCd04,\n"	//運送タイプコード04
				+"(DT04.DeliveryTypeName) as DeliveryTypeName04,\n"			//運送タイプ名04
				+"(KM0060_ITEMMST.DeliveryTypeCd05) as DeliveryTypeCd05,\n"	//運送タイプコード05
				+"(DT05.DeliveryTypeName) as DeliveryTypeName05,\n"			//運送タイプ名05
				+"(KM0060_ITEMMST.PTMSCD) as PTMSCD,\n"						//基幹システム商品コード
				+"(KM0061_ITEMMSTSUB.CtQty) as CtQty,\n"					//カートン入数
				+"(KM0061_ITEMMSTSUB.CsQty) as CsQty,\n"					//ケース入数
				+"(KM0061_ITEMMSTSUB.PlQty) as PlQty,\n"					//パレット入数
				+"(KM0060_ITEMMST.JanCd) as JanCd,\n"						//JANCD
				+"(KM0061_ITEMMSTSUB.CtJan) as CtJan,\n"					//カートンバーコード
				+"(KM0061_ITEMMSTSUB.CsJan) as CsJan,\n"					//ケースバーコード
				+"(KM0061_ITEMMSTSUB.PlJan) as PlJan,\n"					//パレットバーコード
				+"(KM0061_ITEMMSTSUB.CtName) as CtName,\n"					//カートン商品名称
				+"(KM0061_ITEMMSTSUB.CsName) as CsName,\n"					//ケース商品名称
				+"(KM0061_ITEMMSTSUB.PlName) as PlName,\n"					//パレット商品名称
				+"(KM0060_ITEMMST.UnitName) as UnitName,\n"					//商品単位
				+"(KM0061_ITEMMSTSUB.CtUnitName) as CtUnitName,\n"			//カートン商品単位
				+"(KM0061_ITEMMSTSUB.CsUnitName) as CsUnitName,\n"			//ケース商品単位
				+"(KM0061_ITEMMSTSUB.PlUnitName) as PlUnitName,\n"			//パレット商品単位
				+"(KM0060_ITEMMST.ItemWeight) as ItemWeight,\n"				//商品重量
				+"(KM0061_ITEMMSTSUB.CtWeight) as CtWeight,\n"				//カートン重量
				+"(KM0061_ITEMMSTSUB.CsWeight) as CsWeight,\n"				//ケース重量
				+"(KM0061_ITEMMSTSUB.PlWeight) as PlWeight,\n"				//パレット重量
				+"(KM0060_ITEMMST.ItemSize) as ItemSize,\n"					//商品サイズ
				+"(KM0061_ITEMMSTSUB.CtSize) as CtSize,\n"					//カートンサイズ
				+"(KM0061_ITEMMSTSUB.CsSize) as CsSize,\n"					//ケースサイズ
				+"(KM0061_ITEMMSTSUB.PlSize) as PlSize,\n"					//パレットサイズ
				+"(KM0061_ITEMMSTSUB.RecomendLoc) as RecomendLoc,\n"		//推奨ロケ
				+"(KM0061_ITEMMSTSUB.ItemMDNo) as ItemMDNo,\n"				//商品モデル番号（型番）
				+"(KM0061_ITEMMSTSUB.CategoryCd) as CategoryCd,\n"			//商品カテゴリCD
				+"(KM0061_ITEMMSTSUB.CategoryName) as CategoryName,\n"		//商品カテゴリ名
				+"(KM0061_ITEMMSTSUB.ItemColorCd) as ItemColorCd,\n"		//商品カラーコード
				+"(KM0061_ITEMMSTSUB.ItemColorName) as ItemColorName,\n"	//商品カラー名
				+"(KM0061_ITEMMSTSUB.ItemSizeCd) as ItemSizeCd,\n"			//商品サイズコード
				+"(KM0061_ITEMMSTSUB.ItemSizeName) as ItemSizeName,\n"		//商品サイス名
				+"(KM0061_ITEMMSTSUB.Com01) as Com01,\n"					//コメント1
				+"(KM0061_ITEMMSTSUB.Com02) as Com02,\n"					//コメント2
				+"(KM0061_ITEMMSTSUB.Com03) as Com03,\n"					//コメント3
				+"(KM0061_ITEMMSTSUB.TildFG) as TildFG,\n"					//温度区分
				+"(KM0061_ITEMMSTSUB.TildName) as TildName,\n"				//温度区分名
				+"(KM0061_ITEMMSTSUB.PictPass01) as PictPass01,\n"			//画像パス01
				+"(KM0061_ITEMMSTSUB.PictPass02) as PictPass02,\n"			//画像パス02
				+"(KM0061_ITEMMSTSUB.PictPass03) as PictPass03,\n"			//画像パス03
				+"(KM0061_ITEMMSTSUB.PictPass04) as PictPass04,\n"			//画像パス04
				+"(KM0061_ITEMMSTSUB.PictPass05) as PictPass05,\n"			//画像パス05
				+"(KM0061_ITEMMSTSUB.ExpDateHowLong) as ExpDateHowLong,\n"	//賞味期限日数
				+"(KM0060_ITEMMST.EntryDate) as EntryDate,\n"				//データ登録日時
				+"(KM0060_ITEMMST.UpdateDate) as UpdateDate,\n"				//データ更新日時
				+"(KM0060_ITEMMST.EntryUser) as EntryUser,\n"				//登録者コード
				+"(KM0060_ITEMMST.UpdateUser) as UpdateUser,\n"				//更新者コード
				+"(KM0060_ITEMMST.DelFg) as DelFg\n"						//削除フラグ
				
				
				+ " from "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0060_ITEMMST \n"
				+ " left outer join "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0061_ITEMMSTSUB \n"
				+ " on("
				+ " KM0060_ITEMMST.ClGpCd = KM0061_ITEMMSTSUB.ClGpCd and KM0060_ITEMMST.ItemCd = KM0061_ITEMMSTSUB.ItemCd"
				+ ")\n"
				+ " left outer join "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP"
				+ " on("
				+ " KM0060_ITEMMST.ClGpCd = KM0031_CLIENT_GROUP.ClGpCD"
				+ ")\n"
				+ " left outer join "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0050_DELIVERY_TYPEMST as DT01"
				+ " on("
				+ "1 = DT01.DeliveryTypeNo and KM0060_ITEMMST.DeliveryTypeCd = DT01.DeliveryTypeCd"
				+ ")\n"
				+ " left outer join "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0050_DELIVERY_TYPEMST as DT02"
				+ " on("
				+ "2 = DT02.DeliveryTypeNo and KM0060_ITEMMST.DeliveryTypeCd02 = DT02.DeliveryTypeCd"
				+ ")\n"
				+ " left outer join "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0050_DELIVERY_TYPEMST as DT03"
				+ " on("
				+ "3 = DT03.DeliveryTypeNo and KM0060_ITEMMST.DeliveryTypeCd03 = DT03.DeliveryTypeCd"
				+ ")\n"
				+ " left outer join "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0050_DELIVERY_TYPEMST as DT04"
				+ " on("
				+ "4 = DT04.DeliveryTypeNo and KM0060_ITEMMST.DeliveryTypeCd04 = DT04.DeliveryTypeCd"
				+ ")\n"
				+ " left outer join "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0050_DELIVERY_TYPEMST as DT05"
				+ " on("
				+ "5 = DT05.DeliveryTypeNo and KM0060_ITEMMST.DeliveryTypeCd05 = DT05.DeliveryTypeCd"
				+ ")\n"
				+ "where 1=1\n"
				+ "";
		if(null!=SearchClGpCd&&0<SearchClGpCd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchClGpCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KM0060_ITEMMST.ClGpCd = ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchItemCd&&0<SearchItemCd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchItemCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KM0060_ITEMMST.ItemCd = ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchCLItemCd&&0<SearchCLItemCd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchCLItemCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KM0060_ITEMMST.CLItemCd = ?";
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
			}
			sql = sql + ")";
		}
		if(null!=SearchDeliveryTypeCd01&&0<SearchDeliveryTypeCd01.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchDeliveryTypeCd01.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KM0060_ITEMMST.DeliveryTypeCd = ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchDeliveryTypeCd02&&0<SearchDeliveryTypeCd02.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchDeliveryTypeCd02.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KM0060_ITEMMST.DeliveryTypeCd02 = ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchDeliveryTypeCd03&&0<SearchDeliveryTypeCd03.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchDeliveryTypeCd03.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KM0060_ITEMMST.DeliveryTypeCd03 = ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchDeliveryTypeCd04&&0<SearchDeliveryTypeCd04.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchDeliveryTypeCd04.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KM0060_ITEMMST.DeliveryTypeCd04 = ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchDeliveryTypeCd05&&0<SearchDeliveryTypeCd05.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchDeliveryTypeCd05.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KM0060_ITEMMST.DeliveryTypeCd05 = ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchJanCd&&0<SearchJanCd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchJanCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KM0060_ITEMMST.JanCd = ?";
				sql = sql + " or KM0061_ITEMMSTSUB.CtJan = ?";
				sql = sql + " or KM0061_ITEMMSTSUB.CsJan = ?";
				sql = sql + " or KM0061_ITEMMSTSUB.PlJan = ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchItemMDNo&&0<SearchItemMDNo.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchItemMDNo.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KM0061_ITEMMSTSUB.ItemMDNo = ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchCategoryCd&&0<SearchCategoryCd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchCategoryCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KM0061_ITEMMSTSUB.CategoryCd = ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchCategoryName&&0<SearchCategoryName.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchCategoryName.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KM0061_ITEMMSTSUB.CategoryName like ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchItemColorCd&&0<SearchItemColorCd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchItemColorCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + "KM0061_ITEMMSTSUB.ItemColorCd  = ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchItemColorName&&0<SearchItemColorName.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchItemColorName.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KM0061_ITEMMSTSUB.ItemColorName like ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchItemSizeCd&&0<SearchItemSizeCd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchItemSizeCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KM0061_ITEMMSTSUB.ItemSizeCd = ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchItemSizeName&&0<SearchItemSizeName.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchItemSizeName.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KM0061_ITEMMSTSUB.ItemSizeName like ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchTildFG&&0<SearchTildFG.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchTildFG.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KM0061_ITEMMSTSUB.TildFG = ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchTildName&&0<SearchTildName.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchTildName.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KM0061_ITEMMSTSUB.TildName like ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchDelFg&&0<SearchDelFg.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchDelFg.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KM0060_ITEMMST.DelFg = ?";
			}
			sql = sql + ")";
		}

		
		sql = sql+" order by KM0060_ITEMMST.ClGpCd,KM0060_ITEMMST.ItemCd\n";
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
					}
				}
				if(null!=SearchDeliveryTypeCd01&&0<SearchDeliveryTypeCd01.size()){
					for(int i=0;i<SearchDeliveryTypeCd01.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchDeliveryTypeCd01.get(i)+"");
					}
				}
				if(null!=SearchDeliveryTypeCd02&&0<SearchDeliveryTypeCd02.size()){
					for(int i=0;i<SearchDeliveryTypeCd02.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchDeliveryTypeCd02.get(i)+"");
					}
				}
				if(null!=SearchDeliveryTypeCd03&&0<SearchDeliveryTypeCd03.size()){
					for(int i=0;i<SearchDeliveryTypeCd03.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchDeliveryTypeCd03.get(i)+"");
					}
				}
				if(null!=SearchDeliveryTypeCd04&&0<SearchDeliveryTypeCd04.size()){
					for(int i=0;i<SearchDeliveryTypeCd04.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchDeliveryTypeCd04.get(i)+"");
					}
				}
				if(null!=SearchDeliveryTypeCd05&&0<SearchDeliveryTypeCd05.size()){
					for(int i=0;i<SearchDeliveryTypeCd05.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchDeliveryTypeCd05.get(i)+"");
					}
				}
				if(null!=SearchJanCd&&0<SearchJanCd.size()){
					for(int i=0;i<SearchJanCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchJanCd.get(i)+"");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchJanCd.get(i)+"");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchJanCd.get(i)+"");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchJanCd.get(i)+"");
					}
				}
				if(null!=SearchItemMDNo&&0<SearchItemMDNo.size()){
					for(int i=0;i<SearchItemMDNo.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchItemMDNo.get(i)+"");
					}
				}
				if(null!=SearchCategoryCd&&0<SearchCategoryCd.size()){
					for(int i=0;i<SearchCategoryCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchCategoryCd.get(i)+"");
					}
				}
				if(null!=SearchCategoryName&&0<SearchCategoryName.size()){
					for(int i=0;i<SearchCategoryName.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchCategoryName.get(i)+"%");
					}
				}
				if(null!=SearchItemColorCd&&0<SearchItemColorCd.size()){
					for(int i=0;i<SearchItemColorCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchItemColorCd.get(i)+"");
					}
				}
				if(null!=SearchItemColorName&&0<SearchItemColorName.size()){
					for(int i=0;i<SearchItemColorName.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchItemColorName.get(i)+"%");
					}
				}
				if(null!=SearchItemSizeCd&&0<SearchItemSizeCd.size()){
					for(int i=0;i<SearchItemSizeCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchItemSizeCd.get(i)+"");
					}
				}
				if(null!=SearchItemSizeName&&0<SearchItemSizeName.size()){
					for(int i=0;i<SearchItemSizeName.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchItemSizeName.get(i)+"%");
					}
				}
				if(null!=SearchTildFG&&0<SearchTildFG.size()){
					for(int i=0;i<SearchTildFG.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchTildFG.get(i)+"");
					}
				}
				if(null!=SearchTildName&&0<SearchTildName.size()){
					for(int i=0;i<SearchTildName.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchTildName.get(i)+"%");
					}
				}
				if(null!=SearchDelFg&&0<SearchDelFg.size()){
					for(int i=0;i<SearchDelFg.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchDelFg.get(i)+"");
					}
				}
				rset01 = stmt01.executeQuery();
				
				int counter = 0;
				rset01.beforeFirst();
				while (rset01.next()) {
					counter=counter+1;
				}
				rt = new Object[counter][64];
				counter = 0;
				rset01.beforeFirst();
				while (rset01.next()) {
					if(null==rset01.getString("ClGpCd")){				rt[counter][ 0]="";}else{rt[counter][ 0]=rset01.getString("ClGpCd");}				//荷主グループコード
					if(null==rset01.getString("CLGpName01")){			rt[counter][ 1]="";}else{rt[counter][ 1]=rset01.getString("CLGpName01");}			//荷主グループ名1
					if(null==rset01.getString("ItemCd")){				rt[counter][ 2]="";}else{rt[counter][ 2]=rset01.getString("ItemCd");}				//商品コード
					if(null==rset01.getString("CLItemCd")){				rt[counter][ 3]="";}else{rt[counter][ 3]=rset01.getString("CLItemCd");}				//荷主商品コード
					if(null==rset01.getString("ItemName01")){			rt[counter][ 4]="";}else{rt[counter][ 4]=rset01.getString("ItemName01");}			//商品名1
					if(null==rset01.getString("ItemName02")){			rt[counter][ 5]="";}else{rt[counter][ 5]=rset01.getString("ItemName02");}			//商品名2
					if(null==rset01.getString("ItemName03")){			rt[counter][ 6]="";}else{rt[counter][ 6]=rset01.getString("ItemName03");}			//商品名3
					if(null==rset01.getString("DeliveryTypeCd01")){		rt[counter][ 7]="";}else{rt[counter][ 7]=rset01.getString("DeliveryTypeCd01");}		//運送タイプコード01
					if(null==rset01.getString("DeliveryTypeName01")){	rt[counter][ 8]="";}else{rt[counter][ 8]=rset01.getString("DeliveryTypeName01");}	//運送タイプ名01
					if(null==rset01.getString("DeliveryTypeCd02")){		rt[counter][ 9]="";}else{rt[counter][ 9]=rset01.getString("DeliveryTypeCd02");}		//運送タイプコード02
					if(null==rset01.getString("DeliveryTypeName02")){	rt[counter][10]="";}else{rt[counter][10]=rset01.getString("DeliveryTypeName02");}	//運送タイプ名02
					if(null==rset01.getString("DeliveryTypeCd03")){		rt[counter][11]="";}else{rt[counter][11]=rset01.getString("DeliveryTypeCd03");}		//運送タイプコード03
					if(null==rset01.getString("DeliveryTypeName03")){	rt[counter][12]="";}else{rt[counter][12]=rset01.getString("DeliveryTypeName03");}	//運送タイプ名03
					if(null==rset01.getString("DeliveryTypeCd04")){		rt[counter][13]="";}else{rt[counter][13]=rset01.getString("DeliveryTypeCd04");}		//運送タイプコード04
					if(null==rset01.getString("DeliveryTypeName04")){	rt[counter][14]="";}else{rt[counter][14]=rset01.getString("DeliveryTypeName04");}	//運送タイプ名04
					if(null==rset01.getString("DeliveryTypeCd05")){		rt[counter][15]="";}else{rt[counter][15]=rset01.getString("DeliveryTypeCd05");}		//運送タイプコード05
					if(null==rset01.getString("DeliveryTypeName05")){	rt[counter][16]="";}else{rt[counter][16]=rset01.getString("DeliveryTypeName05");}	//運送タイプ名05
					if(null==rset01.getString("PTMSCD")){				rt[counter][17]="";}else{rt[counter][17]=rset01.getString("PTMSCD");}				//基幹システム商品コード
					rt[counter][18]=rset01.getInt("CtQty");			//カートン入数
					rt[counter][19]=rset01.getInt("CsQty");			//ケース入数
					rt[counter][20]=rset01.getInt("PlQty");			//パレット入数
					if(null==rset01.getString("JanCd")){				rt[counter][21]="";}else{rt[counter][21]=rset01.getString("JanCd");}				//JANCD
					if(null==rset01.getString("CtJan")){				rt[counter][22]="";}else{rt[counter][22]=rset01.getString("CtJan");}				//カートンバーコード
					if(null==rset01.getString("CsJan")){				rt[counter][23]="";}else{rt[counter][23]=rset01.getString("CsJan");}				//ケースバーコード
					if(null==rset01.getString("PlJan")){				rt[counter][24]="";}else{rt[counter][24]=rset01.getString("PlJan");}				//パレットバーコード
					if(null==rset01.getString("CtName")){				rt[counter][25]="";}else{rt[counter][25]=rset01.getString("CtName");}				//カートン商品名称
					if(null==rset01.getString("CsName")){				rt[counter][26]="";}else{rt[counter][26]=rset01.getString("CsName");}				//ケース商品名称
					if(null==rset01.getString("PlName")){				rt[counter][27]="";}else{rt[counter][27]=rset01.getString("PlName");}				//パレット商品名称
					if(null==rset01.getString("UnitName")){				rt[counter][28]="";}else{rt[counter][28]=rset01.getString("UnitName");}				//商品単位
					if(null==rset01.getString("CtUnitName")){			rt[counter][29]="";}else{rt[counter][29]=rset01.getString("CtUnitName");}			//カートン商品単位
					if(null==rset01.getString("CsUnitName")){			rt[counter][30]="";}else{rt[counter][30]=rset01.getString("CsUnitName");}			//ケース商品単位
					if(null==rset01.getString("PlUnitName")){			rt[counter][31]="";}else{rt[counter][31]=rset01.getString("PlUnitName");}			//パレット商品単位
					rt[counter][32]=rset01.getFloat("ItemWeight");	//商品重量
					rt[counter][33]=rset01.getFloat("CtWeight");	//カートン重量
					rt[counter][34]=rset01.getFloat("CsWeight");	//ケース重量
					rt[counter][35]=rset01.getFloat("PlWeight");	//パレット重量
					rt[counter][36]=rset01.getFloat("ItemSize");	//商品サイズ
					rt[counter][37]=rset01.getFloat("CtSize");		//カートンサイズ
					rt[counter][38]=rset01.getFloat("CsSize");		//ケースサイズ
					rt[counter][39]=rset01.getFloat("PlSize");		//パレットサイズ
					if(null==rset01.getString("RecomendLoc")){			rt[counter][40]="";}else{rt[counter][40]=rset01.getString("RecomendLoc");}			//推奨ロケ
					if(null==rset01.getString("ItemMDNo")){				rt[counter][41]="";}else{rt[counter][41]=rset01.getString("ItemMDNo");}				//商品モデル番号（型番）
					if(null==rset01.getString("CategoryCd")){			rt[counter][42]="";}else{rt[counter][42]=rset01.getString("CategoryCd");}			//商品カテゴリCD
					if(null==rset01.getString("CategoryName")){			rt[counter][43]="";}else{rt[counter][43]=rset01.getString("CategoryName");}			//商品カテゴリ名
					if(null==rset01.getString("ItemColorCd")){			rt[counter][44]="";}else{rt[counter][44]=rset01.getString("ItemColorCd");}			//商品カラーコード
					if(null==rset01.getString("ItemColorName")){		rt[counter][45]="";}else{rt[counter][45]=rset01.getString("ItemColorName");}		//商品カラー名
					if(null==rset01.getString("ItemSizeCd")){			rt[counter][46]="";}else{rt[counter][46]=rset01.getString("ItemSizeCd");}			//商品サイズコード
					if(null==rset01.getString("ItemSizeName")){			rt[counter][47]="";}else{rt[counter][47]=rset01.getString("ItemSizeName");}			//商品サイス名
					if(null==rset01.getString("Com01")){				rt[counter][48]="";}else{rt[counter][48]=rset01.getString("Com01");}				//コメント1
					if(null==rset01.getString("Com02")){				rt[counter][49]="";}else{rt[counter][49]=rset01.getString("Com02");}				//コメント2
					if(null==rset01.getString("Com03")){				rt[counter][50]="";}else{rt[counter][50]=rset01.getString("Com03");}				//コメント3
					if(null==rset01.getString("TildFG")){				rt[counter][51]="";}else{rt[counter][51]=rset01.getString("TildFG");}				//温度区分
					if(null==rset01.getString("TildName")){				rt[counter][52]="";}else{rt[counter][52]=rset01.getString("TildName");}				//温度区分名
					if(null==rset01.getString("PictPass01")){			rt[counter][53]="";}else{rt[counter][53]=rset01.getString("PictPass01");}			//画像パス01
					if(null==rset01.getString("PictPass02")){			rt[counter][54]="";}else{rt[counter][54]=rset01.getString("PictPass02");}			//画像パス02
					if(null==rset01.getString("PictPass03")){			rt[counter][55]="";}else{rt[counter][55]=rset01.getString("PictPass03");}			//画像パス03
					if(null==rset01.getString("PictPass04")){			rt[counter][56]="";}else{rt[counter][56]=rset01.getString("PictPass04");}			//画像パス04
					if(null==rset01.getString("PictPass05")){			rt[counter][57]="";}else{rt[counter][57]=rset01.getString("PictPass05");}			//画像パス05
					rt[counter][58]=rset01.getInt("ExpDateHowLong");//賞味期限日数
					if(null==rset01.getTimestamp("EntryDate")){			rt[counter][59]="";}else{rt[counter][59]=B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("EntryDate"))[1];}			//データ登録日時
					if(null==rset01.getTimestamp("UpdateDate")){		rt[counter][60]="";}else{rt[counter][60]=B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("UpdateDate"))[1];}			//データ更新日時
					if(null==rset01.getString("EntryUser")){			rt[counter][61]="";}else{rt[counter][61]=rset01.getString("EntryUser");}			//登録者コード
					if(null==rset01.getString("UpdateUser")){			rt[counter][62]="";}else{rt[counter][62]=rset01.getString("UpdateUser");}			//更新者コード
					if(null==rset01.getString("DelFg")){				rt[counter][63]="";}else{rt[counter][63]=rset01.getString("DelFg");}				//削除フラグ
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