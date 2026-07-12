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

public class WM100_LocationMst_02_ExcelEntry{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	public static void LocationMstExcelEntry(int x,int y,String TgtFilePath) {
		A00000_Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;

		final JFrame main_fm = B100_FrameParts.FrameCreate(x,y,600,200,"Corgi00ロケーションマスタ登録（エクセル）","");
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
				LocationMstExcelEntryMain(0,0,TgtFilePath,SheetName);
			}
		});
		
		//EXITボタン押下時の挙動
		exit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				WM100_LocationMst_00_Search.LocationMstSearch(0, 0);
			}
		});
	}
	
	public static void LocationMstExcelEntryMain(int x,int y,String TgtFilePath,String SheetName) {
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
		
		String[] Title = B100_RtObjectCreate.RtTitleName(M100_LocationMstRt.RtLocationMstRt());
		
		Object[][] NeedCol = {
				 {Title[M100_LocationMstRt.ColClCd]			, 1	, 0}
				,{Title[M100_LocationMstRt.ColCLName01]		, 1	, 1}
				,{Title[M100_LocationMstRt.ColWhCd]			, 1	, 2}
				,{Title[M100_LocationMstRt.ColWHName]			, 1	, 3}
				,{Title[M100_LocationMstRt.ColLoc]				, 1	, 4}
				,{Title[M100_LocationMstRt.ColLocName]			, 1	, 5}
				,{Title[M100_LocationMstRt.ColType]			, 0	, 6}
				};	//フィールド名,フィールドタイプ(0:数値 1:文字列 2:日付時刻),基本のカラム(ゼロスタート),基本のカラム位置※カラム位置は後で読み込んだエクセルの1行目でフィールド名比較して更新されます
		
		JLabel LB_SheetList	= B100_FrameParts.JLabelSet(	10, 40,540,20,"以下のデータを登録しようとしています",11,0);
		main_fm.add(LB_SheetList);
		
		String[] columnNames01 = new String[NeedCol.length+1];
		columnNames01[0] = "Fg";
		for(int i=0;i<NeedCol.length;i++) {columnNames01[i+1] = (String)NeedCol[i][0];}
		
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
			LocationMstExcelEntry(0,0,TgtFilePath);
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
					MainFmTableModel.addRow(SetOb);
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
				WM100_LocationMst_00_Search.LocationMstSearch(0, 0);
			}else {
				main_fm.setVisible(true);
			}
		}
		RenewFg = true;
		
		//登録ボタン押下時の挙動
		entry_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				String[] TableCol = B100_TableControl.TableFieldNameRt(tb01);
				int RowCount = MainFmTableModel.getRowCount();
				Object[][] CheckOb = new Object[RowCount][TableCol.length];
				for(int i=0;i<RowCount;i++) {
					for(int i01=0;i01<TableCol.length;i01++) {
						CheckOb[i][i01] = ""+MainFmTableModel.getValueAt(i, i01);
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
					WM100_LocationMst_00_Search.LocationMstSearch(0, 0);
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
				WM100_LocationMst_00_Search.LocationMstSearch(0, 0);
			}
		});
	}
	
	private static void MstEntry(Object[][] CheckOb,String[] TableCol) {
		int ColClCd		= 1;		//荷主コード
		int ColClName	= 2;		//荷主名
		int ColWhCd		= 3;		//倉庫コード
		int ColWhName	= 4;		//倉庫名
		int ColLoc		= 5;		//ロケーション
		int ColLocName	= 6;		//ロケーション名
		int ColType		= 7;		//ロケタイプ
		
		String[] Title = B100_RtObjectCreate.RtTitleName(M100_LocationMstRt.RtLocationMstRt());
		
		for(int i=0;i<TableCol.length;i++) {
			if(TableCol[i].equals(Title[M100_LocationMstRt.ColClCd]		)){ColClCd		= i;}
			if(TableCol[i].equals(Title[M100_LocationMstRt.ColCLName01]	)){ColClName	= i;}
			if(TableCol[i].equals(Title[M100_LocationMstRt.ColWhCd]		)){ColWhCd		= i;}
			if(TableCol[i].equals(Title[M100_LocationMstRt.ColWHName]		)){ColWhName	= i;}
			if(TableCol[i].equals(Title[M100_LocationMstRt.ColLoc]			)){ColLoc		= i;}
			if(TableCol[i].equals(Title[M100_LocationMstRt.ColLocName]		)){ColLocName	= i;}
			if(TableCol[i].equals(Title[M100_LocationMstRt.ColType]		)){ColType		= i;}
		}
		int EntryCount = 0;
		for(int i=0;i<CheckOb.length;i++) {
			String GetClCd		= ""+CheckOb[i][ColClCd];		//荷主コード
			String GetClName	= ""+CheckOb[i][ColClName];		//荷主名
			String GetWhCd		= ""+CheckOb[i][ColWhCd];		//倉庫コード
			String GetWhName	= ""+CheckOb[i][ColWhName];		//倉庫名
			String GetLoc		= ""+CheckOb[i][ColLoc];		//ロケーション
			String GetLocName	= ""+CheckOb[i][ColLocName];	//ロケーション名
			String GetType		= ""+CheckOb[i][ColType];		//ロケタイプ
			
			GetClCd		= B100_TextControl.Trim(GetClCd);		//荷主コード
			GetClName	= B100_TextControl.Trim(GetClName);	//荷主名
			GetWhCd		= B100_TextControl.Trim(GetWhCd);		//倉庫コード
			GetWhName	= B100_TextControl.Trim(GetWhName);	//倉庫名
			GetLoc		= B100_TextControl.Trim(GetLoc);		//ロケーション
			GetLocName	= B100_TextControl.Trim(GetLocName);	//ロケーション名
			GetType		= B100_TextControl.Trim(GetType);		//ロケタイプ
			
			if("".equals(GetType)) {GetType = "0";}
			float WFT = Float.parseFloat(GetType);
			GetType = ""+(int)WFT;
			//数値項目以外のフィールドが全て空白ならSKIP
			boolean SkipFg = false;
			if(	"".equals(GetClCd)				//荷主コード
					&&"".equals(GetClName)		//荷主名
					&&"".equals(GetWhCd)		//倉庫コード
					&&"".equals(GetWhName)		//倉庫名
					&&"".equals(GetLoc)			//ロケーション
					&&"".equals(GetLocName)		//ロケーション名
					) {
				SkipFg = true;
			}
			if(!SkipFg) {
				EntryCount = EntryCount+1;
			}
		}
		String[][] RenewLoc = new String[EntryCount][5];
		EntryCount = 0;
		for(int i=0;i<CheckOb.length;i++) {
			String GetClCd		= ""+CheckOb[i][ColClCd];		//荷主コード
			String GetClName	= ""+CheckOb[i][ColClName];		//荷主名
			String GetWhCd		= ""+CheckOb[i][ColWhCd];		//倉庫コード
			String GetWhName	= ""+CheckOb[i][ColWhName];		//倉庫名
			String GetLoc		= ""+CheckOb[i][ColLoc];		//ロケーション
			String GetLocName	= ""+CheckOb[i][ColLocName];	//ロケーション名
			String GetType		= ""+CheckOb[i][ColType];		//ロケタイプ
			
			GetClCd		= B100_TextControl.Trim(GetClCd);		//荷主コード
			GetClName	= B100_TextControl.Trim(GetClName);	//荷主名
			GetWhCd		= B100_TextControl.Trim(GetWhCd);		//倉庫コード
			GetWhName	= B100_TextControl.Trim(GetWhName);	//倉庫名
			GetLoc		= B100_TextControl.Trim(GetLoc);		//ロケーション
			GetLocName	= B100_TextControl.Trim(GetLocName);	//ロケーション名
			GetType		= B100_TextControl.Trim(GetType);		//ロケタイプ
			
			if("".equals(GetType)) {GetType = "0";}
			float WFT = Float.parseFloat(GetType);
			GetType = ""+(int)WFT;
			//数値項目以外のフィールドが全て空白ならSKIP
			boolean SkipFg = false;
			if(	"".equals(GetClCd)				//荷主コード
					&&"".equals(GetClName)		//荷主名
					&&"".equals(GetWhCd)		//倉庫コード
					&&"".equals(GetWhName)		//倉庫名
					&&"".equals(GetLoc)			//ロケーション
					&&"".equals(GetLocName)		//ロケーション名
					) {
				SkipFg = true;
			}
			if(!SkipFg) {
				if("".equals(GetLocName)) {GetLocName  = GetLoc;}
				RenewLoc[EntryCount][0] = GetClCd;
				RenewLoc[EntryCount][1] = GetWhCd;
				RenewLoc[EntryCount][2] = GetLoc;
				RenewLoc[EntryCount][3] = GetType;
				RenewLoc[EntryCount][4] = GetLocName;
				EntryCount = EntryCount+1;
			}
		}
		WM100_LocationMst_03_RenewStockControl.LocationMstRenewStockControl(RenewLoc);
	}
	
	private static ArrayList<String> ErrCheck(Object[][] CheckOb,String[] TableCol){
		ArrayList<String> ErrMsg = new ArrayList<String>();
		
		int ColClCd		= 1;		//荷主コード
		int ColClName	= 2;		//荷主名
		int ColWhCd		= 3;		//倉庫コード
		int ColWhName	= 4;		//倉庫名
		int ColLoc		= 5;		//ロケーション
		int ColLocName	= 6;		//ロケーション名
		int ColType		= 7;		//ロケタイプ
		
		String[] Title = B100_RtObjectCreate.RtTitleName(M100_LocationMstRt.RtLocationMstRt());
		
		for(int i=0;i<TableCol.length;i++) {
			if(TableCol[i].equals(Title[M100_LocationMstRt.ColClCd]		)){ColClCd		= i;}
			if(TableCol[i].equals(Title[M100_LocationMstRt.ColCLName01]	)){ColClName	= i;}
			if(TableCol[i].equals(Title[M100_LocationMstRt.ColWhCd]		)){ColWhCd		= i;}
			if(TableCol[i].equals(Title[M100_LocationMstRt.ColWHName]		)){ColWhName	= i;}
			if(TableCol[i].equals(Title[M100_LocationMstRt.ColLoc]			)){ColLoc		= i;}
			if(TableCol[i].equals(Title[M100_LocationMstRt.ColLocName]		)){ColLocName	= i;}
			if(TableCol[i].equals(Title[M100_LocationMstRt.ColType]		)){ColType		= i;}
		}
		          
		//現在選択されている荷主・倉庫以外への設定不可
		for(int i=0;i<CheckOb.length;i++) {
			String GetClCd		= ""+CheckOb[i][ColClCd];		//荷主コード
			String GetClName	= ""+CheckOb[i][ColClName];		//荷主名
			String GetWhCd		= ""+CheckOb[i][ColWhCd];		//倉庫コード
			String GetWhName	= ""+CheckOb[i][ColWhName];		//倉庫名
			String GetLoc		= ""+CheckOb[i][ColLoc];		//ロケーション
			String GetLocName	= ""+CheckOb[i][ColLocName];	//ロケーション名
			String GetType		= ""+CheckOb[i][ColType];		//ロケタイプ
			
			GetClCd		= B100_TextControl.Trim(GetClCd);		//荷主コード
			GetClName	= B100_TextControl.Trim(GetClName);	//荷主名
			GetWhCd		= B100_TextControl.Trim(GetWhCd);		//倉庫コード
			GetWhName	= B100_TextControl.Trim(GetWhName);	//倉庫名
			GetLoc		= B100_TextControl.Trim(GetLoc);		//ロケーション
			GetLocName	= B100_TextControl.Trim(GetLocName);	//ロケーション名
			GetType		= B100_TextControl.Trim(GetType);		//ロケタイプ
			
			if("".equals(GetType)) {GetType = "0";}
			float WFT = Float.parseFloat(GetType);
			GetType = ""+(int)WFT;
			
			//数値項目以外のフィールドが全て空白ならSKIP
			boolean SkipFg = false;
			if(	"".equals(GetClCd)				//荷主コード
					&&"".equals(GetClName)		//荷主名
					&&"".equals(GetWhCd)		//倉庫コード
					&&"".equals(GetWhName)		//倉庫名
					&&"".equals(GetLoc)			//ロケーション
					&&"".equals(GetLocName)		//ロケーション名
					) {
				SkipFg = true;
			}
			if(!SkipFg) {
				if(!A00000_Main.ClCd.equals(GetClCd)) {
					int wint = i+1;
					ErrMsg.add(wint+"行目エラー：("+GetClCd+")は現在作業中の荷主コードではありません別荷主の設定を投入しようとしていませんか？("+A00000_Main.ClCd+")を設定してください。");
				}
				if(!A00000_Main.ClWh.equals(GetWhCd)) {
					int wint = i+1;
					ErrMsg.add(wint+"行目エラー：("+GetWhCd+")は現在作業中の倉庫コードではありません別荷主の設定を投入しようとしていませんか？("+A00000_Main.ClWh+")を設定してください。");
				}
				if("".equals(GetLoc)) {
					int wint = i+1;
					ErrMsg.add(wint+"行目エラー：ロケーションコードが未定義です");
				}
				boolean UnHitFg = true;
				for(int i01=0;i01<B100_DefaultVariable.SearchLocType[1].length;i01++) {
					if((B100_DefaultVariable.SearchLocType[1][i01]).equals(GetType)) {
						UnHitFg = false;
					}
				}
				if(UnHitFg) {
					int wint = i+1;
					ErrMsg.add(wint+"行目エラー：("+GetType+")は不正なロケーションタイプです");
				}
			}
		}
		
		return ErrMsg;
	}
	
	private static void ErrView(ArrayList<String>ErrMsg) {
		//必要フォルダを生成する
		String FLD_PATH = A00000_Main.MainFLD+"\\MstControl";
		B100_FolderCheck.FLD_CHECK(FLD_PATH);
		FLD_PATH = A00000_Main.MainFLD+"\\MstControl\\LocationMst";
		B100_FolderCheck.FLD_CHECK(FLD_PATH);
		FLD_PATH = A00000_Main.MainFLD+"\\MstControl\\LocationMst\\Err";
		B100_FolderCheck.FLD_CHECK(FLD_PATH);
		FLD_PATH = A00000_Main.MainFLD+"\\MstControl\\LocationMst\\BK";
		B100_FolderCheck.FLD_CHECK(FLD_PATH);
		
		//ファイルに出力
		String NowDTM=B100_DateTimeControl.dtmString2(B100_DateTimeControl.dtm()[1])[1].replace(" ", "").replace("/", "").replace(":", "");
		
		FLD_PATH = A00000_Main.MainFLD+"\\MstControl\\LocationMst\\Err";
		
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