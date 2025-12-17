import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class WM00036CarMstRenewAndCreate{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	public static void CarMstRenewAndCreate(int x,int y,String TgtWhCd,String TgtShippingCompanyCd,String TgtCarCd) {
		RenewFg = false;
		
		if(null==TgtWhCd) {TgtWhCd="";}
		if(null==TgtShippingCompanyCd) {TgtShippingCompanyCd="";}
		if(null==TgtCarCd) {TgtCarCd="";}
		
		A00000Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		
		final JFrame main_fm = B00110FrameParts.FrameCreate(x,y,500,500,"Corgi00車輛登録・修正","");
		JLabel userinfo = B00110FrameParts.UserInfo();
		JButton exit_btn = B00110FrameParts.ExitBtn();
		JButton entry_btn = B00110FrameParts.EntryBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		main_fm.add(entry_btn);
		
		JLabel LB_WHCD				= B00110FrameParts.JLabelSet(  0, 40,100,20,"担当倉庫:",		11,1);
		JLabel LB_ShippingCompanyCd	= B00110FrameParts.JLabelSet(  0, 65,100,20,"運送会社CD:",		11,1);
		JLabel LB_CarCd				= B00110FrameParts.JLabelSet(  0, 90,100,20,"車輛CD:",			11,1);
		JLabel LB_CarName01			= B00110FrameParts.JLabelSet(  0,115,100,20,"車輛名01:",		11,1);
		JLabel LB_CarName02			= B00110FrameParts.JLabelSet(  0,140,100,20,"車輛名02:",		11,1);
		JLabel LB_CarName03			= B00110FrameParts.JLabelSet(  0,165,100,20,"車輛名03:",		11,1);
		JLabel LB_DriverCd			= B00110FrameParts.JLabelSet(  0,190,100,20,"乗務員CD:",		11,1);
		JLabel LB_PTMSCD			= B00110FrameParts.JLabelSet(  0,215,100,20,"基幹SYS車輛Cd:",	11,1);
		JLabel LB_EntryDate			= B00110FrameParts.JLabelSet(  0,240,100,20,"データ登録日時:",	11,1);
		JLabel LB_UpdateDate		= B00110FrameParts.JLabelSet(  0,265,100,20,"データ更新日時:",	11,1);
		JLabel LB_EntryUser			= B00110FrameParts.JLabelSet(  0,290,100,20,"登録者コード:",	11,1);
		JLabel LB_UpdateUser		= B00110FrameParts.JLabelSet(  0,315,100,20,"更新者コード:",	11,1);
		JLabel LB_DelFg				= B00110FrameParts.JLabelSet(  0,340,100,20,"削除フラグ:",		11,1);
		
		final JComboBox  TB_WHCD				= B00110FrameParts.JComboBoxSet( 100, 40,250,20,B00100DefaultVariable.WhList[0],	11);					//倉庫コード
		final JComboBox  TB_ShippingCompanyCd	= B00110FrameParts.JComboBoxSet( 100, 65,250,20,B00100DefaultVariable.ShippingCompanyList[0],	11);		//運送会社CD
		final JTextField TB_CarCd				= B00110FrameParts.JTextFieldSet(100, 90,100,20,"",11,0);	//車輛CD
		final JTextField TB_CarName01			= B00110FrameParts.JTextFieldSet(100,115,250,20,"",11,0);	//車輛名01
		final JTextField TB_CarName02			= B00110FrameParts.JTextFieldSet(100,140,250,20,"",11,0);	//車輛名02
		final JTextField TB_CarName03			= B00110FrameParts.JTextFieldSet(100,165,250,20,"",11,0);	//車輛名03
		final JTextField TB_DriverCd			= B00110FrameParts.JTextFieldSet(100,190,100,20,"",11,0);	//乗務員CD
		final JTextField TB_PTMSCD				= B00110FrameParts.JTextFieldSet(100,215,100,20,"",11,0);	//基幹SYS車輛Cd
		final JTextField TB_EntryDate			= B00110FrameParts.JTextFieldSet(100,240,250,20,"",11,0);	//データ登録日時
		final JTextField TB_UpdateDate			= B00110FrameParts.JTextFieldSet(100,265,250,20,"",11,0);	//データ更新日時
		final JTextField TB_EntryUser			= B00110FrameParts.JTextFieldSet(100,290,250,20,"",11,0);	//登録者コード
		final JTextField TB_UpdateUser			= B00110FrameParts.JTextFieldSet(100,315,250,20,"",11,0);	//更新者コード
		final JComboBox  TB_DelFg				= B00110FrameParts.JComboBoxSet( 100,340,150,20,B00100DefaultVariable.DelList[0],	11);
		
		TB_WHCD.setSelectedIndex(0);
		TB_ShippingCompanyCd.setSelectedIndex(0);
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
		
		if(!"".equals(TgtWhCd) && !"".equals(TgtShippingCompanyCd) && !"".equals(TgtCarCd)) {
			ArrayList<String> SearchWHCD = new ArrayList<String>();
			ArrayList<String> SearchShippingCompanyCd = new ArrayList<String>();
			ArrayList<String> SearchCarCd = new ArrayList<String>();
			ArrayList<String> SearchCarName = new ArrayList<String>();
			ArrayList<String> SearchDelFg = new ArrayList<String>();
			boolean AllSearch = false;
			
			SearchWHCD.add(TgtWhCd);
			SearchShippingCompanyCd.add(TgtShippingCompanyCd);
			SearchCarCd.add(TgtCarCd);
			
			Object[][] CarMstRt = M00031CarMstRt.CarMstRt(
					SearchWHCD,
					SearchShippingCompanyCd,
					SearchCarCd,
					SearchCarName,
					SearchDelFg,
					AllSearch);
			if(0<CarMstRt.length) {
				for(int i=0;i<B00100DefaultVariable.WhList[1].length;i++) {
					if((""+CarMstRt[0][ 0]).equals(""+B00100DefaultVariable.WhList[1][i])) {
						TB_WHCD.setSelectedIndex(i);
					}
				}
				for(int i=0;i<B00100DefaultVariable.ShippingCompanyList[1].length;i++) {
					if((""+CarMstRt[0][ 1]).equals(""+B00100DefaultVariable.ShippingCompanyList[1][i])) {
						TB_ShippingCompanyCd.setSelectedIndex(i);
					}
				}
				TB_CarCd.setText(""+CarMstRt[0][ 5]);		//車輛CD
				TB_CarName01.setText(""+CarMstRt[0][ 6]);	//車輛名01
				TB_CarName02.setText(""+CarMstRt[0][ 7]);	//車輛名02
				TB_CarName03.setText(""+CarMstRt[0][ 8]);	//車輛名03
				TB_DriverCd.setText(""+CarMstRt[0][ 9]);	//乗務員CD
				TB_PTMSCD.setText(""+CarMstRt[0][13]);		//基幹SYS車輛Cd
				TB_EntryDate.setText(""+CarMstRt[0][14]);	//データ登録日時
				TB_UpdateDate.setText(""+CarMstRt[0][15]);	//データ更新日時
				TB_EntryUser.setText(""+CarMstRt[0][16]);	//登録者コード
				TB_UpdateUser.setText(""+CarMstRt[0][17]);	//更新者コード
				
				for(int i=0;i<B00100DefaultVariable.DelList[1].length;i++) {
					if((""+CarMstRt[0][18]).equals(""+B00100DefaultVariable.DelList[1][i])) {
						TB_DelFg.setSelectedIndex(i);
					}
				}
				
				TB_WHCD.setEnabled(false);
				TB_ShippingCompanyCd.setEnabled(false);
				TB_CarCd.setEnabled(false);
			}
		}
		
		TB_EntryDate.setEditable(false);	//データ登録日時
		TB_UpdateDate.setEditable(false);	//データ更新日時
		TB_EntryUser.setEditable(false);	//登録者コード
		TB_UpdateUser.setEditable(false);	//更新者コード

		main_fm.add(LB_WHCD);
		main_fm.add(LB_ShippingCompanyCd);
		main_fm.add(LB_CarCd);
		main_fm.add(LB_CarName01);
		main_fm.add(LB_CarName02);
		main_fm.add(LB_CarName03);
		main_fm.add(LB_DriverCd);
		main_fm.add(LB_PTMSCD);
		main_fm.add(LB_EntryDate);
		main_fm.add(LB_UpdateDate);
		main_fm.add(LB_EntryUser);
		main_fm.add(LB_UpdateUser);
		main_fm.add(LB_DelFg);
		
		main_fm.add(TB_WHCD);
		main_fm.add(TB_ShippingCompanyCd);
		main_fm.add(TB_CarCd);
		main_fm.add(TB_CarName01);
		main_fm.add(TB_CarName02);
		main_fm.add(TB_CarName03);
		main_fm.add(TB_DriverCd);
		main_fm.add(TB_PTMSCD);
		main_fm.add(TB_EntryDate);
		main_fm.add(TB_UpdateDate);
		main_fm.add(TB_EntryUser);
		main_fm.add(TB_UpdateUser);
		main_fm.add(TB_DelFg);
		
		JButton Check_btn = B00110FrameParts.BtnSet(B00110FrameParts.Width-140, 90,100,20,"CD確認",11);			//車輛CDの存在確認
		main_fm.add(Check_btn);
		
		JButton CdRenew_btn = B00110FrameParts.BtnSet(B00110FrameParts.Width-140,115,100,20,"CD修正Mode",11);		//車輛CDの存在確認
		main_fm.add(CdRenew_btn);
		
		JButton NewCreate_btn = B00110FrameParts.BtnSet(B00110FrameParts.Width-140,140,100,20,"新規登録Mode",10);	//新規登録モードへ
		main_fm.add(NewCreate_btn);
		
		main_fm.setVisible(true);
		RenewFg = true;
		
		//コード変更を許可する
		CdRenew_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				TB_WHCD.setEnabled(true);
				TB_ShippingCompanyCd.setEnabled(true);
				TB_CarCd.setEnabled(true);
			}
		});
		
		//新規登録ボタン
		NewCreate_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				WM00036CarMstRenewAndCreate.CarMstRenewAndCreate(0, 0,"","","");
			}
		});
		
		//チェックボタン押下時の挙動
		Check_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				String GetWHCD				= ""+B00100DefaultVariable.WhList[1][TB_WHCD.getSelectedIndex()];
				String GetShippingCompanyCd	= ""+B00100DefaultVariable.ShippingCompanyList[1][TB_ShippingCompanyCd.getSelectedIndex()];
				String GetCarCd				= TB_CarCd.getText();
				
				if(null==GetWHCD				){GetWHCD 				= "";}
				if(null==GetShippingCompanyCd	){GetShippingCompanyCd 	= "";}
				if(null==GetCarCd				){GetCarCd 				= "";}
				
				GetCarCd				= B00020ToolsTextControl.only1byte_String(GetCarCd);
				
				GetWHCD					= B00020ToolsTextControl.Trim(GetWHCD);
				GetShippingCompanyCd	= B00020ToolsTextControl.Trim(GetShippingCompanyCd);
				GetCarCd				= B00020ToolsTextControl.Trim(GetCarCd);
				
				if(!"".equals(GetCarCd)) {
					ArrayList<String> SearchWHCD = new ArrayList<String>();
					ArrayList<String> SearchShippingCompanyCd = new ArrayList<String>();
					ArrayList<String> SearchCarCd = new ArrayList<String>();
					ArrayList<String> SearchCarName = new ArrayList<String>();
					ArrayList<String> SearchDelFg = new ArrayList<String>();
					boolean AllSearch = false;
					
					SearchWHCD.add(GetWHCD);
					SearchShippingCompanyCd.add(GetShippingCompanyCd);
					SearchCarCd.add(GetCarCd);
					
					Object[][] CarMstRt = M00031CarMstRt.CarMstRt(
							SearchWHCD,
							SearchShippingCompanyCd,
							SearchCarCd,
							SearchCarName,
							SearchDelFg,
							AllSearch);
					if(0<CarMstRt.length) {
						boolean KickFg = false;
						int option = JOptionPane.showConfirmDialog(null, "既に登録されている車輛コードです\n現在の登録情報をセットしますか？","更新確認", JOptionPane.YES_NO_OPTION,
							      JOptionPane.WARNING_MESSAGE);
						if (option == JOptionPane.YES_OPTION){
							KickFg = true;
						}else {
							KickFg = false;
						}
						
						if(KickFg) {
							for(int i=0;i<B00100DefaultVariable.WhList[1].length;i++) {
								if((""+CarMstRt[0][ 0]).equals(""+B00100DefaultVariable.WhList[1][i])) {
									TB_WHCD.setSelectedIndex(i);
								}
							}
							for(int i=0;i<B00100DefaultVariable.ShippingCompanyList[1].length;i++) {
								if((""+CarMstRt[0][ 1]).equals(""+B00100DefaultVariable.ShippingCompanyList[1][i])) {
									TB_ShippingCompanyCd.setSelectedIndex(i);
								}
							}
							TB_CarCd.setText(""+CarMstRt[0][ 5]);		//車輛CD
							TB_CarName01.setText(""+CarMstRt[0][ 6]);	//車輛名01
							TB_CarName02.setText(""+CarMstRt[0][ 7]);	//車輛名02
							TB_CarName03.setText(""+CarMstRt[0][ 8]);	//車輛名03
							TB_DriverCd.setText(""+CarMstRt[0][ 9]);	//乗務員CD
							TB_PTMSCD.setText(""+CarMstRt[0][13]);		//基幹SYS車輛Cd
							TB_EntryDate.setText(""+CarMstRt[0][14]);	//データ登録日時
							TB_UpdateDate.setText(""+CarMstRt[0][15]);	//データ更新日時
							TB_EntryUser.setText(""+CarMstRt[0][16]);	//登録者コード
							TB_UpdateUser.setText(""+CarMstRt[0][17]);	//更新者コード
							
							for(int i=0;i<B00100DefaultVariable.DelList[1].length;i++) {
								if((""+CarMstRt[0][18]).equals(""+B00100DefaultVariable.DelList[1][i])) {
									TB_DelFg.setSelectedIndex(i);
								}
							}
							
							TB_WHCD.setEnabled(false);
							TB_ShippingCompanyCd.setEnabled(false);
							TB_CarCd.setEnabled(false);
						}
					}else {
						JOptionPane.showMessageDialog(null, "新規車輛コードです");
					}
				}
			}
		});
		
		
		//登録ボタン押下時の挙動
		entry_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
				
					String GetWHCD				= ""+B00100DefaultVariable.WhList[1][TB_WHCD.getSelectedIndex()];
					String GetShippingCompanyCd	= ""+B00100DefaultVariable.ShippingCompanyList[1][TB_ShippingCompanyCd.getSelectedIndex()];
					String GetCarCd				= TB_CarCd.getText();
					String GetCarName01			= TB_CarName01.getText();
					String GetCarName02			= TB_CarName02.getText();
					String GetCarName03			= TB_CarName03.getText();
					String GetDriverCd			= TB_DriverCd.getText();
					String GetPTMSCD			= TB_PTMSCD.getText();
					String GetDelFg				= B00100DefaultVariable.DelList[1][TB_DelFg.getSelectedIndex()];
					
					if(null==GetWHCD				){GetWHCD 				= "";}
					if(null==GetShippingCompanyCd	){GetShippingCompanyCd 	= "";}
					if(null==GetCarCd				){GetCarCd 				= "";}
					if(null==GetCarName01			){GetCarName01 			= "";}
					if(null==GetCarName02			){GetCarName02 			= "";}
					if(null==GetCarName03			){GetCarName03 			= "";}
					if(null==GetDriverCd			){GetDriverCd 			= "";}
					if(null==GetPTMSCD				){GetPTMSCD 			= "";}
					if(null==GetDelFg				){GetDelFg 				= "";}
					
					GetCarCd				= B00020ToolsTextControl.only1byte_String(GetCarCd);
					
					GetWHCD					= B00020ToolsTextControl.Trim(GetWHCD);
					GetShippingCompanyCd	= B00020ToolsTextControl.Trim(GetShippingCompanyCd);
					GetCarCd				= B00020ToolsTextControl.Trim(GetCarCd);
					GetCarName01			= B00020ToolsTextControl.Trim(GetCarName01);
					GetCarName02			= B00020ToolsTextControl.Trim(GetCarName02);
					GetCarName03			= B00020ToolsTextControl.Trim(GetCarName03);
					GetDriverCd				= B00020ToolsTextControl.Trim(GetDriverCd);
					GetPTMSCD				= B00020ToolsTextControl.Trim(GetPTMSCD);
					GetDelFg				= B00020ToolsTextControl.Trim(GetDelFg);
					
					boolean KickFg = false;
					if("".equals(GetCarCd)) {
						JOptionPane.showMessageDialog(null, "車輛コードは必須です");
					}else {
						KickFg = true;
					}
					
					if(KickFg) {
						KickFg = false;
						ArrayList<String> SearchWHCD = new ArrayList<String>();
						ArrayList<String> SearchShippingCompanyCd = new ArrayList<String>();
						ArrayList<String> SearchCarCd = new ArrayList<String>();
						ArrayList<String> SearchCarName = new ArrayList<String>();
						ArrayList<String> SearchDelFg = new ArrayList<String>();
						boolean AllSearch = false;
						
						SearchWHCD.add(GetWHCD);
						SearchShippingCompanyCd.add(GetShippingCompanyCd);
						SearchCarCd.add(GetCarCd);
						
						Object[][] CarMstRt = M00031CarMstRt.CarMstRt(
								SearchWHCD,
								SearchShippingCompanyCd,
								SearchCarCd,
								SearchCarName,
								SearchDelFg,
								AllSearch);
						
						if(0<CarMstRt.length) {
							int option = JOptionPane.showConfirmDialog(null, "既に登録されている車輛コードです\n登録情報で上書きしますか？","更新確認", JOptionPane.YES_NO_OPTION,
								      JOptionPane.WARNING_MESSAGE);
							if (option == JOptionPane.YES_OPTION){
								KickFg = true;
							}else {
								KickFg = false;
							}
						}else {
							KickFg = true;
						}
						
						if(KickFg) {
							String now_dtm = B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[1];
							String[][] SetString = {
									{"WHCD"					,"1","1",GetWHCD}				//担当倉庫
									,{"ShippingCompanyCd"	,"1","1",GetShippingCompanyCd}	//運送会社CD
									,{"CarCd"				,"1","1",GetCarCd}				//車輛CD
									,{"CarName01"			,"1","1",GetCarName01}			//車輛名01
									,{"CarName02"			,"1","1",GetCarName02}			//車輛名02
									,{"CarName03"			,"1","1",GetCarName03}			//車輛名03
									,{"DriverCd"			,"1","1",GetDriverCd}			//乗務員CD
									,{"PTMSCD"				,"1","1",GetPTMSCD}				//みらいシステム車輛コード
									,{"EntryDate"			,"1","0",now_dtm}				//データ登録日時
									,{"UpdateDate"			,"1","1",now_dtm}				//データ更新日時
									,{"EntryUser"			,"1","0","(" + A00000Main.LoginUserId + ")" + A00000Main.LoginUserName}	//登録者コード
									,{"UpdateUser"			,"1","1","(" + A00000Main.LoginUserId + ")" + A00000Main.LoginUserName}	//更新者コード
									,{"DelFg"				,"1","1",GetDelFg}	//削除フラグ
							};
							
							String tgt_table = "KM0071_CARMST";
							String[][] field_name = new String[SetString.length][3];
							String[][] entry_data = new String[1][SetString.length];
							String[] judg_field = new String[3];
							String[][] judg_data = new String[1][3];
							String TgtDB = "NYANKO";
							int non_msg_fg = 1;
							
							judg_field[0] = "WHCD";
							judg_field[1] = "ShippingCompanyCd";
							judg_field[2] = "CarCd";
							
							judg_data[0][0] = GetWHCD;
							judg_data[0][1] = GetShippingCompanyCd;
							judg_data[0][2] = GetCarCd;
							
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
							WM00036CarMstRenewAndCreate.CarMstRenewAndCreate(0, 0,GetWHCD,GetShippingCompanyCd,GetCarCd);
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

				main_fm.setVisible(false);
				main_fm.dispose();
				WM00035CarMstSearch.CarMstSearch(0, 0);
			}
		});
	}
}