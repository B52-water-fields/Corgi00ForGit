import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JButton;
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

public class WM00071CautionMstRenewAndCreate{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	public static void CautionMstRenewAndCreate(int x,int y,String CautionCd,String DECD,String DepartmentCd) {
		A00000Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;

		if(null==CautionCd) {CautionCd = "";}
		if(null==DECD) {DECD = "";}
		if(null==DepartmentCd) {DepartmentCd = "";}
		
		final JFrame main_fm = B00110FrameParts.FrameCreate(x,y,500,650,"Corgi00届先注意事項マスタ登録・更新","");
		JLabel userinfo = B00110FrameParts.UserInfo();
		JButton exit_btn = B00110FrameParts.ExitBtn();
		JButton entry_btn = B00110FrameParts.EntryBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		main_fm.add(entry_btn);
		
		JLabel LB_CautionCd		= B00110FrameParts.JLabelSet(  0, 40,100,20,"注意事項CD:"			,11,1);
		JLabel LB_ClGpCD		= B00110FrameParts.JLabelSet(  0, 65,100,20,"荷主グループCD:"		,11,1);
		JLabel LB_CLGpName01	= B00110FrameParts.JLabelSet(  0, 90,100,20,"荷主グループ名:"		,11,1);
		JLabel LB_DECD			= B00110FrameParts.JLabelSet(  0,115,100,20,"届先コード:"			,11,1);
		JLabel LB_DepartmentCd	= B00110FrameParts.JLabelSet(  0,140,100,20,"部署CD:"				,11,1);
		JLabel LB_DEName01		= B00110FrameParts.JLabelSet(  0,165,100,20,"届先名:"				,11,1);
		JLabel LB_Add01			= B00110FrameParts.JLabelSet(  0,190,100,20,"届先住所1:"			,11,1);
		JLabel LB_Add02			= B00110FrameParts.JLabelSet(  0,215,100,20,"届先住所2:"			,11,1);
		JLabel LB_Add03			= B00110FrameParts.JLabelSet(  0,240,100,20,"届先住所3:"			,11,1);
		JLabel LB_CautionTiming	= B00110FrameParts.JLabelSet(  0,265,100,20,"注意タイミング:"		,11,1);
		JLabel LB_CautionName	= B00110FrameParts.JLabelSet(  0,290,100,20,"注意事項名:"			,11,1);
		JLabel LB_Caution		= B00110FrameParts.JLabelSet(  0,315,100,20,"注意事項内容:"		,11,1);
		JLabel LB_EntryDate		= B00110FrameParts.JLabelSet(  0,340,100,20,"データ登録日時:"		,11,1);
		JLabel LB_UpdateDate	= B00110FrameParts.JLabelSet(  0,365,100,20,"データ更新日時:"		,11,1);
		JLabel LB_EntryUser		= B00110FrameParts.JLabelSet(  0,390,100,20,"登録者コード:"		,11,1);
		JLabel LB_UpdateUser	= B00110FrameParts.JLabelSet(  0,415,100,20,"更新者コード:"		,11,1);
		
		final JTextField TB_CautionCd		= B00110FrameParts.JTextFieldSet(100, 40,100,20,"",11,0);			//注意事項コード
		final JTextField TB_ClGpCD			= B00110FrameParts.JTextFieldSet(100, 65,100,20,"",11,0);			//荷主グループコード
		final JTextField TB_CLGpName01		= B00110FrameParts.JTextFieldSet(100, 90,200,20,"",11,0);			//荷主グループ名
		final JTextField TB_DECD			= B00110FrameParts.JTextFieldSet(100,115,100,20,"",11,0);			//届先コード
		final JTextField TB_DepartmentCd	= B00110FrameParts.JTextFieldSet(100,140,100,20,"",11,0);			//部署CD
		final JTextField TB_DEName01		= B00110FrameParts.JTextFieldSet(100,165,200,20,"",11,0);			//届先名
		final JTextField TB_Add01			= B00110FrameParts.JTextFieldSet(100,190,200,20,"",11,0);			//届先住所1
		final JTextField TB_Add02			= B00110FrameParts.JTextFieldSet(100,215,200,20,"",11,0);			//届先住所2
		final JTextField TB_Add03			= B00110FrameParts.JTextFieldSet(100,240,200,20,"",11,0);			//届先住所3
		final JComboBox  TB_CautionTiming	= B00110FrameParts.JComboBoxSet( 100,265,100,20,B00100DefaultVariable.CautionTiming[0],11);	//注意事項タイミング
		final JTextField TB_CautionName		= B00110FrameParts.JTextFieldSet(100,290,150,20,"",11,0);			//注意事項名
		final JTextField TB_Caution			= B00110FrameParts.JTextFieldSet(100,315,300,20,"",11,0);			//注意事項内容
		final JTextField TB_EntryDate		= B00110FrameParts.JTextFieldSet(100,340,200,20,"",11,0);			//データ登録日時
		final JTextField TB_UpdateDate		= B00110FrameParts.JTextFieldSet(100,365,200,20,"",11,0);			//データ更新日時
		final JTextField TB_EntryUser		= B00110FrameParts.JTextFieldSet(100,390,200,20,"",11,0);			//登録者コード
		final JTextField TB_UpdateUser		= B00110FrameParts.JTextFieldSet(100,415,200,20,"",11,0);			//更新者コード
		
