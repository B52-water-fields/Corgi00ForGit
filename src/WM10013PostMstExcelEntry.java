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

public class WM10013PostMstExcelEntry{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	
	public static void PostMstExcelEntry(int x,int y,String TgtFilePath) {
		A00000Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;

		final JFrame main_fm = B00110FrameParts.FrameCreate(x,y,600,200,"Corgi00郵便番号登録（エクセル）","");
		JLabel userinfo = B00110FrameParts.UserInfo();
		JButton exit_btn = B00110FrameParts.ExitBtn();
		JButton entry_btn = B00110FrameParts.EntryBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		main_fm.add(entry_btn);
		
		String SheetName = "";
		
		final String[] SheetList = B00060ToolsExcellControl.ExcellSheetList(TgtFilePath);
		
		if(1==SheetList.length) {
			SheetName = SheetList[0];
		}
		
		JLabel LB_SheetList				= B00110FrameParts.JLabelSet(		 20, 45,300,20,"登録するシートを選択してください"		,11,0);
		final JComboBox   TB_SheetList	= B00110FrameParts.JComboBoxSet( 	 20, 70,250,20,SheetList,11);	//シート一覧
		
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
				WM10010PostMstSearch.PostMstSearch(0, 0);
			}
		});
	}
	
	public static void PostMstExcelEntryMain(int x,int y,String TgtFilePath,String SheetName) {
		A00000Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		
		final JFrame main_fm = B00110FrameParts.FrameCreate(x,y,750,750,"Corgi00郵便番号登録（エクセル）","");
		JLabel userinfo = B00110FrameParts.UserInfo();
		JButton exit_btn = B00110FrameParts.ExitBtn();
		JButton entry_btn = B00110FrameParts.EntryBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		main_fm.add(entry_btn);
		
		JLabel LB_SheetList	= B00110FrameParts.JLabelSet(	10, 40,300,20,"以下のデータを登録しようとしています",11,0);
		main_fm.add(LB_SheetList);
		
		String[] columnNames01 = {
								 "郵便番号"
								,"県"
								,"市区町村"
								,"町丁目"
								,"市区町村CD"
								};
		
		//編集可能カラムの指定
		B10010TableControl.RenewTgt = new int[1];
		B10010TableControl.RenewTgt[0] = -1;
		final DefaultTableModel tableModel_ms01 = new B10010TableControl.MyTableModel01(columnNames01,0);
		
		final JTable tb01 = new JTable(tableModel_ms01);
		tb01.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tb01.setRowHeight(20*A00000Main.Mul/A00000Main.Div);
		tb01.setFont(new Font(A00000Main.DefaultFont, Font.PLAIN, 12*A00000Main.Mul/A00000Main.Div));
		
		DefaultTableColumnModel columnModel01
		= (DefaultTableColumnModel)tb01.getColumnModel();
		
		//列幅初期設定 表示位置設定
		TableColumn column = null;
		
		column = columnModel01.getColumn(0);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//郵便番号
		column = columnModel01.getColumn(1);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//県
		column = columnModel01.getColumn(2);	column.setPreferredWidth(200*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//市区町村
		column = columnModel01.getColumn(3);	column.setPreferredWidth(200*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//町丁目
		column = columnModel01.getColumn(4);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//市区町村CD
		
		//スクロール用設定
		JScrollPane scpn01 = B00110FrameParts.JScrollPaneSet(10,65,700,550,tb01);
		main_fm.add(scpn01);
		
		
		//ヘッダ行取得⇒フィールド名判定
		//必要フィールドなければシート選択に戻る
		Object[][] HeaderRead = B00060ToolsExcellControl.ExcellRead2(TgtFilePath,SheetName,1,0);
		boolean ErrFg = false;
		
		String[] NeedCol = {
						 "郵便番号"
						,"県"
						,"市区町村"
						,"町丁目"
						,"市区町村CD"
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
			JOptionPane.showMessageDialog(null, "ヘッダ行で取込ファイルのレイアウト判別ができませんでした。\n確認しやがれください\n"
													+ "郵便番号,県,市区町村,町丁目,市区町村CD が必要です");
			PostMstExcelEntry(0,0,TgtFilePath);
		}else {
			int[] ClmnType = new int[HeaderRead[0].length];
			for(int i=0;i<ClmnType.length;i++) {ClmnType[i]=1;}

			ClmnType[TgtCol[0]]=1;	//郵便番号
			ClmnType[TgtCol[1]]=1;	//県
			ClmnType[TgtCol[2]]=1;	//市区町村
			ClmnType[TgtCol[3]]=1;	//町丁目
			ClmnType[TgtCol[4]]=1;	//市区町村CD
			
			Object[][] ExcellRead = B00060ToolsExcellControl.ExcellRead(TgtFilePath,SheetName,ClmnType,true);
			
			if(0<ExcellRead.length&&ClmnType.length<=ExcellRead[0].length) {
				for(int i=0;i<ExcellRead.length;i++) {
					Object[] SetOb = new Object[6];
					
					SetOb[0] = B00020ToolsTextControl.num_only_String(""+ExcellRead[i][TgtCol[0]]);	//郵便番号
					if(!"".equals(""+SetOb[0])) {
						
						if(null==ExcellRead[i][TgtCol[1]]){ExcellRead[i][TgtCol[1]]="";}
						if(null==ExcellRead[i][TgtCol[2]]){ExcellRead[i][TgtCol[2]]="";}
						if(null==ExcellRead[i][TgtCol[3]]){ExcellRead[i][TgtCol[3]]="";}
						if(null==ExcellRead[i][TgtCol[4]]){ExcellRead[i][TgtCol[4]]="";}
						
						ExcellRead[i][TgtCol[1]] = B00020ToolsTextControl.Trim(""+ExcellRead[i][TgtCol[1]]);
						ExcellRead[i][TgtCol[2]] = B00020ToolsTextControl.Trim(""+ExcellRead[i][TgtCol[2]]);
						ExcellRead[i][TgtCol[3]] = B00020ToolsTextControl.Trim(""+ExcellRead[i][TgtCol[3]]);
						ExcellRead[i][TgtCol[4]] = B00020ToolsTextControl.Trim(""+ExcellRead[i][TgtCol[4]]);
						
						SetOb[1] = ExcellRead[i][TgtCol[1]];
						SetOb[2] = ExcellRead[i][TgtCol[2]];
						SetOb[3] = ExcellRead[i][TgtCol[3]];
						SetOb[4] = ExcellRead[i][TgtCol[4]];
						
						tableModel_ms01.addRow(SetOb);
					}
				}
			}
			
			main_fm.setVisible(true);
		}
		RenewFg = true;
		
		//Entryボタン押下時の挙動
		entry_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				int RowCount = tableModel_ms01.getRowCount();
				
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
					judg_data[i][0] = B00020ToolsTextControl.Trim(""+tableModel_ms01.getValueAt(i, 0));	//郵便番号
					
					entry_data[i][0] = B00020ToolsTextControl.Trim(""+tableModel_ms01.getValueAt(i, 0));	//郵便番号
					entry_data[i][1] = B00020ToolsTextControl.Trim(""+tableModel_ms01.getValueAt(i, 1));	//県
					entry_data[i][2] = B00020ToolsTextControl.Trim(""+tableModel_ms01.getValueAt(i, 2));	//市区町村
					entry_data[i][3] = B00020ToolsTextControl.Trim(""+tableModel_ms01.getValueAt(i, 3));	//町丁目
					entry_data[i][4] = B00020ToolsTextControl.Trim(""+tableModel_ms01.getValueAt(i, 4));	//市区町村CD
				}
				A00020InsertUdateSQL.RUN_SQLS_EU(tgt_table, field_name, entry_data, judg_field, judg_data, non_msg_fg,TgtDB);
				
				String FN = B00040ToolsFolderCheck.FILENAME(TgtFilePath);
				String FP = B00040ToolsFolderCheck.FILE_FLD(TgtFilePath);
				String BFP = FP + "\\BK";
				B00040ToolsFolderCheck.FILE_BACKUP(FP,BFP,FN);
				
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				WM10010PostMstSearch.PostMstSearch(0, 0);
			}
		});
		
		//EXITボタン押下時の挙動
		exit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				WM10010PostMstSearch.PostMstSearch(0, 0);
			}
		});
	}
}