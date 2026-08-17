import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class T100_ArrivalPlanHdRt{
	/*
	コピペ用
	ArrayList<String> SearchClWh 			= new ArrayList<String>();		//ヘッダ担当倉庫
	ArrayList<String> SearchClCd 			= new ArrayList<String>();		//ヘッダ荷主CD
	ArrayList<String> SearchCLName01 		= new ArrayList<String>();		//ヘッダ荷主名
	ArrayList<String> SearchClGpCD 			= new ArrayList<String>();		//ヘッダ荷主グループCD
	ArrayList<String> SearchCLGpName01 		= new ArrayList<String>();		//ヘッダ荷主グループ標記名
	ArrayList<String> SearchArrNo 			= new ArrayList<String>();		//ヘッダ入荷予定NO
	ArrayList<String> SearchClArrNo 		= new ArrayList<String>();		//ヘッダ荷主予定番号
	ArrayList<String> SearchPlanDateMin 	= new ArrayList<String>();		//ヘッダ入荷予定日最小
	ArrayList<String> SearchPlanDateMax 	= new ArrayList<String>();		//ヘッダ入荷予定日最大
	ArrayList<String> SearchHdActualDateMin = new ArrayList<String>();		//ヘッダ入荷実績日最小
	ArrayList<String> SearchHdActualDateMax	= new ArrayList<String>();		//ヘッダ入荷実績日最大
	ArrayList<String> SearchSpCd 			= new ArrayList<String>();		//ヘッダ仕入先CD
	ArrayList<String> SearchSpName 			= new ArrayList<String>();		//ヘッダ仕入先名
	ArrayList<String> SearchSpPost 			= new ArrayList<String>();		//ヘッダ仕入先郵便
	ArrayList<String> SearchSpAdd 			= new ArrayList<String>();		//ヘッダ仕入先住所
	ArrayList<String> SearchSpTel 			= new ArrayList<String>();		//ヘッダ仕入先電話
	ArrayList<String> SearchArCom 			= new ArrayList<String>();		//ヘッダコメント
	ArrayList<Integer> SearchFixFg 			= new ArrayList<Integer>();		//ヘッダ状況
			
	ArrayList<Integer> SearchMsNoMin 		= new ArrayList<Integer>();		//明細番号最小
	ArrayList<Integer> SearchMsNoMax 		= new ArrayList<Integer>();		//明細番号最大
	ArrayList<String> SearchItemCd 			= new ArrayList<String>();		//商品コード
	ArrayList<String> SearchClItemCd 		= new ArrayList<String>();		//荷主商品コード
	ArrayList<String> SearchJanCd 			= new ArrayList<String>();		//JANCD（バラ）
	ArrayList<String> SearchItemMdNo 		= new ArrayList<String>();		//商品型番
	ArrayList<String> SearchItemName 		= new ArrayList<String>();		//商品名
	ArrayList<String> Searchlot 			= new ArrayList<String>();		//ロット
	ArrayList<String> SearchExpDateMin 		= new ArrayList<String>();		//消費期限最小
	ArrayList<String> SearchExpDateMax 		= new ArrayList<String>();		//消費期限最大
	ArrayList<Integer> SearchPlanQtyMin 	= new ArrayList<Integer>();		//予定数量最小
	ArrayList<Integer> SearchPlanQtyMax 	= new ArrayList<Integer>();		//予定数量最大
	ArrayList<Integer> SearchActualQtyMin 	= new ArrayList<Integer>();		//実績数最小
	ArrayList<Integer> SearchActualQtyMax 	= new ArrayList<Integer>();		//実績数最大
	ArrayList<String> SearchActualDateMin 	= new ArrayList<String>();		//入荷日最小
	ArrayList<String> SearchActualDateMax 	= new ArrayList<String>();		//入荷日最大
	ArrayList<String> SearchCom 			= new ArrayList<String>();		//コメント
	ArrayList<String> SearchEntryDateMin 	= new ArrayList<String>();		//登録日最小
	ArrayList<String> SearchEntryDateMax 	= new ArrayList<String>();		//登録日最大
	ArrayList<String> SearchUpdateDateMin 	= new ArrayList<String>();		//更新日最小
	ArrayList<String> SearchUpdateDateMax 	= new ArrayList<String>();		//更新日最大
	ArrayList<String> SearchEntryUser 		= new ArrayList<String>();		//登録者
	ArrayList<String> SearchUpdateUser 		= new ArrayList<String>();		//更新者
	boolean AllSearch = false;
	
	Object[][] ArrivalPlanHdRt = T100_ArrivalPlanHdRt.ArrivalPlanHdRt(
			SearchClWh,				//ヘッダ担当倉庫
			SearchClCd,				//ヘッダ荷主CD
			SearchCLName01,			//ヘッダ荷主名
			SearchClGpCD,			//ヘッダ荷主グループCD
			SearchCLGpName01,		//ヘッダ荷主グループ標記名
			SearchArrNo,			//ヘッダ入荷予定NO
			SearchClArrNo,			//ヘッダ荷主予定番号
			SearchPlanDateMin,		//ヘッダ入荷予定日最小
			SearchPlanDateMax,		//ヘッダ入荷予定日最大
			SearchHdActualDateMin,	//ヘッダ入荷実績日最小
			SearchHdActualDateMax,	//ヘッダ入荷実績日最大
			SearchSpCd,				//ヘッダ仕入先CD
			SearchSpName,			//ヘッダ仕入先名
			SearchSpPost,			//ヘッダ仕入先郵便
			SearchSpAdd,			//ヘッダ仕入先住所
			SearchSpTel,			//ヘッダ仕入先電話
			SearchArCom,			//ヘッダコメント
			SearchFixFg,			//ヘッダ状況
					
			SearchMsNoMin,			//明細番号最小
			SearchMsNoMax,			//明細番号最大
			SearchItemCd,			//商品コード
			SearchClItemCd,			//荷主商品コード
			SearchJanCd,			//JANCD（バラ）
			SearchItemMdNo,			//商品型番
			SearchItemName,			//商品名
			Searchlot,				//ロット
			SearchExpDateMin,		//消費期限最小
			SearchExpDateMax,		//消費期限最大
			SearchPlanQtyMin,		//予定数量最小
			SearchPlanQtyMax,		//予定数量最大
			SearchActualQtyMin,		//実績数最小
			SearchActualQtyMax,		//実績数最大
			SearchActualDateMin,	//入荷日最小
			SearchActualDateMax,	//入荷日最大
			SearchCom,				//コメント
			SearchEntryDateMin,		//登録日最小
			SearchEntryDateMax,		//登録日最大
			SearchUpdateDateMin,	//更新日最小
			SearchUpdateDateMax,	//更新日最大
			SearchEntryUser,		//登録者
			SearchUpdateUser,		//更新者
			AllSearch);
			
			String GetClWh			= (String)ArrivalPlanHdRt[i][T100_ArrivalPlanHdRt.ColClWh];			//ヘッダ担当倉庫
			String GetClCd			= (String)ArrivalPlanHdRt[i][T100_ArrivalPlanHdRt.ColClCd];			//ヘッダ荷主CD
			String GetCLName01		= (String)ArrivalPlanHdRt[i][T100_ArrivalPlanHdRt.ColCLName01];		//ヘッダ荷主名
			String GetClGpCD		= (String)ArrivalPlanHdRt[i][T100_ArrivalPlanHdRt.ColClGpCD];		//ヘッダ荷主グループCD
			String GetCLGpName01	= (String)ArrivalPlanHdRt[i][T100_ArrivalPlanHdRt.ColCLGpName01];	//ヘッダ荷主グループ標記名
			String GetArrNo			= (String)ArrivalPlanHdRt[i][T100_ArrivalPlanHdRt.ColArrNo];		//ヘッダ入荷予定NO
			String GetClArrNo		= (String)ArrivalPlanHdRt[i][T100_ArrivalPlanHdRt.ColClArrNo];		//ヘッダ荷主予定番号
			String GetPlanDate		= (String)ArrivalPlanHdRt[i][T100_ArrivalPlanHdRt.ColPlanDate];		//ヘッダ入荷予定日
			String GetHdActualDate	= (String)ArrivalPlanHdRt[i][T100_ArrivalPlanHdRt.ColActualDate];	//ヘッダ入荷実績日
			String GetSpCd			= (String)ArrivalPlanHdRt[i][T100_ArrivalPlanHdRt.ColSpCd];			//ヘッダ仕入先CD
			String GetSpName01		= (String)ArrivalPlanHdRt[i][T100_ArrivalPlanHdRt.ColSpName01];		//ヘッダ仕入先名01
			String GetSpName02		= (String)ArrivalPlanHdRt[i][T100_ArrivalPlanHdRt.ColSpName02];		//ヘッダ仕入先名02
			String GetSpName03		= (String)ArrivalPlanHdRt[i][T100_ArrivalPlanHdRt.ColSpName03];		//ヘッダ仕入先名03
			String GetSpPost		= (String)ArrivalPlanHdRt[i][T100_ArrivalPlanHdRt.ColSpPost];		//ヘッダ仕入先郵便
			String GetSpAdd01		= (String)ArrivalPlanHdRt[i][T100_ArrivalPlanHdRt.ColSpAdd01];		//ヘッダ仕入先住所01
			String GetSpAdd02		= (String)ArrivalPlanHdRt[i][T100_ArrivalPlanHdRt.ColSpAdd02];		//ヘッダ仕入先住所02
			String GetSpAdd03		= (String)ArrivalPlanHdRt[i][T100_ArrivalPlanHdRt.ColSpAdd03];		//ヘッダ仕入先住所03
			String GetSpTel			= (String)ArrivalPlanHdRt[i][T100_ArrivalPlanHdRt.ColSpTel];		//ヘッダ仕入先電話
			String GetArCom01		= (String)ArrivalPlanHdRt[i][T100_ArrivalPlanHdRt.ColArCom01];		//ヘッダコメント1
			String GetArCom02		= (String)ArrivalPlanHdRt[i][T100_ArrivalPlanHdRt.ColArCom02];		//ヘッダコメント2
			String GetArCom03		= (String)ArrivalPlanHdRt[i][T100_ArrivalPlanHdRt.ColArCom03];		//ヘッダコメント3
			String GetHdEntryDate	= (String)ArrivalPlanHdRt[i][T100_ArrivalPlanHdRt.ColEntryDate];	//ヘッダ登録日
			String GetHdUpdateDate	= (String)ArrivalPlanHdRt[i][T100_ArrivalPlanHdRt.ColUpdateDate];	//ヘッダ更新日
			String GetHdEntryUser	= (String)ArrivalPlanHdRt[i][T100_ArrivalPlanHdRt.ColEntryUser];	//ヘッダ登録者
			String GetHdUpdateUser	= (String)ArrivalPlanHdRt[i][T100_ArrivalPlanHdRt.ColUpdateUser];	//ヘッダ更新者
			int GetFixFg			= (int)ArrivalPlanHdRt[i][T100_ArrivalPlanHdRt.ColFixFg];			//ヘッダ状況
			int GetPlanQty			= (int)ArrivalPlanHdRt[i][T100_ArrivalPlanHdRt.ColPlanQty];			//予定数合計
			int GetActualQty		= (int)ArrivalPlanHdRt[i][T100_ArrivalPlanHdRt.ColActualQty];		//実績数合計
	*/
	
	//入荷予定明細（各行にヘッダ情報展開）返却する
	static final int ColClWh			=  0;		//ヘッダ担当倉庫
	static final int ColCLName01		=  1;		//ヘッダ荷主名
	static final int ColCLGpName01	=  2;		//ヘッダ荷主グループ標記名
	static final int ColSpName01		=  3;		//ヘッダ仕入先名01
	static final int ColArrNo			=  4;		//ヘッダ入荷予定NO（WMS採番）
	static final int ColClArrNo		=  5;		//ヘッダ荷主予定番号
	static final int ColPlanDate		=  6;		//ヘッダ入荷予定日
	static final int ColActualDate	=  7;		//ヘッダ入荷実績日
	static final int ColPlanQty		=  8;		//実績数合計
	static final int ColActualQty		=  9;		//予定数合計
	static final int ColMsCount		= 10;		//明細行数
	static final int ColSpCd			= 11;		//ヘッダ仕入先CD
	static final int ColSpName02		= 12;		//ヘッダ仕入先名02
	static final int ColSpName03		= 13;		//ヘッダ仕入先名03
	static final int ColSpPost			= 14;		//ヘッダ仕入先郵便
	static final int ColSpAdd01		= 15;		//ヘッダ仕入先住所01
	static final int ColSpAdd02		= 16;		//ヘッダ仕入先住所02
	static final int ColSpAdd03		= 17;		//ヘッダ仕入先住所03
	static final int ColSpTel			= 18;		//ヘッダ仕入先電話
	static final int ColArCom01		= 19;		//ヘッダコメント1
	static final int ColArCom02		= 20;		//ヘッダコメント2
	static final int ColArCom03		= 21;		//ヘッダコメント3
	static final int ColClCd			= 22;		//ヘッダ荷主CD
	static final int ColClGpCD			= 23;		//ヘッダ荷主グループCD
	static final int ColEntryDate		= 24;		//ヘッダ登録日
	static final int ColUpdateDate	= 25;		//ヘッダ更新日
	static final int ColEntryUser		= 26;		//ヘッダ登録者
	static final int ColUpdateUser	= 27;		//ヘッダ更新者
	static final int ColFixFg			= 28;		//ヘッダ状況　完了:1 未完了:0　分納待ち:2  キャンセル:9
	
	//検索値カラム
	static final int ColSearchClWh				=  0;	//担当倉庫
	static final int ColSearchClCd				=  1;	//荷主CD
	static final int ColSearchCLName01			=  2;	//荷主名
	static final int ColSearchClGpCD				=  3;	//荷主グループCD
	static final int ColSearchCLGpName01			=  4;	//荷主グループ標記名
	static final int ColSearchArrNo				=  5;	//入荷予定NO
	static final int ColSearchClArrNo				=  6;	//荷主予定番号
	static final int ColSearchPlanDateMin		=  7;	//入荷予定日開始
	static final int ColSearchPlanDateMax		=  8;	//入荷予定日終了
	static final int ColSearchHdActualDateMin	=  9;	//入荷実績日開始
	static final int ColSearchHdActualDateMax	= 10;	//入荷実績日終了
	static final int ColSearchSpCd				= 11;	//仕入先CD
	static final int ColSearchSpName				= 12;	//仕入先名
	static final int ColSearchSpPost				= 13;	//仕入先郵便
	static final int ColSearchSpAdd				= 14;	//仕入先住所
	static final int ColSearchSpTel				= 15;	//仕入先電話
	static final int ColSearchArCom				= 16;	//コメント
	static final int ColSearchFixFg				= 17;	//状況
			
	static final int ColSearchMsNoMin				= 18;	//明細番号最小
	static final int ColSearchMsNoMax				= 19;	//明細番号最大
	static final int ColSearchItemCd				= 20;	//商品コード
	static final int ColSearchClItemCd			= 21;	//荷主商品コード
	static final int ColSearchJanCd				= 22;	//JANCD（バラ）
	static final int ColSearchItemMdNo			= 23;	//商品型番
	static final int ColSearchItemName			= 24;	//商品名
	static final int ColSearchlot					= 25;	//ロット
	static final int ColSearchExpDateMin			= 26;	//消費期限開始
	static final int ColSearchExpDateMax			= 27;	//消費期限終了
	static final int ColSearchPlanQtyMin			= 28;	//予定数量最小
	static final int ColSearchPlanQtyMax			= 29;	//予定数量最大
	static final int ColSearchActualQtyMin		= 30;	//実績数最小
	static final int ColSearchActualQtyMax		= 31;	//実績数最大
	static final int ColSearchActualDateMin		= 32;	//入荷日開始
	static final int ColSearchActualDateMax		= 33;	//入荷日終了
	static final int ColSearchCom					= 34;	//コメント
	static final int ColSearchEntryDateMin		= 35;	//登録日開始
	static final int ColSearchEntryDateMax		= 36;	//登録日終了
	static final int ColSearchUpdateDateMin		= 37;	//更新日開始
	static final int ColSearchUpdateDateMax		= 38;	//更新日終了
	static final int ColSearchEntryUser			= 39;	//登録者
	static final int ColSearchUpdateUser			= 40;	//更新者
	
	
	public static Object[][] RtArrivalPlanHdRt(
			){
		Object[][] RtArrivalPlanHdRt = {
				 {"ClWh"			,ColClWh			,"String"	,"ヘッダ担当倉庫"}
				,{"ClCd"			,ColClCd			,"String"	,"ヘッダ荷主CD"}
				,{"CLName01"		,ColCLName01		,"String"	,"ヘッダ荷主名"}
				,{"ClGpCD"			,ColClGpCD			,"String"	,"ヘッダ荷主グループCD"}
				,{"CLGpName01"		,ColCLGpName01	,"String"	,"ヘッダ荷主グループ標記名"}
				,{"ArrNo"			,ColArrNo			,"String"	,"ヘッダ入荷予定NO"}
				,{"ClArrNo"			,ColClArrNo		,"String"	,"ヘッダ荷主予定番号"}
				,{"PlanDate"		,ColPlanDate		,"Date"		,"ヘッダ入荷予定日"}
				,{"ActualDate"		,ColActualDate	,"DateTime"	,"ヘッダ入荷実績日"}
				,{"SpCd"			,ColSpCd			,"String"	,"ヘッダ仕入先CD"}
				,{"SpName01"		,ColSpName01		,"String"	,"ヘッダ仕入先名01"}
				,{"SpName02"		,ColSpName02		,"String"	,"ヘッダ仕入先名02"}
				,{"SpName03"		,ColSpName03		,"String"	,"ヘッダ仕入先名03"}
				,{"SpPost"			,ColSpPost			,"String"	,"ヘッダ仕入先郵便"}
				,{"SpAdd01"			,ColSpAdd01		,"String"	,"ヘッダ仕入先住所01"}
				,{"SpAdd02"			,ColSpAdd02		,"String"	,"ヘッダ仕入先住所02"}
				,{"SpAdd03"			,ColSpAdd03		,"String"	,"ヘッダ仕入先住所03"}
				,{"SpTel"			,ColSpTel			,"String"	,"ヘッダ仕入先電話"}
				,{"ArCom01"			,ColArCom01		,"String"	,"ヘッダコメント1"}
				,{"ArCom02"			,ColArCom02		,"String"	,"ヘッダコメント2"}
				,{"ArCom03"			,ColArCom03		,"String"	,"ヘッダコメント3"}
				,{"EntryDate"		,ColEntryDate		,"DateTime"	,"ヘッダ登録日"}
				,{"UpdateDate"		,ColUpdateDate	,"DateTime"	,"ヘッダ更新日"}
				,{"EntryUser"		,ColEntryUser		,"String"	,"ヘッダ登録者"}
				,{"UpdateUser"		,ColUpdateUser	,"String"	,"ヘッダ更新者"}
				,{"FixFg"			,ColFixFg			,"int"		,"ヘッダ状況"}
				,{"PlanQty"			,ColPlanQty		,"int"		,"予定数合計"}
				,{"ActualQty"		,ColActualQty		,"int"		,"実績数合計"}
				,{"MsCount"			,ColMsCount		,"int"		,"明細行数"}
				};
		
		RtArrivalPlanHdRt = B100_LanguageControl.DefinitionControl(RtArrivalPlanHdRt);
		
		return RtArrivalPlanHdRt;
	}

	public static Object[][] DefinitionRt(){
		Object[][] Definition = {
					 {"String"		,null	,"Exact"		,ColSearchClWh				,B100_DefaultVariable.SearchWhList					,"担当倉庫"				,""}
					,{"String"		,null	,"Exact"		,ColSearchClCd				,B100_DefaultVariable.SearchClList					,"荷主CD"				,""}
					,{"String"		,null	,"Partial"		,ColSearchCLName01			,""														,"荷主名"				,""}
					,{"String"		,null	,"Exact"		,ColSearchClGpCD				,B100_DefaultVariable.SearchClGpList					,"荷主グループCD"		,""}
					,{"String"		,null	,"Partial"		,ColSearchCLGpName01			,""														,"荷主グループ標記名"	,""}
					,{"String"		,null	,"Exact"		,ColSearchArrNo				,""														,"入荷予定NO"			,""}
					,{"String"		,null	,"Exact"		,ColSearchClArrNo				,""														,"荷主予定番号"			,""}
					,{"Date"		,null	,"RangeStr"		,ColSearchPlanDateMin		,""														,"入荷予定日"			,"開始"}
					,{"Date"		,null	,"RangeEnd"		,ColSearchPlanDateMax		,""														,"入荷予定日"			,"終了"}
					,{"Date"		,null	,"RangeStr"		,ColSearchHdActualDateMin	,""														,"入荷実績日"			,"開始"}
					,{"Date"		,null	,"RangeEnd"		,ColSearchHdActualDateMax	,""														,"入荷実績日"			,"終了"}
					,{"String"		,null	,"Exact"		,ColSearchSpCd				,B100_DefaultVariable.SearchSupplierList				,"仕入先CD"				,""}
					,{"String"		,null	,"Partial"		,ColSearchSpName				,""														,"仕入先名"				,""}
					,{"String"		,null	,"Prefix"		,ColSearchSpPost				,""														,"仕入先郵便"			,""}
					,{"String"		,null	,"Partial"		,ColSearchSpAdd				,""														,"仕入先住所"			,""}
					,{"String"		,null	,"Partial"		,ColSearchSpTel				,""														,"仕入先電話"			,""}
					,{"String"		,null	,"Partial"		,ColSearchArCom				,""														,"コメント"				,""}
					,{"Integer"		,null	,"Exact"		,ColSearchFixFg				,B100_DefaultVariable.SearchArryvalFixFgList			,"状況"					,""}
							
					,{"Integer"		,null	,"Exact"		,ColSearchMsNoMin				,""														,"明細番号"				,"最小"}
					,{"Integer"		,null	,"Exact"		,ColSearchMsNoMax				,""														,"明細番号"				,"最大"}
					,{"String"		,null	,"Exact"		,ColSearchItemCd				,""														,"商品コード"			,""}
					,{"String"		,null	,"Exact"		,ColSearchClItemCd			,""														,"荷主商品コード"		,""}
					,{"String"		,null	,"Exact"		,ColSearchJanCd				,""														,"JANCD（バラ）"		,""}
					,{"String"		,null	,"Exact"		,ColSearchItemMdNo			,""														,"商品型番"				,""}
					,{"String"		,null	,"Partial"		,ColSearchItemName			,""														,"商品名"				,""}
					,{"String"		,null	,"Exact"		,ColSearchlot					,""														,"ロット"				,""}
					,{"Date"		,null	,"RangeStr"		,ColSearchExpDateMin			,""														,"消費期限"				,"開始"}
					,{"Date"		,null	,"RangeEnd"		,ColSearchExpDateMax			,""														,"消費期限"				,"終了"}
					,{"Integer"		,null	,"RangeMin"		,ColSearchPlanQtyMin			,""														,"予定数量"				,"最小"}
					,{"Integer"		,null	,"RangeMax"		,ColSearchPlanQtyMax			,""														,"予定数量"				,"最大"}
					,{"Integer"		,null	,"RangeMin"		,ColSearchActualQtyMin		,""														,"実績数"				,"最小"}
					,{"Integer"		,null	,"RangeMax"		,ColSearchActualQtyMax		,""														,"実績数"				,"最大"}
					,{"Date"		,null	,"RangeStr"		,ColSearchActualDateMin		,""														,"入荷日"				,"開始"}
					,{"Date"		,null	,"RangeEnd"		,ColSearchActualDateMax		,""														,"入荷日"				,"終了"}
					,{"String"		,null	,"Partial"		,ColSearchCom					,""														,"コメント"				,""}
					,{"DateTime"	,null	,"RangeStr"		,ColSearchEntryDateMin		,""														,"登録日"				,"開始"}
					,{"DateTime"	,null	,"RangeEnd"		,ColSearchEntryDateMax		,""														,"登録日"				,"終了"}
					,{"DateTime"	,null	,"RangeStr"		,ColSearchUpdateDateMin		,""														,"更新日"				,"開始"}
					,{"DateTime"	,null	,"RangeEnd"		,ColSearchUpdateDateMax		,""														,"更新日"				,"終了"}
					,{"String"		,null	,"Partial"		,ColSearchEntryUser			,""														,"登録者"				,""}
					,{"String"		,null	,"Partial"		,ColSearchUpdateUser			,""														,"更新者"				,""}
					};		
		
		Definition = B100_LanguageControl.DefinitionControl(Definition);
		
		return Definition;
	}
	
	public static Object[][] ArrivalPlanHdRt(
			ArrayList<String> SearchClWh,				//担当倉庫
			ArrayList<String> SearchClCd,				//荷主CD
			ArrayList<String> SearchCLName01,			//荷主名
			ArrayList<String> SearchClGpCD,				//荷主グループCD
			ArrayList<String> SearchCLGpName01,			//荷主グループ標記名
			ArrayList<String> SearchArrNo,				//入荷予定NO
			ArrayList<String> SearchClArrNo,			//荷主予定番号
			ArrayList<String> SearchPlanDateMin,		//入荷予定日開始
			ArrayList<String> SearchPlanDateMax,		//入荷予定日終了
			ArrayList<String> SearchHdActualDateMin,	//入荷実績日開始
			ArrayList<String> SearchHdActualDateMax,	//入荷実績日終了
			ArrayList<String> SearchSpCd,				//仕入先CD
			ArrayList<String> SearchSpName,				//仕入先名
			ArrayList<String> SearchSpPost,				//仕入先郵便
			ArrayList<String> SearchSpAdd,				//仕入先住所
			ArrayList<String> SearchSpTel,				//仕入先電話
			ArrayList<String> SearchArCom,				//コメント
			ArrayList<Integer> SearchFixFg,				//状況
					
			ArrayList<Integer> SearchMsNoMin,			//明細番号最小
			ArrayList<Integer> SearchMsNoMax,			//明細番号最大
			ArrayList<String> SearchItemCd,				//商品コード
			ArrayList<String> SearchClItemCd,			//荷主商品コード
			ArrayList<String> SearchJanCd,				//JANCD（バラ）
			ArrayList<String> SearchItemMdNo,			//商品型番
			ArrayList<String> SearchItemName,			//商品名
			ArrayList<String> Searchlot,				//ロット
			ArrayList<String> SearchExpDateMin,			//消費期限開始
			ArrayList<String> SearchExpDateMax,			//消費期限終了
			ArrayList<Integer> SearchPlanQtyMin,		//予定数量最小
			ArrayList<Integer> SearchPlanQtyMax,		//予定数量最大
			ArrayList<Integer> SearchActualQtyMin,		//実績数最小
			ArrayList<Integer> SearchActualQtyMax,		//実績数最大
			ArrayList<String> SearchActualDateMin,		//入荷日開始
			ArrayList<String> SearchActualDateMax,		//入荷日終了
			ArrayList<String> SearchCom,				//コメント
			ArrayList<String> SearchEntryDateMin,		//登録日開始
			ArrayList<String> SearchEntryDateMax,		//登録日終了
			ArrayList<String> SearchUpdateDateMin,		//更新日開始
			ArrayList<String> SearchUpdateDateMax,		//更新日終了
			ArrayList<String> SearchEntryUser,			//登録者
			ArrayList<String> SearchUpdateUser,			//更新者
			boolean AllSearch){
		
		Object[][] Definition = DefinitionRt();

		for(int i=0;i<Definition.length;i++) {
			switch((int)Definition[i][3]) {
				case ColSearchClWh:	
					Definition[i][1]	= SearchClWh;
					break;
				case ColSearchClCd:	
					Definition[i][1]	= SearchClCd;
					break;
				case ColSearchCLName01:	
					Definition[i][1]	= SearchCLName01;
					break;
				case ColSearchClGpCD:	
					Definition[i][1]	= SearchClGpCD;
					break;
				case ColSearchCLGpName01:	
					Definition[i][1]	= SearchCLGpName01;
					break;
				case ColSearchArrNo:	
					Definition[i][1]	= SearchArrNo;
					break;
				case ColSearchClArrNo:	
					Definition[i][1]	= SearchClArrNo;
					break;
				case ColSearchPlanDateMin:	
					Definition[i][1]	= SearchPlanDateMin;
					break;
				case ColSearchPlanDateMax:	
					Definition[i][1]	= SearchPlanDateMax;
					break;
				case ColSearchHdActualDateMin:	
					Definition[i][1]	= SearchHdActualDateMin;
					break;
				case ColSearchHdActualDateMax:	
					Definition[i][1]	= SearchHdActualDateMax;
					break;
				case ColSearchSpCd:	
					Definition[i][1]	= SearchSpCd;
					break;
				case ColSearchSpName:	
					Definition[i][1]	= SearchSpName;
					break;
				case ColSearchSpPost:	
					Definition[i][1]	= SearchSpPost;
					break;
				case ColSearchSpAdd:	
					Definition[i][1]	= SearchSpAdd;
					break;
				case ColSearchSpTel:	
					Definition[i][1]	= SearchSpTel;
					break;
				case ColSearchArCom:	
					Definition[i][1]	= SearchArCom;
					break;
				case ColSearchFixFg:	
					Definition[i][1]	= SearchFixFg;
					break;
				case ColSearchMsNoMin:	
					Definition[i][1]	= SearchMsNoMin;
					break;
				case ColSearchMsNoMax:	
					Definition[i][1]	= SearchMsNoMax;
					break;
				case ColSearchItemCd:	
					Definition[i][1]	= SearchItemCd;
					break;
				case ColSearchClItemCd:	
					Definition[i][1]	= SearchClItemCd;
					break;
				case ColSearchJanCd:	
					Definition[i][1]	= SearchJanCd;
					break;
				case ColSearchItemMdNo:	
					Definition[i][1]	= SearchItemMdNo;
					break;
				case ColSearchItemName:	
					Definition[i][1]	= SearchItemName;
					break;
				case ColSearchlot:	
					Definition[i][1]	= Searchlot;
					break;
				case ColSearchExpDateMin:	
					Definition[i][1]	= SearchExpDateMin;
					break;
				case ColSearchExpDateMax:	
					Definition[i][1]	= SearchExpDateMax;
					break;
				case ColSearchPlanQtyMin:	
					Definition[i][1]	= SearchPlanQtyMin;
					break;
				case ColSearchPlanQtyMax:	
					Definition[i][1]	= SearchPlanQtyMax;
					break;
				case ColSearchActualQtyMin:	
					Definition[i][1]	= SearchActualQtyMin;
					break;
				case ColSearchActualQtyMax:	
					Definition[i][1]	= SearchActualQtyMax;
					break;
				case ColSearchActualDateMin:	
					Definition[i][1]	= SearchActualDateMin;
					break;
				case ColSearchActualDateMax:	
					Definition[i][1]	= SearchActualDateMax;
					break;
				case ColSearchCom:	
					Definition[i][1]	= SearchCom;
					break;
				case ColSearchEntryDateMin:	
					Definition[i][1]	= SearchEntryDateMin;
					break;
				case ColSearchEntryDateMax:	
					Definition[i][1]	= SearchEntryDateMax;
					break;
				case ColSearchUpdateDateMin:	
					Definition[i][1]	= SearchUpdateDateMin;
					break;
				case ColSearchUpdateDateMax:	
					Definition[i][1]	= SearchUpdateDateMax;
					break;
				case ColSearchEntryUser:	
					Definition[i][1]	= SearchEntryUser;
					break;
				case ColSearchUpdateUser:	
					Definition[i][1]	= SearchUpdateUser;
					break;
				default:
					break;
			}
		}
		
		/*
		日付系検索最小は念のため00:00:00扱い
		日付系検索項目最大は一日進めて00:00:00扱い
		検索条件の重複除去
		*/
		Definition	= B100_ArraySearchControl.SearchDefinitionControl(Definition);
		
		for(int i=0;i<Definition.length;i++) {
			switch((int)Definition[i][3]) {
				case ColSearchClWh:	
					SearchClWh					= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchClCd:	
					SearchClCd					= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchCLName01:	
					SearchCLName01				= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchClGpCD:	
					SearchClGpCD				= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchCLGpName01:	
					SearchCLGpName01			= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchArrNo:	
					SearchArrNo					= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchClArrNo:	
					SearchClArrNo				= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchPlanDateMin:	
					SearchPlanDateMin			= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchPlanDateMax:	
					SearchPlanDateMax			= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchHdActualDateMin:	
					SearchHdActualDateMin		= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchHdActualDateMax:	
					SearchHdActualDateMax		= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchSpCd:	
					SearchSpCd					= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchSpName:	
					SearchSpName				= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchSpPost:	
					SearchSpPost				= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchSpAdd:	
					SearchSpAdd					= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchSpTel:	
					SearchSpTel					= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchArCom:	
					SearchArCom					= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchFixFg:	
					SearchFixFg					= (ArrayList<Integer>)Definition[i][1];
					break;
				case ColSearchMsNoMin:	
					SearchMsNoMin				= (ArrayList<Integer>)Definition[i][1];
					break;
				case ColSearchMsNoMax:	
					SearchMsNoMax				= (ArrayList<Integer>)Definition[i][1];
					break;
				case ColSearchItemCd:	
					SearchItemCd				= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchClItemCd:	
					SearchClItemCd				= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchJanCd:	
					SearchJanCd					= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchItemMdNo:	
					SearchItemMdNo				= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchItemName:	
					SearchItemName				= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchlot:	
					Searchlot					= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchExpDateMin:	
					SearchExpDateMin			= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchExpDateMax:	
					SearchExpDateMax			= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchPlanQtyMin:	
					SearchPlanQtyMin			= (ArrayList<Integer>)Definition[i][1];
					break;
				case ColSearchPlanQtyMax:	
					SearchPlanQtyMax			= (ArrayList<Integer>)Definition[i][1];
					break;
				case ColSearchActualQtyMin:	
					SearchActualQtyMin			= (ArrayList<Integer>)Definition[i][1];
					break;
				case ColSearchActualQtyMax:	
					SearchActualQtyMax			= (ArrayList<Integer>)Definition[i][1];
					break;
				case ColSearchActualDateMin:	
					SearchActualDateMin			= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchActualDateMax:	
					SearchActualDateMax			= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchCom:	
					SearchCom					= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchEntryDateMin:	
					SearchEntryDateMin			= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchEntryDateMax:	
					SearchEntryDateMax			= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchUpdateDateMin:	
					SearchUpdateDateMin			= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchUpdateDateMax:	
					SearchUpdateDateMax			= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchEntryUser:	
					SearchEntryUser				= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchUpdateUser:	
					SearchUpdateUser			= (ArrayList<String>)Definition[i][1];
					break;
				default:
					break;
			}
		}
		
		Object[][] Rt	= ArrivalPlanHdRtMain(
				SearchClWh,				//担当倉庫
				SearchClCd,				//荷主CD
				SearchCLName01,			//荷主名
				SearchClGpCD,			//荷主グループCD
				SearchCLGpName01,		//荷主グループ標記名
				SearchArrNo,			//入荷予定NO
				SearchClArrNo,			//荷主予定番号
				SearchPlanDateMin,		//入荷予定日開始
				SearchPlanDateMax,		//入荷予定日終了
				SearchHdActualDateMin,	//入荷実績日開始
				SearchHdActualDateMax,	//入荷実績日終了
				SearchSpCd,				//仕入先CD
				SearchSpName,			//仕入先名
				SearchSpPost,			//仕入先郵便
				SearchSpAdd,			//仕入先住所
				SearchSpTel,			//仕入先電話
				SearchArCom,			//コメント
				SearchFixFg,			//状況
						
				SearchMsNoMin,			//明細番号最小
				SearchMsNoMax,			//明細番号最大
				SearchItemCd,			//商品コード
				SearchClItemCd,			//荷主商品コード
				SearchJanCd,			//JANCD（バラ）
				SearchItemMdNo,			//商品型番
				SearchItemName,			//商品名
				Searchlot,				//ロット
				SearchExpDateMin,		//消費期限開始
				SearchExpDateMax,		//消費期限終了
				SearchPlanQtyMin,		//予定数量開始
				SearchPlanQtyMax,		//予定数量終了
				SearchActualQtyMin,		//実績数最小
				SearchActualQtyMax,		//実績数最大
				SearchActualDateMin,	//入荷日最小
				SearchActualDateMax,	//入荷日最大
				SearchCom,				//コメント
				SearchEntryDateMin,		//登録日開始
				SearchEntryDateMax,		//登録日終了
				SearchUpdateDateMin,	//更新日開始
				SearchUpdateDateMax,	//更新日終了
				SearchEntryUser,		//登録者
				SearchUpdateUser,		//更新者
				AllSearch);
		
		return Rt;
	}
	
	private static Object[][] ArrivalPlanHdRtMain(
			ArrayList<String> SearchClWh,				//担当倉庫
			ArrayList<String> SearchClCd,				//荷主CD
			ArrayList<String> SearchCLName01,			//荷主名
			ArrayList<String> SearchClGpCD,				//荷主グループCD
			ArrayList<String> SearchCLGpName01,			//荷主グループ標記名
			ArrayList<String> SearchArrNo,				//入荷予定NO
			ArrayList<String> SearchClArrNo,			//荷主予定番号
			ArrayList<String> SearchPlanDateMin,		//入荷予定日開始
			ArrayList<String> SearchPlanDateMax,		//入荷予定日終了
			ArrayList<String> SearchHdActualDateMin,	//入荷実績日開始
			ArrayList<String> SearchHdActualDateMax,	//入荷実績日終了
			ArrayList<String> SearchSpCd,				//仕入先CD
			ArrayList<String> SearchSpName,				//仕入先名
			ArrayList<String> SearchSpPost,				//仕入先郵便
			ArrayList<String> SearchSpAdd,				//仕入先住所
			ArrayList<String> SearchSpTel,				//仕入先電話
			ArrayList<String> SearchArCom,				//コメント
			ArrayList<Integer> SearchFixFg,				//状況
					
			ArrayList<Integer> SearchMsNoMin,			//明細番号最小
			ArrayList<Integer> SearchMsNoMax,			//明細番号最大
			ArrayList<String> SearchItemCd,				//商品コード
			ArrayList<String> SearchClItemCd,			//荷主商品コード
			ArrayList<String> SearchJanCd,				//JANCD（バラ）
			ArrayList<String> SearchItemMdNo,			//商品型番
			ArrayList<String> SearchItemName,			//商品名
			ArrayList<String> Searchlot,				//ロット
			ArrayList<String> SearchExpDateMin,			//消費期限開始
			ArrayList<String> SearchExpDateMax,			//消費期限終了
			ArrayList<Integer> SearchPlanQtyMin,		//予定数量最小
			ArrayList<Integer> SearchPlanQtyMax,		//予定数量最大
			ArrayList<Integer> SearchActualQtyMin,		//実績数最小
			ArrayList<Integer> SearchActualQtyMax,		//実績数最大
			ArrayList<String> SearchActualDateMin,		//入荷日開始
			ArrayList<String> SearchActualDateMax,		//入荷日終了
			ArrayList<String> SearchCom,				//コメント
			ArrayList<String> SearchEntryDateMin,		//登録日開始
			ArrayList<String> SearchEntryDateMax,		//登録日終了
			ArrayList<String> SearchUpdateDateMin,		//更新日開始
			ArrayList<String> SearchUpdateDateMax,		//更新日終了
			ArrayList<String> SearchEntryUser,			//登録者
			ArrayList<String> SearchUpdateUser,			//更新者
			boolean AllSearch){
		
		//商品変換マスタを元に荷主商品コードを商品コードに変換する
		Object[][] SearchItemCdFromClItem	= SearchItemCdFromClItem(SearchClGpCD,SearchClCd,SearchClItemCd);
		
		Object[][] rt = new Object[0][RtArrivalPlanHdRt().length];
		boolean SearchKick = false;
		if(AllSearch) {SearchKick = true;}
		
		String sql = "select "
					+"(WW0010ArrivalPlanHd.ClWh)           as ClWh,\n"				//ヘッダ担当倉庫
					+"(WW0010ArrivalPlanHd.ClCd)           as ClCd,\n"				//ヘッダ荷主CD
					+"max(KM0030_CLIENTMST.CLName01)       as CLName01,\n"			//ヘッダ荷主名
					+"max(KM0030_CLIENTMST.ClGpCD)         as ClGpCD,\n"			//ヘッダ荷主グループCD
					+"max(KM0031_CLIENT_GROUP.CLGpName01)  as CLGpName01,\n"		//ヘッダ荷主グループ標記名
					+"(WW0010ArrivalPlanHd.ArrNo)          as ArrNo,\n"				//ヘッダ入荷予定NO（WMS採番）
					+"max(WW0010ArrivalPlanHd.ClArrNo)     as ClArrNo,\n"			//ヘッダ荷主予定番号
					+"(WW0010ArrivalPlanHd.PlanDate)       as PlanDate,\n"			//ヘッダ入荷予定日
					+"max(WW0010ArrivalPlanHd.ActualDate)  as ActualDate,\n"		//ヘッダ入荷実績日
					+"(WW0010ArrivalPlanHd.SpCd)           as SpCd,\n"				//ヘッダ仕入先CD
					+"max(WW0010ArrivalPlanHd.SpName01)    as SpName01,\n"			//ヘッダ仕入先名01
					+"max(WW0010ArrivalPlanHd.SpName02)    as SpName02,\n"			//ヘッダ仕入先名02
					+"max(WW0010ArrivalPlanHd.SpName03)    as SpName03,\n"			//ヘッダ仕入先名03
					+"max(WW0010ArrivalPlanHd.SpPost)      as SpPost,\n"			//ヘッダ仕入先郵便
					+"max(WW0010ArrivalPlanHd.SpAdd01)     as SpAdd01,\n"			//ヘッダ仕入先住所01
					+"max(WW0010ArrivalPlanHd.SpAdd02)     as SpAdd02,\n"			//ヘッダ仕入先住所02
					+"max(WW0010ArrivalPlanHd.SpAdd03)     as SpAdd03,\n"			//ヘッダ仕入先住所03
					+"max(WW0010ArrivalPlanHd.SpTel)       as SpTel,\n"				//ヘッダ仕入先電話
					+"max(WW0010ArrivalPlanHd.ArCom01)     as ArCom01,\n"			//ヘッダコメント1
					+"max(WW0010ArrivalPlanHd.ArCom02)     as ArCom02,\n"			//ヘッダコメント2
					+"max(WW0010ArrivalPlanHd.ArCom03)     as ArCom03,\n"			//ヘッダコメント3
					+"max(WW0010ArrivalPlanHd.EntryDate)   as EntryDate,\n"			//ヘッダ登録日
					+"max(WW0010ArrivalPlanHd.UpdateDate)  as UpdateDate,\n"		//ヘッダ更新日
					+"max(WW0010ArrivalPlanHd.EntryUser)   as EntryUser,\n"			//ヘッダ登録者
					+"max(WW0010ArrivalPlanHd.UpdateUser)  as UpdateUser,\n"		//ヘッダ更新者
					+"max(WW0010ArrivalPlanHd.FixFg)       as FixFg,\n"				//ヘッダ状況　完了:1 未完了:0　分納待ち:2  キャンセル:9
					+"sum(WW0011ArrivalPlanMs.PlanQty)     as PlanQty,\n"			//予定数合計
					+"sum(WW0011ArrivalPlanMs.ActualQty)   as ActualQty,\n"			//実績数合計
					+"count(WW0011ArrivalPlanMs.ArrNo)     as MsCount"				//明細行数
					+" from "+A00000_Main.MySqlDefaultSchemaWANKO+".WW0011ArrivalPlanMs \n"
					+" left outer join "+A00000_Main.MySqlDefaultSchemaWANKO+".WW0010ArrivalPlanHd \n"
					+" on(WW0011ArrivalPlanMs.ClWh = WW0010ArrivalPlanHd.ClWh"
					+" and WW0011ArrivalPlanMs.ClCd = WW0010ArrivalPlanHd.ClCd"
					+" and WW0011ArrivalPlanMs.ArrNo = WW0010ArrivalPlanHd.ArrNo"
					+ ")\n"
					+ " left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0030_CLIENTMST"
					+ " on("
					+ " WW0011ArrivalPlanMs.ClCd = KM0030_CLIENTMST.cl_cd"
					+ ")\n"
					+ " left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP"
					+ " on("
					+ " KM0030_CLIENTMST.ClGpCd = KM0031_CLIENT_GROUP.ClGpCD"
					+ ")\n"
					+ " left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0060_ITEMMST"
					+ " on("
					+ " KM0030_CLIENTMST.ClGpCD = KM0060_ITEMMST.ClGpCd"
					+ " and WW0011ArrivalPlanMs.ItemCd = KM0060_ITEMMST.ItemCd"
					+ ")\n"
					+ " left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0061_ITEMMSTSUB \n"
					+ " on("
					+ " KM0060_ITEMMST.ClGpCd = KM0061_ITEMMSTSUB.ClGpCd"
					+ " and KM0060_ITEMMST.ItemCd = KM0061_ITEMMSTSUB.ItemCd"
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
		if(null!=SearchCLGpName01 && 0<SearchCLGpName01.size()){			//ヘッダ荷主グループ標記名
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
				sql = sql + " KM0060_ITEMMST.ClItemCd = ?";
			}
			if(null!=SearchItemCdFromClItem && 0< SearchItemCdFromClItem.length) {
				for(int i=0;i<SearchItemCdFromClItem.length;i++) {
					sql = sql + " or (WW0010ArrivalPlanHd.ClCd = ?";
					sql = sql + "  and WW0011ArrivalPlanMs.ItemCd = ?)";
				}
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
		
		sql = sql + " group by WW0010ArrivalPlanHd.ClWh,WW0010ArrivalPlanHd.ClCd,WW0010ArrivalPlanHd.PlanDate,WW0010ArrivalPlanHd.SpCd,WW0010ArrivalPlanHd.ArrNo";
		sql = sql + " order by WW0010ArrivalPlanHd.ClWh,WW0010ArrivalPlanHd.ClCd,WW0010ArrivalPlanHd.PlanDate,WW0010ArrivalPlanHd.SpCd,WW0010ArrivalPlanHd.ArrNo";
		//System.out.println(sql);
		if(SearchKick) {
			A100_DbConnect.DB_CONN("NYANKO");
			ResultSet rset01 = null;
			PreparedStatement stmt01 = null;
			try {
				stmt01 = A100_DbConnect.conn.prepareStatement(sql);
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
				if(null!=SearchCLGpName01 && 0<SearchCLGpName01.size()){			//ヘッダ荷主グループ標記名
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
					if(null!=SearchItemCdFromClItem && 0< SearchItemCdFromClItem.length) {
						for(int i=0;i<SearchItemCdFromClItem.length;i++) {
							StmtCount = StmtCount+1;
							stmt01.setString(StmtCount, ""+SearchItemCdFromClItem[i][M100_ItemComversionMstRt.ColClCd]+"");
							StmtCount = StmtCount+1;
							stmt01.setString(StmtCount, ""+SearchItemCdFromClItem[i][M100_ItemComversionMstRt.ColItemCd]+"");
						}
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
				
				rt = B100_RtObjectCreate.B100_RtObjectCreate(rset01,RtArrivalPlanHdRt());
				
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