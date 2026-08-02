import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class WT100_Arrival_31_ExcelEntryInPut{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	
	public static void ArrivalExcelEntry(int x,int y,String TgtFilePath) {
		A00000_Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		
		RenewFg = false;

		final JFrame main_fm = B100_FrameParts.FrameCreate(x,y,600,200,"Corgi00入荷実績登録（エクセル）WT100_Arrival_31_ExcelEntryInPut","NK");
		JLabel userinfo = B100_FrameParts.UserInfo();
		JButton exit_btn = B100_FrameParts.ExitBtn();
		JButton entry_btn = B100_FrameParts.EntryBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		main_fm.add(entry_btn);
		
		final String[] SheetList = B100_ExcelControl.ExcelSheetList(TgtFilePath);
		
		JLabel LB_SheetList				= B100_FrameParts.JLabelSet(		 20, 45,300,20,"登録するシートを選択してください"		,11,0);
		final JComboBox   TB_SheetList	= B100_FrameParts.JComboBoxSet( 	 20, 70,250,20,SheetList,11);	//シート一覧
		
		main_fm.add(LB_SheetList);
		main_fm.add(TB_SheetList);	//シート一覧
		
		main_fm.setVisible(true);
		RenewFg = true;
		
		//登録ボタン押下時の挙動
		entry_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				String SheetName = SheetList[TB_SheetList.getSelectedIndex()];
				
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				ArrivalExcelEntryMain(0,0,TgtFilePath,SheetName);
			}
		});
		
		//EXITボタン押下時の挙動
		exit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				WT100_ArrivalPlan_00_Search.ArrivalPlanSearch(0, 0);
			}
		});
	}
	
	public static void ArrivalExcelEntryMain(int x,int y,String TgtFilePath,String SheetName){
		//対象エクセルとシート名を受け取ってオブジェクト生成

		String[] Title = B100_RtObjectCreate.RtTitleName(WT100_Arrival_30_ExcelEntryOutPut.RtArrivalExcelOutPut());
		
		final Object[][] NeedCol = {
				 {Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetClWh]					,1	,WT100_Arrival_40_ObjectEntry.ColInClWh}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetClCd]					,1	,WT100_Arrival_40_ObjectEntry.ColInClCd}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetPlanDate]				,2	,WT100_Arrival_40_ObjectEntry.ColInPlanDate}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetSpCd]					,1	,WT100_Arrival_40_ObjectEntry.ColInSpCd}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetArrNo]					,1	,WT100_Arrival_40_ObjectEntry.ColInArrNo}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetMsNo]					,0	,WT100_Arrival_40_ObjectEntry.ColInMsNo}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetItemCd]				,1	,WT100_Arrival_40_ObjectEntry.ColInItemCd}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetlot]					,1	,WT100_Arrival_40_ObjectEntry.ColInlot}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetExpDate]				,2	,WT100_Arrival_40_ObjectEntry.ColInExpDate}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetPlanQty]				,0	,WT100_Arrival_40_ObjectEntry.ColInPlanQty}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColGetActualQty]			,0	,WT100_Arrival_40_ObjectEntry.ColInActualQty}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColEntryLot]					,1	,WT100_Arrival_40_ObjectEntry.ColInEntryLot}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColEntryExpDate]			,2	,WT100_Arrival_40_ObjectEntry.ColInEntryExpDate}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColEntryQty]					,0	,WT100_Arrival_40_ObjectEntry.ColInEntryQty}
				,{Title[WT100_Arrival_30_ExcelEntryOutPut.ColEntryStoreLoc]			,1	,WT100_Arrival_40_ObjectEntry.ColInEntryStoreLoc}
		};//フィールド名,フィールドタイプ(0:数値 1:文字列 2:日付時刻),基本のカラム(ゼロスタート),基本のカラム位置※カラム位置は後で読み込んだエクセルの1行目でフィールド名比較して更新されます
		
		String[] NeedColName = new String[NeedCol.length];
		for(int i=0;i<NeedCol.length;i++) {NeedColName[i] = (String)NeedCol[i][0];}
		
		//ヘッダ行取得⇒フィールド名判定
		boolean NeedColCheck	= B100_ExcelControl.ExcelToObjectFieldNameCheck(TgtFilePath,SheetName,NeedColName);
		//必要フィールドなければシート選択に戻る
		if(!NeedColCheck) {
			String Msg = "ヘッダ行で取込ファイルのレイアウト判別ができませんでした。\n確認しやがれください\n";
			for(int i=0;i<NeedCol.length;i++) {
				if(0<i&&0==i%5) {
					Msg = Msg + (String)NeedCol[i][0] + ",\n";
				}else {
					Msg = Msg + (String)NeedCol[i][0] + ",";
				}
			}
			Msg = Msg+"\nがヘッダに必要です";
			
			JOptionPane.showMessageDialog(null, Msg);
			ArrivalExcelEntry(x,y,TgtFilePath);
		}else {
			Object[][] GetOb = B100_ExcelControl.ExcelToObjectFieldName(TgtFilePath,SheetName,NeedCol);
			boolean KickFg = true;
			//入荷予定番号空白の行は対象外ついでに正規化
			int TgtRowCount = 0;
			for(int i=0;i<GetOb.length;i++) {
				for(int i01=0;i01<GetOb[i].length;i01++) {if(null==GetOb[i][i01]) {GetOb[i][i01]="";}}
				GetOb[i][WT100_Arrival_40_ObjectEntry.ColInClWh]				= B100_TextControl.Trim(		""+GetOb[i][WT100_Arrival_40_ObjectEntry.ColInClWh]);
				GetOb[i][WT100_Arrival_40_ObjectEntry.ColInClCd]				= B100_TextControl.Trim(		""+GetOb[i][WT100_Arrival_40_ObjectEntry.ColInClCd]);
				GetOb[i][WT100_Arrival_40_ObjectEntry.ColInPlanDate]			= B100_TextControl.TextToDate(	""+GetOb[i][WT100_Arrival_40_ObjectEntry.ColInPlanDate]);
				GetOb[i][WT100_Arrival_40_ObjectEntry.ColInSpCd]				= B100_TextControl.Trim(		""+GetOb[i][WT100_Arrival_40_ObjectEntry.ColInSpCd]);
				GetOb[i][WT100_Arrival_40_ObjectEntry.ColInArrNo]				= B100_TextControl.Trim(		""+GetOb[i][WT100_Arrival_40_ObjectEntry.ColInArrNo]);
				GetOb[i][WT100_Arrival_40_ObjectEntry.ColInMsNo]				= B100_TextControl.TextToInt(	""+GetOb[i][WT100_Arrival_40_ObjectEntry.ColInMsNo]);
				GetOb[i][WT100_Arrival_40_ObjectEntry.ColInItemCd]				= B100_TextControl.Trim(		""+GetOb[i][WT100_Arrival_40_ObjectEntry.ColInItemCd]);
				GetOb[i][WT100_Arrival_40_ObjectEntry.ColInlot]				= B100_TextControl.Trim(		""+GetOb[i][WT100_Arrival_40_ObjectEntry.ColInlot]);
				GetOb[i][WT100_Arrival_40_ObjectEntry.ColInExpDate]			= B100_TextControl.TextToDate(	""+GetOb[i][WT100_Arrival_40_ObjectEntry.ColInExpDate]);
				GetOb[i][WT100_Arrival_40_ObjectEntry.ColInPlanQty]			= B100_TextControl.TextToInt(	""+GetOb[i][WT100_Arrival_40_ObjectEntry.ColInPlanQty]);
				GetOb[i][WT100_Arrival_40_ObjectEntry.ColInActualQty]			= B100_TextControl.TextToInt(	""+GetOb[i][WT100_Arrival_40_ObjectEntry.ColInActualQty]);
				GetOb[i][WT100_Arrival_40_ObjectEntry.ColInEntryLot]			= B100_TextControl.Trim(		""+GetOb[i][WT100_Arrival_40_ObjectEntry.ColInEntryLot]);
				GetOb[i][WT100_Arrival_40_ObjectEntry.ColInEntryExpDate]		= B100_TextControl.TextToDate(	""+GetOb[i][WT100_Arrival_40_ObjectEntry.ColInEntryExpDate]);
				GetOb[i][WT100_Arrival_40_ObjectEntry.ColInEntryQty]			= B100_TextControl.TextToInt(	""+GetOb[i][WT100_Arrival_40_ObjectEntry.ColInEntryQty]);
				GetOb[i][WT100_Arrival_40_ObjectEntry.ColInEntryStoreLoc]	= B100_TextControl.Trim(		""+GetOb[i][WT100_Arrival_40_ObjectEntry.ColInEntryStoreLoc]);
				if(!"".equals((String)GetOb[i][WT100_Arrival_40_ObjectEntry.ColInArrNo])) {
					TgtRowCount	= TgtRowCount+1;
				}
			}
			Object[][] RtArrivalObjectEntryNeedColLayout = WT100_Arrival_40_ObjectEntry.RtArrivalObjectEntryNeedColLayout();
			Object[][] SetOb = new Object[TgtRowCount][RtArrivalObjectEntryNeedColLayout.length];
			TgtRowCount = 0;
			//ログイン中の荷主倉庫と異なる倉庫・荷主だった場合シート選択に戻る
			for(int i=0;i<GetOb.length;i++) {
				if(!"".equals((String)GetOb[i][WT100_Arrival_40_ObjectEntry.ColInArrNo])) {
					if(!A00000_Main.ClWh.equals(GetOb[i][WT100_Arrival_40_ObjectEntry.ColInClWh])
							|| !A00000_Main.ClCd.equals(GetOb[i][WT100_Arrival_40_ObjectEntry.ColInClCd])
							) {
						KickFg	= false;
					}
					for(int i01=0;i01<RtArrivalObjectEntryNeedColLayout.length;i01++) {
						SetOb[TgtRowCount][(int)RtArrivalObjectEntryNeedColLayout[i01][1]]	= "";
					}
					
					SetOb[TgtRowCount][WT100_Arrival_40_ObjectEntry.ColInClWh]				= GetOb[i][WT100_Arrival_40_ObjectEntry.ColInClWh];
					SetOb[TgtRowCount][WT100_Arrival_40_ObjectEntry.ColInClCd]				= GetOb[i][WT100_Arrival_40_ObjectEntry.ColInClCd];
					SetOb[TgtRowCount][WT100_Arrival_40_ObjectEntry.ColInPlanDate]		= GetOb[i][WT100_Arrival_40_ObjectEntry.ColInPlanDate];
					SetOb[TgtRowCount][WT100_Arrival_40_ObjectEntry.ColInSpCd]				= GetOb[i][WT100_Arrival_40_ObjectEntry.ColInSpCd];
					SetOb[TgtRowCount][WT100_Arrival_40_ObjectEntry.ColInArrNo]			= GetOb[i][WT100_Arrival_40_ObjectEntry.ColInArrNo];
					SetOb[TgtRowCount][WT100_Arrival_40_ObjectEntry.ColInMsNo]				= GetOb[i][WT100_Arrival_40_ObjectEntry.ColInMsNo];
					SetOb[TgtRowCount][WT100_Arrival_40_ObjectEntry.ColInItemCd]			= GetOb[i][WT100_Arrival_40_ObjectEntry.ColInItemCd];
					SetOb[TgtRowCount][WT100_Arrival_40_ObjectEntry.ColInlot]				= GetOb[i][WT100_Arrival_40_ObjectEntry.ColInlot];
					SetOb[TgtRowCount][WT100_Arrival_40_ObjectEntry.ColInExpDate]			= GetOb[i][WT100_Arrival_40_ObjectEntry.ColInExpDate];
					SetOb[TgtRowCount][WT100_Arrival_40_ObjectEntry.ColInPlanQty]			= GetOb[i][WT100_Arrival_40_ObjectEntry.ColInPlanQty];
					SetOb[TgtRowCount][WT100_Arrival_40_ObjectEntry.ColInActualQty]		= GetOb[i][WT100_Arrival_40_ObjectEntry.ColInActualQty];
					SetOb[TgtRowCount][WT100_Arrival_40_ObjectEntry.ColInEntryLot]		= GetOb[i][WT100_Arrival_40_ObjectEntry.ColInEntryLot];
					SetOb[TgtRowCount][WT100_Arrival_40_ObjectEntry.ColInEntryExpDate]	= GetOb[i][WT100_Arrival_40_ObjectEntry.ColInEntryExpDate];
					SetOb[TgtRowCount][WT100_Arrival_40_ObjectEntry.ColInEntryQty]		= GetOb[i][WT100_Arrival_40_ObjectEntry.ColInEntryQty];
					SetOb[TgtRowCount][WT100_Arrival_40_ObjectEntry.ColInEntryStoreLoc]	= GetOb[i][WT100_Arrival_40_ObjectEntry.ColInEntryStoreLoc];
					TgtRowCount	= TgtRowCount+1;
				}
			}
			
			if(KickFg) {
				//現在時点の入荷予定明細取得
				ArrayList<String> SearchArrNo = new ArrayList<String>();
				ArrayList<String> ErrMsg = new ArrayList<String>();
				for(int i=0;i<SetOb.length;i++) {
					SearchArrNo.add((String)SetOb[i][WT100_Arrival_40_ObjectEntry.ColInArrNo]);
				}
				Object[][] ArrivalPlanMsRt = ArrivalPlanMsRt(A00000_Main.ClWh,A00000_Main.ClCd,SearchArrNo);
				
				for(int i=0;i<GetOb.length;i++) {
					if(!"".equals((String)GetOb[i][WT100_Arrival_40_ObjectEntry.ColInArrNo])) {
						String[] TgtData = {(String)GetOb[i][WT100_Arrival_40_ObjectEntry.ColInArrNo],""+(int)GetOb[i][WT100_Arrival_40_ObjectEntry.ColInMsNo]};
						int[] KeyClm	= {T100_ArrivalPlanMsRt.ColArrNo,T100_ArrivalPlanMsRt.ColMsNo};
						int HitRow = B100_ArrayListControl.ObjectGetRowAnyKey(ArrivalPlanMsRt,TgtData,KeyClm,false);
						if(0>HitRow) {
							KickFg = false;
							ErrMsg.add("入荷予定番号:"+(String)GetOb[i][WT100_Arrival_40_ObjectEntry.ColInArrNo]+" 明細番号"+(int)GetOb[i][WT100_Arrival_40_ObjectEntry.ColInMsNo]+"が入荷予定にないです。削除されたか入荷済みじゃね？");
						}else {
							//現在の入荷予定と内容変更があればエラー
							if(((String)ArrivalPlanMsRt[HitRow][T100_ArrivalPlanMsRt.ColItemCd]).equals((String)GetOb[i][WT100_Arrival_40_ObjectEntry.ColInItemCd])
									&& ((String)ArrivalPlanMsRt[HitRow][T100_ArrivalPlanMsRt.Collot]).equals((String)GetOb[i][WT100_Arrival_40_ObjectEntry.ColInlot])
									&& ((String)ArrivalPlanMsRt[HitRow][T100_ArrivalPlanMsRt.ColExpDate]).equals((String)GetOb[i][WT100_Arrival_40_ObjectEntry.ColInExpDate])
									&& (int)ArrivalPlanMsRt[HitRow][T100_ArrivalPlanMsRt.ColPlanQty]==(int)GetOb[i][WT100_Arrival_40_ObjectEntry.ColInPlanQty]
									&& (int)ArrivalPlanMsRt[HitRow][T100_ArrivalPlanMsRt.ColActualQty]==(int)GetOb[i][WT100_Arrival_40_ObjectEntry.ColInActualQty]
									) {
							}else {
								KickFg = false;
								ErrMsg.add("入荷予定番号:"+(String)GetOb[i][WT100_Arrival_40_ObjectEntry.ColInArrNo]+" 明細番号"+(int)GetOb[i][WT100_Arrival_40_ObjectEntry.ColInMsNo]+"が現在の予定と異なります。予定変更されてね？");
							}
						}
					}
				}
				if(null!=ErrMsg&&0<ErrMsg.size()) {
					ErrView(ErrMsg,"予定エクセルエラー");
				}
			}else {
				String Msg = "ログイン中の荷主と異なるエクセルを取り込もうとしています";
				JOptionPane.showMessageDialog(null, Msg);
				ArrivalExcelEntry(x,y,TgtFilePath);
			}
			if(KickFg) {
				WT100_Arrival_40_ObjectEntry.ArrivalObjectEntryMain(0,0,TgtFilePath,SetOb);
			}
		}
	}
	private static Object[][] ArrivalPlanMsRt(String TgtClWh,String TgtClCd,ArrayList<String> SearchArrNo){
		if(null==TgtClWh||"".equals(TgtClWh)) {TgtClWh=A00000_Main.ClWh;}
		if(null==TgtClCd||"".equals(TgtClCd)) {TgtClCd=A00000_Main.ClCd;}
		ArrayList<String> SearchClWh			= new ArrayList<String>();		//ヘッダ担当倉庫
		ArrayList<String> SearchClCd			= new ArrayList<String>();		//ヘッダ荷主CD
		ArrayList<String> SearchCLName01		= new ArrayList<String>();		//ヘッダ荷主名
		ArrayList<String> SearchClGpCD			= new ArrayList<String>();		//ヘッダ荷主グループCD
		ArrayList<String> SearchCLGpName01		= new ArrayList<String>();		//ヘッダ荷主グループ標記名
		//ArrayList<String> SearchArrNo			= new ArrayList<String>();		//ヘッダ入荷予定NO
		ArrayList<String> SearchClArrNo			= new ArrayList<String>();		//ヘッダ荷主予定番号
		ArrayList<String> SearchPlanDateMin		= new ArrayList<String>();		//ヘッダ入荷予定日
		ArrayList<String> SearchPlanDateMax		= new ArrayList<String>();		//ヘッダ入荷予定日
		ArrayList<String> SearchHdActualDateMin	= new ArrayList<String>();		//ヘッダ入荷実績日
		ArrayList<String> SearchHdActualDateMax	= new ArrayList<String>();		//ヘッダ入荷実績日
		ArrayList<String> SearchSpCd			= new ArrayList<String>();		//ヘッダ仕入先CD
		ArrayList<String> SearchSpName			= new ArrayList<String>();		//ヘッダ仕入先名
		ArrayList<String> SearchSpPost			= new ArrayList<String>();		//ヘッダ仕入先郵便
		ArrayList<String> SearchSpAdd			= new ArrayList<String>();		//ヘッダ仕入先住所
		ArrayList<String> SearchSpTel			= new ArrayList<String>();		//ヘッダ仕入先電話
		ArrayList<String> SearchArCom			= new ArrayList<String>();		//ヘッダコメント
		ArrayList<Integer> SearchFixFg			= new ArrayList<Integer>();		//ヘッダ状況
				
		ArrayList<Integer> SearchMsNoMin		= new ArrayList<Integer>();		//明細番号最小
		ArrayList<Integer> SearchMsNoMax		= new ArrayList<Integer>();		//明細番号最大
		ArrayList<String> SearchItemCd			= new ArrayList<String>();		//商品コード
		ArrayList<String> SearchClItemCd		= new ArrayList<String>();		//荷主商品コード
		ArrayList<String> SearchJanCd			= new ArrayList<String>();		//JANCD（バラ）
		ArrayList<String> SearchItemMdNo		= new ArrayList<String>();		//商品型番
		ArrayList<String> SearchItemName		= new ArrayList<String>();		//商品名
		ArrayList<String> Searchlot				= new ArrayList<String>();		//ロット
		ArrayList<String> SearchExpDateMin		= new ArrayList<String>();		//消費期限最小
		ArrayList<String> SearchExpDateMax		= new ArrayList<String>();		//消費期限最大
		ArrayList<Integer> SearchPlanQtyMin		= new ArrayList<Integer>();		//予定数量最小
		ArrayList<Integer> SearchPlanQtyMax		= new ArrayList<Integer>();		//予定数量最大
		ArrayList<Integer> SearchActualQtyMin	= new ArrayList<Integer>();		//実績数
		ArrayList<Integer> SearchActualQtyMax	= new ArrayList<Integer>();		//実績数
		ArrayList<String> SearchActualDateMin	= new ArrayList<String>();		//入荷日
		ArrayList<String> SearchActualDateMax	= new ArrayList<String>();		//入荷日
		ArrayList<String> SearchCom				= new ArrayList<String>();		//コメント
		ArrayList<String> SearchEntryDateMin	= new ArrayList<String>();		//登録日
		ArrayList<String> SearchEntryDateMax	= new ArrayList<String>();		//登録日
		ArrayList<String> SearchUpdateDateMin	= new ArrayList<String>();		//更新日
		ArrayList<String> SearchUpdateDateMax	= new ArrayList<String>();		//更新日
		ArrayList<String> SearchEntryUser		= new ArrayList<String>();		//登録者
		ArrayList<String> SearchUpdateUser		= new ArrayList<String>();		//更新者
		boolean AllSearch = false;
		if(null!=SearchArrNo && 0<SearchArrNo.size()) {
			SearchClWh.add(TgtClWh);
			SearchClCd.add(TgtClCd);
			SearchFixFg.add(0);
			SearchFixFg.add(2);
		}
		
		Object[][] ArrivalPlanMsRt	= T100_ArrivalPlanMsRt.ArrivalPlanMsRt(
				SearchClWh,					//ヘッダ担当倉庫
				SearchClCd,					//ヘッダ荷主CD
				SearchCLName01,				//ヘッダ荷主名
				SearchClGpCD,				//ヘッダ荷主グループCD
				SearchCLGpName01,			//ヘッダ荷主グループ標記名
				SearchArrNo,				//ヘッダ入荷予定NO
				SearchClArrNo,				//ヘッダ荷主予定番号
				SearchPlanDateMin,			//ヘッダ入荷予定日
				SearchPlanDateMax,			//ヘッダ入荷予定日
				SearchHdActualDateMin,		//ヘッダ入荷実績日
				SearchHdActualDateMax,		//ヘッダ入荷実績日
				SearchSpCd,					//ヘッダ仕入先CD
				SearchSpName,				//ヘッダ仕入先名
				SearchSpPost,				//ヘッダ仕入先郵便
				SearchSpAdd,				//ヘッダ仕入先住所
				SearchSpTel,				//ヘッダ仕入先電話
				SearchArCom,				//ヘッダコメント
				SearchFixFg,				//ヘッダ状況
						
				SearchMsNoMin,				//明細番号最小
				SearchMsNoMax,				//明細番号最大
				SearchItemCd,				//商品コード
				SearchClItemCd,				//荷主商品コード
				SearchJanCd,				//JANCD（バラ）
				SearchItemMdNo,				//商品型番
				SearchItemName,				//商品名
				Searchlot,					//ロット
				SearchExpDateMin,			//消費期限最小
				SearchExpDateMax,			//消費期限最大
				SearchPlanQtyMin,			//予定数量最小
				SearchPlanQtyMax,			//予定数量最大
				SearchActualQtyMin,			//実績数
				SearchActualQtyMax,			//実績数
				SearchActualDateMin,		//入荷日
				SearchActualDateMax,		//入荷日
				SearchCom,					//コメント
				SearchEntryDateMin,			//登録日
				SearchEntryDateMax,			//登録日
				SearchUpdateDateMin,		//更新日
				SearchUpdateDateMax,		//更新日
				SearchEntryUser,			//登録者
				SearchUpdateUser,			//更新者
				AllSearch);
		return ArrivalPlanMsRt;
	}
	
	private static void ErrView(ArrayList<String>ErrMsg,String ErrTitle) {
		//必要フォルダを生成する
		String FLD_PATH = A00000_Main.MainFLD+"\\ArrivalPlan";
		B100_FolderCheck.FLD_CHECK(FLD_PATH);
		FLD_PATH = A00000_Main.MainFLD+"\\ArrivalPlan\\ArrivalPlanExcelentry";
		B100_FolderCheck.FLD_CHECK(FLD_PATH);
		FLD_PATH = A00000_Main.MainFLD+"\\ArrivalPlan\\ArrivalPlanExcelentry\\Err";
		B100_FolderCheck.FLD_CHECK(FLD_PATH);
		FLD_PATH = A00000_Main.MainFLD+"\\ArrivalPlan\\ArrivalPlanExcelentry\\BK";
		B100_FolderCheck.FLD_CHECK(FLD_PATH);
		
		//ファイルに出力
		String NowDTM=B100_DateTimeControl.dtmString2(B100_DateTimeControl.dtm()[1])[1].replace(" ", "").replace("/", "").replace(":", "");
		
		FLD_PATH = A00000_Main.MainFLD+"\\ArrivalPlan\\ArrivalPlanExcelentry\\Err";
		
		String ErrFP = FLD_PATH+"\\ERR"+ErrTitle+NowDTM+".txt";
		
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
}