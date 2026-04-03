import java.util.ArrayList;
import java.util.HashMap;

public class WM00093LocationMstRenewStockControl{
	//WM00093LocationMstRenewStockControl.LocationMstRenewStockControl(RenewLoc);
	//ロケーションマスタ更新時にロケタイプが変更加えられた場合に在庫情報を更新する
	/*
	RenewLoc[0][0] = ClCd[0]
	RenewLoc[0][1] = WhCd[0]
	RenewLoc[0][2] = Loc[0]
	RenewLoc[0][3] = Type[0]
	RenewLoc[0][4] = LocName[0]
	～～～～～～～～～
	RenewLoc[i][0] = ClCd[i]
	RenewLoc[i][1] = WhCd[i]
	RenewLoc[i][2] = Loc[i]
	RenewLoc[i][3] = Type[i]
	RenewLoc[i][4] = LocName[i]
	の構造で変更後の設定を引き渡してください		
	*/
	public static void LocationMstRenewStockControl(
			String[][] RenewLoc
			) {
		
		HashMap<String,String[]> NowLoc = NowLocHashMapRt(RenewLoc);
		HashMap<String,String[]> SetLoc = SetLocHashMapRt(RenewLoc);
		
		//現在のマスタ状況と変更後のマスタ状況を比較
		//引当可能ロケ⇒引当不可ロケ　又は　引当不可ロケ⇒引当可能ロケに変更加えられる場合在庫の引当可能数を更新する
		//引当不可ロケに変更:引当可能数ゼロに　引当可能ロケに変更:引当可能数を在庫数-引当済数に更新
		//※既に引当済みの数量=引当済み数は変更しない
		for(String[] GetVal : SetLoc.values()){
			String GetSetClCd		= GetVal[0];
			String GetSetWhCd		= GetVal[1];
			String GetSetLoc		= GetVal[2];
			String GetSetType		= GetVal[3];
			String GetSetLocName	= GetVal[4];
			String GetSetKey = GetSetClCd+"_"+GetSetWhCd+"_"+GetSetLoc;
			
			int SetPickTgtLocFg = 0;		//引当対象ロケなら1　禁止なら0
			
			for(int i01=0;i01<B00100DefaultVariable.LocType[1].length;i01++) {
				if(GetSetType.equals(B00100DefaultVariable.LocType[1][i01])) {
					SetPickTgtLocFg = Integer.parseInt(B00100DefaultVariable.LocType[3][i01]);
				}
			}
			
			if("0".equals(GetSetType)||"1".equals(GetSetType)) {SetPickTgtLocFg = 1;}
			
			String[] GetSt = NowLoc.get(GetSetKey);
			if(null!=GetSt) {
				String GetNowClCd		= GetSt[0];
				String GetNowWhCd		= GetSt[1];
				String GetNowLoc		= GetSt[2];
				String GetNowType		= GetSt[3];
				String GetNowLocName	= GetSt[4];
				
				int NowPickTgtLocFg = 0;
				
				for(int i01=0;i01<B00100DefaultVariable.LocType[1].length;i01++) {
					if(GetNowType.equals(B00100DefaultVariable.LocType[1][i01])) {
						NowPickTgtLocFg = Integer.parseInt(B00100DefaultVariable.LocType[3][i01]);
					}
				}
				
				if(SetPickTgtLocFg!=NowPickTgtLocFg) {
					StockRenew(GetSetClCd,GetSetWhCd,GetSetLoc,GetSetType,SetPickTgtLocFg);
				}
			}
		}
		
		LocationRenew(SetLoc) ;	//ロケーションマスタ登録・更新
	}
	
