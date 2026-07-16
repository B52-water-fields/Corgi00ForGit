import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.io.IOException;
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

public class WM100_ItemRecomendLocMst_01_RenewAndCreate{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	public static void ItemRecomendLocMstRenewAndCreate(int x,int y,String TgtClCd,String TgtWhCd,String TgtItemCd) {
		A00000_Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		
		if(null==TgtClCd		) {TgtClCd= "";}
		if(null==TgtWhCd		) {TgtWhCd= "";}
		if(null==TgtItemCd		) {TgtItemCd= "";}
		
		if("".equals(TgtWhCd)) {TgtWhCd = A00000_Main.ClWh;}
		if("".equals(TgtClCd)) {TgtClCd = A00000_Main.ClCd;}
		
		final JFrame main_fm = B100_FrameParts.FrameCreate(x,y,500,450,"Corgi00推奨ロケマスタ(荷主毎)登録・修正　WM100_ItemRecomendLocMst_01_RenewAndCreate","");
		JLabel userinfo 	= B100_FrameParts.UserInfo();
		JButton exit_btn 	= B100_FrameParts.ExitBtn();
		JButton entry_btn 	= B100_FrameParts.EntryBtn();
		
		JLabel LB_ClCd					= B100_FrameParts.JLabelSet(	   0, 50,130,20,"荷主コード:"		,10,1);
		JLabel LB_ClWh					= B100_FrameParts.JLabelSet(	   0, 75,130,20,"担当倉庫コード:"	,10,1);
		JLabel LB_ItemCd				= B100_FrameParts.JLabelSet(	   0,100,130,20,"商品コード:"		,10,1);
		JLabel LB_ItemName01			= B100_FrameParts.JLabelSet(	   0,125,130,20,"商品表記名:"		,10,1);
		JLabel LB_RecomendLoc			= B100_FrameParts.JLabelSet(	   0,150,130,20,"推奨ロケ:"			,10,1);
		JLabel LB_LocName				= B100_FrameParts.JLabelSet(	   0,175,130,20,"ロケーション名:"	,10,1);
		JLabel LB_Type					= B100_FrameParts.JLabelSet(	   0,200,130,20,"ロケタイプ:"		,10,1);
		JLabel LB_EntryDate				= B100_FrameParts.JLabelSet(	   0,225,130,20,"データ登録日時:"	,10,1);
		JLabel LB_UpdateDate			= B100_FrameParts.JLabelSet(	   0,250,130,20,"データ更新日時:"	,10,1);
		JLabel LB_EntryUser				= B100_FrameParts.JLabelSet(	   0,275,130,20,"登録者:"			,10,1);
		JLabel LB_UpdateUser			= B100_FrameParts.JLabelSet(	   0,300,130,20,"更新者:"			,10,1);
		JLabel LB_ItemSubRecomendLoc	= B100_FrameParts.JLabelSet(	   0,325,130,20,"商品サブマスタ推奨ロケ:"	,10,1);
		
		final JComboBox   TB_ClCd				= B100_FrameParts.JComboBoxSet( 		130, 50,200,20,B100_DefaultVariable.ClList[0],11);	//荷主コード
		final JComboBox   TB_ClWh				= B100_FrameParts.JComboBoxSet( 		130, 75,200,20,B100_DefaultVariable.WhList[0],11);	//担当倉庫コード
		final JTextField  TB_ItemCd				= B100_FrameParts.JTextFieldSet( 	130,100,100,20,TgtItemCd	,11,0);	//商品コード
		final JTextField  TB_ItemName01			= B100_FrameParts.JTextFieldSet( 	130,125,200,20,""			,11,0);	//商品表記名
		final JTextField  TB_RecomendLoc		= B100_FrameParts.JTextFieldSet(		130,150,100,20,""			,11,0);	//推奨ロケ
		final JTextField  TB_LocName			= B100_FrameParts.JTextFieldSet( 	130,175,200,20,""			,11,0);	//ロケーション名
		final JComboBox   TB_Type				= B100_FrameParts.JComboBoxSet(		130,200,100,20,B100_DefaultVariable.LocType[0],11);	//ロケタイプ
		final JTextField  TB_EntryDate			= B100_FrameParts.JTextFieldSet(		130,225,200,20,"",11,0);	//データ登録日時
		final JTextField  TB_UpdateDate			= B100_FrameParts.JTextFieldSet(		130,250,200,20,"",11,0);	//データ更新日時
		final JTextField  TB_EntryUser			= B100_FrameParts.JTextFieldSet(		130,275,200,20,"",11,0);	//登録者
		final JTextField  TB_UpdateUser			= B100_FrameParts.JTextFieldSet(		130,300,200,20,"",11,0);	//更新者
		final JTextField  TB_ItemSubRecomendLoc	= B100_FrameParts.JTextFieldSet(		130,325,100,20,"",11,0);	//商品サブマスタ推奨ロケ
		
		//商品検索ボタン
		JButton ItemSearchBtn = B100_FrameParts.BtnSet(		250,100,100,20,"商品検索",11);
		
		//ロケーション検索ボタン
		JButton LocSearchBtn = B100_FrameParts.BtnSet(		250,150,100,20,"ロケ検索",11);
		main_fm.add(LocSearchBtn);
		
