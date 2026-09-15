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

public class WT100_OkuriData_06_ArrayEntrySetDataView{
	//送り状データ登録する エラーチェックは済んでいいる前提
	static final int Colcl_cd					=  0;
	static final int ColInvoiceWHCD			=  1;
	static final int ColOkuriNo				=  2;
	static final int ColClDeliNo				=  3;
	static final int ColPickupWHCD			=  4;
	static final int ColPurposeFG				=  5;
	static final int ColPlanDate				=  6;
	static final int ColShipDate				=  7;
	static final int ColSPPlanDate			=  8;
	static final int ColSPDate					=  9;
	static final int ColSPTimeFG				= 10;
	static final int ColSPTimeStr				= 11;
	static final int ColSPTimeEnd				= 12;
	static final int ColTotalWeight			= 13;
	static final int ColTotalSize				= 14;
	static final int ColTotalQty				= 15;
	static final int ColDeliveryTypeCd		= 16;
	static final int ColDeliTypeName			= 17;
	static final int ColDeliveryTypeCd02		= 18;
	static final int ColDeliTypeName02		= 19;
	static final int ColDeliveryTypeCd03		= 20;
	static final int ColDeliTypeName03		= 21;
	static final int ColDeliveryTypeCd04		= 22;
	static final int ColDeliTypeName04		= 23;
	static final int ColDeliveryTypeCd05		= 24;
	static final int ColDeliTypeName05		= 25;
	static final int ColCodFG					= 26;
	static final int ColCodPayTotal			= 27;
	static final int ColCodPay					= 28;
	static final int ColCodConsumptionTax	= 29;
	static final int ColChildrenFG			= 30;
	static final int ColParentOkuriNo			= 31;
	static final int ColNiokuriCd				= 32;
	static final int ColNiokuriDepartmentCd	= 33;
	static final int ColNiokuriName01			= 34;
	static final int ColNiokuriName02			= 35;
	static final int ColNiokuriName03			= 36;
	static final int ColNiokuriPost			= 37;
	static final int ColNiokuriAdd01			= 38;
	static final int ColNiokuriAdd02			= 39;
	static final int ColNiokuriAdd03			= 40;
	static final int ColNioKuriTel			= 41;
	static final int ColNioKuriFax			= 42;
	static final int ColNioKuriMail			= 43;
	static final int ColNiokuriMunicCd		= 44;
	static final int ColDeliCd					= 45;
	static final int ColClDeliCd				= 46;
	static final int ColDeliDepartmentCd		= 47;
	static final int ColDeliName01			= 48;
	static final int ColDeliName02			= 49;
	static final int ColDeliName03			= 50;
	static final int ColDeliPost				= 51;
	static final int ColDeliAdd01				= 52;
	static final int ColDeliAdd02				= 53;
	static final int ColDeliAdd03				= 54;
	static final int ColDeliTel				= 55;
	static final int ColDeliFax				= 56;
	static final int ColDeliMail				= 57;
	static final int ColDeliMunicCd			= 58;
	static final int ColCom01					= 59;
	static final int ColCom02					= 60;
	static final int ColCom03					= 61;
	static final int ColCom04					= 62;
	static final int ColCom05					= 63;
	static final int ColStatus					= 64;
	static final int ColTaxFg					= 65;
	static final int ColTaxRate				= 66;
	static final int ColDeliFee				= 67;
	static final int ColAddDeliFee01			= 68;
	static final int ColAddDeliFee02			= 69;
	static final int ColAddDeliFee03			= 70;
	static final int ColHaighWayFee01			= 71;
	static final int ColHaighWayFee02			= 72;
	static final int ColConsumptionTax		= 73;
	static final int ColWithOutTaxTotal		= 74;
	static final int ColTotalFee				= 75;
	static final int ColFeeFixFG				= 76;
	static final int ColFeeFixDate			= 77;
	static final int ColReceiptStampFG		= 78;
	static final int ColReceiptStampDate		= 79;
	static final int ColInvoiceStatus			= 80;
	static final int ColEntryDate				= 81;
	static final int ColUpdateDate			= 82;
	static final int ColEntryUser				= 83;
	static final int ColUpdateUser			= 84;
	static final int ColEntryPG				= 85;
	static final int ColUpdatePG				= 86;
	static final int ColUseFeeBasePtCd		= 87;
	static final int ColWmsStatus				= 88;
	static final int ColWmsShipDate			= 89;
	static final int ColCourseGpCd			= 90;
	static final int ColCourseCD				= 91;
	static final int ColCourseCDEda			= 92;
	static final int ColPitGrp					= 93;
	static final int ColPit01					= 94;
	static final int ColPit02					= 95;
	static final int ColPit03					= 96;
	static final int ColPit04					= 97;
	static final int ColPit05					= 98;
	
	static final int ColMscl_cd				= 99;
	static final int ColMsInvoiceWHCD			=100;
	static final int ColMsOkuriNo				=101;
	static final int ColMsMsNo					=102;
	static final int ColMsDeliNo				=103;
	static final int ColMsDelliMsNo			=104;
	static final int ColMsClOrderNo			=105;
	static final int ColMsClGpCd				=106;
	static final int ColMsItemCd				=107;
	static final int ColMsItemName01			=108;
	static final int ColMsItemName02			=109;
	static final int ColMsItemName03			=110;
	static final int ColMsUnitWeight			=111;
	static final int ColMsUnitSize			=112;
	static final int ColMsQty					=113;
	static final int ColMsPackingQty			=114;
	static final int ColMsUnitName			=115;
	static final int ColMsSubTotalWeight		=116;
	static final int ColMsSubTotalSize		=117;
	static final int ColMsUnitPrice			=118;
	static final int ColMsSubTotalPrice		=119;
	static final int ColMsCategoryCd			=120;
	static final int ColMsCategoryName		=121;
	static final int ColMsTildFG				=122;
	static final int ColMsTildName			=123;
	static final int ColMsCom01				=124;
	static final int ColMsCom02				=125;
	static final int ColMsCom03				=126;
	static final int ColMsCom04				=127;
	static final int ColMsCom05				=128;
	static final int ColMsEntryDate			=129;
	static final int ColMsUpdateDate			=130;
	static final int ColMsEntryUser			=131;
	static final int ColMsUpdateUser			=132;
	static final int ColMsLot					=133;
	static final int ColMsExpDate				=134;
	static final int ColMsPackingType			=135;
	static final int ColMsClItemCd			=136;
	static final int ColMsItemMDNo			=137;
	static final int ColMsJanCd				=138;

	static final int ColCsCount				=139;
	static final int ColBaraFg					=140;
	
