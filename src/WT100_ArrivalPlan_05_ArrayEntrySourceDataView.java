import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class WT100_ArrivalPlan_05_ArrayEntrySourceDataView{
	//入荷予定データ登録用データを受け取って表示する
	
	static final int ColClWh		= 0;	//担当倉庫
	static final int ColClCd		= 1;	//荷主CD
	static final int ColSPCd		= 2;	//仕入先CD
	static final int ColPlanDate	= 3;	//入荷予定日
	static final int ColClArrNo	= 4;	//荷主予定番号
	static final int ColClItemCd	= 5;	//荷主商品CD
	static final int ColLot		= 6;	//ロット
	static final int ColExpDate	= 7;	//賞味期限
	static final int ColPlanQty	= 8;	//数量
	static final int ColHdCom01	= 9;	//ヘッダコメント01
	static final int ColHdCom02	=10;	//ヘッダコメント02
	static final int ColHdCom03	=11;	//ヘッダコメント03
	static final int ColMsCom01	=12;	//明細コメント01
	static final int ColMsCom02	=13;	//明細コメント02
	
	
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	
	public static Object[][] RtArrivalPlanArrayEntrySourceDataView() {
		//投入データ定義
		Object[][] RtArrivapPlanArrayEntry= {
				 {"ClWh"			,ColClWh		,"String"	,"担当倉庫"			,"Key"	}
				,{"ClCd"			,ColClCd		,"String"	,"荷主CD"			,"Key"	}
				,{"SPCd"			,ColSPCd		,"String"	,"仕入先CD"			,"Key"	}
				,{"PlanDate"		,ColPlanDate	,"String"	,"入荷予定日"		,"Key"	}
				,{"ClArrNo"			,ColClArrNo	,"String"	,"荷主予定番号"		,"Key"	}
				,{"ClItemCd"		,ColClItemCd	,"String"	,"荷主商品CD"		,""		}
				,{"Lot"				,ColLot		,"String"	,"ロット"			,""		}
				,{"ExpDate"			,ColExpDate	,"DateTime"	,"賞味期限"			,""		}
				,{"PlanQty"			,ColPlanQty	,"int"		,"数量"				,""		}
				,{"HdCom01"			,ColHdCom01	,"DateTime"	,"ヘッダコメント01"	,""		}
				,{"HdCom02"			,ColHdCom02	,"DateTime"	,"ヘッダコメント02"	,""		}
				,{"HdCom03"			,ColHdCom03	,"DateTime"	,"ヘッダコメント03"	,""		}
				,{"MsCom01"			,ColMsCom01	,"DateTime"	,"明細コメント01"	,""		}
				,{"MsCom02"			,ColMsCom02	,"DateTime"	,"明細コメント02"	,""		}
				};
		return RtArrivapPlanArrayEntry;
	}
	public static void ArrivalPlanArrayEntrySourceDataView(int x,int y,String[][] EntryData) {
		A00000_Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		
		final JFrame main_fm = B100_FrameParts.FrameCreate(x,y,900,750,"Corgi00入荷予定取込（データ内容確認）　WT100_ArrivalPlan_05_ArrayEntrySourceDataView","NK");
		JLabel userinfo = B100_FrameParts.UserInfo();
		JButton exit_btn = B100_FrameParts.ExitBtn();
		JButton entry_btn = B100_FrameParts.EntryBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		main_fm.add(entry_btn);
		
		JLabel LB_Msg 	= B100_FrameParts.JLabelSet(  0, 50,300,20,"以下のデータを取込もうとしています"	,11,1);
		main_fm.add(LB_Msg);
		
		Object[][] RtArrivalPlanArrayEntry = WT100_ArrivalPlan_05_ArrayEntrySourceDataView.RtArrivalPlanArrayEntrySourceDataView();
		
		String[] columnNames01 = new String[RtArrivalPlanArrayEntry.length+1];
		
		columnNames01[0] = "Fg";
		for(int i=0;i<RtArrivalPlanArrayEntry.length;i++) {
			columnNames01[1+i] = ""+RtArrivalPlanArrayEntry[i][3];
		}
		
		//編集可能カラムの指定
		B100_TableControl.RenewTgt = new int[1];
		B100_TableControl.RenewTgt[0] = 0;

		final DefaultTableModel MainFmTableModel = new B100_TableControl.MyTableModel01(columnNames01,0);
		
		final JTable tb01 = new JTable(MainFmTableModel);
		tb01.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tb01.setRowHeight(20*A00000_Main.Mul/A00000_Main.Div);
		tb01.setFont(new Font(A00000_Main.DefaultFont, Font.PLAIN, 12*A00000_Main.Mul/A00000_Main.Div));
		
		DefaultTableColumnModel columnModel01
		= (DefaultTableColumnModel)tb01.getColumnModel();
		
		//列幅初期設定 表示位置設定
		TableColumn column = null;
		
		column = columnModel01.getColumn( 0);	column.setPreferredWidth( 30*A00000_Main.Mul/A00000_Main.Div);	//FG
		
		for(int i=0;i<RtArrivalPlanArrayEntry.length;i++) {
			if("int".equals((String)RtArrivalPlanArrayEntry[i][2])||"float".equals((String)RtArrivalPlanArrayEntry[i][2])) {
				column = columnModel01.getColumn(1+i);	column.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.rightCellRenderer());
			}else {
				column = columnModel01.getColumn(1+i);	column.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.leftCellRenderer());
			}
		}
		//スクロール用設定
		JScrollPane scpn01 = B100_FrameParts.JScrollPaneSet(10,75,860,500,tb01);
		main_fm.add(scpn01);
		
		for(int i=0;i<EntryData.length;i++) {
			Object[] SetOb = new Object[WT100_ArrivalPlan_05_ArrayEntrySourceDataView.RtArrivalPlanArrayEntrySourceDataView().length+1];
			SetOb[0] = false;
			SetOb[ColClWh+1]		= B100_TextControl.Trim(EntryData[i][ColClWh]);		//担当倉庫
			SetOb[ColClCd+1]		= B100_TextControl.Trim(EntryData[i][ColClCd]);		//荷主CD
			SetOb[ColSPCd+1]		= B100_TextControl.Trim(EntryData[i][ColSPCd]);		//仕入先CD
			SetOb[ColPlanDate+1]	= B100_TextControl.Trim(EntryData[i][ColPlanDate]);	//入荷予定日
			SetOb[ColClArrNo+1]	= B100_TextControl.Trim(EntryData[i][ColClArrNo]);	//荷主予定番号
			SetOb[ColClItemCd+1]	= B100_TextControl.Trim(EntryData[i][ColClItemCd]);	//荷主商品CD
			SetOb[ColLot+1]		= B100_TextControl.Trim(EntryData[i][ColLot]);		//ロット
			SetOb[ColExpDate+1]	= B100_TextControl.Trim(EntryData[i][ColExpDate]);	//賞味期限
			SetOb[ColPlanQty+1]	= B100_TextControl.Trim(EntryData[i][ColPlanQty]);	//数量
			SetOb[ColHdCom01+1]	= B100_TextControl.Trim(EntryData[i][ColHdCom01]);	//ヘッダコメント01
			SetOb[ColHdCom02+1]	= B100_TextControl.Trim(EntryData[i][ColHdCom02]);	//ヘッダコメント02
			SetOb[ColHdCom03+1]	= B100_TextControl.Trim(EntryData[i][ColHdCom03]);	//ヘッダコメント03
			SetOb[ColMsCom01+1]	= B100_TextControl.Trim(EntryData[i][ColMsCom01]);	//明細コメント01
			SetOb[ColMsCom02+1]	= B100_TextControl.Trim(EntryData[i][ColMsCom02]);	//明細コメント02
			
			SetOb[ColExpDate+1]	= B100_DateTimeControl.DateFormat((String)SetOb[ColExpDate+1]);		//賞味期限
			SetOb[ColPlanQty+1]	= B100_TextControl.num_only_String02((String)SetOb[ColPlanQty+1]);	//数量
			
			if("".equals((String)SetOb[ColPlanQty+1])) {SetOb[ColPlanQty+1]="0";}
			
			float WFT = Float.parseFloat((String)SetOb[ColPlanQty+1]);
			int Qty = (int)WFT;
			if(0>Qty) {
				SetOb[ColPlanQty+1]="0";
			}else {
				SetOb[ColPlanQty+1]=""+Qty;
			}
			
			if(!"".equals((String)SetOb[ColClItemCd+1])&&0<Integer.parseInt((String)SetOb[ColPlanQty+1])) {
				MainFmTableModel.addRow(SetOb);
			}
		}
		RenewFg = true;
		main_fm.setVisible(true);
		
		//登録ボタン押下時の挙動
		entry_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				String[] TableCol = B100_TableControl.TableFieldNameRt(tb01);
				int RowCount = MainFmTableModel.getRowCount();
				Object[][] CheckOb = new Object[RowCount][TableCol.length-1];
				for(int i=0;i<RowCount;i++) {
					for(int i01=1;i01<TableCol.length;i01++) {
						CheckOb[i][i01-1] = ""+MainFmTableModel.getValueAt(i, i01);
					}
				}
				CheckOb = CheckObTrim(CheckOb);
				ArrayList<String> ErrMsg = ErrCheck(CheckOb);
				
				if(null!=ErrMsg && 0<ErrMsg.size()) {
					ErrView(ErrMsg);
				}else {
					Object[][] SetObRt	= SetObRt(CheckOb);
					
					JOptionPane.showMessageDialog(null, "エラーチェック完了\n引き続き登録内容表示します\n内容確認して登録してください");
					
					SetX=main_fm.getX();
					SetY=main_fm.getY();
	
					main_fm.setVisible(false);
					main_fm.dispose();
					WT100_ArrivalPlan_06_ArrayEntrySetDataView.ArrivalPlanArrayEntrySetDataView(SetX+10,SetY+10,SetObRt);
				}
			}
		});
		
		//チェックボックス操作時の挙動
		MainFmTableModel.addTableModelListener(new TableModelListener(){
			public void tableChanged(TableModelEvent e){
				if(RenewFg) {
					RenewFg = false;
					int row_count = tb01.getRowCount();
					Boolean setBL=Boolean.valueOf(false);
					for(int i=0;i<row_count;i++){
						if(i!=e.getFirstRow()){
							MainFmTableModel.setValueAt(setBL, i, 0);
						}else {
	
						}
					}
					RenewFg = true;
				}
			}
		});
		
		//EXITボタン押下時の挙動
		exit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				WT100_ArrivalPlan_00_Search.ArrivalPlanSearch(0,0);
			}
		});
	}
	
	public static ArrayList<String> ErrCheck(Object[][] CheckOb){
		ArrayList<String> ErrMsg = new ArrayList<String>();
		
		//商品変換チェック/仕入先CDチェック用に商品変換マスタ情報取得検索条件
		ArrayList<String> SearchSPCd = new ArrayList<String>();
		ArrayList<String> ClItemCd=new ArrayList<String>();
		
		for(int i=0;i<CheckOb.length;i++) {
			if(!"".equals((String)CheckOb[i][ColClItemCd])&&0<(int)CheckOb[i][ColPlanQty]) {
				SearchSPCd.add((String)CheckOb[i][ColSPCd]);
				ClItemCd.add((String)CheckOb[i][ColClItemCd]);
			}
		}
		
		//仕入先マスタ情報取得
		Object[][] SupplierRt= SupplierRt(A00000_Main.ClWh,A00000_Main.ClCd,SearchSPCd);
		
		//商品変換チェック用に商品変換マスタ情報取得
		Object[][] ItemComversion = WMTools100_ItemComversion.ItemComversion(A00000_Main.ClCd,ClItemCd);
		
		//マスタエラーチェック
		for(int i=0;i<CheckOb.length;i++) {
			if(!"".equals((String)CheckOb[i][ColClItemCd])&&0<(int)CheckOb[i][ColPlanQty]) {
				if(!A00000_Main.ClWh.equals((String)CheckOb[i][ColClWh])) {
					int wint = i+1;
					ErrMsg.add(wint+"行目エラー:倉庫コード("+(String)CheckOb[i][ColClWh]+")は現在ログイン中の倉庫と異なります");
				}
				if(!A00000_Main.ClCd.equals((String)CheckOb[i][ColClCd])) {
					int wint = i+1;
					ErrMsg.add(wint+"行目エラー:荷主コード("+(String)CheckOb[i][ColClCd]+")は現在ログイン中の荷主と異なります");
				}
				if("".equals((String)CheckOb[i][ColPlanDate])) {
					int wint = i+1;
					ErrMsg.add(wint+"行目エラー:入荷予定日が不正です");
				}
				
				boolean UnHitFg = true;
				for(int i01=0;i01<SupplierRt.length;i01++) {
					if(((String)CheckOb[i][ColSPCd]).equals((String)SupplierRt[i01][M100_SupplierRt.ColSPCd])) {
						UnHitFg = false;
						i01=SupplierRt.length+1;
					}
				}
				if(UnHitFg) {
					int wint = i+1;
					ErrMsg.add(wint+"行目エラー:仕入先コード("+(String)CheckOb[i][ColSPCd]+")は未登録の仕入れ先コードです");
				}
				
				UnHitFg = true;
				for(int i01=0;i01<ItemComversion.length;i01++) {
					if(((String)CheckOb[i][ColClItemCd]).equals((String)ItemComversion[i01][WMTools100_ItemComversion.ColClIemCd])) {
						if(!"".equals((String)ItemComversion[i01][WMTools100_ItemComversion.ColItemCd])) {
							UnHitFg = false;
						}
						i01=ItemComversion.length+1;
					}
				}
				if(UnHitFg) {
					int wint = i+1;
					ErrMsg.add(wint+"行目エラー:荷主商品コード("+(String)CheckOb[i][ColClItemCd]+")は商品変換マスタ未設定です");
				}
			}
		}
		return ErrMsg;
	}
	
	private static Object[][] SetObRt(Object[][]CheckOb){
		int EntryCount = 0;
		
		//商品変換チェック/仕入先CDチェック用に商品変換マスタ情報取得検索条件　ついでに対象件数カウント
		ArrayList<String> SearchSPCd = new ArrayList<String>();
		ArrayList<String> ClItemCd=new ArrayList<String>();
		
		for(int i=0;i<CheckOb.length;i++) {
			if(!"".equals((String)CheckOb[i][ColClItemCd])&&0<(int)CheckOb[i][ColPlanQty]) {
				SearchSPCd.add((String)CheckOb[i][ColSPCd]);
				ClItemCd.add((String)CheckOb[i][ColClItemCd]);
				EntryCount = EntryCount+1;
			}
		}
		
		//仕入先マスタ情報取得
		Object[][] SupplierRt= SupplierRt(A00000_Main.ClWh,A00000_Main.ClCd,SearchSPCd);
		
		//商品変換チェック用に商品変換マスタ情報取得
		Object[][] ItemComversion = WMTools100_ItemComversion.ItemComversion(A00000_Main.ClCd,ClItemCd);
		
		Object[][] SetOb = new Object[EntryCount][WT100_ArrivalPlan_06_ArrayEntrySetDataView.RtSetDataDefinition().length];
		EntryCount = 0;
		
		//商品マスタ取得対象商品CD
		ArrayList<String> TgtItemCd = new ArrayList<String>();
		
		for(int i=0;i<CheckOb.length;i++) {
			if(!"".equals((String)CheckOb[i][ColClItemCd])&&0<(int)CheckOb[i][ColPlanQty]) {
				
				SetOb[EntryCount][WT100_ArrivalPlan_06_ArrayEntrySetDataView.ColSetHdClWh]		= (String)CheckOb[i][ColClWh];		//ヘッダ担当倉庫
				SetOb[EntryCount][WT100_ArrivalPlan_06_ArrayEntrySetDataView.ColSetHdClCd]		= (String)CheckOb[i][ColClCd];		//ヘッダ荷主CD
				SetOb[EntryCount][WT100_ArrivalPlan_06_ArrayEntrySetDataView.ColSetHdArrNo]		= "";	//ヘッダ入荷予定NO
				SetOb[EntryCount][WT100_ArrivalPlan_06_ArrayEntrySetDataView.ColSetHdClArrNo]		= (String)CheckOb[i][ColClArrNo];	//ヘッダ荷主予定番号
				SetOb[EntryCount][WT100_ArrivalPlan_06_ArrayEntrySetDataView.ColSetHdPlanDate]	= (String)CheckOb[i][ColPlanDate];	//ヘッダ入荷予定日
				SetOb[EntryCount][WT100_ArrivalPlan_06_ArrayEntrySetDataView.ColSetHdActualDate]	= "";	//ヘッダ入荷実績日
				SetOb[EntryCount][WT100_ArrivalPlan_06_ArrayEntrySetDataView.ColSetHdSpCd]		= (String)CheckOb[i][ColSPCd];		//ヘッダ仕入先CD"}
				SetOb[EntryCount][WT100_ArrivalPlan_06_ArrayEntrySetDataView.ColSetHdSpName01]	= "";	//ヘッダ仕入先名01
				SetOb[EntryCount][WT100_ArrivalPlan_06_ArrayEntrySetDataView.ColSetHdSpName02]	= "";	//ヘッダ仕入先名02
				SetOb[EntryCount][WT100_ArrivalPlan_06_ArrayEntrySetDataView.ColSetHdSpName03]	= "";	//ヘッダ仕入先名03
				SetOb[EntryCount][WT100_ArrivalPlan_06_ArrayEntrySetDataView.ColSetHdSpPost]		= "";	//ヘッダ仕入先郵便
				SetOb[EntryCount][WT100_ArrivalPlan_06_ArrayEntrySetDataView.ColSetHdSpAdd01]		= "";	//ヘッダ仕入先住所01
				SetOb[EntryCount][WT100_ArrivalPlan_06_ArrayEntrySetDataView.ColSetHdSpAdd02]		= "";	//ヘッダ仕入先住所02
				SetOb[EntryCount][WT100_ArrivalPlan_06_ArrayEntrySetDataView.ColSetHdSpAdd03]		= "";	//ヘッダ仕入先住所03
				SetOb[EntryCount][WT100_ArrivalPlan_06_ArrayEntrySetDataView.ColSetHdSpTel]		= "";	//ヘッダ仕入先電話
				SetOb[EntryCount][WT100_ArrivalPlan_06_ArrayEntrySetDataView.ColSetHdArCom01]		= (String)CheckOb[i][ColHdCom01];	//ヘッダコメント1
				SetOb[EntryCount][WT100_ArrivalPlan_06_ArrayEntrySetDataView.ColSetHdArCom02]		= (String)CheckOb[i][ColHdCom02];	//ヘッダコメント2
				SetOb[EntryCount][WT100_ArrivalPlan_06_ArrayEntrySetDataView.ColSetHdArCom03]		= (String)CheckOb[i][ColHdCom03];	//ヘッダコメント3
				SetOb[EntryCount][WT100_ArrivalPlan_06_ArrayEntrySetDataView.ColSetHdFixFg]		= 0;	//ヘッダ状況
				
				SetOb[EntryCount][WT100_ArrivalPlan_06_ArrayEntrySetDataView.ColSetMsNo]			= "";		//明細番号
				
				SetOb[EntryCount][WT100_ArrivalPlan_06_ArrayEntrySetDataView.ColSetClItemCd]		= (String)CheckOb[i][ColClItemCd];	//荷主商品コード
				SetOb[EntryCount][WT100_ArrivalPlan_06_ArrayEntrySetDataView.ColSetJanCd]			= "";		//ソースマーク_BCD
				SetOb[EntryCount][WT100_ArrivalPlan_06_ArrayEntrySetDataView.ColSetItemMdNo]		= "";		//商品型番
				SetOb[EntryCount][WT100_ArrivalPlan_06_ArrayEntrySetDataView.ColSetItemName]		= "";		//商品名
				SetOb[EntryCount][WT100_ArrivalPlan_06_ArrayEntrySetDataView.ColSetlot]			= (String)CheckOb[i][ColLot];	//ロット
				SetOb[EntryCount][WT100_ArrivalPlan_06_ArrayEntrySetDataView.ColSetExpDate]		= (String)CheckOb[i][ColExpDate];	//消費期限
				SetOb[EntryCount][WT100_ArrivalPlan_06_ArrayEntrySetDataView.ColSetPlanQty]		= (int)CheckOb[i][ColPlanQty];	//予定数量
				SetOb[EntryCount][WT100_ArrivalPlan_06_ArrayEntrySetDataView.ColSetActualQty]		= (int)0;	//実績数
				SetOb[EntryCount][WT100_ArrivalPlan_06_ArrayEntrySetDataView.ColSetActualDate]	= "";		//入荷日
				SetOb[EntryCount][WT100_ArrivalPlan_06_ArrayEntrySetDataView.ColSetCom01]			= (String)CheckOb[i][ColMsCom01];		//コメント1
				SetOb[EntryCount][WT100_ArrivalPlan_06_ArrayEntrySetDataView.ColSetCom02]			= (String)CheckOb[i][ColMsCom02];		//コメント2
				SetOb[EntryCount][WT100_ArrivalPlan_06_ArrayEntrySetDataView.ColUnitType]			= 0;		//荷姿タイプ
				
				
				for(int i01=0;i01<SupplierRt.length;i01++) {
					if(((String)CheckOb[i][ColSPCd]).equals((String)SupplierRt[i01][M100_SupplierRt.ColSPCd])) {
						SetOb[EntryCount][WT100_ArrivalPlan_06_ArrayEntrySetDataView.ColSetHdSpName01]	= (String)SupplierRt[i01][M100_SupplierRt.ColSPName01];	//ヘッダ仕入先名01
						SetOb[EntryCount][WT100_ArrivalPlan_06_ArrayEntrySetDataView.ColSetHdSpName02]	= (String)SupplierRt[i01][M100_SupplierRt.ColSPName02];	//ヘッダ仕入先名02
						SetOb[EntryCount][WT100_ArrivalPlan_06_ArrayEntrySetDataView.ColSetHdSpName03]	= (String)SupplierRt[i01][M100_SupplierRt.ColSPName03];	//ヘッダ仕入先名03
						SetOb[EntryCount][WT100_ArrivalPlan_06_ArrayEntrySetDataView.ColSetHdSpPost]		= (String)SupplierRt[i01][M100_SupplierRt.ColSPPost];		//ヘッダ仕入先郵便
						SetOb[EntryCount][WT100_ArrivalPlan_06_ArrayEntrySetDataView.ColSetHdSpAdd01]		= (String)SupplierRt[i01][M100_SupplierRt.ColSPAdd01];		//ヘッダ仕入先住所01
						SetOb[EntryCount][WT100_ArrivalPlan_06_ArrayEntrySetDataView.ColSetHdSpAdd02]		= (String)SupplierRt[i01][M100_SupplierRt.ColSPAdd02];		//ヘッダ仕入先住所02
						SetOb[EntryCount][WT100_ArrivalPlan_06_ArrayEntrySetDataView.ColSetHdSpAdd03]		= (String)SupplierRt[i01][M100_SupplierRt.ColSPAdd03];		//ヘッダ仕入先住所03
						SetOb[EntryCount][WT100_ArrivalPlan_06_ArrayEntrySetDataView.ColSetHdSpTel]		= (String)SupplierRt[i01][M100_SupplierRt.ColSPTel];		//ヘッダ仕入先電話
						
						i01=SupplierRt.length+1;
					}
				}
				
				for(int i01=0;i01<ItemComversion.length;i01++) {
					if(((String)CheckOb[i][ColClItemCd]).equals((String)ItemComversion[i01][WMTools100_ItemComversion.ColClIemCd])) {
						if(!"".equals((String)ItemComversion[i01][WMTools100_ItemComversion.ColItemCd])) {
							SetOb[EntryCount][WT100_ArrivalPlan_06_ArrayEntrySetDataView.ColSetItemCd]	= (String)ItemComversion[i01][WMTools100_ItemComversion.ColItemCd];		//商品コード
							SetOb[EntryCount][WT100_ArrivalPlan_06_ArrayEntrySetDataView.ColUnitType]	= (int)ItemComversion[i01][WMTools100_ItemComversion.ColPackingType];			//荷姿タイプ
							
							TgtItemCd.add((String)SetOb[EntryCount][WT100_ArrivalPlan_06_ArrayEntrySetDataView.ColSetItemCd]);
						}
						i01=ItemComversion.length+1;
					}
				}
				
				EntryCount = EntryCount+1;
			}
		}
		
		Object[][] ItemMstRt = ItemMstRt(A00000_Main.ClGp ,TgtItemCd);
		EntryCount = 0;
		for(int i=0;i<CheckOb.length;i++) {
			if(!"".equals((String)CheckOb[i][ColClItemCd])&&0<(int)CheckOb[i][ColPlanQty]) {
				if(0>=(int)SetOb[EntryCount][WT100_ArrivalPlan_06_ArrayEntrySetDataView.ColSetPlanQty]) {SetOb[EntryCount][WT100_ArrivalPlan_06_ArrayEntrySetDataView.ColSetPlanQty] = 0;}	//予定数量
				
				for(int i01=0;i01<ItemMstRt.length;i01++) {
					if(((String)SetOb[EntryCount][WT100_ArrivalPlan_06_ArrayEntrySetDataView.ColSetItemCd]).equals((String)ItemMstRt[i01][M100_ItemMstRt.ColItemCd])) {
						SetOb[EntryCount][WT100_ArrivalPlan_06_ArrayEntrySetDataView.ColSetJanCd]				= (String)ItemMstRt[i01][M100_ItemMstRt.ColJanCd];		//ソースマーク_BCD
						SetOb[EntryCount][WT100_ArrivalPlan_06_ArrayEntrySetDataView.ColSetItemMdNo]			= (String)ItemMstRt[i01][M100_ItemMstRt.ColItemMDNo];		//商品型番
						SetOb[EntryCount][WT100_ArrivalPlan_06_ArrayEntrySetDataView.ColSetItemName]			= (String)ItemMstRt[i01][M100_ItemMstRt.ColItemName01];	//商品名
						
						int GetCtQty					= (int)ItemMstRt[i01][M100_ItemMstRt.ColCtQty];					//カートン入数
						int GetCsQty					= (int)ItemMstRt[i01][M100_ItemMstRt.ColCsQty];					//ケース入数
						int GetPlQty					= (int)ItemMstRt[i01][M100_ItemMstRt.ColPlQty];					//パレット入数
						
						if(0>=GetCtQty){GetCtQty=1;}	//カートン入数
						if(0>=GetCsQty){GetCsQty=1;}	//ケース入数
						if(0>=GetPlQty){GetPlQty=1;}	//パレット入数
						
						switch((int)SetOb[EntryCount][WT100_ArrivalPlan_06_ArrayEntrySetDataView.ColUnitType]	) {
							case 0:			//バラ
								break;
							case 1:			//カートン
								SetOb[EntryCount][WT100_ArrivalPlan_06_ArrayEntrySetDataView.ColSetPlanQty]	= GetCtQty*(int)SetOb[EntryCount][WT100_ArrivalPlan_06_ArrayEntrySetDataView.ColSetPlanQty];	//予定数量
								break;
							case 2:			//ケース
								SetOb[EntryCount][WT100_ArrivalPlan_06_ArrayEntrySetDataView.ColSetPlanQty]	= GetCsQty*(int)SetOb[EntryCount][WT100_ArrivalPlan_06_ArrayEntrySetDataView.ColSetPlanQty];	//予定数量
								break;
							case 3:			//パレット
								SetOb[EntryCount][WT100_ArrivalPlan_06_ArrayEntrySetDataView.ColSetPlanQty]	= GetPlQty*(int)SetOb[EntryCount][WT100_ArrivalPlan_06_ArrayEntrySetDataView.ColSetPlanQty];	//予定数量
								break;
							default:
								break;
						}
						i01=ItemMstRt.length+1;
					}
				}
				EntryCount = EntryCount+1;
			}
		}
		return SetOb;
	}
	
	
	private static Object[][] CheckObTrim(Object[][] CheckOb){
		for(int i=0;i<CheckOb.length;i++) {
			CheckOb[i][ColClWh] 		= B100_TextControl.Trim((String)CheckOb[i][ColClWh]);		//担当倉庫
			CheckOb[i][ColClCd] 		= B100_TextControl.Trim((String)CheckOb[i][ColClCd]);		//荷主CD
			CheckOb[i][ColSPCd] 		= B100_TextControl.Trim((String)CheckOb[i][ColSPCd]);		//仕入先CD
			CheckOb[i][ColPlanDate] 	= B100_TextControl.Trim((String)CheckOb[i][ColPlanDate]);	//入荷予定日
			CheckOb[i][ColClArrNo] 	= B100_TextControl.Trim((String)CheckOb[i][ColClArrNo]);	//荷主予定番号
			CheckOb[i][ColClItemCd]	= B100_TextControl.Trim((String)CheckOb[i][ColClItemCd]);	//荷主商品CD
			CheckOb[i][ColLot] 		= B100_TextControl.Trim((String)CheckOb[i][ColLot]);			//ロット
			CheckOb[i][ColExpDate] 	= B100_TextControl.Trim((String)CheckOb[i][ColExpDate]);	//賞味期限
			CheckOb[i][ColPlanQty] 	= B100_TextControl.Trim((String)CheckOb[i][ColPlanQty]);	//数量
			CheckOb[i][ColHdCom01]		= B100_TextControl.Trim((String)CheckOb[i][ColHdCom01]);	//ヘッダコメント01
			CheckOb[i][ColHdCom02]		= B100_TextControl.Trim((String)CheckOb[i][ColHdCom02]);	//ヘッダコメント02
			CheckOb[i][ColHdCom03]		= B100_TextControl.Trim((String)CheckOb[i][ColHdCom03]);	//ヘッダコメント03
			CheckOb[i][ColMsCom01]		= B100_TextControl.Trim((String)CheckOb[i][ColMsCom01]);	//明細コメント01
			CheckOb[i][ColMsCom02]		= B100_TextControl.Trim((String)CheckOb[i][ColMsCom02]);	//明細コメント01
			
			CheckOb[i][ColExpDate] 	= B100_DateTimeControl.DateFormat((String)CheckOb[i][ColExpDate]);
			
			CheckOb[i][ColPlanQty] = B100_TextControl.num_only_String02((String)CheckOb[i][ColPlanQty]);
			if("".equals((String)CheckOb[i][ColPlanQty])) {CheckOb[i][ColPlanQty]="0";}
			float WFT = Float.parseFloat((String)CheckOb[i][ColPlanQty]);
			int Qty = (int)WFT;
			
			if(0>Qty) {
				CheckOb[i][ColPlanQty] = (int)0;
			}else {
				CheckOb[i][ColPlanQty] = Qty;
			}
		}
		return CheckOb;
	}
	
	private static Object[][] ItemMstRt(String TgtClGpCd ,ArrayList<String> TgtItemCd){
		ArrayList<String> SearchClGpCd 				= new ArrayList<String>();	//荷主グループコード
		ArrayList<String> SearchItemCd 				= TgtItemCd;	//商品コード
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
		
		SearchClGpCd.add(TgtClGpCd);
		
		Object[][] ItemMstRt = M100_ItemMstRt.ItemMstRt(
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
		
		return ItemMstRt;
	}
	
	private static Object[][] SupplierRt(String ClWh,String ClCd,ArrayList<String> TgtSPCd){
		//仕入先存在チェック用に仕入れ先マスタ取得検索条件
		ArrayList<String> SearchClWh = new ArrayList<String>(); 			//担当倉庫
		ArrayList<String> SearchClCd = new ArrayList<String>();				//荷主CD
		ArrayList<String> SearchSPCd = TgtSPCd;								//仕入先CD
		ArrayList<String> SearchSPName = new ArrayList<String>();			//仕入先名
		ArrayList<String> SearchSPPost = new ArrayList<String>();			//仕入先郵便
		ArrayList<String> SearchSPAdd = new ArrayList<String>();			//仕入先住所
		ArrayList<String> SearchSPTel = new ArrayList<String>();			//仕入先電話
		ArrayList<String> SearchSPFax = new ArrayList<String>();			//仕入先FAX
		ArrayList<String> SearchSPMail = new ArrayList<String>();			//仕入先MAIL
		ArrayList<String> SearchCom = new ArrayList<String>();				//コメント
		ArrayList<String> SearchPTMSCDBMN = new ArrayList<String>();		//基幹Sysコード（部門）
		ArrayList<String> SearchPTMSCDNINUSHI = new ArrayList<String>();	//基幹Sysコード（荷主）
		ArrayList<Integer> SearchPaySiteStr = new ArrayList<Integer>();		//支払いサイト（月数）開始
		ArrayList<Integer> SearchPayDateStr = new ArrayList<Integer>();		//支払日（日＝99）開始
		ArrayList<Integer> SearchShimeDateStr = new ArrayList<Integer>();	//締め日（末日＝99）開始
		ArrayList<Integer> SearchPaySiteEnd = new ArrayList<Integer>();		//支払いサイト（月数）終了
		ArrayList<Integer> SearchPayDateEnd = new ArrayList<Integer>();		//支払日（日＝99）終了
		ArrayList<Integer> SearchShimeDateEnd = new ArrayList<Integer>();	//締め日（末日＝99）終了
		ArrayList<String> SearchDECD = new ArrayList<String>();				//納品先コード
		ArrayList<String> SearchDepartmentCd = new ArrayList<String>();		//部署CD
		boolean AllSearch = false;
		
		//検索条件設定
		SearchClWh.add(ClWh);
		SearchClCd.add(ClCd);
		
		//仕入先存在チェック用に仕入れ先マスタ取得
		Object[][] SupplierRt = M100_SupplierRt.SupplierRt(
				SearchClWh,				//担当倉庫
				SearchClCd,				//荷主CD
				SearchSPCd,				//仕入先コード
				SearchSPName,			//仕入先名
				SearchSPPost,			//仕入先郵便
				SearchSPAdd,			//仕入先住所
				SearchSPTel,			//仕入先電話
				SearchSPFax,			//仕入先FAX
				SearchSPMail,			//仕入先MAIL
				SearchCom,				//コメント
				SearchPTMSCDBMN,		//基幹Sysコード（部門）
				SearchPTMSCDNINUSHI,	//基幹Sysコード（荷主）
				SearchPaySiteStr,		//支払いサイト（月数）開始
				SearchPayDateStr,		//支払日（日＝99）開始
				SearchShimeDateStr,		//締め日（末日＝99）開始
				SearchPaySiteEnd,		//支払いサイト（月数）終了
				SearchPayDateEnd,		//支払日（日＝99）終了
				SearchShimeDateEnd,		//締め日（末日＝99）終了
				SearchDECD,				//納品先コード
				SearchDepartmentCd,		//部署CD
				AllSearch);
		
		return SupplierRt;
	}
	
	private static void ErrView(ArrayList<String>ErrMsg) {
		//必要フォルダを生成する
		String FLD_PATH = A00000_Main.MainFLD+"\\ArrivalControl";
		B100_FolderCheck.FLD_CHECK(FLD_PATH);
		FLD_PATH = A00000_Main.MainFLD+"\\ArrivalControl\\ArrivalPlanEntry";
		B100_FolderCheck.FLD_CHECK(FLD_PATH);
		FLD_PATH = A00000_Main.MainFLD+"\\ArrivalControl\\ArrivalPlanEntry\\Err";
		B100_FolderCheck.FLD_CHECK(FLD_PATH);
		FLD_PATH = A00000_Main.MainFLD+"\\ArrivalControl\\ArrivalPlanEntry\\BK";
		B100_FolderCheck.FLD_CHECK(FLD_PATH);
		
		//ファイルに出力
		String NowDTM=B100_DateTimeControl.dtmString2(B100_DateTimeControl.dtm()[1])[1].replace(" ", "").replace("/", "").replace(":", "");
		
		FLD_PATH = A00000_Main.MainFLD+"\\ArrivalControl\\ArrivalPlanEntry\\Err";
		
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
	
}