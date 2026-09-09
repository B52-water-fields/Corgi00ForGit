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

public class WT100_OkuriData_05_ArrayEntrySourceDataView{
	static final int ColClCd			= 0;
	static final int ColInvoiceWhCd	= 1;
	static final int ColClDeliNo		= 2;
	static final int ColPlanDate		= 3;
	static final int ColClDeliCd		= 4;
	static final int ColCom01			= 5;
	static final int ColMsClItemCd	= 6;
	static final int ColMsLot			= 7;
	static final int ColMsExpDate		= 8;
	static final int ColMsPackingQty	= 9;
	static final int ColMsCom01		=10;
	
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	
	public static Object[][] RtOkuriDataArrayEntrySourceDataView(){
		Object[][] RtOkuriDataArrayEntrySourceDataView= {
									 {"ClCd"			,ColClCd				,"String"	,"荷主CD"			,""	}
									,{"InvoiceWhCd"		,ColInvoiceWhCd		,"String"	,"担当倉庫CD"		,""	}
									,{"ClDeliNo"		,ColClDeliNo			,"String"	,"荷主予定番号"		,""	}
									,{"PlanDate"		,ColPlanDate			,"Date"		,"出荷予定日"		,""	}
									,{"ClDeliCd"		,ColClDeliCd			,"String"	,"荷主荷届先CD"		,""	}
									,{"Com01"			,ColCom01				,"String"	,"コメント01"		,""	}
									,{"MsClItemCd"		,ColMsClItemCd		,"String"	,"明細荷主商品CD"	,""	}
									,{"MsPackingQty"	,ColMsPackingQty		,"int"		,"明細荷姿数量"		,""	}
									,{"MsCom01"			,ColMsCom01			,"String"	,"明細コメント01"	,""	}
									,{"MsLot"			,ColMsLot				,"String"	,"明細ロット指定"	,""	}
									,{"MsExpDate"		,ColMsExpDate			,"Date"		,"明細賞味期限指定"	,""	}
									};
		
		Object[][] Rt = new Object[RtOkuriDataArrayEntrySourceDataView.length][RtOkuriDataArrayEntrySourceDataView[0].length];
		
		for(int i=0;i<RtOkuriDataArrayEntrySourceDataView.length;i++) {
			for(int i01=0;i01<RtOkuriDataArrayEntrySourceDataView[i].length;i01++) {
				Rt[(int)RtOkuriDataArrayEntrySourceDataView[i][1]][i01]	= RtOkuriDataArrayEntrySourceDataView[i][i01];
			}
		}
		return Rt;
	}
	
	public static void OkuriDataArrayEntrySourceDataView(int x,int y,String[][] EntryData){
		A00000_Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		
		final JFrame main_fm = B100_FrameParts.FrameCreate(x,y,900,750,"Corgi00出荷予定取込（データ内容確認）　WT100_OkuriData_05_ArrayEntrySourceDataView","SP");
		JLabel userinfo = B100_FrameParts.UserInfo();
		JButton exit_btn = B100_FrameParts.ExitBtn();
		JButton entry_btn = B100_FrameParts.EntryBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		main_fm.add(entry_btn);
		
		JLabel LB_Msg 	= B100_FrameParts.JLabelSet( 10, 50,300,20,"以下のデータを取込もうとしています"	,11,0);
		main_fm.add(LB_Msg);
		
		Object[][] RtOkuriDataArrayEntrySourceDataView = WT100_OkuriData_05_ArrayEntrySourceDataView.RtOkuriDataArrayEntrySourceDataView();
		
		String[] columnNames01 = new String[RtOkuriDataArrayEntrySourceDataView.length+1];
		
		columnNames01[0] = "Fg";
		for(int i=0;i<RtOkuriDataArrayEntrySourceDataView.length;i++) {
			columnNames01[1+i] = ""+RtOkuriDataArrayEntrySourceDataView[i][3];
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
		
		for(int i=0;i<RtOkuriDataArrayEntrySourceDataView.length;i++) {
			if("int".equals((String)RtOkuriDataArrayEntrySourceDataView[i][2])||"float".equals((String)RtOkuriDataArrayEntrySourceDataView[i][2])) {
				column = columnModel01.getColumn(1+i);	column.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.rightCellRenderer());
			}else {
				column = columnModel01.getColumn(1+i);	column.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.leftCellRenderer());
			}
		}
		//スクロール用設定
		JScrollPane scpn01 = B100_FrameParts.JScrollPaneSet(10,75,860,500,tb01);
		main_fm.add(scpn01);
		
