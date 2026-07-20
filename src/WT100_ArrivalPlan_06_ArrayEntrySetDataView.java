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

public class WT100_ArrivalPlan_06_ArrayEntrySetDataView{
	/******************************************************************
		WT100_ArrivalPlan_05_ArrayEntrySourceDataView で
		確認済みのデータを登録する※エラーチェック済みのデータ前提
	******************************************************************/

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
	static final int ColSetHdFixFg		=18;			//ヘッダ状況　完了:1 未完了:0　分納待ち:2  キャンセル:9
	
	static final int ColSetMsNo			=19;			//明細番号
	static final int ColSetItemCd			=20;			//商品コード
	static final int ColSetClItemCd		=21;			//荷主商品コード
	static final int ColSetJanCd			=22;			//ソースマーク_BCD（バラ）
	static final int ColSetItemMdNo		=23;			//商品型番
	static final int ColSetItemName		=24;			//商品名
	static final int ColSetlot				=25;			//ロット
	static final int ColSetExpDate		=26;			//消費期限
	static final int ColSetPlanQty		=27;			//予定数量
	static final int ColSetActualQty		=28;			//実績数
	static final int ColSetActualDate		=29;			//入荷日
	static final int ColSetCom01			=30;			//コメント1
	static final int ColSetCom02			=31;			//コメント2
	static final int ColUnitType			=32;			//荷姿タイプ
	
	public static Object[][] RtSetDataDefinition(){
		//登録用データ定義
		Object[][] SetDataDefinition= {
				 {"SetHdClWh"		,ColSetHdClWh			,"String"	,"ヘッダ担当倉庫"		,"Key"	}
				,{"SetHdClCd"		,ColSetHdClCd			,"String"	,"ヘッダ荷主CD"			,"Key"	}
				,{"SetHdArrNo"		,ColSetHdArrNo		,"String"	,"ヘッダ入荷予定NO"		,"Key"	}
				,{"SetHdClArrNo"	,ColSetHdClArrNo		,"String"	,"ヘッダ荷主予定番号"	,"Key"	}
				,{"SetHdPlanDate"	,ColSetHdPlanDate		,"DateTime"	,"ヘッダ入荷予定日"		,"Key"	}
				,{"SetHdActualDate"	,ColSetHdActualDate	,"DateTime"	,"ヘッダ入荷実績日"		,""		}
				,{"SetHdSpCd"		,ColSetHdSpCd			,"String"	,"ヘッダ仕入先CD"		,"Key"	}
				,{"SetHdSpName01"	,ColSetHdSpName01		,"String"	,"ヘッダ仕入先名01"		,""		}
				,{"SetHdSpName02"	,ColSetHdSpName02		,"String"	,"ヘッダ仕入先名02"		,""		}
				,{"SetHdSpName03"	,ColSetHdSpName03		,"String"	,"ヘッダ仕入先名03"		,""		}
				,{"SetHdSpPost"		,ColSetHdSpPost		,"String"	,"ヘッダ仕入先郵便"		,""		}
				,{"SetHdSpAdd01"	,ColSetHdSpAdd01		,"String"	,"ヘッダ仕入先住所01"	,""		}
				,{"SetHdSpAdd02"	,ColSetHdSpAdd02		,"String"	,"ヘッダ仕入先住所02"	,""		}
				,{"SetHdSpAdd03"	,ColSetHdSpAdd03		,"String"	,"ヘッダ仕入先住所03"	,""		}
				,{"SetHdSpTel"		,ColSetHdSpTel		,"String"	,"ヘッダ仕入先電話"		,""		}
				,{"SetHdArCom01"	,ColSetHdArCom01		,"String"	,"ヘッダコメント1"		,""		}
				,{"SetHdArCom02"	,ColSetHdArCom02		,"String"	,"ヘッダコメント2"		,""		}
				,{"SetHdArCom03"	,ColSetHdArCom03		,"String"	,"ヘッダコメント3"		,""		}
				,{"SetHdFixFg"		,ColSetHdFixFg		,"int"		,"ヘッダ状況"}
				
				,{"SetMsNo"			,ColSetMsNo			,"String"	,"明細番号"				,""		}
				,{"SetItemCd"		,ColSetItemCd			,"String"	,"商品コード"			,""		}
				,{"SetClItemCd"		,ColSetClItemCd		,"String"	,"荷主商品コード"		,""		}
				,{"SetJanCd"		,ColSetJanCd			,"String"	,"ソースマーク_BCD"		,""		}
				,{"SetItemMdNo"		,ColSetItemMdNo		,"String"	,"商品型番"				,""		}
				,{"SetItemName"		,ColSetItemName		,"String"	,"商品名"				,""		}
				,{"Setlot"			,ColSetlot				,"String"	,"ロット"				,""		}
				,{"SetExpDate"		,ColSetExpDate		,"DateTime"	,"消費期限"				,""		}
				,{"SetPlanQty"		,ColSetPlanQty		,"int"		,"予定数量"				,""		}	//※バラ数量
				,{"SetActualQty"	,ColSetActualQty		,"int"		,"実績数"				,""		}
				,{"SetActualDate"	,ColSetActualDate		,"DateTime"	,"入荷日"				,""		}
				,{"SetCom01"		,ColSetCom01			,"String"	,"コメント1"			,""		}
				,{"SetCom02"		,ColSetCom02			,"String"	,"コメント2"			,""		}
				,{"UnitType"		,ColUnitType			,"int"		,"荷姿タイプ"			,""		}
				};
		return SetDataDefinition;
	}
	
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	public static void ArrivalPlanArrayEntrySetDataView(int x,int y,Object[][] EntryData) {
		A00000_Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		
		final JFrame main_fm = B100_FrameParts.FrameCreate(x,y,900,750,"Corgi00入荷予定取込（登録データ確認）　WT100_ArrivalPlan_06_ArrayEntrySetDataView","NK");
		JLabel userinfo = B100_FrameParts.UserInfo();
		JButton exit_btn = B100_FrameParts.ExitBtn();
		JButton entry_btn = B100_FrameParts.EntryBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		main_fm.add(entry_btn);
		
		JLabel LB_Msg 	= B100_FrameParts.JLabelSet(  0,50,300,20,"以下のデータを取込もうとしています"	,11,0);
		main_fm.add(LB_Msg);
		
		Object[][] RtSetDataDefinition = RtSetDataDefinition();
		
		String[] columnNames01 = new String[RtSetDataDefinition.length+1];
		
		columnNames01[0] = "Fg";
		for(int i=0;i<RtSetDataDefinition.length;i++) {
			columnNames01[1+(int)RtSetDataDefinition[i][1]] = ""+RtSetDataDefinition[i][3];
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
		
		for(int i=0;i<RtSetDataDefinition.length;i++) {
			if("int".equals((String)RtSetDataDefinition[i][2])||"float".equals((String)RtSetDataDefinition[i][2])) {
				column = columnModel01.getColumn(1+(int)RtSetDataDefinition[i][1]);	column.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.rightCellRenderer());
			}else {
				column = columnModel01.getColumn(1+(int)RtSetDataDefinition[i][1]);	column.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.leftCellRenderer());
			}
		}
		//スクロール用設定
		JScrollPane scpn01 = B100_FrameParts.JScrollPaneSet(10,75,860,500,tb01);
		main_fm.add(scpn01);
		