		TB_ClCd.setEnabled(false);
		TB_ClWh.setEnabled(false);
		TB_ItemName01.setEditable(false);
		TB_LocName.setEditable(false);
		TB_Type.setEnabled(false);
		TB_EntryDate.setEditable(false);
		TB_UpdateDate.setEditable(false);
		TB_EntryUser.setEditable(false);
		TB_UpdateUser.setEditable(false);
		TB_ItemSubRecomendLoc.setEditable(false);
		
		for(int i=0;i<B100_DefaultVariable.ClList[1].length;i++) {
			if(TgtClCd.equals(B100_DefaultVariable.ClList[1][i])) {
				TB_ClCd.setSelectedIndex(i);
			}
		}
		for(int i=0;i<B100_DefaultVariable.WhList[1].length;i++) {
			if(TgtWhCd.equals(B100_DefaultVariable.WhList[1][i])) {
				TB_ClWh.setSelectedIndex(i);
			}
		}
		if(!"".equals(TgtItemCd)) {
			TB_ItemCd.setEnabled(false);
			Object[][] ItemRecomendLocMstRt = ItemRecomendLocMstRt(TgtClCd,TgtWhCd,TgtItemCd);
			if(1==ItemRecomendLocMstRt.length) {
				String GetClCd			= (String)ItemRecomendLocMstRt[0][M100_ItemRecomendLocMstRt.ColClCd];			//荷主コード
				String GetCLName		= (String)ItemRecomendLocMstRt[0][M100_ItemRecomendLocMstRt.ColCLName];		//荷主表記名
				String GetClWh			= (String)ItemRecomendLocMstRt[0][M100_ItemRecomendLocMstRt.ColClWh];			//担当倉庫コード
				String GetClWHName		= (String)ItemRecomendLocMstRt[0][M100_ItemRecomendLocMstRt.ColClWHName];		//担当倉庫名
				String GetClGpCD		= (String)ItemRecomendLocMstRt[0][M100_ItemRecomendLocMstRt.ColClGpCD];		//荷主グループCD
				String GetClGpName		= (String)ItemRecomendLocMstRt[0][M100_ItemRecomendLocMstRt.ColClGpName];		//グループ名1
				String GetItemCd		= (String)ItemRecomendLocMstRt[0][M100_ItemRecomendLocMstRt.ColItemCd];		//商品コード
				String GetItemName01	= (String)ItemRecomendLocMstRt[0][M100_ItemRecomendLocMstRt.ColItemName01];	//商品表記名
				String GetRecomendLoc	= (String)ItemRecomendLocMstRt[0][M100_ItemRecomendLocMstRt.ColRecomendLoc];	//推奨ロケ
				String GetLocName		= (String)ItemRecomendLocMstRt[0][M100_ItemRecomendLocMstRt.ColLocName];		//ロケーション名
				int    GetType			= (int)ItemRecomendLocMstRt[0][M100_ItemRecomendLocMstRt.ColType];			//ロケタイプ　0:通常　1:保管　8:入荷時　9:引当禁止
				String GetEntryDate		= (String)ItemRecomendLocMstRt[0][M100_ItemRecomendLocMstRt.ColEntryDate];	//データ登録日時
				String GetUpdateDate	= (String)ItemRecomendLocMstRt[0][M100_ItemRecomendLocMstRt.ColUpdateDate];	//データ更新日時
				String GetEntryUser		= (String)ItemRecomendLocMstRt[0][M100_ItemRecomendLocMstRt.ColEntryUser];	//登録者
				String GetUpdateUser	= (String)ItemRecomendLocMstRt[0][M100_ItemRecomendLocMstRt.ColUpdateUser];	//更新者
				String GetItemSubRecomendLoc	= (String)ItemRecomendLocMstRt[0][M100_ItemRecomendLocMstRt.ColItemSubRecomendLoc];	//商品サブマスタ推奨ロケ
				
				TB_ItemName01.setText(GetItemName01);
				TB_RecomendLoc.setText(GetRecomendLoc);
				TB_LocName.setText(GetLocName);
				for(int i=0;i<B100_DefaultVariable.LocType[1].length;i++) {
					if((""+GetType).equals(B100_DefaultVariable.LocType[1][i])) {
						TB_Type.setSelectedIndex(i);
					}
				}
				TB_EntryDate.setText(GetEntryDate);
				TB_UpdateDate.setText(GetUpdateDate);
				TB_EntryUser.setText(GetEntryUser);
				TB_UpdateUser.setText(GetUpdateUser);
				TB_ItemSubRecomendLoc.setText(GetItemSubRecomendLoc);
			}else {
				String ClGp = GlGpRt(TgtClCd);
				
				Object[][] ItemMstRt= ItemMstRt(ClGp,TgtItemCd,"");
				if(1==ItemMstRt.length) {
					String GetItemCd				= (String)ItemMstRt[0][M100_ItemMstRt.ColItemCd];			//商品コード
					String GetItemName01			= (String)ItemMstRt[0][M100_ItemMstRt.ColItemName01];	//商品表記名
					String GetItemSubRecomendLoc	= (String)ItemMstRt[0][M100_ItemMstRt.ColRecomendLoc];	//商品サブマスタ推奨ロケ
					
					TB_ItemCd.setText(GetItemCd);							//商品コード
					TB_ItemName01.setText(GetItemName01);					//商品表記名
					TB_ItemSubRecomendLoc.setText(GetItemSubRecomendLoc);	//商品サブマスタ推奨ロケ
				}else {
					main_fm.add(ItemSearchBtn);
					TB_ItemCd.setEnabled(true);
				}
			}
		}else {
			main_fm.add(ItemSearchBtn);
		}
		
		
		main_fm.add(LB_ClCd);
		main_fm.add(LB_ClWh);
		main_fm.add(LB_ItemCd);
		main_fm.add(LB_ItemName01);
		main_fm.add(LB_RecomendLoc);
		main_fm.add(LB_LocName);
		main_fm.add(LB_Type);
		main_fm.add(LB_EntryDate);
		main_fm.add(LB_UpdateDate);
		main_fm.add(LB_EntryUser);
		main_fm.add(LB_UpdateUser);
		main_fm.add(LB_ItemSubRecomendLoc);
		
