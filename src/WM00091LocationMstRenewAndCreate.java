import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class WM00091LocationMstRenewAndCreate{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	public static void LocationMstRenewAndCreate(int x,int y,String TgtClCd,String TgtWhCd,String TgtLoc) {
		A00000Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		
		final JFrame main_fm = B00110FrameParts.FrameCreate(x,y,500,500,"Corgi00ロケーションマスタ登録・修正","");
		JLabel userinfo 	= B00110FrameParts.UserInfo();
		JButton exit_btn 	= B00110FrameParts.ExitBtn();
		JButton entry_btn 	= B00110FrameParts.EntryBtn();

		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		main_fm.add(entry_btn);
		
		JLabel LB_ClCd			= B00110FrameParts.JLabelSet(  0, 50,100,20,"荷主コード:"		,11,1);
		JLabel LB_WhCd			= B00110FrameParts.JLabelSet(  0, 75,100,20,"倉庫コード:"		,11,1);
		JLabel LB_Loc			= B00110FrameParts.JLabelSet(  0,100,100,20,"ロケーション:"	,11,1);
		JLabel LB_LocName		= B00110FrameParts.JLabelSet(  0,125,100,20,"ロケーション名:"	,11,1);
		JLabel LB_Type			= B00110FrameParts.JLabelSet(  0,150,100,20,"ロケタイプ:"		,11,1);
		
		JLabel LB_EntryDate		= B00110FrameParts.JLabelSet(  0,200,100,20,"登録日:"	,11,1);
		JLabel LB_UpdateDate	= B00110FrameParts.JLabelSet(  0,225,100,20,"更新日:"	,11,1);
		JLabel LB_EntryUser		= B00110FrameParts.JLabelSet(  0,250,100,20,"登録者:"	,11,1);
		JLabel LB_UpdateUser	= B00110FrameParts.JLabelSet(  0,275,100,20,"更新者:"	,11,1);
		
		final JComboBox   TB_ClCd		= B00110FrameParts.JComboBoxSet( 100, 50,200,20,B00100DefaultVariable.ClList[0],11);	//荷主コード
		final JComboBox   TB_WhCd		= B00110FrameParts.JComboBoxSet( 100, 75,200,20,B00100DefaultVariable.WhList[0],11);	//倉庫コード
		final JTextField  TB_Loc		= B00110FrameParts.JTextFieldSet(100,100,100,20,"",11,0);								//ロケーション
		final JTextField  TB_LocName	= B00110FrameParts.JTextFieldSet(100,125,100,20,"",11,0);								//ロケーション名
		final JComboBox   TB_Type		= B00110FrameParts.JComboBoxSet( 100,150,100,20,B00100DefaultVariable.LocType[0],11);//ロケタイプ
		
		final JTextField  TB_EntryDate	= B00110FrameParts.JTextFieldSet(100,200,200,20,"",11,0);	//登録日
		final JTextField  TB_UpdateDate	= B00110FrameParts.JTextFieldSet(100,225,200,20,"",11,0);	//更新日
		final JTextField  TB_EntryUser	= B00110FrameParts.JTextFieldSet(100,250,200,20,"",11,0);	//登録者
		final JTextField  TB_UpdateUser	= B00110FrameParts.JTextFieldSet(100,275,200,20,"",11,0);	//更新者
		
		TB_EntryDate.setEditable(false);
		TB_UpdateDate.setEditable(false);
		TB_EntryUser.setEditable(false);
		TB_UpdateUser.setEditable(false);
		
		if(null==TgtClCd) {TgtClCd	= "";}
		if(null==TgtWhCd) {TgtWhCd	= "";}
		if(null==TgtLoc	) {TgtLoc	= "";}
		
		if("".equals(TgtClCd)) {TgtClCd= A00000Main.ClCd;}
		if("".equals(TgtWhCd)) {TgtWhCd= A00000Main.ClWh;}
		
		for(int i=0;i<B00100DefaultVariable.ClList[1].length;i++) {
			if(TgtClCd.equals(B00100DefaultVariable.ClList[1][i])) {
				TB_ClCd.setSelectedIndex(i);
			}
		}
		for(int i=0;i<B00100DefaultVariable.WhList[1].length;i++) {
			if(TgtWhCd.equals(B00100DefaultVariable.WhList[1][i])) {
				TB_WhCd.setSelectedIndex(i);
			}
		}
		if("9".equals(A00000Main.LoginUserAuthorityFG)) {
			
		}else {
			TB_ClCd.setEditable(false);
			TB_WhCd.setEditable(false);
		}
		
		main_fm.add(LB_ClCd);
		main_fm.add(LB_WhCd);
		main_fm.add(LB_Loc);
		main_fm.add(LB_LocName);
		main_fm.add(LB_Type);
		
		main_fm.add(LB_EntryDate);
		main_fm.add(LB_UpdateDate);
		main_fm.add(LB_EntryUser);
		main_fm.add(LB_UpdateUser);
		
		main_fm.add(TB_ClCd);
		main_fm.add(TB_WhCd);
		main_fm.add(TB_Loc);
		main_fm.add(TB_LocName);
		main_fm.add(TB_Type);
		
		main_fm.add(TB_EntryDate);
		main_fm.add(TB_UpdateDate);
		main_fm.add(TB_EntryUser);
		main_fm.add(TB_UpdateUser);
		
		main_fm.setVisible(true);
		

		if("".equals(TgtLoc)) {
			TB_Loc.requestFocusInWindow();
		}else {
			TB_ClCd.setEnabled(false);
			TB_WhCd.setEnabled(false);
			TB_Loc.setEditable(false);
			TB_LocName.requestFocusInWindow();
		}
		RenewFg = true;
		
		//登録ボタン押下時の挙動
		entry_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				String GetClCd		= B00100DefaultVariable.ClList[1][TB_ClCd.getSelectedIndex()];	//荷主コード
				String GetWhCd		= B00100DefaultVariable.WhList[1][TB_WhCd.getSelectedIndex()];	//倉庫コード
				String GetLoc		= TB_Loc.getText();			//ロケーション
				String GetLocName	= TB_LocName.getText();		//ロケーション名
				String GetType		= B00100DefaultVariable.LocType[1][TB_Type.getSelectedIndex()];//ロケタイプ
				
				if(null==GetClCd	){GetClCd		= "";}
				if(null==GetWhCd	){GetWhCd		= "";}
				if(null==GetLoc		){GetLoc		= "";}
				if(null==GetLocName	){GetLocName	= "";}
				if(null==GetType	){GetType		= "";}
				
				GetClCd		= B00020ToolsTextControl.Trim(GetClCd);
				GetWhCd		= B00020ToolsTextControl.Trim(GetWhCd);
				GetLoc		= B00020ToolsTextControl.Trim(GetLoc);
				GetLocName	= B00020ToolsTextControl.Trim(GetLocName);
				GetType		= B00020ToolsTextControl.Trim(GetType);
				
				GetType		= B00020ToolsTextControl.num_only_String(GetType);
				if("".equals(GetType)) {GetType	="0";}
				
				if("".equals(GetLocName)) {GetLocName	= GetLoc;}
				
				if("".equals(GetLoc)) {
					
				}else {
					//現状のロケーション情報を取得して、ロケタイプが変更されていた場合在庫情報を修正する
					ArrayList<String> SearchClCd 	= new ArrayList<String>();	//荷主コード
					ArrayList<String> SearchWhCd 	= new ArrayList<String>();	//倉庫コード
					ArrayList<String> SearchLoc 	= new ArrayList<String>();	//ロケーション
					ArrayList<String> SearchLocName = new ArrayList<String>();	//ロケーション名
					ArrayList<String> SearchType 	= new ArrayList<String>();	//ロケタイプ
					boolean AllSearch = false;
					SearchClCd.add(GetClCd);
					SearchWhCd.add(GetWhCd);
					SearchLoc.add(GetLoc);
					
					Object[][] LocationMstRt = M00090LocationMstRt.LocationMstRt(
							SearchClCd,		//荷主コード
							SearchWhCd,		//倉庫コード
							SearchLoc,		//ロケーション
							SearchLocName,	//ロケーション名
							SearchType,		//ロケタイプ
							AllSearch);
					
					if(0<LocationMstRt.length) {
						if(GetType.equals(""+LocationMstRt[0][M00090LocationMstRt.ColType])) {
							
						}else {
							String BefoureType = ""+LocationMstRt[0][M00090LocationMstRt.ColType];
							StockTypeChange(
									GetClCd,
									GetWhCd,
									GetLoc,
									BefoureType,
									GetType
									);
						}
					}
					
					
					
					
					
					SetX=main_fm.getX();
					SetY=main_fm.getY();

					main_fm.setVisible(false);
					main_fm.dispose();
					LocationMstRenewAndCreate(0,0,GetClCd,GetWhCd,GetLoc);
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
				WM00090LocationMstSearch.LocationMstSearch(0, 0);
			}
		});
	}
	
	private static void StockTypeChange(
			String GetClCd,
			String GetWhCd,
			String GetLoc,
			String BefoureType,
			String AfterType
			) {
		
	}
	
	
	
}