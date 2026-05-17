import java.awt.event.ActionEvent;
import java.sql.Timestamp;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
		
		final JFrame main_fm = B00110FrameParts.FrameCreate(x,y,1200,750,"Corgi00入荷予定検索","");
		JLabel userinfo = B00110FrameParts.UserInfo();
		JButton exit_btn = B00110FrameParts.ExitBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		
		//検索条件パネル
		JPanel PN_Search = B00110FrameParts.JPanelSet(10,40,1160,300,"White");
		JLabel PN_SearchLabel = B00110FrameParts.JLabelSet(10,0,150,20,"検索条件",11,0);
		
		//検索条件
		JLabel LB_SearchClWh			= B00110FrameParts.JLabelSet(  0, 25,100,20,"担当倉庫:"			,11,1);
		JLabel LB_SearchClCd			= B00110FrameParts.JLabelSet(  0, 50,100,20,"荷主:"				,11,1);
		JLabel LB_SearchClGpCD			= B00110FrameParts.JLabelSet(  0, 75,100,20,"荷主グループ:"		,11,1);
		JLabel LB_SearchSpCd			= B00110FrameParts.JLabelSet(  0,100,100,20,"仕入先:"			,11,1);
		JLabel LB_SearchArrNo			= B00110FrameParts.JLabelSet(  0,125,100,20,"入荷予定NO:"		,11,1);
		JLabel LB_SearchClArrNo			= B00110FrameParts.JLabelSet(  0,150,100,20,"荷主予定番号:"		,11,1);
		JLabel LB_SearchPlanDate		= B00110FrameParts.JLabelSet(  0,175,100,20,"入荷予定日:"		,11,1);
		JLabel LB_SearchHdActualDate	= B00110FrameParts.JLabelSet(  0,200,100,20,"入荷実績日:"		,11,1);
		JLabel LB_SearchArCom			= B00110FrameParts.JLabelSet(  0,225,100,20,"ヘッダコメント:"	,10,1);
		
		JLabel LB_SearchItemCd			= B00110FrameParts.JLabelSet(340, 25,100,20,"商品コード:"		,11,1);
		JLabel LB_SearchClItemCd		= B00110FrameParts.JLabelSet(340, 50,100,20,"荷主商品コード:"	,10,1);
		JLabel LB_SearchJanCd			= B00110FrameParts.JLabelSet(340, 75,100,20,"JANCD（バラ）:"	,10,1);
		JLabel LB_SearchItemMdNo		= B00110FrameParts.JLabelSet(340,100,100,20,"商品型番:"			,11,1);
		JLabel LB_SearchItemName		= B00110FrameParts.JLabelSet(340,125,100,20,"商品名:"			,11,1);
		JLabel LB_Searchlot				= B00110FrameParts.JLabelSet(340,150,100,20,"ロット:"			,11,1);
		JLabel LB_SearchExpDate			= B00110FrameParts.JLabelSet(340,175,100,20,"消費期限:"			,11,1);
		JLabel LB_SearchActualDate		= B00110FrameParts.JLabelSet(340,200,100,20,"明細入荷日:"		,10,1);
		JLabel LB_SearchCom				= B00110FrameParts.JLabelSet(340,225,100,20,"明細コメント:"		,10,1);
		JLabel LB_SearchEntryUser		= B00110FrameParts.JLabelSet(340,250,100,20,"登録者:"			,11,1);
		JLabel LB_SearchUpdateUser		= B00110FrameParts.JLabelSet(340,275,100,20,"更新者:"			,11,1);
		
		JLabel LB_SearchPlanQty			= B00110FrameParts.JLabelSet(680, 25,100,20,"明細予定数:"		,11,1);
		JLabel LB_SearchActualQty		= B00110FrameParts.JLabelSet(680, 50,100,20,"明細実績数:"		,11,1);
		JLabel LB_SearchSpName			= B00110FrameParts.JLabelSet(680,100,100,20,"仕入先名:"			,11,1);
		JLabel LB_SearchSpPost			= B00110FrameParts.JLabelSet(680,125,100,20,"仕入先郵便:"		,11,1);
		JLabel LB_SearchSpAdd			= B00110FrameParts.JLabelSet(680,150,100,20,"仕入先住所:"		,11,1);
		JLabel LB_SearchSpTel			= B00110FrameParts.JLabelSet(680,175,100,20,"仕入先電話:"		,11,1);
		JLabel LB_SearchFixFg			= B00110FrameParts.JLabelSet(680,200,100,20,"状況:"				,11,1);
		JLabel LB_DateCom				= B00110FrameParts.JLabelSet(680,225,200,20,"↓YYYY/MM/DD HH:MM:SS"			,11,1);
		JLabel LB_SearchEntryDate		= B00110FrameParts.JLabelSet(680,250,100,20,"登録日:"			,11,1);
		JLabel LB_SearchUpdateDate		= B00110FrameParts.JLabelSet(680,275,100,20,"更新日:"			,11,1);
		
		final JComboBox TB_SearchClWh						= B00110FrameParts.JComboBoxSet(			100, 25,240,20,B00100DefaultVariable.SearchWhList[0],11);				//ヘッダ担当倉庫
		final JComboBox TB_SearchClCd						= B00110FrameParts.JComboBoxSet(			100, 50,240,20,B00100DefaultVariable.SearchClList[0],11);				//ヘッダ荷主CD
		final JComboBox TB_SearchClGpCD						= B00110FrameParts.JComboBoxSet(			100, 75,240,20,B00100DefaultVariable.SearchClGpList[0],11);			//ヘッダ荷主グループCD
		final JComboBox TB_SearchSpCd						= B00110FrameParts.JComboBoxSet(			100,100,240,20,B00100DefaultVariable.SearchSupplierList[0],11);		//ヘッダ仕入先　
		final JTextField TB_SearchArrNo						= B00110FrameParts.JTextFieldSet(			100,125,100,20,"",11,0);	//ヘッダ入荷予定NO
		final JTextField TB_SearchClArrNo					= B00110FrameParts.JTextFieldSet(			100,150,100,20,"",11,0);	//ヘッダ荷主予定番号
		final JFormattedTextField TB_SearchPlanDateMin		= B00110FrameParts.JFormattedTextFieldSet(	100,175, 70,20,""	,11,0,"YYYY/MM/DD");		//ヘッダ入荷予定日
		final JFormattedTextField TB_SearchPlanDateMax		= B00110FrameParts.JFormattedTextFieldSet(	230,175, 70,20,""	,11,0,"YYYY/MM/DD");		//ヘッダ入荷予定日
		final JFormattedTextField TB_SearchHdActualDateMin	= B00110FrameParts.JFormattedTextFieldSet(	100,200, 70,20,""	,11,0,"YYYY/MM/DD");		//ヘッダ入荷実績日
		final JFormattedTextField TB_SearchHdActualDateMax	= B00110FrameParts.JFormattedTextFieldSet(	230,200, 70,20,""	,11,0,"YYYY/MM/DD");		//ヘッダ入荷実績日
		final JTextField TB_SearchArCom						= B00110FrameParts.JTextFieldSet(			100,225,100,20,""	,11,0);	//ヘッダコメント
		
		final JTextField TB_SearchItemCd					= B00110FrameParts.JTextFieldSet(			440, 25,100,20,"",11,0);	//商品コード
		final JTextField TB_SearchClItemCd					= B00110FrameParts.JTextFieldSet(			440, 50,100,20,"",11,0);	//荷主商品コード
		final JTextField TB_SearchJanCd						= B00110FrameParts.JTextFieldSet(			440, 75,100,20,"",11,0);	//JANCD（バラ）
		final JTextField TB_SearchItemMdNo					= B00110FrameParts.JTextFieldSet(			440,100,100,20,"",11,0);	//商品型番
		final JTextField TB_SearchItemName					= B00110FrameParts.JTextFieldSet(			440,125,100,20,"",11,0);	//商品名
		final JTextField TB_Searchlot						= B00110FrameParts.JTextFieldSet(			440,150,100,20,"",11,0);	//ロット
		final JFormattedTextField TB_SearchExpDateMin		= B00110FrameParts.JFormattedTextFieldSet(	440,175, 70,20,"",11,0,"YYYY/MM/DD");		//消費期限最小
		final JFormattedTextField TB_SearchExpDateMax		= B00110FrameParts.JFormattedTextFieldSet(	570,175, 70,20,"",11,0,"YYYY/MM/DD");		//消費期限最大
		final JFormattedTextField TB_SearchActualDateMin	= B00110FrameParts.JFormattedTextFieldSet(	440,200, 70,20,"",11,0,"YYYY/MM/DD");		//明細入荷日最小
		final JFormattedTextField TB_SearchActualDateMax	= B00110FrameParts.JFormattedTextFieldSet(	570,200, 70,20,"",11,0,"YYYY/MM/DD");		//明細入荷日最大
		final JTextField TB_SearchCom						= B00110FrameParts.JTextFieldSet(			440,225,100,20,"",11,0);	//明細コメント
		final JTextField TB_SearchEntryUser					= B00110FrameParts.JTextFieldSet(			440,250,100,20,"",11,0);	//登録者
		final JTextField TB_SearchUpdateUser				= B00110FrameParts.JTextFieldSet(			440,275,100,20,"",11,0);	//更新者
		
		final JFormattedTextField TB_SearchPlanQtyMin		= B00110FrameParts.JFormattedTextFieldSet(	780, 25, 70,20,"",11,1,"####");		//明細予定数最小
		final JFormattedTextField TB_SearchPlanQtyMax		= B00110FrameParts.JFormattedTextFieldSet(	870, 26, 70,20,"",11,1,"####");		//明細予定数最大
		final JFormattedTextField TB_SearchActualQtyMin		= B00110FrameParts.JFormattedTextFieldSet(	780, 50, 70,20,"",11,1,"####");		//明細実績数最小
		final JFormattedTextField TB_SearchActualQtyMax		= B00110FrameParts.JFormattedTextFieldSet(	870, 50, 70,20,"",11,1,"####");		//明細実績数最大
		final JTextField TB_SearchSpName					= B00110FrameParts.JTextFieldSet(			780,100,100,20,"",11,0);	//仕入先名
		final JTextField TB_SearchSpPost					= B00110FrameParts.JTextFieldSet(			780,125,100,20,"",11,0);	//仕入先郵便
		final JTextField TB_SearchSpAdd						= B00110FrameParts.JTextFieldSet(			780,150,100,20,"",11,0);	//仕入先住所
		final JTextField TB_SearchSpTel						= B00110FrameParts.JTextFieldSet(			780,175,100,20,"",11,0);	//仕入先電話
		final JComboBox TB_SearchFixFg						= B00110FrameParts.JComboBoxSet(			780,200,150,20,B00100DefaultVariable.SearchArryvalFixFgList[0],11);				//状況 
		final JFormattedTextField TB_SearchEntryDateMin		= B00110FrameParts.JFormattedTextFieldSet(	780,250,150,20,"",11,0,"YYYY/MM/DD HH:MM:SS");		//登録日
		final JFormattedTextField TB_SearchEntryDateMax		= B00110FrameParts.JFormattedTextFieldSet(	950,250,150,20,"",11,0,"YYYY/MM/DD HH:MM:SS");		//登録日
		final JFormattedTextField TB_SearchUpdateDateMin	= B00110FrameParts.JFormattedTextFieldSet(	780,275,150,20,"",11,0,"YYYY/MM/DD HH:MM:SS");		//更新日
		final JFormattedTextField TB_SearchUpdateDateMax	= B00110FrameParts.JFormattedTextFieldSet(	950,275,150,20,"",11,0,"YYYY/MM/DD HH:MM:SS");		//更新日

		JLabel LB2_SearchArrNo			= B00110FrameParts.JLabelSet( 200,125, 40,20,"と一致"		,10,0);
		JLabel LB2_SearchClArrNo		= B00110FrameParts.JLabelSet( 200,150, 40,20,"と一致"		,10,0);
		JLabel LB2_SearchPlanDate		= B00110FrameParts.JLabelSet( 210,175, 20,20,"～"			,10,2);
		JLabel LB2_SearchHdActualDate	= B00110FrameParts.JLabelSet( 210,200, 20,20,"～"			,10,2);
		JLabel LB2_SearchArCom			= B00110FrameParts.JLabelSet( 200,225, 40,20,"を含む"		,10,0);
		
		JLabel LB2_SearchItemCd			= B00110FrameParts.JLabelSet(540, 25, 40,20,"と一致"	,10,0);
		JLabel LB2_SearchClItemCd		= B00110FrameParts.JLabelSet(540, 50, 40,20,"と一致"	,10,0);
		JLabel LB2_SearchJanCd			= B00110FrameParts.JLabelSet(540, 75, 40,20,"と一致"	,10,0);
		JLabel LB2_SearchItemMdNo		= B00110FrameParts.JLabelSet(540,100, 40,20,"と一致"	,10,0);
		JLabel LB2_SearchItemName		= B00110FrameParts.JLabelSet(540,125, 40,20,"を含む"	,10,0);
		JLabel LB2_Searchlot			= B00110FrameParts.JLabelSet(540,150, 40,20,"と一致"	,10,0);
		JLabel LB2_SearchExpDate		= B00110FrameParts.JLabelSet(550,175, 20,20,"～"		,10,2);
		JLabel LB2_SearchActualDate		= B00110FrameParts.JLabelSet(550,200, 20,20,"～"		,10,2);
		JLabel LB2_SearchCom			= B00110FrameParts.JLabelSet(540,225, 40,20,"を含む"	,10,0);	
		JLabel LB2_SearchEntryUser		= B00110FrameParts.JLabelSet(540,250, 40,20,"を含む"	,10,0);	
		JLabel LB2_SearchUpdateUser		= B00110FrameParts.JLabelSet(540,275, 40,20,"を含む"	,10,0);	
		
		
		JLabel LB2_SearchPlanQty		= B00110FrameParts.JLabelSet(850, 25, 20,20,"～"		,10,2);
		JLabel LB2_SearchActualQty		= B00110FrameParts.JLabelSet(850, 50, 20,20,"～"		,10,2);
		JLabel LB2_SearchSpName			= B00110FrameParts.JLabelSet(880,100, 40,20,"を含む"	,10,0);
		JLabel LB2_SearchSpPost			= B00110FrameParts.JLabelSet(880,125, 40,20,"で始る"	,10,0);
		JLabel LB2_SearchSpAdd			= B00110FrameParts.JLabelSet(880,150, 40,20,"を含む"	,10,0);
		JLabel LB2_SearchSpTel			= B00110FrameParts.JLabelSet(880,175, 40,20,"を含む"	,10,0);
		JLabel LB2_SearchEntryDate		= B00110FrameParts.JLabelSet(930,250, 20,20,"～"		,10,2);
		JLabel LB2_SearchUpdateDate		= B00110FrameParts.JLabelSet(930,275, 20,20,"～"		,10,2);
		
		//予定日進む戻るボタン
		JButton SearchPlanDateMinAfterBtn	= B00110FrameParts.BtnSet(170,175, 40,10,"▲",6);
		JButton SearchPlanDateMinBeforeBtn	= B00110FrameParts.BtnSet(170,185, 40,10,"▼",6);
		JButton SearchPlanDateMaxAfterBtn	= B00110FrameParts.BtnSet(300,175, 40,10,"▲",6);
		JButton SearchPlanDateMaxBeforeBtn	= B00110FrameParts.BtnSet(300,185, 40,10,"▼",6);
		PN_Search.add(SearchPlanDateMinAfterBtn);
		PN_Search.add(SearchPlanDateMinBeforeBtn);
		PN_Search.add(SearchPlanDateMaxAfterBtn);
		PN_Search.add(SearchPlanDateMaxBeforeBtn);
		//予定日進む戻るボタン押下事の挙動
		SearchPlanDateMinAfterBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				String NowDate = B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[0];
				String GetDate = TB_SearchPlanDateMin.getText();
				String SetDate = NowDate;
				if(null==GetDate||"".equals(GetDate)) {
				}else {
					Timestamp WT	= B00050ToolsDateTimeControl.dtmTimestamp2(GetDate)[0];
					WT				= B00050ToolsDateTimeControl.ndate_after(WT, 1);
					SetDate			= B00050ToolsDateTimeControl.dtmString2(WT)[0];
				}
				TB_SearchPlanDateMin.setText(SetDate);
			}
		});
		SearchPlanDateMinBeforeBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				String NowDate = B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[0];
				String GetDate = TB_SearchPlanDateMin.getText();
				String SetDate = NowDate;
				if(null==GetDate||"".equals(GetDate)) {
				}else {
					Timestamp WT	= B00050ToolsDateTimeControl.dtmTimestamp2(GetDate)[0];
					WT				= B00050ToolsDateTimeControl.ndate_before(WT, 1);
					SetDate			= B00050ToolsDateTimeControl.dtmString2(WT)[0];
				}
				TB_SearchPlanDateMin.setText(SetDate);
			}
		});
		SearchPlanDateMaxAfterBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				String NowDate = B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[0];
				String GetDate = TB_SearchPlanDateMax.getText();
				String SetDate = NowDate;
				if(null==GetDate||"".equals(GetDate)) {
				}else {
					Timestamp WT	= B00050ToolsDateTimeControl.dtmTimestamp2(GetDate)[0];
					WT				= B00050ToolsDateTimeControl.ndate_after(WT, 1);
					SetDate			= B00050ToolsDateTimeControl.dtmString2(WT)[0];
				}
				TB_SearchPlanDateMax.setText(SetDate);
			}
		});
		SearchPlanDateMaxBeforeBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				String NowDate = B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[0];
				String GetDate = TB_SearchPlanDateMax.getText();
				String SetDate = NowDate;
				if(null==GetDate||"".equals(GetDate)) {
				}else {
					Timestamp WT	= B00050ToolsDateTimeControl.dtmTimestamp2(GetDate)[0];
					WT				= B00050ToolsDateTimeControl.ndate_before(WT, 1);
					SetDate			= B00050ToolsDateTimeControl.dtmString2(WT)[0];
				}
				TB_SearchPlanDateMax.setText(SetDate);
			}
		});
		//実績日進む戻るボタン
		JButton SearchHdActualDateMinAfterBtn		= B00110FrameParts.BtnSet(170,200, 40,10,"▲",6);
		JButton SearchHdActualDateMinBeforeBtn	= B00110FrameParts.BtnSet(170,210, 40,10,"▼",6);
		JButton SearchHdActualDateMaxAfterBtn		= B00110FrameParts.BtnSet(300,200, 40,10,"▲",6);
		JButton SearchHdActualDateMaxBeforeBtn	= B00110FrameParts.BtnSet(300,210, 40,10,"▼",6);
		PN_Search.add(SearchHdActualDateMinAfterBtn);
		PN_Search.add(SearchHdActualDateMinBeforeBtn);
		PN_Search.add(SearchHdActualDateMaxAfterBtn);
		PN_Search.add(SearchHdActualDateMaxBeforeBtn);
		//実績日進む戻るボタン押下事の挙動
		SearchHdActualDateMinAfterBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				String NowDate = B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[0];
				String GetDate = TB_SearchHdActualDateMin.getText();
				String SetDate = NowDate;
				if(null==GetDate||"".equals(GetDate)) {
				}else {
					Timestamp WT	= B00050ToolsDateTimeControl.dtmTimestamp2(GetDate)[0];
					WT				= B00050ToolsDateTimeControl.ndate_after(WT, 1);
					SetDate			= B00050ToolsDateTimeControl.dtmString2(WT)[0];
				}
				TB_SearchHdActualDateMin.setText(SetDate);
			}
		});
		SearchHdActualDateMinBeforeBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				String NowDate = B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[0];
				String GetDate = TB_SearchHdActualDateMin.getText();
				String SetDate = NowDate;
				if(null==GetDate||"".equals(GetDate)) {
				}else {
					Timestamp WT	= B00050ToolsDateTimeControl.dtmTimestamp2(GetDate)[0];
					WT				= B00050ToolsDateTimeControl.ndate_before(WT, 1);
					SetDate			= B00050ToolsDateTimeControl.dtmString2(WT)[0];
				}
				TB_SearchHdActualDateMin.setText(SetDate);
			}
		});
		SearchHdActualDateMaxAfterBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				String NowDate = B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[0];
				String GetDate = TB_SearchHdActualDateMax.getText();
				String SetDate = NowDate;
				if(null==GetDate||"".equals(GetDate)) {
				}else {
					Timestamp WT	= B00050ToolsDateTimeControl.dtmTimestamp2(GetDate)[0];
					WT				= B00050ToolsDateTimeControl.ndate_after(WT, 1);
					SetDate			= B00050ToolsDateTimeControl.dtmString2(WT)[0];
				}
				TB_SearchHdActualDateMax.setText(SetDate);
			}
		});
		SearchHdActualDateMaxBeforeBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				String NowDate = B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[0];
				String GetDate = TB_SearchHdActualDateMax.getText();
				String SetDate = NowDate;
				if(null==GetDate||"".equals(GetDate)) {
				}else {
					Timestamp WT	= B00050ToolsDateTimeControl.dtmTimestamp2(GetDate)[0];
					WT				= B00050ToolsDateTimeControl.ndate_before(WT, 1);
					SetDate			= B00050ToolsDateTimeControl.dtmString2(WT)[0];
				}
				TB_SearchHdActualDateMax.setText(SetDate);
			}
		});
		
		//賞味期限日進む戻るボタン
		JButton SearchExpDateMinAfterBtn	= B00110FrameParts.BtnSet(510,175, 40,10,"▲",6);
		JButton SearchExpDateMinBeforeBtn	= B00110FrameParts.BtnSet(510,185, 40,10,"▼",6);
		JButton SearchExpDateMaxAfterBtn	= B00110FrameParts.BtnSet(640,175, 40,10,"▲",6);
		JButton SearchExpDateMaxBeforeBtn	= B00110FrameParts.BtnSet(640,185, 40,10,"▼",6);
		PN_Search.add(SearchExpDateMinAfterBtn);
		PN_Search.add(SearchExpDateMinBeforeBtn);
		PN_Search.add(SearchExpDateMaxAfterBtn);
		PN_Search.add(SearchExpDateMaxBeforeBtn);
		//賞味期限日進む戻るボタン押下事の挙動
		SearchExpDateMinAfterBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				String NowDate = B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[0];
				String GetDate = TB_SearchExpDateMin.getText();
				String SetDate = NowDate;
				if(null==GetDate||"".equals(GetDate)) {
				}else {
					Timestamp WT	= B00050ToolsDateTimeControl.dtmTimestamp2(GetDate)[0];
					WT				= B00050ToolsDateTimeControl.ndate_after(WT, 1);
					SetDate			= B00050ToolsDateTimeControl.dtmString2(WT)[0];
				}
				TB_SearchExpDateMin.setText(SetDate);
			}
		});
		SearchExpDateMinBeforeBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				String NowDate = B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[0];
				String GetDate = TB_SearchExpDateMin.getText();
				String SetDate = NowDate;
				if(null==GetDate||"".equals(GetDate)) {
				}else {
					Timestamp WT	= B00050ToolsDateTimeControl.dtmTimestamp2(GetDate)[0];
					WT				= B00050ToolsDateTimeControl.ndate_before(WT, 1);
					SetDate			= B00050ToolsDateTimeControl.dtmString2(WT)[0];
				}
				TB_SearchExpDateMin.setText(SetDate);
			}
		});
		SearchExpDateMaxAfterBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				String NowDate = B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[0];
				String GetDate = TB_SearchExpDateMax.getText();
				String SetDate = NowDate;
				if(null==GetDate||"".equals(GetDate)) {
				}else {
					Timestamp WT	= B00050ToolsDateTimeControl.dtmTimestamp2(GetDate)[0];
					WT				= B00050ToolsDateTimeControl.ndate_after(WT, 1);
					SetDate			= B00050ToolsDateTimeControl.dtmString2(WT)[0];
				}
				TB_SearchExpDateMax.setText(SetDate);
			}
		});
		SearchExpDateMaxBeforeBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				String NowDate = B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[0];
				String GetDate = TB_SearchExpDateMax.getText();
				String SetDate = NowDate;
				if(null==GetDate||"".equals(GetDate)) {
				}else {
					Timestamp WT	= B00050ToolsDateTimeControl.dtmTimestamp2(GetDate)[0];
					WT				= B00050ToolsDateTimeControl.ndate_before(WT, 1);
					SetDate			= B00050ToolsDateTimeControl.dtmString2(WT)[0];
				}
				TB_SearchExpDateMax.setText(SetDate);
			}
		});
		
		//明細実績日進む戻るボタン
		JButton SearchActualDateMinAfterBtn		= B00110FrameParts.BtnSet(510,200, 40,10,"▲",6);
		JButton SearchActualDateMinBeforeBtn	= B00110FrameParts.BtnSet(510,210, 40,10,"▼",6);
		JButton SearchActualDateMaxAfterBtn		= B00110FrameParts.BtnSet(640,200, 40,10,"▲",6);
		JButton SearchActualDateMaxBeforeBtn	= B00110FrameParts.BtnSet(640,210, 40,10,"▼",6);
		PN_Search.add(SearchActualDateMinAfterBtn);
		PN_Search.add(SearchActualDateMinBeforeBtn);
		PN_Search.add(SearchActualDateMaxAfterBtn);
		PN_Search.add(SearchActualDateMaxBeforeBtn);
		//明細実績日進む戻るボタン押下事の挙動
		SearchActualDateMinAfterBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				String NowDate = B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[0];
				String GetDate = TB_SearchActualDateMin.getText();
				String SetDate = NowDate;
				if(null==GetDate||"".equals(GetDate)) {
				}else {
					Timestamp WT	= B00050ToolsDateTimeControl.dtmTimestamp2(GetDate)[0];
					WT				= B00050ToolsDateTimeControl.ndate_after(WT, 1);
					SetDate			= B00050ToolsDateTimeControl.dtmString2(WT)[0];
				}
				TB_SearchActualDateMin.setText(SetDate);
			}
		});
		SearchActualDateMinBeforeBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				String NowDate = B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[0];
				String GetDate = TB_SearchActualDateMin.getText();
				String SetDate = NowDate;
				if(null==GetDate||"".equals(GetDate)) {
				}else {
					Timestamp WT	= B00050ToolsDateTimeControl.dtmTimestamp2(GetDate)[0];
					WT				= B00050ToolsDateTimeControl.ndate_before(WT, 1);
					SetDate			= B00050ToolsDateTimeControl.dtmString2(WT)[0];
				}
				TB_SearchActualDateMin.setText(SetDate);
			}
		});
		SearchActualDateMaxAfterBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				String NowDate = B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[0];
				String GetDate = TB_SearchActualDateMax.getText();
				String SetDate = NowDate;
				if(null==GetDate||"".equals(GetDate)) {
				}else {
					Timestamp WT	= B00050ToolsDateTimeControl.dtmTimestamp2(GetDate)[0];
					WT				= B00050ToolsDateTimeControl.ndate_after(WT, 1);
					SetDate			= B00050ToolsDateTimeControl.dtmString2(WT)[0];
				}
				TB_SearchActualDateMax.setText(SetDate);
			}
		});
		SearchActualDateMaxBeforeBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				String NowDate = B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[0];
				String GetDate = TB_SearchActualDateMax.getText();
				String SetDate = NowDate;
				if(null==GetDate||"".equals(GetDate)) {
				}else {
					Timestamp WT	= B00050ToolsDateTimeControl.dtmTimestamp2(GetDate)[0];
					WT				= B00050ToolsDateTimeControl.ndate_before(WT, 1);
					SetDate			= B00050ToolsDateTimeControl.dtmString2(WT)[0];
				}
				TB_SearchActualDateMax.setText(SetDate);
			}
		});
		
		
		PN_Search.add(LB_SearchClWh);
		PN_Search.add(LB_SearchClCd);
		PN_Search.add(LB_SearchClGpCD);
		PN_Search.add(LB_SearchSpCd);
		PN_Search.add(LB_SearchArrNo);
		PN_Search.add(LB_SearchClArrNo);
		PN_Search.add(LB_SearchPlanDate);
		PN_Search.add(LB_SearchHdActualDate);
		PN_Search.add(LB_SearchArCom);
		PN_Search.add(LB_SearchItemCd);
		PN_Search.add(LB_SearchClItemCd);
		PN_Search.add(LB_SearchJanCd);
		PN_Search.add(LB_SearchItemMdNo);
		PN_Search.add(LB_SearchItemName);
		PN_Search.add(LB_Searchlot);
		PN_Search.add(LB_SearchExpDate);
		PN_Search.add(LB_SearchActualDate);
		PN_Search.add(LB_SearchCom);
		PN_Search.add(LB_SearchEntryUser);
		PN_Search.add(LB_SearchUpdateUser);
		PN_Search.add(LB_SearchPlanQty);
		PN_Search.add(LB_SearchActualQty);
		PN_Search.add(LB_SearchSpName);
		PN_Search.add(LB_SearchSpPost);
		PN_Search.add(LB_SearchSpAdd);
		PN_Search.add(LB_SearchSpTel);
		PN_Search.add(LB_SearchFixFg);
		PN_Search.add(LB_DateCom);
		PN_Search.add(LB_SearchEntryDate);
		PN_Search.add(LB_SearchUpdateDate);
		
		PN_Search.add(TB_SearchClWh);
		PN_Search.add(TB_SearchClCd);
		PN_Search.add(TB_SearchClGpCD);
		PN_Search.add(TB_SearchSpCd);
		PN_Search.add(TB_SearchArrNo);
		PN_Search.add(TB_SearchClArrNo);
		PN_Search.add(TB_SearchPlanDateMin);
		PN_Search.add(TB_SearchPlanDateMax);
		PN_Search.add(TB_SearchHdActualDateMin);
		PN_Search.add(TB_SearchHdActualDateMax);
		PN_Search.add(TB_SearchArCom);
		PN_Search.add(TB_SearchItemCd);
		PN_Search.add(TB_SearchClItemCd);
		PN_Search.add(TB_SearchJanCd);
		PN_Search.add(TB_SearchItemMdNo);
		PN_Search.add(TB_SearchItemName);
		PN_Search.add(TB_Searchlot);
		PN_Search.add(TB_SearchExpDateMin);
		PN_Search.add(TB_SearchExpDateMax);
		PN_Search.add(TB_SearchActualDateMin);
		PN_Search.add(TB_SearchActualDateMax);
		PN_Search.add(TB_SearchCom);
		PN_Search.add(TB_SearchEntryUser);
		PN_Search.add(TB_SearchUpdateUser);
		PN_Search.add(TB_SearchPlanQtyMin);
		PN_Search.add(TB_SearchPlanQtyMax);
		PN_Search.add(TB_SearchActualQtyMin);
		PN_Search.add(TB_SearchActualQtyMax);
		PN_Search.add(TB_SearchSpName);
		PN_Search.add(TB_SearchSpPost);
		PN_Search.add(TB_SearchSpAdd);
		PN_Search.add(TB_SearchSpTel);
		PN_Search.add(TB_SearchFixFg);
		PN_Search.add(TB_SearchEntryDateMin);
		PN_Search.add(TB_SearchEntryDateMax);
		PN_Search.add(TB_SearchUpdateDateMin);
		PN_Search.add(TB_SearchUpdateDateMax);
		
		PN_Search.add(LB2_SearchArrNo);
		PN_Search.add(LB2_SearchClArrNo);
		PN_Search.add(LB2_SearchPlanDate);
		PN_Search.add(LB2_SearchHdActualDate);
		PN_Search.add(LB2_SearchArCom);
		PN_Search.add(LB2_SearchItemCd);
		PN_Search.add(LB2_SearchClItemCd);
		PN_Search.add(LB2_SearchJanCd);
		PN_Search.add(LB2_SearchItemMdNo);
		PN_Search.add(LB2_SearchItemName);
		PN_Search.add(LB2_Searchlot);
		PN_Search.add(LB2_SearchExpDate);
		PN_Search.add(LB2_SearchActualDate);
		PN_Search.add(LB2_SearchCom);
		PN_Search.add(LB2_SearchEntryUser);
		PN_Search.add(LB2_SearchUpdateUser);
		PN_Search.add(LB2_SearchPlanQty);
		PN_Search.add(LB2_SearchActualQty);
		PN_Search.add(LB2_SearchSpName);
		PN_Search.add(LB2_SearchSpPost);
		PN_Search.add(LB2_SearchSpAdd);
		PN_Search.add(LB2_SearchSpTel);
		PN_Search.add(LB2_SearchEntryDate);
		PN_Search.add(LB2_SearchUpdateDate);
		
		//検索ボタン
		JButton SearchBtn = B00110FrameParts.BtnSet(100,275,100,20,"検索",11);
		PN_Search.add(SearchBtn);
		
		//条件クリアボタン
		JButton SearchCrear = B00110FrameParts.BtnSet(250,275,100,20,"条件クリア",10);
		PN_Search.add(SearchCrear);
		
		PN_Search.add(PN_SearchLabel);
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
}