	private static void LocationRenew(HashMap<String,String[]> SetLoc) {
		String now_dtm = B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[1];
		
		String[][] LocationSetString = {
				 {"ClCd"		,"1","1"}	//荷主コード
				,{"WhCd"		,"1","1"}	//倉庫コード
				,{"Loc"			,"1","1"}	//ロケーション
				,{"Type"		,"1","1"}	//ロケタイプ
				,{"LocName"		,"1","1"}	//ロケーション名
				,{"EntryDate"	,"1","0"}	//登録日
				,{"UpdateDate"	,"1","1"}	//更新日
				,{"EntryUser"	,"1","0"}	//登録者
				,{"UpdateUser"	,"1","1"}	//更新者
				};
		String[] LocationSetJudgeString = {
				 "ClCd"	//荷主コード
				,"WhCd"	//倉庫コード
				,"Loc"	//ロケーション
				};
		
		String tgt_table = "WM0010LOCATIONMST";
		String[][] field_name = LocationSetString;
		String[][] entry_data = new String[SetLoc.size()][field_name.length];
		String[] judg_field = LocationSetJudgeString;
		String[][] judg_data = new String[SetLoc.size()][judg_field.length];
		String TgtDB = "WANKO";
		int non_msg_fg = 1;
		
		int SetRow = 0; 
		for(String[] GetVal : SetLoc.values()){
			String GetSetClCd		= GetVal[0];
			String GetSetWhCd		= GetVal[1];
			String GetSetLoc		= GetVal[2];
			String GetSetType		= GetVal[3];
			String GetSetLocName	= GetVal[4];
			
			entry_data[SetRow][0]	= GetSetClCd; 		//荷主コード
			entry_data[SetRow][1]	= GetSetWhCd; 		//倉庫コード
			entry_data[SetRow][2]	= GetSetLoc; 		//ロケーション
			entry_data[SetRow][3]	= GetSetType; 		//ロケタイプ
			entry_data[SetRow][4]	= GetSetLocName; 	//ロケーション名
			entry_data[SetRow][5]	= now_dtm; 			//登録日
			entry_data[SetRow][6]	= now_dtm; 			//更新日
			entry_data[SetRow][7]	= "(" + A00000Main.LoginUserId + ")" + A00000Main.LoginUserName; 	//登録者
			entry_data[SetRow][8]	= "(" + A00000Main.LoginUserId + ")" + A00000Main.LoginUserName; 	//更新者
			
			judg_data[SetRow][0] = GetSetClCd; 	//荷主コード
			judg_data[SetRow][1] = GetSetWhCd; 	//倉庫コード
			judg_data[SetRow][2] = GetSetLoc; 	//ロケーション
					
			SetRow = SetRow+1;
		}
		
		if(0<entry_data.length) {
			A00020InsertUdateSQL.RUN_SQLS_EU(tgt_table, field_name, entry_data, judg_field, judg_data, non_msg_fg,TgtDB);
		}
	}
	
