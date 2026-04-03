import java.awt.Desktop;
import java.awt.Font;
import java.awt.Image;
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

public class WM00082ItemMstExcelEntry{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	public static void ItemMstExcelEntry(int x,int y,String TgtFilePath) {
		A00000Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;

		final JFrame main_fm = B00110FrameParts.FrameCreate(x,y,600,200,"Corgi00商品マスタ登録（エクセル）","");
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
				ItemMstExcelEntryMain(0,0,TgtFilePath,SheetName);
			}
		});
		
		//EXITボタン押下時の挙動
		exit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				WM00080ItemMstSearch.ItemMstSearch(0, 0);
			}
		});
	}
	
	public static void ItemMstExcelEntryMain(int x,int y,String TgtFilePath,String SheetName) {
		A00000Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		final JFrame main_fm = B00110FrameParts.FrameCreate(x,y,850,800,"Corgi00商品マスタ登録（エクセル）","");
		JLabel userinfo = B00110FrameParts.UserInfo();
		JButton exit_btn = B00110FrameParts.ExitBtn();
		JButton entry_btn = B00110FrameParts.EntryBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		main_fm.add(entry_btn);
		
		String[] NeedCol = {
					 "荷主グループコード"
					,"商品コード"
					,"荷主商品コード"
					,"商品名1"
					,"商品名2"
					,"商品名3"
					,"運送タイプコード01"
					,"運送タイプコード02"
					,"運送タイプコード03"
					,"運送タイプコード04"
					,"運送タイプコード05"
					,"基幹システム商品コード"
					,"カートン入数"
					,"ケース入数"
					,"パレット入数"
					,"JANCD"
					,"カートンバーコード"
					,"ケースバーコード"
					,"パレットバーコード"
					,"カートン商品名称"
					,"ケース商品名称"
					,"パレット商品名称"
					,"商品単位"
					,"カートン商品単位"
					,"ケース商品単位"
					,"パレット商品単位"
					,"商品重量"
					,"カートン重量"
					,"ケース重量"
					,"パレット重量"
					,"商品サイズ"
					,"カートンサイズ"
					,"ケースサイズ"
					,"パレットサイズ"
					,"推奨ロケ"
					,"商品モデル番号（型番）"
					,"商品カテゴリCD"
					,"商品カテゴリ名"
					,"商品カラーコード"
					,"商品カラー名"
					,"商品サイズコード"
					,"商品サイス名"
					,"コメント1"
					,"コメント2"
					,"コメント3"
					,"温度区分"
					,"画像パス01"
					,"画像パス02"
					,"画像パス03"
					,"画像パス04"
					,"画像パス05"
					,"賞味期限日数"
					,"削除フラグ"
					};
		int[] TgtCol = new int[NeedCol.length];
		for(int i=0;i<NeedCol.length;i++) {TgtCol[i] = -1;}
		
		
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
		column = columnModel01.getColumn( 2);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//商品コード
		column = columnModel01.getColumn( 3);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//荷主商品コード
		column = columnModel01.getColumn( 4);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//商品名1
		column = columnModel01.getColumn( 5);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//商品名2
		column = columnModel01.getColumn( 6);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//商品名3
		column = columnModel01.getColumn( 7);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//運送タイプコード01
		column = columnModel01.getColumn( 8);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//運送タイプコード02
		column = columnModel01.getColumn( 9);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//運送タイプコード03
		column = columnModel01.getColumn(10);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//運送タイプコード04
		column = columnModel01.getColumn(11);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//運送タイプコード05
		column = columnModel01.getColumn(12);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//基幹システム商品コード
		column = columnModel01.getColumn(13);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.rightCellRenderer());	//カートン入数
		column = columnModel01.getColumn(14);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.rightCellRenderer());	//ケース入数
		column = columnModel01.getColumn(15);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.rightCellRenderer());	//パレット入数
		column = columnModel01.getColumn(16);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//JANCD
		column = columnModel01.getColumn(17);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//カートンバーコード
		column = columnModel01.getColumn(18);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//ケースバーコード
		column = columnModel01.getColumn(19);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//パレットバーコード
		column = columnModel01.getColumn(20);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//カートン商品名称
		column = columnModel01.getColumn(21);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//ケース商品名称
		column = columnModel01.getColumn(22);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//パレット商品名称
		column = columnModel01.getColumn(23);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//商品単位
		column = columnModel01.getColumn(24);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//カートン商品単位
		column = columnModel01.getColumn(25);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//ケース商品単位
		column = columnModel01.getColumn(26);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//パレット商品単位
		column = columnModel01.getColumn(27);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.rightCellRenderer());	//商品重量
		column = columnModel01.getColumn(28);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.rightCellRenderer());	//カートン重量
		column = columnModel01.getColumn(29);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.rightCellRenderer());	//ケース重量
		column = columnModel01.getColumn(30);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.rightCellRenderer());	//パレット重量
		column = columnModel01.getColumn(31);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.rightCellRenderer());	//商品サイズ
		column = columnModel01.getColumn(32);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.rightCellRenderer());	//カートンサイズ
		column = columnModel01.getColumn(33);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.rightCellRenderer());	//ケースサイズ
		column = columnModel01.getColumn(34);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.rightCellRenderer());	//パレットサイズ
		column = columnModel01.getColumn(35);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//推奨ロケ
		column = columnModel01.getColumn(36);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//商品モデル番号（型番）
		column = columnModel01.getColumn(37);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//商品カテゴリCD
		column = columnModel01.getColumn(38);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//商品カテゴリ名
		column = columnModel01.getColumn(39);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//商品カラーコード
		column = columnModel01.getColumn(40);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//商品カラー名
		column = columnModel01.getColumn(41);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//商品サイズコード
		column = columnModel01.getColumn(42);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//商品サイス名
		column = columnModel01.getColumn(43);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//コメント1
		column = columnModel01.getColumn(44);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//コメント2
		column = columnModel01.getColumn(45);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//コメント3
		column = columnModel01.getColumn(46);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//温度区分
		column = columnModel01.getColumn(47);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//画像パス01
		column = columnModel01.getColumn(48);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//画像パス02
		column = columnModel01.getColumn(49);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//画像パス03
		column = columnModel01.getColumn(50);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//画像パス04
		column = columnModel01.getColumn(51);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//画像パス05
		column = columnModel01.getColumn(52);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//賞味期限日数
		column = columnModel01.getColumn(53);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//削除フラグ
		
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
			ItemMstExcelEntry(0,0,TgtFilePath);
		}else {
			int[] ClmnType = new int[HeaderRead[0].length];
			for(int i=0;i<ClmnType.length;i++) {ClmnType[i]=1;}
			
			ClmnType[TgtCol[ 0]]=1;	//荷主グループコード
			ClmnType[TgtCol[ 1]]=1;	//商品コード
			ClmnType[TgtCol[ 2]]=1;	//荷主商品コード
			ClmnType[TgtCol[ 3]]=1;	//商品名1
			ClmnType[TgtCol[ 4]]=1;	//商品名2
			ClmnType[TgtCol[ 5]]=1;	//商品名3
			ClmnType[TgtCol[ 6]]=1;	//運送タイプコード01
			ClmnType[TgtCol[ 7]]=1;	//運送タイプコード02
			ClmnType[TgtCol[ 8]]=1;	//運送タイプコード03
			ClmnType[TgtCol[ 9]]=1;	//運送タイプコード04
			ClmnType[TgtCol[10]]=1;	//運送タイプコード05
			ClmnType[TgtCol[11]]=1;	//基幹システム商品コード
			ClmnType[TgtCol[12]]=0;	//カートン入数
			ClmnType[TgtCol[13]]=0;	//ケース入数
			ClmnType[TgtCol[14]]=0;	//パレット入数
			ClmnType[TgtCol[15]]=1;	//JANCD
			ClmnType[TgtCol[16]]=1;	//カートンバーコード
			ClmnType[TgtCol[17]]=1;	//ケースバーコード
			ClmnType[TgtCol[18]]=1;	//パレットバーコード
			ClmnType[TgtCol[19]]=1;	//カートン商品名称
			ClmnType[TgtCol[20]]=1;	//ケース商品名称
			ClmnType[TgtCol[21]]=1;	//パレット商品名称
			ClmnType[TgtCol[22]]=1;	//商品単位
			ClmnType[TgtCol[23]]=1;	//カートン商品単位
			ClmnType[TgtCol[24]]=1;	//ケース商品単位
			ClmnType[TgtCol[25]]=1;	//パレット商品単位
			ClmnType[TgtCol[26]]=0;	//商品重量
			ClmnType[TgtCol[27]]=0;	//カートン重量
			ClmnType[TgtCol[28]]=0;	//ケース重量
			ClmnType[TgtCol[29]]=0;	//パレット重量
			ClmnType[TgtCol[30]]=0;	//商品サイズ
			ClmnType[TgtCol[31]]=0;	//カートンサイズ
			ClmnType[TgtCol[32]]=0;	//ケースサイズ
			ClmnType[TgtCol[33]]=0;	//パレットサイズ
			ClmnType[TgtCol[34]]=1;	//推奨ロケ
			ClmnType[TgtCol[35]]=1;	//商品モデル番号（型番）
			ClmnType[TgtCol[36]]=1;	//商品カテゴリCD
			ClmnType[TgtCol[37]]=1;	//商品カテゴリ名
			ClmnType[TgtCol[38]]=1;	//商品カラーコード
			ClmnType[TgtCol[39]]=1;	//商品カラー名
			ClmnType[TgtCol[40]]=1;	//商品サイズコード
			ClmnType[TgtCol[41]]=1;	//商品サイス名
			ClmnType[TgtCol[42]]=1;	//コメント1
			ClmnType[TgtCol[43]]=1;	//コメント2
			ClmnType[TgtCol[44]]=1;	//コメント3
			ClmnType[TgtCol[45]]=1;	//温度区分
			ClmnType[TgtCol[46]]=1;	//画像パス01
			ClmnType[TgtCol[47]]=1;	//画像パス02
			ClmnType[TgtCol[48]]=1;	//画像パス03
			ClmnType[TgtCol[49]]=1;	//画像パス04
			ClmnType[TgtCol[50]]=1;	//画像パス05
			ClmnType[TgtCol[51]]=1;	//賞味期限日数
			ClmnType[TgtCol[52]]=1;	//削除フラグ
			
			Object[][] ExcellRead = B00060ToolsExcellControl.ExcellRead(TgtFilePath,SheetName,ClmnType,true);
			
			ArrayList<String> ErrMsg = new ArrayList<String>();
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
				ErrMsg = ErrRt(CheckOb);
			}
			
			
			if(null!=ErrMsg && 0<ErrMsg.size()) {
				ErrView(ErrMsg);
				
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				WM00080ItemMstSearch.ItemMstSearch(0, 0);
			}else {
				main_fm.setVisible(true);
			}
		}
		
		RenewFg = true;
		
		//登録ボタン押下時の挙動
		entry_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				int RowCount = tableModel_ms01.getRowCount();
				int ColCount = tableModel_ms01.getColumnCount();
				
				if(0<RowCount) {
					Object[][] CheckOb = new Object[RowCount][ColCount];
					for(int i01=0;i01<RowCount;i01++) {
						for(int i02=0;i02<ColCount;i02++) {
							CheckOb[i01][i02] = tableModel_ms01.getValueAt(i01, i02);
						}
					}
					ArrayList<String> ErrMsg = ErrRt(CheckOb);
					if(null!=ErrMsg && 0<ErrMsg.size()) {
						ErrView(ErrMsg);
					}else {
						DataEntry(CheckOb);
						SetX=main_fm.getX();
						SetY=main_fm.getY();
	
						main_fm.setVisible(false);
						main_fm.dispose();
						WM00080ItemMstSearch.ItemMstSearch(0, 0);
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
				WM00080ItemMstSearch.ItemMstSearch(0, 0);
			}
		});
	}
	
	private static void DataEntry(Object[][] CheckOb) {
		String[] GetClGpCd 				= new String[CheckOb.length];		//荷主グループコード
		String[] GetItemCd 				= new String[CheckOb.length];		//商品コード
		String[] GetCLItemCd 			= new String[CheckOb.length];		//荷主商品コード
		String[] GetItemName01 			= new String[CheckOb.length];		//商品名1
		String[] GetItemName02 			= new String[CheckOb.length];		//商品名2
		String[] GetItemName03 			= new String[CheckOb.length];		//商品名3
		String[] GetDeliveryTypeCd01 	= new String[CheckOb.length];		//運送タイプコード01
		String[] GetDeliveryTypeCd02 	= new String[CheckOb.length];		//運送タイプコード02
		String[] GetDeliveryTypeCd03 	= new String[CheckOb.length];		//運送タイプコード03
		String[] GetDeliveryTypeCd04 	= new String[CheckOb.length];		//運送タイプコード04
		String[] GetDeliveryTypeCd05 	= new String[CheckOb.length];		//運送タイプコード05
		String[] GetPTMSCD 				= new String[CheckOb.length];		//基幹SYS商品コード
		String[] GetRecomendLoc 		= new String[CheckOb.length];		//推奨ロケ
		String[] GetItemMDNo 			= new String[CheckOb.length];		//商品型番
		String[] GetCategoryCd 			= new String[CheckOb.length];		//商品カテゴリCD
		String[] GetCategoryName 		= new String[CheckOb.length];		//商品カテゴリ名
		String[] GetItemColorCd 		= new String[CheckOb.length];		//商品カラーコード
		String[] GetItemColorName 		= new String[CheckOb.length];		//商品カラー名
		String[] GetItemSizeCd 			= new String[CheckOb.length];		//商品サイズコード
		String[] GetItemSizeName 		= new String[CheckOb.length];		//商品サイス名
		String[] GetTildFG 				= new String[CheckOb.length];		//温度区分
		String[] GetExpDateHowLong 		= new String[CheckOb.length];		//賞味期限日数
		String[] GetCtQty 				= new String[CheckOb.length];		//カートン入数
		String[] GetCsQty 				= new String[CheckOb.length];		//ケース入数
		String[] GetPlQty 				= new String[CheckOb.length];		//パレット入数
		String[] GetItemWeight 			= new String[CheckOb.length];		//商品重量
		String[] GetCtWeight 			= new String[CheckOb.length];		//カートン重量
		String[] GetCsWeight 			= new String[CheckOb.length];		//ケース重量
		String[] GetPlWeight 			= new String[CheckOb.length];		//パレット重量
		String[] GetItemSize 			= new String[CheckOb.length];		//商品サイズ
		String[] GetCtSize 				= new String[CheckOb.length];		//カートンサイズ
		String[] GetCsSize 				= new String[CheckOb.length];		//ケースサイズ
		String[] GetPlSize 				= new String[CheckOb.length];		//パレットサイズ
		String[] GetCtName 				= new String[CheckOb.length];		//カートン商品名称
		String[] GetCsName 				= new String[CheckOb.length];		//ケース商品名称
		String[] GetPlName 				= new String[CheckOb.length];		//パレット商品名称
		String[] GetUnitName 			= new String[CheckOb.length];		//商品単位
		String[] GetCtUnitName 			= new String[CheckOb.length];		//カートン商品単位
		String[] GetCsUnitName 			= new String[CheckOb.length];		//ケース商品単位
		String[] GetPlUnitName 			= new String[CheckOb.length];		//パレット商品単位
		String[] GetJanCd 				= new String[CheckOb.length];		//JANCD
		String[] GetCtJan 				= new String[CheckOb.length];		//カートンバーコード
		String[] GetCsJan 				= new String[CheckOb.length];		//ケースバーコード
		String[] GetPlJan 				= new String[CheckOb.length];		//パレットバーコード
		String[] GetCom01 				= new String[CheckOb.length];		//コメント1
		String[] GetCom02 				= new String[CheckOb.length];		//コメント2
		String[] GetCom03 				= new String[CheckOb.length];		//コメント3
		String[] GetDelFg 				= new String[CheckOb.length];		//削除フラグ
		String[] GetItemImagePath01 	= new String[CheckOb.length];		//画像パス01 
		String[] GetItemImagePath02 	= new String[CheckOb.length];		//画像パス02
		String[] GetItemImagePath03 	= new String[CheckOb.length];		//画像パス03
		String[] GetItemImagePath04 	= new String[CheckOb.length];		//画像パス04
		String[] GetItemImagePath05 	= new String[CheckOb.length];		//画像パス05
		String[] GetTildName 			= new String[CheckOb.length];		//温度区分名
		
		for(int i01=0;i01<CheckOb.length;i01++) {
			for(int i02=0;i02<CheckOb[i01].length;i02++) {
				CheckOb[i01][i02] = B00020ToolsTextControl.Trim(""+CheckOb[i01][i02]);
			}
			GetClGpCd[i01] 				= ""+CheckOb[i01][ 1];		//荷主グループコード
			GetItemCd[i01] 				= ""+CheckOb[i01][ 2];		//商品コード
			GetCLItemCd[i01] 			= ""+CheckOb[i01][ 3];		//荷主商品コード
			GetItemName01[i01] 			= ""+CheckOb[i01][ 4];		//商品名1
			GetItemName02[i01] 			= ""+CheckOb[i01][ 5];		//商品名2
			GetItemName03[i01] 			= ""+CheckOb[i01][ 6];		//商品名3
			GetDeliveryTypeCd01[i01] 	= ""+CheckOb[i01][ 7];		//運送タイプコード01
			GetDeliveryTypeCd02[i01] 	= ""+CheckOb[i01][ 8];		//運送タイプコード02
			GetDeliveryTypeCd03[i01] 	= ""+CheckOb[i01][ 9];		//運送タイプコード03
			GetDeliveryTypeCd04[i01] 	= ""+CheckOb[i01][10];		//運送タイプコード04
			GetDeliveryTypeCd05[i01] 	= ""+CheckOb[i01][11];		//運送タイプコード05
			GetPTMSCD[i01] 				= ""+CheckOb[i01][12];		//基幹SYS商品コード
			GetRecomendLoc[i01] 		= ""+CheckOb[i01][35];		//推奨ロケ
			GetItemMDNo[i01] 			= ""+CheckOb[i01][36];		//商品型番
			GetCategoryCd[i01] 			= ""+CheckOb[i01][37];		//商品カテゴリCD
			GetCategoryName[i01] 		= ""+CheckOb[i01][38];		//商品カテゴリ名
			GetItemColorCd[i01] 		= ""+CheckOb[i01][39];		//商品カラーコード
			GetItemColorName[i01] 		= ""+CheckOb[i01][40];		//商品カラー名
			GetItemSizeCd[i01] 			= ""+CheckOb[i01][41];		//商品サイズコード
			GetItemSizeName[i01] 		= ""+CheckOb[i01][42];		//商品サイス名
			GetTildFG[i01] 				= ""+CheckOb[i01][46];		//温度区分
			GetExpDateHowLong[i01] 		= ""+CheckOb[i01][52];		//賞味期限日数
			GetCtQty[i01] 				= ""+CheckOb[i01][13];		//カートン入数
			GetCsQty[i01] 				= ""+CheckOb[i01][14];		//ケース入数
			GetPlQty[i01] 				= ""+CheckOb[i01][15];		//パレット入数
			GetItemWeight[i01] 			= ""+CheckOb[i01][27];		//商品重量
			GetCtWeight[i01] 			= ""+CheckOb[i01][28];		//カートン重量
			GetCsWeight[i01] 			= ""+CheckOb[i01][29];		//ケース重量
			GetPlWeight[i01] 			= ""+CheckOb[i01][30];		//パレット重量
			GetItemSize[i01] 			= ""+CheckOb[i01][31];		//商品サイズ
			GetCtSize[i01] 				= ""+CheckOb[i01][32];		//カートンサイズ
			GetCsSize[i01] 				= ""+CheckOb[i01][33];		//ケースサイズ
			GetPlSize[i01] 				= ""+CheckOb[i01][34];		//パレットサイズ
			GetCtName[i01] 				= ""+CheckOb[i01][20];		//カートン商品名称
			GetCsName[i01] 				= ""+CheckOb[i01][21];		//ケース商品名称
			GetPlName[i01] 				= ""+CheckOb[i01][22];		//パレット商品名称
			GetUnitName[i01] 			= ""+CheckOb[i01][23];		//商品単位
			GetCtUnitName[i01] 			= ""+CheckOb[i01][24];		//カートン商品単位
			GetCsUnitName[i01] 			= ""+CheckOb[i01][25];		//ケース商品単位
			GetPlUnitName[i01] 			= ""+CheckOb[i01][26];		//パレット商品単位
			GetJanCd[i01] 				= ""+CheckOb[i01][16];		//JANCD
			GetCtJan[i01] 				= ""+CheckOb[i01][17];		//カートンバーコード
			GetCsJan[i01] 				= ""+CheckOb[i01][18];		//ケースバーコード
			GetPlJan[i01] 				= ""+CheckOb[i01][19];		//パレットバーコード
			GetCom01[i01] 				= ""+CheckOb[i01][43];		//コメント1
			GetCom02[i01] 				= ""+CheckOb[i01][44];		//コメント2
			GetCom03[i01] 				= ""+CheckOb[i01][45];		//コメント3
			GetDelFg[i01] 				= ""+CheckOb[i01][53];		//削除フラグ
			GetItemImagePath01[i01] 	= ""+CheckOb[i01][47];		//画像パス01 
			GetItemImagePath02[i01] 	= ""+CheckOb[i01][48];		//画像パス02
			GetItemImagePath03[i01] 	= ""+CheckOb[i01][49];		//画像パス03
			GetItemImagePath04[i01] 	= ""+CheckOb[i01][50];		//画像パス04
			GetItemImagePath05[i01] 	= ""+CheckOb[i01][51];		//画像パス05
			
			GetExpDateHowLong[i01] 		= B00020ToolsTextControl.num_only_String02(GetExpDateHowLong[i01]);		//賞味期限日数
			GetCtQty[i01] 				= B00020ToolsTextControl.num_only_String02(GetCtQty[i01]);				//カートン入数
			GetCsQty[i01] 				= B00020ToolsTextControl.num_only_String02(GetCsQty[i01]);				//ケース入数
			GetPlQty[i01] 				= B00020ToolsTextControl.num_only_String02(GetPlQty[i01]);				//パレット入数
			GetItemWeight[i01] 			= B00020ToolsTextControl.num_only_String02(GetItemWeight[i01]);			//商品重量
			GetCtWeight[i01] 			= B00020ToolsTextControl.num_only_String02(GetCtWeight[i01]);				//カートン重量
			GetCsWeight[i01] 			= B00020ToolsTextControl.num_only_String02(GetCsWeight[i01]);				//ケース重量
			GetPlWeight[i01] 			= B00020ToolsTextControl.num_only_String02(GetPlWeight[i01]);				//パレット重量
			GetItemSize[i01] 			= B00020ToolsTextControl.num_only_String02(GetItemSize[i01]);				//商品サイズ
			GetCtSize[i01] 				= B00020ToolsTextControl.num_only_String02(GetCtSize[i01]);				//カートンサイズ
			GetCsSize[i01] 				= B00020ToolsTextControl.num_only_String02(GetCsSize[i01]);				//ケースサイズ
			GetPlSize[i01] 				= B00020ToolsTextControl.num_only_String02(GetPlSize[i01]);				//パレットサイズ
			
			if("".equals(GetExpDateHowLong[i01]	)){GetExpDateHowLong[i01]="0";}		//賞味期限日数
			if("".equals(GetCtQty[i01]			)){GetCtQty[i01]="0";}				//カートン入数
			if("".equals(GetCsQty[i01]			)){GetCsQty[i01]="0";}				//ケース入数
			if("".equals(GetPlQty[i01]			)){GetPlQty[i01]="0";}				//パレット入数
			if("".equals(GetItemWeight[i01]		)){GetItemWeight[i01]="0";}			//商品重量
			if("".equals(GetCtWeight[i01]		)){GetCtWeight[i01]="0";}			//カートン重量
			if("".equals(GetCsWeight[i01]		)){GetCsWeight[i01]="0";}			//ケース重量
			if("".equals(GetPlWeight[i01]		)){GetPlWeight[i01]="0";}			//パレット重量
			if("".equals(GetItemSize[i01]		)){GetItemSize[i01]="0";}			//商品サイズ
			if("".equals(GetCtSize[i01]			)){GetCtSize[i01]="0";}				//カートンサイズ
			if("".equals(GetCsSize[i01]			)){GetCsSize[i01]="0";}				//ケースサイズ
			if("".equals(GetPlSize[i01]			)){GetPlSize[i01]="0";}				//パレットサイズ
			
			GetTildName[i01] = "";
			for(int i02 =0;i02<B00100DefaultVariable.TildFG[1].length;i02++) {
				if(GetTildFG[i01].equals(B00100DefaultVariable.TildFG[1][i02])) {
					GetTildName[i01] = B00100DefaultVariable.TildFG[2][i02];
				}
			}
		}
		//現在登録済みの商品マスタ取得
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
		boolean AllSearch = false;
		
		for(int i=0;i<CheckOb.length;i++) {
			SearchItemCd.add(""+GetItemCd[i]);
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
		
		//画像パスが指定されていれば画像を1024×1024にリサイズして保存
		//画像保存用のフォルダ存在チェック
		B00040ToolsFolderCheck.FLD_CHECK(A00000Main.FileFldPth);
		B00040ToolsFolderCheck.FLD_CHECK(A00000Main.FileFldPth+"\\ItemImage");
		B00040ToolsFolderCheck.FLD_CHECK(A00000Main.FileFldPth+"\\ItemImage\\"+GetClGpCd);
		
		for(int i01=0;i01<CheckOb.length;i01++) {
			if(!"".equals(GetItemImagePath01[i01])){		//画像パス01 
				String SavePath = A00000Main.FileFldPth+"\\ItemImage\\"+GetClGpCd[i01];
				String FullSavePath = SavePath+"\\"+GetItemCd[i01]+"_01.";
				String GetItemImagePath = GetItemImagePath01[i01];
				
				if(B00140PictControl.PictCheck(GetItemImagePath)&&B00040ToolsFolderCheck.FLD_CHECK_ONRY(SavePath)) {
					String FileType=B00040ToolsFolderCheck.FILE_TYPE(GetItemImagePath);
					Image image = B00140PictControl.PictReSize(1024,1024,GetItemImagePath);
					FullSavePath = FullSavePath+FileType;
					B00140PictControl.PictSave(image,FileType,FullSavePath);
					GetItemImagePath01[i01] = FullSavePath;
				}else {
				}
			}
			if(!"".equals(GetItemImagePath02[i01])){		//画像パス02
				String SavePath = A00000Main.FileFldPth+"\\ItemImage\\"+GetClGpCd[i01];
				String FullSavePath = SavePath+"\\"+GetItemCd[i01]+"_02.";
				String GetItemImagePath = GetItemImagePath02[i01];
				
				if(B00140PictControl.PictCheck(GetItemImagePath)&&B00040ToolsFolderCheck.FLD_CHECK_ONRY(SavePath)) {
					String FileType=B00040ToolsFolderCheck.FILE_TYPE(GetItemImagePath);
					Image image = B00140PictControl.PictReSize(1024,1024,GetItemImagePath);
					FullSavePath = FullSavePath+FileType;
					B00140PictControl.PictSave(image,FileType,FullSavePath);
					GetItemImagePath02[i01] = FullSavePath;
				}else {
				}
			}
			if(!"".equals(GetItemImagePath03[i01])){		//画像パス03
				String SavePath = A00000Main.FileFldPth+"\\ItemImage\\"+GetClGpCd[i01];
				String FullSavePath = SavePath+"\\"+GetItemCd[i01]+"_03.";
				String GetItemImagePath = GetItemImagePath03[i01];
				
				if(B00140PictControl.PictCheck(GetItemImagePath)&&B00040ToolsFolderCheck.FLD_CHECK_ONRY(SavePath)) {
					String FileType=B00040ToolsFolderCheck.FILE_TYPE(GetItemImagePath);
					Image image = B00140PictControl.PictReSize(1024,1024,GetItemImagePath);
					FullSavePath = FullSavePath+FileType;
					B00140PictControl.PictSave(image,FileType,FullSavePath);
					GetItemImagePath03[i01] = FullSavePath;
				}else {
				}
			}
			if(!"".equals(GetItemImagePath04[i01])){		//画像パス04
				String SavePath = A00000Main.FileFldPth+"\\ItemImage\\"+GetClGpCd[i01];
				String FullSavePath = SavePath+"\\"+GetItemCd[i01]+"_04.";
				String GetItemImagePath = GetItemImagePath04[i01];
				
				if(B00140PictControl.PictCheck(GetItemImagePath)&&B00040ToolsFolderCheck.FLD_CHECK_ONRY(SavePath)) {
					String FileType=B00040ToolsFolderCheck.FILE_TYPE(GetItemImagePath);
					Image image = B00140PictControl.PictReSize(1024,1024,GetItemImagePath);
					FullSavePath = FullSavePath+FileType;
					B00140PictControl.PictSave(image,FileType,FullSavePath);
					GetItemImagePath04[i01] = FullSavePath;
				}else {
				}
			}
			if(!"".equals(GetItemImagePath05[i01])){		//画像パス05
				String SavePath = A00000Main.FileFldPth+"\\ItemImage\\"+GetClGpCd[i01];
				String FullSavePath = SavePath+"\\"+GetItemCd[i01]+"_05.";
				String GetItemImagePath = GetItemImagePath05[i01];
				
				if(B00140PictControl.PictCheck(GetItemImagePath)&&B00040ToolsFolderCheck.FLD_CHECK_ONRY(SavePath)) {
					String FileType=B00040ToolsFolderCheck.FILE_TYPE(GetItemImagePath);
					Image image = B00140PictControl.PictReSize(1024,1024,GetItemImagePath);
					FullSavePath = FullSavePath+FileType;
					B00140PictControl.PictSave(image,FileType,FullSavePath);
					GetItemImagePath05[i01] = FullSavePath;
				}else {
				}
			}

			//画像パスが空白だった場合既存のマスタ情報をセットする
			if("".equals(GetItemImagePath01[i01])||"".equals(GetItemImagePath02[i01])||"".equals(GetItemImagePath03[i01])||"".equals(GetItemImagePath04[i01])||"".equals(GetItemImagePath05[i01])) {
				for(int i02=0;i02<ItemMstRt.length;i02++) {
					if(GetClGpCd[i01].equals(""+ItemMstRt[i02][M00070ItemMstRt.ColClGpCd])&&GetItemCd[i01].equals(""+ItemMstRt[i02][M00070ItemMstRt.ColItemCd])) {
						if("".equals(GetItemImagePath01[i01])) {
							GetItemImagePath01[i01] = ""+ItemMstRt[i02][M00070ItemMstRt.ColPictPass01];
						}
						if("".equals(GetItemImagePath02[i01])) {
							GetItemImagePath02[i01] = ""+ItemMstRt[i02][M00070ItemMstRt.ColPictPass02];
						}
						if("".equals(GetItemImagePath03[i01])) {
							GetItemImagePath03[i01] = ""+ItemMstRt[i02][M00070ItemMstRt.ColPictPass03];
						}
						if("".equals(GetItemImagePath04[i01])) {
							GetItemImagePath04[i01] = ""+ItemMstRt[i02][M00070ItemMstRt.ColPictPass04];
						}
						if("".equals(GetItemImagePath05[i01])) {
							GetItemImagePath05[i01] = ""+ItemMstRt[i02][M00070ItemMstRt.ColPictPass05];
						}
						i02=ItemMstRt.length+1;
					}
				}
			}
		}
		
		String now_dtm = B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[1];
		
		String[][] ItemMstSetString = {
				  {"ClGpCd"				,"1","1"}	//荷主グループコード
				 ,{"ItemCd"				,"1","1"}	//商品コード
				 ,{"CLItemCd"			,"1","1"}	//荷主商品コード
				 ,{"ItemName01"			,"1","1"}	//商品名1
				 ,{"ItemName02"			,"1","1"}	//商品名2
				 ,{"ItemName03"			,"1","1"}	//商品名3
				 ,{"ItemWeight"			,"1","1"}	//商品重量
				 ,{"ItemSize"			,"1","1"}	//商品サイズ
				 ,{"DeliveryTypeCd"		,"1","1"}	//運送タイプコード01
				 ,{"DeliveryTypeCd02"	,"1","1"}	//運送タイプコード02
				 ,{"DeliveryTypeCd03"	,"1","1"}	//運送タイプコード03
				 ,{"DeliveryTypeCd04"	,"1","1"}	//運送タイプコード04
				 ,{"DeliveryTypeCd05"	,"1","1"}	//運送タイプコード05
				 ,{"EntryDate"			,"1","0"}	//データ登録日時
				 ,{"UpdateDate"			,"1","1"}	//データ更新日時
				 ,{"EntryUser"			,"1","0"}	//登録者コード
				 ,{"UpdateUser"			,"1","1"}	//更新者コード
				 ,{"DelFg"				,"1","1"}	//削除フラグ
				 ,{"PTMSCD"				,"1","1"}	//基幹SYS商品コード
				 ,{"JanCd"				,"1","1"}	//JANCD
				 ,{"UnitName"			,"1","1"}	//商品単位
				};
		String tgt_table = "KM0060_ITEMMST";
		String[][] field_name = new String[ItemMstSetString.length][3];
		String[][] entry_data = new String[CheckOb.length][ItemMstSetString.length];
		String[] judg_field = new String[2];
		String[][] judg_data = new String[CheckOb.length][2];
		String TgtDB = "NYANKO";
		int non_msg_fg = 1;

		judg_field[0] = "ClGpCd";			//荷主グループコード
		judg_field[1] = "ItemCd";			//商品コード
		
		for(int i=0;i<ItemMstSetString.length;i++) {
			field_name[i][0] = ItemMstSetString[i][0];
			field_name[i][1] = ItemMstSetString[i][1];
			field_name[i][2] = ItemMstSetString[i][2];
		}
		
		for(int i01=0;i01<CheckOb.length;i01++) {
			judg_data[i01][0] = GetClGpCd[i01];		//荷主グループコード
			judg_data[i01][1] = GetItemCd[i01];		//商品コード
			
			entry_data[i01][ 0] = GetClGpCd[i01] ;			//荷主グループコード
			entry_data[i01][ 1] = GetItemCd[i01];			//商品コード
			entry_data[i01][ 2] = GetCLItemCd[i01];			//荷主商品コード
			entry_data[i01][ 3] = GetItemName01[i01];		//商品名1
			entry_data[i01][ 4] = GetItemName02[i01];		//商品名2
			entry_data[i01][ 5] = GetItemName03[i01];		//商品名3
			entry_data[i01][ 6] = GetItemWeight[i01];		//商品重量
			entry_data[i01][ 7] = GetItemSize[i01];			//商品サイズ
			entry_data[i01][ 8] = GetDeliveryTypeCd01[i01];	//運送タイプコード01
			entry_data[i01][ 9] = GetDeliveryTypeCd02[i01];	//運送タイプコード02
			entry_data[i01][10] = GetDeliveryTypeCd03[i01];	//運送タイプコード03
			entry_data[i01][11] = GetDeliveryTypeCd04[i01];	//運送タイプコード04
			entry_data[i01][12] = GetDeliveryTypeCd05[i01];	//運送タイプコード05
			entry_data[i01][13] = now_dtm;	//データ登録日時
			entry_data[i01][14] = now_dtm;	//データ更新日時
			entry_data[i01][15] = "(" + A00000Main.LoginUserId + ")" + A00000Main.LoginUserName;	//登録者コード
			entry_data[i01][16] = "(" + A00000Main.LoginUserId + ")" + A00000Main.LoginUserName;	//更新者コード
			entry_data[i01][17] = GetDelFg[i01];	//削除フラグ
			entry_data[i01][18] = GetPTMSCD[i01];	//基幹SYS商品コード
			entry_data[i01][19] = GetJanCd[i01];	//JANCD
			entry_data[i01][20] = GetUnitName[i01];	//商品単位
		}
		A00020InsertUdateSQL.RUN_SQLS_EU(tgt_table, field_name, entry_data, judg_field, judg_data, non_msg_fg,TgtDB);
		
		String[][] ItemMstSubSetString = {
				 {"ClGpCd"			,"1","1"}	//荷主グループコード
				,{"ItemCd"			,"1","1"}	//商品コード
				,{"CtQty"			,"1","1"}	//カートン入数
				,{"CsQty"			,"1","1"}	//ケース入数
				,{"PlQty"			,"1","1"}	//パレット入数
				,{"CtJan"			,"1","1"}	//カートンバーコード
				,{"CsJan"			,"1","1"}	//ケースバーコード
				,{"PlJan"			,"1","1"}	//パレットバーコード
				,{"CtName"			,"1","1"}	//カートン商品名称
				,{"CsName"			,"1","1"}	//ケース商品名称
				,{"PlName"			,"1","1"}	//パレット商品名称
				,{"CtUnitName"		,"1","1"}	//カートン商品単位
				,{"CsUnitName"		,"1","1"}	//ケース商品単位
				,{"PlUnitName"		,"1","1"}	//パレット商品単位
				,{"CtWeight"		,"1","1"}	//カートン重量
				,{"CsWeight"		,"1","1"}	//ケース重量
				,{"PlWeight"		,"1","1"}	//パレット重量
				,{"CtSize"			,"1","1"}	//カートンサイズ
				,{"CsSize"			,"1","1"}	//ケースサイズ
				,{"PlSize"			,"1","1"}	//パレットサイズ
				,{"RecomendLoc"		,"1","1"}	//推奨ロケ
				,{"ItemMDNo"		,"1","1"}	//商品モデル番号（型番）
				,{"CategoryCd"		,"1","1"}	//商品カテゴリCD
				,{"CategoryName"	,"1","1"}	//商品カテゴリ名
				,{"ItemColorCd"		,"1","1"}	//商品カラーコード
				,{"ItemColorName"	,"1","1"}	//商品カラー名
				,{"ItemSizeCd"		,"1","1"}	//商品サイズコード
				,{"ItemSizeName"	,"1","1"}	//商品サイス名
				,{"Com01"			,"1","1"}	//コメント1
				,{"Com02"			,"1","1"}	//コメント2
				,{"Com03"			,"1","1"}	//コメント3
				,{"EntryDate"		,"1","0"}	//データ登録日時
				,{"UpdateDate"		,"1","1"}	//データ更新日時
				,{"EntryUser"		,"1","0"}	//登録者コード
				,{"UpdateUser"		,"1","1"}	//更新者コード
				,{"TildFG"			,"1","1"}	//温度区分
				,{"TildName"		,"1","1"}	//温度区分名
				,{"PictPass01"		,"1","1"}	//画像パス01
				,{"PictPass02"		,"1","1"}	//画像パス02
				,{"PictPass03"		,"1","1"}	//画像パス03
				,{"PictPass04"		,"1","1"}	//画像パス04
				,{"PictPass05"		,"1","1"}	//画像パス05
				,{"ExpDateHowLong"	,"1","1"}	//賞味期限日数
				};
		tgt_table = "KM0061_ITEMMSTSUB";
		field_name = new String[ItemMstSubSetString.length][3];
		entry_data = new String[CheckOb.length][ItemMstSubSetString.length];
		judg_field = new String[2];
		judg_data = new String[CheckOb.length][2];
		TgtDB = "NYANKO";
		non_msg_fg = 1;

		judg_field[0] = "ClGpCd";			//荷主グループコード
		judg_field[1] = "ItemCd";			//商品コード
		
		for(int i=0;i<ItemMstSubSetString.length;i++) {
			field_name[i][0] = ItemMstSubSetString[i][0];
			field_name[i][1] = ItemMstSubSetString[i][1];
			field_name[i][2] = ItemMstSubSetString[i][2];
		}
		for(int i01=0;i01<CheckOb.length;i01++) {
			judg_data[i01][0] = GetClGpCd[i01];		//荷主グループコード
			judg_data[i01][1] = GetItemCd[i01];		//商品コード
			entry_data[i01][ 0] = GetClGpCd[i01];	//荷主グループコード
			entry_data[i01][ 1] = GetItemCd[i01];	//商品コード
			entry_data[i01][ 2] = GetCtQty[i01];	//カートン入数
			entry_data[i01][ 3] = GetCsQty[i01];	//ケース入数
			entry_data[i01][ 4] = GetPlQty[i01];	//パレット入数
			entry_data[i01][ 5] = GetCtJan[i01];	//カートンバーコード
			entry_data[i01][ 6] = GetCsJan[i01];	//ケースバーコード
			entry_data[i01][ 7] = GetPlJan[i01];	//パレットバーコード
			entry_data[i01][ 8] = GetCtName[i01];	//カートン商品名称
			entry_data[i01][ 9] = GetCsName[i01];	//ケース商品名称
			entry_data[i01][10] = GetPlName[i01];	//パレット商品名称
			entry_data[i01][11] = GetCtUnitName[i01];	//カートン商品単位
			entry_data[i01][12] = GetCsUnitName[i01];	//ケース商品単位
			entry_data[i01][13] = GetPlUnitName[i01];	//パレット商品単位
			entry_data[i01][14] = GetCtWeight[i01];	//カートン重量
			entry_data[i01][15] = GetCsWeight[i01];	//ケース重量
			entry_data[i01][16] = GetPlWeight[i01];	//パレット重量
			entry_data[i01][17] = GetCtSize[i01];	//カートンサイズ
			entry_data[i01][18] = GetCsSize[i01];	//ケースサイズ
			entry_data[i01][19] = GetPlSize[i01];	//パレットサイズ
			entry_data[i01][20] = GetRecomendLoc[i01];	//推奨ロケ
			entry_data[i01][21] = GetItemMDNo[i01];	//商品モデル番号（型番）
			entry_data[i01][22] = GetCategoryCd[i01];	//商品カテゴリCD
			entry_data[i01][23] = GetCategoryName[i01];	//商品カテゴリ名
			entry_data[i01][24] = GetItemColorCd[i01];	//商品カラーコード
			entry_data[i01][25] = GetItemColorName[i01];	//商品カラー名
			entry_data[i01][26] = GetItemSizeCd[i01];	//商品サイズコード
			entry_data[i01][27] = GetItemSizeName[i01];	//商品サイス名
			entry_data[i01][28] = GetCom01[i01];	//コメント1
			entry_data[i01][29] = GetCom02[i01];	//コメント2
			entry_data[i01][30] = GetCom03[i01];	//コメント3
			entry_data[i01][31] = now_dtm;	//データ登録日時
			entry_data[i01][32] = now_dtm;	//データ更新日時
			entry_data[i01][33] = "(" + A00000Main.LoginUserId + ")" + A00000Main.LoginUserName;	//登録者コード
			entry_data[i01][34] = "(" + A00000Main.LoginUserId + ")" + A00000Main.LoginUserName;	//更新者コード
			entry_data[i01][35] = GetTildFG[i01];	//温度区分
			entry_data[i01][36] = GetTildName[i01];	//温度区分名
			entry_data[i01][37] = GetItemImagePath01[i01];	//画像パス01
			entry_data[i01][38] = GetItemImagePath02[i01];	//画像パス02
			entry_data[i01][39] = GetItemImagePath03[i01];	//画像パス03
			entry_data[i01][40] = GetItemImagePath04[i01];	//画像パス04
			entry_data[i01][41] = GetItemImagePath05[i01];	//画像パス05
			entry_data[i01][42] = GetExpDateHowLong[i01];	//賞味期限日数
		}
		A00020InsertUdateSQL.RUN_SQLS_EU(tgt_table, field_name, entry_data, judg_field, judg_data, non_msg_fg,TgtDB);
	}
	
	private static ArrayList<String> ErrRt(Object[][] CheckOb) {
		ArrayList<String> ErrMsg = new ArrayList<String>();
		
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
		
		for(int i=0;i<CheckOb.length;i++) {
			boolean UnHitFg = true;
			for(int i01=0;i01<ClGpMstRt.length;i01++) {
				if((""+CheckOb[i][1]).equals(""+ClGpMstRt[i01][M00010ClGpMstRt.ColClGpCD])) {
					UnHitFg = false;
					i01=ClGpMstRt.length+1;
				}
			}
			if(UnHitFg) {
				int wint=i+1;
				ErrMsg.add(wint+"行目エラー("+CheckOb[i][1]+")は荷主グループCDにありません");
			}
			
			UnHitFg = true;
			for(int i01=0;i01<B00100DefaultVariable.DeliveryType01[1].length;i01++) {
				if((""+CheckOb[i][7]).equals(B00100DefaultVariable.DeliveryType01[1][i01])) {
					UnHitFg = false;
					i01=B00100DefaultVariable.DeliveryType01[1].length+1;
				}
			}
			if(UnHitFg) {
				int wint=i+1;
				ErrMsg.add(wint+"行目エラー("+CheckOb[i][7]+")は運送タイプCD01にありません");
			}

			UnHitFg = true;
			for(int i01=0;i01<B00100DefaultVariable.DeliveryType02[1].length;i01++) {
				if((""+CheckOb[i][8]).equals(B00100DefaultVariable.DeliveryType02[1][i01])) {
					UnHitFg = false;
					i01=B00100DefaultVariable.DeliveryType02[1].length+1;
				}
			}
			if(UnHitFg) {
				int wint=i+1;
				ErrMsg.add(wint+"行目エラー("+CheckOb[i][8]+")は運送タイプCD02にありません");
			}

			UnHitFg = true;
			for(int i01=0;i01<B00100DefaultVariable.DeliveryType03[1].length;i01++) {
				if((""+CheckOb[i][9]).equals(B00100DefaultVariable.DeliveryType03[1][i01])) {
					UnHitFg = false;
					i01=B00100DefaultVariable.DeliveryType03[1].length+1;
				}
			}
			if(UnHitFg) {
				int wint=i+1;
				ErrMsg.add(wint+"行目エラー("+CheckOb[i][9]+")は運送タイプCD03にありません");
			}

			UnHitFg = true;
			for(int i01=0;i01<B00100DefaultVariable.DeliveryType04[1].length;i01++) {
				if((""+CheckOb[i][10]).equals(B00100DefaultVariable.DeliveryType04[1][i01])) {
					UnHitFg = false;
					i01=B00100DefaultVariable.DeliveryType04[1].length+1;
				}
			}
			if(UnHitFg) {
				int wint=i+1;
				ErrMsg.add(wint+"行目エラー("+CheckOb[i][10]+")は運送タイプCD04にありません");
			}

			UnHitFg = true;
			for(int i01=0;i01<B00100DefaultVariable.DeliveryType05[1].length;i01++) {
				if((""+CheckOb[i][11]).equals(B00100DefaultVariable.DeliveryType05[1][i01])) {
					UnHitFg = false;
					i01=B00100DefaultVariable.DeliveryType05[1].length+1;
				}
			}
			if(UnHitFg) {
				int wint=i+1;
				ErrMsg.add(wint+"行目エラー("+CheckOb[i][11]+")は運送タイプCD05にありません");
			}

			UnHitFg = true;
			for(int i01=0;i01<B00100DefaultVariable.TildFG[1].length;i01++) {
				if((""+CheckOb[i][46]).equals(B00100DefaultVariable.TildFG[1][i01])) {
					UnHitFg = false;
					i01=B00100DefaultVariable.TildFG[1].length+1;
				}
			}
			if(UnHitFg) {
				int wint=i+1;
				ErrMsg.add(wint+"行目エラー("+CheckOb[i][46]+")は温度区分にありません");
			}

			UnHitFg = true;
			for(int i01=0;i01<B00100DefaultVariable.DelList[1].length;i01++) {
				if((""+CheckOb[i][53]).equals(B00100DefaultVariable.DelList[1][i01])) {
					UnHitFg = false;
					i01=B00100DefaultVariable.DelList[1].length+1;
				}
			}
			if(UnHitFg) {
				int wint=i+1;
				ErrMsg.add(wint+"行目エラー("+CheckOb[i][53]+")は削除区分にありません");
			}
			
			if("".equals(""+CheckOb[i][2])) {
				int wint=i+1;
				ErrMsg.add(wint+"行目エラー "+CheckOb[i][2]+"商品コードは必須です");
			}
			
			if("".equals(""+CheckOb[i][4])) {
				int wint=i+1;
				ErrMsg.add(wint+"行目エラー "+CheckOb[i][4]+"商品名01は必須です");
			}
		}
		return ErrMsg;
	}
	
	private static void ErrView(ArrayList<String>ErrMsg) {
		//必要フォルダを生成する
		String FLD_PATH = A00000Main.MainFLD+"\\MstControl";
		B00040ToolsFolderCheck.FLD_CHECK(FLD_PATH);
		FLD_PATH = A00000Main.MainFLD+"\\MstControl\\ItemMst";
		B00040ToolsFolderCheck.FLD_CHECK(FLD_PATH);
		FLD_PATH = A00000Main.MainFLD+"\\MstControl\\ItemMst\\Err";
		B00040ToolsFolderCheck.FLD_CHECK(FLD_PATH);
		FLD_PATH = A00000Main.MainFLD+"\\MstControl\\ItemMst\\BK";
		B00040ToolsFolderCheck.FLD_CHECK(FLD_PATH);
		
		//ファイルに出力
		String NowDTM=B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[1].replace(" ", "").replace("/", "").replace(":", "");
		
		FLD_PATH = A00000Main.MainFLD+"\\MstControl\\ItemMst\\Err";
		
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