		main_fm.add(TB_ClCd);
		main_fm.add(TB_ClWh);
		main_fm.add(TB_ItemCd);
		main_fm.add(TB_ItemName01);
		main_fm.add(TB_RecomendLoc);
		main_fm.add(TB_LocName);
		main_fm.add(TB_Type);
		main_fm.add(TB_EntryDate);
		main_fm.add(TB_UpdateDate);
		main_fm.add(TB_EntryUser);
		main_fm.add(TB_UpdateUser);
		main_fm.add(TB_ItemSubRecomendLoc);

		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		main_fm.add(entry_btn);
		
		main_fm.setVisible(true);
		
		//商品検索画面
		final JFrame ItemSearch_fm 			= B100_FrameParts.FrameCreate(x+20,y+20,800,800,"Corgi00推奨ロケマスタ(荷主毎)登録・修正【商品検索】　WM100_ItemRecomendLocMst_01_RenewAndCreate","");
		JLabel ItemSearchUserinfo 			= B100_FrameParts.UserInfo();
		JButton ItemSearchExit_btn 			= B100_FrameParts.ExitBtn();
		JButton ItemSearchEntry_btn 		= B100_FrameParts.EntryBtn();
		
		JLabel LB_SearchItemCd  			= B100_FrameParts.JLabelSet(		  0, 50,130,20,"商品コード:"			,11,1);
		JLabel LB_SearchItemName  			= B100_FrameParts.JLabelSet(		  0, 75,130,20,"商品名:"				,11,1);
		
		final JTextField TB_SearchItemCd  	= B100_FrameParts.JTextFieldSet(	130, 50,100,20,""						,11,0);	//商品コード
		final JTextField TB_SearchItemName  = B100_FrameParts.JTextFieldSet(	130, 75,100,20,""						,11,0);	//商品名
		
		JLabel LB2_SearchItemCd  			= B100_FrameParts.JLabelSet(		230, 50, 80,20,"と一致"					,11,0);	//商品コード
		JLabel LB2_SearchItemName  			= B100_FrameParts.JLabelSet(		230, 75, 80,20,"を含む"					,11,0);	//商品名
		
		ItemSearch_fm.add(ItemSearchUserinfo);
		ItemSearch_fm.add(ItemSearchExit_btn);
		ItemSearch_fm.add(ItemSearchEntry_btn);
		
		ItemSearch_fm.add(LB_SearchItemCd);
		ItemSearch_fm.add(LB_SearchItemName);
		ItemSearch_fm.add(TB_SearchItemCd);
		ItemSearch_fm.add(TB_SearchItemName);
		ItemSearch_fm.add(LB2_SearchItemCd);
		ItemSearch_fm.add(LB2_SearchItemName);
		
		//検索ボタン
		JButton ItemSearchSearchBtn = B100_FrameParts.BtnSet(130,100,100,20,"検索",11);
		ItemSearch_fm.add(ItemSearchSearchBtn);
		
		Object[][] RtSettingItemMstRt = M100_ItemMstRt.RtItemMstRt();
		
		String[] ItemSearchcolumnNames01 = new String[RtSettingItemMstRt.length+1];
		
		ItemSearchcolumnNames01[0] = "Fg";
		for(int i=0;i<RtSettingItemMstRt.length;i++) {
			ItemSearchcolumnNames01[1+i] = ""+RtSettingItemMstRt[i][3];
		}
		
		//編集可能カラムの指定
		B100_TableControl.RenewTgt = new int[1];
		B100_TableControl.RenewTgt[0] = 0;

		final DefaultTableModel ItemSearchtableModel = new B100_TableControl.MyTableModel01(ItemSearchcolumnNames01,0);
		
		final JTable ItemSearchtb01 = new JTable(ItemSearchtableModel);
		ItemSearchtb01.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		ItemSearchtb01.setRowHeight(20*A00000_Main.Mul/A00000_Main.Div);
		ItemSearchtb01.setFont(new Font(A00000_Main.DefaultFont, Font.PLAIN, 12*A00000_Main.Mul/A00000_Main.Div));
		
