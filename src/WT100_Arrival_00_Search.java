import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class WT100_Arrival_00_Search{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	static boolean MsViewMode;
	public static void ArrivalSearch(int x,int y) {
		A00000_Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		MsViewMode = false;
		final JFrame main_fm = B100_FrameParts.FrameCreate(x,y,1200,800,"Corgi00入荷実績検索　WT100_ArrivalPlan_00_Search","NK");
		JLabel userinfo = B100_FrameParts.UserInfo();
		JButton exit_btn = B100_FrameParts.ExitBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		
		//検索条件パネル
		JPanel PN_Search = B100_FrameParts.JPanelSet(10,40,1160,300,"White");
		JLabel PN_SearchLabel = B100_FrameParts.JLabelSet(0,0,100,20,"検索条件",11,0);
		PN_Search.add(PN_SearchLabel);
		
		//検索条件
		JLabel LB_SearchClWh			= B100_FrameParts.JLabelSet(  0, 25,100,20,"担当倉庫:"			,11,1);
		JLabel LB_SearchClCd			= B100_FrameParts.JLabelSet(  0, 50,100,20,"荷主CD:"			,11,1);
		JLabel LB_SearchClGpCD			= B100_FrameParts.JLabelSet(  0, 75,100,20,"荷主グループ:"		,11,1);
		JLabel LB_SearchSpCd			= B100_FrameParts.JLabelSet(  0,100,100,20,"仕入先CD:"			,11,1);
		JLabel LB_SearchArrNo			= B100_FrameParts.JLabelSet(  0,125,100,20,"入荷予定NO:"		,11,1);
		JLabel LB_SearchArrCountMin		= B100_FrameParts.JLabelSet(  0,125,100,20,"入荷予定枝番:"		,11,1);
		JLabel LB_SearchClArrNo			= B100_FrameParts.JLabelSet(  0,150,100,20,"荷主予定番号:"		,11,1);
		JLabel LB_SearchPlanDateMin		= B100_FrameParts.JLabelSet(  0,175,100,20,"入荷予定日:"		,11,1);
		JLabel LB_SearchActualDateMin	= B100_FrameParts.JLabelSet(  0,200,100,20,"入荷実績日:"		,11,1);
		
		
		JLabel LB_SearchItemCd			= B100_FrameParts.JLabelSet(340, 25,100,20,"商品コード:"		,11,1);
		JLabel LB_SearchClItemCd		= B100_FrameParts.JLabelSet(340, 50,100,20,"荷主商品コード:"	,11,1);
		JLabel LB_SearchItemName		= B100_FrameParts.JLabelSet(340, 75,100,20,"商品名:"			,11,1);
		JLabel LB_SearchLot				= B100_FrameParts.JLabelSet(340,100,100,20,"ロット:"			,11,1);
		JLabel LB_SearchExpDate			= B100_FrameParts.JLabelSet(340,125,100,20,"消費期限:"			,11,1);
		
		JLabel LB_SearchCom				= B100_FrameParts.JLabelSet(340,150,100,20,"コメント:"			,11,1);
		JLabel LB_SearchEntryDateMin	= B100_FrameParts.JLabelSet(340,175,100,20,"登録日:"			,11,1);
		JLabel LB_SearchUpdateDateMin	= B100_FrameParts.JLabelSet(340,200,100,20,"更新日:"			,11,1);
		JLabel LB_SearchEntryUser		= B100_FrameParts.JLabelSet(340,225,100,20,"登録者:"			,11,1);
		JLabel LB_SearchUpdateUser		= B100_FrameParts.JLabelSet(340,250,100,20,"更新者:"			,11,1);
		
		final JComboBox TB_SearchClWh						= B100_FrameParts.JComboBoxSet(				100, 25,240,20,B100_DefaultVariable.SearchWhList[0],11);
		final JComboBox TB_SearchClCd						= B100_FrameParts.JComboBoxSet(				100, 50,240,20,B100_DefaultVariable.SearchClList[0],11);
		final JComboBox TB_SearchClGpCD						= B100_FrameParts.JComboBoxSet(				100, 75,240,20,B100_DefaultVariable.SearchClGpList[0],11);
		final JComboBox TB_SearchSpCd						= B100_FrameParts.JComboBoxSet(				100,100,240,20,B100_DefaultVariable.SearchSupplierList[0],11);
		final JTextField TB_SearchArrNo						= B100_FrameParts.JTextFieldSet(				100,125,100,20,"",11,0);
		final JTextField TB_SearchArrCountMin				= B100_FrameParts.JTextFieldSet(				100,125, 70,20,"",11,0);
		final JTextField TB_SearchArrCountMax				= B100_FrameParts.JTextFieldSet(				210,125, 70,20,"",11,0);
		final JTextField TB_SearchClArrNo					= B100_FrameParts.JTextFieldSet(				100,150,100,20,"",11,0);
		final JTextField TB_SearchPlanDateMin				= B100_FrameParts.JFormattedTextFieldSet(	100,175, 70,20,"",11,0,"YYYY/MM/DD");
		final JTextField TB_SearchPlanDateMax				= B100_FrameParts.JFormattedTextFieldSet(	210,175, 70,20,"",11,0,"YYYY/MM/DD");
		final JTextField TB_SearchActualDateMin				= B100_FrameParts.JFormattedTextFieldSet(	100,200, 70,20,"",11,0,"YYYY/MM/DD");
		final JTextField TB_SearchActualDateMax				= B100_FrameParts.JFormattedTextFieldSet(	210,200, 70,20,"",11,0,"YYYY/MM/DD");
		
		
		final JTextField TB_SearchItemCd					= B100_FrameParts.JTextFieldSet(				780,100,100,20,"",11,0);
		final JTextField TB_SearchClItemCd					= B100_FrameParts.JTextFieldSet(				780,100,100,20,"",11,0);
		final JTextField TB_SearchItemName					= B100_FrameParts.JTextFieldSet(				780,100,100,20,"",11,0);
		final JTextField TB_SearchLot						= B100_FrameParts.JTextFieldSet(				780,100,100,20,"",11,0);
		final JFormattedTextField TB_SearchExpDateMin		= B100_FrameParts.JFormattedTextFieldSet(	680,250, 70,20,"",11,0,"YYYY/MM/DD");
		final JFormattedTextField TB_SearchExpDateMax		= B100_FrameParts.JFormattedTextFieldSet(	680,250, 70,20,"",11,0,"YYYY/MM/DD");
		
		
		final JTextField TB_SearchCom						= B100_FrameParts.JTextFieldSet(				780,100,100,20,"",11,0);
		final JFormattedTextField TB_SearchEntryDateMin		= B100_FrameParts.JFormattedTextFieldSet(	680,250,150,20,"",11,0,"YYYY/MM/DD HH:MM:SS");
		final JFormattedTextField TB_SearchEntryDateMax		= B100_FrameParts.JFormattedTextFieldSet(	680,250,150,20,"",11,0,"YYYY/MM/DD HH:MM:SS");
		final JFormattedTextField TB_SearchUpdateDateMin	= B100_FrameParts.JFormattedTextFieldSet(	680,250,150,20,"",11,0,"YYYY/MM/DD HH:MM:SS");
		final JFormattedTextField TB_SearchUpdateDateMax	= B100_FrameParts.JFormattedTextFieldSet(	680,250,150,20,"",11,0,"YYYY/MM/DD HH:MM:SS");
		final JTextField TB_SearchEntryUser					= B100_FrameParts.JTextFieldSet(				780,100,100,20,"",11,0);
		final JTextField TB_SearchUpdateUser				= B100_FrameParts.JTextFieldSet(				780,100,100,20,"",11,0);
		
		
		JLabel LB2_SearchArrNo			= B100_FrameParts.JLabelSet(  0,125,100,20,"と一致"		,11,1);
		JLabel LB2_SearchArrCountMin		= B100_FrameParts.JLabelSet(  0,125,100,20,"～"		,11,1);
		JLabel LB2_SearchClArrNo			= B100_FrameParts.JLabelSet(  0,150,100,20,"と一致"		,11,1);
		JLabel LB2_SearchPlanDateMin		= B100_FrameParts.JLabelSet(  0,175,100,20,"～"		,11,1);
		JLabel LB2_SearchActualDateMin	= B100_FrameParts.JLabelSet(  0,200,100,20,"～"		,11,1);
		
		
		JLabel LB2_SearchItemCd			= B100_FrameParts.JLabelSet(340, 25,100,20,"と一致"		,11,1);
		JLabel LB2_SearchClItemCd		= B100_FrameParts.JLabelSet(340, 50,100,20,"と一致"	,11,1);
		JLabel LB2_SearchItemName		= B100_FrameParts.JLabelSet(340, 75,100,20,"を含む"			,11,1);
		JLabel LB2_SearchLot				= B100_FrameParts.JLabelSet(340,100,100,20,"と一致"			,11,1);
		JLabel LB2_SearchExpDate			= B100_FrameParts.JLabelSet(340,125,100,20,"～"			,11,1);
		
		JLabel LB2_SearchCom				= B100_FrameParts.JLabelSet(340,150,100,20,"を含む"			,11,1);
		JLabel LB2_SearchEntryDateMin	= B100_FrameParts.JLabelSet(340,175,100,20,"～"			,11,1);
		JLabel LB2_SearchUpdateDateMin	= B100_FrameParts.JLabelSet(340,200,100,20,"～"			,11,1);
		JLabel LB2_SearchEntryUser		= B100_FrameParts.JLabelSet(340,225,100,20,"を含む"			,11,1);
		JLabel LB2_SearchUpdateUser		= B100_FrameParts.JLabelSet(340,250,100,20,"を含む"			,11,1);
		

		PN_Search.add(LB_SearchClWh);
		PN_Search.add(LB_SearchClCd);
		PN_Search.add(LB_SearchClGpCD);
		PN_Search.add(LB_SearchSpCd);
		PN_Search.add(LB_SearchArrNo);
		PN_Search.add(LB_SearchArrCountMin);
		PN_Search.add(LB_SearchClArrNo);
		PN_Search.add(LB_SearchPlanDateMin);
		PN_Search.add(LB_SearchActualDateMin);
		
		
		PN_Search.add(LB_SearchItemCd);
		PN_Search.add(LB_SearchClItemCd);
		PN_Search.add(LB_SearchItemName);
		PN_Search.add(LB_SearchLot);
		PN_Search.add(LB_SearchExpDate);
		
		PN_Search.add(LB_SearchCom);
		PN_Search.add(LB_SearchEntryDateMin);
		PN_Search.add(LB_SearchUpdateDateMin);
		PN_Search.add(LB_SearchEntryUser);
		PN_Search.add(LB_SearchUpdateUser);
		
		
		
		PN_Search.add(TB_SearchClWh);
		PN_Search.add(TB_SearchClCd);
		PN_Search.add(TB_SearchClGpCD);
		PN_Search.add(TB_SearchSpCd);
		
		main_fm.add(PN_Search);
		
		/***********************************************
		詳細表示用
		***********************************************/
		final JFrame Ms_fm = B100_FrameParts.FrameCreate(x+20,y+20,800,830,"Corgi00入荷実績検索　WT100_ArrivalPlan_00_Search","NK");
		JLabel Msuserinfo = B100_FrameParts.UserInfo();
		JButton Msexit_btn = B100_FrameParts.ExitBtn();
		
		Ms_fm.add(Msuserinfo);
		Ms_fm.add(Msexit_btn);
		
		RenewFg = true;
		main_fm.setVisible(true);
		
		//EXITボタン押下時の挙動
		exit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();
				
				Ms_fm.setVisible(false);
				Ms_fm.dispose();

				main_fm.setVisible(false);
				main_fm.dispose();
				A00001_WorkMain.WorkMain(0,0);
			}
		});
	}
}