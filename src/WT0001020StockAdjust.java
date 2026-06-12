import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class WT0001020StockAdjust{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	public static void StockAdjust(int x,int y,String TgtWhCd,String TgtClCd,String TgtLoc,String TgtItemCd,String TgtLot,String TgtExpdate,String TgtActualDate) {
		A00000Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		
		/******************************************
			現在の在庫情報を取得する
		******************************************/
		
		if(null==TgtWhCd		) {TgtWhCd			="";}
		if(null==TgtClCd		) {TgtClCd			="";}
		if(null==TgtLoc			) {TgtLoc			="";}
		if(null==TgtItemCd		) {TgtItemCd		="";}
		if(null==TgtLot			) {TgtLot			="";}
		if(null==TgtExpdate		) {TgtExpdate		="";}
		if(null==TgtActualDate	) {TgtActualDate	="";}
		
		if("".equals(TgtWhCd)) {TgtWhCd	=A00000Main.ClWh;}
		if("".equals(TgtClCd)) {TgtClCd	=A00000Main.ClCd;}
		
		String GetClCd			= TgtClCd;		//荷主コード
		String GetWhCd			= TgtWhCd;		//倉庫コード
		String GetLoc			= TgtLoc;		//ロケーション
		String GetItemCd		= TgtItemCd;	//商品コード
		String GetLot			= TgtLot;		//ロット
		String GetExpdate		= TgtExpdate;	//消費期限
		String GetActualDate	= TgtActualDate;//入荷実績日
		int GetQty				= 0;			//数量
		int GetShipPlanQty		= 0;			//引当済数
		int GetPossibleQty		= 0;			//出荷可能数
		String GetItemName		= "";			//商品名
		String GetClItemCd		= "";			//荷主商品コード
		String GetJanCd			= "";			//ソースマーク_BCD（バラ）
		String GetItemMdNo		= "";			//商品型番
		String GetEntryDate		= "";			//登録日時
		String GetUpdateDate	= "";			//更新日時
		String GetEntryUser		= "";			//登録者
		String GetUpdateUser	= "";			//更新者
		
		if(!"".equals(TgtWhCd)
				&&!"".equals(TgtClCd)
				&&!"".equals(TgtLoc)
				&&!"".equals(TgtItemCd)
				&&!"".equals(TgtLot)
				&&!"".equals(TgtExpdate)
				&&!"".equals(TgtActualDate)
				) {
		
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
			boolean LocExactMatch = true;				//ロケーション完全一致
			boolean AllSearch = false;
			
			if(!"".equals(TgtWhCd		)) {SearchWhCd.add(				TgtWhCd);}
			if(!"".equals(TgtClCd		)) {SearchClCd.add(				TgtClCd);}
			if(!"".equals(TgtLoc		)) {SearchLoc.add(				TgtLoc);}
			if(!"".equals(TgtItemCd		)) {SearchItemCd.add(			TgtItemCd);}
			if(!"".equals(TgtLot		)) {SearchLot.add(				TgtLot);}
			if(!"".equals(TgtExpdate	)) {SearchExpdateMin.add(		TgtExpdate);}
			if(!"".equals(TgtActualDate	)) {SearchActualDateMin.add(	TgtActualDate);}
			if(!"".equals(TgtExpdate	)) {SearchExpdateMax.add(		TgtExpdate);}
			if(!"".equals(TgtActualDate	)) {SearchActualDateMax.add(	TgtActualDate);}
			
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
			if(1==StrokRt.length) {
				GetClCd			= (String)StrokRt[0][T00010StrokRt.ColClCd];			//荷主コード
				GetWhCd			= (String)StrokRt[0][T00010StrokRt.ColWhCd];			//倉庫コード
				GetLoc			= (String)StrokRt[0][T00010StrokRt.ColLoc];			//ロケーション
				GetItemCd		= (String)StrokRt[0][T00010StrokRt.ColItemCd];			//商品コード
				GetLot			= (String)StrokRt[0][T00010StrokRt.ColLot];			//ロット
				GetExpdate		= (String)StrokRt[0][T00010StrokRt.ColExpdate];		//消費期限
				GetActualDate	= (String)StrokRt[0][T00010StrokRt.ColActualDate];	//入荷実績日
				GetQty			= (int)StrokRt[0][T00010StrokRt.ColQty];				//数量
				GetShipPlanQty	= (int)StrokRt[0][T00010StrokRt.ColShipPlanQty];		//引当済数
				GetPossibleQty	= (int)StrokRt[0][T00010StrokRt.ColPossibleQty];		//出荷可能数
				GetItemName		= (String)StrokRt[0][T00010StrokRt.ColItemName];		//商品名
				GetClItemCd		= (String)StrokRt[0][T00010StrokRt.ColClItemCd];		//荷主商品コード
				GetJanCd		= (String)StrokRt[0][T00010StrokRt.ColJanCd];			//ソースマーク_BCD（バラ）
				GetItemMdNo		= (String)StrokRt[0][T00010StrokRt.ColItemMdNo];		//商品型番
				GetEntryDate	= (String)StrokRt[0][T00010StrokRt.ColEntryDate];		//登録日時
				GetUpdateDate	= (String)StrokRt[0][T00010StrokRt.ColUpdateDate];	//更新日時
				GetEntryUser	= (String)StrokRt[0][T00010StrokRt.ColEntryUser];		//登録者
				GetUpdateUser	= (String)StrokRt[0][T00010StrokRt.ColUpdateUser];	//更新者
			}
		}
		/******************************************
			現在の在庫情報を取得するここまで
		******************************************/
		
		final JFrame main_fm = B00110FrameParts.FrameCreate(x,y,1200,750,"Corgi00在庫調整","ZK");
		JLabel userinfo = B00110FrameParts.UserInfo();
		JButton exit_btn = B00110FrameParts.ExitBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		
		
		RenewFg = true;
		main_fm.setVisible(true);
		
		//EXITボタン押下時の挙動
		exit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				WT0001010StockSearch.StockSearch(0,0);
			}
		});
	}
	
	private static int GetSelectIndex(String[] SelectList,String TgtData ) {
		int rt = 0;
		for(int i=0;i<SelectList.length;i++) {
			if(TgtData.equals(SelectList[i])) {
				rt	= i;
				i	= SelectList.length+1;
			}
		}
		return rt;
	}
}