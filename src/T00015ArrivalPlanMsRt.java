import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class T00015ArrivalPlanMsRt{
	//入荷予定明細（各行にヘッダ情報展開）返却する
	
	static final int ColClWh			=  0;		//ヘッダ担当倉庫
	static final int ColClCd			=  1;		//ヘッダ荷主CD
	static final int ColCLName01		=  2;		//ヘッダ荷主名
	static final int ColClGpCD			=  3;		//ヘッダ荷主グループCD
	static final int ColCLGpName01		=  4;		//ヘッダ荷主グループ名1
	static final int ColArrNo			=  5;		//ヘッダ入荷予定NO（WMS採番）
	static final int ColClArrNo			=  6;		//ヘッダ荷主予定番号
	static final int ColPlanDate		=  7;		//ヘッダ入荷予定日
	static final int ColHdActualDate	=  8;		//ヘッダ入荷実績日
	static final int ColSpCd			=  9;		//ヘッダ仕入先CD
	static final int ColSpName01		= 10;		//ヘッダ仕入先名01
	static final int ColSpName02		= 11;		//ヘッダ仕入先名02
	static final int ColSpName03		= 12;		//ヘッダ仕入先名03
	static final int ColSpPost			= 13;		//ヘッダ仕入先郵便
	static final int ColSpAdd01			= 14;		//ヘッダ仕入先住所01
	static final int ColSpAdd02			= 15;		//ヘッダ仕入先住所02
	static final int ColSpAdd03			= 16;		//ヘッダ仕入先住所03
	static final int ColSpTel			= 17;		//ヘッダ仕入先電話
	static final int ColArCom01			= 18;		//ヘッダコメント1
	static final int ColArCom02			= 19;		//ヘッダコメント2
	static final int ColArCom03			= 20;		//ヘッダコメント3
	static final int ColHdEntryDate	= 21;		//ヘッダ登録日
	static final int ColHdUpdateDate	= 22;		//ヘッダ更新日
	static final int ColHdEntryUser	= 23;		//ヘッダ登録者
	static final int ColHdUpdateUser	= 24;		//ヘッダ更新者
	static final int ColFixFg			= 25;		//ヘッダ状況　完了:1 未完了:0　分納待ち:2  キャンセル:9
	
	static final int ColMsNo			= 26;		//明細番号
	static final int ColItemCd			= 27;		//商品コード
	static final int ColClItemCd		= 28;		//荷主商品コード
	static final int ColJanCd			= 29;		//ソースマーク_BCD（バラ）
	static final int ColItemMdNo		= 30;		//商品型番
	static final int ColItemName		= 31;		//商品名
	static final int Collot				= 32;		//ロット
	static final int ColExpDate			= 33;		//消費期限
	static final int ColPlanQty			= 34;		//予定数量
	static final int ColActualQty		= 35;		//実績数
	static final int ColActualDate		= 36;		//入荷日
	static final int ColCom01			= 37;		//コメント1
	static final int ColCom02			= 38;		//コメント2
	static final int ColEntryDate		= 39;		//登録日
	static final int ColUpdateDate		= 40;		//更新日
	static final int ColEntryUser		= 41;		//登録者
	static final int ColUpdateUser		= 42;		//更新者
	
	public static Object[][] RtArrivalPlanMsRt(
			){
		Object[][] RtArrivalPlanMsRt = {
				 {"ClWh"			,ColClWh			,"String"	,"ヘッダ担当倉庫"}
				,{"ClCd"			,ColClCd			,"String"	,"ヘッダ荷主CD"}
				,{"CLName01"		,ColClGpCD			,"String"	,"ヘッダ荷主名"}
				,{"ClGpCD"			,ColClGpCD			,"String"	,"ヘッダ荷主グループCD"}
				,{"CLGpName01"		,ColCLGpName01		,"String"	,"ヘッダ荷主グループ名1"}
				,{"ArrNo"			,ColArrNo			,"String"	,"ヘッダ入荷予定NO"}
				,{"ClArrNo"			,ColClArrNo			,"String"	,"ヘッダ荷主予定番号"}
				,{"PlanDate"		,ColPlanDate		,"String"	,"ヘッダ入荷予定日"}
				,{"HdActualDate"	,ColHdActualDate	,"String"	,"ヘッダ入荷実績日"}
				,{"SpCd"			,ColSpCd			,"String"	,"ヘッダ仕入先CD"}
				,{"SpName01"		,ColSpName01		,"String"	,"ヘッダ仕入先名01"}
				,{"SpName02"		,ColSpName02		,"String"	,"ヘッダ仕入先名02"}
				,{"SpName03"		,ColSpName03		,"String"	,"ヘッダ仕入先名03"}
				,{"SpPost"			,ColSpPost			,"String"	,"ヘッダ仕入先郵便"}
				,{"SpAdd01"			,ColSpAdd01			,"String"	,"ヘッダ仕入先住所01"}
				,{"SpAdd02"			,ColSpAdd02			,"String"	,"ヘッダ仕入先住所02"}
				,{"SpAdd03"			,ColSpAdd03			,"String"	,"ヘッダ仕入先住所03"}
				,{"SpTel"			,ColSpTel			,"String"	,"ヘッダ仕入先電話"}
				,{"ArCom01"			,ColArCom01			,"String"	,"ヘッダコメント1"}
				,{"ArCom02"			,ColArCom02			,"String"	,"ヘッダコメント2"}
				,{"ArCom03"			,ColArCom03			,"String"	,"ヘッダコメント3"}
				,{"HdEntryDate"		,ColHdEntryDate	,"String"	,"ヘッダ登録日"}
				,{"HdUpdateDate"	,ColHdUpdateDate	,"String"	,"ヘッダ更新日"}
				,{"HdEntryUser"		,ColHdEntryUser	,"String"	,"ヘッダ登録者"}
				,{"HdUpdateUser"	,ColHdUpdateUser	,"String"	,"ヘッダ更新者"}
				,{"FixFg"			,ColFixFg			,"int"		,"ヘッダ状況"}
					
				,{"MsNo"			,ColMsNo			,"int"		,"明細番号"}
				,{"ItemCd"			,ColItemCd			,"String"	,"商品コード"}
				,{"ClItemCd"		,ColClItemCd		,"String"	,"荷主商品コード"}
				,{"JanCd"			,ColJanCd			,"String"	,"JANCD（バラ）"}
				,{"ItemMdNo"		,ColItemMdNo		,"String"	,"商品型番"}
				,{"ItemName"		,ColItemName		,"String"	,"商品名"}
				,{"lot"				,Collot				,"String"	,"ロット"}
				,{"ExpDate"			,ColExpDate			,"String"	,"消費期限"}
				,{"PlanQty"			,ColPlanQty			,"int"		,"予定数量"}
				,{"ActualQty"		,ColActualQty		,"int"		,"実績数"}
				,{"ActualDate"		,ColActualDate		,"String"	,"入荷日"}
				,{"Com01"			,ColCom01			,"String"	,"コメント1"}
				,{"Com02"			,ColCom02			,"String"	,"コメント2"}
				,{"EntryDate"		,ColEntryDate		,"String"	,"登録日"}
				,{"UpdateDate"		,ColUpdateDate		,"String"	,"更新日"}
				,{"EntryUser"		,ColEntryUser		,"String"	,"登録者"}
				,{"UpdateUser"		,ColUpdateUser		,"String"	,"更新者"}
				};
		return RtArrivalPlanMsRt;
	}
	
	public static Object[][] ArrivalPlanMsRt(
			ArrayList<String> SearchClWh,				//ヘッダ担当倉庫
			ArrayList<String> SearchClCd,				//ヘッダ荷主CD
			ArrayList<String> SearchCLName01,			//ヘッダ荷主名
			ArrayList<String> SearchClGpCD,				//ヘッダ荷主グループCD
			ArrayList<String> SearchCLGpName01,			//ヘッダ荷主グループ名1
			ArrayList<String> SearchArrNo,				//ヘッダ入荷予定NO
			ArrayList<String> SearchClArrNo,			//ヘッダ荷主予定番号
			ArrayList<String> SearchPlanDateMin,		//ヘッダ入荷予定日
			ArrayList<String> SearchPlanDateMax,		//ヘッダ入荷予定日
			ArrayList<String> SearchHdActualDateMin,	//ヘッダ入荷実績日
			ArrayList<String> SearchHdActualDateMax,	//ヘッダ入荷実績日
			ArrayList<String> SearchSpCd,				//ヘッダ仕入先CD
			ArrayList<String> SearchSpName,				//ヘッダ仕入先名
			ArrayList<String> SearchSpPost,				//ヘッダ仕入先郵便
			ArrayList<String> SearchSpAdd,				//ヘッダ仕入先住所
			ArrayList<String> SearchSpTel,				//ヘッダ仕入先電話
			ArrayList<String> SearchArCom,				//ヘッダコメント
			ArrayList<Integer> SearchFixFg,				//ヘッダ状況
					
			ArrayList<Integer> SearchMsNoMin,			//明細番号最小
			ArrayList<Integer> SearchMsNoMax,			//明細番号最大
			ArrayList<String> SearchItemCd,				//商品コード
			ArrayList<String> SearchClItemCd,			//荷主商品コード
			ArrayList<String> SearchJanCd,				//JANCD（バラ）
			ArrayList<String> SearchItemMdNo,			//商品型番
			ArrayList<String> SearchItemName,			//商品名
			ArrayList<String> Searchlot,				//ロット
			ArrayList<String> SearchExpDateMin,			//消費期限最小
			ArrayList<String> SearchExpDateMax,			//消費期限最大
			ArrayList<Integer> SearchPlanQtyMin,		//予定数量最小
			ArrayList<Integer> SearchPlanQtyMax,		//予定数量最大
			ArrayList<Integer> SearchActualQtyMin,		//実績数
			ArrayList<Integer> SearchActualQtyMax,		//実績数
			ArrayList<String> SearchActualDateMin,		//入荷日
			ArrayList<String> SearchActualDateMax,		//入荷日
			ArrayList<String> SearchCom,				//コメント
			ArrayList<String> SearchEntryDateMin,		//登録日
			ArrayList<String> SearchEntryDateMax,		//登録日
			ArrayList<String> SearchUpdateDateMin,		//更新日
			ArrayList<String> SearchUpdateDateMax,		//更新日
			ArrayList<String> SearchEntryUser,			//登録者
			ArrayList<String> SearchUpdateUser,			//更新者
			boolean AllSearch){
		SearchClWh				= B00150ArrayListControl.ArryListStringUniqueList(SearchClWh);			//ヘッダ担当倉庫
		SearchClCd				= B00150ArrayListControl.ArryListStringUniqueList(SearchClCd);			//ヘッダ荷主CD
		SearchCLName01			= B00150ArrayListControl.ArryListStringUniqueList(SearchCLName01);		//ヘッダ荷主名
		SearchClGpCD			= B00150ArrayListControl.ArryListStringUniqueList(SearchClGpCD);			//ヘッダ荷主グループCD
		SearchCLGpName01		= B00150ArrayListControl.ArryListStringUniqueList(SearchCLGpName01);		//ヘッダ荷主グループ名1
		SearchArrNo				= B00150ArrayListControl.ArryListStringUniqueList(SearchArrNo);			//ヘッダ入荷予定NO
		SearchClArrNo			= B00150ArrayListControl.ArryListStringUniqueList(SearchClArrNo);			//ヘッダ荷主予定番号
		SearchPlanDateMin		= B00150ArrayListControl.ArryListStringUniqueList(SearchPlanDateMin);		//ヘッダ入荷予定日最小
		SearchPlanDateMax		= B00150ArrayListControl.ArryListStringUniqueList(SearchPlanDateMax);		//ヘッダ入荷予定日最大
		SearchHdActualDateMin	= B00150ArrayListControl.ArryListStringUniqueList(SearchHdActualDateMin);	//ヘッダ入荷実績日最小
		SearchHdActualDateMax	= B00150ArrayListControl.ArryListStringUniqueList(SearchHdActualDateMax);	//ヘッダ入荷実績日最大
		SearchSpCd				= B00150ArrayListControl.ArryListStringUniqueList(SearchSpCd);			//ヘッダ仕入先CD
		SearchSpName			= B00150ArrayListControl.ArryListStringUniqueList(SearchSpName);			//ヘッダ仕入先名
		SearchSpPost			= B00150ArrayListControl.ArryListStringUniqueList(SearchSpPost);			//ヘッダ仕入先郵便
		SearchSpAdd				= B00150ArrayListControl.ArryListStringUniqueList(SearchSpAdd);			//ヘッダ仕入先住所
		SearchSpTel				= B00150ArrayListControl.ArryListStringUniqueList(SearchSpTel);			//ヘッダ仕入先電話
		SearchArCom				= B00150ArrayListControl.ArryListStringUniqueList(SearchArCom);			//ヘッダコメント
		SearchFixFg				= B00150ArrayListControl.ArryListIntegerUniqueList(SearchFixFg);			//ヘッダ状況
				
		SearchMsNoMin			= B00150ArrayListControl.ArryListIntegerUniqueList(SearchMsNoMin);		//明細番号最小
		SearchMsNoMax			= B00150ArrayListControl.ArryListIntegerUniqueList(SearchMsNoMax);		//明細番号最大
		SearchItemCd			= B00150ArrayListControl.ArryListStringUniqueList(SearchItemCd);			//商品コード
		SearchClItemCd			= B00150ArrayListControl.ArryListStringUniqueList(SearchClItemCd);		//荷主商品コード
		SearchJanCd				= B00150ArrayListControl.ArryListStringUniqueList(SearchJanCd);			//JANCD（バラ）
		SearchItemMdNo			= B00150ArrayListControl.ArryListStringUniqueList(SearchItemMdNo);		//商品型番
		SearchItemName			= B00150ArrayListControl.ArryListStringUniqueList(SearchItemName);		//商品名
		Searchlot				= B00150ArrayListControl.ArryListStringUniqueList(Searchlot);				//ロット
		SearchExpDateMin		= B00150ArrayListControl.ArryListStringUniqueList(SearchExpDateMin);		//消費期限最小
		SearchExpDateMax		= B00150ArrayListControl.ArryListStringUniqueList(SearchExpDateMax);		//消費期限最大
		SearchPlanQtyMin		= B00150ArrayListControl.ArryListIntegerUniqueList(SearchPlanQtyMin);		//予定数量最小
		SearchPlanQtyMax		= B00150ArrayListControl.ArryListIntegerUniqueList(SearchPlanQtyMax);		//予定数量最大
		SearchActualQtyMin		= B00150ArrayListControl.ArryListIntegerUniqueList(SearchActualQtyMin);	//実績数
		SearchActualQtyMax		= B00150ArrayListControl.ArryListIntegerUniqueList(SearchActualQtyMax);	//実績数
		SearchActualDateMin		= B00150ArrayListControl.ArryListStringUniqueList(SearchActualDateMin);	//入荷日最小
		SearchActualDateMax		= B00150ArrayListControl.ArryListStringUniqueList(SearchActualDateMax);	//入荷日最大
		SearchCom				= B00150ArrayListControl.ArryListStringUniqueList(SearchCom);				//コメント
		SearchEntryDateMin		= B00150ArrayListControl.ArryListStringUniqueList(SearchEntryDateMin);	//登録日最小
		SearchEntryDateMax		= B00150ArrayListControl.ArryListStringUniqueList(SearchEntryDateMax);	//登録日最大
		SearchUpdateDateMin		= B00150ArrayListControl.ArryListStringUniqueList(SearchUpdateDateMin);	//更新日最小
		SearchUpdateDateMax		= B00150ArrayListControl.ArryListStringUniqueList(SearchUpdateDateMax);	//更新日最大
		SearchEntryUser			= B00150ArrayListControl.ArryListStringUniqueList(SearchEntryUser);		//登録者
		SearchUpdateUser		= B00150ArrayListControl.ArryListStringUniqueList(SearchUpdateUser);		//更新者
		
		//日付系項目最大は一日進めて00:00:00扱い　※時刻まで検索条件にする場合はそのまま
		if(null!=SearchPlanDateMax && 0<SearchPlanDateMax.size()){				//ヘッダ入荷予定日最大
			for(int i=0;i<SearchPlanDateMax.size();i++){
				String SetString = B00050ToolsDateTimeControl.DateFormat(SearchPlanDateMax.get(i));
				Timestamp SetTimestamp = B00050ToolsDateTimeControl.dtmTimestamp2(SetString)[0];
				SetTimestamp = B00050ToolsDateTimeControl.ndate_after(SetTimestamp,1);
				SetString = B00050ToolsDateTimeControl.dtmString2(SetTimestamp)[0];
				SearchPlanDateMax.set(i,SetString);
			}
		}
		if(null!=SearchHdActualDateMax && 0<SearchHdActualDateMax.size()){		//ヘッダ入荷実績日最大
			for(int i=0;i<SearchHdActualDateMax.size();i++){
				String SetString = B00050ToolsDateTimeControl.DateFormat(SearchHdActualDateMax.get(i));
				Timestamp SetTimestamp = B00050ToolsDateTimeControl.dtmTimestamp2(SetString)[0];
				SetTimestamp = B00050ToolsDateTimeControl.ndate_after(SetTimestamp,1);
				SetString = B00050ToolsDateTimeControl.dtmString2(SetTimestamp)[0];
				SearchPlanDateMax.set(i,SetString);
			}
		}
		if(null!=SearchExpDateMax && 0<SearchExpDateMax.size()){				//消費期限最大
			for(int i=0;i<SearchExpDateMax.size();i++){
				String SetString = B00050ToolsDateTimeControl.DateFormat(SearchExpDateMax.get(i));
				Timestamp SetTimestamp = B00050ToolsDateTimeControl.dtmTimestamp2(SetString)[0];
				SetTimestamp = B00050ToolsDateTimeControl.ndate_after(SetTimestamp,1);
				SetString = B00050ToolsDateTimeControl.dtmString2(SetTimestamp)[0];
				SearchPlanDateMax.set(i,SetString);
			}
		}
		if(null!=SearchActualDateMax && 0<SearchActualDateMax.size()){			//入荷日最大
			for(int i=0;i<SearchActualDateMax.size();i++){
				String SetString = B00050ToolsDateTimeControl.DateFormat(SearchActualDateMax.get(i));
				Timestamp SetTimestamp = B00050ToolsDateTimeControl.dtmTimestamp2(SetString)[0];
				SetTimestamp = B00050ToolsDateTimeControl.ndate_after(SetTimestamp,1);
				SetString = B00050ToolsDateTimeControl.dtmString2(SetTimestamp)[0];
				SearchPlanDateMax.set(i,SetString);
			}
		}
		Object[][] rt = new Object[0][RtArrivalPlanMsRt().length];
		boolean SearchKick = false;
		if(AllSearch) {SearchKick = true;}
		
		String sql = "select "
					+"(WW0010ArrivalPlanHd.ClWh)        as ClWh,\n"				//ヘッダ担当倉庫
					+"(WW0010ArrivalPlanHd.ClCd)        as ClCd,\n"				//ヘッダ荷主CD
					+"(KM0030_CLIENTMST.CLName01)       as CLName01,\n"			//ヘッダ荷主名
					+"(KM0030_CLIENTMST.ClGpCD)         as ClGpCD,\n"			//ヘッダ荷主グループCD
					+"(KM0031_CLIENT_GROUP.CLGpName01)  as CLGpName01,\n"		//ヘッダ荷主グループ名1
					+"(WW0010ArrivalPlanHd.ArrNo)       as ArrNo,\n"			//ヘッダ入荷予定NO（WMS採番）
					+"(WW0010ArrivalPlanHd.ClArrNo)     as ClArrNo,\n"			//ヘッダ荷主予定番号
					+"(WW0010ArrivalPlanHd.PlanDate)    as PlanDate,\n"			//ヘッダ入荷予定日
					+"(WW0010ArrivalPlanHd.ActualDate)  as HdActualDate,\n"		//ヘッダ入荷実績日
					+"(WW0010ArrivalPlanHd.SpCd)        as SpCd,\n"				//ヘッダ仕入先CD
					+"(WW0010ArrivalPlanHd.SpName01)    as SpName01,\n"			//ヘッダ仕入先名01
					+"(WW0010ArrivalPlanHd.SpName02)    as SpName02,\n"			//ヘッダ仕入先名02
					+"(WW0010ArrivalPlanHd.SpName03)    as SpName03,\n"			//ヘッダ仕入先名03
					+"(WW0010ArrivalPlanHd.SpPost)      as SpPost,\n"			//ヘッダ仕入先郵便
					+"(WW0010ArrivalPlanHd.SpAdd01)     as SpAdd01,\n"			//ヘッダ仕入先住所01
					+"(WW0010ArrivalPlanHd.SpAdd02)     as SpAdd02,\n"			//ヘッダ仕入先住所02
					+"(WW0010ArrivalPlanHd.SpAdd03)     as SpAdd03,\n"			//ヘッダ仕入先住所03
					+"(WW0010ArrivalPlanHd.SpTel)       as SpTel,\n"			//ヘッダ仕入先電話
					+"(WW0010ArrivalPlanHd.ArCom01)     as ArCom01,\n"			//ヘッダコメント1
					+"(WW0010ArrivalPlanHd.ArCom02)     as ArCom02,\n"			//ヘッダコメント2
					+"(WW0010ArrivalPlanHd.ArCom03)     as ArCom03,\n"			//ヘッダコメント3
					+"(WW0010ArrivalPlanHd.EntryDate)   as HdEntryDate,\n"		//ヘッダ登録日
					+"(WW0010ArrivalPlanHd.UpdateDate)  as HdUpdateDate,\n"		//ヘッダ更新日
					+"(WW0010ArrivalPlanHd.EntryUser)   as HdEntryUser,\n"		//ヘッダ登録者
					+"(WW0010ArrivalPlanHd.UpdateUser)  as HdUpdateUser,\n"		//ヘッダ更新者
					+"(WW0010ArrivalPlanHd.FixFg)       as FixFg,\n"			//ヘッダ状況　完了:1 未完了:0　分納待ち:2  キャンセル:9
					
					+"(WW0011ArrivalPlanMs.MsNo)        as MsNo,\n"				//明細番号
					+"(WW0011ArrivalPlanMs.ItemCd)      as ItemCd,\n"			//商品コード
					+"(WW0011ArrivalPlanMs.ClItemCd)    as ClItemCd,\n"			//荷主商品コード
					+"(WW0011ArrivalPlanMs.JanCd)       as JanCd,\n"			//ソースマーク_BCD（バラ）
					+"(WW0011ArrivalPlanMs.ItemMdNo)    as ItemMdNo,\n"			//商品型番
					+"(WW0011ArrivalPlanMs.ItemName)    as ItemName,\n"			//商品名
					+"(WW0011ArrivalPlanMs.lot)         as lot,\n"				//ロット
					+"(WW0011ArrivalPlanMs.ExpDate)     as ExpDate,\n"			//消費期限
					+"(WW0011ArrivalPlanMs.PlanQty)     as PlanQty,\n"			//予定数量
					+"(WW0011ArrivalPlanMs.ActualQty)   as ActualQty,\n"		//実績数
					+"(WW0011ArrivalPlanMs.ActualDate)  as ActualDate,\n"		//入荷日
					+"(WW0011ArrivalPlanMs.Com01)       as Com01,\n"			//コメント1
					+"(WW0011ArrivalPlanMs.Com02)       as Com02,\n"			//コメント2
					+"(WW0011ArrivalPlanMs.EntryDate)   as EntryDate,\n"		//登録日
					+"(WW0011ArrivalPlanMs.UpdateDate)  as UpdateDate,\n"		//更新日
					+"(WW0011ArrivalPlanMs.EntryUser)   as EntryUser,\n"		//登録者
					+"(WW0011ArrivalPlanMs.UpdateUser)  as UpdateUser\n"		//更新者
					
					+" from "+A00000Main.MySqlDefaultSchemaWANKO+".WW0011ArrivalPlanMs \n"
					+" left outer join "+A00000Main.MySqlDefaultSchemaWANKO+".WW0010ArrivalPlanHd \n"
					+" on(WW0011ArrivalPlanMs.ClWh = WW0010ArrivalPlanHd.ClWh"
					+" and WW0011ArrivalPlanMs.ClCd = WW0010ArrivalPlanHd.ClCd"
					+" and WW0011ArrivalPlanMs.ArrNo = WW0010ArrivalPlanHd.ArrNo"
					+ ")\n"
					+ " left outer join "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0030_CLIENTMST"
					+ " on("
					+ " WW0011ArrivalPlanMs.ClCd = KM0030_CLIENTMST.cl_cd"
					+ ")\n"
					+ " left outer join "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP"
					+ " on("
					+ " KM0030_CLIENTMST.ClGpCd = KM0031_CLIENT_GROUP.ClGpCD"
					+ ")\n"
					+" where 1=1 \n";
		
		if(null!=SearchClWh && 0<SearchClWh.size()){						//ヘッダ担当倉庫
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchClWh.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0010ArrivalPlanHd.ClWh = ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchClCd && 0<SearchClCd.size()){						//ヘッダ荷主CD
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchClCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0010ArrivalPlanHd.ClCd = ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchCLName01 && 0<SearchCLName01.size()){				//ヘッダ荷主名
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchCLName01.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KM0030_CLIENTMST.CLName01 Like ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchClGpCD && 0<SearchClGpCD.size()){					//ヘッダ荷主グループCD
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchClGpCD.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KM0030_CLIENTMST.ClGpCD = ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchCLGpName01 && 0<SearchCLGpName01.size()){			//ヘッダ荷主グループ名1
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchCLGpName01.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KM0031_CLIENT_GROUP.CLGpName01 Like ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchArrNo && 0<SearchArrNo.size()){						//ヘッダ入荷予定NO
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchArrNo.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0010ArrivalPlanHd.ArrNo = ?";
			}
			sql = sql + ")";
		}	
		if(null!=SearchClArrNo && 0<SearchClArrNo.size()){					//ヘッダ荷主予定番号
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchClArrNo.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0010ArrivalPlanHd.ClArrNo = ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchPlanDateMin && 0<SearchPlanDateMin.size()){			//ヘッダ入荷予定日最小
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchPlanDateMin.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0010ArrivalPlanHd.PlanDate >= ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchPlanDateMax && 0<SearchPlanDateMax.size()){			//ヘッダ入荷予定日最大
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchPlanDateMax.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0010ArrivalPlanHd.PlanDate < ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchHdActualDateMin && 0<SearchHdActualDateMin.size()){	//ヘッダ入荷実績日最小
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchHdActualDateMin.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0010ArrivalPlanHd.ActualDate >= ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchHdActualDateMax && 0<SearchHdActualDateMax.size()){	//ヘッダ入荷実績日最大
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchHdActualDateMax.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0010ArrivalPlanHd.ActualDate < ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchSpCd && 0<SearchSpCd.size()){						//ヘッダ仕入先CD
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchSpCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0010ArrivalPlanHd.SpCd = ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchSpName && 0<SearchSpName.size()){					//ヘッダ仕入先名
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchSpName.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0010ArrivalPlanHd.SpName01 Like ?";
				sql = sql + " or WW0010ArrivalPlanHd.SpName02 Like ?";
				sql = sql + " or WW0010ArrivalPlanHd.SpName03 Like ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchSpPost && 0<SearchSpPost.size()){					//ヘッダ仕入先郵便
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchSpPost.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0010ArrivalPlanHd.SpPost = ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchSpAdd && 0<SearchSpAdd.size()){						//ヘッダ仕入先住所
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchSpAdd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " CONCAT (WW0010ArrivalPlanHd.SpAdd01";
				sql = sql + " , WW0010ArrivalPlanHd.SpAdd02";
				sql = sql + " , WW0010ArrivalPlanHd.SpAdd03) like ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchSpTel && 0<SearchSpTel.size()){						//ヘッダ仕入先電話
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchSpTel.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0010ArrivalPlanHd.SpTel Like ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchArCom && 0<SearchArCom.size()){						//ヘッダコメント
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchArCom.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0010ArrivalPlanHd.ArCom01 Like ?";
				sql = sql + " or WW0010ArrivalPlanHd.ArCom02 Like ?";
				sql = sql + " or WW0010ArrivalPlanHd.ArCom03 Like ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchFixFg && 0<SearchFixFg.size()){						//ヘッダ状況
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchFixFg.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0010ArrivalPlanHd.FixFg = ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchMsNoMin && 0<SearchMsNoMin.size()){					//明細番号最小
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchMsNoMin.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0011ArrivalPlanMs.MsNo >= ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchMsNoMax && 0<SearchMsNoMax.size()){					//明細番号最大
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchMsNoMax.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0011ArrivalPlanMs.MsNo <= ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchItemCd && 0<SearchItemCd.size()){					//商品コード
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchItemCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0011ArrivalPlanMs.ItemCd = ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchClItemCd && 0<SearchClItemCd.size()){				//荷主商品コード
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchClItemCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0011ArrivalPlanMs.ClItemCd = ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchJanCd && 0<SearchJanCd.size()){						//JANCD（バラ）
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchJanCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0011ArrivalPlanMs.JanCd = ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchItemMdNo && 0<SearchItemMdNo.size()){				//商品型番
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchItemMdNo.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0011ArrivalPlanMs.ItemMdNo = ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchItemName && 0<SearchItemName.size()){				//商品名
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchItemName.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0011ArrivalPlanMs.ItemName Like ?";
			}
			sql = sql + ")";
		}
		if(null!=Searchlot && 0<Searchlot.size()){							//ロット
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<Searchlot.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0011ArrivalPlanMs.lot = ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchExpDateMin && 0<SearchExpDateMin.size()){			//消費期限最小
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchExpDateMin.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0011ArrivalPlanMs.ExpDate >= ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchExpDateMax && 0<SearchExpDateMax.size()){			//消費期限最大
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchExpDateMax.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0011ArrivalPlanMs.ExpDate < ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchPlanQtyMin && 0<SearchPlanQtyMin.size()){			//予定数量最小
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchPlanQtyMin.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0011ArrivalPlanMs.PlanQty >= ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchPlanQtyMax && 0<SearchPlanQtyMax.size()){			//予定数量最大
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchPlanQtyMax.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0011ArrivalPlanMs.PlanQty <= ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchActualQtyMin && 0<SearchActualQtyMin.size()){		//実績数最小
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchActualQtyMin.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0011ArrivalPlanMs.ActualQty >= ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchActualQtyMax && 0<SearchActualQtyMax.size()){		//実績数最大
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchActualQtyMax.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0011ArrivalPlanMs.ActualQty <= ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchActualDateMin && 0<SearchActualDateMin.size()){		//入荷日最小
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchActualDateMin.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0011ArrivalPlanMs.ActualDate >= ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchActualDateMax && 0<SearchActualDateMax.size()){		//入荷日最大
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchActualDateMax.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0011ArrivalPlanMs.ActualDate < ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchCom && 0<SearchCom.size()){							//コメント
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchCom.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0011ArrivalPlanMs.Com01 Like ?";
				sql = sql + "or WW0011ArrivalPlanMs.Com02 Like ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchEntryDateMin && 0<SearchEntryDateMin.size()){		//登録日最小
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchEntryDateMin.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0011ArrivalPlanMs.EntryDate >= ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchEntryDateMax && 0<SearchEntryDateMax.size()){		//登録日最大
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchEntryDateMax.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0011ArrivalPlanMs.EntryDate <= ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchUpdateDateMin && 0<SearchUpdateDateMin.size()){		//更新日最小
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchUpdateDateMin.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0011ArrivalPlanMs.UpdateDate >= ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchUpdateDateMax && 0<SearchUpdateDateMax.size()){		//更新日最大
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchUpdateDateMax.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0011ArrivalPlanMs.UpdateDate <= ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchEntryUser && 0<SearchEntryUser.size()){				//登録者
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchEntryUser.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0011ArrivalPlanMs.EntryUser Like ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchUpdateUser && 0<SearchUpdateUser.size()){			//更新者
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchUpdateUser.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0011ArrivalPlanMs.UpdateUser Like ?";
			}
			sql = sql + ")";
		}
		
		sql = sql + " order by WW0010ArrivalPlanHd.ClWh,WW0010ArrivalPlanHd.ClCd,WW0010ArrivalPlanHd.ArrNo,WW0011ArrivalPlanMs.MsNo";
		//System.out.println(sql);
		if(SearchKick) {
			A00010DbConnect.DB_CONN("NYANKO");
			ResultSet rset01 = null;
			PreparedStatement stmt01 = null;
			try {
				stmt01 = A00010DbConnect.conn.prepareStatement(sql);
				int StmtCount = 0;
				
				if(null!=SearchClWh && 0<SearchClWh.size()){						//ヘッダ担当倉庫
					for(int i=0;i<SearchClWh.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchClWh.get(i)+"");
					}
				}
				if(null!=SearchClCd && 0<SearchClCd.size()){						//ヘッダ荷主CD
					for(int i=0;i<SearchClCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchClCd.get(i)+"");
					}
				}
				if(null!=SearchCLName01 && 0<SearchCLName01.size()){				//ヘッダ荷主名
					for(int i=0;i<SearchCLName01.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchCLName01.get(i)+"%");
					}
				}
				if(null!=SearchClGpCD && 0<SearchClGpCD.size()){					//ヘッダ荷主グループCD
					for(int i=0;i<SearchClGpCD.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchClGpCD.get(i)+"");
					}
				}
				if(null!=SearchCLGpName01 && 0<SearchCLGpName01.size()){			//ヘッダ荷主グループ名1
					for(int i=0;i<SearchCLGpName01.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchCLGpName01.get(i)+"%");
					}
				}
				if(null!=SearchArrNo && 0<SearchArrNo.size()){						//ヘッダ入荷予定NO
					for(int i=0;i<SearchArrNo.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchArrNo.get(i)+"");
					}
				}
				if(null!=SearchClArrNo && 0<SearchClArrNo.size()){					//ヘッダ荷主予定番号
					for(int i=0;i<SearchClArrNo.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchClArrNo.get(i)+"");
					}
				}
				if(null!=SearchPlanDateMin && 0<SearchPlanDateMin.size()){			//ヘッダ入荷予定日最小
					for(int i=0;i<SearchPlanDateMin.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchPlanDateMin.get(i)+"");
					}
				}
				if(null!=SearchPlanDateMax && 0<SearchPlanDateMax.size()){			//ヘッダ入荷予定日最大
					for(int i=0;i<SearchPlanDateMax.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchPlanDateMax.get(i)+"");
					}
				}
				if(null!=SearchHdActualDateMin && 0<SearchHdActualDateMin.size()){	//ヘッダ入荷実績日最小
					for(int i=0;i<SearchHdActualDateMin.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchHdActualDateMin.get(i)+"");
					}
				}
				if(null!=SearchHdActualDateMax && 0<SearchHdActualDateMax.size()){	//ヘッダ入荷実績日最大
					for(int i=0;i<SearchHdActualDateMax.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchHdActualDateMax.get(i)+"");
					}
				}
				if(null!=SearchSpCd && 0<SearchSpCd.size()){						//ヘッダ仕入先CD
					for(int i=0;i<SearchSpCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchSpCd.get(i)+"");
					}
				}
				if(null!=SearchSpName && 0<SearchSpName.size()){					//ヘッダ仕入先名
					for(int i=0;i<SearchSpName.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchSpName.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchSpName.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchSpName.get(i)+"%");
					}
				}
				if(null!=SearchSpPost && 0<SearchSpPost.size()){					//ヘッダ仕入先郵便
					for(int i=0;i<SearchSpPost.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchSpPost.get(i)+"%");
					}
				}
				if(null!=SearchSpAdd && 0<SearchSpAdd.size()){						//ヘッダ仕入先住所
					for(int i=0;i<SearchSpAdd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchSpAdd.get(i)+"%");
					}
				}
				if(null!=SearchSpTel && 0<SearchSpTel.size()){						//ヘッダ仕入先電話
					for(int i=0;i<SearchSpTel.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchSpTel.get(i)+"%");
					}
				}
				if(null!=SearchArCom && 0<SearchArCom.size()){						//ヘッダコメント
					for(int i=0;i<SearchArCom.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchArCom.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchArCom.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchArCom.get(i)+"%");
					}
				}
				if(null!=SearchFixFg && 0<SearchFixFg.size()){						//ヘッダ状況
					for(int i=0;i<SearchFixFg.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchFixFg.get(i)+"");
					}
				}
				
				if(null!=SearchMsNoMin && 0<SearchMsNoMin.size()){					//明細番号最小
					for(int i=0;i<SearchMsNoMin.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchMsNoMin.get(i)+"");
					}
				}
				if(null!=SearchMsNoMax && 0<SearchMsNoMax.size()){					//明細番号最大
					for(int i=0;i<SearchMsNoMax.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchMsNoMax.get(i)+"");
					}
				}
				if(null!=SearchItemCd && 0<SearchItemCd.size()){					//商品コード
					for(int i=0;i<SearchItemCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchItemCd.get(i)+"");
					}
				}
				if(null!=SearchClItemCd && 0<SearchClItemCd.size()){				//荷主商品コード
					for(int i=0;i<SearchClItemCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchClItemCd.get(i)+"");
					}
				}
				if(null!=SearchJanCd && 0<SearchJanCd.size()){						//JANCD（バラ）
					for(int i=0;i<SearchJanCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchJanCd.get(i)+"");
					}
				}
				if(null!=SearchItemMdNo && 0<SearchItemMdNo.size()){				//商品型番
					for(int i=0;i<SearchItemMdNo.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchItemMdNo.get(i)+"");
					}
				}
				if(null!=SearchItemName && 0<SearchItemName.size()){				//商品名
					for(int i=0;i<SearchItemName.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchItemName.get(i)+"%");
					}
				}
				if(null!=Searchlot && 0<Searchlot.size()){							//ロット
					for(int i=0;i<Searchlot.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+Searchlot.get(i)+"");
					}
				}
				if(null!=SearchExpDateMin && 0<SearchExpDateMin.size()){			//消費期限最小
					for(int i=0;i<SearchExpDateMin.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchExpDateMin.get(i)+"");
					}
				}
				if(null!=SearchExpDateMax && 0<SearchExpDateMax.size()){			//消費期限最大
					for(int i=0;i<SearchExpDateMax.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchExpDateMax.get(i)+"");
					}
				}
				if(null!=SearchPlanQtyMin && 0<SearchPlanQtyMin.size()){			//予定数量最小
					for(int i=0;i<SearchPlanQtyMin.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchPlanQtyMin.get(i)+"");
					}
				}
				if(null!=SearchPlanQtyMax && 0<SearchPlanQtyMax.size()){			//予定数量最大
					for(int i=0;i<SearchPlanQtyMax.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchPlanQtyMax.get(i)+"");
					}
				}
				if(null!=SearchActualQtyMin && 0<SearchActualQtyMin.size()){		//実績数最小
					for(int i=0;i<SearchActualQtyMin.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchActualQtyMin.get(i)+"");
					}
				}
				if(null!=SearchActualQtyMax && 0<SearchActualQtyMax.size()){		//実績数最大
					for(int i=0;i<SearchActualQtyMax.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchActualQtyMax.get(i)+"");
					}
				}
				if(null!=SearchActualDateMin && 0<SearchActualDateMin.size()){		//入荷日最小
					for(int i=0;i<SearchActualDateMin.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchActualDateMin.get(i)+"");
					}
				}
				if(null!=SearchActualDateMax && 0<SearchActualDateMax.size()){		//入荷日最大
					for(int i=0;i<SearchActualDateMax.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchActualDateMax.get(i)+"");
					}
				}
				if(null!=SearchCom && 0<SearchCom.size()){							//コメント
					for(int i=0;i<SearchCom.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchCom.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchCom.get(i)+"%");
					}
				}
				if(null!=SearchEntryDateMin && 0<SearchEntryDateMin.size()){		//登録日最小
					for(int i=0;i<SearchEntryDateMin.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchEntryDateMin.get(i)+"");
					}
				}
				if(null!=SearchEntryDateMax && 0<SearchEntryDateMax.size()){		//登録日最大
					for(int i=0;i<SearchEntryDateMax.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchEntryDateMax.get(i)+"");
					}
				}
				if(null!=SearchUpdateDateMin && 0<SearchUpdateDateMin.size()){		//更新日最小
					for(int i=0;i<SearchUpdateDateMin.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchUpdateDateMin.get(i)+"");
					}
				}
				if(null!=SearchUpdateDateMax && 0<SearchUpdateDateMax.size()){		//更新日最大
					for(int i=0;i<SearchUpdateDateMax.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchUpdateDateMax.get(i)+"");
					}
				}
				if(null!=SearchEntryUser && 0<SearchEntryUser.size()){				//登録者
					for(int i=0;i<SearchEntryUser.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchEntryUser.get(i)+"%");
					}
				}
				if(null!=SearchUpdateUser && 0<SearchUpdateUser.size()){			//更新者
					for(int i=0;i<SearchUpdateUser.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchUpdateUser.get(i)+"%");
					}
				}
				rset01 = stmt01.executeQuery();
				
				int counter = 0;
				rset01.beforeFirst();
				while (rset01.next()) {
					counter=counter+1;
				}
				rt = new Object[counter][RtArrivalPlanMsRt().length];
				counter = 0;
				rset01.beforeFirst();
				while (rset01.next()) {
					if(null==rset01.getString("ClWh"			)){rt[counter][ColClWh]				="";}else{rt[counter][ColClWh]			=rset01.getString("ClWh");}			//ヘッダ担当倉庫
					if(null==rset01.getString("ClCd"			)){rt[counter][ColClCd]				="";}else{rt[counter][ColClCd]			=rset01.getString("ClCd");}			//ヘッダ荷主CD
					if(null==rset01.getString("CLName01"		)){rt[counter][ColCLName01]			="";}else{rt[counter][ColCLName01]		=rset01.getString("CLName01");}		//ヘッダ荷主名
					if(null==rset01.getString("ClGpCD"			)){rt[counter][ColClGpCD]			="";}else{rt[counter][ColClGpCD]		=rset01.getString("ClGpCD");}		//ヘッダ荷主グループCD
					if(null==rset01.getString("CLGpName01"		)){rt[counter][ColCLGpName01]		="";}else{rt[counter][ColCLGpName01]	=rset01.getString("CLGpName01");}	//ヘッダ荷主グループ名1
					if(null==rset01.getString("ArrNo"			)){rt[counter][ColArrNo]			="";}else{rt[counter][ColArrNo]			=rset01.getString("ArrNo");}		//ヘッダ入荷予定NO（WMS採番）
					if(null==rset01.getString("ClArrNo"			)){rt[counter][ColClArrNo]			="";}else{rt[counter][ColClArrNo]		=rset01.getString("ClArrNo");}		//ヘッダ荷主予定番号
					if(null==rset01.getTimestamp("PlanDate"		)){rt[counter][ColPlanDate]			="";}else{rt[counter][ColPlanDate]		=B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("PlanDate"))[0];}			//ヘッダ入荷予定日
					if(null==rset01.getTimestamp("HdActualDate"	)){rt[counter][ColHdActualDate]	="";}else{rt[counter][ColHdActualDate]	=B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("HdActualDate"))[1];}		//ヘッダ入荷実績日
					if(null==rset01.getString("SpCd"			)){rt[counter][ColSpCd]				="";}else{rt[counter][ColSpCd]			=rset01.getString("SpCd");}			//ヘッダ仕入先CD
					if(null==rset01.getString("SpName01"		)){rt[counter][ColSpName01]			="";}else{rt[counter][ColSpName01]		=rset01.getString("SpName01");}		//ヘッダ仕入先名01
					if(null==rset01.getString("SpName02"		)){rt[counter][ColSpName02]			="";}else{rt[counter][ColSpName02]		=rset01.getString("SpName02");}		//ヘッダ仕入先名02
					if(null==rset01.getString("SpName03"		)){rt[counter][ColSpName03]			="";}else{rt[counter][ColSpName03]		=rset01.getString("SpName03");}		//ヘッダ仕入先名03
					if(null==rset01.getString("SpPost"			)){rt[counter][ColSpPost]			="";}else{rt[counter][ColSpPost]		=rset01.getString("SpPost");}		//ヘッダ仕入先郵便
					if(null==rset01.getString("SpAdd01"			)){rt[counter][ColSpAdd01]			="";}else{rt[counter][ColSpAdd01]		=rset01.getString("SpAdd01");}		//ヘッダ仕入先住所01
					if(null==rset01.getString("SpAdd02"			)){rt[counter][ColSpAdd02]			="";}else{rt[counter][ColSpAdd02]		=rset01.getString("SpAdd02");}		//ヘッダ仕入先住所02
					if(null==rset01.getString("SpAdd03"			)){rt[counter][ColSpAdd03]			="";}else{rt[counter][ColSpAdd03]		=rset01.getString("SpAdd03");}		//ヘッダ仕入先住所03
					if(null==rset01.getString("SpTel"			)){rt[counter][ColSpTel]			="";}else{rt[counter][ColSpTel]			=rset01.getString("SpTel");}		//ヘッダ仕入先電話
					if(null==rset01.getString("ArCom01"			)){rt[counter][ColArCom01]			="";}else{rt[counter][ColArCom01]		=rset01.getString("ArCom01");}		//ヘッダコメント1
					if(null==rset01.getString("ArCom02"			)){rt[counter][ColArCom02]			="";}else{rt[counter][ColArCom02]		=rset01.getString("ArCom02");}		//ヘッダコメント2
					if(null==rset01.getString("ArCom03"			)){rt[counter][ColArCom03]			="";}else{rt[counter][ColArCom03]		=rset01.getString("ArCom03");}		//ヘッダコメント3
					if(null==rset01.getTimestamp("HdEntryDate"	)){rt[counter][ColHdEntryDate]		="";}else{rt[counter][ColHdEntryDate]	=B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("HdEntryDate"))[1];}			//ヘッダ登録日
					if(null==rset01.getTimestamp("HdUpdateDate"	)){rt[counter][ColHdUpdateDate]	="";}else{rt[counter][ColHdUpdateDate]	=B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("HdUpdateDate"))[1];}		//ヘッダ更新日
					if(null==rset01.getString("HdEntryUser"		)){rt[counter][ColHdEntryUser]		="";}else{rt[counter][ColHdEntryUser]	=rset01.getString("HdEntryUser");}	//ヘッダ登録者
					if(null==rset01.getString("HdUpdateUser"	)){rt[counter][ColHdUpdateUser]	="";}else{rt[counter][ColHdUpdateUser]	=rset01.getString("HdUpdateUser");}	//ヘッダ更新者
					rt[counter][ColFixFg]		=rset01.getInt("FixFg");		//ヘッダ状況　完了:1 未完了:0　分納待ち:2  キャンセル:9
					
					rt[counter][ColMsNo]		=rset01.getInt("MsNo");			//明細番号
					if(null==rset01.getString("ItemCd"			)){rt[counter][ColItemCd]			="";}else{rt[counter][ColItemCd]		=rset01.getString("ItemCd");}		//商品コード
					if(null==rset01.getString("ClItemCd"		)){rt[counter][ColClItemCd]			="";}else{rt[counter][ColClItemCd]		=rset01.getString("ClItemCd");}		//荷主商品コード
					if(null==rset01.getString("JanCd"			)){rt[counter][ColJanCd]			="";}else{rt[counter][ColJanCd]			=rset01.getString("JanCd");}		//ソースマーク_BCD（バラ）
					if(null==rset01.getString("ItemMdNo"		)){rt[counter][ColItemMdNo]			="";}else{rt[counter][ColItemMdNo]		=rset01.getString("ItemMdNo");}		//商品型番
					if(null==rset01.getString("ItemName"		)){rt[counter][ColItemName]			="";}else{rt[counter][ColItemName]		=rset01.getString("ItemName");}		//商品名
					if(null==rset01.getString("lot"				)){rt[counter][Collot]				="";}else{rt[counter][Collot]			=rset01.getString("lot");}			//ロット
					if(null==rset01.getTimestamp("ExpDate"		)){rt[counter][ColExpDate]			="";}else{rt[counter][ColExpDate]		=B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("ExpDate"))[0];}			//消費期限
					rt[counter][ColPlanQty]		=rset01.getInt("PlanQty");		//予定数量
					rt[counter][ColActualQty]	=rset01.getInt("ActualQty");	//実績数
					if(null==rset01.getTimestamp("ActualDate"	)){rt[counter][ColActualDate]		="";}else{rt[counter][ColActualDate]	=B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("ActualDate"))[1];}		//入荷日
					if(null==rset01.getString("Com01"			)){rt[counter][ColCom01]			="";}else{rt[counter][ColCom01]			=rset01.getString("Com01");}		//コメント1
					if(null==rset01.getString("Com02"			)){rt[counter][ColCom02]			="";}else{rt[counter][ColCom02]			=rset01.getString("Com02");}		//コメント2
					if(null==rset01.getTimestamp("EntryDate"	)){rt[counter][ColEntryDate]		="";}else{rt[counter][ColEntryDate]	=B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("EntryDate"))[1];}		//登録日
					if(null==rset01.getTimestamp("UpdateDate"	)){rt[counter][ColUpdateDate]		="";}else{rt[counter][ColUpdateDate]	=B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("UpdateDate"))[1];}		//更新日
					if(null==rset01.getString("EntryUser"		)){rt[counter][ColEntryUser]		="";}else{rt[counter][ColEntryUser]	=rset01.getString("EntryUser");}	//登録者
					if(null==rset01.getString("UpdateUser"		)){rt[counter][ColUpdateUser]		="";}else{rt[counter][ColUpdateUser]	=rset01.getString("UpdateUser");}	//更新者
					
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