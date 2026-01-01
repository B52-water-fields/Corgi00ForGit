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

public class WM00061DeliveryMstRenewAndCreate{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	public static void DeliveryMstRenewAndCreate(int x,int y,String DECD,String DepartmentCd) {
		A00000Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		
		if(null==DECD) {DECD = "";}
		if(null==DepartmentCd) {DepartmentCd = "";}
		
		final JFrame main_fm = B00110FrameParts.FrameCreate(x,y,500,750,"Corgi00届先マスタ登録・更新","");
		JLabel userinfo = B00110FrameParts.UserInfo();
		JButton exit_btn = B00110FrameParts.ExitBtn();
		JButton entry_btn = B00110FrameParts.EntryBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		if(!"JIS".equals(DepartmentCd)) {
			main_fm.add(entry_btn);
		}
		
		JLabel LB_DECD				= B00110FrameParts.JLabelSet(  0, 40,100,20,"納品先コード:",	11,1);
		JLabel LB_DepartmentCd		= B00110FrameParts.JLabelSet(  0, 65,100,20,"部署CD:",			11,1);
		JLabel LB_DEName01			= B00110FrameParts.JLabelSet(  0, 90,100,20,"納品先名1:",		11,1);
		JLabel LB_DEName02			= B00110FrameParts.JLabelSet(  0,115,100,20,"納品先名2:",		11,1);
		JLabel LB_DEName03			= B00110FrameParts.JLabelSet(  0,140,100,20,"納品先名3:",		11,1);
		JLabel LB_Post				= B00110FrameParts.JLabelSet(  0,165,100,20,"納品先郵便:",		11,1);
		JLabel LB_Add01				= B00110FrameParts.JLabelSet(  0,190,100,20,"納品先住所1:",	11,1);
		JLabel LB_Add02				= B00110FrameParts.JLabelSet(  0,215,100,20,"納品先住所2:",	11,1);
		JLabel LB_Add03				= B00110FrameParts.JLabelSet(  0,240,100,20,"納品先住所3:",	11,1);
		JLabel LB_Tel				= B00110FrameParts.JLabelSet(  0,265,100,20,"納品先電話:",		11,1);
		JLabel LB_Fax				= B00110FrameParts.JLabelSet(  0,290,100,20,"納品先FAX:",		11,1);
		JLabel LB_Mail				= B00110FrameParts.JLabelSet(  0,315,100,20,"納品先MAIL:",		11,1);
		JLabel LB_Com01				= B00110FrameParts.JLabelSet(  0,340,100,20,"コメント1:",		11,1);
		JLabel LB_Com02				= B00110FrameParts.JLabelSet(  0,365,100,20,"コメント2:",		11,1);
		JLabel LB_Com03				= B00110FrameParts.JLabelSet(  0,390,100,20,"コメント3:",		11,1);
		JLabel LB_PrefecturesCd		= B00110FrameParts.JLabelSet(  0,415,100,20,"JIS県CD2桁:",		11,1);
		JLabel LB_MunicipalityCd	= B00110FrameParts.JLabelSet(  0,440,100,20,"JISCD5桁:",		11,1);
		JLabel LB_PTMSCD			= B00110FrameParts.JLabelSet(  0,465,100,20,"基幹SYS発着CD:",	11,1);
		JLabel LB_EntryDate			= B00110FrameParts.JLabelSet(  0,490,100,20,"データ登録日時:",	11,1);
		JLabel LB_UpdateDate		= B00110FrameParts.JLabelSet(  0,515,100,20,"データ更新日時:",	11,1);
		JLabel LB_EntryUser			= B00110FrameParts.JLabelSet(  0,540,100,20,"登録者コード:",	11,1);
		JLabel LB_UpdateUser		= B00110FrameParts.JLabelSet(  0,565,100,20,"更新者コード:",	11,1);
		JLabel LB_FirstClient		= B00110FrameParts.JLabelSet(  0,590,100,20,"登録した荷主:",	11,1);
		JLabel LB_LastClient		= B00110FrameParts.JLabelSet(  0,615,100,20,"更新した荷主:",	11,1);
		JLabel LB_DelFg				= B00110FrameParts.JLabelSet(  0,640,100,20,"削除区分:",		11,1);
		JLabel LB_MSG				= B00110FrameParts.JLabelSet(  0,665,300,20,"JIS納品先は郵便番号マスタから生成してください",	11,1);

