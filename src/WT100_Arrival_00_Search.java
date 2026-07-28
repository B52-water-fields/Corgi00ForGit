import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class WT100_Arrival_00_Search{
	static int SetX;
	static int SetY;
	
	static String DefaultSearchClWh;
	static String DefaultSearchClCd;
	static String DefaultSearchClGpCD;
	static String DefaultSearchSpCd;
	static String DefaultSearchArrNo;
	static String DefaultSearchArrCountMin;
	static String DefaultSearchArrCountMax;
	static String DefaultSearchClArrNo;
	static String DefaultSearchPlanDateMin;
	static String DefaultSearchPlanDateMax;
	static String DefaultSearchActualDateMin;
	static String DefaultSearchActualDateMax;
	static String DefaultSearchCom;
	
	static String DefaultSearchItemCd;
	static String DefaultSearchClItemCd;
	static String DefaultSearchItemName;
	static String DefaultSearchLot;
	static String DefaultSearchExpDateMin;
	static String DefaultSearchExpDateMax;
	
	static String DefaultSearchEntryDateMin;
	static String DefaultSearchEntryDateMax;
	static String DefaultSearchUpdateDateMin;
	static String DefaultSearchUpdateDateMax;
	static String DefaultSearchEntryUser;
	static String DefaultSearchUpdateUser;
	
	static String DefaultSearchArrNoList;
	
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
		JLabel LB_SearchArrCount		= B100_FrameParts.JLabelSet(  0,150,100,20,"入荷枝番:"			,11,1);
		JLabel LB_SearchClArrNo			= B100_FrameParts.JLabelSet(  0,175,100,20,"荷主予定番号:"		,11,1);
		JLabel LB_SearchPlanDate		= B100_FrameParts.JLabelSet(  0,200,100,20,"入荷予定日:"		,11,1);
		JLabel LB_SearchActualDate		= B100_FrameParts.JLabelSet(  0,225,100,20,"入荷実績日:"		,11,1);
		JLabel LB_SearchCom				= B100_FrameParts.JLabelSet(  0,250,100,20,"コメント:"			,11,1);
		
		
		JLabel LB_SearchItemCd			= B100_FrameParts.JLabelSet(340, 25,100,20,"商品コード:"		,11,1);
		JLabel LB_SearchClItemCd		= B100_FrameParts.JLabelSet(340, 50,100,20,"荷主商品コード:"	,11,1);
		JLabel LB_SearchItemName		= B100_FrameParts.JLabelSet(340, 75,100,20,"商品名:"			,11,1);
		JLabel LB_SearchLot				= B100_FrameParts.JLabelSet(340,100,100,20,"ロット:"			,11,1);
		JLabel LB_SearchExpDate			= B100_FrameParts.JLabelSet(340,125,100,20,"消費期限:"			,11,1);
		
		
		JLabel LB_SearchEntryDate		= B100_FrameParts.JLabelSet(340,175,100,20,"登録日:"			,11,1);
		JLabel LB_SearchUpdateDate		= B100_FrameParts.JLabelSet(340,200,100,20,"更新日:"			,11,1);
		JLabel LB_SearchEntryUser		= B100_FrameParts.JLabelSet(340,225,100,20,"登録者:"			,11,1);
		JLabel LB_SearchUpdateUser		= B100_FrameParts.JLabelSet(340,250,100,20,"更新者:"			,11,1);
		
		final JComboBox TB_SearchClWh						= B100_FrameParts.JComboBoxSet(				100, 25,240,20,B100_DefaultVariable.SearchWhList[0],11);
		final JComboBox TB_SearchClCd						= B100_FrameParts.JComboBoxSet(				100, 50,240,20,B100_DefaultVariable.SearchClList[0],11);
		final JComboBox TB_SearchClGpCD						= B100_FrameParts.JComboBoxSet(				100, 75,240,20,B100_DefaultVariable.SearchClGpList[0],11);
		final JComboBox TB_SearchSpCd						= B100_FrameParts.JComboBoxSet(				100,100,240,20,B100_DefaultVariable.SearchSupplierList[0],11);
		final JTextField TB_SearchArrNo						= B100_FrameParts.JTextFieldSet(				100,125,100,20,"",11,0);
		final JFormattedTextField TB_SearchArrCountMin		= B100_FrameParts.JFormattedTextFieldSet(	100,150, 70,20,"",11,0,"#,###");
		final JFormattedTextField TB_SearchArrCountMax		= B100_FrameParts.JFormattedTextFieldSet(	190,150, 70,20,"",11,0,"#,###");
		final JTextField TB_SearchClArrNo					= B100_FrameParts.JTextFieldSet(				100,175,100,20,"",11,0);
		final JFormattedTextField TB_SearchPlanDateMin		= B100_FrameParts.JFormattedTextFieldSet(	100,200, 70,20,"",11,0,"YYYY/MM/DD");
		final JFormattedTextField TB_SearchPlanDateMax		= B100_FrameParts.JFormattedTextFieldSet(	230,200, 70,20,"",11,0,"YYYY/MM/DD");
		final JFormattedTextField TB_SearchActualDateMin	= B100_FrameParts.JFormattedTextFieldSet(	100,225, 70,20,"",11,0,"YYYY/MM/DD");
		final JFormattedTextField TB_SearchActualDateMax	= B100_FrameParts.JFormattedTextFieldSet(	230,225, 70,20,"",11,0,"YYYY/MM/DD");
		final JTextField TB_SearchCom						= B100_FrameParts.JTextFieldSet(				100,250,200,20,"",11,0);
		
		
		final JTextField TB_SearchItemCd					= B100_FrameParts.JTextFieldSet(				440, 25,100,20,"",11,0);
		final JTextField TB_SearchClItemCd					= B100_FrameParts.JTextFieldSet(				440, 50,100,20,"",11,0);
		final JTextField TB_SearchItemName					= B100_FrameParts.JTextFieldSet(				440, 75,100,20,"",11,0);
		final JTextField TB_SearchLot						= B100_FrameParts.JTextFieldSet(				440,100,100,20,"",11,0);
		final JFormattedTextField TB_SearchExpDateMin		= B100_FrameParts.JFormattedTextFieldSet(	440,125, 70,20,"",11,0,"YYYY/MM/DD");
		final JFormattedTextField TB_SearchExpDateMax		= B100_FrameParts.JFormattedTextFieldSet(	570,125, 70,20,"",11,0,"YYYY/MM/DD");
		
		
		final JFormattedTextField TB_SearchEntryDateMin		= B100_FrameParts.JFormattedTextFieldSet(	440,175,120,20,"",11,0,"YYYY/MM/DD HH:MM:SS");
		final JFormattedTextField TB_SearchEntryDateMax		= B100_FrameParts.JFormattedTextFieldSet(	580,175,120,20,"",11,0,"YYYY/MM/DD HH:MM:SS");
		final JFormattedTextField TB_SearchUpdateDateMin	= B100_FrameParts.JFormattedTextFieldSet(	440,200,120,20,"",11,0,"YYYY/MM/DD HH:MM:SS");
		final JFormattedTextField TB_SearchUpdateDateMax	= B100_FrameParts.JFormattedTextFieldSet(	580,200,120,20,"",11,0,"YYYY/MM/DD HH:MM:SS");
		final JTextField TB_SearchEntryUser					= B100_FrameParts.JTextFieldSet(				440,225,100,20,"",11,0);
		final JTextField TB_SearchUpdateUser				= B100_FrameParts.JTextFieldSet(				440,250,100,20,"",11,0);
		
		JLabel LB_ArrNoAny	= B100_FrameParts.JLabelSet(  720,  5,200,20,"↓入荷予定No複数指定",10,0);
		final JTextArea TB_ArrNoAny	= B100_FrameParts.JTextAreaSet(11);
		JScrollPane SPArrNoAny 	= B100_FrameParts.JScrollPaneSet(720,25,100,195,TB_ArrNoAny);
		
		
		JLabel LB2_SearchArrNo			= B100_FrameParts.JLabelSet(200,125, 40,20,"と一致"	,11,0);
		JLabel LB2_SearchArrCount		= B100_FrameParts.JLabelSet(170,150, 20,20,"～"		,11,2);
		JLabel LB2_SearchClArrNo		= B100_FrameParts.JLabelSet(200,175, 40,20,"と一致"	,11,0);
		JLabel LB2_SearchPlanDate		= B100_FrameParts.JLabelSet(210,200, 20,20,"～"		,11,2);
		JLabel LB2_SearchActualDate		= B100_FrameParts.JLabelSet(210,225, 20,20,"～"		,11,2);
		JLabel LB2_SearchCom			= B100_FrameParts.JLabelSet(300,250, 40,20,"を含む"	,11,0);
		
		
		JLabel LB2_SearchItemCd			= B100_FrameParts.JLabelSet(540, 25, 40,20,"と一致"	,11,0);
		JLabel LB2_SearchClItemCd		= B100_FrameParts.JLabelSet(540, 50, 40,20,"と一致"	,11,0);
		JLabel LB2_SearchItemName		= B100_FrameParts.JLabelSet(540, 75, 40,20,"を含む"	,11,0);
		JLabel LB2_SearchLot			= B100_FrameParts.JLabelSet(540,100, 40,20,"と一致"	,11,0);
		JLabel LB2_SearchExpDate		= B100_FrameParts.JLabelSet(550,125, 20,20,"～"		,11,2);
		
		
		JLabel LB2_SearchEntryDateMin	= B100_FrameParts.JLabelSet(560,175, 20,20,"～"		,11,2);
		JLabel LB2_SearchUpdateDateMin	= B100_FrameParts.JLabelSet(560,200, 20,20,"～"		,11,2);
		JLabel LB2_SearchEntryUser		= B100_FrameParts.JLabelSet(540,225, 40,20,"を含む"	,11,0);
		JLabel LB2_SearchUpdateUser		= B100_FrameParts.JLabelSet(540,250, 40,20,"を含む"	,11,0);
		
		//予定日進む戻るボタン
		JButton SearchPlanDateMinAfterBtn	= B100_FrameParts.BtnSet(170,200, 40,10,"▲",6);
		JButton SearchPlanDateMinBeforeBtn	= B100_FrameParts.BtnSet(170,210, 40,10,"▼",6);
		JButton SearchPlanDateMaxAfterBtn	= B100_FrameParts.BtnSet(300,200, 40,10,"▲",6);
		JButton SearchPlanDateMaxBeforeBtn	= B100_FrameParts.BtnSet(300,210, 40,10,"▼",6);
		PN_Search.add(SearchPlanDateMinAfterBtn);
		PN_Search.add(SearchPlanDateMinBeforeBtn);
		PN_Search.add(SearchPlanDateMaxAfterBtn);
		PN_Search.add(SearchPlanDateMaxBeforeBtn);
		
		//予定日進む戻るボタン押下事の挙動
		SearchPlanDateMinAfterBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				B100_DateTimeControl.AfterDateSet(TB_SearchPlanDateMin);
			}
		});
		SearchPlanDateMinBeforeBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				B100_DateTimeControl.BeforeDateSet(TB_SearchPlanDateMin);
			}
		});
		SearchPlanDateMaxAfterBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				B100_DateTimeControl.AfterDateSet(TB_SearchPlanDateMax);
			}
		});
		SearchPlanDateMaxBeforeBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				B100_DateTimeControl.BeforeDateSet(TB_SearchPlanDateMax);
			}
		});
		//実績日進む戻るボタン
		JButton SearchHdActualDateMinAfterBtn		= B100_FrameParts.BtnSet(170,225, 40,10,"▲",6);
		JButton SearchHdActualDateMinBeforeBtn		= B100_FrameParts.BtnSet(170,235, 40,10,"▼",6);
		JButton SearchHdActualDateMaxAfterBtn		= B100_FrameParts.BtnSet(300,225, 40,10,"▲",6);
		JButton SearchHdActualDateMaxBeforeBtn		= B100_FrameParts.BtnSet(300,235, 40,10,"▼",6);
		PN_Search.add(SearchHdActualDateMinAfterBtn);
		PN_Search.add(SearchHdActualDateMinBeforeBtn);
		PN_Search.add(SearchHdActualDateMaxAfterBtn);
		PN_Search.add(SearchHdActualDateMaxBeforeBtn);
		//実績日進む戻るボタン押下事の挙動
		SearchHdActualDateMinAfterBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				B100_DateTimeControl.AfterDateSet(TB_SearchActualDateMin);
			}
		});
		SearchHdActualDateMinBeforeBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				B100_DateTimeControl.BeforeDateSet(TB_SearchActualDateMin);
			}
		});
		SearchHdActualDateMaxAfterBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				B100_DateTimeControl.AfterDateSet(TB_SearchActualDateMax);
			}
		});
		SearchHdActualDateMaxBeforeBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				B100_DateTimeControl.BeforeDateSet(TB_SearchActualDateMax);
			}
		});
		
		//賞味期限日進む戻るボタン
		JButton SearchExpDateMinAfterBtn	= B100_FrameParts.BtnSet(510,125, 40,10,"▲",6);
		JButton SearchExpDateMinBeforeBtn	= B100_FrameParts.BtnSet(510,135, 40,10,"▼",6);
		JButton SearchExpDateMaxAfterBtn	= B100_FrameParts.BtnSet(640,125, 40,10,"▲",6);
		JButton SearchExpDateMaxBeforeBtn	= B100_FrameParts.BtnSet(640,135, 40,10,"▼",6);
		PN_Search.add(SearchExpDateMinAfterBtn);
		PN_Search.add(SearchExpDateMinBeforeBtn);
		PN_Search.add(SearchExpDateMaxAfterBtn);
		PN_Search.add(SearchExpDateMaxBeforeBtn);
		//賞味期限日進む戻るボタン押下事の挙動
		SearchExpDateMinAfterBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				B100_DateTimeControl.AfterDateSet(TB_SearchExpDateMin);
			}
		});
		SearchExpDateMinBeforeBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				B100_DateTimeControl.BeforeDateSet(TB_SearchExpDateMin);
			}
		});
		SearchExpDateMaxAfterBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				B100_DateTimeControl.AfterDateSet(TB_SearchExpDateMax);
			}
		});
		SearchExpDateMaxBeforeBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				B100_DateTimeControl.BeforeDateSet(TB_SearchExpDateMax);
			}
		});
		

		PN_Search.add(LB_SearchClWh);
		PN_Search.add(LB_SearchClCd);
		PN_Search.add(LB_SearchClGpCD);
		PN_Search.add(LB_SearchSpCd);
		PN_Search.add(LB_SearchArrNo);
		PN_Search.add(LB_SearchArrCount);
		PN_Search.add(LB_SearchClArrNo);
		PN_Search.add(LB_SearchPlanDate);
		PN_Search.add(LB_SearchActualDate);
		
		
		PN_Search.add(LB_SearchItemCd);
		PN_Search.add(LB_SearchClItemCd);
		PN_Search.add(LB_SearchItemName);
		PN_Search.add(LB_SearchLot);
		PN_Search.add(LB_SearchExpDate);
		
		PN_Search.add(LB_SearchCom);
		PN_Search.add(LB_SearchEntryDate);
		PN_Search.add(LB_SearchUpdateDate);
		PN_Search.add(LB_SearchEntryUser);
		PN_Search.add(LB_SearchUpdateUser);
		
		
		PN_Search.add(TB_SearchClWh);
		PN_Search.add(TB_SearchClCd);
		PN_Search.add(TB_SearchClGpCD);
		PN_Search.add(TB_SearchSpCd);
		PN_Search.add(TB_SearchArrNo);
		PN_Search.add(TB_SearchArrCountMin);
		PN_Search.add(TB_SearchArrCountMax);
		PN_Search.add(TB_SearchClArrNo);
		PN_Search.add(TB_SearchPlanDateMin);
		PN_Search.add(TB_SearchPlanDateMax);
		PN_Search.add(TB_SearchActualDateMin);
		PN_Search.add(TB_SearchActualDateMax);
		
		
		PN_Search.add(TB_SearchItemCd);
		PN_Search.add(TB_SearchClItemCd);
		PN_Search.add(TB_SearchItemName);
		PN_Search.add(TB_SearchLot);
		PN_Search.add(TB_SearchExpDateMin);
		PN_Search.add(TB_SearchExpDateMax);
		
		
		PN_Search.add(TB_SearchCom);
		PN_Search.add(TB_SearchEntryDateMin);
		PN_Search.add(TB_SearchEntryDateMax);
		PN_Search.add(TB_SearchUpdateDateMin);
		PN_Search.add(TB_SearchUpdateDateMax);
		PN_Search.add(TB_SearchEntryUser);
		PN_Search.add(TB_SearchUpdateUser);
		
		
		PN_Search.add(LB2_SearchArrNo);
		PN_Search.add(LB2_SearchArrCount);
		PN_Search.add(LB2_SearchClArrNo);
		PN_Search.add(LB2_SearchPlanDate);
		PN_Search.add(LB2_SearchActualDate);
		
		
		PN_Search.add(LB2_SearchItemCd);
		PN_Search.add(LB2_SearchClItemCd);
		PN_Search.add(LB2_SearchItemName);
		PN_Search.add(LB2_SearchLot);
		PN_Search.add(LB2_SearchExpDate);
		
		PN_Search.add(LB2_SearchCom);
		PN_Search.add(LB2_SearchEntryDateMin);
		PN_Search.add(LB2_SearchUpdateDateMin);
		PN_Search.add(LB2_SearchEntryUser);
		PN_Search.add(LB2_SearchUpdateUser);
		
		PN_Search.add(LB_ArrNoAny);
		PN_Search.add(SPArrNoAny);
		
		main_fm.add(PN_Search);
		
		//検索ボタン
		JButton SearchBtn 		= B100_FrameParts.BtnSet(1050,250,100,20,"検索",11);
		PN_Search.add(SearchBtn);
		
		//条件クリアボタン
		JButton SearchCrearBtn 	= B100_FrameParts.BtnSet(1050,25,100,20,"条件クリア",10);
		PN_Search.add(SearchCrearBtn);
		
		//現在ログイン中の荷主情報選択済みにする
		TB_SearchClWh.setSelectedIndex(		B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.SearchWhList[1]	,A00000_Main.ClWh,true));			//ヘッダ担当倉庫
		TB_SearchClCd.setSelectedIndex(		B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.SearchClList[1]	,A00000_Main.ClCd,true));			//ヘッダ荷主CD
		TB_SearchClGpCD.setSelectedIndex(	B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.SearchClGpList[1]	,A00000_Main.ClGp,true));			//ヘッダ荷主グループCD
		
		TB_SearchClWh.setEnabled(false);		//ヘッダ担当倉庫
		TB_SearchClCd.setEnabled(false);		//ヘッダ荷主CD
		TB_SearchClGpCD.setEnabled(false);		//ヘッダ荷主グループCD
		
		//荷主切り替わっていたらデフォルト検索条件セットしない
		boolean DefaultSetFg = true;
		if(null==DefaultSearchClWh) {
			DefaultSetFg=false;
		}else if(!A00000_Main.ClWh.equals(DefaultSearchClWh)){
			DefaultSetFg=false;
		}
		if(null==DefaultSearchClCd) {
			DefaultSetFg=false;
		}else if(!A00000_Main.ClCd.equals(DefaultSearchClCd)){
			DefaultSetFg=false;
		}
		if(null==DefaultSearchClGpCD) {
			DefaultSetFg=false;
		}else if(!A00000_Main.ClGp.equals(DefaultSearchClGpCD)){
			DefaultSetFg=false;
		}
		
		//覚えた検索条件をセット
		if(DefaultSetFg) {
			if(null!=DefaultSearchSpCd				){TB_SearchSpCd.setSelectedIndex(	B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.SearchSupplierList[1]	,DefaultSearchSpCd,true));}
			if(null!=DefaultSearchArrNo				){TB_SearchArrNo.setText(DefaultSearchArrNo);}
			if(null!=DefaultSearchArrCountMin		){TB_SearchArrCountMin.setText(DefaultSearchArrCountMin);}
			if(null!=DefaultSearchArrCountMax		){TB_SearchArrCountMax.setText(DefaultSearchArrCountMax);}
			if(null!=DefaultSearchClArrNo			){TB_SearchClArrNo.setText(DefaultSearchClArrNo);}
			if(null!=DefaultSearchPlanDateMin		){TB_SearchPlanDateMin.setText(DefaultSearchPlanDateMin);}
			if(null!=DefaultSearchPlanDateMax		){TB_SearchPlanDateMax.setText(DefaultSearchPlanDateMax);}
			if(null!=DefaultSearchActualDateMin		){TB_SearchActualDateMin.setText(DefaultSearchActualDateMin);}
			if(null!=DefaultSearchActualDateMax		){TB_SearchActualDateMax.setText(DefaultSearchActualDateMax);}
			if(null!=DefaultSearchCom					){TB_SearchCom.setText(DefaultSearchCom);}
			
			if(null!=DefaultSearchItemCd				){TB_SearchItemCd.setText(DefaultSearchItemCd);}
			if(null!=DefaultSearchClItemCd			){TB_SearchClItemCd.setText(DefaultSearchClItemCd);}
			if(null!=DefaultSearchItemName			){TB_SearchItemName.setText(DefaultSearchItemName);}
			if(null!=DefaultSearchLot					){TB_SearchLot.setText(DefaultSearchLot);}
			if(null!=DefaultSearchExpDateMin			){TB_SearchExpDateMin.setText(DefaultSearchExpDateMin);}
			if(null!=DefaultSearchExpDateMax			){TB_SearchExpDateMax.setText(DefaultSearchExpDateMax);}
			
			if(null!=DefaultSearchEntryDateMin		){TB_SearchEntryDateMin.setText(DefaultSearchEntryDateMin);}
			if(null!=DefaultSearchEntryDateMax		){TB_SearchEntryDateMax.setText(DefaultSearchEntryDateMax);}
			if(null!=DefaultSearchUpdateDateMin		){TB_SearchUpdateDateMin.setText(DefaultSearchUpdateDateMin);}
			if(null!=DefaultSearchUpdateDateMax		){TB_SearchUpdateDateMax.setText(DefaultSearchUpdateDateMax);}
			if(null!=DefaultSearchEntryUser			){TB_SearchEntryUser.setText(DefaultSearchEntryUser);}
			if(null!=DefaultSearchUpdateUser			){TB_SearchUpdateUser.setText(DefaultSearchUpdateUser);}
			
			if(null!=DefaultSearchArrNoList			){TB_ArrNoAny.setText(DefaultSearchArrNoList);}
		}
		
		Object[][] RtArrivalHdRt = T100_ArrivalHdRt.RtArrivalHdRt();
		
		String[] columnNames01 = new String[RtArrivalHdRt.length+1];
		
		columnNames01[0] = "Fg";
		for(int i=0;i<RtArrivalHdRt.length;i++) {
			columnNames01[1+(int)RtArrivalHdRt[i][1]] = ""+RtArrivalHdRt[i][3];
		}
		
		//編集可能カラムの指定
		B100_TableControl.RenewTgt = new int[1];
		B100_TableControl.RenewTgt[0] = 0;

		final DefaultTableModel MainFmTableModel = new B100_TableControl.MyTableModel01(columnNames01,0);
		
		final JTable tb01 = new JTable(MainFmTableModel);
		tb01.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tb01.setRowHeight(20*A00000_Main.Mul/A00000_Main.Div);
		tb01.setFont(new Font(A00000_Main.DefaultFont, Font.PLAIN, 12*A00000_Main.Mul/A00000_Main.Div));
		
		DefaultTableColumnModel columnModel01
		= (DefaultTableColumnModel)tb01.getColumnModel();
		
		//列幅初期設定 表示位置設定
		TableColumn column = null;
		
		column = columnModel01.getColumn( 0);	column.setPreferredWidth( 30*A00000_Main.Mul/A00000_Main.Div);	//FG
		
		for(int i=0;i<RtArrivalHdRt.length;i++) {
			if("int".equals((String)RtArrivalHdRt[i][2])||"float".equals((String)RtArrivalHdRt[i][2])) {
				column = columnModel01.getColumn(1+(int)RtArrivalHdRt[i][1]);	column.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.rightCellRenderer());
			}else {
				column = columnModel01.getColumn(1+(int)RtArrivalHdRt[i][1]);	column.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.leftCellRenderer());
			}
		}
		//スクロール用設定
		JScrollPane scpn01 = B100_FrameParts.JScrollPaneSet(10,350,1160,275,tb01);
		main_fm.add(scpn01);
		
		//CSVボタン
		JButton CsvBtn = B100_FrameParts.BtnSet(				 10,660,100,20,"ヘッダcsv出力"	,10);
		main_fm.add(CsvBtn);
		//明細付CSVボタン
		JButton MsCsvBtn = B100_FrameParts.BtnSet(				 10,685,100,20,"明細付csv出力"	,10);
		main_fm.add(MsCsvBtn);
		
		
		//Excel出力ボタン
		JButton ExcelBtn = B100_FrameParts.BtnSet(				130,660,100,20,"Excel出力"		,11);
		main_fm.add(ExcelBtn);
		//明細付Excel出力ボタン
		JButton MsExcelBtn = B100_FrameParts.BtnSet(			130,685,100,20,"明細Excel出力"	,9);
		main_fm.add(MsExcelBtn);
		
		JLabel LB_RenewBtn  = B100_FrameParts.JLabelSet(		250,640,100,20,"チェック行の" 	,11,2);
		main_fm.add(LB_RenewBtn);
		//詳細表示ボタン
		JButton MsViewBtn = B100_FrameParts.BtnSet(			250,660,100,20,"詳細表示"		,11);
		main_fm.add(MsViewBtn);
		
		/***********************************************
		詳細表示用
		***********************************************/
		final JFrame Ms_fm = B100_FrameParts.FrameCreate(x+20,y+20,830,830,"Corgi00入荷実績検索　WT100_ArrivalPlan_00_Search","NK");
		JLabel Msuserinfo = B100_FrameParts.UserInfo();
		JButton Msexit_btn = B100_FrameParts.ExitBtn();
		
		Ms_fm.add(Msuserinfo);
		Ms_fm.add(Msexit_btn);
		
		JLabel LBMs_ClWh		= B100_FrameParts.JLabelSet(  0, 50,100,20,"担当倉庫:"			,11,1);
		JLabel LBMs_ClCd		= B100_FrameParts.JLabelSet(  0, 75,100,20,"荷主:"				,11,1);
		JLabel LBMs_ClGpCD		= B100_FrameParts.JLabelSet(  0,100,100,20,"荷主グループ:"		,11,1);
		JLabel LBMs_SpCd		= B100_FrameParts.JLabelSet(  0,125,100,20,"仕入先:"			,11,1);
		
		JLabel LBMs_GetArrNo	= B100_FrameParts.JLabelSet(  0,175,100,20,"入荷予定NO:"		,11,1);
		JLabel LBMs_ArrCount	= B100_FrameParts.JLabelSet(  0,200,100,20,"入荷予定枝番:"		,11,1);
		JLabel LBMs_ClArrNo		= B100_FrameParts.JLabelSet(  0,225,100,20,"荷主予定番号:"		,11,1);
		JLabel LBMs_PlanDate	= B100_FrameParts.JLabelSet(  0,250,100,20,"入荷予定日:"		,11,1);
		JLabel LBMs_ActualDate	= B100_FrameParts.JLabelSet(  0,275,100,20,"入荷実績日:"		,11,1);
		
		JLabel LBMs_PlanTotalQty	= B100_FrameParts.JLabelSet(200,250,70,20,"予定数計:"		,11,1);
		JLabel LBMs_ActualTotalQty	= B100_FrameParts.JLabelSet(200,275,70,20,"実績数計:"		,11,1);
		
		JLabel LBMs_SpName01	= B100_FrameParts.JLabelSet(340,150,100,20,"仕入先名01:"		,11,1);
		JLabel LBMs_SpPost		= B100_FrameParts.JLabelSet(340,175,100,20,"仕入先郵便:"		,11,1);
		JLabel LBMs_SpAdd01		= B100_FrameParts.JLabelSet(340,200,100,20,"仕入先住所01:"		,11,1);
		JLabel LBMs_SpAdd02		= B100_FrameParts.JLabelSet(340,225,100,20,"仕入先住所02:"		,11,1);
		JLabel LBMs_SpAdd03		= B100_FrameParts.JLabelSet(340,250,100,20,"仕入先住所03:"		,11,1);
		JLabel LBMs_SpTel		= B100_FrameParts.JLabelSet(340,275,100,20,"仕入先電話:"		,11,1);
		
		JLabel LBMs_ArCom01		= B100_FrameParts.JLabelSet(340, 50,100,20,"コメント1:"		,11,1);
		JLabel LBMs_ArCom02		= B100_FrameParts.JLabelSet(340, 75,100,20,"コメント2:"		,11,1);
		JLabel LBMs_ArCom03		= B100_FrameParts.JLabelSet(340,100,100,20,"コメント3:"		,11,1);
		
		JLabel LBMs_EntryDate	= B100_FrameParts.JLabelSet(  0,300,100,20,"登録日:"			,11,1);
		JLabel LBMs_UpdateDate	= B100_FrameParts.JLabelSet(  0,325,100,20,"更新日:"			,11,1);
		JLabel LBMs_EntryUser	= B100_FrameParts.JLabelSet(340,300,100,20,"登録者:"			,11,1);
		JLabel LBMs_UpdateUser	= B100_FrameParts.JLabelSet(340,325,100,20,"更新者:"			,11,1);
		
		final JComboBox TBMs_ClWh			= B100_FrameParts.JComboBoxSet(	100, 50,240,20,B100_DefaultVariable.WhList[0],11);						//ヘッダ担当倉庫
		final JComboBox TBMs_ClCd			= B100_FrameParts.JComboBoxSet(	100, 75,240,20,B100_DefaultVariable.ClList[0],11);						//ヘッダ荷主CD
		final JComboBox TBMs_ClGpCD			= B100_FrameParts.JComboBoxSet(	100,100,240,20,B100_DefaultVariable.ClGpList[0],11);					//ヘッダ荷主グループCD
		final JComboBox TBMs_SpCd			= B100_FrameParts.JComboBoxSet(	100,125,240,20,B100_DefaultVariable.SupplierList[0],11);				//ヘッダ仕入先
		
		final JTextField TBMs_ArrNo			= B100_FrameParts.JTextFieldSet(	100,175,100,20,"",11,0);	//入荷予定NO
		final JTextField TBMs_ArrCount		= B100_FrameParts.JTextFieldSet(	100,200,100,20,"",11,0);	//入荷予定枝番
		final JTextField TBMs_ClArrNo		= B100_FrameParts.JTextFieldSet(	100,225,100,20,"",11,0);	//荷主予定番号
		final JTextField TBMs_PlanDate		= B100_FrameParts.JTextFieldSet(	100,250,100,20,"",11,0);	//入荷予定日
		final JTextField TBMs_ActualDate	= B100_FrameParts.JTextFieldSet(	100,275,100,20,"",11,0);	//入荷実績日
		
		final JTextField TBMs_PlanTotalQty	= B100_FrameParts.JTextFieldSet(	270,250, 70,20,"",11,1);
		final JTextField TBMs_ActualTotalQty= B100_FrameParts.JTextFieldSet(	270,275, 70,20,"",11,1);
		
		final JTextField TBMs_SpName01		= B100_FrameParts.JTextFieldSet(	440,150,200,20,"",11,0);	//仕入先名01
		final JTextField TBMs_SpPost		= B100_FrameParts.JTextFieldSet(	440,175,100,20,"",11,0);	//仕入先郵便
		final JTextField TBMs_SpAdd01		= B100_FrameParts.JTextFieldSet(	440,200,200,20,"",11,0);	//仕入先住所01
		final JTextField TBMs_SpAdd02		= B100_FrameParts.JTextFieldSet(	440,225,200,20,"",11,0);	//仕入先住所02
		final JTextField TBMs_SpAdd03		= B100_FrameParts.JTextFieldSet(	440,250,200,20,"",11,0);	//仕入先住所03
		final JTextField TBMs_SpTel			= B100_FrameParts.JTextFieldSet(	440,275,100,20,"",11,0);	//仕入先電話
		
		final JTextField TBMs_ArCom01		= B100_FrameParts.JTextFieldSet(  440, 50,200,20,"",11,0);	//コメント1
		final JTextField TBMs_ArCom02		= B100_FrameParts.JTextFieldSet(  440, 75,200,20,"",11,0);	//コメント2
		final JTextField TBMs_ArCom03		= B100_FrameParts.JTextFieldSet(  440,100,200,20,"",11,0);	//コメント3
		
		final JTextField TBMs_EntryDate		= B100_FrameParts.JTextFieldSet(	100,300,150,20,"",11,0);	//登録日
		final JTextField TBMs_UpdateDate	= B100_FrameParts.JTextFieldSet(	100,325,150,20,"",11,0);	//更新日
		final JTextField TBMs_EntryUser		= B100_FrameParts.JTextFieldSet(	440,300,200,20,"",11,0);	//登録者
		final JTextField TBMs_UpdateUser	= B100_FrameParts.JTextFieldSet(	440,325,200,20,"",11,0);	//更新者
		
		TBMs_ClWh.setEnabled(false);
		TBMs_ClCd.setEnabled(false);
		TBMs_ClGpCD.setEnabled(false);
		TBMs_SpCd.setEnabled(false);
		
		TBMs_ArrNo.setEditable(false);
		TBMs_ArrCount.setEditable(false);
		TBMs_ClArrNo.setEditable(false);
		TBMs_PlanDate.setEditable(false);
		TBMs_ActualDate.setEditable(false);
		TBMs_PlanTotalQty.setEditable(false);
		TBMs_ActualTotalQty.setEditable(false);
		
		TBMs_SpName01.setEditable(false);
		TBMs_SpPost.setEditable(false);
		TBMs_SpAdd01.setEditable(false);
		TBMs_SpAdd02.setEditable(false);
		TBMs_SpAdd03.setEditable(false);
		TBMs_SpTel.setEditable(false);
		
		TBMs_ArCom01.setEditable(false);
		TBMs_ArCom02.setEditable(false);
		TBMs_ArCom03.setEditable(false);
		
		TBMs_EntryDate.setEditable(false);
		TBMs_UpdateDate.setEditable(false);
		TBMs_EntryUser.setEditable(false);
		TBMs_UpdateUser.setEditable(false);
		
		Ms_fm.add(LBMs_ClWh);
		Ms_fm.add(LBMs_ClCd);
		Ms_fm.add(LBMs_ClGpCD);
		Ms_fm.add(LBMs_SpCd);
		
		Ms_fm.add(LBMs_GetArrNo);
		Ms_fm.add(LBMs_ArrCount);
		Ms_fm.add(LBMs_ClArrNo);
		Ms_fm.add(LBMs_PlanDate);
		Ms_fm.add(LBMs_ActualDate);
		Ms_fm.add(LBMs_PlanTotalQty);
		Ms_fm.add(LBMs_ActualTotalQty);
		
		Ms_fm.add(LBMs_SpName01);
		Ms_fm.add(LBMs_SpPost);
		Ms_fm.add(LBMs_SpAdd01);
		Ms_fm.add(LBMs_SpAdd02);
		Ms_fm.add(LBMs_SpAdd03);
		Ms_fm.add(LBMs_SpTel);
		
		Ms_fm.add(LBMs_ArCom01);
		Ms_fm.add(LBMs_ArCom02);
		Ms_fm.add(LBMs_ArCom03);
		
		Ms_fm.add(LBMs_EntryDate);
		Ms_fm.add(LBMs_UpdateDate);
		Ms_fm.add(LBMs_EntryUser);
		Ms_fm.add(LBMs_UpdateUser);
		
		
		Ms_fm.add(TBMs_ClWh);
		Ms_fm.add(TBMs_ClCd);
		Ms_fm.add(TBMs_ClGpCD);
		Ms_fm.add(TBMs_SpCd);
		
		Ms_fm.add(TBMs_ArrNo);
		Ms_fm.add(TBMs_ArrCount);
		Ms_fm.add(TBMs_ClArrNo);
		Ms_fm.add(TBMs_PlanDate);
		Ms_fm.add(TBMs_ActualDate);
		
		Ms_fm.add(TBMs_PlanTotalQty);
		Ms_fm.add(TBMs_ActualTotalQty);
		
		Ms_fm.add(TBMs_SpName01);
		Ms_fm.add(TBMs_SpPost);
		Ms_fm.add(TBMs_SpAdd01);
		Ms_fm.add(TBMs_SpAdd02);
		Ms_fm.add(TBMs_SpAdd03);
		Ms_fm.add(TBMs_SpTel);
		
		Ms_fm.add(TBMs_ArCom01);
		Ms_fm.add(TBMs_ArCom02);
		Ms_fm.add(TBMs_ArCom03);
		
		Ms_fm.add(TBMs_EntryDate);
		Ms_fm.add(TBMs_UpdateDate);
		Ms_fm.add(TBMs_EntryUser);
		Ms_fm.add(TBMs_UpdateUser);
		
		Object[][] RtArrivalMsRt = T100_ArrivalMsRt.RtArrivalMsRt();
		
		String[] MscolumnNames01 = new String[RtArrivalMsRt.length+1];
		
		MscolumnNames01[0] = "Fg";
		for(int i=0;i<RtArrivalMsRt.length;i++) {
			MscolumnNames01[1+(int)RtArrivalMsRt[i][1]] = ""+RtArrivalMsRt[i][3];
		}
		
		//編集可能カラムの指定
		B100_TableControl.RenewTgt = new int[1];
		B100_TableControl.RenewTgt[0] = 0;

		final DefaultTableModel MsFmTableModel = new B100_TableControl.MyTableModel01(MscolumnNames01,0);
		
		final JTable MsTb01 = new JTable(MsFmTableModel);
		MsTb01.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		MsTb01.setRowHeight(20*A00000_Main.Mul/A00000_Main.Div);
		MsTb01.setFont(new Font(A00000_Main.DefaultFont, Font.PLAIN, 12*A00000_Main.Mul/A00000_Main.Div));
		
		DefaultTableColumnModel MscolumnModel01
		= (DefaultTableColumnModel)MsTb01.getColumnModel();
		
		//列幅初期設定 表示位置設定
		column = null;
		
		column = MscolumnModel01.getColumn( 0);	column.setPreferredWidth( 30*A00000_Main.Mul/A00000_Main.Div);	//FG
		
		for(int i=0;i<RtArrivalMsRt.length;i++) {
			if("int".equals((String)RtArrivalMsRt[i][2])||"float".equals((String)RtArrivalMsRt[i][2])) {
				column = MscolumnModel01.getColumn(1+(int)RtArrivalMsRt[i][1]);	column.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.rightCellRenderer());
			}else {
				column = MscolumnModel01.getColumn(1+(int)RtArrivalMsRt[i][1]);	column.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.leftCellRenderer());
			}
		}
		//スクロール用設定
		JScrollPane MsScpn01 = B100_FrameParts.JScrollPaneSet(10,350,790,250,MsTb01);
		Ms_fm.add(MsScpn01);
		
		//明細WW0013ArrivalMs由来
		JLabel LBMs_MsNo			= B100_FrameParts.JLabelSet(  0,605,100,20,"明細番号:"			,11,1);
		JLabel LBMs_MsSeq			= B100_FrameParts.JLabelSet(200,605,100,20,"明細Seq番号:"		,11,1);
		JLabel LBMs_MsActualDate	= B100_FrameParts.JLabelSet(  0,630,100,20,"入荷日:"			,11,1);
		JLabel LBMs_ItemCd			= B100_FrameParts.JLabelSet(  0,655,100,20,"商品コード:"		,11,1);
		JLabel LBMs_ClItemCd		= B100_FrameParts.JLabelSet(  0,680,100,20,"荷主商品コード:"	,11,1);
		JLabel LBMs_JanCd			= B100_FrameParts.JLabelSet(  0,705,100,20,"JanCd(バラ):"		,11,1);
		JLabel LBMs_ItemMdNo		= B100_FrameParts.JLabelSet(  0,730,100,20,"商品型番:"			,11,1);
		
		JLabel LBMs_ActualQty		= B100_FrameParts.JLabelSet(200,630,100,20,"実績数:"			,11,1);
		JLabel LBMs_ItemName		= B100_FrameParts.JLabelSet(200,655,100,20,"商品名:"			,11,1);
		JLabel LBMs_Lot				= B100_FrameParts.JLabelSet(200,680,100,20,"ロット:"			,11,1);
		JLabel LBMs_ExpDate			= B100_FrameParts.JLabelSet(200,705,100,20,"消費期限:"			,11,1);
		JLabel LBMs_PlanQty			= B100_FrameParts.JLabelSet(200,730,100,20,"予定数量:"			,11,1);
		
		JLabel LBMs_Com01			= B100_FrameParts.JLabelSet(500,605,100,20,"コメント1:"		,11,1);
		JLabel LBMs_Com02			= B100_FrameParts.JLabelSet(500,630,100,20,"コメント2:"		,11,1);
		
		JLabel LBMs_MsEntryDate		= B100_FrameParts.JLabelSet(500,655,100,20,"登録日:"			,11,1);
		JLabel LBMs_MsUpdateDate	= B100_FrameParts.JLabelSet(500,680,100,20,"更新日:"			,11,1);
		JLabel LBMs_MsEntryUser		= B100_FrameParts.JLabelSet(500,705,100,20,"登録者:"			,11,1);
		JLabel LBMs_MsUpdateUser	= B100_FrameParts.JLabelSet(500,730,100,20,"更新者:"			,11,1);
		
		final JTextField TBMs_MsNo			= B100_FrameParts.JTextFieldSet(100,605,100,20,""		,11,1);
		final JTextField TBMs_MsSeq			= B100_FrameParts.JTextFieldSet(300,605,100,20,""		,11,1);
		final JTextField TBMs_MsActualDate	= B100_FrameParts.JTextFieldSet(100,630,100,20,""		,11,0);
		final JTextField TBMs_ItemCd		= B100_FrameParts.JTextFieldSet(100,655,100,20,""		,11,0);
		final JTextField TBMs_ClItemCd		= B100_FrameParts.JTextFieldSet(100,680,100,20,""		,11,0);
		final JTextField TBMs_JanCd			= B100_FrameParts.JTextFieldSet(100,705,100,20,""		,11,0);
		final JTextField TBMs_ItemMdNo		= B100_FrameParts.JTextFieldSet(100,730,100,20,""		,11,0);
		
		final JTextField TBMs_ActualQty		= B100_FrameParts.JTextFieldSet(300,630,100,20,""		,11,1);
		final JTextField TBMs_ItemName		= B100_FrameParts.JTextFieldSet(300,655,200,20,""		,11,0);
		final JTextField TBMs_Lot			= B100_FrameParts.JTextFieldSet(300,680,100,20,""		,11,0);
		final JTextField TBMs_ExpDate		= B100_FrameParts.JTextFieldSet(300,705,100,20,""		,11,0);
		final JTextField TBMs_PlanQty		= B100_FrameParts.JTextFieldSet(300,730,100,20,""		,11,1);
		
		
		final JTextField TBMs_Com01			= B100_FrameParts.JTextFieldSet(600,605,200,20,""		,11,0);
		final JTextField TBMs_Com02			= B100_FrameParts.JTextFieldSet(600,630,200,20,""		,11,0);
		
		final JTextField TBMs_MsEntryDate	= B100_FrameParts.JTextFieldSet(600,655,150,20,""		,11,0);
		final JTextField TBMs_MsUpdateDate	= B100_FrameParts.JTextFieldSet(600,680,150,20,""		,11,0);
		final JTextField TBMs_MsEntryUser	= B100_FrameParts.JTextFieldSet(600,705,200,20,""		,11,0);
		final JTextField TBMs_MsUpdateUser	= B100_FrameParts.JTextFieldSet(600,730,200,20,""		,11,0);
		
		TBMs_MsNo.setEditable(false);
		TBMs_MsSeq.setEditable(false);
		TBMs_MsActualDate.setEditable(false);
		TBMs_ItemCd.setEditable(false);
		TBMs_ClItemCd.setEditable(false);
		TBMs_JanCd.setEditable(false);
		TBMs_ItemMdNo.setEditable(false);
		
		TBMs_ActualQty.setEditable(false);
		TBMs_ItemName.setEditable(false);
		TBMs_Lot.setEditable(false);
		TBMs_ExpDate.setEditable(false);
		TBMs_PlanQty.setEditable(false);
		
		TBMs_Com01.setEditable(false);
		TBMs_Com02.setEditable(false);
		
		TBMs_MsEntryDate.setEditable(false);
		TBMs_MsUpdateDate.setEditable(false);
		TBMs_MsEntryUser.setEditable(false);
		TBMs_MsUpdateUser.setEditable(false);
		
		Ms_fm.add(LBMs_MsNo);
		Ms_fm.add(LBMs_MsSeq);
		Ms_fm.add(LBMs_MsActualDate);
		Ms_fm.add(LBMs_ItemCd);
		Ms_fm.add(LBMs_ClItemCd);
		Ms_fm.add(LBMs_JanCd);
		Ms_fm.add(LBMs_ItemMdNo);
		
		Ms_fm.add(LBMs_ItemName);
		Ms_fm.add(LBMs_Lot);
		Ms_fm.add(LBMs_ExpDate);
		Ms_fm.add(LBMs_PlanQty);
		Ms_fm.add(LBMs_ActualQty);
		
		Ms_fm.add(LBMs_Com01);
		Ms_fm.add(LBMs_Com02);
		
		Ms_fm.add(LBMs_MsEntryDate);
		Ms_fm.add(LBMs_MsUpdateDate);
		Ms_fm.add(LBMs_MsEntryUser);
		Ms_fm.add(LBMs_MsUpdateUser);
		
		Ms_fm.add(TBMs_MsNo);
		Ms_fm.add(TBMs_MsSeq);
		Ms_fm.add(TBMs_MsActualDate);
		Ms_fm.add(TBMs_ItemCd);
		Ms_fm.add(TBMs_ClItemCd);
		Ms_fm.add(TBMs_JanCd);
		Ms_fm.add(TBMs_ItemMdNo);
		
		Ms_fm.add(TBMs_ItemName);
		Ms_fm.add(TBMs_Lot);
		Ms_fm.add(TBMs_ExpDate);
		Ms_fm.add(TBMs_PlanQty);
		Ms_fm.add(TBMs_ActualQty);
		
		Ms_fm.add(TBMs_Com01);
		Ms_fm.add(TBMs_Com02);
		
		Ms_fm.add(TBMs_MsEntryDate);
		Ms_fm.add(TBMs_MsUpdateDate);
		Ms_fm.add(TBMs_MsEntryUser);
		Ms_fm.add(TBMs_MsUpdateUser);
		
		
		RenewFg = true;
		main_fm.setVisible(true);
		
		final Object[] MsViewControlSet = {
					MainFmTableModel,
					tb01,
					Ms_fm,
					MsFmTableModel,
					MsTb01,
					TBMs_ClWh,
					TBMs_ClCd,
					TBMs_ClGpCD,
					TBMs_SpCd,
					
					TBMs_ArrNo,
					TBMs_ArrCount,
					TBMs_ClArrNo,
					TBMs_PlanDate,
					TBMs_ActualDate,
					
					TBMs_PlanTotalQty,
					TBMs_ActualTotalQty,
					
					TBMs_SpName01,
					TBMs_SpPost,
					TBMs_SpAdd01,
					TBMs_SpAdd02,
					TBMs_SpAdd03,
					TBMs_SpTel,
					
					TBMs_ArCom01,
					TBMs_ArCom02,
					TBMs_ArCom03,
					
					TBMs_EntryDate,
					TBMs_UpdateDate,
					TBMs_EntryUser,
					TBMs_UpdateUser,
					
					TBMs_MsNo,
					TBMs_MsSeq,
					TBMs_MsActualDate,
					TBMs_ItemCd,
					TBMs_ClItemCd,
					TBMs_JanCd,
					TBMs_ItemMdNo,
					
					TBMs_ActualQty,
					TBMs_ItemName,
					TBMs_Lot,
					TBMs_ExpDate,
					TBMs_PlanQty,
					
					TBMs_Com01,
					TBMs_Com02,
					
					TBMs_MsEntryDate,
					TBMs_MsUpdateDate,
					TBMs_MsEntryUser,
					TBMs_MsUpdateUser
					};
		
		//明細情報チェックボックス操作時の挙動
		MsFmTableModel.addTableModelListener(new TableModelListener(){
			public void tableChanged(TableModelEvent e){
				if(RenewFg) {
					RenewFg = false;
					
					TBMs_MsNo.setText("");
					TBMs_MsSeq.setText("");
					TBMs_MsActualDate.setText("");
					TBMs_ItemCd.setText("");
					TBMs_ClItemCd.setText("");
					TBMs_JanCd.setText("");
					TBMs_ItemMdNo.setText("");
					
					TBMs_ActualQty.setText("");
					TBMs_ItemName.setText("");
					TBMs_Lot.setText("");
					TBMs_ExpDate.setText("");
					TBMs_PlanQty.setText("");
					
					TBMs_Com01.setText("");
					TBMs_Com02.setText("");
					
					TBMs_MsEntryDate.setText("");
					TBMs_MsUpdateDate.setText("");
					TBMs_MsEntryUser.setText("");
					TBMs_MsUpdateUser.setText("");
					
					int row_count = MsFmTableModel.getRowCount();
					Boolean setBL=Boolean.valueOf(false);
					for(int i=0;i<row_count;i++){
						if(i!=e.getFirstRow()){
							MsFmTableModel.setValueAt(setBL, i, 0);
						}else {
							if((boolean)MsFmTableModel.getValueAt(i, 0)) {
								int GetMsNo				= B100_TextControl.TextToInt(""+MsFmTableModel.getValueAt(i,1+T100_ArrivalMsRt.ColMsNo));
								int GetMsSeq			= B100_TextControl.TextToInt(""+MsFmTableModel.getValueAt(i,1+T100_ArrivalMsRt.ColMsSeq));
								String GetItemCd		= B100_TextControl.Trim(""+MsFmTableModel.getValueAt(i,1+T100_ArrivalMsRt.ColItemCd));
								String GetClItemCd		= B100_TextControl.Trim(""+MsFmTableModel.getValueAt(i,1+T100_ArrivalMsRt.ColClItemCd));
								String GetJanCd			= B100_TextControl.Trim(""+MsFmTableModel.getValueAt(i,1+T100_ArrivalMsRt.ColJanCd));
								String GetItemMdNo		= B100_TextControl.Trim(""+MsFmTableModel.getValueAt(i,1+T100_ArrivalMsRt.ColItemMdNo));
								String GetItemName		= B100_TextControl.Trim(""+MsFmTableModel.getValueAt(i,1+T100_ArrivalMsRt.ColItemName));
								String GetLot			= B100_TextControl.Trim(""+MsFmTableModel.getValueAt(i,1+T100_ArrivalMsRt.ColLot));
								String GetExpDate		= B100_TextControl.Trim(""+MsFmTableModel.getValueAt(i,1+T100_ArrivalMsRt.ColExpDate));
								int GetPlanQty			= B100_TextControl.TextToInt(""+MsFmTableModel.getValueAt(i,1+T100_ArrivalMsRt.ColPlanQty));
								int GetActualQty		= B100_TextControl.TextToInt(""+MsFmTableModel.getValueAt(i,1+T100_ArrivalMsRt.ColActualQty));
								String GetMsActualDate	= B100_TextControl.Trim(""+MsFmTableModel.getValueAt(i,1+T100_ArrivalMsRt.ColMsActualDate));
								String GetCom01			= B100_TextControl.Trim(""+MsFmTableModel.getValueAt(i,1+T100_ArrivalMsRt.ColCom01));
								String GetCom02			= B100_TextControl.Trim(""+MsFmTableModel.getValueAt(i,1+T100_ArrivalMsRt.ColCom02));
								String GetMsEntryDate	= B100_TextControl.Trim(""+MsFmTableModel.getValueAt(i,1+T100_ArrivalMsRt.ColMsEntryDate));
								String GetMsUpdateDate	= B100_TextControl.Trim(""+MsFmTableModel.getValueAt(i,1+T100_ArrivalMsRt.ColMsUpdateDate));
								String GetMsEntryUser	= B100_TextControl.Trim(""+MsFmTableModel.getValueAt(i,1+T100_ArrivalMsRt.ColMsEntryUser));
								String GetMsUpdateUser	= B100_TextControl.Trim(""+MsFmTableModel.getValueAt(i,1+T100_ArrivalMsRt.ColMsUpdateUser));
								
								TBMs_MsNo.setText(""+GetMsNo);
								TBMs_MsSeq.setText(""+GetMsSeq);
								TBMs_MsActualDate.setText(GetMsActualDate);
								TBMs_ItemCd.setText(GetItemCd);
								TBMs_ClItemCd.setText(GetClItemCd);
								TBMs_JanCd.setText(GetJanCd);
								TBMs_ItemMdNo.setText(GetItemMdNo);
								
								TBMs_ActualQty.setText(""+GetActualQty);
								TBMs_ItemName.setText(GetItemName);
								TBMs_Lot.setText(GetLot);
								TBMs_ExpDate.setText(GetExpDate);
								TBMs_PlanQty.setText(""+GetPlanQty);
								
								TBMs_Com01.setText(GetCom01);
								TBMs_Com02.setText(GetCom02);
								
								TBMs_MsEntryDate.setText(GetMsEntryDate);
								TBMs_MsUpdateDate.setText(GetMsUpdateDate);
								TBMs_MsEntryUser.setText(GetMsEntryUser);
								TBMs_MsUpdateUser.setText(GetMsUpdateUser);
							}
						}
					}
					
					RenewFg = true;
				}
			}
		});
		
		//検索ボタン押下時の挙動
		SearchBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					int RowCount = MainFmTableModel.getRowCount();
					for(int i=0;i<RowCount;i++) {
						MainFmTableModel.removeRow(0);
					}
					
					String GetSearchClWh			= B100_DefaultVariable.SearchWhList[1][TB_SearchClWh.getSelectedIndex()];
					String GetSearchClCd			= B100_DefaultVariable.SearchClList[1][TB_SearchClCd.getSelectedIndex()];
					String GetSearchClGpCD			= B100_DefaultVariable.SearchClGpList[1][TB_SearchClGpCD.getSelectedIndex()];
					String GetSearchSpCd			= B100_DefaultVariable.SearchSupplierList[1][TB_SearchSpCd.getSelectedIndex()];
					String GetSearchArrNo			= TB_SearchArrNo.getText();
					String GetSearchArrCountMin		= TB_SearchArrCountMin.getText();
					
					String GetSearchArrCountMax		= TB_SearchArrCountMax.getText();
					String GetSearchClArrNo			= TB_SearchClArrNo.getText();
					String GetSearchPlanDateMin		= TB_SearchPlanDateMin.getText();
					String GetSearchPlanDateMax		= TB_SearchPlanDateMax.getText();
					String GetSearchActualDateMin	= TB_SearchActualDateMin.getText();
					String GetSearchActualDateMax	= TB_SearchActualDateMax.getText();
					String GetSearchCom				= TB_SearchCom.getText();
					
					
					String GetSearchItemCd			= TB_SearchItemCd.getText();
					String GetSearchClItemCd		= TB_SearchClItemCd.getText();
					String GetSearchItemName		= TB_SearchItemName.getText();
					String GetSearchLot				= TB_SearchLot.getText();
					String GetSearchExpDateMin		= TB_SearchExpDateMin.getText();
					String GetSearchExpDateMax		= TB_SearchExpDateMax.getText();
					
					
					String GetSearchEntryDateMin	= TB_SearchEntryDateMin.getText();
					String GetSearchEntryDateMax	= TB_SearchEntryDateMax.getText();
					String GetSearchUpdateDateMin	= TB_SearchUpdateDateMin.getText();
					String GetSearchUpdateDateMax	= TB_SearchUpdateDateMax.getText();
					String GetSearchEntryUser		= TB_SearchEntryUser.getText();
					String GetSearchUpdateUser		= TB_SearchUpdateUser.getText();
					
					String GetArrNoAny				= TB_ArrNoAny.getText();
					
					Object[][] ArrivalHdRt	= ArrivalHdRt(
							GetSearchClWh,
							GetSearchClCd,
							GetSearchClGpCD,
							GetSearchSpCd,
							GetSearchArrNo,
							GetSearchArrCountMin,
							
							GetSearchArrCountMax,
							GetSearchClArrNo,
							GetSearchPlanDateMin,
							GetSearchPlanDateMax,
							GetSearchActualDateMin,
							GetSearchActualDateMax,
							GetSearchCom,
							
							GetSearchItemCd,
							GetSearchClItemCd,
							GetSearchItemName,
							GetSearchLot,
							GetSearchExpDateMin,
							GetSearchExpDateMax,
							
							GetSearchEntryDateMin,
							GetSearchEntryDateMax,
							GetSearchUpdateDateMin,
							GetSearchUpdateDateMax,
							GetSearchEntryUser,
							GetSearchUpdateUser,
							
							GetArrNoAny
							);
					if(0==ArrivalHdRt.length) {
						B100_TableControl.AddSortOFF(tb01,MainFmTableModel);
					}else {
						for(int i=0;i<ArrivalHdRt.length;i++) {
							Object[] SetOb = new Object[ArrivalHdRt[i].length+1];
							SetOb[0] = false;
							for(int i01=0;i01<ArrivalHdRt[i].length;i01++) {
								SetOb[i01+1] = ""+ArrivalHdRt[i][i01];
							}
							MainFmTableModel.addRow(SetOb);
						}
						B100_TableControl.AddSortON(tb01,MainFmTableModel);
					}
					
					Ms_fm.setVisible(false);
					RenewFg = true;
				}
			}
		});
		
		
		//条件クリアボタン押下時の挙動
		SearchCrearBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					Ms_fm.setVisible(false);
					int RowCount = MainFmTableModel.getRowCount();
					for(int i=0;i<RowCount;i++) {
						MainFmTableModel.removeRow(0);
					}
					
					B100_TableControl.AddSortOFF(tb01,MainFmTableModel);
					
					TB_SearchClWh.setSelectedIndex(		B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.SearchWhList[1]	,A00000_Main.ClWh,true));			//ヘッダ担当倉庫
					TB_SearchClCd.setSelectedIndex(		B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.SearchClList[1]	,A00000_Main.ClCd,true));			//ヘッダ荷主CD
					TB_SearchClGpCD.setSelectedIndex(	B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.SearchClGpList[1]	,A00000_Main.ClGp,true));			//ヘッダ荷主グループCD
					TB_SearchSpCd.setSelectedIndex(0);
					
					TB_SearchArrNo.setText("");
					TB_SearchArrCountMin.setText("");
					TB_SearchArrCountMax.setText("");
					TB_SearchClArrNo.setText("");
					TB_SearchPlanDateMin.setText("");
					TB_SearchPlanDateMax.setText("");
					TB_SearchActualDateMin.setText("");
					TB_SearchActualDateMax.setText("");
					TB_SearchCom.setText("");
					
					
					TB_SearchItemCd.setText("");
					TB_SearchClItemCd.setText("");
					TB_SearchItemName.setText("");
					TB_SearchLot.setText("");
					TB_SearchExpDateMin.setText("");
					TB_SearchExpDateMax.setText("");
					
					
					TB_SearchEntryDateMin.setText("");
					TB_SearchEntryDateMax.setText("");
					TB_SearchUpdateDateMin.setText("");
					TB_SearchUpdateDateMax.setText("");
					TB_SearchEntryUser.setText("");
					TB_SearchUpdateUser.setText("");
					
					TB_ArrNoAny.setText("");
					
					
					
					RenewFg = true;
				}
			}
		});
		//詳細表示Exitボタン押下時の挙動
		Msexit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					Ms_fm.setVisible(false);
					MsViewMode = false;
					RenewFg = true;
				}
			}
		});
		
		//詳細表示ボタン押下時の挙動
		MsViewBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					MsViewMode = true;
					MsView(MsViewControlSet);
					RenewFg = true;
				}
			}
		});
				
		//チェックボックス操作時の挙動
		MainFmTableModel.addTableModelListener(new TableModelListener(){
			public void tableChanged(TableModelEvent e){
				if(RenewFg) {
					RenewFg = false;
					int row_count = tb01.getRowCount();
					Boolean setBL=Boolean.valueOf(false);
					for(int i=0;i<row_count;i++){
						if(i!=e.getFirstRow()){
							MainFmTableModel.setValueAt(setBL, i, 0);
						}else {
	
						}
					}
					MsView(MsViewControlSet);
					RenewFg = true;
				}
			}
		});
		
		//CSVボタン押下時の挙動
		CsvBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					B100_TableControl.TableOutPutCsv("出力先選択","入荷実績（ヘッダ）検索結果",tb01);
					RenewFg = true;
				}
			}
		});
		
		//エクセル出力ボタン押下時の挙動
		ExcelBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					B100_TableControl.TableOutPutExcel("出力先選択","入荷実績（ヘッダ）検索結果",tb01);
					RenewFg = true;
				}
			}
		});
		//明細付CSVボタン押下時の挙動
		MsCsvBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					String Selected = B100_FolderSelect.FolderSelect("出力先選択");
					if(null!=Selected) {
						String[][] OutData	= ArrivalMsOutDataCreate(MainFmTableModel);
						String NowDTM=B100_DateTimeControl.dtmString2(B100_DateTimeControl.dtm()[1])[1].replace(" ", "").replace("/", "").replace(":", "");
						String fp = Selected+"\\"+"入荷実績（明細）検索結果"+NowDTM+".csv";;
						
						B100_TextExport.create_csv(OutData,fp,"UTF-8");
						
						//ファイル開く
						File file = new File(fp);
						Desktop desktop = Desktop.getDesktop();
						try {
							desktop.open(file);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					
					RenewFg = true;
				}
			}
		});
		
		//明細付Excel出力ボタン押下時の挙動
		MsExcelBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					String Selected = B100_FolderSelect.FolderSelect("出力先選択");
					if(null!=Selected) {
						String[][] OutData	= ArrivalMsOutDataCreate(MainFmTableModel);
						String NowDTM=B100_DateTimeControl.dtmString2(B100_DateTimeControl.dtm()[1])[1].replace(" ", "").replace("/", "").replace(":", "");
						String fp = Selected+"\\"+"入荷実績（明細）検索結果"+NowDTM+".xlsx";
						
						int MFG = 0;
						int OPFG = 1;
						B100_ExcelControl.EXCELL_DATA_SET(fp,"入荷実績（明細）検索結果",OutData ,MFG,OPFG);
						
						//ファイル開く
						File file = new File(fp);
						Desktop desktop = Desktop.getDesktop();
						try {
							desktop.open(file);
						} catch (IOException e1) {
							e1.printStackTrace();
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
				
				Ms_fm.setVisible(false);
				Ms_fm.dispose();

				main_fm.setVisible(false);
				main_fm.dispose();
				A00001_WorkMain.WorkMain(0,0);
			}
		});
	}
	
	private static void MsView(Object[] MsViewControlSet){
		final int ColMainFmTableModel	=  0;
		final int Coltb01				=  1;
		final int ColMs_fm				=  2;
		final int ColMsFmTableModel		=  3;
		final int ColMsTb01				=  4;
		final int ColTBMs_ClWh			=  5;
		final int ColTBMs_ClCd			=  6;
		final int ColTBMs_ClGpCD		=  7;
		final int ColTBMs_SpCd			=  8;
		
		final int ColTBMs_ArrNo			=  9;
		final int ColTBMs_ArrCount		= 10;
		final int ColTBMs_ClArrNo		= 11;
		final int ColTBMs_PlanDate		= 12;
		final int ColTBMs_ActualDate	= 13;
		final int ColTBMs_PlanTotalQty	= 14;
		final int ColTBMs_ActualTotalQty= 15;
		
		final int ColTBMs_SpName01		= 16;
		final int ColTBMs_SpPost		= 17;
		final int ColTBMs_SpAdd01		= 18;
		final int ColTBMs_SpAdd02		= 19;
		final int ColTBMs_SpAdd03		= 20;
		final int ColTBMs_SpTel			= 21;
		
		final int ColTBMs_ArCom01		= 22;
		final int ColTBMs_ArCom02		= 23;
		final int ColTBMs_ArCom03		= 24;
		
		final int ColTBMs_EntryDate		= 25;
		final int ColTBMs_UpdateDate	= 26;
		final int ColTBMs_EntryUser		= 27;
		final int ColTBMs_UpdateUser	= 28;
		
		final int ColTBMs_MsNo			= 29;
		final int ColTBMs_MsSeq			= 30;
		final int ColTBMs_MsActualDate	= 31;
		final int ColTBMs_ItemCd		= 32;
		final int ColTBMs_ClItemCd		= 33;
		final int ColTBMs_JanCd			= 34;
		final int ColTBMs_ItemMdNo		= 35;
		
		final int ColTBMs_ActualQty		= 36;
		final int ColTBMs_ItemName		= 37;
		final int ColTBMs_Lot			= 38;
		final int ColTBMs_ExpDate		= 39;
		final int ColTBMs_PlanQty		= 40;
		
		final int ColTBMs_Com01			= 41;
		final int ColTBMs_Com02			= 42;
		
		final int ColTBMs_MsEntryDate	= 43;
		final int ColTBMs_MsUpdateDate	= 44;
		final int ColTBMs_MsEntryUser	= 45;
		final int ColTBMs_MsUpdateUser	= 46;
		
		((JFrame)MsViewControlSet[ColMs_fm]).setVisible(false);
		
		String GetClWh			= "";
		String GetWHName		= "";
		String GetClCd			= "";
		String GetCLName01		= "";
		String GetClGpCD		= "";
		String GetCLGpName01	= "";
		String GetArrNo			= "";
		int GetArrCount			= 0;
		String GetClArrNo		= "";
		String GetPlanDate		= "";
		String GetActualDate	= "";
		String GetSpCd			= "";
		String GetSpName01		= "";
		String GetSpName02		= "";
		String GetSpName03		= "";
		String GetSpPost		= "";
		String GetSpAdd01		= "";
		String GetSpAdd02		= "";
		String GetSpAdd03		= "";
		String GetSpTel			= "";
		String GetArCom01		= "";
		String GetArCom02		= "";
		String GetArCom03		= "";
		String GetEntryDate		= "";
		String GetUpdateDate	= "";
		String GetEntryUser		= "";
		String GetUpdateUser	= "";
		int GetPlanTotalQty		= 0;
		int GetActualTotalQty	= 0;
		
		((JComboBox)MsViewControlSet[ColTBMs_ClWh]).setSelectedIndex(	B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.WhList[1]	,A00000_Main.ClWh,true));
		((JComboBox)MsViewControlSet[ColTBMs_ClCd]).setSelectedIndex(	B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.ClList[1]	,A00000_Main.ClCd,true));
		((JComboBox)MsViewControlSet[ColTBMs_ClGpCD]).setSelectedIndex(	B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.ClGpList[1]	,A00000_Main.ClGp,true));
		((JComboBox)MsViewControlSet[ColTBMs_SpCd]).setSelectedIndex(0);
		
		((JTextField)MsViewControlSet[ColTBMs_ArrNo]).setText("");
		((JTextField)MsViewControlSet[ColTBMs_ArrCount]).setText("");
		((JTextField)MsViewControlSet[ColTBMs_ClArrNo]).setText("");
		((JTextField)MsViewControlSet[ColTBMs_PlanDate]).setText("");
		((JTextField)MsViewControlSet[ColTBMs_ActualDate]).setText("");
		((JTextField)MsViewControlSet[ColTBMs_PlanTotalQty]).setText("0");
		((JTextField)MsViewControlSet[ColTBMs_ActualTotalQty]).setText("0");
		
		((JTextField)MsViewControlSet[ColTBMs_SpName01]).setText("");
		((JTextField)MsViewControlSet[ColTBMs_SpPost]).setText("");
		((JTextField)MsViewControlSet[ColTBMs_SpAdd01]).setText("");
		((JTextField)MsViewControlSet[ColTBMs_SpAdd02]).setText("");
		((JTextField)MsViewControlSet[ColTBMs_SpAdd03]).setText("");
		((JTextField)MsViewControlSet[ColTBMs_SpTel]).setText("");
		
		((JTextField)MsViewControlSet[ColTBMs_ArCom01]).setText("");
		((JTextField)MsViewControlSet[ColTBMs_ArCom02]).setText("");
		((JTextField)MsViewControlSet[ColTBMs_ArCom03]).setText("");
		
		((JTextField)MsViewControlSet[ColTBMs_EntryDate]).setText("");
		((JTextField)MsViewControlSet[ColTBMs_UpdateDate]).setText("");
		((JTextField)MsViewControlSet[ColTBMs_EntryUser]).setText("");
		((JTextField)MsViewControlSet[ColTBMs_UpdateUser]).setText("");
		
		((JTextField)MsViewControlSet[ColTBMs_MsNo]).setText("");
		((JTextField)MsViewControlSet[ColTBMs_MsSeq]).setText("");
		((JTextField)MsViewControlSet[ColTBMs_MsActualDate]).setText("");
		((JTextField)MsViewControlSet[ColTBMs_ItemCd]).setText("");
		((JTextField)MsViewControlSet[ColTBMs_ClItemCd]).setText("");
		((JTextField)MsViewControlSet[ColTBMs_JanCd]).setText("");
		((JTextField)MsViewControlSet[ColTBMs_ItemMdNo]).setText("");
		
		((JTextField)MsViewControlSet[ColTBMs_ActualQty]).setText("");
		((JTextField)MsViewControlSet[ColTBMs_ItemName]).setText("");
		((JTextField)MsViewControlSet[ColTBMs_Lot]).setText("");
		((JTextField)MsViewControlSet[ColTBMs_ExpDate]).setText("");
		((JTextField)MsViewControlSet[ColTBMs_PlanQty]).setText("");
		
		((JTextField)MsViewControlSet[ColTBMs_Com01]).setText("");
		((JTextField)MsViewControlSet[ColTBMs_Com02]).setText("");
		
		((JTextField)MsViewControlSet[ColTBMs_MsEntryDate]).setText("");
		((JTextField)MsViewControlSet[ColTBMs_MsUpdateDate]).setText("");
		((JTextField)MsViewControlSet[ColTBMs_MsEntryUser]).setText("");
		((JTextField)MsViewControlSet[ColTBMs_MsUpdateUser]).setText("");
		
		
		int MainRowCount	=((DefaultTableModel)MsViewControlSet[ColMainFmTableModel]).getRowCount();
		for(int i=0;i<MainRowCount;i++) {
			if((boolean)((DefaultTableModel)MsViewControlSet[ColMainFmTableModel]).getValueAt(i, 0)) {
				GetClWh				= B100_TextControl.Trim(""+((DefaultTableModel)MsViewControlSet[ColMainFmTableModel]).getValueAt(i, 1+T100_ArrivalHdRt.ColClWh));
				GetWHName			= B100_TextControl.Trim(""+((DefaultTableModel)MsViewControlSet[ColMainFmTableModel]).getValueAt(i, 1+T100_ArrivalHdRt.ColWHName));
				GetClCd				= B100_TextControl.Trim(""+((DefaultTableModel)MsViewControlSet[ColMainFmTableModel]).getValueAt(i, 1+T100_ArrivalHdRt.ColClCd));
				GetCLName01			= B100_TextControl.Trim(""+((DefaultTableModel)MsViewControlSet[ColMainFmTableModel]).getValueAt(i, 1+T100_ArrivalHdRt.ColCLName01));
				GetClGpCD			= B100_TextControl.Trim(""+((DefaultTableModel)MsViewControlSet[ColMainFmTableModel]).getValueAt(i, 1+T100_ArrivalHdRt.ColClGpCD));
				GetCLGpName01		= B100_TextControl.Trim(""+((DefaultTableModel)MsViewControlSet[ColMainFmTableModel]).getValueAt(i, 1+T100_ArrivalHdRt.ColCLGpName01));
				GetArrNo			= B100_TextControl.Trim(""+((DefaultTableModel)MsViewControlSet[ColMainFmTableModel]).getValueAt(i, 1+T100_ArrivalHdRt.ColArrNo));
				GetArrCount			= B100_TextControl.TextToInt(""+((DefaultTableModel)MsViewControlSet[ColMainFmTableModel]).getValueAt(i, 1+T100_ArrivalHdRt.ColArrCount));
				GetClArrNo			= B100_TextControl.Trim(""+((DefaultTableModel)MsViewControlSet[ColMainFmTableModel]).getValueAt(i, 1+T100_ArrivalHdRt.ColClArrNo));
				GetPlanDate			= B100_TextControl.Trim(""+((DefaultTableModel)MsViewControlSet[ColMainFmTableModel]).getValueAt(i, 1+T100_ArrivalHdRt.ColPlanDate));
				GetActualDate		= B100_TextControl.Trim(""+((DefaultTableModel)MsViewControlSet[ColMainFmTableModel]).getValueAt(i, 1+T100_ArrivalHdRt.ColActualDate));
				GetSpCd				= B100_TextControl.Trim(""+((DefaultTableModel)MsViewControlSet[ColMainFmTableModel]).getValueAt(i, 1+T100_ArrivalHdRt.ColSpCd));
				GetSpName01			= B100_TextControl.Trim(""+((DefaultTableModel)MsViewControlSet[ColMainFmTableModel]).getValueAt(i, 1+T100_ArrivalHdRt.ColSpName01));
				GetSpName02			= B100_TextControl.Trim(""+((DefaultTableModel)MsViewControlSet[ColMainFmTableModel]).getValueAt(i, 1+T100_ArrivalHdRt.ColSpName02));
				GetSpName03			= B100_TextControl.Trim(""+((DefaultTableModel)MsViewControlSet[ColMainFmTableModel]).getValueAt(i, 1+T100_ArrivalHdRt.ColSpName03));
				GetSpPost			= B100_TextControl.Trim(""+((DefaultTableModel)MsViewControlSet[ColMainFmTableModel]).getValueAt(i, 1+T100_ArrivalHdRt.ColSpPost));
				GetSpAdd01			= B100_TextControl.Trim(""+((DefaultTableModel)MsViewControlSet[ColMainFmTableModel]).getValueAt(i, 1+T100_ArrivalHdRt.ColSpAdd01));
				GetSpAdd02			= B100_TextControl.Trim(""+((DefaultTableModel)MsViewControlSet[ColMainFmTableModel]).getValueAt(i, 1+T100_ArrivalHdRt.ColSpAdd02));
				GetSpAdd03			= B100_TextControl.Trim(""+((DefaultTableModel)MsViewControlSet[ColMainFmTableModel]).getValueAt(i, 1+T100_ArrivalHdRt.ColSpAdd03));
				GetSpTel			= B100_TextControl.Trim(""+((DefaultTableModel)MsViewControlSet[ColMainFmTableModel]).getValueAt(i, 1+T100_ArrivalHdRt.ColSpTel));
				GetArCom01			= B100_TextControl.Trim(""+((DefaultTableModel)MsViewControlSet[ColMainFmTableModel]).getValueAt(i, 1+T100_ArrivalHdRt.ColArCom01));
				GetArCom02			= B100_TextControl.Trim(""+((DefaultTableModel)MsViewControlSet[ColMainFmTableModel]).getValueAt(i, 1+T100_ArrivalHdRt.ColArCom02));
				GetArCom03			= B100_TextControl.Trim(""+((DefaultTableModel)MsViewControlSet[ColMainFmTableModel]).getValueAt(i, 1+T100_ArrivalHdRt.ColArCom03));
				GetEntryDate		= B100_TextControl.Trim(""+((DefaultTableModel)MsViewControlSet[ColMainFmTableModel]).getValueAt(i, 1+T100_ArrivalHdRt.ColEntryDate));
				GetUpdateDate		= B100_TextControl.Trim(""+((DefaultTableModel)MsViewControlSet[ColMainFmTableModel]).getValueAt(i, 1+T100_ArrivalHdRt.ColUpdateDate));
				GetEntryUser		= B100_TextControl.Trim(""+((DefaultTableModel)MsViewControlSet[ColMainFmTableModel]).getValueAt(i, 1+T100_ArrivalHdRt.ColEntryUser));
				GetUpdateUser		= B100_TextControl.Trim(""+((DefaultTableModel)MsViewControlSet[ColMainFmTableModel]).getValueAt(i, 1+T100_ArrivalHdRt.ColUpdateUser));
				GetPlanTotalQty		= B100_TextControl.TextToInt(""+((DefaultTableModel)MsViewControlSet[ColMainFmTableModel]).getValueAt(i, 1+T100_ArrivalHdRt.ColPlanTotalQty));
				GetActualTotalQty	= B100_TextControl.TextToInt(""+((DefaultTableModel)MsViewControlSet[ColMainFmTableModel]).getValueAt(i, 1+T100_ArrivalHdRt.ColActualTotalQty));
			}
		}
		
		String TgtClWh = A00000_Main.ClWh;
		String TgtClCd = A00000_Main.ClCd;
		ArrayList<String> SearchArrNo			= new ArrayList<String>();
		ArrayList<Integer> SearchArrCountMin	= new ArrayList<Integer>();
		ArrayList<Integer> SearchArrCountMax	= new ArrayList<Integer>();
		
		if(!"".equals(GetArrNo)) {
			((JComboBox)MsViewControlSet[ColTBMs_ClWh]).setSelectedIndex(	B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.WhList[1]			,GetClWh,true));
			((JComboBox)MsViewControlSet[ColTBMs_ClCd]).setSelectedIndex(	B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.ClList[1]			,GetClCd,true));
			((JComboBox)MsViewControlSet[ColTBMs_ClGpCD]).setSelectedIndex(	B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.ClGpList[1]		,GetClGpCD,true));
			((JComboBox)MsViewControlSet[ColTBMs_SpCd]).setSelectedIndex(	B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.SupplierList[1]	,GetSpCd,true));
			
			((JTextField)MsViewControlSet[ColTBMs_ArrNo]).setText(GetArrNo);
			((JTextField)MsViewControlSet[ColTBMs_ArrCount]).setText(""+GetArrCount);
			((JTextField)MsViewControlSet[ColTBMs_ClArrNo]).setText(GetClArrNo);
			((JTextField)MsViewControlSet[ColTBMs_PlanDate]).setText(GetPlanDate);
			((JTextField)MsViewControlSet[ColTBMs_ActualDate]).setText(GetActualDate);
			((JTextField)MsViewControlSet[ColTBMs_PlanTotalQty]).setText(""+GetPlanTotalQty);
			((JTextField)MsViewControlSet[ColTBMs_ActualTotalQty]).setText(""+GetActualTotalQty);
			
			((JTextField)MsViewControlSet[ColTBMs_SpName01]).setText(GetSpName01);
			((JTextField)MsViewControlSet[ColTBMs_SpPost]).setText(GetSpPost);
			((JTextField)MsViewControlSet[ColTBMs_SpAdd01]).setText(GetSpAdd01);
			((JTextField)MsViewControlSet[ColTBMs_SpAdd02]).setText(GetSpAdd02);
			((JTextField)MsViewControlSet[ColTBMs_SpAdd03]).setText(GetSpAdd03);
			((JTextField)MsViewControlSet[ColTBMs_SpTel]).setText(GetSpTel);
			
			((JTextField)MsViewControlSet[ColTBMs_ArCom01]).setText(GetArCom01);
			((JTextField)MsViewControlSet[ColTBMs_ArCom02]).setText(GetArCom02);
			((JTextField)MsViewControlSet[ColTBMs_ArCom03]).setText(GetArCom03);
			
			((JTextField)MsViewControlSet[ColTBMs_EntryDate]).setText(GetEntryDate);
			((JTextField)MsViewControlSet[ColTBMs_UpdateDate]).setText(GetUpdateDate);
			((JTextField)MsViewControlSet[ColTBMs_EntryUser]).setText(GetEntryUser);
			((JTextField)MsViewControlSet[ColTBMs_UpdateUser]).setText(GetUpdateUser);
			
			TgtClWh = A00000_Main.ClWh;
			TgtClCd = A00000_Main.ClCd;
			SearchArrNo.add(GetArrNo);
			SearchArrCountMin.add(GetArrCount);
			SearchArrCountMax.add(GetArrCount);
		}
		
		int MsRowCount	=((DefaultTableModel)MsViewControlSet[ColMsFmTableModel]).getRowCount();
		for(int i=0;i<MsRowCount;i++) {
			((DefaultTableModel)MsViewControlSet[ColMsFmTableModel]).removeRow(0);
		}
		Object[][] ArrivalMsRt = ArrivalMsRt(
				TgtClWh,
				TgtClCd,
				SearchArrNo,
				SearchArrCountMin,
				SearchArrCountMax
				);
		for(int i=0;i<ArrivalMsRt.length;i++) {
			Object[] SetOb = new Object[ArrivalMsRt[i].length+1];
			SetOb[0] = false;
			for(int i01=0;i01<ArrivalMsRt[i].length;i01++) {
				SetOb[1+i01]	= ArrivalMsRt[i][i01];
			}
			((DefaultTableModel)MsViewControlSet[ColMsFmTableModel]).addRow(SetOb);
		}
		if(MsViewMode) {
			((JFrame)MsViewControlSet[ColMs_fm]).setVisible(true);
			
		}
	}
	
	private static String[][] ArrivalMsOutDataCreate(DefaultTableModel MainFmTableModel){
		String TgtClWh	=A00000_Main.ClWh;
		String TgtClCd	=A00000_Main.ClCd;
		ArrayList<String> SearchArrNo = new ArrayList<String>();
		ArrayList<Integer> SearchArrCountMin = new ArrayList<Integer>();
		ArrayList<Integer> SearchArrCountMax = new ArrayList<Integer>();
		
		int RowCount= MainFmTableModel.getRowCount();
		for(int i=0;i<RowCount;i++) {
			String TgtArrNo			= B100_TextControl.Trim(""+MainFmTableModel.getValueAt(i, 1+T100_ArrivalHdRt.ColArrNo));
			SearchArrNo.add(TgtArrNo);
		}
		
		Object[][] ArrivalMsRt	= ArrivalMsRt(
											TgtClWh,
											TgtClCd,
											SearchArrNo,
											SearchArrCountMin,
											SearchArrCountMax
											);
		
		int OutPutCount = 0;
		
		for(int i=0;i<RowCount;i++) {
			String CheckClWh			= B100_TextControl.Trim(""+MainFmTableModel.getValueAt(i, 1+T100_ArrivalHdRt.ColClWh));
			String CheckClCd			= B100_TextControl.Trim(""+MainFmTableModel.getValueAt(i, 1+T100_ArrivalHdRt.ColClCd));
			String CheckArrNo			= B100_TextControl.Trim(""+MainFmTableModel.getValueAt(i, 1+T100_ArrivalHdRt.ColArrNo));
			int CheckArrCount			= B100_TextControl.TextToInt(""+MainFmTableModel.getValueAt(i, 1+T100_ArrivalHdRt.ColArrCount));
			
			for(int i01=0;i01<ArrivalMsRt.length;i01++) {
				if(	CheckClWh.equals((String)ArrivalMsRt[i01][T100_ArrivalMsRt.ColClWh])
					&& CheckClCd.equals((String)ArrivalMsRt[i01][T100_ArrivalMsRt.ColClCd])
					&& CheckArrNo.equals((String)ArrivalMsRt[i01][T100_ArrivalMsRt.ColArrNo])
					&& CheckArrCount==(int)ArrivalMsRt[i01][T100_ArrivalMsRt.ColArrCount]
					) {
					OutPutCount	= OutPutCount+1;
				}
			}
		}
		
		Object[][] RtArrivalMsRt = T100_ArrivalMsRt.RtArrivalMsRt();
		String[][] Rt= new String[1+OutPutCount][RtArrivalMsRt.length];
		
		for(int i=0;i<RtArrivalMsRt.length;i++) {
			Rt[0][(int)RtArrivalMsRt[i][1]]	= (String)RtArrivalMsRt[i][3];
		}
		OutPutCount = 0;
		for(int i=0;i<RowCount;i++) {
			String CheckClWh			= B100_TextControl.Trim(""+MainFmTableModel.getValueAt(i, 1+T100_ArrivalHdRt.ColClWh));
			String CheckClCd			= B100_TextControl.Trim(""+MainFmTableModel.getValueAt(i, 1+T100_ArrivalHdRt.ColClCd));
			String CheckArrNo			= B100_TextControl.Trim(""+MainFmTableModel.getValueAt(i, 1+T100_ArrivalHdRt.ColArrNo));
			int CheckArrCount			= B100_TextControl.TextToInt(""+MainFmTableModel.getValueAt(i, 1+T100_ArrivalHdRt.ColArrCount));
			
			for(int i01=0;i01<ArrivalMsRt.length;i01++) {
				if(	CheckClWh.equals((String)ArrivalMsRt[i01][T100_ArrivalMsRt.ColClWh])
					&& CheckClCd.equals((String)ArrivalMsRt[i01][T100_ArrivalMsRt.ColClCd])
					&& CheckArrNo.equals((String)ArrivalMsRt[i01][T100_ArrivalMsRt.ColArrNo])
					&& CheckArrCount==(int)ArrivalMsRt[i01][T100_ArrivalMsRt.ColArrCount]
					) {
					OutPutCount	= OutPutCount+1;
					for(int i02=0;i02<RtArrivalMsRt.length;i02++) {
						Rt[OutPutCount][(int)RtArrivalMsRt[i02][1]]	= ""+ArrivalMsRt[i01][(int)RtArrivalMsRt[i02][1]];
					}
				}
			}
		}
		
		return Rt;
	}
	
	
	private static Object[][] ArrivalMsRt(
			String TgtClWh,
			String TgtClCd,
			ArrayList<String> SearchArrNo,
			ArrayList<Integer> SearchArrCountMin,
			ArrayList<Integer> SearchArrCountMax
			){
		if(null==TgtClWh) {TgtClWh="";}
		if(null==TgtClCd) {TgtClCd="";}
		
		if("".equals(TgtClWh)) {TgtClWh=A00000_Main.ClWh;}
		if("".equals(TgtClCd)) {TgtClCd=A00000_Main.ClCd;}
		
		ArrayList<String> SearchClWh			= new ArrayList<String>();		//担当倉庫
		ArrayList<String> SearchClCd			= new ArrayList<String>();		//荷主CD
		ArrayList<String> SearchClGpCD			= new ArrayList<String>();		//ヘッダ荷主グループCD
		//ArrayList<String> SearchArrNo			= new ArrayList<String>();		//入荷予定NO
		//ArrayList<Integer> SearchArrCountMin	= new ArrayList<Integer>();		//入荷予定枝番最小
		//ArrayList<Integer> SearchArrCountMax	= new ArrayList<Integer>();		//入荷予定枝番最大
		ArrayList<String> SearchClArrNo			= new ArrayList<String>();		//荷主予定番号
		ArrayList<String> SearchPlanDateMin		= new ArrayList<String>();		//入荷予定日最小
		ArrayList<String> SearchPlanDateMax		= new ArrayList<String>();		//入荷予定日最大
		ArrayList<String> SearchActualDateMin	= new ArrayList<String>();		//入荷実績日最小
		ArrayList<String> SearchActualDateMax	= new ArrayList<String>();		//入荷実績日最大
		ArrayList<String> SearchSpCd			= new ArrayList<String>();		//仕入先CD
		ArrayList<String> SearchCom				= new ArrayList<String>();		//コメント
		ArrayList<String> SearchEntryDateMin	= new ArrayList<String>();		//登録日最小
		ArrayList<String> SearchEntryDateMax	= new ArrayList<String>();		//登録日最大
		ArrayList<String> SearchUpdateDateMin	= new ArrayList<String>();		//更新日最小
		ArrayList<String> SearchUpdateDateMax	= new ArrayList<String>();		//更新日最大
		ArrayList<String> SearchEntryUser		= new ArrayList<String>();		//登録者
		ArrayList<String> SearchUpdateUser		= new ArrayList<String>();		//更新者
		
		//明細WW0013ArrivalMs由来
		ArrayList<String> SearchItemCd			= new ArrayList<String>();		//商品コード
		ArrayList<String> SearchClItemCd		= new ArrayList<String>();		//荷主商品コード
		ArrayList<String> SearchItemName		= new ArrayList<String>();		//商品名
		ArrayList<String> SearchLot				= new ArrayList<String>();		//ロット
		ArrayList<String> SearchExpDateMin		= new ArrayList<String>();		//消費期限最小
		ArrayList<String> SearchExpDateMax		= new ArrayList<String>();		//消費期限最大
		boolean AllSearch	= false;
		
		if(null!=SearchArrNo && 0<SearchArrNo.size()) {
			SearchClWh.add(TgtClWh);
			SearchClCd.add(TgtClCd);
		}
		
		Object[][] ArrivalMsRt	= T100_ArrivalMsRt.ArrivalMsRt(
				SearchClWh,				//担当倉庫
				SearchClCd,				//荷主CD
				SearchClGpCD,			//ヘッダ荷主グループCD
				SearchArrNo,			//入荷予定NO
				SearchArrCountMin,		//入荷予定枝番最小
				SearchArrCountMax,		//入荷予定枝番最大
				SearchClArrNo,			//荷主予定番号
				SearchPlanDateMin,		//入荷予定日最小
				SearchPlanDateMax,		//入荷予定日最大
				SearchActualDateMin,	//入荷実績日最小
				SearchActualDateMax,	//入荷実績日最大
				SearchSpCd,				//仕入先CD
				SearchCom,				//コメント
				SearchEntryDateMin,		//登録日最小
				SearchEntryDateMax,		//登録日最大
				SearchUpdateDateMin,	//更新日最小
				SearchUpdateDateMax,	//更新日最大
				SearchEntryUser,		//登録者
				SearchUpdateUser,		//更新者
				
				//明細WW0013ArrivalMs由来
				SearchItemCd,			//商品コード
				SearchClItemCd,			//荷主商品コード
				SearchItemName,			//商品名
				SearchLot,				//ロット
				SearchExpDateMin,		//消費期限最小
				SearchExpDateMax,		//消費期限最大
				AllSearch);
		
		return ArrivalMsRt;
	}
	
	private static Object[][] ArrivalHdRt(
			String GetSearchClWh,
			String GetSearchClCd,
			String GetSearchClGpCD,
			String GetSearchSpCd,
			String GetSearchArrNo,
			String GetSearchArrCountMin,
			
			String GetSearchArrCountMax,
			String GetSearchClArrNo,
			String GetSearchPlanDateMin,
			String GetSearchPlanDateMax,
			String GetSearchActualDateMin,
			String GetSearchActualDateMax,
			String GetSearchCom,
			
			
			String GetSearchItemCd,
			String GetSearchClItemCd,
			String GetSearchItemName,
			String GetSearchLot,
			String GetSearchExpDateMin,
			String GetSearchExpDateMax,
			
			
			String GetSearchEntryDateMin,
			String GetSearchEntryDateMax,
			String GetSearchUpdateDateMin,
			String GetSearchUpdateDateMax,
			String GetSearchEntryUser,
			String GetSearchUpdateUser,
			
			String GetArrNoAnyString
			){
		
		GetSearchClWh			= B100_TextControl.Trim(GetSearchClWh);
		GetSearchClCd			= B100_TextControl.Trim(GetSearchClCd);
		GetSearchClGpCD			= B100_TextControl.Trim(GetSearchClGpCD);
		GetSearchSpCd			= B100_TextControl.Trim(GetSearchSpCd);
		GetSearchArrNo			= B100_TextControl.Trim(GetSearchArrNo);
		GetSearchArrCountMin	= B100_TextControl.Trim(GetSearchArrCountMin);
		GetSearchArrCountMax	= B100_TextControl.Trim(GetSearchArrCountMax);
		GetSearchClArrNo		= B100_TextControl.Trim(GetSearchClArrNo);
		GetSearchPlanDateMin	= B100_TextControl.TextToDate(GetSearchPlanDateMin);
		GetSearchPlanDateMax	= B100_TextControl.TextToDate(GetSearchPlanDateMax);
		GetSearchActualDateMin	= B100_TextControl.TextToDate(GetSearchActualDateMin);
		GetSearchActualDateMax	= B100_TextControl.TextToDate(GetSearchActualDateMax);
		GetSearchCom			= B100_TextControl.Trim(GetSearchCom);
		
		GetSearchItemCd			= B100_TextControl.Trim(GetSearchItemCd);
		GetSearchClItemCd		= B100_TextControl.Trim(GetSearchClItemCd);
		GetSearchItemName		= B100_TextControl.Trim(GetSearchItemName);
		GetSearchLot			= B100_TextControl.Trim(GetSearchLot);
		GetSearchExpDateMin		= B100_TextControl.TextToDate(GetSearchExpDateMin);
		GetSearchExpDateMax		= B100_TextControl.TextToDate(GetSearchExpDateMax);
		
		GetSearchEntryDateMin	= B100_TextControl.Trim(GetSearchEntryDateMin);
		GetSearchEntryDateMax	= B100_TextControl.Trim(GetSearchEntryDateMax);
		GetSearchUpdateDateMin	= B100_TextControl.Trim(GetSearchUpdateDateMin);
		GetSearchUpdateDateMax	= B100_TextControl.Trim(GetSearchUpdateDateMax);
		GetSearchEntryUser		= B100_TextControl.Trim(GetSearchEntryUser);
		GetSearchUpdateUser		= B100_TextControl.Trim(GetSearchUpdateUser);
		
		if(!"".equals(GetSearchArrCountMin	)){GetSearchArrCountMin		= B100_TextControl.num_only_String(GetSearchArrCountMin);}
		if(!"".equals(GetSearchArrCountMax	)){GetSearchArrCountMax		= B100_TextControl.num_only_String(GetSearchArrCountMax);}
		
		//次に訪れた時の為に検索条件覚える
		DefaultSearchClWh				= GetSearchClWh;
		DefaultSearchClCd				= GetSearchClCd;
		DefaultSearchClGpCD			= GetSearchClGpCD;
		DefaultSearchSpCd				= GetSearchSpCd;
		DefaultSearchArrNo			= GetSearchArrNo;
		DefaultSearchArrCountMin	= GetSearchArrCountMin;
		DefaultSearchArrCountMax	= GetSearchArrCountMax;
		DefaultSearchClArrNo			= GetSearchClArrNo;
		DefaultSearchPlanDateMin	= GetSearchPlanDateMin;
		DefaultSearchPlanDateMax	= GetSearchPlanDateMax;
		DefaultSearchActualDateMin	= GetSearchActualDateMin;
		DefaultSearchActualDateMax	= GetSearchActualDateMax;
		DefaultSearchCom				= GetSearchCom;
		
		DefaultSearchItemCd			= GetSearchItemCd;
		DefaultSearchClItemCd		= GetSearchClItemCd;
		DefaultSearchItemName		= GetSearchItemName;
		DefaultSearchLot				= GetSearchLot;
		DefaultSearchExpDateMin		= GetSearchExpDateMin;
		DefaultSearchExpDateMax		= GetSearchExpDateMax;
		
		DefaultSearchEntryDateMin	= GetSearchEntryDateMin;
		DefaultSearchEntryDateMax	= GetSearchEntryDateMax;
		DefaultSearchUpdateDateMin	= GetSearchUpdateDateMin;
		DefaultSearchUpdateDateMax	= GetSearchUpdateDateMax;
		DefaultSearchEntryUser		= GetSearchEntryUser;
		DefaultSearchUpdateUser		= GetSearchUpdateUser;
		
		if(null==GetArrNoAnyString) {
			GetArrNoAnyString="";
		}else {
			DefaultSearchArrNoList = GetArrNoAnyString;
		}
		String[] GetArrNoAny	= GetArrNoAnyString.split("\n");
		for(int i=0;i<GetArrNoAny.length;i++) {
			GetArrNoAny[i]	= B100_TextControl.Trim(GetArrNoAny[i]);
			GetArrNoAny[i]	= B100_TextControl.num_only_String(GetArrNoAny[i]);
		}
		
		ArrayList<String> SearchClWh			= new ArrayList<String>();		//担当倉庫
		ArrayList<String> SearchClCd			= new ArrayList<String>();		//荷主CD
		ArrayList<String> SearchClGpCD			= new ArrayList<String>();		//ヘッダ荷主グループCD
		ArrayList<String> SearchArrNo			= new ArrayList<String>();		//入荷予定NO
		ArrayList<Integer> SearchArrCountMin	= new ArrayList<Integer>();		//入荷予定枝番最小
		ArrayList<Integer> SearchArrCountMax	= new ArrayList<Integer>();		//入荷予定枝番最大
		ArrayList<String> SearchClArrNo			= new ArrayList<String>();		//荷主予定番号
		ArrayList<String> SearchPlanDateMin		= new ArrayList<String>();		//入荷予定日最小
		ArrayList<String> SearchPlanDateMax		= new ArrayList<String>();		//入荷予定日最大
		ArrayList<String> SearchActualDateMin	= new ArrayList<String>();		//入荷実績日最小
		ArrayList<String> SearchActualDateMax	= new ArrayList<String>();		//入荷実績日最大
		ArrayList<String> SearchSpCd			= new ArrayList<String>();		//仕入先CD
		ArrayList<String> SearchCom				= new ArrayList<String>();		//コメント
		ArrayList<String> SearchEntryDateMin	= new ArrayList<String>();		//登録日最小
		ArrayList<String> SearchEntryDateMax	= new ArrayList<String>();		//登録日最大
		ArrayList<String> SearchUpdateDateMin	= new ArrayList<String>();		//更新日最小
		ArrayList<String> SearchUpdateDateMax	= new ArrayList<String>();		//更新日最大
		ArrayList<String> SearchEntryUser		= new ArrayList<String>();		//登録者
		ArrayList<String> SearchUpdateUser		= new ArrayList<String>();		//更新者
		
		//明細WW0013ArrivalMs由来
		ArrayList<String> SearchItemCd			= new ArrayList<String>();		//商品コード
		ArrayList<String> SearchClItemCd		= new ArrayList<String>();		//荷主商品コード
		ArrayList<String> SearchItemName		= new ArrayList<String>();		//商品名
		ArrayList<String> SearchLot				= new ArrayList<String>();		//ロット
		ArrayList<String> SearchExpDateMin		= new ArrayList<String>();		//消費期限最小
		ArrayList<String> SearchExpDateMax		= new ArrayList<String>();		//消費期限最大
		boolean AllSearch	= false;
		
		if(!"".equals(GetSearchClWh				)){SearchClWh.add(GetSearchClWh);}
		if(!"".equals(GetSearchClCd				)){SearchClCd.add(GetSearchClCd);}
		if(!"".equals(GetSearchClGpCD			)){SearchClGpCD.add(GetSearchClGpCD);}
		if(!"".equals(GetSearchSpCd				)){SearchSpCd.add(GetSearchSpCd);}
		if(!"".equals(GetSearchArrNo			)){SearchArrNo.add(GetSearchArrNo);}
		if(!"".equals(GetSearchArrCountMin		)){SearchArrCountMin.add(B100_TextControl.TextToInt(GetSearchArrCountMin));}
		if(!"".equals(GetSearchArrCountMax		)){SearchArrCountMax.add(B100_TextControl.TextToInt(GetSearchArrCountMax));}
		if(!"".equals(GetSearchClArrNo			)){SearchClArrNo.add(GetSearchClArrNo);}
		if(!"".equals(GetSearchPlanDateMin		)){SearchPlanDateMin.add(GetSearchPlanDateMin);}
		if(!"".equals(GetSearchPlanDateMax		)){SearchPlanDateMax.add(GetSearchPlanDateMax);}
		if(!"".equals(GetSearchActualDateMin	)){SearchActualDateMin.add(GetSearchActualDateMin);}
		if(!"".equals(GetSearchActualDateMax	)){SearchActualDateMax.add(GetSearchActualDateMax);}
		if(!"".equals(GetSearchCom				)){SearchCom.add(GetSearchCom);}
		
		if(!"".equals(GetSearchItemCd			)){SearchItemCd.add(GetSearchItemCd);}
		if(!"".equals(GetSearchClItemCd			)){SearchClItemCd.add(GetSearchClItemCd);}
		if(!"".equals(GetSearchItemName			)){SearchItemName.add(GetSearchItemName);}
		if(!"".equals(GetSearchLot				)){SearchLot.add(GetSearchLot);}
		if(!"".equals(GetSearchExpDateMin		)){SearchExpDateMin.add(GetSearchExpDateMin);}
		if(!"".equals(GetSearchExpDateMax		)){SearchExpDateMax.add(GetSearchExpDateMax);}
		
		if(!"".equals(GetSearchEntryDateMin		)){SearchEntryDateMin.add(GetSearchEntryDateMin);}
		if(!"".equals(GetSearchEntryDateMax		)){SearchEntryDateMax.add(GetSearchEntryDateMax);}
		if(!"".equals(GetSearchUpdateDateMin	)){SearchUpdateDateMin.add(GetSearchUpdateDateMin);}
		if(!"".equals(GetSearchUpdateDateMax	)){SearchUpdateDateMax.add(GetSearchUpdateDateMax);}
		if(!"".equals(GetSearchEntryUser		)){SearchEntryUser.add(GetSearchEntryUser);}
		if(!"".equals(GetSearchUpdateUser		)){SearchUpdateUser.add(GetSearchUpdateUser);}
		for(int i=0;i<GetArrNoAny.length;i++) {
			if(!"".equals(GetArrNoAny[i]		)){SearchArrNo.add(GetArrNoAny[i]);}
		}
		
		
		Object[][] ArrivalHdRt	= T100_ArrivalHdRt.ArrivalHdRt(
				SearchClWh,				//担当倉庫
				SearchClCd,				//荷主CD
				SearchClGpCD,			//ヘッダ荷主グループCD
				SearchArrNo,			//入荷予定NO
				SearchArrCountMin,		//入荷予定枝番最小
				SearchArrCountMax,		//入荷予定枝番最大
				SearchClArrNo,			//荷主予定番号
				SearchPlanDateMin,		//入荷予定日最小
				SearchPlanDateMax,		//入荷予定日最大
				SearchActualDateMin,	//入荷実績日最小
				SearchActualDateMax,	//入荷実績日最大
				SearchSpCd,				//仕入先CD
				SearchCom,				//コメント
				SearchEntryDateMin,		//登録日最小
				SearchEntryDateMax,		//登録日最大
				SearchUpdateDateMin,	//更新日最小
				SearchUpdateDateMax,	//更新日最大
				SearchEntryUser,		//登録者
				SearchUpdateUser,		//更新者
				
				//明細WW0013ArrivalMs由来
				SearchItemCd,			//商品コード
				SearchClItemCd,			//荷主商品コード
				SearchItemName,			//商品名
				SearchLot,				//ロット
				SearchExpDateMin,		//消費期限最小
				SearchExpDateMax,		//消費期限最大
				AllSearch);
		
		return ArrivalHdRt;
	}
	
}