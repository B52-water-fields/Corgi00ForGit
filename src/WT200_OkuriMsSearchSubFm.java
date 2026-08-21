import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class WT200_OkuriMsSearchSubFm{
	static boolean RenewFg;
	static int SetX;
	static int SetY;
	
	static final int RtJFrame				= 0;
	static final int RtDefaultTableModel	= 1;
	static final int RtJTable				= 2;
	static final int EntryBtn				= 3;
	
	
	public static Object[] OkuriMsSearchSubFm(int x,int y,String ClCd,String TgtOkuriNo,String BackGroundColor,boolean SearchMode) {
		A00000_Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg=false;
		if(null==ClCd) {ClCd="";}
		if(null==TgtOkuriNo) {TgtOkuriNo="";}
		if("".equals(ClCd)) {ClCd=A00000_Main.ClCd;}
		
		final JFrame OkuriMs_fm 	= B100_FrameParts.FrameCreate(x,y,900,750,"Corgi00出荷明細検索　WT200_OkuriMsSearchSubFm",BackGroundColor);
		JLabel 	OkuriMsUserinfo 	= B100_FrameParts.UserInfo();
		JButton OkuriMsExit_btn 	= B100_FrameParts.ExitBtn();
		JButton OkuriMsEntry_btn 	= B100_FrameParts.EntryBtn();
		
		OkuriMs_fm.add(OkuriMsUserinfo);
		OkuriMs_fm.add(OkuriMsExit_btn);
		OkuriMs_fm.add(OkuriMsEntry_btn);
		
		Object[][] DefinitionRt = T100_OkuriMsRt.DefinitionRt();
		Object[][] RtOkuriMsRt	= T100_OkuriMsRt.RtOkuriMsRt();
		
		String[] columnNamesOkuriMs = new String[RtOkuriMsRt.length+1];
		
		columnNamesOkuriMs[0] = "Fg";
		for(int i=0;i<RtOkuriMsRt.length;i++) {
			columnNamesOkuriMs[1+(int)RtOkuriMsRt[i][1]] = ""+RtOkuriMsRt[i][3];
		}
		
		//編集可能カラム1列目のみ
		final DefaultTableModel tableModel_msOkuriMs = new B100_TableControl.MyTableModel00(columnNamesOkuriMs,0);
		
		final JTable tbOkuriMs = new JTable(tableModel_msOkuriMs);
		tbOkuriMs.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tbOkuriMs.setRowHeight(20*A00000_Main.Mul/A00000_Main.Div);
		tbOkuriMs.setFont(new Font(A00000_Main.DefaultFont, Font.PLAIN, 12*A00000_Main.Mul/A00000_Main.Div));
		
		DefaultTableColumnModel columnModelOkuriMs
		= (DefaultTableColumnModel)tbOkuriMs.getColumnModel();
		
		//列幅初期設定 表示位置設定
		TableColumn column = null;
		
		column = columnModelOkuriMs.getColumn( 0);	column.setPreferredWidth( 30*A00000_Main.Mul/A00000_Main.Div);	//FG
		
		for(int i=0;i<RtOkuriMsRt.length;i++) {
			if("int".equals((String)RtOkuriMsRt[i][2])||"float".equals((String)RtOkuriMsRt[i][2])) {
				column = columnModelOkuriMs.getColumn(1+(int)RtOkuriMsRt[i][1]);	column.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.rightCellRenderer());
			}else {
				column = columnModelOkuriMs.getColumn(1+(int)RtOkuriMsRt[i][1]);	column.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.leftCellRenderer());
			}
		}
		
		//スクロール用設定
		JScrollPane scpnOkuriMs = B100_FrameParts.JScrollPaneSet(10,325,860,180,tbOkuriMs);
		OkuriMs_fm.add(scpnOkuriMs);
		
		//ヘッダ表示パネル
		JPanel PN_HD01 = B100_FrameParts.JPanelSet(10,40,860,280,"White");
		JPanel PN_HD02 = B100_FrameParts.JPanelSet(10,40,860,280,"White");
		String[] TabName 	= {"Main","Sub"};
		JPanel[] SetPN		= {PN_HD01,PN_HD02};
		JTabbedPane TabPaneSet	= B100_FrameParts.TabPaneSet(10,40,860,280,TabName,SetPN,"");
		
		//検索条件
		JLabel LB_ClCd				= B100_FrameParts.JLabelSet(		  0,  0,100,20,(String)DefinitionRt[T100_OkuriMsRt.ColSearchClCd][5]		+":"	,11,1);
		JLabel LB_SearchOkuriNo		= B100_FrameParts.JLabelSet(		  0, 50,100,20,(String)DefinitionRt[T100_OkuriMsRt.ColSearchOkuriNo][5]	+":"	,11,1);
		
		final JComboBox TB_ClCd		= B100_FrameParts.JComboBoxSet(				100,  0,300,20,B100_DefaultVariable.ClList[0],11);	//荷主コード
		final JTextField  TB_SearchOkuriNo		= B100_FrameParts.JTextFieldSet(	100, 50,100,20,"",12,0);							//送り状番号
		
		JLabel LB2_SearchOkuriNo		= B100_FrameParts.JLabelSet(	200, 50,100,20,B100_DefaultVariable.SearchExact	,11,0);
		
		PN_HD01.add(LB_ClCd);
		PN_HD01.add(LB_SearchOkuriNo);
		
		PN_HD01.add(TB_ClCd);
		PN_HD01.add(TB_SearchOkuriNo);
		
		PN_HD01.add(LB2_SearchOkuriNo);
		
		TB_ClCd.setSelectedIndex(	B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.ClList[1]		,ClCd ,true) );		//荷主コード選択状態にする
		TB_ClCd.setEnabled(false);
		
		JButton OkuriMsSearchKickBtn			= B100_FrameParts.BtnSet(			300,50, 90,20,"検索",11);
		if(SearchMode) {
			PN_HD01.add(OkuriMsSearchKickBtn);
			TB_SearchOkuriNo.setEditable(true);
		}else {
			TB_SearchOkuriNo.setEditable(false);
		}
		
		
		//検索結果ヘッダ情報
		JLabel LB_PickupWhCd			= B100_FrameParts.JLabelSet(		  0, 25,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColPickupWhCd][3]				+":"	,11,1);		//集荷倉庫CD
		JLabel LB_ClDeliNo				= B100_FrameParts.JLabelSet(		  0, 75,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColClDeliNo][3]					+":"	,11,1);		//荷主管理番号
		
		JLabel LB_PurposeFG				= B100_FrameParts.JLabelSet(		  0,125,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColPurposeFG][3]				+":"	,11,1);		//目的フラグ
		JLabel LB_PlanDate				= B100_FrameParts.JLabelSet(		  0,150,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColPlanDate][3]					+":"	,11,1);		//出荷予定日
		JLabel LB_ShipDate				= B100_FrameParts.JLabelSet(		  0,175,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColShipDate][3]					+":"	,11,1);		//出荷実績日
		JLabel LB_SPPlanDate			= B100_FrameParts.JLabelSet(		  0,200,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColSPPlanDate][3]				+":"	,11,1);		//着日指定
		JLabel LB_SPDate				= B100_FrameParts.JLabelSet(		  0,225,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColSPDate][3]					+":"	,11,1);		//着日実績
		JLabel LB_SPTimeFG				= B100_FrameParts.JLabelSet(		  0,250,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColSPTimeFG][3]					+":"	,11,1);		//時間指定区分
		JLabel LB_SPTimeStr				= B100_FrameParts.JLabelSet(		  0,275,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColSPTimeStr][3]				+":"	,11,1);		//時間指定開始
		JLabel LB_SPTimeEnd				= B100_FrameParts.JLabelSet(		  0,300,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColSPTimeEnd][3]				+":"	,11,1);		//時間指定終了
		
		JLabel LB_DeliveryTypeCd01		= B100_FrameParts.JLabelSet(		200,100,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColDeliveryTypeCd01][3]		+":"	,11,1);		//運送タイプ01
		JLabel LB_DeliveryTypeCd02		= B100_FrameParts.JLabelSet(		200,125,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColDeliveryTypeCd02][3]		+":"	,11,1);		//運送タイプ02
		JLabel LB_DeliveryTypeCd03		= B100_FrameParts.JLabelSet(		200,150,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColDeliveryTypeCd03][3]		+":"	,11,1);		//運送タイプ03
		JLabel LB_DeliveryTypeCd04		= B100_FrameParts.JLabelSet(		200,175,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColDeliveryTypeCd04][3]		+":"	,11,1);		//運送タイプ04
		JLabel LB_DeliveryTypeCd05		= B100_FrameParts.JLabelSet(		200,200,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColDeliveryTypeCd05][3]		+":"	,11,1);		//運送タイプ05
		JLabel LB_CodFG					= B100_FrameParts.JLabelSet(		200,225,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColCodFG][3]						+":"	,11,1);		//代引フラグ
		JLabel LB_CodPayTotal			= B100_FrameParts.JLabelSet(		200,250,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColCodPayTotal][3]				+":"	,11,1);		//代引収受金額合計
		JLabel LB_CodPay				= B100_FrameParts.JLabelSet(		200,275,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColCodPay][3]					+":"	,11,1);		//代引金額
		JLabel LB_CodConsumptionTax		= B100_FrameParts.JLabelSet(		200,300,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColCodConsumptionTax][3]		+":"	,11,1);		//代引消費税
		
		JLabel LB_NiokuriCd				= B100_FrameParts.JLabelSet(		400, 50,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColNiokuriCd][3]				+":"	,11,1);		//荷送人コード
		JLabel LB_NiokuriDepartmentCd	= B100_FrameParts.JLabelSet(		600, 50,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColNiokuriDepartmentCd][3]		+":"	,11,1);		//荷送人部署CD
		JLabel LB_NiokuriName01			= B100_FrameParts.JLabelSet(		400, 75,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColNiokuriName01][3]			+":"	,11,1);		//荷送人名01
		JLabel LB_NiokuriName02			= B100_FrameParts.JLabelSet(		400,100,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColNiokuriName02][3]			+":"	,11,1);		//荷送人名02
		JLabel LB_NiokuriName03			= B100_FrameParts.JLabelSet(		400,125,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColNiokuriName03][3]			+":"	,11,1);		//荷送人名03
		JLabel LB_NiokuriPost			= B100_FrameParts.JLabelSet(		400,150,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColNiokuriPost][3]				+":"	,11,1);		//荷送人郵便番号
		JLabel LB_NiokuriAdd01			= B100_FrameParts.JLabelSet(		400,175,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColNiokuriAdd01][3]				+":"	,11,1);		//荷送人住所01
		JLabel LB_NiokuriAdd02			= B100_FrameParts.JLabelSet(		400,200,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColNiokuriAdd02][3]				+":"	,11,1);		//荷送人住所02
		JLabel LB_NiokuriAdd03			= B100_FrameParts.JLabelSet(		400,225,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColNiokuriAdd03][3]				+":"	,11,1);		//荷送人住所03
		JLabel LB_NioKuriTel			= B100_FrameParts.JLabelSet(		400,250,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColNioKuriTel][3]				+":"	,11,1);		//荷送人TEL
		JLabel LB_NioKuriFax			= B100_FrameParts.JLabelSet(		600,250,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColNioKuriFax][3]				+":"	,11,1);		//荷送人FAX
		JLabel LB_NioKuriMail			= B100_FrameParts.JLabelSet(		400,275,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColNioKuriMail][3]				+":"	,11,1);		//荷送人MAIL
		JLabel LB_NiokuriMunicCd		= B100_FrameParts.JLabelSet(		400,300,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColNiokuriMunicCd][3]			+":"	,11,1);		//荷送人市区町村CD

		JLabel LB_ClDeliCd				= B100_FrameParts.JLabelSet(	    800, 25,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColClDeliCd][3]					+":"	,11,1);		//荷主荷届先コード
		JLabel LB_DeliCd				= B100_FrameParts.JLabelSet(		800, 50,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColDeliCd][3]					+":"	,11,1);		//荷届先コード
		JLabel LB_DeliDepartmentCd		= B100_FrameParts.JLabelSet(	   1000, 50,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColDeliDepartmentCd][3]		+":"	,11,1);		//部署CD
		JLabel LB_DeliName01			= B100_FrameParts.JLabelSet(		800, 75,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColDeliName01][3]				+":"	,11,1);		//荷届先名01
		JLabel LB_DeliName02			= B100_FrameParts.JLabelSet(		800,100,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColDeliName02][3]				+":"	,11,1);		//荷届先名02
		JLabel LB_DeliName03			= B100_FrameParts.JLabelSet(		800,125,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColDeliName03][3]				+":"	,11,1);		//荷届先名03
		JLabel LB_DeliPost				= B100_FrameParts.JLabelSet(		800,150,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColDeliPost][3]					+":"	,11,1);		//荷届先郵便番号
		JLabel LB_DeliAdd01				= B100_FrameParts.JLabelSet(		800,175,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColDeliAdd01][3]				+":"	,11,1);		//荷届先住所01
		JLabel LB_DeliAdd02				= B100_FrameParts.JLabelSet(		800,200,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColDeliAdd02][3]				+":"	,11,1);		//荷届先住所02
		JLabel LB_DeliAdd03				= B100_FrameParts.JLabelSet(		800,225,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColDeliAdd03][3]				+":"	,11,1);		//荷届先住所03
		JLabel LB_DeliTel				= B100_FrameParts.JLabelSet(		800,250,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColDeliTel][3]					+":"	,11,1);		//荷届先TEL
		JLabel LB_DeliFax				= B100_FrameParts.JLabelSet(	   1000,250,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColDeliFax][3]					+":"	,11,1);		//荷届先FAX
		JLabel LB_DeliMail				= B100_FrameParts.JLabelSet(		800,275,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColDeliMail][3]					+":"	,11,1);		//荷届先MAIL
		JLabel LB_DeliMunicCd			= B100_FrameParts.JLabelSet(		800,300,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColDeliMunicCd][3]				+":"	,11,1);		//荷届先市区町村CD

		JLabel LB_TotalWeight			= B100_FrameParts.JLabelSet(	   1200, 50,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColTotalWeight][3]				+":"	,11,1);		//荷物重量(kg)
		JLabel LB_TotalSize				= B100_FrameParts.JLabelSet(	   1200, 75,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColTotalSize][3]				+":"	,11,1);		//荷物サイズ
		JLabel LB_TotalQty				= B100_FrameParts.JLabelSet(	   1200,100,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColTotalQty][3]					+":"	,11,1);		//個口数
		JLabel LB_ChildrenFG			= B100_FrameParts.JLabelSet(	   1200,125,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColChildrenFG][3]				+":"	,11,1);		//子伝票区分
		JLabel LB_ParentOkuriNo			= B100_FrameParts.JLabelSet(	   1200,150,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColParentOkuriNo][3]			+":"	,11,1);		//親伝票番号
		JLabel LB_Status				= B100_FrameParts.JLabelSet(	   1200,175,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColStatus][3]					+":"	,11,1);		//運送状況
		JLabel LB_Com01					= B100_FrameParts.JLabelSet(	   1200,200,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColCom01][3]						+":"	,11,1);		//コメント01
		JLabel LB_Com02					= B100_FrameParts.JLabelSet(	   1200,225,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColCom02][3]						+":"	,11,1);		//コメント02
		JLabel LB_Com03					= B100_FrameParts.JLabelSet(	   1200,250,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColCom03][3]						+":"	,11,1);		//コメント03
		JLabel LB_Com04					= B100_FrameParts.JLabelSet(	   1200,275,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColCom04][3]						+":"	,11,1);		//コメント04
		JLabel LB_Com05					= B100_FrameParts.JLabelSet(	   1200,300,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColCom05][3]						+":"	,11,1);		//コメント05
		
		JLabel LB_TaxFg					= B100_FrameParts.JLabelSet(	   1200, 25,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColTaxFg][3]						+":"	,11,1);		//税区分
		JLabel LB_TaxRate				= B100_FrameParts.JLabelSet(	   1200, 25,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColTaxRate][3]					+":"	,11,1);		//税率
		JLabel LB_DeliFee				= B100_FrameParts.JLabelSet(	   1200, 25,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColDeliFee][3]					+":"	,11,1);		//運賃
		JLabel LB_AddDeliFee01			= B100_FrameParts.JLabelSet(	   1200, 25,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColAddDeliFee01][3]				+":"	,11,1);		//付帯費用1
		JLabel LB_AddDeliFee02			= B100_FrameParts.JLabelSet(	   1200, 25,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColAddDeliFee02][3]				+":"	,11,1);		//付帯費用2
		JLabel LB_AddDeliFee03			= B100_FrameParts.JLabelSet(	   1200, 25,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColAddDeliFee03][3]				+":"	,11,1);		//付帯費用3
		JLabel LB_HaighWayFee01			= B100_FrameParts.JLabelSet(	   1200, 25,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColHaighWayFee01][3]			+":"	,11,1);		//高速代等実費精算分1（内税）
		JLabel LB_HaighWayFee02			= B100_FrameParts.JLabelSet(	   1200, 25,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColHaighWayFee02][3]			+":"	,11,1);		//高速代等実費精算分2（内税）
		JLabel LB_ConsumptionTax		= B100_FrameParts.JLabelSet(	   1200, 25,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColConsumptionTax][3]			+":"	,11,1);		//消費税
		JLabel LB_WithOutTaxTotal		= B100_FrameParts.JLabelSet(	   1200, 25,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColWithOutTaxTotal][3]			+":"	,11,1);		//税別合計金額
		JLabel LB_TotalFee				= B100_FrameParts.JLabelSet(	   1200, 25,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColTotalFee][3]					+":"	,11,1);		//税込請求額合計
		JLabel LB_FeeFixFG				= B100_FrameParts.JLabelSet(	   1200, 25,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColFeeFixFG][3]					+":"	,11,1);		//金額確定フラグ
		JLabel LB_FeeFixDate			= B100_FrameParts.JLabelSet(	   1200, 25,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColFeeFixDate][3]				+":"	,11,1);		//金額確定日時
		JLabel LB_ReceiptStampFG		= B100_FrameParts.JLabelSet(	   1200, 25,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColReceiptStampFG][3]			+":"	,11,1);		//受領印チェック
		JLabel LB_ReceiptStampDate		= B100_FrameParts.JLabelSet(	   1200, 25,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColReceiptStampDate][3]		+":"	,11,1);		//受領印日時
		JLabel LB_InvoiceStatus			= B100_FrameParts.JLabelSet(	   1200, 25,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColInvoiceStatus][3]			+":"	,11,1);		//請求ステータス
		JLabel LB_EntryPG				= B100_FrameParts.JLabelSet(	   1200, 25,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColEntryPG][3]					+":"	,11,1);		//登録プログラム
		JLabel LB_UpdatePG				= B100_FrameParts.JLabelSet(	   1200, 25,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColUpdatePG][3]					+":"	,11,1);		//更新プログラム
		
		/***************/
		final JComboBox  TB_PickupWhCd				= B100_FrameParts.JComboBoxSet(		100, 25,300,20,B100_DefaultVariable.WhList[0],11);		//集荷倉庫CD
		final JTextField  TB_ClDeliNo				= B100_FrameParts.JTextFieldSet(		100, 75,100,20,"",11,0);		//荷主管理番号
		
		final JTextField  TB_PurposeFG				= B100_FrameParts.JTextFieldSet(		100,125,100,20,"",11,0);		//目的フラグ
		final JTextField  TB_PlanDate				= B100_FrameParts.JTextFieldSet(		100,150,100,20,"",11,0);		//出荷予定日
		final JTextField  TB_ShipDate				= B100_FrameParts.JTextFieldSet(		100,175,100,20,"",11,0);		//出荷実績日
		final JTextField  TB_SPPlanDate				= B100_FrameParts.JTextFieldSet(		100,200,100,20,"",11,0);		//着日指定
		final JTextField  TB_SPDate					= B100_FrameParts.JTextFieldSet(		100,225,100,20,"",11,0);		//着日実績
		final JTextField  TB_SPTimeFG				= B100_FrameParts.JTextFieldSet(		100,250,100,20,"",11,0);		//時間指定区分
		final JTextField  TB_SPTimeStr				= B100_FrameParts.JTextFieldSet(		100,275,100,20,"",11,0);		//時間指定開始
		final JTextField  TB_SPTimeEnd				= B100_FrameParts.JTextFieldSet(		100,300,100,20,"",11,0);		//時間指定終了
		
		final JComboBox  TB_DeliveryTypeCd01		= B100_FrameParts.JComboBoxSet(		300,100,100,20,B100_DefaultVariable.DeliveryType01[0],11);		//運送タイプ01
		final JComboBox  TB_DeliveryTypeCd02		= B100_FrameParts.JComboBoxSet(		300,125,100,20,B100_DefaultVariable.DeliveryType02[0],11);		//運送タイプ02
		final JComboBox  TB_DeliveryTypeCd03		= B100_FrameParts.JComboBoxSet(		300,150,100,20,B100_DefaultVariable.DeliveryType03[0],11);		//運送タイプ03
		final JComboBox  TB_DeliveryTypeCd04		= B100_FrameParts.JComboBoxSet(		300,175,100,20,B100_DefaultVariable.DeliveryType04[0],11);		//運送タイプ04
		final JComboBox  TB_DeliveryTypeCd05		= B100_FrameParts.JComboBoxSet(		300,200,100,20,B100_DefaultVariable.DeliveryType05[0],11);		//運送タイプ05
		final JComboBox  TB_CodFG					= B100_FrameParts.JComboBoxSet(		300,225,100,20,B100_DefaultVariable.CODList[0]	,11);				//代引フラグ
		final JTextField  TB_CodPayTotal			= B100_FrameParts.JTextFieldSet(		300,250,100,20,"",11,0);		//代引収受金額合計
		final JTextField  TB_CodPay					= B100_FrameParts.JTextFieldSet(		300,275,100,20,"",11,0);		//代引金額
		final JTextField  TB_CodConsumptionTax		= B100_FrameParts.JTextFieldSet(		300,300,100,20,"",11,0);		//代引消費税
		
		final JTextField  TB_NiokuriCd				= B100_FrameParts.JTextFieldSet(		500, 50,100,20,"",11,0);		//荷送人コード
		final JTextField  TB_NiokuriDepartmentCd	= B100_FrameParts.JTextFieldSet(		700, 50,100,20,"",11,0);		//荷送人部署CD
		final JTextField  TB_NiokuriName01			= B100_FrameParts.JTextFieldSet(		500, 75,200,20,"",11,0);		//荷送人名01
		final JTextField  TB_NiokuriName02			= B100_FrameParts.JTextFieldSet(		500,100,200,20,"",11,0);		//荷送人名02
		final JTextField  TB_NiokuriName03			= B100_FrameParts.JTextFieldSet(		500,125,200,20,"",11,0);		//荷送人名03
		final JTextField  TB_NiokuriPost			= B100_FrameParts.JTextFieldSet(		500,150,200,20,"",11,0);		//荷送人郵便番号
		final JTextField  TB_NiokuriAdd01			= B100_FrameParts.JTextFieldSet(		500,175,100,20,"",11,0);		//荷送人住所01
		final JTextField  TB_NiokuriAdd02			= B100_FrameParts.JTextFieldSet(		500,200,200,20,"",11,0);		//荷送人住所02
		final JTextField  TB_NiokuriAdd03			= B100_FrameParts.JTextFieldSet(		500,225,200,20,"",11,0);		//荷送人住所03
		final JTextField  TB_NioKuriTel				= B100_FrameParts.JTextFieldSet(		500,250,100,20,"",11,0);		//荷送人TEL
		final JTextField  TB_NioKuriFax				= B100_FrameParts.JTextFieldSet(		700,250,100,20,"",11,0);		//荷送人FAX
		final JTextField  TB_NioKuriMail			= B100_FrameParts.JTextFieldSet(		500,275,200,20,"",11,0);		//荷送人MAIL
		final JTextField  TB_NiokuriMunicCd			= B100_FrameParts.JTextFieldSet(		500,300,100,20,"",11,0);		//荷送人市区町村CD

		final JTextField  TB_ClDeliCd				= B100_FrameParts.JTextFieldSet(	    900, 25,100,20,"",11,0);		//荷主荷届先コード
		final JTextField  TB_DeliCd					= B100_FrameParts.JTextFieldSet(		900, 50,100,20,"",11,0);		//荷届先コード
		final JTextField  TB_DeliDepartmentCd		= B100_FrameParts.JTextFieldSet(	   1100, 50,100,20,"",11,0);		//部署CD
		final JTextField  TB_DeliName01				= B100_FrameParts.JTextFieldSet(		900, 75,200,20,"",11,0);		//荷届先名01
		final JTextField  TB_DeliName02				= B100_FrameParts.JTextFieldSet(		900,100,200,20,"",11,0);		//荷届先名02
		final JTextField  TB_DeliName03				= B100_FrameParts.JTextFieldSet(		900,125,200,20,"",11,0);		//荷届先名03
		final JTextField  TB_DeliPost				= B100_FrameParts.JTextFieldSet(		900,150,100,20,"",11,0);		//荷届先郵便番号
		final JTextField  TB_DeliAdd01				= B100_FrameParts.JTextFieldSet(		900,175,200,20,"",11,0);		//荷届先住所01
		final JTextField  TB_DeliAdd02				= B100_FrameParts.JTextFieldSet(		900,200,200,20,"",11,0);		//荷届先住所02
		final JTextField  TB_DeliAdd03				= B100_FrameParts.JTextFieldSet(		900,225,200,20,"",11,0);		//荷届先住所03
		final JTextField  TB_DeliTel				= B100_FrameParts.JTextFieldSet(		900,250,100,20,"",11,0);		//荷届先TEL
		final JTextField  TB_DeliFax				= B100_FrameParts.JTextFieldSet(	   1100,250,100,20,"",11,0);		//荷届先FAX
		final JTextField  TB_DeliMail				= B100_FrameParts.JTextFieldSet(		900,275,200,20,"",11,0);		//荷届先MAIL
		final JTextField  TB_DeliMunicCd			= B100_FrameParts.JTextFieldSet(		900,300,100,20,"",11,0);		//荷届先市区町村CD

		final JTextField  TB_TotalWeight			= B100_FrameParts.JTextFieldSet(	   1200, 50,100,20,"",11,0);		//荷物重量(kg)
		final JTextField  TB_TotalSize				= B100_FrameParts.JTextFieldSet(	   1200, 75,100,20,"",11,0);		//荷物サイズ
		final JTextField  TB_TotalQty				= B100_FrameParts.JTextFieldSet(	   1200,100,100,20,"",11,0);		//個口数
		final JTextField  TB_ChildrenFG				= B100_FrameParts.JTextFieldSet(	   1200,125,100,20,"",11,0);		//子伝票区分
		final JTextField  TB_ParentOkuriNo			= B100_FrameParts.JTextFieldSet(	   1200,150,100,20,"",11,0);		//親伝票番号
		final JTextField  TB_Status					= B100_FrameParts.JTextFieldSet(	   1200,175,100,20,"",11,0);		//運送状況
		final JTextField  TB_Com01					= B100_FrameParts.JTextFieldSet(	   1200,200,100,20,"",11,0);		//コメント01
		final JTextField  TB_Com02					= B100_FrameParts.JTextFieldSet(	   1200,225,100,20,"",11,0);		//コメント02
		final JTextField  TB_Com03					= B100_FrameParts.JTextFieldSet(	   1200,250,100,20,"",11,0);		//コメント03
		final JTextField  TB_Com04					= B100_FrameParts.JTextFieldSet(	   1200,275,100,20,"",11,0);		//コメント04
		final JTextField  TB_Com05					= B100_FrameParts.JTextFieldSet(	   1200,300,100,20,"",11,0);		//コメント05

		final JTextField  TB_TaxFg					= B100_FrameParts.JTextFieldSet(	   1200, 25,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColTaxFg][3]						+":"	,11,1);		//税区分
		final JTextField  TB_TaxRate				= B100_FrameParts.JTextFieldSet(	   1200, 25,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColTaxRate][3]					+":"	,11,1);		//税率
		final JTextField  TB_DeliFee				= B100_FrameParts.JTextFieldSet(	   1200, 25,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColDeliFee][3]					+":"	,11,1);		//運賃
		final JTextField  TB_AddDeliFee01			= B100_FrameParts.JTextFieldSet(	   1200, 25,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColAddDeliFee01][3]				+":"	,11,1);		//付帯費用1
		final JTextField  TB_AddDeliFee02			= B100_FrameParts.JTextFieldSet(	   1200, 25,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColAddDeliFee02][3]				+":"	,11,1);		//付帯費用2
		final JTextField  TB_AddDeliFee03			= B100_FrameParts.JTextFieldSet(	   1200, 25,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColAddDeliFee03][3]				+":"	,11,1);		//付帯費用3
		final JTextField  TB_HaighWayFee01			= B100_FrameParts.JTextFieldSet(	   1200, 25,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColHaighWayFee01][3]			+":"	,11,1);		//高速代等実費精算分1（内税）
		final JTextField  TB_HaighWayFee02			= B100_FrameParts.JTextFieldSet(	   1200, 25,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColHaighWayFee02][3]			+":"	,11,1);		//高速代等実費精算分2（内税）
		final JTextField  TB_ConsumptionTax			= B100_FrameParts.JTextFieldSet(	   1200, 25,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColConsumptionTax][3]			+":"	,11,1);		//消費税
		final JTextField  TB_WithOutTaxTotal		= B100_FrameParts.JTextFieldSet(	   1200, 25,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColWithOutTaxTotal][3]			+":"	,11,1);		//税別合計金額
		final JTextField  TB_TotalFee				= B100_FrameParts.JTextFieldSet(	   1200, 25,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColTotalFee][3]					+":"	,11,1);		//税込請求額合計
		final JTextField  TB_FeeFixFG				= B100_FrameParts.JTextFieldSet(	   1200, 25,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColFeeFixFG][3]					+":"	,11,1);		//金額確定フラグ
		final JTextField  TB_FeeFixDate				= B100_FrameParts.JTextFieldSet(	   1200, 25,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColFeeFixDate][3]				+":"	,11,1);		//金額確定日時
		final JTextField  TB_ReceiptStampFG			= B100_FrameParts.JTextFieldSet(	   1200, 25,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColReceiptStampFG][3]			+":"	,11,1);		//受領印チェック
		final JTextField  TB_ReceiptStampDate		= B100_FrameParts.JTextFieldSet(	   1200, 25,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColReceiptStampDate][3]		+":"	,11,1);		//受領印日時
		final JTextField  TB_InvoiceStatus			= B100_FrameParts.JTextFieldSet(	   1200, 25,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColInvoiceStatus][3]			+":"	,11,1);		//請求ステータス
		final JTextField  TB_EntryPG				= B100_FrameParts.JTextFieldSet(	   1200, 25,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColEntryPG][3]					+":"	,11,1);		//登録プログラム
		final JTextField  TB_UpdatePG				= B100_FrameParts.JTextFieldSet(	   1200, 25,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColUpdatePG][3]					+":"	,11,1);		//更新プログラム

		

		//明細情報標記用
		JLabel LB_MsNo					= B100_FrameParts.JLabelSet(		  0,525,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsNo][3]						+":"	,11,1);		//明細番号
		JLabel LB_MsDeliNo				= B100_FrameParts.JLabelSet(		  0,550,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsDeliNo][3]					+":"	,11,1);		//明細出荷番号
		JLabel LB_MsDelliMsNo			= B100_FrameParts.JLabelSet(		  0,575,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsDelliMsNo][3]				+":"	,11,1);		//明細出荷番号明細番号
		JLabel LB_MsClOrderNo			= B100_FrameParts.JLabelSet(		  0,600,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsClOrderNo][3]				+":"	,11,1);		//明細荷主管理番号
		
		JLabel LB_MsItemCd				= B100_FrameParts.JLabelSet(		200,525,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsItemCd][3]					+":"	,11,1);		//明細商品コード
		JLabel LB_MsItemName01			= B100_FrameParts.JLabelSet(		200,550,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsItemName01][3]				+":"	,11,1);		//明細商品表記名
		JLabel LB_MsItemName02			= B100_FrameParts.JLabelSet(		200,575,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsItemName02][3]				+":"	,11,1);		//明細商品正式名
		JLabel LB_MsItemName03			= B100_FrameParts.JLabelSet(		200,600,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsItemName03][3]				+":"	,11,1);		//明細商品略名
		JLabel LB_MsQty					= B100_FrameParts.JLabelSet(		200,625,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsQty][3]						+":"	,11,1);		//明細個数
		
		JLabel LB_MsLot					= B100_FrameParts.JLabelSet(		500,525,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsLot][3]						+":"	,11,1);		//明細ロット指定
		JLabel LB_MsExpDate				= B100_FrameParts.JLabelSet(		500,550,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsExpDate][3]				+":"	,11,1);		//明細賞味期限指定
		JLabel LB_MsSubTotalWeight		= B100_FrameParts.JLabelSet(		500,575,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsSubTotalWeight][3]		+":"	,11,1);		//明細明細重量
		JLabel LB_MsSubTotalSize		= B100_FrameParts.JLabelSet(		500,600,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsSubTotalSize][3]			+":"	,11,1);		//明細明細サイズ
		JLabel LB_MsPackingQty			= B100_FrameParts.JLabelSet(		500,625,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsPackingQty][3]				+":"	,11,1);		//明細荷姿数量
		
		JLabel LB_MsUnitPrice			= B100_FrameParts.JLabelSet(		700,525,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsUnitPrice][3]				+":"	,11,1);		//明細単価
		JLabel LB_MsSubTotalPrice		= B100_FrameParts.JLabelSet(		700,550,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsSubTotalPrice][3]			+":"	,11,1);		//明細金額
		JLabel LB_MsCategoryCd			= B100_FrameParts.JLabelSet(		700,575,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsCategoryCd][3]				+":"	,11,1);		//明細商品分類
		JLabel LB_MsCategoryName		= B100_FrameParts.JLabelSet(		700,600,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsCategoryName][3]			+":"	,11,1);		//明細商品分類名
		JLabel LB_MsTildFG				= B100_FrameParts.JLabelSet(		700,625,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsTildFG][3]					+":"	,11,1);		//明細温度区分
		
		JLabel LB_MsCom01				= B100_FrameParts.JLabelSet(		900,525,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsCom01][3]					+":"	,11,1);		//明細コメント01
		JLabel LB_MsCom02				= B100_FrameParts.JLabelSet(		900,550,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsCom02][3]					+":"	,11,1);		//明細コメント02
		JLabel LB_MsCom03				= B100_FrameParts.JLabelSet(		900,575,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsCom03][3]					+":"	,11,1);		//明細コメント03
		JLabel LB_MsCom04				= B100_FrameParts.JLabelSet(		900,600,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsCom04][3]					+":"	,11,1);		//明細コメント04
		JLabel LB_MsCom05				= B100_FrameParts.JLabelSet(		900,625,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsCom05][3]					+":"	,11,1);		//明細コメント05
		
		JLabel LB_MsEntryDate			= B100_FrameParts.JLabelSet(	   1200,525,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsEntryDate][3]				+":"	,11,1);		//明細登録日
		JLabel LB_MsUpdateDate			= B100_FrameParts.JLabelSet(	   1200,550,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsUpdateDate][3]				+":"	,11,1);		//明細更新日
		JLabel LB_MsEntryUser			= B100_FrameParts.JLabelSet(	   1200,575,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsEntryUser][3]				+":"	,11,1);		//明細登録者
		JLabel LB_MsUpdateUser			= B100_FrameParts.JLabelSet(	   1200,600,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsUpdateUser][3]				+":"	,11,1);		//明細更新者
		
		JLabel LB_MsUnitName			= B100_FrameParts.JLabelSet(	   1200,625,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsUnitName][3]				+":"	,11,1);		//明細明細単位
		JLabel LB_MsUnitWeight			= B100_FrameParts.JLabelSet(	   1200,650,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsUnitWeight][3]				+":"	,11,1);		//明細単位重量
		JLabel LB_MsUnitSize			= B100_FrameParts.JLabelSet(	   1200,675,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsUnitSize][3]				+":"	,11,1);		//明細単位サイズ
		JLabel LB_MsPackingType			= B100_FrameParts.JLabelSet(	   1200,700,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsPackingType][3]			+":"	,11,1);		//明細荷姿タイプ
		JLabel LB_MsClItemCd			= B100_FrameParts.JLabelSet(	   1200,725,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsClItemCd][3]				+":"	,11,1);		//明細荷主商品CD
		JLabel LB_MsItemMDNo			= B100_FrameParts.JLabelSet(	   1200,750,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsItemMDNo][3]				+":"	,11,1);		//明細型番
		JLabel LB_MsJanCd				= B100_FrameParts.JLabelSet(	   1200,775,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsJanCd][3]					+":"	,11,1);		//明細荷姿JanCd
		

		PN_HD01.add(LB_ClDeliNo);
		PN_HD01.add(LB_PickupWhCd);
		PN_HD01.add(LB_PurposeFG);
		PN_HD01.add(LB_PlanDate);
		PN_HD01.add(LB_ShipDate);
		PN_HD01.add(LB_SPPlanDate);
		PN_HD01.add(LB_SPDate);
		PN_HD01.add(LB_SPTimeFG);
		PN_HD01.add(LB_SPTimeStr);
		PN_HD01.add(LB_SPTimeEnd);
		
		PN_HD01.add(LB_TotalWeight);
		PN_HD01.add(LB_TotalSize);
		PN_HD01.add(LB_TotalQty);
		PN_HD01.add(LB_DeliveryTypeCd01);
		PN_HD01.add(LB_DeliveryTypeCd02);
		PN_HD01.add(LB_DeliveryTypeCd03);
		PN_HD01.add(LB_DeliveryTypeCd04);
		PN_HD01.add(LB_DeliveryTypeCd05);
		PN_HD01.add(LB_CodFG);
		PN_HD01.add(LB_CodPayTotal);
		PN_HD01.add(LB_CodPay);
		PN_HD01.add(LB_CodConsumptionTax);

		PN_HD01.add(LB_ChildrenFG);
		PN_HD01.add(LB_ParentOkuriNo);
		
		PN_HD01.add(LB_NiokuriCd);
		PN_HD01.add(LB_NiokuriDepartmentCd);
		PN_HD01.add(LB_NiokuriName01);
		PN_HD01.add(LB_NiokuriName02);
		PN_HD01.add(LB_NiokuriName03);
		PN_HD01.add(LB_NiokuriPost);
		PN_HD01.add(LB_NiokuriAdd01);
		PN_HD01.add(LB_NiokuriAdd02);
		PN_HD01.add(LB_NiokuriAdd03);
		PN_HD01.add(LB_NioKuriTel);
		PN_HD01.add(LB_NioKuriFax);
		PN_HD01.add(LB_NioKuriMail);
		PN_HD01.add(LB_NiokuriMunicCd);

		PN_HD01.add(LB_DeliCd);
		PN_HD01.add(LB_ClDeliCd);
		PN_HD01.add(LB_DeliDepartmentCd);
		PN_HD01.add(LB_DeliName01);
		PN_HD01.add(LB_DeliName02);
		PN_HD01.add(LB_DeliName03);
		PN_HD01.add(LB_DeliPost);
		PN_HD01.add(LB_DeliAdd01);
		PN_HD01.add(LB_DeliAdd02);
		PN_HD01.add(LB_DeliAdd03);
		PN_HD01.add(LB_DeliTel);
		PN_HD01.add(LB_DeliFax);
		PN_HD01.add(LB_DeliMail);
		PN_HD01.add(LB_DeliMunicCd);

		PN_HD01.add(LB_Com01);
		PN_HD01.add(LB_Com02);
		PN_HD01.add(LB_Com03);
		PN_HD01.add(LB_Com04);
		PN_HD01.add(LB_Com05);

		PN_HD01.add(LB_Status);
		PN_HD01.add(LB_TaxFg);
		PN_HD01.add(LB_TaxRate);
		PN_HD01.add(LB_DeliFee);
		PN_HD01.add(LB_AddDeliFee01);
		PN_HD01.add(LB_AddDeliFee02);
		PN_HD01.add(LB_AddDeliFee03);
		PN_HD01.add(LB_HaighWayFee01);
		PN_HD01.add(LB_HaighWayFee02);
		PN_HD01.add(LB_ConsumptionTax);
		PN_HD01.add(LB_WithOutTaxTotal);
		PN_HD01.add(LB_TotalFee);
		PN_HD01.add(LB_FeeFixFG);
		PN_HD01.add(LB_FeeFixDate);
		PN_HD01.add(LB_ReceiptStampFG);
		PN_HD01.add(LB_ReceiptStampDate);
		PN_HD01.add(LB_InvoiceStatus);
		PN_HD01.add(LB_EntryPG);
		PN_HD01.add(LB_UpdatePG);
		
		
		PN_HD01.add(LB_MsNo);
		PN_HD01.add(LB_MsDeliNo);
		PN_HD01.add(LB_MsDelliMsNo);
		PN_HD01.add(LB_MsClOrderNo);
		PN_HD01.add(LB_MsItemCd);
		PN_HD01.add(LB_MsItemName01);
		PN_HD01.add(LB_MsItemName02);
		PN_HD01.add(LB_MsItemName03);
		PN_HD01.add(LB_MsUnitWeight);
		PN_HD01.add(LB_MsUnitSize);
		PN_HD01.add(LB_MsQty);
		PN_HD01.add(LB_MsPackingQty);
		PN_HD01.add(LB_MsUnitName);
		PN_HD01.add(LB_MsSubTotalWeight);
		PN_HD01.add(LB_MsSubTotalSize);
		PN_HD01.add(LB_MsUnitPrice);
		PN_HD01.add(LB_MsSubTotalPrice);
		PN_HD01.add(LB_MsCategoryCd);
		PN_HD01.add(LB_MsCategoryName);
		PN_HD01.add(LB_MsTildFG);
		PN_HD01.add(LB_MsCom01);
		PN_HD01.add(LB_MsCom02);
		PN_HD01.add(LB_MsCom03);
		PN_HD01.add(LB_MsCom04);
		PN_HD01.add(LB_MsCom05);
		PN_HD01.add(LB_MsEntryDate);
		PN_HD01.add(LB_MsUpdateDate);
		PN_HD01.add(LB_MsEntryUser);
		PN_HD01.add(LB_MsUpdateUser);
		PN_HD01.add(LB_MsLot);
		PN_HD01.add(LB_MsExpDate);
		PN_HD01.add(LB_MsPackingType);
		PN_HD01.add(LB_MsClItemCd);
		PN_HD01.add(LB_MsItemMDNo);
		PN_HD01.add(LB_MsJanCd);
		
		
		PN_HD01.add(TB_ClDeliNo);
		PN_HD01.add(TB_PickupWhCd);
		PN_HD01.add(TB_PurposeFG);
		PN_HD01.add(TB_PlanDate);
		PN_HD01.add(TB_ShipDate);
		PN_HD01.add(TB_SPPlanDate);
		PN_HD01.add(TB_SPDate);
		PN_HD01.add(TB_SPTimeFG);
		PN_HD01.add(TB_SPTimeStr);
		PN_HD01.add(TB_SPTimeEnd);
		
		PN_HD01.add(TB_DeliveryTypeCd01);
		PN_HD01.add(TB_DeliveryTypeCd02);
		PN_HD01.add(TB_DeliveryTypeCd03);
		PN_HD01.add(TB_DeliveryTypeCd04);
		PN_HD01.add(TB_DeliveryTypeCd05);
		PN_HD01.add(TB_CodFG);
		PN_HD01.add(TB_CodPayTotal);
		PN_HD01.add(TB_CodPay);
		PN_HD01.add(TB_CodConsumptionTax);
		
		PN_HD01.add(TB_NiokuriCd);
		PN_HD01.add(TB_NiokuriDepartmentCd);
		PN_HD01.add(TB_NiokuriName01);
		PN_HD01.add(TB_NiokuriName02);
		PN_HD01.add(TB_NiokuriName03);
		PN_HD01.add(TB_NiokuriPost);
		PN_HD01.add(TB_NiokuriAdd01);
		PN_HD01.add(TB_NiokuriAdd02);
		PN_HD01.add(TB_NiokuriAdd03);
		PN_HD01.add(TB_NioKuriTel);
		PN_HD01.add(TB_NioKuriFax);
		PN_HD01.add(TB_NioKuriMail);
		PN_HD01.add(TB_NiokuriMunicCd);

		PN_HD01.add(TB_ClDeliCd);
		PN_HD01.add(TB_DeliCd);
		PN_HD01.add(TB_DeliDepartmentCd);
		PN_HD01.add(TB_DeliName01);
		PN_HD01.add(TB_DeliName02);
		PN_HD01.add(TB_DeliName03);
		PN_HD01.add(TB_DeliPost);
		PN_HD01.add(TB_DeliAdd01);
		PN_HD01.add(TB_DeliAdd02);
		PN_HD01.add(TB_DeliAdd03);
		PN_HD01.add(TB_DeliTel);
		PN_HD01.add(TB_DeliFax);
		PN_HD01.add(TB_DeliMail);
		PN_HD01.add(TB_DeliMunicCd);

		PN_HD01.add(TB_TotalWeight);
		PN_HD01.add(TB_TotalSize);
		PN_HD01.add(TB_TotalQty);
		PN_HD01.add(TB_ChildrenFG);
		PN_HD01.add(TB_ParentOkuriNo);
		PN_HD01.add(TB_Status);
		PN_HD01.add(TB_Com01);
		PN_HD01.add(TB_Com02);
		PN_HD01.add(TB_Com03);
		PN_HD01.add(TB_Com04);
		PN_HD01.add(TB_Com05);

		PN_HD01.add(TB_TaxFg);
		PN_HD01.add(TB_TaxRate);
		PN_HD01.add(TB_DeliFee);
		PN_HD01.add(TB_AddDeliFee01);
		PN_HD01.add(TB_AddDeliFee02);
		PN_HD01.add(TB_AddDeliFee03);
		PN_HD01.add(TB_HaighWayFee01);
		PN_HD01.add(TB_HaighWayFee02);
		PN_HD01.add(TB_ConsumptionTax);
		PN_HD01.add(TB_WithOutTaxTotal);
		PN_HD01.add(TB_TotalFee);
		PN_HD01.add(TB_FeeFixFG);
		PN_HD01.add(TB_FeeFixDate);
		PN_HD01.add(TB_ReceiptStampFG);
		PN_HD01.add(TB_ReceiptStampDate);
		PN_HD01.add(TB_InvoiceStatus);
		PN_HD01.add(TB_EntryPG);
		PN_HD01.add(TB_UpdatePG);
		
		
		OkuriMs_fm.add(TabPaneSet);
		
		
		
		
		
		Object[][] OkuriMsRt= OkuriMsRt(ClCd,TgtOkuriNo);
		ViewSet(OkuriMsRt,tableModel_msOkuriMs);
		
		OkuriMs_fm.setVisible(true);
		
		RenewFg	= true;
		
		//制御対象まとめる使わない項目はとりあえずnull
		Object[] ControlTgt = new Object[RtOkuriMsRt.length];
		
		ControlTgt[T100_OkuriMsRt.ColClCd] 				= TB_ClCd;
		ControlTgt[T100_OkuriMsRt.ColInvoiceWhCd]	 		= null;				//TB_ColInvoiceWhCd;
		ControlTgt[T100_OkuriMsRt.ColOkuriNo] 				= TB_SearchOkuriNo;
		ControlTgt[T100_OkuriMsRt.ColClDeliNo] 			= TB_ClDeliNo;
		ControlTgt[T100_OkuriMsRt.ColPickupWhCd] 			= TB_PickupWhCd;
		ControlTgt[T100_OkuriMsRt.ColPurposeFG] 			= TB_PurposeFG;
		ControlTgt[T100_OkuriMsRt.ColPlanDate] 			= TB_PlanDate;
		ControlTgt[T100_OkuriMsRt.ColShipDate] 			= TB_ShipDate;
		ControlTgt[T100_OkuriMsRt.ColSPPlanDate] 			= TB_SPPlanDate;
		ControlTgt[T100_OkuriMsRt.ColSPDate] 				= TB_SPDate;
		ControlTgt[T100_OkuriMsRt.ColSPTimeFG] 			= TB_SPTimeFG;
		ControlTgt[T100_OkuriMsRt.ColSPTimeStr]			= TB_SPTimeStr;
		ControlTgt[T100_OkuriMsRt.ColSPTimeEnd] 			= TB_SPTimeEnd;
		ControlTgt[T100_OkuriMsRt.ColTotalWeight] 		= TB_TotalWeight;
		ControlTgt[T100_OkuriMsRt.ColTotalSize] 			= TB_TotalSize;
		ControlTgt[T100_OkuriMsRt.ColTotalQty] 			= TB_TotalQty;
		ControlTgt[T100_OkuriMsRt.ColDeliveryTypeCd01] 	= TB_DeliveryTypeCd01;
		ControlTgt[T100_OkuriMsRt.ColDeliTypeName] 		= null;				//TB_DeliTypeName;
		ControlTgt[T100_OkuriMsRt.ColDeliveryTypeCd02] 	= TB_DeliveryTypeCd02;
		ControlTgt[T100_OkuriMsRt.ColDeliTypeName02] 		= null;				//TB_DeliTypeName02;
		ControlTgt[T100_OkuriMsRt.ColDeliveryTypeCd03] 	= TB_DeliveryTypeCd03;
		ControlTgt[T100_OkuriMsRt.ColDeliTypeName03] 		= null;				//TB_DeliTypeName03;
		ControlTgt[T100_OkuriMsRt.ColDeliveryTypeCd04] 	= TB_DeliveryTypeCd04;
		ControlTgt[T100_OkuriMsRt.ColDeliTypeName04] 		= null;				//TB_DeliTypeName04;
		ControlTgt[T100_OkuriMsRt.ColDeliveryTypeCd05] 	= TB_DeliveryTypeCd05;
		ControlTgt[T100_OkuriMsRt.ColDeliTypeName05] 		= null;				//TB_DeliTypeName05;

		ControlTgt[T100_OkuriMsRt.ColCodFG] 				= TB_CodFG;
		ControlTgt[T100_OkuriMsRt.ColCodPayTotal] 			= TB_CodPayTotal;
		ControlTgt[T100_OkuriMsRt.ColCodPay] 				= TB_CodPay;
		ControlTgt[T100_OkuriMsRt.ColCodConsumptionTax] 	= TB_CodConsumptionTax;

		ControlTgt[T100_OkuriMsRt.ColChildrenFG] 			= TB_ChildrenFG;
		ControlTgt[T100_OkuriMsRt.ColParentOkuriNo] 		= TB_ParentOkuriNo;

		ControlTgt[T100_OkuriMsRt.ColNiokuriCd] 			= TB_NiokuriCd;
		ControlTgt[T100_OkuriMsRt.ColNiokuriDepartmentCd] 	= TB_NiokuriDepartmentCd;
		ControlTgt[T100_OkuriMsRt.ColNiokuriName01] 		= TB_NiokuriName01;
		ControlTgt[T100_OkuriMsRt.ColNiokuriName02] 		= TB_NiokuriName02;
		ControlTgt[T100_OkuriMsRt.ColNiokuriName03] 		= TB_NiokuriName03;
		ControlTgt[T100_OkuriMsRt.ColNiokuriPost] 			= TB_NiokuriPost;
		ControlTgt[T100_OkuriMsRt.ColNiokuriAdd01] 			= TB_NiokuriAdd01;
		ControlTgt[T100_OkuriMsRt.ColNiokuriAdd02] 			= TB_NiokuriAdd02;
		ControlTgt[T100_OkuriMsRt.ColNiokuriAdd03] 			= TB_NiokuriAdd03;
		ControlTgt[T100_OkuriMsRt.ColNioKuriTel] 			= TB_NioKuriTel;
		ControlTgt[T100_OkuriMsRt.ColNioKuriFax] 			= TB_NioKuriFax;
		ControlTgt[T100_OkuriMsRt.ColNioKuriMail] 			= TB_NioKuriMail;
		ControlTgt[T100_OkuriMsRt.ColNiokuriMunicCd] 		= TB_NiokuriMunicCd;

		ControlTgt[T100_OkuriMsRt.ColDeliCd] 				= TB_DeliCd;
		ControlTgt[T100_OkuriMsRt.ColClDeliCd] 				= TB_ClDeliCd;
		ControlTgt[T100_OkuriMsRt.ColDeliDepartmentCd] 		= TB_DeliDepartmentCd;
		ControlTgt[T100_OkuriMsRt.ColDeliName01] 			= TB_DeliName01;
		ControlTgt[T100_OkuriMsRt.ColDeliName02]		 	= TB_DeliName02;
		ControlTgt[T100_OkuriMsRt.ColDeliName03] 			= TB_DeliName03;
		ControlTgt[T100_OkuriMsRt.ColDeliPost] 				= TB_DeliPost;
		ControlTgt[T100_OkuriMsRt.ColDeliAdd01] 			= TB_DeliAdd01;
		ControlTgt[T100_OkuriMsRt.ColDeliAdd02] 			= TB_DeliAdd02;
		ControlTgt[T100_OkuriMsRt.ColDeliAdd03] 			= TB_DeliAdd03;
		ControlTgt[T100_OkuriMsRt.ColDeliTel] 				= TB_DeliTel;
		ControlTgt[T100_OkuriMsRt.ColDeliFax] 				= TB_DeliFax;
		ControlTgt[T100_OkuriMsRt.ColDeliMail] 				= TB_DeliMail;
		ControlTgt[T100_OkuriMsRt.ColDeliMunicCd] 			= TB_DeliMunicCd;

		ControlTgt[T100_OkuriMsRt.ColCom01] 				= TB_Com01;
		ControlTgt[T100_OkuriMsRt.ColCom02] 				= TB_Com02;
		ControlTgt[T100_OkuriMsRt.ColCom03] 				= TB_Com03;
		ControlTgt[T100_OkuriMsRt.ColCom04] 				= TB_Com04;
		ControlTgt[T100_OkuriMsRt.ColCom05] 				= TB_Com05;
