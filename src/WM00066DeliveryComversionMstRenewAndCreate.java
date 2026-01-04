import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
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

public class WM00066DeliveryComversionMstRenewAndCreate{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	public static void DeliveryComversionMstRenewAndCreate(int x,int y,String ClGpCD,final String CL_DECD) {
		A00000Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		
		if(null==ClGpCD) {ClGpCD = "";}
		
		if("".equals(ClGpCD)) {ClGpCD = A00000Main.ClGp;}
		
		final JFrame main_fm = B00110FrameParts.FrameCreate(x,y,550,780,"Corgi00届先変換マスタ登録・更新","");
		JLabel userinfo = B00110FrameParts.UserInfo();
		JButton exit_btn = B00110FrameParts.ExitBtn();
		JButton entry_btn = B00110FrameParts.EntryBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		main_fm.add(entry_btn);
		
		String[] PriorityFirstFg = {"データ優先","マスタ優先"};
		
		JLabel LB_ClGpCD				= B00110FrameParts.JLabelSet(  0, 40,100,20,"荷主グループCD:"	,	11,1);
		JLabel LB_CLGpName01			= B00110FrameParts.JLabelSet(  0, 65,100,20,"荷主グループ名:"	,	11,1);
		JLabel LB_CL_DECD				= B00110FrameParts.JLabelSet(  0, 90,100,20,"荷主届先CD:"		,	11,1);
		JLabel LB_DECD					= B00110FrameParts.JLabelSet(  0,115,100,20,"届先CD:"			,	11,1);
		JLabel LB_DepartmentCd			= B00110FrameParts.JLabelSet(  0,140,100,20,"届先部署CD:"		,	11,1);
		JLabel LB_DEName01				= B00110FrameParts.JLabelSet(  0,165,100,20,"届先名1:"			,	11,1);
		JLabel LB_DEName02				= B00110FrameParts.JLabelSet(  0,190,100,20,"届先名2:"			,	11,1);
		JLabel LB_DEName03				= B00110FrameParts.JLabelSet(  0,215,100,20,"届先名3:"			,	11,1);
		JLabel LB_Post					= B00110FrameParts.JLabelSet(  0,240,100,20,"届先郵便:"		,	11,1);
		JLabel LB_Add01					= B00110FrameParts.JLabelSet(  0,265,100,20,"届先住所1:"		,	11,1);
		JLabel LB_Add02					= B00110FrameParts.JLabelSet(  0,290,100,20,"届先住所2:"		,	11,1);
		JLabel LB_Add03					= B00110FrameParts.JLabelSet(  0,315,100,20,"届先住所3:"		,	11,1);
		JLabel LB_Tel					= B00110FrameParts.JLabelSet(  0,340,100,20,"届先電話:"		,	11,1);
		JLabel LB_Fax					= B00110FrameParts.JLabelSet(  0,365,100,20,"届先FAX:"			,	11,1);
		JLabel LB_Mail					= B00110FrameParts.JLabelSet(  0,390,100,20,"届先MAIL:"		,	11,1);
		JLabel LB_SetName				= B00110FrameParts.JLabelSet(  0,415,100,20,"送り状登録名:"	,	11,1);
		JLabel LB_Com01					= B00110FrameParts.JLabelSet(  0,440,100,20,"コメント01:"		,	11,1);
		JLabel LB_Com02					= B00110FrameParts.JLabelSet(  0,465,100,20,"コメント02:"		,	11,1);
		JLabel LB_Com03					= B00110FrameParts.JLabelSet(  0,490,100,20,"コメント03:"		,	11,1);
		JLabel LB_Com04					= B00110FrameParts.JLabelSet(  0,515,100,20,"コメント04:"		,	11,1);
		JLabel LB_Com05					= B00110FrameParts.JLabelSet(  0,540,100,20,"コメント05:"		,	11,1);
		JLabel LB_DelFg					= B00110FrameParts.JLabelSet(  0,565,100,20,"削除区分:"		,	11,1);
		JLabel LB_MstPriorityFirstFg	= B00110FrameParts.JLabelSet(  0,590,100,20,"届先マスタ優先:"	,	11,1);
		JLabel LB_EntryDate				= B00110FrameParts.JLabelSet(  0,615,100,20,"データ登録日時:"	,	11,1);
		JLabel LB_UpdateDate			= B00110FrameParts.JLabelSet(  0,640,100,20,"データ更新日時:"	,	11,1);
		JLabel LB_EntryUser				= B00110FrameParts.JLabelSet(  0,665,100,20,"登録者コード:"	,	11,1);
		JLabel LB_UpdateUser			= B00110FrameParts.JLabelSet(  0,690,100,20,"更新者コード:"	,	11,1);
		
