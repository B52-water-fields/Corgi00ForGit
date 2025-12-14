import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class WM00031UserMstRenewAndCreate{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	public static void UserMstRenewAndCreate(int x,int y,String TgtWhCd,String TgtShippingCompanyCd,String TgtUserCd) {
		RenewFg = false;
		
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
		
		JLabel LB_cl_cd			= B00110FrameParts.JLabelSet(  0, 40,100,20,"荷主CD:",			11,1);
		
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
		final JComboBox  TB_MainClient			= B00110FrameParts.JComboBoxSet( 100,115,250,20,B00100DefaultVariable.SearchClList[0],	11);			//主要担当荷主
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
		
		for(int i=0;i<B00100DefaultVariable.SearchClList[1].length;i++){
			if((""+B00100DefaultVariable.SearchClList[1][i]).equals(""+A00000Main.ClCd)){
				TB_MainClient.setSelectedIndex(i);
			}
		}
		
		
		
		if(!"".equals(TgtWhCd)&&!"".equals(TgtShippingCompanyCd)&&!"".equals(TgtUserCd)) {
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
				
				for(int i=0;i<B00100DefaultVariable.SearchClList[1].length;i++){
					if((""+B00100DefaultVariable.SearchClList[1][i]).equals(""+UserMstRt[0][30])){
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
		
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		main_fm.add(entry_btn);
		
		main_fm.setVisible(true);
		RenewFg = true;
		
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