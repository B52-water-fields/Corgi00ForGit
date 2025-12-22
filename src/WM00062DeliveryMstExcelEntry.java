import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class WM00062DeliveryMstExcelEntry{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	public static void DeliveryMstExcelEntry(int x,int y,String TgtFilePath) {
		A00000Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;

		final JFrame main_fm = B00110FrameParts.FrameCreate(x,y,600,200,"Corgi00届先登録（エクセル）","");
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
				DeliveryMstExcelEntryMain(0,0,TgtFilePath,SheetName);
			}
		});
		
		
		//EXITボタン押下時の挙動
		exit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				WM00060DeliveryMstSearch.DeliveryMstSearch(0, 0);
			}
		});
	}
	
	public static void DeliveryMstExcelEntryMain(int x,int y,String TgtFilePath,String SheetName) {
		A00000Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		
		final JFrame main_fm = B00110FrameParts.FrameCreate(x,y,750,800,"Corgi00届先登録（エクセル）","");
		JLabel userinfo = B00110FrameParts.UserInfo();
		JButton exit_btn = B00110FrameParts.ExitBtn();
		JButton entry_btn = B00110FrameParts.EntryBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		main_fm.add(entry_btn);
		
		JLabel LB_SheetList	= B00110FrameParts.JLabelSet(	10, 40,300,20,"以下のデータを登録しようとしています",11,0);
		main_fm.add(LB_SheetList);
		
		String[] columnNames01 = {
				 "納品先コード"
				,"部署CD"
				,"納品先名1"
				,"納品先名2"
				,"納品先名3"
				,"納品先郵便"
				,"納品先住所1"
				,"納品先住所2"
				,"納品先住所3"
				,"納品先電話"
				,"納品先FAX"
				,"納品先MAIL"
				,"コメント1"
				,"コメント2"
				,"コメント3"
				,"基幹システム発着地コード"
				,"削除区分"
				};
		
		//編集可能カラムの指定
		B10010TableControl.RenewTgt = new int[1];
		B10010TableControl.RenewTgt[0] = -1;
		final DefaultTableModel tableModel_ms01 = new B10010TableControl.MyTableModel01(columnNames01,0);
		
		final JTable tb01 = new JTable(tableModel_ms01);
		tb01.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tb01.setRowHeight(20*A00000Main.Mul/A00000Main.Div);
		tb01.setFont(new Font(A00000Main.DefaultFont, Font.PLAIN, 12*A00000Main.Mul/A00000Main.Div));
		
		DefaultTableColumnModel columnModel01
		= (DefaultTableColumnModel)tb01.getColumnModel();
		
		//列幅初期設定 表示位置設定
		TableColumn column = null;
		
		column = columnModel01.getColumn( 0);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//納品先コード
		column = columnModel01.getColumn( 1);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//部署CD
		column = columnModel01.getColumn( 2);	column.setPreferredWidth(200*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//納品先名1
		column = columnModel01.getColumn( 3);	column.setPreferredWidth(200*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//納品先名2
		column = columnModel01.getColumn( 4);	column.setPreferredWidth(200*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//納品先名3
		column = columnModel01.getColumn( 5);	column.setPreferredWidth(200*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//納品先郵便
		column = columnModel01.getColumn( 6);	column.setPreferredWidth(200*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//納品先住所1
		column = columnModel01.getColumn( 7);	column.setPreferredWidth(200*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//納品先住所2
		column = columnModel01.getColumn( 8);	column.setPreferredWidth(200*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//納品先住所3
		column = columnModel01.getColumn( 9);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//納品先電話
		column = columnModel01.getColumn(10);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//納品先FAX
		column = columnModel01.getColumn(11);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//納品先MAIL
		column = columnModel01.getColumn(12);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//コメント1
		column = columnModel01.getColumn(13);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//コメント2
		column = columnModel01.getColumn(14);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//コメント3
		column = columnModel01.getColumn(15);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//基幹システム発着地コード
		column = columnModel01.getColumn(16);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//削除区分
		
		//スクロール用設定
		JScrollPane scpn01 = B00110FrameParts.JScrollPaneSet(10,65,700,550,tb01);
		main_fm.add(scpn01);
		
		int[] ClmnType = new int[28];
		
		ClmnType[ 0]=1;	//Fg
		ClmnType[ 1]=1;	//納品先コード
		ClmnType[ 2]=1;	//部署CD
		ClmnType[ 3]=1;	//納品先名1
		ClmnType[ 4]=1;	//納品先名2
		ClmnType[ 5]=1;	//納品先名3
		ClmnType[ 6]=1;	//納品先郵便
		ClmnType[ 7]=1;	//納品先住所1
		ClmnType[ 8]=1;	//納品先住所2
		ClmnType[ 9]=1;	//納品先住所3
		ClmnType[10]=1;	//納品先電話
		ClmnType[11]=1;	//納品先FAX
		ClmnType[12]=1;	//納品先MAIL
		ClmnType[13]=1;	//コメント1
		ClmnType[14]=1;	//コメント2"
		ClmnType[15]=1;	//コメント3
		ClmnType[16]=1;	//JIS県CD2桁
		ClmnType[17]=1;	//JIS市区町村CD5桁
		ClmnType[18]=1;	//基幹システム発着地コード
		ClmnType[19]=1;	//データ登録日時
		ClmnType[20]=1;	//データ更新日時
		ClmnType[21]=1;	//登録者コード
		ClmnType[22]=1;	//更新者コード
		ClmnType[23]=1;	//登録した荷主CD
		ClmnType[24]=1;	//更新した荷主CD
		ClmnType[25]=1;	//削除区分
		ClmnType[26]=1;	//登録した荷主名
		ClmnType[27]=1;	//登録した荷主名
		
		Object[][] ExcellRead = B00060ToolsExcellControl.ExcellRead(TgtFilePath,SheetName,ClmnType,true);
		if(0<ExcellRead.length&&ClmnType.length<=ExcellRead[0].length) {
			for(int i=0;i<ExcellRead.length;i++) {
				Object[] SetOb = new Object[6];
				
				SetOb[0] = B00020ToolsTextControl.num_only_String(""+ExcellRead[i][1]);	//郵便番号
				if(!"".equals(""+SetOb[0])) {
					SetOb[1] = ExcellRead[i][2];
					SetOb[2] = ExcellRead[i][3];
					SetOb[3] = ExcellRead[i][4];
					SetOb[4] = ExcellRead[i][5];
					
					tableModel_ms01.addRow(SetOb);
				}
			}
		}
		

		final JCheckBox PostNeed =new JCheckBox();
		
	
		main_fm.setVisible(true);
		RenewFg = true;
	
		//EXITボタン押下時の挙動
		exit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				WM00060DeliveryMstSearch.DeliveryMstSearch(0, 0);
			}
		});
	}
	
	
	
	
	
	
	
	
}