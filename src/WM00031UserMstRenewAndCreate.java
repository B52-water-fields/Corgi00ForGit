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
import javax.swing.JTextField;

public class WM00031UserMstRenewAndCreate{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	static Object[][] SearchClList;
	
	public static void UserMstRenewAndCreate(int x,int y,String TgtWhCd,String TgtShippingCompanyCd,String TgtUserCd) {
		RenewFg = false;
		SearchClList = B00100DefaultVariable.SearchClList;
		
		if(null==TgtWhCd) {TgtWhCd="";}
		if(null==TgtShippingCompanyCd) {TgtShippingCompanyCd="";}
		if(null==TgtUserCd) {TgtUserCd="";}
		
		A00000Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		
		final JFrame main_fm = B00110FrameParts.FrameCreate(x,y,500,800,"Corgi00ユーザー登録・修正","");
		JLabel userinfo = B00110FrameParts.UserInfo();
		JButton exit_btn = B00110FrameParts.ExitBtn();
		JButton entry_btn = B00110FrameParts.EntryBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		main_fm.add(entry_btn);
		
		JButton Check_btn = B00110FrameParts.BtnSet(B00110FrameParts.Width-140, 90,100,20,"CD確認",11);	//ユーザーCDの存在確認
		main_fm.add(Check_btn);
		
		JButton CdRenew_btn = B00110FrameParts.BtnSet(B00110FrameParts.Width-140,115,100,20,"CD修正Mode",11);		//ユーザーCDの存在確認
		main_fm.add(CdRenew_btn);
		
		JButton NewCreate_btn = B00110FrameParts.BtnSet(B00110FrameParts.Width-140,140,100,20,"新規登録Mode",10);	//新規登録モードへ
		main_fm.add(NewCreate_btn);
		
		JLabel LB_WHCD				= B00110FrameParts.JLabelSet(  0, 40,100,20,"倉庫コード:",		11,1);
		JLabel LB_ShippingCompanyCd	= B00110FrameParts.JLabelSet(  0, 65,100,20,"運送会社CD:",		11,1);
		JLabel LB_UserCd			= B00110FrameParts.JLabelSet(  0, 90,100,20,"ユーザーCD:",		11,1);
		JLabel LB_MainClient		= B00110FrameParts.JLabelSet(  0,115,100,20,"主要担当荷主:",	11,1);
		JLabel LB_AuthorityFG		= B00110FrameParts.JLabelSet(  0,140,100,20,"権限区分:",		11,1);
		JLabel LB_PassWord			= B00110FrameParts.JLabelSet(  0,165,100,20,"パスワード:",		11,1);
		JLabel LB_UserName01		= B00110FrameParts.JLabelSet(  0,190,100,20,"ユーザー名1:",	11,1);
		JLabel LB_UserName02		= B00110FrameParts.JLabelSet(  0,215,100,20,"ユーザー名2:",	11,1);
		JLabel LB_UserName03		= B00110FrameParts.JLabelSet(  0,240,100,20,"ユーザー名3:",	11,1);
		JLabel LB_CarCd				= B00110FrameParts.JLabelSet(  0,265,100,20,"標準車輛:",		11,1);
		JLabel LB_Post				= B00110FrameParts.JLabelSet(  0,290,100,20,"郵便番号:",		11,1);
		JLabel LB_Add01				= B00110FrameParts.JLabelSet(  0,315,100,20,"住所1:",			11,1);
		JLabel LB_Add02				= B00110FrameParts.JLabelSet(  0,340,100,20,"住所2:",			11,1);
		JLabel LB_Add03				= B00110FrameParts.JLabelSet(  0,365,100,20,"住所3:",			11,1);
		JLabel LB_Tel				= B00110FrameParts.JLabelSet(  0,390,100,20,"Tel:",			11,1);
		JLabel LB_Fax				= B00110FrameParts.JLabelSet(  0,415,100,20,"FAX:",			11,1);
		JLabel LB_Mail				= B00110FrameParts.JLabelSet(  0,440,100,20,"Mail:",			11,1);
		JLabel LB_Com01				= B00110FrameParts.JLabelSet(  0,465,100,20,"コメント1:",		11,1);
		JLabel LB_Com02				= B00110FrameParts.JLabelSet(  0,490,100,20,"コメント2:",		11,1);
		JLabel LB_Com03				= B00110FrameParts.JLabelSet(  0,515,100,20,"コメント3:",		11,1);
		JLabel LB_EntryDate			= B00110FrameParts.JLabelSet(  0,540,100,20,"データ登録日時:",	11,1);
		JLabel LB_UpdateDate		= B00110FrameParts.JLabelSet(  0,565,100,20,"データ更新日時:",	11,1);
		JLabel LB_EntryUser			= B00110FrameParts.JLabelSet(  0,590,100,20,"登録者コード:",	11,1);
		JLabel LB_UpdateUser		= B00110FrameParts.JLabelSet(  0,615,100,20,"更新者コード:",	11,1);
		JLabel LB_PTMSCD			= B00110FrameParts.JLabelSet(  0,640,100,20,"基幹SYSCD:",		11,1);
		JLabel LB_DelFg				= B00110FrameParts.JLabelSet(  0,665,100,20,"削除区分:",		11,1);
		
		final JComboBox  TB_WHCD				= B00110FrameParts.JComboBoxSet( 100, 40,250,20,B00100DefaultVariable.WhList[0],	11);					//倉庫コード
		final JComboBox  TB_ShippingCompanyCd	= B00110FrameParts.JComboBoxSet( 100, 65,250,20,B00100DefaultVariable.ShippingCompanyList[0],	11);	//運送会社CD
		final JTextField TB_UserCd				= B00110FrameParts.JTextFieldSet(100, 90,100,20,"",11,0);	//ユーザーCD
		final JComboBox  TB_MainClient			= B00110FrameParts.JComboBoxSet( 100,115,250,20,SearchClList[0],	11);								//主要担当荷主
		final JComboBox  TB_AuthorityFG			= B00110FrameParts.JComboBoxSet( 100,140,250,20,B00100DefaultVariable.AuthorityFG[0],	11);			//権限区分
		final JTextField TB_PassWord			= B00110FrameParts.JTextFieldSet(100,165,100,20,"",11,0);	//パスワード
		final JTextField TB_UserName01			= B00110FrameParts.JTextFieldSet(100,190,250,20,"",11,0);	//ユーザー名1
		final JTextField TB_UserName02			= B00110FrameParts.JTextFieldSet(100,215,250,20,"",11,0);	//ユーザー名2
		final JTextField TB_UserName03			= B00110FrameParts.JTextFieldSet(100,240,250,20,"",11,0);	//ユーザー名3
		final JTextField TB_CarCd				= B00110FrameParts.JTextFieldSet(100,265,100,20,"",11,0);	//標準車輛
		final JTextField TB_Post				= B00110FrameParts.JTextFieldSet(100,290,100,20,"",11,0);	//郵便番号
		final JTextField TB_Add01				= B00110FrameParts.JTextFieldSet(100,315,250,20,"",11,0);	//住所1
		final JTextField TB_Add02				= B00110FrameParts.JTextFieldSet(100,340,250,20,"",11,0);	//住所2
		final JTextField TB_Add03				= B00110FrameParts.JTextFieldSet(100,365,250,20,"",11,0);	//住所3
		final JTextField TB_Tel					= B00110FrameParts.JTextFieldSet(100,390,100,20,"",11,0);	//Tel
		final JTextField TB_Fax					= B00110FrameParts.JTextFieldSet(100,415,100,20,"",11,0);	//FAX
		final JTextField TB_Mail				= B00110FrameParts.JTextFieldSet(100,440,100,20,"",11,0);	//Mail
		final JTextField TB_Com01				= B00110FrameParts.JTextFieldSet(100,465,250,20,"",11,0);	//コメント1
		final JTextField TB_Com02				= B00110FrameParts.JTextFieldSet(100,490,250,20,"",11,0);	//コメント2
		final JTextField TB_Com03				= B00110FrameParts.JTextFieldSet(100,515,250,20,"",11,0);	//コメント3
		final JTextField TB_EntryDate			= B00110FrameParts.JTextFieldSet(100,540,250,20,"",11,0);	//データ登録日時
		final JTextField TB_UpdateDate			= B00110FrameParts.JTextFieldSet(100,565,250,20,"",11,0);	//データ更新日時
		final JTextField TB_EntryUser			= B00110FrameParts.JTextFieldSet(100,590,250,20,"",11,0);	//登録者コード
		final JTextField TB_UpdateUser			= B00110FrameParts.JTextFieldSet(100,615,250,20,"",11,0);	//更新者コード
		final JTextField TB_PTMSCD				= B00110FrameParts.JTextFieldSet(100,640,100,20,"",11,0);	//基幹SYSCD
		final JComboBox  TB_DelFg				= B00110FrameParts.JComboBoxSet( 100,665,150,20,B00100DefaultVariable.DelList[0],	11);				//削除区分
		
		TB_WHCD.setSelectedIndex(0);
		TB_ShippingCompanyCd.setSelectedIndex(0);
		TB_MainClient.setSelectedIndex(0);
		TB_AuthorityFG.setSelectedIndex(0);
		TB_DelFg.setSelectedIndex(0);
		
		for(int i=0;i<B00100DefaultVariable.WhList[1].length;i++){
			if((""+B00100DefaultVariable.WhList[1][i]).equals(""+A00000Main.ClWh)){
				TB_WHCD.setSelectedIndex(i);
			}
		}
		
		for(int i=0;i<B00100DefaultVariable.ShippingCompanyList[1].length;i++){
			if((""+B00100DefaultVariable.ShippingCompanyList[1][i]).equals(""+A00000Main.LoginUserCompany)){
				TB_ShippingCompanyCd.setSelectedIndex(i);
			}
		}
		
		//主要担当荷主を選択中の倉庫配下の荷主だけにする
		String GetWHCD	= ""+B00100DefaultVariable.WhList[1][TB_WHCD.getSelectedIndex()];
		ArrayList<String> SearchClGpCD = new ArrayList<String>();
		ArrayList<String> SearchCLCD = new ArrayList<String>();
		ArrayList<String> SearchCLName = new ArrayList<String>();
		ArrayList<String> SearchPost = new ArrayList<String>();
		ArrayList<String> searchAdd = new ArrayList<String>();
		ArrayList<String> SearchTel = new ArrayList<String>();
		ArrayList<String> SearchFax = new ArrayList<String>();
		ArrayList<String> SearchMail = new ArrayList<String>();
		ArrayList<String> SearchCom = new ArrayList<String>();
		ArrayList<String> SearchWHCD = new ArrayList<String>();
		boolean AllSearch = false;
		
		SearchWHCD.add(GetWHCD);
		
		Object[][] ClMstRt = M00011ClMstRt.ClMstRt(
				SearchClGpCD,
				SearchCLCD,
				SearchCLName,
				SearchPost,
				searchAdd,
				SearchTel,
				SearchFax,
				SearchMail,
				SearchCom,
				SearchWHCD,
				AllSearch);
		if(0<ClMstRt.length) {
			TB_MainClient.removeAllItems();
			
			SearchClList = new Object[3][ClMstRt.length+1];
			SearchClList[0][0] = "未指定";
			SearchClList[1][0] = "";
			SearchClList[2][0] = "";
			TB_MainClient.addItem(SearchClList[0][0]);
			for(int i=0;i<ClMstRt.length;i++) {
				SearchClList[0][i+1] = "" + ClMstRt[i][0] + ":" + ClMstRt[i][5];
				SearchClList[1][i+1] = "" + ClMstRt[i][0];
				SearchClList[2][i+1] = "" + ClMstRt[i][5];
				TB_MainClient.addItem(SearchClList[0][i+1]);
			}
		}
		
		if(!"".equals(TgtWhCd)&&!"".equals(TgtShippingCompanyCd)&&!"".equals(TgtUserCd)) {
			SearchWHCD = new ArrayList<String>();
			ArrayList<String> SearchShippingCompanyCd = new ArrayList<String>();
			ArrayList<String> SearchAuthorityFG = new ArrayList<String>();
			ArrayList<String> SearchUserCd = new ArrayList<String>();
			ArrayList<String> SearchUserName = new ArrayList<String>();
			ArrayList<String> SearchCarCd = new ArrayList<String>();
			ArrayList<String> SearchCarName = new ArrayList<String>();
			SearchPost = new ArrayList<String>();
			ArrayList<String> SearchAdd = new ArrayList<String>();
			SearchTel = new ArrayList<String>();
			SearchFax = new ArrayList<String>();
			SearchMail = new ArrayList<String>();
			SearchCom = new ArrayList<String>();
			ArrayList<String> SearchDelFg = new ArrayList<String>();
			AllSearch = false;
			
			SearchWHCD.add(TgtWhCd);
			SearchShippingCompanyCd.add(TgtShippingCompanyCd);
			SearchUserCd.add(TgtUserCd);
			
			Object[][] UserMstRt = M00020UserMstRt.UserMstRt(
						SearchWHCD,
						SearchShippingCompanyCd,
						SearchAuthorityFG,
						SearchUserCd,
						SearchUserName,
						SearchCarCd,
						SearchCarName,
						SearchPost,
						SearchAdd,
						SearchTel,
						SearchFax,
						SearchMail,
						SearchCom,
						SearchDelFg,
						AllSearch);

			
			
			if(0<UserMstRt.length) {
				//権限が9：管理者だった場合、管理者権限でなければ更新させない
				boolean KickFg = true;
				if("9".equals(""+UserMstRt[0][5])) {
					KickFg = false;
				}
				if("9".equals(A00000Main.LoginUserAuthorityFG)) {
					KickFg = true;
				}
				
				if(KickFg) {
					for(int i=0;i<B00100DefaultVariable.WhList[1].length;i++){
						if((""+B00100DefaultVariable.WhList[1][i]).equals(""+UserMstRt[0][0])){
							TB_WHCD.setSelectedIndex(i);
						}
					}
					
					for(int i=0;i<B00100DefaultVariable.ShippingCompanyList[1].length;i++){
						if((""+B00100DefaultVariable.ShippingCompanyList[1][i]).equals(""+UserMstRt[0][1])){
							TB_ShippingCompanyCd.setSelectedIndex(i);
						}
					}
					
					//主要担当荷主を選択中の倉庫配下の荷主だけにする
					GetWHCD	= ""+B00100DefaultVariable.WhList[1][TB_WHCD.getSelectedIndex()];
					SearchClGpCD = new ArrayList<String>();
					SearchCLCD = new ArrayList<String>();
					SearchCLName = new ArrayList<String>();
					SearchPost = new ArrayList<String>();
					searchAdd = new ArrayList<String>();
					SearchTel = new ArrayList<String>();
					SearchFax = new ArrayList<String>();
					SearchMail = new ArrayList<String>();
					SearchCom = new ArrayList<String>();
					SearchWHCD = new ArrayList<String>();
					AllSearch = false;
					
					SearchWHCD.add(GetWHCD);
					
					ClMstRt = M00011ClMstRt.ClMstRt(
							SearchClGpCD,
							SearchCLCD,
							SearchCLName,
							SearchPost,
							searchAdd,
							SearchTel,
							SearchFax,
							SearchMail,
							SearchCom,
							SearchWHCD,
							AllSearch);
					if(0<ClMstRt.length) {
						TB_MainClient.removeAllItems();
						
						SearchClList = new Object[3][ClMstRt.length+1];
						SearchClList[0][0] = "未指定";
						SearchClList[1][0] = "";
						SearchClList[2][0] = "";
						TB_MainClient.addItem(SearchClList[0][0]);
						for(int i=0;i<ClMstRt.length;i++) {
							SearchClList[0][i+1] = "" + ClMstRt[i][0] + ":" + ClMstRt[i][5];
							SearchClList[1][i+1] = "" + ClMstRt[i][0];
							SearchClList[2][i+1] = "" + ClMstRt[i][5];
							TB_MainClient.addItem(SearchClList[0][i+1]);
						}
					}
					
					for(int i=0;i<SearchClList[1].length;i++){
						if((""+SearchClList[1][i]).equals(""+UserMstRt[0][30])){
							TB_MainClient.setSelectedIndex(i);
						}
					}
					
					for(int i=0;i<B00100DefaultVariable.AuthorityFG[1].length;i++){
						if((""+B00100DefaultVariable.AuthorityFG[1][i]).equals(""+UserMstRt[0][5])){
							TB_AuthorityFG.setSelectedIndex(i);
						}
					}
					
					for(int i=0;i<B00100DefaultVariable.DelList[1].length;i++){
						if((""+B00100DefaultVariable.DelList[1][i]).equals(""+UserMstRt[0][28])){
							TB_DelFg.setSelectedIndex(i);
						}
					}
					TB_UserCd.setText(		""+UserMstRt[0][3]);
					TB_PassWord.setText(	""+UserMstRt[0][4]);
					TB_UserName01.setText(	""+UserMstRt[0][10]);
					TB_UserName02.setText(	""+UserMstRt[0][11]);
					TB_UserName03.setText(	""+UserMstRt[0][12]);
					TB_CarCd.setText(		""+UserMstRt[0][6]);
					TB_Post.setText(		""+UserMstRt[0][13]);
					TB_Add01.setText(		""+UserMstRt[0][14]);
					TB_Add02.setText(		""+UserMstRt[0][15]);
					TB_Add03.setText(		""+UserMstRt[0][16]);
					TB_Tel.setText(			""+UserMstRt[0][17]);
					TB_Fax.setText(			""+UserMstRt[0][18]);
					TB_Mail.setText(		""+UserMstRt[0][19]);
					TB_Com01.setText(		""+UserMstRt[0][20]);
					TB_Com02.setText(		""+UserMstRt[0][21]);
					TB_Com03.setText(		""+UserMstRt[0][22]);
					TB_EntryDate.setText(	""+UserMstRt[0][23]);
					TB_UpdateDate.setText(	""+UserMstRt[0][24]);
					TB_EntryUser.setText(	""+UserMstRt[0][25]);
					TB_UpdateUser.setText(	""+UserMstRt[0][26]);
					TB_PTMSCD.setText(		""+UserMstRt[0][27]);
					
					TB_WHCD.setEnabled(false);
					TB_ShippingCompanyCd.setEnabled(false);
					TB_UserCd.setEnabled(false);
				}
			}
			
		}
		
		TB_EntryDate.setEditable(false);	//データ登録日時
		TB_UpdateDate.setEditable(false);	//データ更新日時
		TB_EntryUser.setEditable(false);	//登録者コード
		TB_UpdateUser.setEditable(false);	//更新者コード
		
		
		main_fm.add(LB_WHCD);
		main_fm.add(LB_ShippingCompanyCd);
		main_fm.add(LB_UserCd);
		main_fm.add(LB_MainClient);
		main_fm.add(LB_AuthorityFG);
		main_fm.add(LB_PassWord);
		main_fm.add(LB_UserName01);
		main_fm.add(LB_UserName02);
		main_fm.add(LB_UserName03);	
		main_fm.add(LB_CarCd);
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
		main_fm.add(LB_EntryDate);
		main_fm.add(LB_UpdateDate);
		main_fm.add(LB_EntryUser);
		main_fm.add(LB_UpdateUser);
		main_fm.add(LB_PTMSCD);
		main_fm.add(LB_DelFg);
			
		main_fm.add(TB_WHCD);
		main_fm.add(TB_ShippingCompanyCd);
		main_fm.add(TB_UserCd);
		main_fm.add(TB_MainClient);
		main_fm.add(TB_AuthorityFG);
		main_fm.add(TB_PassWord);
		main_fm.add(TB_UserName01);
		main_fm.add(TB_UserName02);
		main_fm.add(TB_UserName03);
		main_fm.add(TB_CarCd);
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
		main_fm.add(TB_EntryDate);
		main_fm.add(TB_UpdateDate);
		main_fm.add(TB_EntryUser);
		main_fm.add(TB_UpdateUser);
		main_fm.add(TB_PTMSCD);
		main_fm.add(TB_DelFg);
		
		main_fm.setVisible(true);
		RenewFg = true;
		
		//倉庫選択時の挙動
		TB_WHCD.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					//主要担当荷主を選択中の倉庫配下の荷主だけにする
					String GetWHCD	= ""+B00100DefaultVariable.WhList[1][TB_WHCD.getSelectedIndex()];
					ArrayList<String> SearchClGpCD = new ArrayList<String>();
					ArrayList<String> SearchCLCD = new ArrayList<String>();
					ArrayList<String> SearchCLName = new ArrayList<String>();
					ArrayList<String> SearchPost = new ArrayList<String>();
					ArrayList<String> searchAdd = new ArrayList<String>();
					ArrayList<String> SearchTel = new ArrayList<String>();
					ArrayList<String> SearchFax = new ArrayList<String>();
					ArrayList<String> SearchMail = new ArrayList<String>();
					ArrayList<String> SearchCom = new ArrayList<String>();
					ArrayList<String> SearchWHCD = new ArrayList<String>();
					boolean AllSearch = false;
					
					SearchWHCD.add(GetWHCD);
					
					Object[][] ClMstRt = M00011ClMstRt.ClMstRt(
							SearchClGpCD,
							SearchCLCD,
							SearchCLName,
							SearchPost,
							searchAdd,
							SearchTel,
							SearchFax,
							SearchMail,
							SearchCom,
							SearchWHCD,
							AllSearch);
					if(0<ClMstRt.length) {
						TB_MainClient.removeAllItems();
						
						SearchClList = new Object[3][ClMstRt.length+1];
						SearchClList[0][0] = "未指定";
						SearchClList[1][0] = "";
						SearchClList[2][0] = "";
						TB_MainClient.addItem(SearchClList[0][0]);
						for(int i=0;i<ClMstRt.length;i++) {
							SearchClList[0][i+1] = "" + ClMstRt[i][0] + ":" + ClMstRt[i][5];
							SearchClList[1][i+1] = "" + ClMstRt[i][0];
							SearchClList[2][i+1] = "" + ClMstRt[i][5];
							TB_MainClient.addItem(SearchClList[0][i+1]);
						}
					}
					RenewFg = true;
				}
			}
		});
		
		//コード変更を許可する
		CdRenew_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				TB_WHCD.setEnabled(true);
				TB_ShippingCompanyCd.setEnabled(true);
				TB_UserCd.setEnabled(true);
			}
		});
		
		//新規登録ボタン
		NewCreate_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				WM00031UserMstRenewAndCreate.UserMstRenewAndCreate(0, 0,"","","");
			}
		});
		
		
		//ユーザーCDの存在確認押下時の挙動
		Check_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					String GetWHCD				= ""+B00100DefaultVariable.WhList[1][TB_WHCD.getSelectedIndex()];								//倉庫コード
					String GetShippingCompanyCd	= ""+B00100DefaultVariable.ShippingCompanyList[1][TB_ShippingCompanyCd.getSelectedIndex()];	//運送会社CD
					String GetUserCd			= TB_UserCd.getText();	//ユーザーCD
					
					if(null==GetWHCD				){GetWHCD 				= "";}
					if(null==GetShippingCompanyCd	){GetShippingCompanyCd 	= "";}
					if(null==GetUserCd				){GetUserCd 			= "";}
					
					GetWHCD					= B00020ToolsTextControl.Trim(GetWHCD);
					GetShippingCompanyCd	= B00020ToolsTextControl.Trim(GetShippingCompanyCd);
					GetUserCd				= B00020ToolsTextControl.Trim(GetUserCd);
					
					if(!"".equals(GetWHCD)&&!"".equals(GetShippingCompanyCd)&&!"".equals(GetUserCd)) {
						ArrayList<String> SearchWHCD = new ArrayList<String>();
						ArrayList<String> SearchShippingCompanyCd = new ArrayList<String>();
						ArrayList<String> SearchAuthorityFG = new ArrayList<String>();
						ArrayList<String> SearchUserCd = new ArrayList<String>();
						ArrayList<String> SearchUserName = new ArrayList<String>();
						ArrayList<String> SearchCarCd = new ArrayList<String>();
						ArrayList<String> SearchCarName = new ArrayList<String>();
						ArrayList<String> SearchPost = new ArrayList<String>();
						ArrayList<String> SearchAdd = new ArrayList<String>();
						ArrayList<String> SearchTel = new ArrayList<String>();
						ArrayList<String> SearchFax = new ArrayList<String>();
						ArrayList<String> SearchMail = new ArrayList<String>();
						ArrayList<String> SearchCom = new ArrayList<String>();
						ArrayList<String> SearchDelFg = new ArrayList<String>();
						boolean AllSearch = false;
						
						SearchWHCD.add(GetWHCD);
						SearchShippingCompanyCd.add(GetShippingCompanyCd);
						SearchUserCd.add(GetUserCd);
						
						Object[][] UserMstRt = M00020UserMstRt.UserMstRt(
									SearchWHCD,
									SearchShippingCompanyCd,
									SearchAuthorityFG,
									SearchUserCd,
									SearchUserName,
									SearchCarCd,
									SearchCarName,
									SearchPost,
									SearchAdd,
									SearchTel,
									SearchFax,
									SearchMail,
									SearchCom,
									SearchDelFg,
									AllSearch);
						
						if(0<UserMstRt.length) {
							boolean KickFg = false;
							if("9".equals(""+UserMstRt[0][5])) {
								if("9".equals(A00000Main.LoginUserAuthorityFG)) {
									KickFg = true;
								}else {
									JOptionPane.showMessageDialog(null, "更新不可能なユーザーを上書きしようとしています");
								}
							}else {
								KickFg = true;
							}
							if(KickFg) {
								KickFg = false;
								int option = JOptionPane.showConfirmDialog(null, "既に登録されているユーザーコードです\n現在の登録情報をセットしますか？","更新確認", JOptionPane.YES_NO_OPTION,
									      JOptionPane.WARNING_MESSAGE);
								if (option == JOptionPane.YES_OPTION){
									KickFg = true;
								}else {
									KickFg = false;
								}
								
								if(KickFg) {
									for(int i=0;i<B00100DefaultVariable.WhList[1].length;i++){
										if((""+B00100DefaultVariable.WhList[1][i]).equals(""+UserMstRt[0][0])){
											TB_WHCD.setSelectedIndex(i);
										}
									}
									
									for(int i=0;i<B00100DefaultVariable.ShippingCompanyList[1].length;i++){
										if((""+B00100DefaultVariable.ShippingCompanyList[1][i]).equals(""+UserMstRt[0][1])){
											TB_ShippingCompanyCd.setSelectedIndex(i);
										}
									}
									
									for(int i=0;i<SearchClList[1].length;i++){
										if((""+SearchClList[1][i]).equals(""+UserMstRt[0][30])){
											TB_MainClient.setSelectedIndex(i);
										}
									}
									
									for(int i=0;i<B00100DefaultVariable.AuthorityFG[1].length;i++){
										if((""+B00100DefaultVariable.AuthorityFG[1][i]).equals(""+UserMstRt[0][5])){
											TB_AuthorityFG.setSelectedIndex(i);
										}
									}
									
									for(int i=0;i<B00100DefaultVariable.DelList[1].length;i++){
										if((""+B00100DefaultVariable.DelList[1][i]).equals(""+UserMstRt[0][28])){
											TB_DelFg.setSelectedIndex(i);
										}
									}
									TB_UserCd.setText(		""+UserMstRt[0][3]);
									TB_PassWord.setText(	""+UserMstRt[0][4]);
									TB_UserName01.setText(	""+UserMstRt[0][10]);
									TB_UserName02.setText(	""+UserMstRt[0][11]);
									TB_UserName03.setText(	""+UserMstRt[0][12]);
									TB_CarCd.setText(		""+UserMstRt[0][6]);
									TB_Post.setText(		""+UserMstRt[0][13]);
									TB_Add01.setText(		""+UserMstRt[0][14]);
									TB_Add02.setText(		""+UserMstRt[0][15]);
									TB_Add03.setText(		""+UserMstRt[0][16]);
									TB_Tel.setText(			""+UserMstRt[0][17]);
									TB_Fax.setText(			""+UserMstRt[0][18]);
									TB_Mail.setText(		""+UserMstRt[0][19]);
									TB_Com01.setText(		""+UserMstRt[0][20]);
									TB_Com02.setText(		""+UserMstRt[0][21]);
									TB_Com03.setText(		""+UserMstRt[0][22]);
									TB_EntryDate.setText(	""+UserMstRt[0][23]);
									TB_UpdateDate.setText(	""+UserMstRt[0][24]);
									TB_EntryUser.setText(	""+UserMstRt[0][25]);
									TB_UpdateUser.setText(	""+UserMstRt[0][26]);
									TB_PTMSCD.setText(		""+UserMstRt[0][27]);
									
									TB_WHCD.setEnabled(false);
									TB_ShippingCompanyCd.setEnabled(false);
									TB_UserCd.setEnabled(false);
								}
							}
						}else {
							JOptionPane.showMessageDialog(null, "新規ユーザーコードです");
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
					String GetWHCD				= ""+B00100DefaultVariable.WhList[1][TB_WHCD.getSelectedIndex()];								//倉庫コード
					String GetShippingCompanyCd	= ""+B00100DefaultVariable.ShippingCompanyList[1][TB_ShippingCompanyCd.getSelectedIndex()];	//運送会社CD
					String GetMainClient		= ""+SearchClList[1][TB_MainClient.getSelectedIndex()];										//主要担当荷主
					String GetAuthorityFG		= ""+B00100DefaultVariable.AuthorityFG[1][TB_AuthorityFG.getSelectedIndex()];					//権限区分
					String GetDelFg				= ""+B00100DefaultVariable.DelList[1][TB_DelFg.getSelectedIndex()];							//削除区分
					
					String GetUserCd		= TB_UserCd.getText();		//ユーザーCD
					String GetPassWord		= TB_PassWord.getText();	//パスワード
					String GetUserName01	= TB_UserName01.getText();	//ユーザー名1
					String GetUserName02	= TB_UserName02.getText();	//ユーザー名2
					String GetUserName03	= TB_UserName03.getText();	//ユーザー名3
					String GetCarCd			= TB_CarCd.getText();		//標準車輛
					String GetPost			= TB_Post.getText();		//郵便番号
					String GetAdd01			= TB_Add01.getText();		//住所1
					String GetAdd02			= TB_Add02.getText();		//住所2
					String GetAdd03			= TB_Add03.getText();		//住所3
					String GetTel			= TB_Tel.getText();			//Tel
					String GetFax			= TB_Fax.getText();			//FAX
					String GetMail			= TB_Mail.getText();		//Mail
					String GetCom01			= TB_Com01.getText();		//コメント1
					String GetCom02			= TB_Com02.getText();		//コメント2
					String GetCom03			= TB_Com03.getText();		//コメント3
					String GetEntryDate		= TB_EntryDate.getText();	//データ登録日時
					String GetUpdateDate	= TB_UpdateDate.getText();	//データ更新日時
					String GetEntryUser		= TB_EntryUser.getText();	//登録者コード
					String GetUpdateUser	= TB_UpdateUser.getText();	//更新者コード
					String GetPTMSCD		= TB_PTMSCD.getText();		//基幹SYSCD

					if(null==GetWHCD				){GetWHCD 				= "";}
					if(null==GetShippingCompanyCd	){GetShippingCompanyCd 	= "";}
					if(null==GetMainClient			){GetMainClient 		= "";}
					if(null==GetAuthorityFG			){GetAuthorityFG 		= "";}
					if(null==GetDelFg				){GetDelFg 				= "";}
					if(null==GetUserCd				){GetUserCd 			= "";}
					if(null==GetPassWord			){GetPassWord 			= "";}
					if(null==GetUserName01			){GetUserName01 		= "";}
					if(null==GetUserName02			){GetUserName02 		= "";}
					if(null==GetUserName03			){GetUserName03 		= "";}
					if(null==GetCarCd				){GetCarCd 				= "";}
					if(null==GetPost				){GetPost 				= "";}
					if(null==GetAdd01				){GetAdd01 				= "";}
					if(null==GetAdd02				){GetAdd02 				= "";}
					if(null==GetAdd03				){GetAdd03 				= "";}
					if(null==GetTel					){GetTel 				= "";}
					if(null==GetFax					){GetFax 				= "";}
					if(null==GetMail				){GetMail 				= "";}
					if(null==GetCom01				){GetCom01 				= "";}
					if(null==GetCom02				){GetCom02 				= "";}
					if(null==GetCom03				){GetCom03 				= "";}
					if(null==GetEntryDate			){GetEntryDate 			= "";}
					if(null==GetUpdateDate			){GetUpdateDate 		= "";}
					if(null==GetEntryUser			){GetEntryUser 			= "";}
					if(null==GetUpdateUser			){GetUpdateUser 		= "";}
					if(null==GetPTMSCD				){GetPTMSCD 			= "";}
					
					GetWHCD					= B00020ToolsTextControl.Trim(GetWHCD);
					GetShippingCompanyCd	= B00020ToolsTextControl.Trim(GetShippingCompanyCd);
					GetMainClient			= B00020ToolsTextControl.Trim(GetMainClient);
					GetAuthorityFG			= B00020ToolsTextControl.Trim(GetAuthorityFG);
					GetDelFg				= B00020ToolsTextControl.Trim(GetDelFg);
					GetUserCd				= B00020ToolsTextControl.Trim(GetUserCd);
					GetPassWord				= B00020ToolsTextControl.Trim(GetPassWord);
					GetUserName01			= B00020ToolsTextControl.Trim(GetUserName01);
					GetUserName02			= B00020ToolsTextControl.Trim(GetUserName02);
					GetUserName03			= B00020ToolsTextControl.Trim(GetUserName03);
					GetCarCd				= B00020ToolsTextControl.Trim(GetCarCd);
					GetPost					= B00020ToolsTextControl.Trim(GetPost);
					GetAdd01				= B00020ToolsTextControl.Trim(GetAdd01);
					GetAdd02				= B00020ToolsTextControl.Trim(GetAdd02);
					GetAdd03				= B00020ToolsTextControl.Trim(GetAdd03);
					GetTel					= B00020ToolsTextControl.Trim(GetTel);
					GetFax					= B00020ToolsTextControl.Trim(GetFax);
					GetMail					= B00020ToolsTextControl.Trim(GetMail);
					GetCom01				= B00020ToolsTextControl.Trim(GetCom01);
					GetCom02				= B00020ToolsTextControl.Trim(GetCom02);
					GetCom03				= B00020ToolsTextControl.Trim(GetCom03);
					GetEntryDate			= B00020ToolsTextControl.Trim(GetEntryDate);
					GetUpdateDate			= B00020ToolsTextControl.Trim(GetUpdateDate);
					GetEntryUser			= B00020ToolsTextControl.Trim(GetEntryUser);
					GetUpdateUser			= B00020ToolsTextControl.Trim(GetUpdateUser);
					GetPTMSCD				= B00020ToolsTextControl.Trim(GetPTMSCD);
					
					GetPost			= B00020ToolsTextControl.num_only_String(GetPost);
					GetTel			= B00020ToolsTextControl.num_only_String(GetTel);
					GetFax			= B00020ToolsTextControl.num_only_String(GetFax);
					GetAuthorityFG	= B00020ToolsTextControl.num_only_String(GetAuthorityFG);	if("".equals(GetAuthorityFG)) {GetAuthorityFG   = "0";}
					GetDelFg		= B00020ToolsTextControl.num_only_String(GetDelFg);	if("".equals(GetDelFg)) {GetDelFg = "0";}
					
					GetUserCd		= B00020ToolsTextControl.only1byte_String(GetUserCd);
					
					boolean KickFg = false;
					
					if(!"".equals(GetWHCD)&&!"".equals(GetShippingCompanyCd)&&!"".equals(GetUserCd)) {
						KickFg = true;
					}else {
						JOptionPane.showMessageDialog(null, "ユーザーコードは必須です");
					}
					if(KickFg) {
						//更新権限のないユーザーを更新してしまうのを防止する
						ArrayList<String> SearchWHCD = new ArrayList<String>();
						ArrayList<String> SearchShippingCompanyCd = new ArrayList<String>();
						ArrayList<String> SearchAuthorityFG = new ArrayList<String>();
						ArrayList<String> SearchUserCd = new ArrayList<String>();
						ArrayList<String> SearchUserName = new ArrayList<String>();
						ArrayList<String> SearchCarCd = new ArrayList<String>();
						ArrayList<String> SearchCarName = new ArrayList<String>();
						ArrayList<String> SearchPost = new ArrayList<String>();
						ArrayList<String> SearchAdd = new ArrayList<String>();
						ArrayList<String> SearchTel = new ArrayList<String>();
						ArrayList<String> SearchFax = new ArrayList<String>();
						ArrayList<String> SearchMail = new ArrayList<String>();
						ArrayList<String> SearchCom = new ArrayList<String>();
						ArrayList<String> SearchDelFg = new ArrayList<String>();
						boolean AllSearch = false;
						
						SearchWHCD.add(GetWHCD);
						SearchShippingCompanyCd.add(GetShippingCompanyCd);
						SearchUserCd.add(GetUserCd);
						
						Object[][] UserMstRt = M00020UserMstRt.UserMstRt(
									SearchWHCD,
									SearchShippingCompanyCd,
									SearchAuthorityFG,
									SearchUserCd,
									SearchUserName,
									SearchCarCd,
									SearchCarName,
									SearchPost,
									SearchAdd,
									SearchTel,
									SearchFax,
									SearchMail,
									SearchCom,
									SearchDelFg,
									AllSearch);
						if(0<UserMstRt.length) {
							if("9".equals(""+UserMstRt[0][5])) {
								if("9".equals(A00000Main.LoginUserAuthorityFG)) {
									KickFg = true;
								}else {
									JOptionPane.showMessageDialog(null, "更新不可能なユーザーを上書きしようとしています");
									KickFg = false;
								}
							}else {
								KickFg = true;
							}
							if(KickFg) {
								int option = JOptionPane.showConfirmDialog(null, "既に登録されているユーザーコードです\n登録情報で上書きしますか？","更新確認", JOptionPane.YES_NO_OPTION,
									      JOptionPane.WARNING_MESSAGE);
								if (option == JOptionPane.YES_OPTION){
									KickFg = true;
								}else {
									KickFg = false;
								}
							}
						}else {
							KickFg = true;
						}
						
						if(KickFg) {
							String now_dtm = B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[1];
							String[][] SetString = {
									{"WHCD"					,"1","1",GetWHCD}				//倉庫コード
									,{"ShippingCompanyCd"	,"1","1",GetShippingCompanyCd}	//運送会社CD
									,{"UserCd"				,"1","1",GetUserCd}				//ユーザーCD
									,{"PassWord"			,"1","1",GetPassWord}			//パスワード
									,{"AuthorityFG"			,"1","1",GetAuthorityFG}		//権限区分
									,{"CarCd"				,"1","1",GetCarCd}				//標準車輛CD
									,{"UserName01"			,"1","1",GetUserName01}			//ユーザー名1
									,{"UserName02"			,"1","1",GetUserName02}			//ユーザー名2
									,{"UserName03"			,"1","1",GetUserName03}			//ユーザー名3
									,{"Post"				,"1","1",GetPost}				//郵便番号
									,{"Add01"				,"1","1",GetAdd01}				//住所1
									,{"Add02"				,"1","1",GetAdd02}				//住所2
									,{"Add03"				,"1","1",GetAdd03}				//住所3
									,{"Tel"					,"1","1",GetTel}				//電話番号
									,{"Fax"					,"1","1",GetFax}				//FAX
									,{"Mail"				,"1","1",GetMail}				//メールアドレス
									,{"Com01"				,"1","1",GetCom01}				//コメント1
									,{"Com02"				,"1","1",GetCom02}				//コメント2
									,{"Com03"				,"1","1",GetCom03}				//コメント3
									,{"EntryDate"			,"1","0",now_dtm}				//データ登録日時
									,{"UpdateDate"			,"1","1",now_dtm}				//データ更新日時
									,{"EntryUser"			,"1","0","(" + A00000Main.LoginUserId + ")" + A00000Main.LoginUserName}	//登録者コード
									,{"UpdateUser"			,"1","1","(" + A00000Main.LoginUserId + ")" + A00000Main.LoginUserName}	//更新者コード
									,{"PTMSCD"				,"1","1",GetPTMSCD}				//基幹システムユーザーコード
									,{"DelFg"				,"1","1",GetDelFg}				//削除区分
									,{"MainClient"			,"1","1",GetMainClient}			//主要担当荷主CD
							};
							
							String tgt_table = "KM0020_USERMST";
							String[][] field_name = new String[SetString.length][3];
							String[][] entry_data = new String[1][SetString.length];
							String[] judg_field = new String[3];
							String[][] judg_data = new String[1][3];
							String TgtDB = "NYANKO";
							int non_msg_fg = 1;
	
							judg_field[0] = "WHCD";						//倉庫コード
							judg_field[1] = "ShippingCompanyCd";		//運送会社CD
							judg_field[2] = "UserCd";					//ユーザーCD

							judg_data[0][0] = GetWHCD;					//倉庫コード
							judg_data[0][1] = GetShippingCompanyCd;		//運送会社CD
							judg_data[0][2] = GetUserCd;				//ユーザーCD
							
							for(int i=0;i<SetString.length;i++) {
								field_name[i][0] = SetString[i][0];
								field_name[i][1] = SetString[i][1];
								field_name[i][2] = SetString[i][2];
								entry_data[0][i] = SetString[i][3];
							}
							
							A00020InsertUdateSQL.RUN_SQLS_EU(tgt_table, field_name, entry_data, judg_field, judg_data, non_msg_fg,TgtDB);
							
							SetX=main_fm.getX();
							SetY=main_fm.getY();
			
							main_fm.setVisible(false);
							main_fm.dispose();
							UserMstRenewAndCreate(0,0,GetWHCD,GetShippingCompanyCd,GetUserCd);
						}
						RenewFg = true;
					}
				}
			}
		});
		
		//郵便番号フォーカス消失時の挙動
		TB_Post.addFocusListener(new FocusAdapter(){
			@Override
			public void focusLost(FocusEvent e){
				if(RenewFg) {
					RenewFg = false;
					String GetPost = TB_Post.getText();	if(null==GetPost) {GetPost="";}
					GetPost = B00020ToolsTextControl.Trim(B00020ToolsTextControl.num_only_String(GetPost));
					TB_Add01.setText("");
					TB_Add02.setText("");
					TB_Add03.setText("");
					
					ArrayList<String> SearchPOST = new ArrayList<String>();
					ArrayList<String> SearchAdd = new ArrayList<String>();
					boolean AllSearch = false;
					
					SearchPOST.add(GetPost);
					
					Object[][] PostRt = M10010PostMstRt.PostRt(
								SearchPOST,
								SearchAdd,
								AllSearch);
					
					if(0<PostRt.length) {
						TB_Add01.setText(""+PostRt[0][1]+PostRt[0][2]);
						TB_Add02.setText(""+PostRt[0][3]);
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
				WM00030UserMstSearch.UserMstSearch(0, 0);
			}
		});
	}
}