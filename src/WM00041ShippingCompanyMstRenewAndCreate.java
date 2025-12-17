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

public class WM00041ShippingCompanyMstRenewAndCreate{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	public static void ShippingCompanyMstRenewAndCreate(int x,int y,String ShippingCompanyCd) {
		A00000Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		
		if(null==ShippingCompanyCd) {ShippingCompanyCd = "";}
		
		final JFrame main_fm = B00110FrameParts.FrameCreate(x,y,500,700,"Corgi00運送会社登録・更新","");
		JLabel userinfo = B00110FrameParts.UserInfo();
		JButton exit_btn = B00110FrameParts.ExitBtn();
		JButton entry_btn = B00110FrameParts.EntryBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		main_fm.add(entry_btn);
		
		JLabel LB_ShippingCompanyCd		= B00110FrameParts.JLabelSet(  0, 40,100,20,"運送会社CD:"		,11,1);
		JLabel LB_ShippingCompanyName01	= B00110FrameParts.JLabelSet(  0, 65,100,20,"運送会社名1:"		,11,1);
		JLabel LB_ShippingCompanyName02	= B00110FrameParts.JLabelSet(  0, 90,100,20,"運送会社名2:"		,11,1);
		JLabel LB_ShippingCompanyName03	= B00110FrameParts.JLabelSet(  0,115,100,20,"運送会社名3:"		,11,1);
		JLabel LB_Post					= B00110FrameParts.JLabelSet(  0,140,100,20,"運送会社郵便:"	,11,1);
		JLabel LB_Add01					= B00110FrameParts.JLabelSet(  0,165,100,20,"運送会社住所1:"	,11,1);
		JLabel LB_Add02					= B00110FrameParts.JLabelSet(  0,190,100,20,"運送会社住所2:"	,11,1);
		JLabel LB_Add03					= B00110FrameParts.JLabelSet(  0,215,100,20,"運送会社住所3:"	,11,1);
		JLabel LB_Tel					= B00110FrameParts.JLabelSet(  0,240,100,20,"運送会社電話:"	,11,1);
		JLabel LB_Fax					= B00110FrameParts.JLabelSet(  0,265,100,20,"運送会社FAX:"		,11,1);
		JLabel LB_Mail					= B00110FrameParts.JLabelSet(  0,290,100,20,"運送会社MAIL:"	,11,1);
		JLabel LB_Com01					= B00110FrameParts.JLabelSet(  0,315,100,20,"コメント1:"		,11,1);
		JLabel LB_Com02					= B00110FrameParts.JLabelSet(  0,340,100,20,"コメント2:"		,11,1);
		JLabel LB_Com03					= B00110FrameParts.JLabelSet(  0,365,100,20,"コメント3:"		,11,1);
		JLabel LB_ShimeDate				= B00110FrameParts.JLabelSet(  0,390,100,20,"締日:"			,11,1);
		JLabel LB_ShimeBasis			= B00110FrameParts.JLabelSet(  0,415,100,20,"請求基準:"		,11,1);
		JLabel LB_EntryDate				= B00110FrameParts.JLabelSet(  0,440,100,20,"データ登録日時:"	,11,1);
		JLabel LB_UpdateDate			= B00110FrameParts.JLabelSet(  0,465,100,20,"データ更新日時:"	,11,1);
		JLabel LB_EntryUser				= B00110FrameParts.JLabelSet(  0,490,100,20,"登録者コード:"	,11,1);
		JLabel LB_UpdateUser			= B00110FrameParts.JLabelSet(  0,515,100,20,"更新者コード:"	,11,1);
		JLabel LB_PTMSCD				= B00110FrameParts.JLabelSet(  0,540,100,20,"基幹SYS傭車CD:"	,11,1);
		JLabel LB_ExportDataType		= B00110FrameParts.JLabelSet(  0,565,100,20,"データ抽出Type:"	,11,1);
		
