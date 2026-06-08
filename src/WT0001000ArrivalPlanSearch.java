import java.awt.Font;
import java.awt.event.ActionEvent;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

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
		
		final JFrame main_fm = B00110FrameParts.FrameCreate(x,y,1200,750,"Corgi00入荷予定検索","NK");
		JLabel userinfo = B00110FrameParts.UserInfo();
		JButton exit_btn = B00110FrameParts.ExitBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		
		//検索条件パネル
		JPanel PN_Search = B00110FrameParts.JPanelSet(10,40,1160,300,"White");
		JLabel PN_SearchLabel = B00110FrameParts.JLabelSet(0,0,100,20,"検索条件",11,0);
		
		//検索条件
		JLabel LB_SearchClWh			= B00110FrameParts.JLabelSet(  0, 25,100,20,"担当倉庫:"				,11,1);
		JLabel LB_SearchClCd			= B00110FrameParts.JLabelSet(  0, 50,100,20,"荷主:"					,11,1);
		JLabel LB_SearchClGpCD			= B00110FrameParts.JLabelSet(  0, 75,100,20,"荷主グループ:"			,11,1);
		JLabel LB_SearchSpCd			= B00110FrameParts.JLabelSet(  0,100,100,20,"仕入先:"					,11,1);
		JLabel LB_SearchArrNo			= B00110FrameParts.JLabelSet(  0,125,100,20,"入荷予定NO:"				,11,1);
		JLabel LB_SearchClArrNo			= B00110FrameParts.JLabelSet(  0,150,100,20,"荷主予定番号:"			,11,1);
		JLabel LB_SearchPlanDate		= B00110FrameParts.JLabelSet(  0,175,100,20,"入荷予定日:"				,11,1);
		JLabel LB_SearchHdActualDate	= B00110FrameParts.JLabelSet(  0,200,100,20,"入荷実績日:"				,11,1);
		JLabel LB_SearchArCom			= B00110FrameParts.JLabelSet(  0,225,100,20,"ヘッダコメント:"			,10,1);
		
		JLabel LB_SearchItemCd			= B00110FrameParts.JLabelSet(340, 25,100,20,"商品コード:"				,11,1);
		JLabel LB_SearchClItemCd		= B00110FrameParts.JLabelSet(340, 50,100,20,"荷主商品コード:"			,10,1);
		JLabel LB_SearchJanCd			= B00110FrameParts.JLabelSet(340, 75,100,20,"JANCD（バラ）:"			,10,1);
		JLabel LB_SearchItemMdNo		= B00110FrameParts.JLabelSet(340,100,100,20,"商品型番:"				,11,1);
		JLabel LB_SearchItemName		= B00110FrameParts.JLabelSet(340,125,100,20,"商品名:"					,11,1);
		JLabel LB_Searchlot				= B00110FrameParts.JLabelSet(340,150,100,20,"ロット:"					,11,1);
		JLabel LB_SearchExpDate			= B00110FrameParts.JLabelSet(340,175,100,20,"消費期限:"				,11,1);
		JLabel LB_SearchActualDate		= B00110FrameParts.JLabelSet(340,200,100,20,"明細入荷日:"				,10,1);
		JLabel LB_SearchCom				= B00110FrameParts.JLabelSet(340,225,100,20,"明細コメント:"			,10,1);
		JLabel LB_SearchEntryUser		= B00110FrameParts.JLabelSet(340,250,100,20,"登録者:"					,11,1);
		JLabel LB_SearchUpdateUser		= B00110FrameParts.JLabelSet(340,275,100,20,"更新者:"					,11,1);
		
		JLabel LB_SearchPlanQty			= B00110FrameParts.JLabelSet(680, 25,100,20,"明細予定数:"				,11,1);
		JLabel LB_SearchActualQty		= B00110FrameParts.JLabelSet(680, 50,100,20,"明細実績数:"				,11,1);
		JLabel LB_SearchSpName			= B00110FrameParts.JLabelSet(680,100,100,20,"仕入先名:"				,11,1);
		JLabel LB_SearchSpPost			= B00110FrameParts.JLabelSet(680,125,100,20,"仕入先郵便:"				,11,1);
		JLabel LB_SearchSpAdd			= B00110FrameParts.JLabelSet(680,150,100,20,"仕入先住所:"				,11,1);
		JLabel LB_SearchSpTel			= B00110FrameParts.JLabelSet(680,175,100,20,"仕入先電話:"				,11,1);
		JLabel LB_SearchFixFg			= B00110FrameParts.JLabelSet(680,200,100,20,"状況:"					,11,1);
		JLabel LB_DateCom				= B00110FrameParts.JLabelSet(680,225,200,20,"↓YYYY/MM/DD HH:MM:SS"	,11,1);
		JLabel LB_SearchEntryDate		= B00110FrameParts.JLabelSet(680,250,100,20,"登録日:"					,11,1);
		JLabel LB_SearchUpdateDate		= B00110FrameParts.JLabelSet(680,275,100,20,"更新日:"					,11,1);
		
		final JComboBox TB_SearchClWh						= B00110FrameParts.JComboBoxSet(				100, 25,240,20,B00100DefaultVariable.SearchWhList[0],11);				//ヘッダ担当倉庫
		final JComboBox TB_SearchClCd						= B00110FrameParts.JComboBoxSet(				100, 50,240,20,B00100DefaultVariable.SearchClList[0],11);				//ヘッダ荷主CD
		final JComboBox TB_SearchClGpCD						= B00110FrameParts.JComboBoxSet(				100, 75,240,20,B00100DefaultVariable.SearchClGpList[0],11);			//ヘッダ荷主グループCD
		final JComboBox TB_SearchSpCd						= B00110FrameParts.JComboBoxSet(				100,100,240,20,B00100DefaultVariable.SearchSupplierList[0],11);		//ヘッダ仕入先　
		final JTextField TB_SearchArrNo						= B00110FrameParts.JTextFieldSet(				100,125,100,20,""	,11,0);						//ヘッダ入荷予定NO
		final JTextField TB_SearchClArrNo					= B00110FrameParts.JTextFieldSet(				100,150,100,20,""	,11,0);						//ヘッダ荷主予定番号
		final JFormattedTextField TB_SearchPlanDateMin		= B00110FrameParts.JFormattedTextFieldSet(	100,175, 70,20,""	,11,0,"YYYY/MM/DD");		//ヘッダ入荷予定日
		final JFormattedTextField TB_SearchPlanDateMax		= B00110FrameParts.JFormattedTextFieldSet(	230,175, 70,20,""	,11,0,"YYYY/MM/DD");		//ヘッダ入荷予定日
		final JFormattedTextField TB_SearchHdActualDateMin	= B00110FrameParts.JFormattedTextFieldSet(	100,200, 70,20,""	,11,0,"YYYY/MM/DD");		//ヘッダ入荷実績日
		final JFormattedTextField TB_SearchHdActualDateMax	= B00110FrameParts.JFormattedTextFieldSet(	230,200, 70,20,""	,11,0,"YYYY/MM/DD");		//ヘッダ入荷実績日
		final JTextField TB_SearchArCom						= B00110FrameParts.JTextFieldSet(				100,225,100,20,""	,11,0);						//ヘッダコメント
		
		//現在ログイン中の荷主情報選択済みにする
		TB_SearchClWh.setSelectedIndex(		GetSelectIndex(B00100DefaultVariable.SearchWhList[1]	,A00000Main.ClWh));			//ヘッダ担当倉庫
		TB_SearchClCd.setSelectedIndex(		GetSelectIndex(B00100DefaultVariable.SearchClList[1]	,A00000Main.ClCd));			//ヘッダ荷主CD
		TB_SearchClGpCD.setSelectedIndex(	GetSelectIndex(B00100DefaultVariable.SearchClGpList[1]	,A00000Main.ClGp));			//ヘッダ荷主グループCD
		
		
		final JTextField TB_SearchItemCd					= B00110FrameParts.JTextFieldSet(				440, 25,100,20,"",11,0);	//商品コード
		final JTextField TB_SearchClItemCd					= B00110FrameParts.JTextFieldSet(				440, 50,100,20,"",11,0);	//荷主商品コード
		final JTextField TB_SearchJanCd						= B00110FrameParts.JTextFieldSet(				440, 75,100,20,"",11,0);	//JANCD（バラ）
		final JTextField TB_SearchItemMdNo					= B00110FrameParts.JTextFieldSet(				440,100,100,20,"",11,0);	//商品型番
		final JTextField TB_SearchItemName					= B00110FrameParts.JTextFieldSet(				440,125,100,20,"",11,0);	//商品名
		final JTextField TB_Searchlot						= B00110FrameParts.JTextFieldSet(				440,150,100,20,"",11,0);	//ロット
		final JFormattedTextField TB_SearchExpDateMin		= B00110FrameParts.JFormattedTextFieldSet(	440,175, 70,20,"",11,0,"YYYY/MM/DD");		//消費期限最小
		final JFormattedTextField TB_SearchExpDateMax		= B00110FrameParts.JFormattedTextFieldSet(	570,175, 70,20,"",11,0,"YYYY/MM/DD");		//消費期限最大
		final JFormattedTextField TB_SearchActualDateMin	= B00110FrameParts.JFormattedTextFieldSet(	440,200, 70,20,"",11,0,"YYYY/MM/DD");		//明細入荷日最小
		final JFormattedTextField TB_SearchActualDateMax	= B00110FrameParts.JFormattedTextFieldSet(	570,200, 70,20,"",11,0,"YYYY/MM/DD");		//明細入荷日最大
		final JTextField TB_SearchCom						= B00110FrameParts.JTextFieldSet(				440,225,100,20,"",11,0);	//明細コメント
		final JTextField TB_SearchEntryUser					= B00110FrameParts.JTextFieldSet(				440,250,100,20,"",11,0);	//登録者
		final JTextField TB_SearchUpdateUser				= B00110FrameParts.JTextFieldSet(				440,275,100,20,"",11,0);	//更新者
		
		final JFormattedTextField TB_SearchPlanQtyMin		= B00110FrameParts.JFormattedTextFieldSet(	780, 25, 70,20,"",11,1,"####");		//明細予定数最小
		final JFormattedTextField TB_SearchPlanQtyMax		= B00110FrameParts.JFormattedTextFieldSet(	870, 26, 70,20,"",11,1,"####");		//明細予定数最大
		final JFormattedTextField TB_SearchActualQtyMin		= B00110FrameParts.JFormattedTextFieldSet(	780, 50, 70,20,"",11,1,"####");		//明細実績数最小
		final JFormattedTextField TB_SearchActualQtyMax		= B00110FrameParts.JFormattedTextFieldSet(	870, 50, 70,20,"",11,1,"####");		//明細実績数最大
		final JTextField TB_SearchSpName					= B00110FrameParts.JTextFieldSet(				780,100,100,20,"",11,0);			//仕入先名
		final JTextField TB_SearchSpPost					= B00110FrameParts.JTextFieldSet(				780,125,100,20,"",11,0);			//仕入先郵便
		final JTextField TB_SearchSpAdd						= B00110FrameParts.JTextFieldSet(				780,150,100,20,"",11,0);			//仕入先住所
		final JTextField TB_SearchSpTel						= B00110FrameParts.JTextFieldSet(				780,175,100,20,"",11,0);			//仕入先電話
		final JComboBox TB_SearchFixFg						= B00110FrameParts.JComboBoxSet(				780,200,150,20,B00100DefaultVariable.SearchArryvalFixFgList[0],11);		//状況 
		final JFormattedTextField TB_SearchEntryDateMin		= B00110FrameParts.JFormattedTextFieldSet(	780,250,150,20,"",11,0,"YYYY/MM/DD HH:MM:SS");		//登録日
		final JFormattedTextField TB_SearchEntryDateMax		= B00110FrameParts.JFormattedTextFieldSet(	950,250,150,20,"",11,0,"YYYY/MM/DD HH:MM:SS");		//登録日
		final JFormattedTextField TB_SearchUpdateDateMin	= B00110FrameParts.JFormattedTextFieldSet(	780,275,150,20,"",11,0,"YYYY/MM/DD HH:MM:SS");		//更新日
		final JFormattedTextField TB_SearchUpdateDateMax	= B00110FrameParts.JFormattedTextFieldSet(	950,275,150,20,"",11,0,"YYYY/MM/DD HH:MM:SS");		//更新日
		
		//検索条件　状況　初期値を未入荷に設定
		TB_SearchFixFg.setSelectedIndex(GetSelectIndex(B00100DefaultVariable.SearchArryvalFixFgList[1]	,"0"));

		JLabel LB2_SearchArrNo			= B00110FrameParts.JLabelSet( 200,125, 40,20,"と一致"	,10,0);
		JLabel LB2_SearchClArrNo		= B00110FrameParts.JLabelSet( 200,150, 40,20,"と一致"	,10,0);
		JLabel LB2_SearchPlanDate		= B00110FrameParts.JLabelSet( 210,175, 20,20,"～"		,10,2);
		JLabel LB2_SearchHdActualDate	= B00110FrameParts.JLabelSet( 210,200, 20,20,"～"		,10,2);
		JLabel LB2_SearchArCom			= B00110FrameParts.JLabelSet( 200,225, 40,20,"を含む"	,10,0);
		
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
		
		TB_SearchClWh.setEnabled(false);		//ヘッダ担当倉庫
		TB_SearchClCd.setEnabled(false);		//ヘッダ荷主CD
		TB_SearchClGpCD.setEnabled(false);		//ヘッダ荷主グループCD
		
		//検索ボタン
		JButton SearchBtn 		= B00110FrameParts.BtnSet(100,275,100,20,"検索",11);
		PN_Search.add(SearchBtn);
		
		//条件クリアボタン
		JButton SearchCrearBtn 	= B00110FrameParts.BtnSet(100,0,100,20,"条件クリア",10);
		PN_Search.add(SearchCrearBtn);
		
		Object[][] RtArrivalPlanHdRt = T00016ArrivalPlanHdRt.RtArrivalPlanHdRt();
		
		String[] columnNames01 = new String[RtArrivalPlanHdRt.length+1];
		
		columnNames01[0] = "Fg";
		for(int i=0;i<RtArrivalPlanHdRt.length;i++) {
			columnNames01[1+i] = ""+RtArrivalPlanHdRt[i][3];
		}
		
		//編集可能カラムの指定
		B10010TableControl.RenewTgt = new int[1];
		B10010TableControl.RenewTgt[0] = 0;

		final DefaultTableModel tableModel_ms01 = new B10010TableControl.MyTableModel01(columnNames01,0);
		
		final JTable tb01 = new JTable(tableModel_ms01);
		tb01.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tb01.setRowHeight(20*A00000Main.Mul/A00000Main.Div);
		tb01.setFont(new Font(A00000Main.DefaultFont, Font.PLAIN, 12*A00000Main.Mul/A00000Main.Div));
		
		DefaultTableColumnModel columnModel01
		= (DefaultTableColumnModel)tb01.getColumnModel();
		
		//列幅初期設定 表示位置設定
		TableColumn column = null;
		
		column = columnModel01.getColumn( 0);	column.setPreferredWidth( 30*A00000Main.Mul/A00000Main.Div);	//FG
		
		for(int i=0;i<RtArrivalPlanHdRt.length;i++) {
			if("int".equals((String)RtArrivalPlanHdRt[i][2])||"float".equals((String)RtArrivalPlanHdRt[i][2])) {
				column = columnModel01.getColumn(1+i);	column.setPreferredWidth( 90*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.rightCellRenderer());
			}else {
				column = columnModel01.getColumn(1+i);	column.setPreferredWidth( 90*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());
			}
		}
		//スクロール用設定
		JScrollPane scpn01 = B00110FrameParts.JScrollPaneSet(10,350,1160,275,tb01);
		main_fm.add(scpn01);
		
		PN_Search.add(PN_SearchLabel);
		main_fm.add(PN_Search);
		
		
		//CSVボタン
		JButton CsvBtn = B00110FrameParts.BtnSet(			 10,660,100,20,"csv出力",11);
		main_fm.add(CsvBtn);
		
		JLabel LB_RenewBtn  = B00110FrameParts.JLabelSet(	130,640,100,20,"チェック行を" ,11,2);
		main_fm.add(LB_RenewBtn);
		
		//修正ボタン
		JButton RenewBtn = B00110FrameParts.BtnSet(		130,660,100,20,"修正",11);
		main_fm.add(RenewBtn);
		
		//新規登録ボタン
		JButton CreateBtn = B00110FrameParts.BtnSet(		250,660,100,20,"新規登録",11);
		main_fm.add(CreateBtn);
		
		//一括新規ボタン
		JButton SomeCreateBtn = B00110FrameParts.BtnSet(	370,660,100,20,"一括新規",11);
		main_fm.add(SomeCreateBtn);
		
		//Excel出力ボタン
		JButton ExcelBtn = B00110FrameParts.BtnSet(		490,660,100,20,"Excel出力",11);
		main_fm.add(ExcelBtn);
		
		//Excel取込ボタン
		JButton ExcelEntryBtn = B00110FrameParts.BtnSet(	610,660,100,20,"Excel取込",11);
		main_fm.add(ExcelEntryBtn);
		
		final JCheckBox PlanListTgtAll = B00110FrameParts.JCheckBoxSet(730,640,200,20,"未発行分も対象にして",11);
		main_fm.add(PlanListTgtAll);
		//入荷予定票発行
		JButton PlanListBtn = B00110FrameParts.BtnSet(	730,660,100,20,"予定票発行",11);
		main_fm.add(PlanListBtn);
		
		RenewFg = true;
		main_fm.setVisible(true);
		
		//入荷予定票発行ボタン押下時の挙動
		PlanListBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					String TgtWhCd = B00100DefaultVariable.SearchWhList[1][TB_SearchClWh.getSelectedIndex()];
					String TgtClCd = B00100DefaultVariable.SearchClList[1][TB_SearchClCd.getSelectedIndex()];
					ArrayList<String> ArrNoList = new ArrayList<String>();
					boolean NewPrintOnly = true;
					if(PlanListTgtAll.isSelected()) {NewPrintOnly=false;}
					
					int RowCount = tableModel_ms01.getRowCount();
					for(int i=0;i<RowCount;i++) {
						ArrNoList.add(""+tableModel_ms01.getValueAt(i,T00016ArrivalPlanHdRt.ColArrNo+1));
					}
					
					WTList0001000ArrivalPlan.ArrivalPlanList0001(TgtWhCd,TgtClCd,ArrNoList,NewPrintOnly);
					RenewFg = true;
				}
			}
		});
		
		//検索ボタン押下時の挙動
		SearchBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					int RowCount = tableModel_ms01.getRowCount();
					for(int i=0;i<RowCount;i++) {
						tableModel_ms01.removeRow(0);
					}
					/**************************************************************
					検索条件取得
					***************************************************************/
					String GetSearchClWh			= B00100DefaultVariable.SearchWhList[1][TB_SearchClWh.getSelectedIndex()];			//ヘッダ担当倉庫
					String GetSearchClCd			= B00100DefaultVariable.SearchClList[1][TB_SearchClCd.getSelectedIndex()];			//ヘッダ荷主CD
					String GetSearchClGpCD			= B00100DefaultVariable.SearchClGpList[1][TB_SearchClGpCD.getSelectedIndex()];		//ヘッダ荷主グループCD
					String GetSearchSpCd			= B00100DefaultVariable.SearchSupplierList[1][TB_SearchSpCd.getSelectedIndex()];		//ヘッダ仕入先　
					String GetSearchArrNo			= TB_SearchArrNo.getText();				//ヘッダ入荷予定NO
					String GetSearchClArrNo			= TB_SearchClArrNo.getText();			//ヘッダ荷主予定番号
					String GetSearchPlanDateMin		= TB_SearchPlanDateMin.getText();		//ヘッダ入荷予定日
					String GetSearchPlanDateMax		= TB_SearchPlanDateMax.getText();		//ヘッダ入荷予定日
					String GetSearchHdActualDateMin	= TB_SearchHdActualDateMin.getText();	//ヘッダ入荷実績日
					String GetSearchHdActualDateMax	= TB_SearchHdActualDateMax.getText();	//ヘッダ入荷実績日
					String GetSearchArCom			= TB_SearchArCom.getText();				//ヘッダコメント
					
					String GetSearchItemCd			= TB_SearchItemCd.getText();			//商品コード
					String GetSearchClItemCd		= TB_SearchClItemCd.getText();			//荷主商品コード
					String GetSearchJanCd			= TB_SearchJanCd.getText();				//JANCD（バラ）
					String GetSearchItemMdNo		= TB_SearchItemMdNo.getText();			//商品型番
					String GetSearchItemName		= TB_SearchItemName.getText();			//商品名
					String GetSearchlot				= TB_Searchlot.getText();				//ロット
					String GetSearchExpDateMin		= TB_SearchExpDateMin.getText();		//消費期限最小
					String GetSearchExpDateMax		= TB_SearchExpDateMax.getText();		//消費期限最大
					String GetSearchActualDateMin	= TB_SearchActualDateMin.getText();		//明細入荷日最小
					String GetSearchActualDateMax	= TB_SearchActualDateMax.getText();		//明細入荷日最大
					String GetSearchCom				= TB_SearchCom.getText();				//明細コメント
					String GetSearchEntryUser		= TB_SearchEntryUser.getText();			//登録者
					String GetSearchUpdateUser		= TB_SearchUpdateUser.getText();		//更新者
					
					String GetSearchPlanQtyMin		= TB_SearchPlanQtyMin.getText();		//明細予定数最小
					String GetSearchPlanQtyMax		= TB_SearchPlanQtyMax.getText();		//明細予定数最大
					String GetSearchActualQtyMin	= TB_SearchActualQtyMin.getText();		//明細実績数最小
					String GetSearchActualQtyMax	= TB_SearchActualQtyMax.getText();		//明細実績数最大
					String GetSearchSpName			= TB_SearchSpName.getText();			//仕入先名
					String GetSearchSpPost			= TB_SearchSpPost.getText();			//仕入先郵便
					String GetSearchSpAdd			= TB_SearchSpAdd.getText();				//仕入先住所
					String GetSearchSpTel			= TB_SearchSpTel.getText();				//仕入先電話
					String GetSearchFixFg			= B00100DefaultVariable.SearchArryvalFixFgList[1][TB_SearchFixFg.getSelectedIndex()];	//状況 
					String GetSearchEntryDateMin	= TB_SearchEntryDateMin.getText();		//登録日
					String GetSearchEntryDateMax	= TB_SearchEntryDateMax.getText();		//登録日
					String GetSearchUpdateDateMin	= TB_SearchUpdateDateMin.getText();		//更新日
					String GetSearchUpdateDateMax	= TB_SearchUpdateDateMax.getText();		//更新日
					
					GetSearchClWh				= B00020ToolsTextControl.Trim(GetSearchClWh);
					GetSearchClCd				= B00020ToolsTextControl.Trim(GetSearchClCd);
					GetSearchClGpCD				= B00020ToolsTextControl.Trim(GetSearchClGpCD);
					GetSearchSpCd				= B00020ToolsTextControl.Trim(GetSearchSpCd);
					GetSearchArrNo				= B00020ToolsTextControl.Trim(GetSearchArrNo);
					GetSearchClArrNo			= B00020ToolsTextControl.Trim(GetSearchClArrNo);
					GetSearchPlanDateMin		= B00020ToolsTextControl.Trim(GetSearchPlanDateMin);
					GetSearchPlanDateMax		= B00020ToolsTextControl.Trim(GetSearchPlanDateMax);
					GetSearchHdActualDateMin	= B00020ToolsTextControl.Trim(GetSearchHdActualDateMin);
					GetSearchHdActualDateMax	= B00020ToolsTextControl.Trim(GetSearchHdActualDateMax);
					GetSearchArCom				= B00020ToolsTextControl.Trim(GetSearchArCom);
					
					GetSearchItemCd				= B00020ToolsTextControl.Trim(GetSearchItemCd);
					GetSearchClItemCd			= B00020ToolsTextControl.Trim(GetSearchClItemCd);
					GetSearchJanCd				= B00020ToolsTextControl.Trim(GetSearchJanCd);
					GetSearchItemMdNo			= B00020ToolsTextControl.Trim(GetSearchItemMdNo);
					GetSearchItemName			= B00020ToolsTextControl.Trim(GetSearchItemName);
					GetSearchlot				= B00020ToolsTextControl.Trim(GetSearchlot);
					GetSearchExpDateMin			= B00020ToolsTextControl.Trim(GetSearchExpDateMin);
					GetSearchExpDateMax			= B00020ToolsTextControl.Trim(GetSearchExpDateMax);
					GetSearchActualDateMin		= B00020ToolsTextControl.Trim(GetSearchActualDateMin);
					GetSearchActualDateMax		= B00020ToolsTextControl.Trim(GetSearchActualDateMax);
					GetSearchCom				= B00020ToolsTextControl.Trim(GetSearchCom);
					GetSearchEntryUser			= B00020ToolsTextControl.Trim(GetSearchEntryUser);
					GetSearchUpdateUser			= B00020ToolsTextControl.Trim(GetSearchUpdateUser);
					
					GetSearchPlanQtyMin			= B00020ToolsTextControl.Trim(GetSearchPlanQtyMin);
					GetSearchPlanQtyMax			= B00020ToolsTextControl.Trim(GetSearchPlanQtyMax);
					GetSearchActualQtyMin		= B00020ToolsTextControl.Trim(GetSearchActualQtyMin);
					GetSearchActualQtyMax		= B00020ToolsTextControl.Trim(GetSearchActualQtyMax);
					GetSearchSpName				= B00020ToolsTextControl.Trim(GetSearchSpName);
					GetSearchSpPost				= B00020ToolsTextControl.Trim(GetSearchSpPost);
					GetSearchSpAdd				= B00020ToolsTextControl.Trim(GetSearchSpAdd);
					GetSearchSpTel				= B00020ToolsTextControl.Trim(GetSearchSpTel);
					GetSearchFixFg				= B00020ToolsTextControl.Trim(GetSearchFixFg);
					GetSearchEntryDateMin		= B00020ToolsTextControl.Trim(GetSearchEntryDateMin);
					GetSearchEntryDateMax		= B00020ToolsTextControl.Trim(GetSearchEntryDateMax);
					GetSearchUpdateDateMin		= B00020ToolsTextControl.Trim(GetSearchUpdateDateMin);
					GetSearchUpdateDateMax		= B00020ToolsTextControl.Trim(GetSearchUpdateDateMax);
					
					if(!"".equals(GetSearchPlanQtyMin	)){GetSearchPlanQtyMin		= B00020ToolsTextControl.num_only_String02(GetSearchPlanQtyMin);}
					if(!"".equals(GetSearchPlanQtyMax	)){GetSearchPlanQtyMax		= B00020ToolsTextControl.num_only_String02(GetSearchPlanQtyMax);}
					if(!"".equals(GetSearchActualQtyMin	)){GetSearchActualQtyMin	= B00020ToolsTextControl.num_only_String02(GetSearchActualQtyMin);}
					if(!"".equals(GetSearchActualQtyMax	)){GetSearchActualQtyMax	= B00020ToolsTextControl.num_only_String02(GetSearchActualQtyMax);}
					if(!"".equals(GetSearchFixFg		)){GetSearchFixFg			= B00020ToolsTextControl.num_only_String02(GetSearchFixFg);}
					
					ArrayList<String> SearchClWh 			= new ArrayList<String>();		//ヘッダ担当倉庫
					ArrayList<String> SearchClCd 			= new ArrayList<String>();		//ヘッダ荷主CD
					ArrayList<String> SearchCLName01 		= new ArrayList<String>();		//ヘッダ荷主名
					ArrayList<String> SearchClGpCD 			= new ArrayList<String>();		//ヘッダ荷主グループCD
					ArrayList<String> SearchCLGpName01 		= new ArrayList<String>();		//ヘッダ荷主グループ名1
					ArrayList<String> SearchArrNo 			= new ArrayList<String>();		//ヘッダ入荷予定NO
					ArrayList<String> SearchClArrNo 		= new ArrayList<String>();		//ヘッダ荷主予定番号
					ArrayList<String> SearchPlanDateMin 	= new ArrayList<String>();		//ヘッダ入荷予定日最小
					ArrayList<String> SearchPlanDateMax 	= new ArrayList<String>();		//ヘッダ入荷予定日最大
					ArrayList<String> SearchHdActualDateMin	= new ArrayList<String>();		//ヘッダ入荷実績日最小
					ArrayList<String> SearchHdActualDateMax	= new ArrayList<String>();		//ヘッダ入荷実績日最大
					ArrayList<String> SearchSpCd 			= new ArrayList<String>();		//ヘッダ仕入先CD
					ArrayList<String> SearchSpName 			= new ArrayList<String>();		//ヘッダ仕入先名
					ArrayList<String> SearchSpPost 			= new ArrayList<String>();		//ヘッダ仕入先郵便
					ArrayList<String> SearchSpAdd 			= new ArrayList<String>();		//ヘッダ仕入先住所
					ArrayList<String> SearchSpTel 			= new ArrayList<String>();		//ヘッダ仕入先電話
					ArrayList<String> SearchArCom 			= new ArrayList<String>();		//ヘッダコメント
					ArrayList<Integer> SearchFixFg 			= new ArrayList<Integer>();		//ヘッダ状況
							
					ArrayList<Integer> SearchMsNoMin 		= new ArrayList<Integer>();		//明細番号最小
					ArrayList<Integer> SearchMsNoMax 		= new ArrayList<Integer>();		//明細番号最大
					ArrayList<String> SearchItemCd 			= new ArrayList<String>();		//商品コード
					ArrayList<String> SearchClItemCd 		= new ArrayList<String>();		//荷主商品コード
					ArrayList<String> SearchJanCd 			= new ArrayList<String>();		//JANCD（バラ）
					ArrayList<String> SearchItemMdNo 		= new ArrayList<String>();		//商品型番
					ArrayList<String> SearchItemName 		= new ArrayList<String>();		//商品名
					ArrayList<String> Searchlot 			= new ArrayList<String>();		//ロット
					ArrayList<String> SearchExpDateMin 		= new ArrayList<String>();		//消費期限最小
					ArrayList<String> SearchExpDateMax 		= new ArrayList<String>();		//消費期限最大
					ArrayList<Integer> SearchPlanQtyMin 	= new ArrayList<Integer>();		//予定数量最小
					ArrayList<Integer> SearchPlanQtyMax 	= new ArrayList<Integer>();		//予定数量最大
					ArrayList<Integer> SearchActualQtyMin 	= new ArrayList<Integer>();		//実績数最小
					ArrayList<Integer> SearchActualQtyMax 	= new ArrayList<Integer>();		//実績数最大
					ArrayList<String> SearchActualDateMin 	= new ArrayList<String>();		//入荷日最小
					ArrayList<String> SearchActualDateMax 	= new ArrayList<String>();		//入荷日最大
					ArrayList<String> SearchCom 			= new ArrayList<String>();		//コメント
					ArrayList<String> SearchEntryDateMin 	= new ArrayList<String>();		//登録日最小
					ArrayList<String> SearchEntryDateMax 	= new ArrayList<String>();		//登録日最大
					ArrayList<String> SearchUpdateDateMin 	= new ArrayList<String>();		//更新日最小
					ArrayList<String> SearchUpdateDateMax 	= new ArrayList<String>();		//更新日最大
					ArrayList<String> SearchEntryUser 		= new ArrayList<String>();		//登録者
					ArrayList<String> SearchUpdateUser 		= new ArrayList<String>();		//更新者
					boolean AllSearch=false;
					
					if(!"".equals(GetSearchClWh				)){SearchClWh.add(GetSearchClWh);}
					if(!"".equals(GetSearchClCd				)){SearchClCd.add(GetSearchClCd);}
					if(!"".equals(GetSearchClGpCD			)){SearchClGpCD.add(GetSearchClGpCD);}
					if(!"".equals(GetSearchSpCd				)){SearchSpCd.add(GetSearchSpCd);}
					if(!"".equals(GetSearchArrNo			)){SearchArrNo.add(GetSearchArrNo);}
					if(!"".equals(GetSearchClArrNo			)){SearchClArrNo.add(GetSearchClArrNo);}
					if(!"".equals(GetSearchPlanDateMin		)){SearchPlanDateMin.add(GetSearchPlanDateMin);}
					if(!"".equals(GetSearchPlanDateMax		)){SearchPlanDateMax.add(GetSearchPlanDateMax);}
					if(!"".equals(GetSearchHdActualDateMin	)){SearchHdActualDateMin.add(GetSearchHdActualDateMin);}
					if(!"".equals(GetSearchHdActualDateMax	)){SearchHdActualDateMax.add(GetSearchHdActualDateMax);}
					if(!"".equals(GetSearchArCom			)){SearchArCom.add(GetSearchArCom);}
					
					if(!"".equals(GetSearchItemCd			)){SearchItemCd.add(GetSearchItemCd);}
					if(!"".equals(GetSearchClItemCd			)){SearchClItemCd.add(GetSearchClItemCd);}
					if(!"".equals(GetSearchJanCd			)){SearchJanCd.add(GetSearchJanCd);}
					if(!"".equals(GetSearchItemMdNo			)){SearchItemMdNo.add(GetSearchItemMdNo);}
					if(!"".equals(GetSearchItemName			)){SearchItemName.add(GetSearchItemName);}
					if(!"".equals(GetSearchlot				)){Searchlot.add(GetSearchlot);}
					if(!"".equals(GetSearchExpDateMin		)){SearchExpDateMin.add(GetSearchExpDateMin);}
					if(!"".equals(GetSearchExpDateMax		)){SearchExpDateMax.add(GetSearchExpDateMax);}
					if(!"".equals(GetSearchActualDateMin	)){SearchActualDateMin.add(GetSearchActualDateMin);}
					if(!"".equals(GetSearchActualDateMax	)){SearchActualDateMax.add(GetSearchActualDateMax);}
					if(!"".equals(GetSearchCom				)){SearchCom.add(GetSearchCom);}
					if(!"".equals(GetSearchEntryUser		)){SearchEntryUser.add(GetSearchEntryUser);}
					if(!"".equals(GetSearchUpdateUser		)){SearchUpdateUser.add(GetSearchUpdateUser);}
					
					if(!"".equals(GetSearchPlanQtyMin		)){SearchPlanQtyMin.add((int)Float.parseFloat(GetSearchPlanQtyMin));}
					if(!"".equals(GetSearchPlanQtyMax		)){SearchPlanQtyMax.add((int)Float.parseFloat(GetSearchPlanQtyMax));}
					if(!"".equals(GetSearchActualQtyMin		)){SearchActualQtyMin.add((int)Float.parseFloat(GetSearchActualQtyMin));}
					if(!"".equals(GetSearchActualQtyMax		)){SearchActualQtyMax.add((int)Float.parseFloat(GetSearchActualQtyMax));}
					if(!"".equals(GetSearchSpName			)){SearchSpName.add(GetSearchSpName);}
					if(!"".equals(GetSearchSpPost			)){SearchSpPost.add(GetSearchSpPost);}
					if(!"".equals(GetSearchSpAdd			)){SearchSpAdd.add(GetSearchSpAdd);}
					if(!"".equals(GetSearchSpTel			)){SearchSpTel.add(GetSearchSpTel);}
					if(!"".equals(GetSearchFixFg			)){SearchFixFg.add((int)Float.parseFloat(GetSearchFixFg));}
					if(!"".equals(GetSearchEntryDateMin		)){SearchEntryDateMin.add(GetSearchEntryDateMin);}
					if(!"".equals(GetSearchEntryDateMax		)){SearchEntryDateMax.add(GetSearchEntryDateMax);}
					if(!"".equals(GetSearchUpdateDateMin	)){SearchUpdateDateMin.add(GetSearchUpdateDateMin);}
					if(!"".equals(GetSearchUpdateDateMax	)){SearchUpdateDateMax.add(GetSearchUpdateDateMax);}
					
					Object[][] ArrivalPlanHdRt = T00016ArrivalPlanHdRt.ArrivalPlanHdRt(
							SearchClWh,				//ヘッダ担当倉庫
							SearchClCd,				//ヘッダ荷主CD
							SearchCLName01,			//ヘッダ荷主名
							SearchClGpCD,			//ヘッダ荷主グループCD
							SearchCLGpName01,		//ヘッダ荷主グループ名1
							SearchArrNo,			//ヘッダ入荷予定NO
							SearchClArrNo,			//ヘッダ荷主予定番号
							SearchPlanDateMin,		//ヘッダ入荷予定日最小
							SearchPlanDateMax,		//ヘッダ入荷予定日最大
							SearchHdActualDateMin,	//ヘッダ入荷実績日最小
							SearchHdActualDateMax,	//ヘッダ入荷実績日最大
							SearchSpCd,				//ヘッダ仕入先CD
							SearchSpName,			//ヘッダ仕入先名
							SearchSpPost,			//ヘッダ仕入先郵便
							SearchSpAdd,			//ヘッダ仕入先住所
							SearchSpTel,			//ヘッダ仕入先電話
							SearchArCom,			//ヘッダコメント
							SearchFixFg,			//ヘッダ状況
									
							SearchMsNoMin,			//明細番号最小
							SearchMsNoMax,			//明細番号最大
							SearchItemCd,			//商品コード
							SearchClItemCd,			//荷主商品コード
							SearchJanCd,			//JANCD（バラ）
							SearchItemMdNo,			//商品型番
							SearchItemName,			//商品名
							Searchlot,				//ロット
							SearchExpDateMin,		//消費期限最小
							SearchExpDateMax,		//消費期限最大
							SearchPlanQtyMin,		//予定数量最小
							SearchPlanQtyMax,		//予定数量最大
							SearchActualQtyMin,		//実績数最小
							SearchActualQtyMax,		//実績数最大
							SearchActualDateMin,	//入荷日最小
							SearchActualDateMax,	//入荷日最大
							SearchCom,				//コメント
							SearchEntryDateMin,		//登録日最小
							SearchEntryDateMax,		//登録日最大
							SearchUpdateDateMin,	//更新日最小
							SearchUpdateDateMax,	//更新日最大
							SearchEntryUser,		//登録者
							SearchUpdateUser,		//更新者
							AllSearch);
					
					if(0==ArrivalPlanHdRt.length) {
						B10010TableControl.AddSortOFF(tb01,tableModel_ms01);
					}else {
						for(int i=0;i<ArrivalPlanHdRt.length;i++) {
							Object[] SetOb = new Object[ArrivalPlanHdRt[i].length+1];
							SetOb[0] = false;
							for(int i01=0;i01<ArrivalPlanHdRt[i].length;i01++) {
								SetOb[i01+1] = ""+ArrivalPlanHdRt[i][i01];
							}
							tableModel_ms01.addRow(SetOb);
						}
						B10010TableControl.AddSortON(tb01,tableModel_ms01);
					}
					RenewFg = true;
				}
			}
		});
		
		
		//条件クリアボタン押下時の挙動
		SearchCrearBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					/**************************************************************
					検索条件初期値に戻す
					***************************************************************/
					TB_SearchClWh.setSelectedIndex(		GetSelectIndex(B00100DefaultVariable.SearchWhList[1]	,A00000Main.ClWh));			//ヘッダ担当倉庫
					TB_SearchClCd.setSelectedIndex(		GetSelectIndex(B00100DefaultVariable.SearchClList[1]	,A00000Main.ClCd));			//ヘッダ荷主CD
					TB_SearchClGpCD.setSelectedIndex(	GetSelectIndex(B00100DefaultVariable.SearchClGpList[1]	,A00000Main.ClGp));			//ヘッダ荷主グループCD
					TB_SearchSpCd.setSelectedIndex(0);			//ヘッダ仕入先
					TB_SearchArrNo.setText("");					//ヘッダ入荷予定NO
					TB_SearchClArrNo.setText("");				//ヘッダ荷主予定番号
					TB_SearchPlanDateMin.setText("");			//ヘッダ入荷予定日
					TB_SearchPlanDateMax.setText("");			//ヘッダ入荷予定日
					TB_SearchHdActualDateMin.setText("");		//ヘッダ入荷実績日
					TB_SearchHdActualDateMax.setText("");		//ヘッダ入荷実績日
					TB_SearchArCom.setText("");					//ヘッダコメント
					TB_SearchItemCd.setText("");				//商品コード
					TB_SearchClItemCd.setText("");				//荷主商品コード
					TB_SearchJanCd.setText("");					//JANCD（バラ）
					TB_SearchItemMdNo.setText("");				//商品型番
					TB_SearchItemName.setText("");				//商品名
					TB_Searchlot.setText("");					//ロット
					TB_SearchExpDateMin.setText("");			//消費期限最小
					TB_SearchExpDateMax.setText("");			//消費期限最大
					TB_SearchActualDateMin.setText("");			//明細入荷日最小
					TB_SearchActualDateMax.setText("");			//明細入荷日最大
					TB_SearchCom.setText("");					//明細コメント
					TB_SearchEntryUser.setText("");				//登録者
					TB_SearchUpdateUser.setText("");			//更新者
					TB_SearchPlanQtyMin.setText("");			//明細予定数最小
					TB_SearchPlanQtyMax.setText("");			//明細予定数最大
					TB_SearchActualQtyMin.setText("");			//明細実績数最小
					TB_SearchActualQtyMax.setText("");			//明細実績数最大
					TB_SearchSpName.setText("");				//仕入先名
					TB_SearchSpPost.setText("");				//仕入先郵便
					TB_SearchSpAdd.setText("");					//仕入先住所
					TB_SearchSpTel.setText("");					//仕入先電話
					TB_SearchFixFg.setSelectedIndex(0);			//状況 
					TB_SearchEntryDateMin.setText("");			//登録日
					TB_SearchEntryDateMax.setText("");			//登録日
					TB_SearchUpdateDateMin.setText("");			//更新日
					TB_SearchUpdateDateMax.setText("");			//更新日
					
					/**************************************************************
					検索結果消す
					***************************************************************/
					int RowCount = tableModel_ms01.getRowCount();
					for(int i=0;i<RowCount;i++) {
						tableModel_ms01.removeRow(0);
					}
					B10010TableControl.AddSortOFF(tb01,tableModel_ms01);
					RenewFg = true;
				}
			}
		});
		
		//一括新規ボタン押下時の挙動
		SomeCreateBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					SetX=main_fm.getX();
					SetY=main_fm.getY();
	
					main_fm.setVisible(false);
					main_fm.dispose();
					WT0001003ArrivalPlanSomeEntry.ArrivalPlanSomeEntry(0,0);
					RenewFg = true;
				}
			}
		});
		
		//チェックボックス操作時の挙動
		tableModel_ms01.addTableModelListener(new TableModelListener(){
			public void tableChanged(TableModelEvent e){
				if(RenewFg) {
					RenewFg = false;
					int row_count = tb01.getRowCount();
					Boolean setBL=Boolean.valueOf(false);
					for(int i=0;i<row_count;i++){
						if(i!=e.getFirstRow()){
							tableModel_ms01.setValueAt(setBL, i, 0);
						}else {
	
						}
					}
					RenewFg = true;
				}
			}
		});
		
		//CSVボタン押下時の挙動
		CsvBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					B10010TableControl.TableOutPutCsv("出力先選択","入荷予定検（ヘッダ）検索結果",tb01);
					RenewFg = true;
				}
			}
		});
		
		//エクセル出力ボタン押下時の挙動
		ExcelBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					B10010TableControl.TableOutPutExcel("出力先選択","入荷予定（ヘッダ）検索結果",tb01);
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