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

public class WM00032UserMstExcelEntry{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	public static void UserMstExcelEntry(int x,int y,String TgtFilePath) {
		A00000Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;

		final JFrame main_fm = B00110FrameParts.FrameCreate(x,y,600,200,"Corgi00ユーザーマスタ登録（エクセル）","");
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
				UserMstExcelEntryMain(0,0,TgtFilePath,SheetName);
			}
		});
		
		//EXITボタン押下時の挙動
		exit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				WM00030UserMstSearch.UserMstSearch(0, 0);
			}
		});
	}
	
	private static void UserMstExcelEntryMain(int x,int y,String TgtFilePath,String SheetName) {
		A00000Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		
		final JFrame main_fm = B00110FrameParts.FrameCreate(x,y,750,800,"Corgi00ユーザーマスタ登録（エクセル）","");
		JLabel userinfo = B00110FrameParts.UserInfo();
		JButton exit_btn = B00110FrameParts.ExitBtn();
		JButton entry_btn = B00110FrameParts.EntryBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		
		
		String[] NeedCol = {
				 "倉庫CD"
				,"運送会社CD"
				,"ユーザーCD"
				,"ユーザー名1"
				,"ユーザー名2"
				,"ユーザー名3"
				,"権限区分"
				,"標準車輛CD"
				,"郵便番号"
				,"住所1"
				,"住所2"
				,"住所3"
				,"電話番号"
				,"FAX"
				,"メールアドレス"
				,"コメント1"
				,"コメント2"
				,"コメント3"
				,"基幹システムユーザーコード"
				,"削除区分"
				,"主要担当荷主CD"
				,"パスワード"
		};
		int[] TgtCol = {
				 -1	//倉庫CD
				,-1	//運送会社CD
				,-1	//ユーザーCD
				,-1	//ユーザー名1
				,-1	//ユーザー名2
				,-1	//ユーザー名3
				,-1	//権限区分
				,-1	//標準車輛CD
				,-1	//郵便番号
				,-1	//住所1
				,-1	//住所2
				,-1	//住所3
				,-1	//電話番号
				,-1	//FAX
				,-1	//メールアドレス
				,-1	//コメント1
				,-1	//コメント2
				,-1	//コメント3
				,-1	//基幹システムユーザーコード
				,-1	//削除区分
				,-1	//主要担当荷主CD
				,-1	//パスワード
				};
		
		JLabel LB_SheetList	= B00110FrameParts.JLabelSet(	10, 40,600,20,"以下のデータを登録しようとしています",11,0);
		main_fm.add(LB_SheetList);
		
		String[] columnNames01 = {
				 "FG"
				,"倉庫CD"
				,"運送会社CD"
				,"ユーザーCD"
				,"倉庫名"
				,"運送会社名"
				,"ユーザー名1"
				,"ユーザー名2"
				,"ユーザー名3"
				,"権限区分"
				,"標準車輛CD"
				,"車両名称01"
				,"車両名称02"
				,"車両名称03"
				,"郵便番号"
				,"住所1"
				,"住所2"
				,"住所3"
				,"電話番号"
				,"FAX"
				,"メールアドレス"
				,"コメント1"
				,"コメント2"
				,"コメント3"
				,"基幹システムユーザーコード"
				,"削除区分"
				,"主要担当荷主CD"
				,"主要担当荷主名"
				,"パスワード"
		};
		
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
		column = columnModel01.getColumn( 1);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//倉庫CD
		column = columnModel01.getColumn( 2);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//運送会社CD
		column = columnModel01.getColumn( 3);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//ユーザーCD
		column = columnModel01.getColumn( 4);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//倉庫名
		column = columnModel01.getColumn( 5);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//運送会社名
		column = columnModel01.getColumn( 6);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//ユーザー名1
		column = columnModel01.getColumn( 7);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//ユーザー名2
		column = columnModel01.getColumn( 8);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//ユーザー名3
		column = columnModel01.getColumn( 9);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//権限区分
		column = columnModel01.getColumn(10);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//標準車輛CD
		column = columnModel01.getColumn(11);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//車両名称01
		column = columnModel01.getColumn(12);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//車両名称02
		column = columnModel01.getColumn(13);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//車両名称03
		column = columnModel01.getColumn(14);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//郵便番号
		column = columnModel01.getColumn(15);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//住所1
		column = columnModel01.getColumn(16);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//住所2
		column = columnModel01.getColumn(17);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//住所3
		column = columnModel01.getColumn(18);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//電話番号
		column = columnModel01.getColumn(19);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//FAX
		column = columnModel01.getColumn(20);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//メールアドレス
		column = columnModel01.getColumn(21);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//コメント1
		column = columnModel01.getColumn(22);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//コメント2
		column = columnModel01.getColumn(23);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//コメント3
		column = columnModel01.getColumn(24);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//基幹システムユーザーコード
		column = columnModel01.getColumn(25);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//削除区分
		column = columnModel01.getColumn(26);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//主要担当荷主CD
		column = columnModel01.getColumn(27);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//主要担当荷主名
		column = columnModel01.getColumn(28);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//パスワード
		
		//スクロール用設定
		JScrollPane scpn01 = B00110FrameParts.JScrollPaneSet(10,65,700,600,tb01);
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
			JOptionPane.showMessageDialog(null, "ヘッダ行で取込ファイルのレイアウト判別ができませんでした。\n確認しやがれください\n"
												 	+"倉庫CD"
													+",運送会社CD"
													+",ユーザーCD"
													+",ユーザー名1"
													+",ユーザー名2"
													+",ユーザー名3"
													+",権限区分"
													+",標準車輛CD"
													+",郵便番号"
													+",住所1"
													+",住所2"
													+",住所3"
													+",電話番号"
													+",FAX"
													+",メールアドレス"
													+",コメント1"
													+",コメント2"
													+",コメント3"
													+",基幹システムユーザーコード"
													+",削除区分"
													+",主要担当荷主CD"
													+",パスワード\n"
													+"がヘッダに必要です");
			UserMstExcelEntry(0,0,TgtFilePath);
		}else {
			int[] ClmnType = new int[HeaderRead[0].length];
			for(int i=0;i<ClmnType.length;i++) {ClmnType[i]=1;}

			ClmnType[TgtCol[ 0]]=1;	//倉庫CD
			ClmnType[TgtCol[ 1]]=1;	//運送会社CD
			ClmnType[TgtCol[ 2]]=1;	//ユーザーCD
			ClmnType[TgtCol[ 3]]=1;	//ユーザー名1
			ClmnType[TgtCol[ 4]]=1;	//ユーザー名2
			ClmnType[TgtCol[ 5]]=1;	//ユーザー名3
			ClmnType[TgtCol[ 6]]=1;	//権限区分
			ClmnType[TgtCol[ 7]]=1;	//標準車輛CD
			ClmnType[TgtCol[ 8]]=1;	//郵便番号
			ClmnType[TgtCol[ 9]]=1;	//住所1
			ClmnType[TgtCol[10]]=1;	//住所2
			ClmnType[TgtCol[11]]=1;	//住所3
			ClmnType[TgtCol[12]]=1;	//電話番号
			ClmnType[TgtCol[13]]=1;	//FAX
			ClmnType[TgtCol[14]]=1;	//メールアドレス
			ClmnType[TgtCol[15]]=1;	//コメント1
			ClmnType[TgtCol[16]]=1;	//コメント2
			ClmnType[TgtCol[17]]=1;	//コメント3
			ClmnType[TgtCol[18]]=1;	//基幹システムユーザーコード
			ClmnType[TgtCol[19]]=1;	//削除区分
			ClmnType[TgtCol[20]]=1;	//主要担当荷主CD
			ClmnType[TgtCol[21]]=1;	//パスワード
			
			Object[][] ExcellRead = B00060ToolsExcellControl.ExcellRead(TgtFilePath,SheetName,ClmnType,true);
			
			if(0<ExcellRead.length&&ClmnType.length<=ExcellRead[0].length) {
				//倉庫マスタ
				//運送会社マスタ
				//車両マスタ
				//荷主マスタ
				//コードアラート
				
				ArrayList<String> TgtWHCD = new ArrayList<String>();
				ArrayList<String> TgtShippingCompanyCd = new ArrayList<String>();
				ArrayList<String> TgtCarCd = new ArrayList<String>();
				ArrayList<String> TgthCLCD = new ArrayList<String>();
				
				for(int i=0;i<ExcellRead.length;i++) {
					for(int i01=0;i01<ExcellRead[i].length;i01++) {
						if(null==ExcellRead[i][i01]) {ExcellRead[i][i01]="";}
						ExcellRead[i][i01] = B00020ToolsTextControl.Trim(""+ExcellRead[i][i01]);
					}
					if(!"".equals(""+ExcellRead[i][TgtCol[ 0]])) {TgtWHCD.add(				""+ExcellRead[i][TgtCol[ 0]]);}
					if(!"".equals(""+ExcellRead[i][TgtCol[ 1]])) {TgtShippingCompanyCd.add(	""+ExcellRead[i][TgtCol[ 1]]);}
					if(!"".equals(""+ExcellRead[i][TgtCol[ 7]])) {TgtCarCd.add(				""+ExcellRead[i][TgtCol[ 7]]);}
					if(!"".equals(""+ExcellRead[i][TgtCol[20]])) {TgthCLCD.add(				""+ExcellRead[i][TgtCol[20]]);}
				}
				
				Object[][] ShippingCompanyMstRt = ShippingCompanyMstRt(TgtShippingCompanyCd);
				Object[][] CarMstRt = CarMstRt(TgtCarCd);
				Object[][] ClMstRt = ClMstRt(TgthCLCD);
				Object[][] WhMstRt = WhMstRt(TgtWHCD);
				ArrayList<String> ErrMsg = new ArrayList<String>();
				
				for(int i=0;i<ExcellRead.length;i++) {
					if(!"".equals(""+ExcellRead[i][TgtCol[ 0]])||!"".equals(""+ExcellRead[i][TgtCol[ 1]])||!"".equals(""+ExcellRead[i][TgtCol[ 2]])||!"".equals(""+ExcellRead[i][TgtCol[ 3]])) {
						Object[] SetOb = new Object[29];
						SetOb[ 0] = false;						
						SetOb[ 1] = ""+ExcellRead[i][TgtCol[ 0]];	//倉庫CD
						SetOb[ 2] = ""+ExcellRead[i][TgtCol[ 1]];	//運送会社CD
						SetOb[ 3] = ""+ExcellRead[i][TgtCol[ 2]];	//ユーザーCD
						SetOb[ 4] = "";	//倉庫名
						SetOb[ 5] = "";	//運送会社名
						SetOb[ 6] = ""+ExcellRead[i][TgtCol[ 3]];	//ユーザー名1
						SetOb[ 7] = ""+ExcellRead[i][TgtCol[ 4]];	//ユーザー名2
						SetOb[ 8] = ""+ExcellRead[i][TgtCol[ 5]];	//ユーザー名3
						SetOb[ 9] = ""+ExcellRead[i][TgtCol[ 6]];	//権限区分
						SetOb[10] = ""+ExcellRead[i][TgtCol[ 7]];	//標準車輛CD
						SetOb[11] = "";	//車両名称01
						SetOb[12] = "";	//車両名称02
						SetOb[13] = "";	//車両名称03
						SetOb[14] = ""+ExcellRead[i][TgtCol[ 8]];	//郵便番号
						SetOb[15] = ""+ExcellRead[i][TgtCol[ 9]];	//住所1
						SetOb[16] = ""+ExcellRead[i][TgtCol[10]];	//住所2
						SetOb[17] = ""+ExcellRead[i][TgtCol[11]];	//住所3
						SetOb[18] = ""+ExcellRead[i][TgtCol[12]];	//電話番号
						SetOb[19] = ""+ExcellRead[i][TgtCol[13]];	//FAX
						SetOb[20] = ""+ExcellRead[i][TgtCol[14]];	//メールアドレス
						SetOb[21] = ""+ExcellRead[i][TgtCol[15]];	//コメント1
						SetOb[22] = ""+ExcellRead[i][TgtCol[16]];	//コメント2
						SetOb[23] = ""+ExcellRead[i][TgtCol[17]];	//コメント3
						SetOb[24] = ""+ExcellRead[i][TgtCol[18]];	///基幹システムユーザーコード
						SetOb[25] = ""+ExcellRead[i][TgtCol[19]];	//削除区分
						SetOb[26] = ""+ExcellRead[i][TgtCol[20]];	//主要担当荷主CD
						SetOb[27] = "";	//主要担当荷主名
						SetOb[28] = ""+ExcellRead[i][TgtCol[21]];	//パスワード
						
						for(int i01=1;i01<SetOb.length;i01++) {
							SetOb[i01] = B00020ToolsTextControl.Trim(""+SetOb[i01]);
						}
						SetOb[ 9] = B00020ToolsTextControl.num_only_String(""+SetOb[ 9]);	//権限区分
						SetOb[25] = B00020ToolsTextControl.num_only_String(""+SetOb[25]);	//削除区分
						SetOb[14] = B00020ToolsTextControl.num_only_String(""+SetOb[14]);	//郵便番号
						SetOb[18] = B00020ToolsTextControl.num_only_String(""+SetOb[18]);	//電話番号
						SetOb[19] = B00020ToolsTextControl.num_only_String(""+SetOb[19]);	//FAX
						
						if("".equals(""+SetOb[ 9])) {	//権限区分
							SetOb[ 9] = "0";
						}
						if("".equals(""+SetOb[25])) {	//削除区分
							SetOb[25] = "0";
						}
						
						for(int i01=0;i01<B00100DefaultVariable.AuthorityFG[1].length;i01++) {
							boolean UnHitFg = true;
							if(B00100DefaultVariable.AuthorityFG[1][i01].equals(""+SetOb[9])) {
								UnHitFg = false;
							}
							if(UnHitFg) {
								SetOb[ 9] = "0";
							}
						}
						
						for(int i01=0;i01<B00100DefaultVariable.DelList[1].length;i01++) {
							boolean UnHitFg = true;
							if(B00100DefaultVariable.DelList[1][i01].equals(""+SetOb[25])) {
								UnHitFg = false;
							}
							if(UnHitFg) {
								SetOb[25] = "0";
							}
						}
						
						boolean UnHitFg = true;
						int wint = i+1;
						if(!"".equals(""+ExcellRead[i][TgtCol[ 0]])) {
							UnHitFg = true;
							for(int i01=0;i01<WhMstRt.length;i01++) {
								if((""+ExcellRead[i][TgtCol[ 0]]).equals(""+WhMstRt[i01][0])) {
									SetOb[ 4] = ""+WhMstRt[i01][1];	//倉庫名
									UnHitFg = false;
								}
							}
							if(UnHitFg) {
								ErrMsg.add(wint+"行目エラー:("+ExcellRead[i][TgtCol[ 0]]+")は未登録の倉庫コードです");
							}
						}else {
							ErrMsg.add(wint+"行目エラー:所属倉庫コードは必須です");
						}
						
						if(!"".equals(""+ExcellRead[i][TgtCol[ 1]])) {
							UnHitFg = true;
							for(int i01=0;i01<ShippingCompanyMstRt.length;i01++) {
								if((""+ExcellRead[i][TgtCol[ 1]]).equals(""+ShippingCompanyMstRt[i01][0])) {
									SetOb[ 5] = ""+ShippingCompanyMstRt[i01][1];	//運送会社名
									UnHitFg = false;
								}
							}
							if(UnHitFg) {
								ErrMsg.add(wint+"行目エラー:("+ExcellRead[i][TgtCol[ 1]]+")は未登録の運送会社コードです");
							}
						}else {
							ErrMsg.add(wint+"行目エラー:所属運送会社コードは必須です");
						}
						
						if(!"".equals(""+ExcellRead[i][TgtCol[ 2]])) {
							
						}else {
							ErrMsg.add(wint+"行目エラー:ユーザーコードは必須です");
						}
						
						if(!"".equals(""+ExcellRead[i][TgtCol[ 3]])) {
							
						}else {
							ErrMsg.add(wint+"行目エラー:ユーザー名1は必須です");
						}
						
						if(!"".equals(""+ExcellRead[i][TgtCol[ 7]])) {
							UnHitFg = true;
							for(int i01=0;i01<CarMstRt.length;i01++) {
								if((""+ExcellRead[i][TgtCol[ 0]]).equals(""+CarMstRt[i01][0])
									&& (""+ExcellRead[i][TgtCol[ 1]]).equals(""+CarMstRt[i01][1])
									&& (""+ExcellRead[i][TgtCol[ 7]]).equals(""+CarMstRt[i01][5])
									) {
									SetOb[11] = ""+CarMstRt[i01][6];	//車両名称01
									SetOb[12] = ""+CarMstRt[i01][7];	//車両名称02
									SetOb[13] = ""+CarMstRt[i01][8];	//車両名称03
									UnHitFg = false;
								}
							}
							if(UnHitFg) {
								ErrMsg.add(wint+"行目エラー:("+ExcellRead[i][TgtCol[ 7]]+")は未登録の車輛コードです");
							}
						}
						
						if(!"".equals(""+ExcellRead[i][TgtCol[20]])) {
							UnHitFg = true;
							for(int i01=0;i01<ClMstRt.length;i01++) {
								if((""+ExcellRead[i][TgtCol[20]]).equals(""+ClMstRt[i01][0])) {
									SetOb[27] = ""+ClMstRt[i01][5];	//主要担当荷主名
									UnHitFg = false;
								}
							}
							if(UnHitFg) {
								ErrMsg.add(wint+"行目エラー:("+ExcellRead[i][TgtCol[20]]+")は未登録の荷主コードです");
							}
						}
						tableModel_ms01.addRow(SetOb);
					}
				}
				if(null==ErrMsg||0==ErrMsg.size()) {
					main_fm.add(entry_btn);
				}else {
					//必要フォルダを生成する
					String FLD_PATH = A00000Main.MainFLD+"\\MstControl";
					B00040ToolsFolderCheck.FLD_CHECK(FLD_PATH);
					FLD_PATH = A00000Main.MainFLD+"\\MstControl\\UserMst";
					B00040ToolsFolderCheck.FLD_CHECK(FLD_PATH);
					FLD_PATH = A00000Main.MainFLD+"\\MstControl\\UserMst\\Err";
					B00040ToolsFolderCheck.FLD_CHECK(FLD_PATH);
					FLD_PATH = A00000Main.MainFLD+"\\MstControl\\UserMst\\BK";
					B00040ToolsFolderCheck.FLD_CHECK(FLD_PATH);
					
					//ファイルに出力
					
					String NowDTM=B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[1].replace(" ", "").replace("/", "").replace(":", "");
					
					FLD_PATH = A00000Main.MainFLD+"\\MstControl\\UserMst\\Err";
					
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
			main_fm.setVisible(true);
		}
		//登録ボタン押下時の挙動
		entry_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				int RowCount = tableModel_ms01.getRowCount();
				if(RenewFg&&0<RowCount) {
					RenewFg = false;
					String[][] SetString = {
							{"WHCD"					,"1","1"}	//倉庫コード
							,{"ShippingCompanyCd"	,"1","1"}	//運送会社CD
							,{"UserCd"				,"1","1"}	//ユーザーCD
							,{"PassWord"			,"1","1"}	//パスワード
							,{"AuthorityFG"			,"1","1"}	//権限区分
							,{"CarCd"				,"1","1"}	//標準車輛CD
							,{"UserName01"			,"1","1"}	//ユーザー名1
							,{"UserName02"			,"1","1"}	//ユーザー名2
							,{"UserName03"			,"1","1"}	//ユーザー名3
							,{"Post"				,"1","1"}	//郵便番号
							,{"Add01"				,"1","1"}	//住所1
							,{"Add02"				,"1","1"}	//住所2
							,{"Add03"				,"1","1"}	//住所3
							,{"Tel"					,"1","1"}	//電話番号
							,{"Fax"					,"1","1"}	//FAX
							,{"Mail"				,"1","1"}	//メールアドレス
							,{"Com01"				,"1","1"}	//コメント1
							,{"Com02"				,"1","1"}	//コメント2
							,{"Com03"				,"1","1"}	//コメント3
							,{"EntryDate"			,"1","0"}	//データ登録日時
							,{"UpdateDate"			,"1","1"}	//データ更新日時
							,{"EntryUser"			,"1","0"}	//登録者コード
							,{"UpdateUser"			,"1","1"}	//更新者コード
							,{"PTMSCD"				,"1","1"}	//基幹システムユーザーコード
							,{"DelFg"				,"1","1"}	//削除区分
							,{"MainClient"			,"1","1"}	//主要担当荷主CD
					};
					
					boolean PassWordRenewFg = true;
					//一件でもパスワード********※検索のままがあればパスワード更新しない
					for(int i=0;i<RowCount;i++) {
						if("********".equals(""+tableModel_ms01.getValueAt(i, 28))) {
							PassWordRenewFg = false;
						}
					}
					if(PassWordRenewFg) {
						SetString[3][1] = "1";
						SetString[3][2] = "1";
					}else {
						SetString[3][1] = "0";
						SetString[3][2] = "0";
					}
					
					String now_dtm = B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[1];
					
					String tgt_table = "KM0020_USERMST";
					String[][] field_name = new String[SetString.length][3];
					String[][] entry_data = new String[RowCount][SetString.length];
					String[] judg_field = new String[3];
					String[][] judg_data = new String[RowCount][3];
					String TgtDB = "NYANKO";
					int non_msg_fg = 0;
	
					judg_field[0] = "WHCD";					//倉庫コード
					judg_field[1] = "ShippingCompanyCd";	//運送会社CD
					judg_field[2] = "UserCd";				//ユーザーCD
					
					
					for(int i=0;i<SetString.length;i++) {
						field_name[i][0] = SetString[i][0];
						field_name[i][1] = SetString[i][1];
						field_name[i][2] = SetString[i][2];
					}
					for(int i=0;i<RowCount;i++) {
						judg_data[i][0] = ""+tableModel_ms01.getValueAt(i,  1);	//倉庫コード
						judg_data[i][1] = ""+tableModel_ms01.getValueAt(i,  2);	//運送会社CD
						judg_data[i][2] = ""+tableModel_ms01.getValueAt(i,  3);	//ユーザーCD
						
						entry_data[i][ 0] = ""+tableModel_ms01.getValueAt(i,  1);	//倉庫コード
						entry_data[i][ 1] = ""+tableModel_ms01.getValueAt(i,  2);	//運送会社CD
						entry_data[i][ 2] = ""+tableModel_ms01.getValueAt(i,  3);	//ユーザーCD
						entry_data[i][ 3] = ""+tableModel_ms01.getValueAt(i, 28);	//パスワード
						entry_data[i][ 4] = ""+tableModel_ms01.getValueAt(i,  9);	//権限区分
						entry_data[i][ 5] = ""+tableModel_ms01.getValueAt(i, 10);	//標準車輛CD
						entry_data[i][ 6] = ""+tableModel_ms01.getValueAt(i,  6);	//ユーザー名1
						entry_data[i][ 7] = ""+tableModel_ms01.getValueAt(i,  7);	//ユーザー名2
						entry_data[i][ 8] = ""+tableModel_ms01.getValueAt(i,  8);	//ユーザー名3
						entry_data[i][ 9] = ""+tableModel_ms01.getValueAt(i, 14);	//郵便番号
						entry_data[i][10] = ""+tableModel_ms01.getValueAt(i, 15);	//住所1
						entry_data[i][11] = ""+tableModel_ms01.getValueAt(i, 16);	//住所2
						entry_data[i][12] = ""+tableModel_ms01.getValueAt(i, 17);	//住所3
						entry_data[i][13] = ""+tableModel_ms01.getValueAt(i, 18);	//電話番号
						entry_data[i][14] = ""+tableModel_ms01.getValueAt(i, 19);	//FAX
						entry_data[i][15] = ""+tableModel_ms01.getValueAt(i, 20);	//メールアドレス
						entry_data[i][16] = ""+tableModel_ms01.getValueAt(i, 21);	//コメント1
						entry_data[i][17] = ""+tableModel_ms01.getValueAt(i, 22);	//コメント2
						entry_data[i][18] = ""+tableModel_ms01.getValueAt(i, 23);	//コメント3
						entry_data[i][19] = now_dtm;	//データ登録日時
						entry_data[i][20] = now_dtm;	//データ更新日時
						entry_data[i][21] = "(" + A00000Main.LoginUserId + ")" + A00000Main.LoginUserName;	//登録者コード
						entry_data[i][22] = "(" + A00000Main.LoginUserId + ")" + A00000Main.LoginUserName;	//更新者コード
						entry_data[i][23] = ""+tableModel_ms01.getValueAt(i, 24);	//基幹システムユーザーコード
						entry_data[i][24] = ""+tableModel_ms01.getValueAt(i, 25);	//削除区分
						entry_data[i][25] = ""+tableModel_ms01.getValueAt(i, 26);	//主要担当荷主CD
					}
					A00020InsertUdateSQL.RUN_SQLS_EU(tgt_table, field_name, entry_data, judg_field, judg_data, non_msg_fg,TgtDB);
					
					SetX=main_fm.getX();
					SetY=main_fm.getY();
	
					main_fm.setVisible(false);
					main_fm.dispose();
					WM00030UserMstSearch.UserMstSearch(0, 0);
					RenewFg = true;
				}
			}
		});
		
		RenewFg = true;
		
		//EXITボタン押下時の挙動
		exit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				WM00030UserMstSearch.UserMstSearch(0, 0);
			}
		});
	}
	
	private static Object[][] WhMstRt(ArrayList<String> TgtWHCD){
		ArrayList<String> SearchWHCD = TgtWHCD;
		ArrayList<String> SearchWHName = new ArrayList<String>();
		ArrayList<String> SearchPost = new ArrayList<String>();
		ArrayList<String> SearchAdd = new ArrayList<String>();
		ArrayList<String> SearchTel = new ArrayList<String>();
		ArrayList<String> SearchFax = new ArrayList<String>();
		ArrayList<String> SearchMail = new ArrayList<String>();
		ArrayList<String> SearchCom = new ArrayList<String>();
		ArrayList<String> SearchPTMSCD = new ArrayList<String>();
		boolean AllSearch = false;
		
		Object[][] WhMstRt = M00001WhMstRt.WhMstRt(
				SearchWHCD,
				SearchWHName,
				SearchPost,
				SearchAdd,
				SearchTel,
				SearchFax,
				SearchMail,
				SearchCom,
				SearchPTMSCD,
				AllSearch);
		return WhMstRt;
	}
	
	
	private static Object[][] ClMstRt(ArrayList<String> TgthCLCD){
		ArrayList<String> SearchClGpCD = new ArrayList<String>();
		ArrayList<String> SearchCLCD = TgthCLCD;
		ArrayList<String> SearchCLName = new ArrayList<String>();
		ArrayList<String> SearchPost = new ArrayList<String>();
		ArrayList<String> searchAdd = new ArrayList<String>();
		ArrayList<String> SearchTel = new ArrayList<String>();
		ArrayList<String> SearchFax = new ArrayList<String>();
		ArrayList<String> SearchMail = new ArrayList<String>();
		ArrayList<String> SearchCom = new ArrayList<String>();
		ArrayList<String> SearchWHCD = new ArrayList<String>();
		boolean AllSearch = false;
		
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
		
		return ClMstRt;
	}
	
	
	private static Object[][] ShippingCompanyMstRt(ArrayList<String> TgtShippingCompanyCd){
		ArrayList<String> SearchShippingCompanyCd = TgtShippingCompanyCd;
		ArrayList<String> SearchCompanyName = new ArrayList<String>();
		ArrayList<String> SearchPost = new ArrayList<String>();
		ArrayList<String> SearchAdd = new ArrayList<String>();
		ArrayList<String> SearchTel = new ArrayList<String>();
		ArrayList<String> SearchFax = new ArrayList<String>();
		ArrayList<String> SearchMail = new ArrayList<String>();
		ArrayList<String> SearchCom = new ArrayList<String>();
		boolean AllSearch = false;
		
		Object[][] ShippingCompanyMstRt = M00030ShippingCompanyMstRt.ShippingCompanyMstRt(
				SearchShippingCompanyCd,
				SearchCompanyName,
				SearchPost,
				SearchAdd,
				SearchTel,
				SearchFax,
				SearchMail,
				SearchCom,
				AllSearch);
		return ShippingCompanyMstRt;
	}
	
	private static Object[][] CarMstRt(ArrayList<String> TgtCarCd){
		ArrayList<String> SearchWHCD = new ArrayList<String>();
		ArrayList<String> SearchShippingCompanyCd = new ArrayList<String>();
		ArrayList<String> SearchCarCd = TgtCarCd;
		ArrayList<String> SearchCarName = new ArrayList<String>();
		ArrayList<String> SearchDelFg = new ArrayList<String>();
		boolean AllSearch = false;
		
		Object[][] CarMstRt = M00031CarMstRt.CarMstRt(
				SearchWHCD,
				SearchShippingCompanyCd,
				SearchCarCd,
				SearchCarName,
				SearchDelFg,
				AllSearch);
		return CarMstRt;
	}
}