import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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

public class WM100_ParameterMstNyanko_02_ExcelEntry{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	public static void ParameterMstNyankoExcelEntry(int x,int y,String TgtFilePath) {
		A00000_Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;

		final JFrame main_fm = B100_FrameParts.FrameCreate(x,y,600,200,"Corgi00共通パラメータ登録（エクセル）","");
		JLabel userinfo = B100_FrameParts.UserInfo();
		JButton exit_btn = B100_FrameParts.ExitBtn();
		JButton entry_btn = B100_FrameParts.EntryBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		main_fm.add(entry_btn);
		
		final String[] SheetList = B100_ExcellControl.ExcellSheetList(TgtFilePath);
		
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
				ParameterMstNyankoExcelEntryMain(0,0,TgtFilePath,SheetName);
			}
		});
		
		//EXITボタン押下時の挙動
		exit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				WM100_ParameterMstNyanko_00_Seach.ParameterMstNyankoSeach(0, 0);
			}
		});
	}
	
	public static void ParameterMstNyankoExcelEntryMain(int x,int y,String TgtFilePath,String SheetName) {
		A00000_Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		final JFrame main_fm = B100_FrameParts.FrameCreate(x,y,750,800,"Corgi00ロケーションマスタ登録（エクセル）","");
		JLabel userinfo = B100_FrameParts.UserInfo();
		JButton exit_btn = B100_FrameParts.ExitBtn();
		JButton entry_btn = B100_FrameParts.EntryBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		main_fm.add(entry_btn);
		
		Object[][] NeedCol = {
						 {"パラメータコード"			,1	, 0}
						,{"ナンバリング"				,0	, 1}
						,{"パラメータ名"				,1	, 2}
						,{"パラメータテキスト項目01"	,1	, 3}
						,{"パラメータテキスト項目02"	,1	, 4}
						,{"パラメータテキスト項目03"	,1	, 5}
						,{"パラメータテキスト項目04"	,1	, 6}
						,{"パラメータテキスト項目05"	,1	, 7}
						,{"パラメータテキスト項目06"	,1	, 8}
						,{"パラメータテキスト項目07"	,1	, 9}
						,{"パラメータテキスト項目08"	,1	,10}
						,{"パラメータテキスト項目09"	,1	,11}
						,{"パラメータテキスト項目10"	,1	,12}
						,{"パラメータ数値項目01"		,0	,13}
						,{"パラメータ数値項目02"		,0	,14}
						,{"パラメータ数値項目03"		,0	,15}
						,{"パラメータ数値項目04"		,0	,16}
						,{"パラメータ数値項目05"		,0	,17}
						,{"パラメータ数値項目06"		,0	,18}
						,{"パラメータ数値項目07"		,0	,19}
						,{"パラメータ数値項目08"		,0	,21}
						,{"パラメータ数値項目09"		,0	,22}
						,{"パラメータ数値項目10"		,0	,23}
						};	//フィールド名,フィールドタイプ(0:数値 1:文字列 2:日付時刻),基本のカラム(ゼロスタート),基本のカラム位置※カラム位置は後で読み込んだエクセルの1行目でフィールド名比較して更新されます

		
		JLabel LB_SheetList	= B100_FrameParts.JLabelSet(	10, 40,540,20,"以下のデータを登録しようとしています",11,0);
		main_fm.add(LB_SheetList);
		
		String[] columnNames01 = new String[NeedCol.length+1];
		columnNames01[0] = "Fg";
		for(int i=0;i<NeedCol.length;i++) {columnNames01[i+1] = (String)NeedCol[i][0];}
		
		//編集可能カラムの指定
		B100_TableControl.RenewTgt = new int[1];
		B100_TableControl.RenewTgt[0] = 0;

		final DefaultTableModel tableModel_ms01 = new B100_TableControl.MyTableModel01(columnNames01,0);
		
		final JTable tb01 = new JTable(tableModel_ms01);
		tb01.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tb01.setRowHeight(20*A00000_Main.Mul/A00000_Main.Div);
		tb01.setFont(new Font(A00000_Main.DefaultFont, Font.PLAIN, 12*A00000_Main.Mul/A00000_Main.Div));
		
		DefaultTableColumnModel columnModel01
		= (DefaultTableColumnModel)tb01.getColumnModel();
		
		//列幅初期設定 表示位置設定
		TableColumn column = null;
		column = columnModel01.getColumn( 0);	column.setPreferredWidth( 30*A00000_Main.Mul/A00000_Main.Div);
		for(int i=1;i<NeedCol.length;i++) {
			if(0==(int)NeedCol[i][1]) {
				column = columnModel01.getColumn(i+1);	column.setPreferredWidth(100*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.rightCellRenderer());	
			}else {
				column = columnModel01.getColumn(i+1);	column.setPreferredWidth(100*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.leftCellRenderer());	
			}
		}
		JScrollPane scpn01 = B100_FrameParts.JScrollPaneSet(10,65,700,600,tb01);
		main_fm.add(scpn01);
		
		//ヘッダ行取得⇒フィールド名判定
		//必要フィールドなければシート選択に戻る
		Object[][] HeaderRead = B100_ExcellControl.ExcellRead2(TgtFilePath,SheetName,1,0);
		boolean ErrFg = false;
		
		if(null==HeaderRead||0==HeaderRead.length) {
			ErrFg = true;
		}else {
			for(int i01=0;i01<NeedCol.length;i01++) {
				boolean UnHitFg = true;
				
				for(int i02=0;i02<HeaderRead[0].length;i02++) {
					if(((String)NeedCol[i01][0]).equals(""+HeaderRead[0][i02])) {
						UnHitFg = false;
						NeedCol[i01][2] = i02;
						
						i02=1+HeaderRead[0].length;
					}
				}
				if(UnHitFg) {
					ErrFg = true;
				}
			}
		}
		
		if(ErrFg) {
			SetX=main_fm.getX();
			SetY=main_fm.getY();

			main_fm.setVisible(false);
			main_fm.dispose();
			
			String Msg = "ヘッダ行で取込ファイルのレイアウト判別ができませんでした。\n確認しやがれください\n";
			for(int i=0;i<NeedCol.length;i++) {
				if(0<i&&0==i%5) {
					Msg = Msg + (String)NeedCol[i][0] + ",\n";
				}else {
					Msg = Msg + (String)NeedCol[i][0] + ",";
				}
			}
			Msg = Msg+"\nがヘッダに必要です";
			
			JOptionPane.showMessageDialog(null, Msg);
			ParameterMstNyankoExcelEntry(0,0,TgtFilePath);
		}else {
			int[] ClmnType = new int[HeaderRead[0].length];
			for(int i=0;i<ClmnType.length;i++) {ClmnType[i]=1;}
			
			for(int i01=0;i01<NeedCol.length;i01++) {
				ClmnType[(int)NeedCol[i01][2]]=(int)NeedCol[i01][1];
			}
			Object[][] ExcellRead = B100_ExcellControl.ExcellRead(TgtFilePath,SheetName,ClmnType,true);
			Object[][] CheckOb = new Object[ExcellRead.length][NeedCol.length+1];
			
			if(0<ExcellRead.length&&ClmnType.length<=ExcellRead[0].length) {
				for(int i=0;i<ExcellRead.length;i++) {
					Object[] SetOb = new Object[NeedCol.length+1];
					CheckOb[i][0] = false;
					SetOb[ 0] = false;
					
					for(int i01=0;i01<NeedCol.length;i01++) {
						SetOb[i01+1] = ExcellRead[i][(int)NeedCol[i01][2]];
						CheckOb[i][i01+1]=ExcellRead[i][(int)NeedCol[i01][2]];
					}
					tableModel_ms01.addRow(SetOb);
				}
			}
			String[] TableCol = B100_TableControl.TableFieldNameRt(tb01);
			ArrayList<String> ErrMsg = ErrCheck(CheckOb,TableCol);
			
			if(null!=ErrMsg && 0<ErrMsg.size()) {
				ErrView(ErrMsg);
				
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				WM100_ParameterMstNyanko_00_Seach.ParameterMstNyankoSeach(0, 0);
			}else {
				main_fm.setVisible(true);
			}
		}
		
		RenewFg = true;
		
		//登録ボタン押下時の挙動
		entry_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				String[] TableCol = B100_TableControl.TableFieldNameRt(tb01);
				int RowCount = tableModel_ms01.getRowCount();
				Object[][] CheckOb = new Object[RowCount][TableCol.length];
				for(int i=0;i<RowCount;i++) {
					for(int i01=0;i01<TableCol.length;i01++) {
						CheckOb[i][i01] = ""+tableModel_ms01.getValueAt(i, i01);
					}
				}
				ArrayList<String> ErrMsg = ErrCheck(CheckOb,TableCol);
				
				if(null!=ErrMsg && 0<ErrMsg.size() && 0<RowCount) {
					ErrView(ErrMsg);
				}else {
					MstEntry(CheckOb,TableCol);
					//ファイルバックアップ
					B100_FolderCheck.FileBackUpNormal(TgtFilePath) ;
					
					SetX=main_fm.getX();
					SetY=main_fm.getY();

					main_fm.setVisible(false);
					main_fm.dispose();
					WM100_ParameterMstNyanko_00_Seach.ParameterMstNyankoSeach(0, 0);
				}
			}
		});
		
		//EXITボタン押下時の挙動
		exit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				WM100_ParameterMstNyanko_00_Seach.ParameterMstNyankoSeach(0, 0);
			}
		});
	}
	
	private static void MstEntry(Object[][] CheckOb,String[] TableCol) {
		int ColParaCd		= (int) 0;	//パラメータコード
		int ColParaCdSeq	= (int) 1;	//ナンバリング
		int ColParaName		= (int) 2;	//パラメータ名
		int ColParaTxt01	= (int) 3;	//パラメータテキスト項目01
		int ColParaTxt02	= (int) 4;	//パラメータテキスト項目02
		int ColParaTxt03	= (int) 5;	//パラメータテキスト項目03
		int ColParaTxt04	= (int) 6;	//パラメータテキスト項目04
		int ColParaTxt05	= (int) 7;	//パラメータテキスト項目05
		int ColParaTxt06	= (int) 8;	//パラメータテキスト項目06
		int ColParaTxt07	= (int) 9;	//パラメータテキスト項目07
		int ColParaTxt08	= (int)10;	//パラメータテキスト項目08
		int ColParaTxt09	= (int)11;	//パラメータテキスト項目09
		int ColParaTxt10	= (int)12;	//パラメータテキスト項目10
		int ColParaInt01	= (int)13;	//パラメータ数値項目01
		int ColParaInt02	= (int)14;	//パラメータ数値項目02
		int ColParaInt03	= (int)15;	//パラメータ数値項目03
		int ColParaInt04	= (int)16;	//パラメータ数値項目04
		int ColParaInt05	= (int)17;	//パラメータ数値項目05
		int ColParaInt06	= (int)18;	//パラメータ数値項目06
		int ColParaInt07	= (int)19;	//パラメータ数値項目07
		int ColParaInt08	= (int)20;	//パラメータ数値項目08
		int ColParaInt09	= (int)21;	//パラメータ数値項目09
		int ColParaInt10	= (int)22;	//パラメータ数値項目10
		
		for(int i=0;i<TableCol.length;i++) {
			switch(""+TableCol[i]) {
				case "パラメータコード":
					ColParaCd = i;
					break;
				case "ナンバリング":
					ColParaCdSeq = i;
					break;
				case "パラメータ名":
					ColParaName = i;
					break;
				case "パラメータテキスト項目01":
					ColParaTxt01 = i;
					break;
				case "パラメータテキスト項目02":
					ColParaTxt02 = i;
					break;
				case "パラメータテキスト項目03":
					ColParaTxt03 = i;
					break;
				case "パラメータテキスト項目04":
					ColParaTxt04 = i;
					break;
				case "パラメータテキスト項目05":
					ColParaTxt05 = i;
					break;
				case "パラメータテキスト項目06":
					ColParaTxt06 = i;
					break;
				case "パラメータテキスト項目07":
					ColParaTxt07 = i;
					break;
				case "パラメータテキスト項目08":
					ColParaTxt08 = i;
					break;
				case "パラメータテキスト項目09":
					ColParaTxt09 = i;
					break;
				case "パラメータテキスト項目10":
					ColParaTxt10 = i;
					break;
				case "パラメータ数値項目01":
					ColParaInt01 = i;
					break;
				case "パラメータ数値項目02":
					ColParaInt02 = i;
					break;
				case "パラメータ数値項目03":
					ColParaInt03 = i;
					break;
				case "パラメータ数値項目04":
					ColParaInt04 = i;
					break;
				case "パラメータ数値項目05":
					ColParaInt05 = i;
					break;
				case "パラメータ数値項目06":
					ColParaInt06 = i;
					break;
				case "パラメータ数値項目07":
					ColParaInt07 = i;
					break;
				case "パラメータ数値項目08":
					ColParaInt08 = i;
					break;
				case "パラメータ数値項目09":
					ColParaInt09 = i;
					break;
				case "パラメータ数値項目10":
					ColParaInt10 = i;
					break;
			}
		}
		int EntryCount = 0;
		for(int i=0;i<CheckOb.length;i++) {
			String CheckParaCd	= ""+CheckOb[i][ColParaCd];			//パラメータコード
			String CheckParaCdSeq	= ""+CheckOb[i][ColParaCdSeq];	//ナンバリング
			String CheckParaName	= ""+CheckOb[i][ColParaName];	//パラメータ名
			
			CheckParaCd		= B100_TextControl.Trim(CheckParaCd);		//パラメータコード
			CheckParaCdSeq	= B100_TextControl.Trim(CheckParaCdSeq);	//ナンバリング
			CheckParaName		= B100_TextControl.Trim(CheckParaName);		//パラメータ名
			
			CheckParaCdSeq	= B100_TextControl.num_only_String02(CheckParaCdSeq);	//ナンバリング
			
			if("".equals(CheckParaCd)
					&&"".equals(CheckParaCdSeq)
					&&"".equals(CheckParaName)
					) {
			}else{
				EntryCount = EntryCount+1;
			}
		}
		
		String[] GetParaCd	= new String[EntryCount];		//パラメータコード
		String[] GetParaCdSeq	= new String[EntryCount];	//ナンバリング
		String[] GetParaName	= new String[EntryCount];	//パラメータ名
		String[] GetParaTxt01	= new String[EntryCount];	//パラメータテキスト項目01
		String[] GetParaTxt02	= new String[EntryCount];	//パラメータテキスト項目02
		String[] GetParaTxt03	= new String[EntryCount];	//パラメータテキスト項目03
		String[] GetParaTxt04	= new String[EntryCount];	//パラメータテキスト項目04
		String[] GetParaTxt05	= new String[EntryCount];	//パラメータテキスト項目05
		String[] GetParaTxt06	= new String[EntryCount];	//パラメータテキスト項目06
		String[] GetParaTxt07	= new String[EntryCount];	//パラメータテキスト項目07
		String[] GetParaTxt08	= new String[EntryCount];	//パラメータテキスト項目08
		String[] GetParaTxt09	= new String[EntryCount];	//パラメータテキスト項目09
		String[] GetParaTxt10	= new String[EntryCount];	//パラメータテキスト項目10
		String[] GetParaInt01	= new String[EntryCount];	//パラメータ数値項目01
		String[] GetParaInt02	= new String[EntryCount];	//パラメータ数値項目02
		String[] GetParaInt03	= new String[EntryCount];	//パラメータ数値項目03
		String[] GetParaInt04	= new String[EntryCount];	//パラメータ数値項目04
		String[] GetParaInt05	= new String[EntryCount];	//パラメータ数値項目05
		String[] GetParaInt06	= new String[EntryCount];	//パラメータ数値項目06
		String[] GetParaInt07	= new String[EntryCount];	//パラメータ数値項目07
		String[] GetParaInt08	= new String[EntryCount];	//パラメータ数値項目08
		String[] GetParaInt09	= new String[EntryCount];	//パラメータ数値項目09
		String[] GetParaInt10	= new String[EntryCount];	//パラメータ数値項目10
		String[] GetEntryDate	= new String[EntryCount];	//登録日
		String[] GetUpdateDate	= new String[EntryCount];	//更新日
		String[] GetEntryUser	= new String[EntryCount];	//登録者
		String[] GetUpdateUser	= new String[EntryCount];	//更新者
		
		EntryCount = 0;
		String now_dtm = B100_DateTimeControl.dtmString2(B100_DateTimeControl.dtm()[1])[1];
		for(int i=0;i<CheckOb.length;i++) {
			String CheckParaCd	= ""+CheckOb[i][ColParaCd];			//パラメータコード
			String CheckParaCdSeq	= ""+CheckOb[i][ColParaCdSeq];	//ナンバリング
			String CheckParaName	= ""+CheckOb[i][ColParaName];	//パラメータ名
			
			CheckParaCd		= B100_TextControl.Trim(CheckParaCd);		//パラメータコード
			CheckParaCdSeq	= B100_TextControl.Trim(CheckParaCdSeq);	//ナンバリング
			CheckParaName	= B100_TextControl.Trim(CheckParaName);		//パラメータ名
			
			CheckParaCdSeq	= B100_TextControl.num_only_String02(CheckParaCdSeq);	//ナンバリング
			
			if("".equals(CheckParaCd)
					&&"".equals(CheckParaCdSeq)
					&&"".equals(CheckParaName)
					) {
			}else{
				GetParaCd[EntryCount]		= ""+CheckOb[i][ColParaCd];		//パラメータコード
				GetParaCdSeq[EntryCount]	= ""+CheckOb[i][ColParaCdSeq];	//ナンバリング
				GetParaName[EntryCount]		= ""+CheckOb[i][ColParaName];	//パラメータ名
				GetParaTxt01[EntryCount]	= ""+CheckOb[i][ColParaTxt01];	//パラメータテキスト項目01
				GetParaTxt02[EntryCount]	= ""+CheckOb[i][ColParaTxt02];	//パラメータテキスト項目02
				GetParaTxt03[EntryCount]	= ""+CheckOb[i][ColParaTxt03];	//パラメータテキスト項目03
				GetParaTxt04[EntryCount]	= ""+CheckOb[i][ColParaTxt04];	//パラメータテキスト項目04
				GetParaTxt05[EntryCount]	= ""+CheckOb[i][ColParaTxt05];	//パラメータテキスト項目05
				GetParaTxt06[EntryCount]	= ""+CheckOb[i][ColParaTxt06];	//パラメータテキスト項目06
				GetParaTxt07[EntryCount]	= ""+CheckOb[i][ColParaTxt07];	//パラメータテキスト項目07
				GetParaTxt08[EntryCount]	= ""+CheckOb[i][ColParaTxt08];	//パラメータテキスト項目08
				GetParaTxt09[EntryCount]	= ""+CheckOb[i][ColParaTxt09];	//パラメータテキスト項目09
				GetParaTxt10[EntryCount]	= ""+CheckOb[i][ColParaTxt10];	//パラメータテキスト項目10
				GetParaInt01[EntryCount]	= ""+CheckOb[i][ColParaInt01];	//パラメータ数値項目01
				GetParaInt02[EntryCount]	= ""+CheckOb[i][ColParaInt02];	//パラメータ数値項目02
				GetParaInt03[EntryCount]	= ""+CheckOb[i][ColParaInt03];	//パラメータ数値項目03
				GetParaInt04[EntryCount]	= ""+CheckOb[i][ColParaInt04];	//パラメータ数値項目04
				GetParaInt05[EntryCount]	= ""+CheckOb[i][ColParaInt05];	//パラメータ数値項目05
				GetParaInt06[EntryCount]	= ""+CheckOb[i][ColParaInt06];	//パラメータ数値項目06
				GetParaInt07[EntryCount]	= ""+CheckOb[i][ColParaInt07];	//パラメータ数値項目07
				GetParaInt08[EntryCount]	= ""+CheckOb[i][ColParaInt08];	//パラメータ数値項目08
				GetParaInt09[EntryCount]	= ""+CheckOb[i][ColParaInt09];	//パラメータ数値項目09
				GetParaInt10[EntryCount]	= ""+CheckOb[i][ColParaInt10];	//パラメータ数値項目10
				GetEntryDate[EntryCount]	= now_dtm;	//登録日
				GetUpdateDate[EntryCount]	= now_dtm;	//更新日
				GetEntryUser[EntryCount]	= "(" + A00000_Main.LoginUserId + ")" + A00000_Main.LoginUserName;	//登録者
				GetUpdateUser[EntryCount]	= "(" + A00000_Main.LoginUserId + ")" + A00000_Main.LoginUserName;	//更新者
				
				GetParaCd[EntryCount]		= B100_TextControl.Trim(GetParaCd[EntryCount]);		//パラメータコード
				GetParaCdSeq[EntryCount]	= B100_TextControl.Trim(GetParaCdSeq[EntryCount]);	//ナンバリング
				GetParaName[EntryCount]		= B100_TextControl.Trim(GetParaName[EntryCount]);		//パラメータ名
				GetParaTxt01[EntryCount]	= B100_TextControl.Trim(GetParaTxt01[EntryCount]);	//パラメータテキスト項目01
				GetParaTxt02[EntryCount]	= B100_TextControl.Trim(GetParaTxt02[EntryCount]);	//パラメータテキスト項目02
				GetParaTxt03[EntryCount]	= B100_TextControl.Trim(GetParaTxt03[EntryCount]);	//パラメータテキスト項目03
				GetParaTxt04[EntryCount]	= B100_TextControl.Trim(GetParaTxt04[EntryCount]);	//パラメータテキスト項目04
				GetParaTxt05[EntryCount]	= B100_TextControl.Trim(GetParaTxt05[EntryCount]);	//パラメータテキスト項目05
				GetParaTxt06[EntryCount]	= B100_TextControl.Trim(GetParaTxt06[EntryCount]);	//パラメータテキスト項目06
				GetParaTxt07[EntryCount]	= B100_TextControl.Trim(GetParaTxt07[EntryCount]);	//パラメータテキスト項目07
				GetParaTxt08[EntryCount]	= B100_TextControl.Trim(GetParaTxt08[EntryCount]);	//パラメータテキスト項目08
				GetParaTxt09[EntryCount]	= B100_TextControl.Trim(GetParaTxt09[EntryCount]);	//パラメータテキスト項目09
				GetParaTxt10[EntryCount]	= B100_TextControl.Trim(GetParaTxt10[EntryCount]);	//パラメータテキスト項目10
				GetParaInt01[EntryCount]	= B100_TextControl.Trim(GetParaInt01[EntryCount]);	//パラメータ数値項目01
				GetParaInt02[EntryCount]	= B100_TextControl.Trim(GetParaInt02[EntryCount]);	//パラメータ数値項目02
				GetParaInt03[EntryCount]	= B100_TextControl.Trim(GetParaInt03[EntryCount]);	//パラメータ数値項目03
				GetParaInt04[EntryCount]	= B100_TextControl.Trim(GetParaInt04[EntryCount]);	//パラメータ数値項目04
				GetParaInt05[EntryCount]	= B100_TextControl.Trim(GetParaInt05[EntryCount]);	//パラメータ数値項目05
				GetParaInt06[EntryCount]	= B100_TextControl.Trim(GetParaInt06[EntryCount]);	//パラメータ数値項目06
				GetParaInt07[EntryCount]	= B100_TextControl.Trim(GetParaInt07[EntryCount]);	//パラメータ数値項目07
				GetParaInt08[EntryCount]	= B100_TextControl.Trim(GetParaInt08[EntryCount]);	//パラメータ数値項目08
				GetParaInt09[EntryCount]	= B100_TextControl.Trim(GetParaInt09[EntryCount]);	//パラメータ数値項目09
				GetParaInt10[EntryCount]	= B100_TextControl.Trim(GetParaInt10[EntryCount]);	//パラメータ数値項目10
				
				GetParaCdSeq[EntryCount]	= B100_TextControl.num_only_String02(GetParaCdSeq[EntryCount]);	//ナンバリング
				GetParaInt01[EntryCount]	= B100_TextControl.num_only_String02(GetParaInt01[EntryCount]);	//パラメータ数値項目01
				GetParaInt02[EntryCount]	= B100_TextControl.num_only_String02(GetParaInt02[EntryCount]);	//パラメータ数値項目02
				GetParaInt03[EntryCount]	= B100_TextControl.num_only_String02(GetParaInt03[EntryCount]);	//パラメータ数値項目03
				GetParaInt04[EntryCount]	= B100_TextControl.num_only_String02(GetParaInt04[EntryCount]);	//パラメータ数値項目04
				GetParaInt05[EntryCount]	= B100_TextControl.num_only_String02(GetParaInt05[EntryCount]);	//パラメータ数値項目05
				GetParaInt06[EntryCount]	= B100_TextControl.num_only_String02(GetParaInt06[EntryCount]);	//パラメータ数値項目06
				GetParaInt07[EntryCount]	= B100_TextControl.num_only_String02(GetParaInt07[EntryCount]);	//パラメータ数値項目07
				GetParaInt08[EntryCount]	= B100_TextControl.num_only_String02(GetParaInt08[EntryCount]);	//パラメータ数値項目08
				GetParaInt09[EntryCount]	= B100_TextControl.num_only_String02(GetParaInt09[EntryCount]);	//パラメータ数値項目09
				GetParaInt10[EntryCount]	= B100_TextControl.num_only_String02(GetParaInt10[EntryCount]);	//パラメータ数値項目10
				
				if("".equals(GetParaCdSeq[EntryCount])){GetParaCdSeq[EntryCount]	= "0";}	//ナンバリング
				if("".equals(GetParaInt01[EntryCount])){GetParaInt01[EntryCount]	= "0";}	//パラメータ数値項目01
				if("".equals(GetParaInt02[EntryCount])){GetParaInt02[EntryCount]	= "0";}	//パラメータ数値項目02
				if("".equals(GetParaInt03[EntryCount])){GetParaInt03[EntryCount]	= "0";}	//パラメータ数値項目03
				if("".equals(GetParaInt04[EntryCount])){GetParaInt04[EntryCount]	= "0";}	//パラメータ数値項目04
				if("".equals(GetParaInt05[EntryCount])){GetParaInt05[EntryCount]	= "0";}	//パラメータ数値項目05
				if("".equals(GetParaInt06[EntryCount])){GetParaInt06[EntryCount]	= "0";}	//パラメータ数値項目06
				if("".equals(GetParaInt07[EntryCount])){GetParaInt07[EntryCount]	= "0";}	//パラメータ数値項目07
				if("".equals(GetParaInt08[EntryCount])){GetParaInt08[EntryCount]	= "0";}	//パラメータ数値項目08
				if("".equals(GetParaInt09[EntryCount])){GetParaInt09[EntryCount]	= "0";}	//パラメータ数値項目09
				if("".equals(GetParaInt10[EntryCount])){GetParaInt10[EntryCount]	= "0";}	//パラメータ数値項目10
				
				float WFT = (float)0;
				WFT = Float.parseFloat(GetParaCdSeq[EntryCount]);	GetParaCdSeq[EntryCount]	= ""+(int)WFT;	//ナンバリング
				WFT = Float.parseFloat(GetParaInt01[EntryCount]);	GetParaInt01[EntryCount]	= ""+(int)WFT;	//パラメータ数値項目01
				WFT = Float.parseFloat(GetParaInt02[EntryCount]);	GetParaInt02[EntryCount]	= ""+(int)WFT;	//パラメータ数値項目02
				WFT = Float.parseFloat(GetParaInt03[EntryCount]);	GetParaInt03[EntryCount]	= ""+(int)WFT;	//パラメータ数値項目03
				WFT = Float.parseFloat(GetParaInt04[EntryCount]);	GetParaInt04[EntryCount]	= ""+(int)WFT;	//パラメータ数値項目04
				WFT = Float.parseFloat(GetParaInt05[EntryCount]);	GetParaInt05[EntryCount]	= ""+(int)WFT;	//パラメータ数値項目05
				WFT = Float.parseFloat(GetParaInt06[EntryCount]);	GetParaInt06[EntryCount]	= ""+(int)WFT;	//パラメータ数値項目06
				WFT = Float.parseFloat(GetParaInt07[EntryCount]);	GetParaInt07[EntryCount]	= ""+(int)WFT;	//パラメータ数値項目07
				WFT = Float.parseFloat(GetParaInt08[EntryCount]);	GetParaInt08[EntryCount]	= ""+(int)WFT;	//パラメータ数値項目08
				WFT = Float.parseFloat(GetParaInt09[EntryCount]);	GetParaInt09[EntryCount]	= ""+(int)WFT;	//パラメータ数値項目09
				WFT = Float.parseFloat(GetParaInt10[EntryCount]);	GetParaInt10[EntryCount]	= ""+(int)WFT;	//パラメータ数値項目10
				
				EntryCount = EntryCount+1;
			}
		}
		Object[][] SetString = {
				 {"ParaCd"		,"1","1",GetParaCd		,"Key"}	//パラメータコード
				,{"ParaCdSeq"	,"1","1",GetParaCdSeq	,"Key"}	//ナンバリング
				,{"ParaName"	,"1","1",GetParaName	,""}	//パラメータ名
				,{"ParaTxt01"	,"1","1",GetParaTxt01	,""}	//パラメータテキスト項目01
				,{"ParaTxt02"	,"1","1",GetParaTxt02	,""}	//パラメータテキスト項目02
				,{"ParaTxt03"	,"1","1",GetParaTxt03	,""}	//パラメータテキスト項目03
				,{"ParaTxt04"	,"1","1",GetParaTxt04	,""}	//パラメータテキスト項目04
				,{"ParaTxt05"	,"1","1",GetParaTxt05	,""}	//パラメータテキスト項目05
				,{"ParaTxt06"	,"1","1",GetParaTxt06	,""}	//パラメータテキスト項目06
				,{"ParaTxt07"	,"1","1",GetParaTxt07	,""}	//パラメータテキスト項目07
				,{"ParaTxt08"	,"1","1",GetParaTxt08	,""}	//パラメータテキスト項目08
				,{"ParaTxt09"	,"1","1",GetParaTxt09	,""}	//パラメータテキスト項目09
				,{"ParaTxt10"	,"1","1",GetParaTxt10	,""}	//パラメータテキスト項目10
				,{"ParaInt01"	,"1","1",GetParaInt01	,""}	//パラメータ数値項目01
				,{"ParaInt02"	,"1","1",GetParaInt02	,""}	//パラメータ数値項目02
				,{"ParaInt03"	,"1","1",GetParaInt03	,""}	//パラメータ数値項目03
				,{"ParaInt04"	,"1","1",GetParaInt04	,""}	//パラメータ数値項目04
				,{"ParaInt05"	,"1","1",GetParaInt05	,""}	//パラメータ数値項目05
				,{"ParaInt06"	,"1","1",GetParaInt06	,""}	//パラメータ数値項目06
				,{"ParaInt07"	,"1","1",GetParaInt07	,""}	//パラメータ数値項目07
				,{"ParaInt08"	,"1","1",GetParaInt08	,""}	//パラメータ数値項目08
				,{"ParaInt09"	,"1","1",GetParaInt09	,""}	//パラメータ数値項目09
				,{"ParaInt10"	,"1","1",GetParaInt10	,""}	//パラメータ数値項目10
				,{"EntryDate"	,"1","0",GetEntryDate	,""}	//登録日
				,{"UpdateDate"	,"1","1",GetUpdateDate	,""}	//更新日
				,{"EntryUser"	,"1","0",GetEntryUser	,""}	//登録者
				,{"UpdateUser"	,"1","1",GetUpdateUser	,""}	//更新者
		};
		
		int KeyCount = 0;
		for(int i=0;i<SetString.length;i++) {
			if("Key".equals((String)SetString[i][4])) {KeyCount=KeyCount+1;}
		}
		
		String tgt_table = "KM0000_PARAMETER";
		String[][] field_name = new String[SetString.length][3];
		String[][] entry_data = new String[EntryCount][SetString.length];
		String[] judg_field = new String[KeyCount];
		String[][] judg_data = new String[EntryCount][KeyCount];
		String TgtDB = "NYANKO";
		int non_msg_fg = 1;
		
		KeyCount = 0;
		for(int i=0;i<SetString.length;i++) {
			field_name[i][0] = (String)SetString[i][0];
			field_name[i][1] = (String)SetString[i][1];
			field_name[i][2] = (String)SetString[i][2];
			
			for(int i01=0;i01<((String[])SetString[i][3]).length;i01++) {
				entry_data[i01][i] = ((String[])SetString[i][3])[i01];
			}
			
			if("Key".equals((String)SetString[i][4])) {
				judg_field[KeyCount] = (String)SetString[i][0];
				for(int i01=0;i01<((String[])SetString[i][3]).length;i01++) {
					judg_data[i01][KeyCount] = ((String[])SetString[i][3])[i01];
				}
				KeyCount=KeyCount+1;
			}
		}
		if(0<EntryCount) {
			A100_InsertUpdateSQL.RUN_SQLS_EU(tgt_table, field_name, entry_data, judg_field, judg_data, non_msg_fg,TgtDB);
		}
	}
	
	private static ArrayList<String> ErrCheck(Object[][] CheckOb,String[] TableCol){
		ArrayList<String> ErrMsg = new ArrayList<String>();
		int ColParaCd		= (int) 0;	//パラメータコード
		int ColParaCdSeq	= (int) 1;	//ナンバリング
		int ColParaName		= (int) 2;	//パラメータ名
		int ColParaTxt01	= (int) 3;	//パラメータテキスト項目01
		int ColParaTxt02	= (int) 4;	//パラメータテキスト項目02
		int ColParaTxt03	= (int) 5;	//パラメータテキスト項目03
		int ColParaTxt04	= (int) 6;	//パラメータテキスト項目04
		int ColParaTxt05	= (int) 7;	//パラメータテキスト項目05
		int ColParaTxt06	= (int) 8;	//パラメータテキスト項目06
		int ColParaTxt07	= (int) 9;	//パラメータテキスト項目07
		int ColParaTxt08	= (int)10;	//パラメータテキスト項目08
		int ColParaTxt09	= (int)11;	//パラメータテキスト項目09
		int ColParaTxt10	= (int)12;	//パラメータテキスト項目10
		int ColParaInt01	= (int)13;	//パラメータ数値項目01
		int ColParaInt02	= (int)14;	//パラメータ数値項目02
		int ColParaInt03	= (int)15;	//パラメータ数値項目03
		int ColParaInt04	= (int)16;	//パラメータ数値項目04
		int ColParaInt05	= (int)17;	//パラメータ数値項目05
		int ColParaInt06	= (int)18;	//パラメータ数値項目06
		int ColParaInt07	= (int)19;	//パラメータ数値項目07
		int ColParaInt08	= (int)20;	//パラメータ数値項目08
		int ColParaInt09	= (int)21;	//パラメータ数値項目09
		int ColParaInt10	= (int)22;	//パラメータ数値項目10
		
		for(int i=0;i<TableCol.length;i++) {
			switch(""+TableCol[i]) {
				case "パラメータコード":
					ColParaCd = i;
					break;
				case "ナンバリング":
					ColParaCdSeq = i;
					break;
				case "パラメータ名":
					ColParaName = i;
					break;
				case "パラメータテキスト項目01":
					ColParaTxt01 = i;
					break;
				case "パラメータテキスト項目02":
					ColParaTxt02 = i;
					break;
				case "パラメータテキスト項目03":
					ColParaTxt03 = i;
					break;
				case "パラメータテキスト項目04":
					ColParaTxt04 = i;
					break;
				case "パラメータテキスト項目05":
					ColParaTxt05 = i;
					break;
				case "パラメータテキスト項目06":
					ColParaTxt06 = i;
					break;
				case "パラメータテキスト項目07":
					ColParaTxt07 = i;
					break;
				case "パラメータテキスト項目08":
					ColParaTxt08 = i;
					break;
				case "パラメータテキスト項目09":
					ColParaTxt09 = i;
					break;
				case "パラメータテキスト項目10":
					ColParaTxt10 = i;
					break;
				case "パラメータ数値項目01":
					ColParaInt01 = i;
					break;
				case "パラメータ数値項目02":
					ColParaInt02 = i;
					break;
				case "パラメータ数値項目03":
					ColParaInt03 = i;
					break;
				case "パラメータ数値項目04":
					ColParaInt04 = i;
					break;
				case "パラメータ数値項目05":
					ColParaInt05 = i;
					break;
				case "パラメータ数値項目06":
					ColParaInt06 = i;
					break;
				case "パラメータ数値項目07":
					ColParaInt07 = i;
					break;
				case "パラメータ数値項目08":
					ColParaInt08 = i;
					break;
				case "パラメータ数値項目09":
					ColParaInt09 = i;
					break;
				case "パラメータ数値項目10":
					ColParaInt10 = i;
					break;
			}
		}
		
		for(int i=0;i<CheckOb.length;i++) {
			String GetParaCd	= ""+CheckOb[i][ColParaCd];		//パラメータコード
			String GetParaCdSeq	= ""+CheckOb[i][ColParaCdSeq];	//ナンバリング
			String GetParaName	= ""+CheckOb[i][ColParaName];	//パラメータ名
			String GetParaTxt01	= ""+CheckOb[i][ColParaTxt01];	//パラメータテキスト項目01
			String GetParaTxt02	= ""+CheckOb[i][ColParaTxt02];	//パラメータテキスト項目02
			String GetParaTxt03	= ""+CheckOb[i][ColParaTxt03];	//パラメータテキスト項目03
			String GetParaTxt04	= ""+CheckOb[i][ColParaTxt04];	//パラメータテキスト項目04
			String GetParaTxt05	= ""+CheckOb[i][ColParaTxt05];	//パラメータテキスト項目05
			String GetParaTxt06	= ""+CheckOb[i][ColParaTxt06];	//パラメータテキスト項目06
			String GetParaTxt07	= ""+CheckOb[i][ColParaTxt07];	//パラメータテキスト項目07
			String GetParaTxt08	= ""+CheckOb[i][ColParaTxt08];	//パラメータテキスト項目08
			String GetParaTxt09	= ""+CheckOb[i][ColParaTxt09];	//パラメータテキスト項目09
			String GetParaTxt10	= ""+CheckOb[i][ColParaTxt10];	//パラメータテキスト項目10
			String GetParaInt01	= ""+CheckOb[i][ColParaInt01];	//パラメータ数値項目01
			String GetParaInt02	= ""+CheckOb[i][ColParaInt02];	//パラメータ数値項目02
			String GetParaInt03	= ""+CheckOb[i][ColParaInt03];	//パラメータ数値項目03
			String GetParaInt04	= ""+CheckOb[i][ColParaInt04];	//パラメータ数値項目04
			String GetParaInt05	= ""+CheckOb[i][ColParaInt05];	//パラメータ数値項目05
			String GetParaInt06	= ""+CheckOb[i][ColParaInt06];	//パラメータ数値項目06
			String GetParaInt07	= ""+CheckOb[i][ColParaInt07];	//パラメータ数値項目07
			String GetParaInt08	= ""+CheckOb[i][ColParaInt08];	//パラメータ数値項目08
			String GetParaInt09	= ""+CheckOb[i][ColParaInt09];	//パラメータ数値項目09
			String GetParaInt10	= ""+CheckOb[i][ColParaInt10];	//パラメータ数値項目10
			
			GetParaCd		= B100_TextControl.Trim(GetParaCd);		//パラメータコード
			GetParaCdSeq	= B100_TextControl.Trim(GetParaCdSeq);	//ナンバリング
			GetParaName		= B100_TextControl.Trim(GetParaName);		//パラメータ名
			GetParaTxt01	= B100_TextControl.Trim(GetParaTxt01);	//パラメータテキスト項目01
			GetParaTxt02	= B100_TextControl.Trim(GetParaTxt02);	//パラメータテキスト項目02
			GetParaTxt03	= B100_TextControl.Trim(GetParaTxt03);	//パラメータテキスト項目03
			GetParaTxt04	= B100_TextControl.Trim(GetParaTxt04);	//パラメータテキスト項目04
			GetParaTxt05	= B100_TextControl.Trim(GetParaTxt05);	//パラメータテキスト項目05
			GetParaTxt06	= B100_TextControl.Trim(GetParaTxt06);	//パラメータテキスト項目06
			GetParaTxt07	= B100_TextControl.Trim(GetParaTxt07);	//パラメータテキスト項目07
			GetParaTxt08	= B100_TextControl.Trim(GetParaTxt08);	//パラメータテキスト項目08
			GetParaTxt09	= B100_TextControl.Trim(GetParaTxt09);	//パラメータテキスト項目09
			GetParaTxt10	= B100_TextControl.Trim(GetParaTxt10);	//パラメータテキスト項目10
			GetParaInt01	= B100_TextControl.Trim(GetParaInt01);	//パラメータ数値項目01
			GetParaInt02	= B100_TextControl.Trim(GetParaInt02);	//パラメータ数値項目02
			GetParaInt03	= B100_TextControl.Trim(GetParaInt03);	//パラメータ数値項目03
			GetParaInt04	= B100_TextControl.Trim(GetParaInt04);	//パラメータ数値項目04
			GetParaInt05	= B100_TextControl.Trim(GetParaInt05);	//パラメータ数値項目05
			GetParaInt06	= B100_TextControl.Trim(GetParaInt06);	//パラメータ数値項目06
			GetParaInt07	= B100_TextControl.Trim(GetParaInt07);	//パラメータ数値項目07
			GetParaInt08	= B100_TextControl.Trim(GetParaInt08);	//パラメータ数値項目08
			GetParaInt09	= B100_TextControl.Trim(GetParaInt09);	//パラメータ数値項目09
			GetParaInt10	= B100_TextControl.Trim(GetParaInt10);	//パラメータ数値項目10
			
			GetParaCdSeq	= B100_TextControl.num_only_String02(GetParaCdSeq);	//ナンバリング
			GetParaInt01	= B100_TextControl.num_only_String02(GetParaInt01);	//パラメータ数値項目01
			GetParaInt02	= B100_TextControl.num_only_String02(GetParaInt02);	//パラメータ数値項目02
			GetParaInt03	= B100_TextControl.num_only_String02(GetParaInt03);	//パラメータ数値項目03
			GetParaInt04	= B100_TextControl.num_only_String02(GetParaInt04);	//パラメータ数値項目04
			GetParaInt05	= B100_TextControl.num_only_String02(GetParaInt05);	//パラメータ数値項目05
			GetParaInt06	= B100_TextControl.num_only_String02(GetParaInt06);	//パラメータ数値項目06
			GetParaInt07	= B100_TextControl.num_only_String02(GetParaInt07);	//パラメータ数値項目07
			GetParaInt08	= B100_TextControl.num_only_String02(GetParaInt08);	//パラメータ数値項目08
			GetParaInt09	= B100_TextControl.num_only_String02(GetParaInt09);	//パラメータ数値項目09
			GetParaInt10	= B100_TextControl.num_only_String02(GetParaInt10);	//パラメータ数値項目10
			
			if("".equals(GetParaCd)
					&&"".equals(GetParaCdSeq)
					&&"".equals(GetParaName)
					) {
			}else{
				if("".equals(GetParaCd)) {
					int wint = i+1;
					ErrMsg.add(wint+"行目エラー：パラメータコードは必須です");
				}
				if("".equals(GetParaCdSeq)) {
					int wint = i+1;
					ErrMsg.add(wint+"行目エラー：ナンバリングは必須です");
				}
				if("".equals(GetParaName)) {
					int wint = i+1;
					ErrMsg.add(wint+"行目エラー：パラメータ名は必須です");
				}
			}
		}
		
		return ErrMsg;
	}
	
	private static void ErrView(ArrayList<String>ErrMsg) {
		//必要フォルダを生成する
		String FLD_PATH = A00000_Main.MainFLD+"\\MstControl";
		B100_FolderCheck.FLD_CHECK(FLD_PATH);
		FLD_PATH = A00000_Main.MainFLD+"\\MstControl\\ParameterMstNyanko";
		B100_FolderCheck.FLD_CHECK(FLD_PATH);
		FLD_PATH = A00000_Main.MainFLD+"\\MstControl\\ParameterMstNyanko\\Err";
		B100_FolderCheck.FLD_CHECK(FLD_PATH);
		FLD_PATH = A00000_Main.MainFLD+"\\MstControl\\ParameterMstNyanko\\BK";
		B100_FolderCheck.FLD_CHECK(FLD_PATH);
		
		//ファイルに出力
		String NowDTM=B100_DateTimeControl.dtmString2(B100_DateTimeControl.dtm()[1])[1].replace(" ", "").replace("/", "").replace(":", "");
		
		FLD_PATH = A00000_Main.MainFLD+"\\MstControl\\ParameterMstNyanko\\Err";
		
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
	
}