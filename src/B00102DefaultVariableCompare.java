public class B00102DefaultVariableCompare{
	//B00100DefaultVariableで定義した配列に存在しないものが比較投入されたらfalse返却するクラス
	/*
	コピペ用
	boolean WhListCheck					= B00102DefaultVariableCompare.WhListCheck(CheckString);				//引数文字列が倉庫リストに存在すればTrue
	boolean ClGpListCheck				= B00102DefaultVariableCompare.ClGpListCheck(CheckString);				//引数文字列が設定用荷主グループ一覧に存在すればTrue
	boolean ClListCheck					= B00102DefaultVariableCompare.ClListCheck(CheckString);				//引数文字列が設定用荷主一覧に存在すればTrue
	boolean ShippingCompanyListCheck	= B00102DefaultVariableCompare.ShippingCompanyListCheck(CheckString);	//引数文字列が設定用運送会社一覧に存在すればTrue
	
	boolean DeliveryType01Check			= B00102DefaultVariableCompare.DeliveryType01Check(CheckString);		//引数文字列が運送タイプ01に存在すればTrue
	boolean DeliveryType02Check			= B00102DefaultVariableCompare.DeliveryType02Check(CheckString);		//引数文字列が運送タイプ02に存在すればTrue
	boolean DeliveryType03Check			= B00102DefaultVariableCompare.DeliveryType03Check(CheckString);		//引数文字列が運送タイプ03に存在すればTrue
	boolean DeliveryType04Check			= B00102DefaultVariableCompare.DeliveryType04Check(CheckString);		//引数文字列が運送タイプ04に存在すればTrue
	boolean DeliveryType05Check			= B00102DefaultVariableCompare.DeliveryType05Check(CheckString);		//引数文字列が運送タイプ05に存在すればTrue
	
	boolean UnitTypeListCheck			= B00102DefaultVariableCompare.UnitTypeListCheck(CheckString);			//引数文字列が荷姿タイプに存在すればTrue
	boolean CODListCheck				= B00102DefaultVariableCompare.CODListCheck(CheckString);				//引数文字列が代引区分に存在すればTrue
	boolean TaxFgListCheck				= B00102DefaultVariableCompare.TaxFgListCheck(CheckString);				//引数文字列が外税内税区分に存在すればTrue
	boolean ChildrenFGListCheck			= B00102DefaultVariableCompare.ChildrenFGListCheck(CheckString);		//引数文字列が赤黒区分に存在すればTrue
	boolean ReceiptStampFGListCheck		= B00102DefaultVariableCompare.ReceiptStampFGListCheck(CheckString);	//引数文字列が受領印区分に存在すればTrue
	boolean TildFGCheck					= B00102DefaultVariableCompare.TildFGCheck(CheckString);				//引数文字列が温度区分　0:常温必須に存在すればTrue
	boolean FeeFixFgListCheck			= B00102DefaultVariableCompare.FeeFixFgListCheck(CheckString);			//引数文字列が金額確定フラグ設定値(請求)に存在すればTrue
	boolean PayFixFgListCheck			= B00102DefaultVariableCompare.PayFixFgListCheck(CheckString);			//引数文字列が金額確定フラグ設定値(支払)に存在すればTrue
	boolean PurposeListCheck			= B00102DefaultVariableCompare.PurposeListCheck(CheckString);			//引数文字列が送り状目的区分に存在すればTrue
	
	boolean HaisyaDataLayoutPtCheck		= B00102DefaultVariableCompare.HaisyaDataLayoutPtCheck(CheckString);	//引数文字列が運送会社向け配車データ出力パターンに存在すればTrue
	boolean LayoutPtCheck				= B00102DefaultVariableCompare.LayoutPtCheck(CheckString);				//引数文字列が荷主データ⇒送り状データ取り込みパターンに存在すればTrue
	boolean ShimeDateListCheck			= B00102DefaultVariableCompare.ShimeDateListCheck(CheckString);			//引数文字列が1日～28日　末日99のリストに存在すればTrue
	boolean DeliFeeNormCheck			= B00102DefaultVariableCompare.DeliFeeNormCheck(CheckString);			//引数文字列が運賃請求基準　発請求/着請求に存在すればTrue
	
	boolean StatusListCheck				= B00102DefaultVariableCompare.StatusListCheck(CheckString);			//引数文字列が配送状況に存在すればTrue
	boolean WmsStatusListCheck			= B00102DefaultVariableCompare.WmsStatusListCheck(CheckString);			//引数文字列が倉庫状況に存在すればTrue
	boolean ArryvalFixFgListCheck		= B00102DefaultVariableCompare.ArryvalFixFgListCheck(CheckString);		//引数文字列が入荷状況リストに存在すればTrue
	boolean InvoiceStatusListCheck		= B00102DefaultVariableCompare.InvoiceStatusListCheck(CheckString);		//引数文字列が請求状況に存在すればTrue
	boolean CautionTimingCheck			= B00102DefaultVariableCompare.CautionTimingCheck(CheckString);			//引数文字列が注意事項タイミングに存在すればTrue
	
	boolean DelListCheck				= B00102DefaultVariableCompare.DelListCheck(CheckString);				//引数文字列が設定用削除区分に存在すればTrue
	boolean MstPriorityFirstFgCheck		= B00102DefaultVariableCompare.MstPriorityFirstFgCheck(CheckString);	//引数文字列が届先マスタ情報優先区分に存在すればTrue
	boolean AuthorityFGCheck			= B00102DefaultVariableCompare.AuthorityFGCheck(CheckString);			//引数文字列が設定用ユーザー権限区分に存在すればTrue
	boolean LocTypeCheck				= B00102DefaultVariableCompare.LocTypeCheck(CheckString);				//引数文字列がロケタイプ設定値{表示用,CD,名称,引当可能FG※引当対象なら1}に存在すればTrue
	*/
	
	
	
	public static boolean HaisyaDataLayoutPtCheck(String CheckString){				//運送会社向け配車データ出力パターン
		boolean rt = false;
		if(null!=CheckString && !"".equals(CheckString)){
			for(int i=0;i<B00100DefaultVariable.HaisyaDataLayoutPt[1].length;i++){
				if(CheckString.equals(B00100DefaultVariable.HaisyaDataLayoutPt[1][i])){
					rt = true;
				}
			}
		}
		return rt;
	}
	public static boolean LayoutPtCheck(String CheckString){						//荷主データ⇒送り状データ取り込みパターン
		boolean rt = false;
		if(null!=CheckString && !"".equals(CheckString)){
			for(int i=0;i<B00100DefaultVariable.LayoutPt[1].length;i++){
				if(CheckString.equals(B00100DefaultVariable.LayoutPt[1][i])){
					rt = true;
				}
			}
		}
		return rt;
	}
	
	public static boolean ShimeDateListCheck(String CheckString){					//1日～28日　末日99のリスト
		boolean rt = false;
		if(null!=CheckString && !"".equals(CheckString)){
			for(int i=0;i<B00100DefaultVariable.ShimeDateList[1].length;i++){
				if(CheckString.equals(B00100DefaultVariable.ShimeDateList[1][i])){
					rt = true;
				}
			}
		}
		return rt;
	}
	public static boolean DeliFeeNormCheck(String CheckString){						//運賃請求基準　発請求/着請求
		boolean rt = false;
		if(null!=CheckString && !"".equals(CheckString)){
			for(int i=0;i<B00100DefaultVariable.DeliFeeNorm[1].length;i++){
				if(CheckString.equals(B00100DefaultVariable.DeliFeeNorm[1][i])){
					rt = true;
				}
			}
		}
		return rt;
	}

	public static boolean WhListCheck(String CheckString){							//倉庫リスト
		boolean rt = false;
		if(null!=CheckString && !"".equals(CheckString)){
			for(int i=0;i<B00100DefaultVariable.WhList[1].length;i++){
				if(CheckString.equals(B00100DefaultVariable.WhList[1][i])){
					rt = true;
				}
			}
		}
		return rt;
	}
	

	public static boolean ClGpListCheck(String CheckString){						//設定用荷主グループ一覧
		boolean rt = false;
		if(null!=CheckString && !"".equals(CheckString)){
			for(int i=0;i<B00100DefaultVariable.ClGpList[1].length;i++){
				if(CheckString.equals(B00100DefaultVariable.ClGpList[1][i])){
					rt = true;
				}
			}
		}
		return rt;
	}
	

	public static boolean ClListCheck(String CheckString){							//設定用荷主一覧
		boolean rt = false;
		if(null!=CheckString && !"".equals(CheckString)){
			for(int i=0;i<B00100DefaultVariable.ClList[1].length;i++){
				if(CheckString.equals(B00100DefaultVariable.ClList[1][i])){
					rt = true;
				}
			}
		}
		return rt;
	}
	

	public static boolean ShippingCompanyListCheck(String CheckString){				//設定用運送会社一覧
		boolean rt = false;
		if(null!=CheckString && !"".equals(CheckString)){
			for(int i=0;i<B00100DefaultVariable.ShippingCompanyList[1].length;i++){
				if(CheckString.equals(B00100DefaultVariable.ShippingCompanyList[1][i])){
					rt = true;
				}
			}
		}
		return rt;
	}
	
	public static boolean DeliveryType01Check(String CheckString){
		boolean rt = false;
		if(null!=CheckString && !"".equals(CheckString)){
			for(int i=0;i<B00100DefaultVariable.DeliveryType01[1].length;i++){
				if(CheckString.equals(B00100DefaultVariable.DeliveryType01[1][i])){
					rt = true;
				}
			}
		}
		return rt;
	}
	
	public static boolean DeliveryType02Check(String CheckString){
		boolean rt = false;
		if(null!=CheckString && !"".equals(CheckString)){
			for(int i=0;i<B00100DefaultVariable.DeliveryType02[1].length;i++){
				if(CheckString.equals(B00100DefaultVariable.DeliveryType02[1][i])){
					rt = true;
				}
			}
		}
		return rt;
	}
	
	public static boolean DeliveryType03Check(String CheckString){
		boolean rt = false;
		if(null!=CheckString && !"".equals(CheckString)){
			for(int i=0;i<B00100DefaultVariable.DeliveryType03[1].length;i++){
				if(CheckString.equals(B00100DefaultVariable.DeliveryType03[1][i])){
					rt = true;
				}
			}
		}
		return rt;
	}
	
	public static boolean DeliveryType04Check(String CheckString){
		boolean rt = false;
		if(null!=CheckString && !"".equals(CheckString)){
			for(int i=0;i<B00100DefaultVariable.DeliveryType04[1].length;i++){
				if(CheckString.equals(B00100DefaultVariable.DeliveryType04[1][i])){
					rt = true;
				}
			}
		}
		return rt;
	}
	
	public static boolean DeliveryType05Check(String CheckString){
		boolean rt = false;
		if(null!=CheckString && !"".equals(CheckString)){
			for(int i=0;i<B00100DefaultVariable.DeliveryType05[1].length;i++){
				if(CheckString.equals(B00100DefaultVariable.DeliveryType05[1][i])){
					rt = true;
				}
			}
		}
		return rt;
	}
	
	public static boolean DelListCheck(String CheckString){							//設定用削除区分
		boolean rt = false;
		if(null!=CheckString && !"".equals(CheckString)){
			for(int i=0;i<B00100DefaultVariable.DelList[1].length;i++){
				if(CheckString.equals(B00100DefaultVariable.DelList[1][i])){
					rt = true;
				}
			}
		}
		return rt;
	}
	
	public static boolean MstPriorityFirstFgCheck(String CheckString){
		boolean rt = false;
		if(null!=CheckString && !"".equals(CheckString)){
			for(int i=0;i<B00100DefaultVariable.MstPriorityFirstFg[1].length;i++){
				if(CheckString.equals(B00100DefaultVariable.MstPriorityFirstFg[1][i])){
					rt = true;
				}
			}
		}
		return rt;
	}
	
	public static boolean AuthorityFGCheck(String CheckString){						//設定用ユーザー権限区分
		boolean rt = false;
		if(null!=CheckString && !"".equals(CheckString)){
			for(int i=0;i<B00100DefaultVariable.AuthorityFG[1].length;i++){
				if(CheckString.equals(B00100DefaultVariable.AuthorityFG[1][i])){
					rt = true;
				}
			}
		}
		return rt;
	}
	
	public static boolean LocTypeCheck(String CheckString){							//ロケタイプ設定値{表示用,CD,名称,引当可能FG※引当対象なら1}
		boolean rt = false;
		if(null!=CheckString && !"".equals(CheckString)){
			for(int i=0;i<B00100DefaultVariable.LocType[1].length;i++){
				if(CheckString.equals(B00100DefaultVariable.LocType[1][i])){
					rt = true;
				}
			}
		}
		return rt;
	}

	public static boolean StatusListCheck(String CheckString){						//状況
		boolean rt = false;
		if(null!=CheckString && !"".equals(CheckString)){
			for(int i=0;i<B00100DefaultVariable.StatusList[1].length;i++){
				if(CheckString.equals(B00100DefaultVariable.StatusList[1][i])){
					rt = true;
				}
			}
		}
		return rt;
	}
	
	public static boolean WmsStatusListCheck(String CheckString){					//倉庫状況
		boolean rt = false;
		if(null!=CheckString && !"".equals(CheckString)){
			for(int i=0;i<B00100DefaultVariable.WmsStatusList[1].length;i++){
				if(CheckString.equals(B00100DefaultVariable.WmsStatusList[1][i])){
					rt = true;
				}
			}
		}
		return rt;
	}
	
	public static boolean ArryvalFixFgListCheck(String CheckString){				//入荷状況リスト
		boolean rt = false;
		if(null!=CheckString && !"".equals(CheckString)){
			for(int i=0;i<B00100DefaultVariable.ArryvalFixFgList[1].length;i++){
				if(CheckString.equals(B00100DefaultVariable.ArryvalFixFgList[1][i])){
					rt = true;
				}
			}
		}
		return rt;
	}
	
	public static boolean CautionTimingCheck(String CheckString){					//注意事項タイミング
		boolean rt = false;
		if(null!=CheckString && !"".equals(CheckString)){
			for(int i=0;i<B00100DefaultVariable.CautionTiming[1].length;i++){
				if(CheckString.equals(B00100DefaultVariable.CautionTiming[1][i])){
					rt = true;
				}
			}
		}
		return rt;
	}
	
	public static boolean ChildrenFGListCheck(String CheckString){					//赤黒区分
		boolean rt = false;
		if(null!=CheckString && !"".equals(CheckString)){
			for(int i=0;i<B00100DefaultVariable.ChildrenFGList[1].length;i++){
				if(CheckString.equals(B00100DefaultVariable.ChildrenFGList[1][i])){
					rt = true;
				}
			}
		}
		return rt;
	}
	
	public static boolean TildFGCheck(String CheckString){							//0:常温必須
		boolean rt = false;
		if(null!=CheckString && !"".equals(CheckString)){
			for(int i=0;i<B00100DefaultVariable.TildFG[1].length;i++){
				if(CheckString.equals(B00100DefaultVariable.TildFG[1][i])){
					rt = true;
				}
			}
		}
		return rt;
	}
	
	public static boolean ReceiptStampFGListCheck(String CheckString){				//受領印区分
		boolean rt = false;
		if(null!=CheckString && !"".equals(CheckString)){
			for(int i=0;i<B00100DefaultVariable.ReceiptStampFGList[1].length;i++){
				if(CheckString.equals(B00100DefaultVariable.ReceiptStampFGList[1][i])){
					rt = true;
				}
			}
		}
		return rt;
	}
	
	public static boolean InvoiceStatusListCheck(String CheckString){
		boolean rt = false;
		if(null!=CheckString && !"".equals(CheckString)){
			for(int i=0;i<B00100DefaultVariable.InvoiceStatusList[1].length;i++){
				if(CheckString.equals(B00100DefaultVariable.InvoiceStatusList[1][i])){
					rt = true;
				}
			}
		}
		return rt;
	}
	
	public static boolean FeeFixFgListCheck(String CheckString){					//金額確定フラグ設定値(請求)
		boolean rt = false;
		if(null!=CheckString && !"".equals(CheckString)){
			for(int i=0;i<B00100DefaultVariable.FeeFixFgList[1].length;i++){
				if(CheckString.equals(B00100DefaultVariable.FeeFixFgList[1][i])){
					rt = true;
				}
			}
		}
		return rt;
	}
	
	public static boolean PayFixFgListCheck(String CheckString){					//金額確定フラグ設定値(支払)
		boolean rt = false;
		if(null!=CheckString && !"".equals(CheckString)){
			for(int i=0;i<B00100DefaultVariable.PayFixFgList[1].length;i++){
				if(CheckString.equals(B00100DefaultVariable.PayFixFgList[1][i])){
					rt = true;
				}
			}
		}
		return rt;
	}
	
	public static boolean CODListCheck(String CheckString){							//代引区分
		boolean rt = false;
		if(null!=CheckString && !"".equals(CheckString)){
			for(int i=0;i<B00100DefaultVariable.CODList[1].length;i++){
				if(CheckString.equals(B00100DefaultVariable.CODList[1][i])){
					rt = true;
				}
			}
		}
		return rt;
	}
	
	public static boolean TaxFgListCheck(String CheckString){						//外税内税区分
		boolean rt = false;
		if(null!=CheckString && !"".equals(CheckString)){
			for(int i=0;i<B00100DefaultVariable.TaxFgList[1].length;i++){
				if(CheckString.equals(B00100DefaultVariable.TaxFgList[1][i])){
					rt = true;
				}
			}
		}
		return rt;
	}
	
	public static boolean UnitTypeListCheck(String CheckString){
		boolean rt = false;
		if(null!=CheckString && !"".equals(CheckString)){
			for(int i=0;i<B00100DefaultVariable.UnitTypeList[1].length;i++){
				if(CheckString.equals(B00100DefaultVariable.UnitTypeList[1][i])){
					rt = true;
				}
			}
		}
		return rt;
	}
	
	public static boolean PurposeListCheck(String CheckString){						//送り状目的区分
		boolean rt = false;
		if(null!=CheckString && !"".equals(CheckString)){
			for(int i=0;i<B00100DefaultVariable.PurposeList[1].length;i++){
				if(CheckString.equals(B00100DefaultVariable.PurposeList[1][i])){
					rt = true;
				}
			}
		}
		return rt;
	}
}