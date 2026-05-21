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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class WT0001004ArrivalPlanArrayEntry{
	static final int ColClWh		= 0;	//担当倉庫
	static final int ColClCd		= 1;	//荷主CD
	static final int ColSPCd		= 2;	//仕入先CD
	static final int ColPlanDate	= 3;	//入荷予定日
	static final int ColClArrNo	= 4;	//荷主予定番号
	static final int ColClItemCd	= 5;	//荷主商品CD
	static final int ColLot		= 6;	//ロット
	static final int ColExpDate	= 7;	//賞味期限
	static final int ColPlanQty	= 8;	//数量
	
	
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	
	public static Object[][] RtArrivalPlanArrayEntry() {
		//投入データ定義
		Object[][] RtArrivapPlanArrayEntry= {
				 {"ClWh"			,ColClWh		,"String"	,"担当倉庫"}
				,{"ClCd"			,ColClCd		,"String"	,"荷主CD"}
				,{"SPCd"			,ColSPCd		,"String"	,"仕入先CD"}
				,{"PlanDate"		,ColPlanDate	,"String"	,"入荷予定日"}
				,{"ClArrNo"			,ColClArrNo	,"String"	,"荷主予定番号"}
				,{"ClItemCd"		,ColClItemCd	,"String"	,"荷主商品CD"}
				,{"Lot"				,ColLot		,"String"	,"ロット"}
				,{"ExpDate"			,ColExpDate	,"DateTime"	,"賞味期限"}
				,{"PlanQty"			,ColPlanQty	,"int"		,"数量"}
				};
		return RtArrivapPlanArrayEntry;
	}
	
	static final int ColSetHdClWh			= 0;			//ヘッダ担当倉庫
	static final int ColSetHdClCd			= 1;			//ヘッダ荷主CD
	static final int ColSetHdArrNo		= 2;			//ヘッダ入荷予定NO（WMS採番）
	static final int ColSetHdClArrNo		= 3;			//ヘッダ荷主予定番号
	static final int ColSetHdPlanDate		= 4;			//ヘッダ入荷予定日
	static final int ColSetHdActualDate	= 5;			//ヘッダ入荷実績日
	static final int ColSetHdSpCd			= 6;			//ヘッダ仕入先CD
	static final int ColSetHdSpName01		= 7;			//ヘッダ仕入先名01
	static final int ColSetHdSpName02		= 8;			//ヘッダ仕入先名02
	static final int ColSetHdSpName03		= 9;			//ヘッダ仕入先名03
	static final int ColSetHdSpPost		=10;			//ヘッダ仕入先郵便
	static final int ColSetHdSpAdd01		=11;			//ヘッダ仕入先住所01
	static final int ColSetHdSpAdd02		=12;			//ヘッダ仕入先住所02
	static final int ColSetHdSpAdd03		=13;			//ヘッダ仕入先住所03
	static final int ColSetHdSpTel		=14;			//ヘッダ仕入先電話
	static final int ColSetHdArCom01		=15;			//ヘッダコメント1
	static final int ColSetHdArCom02		=16;			//ヘッダコメント2
	static final int ColSetHdArCom03		=17;			//ヘッダコメント3
	static final int ColSetHdEntryDate	=18;			//ヘッダ登録日
	static final int ColSetHdUpdateDate	=19;			//ヘッダ更新日
	static final int ColSetHdEntryUser	=20;			//ヘッダ登録者
	static final int ColSetHdUpdateUser	=21;			//ヘッダ更新者
	static final int ColSetHdFixFg		=22;			//ヘッダ状況　完了:1 未完了:0　分納待ち:2  キャンセル:9
	
	static final int ColSetMsNo			=23;			//明細番号
	static final int ColSetItemCd			=24;			//商品コード
	static final int ColSetClItemCd		=25;			//荷主商品コード
	static final int ColSetJanCd			=26;			//ソースマーク_BCD（バラ）
	static final int ColSetItemMdNo		=27;			//商品型番
	static final int ColSetItemName		=28;			//商品名
	static final int ColSetlot				=29;			//ロット
	static final int ColSetExpDate		=30;			//消費期限
	static final int ColSetPlanQty		=31;			//予定数量
	static final int ColSetActualQty		=32;			//実績数
	static final int ColSetActualDate		=33;			//入荷日
	static final int ColSetCom01			=34;			//コメント1
	static final int ColSetCom02			=35;			//コメント2
	static final int ColSetEntryDate		=36;			//登録日
	static final int ColSetUpdateDate		=37;			//更新日
	static final int ColSetEntryUser		=38;			//登録者
	static final int ColSetUpdateUser		=39;			///更新者
	
	private static Object[][] SetDataDefinition(){
		//登録用データ定義
		Object[][] SetDataDefinition= {
				 {"ColSetHdClWh"	,ColSetHdClWh			,"String"	,"ヘッダ担当倉庫"}
				,{"SetHdClCd"		,ColSetHdClCd			,"String"	,"ヘッダ荷主CD"}
				,{"SetHdArrNo"		,ColSetHdArrNo		,"String"	,"ヘッダ入荷予定NO"}
				,{"SetHdClArrNo"	,ColSetHdClArrNo		,"String"	,"ヘッダ荷主予定番号"}
				,{"SetHdPlanDate"	,ColSetHdPlanDate		,"DateTime"	,"ヘッダ入荷予定日"}
				,{"SetHdActualDate"	,ColSetHdActualDate	,"DateTime"	,"ヘッダ入荷実績日"}
				,{"SetHdSpCd"		,ColSetHdSpCd			,"String"	,"ヘッダ仕入先CD"}
				,{"SetHdSpName01"	,ColSetHdSpName01		,"String"	,"ヘッダ仕入先名01"}
				,{"SetHdSpName02"	,ColSetHdSpName02		,"String"	,"ヘッダ仕入先名02"}
				,{"SetHdSpName03"	,ColSetHdSpName03		,"String"	,"ヘッダ仕入先名03"}
				,{"SetHdSpPost"		,ColSetHdSpPost		,"String"	,"ヘッダ仕入先郵便"}
				,{"SetHdSpAdd01"	,ColSetHdSpAdd01		,"String"	,"ヘッダ仕入先住所01"}
				,{"SetHdSpAdd02"	,ColSetHdSpAdd02		,"String"	,"ヘッダ仕入先住所02"}
				,{"SetHdSpAdd03"	,ColSetHdSpAdd03		,"String"	,"ヘッダ仕入先住所03"}
				,{"SetHdSpTel"		,ColSetHdSpTel		,"String"	,"ヘッダ仕入先電話"}
				,{"SetHdArCom01"	,ColSetHdArCom01		,"String"	,"ヘッダコメント1"}
				,{"SetHdArCom02"	,ColSetHdArCom02		,"String"	,"ヘッダコメント2"}
				,{"SetHdArCom03"	,ColSetHdArCom03		,"String"	,"ヘッダコメント3"}
				,{"SetHdFixFg"		,ColSetHdFixFg		,"int"		,"ヘッダ状況"}
				
				,{"SetMsNo"			,ColSetMsNo			,"String"	,"明細番号"}
				,{"SetItemCd"		,ColSetItemCd			,"String"	,"商品コード"}
				,{"SetClItemCd"		,ColSetClItemCd		,"String"	,"荷主商品コード"}
				,{"SetJanCd"		,ColSetJanCd			,"String"	,"ソースマーク_BCD"}
				,{"SetItemMdNo"		,ColSetItemMdNo		,"String"	,"商品型番"}
				,{"SetItemName"		,ColSetItemName		,"String"	,"商品名"}
				,{"Setlot"			,ColSetlot				,"String"	,"ロット"}
				,{"SetExpDate"		,ColSetExpDate		,"DateTime"	,"消費期限"}
				,{"SetPlanQty"		,ColSetPlanQty		,"int"		,"予定数量"}
				,{"SetActualQty"	,ColSetActualQty		,"int"		,"実績数"}
				,{"SetActualDate"	,ColSetActualDate		,"DateTime"	,"入荷日"}
				,{"SetCom01"		,ColSetCom01			,"String"	,"コメント1"}
				,{"SetCom02"		,ColSetCom02			,"String"	,"コメント2"}
				};
		return SetDataDefinition;
	}
	
	public static void ArrivalPlanArrayEntry(int x,int y,String[][] EntryData) {
		A00000Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		
		String Nextday = B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.ndate_after(B00050ToolsDateTimeControl.dtm()[1],1))[0];
		
		final JFrame main_fm = B00110FrameParts.FrameCreate(x,y,900,750,"Corgi00入荷予定取込（データ内容確認）","");
		JLabel userinfo = B00110FrameParts.UserInfo();
		JButton exit_btn = B00110FrameParts.ExitBtn();
		JButton entry_btn = B00110FrameParts.EntryBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		main_fm.add(entry_btn);
		
		JLabel LB_Msg 	= B00110FrameParts.JLabelSet(  0, 50,100,20,"以下のデータを取込もうとしています"	,11,1);
		main_fm.add(LB_Msg);
		
		Object[][] RtArrivalPlanArrayEntry = WT0001004ArrivalPlanArrayEntry.RtArrivalPlanArrayEntry();
		
		String[] columnNames01 = new String[RtArrivalPlanArrayEntry.length+1];
		
		columnNames01[0] = "Fg";
		for(int i=0;i<RtArrivalPlanArrayEntry.length;i++) {
			columnNames01[1+i] = ""+RtArrivalPlanArrayEntry[i][3];
		}
		
		//編集可能カラムの指定
		B10010TableControl.RenewTgt = new int[1];
		B10010TableControl.RenewTgt[0] = 0;

		final DefaultTableModel tableModel_ms01 = new B10010TableControl.MyTableModel01(columnNames01,0);
		
		final JTable tb01 = new JTable(tableModel_ms01);
		tb01.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tb01.setRowHeight(20*A00000Main.Mul/A00000Main.Div);
		tb01.setFont(new Font(A00000Main.DefaultFont, Font.PLAIN, 12*A00000Main.Mul/A00000Main.Div));
		
		DefaultTableColumnModel columnModel01
		= (DefaultTableColumnModel)tb01.getColumnModel();
		
		//列幅初期設定 表示位置設定
		TableColumn column = null;
		
		column = columnModel01.getColumn( 0);	column.setPreferredWidth( 30*A00000Main.Mul/A00000Main.Div);	//FG
		
		for(int i=0;i<RtArrivalPlanArrayEntry.length;i++) {
			if("int".equals((String)RtArrivalPlanArrayEntry[i][2])||"float".equals((String)RtArrivalPlanArrayEntry[i][2])) {
				column = columnModel01.getColumn(1+i);	column.setPreferredWidth( 90*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.rightCellRenderer());
			}else {
				column = columnModel01.getColumn(1+i);	column.setPreferredWidth( 90*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());
			}
		}
		//スクロール用設定
		JScrollPane scpn01 = B00110FrameParts.JScrollPaneSet(10,75,860,500,tb01);
		main_fm.add(scpn01);
		
		for(int i=0;i<EntryData.length;i++) {
			Object[] SetOb = new Object[WT0001004ArrivalPlanArrayEntry.RtArrivalPlanArrayEntry().length+1];
			SetOb[0] = false;
			SetOb[ColClWh+1]		= B00020ToolsTextControl.Trim(EntryData[i][ColClWh]);		//担当倉庫
			SetOb[ColClCd+1]		= B00020ToolsTextControl.Trim(EntryData[i][ColClCd]);		//荷主CD
			SetOb[ColSPCd+1]		= B00020ToolsTextControl.Trim(EntryData[i][ColSPCd]);		//仕入先CD
			SetOb[ColPlanDate+1]	= B00020ToolsTextControl.Trim(EntryData[i][ColPlanDate]);	//入荷予定日
			SetOb[ColClArrNo+1]	= B00020ToolsTextControl.Trim(EntryData[i][ColClArrNo]);	//荷主予定番号
			SetOb[ColClItemCd+1]	= B00020ToolsTextControl.Trim(EntryData[i][ColClItemCd]);	//荷主商品CD
			SetOb[ColLot+1]		= B00020ToolsTextControl.Trim(EntryData[i][ColLot]);		//ロット
			SetOb[ColExpDate+1]	= B00020ToolsTextControl.Trim(EntryData[i][ColExpDate]);	//賞味期限
			SetOb[ColPlanQty+1]	= B00020ToolsTextControl.Trim(EntryData[i][ColPlanQty]);	//数量
			
			SetOb[ColExpDate+1]	= B00050ToolsDateTimeControl.DateFormat((String)SetOb[ColExpDate+1]);		//賞味期限
			SetOb[ColPlanQty+1]	= B00020ToolsTextControl.num_only_String02((String)SetOb[ColPlanQty+1]);	//数量
			
			if("".equals((String)SetOb[ColPlanQty+1])) {SetOb[ColPlanQty+1]="0";}
			
			if(!"".equals((String)SetOb[ColClItemCd+1])&&0<Integer.parseInt((String)SetOb[ColPlanQty+1])) {
				tableModel_ms01.addRow(SetOb);
			}
		}
		RenewFg = true;
		main_fm.setVisible(true);
		
		//登録ボタン押下時の挙動
		entry_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				String[] TableCol = B10010TableControl.TableFieldNameRt(tb01);
				int RowCount = tableModel_ms01.getRowCount();
				Object[][] CheckOb = new Object[RowCount][TableCol.length-1];
				for(int i=0;i<RowCount;i++) {
					for(int i01=1;i01<TableCol.length;i01++) {
						CheckOb[i][i01-1] = ""+tableModel_ms01.getValueAt(i, i01);
					}
				}
				ArrayList<String> ErrMsg = ErrCheck(CheckOb);
				
				if(null!=ErrMsg && 0<ErrMsg.size()) {
					ErrView(ErrMsg);
				}else {
					SetX=main_fm.getX();
					SetY=main_fm.getY();
	
					main_fm.setVisible(false);
					main_fm.dispose();
					WT0001000ArrivalPlanSearch.ArrivalPlanSearch(0,0);
				}
			}
		});
		
		//チェックボックス操作時の挙動
		tableModel_ms01.addTableModelListener(new TableModelListener(){
			public void tableChanged(TableModelEvent e){
				if(RenewFg) {
					RenewFg = false;
					int row_count = tb01.getRowCount();
					Boolean setBL=Boolean.valueOf(false);
					for(int i=0;i<row_count;i++){
						if(i!=e.getFirstRow()){
							tableModel_ms01.setValueAt(setBL, i, 0);
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
				WT0001000ArrivalPlanSearch.ArrivalPlanSearch(0,0);
			}
		});
	}
	
	private static void EntryDataView(Object[][] CheckOb) {
		
	}
	
	
	
	
	private static ArrayList<String> ErrCheck(Object[][] CheckOb){
		ArrayList<String> ErrMsg = new ArrayList<String>();
		
		for(int i=0;i<CheckOb.length;i++) {
			CheckOb[i][ColClWh] 		= B00020ToolsTextControl.Trim((String)CheckOb[i][ColClWh]);		//担当倉庫
			CheckOb[i][ColClCd] 		= B00020ToolsTextControl.Trim((String)CheckOb[i][ColClCd]);		//荷主CD
			CheckOb[i][ColSPCd] 		= B00020ToolsTextControl.Trim((String)CheckOb[i][ColSPCd]);		//仕入先CD
			CheckOb[i][ColPlanDate] 	= B00020ToolsTextControl.Trim((String)CheckOb[i][ColPlanDate]);	//入荷予定日
			CheckOb[i][ColClArrNo] 	= B00020ToolsTextControl.Trim((String)CheckOb[i][ColClArrNo]);	//荷主予定番号
			CheckOb[i][ColClItemCd]	= B00020ToolsTextControl.Trim((String)CheckOb[i][ColClItemCd]);	//荷主商品CD
			CheckOb[i][ColLot] 		= B00020ToolsTextControl.Trim((String)CheckOb[i][ColLot]);			//ロット
			CheckOb[i][ColExpDate] 	= B00020ToolsTextControl.Trim((String)CheckOb[i][ColExpDate]);	//賞味期限
			CheckOb[i][ColPlanQty] 	= B00020ToolsTextControl.Trim((String)CheckOb[i][ColPlanQty]);	//数量
			
			CheckOb[i][ColPlanQty] = B00020ToolsTextControl.num_only_String02((String)CheckOb[i][ColPlanQty]);
			if("".equals((String)CheckOb[i][ColPlanQty])) {CheckOb[i][ColPlanQty]="0";}
			float WFT = Float.parseFloat((String)CheckOb[i][ColPlanQty]);
			
			int Qty = (int)WFT;
			int EntryCount = 0;
			if(!"".equals((String)CheckOb[i][ColClItemCd])&&0<Qty) {
				if(!A00000Main.ClWh.equals((String)CheckOb[i][ColClWh])) {
					int wint = i+1;
					ErrMsg.add(wint+"行目エラー:倉庫コード("+(String)CheckOb[i][ColClWh]+")は現在ログイン中の倉庫と異なります");
				}
				if(!A00000Main.ClCd.equals((String)CheckOb[i][ColClCd])) {
					int wint = i+1;
					ErrMsg.add(wint+"行目エラー:荷主コード("+(String)CheckOb[i][ColClCd]+")は現在ログイン中の荷主と異なります");
				}
				EntryCount = EntryCount+1;
			}
		}
		
		//仕入先存在チェック用に仕入れ先マスタ取得検索条件
		ArrayList<String> SearchClWh = new ArrayList<String>(); 			//担当倉庫
		ArrayList<String> SearchClCd = new ArrayList<String>();				//荷主CD
		ArrayList<String> SearchSPCd = new ArrayList<String>();				//仕入先コード
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
		
		//商品変換チェック用に商品変換マスタ情報取得検索条件
		String ClCd	= A00000Main.ClCd; 
		ArrayList<String> ClItemCd=new ArrayList<String>();
		
		//検索条件設定
		SearchClWh.add(A00000Main.ClWh);
		SearchClCd.add(A00000Main.ClCd);
		for(int i=0;i<CheckOb.length;i++) {
			SearchSPCd.add((String)CheckOb[i][ColSPCd]);
			ClItemCd.add((String)CheckOb[i][ColClItemCd]);
		}
		
		//仕入先存在チェック用に仕入れ先マスタ取得
		Object[][] SupplierRt = M00100SupplierRt.SupplierRt(
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
		
		
		//商品変換チェック用に商品変換マスタ情報取得
		Object[][] ItemComversion = WMTools000100ItemComversion.ItemComversion(ClCd,ClItemCd);
		
		//マスタエラーチェック
		for(int i=0;i<CheckOb.length;i++) {
			boolean UnHitFg = true;
			for(int i01=0;i01<SupplierRt.length;i01++) {
				if(((String)CheckOb[i][ColSPCd]).equals((String)SupplierRt[i01][M00100SupplierRt.ColSPCd])) {
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
				if(((String)CheckOb[i][ColClItemCd]).equals((String)ItemComversion[i01][WMTools000100ItemComversion.ColClIemCd])) {
					if(!"".equals((String)ItemComversion[i01][WMTools000100ItemComversion.ColItemCd])) {
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
		
		return ErrMsg;
	}
	
	
	private static Object[][] SetObRt(Object[][]CheckOb){
		Object[][] SetOb = new Object[CheckOb.length][SetDataDefinition().length];
		
		
		
		
		
		
		return SetOb;
	}
	private static void ErrView(ArrayList<String>ErrMsg) {
		//必要フォルダを生成する
		String FLD_PATH = A00000Main.MainFLD+"\\ArrivalControl";
		B00040ToolsFolderCheck.FLD_CHECK(FLD_PATH);
		FLD_PATH = A00000Main.MainFLD+"\\ArrivalControl\\ArrivalPlanEntry";
		B00040ToolsFolderCheck.FLD_CHECK(FLD_PATH);
		FLD_PATH = A00000Main.MainFLD+"\\ArrivalControl\\ArrivalPlanEntry\\Err";
		B00040ToolsFolderCheck.FLD_CHECK(FLD_PATH);
		FLD_PATH = A00000Main.MainFLD+"\\ArrivalControl\\ArrivalPlanEntry\\BK";
		B00040ToolsFolderCheck.FLD_CHECK(FLD_PATH);
		
		//ファイルに出力
		String NowDTM=B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[1].replace(" ", "").replace("/", "").replace(":", "");
		
		FLD_PATH = A00000Main.MainFLD+"\\ArrivalControl\\ArrivalPlanEntry\\Err";
		
		String ErrFP = FLD_PATH+"\\ERR"+NowDTM+".txt";
		
		B00030ToolsTextExport.txt_exp2(ErrMsg, ErrFP,"UTF-8");
		
		//古いエラーデータ削除
		B00040ToolsFolderCheck.ToolsOldFileDeleteWhereFileName(FLD_PATH ,"ERR",B00100DefaultVariable.ErrTxtDelete);
		
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