		final JTextField TB_DECD			= B00110FrameParts.JTextFieldSet(100, 40,100,20,"",11,0);			//納品先コード
		final JTextField TB_DepartmentCd	= B00110FrameParts.JTextFieldSet(100, 65,100,20,"",11,0);			//部署CD
		final JTextField TB_DEName01		= B00110FrameParts.JTextFieldSet(100, 90,250,20,"",11,0);			//納品先名1
		final JTextField TB_DEName02		= B00110FrameParts.JTextFieldSet(100,115,250,20,"",11,0);			//納品先名2
		final JTextField TB_DEName03		= B00110FrameParts.JTextFieldSet(100,140,250,20,"",11,0);			//納品先名3
		final JTextField TB_Post			= B00110FrameParts.JTextFieldSet(100,165,100,20,"",11,0);			//納品先郵便
		final JTextField TB_Add01			= B00110FrameParts.JTextFieldSet(100,190,250,20,"",11,0);			//納品先住所1
		final JTextField TB_Add02			= B00110FrameParts.JTextFieldSet(100,215,250,20,"",11,0);			//納品先住所2
		final JTextField TB_Add03			= B00110FrameParts.JTextFieldSet(100,240,250,20,"",11,0);			//納品先住所3
		final JTextField TB_Tel				= B00110FrameParts.JTextFieldSet(100,265,100,20,"",11,0);			//納品先電話
		final JTextField TB_Fax				= B00110FrameParts.JTextFieldSet(100,290,100,20,"",11,0);			//納品先FAX
		final JTextField TB_Mail			= B00110FrameParts.JTextFieldSet(100,315,100,20,"",11,0);			//納品先MAIL
		final JTextField TB_Com01			= B00110FrameParts.JTextFieldSet(100,340,250,20,"",11,0);			//コメント1
		final JTextField TB_Com02			= B00110FrameParts.JTextFieldSet(100,365,250,20,"",11,0);			//コメント2
		final JTextField TB_Com03			= B00110FrameParts.JTextFieldSet(100,390,250,20,"",11,0);			//コメント3
		final JTextField TB_PrefecturesCd	= B00110FrameParts.JTextFieldSet(100,415,100,20,"",11,0);			//JIS県CD2桁
		final JTextField TB_MunicipalityCd	= B00110FrameParts.JTextFieldSet(100,440,100,20,"",11,0);			//JISCD5桁
		final JTextField TB_PTMSCD			= B00110FrameParts.JTextFieldSet(100,465,100,20,"",11,0);			//基幹SYS発着CD
		final JTextField TB_EntryDate		= B00110FrameParts.JTextFieldSet(100,490,250,20,"",11,0);			//データ登録日時
		final JTextField TB_UpdateDate		= B00110FrameParts.JTextFieldSet(100,515,250,20,"",11,0);			//データ更新日時
		final JTextField TB_EntryUser		= B00110FrameParts.JTextFieldSet(100,540,250,20,"",11,0);			//登録者コード
		final JTextField TB_UpdateUser		= B00110FrameParts.JTextFieldSet(100,565,250,20,"",11,0);			//更新者コード
		final JTextField TB_FirstClient		= B00110FrameParts.JTextFieldSet(100,590,250,20,"",11,0);			//登録した荷主
		final JTextField TB_LastClient		= B00110FrameParts.JTextFieldSet(100,615,250,20,"",11,0);			//更新した荷主
		final JComboBox  TB_DelFg			= B00110FrameParts.JComboBoxSet( 100,640,100,20,B00100DefaultVariable.DelList[0],11);	//削除区分
		
		//部署追加ボタン
		JButton AddDepartmentCdBtn = B00110FrameParts.BtnSet(210,65,100,20,"部署追加",11);
		