		DefaultTableColumnModel ItemSearchcolumnModel01
		= (DefaultTableColumnModel)ItemSearchtb01.getColumnModel();
		
		//列幅初期設定 表示位置設定
		TableColumn ItemSearchcolumn = null;
		
		ItemSearchcolumn = ItemSearchcolumnModel01.getColumn( 0);	ItemSearchcolumn.setPreferredWidth( 30*A00000_Main.Mul/A00000_Main.Div);	//FG
		
		for(int i=0;i<RtSettingItemMstRt.length;i++) {
			if("int".equals((String)RtSettingItemMstRt[i][2])||"float".equals((String)RtSettingItemMstRt[i][2])) {
				ItemSearchcolumn = ItemSearchcolumnModel01.getColumn(1+i);	ItemSearchcolumn.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	ItemSearchcolumn.setCellRenderer(B100_FrameParts.rightCellRenderer());
			}else {
				ItemSearchcolumn = ItemSearchcolumnModel01.getColumn(1+i);	ItemSearchcolumn.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	ItemSearchcolumn.setCellRenderer(B100_FrameParts.leftCellRenderer());
			}
		}
		
		//スクロール用設定
		JScrollPane ItemSearchscpn01 = B100_FrameParts.JScrollPaneSet(10,150,760,500,ItemSearchtb01);
		ItemSearch_fm.add(ItemSearchscpn01);
		
		
		//ロケ検索画面
		final JFrame LocSearch_fm			= B100_FrameParts.FrameCreate(x+20,y+20,800,800,"Corgi00推奨ロケマスタ(荷主毎)登録・修正【ロケ検索】　WM100_ItemRecomendLocMst_01_RenewAndCreate","");
		JLabel LocSearchUserinfo 			= B100_FrameParts.UserInfo();
		JButton LocSearchExit_btn 			= B100_FrameParts.ExitBtn();
		JButton LocSearchEntry_btn 			= B100_FrameParts.EntryBtn();
		
		JLabel LB_SearchLoc					= B100_FrameParts.JLabelSet(  0, 50,100,20,"ロケーション:"	,11,1);
		JLabel LB_SearchLocName				= B100_FrameParts.JLabelSet(  0, 75,100,20,"ロケーション名:"	,11,1);
		JLabel LB_SearchType				= B100_FrameParts.JLabelSet(  0,100,100,20,"ロケタイプ:"		,11,1);
		
		final JTextField  TB_SearchLoc		= B100_FrameParts.JTextFieldSet(100, 50,100,20,"",11,0);										//ロケーション
		final JTextField  TB_SearchLocName	= B100_FrameParts.JTextFieldSet(100, 75,100,20,"",11,0);										//ロケーション名
		final JComboBox   TB_SearchType		= B100_FrameParts.JComboBoxSet( 100,100,100,20,B100_DefaultVariable.SearchLocType[0],11);	//ロケタイプ
		
		JLabel LB2_SearchLoc				= B100_FrameParts.JLabelSet(  200, 50,100,20,"で始まる"	,11,0);
		JLabel LB2_SearchLocName			= B100_FrameParts.JLabelSet(  200, 75,100,20,"を含む"		,11,0);
		
		LocSearch_fm.add(LocSearchUserinfo);
		LocSearch_fm.add(LocSearchExit_btn);
		LocSearch_fm.add(LocSearchEntry_btn);
		LocSearch_fm.add(LB_SearchLoc);
		
		LocSearch_fm.add(LB_SearchLocName);
		LocSearch_fm.add(LB_SearchType);
		LocSearch_fm.add(TB_SearchLoc);
		LocSearch_fm.add(TB_SearchLocName);
		LocSearch_fm.add(TB_SearchType);
		LocSearch_fm.add(LB2_SearchLoc);
		LocSearch_fm.add(LB2_SearchLocName);
		
		//検索ボタン
		JButton LocSearchSearchBtn = B100_FrameParts.BtnSet(100,125,100,20,"検索",11);
		LocSearch_fm.add(LocSearchSearchBtn);
		
		Object[][] RtSettingLocationMstRt = M100_LocationMstRt.RtLocationMstRt();
		
		String[] LocSearchcolumnNames01 = new String[RtSettingLocationMstRt.length+1];
		
		LocSearchcolumnNames01[0] = "Fg";
		for(int i=0;i<RtSettingLocationMstRt.length;i++) {
			LocSearchcolumnNames01[1+i] = ""+RtSettingLocationMstRt[i][3];
		}
		
		//編集可能カラムの指定
		B100_TableControl.RenewTgt = new int[1];
		B100_TableControl.RenewTgt[0] = 0;

		final DefaultTableModel LocSearchtableModel = new B100_TableControl.MyTableModel01(LocSearchcolumnNames01,0);
		
		final JTable LocSearchtb01 = new JTable(LocSearchtableModel);
		LocSearchtb01.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		LocSearchtb01.setRowHeight(20*A00000_Main.Mul/A00000_Main.Div);
		LocSearchtb01.setFont(new Font(A00000_Main.DefaultFont, Font.PLAIN, 12*A00000_Main.Mul/A00000_Main.Div));
		
		DefaultTableColumnModel LocSearchcolumnModel01
		= (DefaultTableColumnModel)LocSearchtb01.getColumnModel();
		
