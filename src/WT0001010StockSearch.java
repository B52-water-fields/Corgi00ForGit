import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WT0001010StockSearch{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	public static void StockSearch(int x,int y) {
		A00000Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		
		final JFrame main_fm = B00110FrameParts.FrameCreate(x,y,1200,750,"Corgi00入荷予定検索","");
		JLabel userinfo = B00110FrameParts.UserInfo();
		JButton exit_btn = B00110FrameParts.ExitBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		
		//検索条件パネル
		JPanel PN_Search = B00110FrameParts.JPanelSet(10,40,1160,300,"White");
		JLabel PN_SearchLabel = B00110FrameParts.JLabelSet(0,0,100,20,"検索条件",11,0);
		PN_Search.add(PN_SearchLabel);
		
		String[] LocExactMatchList	= {"で始まる","と一致"};
		String[] SortModeList		= {"ロケ順","商品CD順"};
		
		//検索条件
		/*
		JLabel LB_SearchClCd		= B00110FrameParts.JLabelSet(  0, 25,100,20,"荷主コード:"		,11,1);
		JLabel LB_SearchWhCd		= B00110FrameParts.JLabelSet(  0, 50,100,20,"倉庫コード:"		,11,1);
		JLabel LB_SearchClGpCD		= B00110FrameParts.JLabelSet(  0, 75,100,20,"荷主グループ:"		,11,1);
		
		JLabel LB_SearchLoc			= B00110FrameParts.JLabelSet(  0,100,100,20,"ロケーション:"		,11,1);
		JLabel LB_SearchType		= B00110FrameParts.JLabelSet(  0,125,100,20,"ロケタイプ:"		,11,1);
		JLabel LB_SearchQty			= B00110FrameParts.JLabelSet(  0,150,100,20,"数量:"				,11,1);
		JLabel LB_SearchShipPlanQty	= B00110FrameParts.JLabelSet(  0,175,100,20,"引当済数:"			,11,1);
		JLabel LB_SearchPossibleQty	= B00110FrameParts.JLabelSet(  0,200,100,20,"出荷可能数:"		,11,1);
		
		JLabel LB_SearchLot			= B00110FrameParts.JLabelSet(  0,225,100,20,"ロット:"			,11,1);
		JLabel LB_SearchExpdate		= B00110FrameParts.JLabelSet(  0,250,100,20,"消費期限:"			,11,1);
		JLabel LB_SearchActualDate	= B00110FrameParts.JLabelSet(  0,275,100,20,"入荷実績日:"		,11,1);
		
		JLabel LB_SearchItemCd		= B00110FrameParts.JLabelSet(400, 25,100,20,"商品コード:"		,11,1);
		JLabel LB_SearchItemName	= B00110FrameParts.JLabelSet(400, 50,100,20,"商品名:"			,11,1);
		JLabel LB_SearchClItemCd	= B00110FrameParts.JLabelSet(400, 75,100,20,"荷主商品コード:"	,11,1);
		JLabel LB_SearchJanCd		= B00110FrameParts.JLabelSet(400,125,100,20,"BCD（バラ）:"		,11,1);
		JLabel LB_SearchItemMdNo	= B00110FrameParts.JLabelSet(400,150,100,20,"商品型番:"			,11,1);
		
		JLabel LB_SortItemcdMode	= B00110FrameParts.JLabelSet(400, 25,100,20,"並び順:"			,11,1);
		
		final JComboBox TB_SearchClCd		= B00110FrameParts.JComboBoxSet(	100,100,200,20,B00100DefaultVariable.SearchClList,11);		//荷主コード
		final JComboBox TB_SearchWhCd		= B00110FrameParts.JComboBoxSet(	100,100,200,20,B00100DefaultVariable.SearchWhList,11);		//倉庫コード
		final JComboBox TB_SearchClGpCD		= B00110FrameParts.JComboBoxSet(	100,100,200,20,B00100DefaultVariable.SearchClGpList,11);	//荷主グループ
		
		final JTextField TB_SearchLoc		= B00110FrameParts.JTextFieldSet(	200,100,100,20,""	,11,0);					//ロケーション
		final JComboBox TB_LocExactMatch	= B00110FrameParts.JComboBoxSet(	300,100,150,20,LocExactMatchList,11);		//ロケーション一致条件0:前方一致　1:完全一致
		
		final JFormattedTextField TB_SearchQtyMin			= B00110FrameParts.JFormattedTextFieldSet(	100,150, 70,20,"",11,1,"####");		//数量
		final JFormattedTextField TB_SearchQtyMax			= B00110FrameParts.JFormattedTextFieldSet(	210,150, 70,20,"",11,1,"####");		//数量
		final JFormattedTextField TB_SearchShipPlanQtyMin	= B00110FrameParts.JFormattedTextFieldSet(	100,175, 70,20,"",11,1,"####");		//引当済数
		final JFormattedTextField TB_SearchShipPlanQtyMax	= B00110FrameParts.JFormattedTextFieldSet(	210,175, 70,20,"",11,1,"####");		//引当済数
		final JFormattedTextField TB_SearchPossibleQtyMin	= B00110FrameParts.JFormattedTextFieldSet(	100,200, 70,20,"",11,1,"####");		//出荷可能数
		final JFormattedTextField TB_SearchPossibleQtyMax	= B00110FrameParts.JFormattedTextFieldSet(	210,200, 70,20,"",11,1,"####");		//出荷可能数
		
		final JTextField TB_SearchLot						= B00110FrameParts.JTextFieldSet(			100,225,100,20,""	,11,0);						//ロット
		final JFormattedTextField TB_SearchExpdateMin		= B00110FrameParts.JFormattedTextFieldSet(	100,250, 70,20,""	,11,0,"YYYY/MM/DD");		//消費期限
		final JFormattedTextField TB_SearchExpdateMax		= B00110FrameParts.JFormattedTextFieldSet(	100,250, 70,20,""	,11,0,"YYYY/MM/DD");		//消費期限
		final JFormattedTextField TB_SearchActualDateMin	= B00110FrameParts.JFormattedTextFieldSet(	100,275, 70,20,""	,11,0,"YYYY/MM/DD");		//入荷実績日
		final JFormattedTextField TB_SearchActualDateMax	= B00110FrameParts.JFormattedTextFieldSet(	100,275, 70,20,""	,11,0,"YYYY/MM/DD");		//入荷実績日
		
		final JComboBox TB_SearchClGpCD	= B00110FrameParts.JComboBoxSet(500,25,150,20,SortModeList,11);	//並び順 0:ロケ順 1:商品CD順
		
		JLabel LB2_SearchQty			= B00110FrameParts.JLabelSet(170,150,100,20,"～"		,11,2);
		JLabel LB2_SearchShipPlanQty	= B00110FrameParts.JLabelSet(170,175,100,20,"～"		,11,2);
		JLabel LB2_SearchPossibleQty	= B00110FrameParts.JLabelSet(170,200,100,20,"～"		,11,2);
		
		JLabel LB2_SearchLot			= B00110FrameParts.JLabelSet(200,225,100,20,"と一致"	,11,0);
		JLabel LB2_SearchExpdate		= B00110FrameParts.JLabelSet(170,250,100,20,"～"		,11,2);
		JLabel LB2_SearchActualDate		= B00110FrameParts.JLabelSet(170,275,100,20,"～"		,11,2);
		
		JLabel LB2_SearchItemCd			= B00110FrameParts.JLabelSet(500, 25,100,20,"と一致"	,11,0);
		JLabel LB2_SearchItemName		= B00110FrameParts.JLabelSet(500, 50,100,20,"を含む"	,11,0);
		JLabel LB2_SearchClItemCd		= B00110FrameParts.JLabelSet(500, 75,100,20,"と一致"	,11,0);
		JLabel LB2_SearchJanCd			= B00110FrameParts.JLabelSet(500,125,100,20,"と一致"	,11,0);
		JLabel LB2_SearchItemMdNo		= B00110FrameParts.JLabelSet(500,150,100,20,"と一致"	,11,0);
		*/
		
		
		
		
		//検索ボタン
		JButton SearchBtn 		= B00110FrameParts.BtnSet(100,275,100,20,"検索",11);
		PN_Search.add(SearchBtn);
		
		//条件クリアボタン
		JButton SearchCrearBtn 	= B00110FrameParts.BtnSet(100,0,100,20,"条件クリア",10);
		PN_Search.add(SearchCrearBtn);
		
		
		
		
		
		
		
		
		main_fm.add(PN_Search);
		
		RenewFg = true;
		main_fm.setVisible(true);
		
		
		
		

		//EXITボタン押下時の挙動
		exit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				W00030WorkMain.WorkMain(0,0);
			}
		});
	}
	
	private static int GetSelectIndex(String[] SelectList,String TgtData ) {
		int rt = 0;
		for(int i=0;i<SelectList.length;i++) {
			if(TgtData.equals(SelectList[i])) {
				rt	= i;
				i	= SelectList.length+1;
			}
		}
		return rt;
	}
}