		for(int i=0;i<EntryData.length;i++) {
			Object[] SetOb = new Object[RtOkuriDataArrayEntrySourceDataView.length+1];
			SetOb[0] = false;
			SetOb[1+ColClCd]			= B100_TextControl.Trim(EntryData[i][ColClCd]);					//荷主CD
			SetOb[1+ColInvoiceWhCd]	= B100_TextControl.Trim(EntryData[i][ColInvoiceWhCd]);			//担当倉庫CD
			SetOb[1+ColClDeliNo]		= B100_TextControl.Trim(EntryData[i][ColClDeliNo]);				//荷主予定番号
			SetOb[1+ColPlanDate]		= B100_TextControl.TextToDate(EntryData[i][ColPlanDate]);		//出荷予定日
			SetOb[1+ColClDeliCd]		= B100_TextControl.Trim(EntryData[i][ColClDeliCd]);				//荷主荷届先CD
			SetOb[1+ColCom01]			= B100_TextControl.Trim(EntryData[i][ColCom01]);					//コメント01
			SetOb[1+ColMsClItemCd]	= B100_TextControl.Trim(EntryData[i][ColMsClItemCd]);				//明細荷主商品CD
			SetOb[1+ColMsPackingQty]	= B100_TextControl.TextToInt(EntryData[i][ColMsPackingQty]);	//明細荷姿数量
			SetOb[1+ColMsCom01]		= B100_TextControl.Trim(EntryData[i][ColMsCom01]);				//明細コメント01
			SetOb[1+ColMsLot]			= B100_TextControl.Trim(EntryData[i][ColMsLot]);					//明細ロット指定
			SetOb[1+ColMsExpDate]		= B100_TextControl.TextToDate(EntryData[i][ColMsExpDate]);		//明細賞味期限指定
			
			if(0<(int)SetOb[1+ColMsPackingQty] && !"".equals((String)SetOb[1+ColClDeliCd]) && !"".equals((String)SetOb[1+ColMsClItemCd])) {
				SetOb[1+ColMsPackingQty]	= ""+SetOb[1+ColMsPackingQty];
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
					//WT100_ArrivalPlan_06_ArrayEntrySetDataView.ArrivalPlanArrayEntrySetDataView(SetX+10,SetY+10,SetObRt);
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
				WT100_OkuriHd_00_Search.OkuriHdSearch(0,0);
			}
		});
	}
	
	private static Object[][] SetObRt(Object[][] CheckOb){
		Object[][] SetObRt = new Object[CheckOb.length][WT100_OkuriData_06_ArrayEntrySetDataView.RtSetDataDefinition().length];
		for(int i=0;i<SetObRt.length;i++) {
			
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.Colcl_cd]					= ""+CheckOb[i][ColClCd];					//荷主コード
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColInvoiceWHCD]			= ""+CheckOb[i][ColInvoiceWhCd];			//倉庫コード
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColOkuriNo]				= ""							;			//送り状番号
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColClDeliNo]				= ""+CheckOb[i][ColClDeliNo];				//荷主管理番号
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColPickupWHCD]			= ""+CheckOb[i][ColInvoiceWhCd];			//集荷倉庫CD
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColPurposeFG]				= "0";										//目的フラグ
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColPlanDate]				= ""+CheckOb[i][ColPlanDate];				//出荷予定日
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColShipDate]				= "";										//出荷実績日
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColSPPlanDate]			= "";										//着日指定
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColSPDate]					= "";										//着日実績
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColSPTimeFG]				= "";										//時間指定区分
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColSPTimeStr]				= "";										//時間指定開始
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColSPTimeEnd]				= "";										//時間指定終了
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColTotalWeight]			= (int)0;									//荷物重量
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColTotalSize]				= (float)0.0;								//荷物サイズ
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColTotalQty]				= (int)0;									//個口数
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColDeliveryTypeCd]		= "";										//運送タイプ01
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColDeliTypeName]			= "";										//運送タイプ名01
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColDeliveryTypeCd02]		= "";										//運送タイプ02
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColDeliTypeName02]		= "";										//運送タイプ名02
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColDeliveryTypeCd03]		= "";										//運送タイプ03
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColDeliTypeName03]		= "";										//運送タイプ名03
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColDeliveryTypeCd04]		= "";										//運送タイプ04
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColDeliTypeName04]		= "";										//運送タイプ名04
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColDeliveryTypeCd05]		= "";										//運送タイプ05
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColDeliTypeName05]		= "";										//運送タイプ名05
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColCodFG]					= (int)0;									//代引きフラグ
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColCodPayTotal]			= (int)0;									//代引き収受金額合計
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColCodPay]					= (int)0;									//代引き金額
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColCodConsumptionTax]	= (int)0;									//代引き消費税
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColChildrenFG]			= (int)0;									//赤黒区分
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColParentOkuriNo]			= "";										//親伝票番号
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColNiokuriCd]				= "";										//荷送り人コード
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColNiokuriDepartmentCd]	= "";										//部署CD
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColNiokuriName01]			= "";										//荷送り人名01
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColNiokuriName02]			= "";										//荷送り人名02
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColNiokuriName03]			= "";										//荷送り人名03
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColNiokuriPost]			= "";										//荷送り人郵便番号
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColNiokuriAdd01]			= "";										//荷送り人住所01
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColNiokuriAdd02]			= "";										//荷送り人住所02
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColNiokuriAdd03]			= "";										//荷送り人住所03
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColNioKuriTel]			= "";										//荷送り人TEL
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColNioKuriFax]			= "";										//荷送り人FAX
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColNioKuriMail]			= "";										//荷送り人MAIL
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColNiokuriMunicCd]		= "";										//荷送人市区町村CD
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColDeliCd]					= "";										//荷届け先コード
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColClDeliCd]				= ""+CheckOb[i][ColClDeliCd];				//荷主荷届け先コード
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColDeliDepartmentCd]		= "";										//部署CD
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColDeliName01]			= "";										//荷届け先名01
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColDeliName02]			= "";										//荷届け先名02
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColDeliName03]			= "";										//荷届け先名03
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColDeliPost]				= "";										//荷届け先郵便番号
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColDeliAdd01]				= "";										//荷届け先住所01
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColDeliAdd02]				= "";										//荷届け先住所02
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColDeliAdd03]				= "";										//荷届け先住所03
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColDeliTel]				= "";										//荷届け先TEL
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColDeliFax]				= "";										//荷届け先FAX
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColDeliMail]				= "";										//荷届け先MAIL
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColDeliMunicCd]			= "";										//荷届先市区町村CD
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColCom01]					= "";										//コメント01
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColCom02]					= "";										//コメント02
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColCom03]					= "";										//コメント03
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColCom04]					= "";										//コメント04
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColCom05]					= "";										//コメント05
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColStatus]					= (int)0;									//状況
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColTaxFg]					= (int)0;									//税区分
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColTaxRate]				= B100_DefaultVariable.NormalTaxRate;		//税率
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColDeliFee]				= (int)0;									//運賃
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColAddDeliFee01]			= (int)0;									//付帯費用1
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColAddDeliFee02]			= (int)0;									//付帯費用2
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColAddDeliFee03]			= (int)0;									//付帯費用3
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColHaighWayFee01]			= (int)0;									//実費精算分1（内税）
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColHaighWayFee02]			= (int)0;									//実費精算分2（内税）
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColConsumptionTax]		= (int)0;									//消費税
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColWithOutTaxTotal]		= (int)0;									//税別合計金額
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColTotalFee]				= (int)0;									//税込請求額合計
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColFeeFixFG]				= (int)0;									//金額確定フラグ
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColFeeFixDate]			= "";										//金額確定日時
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColReceiptStampFG]		= (int)0;									//受領印チェック
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColReceiptStampDate]		= "";										//受領印日時
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColInvoiceStatus]			= (int)0;									//請求ステータス
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColEntryDate]				= "";										//登録日
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColUpdateDate]			= "";										//更新日
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColEntryUser]				= "";										//登録者
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColUpdateUser]			= "";										//更新者
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColEntryPG]				= "";										//登録プログラム
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColUpdatePG]				= "";										//更新プログラム
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColUseFeeBasePtCd]		= "";										//適用運賃タリフCD
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColWmsStatus]				= (int)0;									//在庫管理ステータス
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColWmsShipDate]			= "";										//倉庫出荷日
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColCourseGpCd]			= "";										//コースグループコード
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColCourseCD]				= "";										//一次配車コースコード
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColCourseCDEda]			= "";										//一次配車コースコード枝番
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColPitGrp]					= "";										//一次配車払出ピットグループ
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColPit01]					= "";										//一次配車払出ピット01
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColPit02]					= "";										//一次配車払出ピット02
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColPit03]					= "";										//一次配車払出ピット03
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColPit04]					= "";										//一次配車払出ピット04
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColPit05]					= "";										//一次配車払出ピット05
			
			/*
			SetObRt[i][WT100_OkuriData_06_ArrayEntrySetDataView.ColMscl_cd]	= "";	//荷主コード"					,""}
			,{"MsInvoiceWHCD"		,ColMsInvoiceWHCD]	= "";	//倉庫コード"					,""}
			,{"MsOkuriNo"			,ColMsOkuriNo					,"String"	,"送り状番号"					,""}
			,{"MsMsNo"				,ColMsMsNo						,"int"		,"明細番号"						,""}
			,{"MsDeliNo"			,ColMsDeliNo					,"String"	,"出荷番号"						,""}
			,{"MsDelliMsNo"			,ColMsDelliMsNo				,"int"		,"出荷番号明細番号"				,""}
			,{"MsClOrderNo"			,ColMsClOrderNo				,"String"	,"荷主管理番号"					,""}
			,{"MsClGpCd"			,ColMsClGpCd					,"String"	,"荷主グループコード"			,""}
			,{"MsItemCd"			,ColMsItemCd					,"String"	,"商品コード"					,""}
			,{"MsItemName01"		,ColMsItemName01				,"String"	,"品名01"						,""}
			,{"MsItemName02"		,ColMsItemName02				,"String"	,"品名02"						,""}
			,{"MsItemName03"		,ColMsItemName03				,"String"	,"品名03"						,""}
			,{"MsUnitWeight"		,ColMsUnitWeight				,"float"	,"単位重量"						,""}
			,{"MsUnitSize"			,ColMsUnitSize				,"float"	,"単位サイズ"					,""}
			,{"MsQty"				,ColMsQty						,"int"		,"個数"							,""}
			,{"MsPackingQty"		,ColMsPackingQty				,"int"		,"荷姿数量"						,""}
			,{"MsUnitName"			,ColMsUnitName				,"String"	,"明細単位"						,""}
			,{"MsSubTotalWeight"	,ColMsSubTotalWeight			,"float"	,"明細重量"						,""}
			,{"MsSubTotalSize"		,ColMsSubTotalSize			,"float"	,"明細サイズ"					,""}
			,{"MsUnitPrice"			,ColMsUnitPrice				,"float"	,"単価"							,""}
			,{"MsSubTotalPrice"		,ColMsSubTotalPrice			,"float"	,"金額"							,""}
			,{"MsCategoryCd"		,ColMsCategoryCd				,"String"	,"商品分類"						,""}
			,{"MsCategoryName"		,ColMsCategoryName			,"String"	,"商品分類名"					,""}
			,{"MsTildFG"			,ColMsTildFG					,"String"	,"温度区分"						,""}
			,{"MsTildName"			,ColMsTildName				,"String"	,"温度区分名"					,""}
			,{"MsCom01"				,ColMsCom01					,"String"	,"コメント01"					,""}
			,{"MsCom02"				,ColMsCom02					,"String"	,"コメント02"					,""}
			,{"MsCom03"				,ColMsCom03					,"String"	,"コメント03"					,""}
			,{"MsCom04"				,ColMsCom04					,"String"	,"コメント04"					,""}
			,{"MsCom05"				,ColMsCom05					,"String"	,"コメント05"					,""}
			,{"MsEntryDate"			,ColMsEntryDate				,"DateTime"	,"登録日"						,""}
			,{"MsUpdateDate"		,ColMsUpdateDate				,"DateTime"	,"更新日"						,""}
			,{"MsEntryUser"			,ColMsEntryUser				,"String"	,"登録者"						,""}
			,{"MsUpdateUser"		,ColMsUpdateUser				,"String"	,"更新者"						,""}
			,{"MsLot"				,ColMsLot						,"String"	,"ロット指定"					,""}
			,{"MsExpDate"			,ColMsExpDate					,"Date"		,"賞味期限指定"					,""}
			,{"MsPackingType"		,ColMsPackingType				,"int"		,"荷姿タイプ"					,""}
			,{"MsClItemCd"			,ColMsClItemCd				,"String"	,"荷主商品CD"					,""}
			,{"MsItemMDNo"			,ColMsItemMDNo				,"String"	,"型番"							,""}
			,{"MsJanCd"				,ColMsJanCd					,"String"	,"JanCd"						,""}
			
			*/
			
			
			
			
		}
		
		
		
		
		
		
		
		
		
		ArrayList<String> TgtClCD 		= new ArrayList<String>();
		ArrayList<String> TgtCL_DECD 	= new ArrayList<String>();
		ArrayList<String> TgtClItemCd	= new ArrayList<String>();
		
		String[] ClGp = new String[CheckOb.length];
		
		for(int i=0;i<CheckOb.length;i++) {
			TgtClCD.add(""+CheckOb[i][ColClCd]);
			TgtCL_DECD.add(""+CheckOb[i][ColClDeliCd]);
			TgtClItemCd.add(""+CheckOb[i][ColMsClItemCd]);
			ClGp[i]	= "";
		}
		
		//対象となる荷主マスタ取得
		Object[][] ClMstRt	= ClMstRt(TgtClCD);

		ArrayList<String> TgtClGpCD 	= new ArrayList<String>();
		for(int i=0;i<ClMstRt.length;i++) {
			TgtClGpCD.add((String)ClMstRt[i][M100_ClMstRt.ColClGpCD]);
		}
		for(int i=0;i<CheckOb.length;i++) {
			for(int i01=0;i01<ClMstRt.length;i01++) {
				if((""+CheckOb[i][ColClCd]).equals((String)ClMstRt[i01][M100_ClMstRt.Colcl_cd])) {
					ClGp[i]	= (String)ClMstRt[i01][M100_ClMstRt.ColClGpCD];
					i01=ClMstRt.length+1;
				}
			}
		}
		
		//届先変換マスタ取得
		Object[][] DeliveryComversionMstRt	= DeliveryComversionMstRt(TgtClGpCD,TgtCL_DECD);
		for(int i=0;i<CheckOb.length;i++) {
			for(int i01=0;i01<DeliveryComversionMstRt.length;i01++) {
				if((""+CheckOb[i][ColClDeliCd]).equals((String)DeliveryComversionMstRt[i01][M100_DeliveryComversionMstRt.ColCL_DECD])
						 && ClGp[i].equals((String)DeliveryComversionMstRt[i01][M100_DeliveryComversionMstRt.ColClGpCD])
						 ) {
					i01=DeliveryComversionMstRt.length+1;
				}
			}
		}
		
		//商品マスタ取得
		Object[][] ItemMstRt	= ItemMstRt(TgtClCD,TgtClItemCd);
		//商品変換マスタ取得
		Object[][] ItemComversionMstRt	= ItemComversionMstRt(TgtClGpCD,TgtClCD,TgtClItemCd);
		for(int i=0;i<CheckOb.length;i++) {
			boolean UnHitFg	= true;
			for(int i01=0;i01<ItemMstRt.length;i01++) {
				if((""+CheckOb[i][ColMsClItemCd]).equals((String)(String)ItemMstRt[i01][M100_ItemMstRt.ColClItemCd])
						&& ClGp[i].equals((String)ItemMstRt[i01][M100_ItemMstRt.ColClGpCd])
						) {
					UnHitFg	= false;
					i01=ItemMstRt.length+1;
				}
			}
			if(UnHitFg) {
				for(int i01=0;i01<ItemComversionMstRt.length;i01++) {
					if((""+CheckOb[i][ColMsClItemCd]).equals((String)ItemComversionMstRt[i01][M100_ItemComversionMstRt.ColClItemCd])
							&& ClGp[i].equals((String)ItemComversionMstRt[i01][M100_ItemComversionMstRt.ColClGpCd])
							&& (""+CheckOb[i][ColClCd]).equals((String)ItemComversionMstRt[i01][M100_ItemComversionMstRt.ColClCd])
							) {
						UnHitFg	= false;
						i01=ItemComversionMstRt.length+1;
					}
				}
			}
		}
		
		return SetObRt;
	}
	
	public static ArrayList<String> ErrCheck(Object[][] CheckOb){
		ArrayList<String> ErrMsg = new ArrayList<String>();
		
		ArrayList<String> TgtClCD 		= new ArrayList<String>();
		ArrayList<String> TgtCL_DECD 	= new ArrayList<String>();
		ArrayList<String> TgtClItemCd	= new ArrayList<String>();
		
		String[] ClGp = new String[CheckOb.length];
		
		for(int i=0;i<CheckOb.length;i++) {
			TgtClCD.add(""+CheckOb[i][ColClCd]);
			TgtCL_DECD.add(""+CheckOb[i][ColClDeliCd]);
			TgtClItemCd.add(""+CheckOb[i][ColMsClItemCd]);
			ClGp[i]	= "";
		}
		
		//対象となる荷主マスタ取得
		Object[][] ClMstRt	= ClMstRt(TgtClCD);
		if(0==ClMstRt.length) {
			ErrMsg.add("荷主マスタの特定に失敗しました");
		}
		ArrayList<String> TgtClGpCD 	= new ArrayList<String>();
		for(int i=0;i<ClMstRt.length;i++) {
			TgtClGpCD.add((String)ClMstRt[i][M100_ClMstRt.ColClGpCD]);
		}
		for(int i=0;i<CheckOb.length;i++) {
			boolean UnHitFg	= true;
			for(int i01=0;i01<ClMstRt.length;i01++) {
				if((""+CheckOb[i][ColClCd]).equals((String)ClMstRt[i01][M100_ClMstRt.Colcl_cd])) {
					UnHitFg	= false;
					ClGp[i]	= (String)ClMstRt[i01][M100_ClMstRt.ColClGpCD];
					if(!(""+CheckOb[i][ColInvoiceWhCd]).equals((String)ClMstRt[i01][M100_ClMstRt.ColWHCD])) {
						int wint = i+1;
						ErrMsg.add(wint+"行目エラー 荷主CD("+CheckOb[i][ColClCd]+")の担当倉庫は("+ClMstRt[i01][M100_ClMstRt.ColWHCD]+")です("+CheckOb[i][ColInvoiceWhCd]+")は担当倉庫やあらへん");
					}
					i01=ClMstRt.length+1;
				}
			}
			if(UnHitFg) {
				int wint = i+1;
				ErrMsg.add(wint+"行目エラー ("+CheckOb[i][ColClCd]+")は荷主CDとして登録されてないで");
			}
		}
		//届先変換マスタ取得
		Object[][] DeliveryComversionMstRt	= DeliveryComversionMstRt(TgtClGpCD,TgtCL_DECD);
		for(int i=0;i<CheckOb.length;i++) {
			boolean UnHitFg	= true;
			for(int i01=0;i01<DeliveryComversionMstRt.length;i01++) {
				if((""+CheckOb[i][ColClDeliCd]).equals((String)DeliveryComversionMstRt[i01][M100_DeliveryComversionMstRt.ColCL_DECD])
						 && ClGp[i].equals((String)DeliveryComversionMstRt[i01][M100_DeliveryComversionMstRt.ColClGpCD])
						 ) {
					UnHitFg	= false;
					i01=DeliveryComversionMstRt.length+1;
				}
			}
			if(UnHitFg) {
				int wint = i+1;
				ErrMsg.add(wint+"行目エラー ("+CheckOb[i][ColClDeliCd]+")は荷主届先CDとして登録されてないで");
			}
		}
		
		//商品マスタ取得
		Object[][] ItemMstRt	= ItemMstRt(TgtClCD,TgtClItemCd);
		//商品変換マスタ取得
		Object[][] ItemComversionMstRt	= ItemComversionMstRt(TgtClGpCD,TgtClCD,TgtClItemCd);
		for(int i=0;i<CheckOb.length;i++) {
			boolean UnHitFg	= true;
			for(int i01=0;i01<ItemMstRt.length;i01++) {
				if((""+CheckOb[i][ColMsClItemCd]).equals((String)(String)ItemMstRt[i01][M100_ItemMstRt.ColClItemCd])
						&& ClGp[i].equals((String)ItemMstRt[i01][M100_ItemMstRt.ColClGpCd])
						) {
					UnHitFg	= false;
					i01=ItemMstRt.length+1;
				}
			}
			if(UnHitFg) {
				for(int i01=0;i01<ItemComversionMstRt.length;i01++) {
					if((""+CheckOb[i][ColMsClItemCd]).equals((String)ItemComversionMstRt[i01][M100_ItemComversionMstRt.ColClItemCd])
							&& ClGp[i].equals((String)ItemComversionMstRt[i01][M100_ItemComversionMstRt.ColClGpCd])
							&& (""+CheckOb[i][ColClCd]).equals((String)ItemComversionMstRt[i01][M100_ItemComversionMstRt.ColClCd])
							) {
						UnHitFg	= false;
						i01=ItemComversionMstRt.length+1;
					}
				}
			}
			if(UnHitFg) {
				int wint = i+1;
				ErrMsg.add(wint+"行目エラー ("+CheckOb[i][ColMsClItemCd]+")は商品変換マスタとして登録されてないで");
			}
		}
		return ErrMsg;
	}
	
	
	private static Object[][] CheckObTrim(Object[][] CheckOb){
		for(int i=0;i<CheckOb.length;i++) {
			CheckOb[i][ColClCd]			= B100_TextControl.Trim((String)CheckOb[i][ColClCd]);					//荷主CD
			CheckOb[i][ColInvoiceWhCd]	= B100_TextControl.Trim((String)CheckOb[i][ColInvoiceWhCd]);			//担当倉庫CD
			CheckOb[i][ColClDeliNo]		= B100_TextControl.Trim((String)CheckOb[i][ColClDeliNo]);				//荷主予定番号
			CheckOb[i][ColPlanDate]		= B100_TextControl.TextToDate((String)CheckOb[i][ColPlanDate]);		//出荷予定日
			CheckOb[i][ColClDeliCd]		= B100_TextControl.Trim((String)CheckOb[i][ColClDeliCd]);				//荷主荷届先CD
			CheckOb[i][ColCom01]			= B100_TextControl.Trim((String)CheckOb[i][ColCom01]);				//コメント01
			CheckOb[i][ColMsClItemCd]		= B100_TextControl.Trim((String)CheckOb[i][ColMsClItemCd]);			//明細荷主商品CD
			CheckOb[i][ColMsPackingQty]	= B100_TextControl.TextToInt((String)CheckOb[i][ColMsPackingQty]);	//明細荷姿数量
			CheckOb[i][ColMsCom01]			= B100_TextControl.Trim((String)CheckOb[i][ColMsCom01]);				//明細コメント01
			CheckOb[i][ColMsLot]			= B100_TextControl.Trim((String)CheckOb[i][ColMsLot]);				//明細ロット指定
			CheckOb[i][ColMsExpDate]		= B100_TextControl.TextToDate((String)CheckOb[i][ColMsExpDate]);		//明細賞味期限指定
		}
		return CheckOb;
	}
	
	private static Object[][] ItemMstRt(ArrayList<String> TgtClCd,ArrayList<String> TgtClItemCd){
		ArrayList<String> SearchClCd				= TgtClCd;					//荷主コード
		ArrayList<String> SearchClGpCd 				= new ArrayList<String>();	//荷主グループコード
		ArrayList<String> SearchItemCd 				= new ArrayList<String>();	//商品コード
		ArrayList<String> SearchClItemCd 			= TgtClItemCd;				//荷主商品コード
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
		
		Object[][] ItemMstRt = M100_ItemMstRt.ItemMstRt(
				SearchClCd,				//荷主コード
				SearchClGpCd,			//荷主グループコード
				SearchItemCd,			//商品コード
				SearchClItemCd,			//荷主商品コード
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
	
	private static Object[][] ItemComversionMstRt(ArrayList<String> TgtClGpCd,ArrayList<String> TgtClCd,ArrayList<String> TgtClItemCd){
		ArrayList<String> SearchClGpCd 		= TgtClGpCd;					//荷主グループコード
		ArrayList<String> SearchClCd 		= TgtClCd;						//荷主コード
		ArrayList<String> SearchItemCd 		= new ArrayList<String>();		//商品コード
		ArrayList<String> SearchClItemCd 	= TgtClItemCd;					//荷主商品コード
		ArrayList<String> SearchItemName 	= new ArrayList<String>();		//商品名
		boolean AllSearch = false;
		
		Object[][] ItemComversionMstRt = M100_ItemComversionMstRt.ItemComversionMstRt(
				SearchClGpCd,			//荷主グループコード
				SearchClCd,				//荷主コード
				SearchItemCd,			//商品コード
				SearchClItemCd,			//荷主商品コード
				SearchItemName,			//商品名
				AllSearch);
		
		return ItemComversionMstRt;
	}
	
	
	private static Object[][] DeliveryComversionMstRt(ArrayList<String> TgtClGpCD,ArrayList<String> TgtCL_DECD){
		ArrayList<String> SearchClGpCD 				= TgtClGpCD;
		ArrayList<String> SearchCLGpName 			= new ArrayList<String>();
		ArrayList<String> SearchCL_DECD 			= TgtCL_DECD;
		ArrayList<String> SearchDECD 				= new ArrayList<String>();
		ArrayList<String> SearchDepartmentCd 		= new ArrayList<String>();
		ArrayList<String> SearchDEName 				= new ArrayList<String>();
		ArrayList<String> SearchPost 				= new ArrayList<String>();
		ArrayList<String> SearchAdd 				= new ArrayList<String>();
		ArrayList<String> SearchTel 				= new ArrayList<String>();
		ArrayList<String> SearchFax 				= new ArrayList<String>();
		ArrayList<String> SearchMail 				= new ArrayList<String>();
		ArrayList<String> SearchSetName 			= new ArrayList<String>();
		ArrayList<String> SearchCom 				= new ArrayList<String>();
		ArrayList<String> SearchDelFg 				= new ArrayList<String>();
		ArrayList<String> SearchMstPriorityFirstFg	= new ArrayList<String>();
		boolean AllSearch = false;
		
		Object[][] DeliveryComversionMstRt = M100_DeliveryComversionMstRt.DeliveryComversionMstRt(
							SearchClGpCD,
							SearchCLGpName,
							SearchCL_DECD,
							SearchDECD,
							SearchDepartmentCd,
							SearchDEName,
							SearchPost,
							SearchAdd,
							SearchTel,
							SearchFax,
							SearchMail,
							SearchSetName,
							SearchCom,
							SearchDelFg,
							SearchMstPriorityFirstFg,
							AllSearch);
		
		return DeliveryComversionMstRt;
	}
	
	private static Object[][] ClMstRt(ArrayList<String> TgtClCD){
		ArrayList<String> SearchClGpCD = new ArrayList<String>();
		ArrayList<String> SearchCLCD = TgtClCD;
		ArrayList<String> SearchCLName = new ArrayList<String>();
		ArrayList<String> SearchPost = new ArrayList<String>();
		ArrayList<String> searchAdd = new ArrayList<String>();
		ArrayList<String> SearchTel = new ArrayList<String>();
		ArrayList<String> SearchFax = new ArrayList<String>();
		ArrayList<String> SearchMail = new ArrayList<String>();
		ArrayList<String> SearchCom = new ArrayList<String>();
		ArrayList<String> SearchWHCD = new ArrayList<String>();
		boolean AllSearch = false;
		
		Object[][] ClMstRt = M100_ClMstRt.ClMstRt(
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
		
		return ClMstRt;
	}
	
	private static void ErrView(ArrayList<String>ErrMsg) {
		//必要フォルダを生成する
		String FLD_PATH = A00000_Main.MainFLD+"\\OkuriDataControl";
		B100_FolderCheck.FLD_CHECK(FLD_PATH);
		FLD_PATH = A00000_Main.MainFLD+"\\OkuriDataControl\\PlanEntry";
		B100_FolderCheck.FLD_CHECK(FLD_PATH);
		FLD_PATH = A00000_Main.MainFLD+"\\OkuriDataControl\\PlanEntry\\Err";
		B100_FolderCheck.FLD_CHECK(FLD_PATH);
		FLD_PATH = A00000_Main.MainFLD+"\\OkuriDataControl\\PlanEntry\\BK";
		B100_FolderCheck.FLD_CHECK(FLD_PATH);
		
		//ファイルに出力
		String NowDTM=B100_DateTimeControl.dtmString2(B100_DateTimeControl.dtm()[1])[1].replace(" ", "").replace("/", "").replace(":", "");
		
		FLD_PATH = A00000_Main.MainFLD+"\\OkuriDataControl\\PlanEntry\\Err";
		
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