import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class WM100_PostMst_02_ExcelEntry{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	
	public static void PostMstExcelEntry(int x,int y,String TgtFilePath) {
		A00000_Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;

		final JFrame main_fm = B100_FrameParts.FrameCreate(x,y,600,200,"Corgi00郵便番号登録（エクセル）","");
		JLabel userinfo = B100_FrameParts.UserInfo();
		JButton exit_btn = B100_FrameParts.ExitBtn();
		JButton entry_btn = B100_FrameParts.EntryBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		main_fm.add(entry_btn);
		
		String SheetName = "";
		
		final String[] SheetList = B100_ExcellControl.ExcellSheetList(TgtFilePath);
		
		if(1==SheetList.length) {
			SheetName = SheetList[0];
		}
		
		JLabel LB_SheetList				= B100_FrameParts.JLabelSet(		 20, 45,300,20,"登録するシートを選択してください"		,11,0);
		final JComboBox   TB_SheetList	= B100_FrameParts.JComboBoxSet( 	 20, 70,250,20,SheetList,11);	//シート一覧
		
		main_fm.add(LB_SheetList);
		main_fm.add(TB_SheetList);	//シート一覧
		
		main_fm.setVisible(true);
		RenewFg = true;
		
		//登録ボタン押下時の挙動
		entry_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				String SheetName = SheetList[TB_SheetList.getSelectedIndex()];
				
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				PostMstExcelEntryMain(0,0,TgtFilePath,SheetName);
			}
		});
		
		//EXITボタン押下時の挙動
		exit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				WM100_PostMst_00_Search.PostMstSearch(0, 0);
			}
		});
	}
	
	public static void PostMstExcelEntryMain(int x,int y,String TgtFilePath,String SheetName) {
		A00000_Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		
		final JFrame main_fm = B100_FrameParts.FrameCreate(x,y,750,750,"Corgi00郵便番号登録（エクセル）","");
		JLabel userinfo = B100_FrameParts.UserInfo();
		JButton exit_btn = B100_FrameParts.ExitBtn();
		JButton entry_btn = B100_FrameParts.EntryBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		main_fm.add(entry_btn);
		
		JLabel LB_SheetList	= B100_FrameParts.JLabelSet(	10, 40,300,20,"以下のデータを登録しようとしています",11,0);
		main_fm.add(LB_SheetList);
		
		String[] Title = B100_RtObjectCreate.RtTitleName(M100_PostMstRt.RtSettingPostRt());
				
		String[] columnNames01 = {
								 Title[M100_PostMstRt.ColPOST]
								,Title[M100_PostMstRt.ColPREFECTURES]
								,Title[M100_PostMstRt.ColMUNICI01]
								,Title[M100_PostMstRt.ColMUNICI02]
								,Title[M100_PostMstRt.ColMUNICIPALITY_CD]
								};
		
		//編集可能カラムの指定
		B100_TableControl.RenewTgt = new int[1];
		B100_TableControl.RenewTgt[0] = -1;
		final DefaultTableModel MainFmTableModel = new B100_TableControl.MyTableModel01(columnNames01,0);
		
		final JTable tb01 = new JTable(MainFmTableModel);
		tb01.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tb01.setRowHeight(20*A00000_Main.Mul/A00000_Main.Div);
		tb01.setFont(new Font(A00000_Main.DefaultFont, Font.PLAIN, 12*A00000_Main.Mul/A00000_Main.Div));
		
		DefaultTableColumnModel columnModel01
		= (DefaultTableColumnModel)tb01.getColumnModel();
		
		//列幅初期設定 表示位置設定
		TableColumn column = null;
		
		column = columnModel01.getColumn(0);	column.setPreferredWidth(100*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.leftCellRenderer());	//郵便番号
		column = columnModel01.getColumn(1);	column.setPreferredWidth(100*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.leftCellRenderer());	//県
		column = columnModel01.getColumn(2);	column.setPreferredWidth(200*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.leftCellRenderer());	//市区町村
		column = columnModel01.getColumn(3);	column.setPreferredWidth(200*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.leftCellRenderer());	//町丁目
		column = columnModel01.getColumn(4);	column.setPreferredWidth(100*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.leftCellRenderer());	//市区町村CD
		
		//スクロール用設定
		JScrollPane scpn01 = B100_FrameParts.JScrollPaneSet(10,65,700,550,tb01);
		main_fm.add(scpn01);
		
		
		//ヘッダ行取得⇒フィールド名判定
		//必要フィールドなければシート選択に戻る
		Object[][] HeaderRead = B100_ExcellControl.ExcellRead2(TgtFilePath,SheetName,1,0);
		boolean ErrFg = false;
		
		String[] NeedCol = {
						 Title[M100_PostMstRt.ColPOST]
						,Title[M100_PostMstRt.ColPREFECTURES]
						,Title[M100_PostMstRt.ColMUNICI01]
						,Title[M100_PostMstRt.ColMUNICI02]
						,Title[M100_PostMstRt.ColMUNICIPALITY_CD]
						};
		
		int[] TgtCol = {
						 -1	//郵便番号
						,-1	//県
						,-1	//市区町村
						,-1	//町丁目
						,-1	//市区町村CD
						};
		if(null==HeaderRead||0==HeaderRead.length) {
			ErrFg = true;
		}else {
			for(int i01=0;i01<NeedCol.length;i01++) {
				boolean UnHitFg = true;
				
				for(int i02=0;i02<HeaderRead[0].length;i02++) {
					if(NeedCol[i01].equals(""+HeaderRead[0][i02])) {
						UnHitFg = false;
						TgtCol[i01] = i02;
						
						i02=1+HeaderRead[0].length;
					}
				}
				if(UnHitFg) {
					ErrFg = true;
				}
			}
		}
		
		if(ErrFg) {
			String Msg = "ヘッダ行で取込ファイルのレイアウト判別ができませんでした。\n確認しやがれください\n";
			for(int i=0;i<NeedCol.length;i++) {
				if(0<i&&0==i%5) {
					Msg = Msg + (String)NeedCol[i] + ",\n";
				}else {
					Msg = Msg + (String)NeedCol[i] + ",";
				}
			}
			Msg = Msg+"\nがヘッダに必要です";
			
			JOptionPane.showMessageDialog(null, Msg);
			PostMstExcelEntry(0,0,TgtFilePath);
		}else {
			int[] ClmnType = new int[HeaderRead[0].length];
			for(int i=0;i<ClmnType.length;i++) {ClmnType[i]=1;}

			ClmnType[TgtCol[0]]=1;	//郵便番号
			ClmnType[TgtCol[1]]=1;	//県
			ClmnType[TgtCol[2]]=1;	//市区町村
			ClmnType[TgtCol[3]]=1;	//町丁目
			ClmnType[TgtCol[4]]=1;	//市区町村CD
			
			Object[][] ExcellRead = B100_ExcellControl.ExcellRead(TgtFilePath,SheetName,ClmnType,true);
			
			if(0<ExcellRead.length&&ClmnType.length<=ExcellRead[0].length) {
				for(int i=0;i<ExcellRead.length;i++) {
					Object[] SetOb = new Object[6];
					
					SetOb[0] = B100_TextControl.num_only_String(""+ExcellRead[i][TgtCol[0]]);	//郵便番号
					if(!"".equals(""+SetOb[0])) {
						
						if(null==ExcellRead[i][TgtCol[1]]){ExcellRead[i][TgtCol[1]]="";}
						if(null==ExcellRead[i][TgtCol[2]]){ExcellRead[i][TgtCol[2]]="";}
						if(null==ExcellRead[i][TgtCol[3]]){ExcellRead[i][TgtCol[3]]="";}
						if(null==ExcellRead[i][TgtCol[4]]){ExcellRead[i][TgtCol[4]]="";}
						
						ExcellRead[i][TgtCol[1]] = B100_TextControl.Trim(""+ExcellRead[i][TgtCol[1]]);
						ExcellRead[i][TgtCol[2]] = B100_TextControl.Trim(""+ExcellRead[i][TgtCol[2]]);
						ExcellRead[i][TgtCol[3]] = B100_TextControl.Trim(""+ExcellRead[i][TgtCol[3]]);
						ExcellRead[i][TgtCol[4]] = B100_TextControl.Trim(""+ExcellRead[i][TgtCol[4]]);
						
						SetOb[1] = ExcellRead[i][TgtCol[1]];
						SetOb[2] = ExcellRead[i][TgtCol[2]];
						SetOb[3] = ExcellRead[i][TgtCol[3]];
						SetOb[4] = ExcellRead[i][TgtCol[4]];
						
						MainFmTableModel.addRow(SetOb);
					}
				}
			}
			
			main_fm.setVisible(true);
		}
		RenewFg = true;
		
		//Entryボタン押下時の挙動
		entry_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				int RowCount = MainFmTableModel.getRowCount();
				
				String tgt_table = "M0010_PostMst";
				String[][] field_name = new String[5][3];
				String[][] entry_data = new String[RowCount][5];
				String[] judg_field = new String[1];
				String[][] judg_data = new String[RowCount][1];
				String TgtDB = "POST";
				int non_msg_fg = 0;
				
				judg_field[0] = "POST";					//郵便番号
				
				field_name[0][0] = "POST";				//郵便番号
				field_name[1][0] = "PREFECTURES";		//県
				field_name[2][0] = "MUNICI01";			//市区町村
				field_name[3][0] = "MUNICI02";			//町丁目
				field_name[4][0] = "MUNICIPALITY_CD";	//市区町村CD

				field_name[0][1] = "1";	//郵便番号
				field_name[1][1] = "1";	//県
				field_name[2][1] = "1";	//市区町村
				field_name[3][1] = "1";	//町丁目
				field_name[4][1] = "1";	//市区町村CD

				field_name[0][2] = "1";	//郵便番号
				field_name[1][2] = "1";	//県
				field_name[2][2] = "1";	//市区町村
				field_name[3][2] = "1";	//町丁目
				field_name[4][2] = "1";	//市区町村CD
				
				for(int i=0;i<RowCount;i++) {
					judg_data[i][0] = B100_TextControl.Trim(""+MainFmTableModel.getValueAt(i, 0));	//郵便番号
					
					entry_data[i][0] = B100_TextControl.Trim(""+MainFmTableModel.getValueAt(i, 0));	//郵便番号
					entry_data[i][1] = B100_TextControl.Trim(""+MainFmTableModel.getValueAt(i, 1));	//県
					entry_data[i][2] = B100_TextControl.Trim(""+MainFmTableModel.getValueAt(i, 2));	//市区町村
					entry_data[i][3] = B100_TextControl.Trim(""+MainFmTableModel.getValueAt(i, 3));	//町丁目
					entry_data[i][4] = B100_TextControl.Trim(""+MainFmTableModel.getValueAt(i, 4));	//市区町村CD
				}
				A100_InsertUpdateSQL.RUN_SQLS_EU(tgt_table, field_name, entry_data, judg_field, judg_data, non_msg_fg,TgtDB);
				
				String FN = B100_FolderCheck.FILENAME(TgtFilePath);
				String FP = B100_FolderCheck.FILE_FLD(TgtFilePath);
				String BFP = FP + "\\BK";
				B100_FolderCheck.FILE_BACKUP(FP,BFP,FN);
				
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				WM100_PostMst_00_Search.PostMstSearch(0, 0);
			}
		});
		
		//EXITボタン押下時の挙動
		exit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				WM100_PostMst_00_Search.PostMstSearch(0, 0);
			}
		});
	}
}