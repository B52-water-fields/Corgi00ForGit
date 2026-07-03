import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class WM100_ItemComversionMst_01_RenewAndCreate{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	public static void ItemComversionMstRenewAndCreate(int x,int y,String TgtClgpCd,String TgtClCd,String ClItemCd) {
		A00000_Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		
		final JFrame main_fm = B100_FrameParts.FrameCreate(x,y,500,500,"Corgi00商品変換マスタ登録・修正","");
		JLabel userinfo = B100_FrameParts.UserInfo();
		JButton exit_btn = B100_FrameParts.ExitBtn();
		JButton entry_btn = B100_FrameParts.EntryBtn();

		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		main_fm.add(entry_btn);
		
		JLabel LB_ClGpCd	= B100_FrameParts.JLabelSet(	  0, 25,130,20,"荷主グループコード:"	,11,1);
		JLabel LB_ClCd		= B100_FrameParts.JLabelSet(	  0, 50,130,20,"荷主コード:"			,11,1);
		JLabel LB_CLItemCd	= B100_FrameParts.JLabelSet(	  0, 75,130,20,"荷主商品コード:"		,11,1);
		
		JLabel LB_Msg		= B100_FrameParts.JLabelSet(	130,125,130,20,"を下記に変換します"		,11,0);

		JLabel LB_UnitType	= B100_FrameParts.JLabelSet(	  0,175,130,20,"荷姿:"					,11,1);
		JLabel LB_ItemCd	= B100_FrameParts.JLabelSet(	  0,200,130,20,"商品コード:"			,11,1);
		
		final JComboBox   TB_ClGpCd		= B100_FrameParts.JComboBoxSet(	130, 25,200,20,B100_DefaultVariable.ClGpList[0],11);		//荷主グループコード
		final JComboBox   TB_ClCd		= B100_FrameParts.JComboBoxSet(	130, 50,200,20,B100_DefaultVariable.ClList[0],11);		//荷主コード
		final JTextField  TB_CLItemCd	= B100_FrameParts.JTextFieldSet(	130, 75,100,20,""	,11,0);									//荷主商品コード

		final JComboBox   TB_UnitType	= B100_FrameParts.JComboBoxSet(	130,175,130,20,B100_DefaultVariable.UnitTypeList[0],11);	//荷姿
		final JTextField  TB_ItemCd		= B100_FrameParts.JTextFieldSet(	130,200,100,20,""	,11,0);									//商品コード
		
		JLabel LB_ItemName01			= B100_FrameParts.JLabelSet(  10,225,100,20,"商品名1:"				,10,1);
		JLabel LB_ItemName02			= B100_FrameParts.JLabelSet(  10,250,100,20,"商品名2:"				,10,1);
		JLabel LB_ItemName03			= B100_FrameParts.JLabelSet(  10,275,100,20,"商品名3:"				,10,1);
		final JTextField  TB_ItemName01	= B100_FrameParts.JTextFieldSet( 110,225,200,20,"",11,0);			//商品名1
		final JTextField  TB_ItemName02	= B100_FrameParts.JTextFieldSet( 110,250,200,20,"",11,0);			//商品名2
		final JTextField  TB_ItemName03	= B100_FrameParts.JTextFieldSet( 110,275,200,20,"",11,0);			//商品名3
		JLabel LB_CtQty					= B100_FrameParts.JLabelSet(  10,300,100,20,"カートン入数:"			,10,1);
		JLabel LB_CsQty					= B100_FrameParts.JLabelSet(  10,325,100,20,"ケース入数:"				,10,1);
		JLabel LB_PlQty					= B100_FrameParts.JLabelSet(  10,350,100,20,"パレット入数:"			,10,1);
		final JFormattedTextField TB_CtQty	= B100_FrameParts.JFormattedTextFieldSet(110,300,100,20,"0",11,1,"#,###");	//カートン入数
		final JFormattedTextField TB_CsQty	= B100_FrameParts.JFormattedTextFieldSet(110,325,100,20,"0",11,1,"#,###");	//ケース入数
		final JFormattedTextField TB_PlQty	= B100_FrameParts.JFormattedTextFieldSet(110,350,100,20,"0",11,1,"#,###");	//パレット入数
		
		TB_ItemCd.setEditable(false);
		TB_ItemName01.setEditable(false);
		TB_ItemName02.setEditable(false);
		TB_ItemName03.setEditable(false);
		TB_CtQty.setEditable(false);
		TB_CsQty.setEditable(false);
		TB_PlQty.setEditable(false);
		
		//検索ボタン
		JButton SearchBtn = B100_FrameParts.BtnSet(240, 75,100,20,"変換先商品検索",9);
		main_fm.add(SearchBtn);
		
		//荷主グループ検索条件に現在荷主を設定 管理者以外は荷主選択条件固定する
		if(null==TgtClgpCd	) {TgtClgpCd="";}
		if(null==TgtClCd	) {TgtClCd="";}
		if(null==ClItemCd	) {ClItemCd="";}
		TgtClgpCd	= B100_TextControl.Trim(TgtClgpCd);
		TgtClCd		= B100_TextControl.Trim(TgtClCd);
		ClItemCd	= B100_TextControl.Trim(ClItemCd);
		
		if("".equals(TgtClgpCd	)) {TgtClgpCd	=A00000_Main.ClGp;}
		if("".equals(TgtClCd	)) {TgtClCd		=A00000_Main.ClCd;}
		
		for(int i=0;i<B100_DefaultVariable.ClGpList[1].length;i++) {
			if(TgtClgpCd.equals(""+B100_DefaultVariable.ClGpList[1][i])) {
				TB_ClGpCd.setSelectedIndex(i);
			}
		}
		for(int i=0;i<B100_DefaultVariable.ClList[1].length;i++) {
			if(TgtClCd.equals(""+B100_DefaultVariable.ClList[1][i])) {
				TB_ClCd.setSelectedIndex(i);
			}
		}
		if("9".equals(A00000_Main.LoginUserAuthorityFG)) {
			
		}else {
			TB_ClGpCd.setEditable(false);
			TB_ClCd.setEditable(false);
		}
		
		if(null!=ClItemCd&&!"".equals(ClItemCd)) {
			ArrayList<String> SearchClGpCd = new ArrayList<String>();		//荷主グループコード
			ArrayList<String> SearchClCd = new ArrayList<String>();			//荷主コード
			ArrayList<String> SearchItemCd = new ArrayList<String>();		//商品コード
			ArrayList<String> SearchCLItemCd = new ArrayList<String>();		//荷主商品コード
			ArrayList<String> SearchItemName = new ArrayList<String>();		//商品名
			boolean AllSearch = false;
			
			SearchClGpCd.add(TgtClgpCd);
			SearchClCd.add(TgtClCd);
			SearchCLItemCd.add(ClItemCd);
			
			Object[][] ItemComversionMstRt = M100_ItemComversionMstRt.ItemComversionMstRt(
					SearchClGpCd,			//荷主グループコード
					SearchClCd,				//荷主コード
					SearchItemCd,			//商品コード
					SearchCLItemCd,			//荷主商品コード
					SearchItemName,			//商品名
					AllSearch);
			
			TB_CLItemCd.setText(ClItemCd);
			
			if(0<ItemComversionMstRt.length) {
				TB_UnitType.setSelectedIndex(0);
				for(int i=0;i<B100_DefaultVariable.UnitTypeList[1].length;i++) {
					if((""+B100_DefaultVariable.UnitTypeList[1][i]).equals(""+ItemComversionMstRt[0][M100_ItemComversionMstRt.ColPackingType])) {
						TB_UnitType.setSelectedIndex(i);
					}
				}
				
				TB_ItemCd.setText(		""+ItemComversionMstRt[0][M100_ItemComversionMstRt.ColItemCd]);
				TB_ItemName01.setText(	""+ItemComversionMstRt[0][M100_ItemComversionMstRt.ColItemName01]);
				TB_ItemName02.setText(	""+ItemComversionMstRt[0][M100_ItemComversionMstRt.ColItemName02]);
				TB_ItemName03.setText(	""+ItemComversionMstRt[0][M100_ItemComversionMstRt.ColItemName03]);
				TB_CtQty.setText(		""+ItemComversionMstRt[0][M100_ItemComversionMstRt.ColCtQty]);
				TB_CsQty.setText(		""+ItemComversionMstRt[0][M100_ItemComversionMstRt.ColCsQty]);
				TB_PlQty.setText(		""+ItemComversionMstRt[0][M100_ItemComversionMstRt.ColPlQty]);
			}
		}
		
		main_fm.add(LB_ClGpCd);
		main_fm.add(LB_ClCd);
		main_fm.add(LB_CLItemCd);
		main_fm.add(LB_Msg);
		main_fm.add(LB_UnitType);
		main_fm.add(LB_ItemCd);
		main_fm.add(TB_ClGpCd);
		main_fm.add(TB_ClCd);
		main_fm.add(TB_CLItemCd);
		main_fm.add(TB_UnitType);
		main_fm.add(TB_ItemCd);
		main_fm.add(LB_ItemName01);
		main_fm.add(LB_ItemName02);
		main_fm.add(LB_ItemName03);
		main_fm.add(TB_ItemName01);
		main_fm.add(TB_ItemName02);
		main_fm.add(TB_ItemName03);
		main_fm.add(LB_CtQty);
		main_fm.add(LB_CsQty);
		main_fm.add(LB_PlQty);
		main_fm.add(TB_CtQty);
		main_fm.add(TB_CsQty);
		main_fm.add(TB_PlQty);
		
		main_fm.setVisible(true);
		
		
		final JFrame ItemSearch_fm = B100_FrameParts.FrameCreate(x+20,y+20,800,800,"Corgi00商品変換マスタ登録・修正(商品検索)","");
		JLabel ItemSearch_userinfo = B100_FrameParts.UserInfo();
		JButton ItemSearch_exit_btn = B100_FrameParts.ExitBtn();
		JButton ItemSearch_entry_btn = B100_FrameParts.EntryBtn();

		ItemSearch_fm.add(ItemSearch_userinfo);
		ItemSearch_fm.add(ItemSearch_exit_btn);
		ItemSearch_fm.add(ItemSearch_entry_btn);
		
		//検索条件パネル
		JPanel PN_Search = B100_FrameParts.JPanelSet(10,40,780,130,"White");
		JLabel PN_SearchLabel = B100_FrameParts.JLabelSet(10,0,150,20,"検索条件",11,0);
		
		//検索条件
		JLabel LB_SearchItemCd		= B100_FrameParts.JLabelSet(	  0, 25,130,20,"商品コード:"			,11,1);
		JLabel LB_SearchCLItemCd	= B100_FrameParts.JLabelSet(	  0, 50,130,20,"荷主商品コード:"		,11,1);
		JLabel LB_SearchItemName	= B100_FrameParts.JLabelSet(	  0, 75,130,20,"商品名:"				,11,1);
		
		final JTextField  TB_SearchItemCd	= B100_FrameParts.JTextFieldSet(	130, 25,100,20,""	,11,0);		//商品コード
		final JTextField  TB_SearchCLItemCd	= B100_FrameParts.JTextFieldSet(	130, 50,100,20,""	,11,0);		//荷主商品コード
		final JTextField  TB_SearchItemName	= B100_FrameParts.JTextFieldSet(	130, 75,100,20,""	,11,0);		//商品名
		
		JLabel LB2_SearchItemCd		= B100_FrameParts.JLabelSet(	230, 25, 80,20,"と一致"	,11,0);
		JLabel LB2_SearchCLItemCd	= B100_FrameParts.JLabelSet(	230, 50, 80,20,"と一致"	,11,0);
		JLabel LB2_SearchItemName	= B100_FrameParts.JLabelSet(	230, 75, 80,20,"を含む"	,11,0);
		
		PN_Search.add(LB_SearchItemCd);
		PN_Search.add(LB_SearchCLItemCd);
		PN_Search.add(LB_SearchItemName);

		PN_Search.add(TB_SearchItemCd);
		PN_Search.add(TB_SearchCLItemCd);
		PN_Search.add(TB_SearchItemName);
		
		PN_Search.add(LB2_SearchItemCd);
		PN_Search.add(LB2_SearchCLItemCd);
		PN_Search.add(LB2_SearchItemName);
		
		//検索ボタン
		JButton SearchItemSearchBtn = B100_FrameParts.BtnSet(130,100,100,20,"検索",11);
		PN_Search.add(SearchItemSearchBtn);
		
		ItemSearch_fm.add(PN_Search);
		
		//検索結果
		Object[][] RtSettingItemMstRt = M100_ItemMstRt.RtSettingItemMstRt();
		
		String[] columnNames01 = new String[RtSettingItemMstRt.length+1];
		
		columnNames01[0] = "Fg";
		for(int i=0;i<RtSettingItemMstRt.length;i++) {
			columnNames01[1+i] = ""+RtSettingItemMstRt[i][3];
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
		
		for(int i=0;i<RtSettingItemMstRt.length;i++) {
			column = columnModel01.getColumn(1+i);	column.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.leftCellRenderer());
		}
		
		//スクロール用設定
		JScrollPane scpn01 = B100_FrameParts.JScrollPaneSet(10,170,780,500,tb01);
		ItemSearch_fm.add(scpn01);
		
		
		ItemSearch_exit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				ItemSearch_fm.setVisible(false);
				ItemSearch_fm.dispose();
			}
		});
		
		RenewFg = true;
		
		//商品検索登録ボタン押下時の挙動
		ItemSearch_entry_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					int RowCount = MainFmTableModel.getRowCount();
					for(int i=0;i<RowCount;i++) {
						if((boolean)MainFmTableModel.getValueAt(i, 0)) {
							TB_ItemCd.setText(		""+MainFmTableModel.getValueAt(i, 3));
							TB_ItemName01.setText(	""+MainFmTableModel.getValueAt(i, 5));
							TB_ItemName02.setText(	""+MainFmTableModel.getValueAt(i, 6));
							TB_ItemName03.setText(	""+MainFmTableModel.getValueAt(i, 7));
							TB_CtQty.setText(		""+MainFmTableModel.getValueAt(i,19));
							TB_CsQty.setText(		""+MainFmTableModel.getValueAt(i,20));
							TB_PlQty.setText(		""+MainFmTableModel.getValueAt(i,21));
						}
					}
					ItemSearch_fm.setVisible(false);
					ItemSearch_fm.dispose();
					RenewFg = true;
				}
			}
		});
		
		//商品検索検索ボタン押下時の挙動
		SearchItemSearchBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					int RowCount = MainFmTableModel.getRowCount();
					for(int i=0;i<RowCount;i++) {
						MainFmTableModel.removeRow(0);
					}
					String GetClGpCd 		 	= B100_DefaultVariable.ClGpList[1][TB_ClGpCd.getSelectedIndex()];			//荷主グループコード
					String GetSearchItemCd 		= TB_SearchItemCd.getText();		//商品コード
					String GetSearchCLItemCd 	= TB_SearchCLItemCd.getText();		//荷主商品コード
					String GetSearchItemName 	= TB_SearchItemName.getText();		//商品名
					
					if(null==GetClGpCd			){GetClGpCd			= "";}
					if(null==GetSearchItemCd	){GetSearchItemCd	= "";}
					if(null==GetSearchCLItemCd	){GetSearchCLItemCd	= "";}
					if(null==GetSearchItemName	){GetSearchItemName	= "";}
					
					GetClGpCd			= B100_TextControl.Trim(GetClGpCd);
					GetSearchItemCd		= B100_TextControl.Trim(GetSearchItemCd);
					GetSearchCLItemCd	= B100_TextControl.Trim(GetSearchCLItemCd);
					GetSearchItemName	= B100_TextControl.Trim(GetSearchItemName);
					
					//商品マスタ取得
					Object[][] ItemMstRt = ItemMstCheck(GetClGpCd,GetSearchItemCd,GetSearchCLItemCd,GetSearchItemName);
					
					for(int i=0;i<ItemMstRt.length;i++) {
						Object[] SetOb = new Object[ItemMstRt[i].length+1];
						SetOb[0]=false;
						for(int i01=0;i01<ItemMstRt[i].length;i01++) {
							SetOb[i01+1]=""+ItemMstRt[i][i01];
						}
						MainFmTableModel.addRow(SetOb);
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
		
		//商品検索ボタン押下時の挙動
		SearchBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					ItemSearch_fm.setVisible(false);
					TB_SearchItemCd.setText("");
					TB_SearchCLItemCd.setText("");
					TB_SearchItemName.setText("");
					
					int RowCount = MainFmTableModel.getRowCount();
					for(int i=0;i<RowCount;i++) {
						MainFmTableModel.removeRow(0);
					}
					ItemSearch_fm.setVisible(true);
					RenewFg = true;
				}
			}
		});
		
		
		//登録ボタン押下時の挙動
		entry_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				String GetClGpCd 		= B100_DefaultVariable.ClGpList[1][TB_ClGpCd.getSelectedIndex()];			//荷主グループコード
				String GetClCd 			= B100_DefaultVariable.ClList[1][TB_ClCd.getSelectedIndex()];				//荷主コード
				String GetUnitType		= B100_DefaultVariable.UnitTypeList[1][TB_UnitType.getSelectedIndex()];	//荷姿
				String GetCLItemCd		= TB_CLItemCd.getText();	//荷主商品コード
				String GetItemCd		= TB_ItemCd.getText();		//商品コード
				String GetCtQty			= TB_CtQty.getText();
				String GetCsQty			= TB_CsQty.getText();
				String GetPlQty			= TB_PlQty.getText();
				
				if(null==GetClGpCd		){GetClGpCd		= "";}	//荷主グループコード
				if(null==GetClCd		){GetClCd		= "";}	//荷主コード
				if(null==GetUnitType	){GetUnitType	= "";}	//荷姿
				if(null==GetCLItemCd	){GetCLItemCd	= "";}	//荷主商品コード
				if(null==GetItemCd		){GetItemCd		= "";}	//商品コード
				if(null==GetCtQty		){GetCtQty= "";}
				if(null==GetCsQty		){GetCsQty= "";}
				if(null==GetPlQty		){GetPlQty= "";}
				
				GetClGpCd	= B100_TextControl.Trim(GetClGpCd);	//荷主グループコード
				GetClCd		= B100_TextControl.Trim(GetClCd);		//荷主コード
				GetUnitType	= B100_TextControl.Trim(GetUnitType);	//荷姿
				GetCLItemCd	= B100_TextControl.Trim(GetCLItemCd);	//荷主商品コード
				GetItemCd	= B100_TextControl.Trim(GetItemCd);	//商品コード
				GetCtQty	= B100_TextControl.Trim(GetCtQty);
				GetCsQty	= B100_TextControl.Trim(GetCsQty);
				GetPlQty	= B100_TextControl.Trim(GetPlQty);
				
				GetUnitType	= B100_TextControl.num_only_String(GetUnitType);	//荷姿
				GetCtQty	= B100_TextControl.num_only_String02(GetCtQty);
				GetCsQty	= B100_TextControl.num_only_String02(GetCsQty);
				GetPlQty	= B100_TextControl.num_only_String02(GetPlQty);
				
				if("".equals(GetUnitType)) {GetUnitType	="0";}
				if("".equals(GetCtQty	)) {GetCtQty	="0";}
				if("".equals(GetCsQty	)) {GetCsQty	="0";}
				if("".equals(GetPlQty	)) {GetPlQty	="0";}

				boolean KickFg = true;
				
				ArrayList<String> ErrMsg = ErrCheck(GetClGpCd,GetClCd,GetUnitType,GetCLItemCd,GetItemCd);
				if(0<ErrMsg.size()) {
					KickFg = false;
				}
				int wint = 0;
				switch(GetUnitType) {
					case "0":
						break;
					case "1":
						wint = Integer.parseInt(GetCtQty);
						if(0>=wint) {
							ErrMsg.add("その商品はカートン変換指定できません");
							KickFg = false;
						}
						break;
					case "2":
						wint = Integer.parseInt(GetCsQty);
						if(0>=wint) {
							ErrMsg.add("その商品はケース変換指定できません");
							KickFg = false;
						}
						break;
					case "3":
						wint = Integer.parseInt(GetPlQty);
						if(0>=wint) {
							ErrMsg.add("その商品はパレット変換指定できません");
							KickFg = false;
						}
						break;
					default:
						ErrMsg.add("変換先の荷姿が不正です");
						break;
				}
				
				if(KickFg) {
					String now_dtm = B100_DateTimeControl.dtmString2(B100_DateTimeControl.dtm()[1])[1];
					
					String[][] ItemComversionMstSetString = {
							 {"ClGpCd"		,"1","1",GetClGpCd}		//荷主グループコード
							,{"ClCd"		,"1","1",GetClCd}		//荷主コード
							,{"ClItemCd"	,"1","1",GetCLItemCd}	//荷主商品コード
							,{"ItemCd"		,"1","1",GetItemCd}		//変換先商品コード
							,{"PackingType"	,"1","1",GetUnitType}	//荷姿タイプ
							};
					String tgt_table = "KM0062_ItemComversionMst";
					String[][] field_name = new String[ItemComversionMstSetString.length][3];
					String[][] entry_data = new String[1][ItemComversionMstSetString.length];
					String[] judg_field = new String[3];
					String[][] judg_data = new String[1][3];
					String TgtDB = "NYANKO";
					int non_msg_fg = 1;
		
					judg_field[0] = "ClGpCd";		//荷主グループコード
					judg_field[1] = "ClCd";			//荷主コード
					judg_field[2] = "ClItemCd";		//荷主商品コード
					
					judg_data[0][0] = GetClGpCd;	//荷主グループコード
					judg_data[0][1] = GetClCd;		//荷主コード
					judg_data[0][2] = GetCLItemCd;	//荷主商品コード
					
					for(int i=0;i<ItemComversionMstSetString.length;i++) {
						field_name[i][0] = ItemComversionMstSetString[i][0];
						field_name[i][1] = ItemComversionMstSetString[i][1];
						field_name[i][2] = ItemComversionMstSetString[i][2];
						entry_data[0][i] = ItemComversionMstSetString[i][3];
					}
					A100_InsertUpdateSQL.RUN_SQLS_EU(tgt_table, field_name, entry_data, judg_field, judg_data, non_msg_fg,TgtDB);
					
					SetX=main_fm.getX();
					SetY=main_fm.getY();
	
					main_fm.setVisible(false);
					main_fm.dispose();
					ItemComversionMstRenewAndCreate(0, 0,GetClGpCd,GetClCd,GetUnitType);
				}else {
					String Msg = "";
					for(int i=0;i<ErrMsg.size();i++) {
						Msg = Msg+ErrMsg.get(i)+"\n";
					}
					JOptionPane.showMessageDialog(null, Msg);
				}
			}
		});
		
		
		//EXITボタン押下時の挙動
		exit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();
				
				ItemSearch_fm.setVisible(false);
				ItemSearch_fm.dispose();

				main_fm.setVisible(false);
				main_fm.dispose();
				WM100_ItemComversionMst_00_Search.ItemComversionMstSearch(0, 0);
			}
		});
	}
	
	private static ArrayList<String> ErrCheck(String GetClGpCd,String GetClCd,String GetUnitType,String GetCLItemCd,String GetItemCd) {
		ArrayList<String> ErrMsg = new ArrayList<String>();
		//商品マスタ取得
		Object[][] ItemMstRt = ItemMstCheck(GetClGpCd,GetItemCd,null,null);
		
		if("".equals(GetCLItemCd)) {
			ErrMsg.add("荷主商品コードが設定されていません");
		}
		
		if(1!=ItemMstRt.length) {
			ErrMsg.add("変換先の商品マスタを特定できませんでした。ちゃんと商品マスタから選びやがれ。");
		}else {
			int wint = Integer.parseInt(GetUnitType);
			switch(wint) {
				case 0:
					break;
				case 1:
					if(0==(int)ItemMstRt[0][M100_ItemMstRt.ColCtQty]) {
						ErrMsg.add("変換先の商品マスタにはカートンの概念がありません。やり直し。");
					}
					break;
				case 2:
					if(0==(int)ItemMstRt[0][M100_ItemMstRt.ColCsQty]) {
						ErrMsg.add("変換先の商品マスタにはケースの概念がありません。やり直し。");
					}
					break;
				case 3:
					if(0==(int)ItemMstRt[0][M100_ItemMstRt.ColPlQty]) {
						ErrMsg.add("変換先の商品マスタにはパレットの概念がありません。やり直し。");
					}
					break;
				default:
					ErrMsg.add("ありえない荷姿設定です。やり直し。");
					break;
			}
		}
		return ErrMsg;
	}
	
	
	private static Object[][] ItemMstCheck(String GetClGpCd,String GetItemCd,String GetClItemCd,String GetItemName){
		if(null==GetClGpCd		) {GetClGpCd="";}
		if(null==GetItemCd		) {GetItemCd="";}
		if(null==GetClItemCd	) {GetClItemCd="";}
		if(null==GetItemName	) {GetItemName="";}
		
		ArrayList<String> SearchClGpCd = new ArrayList<String>();			//荷主グループコード
		ArrayList<String> SearchItemCd = new ArrayList<String>();			//商品コード
		ArrayList<String> SearchCLItemCd = new ArrayList<String>();			//荷主商品コード
		ArrayList<String> SearchItemName = new ArrayList<String>();			//商品名
		ArrayList<String> SearchDeliveryTypeCd01 = new ArrayList<String>();	//運送タイプコード01
		ArrayList<String> SearchDeliveryTypeCd02 = new ArrayList<String>();	//運送タイプコード02
		ArrayList<String> SearchDeliveryTypeCd03 = new ArrayList<String>();	//運送タイプコード03
		ArrayList<String> SearchDeliveryTypeCd04 = new ArrayList<String>();	//運送タイプコード04
		ArrayList<String> SearchDeliveryTypeCd05 = new ArrayList<String>();	//運送タイプコード05
		ArrayList<String> SearchItemMDNo = new ArrayList<String>();			//商品モデル番号（型番）
		ArrayList<String> SearchCategoryCd = new ArrayList<String>();		//商品カテゴリCD
		ArrayList<String> SearchCategoryName = new ArrayList<String>();		//商品カテゴリ名
		ArrayList<String> SearchItemColorCd = new ArrayList<String>();		//商品カラーコード
		ArrayList<String> SearchItemColorName = new ArrayList<String>();	//商品カラー名
		ArrayList<String> SearchItemSizeCd = new ArrayList<String>();		//商品サイズコード
		ArrayList<String> SearchItemSizeName = new ArrayList<String>();		//商品サイス名
		ArrayList<String> SearchJanCd = new ArrayList<String>();			//JANCD
		ArrayList<String> SearchTildFG = new ArrayList<String>();			//温度区分
		ArrayList<String> SearchTildName = new ArrayList<String>();			//温度区分名
		ArrayList<String> SearchDelFg = new ArrayList<String>();			//削除フラグ
		boolean AllSearch = false;
		
		if(!"".equals(GetClGpCd)) {
			SearchClGpCd.add(GetClGpCd);
		}
		if(!"".equals(GetItemCd)) {
			SearchItemCd.add(GetItemCd);
		}
		if(!"".equals(GetClItemCd)) {
			SearchCLItemCd.add(GetClItemCd);
		}
		if(!"".equals(GetItemName)) {
			SearchItemName.add(GetItemName);
		}
		
		Object[][] ItemMstRt = M100_ItemMstRt.ItemMstRt(
				SearchClGpCd,			//荷主グループコード
				SearchItemCd,			//商品コード
				SearchCLItemCd,			//荷主商品コード
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
				SearchItemSizeName,		//商品サイス名
				SearchJanCd,			//JANCD
				SearchTildFG,			//温度区分
				SearchTildName,			//温度区分名
				SearchDelFg,			//削除フラグ
				AllSearch);
		
		return ItemMstRt;
	}
	
	
	
}