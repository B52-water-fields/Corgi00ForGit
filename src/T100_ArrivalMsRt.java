import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class T100_ArrivalMsRt{
	/*
	コピペ用
	ArrayList<String> SearchClWh			= new ArrayList<String>();		//担当倉庫
	ArrayList<String> SearchClCd			= new ArrayList<String>();		//荷主CD
	ArrayList<String> SearchClGpCD			= new ArrayList<String>();		//ヘッダ荷主グループCD
	ArrayList<String> SearchArrNo			= new ArrayList<String>();		//入荷予定NO
	ArrayList<Integer> SearchArrCountMin	= new ArrayList<Integer>();		//入荷予定枝番最小
	ArrayList<Integer> SearchArrCountMax	= new ArrayList<Integer>();		//入荷予定枝番最大
	ArrayList<String> SearchClArrNo			= new ArrayList<String>();		//荷主予定番号
	ArrayList<String> SearchPlanDateMin		= new ArrayList<String>();		//入荷予定日最小
	ArrayList<String> SearchPlanDateMax		= new ArrayList<String>();		//入荷予定日最大
	ArrayList<String> SearchActualDateMin	= new ArrayList<String>();		//入荷実績日最小
	ArrayList<String> SearchActualDateMax	= new ArrayList<String>();		//入荷実績日最大
	ArrayList<String> SearchSpCd			= new ArrayList<String>();		//仕入先CD
	ArrayList<String> SearchCom				= new ArrayList<String>();		//コメント
	ArrayList<String> SearchEntryDateMin	= new ArrayList<String>();		//登録日最小
	ArrayList<String> SearchEntryDateMax	= new ArrayList<String>();		//登録日最大
	ArrayList<String> SearchUpdateDateMin	= new ArrayList<String>();		//更新日最小
	ArrayList<String> SearchUpdateDateMax	= new ArrayList<String>();		//更新日最大
	ArrayList<String> SearchEntryUser		= new ArrayList<String>();		//登録者
	ArrayList<String> SearchUpdateUser		= new ArrayList<String>();		//更新者
	
	//明細WW0013ArrivalMs由来
	ArrayList<String> SearchItemCd			= new ArrayList<String>();		//商品コード
	ArrayList<String> SearchClItemCd		= new ArrayList<String>();		//荷主商品コード
	ArrayList<String> SearchItemName		= new ArrayList<String>();		//商品名
	ArrayList<String> SearchLot				= new ArrayList<String>();		//ロット
	ArrayList<String> SearchExpDateMin		= new ArrayList<String>();		//消費期限最小
	ArrayList<String> SearchExpDateMax		= new ArrayList<String>();		//消費期限最大
	boolean AllSearch	= false;
	
	Object[][] ArrivalMsRt	= T100_ArrivalMsRt.ArrivalMsRt(
			SearchClWh,				//担当倉庫
			SearchClCd,				//荷主CD
			SearchClGpCD,			//ヘッダ荷主グループCD
			SearchArrNo,			//入荷予定NO
			SearchArrCountMin,		//入荷予定枝番最小
			SearchArrCountMax,		//入荷予定枝番最大
			SearchClArrNo,			//荷主予定番号
			SearchPlanDateMin,		//入荷予定日最小
			SearchPlanDateMax,		//入荷予定日最大
			SearchActualDateMin,	//入荷実績日最小
			SearchActualDateMax,	//入荷実績日最大
			SearchSpCd,				//仕入先CD
			SearchCom,				//コメント
			SearchEntryDateMin,		//登録日最小
			SearchEntryDateMax,		//登録日最大
			SearchUpdateDateMin,	//更新日最小
			SearchUpdateDateMax,	//更新日最大
			SearchEntryUser,		//登録者
			SearchUpdateUser,		//更新者
			
			//明細WW0013ArrivalMs由来
			SearchItemCd,			//商品コード
			SearchClItemCd,			//荷主商品コード
			SearchItemName,			//商品名
			SearchLot,				//ロット
			SearchExpDateMin,		//消費期限最小
			SearchExpDateMax,		//消費期限最大
			AllSearch);
			
	String GetClWh			= (String)ArrivalMsRt[i][T100_ArrivalMsRt.ColClWh];			//担当倉庫
	String GetWHName		= (String)ArrivalMsRt[i][T100_ArrivalMsRt.ColWHName];		//担当倉庫名
	String GetClCd			= (String)ArrivalMsRt[i][T100_ArrivalMsRt.ColClCd];			//荷主CD
	String GetCLName01		= (String)ArrivalMsRt[i][T100_ArrivalMsRt.ColCLName01];		//荷主名
	String GetClGpCD		= (String)ArrivalMsRt[i][T100_ArrivalMsRt.ColClGpCD];		//荷主グループCD
	String GetCLGpName01	= (String)ArrivalMsRt[i][T100_ArrivalMsRt.ColCLGpName01];	//荷主グループ標記名
	int GetArrNo			= (int)ArrivalMsRt[i][T100_ArrivalMsRt.ColArrNo];			//入荷予定NO
	String GetArrCount		= (String)ArrivalMsRt[i][T100_ArrivalMsRt.ColArrCount];		//入荷予定枝番
	String GetClArrNo		= (String)ArrivalMsRt[i][T100_ArrivalMsRt.ColClArrNo];		//荷主予定番号
	String GetPlanDate		= (String)ArrivalMsRt[i][T100_ArrivalMsRt.ColPlanDate];		//入荷予定日
	String GetActualDate	= (String)ArrivalMsRt[i][T100_ArrivalMsRt.ColActualDate];	//入荷実績日
	String GetSpCd			= (String)ArrivalMsRt[i][T100_ArrivalMsRt.ColSpCd];			//仕入先CD
	String GetSpName01		= (String)ArrivalMsRt[i][T100_ArrivalMsRt.ColSpName01];		//仕入先名01
	String GetSpName02		= (String)ArrivalMsRt[i][T100_ArrivalMsRt.ColSpName02];		//仕入先名02
	String GetSpName03		= (String)ArrivalMsRt[i][T100_ArrivalMsRt.ColSpName03];		//仕入先名03
	String GetSpPost		= (String)ArrivalMsRt[i][T100_ArrivalMsRt.ColSpPost];		//仕入先郵便
	String GetSpAdd01		= (String)ArrivalMsRt[i][T100_ArrivalMsRt.ColSpAdd01];		//仕入先住所01
	String GetSpAdd02		= (String)ArrivalMsRt[i][T100_ArrivalMsRt.ColSpAdd02];		//仕入先住所02
	String GetSpAdd03		= (String)ArrivalMsRt[i][T100_ArrivalMsRt.ColSpAdd03];		//仕入先住所03
	String GetSpTel			= (String)ArrivalMsRt[i][T100_ArrivalMsRt.ColSpTel];		//仕入先電話
	String GetArCom01		= (String)ArrivalMsRt[i][T100_ArrivalMsRt.ColArCom01];		//コメント1
	String GetArCom02		= (String)ArrivalMsRt[i][T100_ArrivalMsRt.ColArCom02];		//コメント2
	String GetArCom03		= (String)ArrivalMsRt[i][T100_ArrivalMsRt.ColArCom03];		//コメント3
	String GetEntryDate		= (String)ArrivalMsRt[i][T100_ArrivalMsRt.ColEntryDate];	//登録日
	String GetUpdateDate	= (String)ArrivalMsRt[i][T100_ArrivalMsRt.ColUpdateDate];	//更新日
	String GetEntryUser		= (String)ArrivalMsRt[i][T100_ArrivalMsRt.ColEntryUser];	//登録者
	String GetUpdateUser	= (String)ArrivalMsRt[i][T100_ArrivalMsRt.ColUpdateUser];	//更新者
	
	//明細WW0013ArrivalMs由来
	int GetMsNo				= (int)ArrivalMsRt[i][T100_ArrivalMsRt.ColMsNo];			//明細番号
	int GetMsSeq			= (int)ArrivalMsRt[i][T100_ArrivalMsRt.ColMsSeq];			//明細Seq番号
	String GetItemCd		= (String)ArrivalMsRt[i][T100_ArrivalMsRt.ColItemCd];		//商品コード
	String GetClItemCd		= (String)ArrivalMsRt[i][T100_ArrivalMsRt.ColClItemCd];		//荷主商品コード
	String GetJanCd			= (String)ArrivalMsRt[i][T100_ArrivalMsRt.ColJanCd];		//JanCd(バラ)
	String GetItemMdNo		= (String)ArrivalMsRt[i][T100_ArrivalMsRt.ColItemMdNo];		//商品型番
	String GetItemName		= (String)ArrivalMsRt[i][T100_ArrivalMsRt.ColItemName];		//商品名
	String GetLot			= (String)ArrivalMsRt[i][T100_ArrivalMsRt.ColLot];			//ロット
	String GetExpDate		= (String)ArrivalMsRt[i][T100_ArrivalMsRt.ColExpDate];		//消費期限
	int GetPlanQty			= (int)ArrivalMsRt[i][T100_ArrivalMsRt.ColPlanQty];			//予定数量
	int GetActualQty		= (int)ArrivalMsRt[i][T100_ArrivalMsRt.ColActualQty];		//実績数
	String GetMsActualDate	= (String)ArrivalMsRt[i][T100_ArrivalMsRt.ColMsActualDate];	//入荷日
	String GetCom01			= (String)ArrivalMsRt[i][T100_ArrivalMsRt.ColCom01];		//コメント1
	String GetCom02			= (String)ArrivalMsRt[i][T100_ArrivalMsRt.ColCom02];		//コメント2
	String GetMsEntryDate	= (String)ArrivalMsRt[i][T100_ArrivalMsRt.ColMsEntryDate];	//登録日
	String GetMsUpdateDate	= (String)ArrivalMsRt[i][T100_ArrivalMsRt.ColMsUpdateDate];	//更新日
	String GetMsEntryUser	= (String)ArrivalMsRt[i][T100_ArrivalMsRt.ColMsEntryUser];	//登録者
	String GetMsUpdateUser	= (String)ArrivalMsRt[i][T100_ArrivalMsRt.ColMsUpdateUser];	//更新者

	*/
	//戻り値カラム
	static final int ColClWh			= 18;		//担当倉庫
	static final int ColWHName			= 19;		//担当倉庫名
	static final int ColClCd			= 20;		//荷主CD
	static final int ColCLName01		= 21;		//ヘッダ荷主名
	static final int ColClGpCD			= 22;		//ヘッダ荷主グループCD
	static final int ColCLGpName01	= 23;		//ヘッダ荷主グループ標記名
	static final int ColArrNo			= 24;		//入荷予定NO
	static final int ColArrCount		= 25;		//入荷予定枝番
	static final int ColClArrNo		= 26;		//荷主予定番号
	static final int ColPlanDate		= 27;		//入荷予定日
	static final int ColActualDate	= 28;		//入荷実績日
	static final int ColSpCd			= 29;		//仕入先CD
	static final int ColSpName01		= 30;		//仕入先名01
	static final int ColSpName02		= 31;		//仕入先名02
	static final int ColSpName03		= 32;		//仕入先名03
	static final int ColSpPost			= 33;		//仕入先郵便
	static final int ColSpAdd01		= 34;		//仕入先住所01
	static final int ColSpAdd02		= 35;		//仕入先住所02
	static final int ColSpAdd03		= 36;		//仕入先住所03
	static final int ColSpTel			= 37;		//仕入先電話"}
	static final int ColArCom01		= 38;		//コメント1
	static final int ColArCom02		= 39;		//コメント2
	static final int ColArCom03		= 40;		//コメント3
	static final int ColEntryDate		= 41;		//登録日
	static final int ColUpdateDate	= 42;		//更新日
	static final int ColEntryUser		= 43;		//登録者
	static final int ColUpdateUser	= 44;		//更新者
	
	//明細WW0013ArrivalMs由来
	static final int ColMsNo			=  0;		//明細番号
	static final int ColMsSeq			=  1;		//明細Seq番号
	static final int ColItemCd			=  2;		//商品コード
	static final int ColClItemCd		=  3;		//荷主商品コード
	static final int ColJanCd			=  4;		//JanCd(バラ)
	static final int ColItemMdNo		=  5;		//商品型番
	static final int ColItemName		=  6;		//商品名
	static final int ColLot			=  7;		//ロット
	static final int ColExpDate		=  8;		//消費期限
	static final int ColPlanQty		=  9;		//予定数量
	static final int ColActualQty		= 10;		//実績数
	static final int ColMsActualDate	= 11;		//入荷日
	static final int ColCom01			= 12;		//コメント1
	static final int ColCom02			= 13;		//コメント2
	static final int ColMsEntryDate	= 14;		//登録日
	static final int ColMsUpdateDate	= 15;		//更新日
	static final int ColMsEntryUser	= 16;		//登録者
	static final int ColMsUpdateUser	= 17;		//更新者
	
	//検索値カラム
	static final int ColSearchClWh			=  0;	//担当倉庫
	static final int ColSearchClCd			=  1;	//荷主CD
	static final int ColSearchClGpCD			=  2;	//ヘッダ荷主グループCD
	static final int ColSearchArrNo			=  3;	//入荷予定NO
	static final int ColSearchArrCountMin	=  4;	//入荷予定枝番最小
	static final int ColSearchArrCountMax	=  5;	//入荷予定枝番最大
	static final int ColSearchClArrNo			=  6;	//荷主予定番号
	static final int ColSearchPlanDateMin	=  7;	//入荷予定日最小
	static final int ColSearchPlanDateMax	=  8;	//入荷予定日最大
	static final int ColSearchActualDateMin	=  9;	//入荷実績日最小
	static final int ColSearchActualDateMax	= 10;	//入荷実績日最大
	static final int ColSearchSpCd			= 11;	//仕入先CD
	static final int ColSearchCom				= 12;	//コメント
	static final int ColSearchEntryDateMin	= 13;	//登録日最小
	static final int ColSearchEntryDateMax	= 14;	//登録日最大
	static final int ColSearchUpdateDateMin	= 15;	//更新日最小
	static final int ColSearchUpdateDateMax	= 16;	//更新日最大
	static final int ColSearchEntryUser		= 17;	//登録者
	static final int ColSearchUpdateUser		= 18;	//更新者
	
	//明細WW0013ArrivalMs由来
	static final int ColSearchItemCd			= 19;	//商品コード
	static final int ColSearchClItemCd		= 20;	//荷主商品コード
	static final int ColSearchItemName		= 21;	//商品名
	static final int ColSearchLot				= 22;	//ロット
	static final int ColSearchExpDateMin		= 23;	//消費期限最小
	static final int ColSearchExpDateMax		= 24;	//消費期限最大
	
	public static Object[][] RtArrivalMsRt(){
		Object[][] Rt = {
					 {"ClWh"			,ColClWh			,"String"	,"担当倉庫"				,"Key"	,"Assigned Warehouse"			,"负责仓库"		,"Kho phụ trách"}
					,{"WHName"			,ColWHName			,"String"	,"担当倉庫名"			,""		,"Warehouse Name"				,"仓库名称"		,"Tên kho"}
					,{"ClCd"			,ColClCd			,"String"	,"荷主CD"				,"Key"	,"Client Code"					,"货主代码"		,"Mã chủ hàng"}
					,{"CLName01"		,ColCLName01		,"String"	,"荷主名"				,""		,"Client Name"					,"货主名称"		,"Tên chủ hàng"}
					,{"ClGpCD"			,ColClGpCD			,"String"	,"荷主グループCD"		,""		,"Client Group Code"			,"货主组代码"		,"Mã nhóm chủ hàng"}
					,{"CLGpName01"		,ColCLGpName01	,"String"	,"荷主グループ標記名"	,""		,"Client Group Display Name"	,"货主组显示名称"	,"Tên hiển thị nhóm chủ hàng"}
					,{"ArrNo"			,ColArrNo			,"String"	,"入荷予定NO"			,"Key"	,"Receipt Plan No."				,"入库计划编号"	,"Số kế hoạch nhập kho"}
					,{"ArrCount"		,ColArrCount		,"int"		,"入荷予定枝番"			,"Key"	,"Receipt Plan Branch No."		,"入库计划分支编号"	,"Số nhánh kế hoạch nhập kho"}
					,{"ClArrNo"			,ColClArrNo		,"String"	,"荷主予定番号"			,""		,"Client Plan No."				,"货主计划编号"	,"Số kế hoạch chủ hàng"}
					,{"PlanDate"		,ColPlanDate		,"Date"		,"入荷予定日"			,""		,"Planned Receipt Date"			,"计划入库日期"	,"Ngày nhập kho dự kiến"}
					,{"ActualDate"		,ColActualDate	,"Date"		,"入荷実績日"			,""		,"Receipt Date"					,"入库日期"		,"Ngày nhập kho"}
					,{"SpCd"			,ColSpCd			,"String"	,"仕入先CD"				,""		,"Supplier Code"				,"供应商代码"		,"Mã nhà cung cấp"}
					,{"SpName01"		,ColSpName01		,"String"	,"仕入先名01"			,""		,"Supplier Name 01"				,"供应商名称01"	,"Tên nhà cung cấp 01"}
					,{"SpName02"		,ColSpName02		,"String"	,"仕入先名02"			,""		,"Supplier Name 02"				,"供应商名称02"	,"Tên nhà cung cấp 02"}
					,{"SpName03"		,ColSpName03		,"String"	,"仕入先名03"			,""		,"Supplier Name 03"				,"供应商名称03"	,"Tên nhà cung cấp 03"}
					,{"SpPost"			,ColSpPost			,"String"	,"仕入先郵便"			,""		,"Supplier Postal Code"			,"供应商邮政编码"	,"Mã bưu chính nhà cung cấp"}
					,{"SpAdd01"			,ColSpAdd01		,"String"	,"仕入先住所01"			,""		,"Supplier Address 01"			,"供应商地址01"	,"Địa chỉ nhà cung cấp 01"}
					,{"SpAdd02"			,ColSpAdd02		,"String"	,"仕入先住所02"			,""		,"Supplier Address 02"			,"供应商地址02"	,"Địa chỉ nhà cung cấp 02"}
					,{"SpAdd03"			,ColSpAdd03		,"String"	,"仕入先住所03"			,""		,"Supplier Address 03"			,"供应商地址03"	,"Địa chỉ nhà cung cấp 03"}
					,{"SpTel"			,ColSpTel			,"String"	,"仕入先電話"			,""		,"Supplier TEL"					,"供应商电话"		,"Điện thoại nhà cung cấp"}
					,{"ArCom01"			,ColArCom01		,"String"	,"コメント1"			,""		,"Comment 1"					,"备注1"			,"Ghi chú 1"}
					,{"ArCom02"			,ColArCom02		,"String"	,"コメント2"			,""		,"Comment 2"					,"备注2"			,"Ghi chú 2"}
					,{"ArCom03"			,ColArCom03		,"String"	,"コメント3"			,""		,"Comment 3"					,"备注3"			,"Ghi chú 3"}
					,{"EntryDate"		,ColEntryDate		,"DateTime"	,"登録日"				,""		,"Created At"					,"登记时间"		,"Ngày giờ tạo"}
					,{"UpdateDate"		,ColUpdateDate	,"DateTime"	,"更新日"				,""		,"Updated At"					,"更新时间"		,"Ngày giờ cập nhật"}
					,{"EntryUser"		,ColEntryUser		,"String"	,"登録者"				,""		,"Created By"					,"登记人"			,"Người tạo"}
					,{"UpdateUser"		,ColUpdateUser	,"String"	,"更新者"				,""		,"Updated By"					,"更新人"		,"Người cập nhật"}
					
					//明細WW0013ArrivalMs由来
					,{"MsNo"			,ColMsNo			,"int"		,"明細番号"				,"Key"	,"Detail No."					,"明细编号"		,"Số chi tiết"}
					,{"MsSeq"			,ColMsSeq			,"int"		,"明細Seq番号"			,"Key"	,"Detail Seq No."				,"明细序号"		,"Số Seq chi tiết"}
					,{"ItemCd"			,ColItemCd			,"String"	,"商品コード"			,""		,"Item Code"					,"商品代码"		,"Mã hàng"}
					,{"ClItemCd"		,ColClItemCd		,"String"	,"荷主商品コード"		,""		,"Client Item Code"				,"货主商品代码"	,"Mã hàng chủ hàng"}
					,{"JanCd"			,ColJanCd			,"String"	,"JanCd(バラ)"			,""		,"Each JAN Code"				,"单品JAN代码"	,"Mã JAN hàng lẻ"}
					,{"ItemMdNo"		,ColItemMdNo		,"String"	,"商品型番"				,""		,"Item Model No."				,"商品型号"		,"Mã model"}
					,{"ItemName"		,ColItemName		,"String"	,"商品名"				,""		,"Item Name"					,"商品名称"		,"Tên hàng"}
					,{"Lot"				,ColLot			,"String"	,"ロット"				,""		,"Lot"							,"批次"			,"Lô"}
					,{"ExpDate"			,ColExpDate		,"Date"		,"消費期限"				,""		,"Expiration Date"				,"有效期"		,"Hạn sử dụng"}
					,{"PlanQty"			,ColPlanQty		,"int"		,"予定数量"				,""		,"Planned Qty"					,"计划数量"		,"SL dự kiến"}
					,{"ActualQty"		,ColActualQty		,"int"		,"実績数"				,""		,"Actual Qty"					,"实际数量"		,"SL thực tế"}
					,{"MsActualDate"	,ColMsActualDate	,"Date"		,"入荷日"				,""		,"Receipt Date"					,"入库日期"		,"Ngày nhập kho"}
					,{"Com01"			,ColCom01			,"String"	,"コメント1"			,""		,"Comment 1"					,"备注1"			,"Ghi chú 1"}
					,{"Com02"			,ColCom02			,"String"	,"コメント2"			,""		,"Comment 2"					,"备注2"			,"Ghi chú 2"}
					,{"MsEntryDate"		,ColMsEntryDate	,"Datetime"	,"登録日"				,""		,"Created At"					,"登记时间"		,"Ngày giờ tạo"}
					,{"MsUpdateDate"	,ColMsUpdateDate	,"DateTime"	,"更新日"				,""		,"Updated At"					,"更新时间"		,"Ngày giờ cập nhật"}
					,{"MsEntryUser"		,ColMsEntryUser	,"String"	,"登録者"				,""		,"Created By"					,"登记人"			,"Người tạo"}
					,{"MsUpdateUser"	,ColMsUpdateUser	,"String"	,"更新者"				,""		,"Updated By"					,"更新人"		,"Người cập nhật"}
					};
		
		Rt = B100_LanguageControl.RtControl(Rt);
		
		return Rt;
	}

	public static Object[][] DefinitionRt(){
		Object[][] Definition = {
					 {"String"		,null	,"Exact"		,ColSearchClWh				,B100_DefaultVariable.SearchWhList			,"担当倉庫"			,""		,"Assigned Warehouse"		,""		,"负责仓库"		,""		,"Kho phụ trách"				,""}
					,{"String"		,null	,"Exact"		,ColSearchClCd				,B100_DefaultVariable.SearchClList			,"荷主CD"			,""		,"Client Code"				,""		,"货主代码"		,""		,"Mã chủ hàng"					,""}
					,{"String"		,null	,"Exact"		,ColSearchClGpCD				,B100_DefaultVariable.SearchClGpList			,"荷主グループCD"	,""		,"Client Group Code"		,""		,"货主组代码"		,""		,"Mã nhóm chủ hàng"				,""}
					,{"String"		,null	,"Exact"		,ColSearchArrNo				,""												,"入荷予定NO"		,""		,"Receipt Plan No."			,""		,"入库计划编号"	,""		,"Số kế hoạch nhập kho"			,""}
					,{"Integer"		,null	,"RangeMin"		,ColSearchArrCountMin		,""												,"入荷予定枝番"		,"最小"	,"Receipt Plan Branch No."	,"Min"	,"入库计划分支编号"	,"最小"	,"Số nhánh kế hoạch nhập kho"	,"Tối thiểu"}
					,{"Integer"		,null	,"RangeMax"		,ColSearchArrCountMax		,""												,"入荷予定枝番"		,"最大"	,"Receipt Plan Branch No."	,"Max"	,"入库计划分支编号"	,"最大"	,"Số nhánh kế hoạch nhập kho"	,"Tối đa"}
					,{"String"		,null	,"Exact"		,ColSearchClArrNo				,""												,"荷主予定番号"		,""		,"Client Plan No."			,""		,"货主计划编号"	,""		,"Số kế hoạch chủ hàng"			,""}
					,{"Date"		,null	,"RangeStr"		,ColSearchPlanDateMin		,""												,"入荷予定日"		,"開始"	,"Planned Receipt Date"		,"From"	,"计划入库日期"	,"开始"	,"Ngày nhập kho dự kiến"		,"Từ"}
					,{"Date"		,null	,"RangeEnd"		,ColSearchPlanDateMax		,""												,"入荷予定日"		,"終了"	,"Planned Receipt Date"		,"To"	,"计划入库日期"	,"结束"	,"Ngày nhập kho dự kiến"		,"Đến"}
					,{"Date"		,null	,"RangeStr"		,ColSearchActualDateMin		,""												,"入荷実績日"		,"開始"	,"Receipt Date"				,"From"	,"入库日期"		,"开始"	,"Ngày nhập kho"				,"Từ"}
					,{"Date"		,null	,"RangeEnd"		,ColSearchActualDateMax		,""												,"入荷実績日"		,"終了"	,"Receipt Date"				,"To"	,"入库日期"		,"结束"	,"Ngày nhập kho"				,"Đến"}
					,{"String"		,null	,"Exact"		,ColSearchSpCd				,B100_DefaultVariable.SearchSupplierList		,"仕入先CD"			,""		,"Supplier Code"			,""		,"供应商代码"		,""		,"Mã nhà cung cấp"				,""}
					,{"String"		,null	,"Partial"		,ColSearchCom					,""												,"コメント"			,""		,"Comment"					,""		,"备注"			,""		,"Ghi chú"						,""}
					,{"DateTime"	,null	,"RangeStr"		,ColSearchEntryDateMin		,""												,"登録日"			,"開始"	,"Created At"				,"From"	,"登记时间"		,"开始"	,"Ngày giờ tạo"					,"Từ"}
					,{"DateTime"	,null	,"RangeEnd"		,ColSearchEntryDateMax		,""												,"登録日"			,"終了"	,"Created At"				,"To"	,"登记时间"		,"结束"	,"Ngày giờ tạo"					,"Đến"}
					,{"DateTime"	,null	,"RangeStr"		,ColSearchUpdateDateMin		,""												,"更新日"			,"開始"	,"Updated At"				,"From"	,"更新时间"		,"开始"	,"Ngày giờ cập nhật"			,"Từ"}
					,{"DateTime"	,null	,"RangeEnd"		,ColSearchUpdateDateMax		,""												,"更新日"			,"終了"	,"Updated At"				,"To"	,"更新时间"		,"结束"	,"Ngày giờ cập nhật"			,"Đến"}
					,{"String"		,null	,"Partial"		,ColSearchEntryUser			,""												,"登録者"			,""		,"Created By"				,""		,"登记人"			,""		,"Người tạo"					,""}
					,{"String"		,null	,"Partial"		,ColSearchUpdateUser			,""												,"更新者"			,""		,"Updated By"				,""		,"更新人"		,""		,"Người cập nhật"				,""}
					
					//明細WW0013ArrivalMs由来
					,{"String"		,null	,"Exact"		,ColSearchItemCd				,""												,"商品コード"		,""		,"Item Code"				,""		,"商品代码"		,""		,"Mã hàng"						,""}
					,{"String"		,null	,"Exact"		,ColSearchClItemCd			,""												,"荷主商品コード"	,""		,"Client Item Code"			,""		,"货主商品代码"	,""		,"Mã hàng chủ hàng"				,""}
					,{"String"		,null	,"Partial"		,ColSearchItemName			,""												,"商品名"			,""		,"Item Name"				,""		,"商品名称"		,""		,"Tên hàng"						,""}
					,{"String"		,null	,"Exact"		,ColSearchLot					,""												,"ロット"			,""		,"Lot"						,""		,"批次"			,""		,"Lô"							,""}
					,{"Date"		,null	,"RangeStr"		,ColSearchExpDateMin			,""												,"消費期限"			,"開始"	,"Expiration Date"			,"From"	,"有效期"		,"开始"	,"Hạn sử dụng"					,"Từ"}
					,{"Date"		,null	,"RangeEnd"		,ColSearchExpDateMax			,""												,"消費期限"			,"終了"	,"Expiration Date"			,"To"	,"有效期"		,"结束"	,"Hạn sử dụng"					,"Đến"}
					};		
		
		Definition = B100_LanguageControl.DefinitionControl(Definition);
		
		return Definition;
	}
	
	public static Object[][] ArrivalMsRt(
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

		Object[][] Definition = DefinitionRt();

		for(int i=0;i<Definition.length;i++) {
			switch((int)Definition[i][3]) {
				case ColSearchClWh:	
					Definition[i][1]	= SearchClWh;
					break;
				case ColSearchClCd:	
					Definition[i][1]	= SearchClCd;
					break;
				case ColSearchClGpCD:	
					Definition[i][1]	= SearchClGpCD;
					break;
				case ColSearchArrNo:	
					Definition[i][1]	= SearchArrNo;
					break;
				case ColSearchArrCountMin:	
					Definition[i][1]	= SearchArrCountMin;
					break;
				case ColSearchArrCountMax:	
					Definition[i][1]	= SearchArrCountMax;
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
				case ColSearchActualDateMin:	
					Definition[i][1]	= SearchActualDateMin;
					break;
				case ColSearchActualDateMax:	
					Definition[i][1]	= SearchActualDateMax;
					break;
				case ColSearchSpCd:	
					Definition[i][1]	= SearchSpCd;
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
				case ColSearchItemCd:	
					Definition[i][1]	= SearchItemCd;
					break;
				case ColSearchClItemCd:	
					Definition[i][1]	= SearchClItemCd;
					break;
				case ColSearchItemName:	
					Definition[i][1]	= SearchItemName;
					break;
				case ColSearchLot:	
					Definition[i][1]	= SearchLot;
					break;
				case ColSearchExpDateMin:	
					Definition[i][1]	= SearchExpDateMin;
					break;
				case ColSearchExpDateMax:	
					Definition[i][1]	= SearchExpDateMax;
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
				case ColSearchClGpCD:	
					SearchClGpCD				= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchArrNo:	
					SearchArrNo					= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchArrCountMin:	
					SearchArrCountMin			= (ArrayList<Integer>)Definition[i][1];
					break;
				case ColSearchArrCountMax:	
					SearchArrCountMax			= (ArrayList<Integer>)Definition[i][1];
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
				case ColSearchActualDateMin:	
					SearchActualDateMin			= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchActualDateMax:	
					SearchActualDateMax			= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchSpCd:	
					SearchSpCd					= (ArrayList<String>)Definition[i][1];
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
				case ColSearchItemCd:	
					SearchItemCd				= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchClItemCd:	
					SearchClItemCd				= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchItemName:	
					SearchItemName				= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchLot:	
					SearchLot					= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchExpDateMin:	
					SearchExpDateMin			= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchExpDateMax:	
					SearchExpDateMax			= (ArrayList<String>)Definition[i][1];
					break;
				default:
					break;
			}
		}
		
		Object[][] Rt	= ArrivalMsRtMain(
				SearchClWh,				//担当倉庫
				SearchClCd,				//荷主CD
				SearchClGpCD,			//ヘッダ荷主グループCD
				SearchArrNo,			//入荷予定NO
				SearchArrCountMin,		//入荷予定枝番最小
				SearchArrCountMax,		//入荷予定枝番最大
				SearchClArrNo,			//荷主予定番号
				SearchPlanDateMin,		//入荷予定日最小
				SearchPlanDateMax,		//入荷予定日最大
				SearchActualDateMin,	//入荷実績日最小
				SearchActualDateMax,	//入荷実績日最大
				SearchSpCd,				//仕入先CD
				SearchCom,				//コメント
				SearchEntryDateMin,		//登録日最小
				SearchEntryDateMax,		//登録日最大
				SearchUpdateDateMin,	//更新日最小
				SearchUpdateDateMax,	//更新日最大
				SearchEntryUser,		//登録者
				SearchUpdateUser,		//更新者
				
				//明細WW0013ArrivalMs由来
				SearchItemCd,			//商品コード
				SearchClItemCd,			//荷主商品コード
				SearchItemName,			//商品名
				SearchLot,				//ロット
				SearchExpDateMin,		//消費期限最小
				SearchExpDateMax,		//消費期限最大
				AllSearch);
		
		return Rt;
	}
	
	private static Object[][] ArrivalMsRtMain(
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
		
		//商品変換マスタを元に荷主商品コードを商品コードに変換する
		Object[][] SearchItemCdFromClItem	= SearchItemCdFromClItem(SearchClGpCD,SearchClCd,SearchClItemCd);
		
		Object[][] Rt = new Object[0][RtArrivalMsRt().length];
		boolean SearchKick = false;
		if(AllSearch) {SearchKick = true;}
		
		String sql = "select \n"
				//ヘッダWW0012ArrivalHd由来
				+"(WW0012ArrivalHd.ClWh) 			as	ClWh,\n"			//担当倉庫
				+"(KM0010_WHMST.WHName)				as	WHName,\n"			//担当倉庫名
				+"(WW0012ArrivalHd.ClCd) 			as	ClCd,\n"			//荷主CD
				+"(KM0030_CLIENTMST.CLName01)       as	CLName01,\n"		//ヘッダ荷主名
				+"(KM0030_CLIENTMST.ClGpCD)         as	ClGpCD,\n"			//ヘッダ荷主グループCD
				+"(KM0031_CLIENT_GROUP.CLGpName01)  as	CLGpName01,\n"		//ヘッダ荷主グループ標記名
				+"(WW0012ArrivalHd.ArrNo)			as	ArrNo,\n"			//入荷予定NO
				+"(WW0012ArrivalHd.ArrCount) 		as	ArrCount,\n"		//入荷予定枝番
				+"(WW0012ArrivalHd.ClArrNo) 		as	ClArrNo,\n"			//荷主予定番号
				+"(WW0012ArrivalHd.PlanDate) 		as	PlanDate,\n"		//入荷予定日
				+"(WW0012ArrivalHd.ActualDate)		as	ActualDate,\n"		//入荷実績日
				+"(WW0012ArrivalHd.SpCd)			as	SpCd,\n"			//仕入先CD
				+"(WW0012ArrivalHd.SpName01)		as	SpName01,\n"		//仕入先名01
				+"(WW0012ArrivalHd.SpName02)		as	SpName02,\n"		//仕入先名02
				+"(WW0012ArrivalHd.SpName03)		as	SpName03,\n"		//仕入先名03
				+"(WW0012ArrivalHd.SpPost)			as	SpPost,\n"			//仕入先郵便
				+"(WW0012ArrivalHd.SpAdd01)			as	SpAdd01,\n"			//仕入先住所01
				+"(WW0012ArrivalHd.SpAdd02)			as	SpAdd02,\n"			//仕入先住所02
				+"(WW0012ArrivalHd.SpAdd03)			as	SpAdd03,\n"			//仕入先住所03
				+"(WW0012ArrivalHd.SpTel)			as	SpTel,\n"			//仕入先電話"}
				+"(WW0012ArrivalHd.ArCom01)			as	ArCom01,\n"			//コメント1
				+"(WW0012ArrivalHd.ArCom02)			as	ArCom02,\n"			//コメント2
				+"(WW0012ArrivalHd.ArCom03)			as	ArCom03,\n"			//コメント3
				+"(WW0012ArrivalHd.EntryDate)		as	EntryDate,\n"		//登録日
				+"(WW0012ArrivalHd.UpdateDate)		as	UpdateDate,\n"		//更新日
				+"(WW0012ArrivalHd.EntryUser)		as	EntryUser,\n"		//登録者
				+"(WW0012ArrivalHd.UpdateUser)		as	UpdateUser,\n"		//更新者
				
				//明細WW0013ArrivalMs由来
				+"(WW0013ArrivalMs.MsNo)				as	MsNo,\n"			//明細番号
				+"(WW0013ArrivalMs.MsSeq)			as	MsSeq,\n"			//明細Seq番号
				+"(WW0013ArrivalMs.ItemCd)			as	ItemCd,\n"			//商品コード
				+"(WW0013ArrivalMs.ClItemCd)			as	ClItemCd,\n"		//荷主商品コード
				+"(WW0013ArrivalMs.JanCd)			as	JanCd,\n"			//JanCd(バラ)
				+"(WW0013ArrivalMs.ItemMdNo)			as	ItemMdNo,\n"		//商品型番
				+"(WW0013ArrivalMs.ItemName)			as	ItemName,\n"		//商品名
				+"(WW0013ArrivalMs.Lot)				as	Lot,\n"				//ロット
				+"(WW0013ArrivalMs.ExpDate)			as	ExpDate,\n"			//消費期限
				+"(WW0013ArrivalMs.PlanQty)			as	PlanQty,\n"			//予定数量
				+"(WW0013ArrivalMs.ActualQty)		as	ActualQty,\n"		//実績数
				+"(WW0013ArrivalMs.ActualDate)		as	MsActualDate,\n"	//入荷日
				+"(WW0013ArrivalMs.Com01)			as	Com01,\n"			//コメント1
				+"(WW0013ArrivalMs.Com02)			as	Com02,\n"			//コメント2
				+"(WW0013ArrivalMs.EntryDate)		as	MsEntryDate,\n"		//登録日
				+"(WW0013ArrivalMs.UpdateDate)		as	MsUpdateDate,\n"	//更新日
				+"(WW0013ArrivalMs.EntryUser)		as	MsEntryUser,\n"		//登録者
				+"(WW0013ArrivalMs.UpdateUser)		as	MsUpdateUser \n"	//更新者
				
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
		
		sql = sql + " order by WW0012ArrivalHd.ActualDate,WW0012ArrivalHd.ArrNo,WW0012ArrivalHd.ArrCount,WW0013ArrivalMs.MsNo,WW0013ArrivalMs.MsSeq; \n";
		
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
				
				Rt = B100_RtObjectCreate.B100_RtObjectCreate(rset01,RtArrivalMsRt());
				
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