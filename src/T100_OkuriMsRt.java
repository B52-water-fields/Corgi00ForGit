import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class T100_OkuriMsRt{
	//出荷明細（各行にヘッダ情報展開）返却する
	/*
	コピペ用
	ArrayList<String> SearchInvoiceWHCD			= new ArrayList<String>();	//倉庫CD
	ArrayList<String> SearchClGpCD				= new ArrayList<String>();	//荷主グループCD
	ArrayList<String> SearchClCd				= new ArrayList<String>();	//荷主CD
	ArrayList<String> SearchOkuriNo				= new ArrayList<String>();	//送り状番号
	ArrayList<String> SearchClDeliNo			= new ArrayList<String>();	//荷主管理番号
	ArrayList<String> SearchPickupWhCd			= new ArrayList<String>();	//集荷倉庫CD
	ArrayList<String> SearchPurposeFG			= new ArrayList<String>();	//目的フラグ
	ArrayList<String> SearchPlanDateStr			= new ArrayList<String>();	//出荷予定日開始
	ArrayList<String> SearchShipDateStr			= new ArrayList<String>();	//出荷実績日開始
	ArrayList<String> SearchSPPlanDateStr		= new ArrayList<String>();	//着日指定開始
	ArrayList<String> SearchSPDateStr			= new ArrayList<String>();	//着日実績開始
	
	ArrayList<String> SearchPlanDateEnd			= new ArrayList<String>();	//出荷予定日終了
	ArrayList<String> SearchShipDateEnd			= new ArrayList<String>();	//出荷実績日終了
	ArrayList<String> SearchSPPlanDateEnd		= new ArrayList<String>();	//着日指定終了
	ArrayList<String> SearchSPDateEnd			= new ArrayList<String>();	//着日実績終了
	
	ArrayList<Float> SearchTotalWeightMin		= new ArrayList<Float>();	//荷物重量(kg)最小
	ArrayList<Float> SearchTotalSizeMin			= new ArrayList<Float>();	//荷物サイズ最小
	ArrayList<Integer> SearchTotalQtyMin		= new ArrayList<Integer>();	//個口数最小
	
	ArrayList<Float> SearchTotalWeightMax		= new ArrayList<Float>();	//荷物重量(kg)最大
	ArrayList<Float> SearchTotalSizeMax			= new ArrayList<Float>();	//荷物サイズ最大
	ArrayList<Integer> SearchTotalQtyMax		= new ArrayList<Integer>();	//個口数最大
	
	ArrayList<String> SearchDeliveryTypeCd01	= new ArrayList<String>();	//運送タイプ01
	ArrayList<String> SearchDeliveryTypeCd02	= new ArrayList<String>();	//運送タイプ02
	ArrayList<String> SearchDeliveryTypeCd03	= new ArrayList<String>();	//運送タイプ03
	ArrayList<String> SearchDeliveryTypeCd04	= new ArrayList<String>();	//運送タイプ04
	ArrayList<String> SearchDeliveryTypeCd05	= new ArrayList<String>();	//運送タイプ05
	
	ArrayList<Integer> SearchCodFG				= new ArrayList<Integer>();	//代引区分
	ArrayList<Integer> SearchCodPayTotalMin		= new ArrayList<Integer>();	//代引収受金額合計最小
	ArrayList<Integer> SearchCodPayTotalMax		= new ArrayList<Integer>();	//代引収受金額合計最大
	
	ArrayList<Integer> SearchChildrenFG			= new ArrayList<Integer>();	//子伝票区分
	ArrayList<String> SearchParentOkuriNo		= new ArrayList<String>();	//親伝票番号
	
	ArrayList<String> SearchNiokuriCd			= new ArrayList<String>();	//荷送人CD
	ArrayList<String> SearchNiokuriDepartmentCd	= new ArrayList<String>();	//荷送人部署CD
	ArrayList<String> SearchNiokuriName			= new ArrayList<String>();	//荷送人名称
	ArrayList<String> SearchNiokuriPost			= new ArrayList<String>();	//荷送人郵便番号
	ArrayList<String> SearchNiokuriAdd			= new ArrayList<String>();	//荷送人住所
	ArrayList<String> SearchNioKuriTel			= new ArrayList<String>();	//荷送人Tel
	ArrayList<String> SearchNioKuriFax			= new ArrayList<String>();	//荷送人Fax
	ArrayList<String> SearchNioKuriMail			= new ArrayList<String>();	//荷送人Mail
	ArrayList<String> SearchNiokuriMunicCd		= new ArrayList<String>();	//荷送人市区町村CD
	
	ArrayList<String> SearchDeliCd				= new ArrayList<String>();	//届先CD
	ArrayList<String> SearchClDeliCd			= new ArrayList<String>();	//荷主届先CD
	ArrayList<String> SearchDeliDepartmentCd	= new ArrayList<String>();	//届先部署CD
	ArrayList<String> SearchDeliName			= new ArrayList<String>();	//届先名称
	ArrayList<String> SearchDeliPost			= new ArrayList<String>();	//届先郵便番号
	ArrayList<String> SearchDeliAdd				= new ArrayList<String>();	//届先住所
	ArrayList<String> SearchDeliTel				= new ArrayList<String>();	//届先Tel
	ArrayList<String> SearchDeliFax				= new ArrayList<String>();	//届先Fax
	ArrayList<String> SearchDeliMail			= new ArrayList<String>();	//届先Mail
	ArrayList<String> SearchDeliMunicCd			= new ArrayList<String>();	//届先市区町村CD
	
	ArrayList<String> SearchCom					= new ArrayList<String>();	//コメント
	ArrayList<Integer> SearchStatus				= new ArrayList<Integer>();	//運送ステータス
	
	ArrayList<Integer> SearchFeeFixFG			= new ArrayList<Integer>();	//運賃確定フラグ
	ArrayList<Integer> SearchReceiptStampFG		= new ArrayList<Integer>();	//受領印フラグ
	ArrayList<Integer> SearchInvoiceStatus		= new ArrayList<Integer>();	//請求ステータス
	
	ArrayList<Integer> SearchWithOutTaxTotalMin	= new ArrayList<Integer>();	//税別運賃合計最小
	ArrayList<Integer> SearchTotalFeeMin		= new ArrayList<Integer>();	//税込運賃合計税込運賃合計
	ArrayList<String> SearchFeeFixDateStr		= new ArrayList<String>();	//運賃確定日時開始
	ArrayList<String> SearchReceiptStampDateStr	= new ArrayList<String>();	//受領印日時開始
	ArrayList<String> SearchEntryDateStr		= new ArrayList<String>();	//登録日終了
	ArrayList<String> SearchUpdateDateStr		= new ArrayList<String>();	//更新日終了
	
	ArrayList<Integer> SearchWithOutTaxTotalMax	= new ArrayList<Integer>();	//税別運賃合計最大
	ArrayList<Integer> SearchTotalFeeMax		= new ArrayList<Integer>();	//税込運賃合計最大
	ArrayList<String> SearchFeeFixDateEnd		= new ArrayList<String>();	//運賃確定日時終了
	ArrayList<String> SearchReceiptStampDateEnd	= new ArrayList<String>();	//受領印日時終了
	ArrayList<String> SearchEntryDateEnd		= new ArrayList<String>();	//登録日終了
	ArrayList<String> SearchUpdateDateEnd		= new ArrayList<String>();	//更新日終了
	
	ArrayList<String> SearchEntryUser			= new ArrayList<String>();	//登録者
	ArrayList<String> SearchUpdateUser			= new ArrayList<String>();	//更新者
	ArrayList<String> SearchEntryPG				= new ArrayList<String>();	//登録プログラム
	ArrayList<String> SearchUpdatePG			= new ArrayList<String>();	//更新プログラム
	ArrayList<String> SearchUseFeeBasePtCd		= new ArrayList<String>();	//運転計算タリフ
	ArrayList<Integer> SearchWmsStatus			= new ArrayList<Integer>();	//倉庫出荷ステータス
	ArrayList<String> SearchWmsShipDateStr		= new ArrayList<String>();	//倉庫出荷日時開始
	ArrayList<String> SearchWmsShipDateEnd		= new ArrayList<String>();	//倉庫出荷日時終了
	ArrayList<String> SearchCourseGpCd			= new ArrayList<String>();	//配車コースグループコード
	ArrayList<String> SearchCourseCD			= new ArrayList<String>();	//配車コースコード
	ArrayList<Integer> SearchCourseCDEda		= new ArrayList<Integer>();	//配車コースコード枝番
	ArrayList<String> SearchPitGrp				= new ArrayList<String>();	//荷物払出ピットグループ
	ArrayList<String> SearchPit					= new ArrayList<String>();	//荷物払出ピット
	
	ArrayList<String> SearchMsItemCd			= new ArrayList<String>();	//商品CD
	ArrayList<String> SearchMsItemName			= new ArrayList<String>();	//商品名
	
	ArrayList<String> SearchClItemCd			= new ArrayList<String>();	//荷主商品CD
	
	ArrayList<String> SearchMsCategoryCd		= new ArrayList<String>();	//カテゴリCD
	ArrayList<String> SearchMsCategoryName		= new ArrayList<String>();	//カテゴリ名
	ArrayList<String> SearchMsTildFG			= new ArrayList<String>();	//温度区分
	ArrayList<String> SearchMsTildName			= new ArrayList<String>();	//温度区分名
	
	ArrayList<String> SearchMsLot				= new ArrayList<String>();	//ロット指定
	ArrayList<String> SearchMsExpDateStr		= new ArrayList<String>();	//賞味期限指定開始
	ArrayList<String> SearchMsExpDateEnd		= new ArrayList<String>();	//賞味期限指定終了
	ArrayList<Integer> SearchMsPackingType		= new ArrayList<Integer>();	//荷姿タイプ
	
	boolean AllSearch = false;
	
	Object[][] OkuriMsRt	= T100_OkuriMsRt.OkuriMsRt(
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
	
		String GetClCd					= (String)OkuriMsRt[i][T100_OkuriMsRt.ColClCd];					//荷主コード
		String GetInvoiceWhCd			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColInvoiceWhCd];			//倉庫コード
		String GetOkuriNo				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColOkuriNo];				//送り状番号
		String GetClDeliNo				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColClDeliNo];				//荷主管理番号
		String GetPickupWhCd			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColPickupWhCd];			//集荷倉庫CD
		int GetPurposeFG				= (int)OkuriMsRt[i][T100_OkuriMsRt.ColPurposeFG];				//目的フラグ
		String GetPlanDate				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColPlanDate];				//出荷予定日
		String GetShipDate				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColShipDate];				/出荷実績日
		String GetSPPlanDate			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColSPPlanDate];			//着日指定
		String GetSPDate				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColSPDate];				//着日実績
		String GetSPTimeFG				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColSPTimeFG];				//時間指定区分
		String GetSPTimeStr				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColSPTimeStr];			//時間指定開始
		String GetSPTimeEnd				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColSPTimeEnd];			//時間指定終了
		float GetTotalWeight			= (float)OkuriMsRt[i][T100_OkuriMsRt.ColTotalWeight];			//荷物重量(kg)
		float GetTotalSize				= (float)OkuriMsRt[i][T100_OkuriMsRt.ColTotalSize];				//荷物サイズ
		int GetTotalQty					= (int)OkuriMsRt[i][T100_OkuriMsRt.ColTotalQty];				//個口数
		String GetDeliveryTypeCd		= (String)OkuriMsRt[i][T100_OkuriMsRt.ColDeliveryTypeCd01];		//運送タイプ01
		String GetDeliTypeName			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColDeliTypeName];			//運送タイプ名01
		String GetDeliveryTypeCd02		= (String)OkuriMsRt[i][T100_OkuriMsRt.ColDeliveryTypeCd02];		//運送タイプ02
		String GetDeliTypeName02		= (String)OkuriMsRt[i][T100_OkuriMsRt.ColDeliTypeName02];		//運送タイプ名02
		String GetDeliveryTypeCd03		= (String)OkuriMsRt[i][T100_OkuriMsRt.ColDeliveryTypeCd03];		//運送タイプ03
		String GetDeliTypeName03		= (String)OkuriMsRt[i][T100_OkuriMsRt.ColDeliTypeName03];		//運送タイプ名03
		String GetDeliveryTypeCd04		= (String)OkuriMsRt[i][T100_OkuriMsRt.ColDeliveryTypeCd04];		//運送タイプ04
		String GetDeliTypeName04		= (String)OkuriMsRt[i][T100_OkuriMsRt.ColDeliTypeName04];		//運送タイプ名04
		String GetDeliveryTypeCd05		= (String)OkuriMsRt[i][T100_OkuriMsRt.ColDeliveryTypeCd05];		//運送タイプ05
		String GetDeliTypeName05		= (String)OkuriMsRt[i][T100_OkuriMsRt.ColDeliTypeName05];		//運送タイプ名05
		int GetCodFG					= (int)OkuriMsRt[i][T100_OkuriMsRt.ColCodFG];					//代引フラグ
		int GetCodPayTotal				= (int)OkuriMsRt[i][T100_OkuriMsRt.ColCodPayTotal];				//代引収受金額合計
		int GetCodPay					= (int)OkuriMsRt[i][T100_OkuriMsRt.ColCodPay];					//代引金額
		int GetCodConsumptionTax		= (int)OkuriMsRt[i][T100_OkuriMsRt.ColCodConsumptionTax];		//代引消費税
		int GetChildrenFG				= (int)OkuriMsRt[i][T100_OkuriMsRt.ColChildrenFG];				//子伝票区分
		String GetParentOkuriNo			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColParentOkuriNo];		//親伝票番号
		String GetNiokuriCd				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColNiokuriCd];			//荷送人コード
		String GetNiokuriDepartmentCd	= (String)OkuriMsRt[i][T100_OkuriMsRt.ColNiokuriDepartmentCd];	//荷送人部署CD
		String GetNiokuriName01			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColNiokuriName01];		//荷送人名01
		String GetNiokuriName02			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColNiokuriName02];		//荷送人名02
		String GetNiokuriName03			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColNiokuriName03];		//荷送人名03
		String GetNiokuriPost			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColNiokuriPost];			//荷送人郵便番号
		String GetNiokuriAdd01			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColNiokuriAdd01];			//荷送人住所01
		String GetNiokuriAdd02			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColNiokuriAdd02];			//荷送人住所02
		String GetNiokuriAdd03			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColNiokuriAdd03];			//荷送人住所03
		String GetNioKuriTel			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColNioKuriTel];			//荷送人TEL
		String GetNioKuriFax			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColNioKuriFax];			//荷送人FAX
		String GetNioKuriMail			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColNioKuriMail];			//荷送人MAIL
		String GetNiokuriMunicCd		= (String)OkuriMsRt[i][T100_OkuriMsRt.ColNiokuriMunicCd];		//荷送人市区町村CD
		String GetDeliCd				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColDeliCd];				//荷届先コード
		String GetClDeliCd				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColClDeliCd];				//荷主荷届先コード
		String GetDeliDepartmentCd		= (String)OkuriMsRt[i][T100_OkuriMsRt.ColDeliDepartmentCd];		//部署CD
		String GetDeliName01			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColDeliName01];			//荷届先名01
		String GetDeliName02			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColDeliName02];			//荷届先名02
		String GetDeliName03			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColDeliName03];			//荷届先名03
		String GetDeliPost				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColDeliPost];				//荷届先郵便番号
		String GetDeliAdd01				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColDeliAdd01];			//荷届先住所01
		String GetDeliAdd02				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColDeliAdd02];			//荷届先住所02
		String GetDeliAdd03				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColDeliAdd03];			//荷届先住所03
		String GetDeliTel				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColDeliTel];				//荷届先TEL
		String GetDeliFax				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColDeliFax];				//荷届先FAX
		String GetDeliMail				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColDeliMail];				//荷届先MAIL
		String GetDeliMunicCd			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColDeliMunicCd];			//荷届先市区町村CD
		String GetCom01					= (String)OkuriMsRt[i][T100_OkuriMsRt.ColCom01];				//コメント01
		String GetCom02					= (String)OkuriMsRt[i][T100_OkuriMsRt.ColCom02];				//コメント02
		String GetCom03					= (String)OkuriMsRt[i][T100_OkuriMsRt.ColCom03];				//コメント03
		String GetCom04					= (String)OkuriMsRt[i][T100_OkuriMsRt.ColCom04];				//コメント04
		String GetCom05					= (String)OkuriMsRt[i][T100_OkuriMsRt.ColCom05];				//コメント05
		int GetStatus					= (int)OkuriMsRt[i][T100_OkuriMsRt.ColStatus];					//状況
		int GetTaxFg					= (int)OkuriMsRt[i][T100_OkuriMsRt.ColTaxFg];					//税区分
		int GetTaxRate					= (int)OkuriMsRt[i][T100_OkuriMsRt.ColTaxRate];					//税率
		int GetDeliFee					= (int)OkuriMsRt[i][T100_OkuriMsRt.ColDeliFee];					//運賃
		int GetAddDeliFee01				= (int)OkuriMsRt[i][T100_OkuriMsRt.ColAddDeliFee01];			//付帯費用1
		int GetAddDeliFee02				= (int)OkuriMsRt[i][T100_OkuriMsRt.ColAddDeliFee02];			//付帯費用2
		int GetAddDeliFee03				= (int)OkuriMsRt[i][T100_OkuriMsRt.ColAddDeliFee03];			//付帯費用3
		int GetHaighWayFee01			= (int)OkuriMsRt[i][T100_OkuriMsRt.ColHaighWayFee01];			//高速代等実費精算分1（内税）
		int GetHaighWayFee02			= (int)OkuriMsRt[i][T100_OkuriMsRt.ColHaighWayFee02];			//高速代等実費精算分2（内税）
		int GetConsumptionTax			= (int)OkuriMsRt[i][T100_OkuriMsRt.ColConsumptionTax];			//消費税
		int GetWithOutTaxTotal			= (int)OkuriMsRt[i][T100_OkuriMsRt.ColWithOutTaxTotal];			//税別合計金額
		int GetTotalFee					= (int)OkuriMsRt[i][T100_OkuriMsRt.ColTotalFee];				//税込請求額合計
		int GetFeeFixFG					= (int)OkuriMsRt[i][T100_OkuriMsRt.ColFeeFixFG];				//金額確定フラグ
		String GetFeeFixDate			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColFeeFixDate];			//金額確定日時
		int GetReceiptStampFG			= (int)OkuriMsRt[i][T100_OkuriMsRt.ColReceiptStampFG];			//受領印チェック
		String GetReceiptStampDate		= (String)OkuriMsRt[i][T100_OkuriMsRt.ColReceiptStampDate];		//受領印日時
		int GetInvoiceStatus			= (int)OkuriMsRt[i][T100_OkuriMsRt.ColInvoiceStatus];			//請求ステータス
		String GetEntryDate				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColEntryDate];			//登録日
		String GetUpdateDate			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColUpdateDate];			//更新日
		String GetEntryUser				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColEntryUser];			//登録者
		String GetUpdateUser			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColUpdateUser];			//更新者
		String GetEntryPG				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColEntryPG];				//登録プログラム
		String GetUpdatePG				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColUpdatePG];				//更新プログラム
		String GetUseFeeBasePtCd		= (String)OkuriMsRt[i][T100_OkuriMsRt.ColUseFeeBasePtCd];		//適用運賃タリフCD
		int GetWmsStatus				= (int)OkuriMsRt[i][T100_OkuriMsRt.ColWmsStatus];				//在庫管理ステータス
		String GetWmsShipDate			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColWmsShipDate];			//倉庫出荷日
		String GetCourseGpCd			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColCourseGpCd];			//コースグループコード
		String GetCourseCD				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColCourseCD];				//一次配車コースコード
		int GetCourseCDEda				= (int)OkuriMsRt[i][T100_OkuriMsRt.ColCourseCDEda];				//一次配車コースコード枝番
		String GetPitGrp				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColPitGrp];				//一次配車払出ピットグループ
		String GetPit01					= (String)OkuriMsRt[i][T100_OkuriMsRt.ColPit01];				//一次配車払出ピット01
		String GetPit02					= (String)OkuriMsRt[i][T100_OkuriMsRt.ColPit02];				//一次配車払出ピット02
		String GetPit03					= (String)OkuriMsRt[i][T100_OkuriMsRt.ColPit03];				//一次配車払出ピット03
		String GetPit04					= (String)OkuriMsRt[i][T100_OkuriMsRt.ColPit04];				//一次配車払出ピット04
		String GetPit05					= (String)OkuriMsRt[i][T100_OkuriMsRt.ColPit05];				//一次配車払出ピット05

		String GetMsClCd				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColMsClCd];				//明細荷主コード
		String GetMsInvoiceWhCd			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColMsInvoiceWhCd];		//明細倉庫コード
		String GetMsOkuriNo				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColMsOkuriNo];			//明細送り状番号
		int GetMsNo						= (int)OkuriMsRt[i][T100_OkuriMsRt.ColMsNo];					//明細番号
		String GetMsDeliNo				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColMsDeliNo];				//明細出荷番号
		int GetMsDelliMsNo				= (int)OkuriMsRt[i][T100_OkuriMsRt.ColMsDelliMsNo];				//明細出荷番号明細番号
		String GetMsClOrderNo			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColMsClOrderNo];			//明細荷主管理番号
		String GetMsClGpCd				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColMsClGpCd];				//明細荷主グループコード
		String GetMsItemCd				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColMsItemCd];				//明細商品コード
		String GetMsItemName01			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColMsItemName01];			//明細商品表記名
		String GetMsItemName02			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColMsItemName02];			//明細商品正式名
		String GetMsItemName03			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColMsItemName03];			//明細商品略名
		float GetMsUnitWeight			= (float)OkuriMsRt[i][T100_OkuriMsRt.ColMsUnitWeight];			//明細単位重量
		float GetMsUnitSize				= (float)OkuriMsRt[i][T100_OkuriMsRt.ColMsUnitSize];			//明細単位サイズ
		int GetMsQty					= (int)OkuriMsRt[i][T100_OkuriMsRt.ColMsQty];					//明細個数
		int GetMsPackingQty				= (int)OkuriMsRt[i][T100_OkuriMsRt.ColMsPackingQty];			//明細荷姿数量
		String GetMsUnitName			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColMsUnitName];			//明細明細単位
		float GetMsSubTotalWeight		= (float)OkuriMsRt[i][T100_OkuriMsRt.ColMsSubTotalWeight];		//明細明細重量
		float GetMsSubTotalSize			= (float)OkuriMsRt[i][T100_OkuriMsRt.ColMsSubTotalSize];		//明細明細サイズ
		float GetMsUnitPrice			= (float)OkuriMsRt[i][T100_OkuriMsRt.ColMsUnitPrice];			//明細単価
		float GetMsSubTotalPrice		= (float)OkuriMsRt[i][T100_OkuriMsRt.ColMsSubTotalPrice];		//明細金額
		String GetMsCategoryCd			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColMsCategoryCd];			//明細商品分類
		String GetMsCategoryName		= (String)OkuriMsRt[i][T100_OkuriMsRt.ColMsCategoryName];		//明細商品分類名
		String GetMsTildFG				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColMsTildFG];				//明細温度区分
		String GetMsTildName			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColMsTildName];			//明細温度区分名
		String GetMsCom01				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColMsCom01];				//明細コメント01
		String GetMsCom02				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColMsCom02];				//明細コメント02
		String GetMsCom03				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColMsCom03];				//明細コメント03
		String GetMsCom04				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColMsCom04];				//明細コメント04
		String GetMsCom05				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColMsCom05];				//明細コメント05
		String GetMsEntryDate			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColMsEntryDate];			//明細登録日
		String GetMsUpdateDate			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColMsUpdateDate];			//明細更新日
		String GetMsEntryUser			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColMsEntryUser];			//明細登録者
		String GetMsUpdateUser			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColMsUpdateUser];			//明細更新者
		String GetMsLot					= (String)OkuriMsRt[i][T100_OkuriMsRt.ColMsLot];				//明細ロット指定
		String GetMsExpDate				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColMsExpDate];			//明細賞味期限指定
		int GetMsPackingType			= (int)OkuriMsRt[i][T100_OkuriMsRt.ColMsPackingType];			//明細荷姿タイプ
		String GetMsClItemCd			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColMsClItemCd];			//明細荷主商品CD
		String GetMsItemMDNo			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColMsItemMDNo];			//明細型番
		String GetMsJanCd				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColMsJanCd];				//明細荷姿JanCd

		String GetCLName01				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColCLName01];				//荷主名
		String GetClGpCD				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColClGpCD];				//荷主グループCD
		String GetCLGpName01			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColCLGpName01];			//荷主グループ標記名
	*/
	
	//戻り値カラム
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
	static final int ColDeliveryTypeCd01		=  16;	//運送タイプ01
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
	static final int ColNiokuriCd				=  32;	//荷送人コード
	static final int ColNiokuriDepartmentCd	=  33;	//部署CD
	static final int ColNiokuriName01			=  34;	//荷送人名01
	static final int ColNiokuriName02			=  35;	//荷送人名02
	static final int ColNiokuriName03			=  36;	//荷送人名03
	static final int ColNiokuriPost			=  37;	//荷送人郵便番号
	static final int ColNiokuriAdd01			=  38;	//荷送人住所01
	static final int ColNiokuriAdd02			=  39;	//荷送人住所02
	static final int ColNiokuriAdd03			=  40;	//荷送人住所03
	static final int ColNioKuriTel			=  41;	//荷送人TEL
	static final int ColNioKuriFax			=  42;	//荷送人FAX
	static final int ColNioKuriMail			=  43;	//荷送人MAIL
	static final int ColNiokuriMunicCd		=  44;	//荷送人市区町村CD
	static final int ColDeliCd					=  45;	//荷届先コード
	static final int ColClDeliCd				=  46;	//荷主荷届先コード
	static final int ColDeliDepartmentCd		=  47;	//部署CD
	static final int ColDeliName01			=  48;	//荷届先名01
	static final int ColDeliName02			=  49;	//荷届先名02
	static final int ColDeliName03			=  50;	//荷届先名03
	static final int ColDeliPost				=  51;	//荷届先郵便番号
	static final int ColDeliAdd01				=  52;	//荷届先住所01
	static final int ColDeliAdd02				=  53;	//荷届先住所02
	static final int ColDeliAdd03				=  54;	//荷届先住所03
	static final int ColDeliTel				=  55;	//荷届先TEL
	static final int ColDeliFax				=  56;	//荷届先FAX
	static final int ColDeliMail				=  57;	//荷届先MAIL
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
	
	//検索値カラム
	static final int ColSearchInvoiceWHCD			=   0;
	static final int ColSearchClGpCD					=   1;
	static final int ColSearchClCd					=   2;
	static final int ColSearchOkuriNo					=   3;
	static final int ColSearchClDeliNo				=   4;
	static final int ColSearchPickupWhCd				=   5;
	static final int ColSearchPurposeFG				=   6;
	static final int ColSearchPlanDateStr			=   7;
	static final int ColSearchShipDateStr			=   8;
	static final int ColSearchSPPlanDateStr			=   9;
	static final int ColSearchSPDateStr				=  10;
		
	static final int ColSearchPlanDateEnd			=  11;
	static final int ColSearchShipDateEnd			=  12;
	static final int ColSearchSPPlanDateEnd			=  13;
	static final int ColSearchSPDateEnd				=  14;
		
	static final int ColSearchTotalWeightMin			=  15;
	static final int ColSearchTotalSizeMin			=  16;
	static final int ColSearchTotalQtyMin			=  17;
		
	static final int ColSearchTotalWeightMax			=  18;
	static final int ColSearchTotalSizeMax			=  19;
	static final int ColSearchTotalQtyMax			=  20;
		
	static final int ColSearchDeliveryTypeCd01		=  21;
	static final int ColSearchDeliveryTypeCd02		=  22;
	static final int ColSearchDeliveryTypeCd03		=  23;
	static final int ColSearchDeliveryTypeCd04		=  24;
	static final int ColSearchDeliveryTypeCd05		=  25;
	
	static final int ColSearchCodFG					=  26;
	static final int ColSearchCodPayTotalMin			=  27;
	static final int ColSearchCodPayTotalMax			=  28;
		
	static final int ColSearchChildrenFG				=  29;
	static final int ColSearchParentOkuriNo			=  30;
		
	static final int ColSearchNiokuriCd				=  31;
	static final int ColSearchNiokuriDepartmentCd	=  32;
	static final int ColSearchNiokuriName			=  33;
	static final int ColSearchNiokuriPost			=  34;
	static final int ColSearchNiokuriAdd				=  35;
	static final int ColSearchNioKuriTel				=  36;
	static final int ColSearchNioKuriFax				=  37;
	static final int ColSearchNioKuriMail			=  38;
	static final int ColSearchNiokuriMunicCd			=  39;
		
	static final int ColSearchDeliCd					=  40;
	static final int ColSearchClDeliCd				=  41;
	static final int ColSearchDeliDepartmentCd		=  42;
	static final int ColSearchDeliName				=  43;
	static final int ColSearchDeliPost				=  44;
	static final int ColSearchDeliAdd					=  45;
	static final int ColSearchDeliTel					=  46;
	static final int ColSearchDeliFax					=  47;
	static final int ColSearchDeliMail				=  48;
	static final int ColSearchDeliMunicCd			=  49;
		
	static final int ColSearchCom						=  50;
	static final int ColSearchStatus					=  51;
		
	static final int ColSearchFeeFixFG				=  52;
	static final int ColSearchReceiptStampFG			=  53;
	static final int ColSearchInvoiceStatus			=  54;
		
	static final int ColSearchWithOutTaxTotalMin	=  55;
	static final int ColSearchTotalFeeMin			=  56;
	static final int ColSearchFeeFixDateStr			=  57;
	static final int ColSearchReceiptStampDateStr	=  58;
	static final int ColSearchEntryDateStr			=  59;
	static final int ColSearchUpdateDateStr			=  60;
		
	static final int ColSearchWithOutTaxTotalMax	=  61;
	static final int ColSearchTotalFeeMax			=  62;
	static final int ColSearchFeeFixDateEnd			=  63;
	static final int ColSearchReceiptStampDateEnd	=  64;
	static final int ColSearchEntryDateEnd			=  65;
	static final int ColSearchUpdateDateEnd			=  66;
		
	static final int ColSearchEntryUser				=  67;
	static final int ColSearchUpdateUser				=  68;
	static final int ColSearchEntryPG					=  69;
	static final int ColSearchUpdatePG				=  70;
	static final int ColSearchUseFeeBasePtCd			=  71;
	static final int ColSearchWmsStatus				=  72;
	static final int ColSearchWmsShipDateStr			=  73;
	static final int ColSearchWmsShipDateEnd			=  74;
	static final int ColSearchCourseGpCd				=  75;
	static final int ColSearchCourseCD				=  76;
	static final int ColSearchCourseCDEda			=  77;
	static final int ColSearchPitGrp					=  78;
	static final int ColSearchPit						=  79;
		
	static final int ColSearchMsItemCd				=  80;
	static final int ColSearchMsItemName				=  81;
		
	static final int ColSearchClItemCd				=  82;
			
	static final int ColSearchMsCategoryCd			=  83;
	static final int ColSearchMsCategoryName			=  84;
	static final int ColSearchMsTildFG				=  85;
	static final int ColSearchMsTildName				=  86;
		
	static final int ColSearchMsLot					=  87;
	static final int ColSearchMsExpDateStr			=  88;
	static final int ColSearchMsExpDateEnd			=  89;
	static final int ColSearchMsPackingType			=  90;
	
	public static Object[][] RtOkuriMsRt(){
		Object[][] Rt = {
						 {"ClCd"				,ColClCd					,"String"	,"荷主コード"					,"Key"	,"Client Code"							,"货主代码"}
						,{"InvoiceWhCd"			,ColInvoiceWhCd			,"String"	,"倉庫コード"					,"Key"	,"Warehouse Code"						,"仓库代码"}
						,{"OkuriNo"				,ColOkuriNo				,"String"	,"送り状番号"					,"Key"	,"Waybill No."							,"运单号"}
						,{"ClDeliNo"			,ColClDeliNo				,"String"	,"荷主管理番号"					,""		,"Client Reference No."					,"货主管理编号"}
						,{"PickupWhCd"			,ColPickupWhCd			,"String"	,"集荷倉庫CD"					,""		,"Pickup Warehouse Code"				,"提货仓库代码"}
						,{"PurposeFG"			,ColPurposeFG				,"int"		,"目的フラグ"					,""		,"Purpose Flag"							,"目的标志"}
						,{"PlanDate"			,ColPlanDate				,"Date"		,"出荷予定日"					,""		,"Planned Ship Date"					,"计划出库日期"}
						,{"ShipDate"			,ColShipDate				,"DateTime"	,"出荷実績日"					,""		,"Actual Ship Date"						,"实际出库日期"}
						,{"SPPlanDate"			,ColSPPlanDate			,"Date"		,"着日指定"						,""		,"Requested Delivery Date"				,"指定到货日期"}
						,{"SPDate"				,ColSPDate					,"DateTime"	,"着日実績"						,""		,"Actual Delivery Date"					,"实际到货日期"}
						,{"SPTimeFG"			,ColSPTimeFG				,"String"	,"時間指定区分"					,""		,"Delivery Time Type"					,"指定时间类型"}
						,{"SPTimeStr"			,ColSPTimeStr				,"String"	,"時間指定開始"					,""		,"Delivery Time From"					,"指定时间开始"}
						,{"SPTimeEnd"			,ColSPTimeEnd				,"String"	,"時間指定終了"					,""		,"Delivery Time To"						,"指定时间结束"}
						,{"TotalWeight"			,ColTotalWeight			,"float"	,"荷物重量(kg)"					,""		,"Total Weight (kg)"					,"货物重量(kg)"}
						,{"TotalSize"			,ColTotalSize				,"float"	,"荷物サイズ"					,""		,"Total Size"							,"货物尺寸"}
						,{"TotalQty"			,ColTotalQty				,"int"		,"個口数"						,""		,"Package Qty"							,"包裹数量"}
						,{"DeliveryTypeCd01"	,ColDeliveryTypeCd01		,"String"	,"運送タイプ01"					,""		,"Delivery Type 01"						,"运输类型01"}
						,{"DeliTypeName"		,ColDeliTypeName			,"String"	,"運送タイプ名01"				,""		,"Delivery Type Name 01"				,"运输类型名称01"}
						,{"DeliveryTypeCd02"	,ColDeliveryTypeCd02		,"String"	,"運送タイプ02"					,""		,"Delivery Type 02"						,"运输类型02"}
						,{"DeliTypeName02"		,ColDeliTypeName02		,"String"	,"運送タイプ名02"				,""		,"Delivery Type Name 02"				,"运输类型名称02"}
						,{"DeliveryTypeCd03"	,ColDeliveryTypeCd03		,"String"	,"運送タイプ03"					,""		,"Delivery Type 03"						,"运输类型03"}
						,{"DeliTypeName03"		,ColDeliTypeName03		,"String"	,"運送タイプ名03"				,""		,"Delivery Type Name 03"				,"运输类型名称03"}
						,{"DeliveryTypeCd04"	,ColDeliveryTypeCd04		,"String"	,"運送タイプ04"					,""		,"Delivery Type 04"						,"运输类型04"}
						,{"DeliTypeName04"		,ColDeliTypeName04		,"String"	,"運送タイプ名04"				,""		,"Delivery Type Name 04"				,"运输类型名称04"}
						,{"DeliveryTypeCd05"	,ColDeliveryTypeCd05		,"String"	,"運送タイプ05"					,""		,"Delivery Type 05"						,"运输类型05"}
						,{"DeliTypeName05"		,ColDeliTypeName05		,"String"	,"運送タイプ名05"				,""		,"Delivery Type Name 05"				,"运输类型名称05"}
	
						,{"CodFG"				,ColCodFG					,"int"		,"代引フラグ"					,""		,"COD Flag"								,"货到付款标志"}
						,{"CodPayTotal"			,ColCodPayTotal			,"int"		,"代引収受金額合計"				,""		,"COD Collection Total"					,"货到付款收款总额"}
						,{"CodPay"				,ColCodPay					,"int"		,"代引金額"						,""		,"COD Amount"							,"货到付款金额"}
						,{"CodConsumptionTax"	,ColCodConsumptionTax	,"int"		,"代引消費税"					,""		,"COD Consumption Tax"					,"货到付款消费税"}
	
						,{"ChildrenFG"			,ColChildrenFG			,"int"		,"子伝票区分"					,""		,"Child Waybill Type"					,"子运单类型"}
						,{"ParentOkuriNo"		,ColParentOkuriNo			,"String"	,"親伝票番号"					,""		,"Parent Waybill No."					,"父运单号"}
	
						,{"NiokuriCd"			,ColNiokuriCd				,"String"	,"荷送人コード"					,""		,"Shipper Code"							,"发货人代码"}
						,{"NiokuriDepartmentCd"	,ColNiokuriDepartmentCd	,"String"	,"荷送人部署CD"					,""		,"Shipper Department Code"				,"发货人部门代码"}
						,{"NiokuriName01"		,ColNiokuriName01			,"String"	,"荷送人名01"					,""		,"Shipper Name 01"						,"发货人名称01"}
						,{"NiokuriName02"		,ColNiokuriName02			,"String"	,"荷送人名02"					,""		,"Shipper Name 02"						,"发货人名称02"}
						,{"NiokuriName03"		,ColNiokuriName03			,"String"	,"荷送人名03"					,""		,"Shipper Name 03"						,"发货人名称03"}
						,{"NiokuriPost"			,ColNiokuriPost			,"String"	,"荷送人郵便番号"				,""		,"Shipper Postal Code"					,"发货人邮政编码"}
						,{"NiokuriAdd01"		,ColNiokuriAdd01			,"String"	,"荷送人住所01"					,""		,"Shipper Address 01"					,"发货人地址01"}
						,{"NiokuriAdd02"		,ColNiokuriAdd02			,"String"	,"荷送人住所02"					,""		,"Shipper Address 02"					,"发货人地址02"}
						,{"NiokuriAdd03"		,ColNiokuriAdd03			,"String"	,"荷送人住所03"					,""		,"Shipper Address 03"					,"发货人地址03"}
						,{"NioKuriTel"			,ColNioKuriTel			,"String"	,"荷送人TEL"					,""		,"Shipper TEL"							,"发货人电话"}
						,{"NioKuriFax"			,ColNioKuriFax			,"String"	,"荷送人FAX"					,""		,"Shipper FAX"							,"发货人传真"}
						,{"NioKuriMail"			,ColNioKuriMail			,"String"	,"荷送人MAIL"					,""		,"Shipper Email"						,"发货人邮箱"}
						,{"NiokuriMunicCd"		,ColNiokuriMunicCd		,"String"	,"荷送人市区町村CD"				,""		,"Shipper Municipality Code"			,"发货人市区町村代码"}
	
						,{"DeliCd"				,ColDeliCd					,"String"	,"荷届先コード"					,""		,"Delivery Destination Code"			,"收货地代码"}
						,{"ClDeliCd"			,ColClDeliCd				,"String"	,"荷主荷届先コード"				,""		,"Client Destination Code"				,"货主收货地代码"}
						,{"DeliDepartmentCd"	,ColDeliDepartmentCd		,"String"	,"部署CD"						,""		,"Destination Department Code"			,"收货地部门代码"}
						,{"DeliName01"			,ColDeliName01			,"String"	,"荷届先名01"					,""		,"Destination Name 01"					,"收货地名称01"}
						,{"DeliName02"			,ColDeliName02			,"String"	,"荷届先名02"					,""		,"Destination Name 02"					,"收货地名称02"}
						,{"DeliName03"			,ColDeliName03			,"String"	,"荷届先名03"					,""		,"Destination Name 03"					,"收货地名称03"}
						,{"DeliPost"			,ColDeliPost				,"String"	,"荷届先郵便番号"				,""		,"Destination Postal Code"				,"收货地邮政编码"}
						,{"DeliAdd01"			,ColDeliAdd01				,"String"	,"荷届先住所01"					,""		,"Destination Address 01"				,"收货地地址01"}
						,{"DeliAdd02"			,ColDeliAdd02				,"String"	,"荷届先住所02"					,""		,"Destination Address 02"				,"收货地地址02"}
						,{"DeliAdd03"			,ColDeliAdd03				,"String"	,"荷届先住所03"					,""		,"Destination Address 03"				,"收货地地址03"}
						,{"DeliTel"				,ColDeliTel				,"String"	,"荷届先TEL"					,""		,"Destination TEL"						,"收货地电话"}
						,{"DeliFax"				,ColDeliFax				,"String"	,"荷届先FAX"					,""		,"Destination FAX"						,"收货地传真"}
						,{"DeliMail"			,ColDeliMail				,"String"	,"荷届先MAIL"					,""		,"Destination Email"					,"收货地邮箱"}
						,{"DeliMunicCd"			,ColDeliMunicCd			,"String"	,"荷届先市区町村CD"				,""		,"Destination Municipality Code"		,"收货地市区町村代码"}
	
						,{"Com01"				,ColCom01					,"String"	,"コメント01"					,""		,"Comment 01"							,"备注01"}
						,{"Com02"				,ColCom02					,"String"	,"コメント02"					,""		,"Comment 02"							,"备注02"}
						,{"Com03"				,ColCom03					,"String"	,"コメント03"					,""		,"Comment 03"							,"备注03"}
						,{"Com04"				,ColCom04					,"String"	,"コメント04"					,""		,"Comment 04"							,"备注04"}
						,{"Com05"				,ColCom05					,"String"	,"コメント05"					,""		,"Comment 05"							,"备注05"}
	
						,{"Status"				,ColStatus					,"int"		,"運送状況"						,""		,"Status"								,"状态"}
						,{"TaxFg"				,ColTaxFg					,"int"		,"税区分"						,""		,"Tax Type"								,"税类型"}
						,{"TaxRate"				,ColTaxRate				,"int"		,"税率"							,""		,"Tax Rate"								,"税率"}
						,{"DeliFee"				,ColDeliFee				,"int"		,"運賃"							,""		,"Freight Charge"						,"运费"}
						,{"AddDeliFee01"		,ColAddDeliFee01			,"int"		,"付帯費用1"					,""		,"Additional Charge 1"					,"附加费用1"}
						,{"AddDeliFee02"		,ColAddDeliFee02			,"int"		,"付帯費用2"					,""		,"Additional Charge 2"					,"附加费用2"}
						,{"AddDeliFee03"		,ColAddDeliFee03			,"int"		,"付帯費用3"					,""		,"Additional Charge 3"					,"附加费用3"}
						,{"HaighWayFee01"		,ColHaighWayFee01			,"int"		,"高速代等実費精算分1（内税）"	,""		,"Highway etc. Actual Cost 1 (Tax Included)"	,"高速费等实报实销1（含税）"}
						,{"HaighWayFee02"		,ColHaighWayFee02			,"int"		,"高速代等実費精算分2（内税）"	,""		,"Highway etc. Actual Cost 2 (Tax Included)"	,"高速费等实报实销2（含税）"}
						,{"ConsumptionTax"		,ColConsumptionTax		,"int"		,"消費税"						,""		,"Consumption Tax"						,"消费税"}
						,{"WithOutTaxTotal"		,ColWithOutTaxTotal		,"int"		,"税別合計金額"					,""		,"Total Excluding Tax"					,"未税合计金额"}
						,{"TotalFee"			,ColTotalFee				,"int"		,"税込請求額合計"				,""		,"Total Including Tax"					,"含税账单总额"}
						,{"FeeFixFG"			,ColFeeFixFG				,"int"		,"金額確定フラグ"				,""		,"Charge Fixed Flag"					,"金额确定标志"}
						,{"FeeFixDate"			,ColFeeFixDate			,"DateTime"	,"金額確定日時"					,""		,"Charge Fixed At"						,"金额确定时间"}
						,{"ReceiptStampFG"		,ColReceiptStampFG		,"int"		,"受領印チェック"				,""		,"Receipt Stamp Check"					,"收货章确认"}
						,{"ReceiptStampDate"	,ColReceiptStampDate		,"DateTime"	,"受領印日時"					,""		,"Receipt Stamp Date"					,"收货章日期"}
						,{"InvoiceStatus"		,ColInvoiceStatus			,"int"		,"請求ステータス"				,""		,"Invoice Status"						,"账单状态"}
						,{"EntryDate"			,ColEntryDate				,"DateTime"	,"登録日"						,""		,"Created At"							,"登记时间"}
						,{"UpdateDate"			,ColUpdateDate			,"DateTime"	,"更新日"						,""		,"Updated At"							,"更新时间"}
						,{"EntryUser"			,ColEntryUser				,"String"	,"登録者"						,""		,"Created By"							,"登记人"}
						,{"UpdateUser"			,ColUpdateUser			,"String"	,"更新者"						,""		,"Updated By"							,"更新人"}
						,{"EntryPG"				,ColEntryPG				,"String"	,"登録プログラム"				,""		,"Created Program"						,"登记程序"}
						,{"UpdatePG"			,ColUpdatePG				,"String"	,"更新プログラム"				,""		,"Updated Program"						,"更新程序"}
	
						,{"UseFeeBasePtCd"		,ColUseFeeBasePtCd		,"String"	,"適用運賃タリフCD"				,""		,"Applied Freight Tariff Code"			,"适用运费费率代码"}
						,{"WmsStatus"			,ColWmsStatus				,"int"		,"在庫管理ステータス"			,""		,"WMS Status"							,"库存管理状态"}
						,{"WmsShipDate"			,ColWmsShipDate			,"DateTime"	,"倉庫出荷日"					,""		,"Warehouse Ship Date"					,"仓库出库日期"}
						,{"CourseGpCd"			,ColCourseGpCd			,"String"	,"コースグループコード"			,""		,"Route Group Code"						,"配送路线组代码"}
						,{"CourseCD"			,ColCourseCD				,"String"	,"一次配車コースコード"			,""		,"Primary Route Code"					,"一次配车路线代码"}
						,{"CourseCDEda"			,ColCourseCDEda			,"int"		,"一次配車コースコード枝番"		,""		,"Primary Route Code Branch"			,"一次配车路线代码分支"}
						,{"PitGrp"				,ColPitGrp					,"String"	,"一次配車払出ピットグループ"	,""		,"Primary Dispatch Pit Group"			,"一次配车出库口组"}
						,{"Pit01"				,ColPit01					,"String"	,"一次配車払出ピット01"			,""		,"Primary Dispatch Pit 01"				,"一次配车出库口01"}
						,{"Pit02"				,ColPit02					,"String"	,"一次配車払出ピット02"			,""		,"Primary Dispatch Pit 02"				,"一次配车出库口02"}
						,{"Pit03"				,ColPit03					,"String"	,"一次配車払出ピット03"			,""		,"Primary Dispatch Pit 03"				,"一次配车出库口03"}
						,{"Pit04"				,ColPit04					,"String"	,"一次配車払出ピット04"			,""		,"Primary Dispatch Pit 04"				,"一次配车出库口04"}
						,{"Pit05"				,ColPit05					,"String"	,"一次配車払出ピット05"			,""		,"Primary Dispatch Pit 05"				,"一次配车出库口05"}
	
						,{"CLName01"			,ColCLName01				,"String"	,"荷主名"						,""		,"Client Name"							,"货主名称"}
						,{"ClGpCD"				,ColClGpCD					,"String"	,"荷主グループCD"				,""		,"Client Group Code"					,"货主组代码"}
						,{"CLGpName01"			,ColCLGpName01			,"String"	,"荷主グループ標記名"			,""		,"Client Group Display Name"			,"货主组显示名称"}
						
						
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
						};
		Rt = B100_LanguageControl.RtControl(Rt);
		
		return Rt;
	}
	public static Object[][] DefinitionRt(){
		Object[][] Definition = {
				 {"String"		,null,	"Exact"		,ColSearchInvoiceWHCD			,B100_DefaultVariable.SearchWhList				,"倉庫CD"				,""		,"Warehouse Code"				,""		,"仓库代码"				,""}
				,{"String"		,null,	"Exact"		,ColSearchClGpCD					,B100_DefaultVariable.SearchClGpList				,"荷主グループCD"		,""		,"Client Group Code"			,""		,"货主组代码"				,""}
				,{"String"		,null,	"Exact"		,ColSearchClCd					,B100_DefaultVariable.SearchClList				,"荷主CD"				,""		,"Client Code"					,""		,"货主代码"				,""}
				,{"String"		,null,	"Exact"		,ColSearchOkuriNo					,""													,"送り状番号"			,""		,"Waybill No."					,""		,"运单号"					,""}
				,{"String"		,null,	"Exact"		,ColSearchClDeliNo				,""													,"荷主管理番号"			,""		,"Client Reference No."			,""		,"货主管理编号"			,""}
				,{"String"		,null,	"Exact"		,ColSearchPickupWhCd				,B100_DefaultVariable.SearchWhList				,"集荷倉庫CD"			,""		,"Pickup Warehouse Code"		,""		,"提货仓库代码"			,""}
				,{"String"		,null,	"Exact"		,ColSearchPurposeFG				,B100_DefaultVariable.SearchPurposeList			,"目的フラグ"			,""		,"Purpose Flag"					,""		,"目的标志"				,""}
				,{"Date"		,null,	"RangeStr"	,ColSearchPlanDateStr			,""													,"出荷予定日"			,"開始"	,"Planned Ship Date"			,"From"	,"计划出库日期"			,"开始"}
				,{"Date"		,null,	"RangeStr"	,ColSearchShipDateStr			,""													,"出荷実績日"			,"開始"	,"Actual Ship Date"				,"From"	,"实际出库日期"			,"开始"}
				,{"Date"		,null,	"RangeStr"	,ColSearchSPPlanDateStr			,""													,"着日指定"				,"開始"	,"Requested Delivery Date"		,"From"	,"指定到货日期"			,"开始"}
				,{"Date"		,null,	"RangeStr"	,ColSearchSPDateStr				,""													,"着日実績"				,"開始"	,"Actual Delivery Date"			,"From"	,"实际到货日期"			,"开始"}

				,{"Date"		,null,	"RangeEnd"	,ColSearchPlanDateEnd			,""													,"出荷予定日"			,"終了"	,"Planned Ship Date"			,"To"	,"计划出库日期"			,"结束"}
				,{"Date"		,null,	"RangeEnd"	,ColSearchShipDateEnd			,""													,"出荷実績日"			,"終了"	,"Actual Ship Date"				,"To"	,"实际出库日期"			,"结束"}
				,{"Date"		,null,	"RangeEnd"	,ColSearchSPPlanDateEnd			,""													,"着日指定"				,"終了"	,"Requested Delivery Date"		,"To"	,"指定到货日期"			,"结束"}
				,{"Date"		,null,	"RangeEnd"	,ColSearchSPDateEnd				,""													,"着日実績"				,"終了"	,"Actual Delivery Date"			,"To"	,"实际到货日期"			,"结束"}

				,{"Float"		,null,	"RangeMin"	,ColSearchTotalWeightMin			,""													,"荷物重量(kg)"			,"最小"	,"Total Weight (kg)"			,"Min"	,"货物重量(kg)"			,"最小"}
				,{"Float"		,null,	"RangeMin"	,ColSearchTotalSizeMin			,""													,"荷物サイズ"			,"最小"	,"Total Size"					,"Min"	,"货物尺寸"				,"最小"}
				,{"Integer"		,null,	"RangeMin"	,ColSearchTotalQtyMin			,""													,"個口数"				,"最小"	,"Package Qty"					,"Min"	,"包裹数量"				,"最小"}

				,{"Float"		,null,	"RangeMax"	,ColSearchTotalWeightMax			,""													,"荷物重量(kg)"			,"最大"	,"Total Weight (kg)"			,"Max"	,"货物重量(kg)"			,"最大"}
				,{"Float"		,null,	"RangeMax"	,ColSearchTotalSizeMax			,""													,"荷物サイズ"			,"最大"	,"Total Size"					,"Max"	,"货物尺寸"				,"最大"}
				,{"Integer"		,null,	"RangeMax"	,ColSearchTotalQtyMax			,""													,"個口数"				,"最大"	,"Package Qty"					,"Max"	,"包裹数量"				,"最大"}

				,{"String"		,null,	"Exact"		,ColSearchDeliveryTypeCd01		,B100_DefaultVariable.SearchDeliveryType01		,"運送タイプ01"			,""		,"Delivery Type 01"				,""		,"运输类型01"				,""}
				,{"String"		,null,	"Exact"		,ColSearchDeliveryTypeCd02		,B100_DefaultVariable.SearchDeliveryType02		,"運送タイプ02"			,""		,"Delivery Type 02"				,""		,"运输类型02"				,""}
				,{"String"		,null,	"Exact"		,ColSearchDeliveryTypeCd03		,B100_DefaultVariable.SearchDeliveryType03		,"運送タイプ03"			,""		,"Delivery Type 03"				,""		,"运输类型03"				,""}
				,{"String"		,null,	"Exact"		,ColSearchDeliveryTypeCd04		,B100_DefaultVariable.SearchDeliveryType04		,"運送タイプ04"			,""		,"Delivery Type 04"				,""		,"运输类型04"				,""}
				,{"String"		,null,	"Exact"		,ColSearchDeliveryTypeCd05		,B100_DefaultVariable.SearchDeliveryType05		,"運送タイプ05"			,""		,"Delivery Type 05"				,""		,"运输类型05"				,""}

				,{"Integer"		,null,	"Exact"		,ColSearchCodFG					,B100_DefaultVariable.SearchCODList				,"代引区分"				,""		,"COD Type"						,""		,"货到付款类型"				,""}
				,{"Integer"		,null,	"RangeMin"	,ColSearchCodPayTotalMin			,""													,"代引収受金額合計"		,"最小"	,"COD Collection Total"			,"Min"	,"货到付款收款总额"			,"最小"}
				,{"Integer"		,null,	"RangeMax"	,ColSearchCodPayTotalMax			,""													,"代引収受金額合計"		,"最大"	,"COD Collection Total"			,"Max"	,"货到付款收款总额"			,"最大"}

				,{"Integer"		,null,	"Exact"		,ColSearchChildrenFG				,""													,"子伝票区分"			,""		,"Child Waybill Type"			,""		,"子运单类型"			,""}
				,{"String"		,null,	"Exact"		,ColSearchParentOkuriNo			,""													,"親伝票番号"			,""		,"Parent Waybill No."			,""		,"父运单号"				,""}
			
				,{"String"		,null,	"Exact"		,ColSearchNiokuriCd				,""													,"荷送人CD"				,""		,"Shipper Code"					,""		,"发货人代码"				,""}
				,{"String"		,null,	"Exact"		,ColSearchNiokuriDepartmentCd	,""													,"荷送人部署CD"			,""		,"Shipper Department Code"		,""		,"发货人部门代码"			,""}
				,{"String"		,null,	"Partial"	,ColSearchNiokuriName			,""													,"荷送人名称"			,""		,"Shipper Name"					,""		,"发货人名称"				,""}
				,{"String"		,null,	"Prefix"	,ColSearchNiokuriPost			,""													,"荷送人郵便番号"		,""		,"Shipper Postal Code"			,""		,"发货人邮政编码"			,""}
				,{"String"		,null,	"Partial"	,ColSearchNiokuriAdd				,""													,"荷送人住所"			,""		,"Shipper Address"				,""		,"发货人地址"				,""}
				,{"String"		,null,	"Partial"	,ColSearchNioKuriTel				,""													,"荷送人Tel"			,""		,"Shipper TEL"					,""		,"发货人电话"				,""}
				,{"String"		,null,	"Partial"	,ColSearchNioKuriFax				,""													,"荷送人Fax"			,""		,"Shipper FAX"					,""		,"发货人传真"				,""}
				,{"String"		,null,	"Partial"	,ColSearchNioKuriMail			,""													,"荷送人Mail"			,""		,"Shipper Email"				,""		,"发货人邮箱"				,""}
				,{"String"		,null,	"Prefix"	,ColSearchNiokuriMunicCd			,""													,"荷送人市区町村CD"		,""		,"Shipper Municipality Code"	,""		,"发货人市区町村代码"		,""}

				,{"String"		,null,	"Exact"		,ColSearchDeliCd					,""													,"届先CD"				,""		,"Destination Code"				,""		,"收货地代码"				,""}
				,{"String"		,null,	"Exact"		,ColSearchClDeliCd				,""													,"荷主届先CD"			,""		,"Client Destination Code"		,""		,"货主收货地代码"			,""}
				,{"String"		,null,	"Exact"		,ColSearchDeliDepartmentCd		,""													,"届先部署CD"			,""		,"Destination Department Code"	,""		,"收货地部门代码"			,""}
				,{"String"		,null,	"Partial"	,ColSearchDeliName				,""													,"届先名称"				,""		,"Destination Name"				,""		,"收货地名称"				,""}
				,{"String"		,null,	"Prefix"	,ColSearchDeliPost				,""													,"届先郵便番号"			,""		,"Destination Postal Code"		,""		,"收货地邮政编码"			,""}
				,{"String"		,null,	"Partial"	,ColSearchDeliAdd					,""													,"届先住所"				,""		,"Destination Address"			,""		,"收货地地址"				,""}
				,{"String"		,null,	"Partial"	,ColSearchDeliTel					,""													,"届先Tel"				,""		,"Destination TEL"				,""		,"收货地电话"				,""}
				,{"String"		,null,	"Partial"	,ColSearchDeliFax					,""													,"届先Fax"				,""		,"Destination FAX"				,""		,"收货地传真"				,""}
				,{"String"		,null,	"Partial"	,ColSearchDeliMail				,""													,"届先Mail"				,""		,"Destination Email"			,""		,"收货地邮箱"				,""}
				,{"String"		,null,	"Prefix"	,ColSearchDeliMunicCd			,""													,"届先市区町村CD"		,""		,"Destination Municipality Code",""		,"收货地市区町村代码"		,""}

				,{"String"		,null,	"Partial"	,ColSearchCom						,""													,"コメント"				,""		,"Comment"						,""		,"备注"					,""}
				,{"Integer"		,null,	"Exact"		,ColSearchStatus					,B100_DefaultVariable.SearchStatusList			,"運送ステータス"		,""		,"Delivery Status"				,""		,"运输状态"				,""}

				,{"Integer"		,null,	"Exact"		,ColSearchFeeFixFG				,B100_DefaultVariable.SearchFeeFixFgList			,"運賃確定フラグ"		,""		,"Freight Fixed Flag"			,""		,"运费确定标志"			,""}
				,{"Integer"		,null,	"Exact"		,ColSearchReceiptStampFG			,B100_DefaultVariable.SearchReceiptStampFGList	,"受領印フラグ"			,""		,"Receipt Stamp Flag"			,""		,"收货章标志"				,""}
				,{"Integer"		,null,	"Exact"		,ColSearchInvoiceStatus			,B100_DefaultVariable.SearchInvoiceStatusList	,"請求ステータス"		,""		,"Invoice Status"				,""		,"账单状态"				,""}

				,{"Integer"		,null,	"RangeMin"	,ColSearchWithOutTaxTotalMin	,""													,"税別運賃合計"			,"最小"	,"Freight Total Excl. Tax"		,"Min"	,"未税运费合计"			,"最小"}
				,{"Integer"		,null,	"RangeMin"	,ColSearchTotalFeeMin			,""													,"税込運賃合計"			,"最小"	,"Freight Total Incl. Tax"		,"Min"	,"含税运费合计"			,"最小"}
				,{"Date"		,null,	"RangeStr"	,ColSearchFeeFixDateStr			,""													,"運賃確定日"			,"開始"	,"Freight Fixed At"				,"From"	,"运费确定时间"			,"开始"}
				,{"Date"		,null,	"RangeStr"	,ColSearchReceiptStampDateStr	,""													,"受領印日"				,"開始"	,"Receipt Stamp Date"			,"From"	,"收货章日期"				,"开始"}
				,{"DateTime"	,null,	"RangeStr"	,ColSearchEntryDateStr			,""													,"登録日"				,"開始"	,"Created At"					,"From"	,"登记时间"				,"开始"}
				,{"DateTime"	,null,	"RangeStr"	,ColSearchUpdateDateStr			,""													,"更新日"				,"開始"	,"Updated At"					,"From"	,"更新时间"				,"开始"}

				,{"Integer"		,null,	"RangeMax"	,ColSearchWithOutTaxTotalMax	,""													,"税別運賃合計"			,"最大"	,"Freight Total Excl. Tax"		,"Max"	,"未税运费合计"			,"最大"}
				,{"Integer"		,null,	"RangeMax"	,ColSearchTotalFeeMax			,""													,"税込運賃合計"			,"最大"	,"Freight Total Incl. Tax"		,"Max"	,"含税运费合计"			,"最大"}
				,{"Date"		,null,	"RangeEnd"	,ColSearchFeeFixDateEnd			,""													,"運賃確定日"			,"終了"	,"Freight Fixed At"				,"To"	,"运费确定时间"			,"结束"}
				,{"Date"		,null,	"RangeEnd"	,ColSearchReceiptStampDateEnd	,""													,"受領印日"				,"終了"	,"Receipt Stamp Date"			,"To"	,"收货章日期"				,"结束"}
				,{"DateTime"	,null,	"RangeEnd"	,ColSearchEntryDateEnd			,""													,"登録日"				,"終了"	,"Created At"					,"To"	,"登记时间"				,"结束"}
				,{"DateTime"	,null,	"RangeEnd"	,ColSearchUpdateDateEnd			,""													,"更新日"				,"終了"	,"Updated At"					,"To"	,"更新时间"				,"结束"}

				,{"String"		,null,	"Partial"	,ColSearchEntryUser				,""													,"登録者"				,""		,"Created By"					,""		,"登记人"					,""}
				,{"String"		,null,	"Partial"	,ColSearchUpdateUser				,""													,"更新者"				,""		,"Updated By"					,""		,"更新人"				,""}
				,{"String"		,null,	"Partial"	,ColSearchEntryPG					,""													,"登録プログラム"		,""		,"Created Program"				,""		,"登记程序"				,""}
				,{"String"		,null,	"Partial"	,ColSearchUpdatePG				,""													,"更新プログラム"		,""		,"Updated Program"				,""		,"更新程序"				,""}

				,{"String"		,null,	"Exact"		,ColSearchUseFeeBasePtCd			,""													,"運転計算タリフ"		,""		,"Freight Tariff"				,""		,"运费费率"				,""}
				,{"Integer"		,null,	"Exact"		,ColSearchWmsStatus				,B100_DefaultVariable.SearchWmsStatusList		,"倉庫出荷ステータス"	,""		,"Warehouse Ship Status"		,""		,"仓库出库状态"			,""}
				,{"Date"		,null,	"RangeStr"	,ColSearchWmsShipDateStr			,""													,"倉庫出荷日時"			,"開始"	,"Warehouse Ship Date"			,"From"	,"仓库出库日期"			,"开始"}
				,{"Date"		,null,	"RangeEnd"	,ColSearchWmsShipDateEnd			,""													,"倉庫出荷日時"			,"終了"	,"Warehouse Ship Date"			,"To"	,"仓库出库日期"			,"结束"}

				,{"String"		,null,	"Exact"		,ColSearchCourseGpCd				,""													,"配車コースグループCD"	,""		,"Route Group Code"				,""		,"配送路线组代码"		,""}
				,{"String"		,null,	"Exact"		,ColSearchCourseCD				,""													,"配車コースCD"			,""		,"Route Code"					,""		,"配送路线代码"			,""}
				,{"Integer"		,null,	"Exact"		,ColSearchCourseCDEda			,""													,"配車コースCD枝番"		,""		,"Route Code Branch"			,""		,"配送路线代码分支"		,""}
				,{"String"		,null,	"Exact"		,ColSearchPitGrp					,""													,"払出ピットグループ"	,""		,"Dispatch Pit Group"			,""		,"出库口组"				,""}
				,{"String"		,null,	"Exact"		,ColSearchPit						,""													,"払出ピット"			,""		,"Dispatch Pit"					,""		,"出库口"					,""}

				,{"String"		,null,	"Exact"		,ColSearchMsItemCd				,""													,"商品CD"				,""		,"Item Code"					,""		,"商品代码"				,""}
				,{"String"		,null,	"Partial"	,ColSearchMsItemName				,""													,"商品名"				,""		,"Item Name"					,""		,"商品名称"				,""}
				,{"String"		,null,	"Exact"		,ColSearchClItemCd				,""													,"荷主商品CD"			,""		,"Client Item Code"				,""		,"货主商品代码"			,""}

				,{"String"		,null,	"Exact"		,ColSearchMsCategoryCd			,""													,"カテゴリCD"			,""		,"Category Code"				,""		,"分类代码"				,""}
				,{"String"		,null,	"Partial"	,ColSearchMsCategoryName			,""													,"カテゴリ名"			,""		,"Category Name"				,""		,"分类名称"				,""}
				,{"String"		,null,	"Exact"		,ColSearchMsTildFG				,""													,"温度区分"				,""		,"Temperature Type"				,""		,"温度类型"				,""}
				,{"String"		,null,	"Partial"	,ColSearchMsTildName				,""													,"温度区分名"			,""		,"Temperature Type Name"		,""		,"温度类型名称"			,""}

				,{"String"		,null,	"Exact"		,ColSearchMsLot					,""													,"ロット指定"			,""		,"Lot"							,""		,"批次"					,""}
				,{"Date"		,null,	"RangeStr"	,ColSearchMsExpDateStr			,""													,"賞味期限指定"			,"開始"	,"Expiration Date"				,"From"	,"有效期"				,"开始"}
				,{"Date"		,null,	"RangeEnd"	,ColSearchMsExpDateEnd			,""													,"賞味期限指定"			,"終了"	,"Expiration Date"				,"To"	,"有效期"				,"结束"}
				,{"Integer"		,null,	"Exact	"	,ColSearchMsPackingType			,B100_DefaultVariable.SearchUnitTypeList			,"荷姿タイプ"			,""		,"Packing Type"					,""		,"包装类型"				,""}
				
			};
		
		Definition = B100_LanguageControl.DefinitionControl(Definition);
		
		return Definition;
	}
	public static Object[][] OkuriMsRt(
			ArrayList<String> SearchInvoiceWHCD,			//倉庫CD
			ArrayList<String> SearchClGpCD,					//荷主グループCD
			ArrayList<String> SearchClCd,					//荷主CD
			ArrayList<String> SearchOkuriNo,				//送り状番号
			ArrayList<String> SearchClDeliNo,				//荷主管理番号
			ArrayList<String> SearchPickupWhCd,				//集荷倉庫CD
			ArrayList<String> SearchPurposeFG,				//目的フラグ
			ArrayList<String> SearchPlanDateStr,			//出荷予定日開始
			ArrayList<String> SearchShipDateStr,			//出荷実績日開始
			ArrayList<String> SearchSPPlanDateStr,			//着日指定開始
			ArrayList<String> SearchSPDateStr,				//着日実績開始
			
			ArrayList<String> SearchPlanDateEnd,			//出荷予定日終了
			ArrayList<String> SearchShipDateEnd,			//出荷実績日終了
			ArrayList<String> SearchSPPlanDateEnd,			//着日指定終了
			ArrayList<String> SearchSPDateEnd,				//着日実績終了
			
			ArrayList<Float> SearchTotalWeightMin,			//荷物重量(kg)最小
			ArrayList<Float> SearchTotalSizeMin,			//荷物サイズ最小
			ArrayList<Integer> SearchTotalQtyMin,			//個口数最小
			
			ArrayList<Float> SearchTotalWeightMax,			//荷物重量(kg)最大
			ArrayList<Float> SearchTotalSizeMax,			//荷物サイズ最大
			ArrayList<Integer> SearchTotalQtyMax,			//個口数最大
			
			ArrayList<String> SearchDeliveryTypeCd01,		//運送タイプ01
			ArrayList<String> SearchDeliveryTypeCd02,		//運送タイプ02
			ArrayList<String> SearchDeliveryTypeCd03,		//運送タイプ03
			ArrayList<String> SearchDeliveryTypeCd04,		//運送タイプ04
			ArrayList<String> SearchDeliveryTypeCd05,		//運送タイプ05
			
			ArrayList<Integer> SearchCodFG,					//代引区分
			ArrayList<Integer> SearchCodPayTotalMin,		//代引収受金額合計最小
			ArrayList<Integer> SearchCodPayTotalMax,		//代引収受金額合計最大
			
			ArrayList<Integer> SearchChildrenFG,			//子伝票区分
			ArrayList<String> SearchParentOkuriNo,			//親伝票番号
			
			ArrayList<String> SearchNiokuriCd,				//荷送人CD
			ArrayList<String> SearchNiokuriDepartmentCd,	//荷送人部署CD
			ArrayList<String> SearchNiokuriName,			//荷送人名称
			ArrayList<String> SearchNiokuriPost,			//荷送人郵便番号
			ArrayList<String> SearchNiokuriAdd,				//荷送人住所
			ArrayList<String> SearchNioKuriTel,				//荷送人Tel
			ArrayList<String> SearchNioKuriFax,				//荷送人Fax
			ArrayList<String> SearchNioKuriMail,			//荷送人Mail
			ArrayList<String> SearchNiokuriMunicCd,			//荷送人市区町村CD
			
			ArrayList<String> SearchDeliCd,					//届先CD
			ArrayList<String> SearchClDeliCd,				//荷主届先CD
			ArrayList<String> SearchDeliDepartmentCd,		//届先部署CD
			ArrayList<String> SearchDeliName,				//届先名称
			ArrayList<String> SearchDeliPost,				//届先郵便番号
			ArrayList<String> SearchDeliAdd,				//届先住所
			ArrayList<String> SearchDeliTel,				//届先Tel
			ArrayList<String> SearchDeliFax,				//届先Fax
			ArrayList<String> SearchDeliMail,				//届先Mail
			ArrayList<String> SearchDeliMunicCd,			//届先市区町村CD
			
			ArrayList<String> SearchCom,					//コメント
			ArrayList<Integer> SearchStatus,				//運送ステータス
			
			ArrayList<Integer> SearchFeeFixFG,				//運賃確定フラグ
			ArrayList<Integer> SearchReceiptStampFG,		//受領印フラグ
			ArrayList<Integer> SearchInvoiceStatus,			//請求ステータス
			
			ArrayList<Integer> SearchWithOutTaxTotalMin,	//税別運賃合計最小
			ArrayList<Integer> SearchTotalFeeMin,			//税込運賃合計税込運賃合計
			ArrayList<String> SearchFeeFixDateStr,			//運賃確定日時開始
			ArrayList<String> SearchReceiptStampDateStr,	//受領印日時開始
			ArrayList<String> SearchEntryDateStr,			//登録日終了
			ArrayList<String> SearchUpdateDateStr,			//更新日終了
			
			ArrayList<Integer> SearchWithOutTaxTotalMax,	//税別運賃合計最大
			ArrayList<Integer> SearchTotalFeeMax,			//税込運賃合計最大
			ArrayList<String> SearchFeeFixDateEnd,			//運賃確定日時終了
			ArrayList<String> SearchReceiptStampDateEnd,	//受領印日時終了
			ArrayList<String> SearchEntryDateEnd,			//登録日終了
			ArrayList<String> SearchUpdateDateEnd,			//更新日終了
			
			ArrayList<String> SearchEntryUser,				//登録者
			ArrayList<String> SearchUpdateUser,				//更新者
			ArrayList<String> SearchEntryPG,				//登録プログラム
			ArrayList<String> SearchUpdatePG,				//更新プログラム
			ArrayList<String> SearchUseFeeBasePtCd,			//運転計算タリフ
			ArrayList<Integer> SearchWmsStatus,				//倉庫出荷ステータス
			ArrayList<String> SearchWmsShipDateStr,			//倉庫出荷日時開始
			ArrayList<String> SearchWmsShipDateEnd,			//倉庫出荷日時終了
			ArrayList<String> SearchCourseGpCd,				//配車コースグループコード
			ArrayList<String> SearchCourseCD,				//配車コースコード
			ArrayList<Integer> SearchCourseCDEda,			//配車コースコード枝番
			ArrayList<String> SearchPitGrp,					//荷物払出ピットグループ
			ArrayList<String> SearchPit,					//荷物払出ピット
			
			ArrayList<String> SearchMsItemCd,				//商品CD
			ArrayList<String> SearchMsItemName,				//商品名
			
			ArrayList<String> SearchClItemCd,				//荷主商品CD
			
			ArrayList<String> SearchMsCategoryCd,			//カテゴリCD
			ArrayList<String> SearchMsCategoryName,			//カテゴリ名
			ArrayList<String> SearchMsTildFG,				//温度区分
			ArrayList<String> SearchMsTildName,				//温度区分名
			
			ArrayList<String> SearchMsLot,					//ロット指定
			ArrayList<String> SearchMsExpDateStr,			//賞味期限指定開始
			ArrayList<String> SearchMsExpDateEnd,			//賞味期限指定終了
			ArrayList<Integer> SearchMsPackingType,			//荷姿タイプ
			
			boolean AllSearch){
		
		Object[][] Definition = T100_OkuriHdRt.DefinitionRt();
		for(int i=0;i<Definition.length;i++) {
			switch((int)Definition[i][3]){
				case ColSearchInvoiceWHCD:	
					Definition[i][1]	= SearchInvoiceWHCD;
					break;
				case ColSearchClGpCD:	
					Definition[i][1]	= SearchClGpCD;
					break;
				case ColSearchClCd:	
					Definition[i][1]	= SearchClCd;
					break;
				case ColSearchOkuriNo:	
					Definition[i][1]	= SearchOkuriNo;
					break;
				case ColSearchClDeliNo:	
					Definition[i][1]	= SearchClDeliNo;
					break;
				case ColSearchPickupWhCd:	
					Definition[i][1]	= SearchPickupWhCd;
					break;
				case ColSearchPurposeFG:	
					Definition[i][1]	= SearchPurposeFG;
					break;
				case ColSearchPlanDateStr:	
					Definition[i][1]	= SearchPlanDateStr;
					break;
				case ColSearchShipDateStr:	
					Definition[i][1]	= SearchShipDateStr;
					break;
				case ColSearchSPPlanDateStr:	
					Definition[i][1]	= SearchSPPlanDateStr;
					break;
				case ColSearchSPDateStr:	
					Definition[i][1]	= SearchSPDateStr;
					break;
				
				case ColSearchPlanDateEnd:	
					Definition[i][1]	= SearchPlanDateEnd;
					break;
				case ColSearchShipDateEnd:	
					Definition[i][1]	= SearchShipDateEnd;
					break;
				case ColSearchSPPlanDateEnd:	
					Definition[i][1]	= SearchSPPlanDateEnd;
					break;
				case ColSearchSPDateEnd:	
					Definition[i][1]	= SearchSPDateEnd;
					break;
				
				case ColSearchTotalWeightMin:	
					Definition[i][1]	= SearchTotalWeightMin;
					break;
				case ColSearchTotalSizeMin:	
					Definition[i][1]	= SearchTotalSizeMin;
					break;
				case ColSearchTotalQtyMin:	
					Definition[i][1]	= SearchTotalQtyMin;
					break;
				
				case ColSearchTotalWeightMax:	
					Definition[i][1]	= SearchTotalWeightMax;
					break;
				case ColSearchTotalSizeMax:	
					Definition[i][1]	= SearchTotalSizeMax;
					break;
				case ColSearchTotalQtyMax:	
					Definition[i][1]	= SearchTotalQtyMax;
					break;
				
				case ColSearchDeliveryTypeCd01:	
					Definition[i][1]	= SearchDeliveryTypeCd01;
					break;
				case ColSearchDeliveryTypeCd02:	
					Definition[i][1]	= SearchDeliveryTypeCd02;
					break;
				case ColSearchDeliveryTypeCd03:	
					Definition[i][1]	= SearchDeliveryTypeCd03;
					break;
				case ColSearchDeliveryTypeCd04:	
					Definition[i][1]	= SearchDeliveryTypeCd04;
					break;
				case ColSearchDeliveryTypeCd05:	
					Definition[i][1]	= SearchDeliveryTypeCd05;
					break;
				
				case ColSearchCodFG:	
					Definition[i][1]	= SearchCodFG;
					break;
				case ColSearchCodPayTotalMin:	
					Definition[i][1]	= SearchCodPayTotalMin;
					break;
				case ColSearchCodPayTotalMax:	
					Definition[i][1]	= SearchCodPayTotalMax;
					break;
				
				case ColSearchChildrenFG:	
					Definition[i][1]	= SearchChildrenFG;
					break;
				case ColSearchParentOkuriNo:
					Definition[i][1]	= SearchParentOkuriNo;
					break;
				
				case ColSearchNiokuriCd:	
					Definition[i][1]	= SearchNiokuriCd;
					break;
				case ColSearchNiokuriDepartmentCd:	
					Definition[i][1]	= SearchNiokuriDepartmentCd;
					break;
				case ColSearchNiokuriName:	
					Definition[i][1]	= SearchNiokuriName;
					break;
				case ColSearchNiokuriPost:	
					Definition[i][1]	= SearchNiokuriPost;
					break;
				case ColSearchNiokuriAdd:	
					Definition[i][1]	= SearchNiokuriAdd;
					break;
				case ColSearchNioKuriTel:	
					Definition[i][1]	= SearchNioKuriTel;
					break;
				case ColSearchNioKuriFax:	
					Definition[i][1]	= SearchNioKuriFax;
					break;
				case ColSearchNioKuriMail:	
					Definition[i][1]	= SearchNioKuriMail;
					break;
				case ColSearchNiokuriMunicCd:	
					Definition[i][1]	= SearchNiokuriMunicCd;
					break;
				
				case ColSearchDeliCd:	
					Definition[i][1]	= SearchDeliCd;
					break;
				case ColSearchClDeliCd:	
					Definition[i][1]	= SearchClDeliCd;
					break;
				case ColSearchDeliDepartmentCd:	
					Definition[i][1]	= SearchDeliDepartmentCd;
					break;
				case ColSearchDeliName:	
					Definition[i][1]	= SearchDeliName;
					break;
				case ColSearchDeliPost:	
					Definition[i][1]	= SearchDeliPost;
					break;
				case ColSearchDeliAdd:	
					Definition[i][1]	= SearchDeliAdd;
					break;
				case ColSearchDeliTel:	
					Definition[i][1]	= SearchDeliTel;
					break;
				case ColSearchDeliFax:	
					Definition[i][1]	= SearchDeliFax;
					break;
				case ColSearchDeliMail:	
					Definition[i][1]	= SearchDeliMail;
					break;
				case ColSearchDeliMunicCd:	
					Definition[i][1]	= SearchDeliMunicCd;
					break;
				
				case ColSearchCom:	
					Definition[i][1]	= SearchCom;
					break;
				case ColSearchStatus:	
					Definition[i][1]	= SearchStatus;
					break;
				
				case ColSearchFeeFixFG:	
					Definition[i][1]	= SearchFeeFixFG;
					break;
				case ColSearchReceiptStampFG:	
					Definition[i][1]	= SearchReceiptStampFG;
					break;
				case ColSearchInvoiceStatus:	
					Definition[i][1]	= SearchInvoiceStatus;
					break;
				
				case ColSearchWithOutTaxTotalMin:	
					Definition[i][1]	= SearchWithOutTaxTotalMin;
					break;
				case ColSearchTotalFeeMin:	
					Definition[i][1]	= SearchTotalFeeMin;
					break;
				case ColSearchFeeFixDateStr:	
					Definition[i][1]	= SearchFeeFixDateStr;
					break;
				case ColSearchReceiptStampDateStr:	
					Definition[i][1]	= SearchReceiptStampDateStr;
					break;
				case ColSearchEntryDateStr:	
					Definition[i][1]	= SearchEntryDateStr;
					break;
				case ColSearchUpdateDateStr:	
					Definition[i][1]	= SearchUpdateDateStr;
					break;
				
				case ColSearchWithOutTaxTotalMax:	
					Definition[i][1]	= SearchWithOutTaxTotalMax;
					break;
				case ColSearchTotalFeeMax:	
					Definition[i][1]	= SearchTotalFeeMax;
					break;
				case ColSearchFeeFixDateEnd:	
					Definition[i][1]	= SearchFeeFixDateEnd;
					break;
				case ColSearchReceiptStampDateEnd:	
					Definition[i][1]	= SearchReceiptStampDateEnd;
					break;
				case ColSearchEntryDateEnd:	
					Definition[i][1]	= SearchEntryDateEnd;
					break;
				case ColSearchUpdateDateEnd:
					Definition[i][1]	= SearchUpdateDateEnd;
					break;
				
				case ColSearchEntryUser:	
					Definition[i][1]	= SearchEntryUser;
					break;
				case ColSearchUpdateUser:	
					Definition[i][1]	= SearchUpdateUser;
					break;
				case ColSearchEntryPG:	
					Definition[i][1]	= SearchEntryPG;
					break;
				case ColSearchUpdatePG:	
					Definition[i][1]	= SearchUpdatePG;
					break;
				case ColSearchUseFeeBasePtCd:	
					Definition[i][1]	= SearchUseFeeBasePtCd;
					break;
				case ColSearchWmsStatus:	
					Definition[i][1]	= SearchWmsStatus;
					break;
				case ColSearchWmsShipDateStr:	
					Definition[i][1]	= SearchWmsShipDateStr;
					break;
				case ColSearchWmsShipDateEnd:	
					Definition[i][1]	= SearchWmsShipDateEnd;
					break;
				case ColSearchCourseGpCd:	
					Definition[i][1]	= SearchCourseGpCd;
					break;
				case ColSearchCourseCD:	
					Definition[i][1]	= SearchCourseCD;
					break;
				case ColSearchCourseCDEda:	
					Definition[i][1]	= SearchCourseCDEda;
					break;
				case ColSearchPitGrp:	
					Definition[i][1]	= SearchPitGrp;
					break;
				case ColSearchPit:	
					Definition[i][1]	= SearchPit;
					break;
				
				case ColSearchMsItemCd:	
					Definition[i][1]	= SearchMsItemCd;
					break;
				case ColSearchMsItemName:	
					Definition[i][1]	= SearchMsItemName;
					break;
				
				case ColSearchClItemCd:	
					Definition[i][1]	= SearchClItemCd;
					break;
					
				case ColSearchMsCategoryCd:	
					Definition[i][1]	= SearchMsCategoryCd;
					break;
				case ColSearchMsCategoryName:	
					Definition[i][1]	= SearchMsCategoryName;
					break;
				case ColSearchMsTildFG:	
					Definition[i][1]	= SearchMsTildFG;
					break;
				case ColSearchMsTildName:	
					Definition[i][1]	= SearchMsTildName;
					break;
				
				case ColSearchMsLot:	
					Definition[i][1]	= SearchMsLot;
					break;
				case ColSearchMsExpDateStr:	
					Definition[i][1]	= SearchMsExpDateStr;
					break;
				case ColSearchMsExpDateEnd:	
					Definition[i][1]	= SearchMsExpDateEnd;
					break;
				case ColSearchMsPackingType:
					Definition[i][1]	= SearchMsPackingType;
					break;
					
				default:
					break;
					
			};
		}
		/*
		日付系検索最小は念のため00:00:00扱い
		日付系検索項目最大は一日進めて00:00:00扱い
		検索条件の重複除去
		*/
		Definition	= B100_ArraySearchControl.SearchDefinitionControl(Definition);
		
		for(int i=0;i<Definition.length;i++) {
			switch((int)Definition[i][3]){
				case ColSearchInvoiceWHCD:
					SearchInvoiceWHCD			= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchClGpCD:
					SearchClGpCD				= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchClCd:
					SearchClCd					= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchOkuriNo:
					SearchOkuriNo				= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchClDeliNo:
					SearchClDeliNo				= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchPickupWhCd:
					SearchPickupWhCd			= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchPurposeFG:
					SearchPurposeFG				= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchPlanDateStr:
					SearchPlanDateStr			= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchShipDateStr:
					SearchShipDateStr			= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchSPPlanDateStr:
					SearchSPPlanDateStr			= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchSPDateStr:
					SearchSPDateStr				= (ArrayList<String>)Definition[i][1];
					break;
					
				case ColSearchPlanDateEnd:
					SearchPlanDateEnd			= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchShipDateEnd:
					SearchShipDateEnd			= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchSPPlanDateEnd:
					SearchSPPlanDateEnd			= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchSPDateEnd:
					SearchSPDateEnd				= (ArrayList<String>)Definition[i][1];
					break;
					
				case ColSearchTotalWeightMin:
					SearchTotalWeightMin		= (ArrayList<Float>)Definition[i][1];
					break;
				case ColSearchTotalSizeMin:
					SearchTotalSizeMin			= (ArrayList<Float>)Definition[i][1];
					break;
				case ColSearchTotalQtyMin:
					SearchTotalQtyMin			= (ArrayList<Integer>)Definition[i][1];
					break;
					
				case ColSearchTotalWeightMax:
					SearchTotalWeightMax		= (ArrayList<Float>)Definition[i][1];
					break;
				case ColSearchTotalSizeMax:
					SearchTotalSizeMax			= (ArrayList<Float>)Definition[i][1];
					break;
				case ColSearchTotalQtyMax:
					SearchTotalQtyMax			= (ArrayList<Integer>)Definition[i][1];
					break;
					
				case ColSearchDeliveryTypeCd01:
					SearchDeliveryTypeCd01		= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchDeliveryTypeCd02:
					SearchDeliveryTypeCd02		= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchDeliveryTypeCd03:
					SearchDeliveryTypeCd03		= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchDeliveryTypeCd04:
					SearchDeliveryTypeCd04		= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchDeliveryTypeCd05:
					SearchDeliveryTypeCd05		= (ArrayList<String>)Definition[i][1];
					break;
					
				case ColSearchCodFG:
					SearchCodFG					= (ArrayList<Integer>)Definition[i][1];
					break;
				case ColSearchCodPayTotalMin:
					SearchCodPayTotalMin		= (ArrayList<Integer>)Definition[i][1];
					break;
				case ColSearchCodPayTotalMax:
					SearchCodPayTotalMax		= (ArrayList<Integer>)Definition[i][1];
					break;
					
				case ColSearchChildrenFG:
					SearchChildrenFG			= (ArrayList<Integer>)Definition[i][1];
					break;
				case ColSearchParentOkuriNo:
					SearchParentOkuriNo			= (ArrayList<String>)Definition[i][1];
					break;
					
				case ColSearchNiokuriCd:
					SearchNiokuriCd				= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchNiokuriDepartmentCd:
					SearchNiokuriDepartmentCd	= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchNiokuriName:
					SearchNiokuriName			= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchNiokuriPost:
					SearchNiokuriPost			= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchNiokuriAdd:
					SearchNiokuriAdd			= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchNioKuriTel:
					SearchNioKuriTel			= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchNioKuriFax:
					SearchNioKuriFax			= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchNioKuriMail:
					SearchNioKuriMail			= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchNiokuriMunicCd:
					SearchNiokuriMunicCd		= (ArrayList<String>)Definition[i][1];
					break;
					
				case ColSearchDeliCd:
					SearchDeliCd				= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchClDeliCd:
					SearchClDeliCd				= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchDeliDepartmentCd:
					SearchDeliDepartmentCd		= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchDeliName:
					SearchDeliName				= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchDeliPost:
					SearchDeliPost				= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchDeliAdd:
					SearchDeliAdd				= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchDeliTel:
					SearchDeliTel				= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchDeliFax:
					SearchDeliFax				= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchDeliMail:
					SearchDeliMail				= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchDeliMunicCd:
					SearchDeliMunicCd			= (ArrayList<String>)Definition[i][1];
					break;
					
				case ColSearchCom:
					SearchCom					= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchStatus:
					SearchStatus				= (ArrayList<Integer>)Definition[i][1];
					break;
					
				case ColSearchFeeFixFG:
					SearchFeeFixFG				= (ArrayList<Integer>)Definition[i][1];
					break;
				case ColSearchReceiptStampFG:
					SearchReceiptStampFG		= (ArrayList<Integer>)Definition[i][1];
					break;
				case ColSearchInvoiceStatus:
					SearchInvoiceStatus			= (ArrayList<Integer>)Definition[i][1];
					break;
					
				case ColSearchWithOutTaxTotalMin:
					SearchWithOutTaxTotalMin	= (ArrayList<Integer>)Definition[i][1];
					break;
				case ColSearchTotalFeeMin:
					SearchTotalFeeMin			= (ArrayList<Integer>)Definition[i][1];
					break;
				case ColSearchFeeFixDateStr:
					SearchFeeFixDateStr			= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchReceiptStampDateStr:
					SearchReceiptStampDateStr	= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchEntryDateStr:
					SearchEntryDateStr			= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchUpdateDateStr:
					SearchUpdateDateStr			= (ArrayList<String>)Definition[i][1];
					break;
					
				case ColSearchWithOutTaxTotalMax:
					SearchWithOutTaxTotalMax	= (ArrayList<Integer>)Definition[i][1];
					break;
				case ColSearchTotalFeeMax:
					SearchTotalFeeMax			= (ArrayList<Integer>)Definition[i][1];
					break;
				case ColSearchFeeFixDateEnd:
					SearchFeeFixDateEnd			= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchReceiptStampDateEnd:
					SearchReceiptStampDateEnd	= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchEntryDateEnd:
					SearchEntryDateEnd			= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchUpdateDateEnd:
					SearchUpdateDateEnd			= (ArrayList<String>)Definition[i][1];
					break;
					
				case ColSearchEntryUser:
					SearchEntryUser				= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchUpdateUser:
					SearchUpdateUser			= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchEntryPG:
					SearchEntryPG				= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchUpdatePG:
					SearchUpdatePG				= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchUseFeeBasePtCd:
					SearchUseFeeBasePtCd		= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchWmsStatus:
					SearchWmsStatus				= (ArrayList<Integer>)Definition[i][1];
					break;
				case ColSearchWmsShipDateStr:
					SearchWmsShipDateStr		= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchWmsShipDateEnd:
					SearchWmsShipDateEnd		= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchCourseGpCd:
					SearchCourseGpCd			= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchCourseCD:
					SearchCourseCD				= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchCourseCDEda:
					SearchCourseCDEda			= (ArrayList<Integer>)Definition[i][1];
					break;
				case ColSearchPitGrp:
					SearchPitGrp				= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchPit:
					SearchPit					= (ArrayList<String>)Definition[i][1];
					break;
					
				case ColSearchMsItemCd:
					SearchMsItemCd				= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchMsItemName:
					SearchMsItemName			= (ArrayList<String>)Definition[i][1];
					break;
					
				case ColSearchClItemCd:
					SearchClItemCd				= (ArrayList<String>)Definition[i][1];
					break;
					
				case ColSearchMsCategoryCd:
					SearchMsCategoryCd			= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchMsCategoryName:
					SearchMsCategoryName		= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchMsTildFG:
					SearchMsTildFG				= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchMsTildName:
					SearchMsTildName			= (ArrayList<String>)Definition[i][1];
					break;
					
				case ColSearchMsLot:
					SearchMsLot					= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchMsExpDateStr:
					SearchMsExpDateStr			= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchMsExpDateEnd:
					SearchMsExpDateEnd			= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchMsPackingType:
					SearchMsPackingType			= (ArrayList<Integer>)Definition[i][1];
					break;
				default:
					break;
			}
			
		}
		Object[][]  Rt = OkuriMsRtMain(
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
		return Rt;
	}
	
	private static Object[][] OkuriMsRtMain(
			ArrayList<String> SearchInvoiceWHCD,			//倉庫CD
			ArrayList<String> SearchClGpCD,					//荷主グループCD
			ArrayList<String> SearchClCd,					//荷主CD
			ArrayList<String> SearchOkuriNo,				//送り状番号
			ArrayList<String> SearchClDeliNo,				//荷主管理番号
			ArrayList<String> SearchPickupWhCd,				//集荷倉庫CD
			ArrayList<String> SearchPurposeFG,				//目的フラグ
			ArrayList<String> SearchPlanDateStr,			//出荷予定日開始
			ArrayList<String> SearchShipDateStr,			//出荷実績日開始
			ArrayList<String> SearchSPPlanDateStr,			//着日指定開始
			ArrayList<String> SearchSPDateStr,				//着日実績開始
			
			ArrayList<String> SearchPlanDateEnd,			//出荷予定日終了
			ArrayList<String> SearchShipDateEnd,			//出荷実績日終了
			ArrayList<String> SearchSPPlanDateEnd,			//着日指定終了
			ArrayList<String> SearchSPDateEnd,				//着日実績終了
			
			ArrayList<Float> SearchTotalWeightMin,			//荷物重量(kg)最小
			ArrayList<Float> SearchTotalSizeMin,			//荷物サイズ最小
			ArrayList<Integer> SearchTotalQtyMin,			//個口数最小
			
			ArrayList<Float> SearchTotalWeightMax,			//荷物重量(kg)最大
			ArrayList<Float> SearchTotalSizeMax,			//荷物サイズ最大
			ArrayList<Integer> SearchTotalQtyMax,			//個口数最大
			
			ArrayList<String> SearchDeliveryTypeCd,			//運送タイプ01
			ArrayList<String> SearchDeliveryTypeCd02,		//運送タイプ02
			ArrayList<String> SearchDeliveryTypeCd03,		//運送タイプ03
			ArrayList<String> SearchDeliveryTypeCd04,		//運送タイプ04
			ArrayList<String> SearchDeliveryTypeCd05,		//運送タイプ05
			
			ArrayList<Integer> SearchCodFG,					//代引区分
			ArrayList<Integer> SearchCodPayTotalMin,		//代引収受金額合計最小
			ArrayList<Integer> SearchCodPayTotalMax,		//代引収受金額合計最大
			
			ArrayList<Integer> SearchChildrenFG,			//子伝票区分
			ArrayList<String> SearchParentOkuriNo,			//親伝票番号
			
			ArrayList<String> SearchNiokuriCd,				//荷送人CD
			ArrayList<String> SearchNiokuriDepartmentCd,	//荷送人部署CD
			ArrayList<String> SearchNiokuriName,			//荷送人名称
			ArrayList<String> SearchNiokuriPost,			//荷送人郵便番号
			ArrayList<String> SearchNiokuriAdd,				//荷送人住所
			ArrayList<String> SearchNioKuriTel,				//荷送人Tel
			ArrayList<String> SearchNioKuriFax,				//荷送人Fax
			ArrayList<String> SearchNioKuriMail,			//荷送人Mail
			ArrayList<String> SearchNiokuriMunicCd,			//荷送人市区町村CD
			
			ArrayList<String> SearchDeliCd,					//届先CD
			ArrayList<String> SearchClDeliCd,				//荷主届先CD
			ArrayList<String> SearchDeliDepartmentCd,		//届先部署CD
			ArrayList<String> SearchDeliName,				//届先名称
			ArrayList<String> SearchDeliPost,				//届先郵便番号
			ArrayList<String> SearchDeliAdd,				//届先住所
			ArrayList<String> SearchDeliTel,				//届先Tel
			ArrayList<String> SearchDeliFax,				//届先Fax
			ArrayList<String> SearchDeliMail,				//届先Mail
			ArrayList<String> SearchDeliMunicCd,			//届先市区町村CD
			
			ArrayList<String> SearchCom,					//コメント
			ArrayList<Integer> SearchStatus,				//運送ステータス
			
			ArrayList<Integer> SearchFeeFixFG,				//運賃確定フラグ
			ArrayList<Integer> SearchReceiptStampFG,		//受領印フラグ
			ArrayList<Integer> SearchInvoiceStatus,			//請求ステータス
			
			ArrayList<Integer> SearchWithOutTaxTotalMin,	//税別運賃合計最小
			ArrayList<Integer> SearchTotalFeeMin,			//税込運賃合計税込運賃合計
			ArrayList<String> SearchFeeFixDateStr,			//運賃確定日時開始
			ArrayList<String> SearchReceiptStampDateStr,	//受領印日時開始
			ArrayList<String> SearchEntryDateStr,			//登録日終了
			ArrayList<String> SearchUpdateDateStr,			//更新日終了
			
			ArrayList<Integer> SearchWithOutTaxTotalMax,	//税別運賃合計最大
			ArrayList<Integer> SearchTotalFeeMax,			//税込運賃合計最大
			ArrayList<String> SearchFeeFixDateEnd,			//運賃確定日時終了
			ArrayList<String> SearchReceiptStampDateEnd,	//受領印日時終了
			ArrayList<String> SearchEntryDateEnd,			//登録日終了
			ArrayList<String> SearchUpdateDateEnd,			//更新日終了
			
			ArrayList<String> SearchEntryUser,				//登録者
			ArrayList<String> SearchUpdateUser,				//更新者
			ArrayList<String> SearchEntryPG,				//登録プログラム
			ArrayList<String> SearchUpdatePG,				//更新プログラム
			ArrayList<String> SearchUseFeeBasePtCd,			//運転計算タリフ
			ArrayList<Integer> SearchWmsStatus,				//倉庫出荷ステータス
			ArrayList<String> SearchWmsShipDateStr,			//倉庫出荷日時開始
			ArrayList<String> SearchWmsShipDateEnd,			//倉庫出荷日時終了
			ArrayList<String> SearchCourseGpCd,				//配車コースグループコード
			ArrayList<String> SearchCourseCD,				//配車コースコード
			ArrayList<Integer> SearchCourseCDEda,			//配車コースコード枝番
			ArrayList<String> SearchPitGrp,					//荷物払出ピットグループ
			ArrayList<String> SearchPit,					//荷物払出ピット
			
			ArrayList<String> SearchMsItemCd,				//商品CD
			ArrayList<String> SearchMsItemName,				//商品名
			
			ArrayList<String> SearchClItemCd,				//荷主商品CD
			
			ArrayList<String> SearchMsCategoryCd,			//カテゴリCD
			ArrayList<String> SearchMsCategoryName,			//カテゴリ名
			ArrayList<String> SearchMsTildFG,				//温度区分
			ArrayList<String> SearchMsTildName,				//温度区分名
			
			ArrayList<String> SearchMsLot,					//ロット指定
			ArrayList<String> SearchMsExpDateStr,			//賞味期限指定開始
			ArrayList<String> SearchMsExpDateEnd,			//賞味期限指定終了
			ArrayList<Integer> SearchMsPackingType,			//荷姿タイプ
			
			boolean AllSearch){
		
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
				+"(KT0010_OKURI_HD.DeliveryTypeCd) 		as DeliveryTypeCd01,\n"		//運送タイプ01
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
				+"(KT0010_OKURI_HD.ChildrenFG) 			as ChildrenFG,\n"			//子伝票区分
				+"(KT0010_OKURI_HD.ParentOkuriNo) 		as ParentOkuriNo,\n"		//親伝票番号
				+"(KT0010_OKURI_HD.NiokuriCd) 			as NiokuriCd,\n"			//荷送人コード
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
				+"(KT0010_OKURI_HD.DeliCd) 				as DeliCd,\n"				//荷届先コード
				+"(KT0010_OKURI_HD.ClDeliCd) 			as ClDeliCd,\n"				//荷主荷届先コード
				+"(KT0010_OKURI_HD.DeliDepartmentCd) 	as DeliDepartmentCd,\n"		//部署CD
				+"(KT0010_OKURI_HD.DeliName01) 			as DeliName01,\n"			//荷届先名01
				+"(KT0010_OKURI_HD.DeliName02) 			as DeliName02,\n"			//荷届先名02
				+"(KT0010_OKURI_HD.DeliName03) 			as DeliName03,\n"			//荷届先名03
				+"(KT0010_OKURI_HD.DeliPost) 			as DeliPost,\n"				//荷届先郵便番号
				+"(KT0010_OKURI_HD.DeliAdd01) 			as DeliAdd01,\n"			//荷届先住所01
				+"(KT0010_OKURI_HD.DeliAdd02) 			as DeliAdd02,\n"			//荷届先住所02
				+"(KT0010_OKURI_HD.DeliAdd03) 			as DeliAdd03,\n"			//荷届先住所03
				+"(KT0010_OKURI_HD.DeliTel) 			as DeliTel,\n"				//荷届先TEL
				+"(KT0010_OKURI_HD.DeliFax) 			as DeliFax,\n"				//荷届先FAX
				+"(KT0010_OKURI_HD.DeliMail) 			as DeliMail,\n"				//荷届先MAIL
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
				+" and KT0010_OKURI_HD.InvoiceWHCD = KT0011_OKURI_MS.InvoiceWHCD"
				+" and KT0010_OKURI_HD.OkuriNo = KT0011_OKURI_MS.OkuriNo"
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
		

		if(null!=SearchInvoiceWHCD && 0<SearchInvoiceWHCD.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchInvoiceWHCD.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.InvoiceWHCD = ?";
			}
			sql = sql + ")\n";
		}
		
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
		
		sql = sql + " order by KT0010_OKURI_HD.InvoiceWHCD,KT0010_OKURI_HD.cl_cd,KT0010_OKURI_HD.PlanDate,KT0010_OKURI_HD.DeliCd,KT0010_OKURI_HD.DeliDepartmentCd,KT0010_OKURI_HD.OkuriNo,KT0011_OKURI_MS.MsNo";
		//System.out.println(sql);
		
		if(SearchKick) {
			A100_DbConnect.DB_CONN("NYANKO");
			ResultSet rset01 = null;
			PreparedStatement stmt01 = null;
			try {
				stmt01 = A100_DbConnect.conn.prepareStatement(sql);
				int StmtCount = 0;

				if(null!=SearchInvoiceWHCD && 0<SearchInvoiceWHCD.size()){
					for(int i=0;i<SearchInvoiceWHCD.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchInvoiceWHCD.get(i)+"");
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
				
				Rt = B100_RtObjectCreate.B100_RtObjectCreate(rset01,RtOkuriMsRt());
				
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