		final JTextField TB_ShippingCompanyCd		= B00110FrameParts.JTextFieldSet(100, 40,100,20,"",11,0);	//運送会社CD
		final JTextField TB_ShippingCompanyName01	= B00110FrameParts.JTextFieldSet(100, 65,250,20,"",11,0);	//運送会社名1
		final JTextField TB_ShippingCompanyName02	= B00110FrameParts.JTextFieldSet(100, 90,250,20,"",11,0);	//運送会社名2
		final JTextField TB_ShippingCompanyName03	= B00110FrameParts.JTextFieldSet(100,115,250,20,"",11,0);	//運送会社名3
		final JTextField TB_Post					= B00110FrameParts.JTextFieldSet(100,140,100,20,"",11,0);	//運送会社郵便
		final JTextField TB_Add01					= B00110FrameParts.JTextFieldSet(100,165,250,20,"",11,0);	//運送会社住所1
		final JTextField TB_Add02					= B00110FrameParts.JTextFieldSet(100,190,250,20,"",11,0);	//運送会社住所2
		final JTextField TB_Add03					= B00110FrameParts.JTextFieldSet(100,215,250,20,"",11,0);	//運送会社住所3
		final JTextField TB_Tel						= B00110FrameParts.JTextFieldSet(100,240,100,20,"",11,0);	//運送会社電話
		final JTextField TB_Fax						= B00110FrameParts.JTextFieldSet(100,265,100,20,"",11,0);	//運送会社FAX
		final JTextField TB_Mail					= B00110FrameParts.JTextFieldSet(100,290,100,20,"",11,0);	//運送会社MAIL
		final JTextField TB_Com01					= B00110FrameParts.JTextFieldSet(100,315,100,20,"",11,0);	//コメント1
		final JTextField TB_Com02					= B00110FrameParts.JTextFieldSet(100,340,100,20,"",11,0);	//コメント2
		final JTextField TB_Com03					= B00110FrameParts.JTextFieldSet(100,365,100,20,"",11,0);	//コメント3
		final JComboBox TB_ShimeDate				= B00110FrameParts.JComboBoxSet( 100,390,100,20,B00100DefaultVariable.ShimeDateList[0],	11);	//締日
		final JComboBox TB_ShimeBasis				= B00110FrameParts.JComboBoxSet( 100,415,150,20,B00100DefaultVariable.DeliFeeNorm[0],	11);	//請求基準
		final JTextField TB_EntryDate				= B00110FrameParts.JTextFieldSet(100,440,250,20,"",11,0);	//データ登録日時
		final JTextField TB_UpdateDate				= B00110FrameParts.JTextFieldSet(100,465,250,20,"",11,0);	//データ更新日時
		final JTextField TB_EntryUser				= B00110FrameParts.JTextFieldSet(100,490,250,20,"",11,0);	//登録者コード
		final JTextField TB_UpdateUser				= B00110FrameParts.JTextFieldSet(100,515,250,20,"",11,0);	//更新者コード
		final JTextField TB_PTMSCD					= B00110FrameParts.JTextFieldSet(100,540,100,20,"",11,0);	//基幹SYS傭車CD
		final JComboBox  TB_ExportDataType			= B00110FrameParts.JComboBoxSet( 100,565,200,20,B00100DefaultVariable.HaisyaDataLayoutPt[0],	11);	//データ抽出Type
		
		TB_ShippingCompanyCd.setEnabled(false);
		TB_EntryDate.setEnabled(false);
		TB_UpdateDate.setEnabled(false);
		TB_EntryUser.setEnabled(false);
		TB_UpdateUser.setEnabled(false);
		
		TB_ShimeDate.setSelectedIndex(28);
		TB_ShimeBasis.setSelectedIndex(0);
		