		final JTextField  TB_ClGpCD				= B00110FrameParts.JTextFieldSet(	100, 40,100,20,"",11,0);			//荷主グループCD
		final JTextField  TB_CLGpName01			= B00110FrameParts.JTextFieldSet(	100, 65,200,20,"",11,0);			//荷主グループ名
		final JTextField  TB_CL_DECD			= B00110FrameParts.JTextFieldSet(	100, 90,100,20,"",11,0);			//荷主届先CD
		final JTextField  TB_DECD				= B00110FrameParts.JTextFieldSet(	100,115,100,20,"",11,0);			//届先CD
		final JTextField  TB_DepartmentCd		= B00110FrameParts.JTextFieldSet(	100,140,100,20,"",11,0);			//届先部署CD
		final JTextField  TB_DEName01			= B00110FrameParts.JTextFieldSet(	100,165,200,20,"",11,0);			//届先名1
		final JTextField  TB_DEName02			= B00110FrameParts.JTextFieldSet(	100,190,200,20,"",11,0);			//届先名2
		final JTextField  TB_DEName03			= B00110FrameParts.JTextFieldSet(	100,215,200,20,"",11,0);			//届先名3
		final JTextField  TB_Post				= B00110FrameParts.JTextFieldSet(	100,240,100,20,"",11,0);			//届先郵便
		final JTextField  TB_Add01				= B00110FrameParts.JTextFieldSet(	100,265,200,20,"",11,0);			//届先住所1
		final JTextField  TB_Add02				= B00110FrameParts.JTextFieldSet(	100,290,200,20,"",11,0);			//届先住所2
		final JTextField  TB_Add03				= B00110FrameParts.JTextFieldSet(	100,315,200,20,"",11,0);			//届先住所3
		final JTextField  TB_Tel				= B00110FrameParts.JTextFieldSet(	100,340,100,20,"",11,0);			//届先電話
		final JTextField  TB_Fax				= B00110FrameParts.JTextFieldSet(	100,365,100,20,"",11,0);			//届先FAX
		final JTextField  TB_Mail				= B00110FrameParts.JTextFieldSet(	100,390,200,20,"",11,0);			//届先MAIL
		final JTextField  TB_SetName			= B00110FrameParts.JTextFieldSet(	100,415,200,20,"",11,0);			//送り状登録名
		final JTextField  TB_Com01				= B00110FrameParts.JTextFieldSet(	100,440,200,20,"",11,0);			//コメント01
		final JTextField  TB_Com02				= B00110FrameParts.JTextFieldSet(	100,465,200,20,"",11,0);			//コメント02
		final JTextField  TB_Com03				= B00110FrameParts.JTextFieldSet(	100,490,200,20,"",11,0);			//コメント03
		final JTextField  TB_Com04				= B00110FrameParts.JTextFieldSet(	100,515,200,20,"",11,0);			//コメント04
		final JTextField  TB_Com05				= B00110FrameParts.JTextFieldSet(	100,540,200,20,"",11,0);			//コメント05
		final JComboBox   TB_DelFg				= B00110FrameParts.JComboBoxSet( 	100,565,150,20,B00100DefaultVariable.DelList[0],11);	//削除区分
		final JComboBox   TB_MstPriorityFirstFg	= B00110FrameParts.JComboBoxSet( 	100,590,150,20,PriorityFirstFg,11);						//届先マスタ優先
		final JTextField  TB_EntryDate			= B00110FrameParts.JTextFieldSet(	100,615,200,20,"",11,0);			//データ登録日時
		final JTextField  TB_UpdateDate			= B00110FrameParts.JTextFieldSet(	100,640,200,20,"",11,0);			//データ更新日時
		final JTextField  TB_EntryUser			= B00110FrameParts.JTextFieldSet(	100,665,200,20,"",11,0);			//登録者コード
		final JTextField  TB_UpdateUser			= B00110FrameParts.JTextFieldSet(	100,690,200,20,"",11,0);			//更新者コード
		
		TB_ClGpCD.setEditable(false);
		TB_CLGpName01.setEditable(false);
		TB_DECD.setEditable(false);
		TB_DepartmentCd.setEditable(false);
		TB_DEName01.setEditable(false);
		TB_DEName02.setEditable(false);
		TB_DEName03.setEditable(false);
		TB_Post.setEditable(false);
		TB_Add01.setEditable(false);
		TB_Add02.setEditable(false);
		TB_Add03.setEditable(false);
		TB_Tel.setEditable(false);
		TB_Fax.setEditable(false);
		TB_Mail.setEditable(false);
		TB_EntryDate.setEditable(false);
		TB_UpdateDate.setEditable(false);
		TB_EntryUser.setEditable(false);
		TB_UpdateUser.setEditable(false);
		
		TB_ClGpCD.setText(ClGpCD);
		
