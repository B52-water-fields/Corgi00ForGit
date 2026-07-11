import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class WM100_PostMst_00_Search{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	public static void PostMstSearch(int x,int y) {
		A00000_Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;

		final JFrame main_fm = B100_FrameParts.FrameCreate(x,y,780,750,"Corgi00郵便番号検索","");
		JLabel userinfo = B100_FrameParts.UserInfo();
		JButton exit_btn = B100_FrameParts.ExitBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		
		//検索条件パネル
		JPanel PN_Search = B100_FrameParts.JPanelSet(10,40,740,100,"White");
		JLabel PN_SearchLabel = B100_FrameParts.JLabelSet(10,0,150,20,"検索条件",11,0);
		PN_Search.add(PN_SearchLabel);
		main_fm.add(PN_Search);
		
		//検索条件
		JLabel LB_SearchPOST  = B100_FrameParts.JLabelSet(	                  0,25,100,20,"郵便番号:",11,1);
		JLabel LB_SearchAdd   = B100_FrameParts.JLabelSet(	                  0,50,100,20,"住所:"    ,11,1);
		final JTextField TB_SearchPOST  = B100_FrameParts.JTextFieldSet(		100,25,100,20,"",11,0);
		final JTextField TB_SearchAdd   =B100_FrameParts.JTextFieldSet(		100,50,100,20,"",11,0);
		JLabel LB2_SearchPOST  = B100_FrameParts.JLabelSet(	                200,25,100,20,"で始まる" ,11,0);
		JLabel LB2_SearchAdd   = B100_FrameParts.JLabelSet(	                200,50,100,20,"を含む"   ,11,0);
		
		PN_Search.add(LB_SearchPOST);
		PN_Search.add(LB_SearchAdd);
		PN_Search.add(TB_SearchPOST);
		PN_Search.add(TB_SearchAdd);
		PN_Search.add(LB2_SearchPOST);
		PN_Search.add(LB2_SearchAdd);
		
		//検索ボタン
		JButton SearchBtn = B100_FrameParts.BtnSet(100,75,100,20,"検索",11);
		PN_Search.add(SearchBtn);
		
		//エクセル取込時に先頭行columnNames01とヘッダ行の名称一致でレイアウト確認＆取込列判定します
		//"郵便番号","県","市区町村","町丁目","市区町村CD"との名称一致をみて列判定するので名称重複＆不足しないでください
		Object[][] RtSettingPostRt = M100_PostMstRt.RtSettingPostRt();
		
		String[] columnNames01 = new String[RtSettingPostRt.length+1];
		
		columnNames01[0] = "Fg";
		for(int i=0;i<RtSettingPostRt.length;i++) {
			columnNames01[1+(int)RtSettingPostRt[i][1]] = ""+RtSettingPostRt[i][3];
		}
		
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
		for(int i=0;i<RtSettingPostRt.length;i++) {
			if("int".equals((String)RtSettingPostRt[i][2])||"float".equals((String)RtSettingPostRt[i][2])) {
				column = columnModel01.getColumn(1+(int)RtSettingPostRt[i][1]);	column.setPreferredWidth(150*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.rightCellRenderer());
			}else {
				column = columnModel01.getColumn(1+(int)RtSettingPostRt[i][1]);	column.setPreferredWidth(150*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.leftCellRenderer());
			}
		}
		
		//スクロール用設定
		JScrollPane scpn01 = B100_FrameParts.JScrollPaneSet(10,160,740,460,tb01);
		main_fm.add(scpn01);
		
		//CSVボタン
		JButton CsvBtn = B100_FrameParts.BtnSet(10,660,100,20,"csv出力",11);
		main_fm.add(CsvBtn);
		
		JLabel LB_RenewBtn  = B100_FrameParts.JLabelSet( 130,640,100,20,"チェック行を" ,11,2);
		main_fm.add(LB_RenewBtn);
		
		//修正ボタン
		JButton RenewBtn = B100_FrameParts.BtnSet(		130,660,100,20,"修正",11);
		main_fm.add(RenewBtn);
		
		//新規登録ボタン
		JButton CreateBtn = B100_FrameParts.BtnSet(		250,660,100,20,"新規登録",11);
		main_fm.add(CreateBtn);
		
		//一括登録ボタン
		JButton CreateSumBtn = B100_FrameParts.BtnSet(	370,660,100,20,"一括登録",11);
		main_fm.add(CreateSumBtn);
		
		//JIS⇒届先ボタン
		JButton JisToDeliveryBtn = B100_FrameParts.BtnSet(490,660,100,20,"JIS⇒届先Mst",10);
		main_fm.add(JisToDeliveryBtn);
		
		//エクセル出力ボタン
		JButton ExcelBtn = B100_FrameParts.BtnSet(	610,660,100,20,"Excel出力",11);
		main_fm.add(ExcelBtn);
		
		//エクセル取込ボタン
		JButton ExcelInBtn = B100_FrameParts.BtnSet(	610,635,100,20,"Excel取込",11);
		main_fm.add(ExcelInBtn);
		
		main_fm.setVisible(true);
		
		RenewFg = true;
		
		//検索ボタン押下時の挙動
		SearchBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					int RowCount = MainFmTableModel.getRowCount();
					for(int i=0;i<RowCount;i++) {
						MainFmTableModel.removeRow(0);
					}
					
					String GetSearchPOST = TB_SearchPOST.getText();	if(null==GetSearchPOST) {GetSearchPOST="";}
					String GetSearchAdd = TB_SearchAdd.getText();	if(null==GetSearchAdd) {GetSearchAdd="";}
					
					if(null==GetSearchPOST	){GetSearchPOST = "";}
					if(null==GetSearchAdd	){GetSearchAdd 	= "";}
					
					GetSearchPOST	= B100_TextControl.Trim(GetSearchPOST);
					GetSearchAdd	= B100_TextControl.Trim(GetSearchAdd);
					
					GetSearchPOST			= B100_TextControl.num_only_String(GetSearchPOST);
					
					TB_SearchPOST.setText(GetSearchPOST);
					TB_SearchAdd.setText(GetSearchAdd);
					
					ArrayList<String> SearchPOST = new ArrayList<String>();
					ArrayList<String> SearchAdd = new ArrayList<String>();
					boolean AllSearch = true;
					boolean PostPerfectMatch = false;
					
					if(!"".equals(GetSearchPOST)) {
						SearchPOST.add(GetSearchPOST);
					}
					
					if(!"".equals(GetSearchAdd)) {
						SearchAdd.add(GetSearchAdd);
					}
					
					Object[][] PostRt = M100_PostMstRt.PostRt(SearchPOST,SearchAdd,AllSearch,PostPerfectMatch);
					Object[][] RtSettingPostRt = M100_PostMstRt.RtSettingPostRt();
					for(int i=0;i<PostRt.length;i++) {
						Object[] SetOb = new Object[RtSettingPostRt.length+1];
						SetOb[0] = false;
						for(int i01=0;i01<RtSettingPostRt.length;i01++) {
							SetOb[1+(int)RtSettingPostRt[i01][1]]	= PostRt[i][(int)RtSettingPostRt[i01][1]];
						}
						MainFmTableModel.addRow(SetOb);
					}
					if(0<PostRt.length) {
						B100_TableControl.AddSortON(tb01,MainFmTableModel);
					}else {
						B100_TableControl.AddSortOFF(tb01,MainFmTableModel);
					}
					RenewFg = true;
				}
			}
		});
		
		//修正ボタン押下時の挙動
		RenewBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					int RowCount = MainFmTableModel.getRowCount();
					String TgtPost = "";
					for(int i=0;i<RowCount;i++) {
						if((boolean)MainFmTableModel.getValueAt(i, 0)) {
							TgtPost = ""+MainFmTableModel.getValueAt(i, 1+M100_PostMstRt.ColPOST);	if(null==TgtPost) {TgtPost="";}
						}
					}
					if(!"".equals(TgtPost)) {
						SetX=main_fm.getX();
						SetY=main_fm.getY();

						main_fm.setVisible(false);
						main_fm.dispose();
						WM100_PostMst_01_RenewAndCreate.PostMstRenewAndCreate(0,0,TgtPost);
					}
					RenewFg = true;
				}
			}
		});
		
		//新規登録ボタン押下時の挙動
		CreateBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					
					SetX=main_fm.getX();
					SetY=main_fm.getY();

					main_fm.setVisible(false);
					main_fm.dispose();
					WM100_PostMst_01_RenewAndCreate.PostMstRenewAndCreate(0,0,"");
					
					RenewFg = true;
				}
			}
		});
		
		//一括登録ボタン押下時の挙動
		CreateSumBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					
					SetX=main_fm.getX();
					SetY=main_fm.getY();

					main_fm.setVisible(false);
					main_fm.dispose();
					WM100_PostMst_03_CreateSum.PostMstCreateSum(0,0);
					
					RenewFg = true;
				}
			}
		});
		
		//チェックボックス操作時の挙動
		MainFmTableModel.addTableModelListener(new TableModelListener(){
			public void tableChanged(TableModelEvent e){
				if(RenewFg) {
					RenewFg = false;
					int row_count = tb01.getRowCount();
					Boolean setBL=Boolean.valueOf(false);
					for(int i=0;i<row_count;i++){
						if(i!=e.getFirstRow()){
							MainFmTableModel.setValueAt(setBL, i, 0);
						}else {
	
						}
					}
					RenewFg = true;
				}
			}
		});
		
		//CSVボタン押下時の挙動
		CsvBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					B100_TableControl.TableOutPutCsv("出力先選択","郵便番号検索結果",tb01);
					RenewFg = true;
				}
			}
		});
		//エクセル出力ボタン押下時の挙動
		ExcelBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					B100_TableControl.TableOutPutExcel("出力先選択","郵便番号検索結果",tb01);
					RenewFg = true;
				}
			}
		});
		
		//エクセル取込ボタン
		ExcelInBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					String MSG = "エクセルファイル選択";
					String[] file_type = {".xlsx"};
					String file_type_name = "エクセルファイル";
					String Selected = B100_FileSelect.FileSelect(MSG,file_type,file_type_name);
					
					if(null!=Selected && !Selected.equals(Selected.replace(".xlsx", ""))) {
						
						SetX=main_fm.getX();
						SetY=main_fm.getY();

						main_fm.setVisible(false);
						main_fm.dispose();
						WM100_PostMst_02_ExcelEntry.PostMstExcelEntry(0,0,Selected);
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
				A00001_MstMain.MstMain(0, 0);
			}
		});
		
		//JIS⇒届先ボタン
		//JISコードベースで届先マスタに登録する
		JisToDeliveryBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					
					ArrayList<String> SearchName = new ArrayList<String>();
					ArrayList<String> SearchMunicipalityCd = new ArrayList<String>();
					boolean AllSearch = true;
					Object[][] MunicipalityRt = M100_PostMstRt.MunicipalityRt(SearchName,SearchMunicipalityCd,AllSearch);
					
					String now_dtm = B100_DateTimeControl.dtmString2(B100_DateTimeControl.dtm()[1])[1];
					
					String[] SetDECD			= new String[MunicipalityRt.length];	//納品先コード
					String[] SetDepartmentCd	= new String[MunicipalityRt.length];	//部署CD
					String[] SetDEName01		= new String[MunicipalityRt.length];	//納品先名1
					String[] SetDEName02		= new String[MunicipalityRt.length];	//納品先名2
					String[] SetDEName03		= new String[MunicipalityRt.length];	//納品先名3
					String[] SetPost			= new String[MunicipalityRt.length];	//納品先郵便
					String[] SetAdd01			= new String[MunicipalityRt.length];	//納品先住所1
					String[] SetAdd02			= new String[MunicipalityRt.length];	//納品先住所2
					String[] SetAdd03			= new String[MunicipalityRt.length];	//納品先住所3
					String[] SetTel				= new String[MunicipalityRt.length];	//納品先電話
					String[] SetFax				= new String[MunicipalityRt.length];	//納品先FAX
					String[] SetMail			= new String[MunicipalityRt.length];	//納品先MAIL
					String[] SetCom01			= new String[MunicipalityRt.length];	//コメント1
					String[] SetCom02			= new String[MunicipalityRt.length];	//コメント2
					String[] SetCom03			= new String[MunicipalityRt.length];	//コメント3
					String[] SetPrefecturesCd	= new String[MunicipalityRt.length];	//JIS県CD2桁
					String[] SetMunicipalityCd	= new String[MunicipalityRt.length];	//JIS市区町村CD5桁
					String[] SetPTMSCD			= new String[MunicipalityRt.length];	//基幹システム発着地コード
					String[] SetEntryDate		= new String[MunicipalityRt.length];	//データ登録日時
					String[] SetUpdateDate		= new String[MunicipalityRt.length];	//データ更新日時
					String[] SetEntryUser		= new String[MunicipalityRt.length];	//登録者コード
					String[] SetUpdateUser		= new String[MunicipalityRt.length];	//更新者コード
					String[] SetFirstClient		= new String[MunicipalityRt.length];	//登録した荷主CD
					String[] SetLastClient		= new String[MunicipalityRt.length];	//更新した荷主CD
					String[] SetDelFg			= new String[MunicipalityRt.length];	//削除区分
					
					for(int i=0;i<MunicipalityRt.length;i++){SetDECD[i]				= "JIS"+MunicipalityRt[i][M100_PostMstRt.ColMunicipalityRtMUNICIPALITY_CD];}
					for(int i=0;i<MunicipalityRt.length;i++){SetDepartmentCd[i]		= "JIS";}
					for(int i=0;i<MunicipalityRt.length;i++){SetDEName01[i]			= "" + MunicipalityRt[i][M100_PostMstRt.ColMunicipalityRtPREFECTURES] + MunicipalityRt[i][M100_PostMstRt.ColMunicipalityRtMUNICI01];}
					for(int i=0;i<MunicipalityRt.length;i++){SetDEName02[i]			= "";}
					for(int i=0;i<MunicipalityRt.length;i++){SetDEName03[i]			= "";}
					for(int i=0;i<MunicipalityRt.length;i++){SetPost[i]				= "";}
					for(int i=0;i<MunicipalityRt.length;i++){SetAdd01[i]			= "";}
					for(int i=0;i<MunicipalityRt.length;i++){SetAdd02[i]			= "";}
					for(int i=0;i<MunicipalityRt.length;i++){SetAdd03[i]			= "";}
					for(int i=0;i<MunicipalityRt.length;i++){SetTel[i]				= "";}
					for(int i=0;i<MunicipalityRt.length;i++){SetFax[i]				= "";}
					for(int i=0;i<MunicipalityRt.length;i++){SetMail[i]				= "";}
					for(int i=0;i<MunicipalityRt.length;i++){SetCom01[i]			= "";}
					for(int i=0;i<MunicipalityRt.length;i++){SetCom02[i]			= "";}
					for(int i=0;i<MunicipalityRt.length;i++){SetCom03[i]			= "";}
					for(int i=0;i<MunicipalityRt.length;i++){
						SetPrefecturesCd[i]	= "";
						if(2<=(""+MunicipalityRt[i][M100_PostMstRt.ColMunicipalityRtMUNICIPALITY_CD]).length()){
							SetPrefecturesCd[i]	= (""+MunicipalityRt[i][M100_PostMstRt.ColMunicipalityRtMUNICIPALITY_CD]).substring(0,2);
						}
					}
					for(int i=0;i<MunicipalityRt.length;i++){SetMunicipalityCd[i]	= ""+MunicipalityRt[i][M100_PostMstRt.ColMunicipalityRtMUNICIPALITY_CD];}
					for(int i=0;i<MunicipalityRt.length;i++){SetPTMSCD[i]			= ""+MunicipalityRt[i][M100_PostMstRt.ColMunicipalityRtMUNICIPALITY_CD];}
					for(int i=0;i<MunicipalityRt.length;i++){SetEntryDate[i]		= now_dtm;}
					for(int i=0;i<MunicipalityRt.length;i++){SetUpdateDate[i]		= now_dtm;}
					for(int i=0;i<MunicipalityRt.length;i++){SetEntryUser[i]		= "(" + A00000_Main.LoginUserId + ")" + A00000_Main.LoginUserName;}
					for(int i=0;i<MunicipalityRt.length;i++){SetUpdateUser[i]		= "(" + A00000_Main.LoginUserId + ")" + A00000_Main.LoginUserName;}
					for(int i=0;i<MunicipalityRt.length;i++){SetFirstClient[i]		= "" + A00000_Main.ClCd;}
					for(int i=0;i<MunicipalityRt.length;i++){SetLastClient[i]		= "" + A00000_Main.ClCd;}
					for(int i=0;i<MunicipalityRt.length;i++){SetDelFg[i]			= "0";}
					
					Object[][] SetString= {
							 		 {"DECD"			,"1","1","Key"	,SetDECD}			//納品先コード
									,{"DepartmentCd"	,"1","1","Key"	,SetDepartmentCd}	//部署CD
									,{"DEName01"		,"1","1",""		,SetDEName01}		//納品先名1
									,{"DEName02"		,"1","1",""		,SetDEName02}		//納品先名2
									,{"DEName03"		,"1","1",""		,SetDEName03}		//納品先名3
									,{"Post"			,"1","1",""		,SetPost}			//納品先郵便
									,{"Add01"			,"1","1",""		,SetAdd01}			//納品先住所1
									,{"Add02"			,"1","1",""		,SetAdd02}			//納品先住所2
									,{"Add03"			,"1","1",""		,SetAdd03}			//納品先住所3
									,{"Tel"				,"1","1",""		,SetTel}			//納品先電話
									,{"Fax"				,"1","1",""		,SetFax}			//納品先FAX
									,{"Mail"			,"1","1",""		,SetMail}			//納品先MAIL
									,{"Com01"			,"1","1",""		,SetCom01}			//コメント1
									,{"Com02"			,"1","1",""		,SetCom02}			//コメント2
									,{"Com03"			,"1","1",""		,SetCom03}			//コメント3
									,{"PrefecturesCd"	,"1","1",""		,SetPrefecturesCd}	//JIS県CD2桁
									,{"MunicipalityCd"	,"1","1",""		,SetMunicipalityCd}	//JIS市区町村CD5桁
									,{"PTMSCD"			,"1","1",""		,SetPTMSCD}			//基幹システム発着地コード
									,{"EntryDate"		,"1","0",""		,SetEntryDate}		//データ登録日時
									,{"UpdateDate"		,"1","1",""		,SetUpdateDate}		//データ更新日時
									,{"EntryUser"		,"1","0",""		,SetEntryUser}		//登録者コード
									,{"UpdateUser"		,"1","1",""		,SetUpdateUser}		//更新者コード
									,{"FirstClient"		,"1","0",""		,SetFirstClient}	//登録した荷主CD
									,{"LastClient"		,"1","1",""		,SetLastClient}		//更新した荷主CD
									,{"DelFg"			,"1","1",""		,SetDelFg}			//削除区分
									};
					
					String tgt_table = "KM0040_DELIVERYMST";
					String TgtDB = "NYANKO";
					int non_msg_fg = 0;
					
					A100_InsertUpdateSQL.InsertUpdateSomeRecord(SetString,tgt_table,TgtDB,non_msg_fg);
					
					RenewFg = true;
				}
			}
		});
	}
}