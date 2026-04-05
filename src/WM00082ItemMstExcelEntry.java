import java.awt.Desktop;
import java.awt.Font;
import java.awt.Image;
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

public class WM00082ItemMstExcelEntry{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	public static void ItemMstExcelEntry(int x,int y,String TgtFilePath) {
		A00000Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;

		final JFrame main_fm = B00110FrameParts.FrameCreate(x,y,600,200,"Corgi00商品マスタ登録（エクセル）","");
		JLabel userinfo = B00110FrameParts.UserInfo();
		JButton exit_btn = B00110FrameParts.ExitBtn();
		JButton entry_btn = B00110FrameParts.EntryBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		main_fm.add(entry_btn);
		
		final String[] SheetList = B00060ToolsExcellControl.ExcellSheetList(TgtFilePath);
		
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
				ItemMstExcelEntryMain(0,0,TgtFilePath,SheetName);
			}
		});
		
		//EXITボタン押下時の挙動
		exit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				WM00080ItemMstSearch.ItemMstSearch(0, 0);
			}
		});
	}
	
	public static void ItemMstExcelEntryMain(int x,int y,String TgtFilePath,String SheetName) {
		A00000Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		final JFrame main_fm = B00110FrameParts.FrameCreate(x,y,850,800,"Corgi00商品マスタ登録（エクセル）","");
		JLabel userinfo = B00110FrameParts.UserInfo();
		JButton exit_btn = B00110FrameParts.ExitBtn();
		JButton entry_btn = B00110FrameParts.EntryBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		main_fm.add(entry_btn);
		
		Object[][] NeedCol = {
					 {"荷主グループコード"		,1, 0}
					,{"商品コード"				,1, 1}
					,{"荷主商品コード"			,1, 2}
					,{"商品名1"					,1, 3}
					,{"商品名2"					,1, 4}
					,{"商品名3"					,1, 5}
					,{"運送タイプコード01"		,1, 6}
					,{"運送タイプコード02"		,1, 7}
					,{"運送タイプコード03"		,1, 8}
					,{"運送タイプコード04"		,1, 9}
					,{"運送タイプコード05"		,1,10}
					,{"基幹システム商品コード"	,1,11}
					,{"カートン入数"			,0,12}
					,{"ケース入数"				,0,13}
					,{"パレット入数"			,0,14}
					,{"JANCD"					,1,15}
					,{"カートンバーコード"		,1,16}
					,{"ケースバーコード"		,1,17}
					,{"パレットバーコード"		,1,18}
					,{"カートン商品名称"		,1,19}
					,{"ケース商品名称"			,1,20}
					,{"パレット商品名称"		,1,21}
					,{"商品単位"				,1,22}
					,{"カートン商品単位"		,1,23}
					,{"ケース商品単位"			,1,24}
					,{"パレット商品単位"		,1,25}
					,{"商品重量"				,0,26}
					,{"カートン重量"			,0,27}
					,{"ケース重量"				,0,28}
					,{"パレット重量"			,0,29}
					,{"商品サイズ"				,0,30}
					,{"カートンサイズ"			,0,31}
					,{"ケースサイズ"			,0,32}
					,{"パレットサイズ"			,0,33}
					,{"推奨ロケ"				,1,34}
					,{"商品モデル番号（型番）"	,1,35}
					,{"商品カテゴリCD"			,1,36}
					,{"商品カテゴリ名"			,1,37}
					,{"商品カラーコード"		,1,38}
					,{"商品カラー名"			,1,39}
					,{"商品サイズコード"		,1,40}
					,{"商品サイズ名"			,1,41}
					,{"コメント1"				,1,42}
					,{"コメント2"				,1,43}
					,{"コメント3"				,1,44}
					,{"温度区分"				,1,45}
					,{"画像パス01"				,1,46}
					,{"画像パス02"				,1,47}
					,{"画像パス03"				,1,48}
					,{"画像パス04"				,1,49}
					,{"画像パス05"				,1,50}
					,{"賞味期限日数"			,1,51}
					,{"削除フラグ"				,1,52}
					};//フィールド名,フィールドタイプ(0:数値 1:文字列 2:日付時刻),基本のカラム(ゼロスタート),基本のカラム位置※カラム位置は後で読み込んだエクセルの1行目でフィールド名比較して更新されます
		JLabel LB_SheetList	= B00110FrameParts.JLabelSet(	10, 40,600,20,"以下のデータを登録しようとしています※データ内の重複はチェックしません",11,0);
		main_fm.add(LB_SheetList);
		
		String[] columnNames01 = new String[NeedCol.length+1];
		columnNames01[0] = "Fg";
		for(int i=0;i<NeedCol.length;i++) {columnNames01[i+1] = (String)NeedCol[i][0];}
		
		//編集可能カラムの指定
		B10010TableControl.RenewTgt = new int[1];
		B10010TableControl.RenewTgt[0] = 0;

		final DefaultTableModel tableModel_ms01 = new B10010TableControl.MyTableModel01(columnNames01,0);
		
		final JTable tb01 = new JTable(tableModel_ms01);
		tb01.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tb01.setRowHeight(20*A00000Main.Mul/A00000Main.Div);
		tb01.setFont(new Font(A00000Main.DefaultFont, Font.PLAIN, 12*A00000Main.Mul/A00000Main.Div));
		
		DefaultTableColumnModel columnModel01
		= (DefaultTableColumnModel)tb01.getColumnModel();
		
		//列幅初期設定 表示位置設定
		TableColumn column = null;
		
		column = columnModel01.getColumn( 0);	column.setPreferredWidth( 30*A00000Main.Mul/A00000Main.Div);
		for(int i=1;i<NeedCol.length;i++) {
			if(0==(int)NeedCol[i][1]) {
				column = columnModel01.getColumn(i+1);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.rightCellRenderer());	
			}else {
				column = columnModel01.getColumn(i+1);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	
			}
		}
		
		JScrollPane scpn01 = B00110FrameParts.JScrollPaneSet(10,65,800,600,tb01);
		main_fm.add(scpn01);
		
		//ヘッダ行取得⇒フィールド名判定
		//必要フィールドなければシート選択に戻る
		Object[][] HeaderRead = B00060ToolsExcellControl.ExcellRead2(TgtFilePath,SheetName,1,0);
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
			ItemMstExcelEntry(0,0,TgtFilePath);
		}else {
			int[] ClmnType = new int[HeaderRead[0].length];
			for(int i=0;i<ClmnType.length;i++) {ClmnType[i]=1;}

			for(int i01=0;i01<NeedCol.length;i01++) {
				ClmnType[(int)NeedCol[i01][2]]=(int)NeedCol[i01][1];
			}
			
			Object[][] ExcellRead = B00060ToolsExcellControl.ExcellRead(TgtFilePath,SheetName,ClmnType,true);
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
			String[] TableCol = B10010TableControl.TableFieldNameRt(tb01);
			ArrayList<String> ErrMsg = ErrCheck(CheckOb,TableCol);
			if(null!=ErrMsg && 0<ErrMsg.size()) {
				ErrView(ErrMsg);
				
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				WM00080ItemMstSearch.ItemMstSearch(0, 0);
			}else {
				main_fm.setVisible(true);
			}
		}
		
		RenewFg = true;
		
		//登録ボタン押下時の挙動
		entry_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				int RowCount = tableModel_ms01.getRowCount();
				int ColCount = tableModel_ms01.getColumnCount();
				
				if(0<RowCount) {
					Object[][] CheckOb = new Object[RowCount][ColCount];
					for(int i01=0;i01<RowCount;i01++) {
						for(int i02=0;i02<ColCount;i02++) {
							CheckOb[i01][i02] = tableModel_ms01.getValueAt(i01, i02);
						}
					}
					String[] TableCol = B10010TableControl.TableFieldNameRt(tb01);
					ArrayList<String> ErrMsg = ErrCheck(CheckOb,TableCol);
					if(null!=ErrMsg && 0<ErrMsg.size()) {
						ErrView(ErrMsg);
					}else {
						DataEntry(CheckOb,TableCol);
						//ファイルバックアップ
						B00040ToolsFolderCheck.FileBackUpNormal(TgtFilePath) ;
						
						SetX=main_fm.getX();
						SetY=main_fm.getY();
	
						main_fm.setVisible(false);
						main_fm.dispose();
						WM00080ItemMstSearch.ItemMstSearch(0, 0);
					}
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
				WM00080ItemMstSearch.ItemMstSearch(0, 0);
			}
		});
	}
	
	private static void DataEntry(Object[][] CheckOb,String[]TableCol) {
		int ColClGpCd			=  1;		//荷主グループコード
		int ColItemCd			=  2;		//商品コード
		int ColCLItemCd			=  3;		//荷主商品コード
		int ColItemName01		=  4;		//商品名1
		int ColItemName02		=  5;		//商品名2
		int ColItemName03		=  6;		//商品名3
		int ColDeliveryTypeCd01	=  7;		//運送タイプコード01
		int ColDeliveryTypeCd02	=  8;		//運送タイプコード02
		int ColDeliveryTypeCd03	=  9;		//運送タイプコード03
		int ColDeliveryTypeCd04	= 10;		//運送タイプコード04
		int ColDeliveryTypeCd05	= 11;		//運送タイプコード05
		int ColPTMSCD			= 12;		//基幹SYS商品コード
		int ColCtQty			= 13;		//カートン入数
		int ColCsQty			= 14;		//ケース入数
		int ColPlQty			= 15;		//パレット入数
		int ColJanCd			= 16;		//JANCD
		int ColCtJan			= 17;		//カートンバーコード
		int ColCsJan			= 18;		//ケースバーコード
		int ColPlJan			= 19;		//パレットバーコード
		int ColCtName			= 20;		//カートン商品名称
		int ColCsName			= 21;		//ケース商品名称
		int ColPlName			= 22;		//パレット商品名称
		int ColUnitName			= 23;		//商品単位
		int ColCtUnitName		= 24;		//カートン商品単位
		int ColCsUnitName		= 25;		//ケース商品単位
		int ColPlUnitName		= 26;		//パレット商品単位
		int ColItemWeight		= 27;		//商品重量
		int ColCtWeight			= 28;		//カートン重量
		int ColCsWeight			= 29;		//ケース重量
		int ColPlWeight			= 30;		//パレット重量
		int ColItemSize			= 31;		//商品サイズ
		int ColCtSize			= 32;		//カートンサイズ
		int ColCsSize			= 33;		//ケースサイズ
		int ColPlSize			= 34;		//パレットサイズ
		int ColRecomendLoc		= 35;		//推奨ロケ
		int ColItemMDNo			= 36;		//商品型番
		int ColCategoryCd		= 37;		//商品カテゴリCD
		int ColCategoryName		= 38;		//商品カテゴリ名
		int ColItemColorCd		= 39;		//商品カラーコード
		int ColItemColorName	= 40;		//商品カラー名
		int ColItemSizeCd		= 41;		//商品サイズコード
		int ColItemSizeName		= 42;		//商品サイズ名
		int ColCom01			= 43;		//コメント1
		int ColCom02			= 44;		//コメント2
		int ColCom03			= 45;		//コメント3
		int ColTildFG			= 46;		//温度区分
		int ColItemImagePath01	= 47;		//画像パス01 
		int ColItemImagePath02	= 48;		//画像パス02
		int ColItemImagePath03	= 49;		//画像パス03
		int ColItemImagePath04	= 50;		//画像パス04
		int ColItemImagePath05	= 51;		//画像パス05
		int ColExpDateHowLong	= 52;		//賞味期限日数
		int ColDelFg			= 53;		//削除フラグ
		
		for(int i=0;i<TableCol.length;i++) {
			switch(TableCol[i]) {
				case "荷主グループコード":
					ColClGpCd = i;
					break;
				case "商品コード":
					ColItemCd = i;
					break;
				case "荷主商品コード":
					ColCLItemCd = i;
					break;
				case "商品名1":
					ColItemName01 = i;
					break;
				case "商品名2":
					ColItemName02 = i;
					break;
				case "商品名3":
					ColItemName03 = i;
					break;
				case "運送タイプコード01":
					ColDeliveryTypeCd01 = i;
					break;
				case "運送タイプコード02":
					ColDeliveryTypeCd02 = i;
					break;
				case "運送タイプコード03":
					ColDeliveryTypeCd03 = i;
					break;
				case "運送タイプコード04":
					ColDeliveryTypeCd04 = i;
					break;
				case "運送タイプコード05":
					ColDeliveryTypeCd05 = i;
					break;
				case "基幹システム商品コード":
					ColPTMSCD = i;
					break;
				case "カートン入数":
					ColCtQty = i;
					break;
				case "ケース入数":
					ColCsQty = i;
					break;
				case "パレット入数":
					ColPlQty = i;
					break;
				case "JANCD":
					ColJanCd = i;
					break;
				case "カートンバーコード":
					ColCtJan = i;
					break;
				case "ケースバーコード":
					ColCsJan = i;
					break;
				case "パレットバーコード":
					ColPlJan = i;
					break;
				case "カートン商品名称":
					ColCtName = i;
					break;
				case "ケース商品名称":
					ColCsName = i;
					break;
				case "パレット商品名称":
					ColPlName = i;
					break;
				case "商品単位":
					ColUnitName = i;
					break;
				case "カートン商品単位":
					ColCtUnitName = i;
					break;
				case "ケース商品単位":
					ColCsUnitName = i;
					break;
				case "パレット商品単位":
					ColPlUnitName = i;
					break;
				case "商品重量":
					ColItemWeight = i;
					break;
				case "カートン重量":
					ColCtWeight = i;
					break;
				case "ケース重量":
					ColCsWeight = i;
					break;
				case "パレット重量":
					ColPlWeight = i;
					break;
				case "商品サイズ":
					ColItemSize = i;
					break;
				case "カートンサイズ":
					ColCtSize = i;
					break;
				case "ケースサイズ":
					ColCsSize = i;
					break;
				case "パレットサイズ":
					ColPlSize = i;
					break;
				case "推奨ロケ":
					ColRecomendLoc = i;
					break;
				case "商品モデル番号（型番）":
					ColItemMDNo = i;
					break;
				case "商品カテゴリCD":
					ColCategoryCd = i;
					break;
				case "商品カテゴリ名":
					ColCategoryName = i;
					break;
				case "商品カラーコード":
					ColItemColorCd = i;
					break;
				case "商品カラー名":
					ColItemColorName = i;
					break;
				case "商品サイズコード":
					ColItemSizeCd = i;
					break;
				case "商品サイズ名":
					ColItemSizeName = i;
					break;
				case "コメント1":
					ColCom01 = i;
					break;
				case "コメント2":
					ColCom02 = i;
					break;
				case "コメント3":
					ColCom03 = i;
					break;
				case "温度区分":
					ColTildFG = i;
					break;
				case "画像パス01":
					ColItemImagePath01 = i;
					break;
				case "画像パス02":
					ColItemImagePath02 = i;
					break;
				case "画像パス03":
					ColItemImagePath03 = i;
					break;
				case "画像パス04":
					ColItemImagePath04 = i;
					break;
				case "画像パス05":
					ColItemImagePath05 = i;
					break;
				case "賞味期限日数":
					ColExpDateHowLong = i;
					break;
				case "削除フラグ":
					ColDelFg = i;
					break;
				default:
					break;
			}
		}
		int EntryCount = 0;
		for(int i=0;i<CheckOb.length;i++) {
			//商品CD・商品名が空白ならSKIP
			if("".equals(""+CheckOb[i][ColItemCd])
					&& "".equals(""+CheckOb[i][ColItemName01])
					) {
			}else {
				EntryCount = EntryCount+1;
			}
		}
		
		String[] GetClGpCd 				= new String[EntryCount];		//荷主グループコード
		String[] GetItemCd 				= new String[EntryCount];		//商品コード
		String[] GetCLItemCd 			= new String[EntryCount];		//荷主商品コード
		String[] GetItemName01 			= new String[EntryCount];		//商品名1
		String[] GetItemName02 			= new String[EntryCount];		//商品名2
		String[] GetItemName03 			= new String[EntryCount];		//商品名3
		String[] GetDeliveryTypeCd01 	= new String[EntryCount];		//運送タイプコード01
		String[] GetDeliveryTypeCd02 	= new String[EntryCount];		//運送タイプコード02
		String[] GetDeliveryTypeCd03 	= new String[EntryCount];		//運送タイプコード03
		String[] GetDeliveryTypeCd04 	= new String[EntryCount];		//運送タイプコード04
		String[] GetDeliveryTypeCd05 	= new String[EntryCount];		//運送タイプコード05
		String[] GetPTMSCD 				= new String[EntryCount];		//基幹SYS商品コード
		String[] GetRecomendLoc 		= new String[EntryCount];		//推奨ロケ
		String[] GetItemMDNo 			= new String[EntryCount];		//商品型番
		String[] GetCategoryCd 			= new String[EntryCount];		//商品カテゴリCD
		String[] GetCategoryName 		= new String[EntryCount];		//商品カテゴリ名
		String[] GetItemColorCd 		= new String[EntryCount];		//商品カラーコード
		String[] GetItemColorName 		= new String[EntryCount];		//商品カラー名
		String[] GetItemSizeCd 			= new String[EntryCount];		//商品サイズコード
		String[] GetItemSizeName 		= new String[EntryCount];		//商品サイズ名
		String[] GetTildFG 				= new String[EntryCount];		//温度区分
		String[] GetExpDateHowLong 		= new String[EntryCount];		//賞味期限日数
		String[] GetCtQty 				= new String[EntryCount];		//カートン入数
		String[] GetCsQty 				= new String[EntryCount];		//ケース入数
		String[] GetPlQty 				= new String[EntryCount];		//パレット入数
		String[] GetItemWeight 			= new String[EntryCount];		//商品重量
		String[] GetCtWeight 			= new String[EntryCount];		//カートン重量
		String[] GetCsWeight 			= new String[EntryCount];		//ケース重量
		String[] GetPlWeight 			= new String[EntryCount];		//パレット重量
		String[] GetItemSize 			= new String[EntryCount];		//商品サイズ
		String[] GetCtSize 				= new String[EntryCount];		//カートンサイズ
		String[] GetCsSize 				= new String[EntryCount];		//ケースサイズ
		String[] GetPlSize 				= new String[EntryCount];		//パレットサイズ
		String[] GetCtName 				= new String[EntryCount];		//カートン商品名称
		String[] GetCsName 				= new String[EntryCount];		//ケース商品名称
		String[] GetPlName 				= new String[EntryCount];		//パレット商品名称
		String[] GetUnitName 			= new String[EntryCount];		//商品単位
		String[] GetCtUnitName 			= new String[EntryCount];		//カートン商品単位
		String[] GetCsUnitName 			= new String[EntryCount];		//ケース商品単位
		String[] GetPlUnitName 			= new String[EntryCount];		//パレット商品単位
		String[] GetJanCd 				= new String[EntryCount];		//JANCD
		String[] GetCtJan 				= new String[EntryCount];		//カートンバーコード
		String[] GetCsJan 				= new String[EntryCount];		//ケースバーコード
		String[] GetPlJan 				= new String[EntryCount];		//パレットバーコード
		String[] GetCom01 				= new String[EntryCount];		//コメント1
		String[] GetCom02 				= new String[EntryCount];		//コメント2
		String[] GetCom03 				= new String[EntryCount];		//コメント3
		String[] GetDelFg 				= new String[EntryCount];		//削除フラグ
		String[] GetItemImagePath01 	= new String[EntryCount];		//画像パス01 
		String[] GetItemImagePath02 	= new String[EntryCount];		//画像パス02
		String[] GetItemImagePath03 	= new String[EntryCount];		//画像パス03
		String[] GetItemImagePath04 	= new String[EntryCount];		//画像パス04
		String[] GetItemImagePath05 	= new String[EntryCount];		//画像パス05
		String[] GetTildName 			= new String[EntryCount];		//温度区分名
		
		
		EntryCount = 0;
		for(int i01=0;i01<CheckOb.length;i01++) {
			for(int i02=0;i02<CheckOb[i01].length;i02++) {
				CheckOb[i01][i02] = B00020ToolsTextControl.Trim(""+CheckOb[i01][i02]);
			}
			//商品CD・商品名が空白ならSKIP
			if("".equals(""+CheckOb[i01][ColItemCd])
					&& "".equals(""+CheckOb[i01][ColItemName01])
					) {
			}else {
				GetClGpCd[EntryCount] 				= ""+CheckOb[i01][ColClGpCd];			//荷主グループコード
				GetItemCd[EntryCount] 				= ""+CheckOb[i01][ColItemCd];			//商品コード
				GetCLItemCd[EntryCount] 			= ""+CheckOb[i01][ColCLItemCd];			//荷主商品コード
				GetItemName01[EntryCount] 			= ""+CheckOb[i01][ColItemName01];		//商品名1
				GetItemName02[EntryCount] 			= ""+CheckOb[i01][ColItemName02];		//商品名2
				GetItemName03[EntryCount] 			= ""+CheckOb[i01][ColItemName03];		//商品名3
				GetDeliveryTypeCd01[EntryCount] 	= ""+CheckOb[i01][ColDeliveryTypeCd01];	//運送タイプコード01
				GetDeliveryTypeCd02[EntryCount] 	= ""+CheckOb[i01][ColDeliveryTypeCd02];	//運送タイプコード02
				GetDeliveryTypeCd03[EntryCount] 	= ""+CheckOb[i01][ColDeliveryTypeCd03];	//運送タイプコード03
				GetDeliveryTypeCd04[EntryCount] 	= ""+CheckOb[i01][ColDeliveryTypeCd04];	//運送タイプコード04
				GetDeliveryTypeCd05[EntryCount] 	= ""+CheckOb[i01][ColDeliveryTypeCd05];	//運送タイプコード05
				GetPTMSCD[EntryCount] 				= ""+CheckOb[i01][ColPTMSCD];			//基幹SYS商品コード
				GetCtQty[EntryCount] 				= ""+CheckOb[i01][ColCtQty];			//カートン入数
				GetCsQty[EntryCount] 				= ""+CheckOb[i01][ColCsQty];			//ケース入数
				GetPlQty[EntryCount] 				= ""+CheckOb[i01][ColPlQty];			//パレット入数
				GetJanCd[EntryCount] 				= ""+CheckOb[i01][ColJanCd];			//JANCD
				GetCtJan[EntryCount] 				= ""+CheckOb[i01][ColCtJan];			//カートンバーコード
				GetCsJan[EntryCount] 				= ""+CheckOb[i01][ColCsJan];			//ケースバーコード
				GetPlJan[EntryCount] 				= ""+CheckOb[i01][ColPlJan];			//パレットバーコード
				GetCtName[EntryCount] 				= ""+CheckOb[i01][ColCtName];			//カートン商品名称
				GetCsName[EntryCount] 				= ""+CheckOb[i01][ColCsName];			//ケース商品名称
				GetPlName[EntryCount] 				= ""+CheckOb[i01][ColPlName];			//パレット商品名称
				GetUnitName[EntryCount] 			= ""+CheckOb[i01][ColUnitName];			//商品単位
				GetCtUnitName[EntryCount] 			= ""+CheckOb[i01][ColCtUnitName];		//カートン商品単位
				GetCsUnitName[EntryCount] 			= ""+CheckOb[i01][ColCsUnitName];		//ケース商品単位
				GetPlUnitName[EntryCount] 			= ""+CheckOb[i01][ColPlUnitName];		//パレット商品単位
				GetItemWeight[EntryCount] 			= ""+CheckOb[i01][ColItemWeight];		//商品重量
				GetCtWeight[EntryCount] 			= ""+CheckOb[i01][ColCtWeight];			//カートン重量
				GetCsWeight[EntryCount] 			= ""+CheckOb[i01][ColCsWeight];			//ケース重量
				GetPlWeight[EntryCount] 			= ""+CheckOb[i01][ColPlWeight];			//パレット重量
				GetItemSize[EntryCount] 			= ""+CheckOb[i01][ColItemSize];			//商品サイズ
				GetCtSize[EntryCount] 				= ""+CheckOb[i01][ColCtSize];			//カートンサイズ
				GetCsSize[EntryCount] 				= ""+CheckOb[i01][ColCsSize];			//ケースサイズ
				GetPlSize[EntryCount] 				= ""+CheckOb[i01][ColPlSize];			//パレットサイズ
				GetRecomendLoc[EntryCount] 			= ""+CheckOb[i01][ColRecomendLoc];		//推奨ロケ
				GetItemMDNo[EntryCount] 			= ""+CheckOb[i01][ColItemMDNo];			//商品型番
				GetCategoryCd[EntryCount] 			= ""+CheckOb[i01][ColCategoryCd];		//商品カテゴリCD
				GetCategoryName[EntryCount] 		= ""+CheckOb[i01][ColCategoryName];		//商品カテゴリ名
				GetItemColorCd[EntryCount] 			= ""+CheckOb[i01][ColItemColorCd];		//商品カラーコード
				GetItemColorName[EntryCount] 		= ""+CheckOb[i01][ColItemColorName];	//商品カラー名
				GetItemSizeCd[EntryCount] 			= ""+CheckOb[i01][ColItemSizeCd];		//商品サイズコード
				GetItemSizeName[EntryCount] 		= ""+CheckOb[i01][ColItemSizeName];		//商品サイズ名
				GetCom01[EntryCount] 				= ""+CheckOb[i01][ColCom01];			//コメント1
				GetCom02[EntryCount] 				= ""+CheckOb[i01][ColCom02];			//コメント2
				GetCom03[EntryCount] 				= ""+CheckOb[i01][ColCom03];			//コメント3
				GetTildFG[EntryCount] 				= ""+CheckOb[i01][ColTildFG];			//温度区分
				GetItemImagePath01[EntryCount] 		= ""+CheckOb[i01][ColItemImagePath01];	//画像パス01 
				GetItemImagePath02[EntryCount] 		= ""+CheckOb[i01][ColItemImagePath02];	//画像パス02
				GetItemImagePath03[EntryCount] 		= ""+CheckOb[i01][ColItemImagePath03];	//画像パス03
				GetItemImagePath04[EntryCount] 		= ""+CheckOb[i01][ColItemImagePath04];	//画像パス04
				GetItemImagePath05[EntryCount] 		= ""+CheckOb[i01][ColItemImagePath05];	//画像パス05
				GetExpDateHowLong[EntryCount] 		= ""+CheckOb[i01][ColExpDateHowLong];	//賞味期限日数
				GetDelFg[EntryCount] 				= ""+CheckOb[i01][ColDelFg];			//削除フラグ
				
				
				GetExpDateHowLong[EntryCount] 		= B00020ToolsTextControl.num_only_String02(GetExpDateHowLong[EntryCount]);	//賞味期限日数
				GetCtQty[EntryCount] 				= B00020ToolsTextControl.num_only_String02(GetCtQty[EntryCount]);				//カートン入数
				GetCsQty[EntryCount] 				= B00020ToolsTextControl.num_only_String02(GetCsQty[EntryCount]);				//ケース入数
				GetPlQty[EntryCount] 				= B00020ToolsTextControl.num_only_String02(GetPlQty[EntryCount]);				//パレット入数
				GetItemWeight[EntryCount] 			= B00020ToolsTextControl.num_only_String02(GetItemWeight[EntryCount]);		//商品重量
				GetCtWeight[EntryCount] 			= B00020ToolsTextControl.num_only_String02(GetCtWeight[EntryCount]);			//カートン重量
				GetCsWeight[EntryCount] 			= B00020ToolsTextControl.num_only_String02(GetCsWeight[EntryCount]);			//ケース重量
				GetPlWeight[EntryCount] 			= B00020ToolsTextControl.num_only_String02(GetPlWeight[EntryCount]);			//パレット重量
				GetItemSize[EntryCount] 			= B00020ToolsTextControl.num_only_String02(GetItemSize[EntryCount]);			//商品サイズ
				GetCtSize[EntryCount] 				= B00020ToolsTextControl.num_only_String02(GetCtSize[EntryCount]);			//カートンサイズ
				GetCsSize[EntryCount] 				= B00020ToolsTextControl.num_only_String02(GetCsSize[EntryCount]);			//ケースサイズ
				GetPlSize[EntryCount] 				= B00020ToolsTextControl.num_only_String02(GetPlSize[EntryCount]);			//パレットサイズ
				
				if("".equals(GetExpDateHowLong[EntryCount]	)){GetExpDateHowLong[EntryCount]	="0";}		//賞味期限日数
				if("".equals(GetCtQty[EntryCount]			)){GetCtQty[EntryCount]				="0";}		//カートン入数
				if("".equals(GetCsQty[EntryCount]			)){GetCsQty[EntryCount]				="0";}		//ケース入数
				if("".equals(GetPlQty[EntryCount]			)){GetPlQty[EntryCount]				="0";}		//パレット入数
				if("".equals(GetItemWeight[EntryCount]		)){GetItemWeight[EntryCount]		="0";}		//商品重量
				if("".equals(GetCtWeight[EntryCount]		)){GetCtWeight[EntryCount]			="0";}		//カートン重量
				if("".equals(GetCsWeight[EntryCount]		)){GetCsWeight[EntryCount]			="0";}		//ケース重量
				if("".equals(GetPlWeight[EntryCount]		)){GetPlWeight[EntryCount]			="0";}		//パレット重量
				if("".equals(GetItemSize[EntryCount]		)){GetItemSize[EntryCount]			="0";}		//商品サイズ
				if("".equals(GetCtSize[EntryCount]			)){GetCtSize[EntryCount]			="0";}		//カートンサイズ
				if("".equals(GetCsSize[EntryCount]			)){GetCsSize[EntryCount]			="0";}		//ケースサイズ
				if("".equals(GetPlSize[EntryCount]			)){GetPlSize[EntryCount]			="0";}		//パレットサイズ
				
				float WFT = (float)0;
				
				WFT=Float.parseFloat(GetExpDateHowLong[EntryCount]);
				GetExpDateHowLong[EntryCount] = ""+(int)WFT;		//賞味期限日数
			
				WFT=Float.parseFloat(GetCtQty[EntryCount]);
				GetCtQty[EntryCount] = ""+(int)WFT;		//カートン入数
		
				WFT=Float.parseFloat(GetCsQty[EntryCount]);
				GetCsQty[EntryCount] = ""+(int)WFT;		//ケース入数
	
				WFT=Float.parseFloat(GetPlQty[EntryCount]);
				GetPlQty[EntryCount] = ""+(int)WFT;		//パレット入数
				
				
				GetTildName[EntryCount]	= "";
				for(int i02 =0;i02<B00100DefaultVariable.TildFG[1].length;i02++) {
					if(GetTildFG[EntryCount].equals(B00100DefaultVariable.TildFG[1][i02])) {
						GetTildName[EntryCount] = B00100DefaultVariable.TildFG[2][i02];
					}
				}
				EntryCount = EntryCount+1;
			}
		}
		//現在登録済みの商品マスタ取得
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
		ArrayList<String> SearchItemSizeName = new ArrayList<String>();		//商品サイズ名
		ArrayList<String> SearchJanCd = new ArrayList<String>();			//JANCD
		ArrayList<String> SearchTildFG = new ArrayList<String>();			//温度区分
		ArrayList<String> SearchTildName = new ArrayList<String>();			//温度区分名
		ArrayList<String> SearchDelFg = new ArrayList<String>();			//削除フラグ
		boolean AllSearch = false;
		
		for(int i=0;i<EntryCount;i++) {
			SearchClGpCd.add(""+GetClGpCd[i]);
			SearchItemCd.add(""+GetItemCd[i]);
		}
		
		Object[][] ItemMstRt = M00070ItemMstRt.ItemMstRt(
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
				SearchItemSizeName,		//商品サイズ名
				SearchJanCd,			//JANCD
				SearchTildFG,			//温度区分
				SearchTildName,			//温度区分名
				SearchDelFg,			//削除フラグ
				AllSearch);
		
		//画像パスが指定されていれば画像を1024×1024にリサイズして保存
		//画像保存用のフォルダ存在チェック
		B00040ToolsFolderCheck.FLD_CHECK(A00000Main.FileFldPth);
		B00040ToolsFolderCheck.FLD_CHECK(A00000Main.FileFldPth+"\\ItemImage");
		B00040ToolsFolderCheck.FLD_CHECK(A00000Main.FileFldPth+"\\ItemImage\\"+GetClGpCd);
		
		for(int i01=0;i01<EntryCount;i01++) {
			if(!"".equals(GetItemImagePath01[i01])){		//画像パス01 
				String SavePath = A00000Main.FileFldPth+"\\ItemImage\\"+GetClGpCd[i01];
				String FullSavePath = SavePath+"\\"+GetItemCd[i01]+"_01.";
				String GetItemImagePath = GetItemImagePath01[i01];
				
				if(B00140PictControl.PictCheck(GetItemImagePath)&&B00040ToolsFolderCheck.FLD_CHECK_ONRY(SavePath)) {
					String FileType=B00040ToolsFolderCheck.FILE_TYPE(GetItemImagePath);
					Image image = B00140PictControl.PictReSize(1024,1024,GetItemImagePath);
					FullSavePath = FullSavePath+FileType;
					B00140PictControl.PictSave(image,FileType,FullSavePath);
					GetItemImagePath01[i01] = FullSavePath;
				}else {
				}
			}
			if(!"".equals(GetItemImagePath02[i01])){		//画像パス02
				String SavePath = A00000Main.FileFldPth+"\\ItemImage\\"+GetClGpCd[i01];
				String FullSavePath = SavePath+"\\"+GetItemCd[i01]+"_02.";
				String GetItemImagePath = GetItemImagePath02[i01];
				
				if(B00140PictControl.PictCheck(GetItemImagePath)&&B00040ToolsFolderCheck.FLD_CHECK_ONRY(SavePath)) {
					String FileType=B00040ToolsFolderCheck.FILE_TYPE(GetItemImagePath);
					Image image = B00140PictControl.PictReSize(1024,1024,GetItemImagePath);
					FullSavePath = FullSavePath+FileType;
					B00140PictControl.PictSave(image,FileType,FullSavePath);
					GetItemImagePath02[i01] = FullSavePath;
				}else {
				}
			}
			if(!"".equals(GetItemImagePath03[i01])){		//画像パス03
				String SavePath = A00000Main.FileFldPth+"\\ItemImage\\"+GetClGpCd[i01];
				String FullSavePath = SavePath+"\\"+GetItemCd[i01]+"_03.";
				String GetItemImagePath = GetItemImagePath03[i01];
				
				if(B00140PictControl.PictCheck(GetItemImagePath)&&B00040ToolsFolderCheck.FLD_CHECK_ONRY(SavePath)) {
					String FileType=B00040ToolsFolderCheck.FILE_TYPE(GetItemImagePath);
					Image image = B00140PictControl.PictReSize(1024,1024,GetItemImagePath);
					FullSavePath = FullSavePath+FileType;
					B00140PictControl.PictSave(image,FileType,FullSavePath);
					GetItemImagePath03[i01] = FullSavePath;
				}else {
				}
			}
			if(!"".equals(GetItemImagePath04[i01])){		//画像パス04
				String SavePath = A00000Main.FileFldPth+"\\ItemImage\\"+GetClGpCd[i01];
				String FullSavePath = SavePath+"\\"+GetItemCd[i01]+"_04.";
				String GetItemImagePath = GetItemImagePath04[i01];
				
				if(B00140PictControl.PictCheck(GetItemImagePath)&&B00040ToolsFolderCheck.FLD_CHECK_ONRY(SavePath)) {
					String FileType=B00040ToolsFolderCheck.FILE_TYPE(GetItemImagePath);
					Image image = B00140PictControl.PictReSize(1024,1024,GetItemImagePath);
					FullSavePath = FullSavePath+FileType;
					B00140PictControl.PictSave(image,FileType,FullSavePath);
					GetItemImagePath04[i01] = FullSavePath;
				}else {
				}
			}
			if(!"".equals(GetItemImagePath05[i01])){		//画像パス05
				String SavePath = A00000Main.FileFldPth+"\\ItemImage\\"+GetClGpCd[i01];
				String FullSavePath = SavePath+"\\"+GetItemCd[i01]+"_05.";
				String GetItemImagePath = GetItemImagePath05[i01];
				
				if(B00140PictControl.PictCheck(GetItemImagePath)&&B00040ToolsFolderCheck.FLD_CHECK_ONRY(SavePath)) {
					String FileType=B00040ToolsFolderCheck.FILE_TYPE(GetItemImagePath);
					Image image = B00140PictControl.PictReSize(1024,1024,GetItemImagePath);
					FullSavePath = FullSavePath+FileType;
					B00140PictControl.PictSave(image,FileType,FullSavePath);
					GetItemImagePath05[i01] = FullSavePath;
				}else {
				}
			}

			//画像パスが空白だった場合既存のマスタ情報をセットする
			if("".equals(GetItemImagePath01[i01])||"".equals(GetItemImagePath02[i01])||"".equals(GetItemImagePath03[i01])||"".equals(GetItemImagePath04[i01])||"".equals(GetItemImagePath05[i01])) {
				for(int i02=0;i02<ItemMstRt.length;i02++) {
					if(GetClGpCd[i01].equals(""+ItemMstRt[i02][M00070ItemMstRt.ColClGpCd])&&GetItemCd[i01].equals(""+ItemMstRt[i02][M00070ItemMstRt.ColItemCd])) {
						if("".equals(GetItemImagePath01[i01])) {
							GetItemImagePath01[i01] = ""+ItemMstRt[i02][M00070ItemMstRt.ColPictPass01];
						}
						if("".equals(GetItemImagePath02[i01])) {
							GetItemImagePath02[i01] = ""+ItemMstRt[i02][M00070ItemMstRt.ColPictPass02];
						}
						if("".equals(GetItemImagePath03[i01])) {
							GetItemImagePath03[i01] = ""+ItemMstRt[i02][M00070ItemMstRt.ColPictPass03];
						}
						if("".equals(GetItemImagePath04[i01])) {
							GetItemImagePath04[i01] = ""+ItemMstRt[i02][M00070ItemMstRt.ColPictPass04];
						}
						if("".equals(GetItemImagePath05[i01])) {
							GetItemImagePath05[i01] = ""+ItemMstRt[i02][M00070ItemMstRt.ColPictPass05];
						}
						i02=ItemMstRt.length+1;
					}
				}
			}
		}
		
		String now_dtm = B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[1];
		
		String[][] ItemMstSetString = {
				  {"ClGpCd"				,"1","1"}	//荷主グループコード
				 ,{"ItemCd"				,"1","1"}	//商品コード
				 ,{"CLItemCd"			,"1","1"}	//荷主商品コード
				 ,{"ItemName01"			,"1","1"}	//商品名1
				 ,{"ItemName02"			,"1","1"}	//商品名2
				 ,{"ItemName03"			,"1","1"}	//商品名3
				 ,{"ItemWeight"			,"1","1"}	//商品重量
				 ,{"ItemSize"			,"1","1"}	//商品サイズ
				 ,{"DeliveryTypeCd"		,"1","1"}	//運送タイプコード01
				 ,{"DeliveryTypeCd02"	,"1","1"}	//運送タイプコード02
				 ,{"DeliveryTypeCd03"	,"1","1"}	//運送タイプコード03
				 ,{"DeliveryTypeCd04"	,"1","1"}	//運送タイプコード04
				 ,{"DeliveryTypeCd05"	,"1","1"}	//運送タイプコード05
				 ,{"EntryDate"			,"1","0"}	//データ登録日時
				 ,{"UpdateDate"			,"1","1"}	//データ更新日時
				 ,{"EntryUser"			,"1","0"}	//登録者コード
				 ,{"UpdateUser"			,"1","1"}	//更新者コード
				 ,{"DelFg"				,"1","1"}	//削除フラグ
				 ,{"PTMSCD"				,"1","1"}	//基幹SYS商品コード
				 ,{"JanCd"				,"1","1"}	//JANCD
				 ,{"UnitName"			,"1","1"}	//商品単位
				};
		String tgt_table = "KM0060_ITEMMST";
		String[][] field_name = ItemMstSetString;
		String[][] entry_data = new String[EntryCount][ItemMstSetString.length];
		String[] judg_field = new String[2];
		String[][] judg_data = new String[EntryCount][2];
		String TgtDB = "NYANKO";
		int non_msg_fg = 1;

		judg_field[0] = "ClGpCd";			//荷主グループコード
		judg_field[1] = "ItemCd";			//商品コード
		
		for(int i01=0;i01<EntryCount;i01++) {
			judg_data[i01][0] = GetClGpCd[i01];		//荷主グループコード
			judg_data[i01][1] = GetItemCd[i01];		//商品コード
			
			entry_data[i01][ 0] = GetClGpCd[i01] ;			//荷主グループコード
			entry_data[i01][ 1] = GetItemCd[i01];			//商品コード
			entry_data[i01][ 2] = GetCLItemCd[i01];			//荷主商品コード
			entry_data[i01][ 3] = GetItemName01[i01];		//商品名1
			entry_data[i01][ 4] = GetItemName02[i01];		//商品名2
			entry_data[i01][ 5] = GetItemName03[i01];		//商品名3
			entry_data[i01][ 6] = GetItemWeight[i01];		//商品重量
			entry_data[i01][ 7] = GetItemSize[i01];			//商品サイズ
			entry_data[i01][ 8] = GetDeliveryTypeCd01[i01];	//運送タイプコード01
			entry_data[i01][ 9] = GetDeliveryTypeCd02[i01];	//運送タイプコード02
			entry_data[i01][10] = GetDeliveryTypeCd03[i01];	//運送タイプコード03
			entry_data[i01][11] = GetDeliveryTypeCd04[i01];	//運送タイプコード04
			entry_data[i01][12] = GetDeliveryTypeCd05[i01];	//運送タイプコード05
			entry_data[i01][13] = now_dtm;	//データ登録日時
			entry_data[i01][14] = now_dtm;	//データ更新日時
			entry_data[i01][15] = "(" + A00000Main.LoginUserId + ")" + A00000Main.LoginUserName;	//登録者コード
			entry_data[i01][16] = "(" + A00000Main.LoginUserId + ")" + A00000Main.LoginUserName;	//更新者コード
			entry_data[i01][17] = GetDelFg[i01];	//削除フラグ
			entry_data[i01][18] = GetPTMSCD[i01];	//基幹SYS商品コード
			entry_data[i01][19] = GetJanCd[i01];	//JANCD
			entry_data[i01][20] = GetUnitName[i01];	//商品単位
		}
		if(0<EntryCount) {
			A00020InsertUdateSQL.RUN_SQLS_EU(tgt_table, field_name, entry_data, judg_field, judg_data, non_msg_fg,TgtDB);
		}
		
		String[][] ItemMstSubSetString = {
				 {"ClGpCd"			,"1","1"}	//荷主グループコード
				,{"ItemCd"			,"1","1"}	//商品コード
				,{"CtQty"			,"1","1"}	//カートン入数
				,{"CsQty"			,"1","1"}	//ケース入数
				,{"PlQty"			,"1","1"}	//パレット入数
				,{"CtJan"			,"1","1"}	//カートンバーコード
				,{"CsJan"			,"1","1"}	//ケースバーコード
				,{"PlJan"			,"1","1"}	//パレットバーコード
				,{"CtName"			,"1","1"}	//カートン商品名称
				,{"CsName"			,"1","1"}	//ケース商品名称
				,{"PlName"			,"1","1"}	//パレット商品名称
				,{"CtUnitName"		,"1","1"}	//カートン商品単位
				,{"CsUnitName"		,"1","1"}	//ケース商品単位
				,{"PlUnitName"		,"1","1"}	//パレット商品単位
				,{"CtWeight"		,"1","1"}	//カートン重量
				,{"CsWeight"		,"1","1"}	//ケース重量
				,{"PlWeight"		,"1","1"}	//パレット重量
				,{"CtSize"			,"1","1"}	//カートンサイズ
				,{"CsSize"			,"1","1"}	//ケースサイズ
				,{"PlSize"			,"1","1"}	//パレットサイズ
				,{"RecomendLoc"		,"1","1"}	//推奨ロケ
				,{"ItemMDNo"		,"1","1"}	//商品モデル番号（型番）
				,{"CategoryCd"		,"1","1"}	//商品カテゴリCD
				,{"CategoryName"	,"1","1"}	//商品カテゴリ名
				,{"ItemColorCd"		,"1","1"}	//商品カラーコード
				,{"ItemColorName"	,"1","1"}	//商品カラー名
				,{"ItemSizeCd"		,"1","1"}	//商品サイズコード
				,{"ItemSizeName"	,"1","1"}	//商品サイズ名
				,{"Com01"			,"1","1"}	//コメント1
				,{"Com02"			,"1","1"}	//コメント2
				,{"Com03"			,"1","1"}	//コメント3
				,{"EntryDate"		,"1","0"}	//データ登録日時
				,{"UpdateDate"		,"1","1"}	//データ更新日時
				,{"EntryUser"		,"1","0"}	//登録者コード
				,{"UpdateUser"		,"1","1"}	//更新者コード
				,{"TildFG"			,"1","1"}	//温度区分
				,{"TildName"		,"1","1"}	//温度区分名
				,{"PictPass01"		,"1","1"}	//画像パス01
				,{"PictPass02"		,"1","1"}	//画像パス02
				,{"PictPass03"		,"1","1"}	//画像パス03
				,{"PictPass04"		,"1","1"}	//画像パス04
				,{"PictPass05"		,"1","1"}	//画像パス05
				,{"ExpDateHowLong"	,"1","1"}	//賞味期限日数
				};
		tgt_table = "KM0061_ITEMMSTSUB";
		field_name = ItemMstSubSetString;
		entry_data = new String[EntryCount][ItemMstSubSetString.length];
		judg_field = new String[2];
		judg_data = new String[EntryCount][2];
		TgtDB = "NYANKO";
		non_msg_fg = 1;

		judg_field[0] = "ClGpCd";			//荷主グループコード
		judg_field[1] = "ItemCd";			//商品コード
		
		for(int i01=0;i01<EntryCount;i01++) {
			judg_data[i01][0] = GetClGpCd[i01];		//荷主グループコード
			judg_data[i01][1] = GetItemCd[i01];		//商品コード
			
			entry_data[i01][ 0] = GetClGpCd[i01];			//荷主グループコード
			entry_data[i01][ 1] = GetItemCd[i01];			//商品コード
			entry_data[i01][ 2] = GetCtQty[i01];			//カートン入数
			entry_data[i01][ 3] = GetCsQty[i01];			//ケース入数
			entry_data[i01][ 4] = GetPlQty[i01];			//パレット入数
			entry_data[i01][ 5] = GetCtJan[i01];			//カートンバーコード
			entry_data[i01][ 6] = GetCsJan[i01];			//ケースバーコード
			entry_data[i01][ 7] = GetPlJan[i01];			//パレットバーコード
			entry_data[i01][ 8] = GetCtName[i01];			//カートン商品名称
			entry_data[i01][ 9] = GetCsName[i01];			//ケース商品名称
			entry_data[i01][10] = GetPlName[i01];			//パレット商品名称
			entry_data[i01][11] = GetCtUnitName[i01];		//カートン商品単位
			entry_data[i01][12] = GetCsUnitName[i01];		//ケース商品単位
			entry_data[i01][13] = GetPlUnitName[i01];		//パレット商品単位
			entry_data[i01][14] = GetCtWeight[i01];			//カートン重量
			entry_data[i01][15] = GetCsWeight[i01];			//ケース重量
			entry_data[i01][16] = GetPlWeight[i01];			//パレット重量
			entry_data[i01][17] = GetCtSize[i01];			//カートンサイズ
			entry_data[i01][18] = GetCsSize[i01];			//ケースサイズ
			entry_data[i01][19] = GetPlSize[i01];			//パレットサイズ
			entry_data[i01][20] = GetRecomendLoc[i01];		//推奨ロケ
			entry_data[i01][21] = GetItemMDNo[i01];			//商品モデル番号（型番）
			entry_data[i01][22] = GetCategoryCd[i01];		//商品カテゴリCD
			entry_data[i01][23] = GetCategoryName[i01];		//商品カテゴリ名
			entry_data[i01][24] = GetItemColorCd[i01];		//商品カラーコード
			entry_data[i01][25] = GetItemColorName[i01];	//商品カラー名
			entry_data[i01][26] = GetItemSizeCd[i01];		//商品サイズコード
			entry_data[i01][27] = GetItemSizeName[i01];		//商品サイズ名
			entry_data[i01][28] = GetCom01[i01];			//コメント1
			entry_data[i01][29] = GetCom02[i01];			//コメント2
			entry_data[i01][30] = GetCom03[i01];			//コメント3
			entry_data[i01][31] = now_dtm;					//データ登録日時
			entry_data[i01][32] = now_dtm;					//データ更新日時
			entry_data[i01][33] = "(" + A00000Main.LoginUserId + ")" + A00000Main.LoginUserName;	//登録者コード
			entry_data[i01][34] = "(" + A00000Main.LoginUserId + ")" + A00000Main.LoginUserName;	//更新者コード
			entry_data[i01][35] = GetTildFG[i01];			//温度区分
			entry_data[i01][36] = GetTildName[i01];			//温度区分名
			entry_data[i01][37] = GetItemImagePath01[i01];	//画像パス01
			entry_data[i01][38] = GetItemImagePath02[i01];	//画像パス02
			entry_data[i01][39] = GetItemImagePath03[i01];	//画像パス03
			entry_data[i01][40] = GetItemImagePath04[i01];	//画像パス04
			entry_data[i01][41] = GetItemImagePath05[i01];	//画像パス05
			entry_data[i01][42] = GetExpDateHowLong[i01];	//賞味期限日数
		}
		if(0<EntryCount) {
			A00020InsertUdateSQL.RUN_SQLS_EU(tgt_table, field_name, entry_data, judg_field, judg_data, non_msg_fg,TgtDB);
		}
	}
	
	private static ArrayList<String> ErrCheck(Object[][] CheckOb,String[]TableCol) {
		ArrayList<String> ErrMsg = new ArrayList<String>();
		
		int ColClGpCd			=  1;		//荷主グループコード
		int ColItemCd			=  2;		//商品コード
		int ColCLItemCd			=  3;		//荷主商品コード
		int ColItemName01		=  4;		//商品名1
		int ColItemName02		=  5;		//商品名2
		int ColItemName03		=  6;		//商品名3
		int ColDeliveryTypeCd01	=  7;		//運送タイプコード01
		int ColDeliveryTypeCd02	=  8;		//運送タイプコード02
		int ColDeliveryTypeCd03	=  9;		//運送タイプコード03
		int ColDeliveryTypeCd04	= 10;		//運送タイプコード04
		int ColDeliveryTypeCd05	= 11;		//運送タイプコード05
		int ColPTMSCD			= 12;		//基幹SYS商品コード
		int ColCtQty			= 13;		//カートン入数
		int ColCsQty			= 14;		//ケース入数
		int ColPlQty			= 15;		//パレット入数
		int ColJanCd			= 16;		//JANCD
		int ColCtJan			= 17;		//カートンバーコード
		int ColCsJan			= 18;		//ケースバーコード
		int ColPlJan			= 19;		//パレットバーコード
		int ColCtName			= 20;		//カートン商品名称
		int ColCsName			= 21;		//ケース商品名称
		int ColPlName			= 22;		//パレット商品名称
		int ColUnitName			= 23;		//商品単位
		int ColCtUnitName		= 24;		//カートン商品単位
		int ColCsUnitName		= 25;		//ケース商品単位
		int ColPlUnitName		= 26;		//パレット商品単位
		int ColItemWeight		= 27;		//商品重量
		int ColCtWeight			= 28;		//カートン重量
		int ColCsWeight			= 29;		//ケース重量
		int ColPlWeight			= 30;		//パレット重量
		int ColItemSize			= 31;		//商品サイズ
		int ColCtSize			= 32;		//カートンサイズ
		int ColCsSize			= 33;		//ケースサイズ
		int ColPlSize			= 34;		//パレットサイズ
		int ColRecomendLoc		= 35;		//推奨ロケ
		int ColItemMDNo			= 36;		//商品型番
		int ColCategoryCd		= 37;		//商品カテゴリCD
		int ColCategoryName		= 38;		//商品カテゴリ名
		int ColItemColorCd		= 39;		//商品カラーコード
		int ColItemColorName	= 40;		//商品カラー名
		int ColItemSizeCd		= 41;		//商品サイズコード
		int ColItemSizeName		= 42;		//商品サイズ名
		int ColCom01			= 43;		//コメント1
		int ColCom02			= 44;		//コメント2
		int ColCom03			= 45;		//コメント3
		int ColTildFG			= 46;		//温度区分
		int ColItemImagePath01	= 47;		//画像パス01 
		int ColItemImagePath02	= 48;		//画像パス02
		int ColItemImagePath03	= 49;		//画像パス03
		int ColItemImagePath04	= 50;		//画像パス04
		int ColItemImagePath05	= 51;		//画像パス05
		int ColExpDateHowLong	= 52;		//賞味期限日数
		int ColDelFg			= 53;		//削除フラグ
		
		for(int i=0;i<TableCol.length;i++) {
			switch(TableCol[i]) {
				case "荷主グループコード":
					ColClGpCd = i;
					break;
				case "商品コード":
					ColItemCd = i;
					break;
				case "荷主商品コード":
					ColCLItemCd = i;
					break;
				case "商品名1":
					ColItemName01 = i;
					break;
				case "商品名2":
					ColItemName02 = i;
					break;
				case "商品名3":
					ColItemName03 = i;
					break;
				case "運送タイプコード01":
					ColDeliveryTypeCd01 = i;
					break;
				case "運送タイプコード02":
					ColDeliveryTypeCd02 = i;
					break;
				case "運送タイプコード03":
					ColDeliveryTypeCd03 = i;
					break;
				case "運送タイプコード04":
					ColDeliveryTypeCd04 = i;
					break;
				case "運送タイプコード05":
					ColDeliveryTypeCd05 = i;
					break;
				case "基幹システム商品コード":
					ColPTMSCD = i;
					break;
				case "カートン入数":
					ColCtQty = i;
					break;
				case "ケース入数":
					ColCsQty = i;
					break;
				case "パレット入数":
					ColPlQty = i;
					break;
				case "JANCD":
					ColJanCd = i;
					break;
				case "カートンバーコード":
					ColCtJan = i;
					break;
				case "ケースバーコード":
					ColCsJan = i;
					break;
				case "パレットバーコード":
					ColPlJan = i;
					break;
				case "カートン商品名称":
					ColCtName = i;
					break;
				case "ケース商品名称":
					ColCsName = i;
					break;
				case "パレット商品名称":
					ColPlName = i;
					break;
				case "商品単位":
					ColUnitName = i;
					break;
				case "カートン商品単位":
					ColCtUnitName = i;
					break;
				case "ケース商品単位":
					ColCsUnitName = i;
					break;
				case "パレット商品単位":
					ColPlUnitName = i;
					break;
				case "商品重量":
					ColItemWeight = i;
					break;
				case "カートン重量":
					ColCtWeight = i;
					break;
				case "ケース重量":
					ColCsWeight = i;
					break;
				case "パレット重量":
					ColPlWeight = i;
					break;
				case "商品サイズ":
					ColItemSize = i;
					break;
				case "カートンサイズ":
					ColCtSize = i;
					break;
				case "ケースサイズ":
					ColCsSize = i;
					break;
				case "パレットサイズ":
					ColPlSize = i;
					break;
				case "推奨ロケ":
					ColRecomendLoc = i;
					break;
				case "商品モデル番号（型番）":
					ColItemMDNo = i;
					break;
				case "商品カテゴリCD":
					ColCategoryCd = i;
					break;
				case "商品カテゴリ名":
					ColCategoryName = i;
					break;
				case "商品カラーコード":
					ColItemColorCd = i;
					break;
				case "商品カラー名":
					ColItemColorName = i;
					break;
				case "商品サイズコード":
					ColItemSizeCd = i;
					break;
				case "商品サイズ名":
					ColItemSizeName = i;
					break;
				case "コメント1":
					ColCom01 = i;
					break;
				case "コメント2":
					ColCom02 = i;
					break;
				case "コメント3":
					ColCom03 = i;
					break;
				case "温度区分":
					ColTildFG = i;
					break;
				case "画像パス01":
					ColItemImagePath01 = i;
					break;
				case "画像パス02":
					ColItemImagePath02 = i;
					break;
				case "画像パス03":
					ColItemImagePath03 = i;
					break;
				case "画像パス04":
					ColItemImagePath04 = i;
					break;
				case "画像パス05":
					ColItemImagePath05 = i;
					break;
				case "賞味期限日数":
					ColExpDateHowLong = i;
					break;
				case "削除フラグ":
					ColDelFg = i;
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
			SearchClGpCD.add(""+CheckOb[i][ColClGpCd]);
		}
		Object[][] ClGpMstRt = M00010ClGpMstRt.ClGpMstRt(
				SearchClGpCD,
				SearchCLGpName,
				SearchPost,
				SearchAdd,
				SearchTel,
				SearchFax,
				SearchMail,
				SearchCom,
				AllSearch);
		
		for(int i=0;i<CheckOb.length;i++) {
			//商品CD・商品名が空白ならSKIP
			for(int i01=0;i01<CheckOb[i].length;i01++) {
				CheckOb[i][i01] = B00020ToolsTextControl.Trim(""+CheckOb[i][i01]);
			}
			if("".equals(""+CheckOb[i][ColItemCd])
					&& "".equals(""+CheckOb[i][ColItemName01])
					) {
			}else {
				boolean UnHitFg = true;
				for(int i01=0;i01<ClGpMstRt.length;i01++) {
					if((""+CheckOb[i][ColClGpCd]).equals(""+ClGpMstRt[i01][M00010ClGpMstRt.ColClGpCD])) {
						UnHitFg = false;
						i01=ClGpMstRt.length+1;
					}
				}
				if(UnHitFg) {
					int wint=i+1;
					ErrMsg.add(wint+"行目エラー("+CheckOb[i][ColClGpCd]+")は荷主グループCDにありません");
				}
				
				UnHitFg = true;
				for(int i01=0;i01<B00100DefaultVariable.DeliveryType01[1].length;i01++) {
					if((""+CheckOb[i][ColDeliveryTypeCd01]).equals(B00100DefaultVariable.DeliveryType01[1][i01])) {
						UnHitFg = false;
						i01=B00100DefaultVariable.DeliveryType01[1].length+1;
					}
				}
				if(UnHitFg) {
					int wint=i+1;
					ErrMsg.add(wint+"行目エラー("+CheckOb[i][ColDeliveryTypeCd01]+")は運送タイプCD01にありません");
				}
	
				UnHitFg = true;
				for(int i01=0;i01<B00100DefaultVariable.DeliveryType02[1].length;i01++) {
					if((""+CheckOb[i][ColDeliveryTypeCd02]).equals(B00100DefaultVariable.DeliveryType02[1][i01])) {
						UnHitFg = false;
						i01=B00100DefaultVariable.DeliveryType02[1].length+1;
					}
				}
				if(UnHitFg) {
					int wint=i+1;
					ErrMsg.add(wint+"行目エラー("+CheckOb[i][ColDeliveryTypeCd02]+")は運送タイプCD02にありません");
				}
	
				UnHitFg = true;
				for(int i01=0;i01<B00100DefaultVariable.DeliveryType03[1].length;i01++) {
					if((""+CheckOb[i][ColDeliveryTypeCd03]).equals(B00100DefaultVariable.DeliveryType03[1][i01])) {
						UnHitFg = false;
						i01=B00100DefaultVariable.DeliveryType03[1].length+1;
					}
				}
				if(UnHitFg) {
					int wint=i+1;
					ErrMsg.add(wint+"行目エラー("+CheckOb[i][ColDeliveryTypeCd03]+")は運送タイプCD03にありません");
				}
	
				UnHitFg = true;
				for(int i01=0;i01<B00100DefaultVariable.DeliveryType04[1].length;i01++) {
					if((""+CheckOb[i][ColDeliveryTypeCd04]).equals(B00100DefaultVariable.DeliveryType04[1][i01])) {
						UnHitFg = false;
						i01=B00100DefaultVariable.DeliveryType04[1].length+1;
					}
				}
				if(UnHitFg) {
					int wint=i+1;
					ErrMsg.add(wint+"行目エラー("+CheckOb[i][ColDeliveryTypeCd04]+")は運送タイプCD04にありません");
				}
	
				UnHitFg = true;
				for(int i01=0;i01<B00100DefaultVariable.DeliveryType05[1].length;i01++) {
					if((""+CheckOb[i][ColDeliveryTypeCd05]).equals(B00100DefaultVariable.DeliveryType05[1][i01])) {
						UnHitFg = false;
						i01=B00100DefaultVariable.DeliveryType05[1].length+1;
					}
				}
				if(UnHitFg) {
					int wint=i+1;
					ErrMsg.add(wint+"行目エラー("+CheckOb[i][ColDeliveryTypeCd05]+")は運送タイプCD05にありません");
				}
	
				UnHitFg = true;
				for(int i01=0;i01<B00100DefaultVariable.TildFG[1].length;i01++) {
					if((""+CheckOb[i][ColTildFG]).equals(B00100DefaultVariable.TildFG[1][i01])) {
						UnHitFg = false;
						i01=B00100DefaultVariable.TildFG[1].length+1;
					}
				}
				if(UnHitFg) {
					int wint=i+1;
					ErrMsg.add(wint+"行目エラー("+CheckOb[i][ColTildFG]+")は温度区分にありません");
				}
	
				UnHitFg = true;
				for(int i01=0;i01<B00100DefaultVariable.DelList[1].length;i01++) {
					if((""+CheckOb[i][ColDelFg]).equals(B00100DefaultVariable.DelList[1][i01])) {
						UnHitFg = false;
						i01=B00100DefaultVariable.DelList[1].length+1;
					}
				}
				if(UnHitFg) {
					int wint=i+1;
					ErrMsg.add(wint+"行目エラー("+CheckOb[i][ColDelFg]+")は削除区分にありません");
				}
				
				if("".equals(""+CheckOb[i][ColItemCd])) {
					int wint=i+1;
					ErrMsg.add(wint+"行目エラー "+CheckOb[i][ColItemCd]+"商品コードは必須です");
				}
				
				if("".equals(""+CheckOb[i][ColItemName01])) {
					int wint=i+1;
					ErrMsg.add(wint+"行目エラー "+CheckOb[i][ColItemName01]+"商品名01は必須です");
				}
			}
		}
		return ErrMsg;
	}
	
	private static void ErrView(ArrayList<String>ErrMsg) {
		//必要フォルダを生成する
		String FLD_PATH = A00000Main.MainFLD+"\\MstControl";
		B00040ToolsFolderCheck.FLD_CHECK(FLD_PATH);
		FLD_PATH = A00000Main.MainFLD+"\\MstControl\\ItemMst";
		B00040ToolsFolderCheck.FLD_CHECK(FLD_PATH);
		FLD_PATH = A00000Main.MainFLD+"\\MstControl\\ItemMst\\Err";
		B00040ToolsFolderCheck.FLD_CHECK(FLD_PATH);
		FLD_PATH = A00000Main.MainFLD+"\\MstControl\\ItemMst\\BK";
		B00040ToolsFolderCheck.FLD_CHECK(FLD_PATH);
		
		//ファイルに出力
		String NowDTM=B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[1].replace(" ", "").replace("/", "").replace(":", "");
		
		FLD_PATH = A00000Main.MainFLD+"\\MstControl\\ItemMst\\Err";
		
		String ErrFP = FLD_PATH+"\\ERR"+NowDTM+".txt";
		
		B00030ToolsTextExport.txt_exp2(ErrMsg, ErrFP,"UTF-8");
		
		//古いエラーデータ削除
		B00040ToolsFolderCheck.ToolsOldFileDeleteWhereFileName(FLD_PATH ,"ERR",B00100DefaultVariable.ErrTxtDelete);
		
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