		if(!"".equals(ClGpCD)) {
			ArrayList<String> SearchClGpCD 		= new ArrayList<String>();
			ArrayList<String> SearchCLGpName 	= new ArrayList<String>();
			ArrayList<String> SearchPost 		= new ArrayList<String>();
			ArrayList<String> SearchAdd 		= new ArrayList<String>();
			ArrayList<String> SearchTel 		= new ArrayList<String>();
			ArrayList<String> SearchFax 		= new ArrayList<String>();
			ArrayList<String> SearchMail 		= new ArrayList<String>();
			ArrayList<String> SearchCom 		= new ArrayList<String>();
			boolean AllSearch = false;
			
			SearchClGpCD.add(ClGpCD);
			
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
			}
		}
		
		
		if(null==CL_DECD) {
			TB_CL_DECD.setText("");
		}else {
			TB_CL_DECD.setText(CL_DECD);
			if(!"".equals(CL_DECD)) {
				TB_CL_DECD.setEditable(false);
				
				ArrayList<String> SearchClGpCD 				= new ArrayList<String>();
				ArrayList<String> SearchCLGpName 			= new ArrayList<String>();
				ArrayList<String> SearchCL_DECD 			= new ArrayList<String>();
				ArrayList<String> SearchDECD 				= new ArrayList<String>();
				ArrayList<String> SearchDepartmentCd 		= new ArrayList<String>();
				ArrayList<String> SearchDEName 				= new ArrayList<String>();
				ArrayList<String> SearchPost 				= new ArrayList<String>();
				ArrayList<String> SearchAdd 				= new ArrayList<String>();
				ArrayList<String> SearchTel 				= new ArrayList<String>();
				ArrayList<String> SearchFax 				= new ArrayList<String>();
				ArrayList<String> SearchMail 				= new ArrayList<String>();
				ArrayList<String> SearchSetName 			= new ArrayList<String>();
				ArrayList<String> SearchCom 				= new ArrayList<String>();
				ArrayList<String> SearchDelFg 				= new ArrayList<String>();
				ArrayList<String> SearchMstPriorityFirstFg	= new ArrayList<String>();
				boolean AllSearch = false;
				
				SearchClGpCD.add(ClGpCD);
				SearchCL_DECD.add(CL_DECD);
				
				Object[][] DeliveryComversionMstRt = M00060DeliveryComversionMstRt.DeliveryComversionMstRt(
									SearchClGpCD,
									SearchCLGpName,
									SearchCL_DECD,
									SearchDECD,
									SearchDepartmentCd,
									SearchDEName,
									SearchPost,
									SearchAdd,
									SearchTel,
									SearchFax,
									SearchMail,
									SearchSetName,
									SearchCom,
									SearchDelFg,
									SearchMstPriorityFirstFg,
									AllSearch);
				if(0<DeliveryComversionMstRt.length) {
					TB_ClGpCD.setText(		""+DeliveryComversionMstRt[0][ 0]);
					TB_CLGpName01.setText(	""+DeliveryComversionMstRt[0][ 1]);
					TB_CL_DECD.setText(		""+DeliveryComversionMstRt[0][ 2]);
					TB_DECD.setText(		""+DeliveryComversionMstRt[0][ 3]);
					TB_DepartmentCd.setText(""+DeliveryComversionMstRt[0][ 4]);
					TB_DEName01.setText(	""+DeliveryComversionMstRt[0][ 5]);
					TB_DEName02.setText(	""+DeliveryComversionMstRt[0][ 6]);
					TB_DEName03.setText(	""+DeliveryComversionMstRt[0][ 7]);
					TB_Post.setText(		""+DeliveryComversionMstRt[0][ 8]);
					TB_Add01.setText(		""+DeliveryComversionMstRt[0][ 9]);
					TB_Add02.setText(		""+DeliveryComversionMstRt[0][10]);
					TB_Add03.setText(		""+DeliveryComversionMstRt[0][11]);
					TB_Tel.setText(			""+DeliveryComversionMstRt[0][12]);
					TB_Fax.setText(			""+DeliveryComversionMstRt[0][13]);
					TB_Mail.setText(		""+DeliveryComversionMstRt[0][14]);
					TB_SetName.setText(		""+DeliveryComversionMstRt[0][15]);
					TB_Com01.setText(		""+DeliveryComversionMstRt[0][16]);
					TB_Com02.setText(		""+DeliveryComversionMstRt[0][17]);
					TB_Com03.setText(		""+DeliveryComversionMstRt[0][18]);
					TB_Com04.setText(		""+DeliveryComversionMstRt[0][19]);
					TB_Com05.setText(		""+DeliveryComversionMstRt[0][20]);
					for(int i=0;i<B00100DefaultVariable.DelList[1].length;i++) {
						if(B00100DefaultVariable.DelList[1][i].equals(""+DeliveryComversionMstRt[0][25])) {
							TB_DelFg.setSelectedIndex(i);
						}
					}
					if(1==(int)DeliveryComversionMstRt[0][26]){TB_MstPriorityFirstFg.setSelectedIndex(1);}else {TB_MstPriorityFirstFg.setSelectedIndex(0);}
					TB_EntryDate.setText(	""+DeliveryComversionMstRt[0][21]);
					TB_UpdateDate.setText(	""+DeliveryComversionMstRt[0][22]);
					TB_EntryUser.setText(	""+DeliveryComversionMstRt[0][23]);
					TB_UpdateUser.setText(	""+DeliveryComversionMstRt[0][24]);
				}
			}
		}
		
		final JCheckBox DeliveryRenewMode = B00110FrameParts.JCheckBoxSet(220,140,200,20,"届先修正モード",11);
		DeliveryRenewMode.setSelected(false);
		main_fm.add(DeliveryRenewMode);
		