/*
		ControlTgt[T100_OkuriMsRt.ColStatus] 				= TB_Status;
		ControlTgt[T100_OkuriMsRt.ColTaxFg] 				= TB_TaxFg;
		ControlTgt[T100_OkuriMsRt.ColTaxRate] 				= TB_TaxRate;
		ControlTgt[T100_OkuriMsRt.ColDeliFee] 				= TB_DeliFee;
		ControlTgt[T100_OkuriMsRt.ColAddDeliFee01] 			= TB_AddDeliFee01;
		ControlTgt[T100_OkuriMsRt.ColAddDeliFee02] 			= TB_AddDeliFee02;
		ControlTgt[T100_OkuriMsRt.ColAddDeliFee03] 			= TB_AddDeliFee03;
		ControlTgt[T100_OkuriMsRt.ColHaighWayFee01] 		= TB_HaighWayFee01;
		ControlTgt[T100_OkuriMsRt.ColHaighWayFee02] 		= TB_HaighWayFee02;
		ControlTgt[T100_OkuriMsRt.ColConsumptionTax] 		= TB_ConsumptionTax;
		ControlTgt[T100_OkuriMsRt.ColWithOutTaxTotal] 		= TB_WithOutTaxTotal;
		ControlTgt[T100_OkuriMsRt.ColTotalFee] 				= TB_TotalFee;
		ControlTgt[T100_OkuriMsRt.ColFeeFixFG] 				= TB_FeeFixFG;
		ControlTgt[T100_OkuriMsRt.ColFeeFixDate] 			= TB_FeeFixDate;
		ControlTgt[T100_OkuriMsRt.ColReceiptStampFG] 		= TB_ReceiptStampFG;
		ControlTgt[T100_OkuriMsRt.ColReceiptStampDate] 		= TB_ReceiptStampDate;
		ControlTgt[T100_OkuriMsRt.ColInvoiceStatus] 		= TB_InvoiceStatus;
		ControlTgt[T100_OkuriMsRt.ColEntryDate] 			= TB_EntryDate;
		ControlTgt[T100_OkuriMsRt.ColUpdateDate] 			= TB_UpdateDate;
		ControlTgt[T100_OkuriMsRt.ColEntryUser] 			= TB_EntryUser;
		ControlTgt[T100_OkuriMsRt.ColUpdateUser] 			= TB_UpdateUser;
		ControlTgt[T100_OkuriMsRt.ColEntryPG] 				= TB_EntryPG;
		ControlTgt[T100_OkuriMsRt.ColUpdatePG] 				= TB_UpdatePG;

		ControlTgt[T100_OkuriMsRt.ColUseFeeBasePtCd] 		= TB_UseFeeBasePtCd;
		ControlTgt[T100_OkuriMsRt.ColWmsStatus] 			= TB_WmsStatus;
		ControlTgt[T100_OkuriMsRt.ColWmsShipDate] 			= TB_WmsShipDate;
		ControlTgt[T100_OkuriMsRt.ColCourseGpCd] 			= TB_CourseGpCd;
		ControlTgt[T100_OkuriMsRt.ColCourseCD] 				= TB_CourseCD;
		ControlTgt[T100_OkuriMsRt.ColCourseCDEda] 			= TB_CourseCDEda;
		ControlTgt[T100_OkuriMsRt.ColPitGrp] 				= TB_PitGrp;
		ControlTgt[T100_OkuriMsRt.ColPit01] 				= TB_Pit01;
		ControlTgt[T100_OkuriMsRt.ColPit02] 				= TB_Pit02;
		ControlTgt[T100_OkuriMsRt.ColPit03] 				= TB_Pit03;
		ControlTgt[T100_OkuriMsRt.ColPit04] 				= TB_Pit04;
		ControlTgt[T100_OkuriMsRt.ColPit05] 				= TB_Pit05;

		ControlTgt[T100_OkuriMsRt.ColCLName01] 				= TB_CLName01;
		ControlTgt[T100_OkuriMsRt.ColClGpCD] 				= TB_ClGpCD;
		ControlTgt[T100_OkuriMsRt.ColCLGpName01] 			= TB_CLGpName01;
		
		
		ControlTgt[T100_OkuriMsRt.ColMsClCd] 				= TB_MsClCd;
		ControlTgt[T100_OkuriMsRt.ColMsInvoiceWhCd] 		= TB_MsInvoiceWhCd;
		ControlTgt[T100_OkuriMsRt.ColMsOkuriNo] 			= TB_MsOkuriNo;
		ControlTgt[T100_OkuriMsRt.ColMsNo] 					= TB_MsNo;
		ControlTgt[T100_OkuriMsRt.ColMsDeliNo] 				= TB_MsDeliNo;
		ControlTgt[T100_OkuriMsRt.ColMsDelliMsNo] 			= TB_MsDelliMsNo;
		ControlTgt[T100_OkuriMsRt.ColMsClOrderNo] 			= TB_MsClOrderNo;
		ControlTgt[T100_OkuriMsRt.ColMsClGpCd] 				= TB_MsClGpCd;
		ControlTgt[T100_OkuriMsRt.ColMsItemCd] 				= TB_MsItemCd;
		ControlTgt[T100_OkuriMsRt.ColMsItemName01] 			= TB_MsItemName01;
		ControlTgt[T100_OkuriMsRt.ColMsItemName02] 			= TB_MsItemName02;
		ControlTgt[T100_OkuriMsRt.ColMsItemName03] 			= TB_MsItemName03;
		ControlTgt[T100_OkuriMsRt.ColMsUnitWeight] 			= TB_MsUnitWeight;
		ControlTgt[T100_OkuriMsRt.ColMsUnitSize] 			= TB_MsUnitSize;
		ControlTgt[T100_OkuriMsRt.ColMsQty] 				= TB_MsQty;
		ControlTgt[T100_OkuriMsRt.ColMsPackingQty] 			= TB_MsPackingQty;
		ControlTgt[T100_OkuriMsRt.ColMsUnitName] 			= TB_MsUnitName;
		ControlTgt[T100_OkuriMsRt.ColMsSubTotalWeight]		= TB_MsSubTotalWeight;
		ControlTgt[T100_OkuriMsRt.ColMsSubTotalSize] 		= TB_MsSubTotalSize;
		ControlTgt[T100_OkuriMsRt.ColMsUnitPrice] 			= TB_MsUnitPrice;
		ControlTgt[T100_OkuriMsRt.ColMsSubTotalPrice] 		= TB_MsSubTotalPrice;
		ControlTgt[T100_OkuriMsRt.ColMsCategoryCd] 			= TB_MsCategoryCd;
		ControlTgt[T100_OkuriMsRt.ColMsCategoryName] 		= TB_MsCategoryName;
		ControlTgt[T100_OkuriMsRt.ColMsTildFG] 				= TB_MsTildFG;
		ControlTgt[T100_OkuriMsRt.ColMsTildName] 			= TB_MsTildName;
		ControlTgt[T100_OkuriMsRt.ColMsCom01] 				= TB_MsCom01;
		ControlTgt[T100_OkuriMsRt.ColMsCom02] 				= TB_MsCom02;
		ControlTgt[T100_OkuriMsRt.ColMsCom03] 				= TB_MsCom03;
		ControlTgt[T100_OkuriMsRt.ColMsCom04] 				= TB_MsCom04;
		ControlTgt[T100_OkuriMsRt.ColMsCom05] 				= TB_MsCom05;
		ControlTgt[T100_OkuriMsRt.ColMsEntryDate] 			= TB_MsEntryDate;
		ControlTgt[T100_OkuriMsRt.ColMsUpdateDate] 			= TB_MsUpdateDate;
		ControlTgt[T100_OkuriMsRt.ColMsEntryUser] 			= TB_MsEntryUser;
		ControlTgt[T100_OkuriMsRt.ColMsUpdateUser] 			= TB_MsUpdateUser;
		ControlTgt[T100_OkuriMsRt.ColMsLot] 				= TB_MsLot;
		ControlTgt[T100_OkuriMsRt.ColMsExpDate] 			= TB_MsExpDate;
		ControlTgt[T100_OkuriMsRt.ColMsPackingType] 		= TB_MsPackingType;
		ControlTgt[T100_OkuriMsRt.ColMsClItemCd] 			= TB_MsClItemCd;
		ControlTgt[T100_OkuriMsRt.ColMsItemMDNo] 			= TB_MsItemMDNo;
		ControlTgt[T100_OkuriMsRt.ColMsJanCd] 				= TB_MsJanCd;
		*/
		
		
		OkuriMsSearchKickBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg	= false;
					int RowCount = tableModel_msOkuriMs.getRowCount();
					for(int i=0;i<RowCount;i++) {
						tableModel_msOkuriMs.removeRow(0);
					}
					
					String TgtClCd		= B100_TextControl.Trim(B100_DefaultVariable.ClList[1][TB_ClCd.getSelectedIndex()]);
					String TgtOkuriNo	= B100_TextControl.Trim(TB_SearchOkuriNo.getText());
					
					Object[][] OkuriMsRt= OkuriMsRt(TgtClCd,TgtOkuriNo);
					
					ViewSet(OkuriMsRt,tableModel_msOkuriMs);
					
					RenewFg	= true;
				}
			}
		});
		
		//チェックボックス操作時の挙動
		tableModel_msOkuriMs.addTableModelListener(new TableModelListener(){
			public void tableChanged(TableModelEvent e){
				if(RenewFg) {
					RenewFg = false;
					int row_count = tbOkuriMs.getRowCount();
					Boolean setBL=Boolean.valueOf(false);
					for(int i=0;i<row_count;i++){
						if(i!=e.getFirstRow()){
							tableModel_msOkuriMs.setValueAt(setBL, i, 0);
						}else {
	
						}
					}
					RenewFg = true;
				}
			}
		});
		
		
		OkuriMsExit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				OkuriMs_fm.setVisible(false);
			}
		});

		Object[] Rt = {
				 OkuriMs_fm
				,tableModel_msOkuriMs
				,tbOkuriMs
				,OkuriMsEntry_btn
				};
		return Rt;
	}
	
	private static Object[][] OkuriMsRt(String TgtClCd,String TgtOkuriNo){
		if(null==TgtOkuriNo) {TgtOkuriNo="";}
		if("".equals(TgtClCd)) {TgtClCd=A00000_Main.ClCd;}
		
		ArrayList<String> SearchInvoiceWHCD			= new ArrayList<String>();	//倉庫CD
		ArrayList<String> SearchClGpCD				= new ArrayList<String>();	//荷主グループCD
		ArrayList<String> SearchClCd				= new ArrayList<String>();	//荷主CD
		ArrayList<String> SearchOkuriNo				= new ArrayList<String>();	//送り状番号
		ArrayList<String> SearchClDeliNo			= new ArrayList<String>();	//荷主管理番号
		ArrayList<String> SearchPickupWhCd			= new ArrayList<String>();	//集荷倉庫CD
		ArrayList<String> SearchPurposeFG			= new ArrayList<String>();	//目的フラグ
		ArrayList<String> SearchPlanDateStr			= new ArrayList<String>();	//出荷予定日開始
		ArrayList<String> SearchShipDateStr			= new ArrayList<String>();	//出荷実績日開始
		ArrayList<String> SearchSPPlanDateStr		= new ArrayList<String>();	//着日指定開始
		ArrayList<String> SearchSPDateStr			= new ArrayList<String>();	//着日実績開始
		
		ArrayList<String> SearchPlanDateEnd			= new ArrayList<String>();	//出荷予定日終了
		ArrayList<String> SearchShipDateEnd			= new ArrayList<String>();	//出荷実績日終了
		ArrayList<String> SearchSPPlanDateEnd		= new ArrayList<String>();	//着日指定終了
		ArrayList<String> SearchSPDateEnd			= new ArrayList<String>();	//着日実績終了
		
		ArrayList<Float> SearchTotalWeightMin		= new ArrayList<Float>();	//荷物重量(kg)最小
		ArrayList<Float> SearchTotalSizeMin			= new ArrayList<Float>();	//荷物サイズ最小
		ArrayList<Integer> SearchTotalQtyMin		= new ArrayList<Integer>();	//個口数最小
		
		ArrayList<Float> SearchTotalWeightMax		= new ArrayList<Float>();	//荷物重量(kg)最大
		ArrayList<Float> SearchTotalSizeMax			= new ArrayList<Float>();	//荷物サイズ最大
		ArrayList<Integer> SearchTotalQtyMax		= new ArrayList<Integer>();	//個口数最大
		
		ArrayList<String> SearchDeliveryTypeCd01	= new ArrayList<String>();	//運送タイプ01
		ArrayList<String> SearchDeliveryTypeCd02	= new ArrayList<String>();	//運送タイプ02
		ArrayList<String> SearchDeliveryTypeCd03	= new ArrayList<String>();	//運送タイプ03
		ArrayList<String> SearchDeliveryTypeCd04	= new ArrayList<String>();	//運送タイプ04
		ArrayList<String> SearchDeliveryTypeCd05	= new ArrayList<String>();	//運送タイプ05
		
		ArrayList<Integer> SearchCodFG				= new ArrayList<Integer>();	//代引区分
		ArrayList<Integer> SearchCodPayTotalMin		= new ArrayList<Integer>();	//代引収受金額合計最小
		ArrayList<Integer> SearchCodPayTotalMax		= new ArrayList<Integer>();	//代引収受金額合計最大
		
		ArrayList<Integer> SearchChildrenFG			= new ArrayList<Integer>();	//子伝票区分
		ArrayList<String> SearchParentOkuriNo		= new ArrayList<String>();	//親伝票番号
		
		ArrayList<String> SearchNiokuriCd			= new ArrayList<String>();	//荷送人CD
		ArrayList<String> SearchNiokuriDepartmentCd	= new ArrayList<String>();	//荷送人部署CD
		ArrayList<String> SearchNiokuriName			= new ArrayList<String>();	//荷送人名称
		ArrayList<String> SearchNiokuriPost			= new ArrayList<String>();	//荷送人郵便番号
		ArrayList<String> SearchNiokuriAdd			= new ArrayList<String>();	//荷送人住所
		ArrayList<String> SearchNioKuriTel			= new ArrayList<String>();	//荷送人Tel
		ArrayList<String> SearchNioKuriFax			= new ArrayList<String>();	//荷送人Fax
		ArrayList<String> SearchNioKuriMail			= new ArrayList<String>();	//荷送人Mail
		ArrayList<String> SearchNiokuriMunicCd		= new ArrayList<String>();	//荷送人市区町村CD
		
		ArrayList<String> SearchDeliCd				= new ArrayList<String>();	//届先CD
		ArrayList<String> SearchClDeliCd			= new ArrayList<String>();	//荷主届先CD
		ArrayList<String> SearchDeliDepartmentCd	= new ArrayList<String>();	//届先部署CD
		ArrayList<String> SearchDeliName			= new ArrayList<String>();	//届先名称
		ArrayList<String> SearchDeliPost			= new ArrayList<String>();	//届先郵便番号
		ArrayList<String> SearchDeliAdd				= new ArrayList<String>();	//届先住所
		ArrayList<String> SearchDeliTel				= new ArrayList<String>();	//届先Tel
		ArrayList<String> SearchDeliFax				= new ArrayList<String>();	//届先Fax
		ArrayList<String> SearchDeliMail			= new ArrayList<String>();	//届先Mail
		ArrayList<String> SearchDeliMunicCd			= new ArrayList<String>();	//届先市区町村CD
		
		ArrayList<String> SearchCom					= new ArrayList<String>();	//コメント
		ArrayList<Integer> SearchStatus				= new ArrayList<Integer>();	//運送ステータス
		
		ArrayList<Integer> SearchFeeFixFG			= new ArrayList<Integer>();	//運賃確定フラグ
		ArrayList<Integer> SearchReceiptStampFG		= new ArrayList<Integer>();	//受領印フラグ
		ArrayList<Integer> SearchInvoiceStatus		= new ArrayList<Integer>();	//請求ステータス
		
		ArrayList<Integer> SearchWithOutTaxTotalMin	= new ArrayList<Integer>();	//税別運賃合計最小
		ArrayList<Integer> SearchTotalFeeMin		= new ArrayList<Integer>();	//税込運賃合計税込運賃合計
		ArrayList<String> SearchFeeFixDateStr		= new ArrayList<String>();	//運賃確定日時開始
		ArrayList<String> SearchReceiptStampDateStr	= new ArrayList<String>();	//受領印日時開始
		ArrayList<String> SearchEntryDateStr		= new ArrayList<String>();	//登録日終了
		ArrayList<String> SearchUpdateDateStr		= new ArrayList<String>();	//更新日終了
		
		ArrayList<Integer> SearchWithOutTaxTotalMax	= new ArrayList<Integer>();	//税別運賃合計最大
		ArrayList<Integer> SearchTotalFeeMax		= new ArrayList<Integer>();	//税込運賃合計最大
		ArrayList<String> SearchFeeFixDateEnd		= new ArrayList<String>();	//運賃確定日時終了
		ArrayList<String> SearchReceiptStampDateEnd	= new ArrayList<String>();	//受領印日時終了
		ArrayList<String> SearchEntryDateEnd		= new ArrayList<String>();	//登録日終了
		ArrayList<String> SearchUpdateDateEnd		= new ArrayList<String>();	//更新日終了
		
		ArrayList<String> SearchEntryUser			= new ArrayList<String>();	//登録者
		ArrayList<String> SearchUpdateUser			= new ArrayList<String>();	//更新者
		ArrayList<String> SearchEntryPG				= new ArrayList<String>();	//登録プログラム
		ArrayList<String> SearchUpdatePG			= new ArrayList<String>();	//更新プログラム
		ArrayList<String> SearchUseFeeBasePtCd		= new ArrayList<String>();	//運転計算タリフ
		ArrayList<Integer> SearchWmsStatus			= new ArrayList<Integer>();	//倉庫出荷ステータス
		ArrayList<String> SearchWmsShipDateStr		= new ArrayList<String>();	//倉庫出荷日時開始
		ArrayList<String> SearchWmsShipDateEnd		= new ArrayList<String>();	//倉庫出荷日時終了
		ArrayList<String> SearchCourseGpCd			= new ArrayList<String>();	//配車コースグループコード
		ArrayList<String> SearchCourseCD			= new ArrayList<String>();	//配車コースコード
		ArrayList<Integer> SearchCourseCDEda		= new ArrayList<Integer>();	//配車コースコード枝番
		ArrayList<String> SearchPitGrp				= new ArrayList<String>();	//荷物払出ピットグループ
		ArrayList<String> SearchPit					= new ArrayList<String>();	//荷物払出ピット
		
		ArrayList<String> SearchMsItemCd			= new ArrayList<String>();	//商品CD
		ArrayList<String> SearchMsItemName			= new ArrayList<String>();	//商品名
		
		ArrayList<String> SearchClItemCd			= new ArrayList<String>();	//荷主商品CD
		
		ArrayList<String> SearchMsCategoryCd		= new ArrayList<String>();	//カテゴリCD
		ArrayList<String> SearchMsCategoryName		= new ArrayList<String>();	//カテゴリ名
		ArrayList<String> SearchMsTildFG			= new ArrayList<String>();	//温度区分
		ArrayList<String> SearchMsTildName			= new ArrayList<String>();	//温度区分名
		
		ArrayList<String> SearchMsLot				= new ArrayList<String>();	//ロット指定
		ArrayList<String> SearchMsExpDateStr		= new ArrayList<String>();	//賞味期限指定開始
		ArrayList<String> SearchMsExpDateEnd		= new ArrayList<String>();	//賞味期限指定終了
		ArrayList<Integer> SearchMsPackingType		= new ArrayList<Integer>();	//荷姿タイプ
		
		boolean AllSearch = false;
		
		SearchClCd.add(TgtClCd);
		SearchOkuriNo.add(TgtOkuriNo);
		
		Object[][] OkuriMsRt	= T100_OkuriMsRt.OkuriMsRt(
				SearchInvoiceWHCD,			//倉庫CD
				SearchClGpCD,				//荷主グループCD
				SearchClCd,					//荷主CD
				SearchOkuriNo,				//送り状番号
				SearchClDeliNo,				//荷主管理番号
				SearchPickupWhCd,			//集荷倉庫CD
				SearchPurposeFG,			//目的フラグ
				SearchPlanDateStr,			//出荷予定日開始
				SearchShipDateStr,			//出荷実績日開始
				SearchSPPlanDateStr,		//着日指定開始
				SearchSPDateStr,			//着日実績開始
				
				SearchPlanDateEnd,			//出荷予定日終了
				SearchShipDateEnd,			//出荷実績日終了
				SearchSPPlanDateEnd,		//着日指定終了
				SearchSPDateEnd,			//着日実績終了
				
				SearchTotalWeightMin,		//荷物重量(kg)最小
				SearchTotalSizeMin,			//荷物サイズ最小
				SearchTotalQtyMin,			//個口数最小
				
				SearchTotalWeightMax,		//荷物重量(kg)最大
				SearchTotalSizeMax,			//荷物サイズ最大
				SearchTotalQtyMax,			//個口数最大
				
				SearchDeliveryTypeCd01,		//運送タイプ01
				SearchDeliveryTypeCd02,		//運送タイプ02
				SearchDeliveryTypeCd03,		//運送タイプ03
				SearchDeliveryTypeCd04,		//運送タイプ04
				SearchDeliveryTypeCd05,		//運送タイプ05
				
				SearchCodFG,				//代引区分
				SearchCodPayTotalMin,		//代引収受金額合計最小
				SearchCodPayTotalMax,		//代引収受金額合計最大
				
				SearchChildrenFG,			//子伝票区分
				SearchParentOkuriNo,		//親伝票番号
				
				SearchNiokuriCd,			//荷送人CD
				SearchNiokuriDepartmentCd,	//荷送人部署CD
				SearchNiokuriName,			//荷送人名称
				SearchNiokuriPost,			//荷送人郵便番号
				SearchNiokuriAdd,			//荷送人住所
				SearchNioKuriTel,			//荷送人Tel
				SearchNioKuriFax,			//荷送人Fax
				SearchNioKuriMail,			//荷送人Mail
				SearchNiokuriMunicCd,		//荷送人市区町村CD
				
				SearchDeliCd,				//届先CD
				SearchClDeliCd,				//荷主届先CD
				SearchDeliDepartmentCd,		//届先部署CD
				SearchDeliName,				//届先名称
				SearchDeliPost,				//届先郵便番号
				SearchDeliAdd,				//届先住所
				SearchDeliTel,				//届先Tel
				SearchDeliFax,				//届先Fax
				SearchDeliMail,				//届先Mail
				SearchDeliMunicCd,			//届先市区町村CD
				
				SearchCom,					//コメント
				SearchStatus,				//運送ステータス
				
				SearchFeeFixFG,				//運賃確定フラグ
				SearchReceiptStampFG,		//受領印フラグ
				SearchInvoiceStatus,		//請求ステータス
				
				SearchWithOutTaxTotalMin,	//税別運賃合計最小
				SearchTotalFeeMin,			//税込運賃合計最小
				SearchFeeFixDateStr,		//運賃確定日時開始
				SearchReceiptStampDateStr,	//受領印日時開始
				SearchEntryDateStr,			//登録日開始
				SearchUpdateDateStr,		//更新日開始
				
				SearchWithOutTaxTotalMax,	//税別運賃合計最大
				SearchTotalFeeMax,			//税込運賃合計最大
				SearchFeeFixDateEnd,		//運賃確定日時終了
				SearchReceiptStampDateEnd,	//受領印日時終了
				SearchEntryDateEnd,			//登録日終了
				SearchUpdateDateEnd,		//更新日終了
				
				SearchEntryUser,			//登録者
				SearchUpdateUser,			//更新者
				SearchEntryPG,				//登録プログラム
				SearchUpdatePG,				//更新プログラム
				SearchUseFeeBasePtCd,		//運転計算タリフ
				SearchWmsStatus,			//倉庫出荷ステータス
				SearchWmsShipDateStr,		//倉庫出荷日時開始
				SearchWmsShipDateEnd,		//倉庫出荷日時終了
				SearchCourseGpCd,			//配車コースグループコード
				SearchCourseCD,				//配車コースコード
				SearchCourseCDEda,			//配車コースコード枝番
				SearchPitGrp,				//荷物払出ピットグループ
				SearchPit,					//荷物払出ピット
				
				SearchMsItemCd,				//商品CD
				SearchMsItemName,			//商品名
				
				SearchClItemCd,				//荷主商品CD
				
				SearchMsCategoryCd,			//カテゴリCD
				SearchMsCategoryName,		//カテゴリ名
				SearchMsTildFG,				//温度区分
				SearchMsTildName,			//温度区分名
				
				SearchMsLot,				//ロット指定
				SearchMsExpDateStr,			//賞味期限指定開始
				SearchMsExpDateEnd,			//賞味期限指定終了
				SearchMsPackingType,		//荷姿タイプ
				AllSearch);
		
		for(int i=0;i<OkuriMsRt.length;i++) {
			Object[] SetOb = new Object[1+OkuriMsRt[i].length];
			SetOb[0]	= false;
			for(int i01=0;i01<OkuriMsRt[i].length;i01++) {
				SetOb[1+i01]	= OkuriMsRt[i][i01];
			}
		}
		
		return OkuriMsRt;
	}
	
	private static void ViewSet(Object[][] OkuriMsRt,DefaultTableModel tableModel_msOkuriMs) {
		int RowCount = tableModel_msOkuriMs.getRowCount();
		for(int i=0;i<RowCount;i++) {
			tableModel_msOkuriMs.removeRow(0);
		}
		
		for(int i=0;i<OkuriMsRt.length;i++) {
			Object[] SetOb = new Object[1+OkuriMsRt[i].length];
			SetOb[0]	= false;
			for(int i01=0;i01<OkuriMsRt[i].length;i01++) {
				SetOb[1+i01]	= OkuriMsRt[i][i01];
			}
			tableModel_msOkuriMs.addRow(SetOb);
			
			
			String GetClCd					= (String)OkuriMsRt[i][T100_OkuriMsRt.ColClCd];					//荷主コード
			String GetInvoiceWhCd			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColInvoiceWhCd];			//倉庫コード
			String GetOkuriNo				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColOkuriNo];					//送り状番号
			String GetClDeliN				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColClDeliNo];				//荷主管理番号
			String GetPickupWhCd			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColPickupWhCd];				//集荷倉庫CD
			int GetPurposeFG				= (int)OkuriMsRt[i][T100_OkuriMsRt.ColPurposeFG];					//目的フラグ
			String GetPlanDate				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColPlanDate];				//出荷予定日
			String GetShipDate				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColShipDate];				//出荷実績日
			String GetSPPlanDate			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColSPPlanDate];				//着日指定
			String GetSPDate				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColSPDate];					//着日実績
			String GetSPTimeFG				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColSPTimeFG];				//時間指定区分
			String GetSPTimeStr				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColSPTimeStr];				//時間指定開始
			String GetSPTimeEnd				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColSPTimeEnd];				//時間指定終了
			float GetTotalWeight			= (float)OkuriMsRt[i][T100_OkuriMsRt.ColTotalWeight];				//荷物重量(kg)
			float GetTotalSize				= (float)OkuriMsRt[i][T100_OkuriMsRt.ColTotalSize];				//荷物サイズ
			int GetTotalQty					= (int)OkuriMsRt[i][T100_OkuriMsRt.ColTotalQty];					//個口数
			String GetDeliveryTypeCd01		= (String)OkuriMsRt[i][T100_OkuriMsRt.ColDeliveryTypeCd01];		//運送タイプ01
			String GetDeliTypeName			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColDeliTypeName];			//運送タイプ名01
			String GetDeliveryTypeCd02		= (String)OkuriMsRt[i][T100_OkuriMsRt.ColDeliveryTypeCd02];		//運送タイプ02
			String GetDeliTypeName02		= (String)OkuriMsRt[i][T100_OkuriMsRt.ColDeliTypeName02];			//運送タイプ名02
			String GetDeliveryTypeCd03		= (String)OkuriMsRt[i][T100_OkuriMsRt.ColDeliveryTypeCd03];		//運送タイプ03
			String GetDeliTypeName03		= (String)OkuriMsRt[i][T100_OkuriMsRt.ColDeliTypeName03];			//運送タイプ名03
			String GetDeliveryTypeCd04		= (String)OkuriMsRt[i][T100_OkuriMsRt.ColDeliveryTypeCd04];		//運送タイプ04
			String GetDeliTypeName04		= (String)OkuriMsRt[i][T100_OkuriMsRt.ColDeliTypeName04];			//運送タイプ名04
			String GetDeliveryTypeCd05		= (String)OkuriMsRt[i][T100_OkuriMsRt.ColDeliveryTypeCd05];		//運送タイプ05
			String GetDeliTypeName05		= (String)OkuriMsRt[i][T100_OkuriMsRt.ColDeliTypeName05];			//運送タイプ名05
	
			int GetCodFG					= (int)OkuriMsRt[i][T100_OkuriMsRt.ColCodFG];						//代引フラグ
			int GetCodPayTotal				= (int)OkuriMsRt[i][T100_OkuriMsRt.ColCodPayTotal];				//代引収受金額合計
			int GetCodPay					= (int)OkuriMsRt[i][T100_OkuriMsRt.ColCodPay];						//代引金額
			int GetCodConsumptionTax		= (int)OkuriMsRt[i][T100_OkuriMsRt.ColCodConsumptionTax];		//代引消費税
	
			int GetChildrenFG				= (int)OkuriMsRt[i][T100_OkuriMsRt.ColChildrenFG];				//子伝票区分
			String GetParentOkuriNo			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColParentOkuriNo];			//親伝票番号
			
			String GetNiokuriCd				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColNiokuriCd];				//荷送人コード
			String GetNiokuriDepartmentCd	= (String)OkuriMsRt[i][T100_OkuriMsRt.ColNiokuriDepartmentCd];		//荷送人部署CD
			String GetNiokuriName01			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColNiokuriName01];			//荷送人名01
			String GetNiokuriName02			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColNiokuriName02];			//荷送人名02
			String GetNiokuriName03			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColNiokuriName03];			//荷送人名03
			String GetNiokuriPost			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColNiokuriPost];			//荷送人郵便番号
			String GetNiokuriAdd01			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColNiokuriAdd01];			//荷送人住所01
			String GetNiokuriAdd02			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColNiokuriAdd02];			//荷送人住所02
			String GetNiokuriAdd03			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColNiokuriAdd03];			//荷送人住所03
			String GetNioKuriTel			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColNioKuriTel];				//荷送人TEL
			String GetNioKuriFax			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColNioKuriFax];				//荷送人FAX
			String GetNioKuriMail			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColNioKuriMail];			//荷送人MAIL
			String GetNiokuriMunicCd		= (String)OkuriMsRt[i][T100_OkuriMsRt.ColNiokuriMunicCd];			//荷送人市区町村CD
	
			String GetDeliCd				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColDeliCd];					//荷届先コード
			String GetClDeliCd				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColClDeliCd];				//荷主荷届先コード
			String GetDeliDepartmentCd		= (String)OkuriMsRt[i][T100_OkuriMsRt.ColDeliDepartmentCd];		//部署CD
			String GetDeliName01			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColDeliName01];				//荷届先名01
			String GetDeliName02			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColDeliName02];				//荷届先名02
			String GetDeliName03			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColDeliName03];				//荷届先名03
			String GetDeliPost				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColDeliPost];				//荷届先郵便番号
			String GetDeliAdd01				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColDeliAdd01];				//荷届先住所01
			String GetDeliAdd02				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColDeliAdd02];				//荷届先住所02
			String GetDeliAdd03				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColDeliAdd03];				//荷届先住所03
			String GetDeliTel				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColDeliTel];					//荷届先TEL
			String GetDeliFax				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColDeliFax];					//荷届先FAX
			String GetDeliMail				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColDeliMail];				//荷届先MAIL
			String GetDeliMunicCd			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColDeliMunicCd];			//荷届先市区町村CD
	
			String GetCom01					= (String)OkuriMsRt[i][T100_OkuriMsRt.ColCom01];					//コメント01
			String GetCom02					= (String)OkuriMsRt[i][T100_OkuriMsRt.ColCom02];					//コメント02
			String GetCom03					= (String)OkuriMsRt[i][T100_OkuriMsRt.ColCom03];					//コメント03
			String GetCom04					= (String)OkuriMsRt[i][T100_OkuriMsRt.ColCom04];					//コメント04
			String GetCom05					= (String)OkuriMsRt[i][T100_OkuriMsRt.ColCom05];					//コメント05
	
			int GetStatus					= (int)OkuriMsRt[i][T100_OkuriMsRt.ColStatus];						//運送状況
			int GetTaxFg					= (int)OkuriMsRt[i][T100_OkuriMsRt.ColTaxFg];						//税区分
			int GetTaxRate					= (int)OkuriMsRt[i][T100_OkuriMsRt.ColTaxRate];					//税率
			int GetDeliFee					= (int)OkuriMsRt[i][T100_OkuriMsRt.ColDeliFee];					//運賃
			int GetAddDeliFee01				= (int)OkuriMsRt[i][T100_OkuriMsRt.ColAddDeliFee01];				//付帯費用1
			int GetAddDeliFee02				= (int)OkuriMsRt[i][T100_OkuriMsRt.ColAddDeliFee02];				//付帯費用2
			int GetAddDeliFee03				= (int)OkuriMsRt[i][T100_OkuriMsRt.ColAddDeliFee03];				//付帯費用3
			int GetHaighWayFee01			= (int)OkuriMsRt[i][T100_OkuriMsRt.ColHaighWayFee01];				//高速代等実費精算分1（内税）
			int GetHaighWayFee02			= (int)OkuriMsRt[i][T100_OkuriMsRt.ColHaighWayFee02];				//高速代等実費精算分2（内税）
			int GetConsumptionTax			= (int)OkuriMsRt[i][T100_OkuriMsRt.ColConsumptionTax];			//消費税
			int GetWithOutTaxTotal			= (int)OkuriMsRt[i][T100_OkuriMsRt.ColWithOutTaxTotal];			//税別合計金額
			int GetTotalFee					= (int)OkuriMsRt[i][T100_OkuriMsRt.ColTotalFee];					//税込請求額合計
			int GetFeeFixFG					= (int)OkuriMsRt[i][T100_OkuriMsRt.ColFeeFixFG];					//金額確定フラグ
			String GetFeeFixDate			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColFeeFixDate];				//金額確定日時
			int GetReceiptStampFG			= (int)OkuriMsRt[i][T100_OkuriMsRt.ColReceiptStampFG];			//受領印チェック
			String GetReceiptStampDate		= (String)OkuriMsRt[i][T100_OkuriMsRt.ColReceiptStampDate];		//受領印日時
			int GetInvoiceStatus			= (int)OkuriMsRt[i][T100_OkuriMsRt.ColInvoiceStatus];				//請求ステータス
			String GetEntryDate				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColEntryDate];				//登録日
			String GetUpdateDate			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColUpdateDate];				//更新日
			String GetEntryUser				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColEntryUser];				//登録者
			String GetUpdateUser			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColUpdateUser];				//更新者
			String GetEntryPG				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColEntryPG];					//登録プログラム
			String GetUpdatePG				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColUpdatePG];				//更新プログラム
	
			String GetUseFeeBasePtCd		= (String)OkuriMsRt[i][T100_OkuriMsRt.ColUseFeeBasePtCd];			//適用運賃タリフCD
			int GetWmsStatus				= (int)OkuriMsRt[i][T100_OkuriMsRt.ColWmsStatus];					//在庫管理ステータス
			String GetWmsShipDate			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColWmsShipDate];			//倉庫出荷日
			String GetCourseGpCd			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColCourseGpCd];				//コースグループコード
			String GetCourseCD				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColCourseCD];				//一次配車コースコード
			int GetCourseCDEda				= (int)OkuriMsRt[i][T100_OkuriMsRt.ColCourseCDEda];				//一次配車コースコード枝番
			String GetPitGrp				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColPitGrp];					//一次配車払出ピットグループ
			String GetPit01					= (String)OkuriMsRt[i][T100_OkuriMsRt.ColPit01];					//一次配車払出ピット01
			String GetPit02					= (String)OkuriMsRt[i][T100_OkuriMsRt.ColPit02];					//一次配車払出ピット02
			String GetPit03					= (String)OkuriMsRt[i][T100_OkuriMsRt.ColPit03];					//一次配車払出ピット03
			String GetPit04					= (String)OkuriMsRt[i][T100_OkuriMsRt.ColPit04];					//一次配車払出ピット04
			String GetPit05					= (String)OkuriMsRt[i][T100_OkuriMsRt.ColPit05];					//一次配車払出ピット05
	
			String GetCLName01				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColCLName01];				//荷主名
			String GetClGpCD				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColClGpCD];					//荷主グループCD
			String GetCLGpName01			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColCLGpName01];				//荷主グループ標記名
			
			
			String GetMsClCd				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColMsClCd];					//明細荷主コード
			String GetMsInvoiceWhCd			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColMsInvoiceWhCd];			//明細倉庫コード
			String GetMsOkuriNo				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColMsOkuriNo];				//明細送り状番号
			int GetMsNo						= (int)OkuriMsRt[i][T100_OkuriMsRt.ColMsNo];						//明細番号
			String GetMsDeliNo				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColMsDeliNo];				//明細出荷番号
			int GetMsDelliMsNo				= (int)OkuriMsRt[i][T100_OkuriMsRt.ColMsDelliMsNo];				//明細出荷番号明細番号
			String GetMsClOrderNo			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColMsClOrderNo];			//明細荷主管理番号
			String GetMsClGpCd				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColMsClGpCd];				//明細荷主グループコード
			String GetMsItemCd				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColMsItemCd];				//明細商品コード
			String GetMsItemName01			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColMsItemName01];			//明細商品表記名
			String GetMsItemName02			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColMsItemName02];			//明細商品正式名
			String GetMsItemName03			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColMsItemName03];			//明細商品略名
			float GetMsUnitWeight			= (float)OkuriMsRt[i][T100_OkuriMsRt.ColMsUnitWeight];			//明細単位重量
			float GetMsUnitSize				= (float)OkuriMsRt[i][T100_OkuriMsRt.ColMsUnitSize];				//明細単位サイズ
			int GetMsQty					= (int)OkuriMsRt[i][T100_OkuriMsRt.ColMsQty];						//明細個数
			int GetMsPackingQty				= (int)OkuriMsRt[i][T100_OkuriMsRt.ColMsPackingQty];				//明細荷姿数量
			String GetMsUnitName			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColMsUnitName];				//明細明細単位
			float GetMsSubTotalWeight		= (float)OkuriMsRt[i][T100_OkuriMsRt.ColMsSubTotalWeight];		//明細明細重量
			float GetMsSubTotalSize			= (float)OkuriMsRt[i][T100_OkuriMsRt.ColMsSubTotalSize];			//明細明細サイズ
			float GetMsUnitPrice			= (float)OkuriMsRt[i][T100_OkuriMsRt.ColMsUnitPrice];				//明細単価
			float GetMsSubTotalPrice		= (float)OkuriMsRt[i][T100_OkuriMsRt.ColMsSubTotalPrice];		//明細金額
			String GetMsCategoryCd			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColMsCategoryCd];			//明細商品分類
			String GetMsCategoryName		= (String)OkuriMsRt[i][T100_OkuriMsRt.ColMsCategoryName];			//明細商品分類名
			String GetMsTildFG				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColMsTildFG];				//明細温度区分
			String GetMsTildName			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColMsTildName];				//明細温度区分名
			String GetMsCom01				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColMsCom01];					//明細コメント01
			String GetMsCom02				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColMsCom02];					//明細コメント02
			String GetMsCom03				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColMsCom03];					//明細コメント03
			String GetMsCom04				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColMsCom04];					//明細コメント04
			String GetMsCom05				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColMsCom05];					//明細コメント05
			String GetMsEntryDate			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColMsEntryDate];			//明細登録日
			String GetMsUpdateDate			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColMsUpdateDate];			//明細更新日
			String GetMsEntryUser			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColMsEntryUser];			//明細登録者
			String GetMsUpdateUser			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColMsUpdateUser];			//明細更新者
			String GetMsLot					= (String)OkuriMsRt[i][T100_OkuriMsRt.ColMsLot];					//明細ロット指定
			String GetMsExpDate				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColMsExpDate];				//明細賞味期限指定
			int GetMsPackingType			= (int)OkuriMsRt[i][T100_OkuriMsRt.ColMsPackingType];				//明細荷姿タイプ
			String GetMsClItemCd			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColMsClItemCd];				//明細荷主商品CD
			String GetMsItemMDNo			= (String)OkuriMsRt[i][T100_OkuriMsRt.ColMsItemMDNo];				//明細型番
			String GetMsJanCd				= (String)OkuriMsRt[i][T100_OkuriMsRt.ColMsJanCd];					//明細荷姿JanCd
		}
	}
}