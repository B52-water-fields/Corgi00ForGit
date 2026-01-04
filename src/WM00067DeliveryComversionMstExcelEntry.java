import java.awt.Font;
import java.awt.event.ActionEvent;

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
		
		final JFrame main_fm = B00110FrameParts.FrameCreate(x,y,750,800,"Corgi00届先変換マスタ登録（エクセル）","");
		JLabel userinfo = B00110FrameParts.UserInfo();
		JButton exit_btn = B00110FrameParts.ExitBtn();
		JButton entry_btn = B00110FrameParts.EntryBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		main_fm.add(entry_btn);
		
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
		
		
		JScrollPane scpn01 = B00110FrameParts.JScrollPaneSet(10,65,700,325,tb01);
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
			
			if(0<ExcellRead.length&&ClmnType.length<=ExcellRead[0].length) {
				for(int i=0;i<ExcellRead.length;i++) {
					Object[] SetOb = new Object[NeedCol.length+1];
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
						SetOb[13] = ""+ExcellRead[i][TgtCol[12]];	//届先マスタ優先フラグ"
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
						
						tableModel_ms01.addRow(SetOb);
					}
				}
			}
			JLabel MSG				= B00110FrameParts.JLabelSet(  10,400,300,20,"選択行をメンテナンスする",11,0);
			main_fm.add(MSG);
			
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
				WM00065DeliveryComversionMstSerarch.DeliveryComversionMstSerarch(0, 0);
			}
		});
	}
}