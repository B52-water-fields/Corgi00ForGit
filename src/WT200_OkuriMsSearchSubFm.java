import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class WT200_OkuriMsSearchSubFm{
static boolean RenewFg;
	
	static final int RtJFrame				= 0;
	static final int RtDefaultTableModel	= 1;
	static final int RtJTable				= 2;
	static final int EntryBtn				= 3;
	
	
	public static Object[] OkuriMsSearchSubFm(int x,int y,String ClWh,String ClCd,String TgtOkuriNo,String BackGroundColor) {
		RenewFg=false;
		if(null==ClWh) {ClWh="";}
		if(null==ClCd) {ClCd="";}
		if(null==TgtOkuriNo) {TgtOkuriNo="";}
		if("".equals(ClWh)) {ClWh=A00000_Main.ClWh;}
		if("".equals(ClCd)) {ClCd=A00000_Main.ClCd;}
		
		final JFrame OkuriMs_fm 	= B100_FrameParts.FrameCreate(x+10,y+10,800,750,"Corgi00出荷明細検索　WT200_OkuriMsSearchSubFm",BackGroundColor);
		JLabel 	OkuriMsUserinfo 	= B100_FrameParts.UserInfo();
		JButton OkuriMsExit_btn 	= B100_FrameParts.ExitBtn();
		JButton OkuriMsEntry_btn 	= B100_FrameParts.EntryBtn();
		
		OkuriMs_fm.add(OkuriMsUserinfo);
		OkuriMs_fm.add(OkuriMsExit_btn);
		OkuriMs_fm.add(OkuriMsEntry_btn);
		
		Object[][] DefinitionRt = T100_OkuriMsRt.DefinitionRt();
		
		JLabel LB_ClCd				= B100_FrameParts.JLabelSet(		  0, 25,130,20,(String)DefinitionRt[T100_OkuriMsRt.ColSearchClCd][5]		+":"	,11,1);
		JLabel LB_SearchOkuriNo		= B100_FrameParts.JLabelSet(		  0, 50,130,20,(String)DefinitionRt[T100_OkuriMsRt.ColSearchOkuriNo][5]	+":"	,11,1);
		
		final JComboBox TB_ClCd		= B100_FrameParts.JComboBoxSet(				130, 25,300,20,B100_DefaultVariable.ClList[0],11);	//荷主コード
		final JTextField  TB_SearchOkuriNo		= B100_FrameParts.JTextFieldSet(	130, 50,100,20,"",12,0);							//送り状番号
		
		JLabel LB2_SearchOkuriNo		= B100_FrameParts.JLabelSet(	230, 50,100,20,B100_DefaultVariable.SearchExact	,11,0);
		
		OkuriMs_fm.add(LB_ClCd);
		OkuriMs_fm.add(LB_SearchOkuriNo);
		
		OkuriMs_fm.add(TB_ClCd);
		OkuriMs_fm.add(TB_SearchOkuriNo);
		
		OkuriMs_fm.add(LB2_SearchOkuriNo);
		
		TB_ClCd.setSelectedIndex(	B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.ClList[1]		,ClCd ,true) );		//荷主コード
		TB_ClCd.setEnabled(false);
		
		JButton OkuriMsSearchKickBtn			= B100_FrameParts.BtnSet(			330,50, 90,20,"検索",11);
		OkuriMs_fm.add(OkuriMsSearchKickBtn);
		
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
		JScrollPane scpnOkuriMs = B100_FrameParts.JScrollPaneSet(10,225,760,380,tbOkuriMs);
		OkuriMs_fm.add(scpnOkuriMs);
		
		
		OkuriMs_fm.setVisible(true);
		
		RenewFg	= true;
		
		OkuriMsSearchKickBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				int RowCount = tableModel_msOkuriMs.getRowCount();
				for(int i=0;i<RowCount;i++) {
					tableModel_msOkuriMs.removeRow(0);
				}
				
				String TgtClCd		= B100_TextControl.Trim(B100_DefaultVariable.ClList[1][TB_ClCd.getSelectedIndex()]);
				String TgtOkuriNo	= B100_TextControl.Trim(TB_SearchOkuriNo.getText());
				
				Object[][] OkuriMsRt= OkuriMsRt(TgtClCd,TgtOkuriNo);
				
				for(int i=0;i<OkuriMsRt.length;i++) {
					Object[] SetOb = new Object[1+OkuriMsRt[i].length];
					SetOb[0]	= false;
					for(int i01=0;i01<OkuriMsRt[i].length;i01++) {
						SetOb[1+i01]	= OkuriMsRt[i][i01];
					}
					tableModel_msOkuriMs.addRow(SetOb);
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
		
		return OkuriMsRt;
	}
	
	
}