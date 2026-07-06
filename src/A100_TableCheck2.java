public class A100_TableCheck2{
	public static Object[][] NynkoNeedTableList(){
		//必要になるテーブルの一覧
		Object[][] NeedTabele = {
						 {"KM0000_PARAMETER"				,"NYANKO"	,"共通パラメータマスタ"		,KM0000_PARAMETER_Definition()}
						,{"KM0005_MUNIC"					,"NYANKO"	,"市区町村マスタ"			,KM0005_MUNIC_Definition()}
						,{"KM0010_WHMST"					,"NYANKO"	,"倉庫マスタ"				,KM0010_WHMST_Definition()}
						,{"KM0020_USERMST"					,"NYANKO"	,"ユーザー（乗務員）マスタ"	,KM0020_USERMST_Definition()}
						,{"KM0030_CLIENTMST"				,"NYANKO"	,"荷主マスタ"				,KM0030_CLIENTMST_Definition()}
						,{"KM0031_CLIENT_GROUP"				,"NYANKO"	,"荷主グループマスタ"		,KM0031_CLIENT_GROUP_Definition()}
						,{"KM0040_DELIVERYMST"				,"NYANKO"	,"届け先マスタ"				,KM0040_DELIVERYMST_Definition()}
						,{"KM0041_DELIVERY_COMVERSIONMST"	,"NYANKO"	,"届先変換マスタ"			,KM0041_DELIVERY_COMVERSIONMST_Definition()}
						,{"KM0050_DELIVERY_TYPEMST"			,"NYANKO"	,"運送タイプマスタ"			,KM0050_DELIVERY_TYPEMST_Definition()}
						,{"KM0060_ITEMMST"					,"NYANKO"	,"商品マスタ"				,KM0060_ITEMMST_Definition()}
						,{"KM0061_ITEMMSTSUB"				,"NYANKO"	,"商品サブマスタ"			,KM0061_ITEMMSTSUB_Definition()}
						,{"KM0062_ItemComversionMst"		,"NYANKO"	,"商品変換マスタ"			,KM0062_ItemComversionMst_Definition()}
						,{"KM0070_SHIPPINGCOMPANYMST"		,"NYANKO"	,"運送会社マスタ"			,KM0070_SHIPPINGCOMPANYMST_Definition()}
						,{"KM0071_CARMST"					,"NYANKO"	,"車輌マスタ"				,KM0071_CARMST_Definition()}
						,{"KM0080_FEEBASEMST"				,"NYANKO"	,"運賃基本タリフマスタ"		,KM0080_FEEBASEMST_Definition()}
						,{"KM0081_FEEMST"					,"NYANKO"	,"運賃マスタ"				,KM0081_FEEMST_Definition()}
						,{"KM0082_FEELOGICTYPEMST"			,"NYANKO"	,"運賃計算ロジックマスタ"	,KM0082_FEELOGICTYPEMST_Definition()}
						,{"KM0090_CAUTION"					,"NYANKO"	,"注意事項マスタ"			,KM0090_CAUTION_Definition()}
						,{"KM0090_PAYBASEMST"				,"NYANKO"	,"下払運賃基本タリフマスタ"	,KM0090_PAYBASEMST_Definition()}
						,{"KM0091_PAYMST"					,"NYANKO"	,"下払タリフマスタ"			,KM0091_PAYMST_Definition()}
						,{"KM0100_OKURIPROGRESSMST"			,"NYANKO"	,"進捗状況マスタ"			,KM0100_OKURIPROGRESSMST_Definition()}
						,{"KM0110_CALLPGMST"				,"NYANKO"	,"呼び出しプログラムマスタ"	,KM0110_CALLPGMST_Definition()}
						,{"KM0122_CourseGlpMST"				,"NYANKO"	,"配送コースグループマスタ"	,KM0122_CourseGlpMST_Definition()}
						,{"KM0123_CourseMST"				,"NYANKO"	,"配送コースマスタ"			,KM0123_CourseMST_Definition()}
						,{"KM0124_CourseDeliveryMST"		,"NYANKO"	,"配送コース届先内訳マスタ"	,KM0124_CourseDeliveryMST_Definition()}
						,{"KM0125_MyDriverMST"				,"NYANKO"	,"俺の乗務員リストマスタ"	,KM0125_MyDriverMST_Definition()}
						,{"KM0126_MyDriverList"				,"NYANKO"	,"俺の乗務員リスト"			,KM0126_MyDriverList_Definition()}
						,{"KT0010_OKURI_HD"					,"NYANKO"	,"送り状データヘッダ"		,KT0010_OKURI_HD_Definition()}
						,{"KT0011_OKURI_MS"					,"NYANKO"	,"送り状データ明細"			,}
						,{"KT0012_OKURI_PROGRESS"			,"NYANKO"	,""}
						,{"KT0013_SEIKYU"					,"NYANKO"	,""}
						,{"KT0023_SHIHARAI"					,"NYANKO"	,""}
						,{"KT0040_PrintControl"				,"NYANKO"	,""}
						,{"KT020_HAISHA_HD"					,"NYANKO"	,""}
						,{"KT021_HAISHA_MS"					,"NYANKO"	,""}
						};
		return NeedTabele;
	}
	
	private static Object[][] KM0000_PARAMETER_Definition(){
		Object[][] Rt	={
						 {"ParaCd"		,"varchar"	,(int) 20	,"KEY"	,(boolean)false	,""		,"パラメータコード"}
						,{"ParaCdSeq"	,"int"		,(int) 1	,"KEY"	,(boolean)false	,""		,"ナンバリング"}
						,{"ParaName"	,"varchar"	,(int) 100	,""		,(boolean)false	,""		,"パラメータ名"}
						,{"ParaTxt01"	,"varchar"	,(int) 200	,""		,(boolean)false	,""		,"パラメータテキスト項目01"}
						,{"ParaTxt02"	,"varchar"	,(int) 200	,""		,(boolean)false	,""		,"パラメータテキスト項目02"}
						,{"ParaTxt03"	,"varchar"	,(int) 200	,""		,(boolean)false	,""		,"パラメータテキスト項目03"}
						,{"ParaTxt04"	,"varchar"	,(int) 200	,""		,(boolean)false	,""		,"パラメータテキスト項目04"}
						,{"ParaTxt05"	,"varchar"	,(int) 200	,""		,(boolean)false	,""		,"パラメータテキスト項目05"}
						,{"ParaTxt06"	,"varchar"	,(int) 200	,""		,(boolean)false	,""		,"パラメータテキスト項目06"}
						,{"ParaTxt07"	,"varchar"	,(int) 200	,""		,(boolean)false	,""		,"パラメータテキスト項目07"}
						,{"ParaTxt08"	,"varchar"	,(int) 200	,""		,(boolean)false	,""		,"パラメータテキスト項目08"}
						,{"ParaTxt09"	,"varchar"	,(int) 200	,""		,(boolean)false	,""		,"パラメータテキスト項目09"}
						,{"ParaTxt10"	,"varchar"	,(int) 200	,""		,(boolean)false	,""		,"パラメータテキスト項目10"}
						,{"ParaInt01"	,"int"		,(int) 11	,""		,(boolean)false	,"0"	,"パラメータ数値項目01"}
						,{"ParaInt02"	,"int"		,(int) 11	,""		,(boolean)false	,"0"	,"パラメータ数値項目02"}
						,{"ParaInt03"	,"int"		,(int) 11	,""		,(boolean)false	,"0"	,"パラメータ数値項目03"}
						,{"ParaInt04"	,"int"		,(int) 11	,""		,(boolean)false	,"0"	,"パラメータ数値項目04"}
						,{"ParaInt05"	,"int"		,(int) 11	,""		,(boolean)false	,"0"	,"パラメータ数値項目05"}
						,{"ParaInt06"	,"int"		,(int) 11	,""		,(boolean)false	,"0"	,"パラメータ数値項目06"}
						,{"ParaInt07"	,"int"		,(int) 11	,""		,(boolean)false	,"0"	,"パラメータ数値項目07"}
						,{"ParaInt08"	,"int"		,(int) 11	,""		,(boolean)false	,"0"	,"パラメータ数値項目08"}
						,{"ParaInt09"	,"int"		,(int) 11	,""		,(boolean)false	,"0"	,"パラメータ数値項目09"}
						,{"ParaInt10"	,"int"		,(int) 11	,""		,(boolean)false	,"0"	,"パラメータ数値項目10"}
						,{"EntryDate"	,"datetime"	,(int) 0	,""		,(boolean)true	,"NULL"	,"登録日"}
						,{"UpdateDate"	,"datetime"	,(int) 0	,""		,(boolean)true	,"NULL"	,"更新日"}
						,{"EntryUser"	,"varchar"	,(int) 50	,""		,(boolean)true	,"NULL"	,"登録者"}
						,{"UpdateUser"	,"varchar"	,(int) 50	,""		,(boolean)true	,"NULL"	,"更新者"}
						};
		return Rt;
	}
	
	private static Object[][] KM0005_MUNIC_Definition(){
		Object[][] Rt	={
						 {"MunicCd"		,"varchar"	,(int) 20	,"KEY"	,(boolean)false	,""		,"市区町村CD"}
						,{"Prefectures"	,"varchar"	,(int) 50	,""		,(boolean)true	,""		,"県名"}
						,{"Munici01"	,"varchar"	,(int) 50	,""		,(boolean)true	,""		,"市区町村名"}
						};
		return Rt;
	}
	
	private static Object[][] KM0010_WHMST_Definition(){
		Object[][] Rt	={
						  {"WHCD"		,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"倉庫コード"}
						 ,{"WHName"		,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"拠点倉庫名"}
						 ,{"Post"		,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"拠点倉庫郵便番号"}
						 ,{"Add01"		,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"拠点倉庫住所1"}
						 ,{"Add02"		,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"拠点倉庫住所2"}
						 ,{"Tel"		,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"拠点倉庫電話"}
						 ,{"Fax"		,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"拠点倉庫FAX"}
						 ,{"Mail"		,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"拠点倉庫MAIL"}
						 ,{"Com01"		,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"コメント１"}
						 ,{"Com02"		,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"コメント２"}
						 ,{"Com03"		,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"コメント３"}
						 ,{"PTMSCD"		,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"基幹システム連携用事業所CD"}
						 ,{"EntryDate"	,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"データ登録日時"}
						 ,{"UpdateDate"	,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"データ更新日時"}
						 ,{"EntryUser"	,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"登録者コード"}
						 ,{"UpdateUser"	,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"更新者コード"}
						 ,{"DelFg"		,"int"		,(int)11	,""		,(boolean)true	,"0"	,"削除区分"}
						};
		return Rt;
	}
	
	private static Object[][] KM0020_USERMST_Definition(){
		Object[][] Rt	={
					 	  {"WHCD"				,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""			,"倉庫コード"}
						 ,{"ShippingCompanyCd"	,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""			,"運送会社CD"}
						 ,{"UserCd"				,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""			,"ユーザーCD"}
						 ,{"PassWord"			,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"		,"パスワード"}
						 ,{"AuthorityFG"		,"int"		,(int)11	,""		,(boolean)false	,"0"		,"権限区分"}
						 ,{"CarCd"				,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"		,"標準車輛CD"}
						 ,{"UserName01"			,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"		,"ユーザー表記名"}
						 ,{"UserName02"			,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"		,"ユーザー正式名"}
						 ,{"UserName03"			,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"		,"ユーザー略名"}
						 ,{"Post"				,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"		,"郵便番号"}
						 ,{"Add01"				,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"		,"住所1"}
						 ,{"Add02"				,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"		,"住所2"}
						 ,{"Add03"				,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"		,"住所3"}
						 ,{"Tel"				,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"		,"電話番号"}
						 ,{"Fax"				,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"		,"FAX"}
						 ,{"Mail"				,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"		,"メールアドレス"}
						 ,{"Com01"				,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"		,"コメント1"}
						 ,{"Com02"				,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"		,"コメント2"}
						 ,{"Com03"				,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"		,"コメント3"}
						 ,{"EntryDate"			,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"		,"データ登録日時"}
						 ,{"UpdateDate"			,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"		,"データ更新日時"}
						 ,{"EntryUser"			,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"		,"登録者コード"}
						 ,{"UpdateUser"			,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"		,"更新者コード"}
						 ,{"PTMSCD"				,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"		,"基幹システムユーザーコード"}
						 ,{"DelFg"				,"tinyint"	,(int)1		,""		,(boolean)false	,"0"		,"削除区分"}
						 ,{"MainClient"			,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"		,"主要担当荷主CD"}
						};
		return Rt;
	}
	
	private static Object[][] KM0030_CLIENTMST_Definition(){
		Object[][] Rt	={
						  {"cl_cd"		,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""			,"荷主CD"}
						 ,{"ClGpCD"		,"varchar"	,(int)50	,""		,(boolean)true	,""			,"荷主グループCD"}
						 ,{"WHCD"		,"varchar"	,(int)20	,""		,(boolean)true	,""			,"担当倉庫"}
						 ,{"CLName01"	,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"		,"荷主表記名"}
						 ,{"CLName02"	,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"		,"荷主正式名"}
						 ,{"CLName03"	,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"		,"荷主略名"}
						 ,{"Post"		,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"		,"郵便番号"}
						 ,{"Add01"		,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"		,"住所1"}
						 ,{"Add02"		,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"		,"住所2"}
						 ,{"Add03"		,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"		,"住所3"}
						 ,{"Tel"		,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"		,"電話番号"}
						 ,{"Fax"		,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"		,"FAX"}
						 ,{"Mail"		,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"		,"メールアドレス"}
						 ,{"MunicCd"	,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"		,"市区町村CD"}
						 ,{"Com01"		,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"		,"コメント1"}
						 ,{"Com02"		,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"		,"コメント2"}
						 ,{"Com03"		,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"		,"コメント3"}
						 ,{"ShimeDate"	,"int"		,(int)11	,""		,(boolean)true	,"99"		,"運賃締日"}
						 ,{"ShimeBasis"	,"int"		,(int)11	,""		,(boolean)true	,"0"		,"請求基準"}
						 ,{"EntryDate"	,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"		,"データ登録日時"}
						 ,{"UpdateDate"	,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"		,"データ更新日時"}
						 ,{"EntryUser"	,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"		,"登録者コード"}
						 ,{"UpdateUser"	,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"		,"更新者コード"}
						 ,{"PTMSCD"		,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"		,"基幹システム荷主コード"}
						 ,{"DelFg"		,"int"		,(int)11	,""		,(boolean)false	,"0"		,"削除区分"}
						};
		return Rt;
	}
	
	private static Object[][] KM0031_CLIENT_GROUP_Definition(){
		Object[][] Rt	={
						  {"ClGpCD"		,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""			,"荷主グループCD"}
						 ,{"ClGpName01"	,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"		,"荷主グループ表記名"}
						 ,{"ClGpName02"	,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"		,"荷主グループ正式名"}
						 ,{"ClGpName0"	,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"		,"荷主グループ略名"}
						 ,{"Post"		,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"		,"郵便番号"}
						 ,{"Add01"		,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"		,"住所1"}
						 ,{"Add02"		,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"		,"住所2"}
						 ,{"Add03"		,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"		,"住所3"}
						 ,{"Tel"		,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"		,"電話番号"}
						 ,{"Fax"		,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"		,"FAX"}
						 ,{"Mail"		,"varchar"	,(int)200	,""		,(boolean)true	,"NULL"		,"メールアドレス"}
						 ,{"Com01"		,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"		,"コメント1"}
						 ,{"Com02"		,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"		,"コメント2"}
						 ,{"Com03"		,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"		,"コメント3"}
						 ,{"EntryDate"	,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"		,"データ登録日時"}
						 ,{"UpdateDate"	,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"		,"データ更新日時"}
						 ,{"EntryUser"	,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"		,"登録者コード"}
						 ,{"UpdateUser"	,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"		,"更新者コード"}
						 ,{"DelFg"		,"tinyint"	,(int)1		,""		,(boolean)true	,"0"		,"削除区分"}
						 ,{"PassWord"	,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"		,"パスワード"}
						};
		return Rt;
	}
	
	private static Object[][] KM0040_DELIVERYMST_Definition(){
		Object[][] Rt	={
						  {"DECD"			,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"納品先コード"}
						 ,{"DepartmentCd"	,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"部署CD"}
						 ,{"DEName01"		,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"納品先表記名"}
						 ,{"DEName02"		,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"納品先正式名"}
						 ,{"DEName03"		,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"納品先略名"}
						 ,{"Post"			,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"納品先郵便"}
						 ,{"Add01"			,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"納品先住所1"}
						 ,{"Add02"			,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"納品先住所2"}
						 ,{"Add03"			,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"納品先住所3"}
						 ,{"Tel"			,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"納品先電話"}
						 ,{"Fax"			,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"納品先FAX"}
						 ,{"Mail"			,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"納品先MAIL"}
						 ,{"Com01"			,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"コメント1"}
						 ,{"Com02"			,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"コメント2"}
						 ,{"Com03"			,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"コメント3"}
						 ,{"PrefecturesCd"	,"varchar"	,(int)10	,""		,(boolean)true	,"NULL"	,"JIS県CD2桁"}
						 ,{"MunicipalityCd"	,"varchar"	,(int)10	,""		,(boolean)true	,"NULL"	,"JIS市区町村CD5桁"}
						 ,{"PTMSCD"			,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"基幹システム発着地コード"}
						 ,{"EntryDate"		,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"データ登録日時"}
						 ,{"UpdateDate"		,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"データ更新日時"}
						 ,{"EntryUser"		,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"登録者コード"}
						 ,{"UpdateUser"		,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"更新者コード"}
						 ,{"FirstClient"	,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"登録した荷主CD"}
						 ,{"LastClient"		,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"更新した荷主CD"}
						 ,{"DelFg"			,"int"		,(int)11	,""		,(boolean)false	,"0"	,"削除区分"}
						};
		return Rt;
	}
	
	private static Object[][] KM0041_DELIVERY_COMVERSIONMST_Definition(){
		Object[][] Rt	={
						  {"ClGpCD"				,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"荷主グループコード"}
						 ,{"CL_DECD"			,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"荷主届け先コード"}
						 ,{"DECD"				,"varchar"	,(int)20	,""		,(boolean)false	,""		,"届け先コード"}
						 ,{"DepartmentCd"		,"varchar"	,(int)20	,""		,(boolean)false	,""		,"部署CD"}
						 ,{"SetName"			,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"送り状登録名"}
						 ,{"Com01"				,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"コメント01"}
						 ,{"Com02"				,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"コメント02"}
						 ,{"Com03"				,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"コメント03"}
						 ,{"Com04"				,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"コメント04"}
						 ,{"Com05"				,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"コメント05"}
						 ,{"EntryDate"			,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"データ登録日時"}
						 ,{"UpdateDate"			,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"データ更新日時"}
						 ,{"EntryUser"			,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"登録者コード"}
						 ,{"UpdateUser"			,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"更新者コード"}
						 ,{"DelFg"				,"int"		,(int)11	,""		,(boolean)true	,"0"	,"削除区分"}
						 ,{"MstPriorityFirstFg"	,"int"		,(int)11	,""		,(boolean)true	,"0"	,"届先マスタ優先フラグ"}
						};
		return Rt;
	}
	
	private static Object[][] KM0050_DELIVERY_TYPEMST_Definition(){
		Object[][] Rt	={
						  {"DeliveryTypeNo"		,"int"		,(int)11	,"KEY"	,(boolean)false	,"0"	,"タイプ番号"}
						 ,{"DeliveryTypeCd"		,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"運送タイプコード"}
						 ,{"DeliveryTypeName"	,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"運送タイプ名"}
						 ,{"EntryDate"			,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"データ登録日時"}
						 ,{"UpdateDate"			,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"データ更新日時"}
						 ,{"EntryUser"			,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"登録者コード"}
						 ,{"UpdateUser"			,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"更新者コード"}
						};
		return Rt;
	}
	
	private static Object[][] KM0060_ITEMMST_Definition(){
		Object[][] Rt	={
						  {"ClGpCD"				,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"荷主グループコード"}
						 ,{"ItemCd"				,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"商品コード"}
						 ,{"CLItemCd"			,"varchar"	,(int)20	,""		,(boolean)false	,""		,"荷主商品コード"}
						 ,{"ItemName01"			,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"商品表記名"}
						 ,{"ItemName02"			,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"商品正式名"}
						 ,{"ItemName03"			,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"商品略名"}
						 ,{"ItemWeight"			,"float"	,(int)0		,""		,(boolean)true	,"0"	,"商品重量"}
						 ,{"ItemSize"			,"float"	,(int)0		,""		,(boolean)true	,"0"	,"商品サイズ"}
						 ,{"DeliveryTypeCd"		,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"運送タイプコード01"}
						 ,{"DeliveryTypeCd02"	,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"運送タイプコード02"}
						 ,{"DeliveryTypeCd03"	,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"運送タイプコード03"}
						 ,{"DeliveryTypeCd04"	,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"運送タイプコード04"}
						 ,{"DeliveryTypeCd05"	,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"運送タイプコード05"}
						 ,{"EntryDate"			,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"データ登録日時"}
						 ,{"UpdateDate"			,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"データ更新日時"}
						 ,{"EntryUser"			,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"登録者コード"}
 						 ,{"UpdateUser"			,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"更新者コード"}
						 ,{"DelFg"				,"int"		,(int)11	,""		,(boolean)true	,"0"	,"削除区分"}
						 ,{"PTMSCD"				,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"基幹システム商品コード"}
						 ,{"JanCd"				,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"JANCD"}
						 ,{"UnitName"			,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"商品単位"}
						};
		return Rt;
	}
	
	private static Object[][] KM0061_ITEMMSTSUB_Definition(){
		Object[][] Rt	={
						  {"ClGpCd"			,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""			,"荷主グループコード"}
						 ,{"ItemCd"			,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""			,"商品コード"}
						 ,{"CtQty"			,"int"		,(int)11	,""		,(boolean)true	,"1"		,"カートン入数"}
						 ,{"CsQty"			,"int"		,(int)11	,""		,(boolean)true	,"1"		,"ケース入数"}
						 ,{"PlQty"			,"int"		,(int)11	,""		,(boolean)true	,"1"		,"パレット入数"}
						 ,{"CtJan"			,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"		,"カートンバーコード"}
						 ,{"CsJan"			,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"		,"ケースバーコード"}
						 ,{"PlJan"			,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"		,"パレットバーコード"}
						 ,{"CtName"			,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"		,"カートン商品名称"}
						 ,{"CsName"			,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"		,"ケース商品名称"}
						 ,{"PlName"			,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"		,"パレット商品名称"}
						 ,{"CtUnitName"		,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"		,"カートン商品単位"}
						 ,{"CsUnitName"		,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"		,"ケース商品単位"}
						 ,{"PlUnitName"		,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"		,"パレット商品単位"}
						 ,{"CtWeight"		,"float"	,(int)0		,""		,(boolean)true	,"0"		,"カートン重量"}
						 ,{"CsWeight"		,"float"	,(int)0		,""		,(boolean)true	,"0"		,"ケース重量"}
						 ,{"PlWeight"		,"float"	,(int)0		,""		,(boolean)true	,"0"		,"パレット重量"}
						 ,{"CtSize"			,"float"	,(int)0		,""		,(boolean)true	,"0"		,"カートンサイズ"}
						 ,{"CsSize"			,"float"	,(int)0		,""		,(boolean)true	,"0"		,"ケースサイズ"}
						 ,{"PlSize"			,"float"	,(int)0		,""		,(boolean)true	,"0"		,"パレットサイズ"}
						 ,{"RecomendLoc"	,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"		,"推奨ロケ"}
						 ,{"ItemMDNo"		,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"		,"商品モデル番号（型番）"}
						 ,{"CategoryCd"		,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"		,"商品カテゴリCD"}
						 ,{"CategoryName"	,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"		,"商品カテゴリ名"}
						 ,{"ItemColorCd"	,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"		,"商品カラーコード"}
						 ,{"ItemColorName"	,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"		,"商品カラー名"}
 						 ,{"ItemSizeCd"		,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"		,"商品サイズコード"}
						 ,{"ItemSizeName"	,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"		,"商品サイズ名"}
						 ,{"Com01"			,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"		,"コメント1"}
						 ,{"Com02"			,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"		,"コメント2"}
						 ,{"Com03"			,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"		,"コメント3"}
						 ,{"EntryDate"		,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"		,"データ登録日時"}
						 ,{"UpdateDate"		,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"		,"データ更新日時"}
						 ,{"EntryUser"		,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"		,"登録者コード"}
						 ,{"UpdateUser"		,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"		,"更新者コード"}
						 ,{"TildFG"			,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"		,"温度区分"}
						 ,{"TildName"		,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"		,"温度区分名"}
						 ,{"PictPass01"		,"varchar"	,(int)300	,""		,(boolean)true	,"NULL"		,"画像パス01"}
						 ,{"PictPass02"		,"varchar"	,(int)300	,""		,(boolean)true	,"NULL"		,"画像パス02"}
						 ,{"PictPass03"		,"varchar"	,(int)300	,""		,(boolean)true	,"NULL"		,"画像パス03"}
						 ,{"PictPass04"		,"varchar"	,(int)300	,""		,(boolean)true	,"NULL"		,"画像パス04"}
						 ,{"PictPass05"		,"varchar"	,(int)300	,""		,(boolean)true	,"NULL"		,"画像パス05"}
						 ,{"ExpDateHowLong"	,"int"		,(int)11	,""		,(boolean)true	,"0"		,"賞味期限日数"}
						};
		return Rt;
	}
	private static Object[][] KM0062_ItemComversionMst_Definition(){
		Object[][] Rt	={
						  {"ClGpCd"			,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""			,"荷主グループコード"}
						 ,{"ClCd"			,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""			,"荷主コード"}
						 ,{"ClItemCd"		,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""			,"荷主商品コード"}
						 ,{"ItemCd"			,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"		,"変換先商品コード"}
						 ,{"PackingType"	,"int"		,(int)11	,""		,(boolean)true	,"0"		,"荷姿タイプ"}
						};
		return Rt;
	}
	
	private static Object[][] KM0070_SHIPPINGCOMPANYMST_Definition(){
		Object[][] Rt	={
						  {"ShippingCompanyCd"		,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""			,"運送会社CD"}
						 ,{"ShippingCompanyName01"	,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"		,"運送会社表記名"}
						 ,{"ShippingCompanyName02"	,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"		,"運送会社正式名"}
						 ,{"ShippingCompanyName03"	,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"		,"運送会社略名"}
						 ,{"Post"					,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"		,"運送会社郵便"}
						 ,{"Add01"					,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"		,"運送会社住所1"}
						 ,{"Add02"					,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"		,"運送会社住所2"}
						 ,{"Add03"					,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"		,"運送会社住所3"}
						 ,{"Tel"					,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"		,"運送会社電話"}
						 ,{"Fax"					,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"		,"運送会社FAX"}
						 ,{"Mail"					,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"		,"運送会社MAIL"}
						 ,{"Com01"					,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"		,"コメント1"}
						 ,{"Com02"					,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"		,"コメント2"}
						 ,{"Com03"					,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"		,"コメント3"}
						 ,{"ShimeDate"				,"int"		,(int)11	,""		,(boolean)true	,"0"		,"締日"}
						 ,{"ShimeBasis"				,"int"		,(int)11	,""		,(boolean)true	,"0"		,"請求基準"}
						 ,{"EntryDate"				,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"		,"データ登録日時"}
						 ,{"UpdateDate"				,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"		,"データ更新日時"}
						 ,{"EntryUser"				,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"		,"登録者コード"}
						 ,{"UpdateUser"				,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"		,"更新者コード"}
						 ,{"PTMSCD"					,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"		,"基幹システム傭車コード"}
						 ,{"DelFg"					,"tinyint"	,(int)1		,""		,(boolean)true	,"0"		,"削除区分"}
						 ,{"ExportDataType"			,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"		,"配車データ抽出タイプ"}
						};
		return Rt;
	}
	private static Object[][] KM0071_CARMST_Definition(){
		Object[][] Rt	={
						  {"WHCD"				,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""			,"担当倉庫"}
						 ,{"ShippingCompanyCd"	,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""			,"運送会社CD"}
						 ,{"CarCd"				,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""			,"車輛CD"}
						 ,{"CarName01"			,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"		,"車輛表記名"}
						 ,{"CarName02"			,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"		,"車輛正式名"}
						 ,{"CarName03"			,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"		,"車輛略名"}
						 ,{"DriverCd"			,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"		,"乗務員CD"}
						 ,{"PTMSCD"				,"varchar"	,(int)12	,""		,(boolean)true	,"NULL"		,"基幹システム車輛コード"}
						 ,{"EntryDate"			,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"		,"データ登録日時"}
						 ,{"UpdateDate"			,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"		,"データ更新日時"}
						 ,{"EntryUser"			,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"		,"登録者コード"}
						 ,{"UpdateUser"			,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"		,"更新者コード"}
						 ,{"DelFg"				,"tinyint"	,(int)1		,""		,(boolean)true	,"0"		,"削除フラグ"}
						};
		return Rt;
	}
	
	private static Object[][] KM0080_FEEBASEMST_Definition(){
		Object[][] Rt	={
						  {"cl_cd"					,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""			,"荷主コード"}
						 ,{"DeliveryTypeCd"			,"varchar"	,(int)20	,"KEY"	,(boolean)false	,"0"		,"運送タイプコード01"}
						 ,{"DeliveryTypeCd02"		,"varchar"	,(int)20	,"KEY"	,(boolean)false	,"0"		,"運送タイプコード02"}
						 ,{"DeliveryTypeCd03"		,"varchar"	,(int)20	,"KEY"	,(boolean)false	,"0"		,"運送タイプコード03"}
						 ,{"DeliveryTypeCd04"		,"varchar"	,(int)20	,"KEY"	,(boolean)false	,"0"		,"運送タイプコード04"}
						 ,{"DeliveryTypeCd05"		,"varchar"	,(int)20	,"KEY"	,(boolean)false	,"0"		,"運送タイプコード05"}
						 ,{"PatternCD"				,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""			,"パターンコード"}
						 ,{"PatternName"			,"varchar"	,(int)100	,""		,(boolean)false	,"NULL"		,"パターン名"}
						 ,{"LogicType"				,"varchar"	,(int)20	,""		,(boolean)false	,"NULL"		,"運賃計算ロジックタイプ"}
						 ,{"SizeFG"					,"int"		,(int)11	,""		,(boolean)false	,"0"		,"重量サイズ基準"}
						 ,{"BaseFee"				,"int"		,(int)11	,""		,(boolean)false	,"0"		,"基本運賃"}
						 ,{"MinFee"					,"int"		,(int)11	,""		,(boolean)false	,"0"		,"最低運賃"}
						 ,{"Range01"				,"float"	,(int)0		,""		,(boolean)false	,"0"		,"重量範囲01"}
						 ,{"RangeBaseFee01"			,"int"		,(int)11	,""		,(boolean)false	,"0"		,"運賃価格01"}
						 ,{"RangeUnitFee01"			,"float"	,(int)0		,""		,(boolean)false	,"0"		,"運賃単価01"}
						 ,{"Range02"				,"float"	,(int)0		,""		,(boolean)false	,"0"		,"重量範囲02"}
						 ,{"RangeBaseFee02"			,"int"		,(int)11	,""		,(boolean)false	,"0"		,"運賃価格02"}
						 ,{"RangeUnitFee02"			,"float"	,(int)0		,""		,(boolean)false	,"0"		,"運賃単価02"}
						 ,{"Range03"				,"float"	,(int)0		,""		,(boolean)false	,"0"		,"重量範囲03"}
						 ,{"RangeBaseFee03"			,"int"		,(int)11	,""		,(boolean)false	,"0"		,"運賃価格03"}
						 ,{"RangeUnitFee03"			,"float"	,(int)0		,""		,(boolean)false	,"0"		,"運賃単価03"}
						 ,{"Range04"				,"float"	,(int)0		,""		,(boolean)false	,"0"		,"重量範囲04"}
						 ,{"RangeBaseFee04"			,"int"		,(int)11	,""		,(boolean)false	,"0"		,"運賃価格04"}
						 ,{"RangeUnitFee04"			,"float"	,(int)0		,""		,(boolean)false	,"0"		,"運賃単価04"}
						 ,{"Range05"				,"float"	,(int)0		,""		,(boolean)false	,"0"		,"重量範囲05"}
						 ,{"RangeBaseFee05"			,"int"		,(int)11	,""		,(boolean)false	,"0"		,"運賃価格05"}
						 ,{"RangeUnitFee05"			,"float"	,(int)0		,""		,(boolean)false	,"0"		,"運賃単価05"}
						 ,{"Range06"				,"float"	,(int)0		,""		,(boolean)false	,"0"		,"重量範囲06"}
						 ,{"RangeBaseFee06"			,"int"		,(int)11	,""		,(boolean)false	,"0"		,"運賃価格06"}
						 ,{"RangeUnitFee06"			,"float"	,(int)0		,""		,(boolean)false	,"0"		,"運賃単価06"}
						 ,{"Range07"				,"float"	,(int)0		,""		,(boolean)false	,"0"		,"重量範囲07"}
						 ,{"RangeBaseFee07"			,"int"		,(int)11	,""		,(boolean)false	,"0"		,"運賃価格07"}
						 ,{"RangeUnitFee07"			,"float"	,(int)0		,""		,(boolean)false	,"0"		,"運賃単価07"}
						 ,{"Range08"				,"float"	,(int)0		,""		,(boolean)false	,"0"		,"重量範囲08"}
						 ,{"RangeBaseFee08"			,"int"		,(int)11	,""		,(boolean)false	,"0"		,"運賃価格08"}
						 ,{"RangeUnitFee08"			,"float"	,(int)0		,""		,(boolean)false	,"0"		,"運賃単価08"}
						 ,{"Range09"				,"float"	,(int)0		,""		,(boolean)false	,"0"		,"重量範囲09"}
						 ,{"RangeBaseFee09"			,"int"		,(int)11	,""		,(boolean)false	,"0"		,"運賃価格09"}
						 ,{"RangeUnitFee09"			,"float"	,(int)0		,""		,(boolean)false	,"0"		,"運賃単価09"}
						 ,{"Range10"				,"float"	,(int)0		,""		,(boolean)false	,"0"		,"重量範囲10"}
						 ,{"RangeBaseFee10"			,"int"		,(int)11	,""		,(boolean)false	,"0"		,"運賃価格10"}
						 ,{"RangeUnitFee10"			,"float"	,(int)0		,""		,(boolean)false	,"0"		,"運賃単価10"}
						 ,{"Range11"				,"float"	,(int)0		,""		,(boolean)false	,"0"		,"重量範囲11"}
						 ,{"RangeBaseFee11"			,"int"		,(int)11	,""		,(boolean)false	,"0"		,"運賃価格11"}
						 ,{"RangeUnitFee11"			,"float"	,(int)0		,""		,(boolean)false	,"0"		,"運賃単価11"}
						 ,{"Range12"				,"float"	,(int)0		,""		,(boolean)false	,"0"		,"重量範囲12"}
						 ,{"RangeBaseFee12"			,"int"		,(int)11	,""		,(boolean)false	,"0"		,"運賃価格12"}
						 ,{"RangeUnitFee12"			,"float"	,(int)0		,""		,(boolean)false	,"0"		,"運賃単価12"}
						 ,{"Range13"				,"float"	,(int)0		,""		,(boolean)false	,"0"		,"重量範囲13"}
						 ,{"RangeBaseFee13"			,"int"		,(int)11	,""		,(boolean)false	,"0"		,"運賃価格13"}
						 ,{"RangeUnitFee13"			,"float"	,(int)0		,""		,(boolean)false	,"0"		,"運賃単価13"}
						 ,{"Range14"				,"float"	,(int)0		,""		,(boolean)false	,"0"		,"重量範囲14"}
						 ,{"RangeBaseFee14"			,"int"		,(int)11	,""		,(boolean)false	,"0"		,"運賃価格14"}
						 ,{"RangeUnitFee14"			,"float"	,(int)0		,""		,(boolean)false	,"0"		,"運賃単価14"}
						 ,{"Range15"				,"float"	,(int)0		,""		,(boolean)false	,"0"		,"重量範囲15"}
						 ,{"RangeBaseFee15"			,"int"		,(int)11	,""		,(boolean)false	,"0"		,"運賃価格15"}
						 ,{"RangeUnitFee15"			,"float"	,(int)0		,""		,(boolean)false	,"0"		,"運賃単価15"}
						 ,{"Range16"				,"float"	,(int)0		,""		,(boolean)false	,"0"		,"重量範囲16"}
						 ,{"RangeBaseFee16"			,"int"		,(int)11	,""		,(boolean)false	,"0"		,"運賃価格16"}
						 ,{"RangeUnitFee16"			,"float"	,(int)0		,""		,(boolean)false	,"0"		,"運賃単価16"}
						 ,{"Range17"				,"float"	,(int)0		,""		,(boolean)false	,"0"		,"重量範囲17"}
						 ,{"RangeBaseFee17"			,"int"		,(int)11	,""		,(boolean)false	,"0"		,"運賃価格17"}
						 ,{"RangeUnitFee17"			,"float"	,(int)0		,""		,(boolean)false	,"0"		,"運賃単価17"}
						 ,{"Range18"				,"float"	,(int)0		,""		,(boolean)false	,"0"		,"重量範囲18"}
						 ,{"RangeBaseFee18"			,"int"		,(int)11	,""		,(boolean)false	,"0"		,"運賃価格18"}
						 ,{"RangeUnitFee18"			,"float"	,(int)0		,""		,(boolean)false	,"0"		,"運賃単価18"}
						 ,{"Range19"				,"float"	,(int)0		,""		,(boolean)false	,"0"		,"重量範囲19"}
						 ,{"RangeBaseFee19"			,"int"		,(int)11	,""		,(boolean)false	,"0"		,"運賃価格19"}
						 ,{"RangeUnitFee19"			,"float"	,(int)0		,""		,(boolean)false	,"0"		,"運賃単価19"}
						 ,{"Range20"				,"float"	,(int)0		,""		,(boolean)false	,"0"		,"重量範囲20"}
						 ,{"RangeBaseFee20"			,"int"		,(int)11	,""		,(boolean)false	,"0"		,"運賃価格20"}
						 ,{"RangeUnitFee20"			,"float"	,(int)0		,""		,(boolean)false	,"0"		,"運賃単価20"}
						 ,{"AddDeliFee01"			,"int"		,(int)11	,""		,(boolean)false	,"0"		,"付帯費用1"}
						 ,{"AddDeliFee02"			,"int"		,(int)11	,""		,(boolean)false	,"0"		,"付帯費用2"}
						 ,{"AddDeliFee03"			,"int"		,(int)11	,""		,(boolean)false	,"0"		,"付帯費用3"}
						 ,{"HaighWayFee01"			,"int"		,(int)11	,""		,(boolean)false	,"0"		,"高速代等実費精算分1（内税）"}
						 ,{"HaighWayFee02"			,"int"		,(int)11	,""		,(boolean)false	,"0"		,"高速代等実費精算分2（内税）"}
						 ,{"EntryDate"				,"datetime"	,(int)0		,""		,(boolean)false	,"NULL"		,"データ登録日時"}
						 ,{"UpdateDate"				,"datetime"	,(int)0		,""		,(boolean)false	,"NULL"		,"データ更新日時"}
						 ,{"EntryUser"				,"varchar"	,(int)50	,""		,(boolean)false	,"NULL"		,"登録者コード"}
						 ,{"UpdateUser"				,"varchar"	,(int)50	,""		,(boolean)false	,"NULL"		,"更新者コード"}
						 ,{"DelFg"					,"int"		,(int)11	,""		,(boolean)false	,"0"		,"削除フラグ"}
						 ,{"DelijudgeJisFg"			,"int"		,(int)11	,""		,(boolean)false	,"0"		,"届先JIS判定区分"}
						 ,{"AddDeliFee03UnitFee"	,"float"	,(int)0		,""		,(boolean)false	,"0"		,"付帯費用3単価"}
						};
		return Rt;
	}
	
	private static Object[][] KM0081_FEEMST_Definition(){
		Object[][] Rt	={
						  {"cl_cd"				,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""			,"荷主コード"}
						 ,{"PickupWHCD"			,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""			,"集荷倉庫CD"}
						 ,{"DECD"				,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""			,"納品先コード"}
						 ,{"DepartmentCd"		,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""			,"部署CD"}
						 ,{"DeliveryTypeCd"		,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""			,"運送タイプコード01"}
						 ,{"DeliveryTypeCd02"	,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""			,"運送タイプコード02"}
						 ,{"DeliveryTypeCd03"	,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""			,"運送タイプコード03"}
						 ,{"DeliveryTypeCd04"	,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""			,"運送タイプコード04"}
						 ,{"DeliveryTypeCd05"	,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""			,"運送タイプコード05"}
						 ,{"PatternCD"			,"varchar"	,(int)20	,""		,(boolean)false	,""			,"パターンコード"}
						 ,{"EntryDate"			,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"		,"データ登録日時"}
						 ,{"UpdateDate"			,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"		,"データ更新日時"}
						 ,{"EntryUser"			,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"		,"登録者コード"}
						 ,{"UpdateUser"			,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"		,"更新者コード"}
						};
		return Rt;
	}
	
	private static Object[][] KM0082_FEELOGICTYPEMST_Definition(){
		Object[][] Rt	={
						  {"LogicType"		,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""			,"運賃計算ロジックタイプ"}
						 ,{"LogicTypeName"	,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"		,"運賃計算ロジックタイプ名"}
						 ,{"PGCd"			,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"		,"プログラムCD"}
						 ,{"PGName"			,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"		,"プログラム名"}
						 ,{"CallScene"		,"int"		,(int)11	,""		,(boolean)true	,"0"		,"呼び出し場面　※0:請求計算 1:支払計算"}
						 ,{"EntryDate"		,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"		,"データ登録日時"}
						 ,{"UpdateDate"		,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"		,"データ更新日時"}
						 ,{"EntryUser"		,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"		,"登録者コード"}
						 ,{"UpdateUser"		,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"		,"更新者コード"}
						 ,{"DelFg"			,"int"		,(int)11	,""		,(boolean)true	,"0"		,"削除区分"}
						};
		return Rt;
	}
	
	private static Object[][] KM0090_CAUTION_Definition(){
		Object[][] Rt	={
						  {"CautionCd"		,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""			,"注意事項コード"}
						 ,{"ClGpCD"			,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"		,"荷主コード"}
						 ,{"DECD"			,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""			,"納品先コード"}
						 ,{"DepartmentCd"	,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""			,"部署CD"}
						 ,{"CautionTiming"	,"int"		,(int)11	,""		,(boolean)true	,"0"		,"注意事項タイミング"}
						 ,{"CautionName"	,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"		,"注意事項名"}
						 ,{"Caution"		,"varchar"	,(int)300	,""		,(boolean)true	,"NULL"		,"注意事項内容"}
						 ,{"EntryDate"		,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"		,"データ登録日時"}
						 ,{"UpdateDate"		,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"		,"データ更新日時"}
						 ,{"EntryUser"		,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"		,"登録者コード"}
						 ,{"UpdateUser"		,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"		,"更新者コード"}
						 ,{"DelFg"			,"int"		,(int)11	,""		,(boolean)true	,"0"		,"削除区分"}
						};
		return Rt;
	}
	
	private static Object[][] KM0090_PAYBASEMST_Definition(){
		Object[][] Rt	={
						  {"ShippingCompanyCd"		,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""			,"運送会社コード"}
						 ,{"DeliveryTypeCd"			,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""			,"運送タイプコード01"}
						 ,{"DeliveryTypeCd02"		,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""			,"運送タイプコード02"}
						 ,{"DeliveryTypeCd03"		,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""			,"運送タイプコード03"}
						 ,{"DeliveryTypeCd04"		,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""			,"運送タイプコード04"}
						 ,{"DeliveryTypeCd05"		,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""			,"運送タイプコード05"}
						 ,{"PatternCD"				,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""			,"パターンコード"}
						 ,{"PatternName"			,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"		,"パターン名"}
						 ,{"LogicType"				,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"		,"運賃計算ロジックタイプ"}
						 ,{"SizeFG"					,"int"		,(int)11	,""		,(boolean)true	,"0"		,"重量サイズ基準"}
						 ,{"BaseFee"				,"int"		,(int)11	,""		,(boolean)true	,"0"		,"基本運賃"}
						 ,{"MinFee"					,"int"		,(int)11	,""		,(boolean)true	,"0"		,"最低運賃"}
						 ,{"Range01"				,"float"	,(int)0		,""		,(boolean)true	,"0"		,"重量範囲01"}
						 ,{"RangeBaseFee01"			,"int"		,(int)11	,""		,(boolean)true	,"0"		,"運賃価格01"}
						 ,{"RangeUnitFee01"			,"float"	,(int)0		,""		,(boolean)true	,"0"		,"運賃単価01"}
						 ,{"Range02"				,"float"	,(int)0		,""		,(boolean)true	,"0"		,"重量範囲02"}
						 ,{"RangeBaseFee02"			,"int"		,(int)11	,""		,(boolean)true	,"0"		,"運賃価格02"}
						 ,{"RangeUnitFee02"			,"float"	,(int)0		,""		,(boolean)true	,"0"		,"運賃単価02"}
						 ,{"Range03"				,"float"	,(int)0		,""		,(boolean)true	,"0"		,"重量範囲03"}
						 ,{"RangeBaseFee03"			,"int"		,(int)11	,""		,(boolean)true	,"0"		,"運賃価格03"}
						 ,{"RangeUnitFee03"			,"float"	,(int)0		,""		,(boolean)true	,"0"		,"運賃単価03"}
						 ,{"Range04"				,"float"	,(int)0		,""		,(boolean)true	,"0"		,"重量範囲04"}
						 ,{"RangeBaseFee04"			,"int"		,(int)11	,""		,(boolean)true	,"0"		,"運賃価格04"}
						 ,{"RangeUnitFee04"			,"float"	,(int)0		,""		,(boolean)true	,"0"		,"運賃単価04"}
						 ,{"Range05"				,"float"	,(int)0		,""		,(boolean)true	,"0"		,"重量範囲05"}
						 ,{"RangeBaseFee05"			,"int"		,(int)11	,""		,(boolean)true	,"0"		,"運賃価格05"}
						 ,{"RangeUnitFee05"			,"float"	,(int)0		,""		,(boolean)true	,"0"		,"運賃単価05"}
						 ,{"Range06"				,"float"	,(int)0		,""		,(boolean)true	,"0"		,"重量範囲06"}
						 ,{"RangeBaseFee06"			,"int"		,(int)11	,""		,(boolean)true	,"0"		,"運賃価格06"}
						 ,{"RangeUnitFee06"			,"float"	,(int)0		,""		,(boolean)true	,"0"		,"運賃単価06"}
						 ,{"Range07"				,"float"	,(int)0		,""		,(boolean)true	,"0"		,"重量範囲07"}
						 ,{"RangeBaseFee07"			,"int"		,(int)11	,""		,(boolean)true	,"0"		,"運賃価格07"}
						 ,{"RangeUnitFee07"			,"float"	,(int)0		,""		,(boolean)true	,"0"		,"運賃単価07"}
						 ,{"Range08"				,"float"	,(int)0		,""		,(boolean)true	,"0"		,"重量範囲08"}
						 ,{"RangeBaseFee08"			,"int"		,(int)11	,""		,(boolean)true	,"0"		,"運賃価格08"}
						 ,{"RangeUnitFee08"			,"float"	,(int)0		,""		,(boolean)true	,"0"		,"運賃単価08"}
						 ,{"Range09"				,"float"	,(int)0		,""		,(boolean)true	,"0"		,"重量範囲09"}
						 ,{"RangeBaseFee09"			,"int"		,(int)11	,""		,(boolean)true	,"0"		,"運賃価格09"}
						 ,{"RangeUnitFee09"			,"float"	,(int)0		,""		,(boolean)true	,"0"		,"運賃単価09"}
						 ,{"Range10"				,"float"	,(int)0		,""		,(boolean)true	,"0"		,"重量範囲10"}
						 ,{"RangeBaseFee10"			,"int"		,(int)11	,""		,(boolean)true	,"0"		,"運賃価格10"}
						 ,{"RangeUnitFee10"			,"float"	,(int)0		,""		,(boolean)true	,"0"		,"運賃単価10"}
						 ,{"Range11"				,"float"	,(int)0		,""		,(boolean)true	,"0"		,"重量範囲11"}
						 ,{"RangeBaseFee11"			,"int"		,(int)11	,""		,(boolean)true	,"0"		,"運賃価格11"}
						 ,{"RangeUnitFee11"			,"float"	,(int)0		,""		,(boolean)true	,"0"		,"運賃単価11"}
						 ,{"Range12"				,"float"	,(int)0		,""		,(boolean)true	,"0"		,"重量範囲12"}
						 ,{"RangeBaseFee12"			,"int"		,(int)11	,""		,(boolean)true	,"0"		,"運賃価格12"}
						 ,{"RangeUnitFee12"			,"float"	,(int)0		,""		,(boolean)true	,"0"		,"運賃単価12"}
						 ,{"Range13"				,"float"	,(int)0		,""		,(boolean)true	,"0"		,"重量範囲13"}
						 ,{"RangeBaseFee13"			,"int"		,(int)11	,""		,(boolean)true	,"0"		,"運賃価格13"}
						 ,{"RangeUnitFee13"			,"float"	,(int)0		,""		,(boolean)true	,"0"		,"運賃単価13"}
						 ,{"Range14"				,"float"	,(int)0		,""		,(boolean)true	,"0"		,"重量範囲14"}
						 ,{"RangeBaseFee14"			,"int"		,(int)11	,""		,(boolean)true	,"0"		,"運賃価格14"}
						 ,{"RangeUnitFee14"			,"float"	,(int)0		,""		,(boolean)true	,"0"		,"運賃単価14"}
						 ,{"Range15"				,"float"	,(int)0		,""		,(boolean)true	,"0"		,"重量範囲15"}
						 ,{"RangeBaseFee15"			,"int"		,(int)11	,""		,(boolean)true	,"0"		,"運賃価格15"}
						 ,{"RangeUnitFee15"			,"float"	,(int)0		,""		,(boolean)true	,"0"		,"運賃単価15"}
						 ,{"Range16"				,"float"	,(int)0		,""		,(boolean)true	,"0"		,"重量範囲16"}
						 ,{"RangeBaseFee16"			,"int"		,(int)11	,""		,(boolean)true	,"0"		,"運賃価格16"}
						 ,{"RangeUnitFee16"			,"float"	,(int)0		,""		,(boolean)true	,"0"		,"運賃単価16"}
						 ,{"Range17"				,"float"	,(int)0		,""		,(boolean)true	,"0"		,"重量範囲17"}
						 ,{"RangeBaseFee17"			,"int"		,(int)11	,""		,(boolean)true	,"0"		,"運賃価格17"}
						 ,{"RangeUnitFee17"			,"float"	,(int)0		,""		,(boolean)true	,"0"		,"運賃単価17"}
						 ,{"Range18"				,"float"	,(int)0		,""		,(boolean)true	,"0"		,"重量範囲18"}
						 ,{"RangeBaseFee18"			,"int"		,(int)11	,""		,(boolean)true	,"0"		,"運賃価格18"}
						 ,{"RangeUnitFee18"			,"float"	,(int)0		,""		,(boolean)true	,"0"		,"運賃単価18"}
						 ,{"Range19"				,"float"	,(int)0		,""		,(boolean)true	,"0"		,"重量範囲19"}
						 ,{"RangeBaseFee19"			,"int"		,(int)11	,""		,(boolean)true	,"0"		,"運賃価格19"}
						 ,{"RangeUnitFee19"			,"float"	,(int)0		,""		,(boolean)true	,"0"		,"運賃単価19"}
						 ,{"Range20"				,"float"	,(int)0		,""		,(boolean)true	,"0"		,"重量範囲20"}
						 ,{"RangeBaseFee20"			,"int"		,(int)11	,""		,(boolean)true	,"0"		,"運賃価格20"}
						 ,{"RangeUnitFee20"			,"float"	,(int)0		,""		,(boolean)true	,"0"		,"運賃単価20"}
						 ,{"AddDeliFee01"			,"int"		,(int)11	,""		,(boolean)true	,"0"		,"付帯費用1"}
						 ,{"AddDeliFee02"			,"int"		,(int)11	,""		,(boolean)true	,"0"		,"付帯費用2"}
						 ,{"AddDeliFee03"			,"int"		,(int)11	,""		,(boolean)true	,"0"		,"付帯費用3"}
						 ,{"HaighWayFee01"			,"int"		,(int)11	,""		,(boolean)true	,"0"		,"高速代等実費精算分1(内税)"}
						 ,{"HaighWayFee02"			,"int"		,(int)11	,""		,(boolean)true	,"0"		,"高速代等実費精算分2(内税)"}
						 ,{"EntryDate"				,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"		,"データ登録日時"}
						 ,{"UpdateDate"				,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"		,"データ更新日時"}
						 ,{"EntryUser"				,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"		,"登録者コード"}
						 ,{"UpdateUser"				,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"		,"更新者コード"}
						 ,{"DelFg"					,"int"		,(int)11	,""		,(boolean)true	,"0"		,"削除フラグ"}
						 ,{"AddDeliFee03UnitFee"	,"float"	,(int)0		,""		,(boolean)true	,"0"		,"付帯費用3単価"}
						 ,{"SummaryType"			,"int"		,(int)11	,""		,(boolean)true	,"0"		,"集計基準"}
						};
		return Rt;
	}
	
	private static Object[][] KM0091_PAYMST_Definition(){
		Object[][] Rt	={
						  {"WHCD"				,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""			,"担当倉庫"}
						 ,{"ShippingCompanyCd"	,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""			,"運送会社CD"}
						 ,{"CarCd"				,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""			,"車輛CD"}
						 ,{"DeliveryTypeCd"		,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""			,"運送タイプコード01"}
						 ,{"DeliveryTypeCd02"	,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""			,"運送タイプコード02"}
						 ,{"DeliveryTypeCd03"	,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""			,"運送タイプコード03"}
						 ,{"DeliveryTypeCd04"	,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""			,"運送タイプコード04"}
						 ,{"DeliveryTypeCd05"	,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""			,"運送タイプコード05"}
						 ,{"PatternCD"			,"varchar"	,(int)20	,""		,(boolean)false	,""			,"パターンコード"}
						 ,{"EntryDate"			,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"		,"データ登録日時"}
						 ,{"UpdateDate"			,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"		,"データ更新日時"}
						 ,{"EntryUser"			,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"		,"登録者コード"}
						 ,{"UpdateUser"			,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"		,"更新者コード"}
						 ,{"DelFg"				,"int"		,(int)11	,""		,(boolean)true	,"0"		,"削除区分"}
						};
		return Rt;
	}
	
	private static Object[][] KM0100_OKURIPROGRESSMST_Definition(){
		Object[][] Rt	={
						  {"ProgressCd"		,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"進捗CD"}
						 ,{"ProgressName"	,"varchar"	,(int)100	,""		,(boolean)true	,""		,"進捗名"}
						 ,{"EntryDate"		,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"データ登録日時"}
						 ,{"UpdateDate"		,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"データ更新日時"}
						 ,{"EntryUser"		,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"登録者コード"}
						 ,{"UpdateUser"		,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"更新者コード"}
						 ,{"DelFg"			,"int"		,(int)11	,""		,(boolean)true	,"0"	,"削除区分"}
						};
		return Rt;
	}
	
	private static Object[][] KM0110_CALLPGMST_Definition(){
		Object[][] Rt	={
						  {"PGCd"		,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"プログラムCD"}
						 ,{"PGName"		,"varchar"	,(int)100	,""		,(boolean)true	,""		,"プログラム名"}
						 ,{"CallScene"	,"int"		,(int)11	,""		,(boolean)true	,"0"	,"呼び出し場面"}
						 ,{"EntryDate"	,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"データ登録日時"}
						 ,{"UpdateDate"	,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"データ更新日時"}
						 ,{"EntryUser"	,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"登録者コード"}
						 ,{"UpdateUser"	,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"更新者コード"}
						 ,{"DelFg"		,"int"		,(int)11	,""		,(boolean)true	,"0"	,"削除区分"}
						};
		return Rt;
	}
	
	private static Object[][] KM0122_CourseGlpMST_Definition(){
		Object[][] Rt	={
						  {"WhCd"			,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"出発倉庫コード"}
						 ,{"CourseGpCd"		,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"コースグループコード"}
						 ,{"CourseGpName"	,"varchar"	,(int)100	,""		,(boolean)true	,""		,"コースグループ名"}
						 ,{"EntryDate"		,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"登録日"}
						 ,{"UpdateDate"		,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"更新日"}
						 ,{"EntryUser"		,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"登録者"}
						 ,{"UpdateUser"		,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"更新者"}
						};
		return Rt;
	}
	
	private static Object[][] KM0123_CourseMST_Definition(){
		Object[][] Rt	={
						  {"WhCd"					,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""			,"出発倉庫コード"}
						 ,{"CourseGpCd"				,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""			,"コースグループコード"}
						 ,{"CourseCd"				,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""			,"コースCd"}
						 ,{"CourseName"				,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"		,"コース名"}
						 ,{"DriverWhCd"				,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"		,"倉庫コード"}
						 ,{"DriverCompanyCd"		,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"		,"運送会社CD"}
						 ,{"DriverCd"				,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"		,"乗務員CD"}
						 ,{"SetBinNo"				,"int"		,(int)11	,""		,(boolean)true	,"1'"		,"二次配車時にセットする便NO"}
						 ,{"EntryDate"				,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"		,"登録日"}
						 ,{"UpdateDate"				,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"		,"更新日"}
						 ,{"EntryUser"				,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"		,"登録者"}
						 ,{"UpdateUser"				,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"		,"更新者"}
						 ,{"OnePitOneDeliveryFg"	,"int"		,(int)11	,""		,(boolean)true	,"0"		,"ピット割付フラグ"}
						};
		return Rt;
	}
	
	private static Object[][] KM0124_CourseDeliveryMST_Definition(){
		Object[][] Rt	={
						  {"WhCd"			,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""			,"出発倉庫コード"}
						 ,{"CourseGpCd"		,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""			,"コースグループコード"}
						 ,{"CourseCd"		,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"		,"コースCd"}
						 ,{"DeliCd"			,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""			,"届先Cd"}
						 ,{"EntryDate"		,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"		,"登録日"}
						 ,{"UpdateDate"		,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"		,"更新日"}
						 ,{"EntryUser"		,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"		,"登録者"}
						 ,{"UpdateUser"		,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"		,"更新者"}
						 ,{"CourseOrder"	,"int"		,(int)11	,""		,(boolean)true	,"0"		,"コース配送順序"}
						};
		return Rt;
	}
	
	private static Object[][] KM0125_MyDriverMST_Definition(){
		Object[][] Rt	={
						  {"MyDriverListCd"			,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"乗務員リストコード"}
						 ,{"MyDriverListName"		,"varchar"	,(int)100	,""		,(boolean)true	,""		,"乗務員リスト名"}
						 ,{"UseWHCD"				,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"利用ユーザー所属倉庫コード"}
						 ,{"UseShippingCompanyCd"	,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"利用ユーザー所属運送会社CD"}
						 ,{"UseUserCd"				,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"利用ユーザーCD"}
						 ,{"EntryDate"				,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"登録日"}
						 ,{"UpdateDate"				,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"更新日"}
						 ,{"EntryUser"				,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"登録者"}
						 ,{"UpdateUser"				,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"更新者"}
						};
		return Rt;
	}
	
	private static Object[][] KM0126_MyDriverList_Definition(){
		Object[][] Rt	={
						  {"MyDriverListCd"				,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"乗務員リストコード"}
						 ,{"MyDriverCd"					,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"乗務員呼出しコード"}
						 ,{"DriverWHCD"					,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"乗務員所属倉庫コード"}
						 ,{"DriverShippingCompanyCd"	,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"乗務員所属運送会社CD"}
						 ,{"DriverUserCd"				,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"乗務員ユーザーCD"}
						 ,{"DriverCallName"				,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"乗務員ユーザー呼称"}
						 ,{"SetBinNo"					,"int"		,(int)11	,""		,(boolean)true	,"1"	,"二次配車時にセットする便NO"}
						 ,{"EntryDate"					,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"登録日"}
						 ,{"UpdateDate"					,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"更新日"}
						 ,{"EntryUser"					,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"登録者"}
						 ,{"UpdateUser"					,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"更新者"}
						};
		return Rt;
	}
	
	private static Object[][] KT0010_OKURI_HD_Definition(){
		Object[][] Rt	={
						  {"cl_cd"					,"varchar"	,(int)20	,""		,(boolean)false	,""		,"荷主コード"}
						 ,{"InvoiceWHCD"			,"varchar"	,(int)20	,""		,(boolean)false	,""		,"倉庫コード"}
						 ,{"OkuriNo"				,"int"		,(int)11	,"KEY"	,(boolean)false	,"0"	,"送り状番号"}
						 ,{"ClDeliNo"				,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"荷主管理番号"}
						 ,{"PickupWHCD"				,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"集荷倉庫CD"}
						 ,{"PurposeFG"				,"int"		,(int)11	,""		,(boolean)true	,"0"	,"目的フラグ"}
						 ,{"PlanDate"				,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"出荷予定日"}
						 ,{"ShipDate"				,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"出荷実績日"}
						 ,{"SPPlanDate"				,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"着日指定"}
						 ,{"SPDate"					,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"着日実績"}
						 ,{"SPTimeFG"				,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"時間指定区分"}
						 ,{"SPTimeStr"				,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"時間指定開始"}
						 ,{"SPTimeEnd"				,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"時間指定終了"}
						 ,{"TotalWeight"			,"float"	,(int)0		,""		,(boolean)true	,"0"	,"荷物重量(kg)"}
						 ,{"TotalSize"				,"float"	,(int)0		,""		,(boolean)true	,"0"	,"荷物サイズ"}
						 ,{"TotalQty"				,"float"	,(int)0		,""		,(boolean)true	,"0"	,"個口数"}
						 ,{"DeliveryTypeCd"			,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"運送タイプ01"}
						 ,{"DeliTypeName"			,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"運送タイプ名01"}
						 ,{"DeliveryTypeCd02"		,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"運送タイプ02"}
						 ,{"DeliTypeName02"			,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"運送タイプ名02"}
						 ,{"DeliveryTypeCd03"		,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"運送タイプ03"}
						 ,{"DeliTypeName03"			,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"運送タイプ名03"}
						 ,{"DeliveryTypeCd04"		,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"運送タイプ04"}
						 ,{"DeliTypeName04"			,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"運送タイプ名04"}
						 ,{"DeliveryTypeCd05"		,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"運送タイプ05"}
						 ,{"DeliTypeName05"			,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"運送タイプ名05"}
						 ,{"CodFG"					,"int"		,(int)11	,""		,(boolean)true	,"0"	,"代引きフラグ"}
						 ,{"CodPayTotal"			,"int"		,(int)11	,""		,(boolean)true	,"0"	,"代引き収受金額合計"}
						 ,{"CodPay"					,"int"		,(int)11	,""		,(boolean)true	,"0"	,"代引き金額"}
						 ,{"CodConsumptionTax"		,"int"		,(int)11	,""		,(boolean)true	,"0"	,"代引き消費税"}
						 ,{"ChildrenFG"				,"int"		,(int)11	,""		,(boolean)true	,"0"	,"赤黒区分"}
						 ,{"ParentOkuriNo"			,"int"		,(int)11	,""		,(boolean)true	,"0"	,"親伝票番号"}
						 ,{"NiokuriCd"				,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"荷送り人コード"}
						 ,{"NiokuriDepartmentCd"	,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"部署CD"}
 						 ,{"NiokuriName01"			,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"荷送り人名01"}
						 ,{"NiokuriName02"			,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"荷送り人名02"}
						 ,{"NiokuriName03"			,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"荷送り人名03"}
						 ,{"NiokuriPost"			,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"荷送り人郵便番号"}
						 ,{"NiokuriAdd01"			,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"荷送り人住所01"}
						 ,{"NiokuriAdd02"			,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"荷送り人住所02"}
						 ,{"NiokuriAdd03"			,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"荷送り人住所03"}
						 ,{"NioKuriTel"				,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"荷送り人TEL"}
						 ,{"NioKuriFax"				,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"荷送り人FAX"}
						 ,{"NioKuriMail"			,"varchar"	,(int)200	,""		,(boolean)true	,"NULL"	,"荷送り人MAIL"}
						 ,{"NiokuriMunicCd"			,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"荷送人市区町村CD"}
						 ,{"DeliCd"					,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"荷届け先コード"}
						 ,{"ClDeliCd"				,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"荷主荷届け先コード"}
						 ,{"DeliDepartmentCd"		,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"部署CD"}
						 ,{"DeliName01"				,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"荷届け先名01"}
						 ,{"DeliName02"				,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"荷届け先名02"}
						 ,{"DeliName03"				,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"荷届け先名03"}
						 ,{"DeliPost"				,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"荷届け先郵便番号"}
						 ,{"DeliAdd01"				,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"荷届け先住所01"}
						 ,{"DeliAdd02"				,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"荷届け先住所02"}
						 ,{"DeliAdd03"				,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"荷届け先住所03"}
						 ,{"DeliTel"				,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"荷届け先TEL"}
						 ,{"DeliFax"				,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"荷届け先FAX"}
						 ,{"DeliMail"				,"varchar"	,(int)200	,""		,(boolean)true	,"NULL"	,"荷届け先MAIL"}
						 ,{"DeliMunicCd"			,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"荷届先市区町村CD"}
						 ,{"Com01"					,"varchar"	,(int)300	,""		,(boolean)true	,"NULL"	,"コメント01"}
						 ,{"Com02"					,"varchar"	,(int)300	,""		,(boolean)true	,"NULL"	,"コメント02"}
						 ,{"Com03"					,"varchar"	,(int)300	,""		,(boolean)true	,"NULL"	,"コメント03"}
						 ,{"Com04"					,"varchar"	,(int)300	,""		,(boolean)true	,"NULL"	,"コメント04"}
						 ,{"Com05"					,"varchar"	,(int)300	,""		,(boolean)true	,"NULL"	,"コメント05"}
						 ,{"Status"					,"int"		,(int)11	,""		,(boolean)true	,"0"	,"状況"}
						 ,{"TaxFg"					,"int"		,(int)11	,""		,(boolean)true	,"0"	,"税区分"}
						 ,{"TaxRate"				,"int"		,(int)11	,""		,(boolean)true	,"0"	,"税率"}
						 ,{"DeliFee"				,"int"		,(int)11	,""		,(boolean)true	,"0"	,"運賃"}
						 ,{"AddDeliFee01"			,"int"		,(int)11	,""		,(boolean)true	,"0"	,"付帯費用1"}
						 ,{"AddDeliFee02"			,"int"		,(int)11	,""		,(boolean)true	,"0"	,"付帯費用2"}
						 ,{"AddDeliFee03"			,"int"		,(int)11	,""		,(boolean)true	,"0"	,"付帯費用3"}
						 ,{"HaighWayFee01"			,"int"		,(int)11	,""		,(boolean)true	,"0"	,"高速代等実費精算分1（内税）"}
						 ,{"HaighWayFee02"			,"int"		,(int)11	,""		,(boolean)true	,"0"	,"高速代等実費精算分2（内税）"}
						 ,{"ConsumptionTax"			,"int"		,(int)11	,""		,(boolean)true	,"0"	,"消費税"}
						 ,{"WithOutTaxTotal"		,"int"		,(int)11	,""		,(boolean)true	,"0"	,"税別合計金額"}
						 ,{"TotalFee"				,"int"		,(int)11	,""		,(boolean)true	,"0"	,"税込請求額合計"}
						 ,{"FeeFixFG"				,"int"		,(int)11	,""		,(boolean)true	,"0"	,"金額確定フラグ"}
						 ,{"FeeFixDate"				,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"金額確定日時"}
						 ,{"ReceiptStampFG"			,"int"		,(int)11	,""		,(boolean)true	,"0"	,"受領印チェック"}
						 ,{"ReceiptStampDate"		,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"受領印日時"}
						 ,{"InvoiceStatus"			,"int"		,(int)11	,""		,(boolean)true	,"0"	,"請求ステータス"}
						 ,{"EntryDate"				,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"登録日"}
						 ,{"UpdateDate"				,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"更新日"}
						 ,{"EntryUser"				,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"登録者"}
						 ,{"UpdateUser"				,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"更新者"}
						 ,{"EntryPG"				,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"登録プログラム"}
						 ,{"UpdatePG"				,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"更新プログラム"}
						 ,{"UseFeeBasePtCd"			,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"適用運賃タリフCD"}
						 ,{"WmsStatus"				,"int"		,(int)11	,""		,(boolean)true	,"0"	,"在庫管理ステータス"}
						 ,{"WmsShipDate"			,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"倉庫出荷日"}
						 ,{"CourseGpCd"				,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"コースグループコード"}
						 ,{"CourseCD"				,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"一次配車コースコード"}
						 ,{"CourseCDEda"			,"int"		,(int)11	,""		,(boolean)true	,"0"	,"一次配車コースコード枝番"}
						 ,{"PitGrp"					,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"一次配車払出ピットグループ"}
						 ,{"Pit01"					,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"一次配車払出ピット01"}
						 ,{"Pit02"					,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"一次配車払出ピット02"}
						 ,{"Pit03"					,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"一次配車払出ピット03"}
						 ,{"Pit04"					,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"一次配車払出ピット04"}
						 ,{"Pit05"					,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"一次配車払出ピット05"}
						};
		return Rt;
	}
	/*
	private static Object[][] KT0011_OKURI_MS_Definition(){
		Object[][] Rt	={
						 ,{"cl_cd` varchar(20) NOT NULL,
						 ,{"InvoiceWHCD` varchar(20) NOT NULL,
						 ,{"kuriNo` int(11) NOT NULL,
						 ,{"MsNo` int(11) NOT NULL,
						 ,{"DeliNo` varchar(20) DEFAULT '0',
						 ,{"DelliMsNo` int(11) DEFAULT '0',
						 ,{"ClOrderNo` varchar(50) DEFAULT NULL,
						 ,{"ClGpCd` varchar(20) DEFAULT NULL,
						 ,{"ItemCd` varchar(20) DEFAULT NULL,
						 ,{"ItemName01` varchar(100) DEFAULT NULL,
						 ,{"ItemName02` varchar(100) DEFAULT NULL,
						 ,{"ItemName03` varchar(100) DEFAULT NULL,
						 ,{"UnitWeight` float DEFAULT '0',
						 ,{"UnitSize` float DEFAULT '0',
						 ,{"Qty` float DEFAULT '0',
						 ,{"PackingQty` int(11) NOT NULL DEFAULT '0',
						 ,{"UnitName` varchar(20) NOT NULL,
						 ,{"SubTotalWeight` float DEFAULT '0',
						 ,{"SubTotalSize` float DEFAULT '0',
						 ,{"UnitPrice` float DEFAULT '0',
						 ,{"SubTotalPrice` float DEFAULT '0',
						 ,{"CategoryCd` varchar(20) DEFAULT NULL,
						 ,{"CategoryName` varchar(50) DEFAULT NULL,
						 ,{"TildFG` varchar(20) DEFAULT NULL,
  `TildName` varchar(50) DEFAULT NULL,
  `Com01` varchar(300) DEFAULT NULL,
  `Com02` varchar(300) DEFAULT NULL,
  `Com03` varchar(300) DEFAULT NULL,
  `Com04` varchar(300) DEFAULT NULL,
  `Com05` varchar(300) DEFAULT NULL,
  `EntryDate` datetime DEFAULT NULL,
  `UpdateDate` datetime DEFAULT NULL,
  `EntryUser` varchar(50) DEFAULT NULL,
  `UpdateUser` varchar(50) DEFAULT NULL,
  `Lot` varchar(20) DEFAULT NULL,
  `ExpDate` datetime DEFAULT NULL,
  `PackingType` int(11) NOT NULL DEFAULT '0',
  `ClItemCd` varchar(20) DEFAULT NULL,
  `ItemMDNo` varchar(20) DEFAULT NULL,
  `JanCd` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`OkuriNo`,`MsNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='KT0010_OKURI_HD送り状データヘッダの内訳情報cl_cdとInvoiceWHCDでKT0010_OKURI_HD送り状データヘッダを特定';

						};
		return Rt;
	}
	private static Object[][] KT0012_OKURI_PROGRESS_Definition(){
		Object[][] Rt	={
						
						};
		return Rt;
	}
	private static Object[][] KT0013_SEIKYU"_Definition(){
		Object[][] Rt	={
						
						};
		return Rt;
	}
	private static Object[][] KT0023_SHIHARAI_Definition(){
		Object[][] Rt	={
						
						};
		return Rt;
	}
	private static Object[][] KT0040_PrintControl_Definition(){
		Object[][] Rt	={
						
						};
		return Rt;
	}
	private static Object[][] KT020_HAISHA_HD_Definition(){
		Object[][] Rt	={
						
						};
		return Rt;
	}
	private static Object[][] KT021_HAISHA_MS_Definition(){
		Object[][] Rt	={
						
						};
		return Rt;
	}
	*/
	
	
	private static Object[][] WM0000PARAMETER_Definition(){
		Object[][] Rt	={
						 {"ClWh"		,"varchar"	,(int) 20	,"KEY"	,(boolean)false	,""		,"倉庫コード"}
						,{"ClCd"		,"varchar"	,(int) 20	,"KEY"	,(boolean)false	,""		,"荷主コード"}
						,{"ParaCd"		,"varchar"	,(int) 20	,"KEY"	,(boolean)false	,""		,"パラメータコード"}
						,{"ParaCdSeq"	,"int"		,(int) 11	,"KEY"	,(boolean)false	,""		,"ナンバリング"}
						,{"ParaName"	,"varchar"	,(int)200	,""		,(boolean)true	,""		,"パラメータ名"}
						,{"ParaTxt01"	,"varchar"	,(int)200	,""		,(boolean)true	,""		,"パラメータテキスト項目01"}
						,{"ParaTxt02"	,"varchar"	,(int)200	,""		,(boolean)true	,""		,"パラメータテキスト項目02"}
						,{"ParaTxt03"	,"varchar"	,(int)200	,""		,(boolean)true	,""		,"パラメータテキスト項目03"}
						,{"ParaTxt04"	,"varchar"	,(int)200	,""		,(boolean)true	,""		,"パラメータテキスト項目04"}
						,{"ParaTxt05"	,"varchar"	,(int)200	,""		,(boolean)true	,""		,"パラメータテキスト項目05"}
						,{"ParaTxt06"	,"varchar"	,(int)200	,""		,(boolean)true	,""		,"パラメータテキスト項目06"}
						,{"ParaTxt07"	,"varchar"	,(int)200	,""		,(boolean)true	,""		,"パラメータテキスト項目07"}
						,{"ParaTxt08"	,"varchar"	,(int)200	,""		,(boolean)true	,""		,"パラメータテキスト項目08"}
						,{"ParaTxt09"	,"varchar"	,(int)200	,""		,(boolean)true	,""		,"パラメータテキスト項目09"}
						,{"ParaTxt10"	,"varchar"	,(int)200	,""		,(boolean)true	,""		,"パラメータテキスト項目10"}
						,{"ParaInt01"	,"int"		,(int) 11	,""		,(boolean)true	,"0"	,"パラメータ数値項目01"}
						,{"ParaInt02"	,"int"		,(int) 11	,""		,(boolean)true	,"0"	,"パラメータ数値項目02"}
						,{"ParaInt03"	,"int"		,(int) 11	,""		,(boolean)true	,"0"	,"パラメータ数値項目03"}
						,{"ParaInt04"	,"int"		,(int) 11	,""		,(boolean)true	,"0"	,"パラメータ数値項目04"}
						,{"ParaInt05"	,"int"		,(int) 11	,""		,(boolean)true	,"0"	,"パラメータ数値項目05"}
						,{"ParaInt06"	,"int"		,(int) 11	,""		,(boolean)true	,"0"	,"パラメータ数値項目06"}
						,{"ParaInt07"	,"int"		,(int) 11	,""		,(boolean)true	,"0"	,"パラメータ数値項目07"}
						,{"ParaInt08"	,"int"		,(int) 11	,""		,(boolean)true	,"0"	,"パラメータ数値項目08"}
						,{"ParaInt09"	,"int"		,(int) 11	,""		,(boolean)true	,"0"	,"パラメータ数値項目09"}
						,{"ParaInt10"	,"int"		,(int) 11	,""		,(boolean)true	,"0"	,"パラメータ数値項目10"}
						,{"EntryDate"	,"datetime"	,(int)  0	,""		,(boolean)true	,"NULL"	,"登録日"}
						,{"UpdateDate"	,"datetime"	,(int)  0	,""		,(boolean)true	,"NULL"	,"更新日"}
						,{"EntryUser"	,"varchar"	,(int) 50	,""		,(boolean)true	,"NULL"	,"登録者"}
						,{"UpdateUser"	,"varchar"	,(int) 50	,""		,(boolean)true	,"NULL"	,"更新者"}
 
						};
		return Rt;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void UserMstCreate() {
		
	}
}