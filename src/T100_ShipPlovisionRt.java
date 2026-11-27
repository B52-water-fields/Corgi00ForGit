import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class T100_ShipPlovisionRt{
	/*
	コピペ用
	ArrayList<String> Searchcl_cd				= new ArrayList<String>();			//荷主コード
	ArrayList<String> SearchInvoiceWHCD			= new ArrayList<String>();			//倉庫コード
	ArrayList<String> SearchOkuriNo				= new ArrayList<String>();			//送り状番号
	ArrayList<Integer> SearchMsNoMin			= new ArrayList<Integer>();			//明細番号
	ArrayList<Integer> SearchSeqMin				= new ArrayList<Integer>();			//引当枝番
	ArrayList<Integer> SearchMsNoMax			= new ArrayList<Integer>();			//明細番号
	ArrayList<Integer> SearchSeqMax				= new ArrayList<Integer>();			//引当枝番
	ArrayList<String> SearchOrderItemCd			= new ArrayList<String>();			//商品コード
	ArrayList<String> SearchOrderItemName		= new ArrayList<String>();			//商品表記名
	ArrayList<String> SearchOrderLot			= new ArrayList<String>();			//受注ロット指定
	ArrayList<String> SearchOrderExpDateStr		= new ArrayList<String>();			//受注賞味期限指定
	ArrayList<String> SearchOrderExpDateEnd		= new ArrayList<String>();			//受注賞味期限指定
	ArrayList<Integer> SearchOrderQtyMin		= new ArrayList<Integer>();			//受注個数
	ArrayList<Integer> SearchOrderQtyMax		= new ArrayList<Integer>();			//受注個数
	ArrayList<String> SearchShipWhCd			= new ArrayList<String>();			//出荷倉庫コード
	ArrayList<String> SearchShipLoc				= new ArrayList<String>();			//ロケーション
	ArrayList<String> SearchShipItemCd			= new ArrayList<String>();			//商品コード
	ArrayList<String> SearchShipLot				= new ArrayList<String>();			//ロット
	ArrayList<String> SearchShipExpdateStr		= new ArrayList<String>();			//消費期限
	ArrayList<String> SearchShipActualDateStr	= new ArrayList<String>();			//入荷実績日
	ArrayList<String> SearchShipExpdateEnd		= new ArrayList<String>();			//消費期限
	ArrayList<String> SearchShipActualDateEnd	= new ArrayList<String>();			//入荷実績日
	ArrayList<String> SearchShipQtyMin			= new ArrayList<String>();			//引当数量
	ArrayList<String> SearchShipQtyMax			= new ArrayList<String>();			//引当数量
	ArrayList<Integer> SearchFixFg				= new ArrayList<Integer>();			//引落済フラグ
	ArrayList<Integer> SearchPackingType		= new ArrayList<Integer>();			//荷姿タイプ
	ArrayList<Integer> SearchPackingQtyMin		= new ArrayList<Integer>();			//荷姿数量
	ArrayList<Integer> SearchPackingQtyMax		= new ArrayList<Integer>();			//荷姿数量
	ArrayList<Integer> SearchBRShipQtyMin		= new ArrayList<Integer>();			//バラ数量
	ArrayList<Integer> SearchCTShipQtyMin		= new ArrayList<Integer>();			//カートン数量
	ArrayList<Integer> SearchCSShipQtyMin		= new ArrayList<Integer>();			//ケース数量
	ArrayList<Integer> SearchPLShipQtyMin		= new ArrayList<Integer>();			//パレット数量
	ArrayList<Integer> SearchBRShipQtyMax		= new ArrayList<Integer>();			//バラ数量
	ArrayList<Integer> SearchCTShipQtyMax		= new ArrayList<Integer>();			//カートン数量
	ArrayList<Integer> SearchCSShipQtyMax		= new ArrayList<Integer>();			//ケース数量
	ArrayList<Integer> SearchPLShipQtyMax		= new ArrayList<Integer>();			//パレット数量
	ArrayList<String> SearchEntryDateStr		= new ArrayList<String>();			//登録日時
	ArrayList<String> SearchUpdateDateStr		= new ArrayList<String>();			//更新日時
	ArrayList<String> SearchEntryDateEnd		= new ArrayList<String>();			//登録日時
	ArrayList<String> SearchUpdateDateEnd		= new ArrayList<String>();			//更新日時
	ArrayList<String> SearchEntryUser			= new ArrayList<String>();			//登録者
	ArrayList<String> SearchUpdateUser			= new ArrayList<String>();			//更新者
		
	ArrayList<String> SearchClDeliNo			= new ArrayList<String>();			//荷主管理番号
	ArrayList<String> SearchPlanDateStr			= new ArrayList<String>();			//出荷予定日
	ArrayList<String> SearchShipDateStr			= new ArrayList<String>();			//出荷実績日
	ArrayList<String> SearchPlanDateEnd			= new ArrayList<String>();			//出荷予定日
	ArrayList<String> SearchShipDateEnd			= new ArrayList<String>();			//出荷実績日
		
	ArrayList<String> SearchDeliCd				= new ArrayList<String>();			//荷届先コード
	ArrayList<String> SearchClDeliCd			= new ArrayList<String>();			//荷主荷届先コード
	ArrayList<String> SearchDeliDepartmentCd	= new ArrayList<String>();			//部署CD
	ArrayList<String> SearchDeliName			= new ArrayList<String>();			//荷届先名
	ArrayList<String> SearchDeliPost			= new ArrayList<String>();			//荷届先郵便番号
	ArrayList<String> SearchDeliAdd				= new ArrayList<String>();			//荷届先住所
	ArrayList<String> SearchDeliTel				= new ArrayList<String>();			//荷届先TEL
	ArrayList<String> SearchDeliFax				= new ArrayList<String>();			//荷届先FAX
	ArrayList<String> SearchDeliMail			= new ArrayList<String>();			//荷届先MAIL
	ArrayList<String> SearchDeliMunicCd			= new ArrayList<String>();			//荷届先市区町村CD
		 
	ArrayList<String> SearchClGpCD				= new ArrayList<String>();			//荷主グループCD
	ArrayList<String> SearchClCd				= new ArrayList<String>();			//荷主CD
	ArrayList<String> SearchPickupWhCd			= new ArrayList<String>();			//集荷倉庫CD
	ArrayList<String> SearchPurposeFG			= new ArrayList<String>();			//目的フラグ
	ArrayList<String> SearchSPPlanDateStr		= new ArrayList<String>();			//着日指定
	ArrayList<String> SearchSPDateStr			= new ArrayList<String>();			//着日実績
	ArrayList<String> SearchSPPlanDateEnd		= new ArrayList<String>();			//着日指定
	ArrayList<String> SearchSPDateEnd			= new ArrayList<String>();			//着日実績

	ArrayList<String> SearchCategoryCd			= new ArrayList<String>();			//商品カテゴリCD
	ArrayList<String> SearchCategoryName		= new ArrayList<String>();			//商品カテゴリ名
	ArrayList<String> SearchItemColorCd			= new ArrayList<String>();			//商品カラーコード
	ArrayList<String> SearchItemColorName		= new ArrayList<String>();			//商品カラー名
	ArrayList<String> SearchItemSizeCd			= new ArrayList<String>();			//商品サイズコード
	ArrayList<String> SearchItemSizeName		= new ArrayList<String>();			//商品サイズ名
	boolean AllSearch = false;
	
	Object[][] ShipPlovisionRt	= T100_ShipPlovisionRt.ShipPlovisionRt(
						 Searchcl_cd				//荷主コード
						,SearchInvoiceWHCD			//倉庫コード
						,SearchOkuriNo				//送り状番号
						,SearchMsNoMin				//明細番号
						,SearchSeqMin				//引当枝番
						,SearchMsNoMax				//明細番号
						,SearchSeqMax				//引当枝番
						,SearchOrderItemCd			//商品コード
						,SearchOrderItemName			//商品表記名
						,SearchOrderLot				//受注ロット指定
						,SearchOrderExpDateStr		//受注賞味期限指定
						,SearchOrderExpDateEnd		//受注賞味期限指定
						,SearchOrderQtyMin			//受注個数
						,SearchOrderQtyMax			//受注個数
						,SearchShipWhCd				//出荷倉庫コード
						,SearchShipLoc				//ロケーション
						,SearchShipItemCd			//商品コード
						,SearchShipLot				//ロット
						,SearchShipExpdateStr		//消費期限
						,SearchShipActualDateStr	//入荷実績日
						,SearchShipExpdateEnd		//消費期限
						,SearchShipActualDateEnd	//入荷実績日
						,SearchShipQtyMin			//引当数量
						,SearchShipQtyMax			//引当数量
						,SearchFixFg				//引落済フラグ
						,SearchPackingType			//荷姿タイプ
						,SearchPackingQtyMin		//荷姿数量
						,SearchPackingQtyMax		//荷姿数量
						,SearchBRShipQtyMin			//バラ数量
						,SearchCTShipQtyMin			//カートン数量
						,SearchCSShipQtyMin			//ケース数量
						,SearchPLShipQtyMin			//パレット数量
						,SearchBRShipQtyMax			//バラ数量
						,SearchCTShipQtyMax			//カートン数量
						,SearchCSShipQtyMax			//ケース数量
						,SearchPLShipQtyMax			//パレット数量
						,SearchEntryDateStr			//登録日時
						,SearchUpdateDateStr		//更新日時
						,SearchEntryDateEnd			//登録日時
						,SearchUpdateDateEnd		//更新日時
						,SearchEntryUser			//登録者
						,SearchUpdateUser			//更新者
							
						,SearchClDeliNo				//荷主管理番号
						,SearchPlanDateStr			//出荷予定日
						,SearchShipDateStr			//出荷実績日
						,SearchPlanDateEnd			//出荷予定日
						,SearchShipDateEnd			//出荷実績日
							
						,SearchDeliCd				//荷届先コード
						,SearchClDeliCd				//荷主荷届先コード
						,SearchDeliDepartmentCd		//部署CD
						,SearchDeliName				//荷届先名
						,SearchDeliPost				//荷届先郵便番号
						,SearchDeliAdd				//荷届先住所
						,SearchDeliTel				//荷届先TEL
						,SearchDeliFax				//荷届先FAX
						,SearchDeliMail				//荷届先MAIL
						,SearchDeliMunicCd			//荷届先市区町村CD
							 
						,SearchClGpCD				//荷主グループCD
						,SearchClCd					//荷主CD
						,SearchPickupWhCd			//集荷倉庫CD
						,SearchPurposeFG			//目的フラグ
						,SearchSPPlanDateStr		//着日指定
						,SearchSPDateStr			//着日実績
						,SearchSPPlanDateEnd		//着日指定
						,SearchSPDateEnd			//着日実績

						,SearchCategoryCd			//商品カテゴリCD
						,SearchCategoryName			//商品カテゴリ名
						,SearchItemColorCd			//商品カラーコード
						,SearchItemColorName		//商品カラー名
						,SearchItemSizeCd			//商品サイズコード
						,SearchItemSizeName			//商品サイズ名
						,AllSearch
						);
						
	String Getcl_cd				= (String)ShipPlovisionRt[i][T100_ShipPlovisionRt.Colcl_cd];			//荷主コード
	String GetInvoiceWHCD		= (String)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColInvoiceWHCD];		//倉庫コード
	String GetOkuriNo			= (String)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColOkuriNo];			//送り状番号
	int GetMsNo					= (int)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColMsNo];				//明細番号
	int GetSeq					= (int)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColSeq];					//引当枝番
	String GetOrderItemCd		= (String)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColOrderItemCd];		//商品コード
	String GetOrderItemName01	= (String)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColOrderItemName01];	//商品表記名
	String GetOrderLot			= (String)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColOrderLot];			//受注ロット指定
	String GetOrderExpDate		= (String)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColOrderExpDate];		//受注賞味期限指定
	int GetOrderQty				= (int)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColOrderQty];			//受注個数
	String GetShipWhCd			= (String)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColShipWhCd];			//出荷倉庫コード
	String GetShipLoc			= (String)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColShipLoc];			//ロケーション
	String GetShipItemCd		= (String)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColShipItemCd];		//商品コード
	String GetShipLot			= (String)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColShipLot];			//ロット
	String GetShipExpdate		= (String)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColShipExpdate];		//消費期限
	String GetShipActualDate	= (String)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColShipActualDate];	//入荷実績日
	int GetShipQty				= (int)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColShipQty];				//引当数量
	int GetFixFg				= (int)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColFixFg];				//引落済フラグ
	int GetPackingType			= (int)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColPackingType];			//荷姿タイプ
	int GetPackingQty			= (int)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColPackingQty];			//荷姿数量
	String GetUnitName			= (String)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColUnitName];			//荷姿単位
	int GetPackingUnitQty		= (int)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColPackingUnitQty];		//荷姿単位のバラ入数
	int GetBRShipQty			= (int)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColBRShipQty];			//バラ数量
	int GetCTShipQty			= (int)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColCTShipQty];			//カートン数量
	int GetCSShipQty			= (int)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColCSShipQty];			//ケース数量
	int GetPLShipQty			= (int)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColPLShipQty];			//パレット数量
	String GetBRUnitName		= (String)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColBRUnitName];		//バラ単位名
	String GetCTUnitName		= (String)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColCTUnitName];		//カートン単位名
	String GetCSUnitName		= (String)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColCSUnitName];		//ケース単位名
	String GetPLUnitName		= (String)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColPLUnitName];		//パレット単位名
	String GetEntryDate			= (String)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColEntryDate];		//登録日時
	String GetUpdateDate		= (String)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColUpdateDate];		//更新日時
	String GetEntryUser			= (String)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColEntryUser];		//登録者
	String GetUpdateUser		= (String)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColUpdateUser];		//更新者
						
	String GetClDeliNo			= (String)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColClDeliNo];			//荷主管理番号
	String GetPlanDate			= (String)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColPlanDate];			//出荷予定日
	String GetShipDate			= (String)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColShipDate];			//出荷実績日
	String GetDeliCd			= (String)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColDeliCd];			//荷届先コード
	String GetClDeliCd			= (String)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColClDeliCd];			//荷主荷届先コード
	String GetDeliDepartmentCd	= (String)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColDeliDepartmentCd];	//部署CD
	String GetDeliName01		= (String)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColDeliName01];		//荷届先名01
	String GetDeliName02		= (String)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColDeliName02];		//荷届先名02
	String GetDeliName03		= (String)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColDeliName03];		//荷届先名03
	String GetDeliPost			= (String)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColDeliPost];			//荷届先郵便番号
	String GetDeliAdd01			= (String)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColDeliAdd01];		//荷届先住所01
	String GetDeliAdd02			= (String)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColDeliAdd02];		//荷届先住所02
	String GetDeliAdd03			= (String)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColDeliAdd03];		//荷届先住所03
	String GetDeliTel			= (String)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColDeliTel];			//荷届先TEL
	String GetDeliFax			= (String)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColDeliFax];			//荷届先FAX
	String GetDeliMail			= (String)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColDeliMail];			//荷届先MAIL
	String GetDeliMunicCd		= (String)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColDeliMunicCd];		//荷届先市区町村CD
	
	int GetCtQty				= (int)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColCtQty];				//カートン入数
	int GetCsQty				= (int)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColCsQty];				//ケース入数
	int GetPlQty				= (int)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColPlQty];				//パレット入数
	String GetJanCd				= (String)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColJanCd];			//JANCD
	String GetCtJan				= (String)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColCtJan];			//カートンバーコード
	String GetCsJan				= (String)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColCsJan];			//ケースバーコード
	String GetPlJan				= (String)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColPlJan];			//パレットバーコード
	String GetCtName			= (String)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColCtName];			//カートン商品名称
	String GetCsName			= (String)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColCsName];			//ケース商品名称
	String GetPlName			= (String)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColPlName];			//パレット商品名称
	String GetCtUnitName		= (String)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColCtUnitName];		//カートン商品単位
	String GetCsUnitName		= (String)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColCsUnitName];		//ケース商品単位
	String GetPlUnitName		= (String)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColPlUnitName];		//パレット商品単位
	String GetItemWeight		= (String)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColItemWeight];		//商品重量
	float GetCtWeight			= (float)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColCtWeight];			//カートン重量
	float GetCsWeight			= (float)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColCsWeight];			//ケース重量
	float GetPlWeight			= (float)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColPlWeight];			//パレット重量
	float GetItemSize			= (float)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColItemSize];			//商品サイズ
	float GetCtSize				= (float)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColCtSize];			//カートンサイズ
	float GetCsSize				= (float)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColCsSize];			//ケースサイズ
	float GetPlSize				= (float)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColPlSize];			//パレットサイズ
	String GetCategoryCd		= (String)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColCategoryCd];		//商品カテゴリCD
	String GetCategoryName		= (String)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColCategoryName];		//商品カテゴリ名
	String GetItemColorCd		= (String)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColItemColorCd];		//商品カラーコード
	String GetItemColorName		= (String)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColItemColorName];	//商品カラー名
	String GetItemSizeCd		= (String)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColItemSizeCd];		//商品サイズコード
	String GetItemSizeName		= (String)ShipPlovisionRt[i][T100_ShipPlovisionRt.ColItemSizeName];		//商品サイズ名
						
	*/
	
	static final int Colcl_cd					=  0;
	static final int ColInvoiceWHCD			=  1;
	static final int ColOkuriNo				=  2;
	static final int ColMsNo					=  3;
	static final int ColSeq					=  4;
	static final int ColOrderItemCd			=  5;
	static final int ColOrderItemName01		=  6;
	static final int ColOrderLot				=  7;
	static final int ColOrderExpDate			=  8;
	static final int ColOrderQty				=  9;
	static final int ColShipWhCd				= 10;
	static final int ColShipLoc				= 11;
	static final int ColShipItemCd			= 12;
	static final int ColShipLot				= 13;
	static final int ColShipExpdate			= 14;
	static final int ColShipActualDate		= 15;
	static final int ColShipQty				= 16;
	static final int ColFixFg					= 17;
	static final int ColPackingType			= 18;
	static final int ColPackingQty			= 19;
	static final int ColUnitName				= 20;
	static final int ColPackingUnitQty		= 21;
	static final int ColBRShipQty				= 22;
	static final int ColCTShipQty				= 23;
	static final int ColCSShipQty				= 24;
	static final int ColPLShipQty				= 25;
	static final int ColBRUnitName			= 26;
	static final int ColCTUnitName			= 27;
	static final int ColCSUnitName			= 28;
	static final int ColPLUnitName			= 29;
	static final int ColEntryDate				= 30;
	static final int ColUpdateDate			= 31;
	static final int ColEntryUser				= 32;
	static final int ColUpdateUser			= 33;
			
	static final int ColClDeliNo				= 34;
	static final int ColPlanDate				= 35;
	static final int ColShipDate				= 36;
	static final int ColDeliCd					= 37;
	static final int ColClDeliCd				= 38;
	static final int ColDeliDepartmentCd		= 39;
	static final int ColDeliName01			= 40;
	static final int ColDeliName02			= 41;
	static final int ColDeliName03			= 42;
	static final int ColDeliPost				= 43;
	static final int ColDeliAdd01				= 44;
	static final int ColDeliAdd02				= 45;
	static final int ColDeliAdd03				= 46;
	static final int ColDeliTel				= 47;
	static final int ColDeliFax				= 48;
	static final int ColDeliMail				= 49;
	static final int ColDeliMunicCd			= 50;
	
	static final int ColCtQty					= 51;
	static final int ColCsQty					= 52;
	static final int ColPlQty					= 53;
	static final int ColJanCd					= 54;
	static final int ColCtJan					= 55;
	static final int ColCsJan					= 56;
	static final int ColPlJan					= 57;
	static final int ColCtName					= 58;
	static final int ColCsName					= 59;
	static final int ColPlName					= 60;
	static final int ColCtUnitName			= 61;
	static final int ColCsUnitName			= 62;
	static final int ColPlUnitName			= 63;
	static final int ColItemWeight			= 64;
	static final int ColCtWeight				= 65;
	static final int ColCsWeight				= 66;
	static final int ColPlWeight				= 67;
	static final int ColItemSize				= 68;
	static final int ColCtSize					= 69;
	static final int ColCsSize					= 70;
	static final int ColPlSize					= 71;
	static final int ColCategoryCd			= 72;
	static final int ColCategoryName			= 73;
	static final int ColItemColorCd			= 74;
	static final int ColItemColorName			= 75;
	static final int ColItemSizeCd			= 76;
	static final int ColItemSizeName			= 77;
	
	static final int ColSearchcl_cd					=  0;
	static final int ColSearchInvoiceWHCD			=  1;
	static final int ColSearchOkuriNo					=  2;
	static final int ColSearchMsNoMin					=  3;
	static final int ColSearchSeqMin					=  4;
	static final int ColSearchMsNoMax					=  5;
	static final int ColSearchSeqMax					=  6;
	static final int ColSearchOrderItemCd			=  7;
	static final int ColSearchOrderItemName			=  8;
	static final int ColSearchOrderLot				=  9;
	static final int ColSearchOrderExpDateStr		= 10;
	static final int ColSearchOrderExpDateEnd		= 11;
	
	static final int ColSearchOrderQtyMin			= 12;
	static final int ColSearchOrderQtyMax			= 13;
	static final int ColSearchShipWhCd				= 14;
	static final int ColSearchShipLoc					= 15;
	static final int ColSearchShipItemCd				= 16;
	static final int ColSearchShipLot					= 17;
	static final int ColSearchShipExpdateStr			= 18;
	static final int ColSearchShipActualDateStr		= 19;
	static final int ColSearchShipExpdateEnd			= 20;
	static final int ColSearchShipActualDateEnd		= 21;
	static final int ColSearchShipQtyMin				= 22;
	static final int ColSearchShipQtyMax				= 23;
	static final int ColSearchFixFg					= 24;
	static final int ColSearchPackingType			= 25;
	static final int ColSearchPackingQtyMin			= 26;
	static final int ColSearchPackingQtyMax			= 27;
	static final int ColSearchBRShipQtyMin			= 28;
	static final int ColSearchCTShipQtyMin			= 29;
	static final int ColSearchCSShipQtyMin			= 30;
	static final int ColSearchPLShipQtyMin			= 31;
	static final int ColSearchBRShipQtyMax			= 32;
	static final int ColSearchCTShipQtyMax			= 33;
	static final int ColSearchCSShipQtyMax			= 34;
	static final int ColSearchPLShipQtyMax			= 35;
	static final int ColSearchEntryDateStr			= 36;
	static final int ColSearchUpdateDateStr			= 37;
	static final int ColSearchEntryDateEnd			= 38;
	static final int ColSearchUpdateDateEnd			= 39;
	static final int ColSearchEntryUser				= 40;
	static final int ColSearchUpdateUser				= 41;
		
	static final int ColSearchClDeliNo				= 42;
	static final int ColSearchPlanDateStr			= 43;
	static final int ColSearchShipDateStr			= 44;
	static final int ColSearchPlanDateEnd			= 45;
	static final int ColSearchShipDateEnd			= 46;
			
	static final int ColSearchDeliCd					= 47;
	static final int ColSearchClDeliCd				= 48;
	static final int ColSearchDeliDepartmentCd		= 49;
	static final int ColSearchDeliName				= 50;
	static final int ColSearchDeliPost				= 51;
	static final int ColSearchDeliAdd					= 52;
	static final int ColSearchDeliTel					= 53;
	static final int ColSearchDeliFax					= 54;
	static final int ColSearchDeliMail				= 55;
	static final int ColSearchDeliMunicCd			= 56;
			 
	static final int ColSearchClGpCD					= 57;
	static final int ColSearchClCd					= 58;
	static final int ColSearchPickupWhCd				= 59;
	static final int ColSearchPurposeFG				= 60;
	static final int ColSearchSPPlanDateStr			= 61;
	static final int ColSearchSPDateStr				= 62;
	static final int ColSearchSPPlanDateEnd			= 63;
	static final int ColSearchSPDateEnd				= 64;
	
	static final int ColSearchCategoryCd				= 65;
	static final int ColSearchCategoryName			= 66;
	static final int ColSearchItemColorCd			= 67;
	static final int ColSearchItemColorName			= 68;
	static final int ColSearchItemSizeCd				= 69;
	static final int ColSearchItemSizeName			= 70;
	
	
	public static Object[][] RtShipPlovisionRt(){
		Object[][] Rt = {
						 {"cl_cd"				,Colcl_cd					,"String"	,"荷主コード"			,"Key"		,"Client Code"		,"货主代码"		,"Mã chủ hàng"}
						,{"InvoiceWHCD"			,ColInvoiceWHCD			,"String"	,"倉庫コード"			,"Key"		,"Warehouse Code"		,"仓库代码"		,"Mã kho"}
						,{"OkuriNo"				,ColOkuriNo				,"String"	,"送り状番号"			,"Key"		,"Waybill No."		,"运单号"		,"Số vận đơn"}
						,{"MsNo"				,ColMsNo					,"int"		,"明細番号"				,"Key"		,"Detail No."		,"明细编号"		,"Số chi tiết"}
 						,{"Seq"					,ColSeq					,"int"		,"引当枝番"				,"Key"		,"Allocation Seq. No."		,"分配序号"		,"Số thứ tự phân bổ"}
						,{"OrderItemCd"			,ColOrderItemCd			,"String"	,"商品コード"			,""		,"Item Code"		,"商品代码"		,"Mã hàng"}
						,{"OrderItemName01"		,ColOrderItemName01		,"String"	,"商品表記名"			,""		,"Display Item Name"		,"商品显示名称"		,"Tên hiển thị"}
						,{"OrderLot"			,ColOrderLot				,"String"	,"受注ロット指定"		,""		,"Order Specified Lot"		,"订单指定批次"		,"Lô chỉ định theo đơn"}
						,{"OrderExpDate"		,ColOrderExpDate			,"Date"		,"受注賞味期限指定"		,""		,"Order Specified Expiration Date"		,"订单指定保质期"		,"Hạn sử dụng chỉ định theo đơn"}
						,{"OrderQty"			,ColOrderQty				,"int"		,"受注個数"				,""		,"Order Qty"		,"订单数量"		,"SL đặt hàng"}
						,{"ShipWhCd"			,ColShipWhCd				,"String"	,"出荷倉庫コード"		,""		,"Shipping Warehouse Code"		,"出库仓库代码"		,"Mã kho xuất hàng"}
 						,{"ShipLoc"				,ColShipLoc				,"String"	,"ロケーション"			,""		,"Location"		,"库位"		,"Vị trí"}
						,{"ShipItemCd"			,ColShipItemCd			,"String"	,"商品コード"			,""		,"Item Code"		,"商品代码"		,"Mã hàng"}
						,{"ShipLot"				,ColShipLot				,"String"	,"ロット"				,""		,"Lot"		,"批次"		,"Lô"}
						,{"ShipExpdate"			,ColShipExpdate			,"Date"		,"消費期限"				,""		,"Expiration Date"		,"有效期"		,"Hạn sử dụng"}
						,{"ShipActualDate"		,ColShipActualDate		,"Date"		,"入荷実績日"			,""		,"Receipt Date"		,"入库日期"		,"Ngày nhập kho"}
						,{"ShipQty"				,ColShipQty				,"int"		,"引当数量"				,""		,"Allocated Qty"		,"已分配数量"		,"SL đã phân bổ"}
						,{"FixFg"				,ColFixFg					,"int"		,"引落済フラグ"			,""		,"Stock Deducted Flag"		,"库存已扣减标志"		,"Cờ đã trừ tồn kho"}
						,{"PackingType"			,ColPackingType			,"int"		,"荷姿タイプ"			,""		,"Packing Type"		,"包装类型"		,"Loại đóng gói"}
						,{"PackingQty"			,ColPackingQty			,"int"		,"荷姿数量"				,""		,"Packing Qty"		,"包装数量"		,"SL theo loại đóng gói"}
						,{"UnitName"			,ColUnitName				,"String"	,"荷姿単位"				,""		,"Packing Unit"		,"包装单位"		,"Đơn vị đóng gói"}
						,{"PackingUnitQty"		,ColPackingUnitQty		,"int"		,"荷姿単位のバラ入数"	,""		,"Each Qty per Packing Unit"		,"每包装单位单品数"		,"SL hàng lẻ mỗi đơn vị đóng gói"}
						,{"BRShipQty"			,ColBRShipQty				,"int"		,"バラ数量"				,""		,"Each Qty"		,"单品数量"		,"SL hàng lẻ"}
						,{"CTShipQty"			,ColCTShipQty				,"int"		,"カートン数量"			,""		,"Carton Qty"		,"纸箱数量"		,"SL carton"}
						,{"CSShipQty"			,ColCSShipQty				,"int"		,"ケース数量"			,""		,"Case Qty"		,"箱数量"		,"SL thùng"}
						,{"PLShipQty"			,ColPLShipQty				,"int"		,"パレット数量"			,""		,"Pallet Qty"		,"托盘数量"		,"SL pallet"}
						,{"BRUnitName"			,ColBRUnitName			,"String"	,"バラ単位名"			,""		,"Each Unit Name"		,"单品单位名称"		,"Tên đơn vị hàng lẻ"}
						,{"CTUnitName"			,ColCTUnitName			,"String"	,"カートン単位名"		,""		,"Carton Unit Name"		,"纸箱单位名称"		,"Tên đơn vị carton"}
						,{"CSUnitName"			,ColCSUnitName			,"String"	,"ケース単位名"			,""		,"Case Unit Name"		,"箱单位名称"		,"Tên đơn vị thùng"}
						,{"PLUnitName"			,ColPLUnitName			,"String"	,"パレット単位名"		,""		,"Pallet Unit Name"		,"托盘单位名称"		,"Tên đơn vị pallet"}
						,{"EntryDate"			,ColEntryDate				,"DateTime"	,"登録日時"				,""		,"Created At"		,"登记时间"		,"Ngày giờ tạo"}
						,{"UpdateDate"			,ColUpdateDate			,"DateTime"	,"更新日時"				,""		,"Updated At"		,"更新时间"		,"Ngày giờ cập nhật"}
						,{"EntryUser"			,ColEntryUser				,"String"	,"登録者"				,""		,"Created By"		,"登记人"		,"Người tạo"}
						,{"UpdateUser"			,ColUpdateUser			,"String"	,"更新者"				,""		,"Updated By"		,"更新人"		,"Người cập nhật"}
						
						,{"ClDeliNo"			,ColClDeliNo				,"String"	,"荷主管理番号"			,""		,"Client Reference No."					,"货主管理编号"			,"Số tham chiếu chủ hàng"}
						,{"PlanDate"			,ColPlanDate				,"Date"		,"出荷予定日"			,""		,"Planned Ship Date"					,"计划出库日期"			,"Ngày xuất dự kiến"}
						,{"ShipDate"			,ColShipDate				,"DateTime"	,"出荷実績日"			,""		,"Actual Ship Date"						,"实际出库日期"			,"Ngày xuất thực tế"}
						,{"DeliCd"				,ColDeliCd					,"String"	,"荷届先コード"			,""		,"Delivery Destination Code"			,"收货地代码"				,"Mã điểm giao hàng"}
						,{"ClDeliCd"			,ColClDeliCd				,"String"	,"荷主荷届先コード"		,""		,"Client Destination Code"				,"货主收货地代码"			,"Mã điểm giao của chủ hàng"}
						,{"DeliDepartmentCd"	,ColDeliDepartmentCd		,"String"	,"部署CD"				,""		,"Destination Department Code"			,"收货地部门代码"			,"Mã bộ phận điểm giao"}
						,{"DeliName01"			,ColDeliName01			,"String"	,"荷届先名01"			,""		,"Destination Name 01"					,"收货地名称01"			,"Tên điểm giao 01"}
						,{"DeliName02"			,ColDeliName02			,"String"	,"荷届先名02"			,""		,"Destination Name 02"					,"收货地名称02"			,"Tên điểm giao 02"}
						,{"DeliName03"			,ColDeliName03			,"String"	,"荷届先名03"			,""		,"Destination Name 03"					,"收货地名称03"			,"Tên điểm giao 03"}
						,{"DeliPost"			,ColDeliPost				,"String"	,"荷届先郵便番号"		,""		,"Destination Postal Code"				,"收货地邮政编码"			,"Mã bưu chính điểm giao"}
						,{"DeliAdd01"			,ColDeliAdd01				,"String"	,"荷届先住所01"			,""		,"Destination Address 01"				,"收货地地址01"			,"Địa chỉ điểm giao 01"}
						,{"DeliAdd02"			,ColDeliAdd02				,"String"	,"荷届先住所02"			,""		,"Destination Address 02"				,"收货地地址02"			,"Địa chỉ điểm giao 02"}
						,{"DeliAdd03"			,ColDeliAdd03				,"String"	,"荷届先住所03"			,""		,"Destination Address 03"				,"收货地地址03"			,"Địa chỉ điểm giao 03"}
						,{"DeliTel"				,ColDeliTel				,"String"	,"荷届先TEL"			,""		,"Destination TEL"						,"收货地电话"				,"Điện thoại điểm giao"}
						,{"DeliFax"				,ColDeliFax				,"String"	,"荷届先FAX"			,""		,"Destination FAX"						,"收货地传真"				,"FAX điểm giao"}
						,{"DeliMail"			,ColDeliMail				,"String"	,"荷届先MAIL"			,""		,"Destination Email"					,"收货地邮箱"				,"Email điểm giao"}
						,{"DeliMunicCd"			,ColDeliMunicCd			,"String"	,"荷届先市区町村CD"		,""		,"Destination Municipality Code"		,"收货地市区町村代码"		,"Mã địa phương điểm giao"}
						
						,{"CtQty"				,ColCtQty					,"int"		,"カートン入数"			,""		,"Carton Pack Qty"						,"纸箱装量"				,"SL mỗi carton"}
						,{"CsQty"				,ColCsQty					,"int"		,"ケース入数"			,""		,"Case Pack Qty"						,"箱装量"				,"SL mỗi thùng"}
						,{"PlQty"				,ColPlQty					,"int"		,"パレット入数"			,""		,"Pallet Pack Qty"						,"托盘装量"				,"SL mỗi pallet"}
						,{"JanCd"				,ColJanCd					,"String"	,"JANCD"				,""		,"JAN Code"								,"JAN代码"				,"Mã JAN"}
						,{"CtJan"				,ColCtJan					,"String"	,"カートンバーコード"	,""		,"Carton Barcode"						,"纸箱条码"				,"Mã vạch carton"}
						,{"CsJan"				,ColCsJan					,"String"	,"ケースバーコード"		,""		,"Case Barcode"							,"箱条码"				,"Mã vạch thùng"}
						,{"PlJan"				,ColPlJan					,"String"	,"パレットバーコード"	,""		,"Pallet Barcode"						,"托盘条码"				,"Mã vạch pallet"}
						,{"CtName"				,ColCtName					,"String"	,"カートン商品名称"		,""		,"Carton Item Name"						,"纸箱商品名称"			,"Tên hàng carton"}
						,{"CsName"				,ColCsName					,"String"	,"ケース商品名称"		,""		,"Case Item Name"						,"箱商品名称"			,"Tên hàng thùng"}
						,{"PlName"				,ColPlName					,"String"	,"パレット商品名称"		,""		,"Pallet Item Name"						,"托盘商品名称"			,"Tên hàng pallet"}
						,{"CtUnitName"			,ColCtUnitName			,"String"	,"カートン商品単位"		,""		,"Carton Unit"							,"纸箱单位"				,"Đơn vị carton"}
						,{"CsUnitName"			,ColCsUnitName			,"String"	,"ケース商品単位"		,""		,"Case Unit"							,"箱单位"					,"Đơn vị thùng"}
						,{"PlUnitName"			,ColPlUnitName			,"String"	,"パレット商品単位"		,""		,"Pallet Unit"							,"托盘单位"				,"Đơn vị pallet"}
						,{"ItemWeight"			,ColItemWeight			,"float"	,"商品重量"				,""		,"Item Weight"							,"商品重量"				,"Trọng lượng hàng"}
						,{"CtWeight"			,ColCtWeight				,"float"	,"カートン重量"			,""		,"Carton Weight"						,"纸箱重量"				,"Trọng lượng carton"}
						,{"CsWeight"			,ColCsWeight				,"float"	,"ケース重量"			,""		,"Case Weight"							,"箱重量"				,"Trọng lượng thùng"}
						,{"PlWeight"			,ColPlWeight				,"float"	,"パレット重量"			,""		,"Pallet Weight"						,"托盘重量"				,"Trọng lượng pallet"}
						,{"ItemSize"			,ColItemSize				,"float"	,"商品サイズ"			,""		,"Item Size"							,"商品尺寸"				,"Kích thước hàng"}
						,{"CtSize"				,ColCtSize					,"float"	,"カートンサイズ"		,""		,"Carton Size"							,"纸箱尺寸"				,"Kích thước carton"}
						,{"CsSize"				,ColCsSize					,"float"	,"ケースサイズ"			,""		,"Case Size"							,"箱尺寸"				,"Kích thước thùng"}
						,{"PlSize"				,ColPlSize					,"float"	,"パレットサイズ"		,""		,"Pallet Size"							,"托盘尺寸"				,"Kích thước pallet"}
						,{"CategoryCd"			,ColCategoryCd			,"String"	,"商品カテゴリCD"		,""		,"Item Category Code"					,"商品分类代码"			,"Mã phân loại hàng"}
						,{"CategoryName"		,ColCategoryName			,"String"	,"商品カテゴリ名"		,""		,"Item Category Name"					,"商品分类名称"			,"Tên phân loại hàng"}
						,{"ItemColorCd"			,ColItemColorCd			,"String"	,"商品カラーコード"		,""		,"Item Color Code"						,"商品颜色代码"			,"Mã màu hàng"}
						,{"ItemColorName"		,ColItemColorName			,"String"	,"商品カラー名"			,""		,"Item Color Name"						,"商品颜色名称"			,"Tên màu hàng"}
						,{"ItemSizeCd"			,ColItemSizeCd			,"String"	,"商品サイズコード"		,""		,"Item Size Code"						,"商品尺寸代码"			,"Mã kích thước hàng"}
						,{"ItemSizeName"		,ColItemSizeName			,"String"	,"商品サイズ名"			,""		,"Item Size Name"						,"商品尺寸名称"			,"Tên kích thước hàng"}
						};
		Rt = B100_LanguageControl.RtControl(Rt);
		return Rt;
	}
	
	public static Object[][] DefinitionRt(){
		Object[][] Definition = {
				 {"String"		,null,	"Exact"			,ColSearchcl_cd					,B100_DefaultVariable.SearchClList		,"荷主コード"			,""		,"Client Code"		,""		,"货主代码"		,""		,"Mã chủ hàng"		,""}
				,{"String"		,null,	"Exact"			,ColSearchInvoiceWHCD			,B100_DefaultVariable.SearchWhList		,"倉庫コード"			,""		,"Warehouse Code"		,""		,"仓库代码"		,""		,"Mã kho"		,""}
				,{"String"		,null,	"Exact"			,ColSearchOkuriNo					,""											,"送り状番号"			,""		,"Waybill No."		,""		,"运单号"		,""		,"Số vận đơn"		,""}
				,{"Integer"		,null,	"RangeStr"		,ColSearchMsNoMin					,""											,"明細番号"				,"最小"		,"Detail No."		,"Min"		,"明细编号"		,"最小"		,"Số chi tiết"		,"Tối thiểu"}
				,{"Integer"		,null,	"RangeStr"		,ColSearchSeqMin					,""											,"引当枝番"				,"最小"		,"Allocation Seq. No."		,"Min"		,"分配序号"		,"最小"		,"Số thứ tự phân bổ"		,"Tối thiểu"}
				,{"Integer"		,null,	"RangeEnd"		,ColSearchMsNoMax					,""											,"明細番号"				,"最大"		,"Detail No."		,"Max"		,"明细编号"		,"最大"		,"Số chi tiết"		,"Tối đa"}
				,{"Integer"		,null,	"RangeEnd"		,ColSearchSeqMax					,""											,"引当枝番"				,"最大"		,"Allocation Seq. No."		,"Max"		,"分配序号"		,"最大"		,"Số thứ tự phân bổ"		,"Tối đa"}
				,{"String"		,null,	"Exact"			,ColSearchOrderItemCd			,""											,"商品コード"			,""		,"Item Code"		,""		,"商品代码"		,""		,"Mã hàng"		,""}
				,{"String"		,null,	"Partial"		,ColSearchOrderItemName				,""											,"商品表記名"			,""		,"Display Item Name"		,""		,"商品显示名称"		,""		,"Tên hiển thị"		,""}
				,{"String"		,null,	"Exact"			,ColSearchOrderLot				,""											,"受注ロット指定"		,""		,"Order Specified Lot"		,""		,"订单指定批次"		,""		,"Lô chỉ định theo đơn"		,""}
				,{"Date"		,null,	"RangeStr"		,ColSearchOrderExpDateStr		,""											,"受注賞味期限指定"		,"開始"		,"Order Specified Expiration Date"		,"From"		,"订单指定保质期"		,"开始"		,"Hạn sử dụng chỉ định theo đơn"		,"Từ"}
				,{"Date"		,null,	"RangeEnd"		,ColSearchOrderExpDateEnd		,""											,"受注賞味期限指定"		,"終了"		,"Order Specified Expiration Date"		,"To"		,"订单指定保质期"		,"结束"		,"Hạn sử dụng chỉ định theo đơn"		,"Đến"}
				,{"Integer"		,null,	"RangeStr"		,ColSearchOrderQtyMin			,""											,"受注個数"				,"最小"		,"Order Qty"		,"Min"		,"订单数量"		,"最小"		,"SL đặt hàng"		,"Tối thiểu"}
				,{"Integer"		,null,	"RangeEnd"		,ColSearchOrderQtyMax			,""											,"受注個数"				,"最大"		,"Order Qty"		,"Max"		,"订单数量"		,"最大"		,"SL đặt hàng"		,"Tối đa"}
				,{"String"		,null,	"Exact"			,ColSearchShipWhCd				,B100_DefaultVariable.SearchWhList		,"出荷倉庫コード"		,""		,"Shipping Warehouse Code"		,""		,"出库仓库代码"		,""		,"Mã kho xuất hàng"		,""}
				,{"String"		,null,	"Prefix"		,ColSearchShipLoc					,""											,"ロケーション"			,""		,"Location"		,""		,"库位"		,""		,"Vị trí"		,""}
				,{"String"		,null,	"Exact"			,ColSearchShipItemCd				,""											,"商品コード"			,""		,"Item Code"		,""		,"商品代码"		,""		,"Mã hàng"		,""}
				,{"String"		,null,	"Exact"			,ColSearchShipLot					,""											,"ロット"				,""		,"Lot"		,""		,"批次"		,""		,"Lô"		,""}
				,{"Date"		,null,	"RangeStr"		,ColSearchShipExpdateStr			,""											,"消費期限"				,"開始"		,"Expiration Date"		,"From"		,"有效期"		,"开始"		,"Hạn sử dụng"		,"Từ"}
				,{"Date"		,null,	"RangeStr"		,ColSearchShipActualDateStr		,""											,"入荷実績日"			,"開始"		,"Receipt Date"		,"From"		,"入库日期"		,"开始"		,"Ngày nhập kho"		,"Từ"}
				,{"Date"		,null,	"RangeEnd"		,ColSearchShipExpdateEnd			,""											,"消費期限"				,"終了"		,"Expiration Date"		,"To"		,"有效期"		,"结束"		,"Hạn sử dụng"		,"Đến"}
				,{"Date"		,null,	"RangeEnd"		,ColSearchShipActualDateEnd		,""											,"入荷実績日"			,"終了"		,"Receipt Date"		,"To"		,"入库日期"		,"结束"		,"Ngày nhập kho"		,"Đến"}
				,{"Integer"		,null,	"RangeStr"		,ColSearchShipQtyMin				,""											,"引当数量"				,"最小"		,"Allocated Qty"		,"Min"		,"已分配数量"		,"最小"		,"SL đã phân bổ"		,"Tối thiểu"}
				,{"Integer"		,null,	"RangeEnd"		,ColSearchShipQtyMax				,""											,"引当数量"				,"最大"		,"Allocated Qty"		,"Max"		,"已分配数量"		,"最大"		,"SL đã phân bổ"		,"Tối đa"}
				,{"Integer"		,null,	"Exact"			,ColSearchFixFg					,""											,"引落済フラグ"			,""		,"Stock Deducted Flag"		,""		,"库存已扣减标志"		,""		,"Cờ đã trừ tồn kho"		,""}
				,{"Integer"		,null,	"Exact"			,ColSearchPackingType			,""											,"荷姿タイプ"			,""		,"Packing Type"		,""		,"包装类型"		,""		,"Loại đóng gói"		,""}
				,{"Integer"		,null,	"RangeStr"		,ColSearchPackingQtyMin			,""											,"荷姿数量"				,"最小"		,"Packing Qty"		,"Min"		,"包装数量"		,"最小"		,"SL theo loại đóng gói"		,"Tối thiểu"}
				,{"Integer"		,null,	"RangeEnd"		,ColSearchPackingQtyMax			,""											,"荷姿数量"				,"最大"		,"Packing Qty"		,"Max"		,"包装数量"		,"最大"		,"SL theo loại đóng gói"		,"Tối đa"}
				,{"Integer"		,null,	"RangeStr"		,ColSearchBRShipQtyMin			,""											,"バラ数量"				,"最小"		,"Each Qty"		,"Min"		,"单品数量"		,"最小"		,"SL hàng lẻ"		,"Tối thiểu"}
				,{"Integer"		,null,	"RangeStr"		,ColSearchCTShipQtyMin			,""											,"カートン数量"			,"最小"		,"Carton Qty"		,"Min"		,"纸箱数量"		,"最小"		,"SL carton"		,"Tối thiểu"}
				,{"Integer"		,null,	"RangeStr"		,ColSearchCSShipQtyMin			,""											,"ケース数量"			,"最小"		,"Case Qty"		,"Min"		,"箱数量"		,"最小"		,"SL thùng"		,"Tối thiểu"}
				,{"Integer"		,null,	"RangeStr"		,ColSearchPLShipQtyMin			,""											,"パレット数量"			,"最小"		,"Pallet Qty"		,"Min"		,"托盘数量"		,"最小"		,"SL pallet"		,"Tối thiểu"}
				,{"Integer"		,null,	"RangeEnd"		,ColSearchBRShipQtyMax			,""											,"バラ数量"				,"最大"		,"Each Qty"		,"Max"		,"单品数量"		,"最大"		,"SL hàng lẻ"		,"Tối đa"}
				,{"Integer"		,null,	"RangeEnd"		,ColSearchCTShipQtyMax			,""											,"カートン数量"			,"最大"		,"Carton Qty"		,"Max"		,"纸箱数量"		,"最大"		,"SL carton"		,"Tối đa"}
				,{"Integer"		,null,	"RangeEnd"		,ColSearchCSShipQtyMax			,""											,"ケース数量"			,"最大"		,"Case Qty"		,"Max"		,"箱数量"		,"最大"		,"SL thùng"		,"Tối đa"}
				,{"Integer"		,null,	"RangeEnd"		,ColSearchPLShipQtyMax			,""											,"パレット数量"			,"最大"		,"Pallet Qty"		,"Max"		,"托盘数量"		,"最大"		,"SL pallet"		,"Tối đa"}
				,{"DateTime"	,null,	"RangeStr"		,ColSearchEntryDateStr			,""											,"登録日時"				,"開始"		,"Created At"		,"From"		,"登记时间"		,"开始"		,"Ngày giờ tạo"		,"Từ"}
				,{"DateTime"	,null,	"RangeStr"		,ColSearchUpdateDateStr			,""											,"更新日時"				,"開始"		,"Updated At"		,"From"		,"更新时间"		,"开始"		,"Ngày giờ cập nhật"		,"Từ"}
				,{"DateTime"	,null,	"RangeEnd"		,ColSearchEntryDateEnd			,""											,"登録日時"				,"終了"		,"Created At"		,"To"		,"登记时间"		,"结束"		,"Ngày giờ tạo"		,"Đến"}
				,{"DateTime"	,null,	"RangeEnd"		,ColSearchUpdateDateEnd			,""											,"更新日時"				,"終了"		,"Updated At"		,"To"		,"更新时间"		,"结束"		,"Ngày giờ cập nhật"		,"Đến"}
				,{"String"		,null,	"Partial"		,ColSearchEntryUser				,""											,"登録者"				,""		,"Created By"		,""		,"登记人"		,""		,"Người tạo"		,""}
				,{"String"		,null,	"Partial"		,ColSearchUpdateUser				,""											,"更新者"				,""		,"Updated By"		,""		,"更新人"		,""		,"Người cập nhật"		,""}
				
				,{"String"		,null,	"Exact"			,ColSearchClDeliNo				,""											,"荷主管理番号"			,""		,"Client Reference No."		,""		,"货主管理编号"		,""		,"Số tham chiếu chủ hàng"		,""}
				,{"Date"		,null,	"RangeStr"		,ColSearchPlanDateStr			,""											,"出荷予定日"			,"開始"		,"Planned Ship Date"		,"From"		,"计划出库日期"		,"开始"		,"Ngày xuất dự kiến"		,"Từ"}
				,{"Date"		,null,	"RangeStr"		,ColSearchShipDateStr			,""											,"出荷実績日"			,"開始"		,"Actual Ship Date"		,"From"		,"实际出库日期"		,"开始"		,"Ngày xuất thực tế"		,"Từ"}
				,{"Date"		,null,	"RangeEnd"		,ColSearchPlanDateEnd			,""											,"出荷予定日"			,"終了"		,"Planned Ship Date"		,"To"		,"计划出库日期"		,"结束"		,"Ngày xuất dự kiến"		,"Đến"}
				,{"Date"		,null,	"RangeEnd"		,ColSearchShipDateEnd			,""											,"出荷実績日"			,"終了"		,"Actual Ship Date"		,"To"		,"实际出库日期"		,"结束"		,"Ngày xuất thực tế"		,"Đến"}
				
				,{"String"		,null,	"Exact"			,ColSearchDeliCd					,""											,"荷届先コード"			,""		,"Delivery Destination Code"		,""		,"收货地代码"		,""		,"Mã điểm giao hàng"		,""}
				,{"String"		,null,	"Exact"			,ColSearchClDeliCd				,""											,"荷主荷届先コード"		,""		,"Client Destination Code"		,""		,"货主收货地代码"		,""		,"Mã điểm giao của chủ hàng"		,""}
				,{"String"		,null,	"Exact"			,ColSearchDeliDepartmentCd		,""											,"部署CD"				,""		,"Destination Department Code"		,""		,"收货地部门代码"		,""		,"Mã bộ phận điểm giao"		,""}
				,{"String"		,null,	"Partial"		,ColSearchDeliName				,""											,"荷届先名"				,""		,"Destination Name"		,""		,"收货地名称"		,""		,"Tên điểm giao"		,""}
				,{"String"		,null,	"Prefix"		,ColSearchDeliPost				,""											,"荷届先郵便番号"		,""		,"Destination Postal Code"		,""		,"收货地邮政编码"		,""		,"Mã bưu chính điểm giao"		,""}
				,{"String"		,null,	"Partial"		,ColSearchDeliAdd					,""											,"荷届先住所"			,""		,"Destination Address"		,""		,"收货地地址"		,""		,"Địa chỉ điểm giao"		,""}
				,{"String"		,null,	"Partial"		,ColSearchDeliTel					,""											,"荷届先TEL"			,""		,"Destination TEL"		,""		,"收货地电话"		,""		,"Điện thoại điểm giao"		,""}
				,{"String"		,null,	"Partial"		,ColSearchDeliFax					,""											,"荷届先FAX"			,""		,"Destination FAX"		,""		,"收货地传真"		,""		,"FAX điểm giao"		,""}
				,{"String"		,null,	"Partial"		,ColSearchDeliMail				,""											,"荷届先MAIL"			,""		,"Destination Email"		,""		,"收货地邮箱"		,""		,"Email điểm giao"		,""}
				,{"String"		,null,	"Exact"			,ColSearchDeliMunicCd			,""											,"荷届先市区町村CD"		,""		,"Destination Municipality Code"		,""		,"收货地市区町村代码"		,""		,"Mã địa phương điểm giao"		,""}
				 
				,{"String"		,null,	"Exact"			,ColSearchClGpCD					,B100_DefaultVariable.SearchClGpList		,"荷主グループCD"		,""		,"Client Group Code"		,""		,"货主组代码"		,""		,"Mã nhóm chủ hàng"		,""}
				,{"String"		,null,	"Exact"			,ColSearchClCd					,B100_DefaultVariable.SearchClList		,"荷主CD"				,""		,"Client Code"		,""		,"货主代码"		,""		,"Mã chủ hàng"		,""}
				,{"String"		,null,	"Exact"			,ColSearchPickupWhCd				,B100_DefaultVariable.SearchWhList		,"集荷倉庫CD"			,""		,"Pickup Warehouse Code"		,""		,"提货仓库代码"		,""		,"Mã kho lấy hàng"		,""}
				,{"String"		,null,	"Exact"			,ColSearchPurposeFG				,B100_DefaultVariable.SearchPurposeList	,"目的フラグ"			,""		,"Purpose Flag"		,""		,"目的标志"		,""		,"Cờ mục đích"		,""}
				,{"Date"		,null,	"RangeStr"		,ColSearchSPPlanDateStr			,""											,"着日指定"				,"開始"		,"Requested Delivery Date"		,"From"		,"指定到货日期"		,"开始"		,"Ngày giao yêu cầu"		,"Từ"}
				,{"Date"		,null,	"RangeStr"		,ColSearchSPDateStr				,""											,"着日実績"				,"開始"		,"Actual Delivery Date"		,"From"		,"实际到货日期"		,"开始"		,"Ngày giao thực tế"		,"Từ"}
				,{"Date"		,null,	"RangeEnd"		,ColSearchSPPlanDateEnd			,""											,"着日指定"				,"終了"		,"Requested Delivery Date"		,"To"		,"指定到货日期"		,"结束"		,"Ngày giao yêu cầu"		,"Đến"}
				,{"Date"		,null,	"RangeEnd"		,ColSearchSPDateEnd				,""											,"着日実績"				,"終了"		,"Actual Delivery Date"		,"To"		,"实际到货日期"		,"结束"		,"Ngày giao thực tế"		,"Đến"}

				,{"String"		,null,	"Exact"			,ColSearchCategoryCd				,""											,"商品カテゴリCD"		,""		,"Item Category Code"		,""		,"商品分类代码"		,""		,"Mã phân loại hàng"		,""}
				,{"String"		,null,	"Partial"		,ColSearchCategoryName			,""											,"商品カテゴリ名"		,""		,"Item Category Name"		,""		,"商品分类名称"		,""		,"Tên phân loại hàng"		,""}
				,{"String"		,null,	"Exact"			,ColSearchItemColorCd			,""											,"商品カラーコード"		,""		,"Item Color Code"		,""		,"商品颜色代码"		,""		,"Mã màu hàng"		,""}
				,{"String"		,null,	"Partial"		,ColSearchItemColorName			,""											,"商品カラー名"			,""		,"Item Color Name"		,""		,"商品颜色名称"		,""		,"Tên màu hàng"		,""}
				,{"String"		,null,	"Exact"			,ColSearchItemSizeCd				,""											,"商品サイズコード"		,""		,"Item Size Code"		,""		,"商品尺寸代码"		,""		,"Mã kích thước hàng"		,""}
				,{"String"		,null,	"Partial"		,ColSearchItemSizeName			,""											,"商品サイズ名"			,""		,"Item Size Name"		,""		,"商品尺寸名称"		,""		,"Tên kích thước hàng"		,""}
				};
		Definition = B100_LanguageControl.DefinitionControl(Definition);
		
		return Definition;
	}
	
	public static Object[][] ShipPlovisionRt(
			 ArrayList<String> Searchcl_cd					//荷主コード
			,ArrayList<String> SearchInvoiceWHCD			//倉庫コード
			,ArrayList<String> SearchOkuriNo				//送り状番号
			,ArrayList<Integer> SearchMsNoMin				//明細番号
			,ArrayList<Integer> SearchSeqMin				//引当枝番
			,ArrayList<Integer> SearchMsNoMax				//明細番号
			,ArrayList<Integer> SearchSeqMax				//引当枝番
			,ArrayList<String> SearchOrderItemCd			//商品コード
			,ArrayList<String> SearchOrderItemName			//商品表記名
			,ArrayList<String> SearchOrderLot				//受注ロット指定
			,ArrayList<String> SearchOrderExpDateStr		//受注賞味期限指定
			,ArrayList<String> SearchOrderExpDateEnd		//受注賞味期限指定
			,ArrayList<Integer> SearchOrderQtyMin			//受注個数
			,ArrayList<Integer> SearchOrderQtyMax			//受注個数
			,ArrayList<String> SearchShipWhCd				//出荷倉庫コード
			,ArrayList<String> SearchShipLoc				//ロケーション
			,ArrayList<String> SearchShipItemCd				//商品コード
			,ArrayList<String> SearchShipLot				//ロット
			,ArrayList<String> SearchShipExpdateStr			//消費期限
			,ArrayList<String> SearchShipActualDateStr		//入荷実績日
			,ArrayList<String> SearchShipExpdateEnd			//消費期限
			,ArrayList<String> SearchShipActualDateEnd		//入荷実績日
			,ArrayList<String> SearchShipQtyMin				//引当数量
			,ArrayList<String> SearchShipQtyMax				//引当数量
			,ArrayList<Integer> SearchFixFg					//引落済フラグ
			,ArrayList<Integer> SearchPackingType			//荷姿タイプ
			,ArrayList<Integer> SearchPackingQtyMin			//荷姿数量
			,ArrayList<Integer> SearchPackingQtyMax			//荷姿数量
			,ArrayList<Integer> SearchBRShipQtyMin			//バラ数量
			,ArrayList<Integer> SearchCTShipQtyMin			//カートン数量
			,ArrayList<Integer> SearchCSShipQtyMin			//ケース数量
			,ArrayList<Integer> SearchPLShipQtyMin			//パレット数量
			,ArrayList<Integer> SearchBRShipQtyMax			//バラ数量
			,ArrayList<Integer> SearchCTShipQtyMax			//カートン数量
			,ArrayList<Integer> SearchCSShipQtyMax			//ケース数量
			,ArrayList<Integer> SearchPLShipQtyMax			//パレット数量
			,ArrayList<String> SearchEntryDateStr			//登録日時
			,ArrayList<String> SearchUpdateDateStr			//更新日時
			,ArrayList<String> SearchEntryDateEnd			//登録日時
			,ArrayList<String> SearchUpdateDateEnd			//更新日時
			,ArrayList<String> SearchEntryUser				//登録者
			,ArrayList<String> SearchUpdateUser				//更新者
				
			,ArrayList<String> SearchClDeliNo				//荷主管理番号
			,ArrayList<String> SearchPlanDateStr			//出荷予定日
			,ArrayList<String> SearchShipDateStr			//出荷実績日
			,ArrayList<String> SearchPlanDateEnd			//出荷予定日
			,ArrayList<String> SearchShipDateEnd			//出荷実績日
				
			,ArrayList<String> SearchDeliCd					//荷届先コード
			,ArrayList<String> SearchClDeliCd				//荷主荷届先コード
			,ArrayList<String> SearchDeliDepartmentCd		//部署CD
			,ArrayList<String> SearchDeliName				//荷届先名
			,ArrayList<String> SearchDeliPost				//荷届先郵便番号
			,ArrayList<String> SearchDeliAdd				//荷届先住所
			,ArrayList<String> SearchDeliTel				//荷届先TEL
			,ArrayList<String> SearchDeliFax				//荷届先FAX
			,ArrayList<String> SearchDeliMail				//荷届先MAIL
			,ArrayList<String> SearchDeliMunicCd			//荷届先市区町村CD
				 
			,ArrayList<String> SearchClGpCD					//荷主グループCD
			,ArrayList<String> SearchClCd					//荷主CD
			,ArrayList<String> SearchPickupWhCd				//集荷倉庫CD
			,ArrayList<String> SearchPurposeFG				//目的フラグ
			,ArrayList<String> SearchSPPlanDateStr			//着日指定
			,ArrayList<String> SearchSPDateStr				//着日実績
			,ArrayList<String> SearchSPPlanDateEnd			//着日指定
			,ArrayList<String> SearchSPDateEnd				//着日実績

			,ArrayList<String> SearchCategoryCd				//商品カテゴリCD
			,ArrayList<String> SearchCategoryName			//商品カテゴリ名
			,ArrayList<String> SearchItemColorCd			//商品カラーコード
			,ArrayList<String> SearchItemColorName			//商品カラー名
			,ArrayList<String> SearchItemSizeCd				//商品サイズコード
			,ArrayList<String> SearchItemSizeName			//商品サイズ名
			,boolean AllSearch
			){
		Object[][] Definition = T100_ShipPlovisionRt.DefinitionRt();
		
		for(int i=0;i<Definition.length;i++) {
			switch((int)Definition[i][3]){
				case ColSearchcl_cd:	
					Definition[i][1]	= Searchcl_cd;
					break;
				case ColSearchInvoiceWHCD:	
					Definition[i][1]	= SearchInvoiceWHCD;
					break;
				case ColSearchOkuriNo:	
					Definition[i][1]	= SearchOkuriNo;
					break;
				case ColSearchMsNoMin:	
					Definition[i][1]	= SearchMsNoMin;
					break;
				case ColSearchSeqMin:	
					Definition[i][1]	= SearchSeqMin;
					break;
				case ColSearchMsNoMax:	
					Definition[i][1]	= SearchMsNoMax;
					break;
				case ColSearchSeqMax:	
					Definition[i][1]	= SearchSeqMax;
					break;
				case ColSearchOrderItemCd:	
					Definition[i][1]	= SearchOrderItemCd;
					break;
				case ColSearchOrderItemName:	
					Definition[i][1]	= SearchOrderItemName;
					break;
				case ColSearchOrderLot:	
					Definition[i][1]	= SearchOrderLot;
					break;
				case ColSearchOrderExpDateStr:	
					Definition[i][1]	= SearchOrderExpDateStr;
					break;
				case ColSearchOrderExpDateEnd:	
					Definition[i][1]	= SearchOrderExpDateEnd;
					break;
				case ColSearchOrderQtyMin:	
					Definition[i][1]	= SearchOrderQtyMin;
					break;
				case ColSearchOrderQtyMax:	
					Definition[i][1]	= SearchOrderQtyMax;
					break;
				case ColSearchShipWhCd:	
					Definition[i][1]	= SearchShipWhCd;
					break;
				case ColSearchShipLoc:	
					Definition[i][1]	= SearchShipLoc;
					break;
				case ColSearchShipItemCd:	
					Definition[i][1]	= SearchShipItemCd;
					break;
				case ColSearchShipLot:	
					Definition[i][1]	= SearchShipLot;
					break;
				case ColSearchShipExpdateStr:	
					Definition[i][1]	= SearchShipExpdateStr;
					break;
				case ColSearchShipActualDateStr:	
					Definition[i][1]	= SearchShipActualDateStr;
					break;
				case ColSearchShipExpdateEnd:	
					Definition[i][1]	= SearchShipExpdateEnd;
					break;
				case ColSearchShipActualDateEnd:	
					Definition[i][1]	= SearchShipActualDateEnd;
					break;
				case ColSearchShipQtyMin:	
					Definition[i][1]	= SearchShipQtyMin;
					break;
				case ColSearchShipQtyMax:	
					Definition[i][1]	= SearchShipQtyMax;
					break;
				case ColSearchFixFg:	
					Definition[i][1]	= SearchFixFg;
					break;
				case ColSearchPackingType:	
					Definition[i][1]	= SearchPackingType;
					break;
				case ColSearchPackingQtyMin:	
					Definition[i][1]	= SearchPackingQtyMin;
					break;
				case ColSearchPackingQtyMax:	
					Definition[i][1]	= SearchPackingQtyMax;
					break;
				case ColSearchBRShipQtyMin:	
					Definition[i][1]	= SearchBRShipQtyMin;
					break;
				case ColSearchCTShipQtyMin:	
					Definition[i][1]	= SearchCTShipQtyMin;
					break;
				case ColSearchCSShipQtyMin:	
					Definition[i][1]	= SearchCSShipQtyMin;
					break;
				case ColSearchPLShipQtyMin:	
					Definition[i][1]	= SearchPLShipQtyMin;
					break;
				case ColSearchBRShipQtyMax:	
					Definition[i][1]	= SearchBRShipQtyMax;
					break;
				case ColSearchCTShipQtyMax:	
					Definition[i][1]	= SearchCTShipQtyMax;
					break;
				case ColSearchCSShipQtyMax:	
					Definition[i][1]	= SearchCSShipQtyMax;
					break;
				case ColSearchPLShipQtyMax:	
					Definition[i][1]	= SearchPLShipQtyMax;
					break;
				case ColSearchEntryDateStr:	
					Definition[i][1]	= SearchEntryDateStr;
					break;
				case ColSearchUpdateDateStr:	
					Definition[i][1]	= SearchUpdateDateStr;
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
				case ColSearchClDeliNo:	
					Definition[i][1]	= SearchClDeliNo;
					break;
				case ColSearchPlanDateStr:	
					Definition[i][1]	= SearchPlanDateStr;
					break;
				case ColSearchShipDateStr:	
					Definition[i][1]	= SearchShipDateStr;
					break;
				case ColSearchPlanDateEnd:	
					Definition[i][1]	= SearchPlanDateEnd;
					break;
				case ColSearchShipDateEnd:	
					Definition[i][1]	= SearchShipDateEnd;
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
				case ColSearchClGpCD:	
					Definition[i][1]	= SearchClGpCD;
					break;
				case ColSearchClCd:	
					Definition[i][1]	= SearchClCd;
					break;
				case ColSearchPickupWhCd:	
					Definition[i][1]	= SearchPickupWhCd;
					break;
				case ColSearchPurposeFG:	
					Definition[i][1]	= SearchPurposeFG;
					break;
				case ColSearchSPPlanDateStr:	
					Definition[i][1]	= SearchSPPlanDateStr;
					break;
				case ColSearchSPDateStr:	
					Definition[i][1]	= SearchSPDateStr;
					break;
				case ColSearchSPPlanDateEnd:	
					Definition[i][1]	= SearchSPPlanDateEnd;
					break;
				case ColSearchSPDateEnd:	
					Definition[i][1]	= SearchSPDateEnd;
					break;
				case ColSearchCategoryCd:	
					Definition[i][1]	= SearchCategoryCd;
					break;
				case ColSearchCategoryName:	
					Definition[i][1]	= SearchCategoryName;
					break;
				case ColSearchItemColorCd:	
					Definition[i][1]	= SearchItemColorCd;
					break;
				case ColSearchItemColorName:	
					Definition[i][1]	= SearchItemColorName;
					break;
				case ColSearchItemSizeCd:	
					Definition[i][1]	= SearchItemSizeCd;
					break;
				case ColSearchItemSizeName:	
					Definition[i][1]	= SearchItemSizeName;
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
			switch((int)Definition[i][3]){
			case ColSearchcl_cd:	
				Searchcl_cd				= (ArrayList<String>)Definition[i][1];;
				break;
			case ColSearchInvoiceWHCD:	
				SearchInvoiceWHCD		= (ArrayList<String>)Definition[i][1];;
				break;
			case ColSearchOkuriNo:	
				SearchOkuriNo			= (ArrayList<String>)Definition[i][1];;
				break;
			case ColSearchMsNoMin:	
				SearchMsNoMin			= (ArrayList<Integer>)Definition[i][1];;
				break;
			case ColSearchSeqMin:	
				SearchSeqMin			= (ArrayList<Integer>)Definition[i][1];;
				break;
			case ColSearchMsNoMax:	
				SearchMsNoMax			= (ArrayList<Integer>)Definition[i][1];;
				break;
			case ColSearchSeqMax:	
				SearchSeqMax			= (ArrayList<Integer>)Definition[i][1];;
				break;
			case ColSearchOrderItemCd:	
				SearchOrderItemCd		= (ArrayList<String>)Definition[i][1];;
				break;
			case ColSearchOrderItemName:	
				SearchOrderItemName		= (ArrayList<String>)Definition[i][1];;
				break;
			case ColSearchOrderLot:	
				SearchOrderLot			= (ArrayList<String>)Definition[i][1];;
				break;
			case ColSearchOrderExpDateStr:	
				SearchOrderExpDateStr	= (ArrayList<String>)Definition[i][1];;
				break;
			case ColSearchOrderExpDateEnd:	
				SearchOrderExpDateEnd	= (ArrayList<String>)Definition[i][1];;
				break;
			case ColSearchOrderQtyMin:	
				SearchOrderQtyMin		= (ArrayList<Integer>)Definition[i][1];;
				break;
			case ColSearchOrderQtyMax:	
				SearchOrderQtyMax		= (ArrayList<Integer>)Definition[i][1];;
				break;
			case ColSearchShipWhCd:	
				SearchShipWhCd			= (ArrayList<String>)Definition[i][1];;
				break;
			case ColSearchShipLoc:	
				SearchShipLoc			= (ArrayList<String>)Definition[i][1];;
				break;
			case ColSearchShipItemCd:	
				SearchShipItemCd		= (ArrayList<String>)Definition[i][1];;
				break;
			case ColSearchShipLot:	
				SearchShipLot			= (ArrayList<String>)Definition[i][1];;
				break;
			case ColSearchShipExpdateStr:	
				SearchShipExpdateStr	= (ArrayList<String>)Definition[i][1];;
				break;
			case ColSearchShipActualDateStr:	
				SearchShipActualDateStr	= (ArrayList<String>)Definition[i][1];;
				break;
			case ColSearchShipExpdateEnd:	
				SearchShipExpdateEnd	= (ArrayList<String>)Definition[i][1];;
				break;
			case ColSearchShipActualDateEnd:	
				SearchShipActualDateEnd	= (ArrayList<String>)Definition[i][1];;
				break;
			case ColSearchShipQtyMin:	
				SearchShipQtyMin		= (ArrayList<String>)Definition[i][1];;
				break;
			case ColSearchShipQtyMax:	
				SearchShipQtyMax		= (ArrayList<String>)Definition[i][1];;
				break;
			case ColSearchFixFg:	
				SearchFixFg				= (ArrayList<Integer>)Definition[i][1];;
				break;
			case ColSearchPackingType:	
				SearchPackingType		= (ArrayList<Integer>)Definition[i][1];;
				break;
			case ColSearchPackingQtyMin:	
				SearchPackingQtyMin		= (ArrayList<Integer>)Definition[i][1];;
				break;
			case ColSearchPackingQtyMax:	
				SearchPackingQtyMax		= (ArrayList<Integer>)Definition[i][1];;
				break;
			case ColSearchBRShipQtyMin:	
				SearchBRShipQtyMin		= (ArrayList<Integer>)Definition[i][1];;
				break;
			case ColSearchCTShipQtyMin:	
				SearchCTShipQtyMin		= (ArrayList<Integer>)Definition[i][1];;
				break;
			case ColSearchCSShipQtyMin:	
				SearchCSShipQtyMin		= (ArrayList<Integer>)Definition[i][1];;
				break;
			case ColSearchPLShipQtyMin:	
				SearchPLShipQtyMin		= (ArrayList<Integer>)Definition[i][1];;
				break;
			case ColSearchBRShipQtyMax:	
				SearchBRShipQtyMax		= (ArrayList<Integer>)Definition[i][1];;
				break;
			case ColSearchCTShipQtyMax:	
				SearchCTShipQtyMax		= (ArrayList<Integer>)Definition[i][1];;
				break;
			case ColSearchCSShipQtyMax:	
				SearchCSShipQtyMax		= (ArrayList<Integer>)Definition[i][1];;
				break;
			case ColSearchPLShipQtyMax:	
				SearchPLShipQtyMax		= (ArrayList<Integer>)Definition[i][1];;
				break;
			case ColSearchEntryDateStr:	
				SearchEntryDateStr		= (ArrayList<String>)Definition[i][1];;
				break;
			case ColSearchUpdateDateStr:	
				SearchUpdateDateStr		= (ArrayList<String>)Definition[i][1];;
				break;
			case ColSearchEntryDateEnd:	
				SearchEntryDateEnd		= (ArrayList<String>)Definition[i][1];;
				break;
			case ColSearchUpdateDateEnd:	
				SearchUpdateDateEnd		= (ArrayList<String>)Definition[i][1];;
				break;
			case ColSearchEntryUser:	
				SearchEntryUser			= (ArrayList<String>)Definition[i][1];;
				break;
			case ColSearchUpdateUser:	
				SearchUpdateUser		= (ArrayList<String>)Definition[i][1];;
				break;
			case ColSearchClDeliNo:	
				SearchClDeliNo			= (ArrayList<String>)Definition[i][1];;
				break;
			case ColSearchPlanDateStr:	
				SearchPlanDateStr		= (ArrayList<String>)Definition[i][1];;
				break;
			case ColSearchShipDateStr:	
				SearchShipDateStr		= (ArrayList<String>)Definition[i][1];;
				break;
			case ColSearchPlanDateEnd:	
				SearchPlanDateEnd		= (ArrayList<String>)Definition[i][1];;
				break;
			case ColSearchShipDateEnd:	
				SearchShipDateEnd		= (ArrayList<String>)Definition[i][1];;
				break;
			case ColSearchDeliCd:	
				SearchDeliCd			= (ArrayList<String>)Definition[i][1];;
				break;
			case ColSearchClDeliCd:	
				SearchClDeliCd			= (ArrayList<String>)Definition[i][1];;
				break;
			case ColSearchDeliDepartmentCd:	
				SearchDeliDepartmentCd	= (ArrayList<String>)Definition[i][1];;
				break;
			case ColSearchDeliName:	
				SearchDeliName			= (ArrayList<String>)Definition[i][1];;
				break;
			case ColSearchDeliPost:	
				SearchDeliPost			= (ArrayList<String>)Definition[i][1];;
				break;
			case ColSearchDeliAdd:	
				SearchDeliAdd			= (ArrayList<String>)Definition[i][1];;
				break;
			case ColSearchDeliTel:	
				SearchDeliTel			= (ArrayList<String>)Definition[i][1];;
				break;
			case ColSearchDeliFax:	
				SearchDeliFax			= (ArrayList<String>)Definition[i][1];;
				break;
			case ColSearchDeliMail:	
				SearchDeliMail			= (ArrayList<String>)Definition[i][1];;
				break;
			case ColSearchDeliMunicCd:	
				SearchDeliMunicCd		= (ArrayList<String>)Definition[i][1];;
				break;
			case ColSearchClGpCD:	
				SearchClGpCD			= (ArrayList<String>)Definition[i][1];;
				break;
			case ColSearchClCd:	
				SearchClCd				= (ArrayList<String>)Definition[i][1];;
				break;
			case ColSearchPickupWhCd:	
				SearchPickupWhCd		= (ArrayList<String>)Definition[i][1];;
				break;
			case ColSearchPurposeFG:	
				SearchPurposeFG			= (ArrayList<String>)Definition[i][1];;
				break;
			case ColSearchSPPlanDateStr:	
				SearchSPPlanDateStr		= (ArrayList<String>)Definition[i][1];;
				break;
			case ColSearchSPDateStr:	
				SearchSPDateStr			= (ArrayList<String>)Definition[i][1];;
				break;
			case ColSearchSPPlanDateEnd:	
				SearchSPPlanDateEnd		= (ArrayList<String>)Definition[i][1];;
				break;
			case ColSearchSPDateEnd:	
				SearchSPDateEnd			= (ArrayList<String>)Definition[i][1];;
				break;
			case ColSearchCategoryCd:	
				SearchCategoryCd		= (ArrayList<String>)Definition[i][1];;
				break;
			case ColSearchCategoryName:	
				SearchCategoryName		= (ArrayList<String>)Definition[i][1];;
				break;
			case ColSearchItemColorCd:	
				SearchItemColorCd		= (ArrayList<String>)Definition[i][1];;
				break;
			case ColSearchItemColorName:	
				SearchItemColorName		= (ArrayList<String>)Definition[i][1];;
				break;
			case ColSearchItemSizeCd:	
				SearchItemSizeCd		= (ArrayList<String>)Definition[i][1];;
				break;
			case ColSearchItemSizeName:	
				SearchItemSizeName		= (ArrayList<String>)Definition[i][1];;
				break;
				default:
				 	break;
			}
		}
		
		
		
		Object[][] Rt = ShipPlovisionRtMain(
									 Searchcl_cd				//荷主コード
									,SearchInvoiceWHCD			//倉庫コード
									,SearchOkuriNo				//送り状番号
									,SearchMsNoMin				//明細番号
									,SearchSeqMin				//引当枝番
									,SearchMsNoMax				//明細番号
									,SearchSeqMax				//引当枝番
									,SearchOrderItemCd			//商品コード
									,SearchOrderItemName		//商品表記名
									,SearchOrderLot				//受注ロット指定
									,SearchOrderExpDateStr		//受注賞味期限指定
									,SearchOrderExpDateEnd		//受注賞味期限指定
									,SearchOrderQtyMin			//受注個数
									,SearchOrderQtyMax			//受注個数
									,SearchShipWhCd				//出荷倉庫コード
									,SearchShipLoc				//ロケーション
									,SearchShipItemCd			//商品コード
									,SearchShipLot				//ロット
									,SearchShipExpdateStr		//消費期限
									,SearchShipActualDateStr	//入荷実績日
									,SearchShipExpdateEnd		//消費期限
									,SearchShipActualDateEnd	//入荷実績日
									,SearchShipQtyMin			//引当数量
									,SearchShipQtyMax			//引当数量
									,SearchFixFg				//引落済フラグ
									,SearchPackingType			//荷姿タイプ
									,SearchPackingQtyMin		//荷姿数量
									,SearchPackingQtyMax		//荷姿数量
									,SearchBRShipQtyMin			//バラ数量
									,SearchCTShipQtyMin			//カートン数量
									,SearchCSShipQtyMin			//ケース数量
									,SearchPLShipQtyMin			//パレット数量
									,SearchBRShipQtyMax			//バラ数量
									,SearchCTShipQtyMax			//カートン数量
									,SearchCSShipQtyMax			//ケース数量
									,SearchPLShipQtyMax			//パレット数量
									,SearchEntryDateStr			//登録日時
									,SearchUpdateDateStr		//更新日時
									,SearchEntryDateEnd			//登録日時
									,SearchUpdateDateEnd		//更新日時
									,SearchEntryUser			//登録者
									,SearchUpdateUser			//更新者
										
									,SearchClDeliNo				//荷主管理番号
									,SearchPlanDateStr			//出荷予定日
									,SearchShipDateStr			//出荷実績日
									,SearchPlanDateEnd			//出荷予定日
									,SearchShipDateEnd			//出荷実績日
										
									,SearchDeliCd				//荷届先コード
									,SearchClDeliCd				//荷主荷届先コード
									,SearchDeliDepartmentCd		//部署CD
									,SearchDeliName				//荷届先名
									,SearchDeliPost				//荷届先郵便番号
									,SearchDeliAdd				//荷届先住所
									,SearchDeliTel				//荷届先TEL
									,SearchDeliFax				//荷届先FAX
									,SearchDeliMail				//荷届先MAIL
									,SearchDeliMunicCd			//荷届先市区町村CD
										 
									,SearchClGpCD				//荷主グループCD
									,SearchClCd					//荷主CD
									,SearchPickupWhCd			//集荷倉庫CD
									,SearchPurposeFG			//目的フラグ
									,SearchSPPlanDateStr		//着日指定
									,SearchSPDateStr			//着日実績
									,SearchSPPlanDateEnd		//着日指定
									,SearchSPDateEnd			//着日実績
				
									,SearchCategoryCd			//商品カテゴリCD
									,SearchCategoryName			//商品カテゴリ名
									,SearchItemColorCd			//商品カラーコード
									,SearchItemColorName		//商品カラー名
									,SearchItemSizeCd			//商品サイズコード
									,SearchItemSizeName			//商品サイズ名
									,AllSearch
									);
		
		return Rt;
	}
	
	public static Object[][] ShipPlovisionRtMain(
			 ArrayList<String> Searchcl_cd					//荷主コード
			,ArrayList<String> SearchInvoiceWHCD			//倉庫コード
			,ArrayList<String> SearchOkuriNo				//送り状番号
			,ArrayList<Integer> SearchMsNoMin				//明細番号
			,ArrayList<Integer> SearchSeqMin				//引当枝番
			,ArrayList<Integer> SearchMsNoMax				//明細番号
			,ArrayList<Integer> SearchSeqMax				//引当枝番
			,ArrayList<String> SearchOrderItemCd			//商品コード
			,ArrayList<String> SearchOrderItemName			//商品表記名
			,ArrayList<String> SearchOrderLot				//受注ロット指定
			,ArrayList<String> SearchOrderExpDateStr		//受注賞味期限指定
			,ArrayList<String> SearchOrderExpDateEnd		//受注賞味期限指定
			,ArrayList<Integer> SearchOrderQtyMin			//受注個数
			,ArrayList<Integer> SearchOrderQtyMax			//受注個数
			,ArrayList<String> SearchShipWhCd				//出荷倉庫コード
			,ArrayList<String> SearchShipLoc				//ロケーション
			,ArrayList<String> SearchShipItemCd				//商品コード
			,ArrayList<String> SearchShipLot				//ロット
			,ArrayList<String> SearchShipExpdateStr			//消費期限
			,ArrayList<String> SearchShipActualDateStr		//入荷実績日
			,ArrayList<String> SearchShipExpdateEnd			//消費期限
			,ArrayList<String> SearchShipActualDateEnd		//入荷実績日
			,ArrayList<String> SearchShipQtyMin				//引当数量
			,ArrayList<String> SearchShipQtyMax				//引当数量
			,ArrayList<Integer> SearchFixFg					//引落済フラグ
			,ArrayList<Integer> SearchPackingType			//荷姿タイプ
			,ArrayList<Integer> SearchPackingQtyMin			//荷姿数量
			,ArrayList<Integer> SearchPackingQtyMax			//荷姿数量
			,ArrayList<Integer> SearchBRShipQtyMin			//バラ数量
			,ArrayList<Integer> SearchCTShipQtyMin			//カートン数量
			,ArrayList<Integer> SearchCSShipQtyMin			//ケース数量
			,ArrayList<Integer> SearchPLShipQtyMin			//パレット数量
			,ArrayList<Integer> SearchBRShipQtyMax			//バラ数量
			,ArrayList<Integer> SearchCTShipQtyMax			//カートン数量
			,ArrayList<Integer> SearchCSShipQtyMax			//ケース数量
			,ArrayList<Integer> SearchPLShipQtyMax			//パレット数量
			,ArrayList<String> SearchEntryDateStr			//登録日時
			,ArrayList<String> SearchUpdateDateStr			//更新日時
			,ArrayList<String> SearchEntryDateEnd			//登録日時
			,ArrayList<String> SearchUpdateDateEnd			//更新日時
			,ArrayList<String> SearchEntryUser				//登録者
			,ArrayList<String> SearchUpdateUser				//更新者
				
			,ArrayList<String> SearchClDeliNo				//荷主管理番号
			,ArrayList<String> SearchPlanDateStr			//出荷予定日
			,ArrayList<String> SearchShipDateStr			//出荷実績日
			,ArrayList<String> SearchPlanDateEnd			//出荷予定日
			,ArrayList<String> SearchShipDateEnd			//出荷実績日
				
			,ArrayList<String> SearchDeliCd					//荷届先コード
			,ArrayList<String> SearchClDeliCd				//荷主荷届先コード
			,ArrayList<String> SearchDeliDepartmentCd		//部署CD
			,ArrayList<String> SearchDeliName				//荷届先名
			,ArrayList<String> SearchDeliPost				//荷届先郵便番号
			,ArrayList<String> SearchDeliAdd				//荷届先住所
			,ArrayList<String> SearchDeliTel				//荷届先TEL
			,ArrayList<String> SearchDeliFax				//荷届先FAX
			,ArrayList<String> SearchDeliMail				//荷届先MAIL
			,ArrayList<String> SearchDeliMunicCd			//荷届先市区町村CD
				 
			,ArrayList<String> SearchClGpCD					//荷主グループCD
			,ArrayList<String> SearchClCd					//荷主CD
			,ArrayList<String> SearchPickupWhCd				//集荷倉庫CD
			,ArrayList<String> SearchPurposeFG				//目的フラグ
			,ArrayList<String> SearchSPPlanDateStr			//着日指定
			,ArrayList<String> SearchSPDateStr				//着日実績
			,ArrayList<String> SearchSPPlanDateEnd			//着日指定
			,ArrayList<String> SearchSPDateEnd				//着日実績

			,ArrayList<String> SearchCategoryCd				//商品カテゴリCD
			,ArrayList<String> SearchCategoryName			//商品カテゴリ名
			,ArrayList<String> SearchItemColorCd			//商品カラーコード
			,ArrayList<String> SearchItemColorName			//商品カラー名
			,ArrayList<String> SearchItemSizeCd				//商品サイズコード
			,ArrayList<String> SearchItemSizeName			//商品サイズ名
			,boolean AllSearch
			){
		Object[][] RtOkuriHdRt	= RtShipPlovisionRt();
		
		Object[][] Rt = new Object[0][RtOkuriHdRt.length];
		boolean SearchKick = false;
		if(AllSearch) {SearchKick = true;}
		
		String sql = "select \n"
	 				+"(WW0020ShipPlovision.cl_cd) as cl_cd,\n"						//荷主コード
					+"(WW0020ShipPlovision.InvoiceWHCD) as InvoiceWHCD,\n"			//倉庫コード
					+"(WW0020ShipPlovision.OkuriNo) as OkuriNo,\n"					//送り状番号
					+"(WW0020ShipPlovision.MsNo) as MsNo,\n"						//明細番号
					+"(WW0020ShipPlovision.Seq) as Seq,\n"							//引当枝番
					+"(WW0020ShipPlovision.OrderItemCd) as OrderItemCd,\n"			//商品コード
					+"(WW0020ShipPlovision.OrderItemName01) as OrderItemName01,\n"	//商品表記名
					+"(WW0020ShipPlovision.OrderLot) as OrderLot,\n"				//受注ロット指定
					+"(WW0020ShipPlovision.OrderExpDate) as OrderExpDate,\n"		//受注賞味期限指定
					+"(WW0020ShipPlovision.OrderQty) as OrderQty,\n"				//受注個数
					+"(WW0020ShipPlovision.ShipWhCd) as ShipWhCd,\n"				//出荷倉庫コード
					+"(WW0020ShipPlovision.ShipLoc) as ShipLoc,\n"					//ロケーション
					+"(WW0020ShipPlovision.ShipItemCd) as ShipItemCd,\n"			//商品コード
					+"(WW0020ShipPlovision.ShipLot) as ShipLot,\n"					//ロット
					+"(WW0020ShipPlovision.ShipExpdate) as ShipExpdate,\n"			//消費期限
					+"(WW0020ShipPlovision.ShipActualDate) as ShipActualDate,\n"	//入荷実績日
					+"(WW0020ShipPlovision.ShipQty) as ShipQty,\n"					//引当数量
					+"(WW0020ShipPlovision.FixFg) as FixFg,\n"						//引落済フラグ
					+"(WW0020ShipPlovision.PackingType) as PackingType,\n"			//荷姿タイプ
					+"(WW0020ShipPlovision.PackingQty) as PackingQty,\n"			//荷姿数量
					+"(WW0020ShipPlovision.UnitName) as UnitName,\n"				//荷姿単位
					+"(WW0020ShipPlovision.PackingUnitQty) as PackingUnitQty,\n"	//荷姿単位のバラ入数
					+"(WW0020ShipPlovision.BRShipQty) as BRShipQty,\n"				//バラ数量
					+"(WW0020ShipPlovision.CTShipQty) as CTShipQty,\n"				//カートン数量
					+"(WW0020ShipPlovision.CSShipQty) as CSShipQty,\n"				//ケース数量
					+"(WW0020ShipPlovision.PLShipQty) as PLShipQty,\n"				//パレット数量
					+"(WW0020ShipPlovision.BRUnitName) as BRUnitName,\n"			//バラ単位名
					+"(WW0020ShipPlovision.CTUnitName) as CTUnitName,\n"			//カートン単位名
					+"(WW0020ShipPlovision.CSUnitName) as CSUnitName,\n"			//ケース単位名
					+"(WW0020ShipPlovision.PLUnitName) as PLUnitName,\n"			//パレット単位名
					+"(WW0020ShipPlovision.EntryDate) as EntryDate,\n"				//登録日時
					+"(WW0020ShipPlovision.UpdateDate) as UpdateDate,\n"			//更新日時
					+"(WW0020ShipPlovision.EntryUser) as EntryUser,\n"				//登録者
					+"(WW0020ShipPlovision.UpdateUser) as UpdateUser,\n"			//更新者
					
					+"(KT0010_OKURI_HD.ClDeliNo) as ClDeliNo,\n"					//荷主管理番号
					+"(KT0010_OKURI_HD.PlanDate) as PlanDate,\n"					//出荷予定日
					+"(KT0010_OKURI_HD.ShipDate) as ShipDate,\n"					//出荷実績日
					+"(KT0010_OKURI_HD.DeliCd) as DeliCd,\n"						//荷届先コード
					+"(KT0010_OKURI_HD.ClDeliCd) as ClDeliCd,\n"					//荷主荷届先コード
					+"(KT0010_OKURI_HD.DeliDepartmentCd) as DeliDepartmentCd,\n"	//部署CD
					+"(KT0010_OKURI_HD.DeliName01) as DeliName01,\n"				//荷届先名01
					+"(KT0010_OKURI_HD.DeliName02) as DeliName02,\n"				//荷届先名02
					+"(KT0010_OKURI_HD.DeliName03) as DeliName03,\n"				//荷届先名03
					+"(KT0010_OKURI_HD.DeliPost) as DeliPost,\n"					//荷届先郵便番号
					+"(KT0010_OKURI_HD.DeliAdd01) as DeliAdd01,\n"					//荷届先住所01
					+"(KT0010_OKURI_HD.DeliAdd02) as DeliAdd02,\n"					//荷届先住所02
					+"(KT0010_OKURI_HD.DeliAdd03) as DeliAdd03,\n"					//荷届先住所03
					+"(KT0010_OKURI_HD.DeliTel) as DeliTel,\n"						//荷届先TEL
					+"(KT0010_OKURI_HD.DeliFax) as DeliFax,\n"						//荷届先FAX
					+"(KT0010_OKURI_HD.DeliMail) as DeliMail,\n"					//荷届先MAIL
					+"(KT0010_OKURI_HD.DeliMunicCd) as DeliMunicCd,\n"				//荷届先市区町村CD
					
					+"(KM0061_ITEMMSTSUB.CtQty) as CtQty,\n"						//カートン入数
					+"(KM0061_ITEMMSTSUB.CsQty) as CsQty,\n"						//ケース入数
					+"(KM0061_ITEMMSTSUB.PlQty) as PlQty,\n"						//パレット入数
					+"(KM0060_ITEMMST.JanCd) as JanCd,\n"							//JANCD
					+"(KM0061_ITEMMSTSUB.CtJan) as CtJan,\n"						//カートンバーコード
					+"(KM0061_ITEMMSTSUB.CsJan) as CsJan,\n"						//ケースバーコード
					+"(KM0061_ITEMMSTSUB.PlJan) as PlJan,\n"						//パレットバーコード
					+"(KM0061_ITEMMSTSUB.CtName) as CtName,\n"						//カートン商品名称
					+"(KM0061_ITEMMSTSUB.CsName) as CsName,\n"						//ケース商品名称
					+"(KM0061_ITEMMSTSUB.PlName) as PlName,\n"						//パレット商品名称
					+"(KM0061_ITEMMSTSUB.CtUnitName) as CtUnitName,\n"				//カートン商品単位
					+"(KM0061_ITEMMSTSUB.CsUnitName) as CsUnitName,\n"				//ケース商品単位
					+"(KM0061_ITEMMSTSUB.PlUnitName) as PlUnitName,\n"				//パレット商品単位
					+"(KM0060_ITEMMST.ItemWeight) as ItemWeight,\n"					//商品重量
					+"(KM0061_ITEMMSTSUB.CtWeight) as CtWeight,\n"					//カートン重量
					+"(KM0061_ITEMMSTSUB.CsWeight) as CsWeight,\n"					//ケース重量
					+"(KM0061_ITEMMSTSUB.PlWeight) as PlWeight,\n"					//パレット重量
					+"(KM0060_ITEMMST.ItemSize) as ItemSize,\n"						//商品サイズ
					+"(KM0061_ITEMMSTSUB.CtSize) as CtSize,\n"						//カートンサイズ
					+"(KM0061_ITEMMSTSUB.CsSize) as CsSize,\n"						//ケースサイズ
					+"(KM0061_ITEMMSTSUB.PlSize) as PlSize,\n"						//パレットサイズ
					+"(KM0061_ITEMMSTSUB.CategoryCd) as CategoryCd,\n"				//商品カテゴリCD
					+"(KM0061_ITEMMSTSUB.CategoryName) as CategoryName,\n"			//商品カテゴリ名
					+"(KM0061_ITEMMSTSUB.ItemColorCd) as ItemColorCd,\n"			//商品カラーコード
					+"(KM0061_ITEMMSTSUB.ItemColorName) as ItemColorName,\n"		//商品カラー名
					+"(KM0061_ITEMMSTSUB.ItemSizeCd) as ItemSizeCd,\n"				//商品サイズコード
					+"(KM0061_ITEMMSTSUB.ItemSizeName) as ItemSizeName\n"			//商品サイズ名
					+" from "+A00000_Main.MySqlDefaultSchemaWANKO+".WW0020ShipPlovision \n"
					+" left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KT0010_OKURI_HD \n"
					+" on(WW0020ShipPlovision.cl_cd = KT0010_OKURI_HD.cl_cd"
					+" and WW0020ShipPlovision.InvoiceWHCD = KT0010_OKURI_HD.InvoiceWHCD"
					+" and WW0020ShipPlovision.OkuriNo = KT0010_OKURI_HD.OkuriNo"
					+")\n"
					+ " left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0030_CLIENTMST"
					+ " on("
					+ " WW0020ShipPlovision.cl_cd = KM0030_CLIENTMST.cl_cd"
					+ ")\n"
					+ " left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0060_ITEMMST"
					+ " on("
					+ " KM0030_CLIENTMST.ClGpCd = KM0060_ITEMMST.ClGpCD"
					+ " and WW0020ShipPlovision.ShipItemCd = KM0060_ITEMMST.ItemCd"
					+ ")\n"
					+ " left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0061_ITEMMSTSUB"
					+ " on("
					+ " KM0060_ITEMMST.ClGpCd = KM0061_ITEMMSTSUB.ClGpCd"
					+ " and  WW0020ShipPlovision.ShipItemCd = KM0061_ITEMMSTSUB.ItemCd"
					+ ")\n"
					+" where 1=1 \n"
					;
		
		if(null!=Searchcl_cd && 0<Searchcl_cd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<Searchcl_cd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0020ShipPlovision.cl_cd = ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchInvoiceWHCD && 0<SearchInvoiceWHCD.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchInvoiceWHCD.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0020ShipPlovision.InvoiceWHCD = ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchOkuriNo && 0<SearchOkuriNo.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchOkuriNo.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0020ShipPlovision.OkuriNo = ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchMsNoMin && 0<SearchMsNoMin.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchMsNoMin.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0020ShipPlovision.MsNo >= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchSeqMin && 0<SearchSeqMin.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchSeqMin.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0020ShipPlovision.Seq >= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchMsNoMax && 0<SearchMsNoMax.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchMsNoMax.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0020ShipPlovision.MsNo <= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchSeqMax && 0<SearchSeqMax.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchSeqMax.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0020ShipPlovision.Seq <= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchOrderItemCd && 0<SearchOrderItemCd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchOrderItemCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0020ShipPlovision.OrderItemCd = ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchOrderItemName && 0<SearchOrderItemName.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchOrderItemName.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0020ShipPlovision.OrderItemName01 Like ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchOrderLot && 0<SearchOrderLot.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchOrderLot.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0020ShipPlovision.OrderLot = ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchOrderExpDateStr && 0<SearchOrderExpDateStr.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchOrderExpDateStr.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0020ShipPlovision.OrderExpDate >= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchOrderExpDateEnd && 0<SearchOrderExpDateEnd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchOrderExpDateEnd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0020ShipPlovision.OrderExpDate < ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchOrderQtyMin && 0<SearchOrderQtyMin.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchOrderQtyMin.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0020ShipPlovision.OrderQty >= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchOrderQtyMax && 0<SearchOrderQtyMax.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchOrderQtyMax.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0020ShipPlovision.OrderQty <= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchShipWhCd && 0<SearchShipWhCd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchShipWhCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0020ShipPlovision.ShipWhCd = ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchShipLoc && 0<SearchShipLoc.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchShipLoc.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0020ShipPlovision.ShipLoc Like ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchShipItemCd && 0<SearchShipItemCd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchShipItemCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0020ShipPlovision.ShipItemCd = ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchShipLot && 0<SearchShipLot.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchShipLot.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0020ShipPlovision.ShipLot = ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchShipExpdateStr && 0<SearchShipExpdateStr.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchShipExpdateStr.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0020ShipPlovision.ShipExpdate >= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchShipActualDateStr && 0<SearchShipActualDateStr.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchShipActualDateStr.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0020ShipPlovision.ShipActualDate >= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchShipExpdateEnd && 0<SearchShipExpdateEnd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchShipExpdateEnd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0020ShipPlovision.ShipExpdate < ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchShipActualDateEnd && 0<SearchShipActualDateEnd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchShipActualDateEnd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0020ShipPlovision.ShipActualDate < ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchShipQtyMin && 0<SearchShipQtyMin.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchShipQtyMin.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0020ShipPlovision.ShipQty >= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchShipQtyMax && 0<SearchShipQtyMax.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchShipQtyMax.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0020ShipPlovision.ShipQty <= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchFixFg && 0<SearchFixFg.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchFixFg.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0020ShipPlovision.FixFg = ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchPackingType && 0<SearchPackingType.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchPackingType.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0020ShipPlovision.PackingType = ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchPackingQtyMin && 0<SearchPackingQtyMin.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchPackingQtyMin.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0020ShipPlovision.PackingQty >= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchPackingQtyMax && 0<SearchPackingQtyMax.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchPackingQtyMax.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0020ShipPlovision.PackingQty <= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchBRShipQtyMin && 0<SearchBRShipQtyMin.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchBRShipQtyMin.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0020ShipPlovision.BRShipQty >= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchCTShipQtyMin && 0<SearchCTShipQtyMin.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchCTShipQtyMin.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0020ShipPlovision.CTShipQty >= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchCSShipQtyMin && 0<SearchCSShipQtyMin.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchCSShipQtyMin.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0020ShipPlovision.CSShipQty >= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchPLShipQtyMin && 0<SearchPLShipQtyMin.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchPLShipQtyMin.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0020ShipPlovision.PLShipQty >= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchBRShipQtyMax && 0<SearchBRShipQtyMax.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchBRShipQtyMax.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0020ShipPlovision.BRShipQty <= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchCTShipQtyMax && 0<SearchCTShipQtyMax.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchCTShipQtyMax.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0020ShipPlovision.CTShipQty <= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchCSShipQtyMax && 0<SearchCSShipQtyMax.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchCSShipQtyMax.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0020ShipPlovision.CSShipQty <= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchPLShipQtyMax && 0<SearchPLShipQtyMax.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchPLShipQtyMax.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0020ShipPlovision.PLShipQty <= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchEntryDateStr && 0<SearchEntryDateStr.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchEntryDateStr.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0020ShipPlovision.EntryDate >= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchUpdateDateStr && 0<SearchUpdateDateStr.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchUpdateDateStr.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0020ShipPlovision.UpdateDate >= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchEntryDateEnd && 0<SearchEntryDateEnd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchEntryDateEnd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0020ShipPlovision.EntryDate < ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchUpdateDateEnd && 0<SearchUpdateDateEnd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchUpdateDateEnd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0020ShipPlovision.UpdateDate < ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchEntryUser && 0<SearchEntryUser.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchEntryUser.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0020ShipPlovision.EntryUser Like ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchUpdateUser && 0<SearchUpdateUser.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchUpdateUser.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0020ShipPlovision.UpdateUser Like ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchClDeliNo && 0<SearchClDeliNo.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchClDeliNo.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.ClDeliNo = ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchPlanDateStr && 0<SearchPlanDateStr.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchPlanDateStr.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.PlanDate >= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchShipDateStr && 0<SearchShipDateStr.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchShipDateStr.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.ShipDate >= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchPlanDateEnd && 0<SearchPlanDateEnd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchPlanDateEnd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.PlanDate < ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchShipDateEnd && 0<SearchShipDateEnd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchShipDateEnd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.ShipDate < ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchDeliCd && 0<SearchDeliCd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchDeliCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.DeliCd = ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchClDeliCd && 0<SearchClDeliCd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchClDeliCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.ClDeliCd = ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchDeliDepartmentCd && 0<SearchDeliDepartmentCd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchDeliDepartmentCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.DeliDepartmentCd = ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchDeliName && 0<SearchDeliName.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchDeliName.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " DeliName01 Like ?";
				sql = sql + " or ";
				sql = sql + " DeliName02 Like ?";
				sql = sql + " or ";
				sql = sql + " DeliName03 Like ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchDeliPost && 0<SearchDeliPost.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchDeliPost.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.DeliPost Like ?";
			}
			sql = sql + ")\n";
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
			sql = sql + ")\n";
		}
		if(null!=SearchDeliTel && 0<SearchDeliTel.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchDeliTel.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.DeliTel Like ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchDeliFax && 0<SearchDeliFax.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchDeliFax.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.DeliFax Like ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchDeliMail && 0<SearchDeliMail.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchDeliMail.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.DeliMail Like ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchDeliMunicCd && 0<SearchDeliMunicCd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchDeliMunicCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.DeliMunicCd = ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchClGpCD && 0<SearchClGpCD.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchClGpCD.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KM0060_ITEMMST.ClGpCd = ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchClCd && 0<SearchClCd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchClCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0020ShipPlovision.cl_cd = ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchPickupWhCd && 0<SearchPickupWhCd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchPickupWhCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " WW0020ShipPlovision.ShipWhCd = ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchPurposeFG && 0<SearchPurposeFG.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchPurposeFG.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.PurposeFG = ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchSPPlanDateStr && 0<SearchSPPlanDateStr.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchSPPlanDateStr.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.SPPlanDate >= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchSPDateStr && 0<SearchSPDateStr.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchSPDateStr.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.SPDate >= ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchSPPlanDateEnd && 0<SearchSPPlanDateEnd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchSPPlanDateEnd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.SPPlanDate < ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchSPDateEnd && 0<SearchSPDateEnd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchSPDateEnd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KT0010_OKURI_HD.SPDate < ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchCategoryCd && 0<SearchCategoryCd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchCategoryCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KM0061_ITEMMSTSUB.CategoryCd = ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchCategoryName && 0<SearchCategoryName.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchCategoryName.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KM0061_ITEMMSTSUB.CategoryName Like ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchItemColorCd && 0<SearchItemColorCd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchItemColorCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KM0061_ITEMMSTSUB.ItemColorCd = ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchItemColorName && 0<SearchItemColorName.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchItemColorName.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KM0061_ITEMMSTSUB.ItemColorName Like ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchItemSizeCd && 0<SearchItemSizeCd.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchItemSizeCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KM0061_ITEMMSTSUB.ItemSizeCd = ?";
			}
			sql = sql + ")\n";
		}
		if(null!=SearchItemSizeName && 0<SearchItemSizeName.size()){
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchItemSizeName.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + " KM0061_ITEMMSTSUB.ItemSizeName Like ?";
			}
			sql = sql + ")\n";
		}
		sql = sql + " order by WW0020ShipPlovision.InvoiceWHCD,WW0020ShipPlovision.cl_cd,WW0020ShipPlovision.OkuriNo,WW0020ShipPlovision.MsNo,WW0020ShipPlovision.Seq";
		//System.out.println(sql);
		if(SearchKick) {
			A100_DbConnect.DB_CONN("NYANKO");
			ResultSet rset01 = null;
			PreparedStatement stmt01 = null;
			try {
				stmt01 = A100_DbConnect.conn.prepareStatement(sql);
				int StmtCount = 0;
				
				if(null!=Searchcl_cd && 0<Searchcl_cd.size()){
					for(int i=0;i<Searchcl_cd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+Searchcl_cd.get(i)+"");
					}
				}
				if(null!=SearchInvoiceWHCD && 0<SearchInvoiceWHCD.size()){
					for(int i=0;i<SearchInvoiceWHCD.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchInvoiceWHCD.get(i)+"");
					}
				}
				if(null!=SearchOkuriNo && 0<SearchOkuriNo.size()){
					for(int i=0;i<SearchOkuriNo.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchOkuriNo.get(i)+"");
					}
				}
				if(null!=SearchMsNoMin && 0<SearchMsNoMin.size()){
					for(int i=0;i<SearchMsNoMin.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchMsNoMin.get(i)+"");
					}
				}
				if(null!=SearchSeqMin && 0<SearchSeqMin.size()){
					for(int i=0;i<SearchSeqMin.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchSeqMin.get(i)+"");
					}
				}
				if(null!=SearchMsNoMax && 0<SearchMsNoMax.size()){
					for(int i=0;i<SearchMsNoMax.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchMsNoMax.get(i)+"");
					}
				}
				if(null!=SearchSeqMax && 0<SearchSeqMax.size()){
					for(int i=0;i<SearchSeqMax.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchSeqMax.get(i)+"");
					}
				}
				if(null!=SearchOrderItemCd && 0<SearchOrderItemCd.size()){
					for(int i=0;i<SearchOrderItemCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchOrderItemCd.get(i)+"");
					}
				}
				if(null!=SearchOrderItemName && 0<SearchOrderItemName.size()){
					for(int i=0;i<SearchOrderItemName.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchOrderItemName.get(i)+"%");
					}
				}
				if(null!=SearchOrderLot && 0<SearchOrderLot.size()){
					for(int i=0;i<SearchOrderLot.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchOrderLot.get(i)+"");
					}
				}
				if(null!=SearchOrderExpDateStr && 0<SearchOrderExpDateStr.size()){
					for(int i=0;i<SearchOrderExpDateStr.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchOrderExpDateStr.get(i)+"");
					}
				}
				if(null!=SearchOrderExpDateEnd && 0<SearchOrderExpDateEnd.size()){
					for(int i=0;i<SearchOrderExpDateEnd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchOrderExpDateEnd.get(i)+"");
					}
				}
				if(null!=SearchOrderQtyMin && 0<SearchOrderQtyMin.size()){
					for(int i=0;i<SearchOrderQtyMin.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchOrderQtyMin.get(i)+"");
					}
				}
				if(null!=SearchOrderQtyMax && 0<SearchOrderQtyMax.size()){
					for(int i=0;i<SearchOrderQtyMax.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchOrderQtyMax.get(i)+"");
					}
				}
				if(null!=SearchShipWhCd && 0<SearchShipWhCd.size()){
					for(int i=0;i<SearchShipWhCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchShipWhCd.get(i)+"");
					}
				}
				if(null!=SearchShipLoc && 0<SearchShipLoc.size()){
					for(int i=0;i<SearchShipLoc.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchShipLoc.get(i)+"%");
					}
				}
				if(null!=SearchShipItemCd && 0<SearchShipItemCd.size()){
					for(int i=0;i<SearchShipItemCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchShipItemCd.get(i)+"");
					}
				}
				if(null!=SearchShipLot && 0<SearchShipLot.size()){
					for(int i=0;i<SearchShipLot.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchShipLot.get(i)+"");
					}
				}
				if(null!=SearchShipExpdateStr && 0<SearchShipExpdateStr.size()){
					for(int i=0;i<SearchShipExpdateStr.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchShipExpdateStr.get(i)+"");
					}
				}
				if(null!=SearchShipActualDateStr && 0<SearchShipActualDateStr.size()){
					for(int i=0;i<SearchShipActualDateStr.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchShipActualDateStr.get(i)+"");
					}
				}
				if(null!=SearchShipExpdateEnd && 0<SearchShipExpdateEnd.size()){
					for(int i=0;i<SearchShipExpdateEnd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchShipExpdateEnd.get(i)+"");
					}
				}
				if(null!=SearchShipActualDateEnd && 0<SearchShipActualDateEnd.size()){
					for(int i=0;i<SearchShipActualDateEnd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchShipActualDateEnd.get(i)+"");
					}
				}
				if(null!=SearchShipQtyMin && 0<SearchShipQtyMin.size()){
					for(int i=0;i<SearchShipQtyMin.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchShipQtyMin.get(i)+"");
					}
				}
				if(null!=SearchShipQtyMax && 0<SearchShipQtyMax.size()){
					for(int i=0;i<SearchShipQtyMax.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchShipQtyMax.get(i)+"");
					}
				}
				if(null!=SearchFixFg && 0<SearchFixFg.size()){
					for(int i=0;i<SearchFixFg.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchFixFg.get(i)+"");
					}
				}
				if(null!=SearchPackingType && 0<SearchPackingType.size()){
					for(int i=0;i<SearchPackingType.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchPackingType.get(i)+"");
					}
				}
				if(null!=SearchPackingQtyMin && 0<SearchPackingQtyMin.size()){
					for(int i=0;i<SearchPackingQtyMin.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchPackingQtyMin.get(i)+"");
					}
				}
				if(null!=SearchPackingQtyMax && 0<SearchPackingQtyMax.size()){
					for(int i=0;i<SearchPackingQtyMax.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchPackingQtyMax.get(i)+"");
					}
				}
				if(null!=SearchBRShipQtyMin && 0<SearchBRShipQtyMin.size()){
					for(int i=0;i<SearchBRShipQtyMin.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchBRShipQtyMin.get(i)+"");
					}
				}
				if(null!=SearchCTShipQtyMin && 0<SearchCTShipQtyMin.size()){
					for(int i=0;i<SearchCTShipQtyMin.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchCTShipQtyMin.get(i)+"");
					}
				}
				if(null!=SearchCSShipQtyMin && 0<SearchCSShipQtyMin.size()){
					for(int i=0;i<SearchCSShipQtyMin.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchCSShipQtyMin.get(i)+"");
					}
				}
				if(null!=SearchPLShipQtyMin && 0<SearchPLShipQtyMin.size()){
					for(int i=0;i<SearchPLShipQtyMin.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchPLShipQtyMin.get(i)+"");
					}
				}
				if(null!=SearchBRShipQtyMax && 0<SearchBRShipQtyMax.size()){
					for(int i=0;i<SearchBRShipQtyMax.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchBRShipQtyMax.get(i)+"");
					}
				}
				if(null!=SearchCTShipQtyMax && 0<SearchCTShipQtyMax.size()){
					for(int i=0;i<SearchCTShipQtyMax.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchCTShipQtyMax.get(i)+"");
					}
				}
				if(null!=SearchCSShipQtyMax && 0<SearchCSShipQtyMax.size()){
					for(int i=0;i<SearchCSShipQtyMax.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchCSShipQtyMax.get(i)+"");
					}
				}
				if(null!=SearchPLShipQtyMax && 0<SearchPLShipQtyMax.size()){
					for(int i=0;i<SearchPLShipQtyMax.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchPLShipQtyMax.get(i)+"");
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
				if(null!=SearchClDeliNo && 0<SearchClDeliNo.size()){
					for(int i=0;i<SearchClDeliNo.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchClDeliNo.get(i)+"");
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
						stmt01.setString(StmtCount, ""+SearchDeliMunicCd.get(i)+"");
					}
				}
				if(null!=SearchClGpCD && 0<SearchClGpCD.size()){
					for(int i=0;i<SearchClGpCD.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchClGpCD.get(i)+"");
					}
				}
				if(null!=SearchClCd && 0<SearchClCd.size()){
					for(int i=0;i<SearchClCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchClCd.get(i)+"");
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
				if(null!=SearchCategoryCd && 0<SearchCategoryCd.size()){
					for(int i=0;i<SearchCategoryCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchCategoryCd.get(i)+"");
					}
				}
				if(null!=SearchCategoryName && 0<SearchCategoryName.size()){
					for(int i=0;i<SearchCategoryName.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchCategoryName.get(i)+"%");
					}
				}
				if(null!=SearchItemColorCd && 0<SearchItemColorCd.size()){
					for(int i=0;i<SearchItemColorCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchItemColorCd.get(i)+"");
					}
				}
				if(null!=SearchItemColorName && 0<SearchItemColorName.size()){
					for(int i=0;i<SearchItemColorName.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchItemColorName.get(i)+"%");
					}
				}
				if(null!=SearchItemSizeCd && 0<SearchItemSizeCd.size()){
					for(int i=0;i<SearchItemSizeCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchItemSizeCd.get(i)+"");
					}
				}
				if(null!=SearchItemSizeName && 0<SearchItemSizeName.size()){
					for(int i=0;i<SearchItemSizeName.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchItemSizeName.get(i)+"%");
					}
				}
				
				rset01 = stmt01.executeQuery();
				
				Rt = B100_RtObjectCreate.B100_RtObjectCreate(rset01,RtShipPlovisionRt());
				
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
}