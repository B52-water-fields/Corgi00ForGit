import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class T100_ArrivalHdRt{
	static final int ColClWh			=  0;		//担当倉庫
	static final int ColWHName			=  1;		//担当倉庫名
	static final int ColClCd			=  2;		//荷主CD
	static final int ColCLName01		=  3;		//ヘッダ荷主名
	static final int ColClGpCD			=  4;		//ヘッダ荷主グループCD
	static final int ColCLGpName01	=  5;		//ヘッダ荷主グループ標記名
	static final int ColArrNo			=  6;		//入荷予定NO
	static final int ColArrCount		=  7;		//入荷予定枝番
	static final int ColClArrNo		=  8;		//荷主予定番号
	static final int ColPlanDate		=  9;		//入荷予定日
	static final int ColActualDate	= 10;		//入荷実績日
	static final int ColSpCd			= 11;		//仕入先CD
	static final int ColSpName01		= 12;		//仕入先名01
	static final int ColSpName02		= 13;		//仕入先名02
	static final int ColSpName03		= 14;		//仕入先名03
	static final int ColSpPost			= 15;		//仕入先郵便
	static final int ColSpAdd01		= 16;		//仕入先住所01
	static final int ColSpAdd02		= 17;		//仕入先住所02
	static final int ColSpAdd03		= 18;		//仕入先住所03
	static final int ColSpTel			= 19;		//仕入先電話"}
	static final int ColArCom01		= 20;		//コメント1
	static final int ColArCom02		= 21;		//コメント2
	static final int ColArCom03		= 22;		//コメント3
	static final int ColEntryDate		= 23;		//登録日
	static final int ColUpdateDate	= 24;		//更新日
	static final int ColEntryUser		= 25;		//登録者
	static final int ColUpdateUser	= 26;		//更新者
	
	//明細WW0013ArrivalMs由来
	static final int ColPlanTotalQty		= 27;		//予定数量
	static final int ColActualTotalQty	= 28;		//実績数
	
	public static Object[][] RtArrivalHdRt(){
		Object[][] Rt = {
					 {"ClWh"			,ColClWh			,"String"	,"担当倉庫"				,"Key"}
					,{"WHName"			,ColWHName			,"String"	,"担当倉庫名"			,""}
					,{"ClCd"			,ColClCd			,"String"	,"荷主CD"				,"Key"}
					,{"CLName01"		,ColCLName01		,"String"	,"荷主名"				,""}
					,{"ClGpCD"			,ColClGpCD			,"String"	,"荷主グループCD"		,""}
					,{"CLGpName01"		,ColCLGpName01	,"String"	,"荷主グループ標記名"	,""}
					,{"ArrNo"			,ColArrNo			,"String"	,"入荷予定NO"			,"Key"}
					,{"ArrCount"		,ColArrCount		,"int"		,"入荷予定枝番"			,"Key"}
					,{"ClArrNo"			,ColClArrNo		,"String"	,"荷主予定番号"			,""}
					,{"PlanDate"		,ColPlanDate		,"Date"		,"入荷予定日"			,""}
					,{"ActualDate"		,ColActualDate	,"Date"		,"入荷実績日"			,""}
					,{"SpCd"			,ColSpCd			,"String"	,"仕入先CD"				,""}
					,{"SpName01"		,ColSpName01		,"String"	,"仕入先名01"			,""}
					,{"SpName02"		,ColSpName02		,"String"	,"仕入先名02"			,""}
					,{"SpName03"		,ColSpName03		,"String"	,"仕入先名03"			,""}
					,{"SpPost"			,ColSpPost			,"String"	,"仕入先郵便"			,""}
					,{"SpAdd01"			,ColSpAdd01		,"String"	,"仕入先住所01"			,""}
					,{"SpAdd02"			,ColSpAdd02		,"String"	,"仕入先住所02"			,""}
					,{"SpAdd03"			,ColSpAdd03		,"String"	,"仕入先住所03"			,""}
					,{"SpTel"			,ColSpTel			,"String"	,"仕入先電話"			,""}
					,{"ArCom01"			,ColArCom01		,"String"	,"コメント1"			,""}
					,{"ArCom02"			,ColArCom02		,"String"	,"コメント2"			,""}
					,{"ArCom03"			,ColArCom03		,"String"	,"コメント3"			,""}
					,{"EntryDate"		,ColEntryDate		,"DateTime"	,"登録日"				,""}
					,{"UpdateDate"		,ColUpdateDate	,"DateTime"	,"更新日"				,""}
					,{"EntryUser"		,ColEntryUser		,"String"	,"登録者"				,""}
					,{"UpdateUser"		,ColUpdateUser	,"String"	,"更新者"				,""}
					
					//明細WW0013ArrivalMs由来
					,{"PlanTotalQty"	,ColPlanTotalQty		,"int"		,"予定数量合計"		,""}
					,{"ActualTotalQty"	,ColActualTotalQty	,"int"		,"実績数合計"		,""}
					};
		return Rt;
	}
	
	public static Object[][] ArrivalHdRt(
			ArrayList<String> SearchClWh,			//担当倉庫
			ArrayList<String> SearchClCd,			//荷主CD
			ArrayList<String> SearchClGpCD,			//ヘッダ荷主グループCD
			ArrayList<String> SearchArrNo,			//入荷予定NO
			ArrayList<Integer> SearchArrCountMin,	//入荷予定枝番最小
			ArrayList<Integer> SearchArrCountMax,	//入荷予定枝番最大
			ArrayList<String> SearchClArrNo,		//荷主予定番号
			ArrayList<String> SearchPlanDateMin,	//入荷予定日最小
			ArrayList<String> SearchPlanDateMax,	//入荷予定日最大
			ArrayList<String> SearchActualDateMin,	//入荷実績日最小
			ArrayList<String> SearchActualDateMax,	//入荷実績日最大
			ArrayList<String> SearchSpCd,			//仕入先CD
			ArrayList<String> SearchCom,			//コメント
			ArrayList<String> SearchEntryDateMin,	//登録日最小
			ArrayList<String> SearchEntryDateMax,	//登録日最大
			ArrayList<String> SearchUpdateDateMin,	//更新日最小
			ArrayList<String> SearchUpdateDateMax,	//更新日最大
			ArrayList<String> SearchEntryUser,		//登録者
			ArrayList<String> SearchUpdateUser,		//更新者
			
			//明細WW0013ArrivalMs由来
			ArrayList<String> SearchItemCd,			//商品コード
			ArrayList<String> SearchClItemCd,		//荷主商品コード
			ArrayList<String> SearchItemName,		//商品名
			ArrayList<String> SearchLot,			//ロット
			ArrayList<String> SearchExpDateMin,		//消費期限最小
			ArrayList<String> SearchExpDateMax,		//消費期限最大
			boolean AllSearch){
		
		//日付系最小は念のため00:00:00扱い　※時刻まで検索条件にする場合はそのまま
		if(null!=SearchPlanDateMin && 0<SearchPlanDateMin.size()){
			for(int i=0;i<SearchPlanDateMin.size();i++){
				String SetString = B100_DateTimeControl.DateFormat(SearchPlanDateMin.get(i));
				Timestamp SetTimestamp = B100_DateTimeControl.dtmTimestamp2(SetString)[0];
				SetString = B100_DateTimeControl.dtmString2(SetTimestamp)[0];
				SearchPlanDateMin.set(i,SetString);
			}
		}
		if(null!=SearchActualDateMin && 0<SearchActualDateMin.size()){
			for(int i=0;i<SearchActualDateMin.size();i++){
				String SetString = B100_DateTimeControl.DateFormat(SearchActualDateMin.get(i));
				Timestamp SetTimestamp = B100_DateTimeControl.dtmTimestamp2(SetString)[0];
				SetString = B100_DateTimeControl.dtmString2(SetTimestamp)[0];
				SearchActualDateMin.set(i,SetString);
			}
		}
		if(null!=SearchExpDateMin && 0<SearchExpDateMin.size()){
			for(int i=0;i<SearchExpDateMin.size();i++){
				String SetString = B100_DateTimeControl.DateFormat(SearchExpDateMin.get(i));
				Timestamp SetTimestamp = B100_DateTimeControl.dtmTimestamp2(SetString)[0];
				SetString = B100_DateTimeControl.dtmString2(SetTimestamp)[0];
				SearchExpDateMin.set(i,SetString);
			}
		}
		
		//日付系項目最大は一日進めて00:00:00扱い　※時刻まで検索条件にする場合はそのまま
		if(null!=SearchPlanDateMax && 0<SearchPlanDateMax.size()){
			for(int i=0;i<SearchPlanDateMax.size();i++){
				String SetString = B100_DateTimeControl.DateFormat(SearchPlanDateMax.get(i));
				Timestamp SetTimestamp = B100_DateTimeControl.dtmTimestamp2(SetString)[0];
				SetTimestamp = B100_DateTimeControl.ndate_after(SetTimestamp,1);
				SetString = B100_DateTimeControl.dtmString2(SetTimestamp)[0];
				SearchPlanDateMax.set(i,SetString);
			}
		}
		if(null!=SearchActualDateMax && 0<SearchActualDateMax.size()){
			for(int i=0;i<SearchActualDateMax.size();i++){
				String SetString = B100_DateTimeControl.DateFormat(SearchActualDateMax.get(i));
				Timestamp SetTimestamp = B100_DateTimeControl.dtmTimestamp2(SetString)[0];
				SetTimestamp = B100_DateTimeControl.ndate_after(SetTimestamp,1);
				SetString = B100_DateTimeControl.dtmString2(SetTimestamp)[0];
				SearchActualDateMax.set(i,SetString);
			}
		}
		if(null!=SearchExpDateMax && 0<SearchExpDateMax.size()){
			for(int i=0;i<SearchExpDateMax.size();i++){
				String SetString = B100_DateTimeControl.DateFormat(SearchExpDateMax.get(i));
				Timestamp SetTimestamp = B100_DateTimeControl.dtmTimestamp2(SetString)[0];
				SetTimestamp = B100_DateTimeControl.ndate_after(SetTimestamp,1);
				SetString = B100_DateTimeControl.dtmString2(SetTimestamp)[0];
				SearchExpDateMax.set(i,SetString);
			}
		}
		//ヘッダWW0012ArrivalHd由来
		SearchClWh			= B100_ArrayListControl.ArryListStringUniqueList(SearchClWh);			//担当倉庫
		SearchClCd			= B100_ArrayListControl.ArryListStringUniqueList(SearchClCd);			//荷主CD
		SearchClGpCD		= B100_ArrayListControl.ArryListStringUniqueList(SearchClGpCD);			//ヘッダ荷主グループCD
		SearchArrNo			= B100_ArrayListControl.ArryListStringUniqueList(SearchArrNo);			//入荷予定NO
		SearchArrCountMin	= B100_ArrayListControl.ArryListIntegerUniqueList(SearchArrCountMin);	//入荷予定枝番最小
		SearchArrCountMax	= B100_ArrayListControl.ArryListIntegerUniqueList(SearchArrCountMax);	//入荷予定枝番最大
		SearchClArrNo		= B100_ArrayListControl.ArryListStringUniqueList(SearchClArrNo);		//荷主予定番号
		SearchPlanDateMin	= B100_ArrayListControl.ArryListStringUniqueList(SearchPlanDateMin);	//入荷予定日最小
		SearchPlanDateMax	= B100_ArrayListControl.ArryListStringUniqueList(SearchPlanDateMax);	//入荷予定日最大
		SearchActualDateMin	= B100_ArrayListControl.ArryListStringUniqueList(SearchActualDateMin);	//入荷実績日最小
		SearchActualDateMax	= B100_ArrayListControl.ArryListStringUniqueList(SearchActualDateMax);	//入荷実績日最大
		SearchSpCd			= B100_ArrayListControl.ArryListStringUniqueList(SearchSpCd);			//仕入先CD
		SearchCom			= B100_ArrayListControl.ArryListStringUniqueList(SearchCom);			//コメント
		SearchEntryDateMin	= B100_ArrayListControl.ArryListStringUniqueList(SearchEntryDateMin);	//登録日最小
		SearchEntryDateMax	= B100_ArrayListControl.ArryListStringUniqueList(SearchEntryDateMax);	//登録日最大
		SearchUpdateDateMin	= B100_ArrayListControl.ArryListStringUniqueList(SearchUpdateDateMin);	//更新日最小
		SearchUpdateDateMax	= B100_ArrayListControl.ArryListStringUniqueList(SearchUpdateDateMax);	//更新日最大
		SearchEntryUser		= B100_ArrayListControl.ArryListStringUniqueList(SearchEntryUser);		//登録者
		SearchUpdateUser	= B100_ArrayListControl.ArryListStringUniqueList(SearchUpdateUser);		//更新者
		
		//明細WW0013ArrivalMs由来
		SearchItemCd		= B100_ArrayListControl.ArryListStringUniqueList(SearchItemCd);			//商品コード
		SearchClItemCd		= B100_ArrayListControl.ArryListStringUniqueList(SearchClItemCd);		//荷主商品コード
		SearchItemName		= B100_ArrayListControl.ArryListStringUniqueList(SearchItemName);		//商品名
		SearchLot			= B100_ArrayListControl.ArryListStringUniqueList(SearchLot);			//ロット
		SearchExpDateMin	= B100_ArrayListControl.ArryListStringUniqueList(SearchExpDateMin);	//消費期限最小
		SearchExpDateMax	= B100_ArrayListControl.ArryListStringUniqueList(SearchExpDateMax);	//消費期限最大
		
		//商品変換マスタを元に荷主商品コードを商品コードに変換する
		Object[][] SearchItemCdFromClItem	= SearchItemCdFromClItem(SearchClGpCD,SearchClCd,SearchClItemCd);
		
		Object[][] Rt = new Object[0][RtArrivalHdRt().length];
		boolean SearchKick = false;
		if(AllSearch) {SearchKick = true;}
		
		String sql = "select \n"
				//ヘッダWW0012ArrivalHd由来
				+"(WW0012ArrivalHd.ClWh) 				as	ClWh,\n"			//担当倉庫
				+"max(KM0010_WHMST.WHName)				as	WHName,\n"			//担当倉庫名
				+"(WW0012ArrivalHd.ClCd) 				as	ClCd,\n"			//荷主CD
				+"max(KM0030_CLIENTMST.CLName01)       	as	CLName01,\n"		//ヘッダ荷主名
				+"(KM0030_CLIENTMST.ClGpCD)         	as	ClGpCD,\n"			//ヘッダ荷主グループCD
				+"max(KM0031_CLIENT_GROUP.CLGpName01)  	as	CLGpName01,\n"		//ヘッダ荷主グループ標記名
				+"(WW0012ArrivalHd.ArrNo)				as	ArrNo,\n"			//入荷予定NO
				+"(WW0012ArrivalHd.ArrCount) 			as	ArrCount,\n"		//入荷予定枝番
				+"max(WW0012ArrivalHd.ClArrNo) 			as	ClArrNo,\n"			//荷主予定番号
				+"max(WW0012ArrivalHd.PlanDate) 		as	PlanDate,\n"		//入荷予定日
				+"max(WW0012ArrivalHd.ActualDate)		as	ActualDate,\n"		//入荷実績日
				+"(WW0012ArrivalHd.SpCd)				as	SpCd,\n"			//仕入先CD
				+"max(WW0012ArrivalHd.SpName01)			as	SpName01,\n"		//仕入先名01
				+"max(WW0012ArrivalHd.SpName02)			as	SpName02,\n"		//仕入先名02
				+"max(WW0012ArrivalHd.SpName03)			as	SpName03,\n"		//仕入先名03
				+"max(WW0012ArrivalHd.SpPost)			as	SpPost,\n"			//仕入先郵便
				+"max(WW0012ArrivalHd.SpAdd01)			as	SpAdd01,\n"			//仕入先住所01
				+"max(WW0012ArrivalHd.SpAdd02)			as	SpAdd02,\n"			//仕入先住所02
				+"max(WW0012ArrivalHd.SpAdd03)			as	SpAdd03,\n"			//仕入先住所03
				+"max(WW0012ArrivalHd.SpTel)			as	SpTel,\n"			//仕入先電話"}
				+"max(WW0012ArrivalHd.ArCom01)			as	ArCom01,\n"			//コメント1
				+"max(WW0012ArrivalHd.ArCom02)			as	ArCom02,\n"			//コメント2
				+"max(WW0012ArrivalHd.ArCom03)			as	ArCom03,\n"			//コメント3
				+"max(WW0012ArrivalHd.EntryDate)		as	EntryDate,\n"		//登録日
				+"max(WW0012ArrivalHd.UpdateDate)		as	UpdateDate,\n"		//更新日
				+"max(WW0012ArrivalHd.EntryUser)		as	EntryUser,\n"		//登録者
				+"max(WW0012ArrivalHd.UpdateUser)		as	UpdateUser,\n"		//更新者
				//明細WW0013ArrivalMs由来
				+"sum(WW0013ArrivalMs.PlanQty)		as	PlanTotalQty,\n"	//予定数量
				+"sum(WW0013ArrivalMs.ActualQty)		as	ActualTotalQty \n"	//実績数
				
				+ " from "+A00000_Main.MySqlDefaultSchemaWANKO+".WW0012ArrivalHd \n"
				
				+ " left outer join "+A00000_Main.MySqlDefaultSchemaWANKO+".WW0013ArrivalMs \n"
				+ " on( WW0012ArrivalHd.ClWh = WW0013ArrivalMs.ClWh"
				+ " and WW0012ArrivalHd.ClCd = WW0013ArrivalMs.ClCd"
				+ " and WW0012ArrivalHd.ArrNo = WW0013ArrivalMs.ArrNo"
				+ " and WW0012ArrivalHd.ArrCount = WW0013ArrivalMs.ArrCount"
				+ ")\n"
				
				+ " left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST"
				+ " on("
				+ " WW0012ArrivalHd.ClWh = KM0010_WHMST.WHCD"
				+ ")\n"
				
				+ " left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0030_CLIENTMST"
				+ " on("
				+ " WW0012ArrivalHd.ClCd = KM0030_CLIENTMST.cl_cd"
				+ ")\n"
				
				+ " left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP"
				+ " on("
				+ " KM0030_CLIENTMST.ClGpCd = KM0031_CLIENT_GROUP.ClGpCD"
				+ ")\n"
				
				+ " left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0060_ITEMMST"
				+ " on("
				+ " KM0030_CLIENTMST.ClGpCD = KM0060_ITEMMST.ClGpCd"
				+ " and WW0013ArrivalMs.ItemCd = KM0060_ITEMMST.ItemCd"
				+ ")\n"
				+ " left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0061_ITEMMSTSUB \n"
				+ " on("
				+ " KM0060_ITEMMST.ClGpCd = KM0061_ITEMMSTSUB.ClGpCd"
				+ " and KM0060_ITEMMST.ItemCd = KM0061_ITEMMSTSUB.ItemCd"
				+ ")\n"
				+" where 1=1 \n";
		
		if(null!=SearchClWh && 0<SearchClWh.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchClWh.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0012ArrivalHd.ClWh = ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchClCd && 0<SearchClCd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchClCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0012ArrivalHd.ClCd = ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchClGpCD && 0<SearchClGpCD.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchClGpCD.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KM0030_CLIENTMST.ClGpCD = ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchArrNo && 0<SearchArrNo.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchArrNo.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0012ArrivalHd.ArrNo = ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchArrCountMin && 0<SearchArrCountMin.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchArrCountMin.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0012ArrivalHd.ArrCount >= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchArrCountMin && 0<SearchArrCountMax.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchArrCountMin.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0012ArrivalHd.ArrCount <= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchClArrNo && 0<SearchClArrNo.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchClArrNo.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0012ArrivalHd.ClArrNo = ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchPlanDateMin && 0<SearchPlanDateMin.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchPlanDateMin.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0012ArrivalHd.PlanDate >= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchPlanDateMax && 0<SearchPlanDateMax.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchPlanDateMax.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0012ArrivalHd.PlanDate < ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchActualDateMin && 0<SearchActualDateMin.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchActualDateMin.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0012ArrivalHd.ActualDate >= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchActualDateMax && 0<SearchActualDateMax.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchActualDateMax.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0012ArrivalHd.ActualDate < ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchSpCd && 0<SearchSpCd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchSpCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0012ArrivalHd.SpCd = ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchCom && 0<SearchCom.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchCom.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0012ArrivalHd.ArCom01 Like ?";
				sql = sql + " or WW0012ArrivalHd.ArCom02 Like ?";
				sql = sql + " or WW0012ArrivalHd.ArCom03 Like ?";
				sql = sql + " or WW0013ArrivalMs.Com01 Like ?";
				sql = sql + " or WW0013ArrivalMs.Com02 Like ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchEntryDateMin && 0<SearchEntryDateMin.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchEntryDateMin.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0012ArrivalHd.EntryDate >= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchEntryDateMax && 0<SearchEntryDateMax.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchEntryDateMax.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0012ArrivalHd.EntryDate <= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchUpdateDateMin && 0<SearchUpdateDateMin.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchUpdateDateMin.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0012ArrivalHd.UpdateDate >= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchUpdateDateMax && 0<SearchUpdateDateMax.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchUpdateDateMax.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0012ArrivalHd.UpdateDate <= ?";
			}
			sql = sql + ")\n";
		}
		
		if(null!=SearchEntryUser && 0<SearchEntryUser.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchEntryUser.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0012ArrivalHd.EntryUser Like ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchUpdateUser && 0<SearchUpdateUser.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchUpdateUser.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0012ArrivalHd.UpdateUser Like ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchItemCd && 0<SearchItemCd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchItemCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0013ArrivalMs.ItemCd = ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchClItemCd && 0<SearchClItemCd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchClItemCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KM0060_ITEMMST.ClItemCd = ?";
			}
			if(null!=SearchItemCdFromClItem && 0< SearchItemCdFromClItem.length) {
				for(int i=0;i<SearchItemCdFromClItem.length;i++) {
					sql = sql + " or (WW0012ArrivalHd.ClCd = ?";
					sql = sql + "  and WW0013ArrivalMs.ItemCd = ?)";
				}
			}
			sql = sql + ")\n";
		}
		if(null!=SearchItemName && 0<SearchItemName.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchItemName.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0013ArrivalMs.ItemName Like ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchLot && 0<SearchLot.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchLot.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0013ArrivalMs.Lot = ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchExpDateMin && 0<SearchExpDateMin.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchExpDateMin.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0013ArrivalMs.ExpDate >= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchExpDateMax && 0<SearchExpDateMax.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchExpDateMax.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0013ArrivalMs.ExpDate <= ?";
			}
			sql = sql + ")\n";
		}
		sql = sql + " group by WW0012ArrivalHd.ClWh,WW0012ArrivalHd.ClCd,KM0030_CLIENTMST.ClGpCD,WW0012ArrivalHd.ArrNo,WW0012ArrivalHd.ArrCount,WW0012ArrivalHd.SpCd";
		sql = sql + " order by WW0012ArrivalHd.ActualDate,WW0012ArrivalHd.SpCd,WW0012ArrivalHd.ArrNo,WW0012ArrivalHd.ArrCount,WW0013ArrivalMs.MsNo,WW0013ArrivalMs.MsSeq ;\n";
		
		//System.out.println(sql);
		
		if(SearchKick) {
			A100_DbConnect.DB_CONN("NYANKO");
			ResultSet rset01 = null;
			PreparedStatement stmt01 = null;
			try {
				stmt01 = A100_DbConnect.conn.prepareStatement(sql);
				int StmtCount = 0;
					
				if(null!=SearchClWh && 0<SearchClWh.size()){
					for(int i=0;i<SearchClWh.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchClWh.get(i)+"");
					}
				}
				if(null!=SearchClCd && 0<SearchClCd.size()){
					for(int i=0;i<SearchClCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchClCd.get(i)+"");
					}
				}
				if(null!=SearchClGpCD && 0<SearchClGpCD.size()){
					for(int i=0;i<SearchClGpCD.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchClGpCD.get(i)+"");
					}
				}
				if(null!=SearchArrNo && 0<SearchArrNo.size()){
					for(int i=0;i<SearchArrNo.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchArrNo.get(i)+"");
					}
				}
				if(null!=SearchArrCountMin && 0<SearchArrCountMin.size()){
					for(int i=0;i<SearchArrCountMin.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchArrCountMin.get(i)+"");
					}
				}
				if(null!=SearchArrCountMin && 0<SearchArrCountMax.size()){
					for(int i=0;i<SearchArrCountMin.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchArrCountMax.get(i)+"");
					}
				}
				if(null!=SearchClArrNo && 0<SearchClArrNo.size()){
					for(int i=0;i<SearchClArrNo.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchClArrNo.get(i)+"");
					}
				}
				if(null!=SearchPlanDateMin && 0<SearchPlanDateMin.size()){
					for(int i=0;i<SearchPlanDateMin.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchPlanDateMin.get(i)+"");
					}
				}
				if(null!=SearchPlanDateMax && 0<SearchPlanDateMax.size()){
					for(int i=0;i<SearchPlanDateMax.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchPlanDateMax.get(i)+"");
					}
				}
				if(null!=SearchActualDateMin && 0<SearchActualDateMin.size()){
					for(int i=0;i<SearchActualDateMin.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchActualDateMin.get(i)+"");
					}
				}
				if(null!=SearchActualDateMax && 0<SearchActualDateMax.size()){
					for(int i=0;i<SearchActualDateMax.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchActualDateMax.get(i)+"");
					}
				}
				if(null!=SearchSpCd && 0<SearchSpCd.size()){
					for(int i=0;i<SearchSpCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchSpCd.get(i)+"");
					}
				}
				if(null!=SearchCom && 0<SearchCom.size()){
					for(int i=0;i<SearchCom.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchCom.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchCom.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchCom.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchCom.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchCom.get(i)+"%");
					}
				}
				if(null!=SearchEntryDateMin && 0<SearchEntryDateMin.size()){
					for(int i=0;i<SearchEntryDateMin.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchEntryDateMin.get(i)+"");
					}
				}
				if(null!=SearchEntryDateMax && 0<SearchEntryDateMax.size()){
					for(int i=0;i<SearchEntryDateMax.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchEntryDateMax.get(i)+"");
					}
				}
				if(null!=SearchUpdateDateMin && 0<SearchUpdateDateMin.size()){
					for(int i=0;i<SearchUpdateDateMin.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchUpdateDateMin.get(i)+"");
					}
				}
				if(null!=SearchUpdateDateMax && 0<SearchUpdateDateMax.size()){
					for(int i=0;i<SearchUpdateDateMax.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchUpdateDateMax.get(i)+"");
					}
				}
				
				if(null!=SearchEntryUser && 0<SearchEntryUser.size()){
					for(int i=0;i<SearchEntryUser.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchEntryUser.get(i)+"%");
					}
				}
				if(null!=SearchUpdateUser && 0<SearchUpdateUser.size()){
					for(int i=0;i<SearchUpdateUser.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchUpdateUser.get(i)+"%");
					}
				}
				if(null!=SearchItemCd && 0<SearchItemCd.size()){
					for(int i=0;i<SearchItemCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchItemCd.get(i)+"");
					}
				}
				if(null!=SearchClItemCd && 0<SearchClItemCd.size()){
					for(int i=0;i<SearchClItemCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchClItemCd.get(i)+"");
					}
					if(null!=SearchItemCdFromClItem && 0< SearchItemCdFromClItem.length) {
						for(int i=0;i<SearchItemCdFromClItem.length;i++) {
							StmtCount = StmtCount+1;
							stmt01.setString(StmtCount, ""+SearchItemCdFromClItem[i][M100_ItemComversionMstRt.ColClCd]+"");
							StmtCount = StmtCount+1;
							stmt01.setString(StmtCount, ""+SearchItemCdFromClItem[i][M100_ItemComversionMstRt.ColItemCd]+"");
						}
					}
				}
				if(null!=SearchItemName && 0<SearchItemName.size()){
					for(int i=0;i<SearchItemName.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchItemName.get(i)+"%");
					}
				}
				if(null!=SearchLot && 0<SearchLot.size()){
					for(int i=0;i<SearchLot.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchLot.get(i)+"");
					}
				}
				if(null!=SearchExpDateMin && 0<SearchExpDateMin.size()){
					for(int i=0;i<SearchExpDateMin.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchExpDateMin.get(i)+"");
					}
				}
				if(null!=SearchExpDateMax && 0<SearchExpDateMax.size()){
					for(int i=0;i<SearchExpDateMax.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchExpDateMax.get(i)+"");
					}
				}
				rset01 = stmt01.executeQuery();
				
				Rt = B100_RtObjectCreate.B100_RtObjectCreate(rset01,RtArrivalHdRt());
				
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
	private static Object[][] SearchItemCdFromClItem(ArrayList<String> SearchClGpCd,ArrayList<String> SearchClCd,ArrayList<String> SearchClItemCd){
		//ArrayList<String> SearchClGpCd = new ArrayList<String>();		//荷主グループコード
		//ArrayList<String> SearchClCd = new ArrayList<String>();		//荷主コード
		ArrayList<String> SearchItemCd = new ArrayList<String>();		//商品コード
		//ArrayList<String> SearchClItemCd = new ArrayList<String>();	//荷主商品コード
		ArrayList<String> SearchItemName = new ArrayList<String>();		//商品名
		boolean AllSearch = false;
		Object[][] ItemComversionMstRt = null;
		if(null!=SearchClItemCd && 0<SearchClItemCd.size()) {
			ItemComversionMstRt = M100_ItemComversionMstRt.ItemComversionMstRt(
					SearchClGpCd,			//荷主グループコード
					SearchClCd,				//荷主コード
					SearchItemCd,			//商品コード
					SearchClItemCd,			//荷主商品コード
					SearchItemName,			//商品名
					AllSearch);
		}
		return ItemComversionMstRt;
	}
}