	private static void StockRenew(String GetSetClCd,String GetSetWhCd,String GetSetLoc,String GetSetType,int SetPickTgtLocFg) {
		ArrayList<String>  SearchClCd			= new ArrayList<String>();	//荷主コード
		ArrayList<String>  SearchWhCd			= new ArrayList<String>();	//倉庫コード
		ArrayList<String>  SearchLoc			= new ArrayList<String>();	//ロケーション
		ArrayList<String>  SearchItemCd			= new ArrayList<String>();	//商品コード
		ArrayList<String>  SearchLot			= new ArrayList<String>();	//ロット
		ArrayList<String>  SearchExpdateMin		= new ArrayList<String>();	//消費期限最小
		ArrayList<String>  SearchActualDateMin	= new ArrayList<String>();	//入荷実績日最小
		ArrayList<Integer> SearchQtyMin			= new ArrayList<Integer>();	//数量最小
		ArrayList<Integer> SearchShipPlanQtyMin	= new ArrayList<Integer>();	//引当済数最小
		ArrayList<Integer> SearchPossibleQtyMin	= new ArrayList<Integer>();	//出荷可能数最小
		ArrayList<String>  SearchExpdateMax		= new ArrayList<String>();	//消費期限最大
		ArrayList<String>  SearchActualDateMax	= new ArrayList<String>();	//入荷実績日最大
		ArrayList<Integer> SearchQtyMax			= new ArrayList<Integer>();	//数量最大
		ArrayList<Integer> SearchShipPlanQtyMax	= new ArrayList<Integer>();	//引当済数最大
		ArrayList<Integer> SearchPossibleQtyMax	= new ArrayList<Integer>();	//出荷可能数最大
		ArrayList<String>  SearchItemName		= new ArrayList<String>();	//商品名
		ArrayList<String>  SearchClItemCd		= new ArrayList<String>();	//荷主商品コード
		ArrayList<String>  SearchJanCd			= new ArrayList<String>();	//ソースマーク_BCD（バラ）
		ArrayList<String>  SearchItemMdNo		= new ArrayList<String>();	//商品型番
		boolean LocSortFg = true;					//ロケ順⇒商品CD⇒賞味期限⇒ロット⇒入荷日順で並べ替え　false なら商品CD⇒賞味期限⇒ロット⇒入荷日⇒ロケ
		boolean LocExactMatch = false;				//ロケーション完全一致
		boolean AllSearch = false;
		
		SearchClCd.add(	GetSetClCd);	//荷主コード
		SearchWhCd.add(	GetSetWhCd);	//倉庫コード
		SearchLoc.add(	GetSetLoc);		//ロケーション
		
		Object[][] StrokRt = T00010StrokRt.StrokRt(
				SearchClCd,				//荷主コード
				SearchWhCd,				//倉庫コード
				SearchLoc,				//ロケーション
				SearchItemCd,			//商品コード
				SearchLot,				//ロット
				SearchExpdateMin,		//消費期限最小
				SearchActualDateMin,	//入荷実績日最小
				SearchQtyMin,			//数量最小
				SearchShipPlanQtyMin,	//引当済数最小
				SearchPossibleQtyMin,	//出荷可能数最小
				SearchExpdateMax,		//消費期限最大
				SearchActualDateMax,	//入荷実績日最大
				SearchQtyMax,			//数量最大
				SearchShipPlanQtyMax,	//引当済数最大
				SearchPossibleQtyMax,	//出荷可能数最大
				SearchItemName,			//商品名
				SearchClItemCd,			//荷主商品コード
				SearchJanCd,			//ソースマーク_BCD（バラ）
				SearchItemMdNo,			//商品型番
				LocSortFg,				//ロケ順⇒商品CD⇒賞味期限⇒ロット⇒入荷日順で並べ替え　false なら商品CD⇒賞味期限⇒ロット⇒入荷日⇒ロケ
				LocExactMatch,			//ロケーション完全一致
				AllSearch);
		
		String now_dtm = B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[1];
		
		String[][] StrokSetString = {
				 {"ClCd"		,"0","0"}	//荷主コード
				,{"WhCd"		,"0","0"}	//倉庫コード
				,{"Loc"			,"0","0"}	//ロケーション
				,{"ItemCd"		,"0","0"}	//商品コード
				,{"Lot"			,"0","0"}	//ロット
				,{"Expdate"		,"0","0"}	//消費期限
				,{"ActualDate"	,"0","0"}	//入荷実績日
				,{"Qty"			,"0","1"}	//数量
				,{"ShipPlanQty"	,"0","0"}	//引当済数
				,{"PossibleQty"	,"0","1"}	//出荷可能数
				,{"UpdateDate"	,"0","1"}	//更新日時
				,{"UpdateUser"	,"0","1"}	//更新者
				};
		String[] StrokSetJudgeString = {
				 "ClCd"			//荷主コード
				,"WhCd"			//倉庫コード
				,"Loc"			//ロケーション
				,"ItemCd"		//商品コード
				,"Lot"			//ロット
				,"Expdate"		//消費期限
				,"ActualDate"	//入荷実績日
				};
		
		String tgt_table = "WW0015Stock";
		String[][] field_name = StrokSetString;
		String[][] entry_data = new String[StrokRt.length][field_name.length];
		String[] judg_field = StrokSetJudgeString;
		String[][] judg_data = new String[StrokRt.length][judg_field.length];
		String TgtDB = "WANKO";
		int non_msg_fg = 1;
				
		for(int i=0;i<StrokRt.length;i++) {
			String GetClCd			= (String)StrokRt[i][T00010StrokRt.ColClCd];			//荷主コード
			String GetWhCd			= (String)StrokRt[i][T00010StrokRt.ColWhCd];			//倉庫コード
			String GetLoc			= (String)StrokRt[i][T00010StrokRt.ColLoc];			//ロケーション
			String GetItemCd		= (String)StrokRt[i][T00010StrokRt.ColItemCd];			//商品コード
			String GetLot			= (String)StrokRt[i][T00010StrokRt.ColLot];			//ロット
			String GetExpdate		= (String)StrokRt[i][T00010StrokRt.ColExpdate];		//消費期限
			String GetActualDate	= (String)StrokRt[i][T00010StrokRt.ColActualDate];	//入荷実績日
			int GetQty				= (int)StrokRt[i][T00010StrokRt.ColQty];				//数量
			int GetShipPlanQty		= (int)StrokRt[i][T00010StrokRt.ColShipPlanQty];		//引当済数
			int GetPossibleQty		= (int)StrokRt[i][T00010StrokRt.ColPossibleQty];		//出荷可能数
			String GetItemName		= (String)StrokRt[i][T00010StrokRt.ColItemName];		//商品名
			String GetClItemCd		= (String)StrokRt[i][T00010StrokRt.ColClItemCd];		//荷主商品コード
			String GetJanCd			= (String)StrokRt[i][T00010StrokRt.ColJanCd];			//ソースマーク_BCD（バラ）
			String GetItemMdNo		= (String)StrokRt[i][T00010StrokRt.ColItemMdNo];		//商品型番
			String GetEntryDate		= (String)StrokRt[i][T00010StrokRt.ColEntryDate];		//登録日時
			String GetUpdateDate	= (String)StrokRt[i][T00010StrokRt.ColUpdateDate];	//更新日時
			String GetEntryUser		= (String)StrokRt[i][T00010StrokRt.ColEntryUser];		//登録者
			String GetUpdateUser	= (String)StrokRt[i][T00010StrokRt.ColUpdateUser];	//更新者
			
			if(1==SetPickTgtLocFg) {
				//変更後ロケが引当可能なロケの場合
				GetPossibleQty = GetQty - GetShipPlanQty;
			}else {
				//変更後ロケが引当不可なロケの場合
				GetPossibleQty = 0;
			}
			
			entry_data[i][ 0]	= ""+GetClCd;			//荷主コード
			entry_data[i][ 1]	= ""+GetWhCd;			//倉庫コード
			entry_data[i][ 2]	= ""+GetLoc;			//ロケーション
			entry_data[i][ 3]	= ""+GetItemCd;			//商品コード
			entry_data[i][ 4]	= ""+GetLot;			//ロット
			entry_data[i][ 5]	= ""+GetExpdate;		//消費期限
			entry_data[i][ 6]	= ""+GetActualDate;		//入荷実績日
			entry_data[i][ 7]	= ""+GetQty;			//数量
			entry_data[i][ 8]	= ""+GetShipPlanQty;	//引当済数
			entry_data[i][ 9]	= ""+GetPossibleQty;	//出荷可能数
			entry_data[i][10]	= now_dtm;				//更新日時
			entry_data[i][11]	= "(" + A00000Main.LoginUserId + ")" + A00000Main.LoginUserName;	//更新者
			
			judg_data[i][ 0]= ""+GetClCd;		//荷主コード
			judg_data[i][ 1]= ""+GetWhCd;		//倉庫コード
			judg_data[i][ 2]= ""+GetLoc;		//ロケーション
			judg_data[i][ 3]= ""+GetItemCd;		//商品コード
			judg_data[i][ 4]= ""+GetLot;		//ロット
			judg_data[i][ 5]= ""+GetExpdate;	//消費期限
			judg_data[i][ 6]= ""+GetActualDate;	//入荷実績日
		}
		
		if(0<entry_data.length) {
			A00020InsertUdateSQL.RUN_SQLS_EU(tgt_table, field_name, entry_data, judg_field, judg_data, non_msg_fg,TgtDB);
		}
	}
	
