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

public class WM00037CarMstExcelEntry{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	public static void CarMstExcelEntry(int x,int y,String TgtFilePath) {
		A00000Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;

		final JFrame main_fm = B00110FrameParts.FrameCreate(x,y,600,200,"Corgi00車輛マスタ登録（エクセル）","");
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
				CarMstExcelEntryMain(0,0,TgtFilePath,SheetName);
			}
		});
		
		//EXITボタン押下時の挙動
		exit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				WM00035CarMstSearch.CarMstSearch(0, 0);
			}
		});
		
	}
	
	public static void CarMstExcelEntryMain(int x,int y,String TgtFilePath,String SheetName) {
		A00000Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		
		final JFrame main_fm = B00110FrameParts.FrameCreate(x,y,750,800,"Corgi00車輛マスタ登録（エクセル）","");
		JLabel userinfo = B00110FrameParts.UserInfo();
		JButton exit_btn = B00110FrameParts.ExitBtn();
		JButton entry_btn = B00110FrameParts.EntryBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		
		String[] NeedCol = {
				 "担当倉庫"
				,"運送会社CD"
				,"車輛CD"
				,"車輛名01"
				,"車輛名02"
				,"車輛名03"
				,"乗務員CD"
				,"基幹システム車輛コード"
				,"削除フラグ"
		};
		int[] TgtCol = {
				 -1	//担当倉庫
				,-1	//運送会社CD
				,-1	//車輛CD
				,-1	//車輛名01
				,-1	//車輛名02
				,-1	//車輛名03
				,-1	//乗務員CD
				,-1	//基幹システム車輛コード
				,-1	//削除フラグ
				};
		
		JLabel LB_SheetList	= B00110FrameParts.JLabelSet(	10, 40,600,20,"以下のデータを登録しようとしています",11,0);
		main_fm.add(LB_SheetList);
		
		String[] columnNames01 = {
				 "FG"
				,"担当倉庫"
				,"倉庫名"
				,"運送会社CD"
				,"運送会社名1"
				,"運送会社名2"
				,"運送会社名3"
				,"車輛CD"
				,"車輛名01"
				,"車輛名02"
				,"車輛名03"
				,"乗務員CD"
				,"ユーザー名1"
				,"ユーザー名2"
				,"ユーザー名3"
				,"基幹システム車輛コード"
				,"削除フラグ"
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
		column = columnModel01.getColumn( 1);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//担当倉庫
		column = columnModel01.getColumn( 2);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//倉庫名
		column = columnModel01.getColumn( 3);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//運送会社CD
		column = columnModel01.getColumn( 4);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//運送会社名1
		column = columnModel01.getColumn( 5);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//運送会社名2
		column = columnModel01.getColumn( 6);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//運送会社名3
		column = columnModel01.getColumn( 7);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//車輛CD
		column = columnModel01.getColumn( 8);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//車輛名01
		column = columnModel01.getColumn( 9);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//車輛名02
		column = columnModel01.getColumn(10);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//車輛名03
		column = columnModel01.getColumn(11);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//乗務員CD
		column = columnModel01.getColumn(12);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//ユーザー名1
		column = columnModel01.getColumn(13);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//ユーザー名2
		column = columnModel01.getColumn(14);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//ユーザー名3
		column = columnModel01.getColumn(15);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//基幹システム車輛コード
		column = columnModel01.getColumn(16);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//削除フラグ
		
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
													+"担当倉庫"
													+",運送会社CD"
													+",車輛CD"
													+",車輛名01"
													+",車輛名02"
													+",車輛名03"
													+",乗務員CD"
													+",基幹システム車輛コード"
													+",削除フラグ\n"
													+"がヘッダに必要です");
			CarMstExcelEntry(0,0,TgtFilePath);
		}else {
			int[] ClmnType = new int[HeaderRead[0].length];
			for(int i=0;i<ClmnType.length;i++) {ClmnType[i]=1;}
			
			ClmnType[TgtCol[ 0]]=1;	//担当倉庫
			ClmnType[TgtCol[ 1]]=1;	//運送会社CD
			ClmnType[TgtCol[ 2]]=1;	//車輛CD
			ClmnType[TgtCol[ 3]]=1;	//車輛名01
			ClmnType[TgtCol[ 4]]=1;	//車輛名02
			ClmnType[TgtCol[ 5]]=1;	//車輛名03
			ClmnType[TgtCol[ 6]]=1;	//乗務員CD
			ClmnType[TgtCol[ 7]]=1;	//基幹システム車輛コード
			ClmnType[TgtCol[ 8]]=1;	//削除フラグ
			
			Object[][] ExcellRead = B00060ToolsExcellControl.ExcellRead(TgtFilePath,SheetName,ClmnType,true);
			
			if(0<ExcellRead.length&&ClmnType.length<=ExcellRead[0].length) {
				//倉庫マスタ
				//運送会社マスタ
				//ユーザーマスタ
				//コードアラート
				ArrayList<String> TgtWHCD = new ArrayList<String>();
				ArrayList<String> TgtShippingCompanyCd = new ArrayList<String>();
				ArrayList<String> TgtUserCd = new ArrayList<String>();
				
				for(int i=0;i<ExcellRead.length;i++) {
					for(int i01=0;i01<ExcellRead[i].length;i01++) {
						if(null==ExcellRead[i][i01]) {ExcellRead[i][i01]="";}
						ExcellRead[i][i01] = B00020ToolsTextControl.Trim(""+ExcellRead[i][i01]);
					}
					
					if(!"".equals(""+ExcellRead[i][TgtCol[ 0]])) {TgtWHCD.add(				""+ExcellRead[i][TgtCol[ 0]]);}
					if(!"".equals(""+ExcellRead[i][TgtCol[ 1]])) {TgtShippingCompanyCd.add(	""+ExcellRead[i][TgtCol[ 1]]);}
					if(!"".equals(""+ExcellRead[i][TgtCol[ 6]])) {TgtUserCd.add(			""+ExcellRead[i][TgtCol[ 6]]);}
				}
				
				Object[][] WhMstRt = WhMstRt(TgtWHCD);
				Object[][] ShippingCompanyMstRt = ShippingCompanyMstRt(TgtShippingCompanyCd);
				Object[][] UserMstRt = UserMstRt(TgtUserCd);
				
				
				ArrayList<String> ErrMsg = new ArrayList<String>();
				
				
				for(int i=0;i<ExcellRead.length;i++) {
					if(!"".equals(""+ExcellRead[i][TgtCol[ 0]])||!"".equals(""+ExcellRead[i][TgtCol[ 1]])||!"".equals(""+ExcellRead[i][TgtCol[ 2]])) {
						Object[] SetOb = new Object[17];
						SetOb[0] = false;
						SetOb[ 1] = ""+ExcellRead[i][TgtCol[ 0]];	//担当倉庫
						SetOb[ 2] = "";	//倉庫名
						SetOb[ 3] = ""+ExcellRead[i][TgtCol[ 1]];	//運送会社CD
						SetOb[ 4] = "";	//運送会社名1
						SetOb[ 5] = "";	//運送会社名2
						SetOb[ 6] = "";	//運送会社名3
						SetOb[ 7] = ""+ExcellRead[i][TgtCol[ 2]];	//車輛CD
						SetOb[ 8] = ""+ExcellRead[i][TgtCol[ 3]];	//車輛名01
						SetOb[ 9] = ""+ExcellRead[i][TgtCol[ 4]];	//車輛名02
						SetOb[10] = ""+ExcellRead[i][TgtCol[ 5]];	//車輛名03
						SetOb[11] = ""+ExcellRead[i][TgtCol[ 6]];	//乗務員CD
						SetOb[12] = "";	//ユーザー名1
						SetOb[13] = "";	//ユーザー名2
						SetOb[14] = "";	//ユーザー名3
						SetOb[15] = ""+ExcellRead[i][TgtCol[ 7]];	//基幹システム車輛コード
						SetOb[16] = ""+ExcellRead[i][TgtCol[ 8]];	//削除フラグ
						
						for(int i01=1;i01<SetOb.length;i01++) {
							SetOb[i01] = B00020ToolsTextControl.Trim(""+SetOb[i01]);
						}
						
						SetOb[16] = B00020ToolsTextControl.num_only_String(""+SetOb[16]);	//削除区分
						
						if("".equals(""+SetOb[16])) {	//削除区分
							SetOb[16] = "0";
						}
						
						for(int i01=0;i01<B00100DefaultVariable.DelList[1].length;i01++) {
							boolean UnHitFg = true;
							if(B00100DefaultVariable.DelList[1][i01].equals(""+SetOb[16])) {
								UnHitFg = false;
							}
							if(UnHitFg) {
								SetOb[16] = "0";
							}
						}
						
						boolean UnHitFg = true;
						int wint = i+1;
						
						if(!"".equals(""+SetOb[ 1])) {
							UnHitFg = true;
							for(int i01=0;i01<WhMstRt.length;i01++) {
								if((""+SetOb[ 1]).equals(""+WhMstRt[i01][0])) {
									SetOb[ 2] = ""+WhMstRt[i01][1];	//倉庫名
									UnHitFg = false;
								}
							}
							if(UnHitFg) {
								ErrMsg.add(wint+"行目エラー:("+SetOb[ 1]+")は未登録の倉庫コードです");
							}
						}else {
							ErrMsg.add(wint+"行目エラー:所属倉庫コードは必須です");
						}
						
						if(!"".equals(""+SetOb[ 3])) {
							UnHitFg = true;
							for(int i01=0;i01<ShippingCompanyMstRt.length;i01++) {
								if((""+SetOb[ 3]).equals(""+ShippingCompanyMstRt[i01][0])) {
									SetOb[ 4] = ""+ShippingCompanyMstRt[i01][1];	//運送会社名1
									SetOb[ 5] = ""+ShippingCompanyMstRt[i01][2];	//運送会社名2
									SetOb[ 6] = ""+ShippingCompanyMstRt[i01][3];	//運送会社名3
									UnHitFg = false;
								}
							}
							if(UnHitFg) {
								ErrMsg.add(wint+"行目エラー:("+SetOb[ 3]+")は未登録の運送会社コードです");
							}
						}else {
							ErrMsg.add(wint+"行目エラー:所属運送会社コードは必須です");
						}
						
						if(!"".equals(""+SetOb[11])) {
							UnHitFg = true;
							for(int i01=0;i01<UserMstRt.length;i01++) {
								if((""+SetOb[ 1]).equals(""+UserMstRt[i01][0])
									&& (""+SetOb[ 3]).equals(""+UserMstRt[i01][1])
									&& (""+SetOb[11]).equals(""+UserMstRt[i01][3])
									) {
									SetOb[12] = ""+UserMstRt[i01][10];	//ユーザー名1
									SetOb[13] = ""+UserMstRt[i01][11];	//ユーザー名2
									SetOb[14] = ""+UserMstRt[i01][12];	//ユーザー名3
									UnHitFg = false;
								}
							}
							if(UnHitFg) {
								ErrMsg.add(wint+"行目エラー:("+SetOb[11]+")は未登録のユーザーコードです");
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
					FLD_PATH = A00000Main.MainFLD+"\\MstControl\\CarMst";
					B00040ToolsFolderCheck.FLD_CHECK(FLD_PATH);
					FLD_PATH = A00000Main.MainFLD+"\\MstControl\\CarMst\\Err";
					B00040ToolsFolderCheck.FLD_CHECK(FLD_PATH);
					FLD_PATH = A00000Main.MainFLD+"\\MstControl\\CarMst\\BK";
					B00040ToolsFolderCheck.FLD_CHECK(FLD_PATH);
					
					//ファイルに出力
					
					String NowDTM=B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[1].replace(" ", "").replace("/", "").replace(":", "");
					
					FLD_PATH = A00000Main.MainFLD+"\\MstControl\\CarMst\\Err";
					
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
		RenewFg = true;
		
		//EXITボタン押下時の挙動
		exit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				WM00035CarMstSearch.CarMstSearch(0, 0);
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
	
	private static Object[][] UserMstRt(ArrayList<String> TgtUserCd){
		ArrayList<String> SearchWHCD = new ArrayList<String>();
		ArrayList<String> SearchShippingCompanyCd = new ArrayList<String>();
		ArrayList<String> SearchAuthorityFG = new ArrayList<String>();
		ArrayList<String> SearchUserCd = TgtUserCd;
		ArrayList<String> SearchUserName = new ArrayList<String>();
		ArrayList<String> SearchCarCd = new ArrayList<String>();
		ArrayList<String> SearchCarName = new ArrayList<String>();
		ArrayList<String> SearchPost = new ArrayList<String>();
		ArrayList<String> SearchAdd = new ArrayList<String>();
		ArrayList<String> SearchTel = new ArrayList<String>();
		ArrayList<String> SearchFax = new ArrayList<String>();
		ArrayList<String> SearchMail = new ArrayList<String>();
		ArrayList<String> SearchCom = new ArrayList<String>();
		ArrayList<String> SearchDelFg = new ArrayList<String>();
		boolean AllSearch = false;
		
		Object[][] UserMstRt = M00020UserMstRt.UserMstRt(
				SearchWHCD,
				SearchShippingCompanyCd,
				SearchAuthorityFG,
				SearchUserCd,
				SearchUserName,
				SearchCarCd,
				SearchCarName,
				SearchPost,
				SearchAdd,
				SearchTel,
				SearchFax,
				SearchMail,
				SearchCom,
				SearchDelFg,
				AllSearch);
		return UserMstRt;
	}
}