		DeliveryRenewMode.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					if(DeliveryRenewMode.isSelected()) {
						TB_DEName01.setEditable(true);
						TB_DEName02.setEditable(true);
						TB_DEName03.setEditable(true);
						TB_Post.setEditable(true);
						TB_Add01.setEditable(true);
						TB_Add02.setEditable(true);
						TB_Add03.setEditable(true);
						TB_Tel.setEditable(true);
						TB_Fax.setEditable(true);
						TB_Mail.setEditable(true);
					}else {
						TB_DEName01.setEditable(false);
						TB_DEName02.setEditable(false);
						TB_DEName03.setEditable(false);
						TB_Post.setEditable(false);
						TB_Add01.setEditable(false);
						TB_Add02.setEditable(false);
						TB_Add03.setEditable(false);
						TB_Tel.setEditable(false);
						TB_Fax.setEditable(false);
						TB_Mail.setEditable(false);
					}
					RenewFg = true;
				}
			}
		});

		main_fm.add(LB_ClGpCD);
		main_fm.add(LB_CLGpName01);
		main_fm.add(LB_CL_DECD);
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
		main_fm.add(LB_SetName);
		main_fm.add(LB_Com01);
		main_fm.add(LB_Com02);
		main_fm.add(LB_Com03);
		main_fm.add(LB_Com04);
		main_fm.add(LB_Com05);
		main_fm.add(LB_DelFg);
		main_fm.add(LB_MstPriorityFirstFg);
		main_fm.add(LB_EntryDate);
		main_fm.add(LB_UpdateDate);
		main_fm.add(LB_EntryUser);
		main_fm.add(LB_UpdateUser);
		
		main_fm.add(TB_ClGpCD);
		main_fm.add(TB_CLGpName01);
		main_fm.add(TB_CL_DECD);
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
		main_fm.add(TB_SetName);
		main_fm.add(TB_Com01);
		main_fm.add(TB_Com02);
		main_fm.add(TB_Com03);
		main_fm.add(TB_Com04);
		main_fm.add(TB_Com05);
		main_fm.add(TB_DelFg);
		main_fm.add(TB_MstPriorityFirstFg);
		main_fm.add(TB_EntryDate);
		main_fm.add(TB_UpdateDate);
		main_fm.add(TB_EntryUser);
		main_fm.add(TB_UpdateUser);
		
		//届先検索ボタン
		JButton SearchBtn = B00110FrameParts.BtnSet(220,115,100,20,"届先検索",11);
		main_fm.add(SearchBtn);
		
		//届先解放ボタン
		JButton DeliveryReleaseBtn = B00110FrameParts.BtnSet(340,115,100,20,"届先解放",11);
		main_fm.add(DeliveryReleaseBtn);
		
		final JFrame DeliSearch_fm = B00110FrameParts.FrameCreate(x+20,y+20,650,650,"Corgi00届先変換マスタ登録・更新(届先検索)","");
		JButton DeliSearchExit_btn = B00110FrameParts.ExitBtn();
		DeliSearch_fm.add(DeliSearchExit_btn);
		DeliSearchExit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				DeliSearch_fm.setVisible(false);
			}
		});
		
		JLabel LB_SearchDECD				= B00110FrameParts.JLabelSet(  0, 40,100,20,"届先CD:"			,11,1);
		JLabel LB_SearchDepartmentCd		= B00110FrameParts.JLabelSet(  0, 65,100,20,"届先部署CD:"		,11,1);
		JLabel LB_SearchDEName				= B00110FrameParts.JLabelSet(  0, 90,100,20,"届先名:"			,11,1);
		JLabel LB_SearchAdd					= B00110FrameParts.JLabelSet(  0,115,100,20,"住所:"			,11,1);
		JLabel LB_SearchTel					= B00110FrameParts.JLabelSet(  0,140,100,20,"電話:"			,11,1);
		
		JLabel LB_SearchPost				= B00110FrameParts.JLabelSet(250, 40, 50,20,"郵便:"			,11,1);
		JLabel LB_SearchFax					= B00110FrameParts.JLabelSet(250, 65, 50,20,"FAX:"				,11,1);
		JLabel LB_SearchMail				= B00110FrameParts.JLabelSet(250, 90, 50,20,"MAIL:"			,11,1);
		
		final JTextField  TB_SearchDECD				= B00110FrameParts.JTextFieldSet(	100, 40,100,20,"",11,0);			//届先CD
		final JTextField  TB_SearchDepartmentCd		= B00110FrameParts.JTextFieldSet(	100, 65,100,20,"",11,0);			//届先部署CD
		final JTextField  TB_SearchDEName			= B00110FrameParts.JTextFieldSet(	100, 90,100,20,"",11,0);			//届先名
		final JTextField  TB_SearchAdd				= B00110FrameParts.JTextFieldSet(	100,115,100,20,"",11,0);			//住所
		final JTextField  TB_SearchTel				= B00110FrameParts.JTextFieldSet(	100,140,100,20,"",11,0);			//電話
		
		final JTextField  TB_SearchPost				= B00110FrameParts.JTextFieldSet(	300, 40,100,20,"",11,0);			//郵便
		final JTextField  TB_SearchFax				= B00110FrameParts.JTextFieldSet(	300, 65,100,20,"",11,0);			//FAX
		final JTextField  TB_SearchMail				= B00110FrameParts.JTextFieldSet(	300, 90,100,20,"",11,0);			//MAIL
		
		JLabel LB2_SearchDECD				= B00110FrameParts.JLabelSet(200, 40, 50,20,"と一致"	,11,0);
		JLabel LB2_SearchDepartmentCd		= B00110FrameParts.JLabelSet(200, 65, 50,20,"と一致"	,11,0);
		JLabel LB2_SearchDEName				= B00110FrameParts.JLabelSet(200, 90, 50,20,"を含む"	,11,0);
		JLabel LB2_SearchAdd				= B00110FrameParts.JLabelSet(200,115, 50,20,"を含む"	,11,0);
		JLabel LB2_SearchTel				= B00110FrameParts.JLabelSet(200,140, 50,20,"を含む"	,11,0);
		
		JLabel LB2_SearchPost				= B00110FrameParts.JLabelSet(400, 40, 50,20,"で始まる"	,11,0);
		JLabel LB2_SearchFax				= B00110FrameParts.JLabelSet(400, 65, 50,20,"を含む"	,11,0);
		JLabel LB2_SearchMail				= B00110FrameParts.JLabelSet(400, 90, 50,20,"を含む"	,11,0);
		
		//届先候補検索ボタン
		JButton DeliSearchBtn = B00110FrameParts.BtnSet(300,140,100,20,"検索",11);
		DeliSearch_fm.add(DeliSearchBtn);
		
		DeliSearch_fm.add(LB_SearchDECD);
		DeliSearch_fm.add(LB_SearchDepartmentCd);
		DeliSearch_fm.add(LB_SearchDEName);
		DeliSearch_fm.add(LB_SearchPost);
		DeliSearch_fm.add(LB_SearchAdd);
		DeliSearch_fm.add(LB_SearchTel);
		DeliSearch_fm.add(LB_SearchFax);
		DeliSearch_fm.add(LB_SearchMail);
		
		DeliSearch_fm.add(TB_SearchDECD);
		DeliSearch_fm.add(TB_SearchDepartmentCd);
		DeliSearch_fm.add(TB_SearchDEName);
		DeliSearch_fm.add(TB_SearchPost);
		DeliSearch_fm.add(TB_SearchAdd);
		DeliSearch_fm.add(TB_SearchTel);
		DeliSearch_fm.add(TB_SearchFax);
		DeliSearch_fm.add(TB_SearchMail);
		
		DeliSearch_fm.add(LB2_SearchDECD);
		DeliSearch_fm.add(LB2_SearchDepartmentCd);
		DeliSearch_fm.add(LB2_SearchDEName);
		DeliSearch_fm.add(LB2_SearchPost);
		DeliSearch_fm.add(LB2_SearchAdd);
		DeliSearch_fm.add(LB2_SearchTel);
		DeliSearch_fm.add(LB2_SearchFax);
		DeliSearch_fm.add(LB2_SearchMail);
		
		Object[][] RtDeliveryComversionMstRt = M00060DeliveryComversionMstRt.RtDeliveryComversionMstRt();
		
		String[] columnNames01 = new String[RtDeliveryComversionMstRt.length+1];
		
		columnNames01[0] = "Fg";
		for(int i=0;i<RtDeliveryComversionMstRt.length;i++) {
			columnNames01[1+i] = ""+RtDeliveryComversionMstRt[i][3];
		}
		
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
		
		for(int i=0;i<RtDeliveryComversionMstRt.length;i++) {
			column = columnModel01.getColumn(1+i);	column.setPreferredWidth( 90*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());
		}
		
		//スクロール用設定
		JScrollPane scpn01 = B00110FrameParts.JScrollPaneSet(10,165,610,335,tb01);
		DeliSearch_fm.add(scpn01);
		
		JLabel LB_RenewBtn  = B00110FrameParts.JLabelSet(	520,520,100,20,"チェック行を" ,11,2);
		DeliSearch_fm.add(LB_RenewBtn);
		
		//選択ボタン
		JButton RenewBtn = B00110FrameParts.BtnSet(		520,540,100,20,"選択",11);
		DeliSearch_fm.add(RenewBtn);
		
		main_fm.setVisible(true);
		RenewFg = true;
		
		
		//届先候補検索ボタン
		DeliSearchBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					int RowCount = tableModel_ms01.getRowCount();
					
					for(int i=0;i<RowCount;i++) {
						tableModel_ms01.removeRow(0);
					}
					
					String GetSearchDECD			= B00111FramePartsControl.JTextFieldRt(TB_SearchDECD);
					String GetSearchDepartmentCd	= B00111FramePartsControl.JTextFieldRt(TB_SearchDepartmentCd);
					String GetSearchDEName			= B00111FramePartsControl.JTextFieldRt(TB_SearchDEName);
					String GetSearchAdd				= B00111FramePartsControl.JTextFieldRt(TB_SearchAdd);
					String GetSearchTel				= B00111FramePartsControl.JTextFieldRt(TB_SearchTel);
					
					String GetSearchPost			= B00111FramePartsControl.JTextFieldRt(TB_SearchPost);
					String GetSearchFax				= B00111FramePartsControl.JTextFieldRt(TB_SearchFax);
					String GetSearchMail			= B00111FramePartsControl.JTextFieldRt(TB_SearchMail);
					
					boolean SearchTelExactMatch = false;
					boolean AllSearch = true;
					
					Object[][] DeliveryMstRt = DeliveryMstRt(
							GetSearchDECD
							,GetSearchDepartmentCd
							,GetSearchDEName
							,GetSearchAdd
							,GetSearchTel
							,GetSearchPost
							,GetSearchFax
							,GetSearchMail
							,SearchTelExactMatch
							,AllSearch
							);
					
					for(int i=0;i<DeliveryMstRt.length;i++) {
						Object[] SetOb = new Object[DeliveryMstRt[i].length+1];
						SetOb[0] = false;
						for(int i01=0;i01<DeliveryMstRt[i].length;i01++) {
							SetOb[i01+1] = ""+DeliveryMstRt[i][i01];
						}
						tableModel_ms01.addRow(SetOb);
					}
					
					RenewFg = true;
				}
			}
		});
		
		//選択ボタン押下時の挙動
		RenewBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					int RowCount = tableModel_ms01.getRowCount();
					for(int i=0;i<RowCount;i++) {
						if((boolean)tableModel_ms01.getValueAt(i, 0)) {
							TB_DECD.setText(			""+tableModel_ms01.getValueAt(i, 1));
							TB_DepartmentCd.setText(	""+tableModel_ms01.getValueAt(i, 2));
							TB_DEName01.setText(		""+tableModel_ms01.getValueAt(i, 3));
							TB_DEName02.setText(		""+tableModel_ms01.getValueAt(i, 4));
							TB_DEName03.setText(		""+tableModel_ms01.getValueAt(i, 5));
							TB_Post.setText(			""+tableModel_ms01.getValueAt(i, 6));
							TB_Add01.setText(			""+tableModel_ms01.getValueAt(i, 7));
							TB_Add02.setText(			""+tableModel_ms01.getValueAt(i, 8));
							TB_Add03.setText(			""+tableModel_ms01.getValueAt(i, 9));
							TB_Tel.setText(				""+tableModel_ms01.getValueAt(i,10));
							TB_Fax.setText(				""+tableModel_ms01.getValueAt(i,11));
							TB_Mail.setText(			""+tableModel_ms01.getValueAt(i,12));
							
							DeliSearch_fm.setVisible(false);
						}
					}
					
					RenewFg = true;
				}
			}
		});
		
		//届先解放ボタン押下時の挙動
		DeliveryReleaseBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					
					TB_DECD.setText(			"");
					TB_DepartmentCd.setText(	"");
					TB_DEName01.setText(		"");
					TB_DEName02.setText(		"");
					TB_DEName03.setText(		"");
					TB_Post.setText(			"");
					TB_Add01.setText(			"");
					TB_Add02.setText(			"");
					TB_Add03.setText(			"");
					TB_Tel.setText(				"");
					TB_Fax.setText(				"");
					TB_Mail.setText(			"");
							
					RenewFg = true;
				}
			}
		});
		
		//届先検索ボタン押下時の挙動
		SearchBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					int RowCount = tableModel_ms01.getRowCount();
					
					for(int i=0;i<RowCount;i++) {
						tableModel_ms01.removeRow(0);
					}
					
					TB_SearchDECD.setText("");
					TB_SearchDepartmentCd.setText("");
					TB_SearchDEName.setText("");
					TB_SearchAdd.setText("");
					TB_SearchTel.setText("");
					
					TB_SearchPost.setText("");
					TB_SearchFax.setText("");
					TB_SearchMail.setText("");
					
					DeliSearch_fm.setVisible(true);
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
							tableModel_ms01.setValueAt(setBL, i, 0);
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
					
					String GetClGpCD		= B00111FramePartsControl.JTextFieldRt(TB_ClGpCD);
					String GetCL_DECD		= B00111FramePartsControl.JTextFieldRt(TB_CL_DECD);
					String GetDECD			= B00111FramePartsControl.JTextFieldRt(TB_DECD);
					String GetDepartmentCd	= B00111FramePartsControl.JTextFieldRt(TB_DepartmentCd);
					String GetDEName01		= B00111FramePartsControl.JTextFieldRt(TB_DEName01);
					String GetDEName02		= B00111FramePartsControl.JTextFieldRt(TB_DEName02);
					String GetDEName03		= B00111FramePartsControl.JTextFieldRt(TB_DEName03);
					String GetPost			= B00111FramePartsControl.JTextFieldRt(TB_Post);
					String GetAdd01			= B00111FramePartsControl.JTextFieldRt(TB_Add01);
					String GetAdd02			= B00111FramePartsControl.JTextFieldRt(TB_Add02);
					String GetAdd03			= B00111FramePartsControl.JTextFieldRt(TB_Add03);
					String GetTel			= B00111FramePartsControl.JTextFieldRt(TB_Tel);
					String GetFax			= B00111FramePartsControl.JTextFieldRt(TB_Fax);
					String GetMail			= B00111FramePartsControl.JTextFieldRt(TB_Mail);
					String GetSetName		= B00111FramePartsControl.JTextFieldRt(TB_SetName);
					String GetCom01			= B00111FramePartsControl.JTextFieldRt(TB_Com01);
					String GetCom02			= B00111FramePartsControl.JTextFieldRt(TB_Com02);
					String GetCom03			= B00111FramePartsControl.JTextFieldRt(TB_Com03);
					String GetCom04			= B00111FramePartsControl.JTextFieldRt(TB_Com04);
					String GetCom05			= B00111FramePartsControl.JTextFieldRt(TB_Com05);
					
					String GetDelFg = B00100DefaultVariable.DelList[1][TB_DelFg.getSelectedIndex()];
					String GetMstPriorityFirstFg = ""+TB_MstPriorityFirstFg.getSelectedIndex();
					
					if(!"".equals(GetCL_DECD)) {
						boolean DeliveryRenewFg = false;
						boolean KickFg = false;
						if(DeliveryRenewMode.isSelected()) {DeliveryRenewFg = true;}else {KickFg = true;}
						if(DeliveryRenewFg) {
							if("".equals(GetDEName01)) {
								JOptionPane.showMessageDialog(null, "届先名の設定は必須です");
							}else {
								boolean DeliveryCheckNG = true;
								if(!"".equals(GetDECD)) {
									//存在する届先コードの登録であれば問題にしない
									Object[][] DeliveryMstRt = DeliveryMstRt(
											GetDECD
											,""
											,""
											,""
											,""
											,""
											,""
											,""
											,true
											,false
											);
									if(0<DeliveryMstRt.length) {
										DeliveryCheckNG = false;
									}
								}
								
								//電話番号だけを元に同一届け先候補を取得
								if(DeliveryCheckNG) {
									Object[][] DeliveryMstRt = DeliveryMstRt(
											""
											,""
											,""
											,""
											,GetTel
											,""
											,""
											,""
											,true
											,false
											);
									if(0==DeliveryMstRt.length) {
										DeliveryCheckNG = false;
									}else{
										JOptionPane.showMessageDialog(null, "電話番号での重複届先候補があります。\n新規届先設定を拒否します。\n届先マスタ見直せやがれください");
									}
								}
								
								if(!DeliveryCheckNG) {
									//届先コード空白なら新規採番
									if("".equals(GetDECD)) {GetDECD = M00040DeliveryMstRt.DeliveryCdGet(1)[0];}
									//届先部署コード空白なら"0000"
									if("".equals(GetDepartmentCd)) {GetDepartmentCd = "0000";}
									
									DeliveryMstEntry(
											GetDECD
											,GetDepartmentCd
											,GetDEName01
											,GetDEName02
											,GetDEName03
											,GetPost
											,GetAdd01
											,GetAdd02
											,GetAdd03
											,GetTel
											,GetFax
											,GetMail
											);
									KickFg = true;
								}
							}
						}
						if(KickFg) {
							if(!"".equals(GetDECD) && !"".equals(GetDepartmentCd)) {
								if("".equals(GetSetName)) {GetSetName = GetDEName01;}
								DeliveryComversionMstEntry(
										GetClGpCD
										,GetCL_DECD
										,GetDECD
										,GetDepartmentCd
										,GetSetName
										,GetCom01
										,GetCom02
										,GetCom03
										,GetCom04
										,GetCom05
										,GetDelFg
										,GetMstPriorityFirstFg
										);
								SetX=main_fm.getX();
								SetY=main_fm.getY();
								
								DeliSearch_fm.setVisible(false);
								DeliSearch_fm.dispose();
								
								main_fm.setVisible(false);
								main_fm.dispose();
								WM00066DeliveryComversionMstRenewAndCreate.DeliveryComversionMstRenewAndCreate(0,0,GetClGpCD,GetCL_DECD);
							}else {
								JOptionPane.showMessageDialog(null, "届先コードの選択は必須です");
							}
						}
					}else {
						JOptionPane.showMessageDialog(null, "荷主届先コードは必須です");
					}
					
					
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
				}
			}
		});
		
		//EXITボタン押下時の挙動
		exit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();
				
				DeliSearch_fm.setVisible(false);
				DeliSearch_fm.dispose();
				
				main_fm.setVisible(false);
				main_fm.dispose();
				WM00065DeliveryComversionMstSerarch.DeliveryComversionMstSerarch(0, 0);
			}
		});
	}
	
	private static void DeliveryMstEntry(
			String GetDECD
			,String GetDepartmentCd
			,String GetDEName01
			,String GetDEName02
			,String GetDEName03
			,String GetPost
			,String GetAdd01
			,String GetAdd02
			,String GetAdd03
			,String GetTel
			,String GetFax
			,String GetMail
			) {
		
		GetPost= B00020ToolsTextControl.num_only_String(GetPost);
		GetTel= B00020ToolsTextControl.num_only_String(GetTel);
		GetFax= B00020ToolsTextControl.num_only_String(GetFax);
				
		String now_dtm = B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[1];
		
		String GetJis = "";
		String GetPrefecturesCd = "";
		if(!"".equals(GetPost)) {
			ArrayList<String> SearchPOST = new ArrayList<String>();
			ArrayList<String> SearchAdd = new ArrayList<String>();
			boolean AllSearch = false;
			boolean PostPerfectMatch = true;
			SearchPOST.add(GetPost);
			Object[][] PostRt = M10010PostMstRt.PostRt(
													SearchPOST,
													SearchAdd,
													AllSearch,
													PostPerfectMatch);
			if(0<PostRt.length) {
				GetJis = ""+PostRt[0][4];
			}
		}
		if("".equals(GetJis)) {
			String[] AddList = {GetAdd01+GetAdd02+GetAdd03};
			
			GetJis = ""+M10010PostMstRt.AddToMunicipality(AddList)[0][1];
		}
		if(2<GetJis.length()) {
			GetPrefecturesCd = GetJis.substring(0,2);
		}else {
			GetPrefecturesCd = "00";
		}
		
		String[][] SetString = {
				{"DECD"				,"1","1",GetDECD}			//届先コード
				,{"DepartmentCd"	,"1","1",GetDepartmentCd}	//部署CD
				,{"DEName01"		,"1","1",GetDEName01}		//届先名1
				,{"DEName02"		,"1","1",GetDEName02}		//届先名2
				,{"DEName03"		,"1","1",GetDEName03}		//届先名3
				,{"Post"			,"1","1",GetPost}			//届先郵便
				,{"Add01"			,"1","1",GetAdd01}			//届先住所1
				,{"Add02"			,"1","1",GetAdd02}			//届先住所2
				,{"Add03"			,"1","1",GetAdd03}			//届先住所3
				,{"Tel"				,"1","1",GetTel}			//届先電話
				,{"Fax"				,"1","1",GetFax}			//届先FAX
				,{"Mail"			,"1","1",GetMail}			//届先MAIL
				,{"Com01"			,"1","0",""}				//コメント1
				,{"Com02"			,"1","0",""}				//コメント2
				,{"Com03"			,"1","0",""}				//コメント3
				,{"PrefecturesCd"	,"1","1",GetPrefecturesCd}	//JIS県CD2桁
				,{"MunicipalityCd"	,"1","1",GetJis}			//JIS市区町村CD5桁
				,{"PTMSCD"			,"1","0",""}				//基幹システム発着地コード
				,{"EntryDate"		,"1","0",now_dtm}			//データ登録日時
				,{"UpdateDate"		,"1","1",now_dtm}			//データ更新日時
				,{"EntryUser"		,"1","0","(" + A00000Main.LoginUserId + ")" + A00000Main.LoginUserName}			//登録者コード
				,{"UpdateUser"		,"1","1","(" + A00000Main.LoginUserId + ")" + A00000Main.LoginUserName}			//更新者コード
				,{"FirstClient"		,"1","0",A00000Main.ClCd}			//登録した荷主CD
				,{"LastClient"		,"1","1",A00000Main.ClCd}			//更新した荷主CD
				,{"DelFg"			,"1","1","0"}						//削除区分
				};
		
		String tgt_table = "KM0040_DELIVERYMST";
		String[][] field_name = new String[SetString.length][3];
		String[][] entry_data = new String[1][SetString.length];
		String[] judg_field = new String[2];
		String[][] judg_data = new String[1][2];
		String TgtDB = "NYANKO";
		int non_msg_fg = 1;

		judg_field[0] = "DECD";
		judg_field[1] = "DepartmentCd";
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
	
	private static void DeliveryComversionMstEntry(
			String GetClGpCD
			,String GetCL_DECD
			,String GetDECD
			,String GetDepartmentCd
			,String GetSetName
			,String GetCom01
			,String GetCom02
			,String GetCom03
			,String GetCom04
			,String GetCom05
			,String GetDelFg
			,String GetMstPriorityFirstFg
			) {
		String now_dtm = B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[1];
		
		String[][] SetString = {
				{"ClGpCD"				,"1","1",GetClGpCD}			//荷主グループコード
				,{"CL_DECD"				,"1","1",GetCL_DECD}		//荷主届先コード
				,{"DECD"				,"1","1",GetDECD}			//届先コード
				,{"DepartmentCd"		,"1","1",GetDepartmentCd}	//届先部署CD
				,{"SetName"				,"1","1",GetSetName}		//送り状登録名
				,{"Com01"				,"1","1",GetCom01}			//コメント01
				,{"Com02"				,"1","1",GetCom02}			//コメント02
				,{"Com03"				,"1","1",GetCom03}			//コメント03
				,{"Com04"				,"1","1",GetCom04}			//コメント04
				,{"Com05"				,"1","1",GetCom05}			//コメント05
				,{"EntryDate"			,"1","0",now_dtm}			//データ登録日時
				,{"UpdateDate"			,"1","1",now_dtm}			//データ更新日時
				,{"EntryUser"			,"1","0","(" + A00000Main.LoginUserId + ")" + A00000Main.LoginUserName}		//登録者コード
				,{"UpdateUser"			,"1","1","(" + A00000Main.LoginUserId + ")" + A00000Main.LoginUserName}		//更新者コード
				,{"DelFg"				,"1","1",GetDelFg}		//削除区分
				,{"MstPriorityFirstFg"	,"1","1",GetMstPriorityFirstFg}		//届先マスタ優先フラグ
				};
		
		String tgt_table = "KM0041_DELIVERY_COMVERSIONMST";
		String[][] field_name = new String[SetString.length][3];
		String[][] entry_data = new String[1][SetString.length];
		String[] judg_field = new String[2];
		String[][] judg_data = new String[1][2];
		String TgtDB = "NYANKO";
		int non_msg_fg = 0;

		judg_field[0] = "ClGpCD";		//荷主グループコード
		judg_field[1] = "CL_DECD";		//荷主届先コード
		judg_data[0][0] = GetClGpCD;
		judg_data[0][1] = GetCL_DECD;
		
		for(int i=0;i<SetString.length;i++) {
			field_name[i][0] = SetString[i][0];
			field_name[i][1] = SetString[i][1];
			field_name[i][2] = SetString[i][2];
			entry_data[0][i] = SetString[i][3];
		}
		
		A00020InsertUdateSQL.RUN_SQLS_EU(tgt_table, field_name, entry_data, judg_field, judg_data, non_msg_fg,TgtDB);
	}
	
	private static Object[][] DeliveryMstRt(
			String GetSearchDECD
			,String GetSearchDepartmentCd
			,String GetSearchDEName
			,String GetSearchAdd
			,String GetSearchTel
			,String GetSearchPost
			,String GetSearchFax
			,String GetSearchMail
			,boolean SearchTelExactMatch
			,boolean AllSearch
			){
		
		GetSearchPost	= B00020ToolsTextControl.num_only_String(GetSearchPost);
		GetSearchTel	= B00020ToolsTextControl.num_only_String(GetSearchTel);
		GetSearchFax	= B00020ToolsTextControl.num_only_String(GetSearchFax);
		
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
		
		if(!"".equals(GetSearchDECD			)){SearchDECD.add(			GetSearchDECD);}
		if(!"".equals(GetSearchDepartmentCd	)){SearchDepartmentCd.add(	GetSearchDepartmentCd);}
		if(!"".equals(GetSearchDEName		)){SearchDEName.add(		GetSearchDEName);}
		if(!"".equals(GetSearchAdd			)){SearchAdd.add(			GetSearchAdd);}
		if(!"".equals(GetSearchTel			)){SearchTel.add(			GetSearchTel);}
		if(!"".equals(GetSearchPost			)){SearchPost.add(			GetSearchPost);}
		if(!"".equals(GetSearchFax			)){SearchFax.add(			GetSearchFax);}
		if(!"".equals(GetSearchMail			)){SearchMail.add(			GetSearchMail);}
		SearchDelFg.add("0");
		
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
		return DeliveryMstRt;
	}
}