		if(!"".equals(DECD) && !"".equals(DepartmentCd)) {
			
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
			boolean SearcNotJis = false;
			boolean AllSearch = false;
			
			SearchDECD.add(DECD);
			SearchDepartmentCd.add(DepartmentCd);
			
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
				TB_DECD.setText(			""+DeliveryMstRt[0][ 0]);
				TB_DepartmentCd.setText(	""+DeliveryMstRt[0][ 1]);
				TB_DEName01.setText(		""+DeliveryMstRt[0][ 2]);
				TB_DEName02.setText(		""+DeliveryMstRt[0][ 3]);
				TB_DEName03.setText(		""+DeliveryMstRt[0][ 4]);
				TB_Post.setText(			""+DeliveryMstRt[0][ 5]);
				TB_Add01.setText(			""+DeliveryMstRt[0][ 6]);
				TB_Add02.setText(			""+DeliveryMstRt[0][ 7]);
				TB_Add03.setText(			""+DeliveryMstRt[0][ 8]);
				TB_Tel.setText(				""+DeliveryMstRt[0][ 9]);
				TB_Fax.setText(				""+DeliveryMstRt[0][10]);
				TB_Mail.setText(			""+DeliveryMstRt[0][11]);
				TB_Com01.setText(			""+DeliveryMstRt[0][12]);
				TB_Com02.setText(			""+DeliveryMstRt[0][13]);
				TB_Com03.setText(			""+DeliveryMstRt[0][14]);
				TB_PrefecturesCd.setText(	""+DeliveryMstRt[0][15]);
				TB_MunicipalityCd.setText(	""+DeliveryMstRt[0][16]);
				TB_PTMSCD.setText(			""+DeliveryMstRt[0][17]);
				TB_EntryDate.setText(		""+DeliveryMstRt[0][18]);
				TB_UpdateDate.setText(		""+DeliveryMstRt[0][19]);
				TB_EntryUser.setText(		""+DeliveryMstRt[0][20]);
				TB_UpdateUser.setText(		""+DeliveryMstRt[0][21]);
				TB_FirstClient.setText(		"("+DeliveryMstRt[0][22]+")"+DeliveryMstRt[0][25]);
				TB_LastClient.setText(		"("+DeliveryMstRt[0][23]+")"+DeliveryMstRt[0][26]);
				for(int i=0;i<B00100DefaultVariable.DelList[1].length;i++) {
					if(B00100DefaultVariable.DelList[1][i].equals(""+DeliveryMstRt[0][24])) {
						TB_DelFg.setSelectedIndex(i);
					}
				}
				
				//JISコード届け先はメンテナンス禁止
				if("JIS".equals(""+DeliveryMstRt[0][ 1])) {
					TB_DECD.setEnabled(false);
					TB_DepartmentCd.setEnabled(false);
					TB_DEName01.setEnabled(false);
					TB_DEName02.setEnabled(false);
					TB_DEName03.setEnabled(false);
					TB_Post.setEnabled(false);
					TB_Add01.setEnabled(false);
					TB_Add02.setEnabled(false);
					TB_Add03.setEnabled(false);
					TB_Tel.setEnabled(false);
					TB_Fax.setEnabled(false);
					TB_Mail.setEnabled(false);
					TB_Com01.setEnabled(false);
					TB_Com02.setEnabled(false);
					TB_Com03.setEnabled(false);
					TB_PrefecturesCd.setEnabled(false);
					TB_MunicipalityCd.setEnabled(false);
					TB_PTMSCD.setEnabled(false);
					TB_EntryDate.setEnabled(false);
					TB_UpdateDate.setEnabled(false);
					TB_EntryUser.setEnabled(false);
					TB_UpdateUser.setEnabled(false);
					TB_FirstClient.setEnabled(false);
					TB_LastClient.setEnabled(false);
					TB_DelFg.setEnabled(false);
					main_fm.add(LB_MSG);
				}else {
					main_fm.add(AddDepartmentCdBtn);
				}
			}
		}
		
		TB_DECD.setEnabled(false);
		TB_DepartmentCd.setEnabled(false);
		
		TB_EntryDate.setEnabled(false);
		TB_UpdateDate.setEnabled(false);
		TB_EntryUser.setEnabled(false);
		TB_UpdateUser.setEnabled(false);
		TB_FirstClient.setEnabled(false);
		TB_LastClient.setEnabled(false);

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
		main_fm.add(LB_PrefecturesCd);
		main_fm.add(LB_MunicipalityCd);
		main_fm.add(LB_PTMSCD);
		main_fm.add(LB_EntryDate);
		main_fm.add(LB_UpdateDate);
		main_fm.add(LB_EntryUser);
		main_fm.add(LB_UpdateUser);
		main_fm.add(LB_FirstClient);
		main_fm.add(LB_LastClient);
		main_fm.add(LB_DelFg);

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
		main_fm.add(TB_PrefecturesCd);
		main_fm.add(TB_MunicipalityCd);
		main_fm.add(TB_PTMSCD);
		main_fm.add(TB_EntryDate);
		main_fm.add(TB_UpdateDate);
		main_fm.add(TB_EntryUser);
		main_fm.add(TB_UpdateUser);
		main_fm.add(TB_FirstClient);
		main_fm.add(TB_LastClient);
		main_fm.add(TB_DelFg);
		
		main_fm.setVisible(true);
		RenewFg = true;
		
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
		
		String[] columnNames01 = {
								"Fg"
								,"届先CD"
								,"部署CD"
								,"届先名1"
								,"届先住所"};
		
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
		