		if(!"".equals(ShippingCompanyCd)) {
			ArrayList<String> SearchShippingCompanyCd = new ArrayList<String>();
			ArrayList<String> SearchCompanyName = new ArrayList<String>();
			ArrayList<String> SearchPost = new ArrayList<String>();
			ArrayList<String> SearchAdd = new ArrayList<String>();
			ArrayList<String> SearchTel = new ArrayList<String>();
			ArrayList<String> SearchFax = new ArrayList<String>();
			ArrayList<String> SearchMail = new ArrayList<String>();
			ArrayList<String> SearchCom = new ArrayList<String>();
			boolean AllSearch = false;
			
			SearchShippingCompanyCd.add(ShippingCompanyCd);
			
			Object[][] ShippingCompanyMstRt = M00030ShippingCompanyMstRt.ShippingCompanyMstRt(
					SearchShippingCompanyCd,
					SearchCompanyName,
					SearchPost,
					SearchAdd,
					SearchTel,
					SearchFax,
					SearchMail,
					SearchCom,
					AllSearch);
			
			if(0<ShippingCompanyMstRt.length) {
				TB_ShippingCompanyCd.setText(		""+ShippingCompanyMstRt[0][ 0]);	//運送会社CD
				TB_ShippingCompanyName01.setText(	""+ShippingCompanyMstRt[0][ 1]);	//運送会社名1
				TB_ShippingCompanyName02.setText(	""+ShippingCompanyMstRt[0][ 2]);	//運送会社名2
				TB_ShippingCompanyName03.setText(	""+ShippingCompanyMstRt[0][ 3]);	//運送会社名3
				TB_Post.setText(					""+ShippingCompanyMstRt[0][ 4]);	//運送会社郵便
				TB_Add01.setText(					""+ShippingCompanyMstRt[0][ 5]);	//運送会社住所1
				TB_Add02.setText(					""+ShippingCompanyMstRt[0][ 6]);	//運送会社住所2
				TB_Add03.setText(					""+ShippingCompanyMstRt[0][ 7]);	//運送会社住所3
				TB_Tel.setText(						""+ShippingCompanyMstRt[0][ 8]);	//運送会社電話
				TB_Fax.setText(						""+ShippingCompanyMstRt[0][ 9]);	//運送会社FAX
				TB_Mail.setText(					""+ShippingCompanyMstRt[0][10]);	//運送会社MAIL
				TB_Com01.setText(					""+ShippingCompanyMstRt[0][11]);	//コメント1
				TB_Com02.setText(					""+ShippingCompanyMstRt[0][12]);	//コメント2
				TB_Com03.setText(					""+ShippingCompanyMstRt[0][13]);	//コメント3
				TB_EntryDate.setText(				""+ShippingCompanyMstRt[0][16]);	//データ登録日時
				TB_UpdateDate.setText(				""+ShippingCompanyMstRt[0][17]);	//データ更新日時
				TB_EntryUser.setText(				""+ShippingCompanyMstRt[0][18]);	//登録者コード
				TB_UpdateUser.setText(				""+ShippingCompanyMstRt[0][19]);	//更新者コード
				TB_PTMSCD.setText(					""+ShippingCompanyMstRt[0][20]);	//基幹SYS傭車CD
				
				for(int i=0;i<B00100DefaultVariable.ShimeDateList[1].length;i++) {
					if((""+B00100DefaultVariable.ShimeDateList[1][i]).equals(""+ShippingCompanyMstRt[0][14])){
						TB_ShimeDate.setSelectedIndex(i);
					}
				}
				for(int i=0;i<B00100DefaultVariable.DeliFeeNorm[1].length;i++) {
					if((""+B00100DefaultVariable.DeliFeeNorm[1][i]).equals(""+ShippingCompanyMstRt[0][15])){
						TB_ShimeBasis.setSelectedIndex(i);
					}
				}
				for(int i=0;i<B00100DefaultVariable.HaisyaDataLayoutPt[1].length;i++) {
					if((""+B00100DefaultVariable.HaisyaDataLayoutPt[1][i]).equals(""+ShippingCompanyMstRt[0][21])){
						TB_ExportDataType.setSelectedIndex(i);
					}
				}
			}
		}

		main_fm.add(LB_ShippingCompanyCd);
		main_fm.add(LB_ShippingCompanyName01);
		main_fm.add(LB_ShippingCompanyName02);
		main_fm.add(LB_ShippingCompanyName03);
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
		main_fm.add(LB_ExportDataType);
		
		main_fm.add(TB_ShippingCompanyCd);
		main_fm.add(TB_ShippingCompanyName01);
		main_fm.add(TB_ShippingCompanyName02);
		main_fm.add(TB_ShippingCompanyName03);
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
		main_fm.add(TB_ExportDataType);
		