		for(int i=0;i<EntryData.length;i++) {
			Object[] SetOb = new Object[RtSetDataDefinition.length+1];
			
			SetOb[0]	= false;
			for(int i01=0;i01<EntryData[i].length;i01++) {
				SetOb[(int)RtSetDataDefinition[i01][1]+1]=EntryData[i][(int)RtSetDataDefinition[i01][1]];
			}
			MainFmTableModel.addRow(SetOb);
		}
		
		RenewFg = true;
		main_fm.setVisible(true);
		
		//登録ボタン押下時の挙動
		entry_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				int RowCount = MainFmTableModel.getRowCount();
				String[][] GetData = new String[RowCount][RtSetDataDefinition().length];
				
				if(0<RowCount) {
					for(int i=0;i<RowCount;i++) {
						for(int i01=0;i01<RtSetDataDefinition().length;i01++) {
							GetData[i][i01] = ""+MainFmTableModel.getValueAt(i, i01+1);
						}
					}
					
					DataEntry(A00000_Main.ClWh,A00000_Main.ClCd,GetData);
					
					SetX=main_fm.getX();
					SetY=main_fm.getY();
	
					main_fm.setVisible(false);
					main_fm.dispose();
					WT100_ArrivalPlan_00_Search.ArrivalPlanSearch(0,0);
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
	
	public static void DataEntry(String TgtClWh,String TgtCLCd,String[][] GetData) {
		//荷主入荷予定番号を元に入荷予定番号必要数を取得
		//同一仕入れ先コード_同一入荷予定日_同一荷主入荷予定番号をもって1伝票のデータとして判断
		
		ArrayList<String> CheckClArrNoList 	= new ArrayList<String>();
		ArrayList<String> ClArrNoList 		= new ArrayList<String>();
		
		ArrayList<String> CheckArrNoList 	= new ArrayList<String>();
		ArrayList<String> ArrNoList 		= new ArrayList<String>();
		for(int i=0;i<GetData.length;i++) {
			if(null==GetData[i][ColSetHdClArrNo]) {GetData[i][ColSetHdClArrNo]="";}
			GetData[i][ColSetHdPlanDate] = B100_TextControl.TextToDate(GetData[i][ColSetHdPlanDate]);
			
			if(null==GetData[i][ColSetHdArrNo]||"".equals(GetData[i][ColSetHdArrNo])) {
				ClArrNoList.add(GetData[i][ColSetHdClArrNo]);
			}else {
				ArrNoList.add(GetData[i][ColSetHdArrNo]);
			}
		}
		ArrNoList	= B100_ArrayListControl.ArryListStringUniqueList(ArrNoList);
		
		//既に登録済みの荷主入荷予定番号はエラー
		//入荷予定番号指定されている場合ステータスが0未入荷以外はエラー
		Object[][] FromClArrNoArrivalPlanHdRt	= ArrivalPlanHdRt(TgtClWh,TgtCLCd,null,ClArrNoList);
		Object[][] FromArrNoArrivalPlanHdRt		= ArrivalPlanHdRt(TgtClWh,TgtCLCd,ArrNoList,null);
		
		//入荷予定番号指定されている場合明細行"削られている"可能性があるので一旦登録内容削除して再登録＝"一部修正データの取り扱いには非対応"
		//削除対象は状況：未入荷
		int DelCount = 0;
		for(int i=0;i<FromArrNoArrivalPlanHdRt.length;i++) {
			if(0==(int)FromArrNoArrivalPlanHdRt[i][T100_ArrivalPlanHdRt.ColFixFg]) {
				DelCount = DelCount+1;
			}
		}
		if(0<DelCount) {
			String Hdtgt_table 		= "WW0010ArrivalPlanHd";
			String Mstgt_table 		= "WW0011ArrivalPlanMs";
			String[] judg_field 	=  {"ClWh","ClCd","ArrNo"};
			String[][] judg_data 	= new String[DelCount][3];
			String TgtDB 			= "WANKO";
			
			DelCount = 0;
			for(int i=0;i<FromArrNoArrivalPlanHdRt.length;i++) {
				if(0==(int)FromArrNoArrivalPlanHdRt[i][T100_ArrivalPlanHdRt.ColFixFg]) {
					judg_data[DelCount][0]	= TgtClWh;
					judg_data[DelCount][1]	= TgtCLCd;
					judg_data[DelCount][2]	= (String)FromArrNoArrivalPlanHdRt[i][T100_ArrivalPlanHdRt.ColArrNo];
					//System.out.println(TgtClWh+":"+TgtCLCd+":"+(String)FromArrNoArrivalPlanHdRt[i][T100_ArrivalPlanHdRt.ColArrNo]);
				}
			}
			A100_DeleteSQL.DeleteSql(Hdtgt_table,judg_field,judg_data,TgtDB);
			A100_DeleteSQL.DeleteSql(Mstgt_table,judg_field,judg_data,TgtDB);
		}
		
		ArrayList<String> ErrMsg	= new ArrayList<String>();
		int MsEntryCount = 0;
		for(int i=0;i<GetData.length;i++) {
			if(null==GetData[i][ColSetHdArrNo]||"".equals(GetData[i][ColSetHdArrNo])) {
				boolean UnHitFg = true;
				if("".equals(GetData[i][ColSetHdClArrNo])) {
					
				}else {
					for(int i01=0;i01<FromClArrNoArrivalPlanHdRt.length;i01++) {
						String GetClArrNo		= (String)FromClArrNoArrivalPlanHdRt[i01][T100_ArrivalPlanHdRt.ColClArrNo];	//ヘッダ荷主予定番号
						String GetPlanDate		= (String)FromClArrNoArrivalPlanHdRt[i01][T100_ArrivalPlanHdRt.ColPlanDate];	//ヘッダ入荷予定日
						String GetSpCd			= (String)FromClArrNoArrivalPlanHdRt[i01][T100_ArrivalPlanHdRt.ColSpCd];		//ヘッダ仕入先CD
						
						if(GetClArrNo.equals(GetData[i][ColSetHdClArrNo])
								&& GetPlanDate.equals(GetData[i][ColSetHdPlanDate])
								&& GetSpCd.equals(GetData[i][ColSetHdSpCd])
								) {
							ErrMsg.add("仕入先コード:"+GetSpCd+" 入荷予定日:"+GetPlanDate+"　入荷予定番号:"+GetClArrNo+"のデータは既に登録済みの為スキップしました");
							UnHitFg = false;
							i01=FromClArrNoArrivalPlanHdRt.length+1;
						}
					}
				}
				if(UnHitFg) {
					CheckClArrNoList.add(GetData[i][ColSetHdSpCd]+"_"+GetData[i][ColSetHdPlanDate]+"_"+GetData[i][ColSetHdClArrNo]);
					MsEntryCount	= MsEntryCount+1;
				}
			}else {
				boolean UnHitFg = true;
				for(int i01=0;i01<FromArrNoArrivalPlanHdRt.length;i01++) {
					if(GetData[i][ColSetHdArrNo].equals(FromArrNoArrivalPlanHdRt[i01][T100_ArrivalPlanHdRt.ColArrNo])) {
						if(0==(int)FromArrNoArrivalPlanHdRt[i01][T100_ArrivalPlanHdRt.ColFixFg]) {
							
						}else {
							ErrMsg.add("入荷予定番号:"+GetData[i][ColSetHdArrNo]+"　は未入荷以外のステータスなのでスキップしました");
							UnHitFg = false;
						}
						i01=FromArrNoArrivalPlanHdRt.length+1;
					}
				}
				
				if(UnHitFg) {
					CheckArrNoList.add(""+GetData[i][ColSetHdArrNo]);
					MsEntryCount	= MsEntryCount+1;
				}
			}
		}
		
		CheckClArrNoList	= B100_ArrayListControl.ArryListStringUniqueList(CheckClArrNoList);
		CheckArrNoList		= B100_ArrayListControl.ArryListStringUniqueList(CheckArrNoList);
		int HdEntryCount = CheckClArrNoList.size()+CheckArrNoList.size();
		
		
		int[] ArrivalPlanNoRt = Tools100_ArrivalPlanNoGet.ArrivalPlanNoRt(CheckClArrNoList.size());
		
		String[] SetHdClWh			= new String[HdEntryCount];			//ヘッダ担当倉庫
		String[] SetHdClCd			= new String[HdEntryCount];			//ヘッダ荷主CD
		String[] SetHdArrNo			= new String[HdEntryCount];			//ヘッダ入荷予定NO（WMS採番）
		String[] SetHdClArrNo		= new String[HdEntryCount];			//ヘッダ荷主予定番号
		String[] SetHdPlanDate		= new String[HdEntryCount];			//ヘッダ入荷予定日
		String[] SetHdActualDate	= new String[HdEntryCount];			//ヘッダ入荷実績日
		String[] SetHdSpCd			= new String[HdEntryCount];			//ヘッダ仕入先CD
		String[] SetHdSpName01		= new String[HdEntryCount];			//ヘッダ仕入先名01
		String[] SetHdSpName02		= new String[HdEntryCount];			//ヘッダ仕入先名02
		String[] SetHdSpName03		= new String[HdEntryCount];			//ヘッダ仕入先名03
		String[] SetHdSpPost		= new String[HdEntryCount];			//ヘッダ仕入先郵便
		String[] SetHdSpAdd01		= new String[HdEntryCount];			//ヘッダ仕入先住所01
		String[] SetHdSpAdd02		= new String[HdEntryCount];			//ヘッダ仕入先住所02
		String[] SetHdSpAdd03		= new String[HdEntryCount];			//ヘッダ仕入先住所03
		String[] SetHdSpTel			= new String[HdEntryCount];			//ヘッダ仕入先電話
		String[] SetHdArCom01		= new String[HdEntryCount];			//ヘッダコメント1
		String[] SetHdArCom02		= new String[HdEntryCount];			//ヘッダコメント2
		String[] SetHdArCom03		= new String[HdEntryCount];			//ヘッダコメント3
		String[] SetHdFixFg			= new String[HdEntryCount];			//ヘッダ状況　完了:1 未完了:0　分納待ち:2  キャンセル:9
		String[] SetHdEntryDate		= new String[HdEntryCount];			//ヘッダ登録日
		String[] SetHdUpdateDate	= new String[HdEntryCount];			//ヘッダ更新日
		String[] SetHdEntryUser		= new String[HdEntryCount];			//ヘッダ登録者
		String[] SetHdUpdateUser	= new String[HdEntryCount];			//ヘッダ更新者
		
		String[] SetClWh			= new String[MsEntryCount];			//ヘッダ担当倉庫
		String[] SetClCd			= new String[MsEntryCount];			//ヘッダ荷主CD
		String[] SetArrNo			= new String[MsEntryCount];			//ヘッダ入荷予定NO（WMS採番）
		String[] SetMsNo			= new String[MsEntryCount];			//明細番号
		String[] SetItemCd			= new String[MsEntryCount];			//商品コード
		String[] SetClItemCd		= new String[MsEntryCount];			//荷主商品コード
		String[] SetJanCd			= new String[MsEntryCount];			//ソースマーク_BCD（バラ）
		String[] SetItemMdNo		= new String[MsEntryCount];			//商品型番
		String[] SetItemName		= new String[MsEntryCount];			//商品名
		String[] Setlot				= new String[MsEntryCount];			//ロット
		String[] SetExpDate			= new String[MsEntryCount];			//消費期限
		String[] SetPlanQty			= new String[MsEntryCount];			//予定数量
		String[] SetActualQty		= new String[MsEntryCount];			//実績数
		String[] SetActualDate		= new String[MsEntryCount];			//入荷日
		String[] SetCom01			= new String[MsEntryCount];			//コメント1
		String[] SetCom02			= new String[MsEntryCount];			//コメント2
		String[] SetEntryDate		= new String[MsEntryCount];			//登録日
		String[] SetUpdateDate		= new String[MsEntryCount];			//更新日
		String[] SetEntryUser		= new String[MsEntryCount];			//登録者
		String[] SetUpdateUser		= new String[MsEntryCount];			//更新者
		
		//System.out.println(MsEntryCount);
		
		String now_dtm = B100_DateTimeControl.dtmString2(B100_DateTimeControl.dtm()[1])[1];
		MsEntryCount = 0;
		for(int i01=0;i01<CheckClArrNoList.size();i01++) {
			int MsNo = 0;
			for(int i=0;i<GetData.length;i++) {
				if(null==GetData[i][ColSetHdArrNo]||"".equals(GetData[i][ColSetHdArrNo])) {
					String CST	= GetData[i][ColSetHdSpCd]+"_"+GetData[i][ColSetHdPlanDate]+"_"+GetData[i][ColSetHdClArrNo];
					if(CST.equals(CheckClArrNoList.get(i01))) {
						SetHdClWh[i01]			= GetData[i][ColSetHdClWh];			//ヘッダ担当倉庫
						SetHdClCd[i01]			= GetData[i][ColSetHdClCd];			//ヘッダ荷主CD
						SetHdArrNo[i01]			= ""+ArrivalPlanNoRt[i01];				//ヘッダ入荷予定NO（WMS採番）
						SetHdClArrNo[i01]		= GetData[i][ColSetHdClArrNo];		//ヘッダ荷主予定番号
						SetHdPlanDate[i01]		= GetData[i][ColSetHdPlanDate];		//ヘッダ入荷予定日
						SetHdActualDate[i01]	= "null";								//ヘッダ入荷実績日
						SetHdSpCd[i01]			= GetData[i][ColSetHdSpCd];			//ヘッダ仕入先CD
						SetHdSpName01[i01]		= GetData[i][ColSetHdSpName01];		//ヘッダ仕入先名01
						SetHdSpName02[i01]		= GetData[i][ColSetHdSpName02];		//ヘッダ仕入先名02
						SetHdSpName03[i01]		= GetData[i][ColSetHdSpName03];		//ヘッダ仕入先名03
						SetHdSpPost[i01]		= GetData[i][ColSetHdSpPost];			//ヘッダ仕入先郵便
						SetHdSpAdd01[i01]		= GetData[i][ColSetHdSpAdd01];		//ヘッダ仕入先住所01
						SetHdSpAdd02[i01]		= GetData[i][ColSetHdSpAdd02];		//ヘッダ仕入先住所02
						SetHdSpAdd03[i01]		= GetData[i][ColSetHdSpAdd03];		//ヘッダ仕入先住所03
						SetHdSpTel[i01]			= GetData[i][ColSetHdSpTel];			//ヘッダ仕入先電話
						SetHdArCom01[i01]		= GetData[i][ColSetHdArCom01];		//ヘッダコメント1
						SetHdArCom02[i01]		= GetData[i][ColSetHdArCom02];		//ヘッダコメント2
						SetHdArCom03[i01]		= GetData[i][ColSetHdArCom03];		//ヘッダコメント3
						SetHdFixFg[i01]			= "0";			//ヘッダ状況　完了:1 未完了:0　分納待ち:2  キャンセル:9
						SetHdEntryDate[i01]		= now_dtm;		//ヘッダ登録日
						SetHdUpdateDate[i01]	= now_dtm;		//ヘッダ更新日
						SetHdEntryUser[i01]		= "(" + A00000_Main.LoginUserId + ")" + A00000_Main.LoginUserName;			//ヘッダ登録者
						SetHdUpdateUser[i01]	= "(" + A00000_Main.LoginUserId + ")" + A00000_Main.LoginUserName;			//ヘッダ更新者
						
						SetHdPlanDate[i01]		= B100_DateTimeControl.DateFormat(SetHdPlanDate[i01]);
						SetHdSpPost[i01]		= B100_TextControl.num_only_String(SetHdSpPost[i01]);
						SetHdSpTel[i01]			= B100_TextControl.num_only_String(SetHdSpTel[i01]);
						
						MsNo = MsNo+1;
						
						SetClWh[MsEntryCount]			= GetData[i][ColSetHdClWh];		//ヘッダ担当倉庫
						SetClCd[MsEntryCount]			= GetData[i][ColSetHdClCd];		//ヘッダ荷主CD
						SetArrNo[MsEntryCount]			= ""+ArrivalPlanNoRt[i01];			//ヘッダ入荷予定NO（WMS採番）
						SetMsNo[MsEntryCount]			= ""+MsNo;							//明細番号
						SetItemCd[MsEntryCount]			= GetData[i][ColSetItemCd];		//商品コード
						SetClItemCd[MsEntryCount]		= GetData[i][ColSetClItemCd];		//荷主商品コード
						SetJanCd[MsEntryCount]			= GetData[i][ColSetJanCd];			//ソースマーク_BCD（バラ）
						SetItemMdNo[MsEntryCount]		= GetData[i][ColSetItemMdNo];		//商品型番
						SetItemName[MsEntryCount]		= GetData[i][ColSetItemName];		//商品名
						Setlot[MsEntryCount]			= GetData[i][ColSetlot];			//ロット
						SetExpDate[MsEntryCount]		= GetData[i][ColSetExpDate];		//消費期限
						SetPlanQty[MsEntryCount]		= GetData[i][ColSetPlanQty];		//予定数量
						SetActualQty[MsEntryCount]		= "0";								//実績数
						SetActualDate[MsEntryCount]		= "null";							//入荷日
						SetCom01[MsEntryCount]			= GetData[i][ColSetCom01];			//コメント1
						SetCom02[MsEntryCount]			= GetData[i][ColSetCom02];			//コメント2
						SetEntryDate[MsEntryCount]		= now_dtm;			//登録日
						SetUpdateDate[MsEntryCount]		= now_dtm;			//更新日
						SetEntryUser[MsEntryCount]		= "(" + A00000_Main.LoginUserId + ")" + A00000_Main.LoginUserName;	//登録者
						SetUpdateUser[MsEntryCount]		= "(" + A00000_Main.LoginUserId + ")" + A00000_Main.LoginUserName;	//更新者
						
						SetExpDate[MsEntryCount] =  B100_DateTimeControl.DateFormat(SetExpDate[MsEntryCount]);
						if("".equals(SetExpDate[MsEntryCount])) {SetExpDate[MsEntryCount]	= "null";}
						
						MsEntryCount = MsEntryCount+1;
					}
				}
			}
		}
		for(int i01=0;i01<CheckArrNoList.size();i01++) {
			int MsNo = 0;
			for(int i=0;i<GetData.length;i++) {
				if(null!=GetData[i][ColSetHdArrNo]&&!"".equals(GetData[i][ColSetHdArrNo])) {
					if(GetData[i][ColSetHdArrNo].equals(CheckArrNoList.get(i01))) {
						SetHdClWh[CheckClArrNoList.size()+i01]			= GetData[i][ColSetHdClWh];			//ヘッダ担当倉庫
						SetHdClCd[CheckClArrNoList.size()+i01]			= GetData[i][ColSetHdClCd];			//ヘッダ荷主CD
						SetHdArrNo[CheckClArrNoList.size()+i01]			= GetData[i][ColSetHdArrNo];			//ヘッダ入荷予定NO（WMS採番）
						SetHdClArrNo[CheckClArrNoList.size()+i01]		= GetData[i][ColSetHdClArrNo];		//ヘッダ荷主予定番号
						SetHdPlanDate[CheckClArrNoList.size()+i01]		= GetData[i][ColSetHdPlanDate];		//ヘッダ入荷予定日
						SetHdActualDate[CheckClArrNoList.size()+i01]	= "null";								//ヘッダ入荷実績日
						SetHdSpCd[CheckClArrNoList.size()+i01]			= GetData[i][ColSetHdSpCd];			//ヘッダ仕入先CD
						SetHdSpName01[CheckClArrNoList.size()+i01]		= GetData[i][ColSetHdSpName01];		//ヘッダ仕入先名01
						SetHdSpName02[CheckClArrNoList.size()+i01]		= GetData[i][ColSetHdSpName02];		//ヘッダ仕入先名02
						SetHdSpName03[CheckClArrNoList.size()+i01]		= GetData[i][ColSetHdSpName03];		//ヘッダ仕入先名03
						SetHdSpPost[CheckClArrNoList.size()+i01]		= GetData[i][ColSetHdSpPost];			//ヘッダ仕入先郵便
						SetHdSpAdd01[CheckClArrNoList.size()+i01]		= GetData[i][ColSetHdSpAdd01];		//ヘッダ仕入先住所01
						SetHdSpAdd02[CheckClArrNoList.size()+i01]		= GetData[i][ColSetHdSpAdd02];		//ヘッダ仕入先住所02
						SetHdSpAdd03[CheckClArrNoList.size()+i01]		= GetData[i][ColSetHdSpAdd03];		//ヘッダ仕入先住所03
						SetHdSpTel[CheckClArrNoList.size()+i01]			= GetData[i][ColSetHdSpTel];			//ヘッダ仕入先電話
						SetHdArCom01[CheckClArrNoList.size()+i01]		= GetData[i][ColSetHdArCom01];		//ヘッダコメント1
						SetHdArCom02[CheckClArrNoList.size()+i01]		= GetData[i][ColSetHdArCom02];		//ヘッダコメント2
						SetHdArCom03[CheckClArrNoList.size()+i01]		= GetData[i][ColSetHdArCom03];		//ヘッダコメント3
						SetHdFixFg[CheckClArrNoList.size()+i01]			= "0";			//ヘッダ状況　完了:1 未完了:0　分納待ち:2  キャンセル:9
						SetHdEntryDate[CheckClArrNoList.size()+i01]		= now_dtm;		//ヘッダ登録日
						SetHdUpdateDate[CheckClArrNoList.size()+i01]	= now_dtm;		//ヘッダ更新日
						SetHdEntryUser[CheckClArrNoList.size()+i01]		= "(" + A00000_Main.LoginUserId + ")" + A00000_Main.LoginUserName;			//ヘッダ登録者
						SetHdUpdateUser[CheckClArrNoList.size()+i01]	= "(" + A00000_Main.LoginUserId + ")" + A00000_Main.LoginUserName;			//ヘッダ更新者
						
						SetHdPlanDate[CheckClArrNoList.size()+i01]		= B100_DateTimeControl.DateFormat(SetHdPlanDate[i01]);
						SetHdSpPost[CheckClArrNoList.size()+i01]		= B100_TextControl.num_only_String(SetHdSpPost[i01]);
						SetHdSpTel[CheckClArrNoList.size()+i01]			= B100_TextControl.num_only_String(SetHdSpTel[i01]);
						MsNo=MsNo+1;
						SetClWh[MsEntryCount]			= GetData[i][ColSetHdClWh];		//ヘッダ担当倉庫
						SetClCd[MsEntryCount]			= GetData[i][ColSetHdClCd];		//ヘッダ荷主CD
						SetArrNo[MsEntryCount]			= GetData[i][ColSetHdArrNo];		//ヘッダ入荷予定NO（WMS採番）
						SetMsNo[MsEntryCount]			= ""+MsNo;							//明細番号
						SetItemCd[MsEntryCount]			= GetData[i][ColSetItemCd];		//商品コード
						SetClItemCd[MsEntryCount]		= GetData[i][ColSetClItemCd];		//荷主商品コード
						SetJanCd[MsEntryCount]			= GetData[i][ColSetJanCd];			//ソースマーク_BCD（バラ）
						SetItemMdNo[MsEntryCount]		= GetData[i][ColSetItemMdNo];		//商品型番
						SetItemName[MsEntryCount]		= GetData[i][ColSetItemName];		//商品名
						Setlot[MsEntryCount]			= GetData[i][ColSetlot];			//ロット
						SetExpDate[MsEntryCount]		= GetData[i][ColSetExpDate];		//消費期限
						SetPlanQty[MsEntryCount]		= GetData[i][ColSetPlanQty];		//予定数量
						SetActualQty[MsEntryCount]		= "0";								//実績数
						SetActualDate[MsEntryCount]		= "null";							//入荷日
						SetCom01[MsEntryCount]			= GetData[i][ColSetCom01];			//コメント1
						SetCom02[MsEntryCount]			= GetData[i][ColSetCom02];			//コメント2
						SetEntryDate[MsEntryCount]		= now_dtm;			//登録日
						SetUpdateDate[MsEntryCount]		= now_dtm;			//更新日
						SetEntryUser[MsEntryCount]		= "(" + A00000_Main.LoginUserId + ")" + A00000_Main.LoginUserName;	//登録者
						SetUpdateUser[MsEntryCount]		= "(" + A00000_Main.LoginUserId + ")" + A00000_Main.LoginUserName;	//更新者
						
						SetExpDate[MsEntryCount] =  B100_DateTimeControl.DateFormat(SetExpDate[MsEntryCount]);
						if("".equals(SetExpDate[MsEntryCount])) {SetExpDate[MsEntryCount]	= "null";}
						
						MsEntryCount = MsEntryCount+1;
					}
				}
			}
		}
		Object[][] HdSetOb = {
					 {"ClWh"		,"1"	,"0"	,"Key"	,SetHdClWh			}
					,{"ClCd"		,"1"	,"0"	,"Key"	,SetHdClCd			}
					,{"ArrNo"		,"1"	,"0"	,"Key"	,SetHdArrNo			}
					,{"ClArrNo"		,"1"	,"0"	,""		,SetHdClArrNo		}
					,{"PlanDate"	,"1"	,"0"	,""		,SetHdPlanDate		}
					,{"ActualDate"	,"1"	,"0"	,""		,SetHdActualDate	}
					,{"SpCd"		,"1"	,"0"	,""		,SetHdSpCd			}
					,{"SpName01"	,"1"	,"0"	,""		,SetHdSpName01		}
					,{"SpName02"	,"1"	,"0"	,""		,SetHdSpName02		}
					,{"SpName03"	,"1"	,"0"	,""		,SetHdSpName03		}
					,{"SpPost"		,"1"	,"0"	,""		,SetHdSpPost		}
					,{"SpAdd01"		,"1"	,"0"	,""		,SetHdSpAdd01		}
					,{"SpAdd02"		,"1"	,"0"	,""		,SetHdSpAdd02		}
					,{"SpAdd03"		,"1"	,"0"	,""		,SetHdSpAdd03		}
					,{"SpTel"		,"1"	,"0"	,""		,SetHdSpTel			}
					,{"ArCom01"		,"1"	,"0"	,""		,SetHdArCom01		}
					,{"ArCom02"		,"1"	,"0"	,""		,SetHdArCom02		}
					,{"ArCom03"		,"1"	,"0"	,""		,SetHdArCom03		}
					,{"FixFg"		,"1"	,"0"	,""		,SetHdFixFg			}
					,{"EntryDate"	,"1"	,"0"	,""		,SetHdEntryDate		}
					,{"UpdateDate"	,"1"	,"0"	,""		,SetHdUpdateDate	}
					,{"EntryUser"	,"1"	,"0"	,""		,SetHdEntryUser		}
					,{"UpdateUser"	,"1"	,"0"	,""		,SetHdUpdateUser	}
					};
		
		Object[][] MsSetOb = {
					 {"ClWh"		,"1"	,"0"	,"Key"	,SetClWh			}
					,{"ClCd"		,"1"	,"0"	,"Key"	,SetClCd			}
					,{"ArrNo"		,"1"	,"0"	,"Key"	,SetArrNo			}
					,{"MsNo"		,"1"	,"0"	,"Key"	,SetMsNo			}
					,{"ItemCd"		,"1"	,"0"	,""		,SetItemCd			}
					,{"ClItemCd"	,"1"	,"0"	,""		,SetClItemCd		}
					,{"JanCd"		,"1"	,"0"	,""		,SetJanCd			}
					,{"ItemMdNo"	,"1"	,"0"	,""		,SetItemMdNo		}
					,{"ItemName"	,"1"	,"0"	,""		,SetItemName		}
					,{"lot"			,"1"	,"0"	,""		,Setlot				}
					,{"ExpDate"		,"1"	,"0"	,""		,SetExpDate			}
					,{"PlanQty"		,"1"	,"0"	,""		,SetPlanQty			}
					,{"ActualQty"	,"1"	,"0"	,""		,SetActualQty		}
					,{"ActualDate"	,"1"	,"0"	,""		,SetActualDate		}
					,{"Com01"		,"1"	,"0"	,""		,SetCom01			}
					,{"Com02"		,"1"	,"0"	,""		,SetCom02			}
					,{"EntryDate"	,"1"	,"0"	,""		,SetEntryDate		}
					,{"UpdateDate"	,"1"	,"0"	,""		,SetUpdateDate		}
					,{"EntryUser"	,"1"	,"0"	,""		,SetEntryUser		}
					,{"UpdateUser"	,"1"	,"0"	,""		,SetUpdateUser		}
					};
		
		String Hd_tgt_table = "WW0010ArrivalPlanHd";
		String Hd_TgtDB = "WANKO";
		int Hd_non_msg_fg = 1;
		
		A100_InsertUpdateSQL.InsertUpdateSomeRecord(HdSetOb,Hd_tgt_table,Hd_TgtDB,Hd_non_msg_fg);
		
		String Ms_tgt_table = "WW0011ArrivalPlanMs";
		String Ms_TgtDB = "WANKO";
		int Ms_non_msg_fg = 0;
		
		A100_InsertUpdateSQL.InsertUpdateSomeRecord(MsSetOb,Ms_tgt_table,Ms_TgtDB,Ms_non_msg_fg);
		
		if(null!=ErrMsg&&0<ErrMsg.size()) {
			ErrView(ErrMsg);
		}
		
	}
	
	private static Object[][] ArrivalPlanHdRt(String TgtClWh,String TgtCLCd,ArrayList<String> ArrNo,ArrayList<String> ClArrNo){
		if(null==ArrNo		) {ArrNo	= new ArrayList<String>();}
		if(null==ClArrNo	) {ClArrNo	= new ArrayList<String>();}
		
		ArrayList<String> SearchClWh 			= new ArrayList<String>();		//ヘッダ担当倉庫
		ArrayList<String> SearchClCd 			= new ArrayList<String>();		//ヘッダ荷主CD
		ArrayList<String> SearchCLName01 		= new ArrayList<String>();		//ヘッダ荷主名
		ArrayList<String> SearchClGpCD 			= new ArrayList<String>();		//ヘッダ荷主グループCD
		ArrayList<String> SearchCLGpName01 		= new ArrayList<String>();		//ヘッダ荷主グループ標記名
		ArrayList<String> SearchArrNo 			= ArrNo;						//ヘッダ入荷予定NO
		ArrayList<String> SearchClArrNo 		= ClArrNo;						//ヘッダ荷主予定番号
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
		
		if(0==ArrNo.size()&&0==ClArrNo.size()) {
			
		}else {
			SearchClWh.add(TgtClWh);
			SearchClCd.add(TgtCLCd);
		}
		Object[][] ArrivalPlanHdRt = T100_ArrivalPlanHdRt.ArrivalPlanHdRt(
				SearchClWh,				//ヘッダ担当倉庫
				SearchClCd,				//ヘッダ荷主CD
				SearchCLName01,			//ヘッダ荷主名
				SearchClGpCD,			//ヘッダ荷主グループCD
				SearchCLGpName01,		//ヘッダ荷主グループ標記名
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
	
	private static void ErrView(ArrayList<String>ErrMsg){
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