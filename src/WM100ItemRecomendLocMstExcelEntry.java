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

public class WM100ItemRecomendLocMstExcelEntry{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	public static void ItemRecomendLocMstExcelEntry(int x,int y,String TgtFilePath) {
		A00000Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;

		final JFrame main_fm = B100FrameParts.FrameCreate(x,y,600,200,"Corgi00推奨ロケマスタ登録（エクセル）","");
		JLabel userinfo = B100FrameParts.UserInfo();
		JButton exit_btn = B100FrameParts.ExitBtn();
		JButton entry_btn = B100FrameParts.EntryBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		main_fm.add(entry_btn);
		
		final String[] SheetList = B100ExcellControl.ExcellSheetList(TgtFilePath);
		
		JLabel LB_SheetList				= B100FrameParts.JLabelSet(		 20, 45,300,20,"登録するシートを選択してください"		,11,0);
		final JComboBox   TB_SheetList	= B100FrameParts.JComboBoxSet( 	 20, 70,250,20,SheetList,11);	//シート一覧
		
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
				ItemRecomendLocMstExcelEntryMain(0,0,TgtFilePath,SheetName);
			}
		});
		
		//EXITボタン押下時の挙動
		exit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				WM100ItemRecomendLocMstSearch.ItemRecomendLocMstSearch(0, 0);
			}
		});
	}
	
	public static void ItemRecomendLocMstExcelEntryMain(int x,int y,String TgtFilePath,String SheetName) {
		A00000Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		
		final JFrame main_fm = B100FrameParts.FrameCreate(x,y,500,800,"Corgi00推奨ロケマスタ登録（エクセル）","");
		JLabel userinfo = B100FrameParts.UserInfo();
		JButton exit_btn = B100FrameParts.ExitBtn();
		JButton entry_btn = B100FrameParts.EntryBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		main_fm.add(entry_btn);
		
		Object[][] NeedCol = {
				 {"荷主コード"		, 1	, 0}
				,{"担当倉庫コード"	, 1	, 1}
				,{"商品コード"		, 1	, 2}
				,{"推奨ロケ"		, 1	, 3}
				};	//フィールド名,フィールドタイプ(0:数値 1:文字列 2:日付時刻),基本のカラム(ゼロスタート),基本のカラム位置※カラム位置は後で読み込んだエクセルの1行目でフィールド名比較して更新されます
		
		JLabel LB_SheetList	= B100FrameParts.JLabelSet(	10, 40,540,20,"以下のデータを登録しようとしています",11,0);
		main_fm.add(LB_SheetList);
		
		String[] columnNames01 = new String[NeedCol.length+1];
		columnNames01[0] = "Fg";
		for(int i=0;i<NeedCol.length;i++) {columnNames01[i+1] = (String)NeedCol[i][0];}
		
		//編集可能カラムの指定
		B100TableControl.RenewTgt = new int[1];
		B100TableControl.RenewTgt[0] = 0;

		final DefaultTableModel tableModel_ms01 = new B100TableControl.MyTableModel01(columnNames01,0);
		
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
				column = columnModel01.getColumn(i+1);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B100FrameParts.rightCellRenderer());	
			}else {
				column = columnModel01.getColumn(i+1);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B100FrameParts.leftCellRenderer());	
			}
		}
		JScrollPane scpn01 = B100FrameParts.JScrollPaneSet(10,65,460,600,tb01);
		main_fm.add(scpn01);
		
		//ヘッダ行取得⇒フィールド名判定
		//必要フィールドなければシート選択に戻る
		Object[][] HeaderRead = B100ExcellControl.ExcellRead2(TgtFilePath,SheetName,1,0);
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
			ItemRecomendLocMstExcelEntry(0,0,TgtFilePath);
		}else {
			int[] ClmnType = new int[HeaderRead[0].length];
			for(int i=0;i<ClmnType.length;i++) {ClmnType[i]=1;}
			
			for(int i01=0;i01<NeedCol.length;i01++) {
				ClmnType[(int)NeedCol[i01][2]]=(int)NeedCol[i01][1];
			}
			Object[][] ExcellRead = B100ExcellControl.ExcellRead(TgtFilePath,SheetName,ClmnType,true);
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
			String[] TableCol = B100TableControl.TableFieldNameRt(tb01);
			Object[][] SetObRt = SetObRt(CheckOb,TableCol);
			ArrayList<String> ErrMsg = ErrCheck(SetObRt);
			
			if(null!=ErrMsg && 0<ErrMsg.size()) {
				ErrView(ErrMsg);
				
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				WM100SupplierMstSearch.SupplierMstSearch(0, 0);
			}else {
				main_fm.setVisible(true);
			}
		}
		RenewFg = true;

		//登録ボタン押下時の挙動
		entry_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					String[] TableCol = B100TableControl.TableFieldNameRt(tb01);
					int RowCount = tableModel_ms01.getRowCount();
					Object[][] CheckOb = new Object[RowCount][TableCol.length];
					for(int i=0;i<RowCount;i++) {
						for(int i01=0;i01<TableCol.length;i01++) {
							CheckOb[i][i01] = ""+tableModel_ms01.getValueAt(i, i01);
						}
					}
					Object[][] SetObRt = SetObRt(CheckOb,TableCol);
					ArrayList<String> ErrMsg = ErrCheck(SetObRt);
					
					if(null!=ErrMsg && 0<ErrMsg.size() && 0<RowCount) {
						ErrView(ErrMsg);
					}else {
						MstEntry(SetObRt);
						//ファイルバックアップ
						B100FolderCheck.FileBackUpNormal(TgtFilePath) ;
						
						SetX=main_fm.getX();
						SetY=main_fm.getY();

						main_fm.setVisible(false);
						main_fm.dispose();
						WM100ItemRecomendLocMstSearch.ItemRecomendLocMstSearch(0, 0);
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
				WM100ItemRecomendLocMstSearch.ItemRecomendLocMstSearch(0, 0);
			}
		});
	}
	private static void MstEntry(Object[][] SetOb) {
		String tgt_table = "WW00630ItemRecomendLoc";
		String TgtDB = "WANKO";
		int non_msg_fg = 0;
		
		A100InsertUdateSQL.InsertUpdateSomeRecord(SetOb,tgt_table,TgtDB,non_msg_fg);
	}
	
	private static ArrayList<String> ErrCheck(Object[][] SetObRt){
		ArrayList<String> ErrMsg = new ArrayList<String>();
		ArrayList<String> SearchRecomendLoc 	= new ArrayList<String>();	//ロケーション
		ArrayList<String> SearchItemCd 			= new ArrayList<String>();	//商品CD
		
		//ログイン中の荷主コード・倉庫コードでなければエラー
		//主賓コード検索条件とロケーションコード検索条件にADD
		for(int i=0;i<SetObRt.length;i++) {
			if("ClCd".equals((String)SetObRt[i][0])) {
				for(int i01=0;i01<((String[])SetObRt[i][4]).length;i01++) {
					if(!A00000Main.ClCd.equals(((String[])SetObRt[i][4])[i01])) {
						int wint = i01+1;
						ErrMsg.add(wint+"行目エラー:("+((String[])SetObRt[i][4])[i01]+")は現在ログイン中の荷主と異なります");
					}
				}
			}
			if("ClWh".equals((String)SetObRt[i][0])) {
				for(int i01=0;i01<((String[])SetObRt[i][4]).length;i01++) {
					if(!A00000Main.ClWh.equals(((String[])SetObRt[i][4])[i01])) {
						int wint = i01+1;
						ErrMsg.add(wint+"行目エラー:("+((String[])SetObRt[i][4])[i01]+")は現在ログイン中の担当倉庫と異なります");
					}
				}
			}
			if("ItemCd".equals((String)SetObRt[i][0])) {
				for(int i01=0;i01<((String[])SetObRt[i][4]).length;i01++) {
					if("".equals((String)((String[])SetObRt[i][4])[i01])) {
						int wint = i01+1;
						ErrMsg.add(wint+"行目エラー:商品コードは必須です");
					}else {
						SearchItemCd.add(((String[])SetObRt[i][4])[i01]);
					}
				}
			}
			if("RecomendLoc".equals((String)SetObRt[i][0])) {
				for(int i01=0;i01<((String[])SetObRt[i][4]).length;i01++) {
					if("".equals((String)((String[])SetObRt[i][4])[i01])) {
						
					}else {
						SearchRecomendLoc.add(((String[])SetObRt[i][4])[i01]);
					}
				}
			}
		}
		Object[][] ItemMstRt	= ItemMstRt(SearchItemCd);
		Object[][] LocMstRt		= LocMstRt(SearchRecomendLoc);
		for(int i=0;i<SetObRt.length;i++) {
			if("ClCd".equals((String)SetObRt[i][0])) {
			}
			if("ClWh".equals((String)SetObRt[i][0])) {
			}
			if("ItemCd".equals((String)SetObRt[i][0])) {
				for(int i01=0;i01<((String[])SetObRt[i][4]).length;i01++) {
					if("".equals((String)((String[])SetObRt[i][4])[i01])) {
						
					}else {
						boolean UnHitFg = true;
						String CheckItemCd = ((String[])SetObRt[i][4])[i01];
						
						for(int i02=0;i02<ItemMstRt.length;i02++) {
							String MstItemCd = (String)ItemMstRt[i02][M100ItemMstRt.ColItemCd];				//商品コード
							if(MstItemCd.equals(CheckItemCd)) {
								UnHitFg = false;
								i02=ItemMstRt.length+1;
							}
						}
						
						if(UnHitFg) {
							int wint = i01+1;
							ErrMsg.add(wint+"行目エラー:商品CD("+CheckItemCd+")はマスタにないよ");
						}
					}
				}
			}
			if("RecomendLoc".equals((String)SetObRt[i][0])) {
				for(int i01=0;i01<((String[])SetObRt[i][4]).length;i01++) {
					if("".equals((String)((String[])SetObRt[i][4])[i01])) {
						
					}else {
						boolean UnHitFg = true;
						String CheckRecomendLoc = ((String[])SetObRt[i][4])[i01];
						for(int i02=0;i02<LocMstRt.length;i02++) {
							String MstLoc	= (String)LocMstRt[i02][M100LocationMstRt.ColLoc];			//ロケーション
							if(MstLoc.equals(CheckRecomendLoc)) {
								UnHitFg = false;
								i02=LocMstRt.length+1;
							}
						}
						
						if(UnHitFg) {
							int wint = i01+1;
							ErrMsg.add(wint+"行目エラー:ロケーション("+CheckRecomendLoc+")はマスタにないよ");
						}
					}
				}
			}
		}
		return ErrMsg;
	}
	
	private static Object[][] SetObRt(
			Object[][] CheckOb,String[] TableCol) {
		String now_dtm = B100DateTimeControl.dtmString2(B100DateTimeControl.dtm()[1])[1];
		
		int ColClCd				= (int)1;	//荷主コード
		int ColClWh				= (int)2;	//担当倉庫コード
		int ColItemCd			= (int)3;	//商品コード
		int ColRecomendLoc		= (int)4;	//推奨ロケ
		
		for(int i=0;i<TableCol.length;i++) {
			switch(""+TableCol[i]) {
			 	case "荷主コード":
			 		ColClCd= i;
			 		break;
				case "担当倉庫コード":
					ColClWh= i;
			 		break;
				case "商品コード":
					ColItemCd= i;
			 		break;
				case "推奨ロケ":
					ColRecomendLoc= i;
			 		break;
			 	default:
			 		break;
			}
		}
		int EntryCount = 0;
		for(int i=0;i<CheckOb.length;i++) {
			String GetClCd			= ""+CheckOb[i][ColClCd];			//荷主コード
			String GetClWh			= ""+CheckOb[i][ColClWh];			//担当倉庫コード
			String GetItemCd		= ""+CheckOb[i][ColItemCd];			//商品コード
			String GetRecomendLoc	= ""+CheckOb[i][ColRecomendLoc];	//推奨ロケ
			
			int BackClCd			= (int)0;	//荷主コード
			int BackClWh			= (int)1;	//担当倉庫コード
			int BackItemCd			= (int)2;	//商品コード
			int BackRecomendLoc		= (int)3;	//推奨ロケ
			
			String[] TxtCheck	= TxtCheck(
					GetClCd,
					GetClWh,
					GetItemCd,
					GetRecomendLoc,
					BackClCd,
					BackClWh,
					BackItemCd,
					BackRecomendLoc
					);
			
			GetClCd			= TxtCheck[BackClCd];			//荷主コード
			GetClWh			= TxtCheck[BackClWh];			//担当倉庫コード
			GetItemCd		= TxtCheck[BackItemCd];			//商品コード
			GetRecomendLoc	= TxtCheck[BackRecomendLoc];	//推奨ロケ
			//必須項目のフィールドが全て空白ならSKIP
			boolean SkipFg = false;
			if("".equals(GetClCd)
				&&"".equals(GetClWh)
				&&"".equals(GetItemCd)
				&&"".equals(GetRecomendLoc)
				) {
				SkipFg = true;
			}
			if(!SkipFg){
				EntryCount = EntryCount+1;
			}
		}
		String[] SetClCd			= new String[EntryCount];
		String[] SetClWh			= new String[EntryCount];
		String[] SetItemCd			= new String[EntryCount];
		String[] SetRecomendLoc		= new String[EntryCount];
		
		String[] SetEntryDate		= new String[EntryCount];
		String[] SetUpdateDate		= new String[EntryCount];
		String[] SetEntryUser		= new String[EntryCount];
		String[] SetUpdateUser		= new String[EntryCount];
		
		EntryCount = 0;
		for(int i=0;i<CheckOb.length;i++) {
			String GetClCd			= ""+CheckOb[i][ColClCd];			//荷主コード
			String GetClWh			= ""+CheckOb[i][ColClWh];			//担当倉庫コード
			String GetItemCd		= ""+CheckOb[i][ColItemCd];			//商品コード
			String GetRecomendLoc	= ""+CheckOb[i][ColRecomendLoc];	//推奨ロケ
			
			int BackClCd			= (int)0;	//荷主コード
			int BackClWh			= (int)1;	//担当倉庫コード
			int BackItemCd			= (int)2;	//商品コード
			int BackRecomendLoc		= (int)3;	//推奨ロケ
			
			String[] TxtCheck	= TxtCheck(
					GetClCd,
					GetClWh,
					GetItemCd,
					GetRecomendLoc,
					BackClCd,
					BackClWh,
					BackItemCd,
					BackRecomendLoc
					);
			
			GetClCd			= TxtCheck[BackClCd];			//荷主コード
			GetClWh			= TxtCheck[BackClWh];			//担当倉庫コード
			GetItemCd		= TxtCheck[BackItemCd];			//商品コード
			GetRecomendLoc	= TxtCheck[BackRecomendLoc];	//推奨ロケ
			//必須項目のフィールドが全て空白ならSKIP
			boolean SkipFg = false;
			if("".equals(GetClCd)
				&&"".equals(GetClWh)
				&&"".equals(GetItemCd)
				&&"".equals(GetRecomendLoc)
				) {
				SkipFg = true;
			}
			
			if(!SkipFg){
				SetClCd[EntryCount]			= GetClCd;
				SetClWh[EntryCount]			= GetClWh;
				SetItemCd[EntryCount]		= GetItemCd;
				SetRecomendLoc[EntryCount]	= GetRecomendLoc;
				
				SetEntryDate[EntryCount]	= now_dtm;
				SetUpdateDate[EntryCount]	= now_dtm;
				SetEntryUser[EntryCount]	= "(" + A00000Main.LoginUserId + ")" + A00000Main.LoginUserName;
				SetUpdateUser[EntryCount]	= "(" + A00000Main.LoginUserId + ")" + A00000Main.LoginUserName;
				
				EntryCount = EntryCount+1;
			}
		}
		Object[][] SetOb = {
				 {"ClCd"		,"1","1","Key"	,SetClCd}
				,{"ClWh"		,"1","1","Key"	,SetClWh}
				,{"ItemCd"		,"1","1","Key"	,SetItemCd}
				,{"RecomendLoc"	,"1","1",""		,SetRecomendLoc}
				,{"EntryDate"	,"1","0",""		,SetEntryDate}
				,{"UpdateDate"	,"1","1",""		,SetUpdateDate}
				,{"EntryUser"	,"1","0",""		,SetEntryUser}
				,{"UpdateUser"	,"1","1",""		,SetUpdateUser}
				};
		return SetOb;
	}
	
	private static String[] TxtCheck(
			String GetClCd,
			String GetClWh,
			String GetItemCd,
			String GetRecomendLoc,
			int BackClCd,
			int BackClWh,
			int BackItemCd,
			int BackRecomendLoc
			) {
		String[] Rt = new String[4];
		
		if(null==GetClCd			){GetClCd			= "";}
		if(null==GetClWh			){GetClWh			= "";}
		if(null==GetItemCd			){GetItemCd			= "";}
		if(null==GetRecomendLoc		){GetRecomendLoc	= "";}
		
		GetClCd			= B100TextControl.Trim(GetClCd);
		GetClWh			= B100TextControl.Trim(GetClWh);
		GetItemCd		= B100TextControl.Trim(GetItemCd);
		GetRecomendLoc	= B100TextControl.Trim(GetRecomendLoc);
		
		Rt[BackClCd] 		= GetClCd;
		Rt[BackClWh] 		= GetClWh;
		Rt[BackItemCd] 		= GetItemCd;
		Rt[BackRecomendLoc]	= GetRecomendLoc;
		
		return Rt;
	}
	
	private static Object[][] ItemMstRt(ArrayList<String> SearchItemCd){
		ArrayList<String> SearchClGpCd 				= new ArrayList<String>();	//荷主グループコード
		ArrayList<String> SearchCLItemCd 			= new ArrayList<String>();	//荷主商品コード
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
		boolean AllSearch = false;
		
		SearchClGpCd.add(A00000Main.ClGp);
		
		Object[][] ItemMstRt = M100ItemMstRt.ItemMstRt(
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
		
		return ItemMstRt;
	}
	private static Object[][] LocMstRt(ArrayList<String> SearchRecomendLoc ){
		ArrayList<String> SearchClCd 	= new ArrayList<String>();	//荷主コード
		ArrayList<String> SearchWhCd 	= new ArrayList<String>();	//倉庫コード
		ArrayList<String> SearchLoc 	= SearchRecomendLoc;		//ロケーション
		ArrayList<String> SearchLocName = new ArrayList<String>();	//ロケーション名
		ArrayList<String> SearchType 	= new ArrayList<String>();	//ロケタイプ
		boolean LocExactMatch = true;	//ロケーション完全一致
		boolean AllSearch = false;
		
		SearchClCd.add(A00000Main.ClCd);
		SearchWhCd.add(A00000Main.ClWh);
		
		Object[][] LocationMstRt = M100LocationMstRt.LocationMstRt(
				SearchClCd,		//荷主コード
				SearchWhCd,		//倉庫コード
				SearchLoc,		//ロケーション
				SearchLocName,	//ロケーション名
				SearchType,		//ロケタイプ
				LocExactMatch,	//ロケーション完全一致
				AllSearch);
		return LocationMstRt;
	}
	
	private static void ErrView(ArrayList<String>ErrMsg) {
		//必要フォルダを生成する
		String FLD_PATH = A00000Main.MainFLD+"\\MstControl";
		B100FolderCheck.FLD_CHECK(FLD_PATH);
		FLD_PATH = A00000Main.MainFLD+"\\MstControl\\ItemRecomendLocMst";
		B100FolderCheck.FLD_CHECK(FLD_PATH);
		FLD_PATH = A00000Main.MainFLD+"\\MstControl\\ItemRecomendLocMst\\Err";
		B100FolderCheck.FLD_CHECK(FLD_PATH);
		FLD_PATH = A00000Main.MainFLD+"\\MstControl\\ItemRecomendLocMst\\BK";
		B100FolderCheck.FLD_CHECK(FLD_PATH);
		
		//ファイルに出力
		String NowDTM=B100DateTimeControl.dtmString2(B100DateTimeControl.dtm()[1])[1].replace(" ", "").replace("/", "").replace(":", "");
		
		FLD_PATH = A00000Main.MainFLD+"\\MstControl\\ItemRecomendLocMst\\Err";
		
		String ErrFP = FLD_PATH+"\\ERR"+NowDTM+".txt";
		
		B100TextExport.txt_exp2(ErrMsg, ErrFP,"UTF-8");
		
		//古いエラーデータ削除
		B100FolderCheck.ToolsOldFileDeleteWhereFileName(FLD_PATH ,"ERR",B100DefaultVariable.ErrTxtDelete);
		
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