		main_fm.setVisible(true);
		RenewFg = true;
		//登録ボタン押下時の挙動
		entry_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					
					String GetShippingCompanyCd		= TB_ShippingCompanyCd.getText();		//運送会社CD
					String GetShippingCompanyName01	= TB_ShippingCompanyName01.getText();	//運送会社名1
					String GetShippingCompanyName02	= TB_ShippingCompanyName02.getText();	//運送会社名2
					String GetShippingCompanyName03	= TB_ShippingCompanyName03.getText();	//運送会社名3
					String GetPost					= TB_Post.getText();	//運送会社郵便
					String GetAdd01					= TB_Add01.getText();	//運送会社住所1
					String GetAdd02					= TB_Add02.getText();	//運送会社住所2
					String GetAdd03					= TB_Add03.getText();	//運送会社住所3
					String GetTel					= TB_Tel.getText();		//運送会社電話
					String GetFax					= TB_Fax.getText();		//運送会社FAX
					String GetMail					= TB_Mail.getText();	//運送会社MAIL
					String GetCom01					= TB_Com01.getText();	//コメント1
					String GetCom02					= TB_Com02.getText();	//コメント2
					String GetCom03					= TB_Com03.getText();	//コメント3
					String GetShimeDate				= ""+B00100DefaultVariable.ShimeDateList[1][TB_ShimeDate.getSelectedIndex()];	//締日
					String GetShimeBasis			= ""+B00100DefaultVariable.DeliFeeNorm[1][TB_ShimeBasis.getSelectedIndex()];	//請求基準
					String GetPTMSCD				= TB_PTMSCD.getText();	//基幹SYS傭車CD
					String GetExportDataType		= ""+B00100DefaultVariable.HaisyaDataLayoutPt[1][TB_ExportDataType.getSelectedIndex()];	//データ抽出Type
					
					if(null==GetShippingCompanyCd		){GetShippingCompanyCd = "";}
					if(null==GetShippingCompanyName01	){GetShippingCompanyName01 = "";}
					if(null==GetShippingCompanyName02	){GetShippingCompanyName02 = "";}
					if(null==GetShippingCompanyName03	){GetShippingCompanyName03 = "";}
					if(null==GetPost					){GetPost = "";}
					if(null==GetAdd01					){GetAdd01 = "";}
					if(null==GetAdd02					){GetAdd02 = "";}
					if(null==GetAdd03					){GetAdd03 = "";}
					if(null==GetTel						){GetTel = "";}
					if(null==GetFax						){GetFax = "";}
					if(null==GetMail					){GetMail = "";}
					if(null==GetCom01					){GetCom01 = "";}
					if(null==GetCom02					){GetCom02 = "";}
					if(null==GetCom03					){GetCom03 = "";}
					if(null==GetShimeDate				){GetShimeDate = "";}
					if(null==GetShimeBasis				){GetShimeBasis = "";}
					if(null==GetPTMSCD					){GetPTMSCD = "";}
					if(null==GetExportDataType			){GetExportDataType = "";}
					
					GetShippingCompanyCd		= B00020ToolsTextControl.Trim(GetShippingCompanyCd);
					GetShippingCompanyName01	= B00020ToolsTextControl.Trim(GetShippingCompanyName01);
					GetShippingCompanyName02	= B00020ToolsTextControl.Trim(GetShippingCompanyName02);
					GetShippingCompanyName03	= B00020ToolsTextControl.Trim(GetShippingCompanyName03);
					GetPost						= B00020ToolsTextControl.Trim(GetPost);
					GetAdd01					= B00020ToolsTextControl.Trim(GetAdd01);
					GetAdd02					= B00020ToolsTextControl.Trim(GetAdd02);
					GetAdd03					= B00020ToolsTextControl.Trim(GetAdd03);
					GetTel						= B00020ToolsTextControl.Trim(GetTel);
					GetFax						= B00020ToolsTextControl.Trim(GetFax);
					GetMail						= B00020ToolsTextControl.Trim(GetMail);
					GetCom01					= B00020ToolsTextControl.Trim(GetCom01);
					GetCom02					= B00020ToolsTextControl.Trim(GetCom02);
					GetCom03					= B00020ToolsTextControl.Trim(GetCom03);
					GetShimeDate				= B00020ToolsTextControl.Trim(GetShimeDate);
					GetShimeBasis				= B00020ToolsTextControl.Trim(GetShimeBasis);
					GetPTMSCD					= B00020ToolsTextControl.Trim(GetPTMSCD);
					GetExportDataType			= B00020ToolsTextControl.Trim(GetExportDataType);

