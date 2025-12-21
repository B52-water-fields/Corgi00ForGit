import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class WM10012PostMstCreateSum{
	static int SetX;
	static int SetY;
	public static void PostMstCreateSum(int x,int y) {
		A00000Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		
		final JFrame main_fm = B00110FrameParts.FrameCreate(x,y,780,700,"Corgi00郵便番号一括登録","");
		JLabel userinfo = B00110FrameParts.UserInfo();
		JButton exit_btn = B00110FrameParts.ExitBtn();
		JButton entry_btn = B00110FrameParts.EntryBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		main_fm.add(entry_btn);
		
		JLabel LB_EntryPost = B00110FrameParts.JLabelSet(				 10,40,100,20,"郵便番号"  ,11,2);
		JLabel LB_EntryPref = B00110FrameParts.JLabelSet(				120,40,100,20,"県"        ,11,2);
		JLabel LB_EntryMunic01 = B00110FrameParts.JLabelSet(			230,40,200,20,"市区町村"  ,11,2);
		JLabel LB_EntryMunic02 = B00110FrameParts.JLabelSet(			440,40,200,20,"町丁目"    ,11,2);
		JLabel LB_EntryMunicipalityCd = B00110FrameParts.JLabelSet(	650,40,100,20,"市区町村CD",11,2);
		
		final JTextArea TB_EntryPost = new JTextArea();				//郵便番号
		final JTextArea TB_EntryPref = new JTextArea();				//県
		final JTextArea TB_EntryMunic01 = new JTextArea();			//市区町村
		final JTextArea TB_EntryMunic02 = new JTextArea();			//町丁目
		final JTextArea TB_EntryMunicipalityCd = new JTextArea();	//市区町村CD
		
		//スクロール用設定
		JScrollPane Scpn_EntryPost = B00110FrameParts.JScrollPaneSet(				 10,60,100,500,TB_EntryPost);			//郵便番号
		JScrollPane Scpn_EntryPref = B00110FrameParts.JScrollPaneSet(				120,60,100,500,TB_EntryPref);			//県
		JScrollPane Scpn_EntryMunic01 = B00110FrameParts.JScrollPaneSet(			230,60,200,500,TB_EntryMunic01);		//市区町村
		JScrollPane Scpn_EntryMunic02 = B00110FrameParts.JScrollPaneSet(			440,60,200,500,TB_EntryMunic02);		//町丁目
		JScrollPane Scpn_EntryMunicipalityCd = B00110FrameParts.JScrollPaneSet(	650,60,100,500,TB_EntryMunicipalityCd);	//市区町村CD

		main_fm.add(LB_EntryPost);
		main_fm.add(LB_EntryPref);
		main_fm.add(LB_EntryMunic01);
		main_fm.add(LB_EntryMunic02);
		main_fm.add(LB_EntryMunicipalityCd);

		main_fm.add(Scpn_EntryPost);			//郵便番号
		main_fm.add(Scpn_EntryPref);			//県
		main_fm.add(Scpn_EntryMunic01);			//市区町村
		main_fm.add(Scpn_EntryMunic02);			//町丁目
		main_fm.add(Scpn_EntryMunicipalityCd);	//市区町村CD
		
		main_fm.setVisible(true);
		
		//郵便DLデータ取込ボタン
		JButton DlEntryBtn = B00110FrameParts.BtnSet(10,580,100,20,"DL（Ken_All）",9);
		main_fm.add(DlEntryBtn);
		
		main_fm.setVisible(true);
		
		//郵便DLデータ取込ボタン
		DlEntryBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				TB_EntryPost.setText("");
				TB_EntryPref.setText("");
				TB_EntryMunic01.setText("");
				TB_EntryMunic02.setText("");
				TB_EntryMunicipalityCd.setText("");
				
				String[] file_type = {".csv",".CSV"};
				String file_type_name="テキストファイル";
				String selected = B00090FileSelect.FileSelect("ファイル選択",file_type,file_type_name);
				
				if(null!=selected && !"".equals(selected)) {
					String[][] CSVRead = B00010ToolsTextRead.CSVRead(selected,null);
					
					if(0<CSVRead.length && 10<=CSVRead[0].length) {
						String[] GetEntryPost = new String[CSVRead.length];				//郵便番号
						String[] GetEntryPref = new String[CSVRead.length];				//県
						String[] GetEntryMunic01 = new String[CSVRead.length];			//市区町村
						String[] GetEntryMunic02 = new String[CSVRead.length];			//町丁目
						String[] GetEntryMunicipalityCd = new String[CSVRead.length];	//市区町村CD
						
						//件数が多いと遅いので
						for(int i=0;i<CSVRead.length;i++) {
							
							GetEntryPost[i]				 = B00020ToolsTextControl.Trim(""+CSVRead[i][2]);
							GetEntryPref[i]				 = B00020ToolsTextControl.Trim(""+CSVRead[i][6]);
							GetEntryMunic01[i]			 = B00020ToolsTextControl.Trim(""+CSVRead[i][7]);
							GetEntryMunic02[i]			 = B00020ToolsTextControl.Trim(""+CSVRead[i][8]);
							GetEntryMunicipalityCd[i]	 = B00020ToolsTextControl.Trim(""+CSVRead[i][0]);
							
						}
						SetX=main_fm.getX();
						SetY=main_fm.getY();

						main_fm.setVisible(false);
						main_fm.dispose();
						
						PostSet(0,0,GetEntryPost,GetEntryPref,GetEntryMunic01,GetEntryMunic02,GetEntryMunicipalityCd);
					}
					
				}
				
			}
		});
		
		//登録ボタン押下事の挙動
		entry_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				String[] GetEntryPost 			= TB_EntryPost.getText().split("\n");			//郵便番号
				String[] GetEntryPref 			= TB_EntryPref.getText().split("\n");			//県
				String[] GetEntryMunic01 		= TB_EntryMunic01.getText().split("\n");		//市区町村
				String[] GetEntryMunic02 		= TB_EntryMunic02.getText().split("\n");		//町丁目
				String[] GetEntryMunicipalityCd = TB_EntryMunicipalityCd.getText().split("\n");	//市区町村CD
				
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				
				PostSet(0,0,GetEntryPost,GetEntryPref,GetEntryMunic01,GetEntryMunic02,GetEntryMunicipalityCd);

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
	
	private static void PostSet(int x,int y,String[] GetEntryPost,String[] GetEntryPref,String[] GetEntryMunic01,String[] GetEntryMunic02,String[] GetEntryMunicipalityCd) {
		A00000Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		
		boolean NonErr = true;
		int TgtLength = GetEntryPost.length;
		if(TgtLength!=GetEntryPref.length) {NonErr=false;}					//県
		if(TgtLength!=GetEntryMunic01.length) {NonErr=false;}				//市区町村
		if(TgtLength!=GetEntryMunic02.length) {NonErr=false;}				//町丁目
		if(TgtLength!=GetEntryMunicipalityCd.length) {NonErr=false;}		//市区町村CD
		if(NonErr) {
			for(int i=0;i<GetEntryPost.length;i++) {
				GetEntryPost[i] = B00020ToolsTextControl.num_only_String(GetEntryPost[i]);
				if("".equals(GetEntryPost[i])) {
					NonErr = false;
				}
			}
			if(NonErr) {
			}else {
				JOptionPane.showMessageDialog(null, "不正な郵便番号を登録しようとしています。やり直し");
			}
		}else {
			JOptionPane.showMessageDialog(null, "各項目の登録件数が一致しません。やり直し");
		}
		
		if(NonErr) {
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
			
			for(int i=0;i<GetEntryPost.length;i++) {
				Object[] SetOb = {GetEntryPost[i],GetEntryPref[i],GetEntryMunic01[i],GetEntryMunic02[i],GetEntryMunicipalityCd[i]};
				tableModel_ms01.addRow(SetOb);
			}
			
			main_fm.setVisible(true);
			
			//登録ボタン押下時の挙動
			entry_btn.addActionListener(new AbstractAction(){
				public void actionPerformed(ActionEvent e){
					String tgt_table = "M0010_PostMst";
					String[][] field_name = new String[5][3];
					String[][] entry_data = new String[GetEntryPost.length][5];
					String[] judg_field = new String[1];
					String[][] judg_data = new String[GetEntryPost.length][1];
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
					
					for(int i=0;i<GetEntryPost.length;i++) {
						judg_data[i][0] = B00020ToolsTextControl.Trim(GetEntryPost[i]);	//郵便番号
						
						entry_data[i][0] = B00020ToolsTextControl.Trim(GetEntryPost[i]);			//郵便番号
						entry_data[i][1] = B00020ToolsTextControl.Trim(GetEntryPref[i]);			//県
						entry_data[i][2] = B00020ToolsTextControl.Trim(GetEntryMunic01[i]);			//市区町村
						entry_data[i][3] = B00020ToolsTextControl.Trim(GetEntryMunic02[i]);			//町丁目
						entry_data[i][4] = B00020ToolsTextControl.Trim(GetEntryMunicipalityCd[i]);	//市区町村CD
					}
					A00020InsertUdateSQL.RUN_SQLS_EU(tgt_table, field_name, entry_data, judg_field, judg_data, non_msg_fg,TgtDB);
					
					
					
					SetX=main_fm.getX();
					SetY=main_fm.getY();

					main_fm.setVisible(false);
					main_fm.dispose();
					WM10012PostMstCreateSum.PostMstCreateSum(0, 0);
				}
			});
			
			//EXITボタン押下時の挙動
			exit_btn.addActionListener(new AbstractAction(){
				public void actionPerformed(ActionEvent e){
					SetX=main_fm.getX();
					SetY=main_fm.getY();

					main_fm.setVisible(false);
					main_fm.dispose();
					WM10012PostMstCreateSum.PostMstCreateSum(0, 0);
				}
			});
		}else {
			WM10012PostMstCreateSum.PostMstCreateSum(0, 0);
		}
		

	}
}