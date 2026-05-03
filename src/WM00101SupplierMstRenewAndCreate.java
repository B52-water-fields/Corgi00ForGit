import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class WM00101SupplierMstRenewAndCreate{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	public static void SupplierMstRenewAndCreate(int x,int y,String TgtClCd,String TgtWhCd,String TgSPCd) {
		A00000Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		
		if(null==TgtClCd		) {TgtClCd= "";}
		if(null==TgtWhCd		) {TgtWhCd= "";}
		if(null==TgSPCd			) {TgSPCd= "";}
		
		final JFrame main_fm = B00110FrameParts.FrameCreate(x,y,800,800,"Corgi00仕入先マスタ登録・修正","");
		JLabel userinfo 	= B00110FrameParts.UserInfo();
		JButton exit_btn 	= B00110FrameParts.ExitBtn();
		JButton entry_btn 	= B00110FrameParts.EntryBtn();

		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		main_fm.add(entry_btn);
		
		JLabel LB_ClCd				= B00110FrameParts.JLabelSet(   0, 50,130,20,"荷主コード:"			,10,1);
		JLabel LB_WhCd				= B00110FrameParts.JLabelSet(   0, 75,130,20,"倉庫コード:"			,10,1);
		JLabel LB_SPCd				= B00110FrameParts.JLabelSet(   0,100,130,20,"仕入先コード:"		,10,1);
		JLabel LB_SPName01			= B00110FrameParts.JLabelSet(   0,125,130,20,"仕入先名1:"			,10,1);
		JLabel LB_SPName02			= B00110FrameParts.JLabelSet(   0,150,130,20,"仕入先名2:"			,10,1);
		JLabel LB_SPName03			= B00110FrameParts.JLabelSet(   0,175,130,20,"仕入先名3:"			,10,1);
		JLabel LB_SPPost			= B00110FrameParts.JLabelSet(   0,200,130,20,"仕入先郵便:"			,10,1);
		JLabel LB_SPAdd01			= B00110FrameParts.JLabelSet(   0,225,130,20,"仕入先住所1:"		,10,1);
		JLabel LB_SPAdd02			= B00110FrameParts.JLabelSet(   0,250,130,20,"仕入先住所2:"		,10,1);
		JLabel LB_SPAdd03			= B00110FrameParts.JLabelSet(   0,275,130,20,"仕入先住所3:"		,10,1);
		JLabel LB_SPTel				= B00110FrameParts.JLabelSet(   0,300,130,20,"仕入先電話:"			,10,1);
		JLabel LB_SPFax				= B00110FrameParts.JLabelSet(   0,325,130,20,"仕入先FAX:"			,10,1);
		JLabel LB_SPMail			= B00110FrameParts.JLabelSet(   0,350,130,20,"仕入先MAIL:"			,10,1);
		JLabel LB_Com01				= B00110FrameParts.JLabelSet(   0,375,130,20,"コメント1:"			,10,1);
		JLabel LB_Com02				= B00110FrameParts.JLabelSet(   0,400,130,20,"コメント2:"			,10,1);
		JLabel LB_Com03				= B00110FrameParts.JLabelSet(   0,425,130,20,"コメント3:"			,10,1);
		JLabel LB_PTMSCDBMN			= B00110FrameParts.JLabelSet(   0,450,130,20,"基幹Sys部門コード:"	,10,1);
		JLabel LB_PTMSCDNINUSHI		= B00110FrameParts.JLabelSet(   0,475,130,20,"基幹Sys荷主コード:"	,10,1);
		JLabel LB_PaySite			= B00110FrameParts.JLabelSet(   0,500,130,20,"支払いサイト:"		,10,1);
		JLabel LB_PayDate			= B00110FrameParts.JLabelSet(   0,525,130,20,"支払日:"				,10,1);
		JLabel LB_ShimeDate			= B00110FrameParts.JLabelSet(   0,550,130,20,"締め日:"				,10,1);
		JLabel LB_EntryDate			= B00110FrameParts.JLabelSet(   0,575,130,20,"登録日:"				,10,1);
		JLabel LB_UpdateDate		= B00110FrameParts.JLabelSet(   0,600,130,20,"更新日:"				,10,1);
		JLabel LB_EntryUser			= B00110FrameParts.JLabelSet( 330,575,130,20,"登録者:"				,10,1);
		JLabel LB_UpdateUser		= B00110FrameParts.JLabelSet( 330,600,130,20,"更新者:"				,10,1);
		JLabel LB_DECD				= B00110FrameParts.JLabelSet(   0,625,130,20,"納品先コード:"		,10,1);
		JLabel LB_DepartmentCd		= B00110FrameParts.JLabelSet(   0,650,130,20,"部署CD:"				,10,1);
		JLabel LB_DEName01			= B00110FrameParts.JLabelSet(   0,675,130,20,"納品先名1:"			,10,1);
		
		final JComboBox   TB_ClCd				= B00110FrameParts.JComboBoxSet( 				130, 50,200,20,B00100DefaultVariable.ClList[0],11);	//荷主コード
		final JComboBox   TB_WhCd				= B00110FrameParts.JComboBoxSet( 				130, 75,200,20,B00100DefaultVariable.WhList[0],11);	//倉庫コード
		final JTextField  TB_SPCd				= B00110FrameParts.JTextFieldSet( 			130,100,200,20,"",11,0);	//仕入先コード
		final JTextField  TB_SPName01			= B00110FrameParts.JTextFieldSet( 			130,125,200,20,"",11,0);	//仕入先名1
		final JTextField  TB_SPName02			= B00110FrameParts.JTextFieldSet( 			130,150,200,20,"",11,0);	//仕入先名2
		final JTextField  TB_SPName03			= B00110FrameParts.JTextFieldSet( 			130,175,200,20,"",11,0);	//仕入先名3
		final JTextField  TB_SPPost				= B00110FrameParts.JTextFieldSet( 			130,200,100,20,"",11,0);	//仕入先郵便
		final JTextField  TB_SPAdd01			= B00110FrameParts.JTextFieldSet( 			130,225,200,20,"",11,0);	//仕入先住所1
		final JTextField  TB_SPAdd02			= B00110FrameParts.JTextFieldSet( 			130,250,200,20,"",11,0);	//仕入先住所2
		final JTextField  TB_SPAdd03			= B00110FrameParts.JTextFieldSet( 			130,275,200,20,"",11,0);	//仕入先住所3
		final JTextField  TB_SPTel				= B00110FrameParts.JTextFieldSet( 			130,300,150,20,"",11,0);	//仕入先電話
		final JTextField  TB_SPFax				= B00110FrameParts.JTextFieldSet( 			130,325,150,20,"",11,0);	//仕入先FAX
		final JTextField  TB_SPMail				= B00110FrameParts.JTextFieldSet( 			130,350,200,20,"",11,0);	//仕入先MAIL
		final JTextField  TB_Com01				= B00110FrameParts.JTextFieldSet( 			130,375,200,20,"",11,0);	//コメント1
		final JTextField  TB_Com02				= B00110FrameParts.JTextFieldSet( 			130,400,200,20,"",11,0);	//コメント2
		final JTextField  TB_Com03				= B00110FrameParts.JTextFieldSet( 			130,425,200,20,"",11,0);	//コメント3
		final JTextField  TB_PTMSCDBMN			= B00110FrameParts.JTextFieldSet( 			130,450,100,20,"",11,0);	//基幹Sysコード（部門）
		final JTextField  TB_PTMSCDNINUSHI		= B00110FrameParts.JTextFieldSet( 			130,475,100,20,"",11,0);	//基幹Sysコード（荷主）
		final JFormattedTextField TB_PaySite	= B00110FrameParts.JFormattedTextFieldSet(	130,500, 60,20,"1",11,1,"####");								//支払いサイト（月数）
		final JComboBox TB_PayDate				= B00110FrameParts.JComboBoxSet(				130,525, 60,20,B00100DefaultVariable.ShimeDateList[0],11);	//支払日（末日＝99）
		final JComboBox TB_ShimeDate			= B00110FrameParts.JComboBoxSet(				130,550, 60,20,B00100DefaultVariable.ShimeDateList[0],11);	//締め日（末日＝99）
		final JTextField  TB_EntryDate			= B00110FrameParts.JTextFieldSet( 			130,575,200,20,"",11,0);	//登録日
		final JTextField  TB_UpdateDate			= B00110FrameParts.JTextFieldSet( 			130,600,200,20,"",11,0);	//更新日
		final JTextField  TB_EntryUser			= B00110FrameParts.JTextFieldSet(				460,575,200,20,"",11,0);	//登録者
		final JTextField  TB_UpdateUser			= B00110FrameParts.JTextFieldSet(				460,600,200,20,"",11,0);	//更新者
		final JTextField  TB_DECD				= B00110FrameParts.JTextFieldSet( 			130,625,100,20,"",11,0);	//納品先コード
		final JTextField  TB_DepartmentCd		= B00110FrameParts.JTextFieldSet( 			130,650,100,20,"",11,0);	//部署CD
		final JTextField  TB_DEName01			= B00110FrameParts.JTextFieldSet( 			130,675,200,20,"",11,0);	//納品先名1
		
		TB_ClCd.setEnabled(false);
		TB_WhCd.setEnabled(false);
		TB_EntryDate.setEditable(false);
		TB_UpdateDate.setEditable(false);
		TB_EntryUser.setEditable(false);
		TB_UpdateUser.setEditable(false);
		TB_DECD.setEditable(false);
		TB_DepartmentCd.setEditable(false);
		TB_DEName01.setEditable(false);
		
		TB_PayDate.setSelectedIndex(	B00100DefaultVariable.ShimeDateList[0].length-1);
		TB_ShimeDate.setSelectedIndex(	B00100DefaultVariable.ShimeDateList[0].length-1);
		
		if("".equals(TgtClCd)) {TgtClCd= A00000Main.ClCd;}
		if("".equals(TgtWhCd)) {TgtWhCd= A00000Main.ClWh;}
		if(!"".equals(TgSPCd)) {
			Object[][] SupplierRt = SupplierRt(TgtClCd,TgtWhCd,TgSPCd);
			if(1==SupplierRt.length) {
				TB_SPName01.setText(		(String)SupplierRt[0][M00100SupplierRt.ColSPName01]);			//仕入先名1
				TB_SPName02.setText(		(String)SupplierRt[0][M00100SupplierRt.ColSPName02]);			//仕入先名2
				TB_SPName03.setText(		(String)SupplierRt[0][M00100SupplierRt.ColSPName03]);			//仕入先名3
				TB_SPPost.setText(			(String)SupplierRt[0][M00100SupplierRt.ColSPPost]);			//仕入先郵便
				TB_SPAdd01.setText(			(String)SupplierRt[0][M00100SupplierRt.ColSPAdd01]);			//仕入先住所1
				TB_SPAdd02.setText(			(String)SupplierRt[0][M00100SupplierRt.ColSPAdd02]);			//仕入先住所2
				TB_SPAdd03.setText(			(String)SupplierRt[0][M00100SupplierRt.ColSPAdd03]);			//仕入先住所3
				TB_SPTel.setText(			(String)SupplierRt[0][M00100SupplierRt.ColSPTel]);				//仕入先電話
				TB_SPFax.setText(			(String)SupplierRt[0][M00100SupplierRt.ColSPFax]);				//仕入先FAX
				TB_SPMail.setText(			(String)SupplierRt[0][M00100SupplierRt.ColSPMail]);			//仕入先MAIL
				TB_Com01.setText(			(String)SupplierRt[0][M00100SupplierRt.ColCom01]);				//コメント1
				TB_Com02.setText(			(String)SupplierRt[0][M00100SupplierRt.ColCom02]);				//コメント2
				TB_Com03.setText(			(String)SupplierRt[0][M00100SupplierRt.ColCom03]);				//コメント3
				TB_PTMSCDBMN.setText(		(String)SupplierRt[0][M00100SupplierRt.ColPTMSCDBMN]);		//基幹Sysコード（部門）
				TB_PTMSCDNINUSHI.setText(	(String)SupplierRt[0][M00100SupplierRt.ColPTMSCDNINUSHI]);	//基幹Sysコード（荷主）
				TB_PaySite.setText(			""+(int)SupplierRt[0][M00100SupplierRt.ColPaySite]);			//支払いサイト（月数）
				
				for(int i=0;i<B00100DefaultVariable.ShimeDateList[1].length;i++) {
					if((""+(int)SupplierRt[0][M00100SupplierRt.ColPayDate]).equals(""+B00100DefaultVariable.ShimeDateList[1][i])) {
						TB_PayDate.setSelectedIndex(i);
					}
				}
				for(int i=0;i<B00100DefaultVariable.ShimeDateList[1].length;i++) {
					if((""+(int)SupplierRt[0][M00100SupplierRt.ColShimeDate]).equals(""+B00100DefaultVariable.ShimeDateList[1][i])) {
						TB_ShimeDate.setSelectedIndex(i);
					}
				}
				TB_EntryDate.setText(		(String)SupplierRt[0][M00100SupplierRt.ColEntryDate]);	//登録日
				TB_UpdateDate.setText(		(String)SupplierRt[0][M00100SupplierRt.ColUpdateDate]);	//更新日
				TB_EntryUser.setText(		(String)SupplierRt[0][M00100SupplierRt.ColEntryUser]);	//登録者
				TB_UpdateUser.setText(		(String)SupplierRt[0][M00100SupplierRt.ColUpdateUser]);	//更新者
				TB_DECD.setText(			(String)SupplierRt[0][M00100SupplierRt.ColDECD]);			//納品先コード
				TB_DepartmentCd.setText(	(String)SupplierRt[0][M00100SupplierRt.ColDepartmentCd]);	//部署CD
				TB_DEName01.setText(		(String)SupplierRt[0][M00100SupplierRt.ColDEName01]);		//納品先名1
			}
		}
		for(int i=0;i<B00100DefaultVariable.ClList[1].length;i++) {
			if(TgtClCd.equals(B00100DefaultVariable.ClList[1][i])) {
				TB_ClCd.setSelectedIndex(i);
			}
		}
		for(int i=0;i<B00100DefaultVariable.WhList[1].length;i++) {
			if(TgtWhCd.equals(B00100DefaultVariable.WhList[1][i])) {
				TB_WhCd.setSelectedIndex(i);
			}
		}
		
		TB_SPCd.setText(TgSPCd);
		
		main_fm.add(LB_ClCd);
		main_fm.add(LB_WhCd);
		main_fm.add(LB_SPCd);
		main_fm.add(LB_SPName01);
		main_fm.add(LB_SPName02);
		main_fm.add(LB_SPName03);
		main_fm.add(LB_SPPost);
		main_fm.add(LB_SPAdd01);
		main_fm.add(LB_SPAdd02);
		main_fm.add(LB_SPAdd03);
		main_fm.add(LB_SPTel);
		main_fm.add(LB_SPFax);
		main_fm.add(LB_SPMail);
		main_fm.add(LB_Com01);
		main_fm.add(LB_Com02);
		main_fm.add(LB_Com03);
		main_fm.add(LB_PTMSCDBMN);
		main_fm.add(LB_PTMSCDNINUSHI);
		main_fm.add(LB_PaySite);
		main_fm.add(LB_PayDate);
		main_fm.add(LB_ShimeDate);
		main_fm.add(LB_EntryDate);
		main_fm.add(LB_UpdateDate);
		main_fm.add(LB_EntryUser);
		main_fm.add(LB_UpdateUser);
		main_fm.add(LB_DECD);
		main_fm.add(LB_DepartmentCd);
		main_fm.add(LB_DEName01);
		
		main_fm.add(TB_ClCd);
		main_fm.add(TB_WhCd);
		main_fm.add(TB_SPCd);
		main_fm.add(TB_SPName01);
		main_fm.add(TB_SPName02);
		main_fm.add(TB_SPName03);
		main_fm.add(TB_SPPost);
		main_fm.add(TB_SPAdd01);
		main_fm.add(TB_SPAdd02);
		main_fm.add(TB_SPAdd03);
		main_fm.add(TB_SPTel);
		main_fm.add(TB_SPFax);
		main_fm.add(TB_SPMail);
		main_fm.add(TB_Com01);
		main_fm.add(TB_Com02);
		main_fm.add(TB_Com03);
		main_fm.add(TB_PTMSCDBMN);
		main_fm.add(TB_PTMSCDNINUSHI);
		main_fm.add(TB_PaySite);
		main_fm.add(TB_PayDate);
		main_fm.add(TB_ShimeDate);
		main_fm.add(TB_EntryDate);
		main_fm.add(TB_UpdateDate);
		main_fm.add(TB_EntryUser);
		main_fm.add(TB_UpdateUser);
		main_fm.add(TB_DECD);
		main_fm.add(TB_DepartmentCd);
		main_fm.add(TB_DEName01);
		
		main_fm.setVisible(true);
		
		//届先検索ボタン
		JButton GetSpCdBtn = B00110FrameParts.BtnSet(	350,100,100,20,"仕入先Cd採番",10);
		
		//届先検索ボタン
		JButton DeliverySerachBtn = B00110FrameParts.BtnSet(	480,625,100,20,"届先検索",11);
		main_fm.add(DeliverySerachBtn);
		
		final JFrame DeliverySerach_fm 		= B00110FrameParts.FrameCreate(x+20,y+20,800,800,"Corgi00仕入先マスタ登録・修正(届先検索)","");
		JLabel DeliverySerach_userinfo 		= B00110FrameParts.UserInfo();
		JButton DeliverySerach_exit_btn 	= B00110FrameParts.ExitBtn();
		JButton DeliverySerach_entry_btn 	= B00110FrameParts.EntryBtn();
		
		DeliverySerach_fm.add(DeliverySerach_userinfo);
		DeliverySerach_fm.add(DeliverySerach_exit_btn);
		DeliverySerach_fm.add(DeliverySerach_entry_btn);
		
		//検索条件パネル
		JPanel PN_Search = B00110FrameParts.JPanelSet(10,40,770,160,"White");
		JLabel PN_SearchLabel = B00110FrameParts.JLabelSet(10,0,150,20,"検索条件",11,0);
		PN_Search.add(PN_SearchLabel);
		
		JLabel LB_SearchDECD			= B00110FrameParts.JLabelSet(  0, 25,100,20,"届先CD:"			,11,1);
		JLabel LB_SearchDepartmentCd	= B00110FrameParts.JLabelSet(  0, 50,100,20,"届先部署CD:"		,11,1);
		JLabel LB_SearchDEName			= B00110FrameParts.JLabelSet(  0, 75,100,20,"届先名:"			,11,1);
		JLabel LB_SearchPost			= B00110FrameParts.JLabelSet(  0,100,100,20,"届先郵便:"		,11,1);
		JLabel LB_SearchAdd				= B00110FrameParts.JLabelSet(  0,125,100,20,"届先住所:"		,11,1);
		JLabel LB_SearchTel				= B00110FrameParts.JLabelSet(250, 25,100,20,"届先TEL:"			,11,1);
		JLabel LB_SearchFax				= B00110FrameParts.JLabelSet(250, 50,100,20,"届先FAX:"			,11,1);
		JLabel LB_SearchMail			= B00110FrameParts.JLabelSet(250, 75,100,20,"届先MAIL:"		,11,1);
		JLabel LB_SearchCom				= B00110FrameParts.JLabelSet(250,100,100,20,"届先コメント:"	,11,1);
		
		final JTextField  TB_SearchDECD				= B00110FrameParts.JTextFieldSet(100, 25,100,20,"",11,0);			//届先CD
		final JTextField  TB_SearchDepartmentCd		= B00110FrameParts.JTextFieldSet(100, 50,100,20,"",11,0);			//届先部署CD
		final JTextField  TB_SearchDEName			= B00110FrameParts.JTextFieldSet(100, 75,100,20,"",11,0);			//届先名
		final JTextField  TB_SearchPost				= B00110FrameParts.JTextFieldSet(100,100,100,20,"",11,0);			//届先郵便
		final JTextField  TB_SearchAdd				= B00110FrameParts.JTextFieldSet(100,125,100,20,"",11,0);			//届先住所
		final JTextField  TB_SearchTel				= B00110FrameParts.JTextFieldSet(350, 25,100,20,"",11,0);			//届先TEL
		final JTextField  TB_SearchFax				= B00110FrameParts.JTextFieldSet(350, 50,100,20,"",11,0);			//届先FAX
		final JTextField  TB_SearchMail				= B00110FrameParts.JTextFieldSet(350, 75,100,20,"",11,0);			//届先MAIL
		final JTextField  TB_SearchCom				= B00110FrameParts.JTextFieldSet(350,100,100,20,"",11,0);			//届先コメント
		
		JLabel LB2_SearchDECD			= B00110FrameParts.JLabelSet(200, 25, 50,20,"と一致"		,11,0);
		JLabel LB2_SearchDepartmentCd	= B00110FrameParts.JLabelSet(200, 50, 50,20,"と一致"		,11,0);
		JLabel LB2_SearchDEName			= B00110FrameParts.JLabelSet(200, 75, 50,20,"を含む"		,11,0);
		JLabel LB2_SearchPost			= B00110FrameParts.JLabelSet(200,100, 50,20,"で始まる"		,11,0);
		JLabel LB2_SearchAdd			= B00110FrameParts.JLabelSet(200,125, 50,20,"を含む"		,11,0);
		JLabel LB2_SearchTel			= B00110FrameParts.JLabelSet(450, 25, 50,20,"を含む"		,11,0);
		JLabel LB2_SearchFax			= B00110FrameParts.JLabelSet(450, 50, 50,20,"を含む"		,11,0);
		JLabel LB2_SearchMail			= B00110FrameParts.JLabelSet(450, 75, 50,20,"を含む"		,11,0);
		JLabel LB2_SearchCom			= B00110FrameParts.JLabelSet(450,100, 50,20,"を含む"		,11,0);
		
		PN_Search.add(LB_SearchDECD);
		PN_Search.add(LB_SearchDepartmentCd);
		PN_Search.add(LB_SearchDEName);
		PN_Search.add(LB_SearchPost);
		PN_Search.add(LB_SearchAdd);
		PN_Search.add(LB_SearchTel);
		PN_Search.add(LB_SearchFax);
		PN_Search.add(LB_SearchMail);
		PN_Search.add(LB_SearchCom);
		
		PN_Search.add(TB_SearchDECD);
		PN_Search.add(TB_SearchDepartmentCd);
		PN_Search.add(TB_SearchDEName);
		PN_Search.add(TB_SearchPost);
		PN_Search.add(TB_SearchAdd);
		PN_Search.add(TB_SearchTel);
		PN_Search.add(TB_SearchFax);
		PN_Search.add(TB_SearchMail);
		PN_Search.add(TB_SearchCom);
		
		PN_Search.add(LB2_SearchDECD);
		PN_Search.add(LB2_SearchDepartmentCd);
		PN_Search.add(LB2_SearchDEName);
		PN_Search.add(LB2_SearchPost);
		PN_Search.add(LB2_SearchAdd);
		PN_Search.add(LB2_SearchTel);
		PN_Search.add(LB2_SearchFax);
		PN_Search.add(LB2_SearchMail);
		PN_Search.add(LB2_SearchCom);
		
		DeliverySerach_fm.add(PN_Search);
		
		//検索ボタン
		JButton SearchBtn = B00110FrameParts.BtnSet(350,125,100,20,"検索",11);
		PN_Search.add(SearchBtn);
		
		Object[][] RtSettingDeliveryMstRt = M00040DeliveryMstRt.RtSettingDeliveryMstRt();
		
		String[] columnNames01 = new String[RtSettingDeliveryMstRt.length+1];
		
		columnNames01[0] = "Fg";
		for(int i=0;i<RtSettingDeliveryMstRt.length;i++) {
			columnNames01[1+i] = ""+RtSettingDeliveryMstRt[i][3];
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
		
		for(int i=0;i<RtSettingDeliveryMstRt.length;i++) {
			if("int".equals((String)RtSettingDeliveryMstRt[i][2])||"float".equals((String)RtSettingDeliveryMstRt[i][2])) {
				column = columnModel01.getColumn(1+i);	column.setPreferredWidth( 90*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.rightCellRenderer());
			}else {
				column = columnModel01.getColumn(1+i);	column.setPreferredWidth( 90*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());
			}
		}
		
		//スクロール用設定
		JScrollPane scpn01 = B00110FrameParts.JScrollPaneSet(10,210,770,400,tb01);
		DeliverySerach_fm.add(scpn01);
		
		String GetSpCd = TB_SPCd.getText();
		if("".equals(GetSpCd)) {
			main_fm.add(GetSpCdBtn);
			TB_SPCd.setEnabled(true);
			TB_SPCd.requestFocusInWindow();
		}else {
			TB_SPCd.setEnabled(false);
			TB_SPName01.requestFocusInWindow();
		}
		RenewFg = true;
		
		//届先検索検索ボタン押下時の挙動
		SearchBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					
					int RowCount = tableModel_ms01.getRowCount();
					for(int i=0;i<RowCount;i++) {
						tableModel_ms01.removeRow(0);
					}
					
					String GetSearchDECD			= TB_SearchDECD.getText();
					String GetSearchDepartmentCd	= TB_SearchDepartmentCd.getText();
					String GetSearchDEName			= TB_SearchDEName.getText();
					String GetSearchPost			= TB_SearchPost.getText();
					String GetSearchAdd				= TB_SearchAdd.getText();
					String GetSearchTel				= TB_SearchTel.getText();
					String GetSearchFax				= TB_SearchFax.getText();
					String GetSearchMail			= TB_SearchMail.getText();
					String GetSearchCom				= TB_SearchCom.getText();
					
					if(null==GetSearchDECD			) {GetSearchDECD			= "";}
					if(null==GetSearchDepartmentCd	) {GetSearchDepartmentCd	= "";}
					if(null==GetSearchDEName		) {GetSearchDEName			= "";}
					if(null==GetSearchPost			) {GetSearchPost			= "";}
					if(null==GetSearchAdd			) {GetSearchAdd				= "";}
					if(null==GetSearchTel			) {GetSearchTel				= "";}
					if(null==GetSearchFax			) {GetSearchFax				= "";}
					if(null==GetSearchMail			) {GetSearchMail			= "";}
					if(null==GetSearchCom			) {GetSearchCom				= "";}
					
					GetSearchDECD			= B00020ToolsTextControl.Trim(GetSearchDECD);
					GetSearchDepartmentCd	= B00020ToolsTextControl.Trim(GetSearchDepartmentCd);
					GetSearchDEName			= B00020ToolsTextControl.Trim(GetSearchDEName);
					GetSearchPost			= B00020ToolsTextControl.Trim(GetSearchPost);
					GetSearchAdd			= B00020ToolsTextControl.Trim(GetSearchAdd);
					GetSearchTel			= B00020ToolsTextControl.Trim(GetSearchTel);
					GetSearchFax			= B00020ToolsTextControl.Trim(GetSearchFax);
					GetSearchMail			= B00020ToolsTextControl.Trim(GetSearchMail);
					GetSearchCom			= B00020ToolsTextControl.Trim(GetSearchCom);
					
					GetSearchPost			= B00020ToolsTextControl.num_only_String(GetSearchPost);
					GetSearchTel			= B00020ToolsTextControl.num_only_String(GetSearchTel);
					GetSearchFax			= B00020ToolsTextControl.num_only_String(GetSearchFax);
					
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
					boolean SearchTelExactMatch = false;
					boolean SearcNotJis = true;
					boolean AllSearch = true;
					
					SearchDelFg.add("0");
					if(!"".equals(GetSearchDECD)){SearchDECD.add(GetSearchDECD);}
					if(!"".equals(GetSearchDepartmentCd)){SearchDepartmentCd.add(GetSearchDepartmentCd);}
					if(!"".equals(GetSearchDEName)){SearchDEName.add(GetSearchDEName);}
					if(!"".equals(GetSearchPost)){SearchPost.add(GetSearchPost);}
					if(!"".equals(GetSearchAdd)){SearchAdd.add(GetSearchAdd);}
					if(!"".equals(GetSearchTel)){SearchTel.add(GetSearchTel);}
					if(!"".equals(GetSearchFax)){SearchFax.add(GetSearchFax);}
					if(!"".equals(GetSearchMail)){SearchMail.add(GetSearchMail);}
					if(!"".equals(GetSearchCom)){SearchCom.add(GetSearchCom);}
					
					Object[][] DeliveryMstRt = M00040DeliveryMstRt.DeliveryMstRt(
							SearchDECD,				//検索条件届先CD
							SearchDepartmentCd,		//検索条件届先部署CD
							SearchDEName,			//検索条件届先名
							SearchPost,				//検索条件届先郵便
							SearchAdd,				//検索条件届先住所
							SearchTel,				//検索条件届先TEL
							SearchFax,				//検索条件届先FAX
							SearchMail,				//検索条件届先MAIL
							SearchCom,				//検索条件届先コメント
							SearchPrefecturesCd,	//検索条件届先県CD
							SearchMunicipalityCd,	//検索条件届先市区町村CD
							SearchDelFg,			//検索条件削除区分
							SearcNotJis,
							SearchTelExactMatch,
							AllSearch
							);
					
					
					for(int i=0;i<DeliveryMstRt.length;i++) {
						Object[] SetOb= new Object[DeliveryMstRt[i].length+1];
						SetOb[0] = false;
						for(int i01=0;i01<DeliveryMstRt[i].length;i01++) {
							SetOb[i01+1] = DeliveryMstRt[i][i01];
						}
						tableModel_ms01.addRow(SetOb);
					}
					if(0<DeliveryMstRt.length) {
						B10010TableControl.AddSortON(tb01,tableModel_ms01);
					}else {
						B10010TableControl.AddSortOFF(tb01,tableModel_ms01);
					}
					RenewFg = true;
				}
			}
		});
		
		//届先検索登録ボタン押下時の挙動
		DeliverySerach_entry_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					TB_DECD.setText("");
					TB_DepartmentCd.setText("");
					TB_DEName01.setText("");
					
					int RowCount = tableModel_ms01.getRowCount();
					for(int i=0;i<RowCount;i++) {
						if((boolean)tableModel_ms01.getValueAt(i, 0)) {
							TB_DECD.setText(""+tableModel_ms01.getValueAt(i, M00040DeliveryMstRt.ColDECD+1));
							TB_DepartmentCd.setText(""+tableModel_ms01.getValueAt(i, M00040DeliveryMstRt.ColDepartmentCd+1));
							TB_DEName01.setText(""+tableModel_ms01.getValueAt(i, M00040DeliveryMstRt.ColDEName01+1));
						}
					}
					
					DeliverySerach_fm.setVisible(false);
					DeliverySerach_fm.dispose();
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
		
		//EXITボタン押下時の挙動
		DeliverySerach_exit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				DeliverySerach_fm.setVisible(false);
				DeliverySerach_fm.dispose();
			}
		});
		
		//登録ボタン押下時の挙動
		entry_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					
					String GetClCd			= B00100DefaultVariable.ClList[1][TB_ClCd.getSelectedIndex()];	//荷主コード
					String GetWhCd			= B00100DefaultVariable.WhList[1][TB_WhCd.getSelectedIndex()];	//倉庫コード
					String GetSPCd			= TB_SPCd.getText();			//仕入先コード
					String GetSPName01		= TB_SPName01.getText();		//仕入先名1
					String GetSPName02		= TB_SPName02.getText();		//仕入先名2
					String GetSPName03		= TB_SPName03.getText();		//仕入先名3
					String GetSPPost		= TB_SPPost.getText();			//仕入先郵便
					String GetSPAdd01		= TB_SPAdd01.getText();			//仕入先住所1
					String GetSPAdd02		= TB_SPAdd02.getText();			//仕入先住所2
					String GetSPAdd03		= TB_SPAdd03.getText();			//仕入先住所3
					String GetSPTel			= TB_SPTel.getText();			//仕入先電話
					String GetSPFax			= TB_SPFax.getText();			//仕入先FAX
					String GetSPMail		= TB_SPMail.getText();			//仕入先MAIL
					String GetCom01			= TB_Com01.getText();			//コメント1
					String GetCom02			= TB_Com02.getText();			//コメント2
					String GetCom03			= TB_Com03.getText();			//コメント3
					String GetPTMSCDBMN		= TB_PTMSCDBMN.getText();		//基幹Sysコード（部門）
					String GetPTMSCDNINUSHI	= TB_PTMSCDNINUSHI.getText();	//基幹Sysコード（荷主）
					String GetPaySite		= TB_PaySite.getText();			//支払いサイト（月数）
					String GetPayDate		= B00100DefaultVariable.ShimeDateList[1][TB_PayDate.getSelectedIndex()];		//支払日（末日＝99）
					String GetShimeDate		= B00100DefaultVariable.ShimeDateList[1][TB_ShimeDate.getSelectedIndex()];	//締め日（末日＝99）
					String GetDECD			= TB_DECD.getText();			//納品先コード
					String GetDepartmentCd	= TB_DepartmentCd.getText();	//部署CD
					

					
					ArrayList<String> ErrMsg = ErrCheck(
							GetClCd,
							GetWhCd,
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
							GetDepartmentCd
							);
					
					if(null==ErrMsg||0==ErrMsg.size()) {
						DataEntry(
								GetClCd,
								GetWhCd,
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
								GetDepartmentCd
								);
						
						SetX=main_fm.getX();
						SetY=main_fm.getY();
						
						DeliverySerach_fm.setVisible(false);
						DeliverySerach_fm.dispose();

						main_fm.setVisible(false);
						main_fm.dispose();
						SupplierMstRenewAndCreate(0,0,GetClCd,GetWhCd,GetSPCd);
					}else {
						ErrView(ErrMsg);
					}
					RenewFg = true;
				}
			}
		});
		
		//届先検索ボタン押下時の挙動
		DeliverySerachBtn.addActionListener(new AbstractAction(){
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
					TB_SearchPost.setText("");
					TB_SearchAdd.setText("");
					TB_SearchTel.setText("");
					TB_SearchFax.setText("");
					TB_SearchMail.setText("");
					TB_SearchCom.setText("");
					
					DeliverySerach_fm.setVisible(true);
					RenewFg = true;
				}
			}
		});
		
		
		//郵便番号フォーカス消失時の挙動
		TB_SPPost.addFocusListener(new FocusAdapter(){
			@Override
			public void focusLost(FocusEvent e){
				if(RenewFg) {
					RenewFg = false;
					String GetPost = TB_SPPost.getText();	if(null==GetPost) {GetPost="";}
					GetPost = B00020ToolsTextControl.Trim(B00020ToolsTextControl.num_only_String(GetPost));
					TB_SPPost.setText(GetPost);
					
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
						String GetAdd01 = TB_SPAdd01.getText();	if(null==GetAdd01) {GetAdd01="";}
						String GetAdd02 = TB_SPAdd02.getText();	if(null==GetAdd02) {GetAdd02="";}
						String GetAdd03 = TB_SPAdd03.getText();	if(null==GetAdd03) {GetAdd03="";}
						
						if("".equals(GetAdd01)&&"".equals(GetAdd02)&&"".equals(GetAdd03)) {
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
							TB_SPAdd01.setText("");
							TB_SPAdd02.setText("");
							TB_SPAdd03.setText("");
							
							TB_SPAdd01.setText(""+PostRt[0][M10010PostMstRt.ColPREFECTURES]+PostRt[0][M10010PostMstRt.ColMUNICI01]);
							TB_SPAdd02.setText(""+PostRt[0][M10010PostMstRt.ColMUNICI02]);
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
				
				DeliverySerach_fm.setVisible(false);
				DeliverySerach_fm.dispose();

				main_fm.setVisible(false);
				main_fm.dispose();
				WM00100SupplierMstSearch.SupplierMstSearch(0, 0);
			}
		});
	}
	
	private static String[] TxtTrim(
			String GetClCd,
			String GetWhCd,
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
			String GetDepartmentCd
			) {
		String[] rt = new String[23];
		
		if(null==GetClCd			){GetClCd				= "";}
		if(null==GetWhCd			){GetWhCd				= "";}
		if(null==GetSPCd			){GetSPCd				= "";}
		if(null==GetSPName01		){GetSPName01			= "";}
		if(null==GetSPName02		){GetSPName02			= "";}
		if(null==GetSPName03		){GetSPName03			= "";}
		if(null==GetSPPost			){GetSPPost				= "";}
		if(null==GetSPAdd01			){GetSPAdd01			= "";}
		if(null==GetSPAdd02			){GetSPAdd02			= "";}
		if(null==GetSPAdd03			){GetSPAdd03			= "";}
		if(null==GetSPTel			){GetSPTel				= "";}
		if(null==GetSPFax			){GetSPFax				= "";}
		if(null==GetSPMail			){GetSPMail				= "";}
		if(null==GetCom01			){GetCom01				= "";}
		if(null==GetCom02			){GetCom02				= "";}
		if(null==GetCom03			){GetCom03				= "";}
		if(null==GetPTMSCDBMN		){GetPTMSCDBMN			= "";}
		if(null==GetPTMSCDNINUSHI	){GetPTMSCDNINUSHI		= "";}
		if(null==GetPaySite			){GetPaySite			= "";}
		if(null==GetPayDate			){GetPayDate			= "";}
		if(null==GetShimeDate		){GetShimeDate			= "";}
		if(null==GetDECD			){GetDECD				= "";}
		if(null==GetDepartmentCd	){GetDepartmentCd		= "";}
		
		GetClCd				= B00020ToolsTextControl.Trim(GetClCd);
		GetWhCd				= B00020ToolsTextControl.Trim(GetWhCd);
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
		
		GetPaySite			= B00020ToolsTextControl.num_only_String02(GetPaySite);
		GetPayDate			= B00020ToolsTextControl.num_only_String02(GetPayDate);
		GetShimeDate		= B00020ToolsTextControl.num_only_String02(GetShimeDate);
		
		GetSPPost			= B00020ToolsTextControl.num_only_String(GetSPPost);
		GetSPTel			= B00020ToolsTextControl.num_only_String(GetSPTel);
		GetSPFax			= B00020ToolsTextControl.num_only_String(GetSPFax);
		
		if("".equals(GetPaySite)	){GetPaySite			= "1";}
		if("".equals(GetPayDate)	){GetPayDate			= "99";}
		if("".equals(GetShimeDate)	){GetShimeDate			= "99";}
		
		GetPaySite			= ""+(int)(Float.parseFloat(GetPaySite));
		GetPayDate			= ""+(int)(Float.parseFloat(GetPayDate));
		GetShimeDate		= ""+(int)(Float.parseFloat(GetShimeDate));
		
		rt[ 0]= GetClCd;
		rt[ 1]= GetWhCd;
		rt[ 2]= GetSPCd;
		rt[ 3]= GetSPName01;
		rt[ 4]= GetSPName02;
		rt[ 5]= GetSPName03;
		rt[ 6]= GetSPPost;
		rt[ 7]= GetSPAdd01;
		rt[ 8]= GetSPAdd02;
		rt[ 9]= GetSPAdd03;
		rt[10]= GetSPTel;
		rt[11]= GetSPFax;
		rt[12]= GetSPMail;
		rt[13]= GetCom01;
		rt[14]= GetCom02;
		rt[15]= GetCom03;
		rt[16]= GetPTMSCDBMN;
		rt[17]= GetPTMSCDNINUSHI;
		rt[18]= GetPaySite;
		rt[19]= GetPayDate;
		rt[20]= GetShimeDate;
		rt[21]= GetDECD;
		rt[22]= GetDepartmentCd;
		
		return rt;
	}
	
	private static ArrayList<String> ErrCheck(
			String GetClCd,
			String GetWhCd,
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
			String GetDepartmentCd
			) {
		ArrayList<String> ErrMsg = new ArrayList<String>();
		
		String[] TxtTrim = TxtTrim(
				GetClCd,
				GetWhCd,
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
				GetDepartmentCd
				);
		GetClCd				= TxtTrim[0];
		GetWhCd				= TxtTrim[1];
		GetSPCd				= TxtTrim[2];
		GetSPName01			= TxtTrim[3];
		GetSPName02			= TxtTrim[4];
		GetSPName03			= TxtTrim[5];
		GetSPPost			= TxtTrim[6];
		GetSPAdd01			= TxtTrim[7];
		GetSPAdd02			= TxtTrim[8];
		GetSPAdd03			= TxtTrim[9];
		GetSPTel			= TxtTrim[10];
		GetSPFax			= TxtTrim[11];
		GetSPMail			= TxtTrim[12];
		GetCom01			= TxtTrim[13];
		GetCom02			= TxtTrim[14];
		GetCom03			= TxtTrim[15];
		GetPTMSCDBMN		= TxtTrim[16];
		GetPTMSCDNINUSHI	= TxtTrim[17];
		GetPaySite			= TxtTrim[18];
		GetPayDate			= TxtTrim[19];
		GetShimeDate		= TxtTrim[20];
		GetDECD				= TxtTrim[21];
		GetDepartmentCd		= TxtTrim[22];
		
		if("".equals(GetSPCd)) {
			ErrMsg.add("仕入先CDは必須です");
		}
		if("".equals(GetSPName01)) {
			ErrMsg.add("仕入先名01は必須です");
		}
		return ErrMsg;
	}
	
	private static void DataEntry(
			String GetClCd,
			String GetWhCd,
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
			String GetDepartmentCd
			) {
		String[] TxtTrim = TxtTrim(
				GetClCd,
				GetWhCd,
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
				GetDepartmentCd
				);
		GetClCd				= TxtTrim[0];
		GetWhCd				= TxtTrim[1];
		GetSPCd				= TxtTrim[2];
		GetSPName01			= TxtTrim[3];
		GetSPName02			= TxtTrim[4];
		GetSPName03			= TxtTrim[5];
		GetSPPost			= TxtTrim[6];
		GetSPAdd01			= TxtTrim[7];
		GetSPAdd02			= TxtTrim[8];
		GetSPAdd03			= TxtTrim[9];
		GetSPTel			= TxtTrim[10];
		GetSPFax			= TxtTrim[11];
		GetSPMail			= TxtTrim[12];
		GetCom01			= TxtTrim[13];
		GetCom02			= TxtTrim[14];
		GetCom03			= TxtTrim[15];
		GetPTMSCDBMN		= TxtTrim[16];
		GetPTMSCDNINUSHI	= TxtTrim[17];
		GetPaySite			= TxtTrim[18];
		GetPayDate			= TxtTrim[19];
		GetShimeDate		= TxtTrim[20];
		GetDECD				= TxtTrim[21];
		GetDepartmentCd		= TxtTrim[22];
		
		if("".equals(GetSPCd)) {GetSPCd = M00100SupplierRt.NewSpCdGet(1)[0];}
		
		String now_dtm = B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[1];
		
		Object[][] SetString = {
				 {"ClWh"			,"1","1","Key"	,GetWhCd}			//担当倉庫
				,{"ClCd"			,"1","1","Key"	,GetClCd}			//荷主CD
				,{"SPCd"			,"1","1","Key"	,GetSPCd}			//仕入先コード
				,{"SPName01"		,"1","1",""		,GetSPName01}		//仕入先名1
				,{"SPName02"		,"1","1",""		,GetSPName02}		//仕入先名2
				,{"SPName03"		,"1","1",""		,GetSPName03}		//仕入先名3
				,{"SPPost"			,"1","1",""		,GetSPPost}			//仕入先郵便
				,{"SPAdd01"			,"1","1",""		,GetSPAdd01}		//仕入先住所1
				,{"SPAdd02"			,"1","1",""		,GetSPAdd02}		//仕入先住所2
				,{"SPAdd03"			,"1","1",""		,GetSPAdd03}		//仕入先住所3
				,{"SPTel"			,"1","1",""		,GetSPTel}			//仕入先電話
				,{"SPFax"			,"1","1",""		,GetSPFax}			//仕入先FAX
				,{"SPMail"			,"1","1",""		,GetSPMail}			//仕入先MAIL
				,{"Com01"			,"1","1",""		,GetCom01}			//コメント1
				,{"Com02"			,"1","1",""		,GetCom02}			//コメント2
				,{"Com03"			,"1","1",""		,GetCom03}			//コメント3
				,{"PTMSCDBMN"		,"1","1",""		,GetPTMSCDBMN}		//基幹SYSコード（部門）
				,{"PTMSCDNINUSHI"	,"1","1",""		,GetPTMSCDNINUSHI}	//基幹SYSコード（荷主）
				,{"PaySite"			,"1","1",""		,GetPaySite}		//支払いサイト（月数）
				,{"PayDate"			,"1","1",""		,GetPayDate}		//支払日　末日＝99
				,{"ShimeDate"		,"1","1",""		,GetShimeDate}		//末日＝99
				,{"EntryDate"		,"1","0",""		,now_dtm}			//登録日
				,{"UpdateDate"		,"1","1",""		,now_dtm}			//更新日
				,{"EntryUser"		,"1","0",""		,"(" + A00000Main.LoginUserId + ")" + A00000Main.LoginUserName}	//登録者
				,{"UpdateUser"		,"1","1",""		,"(" + A00000Main.LoginUserId + ")" + A00000Main.LoginUserName}	//更新者
				,{"DECD"			,"1","1",""		,GetDECD}			//納品先コード
				,{"DepartmentCd"	,"1","1",""		,GetDepartmentCd}	//部署CD
				};
		String tgt_table = "WM0010Supplier";
		String TgtDB = "WANKO";
		int non_msg_fg = 1;
		
		A00020InsertUdateSQL.InsertUpdateOneRecord(SetString,tgt_table,TgtDB,non_msg_fg);
		
		B00100DefaultVariable.SupplierList();
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
	
	private static Object[][] SupplierRt(String TgtClCd,String TgtWhCd,String TgSPCd){
		ArrayList<String> SearchClWh = new ArrayList<String>(); 			//担当倉庫
		ArrayList<String> SearchClCd = new ArrayList<String>();				//荷主CD
		ArrayList<String> SearchSPCd = new ArrayList<String>();				//仕入先コード
		ArrayList<String> SearchSPName = new ArrayList<String>();			//仕入先名
		ArrayList<String> SearchSPPost = new ArrayList<String>();			//仕入先郵便
		ArrayList<String> SearchSPAdd = new ArrayList<String>();			//仕入先住所
		ArrayList<String> SearchSPTel = new ArrayList<String>();			//仕入先電話
		ArrayList<String> SearchSPFax = new ArrayList<String>();			//仕入先FAX
		ArrayList<String> SearchSPMail = new ArrayList<String>();			//仕入先MAIL
		ArrayList<String> SearchCom = new ArrayList<String>();				//コメント
		ArrayList<String> SearchPTMSCDBMN = new ArrayList<String>();		//基幹Sysコード（部門）
		ArrayList<String> SearchPTMSCDNINUSHI = new ArrayList<String>();	//基幹Sysコード（荷主）
		ArrayList<Integer> SearchPaySiteStr = new ArrayList<Integer>();		//支払いサイト（月数）開始
		ArrayList<Integer> SearchPayDateStr = new ArrayList<Integer>();		//支払日（日＝99）開始
		ArrayList<Integer> SearchShimeDateStr = new ArrayList<Integer>();	//締め日（末日＝99）開始
		ArrayList<Integer> SearchPaySiteEnd = new ArrayList<Integer>();		//支払いサイト（月数）終了
		ArrayList<Integer> SearchPayDateEnd = new ArrayList<Integer>();		//支払日（日＝99）終了
		ArrayList<Integer> SearchShimeDateEnd = new ArrayList<Integer>();	//締め日（末日＝99）終了
		ArrayList<String> SearchDECD = new ArrayList<String>();				//納品先コード
		ArrayList<String> SearchDepartmentCd = new ArrayList<String>();		//部署CD
		boolean AllSearch = false;
		
		if(!"".equals(TgtClCd	)) {SearchClCd.add(TgtClCd);}
		if(!"".equals(TgtWhCd	)) {SearchClWh.add(TgtWhCd);}
		if(!"".equals(TgSPCd	)) {SearchSPCd.add(TgSPCd);}
		
		Object[][] SupplierRt = M00100SupplierRt.SupplierRt(
				SearchClWh,				//担当倉庫
				SearchClCd,				//荷主CD
				SearchSPCd,				//仕入先コード
				SearchSPName,			//仕入先名
				SearchSPPost,			//仕入先郵便
				SearchSPAdd,			//仕入先住所
				SearchSPTel,			//仕入先電話
				SearchSPFax,			//仕入先FAX
				SearchSPMail,			//仕入先MAIL
				SearchCom,				//コメント
				SearchPTMSCDBMN,		//基幹Sysコード（部門）
				SearchPTMSCDNINUSHI,	//基幹Sysコード（荷主）
				SearchPaySiteStr,		//支払いサイト（月数）開始
				SearchPayDateStr,		//支払日（日＝99）開始
				SearchShimeDateStr,		//締め日（末日＝99）開始
				SearchPaySiteEnd,		//支払いサイト（月数）終了
				SearchPayDateEnd,		//支払日（日＝99）終了
				SearchShimeDateEnd,		//締め日（末日＝99）終了
				SearchDECD,				//納品先コード
				SearchDepartmentCd,		//部署CD
				AllSearch);
		return SupplierRt;
	}
}