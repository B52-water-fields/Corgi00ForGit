import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Tools100_ArrivalPlanCancel{
	public static void ArrivalPlanCancel(
			String TgtClWh,
			String TgtClCd,
			ArrayList<String> TgtArrNo
			) {
		boolean KickFg = true;
		if(null==TgtClWh) {KickFg = false;}
		if(null==TgtClCd) {KickFg = false;}
		if(null==TgtArrNo) {KickFg = false;}
		if(null!=TgtArrNo && 0==TgtArrNo.size()) {KickFg = false;}
		
		ArrayList<String> ErrMsg = new ArrayList<String>();
		
		
		if(KickFg) {
			//対象の入荷予定情報⇒入荷予定ヘッダ取得
			//入荷予定の状況未入荷についてキャンセルステータスにする
			//ステータスが未入荷でなければエラー
			Object[][] ArrivalPlanHdRt = ArrivalPlanHdRt(TgtClWh,TgtClCd,TgtArrNo);
			
			int counter = 0;
			for(int i=0;i<ArrivalPlanHdRt.length;i++) {
				if(0==(int)ArrivalPlanHdRt[i][T100_ArrivalPlanHdRt.ColFixFg]) {
					counter = counter+1;
				}else {
					ErrMsg.add("入荷予定番号"+ (String)ArrivalPlanHdRt[i][T100_ArrivalPlanHdRt.ColArrNo]+"は未入荷ではないのでキャンセルできません");
				}
			}
			
			String[] SetClWh 		= new String[counter];
			String[] SetClCd 		= new String[counter];
			String[] SetArrNo 		= new String[counter];
			String[] SetClArrNo 	= new String[counter];
			String[] SetUpdateDate 	= new String[counter];
			String[] SetUpdateUser 	= new String[counter];
			String[] SetFixFg 		= new String[counter];
			String now_dtm = B100_DateTimeControl.dtmString2(B100_DateTimeControl.dtm()[1])[1];
			counter = 0;
			for(int i=0;i<ArrivalPlanHdRt.length;i++) {
				if(0==(int)ArrivalPlanHdRt[i][T100_ArrivalPlanHdRt.ColFixFg]) {
					SetClWh[counter] 		= TgtClWh;
					SetClCd[counter] 		= TgtClCd;
					SetArrNo[counter] 		= (String)ArrivalPlanHdRt[i][T100_ArrivalPlanHdRt.ColArrNo];
					SetClArrNo[counter] 	= "Cancel"+(String)ArrivalPlanHdRt[i][T100_ArrivalPlanHdRt.ColClArrNo];
					SetUpdateDate[counter] 	= now_dtm;
					SetUpdateUser[counter] 	= "(" + A00000_Main.LoginUserId + ")" + A00000_Main.LoginUserName;
					SetFixFg[counter] 		= "9";
					
					counter = counter+1;
				}else {
					
				}
			}
			
			Object[][] SetString = {
					 {"ClWh"		,"0"	,"1"	,"Key"	,SetClWh}				//担当倉庫
					,{"ClCd"		,"0"	,"1"	,"Key"	,SetClCd}				//荷主CD
					,{"ArrNo"		,"0"	,"1"	,"Key"	,SetArrNo}				//入荷予定NO（WMS採番）
					,{"ClArrNo"		,"0"	,"1"	,""		,SetClArrNo}			//荷主予定番号
					,{"UpdateDate"	,"0"	,"1"	,""		,SetUpdateDate}			//更新日
					,{"UpdateUser"	,"0"	,"1"	,""		,SetUpdateUser}			//更新者
					,{"FixFg"		,"0"	,"1"	,""		,SetFixFg}
					};
		
			String tgt_table = "WW0010ArrivalPlanHd";
			String TgtDB = "WANKO";
			int non_msg_fg = 1;
			
			A100_InsertUpdateSQL.InsertUpdateSomeRecord(SetString,tgt_table,TgtDB,non_msg_fg);
			if(null!=ErrMsg && 0<ErrMsg.size()) {
				ErrView(ErrMsg);
			}
		}else {
			JOptionPane.showMessageDialog(null, "Nullデーった突っ込んでんじゃねぇよ");
		}
	}
	private static void ErrView(ArrayList<String> ErrMsg) {
		//必要フォルダを生成する
		String FLD_PATH = A00000_Main.MainFLD+"\\ArrivalPlanControl";
		B100_FolderCheck.FLD_CHECK(FLD_PATH);
		FLD_PATH = A00000_Main.MainFLD+"\\ArrivalPlanControl\\ArrivalPlanCancel";
		B100_FolderCheck.FLD_CHECK(FLD_PATH);
		FLD_PATH = A00000_Main.MainFLD+"\\ArrivalPlanControl\\ArrivalPlanCancel\\Err";
		B100_FolderCheck.FLD_CHECK(FLD_PATH);
		FLD_PATH = A00000_Main.MainFLD+"\\ArrivalPlanControl\\ArrivalPlanCancel\\BK";
		B100_FolderCheck.FLD_CHECK(FLD_PATH);
		
		//ファイルに出力
		
		String NowDTM=B100_DateTimeControl.dtmString2(B100_DateTimeControl.dtm()[1])[1].replace(" ", "").replace("/", "").replace(":", "");
		
		FLD_PATH = A00000_Main.MainFLD+"\\ArrivalPlanControl\\ArrivalPlanCancel\\Err";
		
		String ErrFP = FLD_PATH+"\\ERR"+NowDTM+".txt";
		
		B100_TextExport.txt_exp2(ErrMsg, ErrFP,"UTF-8");
		
		//古いエラーデータ削除
		B100_FolderCheck.ToolsOldFileDeleteWhereFileName(FLD_PATH ,"ERR",B100_DefaultVariable.ErrTxtDelete);
		
		//ファイル開く
		File file = new File(ErrFP);
		Desktop desktop = Desktop.getDesktop();
		try {
			desktop.open(file);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	private static Object[][] ArrivalPlanHdRt(
			String TgtClWh,
			String TgtClCd,
			ArrayList<String> TgtArrNo
			){
		ArrayList<String> SearchClWh 			= new ArrayList<String>();		//ヘッダ担当倉庫
		ArrayList<String> SearchClCd 			= new ArrayList<String>();		//ヘッダ荷主CD
		ArrayList<String> SearchCLName01 		= new ArrayList<String>();		//ヘッダ荷主名
		ArrayList<String> SearchClGpCD 			= new ArrayList<String>();		//ヘッダ荷主グループCD
		ArrayList<String> SearchCLGpName01 		= new ArrayList<String>();		//ヘッダ荷主グループ名1
		ArrayList<String> SearchArrNo 			= TgtArrNo;						//ヘッダ入荷予定NO
		ArrayList<String> SearchClArrNo 		= new ArrayList<String>();		//ヘッダ荷主予定番号
		ArrayList<String> SearchPlanDateMin 	= new ArrayList<String>();		//ヘッダ入荷予定日最小
		ArrayList<String> SearchPlanDateMax 	= new ArrayList<String>();		//ヘッダ入荷予定日最大
		ArrayList<String> SearchHdActualDateMin = new ArrayList<String>();		//ヘッダ入荷実績日最小
		ArrayList<String> SearchHdActualDateMax	= new ArrayList<String>();		//ヘッダ入荷実績日最大
		ArrayList<String> SearchSpCd 			= new ArrayList<String>();		//ヘッダ仕入先CD
		ArrayList<String> SearchSpName 			= new ArrayList<String>();		//ヘッダ仕入先名
		ArrayList<String> SearchSpPost 			= new ArrayList<String>();		//ヘッダ仕入先郵便
		ArrayList<String> SearchSpAdd 			= new ArrayList<String>();		//ヘッダ仕入先住所
		ArrayList<String> SearchSpTel 			= new ArrayList<String>();		//ヘッダ仕入先電話
		ArrayList<String> SearchArCom 			= new ArrayList<String>();		//ヘッダコメント
		ArrayList<Integer> SearchFixFg 			= new ArrayList<Integer>();		//ヘッダ状況
				
		ArrayList<Integer> SearchMsNoMin 		= new ArrayList<Integer>();		//明細番号最小
		ArrayList<Integer> SearchMsNoMax 		= new ArrayList<Integer>();		//明細番号最大
		ArrayList<String> SearchItemCd 			= new ArrayList<String>();		//商品コード
		ArrayList<String> SearchClItemCd 		= new ArrayList<String>();		//荷主商品コード
		ArrayList<String> SearchJanCd 			= new ArrayList<String>();		//JANCD（バラ）
		ArrayList<String> SearchItemMdNo 		= new ArrayList<String>();		//商品型番
		ArrayList<String> SearchItemName 		= new ArrayList<String>();		//商品名
		ArrayList<String> Searchlot 			= new ArrayList<String>();		//ロット
		ArrayList<String> SearchExpDateMin 		= new ArrayList<String>();		//消費期限最小
		ArrayList<String> SearchExpDateMax 		= new ArrayList<String>();		//消費期限最大
		ArrayList<Integer> SearchPlanQtyMin 	= new ArrayList<Integer>();		//予定数量最小
		ArrayList<Integer> SearchPlanQtyMax 	= new ArrayList<Integer>();		//予定数量最大
		ArrayList<Integer> SearchActualQtyMin 	= new ArrayList<Integer>();		//実績数最小
		ArrayList<Integer> SearchActualQtyMax 	= new ArrayList<Integer>();		//実績数最大
		ArrayList<String> SearchActualDateMin 	= new ArrayList<String>();		//入荷日最小
		ArrayList<String> SearchActualDateMax 	= new ArrayList<String>();		//入荷日最大
		ArrayList<String> SearchCom 			= new ArrayList<String>();		//コメント
		ArrayList<String> SearchEntryDateMin 	= new ArrayList<String>();		//登録日最小
		ArrayList<String> SearchEntryDateMax 	= new ArrayList<String>();		//登録日最大
		ArrayList<String> SearchUpdateDateMin 	= new ArrayList<String>();		//更新日最小
		ArrayList<String> SearchUpdateDateMax 	= new ArrayList<String>();		//更新日最大
		ArrayList<String> SearchEntryUser 		= new ArrayList<String>();		//登録者
		ArrayList<String> SearchUpdateUser 		= new ArrayList<String>();		//更新者
		boolean AllSearch = false;
		
		SearchClWh.add(TgtClWh);
		SearchClCd.add(TgtClCd);
		
		Object[][] ArrivalPlanHdRt = T100_ArrivalPlanHdRt.ArrivalPlanHdRt(
				SearchClWh,				//ヘッダ担当倉庫
				SearchClCd,				//ヘッダ荷主CD
				SearchCLName01,			//ヘッダ荷主名
				SearchClGpCD,			//ヘッダ荷主グループCD
				SearchCLGpName01,		//ヘッダ荷主グループ名1
				SearchArrNo,			//ヘッダ入荷予定NO
				SearchClArrNo,			//ヘッダ荷主予定番号
				SearchPlanDateMin,		//ヘッダ入荷予定日最小
				SearchPlanDateMax,		//ヘッダ入荷予定日最大
				SearchHdActualDateMin,	//ヘッダ入荷実績日最小
				SearchHdActualDateMax,	//ヘッダ入荷実績日最大
				SearchSpCd,				//ヘッダ仕入先CD
				SearchSpName,			//ヘッダ仕入先名
				SearchSpPost,			//ヘッダ仕入先郵便
				SearchSpAdd,			//ヘッダ仕入先住所
				SearchSpTel,			//ヘッダ仕入先電話
				SearchArCom,			//ヘッダコメント
				SearchFixFg,			//ヘッダ状況
						
				SearchMsNoMin,			//明細番号最小
				SearchMsNoMax,			//明細番号最大
				SearchItemCd,			//商品コード
				SearchClItemCd,			//荷主商品コード
				SearchJanCd,			//JANCD（バラ）
				SearchItemMdNo,			//商品型番
				SearchItemName,			//商品名
				Searchlot,				//ロット
				SearchExpDateMin,		//消費期限最小
				SearchExpDateMax,		//消費期限最大
				SearchPlanQtyMin,		//予定数量最小
				SearchPlanQtyMax,		//予定数量最大
				SearchActualQtyMin,		//実績数最小
				SearchActualQtyMax,		//実績数最大
				SearchActualDateMin,	//入荷日最小
				SearchActualDateMax,	//入荷日最大
				SearchCom,				//コメント
				SearchEntryDateMin,		//登録日最小
				SearchEntryDateMax,		//登録日最大
				SearchUpdateDateMin,	//更新日最小
				SearchUpdateDateMax,	//更新日最大
				SearchEntryUser,		//登録者
				SearchUpdateUser,		//更新者
				AllSearch);
		
		return ArrivalPlanHdRt;
	}
	
	
	
}