import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
		
		
		main_fm.setVisible(true);
		RenewFg = true;
		
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