					GetPost						= B00020ToolsTextControl.num_only_String(GetPost);
					GetTel						= B00020ToolsTextControl.num_only_String(GetTel);
					GetFax						= B00020ToolsTextControl.num_only_String(GetFax);
					GetShimeDate				= B00020ToolsTextControl.num_only_String(GetShimeDate);
					GetShimeBasis				= B00020ToolsTextControl.num_only_String(GetShimeBasis);
					
					if("".equals(GetShimeDate)) {GetShimeDate="99";}
					if("".equals(GetShimeBasis)) {GetShimeBasis="0";}
					
					if(!"".equals(GetShippingCompanyName01)) {
						if("".equals(GetShippingCompanyCd)) {
							GetShippingCompanyCd = M00030ShippingCompanyMstRt.NewShipingCompanyCdGet();
						}
						String now_dtm = B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[1];
						
						String[][] SetString = {
								{"ShippingCompanyCd"		,"1","1",GetShippingCompanyCd}		//運送会社CD
								,{"ShippingCompanyName01"	,"1","1",GetShippingCompanyName01}	//運送会社名1
								,{"ShippingCompanyName02"	,"1","1",GetShippingCompanyName02}	//運送会社名2
								,{"ShippingCompanyName03"	,"1","1",GetShippingCompanyName03}	//運送会社名3
								,{"Post"					,"1","1",GetPost}					//運送会社郵便
								,{"Add01"					,"1","1",GetAdd01}					//運送会社住所1
								,{"Add02"					,"1","1",GetAdd02}					//運送会社住所2
								,{"Add03"					,"1","1",GetAdd03}					//運送会社住所3
								,{"Tel"						,"1","1",GetTel}					//運送会社電話
								,{"Fax"						,"1","1",GetFax}					//運送会社FAX
								,{"Mail"					,"1","1",GetMail}					//運送会社MAIL
								,{"Com01"					,"1","1",GetCom01}					//コメント1
								,{"Com02"					,"1","1",GetCom02}					//コメント2
								,{"Com03"					,"1","1",GetCom03}					//コメント3
								,{"ShimeDate"				,"1","1",GetShimeDate}				//締日
								,{"ShimeBasis"				,"1","1",GetShimeBasis}				//請求基準
								,{"EntryDate"				,"1","0",now_dtm}					//データ登録日時
								,{"UpdateDate"				,"1","1",now_dtm}					//データ更新日時
								,{"EntryUser"				,"1","0","(" + A00000Main.LoginUserId + ")" + A00000Main.LoginUserName}		//登録者コード
								,{"UpdateUser"				,"1","1","(" + A00000Main.LoginUserId + ")" + A00000Main.LoginUserName}		//更新者コード
								,{"PTMSCD"					,"1","1",GetPTMSCD}					//基幹システム傭車コード
								,{"ExportDataType"			,"1","1",GetExportDataType}			//配車データ抽出タイプ
						};
						
						String tgt_table = "KM0070_SHIPPINGCOMPANYMST";
						String[][] field_name = new String[SetString.length][3];
						String[][] entry_data = new String[1][SetString.length];
						String[] judg_field = new String[1];
						String[][] judg_data = new String[1][1];
						String TgtDB = "NYANKO";
						int non_msg_fg = 1;

						judg_field[0] = "ShippingCompanyCd";		//運送会社CD
						judg_data[0][0] = GetShippingCompanyCd;
						
						for(int i=0;i<SetString.length;i++) {
							field_name[i][0] = SetString[i][0];
							field_name[i][1] = SetString[i][1];
							field_name[i][2] = SetString[i][2];
							entry_data[0][i] = SetString[i][3];
						}
						
						A00020InsertUdateSQL.RUN_SQLS_EU(tgt_table, field_name, entry_data, judg_field, judg_data, non_msg_fg,TgtDB);
						
						B00100DefaultVariable.ShippingCompanyList();		//運送会社一覧生成
						
						SetX=main_fm.getX();
						SetY=main_fm.getY();
		
						main_fm.setVisible(false);
						main_fm.dispose();
						WM00041ShippingCompanyMstRenewAndCreate.ShippingCompanyMstRenewAndCreate(0, 0,GetShippingCompanyCd);
					}else {
						JOptionPane.showMessageDialog(null, "運送会社名は必要ですよ");
					}
					RenewFg = true;
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
				WM00040ShippingCompanyMstSearch.ShippingCompanyMstSearch(0, 0);
			}
		});
	}
}