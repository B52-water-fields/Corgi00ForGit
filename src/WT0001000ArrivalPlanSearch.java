import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WT0001000ArrivalPlanSearch{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	public static void ArrivalPlanSearch(int x,int y) {
		A00000Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		
		final JFrame main_fm = B00110FrameParts.FrameCreate(x,y,900,750,"Corgi00入荷予定検索","");
		JLabel userinfo = B00110FrameParts.UserInfo();
		JButton exit_btn = B00110FrameParts.ExitBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		
		//検索条件パネル
		JPanel PN_Search = B00110FrameParts.JPanelSet(10,40,880,250,"White");
		JLabel PN_SearchLabel = B00110FrameParts.JLabelSet(10,0,150,20,"検索条件",11,0);
		/*
		//検索条件
		JLabel LB_SearchClWh			= B00110FrameParts.JLabelSet(  0, 25,100,20,"担当倉庫:"			,11,1);
		JLabel LB_SearchClCd			= B00110FrameParts.JLabelSet(  0, 25,100,20,"荷主:"				,11,1);
		JLabel LB_SearchClGpCD			= B00110FrameParts.JLabelSet(  0, 25,100,20,"荷主グループ:"		,11,1);
		JLabel LB_SearchArrNo			= B00110FrameParts.JLabelSet(  0, 25,100,20,"入荷予定NO:"		,11,1);
		JLabel LB_SearchClArrNo			= B00110FrameParts.JLabelSet(  0, 25,100,20,"荷主予定番号:"		,11,1);
		JLabel LB_SearchPlanDate		= B00110FrameParts.JLabelSet(  0, 25,100,20,"入荷予定日:"		,11,1);
		JLabel LB_SearchHdActualDate	= B00110FrameParts.JLabelSet(  0, 25,100,20,"ヘッダ入荷実績日:"	,11,1);
		JLabel LB_SearchSpCd			= B00110FrameParts.JLabelSet(  0, 25,100,20,"仕入先CD:"			,11,1);
		JLabel LB_SearchSpName			= B00110FrameParts.JLabelSet(  0, 25,100,20,"仕入先名:"			,11,1);
		JLabel LB_SearchSpPost			= B00110FrameParts.JLabelSet(  0, 25,100,20,"仕入先郵便:"		,11,1);
		JLabel LB_SearchSpAdd			= B00110FrameParts.JLabelSet(  0, 25,100,20,"仕入先住所:"		,11,1);
		JLabel LB_SearchSpTel			= B00110FrameParts.JLabelSet(  0, 25,100,20,"仕入先電話:"		,11,1);
		JLabel LB_SearchArCom			= B00110FrameParts.JLabelSet(  0, 25,100,20,"コメント:"			,11,1);
		JLabel LB_SearchFixFg			= B00110FrameParts.JLabelSet(  0, 25,100,20,"状況:"				,11,1);
				
		JLabel LB_SearchMsNo= B00110FrameParts.JLabelSet(	                  0,25,100,20,"明細番号:"	,11,1);
		JLabel LB_SearchItemCd= B00110FrameParts.JLabelSet(	                  0,25,100,20,"商品コード:"	,11,1);
		JLabel LB_SearchClItemCd= B00110FrameParts.JLabelSet(	                  0,25,100,20,"荷主商品コード:"	,11,1);
		JLabel LB_SearchJanCd= B00110FrameParts.JLabelSet(	                  0,25,100,20,"JANCD（バラ）:"	,11,1);
		JLabel LB_SearchItemMdNo= B00110FrameParts.JLabelSet(	                  0,25,100,20,"商品型番:"	,11,1);
		JLabel LB_SearchItemName= B00110FrameParts.JLabelSet(	                  0,25,100,20,"商品名:"	,11,1);
		JLabel LB_Searchlot= B00110FrameParts.JLabelSet(	                  0,25,100,20,"ロット:"	,11,1);
		JLabel LB_SearchExpDate= B00110FrameParts.JLabelSet(	                  0,25,100,20,"消費期限:"	,11,1);
		JLabel LB_SearchPlanQty= B00110FrameParts.JLabelSet(	                  0,25,100,20,"予定数量:"	,11,1);
		JLabel LB_SearchActualQty= B00110FrameParts.JLabelSet(	                  0,25,100,20,"実績数:"	,11,1);
		JLabel LB_SearchActualDate= B00110FrameParts.JLabelSet(	                  0,25,100,20,"入荷日:"	,11,1);
		JLabel LB_SearchCom= B00110FrameParts.JLabelSet(	                  0,25,100,20,"コメント:"	,11,1);
		JLabel LB_SearchEntryDate= B00110FrameParts.JLabelSet(	                  0,25,100,20,"登録日:"	,11,1);
		JLabel LB_SearchUpdateDate= B00110FrameParts.JLabelSet(	                  0,25,100,20,"更新日:"	,11,1);
		JLabel LB_SearchEntryUser= B00110FrameParts.JLabelSet(	                  0,25,100,20,"登録者:"	,11,1);
		JLabel LB_SearchUpdateUser= B00110FrameParts.JLabelSet(	                  0,25,100,20,"更新者:"	,11,1);
		
		final JComboBox TB_SearchClWh= B00110FrameParts.JTextFieldSet(	750, 25,100,20,""						,11,0);	//ヘッダ担当倉庫
		final JTextField TB_SearchClCd= B00110FrameParts.JTextFieldSet(	750, 25,100,20,""						,11,0);	//ヘッダ荷主CD
		final JTextField TB_SearchCLName01= B00110FrameParts.JTextFieldSet(	750, 25,100,20,""						,11,0);	//ヘッダ荷主名
		final JTextField TB_ SearchClGpCD= B00110FrameParts.JTextFieldSet(	750, 25,100,20,""						,11,0);	//ヘッダ荷主グループCD
		final JTextField TB_SearchCLGpName01= B00110FrameParts.JTextFieldSet(	750, 25,100,20,""						,11,0);	//ヘッダ荷主グループ名1
		final JTextField TB_SearchArrNo= B00110FrameParts.JTextFieldSet(	750, 25,100,20,""						,11,0);	//ヘッダ入荷予定NO
		final JTextField TB_SearchClArrNo= B00110FrameParts.JTextFieldSet(	750, 25,100,20,""						,11,0);	//ヘッダ荷主予定番号
		final JFormattedTextField TB_SearchPlanDateMin,		//ヘッダ入荷予定日
		final JFormattedTextField TB_SearchPlanDateMax,		//ヘッダ入荷予定日
		ArrayList<String> SearchHdActualDateMin,	//ヘッダ入荷実績日
		ArrayList<String> SearchHdActualDateMax,	//ヘッダ入荷実績日
		ArrayList<String> SearchSpCd,				//ヘッダ仕入先CD
		ArrayList<String> SearchSpName,				//ヘッダ仕入先名
		ArrayList<String> SearchSpPost,				//ヘッダ仕入先郵便
		ArrayList<String> SearchSpAdd,				//ヘッダ仕入先住所
		ArrayList<String> SearchSpTel,				//ヘッダ仕入先電話
		ArrayList<String> SearchArCom,				//ヘッダコメント
		*/
		final JComboBox TB_SearchFixFg= B00110FrameParts.JComboBoxSet(	440,150,180,20,B00100DefaultVariable.SearchArryvalFixFgList[0],11);				//状況 
		/*
		ArrayList<Integer> SearchMsNoMin,			//明細番号最小
		ArrayList<Integer> SearchMsNoMax,			//明細番号最大
		ArrayList<String> SearchItemCd,				//商品コード
		ArrayList<String> SearchClItemCd,			//荷主商品コード
		ArrayList<String> SearchJanCd,				//JANCD（バラ）
		ArrayList<String> SearchItemMdNo,			//商品型番
		ArrayList<String> SearchItemName,			//商品名
		ArrayList<String> Searchlot,				//ロット
		ArrayList<String> SearchExpDateMin,			//消費期限最小
		ArrayList<String> SearchExpDateMax,			//消費期限最大
		ArrayList<Integer> SearchPlanQtyMin,		//予定数量最小
		ArrayList<Integer> SearchPlanQtyMax,		//予定数量最大
		ArrayList<Integer> SearchActualQtyMin,		//実績数
		ArrayList<Integer> SearchActualQtyMax,		//実績数
		ArrayList<String> SearchActualDateMin,		//入荷日
		ArrayList<String> SearchActualDateMax,		//入荷日
		ArrayList<String> SearchCom,				//コメント
		ArrayList<String> SearchEntryDateMin,		//登録日
		ArrayList<String> SearchEntryDateMax,		//登録日
		ArrayList<String> SearchUpdateDateMin,		//更新日
		ArrayList<String> SearchUpdateDateMax,		//更新日
		ArrayList<String> SearchEntryUser,			//登録者
		ArrayList<String> SearchUpdateUser,			//更新者
		
		*/
		
		
		PN_Search.add(PN_SearchLabel);
		
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
}