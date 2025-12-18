import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class WM00051DeliveryTypeMstRenewAndCreate{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	public static void DeliveryTypeMstRenewAndCreate(int x,int y,String DeliveryTypeNo,String DeliveryTypeCd) {
		A00000Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		
		if(null==DeliveryTypeNo) {DeliveryTypeNo = "";}
		if(null==DeliveryTypeCd) {DeliveryTypeCd = "";}
		
		final JFrame main_fm = B00110FrameParts.FrameCreate(x,y,500,350,"Corgi00運送タイプマスタ登録・更新","");
		JLabel userinfo = B00110FrameParts.UserInfo();
		JButton exit_btn = B00110FrameParts.ExitBtn();
		JButton entry_btn = B00110FrameParts.EntryBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		main_fm.add(entry_btn);
		
		JLabel LB_DeliveryTypeNo	= B00110FrameParts.JLabelSet(  0, 40,100,20,"タイプ番号:",			11,1);
		JLabel LB_DeliveryTypeCd	= B00110FrameParts.JLabelSet(  0, 65,100,20,"運送タイプコード:",	11,1);
		JLabel LB_DeliveryTypeName	= B00110FrameParts.JLabelSet(  0, 90,100,20,"運送タイプ名:",		11,1);
		JLabel LB_EntryDate			= B00110FrameParts.JLabelSet(  0,115,100,20,"データ登録日時:",		11,1);
		JLabel LB_UpdateDate		= B00110FrameParts.JLabelSet(  0,140,100,20,"データ更新日時:",		11,1);
		JLabel LB_EntryUser			= B00110FrameParts.JLabelSet(  0,165,100,20,"登録者コード:",		11,1);
		JLabel LB_UpdateUser		= B00110FrameParts.JLabelSet(  0,190,100,20,"更新者コード:",		11,1);
		
		String[] TypeNoList = {"01","02","03","04","05"};
		
		final JComboBox  TB_SearchDeliveryTypeNo	= B00110FrameParts.JComboBoxSet( 100, 40,100,20,TypeNoList,11);	//タイプNo
		final JTextField TB_DeliveryTypeCd			= B00110FrameParts.JTextFieldSet(100, 65,100,20,"",11,0);			//運送タイプコード
		final JTextField TB_DeliveryTypeName		= B00110FrameParts.JTextFieldSet(100, 90,150,20,"",11,0);			//運送タイプ名
		final JTextField TB_EntryDate				= B00110FrameParts.JTextFieldSet(100,115,250,20,"",11,0);			//データ登録日時
		final JTextField TB_UpdateDate				= B00110FrameParts.JTextFieldSet(100,140,250,20,"",11,0);			//データ更新日時
		final JTextField TB_EntryUser				= B00110FrameParts.JTextFieldSet(100,165,250,20,"",11,0);			//登録者コード
		final JTextField TB_UpdateUser				= B00110FrameParts.JTextFieldSet(100,190,250,20,"",11,0);			//更新者コード
		
		TB_EntryDate.setEnabled(false);
		TB_UpdateDate.setEnabled(false);
		TB_EntryUser.setEnabled(false);
		TB_UpdateUser.setEnabled(false);
		
		if(!"".equals(DeliveryTypeNo)&& !"".equals(DeliveryTypeCd)) {
			ArrayList<String> SearchDeliveryTypeNo = new ArrayList<String>();
			ArrayList<String> SearchDeliveryTypeCd = new ArrayList<String>();
			ArrayList<String> SearchDeliveryTypeName = new ArrayList<String>();
			boolean AllSearch = false;
			
			SearchDeliveryTypeNo.add(DeliveryTypeNo);
			SearchDeliveryTypeCd.add(DeliveryTypeCd);
			
			Object[][] DeliveryTypeMstRt = M00050DeliveryTypeMstRt.DeliveryTypeMstRt(
					SearchDeliveryTypeNo,
					SearchDeliveryTypeCd,
					SearchDeliveryTypeName,
					AllSearch);
			
			if(0<DeliveryTypeMstRt.length) {
				TB_SearchDeliveryTypeNo.setSelectedIndex((int)DeliveryTypeMstRt[0][0]);
				TB_DeliveryTypeCd.setText(		""+DeliveryTypeMstRt[0][1]);
				TB_DeliveryTypeName.setText(	""+DeliveryTypeMstRt[0][2]);
				TB_EntryDate.setText(			""+DeliveryTypeMstRt[0][3]);
				TB_UpdateDate.setText(			""+DeliveryTypeMstRt[0][4]);
				TB_EntryUser.setText(			""+DeliveryTypeMstRt[0][5]);
				TB_UpdateUser.setText(			""+DeliveryTypeMstRt[0][6]);
			}
		}
		
		main_fm.add(LB_DeliveryTypeNo);
		main_fm.add(LB_DeliveryTypeCd);
		main_fm.add(LB_DeliveryTypeName);
		main_fm.add(LB_EntryDate);
		main_fm.add(LB_UpdateDate);
		main_fm.add(LB_EntryUser);
		main_fm.add(LB_UpdateUser);
		
		main_fm.add(TB_SearchDeliveryTypeNo);
		main_fm.add(TB_DeliveryTypeCd);
		main_fm.add(TB_DeliveryTypeName);
		main_fm.add(TB_EntryDate);
		main_fm.add(TB_UpdateDate);
		main_fm.add(TB_EntryUser);
		main_fm.add(TB_UpdateUser);
		
		
		main_fm.setVisible(true);
		RenewFg = true;
		
		//登録ボタン押下時の挙動
		entry_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					String GetSearchDeliveryTypeNo = "1";
					switch(TB_SearchDeliveryTypeNo.getSelectedIndex()) {
						case 0:
							 GetSearchDeliveryTypeNo = "1";
							break;
						case 1:
							 GetSearchDeliveryTypeNo = "2";
							break;
						case 2:
							 GetSearchDeliveryTypeNo = "3";
							break;
						case 3:
							 GetSearchDeliveryTypeNo = "4";
							break;
						case 4:
							 GetSearchDeliveryTypeNo = "5";
							break;
						default:
							break;
					}
					String GetDeliveryTypeCd	= TB_DeliveryTypeCd.getText();
					String GetDeliveryTypeName	= TB_DeliveryTypeName.getText();
					
					if(null==GetSearchDeliveryTypeNo	){GetSearchDeliveryTypeNo = "";}
					if(null==GetDeliveryTypeCd			){GetDeliveryTypeCd = "";}
					if(null==GetDeliveryTypeName		){GetDeliveryTypeName = "";}
					
					GetSearchDeliveryTypeNo	= B00020ToolsTextControl.Trim(GetSearchDeliveryTypeNo);
					GetDeliveryTypeCd		= B00020ToolsTextControl.Trim(GetDeliveryTypeCd);
					GetDeliveryTypeName		= B00020ToolsTextControl.Trim(GetDeliveryTypeName);
					
					GetDeliveryTypeCd		= B00020ToolsTextControl.only1byte_String(GetDeliveryTypeCd);
					
					if(!"".equals(GetSearchDeliveryTypeNo)&&!"".equals(GetDeliveryTypeCd)&&!"".equals(GetDeliveryTypeName)) {
						String now_dtm = B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[1];
						String[][] SetString = {
								 {"DeliveryTypeNo"		,"1","1",GetSearchDeliveryTypeNo}	//タイプ番号
								,{"DeliveryTypeCd"		,"1","1",GetDeliveryTypeCd}			//運送タイプコード
								,{"DeliveryTypeName"	,"1","1",GetDeliveryTypeName}		//運送タイプ名
								,{"EntryDate"			,"1","0",now_dtm}					//データ登録日時
								,{"UpdateDate"			,"1","1",now_dtm}					//データ更新日時
								,{"EntryUser"			,"1","0","(" + A00000Main.LoginUserId + ")" + A00000Main.LoginUserName}				//登録者コード
								,{"UpdateUser"			,"1","1","(" + A00000Main.LoginUserId + ")" + A00000Main.LoginUserName}				//更新者コード
						};
						
						String tgt_table = "KM0050_DELIVERY_TYPEMST";
						String[][] field_name = new String[SetString.length][3];
						String[][] entry_data = new String[1][SetString.length];
						String[] judg_field = new String[2];
						String[][] judg_data = new String[1][2];
						String TgtDB = "NYANKO";
						int non_msg_fg = 1;

						judg_field[0] = "DeliveryTypeNo";			//タイプ番号
						judg_field[1] = "DeliveryTypeCd";			//運送タイプコード

						judg_data[0][0] = GetSearchDeliveryTypeNo;	//タイプ番号
						judg_data[0][1] = GetDeliveryTypeCd;		//運送タイプコード
						
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
						
						WM00051DeliveryTypeMstRenewAndCreate.DeliveryTypeMstRenewAndCreate(0,0,GetSearchDeliveryTypeNo,GetDeliveryTypeCd);
					}else {
						JOptionPane.showMessageDialog(null, "運送タイプNo・CD・名称は必須です");
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
				WM00050DeliveryTypeMstSearch.DeliveryTypeMstSearch(0, 0);
			}
		});
	}
}