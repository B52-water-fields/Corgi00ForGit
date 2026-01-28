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
		
		String[] NeedCol = {
				 "荷主グループCD"
				,"荷主グループ名"
				,"荷主届先CD"
				,"届先CD"
				,"届先部署CD"
				,"送り状登録名"
				,"コメント01"
				,"コメント02"
				,"コメント03"
				,"コメント04"
				,"コメント05"
				,"削除区分"
				,"届先マスタ優先フラグ"
				,"届先名1"
				,"届先名2"
				,"届先名3"
				,"届先郵便"
				,"届先住所1"
				,"届先住所2"
				,"届先住所3"
				,"届先電話"
				,"届先FAX"
				,"届先MAIL"
				};

		int[] TgtCol = {
					 -1	//荷主グループCD
					,-1	//荷主グループ名
					,-1	//荷主届先CD
					,-1	//届先CD
					,-1	//届先部署CD
					,-1	//送り状登録名
					,-1	//コメント01
					,-1	//コメント02
					,-1	//コメント03
					,-1	//コメント04
					,-1	//コメント05
					,-1	//削除区分
					,-1	//届先マスタ優先フラグ
					,-1	//届先名1
					,-1	//届先名2
					,-1	//届先名3
					,-1	//届先郵便
					,-1	//届先住所1
					,-1	//届先住所2
					,-1	//届先住所3
					,-1	//届先電話
					,-1	//届先FAX
					,-1	//届先MAIL
					};
		
		JLabel LB_SheetList	= B00110FrameParts.JLabelSet(	10, 40,600,20,"以下のデータを登録しようとしています※データ内の重複はチェックしません",11,0);
		main_fm.add(LB_SheetList);
		
		String[] columnNames01 = new String[NeedCol.length+2];
		columnNames01[0] = "Fg";
		for(int i=0;i<NeedCol.length;i++) {columnNames01[i+1] = NeedCol[i];}
		columnNames01[NeedCol.length+1] = "届先Mst存在Fg";	//登録更新しない場合0　新規登録1　更新2 エラー9
		
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
		column = columnModel01.getColumn( 1);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//荷主グループCD
		column = columnModel01.getColumn( 2);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//荷主グループ名
		column = columnModel01.getColumn( 3);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//荷主届先CD
		column = columnModel01.getColumn( 4);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//届先CD
		column = columnModel01.getColumn( 5);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//届先部署CD
		column = columnModel01.getColumn( 6);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//送り状登録名
		column = columnModel01.getColumn( 7);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//コメント01
		column = columnModel01.getColumn( 8);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//コメント02
		column = columnModel01.getColumn( 9);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//コメント03
		column = columnModel01.getColumn(10);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//コメント04
		column = columnModel01.getColumn(11);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//コメント05
		column = columnModel01.getColumn(12);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//削除区分
		column = columnModel01.getColumn(13);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//届先マスタ優先フラグ
		column = columnModel01.getColumn(14);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//届先名1
		column = columnModel01.getColumn(15);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//届先名2
		column = columnModel01.getColumn(16);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//届先名3
		column = columnModel01.getColumn(17);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//届先郵便
		column = columnModel01.getColumn(18);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//届先住所1
		column = columnModel01.getColumn(19);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//届先住所2
		column = columnModel01.getColumn(20);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//届先住所3
		column = columnModel01.getColumn(21);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//届先電話
		column = columnModel01.getColumn(22);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//届先FAX
		column = columnModel01.getColumn(23);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//届先MAIL
		column = columnModel01.getColumn(24);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//届先Mst存在Fg
		
		JScrollPane scpn01 = B00110FrameParts.JScrollPaneSet(10,65,800,325,tb01);
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
													+"荷主グループCD,荷主グループ名,荷主届先CD,届先CD,届先部署CD,送り状登録名\n"
													+"コメント01,コメント02,コメント03,コメント04,コメント05,削除区分,届先マスタ優先フラグ\n"
													+"届先名1,届先名2,届先名3,届先郵便,届先住所1,届先住所2,届先住所3,届先電話,届先FAX,届先MAIL\n"
													+"がヘッダに必要です");
			DeliveryComversionMstExcelEntry(0,0,TgtFilePath);
		}else {
			int[] ClmnType = new int[HeaderRead[0].length];
			for(int i=0;i<ClmnType.length;i++) {ClmnType[i]=1;}
			
			ClmnType[TgtCol[ 0]]=1;	//荷主グループCD
			ClmnType[TgtCol[ 1]]=1;	//荷主グループ名
			ClmnType[TgtCol[ 2]]=1;	//荷主届先CD
			ClmnType[TgtCol[ 3]]=1;	//届先CD
			ClmnType[TgtCol[ 4]]=1;	//届先部署CD
			ClmnType[TgtCol[ 5]]=1;	//送り状登録名
			ClmnType[TgtCol[ 6]]=1;	//コメント01
			ClmnType[TgtCol[ 7]]=1;	//コメント02
			ClmnType[TgtCol[ 8]]=1;	//コメント03
			ClmnType[TgtCol[ 9]]=1;	//コメント04
			ClmnType[TgtCol[10]]=1;	//コメント05
			ClmnType[TgtCol[11]]=1;	//削除区分
			ClmnType[TgtCol[12]]=1;	//届先マスタ優先フラグ
			ClmnType[TgtCol[13]]=1;	//届先名1
			ClmnType[TgtCol[14]]=1;	//届先名2
			ClmnType[TgtCol[15]]=1;	//届先名3
			ClmnType[TgtCol[16]]=1;	//届先郵便
			ClmnType[TgtCol[17]]=1;	//届先住所1
			ClmnType[TgtCol[18]]=1;	//届先住所2
			ClmnType[TgtCol[19]]=1;	//届先住所3
			ClmnType[TgtCol[20]]=1;	//届先電話
			ClmnType[TgtCol[21]]=1;	//届先FAX
			ClmnType[TgtCol[22]]=1;	//届先MAIL
			
			Object[][] ExcellRead = B00060ToolsExcellControl.ExcellRead(TgtFilePath,SheetName,ClmnType,true);
			ArrayList<String> ErrMsg = new ArrayList<String>();
			
			if(0<ExcellRead.length&&ClmnType.length<=ExcellRead[0].length) {
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
				
				//届先コードが一致する届先マスタ取得する
				for(int i=0;i<ExcellRead.length;i++) {
					Object[] SetOb = new Object[NeedCol.length+1];
					if(!"".equals(""+ExcellRead[i][TgtCol[ 0]])&&!"".equals(""+ExcellRead[i][TgtCol[ 2]])) {
						SearchDECD.add(""+ExcellRead[i][TgtCol[ 3]]);
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
				
				
				for(int i=0;i<ExcellRead.length;i++) {
					boolean NonErrFgb = true;
					Object[] SetOb = new Object[NeedCol.length+2];
					if(!"".equals(""+ExcellRead[i][TgtCol[ 0]])&&!"".equals(""+ExcellRead[i][TgtCol[ 2]])) {
						SetOb[ 0] = false;
						SetOb[ 1] = ""+ExcellRead[i][TgtCol[ 0]];	//荷主グループCD
						SetOb[ 2] = ""+ExcellRead[i][TgtCol[ 1]];	//荷主グループ名
						SetOb[ 3] = ""+ExcellRead[i][TgtCol[ 2]];	//荷主届先CD
						SetOb[ 4] = ""+ExcellRead[i][TgtCol[ 3]];	//届先CD
						SetOb[ 5] = ""+ExcellRead[i][TgtCol[ 4]];	//届先部署CD
						SetOb[ 6] = ""+ExcellRead[i][TgtCol[ 5]];	//送り状登録名
						SetOb[ 7] = ""+ExcellRead[i][TgtCol[ 6]];	//コメント01
						SetOb[ 8] = ""+ExcellRead[i][TgtCol[ 7]];	//コメント02
						SetOb[ 9] = ""+ExcellRead[i][TgtCol[ 8]];	//コメント03
						SetOb[10] = ""+ExcellRead[i][TgtCol[ 9]];	//コメント04
						SetOb[11] = ""+ExcellRead[i][TgtCol[10]];	//コメント05
						SetOb[12] = ""+ExcellRead[i][TgtCol[11]];	//削除区分
						SetOb[13] = ""+ExcellRead[i][TgtCol[12]];	//届先マスタ優先フラグ
						SetOb[14] = ""+ExcellRead[i][TgtCol[13]];	//届先名1
						SetOb[15] = ""+ExcellRead[i][TgtCol[14]];	//届先名2
						SetOb[16] = ""+ExcellRead[i][TgtCol[15]];	//届先名3
						SetOb[17] = ""+ExcellRead[i][TgtCol[16]];	//届先郵便
						SetOb[18] = ""+ExcellRead[i][TgtCol[17]];	//届先住所1
						SetOb[19] = ""+ExcellRead[i][TgtCol[18]];	//届先住所2
						SetOb[20] = ""+ExcellRead[i][TgtCol[19]];	//届先住所3
						SetOb[21] = ""+ExcellRead[i][TgtCol[20]];	//届先電話
						SetOb[22] = ""+ExcellRead[i][TgtCol[21]];	//届先FAX
						SetOb[23] = ""+ExcellRead[i][TgtCol[22]];	//届先MAIL
						SetOb[24] = "0";//届先Mst存在Fg  1:新規　2:更新
						
						//届先名1が空白であれば届先コード・部署コード必須
						boolean DeliRenewCheckFg = false;
						if(("").equals(""+SetOb[14])	//届先名1
								) {
							if("".equals(""+SetOb[ 4]) || "".equals(""+SetOb[ 5])) {
								SetOb[23] = "9";//届先Mst存在Fg
								int wint = i+1;
								ErrMsg.add(wint+"行目エラー:"+"届先CD("+ExcellRead[i][TgtCol[ 3]]+") 部署CD("+ExcellRead[i][TgtCol[ 4]]+")で設定しようとしていますが設定必須です");
							}else {
								
							}
						}else {
							DeliRenewCheckFg = true;
						}
						
						int HitCount = 0;
						if(DeliRenewCheckFg) {
							//届先CDコード空白なら届先新規登録扱い確定
							if("".equals(""+ExcellRead[i][TgtCol[ 3]])) {
								SetOb[24] = "1";//届先Mst存在Fg
							}else {
								//存在しない届先CDコード設定しようとしていた場合新規登録扱い
								boolean UnHitFg = true;
								for(int i01=0;i01<DeliveryMstRt.length;i01++) {
									if((""+DeliveryMstRt[i01][ 0]).equals(""+ExcellRead[i][TgtCol[ 3]])) {
										UnHitFg = false;
										HitCount = HitCount+1;
										if((""+DeliveryMstRt[i01][ 1]).equals(""+ExcellRead[i][TgtCol[ 4]])) {
											SetOb[24] = "2";//届先Mst存在Fg
										}
									}
								}
								if(UnHitFg) {
									SetOb[24] = "1";//届先Mst存在Fg
								}
							}
							
							if("0".equals(""+SetOb[24])&&!("").equals(""+ExcellRead[i][TgtCol[13]])) {
								for(int i01=0;i01<DeliveryMstRt.length;i01++) {
									if((""+DeliveryMstRt[i01][ 0]).equals(""+ExcellRead[i][TgtCol[ 3]])) {
										if(1==HitCount) {
											
										}
									}
								}
							}
						}
						
						tableModel_ms01.addRow(SetOb);
					}
				}
			}
			JLabel MSG				= B00110FrameParts.JLabelSet(  10,400,300,20,"選択行をメンテナンスする",11,0);
			main_fm.add(MSG);
			
			/*
			JLabel LB_ClGpCD"				,(int) 0	,"String"	,"荷主グループCD"}
			JLabel LB_CLGpName01"			,(int) 1	,"String"	,"荷主グループ名"}
			JLabel LB_CL_DECD"				,(int) 2	,"String"	,"荷主届先CD"}
			JLabel LB_DECD"				,(int) 3	,"String"	,"届先CD"}
			JLabel LB_DepartmentCd"		,(int) 4	,"String"	,"届先部署CD"}
			JLabel LB_DEName01"			,(int) 5	,"String"	,"届先名1"}
			JLabel LB_DEName02"			,(int) 6	,"String"	,"届先名2"}
			JLabel LB_DEName03"			,(int) 7	,"String"	,"届先名3"}
			JLabel LB_Post"				,(int) 8	,"String"	,"届先郵便"}
			JLabel LB_Add01"				,(int) 9	,"String"	,"届先住所1"}
			JLabel LB_Add02"				,(int)10	,"String"	,"届先住所2"}
			JLabel LB_Add03"				,(int)11	,"String"	,"届先住所3"}
			JLabel LB_Tel"					,(int)12	,"String"	,"届先電話"}
			JLabel LB_Fax"					,(int)13	,"String"	,"届先FAX"}
			JLabel LB_Mail"				,(int)14	,"String"	,"届先MAIL"}
			JLabel LB_SetName"				,(int)15	,"String"	,"送り状登録名"}
			JLabel LB_Com01"				,(int)16	,"String"	,"コメント01"}
			JLabel LB_Com02"				,(int)17	,"String"	,"コメント02"}
			JLabel LB_Com03"				,(int)18	,"String"	,"コメント03"}
			JLabel LB_Com04"				,(int)19	,"String"	,"コメント04"}
			JLabel LB_Com05"				,(int)20	,"String"	,"コメント05"}
			JLabel LB_EntryDate"			,(int)21	,"String"	,"データ登録日時"}
			JLabel LB_UpdateDate"			,(int)22	,"String"	,"データ更新日時"}
			JLabel LB_EntryUser"			,(int)23	,"String"	,"登録者コード"}
			JLabel LB_UpdateUser"			,(int)24	,"String"	,"更新者コード"}
			JLabel LB_DelFg"				,(int)25	,"int"		,"削除区分"}
			JLabel LB_MstPriorityFirstFg"	,(int)26	,"int"		,"届先マスタ優先フラグ"}
			
			*/
			
			
			
			
			if(null==ErrMsg||0==ErrMsg.size()) {
				main_fm.add(entry_btn);
			}else {
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
				
				//ファイル開く
				File file = new File(ErrFP);
				Desktop desktop = Desktop.getDesktop();
				try {
					desktop.open(file);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			main_fm.setVisible(true);
			
			
			
			
			
			
			
			
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
		}
		RenewFg = true;
		
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
}