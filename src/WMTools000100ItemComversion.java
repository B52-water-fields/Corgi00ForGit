import java.util.ArrayList;

public class WMTools000100ItemComversion{
	//荷主コードと荷主商品コードを受け取って、商品マスタ荷主商品コードもしくは商品変換マスタを基に商品変換
	//商品商品マスタ変換結果を返却する
	
	/*
	コピペ用
	String ClCd	= A00000Main.ClCd; 
	ArrayList<String> ClItemCd=new ArrayList<String>();
	Object[][] ItemComversion = WMTools000100ItemComversion.ItemComversion(ClCd,ClItemCd);
	
	String GetClCd						= (String)ItemComversion[i][WMTools000100ItemComversion.IColClCd];						//荷主CD
	String GetClGpCd					= (String)ItemComversion[i][WMTools000100ItemComversion.IColClGpCd];					//荷主グループCD
	String GetItemCd					= (String)ItemComversion[i][WMTools000100ItemComversion.IColItemCd];					//変換後商品CD
	String GetClIemCd					= (String)ItemComversion[i][WMTools000100ItemComversion.IColClIemCd];					//荷主商品CD
	String GetPackingType				= (int)ItemComversion[i][WMTools000100ItemComversion.IColPackingType];					//荷姿タイプ
	String GetPackingQty				= (int)ItemComversion[i][WMTools000100ItemComversion.IColPackingQty];					//荷姿入数(バラ換算数)
	String GetItemName01				= (String)ItemComversion[i][WMTools000100ItemComversion.IColItemName01];				//商品名
	String GetItemPackingTypeName		= (String)ItemComversion[i][WMTools000100ItemComversion.IColItemPackingTypeName];		//荷姿商品名
	String GetItemUnitName				= (String)ItemComversion[i][WMTools000100ItemComversion.IColItemUnitName];				//商品単位(バラ)
	String GetItemPackingTypeUnitName	= (String)ItemComversion[i][WMTools000100ItemComversion.IColItemPackingTypeUnitName];	//商品単位(荷姿単位)
	*/
	
	static final int ColClCd						=0;	//荷主CD
	static final int ColClGpCd						=1;	//荷主グループCD
	static final int ColItemCd						=2;	//変換後商品CD
	static final int ColClIemCd					=3;	//荷主商品CD
	static final int ColPackingType				=4;	//荷姿タイプ(0：バラ	1:カートン　2：ケース　3：パレット)
	static final int ColPackingQty				=5;	//荷姿入数(バラ換算数)
	static final int ColItemName01				=6;	//商品名
	static final int ColPackingTypeItemName		=7;	//荷姿商品名
	static final int ColItemUnitName				=8;	//商品単位(バラ)
	static final int ColPackingTypeItemUnitName	=9;	//商品単位(荷姿単位)
	
	public static Object[][] RtItemComversion() {
		Object[][] RtSettingUserMstRt = {
				 {"ClCd"					,ColClCd						,"String"	,"荷主CD"}
				,{"ClGpCd"					,ColClGpCd						,"String"	,"荷主グループCD"}
				,{"ItemCd"					,ColItemCd						,"String"	,"変換後商品CD"}
				,{"ClIemCd"					,ColClIemCd					,"String"	,"荷主商品CD"}
				,{"PackingType"				,ColPackingType				,"int"		,"荷姿タイプ"}
				,{"PackingQty"				,ColPackingQty				,"int"		,"荷姿入数(バラ換算数)"}
				,{"ItemName01"				,ColItemName01				,"String"	,"商品名"}
				,{"ItemPackingTypeName"		,ColPackingTypeItemName		,"String"	,"荷姿商品名"}
				,{"ItemUnitName"			,ColItemUnitName				,"String"	,"商品単位(バラ)"}
				,{"ItemPackingTypeUnitName"	,ColPackingTypeItemUnitName	,"String"	,"商品単位(荷姿単位)"}
				};
		
		return RtSettingUserMstRt;
	}
	
