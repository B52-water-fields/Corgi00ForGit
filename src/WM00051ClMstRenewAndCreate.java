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

public class WM00051ClMstRenewAndCreate{
	static int SetX;
	static int SetY;
	public static void ClMstRenewAndCreate(int x,int y,String TgtClCd) {
		if(null==TgtClCd) {TgtClCd="";}
		A00000Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		
		final JFrame main_fm = B00110FrameParts.FrameCreate(x,y,500,700,"Corgi00荷主登録・修正","");
		JLabel userinfo = B00110FrameParts.UserInfo();
		JButton exit_btn = B00110FrameParts.ExitBtn();
		JButton entry_btn = B00110FrameParts.EntryBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		main_fm.add(entry_btn);
		
		JLabel LB_cl_cd			= B00110FrameParts.JLabelSet(  0, 40,100,20,"荷主CD:",				11,1);
		JLabel LB_ClGpCD		= B00110FrameParts.JLabelSet(  0, 65,100,20,"荷主グループCD:",		11,1);
		JLabel LB_WHCD			= B00110FrameParts.JLabelSet(  0, 90,100,20,"担当倉庫:",			11,1);
		JLabel LB_CLName01		= B00110FrameParts.JLabelSet(  0,115,100,20,"荷主名1:",			11,1);
		JLabel LB_CLName02		= B00110FrameParts.JLabelSet(  0,140,100,20,"荷主名2:",			11,1);
		JLabel LB_CLName03		= B00110FrameParts.JLabelSet(  0,165,100,20,"荷主名3:",			11,1);
		JLabel LB_Post			= B00110FrameParts.JLabelSet(  0,190,100,20,"郵便番号:",			11,1);
		JLabel LB_Add01			= B00110FrameParts.JLabelSet(  0,215,100,20,"住所1:",				11,1);
		JLabel LB_Add02			= B00110FrameParts.JLabelSet(  0,240,100,20,"住所2:",				11,1);
		JLabel LB_Add03			= B00110FrameParts.JLabelSet(  0,265,100,20,"住所3:",				11,1);
		JLabel LB_Tel			= B00110FrameParts.JLabelSet(  0,290,100,20,"電話番号:",			11,1);
		JLabel LB_Fax			= B00110FrameParts.JLabelSet(  0,315,100,20,"FAX:",				11,1);
		JLabel LB_Mail			= B00110FrameParts.JLabelSet(  0,340,100,20,"メールアドレス:",		11,1);
		JLabel LB_Com01			= B00110FrameParts.JLabelSet(  0,365,100,20,"コメント1:",			11,1);
		JLabel LB_Com02			= B00110FrameParts.JLabelSet(  0,390,100,20,"コメント2:",			11,1);
		JLabel LB_Com03			= B00110FrameParts.JLabelSet(  0,415,100,20,"コメント3:",			11,1);
		JLabel LB_ShimeDate		= B00110FrameParts.JLabelSet(  0,440,100,20,"運賃締日:",			11,1);
		JLabel LB_ShimeBasis	= B00110FrameParts.JLabelSet(  0,465,100,20,"請求基準:",			11,1);
		JLabel LB_EntryDate		= B00110FrameParts.JLabelSet(  0,490,100,20,"データ登録日時:",		11,1);
		JLabel LB_UpdateDate	= B00110FrameParts.JLabelSet(  0,515,100,20,"データ更新日時:",		11,1);
		JLabel LB_EntryUser		= B00110FrameParts.JLabelSet(  0,540,100,20,"登録者コード:",		11,1);
		JLabel LB_UpdateUser	= B00110FrameParts.JLabelSet(  0,565,100,20,"更新者コード:",		11,1);
		JLabel LB_PTMSCD		= B00110FrameParts.JLabelSet(  0,590,100,20,"基幹SYS荷主CD:",		11,1);
		
		final JTextField TB_cl_cd		 = B00110FrameParts.JTextFieldSet(100, 40,100,20,"",11,0);	//荷主CD
		final JComboBox  TB_ClGpCD	  	 = B00110FrameParts.JComboBoxSet( 100, 65,250,20,B00100DefaultVariable.ClGpList[0],	11);		//荷主グループCD
		final JComboBox  TB_WHCD	  	 = B00110FrameParts.JComboBoxSet( 100, 90,250,20,B00100DefaultVariable.WhList[0],	11);		//担当倉庫
		final JTextField  TB_CLName01	 = B00110FrameParts.JTextFieldSet(100,115,200,20,"",11,0);	//荷主名1
		final JTextField  TB_CLName02	 = B00110FrameParts.JTextFieldSet(100,140,200,20,"",11,0);	//荷主名2
		final JTextField  TB_CLName03	 = B00110FrameParts.JTextFieldSet(100,165,200,20,"",11,0);	//荷主名3
		final JTextField  TB_Post		 = B00110FrameParts.JTextFieldSet(100,190,100,20,"",11,0);	//郵便番号
		final JTextField  TB_Add01		 = B00110FrameParts.JTextFieldSet(100,215,250,20,"",11,0);	//住所1
		final JTextField  TB_Add02		 = B00110FrameParts.JTextFieldSet(100,240,250,20,"",11,0);	//住所2
		final JTextField  TB_Add03		 = B00110FrameParts.JTextFieldSet(100,265,250,20,"",11,0);	//住所3
		final JTextField  TB_Tel		 = B00110FrameParts.JTextFieldSet(100,290,100,20,"",11,0);	//電話番号
		final JTextField  TB_Fax		 = B00110FrameParts.JTextFieldSet(100,315,100,20,"",11,0);	//FAX
		final JTextField  TB_Mail		 = B00110FrameParts.JTextFieldSet(100,340,250,20,"",11,0);	//メールアドレス
		final JTextField  TB_Com01		 = B00110FrameParts.JTextFieldSet(100,365,250,20,"",11,0);	//コメント1
		final JTextField  TB_Com02		 = B00110FrameParts.JTextFieldSet(100,390,250,20,"",11,0);	//コメント2
		final JTextField  TB_Com03		 = B00110FrameParts.JTextFieldSet(100,415,250,20,"",11,0);	//コメント3
		final JComboBox  TB_ShimeDate	 = B00110FrameParts.JComboBoxSet( 100,440, 50,20,B00100DefaultVariable.ShimeDateList,	11);	//運賃締日
		final JComboBox  TB_ShimeBasis	 = B00110FrameParts.JComboBoxSet( 100,465,150,20,B00100DefaultVariable.DeliFeeNorm[0],	11);	//請求基準
		final JTextField  TB_EntryDate	 = B00110FrameParts.JTextFieldSet(100,490,250,20,"",11,0);	//データ登録日時
		final JTextField  TB_UpdateDate	 = B00110FrameParts.JTextFieldSet(100,515,250,20,"",11,0);	//データ更新日時
		final JTextField  TB_EntryUser	 = B00110FrameParts.JTextFieldSet(100,540,250,20,"",11,0);	//登録者コード
		final JTextField  TB_UpdateUser	 = B00110FrameParts.JTextFieldSet(100,565,250,20,"",11,0);	//更新者コード
		final JTextField  TB_PTMSCD		 = B00110FrameParts.JTextFieldSet(100,590,100,20,"",11,0);	//基幹SYS荷主コード
		
		TB_cl_cd.setEnabled(false);
		TB_EntryDate.setEditable(false);	//データ登録日時
		TB_UpdateDate.setEditable(false);	//データ更新日時
		TB_EntryUser.setEditable(false);	//登録者コード
		TB_UpdateUser.setEditable(false);	//更新者コード
		
		for(int i=0;i<B00100DefaultVariable.ClGpList[1].length;i++) {
			if((""+A00000Main.ClGp).equals(""+B00100DefaultVariable.ClGpList[1][i])) {
				TB_ClGpCD.setSelectedIndex(i);
			}
		}
		
		for(int i=0;i<B00100DefaultVariable.WhList[1].length;i++) {
			if((""+A00000Main.ClWh).equals(""+B00100DefaultVariable.WhList[1][i])) {
				TB_WHCD.setSelectedIndex(i);
			}
		}
		
		TB_ShimeDate.setSelectedIndex(28);
		TB_ShimeBasis.setSelectedIndex(0);
		
		if(!"".equals(TgtClCd)) {
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
			
			SearchCLCD.add(TgtClCd);
			
			Object[][] ClMstRt = M00011ClMstRt.ClMstRt(
						SearchClGpCD,SearchCLCD,SearchCLName,SearchPost,searchAdd,
						SearchTel,SearchFax,SearchMail,SearchCom,SearchWHCD,AllSearch);
			
			if(0<ClMstRt.length) {
				TB_cl_cd.setText(""+ClMstRt[0][0]);
				TB_CLName01.setText(""+ClMstRt[0][5]);
				TB_CLName02.setText(""+ClMstRt[0][6]);
				TB_CLName03.setText(""+ClMstRt[0][7]);
				TB_Post.setText(""+ClMstRt[0][8]);
				TB_Add01.setText(""+ClMstRt[0][9]);
				TB_Add02.setText(""+ClMstRt[0][10]);
				TB_Add03.setText(""+ClMstRt[0][11]);
				TB_Tel.setText(""+ClMstRt[0][12]);
				TB_Fax.setText(""+ClMstRt[0][13]);
				TB_Mail.setText(""+ClMstRt[0][14]);
				TB_Com01.setText(""+ClMstRt[0][15]);
				TB_Com02.setText(""+ClMstRt[0][16]);
				TB_Com03.setText(""+ClMstRt[0][17]);
				TB_EntryDate.setText(""+ClMstRt[0][20]);
				TB_UpdateDate.setText(""+ClMstRt[0][21]);
				TB_EntryUser.setText(""+ClMstRt[0][22]);
				TB_UpdateUser.setText(""+ClMstRt[0][23]);
				TB_PTMSCD.setText(""+ClMstRt[0][24]);
				
				for(int i=0;i<B00100DefaultVariable.ClGpList[1].length;i++) {
					if((""+ClMstRt[0][1]).equals(""+B00100DefaultVariable.ClGpList[1][i])) {
						TB_ClGpCD.setSelectedIndex(i);
					}
				}
				
				for(int i=0;i<B00100DefaultVariable.WhList[1].length;i++) {
					if((""+ClMstRt[0][3]).equals(""+B00100DefaultVariable.WhList[1][i])) {
						TB_WHCD.setSelectedIndex(i);
					}
				}
				
				for(int i=0;i<B00100DefaultVariable.ShimeDateList.length;i++) {
					if((""+ClMstRt[0][18]).equals(""+B00100DefaultVariable.ShimeDateList[i])) {
						TB_ShimeDate.setSelectedIndex(i);
					}
				}
				
				for(int i=0;i<B00100DefaultVariable.DeliFeeNorm[1].length;i++) {
					if((""+ClMstRt[0][19]).equals(""+B00100DefaultVariable.DeliFeeNorm[1][i])) {
						TB_ShimeBasis.setSelectedIndex(i);
					}
				}
			}
		}
		
		main_fm.add(LB_cl_cd);
		main_fm.add(LB_ClGpCD);
		main_fm.add(LB_WHCD);
		main_fm.add(LB_CLName01);
		main_fm.add(LB_CLName02);
		main_fm.add(LB_CLName03);
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
		main_fm.add(LB_ShimeDate);
		main_fm.add(LB_ShimeBasis);
		main_fm.add(LB_EntryDate);
		main_fm.add(LB_UpdateDate);
		main_fm.add(LB_EntryUser);
		main_fm.add(LB_UpdateUser);
		main_fm.add(LB_PTMSCD);
		
		main_fm.add(TB_cl_cd);
		main_fm.add(TB_ClGpCD);
		main_fm.add(TB_WHCD);
		main_fm.add(TB_CLName01);
		main_fm.add(TB_CLName02);
		main_fm.add(TB_CLName03);
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
		main_fm.add(TB_ShimeDate);
		main_fm.add(TB_ShimeBasis);
		main_fm.add(TB_EntryDate);
		main_fm.add(TB_UpdateDate);
		main_fm.add(TB_EntryUser);
		main_fm.add(TB_UpdateUser);
		main_fm.add(TB_PTMSCD);
		
		main_fm.setVisible(true);
		
		//登録ボタン押下時の挙動
		entry_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				String Getcl_cd 		= TB_cl_cd.getText();		//荷主CD
				String GetClGpCD 		= ""+B00100DefaultVariable.ClGpList[1][TB_ClGpCD.getSelectedIndex()];	//荷主グループCD
				String GetWHCD 			= ""+B00100DefaultVariable.WhList[1][TB_WHCD.getSelectedIndex()];		//担当倉庫
				String GetCLName01 		= TB_CLName01.getText();	//荷主名1
				String GetCLName02 		= TB_CLName02.getText();	//荷主名2
				String GetCLName03 		= TB_CLName03.getText();	//荷主名3
				String GetPost 			= TB_Post.getText();		//郵便番号
				String GetAdd01 		= TB_Add01.getText();		//住所1
				String GetAdd02 		= TB_Add02.getText();		//住所2
				String GetAdd03 		= TB_Add03.getText();		//住所3
				String GetTel 			= TB_Tel.getText();			//電話番号
				String GetFax			= TB_Fax.getText();			//FAX
				String GetMail 			= TB_Mail.getText();		//メールアドレス
				String GetCom01 		= TB_Com01.getText();		//コメント1
				String GetCom02 		= TB_Com02.getText();		//コメント2
				String GetCom03 		= TB_Com03.getText();		//コメント3
				String GetShimeDate 	= ""+B00100DefaultVariable.ShimeDateList[TB_ShimeDate.getSelectedIndex()];	//運賃締日
				String GetShimeBasis 	= ""+B00100DefaultVariable.DeliFeeNorm[1][TB_ShimeBasis.getSelectedIndex()];	//請求基準
				String GetPTMSCD 		= TB_PTMSCD.getText();		//基幹SYS荷主コード
				
				if(null==Getcl_cd		){Getcl_cd 		= "";}
				if(null==GetClGpCD		){GetClGpCD 	= "";}
				if(null==GetWHCD		){GetWHCD 		= "";}
				if(null==GetCLName01	){GetCLName01 	= "";}
				if(null==GetCLName02	){GetCLName02 	= "";}
				if(null==GetCLName03	){GetCLName03 	= "";}
				if(null==GetPost		){GetPost 		= "";}
				if(null==GetAdd01		){GetAdd01 		= "";}
				if(null==GetAdd02		){GetAdd02 		= "";}
				if(null==GetAdd03		){GetAdd03 		= "";}
				if(null==GetTel			){GetTel 		= "";}
				if(null==GetFax			){GetFax 		= "";}
				if(null==GetMail		){GetMail 		= "";}
				if(null==GetCom01		){GetCom01 		= "";}
				if(null==GetCom02		){GetCom02 		= "";}
				if(null==GetCom03		){GetCom03 		= "";}
				if(null==GetShimeDate	){GetShimeDate 	= "";}
				if(null==GetShimeBasis	){GetShimeBasis	= "";}
				if(null==GetPTMSCD		){GetPTMSCD 	= "";}
				
				Getcl_cd		= B00020ToolsTextControl.Trim(Getcl_cd);
				GetClGpCD		= B00020ToolsTextControl.Trim(GetClGpCD);
				GetWHCD			= B00020ToolsTextControl.Trim(GetWHCD);
				GetCLName01		= B00020ToolsTextControl.Trim(GetCLName01);
				GetCLName02		= B00020ToolsTextControl.Trim(GetCLName02);
				GetCLName03		= B00020ToolsTextControl.Trim(GetCLName03);
				GetPost			= B00020ToolsTextControl.Trim(GetPost);
				GetAdd01		= B00020ToolsTextControl.Trim(GetAdd01);
				GetAdd02		= B00020ToolsTextControl.Trim(GetAdd02);
				GetAdd03		= B00020ToolsTextControl.Trim(GetAdd03);
				GetTel			= B00020ToolsTextControl.Trim(GetTel);
				GetFax			= B00020ToolsTextControl.Trim(GetFax);
				GetMail			= B00020ToolsTextControl.Trim(GetMail);
				GetCom01		= B00020ToolsTextControl.Trim(GetCom01);
				GetCom02		= B00020ToolsTextControl.Trim(GetCom02);
				GetCom03		= B00020ToolsTextControl.Trim(GetCom03);
				GetShimeDate	= B00020ToolsTextControl.Trim(GetShimeDate);
				GetShimeBasis	= B00020ToolsTextControl.Trim(GetShimeBasis);
				GetPTMSCD		= B00020ToolsTextControl.Trim(GetPTMSCD);
				
				GetPost			= B00020ToolsTextControl.num_only_String(GetPost);
				GetTel			= B00020ToolsTextControl.num_only_String(GetTel);
				GetFax			= B00020ToolsTextControl.num_only_String(GetFax);
				GetShimeDate	= B00020ToolsTextControl.num_only_String(GetShimeDate);	if("".equals(GetShimeDate)) {GetShimeDate   = "99";}
				GetShimeBasis	= B00020ToolsTextControl.num_only_String(GetShimeBasis);	if("".equals(GetShimeBasis)) {GetShimeBasis = "0";}
				
				if(!"".equals(GetCLName01)) {
					if("".equals(Getcl_cd)) {
						Getcl_cd = M00011ClMstRt.NewClCdGet();
					}
					String tgt_table = "KM0030_CLIENTMST";
					String[][] field_name = new String[23][3];
					String[][] entry_data = new String[1][23];
					String[] judg_field = new String[1];
					String[][] judg_data = new String[1][1];
					String TgtDB = "NYANKO";
					int non_msg_fg = 1;
					String now_dtm = B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[1];

					judg_field[ 0] = "cl_cd";		//荷主CD
					
					field_name[ 0][0] = "cl_cd";		//荷主CD
					field_name[ 1][0] = "ClGpCD";		//荷主グループCD
					field_name[ 2][0] = "WHCD";			//担当倉庫
					field_name[ 3][0] = "CLName01";		//荷主名1
					field_name[ 4][0] = "CLName02";		//荷主名2
					field_name[ 5][0] = "CLName03";		//荷主名3
					field_name[ 6][0] = "Post";			//郵便番号
					field_name[ 7][0] = "Add01";		//住所1
					field_name[ 8][0] = "Add02";		//住所2
					field_name[ 9][0] = "Add03";		//住所3
					field_name[10][0] = "Tel";			//電話番号
					field_name[11][0] = "Fax";			//FAX
					field_name[12][0] = "Mail";			//メールアドレス
					field_name[13][0] = "Com01";		//コメント1
					field_name[14][0] = "Com02";		//コメント2
					field_name[15][0] = "Com03";		//コメント3
					field_name[16][0] = "ShimeDate";	//運賃締日
					field_name[17][0] = "ShimeBasis";	//請求基準
					field_name[18][0] = "EntryDate";	//データ登録日時
					field_name[19][0] = "UpdateDate";	//データ更新日時
					field_name[20][0] = "EntryUser";	//登録者コード
					field_name[21][0] = "UpdateUser";	//更新者コード
					field_name[22][0] = "PTMSCD";		//基幹システム荷主コード

					field_name[ 0][1] = "1";	//荷主CD
					field_name[ 1][1] = "1";	//荷主グループCD
					field_name[ 2][1] = "1";	//担当倉庫
					field_name[ 3][1] = "1";	//荷主名1
					field_name[ 4][1] = "1";	//荷主名2
					field_name[ 5][1] = "1";	//荷主名3
					field_name[ 6][1] = "1";	//郵便番号
					field_name[ 7][1] = "1";	//住所1
					field_name[ 8][1] = "1";	//住所2
					field_name[ 9][1] = "1";	//住所3
					field_name[10][1] = "1";	//電話番号
					field_name[11][1] = "1";	//FAX
					field_name[12][1] = "1";	//メールアドレス
					field_name[13][1] = "1";	//コメント1
					field_name[14][1] = "1";	//コメント2
					field_name[15][1] = "1";	//コメント3
					field_name[16][1] = "1";	//運賃締日
					field_name[17][1] = "1";	//請求基準
					field_name[18][1] = "1";	//データ登録日時
					field_name[19][1] = "1";	//データ更新日時
					field_name[20][1] = "1";	//登録者コード
					field_name[21][1] = "1";	//更新者コード
					field_name[22][1] = "1";	//基幹システム荷主コード
					
					field_name[ 0][2] = "1";	//荷主CD
					field_name[ 1][2] = "1";	//荷主グループCD
					field_name[ 2][2] = "1";	//担当倉庫
					field_name[ 3][2] = "1";	//荷主名1
					field_name[ 4][2] = "1";	//荷主名2
					field_name[ 5][2] = "1";	//荷主名3
					field_name[ 6][2] = "1";	//郵便番号
					field_name[ 7][2] = "1";	//住所1
					field_name[ 8][2] = "1";	//住所2
					field_name[ 9][2] = "1";	//住所3
					field_name[10][2] = "1";	//電話番号
					field_name[11][2] = "1";	//FAX
					field_name[12][2] = "1";	//メールアドレス
					field_name[13][2] = "1";	//コメント1
					field_name[14][2] = "1";	//コメント2
					field_name[15][2] = "1";	//コメント3
					field_name[16][2] = "1";	//運賃締日
					field_name[17][2] = "1";	//請求基準
					field_name[18][2] = "0";	//データ登録日時
					field_name[19][2] = "1";	//データ更新日時
					field_name[20][2] = "0";	//登録者コード
					field_name[21][2] = "1";	//更新者コード
					field_name[22][2] = "1";	//基幹システム荷主コード
					
					judg_data[0][0] = Getcl_cd;
					
					entry_data[0][ 0] = Getcl_cd;		//荷主CD
					entry_data[0][ 1] = GetClGpCD;		//荷主グループCD
					entry_data[0][ 2] = GetWHCD;		//担当倉庫
					entry_data[0][ 3] = GetCLName01;	//荷主名1
					entry_data[0][ 4] = GetCLName02;	//荷主名2
					entry_data[0][ 5] = GetCLName03;	//荷主名3
					entry_data[0][ 6] = GetPost;		//郵便番号
					entry_data[0][ 7] = GetAdd01;		//住所1
					entry_data[0][ 8] = GetAdd02;		//住所2
					entry_data[0][ 9] = GetAdd03;		//住所3
					entry_data[0][10] = GetTel;			//電話番号
					entry_data[0][11] = GetFax;			//FAX
					entry_data[0][12] = GetMail;		//メールアドレス
					entry_data[0][13] = GetCom01;		//コメント1
					entry_data[0][14] = GetCom02;		//コメント2
					entry_data[0][15] = GetCom03;		//コメント3
					entry_data[0][16] = GetShimeDate;	//運賃締日
					entry_data[0][17] = GetShimeBasis;	//請求基準
					entry_data[0][18] = now_dtm;		//データ登録日時
					entry_data[0][19] = now_dtm;		//データ更新日時
					entry_data[0][20] = "(" + A00000Main.LoginUserId + ")" + A00000Main.LoginUserName;	//登録者コード
					entry_data[0][21] = "(" + A00000Main.LoginUserId + ")" + A00000Main.LoginUserName;	//更新者コード
					entry_data[0][22] = GetPTMSCD;	//基幹システム荷主コード
					
					A00020InsertUdateSQL.RUN_SQLS_EU(tgt_table, field_name, entry_data, judg_field, judg_data, non_msg_fg,TgtDB);
					
					//荷主一覧更新
					B00100DefaultVariable.ClList();
					
					SetX=main_fm.getX();
					SetY=main_fm.getY();
	
					main_fm.setVisible(false);
					main_fm.dispose();
					ClMstRenewAndCreate(0,0,Getcl_cd);
				}else {
					JOptionPane.showMessageDialog(null, "荷主名は必要ですよ");
				}
			}
		});
		
		//郵便番号フォーカス消失時の挙動
		TB_Post.addFocusListener(new FocusAdapter(){
			@Override
			public void focusLost(FocusEvent e){
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
			}
		});
		
		//EXITボタン押下時の挙動
		exit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				WM00050ClMstSearch.ClMstSearch(0, 0);
			}
		});
	}
}