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

public class WM100_ItemComversionMst_02_ExcelEntry{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	public static void ItemComversionMstExcelEntry(int x,int y,String TgtFilePath) {
		A00000_Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		final JFrame main_fm = B100_FrameParts.FrameCreate(x,y,600,200,"Corgi00商品変換マスタ登録（エクセル）","");
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
				ItemComversionMstExcelEntryMain(0,0,TgtFilePath,SheetName);
			}
		});
		
		//EXITボタン押下時の挙動
		exit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				WM100_ItemComversionMst_00_Search.ItemComversionMstSearch(0, 0);
			}
		});
	}
	public static void ItemComversionMstExcelEntryMain(int x,int y,String TgtFilePath,String SheetName) {
		A00000_Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		final JFrame main_fm = B100_FrameParts.FrameCreate(x,y,550,800,"Corgi00商品変換マスタ登録（エクセル）","");
		JLabel userinfo = B100_FrameParts.UserInfo();
		JButton exit_btn = B100_FrameParts.ExitBtn();
		JButton entry_btn = B100_FrameParts.EntryBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		main_fm.add(entry_btn);
		
		Object[][] NeedCol = {
					 {"荷主グループコード"	,1,0}
					,{"荷主コード"			,1,1}
					,{"荷主商品コード"		,1,2}
					,{"変換先商品コード"	,1,3}
					,{"荷姿タイプ"			,0,4}
					};	//フィールド名,フィールドタイプ(0:数値 1:文字列 2:日付時刻),基本のカラム(ゼロスタート),基本のカラム位置※カラム位置は後で読み込んだエクセルの1行目でフィールド名比較して更新されます
		
		JLabel LB_SheetList	= B100_FrameParts.JLabelSet(	10, 40,540,20,"以下のデータを登録しようとしています※データ内の重複はチェックしません",11,0);
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
		
		JScrollPane scpn01 = B100_FrameParts.JScrollPaneSet(10,65,500,600,tb01);
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
			String Msg = "ヘッダ行で取込ファイルのレイアウト判別ができませんでした。\n確認しやがれください\n";
			for(int i=0;i<NeedCol.length;i++) {
				if(0<i&&0==i%5) {
					Msg = Msg + NeedCol[i][0] + ",\n";
				}else {
					Msg = Msg + NeedCol[i][0] + ",";
				}
			}
			Msg = Msg+"\nがヘッダに必要です";
			
			JOptionPane.showMessageDialog(null, Msg);
			ItemComversionMstExcelEntry(0,0,TgtFilePath);
			
			SetX=main_fm.getX();
			SetY=main_fm.getY();

			main_fm.setVisible(false);
			main_fm.dispose();
			
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
				WM100_ItemComversionMst_00_Search.ItemComversionMstSearch(0, 0);
			}else {
				main_fm.setVisible(true);
			}
		}
		RenewFg = true;
		
		//登録ボタン押下時の挙動
		entry_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					int RowCount = tableModel_ms01.getRowCount();
					String[] TableCol = B100_TableControl.TableFieldNameRt(tb01);
					Object[][] CheckOb = new Object[RowCount][TableCol.length];
					
					for(int i=0;i<RowCount;i++) {
						for(int i01=0;i01<TableCol.length;i01++) {
							CheckOb[i][i01] = tableModel_ms01.getValueAt(i, i01);
						}
					}
					ArrayList<String> ErrMsg = ErrCheck(CheckOb,TableCol);
					
					if(null!=ErrMsg && 0<ErrMsg.size() && 0<CheckOb.length) {
						ErrView(ErrMsg);
					}else {
						MstEntry(CheckOb,TableCol);
						//ファイルバックアップ
						B100_FolderCheck.FileBackUpNormal(TgtFilePath) ;
						
						SetX=main_fm.getX();
						SetY=main_fm.getY();
	
						main_fm.setVisible(false);
						main_fm.dispose();
						WM100_ItemComversionMst_00_Search.ItemComversionMstSearch(0, 0);
					}
					RenewFg = true;
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
				WM100_ItemComversionMst_00_Search.ItemComversionMstSearch(0, 0);
			}
		});
	}
	
	private static void MstEntry(Object[][] CheckOb,String[] TableCol) {
		int ColClGpCd 	= 1;	//荷主グループコード
		int ColClCd 	= 2;	//荷主コード
		int ColClItemCd	= 3;	//荷主商品コード
		int ColItemCd 	= 4;	//変換先商品コード
		int ColType 	= 5;	//荷姿タイプ
		
		for(int i=0;i<TableCol.length;i++) {
			switch(TableCol[i]) {
				case "荷主グループコード":
					ColClGpCd = i;
					break;
				case "荷主コード":
					ColClCd = i;
					break;
				case "荷主商品コード":
					ColClItemCd = i;
					break;
				case "変換先商品コード":
					ColItemCd = i;
					break;
				case "荷姿タイプ":
					ColType = i;
					break;
				default:
					break;
			}
		}
		int EntryCount = 0;
		for(int i=0;i<CheckOb.length;i++) {
			if("".equals(""+CheckOb[i][ColClGpCd])
					&& "".equals(""+CheckOb[i][ColClCd])
					&& "".equals(""+CheckOb[i][ColClItemCd])
					&& "".equals(""+CheckOb[i][ColItemCd])
						) {
			}else {
				EntryCount = EntryCount+1;
			}
		}
		
		String[][] ItemComversionMstSetString = {
				 {"ClGpCd"		,"1","1"}	//荷主グループコード
				,{"ClCd"		,"1","1"}	//荷主コード
				,{"ClItemCd"	,"1","1"}	//荷主商品コード
				,{"ItemCd"		,"1","1"}	//変換先商品コード
				,{"PackingType"	,"1","1"}	//荷姿タイプ
				};
		
		String now_dtm = B100_DateTimeControl.dtmString2(B100_DateTimeControl.dtm()[1])[1];
		
		String tgt_table = "KM0062_ItemComversionMst";
		String[][] field_name = ItemComversionMstSetString;
		String[][] entry_data = new String[EntryCount][ItemComversionMstSetString.length];
		String[] judg_field = new String[3];
		String[][] judg_data = new String[EntryCount][3];
		String TgtDB = "NYANKO";
		int non_msg_fg = 1;

		judg_field[0] = "ClGpCd";		//荷主グループコード
		judg_field[1] = "ClCd";			//荷主コード
		judg_field[2] = "ClItemCd";		//荷主商品コード
		
		EntryCount = 0;
		for(int i=0;i<CheckOb.length;i++) {
			if("".equals(""+CheckOb[i][ColClGpCd])
					&& "".equals(""+CheckOb[i][ColClCd])
					&& "".equals(""+CheckOb[i][ColClItemCd])
					&& "".equals(""+CheckOb[i][ColItemCd])
						) {
			}else {
				String GetClGpCd 	= ""+CheckOb[i][ColClGpCd];		//荷主グループコード
				String GetClCd 		= ""+CheckOb[i][ColClCd];		//荷主コード
				String GetClItemCd	= ""+CheckOb[i][ColClItemCd];	//荷主商品コード
				String GetItemCd 	= ""+CheckOb[i][ColItemCd];		//変換先商品コード
				String GetType 		= ""+CheckOb[i][ColType];		//荷姿タイプ
				
				GetClGpCd 	= B100_TextControl.Trim(GetClGpCd);	//荷主グループコード
				GetClCd 	= B100_TextControl.Trim(GetClCd);		//荷主コード
				GetClItemCd	= B100_TextControl.Trim(GetClItemCd);	//荷主商品コード
				GetItemCd 	= B100_TextControl.Trim(GetItemCd);	//変換先商品コード
				GetType 	= B100_TextControl.Trim(GetType);		//荷姿タイプ
				
				GetType = B100_TextControl.num_only_String02(GetType);
				if("".equals(GetType)) {GetType = "0";}
				
				float WFT = Float.parseFloat(GetType);
				GetType = ""+(int)WFT;
				
				
				judg_data[EntryCount][0] = GetClGpCd;		//荷主グループコード
				judg_data[EntryCount][1] = GetClCd;			//荷主コード
				judg_data[EntryCount][2] = GetClItemCd;		//荷主商品コード
				
				entry_data[EntryCount][0] = GetClGpCd;		//荷主グループコード
				entry_data[EntryCount][1] = GetClCd;		//荷主コード
				entry_data[EntryCount][2] = GetClItemCd;	//荷主商品コード
				entry_data[EntryCount][3] = GetItemCd;		//商品コード
				entry_data[EntryCount][4] = GetType;		//荷姿
				
				EntryCount = EntryCount+1;
			}
		}
		
		A100_InsertUpdateSQL.RUN_SQLS_EU(tgt_table, field_name, entry_data, judg_field, judg_data, non_msg_fg,TgtDB);
	}
	
	private static ArrayList<String> ErrCheck(Object[][] CheckOb,String[] TableCol){
		ArrayList<String> ErrMsg = new ArrayList<String>();
		boolean UnHitFg = true;
		
		int ColClGpCd 	= 1;	//荷主グループコード
		int ColClCd 	= 2;	//荷主コード
		int ColClItemCd	= 3;	//荷主商品コード
		int ColItemCd 	= 4;	//変換先商品コード
		int ColType 	= 5;	//荷姿タイプ
		
		for(int i=0;i<TableCol.length;i++) {
			switch(TableCol[i]) {
				case "荷主グループコード":
					ColClGpCd = i;
					break;
				case "荷主コード":
					ColClCd = i;
					break;
				case "荷主商品コード":
					ColClItemCd = i;
					break;
				case "変換先商品コード":
					ColItemCd = i;
					break;
				case "荷姿タイプ":
					ColType = i;
					break;
				default:
					break;
			}
		}
		
		ArrayList<String> SearchClGpCD 		= new ArrayList<String>();
		ArrayList<String> SearchCLGpName 	= new ArrayList<String>();
		ArrayList<String> SearchPost 		= new ArrayList<String>();
		ArrayList<String> SearchAdd 		= new ArrayList<String>();
		ArrayList<String> SearchTel 		= new ArrayList<String>();
		ArrayList<String> SearchFax 		= new ArrayList<String>();
		ArrayList<String> SearchMail 		= new ArrayList<String>();
		ArrayList<String> SearchCom 		= new ArrayList<String>();
		boolean AllSearch = false;
		
		for(int i=0;i<CheckOb.length;i++) {
			if(!"".equals(""+CheckOb[i][ColClGpCd])) {
				SearchClGpCD.add(""+CheckOb[i][ColClGpCd]);
			}
		}
		
		Object[][] ClGpMstRt = M100_ClGpMstRt.ClGpMstRt(
				SearchClGpCD,
				SearchCLGpName,
				SearchPost,
				SearchAdd,
				SearchTel,
				SearchFax,
				SearchMail,
				SearchCom,
				AllSearch);
		
		SearchClGpCD = new ArrayList<String>();
		ArrayList<String> SearchCLCD = new ArrayList<String>();
		ArrayList<String> SearchCLName = new ArrayList<String>();
		SearchPost = new ArrayList<String>();
		ArrayList<String> searchAdd = new ArrayList<String>();
		SearchTel = new ArrayList<String>();
		SearchFax = new ArrayList<String>();
		SearchMail = new ArrayList<String>();
		SearchCom = new ArrayList<String>();
		ArrayList<String> SearchWHCD = new ArrayList<String>();
		AllSearch = false;
		
		for(int i=0;i<CheckOb.length;i++) {
			if(!"".equals(""+CheckOb[i][ColClCd])) {
				SearchCLCD.add(""+CheckOb[i][ColClCd]);
			}
		}
		
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
		AllSearch = false;
		
		for(int i=0;i<CheckOb.length;i++) {
			if(!"".equals(""+CheckOb[i][ColClGpCd])) {
				SearchClGpCd.add(""+CheckOb[i][ColClGpCd]);
			}
			if(!"".equals(""+CheckOb[i][ColItemCd])) {
				SearchItemCd.add(""+CheckOb[i][ColItemCd]);
			}
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
		
		for(int i=0;i<CheckOb.length;i++) {
			if("".equals(""+CheckOb[i][ColClGpCd])
				&& "".equals(""+CheckOb[i][ColClCd])
				&& "".equals(""+CheckOb[i][ColClItemCd])
				&& "".equals(""+CheckOb[i][ColItemCd])
					) {
			
			}else {
				UnHitFg = true;
				CheckOb[i][ColType] = B100_TextControl.num_only_String02(""+CheckOb[i][ColType]);
				if("".equals(""+CheckOb[i][ColType])) {CheckOb[i][ColType]="0";}
				float WFT = Float.parseFloat(""+CheckOb[i][ColType]);
				CheckOb[i][ColType] = ""+(int)WFT;
				
				for(int i01=0;i01<B100_DefaultVariable.UnitTypeList[1].length;i01++) {
					if(B100_DefaultVariable.UnitTypeList[1][i01].equals(CheckOb[i][ColType])) {
						UnHitFg = false;
					}
				}
				if(UnHitFg) {
					int wint = i+1;
					ErrMsg.add(wint+"行目エラー("+CheckOb[i][ColType]+")は荷姿設定として不正です");
				}
				
				UnHitFg = true;
				for(int i01=0;i01<ClGpMstRt.length;i01++) {
					if((""+CheckOb[i][1]).equals(""+ClGpMstRt[i01][M100_ClGpMstRt.ColClGpCD])) {
						UnHitFg = false;
						i01=ClGpMstRt.length+1;
					}
				}
				if(UnHitFg) {
					int wint = i+1;
					ErrMsg.add(wint+"行目エラー("+CheckOb[i][ColClGpCd]+")は荷主グループに登録されていないコードです");
				}
				
				
				UnHitFg = true;
				for(int i01=0;i01<ClMstRt.length;i01++) {
					if((""+CheckOb[i][ColClGpCd]).equals(""+ClMstRt[i01][M100_ClMstRt.ColClGpCD])&&(""+CheckOb[i][ColClCd]).equals(""+ClMstRt[i01][M100_ClMstRt.Colcl_cd])) {
						UnHitFg = false;
						i01=ClMstRt.length+1;
					}
				}
				if(UnHitFg) {
					int wint = i+1;
					ErrMsg.add(wint+"行目エラー("+CheckOb[i][ColClCd]+")は荷主に登録されていないコードです");
				}
				
				UnHitFg = true;
				for(int i01=0;i01<ItemMstRt.length;i01++) {
					if((""+CheckOb[i][ColClGpCd]).equals(""+ItemMstRt[i01][M100_ItemMstRt.ColClGpCd])&&(""+CheckOb[i][ColItemCd]).equals(""+ItemMstRt[i01][M100_ItemMstRt.ColItemCd])) {
						UnHitFg = false;
						switch(""+CheckOb[i][ColType]) {
							case "0":
								break;
							case "1":
								if(0>=(int)ItemMstRt[i01][M100_ItemMstRt.ColCtQty]) {
									int wint = i+1;
									ErrMsg.add(wint+"行目エラー("+ItemMstRt[i01][M100_ItemMstRt.ColItemCd]+")"+ItemMstRt[i01][M100_ItemMstRt.ColItemName01]+"にはカートン設定されていません");
								}
								break;
							case "2":
								if(0>=(int)ItemMstRt[i01][M100_ItemMstRt.ColCsQty]) {
									int wint = i+1;
									ErrMsg.add(wint+"行目エラー("+ItemMstRt[i01][M100_ItemMstRt.ColItemCd]+")"+ItemMstRt[i01][M100_ItemMstRt.ColItemName01]+"にはケース設定されていません");
								}
								break;
							case "3":
								if(0>=(int)ItemMstRt[i01][M100_ItemMstRt.ColPlQty]) {
									int wint = i+1;
									ErrMsg.add(wint+"行目エラー("+ItemMstRt[i01][M100_ItemMstRt.ColItemCd]+")"+ItemMstRt[i01][M100_ItemMstRt.ColItemName01]+"にはパレット設定されていません");
								}
								break;
							default:
								break;
						}
						i01=ItemMstRt.length+1;
					}
				}
				if(UnHitFg) {
					int wint = i+1;
					ErrMsg.add(wint+"行目エラー("+CheckOb[i][ColItemCd]+")は商品マスタに登録されていないコードです");
				}
			}
		}
		return ErrMsg;
	}
	
	private static void ErrView(ArrayList<String>ErrMsg) {
		//必要フォルダを生成する
		String FLD_PATH = A00000_Main.MainFLD+"\\MstControl";
		B100_FolderCheck.FLD_CHECK(FLD_PATH);
		FLD_PATH = A00000_Main.MainFLD+"\\MstControl\\ItemComversionMst";
		B100_FolderCheck.FLD_CHECK(FLD_PATH);
		FLD_PATH = A00000_Main.MainFLD+"\\MstControl\\ItemComversionMst\\Err";
		B100_FolderCheck.FLD_CHECK(FLD_PATH);
		FLD_PATH = A00000_Main.MainFLD+"\\MstControl\\ItemComversionMst\\BK";
		B100_FolderCheck.FLD_CHECK(FLD_PATH);
		
		//ファイルに出力
		String NowDTM=B100_DateTimeControl.dtmString2(B100_DateTimeControl.dtm()[1])[1].replace(" ", "").replace("/", "").replace(":", "");
		
		FLD_PATH = A00000_Main.MainFLD+"\\MstControl\\ItemComversionMst\\Err";
		
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