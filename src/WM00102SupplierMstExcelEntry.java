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

public class WM00102SupplierMstExcelEntry{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	public static void SupplierMstExcelEntry(int x,int y,String TgtFilePath) {
		A00000Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;

		final JFrame main_fm = B00110FrameParts.FrameCreate(x,y,600,200,"Corgi00仕入先マスタ登録（エクセル）","");
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
				SupplierMstExcelEntryMain(0,0,TgtFilePath,SheetName);
			}
		});
		
		//EXITボタン押下時の挙動
		exit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				WM00100SupplierMstSearch.SupplierMstSearch(0, 0);
			}
		});
	}
	public static void SupplierMstExcelEntryMain(int x,int y,String TgtFilePath,String SheetName) {
		A00000Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		final JFrame main_fm = B00110FrameParts.FrameCreate(x,y,750,800,"Corgi00仕入先マスタ登録（エクセル）","");
		JLabel userinfo = B00110FrameParts.UserInfo();
		JButton exit_btn = B00110FrameParts.ExitBtn();
		JButton entry_btn = B00110FrameParts.EntryBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		main_fm.add(entry_btn);
		
		Object[][] NeedCol = {
				 {"担当倉庫"				, 1	, 0}
				,{"荷主CD"					, 1	, 1}
				,{"仕入先コード"			, 1	, 2}
				,{"仕入先名1"				, 1	, 3}
				,{"仕入先名2"				, 1	, 4}
				,{"仕入先名3"				, 1	, 5}
				,{"仕入先郵便"				, 1	, 6}
				,{"仕入先住所1"				, 1	, 7}
				,{"仕入先住所2"				, 1	, 8}
				,{"仕入先住所3"				, 1	, 9}
				,{"仕入先電話"				, 1	,10}
				,{"仕入先FAX"				, 1	,11}
				,{"仕入先MAIL"				, 1	,12}
				,{"コメント1"				, 1	,13}
				,{"コメント2"				, 1	,14}
				,{"コメント3"				, 1	,15}
				,{"基幹Sysコード（部門）"	, 1	,16}
				,{"基幹Sysコード（荷主）"	, 1	,17}
				,{"支払いサイト（月数）"	, 0	,18}
				,{"支払日（末日＝99）"		, 0	,19}
				,{"締め日（末日＝99）"		, 0	,20}
				,{"納品先コード"			, 1	,21}
				,{"部署CD"					, 1	,22}
				};	//フィールド名,フィールドタイプ(0:数値 1:文字列 2:日付時刻),基本のカラム(ゼロスタート),基本のカラム位置※カラム位置は後で読み込んだエクセルの1行目でフィールド名比較して更新されます
		
		JLabel LB_SheetList	= B00110FrameParts.JLabelSet(	10, 40,540,20,"以下のデータを登録しようとしています",11,0);
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
			SupplierMstExcelEntry(0,0,TgtFilePath);
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
				WM00100SupplierMstSearch.SupplierMstSearch(0, 0);
			}else {
				main_fm.setVisible(true);
			}
		}
		
		main_fm.setVisible(true);
		RenewFg = true;
		
		//登録ボタン押下時の挙動
		entry_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					String[] TableCol = B10010TableControl.TableFieldNameRt(tb01);
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
						B00040ToolsFolderCheck.FileBackUpNormal(TgtFilePath) ;
						
						SetX=main_fm.getX();
						SetY=main_fm.getY();

						main_fm.setVisible(false);
						main_fm.dispose();
						WM00090LocationMstSearch.LocationMstSearch(0, 0);
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
				WM00100SupplierMstSearch.SupplierMstSearch(0, 0);
			}
		});
	}
	
	private static void MstEntry(Object[][] CheckOb,String[] TableCol) {
		String now_dtm = B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[1];
		
		int ColClWh				= (int)0;	//担当倉庫
		int ColClCd				= (int)1;	//荷主CD
		int ColSPCd				= (int)2;	//仕入先コード
		int ColSPName01			= (int)3;	//仕入先名1
		int ColSPName02			= (int)4;	//仕入先名2
		int ColSPName03			= (int)5;	//仕入先名3
		int ColSPPost			= (int)6;	//仕入先郵便
		int ColSPAdd01			= (int)7;	//仕入先住所1
		int ColSPAdd02			= (int)8;	//仕入先住所2
		int ColSPAdd03			= (int)9;	//仕入先住所3
		int ColSPTel			= (int)10;	//仕入先電話
		int ColSPFax			= (int)11;	//仕入先FAX
		int ColSPMail			= (int)12;	//仕入先MAIL
		int ColCom01			= (int)13;	//コメント1
		int ColCom02			= (int)14;	//コメント2
		int ColCom03			= (int)15;	//コメント3
		int ColPTMSCDBMN		= (int)16;	//基幹Sysコード（部門）
		int ColPTMSCDNINUSHI	= (int)17;	//基幹Sysコード（荷主）
		int ColPaySite			= (int)18;	//支払いサイト（月数）
		int ColPayDate			= (int)19;	//支払日　末日＝99
		int ColShimeDate		= (int)20;	//締め日　末日＝99
		int ColDECD				= (int)21;	//納品先コード
		int ColDepartmentCd		= (int)22;	//納品先部署コード
		
		for(int i=0;i<TableCol.length;i++) {
			switch(""+TableCol[i]) {
			 	case "担当倉庫":
			 		ColClWh= i;
			 		break;
				case "荷主CD":
			 		ColClCd= i;
			 		break;
				case "仕入先コード":
			 		ColSPCd= i;
			 		break;
				case "仕入先名1":
			 		ColSPName01= i;
			 		break;
				case "仕入先名2":
			 		ColSPName02= i;
			 		break;
				case "仕入先名3":
			 		ColSPName03= i;
			 		break;
				case "仕入先郵便":
			 		ColSPPost= i;
			 		break;
				case "仕入先住所1":
			 		ColSPAdd01= i;
			 		break;
				case "仕入先住所2":
			 		ColSPAdd02= i;
			 		break;
				case "仕入先住所3":
			 		ColSPAdd03= i;
			 		break;
				case "仕入先電話":
			 		ColSPTel= i;
			 		break;
				case "仕入先FAX":
			 		ColSPFax= i;
			 		break;
				case "仕入先MAIL":
			 		ColSPMail= i;
			 		break;
				case "コメント1":
			 		ColCom01= i;
			 		break;
				case "コメント2":
			 		ColCom02= i;
			 		break;
				case "コメント3":
			 		ColCom03= i;
			 		break;
				case "基幹Sysコード（部門）":
			 		ColPTMSCDBMN= i;
			 		break;
				case "基幹Sysコード（荷主）":
			 		ColPTMSCDNINUSHI= i;
			 		break;
				case "支払いサイト（月数）":
			 		ColPaySite= i;
			 		break;
				case "支払日（末日＝99）":
			 		ColPayDate= i;
			 		break;
				case "締め日（末日＝99）":
			 		ColShimeDate= i;
			 		break;
				case "納品先コード":
			 		ColDECD= i;
			 		break;
				case "部署CD":
			 		ColDepartmentCd= i;
			 		break;
				default:
			 		break;
			}
		}
		int EntryCount = 0;
		for(int i=0;i<CheckOb.length;i++) {
			String GetClWh			= ""+CheckOb[i][ColClWh];			//担当倉庫
			String GetClCd			= ""+CheckOb[i][ColClCd];			//荷主CD
			String GetSPCd			= ""+CheckOb[i][ColSPCd];			//仕入先コード
			String GetSPName01		= ""+CheckOb[i][ColSPName01];		//仕入先名1
			String GetSPName02		= ""+CheckOb[i][ColSPName02];		//仕入先名2
			String GetSPName03		= ""+CheckOb[i][ColSPName03];		//仕入先名3
			String GetSPPost		= ""+CheckOb[i][ColSPPost];			//仕入先郵便
			String GetSPAdd01		= ""+CheckOb[i][ColSPAdd01];		//仕入先住所1
			String GetSPAdd02		= ""+CheckOb[i][ColSPAdd02];		//仕入先住所2
			String GetSPAdd03		= ""+CheckOb[i][ColSPAdd03];		//仕入先住所3
			String GetSPTel			= ""+CheckOb[i][ColSPTel];			//仕入先電話
			String GetSPFax			= ""+CheckOb[i][ColSPFax];			//仕入先FAX
			String GetSPMail		= ""+CheckOb[i][ColSPMail];			//仕入先MAIL
			String GetCom01			= ""+CheckOb[i][ColCom01];			//コメント1
			String GetCom02			= ""+CheckOb[i][ColCom02];			//コメント2
			String GetCom03			= ""+CheckOb[i][ColCom03];			//コメント3
			String GetPTMSCDBMN		= ""+CheckOb[i][ColPTMSCDBMN];		//基幹Sysコード（部門）
			String GetPTMSCDNINUSHI	= ""+CheckOb[i][ColPTMSCDNINUSHI];	//基幹Sysコード（荷主）
			String GetPaySite		= ""+CheckOb[i][ColPaySite];		//支払いサイト（月数）
			String GetPayDate		= ""+CheckOb[i][ColPayDate];		//支払日　末日＝99
			String GetShimeDate		= ""+CheckOb[i][ColShimeDate];		//締め日　末日＝99
			String GetDECD			= ""+CheckOb[i][ColDECD];			//納品先コード
			String GetDepartmentCd	= ""+CheckOb[i][ColDepartmentCd];	//納品先部署コード
			
			int BackClWh			= (int)0;
			int BackClCd			= (int)1;
			int BackSPCd			= (int)2;
			int BackSPName01		= (int)3;
			int BackSPName02		= (int)4;
			int BackSPName03		= (int)5;
			int BackSPPost			= (int)6;
			int BackSPAdd01			= (int)7;
			int BackSPAdd02			= (int)8;
			int BackSPAdd03			= (int)9;
			int BackSPTel			= (int)10;
			int BackSPFax			= (int)11;
			int BackSPMail			= (int)12;
			int BackCom01			= (int)13;
			int BackCom02			= (int)14;
			int BackCom03			= (int)15;
			int BackPTMSCDBMN		= (int)16;
			int BackPTMSCDNINUSHI	= (int)17;
			int BackPaySite			= (int)18;
			int BackPayDate			= (int)19;
			int BackShimeDate		= (int)20;
			int BackDECD			= (int)21;
			int BackDepartmentCd	= (int)22;
			
			String[] TxtCheck	= TxtCheck(
					GetClWh,
					GetClCd,
					GetSPCd,
					GetSPName01,
					GetSPName02,
					GetSPName03,
					GetSPPost,
					GetSPAdd01,
					GetSPAdd02,
					GetSPAdd03,
					GetSPTel,
					GetSPFax,
					GetSPMail,
					GetCom01,
					GetCom02,
					GetCom03,
					GetPTMSCDBMN,
					GetPTMSCDNINUSHI,
					GetPaySite,
					GetPayDate,
					GetShimeDate,
					GetDECD,
					GetDepartmentCd,
					BackClWh,
					BackClCd,
					BackSPCd,
					BackSPName01,
					BackSPName02,
					BackSPName03,
					BackSPPost,
					BackSPAdd01,
					BackSPAdd02,
					BackSPAdd03,
					BackSPTel,
					BackSPFax,
					BackSPMail,
					BackCom01,
					BackCom02,
					BackCom03,
					BackPTMSCDBMN,
					BackPTMSCDNINUSHI,
					BackPaySite,
					BackPayDate,
					BackShimeDate,
					BackDECD,
					BackDepartmentCd
					);
			
			GetClWh				= TxtCheck[BackClWh];
			GetClCd				= TxtCheck[BackClCd];
			GetSPCd				= TxtCheck[BackSPCd];
			GetSPName01			= TxtCheck[BackSPName01];
			GetSPName02			= TxtCheck[BackSPName02];
			GetSPName03			= TxtCheck[BackSPName03];
			GetSPPost			= TxtCheck[BackSPPost];
			GetSPAdd01			= TxtCheck[BackSPAdd01];
			GetSPAdd02			= TxtCheck[BackSPAdd02];
			GetSPAdd03			= TxtCheck[BackSPAdd03];
			GetSPTel			= TxtCheck[BackSPTel];
			GetSPFax			= TxtCheck[BackSPFax];
			GetSPMail			= TxtCheck[BackSPMail];
			GetCom01			= TxtCheck[BackCom01];
			GetCom02			= TxtCheck[BackCom02];
			GetCom03			= TxtCheck[BackCom03];
			GetPTMSCDBMN		= TxtCheck[BackPTMSCDBMN];
			GetPTMSCDNINUSHI	= TxtCheck[BackPTMSCDNINUSHI];
			GetPaySite			= TxtCheck[BackPaySite];
			GetPayDate			= TxtCheck[BackPayDate];
			GetShimeDate		= TxtCheck[BackShimeDate];
			GetDECD				= TxtCheck[BackDECD];
			GetDepartmentCd		= TxtCheck[BackDepartmentCd];
			
			//必須項目のフィールドが全て空白ならSKIP
			boolean SkipFg = false;
			if(	"".equals(GetClWh)
					&&"".equals(GetClCd)
					&&"".equals(GetSPCd)
					&&"".equals(GetSPName01)
					) {
				SkipFg = true;
			}
			if(!SkipFg) {
				EntryCount = EntryCount+1;
			}
		}
		String[] SetClWh			= new String[EntryCount];
		String[] SetClCd			= new String[EntryCount];
		String[] SetSPCd			= new String[EntryCount];
		String[] SetSPName01		= new String[EntryCount];
		String[] SetSPName02		= new String[EntryCount];
		String[] SetSPName03		= new String[EntryCount];
		String[] SetSPPost			= new String[EntryCount];
		String[] SetSPAdd01			= new String[EntryCount];
		String[] SetSPAdd02			= new String[EntryCount];
		String[] SetSPAdd03			= new String[EntryCount];
		String[] SetSPTel			= new String[EntryCount];
		String[] SetSPFax			= new String[EntryCount];
		String[] SetSPMail			= new String[EntryCount];
		String[] SetCom01			= new String[EntryCount];
		String[] SetCom02			= new String[EntryCount];
		String[] SetCom03			= new String[EntryCount];
		String[] SetPTMSCDBMN		= new String[EntryCount];
		String[] SetPTMSCDNINUSHI	= new String[EntryCount];
		String[] SetPaySite			= new String[EntryCount];
		String[] SetPayDate			= new String[EntryCount];
		String[] SetShimeDate		= new String[EntryCount];
		String[] SetDECD			= new String[EntryCount];
		String[] SetDepartmentCd	= new String[EntryCount];
		String[] SetEntryDate		= new String[EntryCount];
		String[] SetUpdateDate		= new String[EntryCount];
		String[] SetEntryUser		= new String[EntryCount];
		String[] SetUpdateUser		= new String[EntryCount];
		EntryCount = 0;
		
		for(int i=0;i<CheckOb.length;i++) {
			String GetClWh			= ""+CheckOb[i][ColClWh];			//担当倉庫
			String GetClCd			= ""+CheckOb[i][ColClCd];			//荷主CD
			String GetSPCd			= ""+CheckOb[i][ColSPCd];			//仕入先コード
			String GetSPName01		= ""+CheckOb[i][ColSPName01];		//仕入先名1
			String GetSPName02		= ""+CheckOb[i][ColSPName02];		//仕入先名2
			String GetSPName03		= ""+CheckOb[i][ColSPName03];		//仕入先名3
			String GetSPPost		= ""+CheckOb[i][ColSPPost];			//仕入先郵便
			String GetSPAdd01		= ""+CheckOb[i][ColSPAdd01];		//仕入先住所1
			String GetSPAdd02		= ""+CheckOb[i][ColSPAdd02];		//仕入先住所2
			String GetSPAdd03		= ""+CheckOb[i][ColSPAdd03];		//仕入先住所3
			String GetSPTel			= ""+CheckOb[i][ColSPTel];			//仕入先電話
			String GetSPFax			= ""+CheckOb[i][ColSPFax];			//仕入先FAX
			String GetSPMail		= ""+CheckOb[i][ColSPMail];			//仕入先MAIL
			String GetCom01			= ""+CheckOb[i][ColCom01];			//コメント1
			String GetCom02			= ""+CheckOb[i][ColCom02];			//コメント2
			String GetCom03			= ""+CheckOb[i][ColCom03];			//コメント3
			String GetPTMSCDBMN		= ""+CheckOb[i][ColPTMSCDBMN];		//基幹Sysコード（部門）
			String GetPTMSCDNINUSHI	= ""+CheckOb[i][ColPTMSCDNINUSHI];	//基幹Sysコード（荷主）
			String GetPaySite		= ""+CheckOb[i][ColPaySite];		//支払いサイト（月数）
			String GetPayDate		= ""+CheckOb[i][ColPayDate];		//支払日　末日＝99
			String GetShimeDate		= ""+CheckOb[i][ColShimeDate];		//締め日　末日＝99
			String GetDECD			= ""+CheckOb[i][ColDECD];			//納品先コード
			String GetDepartmentCd	= ""+CheckOb[i][ColDepartmentCd];	//納品先部署コード
			
			int BackClWh			= (int)0;
			int BackClCd			= (int)1;
			int BackSPCd			= (int)2;
			int BackSPName01		= (int)3;
			int BackSPName02		= (int)4;
			int BackSPName03		= (int)5;
			int BackSPPost			= (int)6;
			int BackSPAdd01			= (int)7;
			int BackSPAdd02			= (int)8;
			int BackSPAdd03			= (int)9;
			int BackSPTel			= (int)10;
			int BackSPFax			= (int)11;
			int BackSPMail			= (int)12;
			int BackCom01			= (int)13;
			int BackCom02			= (int)14;
			int BackCom03			= (int)15;
			int BackPTMSCDBMN		= (int)16;
			int BackPTMSCDNINUSHI	= (int)17;
			int BackPaySite			= (int)18;
			int BackPayDate			= (int)19;
			int BackShimeDate		= (int)20;
			int BackDECD			= (int)21;
			int BackDepartmentCd	= (int)22;
			
			String[] TxtCheck	= TxtCheck(
					GetClWh,
					GetClCd,
					GetSPCd,
					GetSPName01,
					GetSPName02,
					GetSPName03,
					GetSPPost,
					GetSPAdd01,
					GetSPAdd02,
					GetSPAdd03,
					GetSPTel,
					GetSPFax,
					GetSPMail,
					GetCom01,
					GetCom02,
					GetCom03,
					GetPTMSCDBMN,
					GetPTMSCDNINUSHI,
					GetPaySite,
					GetPayDate,
					GetShimeDate,
					GetDECD,
					GetDepartmentCd,
					BackClWh,
					BackClCd,
					BackSPCd,
					BackSPName01,
					BackSPName02,
					BackSPName03,
					BackSPPost,
					BackSPAdd01,
					BackSPAdd02,
					BackSPAdd03,
					BackSPTel,
					BackSPFax,
					BackSPMail,
					BackCom01,
					BackCom02,
					BackCom03,
					BackPTMSCDBMN,
					BackPTMSCDNINUSHI,
					BackPaySite,
					BackPayDate,
					BackShimeDate,
					BackDECD,
					BackDepartmentCd
					);
			
			GetClWh				= TxtCheck[BackClWh];
			GetClCd				= TxtCheck[BackClCd];
			GetSPCd				= TxtCheck[BackSPCd];
			GetSPName01			= TxtCheck[BackSPName01];
			GetSPName02			= TxtCheck[BackSPName02];
			GetSPName03			= TxtCheck[BackSPName03];
			GetSPPost			= TxtCheck[BackSPPost];
			GetSPAdd01			= TxtCheck[BackSPAdd01];
			GetSPAdd02			= TxtCheck[BackSPAdd02];
			GetSPAdd03			= TxtCheck[BackSPAdd03];
			GetSPTel			= TxtCheck[BackSPTel];
			GetSPFax			= TxtCheck[BackSPFax];
			GetSPMail			= TxtCheck[BackSPMail];
			GetCom01			= TxtCheck[BackCom01];
			GetCom02			= TxtCheck[BackCom02];
			GetCom03			= TxtCheck[BackCom03];
			GetPTMSCDBMN		= TxtCheck[BackPTMSCDBMN];
			GetPTMSCDNINUSHI	= TxtCheck[BackPTMSCDNINUSHI];
			GetPaySite			= TxtCheck[BackPaySite];
			GetPayDate			= TxtCheck[BackPayDate];
			GetShimeDate		= TxtCheck[BackShimeDate];
			GetDECD				= TxtCheck[BackDECD];
			GetDepartmentCd		= TxtCheck[BackDepartmentCd];
			
			//必須項目のフィールドが全て空白ならSKIP
			boolean SkipFg = false;
			if(	"".equals(GetClWh)
					&&"".equals(GetClCd)
					&&"".equals(GetSPCd)
					&&"".equals(GetSPName01)
					) {
				SkipFg = true;
			}
			if(!SkipFg) {
				SetClWh[EntryCount]				= GetClWh;
				SetClCd[EntryCount]				= GetClCd;
				SetSPCd[EntryCount]				= GetSPCd;
				SetSPName01[EntryCount]			= GetSPName01;
				SetSPName02[EntryCount]			= GetSPName02;
				SetSPName03[EntryCount]			= GetSPName03;
				SetSPPost[EntryCount]			= GetSPPost;
				SetSPAdd01[EntryCount]			= GetSPAdd01;
				SetSPAdd02[EntryCount]			= GetSPAdd02;
				SetSPAdd03[EntryCount]			= GetSPAdd03;
				SetSPTel[EntryCount]			= GetSPTel;
				SetSPFax[EntryCount]			= GetSPFax;
				SetSPMail[EntryCount]			= GetSPMail;
				SetCom01[EntryCount]			= GetCom01;
				SetCom02[EntryCount]			= GetCom02;
				SetCom03[EntryCount]			= GetCom03;
				SetPTMSCDBMN[EntryCount]		= GetPTMSCDBMN;
				SetPTMSCDNINUSHI[EntryCount]	= GetPTMSCDNINUSHI;
				SetPaySite[EntryCount]			= GetPaySite;
				SetPayDate[EntryCount]			= GetPayDate;
				SetShimeDate[EntryCount]		= GetShimeDate;
				SetDECD[EntryCount]				= GetDECD;
				SetDepartmentCd[EntryCount]		= GetDepartmentCd;
				SetEntryDate[EntryCount]		= now_dtm;
				SetUpdateDate[EntryCount]		= now_dtm;
				SetEntryUser[EntryCount]		= "(" + A00000Main.LoginUserId + ")" + A00000Main.LoginUserName;
				SetUpdateUser[EntryCount]		= "(" + A00000Main.LoginUserId + ")" + A00000Main.LoginUserName;
				
				EntryCount = EntryCount+1;
			}
		}
		
		Object[][] SetOb = {
						 {"ClWh"			,"1","1","Key"	,SetClWh}
						,{"ClCd"			,"1","1","Key"	,SetClCd}
						,{"SPCd"			,"1","1","Key"	,SetSPCd}
						,{"SPName01"		,"1","1",""		,SetSPName01}
						,{"SPName02"		,"1","1",""		,SetSPName02}
						,{"SPName03"		,"1","1",""		,SetSPName03}
						,{"SPPost"			,"1","1",""		,SetSPPost}
						,{"SPAdd01"			,"1","1",""		,SetSPAdd01}
						,{"SPAdd02"			,"1","1",""		,SetSPAdd02}
						,{"SPAdd03"			,"1","1",""		,SetSPAdd03}
						,{"SPTel"			,"1","1",""		,SetSPTel}
						,{"SPFax"			,"1","1",""		,SetSPFax}
						,{"SPMail"			,"1","1",""		,SetSPMail}
						,{"Com01"			,"1","1",""		,SetCom01}
						,{"Com02"			,"1","1",""		,SetCom02}
						,{"Com03"			,"1","1",""		,SetCom03}
						,{"PTMSCDBMN"		,"1","1",""		,SetPTMSCDBMN}
						,{"PTMSCDNINUSHI"	,"1","1",""		,SetPTMSCDNINUSHI}
						,{"PaySite"			,"1","1",""		,SetPaySite}
						,{"PayDate"			,"1","1",""		,SetPayDate}
						,{"ShimeDate"		,"1","1",""		,SetShimeDate}
						,{"DECD"			,"1","1",""		,SetDECD}
						,{"DepartmentCd"	,"1","1",""		,SetDepartmentCd}
						,{"EntryDate"		,"1","0",""		,SetEntryDate}
						,{"UpdateDate"		,"1","1",""		,SetUpdateDate}
						,{"EntryUser"		,"1","0",""		,SetEntryUser}
						,{"UpdateUser"		,"1","1",""		,SetUpdateUser}
						};
		
		
		
	}
	
	private static String[] TxtCheck(
			String GetClWh,
			String GetClCd,
			String GetSPCd,
			String GetSPName01,
			String GetSPName02,
			String GetSPName03,
			String GetSPPost,
			String GetSPAdd01,
			String GetSPAdd02,
			String GetSPAdd03,
			String GetSPTel,
			String GetSPFax,
			String GetSPMail,
			String GetCom01,
			String GetCom02,
			String GetCom03,
			String GetPTMSCDBMN,
			String GetPTMSCDNINUSHI,
			String GetPaySite,
			String GetPayDate,
			String GetShimeDate,
			String GetDECD,
			String GetDepartmentCd,
			int BackClWh,
			int BackClCd,
			int BackSPCd,
			int BackSPName01,
			int BackSPName02,
			int BackSPName03,
			int BackSPPost,
			int BackSPAdd01,
			int BackSPAdd02,
			int BackSPAdd03,
			int BackSPTel,
			int BackSPFax,
			int BackSPMail,
			int BackCom01,
			int BackCom02,
			int BackCom03,
			int BackPTMSCDBMN,
			int BackPTMSCDNINUSHI,
			int BackPaySite,
			int BackPayDate,
			int BackShimeDate,
			int BackDECD,
			int BackDepartmentCd
			) {
		String[] Rt = new String[23];
		if(null==GetClWh			){GetClWh			= "";}
		if(null==GetClCd			){GetClCd			= "";}
		if(null==GetSPCd			){GetSPCd			= "";}
		if(null==GetSPName01		){GetSPName01		= "";}
		if(null==GetSPName02		){GetSPName02		= "";}
		if(null==GetSPName03		){GetSPName03		= "";}
		if(null==GetSPPost			){GetSPPost			= "";}
		if(null==GetSPAdd01			){GetSPAdd01		= "";}
		if(null==GetSPAdd02			){GetSPAdd02		= "";}
		if(null==GetSPAdd03			){GetSPAdd03		= "";}
		if(null==GetSPTel			){GetSPTel			= "";}
		if(null==GetSPFax			){GetSPFax			= "";}
		if(null==GetSPMail			){GetSPMail			= "";}
		if(null==GetCom01			){GetCom01			= "";}
		if(null==GetCom02			){GetCom02			= "";}
		if(null==GetCom03			){GetCom03			= "";}
		if(null==GetPTMSCDBMN		){GetPTMSCDBMN		= "";}
		if(null==GetPTMSCDNINUSHI	){GetPTMSCDNINUSHI	= "";}
		if(null==GetPaySite			){GetPaySite		= "";}
		if(null==GetPayDate			){GetPayDate		= "";}
		if(null==GetShimeDate		){GetShimeDate		= "";}
		if(null==GetDECD			){GetDECD			= "";}
		if(null==GetDepartmentCd	){GetDepartmentCd	= "";}
		
		GetClWh				= B00020ToolsTextControl.Trim(GetClWh);
		GetClCd				= B00020ToolsTextControl.Trim(GetClCd);
		GetSPCd				= B00020ToolsTextControl.Trim(GetSPCd);
		GetSPName01			= B00020ToolsTextControl.Trim(GetSPName01);
		GetSPName02			= B00020ToolsTextControl.Trim(GetSPName02);
		GetSPName03			= B00020ToolsTextControl.Trim(GetSPName03);
		GetSPPost			= B00020ToolsTextControl.Trim(GetSPPost);
		GetSPAdd01			= B00020ToolsTextControl.Trim(GetSPAdd01);
		GetSPAdd02			= B00020ToolsTextControl.Trim(GetSPAdd02);
		GetSPAdd03			= B00020ToolsTextControl.Trim(GetSPAdd03);
		GetSPTel			= B00020ToolsTextControl.Trim(GetSPTel);
		GetSPFax			= B00020ToolsTextControl.Trim(GetSPFax);
		GetSPMail			= B00020ToolsTextControl.Trim(GetSPMail);
		GetCom01			= B00020ToolsTextControl.Trim(GetCom01);
		GetCom02			= B00020ToolsTextControl.Trim(GetCom02);
		GetCom03			= B00020ToolsTextControl.Trim(GetCom03);
		GetPTMSCDBMN		= B00020ToolsTextControl.Trim(GetPTMSCDBMN);
		GetPTMSCDNINUSHI	= B00020ToolsTextControl.Trim(GetPTMSCDNINUSHI);
		GetPaySite			= B00020ToolsTextControl.Trim(GetPaySite);
		GetPayDate			= B00020ToolsTextControl.Trim(GetPayDate);
		GetShimeDate		= B00020ToolsTextControl.Trim(GetShimeDate);
		GetDECD				= B00020ToolsTextControl.Trim(GetDECD);
		GetDepartmentCd		= B00020ToolsTextControl.Trim(GetDepartmentCd);
		
		GetSPTel			= B00020ToolsTextControl.num_only_String(GetSPTel);
		GetSPFax			= B00020ToolsTextControl.num_only_String(GetSPFax);
		
		GetPaySite			= B00020ToolsTextControl.num_only_String02(GetPaySite);
		GetPayDate			= B00020ToolsTextControl.num_only_String02(GetPayDate);
		GetShimeDate		= B00020ToolsTextControl.num_only_String02(GetShimeDate);
		
		if("".equals(GetPaySite)	) {GetPaySite	="1";}
		if("".equals(GetPayDate)	) {GetPayDate	="99";}
		if("".equals(GetShimeDate)	) {GetShimeDate	="99";}
		
		float WFT = (float)0;
		GetPaySite			= ""+(int)(Float.parseFloat(GetPaySite));
		GetPayDate			= ""+(int)(Float.parseFloat(GetPayDate));
		GetShimeDate		= ""+(int)(Float.parseFloat(GetShimeDate));
		
		Rt[BackClWh]			= GetClWh;
		Rt[BackClCd]			= GetClCd;
		Rt[BackSPCd]			= GetSPCd;
		Rt[BackSPName01]		= GetSPName01;
		Rt[BackSPName02]		= GetSPName02;
		Rt[BackSPName03]		= GetSPName03;
		Rt[BackSPPost]			= GetSPPost;
		Rt[BackSPAdd01]			= GetSPAdd01;
		Rt[BackSPAdd02]			= GetSPAdd02;
		Rt[BackSPAdd03]			= GetSPAdd03;
		Rt[BackSPTel]			= GetSPTel;
		Rt[BackSPFax]			= GetSPFax;
		Rt[BackSPMail]			= GetSPMail;
		Rt[BackCom01]			= GetCom01;
		Rt[BackCom02]			= GetCom02;
		Rt[BackCom03]			= GetCom03;
		Rt[BackPTMSCDBMN]		= GetPTMSCDBMN;
		Rt[BackPTMSCDNINUSHI]	= GetPTMSCDNINUSHI;
		Rt[BackPaySite]			= GetPaySite;
		Rt[BackPayDate]			= GetPayDate;
		Rt[BackShimeDate]		= GetShimeDate;
		Rt[BackDECD]			= GetDECD;
		Rt[BackDepartmentCd]	= GetDepartmentCd;
		
		return Rt;
	}
	
	
	
	private static ArrayList<String> ErrCheck(Object[][] CheckOb,String[] TableCol){
		ArrayList<String> ErrMsg = new ArrayList<String>();
		
		return ErrMsg;
	}
	
	private static void ErrView(ArrayList<String>ErrMsg) {
		//必要フォルダを生成する
		String FLD_PATH = A00000Main.MainFLD+"\\MstControl";
		B00040ToolsFolderCheck.FLD_CHECK(FLD_PATH);
		FLD_PATH = A00000Main.MainFLD+"\\MstControl\\SupplierMst";
		B00040ToolsFolderCheck.FLD_CHECK(FLD_PATH);
		FLD_PATH = A00000Main.MainFLD+"\\MstControl\\SupplierMst\\Err";
		B00040ToolsFolderCheck.FLD_CHECK(FLD_PATH);
		FLD_PATH = A00000Main.MainFLD+"\\MstControl\\SupplierMst\\BK";
		B00040ToolsFolderCheck.FLD_CHECK(FLD_PATH);
		
		//ファイルに出力
		String NowDTM=B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[1].replace(" ", "").replace("/", "").replace(":", "");
		
		FLD_PATH = A00000Main.MainFLD+"\\MstControl\\SupplierMst\\Err";
		
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