		if(!"".equals(CautionCd)&&!"".equals(DECD)) {
			ArrayList<String> SearchCautionCd 		= new ArrayList<String>();
			ArrayList<String> SearchClGpCD 			= new ArrayList<String>();
			ArrayList<String> SearchDECD 			= new ArrayList<String>();
			ArrayList<String> SearchDepartmentCd 	= new ArrayList<String>();
			ArrayList<String> SearchCautionTiming 	= new ArrayList<String>();
			ArrayList<String> SearchCautionName 	= new ArrayList<String>();
			ArrayList<String> SearchCaution 		= new ArrayList<String>();
			ArrayList<String> SearchDeName 			= new ArrayList<String>();
			boolean AllSearch = false;
			
			SearchCautionCd.add(CautionCd);
			SearchDECD.add(DECD);
			SearchDepartmentCd.add(DepartmentCd);
			
			Object[][] CautionMstRt = M00042CautionMstRt.CautionMstRt(
					SearchCautionCd,
					SearchClGpCD,
					SearchDECD,
					SearchDepartmentCd,
					SearchCautionTiming,
					SearchCautionName,
					SearchCaution,
					SearchDeName,
					AllSearch);
			
			for(int i=0;i<CautionMstRt.length;i++) {
				if(CautionCd.equals(""+CautionMstRt[i][0]) && DECD.equals(""+CautionMstRt[i][3])) {
					for(int i01=0;i01<B00100DefaultVariable.CautionTiming[1].length;i01++) {
						TB_CautionCd.setText(	""+CautionMstRt[i][ 0]);		//注意事項コード
						TB_ClGpCD.setText(		""+CautionMstRt[i][ 1]);		//荷主グループコード
						TB_CLGpName01.setText(	""+CautionMstRt[i][ 2]);		//荷主グループ名
						TB_DECD.setText(		""+CautionMstRt[i][ 3]);		//届先コード
						TB_DepartmentCd.setText(""+CautionMstRt[i][ 4]);		//部署CD
						TB_DEName01.setText(	""+CautionMstRt[i][ 5]);		//届先名
						TB_Add01.setText(		""+CautionMstRt[i][13]);		//届先住所1
						TB_Add02.setText(		""+CautionMstRt[i][14]);		//届先住所2
						TB_Add03.setText(		""+CautionMstRt[i][15]);		//届先住所3
						TB_CautionName.setText(	""+CautionMstRt[i][ 7]);		//注意事項名
						TB_Caution.setText(		""+CautionMstRt[i][ 8]);		//注意事項内容
						TB_EntryDate.setText(	""+CautionMstRt[i][ 9]);		//データ登録日時
						TB_UpdateDate.setText(	""+CautionMstRt[i][10]);		//データ更新日時
						TB_EntryUser.setText(	""+CautionMstRt[i][11]);		//登録者コード
						TB_UpdateUser.setText(	""+CautionMstRt[i][12]);		//更新者コード
						
						if(B00100DefaultVariable.CautionTiming[1][i01].equals(""+CautionMstRt[i][6])) {
							TB_CautionTiming.setSelectedIndex(i01);	//注意事項タイミング
						}
					}
				}
			}
		}
		
		String EntryCom01 = "荷主グループを指定した場合";
		String EntryCom02 = "    対象荷主グループの出荷にのみ注意事項適用されます";
		String EntryCom03 = "部署コードを指定した場合";
		String EntryCom04 = "    特定部署の出荷にのみ注意事項適用されます";
		
		JLabel LB_EntryCom01 = B00110FrameParts.JLabelSet( 10,465,400,20,EntryCom01,11,0);
		JLabel LB_EntryCom02 = B00110FrameParts.JLabelSet( 10,485,400,20,EntryCom02,11,0);
		JLabel LB_EntryCom03 = B00110FrameParts.JLabelSet( 10,510,400,20,EntryCom03,11,0);
		JLabel LB_EntryCom04 = B00110FrameParts.JLabelSet( 10,530,400,20,EntryCom04,11,0);
		
		main_fm.add(LB_EntryCom01);
		main_fm.add(LB_EntryCom02);
		main_fm.add(LB_EntryCom03);
		main_fm.add(LB_EntryCom04);
		
		TB_CautionCd.setEnabled(false);
		TB_CLGpName01.setEnabled(false);
		TB_DEName01.setEnabled(false);
		TB_Add01.setEnabled(false);
		TB_Add02.setEnabled(false);
		TB_Add03.setEnabled(false);
		TB_EntryDate.setEnabled(false);
		TB_UpdateDate.setEnabled(false);
		TB_EntryUser.setEnabled(false);
		TB_UpdateUser.setEnabled(false);
		
		main_fm.add(LB_CautionCd);
		main_fm.add(LB_ClGpCD);
		main_fm.add(LB_CLGpName01);
		main_fm.add(LB_DECD);
		main_fm.add(LB_DepartmentCd);
		main_fm.add(LB_DEName01);
		main_fm.add(LB_Add01);
		main_fm.add(LB_Add02);
		main_fm.add(LB_Add03);
		main_fm.add(LB_CautionTiming);
		main_fm.add(LB_CautionName);
		main_fm.add(LB_Caution);
		main_fm.add(LB_EntryDate);
		main_fm.add(LB_UpdateDate);
		main_fm.add(LB_EntryUser);
		main_fm.add(LB_UpdateUser);
		