	public static Object[][] RtSetDataDefinition(){
		//登録用データ定義
		Object[][] SetDataDefinition= {
				 {"cl_cd"				,Colcl_cd						,"String"	,"荷主コード"					,""}
				,{"InvoiceWHCD"			,ColInvoiceWHCD				,"String"	,"倉庫コード"					,""}
				,{"OkuriNo"				,ColOkuriNo					,"String"	,"送り状番号"					,""}
				,{"ClDeliNo"			,ColClDeliNo					,"String"	,"荷主管理番号"					,""}
				,{"PickupWHCD"			,ColPickupWHCD				,"String"	,"集荷倉庫CD"					,""}
				,{"PurposeFG"			,ColPurposeFG					,"int"		,"目的フラグ"					,""}
				,{"PlanDate"			,ColPlanDate					,"Date"		,"出荷予定日"					,""}
				,{"ShipDate"			,ColShipDate					,"DateTime"	,"出荷実績日"					,""}
				,{"SPPlanDate"			,ColSPPlanDate				,"Date"		,"着日指定"						,""}
				,{"SPDate"				,ColSPDate						,"DateTime"	,"着日実績"						,""}
				,{"SPTimeFG"			,ColSPTimeFG					,"String"	,"時間指定区分"					,""}
				,{"SPTimeStr"			,ColSPTimeStr					,"HH:MM"	,"時間指定開始"					,""}
				,{"SPTimeEnd"			,ColSPTimeEnd					,"HH:MM"	,"時間指定終了"					,""}
				,{"TotalWeight"			,ColTotalWeight				,"float"	,"荷物重量"						,""}
				,{"TotalSize"			,ColTotalSize					,"float"	,"荷物サイズ"					,""}
				,{"TotalQty"			,ColTotalQty					,"int"		,"個口数"						,""}
				,{"DeliveryTypeCd"		,ColDeliveryTypeCd			,"String"	,"運送タイプ01"					,""}
				,{"DeliTypeName"		,ColDeliTypeName				,"String"	,"運送タイプ名01"				,""}
				,{"DeliveryTypeCd02"	,ColDeliveryTypeCd02			,"String"	,"運送タイプ02"					,""}
				,{"DeliTypeName02"		,ColDeliTypeName02			,"String"	,"運送タイプ名02"				,""}
				,{"DeliveryTypeCd03"	,ColDeliveryTypeCd03			,"String"	,"運送タイプ03"					,""}
				,{"DeliTypeName03"		,ColDeliTypeName03			,"String"	,"運送タイプ名03"				,""}
				,{"DeliveryTypeCd04"	,ColDeliveryTypeCd04			,"String"	,"運送タイプ04"					,""}
				,{"DeliTypeName04"		,ColDeliTypeName04			,"String"	,"運送タイプ名04"				,""}
				,{"DeliveryTypeCd05"	,ColDeliveryTypeCd05			,"String"	,"運送タイプ05"					,""}
				,{"DeliTypeName05"		,ColDeliTypeName05			,"String"	,"運送タイプ名05"				,""}
				,{"CodFG"				,ColCodFG						,"int"		,"代引きフラグ"					,""}
				,{"CodPayTotal"			,ColCodPayTotal				,"int"		,"代引き収受金額合計"			,""}
				,{"CodPay"				,ColCodPay						,"int"		,"代引き金額"					,""}
				,{"CodConsumptionTax"	,ColCodConsumptionTax		,"int"		,"代引き消費税"					,""}
				,{"ChildrenFG"			,ColChildrenFG				,"int"		,"赤黒区分"						,""}
				,{"ParentOkuriNo"		,ColParentOkuriNo				,"int"		,"親伝票番号"					,""}
				,{"NiokuriCd"			,ColNiokuriCd					,"String"	,"荷送り人コード"				,""}
				,{"NiokuriDepartmentCd"	,ColNiokuriDepartmentCd		,"String"	,"部署CD"						,""}
				,{"NiokuriName01"		,ColNiokuriName01				,"String"	,"荷送り人名01"					,""}
				,{"NiokuriName02"		,ColNiokuriName02				,"String"	,"荷送り人名02"					,""}
				,{"NiokuriName03"		,ColNiokuriName03				,"String"	,"荷送り人名03"					,""}
				,{"NiokuriPost"			,ColNiokuriPost				,"String"	,"荷送り人郵便番号"				,""}
				,{"NiokuriAdd01"		,ColNiokuriAdd01				,"String"	,"荷送り人住所01"				,""}
				,{"NiokuriAdd02"		,ColNiokuriAdd02				,"String"	,"荷送り人住所02"				,""}
				,{"NiokuriAdd03"		,ColNiokuriAdd03				,"String"	,"荷送り人住所03"				,""}
				,{"NioKuriTel"			,ColNioKuriTel				,"String"	,"荷送り人TEL"					,""}
				,{"NioKuriFax"			,ColNioKuriFax				,"String"	,"荷送り人FAX"					,""}
				,{"NioKuriMail"			,ColNioKuriMail				,"String"	,"荷送り人MAIL"					,""}
				,{"NiokuriMunicCd"		,ColNiokuriMunicCd			,"String"	,"荷送人市区町村CD"				,""}
				,{"DeliCd"				,ColDeliCd						,"String"	,"荷届け先コード"				,""}
				,{"ClDeliCd"			,ColClDeliCd					,"String"	,"荷主荷届け先コード"			,""}
				,{"DeliDepartmentCd"	,ColDeliDepartmentCd			,"String"	,"部署CD"						,""}
				,{"DeliName01"			,ColDeliName01				,"String"	,"荷届け先名01"					,""}
				,{"DeliName02"			,ColDeliName02				,"String"	,"荷届け先名02"					,""}
				,{"DeliName03"			,ColDeliName03				,"String"	,"荷届け先名03"					,""}
				,{"DeliPost"			,ColDeliPost					,"String"	,"荷届け先郵便番号"				,""}
				,{"DeliAdd01"			,ColDeliAdd01					,"String"	,"荷届け先住所01"				,""}
				,{"DeliAdd02"			,ColDeliAdd02					,"String"	,"荷届け先住所02"				,""}
				,{"DeliAdd03"			,ColDeliAdd03					,"String"	,"荷届け先住所03"				,""}
				,{"DeliTel"				,ColDeliTel					,"String"	,"荷届け先TEL"					,""}
				,{"DeliFax"				,ColDeliFax					,"String"	,"荷届け先FAX"					,""}
				,{"DeliMail"			,ColDeliMail					,"String"	,"荷届け先MAIL"					,""}
				,{"DeliMunicCd"			,ColDeliMunicCd				,"String"	,"荷届先市区町村CD"				,""}
				,{"Com01"				,ColCom01						,"String"	,"コメント01"					,""}
				,{"Com02"				,ColCom02						,"String"	,"コメント02"					,""}
				,{"Com03"				,ColCom03						,"String"	,"コメント03"					,""}
				,{"Com04"				,ColCom04						,"String"	,"コメント04"					,""}
				,{"Com05"				,ColCom05						,"String"	,"コメント05"					,""}
				,{"Status"				,ColStatus						,"int"		,"状況"							,""}
				,{"TaxFg"				,ColTaxFg						,"int"		,"税区分"						,""}
				,{"TaxRate"				,ColTaxRate					,"int"		,"税率"							,""}
				,{"DeliFee"				,ColDeliFee					,"int"		,"運賃"							,""}
				,{"AddDeliFee01"		,ColAddDeliFee01				,"int"		,"付帯費用1"					,""}
				,{"AddDeliFee02"		,ColAddDeliFee02				,"int"		,"付帯費用2"					,""}
				,{"AddDeliFee03"		,ColAddDeliFee03				,"int"		,"付帯費用3"					,""}
				,{"HaighWayFee01"		,ColHaighWayFee01				,"int"		,"実費精算分1（内税）"			,""}
				,{"HaighWayFee02"		,ColHaighWayFee02				,"int"		,"実費精算分2（内税）"			,""}
				,{"ConsumptionTax"		,ColConsumptionTax			,"int"		,"消費税"						,""}
				,{"WithOutTaxTotal"		,ColWithOutTaxTotal			,"int"		,"税別合計金額"					,""}
				,{"TotalFee"			,ColTotalFee					,"int"		,"税込請求額合計"				,""}
				,{"FeeFixFG"			,ColFeeFixFG					,"int"		,"金額確定フラグ"				,""}
				,{"FeeFixDate"			,ColFeeFixDate				,"DateTime"	,"金額確定日時"					,""}
				,{"ReceiptStampFG"		,ColReceiptStampFG			,"int"		,"受領印チェック"				,""}
				,{"ReceiptStampDate"	,ColReceiptStampDate			,"DateTime"	,"受領印日時"					,""}
				,{"InvoiceStatus"		,ColInvoiceStatus				,"int"		,"請求ステータス"				,""}
				,{"EntryDate"			,ColEntryDate					,"DateTime"	,"登録日"						,""}
				,{"UpdateDate"			,ColUpdateDate				,"DateTime"	,"更新日"						,""}
				,{"EntryUser"			,ColEntryUser					,"String"	,"登録者"						,""}
				,{"UpdateUser"			,ColUpdateUser				,"String"	,"更新者"						,""}
				,{"EntryPG"				,ColEntryPG					,"String"	,"登録プログラム"				,""}
				,{"UpdatePG"			,ColUpdatePG					,"String"	,"更新プログラム"				,""}
				,{"UseFeeBasePtCd"		,ColUseFeeBasePtCd			,"String"	,"適用運賃タリフCD"				,""}
				,{"WmsStatus"			,ColWmsStatus					,"int"		,"在庫管理ステータス"			,""}
				,{"WmsShipDate"			,ColWmsShipDate				,"DateTime"	,"倉庫出荷日"					,""}
				,{"CourseGpCd"			,ColCourseGpCd				,"String"	,"コースグループコード"			,""}
				,{"CourseCD"			,ColCourseCD					,"String"	,"一次配車コースコード"			,""}
				,{"CourseCDEda"			,ColCourseCDEda				,"int"		,"一次配車コースコード枝番"		,""}
				,{"PitGrp"				,ColPitGrp						,"String"	,"一次配車払出ピットグループ"	,""}
				,{"Pit01"				,ColPit01						,"String"	,"一次配車払出ピット01"			,""}
				,{"Pit02"				,ColPit02						,"String"	,"一次配車払出ピット02"			,""}
				,{"Pit03"				,ColPit03						,"String"	,"一次配車払出ピット03"			,""}
				,{"Pit04"				,ColPit04						,"String"	,"一次配車払出ピット04"			,""}
				,{"Pit05"				,ColPit05						,"String"	,"一次配車払出ピット05"			,""}
				
				,{"Mscl_cd"				,ColMscl_cd					,"String"	,"荷主コード"					,""}
				,{"MsInvoiceWHCD"		,ColMsInvoiceWHCD				,"String"	,"倉庫コード"					,""}
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
				
				,{"CsCount"				,ColCsCount					,"int"		,"個口数"						,""}
				,{"BaraFg"				,ColBaraFg						,"int"		,"バラ端数無=0"					,""}
				};
		Object[][] Rt = new Object[SetDataDefinition.length][SetDataDefinition[0].length];
		for(int i01=0;i01<SetDataDefinition.length;i01++) {
			for(int i02=0;i02<SetDataDefinition[i01].length;i02++) {
				Rt[(int)SetDataDefinition[i01][1]][i02]	= SetDataDefinition[i01][i02];
			}
		}
		return Rt;
	}
	
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	
	public static void OkuriDataArrayEntrySetDataView(int x,int y,Object[][] EntryData){
		A00000_Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		
		final JFrame main_fm = B100_FrameParts.FrameCreate(x,y,900,750,"Corgi00出荷予定取込（登録データ確認）　WT100_ArrivalPlan_06_ArrayEntrySetDataView","SP");
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
			for(int i01=0;i01<RtSetDataDefinition.length;i01++) {
				if(i01<EntryData[i].length) {
					switch((String)RtSetDataDefinition[i01][2]) {
						case "Date":
							EntryData[i][(int)RtSetDataDefinition[i01][1]] = B100_TextControl.TextToDate(""+EntryData[i][(int)RtSetDataDefinition[i01][1]]);
							break;
						case "int":
							int WINT	= B100_TextControl.TextToInt(""+EntryData[i][(int)RtSetDataDefinition[i01][1]]);
							EntryData[i][(int)RtSetDataDefinition[i01][1]]	= ""+WINT;
							break;
						case "float":
							float WFT	= B100_TextControl.TextToFloat(""+EntryData[i][(int)RtSetDataDefinition[i01][1]]);
							WFT = Math.round(WFT*1000);	WFT	= WFT/1000;
							EntryData[i][(int)RtSetDataDefinition[i01][1]]	= ""+WFT;
							break;
						default:
							EntryData[i][(int)RtSetDataDefinition[i01][1]] = B100_TextControl.Trim(""+EntryData[i][(int)RtSetDataDefinition[i01][1]]);
							break;
					}
					
					
					
					SetOb[(int)RtSetDataDefinition[i01][1]+1]=EntryData[i][(int)RtSetDataDefinition[i01][1]];
				}else {
					SetOb[(int)RtSetDataDefinition[i01][1]+1]="";
				}
			}
			MainFmTableModel.addRow(SetOb);
		}
		