	public static Object[][] ItemComversion(String ClCd,ArrayList<String> ClItemCd){
		ClItemCd	= B00150ArrayListControl.ArryListStringUniqueList(ClItemCd);
		
		if(null==ClCd) {ClCd="";}
		if("".equals(ClCd)) {ClCd=A00000Main.ClCd;}
		
		String ClGp	= ClgpGet(ClCd);
		
		Object[][] rt = new Object[0][RtItemComversion().length];
		if(null!=ClItemCd && 0<ClItemCd.size() && !"".equals(ClGp)) {
			rt = new Object[ClItemCd.size()][RtItemComversion().length];
			for(int i=0;i<ClItemCd.size();i++) {
				rt[i][ColClCd]							= ClCd;
				rt[i][ColClGpCd]						= ClGp;
				rt[i][ColItemCd]						= "";	//変換後商品CD
				rt[i][ColClIemCd]						= ClItemCd.get(i);
				rt[i][ColPackingType]					= 0;	//荷姿タイプ
				rt[i][ColPackingQty]					= 1;	//荷姿入数(バラ換算数)
				rt[i][ColItemName01]					= "";	//商品名
				rt[i][ColPackingTypeItemName]		= "";	//荷姿商品名
				rt[i][ColItemUnitName]				= "";	//商品単位(バラ)
				rt[i][ColPackingTypeItemUnitName]	= "";	//商品単位(荷姿単位)
			}
			rt = FromItemMst(ClGp,rt);
			rt = FromItemComversion(ClGp,ClCd,rt);
		}
		return rt;
	}
	
