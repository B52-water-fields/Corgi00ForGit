import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
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
		
		String[] NeedCol = {
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

		int[] TgtCol = {
					 -1	//納品先コード
					,-1	//部署CD
					,-1	//納品先名1
					,-1	//納品先名2
					,-1	//納品先名3
					,-1	//納品先郵便
					,-1	//納品先住所1
					,-1	//納品先住所2
					,-1	//納品先住所3
					,-1	//納品先電話
					,-1	//納品先FAX
					,-1	//納品先MAIL
					,-1	//コメント1
					,-1	//コメント2
					,-1	//コメント3
					,-1	//基幹システム発着地コード
					,-1	//削除区分
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
		column = columnModel01.getColumn( 1);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//納品先コード
		column = columnModel01.getColumn( 2);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//部署CD
		column = columnModel01.getColumn( 3);	column.setPreferredWidth(200*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//納品先名1
		column = columnModel01.getColumn( 4);	column.setPreferredWidth(200*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//納品先名2
		column = columnModel01.getColumn( 5);	column.setPreferredWidth(200*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//納品先名3
		column = columnModel01.getColumn( 6);	column.setPreferredWidth(200*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//納品先郵便
		column = columnModel01.getColumn( 7);	column.setPreferredWidth(200*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//納品先住所1
		column = columnModel01.getColumn( 8);	column.setPreferredWidth(200*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//納品先住所2
		column = columnModel01.getColumn( 9);	column.setPreferredWidth(200*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//納品先住所3
		column = columnModel01.getColumn(10);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//納品先電話
		column = columnModel01.getColumn(11);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//納品先FAX
		column = columnModel01.getColumn(12);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//納品先MAIL
		column = columnModel01.getColumn(13);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//コメント1
		column = columnModel01.getColumn(14);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//コメント2
		column = columnModel01.getColumn(15);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//コメント3
		column = columnModel01.getColumn(16);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//基幹システム発着地コード
		column = columnModel01.getColumn(17);	column.setPreferredWidth(100*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//削除区分
		
		//スクロール用設定
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
													+"納品先コード,部署CD,納品先名1,納品先名2,納品先名3,\n"
													+"納品先郵便,納品先住所1,納品先住所2,納品先住所3\n"
													+",納品先電話,納品先FAX,納品先MAIL,\n"
													+"コメント1,コメント2,コメント3,基幹システム発着地コード,削除区分\n"
													+"がヘッダに必要です");
			DeliveryMstExcelEntry(0,0,TgtFilePath);
		}else {
			int[] ClmnType = new int[HeaderRead[0].length];
			for(int i=0;i<ClmnType.length;i++) {ClmnType[i]=1;}
			
			ClmnType[TgtCol[ 0]]=1;	//納品先コード
			ClmnType[TgtCol[ 1]]=1;	//部署CD
			ClmnType[TgtCol[ 2]]=1;	//納品先名1
			ClmnType[TgtCol[ 3]]=1;	//納品先名2
			ClmnType[TgtCol[ 4]]=1;	//納品先名3
			ClmnType[TgtCol[ 5]]=1;	//納品先郵便
			ClmnType[TgtCol[ 6]]=1;	//納品先住所1
			ClmnType[TgtCol[ 7]]=1;	//納品先住所2
			ClmnType[TgtCol[ 8]]=1;	//納品先住所3
			ClmnType[TgtCol[ 9]]=1;	//納品先電話
			ClmnType[TgtCol[10]]=1;	//納品先FAX
			ClmnType[TgtCol[11]]=1;	//納品先MAIL
			ClmnType[TgtCol[12]]=1;	//コメント1
			ClmnType[TgtCol[13]]=1;	//コメント2
			ClmnType[TgtCol[14]]=1;	//コメント3
			ClmnType[TgtCol[15]]=1;	//基幹システム発着地コード
			ClmnType[TgtCol[16]]=1;	//削除区分
			
			Object[][] ExcellRead = B00060ToolsExcellControl.ExcellRead(TgtFilePath,SheetName,ClmnType,true);
			
			if(0<ExcellRead.length&&ClmnType.length<=ExcellRead[0].length) {
				for(int i=0;i<ExcellRead.length;i++) {
					Object[] SetOb = new Object[NeedCol.length+1];
					if(!"".equals(""+SetOb[0])) {
						SetOb[ 0] = false;
						SetOb[ 1] = ""+ExcellRead[i][TgtCol[ 0]];	//納品先コード
						SetOb[ 2] = ""+ExcellRead[i][TgtCol[ 1]];	//部署CD
						SetOb[ 3] = ""+ExcellRead[i][TgtCol[ 2]];	//納品先名1
						SetOb[ 4] = ""+ExcellRead[i][TgtCol[ 3]];	//納品先名2
						SetOb[ 5] = ""+ExcellRead[i][TgtCol[ 4]];	//納品先名3
						SetOb[ 6] = B00020ToolsTextControl.num_only_String(""+ExcellRead[i][TgtCol[ 5]]);	//納品先郵便
						SetOb[ 7] = ""+ExcellRead[i][TgtCol[ 6]];	//納品先住所1
						SetOb[ 8] = ""+ExcellRead[i][TgtCol[ 7]];	//納品先住所2
						SetOb[ 9] = ""+ExcellRead[i][TgtCol[ 8]];	//納品先住所3
						SetOb[10] = B00020ToolsTextControl.num_only_String(""+ExcellRead[i][TgtCol[ 9]]);	//納品先電話
						SetOb[11] = B00020ToolsTextControl.num_only_String(""+ExcellRead[i][TgtCol[10]]);	//納品先FAX
						SetOb[12] = ""+ExcellRead[i][TgtCol[11]];	//納品先MAIL
						SetOb[13] = ""+ExcellRead[i][TgtCol[12]];	//コメント1
						SetOb[14] = ""+ExcellRead[i][TgtCol[13]];	//コメント2
						SetOb[15] = ""+ExcellRead[i][TgtCol[14]];	//コメント3
						SetOb[16] = ""+ExcellRead[i][TgtCol[15]];	//基幹システム発着地コード
						SetOb[17] = ""+ExcellRead[i][TgtCol[16]];	//削除区分
						
						tableModel_ms01.addRow(SetOb);
					}
				}
			}
			main_fm.setVisible(true);
		}
		JLabel MSG				= B00110FrameParts.JLabelSet(  10,400,300,20,"選択行をメンテナンスする",11,0);
		main_fm.add(MSG);
		
		JLabel LB_DECD				= B00110FrameParts.JLabelSet(  0,425,100,20,"納品先CD:",		11,1);
		JLabel LB_DepartmentCd		= B00110FrameParts.JLabelSet(  0,450,100,20,"部署CD:",			11,1);
		JLabel LB_DEName01			= B00110FrameParts.JLabelSet(  0,475,100,20,"納品先名1:",		11,1);
		JLabel LB_DEName02			= B00110FrameParts.JLabelSet(  0,500,100,20,"納品先名2:",		11,1);
		JLabel LB_DEName03			= B00110FrameParts.JLabelSet(  0,525,100,20,"納品先名3:",		11,1);		
		JLabel LB_Post				= B00110FrameParts.JLabelSet(  0,550,100,20,"納品先郵便:",		11,1);
		JLabel LB_Add01				= B00110FrameParts.JLabelSet(  0,575,100,20,"納品先住所1:",	11,1);
		JLabel LB_Add02				= B00110FrameParts.JLabelSet(  0,600,100,20,"納品先住所2:",	11,1);
		JLabel LB_Add03				= B00110FrameParts.JLabelSet(  0,625,100,20,"納品先住所3:",	11,1);
		
		JLabel LB_Tel				= B00110FrameParts.JLabelSet(350,425,100,20,"納品先電話:",		11,1);
		JLabel LB_Fax				= B00110FrameParts.JLabelSet(350,450,100,20,"納品先FAX:",		11,1);
		JLabel LB_Mail				= B00110FrameParts.JLabelSet(350,475,100,20,"納品先MAIL:",		11,1);
		JLabel LB_Com01				= B00110FrameParts.JLabelSet(350,500,100,20,"コメント1:",		11,1);
		JLabel LB_Com02				= B00110FrameParts.JLabelSet(350,525,100,20,"コメント2:",		11,1);
		JLabel LB_Com03				= B00110FrameParts.JLabelSet(350,550,100,20,"コメント3:",		11,1);
		JLabel LB_DelFg				= B00110FrameParts.JLabelSet(350,575,100,20,"削除区分:",		11,1);
		JLabel LB_PTMSCD			= B00110FrameParts.JLabelSet(350,600,100,20,"基幹SYS発着CD:",	11,1);
		JLabel LB_Row				= B00110FrameParts.JLabelSet(350,625,100,20,"対象行:",			11,1);
		
		final JTextField TB_DECD			= B00110FrameParts.JTextFieldSet(100,425,100,20,"",11,0);			//納品先コード
		final JTextField TB_DepartmentCd	= B00110FrameParts.JTextFieldSet(100,450,100,20,"",11,0);			//部署CD
		final JTextField TB_DEName01		= B00110FrameParts.JTextFieldSet(100,475,250,20,"",11,0);			//納品先名1
		final JTextField TB_DEName02		= B00110FrameParts.JTextFieldSet(100,500,250,20,"",11,0);			//納品先名2
		final JTextField TB_DEName03		= B00110FrameParts.JTextFieldSet(100,525,250,20,"",11,0);			//納品先名3		
		final JTextField TB_Post			= B00110FrameParts.JTextFieldSet(100,550,100,20,"",11,0);			//納品先郵便
		final JTextField TB_Add01			= B00110FrameParts.JTextFieldSet(100,575,250,20,"",11,0);			//納品先住所1
		final JTextField TB_Add02			= B00110FrameParts.JTextFieldSet(100,600,250,20,"",11,0);			//納品先住所2
		final JTextField TB_Add03			= B00110FrameParts.JTextFieldSet(100,625,250,20,"",11,0);			//納品先住所3
		
		final JTextField TB_Tel				= B00110FrameParts.JTextFieldSet(450,425,100,20,"",11,0);			//納品先電話
		final JTextField TB_Fax				= B00110FrameParts.JTextFieldSet(450,450,100,20,"",11,0);			//納品先FAX
		final JTextField TB_Mail			= B00110FrameParts.JTextFieldSet(450,475,100,20,"",11,0);			//納品先MAIL		
		final JTextField TB_Com01			= B00110FrameParts.JTextFieldSet(450,500,250,20,"",11,0);			//コメント1
		final JTextField TB_Com02			= B00110FrameParts.JTextFieldSet(450,525,250,20,"",11,0);			//コメント2
		final JTextField TB_Com03			= B00110FrameParts.JTextFieldSet(450,550,250,20,"",11,0);			//コメント3
		final JComboBox  TB_DelFg			= B00110FrameParts.JComboBoxSet( 450,575,100,20,B00100DefaultVariable.DelList[0],11);	//削除区分
		final JTextField TB_PTMSCD			= B00110FrameParts.JTextFieldSet(450,600,100,20,"",11,0);			//基幹SYS発着CD:",	11,1);
		final JTextField TB_Row				= B00110FrameParts.JTextFieldSet(450,625,100,20,"",11,0);			//対象行:",			11,1);
		
		TB_Row.setEnabled(false);
		
		main_fm.add(LB_DECD);
		main_fm.add(LB_DepartmentCd);
		main_fm.add(LB_DEName01);
		main_fm.add(LB_DEName02);
		main_fm.add(LB_DEName03);
		main_fm.add(LB_Post);
		main_fm.add(LB_Add01);
		main_fm.add(LB_Add02);
		main_fm.add(LB_Add03);
		main_fm.add(LB_Tel);
		main_fm.add(LB_Fax);
		main_fm.add(LB_Mail);
		main_fm.add(LB_Com01);
		main_fm.add(LB_Com02);
		main_fm.add(LB_Com03);
		main_fm.add(LB_DelFg);
		main_fm.add(LB_PTMSCD);
		main_fm.add(LB_Row);

		main_fm.add(TB_DECD);
		main_fm.add(TB_DepartmentCd);
		main_fm.add(TB_DEName01);
		main_fm.add(TB_DEName02);
		main_fm.add(TB_DEName03);
		main_fm.add(TB_Post);
		main_fm.add(TB_Add01);
		main_fm.add(TB_Add02);
		main_fm.add(TB_Add03);
		main_fm.add(TB_Tel);
		main_fm.add(TB_Fax);
		main_fm.add(TB_Mail);
		main_fm.add(TB_Com01);
		main_fm.add(TB_Com02);
		main_fm.add(TB_Com03);
		main_fm.add(TB_DelFg);
		main_fm.add(TB_PTMSCD);
		main_fm.add(TB_Row);

		final JCheckBox NewDuplicationUnSet = B00110FrameParts.JCheckBoxSet(10,675,600,20,"新規登録は重複チェックして重複の可能性があれば取り込まない",11);
		NewDuplicationUnSet.setSelected(true);
		main_fm.add(NewDuplicationUnSet);
		
		JButton DuplicationCheckBtn = B00110FrameParts.BtnSet(600,600,100,20,"重複確認",11);
		JButton RenewBtn 			= B00110FrameParts.BtnSet(600,625,100,20,"行修正",11);
		main_fm.add(DuplicationCheckBtn);
		main_fm.add(RenewBtn);
		
		/*----------------------------------------------------
		同一届け先候補をリストアップ
		----------------------------------------------------*/
		final JFrame SameDelivery_fm = B00110FrameParts.FrameCreate(x,y,640,500,"Corgi00届先マスタ登録・更新","");
		JLabel SameDelivery_userinfo = B00110FrameParts.UserInfo();
		JButton SameDelivery_exit_btn = B00110FrameParts.ExitBtn();
		
		SameDelivery_fm.add(SameDelivery_userinfo);
		SameDelivery_fm.add(SameDelivery_exit_btn);
		JLabel LB_Msg  = B00110FrameParts.JLabelSet(	10,40,300,20,"以下の届先と重複していませんか？" ,11,0);
		SameDelivery_fm.add(LB_Msg);
		
		SameDelivery_exit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SameDelivery_fm.setVisible(false);
			}
		});
		
		String[] SDcolumnNames01 = {
								"Fg"
								,"届先CD"
								,"部署CD"
								,"届先名1"
								,"届先住所"};
		
		//編集可能カラムの指定
		B10010TableControl.RenewTgt = new int[1];
		B10010TableControl.RenewTgt[0] = 0;
		final DefaultTableModel SDtableModel_ms01 = new B10010TableControl.MyTableModel01(SDcolumnNames01,0);
		
		final JTable SDtb01 = new JTable(SDtableModel_ms01);
		SDtb01.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		SDtb01.setRowHeight(20*A00000Main.Mul/A00000Main.Div);
		SDtb01.setFont(new Font(A00000Main.DefaultFont, Font.PLAIN, 12*A00000Main.Mul/A00000Main.Div));
		
		DefaultTableColumnModel SDcolumnModel01
		= (DefaultTableColumnModel)SDtb01.getColumnModel();
		
		column = SDcolumnModel01.getColumn( 0);	column.setPreferredWidth( 30*A00000Main.Mul/A00000Main.Div);	//FG
		column = SDcolumnModel01.getColumn( 1);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//届先CD
		column = SDcolumnModel01.getColumn( 2);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//部署CD
		column = SDcolumnModel01.getColumn( 3);	column.setPreferredWidth(250*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//届先名1
		column = SDcolumnModel01.getColumn( 4);	column.setPreferredWidth(250*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//届先住所
		
		//スクロール用設定
		JScrollPane SDscpn01 = B00110FrameParts.JScrollPaneSet(10,60,610,300,SDtb01);
		SameDelivery_fm.add(SDscpn01);
		
		//上書きボタン
		JLabel LB_CdEntry  = B00110FrameParts.JLabelSet(	310,360,150,20,"チェックした届先を" ,11,0);
		SameDelivery_fm.add(LB_CdEntry);
		JButton CdEntryBtn = B00110FrameParts.BtnSet(		310,380,100,20,"上書",11);
		SameDelivery_fm.add(CdEntryBtn);
		
		//新規部署扱い
		JLabel LB_NewDept  = B00110FrameParts.JLabelSet(	460,360,150,20,"チェックした届先の" ,11,0);
		SameDelivery_fm.add(LB_NewDept);
		JButton NewDeptBtn = B00110FrameParts.BtnSet(		460,380,100,20,"新規部署扱い",11);
		SameDelivery_fm.add(NewDeptBtn);
		
		RenewFg = true;
		
		//上書きボタン押下時の挙動
		CdEntryBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					int RowCount = SDtableModel_ms01.getRowCount();
					boolean KickFg = false;
					
					String GetDECD = "";
					String GetDepartmentCd = "";
					
					for(int i=0;i<RowCount;i++) {
						if((boolean)SDtableModel_ms01.getValueAt(i, 0)) {
							GetDECD = ""+SDtableModel_ms01.getValueAt(i, 1);
							GetDepartmentCd = ""+SDtableModel_ms01.getValueAt(i, 2);
							KickFg = true;
						}
					}
					if(KickFg) {
						TB_DECD.setText(GetDECD);
						TB_DepartmentCd.setText(GetDepartmentCd);
						SameDelivery_fm.setVisible(false);
					}
					
					RenewFg = true;
				}
			}
		});
		
		//新規部署扱いボタン押下時の挙動
		NewDeptBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					int RowCount = SDtableModel_ms01.getRowCount();
					boolean KickFg = false;
					
					String GetDECD = "";
					String GetDepartmentCd = "";
					
					for(int i=0;i<RowCount;i++) {
						if((boolean)SDtableModel_ms01.getValueAt(i, 0)) {
							GetDECD = ""+SDtableModel_ms01.getValueAt(i, 1);
							GetDepartmentCd = ""+SDtableModel_ms01.getValueAt(i, 2);
							KickFg = true;
						}
					}
					if(KickFg) {
						String NewDepartmentCd = M00040DeliveryMstRt.NewDepartmentCd(GetDECD);
						//既に別部署扱いで部署コード採番されていた場合部署コード＋1
						RowCount = tableModel_ms01.getRowCount();
						for(int i=0;i<RowCount;i++) {
							if(!(boolean)tableModel_ms01.getValueAt(i, 0) && GetDECD.equals(""+tableModel_ms01.getValueAt(i, 1)))
							if(Integer.parseInt(NewDepartmentCd)<=Integer.parseInt(""+tableModel_ms01.getValueAt(i, 2))) {
								int wint = Integer.parseInt(NewDepartmentCd)+1;
								if(9999<wint) {
									NewDepartmentCd = ""+wint;
								}else {
									NewDepartmentCd = ("0000"+wint).substring(("0000"+wint).length()-4,("0000"+wint).length());
								}
							}
						}
						
						TB_DECD.setText(GetDECD);
						TB_DepartmentCd.setText(NewDepartmentCd);
						SameDelivery_fm.setVisible(false);
					}
					RenewFg = true;
				}
			}
		});
		
		//チェックボックス操作時の挙動
		SDtableModel_ms01.addTableModelListener(new TableModelListener(){
			public void tableChanged(TableModelEvent e){
				if(RenewFg) {
					RenewFg = false;
					int row_count = SDtableModel_ms01.getRowCount();
					Boolean setBL=Boolean.valueOf(false);
					for(int i=0;i<row_count;i++){
						if(i!=e.getFirstRow()){
							SDtableModel_ms01.setValueAt(setBL, i, 0);
						}else {
							
						}
					}
					RenewFg = true;
				}
			}
		});
		
		
		//重複確認ボタン押下時の挙動
		DuplicationCheckBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					
					int RowCount = SDtableModel_ms01.getRowCount();
					for(int i=0;i<RowCount;i++) {SDtableModel_ms01.removeRow(0);}
					
					String GetDECD			= TB_DECD.getText();
					String GetDepartmentCd	= TB_DepartmentCd.getText();
					String GetPost			= TB_Post.getText();
					String GetTel			= TB_Tel.getText();
					
					if(null==GetDECD			){GetDECD = "";}
					if(null==GetDepartmentCd	){GetDepartmentCd = "";}
					if(null==GetPost			){GetPost = "";}
					if(null==GetTel				){GetTel = "";}
					
					GetDECD				= B00020ToolsTextControl.Trim(GetDECD);
					GetDepartmentCd		= B00020ToolsTextControl.Trim(GetDepartmentCd);
					GetPost				= B00020ToolsTextControl.Trim(GetPost);
					GetTel				= B00020ToolsTextControl.Trim(GetTel);
					
					GetPost				= B00020ToolsTextControl.num_only_String(GetPost);
					GetTel				= B00020ToolsTextControl.num_only_String(GetTel);
					
					if(!"".equals(GetTel)) {
						Object[][] TelDuplicationCheck = TelDuplicationCheck(GetDECD,GetDepartmentCd,GetPost,GetTel);
						boolean KickFg = false;
						
						for(int i=0;i<TelDuplicationCheck.length;i++) {
							//郵便番号一致で重複候補とする
							//郵便番号空白なら電話番号で重複候補とする
							if(GetPost.equals(""+TelDuplicationCheck[i][5])
									||GetPost.equals("")) {
								Object[] SetOb = new Object[5];
								SetOb[ 0] = false;
								SetOb[ 1] = ""+TelDuplicationCheck[i][0];	//届先CD
								SetOb[ 2] = ""+TelDuplicationCheck[i][1];	//部署CD
								SetOb[ 3] = ""+TelDuplicationCheck[i][2];	//届先名
								SetOb[ 4] = ""+TelDuplicationCheck[i][6]+TelDuplicationCheck[i][7]+TelDuplicationCheck[i][8];	//届先住所
								
								SDtableModel_ms01.addRow(SetOb);
								KickFg = true;
							}
						}
						
						if(KickFg) {
							SameDelivery_fm.setVisible(true);
						}else {
							JOptionPane.showMessageDialog(null, "重複候補は見つけられませんでした");
						}
					}
					
					RenewFg = true;
				}
			}
		});
		
		//登録ボタン押下時の挙動
		entry_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					boolean DuplicationUnSet = NewDuplicationUnSet.isSelected();
					
					Object[][] TelDuplicationCheckAny = new Object[0][27];
					int RowCount = tableModel_ms01.getRowCount();
					String[] GetDECDList			= new String[RowCount];
					String[] GetDepartmentCdList	= new String[RowCount];
					String[] GetPostList			= new String[RowCount];
					String[] GetTelList				= new String[RowCount];
					
					if(DuplicationUnSet) {
						for(int i=0;i<RowCount;i++) {
							GetDECDList[i]			= ""+tableModel_ms01.getValueAt(i, 1);
							GetDepartmentCdList[i]	= ""+tableModel_ms01.getValueAt(i, 2);
							GetPostList[i]			= ""+tableModel_ms01.getValueAt(i, 6);
							GetTelList[i]			= ""+tableModel_ms01.getValueAt(i, 10);
							
							if(null==GetDECDList[i]			){GetDECDList[i] 			= "";}
							if(null==GetDepartmentCdList[i]	){GetDepartmentCdList[i] 	= "";}
							if(null==GetPostList[i]			){GetPostList[i] 			= "";}
							if(null==GetTelList[i]			){GetTelList[i] 			= "";}

							GetDECDList[i]			= B00020ToolsTextControl.Trim(GetDECDList[i]);
							GetDepartmentCdList[i]	= B00020ToolsTextControl.Trim(GetDepartmentCdList[i]);
							GetPostList[i]			= B00020ToolsTextControl.Trim(GetPostList[i]);
							GetTelList[i]			= B00020ToolsTextControl.Trim(GetTelList[i]);
							
							GetPostList[i]			= B00020ToolsTextControl.num_only_String(GetPostList[i]);
							GetTelList[i]			= B00020ToolsTextControl.num_only_String(GetTelList[i]);
						}
						
						TelDuplicationCheckAny = TelDuplicationCheckAny(GetDECDList,GetDepartmentCdList,GetPostList,GetTelList);
					}
					//重複チェック⇒重複分除外　※重複チェックしない場合TelDuplicationCheckAnyがゼロ行なのでHitしない
					//郵便番号空白なら電話番号のみで重複チェック
					int TgtCount = 0;
					for(int i=0;i<RowCount;i++) {
						boolean UnHitFg = true;
						boolean MineFg = false;
						for(int i01=0;i01<TelDuplicationCheckAny.length;i01++) {
							if((GetPostList[i].equals(""+TelDuplicationCheckAny[i01][5])&&GetTelList[i].equals(""+TelDuplicationCheckAny[i01][9]))
									||(GetPostList[i].equals("")&&GetTelList[i].equals(""+TelDuplicationCheckAny[i01][9]))
									) {
								UnHitFg = false;
							}
							if(GetDECDList[i].equals(""+TelDuplicationCheckAny[i01][0])) {
								MineFg = true;
							}
						}
						
						if(MineFg) {UnHitFg = true;}
						
						if(UnHitFg) {
							TgtCount = TgtCount+1;
						}
					}
					if(0<TgtCount) {
						String now_dtm = B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[1];
						
						String[][] SetString = {
								{"DECD"				,"1","1"}	//納品先コード
								,{"DepartmentCd"	,"1","1"}	//部署CD
								,{"DEName01"		,"1","1"}	//納品先名1
								,{"DEName02"		,"1","1"}	//納品先名2
								,{"DEName03"		,"1","1"}	//納品先名3
								,{"Post"			,"1","1"}	//納品先郵便
								,{"Add01"			,"1","1"}	//納品先住所1
								,{"Add02"			,"1","1"}	//納品先住所2
								,{"Add03"			,"1","1"}	//納品先住所3
								,{"Tel"				,"1","1"}	//納品先電話
								,{"Fax"				,"1","1"}	//納品先FAX
								,{"Mail"			,"1","1"}	//納品先MAIL
								,{"Com01"			,"1","1"}	//コメント1
								,{"Com02"			,"1","1"}	//コメント2
								,{"Com03"			,"1","1"}	//コメント3
								,{"PrefecturesCd"	,"1","1"}	//JIS県CD2桁
								,{"MunicipalityCd"	,"1","1"}	//JIS市区町村CD5桁
								,{"PTMSCD"			,"1","1"}	//基幹システム発着地コード
								,{"EntryDate"		,"1","0"}	//データ登録日時
								,{"UpdateDate"		,"1","1"}	//データ更新日時
								,{"EntryUser"		,"1","0"}	//登録者コード
								,{"UpdateUser"		,"1","1"}	//更新者コード
								,{"FirstClient"		,"1","0"}	//登録した荷主CD
								,{"LastClient"		,"1","1"}	//更新した荷主CD
								,{"DelFg"			,"1","1"}	//削除区分

						};
						
						String tgt_table = "KM0040_DELIVERYMST";
						String[][] field_name = new String[SetString.length][3];
						String[][] entry_data = new String[TgtCount][SetString.length];
						String[] judg_field = new String[2];
						String[][] judg_data = new String[TgtCount][2];
						String TgtDB = "NYANKO";
						int non_msg_fg = 0;

						judg_field[0] = "DECD";				//納品先コード
						judg_field[1] = "DepartmentCd";		//部署CD
						
						
						for(int i=0;i<SetString.length;i++) {
							field_name[i][0] = SetString[i][0];
							field_name[i][1] = SetString[i][1];
							field_name[i][2] = SetString[i][2];
						}
						
						String GetDECD			= "";	//納品先コード
						String GetDepartmentCd	= "";	//部署CD
						String GetDEName01		= "";	//納品先名1
						String GetDEName02		= "";	//納品先名2
						String GetDEName03		= "";	//納品先名3
						String GetPost			= "";	//納品先郵便
						String GetAdd01			= "";	//納品先住所1
						String GetAdd02			= "";	//納品先住所2
						String GetAdd03			= "";	//納品先住所3
						String GetTel			= "";	//納品先電話
						String GetFax			= "";	//納品先FAX
						String GetMail			= "";	//納品先MAIL
						String GetCom01			= "";	//コメント1
						String GetCom02			= "";	//コメント2
						String GetCom03			= "";	//コメント3
						String GetPTMSCD		= "";	//基幹システム発着地コード
						String GetDelFg			= "";	//削除区分
						
						TgtCount = 0;
						ArrayList<Integer> RemoveRow = new ArrayList<Integer>();
						int NeedDeCdCount = 0;
						
						for(int i=0;i<RowCount;i++) {
							GetDECD				= ""+tableModel_ms01.getValueAt(i, 1);	//納品先コード
							GetDepartmentCd		= ""+tableModel_ms01.getValueAt(i, 2);	//部署CD
							GetDEName01			= ""+tableModel_ms01.getValueAt(i, 3);	//納品先名1
							GetDEName02			= ""+tableModel_ms01.getValueAt(i, 4);	//納品先名2
							GetDEName03			= ""+tableModel_ms01.getValueAt(i, 5);	//納品先名3
							GetPost				= ""+tableModel_ms01.getValueAt(i, 6);	//納品先郵便
							GetAdd01			= ""+tableModel_ms01.getValueAt(i, 7);	//納品先住所1
							GetAdd02			= ""+tableModel_ms01.getValueAt(i, 8);	//納品先住所2
							GetAdd03			= ""+tableModel_ms01.getValueAt(i, 9);	//納品先住所3
							GetTel				= ""+tableModel_ms01.getValueAt(i,10);	//納品先電話
							GetFax				= ""+tableModel_ms01.getValueAt(i,11);	//納品先FAX
							GetMail				= ""+tableModel_ms01.getValueAt(i,12);	//納品先MAIL
							GetCom01			= ""+tableModel_ms01.getValueAt(i,13);	//コメント1
							GetCom02			= ""+tableModel_ms01.getValueAt(i,14);	//コメント2
							GetCom03			= ""+tableModel_ms01.getValueAt(i,15);	//コメント3
							GetPTMSCD			= ""+tableModel_ms01.getValueAt(i,16);	//基幹システム発着地コード
							GetDelFg			= ""+tableModel_ms01.getValueAt(i,17);	//削除区分
							
							if(null==GetDECD			){GetDECD 			= "";}
							if(null==GetDepartmentCd	){GetDepartmentCd 	= "";}
							if(null==GetDEName01		){GetDEName01 		= "";}
							if(null==GetDEName02		){GetDEName02 		= "";}
							if(null==GetDEName03		){GetDEName03 		= "";}
							if(null==GetPost			){GetPost 			= "";}
							if(null==GetAdd01			){GetAdd01 			= "";}
							if(null==GetAdd02			){GetAdd02 			= "";}
							if(null==GetAdd03			){GetAdd03 			= "";}
							if(null==GetTel				){GetTel 			= "";}
							if(null==GetFax				){GetFax 			= "";}
							if(null==GetMail			){GetMail 			= "";}
							if(null==GetCom01			){GetCom01 			= "";}
							if(null==GetCom02			){GetCom02 			= "";}
							if(null==GetCom03			){GetCom03 			= "";}
							if(null==GetPTMSCD			){GetPTMSCD 		= "";}
							if(null==GetDelFg			){GetDelFg 			= "";}
							
							GetDECD				= B00020ToolsTextControl.Trim(GetDECD);
							GetDepartmentCd		= B00020ToolsTextControl.Trim(GetDepartmentCd);
							GetDEName01			= B00020ToolsTextControl.Trim(GetDEName01);
							GetDEName02			= B00020ToolsTextControl.Trim(GetDEName02);
							GetDEName03			= B00020ToolsTextControl.Trim(GetDEName03);
							GetPost				= B00020ToolsTextControl.Trim(GetPost);
							GetAdd01			= B00020ToolsTextControl.Trim(GetAdd01);
							GetAdd02			= B00020ToolsTextControl.Trim(GetAdd02);
							GetAdd03			= B00020ToolsTextControl.Trim(GetAdd03);
							GetTel				= B00020ToolsTextControl.Trim(GetTel);
							GetFax				= B00020ToolsTextControl.Trim(GetFax);
							GetMail				= B00020ToolsTextControl.Trim(GetMail);
							GetCom01			= B00020ToolsTextControl.Trim(GetCom01);
							GetCom02			= B00020ToolsTextControl.Trim(GetCom02);
							GetCom03			= B00020ToolsTextControl.Trim(GetCom03);
							GetPTMSCD			= B00020ToolsTextControl.Trim(GetPTMSCD);
							GetDelFg			= B00020ToolsTextControl.Trim(GetDelFg);
							
							GetPost				= B00020ToolsTextControl.num_only_String(GetPost);
							GetTel				= B00020ToolsTextControl.num_only_String(GetTel);
							GetFax				= B00020ToolsTextControl.num_only_String(GetFax);
							GetDelFg			= B00020ToolsTextControl.num_only_String(GetDelFg);
							
							if("".equals(GetDelFg)) {GetDelFg = "0";}
							
							boolean UnHitFg = true;
							boolean MineFg = false;
							
							for(int i01=0;i01<TelDuplicationCheckAny.length;i01++) {
								if(GetPost.equals(""+TelDuplicationCheckAny[i01][5])&&GetTel.equals(""+TelDuplicationCheckAny[i01][9])) {
									UnHitFg = false;
								}
								if(GetDECD.equals(""+TelDuplicationCheckAny[i01][0])) {
									MineFg = true;
								}
							}
							
							if(MineFg) {UnHitFg = true;}
							
							if(UnHitFg) {
								RemoveRow.add(i);
								
								judg_data[TgtCount][0] = GetDECD;			//納品先コード
								judg_data[TgtCount][1] = GetDepartmentCd;	//部署CD
								
								entry_data[TgtCount][ 0] = GetDECD;			//納品先コード
								entry_data[TgtCount][ 1] = GetDepartmentCd; //部署CD
								entry_data[TgtCount][ 2] = GetDEName01; 	//納品先名1
								entry_data[TgtCount][ 3] = GetDEName02; 	//納品先名2
								entry_data[TgtCount][ 4] = GetDEName03; 	//納品先名3
								entry_data[TgtCount][ 5] = GetPost;			//納品先郵便
								entry_data[TgtCount][ 6] = GetAdd01; 		//納品先住所1
								entry_data[TgtCount][ 7] = GetAdd02; 		//納品先住所2
								entry_data[TgtCount][ 8] = GetAdd03; 		//納品先住所3
								entry_data[TgtCount][ 9] = GetTel; 			//納品先電話
								entry_data[TgtCount][10] = GetFax; 			//納品先FAX
								entry_data[TgtCount][11] = GetMail;			//納品先MAIL
								entry_data[TgtCount][12] = GetCom01;		//コメント1
								entry_data[TgtCount][13] = GetCom02;		//コメント2
								entry_data[TgtCount][14] = GetCom03;		//コメント3
								entry_data[TgtCount][15] = "";				//JIS県CD2桁
								entry_data[TgtCount][16] = "";				//JIS市区町村CD5桁
								entry_data[TgtCount][17] = GetPTMSCD;		//基幹システム発着地コード
								entry_data[TgtCount][18] = now_dtm;			//データ登録日時
								entry_data[TgtCount][19] = now_dtm;			//データ更新日時
								entry_data[TgtCount][20] = "(" + A00000Main.LoginUserId + ")" + A00000Main.LoginUserName;	//登録者コード
								entry_data[TgtCount][21] = "(" + A00000Main.LoginUserId + ")" + A00000Main.LoginUserName;	//更新者コード
								entry_data[TgtCount][22] = A00000Main.ClCd;	//登録した荷主CD
								entry_data[TgtCount][23] = A00000Main.ClCd;	//更新した荷主CD
								entry_data[TgtCount][24] = GetDelFg;		//削除区分
								
								//部署コード空白なら"0000"
								if("".equals(entry_data[TgtCount][ 1])) {
									entry_data[TgtCount][ 1] = "0000";
									judg_data[TgtCount][1] = "0000";
								}
								//納品先コード空白なら新規採番必要件数加算
								if("".equals(GetDECD)) {
									NeedDeCdCount = NeedDeCdCount+1;
								}
								
								TgtCount = TgtCount+1;
							}
						}
						//郵便番号を元にJIS市区町村CD判定
						ArrayList<String> SearchPOST = new ArrayList<String>();
						ArrayList<String> SearchAdd = new ArrayList<String>();
						boolean AllSearch = false;
						boolean PostPerfectMatch = true;
						
						for(int i=0;i<entry_data.length;i++) {
							if(!"".equals(entry_data[i][ 5])) {
								SearchPOST.add(entry_data[i][ 5]);
							}
						}
						
						Object[][] PostRt = M10010PostMstRt.PostRt(
																SearchPOST,
																SearchAdd,
																AllSearch,
																PostPerfectMatch);
						
						int AddToJisCount = 0;
						for(int i=0;i<entry_data.length;i++) {
							for(int i01=0;i01<PostRt.length;i01++) {
								if(entry_data[i][ 5].equals(""+PostRt[i01][0])) {
									entry_data[i][16] = ""+PostRt[i01][4];
									if(2<(""+PostRt[i01][4]).length()) {
										entry_data[i][15] = (""+PostRt[i01][4]).substring(0,2);
									}else {
										entry_data[i][15] = "00";
									}
									i01=PostRt.length+1;
								}
							}
							if("".equals(entry_data[i][16])) {
								AddToJisCount = AddToJisCount+1;
							}
						}
						//郵便番号でJIS市区町村CD判定できなかったデータについて住所でJIS判定
						if(0<AddToJisCount) {
							String[] AddList = new String[AddToJisCount];
							AddToJisCount = 0;
							for(int i=0;i<entry_data.length;i++) {
								if("".equals(entry_data[i][16])) {
									AddList[AddToJisCount] = entry_data[i][ 6]+entry_data[i][ 7]+entry_data[i][ 8];
									
									AddToJisCount = AddToJisCount+1;
								}
							}
							String[][] AddToMunicipality = M10010PostMstRt.AddToMunicipality(AddList);
							for(int i=0;i<entry_data.length;i++) {
								if("".equals(entry_data[i][16])) {
									for(int i01=0;i01<AddToMunicipality.length;i01++) {
										if((entry_data[i][ 6]+entry_data[i][ 7]+entry_data[i][ 8]).equals(""+AddToMunicipality[i01][0])) {
											entry_data[i][16] = ""+AddToMunicipality[i01][1];
											if(2<entry_data[i][16].length()) {
												entry_data[i][15] = entry_data[i][16].substring(0,2);
											}else {
												entry_data[i][15] = "00";
											}
										}
									}
								}
							}
						}
						//JIS判定できなかったデータについてはJIS"00000"扱いで登録
						for(int i=0;i<entry_data.length;i++) {
							if("".equals(entry_data[i][16])) {
								entry_data[i][15] = "00";
								entry_data[i][16] = "00000";
							}
						}
						//新規届け先コード必要件数分採番
						String[] DeliveryCdGet = M00040DeliveryMstRt.DeliveryCdGet(NeedDeCdCount);
						NeedDeCdCount = 0;
						for(int i=0;i<entry_data.length;i++) {
							if("".equals(judg_data[i][0])) {
								judg_data[i][0] = DeliveryCdGet[NeedDeCdCount];			//納品先コード
								judg_data[i][1] = "0000";	//部署CD
								
								entry_data[i][ 0] = DeliveryCdGet[NeedDeCdCount];			//納品先コード
								entry_data[i][ 1] = "0000"; //部署CD
								NeedDeCdCount = NeedDeCdCount+1;
							}
						}
						
						A00020InsertUdateSQL.RUN_SQLS_EU(tgt_table, field_name, entry_data, judg_field, judg_data, non_msg_fg,TgtDB);
						
						//登録済み除外
						if(null!=RemoveRow && 0<RemoveRow.size()) {
							for(int i=0;i<RemoveRow.size();i++) {
								tableModel_ms01.removeRow(RemoveRow.get(RemoveRow.size()-i-1));
							}
						}
						
						//除外した結果0行になったら検索画面に戻る
						RowCount = tableModel_ms01.getRowCount();
						if(0>=RowCount) {
							JOptionPane.showMessageDialog(null, "全件登録完了　届先検索画面に戻ります");
							SetX=main_fm.getX();
							SetY=main_fm.getY();
							
							SameDelivery_fm.setVisible(false);
							SameDelivery_fm.dispose();

							main_fm.setVisible(false);
							main_fm.dispose();
							WM00060DeliveryMstSearch.DeliveryMstSearch(0, 0);
						}
					}
					
					RenewFg = true;
				}
			}
		});
		
		//行修正ボタン押下時の挙動
		RenewBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					String GetDECD			= TB_DECD.getText();
					String GetDepartmentCd	= TB_DepartmentCd.getText();
					String GetDEName01		= TB_DEName01.getText();
					String GetDEName02		= TB_DEName02.getText();
					String GetDEName03		= TB_DEName03.getText();
					String GetPost			= TB_Post.getText();
					String GetAdd01			= TB_Add01.getText();
					String GetAdd02			= TB_Add02.getText();
					String GetAdd03			= TB_Add03.getText();
					String GetTel			= TB_Tel.getText();
					String GetFax			= TB_Fax.getText();
					String GetMail			= TB_Mail.getText();
					String GetCom01			= TB_Com01.getText();
					String GetCom02			= TB_Com02.getText();
					String GetCom03			= TB_Com03.getText();
					String GetPTMSCD		= TB_PTMSCD.getText();
					String GetDelFg 		= B00100DefaultVariable.DelList[1][TB_DelFg.getSelectedIndex()];
					String GetRow 			= TB_Row.getText();
					
					if(null==GetDECD			){GetDECD = "";}
					if(null==GetDepartmentCd	){GetDepartmentCd = "";}
					if(null==GetDEName01		){GetDEName01 = "";}
					if(null==GetDEName02		){GetDEName02 = "";}
					if(null==GetDEName03		){GetDEName03 = "";}
					if(null==GetPost			){GetPost = "";}
					if(null==GetAdd01			){GetAdd01 = "";}
					if(null==GetAdd02			){GetAdd02 = "";}
					if(null==GetAdd03			){GetAdd03 = "";}
					if(null==GetTel				){GetTel = "";}
					if(null==GetFax				){GetFax = "";}
					if(null==GetMail			){GetMail = "";}
					if(null==GetCom01			){GetCom01 = "";}
					if(null==GetCom02			){GetCom02 = "";}
					if(null==GetCom03			){GetCom03 = "";}
					if(null==GetPTMSCD			){GetPTMSCD = "";}
					if(null==GetDelFg			){GetDelFg = "";}
					if(null==GetRow				){GetRow = "";}
					
					GetDECD				= B00020ToolsTextControl.Trim(GetDECD);
					GetDepartmentCd		= B00020ToolsTextControl.Trim(GetDepartmentCd);
					GetDEName01			= B00020ToolsTextControl.Trim(GetDEName01);
					GetDEName02			= B00020ToolsTextControl.Trim(GetDEName02);
					GetDEName03			= B00020ToolsTextControl.Trim(GetDEName03);
					GetPost				= B00020ToolsTextControl.Trim(GetPost);
					GetAdd01			= B00020ToolsTextControl.Trim(GetAdd01);
					GetAdd02			= B00020ToolsTextControl.Trim(GetAdd02);
					GetAdd03			= B00020ToolsTextControl.Trim(GetAdd03);
					GetTel				= B00020ToolsTextControl.Trim(GetTel);
					GetFax				= B00020ToolsTextControl.Trim(GetFax);
					GetMail				= B00020ToolsTextControl.Trim(GetMail);
					GetCom01			= B00020ToolsTextControl.Trim(GetCom01);
					GetCom02			= B00020ToolsTextControl.Trim(GetCom02);
					GetCom03			= B00020ToolsTextControl.Trim(GetCom03);
					GetPTMSCD			= B00020ToolsTextControl.Trim(GetPTMSCD);
					GetDelFg			= B00020ToolsTextControl.Trim(GetDelFg);
					GetRow				= B00020ToolsTextControl.Trim(GetRow);
					
					GetPost				= B00020ToolsTextControl.num_only_String(GetPost);
					GetTel				= B00020ToolsTextControl.num_only_String(GetTel);
					GetFax				= B00020ToolsTextControl.num_only_String(GetFax);
					GetRow				= B00020ToolsTextControl.num_only_String(GetRow);
					
					if(!"".equals(GetRow)) {
						int TgtRow = Integer.parseInt(GetRow);
						tableModel_ms01.setValueAt(GetDECD			, TgtRow, 1);
						tableModel_ms01.setValueAt(GetDepartmentCd	, TgtRow, 2);
						tableModel_ms01.setValueAt(GetDEName01		, TgtRow, 3);
						tableModel_ms01.setValueAt(GetDEName02		, TgtRow, 4);
						tableModel_ms01.setValueAt(GetDEName03		, TgtRow, 5);
						tableModel_ms01.setValueAt(GetPost			, TgtRow, 6);
						tableModel_ms01.setValueAt(GetAdd01			, TgtRow, 7);
						tableModel_ms01.setValueAt(GetAdd02			, TgtRow, 8);
						tableModel_ms01.setValueAt(GetAdd03			, TgtRow, 9);
						tableModel_ms01.setValueAt(GetTel			, TgtRow,10);
						tableModel_ms01.setValueAt(GetFax			, TgtRow,11);
						tableModel_ms01.setValueAt(GetMail			, TgtRow,12);
						tableModel_ms01.setValueAt(GetCom01			, TgtRow,13);
						tableModel_ms01.setValueAt(GetCom02			, TgtRow,14);
						tableModel_ms01.setValueAt(GetCom03			, TgtRow,15);
						tableModel_ms01.setValueAt(GetPTMSCD		, TgtRow,16);
						tableModel_ms01.setValueAt(GetDelFg			, TgtRow,17);
						
						tableModel_ms01.setValueAt(false			, TgtRow, 0);
						
						TB_DECD.setText("");
						TB_DepartmentCd.setText("");
						TB_DEName01.setText("");
						TB_DEName02.setText("");
						TB_DEName03.setText("");	
						TB_Post.setText("");
						TB_Add01.setText("");
						TB_Add02.setText("");
						TB_Add03.setText("");
						TB_Tel.setText("");
						TB_Fax.setText("");
						TB_Mail.setText("");	
						TB_Com01.setText("");
						TB_Com02.setText("");
						TB_Com03.setText("");
						TB_PTMSCD.setText("");
						TB_Row.setText("");
						TB_DelFg.setSelectedIndex(0);
					}
					RenewFg = true;
				}
			}
		});
		
		//チェックボックス操作時の挙動
		tableModel_ms01.addTableModelListener(new TableModelListener(){
			public void tableChanged(TableModelEvent e){
				if(RenewFg) {
					RenewFg = false;
					TB_DECD.setText("");
					TB_DepartmentCd.setText("");
					TB_DEName01.setText("");
					TB_DEName02.setText("");
					TB_DEName03.setText("");	
					TB_Post.setText("");
					TB_Add01.setText("");
					TB_Add02.setText("");
					TB_Add03.setText("");
					TB_Tel.setText("");
					TB_Fax.setText("");
					TB_Mail.setText("");	
					TB_Com01.setText("");
					TB_Com02.setText("");
					TB_Com03.setText("");
					TB_PTMSCD.setText("");
					TB_Row.setText("");
					TB_DelFg.setSelectedIndex(0);
					
					SameDelivery_fm.setVisible(false);
					
					int row_count = tb01.getRowCount();
					Boolean setBL=Boolean.valueOf(false);
					for(int i=0;i<row_count;i++){
						if(i!=e.getFirstRow()){
							tableModel_ms01.setValueAt(setBL, i, 0);
						}else {
							if((boolean)tableModel_ms01.getValueAt(i, 0)) {
								String GetDECD			= ""+tableModel_ms01.getValueAt(i, 1);
								String GetDepartmentCd	= ""+tableModel_ms01.getValueAt(i, 2);
								String GetDEName01		= ""+tableModel_ms01.getValueAt(i, 3);
								String GetDEName02		= ""+tableModel_ms01.getValueAt(i, 4);
								String GetDEName03		= ""+tableModel_ms01.getValueAt(i, 5);
								String GetPost			= ""+tableModel_ms01.getValueAt(i, 6);
								String GetAdd01			= ""+tableModel_ms01.getValueAt(i, 7);
								String GetAdd02			= ""+tableModel_ms01.getValueAt(i, 8);
								String GetAdd03			= ""+tableModel_ms01.getValueAt(i, 9);
								String GetTel			= ""+tableModel_ms01.getValueAt(i,10);
								String GetFax			= ""+tableModel_ms01.getValueAt(i,11);
								String GetMail			= ""+tableModel_ms01.getValueAt(i,12);
								String GetCom01			= ""+tableModel_ms01.getValueAt(i,13);
								String GetCom02			= ""+tableModel_ms01.getValueAt(i,14);
								String GetCom03			= ""+tableModel_ms01.getValueAt(i,15);
								String GetPTMSCD		= ""+tableModel_ms01.getValueAt(i,16);
								String GetDelFg 		= ""+tableModel_ms01.getValueAt(i,17);
								
								if(null==GetDECD			){GetDECD = "";}
								if(null==GetDepartmentCd	){GetDepartmentCd = "";}
								if(null==GetDEName01		){GetDEName01 = "";}
								if(null==GetDEName02		){GetDEName02 = "";}
								if(null==GetDEName03		){GetDEName03 = "";}
								if(null==GetPost			){GetPost = "";}
								if(null==GetAdd01			){GetAdd01 = "";}
								if(null==GetAdd02			){GetAdd02 = "";}
								if(null==GetAdd03			){GetAdd03 = "";}
								if(null==GetTel				){GetTel = "";}
								if(null==GetFax				){GetFax = "";}
								if(null==GetMail			){GetMail = "";}
								if(null==GetCom01			){GetCom01 = "";}
								if(null==GetCom02			){GetCom02 = "";}
								if(null==GetCom03			){GetCom03 = "";}
								if(null==GetPTMSCD			){GetPTMSCD = "";}
								if(null==GetDelFg			){GetDelFg = "";}
								
								GetDECD				= B00020ToolsTextControl.Trim(GetDECD);
								GetDepartmentCd		= B00020ToolsTextControl.Trim(GetDepartmentCd);
								GetDEName01			= B00020ToolsTextControl.Trim(GetDEName01);
								GetDEName02			= B00020ToolsTextControl.Trim(GetDEName02);
								GetDEName03			= B00020ToolsTextControl.Trim(GetDEName03);
								GetPost				= B00020ToolsTextControl.Trim(GetPost);
								GetAdd01			= B00020ToolsTextControl.Trim(GetAdd01);
								GetAdd02			= B00020ToolsTextControl.Trim(GetAdd02);
								GetAdd03			= B00020ToolsTextControl.Trim(GetAdd03);
								GetTel				= B00020ToolsTextControl.Trim(GetTel);
								GetFax				= B00020ToolsTextControl.Trim(GetFax);
								GetMail				= B00020ToolsTextControl.Trim(GetMail);
								GetCom01			= B00020ToolsTextControl.Trim(GetCom01);
								GetCom02			= B00020ToolsTextControl.Trim(GetCom02);
								GetCom03			= B00020ToolsTextControl.Trim(GetCom03);
								GetPTMSCD			= B00020ToolsTextControl.Trim(GetPTMSCD);
								GetDelFg			= B00020ToolsTextControl.Trim(GetDelFg);
								
								GetPost				= B00020ToolsTextControl.num_only_String(GetPost);
								GetTel				= B00020ToolsTextControl.num_only_String(GetTel);
								GetFax				= B00020ToolsTextControl.num_only_String(GetFax);
								
								TB_DECD.setText(GetDECD);
								TB_DepartmentCd.setText(GetDepartmentCd);
								TB_DEName01.setText(GetDEName01);
								TB_DEName02.setText(GetDEName02);
								TB_DEName03.setText(GetDEName03);	
								TB_Post.setText(GetPost);
								TB_Add01.setText(GetAdd01);
								TB_Add02.setText(GetAdd02);
								TB_Add03.setText(GetAdd03);
								TB_Tel.setText(GetTel);
								TB_Fax.setText(GetFax);
								TB_Mail.setText(GetMail);	
								TB_Com01.setText(GetCom01);
								TB_Com02.setText(GetCom02);
								TB_Com03.setText(GetCom03);
								TB_PTMSCD.setText(GetPTMSCD);
								TB_Row.setText(""+i);
								for(int i01=0;i01<B00100DefaultVariable.DelList[1].length;i01++) {
									if(GetDelFg.equals(B00100DefaultVariable.DelList[1][i01])) {
										TB_DelFg.setSelectedIndex(i01);
									}
								}
							}
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
				
				SameDelivery_fm.setVisible(false);
				SameDelivery_fm.dispose();

				main_fm.setVisible(false);
				main_fm.dispose();
				WM00060DeliveryMstSearch.DeliveryMstSearch(0, 0);
			}
		});
	}
	
	private static Object[][] TelDuplicationCheck(String GetDECD,String GetDepartmentCd,String GetPost,String GetTel){
		//重複候補届先配列を返却する
		String[] GetDECDList 			= {GetDECD};
		String[] GetDepartmentCdList 	= {GetDepartmentCd};
		String[] GetPostList 			= {GetPost};
		String[] GetTelList 			= {GetTel};
		
		Object[][] rt = TelDuplicationCheckAny(GetDECDList,GetDepartmentCdList,GetPostList,GetTelList);
		
		return rt;
	}
	
	private static Object[][] TelDuplicationCheckAny(String[] GetDECD,String[] GetDepartmentCd,String[] GetPost,String[] GetTel){
		//重複候補届先配列を返却する
		Object[][] rt = new Object[0][27];
		
		if(null==GetDECD){			GetDECD 		= new String[0];}
		if(null==GetDepartmentCd){	GetDepartmentCd	= new String[0];}
		if(null==GetPost){			GetPost 		= new String[0];}
		if(null==GetTel){			GetTel 			= new String[0];}
		
		//既に登録されている届先CDであれば更新の為、重複として扱わない
		ArrayList<String> SearchDECD = new ArrayList<String>();
		ArrayList<String> SearchDepartmentCd = new ArrayList<String>();
		ArrayList<String> SearchDEName = new ArrayList<String>();
		ArrayList<String> SearchPost = new ArrayList<String>();
		ArrayList<String> SearchAdd = new ArrayList<String>();
		ArrayList<String> SearchTel = new ArrayList<String>();
		ArrayList<String> SearchFax = new ArrayList<String>();
		ArrayList<String> SearchMail = new ArrayList<String>();
		ArrayList<String> SearchCom = new ArrayList<String>();
		ArrayList<String> SearchPrefecturesCd = new ArrayList<String>();
		ArrayList<String> SearchMunicipalityCd = new ArrayList<String>();
		ArrayList<String> SearchDelFg = new ArrayList<String>();
		boolean AllSearch = false;
		
		for(int i=0;i<GetDECD.length;i++) {
			if(!"".equals(GetDECD[i])) {SearchDECD.add(GetDECD[i]);}
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
			AllSearch
			);
		SearchDECD = new ArrayList<String>();
		
		for(int i=0;i<GetDECD.length;i++) {
			boolean UnHitFg = true;
			if(!"".equals(GetDECD[i])) {
				for(int i01=0;i01<DeliveryMstRt.length;i01++) {
					if((""+DeliveryMstRt[i01][0]).equals(GetDECD[i])) {
						UnHitFg = false;
					}
				}
			}
			
			if(UnHitFg) {
				if(!"".equals(GetTel[i])) {
					SearchTel.add(GetTel[i]);
				}
			}
		}
		
		//新規扱いの届先について同一電話番号の届先一覧を返却
		rt = M00040DeliveryMstRt.DeliveryMstRt(
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
				AllSearch
				);
		return rt;
	}
}