	private static HashMap<String,String[]> SetLocHashMapRt(String[][] RenewLoc){
		HashMap<String,String[]> SetLoc = new HashMap<String,String[]>();
		
		if(null!=RenewLoc && 0<RenewLoc.length) {
			for(int i=0;i<RenewLoc.length;i++) {
				if(3<=RenewLoc[i].length) {
					String GetClCd		= RenewLoc[i][0];
					String GetWhCd		= RenewLoc[i][1];
					String GetLoc		= RenewLoc[i][2];
					String GetType		= RenewLoc[i][3];
					String GetLocName	= RenewLoc[i][4];
					
					String Key = GetClCd+"_"+GetWhCd+"_"+GetLoc;
					SetLoc.put(Key, RenewLoc[i]);
				}
			}
		}
		return SetLoc;
	}
	
	private static HashMap<String,String[]> NowLocHashMapRt(String[][] RenewLoc){
		HashMap<String,String[]> NowLoc = new HashMap<String,String[]>();
		
		if(null!=RenewLoc && 0<RenewLoc.length) {
			ArrayList<String> SearchClCd 	= new ArrayList<String>();	//荷主コード
			ArrayList<String> SearchWhCd 	= new ArrayList<String>();	//倉庫コード
			ArrayList<String> SearchLoc 	= new ArrayList<String>();	//ロケーション
			ArrayList<String> SearchLocName = new ArrayList<String>();	//ロケーション名
			ArrayList<String> SearchType 	= new ArrayList<String>();	//ロケタイプ
			boolean LocExactMatch = true;	//ロケーション完全一致
			boolean AllSearch = false;
			
			for(int i=0;i<RenewLoc.length;i++) {
				if(3<=RenewLoc[i].length) {
					String GetClCd		= RenewLoc[i][0];
					String GetWhCd		= RenewLoc[i][1];
					String GetLoc		= RenewLoc[i][2];
					String GetType		= RenewLoc[i][3];
					String GetLocName	= RenewLoc[i][4];
					
					SearchClCd.add(	GetClCd);
					SearchWhCd.add(	GetWhCd);
					SearchLoc.add(	GetLoc);
				}
			}
			
			Object[][] LocationMstRt = M00090LocationMstRt.LocationMstRt(
					SearchClCd,		//荷主コード
					SearchWhCd,		//倉庫コード
					SearchLoc,		//ロケーション
					SearchLocName,	//ロケーション名
					SearchType,		//ロケタイプ
					LocExactMatch,	//ロケーション完全一致
					AllSearch);
			
			for(int i=0;i<LocationMstRt.length;i++) {
				String GetClCd			= (String)LocationMstRt[i][M00090LocationMstRt.ColClCd];			//荷主コード
				String GetCLName01		= (String)LocationMstRt[i][M00090LocationMstRt.ColCLName01];		//荷主名1
				String GetWhCd			= (String)LocationMstRt[i][M00090LocationMstRt.ColWhCd];			//倉庫コード
				String GetWHName		= (String)LocationMstRt[i][M00090LocationMstRt.ColWHName];			//拠点倉庫名
				String GetLoc			= (String)LocationMstRt[i][M00090LocationMstRt.ColLoc];			//ロケーション
				String GetLocName		= (String)LocationMstRt[i][M00090LocationMstRt.ColLocName];		//ロケーション名
				int GetType				= (int)LocationMstRt[i][M00090LocationMstRt.ColType];				//ロケタイプ
				String GetEntryDate		= (String)LocationMstRt[i][M00090LocationMstRt.ColEntryDate];		//登録日
				String GetUpdateDate	= (String)LocationMstRt[i][M00090LocationMstRt.ColUpdateDate];	//更新日
				String GetEntryUser		= (String)LocationMstRt[i][M00090LocationMstRt.ColEntryUser];		//登録者
				String GetUpdateUser	= (String)LocationMstRt[i][M00090LocationMstRt.ColUpdateUser];	//更新者
				
				String Key = GetClCd+"_"+GetWhCd+"_"+GetLoc;
				String[] SetOb = {GetClCd,GetWhCd,GetLoc,""+GetType,GetLocName};
				NowLoc.put(Key, SetOb);
			}
		}
		return NowLoc;
	}
	
}