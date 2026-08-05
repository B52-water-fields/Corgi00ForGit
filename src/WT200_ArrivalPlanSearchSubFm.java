import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class WT200_ArrivalPlanSearchSubFm{
	static boolean RenewFg;
	
	static final int RtJFrame				= 0;
	static final int RtDefaultTableModel	= 1;
	static final int RtJTable				= 2;
	static final int EntryBtn				= 3;
	public static Object[] ArrivalPlanSearchSubFm(int x,int y,String ClWh,String ClCd,String BackGroundColor) {
		RenewFg=false;
		if(null==ClWh) {ClWh="";}
		if(null==ClCd) {ClCd="";}
		if("".equals(ClWh)) {ClWh=A00000_Main.ClWh;}
		if("".equals(ClCd)) {ClCd=A00000_Main.ClCd;}
		String now_dtm	= B100_DateTimeControl.dtmString2(B100_DateTimeControl.dtm()[1])[1];
		String now_date	= B100_DateTimeControl.dtmString2(B100_DateTimeControl.dtm()[1])[0];
		
		
		final JFrame ArrivalPlan_fm 	= B100_FrameParts.FrameCreate(x+10,y+10,800,750,"Corgi00未入荷予定検索　WT200_ArrivalPlanSearchSubFm",BackGroundColor);
		JLabel 	ArrivalPlanUserinfo 	= B100_FrameParts.UserInfo();
		JButton ArrivalPlanExit_btn 	= B100_FrameParts.ExitBtn();
		JButton ArrivalPlanEntry_btn 	= B100_FrameParts.EntryBtn();
		
		ArrivalPlan_fm.add(ArrivalPlanUserinfo);
		ArrivalPlan_fm.add(ArrivalPlanExit_btn);
		ArrivalPlan_fm.add(ArrivalPlanEntry_btn);
		
		JLabel LB_WhCd				= B100_FrameParts.JLabelSet(	  0, 50,100,20,"倉庫:"		,11,1);
		JLabel LB_ClCd				= B100_FrameParts.JLabelSet(	  0, 75,100,20,"荷主:"		,11,1);
		JLabel LB_SpCd				= B100_FrameParts.JLabelSet(	  0,100,100,20,"仕入先:"	,11,1);
		
		JLabel LB_PlanDate			= B100_FrameParts.JLabelSet(	  0,125,100,20,"入荷予定日:"	,10,1);
		JLabel LB_ItemCd			= B100_FrameParts.JLabelSet(	  0,150,100,20,"商品CD:"		,10,1);
		JLabel LB_ItemName			= B100_FrameParts.JLabelSet(	  0,175,100,20,"商品名:"		,10,1);
		
		final JComboBox TB_WhCd		= B100_FrameParts.JComboBoxSet(	100, 50,300,20,B100_DefaultVariable.WhList[0],11);
		final JComboBox TB_ClCd		= B100_FrameParts.JComboBoxSet(	100, 75,300,20,B100_DefaultVariable.ClList[0],11);
		final JComboBox TB_SpCd		= B100_FrameParts.JComboBoxSet(	100,100,240,20,B100_DefaultVariable.SearchSupplierList[0],11);
		
		final JFormattedTextField TB_SearchPlanDateStr	= B100_FrameParts.JFormattedTextFieldSet(	100,125,70,20,now_date	,11,0,"YYYY/MM/DD");
		final JFormattedTextField TB_SearchPlanDateEnd	= B100_FrameParts.JFormattedTextFieldSet(	230,125,70,20,now_date	,11,0,"YYYY/MM/DD");
		final JTextField  TB_ItemCd						= B100_FrameParts.JTextFieldSet(				100,150,100,20,"",12,0);
		final JTextField  TB_ItemName					= B100_FrameParts.JTextFieldSet(				100,175,100,20,"",12,0);
		
		//日付進む戻るボタン
		JButton SearchPlanDateStrAfterBtn		= B100_FrameParts.BtnSet(	170,125, 40,10,"▲",6);
		JButton SearchPlanDateStrBeforeBtn		= B100_FrameParts.BtnSet(	170,135, 40,10,"▼",6);
		JButton SearchPlanDateEndAfterBtn		= B100_FrameParts.BtnSet(	300,125, 40,10,"▲",6);
		JButton SearchPlanDateEndBeforeBtn		= B100_FrameParts.BtnSet(	300,135, 40,10,"▼",6);
		
		JLabel LB2_PlanDate			= B100_FrameParts.JLabelSet(	210,100, 20,20,"～"	,10,2);
		JLabel LB2_ItemCd			= B100_FrameParts.JLabelSet(	200,150,100,20,"と一致"		,10,0);
		JLabel LB2_ItemName			= B100_FrameParts.JLabelSet(	200,175,100,20,"を含む"		,10,0);
		
		JButton ArrivalPlanSearchKickBtn	= B100_FrameParts.BtnSet(	300,175, 90,20,"検索",11);
		
		
		TB_WhCd.setSelectedIndex(	B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.WhList[1]		,ClWh ,true) );
		TB_ClCd.setSelectedIndex(	B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.ClList[1]		,ClCd ,true) );
		TB_SpCd.setSelectedIndex(0);
		
		TB_WhCd.setEnabled(false);
		TB_ClCd.setEnabled(false);
		
		ArrivalPlan_fm.add(LB_WhCd);
		ArrivalPlan_fm.add(LB_ClCd);
		
		//日付進む戻るボタン押下時の挙動
		SearchPlanDateStrAfterBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				B100_DateTimeControl.AfterDateSet(TB_SearchPlanDateStr);
			}
		});
		SearchPlanDateStrBeforeBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				B100_DateTimeControl.BeforeDateSet(TB_SearchPlanDateStr);
			}
		});
		SearchPlanDateEndAfterBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				B100_DateTimeControl.AfterDateSet(TB_SearchPlanDateEnd);
			}
		});
		SearchPlanDateEndBeforeBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				B100_DateTimeControl.BeforeDateSet(TB_SearchPlanDateEnd);
			}
		});
		
		ArrivalPlan_fm.add(LB_WhCd);
		ArrivalPlan_fm.add(LB_ClCd);
		ArrivalPlan_fm.add(LB_SpCd);
		
		ArrivalPlan_fm.add(LB_PlanDate);
		ArrivalPlan_fm.add(LB_ItemCd);
		ArrivalPlan_fm.add(LB_ItemName);
		
		
		ArrivalPlan_fm.add(TB_WhCd);
		ArrivalPlan_fm.add(TB_ClCd);
		ArrivalPlan_fm.add(TB_SpCd);
		
		ArrivalPlan_fm.add(TB_SearchPlanDateStr);
		ArrivalPlan_fm.add(TB_SearchPlanDateEnd);
		ArrivalPlan_fm.add(TB_ItemCd);
		ArrivalPlan_fm.add(TB_ItemName);
		
		ArrivalPlan_fm.add(SearchPlanDateStrAfterBtn);
		ArrivalPlan_fm.add(SearchPlanDateStrBeforeBtn);
		ArrivalPlan_fm.add(SearchPlanDateEndAfterBtn);
		ArrivalPlan_fm.add(SearchPlanDateEndBeforeBtn);
		
		ArrivalPlan_fm.add(LB2_PlanDate);
		ArrivalPlan_fm.add(LB2_ItemCd);
		ArrivalPlan_fm.add(LB2_ItemName);
		
		ArrivalPlan_fm.add(ArrivalPlanSearchKickBtn);
		
		Object[][] RtArrivalPlanHdRt = T100_ArrivalPlanHdRt.RtArrivalPlanHdRt();
		
		String[] columnNamesArrivalPlan = new String[RtArrivalPlanHdRt.length+1];
		
		columnNamesArrivalPlan[0] = "Fg";
		for(int i=0;i<RtArrivalPlanHdRt.length;i++) {
			columnNamesArrivalPlan[1+(int)RtArrivalPlanHdRt[i][1]] = ""+RtArrivalPlanHdRt[i][3];
		}
		
		//編集可能カラム1列目のみ
		final DefaultTableModel tableModel_msArrivalPlan = new B100_TableControl.MyTableModel00(columnNamesArrivalPlan,0);
		
		final JTable tbArrivalPlan = new JTable(tableModel_msArrivalPlan);
		tbArrivalPlan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tbArrivalPlan.setRowHeight(20*A00000_Main.Mul/A00000_Main.Div);
		tbArrivalPlan.setFont(new Font(A00000_Main.DefaultFont, Font.PLAIN, 12*A00000_Main.Mul/A00000_Main.Div));
		
		DefaultTableColumnModel columnModelArrivalPlan
		= (DefaultTableColumnModel)tbArrivalPlan.getColumnModel();
		
		//列幅初期設定 表示位置設定
		TableColumn column = null;
		
		column = columnModelArrivalPlan.getColumn( 0);	column.setPreferredWidth( 30*A00000_Main.Mul/A00000_Main.Div);	//FG
		
		for(int i=0;i<RtArrivalPlanHdRt.length;i++) {
			if("int".equals((String)RtArrivalPlanHdRt[i][2])||"float".equals((String)RtArrivalPlanHdRt[i][2])) {
				column = columnModelArrivalPlan.getColumn(1+(int)RtArrivalPlanHdRt[i][1]);	column.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.rightCellRenderer());
			}else {
				column = columnModelArrivalPlan.getColumn(1+(int)RtArrivalPlanHdRt[i][1]);	column.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.leftCellRenderer());
			}
		}
		
		//スクロール用設定
		JScrollPane scpnArrivalPlan = B100_FrameParts.JScrollPaneSet(10,225,760,380,tbArrivalPlan);
		ArrivalPlan_fm.add(scpnArrivalPlan);
		
		RenewFg=true;
		
		//検索ボタン押下時の挙動
		ArrivalPlanSearchKickBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				int RowCount = tableModel_msArrivalPlan.getRowCount();
				for(int i=0;i<RowCount;i++) {
					tableModel_msArrivalPlan.removeRow(0);
				}
				
				String SearchTgtClCd	= B100_TextControl.Trim(B100_DefaultVariable.ClList[1][TB_ClCd.getSelectedIndex()]);
				String SearchTgtWhCd	= B100_TextControl.Trim(B100_DefaultVariable.WhList[1][TB_WhCd.getSelectedIndex()]);
				String SearchTgtSpCd	= B100_TextControl.Trim(B100_DefaultVariable.SearchSupplierList[1][TB_SpCd.getSelectedIndex()]);
				
				String SearchTgtPlanDateStr		= B100_TextControl.TextToDate(TB_SearchPlanDateStr.getText());
				String SearchTgtPlanDateEnd		= B100_TextControl.TextToDate(TB_SearchPlanDateEnd.getText());
				String SearchTgtItemCd			= B100_TextControl.Trim(TB_ItemCd.getText());
				String SearchTgtItemName		= B100_TextControl.Trim(TB_ItemName.getText());
				
				ArrayList<String> SearchClWh 			= new ArrayList<String>();		//ヘッダ担当倉庫
				ArrayList<String> SearchClCd 			= new ArrayList<String>();		//ヘッダ荷主CD
				ArrayList<String> SearchCLName01 		= new ArrayList<String>();		//ヘッダ荷主名
				ArrayList<String> SearchClGpCD 			= new ArrayList<String>();		//ヘッダ荷主グループCD
				ArrayList<String> SearchCLGpName01 		= new ArrayList<String>();		//ヘッダ荷主グループ標記名
				ArrayList<String> SearchArrNo 			= new ArrayList<String>();		//ヘッダ入荷予定NO
				ArrayList<String> SearchClArrNo 		= new ArrayList<String>();		//ヘッダ荷主予定番号
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
				
				SearchFixFg.add(0);
				SearchFixFg.add(2);
				
				if(!"".equals(SearchTgtClCd)){SearchClCd.add(SearchTgtClCd);}
				if(!"".equals(SearchTgtWhCd)){SearchClWh.add(SearchTgtWhCd);}
				if(!"".equals(SearchTgtSpCd)){SearchSpCd.add(SearchTgtSpCd);}
				
				if(!"".equals(SearchTgtPlanDateStr)){SearchPlanDateMin.add(SearchTgtPlanDateStr);}
				if(!"".equals(SearchTgtPlanDateEnd)){SearchPlanDateMax.add(SearchTgtPlanDateEnd);}
				if(!"".equals(SearchTgtItemCd)){SearchItemCd.add(SearchTgtItemCd);}
				if(!"".equals(SearchTgtItemName)){SearchItemName.add(SearchTgtItemName);}
				
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
				
				for(int i=0;i<ArrivalPlanHdRt.length;i++) {
					Object[] SetOb = new Object[ArrivalPlanHdRt[i].length+1];
					SetOb[0] = false;
					for(int i01=0;i01<ArrivalPlanHdRt[i].length;i01++) {
						SetOb[i01+1] = ""+ArrivalPlanHdRt[i][i01];
					}
					tableModel_msArrivalPlan.addRow(SetOb);
				}
			}
		});
		
		//入荷予定検索チェックボックス操作時の挙動
		tableModel_msArrivalPlan.addTableModelListener(new TableModelListener(){
			public void tableChanged(TableModelEvent e){
				if(RenewFg) {
					RenewFg = false;
					int row_count = tbArrivalPlan.getRowCount();
					Boolean setBL=Boolean.valueOf(false);
					for(int i=0;i<row_count;i++){
						if(i!=e.getFirstRow()){
							tableModel_msArrivalPlan.setValueAt(setBL, i, 0);
						}else {
	
						}
					}
					RenewFg = true;
				}
			}
		});
		
		//在庫検索EXITボタン押下時の挙動
		ArrivalPlanExit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				ArrivalPlan_fm.setVisible(false);
			}
		});
		
		Object[] Rt = {
				ArrivalPlan_fm
				,tableModel_msArrivalPlan
				,tbArrivalPlan
				,ArrivalPlanEntry_btn
				};
		return Rt;
	}
}