		//列幅初期設定 表示位置設定
		TableColumn LocSearchcolumn = null;
		
		LocSearchcolumn = LocSearchcolumnModel01.getColumn( 0);	LocSearchcolumn.setPreferredWidth( 30*A00000_Main.Mul/A00000_Main.Div);	//FG
		
		for(int i=0;i<RtSettingLocationMstRt.length;i++) {
			if("int".equals((String)RtSettingLocationMstRt[i][2])||"float".equals((String)RtSettingLocationMstRt[i][2])) {
				LocSearchcolumn = LocSearchcolumnModel01.getColumn(1+i);	LocSearchcolumn.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	LocSearchcolumn.setCellRenderer(B100_FrameParts.rightCellRenderer());
			}else {
				LocSearchcolumn = LocSearchcolumnModel01.getColumn(1+i);	LocSearchcolumn.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	LocSearchcolumn.setCellRenderer(B100_FrameParts.leftCellRenderer());
			}
		}
		
		//スクロール用設定
		JScrollPane LocSearchscpn01 = B100_FrameParts.JScrollPaneSet(10,175,760,500,LocSearchtb01);
		LocSearch_fm.add(LocSearchscpn01);
		
		RenewFg = true;
		
		//登録ボタン押下時の挙動
		entry_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				String GetClCd			= B100_DefaultVariable.ClList[1][TB_ClCd.getSelectedIndex()];
				String GetClWh			= B100_DefaultVariable.WhList[1][TB_ClWh.getSelectedIndex()];
				String GetItemCd		= TB_ItemCd.getText();
				String GetRecomendLoc	= TB_RecomendLoc.getText();
				
				if(null==GetClCd		){GetClCd			= "";}
				if(null==GetClWh		){GetClWh			= "";}
				if(null==GetItemCd		){GetItemCd			= "";}
				if(null==GetRecomendLoc	){GetRecomendLoc	= "";}
				
				GetClCd			= B100_TextControl.Trim(GetClCd);
				GetClWh			= B100_TextControl.Trim(GetClWh);
				GetItemCd		= B100_TextControl.Trim(GetItemCd);
				GetRecomendLoc	= B100_TextControl.Trim(GetRecomendLoc);
				
				ArrayList<String> ErrMsg= ErrCheck(GetClCd,GetClWh,GetItemCd,GetRecomendLoc);
				