		column = columnModel01.getColumn( 0);	column.setPreferredWidth( 30*A00000Main.Mul/A00000Main.Div);	//FG
		column = columnModel01.getColumn( 1);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//届先CD
		column = columnModel01.getColumn( 2);	column.setPreferredWidth( 80*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//部署CD
		column = columnModel01.getColumn( 3);	column.setPreferredWidth(250*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//届先名1
		column = columnModel01.getColumn( 4);	column.setPreferredWidth(250*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());	//届先住所
		
		//スクロール用設定
		JScrollPane scpn01 = B00110FrameParts.JScrollPaneSet(10,60,610,300,tb01);
		SameDelivery_fm.add(scpn01);
		
		//重複無視ボタン
		JLabel LB_Set  = B00110FrameParts.JLabelSet(	10,360,150,20,"重複を無視して" ,11,0);
		SameDelivery_fm.add(LB_Set);
		JButton SetBtn = B00110FrameParts.BtnSet(		10,380,100,20,"新規登録",11);
		SameDelivery_fm.add(SetBtn);
		
		//登録やり直しボタン
		JLabel LB_MstEntry  = B00110FrameParts.JLabelSet(	160,360,150,20,"チェックした届先で" ,11,0);
		SameDelivery_fm.add(LB_MstEntry);
		JButton MstEntryBtn = B00110FrameParts.BtnSet(		160,380,100,20,"登録やり直し",11);
		SameDelivery_fm.add(MstEntryBtn);
		
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
		
		
		SameDelivery_exit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					
					SameDelivery_fm.setVisible(false);
					SameDelivery_fm.dispose();
					
					RenewFg = true;
				}
			}
		});
		
		//重複無視ボタン押下時の挙動
		SetBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					int RowCount = tableModel_ms01.getRowCount();
					boolean KickFg = false;
					
					String GetDECD = M00040DeliveryMstRt.DeliveryCdGet(1)[0];
					String GetDepartmentCd = "0000";
					
					KickFg = true;
					
					if(KickFg) {
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
						String GetPrefecturesCd	= TB_PrefecturesCd.getText();
						String GetMunicipalityCd= TB_MunicipalityCd.getText();
						String GetPTMSCD		= TB_PTMSCD.getText();
						String GetDelFg 		= B00100DefaultVariable.DelList[1][TB_DelFg.getSelectedIndex()];
						
						DeliveryEntry(
								GetDECD,
								GetDepartmentCd,
								GetDEName01,
								GetDEName02,
								GetDEName03,
								GetPost,
								GetAdd01,
								GetAdd02,
								GetAdd03,
								GetTel,
								GetFax,
								GetMail,
								GetCom01,
								GetCom02,
								GetCom03,
								GetPrefecturesCd,
								GetMunicipalityCd,
								GetPTMSCD,
								GetDelFg
								);
						SetX=main_fm.getX();
						SetY=main_fm.getY();
						
						SameDelivery_fm.setVisible(false);
						SameDelivery_fm.dispose();

						main_fm.setVisible(false);
						main_fm.dispose();
						WM00061DeliveryMstRenewAndCreate.DeliveryMstRenewAndCreate(0, 0,GetDECD,GetDepartmentCd);
					}
					
					RenewFg = true;
				}
			}
		});
		
		//登録やり直しボタン押下時の挙動
		MstEntryBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					int RowCount = tableModel_ms01.getRowCount();
					boolean KickFg = false;
					
					String GetDECD = "";
					String GetDepartmentCd = "";
					
					for(int i=0;i<RowCount;i++) {
						if((boolean)tableModel_ms01.getValueAt(i, 0)) {;
							GetDECD = ""+tableModel_ms01.getValueAt(i, 1);
							GetDepartmentCd = ""+tableModel_ms01.getValueAt(i, 2);
							KickFg = true;
						}
					}
					if(KickFg) {
						SetX=main_fm.getX();
						SetY=main_fm.getY();
						
						SameDelivery_fm.setVisible(false);
						SameDelivery_fm.dispose();

						main_fm.setVisible(false);
						main_fm.dispose();
						WM00061DeliveryMstRenewAndCreate.DeliveryMstRenewAndCreate(0, 0,GetDECD,GetDepartmentCd);
					}
					
					RenewFg = true;
				}
			}
		});
		
		//上書きボタン押下時の挙動
		CdEntryBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					int RowCount = tableModel_ms01.getRowCount();
					boolean KickFg = false;
					
					String GetDECD = "";
					String GetDepartmentCd = "";
					
					for(int i=0;i<RowCount;i++) {
						if((boolean)tableModel_ms01.getValueAt(i, 0)) {
							GetDECD = ""+tableModel_ms01.getValueAt(i, 1);
							GetDepartmentCd = ""+tableModel_ms01.getValueAt(i, 2);
							KickFg = true;
						}
					}
					if(KickFg) {
						TB_DECD.setText(GetDECD);
						TB_DepartmentCd.setText(GetDepartmentCd);

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
						String GetPrefecturesCd	= TB_PrefecturesCd.getText();
						String GetMunicipalityCd= TB_MunicipalityCd.getText();
						String GetPTMSCD		= TB_PTMSCD.getText();
						String GetDelFg 		= B00100DefaultVariable.DelList[1][TB_DelFg.getSelectedIndex()];
						
						DeliveryEntry(
								GetDECD,
								GetDepartmentCd,
								GetDEName01,
								GetDEName02,
								GetDEName03,
								GetPost,
								GetAdd01,
								GetAdd02,
								GetAdd03,
								GetTel,
								GetFax,
								GetMail,
								GetCom01,
								GetCom02,
								GetCom03,
								GetPrefecturesCd,
								GetMunicipalityCd,
								GetPTMSCD,
								GetDelFg
								);
						SetX=main_fm.getX();
						SetY=main_fm.getY();
						
						SameDelivery_fm.setVisible(false);
						SameDelivery_fm.dispose();

						main_fm.setVisible(false);
						main_fm.dispose();
						WM00061DeliveryMstRenewAndCreate.DeliveryMstRenewAndCreate(0, 0,GetDECD,GetDepartmentCd);
					}
					RenewFg = true;
				}
			}
		});
		
		//新規部署扱い押下時の挙動
		NewDeptBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					int RowCount = tableModel_ms01.getRowCount();
					boolean KickFg = false;
					
					String GetDECD = "";
					String GetDepartmentCd = "";
					
					for(int i=0;i<RowCount;i++) {
						if((boolean)tableModel_ms01.getValueAt(i, 0)) {
							GetDECD = ""+tableModel_ms01.getValueAt(i, 1);
							GetDepartmentCd = M00040DeliveryMstRt.NewDepartmentCd(GetDECD);
							KickFg = true;
						}
					}
					if(KickFg) {
						TB_DECD.setText(GetDECD);
						TB_DepartmentCd.setText(GetDepartmentCd);
						
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
						String GetPrefecturesCd	= TB_PrefecturesCd.getText();
						String GetMunicipalityCd= TB_MunicipalityCd.getText();
						String GetPTMSCD		= TB_PTMSCD.getText();
						String GetDelFg 		= B00100DefaultVariable.DelList[1][TB_DelFg.getSelectedIndex()];
						
						DeliveryEntry(
								GetDECD,
								GetDepartmentCd,
								GetDEName01,
								GetDEName02,
								GetDEName03,
								GetPost,
								GetAdd01,
								GetAdd02,
								GetAdd03,
								GetTel,
								GetFax,
								GetMail,
								GetCom01,
								GetCom02,
								GetCom03,
								GetPrefecturesCd,
								GetMunicipalityCd,
								GetPTMSCD,
								GetDelFg
								);
						SetX=main_fm.getX();
						SetY=main_fm.getY();
						
						SameDelivery_fm.setVisible(false);
						SameDelivery_fm.dispose();

						main_fm.setVisible(false);
						main_fm.dispose();
						WM00061DeliveryMstRenewAndCreate.DeliveryMstRenewAndCreate(0, 0,GetDECD,GetDepartmentCd);
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
					int row_count = tb01.getRowCount();
					Boolean setBL=Boolean.valueOf(false);
					for(int i=0;i<row_count;i++){
						if(i!=e.getFirstRow()){
							if((Boolean)tb01.getValueAt(i,0)){
								tableModel_ms01.setValueAt(setBL, i, 0);
							}
						}else {
	
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
					String GetPrefecturesCd	= TB_PrefecturesCd.getText();
					String GetMunicipalityCd= TB_MunicipalityCd.getText();
					String GetPTMSCD		= TB_PTMSCD.getText();
					String GetDelFg 		= B00100DefaultVariable.DelList[1][TB_DelFg.getSelectedIndex()];
					
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
					if(null==GetPrefecturesCd	){GetPrefecturesCd = "";}
					if(null==GetMunicipalityCd	){GetMunicipalityCd = "";}
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
					GetPrefecturesCd	= B00020ToolsTextControl.Trim(GetPrefecturesCd);
					GetMunicipalityCd	= B00020ToolsTextControl.Trim(GetMunicipalityCd);
					GetPTMSCD			= B00020ToolsTextControl.Trim(GetPTMSCD);
					GetDelFg			= B00020ToolsTextControl.Trim(GetDelFg);
					
					GetPost				= B00020ToolsTextControl.num_only_String(GetPost);
					GetTel				= B00020ToolsTextControl.num_only_String(GetTel);
					GetFax				= B00020ToolsTextControl.num_only_String(GetFax);
					GetPrefecturesCd	= B00020ToolsTextControl.num_only_String(GetPrefecturesCd);
					GetMunicipalityCd	= B00020ToolsTextControl.num_only_String(GetMunicipalityCd);
					
					boolean KickFg=false;
					
					if(!"".equals(GetDEName01)) {
						if(!"".equals(GetDECD)) {
							//更新の場合は重複気にしない
							KickFg=true;
							if("".equals(GetDepartmentCd)) {
								GetDepartmentCd = "0000";
							}
						}else {
							//新規の場合　同一郵便番号・同一電話番号の納品先があれば候補を出力⇒選択or完全新規
							Object[][] SameDelivery = SameDelivery(GetPost,GetTel);
							
							if(0==SameDelivery.length) {
								String[] DeliveryCdGet = M00040DeliveryMstRt.DeliveryCdGet(1);
								GetDECD = DeliveryCdGet[0];
								GetDepartmentCd = "0000";
								KickFg=true;
							}else {
								int RowCount = tableModel_ms01.getRowCount();
								for(int i=0;i<RowCount;i++) {
									tableModel_ms01.removeRow(0);
								}
								for(int i=0;i<SameDelivery.length;i++) {
									Object[] SetOb = new Object[5];
									
									SetOb[0] = false;
									SetOb[ 1] = ""+SameDelivery[i][0];	//届先CD
									SetOb[ 2] = ""+SameDelivery[i][1];	//部署CD
									SetOb[ 3] = ""+SameDelivery[i][2];	//届先名１
									SetOb[ 4] = ""+SameDelivery[i][6]+SameDelivery[i][7]+SameDelivery[i][8];	//届先住所
									
									tableModel_ms01.addRow(SetOb);
								}
								if(0<SameDelivery.length) {
									B10010TableControl.AddSortON(tb01,tableModel_ms01);
								}else {
									B10010TableControl.AddSortOFF(tb01,tableModel_ms01);
								}
								
								SameDelivery_fm.setVisible(true);
							}
							
						}
					}else {
						JOptionPane.showMessageDialog(null, "届先名は必要ですよ");
					}
					
					if(KickFg) {
						DeliveryEntry(
								GetDECD,
								GetDepartmentCd,
								GetDEName01,
								GetDEName02,
								GetDEName03,
								GetPost,
								GetAdd01,
								GetAdd02,
								GetAdd03,
								GetTel,
								GetFax,
								GetMail,
								GetCom01,
								GetCom02,
								GetCom03,
								GetPrefecturesCd,
								GetMunicipalityCd,
								GetPTMSCD,
								GetDelFg
								);
						SetX=main_fm.getX();
						SetY=main_fm.getY();
						
						SameDelivery_fm.setVisible(false);
						SameDelivery_fm.dispose();

						main_fm.setVisible(false);
						main_fm.dispose();
						WM00061DeliveryMstRenewAndCreate.DeliveryMstRenewAndCreate(0, 0,GetDECD,GetDepartmentCd);
					}
					
					RenewFg = true;
				}
			}
		});
		
		//部署追加ボタン押下時の挙動
		AddDepartmentCdBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					String GetDECD = TB_DECD.getText();
					String NewDepartmentCd = M00040DeliveryMstRt.NewDepartmentCd(GetDECD);
					TB_DepartmentCd.setText(NewDepartmentCd);
					RenewFg = true;
				}
			}
		});
		
		//郵便番号フォーカス消失時の挙動
		TB_Post.addFocusListener(new FocusAdapter(){
			@Override
			public void focusLost(FocusEvent e){
				String GetPost = TB_Post.getText();	if(null==GetPost) {GetPost="";}
				GetPost = B00020ToolsTextControl.Trim(B00020ToolsTextControl.num_only_String(GetPost));
				TB_Post.setText(GetPost);
				TB_PrefecturesCd.setText("");
				TB_MunicipalityCd.setText("");
				
				ArrayList<String> SearchPOST = new ArrayList<String>();
				ArrayList<String> SearchAdd = new ArrayList<String>();
				boolean AllSearch = false;
				boolean PostPerfectMatch = true;
				
				if(!"".equals(GetPost)) {
					SearchPOST.add(GetPost);
				}
				
				Object[][] PostRt = M10010PostMstRt.PostRt(
							SearchPOST,
							SearchAdd,
							AllSearch,
							PostPerfectMatch);
				
				if(0<PostRt.length) {
					boolean KickFg = false;
					String GetAdd01 = TB_Add01.getText();	if(null==GetAdd01) {GetAdd01="";}
					String GetAdd02 = TB_Add02.getText();	if(null==GetAdd02) {GetAdd02="";}
					
					if("".equals(GetAdd01)&&"".equals(GetAdd02)) {
						KickFg = true;
					}
					
					if(!KickFg) {
						int option = JOptionPane.showConfirmDialog(null, "郵便番号を元に住所上書きしますか？","登録確認", JOptionPane.YES_NO_OPTION,
							      JOptionPane.WARNING_MESSAGE);
						if (option == JOptionPane.YES_OPTION){
							KickFg = true;
						}else {
							KickFg = false;
						}
					}
					
					if(KickFg) {
						TB_Add01.setText("");
						TB_Add02.setText("");
						TB_Add03.setText("");
						
						TB_Add01.setText(""+PostRt[0][1]+PostRt[0][2]);
						TB_Add02.setText(""+PostRt[0][3]);
					}
					if(2<(""+PostRt[0][4]).length()) {
						TB_PrefecturesCd.setText((""+PostRt[0][4]).substring(0,2));
					}else {
						TB_PrefecturesCd.setText("");
					}
					TB_MunicipalityCd.setText(""+PostRt[0][4]);
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
	
	private static Object[][] SameDelivery(String GetPost,String GetTel) {
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
		boolean SearcNotJis = false;
		boolean AllSearch = false;
		
		if(null!=GetPost && !"".equals(GetPost) && null!=GetTel && !"".equals(GetTel)) {
			SearchPost.add(GetPost);
			SearchTel.add(GetTel);
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
				AllSearch
				);
		
		return DeliveryMstRt;
	}
	
	private static void DeliveryEntry(
			String GetDECD,
			String GetDepartmentCd,
			String GetDEName01,
			String GetDEName02,
			String GetDEName03,
			String GetPost,
			String GetAdd01,
			String GetAdd02,
			String GetAdd03,
			String GetTel,
			String GetFax,
			String GetMail,
			String GetCom01,
			String GetCom02,
			String GetCom03,
			String GetPrefecturesCd,
			String GetMunicipalityCd,
			String GetPTMSCD,
			String GetDelFg
			) {
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
		if(null==GetPrefecturesCd	){GetPrefecturesCd 	= "";}
		if(null==GetMunicipalityCd	){GetMunicipalityCd = "";}
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
		GetPrefecturesCd	= B00020ToolsTextControl.Trim(GetPrefecturesCd);
		GetMunicipalityCd	= B00020ToolsTextControl.Trim(GetMunicipalityCd);
		GetPTMSCD			= B00020ToolsTextControl.Trim(GetPTMSCD);
		GetDelFg			= B00020ToolsTextControl.Trim(GetDelFg);
		
		GetPost				= B00020ToolsTextControl.num_only_String(GetPost);
		GetTel				= B00020ToolsTextControl.num_only_String(GetTel);
		GetFax				= B00020ToolsTextControl.num_only_String(GetFax);
		GetPrefecturesCd	= B00020ToolsTextControl.num_only_String(GetPrefecturesCd);
		GetMunicipalityCd	= B00020ToolsTextControl.num_only_String(GetMunicipalityCd);
		
		String now_dtm = B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[1];
		
		//現在の市区町村CDを仮格納
		String WorkMunicipalityCd = GetMunicipalityCd;
		
		boolean KickFg = true;
		//郵便番号を元に市区町村CD設定
		if(!"".equals(GetPost)) {
			ArrayList<String> SearchPOST = new ArrayList<String>();
			ArrayList<String> SearchAddT = new ArrayList<String>();
			boolean AllSearch = false;
			boolean PostPerfectMatch = true;
			SearchPOST.add(GetPost);
			
			Object[][] PostRt = M10010PostMstRt.PostRt(SearchPOST, SearchAddT, AllSearch, PostPerfectMatch);
			if(0<PostRt.length) {
				GetMunicipalityCd = ""+PostRt[0][4];
				KickFg = false;
			}
		}
		
		//住所を元に市区町村CD設定それでも無理なら諦めて"00000"設定
		if(KickFg) {
			String[] AddList = {GetAdd01+GetAdd02+GetAdd03};
			
			String[][] AddToMunicipality = M10010PostMstRt.AddToMunicipality(AddList);
			
			GetMunicipalityCd = AddToMunicipality[0][1];
			if("".equals(GetMunicipalityCd)) {GetMunicipalityCd = "00000";}
			
		}
		//設定しようとしている市区町村CDと判定された市区町村Cdが異なる場合確認
		if(GetMunicipalityCd.equals(WorkMunicipalityCd)||"".equals(WorkMunicipalityCd)) {
			
		}else {
			int option = JOptionPane.showConfirmDialog(null, "登録しようとしている市区町村コードが\n正しくない可能性がありますが\n設定値を優先しますか？","登録確認", JOptionPane.YES_NO_OPTION,
				      JOptionPane.WARNING_MESSAGE);
			if (option == JOptionPane.YES_OPTION){
				GetMunicipalityCd = WorkMunicipalityCd;
			}else {
			}
		}
		
		if(2<=GetMunicipalityCd.length()) {
			GetPrefecturesCd = GetMunicipalityCd.substring(0,2);
		}else {
			GetPrefecturesCd = "00";
		}
		
		
		String[][] SetString = {
				{"DECD"				,"1","1",GetDECD}			//納品先コード
				,{"DepartmentCd"	,"1","1",GetDepartmentCd}	//部署CD
				,{"DEName01"		,"1","1",GetDEName01}		//納品先名1
				,{"DEName02"		,"1","1",GetDEName02}		//納品先名2
				,{"DEName03"		,"1","1",GetDEName03}		//納品先名3
				,{"Post"			,"1","1",GetPost}			//納品先郵便
				,{"Add01"			,"1","1",GetAdd01}			//納品先住所1
				,{"Add02"			,"1","1",GetAdd02}			//納品先住所2
				,{"Add03"			,"1","1",GetAdd03}			//納品先住所3
				,{"Tel"				,"1","1",GetTel}			//納品先電話
				,{"Fax"				,"1","1",GetFax}			//納品先FAX
				,{"Mail"			,"1","1",GetMail}			//納品先MAIL
				,{"Com01"			,"1","1",GetCom01}			//コメント1
				,{"Com02"			,"1","1",GetCom02}			//コメント2
				,{"Com03"			,"1","1",GetCom03}			//コメント3
				,{"PrefecturesCd"	,"1","1",GetPrefecturesCd}	//JIS県CD2桁
				,{"MunicipalityCd"	,"1","1",GetMunicipalityCd}	//JIS市区町村CD5桁
				,{"PTMSCD"			,"1","1",GetPTMSCD}			//基幹システム発着地コード
				,{"EntryDate"		,"1","0",now_dtm}			//データ登録日時
				,{"UpdateDate"		,"1","1",now_dtm}			//データ更新日時
				,{"EntryUser"		,"1","0","(" + A00000Main.LoginUserId + ")" + A00000Main.LoginUserName}	//登録者コード
				,{"UpdateUser"		,"1","1","(" + A00000Main.LoginUserId + ")" + A00000Main.LoginUserName}	//更新者コード
				,{"FirstClient"		,"1","0","" + A00000Main.ClCd}	//登録した荷主CD
				,{"LastClient"		,"1","1","" + A00000Main.ClCd}	//更新した荷主CD
				,{"DelFg"			,"1","1",GetDelFg}	//削除区分

		};
		
		String tgt_table = "KM0040_DELIVERYMST";
		String[][] field_name = new String[SetString.length][3];
		String[][] entry_data = new String[1][SetString.length];
		String[] judg_field = new String[2];
		String[][] judg_data = new String[1][2];
		String TgtDB = "NYANKO";
		int non_msg_fg = 1;

		judg_field[0] = "DECD";				//納品先コード
		judg_field[1] = "DepartmentCd";		//部署CD
		judg_data[0][0] = GetDECD;
		judg_data[0][1] = GetDepartmentCd;
		
		for(int i=0;i<SetString.length;i++) {
			field_name[i][0] = SetString[i][0];
			field_name[i][1] = SetString[i][1];
			field_name[i][2] = SetString[i][2];
			entry_data[0][i] = SetString[i][3];
		}
		
		A00020InsertUdateSQL.RUN_SQLS_EU(tgt_table, field_name, entry_data, judg_field, judg_data, non_msg_fg,TgtDB);
	}
}