	private static Object[][] FromItemMst(String ClGp,Object[][] rt){
		//商品マスタの商品CDから商品CD特定
		ArrayList<String> SearchClGpCd 				= new ArrayList<String>();	//荷主グループコード
		ArrayList<String> SearchItemCd 				= new ArrayList<String>();	//商品コード
		ArrayList<String> SearchCLItemCd 			= new ArrayList<String>();	//荷主商品コード
		ArrayList<String> SearchItemName 			= new ArrayList<String>();	//商品名
		ArrayList<String> SearchDeliveryTypeCd01 	= new ArrayList<String>();	//運送タイプコード01
		ArrayList<String> SearchDeliveryTypeCd02 	= new ArrayList<String>();	//運送タイプコード02
		ArrayList<String> SearchDeliveryTypeCd03 	= new ArrayList<String>();	//運送タイプコード03
		ArrayList<String> SearchDeliveryTypeCd04 	= new ArrayList<String>();	//運送タイプコード04
		ArrayList<String> SearchDeliveryTypeCd05 	= new ArrayList<String>();	//運送タイプコード05
		ArrayList<String> SearchItemMDNo 			= new ArrayList<String>();	//商品モデル番号（型番）
		ArrayList<String> SearchCategoryCd 			= new ArrayList<String>();	//商品カテゴリCD
		ArrayList<String> SearchCategoryName 		= new ArrayList<String>();	//商品カテゴリ名
		ArrayList<String> SearchItemColorCd 		= new ArrayList<String>();	//商品カラーコード
		ArrayList<String> SearchItemColorName 		= new ArrayList<String>();	//商品カラー名
		ArrayList<String> SearchItemSizeCd 			= new ArrayList<String>();	//商品サイズコード
		ArrayList<String> SearchItemSizeName 		= new ArrayList<String>();	//商品サイズ名
		ArrayList<String> SearchJanCd 				= new ArrayList<String>();	//JANCD
		ArrayList<String> SearchTildFG 				= new ArrayList<String>();	//温度区分
		ArrayList<String> SearchTildName 			= new ArrayList<String>();	//温度区分名
		ArrayList<String> SearchDelFg 				= new ArrayList<String>();	//削除フラグ
		boolean AllSearch = false;
		
		SearchClGpCd.add(ClGp);
		for(int i=0;i<rt.length;i++) {
			String CheckClItemCd = (String)rt[i][ColClIemCd];
			if(null==rt[i][ColItemCd]||"".equals(rt[i][ColItemCd])) {
				SearchCLItemCd.add(CheckClItemCd);
			}
		}
		Object[][] ItemMstRt = M00070ItemMstRt.ItemMstRt(
				SearchClGpCd,			//荷主グループコード
				SearchItemCd,			//商品コード
				SearchCLItemCd,			//荷主商品コード
				SearchItemName,			//商品名
				SearchDeliveryTypeCd01,	//運送タイプコード01
				SearchDeliveryTypeCd02,	//運送タイプコード02
				SearchDeliveryTypeCd03,	//運送タイプコード03
				SearchDeliveryTypeCd04,	//運送タイプコード04
				SearchDeliveryTypeCd05,	//運送タイプコード05
				SearchItemMDNo,			//商品モデル番号（型番）
				SearchCategoryCd,		//商品カテゴリCD
				SearchCategoryName,		//商品カテゴリ名
				SearchItemColorCd,		//商品カラーコード
				SearchItemColorName,	//商品カラー名
				SearchItemSizeCd,		//商品サイズコード
				SearchItemSizeName,		//商品サイズ名
				SearchJanCd,			//JANCD
				SearchTildFG,			//温度区分
				SearchTildName,			//温度区分名
				SearchDelFg,			//削除フラグ
				AllSearch);
		for(int i=0;i<rt.length;i++) {
			String CheckClItemCd = (String)rt[i][ColClIemCd];
			if(null==rt[i][ColItemCd]||"".equals(rt[i][ColItemCd])) {
				for(int i01=0;i01<ItemMstRt.length;i01++) {
					String GetCLItemCd 		= (String)ItemMstRt[i01][M00070ItemMstRt.ColCLItemCd];
					String GetItemCd		= (String)ItemMstRt[i01][M00070ItemMstRt.ColItemCd];			//商品コード
					String GetUnitName		= (String)ItemMstRt[i01][M00070ItemMstRt.ColUnitName];			//商品単位
					String GetItemName01	= (String)ItemMstRt[i01][M00070ItemMstRt.ColItemName01];		//商品名1
					if(CheckClItemCd.equals(GetCLItemCd)){
						rt[i][ColItemCd]						= GetItemCd;		//変換後商品CD
						rt[i][ColPackingType]					= 0;				//荷姿タイプ
						rt[i][ColPackingQty]					= 1;				//荷姿入数(バラ換算数)
						rt[i][ColItemName01]					= GetItemName01;	//商品名
						rt[i][ColPackingTypeItemName]		= GetItemName01;	//荷姿商品名
						rt[i][ColItemUnitName]				= GetUnitName;		//商品単位(バラ)
						rt[i][ColPackingTypeItemUnitName]	= GetUnitName;		//商品単位(荷姿単位)
					}
				}
			}
		}
		return rt;
	}
	private static Object[][] FromItemComversion(String ClGp,String ClCd,Object[][] rt){
		ArrayList<String> SearchClGpCd = new ArrayList<String>();		//荷主グループコード
		ArrayList<String> SearchClCd = new ArrayList<String>();			//荷主コード
		ArrayList<String> SearchItemCd = new ArrayList<String>();		//商品コード
		ArrayList<String> SearchCLItemCd = new ArrayList<String>();		//荷主商品コード
		ArrayList<String> SearchItemName = new ArrayList<String>();		//商品名
		boolean AllSearch = false;
		SearchClGpCd.add(ClGp);
		SearchClCd.add(ClCd);
		for(int i=0;i<rt.length;i++) {
			String CheckClItemCd = (String)rt[i][ColClIemCd];
			if(null==rt[i][ColItemCd]||"".equals(rt[i][ColItemCd])) {
				SearchCLItemCd.add(CheckClItemCd);
			}
		}
		Object[][] ItemComversionMstRt = M00080ItemComversionMstRt.ItemComversionMstRt(
				SearchClGpCd,			//荷主グループコード
				SearchClCd,				//荷主コード
				SearchItemCd,			//商品コード
				SearchCLItemCd,			//荷主商品コード
				SearchItemName,			//商品名
				AllSearch);
		for(int i=0;i<rt.length;i++) {
			String CheckClItemCd = (String)rt[i][ColClIemCd];
			if(null==rt[i][ColItemCd]||"".equals(rt[i][ColItemCd])) {				
				for(int i01=0;i01<ItemComversionMstRt.length;i01++) {
					String GetClItemCd		= (String)ItemComversionMstRt[i01][M00080ItemComversionMstRt.ColClItemCd];	//荷主商品コード
					if(CheckClItemCd.equals(GetClItemCd)){
						String GetItemCd		= (String)ItemComversionMstRt[i01][M00080ItemComversionMstRt.ColItemCd];		//変換先商品コード
						int GetPackingType		= (int)ItemComversionMstRt[i01][M00080ItemComversionMstRt.ColPackingType];	//荷姿タイプ
						String GetItemName01	= (String)ItemComversionMstRt[i01][M00080ItemComversionMstRt.ColItemName01];	//商品名1
						String GetCtName		= (String)ItemComversionMstRt[i01][M00080ItemComversionMstRt.ColCtName];		//カートン商品名称
						String GetCsName		= (String)ItemComversionMstRt[i01][M00080ItemComversionMstRt.ColCsName];		//ケース商品名称
						String GetPlName		= (String)ItemComversionMstRt[i01][M00080ItemComversionMstRt.ColPlName];		//パレット商品名称
						int GetCtQty			= (int)ItemComversionMstRt[i01][M00080ItemComversionMstRt.ColCtQty];			//カートン入数
						int GetCsQty			= (int)ItemComversionMstRt[i01][M00080ItemComversionMstRt.ColCsQty];			//ケース入数
						int GetPlQty			= (int)ItemComversionMstRt[i01][M00080ItemComversionMstRt.ColPlQty];			//パレット入数
						String GetUnitName		= (String)ItemComversionMstRt[i01][M00080ItemComversionMstRt.ColUnitName];		//商品単位
						String GetCtUnitName	= (String)ItemComversionMstRt[i01][M00080ItemComversionMstRt.ColCtUnitName];	//カートン商品単位
						String GetCsUnitName	= (String)ItemComversionMstRt[i01][M00080ItemComversionMstRt.ColCsUnitName];	//ケース商品単位
						String GetPlUnitName	= (String)ItemComversionMstRt[i01][M00080ItemComversionMstRt.ColPlUnitName];	//パレット商品単位
						
						int SetPackingType					= 0;				//荷姿タイプ
						int SetPackingQty					= 1;				//荷姿入数(バラ換算数)
						String SetItemName01				=GetItemName01;		//商品名
						String SetPackingTypeItemName		=GetItemName01;		//荷姿商品名
						String SetItemUnitName				=GetUnitName;		//商品単位(バラ)
						String SetPackingTypeItemUnitName	=GetUnitName;		//商品単位(荷姿単位)
						
						switch(GetPackingType) {
							case 0:
								break;
							case 1:
								SetPackingType				=GetPackingType;	//荷姿タイプ
								SetPackingQty				=GetCtQty;			//荷姿入数(バラ換算数)
								SetPackingTypeItemName		=GetCtName;			//荷姿商品名
								SetPackingTypeItemUnitName	=GetCtUnitName;		//商品単位(荷姿単位)
								if("".equals(SetPackingTypeItemName)) {SetPackingTypeItemName="【Ct】"+SetItemName01;}
								break;
							case 2:
								SetPackingType				=GetPackingType;	//荷姿タイプ
								SetPackingQty				=GetCsQty;			//荷姿入数(バラ換算数)
								SetPackingTypeItemName		=GetCsName;			//荷姿商品名
								SetPackingTypeItemUnitName	=GetCsUnitName;		//商品単位(荷姿単位)
								if("".equals(SetPackingTypeItemName)) {SetPackingTypeItemName="【Cs】"+SetItemName01;}
								break;
							case 3:
								SetPackingType				=GetPackingType;	//荷姿タイプ
								SetPackingQty				=GetPlQty;			//荷姿入数(バラ換算数)
								SetPackingTypeItemName		=GetPlName;			//荷姿商品名
								SetPackingTypeItemUnitName	=GetPlUnitName;		//商品単位(荷姿単位)
								if("".equals(SetPackingTypeItemName)) {SetPackingTypeItemName="【Pl】"+SetItemName01;}
								break;
							default:
								break;
						}
						
						rt[i][ColItemCd]						= GetItemCd;						//変換後商品CD
						rt[i][ColPackingType]					= SetPackingType;					//荷姿タイプ
						rt[i][ColPackingQty]					= SetPackingQty;					//荷姿入数(バラ換算数)
						rt[i][ColItemName01]					= SetItemName01;					//商品名
						rt[i][ColPackingTypeItemName]		= SetPackingTypeItemName;			//荷姿商品名
						rt[i][ColItemUnitName]				= SetItemUnitName;					//商品単位(バラ)
						rt[i][ColPackingTypeItemUnitName]	= SetPackingTypeItemUnitName;		//商品単位(荷姿単位)
					}
				}
			}
		}
		return rt;
	}
	