				if(null!=ErrMsg&&0<ErrMsg.size()) {
					ErrView(ErrMsg);
				}else {
					DataEntry(GetClCd,GetClWh,GetItemCd,GetRecomendLoc);
					
					SetX=main_fm.getX();
					SetY=main_fm.getY();
					
					ItemSearch_fm.setVisible(false);
					LocSearch_fm.setVisible(false);
					
					ItemSearch_fm.dispose();
					LocSearch_fm.dispose();

					main_fm.setVisible(false);
					main_fm.dispose();
					
					ItemRecomendLocMstRenewAndCreate(0,0,GetClCd,GetClWh,GetItemCd);
				}
			}
		});
		
		//商品コードフォーカス消失時の挙動
		TB_ItemCd.addFocusListener(new FocusAdapter(){
			@Override
			public void focusLost(FocusEvent e){
				if(RenewFg) {
					String GetClCd	= B100_DefaultVariable.ClList[1][TB_ClCd.getSelectedIndex()];
					String GetClWh	= B100_DefaultVariable.ClList[1][TB_ClWh.getSelectedIndex()];
					String GetItemCd = TB_ItemCd.getText();	if(null==GetItemCd) {GetItemCd="";}
					
					if(!"".equals(GetItemCd)) {
						SetX=main_fm.getX();
						SetY=main_fm.getY();
						
						ItemSearch_fm.setVisible(false);
						LocSearch_fm.setVisible(false);
						
						ItemSearch_fm.dispose();
						LocSearch_fm.dispose();
	
						main_fm.setVisible(false);
						main_fm.dispose();
						
						ItemRecomendLocMstRenewAndCreate(0,0,GetClCd,GetClWh,GetItemCd);
					}
				}
			}
		});
		
		//商品検索画面登録ボタン押下時の挙動
		ItemSearchEntry_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					int RowCount = ItemSearchtableModel.getRowCount();
					for(int i=0;i<RowCount;i++) {
						if((boolean)ItemSearchtableModel.getValueAt(i, 0)) {
							String GetItemCd				= (String)ItemSearchtableModel.getValueAt(i, M100_ItemMstRt.ColItemCd+1);			//商品コード
							String GetItemName01			= (String)ItemSearchtableModel.getValueAt(i, M100_ItemMstRt.ColItemName01+1);	//商品表記名
							String GetItemSubRecomendLoc	= (String)ItemSearchtableModel.getValueAt(i, M100_ItemMstRt.ColRecomendLoc+1);	//商品サブマスタ推奨ロケ
							
							TB_ItemCd.setText(GetItemCd);							//商品コード
							TB_ItemName01.setText(GetItemName01);					//商品表記名
							TB_ItemSubRecomendLoc.setText(GetItemSubRecomendLoc);	//商品サブマスタ推奨ロケ
							
							TB_ItemCd.requestFocusInWindow();
							
							ItemSearch_fm.setVisible(false);
						}
					}
					RenewFg = true;
				}
			}
		});
		
		//ロケーション検索画面登録ボタン押下時の挙動
		LocSearchEntry_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					int RowCount = LocSearchtableModel.getRowCount();
					for(int i=0;i<RowCount;i++) {
						if((boolean)LocSearchtableModel.getValueAt(i, 0)) {
							String GetLoc			= ""+LocSearchtableModel.getValueAt(i, M100_LocationMstRt.ColLoc+1);			//ロケーション
							String GetLocName		= ""+LocSearchtableModel.getValueAt(i, M100_LocationMstRt.ColLocName+1);		//ロケーション名
							String GetType			= ""+LocSearchtableModel.getValueAt(i, M100_LocationMstRt.ColType+1);		//ロケタイプ
							
							TB_RecomendLoc.setText(GetLoc);	//推奨ロケ
							TB_LocName.setText(GetLocName);	//ロケーション名
							for(int i01=0;i01<B100_DefaultVariable.LocType[1].length;i01++) {
								if(GetType.equals(B100_DefaultVariable.LocType[1][i01])) {
									TB_Type.setSelectedIndex(i01);
								}
							}
							LocSearch_fm.setVisible(false);
						}
					}
					RenewFg = true;
				}
			}
		});
		
		//商品検索画面検索ボタン押下時の挙動
		ItemSearchSearchBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					int RowCount = ItemSearchtableModel.getRowCount();
					for(int i=0;i<RowCount;i++) {
						ItemSearchtableModel.removeRow(0);
					}
					
					String GetClCd				= B100_DefaultVariable.ClList[1][TB_ClCd.getSelectedIndex()];
					String GetSearchItemCd		= TB_SearchItemCd.getText();
					String GetSearchItemName	= TB_SearchItemName.getText();
					
					String GetClGp = GlGpRt(GetClCd);
					
					Object[][] ItemMstRt	= ItemMstRt(GetClGp,GetSearchItemCd,GetSearchItemName);
					
					for(int i=0;i<ItemMstRt.length;i++) {
						Object[] SetOb = new Object[ItemMstRt[i].length+1];
						SetOb[ 0] = false;
						for(int i01=0;i01<ItemMstRt[i].length;i01++) {
							SetOb[i01+1] = ItemMstRt[i][i01];
						}
						ItemSearchtableModel.addRow(SetOb);
					}
					
					RenewFg = true;
				}
			}
		});
		
		//ロケ検索画面検索ボタン押下時の挙動
		LocSearchSearchBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					int RowCount = LocSearchtableModel.getRowCount();
					for(int i=0;i<RowCount;i++) {
						LocSearchtableModel.removeRow(0);
					}
					String GetClWh			= B100_DefaultVariable.WhList[1][TB_ClWh.getSelectedIndex()];
					String GetClCd			= B100_DefaultVariable.ClList[1][TB_ClCd.getSelectedIndex()];
					String GetSearchLoc		= TB_SearchLoc.getText();
					String GetSearchLocName	= TB_SearchLocName.getText();
					String GetSearchType	= B100_DefaultVariable.SearchLocType[1][TB_SearchType.getSelectedIndex()];
					boolean LocExactMatch = false;
					
					Object[][] LocationMstRt	= LocationMstRt(GetClWh,GetClCd,GetSearchLoc,GetSearchLocName,GetSearchType,LocExactMatch);
					for(int i=0;i<LocationMstRt.length;i++) {
						Object[] SetOb = new Object[LocationMstRt[i].length+1];
						SetOb[ 0] = false;
						for(int i01=0;i01<LocationMstRt[i].length;i01++) {
							SetOb[i01+1] = LocationMstRt[i][i01];
						}
						LocSearchtableModel.addRow(SetOb);
					}
					RenewFg = true;
				}
			}
		});
		
		//商品検索ボタン押下時の挙動
		ItemSearchBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					int RowCount = ItemSearchtableModel.getRowCount();
					for(int i=0;i<RowCount;i++) {
						ItemSearchtableModel.removeRow(0);
					}
					
					TB_SearchItemCd.setText("");
					TB_SearchItemName.setText("");
					
					ItemSearch_fm.setVisible(true);
					
					RenewFg = true;
				}
			}
		});
		
		//ロケーション検索ボタン押下時の挙動
		LocSearchBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					int RowCount = LocSearchtableModel.getRowCount();
					for(int i=0;i<RowCount;i++) {
						LocSearchtableModel.removeRow(0);
					}
					
					TB_SearchLoc.setText("");
					TB_SearchLocName.setText("");
					TB_SearchType.setSelectedIndex(0);
					
					LocSearch_fm.setVisible(true);
					
					RenewFg = true;
				}
			}
		});
		
		//商品検索チェックボックス操作時の挙動
		ItemSearchtableModel.addTableModelListener(new TableModelListener(){
			public void tableChanged(TableModelEvent e){
				if(RenewFg) {
					RenewFg = false;
					int row_count = ItemSearchtableModel.getRowCount();
					Boolean setBL=Boolean.valueOf(false);
					for(int i=0;i<row_count;i++){
						if(i!=e.getFirstRow()){
							ItemSearchtableModel.setValueAt(setBL, i, 0);
						}else {
	
						}
					}
					RenewFg = true;
				}
			}
		});
		
		//ロケーション検索チェックボックス操作時の挙動
		LocSearchtableModel.addTableModelListener(new TableModelListener(){
			public void tableChanged(TableModelEvent e){
				if(RenewFg) {
					RenewFg = false;
					int row_count = LocSearchtableModel.getRowCount();
					Boolean setBL=Boolean.valueOf(false);
					for(int i=0;i<row_count;i++){
						if(i!=e.getFirstRow()){
							LocSearchtableModel.setValueAt(setBL, i, 0);
						}else {
	
						}
					}
					RenewFg = true;
				}
			}
		});
		
		//【商品検索】EXITボタン押下時の挙動
		ItemSearchExit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				ItemSearch_fm.setVisible(false);
			}
		});
		//【ロケ検索】EXITボタン押下時の挙動
		LocSearchExit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				LocSearch_fm.setVisible(false);
			}
		});
		
		//EXITボタン押下時の挙動
		exit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();
				
				ItemSearch_fm.setVisible(false);
				LocSearch_fm.setVisible(false);
				
				ItemSearch_fm.dispose();
				LocSearch_fm.dispose();

				main_fm.setVisible(false);
				main_fm.dispose();
				WM100_ItemRecomendLocMst_00_Search.ItemRecomendLocMstSearch(0, 0);
			}
		});
	}
	
	private static void DataEntry(String GetClCd,
			String GetClWh,
			String GetItemCd,
			String GetRecomendLoc
			) {
		String now_dtm = B100_DateTimeControl.dtmString2(B100_DateTimeControl.dtm()[1])[1];
		
		Object[][] SetString = {
				 {"ClCd"		,"1","1","Key"	,GetClCd}				//荷主コード
				,{"ClWh"		,"1","1","Key"	,GetClWh}				//担当倉庫コード
				,{"ItemCd"		,"1","1","Key"	,GetItemCd}				//商品コード
				,{"RecomendLoc"	,"1","1",""		,GetRecomendLoc}		//推奨ロケ
				,{"EntryDate"	,"1","0",""		,now_dtm}				//データ登録日時
				,{"UpdateDate"	,"1","1",""		,now_dtm}				//データ更新日時
				,{"EntryUser"	,"1","0",""		,"(" + A00000_Main.LoginUserId + ")" + A00000_Main.LoginUserName}				//登録者
				,{"UpdateUser"	,"1","1",""		,"(" + A00000_Main.LoginUserId + ")" + A00000_Main.LoginUserName}				//更新者
				};
		
		String tgt_table = "WW00630ItemRecomendLoc";
		String TgtDB = "WANKO";
		int non_msg_fg = 1;
		
		A100_InsertUpdateSQL.InsertUpdateOneRecord(SetString,tgt_table,TgtDB,non_msg_fg);
	}
	
	private static ArrayList<String> ErrCheck(
			String GetClCd,
			String GetClWh,
			String GetItemCd,
			String GetRecomendLoc
			) {
		ArrayList<String> ErrMsg = new ArrayList<String>();
		if("".equals(GetClCd)) {ErrMsg.add("荷主コードは必須です");}
		if("".equals(GetClWh)) {ErrMsg.add("倉庫コードは必須です");}
		if("".equals(GetItemCd)) {ErrMsg.add("商品コードは必須です");}
		if("".equals(GetRecomendLoc)) {ErrMsg.add("ロケコードは必須です");}
		
		String GetClGp = GlGpRt(GetClCd);
		Object[][] ItemMstRt = ItemMstRt(GetClGp,GetItemCd,"");
		if(1!=ItemMstRt.length) {
			ErrMsg.add("("+GetItemCd+")商品コードが不正です");
		}
		boolean LocExactMatch = true;
		Object[][] LocationMstRt = LocationMstRt(GetClWh,GetClCd,GetRecomendLoc,"","",LocExactMatch);
		if(1!=LocationMstRt.length) {
			ErrMsg.add("("+GetRecomendLoc+")ロケーションコードが不正です");
		}
		
		return ErrMsg;
	}
	
	private static void ErrView(ArrayList<String>ErrMsg) {
		//必要フォルダを生成する
		String FLD_PATH = A00000_Main.MainFLD+"\\MstControl";
		B100_FolderCheck.FLD_CHECK(FLD_PATH);
		FLD_PATH = A00000_Main.MainFLD+"\\MstControl\\ItemRecomendLocMst";
		B100_FolderCheck.FLD_CHECK(FLD_PATH);
		FLD_PATH = A00000_Main.MainFLD+"\\MstControl\\ItemRecomendLocMst\\Err";
		B100_FolderCheck.FLD_CHECK(FLD_PATH);
		FLD_PATH = A00000_Main.MainFLD+"\\MstControl\\ItemRecomendLocMst\\BK";
		B100_FolderCheck.FLD_CHECK(FLD_PATH);
		
		//ファイルに出力
		String NowDTM=B100_DateTimeControl.dtmString2(B100_DateTimeControl.dtm()[1])[1].replace(" ", "").replace("/", "").replace(":", "");
		
		FLD_PATH = A00000_Main.MainFLD+"\\MstControl\\ItemRecomendLocMst\\Err";
		
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

	private static String GlGpRt(String ClCd) {
		//荷主マスタを元に荷主グループCD返却
		String ClGp = A00000_Main.ClGp;
		
		if(null==ClCd) {ClCd="";}
		if("".equals(ClCd)) {ClCd=A00000_Main.ClCd;}
		
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
		
		SearchCLCD.add(ClCd);
		
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
		
		if(1==ClMstRt.length) {
			ClGp = (String)ClMstRt[0][M100_ClMstRt.ColClGpCD];		//荷主グループCD
		}
		
		return ClGp;
	}
	
	private static Object[][] ItemMstRt(String GetClGp,String GetSearchItemCd,String GetSearchItemName){
		if(null==GetClGp			) {GetClGp="";}
		if(null==GetSearchItemCd	) {GetSearchItemCd="";}
		if(null==GetSearchItemName	) {GetSearchItemName="";}
		
		ArrayList<String> SearchClCd				= new ArrayList<String>();	//荷主コード
		ArrayList<String> SearchClGpCd 				= new ArrayList<String>();	//荷主グループコード
		ArrayList<String> SearchItemCd 				= new ArrayList<String>();	//商品コード
		ArrayList<String> SearchClItemCd 			= new ArrayList<String>();	//荷主商品コード
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
		boolean AllSearch = true;
		
		SearchClGpCd.add(GetClGp);
		if(!"".equals(GetSearchItemCd	)) {SearchItemCd.add(GetSearchItemCd);}
		if(!"".equals(GetSearchItemName	)) {SearchItemName.add(GetSearchItemName);}
		
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
	
	private static Object[][] LocationMstRt(
			String GetClWh,
			String GetClCd,
			String GetSearchLoc,
			String GetSearchLocName,
			String GetSearchType,
			boolean LocExactMatch
			){
		if(null==GetClWh) {GetClWh="";}
		if(null==GetClCd) {GetClCd="";}
		if(null==GetSearchLoc) {GetSearchLoc="";}
		if(null==GetSearchLocName) {GetSearchLocName="";}
		if(null==GetSearchType) {GetSearchType="";}
		
		ArrayList<String> SearchClCd 	= new ArrayList<String>();	//荷主コード
		ArrayList<String> SearchWhCd 	= new ArrayList<String>();	//倉庫コード
		ArrayList<String> SearchLoc 	= new ArrayList<String>();	//ロケーション
		ArrayList<String> SearchLocName = new ArrayList<String>();	//ロケーション名
		ArrayList<String> SearchType 	= new ArrayList<String>();	//ロケタイプ
		boolean AllSearch = true;
		
		if(!"".equals(GetClWh			)) {SearchWhCd.add(GetClWh);}
		if(!"".equals(GetClCd			)) {SearchClCd.add(GetClCd);}
		if(!"".equals(GetSearchLoc		)) {SearchLoc.add(GetSearchLoc);}
		if(!"".equals(GetSearchLocName	)) {SearchLocName.add(GetSearchLocName);}
		if(!"".equals(GetSearchType		)) {SearchType.add(GetSearchType);}
		
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
	
	private static Object[][] ItemRecomendLocMstRt(String TgtClCd,String TgtWhCd,String TgtItemCd){
		ArrayList<String> SearchClCd		= new ArrayList<String>();	//荷主コード
		ArrayList<String> SearchClWh		= new ArrayList<String>();	//担当倉庫コード
		ArrayList<String> SearchClGpCD		= new ArrayList<String>();	//荷主グループCD
		ArrayList<String> SearchItemCd		= new ArrayList<String>();	//商品コード
		ArrayList<String> SearchItemName01	= new ArrayList<String>();	//商品表記名
		ArrayList<String> SearchRecomendLoc	= new ArrayList<String>();	//推奨ロケ
		ArrayList<String> SearchLocName		= new ArrayList<String>();	//ロケーション名
		ArrayList<Integer> SearchType		= new ArrayList<Integer>();	//ロケタイプ　0:通常　1:保管　8:入荷時　9:引当禁止
		boolean LocExactMatch = false;									//ロケーション完全一致
		boolean AllSearch = false;
		
		SearchClCd.add(TgtClCd);		//荷主コード
		SearchClWh.add(TgtWhCd);		//担当倉庫コード
		SearchItemCd.add(TgtItemCd);	//商品コード
		
		Object[][] ItemRecomendLocMstRt	= M100_ItemRecomendLocMstRt.ItemRecomendLocMstRt(
				SearchClCd,			//荷主コード
				SearchClWh,			//担当倉庫コード
				SearchClGpCD,		//荷主グループCD
				SearchItemCd,		//商品コード
				SearchItemName01,	//商品表記名
				SearchRecomendLoc,	//推奨ロケ
				SearchLocName,		//ロケーション名
				SearchType,			//ロケタイプ　0:通常　1:保管　8:入荷時　9:引当禁止
				LocExactMatch,		//ロケーション完全一致
				AllSearch);
		return ItemRecomendLocMstRt;
	}
}