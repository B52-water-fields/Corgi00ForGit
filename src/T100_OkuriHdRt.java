import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class T100_OkuriHdRt{
	//出荷明細（各行にヘッダ情報展開）返却する
	
	static final int ColClCd					=   0;	//荷主コード
	static final int ColInvoiceWhCd			=   1;	//倉庫コード
	static final int ColOkuriNo				=   2;	//送り状番号
	static final int ColClDeliNo				=   3;	//荷主管理番号
	static final int ColPickupWhCd			=   4;	//集荷倉庫CD
	static final int ColPurposeFG				=   5;	//目的フラグ
	static final int ColPlanDate				=   6;	//出荷予定日
	static final int ColShipDate				=   7;	//出荷実績日
	static final int ColSPPlanDate			=   8;	//着日指定
	static final int ColSPDate					=   9;	//着日実績
	static final int ColSPTimeFG				=  10;	//時間指定区分
	static final int ColSPTimeStr				=  11;	//時間指定開始
	static final int ColSPTimeEnd				=  12;	//時間指定終了
	static final int ColTotalWeight			=  13;	//荷物重量(kg)
	static final int ColTotalSize				=  14;	//荷物サイズ
	static final int ColTotalQty				=  15;	//個口数
	static final int ColDeliveryTypeCd		=  16;	//運送タイプ01
	static final int ColDeliTypeName			=  17;	//運送タイプ名01
	static final int ColDeliveryTypeCd02		=  18;	//運送タイプ02
	static final int ColDeliTypeName02		=  19;	//運送タイプ名02
	static final int ColDeliveryTypeCd03		=  20;	//運送タイプ03
	static final int ColDeliTypeName03		=  21;	//運送タイプ名03
	static final int ColDeliveryTypeCd04		=  22;	//運送タイプ04
	static final int ColDeliTypeName04		=  23;	//運送タイプ名04
	static final int ColDeliveryTypeCd05		=  24;	//運送タイプ05
	static final int ColDeliTypeName05		=  25;	//運送タイプ名05
	static final int ColCodFG					=  26;	//代引きフラグ
	static final int ColCodPayTotal			=  27;	//代引き収受金額合計
	static final int ColCodPay					=  28;	//代引き金額
	static final int ColCodConsumptionTax	=  29;	//代引き消費税
	static final int ColChildrenFG			=  30;	//赤黒区分
	static final int ColParentOkuriNo			=  31;	//親伝票番号
	static final int ColNiokuriCd				=  32;	//荷送り人コード
	static final int ColNiokuriDepartmentCd	=  33;	//部署CD
	static final int ColNiokuriName01			=  34;	//荷送り人名01
	static final int ColNiokuriName02			=  35;	//荷送り人名02
	static final int ColNiokuriName03			=  36;	//荷送り人名03
	static final int ColNiokuriPost			=  37;	//荷送り人郵便番号
	static final int ColNiokuriAdd01			=  38;	//荷送り人住所01
	static final int ColNiokuriAdd02			=  39;	//荷送り人住所02
	static final int ColNiokuriAdd03			=  40;	//荷送り人住所03
	static final int ColNioKuriTel			=  41;	//荷送り人TEL
	static final int ColNioKuriFax			=  42;	//荷送り人FAX
	static final int ColNioKuriMail			=  43;	//荷送り人MAIL
	static final int ColNiokuriMunicCd		=  44;	//荷送人市区町村CD
	static final int ColDeliCd					=  45;	//荷届け先コード
	static final int ColClDeliCd				=  46;	//荷主荷届け先コード
	static final int ColDeliDepartmentCd		=  47;	//部署CD
	static final int ColDeliName01			=  48;	//荷届け先名01
	static final int ColDeliName02			=  49;	//荷届け先名02
	static final int ColDeliName03			=  50;	//荷届け先名03
	static final int ColDeliPost				=  51;	//荷届け先郵便番号
	static final int ColDeliAdd01				=  52;	//荷届け先住所01
	static final int ColDeliAdd02				=  53;	//荷届け先住所02
	static final int ColDeliAdd03				=  54;	//荷届け先住所03
	static final int ColDeliTel				=  55;	//荷届け先TEL
	static final int ColDeliFax				=  56;	//荷届け先FAX
	static final int ColDeliMail				=  57;	//荷届け先MAIL
	static final int ColDeliMunicCd			=  58;	//荷届先市区町村CD
	static final int ColCom01					=  59;	//コメント01
	static final int ColCom02					=  60;	//コメント02
	static final int ColCom03					=  61;	//コメント03
	static final int ColCom04					=  62;	//コメント04
	static final int ColCom05					=  63;	//コメント05
	static final int ColStatus					=  64;	//状況
	static final int ColTaxFg					=  65;	//税区分
	static final int ColTaxRate				=  66;	//税率
	static final int ColDeliFee				=  67;	//運賃
	static final int ColAddDeliFee01			=  68;	//付帯費用1
	static final int ColAddDeliFee02			=  69;	//付帯費用2
	static final int ColAddDeliFee03			=  70;	//付帯費用3
	static final int ColHaighWayFee01			=  71;	//高速代等実費精算分1（内税）
	static final int ColHaighWayFee02			=  72;	//高速代等実費精算分2（内税）
	static final int ColConsumptionTax		=  73;	//消費税
	static final int ColWithOutTaxTotal		=  74;	//税別合計金額
	static final int ColTotalFee				=  75;	//税込請求額合計
	static final int ColFeeFixFG				=  76;	//金額確定フラグ
	static final int ColFeeFixDate			=  77;	//金額確定日時
	static final int ColReceiptStampFG		=  78;	//受領印チェック
	static final int ColReceiptStampDate		=  79;	//受領印日時
	static final int ColInvoiceStatus			=  80;	//請求ステータス
	static final int ColEntryDate				=  81;	//登録日
	static final int ColUpdateDate			=  82;	//更新日
	static final int ColEntryUser				=  83;	//登録者
	static final int ColUpdateUser			=  84;	//更新者
	static final int ColEntryPG				=  85;	//登録プログラム
	static final int ColUpdatePG				=  86;	//更新プログラム
	static final int ColUseFeeBasePtCd		=  87;	//適用運賃タリフCD
	static final int ColWmsStatus				=  88;	//在庫管理ステータス
	static final int ColWmsShipDate			=  89;	//倉庫出荷日
	static final int ColCourseGpCd			=  90;	//コースグループコード
	static final int ColCourseCD				=  91;	//一次配車コースコード
	static final int ColCourseCDEda			=  92;	//一次配車コースコード枝番
	static final int ColPitGrp					=  93;	//一次配車払出ピットグループ
	static final int ColPit01					=  94;	//一次配車払出ピット01
	static final int ColPit02					=  95;	//一次配車払出ピット02
	static final int ColPit03					=  96;	//一次配車払出ピット03
	static final int ColPit04					=  97;	//一次配車払出ピット04
	static final int ColPit05					=  98;	//一次配車払出ピット05
	
	static final int ColMsClCd					=  99;	//明細荷主コード
	static final int ColMsInvoiceWhCd			= 100;	//明細倉庫コード
	static final int ColMsOkuriNo				= 101;	//明細送り状番号
	static final int ColMsNo					= 102;	//明細番号
	static final int ColMsDeliNo				= 103;	//明細出荷番号
	static final int ColMsDelliMsNo			= 104;	//明細出荷番号明細番号
	static final int ColMsClOrderNo			= 105;	//明細荷主管理番号
	static final int ColMsClGpCd				= 106;	//明細荷主グループコード
	static final int ColMsItemCd				= 107;	//明細商品コード
	static final int ColMsItemName01			= 108;	//明細商品表記名
	static final int ColMsItemName02			= 109;	//明細商品正式名
	static final int ColMsItemName03			= 110;	//明細商品略名
	static final int ColMsUnitWeight			= 111;	//明細単位重量
	static final int ColMsUnitSize			= 112;	//明細単位サイズ
	static final int ColMsQty					= 113;	//明細個数
	static final int ColMsPackingQty			= 114;	//明細荷姿数量
	static final int ColMsUnitName			= 115;	//明細明細単位
	static final int ColMsSubTotalWeight		= 116;	//明細明細重量
	static final int ColMsSubTotalSize		= 117;	//明細明細サイズ
	static final int ColMsUnitPrice			= 118;	//明細単価
	static final int ColMsSubTotalPrice		= 119;	//明細金額
	static final int ColMsCategoryCd			= 120;	//明細商品分類
	static final int ColMsCategoryName		= 121;	//明細商品分類名
	static final int ColMsTildFG				= 122;	//明細温度区分
	static final int ColMsTildName			= 123;	//明細温度区分名
	static final int ColMsCom01				= 124;	//明細コメント01
	static final int ColMsCom02				= 125;	//明細コメント02
	static final int ColMsCom03				= 126;	//明細コメント03
	static final int ColMsCom04				= 127;	//明細コメント04
	static final int ColMsCom05				= 128;	//明細コメント05
	static final int ColMsEntryDate			= 129;	//明細登録日
	static final int ColMsUpdateDate			= 130;	//明細更新日
	static final int ColMsEntryUser			= 131;	//明細登録者
	static final int ColMsUpdateUser			= 132;	//明細更新者
	static final int ColMsLot					= 133;	//明細ロット指定
	static final int ColMsExpDate				= 134;	//明細賞味期限指定
	static final int ColMsPackingType			= 135;	//明細荷姿タイプ
	static final int ColMsClItemCd			= 136;	//明細荷主商品CD
	static final int ColMsItemMDNo			= 137;	//明細型番
	static final int ColMsJanCd				= 138;	//明細荷姿JanCd
	static final int ColCLName01				= 139;	//荷主名
	static final int ColClGpCD					= 140;	//荷主グループCD
	static final int ColCLGpName01			= 141;	//荷主グループ標記名
	
	public static Object[][] RtOkuriHdRt(){
		Object[][] Rt = {
						 {"ClCd"				,ColClCd					,"String"	,"荷主コード"					,"Key"}
						,{"InvoiceWhCd"			,ColInvoiceWhCd			,"String"	,"倉庫コード"					,"Key"}
						,{"OkuriNo"				,ColOkuriNo				,"String"	,"送り状番号"					,"Key"}
						,{"ClDeliNo"			,ColClDeliNo				,"String"	,"荷主管理番号"					,""}
						,{"PickupWhCd"			,ColPickupWhCd			,"String"	,"集荷倉庫CD"					,""}
						,{"PurposeFG"			,ColPurposeFG				,"int"		,"目的フラグ"					,""}
						,{"PlanDate"			,ColPlanDate				,"Date"		,"出荷予定日"					,""}
						,{"ShipDate"			,ColShipDate				,"DateTime"	,"出荷実績日"					,""}
						,{"SPPlanDate"			,ColSPPlanDate			,"Date"		,"着日指定"						,""}
						,{"SPDate"				,ColSPDate					,"DateTime"	,"着日実績"						,""}
						,{"SPTimeFG"			,ColSPTimeFG				,"String"	,"時間指定区分"					,""}
						,{"SPTimeStr"			,ColSPTimeStr				,"String"	,"時間指定開始"					,""}
						,{"SPTimeEnd"			,ColSPTimeEnd				,"String"	,"時間指定終了"					,""}
						,{"TotalWeight"			,ColTotalWeight			,"float"	,"荷物重量(kg)"					,""}
						,{"TotalSize"			,ColTotalSize				,"float"	,"荷物サイズ"					,""}
						,{"TotalQty"			,ColTotalQty				,"int"		,"個口数"						,""}
						,{"DeliveryTypeCd"		,ColDeliveryTypeCd		,"String"	,"運送タイプ01"					,""}
						,{"DeliTypeName"		,ColDeliTypeName			,"String"	,"運送タイプ名01"				,""}
						,{"DeliveryTypeCd02"	,ColDeliveryTypeCd02		,"String"	,"運送タイプ02"					,""}
						,{"DeliTypeName02"		,ColDeliTypeName02		,"String"	,"運送タイプ名02"				,""}
						,{"DeliveryTypeCd03"	,ColDeliveryTypeCd03		,"String"	,"運送タイプ03"					,""}
						,{"DeliTypeName03"		,ColDeliTypeName03		,"String"	,"運送タイプ名03"				,""}
						,{"DeliveryTypeCd04"	,ColDeliveryTypeCd04		,"String"	,"運送タイプ04"					,""}
						,{"DeliTypeName04"		,ColDeliTypeName04		,"String"	,"運送タイプ名04"				,""}
						,{"DeliveryTypeCd05"	,ColDeliveryTypeCd05		,"String"	,"運送タイプ05"					,""}
						,{"DeliTypeName05"		,ColDeliTypeName05		,"String"	,"運送タイプ名05"				,""}
						,{"CodFG"				,ColCodFG					,"String"	,"代引フラグ"					,""}
						,{"CodPayTotal"			,ColCodPayTotal			,"int"		,"代引収受金額合計"				,""}
						,{"CodPay"				,ColCodPay					,"int"		,"代引金額"						,""}
						,{"CodConsumptionTax"	,ColCodConsumptionTax	,"int"		,"代引消費税"					,""}
						,{"ChildrenFG"			,ColChildrenFG			,"int"		,"赤黒区分"						,""}
						,{"ParentOkuriNo"		,ColParentOkuriNo			,"String"	,"親伝票番号"					,""}
						,{"NiokuriCd"			,ColNiokuriCd				,"String"	,"荷送人コード"					,""}
						,{"NiokuriDepartmentCd"	,ColNiokuriDepartmentCd	,"String"	,"荷送人部署CD"					,""}
						,{"NiokuriName01"		,ColNiokuriName01			,"String"	,"荷送人名01"					,""}
						,{"NiokuriName02"		,ColNiokuriName02			,"String"	,"荷送人名02"					,""}
						,{"NiokuriName03"		,ColNiokuriName03			,"String"	,"荷送人名03"					,""}
						,{"NiokuriPost"			,ColNiokuriPost			,"String"	,"荷送人郵便番号"				,""}
						,{"NiokuriAdd01"		,ColNiokuriAdd01			,"String"	,"荷送人住所01"					,""}
						,{"NiokuriAdd02"		,ColNiokuriAdd02			,"String"	,"荷送人住所02"					,""}
						,{"NiokuriAdd03"		,ColNiokuriAdd03			,"String"	,"荷送人住所03"					,""}
						,{"NioKuriTel"			,ColNioKuriTel			,"String"	,"荷送人TEL"					,""}
						,{"NioKuriFax"			,ColNioKuriFax			,"String"	,"荷送人FAX"					,""}
						,{"NioKuriMail"			,ColNioKuriMail			,"String"	,"荷送人MAIL"					,""}
						,{"NiokuriMunicCd"		,ColNiokuriMunicCd		,"String"	,"荷送人市区町村CD"				,""}
						,{"DeliCd"				,ColDeliCd					,"String"	,"荷届け先コード"				,""}
						,{"ClDeliCd"			,ColClDeliCd				,"String"	,"荷主荷届け先コード"			,""}
						,{"DeliDepartmentCd"	,ColDeliDepartmentCd		,"String"	,"部署CD"						,""}
						,{"DeliName01"			,ColDeliName01			,"String"	,"荷届先名01"					,""}
						,{"DeliName02"			,ColDeliName02			,"String"	,"荷届先名02"					,""}
						,{"DeliName03"			,ColDeliName03			,"String"	,"荷届先名03"					,""}
						,{"DeliPost"			,ColDeliPost				,"String"	,"荷届先郵便番号"				,""}
						,{"DeliAdd01"			,ColDeliAdd01				,"String"	,"荷届先住所01"					,""}
						,{"DeliAdd02"			,ColDeliAdd02				,"String"	,"荷届先住所02"					,""}
						,{"DeliAdd03"			,ColDeliAdd03				,"String"	,"荷届先住所03"					,""}
						,{"DeliTel"				,ColDeliTel				,"String"	,"荷届先TEL"					,""}
						,{"DeliFax"				,ColDeliFax				,"String"	,"荷届先FAX"					,""}
						,{"DeliMail"			,ColDeliMail				,"String"	,"荷届先MAIL"					,""}
						,{"DeliMunicCd"			,ColDeliMunicCd			,"String"	,"荷届先市区町村CD"				,""}
						,{"Com01"				,ColCom01					,"String"	,"コメント01"					,""}
						,{"Com02"				,ColCom02					,"String"	,"コメント02"					,""}
						,{"Com03"				,ColCom03					,"String"	,"コメント03"					,""}
						,{"Com04"				,ColCom04					,"String"	,"コメント04"					,""}
						,{"Com05"				,ColCom05					,"String"	,"コメント05"					,""}
						,{"Status"				,ColStatus					,"int"		,"状況"							,""}
						,{"TaxFg"				,ColTaxFg					,"int"		,"税区分"						,""}
						,{"TaxRate"				,ColTaxRate				,"int"		,"税率"							,""}
						,{"DeliFee"				,ColDeliFee				,"int"		,"運賃"							,""}
						,{"AddDeliFee01"		,ColAddDeliFee01			,"int"		,"付帯費用1"					,""}
						,{"AddDeliFee02"		,ColAddDeliFee02			,"int"		,"付帯費用2"					,""}
						,{"AddDeliFee03"		,ColAddDeliFee03			,"int"		,"付帯費用3"					,""}
						,{"HaighWayFee01"		,ColHaighWayFee01			,"int"		,"高速代等実費精算分1（内税）"	,""}
						,{"HaighWayFee02"		,ColHaighWayFee02			,"int"		,"高速代等実費精算分2（内税）"	,""}
						,{"ConsumptionTax"		,ColConsumptionTax		,"int"		,"消費税"						,""}
						,{"WithOutTaxTotal"		,ColWithOutTaxTotal		,"int"		,"税別合計金額"					,""}
						,{"TotalFee"			,ColTotalFee				,"int"		,"税込請求額合計"				,""}
						,{"FeeFixFG"			,ColFeeFixFG				,"int"		,"金額確定フラグ"				,""}
						,{"FeeFixDate"			,ColFeeFixDate			,"DateTime"	,"金額確定日時"					,""}
						,{"ReceiptStampFG"		,ColReceiptStampFG		,"int"		,"受領印チェック"				,""}
						,{"ReceiptStampDate"	,ColReceiptStampDate		,"DateTime"	,"受領印日時"					,""}
						,{"InvoiceStatus"		,ColInvoiceStatus			,"int"		,"請求ステータス"				,""}
						,{"EntryDate"			,ColEntryDate				,"DateTime"	,"登録日"						,""}
						,{"UpdateDate"			,ColUpdateDate			,"DateTime"	,"更新日"						,""}
						,{"EntryUser"			,ColEntryUser				,"String"	,"登録者"						,""}
						,{"UpdateUser"			,ColUpdateUser			,"String"	,"更新者"						,""}
						,{"EntryPG"				,ColEntryPG				,"String"	,"登録プログラム"				,""}
						,{"UpdatePG"			,ColUpdatePG				,"String"	,"更新プログラム"				,""}
						,{"UseFeeBasePtCd"		,ColUseFeeBasePtCd		,"String"	,"適用運賃タリフCD"				,""}
						,{"WmsStatus"			,ColWmsStatus				,"int"		,"在庫管理ステータス"			,""}
						,{"WmsShipDate"			,ColWmsShipDate			,"DateTime"	,"倉庫出荷日"					,""}
						,{"CourseGpCd"			,ColCourseGpCd			,"String"	,"コースグループコード"			,""}
						,{"CourseCD"			,ColCourseCD				,"String"	,"一次配車コースコード"			,""}
						,{"CourseCDEda"			,ColCourseCDEda			,"int"		,"一次配車コースコード枝番"		,""}
						,{"PitGrp"				,ColPitGrp					,"String"	,"一次配車払出ピットグループ"	,""}
						,{"Pit01"				,ColPit01					,"String"	,"一次配車払出ピット01"			,""}
						,{"Pit02"				,ColPit02					,"String"	,"一次配車払出ピット02"			,""}
						,{"Pit03"				,ColPit03					,"String"	,"一次配車払出ピット03"			,""}
						,{"Pit04"				,ColPit04					,"String"	,"一次配車払出ピット04"			,""}
						,{"Pit05"				,ColPit05					,"String"	,"一次配車払出ピット05"			,""}
						
						,{"MsClCd"				,ColMsClCd					,"String"	,"明細荷主コード"				,""}
						,{"MsInvoiceWhCd"		,ColMsInvoiceWhCd			,"String"	,"明細倉庫コード"				,""}
						,{"MsOkuriNo"			,ColMsOkuriNo				,"String"	,"明細送り状番号"				,""}
						,{"MsNo"				,ColMsNo					,"int"		,"明細番号"						,"Key"}
						,{"MsDeliNo"			,ColMsDeliNo				,"String"	,"明細出荷番号"					,""}
						,{"MsDelliMsNo"			,ColMsDelliMsNo			,"int"		,"明細出荷番号明細番号"			,""}
						,{"MsClOrderNo"			,ColMsClOrderNo			,"String"	,"明細荷主管理番号"				,""}
						,{"MsClGpCd"			,ColMsClGpCd				,"String"	,"明細荷主グループコード"		,""}
						,{"MsItemCd"			,ColMsItemCd				,"String"	,"明細商品コード"				,""}
						,{"MsItemName01"		,ColMsItemName01			,"String"	,"明細商品表記名"				,""}
						,{"MsItemName02"		,ColMsItemName02			,"String"	,"明細商品正式名"				,""}
						,{"MsItemName03"		,ColMsItemName03			,"String"	,"明細商品略名"					,""}
						,{"MsUnitWeight"		,ColMsUnitWeight			,"float"	,"明細単位重量"					,""}
						,{"MsUnitSize"			,ColMsUnitSize			,"float"	,"明細単位サイズ"				,""}
						,{"MsQty"				,ColMsQty					,"int"		,"明細個数"						,""}
						,{"MsPackingQty"		,ColMsPackingQty			,"int"		,"明細荷姿数量"					,""}
						,{"MsUnitName"			,ColMsUnitName			,"String"	,"明細明細単位"					,""}
						,{"MsSubTotalWeight"	,ColMsSubTotalWeight		,"float"	,"明細明細重量"					,""}
						,{"MsSubTotalSize"		,ColMsSubTotalSize		,"float"	,"明細明細サイズ"				,""}
						,{"MsUnitPrice"			,ColMsUnitPrice			,"float"	,"明細単価"						,""}
						,{"MsSubTotalPrice"		,ColMsSubTotalPrice		,"float"	,"明細金額"						,""}
						,{"MsCategoryCd"		,ColMsCategoryCd			,"String"	,"明細商品分類"					,""}
						,{"MsCategoryName"		,ColMsCategoryName		,"String"	,"明細商品分類名"				,""}
						,{"MsTildFG"			,ColMsTildFG				,"String"	,"明細温度区分"					,""}
						,{"MsTildName"			,ColMsTildName			,"String"	,"明細温度区分名"				,""}
						,{"MsCom01"				,ColMsCom01				,"String"	,"明細コメント01"				,""}
						,{"MsCom02"				,ColMsCom02				,"String"	,"明細コメント02"				,""}
						,{"MsCom03"				,ColMsCom03				,"String"	,"明細コメント03"				,""}
						,{"MsCom04"				,ColMsCom04				,"String"	,"明細コメント04"				,""}
						,{"MsCom05"				,ColMsCom05				,"String"	,"明細コメント05"				,""}
						,{"MsEntryDate"			,ColMsEntryDate			,"DateTime"	,"明細登録日"					,""}
						,{"MsUpdateDate"		,ColMsUpdateDate			,"DateTime"	,"明細更新日"					,""}
						,{"MsEntryUser"			,ColMsEntryUser			,"String"	,"明細登録者"					,""}
						,{"MsUpdateUser"		,ColMsUpdateUser			,"String"	,"明細更新者"					,""}
						,{"MsLot"				,ColMsLot					,"String"	,"明細ロット指定"				,""}
						,{"MsExpDate"			,ColMsExpDate				,"Date"		,"明細賞味期限指定"				,""}
						,{"MsPackingType"		,ColMsPackingType			,"int"		,"明細荷姿タイプ"				,""}
						,{"MsClItemCd"			,ColMsClItemCd			,"String"	,"明細荷主商品CD"				,""}
						,{"MsItemMDNo"			,ColMsItemMDNo			,"String"	,"明細型番"						,""}
						,{"MsJanCd"				,ColMsJanCd				,"String"	,"明細荷姿JanCd"				,""}
						,{"CLName01"			,ColCLName01				,"String"	,"荷主名"						,""}
						,{"ClGpCD"				,ColClGpCD					,"String"	,"荷主グループCD"				,""}
						,{"CLGpName01"			,ColCLGpName01			,"String"	,"荷主グループ標記名"			,""}
						};
		return Rt;
	}
	
	
	
	public static Object[][] OkuriHdRt(
			ArrayList<String> SearchClGpCD,
			ArrayList<String> SearchClCd,
			ArrayList<String> SearchOkuriNo,
			ArrayList<String> SearchClDeliNo,
			ArrayList<String> SearchPickupWhCd,
			ArrayList<String> SearchPurposeFG,
			ArrayList<String> SearchPlanDateStr,
			ArrayList<String> SearchShipDateStr,
			ArrayList<String> SearchSPPlanDateStr,
			ArrayList<String> SearchSPDateStr,
			
			ArrayList<String> SearchPlanDateEnd,
			ArrayList<String> SearchShipDateEnd,
			ArrayList<String> SearchSPPlanDateEnd,
			ArrayList<String> SearchSPDateEnd,
			
			ArrayList<Float> SearchTotalWeightMin,
			ArrayList<Float> SearchTotalSizeMin,
			ArrayList<Integer> SearchTotalQtyMin,
			
			ArrayList<Float> SearchTotalWeightMax,
			ArrayList<Float> SearchTotalSizeMax,
			ArrayList<Integer> SearchTotalQtyMax,
			
			ArrayList<String> SearchDeliveryTypeCd,
			ArrayList<String> SearchDeliveryTypeCd02,
			ArrayList<String> SearchDeliveryTypeCd03,
			ArrayList<String> SearchDeliveryTypeCd04,
			ArrayList<String> SearchDeliveryTypeCd05,
			
			ArrayList<Integer> SearchCodFG,
			ArrayList<Integer> SearchCodPayTotalMin,
			ArrayList<Integer> SearchCodPayTotalMax,
			
			ArrayList<Integer> SearchChildrenFG,
			ArrayList<String> SearchParentOkuriNo,
			
			ArrayList<String> SearchNiokuriCd,
			ArrayList<String> SearchNiokuriDepartmentCd,
			ArrayList<String> SearchNiokuriName,
			ArrayList<String> SearchNiokuriPost,
			ArrayList<String> SearchNiokuriAdd,
			ArrayList<String> SearchNioKuriTel,
			ArrayList<String> SearchNioKuriFax,
			ArrayList<String> SearchNioKuriMail,
			ArrayList<String> SearchNiokuriMunicCd,
			
			ArrayList<String> SearchDeliCd,
			ArrayList<String> SearchClDeliCd,
			ArrayList<String> SearchDeliDepartmentCd,
			ArrayList<String> SearchDeliName,
			ArrayList<String> SearchDeliPost,
			ArrayList<String> SearchDeliAdd,
			ArrayList<String> SearchDeliTel,
			ArrayList<String> SearchDeliFax,
			ArrayList<String> SearchDeliMail,
			ArrayList<String> SearchDeliMunicCd,
			
			ArrayList<String> SearchCom,
			ArrayList<Integer> SearchStatus,
			
			ArrayList<Integer> SearchFeeFixFG,
			ArrayList<Integer> SearchReceiptStampFG,
			ArrayList<Integer> SearchInvoiceStatus,
			
			ArrayList<Integer> SearchWithOutTaxTotalMin,
			ArrayList<Integer> SearchTotalFeeMin,
			ArrayList<String> SearchFeeFixDateStr,
			ArrayList<String> SearchReceiptStampDateStr,
			ArrayList<String> SearchEntryDateStr,
			ArrayList<String> SearchUpdateDateStr,
			
			ArrayList<Integer> SearchWithOutTaxTotalMax,
			ArrayList<Integer> SearchTotalFeeMax,
			ArrayList<String> SearchFeeFixDateEnd,
			ArrayList<String> SearchReceiptStampDateEnd,
			ArrayList<String> SearchEntryDateEnd,
			ArrayList<String> SearchUpdateDateEnd,
			
			ArrayList<String> SearchEntryUser,
			ArrayList<String> SearchUpdateUser,
			ArrayList<String> SearchEntryPG,
			ArrayList<String> SearchUpdatePG,
			ArrayList<String> SearchUseFeeBasePtCd,
			ArrayList<Integer> SearchWmsStatus,
			ArrayList<String> SearchWmsShipDateStr,
			ArrayList<String> SearchWmsShipDateEnd,
			ArrayList<String> SearchCourseGpCd,
			ArrayList<String> SearchCourseCD,
			ArrayList<String> SearchCourseCDEda,
			ArrayList<String> SearchPitGrp,
			ArrayList<String> SearchPit,
			
			ArrayList<String> SearchMsItemCd,
			ArrayList<String> SearchMsItemName,
			
			ArrayList<String> SearchClItemCd,
			
			ArrayList<String> SearchMsCategoryCd,
			ArrayList<String> SearchMsCategoryName,
			ArrayList<String> SearchMsTildFG,
			ArrayList<String> SearchMsTildName,
			
			ArrayList<String> SearchMsLot,
			ArrayList<String> SearchMsExpDateStr,
			ArrayList<String> SearchMsExpDateEnd,
			ArrayList<String> SearchMsPackingType,
			
			boolean AllSearch){
		
		//日付系最小は念のため00:00:00扱い
		if(null!=SearchPlanDateStr && 0<SearchPlanDateStr.size()){
			for(int i=0;i<SearchPlanDateStr.size();i++){
				String SetString = B100_DateTimeControl.DateFormat(SearchPlanDateStr.get(i));
				Timestamp SetTimestamp = B100_DateTimeControl.dtmTimestamp2(SetString)[0];
				SetString = B100_DateTimeControl.dtmString2(SetTimestamp)[0];
				SearchPlanDateStr.set(i,SetString);
			}
		}
		
		if(null!=SearchShipDateStr && 0<SearchShipDateStr.size()){
			for(int i=0;i<SearchShipDateStr.size();i++){
				String SetString = B100_DateTimeControl.DateFormat(SearchShipDateStr.get(i));
				Timestamp SetTimestamp = B100_DateTimeControl.dtmTimestamp2(SetString)[0];
				SetString = B100_DateTimeControl.dtmString2(SetTimestamp)[0];
				SearchShipDateStr.set(i,SetString);
			}
		}
		
		if(null!=SearchSPPlanDateStr && 0<SearchSPPlanDateStr.size()){
			for(int i=0;i<SearchSPPlanDateStr.size();i++){
				String SetString = B100_DateTimeControl.DateFormat(SearchSPPlanDateStr.get(i));
				Timestamp SetTimestamp = B100_DateTimeControl.dtmTimestamp2(SetString)[0];
				SetString = B100_DateTimeControl.dtmString2(SetTimestamp)[0];
				SearchSPPlanDateStr.set(i,SetString);
			}
		}
		
		if(null!=SearchSPDateStr && 0<SearchSPDateStr.size()){
			for(int i=0;i<SearchSPPlanDateStr.size();i++){
				String SetString = B100_DateTimeControl.DateFormat(SearchSPDateStr.get(i));
				Timestamp SetTimestamp = B100_DateTimeControl.dtmTimestamp2(SetString)[0];
				SetString = B100_DateTimeControl.dtmString2(SetTimestamp)[0];
				SearchSPDateStr.set(i,SetString);
			}
		}
		
		
		if(null!=SearchWmsShipDateStr && 0<SearchWmsShipDateStr.size()){
			for(int i=0;i<SearchWmsShipDateStr.size();i++){
				String SetString = B100_DateTimeControl.DateFormat(SearchWmsShipDateStr.get(i));
				Timestamp SetTimestamp = B100_DateTimeControl.dtmTimestamp2(SetString)[0];
				SetString = B100_DateTimeControl.dtmString2(SetTimestamp)[0];
				SearchWmsShipDateStr.set(i,SetString);
			}
		}
		
		if(null!=SearchFeeFixDateStr && 0<SearchFeeFixDateStr.size()){
			for(int i=0;i<SearchFeeFixDateStr.size();i++){
				String SetString = B100_DateTimeControl.DateFormat(SearchFeeFixDateStr.get(i));
				Timestamp SetTimestamp = B100_DateTimeControl.dtmTimestamp2(SetString)[0];
				SetString = B100_DateTimeControl.dtmString2(SetTimestamp)[0];
				SearchFeeFixDateStr.set(i,SetString);
			}
		}
		
		if(null!=SearchReceiptStampDateStr && 0<SearchReceiptStampDateStr.size()){
			for(int i=0;i<SearchReceiptStampDateStr.size();i++){
				String SetString = B100_DateTimeControl.DateFormat(SearchReceiptStampDateStr.get(i));
				Timestamp SetTimestamp = B100_DateTimeControl.dtmTimestamp2(SetString)[0];
				SetString = B100_DateTimeControl.dtmString2(SetTimestamp)[0];
				SearchReceiptStampDateStr.set(i,SetString);
			}
		}
		
		if(null!=SearchMsExpDateStr && 0<SearchMsExpDateStr.size()){
			for(int i=0;i<SearchMsExpDateStr.size();i++){
				String SetString = B100_DateTimeControl.DateFormat(SearchMsExpDateStr.get(i));
				Timestamp SetTimestamp = B100_DateTimeControl.dtmTimestamp2(SetString)[0];
				SetString = B100_DateTimeControl.dtmString2(SetTimestamp)[0];
				SearchMsExpDateStr.set(i,SetString);
			}
		}
		
		//日付系項目最大は一日進めて00:00:00扱い　※時刻まで検索条件にする場合はそのまま
		if(null!=SearchPlanDateEnd && 0<SearchPlanDateEnd.size()){
			for(int i=0;i<SearchPlanDateEnd.size();i++){
				String SetString = B100_DateTimeControl.DateFormat(SearchPlanDateEnd.get(i));
				Timestamp SetTimestamp = B100_DateTimeControl.dtmTimestamp2(SetString)[0];
				SetTimestamp = B100_DateTimeControl.ndate_after(SetTimestamp,1);
				SetString = B100_DateTimeControl.dtmString2(SetTimestamp)[0];
				SearchPlanDateEnd.set(i,SetString);
			}
		}
		
		if(null!=SearchShipDateEnd && 0<SearchShipDateEnd.size()){
			for(int i=0;i<SearchShipDateEnd.size();i++){
				String SetString = B100_DateTimeControl.DateFormat(SearchShipDateEnd.get(i));
				Timestamp SetTimestamp = B100_DateTimeControl.dtmTimestamp2(SetString)[0];
				SetTimestamp = B100_DateTimeControl.ndate_after(SetTimestamp,1);
				SetString = B100_DateTimeControl.dtmString2(SetTimestamp)[0];
				SearchShipDateEnd.set(i,SetString);
			}
		}
		
		if(null!=SearchSPPlanDateEnd && 0<SearchSPPlanDateEnd.size()){
			for(int i=0;i<SearchSPPlanDateEnd.size();i++){
				String SetString = B100_DateTimeControl.DateFormat(SearchSPPlanDateEnd.get(i));
				Timestamp SetTimestamp = B100_DateTimeControl.dtmTimestamp2(SetString)[0];
				SetTimestamp = B100_DateTimeControl.ndate_after(SetTimestamp,1);
				SetString = B100_DateTimeControl.dtmString2(SetTimestamp)[0];
				SearchSPPlanDateEnd.set(i,SetString);
			}
		}
		
		if(null!=SearchSPDateEnd && 0<SearchSPDateEnd.size()){
			for(int i=0;i<SearchSPDateEnd.size();i++){
				String SetString = B100_DateTimeControl.DateFormat(SearchSPDateEnd.get(i));
				Timestamp SetTimestamp = B100_DateTimeControl.dtmTimestamp2(SetString)[0];
				SetTimestamp = B100_DateTimeControl.ndate_after(SetTimestamp,1);
				SetString = B100_DateTimeControl.dtmString2(SetTimestamp)[0];
				SearchSPDateEnd.set(i,SetString);
			}
		}
		
		if(null!=SearchWmsShipDateEnd && 0<SearchWmsShipDateEnd.size()){
			for(int i=0;i<SearchWmsShipDateEnd.size();i++){
				String SetString = B100_DateTimeControl.DateFormat(SearchWmsShipDateEnd.get(i));
				Timestamp SetTimestamp = B100_DateTimeControl.dtmTimestamp2(SetString)[0];
				SetTimestamp = B100_DateTimeControl.ndate_after(SetTimestamp,1);
				SetString = B100_DateTimeControl.dtmString2(SetTimestamp)[0];
				SearchWmsShipDateEnd.set(i,SetString);
			}
		}
		
		if(null!=SearchFeeFixDateEnd && 0<SearchFeeFixDateEnd.size()){
			for(int i=0;i<SearchFeeFixDateEnd.size();i++){
				String SetString = B100_DateTimeControl.DateFormat(SearchFeeFixDateEnd.get(i));
				Timestamp SetTimestamp = B100_DateTimeControl.dtmTimestamp2(SetString)[0];
				SetTimestamp = B100_DateTimeControl.ndate_after(SetTimestamp,1);
				SetString = B100_DateTimeControl.dtmString2(SetTimestamp)[0];
				SearchFeeFixDateEnd.set(i,SetString);
			}
		}
		
		if(null!=SearchReceiptStampDateEnd && 0<SearchReceiptStampDateEnd.size()){
			for(int i=0;i<SearchReceiptStampDateEnd.size();i++){
				String SetString = B100_DateTimeControl.DateFormat(SearchReceiptStampDateEnd.get(i));
				Timestamp SetTimestamp = B100_DateTimeControl.dtmTimestamp2(SetString)[0];
				SetTimestamp = B100_DateTimeControl.ndate_after(SetTimestamp,1);
				SetString = B100_DateTimeControl.dtmString2(SetTimestamp)[0];
				SearchReceiptStampDateEnd.set(i,SetString);
			}
		}
		
		if(null!=SearchMsExpDateEnd && 0<SearchMsExpDateEnd.size()){
			for(int i=0;i<SearchMsExpDateEnd.size();i++){
				String SetString = B100_DateTimeControl.DateFormat(SearchMsExpDateEnd.get(i));
				Timestamp SetTimestamp = B100_DateTimeControl.dtmTimestamp2(SetString)[0];
				SetTimestamp = B100_DateTimeControl.ndate_after(SetTimestamp,1);
				SetString = B100_DateTimeControl.dtmString2(SetTimestamp)[0];
				SearchMsExpDateEnd.set(i,SetString);
			}
		}
		
		Object[][] Rt = new Object[0][0];
		boolean SearchKick = false;
		if(AllSearch) {SearchKick = true;}
		
		
		
		
		//商品変換マスタを元に荷主商品コードを商品コードに変換する
		Object[][] SearchItemCdFromClItem	= SearchItemCdFromClItem(SearchClGpCD,SearchClCd,SearchClItemCd);
		
		String sql = "select "
				+"(KT0010_OKURI_HD.cl_cd) 				as ClCd,\n"					//荷主コード
				+"(KM0030_CLIENTMST.CLName01)       	as CLName01,\n"				//荷主名
				+"(KM0030_CLIENTMST.ClGpCD)         	as ClGpCD,\n"				//荷主グループCD
				+"(KM0031_CLIENT_GROUP.CLGpName01)  	as CLGpName01,\n"			//荷主グループ標記名
				+"(KT0010_OKURI_HD.InvoiceWHCD) 		as InvoiceWhCd,\n"			//倉庫コード
				+"(KT0010_OKURI_HD.OkuriNo) 			as OkuriNo,\n"				//送り状番号
				+"(KT0010_OKURI_HD.ClDeliNo) 			as ClDeliNo,\n"				//荷主管理番号
				+"(KT0010_OKURI_HD.PickupWHCD) 			as PickupWhCd,\n"			//集荷倉庫CD
				+"(KT0010_OKURI_HD.PurposeFG) 			as PurposeFG,\n"			//目的フラグ
				+"(KT0010_OKURI_HD.PlanDate) 			as PlanDate,\n"				//出荷予定日
				+"(KT0010_OKURI_HD.ShipDate) 			as ShipDate,\n"				//出荷実績日
				+"(KT0010_OKURI_HD.SPPlanDate) 			as SPPlanDate,\n"			//着日指定
				+"(KT0010_OKURI_HD.SPDate) 				as SPDate,\n"				//着日実績
				+"(KT0010_OKURI_HD.SPTimeFG) 			as SPTimeFG,\n"				//時間指定区分
				+"(KT0010_OKURI_HD.SPTimeStr) 			as SPTimeStr,\n"			//時間指定開始
				+"(KT0010_OKURI_HD.SPTimeEnd) 			as SPTimeEnd,\n"			//時間指定終了
				+"(KT0010_OKURI_HD.TotalWeight) 		as TotalWeight,\n"			//荷物重量(kg)
				+"(KT0010_OKURI_HD.TotalSize) 			as TotalSize,\n"			//荷物サイズ
				+"(KT0010_OKURI_HD.TotalQty) 			as TotalQty,\n"				//個口数
				+"(KT0010_OKURI_HD.DeliveryTypeCd) 		as DeliveryTypeCd,\n"		//運送タイプ01
				+"(KT0010_OKURI_HD.DeliTypeName) 		as DeliTypeName,\n"			//運送タイプ名01
				+"(KT0010_OKURI_HD.DeliveryTypeCd02) 	as DeliveryTypeCd02,\n"		//運送タイプ02
				+"(KT0010_OKURI_HD.DeliTypeName02) 		as DeliTypeName02,\n"		//運送タイプ名02
				+"(KT0010_OKURI_HD.DeliveryTypeCd03) 	as DeliveryTypeCd03,\n"		//運送タイプ03
				+"(KT0010_OKURI_HD.DeliTypeName03) 		as DeliTypeName03,\n"		//運送タイプ名03
				+"(KT0010_OKURI_HD.DeliveryTypeCd04) 	as DeliveryTypeCd04,\n"		//運送タイプ04
				+"(KT0010_OKURI_HD.DeliTypeName04) 		as DeliTypeName04,\n"		//運送タイプ名04
				+"(KT0010_OKURI_HD.DeliveryTypeCd05) 	as DeliveryTypeCd05,\n"		//運送タイプ05
				+"(KT0010_OKURI_HD.DeliTypeName05) 		as DeliTypeName05,\n"		//運送タイプ名05
				+"(KT0010_OKURI_HD.CodFG) 				as CodFG,\n"				//代引きフラグ
				+"(KT0010_OKURI_HD.CodPayTotal) 		as CodPayTotal,\n"			//代引き収受金額合計
				+"(KT0010_OKURI_HD.CodPay) 				as CodPay,\n"				//代引き金額
				+"(KT0010_OKURI_HD.CodConsumptionTax) 	as CodConsumptionTax,\n"	//代引き消費税
				+"(KT0010_OKURI_HD.ChildrenFG) 			as ChildrenFG,\n"			//赤黒区分
				+"(KT0010_OKURI_HD.ParentOkuriNo) 		as ParentOkuriNo,\n"		//親伝票番号
				+"(KT0010_OKURI_HD.NiokuriCd) 			as NiokuriCd,\n"			//荷送り人コード
				+"(KT0010_OKURI_HD.NiokuriDepartmentCd)	as NiokuriDepartmentCd,\n"	//荷送人部署CD
 				+"(KT0010_OKURI_HD.NiokuriName01) 		as NiokuriName01,\n"		//荷送人名01
				+"(KT0010_OKURI_HD.NiokuriName02) 		as NiokuriName02,\n"		//荷送人名02
				+"(KT0010_OKURI_HD.NiokuriName03) 		as NiokuriName03,\n"		//荷送人名03
				+"(KT0010_OKURI_HD.NiokuriPost) 		as NiokuriPost,\n"			//荷送人郵便番号
				+"(KT0010_OKURI_HD.NiokuriAdd01) 		as NiokuriAdd01,\n"			//荷送人住所01
				+"(KT0010_OKURI_HD.NiokuriAdd02) 		as NiokuriAdd02,\n"			//荷送人住所02
				+"(KT0010_OKURI_HD.NiokuriAdd03) 		as NiokuriAdd03,\n"			//荷送人住所03
				+"(KT0010_OKURI_HD.NioKuriTel) 			as NioKuriTel,\n"			//荷送人TEL
				+"(KT0010_OKURI_HD.NioKuriFax) 			as NioKuriFax,\n"			//荷送人FAX
				+"(KT0010_OKURI_HD.NioKuriMail) 		as NioKuriMail,\n"			//荷送人MAIL
				+"(KT0010_OKURI_HD.NiokuriMunicCd) 		as NiokuriMunicCd,\n"		//荷送人市区町村CD
				+"(KT0010_OKURI_HD.DeliCd) 				as DeliCd,\n"				//荷届け先コード
				+"(KT0010_OKURI_HD.ClDeliCd) 			as ClDeliCd,\n"				//荷主荷届け先コード
				+"(KT0010_OKURI_HD.DeliDepartmentCd) 	as DeliDepartmentCd,\n"		//部署CD
				+"(KT0010_OKURI_HD.DeliName01) 			as DeliName01,\n"			//荷届け先名01
				+"(KT0010_OKURI_HD.DeliName02) 			as DeliName02,\n"			//荷届け先名02
				+"(KT0010_OKURI_HD.DeliName03) 			as DeliName03,\n"			//荷届け先名03
				+"(KT0010_OKURI_HD.DeliPost) 			as DeliPost,\n"				//荷届け先郵便番号
				+"(KT0010_OKURI_HD.DeliAdd01) 			as DeliAdd01,\n"			//荷届け先住所01
				+"(KT0010_OKURI_HD.DeliAdd02) 			as DeliAdd02,\n"			//荷届け先住所02
				+"(KT0010_OKURI_HD.DeliAdd03) 			as DeliAdd03,\n"			//荷届け先住所03
				+"(KT0010_OKURI_HD.DeliTel) 			as DeliTel,\n"				//荷届け先TEL
				+"(KT0010_OKURI_HD.DeliFax) 			as DeliFax,\n"				//荷届け先FAX
				+"(KT0010_OKURI_HD.DeliMail) 			as DeliMail,\n"				//荷届け先MAIL
				+"(KT0010_OKURI_HD.DeliMunicCd) 		as DeliMunicCd,\n"			//荷届先市区町村CD
				+"(KT0010_OKURI_HD.Com01) 				as Com01,\n"				//コメント01
				+"(KT0010_OKURI_HD.Com02) 				as Com02,\n"				//コメント02
				+"(KT0010_OKURI_HD.Com03) 				as Com03,\n"				//コメント03
				+"(KT0010_OKURI_HD.Com04) 				as Com04,\n"				//コメント04
				+"(KT0010_OKURI_HD.Com05) 				as Com05,\n"				//コメント05
				+"(KT0010_OKURI_HD.Status) 				as Status,\n"				//状況
				+"(KT0010_OKURI_HD.TaxFg) 				as TaxFg,\n"				//税区分
				+"(KT0010_OKURI_HD.TaxRate) 			as TaxRate,\n"				//税率
				+"(KT0010_OKURI_HD.DeliFee) 			as DeliFee,\n"				//運賃
				+"(KT0010_OKURI_HD.AddDeliFee01) 		as AddDeliFee01,\n"			//付帯費用1
				+"(KT0010_OKURI_HD.AddDeliFee02) 		as AddDeliFee02,\n"			//付帯費用2
				+"(KT0010_OKURI_HD.AddDeliFee03) 		as AddDeliFee03,\n"			//付帯費用3
				+"(KT0010_OKURI_HD.HaighWayFee01) 		as HaighWayFee01,\n"		//高速代等実費精算分1（内税）
				+"(KT0010_OKURI_HD.HaighWayFee02) 		as HaighWayFee02,\n"		//高速代等実費精算分2（内税）
				+"(KT0010_OKURI_HD.ConsumptionTax) 		as ConsumptionTax,\n"		//消費税
				+"(KT0010_OKURI_HD.WithOutTaxTotal) 	as WithOutTaxTotal,\n"		//税別合計金額
				+"(KT0010_OKURI_HD.TotalFee) 			as TotalFee,\n"				//税込請求額合計
				+"(KT0010_OKURI_HD.FeeFixFG) 			as FeeFixFG,\n"				//金額確定フラグ
				+"(KT0010_OKURI_HD.FeeFixDate) 			as FeeFixDate,\n"			//金額確定日時
				+"(KT0010_OKURI_HD.ReceiptStampFG) 		as ReceiptStampFG,\n"		//受領印チェック
				+"(KT0010_OKURI_HD.ReceiptStampDate) 	as ReceiptStampDate,\n"		//受領印日時
				+"(KT0010_OKURI_HD.InvoiceStatus) 		as InvoiceStatus,\n"		//請求ステータス
				+"(KT0010_OKURI_HD.EntryDate) 			as EntryDate,\n"			//登録日
				+"(KT0010_OKURI_HD.UpdateDate) 			as UpdateDate,\n"			//更新日
				+"(KT0010_OKURI_HD.EntryUser) 			as EntryUser,\n"			//登録者
				+"(KT0010_OKURI_HD.UpdateUser) 			as UpdateUser,\n"			//更新者
				+"(KT0010_OKURI_HD.EntryPG) 			as EntryPG,\n"				//登録プログラム
				+"(KT0010_OKURI_HD.UpdatePG) 			as UpdatePG,\n"				//更新プログラム
				+"(KT0010_OKURI_HD.UseFeeBasePtCd) 		as UseFeeBasePtCd,\n"		//適用運賃タリフCD
				+"(KT0010_OKURI_HD.WmsStatus) 			as WmsStatus,\n"			//在庫管理ステータス
				+"(KT0010_OKURI_HD.WmsShipDate) 		as WmsShipDate,\n"			//倉庫出荷日
				+"(KT0010_OKURI_HD.CourseGpCd) 			as CourseGpCd,\n"			//コースグループコード
				+"(KT0010_OKURI_HD.CourseCD) 			as CourseCD,\n"				//一次配車コースコード
				+"(KT0010_OKURI_HD.CourseCDEda) 		as CourseCDEda,\n"			//一次配車コースコード枝番
				+"(KT0010_OKURI_HD.PitGrp) 				as PitGrp,\n"				//一次配車払出ピットグループ
				+"(KT0010_OKURI_HD.Pit01) 				as Pit01,\n"				//一次配車払出ピット01
				+"(KT0010_OKURI_HD.Pit02) 				as Pit02,\n"				//一次配車払出ピット02
				+"(KT0010_OKURI_HD.Pit03) 				as Pit03,\n"				//一次配車払出ピット03
				+"(KT0010_OKURI_HD.Pit04) 				as Pit04,\n"				//一次配車払出ピット04
				+"(KT0010_OKURI_HD.Pit05) 				as Pit05,\n"				//一次配車払出ピット05
				
				+"(KT0011_OKURI_MS.cl_cd) 				as MsClCd,\n"				//明細荷主コード
				+"(KT0011_OKURI_MS.InvoiceWHCD) 		as MsInvoiceWhCd,\n"		//明細倉庫コード
				+"(KT0011_OKURI_MS.OkuriNo) 			as MsOkuriNo,\n"			//明細送り状番号
				+"(KT0011_OKURI_MS.MsNo) 				as MsNo,\n"					//明細番号
				+"(KT0011_OKURI_MS.DeliNo) 				as MsDeliNo,\n"				//明細出荷番号
				+"(KT0011_OKURI_MS.DelliMsNo) 			as MsDelliMsNo,\n"			//明細出荷番号明細番号
				+"(KT0011_OKURI_MS.ClOrderNo) 			as MsClOrderNo,\n"			//明細荷主管理番号
				+"(KT0011_OKURI_MS.ClGpCd) 				as MsClGpCd,\n"				//明細荷主グループコード
				+"(KT0011_OKURI_MS.ItemCd) 				as MsItemCd,\n"				//明細商品コード
				+"(KT0011_OKURI_MS.ItemName01) 			as MsItemName01,\n"			//明細商品表記名
				+"(KT0011_OKURI_MS.ItemName02) 			as MsItemName02,\n"			//明細商品正式名
				+"(KT0011_OKURI_MS.ItemName03) 			as MsItemName03,\n"			//明細商品略名
				+"(KT0011_OKURI_MS.UnitWeight) 			as MsUnitWeight,\n"			//明細単位重量
				+"(KT0011_OKURI_MS.UnitSize) 			as MsUnitSize,\n"			//明細単位サイズ
				+"(KT0011_OKURI_MS.Qty) 				as MsQty,\n"				//明細個数
				+"(KT0011_OKURI_MS.PackingQty) 			as MsPackingQty,\n"			//明細荷姿数量
				+"(KT0011_OKURI_MS.UnitName) 			as MsUnitName,\n"			//明細明細単位
				+"(KT0011_OKURI_MS.SubTotalWeight) 		as MsSubTotalWeight,\n"		//明細明細重量
				+"(KT0011_OKURI_MS.SubTotalSize) 		as MsSubTotalSize,\n"		//明細明細サイズ
				+"(KT0011_OKURI_MS.UnitPrice) 			as MsUnitPrice,\n"			//明細単価
				+"(KT0011_OKURI_MS.SubTotalPrice) 		as MsSubTotalPrice,\n"		//明細金額
				+"(KT0011_OKURI_MS.CategoryCd) 			as MsCategoryCd,\n"			//明細商品分類
				+"(KT0011_OKURI_MS.CategoryName) 		as MsCategoryName,\n"		//明細商品分類名
				+"(KT0011_OKURI_MS.TildFG) 				as MsTildFG,\n"				//明細温度区分
				+"(KT0011_OKURI_MS.TildName) 			as MsTildName,\n"			//明細温度区分名
				+"(KT0011_OKURI_MS.Com01) 				as MsCom01,\n"				//明細コメント01
				+"(KT0011_OKURI_MS.Com02) 				as MsCom02,\n"				//明細コメント02
				+"(KT0011_OKURI_MS.Com03) 				as MsCom03,\n"				//明細コメント03
				+"(KT0011_OKURI_MS.Com04) 				as MsCom04,\n"				//明細コメント04
				+"(KT0011_OKURI_MS.Com05) 				as MsCom05,\n"				//明細コメント05
				+"(KT0011_OKURI_MS.EntryDate) 			as MsEntryDate,\n"			//明細登録日
				+"(KT0011_OKURI_MS.UpdateDate) 			as MsUpdateDate,\n"			//明細更新日
				+"(KT0011_OKURI_MS.EntryUser) 			as MsEntryUser,\n"			//明細登録者
				+"(KT0011_OKURI_MS.UpdateUser) 			as MsUpdateUser,\n"			//明細更新者
				+"(KT0011_OKURI_MS.Lot) 				as MsLot,\n"				//明細ロット指定
				+"(KT0011_OKURI_MS.ExpDate) 			as MsExpDate,\n"			//明細賞味期限指定
				+"(KT0011_OKURI_MS.PackingType) 		as MsPackingType,\n"		//明細荷姿タイプ
				+"(KT0011_OKURI_MS.ClItemCd) 			as MsClItemCd,\n"			//明細荷主商品CD
				+"(KT0011_OKURI_MS.ItemMDNo) 			as MsItemMDNo,\n"			//明細型番
				+"(KT0011_OKURI_MS.JanCd) 				as MsJanCd \n"				//明細荷姿JanCd
				
				+" from "+A00000_Main.MySqlDefaultSchemaNYANKO+".KT0010_OKURI_HD \n"
				+" left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KT0011_OKURI_MS \n"
				+" on(KT0010_OKURI_HD.cl_cd = KT0011_OKURI_MS.cl_cd"
				+"and KT0010_OKURI_HD.InvoiceWHCD = KT0011_OKURI_MS.InvoiceWHCD"
				+"and KT0010_OKURI_HD.OkuriNo = KT0011_OKURI_MS.OkuriNo"
				+")\n"
				+ " left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0030_CLIENTMST"
				+ " on("
				+ " KT0010_OKURI_HD.cl_cd = KM0030_CLIENTMST.cl_cd"
				+ ")\n"
				+ " left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP"
				+ " on("
				+ " KM0030_CLIENTMST.ClGpCd = KM0031_CLIENT_GROUP.ClGpCD"
				+ ")\n"
				+" where 1=1 \n"
				;
		
		if(null!=SearchClCd && 0<SearchClCd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchClCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.cl_cd = ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchClGpCD && 0<SearchClGpCD.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchClGpCD.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0011_OKURI_MS.ClGpCd = ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchOkuriNo && 0<SearchOkuriNo.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchOkuriNo.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.OkuriNo = ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchClDeliNo && 0<SearchClDeliNo.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchClDeliNo.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.ClDeliNo = ?";
				sql = sql + "or KT0011_OKURI_MS.ClOrderNo = ?";
				sql = sql + "or KT0011_OKURI_MS.DeliNo = ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchPickupWhCd && 0<SearchPickupWhCd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchPickupWhCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.PickupWHCD = ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchPurposeFG && 0<SearchPurposeFG.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchPurposeFG.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.PurposeFG = ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchPlanDateStr && 0<SearchPlanDateStr.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchPlanDateStr.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.PlanDate >= ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchShipDateStr && 0<SearchShipDateStr.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchShipDateStr.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.ShipDate >= ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchSPPlanDateStr && 0<SearchSPPlanDateStr.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchSPPlanDateStr.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.SPPlanDate >= ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchSPDateStr && 0<SearchSPDateStr.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchSPDateStr.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.SPDate >= ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchPlanDateEnd && 0<SearchPlanDateEnd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchPlanDateEnd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.PlanDate < ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchShipDateEnd && 0<SearchShipDateEnd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchShipDateEnd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.ShipDate < ?";
			}
			sql = sql + ")";
		}

		if(null!=SearchSPPlanDateEnd && 0<SearchSPPlanDateEnd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchSPPlanDateEnd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.SPPlanDate < ?";
			}
			sql = sql + ")";
		}

		if(null!=SearchSPDateEnd && 0<SearchSPDateEnd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchSPDateEnd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.SPDate < ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchTotalWeightMin && 0<SearchTotalWeightMin.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchTotalWeightMin.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.TotalWeight >= ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchTotalSizeMin && 0<SearchTotalSizeMin.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchTotalSizeMin.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.TotalSize >= ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchTotalQtyMin && 0<SearchTotalQtyMin.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchTotalQtyMin.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.TotalQty >= ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchTotalWeightMax && 0<SearchTotalWeightMax.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchTotalWeightMax.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.TotalWeight <= ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchTotalSizeMax && 0<SearchTotalSizeMax.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchTotalSizeMax.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.TotalSize <= ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchTotalQtyMax && 0<SearchTotalQtyMax.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchTotalQtyMax.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.TotalQty <= ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchDeliveryTypeCd && 0<SearchDeliveryTypeCd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchDeliveryTypeCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.DeliveryTypeCd = ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchDeliveryTypeCd02 && 0<SearchDeliveryTypeCd02.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchDeliveryTypeCd02.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.DeliveryTypeCd02 = ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchDeliveryTypeCd03 && 0<SearchDeliveryTypeCd03.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchDeliveryTypeCd03.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.DeliveryTypeCd03 = ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchDeliveryTypeCd04 && 0<SearchDeliveryTypeCd04.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchDeliveryTypeCd04.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.DeliveryTypeCd04 = ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchDeliveryTypeCd05 && 0<SearchDeliveryTypeCd05.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchDeliveryTypeCd05.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.DeliveryTypeCd05 = ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchCodFG && 0<SearchCodFG.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchCodFG.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.CodFG = ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchCodPayTotalMin && 0<SearchCodPayTotalMin.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchCodPayTotalMin.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.CodPayTotal >= ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchCodPayTotalMax && 0<SearchCodPayTotalMax.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchCodPayTotalMax.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.CodPayTotal <= ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchChildrenFG && 0<SearchChildrenFG.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchChildrenFG.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.ChildrenFG = ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchParentOkuriNo && 0<SearchParentOkuriNo.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchParentOkuriNo.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.ParentOkuriNo = ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchNiokuriCd && 0<SearchNiokuriCd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchNiokuriCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.NiokuriCd = ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchNiokuriDepartmentCd && 0<SearchNiokuriDepartmentCd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchNiokuriDepartmentCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.NiokuriDepartmentCd = ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchNiokuriName && 0<SearchNiokuriName.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchNiokuriName.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.NiokuriName01 Like ?";
				sql = sql + " or KT0010_OKURI_HD.NiokuriName02 Like ?";
				sql = sql + " or KT0010_OKURI_HD.NiokuriName03 Like ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchNiokuriPost && 0<SearchNiokuriPost.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchNiokuriPost.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.NiokuriPost Like ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchNiokuriAdd && 0<SearchNiokuriAdd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchNiokuriAdd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " CONCAT (KT0010_OKURI_HD.NiokuriAdd01";
				sql = sql + " , KT0010_OKURI_HD.NiokuriAdd02";
				sql = sql + " , KT0010_OKURI_HD.NiokuriAdd03) like ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchNioKuriTel && 0<SearchNioKuriTel.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchNioKuriTel.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.NioKuriTel Like ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchNioKuriFax && 0<SearchNioKuriFax.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchNioKuriFax.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.NioKuriFax Like ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchNioKuriMail && 0<SearchNioKuriMail.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchNioKuriMail.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.NioKuriMail Like ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchNiokuriMunicCd && 0<SearchNiokuriMunicCd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchNiokuriMunicCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.NiokuriMunicCd Like ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchDeliCd && 0<SearchDeliCd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchDeliCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.DeliCd = ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchClDeliCd && 0<SearchClDeliCd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchClDeliCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.ClDeliCd = ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchDeliDepartmentCd && 0<SearchDeliDepartmentCd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchDeliDepartmentCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.DeliDepartmentCd = ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchDeliName && 0<SearchDeliName.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchDeliName.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.DeliName01 Like ?";
				sql = sql + " or KT0010_OKURI_HD.DeliName02 Like ?";
				sql = sql + " or KT0010_OKURI_HD.DeliName03 Like ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchDeliPost && 0<SearchDeliPost.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchDeliPost.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.DeliPost Like ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchDeliAdd && 0<SearchDeliAdd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchDeliAdd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " CONCAT (KT0010_OKURI_HD.DeliAdd01";
				sql = sql + " , KT0010_OKURI_HD.DeliAdd02";
				sql = sql + " , KT0010_OKURI_HD.DeliAdd03) Like ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchDeliTel && 0<SearchDeliTel.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchDeliTel.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.DeliTel Like ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchDeliFax && 0<SearchDeliFax.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchDeliFax.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.DeliFax Like ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchDeliMail && 0<SearchDeliMail.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchDeliMail.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.DeliMail Like ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchDeliMunicCd && 0<SearchDeliMunicCd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchDeliMunicCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.DeliMunicCd Like ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchCom && 0<SearchCom.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchCom.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.Com01 Like ?";
				sql = sql + " or KT0010_OKURI_HD.Com02 Like ?";
				sql = sql + " or KT0010_OKURI_HD.Com03 Like ?";
				sql = sql + " or KT0010_OKURI_HD.Com04 Like ?";
				sql = sql + " or KT0010_OKURI_HD.Com05 Like ?";
				sql = sql + " or KT0011_OKURI_MS.Com01 Like ?";
				sql = sql + " or KT0011_OKURI_MS.Com02 Like ?";
				sql = sql + " or KT0011_OKURI_MS.Com03 Like ?";
				sql = sql + " or KT0011_OKURI_MS.Com04 Like ?";
				sql = sql + " or KT0011_OKURI_MS.Com05 Like ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchStatus && 0<SearchStatus.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchStatus.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.Status = ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchFeeFixFG && 0<SearchFeeFixFG.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchFeeFixFG.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.FeeFixFG = ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchReceiptStampFG && 0<SearchReceiptStampFG.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchReceiptStampFG.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.ReceiptStampFG = ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchInvoiceStatus && 0<SearchInvoiceStatus.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchInvoiceStatus.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.InvoiceStatus = ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchWithOutTaxTotalMin && 0<SearchWithOutTaxTotalMin.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchWithOutTaxTotalMin.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.WithOutTaxTotal >= ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchTotalFeeMin && 0<SearchTotalFeeMin.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchTotalFeeMin.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.TotalFee >= ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchFeeFixDateStr && 0<SearchFeeFixDateStr.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchFeeFixDateStr.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.FeeFixDate >= ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchReceiptStampDateStr && 0<SearchReceiptStampDateStr.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchReceiptStampDateStr.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.ReceiptStampDate >= ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchEntryDateStr && 0<SearchEntryDateStr.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchEntryDateStr.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.EntryDate >= ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchUpdateDateStr && 0<SearchUpdateDateStr.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchUpdateDateStr.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.UpdateDate >= ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchWithOutTaxTotalMax && 0<SearchWithOutTaxTotalMax.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchWithOutTaxTotalMax.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.WithOutTaxTotal <= ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchTotalFeeMax && 0<SearchTotalFeeMax.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchTotalFeeMax.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.TotalFee <= ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchFeeFixDateEnd && 0<SearchFeeFixDateEnd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchFeeFixDateEnd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.FeeFixDate < ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchReceiptStampDateEnd && 0<SearchReceiptStampDateEnd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchReceiptStampDateEnd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.ReceiptStampDate < ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchEntryDateEnd && 0<SearchEntryDateEnd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchEntryDateEnd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.EntryDate <= ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchUpdateDateEnd && 0<SearchUpdateDateEnd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchUpdateDateEnd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.UpdateDate <= ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchEntryUser && 0<SearchEntryUser.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchEntryUser.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.EntryUser Like ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchUpdateUser && 0<SearchUpdateUser.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchUpdateUser.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.UpdateUser Like ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchEntryPG && 0<SearchEntryPG.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchEntryPG.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.EntryPG = ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchUpdatePG && 0<SearchUpdatePG.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchUpdatePG.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.UpdatePG = ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchUseFeeBasePtCd && 0<SearchUseFeeBasePtCd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchUseFeeBasePtCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.UseFeeBasePtCd = ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchWmsStatus && 0<SearchWmsStatus.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchWmsStatus.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.WmsStatus = ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchWmsShipDateStr && 0<SearchWmsShipDateStr.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchWmsShipDateStr.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.WmsShipDate >= ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchWmsShipDateEnd && 0<SearchWmsShipDateEnd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchWmsShipDateEnd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.WmsShipDate < ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchCourseGpCd && 0<SearchCourseGpCd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchCourseGpCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.CourseGpCd = ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchCourseCD && 0<SearchCourseCD.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchCourseCD.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.CourseCD = ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchCourseCDEda && 0<SearchCourseCDEda.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchCourseCDEda.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.CourseCDEda = ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchPitGrp && 0<SearchPitGrp.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchPitGrp.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.PitGrp = ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchPit && 0<SearchPit.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchPit.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.Pit01 = ?";
				sql = sql + " or KT0010_OKURI_HD.Pit02 = ?";
				sql = sql + " or KT0010_OKURI_HD.Pit03 = ?";
				sql = sql + " or KT0010_OKURI_HD.Pit04 = ?";
				sql = sql + " or KT0010_OKURI_HD.Pit05 = ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchMsItemCd && 0<SearchMsItemCd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchMsItemCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0011_OKURI_MS.ItemCd = ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchMsItemName && 0<SearchMsItemName.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchMsItemName.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0011_OKURI_MS.ItemName01 = ?";
				sql = sql + " or KT0011_OKURI_MS.ItemName02 = ?";
				sql = sql + " or KT0011_OKURI_MS.ItemName03 = ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchClItemCd && 0<SearchClItemCd.size()){				//荷主商品コード
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchClItemCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0011_OKURI_MS.ClItemCd = ?";
			}
			if(null!=SearchItemCdFromClItem && 0< SearchItemCdFromClItem.length) {
				for(int i=0;i<SearchItemCdFromClItem.length;i++) {
					sql = sql + " or (KT0011_OKURI_MS.cl_cd = ?";
					sql = sql + "  and KT0011_OKURI_MS.ItemCd.ItemCd = ?)";
				}
			}
			sql = sql + ")";
		}
		
		if(null!=SearchMsCategoryCd && 0<SearchMsCategoryCd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchMsCategoryCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0011_OKURI_MS.CategoryCd = ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchMsCategoryName && 0<SearchMsCategoryName.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchMsCategoryName.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0011_OKURI_MS.CategoryName Like ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchMsTildFG && 0<SearchMsTildFG.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchMsTildFG.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0011_OKURI_MS.TildFG = ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchMsTildName && 0<SearchMsTildName.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchMsTildName.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0011_OKURI_MS.TildName Like ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchMsLot && 0<SearchMsLot.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchMsLot.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0011_OKURI_MS.Lot = ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchMsExpDateStr && 0<SearchMsExpDateStr.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchMsExpDateStr.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0011_OKURI_MS.ExpDate >= ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchMsExpDateEnd && 0<SearchMsExpDateEnd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchMsExpDateEnd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0011_OKURI_MS.ExpDate < ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchMsPackingType && 0<SearchMsPackingType.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchMsPackingType.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0011_OKURI_MS.PackingType = ?";
			}
			sql = sql + ")";
		}
		
		sql = sql + " order by KT0010_OKURI_HD.InvoiceWHCD,KT0010_OKURI_HD.cl_cd,KT0010_OKURI_HD.OkuriNo,KT0011_OKURI_MS.MsNo";
		//System.out.println(sql);
		
		if(SearchKick) {
			A100_DbConnect.DB_CONN("NYANKO");
			ResultSet rset01 = null;
			PreparedStatement stmt01 = null;
			try {
				stmt01 = A100_DbConnect.conn.prepareStatement(sql);
				int StmtCount = 0;
				
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
				
				if(null!=SearchOkuriNo && 0<SearchOkuriNo.size()){
					for(int i=0;i<SearchOkuriNo.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchOkuriNo.get(i)+"");
					}
				}
				
				if(null!=SearchClDeliNo && 0<SearchClDeliNo.size()){
					for(int i=0;i<SearchClDeliNo.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchClDeliNo.get(i)+"");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchClDeliNo.get(i)+"");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchClDeliNo.get(i)+"");
					}
				}
				
				if(null!=SearchPickupWhCd && 0<SearchPickupWhCd.size()){
					for(int i=0;i<SearchPickupWhCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchPickupWhCd.get(i)+"");
					}
				}
				
				if(null!=SearchPurposeFG && 0<SearchPurposeFG.size()){
					for(int i=0;i<SearchPurposeFG.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchPurposeFG.get(i)+"");
					}
				}
				
				if(null!=SearchPlanDateStr && 0<SearchPlanDateStr.size()){
					for(int i=0;i<SearchPlanDateStr.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchPlanDateStr.get(i)+"");
					}
				}
				
				if(null!=SearchShipDateStr && 0<SearchShipDateStr.size()){
					for(int i=0;i<SearchShipDateStr.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchShipDateStr.get(i)+"");
					}
				}
				
				if(null!=SearchSPPlanDateStr && 0<SearchSPPlanDateStr.size()){
					for(int i=0;i<SearchSPPlanDateStr.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchSPPlanDateStr.get(i)+"");
					}
				}
				
				if(null!=SearchSPDateStr && 0<SearchSPDateStr.size()){
					for(int i=0;i<SearchSPDateStr.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchSPDateStr.get(i)+"");
					}
				}
				
				if(null!=SearchPlanDateEnd && 0<SearchPlanDateEnd.size()){
					for(int i=0;i<SearchPlanDateEnd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchPlanDateEnd.get(i)+"");
					}
				}
				
				if(null!=SearchShipDateEnd && 0<SearchShipDateEnd.size()){
					for(int i=0;i<SearchShipDateEnd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchShipDateEnd.get(i)+"");
					}
				}
		
				if(null!=SearchSPPlanDateEnd && 0<SearchSPPlanDateEnd.size()){
					for(int i=0;i<SearchSPPlanDateEnd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchSPPlanDateEnd.get(i)+"");
					}
				}
		
				if(null!=SearchSPDateEnd && 0<SearchSPDateEnd.size()){
					for(int i=0;i<SearchSPDateEnd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchSPDateEnd.get(i)+"");
					}
				}
				
				if(null!=SearchTotalWeightMin && 0<SearchTotalWeightMin.size()){
					for(int i=0;i<SearchTotalWeightMin.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchTotalWeightMin.get(i)+"");
					}
				}
				
				if(null!=SearchTotalSizeMin && 0<SearchTotalSizeMin.size()){
					for(int i=0;i<SearchTotalSizeMin.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchTotalSizeMin.get(i)+"");
					}
				}
				
				if(null!=SearchTotalQtyMin && 0<SearchTotalQtyMin.size()){
					for(int i=0;i<SearchTotalQtyMin.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchTotalQtyMin.get(i)+"");
					}
				}
				
				if(null!=SearchTotalWeightMax && 0<SearchTotalWeightMax.size()){
					for(int i=0;i<SearchTotalWeightMax.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchTotalWeightMax.get(i)+"");
					}
				}
				
				if(null!=SearchTotalSizeMax && 0<SearchTotalSizeMax.size()){
					for(int i=0;i<SearchTotalSizeMax.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchTotalSizeMax.get(i)+"");
					}
				}
				
				if(null!=SearchTotalQtyMax && 0<SearchTotalQtyMax.size()){
					for(int i=0;i<SearchTotalQtyMax.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchTotalQtyMax.get(i)+"");
					}
				}
				
				if(null!=SearchDeliveryTypeCd && 0<SearchDeliveryTypeCd.size()){
					for(int i=0;i<SearchDeliveryTypeCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchDeliveryTypeCd.get(i)+"");
					}
				}
				
				if(null!=SearchDeliveryTypeCd02 && 0<SearchDeliveryTypeCd02.size()){
					for(int i=0;i<SearchDeliveryTypeCd02.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchDeliveryTypeCd02.get(i)+"");
					}
				}
				
				if(null!=SearchDeliveryTypeCd03 && 0<SearchDeliveryTypeCd03.size()){
					for(int i=0;i<SearchDeliveryTypeCd03.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchDeliveryTypeCd03.get(i)+"");
					}
				}
				
				if(null!=SearchDeliveryTypeCd04 && 0<SearchDeliveryTypeCd04.size()){
					for(int i=0;i<SearchDeliveryTypeCd04.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchDeliveryTypeCd04.get(i)+"");
					}
				}
				
				if(null!=SearchDeliveryTypeCd05 && 0<SearchDeliveryTypeCd05.size()){
					for(int i=0;i<SearchDeliveryTypeCd05.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchDeliveryTypeCd05.get(i)+"");
					}
				}
				
				if(null!=SearchCodFG && 0<SearchCodFG.size()){
					for(int i=0;i<SearchCodFG.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchCodFG.get(i)+"");
					}
				}
				
				if(null!=SearchCodPayTotalMin && 0<SearchCodPayTotalMin.size()){
					for(int i=0;i<SearchCodPayTotalMin.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchCodPayTotalMin.get(i)+"");
					}
				}
				
				if(null!=SearchCodPayTotalMax && 0<SearchCodPayTotalMax.size()){
					for(int i=0;i<SearchCodPayTotalMax.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchCodPayTotalMax.get(i)+"");
					}
				}
				
				if(null!=SearchChildrenFG && 0<SearchChildrenFG.size()){
					for(int i=0;i<SearchChildrenFG.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchChildrenFG.get(i)+"");
					}
				}
				
				if(null!=SearchParentOkuriNo && 0<SearchParentOkuriNo.size()){
					for(int i=0;i<SearchParentOkuriNo.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchParentOkuriNo.get(i)+"");
					}
				}
				
				if(null!=SearchNiokuriCd && 0<SearchNiokuriCd.size()){
					for(int i=0;i<SearchNiokuriCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchNiokuriCd.get(i)+"");
					}
				}
				
				if(null!=SearchNiokuriDepartmentCd && 0<SearchNiokuriDepartmentCd.size()){
					for(int i=0;i<SearchNiokuriDepartmentCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchNiokuriDepartmentCd.get(i)+"");
					}
				}
				
				if(null!=SearchNiokuriName && 0<SearchNiokuriName.size()){
					for(int i=0;i<SearchNiokuriName.size();i++){
						if(0<i){sql = sql + " or ";}
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchNiokuriName.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchNiokuriName.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchNiokuriName.get(i)+"%");
					}
				}
				
				if(null!=SearchNiokuriPost && 0<SearchNiokuriPost.size()){
					for(int i=0;i<SearchNiokuriPost.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchNiokuriPost.get(i)+"%");
					}
				}
				
				if(null!=SearchNiokuriAdd && 0<SearchNiokuriAdd.size()){
					for(int i=0;i<SearchNiokuriAdd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchNiokuriAdd.get(i)+"%");
					}
				}
				
				if(null!=SearchNioKuriTel && 0<SearchNioKuriTel.size()){
					for(int i=0;i<SearchNioKuriTel.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchNioKuriTel.get(i)+"%");
					}
				}
				
				if(null!=SearchNioKuriFax && 0<SearchNioKuriFax.size()){
					for(int i=0;i<SearchNioKuriFax.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchNioKuriFax.get(i)+"%");
					}
				}
				
				if(null!=SearchNioKuriMail && 0<SearchNioKuriMail.size()){
					for(int i=0;i<SearchNioKuriMail.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchNioKuriMail.get(i)+"%");
					}
				}
				
				if(null!=SearchNiokuriMunicCd && 0<SearchNiokuriMunicCd.size()){
					for(int i=0;i<SearchNiokuriMunicCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchNiokuriMunicCd.get(i)+"%");
					}
				}
				
				if(null!=SearchDeliCd && 0<SearchDeliCd.size()){
					for(int i=0;i<SearchDeliCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchDeliCd.get(i)+"");
					}
				}
				
				if(null!=SearchClDeliCd && 0<SearchClDeliCd.size()){
					for(int i=0;i<SearchClDeliCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchClDeliCd.get(i)+"");
					}
				}
				
				if(null!=SearchDeliDepartmentCd && 0<SearchDeliDepartmentCd.size()){
					for(int i=0;i<SearchDeliDepartmentCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchDeliDepartmentCd.get(i)+"");
					}
				}
				
				if(null!=SearchDeliName && 0<SearchDeliName.size()){
					for(int i=0;i<SearchDeliName.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchDeliName.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchDeliName.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchDeliName.get(i)+"%");
					}
				}
				
				if(null!=SearchDeliPost && 0<SearchDeliPost.size()){
					for(int i=0;i<SearchDeliPost.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchDeliPost.get(i)+"%");
					}
				}
				
				if(null!=SearchDeliAdd && 0<SearchDeliAdd.size()){
					for(int i=0;i<SearchDeliAdd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchDeliAdd.get(i)+"%");
					}
				}
				
				if(null!=SearchDeliTel && 0<SearchDeliTel.size()){
					for(int i=0;i<SearchDeliTel.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchDeliTel.get(i)+"%");
					}
				}
				
				if(null!=SearchDeliFax && 0<SearchDeliFax.size()){
					for(int i=0;i<SearchDeliFax.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchDeliFax.get(i)+"%");
					}
				}
				
				if(null!=SearchDeliMail && 0<SearchDeliMail.size()){
					for(int i=0;i<SearchDeliMail.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchDeliMail.get(i)+"%");
					}
				}
				
				if(null!=SearchDeliMunicCd && 0<SearchDeliMunicCd.size()){
					for(int i=0;i<SearchDeliMunicCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchDeliMunicCd.get(i)+"%");
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
				
				if(null!=SearchStatus && 0<SearchStatus.size()){
					for(int i=0;i<SearchStatus.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchStatus.get(i)+"");
					}
				}
				
				if(null!=SearchFeeFixFG && 0<SearchFeeFixFG.size()){
					for(int i=0;i<SearchFeeFixFG.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchFeeFixFG.get(i)+"");
					}
				}
				
				if(null!=SearchReceiptStampFG && 0<SearchReceiptStampFG.size()){
					for(int i=0;i<SearchReceiptStampFG.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchReceiptStampFG.get(i)+"");
					}
				}
				
				if(null!=SearchInvoiceStatus && 0<SearchInvoiceStatus.size()){
					for(int i=0;i<SearchInvoiceStatus.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchInvoiceStatus.get(i)+"");
					}
				}
				
				if(null!=SearchWithOutTaxTotalMin && 0<SearchWithOutTaxTotalMin.size()){
					for(int i=0;i<SearchWithOutTaxTotalMin.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchWithOutTaxTotalMin.get(i)+"");
					}
				}
				
				if(null!=SearchTotalFeeMin && 0<SearchTotalFeeMin.size()){
					for(int i=0;i<SearchTotalFeeMin.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchTotalFeeMin.get(i)+"");
					}
				}
				
				if(null!=SearchFeeFixDateStr && 0<SearchFeeFixDateStr.size()){
					for(int i=0;i<SearchFeeFixDateStr.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchFeeFixDateStr.get(i)+"");
					}
				}
				
				if(null!=SearchReceiptStampDateStr && 0<SearchReceiptStampDateStr.size()){
					for(int i=0;i<SearchReceiptStampDateStr.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchReceiptStampDateStr.get(i)+"");
					}
				}
				
				if(null!=SearchEntryDateStr && 0<SearchEntryDateStr.size()){
					for(int i=0;i<SearchEntryDateStr.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchEntryDateStr.get(i)+"");
					}
				}
				
				if(null!=SearchUpdateDateStr && 0<SearchUpdateDateStr.size()){
					for(int i=0;i<SearchUpdateDateStr.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchUpdateDateStr.get(i)+"");
					}
				}
				
				if(null!=SearchWithOutTaxTotalMax && 0<SearchWithOutTaxTotalMax.size()){
					for(int i=0;i<SearchWithOutTaxTotalMax.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchWithOutTaxTotalMax.get(i)+"");
					}
				}
				
				if(null!=SearchTotalFeeMax && 0<SearchTotalFeeMax.size()){
					for(int i=0;i<SearchTotalFeeMax.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchTotalFeeMax.get(i)+"");
					}
				}
				
				if(null!=SearchFeeFixDateEnd && 0<SearchFeeFixDateEnd.size()){
					for(int i=0;i<SearchFeeFixDateEnd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchFeeFixDateEnd.get(i)+"");
					}
				}
				
				if(null!=SearchReceiptStampDateEnd && 0<SearchReceiptStampDateEnd.size()){
					for(int i=0;i<SearchReceiptStampDateEnd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchReceiptStampDateEnd.get(i)+"");
					}
				}
				
				if(null!=SearchEntryDateEnd && 0<SearchEntryDateEnd.size()){
					for(int i=0;i<SearchEntryDateEnd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchEntryDateEnd.get(i)+"");
					}
				}
				
				if(null!=SearchUpdateDateEnd && 0<SearchUpdateDateEnd.size()){
					for(int i=0;i<SearchUpdateDateEnd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchUpdateDateEnd.get(i)+"");
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
				
				if(null!=SearchEntryPG && 0<SearchEntryPG.size()){
					for(int i=0;i<SearchEntryPG.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchEntryPG.get(i)+"");
					}
				}
				
				if(null!=SearchUpdatePG && 0<SearchUpdatePG.size()){
					for(int i=0;i<SearchUpdatePG.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchUpdatePG.get(i)+"");
					}
				}
				
				if(null!=SearchUseFeeBasePtCd && 0<SearchUseFeeBasePtCd.size()){
					for(int i=0;i<SearchUseFeeBasePtCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchUseFeeBasePtCd.get(i)+"");
					}
				}
				
				if(null!=SearchWmsStatus && 0<SearchWmsStatus.size()){
					for(int i=0;i<SearchWmsStatus.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchWmsStatus.get(i)+"");
					}
				}
				
				if(null!=SearchWmsShipDateStr && 0<SearchWmsShipDateStr.size()){
					for(int i=0;i<SearchWmsShipDateStr.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchWmsShipDateStr.get(i)+"");
					}
				}
				
				if(null!=SearchWmsShipDateEnd && 0<SearchWmsShipDateEnd.size()){
					for(int i=0;i<SearchWmsShipDateEnd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchWmsShipDateEnd.get(i)+"");
					}
				}
				
				if(null!=SearchCourseGpCd && 0<SearchCourseGpCd.size()){
					for(int i=0;i<SearchCourseGpCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchCourseGpCd.get(i)+"");
					}
				}
				
				if(null!=SearchCourseCD && 0<SearchCourseCD.size()){
					for(int i=0;i<SearchCourseCD.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchCourseCD.get(i)+"");
					}
				}
				
				if(null!=SearchCourseCDEda && 0<SearchCourseCDEda.size()){
					for(int i=0;i<SearchCourseCDEda.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchCourseCDEda.get(i)+"");
					}
				}
				
				if(null!=SearchPitGrp && 0<SearchPitGrp.size()){
					for(int i=0;i<SearchPitGrp.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchPitGrp.get(i)+"");
					}
				}
				
				if(null!=SearchPit && 0<SearchPit.size()){
					for(int i=0;i<SearchPit.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchPit.get(i)+"");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchPit.get(i)+"");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchPit.get(i)+"");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchPit.get(i)+"");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchPit.get(i)+"");
					}
				}
				
				if(null!=SearchMsItemCd && 0<SearchMsItemCd.size()){
					for(int i=0;i<SearchMsItemCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchMsItemCd.get(i)+"");
					}
				}
				
				if(null!=SearchMsItemName && 0<SearchMsItemName.size()){
					for(int i=0;i<SearchMsItemName.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchMsItemName.get(i)+"");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchMsItemName.get(i)+"");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchMsItemName.get(i)+"");
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
				
				if(null!=SearchMsCategoryCd && 0<SearchMsCategoryCd.size()){
					for(int i=0;i<SearchMsCategoryCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchMsCategoryCd.get(i)+"");
					}
				}
				
				if(null!=SearchMsCategoryName && 0<SearchMsCategoryName.size()){
					for(int i=0;i<SearchMsCategoryName.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchMsCategoryName.get(i)+"%");
					}
				}
				
				if(null!=SearchMsTildFG && 0<SearchMsTildFG.size()){
					for(int i=0;i<SearchMsTildFG.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchMsTildFG.get(i)+"");
					}
				}
				
				if(null!=SearchMsTildName && 0<SearchMsTildName.size()){
					for(int i=0;i<SearchMsTildName.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchMsTildName.get(i)+"%");
					}
				}
				
				if(null!=SearchMsLot && 0<SearchMsLot.size()){
					for(int i=0;i<SearchMsLot.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchMsLot.get(i)+"");
					}
				}
				
				if(null!=SearchMsExpDateStr && 0<SearchMsExpDateStr.size()){
					for(int i=0;i<SearchMsExpDateStr.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchMsExpDateStr.get(i)+"");
					}
				}
				
				if(null!=SearchMsExpDateEnd && 0<SearchMsExpDateEnd.size()){
					for(int i=0;i<SearchMsExpDateEnd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchMsExpDateEnd.get(i)+"");
					}
				}
				
				if(null!=SearchMsPackingType && 0<SearchMsPackingType.size()){
					for(int i=0;i<SearchMsPackingType.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchMsPackingType.get(i)+"");
					}
				}

				rset01 = stmt01.executeQuery();
				
				Rt = B100_RtObjectCreate.B100_RtObjectCreate(rset01,RtOkuriHdRt());
				
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