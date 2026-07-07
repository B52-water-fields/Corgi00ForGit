import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class A100_TableCheck2{
	public static void TableCheck(){
		Object[][] NeedTabele = NynkoNeedTableList();
		String[] TabeleList = new String[0];
		
		TabeleList = TabeleList("NYANKO");
		for(int i01=0;i01<NeedTabele.length;i01++) {
			if("NYANKO".equals((String)NeedTabele[i01][1])) {
				boolean NewCreateFg = true;
				String NeedTableName	= ((String)NeedTabele[i01][0]).toUpperCase();
				for(int i02=0;i02<TabeleList.length;i02++) {
					String CheckTableName	= TabeleList[i02].toUpperCase();
					if(NeedTableName.equals(CheckTableName)) {
						NewCreateFg = false;
					}
				}
				if(NewCreateFg) {
					TableCreate(NeedTabele[i01]);
				}
			}
			TableAlther(NeedTabele[i01]);
		}
		
		TabeleList = TabeleList("WANKO");
		for(int i01=0;i01<NeedTabele.length;i01++) {
			if("WANKO".equals((String)NeedTabele[i01][1])) {
				boolean NewCreateFg = true;
				String NeedTableName	= ((String)NeedTabele[i01][0]).toUpperCase();
				for(int i02=0;i02<TabeleList.length;i02++) {
					String CheckTableName	= TabeleList[i02].toUpperCase();
					if(NeedTableName.equals(CheckTableName)) {
						NewCreateFg = false;
					}
				}
				if(NewCreateFg) {
					TableCreate(NeedTabele[i01]);
				}
			}
			TableAlther(NeedTabele[i01]);
		}
		
		TabeleList = TabeleList("POST");
		for(int i01=0;i01<NeedTabele.length;i01++) {
			if("POST".equals((String)NeedTabele[i01][1])) {
				boolean NewCreateFg = true;
				String NeedTableName	= ((String)NeedTabele[i01][0]).toUpperCase();
				for(int i02=0;i02<TabeleList.length;i02++) {
					String CheckTableName	= TabeleList[i02].toUpperCase();
					if(NeedTableName.equals(CheckTableName)) {
						NewCreateFg = false;
					}
				}
				if(NewCreateFg) {
					TableCreate(NeedTabele[i01]);
				}
			}
			TableAlther(NeedTabele[i01]);
		}
	}
	
	private static void TableCreate(Object[] TableCreateData) {
		String TableName	= (String)TableCreateData[0];
		String TgtDB		= (String)TableCreateData[1];
		String TableComment	= (String)TableCreateData[2];
		Object[][] NeedDefinition = (Object[][])TableCreateData[3];
		ArrayList<String> PrimaryKeyList	= new ArrayList<String>();
		
		String MySqlDefaultSchema= MySqlDefaultSchema(TgtDB);
		
		String sql = ""
				+"CREATE TABLE IF NOT EXISTS "+MySqlDefaultSchema+".`"+TableName+"` (\n";
		
		for(int i01=0;i01<NeedDefinition.length;i01++) {
			String Name			= (String)NeedDefinition[i01][0];
			String Type			= ((String)NeedDefinition[i01][1]).toLowerCase();
			int Size			= (int)NeedDefinition[i01][2];
			String KeyCheck		= ((String)NeedDefinition[i01][3]).toLowerCase();
			String NullSet 		= "";
			if((boolean)NeedDefinition[i01][4]) {
				NullSet 		= "";
			}else {
				NullSet 		= "NOT NULL ";
			}
			String DefaultSet	= ((String)NeedDefinition[i01][5]).toUpperCase();
			if("NULL".equals(DefaultSet)) {
				
			}else {
				switch(Type) {
					case "varchar":
						break;
					case "int":
						DefaultSet = B100_TextControl.num_only_String(DefaultSet);
						break;
					case "float":
						DefaultSet = B100_TextControl.num_only_String02(DefaultSet);
						break;
					case "tinyint":
						DefaultSet = B100_TextControl.num_only_String(DefaultSet);
						break;
					case "datetime":
						break;
					default:
						break;
				}
				DefaultSet	= "'"+DefaultSet+"'";
			}
			String CommentSet	= (String)NeedDefinition[i01][6];
			
			if("key".equals(KeyCheck)) {
				PrimaryKeyList.add(Name);
			}
			if(0<i01) {sql = sql + ",";}
			switch(Type) {
				case "varchar":
					sql = sql + "`"	+ Name	+"` varchar("+Size+") "	+ NullSet	+ "DEFAULT "	+ DefaultSet	+ " COMMENT '"+CommentSet+"'\n";
					break;
				case "int":
					if("''".equals(DefaultSet)) {
						sql = sql + "`"	+ Name	+"` int(11) "		+ NullSet									+ " COMMENT '"+CommentSet+"'\n";
					}else {
						sql = sql + "`"	+ Name	+"` int(11) "		+ NullSet	+ "DEFAULT "	+ DefaultSet	+ " COMMENT '"+CommentSet+"'\n";
					}
					break;
				case "float":
					if("''".equals(DefaultSet)) {
						sql = sql + "`"	+ Name	+"` float "			+ NullSet									+ " COMMENT '"+CommentSet+"'\n";
					}else {
						sql = sql + "`"	+ Name	+"` float "			+ NullSet	+ "DEFAULT "	+ DefaultSet	+ " COMMENT '"+CommentSet+"'\n";
					}
					break;
				case "tinyint":
					if("''".equals(DefaultSet)) {
						sql = sql + "`"	+ Name	+"` tinyint(1) "	+ NullSet									+ " COMMENT '"+CommentSet+"'\n";
					}else {
						sql = sql + "`"	+ Name	+"` tinyint(1) "	+ NullSet	+ "DEFAULT "	+ DefaultSet	+ " COMMENT '"+CommentSet+"'\n";
					}
					break;
				case "datetime":
					if("''".equals(DefaultSet)) {
						sql = sql + "`"	+ Name	+"` datetime "		+ NullSet									+ " COMMENT '"+CommentSet+"'\n";
					}else {
						sql = sql + "`"	+ Name	+"` datetime "		+ NullSet	+ "DEFAULT "	+ DefaultSet	+ " COMMENT '"+CommentSet+"'\n";
					}
					break;
				default:
					sql = sql + "`"	+ Name	+"` varchar(100) "		+ NullSet	+ "DEFAULT "	+ DefaultSet	+ " COMMENT '"+CommentSet+"'\n";
					break;
			}
		}
		
		if(null!=PrimaryKeyList&&0<PrimaryKeyList.size()) {
			sql = sql+",PRIMARY KEY (";
			for(int i01=0;i01<PrimaryKeyList.size();i01++) {
				if(0<i01) {sql = sql + ",";}
				sql = sql + "`"+PrimaryKeyList.get(i01)+"`";
			}
			sql = sql+")\n";
		}
		sql = sql+") ENGINE=InnoDB DEFAULT CHARSET=utf8  COMMENT='"+TableComment+"';";
		
		//System.out.println(sql);
		
		KickSql(TgtDB,sql);
		
	}
	private static void TableAlther(Object[] TableCreateData) {
		//String[] CheckColumnList= ColumnList((String)TableCreateData[0],(String)TableCreateData[1]);
		//Object[][] NeedDefinition = (Object[][])TableCreateData[3];
	}
	
	private static Object[][] NynkoNeedTableList(){
		//必要になるテーブルの一覧
		Object[][] NeedTabele = {
						 {"KM0000_PARAMETER"				,"NYANKO"	,"共通パラメータマスタ"			,KM0000_PARAMETER_Definition()}
						,{"KM0005_MUNIC"					,"NYANKO"	,"市区町村マスタ"				,KM0005_MUNIC_Definition()}
						,{"KM0010_WHMST"					,"NYANKO"	,"倉庫マスタ"					,KM0010_WHMST_Definition()}
						,{"KM0020_USERMST"					,"NYANKO"	,"ユーザー（乗務員）マスタ"		,KM0020_USERMST_Definition()}
						,{"KM0030_CLIENTMST"				,"NYANKO"	,"荷主マスタ"					,KM0030_CLIENTMST_Definition()}
						,{"KM0031_CLIENT_GROUP"				,"NYANKO"	,"荷主グループマスタ"			,KM0031_CLIENT_GROUP_Definition()}
						,{"KM0040_DELIVERYMST"				,"NYANKO"	,"届け先マスタ"					,KM0040_DELIVERYMST_Definition()}
						,{"KM0041_DELIVERY_COMVERSIONMST"	,"NYANKO"	,"届先変換マスタ"				,KM0041_DELIVERY_COMVERSIONMST_Definition()}
						,{"KM0050_DELIVERY_TYPEMST"			,"NYANKO"	,"運送タイプマスタ"				,KM0050_DELIVERY_TYPEMST_Definition()}
						,{"KM0060_ITEMMST"					,"NYANKO"	,"商品マスタ"					,KM0060_ITEMMST_Definition()}
						,{"KM0061_ITEMMSTSUB"				,"NYANKO"	,"商品サブマスタ"				,KM0061_ITEMMSTSUB_Definition()}
						,{"KM0062_ItemComversionMst"		,"NYANKO"	,"商品変換マスタ"				,KM0062_ItemComversionMst_Definition()}
						,{"KM0070_SHIPPINGCOMPANYMST"		,"NYANKO"	,"運送会社マスタ"				,KM0070_SHIPPINGCOMPANYMST_Definition()}
						,{"KM0071_CARMST"					,"NYANKO"	,"車輌マスタ"					,KM0071_CARMST_Definition()}
						,{"KM0080_FEEBASEMST"				,"NYANKO"	,"運賃基本タリフマスタ"			,KM0080_FEEBASEMST_Definition()}
						,{"KM0081_FEEMST"					,"NYANKO"	,"運賃マスタ"					,KM0081_FEEMST_Definition()}
						,{"KM0082_FEELOGICTYPEMST"			,"NYANKO"	,"運賃計算ロジックマスタ"		,KM0082_FEELOGICTYPEMST_Definition()}
						,{"KM0090_CAUTION"					,"NYANKO"	,"注意事項マスタ"				,KM0090_CAUTION_Definition()}
						,{"KM0090_PAYBASEMST"				,"NYANKO"	,"下払運賃基本タリフマスタ"		,KM0090_PAYBASEMST_Definition()}
						,{"KM0091_PAYMST"					,"NYANKO"	,"下払タリフマスタ"				,KM0091_PAYMST_Definition()}
						,{"KM0100_OKURIPROGRESSMST"			,"NYANKO"	,"進捗状況マスタ"				,KM0100_OKURIPROGRESSMST_Definition()}
						,{"KM0110_CALLPGMST"				,"NYANKO"	,"呼び出しプログラムマスタ"		,KM0110_CALLPGMST_Definition()}
						,{"KM0122_CourseGlpMST"				,"NYANKO"	,"配送コースグループマスタ"		,KM0122_CourseGlpMST_Definition()}
						,{"KM0123_CourseMST"				,"NYANKO"	,"配送コースマスタ"				,KM0123_CourseMST_Definition()}
						,{"KM0124_CourseDeliveryMST"		,"NYANKO"	,"配送コース届先内訳マスタ"		,KM0124_CourseDeliveryMST_Definition()}
						,{"KM0125_MyDriverMST"				,"NYANKO"	,"俺の乗務員リストマスタ"		,KM0125_MyDriverMST_Definition()}
						,{"KM0126_MyDriverList"				,"NYANKO"	,"俺の乗務員リスト"				,KM0126_MyDriverList_Definition()}
						,{"KT0010_OKURI_HD"					,"NYANKO"	,"送り状データヘッダ"			,KT0010_OKURI_HD_Definition()}
						,{"KT0011_OKURI_MS"					,"NYANKO"	,"送り状データ明細"				,KT0011_OKURI_MS_Definition()}
						,{"KT0012_OKURI_PROGRESS"			,"NYANKO"	,"送り状進捗情報"				,KT0012_OKURI_PROGRESS_Definition()}
						,{"KT0013_SEIKYU"					,"NYANKO"	,"運賃請求データ（To荷主）"		,KT0013_SEIKYU_Definition()}
						,{"KT0023_SHIHARAI"					,"NYANKO"	,"運賃支払データ（To傭車）"		,KT0023_SHIHARAI_Definition()}
						,{"KT0040_PrintControl"				,"NYANKO"	,"印刷制御"						,KT0040_PrintControl_Definition()}
						,{"KT020_HAISHA_HD"					,"NYANKO"	,"配車ヘッダ"					,KT020_HAISHA_HD_Definition()}
						,{"KT021_HAISHA_MS"					,"NYANKO"	,"配車明細"						,KT021_HAISHA_MS_Definition()}
						
						,{"WM0000PARAMETER"					,"WANKO"	,"荷主毎パラメータマスタ"		,WM0000PARAMETER_Definition()}
						,{"WM0010LOCATIONMST"				,"WANKO"	,"ロケーションマスタ"			,WM0010LOCATIONMST_Definition()}
						,{"WM0010Supplier"					,"WANKO"	,"仕入先マスタ"					,WM0010Supplier_Definition()}
						,{"WM0015_BerthMst"					,"WANKO"	,"出荷バースマスタ"				,WM0015_BerthMst_Definition()}
						,{"WM0016_PitGlpMST"				,"WANKO"	,"仕分けピットグループマスタ"	,WM0016_PitGlpMST_Definition()}
						,{"WM0017_PitMST"					,"WANKO"	,"仕分けピットマスタ"			,WM0017_PitMST_Definition()}
						,{"WM0020AdjustReason"				,"WANKO"	,"調整理由マスタ"				,WM0020AdjustReason_Definition()}
						,{"WM0031WhFeeBaseMstIn"			,"WANKO"	,"倉庫入荷料単価マスタ"			,WM0031WhFeeBaseMstIn_Definition()}
						,{"WM0032WhFeeBaseMstOut"			,"WANKO"	,"倉庫出荷料単価マスタ"			,WM0032WhFeeBaseMstOut_Definition()}
						,{"WM0033WhFeeBaseMstStock"			,"WANKO"	,"倉庫保管料単価マスタ"			,WM0033WhFeeBaseMstStock_Definition()}
						,{"WM0034WhFeeBaseMstAdjust"		,"WANKO"	,"倉庫在庫調整料単価マスタ"		,WM0034WhFeeBaseMstAdjust_Definition()}
						,{"WW0010ArrivalPlanHd"				,"WANKO"	,"入荷予定ヘッダ"				,WW0010ArrivalPlanHd_Definition()}
						,{"WW0011ArrivalPlanMs"				,"WANKO"	,"入荷予定明細"					,WW0011ArrivalPlanMs_Definition()}
						,{"WW0012ArrivalHd"					,"WANKO"	,"入荷実績ヘッダ"				,WW0012ArrivalHd_Definition()}
						,{"WW0013ArrivaMs"					,"WANKO"	,"入荷実績明細"					,WW0013ArrivaMs_Definition()}
						,{"WW0015Stock"						,"WANKO"	,"在庫"							,WW0015Stock_Definition()}
						,{"WW0016StockAdjust"				,"WANKO"	,"在庫調整"						,WW0016StockAdjust_Definition()}
						,{"WW0020ShipPlovision"				,"WANKO"	,"引当結果"						,WW0020ShipPlovision_Definition()}
						,{"WW0025BerthReservation"			,"WANKO"	,"出荷バース予約"				,WW0025BerthReservation_Definition()}
						,{"WW013101WhFeeInHd"				,"WANKO"	,"倉庫入荷料ヘッダ"				,WW013101WhFeeInHd_Definition()}
						,{"WW013102WhFeeInMs"				,"WANKO"	,"倉庫入荷料明細"				,WW013102WhFeeInMs_Definition()}
						,{"WW013201WhFeeOutHd"				,"WANKO"	,"倉庫出荷料"					,WW013201WhFeeOutHd_Definition()}
						,{"WW013202WhFeeOutMs"				,"WANKO"	,"倉庫出荷料明細"				,}
						,{"WW013301WhFeeStockHd"			,"WANKO"	,""	,}
						,{"WW013302WhFeeStockMs"			,"WANKO"	,""	,}
						,{"WW013401WhFeeAdjustHd"			,"WANKO"	,""	,}
						,{"WW013402WhFeeAdjustMs"			,"WANKO"	,""	,}
						,{"WW013501WhFeeOther"				,"WANKO"	,""	,}
						,{"WW014001WhFeeInvoice"			,"WANKO"	,""	,}
						,{"WW00630ItemRecomendLoc"			,"WANKO"	,"荷主毎推奨ロケ"				,WW00630ItemRecomendLoc_Definition()}
						
						
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
	
	private static Object[][] KT0011_OKURI_MS_Definition(){
		Object[][] Rt	={
						  {"cl_cd"			,"varchar"	,(int)20	,""		,(boolean)false	,""		,"荷主コード"}
						 ,{"InvoiceWHCD"	,"varchar"	,(int)20	,""		,(boolean)false	,""		,"倉庫コード"}
						 ,{"kuriNo"			,"int"		,(int)11	,"KEY"	,(boolean)false	,"0"	,"送り状番号"}
						 ,{"MsNo"			,"int"		,(int)11	,"KEY"	,(boolean)false	,"0"	,"明細番号"}
						 ,{"DeliNo"			,"varchar"	,(int)20	,""		,(boolean)true	,"0"	,"出荷番号"}
						 ,{"DelliMsNo"		,"int"		,(int)11	,""		,(boolean)true	,"0"	,"出荷番号明細番号"}
						 ,{"ClOrderNo"		,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"荷主管理番号"}
						 ,{"ClGpCd"			,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"荷主グループコード"}
						 ,{"ItemCd"			,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"商品コード"}
						 ,{"ItemName01"		,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"商品表記名"}
						 ,{"ItemName02"		,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"商品正式名"}
						 ,{"ItemName03"		,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"商品略名"}
						 ,{"UnitWeight"		,"float"	,(int)0		,""		,(boolean)false	,"0"	,"単位重量"}
						 ,{"UnitSize"		,"float"	,(int)0		,""		,(boolean)false	,"0"	,"単位サイズ"}
						 ,{"Qty"			,"float"	,(int)0		,""		,(boolean)false	,"0"	,"個数"}
						 ,{"PackingQty"		,"int"		,(int)11	,""		,(boolean)false	,"0"	,"荷姿数量"}
						 ,{"UnitName"		,"varchar"	,(int)20	,""		,(boolean)true	,""		,"明細単位"}
						 ,{"SubTotalWeight"	,"float"	,(int)0		,""		,(boolean)false	,"0"	,"明細重量"}
						 ,{"SubTotalSize"	,"float"	,(int)0		,""		,(boolean)false	,"0"	,"明細サイズ"}
						 ,{"UnitPrice"		,"float"	,(int)0		,""		,(boolean)false	,"0"	,"単価"}
						 ,{"SubTotalPrice"	,"float"	,(int)0		,""		,(boolean)false	,"0"	,"金額"}
						 ,{"CategoryCd"		,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"商品分類"}
						 ,{"CategoryName"	,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"商品分類名"}
						 ,{"TildFG"			,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"温度区分"}
						 ,{"TildName"		,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"温度区分名"}
						 ,{"Com01"			,"varchar"	,(int)300	,""		,(boolean)true	,"NULL"	,"コメント01"}
						 ,{"Com02"			,"varchar"	,(int)300	,""		,(boolean)true	,"NULL"	,"コメント02"}
						 ,{"Com03"			,"varchar"	,(int)300	,""		,(boolean)true	,"NULL"	,"コメント03"}
						 ,{"Com04"			,"varchar"	,(int)300	,""		,(boolean)true	,"NULL"	,"コメント04"}
						 ,{"Com05"			,"varchar"	,(int)300	,""		,(boolean)true	,"NULL"	,"コメント05"}
						 ,{"EntryDate"		,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"登録日"}
						 ,{"UpdateDate"		,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"更新日"}
						 ,{"EntryUser"		,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"登録者"}
						 ,{"UpdateUser"		,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"更新者"}
						 ,{"Lot"			,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"ロット指定"}
						 ,{"ExpDate"		,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"賞味期限指定"}
						 ,{"PackingType"	,"int"		,(int)11	,""		,(boolean)false	,"0"	,"荷姿タイプ"}
						 ,{"ClItemCd"		,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"荷主商品CD"}
						 ,{"ItemMDNo"		,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"型番"}
						 ,{"JanCd"			,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"荷姿JanCd"}
						};
		return Rt;
	}
	
	private static Object[][] KT0012_OKURI_PROGRESS_Definition(){
		Object[][] Rt	={
						  {"cl_cd"			,"varchar"	,(int)20	,""		,(boolean)false	,""		,"荷主コード"}
						 ,{"InvoiceWHCD"	,"varchar"	,(int)20	,""		,(boolean)false	,""		,"倉庫コード"}
						 ,{"OkuriNo"		,"int"		,(int)11	,"KEY"	,(boolean)false	,"0"	,"送り状番号"}
						 ,{"ProgressNo"		,"int"		,(int)11	,"KEY"	,(boolean)false	,"0"	,"進捗番号"}
						 ,{"ProgressCd"		,"varchar"	,(int)20	,""		,(boolean)false	,""		,"進捗CD"}
						 ,{"ProgressName"	,"varchar"	,(int)100	,""		,(boolean)false	,""		,"進捗名"}
						 ,{"Com01"			,"varchar"	,(int)300	,""		,(boolean)true	,"NULL"	,"コメント01"}
						 ,{"Com02"			,"varchar"	,(int)300	,""		,(boolean)true	,"NULL"	,"コメント02"}
						 ,{"Com03"			,"varchar"	,(int)300	,""		,(boolean)true	,"NULL"	,"コメント03"}
						 ,{"Com04"			,"varchar"	,(int)300	,""		,(boolean)true	,"NULL"	,"コメント04"}
 						 ,{"Com05"			,"varchar"	,(int)300	,""		,(boolean)true	,"NULL"	,"コメント05"}
						 ,{"EntryDate"		,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"登録日"}
						 ,{"UpdateDate"		,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"更新日"}
						 ,{"EntryUser"		,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"登録者"}
						 ,{"UpdateUser"		,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"更新者"}
						};
		return Rt;
	}
	
	private static Object[][] KT0013_SEIKYU_Definition(){
		Object[][] Rt	={
						  {"cl_cd"				,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"荷主コード"}
						 ,{"InvoiceWHCD"		,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"倉庫コード"}
						 ,{"SeikyuNo"			,"int"		,(int)11	,"KEY"	,(boolean)false	,"0"	,"請求番号"}
						 ,{"OkuriNo"			,"int"		,(int)11	,"KEY"	,(boolean)false	,"0"	,"送り状番号"}
						 ,{"SeikyuDate"			,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"請求日"}
						 ,{"ShimeDate"			,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"締日"}
						 ,{"TaxFg"				,"int"		,(int)11	,""		,(boolean)true	,"0"	,"税区分"}
						 ,{"TaxRate"			,"int"		,(int)11	,""		,(boolean)true	,"0"	,"税率"}
						 ,{"DeliFee"			,"int"		,(int)11	,""		,(boolean)true	,"0"	,"運賃"}
						 ,{"AddDeliFee01"		,"int"		,(int)11	,""		,(boolean)true	,"0"	,"付帯費用1"}
						 ,{"AddDeliFee02"		,"int"		,(int)11	,""		,(boolean)true	,"0"	,"付帯費用2"}
						 ,{"AddDeliFee03"		,"int"		,(int)11	,""		,(boolean)true	,"0"	,"付帯費用3"}
						 ,{"HaighWayFee01"		,"int"		,(int)11	,""		,(boolean)true	,"0"	,"高速代等実費精算分1（内税）"}
						 ,{"HaighWayFee02"		,"int"		,(int)11	,""		,(boolean)true	,"0"	,"高速代等実費精算分2（内税）"}
						 ,{"ConsumptionTax"		,"int"		,(int)11	,""		,(boolean)true	,"0"	,"消費税"}
						 ,{"WithOutTaxTotal"	,"int"		,(int)11	,""		,(boolean)true	,"0"	,"税別合計金額"}
						 ,{"TotalFee"			,"int"		,(int)11	,""		,(boolean)true	,"0"	,"税込請求額合計"}
						 ,{"Com01"				,"varchar"	,(int)300	,""		,(boolean)true	,"NULL"	,"コメント01"}
						 ,{"Com02"				,"varchar"	,(int)300	,""		,(boolean)true	,"NULL"	,"コメント02"}
						 ,{"Com03"				,"varchar"	,(int)300	,""		,(boolean)true	,"NULL"	,"コメント03"}
						 ,{"Com04"				,"varchar"	,(int)300	,""		,(boolean)true	,"NULL"	,"コメント04"}
						 ,{"Com05"				,"varchar"	,(int)300	,""		,(boolean)true	,"NULL"	,"コメント05"}
						 ,{"EntryDate"			,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"登録日"}
						 ,{"UpdateDate"			,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"更新日"}
						 ,{"EntryUser"			,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"登録者"}
						 ,{"UpdateUser"			,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"更新者"}
						 ,{"PrtFg"				,"int"		,(int)11	,""		,(boolean)true	,"0"	,"請求書印刷区分"}
						 ,{"DataOutFg"			,"int"		,(int)11	,""		,(boolean)true	,"0"	,"請求データ抽出区分"}
						};
		return Rt;
	}
	
	private static Object[][] KT0023_SHIHARAI_Definition(){
		Object[][] Rt	={
						  {"DeliWHCD"			,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"配達倉庫コード"}
						 ,{"DeliCompCd"			,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"配送_会社CD"}
						 ,{"ShiharaiNo"			,"int"		,(int)11	,"KEY"	,(boolean)false	,"0"	,"支払通知番号"}
						 ,{"HaishaNo"			,"int"		,(int)11	,"KEY"	,(boolean)false	,"0"	,"配車番号"}
						 ,{"ShiharaiDate"		,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"支払日"}
						 ,{"ShimeDate"			,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"締日"}
						 ,{"TaxFg"				,"int"		,(int)11	,""		,(boolean)true	,"0"	,"税区分"}
						 ,{"TaxRate"			,"int"		,(int)11	,""		,(boolean)true	,"0"	,"税率"}
						 ,{"DeliPay"			,"int"		,(int)11	,""		,(boolean)true	,"0"	,"運賃"}
						 ,{"AddDeliPay01"		,"int"		,(int)11	,""		,(boolean)true	,"0"	,"付帯費用1"}
						 ,{"AddDeliPay02"		,"int"		,(int)11	,""		,(boolean)true	,"0"	,"付帯費用2"}
						 ,{"AddDeliPay03"		,"int"		,(int)11	,""		,(boolean)true	,"0"	,"付帯費用3"}
						 ,{"HaighWayPay01"		,"int"		,(int)11	,""		,(boolean)true	,"0"	,"高速代等実費精算分1（内税）"}
						 ,{"HaighWayPay02"		,"int"		,(int)11	,""		,(boolean)true	,"0"	,"高速代等実費精算分2（内税）"}
						 ,{"ConsumptionTax"		,"int"		,(int)11	,""		,(boolean)true	,"0"	,"消費税"}
						 ,{"WithOutTaxTotal"	,"int"		,(int)11	,""		,(boolean)true	,"0"	,"税別合計金額"}
						 ,{"TotalPay"			,"int"		,(int)11	,""		,(boolean)true	,"0"	,"税込請求額合計"}
						 ,{"EntryDate"			,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"登録日"}
						 ,{"UpdateDate"			,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"更新日"}
						 ,{"EntryUser"			,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"登録者"}
						 ,{"UpdateUser"			,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"更新者"}
						};
		return Rt;
	}
	
	private static Object[][] KT0040_PrintControl_Definition(){
		Object[][] Rt	={
						  {"PrintCd"	,"varchar"	,(int)50	,"KEY"	,(boolean)false	,""		,"印刷帳票CD"}
						 ,{"OkuriNo"	,"varchar"	,(int)50	,"KEY"	,(boolean)false	,""		,"送り状番号等"}
						 ,{"Key01"		,"varchar"	,(int)50	,"KEY"	,(boolean)false	,""		,"サブキー01"}
						 ,{"Key02"		,"varchar"	,(int)50	,"KEY"	,(boolean)false	,""		,"サブキー02"}
						 ,{"Key03"		,"varchar"	,(int)50	,"KEY"	,(boolean)false	,""		,"サブキー03"}
						 ,{"Key04"		,"varchar"	,(int)50	,"KEY"	,(boolean)false	,""		,"サブキー04"}
						 ,{"EntryDate"	,"datetime"	,(int)0		,"KEY"	,(boolean)true	,"NULL"	,"登録日"}
						 ,{"UpdateDate"	,"datetime"	,(int)0		,"KEY"	,(boolean)true	,"NULL"	,"更新日"}
						 ,{"EntryUser"	,"varchar"	,(int)50	,"KEY"	,(boolean)true	,"NULL"	,"登録者"}
						 ,{"UpdateUser"	,"varcha"	,(int)50	,"KEY"	,(boolean)true	,"NULL"	,"更新者"}
						};
		return Rt;
	}
	
	private static Object[][] KT020_HAISHA_HD_Definition(){
		Object[][] Rt	={
						  {"DeliWHCD"			,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"配達倉庫コード"}
						 ,{"HaishaNo"			,"int"		,(int)11	,"KEY"	,(boolean)false	,"0"	,"配車番号"}
						 ,{"PlanDate"			,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"出荷予定日"}
						 ,{"ShipDate"			,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"出荷実績日"}
						 ,{"SPPlanDate"			,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"着日指定"}
						 ,{"SPDate"				,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"着日実績"}
						 ,{"TotalWeight"		,"float"	,(int)0		,""		,(boolean)true	,"0"	,"荷物重量"}
						 ,{"TotalSize"			,"float"	,(int)0		,""		,(boolean)true	,"0"	,"荷物サイズ"}
						 ,{"TotalQty"			,"int"		,(int)11	,""		,(boolean)true	,"0"	,"個口数"}
						 ,{"DeliveryTypeCd"		,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"運送タイプ01"}
						 ,{"DeliTypeName"		,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"運送タイプ名01"}
						 ,{"DeliveryTypeCd02"	,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"運送タイプ02"}
						 ,{"DeliTypeName02"		,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"運送タイプ名02"}
						 ,{"DeliveryTypeCd03"	,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"運送タイプ03"}
						 ,{"DeliTypeName03"		,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"運送タイプ名03"}
						 ,{"DeliveryTypeCd04"	,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"運送タイプ04"}
						 ,{"DeliTypeName04"		,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"運送タイプ名04"}
						 ,{"DeliveryTypeCd05"	,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"運送タイプ05"}
						 ,{"DeliTypeName05"		,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"運送タイプ名05"}
 						 ,{"DeliCompCd"			,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"配送_会社CD"}
						 ,{"DeliCompName"		,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"配送_会社名"}
						 ,{"CarCd"				,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"車輛CD"}
 						 ,{"CarName"			,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"車輛名"}
						 ,{"DriverCd"			,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"乗務員CD"}
						 ,{"DriverName"			,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"乗務員名"}
						 ,{"Status"				,"int"		,(int)11	,""		,(boolean)true	,"0"	,"状況"}
						 ,{"TaxFg"				,"int"		,(int)11	,""		,(boolean)true	,"0"	,"税区分"}
						 ,{"TaxRate"			,"int"		,(int)11	,""		,(boolean)true	,"0"	,"税率"}
						 ,{"DeliPay"			,"int"		,(int)11	,""		,(boolean)true	,"0"	,"運賃"}
						 ,{"AddDeliPay01"		,"int"		,(int)11	,""		,(boolean)true	,"0"	,"付帯費用1"}
						 ,{"AddDeliPay02"		,"int"		,(int)11	,""		,(boolean)true	,"0"	,"付帯費用2"}
						 ,{"AddDeliPay03"		,"int"		,(int)11	,""		,(boolean)true	,"0"	,"付帯費用3"}
						 ,{"HaighWayPay01"		,"int"		,(int)11	,""		,(boolean)true	,"0"	,"高速代等実費精算分1（内税）"}
						 ,{"HaighWayPay02"		,"int"		,(int)11	,""		,(boolean)true	,"0"	,"高速代等実費精算分2（内税）"}
						 ,{"ConsumptionTax"		,"int"		,(int)11	,""		,(boolean)true	,"0"	,"消費税"}
						 ,{"WithOutTaxTotal"	,"int"		,(int)11	,""		,(boolean)true	,"0"	,"税別合計金額"}
						 ,{"TotalPay"			,"int"		,(int)11	,""		,(boolean)true	,"0"	,"税込請求額合計"}
						 ,{"PayFixFG"			,"int"		,(int)11	,""		,(boolean)true	,"0"	,"金額確定フラグ"}
						 ,{"PayFixDate"			,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"金額確定日時"}
						 ,{"InvoiceStatus"		,"int"		,(int)11	,""		,(boolean)true	,"0"	,"支払通知ステータス"}
						 ,{"ChildrenFG"			,"int"		,(int)11	,""		,(boolean)true	,"0"	,"赤黒区分"}
						 ,{"ParentHaishaNo"		,"int"		,(int)11	,""		,(boolean)true	,"0"	,"親伝票番号"}
						 ,{"EntryDate"			,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"登録日"}
						 ,{"UpdateDate"			,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"更新日"}
						 ,{"EntryUser"			,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"登録者"}
						 ,{"UpdateUser"			,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"更新者"}
						 ,{"UsePayBasePtCd"		,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"適用運賃タリフCD"}
						};
		return Rt;
	}
	
	private static Object[][] KT021_HAISHA_MS_Definition(){
		Object[][] Rt	={
						  {"DeliWHCD"		,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,""}
						 ,{"HaishaNo"		,"int"		,(int)11	,"KEY"	,(boolean)false	,"0"	,""}
						 ,{"BinNo"			,"int"		,(int)11	,"KEY"	,(boolean)false	,"0"	,""}
						 ,{"MsNo"			,"int"		,(int)11	,"KEY"	,(boolean)false	,"0"	,""}
						 ,{"InvoiceWHCD"	,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,""}
						 ,{"cl_cd"			,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,""}
						 ,{"OkuriNo"		,"int"		,(int)11	,""		,(boolean)true	,"0"	,""}
						 ,{"Status"			,"int"		,(int)11	,""		,(boolean)true	,"0"	,""}
						 ,{"FG01"			,"int"		,(int)11	,""		,(boolean)true	,"0"	,""}
						 ,{"FG02"			,"int"		,(int)11	,""		,(boolean)true	,"0"	,""}
						 ,{"FG03"			,"int"		,(int)11	,""		,(boolean)true	,"0"	,""}
						 ,{"FG04"			,"int"		,(int)11	,""		,(boolean)true	,"0"	,""}
						 ,{"FG05"			,"int"		,(int)11	,""		,(boolean)true	,"0"	,""}
						 ,{"Com01"			,"varchar"	,(int)300	,""		,(boolean)true	,"NULL"	,""}
						 ,{"Com02"			,"varchar"	,(int)300	,""		,(boolean)true	,"NULL"	,""}
						 ,{"Com03"			,"varchar"	,(int)300	,""		,(boolean)true	,"NULL"	,""}
						 ,{"Com04"			,"varchar"	,(int)300	,""		,(boolean)true	,"NULL"	,""}
						 ,{"Com05"			,"varchar"	,(int)300	,""		,(boolean)true	,"NULL"	,""}
						 ,{"EntryDate"		,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,""}
						 ,{"UpdateDate"		,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,""}
						 ,{"EntryUser"		,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,""}
						 ,{"UpdateUser"		,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,""}
						};
		return Rt;
	}
	
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
	
	private static Object[][] WM0010LOCATIONMST_Definition(){
		Object[][] Rt	={
						 {"ClCd"		,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"荷主コード"}
						,{"WhCd"		,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"倉庫コード"}
						,{"Loc"			,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"ロケーション"}
						,{"LocName"		,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"ロケーション名称"}
						,{"LocType"		,"float"	,(int)0		,""		,(boolean)true	,"0"	,"ロケタイプ"}
						,{"EntryDate"	,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"登録日"}
						,{"UpdateDate"	,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"更新日"}
						,{"EntryUser"	,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"登録者"}
						,{"UpdateUser"	,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"更新者"}
						};
		return Rt;
	}
	
	private static Object[][] WM0010Supplier_Definition(){
		Object[][] Rt	={
						 {"ClWh"			,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"担当倉庫"}
						,{"ClCd"			,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"荷主CD"}
						,{"SPCd"			,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"仕入先コード"}
						,{"SPName01"		,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"仕入先表記名"}
						,{"SPName02"		,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"仕入先正式名"}
						,{"SPName03"		,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"仕入先略名"}
						,{"SPPost"			,"varchar"	,(int)10	,""		,(boolean)true	,"NULL"	,"仕入先郵便"}
						,{"SPAdd01"			,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"仕入先住所1"}
						,{"SPAdd02"			,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"仕入先住所2"}
						,{"SPAdd03"			,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"仕入先住所3"}
						,{"SPTel"			,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"仕入先電話"}
						,{"SPFax"			,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"仕入先FAX"}
						,{"SPMail"			,"varchar"	,(int)200	,""		,(boolean)true	,"NULL"	,"仕入先MAIL"}
						,{"Com01"			,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"コメント1"}
						,{"Com02"			,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"コメント2"}
						,{"Com03"			,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"コメント3"}
						,{"PTMSCDBMN"		,"varchar"	,(int)12	,""		,(boolean)true	,"NULL"	,"基幹システム部門"}
						,{"PTMSCDNINUSHI"	,"varchar"	,(int)12	,""		,(boolean)true	,"NULL"	,"基幹システム荷主"}
						,{"PaySite"			,"int"		,(int)11	,""		,(boolean)true	,"1"	,"支払いサイト（月数）"}
						,{"PayDate"			,"int"		,(int)11	,""		,(boolean)true	,"99"	,"支払日"}
						,{"ShimeDate"		,"int"		,(int)11	,""		,(boolean)true	,"99"	,"締め日"}
						,{"EntryDate"		,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"登録日"}
						,{"UpdateDate"		,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"更新日"}
						,{"EntryUser"		,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"登録者"}
						,{"UpdateUser"		,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"更新者"}
						,{"DECD"			,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"納品先コード"}
						,{"DepartmentCd"	,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"部署CD"}
						};
		return Rt;
	}
	
	private static Object[][] WM0015_BerthMst_Definition(){
		Object[][] Rt	={
						 {"WhCd"		,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"倉庫コード"}
						,{"BerthCd"		,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"バースCD"}
						,{"BerthName"	,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"バース名"}
						,{"ENTRY_DATE"	,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"登録日"}
						,{"UPDATE_DATE"	,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"更新日"}
						,{"ENTRY_USER"	,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"登録者"}
						,{"UPDATE_USER"	,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"更新者"}
						};
		return Rt;
	}
	
	private static Object[][] WM0016_PitGlpMST_Definition(){
		Object[][] Rt	={
						 {"WhCd"		,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"出発倉庫コード"}
						,{"PitGlpCd"	,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"ピットグループコード"}
						,{"PitGlpName"	,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"ピットグループ名"}
						,{"EntryDate"	,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"登録日"}
						,{"UpdateDate"	,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"更新日"}
						,{"EntryUser"	,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"登録者"}
						,{"UpdateUser"	,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"更新者"}
						};
		return Rt;
	}
	
	private static Object[][] WM0017_PitMST_Definition(){
		Object[][] Rt	={
						 {"WhCd"					,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"出発倉庫コード"}
						,{"PitGlpCd"				,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"ピットグループコード"}
						,{"PitCd"					,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"ピットコード"}
						,{"PitName"					,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"ピット名"}
						,{"Status"					,"int"		,(int)11	,""		,(boolean)true	,"0"	,"ピット占有状態"}
						,{"TakeCoursePlanDate"		,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"占有中コース出荷予定日"}
						,{"TakeCourseGpCd"			,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"占有中コースグループコード"}
						,{"TakeCourseCd"			,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"占有中コースCd"}
						,{"TakeCourseEda"			,"int"		,(int)11	,""		,(boolean)true	,"0"	,"占有中コース枝番"}
						,{"TakeCourseDeliveryCd"	,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"占有中コース指定届先CD"}
						,{"TakeCourseDptCd"			,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"占有中コース指定届先部署CD"}
						,{"TakeCourse01PlanDate"	,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"待機コース01出荷予定日"}
						,{"TakeCourse01GpCd"		,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"待機コース01グループコード"}
						,{"TakeCourse01Cd"			,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"待機コース01Cd"}
						,{"TakeCourse01Eda"			,"int"		,(int)11	,""		,(boolean)true	,"0"	,"待機コース01枝番"}
						,{"TakeCourse01DeliveryCd"	,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"待機コース01指定届先CD"}
						,{"TakeCourse01DptCd"		,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"待機コース01指定届先部署CD"}
						,{"TakeCourse02PlanDate"	,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"待機コース02出荷予定日"}
						,{"TakeCourse02GpCd"		,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"待機コース02グループコード"}
						,{"TakeCourse02Cd"			,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"待機コース02Cd"}
						,{"TakeCourse02Eda"			,"int"		,(int)11	,""		,(boolean)true	,"0"	,"待機コース02枝番"}
						,{"TakeCourse02DeliveryCd"	,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"待機コース02指定届先CD"}
						,{"TakeCourse02DptCd"		,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"待機コース02指定届先部署CD"}
						,{"EntryDate"				,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"登録日"}
						,{"UpdateDate"				,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"更新日"}
						,{"EntryUser"				,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"登録者"}
						,{"UpdateUser"				,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"更新者"}
						};
		return Rt;
	}
	
	private static Object[][] WM0020AdjustReason_Definition(){
		Object[][] Rt	={
						 {"ClCd"				,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"荷主コード"}
						,{"WhCd"				,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"倉庫コード"}
						,{"AdjustReasonCd"		,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"調整理由コード"}
						,{"AdjustReasonName"	,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"調整理由名"}
						,{"EntryDate"			,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"登録日"}
						,{"UpdateDate"			,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"更新日"}
						,{"EntryUser"			,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"登録者"}
						,{"UpdateUser"			,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"更新者"}
						};
		return Rt;
	}
	
	private static Object[][] WM0031WhFeeBaseMstIn_Definition(){
		Object[][] Rt	={
						 {"ClCd"				,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"荷主コード"}
						,{"WhCd"				,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"倉庫コード"}
						,{"ArrivalFeeCd"		,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"入荷料金コード"}
						,{"ArrivalFeeName"		,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"入荷料金名"}
						,{"DeliveryTypeCd01"	,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"運送タイプコード01"}
						,{"DeliveryTypeCd02"	,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"運送タイプコード02"}
						,{"DeliveryTypeCd03"	,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"運送タイプコード03"}
						,{"DeliveryTypeCd04"	,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"運送タイプコード04"}
						,{"DeliveryTypeCd05"	,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"運送タイプコード05"}
						,{"TildFG"				,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"温度区分"}
						,{"CategoryCd"			,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"商品カテゴリCD"}
						,{"ShimeDate"			,"int"		,(int)11	,""		,(boolean)true	,"99"	,"締め日"}
						,{"ArrivalBaseFee"		,"int"		,(int)11	,""		,(boolean)true	,"0"	,"入荷基本料金"}
						,{"ArrivalSlipFee"		,"int"		,(int)11	,""		,(boolean)true	,"0"	,"入荷伝票基本料金"}
						,{"ArrivalUnitFee"		,"float"	,(int)0		,""		,(boolean)true	,"0"	,"入荷料単価"}
						,{"FeeUnit"				,"int"		,(int)11	,""		,(boolean)true	,"0"	,"課金単位"}
						,{"SummaryFg"			,"int"		,(int)11	,""		,(boolean)true	,"0"	,"集計区分"}
						,{"EntryDate"			,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"登録日"}
						,{"UpdateDate"			,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"更新日"}
						,{"EntryUser"			,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"登録者"}
						,{"UpdateUser"			,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"更新者"}
						};
		return Rt;
	}
	
	private static Object[][] WM0032WhFeeBaseMstOut_Definition(){
		Object[][] Rt	={
						 {"ClCd"				,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"荷主コード"}
						,{"WhCd"				,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"倉庫コード"}
						,{"ShipFeeCd"			,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"出荷料金コード"}
						,{"ShipFeeName"			,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"出荷料金名"}
						,{"DeliveryTypeCd01"	,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"運送タイプコード01"}
						,{"DeliveryTypeCd02"	,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"運送タイプコード02"}
						,{"DeliveryTypeCd03"	,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"運送タイプコード03"}
						,{"DeliveryTypeCd04"	,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"運送タイプコード04"}
						,{"DeliveryTypeCd05"	,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"運送タイプコード05"}
						,{"TildFG"				,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"温度区分"}
						,{"CategoryCd"			,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"商品カテゴリCD"}
						,{"ShimeDate"			,"int"		,(int)11	,""		,(boolean)true	,"99"	,"締め日"}
						,{"ShipBaseFee"			,"int"		,(int)11	,""		,(boolean)true	,"0"	,"出荷基本料金"}
						,{"ShipSlipFee"			,"int"		,(int)11	,""		,(boolean)true	,"0"	,"出荷伝票基本料金"}
						,{"ShipUnitFee"			,"float"	,(int)0		,""		,(boolean)true	,"0"	,"出荷料単価"}
						,{"FeeUnit"				,"int"		,(int)11	,""		,(boolean)true	,"0"	,"課金単位"}
						,{"SummaryFg"			,"int"		,(int)11	,""		,(boolean)true	,"0"	,"集計区分"}
						,{"EntryDate"			,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"登録日"}
						,{"UpdateDate"			,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"更新日"}
						,{"EntryUser"			,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"登録者"}
						,{"UpdateUser"			,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"更新者"}
						};
		return Rt;
	}
	
	private static Object[][] WM0033WhFeeBaseMstStock_Definition(){
		Object[][] Rt	={
						 {"ClCd"				,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"荷主コード"}
						,{"WhCd"				,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"倉庫コード"}
						,{"StockFeeCd"			,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"保管料金コード"}
						,{"StockFeeName"		,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"保管料金名"}
						,{"DeliveryTypeCd01"	,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"運送タイプコード01"}
						,{"DeliveryTypeCd02"	,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"運送タイプコード02"}
						,{"DeliveryTypeCd03"	,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"運送タイプコード03"}
						,{"DeliveryTypeCd04"	,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"運送タイプコード04"}
						,{"DeliveryTypeCd05"	,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"運送タイプコード05"}
						,{"TildFG"				,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"温度区分"}
						,{"CategoryCd"			,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"商品カテゴリCD"}
						,{"CuttingDate"			,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"期締め設定"}
						,{"ShimeDate"			,"int"		,(int)11	,""		,(boolean)true	,"99"	,"締め日"}
						,{"StockBaseFee"		,"int"		,(int)11	,""		,(boolean)true	,"0"	,"保管基本料金"}
						,{"StockUnitFee"		,"float"	,(int)0		,""		,(boolean)true	,"0"	,"保管料単価"}
						,{"FeeUnit"				,"int"		,(int)11	,""		,(boolean)true	,"0"	,"課金単位"}
						,{"SummaryFg"			,"int"		,(int)11	,""		,(boolean)true	,"0"	,"集計区分"}
						,{"EntryDate"			,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"登録日"}
						,{"UpdateDate"			,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"更新日"}
						,{"EntryUser"			,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"登録者"}
						,{"UpdateUser"			,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"更新者"}
						};
		return Rt;
	}
	
	private static Object[][] WM0034WhFeeBaseMstAdjust_Definition(){
		Object[][] Rt	={
						 {"ClCd"				,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"荷主コード"}
						,{"WhCd"				,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"倉庫コード"}
						,{"AdjustFeeCd"			,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"調整料金コード"}
						,{"AdjustFeeName"		,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"調整料金名"}
						,{"DeliveryTypeCd01"	,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"運送タイプコード01"}
						,{"DeliveryTypeCd02"	,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"運送タイプコード02"}
						,{"DeliveryTypeCd03"	,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"運送タイプコード03"}
						,{"DeliveryTypeCd04"	,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"運送タイプコード04"}
						,{"DeliveryTypeCd05"	,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"運送タイプコード05"}
						,{"TildFG"				,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"温度区分"}
						,{"CategoryCd"			,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"商品カテゴリCD"}
						,{"AdjustReasonCd"		,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"調整理由CD"}
						,{"ShimeDate"			,"int"		,(int)11	,""		,(boolean)true	,"99"	,"締め日"}
						,{"AdjustBaseFee"		,"int"		,(int)11	,""		,(boolean)true	,"0"	,"調整基本料金"}
						,{"AdjustUnitFee"		,"float"	,(int)0		,""		,(boolean)true	,"0"	,"調整料単価"}
						,{"FeeUnit"				,"int"		,(int)11	,""		,(boolean)true	,"0"	,"課金単位"}
						,{"SummaryFg"			,"int"		,(int)11	,""		,(boolean)true	,"0"	,"集計区分"}
						,{"EntryDate"			,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"登録日"}
						,{"UpdateDate"			,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"更新日"}
						,{"EntryUser"			,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"登録者"}
						,{"UpdateUser"			,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"更新者"}
						};
		return Rt;
	}
	
	private static Object[][] WW0010ArrivalPlanHd_Definition(){
		Object[][] Rt	={
						 {"ClWh"		,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"担当倉庫"}
 						,{"ClCd"		,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"荷主CD"}
						,{"ArrNo"		,"varchar"	,(int)50	,"KEY"	,(boolean)false	,""		,"入荷予定NO"}
						,{"ClArrNo"		,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"荷主予定番号"}
						,{"PlanDate"	,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"入荷予定日"}
						,{"ActualDate"	,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"入荷実績日"}
						,{"SpCd"		,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"仕入先CD"}
						,{"SpName01"	,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"仕入先名01"}
						,{"SpName02"	,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"仕入先名02"}
						,{"SpName03"	,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"仕入先名03"}
						,{"SpPost"		,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"仕入先郵便"}
						,{"SpAdd01"		,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"仕入先住所01"}
						,{"SpAdd02"		,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"仕入先住所02"}
						,{"SpAdd03"		,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"仕入先住所03"}
						,{"SpTel"		,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"仕入先電話"}
						,{"ArCom01"		,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"コメント1"}
						,{"ArCom02"		,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"コメント2"}
						,{"ArCom03"		,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"コメント3"}
						,{"EntryDate"	,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"登録日"}
						,{"UpdateDate"	,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"更新日"}
						,{"EntryUser"	,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"登録者"}
						,{"UpdateUser"	,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"更新者"}
						,{"FixFg"		,"int"		,(int)11	,""		,(boolean)true	,"0"	,"ステータス"}
						};
		return Rt;
	}
	
	private static Object[][] WW0011ArrivalPlanMs_Definition(){
		Object[][] Rt	={
						 {"ClWh"		,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"担当倉庫"}
						,{"ClCd"		,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"荷主CD"}
						,{"ArrNo"		,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"入荷予定NO"}
						,{"MsNo"		,"int"		,(int)11	,"KEY"	,(boolean)false	,"0"	,"明細番号"}
						,{"ItemCd"		,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"商品コード"}
						,{"ClItemCd"	,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"荷主商品コード"}
						,{"JanCd"		,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"JanCd（バラ）"}
						,{"ItemMdNo"	,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"商品型番"}
						,{"ItemName"	,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"商品名"}
						,{"lot"			,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"ロット"}
						,{"ExpDate"		,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"消費期限"}
						,{"PlanQty"		,"int"		,(int)11	,""		,(boolean)true	,"0"	,"予定数量"}
 						,{"ActualQty"	,"int"		,(int)11	,""		,(boolean)true	,"0"	,"実績数"}
						,{"ActualDate"	,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"入荷日"}
						,{"Com01"		,"varchar"	,(int)200	,""		,(boolean)true	,"NULL"	,"コメント1"}
						,{"Com02"		,"varchar"	,(int)200	,""		,(boolean)true	,"NULL"	,"コメント2"}
						,{"EntryDate"	,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"登録日"}
						,{"UpdateDate"	,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"更新日"}
						,{"EntryUser"	,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"登録者"}
						,{"UpdateUser"	,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"更新者"}
						};
		return Rt;
	}
	
	private static Object[][] WW0012ArrivalHd_Definition(){
		Object[][] Rt	={
						 {"ClWh"		,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"担当倉庫"}
						,{"ClCd"		,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"荷主CD"}
						,{"ArrNo"		,"varchar"	,(int)50	,"KEY"	,(boolean)false	,""		,"入荷予定NO"}
						,{"ArrCount"	,"int"		,(int)11	,"KEY"	,(boolean)false	,"0"	,"入荷予定枝番"}
						,{"ClArrNo"		,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"荷主予定番号"}
 						,{"PlanDate"	,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"入荷予定日"}
						,{"ActualDate"	,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"入荷実績日"}
						,{"SpCd"		,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"仕入先CD"}
						,{"SpName01"	,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"仕入先名01"}
						,{"SpName02"	,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"仕入先名02"}
						,{"SpName03"	,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"仕入先名03"}
						,{"SpPost"		,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"仕入先郵便"}
						,{"SpAdd01"		,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"仕入先住所01"}
						,{"SpAdd02"		,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"仕入先住所02"}
						,{"SpAdd03"		,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"仕入先住所03"}
						,{"SpTel"		,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"仕入先電話"}
						,{"ArCom01"		,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"コメント1"}
						,{"ArCom02"		,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"コメント2"}
						,{"ArCom03"		,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"コメント3"}
						,{"EntryDate"	,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"登録日"}
						,{"UpdateDate"	,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"更新日"}
						,{"EntryUser"	,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"登録者"}
						,{"UpdateUser"	,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"更新者"}
						};
		return Rt;
	}
	
	private static Object[][] WW0013ArrivaMs_Definition(){
		Object[][] Rt	={
						 {"ClWh"		,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"担当倉庫"}
						,{"ClCd"		,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"荷主CD"}
						,{"ArrNo"		,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"入荷予定NO"}
						,{"ArrCount"	,"int"		,(int)11	,"KEY"	,(boolean)false	,"0"	,"入荷予定枝番"}
						,{"MsNo"		,"int"		,(int)11	,"KEY"	,(boolean)false	,"0"	,"明細番号"}
						,{"MsSeq"		,"int"		,(int)11	,"KEY"	,(boolean)false	,"0"	,"明細Seq番号"}
						,{"ItemCd"		,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"商品コード"}
						,{"ClItemCd"	,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"荷主商品コード"}
						,{"JanCd"		,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"JanCd(バラ)"}
						,{"ItemMdNo"	,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"商品型番"}
						,{"ItemName"	,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"商品名"}
						,{"Lot"			,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"ロット"}
						,{"ExpDate"		,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"消費期限"}
						,{"PlanQty"		,"int"		,(int)11	,""		,(boolean)true	,"0"	,"予定数量"}
						,{"ActualQty"	,"int"		,(int)11	,""		,(boolean)true	,"0"	,"実績数"}
						,{"ActualDate"	,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"入荷日"}
						,{"Com01"		,"varchar"	,(int)200	,""		,(boolean)true	,"NULL"	,"コメント1"}
						,{"Com02"		,"varchar"	,(int)200	,""		,(boolean)true	,"NULL"	,"コメント2"}
						,{"EntryDate"	,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"登録日"}
						,{"UpdateDate"	,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"更新日"}
						,{"EntryUser"	,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"登録者"}
						,{"UpdateUser"	,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"更新者"}
						};
		return Rt;
	}
	
	private static Object[][] WW0015Stock_Definition(){
		Object[][] Rt	={
						 {"ClCd"		,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""					,"荷主コード"}
						,{"WhCd"		,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""					,"倉庫コード"}
						,{"Loc"			,"varchar"	,(int)15	,"KEY"	,(boolean)false	,""					,"ロケーション"}
						,{"ItemCd"		,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""					,"商品コード"}
						,{"Lot"			,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""					,"ロット"}
						,{"Expdate"		,"datetime"	,(int)0		,"KEY"	,(boolean)false	,""					,"消費期限"}
						,{"ActualDate"	,"datetime"	,(int)0		,"KEY"	,(boolean)false	,""					,"入荷実績日"}
						,{"Qty"			,"int"		,(int)11	,""		,(boolean)true	,"0"				,"数量"}
						,{"ShipPlanQty"	,"int"		,(int)11	,""		,(boolean)true	,"0"				,"引当済数"}
						,{"PossibleQty"	,"int"		,(int)11	,""		,(boolean)true	,"0"				,"出荷可能数"}
						,{"ItemName"	,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"				,"商品名"}
						,{"ClItemCd"	,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"				,"荷主商品コード"}
						,{"JanCd"		,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"				,"JanCd(バラ)"}
						,{"ItemMdNo"	,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"				,"商品型番"}
						,{"EntryDate"	,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"				,"登録日時"}
						,{"UpdateDate"	,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"				,"更新日時"}
						,{"EntryUser"	,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"				,"登録者"}
						,{"UpdateUser"	,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"				,"更新者"}
						};
		return Rt;
	}
	
	private static Object[][] WW0016StockAdjust_Definition(){
		Object[][] Rt	={
						 {"ClCd"				,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"荷主コード"}
						,{"WhCd"				,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"倉庫コード"}
						,{"AdjustNo"			,"int"		,(int)11	,"KEY"	,(boolean)false	,"0"	,"調整番号"}
						,{"AdjustReasonCd"		,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"調整理由コード"}
						,{"AdjustReasonName"	,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"調整理由名"}
						,{"Adjustdate"			,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"調整日"}
						,{"Loc"					,"varchar"	,(int)15	,""		,(boolean)true	,"NULL"	,"調整元ロケ"}
						,{"ItemCd"				,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"調整元商品CD"}
						,{"ItemName"			,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"調整元商品名"}
						,{"Lot"					,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"調整元ロット"}
						,{"ExpDate"				,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"調整元賞味期限"}
						,{"ActualDate"			,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"調整元入荷日"}
						,{"BeforeQty"			,"int"		,(int)11	,""		,(boolean)true	,"0"	,"調整元在庫数"}
						,{"ShipPlanQty"			,"int"		,(int)11	,""		,(boolean)true	,"0"	,"調整元引当済数"}
						,{"PossibleQty"			,"int"		,(int)11	,""		,(boolean)true	,"0"	,"調整元出荷可能数"}
						,{"AdjustQty"			,"int"		,(int)11	,""		,(boolean)true	,"0"	,"調整数"}
						,{"AdjustCom01"			,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"調整理由コメント01"}
						,{"AdjustCom02"			,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"調整理由コメント02"}
						,{"AdjustCom03"			,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"調整理由コメント03"}
						,{"AfterQty"			,"int"		,(int)11	,""		,(boolean)true	,"0"	,"調整後在庫数"}
						,{"EntryDate"			,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"登録日"}
						,{"UpdateDate"			,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"更新日"}
						,{"EntryUser"			,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"登録者"}
						,{"UpdateUser"			,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"更新者"}
						};
		return Rt;
	}
	
	private static Object[][] WW0020ShipPlovision_Definition(){
		Object[][] Rt	={
						 {"cl_cd"			,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"荷主コード"}
						,{"InvoiceWHCD"		,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"倉庫コード"}
						,{"OkuriNo"			,"int"		,(int)11	,"KEY"	,(boolean)false	,"0"	,"送り状番号"}
						,{"MsNo"			,"int"		,(int)11	,"KEY"	,(boolean)false	,"0"	,"明細番号"}
 						,{"Seq"				,"int"		,(int)11	,"KEY"	,(boolean)false	,"0"	,"引当枝番"}
						,{"OrderItemCd"		,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"商品コード"}
						,{"OrderItemName01"	,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"商品表記名"}
						,{"OrderLot"		,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"受注ロット指定"}
						,{"OrderExpDate"	,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"受注賞味期限指定"}
						,{"OrderQty"		,"int"		,(int)11	,""		,(boolean)true	,"0"	,"受注個数"}
						,{"ShipWhCd"		,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"倉庫コード"}
 						,{"ShipLoc"			,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"ロケーション"}
						,{"ShipItemCd"		,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"商品コード"}
						,{"ShipLot"			,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"ロット"}
						,{"ShipExpdate"		,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"消費期限"}
						,{"ShipActualDate"	,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"入荷実績日"}
						,{"ShipQty"			,"int"		,(int)11	,""		,(boolean)true	,"0"	,"引当数量"}
						,{"FixFg"			,"int"		,(int)11	,""		,(boolean)true	,"0"	,"引落済フラグ"}
						,{"PackingType"		,"int"		,(int)11	,""		,(boolean)true	,"0"	,"荷姿タイプ"}
						,{"PackingQty"		,"int"		,(int)11	,""		,(boolean)true	,"0"	,"荷姿数量"}
						,{"UnitName"		,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"荷姿単位"}
						,{"PackingUnitQty"	,"int"		,(int)11	,""		,(boolean)true	,"1"	,"荷姿単位のバラ入数"}
						,{"BRShipQty"		,"int"		,(int)11	,""		,(boolean)true	,"0"	,"バラ数量"}
						,{"CTShipQty"		,"int"		,(int)11	,""		,(boolean)true	,"0"	,"カートン数量"}
						,{"CSShipQty"		,"int"		,(int)11	,""		,(boolean)true	,"0"	,"ケース数量"}
						,{"PLShipQty"		,"int"		,(int)11	,""		,(boolean)true	,"0"	,"パレット数量"}
						,{"BRUnitName"		,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"バラ単位名"}
						,{"CTUnitName"		,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"カートン単位名"}
						,{"CSUnitName"		,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"ケース単位名"}
						,{"PLUnitName"		,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"パレット単位名"}
						,{"EntryDate"		,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"登録日時"}
						,{"UpdateDate"		,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"更新日時"}
						,{"EntryUser"		,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"登録者"}
						,{"UpdateUser"		,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"更新者"}
						};
		return Rt;
	}
	
	private static Object[][] WW0025BerthReservation_Definition(){
		Object[][] Rt	={
						 {"HaishaNo"		,"int"		,(int)11	,"KEY"	,(boolean)false	,""		,"配車番号"}
						,{"WhCd"			,"varchar"	,(int)20	,""		,(boolean)true	,""		,"倉庫コード"}
						,{"BerthCd"			,"varchar"	,(int)20	,""		,(boolean)true	,""		,"バースCD"}
						,{"ReserveDate"		,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"バース予約日"}
						,{"ReserveTimeStr"	,"int"		,(int)11	,""		,(boolean)true	,"0"	,"バース予約開始時刻"}
						,{"ReserveTimeEnd"	,"int"		,(int)11	,""		,(boolean)true	,"0"	,"バース予約終了時刻"}
						,{"EntryDate"		,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"登録日"}
						,{"UpdateDate"		,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"更新日"}
						,{"EntryUser"		,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"登録者"}
						,{"UpdateUser"		,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"更新者"}
						};
		return Rt;
	}
	
	private static Object[][] WW013101WhFeeInHd_Definition(){
		Object[][] Rt	={
						 {"ClCd"				,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"荷主コード"}
						,{"WhCd"				,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"倉庫コード"}
						,{"ArrivalFeeCd"		,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"入荷料金コード"}
						,{"ShimeDate"			,"datetime"	,(int)0		,"KEY"	,(boolean)false	,""		,"締め日"}
						,{"ArrivalFeeName"		,"varchar"	,(int)100	,""		,(boolean)true	,"NULL"	,"入荷料金名"}
						,{"DeliveryTypeCd01"	,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"運送タイプコード01"}
						,{"DeliveryTypeCd02"	,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"運送タイプコード02"}
						,{"DeliveryTypeCd03"	,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"運送タイプコード03"}
						,{"DeliveryTypeCd04"	,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"運送タイプコード04"}
						,{"DeliveryTypeCd05"	,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"運送タイプコード05"}
						,{"TildFG"				,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"温度区分"}
						,{"CategoryCd"			,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"商品カテゴリCD"}
						,{"FeeUnit"				,"int"		,(int)11	,""		,(boolean)true	,"0"	,"課金単位"}
						,{"SummaryFg"			,"int"		,(int)11	,""		,(boolean)true	,"0"	,"集計区分"}
						,{"ArrivalBaseFee"		,"int"		,(int)11	,""		,(boolean)true	,"0"	,"入荷基本料金"}
						,{"ArrivalSlipFee"		,"int"		,(int)11	,""		,(boolean)true	,"0"	,"入荷伝票基本料金"}
						,{"ArrivalSlipFeeTotal"	,"int"		,(int)11	,""		,(boolean)true	,"0"	,"入荷伝票基本料金合計"}
						,{"ArrivalUnitFee"		,"float"	,(int)0		,""		,(boolean)true	,"0"	,"入荷料単価"}
						,{"ArrivalQtyTotal"		,"int"		,(int)11	,""		,(boolean)true	,"0"	,"入荷数合計"}
						,{"ArrivalVolTotal"		,"float"	,(int)0		,""		,(boolean)true	,"0"	,"入荷量合計"}
						,{"ArrivalFeeTotal"		,"int"		,(int)11	,""		,(boolean)true	,"0"	,"入荷料合計"}
						,{"TaxFg"				,"int"		,(int)11	,""		,(boolean)true	,"0"	,"税区分"}
						,{"TaxRate"				,"int"		,(int)11	,""		,(boolean)true	,"0"	,"消費税率"}
						,{"ConsumptionTax"		,"int"		,(int)11	,""		,(boolean)true	,"0"	,"消費税"}
						,{"WithOutTaxTotal"		,"int"		,(int)11	,""		,(boolean)true	,"0"	,"税別合計金額"}
						,{"TotalFee"			,"int"		,(int)11	,""		,(boolean)true	,"0"	,"税込請求額合計"}
						,{"FeeFixFg"			,"int"		,(int)11	,""		,(boolean)true	,"0"	,"確定区分"}
						,{"FeeNo"				,"int"		,(int)11	,""		,(boolean)true	,"0"	,"請求番号"}
						,{"SlipCount"			,"int"		,(int)11	,""		,(boolean)true	,"0"	,"伝票枚数"}
						,{"EntryDate"			,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"登録日"}
						,{"UpdateDate"			,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"更新日"}
						,{"EntryUser"			,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"登録者"}
						,{"UpdateUser"			,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"更新者"}
						};
		return Rt;
	}
	
	private static Object[][] WW013102WhFeeInMs_Definition(){
		Object[][] Rt	={
						 {"ClCd"			,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"荷主コード"}
						,{"WhCd"			,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"倉庫コード"}
  						,{"ArrivalFeeCd"	,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"入荷料金コード"}
  						,{"ShimeDate"		,"datetime"	,(int)0		,"KEY"	,(boolean)false	,""		,"締め日"}
						,{"ArrNo"			,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"入荷予定NO"}
						,{"ArrCount"		,"int"		,(int)11	,"KEY"	,(boolean)false	,""		,"入荷予定枝番"}
						,{"MsSeq"			,"int"		,(int)11	,"KEY"	,(boolean)false	,""		,"明細Seq番号"}
						,{"ClGpCD"			,"varchar"	,(int)20	,""		,(boolean)true	,"NULL"	,"荷主グループ"}
						,{"ItemCd"			,"varchar"	,(int)20	,""		,(boolean)false	,""		,"商品コード"}
						,{"ClItemCd"		,"varchar"	,(int)20	,""		,(boolean)false	,""		,"荷主商品コード"}
						,{"JanCd"			,"varchar"	,(int)20	,""		,(boolean)false	,""		,"JanCd（バラ）"}
						,{"ItemMdNo"		,"varchar"	,(int)20	,""		,(boolean)false	,""		,"商品型番"}
						,{"ItemName"		,"varchar"	,(int)100	,""		,(boolean)false	,""		,"商品名"}
						,{"Lot"				,"varchar"	,(int)20	,""		,(boolean)false	,""		,"ロット"}
						,{"ExpDate"			,"datetime"	,(int)0		,""		,(boolean)false	,""		,"消費期限"}
						,{"PlanQty"			,"int"		,(int)11	,""		,(boolean)true	,"0"	,"予定数量"}
						,{"ActualQty"		,"int"		,(int)11	,""		,(boolean)true	,"0"	,"実績数"}
						,{"ActualDate"		,"datetime"	,(int)0		,""		,(boolean)false	,""		,"入荷日"}
						,{"Com01"			,"varchar"	,(int)200	,""		,(boolean)false	,""		,"コメント1"}
						,{"Com02"			,"varchar"	,(int)200	,""		,(boolean)false	,""		,"コメント2"}
						,{"ArrivalSlipFee"	,"int"		,(int)11	,""		,(boolean)false	,"0"	,"入荷伝票基本料金"}
						,{"ArrivalUnitFee"	,"float"	,(int)0		,""		,(boolean)false	,"0"	,"入荷料単価"}
						,{"ItemWeight"		,"float"	,(int)0		,""		,(boolean)false	,"0"	,"商品重量"}
						,{"ItemSize"		,"float"	,(int)0		,""		,(boolean)false	,"0"	,"商品サイズ"}
						,{"FeeUnit"			,"int"		,(int)11	,""		,(boolean)false	,"0"	,"課金単位"}
						,{"ArrivalQty"		,"int"		,(int)11	,""		,(boolean)false	,"0"	,"入荷数"}
						,{"ArrivalVol"		,"float"	,(int)0		,""		,(boolean)false	,"0"	,"入荷量"}
						,{"ArrivalFee"		,"int"		,(int)11	,""		,(boolean)false	,"0"	,"入荷料"}
						,{"ClArrNo"			,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"荷主予定番号"}
						,{"FeeNo"			,"int"		,(int)11	,""		,(boolean)false	,"0"	,"請求番号"}
						,{"EntryDate"		,"datetime"	,(int)0		,""		,(boolean)false	,""		,"登録日"}
						,{"UpdateDate"		,"datetime"	,(int)0		,""		,(boolean)false	,""		,"更新日"}
						,{"EntryUser"		,"varchar"	,(int)50	,""		,(boolean)false	,""		,"登録者"}
						,{"UpdateUser"		,"varchar"	,(int)50	,""		,(boolean)false	,""		,"更新者"}
						};
		return Rt;
	}
	
	private static Object[][] WW013201WhFeeOutHd_Definition(){
		Object[][] Rt	={
						 {"ClCd"				,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"荷主コード"}
						,{"WhCd"				,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"倉庫コード"}
						,{"ShipFeeCd"			,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"出荷料金コード"}
						,{"ShimeDate"			,"datetime"	,(int)0		,"KEY"	,(boolean)false	,""		,"締め日"}
						,{"ShipFeeName"			,"varchar"	,(int)100	,""		,(boolean)false	,""		,"出荷料金名"}
						,{"DeliveryTypeCd01"	,"varchar"	,(int)20	,""		,(boolean)false	,""		,"運送タイプコード01"}
						,{"DeliveryTypeCd02"	,"varchar"	,(int)20	,""		,(boolean)false	,""		,"運送タイプコード02"}
						,{"DeliveryTypeCd03"	,"varchar"	,(int)20	,""		,(boolean)false	,""		,"運送タイプコード03"}
						,{"DeliveryTypeCd04"	,"varchar"	,(int)20	,""		,(boolean)false	,""		,"運送タイプコード04"}
						,{"DeliveryTypeCd05"	,"varchar"	,(int)20	,""		,(boolean)false	,""		,"運送タイプコード05"}
						,{"TildFG"				,"varchar"	,(int)20	,""		,(boolean)false	,""		,"温度区分"}
						,{"CategoryCd"			,"varchar"	,(int)20	,""		,(boolean)false	,""		,"商品カテゴリCD"}
						,{"FeeUnit"				,"int"		,(int)11	,""		,(boolean)false	,"0"	,"課金単位"}
						,{"SummaryFg"			,"int"		,(int)11	,""		,(boolean)false	,"0"	,"集計区分"}
						,{"ShipBaseFee"			,"int"		,(int)11	,""		,(boolean)false	,"0"	,"出荷基本料金"}
						,{"ShipSlipFee"			,"int"		,(int)11	,""		,(boolean)false	,"0"	,"出荷伝票基本料金"}
						,{"ShipSlipFeeTotal"	,"int"		,(int)11	,""		,(boolean)false	,"0"	,"出荷伝票基本料金合計"}
						,{"ShipUnitFee"			,"float"	,(int)0		,""		,(boolean)false	,"0"	,"出荷料単価"}
						,{"ShipQtyTotal"		,"int"		,(int)11	,""		,(boolean)false	,"0"	,"出荷数合計"}
						,{"ShipVolTotal"		,"float"	,(int)0		,""		,(boolean)false	,"0"	,"出荷量合計"}
						,{"ShipFeeTotal"		,"int"		,(int)11	,""		,(boolean)false	,"0"	,"出荷料"}
						,{"TaxFg"				,"int"		,(int)11	,""		,(boolean)false	,"0"	,"税区分"}
						,{"TaxRate"				,"int"		,(int)11	,""		,(boolean)false	,"0"	,"税率"}
						,{"ConsumptionTax"		,"int"		,(int)11	,""		,(boolean)false	,"0"	,"消費税"}
						,{"WithOutTaxTotal"		,"int"		,(int)11	,""		,(boolean)false	,"0"	,"税別合計金額"}
						,{"TotalFee"			,"int"		,(int)11	,""		,(boolean)false	,"0"	,"税込請求額合計"}
						,{"FeeFixFg"			,"int"		,(int)11	,""		,(boolean)false	,"0"	,"確定区分"}
						,{"FeeNo"				,"int"		,(int)11	,""		,(boolean)false	,"0"	,"請求番号"}
						,{"SlipCount"			,"int"		,(int)11	,""		,(boolean)false	,"0"	,"伝票枚数"}
						,{"EntryDate"			,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"登録日"}
						,{"UpdateDate"			,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"更新日"}
						,{"EntryUser"			,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"登録者"}
						,{"UpdateUser"			,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"更新者"}
						};
		return Rt;
	}
	/*
	private static Object[][] WW013202WhFeeOutMs_Definition(){
		Object[][] Rt	={
						,{"ClCd` varchar(20) NOT NULL COMMENT '荷主コード',
						,{"WhCd` varchar(20) NOT NULL COMMENT '倉庫コード',
						,{"ShipFeeCd` varchar(20) NOT NULL COMMENT '出荷料金コード',
						,{"ShimeDate` datetime NOT NULL COMMENT '締め日',
						,{"OkuriNo` int(11) NOT NULL COMMENT '送り状番号',
						,{"MsNo` int(11) NOT NULL COMMENT '明細番号',
						,{"Seq` int(11) NOT NULL COMMENT '引当枝番',
						,{"ClGpCD` varchar(20) DEFAULT NULL,
						,{"OrderItemCd` varchar(20) DEFAULT NULL,
						,{"ClItemCd` varchar(20) DEFAULT NULL,
						,{"OrderItemName01` varchar(100) DEFAULT NULL,
						,{"OrderLot` varchar(20) DEFAULT NULL,
						,{"OrderExpDate` datetime DEFAULT NULL,
						,{"OrderQty` float NOT NULL DEFAULT '0',
						,{"ShipWhCd` varchar(20) DEFAULT NULL,
						,{"ShipLoc` varchar(15) DEFAULT NULL,
						,{"ShipItemCd` varchar(20) DEFAULT NULL,
						,{"ShipLot` varchar(20) DEFAULT NULL,
						,{"ShipExpdate` datetime DEFAULT NULL,
						,{"ShipActualDate` datetime DEFAULT NULL,
						,{"WmsShipDate` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
						,{"FixFg` int(11) NOT NULL DEFAULT '0',
						,{"PackingType` int(11) NOT NULL DEFAULT '0',
						,{"PackingQty` int(11) NOT NULL DEFAULT '0',
						,{"UnitName` varchar(20) DEFAULT NULL,
						,{"PackingUnitQty` int(11) NOT NULL DEFAULT '0',
						,{"BRShipQty` int(11) NOT NULL DEFAULT '0',
						,{"CTShipQty` int(11) NOT NULL DEFAULT '0',
						,{"CSShipQty` int(11) NOT NULL DEFAULT '0',
						,{"PLShipQty` int(11) NOT NULL DEFAULT '0',
						,{"BRUnitName` varchar(20) DEFAULT NULL,
						,{"CTUnitName` varchar(20) DEFAULT NULL,
						,{"CSUnitName` varchar(20) DEFAULT NULL,
						,{"PLUnitName` varchar(20) DEFAULT NULL,
						,{"ShipSlipFee` int(11) NOT NULL DEFAULT '0',
						,{"ShipUnitFee` float NOT NULL DEFAULT '0',
						,{"ItemWeight` float NOT NULL DEFAULT '0',
						,{"ItemSize` float NOT NULL DEFAULT '0',
						,{"FeeUnit` int(11) NOT NULL DEFAULT '0',
						,{"ShipQty` int(11) NOT NULL DEFAULT '0',
						,{"ShipVol` float NOT NULL DEFAULT '0',
						,{"ShipFee` int(11) NOT NULL DEFAULT '0',
						,{"EntryDate` datetime DEFAULT NULL,
						,{"UpdateDate` datetime DEFAULT NULL,
						,{"EntryUser` varchar(50) DEFAULT NULL,
						,{"UpdateUser` varchar(50) DEFAULT NULL,
						,{"ClDeliNo` varchar(50) DEFAULT NULL,
						,{"ClOrderNo` varchar(50) DEFAULT NULL,
						,{"NiokuriCd` varchar(20) DEFAULT NULL,
						,{"NiokuriDepartmentCd` varchar(20) DEFAULT NULL,
						,{"NiokuriName01` varchar(50) DEFAULT NULL,
						,{"NiokuriName02` varchar(50) DEFAULT NULL,
						,{"NiokuriName03` varchar(50) DEFAULT NULL,
						,{"NiokuriPost` varchar(20) DEFAULT NULL,
						,{"NiokuriAdd01` varchar(100) DEFAULT NULL,
						,{"NiokuriAdd02` varchar(100) DEFAULT NULL,
						,{"NiokuriAdd03` varchar(100) DEFAULT NULL,
						,{"NioKuriTel` varchar(20) DEFAULT NULL,
						,{"NioKuriFax` varchar(20) DEFAULT NULL,
						,{"NioKuriMail` varchar(200) DEFAULT NULL,
						,{"NiokuriMunicCd` varchar(20) DEFAULT NULL,
						,{"DeliCd` varchar(20) DEFAULT NULL,
						,{"ClDeliCd` varchar(20) DEFAULT NULL,
						,{"DeliDepartmentCd` varchar(20) DEFAULT NULL,
						,{"DeliName01` varchar(50) DEFAULT NULL,
						,{"DeliName02` varchar(50) DEFAULT NULL,
						,{"DeliName03` varchar(50) DEFAULT NULL,
						,{"DeliPost` varchar(20) DEFAULT NULL,
						,{"DeliAdd01` varchar(100) DEFAULT NULL,
						,{"DeliAdd02` varchar(100) DEFAULT NULL,
						,{"DeliAdd03` varchar(100) DEFAULT NULL,
						,{"DeliTel` varchar(20) DEFAULT NULL,
						,{"DeliFax` varchar(20) DEFAULT NULL,
						,{"DeliMail` varchar(200) DEFAULT NULL,
						,{"DeliMunicCd` varchar(20) DEFAULT NULL,
						,{"FeeNo` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ClCd`,`WhCd`,`ShipFeeCd`,`ShimeDate`,`OkuriNo`,`MsNo`,`Seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='';

						};
		return Rt;
	}
	private static Object[][] WW013301WhFeeStockHd_Definition(){
		Object[][] Rt	={
						
						};
		return Rt;
	}
	private static Object[][] WW013302WhFeeStockMs_Definition(){
		Object[][] Rt	={
						
						};
		return Rt;
	}
	private static Object[][] WW013401WhFeeAdjustHd_Definition(){
		Object[][] Rt	={
						
						};
		return Rt;
	}
	private static Object[][] WW013402WhFeeAdjustMs_Definition(){
		Object[][] Rt	={
						
						};
		return Rt;
	}
	private static Object[][] WW013501WhFeeOther_Definition(){
		Object[][] Rt	={
						
						};
		return Rt;
	}
	private static Object[][] WW014001WhFeeInvoice_Definition(){
		Object[][] Rt	={
						
						};
		return Rt;
	}
	*/
	private static Object[][] WW00630ItemRecomendLoc_Definition(){
		Object[][] Rt	={
						 {"ClCd"		,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"荷主コード"}
						,{"ClWh"		,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"担当倉庫コード"}
						,{"ItemCd"		,"varchar"	,(int)20	,"KEY"	,(boolean)false	,""		,"商品コード"}
						,{"RecomendLoc"	,"varchar"	,(int)20	,""		,(boolean)true	,""		,"推奨ロケ"}
						,{"EntryDate"	,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"データ登録日時"}
						,{"UpdateDate"	,"datetime"	,(int)0		,""		,(boolean)true	,"NULL"	,"データ更新日時"}
						,{"EntryUser"	,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"登録者コード"}
						,{"UpdateUser"	,"varchar"	,(int)50	,""		,(boolean)true	,"NULL"	,"更新者コード"}
						};
		return Rt;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//sql実行
	private static void KickSql(String TgtDB,String sql) {
		A100_DbConnect.DB_CONN(TgtDB);
		ResultSet rset01 = null;
		PreparedStatement CreateTablestmt = null;
		Statement stmt01 = null;
		try {
			CreateTablestmt = A100_DbConnect.conn.prepareStatement(sql);
			CreateTablestmt.executeUpdate();
			if(null!=CreateTablestmt) {CreateTablestmt.close();}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(null!=rset01){rset01.close();}
				if(null!=CreateTablestmt) {CreateTablestmt.close();}
				if(null!=stmt01){stmt01.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		A100_DbConnect.close();
	}
	
	private static String[] ColumnList(String TgtTable,String TgtDB) {
		//データベース・テーブルを指定してフィールド名一覧を返却する
		String[] ColumName=new String[0];
		A100_DbConnect.DB_CONN(TgtDB);
		ResultSet rset01 = null;
		PreparedStatement CreateTablestmt = null;
		Statement stmt01 = null;
		
		String MySqlDefaultSchema = "";
		if("WANKO".equals(TgtDB)) {
			MySqlDefaultSchema = A00000_Main.MySqlDefaultSchemaWANKO;
		}else if("POST".equals(TgtDB)){
			MySqlDefaultSchema = A00000_Main.MySqlDefaultSchemaPOST;
		}else if("NYANKO".equals(TgtDB)){
			MySqlDefaultSchema = A00000_Main.MySqlDefaultSchemaNYANKO;
		}else if("OLD".equals(TgtDB)) {
			MySqlDefaultSchema = A00000_Main.MySqlDefaultSchemaOLD;
			
		}else {
			
		}
		
		String sql = "SELECT COLUMN_NAME, DATA_TYPE,column_default,is_nullable\n"
				+ " FROM INFORMATION_SCHEMA.COLUMNS\n"
				+ " WHERE TABLE_SCHEMA = '"+MySqlDefaultSchema+"'\n"
				+ " AND TABLE_NAME = '"+TgtTable+"'";

		try {
			stmt01 = A100_DbConnect.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
				      ResultSet.CONCUR_UPDATABLE);
			rset01 = stmt01.executeQuery(sql);
			
			int counter = 0;
			rset01.beforeFirst();
			while (rset01.next()) {
				counter=counter+1;
			}
			
			ColumName=new String[counter];
			
			counter = 0;
			rset01.beforeFirst();
			while (rset01.next()) {
				
				ColumName[counter] = rset01.getString("COLUMN_NAME");
				counter=counter+1;
			}
			
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(null!=rset01){rset01.close();}
				if(null!=CreateTablestmt) {CreateTablestmt.close();}
				if(null!=stmt01){stmt01.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		A100_DbConnect.close();
		
		return ColumName;
	}
	
	private static String[] TabeleList(String TgtDB) {
		//データベースのテーブル一覧を返却する
		String[] TableName=new String[0];
		A100_DbConnect.DB_CONN(TgtDB);
		ResultSet rset01 = null;
		PreparedStatement CreateTablestmt = null;
		Statement stmt01 = null;
		
		String sql = "SELECT database()";
		
		try {
			stmt01 = A100_DbConnect.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
				      ResultSet.CONCUR_UPDATABLE);
			rset01 = stmt01.executeQuery(sql);
			int counter = 0;
			rset01.beforeFirst();
			while (rset01.next()) {
				counter=counter+1;
			}
			String[] DB_Name = new String[counter];
			counter = 0;
			rset01.beforeFirst();
			while (rset01.next()) {
				DB_Name[counter] = rset01.getString(1);
				counter=counter+1;
			}
			
			String MySqlDefaultSchema = MySqlDefaultSchema(TgtDB);
			
			
			if(0<DB_Name.length && MySqlDefaultSchema.equals(DB_Name[0])) {
				rset01 = null;
				stmt01 = null;
				sql = "show tables from "+ MySqlDefaultSchema;
				stmt01 = A100_DbConnect.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					      ResultSet.CONCUR_UPDATABLE);
				rset01 = stmt01.executeQuery(sql);
				counter = 0;
				rset01.beforeFirst();
				while (rset01.next()) {
					counter=counter+1;
				}
				
				TableName = new String[counter];
				counter = 0;
				rset01.beforeFirst();
				while (rset01.next()) {
					TableName[counter] = rset01.getString(1);
					counter=counter+1;
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(null!=rset01){rset01.close();}
				if(null!=CreateTablestmt) {CreateTablestmt.close();}
				if(null!=stmt01){stmt01.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		A100_DbConnect.close();
		return TableName;
	}
	
	private static String MySqlDefaultSchema(String TgtDB) {
		String MySqlDefaultSchema = A00000_Main.MySqlDefaultSchemaNYANKO;
		if("WANKO".equals(TgtDB)) {
			MySqlDefaultSchema = A00000_Main.MySqlDefaultSchemaWANKO;
		}else if("POST".equals(TgtDB)){
			MySqlDefaultSchema = A00000_Main.MySqlDefaultSchemaPOST;
		}else if("NYANKO".equals(TgtDB)){
			MySqlDefaultSchema = A00000_Main.MySqlDefaultSchemaNYANKO;
		}else if("OLD".equals(TgtDB)) {
			MySqlDefaultSchema = A00000_Main.MySqlDefaultSchemaOLD;
		}else {
			
		}
		return MySqlDefaultSchema;
	}
	
	
}