		RenewFg = true;
		main_fm.setVisible(true);
		
		//登録ボタン押下時の挙動
		entry_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					int row_count = MainFmTableModel.getRowCount();
					Object [][] RtSetDataDefinition	= RtSetDataDefinition();
					
					String[][] SetData = new String[row_count][RtSetDataDefinition.length];
					for(int i=0;i<row_count;i++){
						for(int i01=0;i01<RtSetDataDefinition.length;i01++) {
							SetData[i][i01]	= B100_TextControl.Trim(""+MainFmTableModel.getValueAt(i, 1+i01));
							switch((String)RtSetDataDefinition[i01][2]) {
								case "Date":
									SetData[i][i01] = B100_TextControl.TextToDate(""+SetData[i][i01]);
									if("".equals(SetData[i][i01])) {
										SetData[i][i01]	= "null";
									}
									break;
								case "DateTime":
									SetData[i][i01] = B100_TextControl.Trim(""+SetData[i][i01]);
									if("".equals(SetData[i][i01])) {
										SetData[i][i01]	= "null";
									}
									break;
								case "int":
									int WINT	= B100_TextControl.TextToInt(""+SetData[i][i01]);
									SetData[i][i01]	= ""+WINT;
									break;
								case "float":
									float WFT	= B100_TextControl.TextToFloat(""+SetData[i][i01]);
									WFT = Math.round(WFT*1000);	WFT	= WFT/1000;
									SetData[i][i01]	= ""+WFT;
									break;
								default:
									SetData[i][i01] = B100_TextControl.Trim(""+SetData[i][i01]);
									break;
							}
						}
					}
					if(0<SetData.length) {
						DataEntry(SetData);
						
						SetX=main_fm.getX();
						SetY=main_fm.getY();

						main_fm.setVisible(false);
						main_fm.dispose();
						WT100_OkuriHd_00_Search.OkuriHdSearch(0,0);
					}
					RenewFg = true;
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
	
