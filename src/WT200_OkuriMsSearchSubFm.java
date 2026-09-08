import java.awt.Font;
import java.awt.event.ActionEvent;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
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
		Object[][] ClMstRt= ClMstRt(ClCd);
		String ClGp = A00000_Main.ClGp;
		String ClWh = A00000_Main.ClWh;
		if(1==ClMstRt.length) {
			ClGp = (String)ClMstRt[0][M100_ClMstRt.ColClGpCD];		//荷主グループCD
			ClWh = (String)ClMstRt[0][M100_ClMstRt.ColWHCD];		//担当倉庫
		}
		
		final JFrame OkuriMs_fm 	= B100_FrameParts.FrameCreate(x,y,1300,750,"Corgi00出荷明細検索　WT200_OkuriMsSearchSubFm",BackGroundColor);
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
		JScrollPane scpnOkuriMs = B100_FrameParts.JScrollPaneSet(10,350,1260,150,tbOkuriMs);
		OkuriMs_fm.add(scpnOkuriMs);
		
		//ヘッダ表示パネル
		JPanel PN_HD00 = B100_FrameParts.JPanelSet(10,40,1260,300,"White");
		JPanel PN_HD01 = B100_FrameParts.JPanelSet(10,40,1260,300,"White");
		JPanel PN_HD02 = B100_FrameParts.JPanelSet(10,40,1260,300,"White");
		String[] HdTabName 	= {"Main","Sub01","Sub02"};
		JPanel[] HdSetPN		= {PN_HD00,PN_HD01,PN_HD02};
		JTabbedPane HdTabPaneSet	= B100_FrameParts.TabPaneSet(10,40,1260,300,HdTabName,HdSetPN,"");
		
		//明細表示パネル
		JPanel PN_MS00 = B100_FrameParts.JPanelSet(10,500,1260,150,"White");
		JPanel PN_MS01 = B100_FrameParts.JPanelSet(10,500,1260,150,"White");
		String[] MsTabName 	= {"Main","Sub01"};
		JPanel[] MsSetPN		= {PN_MS00,PN_MS01};
		JTabbedPane MsTabPaneSet	= B100_FrameParts.TabPaneSet(10,500,1260,150,MsTabName,MsSetPN,"");
		
		//検索条件
		JLabel LB_ClCd				= B100_FrameParts.JLabelSet(		360, 20, 70,20,(String)DefinitionRt[T100_OkuriMsRt.ColSearchClCd][5]		+":"	,11,1);
		JLabel LB_SearchOkuriNo		= B100_FrameParts.JLabelSet(		  0, 20,100,20,(String)DefinitionRt[T100_OkuriMsRt.ColSearchOkuriNo][5]	+":"	,11,1);
		
		final JComboBox TB_ClCd		= B100_FrameParts.JComboBoxSet(				430, 20,250,20,B100_DefaultVariable.ClList[0],11);	//荷主コード
		final JTextField  TB_SearchOkuriNo		= B100_FrameParts.JTextFieldSet(	100, 20,100,20,"",12,0);							//送り状番号
		
		JLabel LB2_SearchOkuriNo		= B100_FrameParts.JLabelSet(	200, 20,60,20,B100_DefaultVariable.SearchExact	,11,0);
		
		OkuriMs_fm.add(LB_ClCd);
		OkuriMs_fm.add(LB_SearchOkuriNo);
		
		OkuriMs_fm.add(TB_ClCd);
		OkuriMs_fm.add(TB_SearchOkuriNo);
		
		OkuriMs_fm.add(LB2_SearchOkuriNo);
		
		TB_ClCd.setSelectedIndex(	B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.ClList[1]		,ClCd ,true) );		//荷主コード選択状態にする
		TB_ClCd.setEnabled(false);
		
		JButton OkuriMsSearchKickBtn			= B100_FrameParts.BtnSet(			260,20, 90,20,"検索",11);
		if(SearchMode) {
			OkuriMs_fm.add(OkuriMsSearchKickBtn);
			TB_SearchOkuriNo.setEditable(true);
		}else {
			TB_SearchOkuriNo.setEditable(false);
		}
		
		//検索結果ヘッダ情報
		JLabel LB_InvoiceWhCd			= B100_FrameParts.JLabelSet(		680, 20, 70,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColInvoiceWhCd][3]				+":"	,11,1);		//倉庫CD
		final JComboBox   			TB_InvoiceWhCd			= B100_FrameParts.JComboBoxSet(	750, 20,250,20,B100_DefaultVariable.WhList[0],11);									//倉庫CD
		OkuriMs_fm.add(LB_InvoiceWhCd);
		OkuriMs_fm.add(TB_InvoiceWhCd);
		
		JLabel LB_ClDeliNo				= B100_FrameParts.JLabelSet(		  0,  0,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColClDeliNo][3]					+":"	,11,1);		//荷主管理番号
		JLabel LB_PurposeFG				= B100_FrameParts.JLabelSet(		  0, 25,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColPurposeFG][3]				+":"	,11,1);		//目的フラグ
		JLabel LB_PlanDate				= B100_FrameParts.JLabelSet(		  0, 50,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColPlanDate][3]					+":"	,11,1);		//出荷予定日
		JLabel LB_ShipDate				= B100_FrameParts.JLabelSet(		  0, 75,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColShipDate][3]					+":"	,11,1);		//出荷実績日
		JLabel LB_SPPlanDate			= B100_FrameParts.JLabelSet(		  0,100,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColSPPlanDate][3]				+":"	,11,1);		//着日指定
		JLabel LB_SPDate				= B100_FrameParts.JLabelSet(		  0,125,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColSPDate][3]					+":"	,11,1);		//着日実績
		JLabel LB_SPTimeFG				= B100_FrameParts.JLabelSet(		  0,150,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColSPTimeFG][3]					+":"	,11,1);		//時間指定区分
		JLabel LB_SPTimeStr				= B100_FrameParts.JLabelSet(		  0,175,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColSPTimeStr][3]				+":"	,11,1);		//時間指定開始
		JLabel LB_SPTimeEnd				= B100_FrameParts.JLabelSet(		  0,200,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColSPTimeEnd][3]				+":"	,11,1);		//時間指定終了
		JLabel LB_NiokuriMunicCd		= B100_FrameParts.JLabelSet(		  0,225,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColNiokuriMunicCd][3]			+":"	,11,1);		//荷送人市区町村CD
		JLabel LB_DeliMunicCd			= B100_FrameParts.JLabelSet(		  0,250,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColDeliMunicCd][3]				+":"	,11,1);		//荷届先市区町村CD
		
		final JTextField  			TB_ClDeliNo				= B100_FrameParts.JTextFieldSet(					100,  0,100,20,"",11,0);											//荷主管理番号
		final JComboBox   			TB_PurposeFG			= B100_FrameParts.JComboBoxSet(					100, 25,100,20,B100_DefaultVariable.PurposeList[0],11);			//目的フラグ
		final JFormattedTextField  	TB_PlanDate				= B100_FrameParts.JFormattedTextFieldSet(		100, 50,100,20,"",11,0,"YYYY/MM/DD");								//出荷予定日
		final JFormattedTextField  	TB_ShipDate				= B100_FrameParts.JFormattedTextFieldSet(		100, 75,100,20,"",11,0,"YYYY/MM/DD");								//出荷実績日
		final JFormattedTextField  	TB_SPPlanDate			= B100_FrameParts.JFormattedTextFieldSet(		100,100,100,20,"",11,0,"YYYY/MM/DD");								//着日指定
		final JFormattedTextField  	TB_SPDate				= B100_FrameParts.JFormattedTextFieldSet(		100,125,100,20,"",11,0,"YYYY/MM/DD");								//着日実績
		final JTextField  			TB_SPTimeFG				= B100_FrameParts.JTextFieldSet(					100,150,100,20,"",11,0);											//時間指定区分
		final JFormattedTextField  	TB_SPTimeStr			= B100_FrameParts.JFormattedTextFieldSet(		100,175,100,20,"",11,0,"HH:MM");									//時間指定開始
		final JFormattedTextField  	TB_SPTimeEnd			= B100_FrameParts.JFormattedTextFieldSet(		100,200,100,20,"",11,0,"HH:MM");									//時間指定終了
		final JTextField  			TB_NiokuriMunicCd		= B100_FrameParts.JTextFieldSet(					100,225,100,20,"",11,0);											//荷送人市区町村CD
		final JTextField  			TB_DeliMunicCd			= B100_FrameParts.JTextFieldSet(					100,250,100,20,"",11,0);											//荷届先市区町村CD
		
		JLabel LB_PickupWhCd			= B100_FrameParts.JLabelSet(		200,  0,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColPickupWhCd][3]				+":"	,11,1);		//集荷倉庫CD
		JLabel LB_NiokuriCd				= B100_FrameParts.JLabelSet(		200, 25,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColNiokuriCd][3]				+":"	,11,1);		//荷送人コード
		JLabel LB_NiokuriDepartmentCd	= B100_FrameParts.JLabelSet(		400, 25,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColNiokuriDepartmentCd][3]		+":"	,11,1);		//荷送人部署CD
		JLabel LB_NiokuriName01			= B100_FrameParts.JLabelSet(		200, 50,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColNiokuriName01][3]			+":"	,11,1);		//荷送人名01
		JLabel LB_NiokuriName02			= B100_FrameParts.JLabelSet(		200, 75,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColNiokuriName02][3]			+":"	,11,1);		//荷送人名02
		JLabel LB_NiokuriName03			= B100_FrameParts.JLabelSet(		200,100,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColNiokuriName03][3]			+":"	,11,1);		//荷送人名03
		JLabel LB_NiokuriPost			= B100_FrameParts.JLabelSet(		200,125,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColNiokuriPost][3]				+":"	,11,1);		//荷送人郵便番号
		JLabel LB_NiokuriAdd01			= B100_FrameParts.JLabelSet(		200,150,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColNiokuriAdd01][3]				+":"	,11,1);		//荷送人住所01
		JLabel LB_NiokuriAdd02			= B100_FrameParts.JLabelSet(		200,175,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColNiokuriAdd02][3]				+":"	,11,1);		//荷送人住所02
		JLabel LB_NiokuriAdd03			= B100_FrameParts.JLabelSet(		200,200,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColNiokuriAdd03][3]				+":"	,11,1);		//荷送人住所03
		JLabel LB_NioKuriTel			= B100_FrameParts.JLabelSet(		200,225,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColNioKuriTel][3]				+":"	,11,1);		//荷送人TEL
		JLabel LB_NioKuriFax			= B100_FrameParts.JLabelSet(		400,225,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColNioKuriFax][3]				+":"	,11,1);		//荷送人FAX
		JLabel LB_NioKuriMail			= B100_FrameParts.JLabelSet(		200,250,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColNioKuriMail][3]				+":"	,11,1);		//荷送人MAIL
		
		
		final JComboBox   TB_PickupWhCd				= B100_FrameParts.JComboBoxSet(	300,  0,250,20,B100_DefaultVariable.WhList[0],11);							//集荷倉庫CD
		final JTextField  TB_NiokuriCd				= B100_FrameParts.JTextFieldSet(	300, 25,100,20,"",11,0);													//荷送人コード
		final JTextField  TB_NiokuriDepartmentCd	= B100_FrameParts.JTextFieldSet(	500, 25,100,20,"",11,0);													//荷送人部署CD
		final JTextField  TB_NiokuriName01			= B100_FrameParts.JTextFieldSet(	300, 50,300,20,"",11,0);													//荷送人名01
		final JTextField  TB_NiokuriName02			= B100_FrameParts.JTextFieldSet(	300, 75,300,20,"",11,0);													//荷送人名02
		final JTextField  TB_NiokuriName03			= B100_FrameParts.JTextFieldSet(	300,100,300,20,"",11,0);													//荷送人名03
		final JTextField  TB_NiokuriPost			= B100_FrameParts.JTextFieldSet(	300,125,100,20,"",11,0);													//荷送人郵便番号
		final JTextField  TB_NiokuriAdd01			= B100_FrameParts.JTextFieldSet(	300,150,300,20,"",11,0);													//荷送人住所01
		final JTextField  TB_NiokuriAdd02			= B100_FrameParts.JTextFieldSet(	300,175,300,20,"",11,0);													//荷送人住所02
		final JTextField  TB_NiokuriAdd03			= B100_FrameParts.JTextFieldSet(	300,200,300,20,"",11,0);													//荷送人住所03
		final JTextField  TB_NioKuriTel				= B100_FrameParts.JTextFieldSet(	300,225,100,20,"",11,0);													//荷送人TEL
		final JTextField  TB_NioKuriFax				= B100_FrameParts.JTextFieldSet(	500,225,100,20,"",11,0);													//荷送人FAX
		final JTextField  TB_NioKuriMail			= B100_FrameParts.JTextFieldSet(	300,250,300,20,"",11,0);													//荷送人MAIL
		
		JLabel LB_ClDeliCd				= B100_FrameParts.JLabelSet(	    600,  0,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColClDeliCd][3]					+":"	,11,1);		//荷主荷届先コード
		JLabel LB_DeliCd				= B100_FrameParts.JLabelSet(		600, 25,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColDeliCd][3]					+":"	,11,1);		//荷届先コード
		JLabel LB_DeliDepartmentCd		= B100_FrameParts.JLabelSet(	    800, 25,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColDeliDepartmentCd][3]		+":"	,11,1);		//部署CD
		JLabel LB_DeliName01			= B100_FrameParts.JLabelSet(		600, 50,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColDeliName01][3]				+":"	,11,1);		//荷届先名01
		JLabel LB_DeliName02			= B100_FrameParts.JLabelSet(		600, 75,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColDeliName02][3]				+":"	,11,1);		//荷届先名02
		JLabel LB_DeliName03			= B100_FrameParts.JLabelSet(		600,100,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColDeliName03][3]				+":"	,11,1);		//荷届先名03
		JLabel LB_DeliPost				= B100_FrameParts.JLabelSet(		600,125,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColDeliPost][3]					+":"	,11,1);		//荷届先郵便番号
		JLabel LB_DeliAdd01				= B100_FrameParts.JLabelSet(		600,150,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColDeliAdd01][3]				+":"	,11,1);		//荷届先住所01
		JLabel LB_DeliAdd02				= B100_FrameParts.JLabelSet(		600,175,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColDeliAdd02][3]				+":"	,11,1);		//荷届先住所02
		JLabel LB_DeliAdd03				= B100_FrameParts.JLabelSet(		600,200,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColDeliAdd03][3]				+":"	,11,1);		//荷届先住所03
		JLabel LB_DeliTel				= B100_FrameParts.JLabelSet(		600,225,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColDeliTel][3]					+":"	,11,1);		//荷届先TEL
		JLabel LB_DeliFax				= B100_FrameParts.JLabelSet(	    800,225,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColDeliFax][3]					+":"	,11,1);		//荷届先FAX
		JLabel LB_DeliMail				= B100_FrameParts.JLabelSet(		600,250,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColDeliMail][3]					+":"	,11,1);		//荷届先MAIL
		
		final JTextField  TB_ClDeliCd				= B100_FrameParts.JTextFieldSet(	700,  0,100,20,"",11,0);													//荷主荷届先コード
		final JTextField  TB_DeliCd					= B100_FrameParts.JTextFieldSet(	700, 25,100,20,"",11,0);													//荷届先コード
		final JTextField  TB_DeliDepartmentCd		= B100_FrameParts.JTextFieldSet(	900, 25,100,20,"",11,0);													//部署CD
		final JTextField  TB_DeliName01				= B100_FrameParts.JTextFieldSet(	700, 50,300,20,"",11,0);													//荷届先名01
		final JTextField  TB_DeliName02				= B100_FrameParts.JTextFieldSet(	700, 75,300,20,"",11,0);													//荷届先名02
		final JTextField  TB_DeliName03				= B100_FrameParts.JTextFieldSet(	700,100,300,20,"",11,0);													//荷届先名03
		final JTextField  TB_DeliPost				= B100_FrameParts.JTextFieldSet(	700,125,100,20,"",11,0);													//荷届先郵便番号
		final JTextField  TB_DeliAdd01				= B100_FrameParts.JTextFieldSet(	700,150,300,20,"",11,0);													//荷届先住所01
		final JTextField  TB_DeliAdd02				= B100_FrameParts.JTextFieldSet(	700,175,300,20,"",11,0);													//荷届先住所02
		final JTextField  TB_DeliAdd03				= B100_FrameParts.JTextFieldSet(	700,200,300,20,"",11,0);													//荷届先住所03
		final JTextField  TB_DeliTel				= B100_FrameParts.JTextFieldSet(	700,225,100,20,"",11,0);													//荷届先TEL
		final JTextField  TB_DeliFax				= B100_FrameParts.JTextFieldSet(	900,225,100,20,"",11,0);													//荷届先FAX
		final JTextField  TB_DeliMail				= B100_FrameParts.JTextFieldSet(	700,250,300,20,"",11,0);													//荷届先MAIL
		
		JLabel LB_TotalWeight			= B100_FrameParts.JLabelSet(	   1000, 25,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColTotalWeight][3]				+":"	,11,1);		//荷物重量(kg)
		JLabel LB_TotalSize				= B100_FrameParts.JLabelSet(	   1000, 50,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColTotalSize][3]				+":"	,11,1);		//荷物サイズ
		JLabel LB_TotalQty				= B100_FrameParts.JLabelSet(	   1000, 75,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColTotalQty][3]					+":"	,11,1);		//個口数
		JLabel LB_ChildrenFG			= B100_FrameParts.JLabelSet(	   1000,100,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColChildrenFG][3]				+":"	,11,1);		//子伝票区分
		JLabel LB_ParentOkuriNo			= B100_FrameParts.JLabelSet(	   1000,125,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColParentOkuriNo][3]			+":"	,11,1);		//親伝票番号
		JLabel LB_Status				= B100_FrameParts.JLabelSet(	   1000,150,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColStatus][3]					+":"	,11,1);		//運送状況
		JLabel LB_WmsStatus				= B100_FrameParts.JLabelSet(	   1000,175,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColWmsStatus][3]				+":"	,11,1);		//在庫管理ステータス
		JLabel LB_WmsShipDate			= B100_FrameParts.JLabelSet(	   1000,200,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColWmsShipDate][3]				+":"	,11,1);		//倉庫出荷日
		JLabel LB_CodFG					= B100_FrameParts.JLabelSet(	   1000,225,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColCodFG][3]						+":"	,11,1);		//代引フラグ
		JLabel LB_CodPayTotal			= B100_FrameParts.JLabelSet(	   1000,250,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColCodPayTotal][3]				+":"	,11,1);		//代引収受金額合計
		
		final JFormattedTextField  	TB_TotalWeight			= B100_FrameParts.JFormattedTextFieldSet(	   1100, 25,100,20,"",11,1,"#,###.##");											//荷物重量(kg)
		final JFormattedTextField  	TB_TotalSize			= B100_FrameParts.JFormattedTextFieldSet(	   1100, 50,100,20,"",11,1,"#,###.##");											//荷物サイズ
		final JFormattedTextField  	TB_TotalQty				= B100_FrameParts.JFormattedTextFieldSet(	   1100, 75,100,20,"",11,1,"#,###");											//個口数
		final JComboBox  			TB_ChildrenFG			= B100_FrameParts.JComboBoxSet(				   1100,100,100,20,B100_DefaultVariable.ChildrenFGList[0],11);				//子伝票区分
		final JTextField  			TB_ParentOkuriNo		= B100_FrameParts.JTextFieldSet(				   1100,125,100,20,"",11,0);													//親伝票番号
		final JComboBox  			TB_Status				= B100_FrameParts.JComboBoxSet(				   1100,150,100,20,B100_DefaultVariable.StatusList[0],11);						//運送状況
		final JComboBox  			TB_WmsStatus			= B100_FrameParts.JComboBoxSet(				   1100,175,100,20,B100_DefaultVariable.WmsStatusList[0],11);					//在庫管理ステータス
		final JFormattedTextField  	TB_WmsShipDate			= B100_FrameParts.JFormattedTextFieldSet(	   1100,200,100,20,"",11,0,"YYYY/MM/DD");										//倉庫出荷日
		final JComboBox  			TB_CodFG				= B100_FrameParts.JComboBoxSet(				   1100,225,100,20,B100_DefaultVariable.CODList[0]	,11);						//代引フラグ
		final JFormattedTextField  	TB_CodPayTotal			= B100_FrameParts.JFormattedTextFieldSet(	   1100,250,100,20,"",11,0,"#,###");											//代引収受金額合計
		
		
		PN_HD00.add(LB_ClDeliNo);
		PN_HD00.add(LB_PurposeFG);
		PN_HD00.add(LB_PlanDate);
		PN_HD00.add(LB_ShipDate);
		PN_HD00.add(LB_SPPlanDate);
		PN_HD00.add(LB_SPDate);
		PN_HD00.add(LB_SPTimeFG);
		PN_HD00.add(LB_SPTimeStr);
		PN_HD00.add(LB_SPTimeEnd);
		PN_HD00.add(LB_NiokuriMunicCd);
		PN_HD00.add(LB_DeliMunicCd);
		
		PN_HD00.add(LB_PickupWhCd);
		PN_HD00.add(LB_NiokuriCd);
		PN_HD00.add(LB_NiokuriDepartmentCd);
		PN_HD00.add(LB_NiokuriName01);
		PN_HD00.add(LB_NiokuriName02);
		PN_HD00.add(LB_NiokuriName03);
		PN_HD00.add(LB_NiokuriPost);
		PN_HD00.add(LB_NiokuriAdd01);
		PN_HD00.add(LB_NiokuriAdd02);
		PN_HD00.add(LB_NiokuriAdd03);
		PN_HD00.add(LB_NioKuriTel);
		PN_HD00.add(LB_NioKuriFax);
		PN_HD00.add(LB_NioKuriMail);
		
		PN_HD00.add(LB_ClDeliCd);
		PN_HD00.add(LB_DeliCd);
		PN_HD00.add(LB_DeliDepartmentCd);
		PN_HD00.add(LB_DeliName01);
		PN_HD00.add(LB_DeliName02);
		PN_HD00.add(LB_DeliName03);
		PN_HD00.add(LB_DeliPost);
		PN_HD00.add(LB_DeliAdd01);
		PN_HD00.add(LB_DeliAdd02);
		PN_HD00.add(LB_DeliAdd03);
		PN_HD00.add(LB_DeliTel);
		PN_HD00.add(LB_DeliFax);
		PN_HD00.add(LB_DeliMail);
		
		PN_HD00.add(LB_TotalWeight);
		PN_HD00.add(LB_TotalSize);
		PN_HD00.add(LB_TotalQty);
		PN_HD00.add(LB_ChildrenFG);
		PN_HD00.add(LB_ParentOkuriNo);
		PN_HD00.add(LB_Status);
		PN_HD00.add(LB_WmsStatus);
		PN_HD00.add(LB_WmsShipDate);
		PN_HD00.add(LB_CodFG);
		PN_HD00.add(LB_CodPayTotal);
		
		PN_HD00.add(TB_ClDeliNo);
		PN_HD00.add(TB_PurposeFG);
		PN_HD00.add(TB_PlanDate);
		PN_HD00.add(TB_ShipDate);
		PN_HD00.add(TB_SPPlanDate);
		PN_HD00.add(TB_SPDate);
		PN_HD00.add(TB_SPTimeFG);
		PN_HD00.add(TB_SPTimeStr);
		PN_HD00.add(TB_SPTimeEnd);
		PN_HD00.add(TB_NiokuriMunicCd);
		PN_HD00.add(TB_DeliMunicCd);
		
		PN_HD00.add(TB_PickupWhCd);
		PN_HD00.add(TB_NiokuriCd);
		PN_HD00.add(TB_NiokuriDepartmentCd);
		PN_HD00.add(TB_NiokuriName01);
		PN_HD00.add(TB_NiokuriName02);
		PN_HD00.add(TB_NiokuriName03);
		PN_HD00.add(TB_NiokuriPost);
		PN_HD00.add(TB_NiokuriAdd01);
		PN_HD00.add(TB_NiokuriAdd02);
		PN_HD00.add(TB_NiokuriAdd03);
		PN_HD00.add(TB_NioKuriTel);
		PN_HD00.add(TB_NioKuriFax);
		PN_HD00.add(TB_NioKuriMail);
		
		PN_HD00.add(TB_ClDeliCd);
		PN_HD00.add(TB_DeliCd);
		PN_HD00.add(TB_DeliDepartmentCd);
		PN_HD00.add(TB_DeliName01);
		PN_HD00.add(TB_DeliName02);
		PN_HD00.add(TB_DeliName03);
		PN_HD00.add(TB_DeliPost);
		PN_HD00.add(TB_DeliAdd01);
		PN_HD00.add(TB_DeliAdd02);
		PN_HD00.add(TB_DeliAdd03);
		PN_HD00.add(TB_DeliTel);
		PN_HD00.add(TB_DeliFax);
		PN_HD00.add(TB_DeliMail);
		
		PN_HD00.add(TB_TotalWeight);
		PN_HD00.add(TB_TotalSize);
		PN_HD00.add(TB_TotalQty);
		PN_HD00.add(TB_ChildrenFG);
		PN_HD00.add(TB_ParentOkuriNo);
		PN_HD00.add(TB_Status);
		PN_HD00.add(TB_WmsStatus);
		PN_HD00.add(TB_WmsShipDate);
		PN_HD00.add(TB_CodFG);
		PN_HD00.add(TB_CodPayTotal);
		
		/****************************/
		
		JLabel LB_DeliveryTypeCd01		= B100_FrameParts.JLabelSet(		  0, 25,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColDeliveryTypeCd01][3]		+":"	,11,1);		//運送タイプ01
		JLabel LB_DeliveryTypeCd02		= B100_FrameParts.JLabelSet(		  0, 50,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColDeliveryTypeCd02][3]		+":"	,11,1);		//運送タイプ02
		JLabel LB_DeliveryTypeCd03		= B100_FrameParts.JLabelSet(		  0, 75,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColDeliveryTypeCd03][3]		+":"	,11,1);		//運送タイプ03
		JLabel LB_DeliveryTypeCd04		= B100_FrameParts.JLabelSet(		  0,100,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColDeliveryTypeCd04][3]		+":"	,11,1);		//運送タイプ04
		JLabel LB_DeliveryTypeCd05		= B100_FrameParts.JLabelSet(		  0,125,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColDeliveryTypeCd05][3]		+":"	,11,1);		//運送タイプ05
		
		JLabel LB_CodPay				= B100_FrameParts.JLabelSet(		  0,175,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColCodPay][3]					+":"	,11,1);		//代引金額
		JLabel LB_CodConsumptionTax		= B100_FrameParts.JLabelSet(		  0,200,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColCodConsumptionTax][3]		+":"	,11,1);		//代引消費税
		JLabel LB_ReceiptStampFG		= B100_FrameParts.JLabelSet(		  0,225,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColReceiptStampFG][3]			+":"	,11,1);		//受領印チェック
		JLabel LB_ReceiptStampDate		= B100_FrameParts.JLabelSet(		  0,250,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColReceiptStampDate][3]		+":"	,11,1);		//受領印日時
		
		JLabel LB_Com01					= B100_FrameParts.JLabelSet(		300, 25,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColCom01][3]						+":"	,11,1);		//コメント01
		JLabel LB_Com02					= B100_FrameParts.JLabelSet(		300, 50,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColCom02][3]						+":"	,11,1);		//コメント02
		JLabel LB_Com03					= B100_FrameParts.JLabelSet(		300, 75,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColCom03][3]						+":"	,11,1);		//コメント03
		JLabel LB_Com04					= B100_FrameParts.JLabelSet(		300,100,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColCom04][3]						+":"	,11,1);		//コメント04
		JLabel LB_Com05					= B100_FrameParts.JLabelSet(		300,125,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColCom05][3]						+":"	,11,1);		//コメント05
		
		JLabel LB_EntryDate				= B100_FrameParts.JLabelSet(		700, 25,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColEntryDate][3]				+":"	,11,1);		//登録日
		JLabel LB_UpdateDate			= B100_FrameParts.JLabelSet(		700, 50,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColUpdateDate][3]				+":"	,11,1);		//更新日
		JLabel LB_EntryUser				= B100_FrameParts.JLabelSet(		700, 75,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColEntryUser][3]				+":"	,11,1);		//登録者
		JLabel LB_UpdateUser			= B100_FrameParts.JLabelSet(		700,100,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColUpdateUser][3]				+":"	,11,1);		//更新者
		
		JLabel LB_EntryPG				= B100_FrameParts.JLabelSet(		950, 25,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColEntryPG][3]					+":"	,11,1);		//登録プログラム
		JLabel LB_UpdatePG				= B100_FrameParts.JLabelSet(		950, 50,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColUpdatePG][3]					+":"	,11,1);		//更新プログラム
		
		JLabel LB_InvoiceStatus			= B100_FrameParts.JLabelSet(		200,175,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColInvoiceStatus][3]			+":"	,11,1);		//請求ステータス
		JLabel LB_UseFeeBasePtCd		= B100_FrameParts.JLabelSet(		200,200,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColUseFeeBasePtCd][3]			+":"	,10,1);		//適用運賃タリフCD
		JLabel LB_FeeFixFG				= B100_FrameParts.JLabelSet(		200,225,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColFeeFixFG][3]					+":"	,11,1);		//金額確定フラグ
		JLabel LB_FeeFixDate			= B100_FrameParts.JLabelSet(		200,250,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColFeeFixDate][3]				+":"	,11,1);		//金額確定日時
		
		JLabel LB_TaxFg					= B100_FrameParts.JLabelSet(		400,200,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColTaxFg][3]						+":"	,11,1);		//税区分
		JLabel LB_TaxRate				= B100_FrameParts.JLabelSet(		400,225,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColTaxRate][3]					+":"	,11,1);		//税率
		JLabel LB_DeliFee				= B100_FrameParts.JLabelSet(		400,250,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColDeliFee][3]					+":"	,11,1);		//運賃
		
		JLabel LB_AddDeliFee01			= B100_FrameParts.JLabelSet(		600,200,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColAddDeliFee01][3]				+":"	,11,1);		//付帯費用1
		JLabel LB_AddDeliFee02			= B100_FrameParts.JLabelSet(		600,225,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColAddDeliFee02][3]				+":"	,11,1);		//付帯費用2
		JLabel LB_AddDeliFee03			= B100_FrameParts.JLabelSet(		600,250,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColAddDeliFee03][3]				+":"	,11,1);		//付帯費用3
		
		JLabel LB_HaighWayFee01			= B100_FrameParts.JLabelSet(		800,200,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColHaighWayFee01][3]			+":"	, 9,1);		//高速代等実費精算分1（内税）
		JLabel LB_HaighWayFee02			= B100_FrameParts.JLabelSet(		800,225,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColHaighWayFee02][3]			+":"	, 9,1);		//高速代等実費精算分2（内税）
		JLabel LB_ConsumptionTax		= B100_FrameParts.JLabelSet(		800,250,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColConsumptionTax][3]			+":"	,11,1);		//消費税
		
		JLabel LB_WithOutTaxTotal		= B100_FrameParts.JLabelSet(	   1000,225,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColWithOutTaxTotal][3]			+":"	,11,1);		//税別合計金額
		JLabel LB_TotalFee				= B100_FrameParts.JLabelSet(	   1000,250,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColTotalFee][3]					+":"	,11,1);		//税込請求額合計
		
		
		final JComboBox  			TB_DeliveryTypeCd01		= B100_FrameParts.JComboBoxSet(					100, 25,200,20,B100_DefaultVariable.DeliveryType01[0],11);				//運送タイプ01
		final JComboBox  			TB_DeliveryTypeCd02		= B100_FrameParts.JComboBoxSet(					100, 50,200,20,B100_DefaultVariable.DeliveryType02[0],11);				//運送タイプ02
		final JComboBox  			TB_DeliveryTypeCd03		= B100_FrameParts.JComboBoxSet(					100, 75,200,20,B100_DefaultVariable.DeliveryType03[0],11);				//運送タイプ03
		final JComboBox  			TB_DeliveryTypeCd04		= B100_FrameParts.JComboBoxSet(					100,100,200,20,B100_DefaultVariable.DeliveryType04[0],11);				//運送タイプ04
		final JComboBox  			TB_DeliveryTypeCd05		= B100_FrameParts.JComboBoxSet(					100,125,200,20,B100_DefaultVariable.DeliveryType05[0],11);				//運送タイプ05
		
		final JFormattedTextField  	TB_CodPay				= B100_FrameParts.JFormattedTextFieldSet(		100,175,100,20,"",11,1,"#,###");											//代引金額
		final JFormattedTextField  	TB_CodConsumptionTax	= B100_FrameParts.JFormattedTextFieldSet(		100,200,100,20,"",11,1,"#,###");											//代引消費税
		final JComboBox  			TB_ReceiptStampFG		= B100_FrameParts.JComboBoxSet(					100,225,100,20,B100_DefaultVariable.ReceiptStampFGList[0],11);			//受領印チェック
		final JFormattedTextField  	TB_ReceiptStampDate		= B100_FrameParts.JFormattedTextFieldSet(		100,250,100,20,"",11,0,"YYYY/MM/DD");										//受領印日時
		
		final JTextField  			TB_Com01				= B100_FrameParts.JTextFieldSet(					400, 25,300,20,"",11,0);													//コメント01
		final JTextField  			TB_Com02				= B100_FrameParts.JTextFieldSet(					400, 50,300,20,"",11,0);													//コメント02
		final JTextField  			TB_Com03				= B100_FrameParts.JTextFieldSet(					400, 75,300,20,"",11,0);													//コメント03
		final JTextField  			TB_Com04				= B100_FrameParts.JTextFieldSet(					400,100,300,20,"",11,0);													//コメント04
		final JTextField  			TB_Com05				= B100_FrameParts.JTextFieldSet(					400,125,300,20,"",11,0);													//コメント05
		
		final JFormattedTextField 	TB_EntryDate			= B100_FrameParts.JFormattedTextFieldSet(		800, 25,150,20,"",11,0,"YYYY/MM/DD HH:MM:SS");								//登録日
		final JFormattedTextField 	TB_UpdateDate			= B100_FrameParts.JFormattedTextFieldSet(		800, 50,150,20,"",11,0,"YYYY/MM/DD HH:MM:SS");								//更新日
		final JTextField  			TB_EntryUser			= B100_FrameParts.JTextFieldSet(					800, 75,150,20,"",11,0);													//登録者
		final JTextField  			TB_UpdateUser			= B100_FrameParts.JTextFieldSet(					800,100,150,20,"",11,0);													//更新者
		
		final JTextField  			TB_EntryPG				= B100_FrameParts.JTextFieldSet(				   1050, 25,200,20,"",11,0);													//登録プログラム
		final JTextField  			TB_UpdatePG				= B100_FrameParts.JTextFieldSet(				   1050, 50,200,20,"",11,0);													//更新プログラム
		
		final JComboBox  			TB_InvoiceStatus		= B100_FrameParts.JComboBoxSet(					300,175,100,20,B100_DefaultVariable.InvoiceStatusList[0],11);				//請求ステータス
		final JTextField  			TB_UseFeeBasePtCd		= B100_FrameParts.JTextFieldSet(					300,200,100,20,"",11,0);													//適用運賃タリフCD
		final JComboBox  			TB_FeeFixFG				= B100_FrameParts.JComboBoxSet(					300,225,100,20,B100_DefaultVariable.FeeFixFgList[0],11);					//金額確定フラグ
		final JFormattedTextField  	TB_FeeFixDate			= B100_FrameParts.JFormattedTextFieldSet(		300,250,100,20,"",11,0,"YYYY/MM/DD");										//金額確定日時
		
		final JComboBox  			TB_TaxFg				= B100_FrameParts.JComboBoxSet(					500,200,100,20,B100_DefaultVariable.TaxFgList[0],11);						//税区分
		final JFormattedTextField  	TB_TaxRate				= B100_FrameParts.JFormattedTextFieldSet(		500,225,100,20,""+B100_DefaultVariable.NormalTaxRate,11,1,"#,###");		//税率
		final JFormattedTextField  	TB_DeliFee				= B100_FrameParts.JFormattedTextFieldSet(		500,250,100,20,"",11,1,"#,###");											//運賃
		
		final JFormattedTextField  	TB_AddDeliFee01			= B100_FrameParts.JFormattedTextFieldSet(		700,200,100,20,"",11,1,"#,###");											//付帯費用1
		final JFormattedTextField  	TB_AddDeliFee02			= B100_FrameParts.JFormattedTextFieldSet(		700,225,100,20,"",11,1,"#,###");											//付帯費用2
		final JFormattedTextField  	TB_AddDeliFee03			= B100_FrameParts.JFormattedTextFieldSet(		700,250,100,20,"",11,1,"#,###");											//付帯費用3
		
		final JFormattedTextField  	TB_HaighWayFee01		= B100_FrameParts.JFormattedTextFieldSet(		900,200,100,20,"",11,1,"#,###");											//高速代等実費精算分1（内税）
		final JFormattedTextField  	TB_HaighWayFee02		= B100_FrameParts.JFormattedTextFieldSet(		900,225,100,20,"",11,1,"#,###");											//高速代等実費精算分2（内税）
		final JFormattedTextField  	TB_ConsumptionTax		= B100_FrameParts.JFormattedTextFieldSet(		900,250,100,20,"",11,1,"#,###");											//消費税
		
		final JFormattedTextField  	TB_WithOutTaxTotal		= B100_FrameParts.JFormattedTextFieldSet(	   1100,225,100,20,"",11,1,"#,###");											//税別合計金額
		final JFormattedTextField  	TB_TotalFee				= B100_FrameParts.JFormattedTextFieldSet(	   1100,250,100,20,"",11,1,"#,###");											//税込請求額合計
		
		PN_HD01.add(LB_DeliveryTypeCd01);
		PN_HD01.add(LB_DeliveryTypeCd02);
		PN_HD01.add(LB_DeliveryTypeCd03);
		PN_HD01.add(LB_DeliveryTypeCd04);
		PN_HD01.add(LB_DeliveryTypeCd05);
		
		PN_HD01.add(LB_CodPay);
		PN_HD01.add(LB_CodConsumptionTax);
		PN_HD01.add(LB_ReceiptStampFG);
		PN_HD01.add(LB_ReceiptStampDate);
		
		PN_HD01.add(LB_Com01);
		PN_HD01.add(LB_Com02);
		PN_HD01.add(LB_Com03);
		PN_HD01.add(LB_Com04);
		PN_HD01.add(LB_Com05);
		
		PN_HD01.add(LB_EntryDate);
		PN_HD01.add(LB_UpdateDate);
		PN_HD01.add(LB_EntryUser);
		PN_HD01.add(LB_UpdateUser);
		
		PN_HD01.add(LB_EntryPG);
		PN_HD01.add(LB_UpdatePG);
		
		PN_HD01.add(LB_InvoiceStatus);
		PN_HD01.add(LB_UseFeeBasePtCd);
		PN_HD01.add(LB_FeeFixFG);
		PN_HD01.add(LB_FeeFixDate);
		
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
		
		PN_HD01.add(TB_DeliveryTypeCd01);
		PN_HD01.add(TB_DeliveryTypeCd02);
		PN_HD01.add(TB_DeliveryTypeCd03);
		PN_HD01.add(TB_DeliveryTypeCd04);
		PN_HD01.add(TB_DeliveryTypeCd05);
		
		PN_HD01.add(TB_CodPay);
		PN_HD01.add(TB_CodConsumptionTax);
		PN_HD01.add(TB_ReceiptStampFG);
		PN_HD01.add(TB_ReceiptStampDate);
		
		PN_HD01.add(TB_Com01);
		PN_HD01.add(TB_Com02);
		PN_HD01.add(TB_Com03);
		PN_HD01.add(TB_Com04);
		PN_HD01.add(TB_Com05);
		
		PN_HD01.add(TB_EntryDate);
		PN_HD01.add(TB_UpdateDate);
		PN_HD01.add(TB_EntryUser);
		PN_HD01.add(TB_UpdateUser);
		
		PN_HD01.add(TB_EntryPG);
		PN_HD01.add(TB_UpdatePG);
		
		PN_HD01.add(TB_InvoiceStatus);
		PN_HD01.add(TB_UseFeeBasePtCd);
		PN_HD01.add(TB_FeeFixFG);
		PN_HD01.add(TB_FeeFixDate);
		
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
		
		/****************************/
		
		JLabel LB_DeliTypeName			= B100_FrameParts.JLabelSet(		  0, 25,150,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColDeliTypeName][3]				+":"	,11,1);		//運送タイプ名01
		JLabel LB_DeliTypeName02		= B100_FrameParts.JLabelSet(		  0, 50,150,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColDeliTypeName02][3]			+":"	,11,1);		//運送タイプ名02
		JLabel LB_DeliTypeName03		= B100_FrameParts.JLabelSet(		  0, 75,150,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColDeliTypeName03][3]			+":"	,11,1);		//運送タイプ名03
		JLabel LB_DeliTypeName04		= B100_FrameParts.JLabelSet(		  0,100,150,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColDeliTypeName04][3]			+":"	,11,1);		//運送タイプ名04
		JLabel LB_DeliTypeName05		= B100_FrameParts.JLabelSet(		  0,125,150,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColDeliTypeName05][3]			+":"	,11,1);		//運送タイプ名05
		
		JLabel LB_CLName01				= B100_FrameParts.JLabelSet(		  0,175,150,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColCLName01][3]					+":"	,11,1);		//荷主名
		JLabel LB_ClGpCD				= B100_FrameParts.JLabelSet(		  0,200,150,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColClGpCD][3]					+":"	,11,1);		//荷主グループCD
		JLabel LB_CLGpName01			= B100_FrameParts.JLabelSet(		  0,225,150,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColCLGpName01][3]				+":"	,11,1);		//荷主グループ標記名

		JLabel LB_CourseGpCd			= B100_FrameParts.JLabelSet(		350, 25,150,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColCourseGpCd][3]				+":"	,10,1);		//コースグループコード
		JLabel LB_CourseCD				= B100_FrameParts.JLabelSet(		350, 50,150,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColCourseCD][3]					+":"	,10,1);		//一次配車コースコード
		JLabel LB_CourseCDEda			= B100_FrameParts.JLabelSet(		350, 75,150,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColCourseCDEda][3]				+":"	,10,1);		//一次配車コースコード枝番
		JLabel LB_PitGrp				= B100_FrameParts.JLabelSet(		350,100,150,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColPitGrp][3]					+":"	,10,1);		//一次配車払出ピットグループ
		JLabel LB_Pit01					= B100_FrameParts.JLabelSet(		350,125,150,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColPit01][3]						+":"	,10,1);		//一次配車払出ピット01
		JLabel LB_Pit02					= B100_FrameParts.JLabelSet(		350,150,150,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColPit02][3]						+":"	,10,1);		//一次配車払出ピット02
		JLabel LB_Pit03					= B100_FrameParts.JLabelSet(		350,175,150,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColPit03][3]						+":"	,10,1);		//一次配車払出ピット03
		JLabel LB_Pit04					= B100_FrameParts.JLabelSet(		350,200,150,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColPit04][3]						+":"	,10,1);		//一次配車払出ピット04
		JLabel LB_Pit05					= B100_FrameParts.JLabelSet(		350,225,150,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColPit05][3]						+":"	,10,1);		//一次配車払出ピット05
		
		final JTextField  			TB_DeliTypeName			= B100_FrameParts.JTextFieldSet(					150, 25,200,20,"",11,0);											//運送タイプ名01
		final JTextField  			TB_DeliTypeName02		= B100_FrameParts.JTextFieldSet(					150, 50,200,20,"",11,0);											//運送タイプ名02
		final JTextField  			TB_DeliTypeName03		= B100_FrameParts.JTextFieldSet(					150, 75,200,20,"",11,0);											//運送タイプ名03
		final JTextField  			TB_DeliTypeName04		= B100_FrameParts.JTextFieldSet(					150,100,200,20,"",11,0);											//運送タイプ名04
		final JTextField  			TB_DeliTypeName05		= B100_FrameParts.JTextFieldSet(					150,125,200,20,"",11,0);											//運送タイプ名05
		
		final JTextField  			TB_CLName01				= B100_FrameParts.JTextFieldSet(					150,175,200,20,"",11,0);											//荷主名
		final JComboBox   			TB_ClGpCD				= B100_FrameParts.JComboBoxSet(					150,200,200,20,B100_DefaultVariable.ClGpList[0],11);				//荷主グループCD
		final JTextField  			TB_CLGpName01			= B100_FrameParts.JTextFieldSet(					150,225,200,20,"",11,0);											//荷主グループ標記名
		
		final JTextField  			TB_CourseGpCd			= B100_FrameParts.JTextFieldSet(					500, 25,100,20,"",11,0);											//コースグループコード
		final JTextField  			TB_CourseCD				= B100_FrameParts.JTextFieldSet(					500, 50,100,20,"",11,0);											//一次配車コースコード
		final JTextField  			TB_CourseCDEda			= B100_FrameParts.JTextFieldSet(					500, 75,100,20,"",11,0);											//一次配車コースコード枝番
		final JTextField  			TB_PitGrp				= B100_FrameParts.JTextFieldSet(					500,100,100,20,"",11,0);											//一次配車払出ピットグループ
		final JTextField  			TB_Pit01				= B100_FrameParts.JTextFieldSet(					500,125,100,20,"",11,0);											//一次配車払出ピット01
		final JTextField  			TB_Pit02				= B100_FrameParts.JTextFieldSet(					500,150,100,20,"",11,0);											//一次配車払出ピット02
		final JTextField  			TB_Pit03				= B100_FrameParts.JTextFieldSet(					500,175,100,20,"",11,0);											//一次配車払出ピット03
		final JTextField  			TB_Pit04				= B100_FrameParts.JTextFieldSet(					500,200,100,20,"",11,0);											//一次配車払出ピット04
		final JTextField  			TB_Pit05				= B100_FrameParts.JTextFieldSet(					500,225,100,20,"",11,0);											//一次配車払出ピット05
		
		PN_HD02.add(LB_DeliTypeName);
		PN_HD02.add(LB_DeliTypeName02);
		PN_HD02.add(LB_DeliTypeName03);
		PN_HD02.add(LB_DeliTypeName04);
		PN_HD02.add(LB_DeliTypeName05);
		
		PN_HD02.add(LB_CLName01);
		PN_HD02.add(LB_ClGpCD);
		PN_HD02.add(LB_CLGpName01);
		
		PN_HD02.add(LB_CourseGpCd);
		PN_HD02.add(LB_CourseCD);
		PN_HD02.add(LB_CourseCDEda);
		PN_HD02.add(LB_PitGrp);
		PN_HD02.add(LB_Pit01);
		PN_HD02.add(LB_Pit02);
		PN_HD02.add(LB_Pit03);
		PN_HD02.add(LB_Pit04);
		PN_HD02.add(LB_Pit05);
		
		PN_HD02.add(TB_DeliTypeName);
		PN_HD02.add(TB_DeliTypeName02);
		PN_HD02.add(TB_DeliTypeName03);
		PN_HD02.add(TB_DeliTypeName04);
		PN_HD02.add(TB_DeliTypeName05);
		
		PN_HD02.add(TB_CLName01);
		PN_HD02.add(TB_ClGpCD);
		PN_HD02.add(TB_CLGpName01);
		
		PN_HD02.add(TB_CourseGpCd);
		PN_HD02.add(TB_CourseCD);
		PN_HD02.add(TB_CourseCDEda);
		PN_HD02.add(TB_PitGrp);
		PN_HD02.add(TB_Pit01);
		PN_HD02.add(TB_Pit02);
		PN_HD02.add(TB_Pit03);
		PN_HD02.add(TB_Pit04);
		PN_HD02.add(TB_Pit05);
		
		/***************/
		
		//コンボBoxデフォルトポジション
		TB_InvoiceWhCd.setSelectedIndex(B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.WhList[1]		,ClWh,true));				//倉庫CD
		TB_PickupWhCd.setSelectedIndex(B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.WhList[1]		,ClWh,true));				//集荷倉庫CD
		TB_ClGpCD.setSelectedIndex(B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.ClGpList[1]			,ClGp,true));				//荷主グループCD
		TB_PurposeFG.setSelectedIndex(B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.PurposeList[1]	,"0",true));				//目的フラグ
		TB_DeliveryTypeCd01.setSelectedIndex(B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.DeliveryType01[1]	,"",true));		//運送タイプ01
		TB_DeliveryTypeCd02.setSelectedIndex(B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.DeliveryType02[1]	,"",true));		//運送タイプ02
		TB_DeliveryTypeCd03.setSelectedIndex(B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.DeliveryType03[1]	,"",true));		//運送タイプ03
		TB_DeliveryTypeCd04.setSelectedIndex(B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.DeliveryType04[1]	,"",true));		//運送タイプ04
		TB_DeliveryTypeCd05.setSelectedIndex(B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.DeliveryType05[1]	,"",true));		//運送タイプ05
		TB_CodFG.setSelectedIndex(B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.CODList[1]	,"0",true));						//代引フラグ
		TB_Status.setSelectedIndex(B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.StatusList[1]	,"0",true));					//運送状況
		TB_TaxFg.setSelectedIndex(B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.TaxFgList[1]	,"0",true));						//税区分
		TB_ReceiptStampFG.setSelectedIndex(B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.ReceiptStampFGList[1]	,"0",true));	//受領印チェック
		TB_InvoiceStatus.setSelectedIndex(B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.InvoiceStatusList[1]	,"0",true));	//請求ステータス
		TB_WmsStatus.setSelectedIndex(B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.WmsStatusList[1]	,"0",true));			//在庫管理ステータス
		
		//検索結果修正不可
		TB_InvoiceWhCd.setEnabled(false);
		TB_PickupWhCd.setEnabled(false);
		TB_ClDeliNo.setEditable(false);
		
		TB_PurposeFG.setEnabled(false);
		TB_PlanDate.setEditable(false);
		TB_ShipDate.setEditable(false);
		TB_SPPlanDate.setEditable(false);
		TB_SPDate.setEditable(false);
		TB_SPTimeFG.setEditable(false);
		TB_SPTimeStr.setEditable(false);
		TB_SPTimeEnd.setEditable(false);
		
		TB_DeliveryTypeCd01.setEnabled(false);
		TB_DeliveryTypeCd02.setEnabled(false);
		TB_DeliveryTypeCd03.setEnabled(false);
		TB_DeliveryTypeCd04.setEnabled(false);
		TB_DeliveryTypeCd05.setEnabled(false);
		TB_CodFG.setEnabled(false);
		TB_CodPayTotal.setEditable(false);
		TB_CodPay.setEditable(false);
		TB_CodConsumptionTax.setEditable(false);
		
		TB_NiokuriCd.setEditable(false);
		TB_NiokuriDepartmentCd.setEditable(false);
		TB_NiokuriName01.setEditable(false);
		TB_NiokuriName02.setEditable(false);
		TB_NiokuriName03.setEditable(false);
		TB_NiokuriPost.setEditable(false);
		TB_NiokuriAdd01.setEditable(false);
		TB_NiokuriAdd02.setEditable(false);
		TB_NiokuriAdd03.setEditable(false);
		TB_NioKuriTel.setEditable(false);
		TB_NioKuriFax.setEditable(false);
		TB_NioKuriMail.setEditable(false);
		TB_NiokuriMunicCd.setEditable(false);

		TB_ClDeliCd.setEditable(false);
		TB_DeliCd.setEditable(false);
		TB_DeliDepartmentCd.setEditable(false);
		TB_DeliName01.setEditable(false);
		TB_DeliName02.setEditable(false);
		TB_DeliName03.setEditable(false);
		TB_DeliPost.setEditable(false);
		TB_DeliAdd01.setEditable(false);
		TB_DeliAdd02.setEditable(false);
		TB_DeliAdd03.setEditable(false);
		TB_DeliTel.setEditable(false);
		TB_DeliFax.setEditable(false);
		TB_DeliMail.setEditable(false);
		TB_DeliMunicCd.setEditable(false);

		TB_TotalWeight.setEditable(false);
		TB_TotalSize.setEditable(false);
		TB_TotalQty.setEditable(false);
		TB_ChildrenFG.setEnabled(false);
		TB_ParentOkuriNo.setEditable(false);
		TB_Status.setEnabled(false);
		TB_Com01.setEditable(false);
		TB_Com02.setEditable(false);
		TB_Com03.setEditable(false);
		TB_Com04.setEditable(false);
		TB_Com05.setEditable(false);

		TB_TaxFg.setEnabled(false);
		TB_TaxRate.setEditable(false);
		TB_DeliFee.setEditable(false);
		TB_AddDeliFee01.setEditable(false);
		TB_AddDeliFee02.setEditable(false);
		TB_AddDeliFee03.setEditable(false);
		TB_HaighWayFee01.setEditable(false);
		TB_HaighWayFee02.setEditable(false);
		TB_ConsumptionTax.setEditable(false);
		TB_WithOutTaxTotal.setEditable(false);
		TB_TotalFee.setEditable(false);
		TB_FeeFixFG.setEnabled(false);
		TB_FeeFixDate.setEditable(false);
		TB_ReceiptStampFG.setEnabled(false);
		TB_ReceiptStampDate.setEditable(false);
		TB_InvoiceStatus.setEnabled(false);
		TB_EntryPG.setEditable(false);
		TB_UpdatePG.setEditable(false);
		
		TB_DeliTypeName.setEditable(false);
		TB_DeliTypeName02.setEditable(false);
		TB_DeliTypeName03.setEditable(false);
		TB_DeliTypeName04.setEditable(false);
		TB_DeliTypeName05.setEditable(false);
		TB_EntryDate.setEditable(false);
		TB_UpdateDate.setEditable(false);
		TB_EntryUser.setEditable(false);
		TB_UpdateUser.setEditable(false);
		
		TB_UseFeeBasePtCd.setEditable(false);
		TB_WmsStatus.setEnabled(false);
		TB_WmsShipDate.setEditable(false);
		TB_CourseGpCd.setEditable(false);
		TB_CourseCD.setEditable(false);
		TB_CourseCDEda.setEditable(false);
		TB_PitGrp.setEditable(false);
		TB_Pit01.setEditable(false);
		TB_Pit02.setEditable(false);
		TB_Pit03.setEditable(false);
		TB_Pit04.setEditable(false);
		TB_Pit05.setEditable(false);

		TB_CLName01.setEditable(false);
		TB_ClGpCD.setEnabled(false);
		TB_CLGpName01.setEditable(false);

		//明細情報標記用
		JLabel LB_MsNo					= B100_FrameParts.JLabelSet(		  0,  0,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsNo][3]						+":"	, 9,1);		//明細番号
		JLabel LB_MsDeliNo				= B100_FrameParts.JLabelSet(		  0, 25,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsDeliNo][3]					+":"	, 9,1);		//明細出荷番号
		JLabel LB_MsDelliMsNo			= B100_FrameParts.JLabelSet(		  0, 50,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsDelliMsNo][3]				+":"	, 9,1);		//明細出荷番号明細番号
		JLabel LB_MsClOrderNo			= B100_FrameParts.JLabelSet(		  0, 75,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsClOrderNo][3]				+":"	, 9,1);		//明細荷主管理番号
		
		JLabel LB_MsItemCd				= B100_FrameParts.JLabelSet(		200,  0,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsItemCd][3]					+":"	,11,1);		//明細商品コード
		JLabel LB_MsItemName01			= B100_FrameParts.JLabelSet(		200, 25,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsItemName01][3]				+":"	,11,1);		//明細商品表記名
		JLabel LB_MsQty					= B100_FrameParts.JLabelSet(		200, 50,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsQty][3]						+":"	,11,1);		//明細個数
		JLabel LB_MsLot					= B100_FrameParts.JLabelSet(		200, 75,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsLot][3]						+":"	,11,1);		//明細ロット指定
		JLabel LB_MsExpDate				= B100_FrameParts.JLabelSet(		200,100,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsExpDate][3]				+":"	,11,1);		//明細賞味期限指定
		
		JLabel LB_MsSubTotalWeight		= B100_FrameParts.JLabelSet(		400, 50,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsSubTotalWeight][3]		+":"	,11,1);		//明細明細重量
		JLabel LB_MsSubTotalSize		= B100_FrameParts.JLabelSet(		400, 75,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsSubTotalSize][3]			+":"	,11,1);		//明細明細サイズ
		JLabel LB_MsPackingQty			= B100_FrameParts.JLabelSet(		400,100,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsPackingQty][3]				+":"	,11,1);		//明細荷姿数量
		
		JLabel LB_MsCom01				= B100_FrameParts.JLabelSet(		600,  0,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsCom01][3]					+":"	,11,1);		//明細コメント01
		JLabel LB_MsCom02				= B100_FrameParts.JLabelSet(		600, 25,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsCom02][3]					+":"	,11,1);		//明細コメント02
		JLabel LB_MsCom03				= B100_FrameParts.JLabelSet(		600, 50,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsCom03][3]					+":"	,11,1);		//明細コメント03
		JLabel LB_MsCom04				= B100_FrameParts.JLabelSet(		600, 75,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsCom04][3]					+":"	,11,1);		//明細コメント04
		JLabel LB_MsCom05				= B100_FrameParts.JLabelSet(		600,100,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsCom05][3]					+":"	,11,1);		//明細コメント05
		
		JLabel LB_MsPackingType			= B100_FrameParts.JLabelSet(	   1000,  0,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsPackingType][3]			+":"	,11,1);		//明細荷姿タイプ
		JLabel LB_MsTildFG				= B100_FrameParts.JLabelSet(	   1000, 25,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsTildFG][3]					+":"	,11,1);		//明細温度区分
		
		JLabel LB_MsUnitPrice			= B100_FrameParts.JLabelSet(	   1000, 75,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsUnitPrice][3]				+":"	,11,1);		//明細単価
		JLabel LB_MsSubTotalPrice		= B100_FrameParts.JLabelSet(	   1000,100,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsSubTotalPrice][3]			+":"	,11,1);		//明細金額
		
		final JFormattedTextField  	TB_MsNo					= B100_FrameParts.JFormattedTextFieldSet(		100,  0,100,20,"",11,1,"#,###");									//明細番号
		final JTextField  			TB_MsDeliNo				= B100_FrameParts.JTextFieldSet(					100, 25,100,20,"",11,0);											//明細出荷番号
		final JFormattedTextField  	TB_MsDelliMsNo			= B100_FrameParts.JFormattedTextFieldSet(		100, 50,100,20,"",11,1,"#,###");									//明細出荷番号明細番号
		final JTextField  			TB_MsClOrderNo			= B100_FrameParts.JTextFieldSet(					100, 75,100,20,"",11,0);											//明細荷主管理番号
		
		final JTextField  			TB_MsItemCd				= B100_FrameParts.JTextFieldSet(					300,  0,100,20,"",11,0);											//明細商品コード
		final JTextField  			TB_MsItemName01			= B100_FrameParts.JTextFieldSet(					300, 25,300,20,"",11,0);											//明細商品表記名
		final JFormattedTextField  	TB_MsQty				= B100_FrameParts.JFormattedTextFieldSet(		300, 50,100,20,"",11,1,"#,###");									//明細個数
		final JTextField  			TB_MsLot				= B100_FrameParts.JTextFieldSet(					300, 75,100,20,"",11,0);											//明細ロット指定
		final JFormattedTextField  	TB_MsExpDate			= B100_FrameParts.JFormattedTextFieldSet(		300,100,100,20,"",11,0,"YYYY/MM/DD");								//明細賞味期限指定
		
		final JFormattedTextField  	TB_MsSubTotalWeight		= B100_FrameParts.JFormattedTextFieldSet(		500, 50,100,20,"",11,1,"#,###.##");									//明細明細重量
		final JFormattedTextField  	TB_MsSubTotalSize		= B100_FrameParts.JFormattedTextFieldSet(		500, 75,100,20,"",11,1,"#,###.##");									//明細明細サイズ
		final JFormattedTextField  	TB_MsPackingQty			= B100_FrameParts.JFormattedTextFieldSet(		500,100,100,20,"",11,1,"#,###");									//明細荷姿数量
		
		final JTextField  			TB_MsCom01				= B100_FrameParts.JTextFieldSet(					700,  0,300,20,"",11,0);											//明細コメント01
		final JTextField  			TB_MsCom02				= B100_FrameParts.JTextFieldSet(					700, 25,300,20,"",11,0);											//明細コメント02
		final JTextField  			TB_MsCom03				= B100_FrameParts.JTextFieldSet(					700, 50,300,20,"",11,0);											//明細コメント03
		final JTextField  			TB_MsCom04				= B100_FrameParts.JTextFieldSet(					700, 75,300,20,"",11,0);											//明細コメント04
		final JTextField  			TB_MsCom05				= B100_FrameParts.JTextFieldSet(					700,100,300,20,"",11,0);											//明細コメント05
		
		final JComboBox  			TB_MsPackingType		= B100_FrameParts.JComboBoxSet(				   1100,  0,100,20,B100_DefaultVariable.UnitTypeList[0],11);			//明細荷姿タイプ
		final JComboBox   			TB_MsTildFG				= B100_FrameParts.JComboBoxSet(				   1100, 25,100,20,B100_DefaultVariable.TildFG[0],11);					//明細温度区分
		
		final JFormattedTextField  	TB_MsUnitPrice			= B100_FrameParts.JFormattedTextFieldSet(	   1100, 75,100,20,"",11,0,"#,###.##");									//明細単価
		final JFormattedTextField  	TB_MsSubTotalPrice		= B100_FrameParts.JFormattedTextFieldSet(	   1100,100,100,20,"",11,0,"#,###.##");									//明細金額
		
		
		
		PN_MS00.add(LB_MsNo);
		PN_MS00.add(LB_MsDeliNo);
		PN_MS00.add(LB_MsDelliMsNo);
		PN_MS00.add(LB_MsClOrderNo);
		
		PN_MS00.add(LB_MsItemCd);
		PN_MS00.add(LB_MsItemName01);
		PN_MS00.add(LB_MsQty);
		PN_MS00.add(LB_MsLot);
		PN_MS00.add(LB_MsExpDate);
		
		PN_MS00.add(LB_MsSubTotalWeight);
		PN_MS00.add(LB_MsSubTotalSize);
		PN_MS00.add(LB_MsPackingQty);
		
		PN_MS00.add(LB_MsCom01);
		PN_MS00.add(LB_MsCom02);
		PN_MS00.add(LB_MsCom03);
		PN_MS00.add(LB_MsCom04);
		PN_MS00.add(LB_MsCom05);
		
		PN_MS00.add(LB_MsPackingType);
		PN_MS00.add(LB_MsTildFG);
		PN_MS00.add(LB_MsUnitPrice);
		PN_MS00.add(LB_MsSubTotalPrice);
		
		PN_MS00.add(TB_MsNo);
		PN_MS00.add(TB_MsDeliNo);
		PN_MS00.add(TB_MsDelliMsNo);
		PN_MS00.add(TB_MsClOrderNo);
		
		PN_MS00.add(TB_MsItemCd);
		PN_MS00.add(TB_MsItemName01);
		PN_MS00.add(TB_MsQty);
		PN_MS00.add(TB_MsLot);
		PN_MS00.add(TB_MsExpDate);
		
		PN_MS00.add(TB_MsSubTotalWeight);
		PN_MS00.add(TB_MsSubTotalSize);
		PN_MS00.add(TB_MsPackingQty);
		
		PN_MS00.add(TB_MsCom01);
		PN_MS00.add(TB_MsCom02);
		PN_MS00.add(TB_MsCom03);
		PN_MS00.add(TB_MsCom04);
		PN_MS00.add(TB_MsCom05);
		
		PN_MS00.add(TB_MsPackingType);
		PN_MS00.add(TB_MsTildFG);
		PN_MS00.add(TB_MsUnitPrice);
		PN_MS00.add(TB_MsSubTotalPrice);
		
		/***************/
		
		JLabel LB_MsItemName02			= B100_FrameParts.JLabelSet(		  0,  0,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsItemName02][3]				+":"	,11,1);		//明細商品正式名
		JLabel LB_MsItemName03			= B100_FrameParts.JLabelSet(		  0, 25,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsItemName03][3]				+":"	,11,1);		//明細商品略名
		JLabel LB_MsCategoryCd			= B100_FrameParts.JLabelSet(		  0, 50,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsCategoryCd][3]				+":"	,11,1);		//明細商品分類
		JLabel LB_MsCategoryName		= B100_FrameParts.JLabelSet(		  0, 75,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsCategoryName][3]			+":"	,11,1);		//明細商品分類名
		JLabel LB_MsClItemCd			= B100_FrameParts.JLabelSet(		200, 50,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsClItemCd][3]				+":"	,11,1);		//明細荷主商品CD
		JLabel LB_MsItemMDNo			= B100_FrameParts.JLabelSet(		200, 75,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsItemMDNo][3]				+":"	,11,1);		//明細型番
		JLabel LB_MsJanCd				= B100_FrameParts.JLabelSet(		200,100,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsJanCd][3]					+":"	,11,1);		//明細荷姿JanCd
		
		JLabel LB_MsEntryDate			= B100_FrameParts.JLabelSet(		400,  0,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsEntryDate][3]				+":"	,11,1);		//明細登録日
		JLabel LB_MsUpdateDate			= B100_FrameParts.JLabelSet(		400, 25,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsUpdateDate][3]				+":"	,11,1);		//明細更新日
		JLabel LB_MsEntryUser			= B100_FrameParts.JLabelSet(		400, 50,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsEntryUser][3]				+":"	,11,1);		//明細登録者
		JLabel LB_MsUpdateUser			= B100_FrameParts.JLabelSet(		400, 75,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsUpdateUser][3]				+":"	,11,1);		//明細更新者
		
		JLabel LB_MsUnitName			= B100_FrameParts.JLabelSet(		800,  0,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsUnitName][3]				+":"	,11,1);		//明細明細単位
		JLabel LB_MsUnitWeight			= B100_FrameParts.JLabelSet(		800, 25,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsUnitWeight][3]				+":"	,11,1);		//明細単位重量
		JLabel LB_MsUnitSize			= B100_FrameParts.JLabelSet(		800, 50,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsUnitSize][3]				+":"	,11,1);		//明細単位サイズ
		JLabel LB_MsTildName			= B100_FrameParts.JLabelSet(		800, 75,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsTildName][3]				+":"	,11,1);		//明細温度区分
		
		JLabel LB_MsClCd				= B100_FrameParts.JLabelSet(	   1000,  0,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsClCd][3]					+":"	,11,1);		//明細荷主コード
		JLabel LB_MsInvoiceWhCd			= B100_FrameParts.JLabelSet(	   1000, 25,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsInvoiceWhCd][3]			+":"	,11,1);		//明細倉庫コード
		JLabel LB_MsOkuriNo				= B100_FrameParts.JLabelSet(	   1000, 75,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsOkuriNo][3]				+":"	,11,1);		//明細送り状番号
		JLabel LB_MsClGpCd				= B100_FrameParts.JLabelSet(	   1000,100,100,20,(String)RtOkuriMsRt[T100_OkuriMsRt.ColMsClGpCd][3]					+":"	,11,1);		//明細荷主グループコード
		
		final JTextField  			TB_MsItemName02			= B100_FrameParts.JTextFieldSet(					100,  0,300,20,"",11,0);											//明細商品正式名
		final JTextField  			TB_MsItemName03			= B100_FrameParts.JTextFieldSet(					100, 25,300,20,"",11,0);											//明細商品略名
		final JTextField  			TB_MsCategoryCd			= B100_FrameParts.JTextFieldSet(					100, 50,100,20,"",11,0);											//明細商品分類
		final JTextField  			TB_MsCategoryName		= B100_FrameParts.JTextFieldSet(					100, 75,100,20,"",11,0);											//明細商品分類名
		final JTextField  			TB_MsClItemCd			= B100_FrameParts.JTextFieldSet(					300, 50,100,20,"",11,0);											//明細荷主商品CD
		final JTextField  			TB_MsItemMDNo			= B100_FrameParts.JTextFieldSet(					300, 75,100,20,"",11,0);											//明細型番
		final JTextField  			TB_MsJanCd				= B100_FrameParts.JTextFieldSet(					300,100,100,20,"",11,0);											//明細荷姿JanCd
		
		final JFormattedTextField  	TB_MsEntryDate			= B100_FrameParts.JFormattedTextFieldSet(		500,  0,300,20,"",11,0,"YYYY/MM/DD HH:MM:SS");						//明細登録日
		final JFormattedTextField  	TB_MsUpdateDate			= B100_FrameParts.JFormattedTextFieldSet(		500, 25,300,20,"",11,0,"YYYY/MM/DD HH:MM:SS");						//明細更新日
		final JTextField  			TB_MsEntryUser			= B100_FrameParts.JTextFieldSet(					500, 50,300,20,"",11,0);											//明細登録者
		final JTextField  			TB_MsUpdateUser			= B100_FrameParts.JTextFieldSet(					500, 75,300,20,"",11,0);											//明細更新者
		
		final JTextField  			TB_MsUnitName			= B100_FrameParts.JTextFieldSet(					900,  0,100,20,"",11,0);											//明細明細単位
		final JFormattedTextField  	TB_MsUnitWeight			= B100_FrameParts.JFormattedTextFieldSet(		900, 25,100,20,"",11,1,"#,###.##");									//明細単位重量
		final JFormattedTextField  	TB_MsUnitSize			= B100_FrameParts.JFormattedTextFieldSet(		900, 50,100,20,"",11,1,"#,###.##");									//明細単位サイズ
		final JTextField  			TB_MsTildName			= B100_FrameParts.JTextFieldSet(					900, 75,100,20,"",11,0);											//明細温度区分名
		
		final JComboBox   			TB_MsClCd				= B100_FrameParts.JComboBoxSet(				   1100,  0,150,20,B100_DefaultVariable.ClList[0],11);					//明細荷主コード
		final JComboBox   			TB_MsInvoiceWhCd		= B100_FrameParts.JComboBoxSet(				   1100, 25,150,20,B100_DefaultVariable.WhList[0],11);					//明細倉庫コード
		final JTextField  			TB_MsOkuriNo			= B100_FrameParts.JTextFieldSet(				   1100, 75,100,20,"",11,0);											//明細送り状番号
		final JComboBox				TB_MsClGpCd				= B100_FrameParts.JComboBoxSet(				   1100,100,150,20,B100_DefaultVariable.ClGpList[0],11);				//明細荷主グループコード
		
		PN_MS01.add(LB_MsItemName02);
		PN_MS01.add(LB_MsItemName03);
		PN_MS01.add(LB_MsCategoryCd);
		PN_MS01.add(LB_MsCategoryName);
		PN_MS01.add(LB_MsClItemCd);
		PN_MS01.add(LB_MsItemMDNo);
		PN_MS01.add(LB_MsJanCd);
		
		PN_MS01.add(LB_MsEntryDate);
		PN_MS01.add(LB_MsUpdateDate);
		PN_MS01.add(LB_MsEntryUser);
		PN_MS01.add(LB_MsUpdateUser);
		
		PN_MS01.add(LB_MsUnitName);
		PN_MS01.add(LB_MsUnitWeight);
		PN_MS01.add(LB_MsUnitSize);
		PN_MS01.add(LB_MsTildName);
		
		PN_MS01.add(LB_MsClCd);
		PN_MS01.add(LB_MsInvoiceWhCd);
		PN_MS01.add(LB_MsOkuriNo);
		PN_MS01.add(LB_MsClGpCd);
		
		PN_MS01.add(TB_MsItemName02);
		PN_MS01.add(TB_MsItemName03);
		PN_MS01.add(TB_MsCategoryCd);
		PN_MS01.add(TB_MsCategoryName);
		PN_MS01.add(TB_MsClItemCd);
		PN_MS01.add(TB_MsItemMDNo);
		PN_MS01.add(TB_MsJanCd);
		
		PN_MS01.add(TB_MsEntryDate);
		PN_MS01.add(TB_MsUpdateDate);
		PN_MS01.add(TB_MsEntryUser);
		PN_MS01.add(TB_MsUpdateUser);
		
		PN_MS01.add(TB_MsUnitName);
		PN_MS01.add(TB_MsUnitWeight);
		PN_MS01.add(TB_MsUnitSize);
		PN_MS01.add(TB_MsTildName);
		
		PN_MS01.add(TB_MsClCd);
		PN_MS01.add(TB_MsInvoiceWhCd);
		PN_MS01.add(TB_MsOkuriNo);
		PN_MS01.add(TB_MsClGpCd);
		
		TB_MsClCd.setEnabled(false);
		TB_MsInvoiceWhCd.setEnabled(false);
		TB_MsOkuriNo.setEditable(false);
		TB_MsNo.setEditable(false);
		TB_MsDeliNo.setEditable(false);
		TB_MsDelliMsNo.setEditable(false);
		TB_MsClOrderNo.setEditable(false);
		TB_MsClGpCd.setEnabled(false);
		TB_MsItemCd.setEditable(false);
		TB_MsItemName01.setEditable(false);
		TB_MsItemName02.setEditable(false);
		TB_MsItemName03.setEditable(false);
		TB_MsUnitWeight.setEditable(false);
		TB_MsUnitSize.setEditable(false);
		TB_MsQty.setEditable(false);
		TB_MsPackingQty.setEditable(false);
		TB_MsUnitName.setEditable(false);
		TB_MsSubTotalWeight.setEditable(false);
		TB_MsSubTotalSize.setEditable(false);
		TB_MsUnitPrice.setEditable(false);
		TB_MsSubTotalPrice.setEditable(false);
		TB_MsCategoryCd.setEditable(false);
		TB_MsCategoryName.setEditable(false);
		TB_MsTildFG.setEnabled(false);
		TB_MsTildName.setEditable(false);
		TB_MsCom01.setEditable(false);
		TB_MsCom02.setEditable(false);
		TB_MsCom03.setEditable(false);
		TB_MsCom04.setEditable(false);
		TB_MsCom05.setEditable(false);
		TB_MsEntryDate.setEditable(false);
		TB_MsUpdateDate.setEditable(false);
		TB_MsEntryUser.setEditable(false);
		TB_MsUpdateUser.setEditable(false);
		TB_MsLot.setEditable(false);
		TB_MsExpDate.setEditable(false);
		TB_MsPackingType.setEnabled(false);
		TB_MsClItemCd.setEditable(false);
		TB_MsItemMDNo.setEditable(false);
		TB_MsJanCd.setEditable(false);
		
		

		OkuriMs_fm.add(HdTabPaneSet);
		OkuriMs_fm.add(MsTabPaneSet);
		
		//制御対象まとめる
		final Object[][] ControlTgt = new Object[5][RtOkuriMsRt.length];
		
		ControlTgt[0][T100_OkuriMsRt.ColClCd] 					= TB_ClCd;
		ControlTgt[0][T100_OkuriMsRt.ColInvoiceWhCd]	 		= TB_InvoiceWhCd;
		ControlTgt[0][T100_OkuriMsRt.ColOkuriNo] 				= TB_SearchOkuriNo;
		ControlTgt[0][T100_OkuriMsRt.ColClDeliNo] 				= TB_ClDeliNo;
		ControlTgt[0][T100_OkuriMsRt.ColPickupWhCd] 			= TB_PickupWhCd;
		ControlTgt[0][T100_OkuriMsRt.ColPurposeFG] 			= TB_PurposeFG;
		ControlTgt[0][T100_OkuriMsRt.ColPlanDate] 				= TB_PlanDate;
		ControlTgt[0][T100_OkuriMsRt.ColShipDate] 				= TB_ShipDate;
		ControlTgt[0][T100_OkuriMsRt.ColSPPlanDate] 			= TB_SPPlanDate;
		ControlTgt[0][T100_OkuriMsRt.ColSPDate] 				= TB_SPDate;
		ControlTgt[0][T100_OkuriMsRt.ColSPTimeFG] 				= TB_SPTimeFG;
		ControlTgt[0][T100_OkuriMsRt.ColSPTimeStr]			= TB_SPTimeStr;
		ControlTgt[0][T100_OkuriMsRt.ColSPTimeEnd] 			= TB_SPTimeEnd;
		ControlTgt[0][T100_OkuriMsRt.ColTotalWeight] 			= TB_TotalWeight;
		ControlTgt[0][T100_OkuriMsRt.ColTotalSize] 			= TB_TotalSize;
		ControlTgt[0][T100_OkuriMsRt.ColTotalQty] 				= TB_TotalQty;
		ControlTgt[0][T100_OkuriMsRt.ColDeliveryTypeCd01] 	= TB_DeliveryTypeCd01;
		ControlTgt[0][T100_OkuriMsRt.ColDeliTypeName] 		= TB_DeliTypeName;
		ControlTgt[0][T100_OkuriMsRt.ColDeliveryTypeCd02] 	= TB_DeliveryTypeCd02;
		ControlTgt[0][T100_OkuriMsRt.ColDeliTypeName02] 		= TB_DeliTypeName02;
		ControlTgt[0][T100_OkuriMsRt.ColDeliveryTypeCd03] 	= TB_DeliveryTypeCd03;
		ControlTgt[0][T100_OkuriMsRt.ColDeliTypeName03] 		= TB_DeliTypeName03;
		ControlTgt[0][T100_OkuriMsRt.ColDeliveryTypeCd04] 	= TB_DeliveryTypeCd04;
		ControlTgt[0][T100_OkuriMsRt.ColDeliTypeName04] 		= TB_DeliTypeName04;
		ControlTgt[0][T100_OkuriMsRt.ColDeliveryTypeCd05] 	= TB_DeliveryTypeCd05;
		ControlTgt[0][T100_OkuriMsRt.ColDeliTypeName05] 		= TB_DeliTypeName05;

		ControlTgt[0][T100_OkuriMsRt.ColCodFG] 				= TB_CodFG;
		ControlTgt[0][T100_OkuriMsRt.ColCodPayTotal] 			= TB_CodPayTotal;
		ControlTgt[0][T100_OkuriMsRt.ColCodPay] 				= TB_CodPay;
		ControlTgt[0][T100_OkuriMsRt.ColCodConsumptionTax] 	= TB_CodConsumptionTax;

		ControlTgt[0][T100_OkuriMsRt.ColChildrenFG] 			= TB_ChildrenFG;
		ControlTgt[0][T100_OkuriMsRt.ColParentOkuriNo] 		= TB_ParentOkuriNo;

		ControlTgt[0][T100_OkuriMsRt.ColNiokuriCd] 			= TB_NiokuriCd;
		ControlTgt[0][T100_OkuriMsRt.ColNiokuriDepartmentCd]	= TB_NiokuriDepartmentCd;
		ControlTgt[0][T100_OkuriMsRt.ColNiokuriName01] 		= TB_NiokuriName01;
		ControlTgt[0][T100_OkuriMsRt.ColNiokuriName02] 		= TB_NiokuriName02;
		ControlTgt[0][T100_OkuriMsRt.ColNiokuriName03] 		= TB_NiokuriName03;
		ControlTgt[0][T100_OkuriMsRt.ColNiokuriPost] 			= TB_NiokuriPost;
		ControlTgt[0][T100_OkuriMsRt.ColNiokuriAdd01] 		= TB_NiokuriAdd01;
		ControlTgt[0][T100_OkuriMsRt.ColNiokuriAdd02] 		= TB_NiokuriAdd02;
		ControlTgt[0][T100_OkuriMsRt.ColNiokuriAdd03] 		= TB_NiokuriAdd03;
		ControlTgt[0][T100_OkuriMsRt.ColNioKuriTel] 			= TB_NioKuriTel;
		ControlTgt[0][T100_OkuriMsRt.ColNioKuriFax] 			= TB_NioKuriFax;
		ControlTgt[0][T100_OkuriMsRt.ColNioKuriMail] 			= TB_NioKuriMail;
		ControlTgt[0][T100_OkuriMsRt.ColNiokuriMunicCd] 		= TB_NiokuriMunicCd;

		ControlTgt[0][T100_OkuriMsRt.ColDeliCd] 				= TB_DeliCd;
		ControlTgt[0][T100_OkuriMsRt.ColClDeliCd] 				= TB_ClDeliCd;
		ControlTgt[0][T100_OkuriMsRt.ColDeliDepartmentCd] 	= TB_DeliDepartmentCd;
		ControlTgt[0][T100_OkuriMsRt.ColDeliName01] 			= TB_DeliName01;
		ControlTgt[0][T100_OkuriMsRt.ColDeliName02]		 	= TB_DeliName02;
		ControlTgt[0][T100_OkuriMsRt.ColDeliName03] 			= TB_DeliName03;
		ControlTgt[0][T100_OkuriMsRt.ColDeliPost] 				= TB_DeliPost;
		ControlTgt[0][T100_OkuriMsRt.ColDeliAdd01] 			= TB_DeliAdd01;
		ControlTgt[0][T100_OkuriMsRt.ColDeliAdd02] 			= TB_DeliAdd02;
		ControlTgt[0][T100_OkuriMsRt.ColDeliAdd03] 			= TB_DeliAdd03;
		ControlTgt[0][T100_OkuriMsRt.ColDeliTel] 				= TB_DeliTel;
		ControlTgt[0][T100_OkuriMsRt.ColDeliFax] 				= TB_DeliFax;
		ControlTgt[0][T100_OkuriMsRt.ColDeliMail] 				= TB_DeliMail;
		ControlTgt[0][T100_OkuriMsRt.ColDeliMunicCd] 			= TB_DeliMunicCd;

		ControlTgt[0][T100_OkuriMsRt.ColCom01] 				= TB_Com01;
		ControlTgt[0][T100_OkuriMsRt.ColCom02] 				= TB_Com02;
		ControlTgt[0][T100_OkuriMsRt.ColCom03] 				= TB_Com03;
		ControlTgt[0][T100_OkuriMsRt.ColCom04] 				= TB_Com04;
		ControlTgt[0][T100_OkuriMsRt.ColCom05] 				= TB_Com05;

		ControlTgt[0][T100_OkuriMsRt.ColStatus] 				= TB_Status;
		ControlTgt[0][T100_OkuriMsRt.ColTaxFg] 				= TB_TaxFg;
		ControlTgt[0][T100_OkuriMsRt.ColTaxRate] 				= TB_TaxRate;
		ControlTgt[0][T100_OkuriMsRt.ColDeliFee] 				= TB_DeliFee;
		ControlTgt[0][T100_OkuriMsRt.ColAddDeliFee01] 		= TB_AddDeliFee01;
		ControlTgt[0][T100_OkuriMsRt.ColAddDeliFee02] 		= TB_AddDeliFee02;
		ControlTgt[0][T100_OkuriMsRt.ColAddDeliFee03] 		= TB_AddDeliFee03;
		ControlTgt[0][T100_OkuriMsRt.ColHaighWayFee01] 		= TB_HaighWayFee01;
		ControlTgt[0][T100_OkuriMsRt.ColHaighWayFee02] 		= TB_HaighWayFee02;
		ControlTgt[0][T100_OkuriMsRt.ColConsumptionTax] 		= TB_ConsumptionTax;
		ControlTgt[0][T100_OkuriMsRt.ColWithOutTaxTotal] 	= TB_WithOutTaxTotal;
		ControlTgt[0][T100_OkuriMsRt.ColTotalFee] 				= TB_TotalFee;
		ControlTgt[0][T100_OkuriMsRt.ColFeeFixFG] 				= TB_FeeFixFG;
		ControlTgt[0][T100_OkuriMsRt.ColFeeFixDate] 			= TB_FeeFixDate;
		ControlTgt[0][T100_OkuriMsRt.ColReceiptStampFG] 		= TB_ReceiptStampFG;
		ControlTgt[0][T100_OkuriMsRt.ColReceiptStampDate] 	= TB_ReceiptStampDate;
		ControlTgt[0][T100_OkuriMsRt.ColInvoiceStatus] 		= TB_InvoiceStatus;
		ControlTgt[0][T100_OkuriMsRt.ColEntryDate] 			= TB_EntryDate;
		ControlTgt[0][T100_OkuriMsRt.ColUpdateDate] 			= TB_UpdateDate;
		ControlTgt[0][T100_OkuriMsRt.ColEntryUser] 			= TB_EntryUser;
		ControlTgt[0][T100_OkuriMsRt.ColUpdateUser] 			= TB_UpdateUser;
		ControlTgt[0][T100_OkuriMsRt.ColEntryPG] 				= TB_EntryPG;
		ControlTgt[0][T100_OkuriMsRt.ColUpdatePG] 				= TB_UpdatePG;

		ControlTgt[0][T100_OkuriMsRt.ColUseFeeBasePtCd] 		= TB_UseFeeBasePtCd;
		ControlTgt[0][T100_OkuriMsRt.ColWmsStatus] 			= TB_WmsStatus;
		ControlTgt[0][T100_OkuriMsRt.ColWmsShipDate] 			= TB_WmsShipDate;
		ControlTgt[0][T100_OkuriMsRt.ColCourseGpCd] 			= TB_CourseGpCd;
		ControlTgt[0][T100_OkuriMsRt.ColCourseCD] 				= TB_CourseCD;
		ControlTgt[0][T100_OkuriMsRt.ColCourseCDEda] 			= TB_CourseCDEda;
		ControlTgt[0][T100_OkuriMsRt.ColPitGrp] 				= TB_PitGrp;
		ControlTgt[0][T100_OkuriMsRt.ColPit01] 				= TB_Pit01;
		ControlTgt[0][T100_OkuriMsRt.ColPit02] 				= TB_Pit02;
		ControlTgt[0][T100_OkuriMsRt.ColPit03] 				= TB_Pit03;
		ControlTgt[0][T100_OkuriMsRt.ColPit04] 				= TB_Pit04;
		ControlTgt[0][T100_OkuriMsRt.ColPit05] 				= TB_Pit05;

		ControlTgt[0][T100_OkuriMsRt.ColCLName01] 				= TB_CLName01;
		ControlTgt[0][T100_OkuriMsRt.ColClGpCD] 				= TB_ClGpCD;
		ControlTgt[0][T100_OkuriMsRt.ColCLGpName01] 			= TB_CLGpName01;
		
		ControlTgt[0][T100_OkuriMsRt.ColMsClCd] 				= TB_MsClCd;
		ControlTgt[0][T100_OkuriMsRt.ColMsInvoiceWhCd] 		= TB_MsInvoiceWhCd;
		ControlTgt[0][T100_OkuriMsRt.ColMsOkuriNo] 			= TB_MsOkuriNo;
		ControlTgt[0][T100_OkuriMsRt.ColMsNo] 					= TB_MsNo;
		ControlTgt[0][T100_OkuriMsRt.ColMsDeliNo] 				= TB_MsDeliNo;
		ControlTgt[0][T100_OkuriMsRt.ColMsDelliMsNo] 			= TB_MsDelliMsNo;
		ControlTgt[0][T100_OkuriMsRt.ColMsClOrderNo] 			= TB_MsClOrderNo;
		ControlTgt[0][T100_OkuriMsRt.ColMsClGpCd] 				= TB_MsClGpCd;
		ControlTgt[0][T100_OkuriMsRt.ColMsItemCd] 				= TB_MsItemCd;
		ControlTgt[0][T100_OkuriMsRt.ColMsItemName01] 		= TB_MsItemName01;
		ControlTgt[0][T100_OkuriMsRt.ColMsItemName02] 		= TB_MsItemName02;
		ControlTgt[0][T100_OkuriMsRt.ColMsItemName03] 		= TB_MsItemName03;
		ControlTgt[0][T100_OkuriMsRt.ColMsUnitWeight] 		= TB_MsUnitWeight;
		ControlTgt[0][T100_OkuriMsRt.ColMsUnitSize] 			= TB_MsUnitSize;
		ControlTgt[0][T100_OkuriMsRt.ColMsQty] 				= TB_MsQty;
		ControlTgt[0][T100_OkuriMsRt.ColMsPackingQty] 		= TB_MsPackingQty;
		ControlTgt[0][T100_OkuriMsRt.ColMsUnitName] 			= TB_MsUnitName;
		ControlTgt[0][T100_OkuriMsRt.ColMsSubTotalWeight]	= TB_MsSubTotalWeight;
		ControlTgt[0][T100_OkuriMsRt.ColMsSubTotalSize] 		= TB_MsSubTotalSize;
		ControlTgt[0][T100_OkuriMsRt.ColMsUnitPrice] 			= TB_MsUnitPrice;
		ControlTgt[0][T100_OkuriMsRt.ColMsSubTotalPrice] 	= TB_MsSubTotalPrice;
		ControlTgt[0][T100_OkuriMsRt.ColMsCategoryCd] 		= TB_MsCategoryCd;
		ControlTgt[0][T100_OkuriMsRt.ColMsCategoryName] 		= TB_MsCategoryName;
		ControlTgt[0][T100_OkuriMsRt.ColMsTildFG] 				= TB_MsTildFG;
		ControlTgt[0][T100_OkuriMsRt.ColMsTildName] 			= TB_MsTildName;
		ControlTgt[0][T100_OkuriMsRt.ColMsCom01] 				= TB_MsCom01;
		ControlTgt[0][T100_OkuriMsRt.ColMsCom02] 				= TB_MsCom02;
		ControlTgt[0][T100_OkuriMsRt.ColMsCom03] 				= TB_MsCom03;
		ControlTgt[0][T100_OkuriMsRt.ColMsCom04] 				= TB_MsCom04;
		ControlTgt[0][T100_OkuriMsRt.ColMsCom05] 				= TB_MsCom05;
		ControlTgt[0][T100_OkuriMsRt.ColMsEntryDate] 			= TB_MsEntryDate;
		ControlTgt[0][T100_OkuriMsRt.ColMsUpdateDate] 		= TB_MsUpdateDate;
		ControlTgt[0][T100_OkuriMsRt.ColMsEntryUser] 			= TB_MsEntryUser;
		ControlTgt[0][T100_OkuriMsRt.ColMsUpdateUser] 		= TB_MsUpdateUser;
		ControlTgt[0][T100_OkuriMsRt.ColMsLot] 				= TB_MsLot;
		ControlTgt[0][T100_OkuriMsRt.ColMsExpDate] 			= TB_MsExpDate;
		ControlTgt[0][T100_OkuriMsRt.ColMsPackingType] 		= TB_MsPackingType;
		ControlTgt[0][T100_OkuriMsRt.ColMsClItemCd] 			= TB_MsClItemCd;
		ControlTgt[0][T100_OkuriMsRt.ColMsItemMDNo] 			= TB_MsItemMDNo;
		ControlTgt[0][T100_OkuriMsRt.ColMsJanCd] 				= TB_MsJanCd;
		
		for(int i=0;i<ControlTgt[1].length;i++) {
			ControlTgt[1][i]	 = "JTextField";
			ControlTgt[2][i]	 = null;
			ControlTgt[3][i]	 = -1;
			
		}
		
		ControlTgt[2][T100_OkuriMsRt.ColClCd]	 				=	(String[])B100_DefaultVariable.ClList[1];
		ControlTgt[2][T100_OkuriMsRt.ColInvoiceWhCd]	 		=	(String[])B100_DefaultVariable.WhList[1];
		ControlTgt[2][T100_OkuriMsRt.ColPickupWhCd]			=	(String[])B100_DefaultVariable.WhList[1];
		ControlTgt[2][T100_OkuriMsRt.ColPurposeFG] 			=	(String[])B100_DefaultVariable.PurposeList[1];
		ControlTgt[2][T100_OkuriMsRt.ColDeliveryTypeCd01]	=	(String[])B100_DefaultVariable.DeliveryType01[1];
		ControlTgt[2][T100_OkuriMsRt.ColDeliveryTypeCd02]	=	(String[])B100_DefaultVariable.DeliveryType02[1];
		ControlTgt[2][T100_OkuriMsRt.ColDeliveryTypeCd03]	=	(String[])B100_DefaultVariable.DeliveryType03[1];
		ControlTgt[2][T100_OkuriMsRt.ColDeliveryTypeCd04]	=	(String[])B100_DefaultVariable.DeliveryType04[1];
		ControlTgt[2][T100_OkuriMsRt.ColDeliveryTypeCd05]	=	(String[])B100_DefaultVariable.DeliveryType05[1];
		ControlTgt[2][T100_OkuriMsRt.ColChildrenFG]			=	(String[])B100_DefaultVariable.ChildrenFGList[1];
		ControlTgt[2][T100_OkuriMsRt.ColCodFG]					=	(String[])B100_DefaultVariable.CODList[1];
		ControlTgt[2][T100_OkuriMsRt.ColStatus] 				=	(String[])B100_DefaultVariable.StatusList[1];
		ControlTgt[2][T100_OkuriMsRt.ColTaxFg] 				=	(String[])B100_DefaultVariable.TaxFgList[1];
		ControlTgt[2][T100_OkuriMsRt.ColFeeFixFG] 				=	(String[])B100_DefaultVariable.FeeFixFgList[1];
		ControlTgt[2][T100_OkuriMsRt.ColInvoiceStatus] 		=	(String[])B100_DefaultVariable.InvoiceStatusList[1];
		ControlTgt[2][T100_OkuriMsRt.ColWmsStatus] 			=	(String[])B100_DefaultVariable.WmsStatusList[1];
		ControlTgt[2][T100_OkuriMsRt.ColReceiptStampFG] 		=	(String[])B100_DefaultVariable.ReceiptStampFGList[1];
		
		ControlTgt[2][T100_OkuriMsRt.ColMsClCd] 				=	(String[])B100_DefaultVariable.ClList[1];
		ControlTgt[2][T100_OkuriMsRt.ColMsInvoiceWhCd] 		=	(String[])B100_DefaultVariable.WhList[1];
		ControlTgt[2][T100_OkuriMsRt.ColMsClGpCd]				=	(String[])B100_DefaultVariable.ClGpList[1];
		ControlTgt[2][T100_OkuriMsRt.ColMsTildFG]				=	(String[])B100_DefaultVariable.TildFG[1];
		ControlTgt[2][T100_OkuriMsRt.ColMsPackingType]		=	(String[])B100_DefaultVariable.UnitTypeList[1];
		ControlTgt[2][T100_OkuriMsRt.ColClGpCD]				=	(String[])B100_DefaultVariable.ClGpList[1];
		
		ControlTgt[2][T100_OkuriMsRt.ColPlanDate]				=	"YYYY/MM/DD";
		ControlTgt[2][T100_OkuriMsRt.ColShipDate]				=	"YYYY/MM/DD";
		ControlTgt[2][T100_OkuriMsRt.ColSPPlanDate]			=	"YYYY/MM/DD";
		ControlTgt[2][T100_OkuriMsRt.ColSPDate]				=	"YYYY/MM/DD";
		ControlTgt[2][T100_OkuriMsRt.ColSPTimeStr]			=	"HH:MM";
		ControlTgt[2][T100_OkuriMsRt.ColSPTimeEnd]			=	"HH:MM";
		ControlTgt[2][T100_OkuriMsRt.ColCodPayTotal]			=	"#,###";
		ControlTgt[2][T100_OkuriMsRt.ColCodPay]				=	"#,###";
		ControlTgt[2][T100_OkuriMsRt.ColCodConsumptionTax]	=	"#,###";
		ControlTgt[2][T100_OkuriMsRt.ColTotalWeight]			=	"#,###.##";
		ControlTgt[2][T100_OkuriMsRt.ColTotalSize]			=	"#,###.##";
		ControlTgt[2][T100_OkuriMsRt.ColTotalQty]				=	"#,###";
		ControlTgt[2][T100_OkuriMsRt.ColTaxRate]				=	"#,###";
		ControlTgt[2][T100_OkuriMsRt.ColDeliFee]				=	"#,###";
		ControlTgt[2][T100_OkuriMsRt.ColAddDeliFee01]			=	"#,###";
		ControlTgt[2][T100_OkuriMsRt.ColAddDeliFee02]			=	"#,###";
		ControlTgt[2][T100_OkuriMsRt.ColAddDeliFee03]			=	"#,###";
		ControlTgt[2][T100_OkuriMsRt.ColHaighWayFee01]		=	"#,###";
		ControlTgt[2][T100_OkuriMsRt.ColHaighWayFee02]		=	"#,###";
		ControlTgt[2][T100_OkuriMsRt.ColConsumptionTax]		=	"#,###";
		ControlTgt[2][T100_OkuriMsRt.ColWithOutTaxTotal]		=	"#,###";
		ControlTgt[2][T100_OkuriMsRt.ColTotalFee]				=	"#,###";
		ControlTgt[2][T100_OkuriMsRt.ColFeeFixDate]			=	"YYYY/MM/DD";
		ControlTgt[2][T100_OkuriMsRt.ColReceiptStampDate]	=	"YYYY/MM/DD";
		ControlTgt[2][T100_OkuriMsRt.ColEntryDate]			=	"YYYY/MM/DD HH:MM:SS";
		ControlTgt[2][T100_OkuriMsRt.ColUpdateDate]			=	"YYYY/MM/DD HH:MM:SS";
		ControlTgt[2][T100_OkuriMsRt.ColWmsShipDate]			=	"YYYY/MM/DD";
		
		ControlTgt[2][T100_OkuriMsRt.ColMsNo]					=	"#,###";
		ControlTgt[2][T100_OkuriMsRt.ColMsDelliMsNo]			=	"#,###";
		ControlTgt[2][T100_OkuriMsRt.ColMsQty]					=	"#,###";
		ControlTgt[2][T100_OkuriMsRt.ColMsExpDate]			=	"YYYY/MM/DD";
		ControlTgt[2][T100_OkuriMsRt.ColMsSubTotalWeight]	=	"#,###.##";	
		ControlTgt[2][T100_OkuriMsRt.ColMsSubTotalSize]		=	"#,###.##";
		ControlTgt[2][T100_OkuriMsRt.ColMsPackingQty]			=	"#,###";
		
		ControlTgt[2][T100_OkuriMsRt.ColMsUnitPrice]			=	"#,###.##";
		ControlTgt[2][T100_OkuriMsRt.ColMsSubTotalPrice]		=	"#,###.##";
		ControlTgt[2][T100_OkuriMsRt.ColMsEntryDate]			=	"YYYY/MM/DD HH:MM:SS";
		ControlTgt[2][T100_OkuriMsRt.ColMsUpdateDate]			=	"YYYY/MM/DD HH:MM:SS";
		ControlTgt[2][T100_OkuriMsRt.ColMsUnitWeight]			=	"#,###.##";
		ControlTgt[2][T100_OkuriMsRt.ColMsUnitSize]			=	"#,###.##";
		
		for(int i=0;i<ControlTgt[0].length;i++) {
			if(ControlTgt[0][i] instanceof JTextField) {
				ControlTgt[1][T100_OkuriMsRt.ColEntryDate] 			= "JTextField";
				ControlTgt[3][i] = ((JTextField)ControlTgt[0][i]).getText();
			}
			if(ControlTgt[0][i] instanceof JFormattedTextField) {
				ControlTgt[1][T100_OkuriMsRt.ColEntryDate] 			= "JFormattedTextField";
				ControlTgt[3][i] = ((JFormattedTextField)ControlTgt[0][i]).getText();
				//System.out.println(i+":"+ControlTgt[2][i]);
			}
			if(ControlTgt[0][i] instanceof JComboBox) {
				ControlTgt[1][T100_OkuriMsRt.ColEntryDate] 			= "JComboBox";
				//System.out.println(i+":"+ControlTgt[2][i]);
				ControlTgt[3][i] = ((JComboBox)ControlTgt[0][i]).getSelectedIndex();
			}
		}
		ControlTgt[4][T100_OkuriMsRt.ColClCd] 					= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColInvoiceWhCd]	 		= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColOkuriNo] 				= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColClDeliNo] 				= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColPickupWhCd] 			= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColPurposeFG] 			= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColPlanDate] 				= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColShipDate] 				= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColSPPlanDate] 			= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColSPDate] 				= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColSPTimeFG] 				= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColSPTimeStr]			= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColSPTimeEnd] 			= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColTotalWeight] 			= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColTotalSize] 			= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColTotalQty] 				= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColDeliveryTypeCd01] 	= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColDeliTypeName] 		= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColDeliveryTypeCd02] 	= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColDeliTypeName02] 		= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColDeliveryTypeCd03] 	= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColDeliTypeName03] 		= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColDeliveryTypeCd04] 	= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColDeliTypeName04] 		= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColDeliveryTypeCd05] 	= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColDeliTypeName05] 		= "Hd";

		ControlTgt[4][T100_OkuriMsRt.ColCodFG] 				= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColCodPayTotal] 			= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColCodPay] 				= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColCodConsumptionTax] 	= "Hd";

		ControlTgt[4][T100_OkuriMsRt.ColChildrenFG] 			= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColParentOkuriNo] 		= "Hd";

		ControlTgt[4][T100_OkuriMsRt.ColNiokuriCd] 			= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColNiokuriDepartmentCd]	= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColNiokuriName01] 		= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColNiokuriName02] 		= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColNiokuriName03] 		= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColNiokuriPost] 			= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColNiokuriAdd01] 		= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColNiokuriAdd02] 		= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColNiokuriAdd03] 		= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColNioKuriTel] 			= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColNioKuriFax] 			= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColNioKuriMail] 			= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColNiokuriMunicCd] 		= "Hd";

		ControlTgt[4][T100_OkuriMsRt.ColDeliCd] 				= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColClDeliCd] 				= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColDeliDepartmentCd] 	= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColDeliName01] 			= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColDeliName02]		 	= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColDeliName03] 			= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColDeliPost] 				= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColDeliAdd01] 			= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColDeliAdd02] 			= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColDeliAdd03] 			= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColDeliTel] 				= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColDeliFax] 				= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColDeliMail] 				= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColDeliMunicCd] 			= "Hd";

		ControlTgt[4][T100_OkuriMsRt.ColCom01] 				= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColCom02] 				= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColCom03] 				= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColCom04] 				= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColCom05] 				= "Hd";

		ControlTgt[4][T100_OkuriMsRt.ColStatus] 				= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColTaxFg] 				= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColTaxRate] 				= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColDeliFee] 				= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColAddDeliFee01] 		= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColAddDeliFee02] 		= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColAddDeliFee03] 		= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColHaighWayFee01] 		= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColHaighWayFee02] 		= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColConsumptionTax] 		= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColWithOutTaxTotal] 	= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColTotalFee] 				= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColFeeFixFG] 				= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColFeeFixDate] 			= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColReceiptStampFG] 		= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColReceiptStampDate] 	= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColInvoiceStatus] 		= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColEntryDate] 			= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColUpdateDate] 			= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColEntryUser] 			= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColUpdateUser] 			= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColEntryPG] 				= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColUpdatePG] 				= "Hd";

		ControlTgt[4][T100_OkuriMsRt.ColUseFeeBasePtCd] 		= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColWmsStatus] 			= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColWmsShipDate] 			= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColCourseGpCd] 			= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColCourseCD] 				= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColCourseCDEda] 			= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColPitGrp] 				= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColPit01] 				= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColPit02] 				= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColPit03] 				= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColPit04] 				= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColPit05] 				= "Hd";

		ControlTgt[4][T100_OkuriMsRt.ColCLName01] 				= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColClGpCD] 				= "Hd";
		ControlTgt[4][T100_OkuriMsRt.ColCLGpName01] 			= "Hd";
		
		ControlTgt[4][T100_OkuriMsRt.ColMsClCd] 				= "Ms";
		ControlTgt[4][T100_OkuriMsRt.ColMsInvoiceWhCd] 		= "Ms";
		ControlTgt[4][T100_OkuriMsRt.ColMsOkuriNo] 			= "Ms";
		ControlTgt[4][T100_OkuriMsRt.ColMsNo] 					= "Ms";
		ControlTgt[4][T100_OkuriMsRt.ColMsDeliNo] 				= "Ms";
		ControlTgt[4][T100_OkuriMsRt.ColMsDelliMsNo] 			= "Ms";
		ControlTgt[4][T100_OkuriMsRt.ColMsClOrderNo] 			= "Ms";
		ControlTgt[4][T100_OkuriMsRt.ColMsClGpCd] 				= "Ms";
		ControlTgt[4][T100_OkuriMsRt.ColMsItemCd] 				= "Ms";
		ControlTgt[4][T100_OkuriMsRt.ColMsItemName01] 		= "Ms";
		ControlTgt[4][T100_OkuriMsRt.ColMsItemName02] 		= "Ms";
		ControlTgt[4][T100_OkuriMsRt.ColMsItemName03] 		= "Ms";
		ControlTgt[4][T100_OkuriMsRt.ColMsUnitWeight] 		= "Ms";
		ControlTgt[4][T100_OkuriMsRt.ColMsUnitSize] 			= "Ms";
		ControlTgt[4][T100_OkuriMsRt.ColMsQty] 				= "Ms";
		ControlTgt[4][T100_OkuriMsRt.ColMsPackingQty] 		= "Ms";
		ControlTgt[4][T100_OkuriMsRt.ColMsUnitName] 			= "Ms";
		ControlTgt[4][T100_OkuriMsRt.ColMsSubTotalWeight]	= "Ms";
		ControlTgt[4][T100_OkuriMsRt.ColMsSubTotalSize] 		= "Ms";
		ControlTgt[4][T100_OkuriMsRt.ColMsUnitPrice] 			= "Ms";
		ControlTgt[4][T100_OkuriMsRt.ColMsSubTotalPrice] 	= "Ms";
		ControlTgt[4][T100_OkuriMsRt.ColMsCategoryCd] 		= "Ms";
		ControlTgt[4][T100_OkuriMsRt.ColMsCategoryName] 		= "Ms";
		ControlTgt[4][T100_OkuriMsRt.ColMsTildFG] 				= "Ms";
		ControlTgt[4][T100_OkuriMsRt.ColMsTildName] 			= "Ms";
		ControlTgt[4][T100_OkuriMsRt.ColMsCom01] 				= "Ms";
		ControlTgt[4][T100_OkuriMsRt.ColMsCom02] 				= "Ms";
		ControlTgt[4][T100_OkuriMsRt.ColMsCom03] 				= "Ms";
		ControlTgt[4][T100_OkuriMsRt.ColMsCom04] 				= "Ms";
		ControlTgt[4][T100_OkuriMsRt.ColMsCom05] 				= "Ms";
		ControlTgt[4][T100_OkuriMsRt.ColMsEntryDate] 			= "Ms";
		ControlTgt[4][T100_OkuriMsRt.ColMsUpdateDate] 		= "Ms";
		ControlTgt[4][T100_OkuriMsRt.ColMsEntryUser] 			= "Ms";
		ControlTgt[4][T100_OkuriMsRt.ColMsUpdateUser] 		= "Ms";
		ControlTgt[4][T100_OkuriMsRt.ColMsLot] 				= "Ms";
		ControlTgt[4][T100_OkuriMsRt.ColMsExpDate] 			= "Ms";
		ControlTgt[4][T100_OkuriMsRt.ColMsPackingType] 		= "Ms";
		ControlTgt[4][T100_OkuriMsRt.ColMsClItemCd] 			= "Ms";
		ControlTgt[4][T100_OkuriMsRt.ColMsItemMDNo] 			= "Ms";
		ControlTgt[4][T100_OkuriMsRt.ColMsJanCd] 				= "Ms";
		
		Object[][] OkuriMsRt= OkuriMsRt(ClCd,TgtOkuriNo);
		ViewSet(OkuriMsRt,tableModel_msOkuriMs,ControlTgt);
		
		OkuriMs_fm.setVisible(true);
		
		RenewFg	= true;
		
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
					
					ViewSet(OkuriMsRt,tableModel_msOkuriMs,ControlTgt);
					
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
					TableDataSet(tableModel_msOkuriMs,ControlTgt);
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
	
	private static void ViewSet(Object[][] OkuriMsRt,DefaultTableModel tableModel_msOkuriMs,Object[][] ControlTgt) {
		NumberFormat ni = NumberFormat.getNumberInstance();
		
		for(int i=0;i<ControlTgt[0].length;i++) {
			if(ControlTgt[0][i] instanceof JTextField) {
				((JTextField)ControlTgt[0][i]).setText((String)ControlTgt[3][i]);
			}
			if(ControlTgt[0][i] instanceof JFormattedTextField) {
				((JFormattedTextField)ControlTgt[0][i]).setText((String)ControlTgt[3][i]);
			}
			if(ControlTgt[0][i] instanceof JComboBox) {
				((JComboBox)ControlTgt[0][i]).setSelectedIndex((int)ControlTgt[3][i]);
			}
		}
		
		int RowCount = tableModel_msOkuriMs.getRowCount();
		for(int i=0;i<RowCount;i++) {
			tableModel_msOkuriMs.removeRow(0);
		}
		if(0<OkuriMsRt.length) {
			for(int i=0;i<ControlTgt[0].length;i++) {
				if(ControlTgt[0][i] instanceof JTextField) {
					String WST = B100_TextControl.Trim(""+OkuriMsRt[0][i]);
					((JTextField)ControlTgt[0][i]).setText(WST);
				}
				if(ControlTgt[0][i] instanceof JFormattedTextField) {
					String WST = B100_TextControl.Trim(""+OkuriMsRt[0][i]);
					if(null!=ControlTgt[2][i]) {
						switch((String)ControlTgt[2][i]) {
							case "YYYY/MM/DD":
								WST = B100_TextControl.TextToDate(WST);
								break;
							case "#,###":
								WST = ""+ni.format(B100_TextControl.TextToInt(WST));
								break;
							case "#,###.##":
								WST = ""+ni.format(B100_TextControl.TextToFloat(WST));
								break;
							default:
								break;
						}
					}
					((JFormattedTextField)ControlTgt[0][i]).setText(WST);
					
				}
				if(ControlTgt[0][i] instanceof JComboBox) {
					((JComboBox)ControlTgt[0][i]).setSelectedIndex(B100_ArrayListControl.ArryListGetRow((String[])ControlTgt[2][i],""+OkuriMsRt[0][i],true));
				}
			}
		}
		for(int i=0;i<OkuriMsRt.length;i++) {
			Object[] SetOb = new Object[1+OkuriMsRt[i].length];
			if(0==i) {
				SetOb[0]	= true;
			}else {
				SetOb[0]	= false;
			}
			
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
	
	private static void TableDataSet(DefaultTableModel tableModel_msOkuriMs,Object[][] ControlTgt) {
		NumberFormat ni = NumberFormat.getNumberInstance();
		for(int i=0;i<ControlTgt[0].length;i++) {
			if("Ms".equals((String)ControlTgt[4][i])) {
				if(ControlTgt[0][i] instanceof JTextField) {
					((JTextField)ControlTgt[0][i]).setText((String)ControlTgt[3][i]);
				}
				if(ControlTgt[0][i] instanceof JFormattedTextField) {
					((JFormattedTextField)ControlTgt[0][i]).setText((String)ControlTgt[3][i]);
				}
				if(ControlTgt[0][i] instanceof JComboBox) {
					((JComboBox)ControlTgt[0][i]).setSelectedIndex((int)ControlTgt[3][i]);
				}
			}
		}
		int RowCount = tableModel_msOkuriMs.getRowCount();
		for(int i01=0;i01<RowCount;i01++) {
			if((boolean)tableModel_msOkuriMs.getValueAt(i01, 0)) {
				for(int i=0;i<ControlTgt[0].length;i++) {
					if("Ms".equals((String)ControlTgt[4][i])) {
						if(ControlTgt[0][i] instanceof JTextField) {
							String WST = B100_TextControl.Trim(""+tableModel_msOkuriMs.getValueAt(i01, 1+i));
							((JTextField)ControlTgt[0][i]).setText(WST);
						}
						if(ControlTgt[0][i] instanceof JFormattedTextField) {
							String WST = B100_TextControl.Trim(""+tableModel_msOkuriMs.getValueAt(i01, 1+i));
							if(null!=ControlTgt[2][i]) {
								switch((String)ControlTgt[2][i]) {
									case "YYYY/MM/DD":
										WST = B100_TextControl.TextToDate(WST);
										break;
									case "#,###":
										WST = ""+ni.format(B100_TextControl.TextToInt(WST));
										break;
									case "#,###.##":
										WST = ""+ni.format(B100_TextControl.TextToFloat(WST));
										break;
									default:
										break;
								}
							}
							((JFormattedTextField)ControlTgt[0][i]).setText(WST);
							
						}
						if(ControlTgt[0][i] instanceof JComboBox) {
							String WST = B100_TextControl.Trim(""+tableModel_msOkuriMs.getValueAt(i01, 1+i));
							((JComboBox)ControlTgt[0][i]).setSelectedIndex(B100_ArrayListControl.ArryListGetRow((String[])ControlTgt[2][i],WST,true));
						}
					}
				}
			}
		}
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

	private static Object[][] ClMstRt(String TgtClCd){
		ArrayList<String> SearchClGpCD = new ArrayList<String>();
		ArrayList<String> SearchCLCD = new ArrayList<String>();
		ArrayList<String> SearchCLName = new ArrayList<String>();
		ArrayList<String> SearchPost = new ArrayList<String>();
		ArrayList<String> searchAdd = new ArrayList<String>();
		ArrayList<String> SearchTel = new ArrayList<String>();
		ArrayList<String> SearchFax = new ArrayList<String>();
		ArrayList<String> SearchMail = new ArrayList<String>();
		ArrayList<String> SearchCom = new ArrayList<String>();
		ArrayList<String> SearchWHCD = new ArrayList<String>();
		boolean AllSearch = false;
		
		SearchCLCD.add(TgtClCd);
		
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
	
}