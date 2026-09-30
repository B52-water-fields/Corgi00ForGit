import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WT100_ShipPlovision{
	//在庫引き当て
	static final int StockItemLotExpDateSumRt_Col_ClCd				= (int) 0;		//荷主コード
	static final int StockItemLotExpDateSumRt_Col_CLName			= (int) 1;		//荷主表記名
	static final int StockItemLotExpDateSumRt_Col_WhCd				= (int) 2;		//倉庫コード
	static final int StockItemLotExpDateSumRt_Col_ClWHName		= (int) 3;		//担当倉庫名
	static final int StockItemLotExpDateSumRt_Col_ClGpCD			= (int) 4;		//荷主グループCD
	static final int StockItemLotExpDateSumRt_Col_ClGpName		= (int) 5;		//グループ名1
	static final int StockItemLotExpDateSumRt_Col_ItemCd			= (int) 6;		//商品コード
	static final int StockItemLotExpDateSumRt_Col_Lot				= (int) 7;		//ロット
	static final int StockItemLotExpDateSumRt_Col_Expdate			= (int) 8;		//消費期限
	static final int StockItemLotExpDateSumRt_Col_Qty				= (int) 9;		//数量
	static final int StockItemLotExpDateSumRt_Col_ShipPlanQty		= (int)10;		//引当済数
	static final int StockItemLotExpDateSumRt_Col_PossibleQty		= (int)11;		//出荷可能数
	static final int StockItemLotExpDateSumRt_Col_ItemName		= (int)12;		//商品名
	static final int StockItemLotExpDateSumRt_Col_ItemName01		= (int)13;		//商品表記名
	static final int StockItemLotExpDateSumRt_Col_ItemName02		= (int)14;		//商品正式名
	static final int StockItemLotExpDateSumRt_Col_ItemName03		= (int)15;		//商品略名
	static final int StockItemLotExpDateSumRt_Col_CtUnitQty		= (int)16;		//カートン入数
	static final int StockItemLotExpDateSumRt_Col_CsUnitQty		= (int)17;		//ケース入数
	static final int StockItemLotExpDateSumRt_Col_PlUnitQty		= (int)18;		//パレット入数
	static final int StockItemLotExpDateSumRt_Col_UnitName		= (int)19;		//商品単位
	static final int StockItemLotExpDateSumRt_Col_CtUnitName		= (int)20;		//カートン商品単位
	static final int StockItemLotExpDateSumRt_Col_CsUnitName		= (int)21;		//ケース商品単位
	static final int StockItemLotExpDateSumRt_Col_PlUnitName		= (int)22;		//パレット商品単位
	
	static final int RtOkuriItemLotExpDateSumRt_ColMsItemCd		= (int) 0;		//明細商品コード
	static final int RtOkuriItemLotExpDateSumRt_ColMsQty			= (int) 1;		//明細個数
	static final int RtOkuriItemLotExpDateSumRt_ColMsLot			= (int) 2;		//明細ロット指定
	static final int RtOkuriItemLotExpDateSumRt_ColMsExpDate		= (int) 3;		//明細賞味期限指定
	static final int RtOkuriItemLotExpDateSumRt_ColMsPackingType	= (int) 4;		//明細荷姿タイプ
	
	static final int ColErrType			= (int) 0;
	static final int ColOkuriNo			= (int) 1;
	static final int ColMsNo				= (int) 2;
	static final int ColItemCd				= (int) 3;
	static final int ColLot				= (int) 4;
	static final int ColExpDate			= (int) 5;
	static final int ColNeedQty			= (int) 6;
	static final int ColPackingType		= (int) 7;
	static final int ColErrMsg				= (int) 8;
	
	
	public static Object[][] RtShipPlovision(){
		Object[][] Rt = {
						 {"ErrType"		,ColErrType		,"String"	}
						,{"OkuriNo"		,ColOkuriNo		,"String"}
						,{"MsNo"		,ColMsNo			,"int"}
						,{"ItemCd"		,ColItemCd			,"String"}
						,{"Lot"			,ColLot			,"String"}
						,{"ExpDate"		,ColExpDate		,"Date"}
						,{"NeedQty"		,ColNeedQty		,"int"}
						,{"PackingType"	,ColPackingType	,"int"}
						,{"ErrMsg"		,ColErrMsg			,"String"}
						};
		return Rt;
	}
	
	
	public static Object[][] ShipPlovision(String TgtWhCd,String TgtClCd,ArrayList<String> TgtOkurino) {
		Object[][] ErrRt = new Object[0][0];
		if(null==TgtWhCd||"".equals(TgtWhCd)) {TgtWhCd=A00000_Main.ClWh;}
		if(null==TgtClCd||"".equals(TgtClCd)) {TgtClCd=A00000_Main.ClCd;}
		if(null!=TgtOkurino && 0<TgtOkurino.size()) {
			WmsStatusCheck(TgtWhCd,TgtClCd,TgtOkurino);
			
			
		}
		
		return ErrRt;
	}
	
	public static Object[][] WmsStatusCheck(String TgtWhCd,String TgtClCd,ArrayList<String> TgtOkurino) {
		ArrayList<String> ErrOkuriNo	= new ArrayList<String>();
		ArrayList<String> ErrMsg		= new ArrayList<String>();
		int ErrCount=0;
		
		Object[][] OkuriHdRt	= OkuriHdRt(TgtWhCd,TgtClCd,TgtOkurino);
		
		for(int i=0;i<TgtOkurino.size();i++) {
			boolean UnHitFg = true;
			for(int i01=0;i01<OkuriHdRt.length;i01++) {
				if(TgtOkurino.get(i).equals((String)OkuriHdRt[i01][T100_OkuriHdRt.ColOkuriNo])) {
					if(0!=(int)OkuriHdRt[i01][T100_OkuriHdRt.ColWmsStatus]) {
						ErrOkuriNo.add(TgtOkurino.get(i));
						ErrMsg.add("("+TgtOkurino.get(i)+")は未引当ステータスではないので引当対象外です");
						ErrCount=ErrCount+1;
					}
					UnHitFg = false;
					i01=OkuriHdRt.length+1;
				}
			}
			if(UnHitFg) {
				ErrOkuriNo.add(TgtOkurino.get(i));
				ErrMsg.add("("+TgtOkurino.get(i)+")は当該荷主の送り状Noに存在しません");
				ErrCount=ErrCount+1;
			}
		}
		
		Object[][] RtShipPlovision = RtShipPlovision();
		
		Object[][] Rt	= DefaultSett(ErrCount);
		
		
		
		
		return Rt;
	}
	
	private static Object[][] DefaultSett(int RowCount){
		Object[][] RtShipPlovision = RtShipPlovision();
		Object[][] Rt = new Object[RowCount][RtShipPlovision.length];
		
		for(int i01=0;i01<RowCount;i01++) {
			for(int i02=0;i02<RtShipPlovision.length;i02++) {
				
			}
		}
		
		return Rt;
	}
	
	
	private static Object[][] RtOkuriItemLotExpDateSumRt(){
		Object[][] Rt = {
				 {"MsItemCd"			,RtOkuriItemLotExpDateSumRt_ColMsItemCd			,"String"	,"明細商品コード"				,"Key"		,"Detail Item Code"						,"明细商品代码"			,"Mã hàng chi tiết"}
				,{"MsQty"				,RtOkuriItemLotExpDateSumRt_ColMsQty				,"int"		,"明細個数"						,""			,"Detail Quantity"						,"明细数量"				,"Số lượng chi tiết"}
				,{"MsLot"				,RtOkuriItemLotExpDateSumRt_ColMsLot				,"String"	,"明細ロット指定"				,"Key"		,"Detail Specified Lot"					,"明细指定批次"			,"Lô chỉ định chi tiết"}
				,{"MsExpDate"			,RtOkuriItemLotExpDateSumRt_ColMsExpDate			,"Date"		,"明細賞味期限指定"				,"Key"		,"Detail Specified Expiration Date"		,"明细指定保质期"			,"Hạn sử dụng chỉ định chi tiết"}
				,{"MsPackingType"		,RtOkuriItemLotExpDateSumRt_ColMsPackingType		,"int"		,"明細荷姿タイプ"				,"Key"		,"Detail Packing Type"					,"明细包装类型"				,"Loại đóng gói chi tiết"}
				};
		Rt = B100_LanguageControl.RtControl(Rt);
		return Rt;
	}
	
	private static Object[][] OkuriItemLotExpDateSumRt(String TgtWhCd,String TgtClCd,ArrayList<String> TgtOkurino){
		//未引当出荷指示データの商品・ロット賞味期限・荷姿タイプ別数量を取得する
		Object[][] Rt = new Object[0][RtOkuriItemLotExpDateSumRt().length];
		boolean SearchKick = false;
		
		String sql = "select "
				+"(KT0011_OKURI_MS.ItemCd) 				as MsItemCd,\n"				//明細商品コード
				+"(KT0011_OKURI_MS.Lot) 				as MsLot,\n"				//明細ロット指定
				+"(KT0011_OKURI_MS.ExpDate) 			as MsExpDate,\n"			//明細賞味期限指定
				+"(KT0011_OKURI_MS.PackingType) 		as MsPackingType,\n"		//明細荷姿タイプ
				+"sum(KT0011_OKURI_MS.Qty) 				as MsQty \n"				//明細個数
				+" from "+A00000_Main.MySqlDefaultSchemaNYANKO+".KT0010_OKURI_HD \n"
				+" left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KT0011_OKURI_MS \n"
				+" on(KT0010_OKURI_HD.cl_cd = KT0011_OKURI_MS.cl_cd"
				+" and KT0010_OKURI_HD.InvoiceWHCD = KT0011_OKURI_MS.InvoiceWHCD"
				+" and KT0010_OKURI_HD.OkuriNo = KT0011_OKURI_MS.OkuriNo"
				+")\n"
				+" where KT0010_OKURI_HD.cl_cd = "		+TgtClCd+"\n"
				+" and KT0010_OKURI_HD.InvoiceWHCD = "	+TgtWhCd+"\n"
				+" and KT0010_OKURI_HD.WmsStatus = 0 \n";
				
			if(null!=TgtOkurino && 0<TgtOkurino.size()) {
				SearchKick = true;
				sql = sql + " and (";
				for(int i=0;i<TgtOkurino.size();i++) {
					if(0<i) {sql = sql + " or ";}
					sql = sql + "KT0010_OKURI_HD.OkuriNo = ?";
				}
				sql = sql + ")\n";
			}
			
			sql = sql+" group by KT0011_OKURI_MS.ItemCd,KT0011_OKURI_MS.Lot,KT0011_OKURI_MS.ExpDate,KT0011_OKURI_MS.PackingType";
			sql = sql+" order by KT0011_OKURI_MS.ItemCd,KT0011_OKURI_MS.Lot,KT0011_OKURI_MS.ExpDate,KT0011_OKURI_MS.PackingType";
			if(SearchKick) {
				A100_DbConnect.DB_CONN("NYANKO");
				ResultSet rset01 = null;
				PreparedStatement stmt01 = null;
				try {
					stmt01 = A100_DbConnect.conn.prepareStatement(sql);
					int StmtCount = 0;
					if(null!=TgtOkurino && 0<TgtOkurino.size()) {
						for(int i=0;i<TgtOkurino.size();i++) {
							StmtCount = StmtCount+1;
							stmt01.setString(StmtCount, ""+TgtOkurino.get(i)+"");
						}
					}
					rset01 = stmt01.executeQuery();
					
					Rt = B100_RtObjectCreate.B100_RtObjectCreate(rset01,RtOkuriItemLotExpDateSumRt());
					
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
					
		return Rt;
	}
	
	
	
	private static PreparedStatement StockItemLotExpDateSumRt(String TgtWhCd,String TgtClCd) {
		//商品・賞味期限別在庫を検索するPreparedStatement返却
		Object[][] Rt = new Object[0][100];
		String sql = "select \n"
				+"(WW0015Stock.ClCd) as ClCd,\n"						//荷主コード
				+"max(KM0030_CLIENTMST.CLName01) as CLName,\n"			//荷主表記名
				+"(WW0015Stock.WhCd) as WhCd,\n"						//倉庫コード
				+"max(KM0010_WHMST.WHName) as ClWHName,\n"				//担当倉庫名
				+"max(KM0030_CLIENTMST.ClGpCD) as ClGpCD,\n"			//荷主グループCD
				+"max(KM0031_CLIENT_GROUP.ClGpName01) as ClGpName,\n"	//グループ名1
				+"(WW0015Stock.ItemCd) as ItemCd,\n"					//商品コード
				+"(WW0015Stock.Lot) as Lot,\n"							//ロット
				+"(WW0015Stock.Expdate) as Expdate,\n"					//消費期限
				+"sum(WW0015Stock.Qty) as Qty,\n"						//数量
				+"sum(WW0015Stock.ShipPlanQty) as ShipPlanQty,\n"		//引当済数
				+"sum(WW0015Stock.PossibleQty) as PossibleQty,\n"		//出荷可能数
				+"max(WW0015Stock.ItemName) as ItemName,\n"				//商品名
				+"max(KM0060_ITEMMST.ItemName01) as ItemName01,\n"		//商品表記名
				+"max(KM0060_ITEMMST.ItemName02) as ItemName02,\n"		//商品正式名
				+"max(KM0060_ITEMMST.ItemName03) as ItemName03,\n"		//商品略名
				+"max(KM0061_ITEMMSTSUB.CtQty) as CtUnitQty,\n"			//カートン入数
				+"max(KM0061_ITEMMSTSUB.CsQty) as CsUnitQty,\n"			//ケース入数
				+"max(KM0061_ITEMMSTSUB.PlQty) as PlUnitQty,\n"			//パレット入数
				+"max(KM0060_ITEMMST.UnitName) as UnitName,\n"			//商品単位
				+"max(KM0061_ITEMMSTSUB.CtUnitName) as CtUnitName,\n"	//カートン商品単位
				+"max(KM0061_ITEMMSTSUB.CsUnitName) as CsUnitName,\n"	//ケース商品単位
				+"max(KM0061_ITEMMSTSUB.PlUnitName) as PlUnitName,\n"	//パレット商品単位
				+ " from "+A00000_Main.MySqlDefaultSchemaWANKO+".WW0015Stock"
				+ " left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0030_CLIENTMST"
				+ " on("
				+ " WW0015Stock.WhCd = KM0030_CLIENTMST.WHCD"
				+ " and WW0015Stock.ClCd = KM0030_CLIENTMST.cl_cd"
				+ ")\n"
				+ " left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP"
				+ " on("
				+ " KM0030_CLIENTMST.ClGpCD = KM0031_CLIENT_GROUP.ClGpCD"
				+ ")\n"
				+ " left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST"
				+ " on("
				+ " WW0015Stock.WhCd = KM0010_WHMST.WHCD"
				+ ")\n"
				+ " left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0060_ITEMMST"
				+ " on("
				+ " KM0030_CLIENTMST.ClGpCD = KM0060_ITEMMST.ClGpCd"
				+ " and WW0015Stock.ItemCd = KM0060_ITEMMST.ItemCd"
				+ ")\n"
				+ " left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0061_ITEMMSTSUB \n"
				+ " on("
				+ " KM0060_ITEMMST.ClGpCd = KM0061_ITEMMSTSUB.ClGpCd"
				+ " and KM0060_ITEMMST.ItemCd = KM0061_ITEMMSTSUB.ItemCd"
				+ ")\n"
				+ " where WW0015Stock.WhCd = "	+TgtWhCd+"\n"
				+ " and WW0015Stock.ClCd = "	+TgtClCd+"\n"
				+ " and WW0015Stock.ItemCd = ? \n";
		
		sql = sql + " group by WW0015Stock.WhCd,WW0015Stock.ClCd,WW0015Stock.ItemCd,WW0015Stock.Expdate,WW0015Stock.Lot";
		
		A100_DbConnect.DB_CONN("WANKO");
		ResultSet rset01 = null;
		PreparedStatement stmt01 = null;
		try {
			stmt01 = A100_DbConnect.conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stmt01;
	}
	
	
	private static Object[][] OkuriHdRt(String TgtWhCd,String TgtClCd,ArrayList<String> TgtOkurino){
		ArrayList<String> SearchInvoiceWHCD			= new ArrayList<String>();			//倉庫CD
		ArrayList<String> SearchClGpCD				= new ArrayList<String>();			//荷主グループCD
		ArrayList<String> SearchClCd				= new ArrayList<String>();			//荷主CD
		ArrayList<String> SearchOkuriNo				= TgtOkurino;						//送り状番号
		ArrayList<String> SearchClDeliNo			= new ArrayList<String>();			//荷主管理番号
		ArrayList<String> SearchPickupWhCd			= new ArrayList<String>();			//集荷倉庫CD
		ArrayList<String> SearchPurposeFG			= new ArrayList<String>();			//目的フラグ
		ArrayList<String> SearchPlanDateStr			= new ArrayList<String>();			//出荷予定日開始
		ArrayList<String> SearchShipDateStr			= new ArrayList<String>();			//出荷実績日開始
		ArrayList<String> SearchSPPlanDateStr		= new ArrayList<String>();			//着日指定開始
		ArrayList<String> SearchSPDateStr			= new ArrayList<String>();			//着日実績開始
		
		ArrayList<String> SearchPlanDateEnd			= new ArrayList<String>();			//出荷予定日終了
		ArrayList<String> SearchShipDateEnd			= new ArrayList<String>();			//出荷実績日終了
		ArrayList<String> SearchSPPlanDateEnd		= new ArrayList<String>();			//着日指定終了
		ArrayList<String> SearchSPDateEnd			= new ArrayList<String>();			//着日実績終了
		
		ArrayList<Float> SearchTotalWeightMin		= new ArrayList<Float>();			//荷物重量(kg)最小
		ArrayList<Float> SearchTotalSizeMin			= new ArrayList<Float>();			//荷物サイズ最小
		ArrayList<Integer> SearchTotalQtyMin		= new ArrayList<Integer>();			//個口数最小
		
		ArrayList<Float> SearchTotalWeightMax		= new ArrayList<Float>();			//荷物重量(kg)最大
		ArrayList<Float> SearchTotalSizeMax			= new ArrayList<Float>();			//荷物サイズ最大
		ArrayList<Integer> SearchTotalQtyMax		= new ArrayList<Integer>();			//個口数最大
		
		ArrayList<String> SearchDeliveryTypeCd01	= new ArrayList<String>();			//運送タイプ01
		ArrayList<String> SearchDeliveryTypeCd02	= new ArrayList<String>();			//運送タイプ02
		ArrayList<String> SearchDeliveryTypeCd03	= new ArrayList<String>();			//運送タイプ03
		ArrayList<String> SearchDeliveryTypeCd04	= new ArrayList<String>();			//運送タイプ04
		ArrayList<String> SearchDeliveryTypeCd05	= new ArrayList<String>();			//運送タイプ05
		
		ArrayList<Integer> SearchCodFG				= new ArrayList<Integer>();			//代引区分
		ArrayList<Integer> SearchCodPayTotalMin		= new ArrayList<Integer>();			//代引収受金額合計最小
		ArrayList<Integer> SearchCodPayTotalMax		= new ArrayList<Integer>();			//代引収受金額合計最大
		
		ArrayList<Integer> SearchChildrenFG			= new ArrayList<Integer>();			//子伝票区分
		ArrayList<String> SearchParentOkuriNo		= new ArrayList<String>();			//親伝票番号
		
		ArrayList<String> SearchNiokuriCd			= new ArrayList<String>();			//荷送人CD
		ArrayList<String> SearchNiokuriDepartmentCd	= new ArrayList<String>();			//荷送人部署CD
		ArrayList<String> SearchNiokuriName			= new ArrayList<String>();			//荷送人名称
		ArrayList<String> SearchNiokuriPost			= new ArrayList<String>();			//荷送人郵便番号
		ArrayList<String> SearchNiokuriAdd			= new ArrayList<String>();			//荷送人住所
		ArrayList<String> SearchNioKuriTel			= new ArrayList<String>();			//荷送人Tel
		ArrayList<String> SearchNioKuriFax			= new ArrayList<String>();			//荷送人Fax
		ArrayList<String> SearchNioKuriMail			= new ArrayList<String>();			//荷送人Mail
		ArrayList<String> SearchNiokuriMunicCd		= new ArrayList<String>();			//荷送人市区町村CD
		
		ArrayList<String> SearchDeliCd				= new ArrayList<String>();			//届先CD
		ArrayList<String> SearchClDeliCd			= new ArrayList<String>();			//荷主届先CD
		ArrayList<String> SearchDeliDepartmentCd	= new ArrayList<String>();			//届先部署CD
		ArrayList<String> SearchDeliName			= new ArrayList<String>();			//届先名称
		ArrayList<String> SearchDeliPost			= new ArrayList<String>();			//届先郵便番号
		ArrayList<String> SearchDeliAdd				= new ArrayList<String>();			//届先住所
		ArrayList<String> SearchDeliTel				= new ArrayList<String>();			//届先Tel
		ArrayList<String> SearchDeliFax				= new ArrayList<String>();			//届先Fax
		ArrayList<String> SearchDeliMail			= new ArrayList<String>();			//届先Mail
		ArrayList<String> SearchDeliMunicCd			= new ArrayList<String>();			//届先市区町村CD
		
		ArrayList<String> SearchCom					= new ArrayList<String>();			//コメント
		ArrayList<Integer> SearchStatus				= new ArrayList<Integer>();			//運送ステータス
		
		ArrayList<Integer> SearchFeeFixFG			= new ArrayList<Integer>();			//運賃確定フラグ
		ArrayList<Integer> SearchReceiptStampFG		= new ArrayList<Integer>();			//受領印フラグ
		ArrayList<Integer> SearchInvoiceStatus		= new ArrayList<Integer>();			//請求ステータス
		
		ArrayList<Integer> SearchWithOutTaxTotalMin	= new ArrayList<Integer>();			//税別運賃合計最小
		ArrayList<Integer> SearchTotalFeeMin		= new ArrayList<Integer>();			//税込運賃合計税込運賃合計
		ArrayList<String> SearchFeeFixDateStr		= new ArrayList<String>();			//運賃確定日時開始
		ArrayList<String> SearchReceiptStampDateStr	= new ArrayList<String>();			//受領印日時開始
		ArrayList<String> SearchEntryDateStr		= new ArrayList<String>();			//登録日終了
		ArrayList<String> SearchUpdateDateStr		= new ArrayList<String>();			//更新日終了
		
		ArrayList<Integer> SearchWithOutTaxTotalMax	= new ArrayList<Integer>();			//税別運賃合計最大
		ArrayList<Integer> SearchTotalFeeMax		= new ArrayList<Integer>();			//税込運賃合計最大
		ArrayList<String> SearchFeeFixDateEnd		= new ArrayList<String>();			//運賃確定日時終了
		ArrayList<String> SearchReceiptStampDateEnd	= new ArrayList<String>();			//受領印日時終了
		ArrayList<String> SearchEntryDateEnd		= new ArrayList<String>();			//登録日終了
		ArrayList<String> SearchUpdateDateEnd		= new ArrayList<String>();			//更新日終了
		
		ArrayList<String> SearchEntryUser			= new ArrayList<String>();			//登録者
		ArrayList<String> SearchUpdateUser			= new ArrayList<String>();			//更新者
		ArrayList<String> SearchEntryPG				= new ArrayList<String>();			//登録プログラム
		ArrayList<String> SearchUpdatePG			= new ArrayList<String>();			//更新プログラム
		ArrayList<String> SearchUseFeeBasePtCd		= new ArrayList<String>();			//運転計算タリフ
		ArrayList<Integer> SearchWmsStatus			= new ArrayList<Integer>();			//倉庫出荷ステータス
		ArrayList<String> SearchWmsShipDateStr		= new ArrayList<String>();			//倉庫出荷日時開始
		ArrayList<String> SearchWmsShipDateEnd		= new ArrayList<String>();			//倉庫出荷日時終了
		ArrayList<String> SearchCourseGpCd			= new ArrayList<String>();			//配車コースグループコード
		ArrayList<String> SearchCourseCD			= new ArrayList<String>();			//配車コースコード
		ArrayList<Integer> SearchCourseCDEda		= new ArrayList<Integer>();			//配車コースコード枝番
		ArrayList<String> SearchPitGrp				= new ArrayList<String>();			//荷物払出ピットグループ
		ArrayList<String> SearchPit					= new ArrayList<String>();			//荷物払出ピット
		
		ArrayList<String> SearchMsItemCd			= new ArrayList<String>();			//商品CD
		ArrayList<String> SearchMsItemName			= new ArrayList<String>();			//商品名
		
		ArrayList<String> SearchClItemCd			= new ArrayList<String>();			//荷主商品CD
		
		ArrayList<String> SearchMsCategoryCd		= new ArrayList<String>();			//カテゴリCD
		ArrayList<String> SearchMsCategoryName		= new ArrayList<String>();			//カテゴリ名
		ArrayList<String> SearchMsTildFG			= new ArrayList<String>();			//温度区分
		ArrayList<String> SearchMsTildName			= new ArrayList<String>();			//温度区分名
		
		ArrayList<String> SearchMsLot				= new ArrayList<String>();			//ロット指定
		ArrayList<String> SearchMsExpDateStr		= new ArrayList<String>();			//賞味期限指定開始
		ArrayList<String> SearchMsExpDateEnd		= new ArrayList<String>();			//賞味期限指定終了
		ArrayList<Integer> SearchMsPackingType		= new ArrayList<Integer>();			//荷姿タイプ
		boolean AllSearch = false;
		
		//対象送り状番号無ければゼロ件返却してほしい
		if(null!=TgtOkurino && 0<TgtOkurino.size()) {
			SearchInvoiceWHCD.add(TgtWhCd);
			SearchClCd.add(TgtClCd);
		}
		Object[][] OkuriHdRt	= T100_OkuriHdRt.OkuriHdRt(
					SearchInvoiceWHCD,			//倉庫CD
					SearchClGpCD,				//荷主グループCD
					SearchClCd,					//荷主CD
					SearchOkuriNo,				//送り状番号
					SearchClDeliNo,				//荷主管理番号
					SearchPickupWhCd,			//集荷倉庫CD
					SearchPurposeFG,			//目的フラグ
					SearchPlanDateStr,			//出荷予定日開始
					SearchShipDateStr,			//出荷実績日開始
					SearchSPPlanDateStr,		//着日指定開始
					SearchSPDateStr,			//着日実績開始
					
					SearchPlanDateEnd,			//出荷予定日終了
					SearchShipDateEnd,			//出荷実績日終了
					SearchSPPlanDateEnd,		//着日指定終了
					SearchSPDateEnd,			//着日実績終了
					
					SearchTotalWeightMin,		//荷物重量(kg)最小
					SearchTotalSizeMin,			//荷物サイズ最小
					SearchTotalQtyMin,			//個口数最小
					
					SearchTotalWeightMax,		//荷物重量(kg)最大
					SearchTotalSizeMax,			//荷物サイズ最大
					SearchTotalQtyMax,			//個口数最大
					
					SearchDeliveryTypeCd01,		//運送タイプ01
					SearchDeliveryTypeCd02,		//運送タイプ02
					SearchDeliveryTypeCd03,		//運送タイプ03
					SearchDeliveryTypeCd04,		//運送タイプ04
					SearchDeliveryTypeCd05,		//運送タイプ05
					
					SearchCodFG,				//代引区分
					SearchCodPayTotalMin,		//代引収受金額合計最小
					SearchCodPayTotalMax,		//代引収受金額合計最大
					
					SearchChildrenFG,			//子伝票区分
					SearchParentOkuriNo,		//親伝票番号
					
					SearchNiokuriCd,			//荷送人CD
					SearchNiokuriDepartmentCd,	//荷送人部署CD
					SearchNiokuriName,			//荷送人名称
					SearchNiokuriPost,			//荷送人郵便番号
					SearchNiokuriAdd,			//荷送人住所
					SearchNioKuriTel,			//荷送人Tel
					SearchNioKuriFax,			//荷送人Fax
					SearchNioKuriMail,			//荷送人Mail
					SearchNiokuriMunicCd,		//荷送人市区町村CD
					
					SearchDeliCd,				//届先CD
					SearchClDeliCd,				//荷主届先CD
					SearchDeliDepartmentCd,		//届先部署CD
					SearchDeliName,				//届先名称
					SearchDeliPost,				//届先郵便番号
					SearchDeliAdd,				//届先住所
					SearchDeliTel,				//届先Tel
					SearchDeliFax,				//届先Fax
					SearchDeliMail,				//届先Mail
					SearchDeliMunicCd,			//届先市区町村CD
					
					SearchCom,					//コメント
					SearchStatus,				//運送ステータス
					
					SearchFeeFixFG,				//運賃確定フラグ
					SearchReceiptStampFG,		//受領印フラグ
					SearchInvoiceStatus,		//請求ステータス
					
					SearchWithOutTaxTotalMin,	//税別運賃合計最小
					SearchTotalFeeMin,			//税込運賃合計最小
					SearchFeeFixDateStr,		//運賃確定日時開始
					SearchReceiptStampDateStr,	//受領印日時開始
					SearchEntryDateStr,			//登録日開始
					SearchUpdateDateStr,		//更新日開始
					
					SearchWithOutTaxTotalMax,	//税別運賃合計最大
					SearchTotalFeeMax,			//税込運賃合計最大
					SearchFeeFixDateEnd,		//運賃確定日時終了
					SearchReceiptStampDateEnd,	//受領印日時終了
					SearchEntryDateEnd,			//登録日終了
					SearchUpdateDateEnd,		//更新日終了
					
					SearchEntryUser,			//登録者
					SearchUpdateUser,			//更新者
					SearchEntryPG,				//登録プログラム
					SearchUpdatePG,				//更新プログラム
					SearchUseFeeBasePtCd,		//運転計算タリフ
					SearchWmsStatus,			//倉庫出荷ステータス
					SearchWmsShipDateStr,		//倉庫出荷日時開始
					SearchWmsShipDateEnd,		//倉庫出荷日時終了
					SearchCourseGpCd,			//配車コースグループコード
					SearchCourseCD,				//配車コースコード
					SearchCourseCDEda,			//配車コースコード枝番
					SearchPitGrp,				//荷物払出ピットグループ
					SearchPit,					//荷物払出ピット
					
					SearchMsItemCd,				//商品CD
					SearchMsItemName,			//商品名
					
					SearchClItemCd,				//荷主商品CD
					
					SearchMsCategoryCd,			//カテゴリCD
					SearchMsCategoryName,		//カテゴリ名
					SearchMsTildFG,				//温度区分
					SearchMsTildName,			//温度区分名
					
					SearchMsLot,				//ロット指定
					SearchMsExpDateStr,			//賞味期限指定開始
					SearchMsExpDateEnd,			//賞味期限指定終了
					SearchMsPackingType,		//荷姿タイプ
					AllSearch);
		
		return OkuriHdRt;
	}
	
	
	
	
}