	private static void DataEntry(String[][] SetData) {
		//同一ヘッダとしてみなす値
		ArrayList<String> CheckArray_cl_cd					= new ArrayList<String>();
		ArrayList<String> CheckArray_InvoiceWHCD			= new ArrayList<String>();
		ArrayList<String> CheckArray_OkuriNo				= new ArrayList<String>();
		ArrayList<String> CheckArray_ClDeliNo				= new ArrayList<String>();
		ArrayList<String> CheckArray_PickupWHCD				= new ArrayList<String>();
		ArrayList<String> CheckArray_PurposeFG				= new ArrayList<String>();
		ArrayList<String> CheckArray_DeliveryTypeCd			= new ArrayList<String>();
		ArrayList<String> CheckArray_DeliveryTypeCd02		= new ArrayList<String>();
		ArrayList<String> CheckArray_DeliveryTypeCd03		= new ArrayList<String>();
		ArrayList<String> CheckArray_DeliveryTypeCd04		= new ArrayList<String>();
		ArrayList<String> CheckArray_DeliveryTypeCd05		= new ArrayList<String>();
		ArrayList<String> CheckArray_CodFG					= new ArrayList<String>();
		ArrayList<String> CheckArray_NiokuriCd				= new ArrayList<String>();
		ArrayList<String> CheckArray_NiokuriDepartmentCd	= new ArrayList<String>();
		ArrayList<String> CheckArray_DeliCd					= new ArrayList<String>();
		ArrayList<String> CheckArray_ClDeliCd				= new ArrayList<String>();
		ArrayList<String> CheckArray_MsTildFG				= new ArrayList<String>();
		
		ArrayList<Integer> 	TotalCsCount			= new ArrayList<Integer>();		//伝票個口数
		ArrayList<Integer> 	TotalBaraFg				= new ArrayList<Integer>();		//伝票内バラ有無
		ArrayList<Float> 	TotalWeight				= new ArrayList<Float>();		//伝票重量
		ArrayList<Float> 	TotalSize				= new ArrayList<Float>();		//伝票サイズ
		
		int OkuriNeedCount = 0;
		
		ArrayList<String> DeleteOkurino				= new ArrayList<String>();
		
		for(int i=0;i<SetData.length;i++){
			boolean UhHitFg=true;
			if(null!=CheckArray_cl_cd&&0>=CheckArray_cl_cd.size()) {
				
			}else {
				for(int i01=0;i01<CheckArray_cl_cd.size();i01++) {
					if(CheckArray_cl_cd.get(i01).equals(SetData[i][Colcl_cd])
							&&CheckArray_InvoiceWHCD.get(i01).equals(""+SetData[i][ColInvoiceWHCD])
							&&CheckArray_OkuriNo.get(i01).equals(SetData[i][ColOkuriNo])
							&&CheckArray_ClDeliNo.get(i01).equals(SetData[i][ColClDeliNo])
							&&CheckArray_PickupWHCD.get(i01).equals(SetData[i][ColPickupWHCD])
							&&CheckArray_PurposeFG.get(i01).equals(SetData[i][ColPurposeFG])
							&&CheckArray_DeliveryTypeCd.get(i01).equals(SetData[i][ColDeliveryTypeCd])
							&&CheckArray_DeliveryTypeCd02.get(i01).equals(SetData[i][ColDeliveryTypeCd02])
							&&CheckArray_DeliveryTypeCd03.get(i01).equals(SetData[i][ColDeliveryTypeCd03])
							&&CheckArray_DeliveryTypeCd04.get(i01).equals(SetData[i][ColDeliveryTypeCd04])
							&&CheckArray_DeliveryTypeCd05.get(i01).equals(SetData[i][ColDeliveryTypeCd05])
							&&CheckArray_CodFG.get(i01).equals(SetData[i][ColCodFG])
							&&CheckArray_NiokuriCd.get(i01).equals(SetData[i][ColNiokuriCd])
							&&CheckArray_NiokuriDepartmentCd.get(i01).equals(SetData[i][ColNiokuriDepartmentCd])
							&&CheckArray_DeliCd.get(i01).equals(SetData[i][ColDeliCd])
							&&CheckArray_ClDeliCd.get(i01).equals(SetData[i][ColClDeliCd])
							&&CheckArray_MsTildFG.get(i01).equals(SetData[i][ColMsTildFG])
							) {
						
						UhHitFg=false;
						TotalCsCount.set(i01,TotalCsCount.get(i01)+B100_TextControl.TextToInt(SetData[i][ColCsCount]));
						if(0==B100_TextControl.TextToInt(SetData[i][ColBaraFg])) {
						}else {
							TotalBaraFg.set(i01,1);
						}
						TotalWeight.set(i01,TotalWeight.get(i01)+B100_TextControl.TextToFloat(SetData[i][ColMsSubTotalWeight]));
						TotalSize.set(i01,TotalSize.get(i01)+B100_TextControl.TextToFloat(SetData[i][ColMsSubTotalSize]));
					}
				}
			}
			
			
			if(UhHitFg) {
				CheckArray_cl_cd.add(SetData[i][Colcl_cd]);
				CheckArray_InvoiceWHCD.add(SetData[i][ColInvoiceWHCD]);
				CheckArray_OkuriNo.add(SetData[i][ColOkuriNo]);
				CheckArray_ClDeliNo.add(SetData[i][ColClDeliNo]);
				CheckArray_PickupWHCD.add(SetData[i][ColPickupWHCD]);
				CheckArray_PurposeFG.add(SetData[i][ColPurposeFG]);
				CheckArray_DeliveryTypeCd.add(SetData[i][ColDeliveryTypeCd]);
				CheckArray_DeliveryTypeCd02.add(SetData[i][ColDeliveryTypeCd02]);
				CheckArray_DeliveryTypeCd03.add(SetData[i][ColDeliveryTypeCd03]);
				CheckArray_DeliveryTypeCd04.add(SetData[i][ColDeliveryTypeCd04]);
				CheckArray_DeliveryTypeCd05.add(SetData[i][ColDeliveryTypeCd05]);
				CheckArray_CodFG.add(SetData[i][ColCodFG]);
				CheckArray_NiokuriCd.add(SetData[i][ColNiokuriCd]);
				CheckArray_NiokuriDepartmentCd.add(SetData[i][ColNiokuriDepartmentCd]);
				CheckArray_DeliCd.add(SetData[i][ColDeliCd]);
				CheckArray_ClDeliCd.add(SetData[i][ColClDeliCd]);
				CheckArray_MsTildFG.add(SetData[i][ColMsTildFG]);
				
				if(!"".equals(SetData[i][ColOkuriNo])) {
					DeleteOkurino.add(SetData[i][ColOkuriNo]);
				}
				
				TotalCsCount.add(B100_TextControl.TextToInt(SetData[i][ColCsCount]));
				if(0==B100_TextControl.TextToInt(SetData[i][ColBaraFg])) {
					TotalBaraFg.add(0);
				}else {
					TotalBaraFg.add(1);
				}
				TotalWeight.add(B100_TextControl.TextToFloat(SetData[i][ColMsSubTotalWeight]));
				TotalSize.add(B100_TextControl.TextToFloat(SetData[i][ColMsSubTotalSize]));
				
				if("".equals(SetData[i][ColOkuriNo])) {
					OkuriNeedCount	= OkuriNeedCount+1;
				}
			}
		}
		int[] OkuriNo= Tools100_OkuriNoGet.OkuriNoRt(OkuriNeedCount);
		String now_dtm = B100_DateTimeControl.dtmString2(B100_DateTimeControl.dtm()[1])[1];
		
		//更新の場合明細削除済みの可能性と送り状番号ループ回避のために送り状データ削除
		
		String[] judg_field = {"OkuriNo"};
		String[][] judg_data = new String[OkuriNo.length+DeleteOkurino.size()][1];
		String TgtDB = "NYANKO";
		
		int counter = 0;
		for(int i=0;i<OkuriNo.length;i++) {
			judg_data[counter][0] = ""+OkuriNo[i];
			counter = counter+1;
		}
		for(int i=0;i<DeleteOkurino.size();i++) {
			judg_data[counter][0] = DeleteOkurino.get(i);
			counter = counter+1;
		}
		
		String tgt_table = "KT0010_OKURI_HD";
		A100_DeleteSQL.DeleteSql(tgt_table,judg_field,judg_data,TgtDB);
		
		tgt_table = "KT0011_OKURI_MS";
		A100_DeleteSQL.DeleteSql(tgt_table,judg_field,judg_data,TgtDB);
		
		String[] HdSet_cl_cd				= new String[CheckArray_cl_cd.size()];
		String[] HdSet_InvoiceWHCD			= new String[CheckArray_cl_cd.size()];
		String[] HdSet_OkuriNo				= new String[CheckArray_cl_cd.size()];
		String[] HdSet_ClDeliNo				= new String[CheckArray_cl_cd.size()];
		String[] HdSet_PickupWHCD			= new String[CheckArray_cl_cd.size()];
		String[] HdSet_PurposeFG			= new String[CheckArray_cl_cd.size()];
		String[] HdSet_PlanDate				= new String[CheckArray_cl_cd.size()];
		String[] HdSet_ShipDate				= new String[CheckArray_cl_cd.size()];
		String[] HdSet_SPPlanDate			= new String[CheckArray_cl_cd.size()];
		String[] HdSet_SPDate				= new String[CheckArray_cl_cd.size()];
		String[] HdSet_SPTimeFG				= new String[CheckArray_cl_cd.size()];
		String[] HdSet_SPTimeStr			= new String[CheckArray_cl_cd.size()];
		String[] HdSet_SPTimeEnd			= new String[CheckArray_cl_cd.size()];
		String[] HdSet_TotalWeight			= new String[CheckArray_cl_cd.size()];
		String[] HdSet_TotalSize			= new String[CheckArray_cl_cd.size()];
		String[] HdSet_TotalQty				= new String[CheckArray_cl_cd.size()];
		String[] HdSet_DeliveryTypeCd		= new String[CheckArray_cl_cd.size()];
		String[] HdSet_DeliTypeName			= new String[CheckArray_cl_cd.size()];
		String[] HdSet_DeliveryTypeCd02		= new String[CheckArray_cl_cd.size()];
		String[] HdSet_DeliTypeName02		= new String[CheckArray_cl_cd.size()];
		String[] HdSet_DeliveryTypeCd03		= new String[CheckArray_cl_cd.size()];
		String[] HdSet_DeliTypeName03		= new String[CheckArray_cl_cd.size()];
		String[] HdSet_DeliveryTypeCd04		= new String[CheckArray_cl_cd.size()];
		String[] HdSet_DeliTypeName04		= new String[CheckArray_cl_cd.size()];
		String[] HdSet_DeliveryTypeCd05		= new String[CheckArray_cl_cd.size()];
		String[] HdSet_DeliTypeName05		= new String[CheckArray_cl_cd.size()];
		String[] HdSet_CodFG				= new String[CheckArray_cl_cd.size()];
		String[] HdSet_CodPayTotal			= new String[CheckArray_cl_cd.size()];
		String[] HdSet_CodPay				= new String[CheckArray_cl_cd.size()];
		String[] HdSet_CodConsumptionTax	= new String[CheckArray_cl_cd.size()];
		String[] HdSet_ChildrenFG			= new String[CheckArray_cl_cd.size()];
		String[] HdSet_ParentOkuriNo		= new String[CheckArray_cl_cd.size()];
		String[] HdSet_NiokuriCd			= new String[CheckArray_cl_cd.size()];
		String[] HdSet_NiokuriDepartmentCd	= new String[CheckArray_cl_cd.size()];
		String[] HdSet_NiokuriName01		= new String[CheckArray_cl_cd.size()];
		String[] HdSet_NiokuriName02		= new String[CheckArray_cl_cd.size()];
		String[] HdSet_NiokuriName03		= new String[CheckArray_cl_cd.size()];
		String[] HdSet_NiokuriPost			= new String[CheckArray_cl_cd.size()];
		String[] HdSet_NiokuriAdd01			= new String[CheckArray_cl_cd.size()];
		String[] HdSet_NiokuriAdd02			= new String[CheckArray_cl_cd.size()];
		String[] HdSet_NiokuriAdd03			= new String[CheckArray_cl_cd.size()];
		String[] HdSet_NioKuriTel			= new String[CheckArray_cl_cd.size()];
		String[] HdSet_NioKuriFax			= new String[CheckArray_cl_cd.size()];
		String[] HdSet_NioKuriMail			= new String[CheckArray_cl_cd.size()];
		String[] HdSet_NiokuriMunicCd		= new String[CheckArray_cl_cd.size()];
		String[] HdSet_DeliCd				= new String[CheckArray_cl_cd.size()];
		String[] HdSet_ClDeliCd				= new String[CheckArray_cl_cd.size()];
		String[] HdSet_DeliDepartmentCd		= new String[CheckArray_cl_cd.size()];
		String[] HdSet_DeliName01			= new String[CheckArray_cl_cd.size()];
		String[] HdSet_DeliName02			= new String[CheckArray_cl_cd.size()];
		String[] HdSet_DeliName03			= new String[CheckArray_cl_cd.size()];
		String[] HdSet_DeliPost				= new String[CheckArray_cl_cd.size()];
		String[] HdSet_DeliAdd01			= new String[CheckArray_cl_cd.size()];
		String[] HdSet_DeliAdd02			= new String[CheckArray_cl_cd.size()];
		String[] HdSet_DeliAdd03			= new String[CheckArray_cl_cd.size()];
		String[] HdSet_DeliTel				= new String[CheckArray_cl_cd.size()];
		String[] HdSet_DeliFax				= new String[CheckArray_cl_cd.size()];
		String[] HdSet_DeliMail				= new String[CheckArray_cl_cd.size()];
		String[] HdSet_DeliMunicCd			= new String[CheckArray_cl_cd.size()];
		String[] HdSet_Com01				= new String[CheckArray_cl_cd.size()];
		String[] HdSet_Com02				= new String[CheckArray_cl_cd.size()];
		String[] HdSet_Com03				= new String[CheckArray_cl_cd.size()];
		String[] HdSet_Com04				= new String[CheckArray_cl_cd.size()];
		String[] HdSet_Com05				= new String[CheckArray_cl_cd.size()];
		String[] HdSet_Status				= new String[CheckArray_cl_cd.size()];
		String[] HdSet_TaxFg				= new String[CheckArray_cl_cd.size()];
		String[] HdSet_TaxRate				= new String[CheckArray_cl_cd.size()];
		String[] HdSet_DeliFee				= new String[CheckArray_cl_cd.size()];
		String[] HdSet_AddDeliFee01			= new String[CheckArray_cl_cd.size()];
		String[] HdSet_AddDeliFee02			= new String[CheckArray_cl_cd.size()];
		String[] HdSet_AddDeliFee03			= new String[CheckArray_cl_cd.size()];
		String[] HdSet_HaighWayFee01		= new String[CheckArray_cl_cd.size()];
		String[] HdSet_HaighWayFee02		= new String[CheckArray_cl_cd.size()];
		String[] HdSet_ConsumptionTax		= new String[CheckArray_cl_cd.size()];
		String[] HdSet_WithOutTaxTotal		= new String[CheckArray_cl_cd.size()];
		String[] HdSet_TotalFee				= new String[CheckArray_cl_cd.size()];
		String[] HdSet_FeeFixFG				= new String[CheckArray_cl_cd.size()];
		String[] HdSet_FeeFixDate			= new String[CheckArray_cl_cd.size()];
		String[] HdSet_ReceiptStampFG		= new String[CheckArray_cl_cd.size()];
		String[] HdSet_ReceiptStampDate		= new String[CheckArray_cl_cd.size()];
		String[] HdSet_InvoiceStatus		= new String[CheckArray_cl_cd.size()];
		String[] HdSet_EntryDate			= new String[CheckArray_cl_cd.size()];
		String[] HdSet_UpdateDate			= new String[CheckArray_cl_cd.size()];
		String[] HdSet_EntryUser			= new String[CheckArray_cl_cd.size()];
		String[] HdSet_UpdateUser			= new String[CheckArray_cl_cd.size()];
		String[] HdSet_EntryPG				= new String[CheckArray_cl_cd.size()];
		String[] HdSet_UpdatePG				= new String[CheckArray_cl_cd.size()];
		String[] HdSet_UseFeeBasePtCd		= new String[CheckArray_cl_cd.size()];
		String[] HdSet_WmsStatus			= new String[CheckArray_cl_cd.size()];
		String[] HdSet_WmsShipDate			= new String[CheckArray_cl_cd.size()];
		String[] HdSet_CourseGpCd			= new String[CheckArray_cl_cd.size()];
		String[] HdSet_CourseCD				= new String[CheckArray_cl_cd.size()];
		String[] HdSet_CourseCDEda			= new String[CheckArray_cl_cd.size()];
		String[] HdSet_PitGrp				= new String[CheckArray_cl_cd.size()];
		String[] HdSet_Pit01				= new String[CheckArray_cl_cd.size()];
		String[] HdSet_Pit02				= new String[CheckArray_cl_cd.size()];
		String[] HdSet_Pit03				= new String[CheckArray_cl_cd.size()];
		String[] HdSet_Pit04				= new String[CheckArray_cl_cd.size()];
		String[] HdSet_Pit05				= new String[CheckArray_cl_cd.size()];
		
		String[] MsSet_Mscl_cd				= new String[SetData.length];
		String[] MsSet_MsInvoiceWHCD		= new String[SetData.length];
		String[] MsSet_MsOkuriNo			= new String[SetData.length];
		String[] MsSet_MsMsNo				= new String[SetData.length];
		String[] MsSet_MsDeliNo				= new String[SetData.length];
		String[] MsSet_MsDelliMsNo			= new String[SetData.length];
		String[] MsSet_MsClOrderNo			= new String[SetData.length];
		String[] MsSet_MsClGpCd				= new String[SetData.length];
		String[] MsSet_MsItemCd				= new String[SetData.length];
		String[] MsSet_MsItemName01			= new String[SetData.length];
		String[] MsSet_MsItemName02			= new String[SetData.length];
		String[] MsSet_MsItemName03			= new String[SetData.length];
		String[] MsSet_MsUnitWeight			= new String[SetData.length];
		String[] MsSet_MsUnitSize			= new String[SetData.length];
		String[] MsSet_MsQty				= new String[SetData.length];
		String[] MsSet_MsPackingQty			= new String[SetData.length];
		String[] MsSet_MsUnitName			= new String[SetData.length];
		String[] MsSet_MsSubTotalWeight		= new String[SetData.length];
		String[] MsSet_MsSubTotalSize		= new String[SetData.length];
		String[] MsSet_MsUnitPrice			= new String[SetData.length];
		String[] MsSet_MsSubTotalPrice		= new String[SetData.length];
		String[] MsSet_MsCategoryCd			= new String[SetData.length];
		String[] MsSet_MsCategoryName		= new String[SetData.length];
		String[] MsSet_MsTildFG				= new String[SetData.length];
		String[] MsSet_MsTildName			= new String[SetData.length];
		String[] MsSet_MsCom01				= new String[SetData.length];
		String[] MsSet_MsCom02				= new String[SetData.length];
		String[] MsSet_MsCom03				= new String[SetData.length];
		String[] MsSet_MsCom04				= new String[SetData.length];
		String[] MsSet_MsCom05				= new String[SetData.length];
		String[] MsSet_MsEntryDate			= new String[SetData.length];
		String[] MsSet_MsUpdateDate			= new String[SetData.length];
		String[] MsSet_MsEntryUser			= new String[SetData.length];
		String[] MsSet_MsUpdateUser			= new String[SetData.length];
		String[] MsSet_MsLot				= new String[SetData.length];
		String[] MsSet_MsExpDate			= new String[SetData.length];
		String[] MsSet_MsPackingType		= new String[SetData.length];
		String[] MsSet_MsClItemCd			= new String[SetData.length];
		String[] MsSet_MsItemMDNo			= new String[SetData.length];
		String[] MsSet_MsJanCd				= new String[SetData.length];
		
		OkuriNeedCount	= 0;
		
		for(int i01=0;i01<CheckArray_cl_cd.size();i01++) {
			boolean UhHitFg=true;
			int MsNo = 0;
			String SetOkurino = CheckArray_OkuriNo.get(i01);
			if("".equals(SetOkurino)) {
				SetOkurino = ""+OkuriNo[OkuriNeedCount];
				OkuriNeedCount = OkuriNeedCount+1;
			}
			
			for(int i=0;i<SetData.length;i++){
				if(CheckArray_cl_cd.get(i01).equals(SetData[i][Colcl_cd])
						&&CheckArray_InvoiceWHCD.get(i01).equals(""+SetData[i][ColInvoiceWHCD])
						&&CheckArray_OkuriNo.get(i01).equals(SetData[i][ColOkuriNo])
						&&CheckArray_ClDeliNo.get(i01).equals(SetData[i][ColClDeliNo])
						&&CheckArray_PickupWHCD.get(i01).equals(SetData[i][ColPickupWHCD])
						&&CheckArray_PurposeFG.get(i01).equals(SetData[i][ColPurposeFG])
						&&CheckArray_DeliveryTypeCd.get(i01).equals(SetData[i][ColDeliveryTypeCd])
						&&CheckArray_DeliveryTypeCd02.get(i01).equals(SetData[i][ColDeliveryTypeCd02])
						&&CheckArray_DeliveryTypeCd03.get(i01).equals(SetData[i][ColDeliveryTypeCd03])
						&&CheckArray_DeliveryTypeCd04.get(i01).equals(SetData[i][ColDeliveryTypeCd04])
						&&CheckArray_DeliveryTypeCd05.get(i01).equals(SetData[i][ColDeliveryTypeCd05])
						&&CheckArray_CodFG.get(i01).equals(SetData[i][ColCodFG])
						&&CheckArray_NiokuriCd.get(i01).equals(SetData[i][ColNiokuriCd])
						&&CheckArray_NiokuriDepartmentCd.get(i01).equals(SetData[i][ColNiokuriDepartmentCd])
						&&CheckArray_DeliCd.get(i01).equals(SetData[i][ColDeliCd])
						&&CheckArray_ClDeliCd.get(i01).equals(SetData[i][ColClDeliCd])
						&&CheckArray_MsTildFG.get(i01).equals(SetData[i][ColMsTildFG])
						) {
					
					if(UhHitFg) {
						HdSet_cl_cd[i01]				= SetData[i][Colcl_cd];
						HdSet_InvoiceWHCD[i01]			= SetData[i][ColInvoiceWHCD];
						HdSet_OkuriNo[i01]				= SetOkurino;
						HdSet_ClDeliNo[i01]				= SetData[i][ColClDeliNo];
						HdSet_PickupWHCD[i01]			= SetData[i][ColPickupWHCD];
						HdSet_PurposeFG[i01]			= SetData[i][ColPurposeFG];
						HdSet_PlanDate[i01]				= SetData[i][ColPlanDate];
						HdSet_ShipDate[i01]				= SetData[i][ColShipDate];
						HdSet_SPPlanDate[i01]			= SetData[i][ColSPPlanDate];
						HdSet_SPDate[i01]				= SetData[i][ColSPDate];
						HdSet_SPTimeFG[i01]				= SetData[i][ColSPTimeFG];
						HdSet_SPTimeStr[i01]			= SetData[i][ColSPTimeStr];
						HdSet_SPTimeEnd[i01]			= SetData[i][ColSPTimeEnd];
						HdSet_TotalWeight[i01]			= SetData[i][ColTotalWeight];
						HdSet_TotalSize[i01]			= SetData[i][ColTotalSize];
						HdSet_TotalQty[i01]				= SetData[i][ColTotalQty];
						HdSet_DeliveryTypeCd[i01]		= SetData[i][ColDeliveryTypeCd];
						HdSet_DeliTypeName[i01]			= SetData[i][ColDeliTypeName];
						HdSet_DeliveryTypeCd02[i01]		= SetData[i][ColDeliveryTypeCd02];
						HdSet_DeliTypeName02[i01]		= SetData[i][ColDeliTypeName02];
						HdSet_DeliveryTypeCd03[i01]		= SetData[i][ColDeliveryTypeCd03];
						HdSet_DeliTypeName03[i01]		= SetData[i][ColDeliTypeName03];
						HdSet_DeliveryTypeCd04[i01]		= SetData[i][ColDeliveryTypeCd04];
						HdSet_DeliTypeName04[i01]		= SetData[i][ColDeliTypeName04];
						HdSet_DeliveryTypeCd05[i01]		= SetData[i][ColDeliveryTypeCd05];
						HdSet_DeliTypeName05[i01]		= SetData[i][ColDeliTypeName05];
						HdSet_CodFG[i01]				= SetData[i][ColCodFG];
						HdSet_CodPayTotal[i01]			= SetData[i][ColCodPayTotal];
						HdSet_CodPay[i01]				= SetData[i][ColCodPay];
						HdSet_CodConsumptionTax[i01]	= SetData[i][ColCodConsumptionTax];
						HdSet_ChildrenFG[i01]			= SetData[i][ColChildrenFG];
						HdSet_ParentOkuriNo[i01]		= SetData[i][ColParentOkuriNo];
						HdSet_NiokuriCd[i01]			= SetData[i][ColNiokuriCd];
						HdSet_NiokuriDepartmentCd[i01]	= SetData[i][ColNiokuriDepartmentCd];
						HdSet_NiokuriName01[i01]		= SetData[i][ColNiokuriName01];
						HdSet_NiokuriName02[i01]		= SetData[i][ColNiokuriName02];
						HdSet_NiokuriName03[i01]		= SetData[i][ColNiokuriName03];
						HdSet_NiokuriPost[i01]			= SetData[i][ColNiokuriPost];
						HdSet_NiokuriAdd01[i01]			= SetData[i][ColNiokuriAdd01];
						HdSet_NiokuriAdd02[i01]			= SetData[i][ColNiokuriAdd02];
						HdSet_NiokuriAdd03[i01]			= SetData[i][ColNiokuriAdd03];
						HdSet_NioKuriTel[i01]			= SetData[i][ColNioKuriTel];
						HdSet_NioKuriFax[i01]			= SetData[i][ColNioKuriFax];
						HdSet_NioKuriMail[i01]			= SetData[i][ColNioKuriMail];
						HdSet_NiokuriMunicCd[i01]		= SetData[i][ColNiokuriMunicCd];
						HdSet_DeliCd[i01]				= SetData[i][ColDeliCd];
						HdSet_ClDeliCd[i01]				= SetData[i][ColClDeliCd];
						HdSet_DeliDepartmentCd[i01]		= SetData[i][ColDeliDepartmentCd];
						HdSet_DeliName01[i01]			= SetData[i][ColDeliName01];
						HdSet_DeliName02[i01]			= SetData[i][ColDeliName02];
						HdSet_DeliName03[i01]			= SetData[i][ColDeliName03];
						HdSet_DeliPost[i01]				= SetData[i][ColDeliPost];
						HdSet_DeliAdd01[i01]			= SetData[i][ColDeliAdd01];
						HdSet_DeliAdd02[i01]			= SetData[i][ColDeliAdd02];
						HdSet_DeliAdd03[i01]			= SetData[i][ColDeliAdd03];
						HdSet_DeliTel[i01]				= SetData[i][ColDeliTel];
						HdSet_DeliFax[i01]				= SetData[i][ColDeliFax];
						HdSet_DeliMail[i01]				= SetData[i][ColDeliMail];
						HdSet_DeliMunicCd[i01]			= SetData[i][ColDeliMunicCd];
						HdSet_Com01[i01]				= SetData[i][ColCom01];
						HdSet_Com02[i01]				= SetData[i][ColCom02];
						HdSet_Com03[i01]				= SetData[i][ColCom03];
						HdSet_Com04[i01]				= SetData[i][ColCom04];
						HdSet_Com05[i01]				= SetData[i][ColCom05];
						HdSet_Status[i01]				= SetData[i][ColStatus];
						HdSet_TaxFg[i01]				= SetData[i][ColTaxFg];
						HdSet_TaxRate[i01]				= SetData[i][ColTaxRate];
						HdSet_DeliFee[i01]				= SetData[i][ColDeliFee];
						HdSet_AddDeliFee01[i01]			= SetData[i][ColAddDeliFee01];
						HdSet_AddDeliFee02[i01]			= SetData[i][ColAddDeliFee02];
						HdSet_AddDeliFee03[i01]			= SetData[i][ColAddDeliFee03];
						HdSet_HaighWayFee01[i01]		= SetData[i][ColHaighWayFee01];
						HdSet_HaighWayFee02[i01]		= SetData[i][ColHaighWayFee02];
						HdSet_ConsumptionTax[i01]		= SetData[i][ColConsumptionTax];
						HdSet_WithOutTaxTotal[i01]		= SetData[i][ColWithOutTaxTotal];
						HdSet_TotalFee[i01]				= SetData[i][ColTotalFee];
						HdSet_FeeFixFG[i01]				= SetData[i][ColFeeFixFG];
						HdSet_FeeFixDate[i01]			= SetData[i][ColFeeFixDate];
						HdSet_ReceiptStampFG[i01]		= SetData[i][ColReceiptStampFG];
						HdSet_ReceiptStampDate[i01]		= SetData[i][ColReceiptStampDate];
						HdSet_InvoiceStatus[i01]		= SetData[i][ColInvoiceStatus];
						HdSet_EntryDate[i01]			= now_dtm;
						HdSet_UpdateDate[i01]			= now_dtm;
						HdSet_EntryUser[i01]			= "(" + A00000_Main.LoginUserId + ")" + A00000_Main.LoginUserName;
						HdSet_UpdateUser[i01]			= "(" + A00000_Main.LoginUserId + ")" + A00000_Main.LoginUserName;
						HdSet_EntryPG[i01]				= "WT100_OkuriData_06_ArrayEntrySetDataView";
						HdSet_UpdatePG[i01]				= "WT100_OkuriData_06_ArrayEntrySetDataView";
						HdSet_UseFeeBasePtCd[i01]		= SetData[i][ColUseFeeBasePtCd];
						HdSet_WmsStatus[i01]			= SetData[i][ColWmsStatus];
						HdSet_WmsShipDate[i01]			= SetData[i][ColWmsShipDate];
						HdSet_CourseGpCd[i01]			= SetData[i][ColCourseGpCd];
						HdSet_CourseCD[i01]				= SetData[i][ColCourseCD];
						HdSet_CourseCDEda[i01]			= SetData[i][ColCourseCDEda];
						HdSet_PitGrp[i01]				= SetData[i][ColPitGrp];
						HdSet_Pit01[i01]				= SetData[i][ColPit01];
						HdSet_Pit02[i01]				= SetData[i][ColPit02];
						HdSet_Pit03[i01]				= SetData[i][ColPit03];
						HdSet_Pit04[i01]				= SetData[i][ColPit04];
						HdSet_Pit05[i01]				= SetData[i][ColPit05];
					}
					MsNo = MsNo+1;
					MsSet_Mscl_cd[i]			= SetData[i][ColMscl_cd];
					MsSet_MsInvoiceWHCD[i]		= SetData[i][ColMsInvoiceWHCD];
					MsSet_MsOkuriNo[i]			= SetOkurino;
					MsSet_MsMsNo[i]				= ""+MsNo;
					MsSet_MsDeliNo[i]			= SetData[i][ColMsDeliNo];
					MsSet_MsDelliMsNo[i]		= SetData[i][ColMsDelliMsNo];
					MsSet_MsClOrderNo[i]		= SetData[i][ColMsClOrderNo];
					MsSet_MsClGpCd[i]			= SetData[i][ColMsClGpCd];
					MsSet_MsItemCd[i]			= SetData[i][ColMsItemCd];
					MsSet_MsItemName01[i]		= SetData[i][ColMsItemName01];
					MsSet_MsItemName02[i]		= SetData[i][ColMsItemName02];
					MsSet_MsItemName03[i]		= SetData[i][ColMsItemName03];
					MsSet_MsUnitWeight[i]		= SetData[i][ColMsUnitWeight];
					MsSet_MsUnitSize[i]			= SetData[i][ColMsUnitSize];
					MsSet_MsQty[i]				= SetData[i][ColMsQty];
					MsSet_MsPackingQty[i]		= SetData[i][ColMsPackingQty];
					MsSet_MsUnitName[i]			= SetData[i][ColMsUnitName];
					MsSet_MsSubTotalWeight[i]	= SetData[i][ColMsSubTotalWeight];
					MsSet_MsSubTotalSize[i]		= SetData[i][ColMsSubTotalSize];
					MsSet_MsUnitPrice[i]		= SetData[i][ColMsUnitPrice];
					MsSet_MsSubTotalPrice[i]	= SetData[i][ColMsSubTotalPrice];
					MsSet_MsCategoryCd[i]		= SetData[i][ColMsCategoryCd];
					MsSet_MsCategoryName[i]		= SetData[i][ColMsCategoryName];
					MsSet_MsTildFG[i]			= SetData[i][ColMsTildFG];
					MsSet_MsTildName[i]			= SetData[i][ColMsTildName];
					MsSet_MsCom01[i]			= SetData[i][ColMsCom01];
					MsSet_MsCom02[i]			= SetData[i][ColMsCom02];
					MsSet_MsCom03[i]			= SetData[i][ColMsCom03];
					MsSet_MsCom04[i]			= SetData[i][ColMsCom04];
					MsSet_MsCom05[i]			= SetData[i][ColMsCom05];
					MsSet_MsEntryDate[i]		= now_dtm;
					MsSet_MsUpdateDate[i]		= now_dtm;
					MsSet_MsEntryUser[i]		= "(" + A00000_Main.LoginUserId + ")" + A00000_Main.LoginUserName;
					MsSet_MsUpdateUser[i]		= "(" + A00000_Main.LoginUserId + ")" + A00000_Main.LoginUserName;
					MsSet_MsLot[i]				= SetData[i][ColMsLot];
					MsSet_MsExpDate[i]			= SetData[i][ColMsExpDate];
					MsSet_MsPackingType[i]		= SetData[i][ColMsPackingType];
					MsSet_MsClItemCd[i]			= SetData[i][ColMsClItemCd];
					MsSet_MsItemMDNo[i]			= SetData[i][ColMsItemMDNo];
					MsSet_MsJanCd[i]			= SetData[i][ColMsJanCd];
					UhHitFg = false;
				}
			}
		}
		
		Object[][] HdSetOb = {
				 {"cl_cd"					,"1"	,"1"	,""		,HdSet_cl_cd				}	//荷主コード
				 ,{"InvoiceWHCD"			,"1"	,"1"	,""		,HdSet_InvoiceWHCD			}	//倉庫コード
				 ,{"OkuriNo"				,"1"	,"1"	,"Key"	,HdSet_OkuriNo				}	//送り状番号
				 ,{"ClDeliNo"				,"1"	,"1"	,""		,HdSet_ClDeliNo				}	//荷主管理番号
				 ,{"PickupWHCD"				,"1"	,"1"	,""		,HdSet_PickupWHCD			}	//集荷倉庫CD
				 ,{"PurposeFG"				,"1"	,"1"	,""		,HdSet_PurposeFG			}	//目的フラグ
				 ,{"PlanDate"				,"1"	,"1"	,""		,HdSet_PlanDate				}	//出荷予定日
				 ,{"ShipDate"				,"1"	,"1"	,""		,HdSet_ShipDate				}	//出荷実績日
				 ,{"SPPlanDate"				,"1"	,"1"	,""		,HdSet_SPPlanDate			}	//着日指定
				 ,{"SPDate"					,"1"	,"1"	,""		,HdSet_SPDate				}	//着日実績
				 ,{"SPTimeFG"				,"1"	,"1"	,""		,HdSet_SPTimeFG				}	//時間指定区分
				 ,{"SPTimeStr"				,"1"	,"1"	,""		,HdSet_SPTimeStr			}	//時間指定開始
				 ,{"SPTimeEnd"				,"1"	,"1"	,""		,HdSet_SPTimeEnd			}	//時間指定終了
				 ,{"TotalWeight"			,"1"	,"1"	,""		,HdSet_TotalWeight			}	//荷物重量(kg)
				 ,{"TotalSize"				,"1"	,"1"	,""		,HdSet_TotalSize			}	//荷物サイズ
				 ,{"TotalQty"				,"1"	,"1"	,""		,HdSet_TotalQty				}	//個口数
				 ,{"DeliveryTypeCd"			,"1"	,"1"	,""		,HdSet_DeliveryTypeCd		}	//運送タイプ01
				 ,{"DeliTypeName"			,"1"	,"1"	,""		,HdSet_DeliTypeName			}	//運送タイプ名01
				 ,{"DeliveryTypeCd02"		,"1"	,"1"	,""		,HdSet_DeliveryTypeCd02		}	//運送タイプ02
				 ,{"DeliTypeName02"			,"1"	,"1"	,""		,HdSet_DeliTypeName02		}	//運送タイプ名02
				 ,{"DeliveryTypeCd03"		,"1"	,"1"	,""		,HdSet_DeliveryTypeCd03		}	//運送タイプ03
				 ,{"DeliTypeName03"			,"1"	,"1"	,""		,HdSet_DeliTypeName03		}	//運送タイプ名03
				 ,{"DeliveryTypeCd04"		,"1"	,"1"	,""		,HdSet_DeliveryTypeCd04		}	//運送タイプ04
				 ,{"DeliTypeName04"			,"1"	,"1"	,""		,HdSet_DeliTypeName04		}	//運送タイプ名04
				 ,{"DeliveryTypeCd05"		,"1"	,"1"	,""		,HdSet_DeliveryTypeCd05		}	//運送タイプ05
				 ,{"DeliTypeName05"			,"1"	,"1"	,""		,HdSet_DeliTypeName05		}	//運送タイプ名05
				 ,{"CodFG"					,"1"	,"1"	,""		,HdSet_CodFG				}	//代引フラグ
				 ,{"CodPayTotal"			,"1"	,"1"	,""		,HdSet_CodPayTotal			}	//代引収受金額合計
				 ,{"CodPay"					,"1"	,"1"	,""		,HdSet_CodPay				}	//代引金額
				 ,{"CodConsumptionTax"		,"1"	,"1"	,""		,HdSet_CodConsumptionTax	}	//代引消費税
				 ,{"ChildrenFG"				,"1"	,"1"	,""		,HdSet_ChildrenFG			}	//赤黒区分
				 ,{"ParentOkuriNo"			,"1"	,"1"	,""		,HdSet_ParentOkuriNo		}	//親伝票番号
				 ,{"NiokuriCd"				,"1"	,"1"	,""		,HdSet_NiokuriCd			}	//荷送り人コード
				 ,{"NiokuriDepartmentCd"	,"1"	,"1"	,""		,HdSet_NiokuriDepartmentCd	}	//部署CD
				 ,{"NiokuriName01"			,"1"	,"1"	,""		,HdSet_NiokuriName01		}	//荷送人名01
				 ,{"NiokuriName02"			,"1"	,"1"	,""		,HdSet_NiokuriName02		}	//荷送人名02
				 ,{"NiokuriName03"			,"1"	,"1"	,""		,HdSet_NiokuriName03		}	//荷送人名03
				 ,{"NiokuriPost"			,"1"	,"1"	,""		,HdSet_NiokuriPost			}	//荷送人郵便番号
				 ,{"NiokuriAdd01"			,"1"	,"1"	,""		,HdSet_NiokuriAdd01			}	//荷送人住所01
				 ,{"NiokuriAdd02"			,"1"	,"1"	,""		,HdSet_NiokuriAdd02			}	//荷送人住所02
				 ,{"NiokuriAdd03"			,"1"	,"1"	,""		,HdSet_NiokuriAdd03			}	//荷送人住所03
				 ,{"NioKuriTel"				,"1"	,"1"	,""		,HdSet_NioKuriTel			}	//荷送人TEL
				 ,{"NioKuriFax"				,"1"	,"1"	,""		,HdSet_NioKuriFax			}	//荷送人FAX
				 ,{"NioKuriMail"			,"1"	,"1"	,""		,HdSet_NioKuriMail			}	//荷送人MAIL
				 ,{"NiokuriMunicCd"			,"1"	,"1"	,""		,HdSet_NiokuriMunicCd		}	//荷送人市区町村CD
				 ,{"DeliCd"					,"1"	,"1"	,""		,HdSet_DeliCd				}	//荷届け先コード
				 ,{"ClDeliCd"				,"1"	,"1"	,""		,HdSet_ClDeliCd				}	//荷主荷届け先コード
				 ,{"DeliDepartmentCd"		,"1"	,"1"	,""		,HdSet_DeliDepartmentCd		}	//部署CD
				 ,{"DeliName01"				,"1"	,"1"	,""		,HdSet_DeliName01			}	//荷届先名01
				 ,{"DeliName02"				,"1"	,"1"	,""		,HdSet_DeliName02			}	//荷届先名02
				 ,{"DeliName03"				,"1"	,"1"	,""		,HdSet_DeliName03			}	//荷届先名03
				 ,{"DeliPost"				,"1"	,"1"	,""		,HdSet_DeliPost				}	//荷届先郵便番号
				 ,{"DeliAdd01"				,"1"	,"1"	,""		,HdSet_DeliAdd01			}	//荷届先住所01
				 ,{"DeliAdd02"				,"1"	,"1"	,""		,HdSet_DeliAdd02			}	//荷届先住所02
				 ,{"DeliAdd03"				,"1"	,"1"	,""		,HdSet_DeliAdd03			}	//荷届先住所03
				 ,{"DeliTel"				,"1"	,"1"	,""		,HdSet_DeliTel				}	//荷届先TEL
				 ,{"DeliFax"				,"1"	,"1"	,""		,HdSet_DeliFax				}	//荷届先FAX
				 ,{"DeliMail"				,"1"	,"1"	,""		,HdSet_DeliMail				}	//荷届先MAIL
				 ,{"DeliMunicCd"			,"1"	,"1"	,""		,HdSet_DeliMunicCd			}	//荷届先市区町村CD
				 ,{"Com01"					,"1"	,"1"	,""		,HdSet_Com01				}	//コメント01
				 ,{"Com02"					,"1"	,"1"	,""		,HdSet_Com02				}	//コメント02
				 ,{"Com03"					,"1"	,"1"	,""		,HdSet_Com03				}	//コメント03
				 ,{"Com04"					,"1"	,"1"	,""		,HdSet_Com04				}	//コメント04
				 ,{"Com05"					,"1"	,"1"	,""		,HdSet_Com05				}	//コメント05
				 ,{"Status"					,"1"	,"1"	,""		,HdSet_Status				}	//状況
				 ,{"TaxFg"					,"1"	,"1"	,""		,HdSet_TaxFg				}	//税区分
				 ,{"TaxRate"				,"1"	,"1"	,""		,HdSet_TaxRate				}	//税率
				 ,{"DeliFee"				,"1"	,"1"	,""		,HdSet_DeliFee				}	//運賃
				 ,{"AddDeliFee01"			,"1"	,"1"	,""		,HdSet_AddDeliFee01			}	//付帯費用1
				 ,{"AddDeliFee02"			,"1"	,"1"	,""		,HdSet_AddDeliFee02			}	//付帯費用2
				 ,{"AddDeliFee03"			,"1"	,"1"	,""		,HdSet_AddDeliFee03			}	//付帯費用3
				 ,{"HaighWayFee01"			,"1"	,"1"	,""		,HdSet_HaighWayFee01		}	//高速代等実費精算分1（内税）
				 ,{"HaighWayFee02"			,"1"	,"1"	,""		,HdSet_HaighWayFee02		}	//高速代等実費精算分2（内税）
				 ,{"ConsumptionTax"			,"1"	,"1"	,""		,HdSet_ConsumptionTax		}	//消費税
				 ,{"WithOutTaxTotal"		,"1"	,"1"	,""		,HdSet_WithOutTaxTotal		}	//税別合計金額
				 ,{"TotalFee"				,"1"	,"1"	,""		,HdSet_TotalFee				}	//税込請求額合計
				 ,{"FeeFixFG"				,"1"	,"1"	,""		,HdSet_FeeFixFG				}	//金額確定フラグ
				 ,{"FeeFixDate"				,"1"	,"1"	,""		,HdSet_FeeFixDate			}	//金額確定日時
				 ,{"ReceiptStampFG"			,"1"	,"1"	,""		,HdSet_ReceiptStampFG		}	//受領印チェック
				 ,{"ReceiptStampDate"		,"1"	,"1"	,""		,HdSet_ReceiptStampDate		}	//受領印日時
				 ,{"InvoiceStatus"			,"1"	,"1"	,""		,HdSet_InvoiceStatus		}	//請求ステータス
				 ,{"EntryDate"				,"1"	,"0"	,""		,HdSet_EntryDate			}	//登録日
				 ,{"UpdateDate"				,"1"	,"1"	,""		,HdSet_UpdateDate			}	//更新日
				 ,{"EntryUser"				,"1"	,"0"	,""		,HdSet_EntryUser			}	//登録者
				 ,{"UpdateUser"				,"1"	,"1"	,""		,HdSet_UpdateUser			}	//更新者
				 ,{"EntryPG"				,"1"	,"0"	,""		,HdSet_EntryPG				}	//登録プログラム
				 ,{"UpdatePG"				,"1"	,"1"	,""		,HdSet_UpdatePG				}	//更新プログラム
				 ,{"UseFeeBasePtCd"			,"1"	,"1"	,""		,HdSet_UseFeeBasePtCd		}	//適用運賃タリフCD
				 ,{"WmsStatus"				,"1"	,"1"	,""		,HdSet_WmsStatus			}	//在庫管理ステータス
				 ,{"WmsShipDate"			,"1"	,"1"	,""		,HdSet_WmsShipDate			}	//倉庫出荷日
				 ,{"CourseGpCd"				,"1"	,"1"	,""		,HdSet_CourseGpCd			}	//コースグループコード
				 ,{"CourseCD"				,"1"	,"1"	,""		,HdSet_CourseCD				}	//一次配車コースコード
				 ,{"CourseCDEda"			,"1"	,"1"	,""		,HdSet_CourseCDEda			}	//一次配車コースコード枝番
				 ,{"PitGrp"					,"1"	,"1"	,""		,HdSet_PitGrp				}	//一次配車払出ピットグループ
				 ,{"Pit01"					,"1"	,"1"	,""		,HdSet_Pit01				}	//一次配車払出ピット01
				 ,{"Pit02"					,"1"	,"1"	,""		,HdSet_Pit02				}	//一次配車払出ピット02
				 ,{"Pit03"					,"1"	,"1"	,""		,HdSet_Pit03				}	//一次配車払出ピット03
				 ,{"Pit04"					,"1"	,"1"	,""		,HdSet_Pit04				}	//一次配車払出ピット04
				 ,{"Pit05"					,"1"	,"1"	,""		,HdSet_Pit05				}	//一次配車払出ピット05
				};
		
		Object[][] MsSetOb = {
				  {"cl_cd"			,"1"	,"1"	,""		,MsSet_Mscl_cd			}	//荷主コード
				 ,{"InvoiceWHCD"	,"1"	,"1"	,""		,MsSet_MsInvoiceWHCD	}	//倉庫コード
				 ,{"OkuriNo"		,"1"	,"1"	,"Key"	,MsSet_MsOkuriNo		}	//送り状番号
				 ,{"MsNo"			,"1"	,"1"	,"Key"	,MsSet_MsMsNo			}	//明細番号
				 ,{"DeliNo"			,"1"	,"1"	,""		,MsSet_MsDeliNo			}	//出荷番号
				 ,{"DelliMsNo"		,"1"	,"1"	,""		,MsSet_MsDelliMsNo		}	//出荷番号明細番号
				 ,{"ClOrderNo"		,"1"	,"1"	,""		,MsSet_MsClOrderNo		}	//荷主管理番号
				 ,{"ClGpCd"			,"1"	,"1"	,""		,MsSet_MsClGpCd			}	//荷主グループコード
				 ,{"ItemCd"			,"1"	,"1"	,""		,MsSet_MsItemCd			}	//商品コード
				 ,{"ItemName01"		,"1"	,"1"	,""		,MsSet_MsItemName01		}	//商品表記名
				 ,{"ItemName02"		,"1"	,"1"	,""		,MsSet_MsItemName02		}	//商品正式名
				 ,{"ItemName03"		,"1"	,"1"	,""		,MsSet_MsItemName03		}	//商品略名
				 ,{"UnitWeight"		,"1"	,"1"	,""		,MsSet_MsUnitWeight		}	//単位重量
				 ,{"UnitSize"		,"1"	,"1"	,""		,MsSet_MsUnitSize		}	//単位サイズ
				 ,{"Qty"			,"1"	,"1"	,""		,MsSet_MsQty			}	//個数
				 ,{"PackingQty"		,"1"	,"1"	,""		,MsSet_MsPackingQty		}	//荷姿数量
				 ,{"UnitName"		,"1"	,"1"	,""		,MsSet_MsUnitName		}	//明細単位
				 ,{"SubTotalWeight"	,"1"	,"1"	,""		,MsSet_MsSubTotalWeight	}	//明細重量
				 ,{"SubTotalSize"	,"1"	,"1"	,""		,MsSet_MsSubTotalSize	}	//明細サイズ
				 ,{"UnitPrice"		,"1"	,"1"	,""		,MsSet_MsUnitPrice		}	//単価
				 ,{"SubTotalPrice"	,"1"	,"1"	,""		,MsSet_MsSubTotalPrice	}	//金額
				 ,{"CategoryCd"		,"1"	,"1"	,""		,MsSet_MsCategoryCd		}	//商品分類
				 ,{"CategoryName"	,"1"	,"1"	,""		,MsSet_MsCategoryName	}	//商品分類名
				 ,{"TildFG"			,"1"	,"1"	,""		,MsSet_MsTildFG			}	//温度区分
				 ,{"TildName"		,"1"	,"1"	,""		,MsSet_MsTildName		}	//温度区分名
				 ,{"Com01"			,"1"	,"1"	,""		,MsSet_MsCom01			}	//コメント01
				 ,{"Com02"			,"1"	,"1"	,""		,MsSet_MsCom02			}	//コメント02
				 ,{"Com03"			,"1"	,"1"	,""		,MsSet_MsCom03			}	//コメント03
				 ,{"Com04"			,"1"	,"1"	,""		,MsSet_MsCom04			}	//コメント04
				 ,{"Com05"			,"1"	,"1"	,""		,MsSet_MsCom05			}	//コメント05
				 ,{"EntryDate"		,"1"	,"0"	,""		,MsSet_MsEntryDate		}	//登録日
				 ,{"UpdateDate"		,"1"	,"1"	,""		,MsSet_MsUpdateDate		}	//更新日
				 ,{"EntryUser"		,"1"	,"0"	,""		,MsSet_MsEntryUser		}	//登録者
				 ,{"UpdateUser"		,"1"	,"1"	,""		,MsSet_MsUpdateUser		}	//更新者
				 ,{"Lot"			,"1"	,"1"	,""		,MsSet_MsLot			}	//ロット指定
				 ,{"ExpDate"		,"1"	,"1"	,""		,MsSet_MsExpDate		}	//賞味期限指定
				 ,{"PackingType"	,"1"	,"1"	,""		,MsSet_MsPackingType	}	//荷姿タイプ
				 ,{"ClItemCd"		,"1"	,"1"	,""		,MsSet_MsClItemCd		}	//荷主商品CD
				 ,{"ItemMDNo"		,"1"	,"1"	,""		,MsSet_MsItemMDNo		}	//型番
				 ,{"JanCd"			,"1"	,"1"	,""		,MsSet_MsJanCd			}	//荷姿JanCd
				};
		
		String Hd_tgt_table = "KT0010_OKURI_HD";
		String Hd_TgtDB = "NYANKO";
		int Hd_non_msg_fg = 1;
		
		A100_InsertUpdateSQL.InsertUpdateSomeRecord(HdSetOb,Hd_tgt_table,Hd_TgtDB,Hd_non_msg_fg);
		
		String Ms_tgt_table = "KT0011_OKURI_MS";
		String Ms_TgtDB = "NYANKO";
		int Ms_non_msg_fg = 0;
		
		A100_InsertUpdateSQL.InsertUpdateSomeRecord(MsSetOb,Ms_tgt_table,Ms_TgtDB,Ms_non_msg_fg);
		
	}
}