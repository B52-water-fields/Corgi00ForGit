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
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class WM00067DeliveryComversionMstExcelEntry{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	public static void DeliveryComversionMstExcelEntry(int x,int y,String TgtFilePath) {
		A00000Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;

		final JFrame main_fm = B00110FrameParts.FrameCreate(x,y,600,200,"Corgi00届先変換マスタ登録（エクセル）","");
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
				DeliveryComversionMstExcelEntryMain(0,0,TgtFilePath,SheetName);
			}
		});
		
		//EXITボタン押下時の挙動
		exit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				WM00065DeliveryComversionMstSerarch.DeliveryComversionMstSerarch(0, 0);
			}
		});
	}
	
	public static void DeliveryComversionMstExcelEntryMain(int x,int y,String TgtFilePath,String SheetName) {
		A00000Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		
		final JFrame main_fm = B00110FrameParts.FrameCreate(x,y,850,800,"Corgi00届先変換マスタ登録（エクセル）","");
		JLabel userinfo = B00110FrameParts.UserInfo();
		JButton exit_btn = B00110FrameParts.ExitBtn();
		JButton entry_btn = B00110FrameParts.EntryBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		main_fm.add(entry_btn);
		
		Object[][] NeedCol = {
				 {"荷主グループCD"		,1, 0}
				,{"荷主届先CD"			,1, 1}
				,{"届先CD"				,1, 2}
				,{"届先部署CD"			,1, 3}
				,{"送り状登録名"		,1, 4}
				,{"コメント01"			,1, 5}
				,{"コメント02"			,1, 6}
				,{"コメント03"			,1, 7}
				,{"コメント04"			,1, 8}
				,{"コメント05"			,1, 9}
				,{"削除区分"			,0,10}
				,{"届先マスタ優先フラグ",0,11}
				};
		
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
			DeliveryComversionMstExcelEntry(0,0,TgtFilePath);
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
				WM00065DeliveryComversionMstSerarch.DeliveryComversionMstSerarch(0, 0);
			}else {
				main_fm.setVisible(true);
			}
		}
		RenewFg = true;
		
		//チェックボックス操作時の挙動
		tableModel_ms01.addTableModelListener(new TableModelListener(){
			public void tableChanged(TableModelEvent e){
				if(RenewFg) {
					RenewFg = false;
					int row_count = tableModel_ms01.getRowCount();
					Boolean setBL=Boolean.valueOf(false);
					for(int i=0;i<row_count;i++){
						if(i!=e.getFirstRow()){
							tableModel_ms01.setValueAt(setBL, i, 0);
						}else {
							
						}
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
				WM00065DeliveryComversionMstSerarch.DeliveryComversionMstSerarch(0, 0);
			}
		});
	}
	private static ArrayList<String> ErrCheck(Object[][] CheckOb,String[] TableCol){
		ArrayList<String> ErrMsg = new ArrayList<String>();
		
		int ColClGpCD				= (int) 1;	//荷主グループCD
		int ColCL_DECD				= (int) 2;	//荷主届先CD
		int ColDECD					= (int) 3;	//届先CD
		int ColDepartmentCd			= (int) 4;	//届先部署CD
		int ColSetName				= (int) 5;	//送り状登録名
		int ColCom01				= (int) 6;	//コメント01
		int ColCom02				= (int) 7;	//コメント02
		int ColCom03				= (int) 8;	//コメント03
		int ColCom04				= (int) 9;	//コメント04
		int ColCom05				= (int)10;	//コメント05
		int ColDelFg				= (int)11;	//削除区分
		int ColMstPriorityFirstFg	= (int)12;	//届先マスタ優先フラグ
		
		for(int i=0;i<TableCol.length;i++) {
			switch(""+TableCol[i]) {
				case "荷主グループCD":
					ColClGpCD = i;
					break;
				case "荷主届先CD":
					ColCL_DECD = i;
					break;
				case "届先CD":
					ColDECD = i;
					break;
				case "届先部署CD":
					ColDepartmentCd = i;
					break;
				case "送り状登録名":
					ColSetName = i;
					break;
				case "コメント01":
					ColCom01 = i;
					break;
				case "コメント02":
					ColCom02 = i;
					break;
				case "コメント03":
					ColCom03 = i;
					break;
				case "コメント04":
					ColCom04 = i;
					break;
				case "コメント05":
					ColCom05 = i;
					break;
				case "削除区分":
					ColDelFg = i;
					break;
				case "届先マスタ優先フラグ":
					ColMstPriorityFirstFg = i;
					break;
				default:
					break;
			}
		}
		//届先マスタ取得
		ArrayList<String> SearchDECD 			= new ArrayList<String>();
		ArrayList<String> SearchDepartmentCd 	= new ArrayList<String>();
		ArrayList<String> SearchDEName 			= new ArrayList<String>();
		ArrayList<String> SearchPost 			= new ArrayList<String>();
		ArrayList<String> SearchAdd 			= new ArrayList<String>();
		ArrayList<String> SearchTel 			= new ArrayList<String>();
		ArrayList<String> SearchFax 			= new ArrayList<String>();
		ArrayList<String> SearchMail 			= new ArrayList<String>();
		ArrayList<String> SearchCom 			= new ArrayList<String>();
		ArrayList<String> SearchPrefecturesCd 	= new ArrayList<String>();
		ArrayList<String> SearchMunicipalityCd 	= new ArrayList<String>();
		ArrayList<String> SearchDelFg 			= new ArrayList<String>();
		boolean SearcNotJis = true;
		boolean SearchTelExactMatch = false;
		boolean AllSearch = false;
		
		for(int i=0;i<CheckOb.length;i++) {
			String WST = B00020ToolsTextControl.Trim(""+CheckOb[i][ColDECD]);
			if(!"".equals(WST)) {
				SearchDECD.add(WST);
			}
		}
		
		Object[][] DeliveryMstRt = M00040DeliveryMstRt.DeliveryMstRt(
				SearchDECD,
				SearchDepartmentCd,
				SearchDEName,
				SearchPost,
				SearchAdd,
				SearchTel,
				SearchFax,
				SearchMail,
				SearchCom,
				SearchPrefecturesCd,
				SearchMunicipalityCd,
				SearchDelFg,
				SearcNotJis,
				SearchTelExactMatch,
				AllSearch
				);
		
		for(int i=0;i<CheckOb.length;i++) {
			for(int i01=0;i01<CheckOb[i].length;i01++) {
				CheckOb[i][i01] = B00020ToolsTextControl.Trim(""+CheckOb[i][i01]);
			}
			String GetClGpCD				= ""+CheckOb[i][ColClGpCD];				//荷主グループCD
			String GetCL_DECD				= ""+CheckOb[i][ColCL_DECD];			//荷主届先CD
			String GetDECD					= ""+CheckOb[i][ColDECD];				//届先CD
			String GetDepartmentCd			= ""+CheckOb[i][ColDepartmentCd];		//届先部署CD
			String GetSetName				= ""+CheckOb[i][ColSetName];			//送り状登録名
			String GetCom01					= ""+CheckOb[i][ColCom01];				//コメント01
			String GetCom02					= ""+CheckOb[i][ColCom02];				//コメント02
			String GetCom03					= ""+CheckOb[i][ColCom03];				//コメント03
			String GetCom04					= ""+CheckOb[i][ColCom04];				//コメント04
			String GetCom05					= ""+CheckOb[i][ColCom05];				//コメント05
			String GetDelFg					= ""+CheckOb[i][ColDelFg];				//削除区分
			String GetMstPriorityFirstFg	= ""+CheckOb[i][ColMstPriorityFirstFg];	//届先マスタ優先フラグ
			
			if("".equals(GetClGpCD)
					&&"".equals(GetCL_DECD)
					&&"".equals(GetDECD)
					&&"".equals(GetDepartmentCd)
					) {
			}else {
				if(!A00000Main.ClGp.equals(GetClGpCD)) {
					int wint = i+1;
					ErrMsg.add(wint+"行目エラー：("+GetClGpCD+")は現在作業中の荷主グループコードではありません別荷主の設定を投入しようとしていませんか？("+A00000Main.ClGp+")を設定してください。");
				}
				boolean UnHitFg = true;
				for(int i01=0;i01<DeliveryMstRt.length;i01++) {
					if(GetDECD.equals(""+DeliveryMstRt[i01][M00040DeliveryMstRt.ColDECD]) 
							&& GetDepartmentCd.equals(""+DeliveryMstRt[i01][M00040DeliveryMstRt.ColDepartmentCd]) 
							) {
						UnHitFg = false;
					}
				}
				if(UnHitFg) {
					int wint = i+1;
					ErrMsg.add(wint+"行目エラー：(届先："+GetDECD+"　部署："+GetDepartmentCd+")は届先マスタに存在しません");
				}
				if("".equals(GetCL_DECD)) {
					int wint = i+1;
					ErrMsg.add(wint+"行目エラー：荷主届け先コードは必須です");
				}
			}
		}
		return ErrMsg;
	}
	private static void ErrView(ArrayList<String>ErrMsg) {
		//必要フォルダを生成する
		String FLD_PATH = A00000Main.MainFLD+"\\MstControl";
		B00040ToolsFolderCheck.FLD_CHECK(FLD_PATH);
		FLD_PATH = A00000Main.MainFLD+"\\MstControl\\DeliveryComversionMst";
		B00040ToolsFolderCheck.FLD_CHECK(FLD_PATH);
		FLD_PATH = A00000Main.MainFLD+"\\MstControl\\DeliveryComversionMst\\Err";
		B00040ToolsFolderCheck.FLD_CHECK(FLD_PATH);
		FLD_PATH = A00000Main.MainFLD+"\\MstControl\\DeliveryComversionMst\\BK";
		B00040ToolsFolderCheck.FLD_CHECK(FLD_PATH);
		
		//ファイルに出力
		
		String NowDTM=B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[1].replace(" ", "").replace("/", "").replace(":", "");
		
		FLD_PATH = A00000Main.MainFLD+"\\MstControl\\DeliveryComversionMst\\Err";
		
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