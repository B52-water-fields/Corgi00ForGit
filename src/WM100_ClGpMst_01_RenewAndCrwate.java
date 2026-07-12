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

public class WM100_ClGpMst_01_RenewAndCrwate{
	static int SetX;
	static int SetY;
	public static void ClGpMstRenewAndCrwate(int x,int y,String TgtClGpCd) {
		if(null==TgtClGpCd) {TgtClGpCd="";}
		A00000_Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		
		final JFrame main_fm = B100_FrameParts.FrameCreate(x,y,500,625,"Corgi00荷主グループ登録・修正","");
		JLabel userinfo = B100_FrameParts.UserInfo();
		JButton exit_btn = B100_FrameParts.ExitBtn();
		JButton entry_btn = B100_FrameParts.EntryBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		main_fm.add(entry_btn);
		
		JLabel LB_ClGpCD		 = B100_FrameParts.JLabelSet(  0, 40,100,20,"荷主グループCD:"	,11,1);
		JLabel LB_CLGpName01	 = B100_FrameParts.JLabelSet(  0, 65,100,20,"グループ表記名:"	,11,1);
		JLabel LB_CLGpName02	 = B100_FrameParts.JLabelSet(  0, 90,100,20,"グループ正式名:"	,11,1);
		JLabel LB_CLGpName03	 = B100_FrameParts.JLabelSet(  0,115,100,20,"グループ略名:"	,11,1);
		JLabel LB_Post			 = B100_FrameParts.JLabelSet(  0,140,100,20,"郵便番号:"		,11,1);
		JLabel LB_Add01			 = B100_FrameParts.JLabelSet(  0,165,100,20,"住所1:"			,11,1);
		JLabel LB_Add02			 = B100_FrameParts.JLabelSet(  0,190,100,20,"住所2:"			,11,1);
		JLabel LB_Add03			 = B100_FrameParts.JLabelSet(  0,215,100,20,"住所3:"			,11,1);
		JLabel LB_Tel			 = B100_FrameParts.JLabelSet(  0,240,100,20,"電話番号:"		,11,1);
		JLabel LB_Fax			 = B100_FrameParts.JLabelSet(  0,265,100,20,"FAX:"			,11,1);
		JLabel LB_Mail			 = B100_FrameParts.JLabelSet(  0,290,100,20,"メールアドレス:"	,11,1);
		JLabel LB_Com01			 = B100_FrameParts.JLabelSet(  0,315,100,20,"コメント1:"		,11,1);
		JLabel LB_Com02			 = B100_FrameParts.JLabelSet(  0,340,100,20,"コメント2:"		,11,1);
		JLabel LB_Com03			 = B100_FrameParts.JLabelSet(  0,365,100,20,"コメント3:"		,11,1);
		JLabel LB_EntryDate		 = B100_FrameParts.JLabelSet(  0,390,100,20,"データ登録日時:"	,11,1);
		JLabel LB_UpdateDate	 = B100_FrameParts.JLabelSet(  0,415,100,20,"データ更新日時:"	,11,1);
		JLabel LB_EntryUser		 = B100_FrameParts.JLabelSet(  0,440,100,20,"登録者コード:"	,11,1);
		JLabel LB_UpdateUser	 = B100_FrameParts.JLabelSet(  0,465,100,20,"更新者コード:"	,11,1);
		JLabel LB_PassWord		 = B100_FrameParts.JLabelSet(  0,490,100,20,"パスワード:"		,11,1);
		
		final JTextField TB_ClGpCD		 = B100_FrameParts.JTextFieldSet(100, 40,100,20,"",11,0);	//荷主グループCD
		final JTextField TB_CLGpName01	 = B100_FrameParts.JTextFieldSet(100, 65,200,20,"",11,0);	//荷主表記名
		final JTextField TB_CLGpName02	 = B100_FrameParts.JTextFieldSet(100, 90,200,20,"",11,0);	//荷主正式名
		final JTextField TB_CLGpName03	 = B100_FrameParts.JTextFieldSet(100,115,200,20,"",11,0);	//荷主略名
		final JTextField TB_Post		 = B100_FrameParts.JTextFieldSet(100,140,100,20,"",11,0);	//郵便番号
		final JTextField TB_Add01		 = B100_FrameParts.JTextFieldSet(100,165,250,20,"",11,0);	//住所1
		final JTextField TB_Add02		 = B100_FrameParts.JTextFieldSet(100,190,250,20,"",11,0);	//住所2
		final JTextField TB_Add03		 = B100_FrameParts.JTextFieldSet(100,215,250,20,"",11,0);	//住所3
		final JTextField TB_Tel			 = B100_FrameParts.JTextFieldSet(100,240,100,20,"",11,0);	//電話番号
		final JTextField TB_Fax			 = B100_FrameParts.JTextFieldSet(100,265,100,20,"",11,0);	//FAX
		final JTextField TB_Mail		 = B100_FrameParts.JTextFieldSet(100,290,200,20,"",11,0);	//メールアドレス
		final JTextField TB_Com01		 = B100_FrameParts.JTextFieldSet(100,315,250,20,"",11,0);	//コメント1
		final JTextField TB_Com02		 = B100_FrameParts.JTextFieldSet(100,340,250,20,"",11,0);	//コメント2
		final JTextField TB_Com03		 = B100_FrameParts.JTextFieldSet(100,365,250,20,"",11,0);	//コメント3
		final JTextField TB_EntryDate	 = B100_FrameParts.JTextFieldSet(100,390,250,20,"",11,0);	//データ登録日時
		final JTextField TB_UpdateDate	 = B100_FrameParts.JTextFieldSet(100,415,250,20,"",11,0);	//データ更新日時
		final JTextField TB_EntryUser	 = B100_FrameParts.JTextFieldSet(100,440,250,20,"",11,0);	//登録者コード
		final JTextField TB_UpdateUser	 = B100_FrameParts.JTextFieldSet(100,465,250,20,"",11,0);	//更新者コード
		final JTextField TB_PassWord	 = B100_FrameParts.JTextFieldSet(100,490,100,20,"",11,0);	//パスワード
		
		TB_ClGpCD.setEnabled(false);
		TB_EntryDate.setEditable(false);	//データ登録日時
		TB_UpdateDate.setEditable(false);	//データ更新日時
		TB_EntryUser.setEditable(false);	//登録者
		TB_UpdateUser.setEditable(false);	//更新者
		
		main_fm.add(LB_ClGpCD);
		main_fm.add(LB_CLGpName01);
		main_fm.add(LB_CLGpName02);
		main_fm.add(LB_CLGpName03);
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
		main_fm.add(LB_PassWord);
		
		main_fm.add(TB_ClGpCD);
		main_fm.add(TB_CLGpName01);
		main_fm.add(TB_CLGpName02);
		main_fm.add(TB_CLGpName03);
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
		main_fm.add(TB_PassWord);
		
		if(!"".equals(TgtClGpCd)) {
			ArrayList<String> SearchClGpCD = new ArrayList<String>();
			ArrayList<String> SearchCLGpName = new ArrayList<String>();
			ArrayList<String> SearchPost = new ArrayList<String>();
			ArrayList<String> SearchAdd = new ArrayList<String>();
			ArrayList<String> SearchTel = new ArrayList<String>();
			ArrayList<String> SearchFax = new ArrayList<String>();
			ArrayList<String> SearchMail = new ArrayList<String>();
			ArrayList<String> SearchCom = new ArrayList<String>();
			boolean AllSearch = false;
			
			SearchClGpCD.add(TgtClGpCd);
			
			Object[][] ClGpMstRt = M100_ClGpMstRt.ClGpMstRt(
						SearchClGpCD,SearchCLGpName, SearchPost,
						SearchAdd,SearchTel,SearchFax,SearchMail,SearchCom,AllSearch);
			
			if(0<ClGpMstRt.length) {
				TB_ClGpCD.setText(		""+ClGpMstRt[0][M100_ClGpMstRt.ColClGpCD]);		//荷主グループCD
				TB_CLGpName01.setText(	""+ClGpMstRt[0][M100_ClGpMstRt.ColCLGpName01]);	//荷主表記名
				TB_CLGpName02.setText(	""+ClGpMstRt[0][M100_ClGpMstRt.ColCLGpName02]);	//荷主正式名
				TB_CLGpName03.setText(	""+ClGpMstRt[0][M100_ClGpMstRt.ColCLGpName03]);	//荷主略名
				TB_Post.setText(		""+ClGpMstRt[0][M100_ClGpMstRt.ColPost]);			//郵便番号
				TB_Add01.setText(		""+ClGpMstRt[0][M100_ClGpMstRt.ColAdd01]);			//住所1
				TB_Add02.setText(		""+ClGpMstRt[0][M100_ClGpMstRt.ColAdd02]);			//住所2
				TB_Add03.setText(		""+ClGpMstRt[0][M100_ClGpMstRt.ColAdd03]);			//住所3
				TB_Tel.setText(			""+ClGpMstRt[0][M100_ClGpMstRt.ColTel]);			//電話番号
				TB_Fax.setText(			""+ClGpMstRt[0][M100_ClGpMstRt.ColFax]);			//FAX
				TB_Mail.setText(		""+ClGpMstRt[0][M100_ClGpMstRt.ColMail]);			//メールアドレス
				TB_Com01.setText(		""+ClGpMstRt[0][M100_ClGpMstRt.ColCom01]);			//コメント1
				TB_Com02.setText(		""+ClGpMstRt[0][M100_ClGpMstRt.ColCom02]);			//コメント2
				TB_Com03.setText(		""+ClGpMstRt[0][M100_ClGpMstRt.ColCom03]);			//コメント3
				TB_EntryDate.setText(	""+ClGpMstRt[0][M100_ClGpMstRt.ColEntryDate]);	//データ登録日時
				TB_UpdateDate.setText(	""+ClGpMstRt[0][M100_ClGpMstRt.ColUpdateDate]);	//データ更新日時
				TB_EntryUser.setText(	""+ClGpMstRt[0][M100_ClGpMstRt.ColEntryUser]);	//登録者コード
				TB_UpdateUser.setText(	""+ClGpMstRt[0][M100_ClGpMstRt.ColUpdateUser]);	//更新者コード
				TB_PassWord.setText(	""+ClGpMstRt[0][M100_ClGpMstRt.ColPassWord]);		//パスワード
			}
		}
		
		main_fm.setVisible(true);
		
		//郵便番号フォーカス消失時の挙動
		TB_Post.addFocusListener(new FocusAdapter(){
			@Override
			public void focusLost(FocusEvent e){
				String GetPost = TB_Post.getText();	if(null==GetPost) {GetPost="";}
				GetPost = B100_TextControl.Trim(B100_TextControl.num_only_String(GetPost));
				TB_Post.setText(GetPost);
				
				ArrayList<String> SearchPOST = new ArrayList<String>();
				ArrayList<String> SearchAdd = new ArrayList<String>();
				boolean AllSearch = false;
				boolean PostPerfectMatch = true;
				
				if(!"".equals(GetPost)) {
					SearchPOST.add(GetPost);
				}
				
				Object[][] PostRt = M100_PostMstRt.PostRt(
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
		
		//登録ボタン押下時の挙動
		entry_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				String GetClGpCD		  = TB_ClGpCD.getText();		if(null==GetClGpCD){GetClGpCD = "";}				//荷主グループCD
				String GetCLGpName01	  = TB_CLGpName01.getText();	if(null==GetCLGpName01){GetCLGpName01 = "";}		//荷主表記名
				String GetCLGpName02	  = TB_CLGpName02.getText();	if(null==GetCLGpName02){GetCLGpName02 = "";}		//荷主正式名
				String GetCLGpName03	  = TB_CLGpName03.getText();	if(null==GetCLGpName03){GetCLGpName03 = "";}		//荷主略名
				String GetPost			  = TB_Post.getText();			if(null==GetPost){GetPost = "";}					//郵便番号
				String GetAdd01			  = TB_Add01.getText();			if(null==GetAdd01){GetAdd01 = "";}					//住所1
				String GetAdd02			  = TB_Add02.getText();			if(null==GetAdd02){GetAdd02 = "";}					//住所2
				String GetAdd03			  = TB_Add03.getText();			if(null==GetAdd03){GetAdd03 = "";}					//住所3
				String GetTel			  = TB_Tel.getText();			if(null==GetTel){GetTel = "";}						//電話番号
				String GetFax			  = TB_Fax.getText();			if(null==GetFax){GetFax = "";}						//FAX
				String GetMail			  = TB_Mail.getText();			if(null==GetMail){GetMail = "";}					//メールアドレス
				String GetCom01			  = TB_Com01.getText();			if(null==GetCom01){GetCom01 = "";}					//コメント1
				String GetCom02			  = TB_Com02.getText();			if(null==GetCom02){GetCom02 = "";}					//コメント2
				String GetCom03			  = TB_Com03.getText();			if(null==GetCom03){GetCom03 = "";}					//コメント3
				String GetPassWord		  = TB_PassWord.getText();		if(null==GetPassWord){GetPassWord = "";}			//パスワード
				
				GetClGpCD = B100_TextControl.Trim(GetClGpCD);
				GetCLGpName01 = B100_TextControl.Trim(GetCLGpName01);
				GetCLGpName02 = B100_TextControl.Trim(GetCLGpName02);
				GetCLGpName03 = B100_TextControl.Trim(GetCLGpName03);
				GetPost = B100_TextControl.Trim(GetPost);
				GetAdd01 = B100_TextControl.Trim(GetAdd01);
				GetAdd02 = B100_TextControl.Trim(GetAdd02);
				GetAdd03 = B100_TextControl.Trim(GetAdd03);
				GetTel = B100_TextControl.Trim(GetTel);
				GetFax = B100_TextControl.Trim(GetFax);
				GetMail = B100_TextControl.Trim(GetMail);
				GetCom01 = B100_TextControl.Trim(GetCom01);
				GetCom02 = B100_TextControl.Trim(GetCom02);
				GetCom03 = B100_TextControl.Trim(GetCom03);
				GetPassWord	 = B100_TextControl.Trim(GetPassWord);
	
				GetPost = B100_TextControl.num_only_String(GetPost);
				GetTel  = B100_TextControl.num_only_String(GetTel);
				GetFax  = B100_TextControl.num_only_String(GetFax);
				
				if(!"".equals(GetCLGpName01)) {
					if("".equals(GetClGpCD)) {
						GetClGpCD = M100_ClGpMstRt.NewWhCdGet(1)[0];
					}
					
					String now_dtm = B100_DateTimeControl.dtmString2(B100_DateTimeControl.dtm()[1])[1];
					
					String[][] SetString = {
							{"ClGpCD"		,"1","1",GetClGpCD}			//荷主グループCD
							,{"CLGpName01"	,"1","1",GetCLGpName01}		//荷主表記名
							,{"CLGpName02"	,"1","1",GetCLGpName02}		//荷主正式名
							,{"CLGpName03"	,"1","1",GetCLGpName03}		//荷主略名"
							,{"Post"		,"1","1",GetPost}			//郵便番号
							,{"Add01"		,"1","1",GetAdd01}			//住所1
							,{"Add02"		,"1","1",GetAdd02}			//住所2
							,{"Add03"		,"1","1",GetAdd03}			//住所3
							,{"Tel"			,"1","1",GetTel}			//電話番号
							,{"Fax"			,"1","1",GetFax}			//FAX
							,{"Mail"		,"1","1",GetMail}			//メールアドレス
							,{"Com01"		,"1","1",GetCom01}			//コメント1
							,{"Com02"		,"1","1",GetCom02}			//コメント2
							,{"Com03"		,"1","1",GetCom03}			//コメント3
							,{"EntryDate"	,"1","0",now_dtm}			//データ登録日時
							,{"UpdateDate"	,"1","1",now_dtm}			//データ更新日時
							,{"EntryUser"	,"1","0","(" + A00000_Main.LoginUserId + ")" + A00000_Main.LoginUserName}		//登録者コード
							,{"UpdateUser"	,"1","1","(" + A00000_Main.LoginUserId + ")" + A00000_Main.LoginUserName}	//更新者コード
							,{"PassWord"	,"1","1",GetPassWord}		//パスワード

					};
					
					String tgt_table = "KM0031_CLIENT_GROUP";
					String[][] field_name = new String[SetString.length][3];
					String[][] entry_data = new String[1][SetString.length];
					String[] judg_field = new String[1];
					String[][] judg_data = new String[1][1];
					String TgtDB = "NYANKO";
					int non_msg_fg = 1;
					

					judg_field[ 0] = "ClGpCD";		//荷主グループCD
					judg_data[0][ 0] = GetClGpCD;	//荷主グループCD
					
					for(int i=0;i<SetString.length;i++) {
						field_name[i][0] = SetString[i][0];
						field_name[i][1] = SetString[i][1];
						field_name[i][2] = SetString[i][2];
						entry_data[0][i] = SetString[i][3];
					}

					A100_InsertUpdateSQL.RUN_SQLS_EU(tgt_table, field_name, entry_data, judg_field, judg_data, non_msg_fg,TgtDB);
					
					//荷主グループ一覧更新
					B100_DefaultVariable.ClGpList();
					
					SetX=main_fm.getX();
					SetY=main_fm.getY();
	
					main_fm.setVisible(false);
					main_fm.dispose();
					ClGpMstRenewAndCrwate(0,0,GetClGpCD);
					
				}else {
					JOptionPane.showMessageDialog(null, "荷主グループ名は必要ですよ");
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
				WM100_ClGlpMst_00_Search.ClGlpMstSearch(0, 0);
			}
		});
	}
}