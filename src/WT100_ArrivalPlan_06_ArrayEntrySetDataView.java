import java.awt.Font;
import java.awt.event.ActionEvent;
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
				,{"SetPlanQty"		,ColSetPlanQty		,"int"		,"予定数量"}	//※バラ数量
				,{"SetActualQty"	,ColSetActualQty		,"int"		,"実績数"}
				,{"SetActualDate"	,ColSetActualDate		,"DateTime"	,"入荷日"}
				,{"SetCom01"		,ColSetCom01			,"String"	,"コメント1"}
				,{"SetCom02"		,ColSetCom02			,"String"	,"コメント2"}
				,{"UnitType"		,ColUnitType			,"int"		,"荷姿タイプ"}
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
		
		final JFrame main_fm = B100_FrameParts.FrameCreate(x,y,900,750,"Corgi00入荷予定取込（登録データ確認）","NK");
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
			columnNames01[1+i] = ""+RtSetDataDefinition[i][3];
		}
		
		//編集可能カラムの指定
		B100_TableControl.RenewTgt = new int[1];
		B100_TableControl.RenewTgt[0] = 0;

		final DefaultTableModel tableModel_ms01 = new B100_TableControl.MyTableModel01(columnNames01,0);
		
		final JTable tb01 = new JTable(tableModel_ms01);
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
				column = columnModel01.getColumn(1+i);	column.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.rightCellRenderer());
			}else {
				column = columnModel01.getColumn(1+i);	column.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.leftCellRenderer());
			}
		}
		//スクロール用設定
		JScrollPane scpn01 = B100_FrameParts.JScrollPaneSet(10,75,860,500,tb01);
		main_fm.add(scpn01);
		
		for(int i=0;i<EntryData.length;i++) {
			Object[] SetOb = new Object[RtSetDataDefinition.length+1];
			
			SetOb[0]	= false;
			for(int i01=0;i01<EntryData[i].length;i01++) {
				SetOb[i01+1]=EntryData[i][i01];
			}
			tableModel_ms01.addRow(SetOb);
		}
		
		RenewFg = true;
		main_fm.setVisible(true);
		
		//登録ボタン押下時の挙動
		entry_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				int RowCount = tableModel_ms01.getRowCount();
				String[][] GetData = new String[RowCount][RtSetDataDefinition().length];
				
				if(0<RowCount) {
					for(int i=0;i<RowCount;i++) {
						for(int i01=0;i01<RtSetDataDefinition().length;i01++) {
							GetData[i][i01] = ""+tableModel_ms01.getValueAt(i, i01+1);
						}
					}
					
					DataEntry(GetData);
					
					SetX=main_fm.getX();
					SetY=main_fm.getY();
	
					main_fm.setVisible(false);
					main_fm.dispose();
					WT100_ArrivalPlan_00_Search.ArrivalPlanSearch(0,0);
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
				WT100_ArrivalPlan_00_Search.ArrivalPlanSearch(0,0);
			}
		});
	}
	
	private static void DataEntry(String[][] GetData) {
		//荷主入荷予定番号を元に入荷予定番号必要数を取得
		//同一仕入れ先コード_同一入荷予定日_同一入荷予定番号をもって1伝票のデータとして判断
		
		ArrayList<String> CheckClArrNoList = new ArrayList<String>();
		for(int i=0;i<GetData.length;i++) {
			CheckClArrNoList.add(GetData[i][ColSetHdSpCd]+"_"+GetData[i][ColSetHdPlanDate]+"_"+GetData[i][ColSetHdClArrNo]);
		}
		CheckClArrNoList = B100_ArrayListControl.ArryListStringUniqueList(CheckClArrNoList);
		
		int HdEntryCount = CheckClArrNoList.size();
		int MsEntryCount = GetData.length;
		
		int[] ArrivalPlanNoRt = Tooles100_ArrivalPlan.ArrivalPlanNoRt(HdEntryCount);
		
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
		
		String now_dtm = B100_DateTimeControl.dtmString2(B100_DateTimeControl.dtm()[1])[1];
		MsEntryCount = 0;
		
		for(int i01=0;i01<CheckClArrNoList.size();i01++) {
			int MsNo = 0;
			for(int i=0;i<GetData.length;i++) {
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
		
	}
}