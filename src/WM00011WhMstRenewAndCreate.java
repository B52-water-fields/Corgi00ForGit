import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class WM00011WhMstRenewAndCreate{
	static int SetX;
	static int SetY;
	public static void WhMstRenewAndCreate(int x,int y,String TgtWhCd){
		if(null==TgtWhCd) {TgtWhCd="";}
		A00000Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		
		final JFrame main_fm = B00110FrameParts.FrameCreate(x,y,500,600,"Corgi00倉庫（事業所）登録・修正","");
		JLabel userinfo = B00110FrameParts.UserInfo();
		JButton exit_btn = B00110FrameParts.ExitBtn();
		JButton entry_btn = B00110FrameParts.EntryBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		main_fm.add(entry_btn);

		JLabel LB_WHCD  		= B00110FrameParts.JLabelSet(  0, 40,100,20,"倉庫コード:"			,11,1);
		JLabel LB_WHName  		= B00110FrameParts.JLabelSet(  0, 65,100,20,"拠点倉庫名:"			,11,1);
		JLabel LB_Post  		= B00110FrameParts.JLabelSet(  0, 90,100,20,"拠点倉庫郵便番号:"	,11,1);
		JLabel LB_Add01  		= B00110FrameParts.JLabelSet(  0,115,100,20,"拠点倉庫住所1:"		,11,1);
		JLabel LB_Add02  		= B00110FrameParts.JLabelSet(  0,140,100,20,"拠点倉庫住所2:"		,11,1);
		JLabel LB_Tel 			= B00110FrameParts.JLabelSet(  0,165,100,20,"拠点倉庫電話:"		,11,1);
		JLabel LB_Fax  			= B00110FrameParts.JLabelSet(  0,190,100,20,"拠点倉庫FAX:"			,11,1);
		JLabel LB_Mail  		= B00110FrameParts.JLabelSet(  0,215,100,20,"拠点倉庫MAIL:"		,11,1);
		JLabel LB_Com01  		= B00110FrameParts.JLabelSet(  0,240,100,20,"コメント１:"			,11,1);
		JLabel LB_Com02  		= B00110FrameParts.JLabelSet(  0,265,100,20,"コメント２:"			,11,1);
		JLabel LB_Com03  		= B00110FrameParts.JLabelSet(  0,290,100,20,"コメント３:"			,11,1);
		JLabel LB_PTMSCD  		= B00110FrameParts.JLabelSet(  0,315,100,20,"基幹SysCD:"			,11,1);
		JLabel LB_EntryDate  	= B00110FrameParts.JLabelSet(  0,340,100,20,"データ登録日時:"		,11,1);
		JLabel LB_UpdateDate  	= B00110FrameParts.JLabelSet(  0,365,100,20,"データ更新日時:"		,11,1);
		JLabel LB_EntryUser  	= B00110FrameParts.JLabelSet(  0,390,100,20,"登録者:"				,11,1);
		JLabel LB_UpdateUser  	= B00110FrameParts.JLabelSet(  0,415,100,20,"更新者:"				,11,1);
		
		final JTextField TB_WHCD  		= B00110FrameParts.JTextFieldSet(100, 40,100,20,"",11,0);	//倉庫コード
		final JTextField TB_WHName  	= B00110FrameParts.JTextFieldSet(100, 65,200,20,"",11,0);	//拠点倉庫名
		final JTextField TB_Post  		= B00110FrameParts.JTextFieldSet(100, 90,100,20,"",11,0);	//拠点倉庫郵便番号
		final JTextField TB_Add01  		= B00110FrameParts.JTextFieldSet(100,115,250,20,"",11,0);	//拠点倉庫住所1
		final JTextField TB_Add02  		= B00110FrameParts.JTextFieldSet(100,140,250,20,"",11,0);	//拠点倉庫住所2
		final JTextField TB_Tel 		= B00110FrameParts.JTextFieldSet(100,165,100,20,"",11,0);	//拠点倉庫電話
		final JTextField TB_Fax  		= B00110FrameParts.JTextFieldSet(100,190,100,20,"",11,0);	//拠点倉庫FAX
		final JTextField TB_Mail 		= B00110FrameParts.JTextFieldSet(100,215,100,20,"",11,0);	//拠点倉庫MAIL
		final JTextField TB_Com01  		= B00110FrameParts.JTextFieldSet(100,240,250,20,"",11,0);	//コメント１
		final JTextField TB_Com02  		= B00110FrameParts.JTextFieldSet(100,265,250,20,"",11,0);	//コメント２
		final JTextField TB_Com03  		= B00110FrameParts.JTextFieldSet(100,290,250,20,"",11,0);	//コメント３
		final JTextField TB_PTMSCD 		= B00110FrameParts.JTextFieldSet(100,315,100,20,"",11,0);	//基幹SysCD
		final JTextField TB_EntryDate  	= B00110FrameParts.JTextFieldSet(100,340,200,20,"",11,0);	//データ登録日時
		final JTextField TB_UpdateDate  = B00110FrameParts.JTextFieldSet(100,365,200,20,"",11,0);	//データ更新日時
		final JTextField TB_EntryUser  	= B00110FrameParts.JTextFieldSet(100,390,200,20,"",11,0);	//登録者
		final JTextField TB_UpdateUser  = B00110FrameParts.JTextFieldSet(100,415,200,20,"",11,0);	//更新者
		
		TB_EntryDate.setEditable(false);	//データ登録日時
		TB_UpdateDate.setEditable(false);	//データ更新日時
		TB_EntryUser.setEditable(false);	//登録者
		TB_UpdateUser.setEditable(false);	//更新者
		
		if(!"".equals(TgtWhCd)) {
			ArrayList<String> SearchWHCD = new ArrayList<String>();
			ArrayList<String> SearchWHName = new ArrayList<String>();
			ArrayList<String> SearchPost = new ArrayList<String>();
			ArrayList<String> SearchAdd = new ArrayList<String>();
			ArrayList<String> SearchTel = new ArrayList<String>();
			ArrayList<String> SearchFax = new ArrayList<String>();
			ArrayList<String> SearchMail = new ArrayList<String>();
			ArrayList<String> SearchCom = new ArrayList<String>();
			ArrayList<String> SearchPTMSCD = new ArrayList<String>();
			boolean AllSearch = false;
			
			SearchWHCD.add(TgtWhCd);
			
			Object[][] WhMstRt = M00001WhMstRt.WhMstRt(
					SearchWHCD,SearchWHName,SearchPost,
					SearchAdd,SearchTel,SearchFax,SearchMail,
					SearchCom,SearchPTMSCD,
					AllSearch);
			
			if(0<WhMstRt.length) {
				TB_WHCD.setText(		""+WhMstRt[0][ 0]);	//倉庫コード
				TB_WHName .setText(		""+WhMstRt[0][ 1]);	//拠点倉庫名
				TB_Post.setText(		""+WhMstRt[0][ 2]);	//拠点倉庫郵便番号
				TB_Add01.setText(		""+WhMstRt[0][ 3]);	//拠点倉庫住所1
				TB_Add02.setText(		""+WhMstRt[0][ 4]);	//拠点倉庫住所2
				TB_Tel.setText(			""+WhMstRt[0][ 5]);	//拠点倉庫電話
				TB_Fax.setText(			""+WhMstRt[0][ 6]);	//拠点倉庫FAX
				TB_Mail.setText(		""+WhMstRt[0][ 7]);	//拠点倉庫MAIL
				TB_Com01.setText(		""+WhMstRt[0][ 8]);	//コメント１
				TB_Com02.setText(		""+WhMstRt[0][ 9]);	//コメント２
				TB_Com03.setText(		""+WhMstRt[0][10]);	//コメント３
				TB_PTMSCD.setText(		""+WhMstRt[0][11]);	//基幹SysCD
				TB_EntryDate.setText(	""+WhMstRt[0][12]);	//データ登録日時
				TB_UpdateDate.setText(	""+WhMstRt[0][13]);	//データ更新日時
				TB_EntryUser.setText(	""+WhMstRt[0][14]);	//登録者
				TB_UpdateUser.setText(	""+WhMstRt[0][15]);	//更新者
			}
		}
		main_fm.add(LB_WHCD);
		main_fm.add(LB_WHName);
		main_fm.add(LB_Post);
		main_fm.add(LB_Add01);
		main_fm.add(LB_Add02);
		main_fm.add(LB_Tel);
		main_fm.add(LB_Fax);
		main_fm.add(LB_Mail);
		main_fm.add(LB_Com01);
		main_fm.add(LB_Com02);
		main_fm.add(LB_Com03);
		main_fm.add(LB_PTMSCD);
		main_fm.add(LB_EntryDate);
		main_fm.add(LB_UpdateDate);
		main_fm.add(LB_EntryUser);
		main_fm.add(LB_UpdateUser);
		
		main_fm.add(TB_WHCD);		//倉庫コード
		main_fm.add(TB_WHName);		//拠点倉庫名
		main_fm.add(TB_Post);		//拠点倉庫郵便番号
		main_fm.add(TB_Add01);		//拠点倉庫住所1
		main_fm.add(TB_Add02);		//拠点倉庫住所2
		main_fm.add(TB_Tel);		//拠点倉庫電話
		main_fm.add(TB_Fax);		//拠点倉庫FAX
		main_fm.add(TB_Mail);		//拠点倉庫MAIL
		main_fm.add(TB_Com01);		//コメント１
		main_fm.add(TB_Com02);		//コメント２
		main_fm.add(TB_Com03);		//コメント３
		main_fm.add(TB_PTMSCD);		//基幹SysCD
		main_fm.add(TB_EntryDate);	//データ登録日時
		main_fm.add(TB_UpdateDate);	//データ更新日時
		main_fm.add(TB_EntryUser);	//登録者
		main_fm.add(TB_UpdateUser);	//更新者

		TB_WHName.requestFocusInWindow();
		main_fm.setVisible(true);
		
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
						
						TB_Add01.setText(""+PostRt[0][1]+PostRt[0][2]);
						TB_Add02.setText(""+PostRt[0][3]);
					}
				}
			}
		});
		
		//登録ボタン押下時の挙動
		entry_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				String GetWHCD  = TB_WHCD.getText();	if(null==GetWHCD){GetWHCD = "";}		//倉庫コード
				String GetWHName = TB_WHName.getText();	if(null==GetWHName){GetWHName = "";}	//拠点倉庫名
				String GetPost = TB_Post.getText();		if(null==GetPost){GetPost = "";}		//拠点倉庫郵便番号
				String GetAdd01 = TB_Add01.getText();	if(null==GetAdd01){GetAdd01 = "";}		//拠点倉庫住所1
				String GetAdd02 = TB_Add02.getText();	if(null==GetAdd02){GetAdd02 = "";}		//拠点倉庫住所2
				String GetTel = TB_Tel.getText();		if(null==GetTel){GetTel = "";}			//拠点倉庫電話
				String GetFax = TB_Fax.getText();		if(null==GetFax){GetFax = "";}			//拠点倉庫FAX
				String GetMail = TB_Mail.getText();		if(null==GetMail){GetMail = "";}		//拠点倉庫MAIL
				String GetCom01 = TB_Com01.getText();	if(null==GetCom01){GetCom01 = "";}		//コメント１
				String GetCom02 = TB_Com02.getText();	if(null==GetCom02){GetCom02 = "";}		//コメント２
				String GetCom03 = TB_Com03.getText();	if(null==GetCom03){GetCom03 = "";}		//コメント３
				String GetPTMSCD = TB_PTMSCD.getText();	if(null==GetPTMSCD){GetPTMSCD = "";}	//基幹SysCD
				
				GetWHCD	= B00020ToolsTextControl.only1byte_String(GetWHCD);
				
				GetPost = B00020ToolsTextControl.num_only_String(GetPost);
				GetTel  = B00020ToolsTextControl.num_only_String(GetTel);
				GetFax  = B00020ToolsTextControl.num_only_String(GetFax);
				
				String SetWh = B00101DefaultVariableWarehouse.RenewAndCreateWh(
						GetWHCD,	//倉庫コード
						GetWHName,	//拠点倉庫名
						GetPost,	//拠点倉庫郵便番号
						GetAdd01,	//拠点倉庫住所1
						GetAdd02,	//拠点倉庫住所2
						GetTel,		//拠点倉庫電話
						GetFax,		//拠点倉庫FAX
						GetMail,	//拠点倉庫MAIL
						GetCom01,	//コメント１
						GetCom02,	//コメント２
						GetCom03,	//コメント３
						GetPTMSCD	//基幹SysCD
						);
				
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				WhMstRenewAndCreate(0,0,SetWh);
			}
		});
		
		//EXITボタン押下時の挙動
		exit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				WM00010WhMstSearch.WhMstSearch(0, 0);
			}
		});
	}
}