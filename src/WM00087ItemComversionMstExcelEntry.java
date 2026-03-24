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

public class WM00087ItemComversionMstExcelEntry{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	public static void ItemComversionMstExcelEntry(int x,int y,String TgtFilePath) {
		A00000Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		final JFrame main_fm = B00110FrameParts.FrameCreate(x,y,600,200,"Corgi00商品変換マスタ登録（エクセル）","");
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
				WM00085ItemComversionMstSearch.ItemComversionMstSearch(0, 0);
			}
		});
	}
	public static void ItemComversionMstExcelEntryMain(int x,int y,String TgtFilePath,String SheetName) {
		A00000Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		final JFrame main_fm = B00110FrameParts.FrameCreate(x,y,550,800,"Corgi00商品変換マスタ登録（エクセル）","");
		JLabel userinfo = B00110FrameParts.UserInfo();
		JButton exit_btn = B00110FrameParts.ExitBtn();
		JButton entry_btn = B00110FrameParts.EntryBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		main_fm.add(entry_btn);
		
		String[] NeedCol = {
					 "荷主グループコード"
					,"荷主コード"
					,"荷主商品コード"
					,"変換先商品コード"
					,"荷姿タイプ"
					};
		
		int[] TgtCol = new int[NeedCol.length];
		for(int i=0;i<NeedCol.length;i++) {TgtCol[i] = -1;}
		
		
		JLabel LB_SheetList	= B00110FrameParts.JLabelSet(	10, 40,540,20,"以下のデータを登録しようとしています※データ内の重複はチェックしません",11,0);
		main_fm.add(LB_SheetList);
		
		String[] columnNames01 = new String[NeedCol.length+1];
		columnNames01[0] = "Fg";
		for(int i=0;i<NeedCol.length;i++) {columnNames01[i+1] = NeedCol[i];}
		
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
		column = columnModel01.getColumn( 1);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//荷主グループコード
		column = columnModel01.getColumn( 2);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//荷主コード
		column = columnModel01.getColumn( 3);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//荷主商品コード
		column = columnModel01.getColumn( 4);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//変換先商品コード
		column = columnModel01.getColumn( 5);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//荷姿タイプ
		
		JScrollPane scpn01 = B00110FrameParts.JScrollPaneSet(10,65,500,600,tb01);
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
			SetX=main_fm.getX();
			SetY=main_fm.getY();

			main_fm.setVisible(false);
			main_fm.dispose();
			
			String Msg = "ヘッダ行で取込ファイルのレイアウト判別ができませんでした。\n確認しやがれください\n";
			for(int i=0;i<NeedCol.length;i++) {
				if(0<i&&0==i%5) {
					Msg = Msg + NeedCol[i] + ",\n";
				}else {
					Msg = Msg + NeedCol[i] + ",";
				}
			}
			Msg = Msg+"\nがヘッダに必要です";
			
			JOptionPane.showMessageDialog(null, Msg);
			ItemComversionMstExcelEntry(0,0,TgtFilePath);
		}else {
			int[] ClmnType = new int[HeaderRead[0].length];
			for(int i=0;i<ClmnType.length;i++) {ClmnType[i]=1;}
			
			ClmnType[TgtCol[ 0]]=1;	//荷主グループコード
			ClmnType[TgtCol[ 1]]=1;	//荷主コード
			ClmnType[TgtCol[ 2]]=1;	//荷主商品コード
			ClmnType[TgtCol[ 3]]=1;	//変換先商品コード
			ClmnType[TgtCol[ 4]]=1;	//荷姿タイプ
			
			Object[][] ExcellRead = B00060ToolsExcellControl.ExcellRead(TgtFilePath,SheetName,ClmnType,true);
			Object[][] CheckOb = new Object[ExcellRead.length][NeedCol.length+1];
			
			if(0<ExcellRead.length&&ClmnType.length<=ExcellRead[0].length) {
				for(int i=0;i<ExcellRead.length;i++) {
					Object[] SetOb = new Object[NeedCol.length+1];
					CheckOb[i][0] = false;
					SetOb[ 0] = false;
					for(int i01=0;i01<NeedCol.length;i01++) {
						SetOb[i01+1] = ExcellRead[i][TgtCol[i01]];
						CheckOb[i][i01+1]=ExcellRead[i][TgtCol[i01]];
					}
					tableModel_ms01.addRow(SetOb);
				}
			}
			ArrayList<String> ErrMsg = ErrCheck(CheckOb);
			if(null!=ErrMsg && 0<ErrMsg.size()) {
				ErrView(ErrMsg);
				
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				WM00085ItemComversionMstSearch.ItemComversionMstSearch(0, 0);
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
					
					Object[][] CheckOb = new Object[RowCount][6];
					for(int i=0;i<RowCount;i++) {
						CheckOb[i][0] = ""+tableModel_ms01.getValueAt(i, 0);
						CheckOb[i][1] = ""+tableModel_ms01.getValueAt(i, 1);	//荷主グループコード
						CheckOb[i][2] = ""+tableModel_ms01.getValueAt(i, 2);	//荷主コード
						CheckOb[i][3] = ""+tableModel_ms01.getValueAt(i, 3);	//荷主商品コード
						CheckOb[i][4] = ""+tableModel_ms01.getValueAt(i, 4);	//変換先商品コード
						CheckOb[i][5] = ""+tableModel_ms01.getValueAt(i, 5);	//荷姿タイプ
						
						CheckOb[i][0] = B00020ToolsTextControl.Trim(""+CheckOb[i][0]);
						CheckOb[i][1] = B00020ToolsTextControl.Trim(""+CheckOb[i][1]);
						CheckOb[i][2] = B00020ToolsTextControl.Trim(""+CheckOb[i][2]);
						CheckOb[i][3] = B00020ToolsTextControl.Trim(""+CheckOb[i][3]);
						CheckOb[i][4] = B00020ToolsTextControl.Trim(""+CheckOb[i][4]);
						CheckOb[i][5] = B00020ToolsTextControl.Trim(""+CheckOb[i][5]);
						
						CheckOb[i][5] = B00020ToolsTextControl.num_only_String(""+CheckOb[i][5]);
						if("".equals(""+CheckOb[i][5])) {CheckOb[i][5] = "0";}
					}
					
					ArrayList<String> ErrMsg = ErrCheck(CheckOb);
					
					if(null!=ErrMsg && 0<ErrMsg.size() && 0<CheckOb.length) {
						ErrView(ErrMsg);
					}else {
						String[] GetClGpCd 		= new String[CheckOb.length];	//荷主グループコード
						String[] GetClCd 		= new String[CheckOb.length];	//荷主コード
						String[] GetCLItemCd	= new String[CheckOb.length];	//荷主商品コード
						String[] GetItemCd		= new String[CheckOb.length];	//商品コード
						String[] GetUnitType	= new String[CheckOb.length];	//荷姿
						
						for(int i=0;i<CheckOb.length;i++) {
							GetClGpCd[i] 	= ""+CheckOb[i][1];	//荷主グループコード
							GetClCd[i] 		= ""+CheckOb[i][2];	//荷主コード
							GetCLItemCd[i]	= ""+CheckOb[i][3];	//荷主商品コード
							GetItemCd[i]	= ""+CheckOb[i][4];	//商品コード
							GetUnitType[i]	= ""+CheckOb[i][5];	//荷姿
						}
						
						String now_dtm = B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[1];
						
						String[][] ItemComversionMstSetString = {
								 {"ClGpCd"		,"1","1"}	//荷主グループコード
								,{"ClCd"		,"1","1"}	//荷主コード
								,{"ClItemCd"	,"1","1"}	//荷主商品コード
								,{"ItemCd"		,"1","1"}	//変換先商品コード
								,{"PackingType"	,"1","1"}	//荷姿タイプ
								};
						String tgt_table = "KM0062_ItemComversionMst";
						String[][] field_name = new String[ItemComversionMstSetString.length][3];
						String[][] entry_data = new String[CheckOb.length][ItemComversionMstSetString.length];
						String[] judg_field = new String[3];
						String[][] judg_data = new String[CheckOb.length][3];
						String TgtDB = "NYANKO";
						int non_msg_fg = 1;
			
						judg_field[0] = "ClGpCd";		//荷主グループコード
						judg_field[1] = "ClCd";			//荷主コード
						judg_field[2] = "ClItemCd";		//荷主商品コード
						
						for(int i=0;i<ItemComversionMstSetString.length;i++) {
							field_name[i][0] = ItemComversionMstSetString[i][0];
							field_name[i][1] = ItemComversionMstSetString[i][1];
							field_name[i][2] = ItemComversionMstSetString[i][2];
						}
						for(int i=0;i<CheckOb.length;i++) {
							judg_data[i][0] = GetClGpCd[i];		//荷主グループコード
							judg_data[i][1] = GetClCd[i];		//荷主コード
							judg_data[i][2] = GetCLItemCd[i];	//荷主商品コード
							
							//entry_data[i][] = 
							entry_data[i][0] = GetClGpCd[i];	//荷主グループコード
							entry_data[i][1] = GetClCd[i];		//荷主コード
							entry_data[i][2] = GetCLItemCd[i];	//荷主商品コード
							entry_data[i][3] = GetItemCd[i];	//商品コード
							entry_data[i][4] = GetUnitType[i];	//荷姿
						}
						A00020InsertUdateSQL.RUN_SQLS_EU(tgt_table, field_name, entry_data, judg_field, judg_data, non_msg_fg,TgtDB);
						
						SetX=main_fm.getX();
						SetY=main_fm.getY();
	
						main_fm.setVisible(false);
						main_fm.dispose();
						WM00085ItemComversionMstSearch.ItemComversionMstSearch(0, 0);
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
				WM00085ItemComversionMstSearch.ItemComversionMstSearch(0, 0);
			}
		});
	}
	
	private static ArrayList<String> ErrCheck(Object[][] CheckOb){
		ArrayList<String> ErrMsg = new ArrayList<String>();
		boolean UnHitFg = true;
		
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
			SearchClGpCD.add(""+CheckOb[i][1]);
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
			SearchCLCD.add(""+CheckOb[i][2]);
		}
		
		Object[][] ClMstRt = M00011ClMstRt.ClMstRt(
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
			SearchClGpCd.add(""+CheckOb[i][1]);
			SearchItemCd.add(""+CheckOb[i][4]);
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
				SearchItemSizeName,		//商品サイス名
				SearchJanCd,			//JANCD
				SearchTildFG,			//温度区分
				SearchTildName,			//温度区分名
				SearchDelFg,			//削除フラグ
				AllSearch);
		
		for(int i=0;i<CheckOb.length;i++) {
			UnHitFg = true;
			switch(""+CheckOb[i][5]) {
				case "0":
					UnHitFg = false;
					break;
				case "1":
					UnHitFg = false;
					break;
				case "2":
					UnHitFg = false;
					break;
				case "3":
					UnHitFg = false;
					break;
				default:
					break;
			}
			if(UnHitFg) {
				int wint = i+1;
				ErrMsg.add(wint+"行目エラー("+CheckOb[i][5]+")は荷姿設定として不正です");
			}
			
			UnHitFg = true;
			for(int i01=0;i01<ClGpMstRt.length;i01++) {
				if((""+CheckOb[i][1]).equals(""+ClGpMstRt[i01][0])) {
					UnHitFg = false;
					i01=ClGpMstRt.length+1;
				}
			}
			if(UnHitFg) {
				int wint = i+1;
				ErrMsg.add(wint+"行目エラー("+CheckOb[i][1]+")は荷主グループに登録されていないコードです");
			}
			
			
			UnHitFg = true;
			for(int i01=0;i01<ClMstRt.length;i01++) {
				if((""+CheckOb[i][1]).equals(""+ClMstRt[i01][1])&&(""+CheckOb[i][2]).equals(""+ClMstRt[i01][0])) {
					UnHitFg = false;
					i01=ClMstRt.length+1;
				}
			}
			if(UnHitFg) {
				int wint = i+1;
				ErrMsg.add(wint+"行目エラー("+CheckOb[i][2]+")は荷主に登録されていないコードです");
			}
			
			UnHitFg = true;
			for(int i01=0;i01<ItemMstRt.length;i01++) {
				if((""+CheckOb[i][1]).equals(""+ItemMstRt[i01][0])&&(""+CheckOb[i][4]).equals(""+ItemMstRt[i01][2])) {
					UnHitFg = false;
					switch(""+CheckOb[i][5]) {
						case "0":
							break;
						case "1":
							if(0>=(int)ItemMstRt[i01][18]) {
								int wint = i+1;
								ErrMsg.add(wint+"行目エラー("+ItemMstRt[i01][2]+")"+ItemMstRt[i01][4]+"にはカートン設定されていません");
							}
							break;
						case "2":
							if(0>=(int)ItemMstRt[i01][19]) {
								int wint = i+1;
								ErrMsg.add(wint+"行目エラー("+ItemMstRt[i01][2]+")"+ItemMstRt[i01][4]+"にはケース設定されていません");
							}
							break;
						case "3":
							if(0>=(int)ItemMstRt[i01][20]) {
								int wint = i+1;
								ErrMsg.add(wint+"行目エラー("+ItemMstRt[i01][2]+")"+ItemMstRt[i01][4]+"にはパレット設定されていません");
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
				ErrMsg.add(wint+"行目エラー("+CheckOb[i][4]+")は商品マスタに登録されていないコードです");
			}
		}
		return ErrMsg;
	}
	
	private static void ErrView(ArrayList<String>ErrMsg) {
		//必要フォルダを生成する
		String FLD_PATH = A00000Main.MainFLD+"\\MstControl";
		B00040ToolsFolderCheck.FLD_CHECK(FLD_PATH);
		FLD_PATH = A00000Main.MainFLD+"\\MstControl\\ItemComversionMst";
		B00040ToolsFolderCheck.FLD_CHECK(FLD_PATH);
		FLD_PATH = A00000Main.MainFLD+"\\MstControl\\ItemComversionMst\\Err";
		B00040ToolsFolderCheck.FLD_CHECK(FLD_PATH);
		FLD_PATH = A00000Main.MainFLD+"\\MstControl\\ItemComversionMst\\BK";
		B00040ToolsFolderCheck.FLD_CHECK(FLD_PATH);
		
		//ファイルに出力
		String NowDTM=B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[1].replace(" ", "").replace("/", "").replace(":", "");
		
		FLD_PATH = A00000Main.MainFLD+"\\MstControl\\ItemComversionMst\\Err";
		
		String ErrFP = FLD_PATH+"\\ERR"+NowDTM+".txt";
		
		B00030ToolsTextExport.txt_exp2(ErrMsg, ErrFP,"UTF-8");
		
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