		main_fm.add(TB_CautionCd);
		main_fm.add(TB_ClGpCD);
		main_fm.add(TB_CLGpName01);
		main_fm.add(TB_DECD);
		main_fm.add(TB_DepartmentCd);
		main_fm.add(TB_DEName01);
		main_fm.add(TB_Add01);
		main_fm.add(TB_Add02);
		main_fm.add(TB_Add03);
		main_fm.add(TB_CautionTiming);
		main_fm.add(TB_CautionName);
		main_fm.add(TB_Caution);
		main_fm.add(TB_EntryDate);
		main_fm.add(TB_UpdateDate);
		main_fm.add(TB_EntryUser);
		main_fm.add(TB_UpdateUser);
		
		//荷主Grp検索ボタン
		JButton ClGrpSearchBtn = B00110FrameParts.BtnSet(210, 65,100,20,"荷主Grp検索",11);
		main_fm.add(ClGrpSearchBtn);
		
		//届先検索ボタン
		JButton DeliverySearchBtn = B00110FrameParts.BtnSet(210,115,100,20,"届先検索",11);
		main_fm.add(DeliverySearchBtn);
		
		/*----------------------------------------------------------
		荷主Grp検索
		----------------------------------------------------------*/
		final JFrame  ClGrpSearch_fm = B00110FrameParts.FrameCreate(x+20,y+20,500,500,"Corgi00届先注意事項マスタ登録・更新(荷主Grp検索)","");
		JButton  ClGrpSearchexit_btn = B00110FrameParts.ExitBtn();
		
		ClGrpSearch_fm.add(ClGrpSearchexit_btn);
		
		//編集可能カラムの指定
		B10010TableControl.RenewTgt = new int[1];
		B10010TableControl.RenewTgt[0] = 0;
		
		String[] ClGrpSearch_columnNames01 = {
								"Fg"
								,"荷主GrpCD"
								,"荷主Grp名"
								,"住所1"
								,"住所2"
								,"住所3"
								,"TEL"
								};
		final DefaultTableModel tableModel_ClGrp = new B10010TableControl.MyTableModel01(ClGrpSearch_columnNames01,0);
		
		final JTable tb_ClGrp = new JTable(tableModel_ClGrp);
		tb_ClGrp.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tb_ClGrp.setRowHeight(20*A00000Main.Mul/A00000Main.Div);
		tb_ClGrp.setFont(new Font(A00000Main.DefaultFont, Font.PLAIN, 12*A00000Main.Mul/A00000Main.Div));
		
		DefaultTableColumnModel columnModel01
		= (DefaultTableColumnModel)tb_ClGrp.getColumnModel();
		
		//列幅初期設定 表示位置設定
		TableColumn column = null;
		
		column = columnModel01.getColumn( 0);	column.setPreferredWidth( 30*A00000Main.Mul/A00000Main.Div);	//FG
		
		for(int i=1;i<ClGrpSearch_columnNames01.length;i++) {
			column = columnModel01.getColumn(1);	column.setPreferredWidth( 90*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());
		}
		
		//スクロール用設定
		JScrollPane scpn_ClGrp = B00110FrameParts.JScrollPaneSet(10,125,460,250,tb_ClGrp);
		ClGrpSearch_fm.add(scpn_ClGrp);
		
		JLabel LB_SearchGrpName	= B00110FrameParts.JLabelSet(0, 25,100,20,"グループ名:"	,11,1);
		JLabel LB_SearchGrpAdd	= B00110FrameParts.JLabelSet(0, 50,100,20,"住所:"			,11,1);
		JLabel LB_SearchGrpTel	= B00110FrameParts.JLabelSet(0, 75,100,20,"TEL:"			,11,1);
		
		final JTextField  TB_SearchGrpName	= B00110FrameParts.JTextFieldSet(100, 25,100,20,"",11,0);			//荷主グループ名
		final JTextField  TB_SearchGrpAdd	= B00110FrameParts.JTextFieldSet(100, 50,100,20,"",11,0);			//住所
		final JTextField  TB_SearchGrpTel	= B00110FrameParts.JTextFieldSet(100, 75,100,20,"",11,0);			//TEL
		
		JLabel LB2_SearchGrpName		= B00110FrameParts.JLabelSet(200, 25,100,20,"を含む",11,0);
		JLabel LB2_SearchGrpAdd			= B00110FrameParts.JLabelSet(200, 50,100,20,"を含む",11,0);
		JLabel LB2_SearchGrpTel			= B00110FrameParts.JLabelSet(200, 75,100,20,"を含む",11,0);
		
		ClGrpSearch_fm.add(LB_SearchGrpName);
		ClGrpSearch_fm.add(LB_SearchGrpAdd);
		ClGrpSearch_fm.add(LB_SearchGrpTel);
		
		ClGrpSearch_fm.add(TB_SearchGrpName);
		ClGrpSearch_fm.add(TB_SearchGrpAdd);
		ClGrpSearch_fm.add(TB_SearchGrpTel);
		
		ClGrpSearch_fm.add(LB2_SearchGrpName);
		ClGrpSearch_fm.add(LB2_SearchGrpAdd);
		ClGrpSearch_fm.add(LB2_SearchGrpTel);
		
