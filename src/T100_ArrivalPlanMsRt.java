import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class T100_ArrivalPlanMsRt{
	//入荷予定明細（各行にヘッダ情報展開）返却する
	
	/*
	コピペ用
	ArrayList<String> SearchClWh			= new ArrayList<String>();		//ヘッダ担当倉庫
	ArrayList<String> SearchClCd			= new ArrayList<String>();		//ヘッダ荷主CD
	ArrayList<String> SearchCLName01		= new ArrayList<String>();		//ヘッダ荷主名
	ArrayList<String> SearchClGpCD			= new ArrayList<String>();		//ヘッダ荷主グループCD
	ArrayList<String> SearchCLGpName01		= new ArrayList<String>();		//ヘッダ荷主グループ名1
	ArrayList<String> SearchArrNo			= new ArrayList<String>();		//ヘッダ入荷予定NO
	ArrayList<String> SearchClArrNo			= new ArrayList<String>();		//ヘッダ荷主予定番号
	ArrayList<String> SearchPlanDateMin		= new ArrayList<String>();		//ヘッダ入荷予定日
	ArrayList<String> SearchPlanDateMax		= new ArrayList<String>();		//ヘッダ入荷予定日
	ArrayList<String> SearchHdActualDateMin	= new ArrayList<String>();		//ヘッダ入荷実績日
	ArrayList<String> SearchHdActualDateMax	= new ArrayList<String>();		//ヘッダ入荷実績日
	ArrayList<String> SearchSpCd			= new ArrayList<String>();		//ヘッダ仕入先CD
	ArrayList<String> SearchSpName			= new ArrayList<String>();		//ヘッダ仕入先名
	ArrayList<String> SearchSpPost			= new ArrayList<String>();		//ヘッダ仕入先郵便
	ArrayList<String> SearchSpAdd			= new ArrayList<String>();		//ヘッダ仕入先住所
	ArrayList<String> SearchSpTel			= new ArrayList<String>();		//ヘッダ仕入先電話
	ArrayList<String> SearchArCom			= new ArrayList<String>();		//ヘッダコメント
	ArrayList<Integer> SearchFixFg			= new ArrayList<Integer>();		//ヘッダ状況
			
	ArrayList<Integer> SearchMsNoMin		= new ArrayList<Integer>();		//明細番号最小
	ArrayList<Integer> SearchMsNoMax		= new ArrayList<Integer>();		//明細番号最大
	ArrayList<String> SearchItemCd			= new ArrayList<String>();		//商品コード
	ArrayList<String> SearchClItemCd		= new ArrayList<String>();		//荷主商品コード
	ArrayList<String> SearchJanCd			= new ArrayList<String>();		//JANCD（バラ）
	ArrayList<String> SearchItemMdNo		= new ArrayList<String>();		//商品型番
	ArrayList<String> SearchItemName		= new ArrayList<String>();		//商品名
	ArrayList<String> Searchlot				= new ArrayList<String>();		//ロット
	ArrayList<String> SearchExpDateMin		= new ArrayList<String>();		//消費期限最小
	ArrayList<String> SearchExpDateMax		= new ArrayList<String>();		//消費期限最大
	ArrayList<Integer> SearchPlanQtyMin		= new ArrayList<Integer>();		//予定数量最小
	ArrayList<Integer> SearchPlanQtyMax		= new ArrayList<Integer>();		//予定数量最大
	ArrayList<Integer> SearchActualQtyMin	= new ArrayList<Integer>();		//実績数
	ArrayList<Integer> SearchActualQtyMax	= new ArrayList<Integer>();		//実績数
	ArrayList<String> SearchActualDateMin	= new ArrayList<String>();		//入荷日
	ArrayList<String> SearchActualDateMax	= new ArrayList<String>();		//入荷日
	ArrayList<String> SearchCom				= new ArrayList<String>();		//コメント
	ArrayList<String> SearchEntryDateMin	= new ArrayList<String>();		//登録日
	ArrayList<String> SearchEntryDateMax	= new ArrayList<String>();		//登録日
	ArrayList<String> SearchUpdateDateMin	= new ArrayList<String>();		//更新日
	ArrayList<String> SearchUpdateDateMax	= new ArrayList<String>();		//更新日
	ArrayList<String> SearchEntryUser		= new ArrayList<String>();		//登録者
	ArrayList<String> SearchUpdateUser		= new ArrayList<String>();		//更新者
	boolean AllSearch = false;
	
	
	Object[][] ArrivalPlanMsRt	= T100_ArrivalPlanMsRt.ArrivalPlanMsRt(
			SearchClWh,					//ヘッダ担当倉庫
			SearchClCd,					//ヘッダ荷主CD
			SearchCLName01,				//ヘッダ荷主名
			SearchClGpCD,				//ヘッダ荷主グループCD
			SearchCLGpName01,			//ヘッダ荷主グループ名1
			SearchArrNo,				//ヘッダ入荷予定NO
			SearchClArrNo,				//ヘッダ荷主予定番号
			SearchPlanDateMin,			//ヘッダ入荷予定日
			SearchPlanDateMax,			//ヘッダ入荷予定日
			SearchHdActualDateMin,		//ヘッダ入荷実績日
			SearchHdActualDateMax,		//ヘッダ入荷実績日
			SearchSpCd,					//ヘッダ仕入先CD
			SearchSpName,				//ヘッダ仕入先名
			SearchSpPost,				//ヘッダ仕入先郵便
			SearchSpAdd,				//ヘッダ仕入先住所
			SearchSpTel,				//ヘッダ仕入先電話
			SearchArCom,				//ヘッダコメント
			SearchFixFg,				//ヘッダ状況
					
			SearchMsNoMin,				//明細番号最小
			SearchMsNoMax,				//明細番号最大
			SearchItemCd,				//商品コード
			SearchClItemCd,				//荷主商品コード
			SearchJanCd,				//JANCD（バラ）
			SearchItemMdNo,				//商品型番
			SearchItemName,				//商品名
			Searchlot,					//ロット
			SearchExpDateMin,			//消費期限最小
			SearchExpDateMax,			//消費期限最大
			SearchPlanQtyMin,			//予定数量最小
			SearchPlanQtyMax,			//予定数量最大
			SearchActualQtyMin,			//実績数
			SearchActualQtyMax,			//実績数
			SearchActualDateMin,		//入荷日
			SearchActualDateMax,		//入荷日
			SearchCom,					//コメント
			SearchEntryDateMin,			//登録日
			SearchEntryDateMax,			//登録日
			SearchUpdateDateMin,		//更新日
			SearchUpdateDateMax,		//更新日
			SearchEntryUser,			//登録者
			SearchUpdateUser,			//更新者
			AllSearch);
			
		String GetClWh			= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColClWh];			//ヘッダ担当倉庫
		String GetClCd			= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColClCd];			//ヘッダ荷主CD
		String GetCLName01		= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColCLName01];		//ヘッダ荷主名
		String GetClGpCD		= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColClGpCD];			//ヘッダ荷主グループCD
		String GetCLGpName01	= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColCLGpName01];		//ヘッダ荷主グループ名1
		String GetArrNo			= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColArrNo];			//ヘッダ入荷予定NO
		String GetClArrNo		= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColClArrNo];			//ヘッダ荷主予定番号
		String GetPlanDate		= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColPlanDate];		//ヘッダ入荷予定日
		String GetHdActualDate	= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColHdActualDate];	//ヘッダ入荷実績日
		String GetSpCd			= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColSpCd];			//ヘッダ仕入先CD
		String GetSpName01		= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColSpName01];		//ヘッダ仕入先名01
		String GetSpName02		= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColSpName02];		//ヘッダ仕入先名02
		String GetSpName03		= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColSpName03];		//ヘッダ仕入先名03
		String GetSpPost		= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColSpPost];			//ヘッダ仕入先郵便
		String GetSpAdd01		= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColSpAdd01];			//ヘッダ仕入先住所01
		String GetSpAdd02		= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColSpAdd02];			//ヘッダ仕入先住所02
		String GetSpAdd03		= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColSpAdd03];			//ヘッダ仕入先住所03
		String GetSpTel			= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColSpTel];			//ヘッダ仕入先電話
		String GetArCom01		= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColArCom01];			//ヘッダコメント1
		String GetArCom02		= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColArCom02];			//ヘッダコメント2
		String GetArCom03		= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColArCom03];			//ヘッダコメント3
		String GetHdEntryDate	= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColHdEntryDate];		//ヘッダ登録日
		String GetHdUpdateDate	= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColHdUpdateDate];	//ヘッダ更新日
		String GetHdEntryUser	= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColHdEntryUser];		//ヘッダ登録者
		String GetHdUpdateUser	= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColHdUpdateUser];	//ヘッダ更新者
		int  GetFixFg			= (int)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColFixFg];				//ヘッダ状況
					
		int GetMsNo				= (int)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColMsNo];				//明細番号
		String GetItemCd		= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColItemCd];			//商品コード
		String GetClItemCd		= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColClItemCd];		//荷主商品コード
		String GetJanCd			= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColJanCd];			//JANCD（バラ）
		String GetItemMdNo		= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColItemMdNo];		//商品型番
		String GetItemName		= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColItemName];		//商品名
		String Getlot			= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.Collot];				//ロット
		String GetExpDate		= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColExpDate];			//消費期限
		int GetPlanQty			= (int)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColPlanQty];			//予定数量
		int GetActualQty		= (int)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColActualQty];			//実績数
		String GetActualDate	= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColActualDate];		//入荷日
		String GetCom01			= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColCom01];			//コメント1
		String GetCom02			= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColCom02];			//コメント2
		String GetEntryDate		= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColEntryDate];		//登録日
		String GetUpdateDate	= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColUpdateDate];		//更新日
		String GetEntryUser		= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColEntryUser];		//登録者
		String GetUpdateUser	= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColUpdateUser];		//更新者
	
	*/
	
	static final int ColClWh			=  0;		//ヘッダ担当倉庫
	static final int ColClCd			=  1;		//ヘッダ荷主CD
	static final int ColCLName01		=  2;		//ヘッダ荷主名
	static final int ColClGpCD			=  3;		//ヘッダ荷主グループCD
	static final int ColCLGpName01	=  4;		//ヘッダ荷主グループ名1
	static final int ColArrNo			=  5;		//ヘッダ入荷予定NO（WMS採番）
	static final int ColClArrNo		=  6;		//ヘッダ荷主予定番号
	static final int ColPlanDate		=  7;		//ヘッダ入荷予定日
	static final int ColHdActualDate	=  8;		//ヘッダ入荷実績日
	static final int ColSpCd			=  9;		//ヘッダ仕入先CD
	static final int ColSpName01		= 10;		//ヘッダ仕入先名01
	static final int ColSpName02		= 11;		//ヘッダ仕入先名02
	static final int ColSpName03		= 12;		//ヘッダ仕入先名03
	static final int ColSpPost			= 13;		//ヘッダ仕入先郵便
	static final int ColSpAdd01		= 14;		//ヘッダ仕入先住所01
	static final int ColSpAdd02		= 15;		//ヘッダ仕入先住所02
	static final int ColSpAdd03		= 16;		//ヘッダ仕入先住所03
	static final int ColSpTel			= 17;		//ヘッダ仕入先電話
	static final int ColArCom01		= 18;		//ヘッダコメント1
	static final int ColArCom02		= 19;		//ヘッダコメント2
	static final int ColArCom03		= 20;		//ヘッダコメント3
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
	static final int Collot			= 32;		//ロット
	static final int ColExpDate		= 33;		//消費期限
	static final int ColPlanQty		= 34;		//予定数量
	static final int ColActualQty		= 35;		//実績数
	static final int ColActualDate	= 36;		//入荷日
	static final int ColCom01			= 37;		//コメント1
	static final int ColCom02			= 38;		//コメント2
	static final int ColEntryDate		= 39;		//登録日
	static final int ColUpdateDate	= 40;		//更新日
	static final int ColEntryUser		= 41;		//登録者
	static final int ColUpdateUser	= 42;		//更新者
	
	public static Object[][] RtArrivalPlanMsRt(
			){
		Object[][] RtArrivalPlanMsRt = {
				 {"ClWh"			,ColClWh			,"String"	,"ヘッダ担当倉庫"}
				,{"ClCd"			,ColClCd			,"String"	,"ヘッダ荷主CD"}
				,{"CLName01"		,ColCLName01		,"String"	,"ヘッダ荷主名"}
				,{"ClGpCD"			,ColClGpCD			,"String"	,"ヘッダ荷主グループCD"}
				,{"CLGpName01"		,ColCLGpName01	,"String"	,"ヘッダ荷主グループ名1"}
				,{"ArrNo"			,ColArrNo			,"String"	,"ヘッダ入荷予定NO"}
				,{"ClArrNo"			,ColClArrNo		,"String"	,"ヘッダ荷主予定番号"}
				,{"PlanDate"		,ColPlanDate		,"Date"		,"ヘッダ入荷予定日"}
				,{"HdActualDate"	,ColHdActualDate	,"DateTime"	,"ヘッダ入荷実績日"}
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
				,{"HdEntryDate"		,ColHdEntryDate	,"DateTime"	,"ヘッダ登録日"}
				,{"HdUpdateDate"	,ColHdUpdateDate	,"DateTime"	,"ヘッダ更新日"}
				,{"HdEntryUser"		,ColHdEntryUser	,"String"	,"ヘッダ登録者"}
				,{"HdUpdateUser"	,ColHdUpdateUser	,"String"	,"ヘッダ更新者"}
				,{"FixFg"			,ColFixFg			,"int"		,"ヘッダ状況"}
					
				,{"MsNo"			,ColMsNo			,"int"		,"明細番号"}
				,{"ItemCd"			,ColItemCd			,"String"	,"商品コード"}
				,{"ClItemCd"		,ColClItemCd		,"String"	,"荷主商品コード"}
				,{"JanCd"			,ColJanCd			,"String"	,"JANCD（バラ）"}
				,{"ItemMdNo"		,ColItemMdNo		,"String"	,"商品型番"}
				,{"ItemName"		,ColItemName		,"String"	,"商品名"}
				,{"lot"				,Collot			,"String"	,"ロット"}
				,{"ExpDate"			,ColExpDate		,"Date"		,"消費期限"}
				,{"PlanQty"			,ColPlanQty		,"int"		,"予定数量"}
				,{"ActualQty"		,ColActualQty		,"int"		,"実績数"}
				,{"ActualDate"		,ColActualDate	,"String"	,"入荷日"}
				,{"Com01"			,ColCom01			,"String"	,"コメント1"}
				,{"Com02"			,ColCom02			,"String"	,"コメント2"}
				,{"EntryDate"		,ColEntryDate		,"DateTime"	,"登録日"}
				,{"UpdateDate"		,ColUpdateDate	,"DateTime"	,"更新日"}
				,{"EntryUser"		,ColEntryUser		,"String"	,"登録者"}
				,{"UpdateUser"		,ColUpdateUser	,"String"	,"更新者"}
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
		SearchClWh				= B100_ArrayListControl.ArryListStringUniqueList(SearchClWh);			//ヘッダ担当倉庫
		SearchClCd				= B100_ArrayListControl.ArryListStringUniqueList(SearchClCd);			//ヘッダ荷主CD
		SearchCLName01			= B100_ArrayListControl.ArryListStringUniqueList(SearchCLName01);		//ヘッダ荷主名
		SearchClGpCD			= B100_ArrayListControl.ArryListStringUniqueList(SearchClGpCD);			//ヘッダ荷主グループCD
		SearchCLGpName01		= B100_ArrayListControl.ArryListStringUniqueList(SearchCLGpName01);		//ヘッダ荷主グループ名1
		SearchArrNo				= B100_ArrayListControl.ArryListStringUniqueList(SearchArrNo);			//ヘッダ入荷予定NO
		SearchClArrNo			= B100_ArrayListControl.ArryListStringUniqueList(SearchClArrNo);			//ヘッダ荷主予定番号
		SearchPlanDateMin		= B100_ArrayListControl.ArryListStringUniqueList(SearchPlanDateMin);		//ヘッダ入荷予定日最小
		SearchPlanDateMax		= B100_ArrayListControl.ArryListStringUniqueList(SearchPlanDateMax);		//ヘッダ入荷予定日最大
		SearchHdActualDateMin	= B100_ArrayListControl.ArryListStringUniqueList(SearchHdActualDateMin);	//ヘッダ入荷実績日最小
		SearchHdActualDateMax	= B100_ArrayListControl.ArryListStringUniqueList(SearchHdActualDateMax);	//ヘッダ入荷実績日最大
		SearchSpCd				= B100_ArrayListControl.ArryListStringUniqueList(SearchSpCd);			//ヘッダ仕入先CD
		SearchSpName			= B100_ArrayListControl.ArryListStringUniqueList(SearchSpName);			//ヘッダ仕入先名
		SearchSpPost			= B100_ArrayListControl.ArryListStringUniqueList(SearchSpPost);			//ヘッダ仕入先郵便
		SearchSpAdd				= B100_ArrayListControl.ArryListStringUniqueList(SearchSpAdd);			//ヘッダ仕入先住所
		SearchSpTel				= B100_ArrayListControl.ArryListStringUniqueList(SearchSpTel);			//ヘッダ仕入先電話
		SearchArCom				= B100_ArrayListControl.ArryListStringUniqueList(SearchArCom);			//ヘッダコメント
		SearchFixFg				= B100_ArrayListControl.ArryListIntegerUniqueList(SearchFixFg);			//ヘッダ状況
				
		SearchMsNoMin			= B100_ArrayListControl.ArryListIntegerUniqueList(SearchMsNoMin);		//明細番号最小
		SearchMsNoMax			= B100_ArrayListControl.ArryListIntegerUniqueList(SearchMsNoMax);		//明細番号最大
		SearchItemCd			= B100_ArrayListControl.ArryListStringUniqueList(SearchItemCd);			//商品コード
		SearchClItemCd			= B100_ArrayListControl.ArryListStringUniqueList(SearchClItemCd);		//荷主商品コード
		SearchJanCd				= B100_ArrayListControl.ArryListStringUniqueList(SearchJanCd);			//JANCD（バラ）
		SearchItemMdNo			= B100_ArrayListControl.ArryListStringUniqueList(SearchItemMdNo);		//商品型番
		SearchItemName			= B100_ArrayListControl.ArryListStringUniqueList(SearchItemName);		//商品名
		Searchlot				= B100_ArrayListControl.ArryListStringUniqueList(Searchlot);				//ロット
		SearchExpDateMin		= B100_ArrayListControl.ArryListStringUniqueList(SearchExpDateMin);		//消費期限最小
		SearchExpDateMax		= B100_ArrayListControl.ArryListStringUniqueList(SearchExpDateMax);		//消費期限最大
		SearchPlanQtyMin		= B100_ArrayListControl.ArryListIntegerUniqueList(SearchPlanQtyMin);		//予定数量最小
		SearchPlanQtyMax		= B100_ArrayListControl.ArryListIntegerUniqueList(SearchPlanQtyMax);		//予定数量最大
		SearchActualQtyMin		= B100_ArrayListControl.ArryListIntegerUniqueList(SearchActualQtyMin);	//実績数
		SearchActualQtyMax		= B100_ArrayListControl.ArryListIntegerUniqueList(SearchActualQtyMax);	//実績数
		SearchActualDateMin		= B100_ArrayListControl.ArryListStringUniqueList(SearchActualDateMin);	//入荷日最小
		SearchActualDateMax		= B100_ArrayListControl.ArryListStringUniqueList(SearchActualDateMax);	//入荷日最大
		SearchCom				= B100_ArrayListControl.ArryListStringUniqueList(SearchCom);				//コメント
		SearchEntryDateMin		= B100_ArrayListControl.ArryListStringUniqueList(SearchEntryDateMin);	//登録日最小
		SearchEntryDateMax		= B100_ArrayListControl.ArryListStringUniqueList(SearchEntryDateMax);	//登録日最大
		SearchUpdateDateMin		= B100_ArrayListControl.ArryListStringUniqueList(SearchUpdateDateMin);	//更新日最小
		SearchUpdateDateMax		= B100_ArrayListControl.ArryListStringUniqueList(SearchUpdateDateMax);	//更新日最大
		SearchEntryUser			= B100_ArrayListControl.ArryListStringUniqueList(SearchEntryUser);		//登録者
		SearchUpdateUser		= B100_ArrayListControl.ArryListStringUniqueList(SearchUpdateUser);		//更新者
		
		//日付系項目最大は一日進めて00:00:00扱い　※時刻まで検索条件にする場合はそのまま
		if(null!=SearchPlanDateMax && 0<SearchPlanDateMax.size()){				//ヘッダ入荷予定日最大
			for(int i=0;i<SearchPlanDateMax.size();i++){
				String SetString = B100_DateTimeControl.DateFormat(SearchPlanDateMax.get(i));
				Timestamp SetTimestamp = B100_DateTimeControl.dtmTimestamp2(SetString)[0];
				SetTimestamp = B100_DateTimeControl.ndate_after(SetTimestamp,1);
				SetString = B100_DateTimeControl.dtmString2(SetTimestamp)[0];
				SearchPlanDateMax.set(i,SetString);
			}
		}
		if(null!=SearchHdActualDateMax && 0<SearchHdActualDateMax.size()){		//ヘッダ入荷実績日最大
			for(int i=0;i<SearchHdActualDateMax.size();i++){
				String SetString = B100_DateTimeControl.DateFormat(SearchHdActualDateMax.get(i));
				Timestamp SetTimestamp = B100_DateTimeControl.dtmTimestamp2(SetString)[0];
				SetTimestamp = B100_DateTimeControl.ndate_after(SetTimestamp,1);
				SetString = B100_DateTimeControl.dtmString2(SetTimestamp)[0];
				SearchHdActualDateMax.set(i,SetString);
			}
		}
		if(null!=SearchExpDateMax && 0<SearchExpDateMax.size()){				//消費期限最大
			for(int i=0;i<SearchExpDateMax.size();i++){
				String SetString = B100_DateTimeControl.DateFormat(SearchExpDateMax.get(i));
				Timestamp SetTimestamp = B100_DateTimeControl.dtmTimestamp2(SetString)[0];
				SetTimestamp = B100_DateTimeControl.ndate_after(SetTimestamp,1);
				SetString = B100_DateTimeControl.dtmString2(SetTimestamp)[0];
				SearchExpDateMax.set(i,SetString);
			}
		}
		if(null!=SearchActualDateMax && 0<SearchActualDateMax.size()){			//入荷日最大
			for(int i=0;i<SearchActualDateMax.size();i++){
				String SetString = B100_DateTimeControl.DateFormat(SearchActualDateMax.get(i));
				Timestamp SetTimestamp = B100_DateTimeControl.dtmTimestamp2(SetString)[0];
				SetTimestamp = B100_DateTimeControl.ndate_after(SetTimestamp,1);
				SetString = B100_DateTimeControl.dtmString2(SetTimestamp)[0];
				SearchActualDateMax.set(i,SetString);
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
				
				rt = B100_RtObjectCreate.B100_RtObjectCreate(rset01,RtArrivalPlanMsRt());
				
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