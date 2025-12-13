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