		JButton ClGrpSearch_Btn = B00110FrameParts.BtnSet(100,100,100,20,"検索",11);
		ClGrpSearch_fm.add(ClGrpSearch_Btn);
		
		JButton ClGrpEntry_Btn = B00110FrameParts.EntryBtn();
		ClGrpSearch_fm.add(ClGrpEntry_Btn);
		
		ClGrpSearchexit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				ClGrpSearch_fm.setVisible(false);
			}
		});
		
		ClGrpEntry_Btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					int row_count = tableModel_ClGrp.getRowCount();
					for(int i=0;i<row_count;i++){
						if((boolean)tableModel_ClGrp.getValueAt(i, 0)) {
							TB_ClGpCD.setText(		""+tableModel_ClGrp.getValueAt(i, 1));
							TB_CLGpName01.setText(	""+tableModel_ClGrp.getValueAt(i, 2));
							ClGrpSearch_fm.setVisible(false);
						}
					}
					RenewFg = true;
				}
			}
		});
		
		ClGrpSearch_Btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					int row_count = tableModel_ClGrp.getRowCount();
					for(int i=0;i<row_count;i++){
						tableModel_ClGrp.removeRow(0);
					}

					String GetSearchGrpName	=TB_SearchGrpName.getText();
					String GetSearchGrpAdd	=TB_SearchGrpAdd.getText();
					String GetSearchGrpTel	=TB_SearchGrpTel.getText();
					
					if(null==GetSearchGrpName	){GetSearchGrpName	= "";}
					if(null==GetSearchGrpAdd	){GetSearchGrpAdd	= "";}
					if(null==GetSearchGrpTel	){GetSearchGrpTel	= "";}
					
					GetSearchGrpName	= B00020ToolsTextControl.Trim(GetSearchGrpName);
					GetSearchGrpAdd		= B00020ToolsTextControl.Trim(GetSearchGrpAdd);
					GetSearchGrpTel		= B00020ToolsTextControl.Trim(GetSearchGrpTel);
					
					GetSearchGrpTel		= B00020ToolsTextControl.num_only_String(GetSearchGrpTel);
					
					ArrayList<String> SearchClGpCD 		= new ArrayList<String>();
					ArrayList<String> SearchCLGpName 	= new ArrayList<String>();
					ArrayList<String> SearchPost 		= new ArrayList<String>();
					ArrayList<String> SearchAdd 		= new ArrayList<String>();
					ArrayList<String> SearchTel 		= new ArrayList<String>();
					ArrayList<String> SearchFax 		= new ArrayList<String>();
					ArrayList<String> SearchMail 		= new ArrayList<String>();
					ArrayList<String> SearchCom 		= new ArrayList<String>();
					boolean AllSearch = true;
					
					if(!"".equals(GetSearchGrpName	)) {SearchCLGpName.add(GetSearchGrpName);}
					if(!"".equals(GetSearchGrpAdd	)) {SearchAdd.add(GetSearchGrpAdd);}
					if(!"".equals(GetSearchGrpTel	)) {SearchTel.add(GetSearchGrpTel);}
					
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
					
					for(int i=0;i<ClGpMstRt.length;i++) {
						Object[] SetOb = new Object[7];
						SetOb[ 0] = false;
						SetOb[ 1] = ""+ClGpMstRt[i][ 0];	//荷主GrpCD
						SetOb[ 2] = ""+ClGpMstRt[i][ 1];	//荷主Grp名
						SetOb[ 3] = ""+ClGpMstRt[i][ 5];	//住所1
						SetOb[ 4] = ""+ClGpMstRt[i][ 6];	//住所2
						SetOb[ 5] = ""+ClGpMstRt[i][ 7];	//住所3
						SetOb[ 6] = ""+ClGpMstRt[i][ 8];	//TEL
						
						tableModel_ClGrp.addRow(SetOb);
					}
					
					RenewFg = true;
				}
			}
		});
		
		//チェックボックス操作時の挙動
		tableModel_ClGrp.addTableModelListener(new TableModelListener(){
			public void tableChanged(TableModelEvent e){
				if(RenewFg) {
					RenewFg = false;
					int row_count = tb_ClGrp.getRowCount();
					Boolean setBL=Boolean.valueOf(false);
					for(int i=0;i<row_count;i++){
						if(i!=e.getFirstRow()){
							tableModel_ClGrp.setValueAt(setBL, i, 0);
						}else {
	
						}
					}
					RenewFg = true;
				}
			}
		});

		/*----------------------------------------------------------
		届先検索
		----------------------------------------------------------*/
		
		final JFrame DeliverySearch_fm = B00110FrameParts.FrameCreate(x+20,y+20,500,500,"Corgi00届先注意事項マスタ登録・更新(届先検索)","");
		JButton  DeliverySearchexit_btn = B00110FrameParts.ExitBtn();
		
		DeliverySearch_fm.add(DeliverySearchexit_btn);
		
		DeliverySearchexit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				DeliverySearch_fm.setVisible(false);
			}
		});
		
		//編集可能カラムの指定
		B10010TableControl.RenewTgt = new int[1];
		B10010TableControl.RenewTgt[0] = 0;
		
		String[] DeliverySearch_columnNames01 = {
								"Fg"
								,"届先CD"
								,"届先部署CD"
								,"届先名"
								,"住所1"
								,"住所2"
								,"住所3"
								,"TEL"
								};
		final DefaultTableModel tableModel_delivery = new B10010TableControl.MyTableModel01(DeliverySearch_columnNames01,0);
		
		final JTable tb_Delivery = new JTable(tableModel_delivery);
		tb_Delivery.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tb_Delivery.setRowHeight(20*A00000Main.Mul/A00000Main.Div);
		tb_Delivery.setFont(new Font(A00000Main.DefaultFont, Font.PLAIN, 12*A00000Main.Mul/A00000Main.Div));
		
		DefaultTableColumnModel columnModelDelivery
		= (DefaultTableColumnModel)tb_Delivery.getColumnModel();
		
		//列幅初期設定 表示位置設定
		column = null;
		
		column = columnModelDelivery.getColumn( 0);	column.setPreferredWidth( 30*A00000Main.Mul/A00000Main.Div);	//FG
		
		for(int i=1;i<DeliverySearch_columnNames01.length;i++) {
			column = columnModelDelivery.getColumn(1);	column.setPreferredWidth( 90*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());
		}
		
		//スクロール用設定
		JScrollPane scpn_Delivery = B00110FrameParts.JScrollPaneSet(10,125,460,250,tb_Delivery);
		DeliverySearch_fm.add(scpn_Delivery);
		
		JLabel LB_SearchDeliveryName	= B00110FrameParts.JLabelSet(0, 25,100,20,"届先名:"	,11,1);
		JLabel LB_SearchDeliveryAdd		= B00110FrameParts.JLabelSet(0, 50,100,20,"住所:"		,11,1);
		JLabel LB_SearchDeliveryTel		= B00110FrameParts.JLabelSet(0, 75,100,20,"TEL:"		,11,1);
		
		final JTextField  TB_SearchDeliveryName	= B00110FrameParts.JTextFieldSet(100, 25,100,20,"",11,0);			//届先名
		final JTextField  TB_SearchDeliveryAdd	= B00110FrameParts.JTextFieldSet(100, 50,100,20,"",11,0);			//住所
		final JTextField  TB_SearchDeliveryTel	= B00110FrameParts.JTextFieldSet(100, 75,100,20,"",11,0);			//TEL
		
		JLabel LB2_SearchDeliveryName	= B00110FrameParts.JLabelSet(200, 25,100,20,"を含む",11,0);
		JLabel LB2_SearchDeliveryAdd	= B00110FrameParts.JLabelSet(200, 50,100,20,"を含む",11,0);
		JLabel LB2_SearchDeliveryTel	= B00110FrameParts.JLabelSet(200, 75,100,20,"を含む",11,0);
		
		DeliverySearch_fm.add(LB_SearchDeliveryName);
		DeliverySearch_fm.add(LB_SearchDeliveryAdd);
		DeliverySearch_fm.add(LB_SearchDeliveryTel);
		
		DeliverySearch_fm.add(TB_SearchDeliveryName);
		DeliverySearch_fm.add(TB_SearchDeliveryAdd);
		DeliverySearch_fm.add(TB_SearchDeliveryTel);
		
		DeliverySearch_fm.add(LB2_SearchDeliveryName);
		DeliverySearch_fm.add(LB2_SearchDeliveryAdd);
		DeliverySearch_fm.add(LB2_SearchDeliveryTel);
		
		JButton DeliverySearch_Btn = B00110FrameParts.BtnSet(100,100,100,20,"検索",11);
		DeliverySearch_fm.add(DeliverySearch_Btn);
		
		JButton DeliveryEntry_Btn = B00110FrameParts.EntryBtn();
		DeliverySearch_fm.add(DeliveryEntry_Btn);
		
		DeliveryEntry_Btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					int row_count = tableModel_delivery.getRowCount();
					for(int i=0;i<row_count;i++){
						if((boolean)tableModel_delivery.getValueAt(i, 0)) {
							TB_DECD.setText(		""+tableModel_delivery.getValueAt(i, 1));
							TB_DepartmentCd.setText(""+tableModel_delivery.getValueAt(i, 2));
							TB_DEName01.setText(	""+tableModel_delivery.getValueAt(i, 3));
							TB_Add01.setText(		""+tableModel_delivery.getValueAt(i, 4));
							TB_Add02.setText(		""+tableModel_delivery.getValueAt(i, 5));
							TB_Add03.setText(		""+tableModel_delivery.getValueAt(i, 6));
							
							DeliverySearch_fm.setVisible(false);
						}
					}
					RenewFg = true;
				}
			}
		});
		
		DeliverySearch_Btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					int row_count = tableModel_delivery.getRowCount();
					for(int i=0;i<row_count;i++){
						tableModel_delivery.removeRow(0);
					}
					
					String GetSearchDeliveryName	= TB_SearchDeliveryName.getText();
					String GetSearchDeliveryAdd		= TB_SearchDeliveryAdd.getText();
					String GetSearchDeliveryTel		= TB_SearchDeliveryTel.getText();
					
					if(null==GetSearchDeliveryName	){GetSearchDeliveryName	= "";}
					if(null==GetSearchDeliveryAdd	){GetSearchDeliveryAdd	= "";}
					if(null==GetSearchDeliveryTel	){GetSearchDeliveryTel	= "";}
					
					GetSearchDeliveryName	= B00020ToolsTextControl.Trim(GetSearchDeliveryName);
					GetSearchDeliveryAdd	= B00020ToolsTextControl.Trim(GetSearchDeliveryAdd);
					GetSearchDeliveryTel	= B00020ToolsTextControl.Trim(GetSearchDeliveryTel);
					
					GetSearchDeliveryTel	= B00020ToolsTextControl.num_only_String(GetSearchDeliveryTel);
					
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
					boolean AllSearch = true;
					
					if(!"".equals(GetSearchDeliveryName	)){SearchDEName.add(GetSearchDeliveryName);}
					if(!"".equals(GetSearchDeliveryAdd	)){SearchAdd.add(	GetSearchDeliveryAdd);}
					if(!"".equals(GetSearchDeliveryTel	)){SearchTel.add(	GetSearchDeliveryTel);}
					
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
						AllSearch
						);
					
					for(int i=0;i<DeliveryMstRt.length;i++) {
						/*
						部署コードJISは注意事項対象外
						*/
						if(!"JIS".equals(""+DeliveryMstRt[i][ 1])) {
							Object[] SetOb = new Object[8];
							SetOb[ 0] = false;
							SetOb[ 1] = ""+DeliveryMstRt[i][ 0];	//届先CD
							SetOb[ 2] = ""+DeliveryMstRt[i][ 1];	//届先部署CD
							SetOb[ 3] = ""+DeliveryMstRt[i][ 2];	//届先名
							SetOb[ 4] = ""+DeliveryMstRt[i][ 6];	//住所1
							SetOb[ 5] = ""+DeliveryMstRt[i][ 7];	//住所2
							SetOb[ 6] = ""+DeliveryMstRt[i][ 8];	//住所3
							SetOb[ 7] = ""+DeliveryMstRt[i][ 9];	//TEL
							
							tableModel_delivery.addRow(SetOb);
						}
					}
					
					RenewFg = true;
				}
			}
		});
		
		//チェックボックス操作時の挙動
		tableModel_delivery.addTableModelListener(new TableModelListener(){
			public void tableChanged(TableModelEvent e){
				if(RenewFg) {
					RenewFg = false;
					int row_count = tableModel_delivery.getRowCount();
					Boolean setBL=Boolean.valueOf(false);
					for(int i=0;i<row_count;i++){
						if(i!=e.getFirstRow()){
							tableModel_delivery.setValueAt(setBL, i, 0);
						}else {
	
						}
					}
					RenewFg = true;
				}
			}
		});
		
		
		main_fm.setVisible(true);
		RenewFg = true;
		
		//届先検索ボタン
		DeliverySearchBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					DeliverySearch_fm.setVisible(false);
					ClGrpSearch_fm.setVisible(false);
					
					TB_SearchDeliveryName.setText("");
					TB_SearchDeliveryAdd.setText("");
					TB_SearchDeliveryTel.setText("");
					
					int RowCount = tableModel_delivery.getRowCount();
					for(int i=0;i<RowCount;i++) {
						tableModel_delivery.removeRow(0);
					}
					DeliverySearch_fm.setVisible(true);
					RenewFg = true;
				}
			}
		});
		
		//荷主Grp検索ボタン押下時の挙動
		ClGrpSearchBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					DeliverySearch_fm.setVisible(false);
					ClGrpSearch_fm.setVisible(false);
					
					TB_SearchGrpName.setText("");
					TB_SearchGrpAdd.setText("");
					TB_SearchGrpTel.setText("");
					
					int RowCount = tableModel_ClGrp.getRowCount();
					for(int i=0;i<RowCount;i++) {
						tableModel_ClGrp.removeRow(0);
					}
					ClGrpSearch_fm.setVisible(true);
					RenewFg = true;
				}
			}
		});
		
		//荷主グループコードフォーカス消失時の挙動
		TB_ClGpCD.addFocusListener(new FocusAdapter(){
			@Override
			public void focusLost(FocusEvent e){
				if(RenewFg) {
					RenewFg = false;
					TB_CLGpName01.setText("");
					String GetClGpCD = TB_ClGpCD.getText();
					if(null==GetClGpCD) {GetClGpCD="";}
					GetClGpCD = B00020ToolsTextControl.Trim(GetClGpCD);
					if(!"".equals(GetClGpCD)) {
						ArrayList<String> SearchClGpCD 		= new ArrayList<String>();
						ArrayList<String> SearchCLGpName 	= new ArrayList<String>();
						ArrayList<String> SearchPost 		= new ArrayList<String>();
						ArrayList<String> SearchAdd 		= new ArrayList<String>();
						ArrayList<String> SearchTel 		= new ArrayList<String>();
						ArrayList<String> SearchFax 		= new ArrayList<String>();
						ArrayList<String> SearchMail 		= new ArrayList<String>();
						ArrayList<String> SearchCom 		= new ArrayList<String>();
						boolean AllSearch = false;
						SearchClGpCD.add(GetClGpCD);
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
						if(0<ClGpMstRt.length) {
							TB_CLGpName01.setText(""+ClGpMstRt[0][1]);
						}else {
							JOptionPane.showMessageDialog(null, "存在しない荷主グループコードです");
							TB_ClGpCD.setText("");
						}
					}
					RenewFg = true;
				}
			}
		});
		
		//届先コードフォーカス消失時の挙動
		TB_DECD.addFocusListener(new FocusAdapter(){
			@Override
			public void focusLost(FocusEvent e){
				if(RenewFg) {
					RenewFg = false;
					TB_DEName01.setText("");
					TB_Add01.setText("");
					TB_Add02.setText("");
					TB_Add03.setText("");
					
					String GetDECD 			= TB_DECD.getText();
					String GetDepartmentCd 	= TB_DepartmentCd.getText();
					
					if(null==GetDECD		) {GetDECD= "";}
					if(null==GetDepartmentCd) {GetDepartmentCd="";}
					
					GetDECD			= B00020ToolsTextControl.Trim(GetDECD);
					GetDepartmentCd	= B00020ToolsTextControl.Trim(GetDepartmentCd);
					
					if(!"".equals(GetDECD)) {
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
						boolean AllSearch = false;
						
						SearchDECD.add(GetDECD);
						
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
							AllSearch
							);
						
						if(0<DeliveryMstRt.length) {
							if("".equals(GetDepartmentCd)) {
								TB_DEName01.setText(""+DeliveryMstRt[0][2]);
								TB_Add01.setText(""+DeliveryMstRt[0][6]);
								TB_Add02.setText(""+DeliveryMstRt[0][7]);
								TB_Add03.setText(""+DeliveryMstRt[0][8]);
							}else {
								boolean UnHitFg=true;
								for(int i=0;i<DeliveryMstRt.length;i++) {
									if(GetDepartmentCd.equals(""+DeliveryMstRt[i][1])) {
										TB_DEName01.setText(""+DeliveryMstRt[i][2]);
										TB_Add01.setText(""+DeliveryMstRt[i][6]);
										TB_Add02.setText(""+DeliveryMstRt[i][7]);
										TB_Add03.setText(""+DeliveryMstRt[i][8]);
										UnHitFg=false;
									}
								}
								if(UnHitFg) {
									JOptionPane.showMessageDialog(null, "存在しない届先部署です部署設定外します");
									TB_DepartmentCd.setText("");
									TB_DEName01.setText(""+DeliveryMstRt[0][2]);
									TB_Add01.setText(""+DeliveryMstRt[0][6]);
									TB_Add02.setText(""+DeliveryMstRt[0][7]);
									TB_Add03.setText(""+DeliveryMstRt[0][8]);
								}
							}
						}else {
							JOptionPane.showMessageDialog(null, "存在しない届先です");
							TB_DECD.setText(		"");
							TB_DepartmentCd.setText("");
						}
					}else {
						TB_DECD.setText(		"");
						TB_DepartmentCd.setText("");
					}
					RenewFg = true;
				}
			}
		});
		
		//部署コードフォーカス消失時の挙動
		TB_DepartmentCd.addFocusListener(new FocusAdapter(){
			@Override
			public void focusLost(FocusEvent e){
				if(RenewFg) {
					RenewFg = false;
					TB_DEName01.setText("");
					TB_Add01.setText("");
					TB_Add02.setText("");
					TB_Add03.setText("");
					
					String GetDECD 			= TB_DECD.getText();
					String GetDepartmentCd 	= TB_DepartmentCd.getText();
					
					if(null==GetDECD		) {GetDECD= "";}
					if(null==GetDepartmentCd) {GetDepartmentCd="";}
					
					GetDECD			= B00020ToolsTextControl.Trim(GetDECD);
					GetDepartmentCd	= B00020ToolsTextControl.Trim(GetDepartmentCd);
					
					if(!"".equals(GetDECD)) {
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
						boolean AllSearch = false;
						
						SearchDECD.add(GetDECD);
						
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
							AllSearch
							);
						
						if(0<DeliveryMstRt.length) {
							if("".equals(GetDepartmentCd)) {
								TB_DEName01.setText(""+DeliveryMstRt[0][2]);
								TB_Add01.setText(""+DeliveryMstRt[0][6]);
								TB_Add02.setText(""+DeliveryMstRt[0][7]);
								TB_Add03.setText(""+DeliveryMstRt[0][8]);
							}else {
								boolean UnHitFg=true;
								for(int i=0;i<DeliveryMstRt.length;i++) {
									if(GetDepartmentCd.equals(""+DeliveryMstRt[i][1])) {
										TB_DEName01.setText(""+DeliveryMstRt[i][2]);
										TB_Add01.setText(""+DeliveryMstRt[i][6]);
										TB_Add02.setText(""+DeliveryMstRt[i][7]);
										TB_Add03.setText(""+DeliveryMstRt[i][8]);
										UnHitFg=false;
									}
								}
								if(UnHitFg) {
									JOptionPane.showMessageDialog(null, "存在しない届先部署です部署設定外します");
									TB_DepartmentCd.setText("");
									TB_DEName01.setText(""+DeliveryMstRt[0][2]);
									TB_Add01.setText(""+DeliveryMstRt[0][6]);
									TB_Add02.setText(""+DeliveryMstRt[0][7]);
									TB_Add03.setText(""+DeliveryMstRt[0][8]);
								}
							}
						}else {
							JOptionPane.showMessageDialog(null, "存在しない届先です");
							TB_DECD.setText(		"");
							TB_DepartmentCd.setText("");
						}
					}else {
						if(!"".equals(GetDepartmentCd)) {
							JOptionPane.showMessageDialog(null, "存在しない届先部署です部署設定外します");
							TB_DepartmentCd.setText("");
						}
					}
					RenewFg = true;
				}
			}
		});
		
		//登録ボタン押下時の挙動
		entry_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				String now_dtm = B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[1];
				
				String GetCautionCd		= TB_CautionCd.getText();		//注意事項コード
				String GetClGpCD		= TB_ClGpCD.getText();			//荷主グループコード
				String GetDECD			= TB_DECD.getText();			//届先コード
				String GetDepartmentCd	= TB_DepartmentCd.getText();	//部署CD
				String GetCautionTiming	= B00100DefaultVariable.CautionTiming[1][TB_CautionTiming.getSelectedIndex()];	//注意事項タイミング
				String GetCautionName	= TB_CautionName.getText();		//注意事項名
				String GetCaution		= TB_Caution.getText();			//注意事項内容
				
				if(null==GetCautionCd		){GetCautionCd		= "";}
				if(null==GetClGpCD			){GetClGpCD			= "";}
				if(null==GetDECD			){GetDECD			= "";}
				if(null==GetDepartmentCd	){GetDepartmentCd	= "";}
				if(null==GetCautionTiming	){GetCautionTiming	= "";}
				if(null==GetCautionName		){GetCautionName	= "";}
				if(null==GetCaution			){GetCaution		= "";}
				
				GetCautionCd		= B00020ToolsTextControl.Trim(GetCautionCd);
				GetClGpCD			= B00020ToolsTextControl.Trim(GetClGpCD);
				GetDECD				= B00020ToolsTextControl.Trim(GetDECD);
				GetDepartmentCd		= B00020ToolsTextControl.Trim(GetDepartmentCd);
				GetCautionTiming	= B00020ToolsTextControl.Trim(GetCautionTiming);
				GetCautionName		= B00020ToolsTextControl.Trim(GetCautionName);
				GetCaution			= B00020ToolsTextControl.Trim(GetCaution);
				
				if(!"".equals(GetDECD)) {
					if("".equals(GetCautionCd)) {GetCautionCd = M00042CautionMstRt.NewCautionCdGet(1)[0];}
					
					String[][] SetString = {
							 {"CautionCd"		,"1","1",GetCautionCd}			//注意事項コード
							,{"ClGpCD"			,"1","1",GetClGpCD}				//荷主グループコード
							,{"DECD"			,"1","1",GetDECD}				//届先コード
							,{"DepartmentCd"	,"1","1",GetDepartmentCd}		//部署CD
							,{"CautionTiming"	,"1","1",GetCautionTiming}		//注意事項タイミング
							,{"CautionName"		,"1","1",GetCautionName}		//注意事項名
							,{"Caution"			,"1","1",GetCaution}			//注意事項内容
							,{"EntryDate"		,"1","0",now_dtm}				//データ登録日時
							,{"UpdateDate"		,"1","1",now_dtm}				//データ更新日時
							,{"EntryUser"		,"1","0","(" + A00000Main.LoginUserId + ")" + A00000Main.LoginUserName}				//登録者コード
							,{"UpdateUser"		,"1","1","(" + A00000Main.LoginUserId + ")" + A00000Main.LoginUserName}				//更新者コード
					};
					
					String tgt_table = "KM0090_CAUTION";
					String[][] field_name = new String[SetString.length][3];
					String[][] entry_data = new String[1][SetString.length];
					String[] judg_field = new String[3];
					String[][] judg_data = new String[1][3];
					String TgtDB = "NYANKO";
					int non_msg_fg = 1;
	
					judg_field[0] = "CautionCd";	//注意事項コード
					judg_field[1] = "DECD";			//運送タイプコード
					judg_field[2] = "DepartmentCd";	//部署CD
	
					judg_data[0][0] = GetCautionCd;		//注意事項コード
					judg_data[0][1] = GetDECD;			//届先コード
					judg_data[0][2] = GetDepartmentCd;	//部署CD
					
					for(int i=0;i<SetString.length;i++) {
						field_name[i][0] = SetString[i][0];
						field_name[i][1] = SetString[i][1];
						field_name[i][2] = SetString[i][2];
						entry_data[0][i] = SetString[i][3];
					}
					
					A00020InsertUdateSQL.RUN_SQLS_EU(tgt_table, field_name, entry_data, judg_field, judg_data, non_msg_fg,TgtDB);
					
					SetX=main_fm.getX();
					SetY=main_fm.getY();
					
					ClGrpSearch_fm.setVisible(false);
					ClGrpSearch_fm.dispose();
					
					DeliverySearch_fm.setVisible(false);
					DeliverySearch_fm.dispose();
					
					main_fm.setVisible(false);
					main_fm.dispose();
					
					CautionMstRenewAndCreate(0,0,GetCautionCd,GetDECD,GetDepartmentCd);
				}else {
					JOptionPane.showMessageDialog(null, "届先の指定は必須です");
				}
			}
		});
		
		
		//EXITボタン押下時の挙動
		exit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();
				
				ClGrpSearch_fm.setVisible(false);
				ClGrpSearch_fm.dispose();
				
				DeliverySearch_fm.setVisible(false);
				DeliverySearch_fm.dispose();
				
				main_fm.setVisible(false);
				main_fm.dispose();
				WM00070CautionMstSearch.CautionMstSearch(0, 0);
			}
		});
	}
}