	private static String ClgpGet(String ClCd) {
		String ClGp = "";
		ArrayList<String> SearchClGpCD = new ArrayList<String>();
		ArrayList<String> SearchCLCD = new ArrayList<String>();
		ArrayList<String> SearchCLName = new ArrayList<String>();
		ArrayList<String> SearchPost = new ArrayList<String>();
		ArrayList<String> searchAdd = new ArrayList<String>();
		ArrayList<String> SearchTel = new ArrayList<String>();
		ArrayList<String> SearchFax = new ArrayList<String>();
		ArrayList<String> SearchMail = new ArrayList<String>();
		ArrayList<String> SearchCom = new ArrayList<String>();
		ArrayList<String> SearchWHCD = new ArrayList<String>();
		boolean AllSearch = false;
		
		SearchCLCD.add(ClCd);
		
		Object[][] ClMstRt = M00011ClMstRt.ClMstRt(
			SearchClGpCD,
			SearchCLCD,
			SearchCLName,
			SearchPost,
			searchAdd,
			SearchTel,
			SearchFax,
			SearchMail,
			SearchCom,
			SearchWHCD,
			AllSearch);
		if(1==ClMstRt.length) {
			ClGp = (String)ClMstRt[0][M00011ClMstRt.ColClGpCD];		//荷主グループCD
		}
		return ClGp;
	}
}