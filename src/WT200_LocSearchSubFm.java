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
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class WT200_LocSearchSubFm{
	//検索子画面としてロケマスタ検索する
	//業務系の画面から召喚される
	
	static boolean RenewFg;
	
	static final int RtJFrame				= 0;
	static final int RtDefaultTableModel	= 1;
	static final int RtJTable				= 2;
	static final int EntryBtn				= 3;
	
	public static Object[] LocSearchSubFm(int x,int y,String ClWh,String ClCd,String BackGroundColor){
		RenewFg=false;
		if(null==ClWh) {ClWh="";}
		if(null==ClCd) {ClCd="";}
		if("".equals(ClWh)) {ClWh=A00000_Main.ClWh;}
		if("".equals(ClCd)) {ClCd=A00000_Main.ClCd;}
		
		final JFrame Loc_fm 	= B100_FrameParts.FrameCreate(x+10,y+10,800,750,"Corgi00ロケーション検索　WT200_LocSearchSubFm",BackGroundColor);
		JLabel 	LocUserinfo 	= B100_FrameParts.UserInfo();
		JButton LocExit_btn 	= B100_FrameParts.ExitBtn();
		JButton LocEntry_btn 	= B100_FrameParts.EntryBtn();
		
		Loc_fm.add(LocUserinfo);
		Loc_fm.add(LocExit_btn);
		Loc_fm.add(LocEntry_btn);
		
		JLabel LB_WhCd				= B100_FrameParts.JLabelSet(	 0, 50,100,20,"倉庫:"				,11,1);
		JLabel LB_ClCd				= B100_FrameParts.JLabelSet(	 0, 75,100,20,"荷主:"				,11,1);
		JLabel LB_SearchLoc			= B100_FrameParts.JLabelSet(	 0,100,130,20,"ロケーション:"		,11,1);
		JLabel LB_SearchLocName		= B100_FrameParts.JLabelSet(	 0,125,130,20,"ロケーション名:"	,11,1);
		JLabel LB_SearchType		= B100_FrameParts.JLabelSet(	 0,150,130,20,"ロケタイプ:"		,11,1);
		
		final JComboBox TB_WhCd		= B100_FrameParts.JComboBoxSet(			100, 50,300,20,B100_DefaultVariable.WhList[0],11);	//倉庫
		final JComboBox TB_ClCd		= B100_FrameParts.JComboBoxSet(			100, 75,300,20,B100_DefaultVariable.ClList[0],11);	//荷主
		
		final JTextField  TB_SearchLoc		= B100_FrameParts.JTextFieldSet(	130,100,100,20,"",12,0);									//ロケーション
		final JTextField  TB_SearchLocName	= B100_FrameParts.JTextFieldSet(	130,125,100,20,"",12,0);									//ロケーション名
		final JComboBox   TB_SearchType		= B100_FrameParts.JComboBoxSet( 	130,150,100,20,B100_DefaultVariable.SearchLocType[0],12);	//ロケタイプ
		
		JLabel LB2_SearchLoc				= B100_FrameParts.JLabelSet(	  	230,100,100,20,"で始まる"	,11,0);
		JLabel LB2_SearchLocName			= B100_FrameParts.JLabelSet(  		230,125,100,20,"を含む"		,11,0);
		
		JButton LocSearchKickBtn			= B100_FrameParts.BtnSet(			130,175, 90,20,"検索",11);
		
		TB_WhCd.setSelectedIndex(	B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.WhList[1]		,ClWh ,true) );		//倉庫コード
		TB_ClCd.setSelectedIndex(	B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.ClList[1]		,ClCd ,true) );		//荷主コード
		
		
		TB_WhCd.setEnabled(false);
		TB_ClCd.setEnabled(false);
		
		Loc_fm.add(LB_ClCd);
		Loc_fm.add(LB_WhCd);
		Loc_fm.add(LB_SearchLoc);
		Loc_fm.add(LB_SearchLocName);
		Loc_fm.add(LB_SearchType);
		Loc_fm.add(TB_ClCd);
		Loc_fm.add(TB_WhCd);
		Loc_fm.add(TB_SearchLoc);
		Loc_fm.add(TB_SearchLocName);
		Loc_fm.add(TB_SearchType);
		Loc_fm.add(LB2_SearchLoc);
		Loc_fm.add(LB2_SearchLocName);
		Loc_fm.add(LocSearchKickBtn);
		
		Object[][] RtSettingLocationMstRt = M100_LocationMstRt.RtLocationMstRt();
		
		String[] columnNamesLoc = new String[RtSettingLocationMstRt.length+1];
		
		columnNamesLoc[0] = "Fg";
		for(int i=0;i<RtSettingLocationMstRt.length;i++) {
			columnNamesLoc[1+(int)RtSettingLocationMstRt[i][1]] = ""+RtSettingLocationMstRt[i][3];
		}
		
		//編集可能カラムの指定
		B100_TableControl.RenewTgt = new int[1];
		B100_TableControl.RenewTgt[0] = 0;

		final DefaultTableModel tableModel_msLoc = new B100_TableControl.MyTableModel01(columnNamesLoc,0);
		
		final JTable tbLoc = new JTable(tableModel_msLoc);
		tbLoc.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tbLoc.setRowHeight(20*A00000_Main.Mul/A00000_Main.Div);
		tbLoc.setFont(new Font(A00000_Main.DefaultFont, Font.PLAIN, 12*A00000_Main.Mul/A00000_Main.Div));
		
		DefaultTableColumnModel columnModelLoc
		= (DefaultTableColumnModel)tbLoc.getColumnModel();
		
		//列幅初期設定 表示位置設定
		TableColumn column = null;
		
		column = columnModelLoc.getColumn( 0);	column.setPreferredWidth( 30*A00000_Main.Mul/A00000_Main.Div);	//FG
		
		for(int i=0;i<RtSettingLocationMstRt.length;i++) {
			if("int".equals((String)RtSettingLocationMstRt[i][2])||"float".equals((String)RtSettingLocationMstRt[i][2])) {
				column = columnModelLoc.getColumn(1+(int)RtSettingLocationMstRt[i][1]);	column.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.rightCellRenderer());
			}else {
				column = columnModelLoc.getColumn(1+(int)RtSettingLocationMstRt[i][1]);	column.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.leftCellRenderer());
			}
		}
		
		//スクロール用設定
		JScrollPane scpnLoc = B100_FrameParts.JScrollPaneSet(10,225,760,380,tbLoc);
		Loc_fm.add(scpnLoc);
		
		RenewFg=true;
		//ロケーションマスタ検索ボタン押下時の挙動
		LocSearchKickBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					int RowCount = tableModel_msLoc.getRowCount();
					for(int i=0;i<RowCount;i++) {
						tableModel_msLoc.removeRow(0);
					}
					String SearchTgtClCd			= B100_TextControl.Trim(B100_DefaultVariable.ClList[1][TB_ClCd.getSelectedIndex()]);	//荷主コード
					String SearchTgtWhCd			= B100_TextControl.Trim(B100_DefaultVariable.WhList[1][TB_WhCd.getSelectedIndex()]);	//倉庫コード
					String SearchTgtLoc		= B100_TextControl.Trim(TB_SearchLoc.getText());
					String SearchTgtLocName	= B100_TextControl.Trim(TB_SearchLocName.getText());
					String SearchTgtType 	= B100_TextControl.Trim(B100_DefaultVariable.SearchLocType[1][TB_SearchType.getSelectedIndex()]);
					
					Object[][] LocSearch = LocSearch(
												SearchTgtClCd,
												SearchTgtWhCd,
												SearchTgtLoc,
												SearchTgtLocName,
												SearchTgtType
												);
					for(int i=0;i<LocSearch.length;i++) {
						Object[] SetOb = new Object[LocSearch[i].length+1];
						SetOb[0] = false;
						for(int i01=0;i01<LocSearch[i].length;i01++) {
							SetOb[i01+1] = ""+LocSearch[i][i01];
						}
						tableModel_msLoc.addRow(SetOb);
					}
					Loc_fm.setVisible(true);
					RenewFg = true;
				}
			}
		});
		
		//ロケーションマスタ検索チェックボックス操作時の挙動
		tableModel_msLoc.addTableModelListener(new TableModelListener(){
			public void tableChanged(TableModelEvent e){
				if(RenewFg) {
					RenewFg = false;
					int row_count = tbLoc.getRowCount();
					Boolean setBL=Boolean.valueOf(false);
					for(int i=0;i<row_count;i++){
						if(i!=e.getFirstRow()){
							tableModel_msLoc.setValueAt(setBL, i, 0);
						}else {
	
						}
					}
					RenewFg = true;
				}
			}
		});
		
		//ロケーションマスタ検索EXITボタン押下時の挙動
		LocExit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				Loc_fm.setVisible(false);
			}
		});
		
		
		
		
		Object[] Rt = {
				Loc_fm
				,tableModel_msLoc
				,tbLoc
				,LocEntry_btn
				};
		return Rt;
	}
	
	private static Object[][] LocSearch(
				String SearchTgtClCd,
				String SearchTgtWhCd,
				String SearchTgtLoc,
				String SearchTgtLocName,
				String SearchTgtType
				){
		ArrayList<String> SearchClCd 	= new ArrayList<String>();	//荷主コード
		ArrayList<String> SearchWhCd 	= new ArrayList<String>();	//倉庫コード
		ArrayList<String> SearchLoc 	= new ArrayList<String>();	//ロケーション
		ArrayList<String> SearchLocName = new ArrayList<String>();	//ロケーション名
		ArrayList<String> SearchType 	= new ArrayList<String>();	//ロケタイプ
		boolean LocExactMatch = false;	//ロケーション完全一致
		boolean AllSearch = true;
		
		if(!"".equals(SearchTgtClCd		)) {SearchClCd.add(		SearchTgtClCd);}
		if(!"".equals(SearchTgtWhCd		)) {SearchWhCd.add(		SearchTgtWhCd);}
		if(!"".equals(SearchTgtLoc		)) {SearchLoc.add(		SearchTgtLoc);}
		if(!"".equals(SearchTgtLocName	)) {SearchLocName.add(	SearchTgtLocName);}
		if(!"".equals(SearchTgtType		)) {SearchType.add(		SearchTgtType);}
		
		Object[][] LocationMstRt = M100_LocationMstRt.LocationMstRt(
		SearchClCd,		//荷主コード
		SearchWhCd,		//倉庫コード
		SearchLoc,		//ロケーション
		SearchLocName,	//ロケーション名
		SearchType,		//ロケタイプ
		LocExactMatch,	//ロケーション完全一致
		AllSearch);
		return LocationMstRt;
	}
}