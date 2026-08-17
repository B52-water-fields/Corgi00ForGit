import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class B100_LanguageControl{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	
	public static Object[][] RtControl(Object[][] TgtRt){
		//RtXXXXRtの第三カラムを対応言語にスイッチ
		Object[][] Rt = new Object[TgtRt.length][5];
		
		for(int i=0;i<TgtRt.length;i++) {
			for(int i01=0;i01<Rt[i].length;i01++) {
				if(i01<TgtRt[i].length) {
					Rt[(int)TgtRt[i][1]][i01]=TgtRt[i][i01];
				}
			}
			switch(A00000_Main.LoginUserLanguage) {
				case "JP":
					if(3<TgtRt[i].length) { Rt[(int)TgtRt[i][1]][3]	= TgtRt[i][ 3];}
					break;
				case "EN":
					if(5<TgtRt[i].length) {	Rt[(int)TgtRt[i][1]][3]	= TgtRt[i][ 5];}
					break;
				case "ZH":
					if(6<TgtRt[i].length) {	Rt[(int)TgtRt[i][1]][3]	= TgtRt[i][ 6];}
					break;
				default:
					break;
			}
		}
		return Rt;
	}
	
	public static Object[][] DefinitionControl(Object[][] TgtDefinition){
		//XXXXRtの検索条件設定Definitionの第6,7カラムを対応言語にスイッチ
		Object[][] Rt = new Object[TgtDefinition.length][7];
		
		for(int i=0;i<TgtDefinition.length;i++) {
			for(int i01=0;i01<Rt[i].length;i01++) {
				if(i01<TgtDefinition[i].length) {
					Rt[(int)TgtDefinition[i][3]][i01]	= TgtDefinition[i][i01];
				}
			}
			switch(A00000_Main.LoginUserLanguage) {
				case "JP":
					if( 5<TgtDefinition[i].length) { Rt[(int)TgtDefinition[i][3]][5]	= TgtDefinition[i][ 5];}
					if( 6<TgtDefinition[i].length) { Rt[(int)TgtDefinition[i][3]][6]	= TgtDefinition[i][ 6];}
					break;
				case "EN":
					if( 7<TgtDefinition[i].length) { Rt[(int)TgtDefinition[i][3]][5]	= TgtDefinition[i][ 7];}
					if( 8<TgtDefinition[i].length) { Rt[(int)TgtDefinition[i][3]][6]	= TgtDefinition[i][ 8];}
					break;
				case "ZH":
					if( 9<TgtDefinition[i].length) { Rt[(int)TgtDefinition[i][3]][5]	= TgtDefinition[i][ 9];}
					if(10<TgtDefinition[i].length) { Rt[(int)TgtDefinition[i][3]][6]	= TgtDefinition[i][10];}
					break;
				default:
					break;
			}
		}
		return Rt;
	}
	
	static final int ColJP = 0;
	static final int ColEN = 1;
	static final int ColZH = 2;
	
	private static String[][] LanguageListRt(String[][][] TgtList) {
		String[][] Rt = new String[3][0];
		if(null!=TgtList && 0<TgtList.length) {
			Rt = TgtList[0];
			switch(A00000_Main.LoginUserLanguage) {
				case "JP":
					if(ColJP<TgtList.length)
						Rt	= TgtList[ColJP];
					break;
				case "EN":
					if(ColEN<TgtList.length)
						Rt	= TgtList[ColEN];
					break;
				case "ZH":
					if(ColZH<TgtList.length)
						Rt	= TgtList[ColZH];
					break;
				default:
					break;
			}
		}
		return Rt;
	}
	
	public static void DefaultVariableControl() {
		// ============================================================================
		// 税区分
		// ============================================================================
		String[][] SearchTaxFgJP 							= {{"未指定","0:外税","1:内税","2:非課税"}								,{"","0","1","2"}	,{"","外税","内税","非課税"}};						//検索条件：外税内税区分
		String[][] SearchTaxFgEN 							= {{"Unspecified","0:Tax Excluded","1:Tax Included","2:Tax Exempt"} 	,{"","0","1","2"}	,{"","Tax Excluded","Tax Included","Tax Exempt"}};	//検索条件：外税内税区分
		String[][] SearchTaxFgZH 							= {{"未指定","0:外税","1:含税","2:免税"}								,{"","0","1","2"}	,{"","外税","含税","免税"}};						//検索条件：外税内税区分
		
		String[][] TaxFgJP 									= {{"0:外税","1:内税","2:非課税"}										,{"0","1","2"}		,{"外税","内税","非課税"}};							//外税内税区分
		String[][] TaxFgEN 									= {{"0:Tax Excluded","1:Tax Included","2:Tax Exempt"} 					,{"0","1","2"}		,{"Tax Excluded","Tax Included","Tax Exempt"}};		//外税内税区分
		String[][] TaxFgZH 									= {{"0:外税","1:含税","2:免税"}											,{"0","1","2"}		,{"外税","含税","免税"}};							//外税内税区分
		
		String[][][] SearchTaxFgTgtList 					= {SearchTaxFgJP,SearchTaxFgEN,SearchTaxFgZH};
		String[][][] TaxFgTgtList 							= {TaxFgJP,TaxFgEN,TaxFgZH};
		
		B100_DefaultVariable.SearchTaxFgList				= B100_LanguageControl.LanguageListRt(SearchTaxFgTgtList);	//検索条件：外税内税区分
		B100_DefaultVariable.TaxFgList 					= B100_LanguageControl.LanguageListRt(TaxFgTgtList);			//外税内税区分

		// ============================================================================
		// 削除区分
		// ============================================================================
		String[][] SearchDelListJP 							= {{"0:稼働中","1:削除","未指定"}			,{"0","1",""}	,{"稼働中","削除",""}};		//検索用削除区分
		String[][] SearchDelListEN 							= {{"0:Active","1:Deleted","Unspecified"}	,{"0","1",""}	,{"Active","Deleted",""}};	//検索用削除区分
		String[][] SearchDelListZH 							= {{"0:使用中","1:已删除","未指定"}			,{"0","1",""}	,{"使用中","已删除",""}};		//検索用削除区分
		
		String[][] DelListJP 								= {{"0:稼働中","1:削除"}					,{"0","1"}		,{"稼働中","削除"}};		//設定用削除区分
		String[][] DelListEN 								= {{"0:Active","1:Deleted"}					,{"0","1"}		,{"Active","Deleted"}};		//設定用削除区分
		String[][] DelListZH 								= {{"0:使用中","1:已删除"}					,{"0","1"}		,{"使用中","已删除"}};		//設定用削除区分
		
		String[][][] SearchDelTgtList 						= {SearchDelListJP,SearchDelListEN,SearchDelListZH};
		String[][][] DelTgtList 							= {DelListJP,DelListEN,DelListZH};
		
		B100_DefaultVariable.SearchDelList 				= B100_LanguageControl.LanguageListRt(SearchDelTgtList);		//検索用削除区分
		B100_DefaultVariable.DelList 						= B100_LanguageControl.LanguageListRt(DelTgtList);			//設定用削除区分

		// ============================================================================
		// マスタ優先区分
		// ============================================================================
		String[][] SearchMstPriorityFirstFgJP 				= {{"0:データ優先","1:マスタ優先","未指定"}					,{"0","1",""}	,{"データ優先","マスタ優先",""}};
		String[][] SearchMstPriorityFirstFgEN 				= {{"0:Data Priority","1:Master Priority","Unspecified"}	,{"0","1",""}	,{"Data Priority","Master Priority",""}};
		String[][] SearchMstPriorityFirstFgZH 				= {{"0:数据优先","1:主数据优先","未指定"}					,{"0","1",""}	,{"数据优先","主数据优先",""}};
		
		String[][] MstPriorityFirstFgJP 					= {{"0:データ優先","1:マスタ優先"}							,{"0","1"}		,{"データ優先","マスタ優先"}};
		String[][] MstPriorityFirstFgEN 					= {{"0:Data Priority","1:Master Priority"}					,{"0","1"}		,{"Data Priority","Master Priority"}};
		String[][] MstPriorityFirstFgZH 					= {{"0:数据优先","1:マスタ優先"}							,{"0","1"}		,{"数据优先","主数据优先"}};
		
		String[][][] SearchMstPriorityFirstFgTgtList 		= {SearchMstPriorityFirstFgJP,SearchMstPriorityFirstFgEN,SearchMstPriorityFirstFgZH};
		String[][][] MstPriorityFirstFgTgtList 				= {MstPriorityFirstFgJP,MstPriorityFirstFgEN,MstPriorityFirstFgZH};
		
		B100_DefaultVariable.SearchMstPriorityFirstFg 	= B100_LanguageControl.LanguageListRt(SearchMstPriorityFirstFgTgtList);	
		B100_DefaultVariable.MstPriorityFirstFg 			= B100_LanguageControl.LanguageListRt(MstPriorityFirstFgTgtList);

		// ============================================================================
		// ロケタイプ
		// ============================================================================

		String[][] SearchLocTypeJP 							= {{"未指定","0:通常","1:保管","7:スルーロケ","8:入荷時","9:引当禁止"}						,{"","0","1","7","8","9"}	,{"","通常","保管","スルーロケ","入荷時","引当禁止"}					,{"","1","1","0","0","0"}};		//ロケタイプ検索値
		String[][] SearchLocTypeEN 							= {{"Unspecified","0:Normal","1:Storage","7:Through","8:Receipt","9:Allocation Prohibited"}	,{"","0","1","7","8","9"}	,{"","Normal","Storage","Through","Receipt","Allocation Prohibited"}	,{"","1","1","0","0","0"}};		//ロケタイプ検索値
		String[][] SearchLocTypeZH 							= {{"未指定","0:普通","1:保管","7:越库库位","8:入库 ","9:禁止分配"}							,{"","0","1","7","8","9"}	,{"","普通","保管","越库库位","入库 ","禁止分配"}							,{"","1","1","0","0","0"}};		//ロケタイプ検索値
		
		
		String[][] LocTypeJP 								= {{"0:通常","1:保管","7:スルーロケ","8:入荷時","9:引当禁止"}								,{"0","1","7","8","9"}		,{"通常","保管","スルーロケ","入荷時","引当禁止"}						,{"1","1","0","0","0"}};		//ロケタイプ設定値
		String[][] LocTypeEN 								= {{"0:Normal","1:Storage","7:Through","8:Receipt","9:Allocation Prohibited"}				,{"0","1","7","8","9"}		,{"Normal","Storage","Through","Receipt","Allocation Prohibited"}		,{"1","1","0","0","0"}};		//ロケタイプ設定値
		String[][] LocTypeZH 								= {{"0:普通","1:保管","7:越库库位","8:入库 ","9:禁止分配"}										,{"0","1","7","8","9"}		,{"普通","保管","越库库位","入库 ","禁止分配"}								,{"1","1","0","0","0"}};		//ロケタイプ設定値
		
		String[][][] SearchLocTypeTgtList 					= {SearchLocTypeJP,SearchLocTypeEN,SearchLocTypeZH};
		String[][][] LocTypeTgtList 						= {LocTypeJP,LocTypeEN,LocTypeZH};
		
		B100_DefaultVariable.SearchLocType 				= B100_LanguageControl.LanguageListRt(SearchLocTypeTgtList);
		B100_DefaultVariable.LocType 						= B100_LanguageControl.LanguageListRt(LocTypeTgtList);

		// ============================================================================
		// 配送状況
		// ============================================================================

		String[][] SearchStatusListJP 						= {{"未指定","0:未配車","1:配車済","2:出荷完了","3:配達完了","8:保留","9:キャンセル"}				,{"","0","1","2","3","8","9"}	,{"","未配車","配車済","出荷完了","配達完了","保留","キャンセル"}};			//検索条件：状況
		String[][] SearchStatusListEN 						= {{"Unspecified","0:Unassigned","1:Assigned","2:Shipped","3:Delivered","8:On Hold","9:Canceled"}	,{"","0","1","2","3","8","9"}	,{"","Unassigned","Assigned","Shipped","Delivered","On Hold","Canceled"}};	//検索条件：状況
		String[][] SearchStatusListZH 						= {{"未指定","0:未派车","1:已派车","2:已出库","3:已送达","8:暂停","9:已取消"}						,{"","0","1","2","3","8","9"}	,{"","未派车","已派车","已出库","已送达","暂停","已取消"}};					//検索条件：状況
		
		String[][] StatusListJP 							= {{"0:未配車","1:配車済","2:出荷完了","3:配達完了","8:保留","9:キャンセル"}						,{"0","1","2","3","8","9"}		,{"未配車","配車済","出荷完了","配達完了","保留","キャンセル"}};			//状況
		String[][] StatusListEN 							= {{"0:Unassigned","1:Assigned","2:Shipped","3:Delivered","8:On Hold","9:Canceled"}					,{"0","1","2","3","8","9"}		,{"Unassigned","Assigned","Shipped","Delivered","保留","Canceled"}};		//状況
		String[][] StatusListZH 							= {{"0:未派车","1:已派车","2:已出库","3:已送达","8:暂停","9:已取消"}									,{"0","1","2","3","8","9"}		,{"未派车","已派车","已出库","已送达","暂停","已取消"}};						//状況
		
		String[][][] SearchStatusTgtList 					= {SearchStatusListJP,SearchStatusListEN,SearchStatusListZH};	//検索条件：状況
		String[][][] StatusTgtList 							= {StatusListJP,StatusListEN,StatusListZH};						//状況
		
		B100_DefaultVariable.SearchStatusList 			= B100_LanguageControl.LanguageListRt(SearchStatusTgtList);
		B100_DefaultVariable.StatusList 					= B100_LanguageControl.LanguageListRt(StatusTgtList);

		// ============================================================================
		// WMS状況
		// ============================================================================
		String[][] SearchWmsStatusListJP 					= {{"未指定","0:未引当","1:引当済","2:指示済","3:出荷済","8:引当保留","7:出荷対象外","9:キャンセル"}									,{"","0","1","2","3","8","7","9"}	,{"未指定","未引当","引当済","指示済","出荷済","引当保留","出荷対象外","キャンセル"}};							//検索条件：倉庫状況
		String[][] SearchWmsStatusListEN 					= {{"Unspecified","0:Unallocated","1:Allocated","2:Instructed","3:Shipped","8:Allocation Hold","7:Excluded from Shipping","9:Canceled"}	,{"","0","1","2","3","8","7","9"}	,{"","Unallocated","Allocated","Instructed","Shipped","Allocation Hold","Excluded from Shipping","Canceled"}};	//検索条件：倉庫状況
		String[][] SearchWmsStatusListZH 					= {{"未指定","0:未分配","1:已分配","2:已下达指示","3:已出库","8:分配暂停","7:非出库对象","9:已取消"}									,{"","0","1","2","3","8","7","9"}	,{"","未分配","已分配","已下达指示","已出库","分配暂停","非出库对象","已取消"}};								//検索条件：倉庫状況
		
		String[][] WmsStatusListJP 							= {{"0:未引当","1:引当済","2:指示済","3:出荷済","8:引当保留","7:出荷対象外","9:キャンセル"}												,{"0","1","2","3","8","7","9"}		,{"未引当","引当済","指示済","出荷済","引当保留","出荷対象外","キャンセル"}};									//倉庫状況
		String[][] WmsStatusListEN 							= {{"0:Unallocated","1:Allocated","2:Instructed","3:Shipped","8:Allocation Hold","7:Excluded from Shipping","9:Canceled"}				,{"0","1","2","3","8","7","9"}		,{"Unallocated","Allocated","Instructed","Shipped","Allocation Hold","Excluded from Shipping","Canceled"}};		//倉庫状況
		String[][] WmsStatusListZH 							= {{"0:未分配","1:已分配","2:指示済","3:已出库","8:分配暂停","7:非出库对象","9:已取消"}													,{"0","1","2","3","8","7","9"}		,{"未分配","已分配","已下达指示","已出库","分配暂停","非出库对象","已取消"}};									//倉庫状況
		
		String[][][] SearchWmsStatusTgtList 				= {SearchWmsStatusListJP,SearchWmsStatusListEN,SearchWmsStatusListZH};	//検索条件：倉庫状況
		String[][][] WmsStatusTgtList 						= {WmsStatusListJP,WmsStatusListEN,WmsStatusListZH};					//倉庫状況
		
		B100_DefaultVariable.SearchWmsStatusList 		= B100_LanguageControl.LanguageListRt(SearchWmsStatusTgtList);
		B100_DefaultVariable.WmsStatusList 				= B100_LanguageControl.LanguageListRt(WmsStatusTgtList);

		// ============================================================================
		// 入荷状況
		// ============================================================================
		String[][] SearchArryvalFixFgListJP 				= {{"未指定","0:未入荷","1:入荷済","2:分納待","9:キャンセル"}								,{"","0","1","2","9"}	,{"","未入荷","入荷済","分納待","キャンセル"}};								//入荷状況リスト
		String[][] SearchArryvalFixFgListEN 				= {{"Unspecified","0:Not Received","1:Received","2:Awaiting Partial Receipt","9:Canceled"}	,{"","0","1","2","9"}	,{"","Not Received","Received","Awaiting Partial Receipt","Canceled"}};		//入荷状況リスト
		String[][] SearchArryvalFixFgListZH 				= {{"未指定","0:未入库","1:已入库","2:等待分批入库","9:已取消"}								,{"","0","1","2","9"}	,{"","未入库","已入库","等待分批入库","已取消"}};							//入荷状況リスト
		
		String[][] ArryvalFixFgListJP 						= {{"0:未入荷","1:入荷済","2:分納待","9:キャンセル"}										,{"0","1","2","9"}		,{"未入荷","入荷済","分納待","キャンセル"}};								//入荷状況リスト
		String[][] ArryvalFixFgListEN 						= {{"0:Not Received","1:Received","2:Awaiting Partial Receipt","9:Canceled"}				,{"0","1","2","9"}		,{"Not Received","Received","Awaiting Partial Receipt","Canceled"}};		//入荷状況リスト
		String[][] ArryvalFixFgListZH 						= {{"0:未入库","1:已入库","2:等待分批入库","9:已取消"}										,{"0","1","2","9"}		,{"未入库","已入库","等待分批入库","已取消"}};								//入荷状況リスト
		
		String[][][] SearchArryvalFixFgTgtList 				= {SearchArryvalFixFgListJP,SearchArryvalFixFgListEN,SearchArryvalFixFgListZH};		//入荷状況リスト
		String[][][] ArryvalFixFgTgtList 					= {ArryvalFixFgListJP,ArryvalFixFgListEN,ArryvalFixFgListZH};						//入荷状況リスト
		
		B100_DefaultVariable.SearchArryvalFixFgList 		= B100_LanguageControl.LanguageListRt(SearchArryvalFixFgTgtList);			//入荷状況リスト
		B100_DefaultVariable.ArryvalFixFgList 			= B100_LanguageControl.LanguageListRt(ArryvalFixFgTgtList);				//入荷状況リスト

		// ============================================================================
		// 注意事項タイミング
		// ============================================================================
		String[][] SearchCautionTimingJP					= {{"未指定","0:納品時","1:出荷時"}					,{"","0","1"}	,{"","納品時","出荷時"}};				//検索条件：注意事項タイミング
		String[][] SearchCautionTimingEN					= {{"Unspecified","0:At Delivery","1:At Shipping"}	,{"","0","1"}	,{"","At Delivery","At Shipping"}};		//検索条件：注意事項タイミング
		String[][] SearchCautionTimingZH					= {{"未指定","0:交货时","1:出库时"}					,{"","0","1"}	,{"","交货时","出库时"}};					//検索条件：注意事項タイミング
		
		String[][] CautionTimingJP 							= {{"0:納品時","1:出荷時"}							,{"0","1"}		,{"納品時","出荷時"}};					//注意事項タイミング
		String[][] CautionTimingEN 							= {{"0:At Delivery","1:At Shipping"}				,{"0","1"}		,{"At Delivery","At Shipping"}};		//注意事項タイミング
		String[][] CautionTimingZH 							= {{"0:交货时","1:出库时"}								,{"0","1"}		,{"交货时","出库时"}};						//注意事項タイミング
		
		String[][][] SearchCautionTimingTgtList				= {SearchCautionTimingJP,SearchCautionTimingEN,SearchCautionTimingZH};			//検索条件：注意事項タイミング
		String[][][] CautionTimingTgtList 					= {CautionTimingJP,CautionTimingEN,CautionTimingZH};							//注意事項タイミング
		
		B100_DefaultVariable.SearchCautionTiming			= B100_LanguageControl.LanguageListRt(SearchCautionTimingTgtList);	//検索条件：注意事項タイミング
		B100_DefaultVariable.CautionTiming 				= B100_LanguageControl.LanguageListRt(CautionTimingTgtList);			//注意事項タイミング

		// ============================================================================
		// 親子伝票
		// ============================================================================
		String[][] SearchChildrenFGListJP 					= {{"未指定","0:親伝票","1:子伝票"}					,{"","0","1"}	,{"","親伝票","子伝票"}};				//検索条件：親子区分
		String[][] SearchChildrenFGListEN 					= {{"Unspecified","0:Parent Slip","1:Child Slip"}	,{"","0","1"}	,{"","Parent Slip","Child Slip"}};		//検索条件：親子区分
		String[][] SearchChildrenFGListZH 					= {{"未指定","0:父单据","1:子单据"}					,{"","0","1"}	,{"","父单据","子单据"}};					//検索条件：親子区分
		
		String[][] ChildrenFGListJP 						= {{"0:親伝票","1:子伝票"}							,{"0","1"}		,{"親伝票","子伝票"}};					//親子区分
		String[][] ChildrenFGListEN 						= {{"0:Parent Slip","1:Child Slip"}					,{"0","1"}		,{"Parent Slip","Child Slip"}};			//親子区分
		String[][] ChildrenFGListZH 						= {{"0:父单据","1:子单据"}								,{"0","1"}		,{"父单据","子单据"}};						//親子区分
		
		String[][][] SearchChildrenFGTgtList 				= {SearchChildrenFGListJP,SearchChildrenFGListEN,SearchChildrenFGListZH};		//検索条件：親子区分
		String[][][] ChildrenFGTgtList 						= {ChildrenFGListJP,ChildrenFGListEN,ChildrenFGListZH};							//親子区分
		
		B100_DefaultVariable.SearchChildrenFGList 		= B100_LanguageControl.LanguageListRt(SearchChildrenFGTgtList);				//検索条件：親子区分
		B100_DefaultVariable.ChildrenFGList 				= B100_LanguageControl.LanguageListRt(ChildrenFGTgtList);						//親子区分

		// ============================================================================
		// 温度帯
		// ============================================================================
		String[][] SearchTildFGJP 							= {{"未指定","0:常温","1:冷蔵","2:冷凍","3:チルド"}						,{"","0","1","2","3"}	,{"","常温","冷蔵","冷凍","チルド"}};
		String[][] SearchTildFGEN 							= {{"Unspecified","0:Ambient ","1:Refrigerated","2:Frozen","3:Chilled"}	,{"","0","1","2","3"}	,{"","Ambient ","Refrigerated","Frozen","Chilled"}};
		String[][] SearchTildFGZH 							= {{"未指定","0:常温","1:冷藏","2:冷冻","3:冰鲜"}							,{"","0","1","2","3"}	,{"","常温","冷藏","冷冻","冰鲜"}};
		
		String[][] TildFGJP 								= {{"0:常温","1:冷蔵","2:冷凍","3:チルド"}								,{"0","1","2","3"}		,{"常温","冷蔵","冷凍","チルド"}};					//0:常温必須
		String[][] TildFGEN 								= {{"0:Ambient","1:Refrigerated","2:Frozen","3:Chilled"}				,{"0","1","2","3"}		,{"Ambient","Refrigerated","Frozen","Chilled"}};	//0:常温必須
		String[][] TildFGZH 								= {{"0:常温","1:冷藏","2:冷冻","3:冰鲜"}									,{"0","1","2","3"}		,{"常温","冷藏","冷凍","冰鲜"}};						//0:常温必須
		
		String[][][] SearchTildFGTgtList 					= {SearchTildFGJP,SearchTildFGEN,SearchTildFGZH};
		String[][][] TildFGTgtList 							= {TildFGJP,TildFGEN,TildFGZH};
		
		B100_DefaultVariable.SearchTildFG 				= B100_LanguageControl.LanguageListRt(SearchTildFGTgtList);
		B100_DefaultVariable.TildFG 						= B100_LanguageControl.LanguageListRt(TildFGTgtList);

		// ============================================================================
		// 受領印
		// ============================================================================
		String[][] SearchReceiptStampFGListJP				= {{"未指定","0:未回収","1:回収済","2:返送済","9:回収不要"}									,{"","0","1","2","9"}	,{"","未回収","回収済","返送済","回収不要"}};								//受領印区分
		String[][] SearchReceiptStampFGListEN				= {{"Unspecified","0:Not Collected","1:Collected","2:Returned","9:Collection Not Required"}	,{"","0","1","2","9"}	,{"","Not Collected","Collected","Returned","Collection Not Required"}};	//受領印区分
		String[][] SearchReceiptStampFGListZH				= {{"未指定","0:未回收","1:已回收","2:已返还","9:无需回收"}									,{"","0","1","2","9"}	,{"","未回收","已回收","已返还","无需回收"}};								//受領印区分
		
		String[][] ReceiptStampFGListJP 					= {{"0:未回収","1:回収済","2:返送済","9:回収不要"}											,{"0","1","2","9"}		,{"未回収","回収済","返送済","回収不要"}};									//受領印区分
		String[][] ReceiptStampFGListEN 					= {{"0:Not Collected","1:Collected","2:Returned","9:Collection Not Required"}				,{"0","1","2","9"}		,{"Not Collected","Collected","Returned","Collection Not Required"}};		//受領印区分
		String[][] ReceiptStampFGListZH 					= {{"0:未回收","1:已回收","2:已返还","9:无需回收"}											,{"0","1","2","9"}		,{"未回收","已回收","已返还","无需回收"}};					//受領印区分
		
		String[][][] SearchReceiptStampFGTgtList			= {SearchReceiptStampFGListJP,SearchReceiptStampFGListEN,SearchReceiptStampFGListZH};
		String[][][] ReceiptStampFGTgtList 					= {ReceiptStampFGListJP,ReceiptStampFGListEN,ReceiptStampFGListZH};
		
		B100_DefaultVariable.SearchReceiptStampFGList	= B100_LanguageControl.LanguageListRt(SearchReceiptStampFGTgtList);
		B100_DefaultVariable.ReceiptStampFGList 			= B100_LanguageControl.LanguageListRt(ReceiptStampFGTgtList);
		
		// ============================================================================
		// 請求区分
		// ============================================================================
		String[][] SearchInvoiceStatusListJP 				= {{"未指定","0:未請求","1:請求済","9:対象外"}						,{"","0","1","9"}	,{"","未請求","請求済","対象外"}};						//請求区分
		String[][] SearchInvoiceStatusListEN 				= {{"Unspecified","0:Not Invoiced","1:Invoiced","9:Not Applicable"}	,{"","0","1","9"}	,{"","Not Invoiced","Invoiced","Not Applicable"}};		//請求区分
		String[][] SearchInvoiceStatusListZH 				= {{"未指定","0:未开票","1:已开票","9:不适用"}						,{"","0","1","9"}	,{"","未开票","已开票","不适用"}};						//請求区分
		
		String[][] InvoiceStatusListJP 						= {{"0:未請求","1:請求済","9:対象外"}								,{"0","1","9"}		,{"未請求","請求済","対象外"}};							//請求区分
		String[][] InvoiceStatusListEN 						= {{"0:Not Invoiced","1:Invoiced","9:Not Applicable"}				,{"0","1","9"}		,{"Not Invoiced","Invoiced","Not Applicable"}};			//請求区分
		String[][] InvoiceStatusListZH 						= {{"0:未开票","1:已开票","9:不适用"}								,{"0","1","9"}		,{"未开票","已开票","不适用"}};							//請求区分
		
		String[][][] SearchInvoiceStatusTgtList 			= {SearchInvoiceStatusListJP,SearchInvoiceStatusListEN,SearchInvoiceStatusListZH};
		String[][][] InvoiceStatusTgtList 					= {InvoiceStatusListJP,InvoiceStatusListEN,InvoiceStatusListZH};
		
		B100_DefaultVariable.SearchInvoiceStatusList 	= B100_LanguageControl.LanguageListRt(SearchInvoiceStatusTgtList);
		B100_DefaultVariable.InvoiceStatusList 			= B100_LanguageControl.LanguageListRt(InvoiceStatusTgtList);

		// ============================================================================
		// 運賃請求金額確定
		// ============================================================================
		String[][] SearchFeeFixFgListJP 					= {{"未指定","0:未確定","1:確定済"}					,{"","0","1"}	,{"","未確定","確定済"}};			//金額確定フラグ検索値(請求)
		String[][] SearchFeeFixFgListEN 					= {{"Unspecified","0:Unconfirmed","1:Confirmed"}	,{"","0","1"}	,{"","Unconfirmed","Confirmed"}};	//金額確定フラグ検索値(請求)
		String[][] SearchFeeFixFgListZH 					= {{"未指定","0:未确定","1:已确定"}					,{"","0","1"}	,{"","未确定","已确定"}};			//金額確定フラグ検索値(請求)
		
		String[][] FeeFixFgListJP 							= {{"0:未確定","1:確定済"}							,{"0","1"}		,{"未確定","確定済"}};				//金額確定フラグ設定値(請求)
		String[][] FeeFixFgListEN 							= {{"0:Unconfirmed","1:Confirmed"}					,{"0","1"}		,{"Unconfirmed","Confirmed"}};		//金額確定フラグ設定値(請求)
		String[][] FeeFixFgListZH 							= {{"0:未确定","1:已确定"}							,{"0","1"}		,{"未确定","已确定"}};				//金額確定フラグ設定値(請求)
		
		String[][][] SearchFeeFixFgTgtList 					= {SearchFeeFixFgListJP,SearchFeeFixFgListEN,SearchFeeFixFgListZH};
		String[][][] FeeFixFgTgtList 						= {FeeFixFgListJP,FeeFixFgListEN,FeeFixFgListZH};
		
		B100_DefaultVariable.SearchFeeFixFgList 			= B100_LanguageControl.LanguageListRt(SearchFeeFixFgTgtList);
		B100_DefaultVariable.FeeFixFgList 				= B100_LanguageControl.LanguageListRt(FeeFixFgTgtList);

		// ============================================================================
		// 運賃支払金額確定
		// ============================================================================
		String[][] SearchPayFixFgListJP 					= {{"未指定","0:未確定","1:確定済"}					,{"","0","1"}	,{"","未確定","確定済"}};			//金額確定フラグ検索値(支払)
		String[][] SearchPayFixFgListEN 					= {{"Unspecified","0:Unconfirmed","1:Confirmed"}	,{"","0","1"}	,{"","Unconfirmed","Confirmed"}};	//金額確定フラグ検索値(支払)
		String[][] SearchPayFixFgListZH 					= {{"未指定","0:未确定","1:已确定"}					,{"","0","1"}	,{"","未确定","已确定"}};			//金額確定フラグ検索値(支払)
		
		String[][] PayFixFgListJP 							= {{"0:未確定","1:確定済"}							,{"0","1"}		,{"未確定","確定済"}};				//金額確定フラグ設定値(支払)
		String[][] PayFixFgListEN 							= {{"0:Unconfirmed","1:Confirmed"}					,{"0","1"}		,{"Unconfirmed","Confirmed"}};		//金額確定フラグ設定値(支払)
		String[][] PayFixFgListZH 							= {{"0:未确定","1:已确定"}							,{"0","1"}		,{"未确定","已确定"}};				//金額確定フラグ設定値(支払)
		
		String[][][] SearchPayFixFgTgtList 					= {SearchPayFixFgListJP,SearchPayFixFgListEN,SearchPayFixFgListZH};
		String[][][] PayFixFgTgtList 						= {PayFixFgListJP,PayFixFgListEN,PayFixFgListZH};
		
		B100_DefaultVariable.SearchPayFixFgList 			= B100_LanguageControl.LanguageListRt(SearchPayFixFgTgtList);
		B100_DefaultVariable.PayFixFgList 				= B100_LanguageControl.LanguageListRt(PayFixFgTgtList);
		
		// ============================================================================
		// 代引区分
		// ============================================================================
		String[][] SearchCODListJP 							= {{"未指定","0:一般","1:代引"}							,{"","0","1"}	,{"","一般","代引"}};					//検索条件：代引区分
		String[][] SearchCODListEN 							= {{"Unspecified","0:Standard","1:Cash on Delivery"}	,{"","0","1"}	,{"","Standard","Cash on Delivery"}};	//検索条件：代引区分
		String[][] SearchCODListZH 							= {{"未指定","0:普通","1:货到付款"}						,{"","0","1"}	,{"","普通","货到付款"}};					//検索条件：代引区分
		
		String[][] CODListJP 								= {{"0:一般","1:代引"}									,{"0","1"}		,{"一般","代引"}};						//代引区分
		String[][] CODListEN 								= {{"0:Standard","1:Cash on Delivery"}					,{"0","1"}		,{"Standard","Cash on Delivery"}};		//代引区分
		String[][] CODListZH 								= {{"0:普通","1:货到付款"}								,{"0","1"}		,{"普通","货到付款"}};					//代引区分
		
		String[][][] SearchCODTgtList 						= {SearchCODListJP,SearchCODListEN,SearchCODListZH};
		String[][][] CODTgtList 							= {CODListJP,CODListEN,CODListZH};
		
		B100_DefaultVariable.SearchCODList 				= B100_LanguageControl.LanguageListRt(SearchCODTgtList);
		B100_DefaultVariable.CODList 						= B100_LanguageControl.LanguageListRt(CODTgtList);

		// ============================================================================
		// 荷姿
		// ============================================================================
		String[][] SearchUnitTypeListJP 					= {{"未指定","0:バラ","1:カートン","2:ケース","3：パレット"}	,{"","0","1","2","3"}	,{"","バラ","カートン","ケース","パレット"}};
		String[][] SearchUnitTypeListEN 					= {{"Unspecified","0:Each","1:Carton","2:Case","3：Pallet"}		,{"","0","1","2","3"}	,{"","Each","Carton","Case","Pallet"}};
		String[][] SearchUnitTypeListZH 					= {{"未指定","0:单品","1:纸箱","2:箱","3：托盘"}					,{"","0","1","2","3"}	,{"","单品","纸箱","箱","托盘"}};
		
		String[][] UnitTypeListJP 							= {{"0:バラ","1:カートン","2:ケース","3：パレット"}				,{"0","1","2","3"}		,{"バラ","カートン","ケース","パレット"}};
		String[][] UnitTypeListEN 							= {{"0:Each","1:Carton","2:Case","3：Pallet"}					,{"0","1","2","3"}		,{"Each","Carton","Case","Pallet"}};
		String[][] UnitTypeListZH 							= {{"0:单品","1:纸箱","2:箱","3：托盘"}							,{"0","1","2","3"}		,{"单品","纸箱","箱","托盘"}};
		
		String[][][] SearchUnitTypeTgtList 					= {SearchUnitTypeListJP,SearchUnitTypeListEN,SearchUnitTypeListZH};
		String[][][] UnitTypeTgtList 						= {UnitTypeListJP,UnitTypeListEN,UnitTypeListZH};
		
		B100_DefaultVariable.SearchUnitTypeList 			= B100_LanguageControl.LanguageListRt(SearchUnitTypeTgtList);
		B100_DefaultVariable.UnitTypeList 				= B100_LanguageControl.LanguageListRt(UnitTypeTgtList);

		// ============================================================================
		// 送り状目的区分
		// ============================================================================

		//JP : 未指定 / 配達 / 配達 / 集荷 / 中継
		//EN : Unspecified / Delivery / Delivery / Pickup / Transfer
		//ZH : 未指定 / 配送 / 配送 / 揽收 / 中转

		// ============================================================================
		// ON/OFF
		// ============================================================================

		//JP : 未指定 / Off / On
		//EN : Unspecified / Off / On
		//ZH : 未指定 / 关闭 / 开启

		// ============================================================================
		// 検索条件
		// ============================================================================

		// と一致
		// EN : Equals
		// ZH : 等于

		// を含む
		// EN : Contains
		// ZH : 包含

		// で始まる
		// EN : Starts With
		// ZH : 开头为

		// 空白文字を条件に追加
		// EN : Add Blank to Conditions
		// ZH : 将空白添加到条件
		
	}
	
	//言語選択
	public static void LanguageSelect(int x,int y) {
		
		A00000_Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		
		final JFrame main_fm = B100_FrameParts.FrameCreate(x,y,500,300,"Corgi00言語選択　B100_LanguageControl","");
		
		JLabel userinfo = B100_FrameParts.UserInfo();
		JButton exit_btn = B100_FrameParts.ExitBtn();
		JButton entry_btn = B100_FrameParts.EntryBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		main_fm.add(entry_btn);
		
		
		JLabel LB_SortItemcdMode			= B100_FrameParts.JLabelSet(		  0, 75,100,20,"Language:"	,11,1);
		final JComboBox TB_SelectLanguage	= B100_FrameParts.JComboBoxSet(	100, 75,200,20,B100_DefaultVariable.LanguageList[0]	,11);
		
		TB_SelectLanguage.setSelectedIndex(B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.LanguageList[1],A00000_Main.LoginUserLanguage	,true));
		
		main_fm.add(LB_SortItemcdMode);
		main_fm.add(TB_SelectLanguage);
		
		RenewFg = true;
		main_fm.setVisible(true);
		
		entry_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				String GetLanguage = B100_DefaultVariable.LanguageList[1][TB_SelectLanguage.getSelectedIndex()];
				A00000_Main.LoginUserLanguage		= GetLanguage;
				
				B100_LanguageControl.DefaultVariableControl();
				
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				A00001_MainMenu.MainMenu(0,0);
			}
		});
		
		//EXITボタン押下時の挙動
		exit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				A00001_MainMenu.MainMenu(0,0);
			}
		});
	}
	
	
	
	
}