import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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

public class WT100_ArrivalPlan_00_Search{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	
	static String DefaultSearchClWh;
	static String DefaultSearchClCd;
	static String DefaultSearchClGpCD;
	static String DefaultSearchSpCd;
	static String DefaultSearchArrNo;
	static String DefaultSearchClArrNo;
	static String DefaultSearchPlanDateMin;
	static String DefaultSearchPlanDateMax;
	static String DefaultSearchHdActualDateMin;
	static String DefaultSearchHdActualDateMax;
	static String DefaultSearchArCom;
	
	static String DefaultSearchItemCd;
	static String DefaultSearchClItemCd;
	static String DefaultSearchJanCd;
	static String DefaultSearchItemMdNo;
	static String DefaultSearchItemName;
	static String DefaultSearchlot;
	static String DefaultSearchExpDateMin;
	static String DefaultSearchExpDateMax;
	static String DefaultSearchActualDateMin;
	static String DefaultSearchActualDateMax;
	static String DefaultSearchCom;
	static String DefaultSearchEntryUser;
	static String DefaultSearchUpdateUser;
	
	static String DefaultSearchPlanQtyMin;
	static String DefaultSearchPlanQtyMax;
	static String DefaultSearchActualQtyMin;
	static String DefaultSearchActualQtyMax;
	static String DefaultSearchSpName;
	static String DefaultSearchSpPost;
	static String DefaultSearchSpAdd;
	static String DefaultSearchSpTel;
	static String DefaultSearchFixFg;
	static String DefaultSearchEntryDateMin;
	static String DefaultSearchEntryDateMax;
	static String DefaultSearchUpdateDateMin;
	static String DefaultSearchUpdateDateMax;
	static String DefaultSearchArrNoList;
	
	static final int MsViewColMsNo 			=  1;		//明細番号
	static final int MsViewColItemCd 			=  2;		//商品コード
	static final int MsViewColClItemCd 		=  3;		//荷主商品コード
	static final int MsViewColItemName 		=  4;		//商品名
	static final int MsViewColLot 			=  5;		//ロット
	static final int MsViewColExpDate 		=  6;		//消費期限
	static final int MsViewColPlanQty 		=  7;		//予定数量
	static final int MsViewColActualQty 		=  8;		//実績数
	static final int MsViewColActualDate 	=  9;		//入荷日
	static final int MsViewColCom01 			= 10;		//コメント1
	static final int MsViewColCom02 			= 11;		//コメント2
	static final int MsViewColJanCd 			= 12;		//JANCD
	static final int MsViewColItemMdNo 		= 13;		//商品型番
	static final int MsViewColEntryDate 		= 14;		//登録日
	static final int MsViewColUpdateDate 	= 15;		//更新日
	static final int MsViewColEntryUser 		= 16;		//登録者
	static final int MsViewColUpdateUser 	= 17;		//更新者
	
	static boolean MsViewMode;
	
	public static void ArrivalPlanSearch(int x,int y) {
		A00000_Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		MsViewMode = false;
		final JFrame main_fm = B100_FrameParts.FrameCreate(x,y,1200,800,"Corgi00入荷予定検索","NK");
		JLabel userinfo = B100_FrameParts.UserInfo();
		JButton exit_btn = B100_FrameParts.ExitBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		
		//検索条件パネル
		JPanel PN_Search = B100_FrameParts.JPanelSet(10,40,1160,300,"White");
		JLabel PN_SearchLabel = B100_FrameParts.JLabelSet(0,0,100,20,"検索条件",11,0);
		
		//検索条件
		JLabel LB_SearchClWh			= B100_FrameParts.JLabelSet(  0, 25,100,20,"担当倉庫:"				,11,1);
		JLabel LB_SearchClCd			= B100_FrameParts.JLabelSet(  0, 50,100,20,"荷主:"					,11,1);
		JLabel LB_SearchClGpCD			= B100_FrameParts.JLabelSet(  0, 75,100,20,"荷主グループ:"			,11,1);
		JLabel LB_SearchSpCd			= B100_FrameParts.JLabelSet(  0,100,100,20,"仕入先:"				,11,1);
		JLabel LB_SearchArrNo			= B100_FrameParts.JLabelSet(  0,125,100,20,"入荷予定NO:"			,11,1);
		JLabel LB_SearchClArrNo			= B100_FrameParts.JLabelSet(  0,150,100,20,"荷主予定番号:"			,11,1);
		JLabel LB_SearchPlanDate		= B100_FrameParts.JLabelSet(  0,175,100,20,"入荷予定日:"			,11,1);
		JLabel LB_SearchHdActualDate	= B100_FrameParts.JLabelSet(  0,200,100,20,"入荷実績日:"			,11,1);
		JLabel LB_SearchArCom			= B100_FrameParts.JLabelSet(  0,225,100,20,"ヘッダコメント:"		,10,1);
		
		JLabel LB_SearchItemCd			= B100_FrameParts.JLabelSet(340, 25,100,20,"商品コード:"			,11,1);
		JLabel LB_SearchClItemCd		= B100_FrameParts.JLabelSet(340, 50,100,20,"荷主商品コード:"		,10,1);
		JLabel LB_SearchJanCd			= B100_FrameParts.JLabelSet(340, 75,100,20,"JANCD（バラ）:"		,10,1);
		JLabel LB_SearchItemMdNo		= B100_FrameParts.JLabelSet(340,100,100,20,"商品型番:"				,11,1);
		JLabel LB_SearchItemName		= B100_FrameParts.JLabelSet(340,125,100,20,"商品名:"				,11,1);
		JLabel LB_Searchlot				= B100_FrameParts.JLabelSet(340,150,100,20,"ロット:"				,11,1);
		JLabel LB_SearchExpDate			= B100_FrameParts.JLabelSet(340,175,100,20,"消費期限:"				,11,1);
		JLabel LB_SearchActualDate		= B100_FrameParts.JLabelSet(340,200,100,20,"明細入荷日:"			,10,1);
		JLabel LB_SearchCom				= B100_FrameParts.JLabelSet(340,225,100,20,"明細コメント:"			,10,1);
		JLabel LB_SearchEntryUser		= B100_FrameParts.JLabelSet(340,250,100,20,"登録者:"				,11,1);
		JLabel LB_SearchUpdateUser		= B100_FrameParts.JLabelSet(340,275,100,20,"更新者:"				,11,1);
		
		JLabel LB_SearchPlanQty			= B100_FrameParts.JLabelSet(680, 25,100,20,"明細予定数:"			,11,1);
		JLabel LB_SearchActualQty		= B100_FrameParts.JLabelSet(680, 50,100,20,"明細実績数:"			,11,1);
		JLabel LB_SearchSpName			= B100_FrameParts.JLabelSet(680,100,100,20,"仕入先名:"				,11,1);
		JLabel LB_SearchSpPost			= B100_FrameParts.JLabelSet(680,125,100,20,"仕入先郵便:"			,11,1);
		JLabel LB_SearchSpAdd			= B100_FrameParts.JLabelSet(680,150,100,20,"仕入先住所:"			,11,1);
		JLabel LB_SearchSpTel			= B100_FrameParts.JLabelSet(680,175,100,20,"仕入先電話:"			,11,1);
		JLabel LB_SearchFixFg			= B100_FrameParts.JLabelSet(680,200,100,20,"状況:"					,11,1);
		JLabel LB_DateCom				= B100_FrameParts.JLabelSet(680,225,200,20,"↓YYYY/MM/DD HH:MM:SS"	,11,1);
		JLabel LB_SearchEntryDate		= B100_FrameParts.JLabelSet(580,250,100,20,"登録日:"				,11,1);
		JLabel LB_SearchUpdateDate		= B100_FrameParts.JLabelSet(580,275,100,20,"更新日:"				,11,1);
		
		final JComboBox TB_SearchClWh						= B100_FrameParts.JComboBoxSet(				100, 25,240,20,B100_DefaultVariable.SearchWhList[0],11);				//ヘッダ担当倉庫
		final JComboBox TB_SearchClCd						= B100_FrameParts.JComboBoxSet(				100, 50,240,20,B100_DefaultVariable.SearchClList[0],11);				//ヘッダ荷主CD
		final JComboBox TB_SearchClGpCD						= B100_FrameParts.JComboBoxSet(				100, 75,240,20,B100_DefaultVariable.SearchClGpList[0],11);			//ヘッダ荷主グループCD
		final JComboBox TB_SearchSpCd						= B100_FrameParts.JComboBoxSet(				100,100,240,20,B100_DefaultVariable.SearchSupplierList[0],11);		//ヘッダ仕入先　
		final JTextField TB_SearchArrNo						= B100_FrameParts.JTextFieldSet(				100,125,100,20,""	,11,0);						//ヘッダ入荷予定NO
		final JTextField TB_SearchClArrNo					= B100_FrameParts.JTextFieldSet(				100,150,100,20,""	,11,0);						//ヘッダ荷主予定番号
		final JFormattedTextField TB_SearchPlanDateMin		= B100_FrameParts.JFormattedTextFieldSet(	100,175, 70,20,""	,11,0,"YYYY/MM/DD");		//ヘッダ入荷予定日
		final JFormattedTextField TB_SearchPlanDateMax		= B100_FrameParts.JFormattedTextFieldSet(	230,175, 70,20,""	,11,0,"YYYY/MM/DD");		//ヘッダ入荷予定日
		final JFormattedTextField TB_SearchHdActualDateMin	= B100_FrameParts.JFormattedTextFieldSet(	100,200, 70,20,""	,11,0,"YYYY/MM/DD");		//ヘッダ入荷実績日
		final JFormattedTextField TB_SearchHdActualDateMax	= B100_FrameParts.JFormattedTextFieldSet(	230,200, 70,20,""	,11,0,"YYYY/MM/DD");		//ヘッダ入荷実績日
		final JTextField TB_SearchArCom						= B100_FrameParts.JTextFieldSet(				100,225,100,20,""	,11,0);						//ヘッダコメント
		
		//現在ログイン中の荷主情報選択済みにする
		TB_SearchClWh.setSelectedIndex(		B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.SearchWhList[1]	,A00000_Main.ClWh,true));			//ヘッダ担当倉庫
		TB_SearchClCd.setSelectedIndex(		B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.SearchClList[1]	,A00000_Main.ClCd,true));			//ヘッダ荷主CD
		TB_SearchClGpCD.setSelectedIndex(	B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.SearchClGpList[1]	,A00000_Main.ClGp,true));			//ヘッダ荷主グループCD
		
		
		final JTextField TB_SearchItemCd					= B100_FrameParts.JTextFieldSet(				440, 25,100,20,"",11,0);	//商品コード
		final JTextField TB_SearchClItemCd					= B100_FrameParts.JTextFieldSet(				440, 50,100,20,"",11,0);	//荷主商品コード
		final JTextField TB_SearchJanCd						= B100_FrameParts.JTextFieldSet(				440, 75,100,20,"",11,0);	//JANCD（バラ）
		final JTextField TB_SearchItemMdNo					= B100_FrameParts.JTextFieldSet(				440,100,100,20,"",11,0);	//商品型番
		final JTextField TB_SearchItemName					= B100_FrameParts.JTextFieldSet(				440,125,100,20,"",11,0);	//商品名
		final JTextField TB_Searchlot						= B100_FrameParts.JTextFieldSet(				440,150,100,20,"",11,0);	//ロット
		final JFormattedTextField TB_SearchExpDateMin		= B100_FrameParts.JFormattedTextFieldSet(	440,175, 70,20,"",11,0,"YYYY/MM/DD");		//消費期限最小
		final JFormattedTextField TB_SearchExpDateMax		= B100_FrameParts.JFormattedTextFieldSet(	570,175, 70,20,"",11,0,"YYYY/MM/DD");		//消費期限最大
		final JFormattedTextField TB_SearchActualDateMin	= B100_FrameParts.JFormattedTextFieldSet(	440,200, 70,20,"",11,0,"YYYY/MM/DD");		//明細入荷日最小
		final JFormattedTextField TB_SearchActualDateMax	= B100_FrameParts.JFormattedTextFieldSet(	570,200, 70,20,"",11,0,"YYYY/MM/DD");		//明細入荷日最大
		final JTextField TB_SearchCom						= B100_FrameParts.JTextFieldSet(				440,225,100,20,"",11,0);	//明細コメント
		final JTextField TB_SearchEntryUser					= B100_FrameParts.JTextFieldSet(				440,250,100,20,"",11,0);	//登録者
		final JTextField TB_SearchUpdateUser				= B100_FrameParts.JTextFieldSet(				440,275,100,20,"",11,0);	//更新者
		
		final JFormattedTextField TB_SearchPlanQtyMin		= B100_FrameParts.JFormattedTextFieldSet(	780, 25, 70,20,"",11,1,"####");		//明細予定数最小
		final JFormattedTextField TB_SearchPlanQtyMax		= B100_FrameParts.JFormattedTextFieldSet(	870, 26, 70,20,"",11,1,"####");		//明細予定数最大
		final JFormattedTextField TB_SearchActualQtyMin		= B100_FrameParts.JFormattedTextFieldSet(	780, 50, 70,20,"",11,1,"####");		//明細実績数最小
		final JFormattedTextField TB_SearchActualQtyMax		= B100_FrameParts.JFormattedTextFieldSet(	870, 50, 70,20,"",11,1,"####");		//明細実績数最大
		final JTextField TB_SearchSpName					= B100_FrameParts.JTextFieldSet(				780,100,100,20,"",11,0);			//仕入先名
		final JTextField TB_SearchSpPost					= B100_FrameParts.JTextFieldSet(				780,125,100,20,"",11,0);			//仕入先郵便
		final JTextField TB_SearchSpAdd						= B100_FrameParts.JTextFieldSet(				780,150,100,20,"",11,0);			//仕入先住所
		final JTextField TB_SearchSpTel						= B100_FrameParts.JTextFieldSet(				780,175,100,20,"",11,0);			//仕入先電話
		final JComboBox TB_SearchFixFg						= B100_FrameParts.JComboBoxSet(				780,200,150,20,B100_DefaultVariable.SearchArryvalFixFgList[0],11);		//状況 
		final JFormattedTextField TB_SearchEntryDateMin		= B100_FrameParts.JFormattedTextFieldSet(	680,250,150,20,"",11,0,"YYYY/MM/DD HH:MM:SS");		//登録日
		final JFormattedTextField TB_SearchEntryDateMax		= B100_FrameParts.JFormattedTextFieldSet(	850,250,150,20,"",11,0,"YYYY/MM/DD HH:MM:SS");		//登録日
		final JFormattedTextField TB_SearchUpdateDateMin	= B100_FrameParts.JFormattedTextFieldSet(	680,275,150,20,"",11,0,"YYYY/MM/DD HH:MM:SS");		//更新日
		final JFormattedTextField TB_SearchUpdateDateMax	= B100_FrameParts.JFormattedTextFieldSet(	850,275,150,20,"",11,0,"YYYY/MM/DD HH:MM:SS");		//更新日
		
		//検索条件　状況　初期値を未入荷に設定
		TB_SearchFixFg.setSelectedIndex(B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.SearchArryvalFixFgList[1]	,"0",true));

		JLabel LB2_SearchArrNo			= B100_FrameParts.JLabelSet( 200,125, 40,20,"と一致"	,10,0);
		JLabel LB2_SearchClArrNo		= B100_FrameParts.JLabelSet( 200,150, 40,20,"と一致"	,10,0);
		JLabel LB2_SearchPlanDate		= B100_FrameParts.JLabelSet( 210,175, 20,20,"～"		,10,2);
		JLabel LB2_SearchHdActualDate	= B100_FrameParts.JLabelSet( 210,200, 20,20,"～"		,10,2);
		JLabel LB2_SearchArCom			= B100_FrameParts.JLabelSet( 200,225, 40,20,"を含む"	,10,0);
		
		JLabel LB2_SearchItemCd			= B100_FrameParts.JLabelSet(540, 25, 40,20,"と一致"	,10,0);
		JLabel LB2_SearchClItemCd		= B100_FrameParts.JLabelSet(540, 50, 40,20,"と一致"	,10,0);
		JLabel LB2_SearchJanCd			= B100_FrameParts.JLabelSet(540, 75, 40,20,"と一致"	,10,0);
		JLabel LB2_SearchItemMdNo		= B100_FrameParts.JLabelSet(540,100, 40,20,"と一致"	,10,0);
		JLabel LB2_SearchItemName		= B100_FrameParts.JLabelSet(540,125, 40,20,"を含む"	,10,0);
		JLabel LB2_Searchlot			= B100_FrameParts.JLabelSet(540,150, 40,20,"と一致"	,10,0);
		JLabel LB2_SearchExpDate		= B100_FrameParts.JLabelSet(550,175, 20,20,"～"		,10,2);
		JLabel LB2_SearchActualDate		= B100_FrameParts.JLabelSet(550,200, 20,20,"～"		,10,2);
		JLabel LB2_SearchCom			= B100_FrameParts.JLabelSet(540,225, 40,20,"を含む"	,10,0);	
		JLabel LB2_SearchEntryUser		= B100_FrameParts.JLabelSet(540,250, 40,20,"を含む"	,10,0);	
		JLabel LB2_SearchUpdateUser		= B100_FrameParts.JLabelSet(540,275, 40,20,"を含む"	,10,0);	
		
		
		JLabel LB2_SearchPlanQty		= B100_FrameParts.JLabelSet(850, 25, 20,20,"～"		,10,2);
		JLabel LB2_SearchActualQty		= B100_FrameParts.JLabelSet(850, 50, 20,20,"～"		,10,2);
		JLabel LB2_SearchSpName			= B100_FrameParts.JLabelSet(880,100, 40,20,"を含む"	,10,0);
		JLabel LB2_SearchSpPost			= B100_FrameParts.JLabelSet(880,125, 40,20,"で始る"	,10,0);
		JLabel LB2_SearchSpAdd			= B100_FrameParts.JLabelSet(880,150, 40,20,"を含む"	,10,0);
		JLabel LB2_SearchSpTel			= B100_FrameParts.JLabelSet(880,175, 40,20,"を含む"	,10,0);
		JLabel LB2_SearchEntryDate		= B100_FrameParts.JLabelSet(830,250, 20,20,"～"		,10,2);
		JLabel LB2_SearchUpdateDate		= B100_FrameParts.JLabelSet(830,275, 20,20,"～"		,10,2);
		
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
			if(null!=DefaultSearchClArrNo			){TB_SearchClArrNo.setText(DefaultSearchClArrNo);}
			if(null!=DefaultSearchPlanDateMin		){TB_SearchPlanDateMin.setText(DefaultSearchPlanDateMin);}
			if(null!=DefaultSearchPlanDateMax		){TB_SearchPlanDateMax.setText(DefaultSearchPlanDateMax);}
			if(null!=DefaultSearchHdActualDateMin	){TB_SearchHdActualDateMin.setText(DefaultSearchHdActualDateMin);}
			if(null!=DefaultSearchHdActualDateMax	){TB_SearchHdActualDateMax.setText(DefaultSearchHdActualDateMax);}
			if(null!=DefaultSearchArCom				){TB_SearchArCom.setText(DefaultSearchArCom);}
			
			if(null!=DefaultSearchItemCd				){TB_SearchItemCd.setText(DefaultSearchItemCd);}
			if(null!=DefaultSearchClItemCd			){TB_SearchClItemCd.setText(DefaultSearchClItemCd);}
			if(null!=DefaultSearchJanCd				){TB_SearchJanCd.setText(DefaultSearchJanCd);}
			if(null!=DefaultSearchItemMdNo			){TB_SearchItemMdNo.setText(DefaultSearchItemMdNo);}
			if(null!=DefaultSearchItemName			){TB_SearchItemName.setText(DefaultSearchItemName);}
			if(null!=DefaultSearchlot					){TB_Searchlot.setText(DefaultSearchlot);}
			if(null!=DefaultSearchExpDateMin			){TB_SearchExpDateMin.setText(DefaultSearchExpDateMin);}
			if(null!=DefaultSearchExpDateMax			){TB_SearchExpDateMax.setText(DefaultSearchExpDateMax);}
			if(null!=DefaultSearchActualDateMin		){TB_SearchActualDateMin.setText(DefaultSearchActualDateMin);}
			if(null!=DefaultSearchActualDateMax		){TB_SearchActualDateMax.setText(DefaultSearchActualDateMax);}
			if(null!=DefaultSearchCom					){TB_SearchCom.setText(DefaultSearchCom);}
			if(null!=DefaultSearchEntryUser			){TB_SearchEntryUser.setText(DefaultSearchEntryUser);}
			if(null!=DefaultSearchUpdateUser			){TB_SearchUpdateUser.setText(DefaultSearchUpdateUser);}
			
			if(null!=DefaultSearchPlanQtyMin			){TB_SearchPlanQtyMin.setText(DefaultSearchPlanQtyMin);}
			if(null!=DefaultSearchPlanQtyMax			){TB_SearchPlanQtyMax.setText(DefaultSearchPlanQtyMax);}
			if(null!=DefaultSearchActualQtyMin		){TB_SearchActualQtyMin.setText(DefaultSearchActualQtyMin);}
			if(null!=DefaultSearchActualQtyMax		){TB_SearchActualQtyMax.setText(DefaultSearchActualQtyMax);}
			if(null!=DefaultSearchSpName				){TB_SearchSpName.setText(DefaultSearchSpName);}
			if(null!=DefaultSearchSpPost				){TB_SearchSpPost.setText(DefaultSearchSpPost);}
			if(null!=DefaultSearchSpAdd				){TB_SearchSpAdd.setText(DefaultSearchSpAdd);}
			if(null!=DefaultSearchSpTel				){TB_SearchSpTel.setText(DefaultSearchSpTel);}
			if(null!=DefaultSearchFixFg				){TB_SearchFixFg.setSelectedIndex(	B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.SearchArryvalFixFgList[1]	,DefaultSearchFixFg,true));}
			if(null!=DefaultSearchEntryDateMin		){TB_SearchEntryDateMin.setText(DefaultSearchEntryDateMin);}
			if(null!=DefaultSearchEntryDateMax		){TB_SearchEntryDateMax.setText(DefaultSearchEntryDateMax);}
			if(null!=DefaultSearchUpdateDateMin		){TB_SearchUpdateDateMin.setText(DefaultSearchUpdateDateMin);}
			if(null!=DefaultSearchUpdateDateMax		){TB_SearchUpdateDateMax.setText(DefaultSearchUpdateDateMax);}
		}
		
		JLabel LB_ArrNoAny	= B100_FrameParts.JLabelSet(  950, 50,200,20,"↓入荷予定No複数指定",10,2);
		final JTextArea TB_ArrNoAny	= B100_FrameParts.JTextAreaSet(11);
		JScrollPane SPArrNoAny 	= B100_FrameParts.JScrollPaneSet(950,75,200,150,TB_ArrNoAny);
		PN_Search.add(LB_ArrNoAny);
		PN_Search.add(SPArrNoAny);
		
		if(null!=DefaultSearchArrNoList) {TB_ArrNoAny.setText(DefaultSearchArrNoList);}
		
		//予定日進む戻るボタン
		JButton SearchPlanDateMinAfterBtn	= B100_FrameParts.BtnSet(170,175, 40,10,"▲",6);
		JButton SearchPlanDateMinBeforeBtn	= B100_FrameParts.BtnSet(170,185, 40,10,"▼",6);
		JButton SearchPlanDateMaxAfterBtn	= B100_FrameParts.BtnSet(300,175, 40,10,"▲",6);
		JButton SearchPlanDateMaxBeforeBtn	= B100_FrameParts.BtnSet(300,185, 40,10,"▼",6);
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
		JButton SearchHdActualDateMinAfterBtn		= B100_FrameParts.BtnSet(170,200, 40,10,"▲",6);
		JButton SearchHdActualDateMinBeforeBtn	= B100_FrameParts.BtnSet(170,210, 40,10,"▼",6);
		JButton SearchHdActualDateMaxAfterBtn		= B100_FrameParts.BtnSet(300,200, 40,10,"▲",6);
		JButton SearchHdActualDateMaxBeforeBtn	= B100_FrameParts.BtnSet(300,210, 40,10,"▼",6);
		PN_Search.add(SearchHdActualDateMinAfterBtn);
		PN_Search.add(SearchHdActualDateMinBeforeBtn);
		PN_Search.add(SearchHdActualDateMaxAfterBtn);
		PN_Search.add(SearchHdActualDateMaxBeforeBtn);
		//実績日進む戻るボタン押下事の挙動
		SearchHdActualDateMinAfterBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				B100_DateTimeControl.AfterDateSet(TB_SearchHdActualDateMin);
			}
		});
		SearchHdActualDateMinBeforeBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				B100_DateTimeControl.BeforeDateSet(TB_SearchHdActualDateMin);
			}
		});
		SearchHdActualDateMaxAfterBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				B100_DateTimeControl.AfterDateSet(TB_SearchHdActualDateMax);
			}
		});
		SearchHdActualDateMaxBeforeBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				B100_DateTimeControl.BeforeDateSet(TB_SearchHdActualDateMax);
			}
		});
		
		//賞味期限日進む戻るボタン
		JButton SearchExpDateMinAfterBtn	= B100_FrameParts.BtnSet(510,175, 40,10,"▲",6);
		JButton SearchExpDateMinBeforeBtn	= B100_FrameParts.BtnSet(510,185, 40,10,"▼",6);
		JButton SearchExpDateMaxAfterBtn	= B100_FrameParts.BtnSet(640,175, 40,10,"▲",6);
		JButton SearchExpDateMaxBeforeBtn	= B100_FrameParts.BtnSet(640,185, 40,10,"▼",6);
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
		
		//明細実績日進む戻るボタン
		JButton SearchActualDateMinAfterBtn		= B100_FrameParts.BtnSet(510,200, 40,10,"▲",6);
		JButton SearchActualDateMinBeforeBtn	= B100_FrameParts.BtnSet(510,210, 40,10,"▼",6);
		JButton SearchActualDateMaxAfterBtn		= B100_FrameParts.BtnSet(640,200, 40,10,"▲",6);
		JButton SearchActualDateMaxBeforeBtn	= B100_FrameParts.BtnSet(640,210, 40,10,"▼",6);
		PN_Search.add(SearchActualDateMinAfterBtn);
		PN_Search.add(SearchActualDateMinBeforeBtn);
		PN_Search.add(SearchActualDateMaxAfterBtn);
		PN_Search.add(SearchActualDateMaxBeforeBtn);
		//明細実績日進む戻るボタン押下事の挙動
		SearchActualDateMinAfterBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				B100_DateTimeControl.AfterDateSet(TB_SearchActualDateMin);
			}
		});
		SearchActualDateMinBeforeBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				B100_DateTimeControl.BeforeDateSet(TB_SearchActualDateMin);
			}
		});
		SearchActualDateMaxAfterBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				B100_DateTimeControl.AfterDateSet(TB_SearchActualDateMax);
			}
		});
		SearchActualDateMaxBeforeBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				B100_DateTimeControl.BeforeDateSet(TB_SearchActualDateMax);
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
		JButton SearchBtn 		= B100_FrameParts.BtnSet(1050,275,100,20,"検索",11);
		PN_Search.add(SearchBtn);
		
		//条件クリアボタン
		JButton SearchCrearBtn 	= B100_FrameParts.BtnSet(1050,25,100,20,"条件クリア",10);
		PN_Search.add(SearchCrearBtn);
		
		
		
		Object[][] RtArrivalPlanHdRt = T100_ArrivalPlanHdRt.RtArrivalPlanHdRt();
		
		String[] columnNames01 = new String[RtArrivalPlanHdRt.length+1];
		
		columnNames01[0] = "Fg";
		for(int i=0;i<RtArrivalPlanHdRt.length;i++) {
			columnNames01[1+(int)RtArrivalPlanHdRt[i][1]] = ""+RtArrivalPlanHdRt[i][3];
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
		
		for(int i=0;i<RtArrivalPlanHdRt.length;i++) {
			if("int".equals((String)RtArrivalPlanHdRt[i][2])||"float".equals((String)RtArrivalPlanHdRt[i][2])) {
				column = columnModel01.getColumn(1+(int)RtArrivalPlanHdRt[i][1]);	column.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.rightCellRenderer());
			}else {
				column = columnModel01.getColumn(1+(int)RtArrivalPlanHdRt[i][1]);	column.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	column.setCellRenderer(B100_FrameParts.leftCellRenderer());
			}
		}
		//スクロール用設定
		JScrollPane scpn01 = B100_FrameParts.JScrollPaneSet(10,350,1160,275,tb01);
		main_fm.add(scpn01);
		
		PN_Search.add(PN_SearchLabel);
		main_fm.add(PN_Search);
		
		
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
		
		
		JLabel LB_RenewBtn  = B100_FrameParts.JLabelSet(		250,640,100,20,"チェック行を" 	,11,2);
		main_fm.add(LB_RenewBtn);
		//修正ボタン
		JButton RenewBtn = B100_FrameParts.BtnSet(				250,660,100,20,"予定修正"		,11);
		main_fm.add(RenewBtn);
		//明細表示ボタン
		JButton MsViewBtn = B100_FrameParts.BtnSet(			250,685,100,20,"詳細表示"		,11);
		main_fm.add(MsViewBtn);
		
		JLabel LB_Create  = B100_FrameParts.JLabelSet(			370,640,100,20,"新規予定作成" 	, 9,2);
		main_fm.add(LB_Create);
		//新規登録ボタン
		JButton CreateBtn = B100_FrameParts.BtnSet(			370,660,100,20,"新規登録"		,11);
		main_fm.add(CreateBtn);
		//一括新規ボタン
		JButton SomeCreateBtn = B100_FrameParts.BtnSet(		370,685,100,20,"予定一括新規"	,9);
		main_fm.add(SomeCreateBtn);
		
		JLabel LB_PlanEntryExcel  = B100_FrameParts.JLabelSet(			490,640,130,20,"予定一括追加修正" , 9,2);
		main_fm.add(LB_PlanEntryExcel);
		//Excel取込ボタン
		JButton PlanEntryExcelCreateBtn = B100_FrameParts.BtnSet(		490,660,130,20,"予定Excel出力"	,9);
		main_fm.add(PlanEntryExcelCreateBtn);
		//Excel取込ボタン
		JButton PlanEntryExcelEntryBtn = B100_FrameParts.BtnSet(		490,685,130,20,"予定Excel取込"	,9);
		main_fm.add(PlanEntryExcelEntryBtn);
		
		
		JLabel LB_EntryExcel  = B100_FrameParts.JLabelSet(				650,640,130,20,"実績登録用" 	,11,2);
		main_fm.add(LB_EntryExcel);
		//実績登録用エクセル出力ボタン
		JButton ActualEntryExcelCreateBtn = B100_FrameParts.BtnSet(	650,660,130,20,"実績登録用Excel出力",9);
		main_fm.add(ActualEntryExcelCreateBtn);
		//実績登録用エクセル出力ボタン
		JButton ActualEntryExcelEntryBtn = B100_FrameParts.BtnSet(		650,685,130,20,"実績登録用Excel取込",9);
		main_fm.add(ActualEntryExcelEntryBtn);
		
		final JCheckBox PlanListTgtAll = B100_FrameParts.JCheckBoxSet(850,640,220,20,"発行済分も対象にして",11);
		main_fm.add(PlanListTgtAll);
		//入荷予定票発行
		JButton PlanListBtn = B100_FrameParts.BtnSet(		850,660,100,20,"予定票発行",11);
		main_fm.add(PlanListBtn);
		
		//貼札発行
		JButton PlanPosterBtn = B100_FrameParts.BtnSet(	970,660,100,20,"貼札発行",11);
		main_fm.add(PlanPosterBtn);
		
		
		/***********************************************
		詳細表示用
		***********************************************/
		final JFrame Ms_fm = B100_FrameParts.FrameCreate(x+20,y+20,800,830,"Corgi00入荷予定検索","NK");
		JLabel Msuserinfo = B100_FrameParts.UserInfo();
		JButton Msexit_btn = B100_FrameParts.ExitBtn();
		
		Ms_fm.add(Msuserinfo);
		Ms_fm.add(Msexit_btn);
		
		JLabel LBMs_ClWh			= B100_FrameParts.JLabelSet(  0, 50,130,20,"担当倉庫:"			,11,1);
		JLabel LBMs_ClCd			= B100_FrameParts.JLabelSet(  0, 75,130,20,"荷主:"				,11,1);
		JLabel LBMs_ClGpCD			= B100_FrameParts.JLabelSet(  0,100,130,20,"荷主グループ:"		,11,1);
		JLabel LBMs_SpCd			= B100_FrameParts.JLabelSet(  0,125,130,20,"仕入先:"			,11,1);
		JLabel LBMs_ArrNo			= B100_FrameParts.JLabelSet(  0,150,130,20,"入荷予定NO:"		,11,1);
		JLabel LBMs_ClArrNo			= B100_FrameParts.JLabelSet(  0,175,130,20,"荷主予定番号:"		,11,1);
		JLabel LBMs_PlanDate		= B100_FrameParts.JLabelSet(  0,200,130,20,"入荷予定日:"		,11,1);
		JLabel LBMs_HdActualDate	= B100_FrameParts.JLabelSet(  0,225,130,20,"入荷実績日:"		,11,1);

		JLabel LBMs_HdEntryDate		= B100_FrameParts.JLabelSet(370, 50,100,20,"登録日:"			,11,1);
		JLabel LBMs_HdUpdateDate	= B100_FrameParts.JLabelSet(370, 75,100,20,"更新日:"			,11,1);
		JLabel LBMs_HdEntryUser		= B100_FrameParts.JLabelSet(370,100,100,20,"登録者:"			,11,1);
		JLabel LBMs_HdUpdateUser	= B100_FrameParts.JLabelSet(370,125,100,20,"更新者:"			,11,1);
		JLabel LBMs_FixFg			= B100_FrameParts.JLabelSet(370,150,100,20,"状況:"				,11,1);
		JLabel LBMs_ArCom01			= B100_FrameParts.JLabelSet(370,175,100,20,"コメント1:"		,11,1);
		JLabel LBMs_ArCom02			= B100_FrameParts.JLabelSet(370,200,100,20,"コメント2:"		,11,1);
		JLabel LBMs_ArCom03			= B100_FrameParts.JLabelSet(370,225,100,20,"コメント3:"		,11,1);
		
		final JComboBox TBMs_ClWh					= B100_FrameParts.JComboBoxSet(				130, 50,240,20,B100_DefaultVariable.WhList[0],11);						//ヘッダ担当倉庫
		final JComboBox TBMs_ClCd					= B100_FrameParts.JComboBoxSet(				130, 75,240,20,B100_DefaultVariable.ClList[0],11);						//ヘッダ荷主CD
		final JComboBox TBMs_ClGpCD					= B100_FrameParts.JComboBoxSet(				130,100,240,20,B100_DefaultVariable.ClGpList[0],11);					//ヘッダ荷主グループCD
		final JComboBox TBMs_SpCd					= B100_FrameParts.JComboBoxSet(				130,125,240,20,B100_DefaultVariable.SupplierList[0],11);				//ヘッダ仕入先
		final JTextField TBMs_ArrNo					= B100_FrameParts.JTextFieldSet(				130,150,100,20,"",11,0);												//入荷予定NO
		final JTextField TBMs_ClArrNo				= B100_FrameParts.JTextFieldSet(				130,175,100,20,"",11,0);												//荷主予定番号
		final JFormattedTextField TBMs_PlanDate		= B100_FrameParts.JFormattedTextFieldSet(	130,200, 70,20,"",11,0,"YYYY/MM/DD");									//入荷予定日
		final JFormattedTextField TBMs_HdActualDate	= B100_FrameParts.JFormattedTextFieldSet(	130,225, 70,20,"",11,0,"YYYY/MM/DD");									//入荷実績日

		final JTextField TBMs_HdEntryDate			= B100_FrameParts.JTextFieldSet(				470, 50,200,20,"",11,0);												//登録日
		final JTextField TBMs_HdUpdateDate			= B100_FrameParts.JTextFieldSet(				470, 75,200,20,"",11,0);												//更新日
		final JTextField TBMs_HdEntryUser			= B100_FrameParts.JTextFieldSet(				470,100,200,20,"",11,0);												//登録者
		final JTextField TBMs_HdUpdateUser			= B100_FrameParts.JTextFieldSet(				470,125,200,20,"",11,0);												//更新者
		final JComboBox TBMs_FixFg					= B100_FrameParts.JComboBoxSet(				470,150,150,20,B100_DefaultVariable.ArryvalFixFgList[0],11);	//状況
		final JTextField TBMs_ArCom01				= B100_FrameParts.JTextFieldSet(				470,175,240,20,"",11,0);												//コメント1
		final JTextField TBMs_ArCom02				= B100_FrameParts.JTextFieldSet(				470,200,240,20,"",11,0);												//コメント2
		final JTextField TBMs_ArCom03				= B100_FrameParts.JTextFieldSet(				470,225,240,20,"",11,0);												//コメント3
		
		//入荷予定日進む戻るボタン
		JButton TBMs_PlanDateAfterBtn	= B100_FrameParts.BtnSet(200,200, 40,10,"▲",6);
		JButton TBMs_PlanDateBeforeBtn	= B100_FrameParts.BtnSet(200,210, 40,10,"▼",6);
		//賞味期限日進む戻るボタン押下事の挙動
		TBMs_PlanDateAfterBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				B100_DateTimeControl.AfterDateSet(TBMs_PlanDate);
			}
		});
		TBMs_PlanDateBeforeBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				B100_DateTimeControl.BeforeDateSet(TBMs_PlanDate);
			}
		});
		
		
		TBMs_ClWh.setEnabled(false);
		TBMs_ClCd.setEnabled(false);
		TBMs_ClGpCD.setEnabled(false);
		TBMs_SpCd.setEnabled(false);
		TBMs_ArrNo.setEditable(false);
		TBMs_ClArrNo.setEditable(false);
		TBMs_HdActualDate.setEditable(false);

		TBMs_HdEntryDate.setEditable(false);
		TBMs_HdUpdateDate.setEditable(false);
		TBMs_HdEntryUser.setEditable(false);
		TBMs_HdUpdateUser.setEditable(false);
		TBMs_FixFg.setEnabled(false);
		
		
		Ms_fm.add(LBMs_ClWh);
		Ms_fm.add(LBMs_ClCd);
		Ms_fm.add(LBMs_ClGpCD);
		Ms_fm.add(LBMs_ArrNo);
		Ms_fm.add(LBMs_ClArrNo);
		Ms_fm.add(LBMs_PlanDate);
		Ms_fm.add(LBMs_HdActualDate);
		Ms_fm.add(LBMs_SpCd);
		Ms_fm.add(LBMs_ArCom01);
		Ms_fm.add(LBMs_ArCom02);
		Ms_fm.add(LBMs_ArCom03);
		
		Ms_fm.add(LBMs_HdEntryDate);
		Ms_fm.add(LBMs_HdUpdateDate);
		Ms_fm.add(LBMs_HdEntryUser);
		Ms_fm.add(LBMs_HdUpdateUser);
		Ms_fm.add(LBMs_FixFg);
		
		
		Ms_fm.add(TBMs_ClWh);
		Ms_fm.add(TBMs_ClCd);
		Ms_fm.add(TBMs_ClGpCD);
		Ms_fm.add(TBMs_SpCd);
		Ms_fm.add(TBMs_ArrNo);
		Ms_fm.add(TBMs_ClArrNo);
		Ms_fm.add(TBMs_PlanDate);
		Ms_fm.add(TBMs_HdActualDate);
		Ms_fm.add(TBMs_ArCom01);
		Ms_fm.add(TBMs_ArCom02);
		Ms_fm.add(TBMs_ArCom03);
		
		Ms_fm.add(TBMs_HdEntryDate);
		Ms_fm.add(TBMs_HdUpdateDate);
		Ms_fm.add(TBMs_HdEntryUser);
		Ms_fm.add(TBMs_HdUpdateUser);
		Ms_fm.add(TBMs_FixFg);
		
		String[] MscolumnNames = {
				 "Fg"
				,"明細番号"
				,"商品コード"
				,"荷主商品コード"
				,"商品名"
				,"ロット"
				,"消費期限"
				,"予定数量"
				,"実績数"
				,"入荷日"
				,"コメント1"
				,"コメント2"
				,"JANCD（バラ）"
				,"商品型番"
				,"登録日"
				,"更新日"
				,"登録者"
				,"更新者"
				};
		
		//編集可能カラムの指定
		B100_TableControl.RenewTgt = new int[1];
		B100_TableControl.RenewTgt[0] = 0;

		final DefaultTableModel MstableModel = new B100_TableControl.MyTableModel01(MscolumnNames,0);
		
		final JTable Mstb = new JTable(MstableModel);
		Mstb.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		Mstb.setRowHeight(20*A00000_Main.Mul/A00000_Main.Div);
		Mstb.setFont(new Font(A00000_Main.DefaultFont, Font.PLAIN, 12*A00000_Main.Mul/A00000_Main.Div));
		
		DefaultTableColumnModel MscolumnModel
		= (DefaultTableColumnModel)Mstb.getColumnModel();
		
		//列幅初期設定 表示位置設定
		TableColumn Mscolumn = null;
		
		Mscolumn = MscolumnModel.getColumn(0);						Mscolumn.setPreferredWidth( 30*A00000_Main.Mul/A00000_Main.Div);	//FG
		Mscolumn = MscolumnModel.getColumn(MsViewColMsNo);		Mscolumn.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	Mscolumn.setCellRenderer(B100_FrameParts.leftCellRenderer());		//明細番号
		Mscolumn = MscolumnModel.getColumn(MsViewColItemCd);		Mscolumn.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	Mscolumn.setCellRenderer(B100_FrameParts.leftCellRenderer());		//商品コード
		Mscolumn = MscolumnModel.getColumn(MsViewColClItemCd);	Mscolumn.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	Mscolumn.setCellRenderer(B100_FrameParts.leftCellRenderer());		//荷主商品コード
		Mscolumn = MscolumnModel.getColumn(MsViewColItemName);	Mscolumn.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	Mscolumn.setCellRenderer(B100_FrameParts.leftCellRenderer());		//商品名
		Mscolumn = MscolumnModel.getColumn(MsViewColLot);			Mscolumn.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	Mscolumn.setCellRenderer(B100_FrameParts.leftCellRenderer());		//ロット
		Mscolumn = MscolumnModel.getColumn(MsViewColExpDate);		Mscolumn.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	Mscolumn.setCellRenderer(B100_FrameParts.leftCellRenderer());		//消費期限
		Mscolumn = MscolumnModel.getColumn(MsViewColPlanQty);		Mscolumn.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	Mscolumn.setCellRenderer(B100_FrameParts.rightCellRenderer());	//予定数量
		Mscolumn = MscolumnModel.getColumn(MsViewColActualQty);	Mscolumn.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	Mscolumn.setCellRenderer(B100_FrameParts.rightCellRenderer());	//実績数
		Mscolumn = MscolumnModel.getColumn(MsViewColActualDate);	Mscolumn.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	Mscolumn.setCellRenderer(B100_FrameParts.leftCellRenderer());		//入荷日
		Mscolumn = MscolumnModel.getColumn(MsViewColCom01);		Mscolumn.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	Mscolumn.setCellRenderer(B100_FrameParts.leftCellRenderer());		//コメント1
		Mscolumn = MscolumnModel.getColumn(MsViewColCom02);		Mscolumn.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	Mscolumn.setCellRenderer(B100_FrameParts.leftCellRenderer());		//コメント2
		Mscolumn = MscolumnModel.getColumn(MsViewColJanCd);		Mscolumn.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	Mscolumn.setCellRenderer(B100_FrameParts.leftCellRenderer());		//JANCD（バラ）
		Mscolumn = MscolumnModel.getColumn(MsViewColItemMdNo);	Mscolumn.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	Mscolumn.setCellRenderer(B100_FrameParts.leftCellRenderer());		//商品型番
		Mscolumn = MscolumnModel.getColumn(MsViewColEntryDate);	Mscolumn.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	Mscolumn.setCellRenderer(B100_FrameParts.leftCellRenderer());		//登録日
		Mscolumn = MscolumnModel.getColumn(MsViewColUpdateDate);	Mscolumn.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	Mscolumn.setCellRenderer(B100_FrameParts.leftCellRenderer());		//更新日
		Mscolumn = MscolumnModel.getColumn(MsViewColEntryUser);	Mscolumn.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	Mscolumn.setCellRenderer(B100_FrameParts.leftCellRenderer());		//登録者
		Mscolumn = MscolumnModel.getColumn(MsViewColUpdateUser);	Mscolumn.setPreferredWidth( 90*A00000_Main.Mul/A00000_Main.Div);	Mscolumn.setCellRenderer(B100_FrameParts.leftCellRenderer());		//更新者
		
		//スクロール用設定
		JScrollPane Msscpn01 = B100_FrameParts.JScrollPaneSet(10,250,760,250,Mstb);
		Ms_fm.add(Msscpn01);
		
		
		//明細表示用
		JLabel LBMs_MsNo		= B100_FrameParts.JLabelSet(  0,525,130,20,"明細番号:"			,11,1);
		JLabel LBMs_ItemCd		= B100_FrameParts.JLabelSet(  0,550,130,20,"商品コード:"		,11,1);
		JLabel LBMs_ItemName	= B100_FrameParts.JLabelSet(  0,575,130,20,"商品名:"			,11,1);
		JLabel LBMs_lot			= B100_FrameParts.JLabelSet(  0,600,130,20,"ロット:"			,11,1);
		JLabel LBMs_ExpDate		= B100_FrameParts.JLabelSet(  0,625,130,20,"消費期限:"			,11,1);
		JLabel LBMs_PlanQty		= B100_FrameParts.JLabelSet(  0,650,130,20,"予定数量:"			,11,1);
		JLabel LBMs_ActualQty	= B100_FrameParts.JLabelSet(200,650, 60,20,"実績数:"			,11,1);
		JLabel LBMs_Com01		= B100_FrameParts.JLabelSet(  0,675,130,20,"コメント1:"		,11,1);
		JLabel LBMs_Com02		= B100_FrameParts.JLabelSet(  0,700,130,20,"コメント2:"		,11,1);
		
		
		JLabel LBMs_ClItemCd	= B100_FrameParts.JLabelSet(330,525,130,20,"荷主商品コード:"	,11,1);
		JLabel LBMs_ActualDate	= B100_FrameParts.JLabelSet(330,550,130,20,"入荷日:"			,11,1);
		JLabel LBMs_JanCd		= B100_FrameParts.JLabelSet(330,575,130,20,"JANCD:"			,11,1);
		JLabel LBMs_ItemMdNo	= B100_FrameParts.JLabelSet(330,600,130,20,"商品型番:"			,11,1);
		JLabel LBMs_EntryDate	= B100_FrameParts.JLabelSet(330,625,130,20,"登録日:"			,11,1);
		JLabel LBMs_UpdateDate	= B100_FrameParts.JLabelSet(330,650,130,20,"更新日:"			,11,1);
		JLabel LBMs_EntryUser	= B100_FrameParts.JLabelSet(330,675,130,20,"登録者:"			,11,1);
		JLabel LBMs_UpdateUser	= B100_FrameParts.JLabelSet(330,700,130,20,"更新者:"			,11,1);
		
		final JFormattedTextField TBMs_MsNo			= B100_FrameParts.JFormattedTextFieldSet(	130,525, 70,20,""	,11,1,"####");						//明細番号
		final JTextField TBMs_ItemCd				= B100_FrameParts.JTextFieldSet(				130,550,100,20,""	,11,0);								//商品コード
		final JTextField TBMs_ItemName				= B100_FrameParts.JTextFieldSet(				130,575,200,20,""	,11,0);								//商品名
		final JTextField TBMs_lot					= B100_FrameParts.JTextFieldSet(				130,600,100,20,""	,11,0);								//ロット
		final JFormattedTextField TBMs_ExpDate		= B100_FrameParts.JFormattedTextFieldSet(	130,625,100,20,""	,11,0,"YYYY/MM/DD");				//消費期限
		final JFormattedTextField TBMs_PlanQty		= B100_FrameParts.JFormattedTextFieldSet(	130,650, 70,20,""	,11,1,"####");						//予定数量
		final JFormattedTextField TBMs_ActualQty	= B100_FrameParts.JFormattedTextFieldSet(	260,650, 70,20,""	,11,1,"####");						//実績数
		final JTextField TBMs_Com01					= B100_FrameParts.JTextFieldSet(				130,675,200,20,""	,11,0);								//コメント1
		final JTextField TBMs_Com02					= B100_FrameParts.JTextFieldSet(				130,700,200,20,""	,11,0);								//コメント2
		
		final JTextField TBMs_ClItemCd				= B100_FrameParts.JTextFieldSet(				460,525,100,20,""	,11,0);								//荷主商品コード
		final JFormattedTextField TBMs_ActualDate	= B100_FrameParts.JFormattedTextFieldSet(	460,550,200,20,""	,11,0,"YYYY/MM/DD HH:MM:SS");		//入荷日
		final JTextField TBMs_JanCd					= B100_FrameParts.JTextFieldSet(				460,575,100,20,""	,11,0);								//JANCD（バラ）
		final JTextField TBMs_ItemMdNo				= B100_FrameParts.JTextFieldSet(				460,600,100,20,""	,11,0);								//商品型番
		final JFormattedTextField TBMs_EntryDate	= B100_FrameParts.JFormattedTextFieldSet(	460,625,200,20,""	,11,0,"YYYY/MM/DD HH:MM:SS");		//登録日
		final JFormattedTextField TBMs_UpdateDate	= B100_FrameParts.JFormattedTextFieldSet(	460,650,200,20,""	,11,0,"YYYY/MM/DD HH:MM:SS");		//更新日
		final JTextField TBMs_EntryUser				= B100_FrameParts.JTextFieldSet(				460,675,200,20,""	,11,0);								//登録者
		final JTextField TBMs_UpdateUser			= B100_FrameParts.JTextFieldSet(				460,700,200,20,""	,11,0);								//更新者
		
		//賞味期限進む戻るボタン
		JButton TBMs_ExpDateAfterBtn	= B100_FrameParts.BtnSet(230,625, 40,10,"▲",6);
		JButton TBMs_ExpDateBeforeBtn	= B100_FrameParts.BtnSet(230,635, 40,10,"▼",6);
		//賞味期限日進む戻るボタン押下事の挙動
		TBMs_ExpDateAfterBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				B100_DateTimeControl.AfterDateSet(TBMs_ExpDate);
			}
		});
		TBMs_ExpDateBeforeBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				B100_DateTimeControl.BeforeDateSet(TBMs_ExpDate);
			}
		});
		
		//修正仮反映ボタン
		JButton MsRenewBtn 				= B100_FrameParts.BtnSet(	230,725,100,20,"修正仮反映",11);
		
		//修正反映ボタン
		JButton MsRenewEntryBtn 		= B100_FrameParts.BtnSet(	640,725,100,20,"修正反映",11);
		
		//キャンセルボタン
		JButton CancelBtn 				= B100_FrameParts.BtnSet(	520,725,100,20,"予定キャンセル",9);
		
		//入荷予定票発行
		JButton MsPlanListBtn = B100_FrameParts.BtnSet(			400,725,100,20,"予定票発行",11);
		
		//貼札発行
		JButton MsPlanPosterBtn = B100_FrameParts.BtnSet(			400,750,100,20,"貼札発行",11);
		
		TBMs_MsNo.setEditable(false);
		TBMs_ItemCd.setEditable(false);
		TBMs_ItemName.setEditable(false);
		TBMs_lot.setEditable(false);
		TBMs_ExpDate.setEditable(false);
		TBMs_PlanQty.setEditable(false);
		TBMs_ActualQty.setEditable(false);
		TBMs_Com01.setEditable(false);
		TBMs_Com02.setEditable(false);
		
		TBMs_ClItemCd.setEditable(false);
		TBMs_ActualDate.setEditable(false);
		TBMs_JanCd.setEditable(false);
		TBMs_ItemMdNo.setEditable(false);
		TBMs_EntryDate.setEditable(false);
		TBMs_UpdateDate.setEditable(false);
		TBMs_EntryUser.setEditable(false);
		TBMs_UpdateUser.setEditable(false);
		
		TBMs_MsNo.setBackground(B100_FrameParts.SelectColer("NoEntry"));
		TBMs_ItemCd.setBackground(B100_FrameParts.SelectColer("NoEntry"));
		TBMs_ItemName.setBackground(B100_FrameParts.SelectColer("NoEntry"));
		TBMs_lot.setBackground(B100_FrameParts.SelectColer("NoEntry"));
		TBMs_ExpDate.setBackground(B100_FrameParts.SelectColer("NoEntry"));
		TBMs_PlanQty.setBackground(B100_FrameParts.SelectColer("NoEntry"));
		TBMs_ActualQty.setBackground(B100_FrameParts.SelectColer("NoEntry"));
		TBMs_Com01.setBackground(B100_FrameParts.SelectColer("NoEntry"));
		TBMs_Com02.setBackground(B100_FrameParts.SelectColer("NoEntry"));
		
		TBMs_ClItemCd.setBackground(B100_FrameParts.SelectColer("NoEntry"));
		TBMs_ActualDate.setBackground(B100_FrameParts.SelectColer("NoEntry"));
		TBMs_JanCd.setBackground(B100_FrameParts.SelectColer("NoEntry"));
		TBMs_ItemMdNo.setBackground(B100_FrameParts.SelectColer("NoEntry"));
		TBMs_EntryDate.setBackground(B100_FrameParts.SelectColer("NoEntry"));
		TBMs_UpdateDate.setBackground(B100_FrameParts.SelectColer("NoEntry"));
		TBMs_EntryUser.setBackground(B100_FrameParts.SelectColer("NoEntry"));
		TBMs_UpdateUser.setBackground(B100_FrameParts.SelectColer("NoEntry"));	
		
		Ms_fm.add(LBMs_MsNo);
		Ms_fm.add(LBMs_ItemCd);
		Ms_fm.add(LBMs_ClItemCd);
		Ms_fm.add(LBMs_ItemName);
		Ms_fm.add(LBMs_lot);
		Ms_fm.add(LBMs_ExpDate);
		
		Ms_fm.add(LBMs_PlanQty);
		Ms_fm.add(LBMs_ActualQty);
		Ms_fm.add(LBMs_ActualDate);
		Ms_fm.add(LBMs_Com01);
		Ms_fm.add(LBMs_Com02);
		Ms_fm.add(LBMs_JanCd);
		Ms_fm.add(LBMs_ItemMdNo);
		Ms_fm.add(LBMs_EntryDate);
		Ms_fm.add(LBMs_UpdateDate);
		Ms_fm.add(LBMs_EntryUser);
		Ms_fm.add(LBMs_UpdateUser);
		
		Ms_fm.add(TBMs_MsNo);
		Ms_fm.add(TBMs_ItemCd);
		Ms_fm.add(TBMs_ClItemCd);
		Ms_fm.add(TBMs_ItemName);
		Ms_fm.add(TBMs_lot);
		Ms_fm.add(TBMs_ExpDate);
		
		Ms_fm.add(TBMs_PlanQty);
		Ms_fm.add(TBMs_ActualQty);
		Ms_fm.add(TBMs_ActualDate);
		Ms_fm.add(TBMs_Com01);
		Ms_fm.add(TBMs_Com02);
		Ms_fm.add(TBMs_JanCd);
		Ms_fm.add(TBMs_ItemMdNo);
		Ms_fm.add(TBMs_EntryDate);
		Ms_fm.add(TBMs_UpdateDate);
		Ms_fm.add(TBMs_EntryUser);
		Ms_fm.add(TBMs_UpdateUser);
		
		
		RenewFg = true;
		main_fm.setVisible(true);
		
		//チェックボックス操作時の挙動
		MstableModel.addTableModelListener(new TableModelListener(){
			public void tableChanged(TableModelEvent e){
				if(RenewFg) {
					RenewFg = false;
					TBMs_MsNo.setText("");
					TBMs_ItemCd.setText("");
					TBMs_ItemName.setText("");
					TBMs_lot.setText("");
					TBMs_ExpDate.setText("");
					TBMs_PlanQty.setText("");
					TBMs_ActualQty.setText("");
					TBMs_Com01.setText("");
					TBMs_Com02.setText("");
					
					TBMs_ClItemCd.setText("");
					TBMs_ActualDate.setText("");
					TBMs_JanCd.setText("");
					TBMs_ItemMdNo.setText("");
					TBMs_EntryDate.setText("");
					TBMs_UpdateDate.setText("");
					TBMs_EntryUser.setText("");
					TBMs_UpdateUser.setText("");
					int GetTBMs_FixFg= B100_TextControl.TextToInt(B100_DefaultVariable.ArryvalFixFgList[1][TBMs_FixFg.getSelectedIndex()]);	//状況
					Ms_fm.remove(MsRenewBtn);
					
					int row_count = Mstb.getRowCount();
					Boolean setBL=Boolean.valueOf(false);
					for(int i=0;i<row_count;i++){
						if(i!=e.getFirstRow()){
							MstableModel.setValueAt(setBL, i, 0);
						}else {
							if((boolean)MstableModel.getValueAt(i, 0)) {
								TBMs_MsNo.setText(""+MstableModel.getValueAt(i,MsViewColMsNo));
								TBMs_ItemCd.setText(""+MstableModel.getValueAt(i,MsViewColItemCd));
								TBMs_ItemName.setText(""+MstableModel.getValueAt(i,MsViewColItemName));
								TBMs_lot.setText(""+MstableModel.getValueAt(i,MsViewColLot));
								TBMs_ExpDate.setText(""+MstableModel.getValueAt(i,MsViewColExpDate));
								TBMs_PlanQty.setText(""+MstableModel.getValueAt(i,MsViewColPlanQty));
								TBMs_ActualQty.setText(""+MstableModel.getValueAt(i,MsViewColActualQty));
								TBMs_Com01.setText(""+MstableModel.getValueAt(i,MsViewColCom01));
								TBMs_Com02.setText(""+MstableModel.getValueAt(i,MsViewColCom02));
								
								TBMs_ClItemCd.setText(""+MstableModel.getValueAt(i,MsViewColClItemCd));
								TBMs_ActualDate.setText(""+MstableModel.getValueAt(i,MsViewColActualDate));
								TBMs_JanCd.setText(""+MstableModel.getValueAt(i,MsViewColJanCd));
								TBMs_ItemMdNo.setText(""+MstableModel.getValueAt(i,MsViewColItemMdNo));
								TBMs_EntryDate.setText(""+MstableModel.getValueAt(i,MsViewColEntryDate));
								TBMs_UpdateDate.setText(""+MstableModel.getValueAt(i,MsViewColUpdateDate));
								TBMs_EntryUser.setText(""+MstableModel.getValueAt(i,MsViewColEntryUser));
								TBMs_UpdateUser.setText(""+MstableModel.getValueAt(i,MsViewColUpdateUser));
								if(0==GetTBMs_FixFg) {
									Ms_fm.add(MsRenewBtn);
								}
							}
						}
					}
					Ms_fm.setVisible(false);
					Ms_fm.setVisible(true);
					RenewFg = true;
				}
			}
		});
		
		//修正仮反映ボタン押下時の挙動
		MsRenewBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					
					int GetMsNo				=B100_TextControl.TextToInt(TBMs_MsNo.getText());			//明細番号
					String GetItemCd		=B100_TextControl.Trim(TBMs_ItemCd.getText());				//商品コード
					String GetItemName		=B100_TextControl.Trim(TBMs_ItemName.getText());			//商品名
					String Getlot			=B100_TextControl.Trim(TBMs_lot.getText());					//ロット
					String GetExpDate		=B100_TextControl.TextToDate(TBMs_ExpDate.getText());		//消費期限
					int GetPlanQty			=B100_TextControl.TextToInt(TBMs_PlanQty.getText());		//予定数量
					int GetActualQty		=B100_TextControl.TextToInt(TBMs_ActualQty.getText());		//実績数
					String GetCom01			=B100_TextControl.Trim(TBMs_Com01.getText());				//コメント1
					String GetCom02			=B100_TextControl.Trim(TBMs_Com02.getText());				//コメント2
					int SetMsNo = -1;
					
					//不正入力怒る
					boolean KickFg = true;
					String ErrMsg = "";
					if(0>GetPlanQty) {
						ErrMsg = ErrMsg+"マイナスの入荷予定とは何事ですか？";
						KickFg = false;
					}
					
					if(KickFg) {
						int RowCount = MstableModel.getRowCount();
						for(int i=0;i<RowCount;i++) {
							int CheckMsNo = B100_TextControl.TextToInt(""+MstableModel.getValueAt(i, MsViewColMsNo));
							if(GetMsNo==CheckMsNo) {
								MstableModel.setValueAt(Getlot,i,MsViewColLot);
								MstableModel.setValueAt(GetExpDate,i,MsViewColExpDate);
								MstableModel.setValueAt(""+GetPlanQty,i,MsViewColPlanQty);
								MstableModel.setValueAt(GetCom01,i,MsViewColCom01);
								MstableModel.setValueAt(GetCom02,i,MsViewColCom02);
								SetMsNo = i;
							}
						}
					}else {
						JOptionPane.showMessageDialog(null, ErrMsg);
					}
					RenewFg = true;
					if(0<=SetMsNo) {
						MstableModel.setValueAt(false,SetMsNo,0);
					}
				}
			}
		});
		//修正反映ボタン押下時の挙動
		MsRenewEntryBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					int GetMsNo				=B100_TextControl.TextToInt(TBMs_MsNo.getText());			//明細番号
					String GetItemCd		=B100_TextControl.Trim(TBMs_ItemCd.getText());				//商品コード
					boolean KickFg = false;
					if(0==GetMsNo && "".equals(GetItemCd)) {
						KickFg = true;
					}
					if(!KickFg) {
						JOptionPane.showMessageDialog(null, "修正中の仮反映を忘れていやしませんか？");
					}else {
						int option = JOptionPane.showConfirmDialog(null, "本当に修正反映しますか？", "実行確認", JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE);
					    if (option == JOptionPane.YES_OPTION){
					    }else if (option == JOptionPane.NO_OPTION){
					    	KickFg = false;
					    	JOptionPane.showMessageDialog(null, "中断しました");
					    }
					}
				    if(KickFg) {
				    	DataRenew(
								TBMs_ClWh,
								TBMs_ClCd,
								TBMs_ClGpCD,
								TBMs_SpCd,
								TBMs_ArrNo,
								TBMs_ClArrNo,
								TBMs_PlanDate,
								TBMs_HdActualDate,

								TBMs_HdEntryDate,
								TBMs_HdUpdateDate,
								TBMs_HdEntryUser,
								TBMs_HdUpdateUser,
								TBMs_FixFg,
								TBMs_ArCom01,
								TBMs_ArCom02,
								TBMs_ArCom03,
								
								MstableModel
								);
						//再検索
						int RowCount = MainFmTableModel.getRowCount();
						for(int i=0;i<RowCount;i++) {
							MainFmTableModel.removeRow(0);
						}
						String GetSearchClWh			= B100_DefaultVariable.SearchWhList[1][TB_SearchClWh.getSelectedIndex()];			//ヘッダ担当倉庫
						String GetSearchClCd			= B100_DefaultVariable.SearchClList[1][TB_SearchClCd.getSelectedIndex()];			//ヘッダ荷主CD
						String GetSearchClGpCD			= B100_DefaultVariable.SearchClGpList[1][TB_SearchClGpCD.getSelectedIndex()];		//ヘッダ荷主グループCD
						String GetSearchSpCd			= B100_DefaultVariable.SearchSupplierList[1][TB_SearchSpCd.getSelectedIndex()];		//ヘッダ仕入先　
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
						String GetSearchFixFg			= B100_DefaultVariable.SearchArryvalFixFgList[1][TB_SearchFixFg.getSelectedIndex()];	//状況 
						String GetSearchEntryDateMin	= TB_SearchEntryDateMin.getText();		//登録日
						String GetSearchEntryDateMax	= TB_SearchEntryDateMax.getText();		//登録日
						String GetSearchUpdateDateMin	= TB_SearchUpdateDateMin.getText();		//更新日
						String GetSearchUpdateDateMax	= TB_SearchUpdateDateMax.getText();		//更新日
						String GetArrNoAnyString		= TB_ArrNoAny.getText();
						
						Object[][] ArrivalPlanHdRt		= ArrivalPlanHdRt(
								GetSearchClWh,
								GetSearchClCd,
								GetSearchClGpCD,
								GetSearchSpCd,
								GetSearchArrNo,
								GetSearchClArrNo,
								GetSearchPlanDateMin,
								GetSearchPlanDateMax,
								GetSearchHdActualDateMin,
								GetSearchHdActualDateMax,
								GetSearchArCom,
								
								GetSearchItemCd,
								GetSearchClItemCd,
								GetSearchJanCd,
								GetSearchItemMdNo,
								GetSearchItemName,
								GetSearchlot,
								GetSearchExpDateMin,
								GetSearchExpDateMax,
								GetSearchActualDateMin,
								GetSearchActualDateMax,
								GetSearchCom,
								GetSearchEntryUser,
								GetSearchUpdateUser,
								
								GetSearchPlanQtyMin,
								GetSearchPlanQtyMax,
								GetSearchActualQtyMin,
								GetSearchActualQtyMax,
								GetSearchSpName,
								GetSearchSpPost,
								GetSearchSpAdd,
								GetSearchSpTel,
								GetSearchFixFg,
								GetSearchEntryDateMin,
								GetSearchEntryDateMax,
								GetSearchUpdateDateMin,
								GetSearchUpdateDateMax,
								GetArrNoAnyString
								);
						
						if(0==ArrivalPlanHdRt.length) {
							B100_TableControl.AddSortOFF(tb01,MainFmTableModel);
						}else {
							for(int i=0;i<ArrivalPlanHdRt.length;i++) {
								Object[] SetOb = new Object[ArrivalPlanHdRt[i].length+1];
								SetOb[0] = false;
								for(int i01=0;i01<ArrivalPlanHdRt[i].length;i01++) {
									SetOb[i01+1] = ""+ArrivalPlanHdRt[i][i01];
								}
								MainFmTableModel.addRow(SetOb);
							}
							B100_TableControl.AddSortON(tb01,MainFmTableModel);
						}
						
						Ms_fm.setVisible(false);
				    }
					RenewFg = true;
				}
			}
		});
		//キャンセルボタン押下時の挙動
		CancelBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				//対象伝票をキャンセルする
				if(RenewFg) {
					RenewFg = false;
					int option = JOptionPane.showConfirmDialog(null, "本当にキャンセルしますか？\n後戻りできませんよ？", "実行確認", JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE);
				    if (option == JOptionPane.YES_OPTION){
						String GetClWh		= B100_DefaultVariable.WhList[1][TBMs_ClWh.getSelectedIndex()];			//ヘッダ担当倉庫
						String GetClCd		= B100_DefaultVariable.ClList[1][TBMs_ClCd.getSelectedIndex()];			//ヘッダ荷主CD
						String GetArrNo		= TBMs_ArrNo.getText();														//入荷予定NO
						
						ArrayList<String> TgtArrNo = new ArrayList<String>();
						TgtArrNo.add(GetArrNo);
						
						Tools100_ArrivalPlanCancel.ArrivalPlanCancel(
								GetClWh,
								GetClCd,
								TgtArrNo
								); 
				    
						//再検索
						int RowCount = MainFmTableModel.getRowCount();
						for(int i=0;i<RowCount;i++) {
							MainFmTableModel.removeRow(0);
						}
						String GetSearchClWh			= B100_DefaultVariable.SearchWhList[1][TB_SearchClWh.getSelectedIndex()];			//ヘッダ担当倉庫
						String GetSearchClCd			= B100_DefaultVariable.SearchClList[1][TB_SearchClCd.getSelectedIndex()];			//ヘッダ荷主CD
						String GetSearchClGpCD			= B100_DefaultVariable.SearchClGpList[1][TB_SearchClGpCD.getSelectedIndex()];		//ヘッダ荷主グループCD
						String GetSearchSpCd			= B100_DefaultVariable.SearchSupplierList[1][TB_SearchSpCd.getSelectedIndex()];	//ヘッダ仕入先　
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
						String GetSearchFixFg			= B100_DefaultVariable.SearchArryvalFixFgList[1][TB_SearchFixFg.getSelectedIndex()];	//状況 
						String GetSearchEntryDateMin	= TB_SearchEntryDateMin.getText();		//登録日
						String GetSearchEntryDateMax	= TB_SearchEntryDateMax.getText();		//登録日
						String GetSearchUpdateDateMin	= TB_SearchUpdateDateMin.getText();		//更新日
						String GetSearchUpdateDateMax	= TB_SearchUpdateDateMax.getText();		//更新日
						String GetArrNoAnyString		= TB_ArrNoAny.getText();
						
						Object[][] ArrivalPlanHdRt		= ArrivalPlanHdRt(
								GetSearchClWh,
								GetSearchClCd,
								GetSearchClGpCD,
								GetSearchSpCd,
								GetSearchArrNo,
								GetSearchClArrNo,
								GetSearchPlanDateMin,
								GetSearchPlanDateMax,
								GetSearchHdActualDateMin,
								GetSearchHdActualDateMax,
								GetSearchArCom,
								
								GetSearchItemCd,
								GetSearchClItemCd,
								GetSearchJanCd,
								GetSearchItemMdNo,
								GetSearchItemName,
								GetSearchlot,
								GetSearchExpDateMin,
								GetSearchExpDateMax,
								GetSearchActualDateMin,
								GetSearchActualDateMax,
								GetSearchCom,
								GetSearchEntryUser,
								GetSearchUpdateUser,
								
								GetSearchPlanQtyMin,
								GetSearchPlanQtyMax,
								GetSearchActualQtyMin,
								GetSearchActualQtyMax,
								GetSearchSpName,
								GetSearchSpPost,
								GetSearchSpAdd,
								GetSearchSpTel,
								GetSearchFixFg,
								GetSearchEntryDateMin,
								GetSearchEntryDateMax,
								GetSearchUpdateDateMin,
								GetSearchUpdateDateMax,
								GetArrNoAnyString
								);
						
						if(0==ArrivalPlanHdRt.length) {
							B100_TableControl.AddSortOFF(tb01,MainFmTableModel);
						}else {
							for(int i=0;i<ArrivalPlanHdRt.length;i++) {
								Object[] SetOb = new Object[ArrivalPlanHdRt[i].length+1];
								SetOb[0] = false;
								for(int i01=0;i01<ArrivalPlanHdRt[i].length;i01++) {
									SetOb[i01+1] = ""+ArrivalPlanHdRt[i][i01];
								}
								MainFmTableModel.addRow(SetOb);
							}
							B100_TableControl.AddSortON(tb01,MainFmTableModel);
						}
						
						Ms_fm.setVisible(false);
				    }else if (option == JOptionPane.NO_OPTION){
				    	JOptionPane.showMessageDialog(null, "中断しました");
				    }
					RenewFg = true;
				}
			}
		});
		
		//詳細画面入荷予定票発行ボタン押下時の挙動
		MsPlanListBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					String GetArrNo		= TBMs_ArrNo.getText();//入荷予定NO
					String TgtWhCd 		= B100_DefaultVariable.WhList[1][TBMs_ClWh.getSelectedIndex()];
					String TgtClCd 		= B100_DefaultVariable.ClList[1][TBMs_ClCd.getSelectedIndex()];
					ArrayList<String> ArrNoList = new ArrayList<String>();
					boolean NewPrintOnly = false;
					
					ArrNoList.add(""+GetArrNo);
					
					WTList100_ArrivalPlan.ArrivalPlanList0001(TgtWhCd,TgtClCd,ArrNoList,NewPrintOnly);
					RenewFg = true;
				}
			}
		});
		
		//詳細画面貼札発行ボタン押下時の挙動
		MsPlanPosterBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					String GetArrNo		= TBMs_ArrNo.getText();//入荷予定NO
					String TgtWhCd 		= B100_DefaultVariable.WhList[1][TBMs_ClWh.getSelectedIndex()];
					String TgtClCd 		= B100_DefaultVariable.ClList[1][TBMs_ClCd.getSelectedIndex()];
					ArrayList<String> ArrNoList = new ArrayList<String>();
					boolean NewPrintOnly = false;
					
					ArrNoList.add(""+GetArrNo);
					
					WTList100_ArrivalPoster.ArrivalPoster(TgtWhCd,TgtClCd,ArrNoList,NewPrintOnly);
					RenewFg = true;
				}
			}
		});
		
		//詳細画面EXITボタン押下時の挙動
		Msexit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					MsViewMode = false;
					Ms_fm.setVisible(false);
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
					Ms_fm.setVisible(false);
					
					int RowCount = MainFmTableModel.getRowCount();
					boolean KickFg = false;
					
					for(int i=0;i<RowCount;i++) {
						if((boolean)MainFmTableModel.getValueAt(i, 0)) {
							KickFg = true;
						}
					}
					if(KickFg) {
						MsFrameControl(
								Ms_fm,
								MainFmTableModel,
								MstableModel,
								TBMs_ClWh,
								TBMs_ClCd,
								TBMs_ClGpCD,
								TBMs_SpCd,
								TBMs_ArrNo,
								TBMs_ClArrNo,
								TBMs_PlanDate,
								TBMs_HdActualDate,
	
								TBMs_HdEntryDate,
								TBMs_HdUpdateDate,
								TBMs_HdEntryUser,
								TBMs_HdUpdateUser,
								TBMs_FixFg,
								TBMs_ArCom01,
								TBMs_ArCom02,
								TBMs_ArCom03,
								TBMs_MsNo,
								TBMs_ItemCd,
								TBMs_ItemName,
								TBMs_lot,
								TBMs_ExpDate,
								TBMs_PlanQty,
								TBMs_ActualQty,
								TBMs_Com01,
								TBMs_Com02,
								
								TBMs_ClItemCd,
								TBMs_ActualDate,
								TBMs_JanCd,
								TBMs_ItemMdNo,
								TBMs_EntryDate,
								TBMs_UpdateDate,
								TBMs_EntryUser,
								TBMs_UpdateUser,
								
								MsRenewBtn,
								MsRenewEntryBtn,
								CancelBtn,
								
								TBMs_PlanDateAfterBtn,
								TBMs_PlanDateBeforeBtn,
								
								TBMs_ExpDateAfterBtn,
								TBMs_ExpDateBeforeBtn,
								
								MsPlanListBtn,
								MsPlanPosterBtn
								) ;
					}
					RenewFg = true;
				}
			}
		});
		
		
		//入荷予定票発行ボタン押下時の挙動
		PlanListBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					String TgtWhCd = B100_DefaultVariable.SearchWhList[1][TB_SearchClWh.getSelectedIndex()];
					String TgtClCd = B100_DefaultVariable.SearchClList[1][TB_SearchClCd.getSelectedIndex()];
					ArrayList<String> ArrNoList = new ArrayList<String>();
					boolean NewPrintOnly = true;
					if(PlanListTgtAll.isSelected()) {NewPrintOnly=false;}
					
					int RowCount = MainFmTableModel.getRowCount();
					for(int i=0;i<RowCount;i++) {
						ArrNoList.add(""+MainFmTableModel.getValueAt(i,T100_ArrivalPlanHdRt.ColArrNo+1));
					}
					
					WTList100_ArrivalPlan.ArrivalPlanList0001(TgtWhCd,TgtClCd,ArrNoList,NewPrintOnly);
					RenewFg = true;
				}
			}
		});
		
		//貼札発行ボタン押下時の挙動
		PlanPosterBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					String TgtWhCd = B100_DefaultVariable.SearchWhList[1][TB_SearchClWh.getSelectedIndex()];
					String TgtClCd = B100_DefaultVariable.SearchClList[1][TB_SearchClCd.getSelectedIndex()];
					ArrayList<String> ArrNoList = new ArrayList<String>();
					boolean NewPrintOnly = true;
					if(PlanListTgtAll.isSelected()) {NewPrintOnly=false;}
					
					int RowCount = MainFmTableModel.getRowCount();
					for(int i=0;i<RowCount;i++) {
						ArrNoList.add(""+MainFmTableModel.getValueAt(i,T100_ArrivalPlanHdRt.ColArrNo+1));
					}
					
					WTList100_ArrivalPoster.ArrivalPoster(TgtWhCd,TgtClCd,ArrNoList,NewPrintOnly);
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
					String GetSearchClWh			= B100_DefaultVariable.SearchWhList[1][TB_SearchClWh.getSelectedIndex()];			//ヘッダ担当倉庫
					String GetSearchClCd			= B100_DefaultVariable.SearchClList[1][TB_SearchClCd.getSelectedIndex()];			//ヘッダ荷主CD
					String GetSearchClGpCD			= B100_DefaultVariable.SearchClGpList[1][TB_SearchClGpCD.getSelectedIndex()];		//ヘッダ荷主グループCD
					String GetSearchSpCd			= B100_DefaultVariable.SearchSupplierList[1][TB_SearchSpCd.getSelectedIndex()];		//ヘッダ仕入先　
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
					String GetSearchFixFg			= B100_DefaultVariable.SearchArryvalFixFgList[1][TB_SearchFixFg.getSelectedIndex()];	//状況 
					String GetSearchEntryDateMin	= TB_SearchEntryDateMin.getText();		//登録日
					String GetSearchEntryDateMax	= TB_SearchEntryDateMax.getText();		//登録日
					String GetSearchUpdateDateMin	= TB_SearchUpdateDateMin.getText();		//更新日
					String GetSearchUpdateDateMax	= TB_SearchUpdateDateMax.getText();		//更新日
					String GetArrNoAnyString		= TB_ArrNoAny.getText();
					
					Object[][] ArrivalPlanHdRt		= ArrivalPlanHdRt(
							GetSearchClWh,
							GetSearchClCd,
							GetSearchClGpCD,
							GetSearchSpCd,
							GetSearchArrNo,
							GetSearchClArrNo,
							GetSearchPlanDateMin,
							GetSearchPlanDateMax,
							GetSearchHdActualDateMin,
							GetSearchHdActualDateMax,
							GetSearchArCom,
							
							GetSearchItemCd,
							GetSearchClItemCd,
							GetSearchJanCd,
							GetSearchItemMdNo,
							GetSearchItemName,
							GetSearchlot,
							GetSearchExpDateMin,
							GetSearchExpDateMax,
							GetSearchActualDateMin,
							GetSearchActualDateMax,
							GetSearchCom,
							GetSearchEntryUser,
							GetSearchUpdateUser,
							
							GetSearchPlanQtyMin,
							GetSearchPlanQtyMax,
							GetSearchActualQtyMin,
							GetSearchActualQtyMax,
							GetSearchSpName,
							GetSearchSpPost,
							GetSearchSpAdd,
							GetSearchSpTel,
							GetSearchFixFg,
							GetSearchEntryDateMin,
							GetSearchEntryDateMax,
							GetSearchUpdateDateMin,
							GetSearchUpdateDateMax,
							GetArrNoAnyString
							);
					
					if(0==ArrivalPlanHdRt.length) {
						B100_TableControl.AddSortOFF(tb01,MainFmTableModel);
					}else {
						for(int i=0;i<ArrivalPlanHdRt.length;i++) {
							Object[] SetOb = new Object[ArrivalPlanHdRt[i].length+1];
							SetOb[0] = false;
							for(int i01=0;i01<ArrivalPlanHdRt[i].length;i01++) {
								SetOb[i01+1] = ""+ArrivalPlanHdRt[i][i01];
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
					/**************************************************************
					検索条件初期値に戻す
					***************************************************************/
					TB_SearchClWh.setSelectedIndex(		B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.SearchWhList[1]	,A00000_Main.ClWh,true));			//ヘッダ担当倉庫
					TB_SearchClCd.setSelectedIndex(		B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.SearchClList[1]	,A00000_Main.ClCd,true));			//ヘッダ荷主CD
					TB_SearchClGpCD.setSelectedIndex(	B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.SearchClGpList[1]	,A00000_Main.ClGp,true));		//ヘッダ荷主グループCD
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
					TB_ArrNoAny.setText("");
					
					/**************************************************************
					検索結果消す
					***************************************************************/
					int RowCount = MainFmTableModel.getRowCount();
					for(int i=0;i<RowCount;i++) {
						MainFmTableModel.removeRow(0);
					}
					B100_TableControl.AddSortOFF(tb01,MainFmTableModel);
					Ms_fm.setVisible(false);
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
					
					Ms_fm.setVisible(false);
					Ms_fm.dispose();
					
					main_fm.setVisible(false);
					main_fm.dispose();
					WT100_ArrivalPlan_04_SomeEntry.ArrivalPlanSomeEntry(0,0);
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
					MsFrameControl(
							Ms_fm,
							MainFmTableModel,
							MstableModel,
							TBMs_ClWh,
							TBMs_ClCd,
							TBMs_ClGpCD,
							TBMs_SpCd,
							TBMs_ArrNo,
							TBMs_ClArrNo,
							TBMs_PlanDate,
							TBMs_HdActualDate,

							TBMs_HdEntryDate,
							TBMs_HdUpdateDate,
							TBMs_HdEntryUser,
							TBMs_HdUpdateUser,
							TBMs_FixFg,
							TBMs_ArCom01,
							TBMs_ArCom02,
							TBMs_ArCom03,
							TBMs_MsNo,
							TBMs_ItemCd,
							TBMs_ItemName,
							TBMs_lot,
							TBMs_ExpDate,
							TBMs_PlanQty,
							TBMs_ActualQty,
							TBMs_Com01,
							TBMs_Com02,
							
							TBMs_ClItemCd,
							TBMs_ActualDate,
							TBMs_JanCd,
							TBMs_ItemMdNo,
							TBMs_EntryDate,
							TBMs_UpdateDate,
							TBMs_EntryUser,
							TBMs_UpdateUser,
							
							MsRenewBtn,
							MsRenewEntryBtn,
							CancelBtn,
							
							TBMs_PlanDateAfterBtn,
							TBMs_PlanDateBeforeBtn,
							
							TBMs_ExpDateAfterBtn,
							TBMs_ExpDateBeforeBtn,
							
							MsPlanListBtn,
							MsPlanPosterBtn
							) ;
					RenewFg = true;
				}
			}
		});
		
		//修正ボタン
		RenewBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					String TgtWhCd	= "";
					String TgtClCd	= "";
					String TgtArrNo	= "";
					
					int RowCount = MainFmTableModel.getRowCount();
					boolean KickFg = false;
					for(int i=0;i<RowCount;i++) {
						if((boolean)MainFmTableModel.getValueAt(i, 0)) {
							TgtWhCd		= ""+MainFmTableModel.getValueAt(i, 1+T100_ArrivalPlanHdRt.ColClWh);
							TgtClCd		= ""+MainFmTableModel.getValueAt(i, 1+T100_ArrivalPlanHdRt.ColClCd);
							TgtArrNo	= ""+MainFmTableModel.getValueAt(i, 1+T100_ArrivalPlanHdRt.ColArrNo);
							
							KickFg = true;
						}
					}
					
					if(KickFg) {
						SetX=main_fm.getX();
						SetY=main_fm.getY();
						
						Ms_fm.setVisible(false);
						Ms_fm.dispose();
	
						main_fm.setVisible(false);
						main_fm.dispose();
						WT100_ArrivalPlan_01_RenewAndCreate.ArrivalPlanRenewAndCreate(0,0,TgtWhCd,TgtClCd,TgtArrNo);
					}
					RenewFg = true;
				}
			}
		});
		//新規登録ボタン
		CreateBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					String TgtWhCd	= "";
					String TgtClCd	= "";
					String TgtArrNo	= "";
					
					SetX=main_fm.getX();
					SetY=main_fm.getY();
					
					Ms_fm.setVisible(false);
					Ms_fm.dispose();

					main_fm.setVisible(false);
					main_fm.dispose();
					WT100_ArrivalPlan_01_RenewAndCreate.ArrivalPlanRenewAndCreate(0,0,TgtWhCd,TgtClCd,TgtArrNo);
					RenewFg = true;
				}
			}
		});
		
		//CSVボタン押下時の挙動
		CsvBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					B100_TableControl.TableOutPutCsv("出力先選択","入荷予定検（ヘッダ）検索結果",tb01);
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
						String[][] OutData = MsOutPutDetaGet(MainFmTableModel);
						
						String NowDTM=B100_DateTimeControl.dtmString2(B100_DateTimeControl.dtm()[1])[1].replace(" ", "").replace("/", "").replace(":", "");
						String fp = Selected+"\\"+"入荷予定（明細）検索結果"+NowDTM+".csv";;
						
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
		
		//エクセル出力ボタン押下時の挙動
		ExcelBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					B100_TableControl.TableOutPutExcel("出力先選択","入荷予定（ヘッダ）検索結果",tb01);
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
						String[][] OutData = MsOutPutDetaGet(MainFmTableModel);
						
						String NowDTM=B100_DateTimeControl.dtmString2(B100_DateTimeControl.dtm()[1])[1].replace(" ", "").replace("/", "").replace(":", "");
						String fp = Selected+"\\"+"入荷予定（明細）検索結果"+NowDTM+".xlsx";
						
						int MFG = 0;
						int OPFG = 1;
						B100_ExcellControl.EXCELL_DATA_SET(fp,"入荷予定（明細）検索結果",OutData ,MFG,OPFG);
						
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
	
	private static void DataRenew(
			JComboBox TBMs_ClWh,
			JComboBox TBMs_ClCd,
			JComboBox TBMs_ClGpCD,
			JComboBox TBMs_SpCd,
			JTextField TBMs_ArrNo,
			JTextField TBMs_ClArrNo,
			JFormattedTextField TBMs_PlanDate,
			JFormattedTextField TBMs_HdActualDate,

			JTextField TBMs_HdEntryDate,
			JTextField TBMs_HdUpdateDate,
			JTextField TBMs_HdEntryUser,
			JTextField TBMs_HdUpdateUser,
			JComboBox TBMs_FixFg,
			JTextField TBMs_ArCom01,
			JTextField TBMs_ArCom02,
			JTextField TBMs_ArCom03,
			
			DefaultTableModel MstableModel
			) {
		//入荷予定データ更新する
		String GetClWh			= B100_TextControl.Trim(B100_DefaultVariable.WhList[1][TBMs_ClWh.getSelectedIndex()]);						//ヘッダ担当倉庫
		String GetClCd			= B100_TextControl.Trim(B100_DefaultVariable.ClList[1][TBMs_ClCd.getSelectedIndex()]);						//ヘッダ荷主CD
		String GetClGpCD		= B100_TextControl.Trim(B100_DefaultVariable.ClGpList[1][TBMs_ClGpCD.getSelectedIndex()]);				//ヘッダ荷主グループCD
		String GetSpCd			= B100_TextControl.Trim(B100_DefaultVariable.SupplierList[1][TBMs_SpCd.getSelectedIndex()]);				//ヘッダ仕入先
		String GetArrNo			= B100_TextControl.Trim(TBMs_ArrNo.getText());																//入荷予定NO
		String GetClArrNo		= B100_TextControl.Trim(TBMs_ClArrNo.getText());															//荷主予定番号
		String GetPlanDate		= B100_TextControl.TextToDate(TBMs_PlanDate.getText());													//入荷予定日
		String GetHdActualDate	= B100_TextControl.Trim(TBMs_HdActualDate.getText());														//入荷実績日

		String GetHdEntryDate	= B100_TextControl.Trim(TBMs_HdEntryDate.getText());														//登録日
		String GetHdUpdateDate	= B100_TextControl.Trim(TBMs_HdUpdateDate.getText());														//更新日
		String GetHdEntryUser	= B100_TextControl.Trim(TBMs_HdEntryUser.getText());														//登録者
		String GetHdUpdateUser	= B100_TextControl.Trim(TBMs_HdUpdateUser.getText());														//更新者
		int GetFixFg			= B100_TextControl.TextToInt(B100_DefaultVariable.ArryvalFixFgList[1][TBMs_FixFg.getSelectedIndex()]);	//状況
		String GetArCom01		= B100_TextControl.Trim(TBMs_ArCom01.getText());															//コメント1
		String GetArCom02		= B100_TextControl.Trim(TBMs_ArCom02.getText());															//コメント2
		String GetArCom03		= B100_TextControl.Trim(TBMs_ArCom03.getText());															//コメント3
		
		int RowCount = MstableModel.getRowCount();
		
		String[] MsGetClWh		= new String[RowCount];				//担当倉庫
		String[] MsGetClCd		= new String[RowCount];				//荷主CD
		String[] MsGetArrNo		= new String[RowCount];				//入荷予定NO（WMS採番）
		String[] MsGetMsNo		= new String[RowCount];				//明細番号
		String[] MsGetlot		= new String[RowCount];				//ロット
		String[] MsGetExpDate	= new String[RowCount];				//消費期限
		String[] MsGetPlanQty	= new String[RowCount];				//予定数量
		String[] MsGetCom01		= new String[RowCount];				//コメント1
		String[] MsGetCom02		= new String[RowCount];				//コメント2
		String[] MsGetUpdateDate= new String[RowCount];				//更新日
		String[] MsGetUpdateUser= new String[RowCount];				//更新者
		
		String now_dtm = B100_DateTimeControl.dtmString2(B100_DateTimeControl.dtm()[1])[1];
		
		for(int i=0;i<RowCount;i++) {
			String GetMsViewMsNo		= ""+MstableModel.getValueAt(i,MsViewColMsNo);			//明細番号
			String GetMsViewItemCd		= ""+MstableModel.getValueAt(i,MsViewColItemCd);			//商品コード
			String GetMsViewClItemCd	= ""+MstableModel.getValueAt(i,MsViewColClItemCd);		//荷主商品コード
			String GetMsViewItemName	= ""+MstableModel.getValueAt(i,MsViewColItemName);		//商品名
			String GetMsViewLot			= ""+MstableModel.getValueAt(i,MsViewColLot);				//ロット
			String GetMsViewExpDate		= ""+MstableModel.getValueAt(i,MsViewColExpDate);			//消費期限
			String GetMsViewPlanQty		= ""+MstableModel.getValueAt(i,MsViewColPlanQty);			//予定数量
			String GetMsViewActualQty	= ""+MstableModel.getValueAt(i,MsViewColActualQty);		//実績数
			String GetMsViewActualDate	= ""+MstableModel.getValueAt(i,MsViewColActualDate);		//入荷日
			String GetMsViewCom01		= ""+MstableModel.getValueAt(i,MsViewColCom01);			//コメント1
			String GetMsViewCom02		= ""+MstableModel.getValueAt(i,MsViewColCom02);			//コメント2
			String GetMsViewJanCd		= ""+MstableModel.getValueAt(i,MsViewColJanCd);			//JANCD（バラ）
			String GetMsViewItemMdNo	= ""+MstableModel.getValueAt(i,MsViewColItemMdNo);		//商品型番
			String GetMsViewEntryDate	= ""+MstableModel.getValueAt(i,MsViewColEntryDate);		//登録日
			String GetMsViewUpdateDate	= ""+MstableModel.getValueAt(i,MsViewColUpdateDate);		//更新日
			String GetMsViewEntryUser	= ""+MstableModel.getValueAt(i,MsViewColEntryUser);		//登録者
			String GetMsViewUpdateUser	= ""+MstableModel.getValueAt(i,MsViewColUpdateUser);		//更新者
			
			
			MsGetClWh[i]		= B100_TextControl.Trim(GetClWh);									//担当倉庫
			MsGetClCd[i]		= B100_TextControl.Trim(GetClCd);									//荷主CD
			MsGetArrNo[i]		= B100_TextControl.Trim(GetArrNo);									//入荷予定NO（WMS採番）
			MsGetMsNo[i]		= B100_TextControl.Trim(GetMsViewMsNo);								//明細番号
			MsGetlot[i]			= B100_TextControl.Trim(GetMsViewLot);								//ロット
			MsGetExpDate[i]		= B100_TextControl.TextToDate(GetMsViewExpDate);					//消費期限
			MsGetPlanQty[i]		= ""+B100_TextControl.TextToInt(GetMsViewPlanQty);					//予定数量
			MsGetCom01[i]		= B100_TextControl.Trim(GetMsViewCom01);							//コメント1
			MsGetCom02[i]		= B100_TextControl.Trim(GetMsViewCom02);							//コメント2
			MsGetUpdateDate[i]	= now_dtm;															//更新日
			MsGetUpdateUser[i]	= "(" + A00000_Main.LoginUserId + ")" + A00000_Main.LoginUserName;				//更新者
			
			//賞味期限空白の場合データはnullで登録
			if("".equals(MsGetExpDate[i])) {
				MsGetExpDate[i]="null";
			}
		}
		
		
		boolean KickFg = true;
		
		if("".equals(GetClWh)) 		{KickFg = false;}
		if("".equals(GetClCd)) 		{KickFg = false;}
		if("".equals(GetArrNo)) 	{KickFg = false;}
		if("".equals(GetPlanDate)) 	{KickFg = false;}
		if(KickFg) {
			Object[][] SetString = {
					 {"ClWh"		,"0"	,"1"	,"Key"	,GetClWh}				//担当倉庫
					,{"ClCd"		,"0"	,"1"	,"Key"	,GetClCd}				//荷主CD
					,{"ArrNo"		,"0"	,"1"	,"Key"	,GetArrNo}				//入荷予定NO（WMS採番）
					,{"PlanDate"	,"0"	,"1"	,""		,GetPlanDate}			//入荷予定日
					,{"ArCom01"		,"0"	,"1"	,""		,GetArCom01}			//コメント1
					,{"ArCom02"		,"0"	,"1"	,""		,GetArCom02}			//コメント2
					,{"ArCom03"		,"0"	,"1"	,""		,GetArCom03}			//コメント3
					,{"UpdateDate"	,"0"	,"1"	,""		,now_dtm}				//更新日
					,{"UpdateUser"	,"0"	,"1"	,""		,"(" + A00000_Main.LoginUserId + ")" + A00000_Main.LoginUserName}			//更新者
					};
		
			String tgt_table = "WW0010ArrivalPlanHd";
			String TgtDB = "WANKO";
			int non_msg_fg = 1;
			
			A100_InsertUpdateSQL.InsertUpdateOneRecord(SetString,tgt_table,TgtDB,non_msg_fg);
			
			Object[][] MsSetString = {
					 {"ClWh"		,"0"	,"1"	,"Key"	,MsGetClWh}				//担当倉庫
					,{"ClCd"		,"0"	,"1"	,"Key"	,MsGetClCd}				//荷主CD
					,{"ArrNo"		,"0"	,"1"	,"Key"	,MsGetArrNo}			//入荷予定NO（WMS採番）
					,{"MsNo"		,"0"	,"1"	,"Key"	,MsGetMsNo}				//明細番号
					,{"lot"			,"0"	,"1"	,""		,MsGetlot}				//ロット
					,{"ExpDate"		,"0"	,"1"	,""		,MsGetExpDate}			//消費期限
					,{"PlanQty"		,"0"	,"1"	,""		,MsGetPlanQty}			//予定数量
					,{"Com01"		,"0"	,"1"	,""		,MsGetCom01}			//コメント1
					,{"Com02"		,"0"	,"1"	,""		,MsGetCom02}			//コメント2
					,{"UpdateDate"	,"0"	,"1"	,""		,MsGetUpdateDate}		//更新日
					,{"UpdateUser"	,"0"	,"1"	,""		,MsGetUpdateUser}		//更新者
					};
		
			tgt_table = "WW0011ArrivalPlanMs";
			TgtDB = "WANKO";
			non_msg_fg = 1;
			
			A100_InsertUpdateSQL.InsertUpdateSomeRecord(MsSetString,tgt_table,TgtDB,non_msg_fg);
		}
	}
	
	private static String[][] MsOutPutDetaGet(
			DefaultTableModel MainFmTableModel
			){
		Object[][] RtArrivalPlanMsRt= T100_ArrivalPlanMsRt.RtArrivalPlanMsRt();
		String[][] OutData = new String[1][RtArrivalPlanMsRt.length];
		for(int j=0;j<RtArrivalPlanMsRt.length;j++) {
			OutData[0][(int)RtArrivalPlanMsRt[j][1]] = ""+RtArrivalPlanMsRt[j][3];
		}
		ArrayList<String> TgtClWh			= new ArrayList<String>();		//ヘッダ担当倉庫
		ArrayList<String> TgtClCd			= new ArrayList<String>();		//ヘッダ荷主CD
		int RowCount = MainFmTableModel.getRowCount();
		if(0<RowCount) {
			for(int i=0;i<RowCount;i++) {
				TgtClWh.add(B100_TextControl.Trim(""+MainFmTableModel.getValueAt(i, T100_ArrivalPlanHdRt.ColClWh+1)));
				TgtClCd.add(B100_TextControl.Trim(""+MainFmTableModel.getValueAt(i, T100_ArrivalPlanHdRt.ColClCd+1)));
			}
			TgtClWh				= B100_ArrayListControl.ArryListStringUniqueList(TgtClWh);				//ヘッダ担当倉庫
			TgtClCd				= B100_ArrayListControl.ArryListStringUniqueList(TgtClCd);				//ヘッダ荷主CD
			
			int Counter = 0;
			
			for(int i01=0;i01<TgtClWh.size();i01++) {
				for(int i02=0;i02<TgtClCd.size();i02++) {
					ArrayList<String> SearchClWh			= new ArrayList<String>();		//ヘッダ担当倉庫
					ArrayList<String> SearchClCd			= new ArrayList<String>();		//ヘッダ荷主CD
					ArrayList<String> SearchClGpCD			= new ArrayList<String>();		//ヘッダ荷主グループCD
					ArrayList<String> SearchArrNo			= new ArrayList<String>();		//ヘッダ入荷予定NO
					
					SearchClWh.add(TgtClWh.get(i01));
					SearchClCd.add(TgtClCd.get(i02));
					
					for(int i=0;i<RowCount;i++) {
						if(TgtClWh.get(i01).equals(B100_TextControl.Trim(""+MainFmTableModel.getValueAt(i, T100_ArrivalPlanHdRt.ColClWh+1)))
							&& 	TgtClCd.get(i02).equals(B100_TextControl.Trim(""+MainFmTableModel.getValueAt(i, T100_ArrivalPlanHdRt.ColClCd+1)))
							) {
							SearchClGpCD.add(B100_TextControl.Trim(""+MainFmTableModel.getValueAt(i, T100_ArrivalPlanHdRt.ColClGpCD+1)));
							SearchArrNo.add(B100_TextControl.Trim(""+MainFmTableModel.getValueAt(i, T100_ArrivalPlanHdRt.ColArrNo+1)));
						}
					}
					Object[][] ArrivalPlanMsRt	= ArrivalPlanMsRt(
							SearchClWh,
							SearchClCd,
							SearchClGpCD,
							SearchArrNo
							);
					
					Counter = Counter+ArrivalPlanMsRt.length;
				}
			}
			
			OutData = new String[Counter+1][RtArrivalPlanMsRt.length];
			for(int j=0;j<RtArrivalPlanMsRt.length;j++) {
				OutData[0][(int)RtArrivalPlanMsRt[j][1]] = ""+RtArrivalPlanMsRt[j][3];
			}
			
			Counter = 0;
			for(int i01=0;i01<TgtClWh.size();i01++) {
				for(int i02=0;i02<TgtClCd.size();i02++) {
					ArrayList<String> SearchClWh			= new ArrayList<String>();		//ヘッダ担当倉庫
					ArrayList<String> SearchClCd			= new ArrayList<String>();		//ヘッダ荷主CD
					ArrayList<String> SearchClGpCD			= new ArrayList<String>();		//ヘッダ荷主グループCD
					ArrayList<String> SearchArrNo			= new ArrayList<String>();		//ヘッダ入荷予定NO
					
					SearchClWh.add(TgtClWh.get(i01));
					SearchClCd.add(TgtClCd.get(i02));
					
					for(int i=0;i<RowCount;i++) {
						if(TgtClWh.get(i01).equals(B100_TextControl.Trim(""+MainFmTableModel.getValueAt(i, T100_ArrivalPlanHdRt.ColClWh+1)))
							&& 	TgtClCd.get(i02).equals(B100_TextControl.Trim(""+MainFmTableModel.getValueAt(i, T100_ArrivalPlanHdRt.ColClCd+1)))
							) {
							SearchClGpCD.add(B100_TextControl.Trim(""+MainFmTableModel.getValueAt(i, T100_ArrivalPlanHdRt.ColClGpCD+1)));
							SearchArrNo.add(B100_TextControl.Trim(""+MainFmTableModel.getValueAt(i, T100_ArrivalPlanHdRt.ColArrNo+1)));
						}
					}
					Object[][] ArrivalPlanMsRt	= ArrivalPlanMsRt(
							SearchClWh,
							SearchClCd,
							SearchClGpCD,
							SearchArrNo
							);
					for(int i=0;i<ArrivalPlanMsRt.length;i++) {
						Counter	= Counter+1;
						
						for(int j=0;j<RtArrivalPlanMsRt.length;j++) {
							OutData[Counter][j] = ""+ArrivalPlanMsRt[i][j];
						}
					}
					
				}
			}
		}
		return OutData;
	}
	
	//詳細子画面初期表示
	private static void MsFrameControl(
			JFrame Ms_fm,
			DefaultTableModel MainFmTableModel,
			DefaultTableModel MstableModel,
			
			JComboBox TBMs_ClWh,
			JComboBox TBMs_ClCd,
			JComboBox TBMs_ClGpCD,
			JComboBox TBMs_SpCd,
			JTextField TBMs_ArrNo,
			JTextField TBMs_ClArrNo,
			JFormattedTextField TBMs_PlanDate,
			JFormattedTextField TBMs_HdActualDate,

			JTextField TBMs_HdEntryDate,
			JTextField TBMs_HdUpdateDate,
			JTextField TBMs_HdEntryUser,
			JTextField TBMs_HdUpdateUser,
			JComboBox TBMs_FixFg,
			JTextField TBMs_ArCom01,
			JTextField TBMs_ArCom02,
			JTextField TBMs_ArCom03,
			JFormattedTextField TBMs_MsNo,
			JTextField TBMs_ItemCd,
			JTextField TBMs_ItemName,
			JTextField TBMs_lot,
			JFormattedTextField TBMs_ExpDate,
			JFormattedTextField TBMs_PlanQty,
			JFormattedTextField TBMs_ActualQty,
			JTextField TBMs_Com01,
			JTextField TBMs_Com02,
			
			JTextField TBMs_ClItemCd,
			JFormattedTextField TBMs_ActualDate,
			JTextField TBMs_JanCd,
			JTextField TBMs_ItemMdNo,
			JFormattedTextField TBMs_EntryDate,
			JFormattedTextField TBMs_UpdateDate,
			JTextField TBMs_EntryUser,
			JTextField TBMs_UpdateUser,
			
			JButton MsRenewBtn,
			JButton MsRenewEntryBtn,
			JButton CancelBtn,
			
			JButton TBMs_PlanDateAfterBtn,
			JButton TBMs_PlanDateBeforeBtn,
			
			JButton TBMs_ExpDateAfterBtn,
			JButton TBMs_ExpDateBeforeBtn,
			
			JButton MsPlanListBtn,
			JButton MsPlanPosterBtn
			) {
		//初期化
		int RowCount = MstableModel.getRowCount();
		for(int i=0;i<RowCount;i++) {
			MstableModel.removeRow(0);
		}
		
		
		TBMs_ClWh.setSelectedIndex(		B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.WhList[1]				,A00000_Main.ClWh,true));			//ヘッダ担当倉庫
		TBMs_ClCd.setSelectedIndex(		B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.ClList[1]				,A00000_Main.ClCd,true));			//ヘッダ荷主CD
		TBMs_ClGpCD.setSelectedIndex(	B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.ClGpList[1]			,A00000_Main.ClGp,true));			//ヘッダ荷主グループCD
		TBMs_SpCd.setSelectedIndex(		0);			//ヘッダ仕入先
		TBMs_ArrNo.setText("");
		TBMs_ClArrNo.setText("");
		TBMs_PlanDate.setText("");
		TBMs_HdActualDate.setText("");

		TBMs_HdEntryDate.setText("");
		TBMs_HdUpdateDate.setText("");
		TBMs_HdEntryUser.setText("");
		TBMs_HdUpdateUser.setText("");
		TBMs_FixFg.setSelectedIndex(	B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.ArryvalFixFgList[1]	,"0",true));
		TBMs_ArCom01.setText("");
		TBMs_ArCom02.setText("");
		TBMs_ArCom03.setText("");
		TBMs_MsNo.setText("");
		TBMs_ItemCd.setText("");
		TBMs_ItemName.setText("");
		TBMs_lot.setText("");
		TBMs_ExpDate.setText("");
		TBMs_PlanQty.setText("");
		TBMs_ActualQty.setText("");
		TBMs_Com01.setText("");
		TBMs_Com02.setText("");
		
		TBMs_ClItemCd.setText("");
		TBMs_ActualDate.setText("");
		TBMs_JanCd.setText("");
		TBMs_ItemMdNo.setText("");
		TBMs_EntryDate.setText("");
		TBMs_UpdateDate.setText("");
		TBMs_EntryUser.setText("");
		TBMs_UpdateUser.setText("");
		
		Ms_fm.remove(MsRenewBtn);
		
		//検索結果に応じて値セット
		boolean KickFg = false;
		String GetSearchClWh	= A00000_Main.ClWh;
		String GetSearchClCd	= A00000_Main.ClCd;
		String GetSearchClGpCD	= A00000_Main.ClGp;
		String GetSearchArrNo	= "";
		
		RowCount = MainFmTableModel.getRowCount();
		for(int i=0;i<RowCount;i++) {
			if((boolean)MainFmTableModel.getValueAt(i, 0)) {
				GetSearchClWh		= (String)MainFmTableModel.getValueAt(i, 1+T100_ArrivalPlanHdRt.ColClWh);
				GetSearchClCd		= (String)MainFmTableModel.getValueAt(i, 1+T100_ArrivalPlanHdRt.ColClCd);
				GetSearchClGpCD		= (String)MainFmTableModel.getValueAt(i, 1+T100_ArrivalPlanHdRt.ColClGpCD);
				GetSearchArrNo		= (String)MainFmTableModel.getValueAt(i, 1+T100_ArrivalPlanHdRt.ColArrNo);
				
				KickFg = true;
			}
		}
		Object[][] ArrivalPlanMsRt = null;
		if(KickFg) {
			ArrayList<String> SearchClWh	= new ArrayList<String>();
			ArrayList<String> SearchClCd	= new ArrayList<String>();
			ArrayList<String> SearchClGpCD	= new ArrayList<String>();
			ArrayList<String> SearchArrNo	= new ArrayList<String>();
			
			SearchClWh.add(GetSearchClWh);
			SearchClCd.add(GetSearchClCd);
			SearchClGpCD.add(GetSearchClGpCD);
			SearchArrNo.add(GetSearchArrNo);
			
			ArrivalPlanMsRt	= ArrivalPlanMsRt(
												SearchClWh,
												SearchClCd,
												SearchClGpCD,
												SearchArrNo
												);
			
			if(null!=ArrivalPlanMsRt && 0<ArrivalPlanMsRt.length) {
				KickFg = true;
			}else {
				KickFg = false;
			}
		}
		if(KickFg) {
			String GetClWh			= (String)ArrivalPlanMsRt[0][T100_ArrivalPlanMsRt.ColClWh];			//ヘッダ担当倉庫
			String GetClCd			= (String)ArrivalPlanMsRt[0][T100_ArrivalPlanMsRt.ColClCd];			//ヘッダ荷主CD
			String GetCLName01		= (String)ArrivalPlanMsRt[0][T100_ArrivalPlanMsRt.ColCLName01];		//ヘッダ荷主名
			String GetClGpCD		= (String)ArrivalPlanMsRt[0][T100_ArrivalPlanMsRt.ColClGpCD];			//ヘッダ荷主グループCD
			String GetCLGpName01	= (String)ArrivalPlanMsRt[0][T100_ArrivalPlanMsRt.ColCLGpName01];		//ヘッダ荷主グループ名1
			String GetArrNo			= (String)ArrivalPlanMsRt[0][T100_ArrivalPlanMsRt.ColArrNo];			//ヘッダ入荷予定NO
			String GetClArrNo		= (String)ArrivalPlanMsRt[0][T100_ArrivalPlanMsRt.ColClArrNo];			//ヘッダ荷主予定番号
			String GetPlanDate		= (String)ArrivalPlanMsRt[0][T100_ArrivalPlanMsRt.ColPlanDate];		//ヘッダ入荷予定日
			String GetHdActualDate	= (String)ArrivalPlanMsRt[0][T100_ArrivalPlanMsRt.ColHdActualDate];	//ヘッダ入荷実績日
			String GetSpCd			= (String)ArrivalPlanMsRt[0][T100_ArrivalPlanMsRt.ColSpCd];			//ヘッダ仕入先CD
			String GetSpName01		= (String)ArrivalPlanMsRt[0][T100_ArrivalPlanMsRt.ColSpName01];		//ヘッダ仕入先名01
			String GetSpName02		= (String)ArrivalPlanMsRt[0][T100_ArrivalPlanMsRt.ColSpName02];		//ヘッダ仕入先名02
			String GetSpName03		= (String)ArrivalPlanMsRt[0][T100_ArrivalPlanMsRt.ColSpName03];		//ヘッダ仕入先名03
			String GetSpPost		= (String)ArrivalPlanMsRt[0][T100_ArrivalPlanMsRt.ColSpPost];			//ヘッダ仕入先郵便
			String GetSpAdd01		= (String)ArrivalPlanMsRt[0][T100_ArrivalPlanMsRt.ColSpAdd01];			//ヘッダ仕入先住所01
			String GetSpAdd02		= (String)ArrivalPlanMsRt[0][T100_ArrivalPlanMsRt.ColSpAdd02];			//ヘッダ仕入先住所02
			String GetSpAdd03		= (String)ArrivalPlanMsRt[0][T100_ArrivalPlanMsRt.ColSpAdd03];			//ヘッダ仕入先住所03
			String GetSpTel			= (String)ArrivalPlanMsRt[0][T100_ArrivalPlanMsRt.ColSpTel];			//ヘッダ仕入先電話
			String GetArCom01		= (String)ArrivalPlanMsRt[0][T100_ArrivalPlanMsRt.ColArCom01];			//ヘッダコメント1
			String GetArCom02		= (String)ArrivalPlanMsRt[0][T100_ArrivalPlanMsRt.ColArCom02];			//ヘッダコメント2
			String GetArCom03		= (String)ArrivalPlanMsRt[0][T100_ArrivalPlanMsRt.ColArCom03];			//ヘッダコメント3
			String GetHdEntryDate	= (String)ArrivalPlanMsRt[0][T100_ArrivalPlanMsRt.ColHdEntryDate];	//ヘッダ登録日
			String GetHdUpdateDate	= (String)ArrivalPlanMsRt[0][T100_ArrivalPlanMsRt.ColHdUpdateDate];	//ヘッダ更新日
			String GetHdEntryUser	= (String)ArrivalPlanMsRt[0][T100_ArrivalPlanMsRt.ColHdEntryUser];	//ヘッダ登録者
			String GetHdUpdateUser	= (String)ArrivalPlanMsRt[0][T100_ArrivalPlanMsRt.ColHdUpdateUser];	//ヘッダ更新者
			int  GetFixFg			= (int)ArrivalPlanMsRt[0][T100_ArrivalPlanMsRt.ColFixFg];				//ヘッダ状況
			
			TBMs_FixFg.setSelectedIndex(	B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.ArryvalFixFgList[1]	,""+GetFixFg,true));
			TBMs_ClWh.setSelectedIndex(		B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.WhList[1]				,GetClWh,true));			//ヘッダ担当倉庫
			TBMs_ClCd.setSelectedIndex(		B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.ClList[1]				,GetClCd,true));			//ヘッダ荷主CD
			TBMs_ClGpCD.setSelectedIndex(	B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.ClGpList[1]			,GetClGpCD,true));			//ヘッダ荷主グループCD
			TBMs_SpCd.setSelectedIndex(		B100_ArrayListControl.ArryListGetRow(B100_DefaultVariable.SupplierList[1]		,GetSpCd,true));			//ヘッダ仕入先
			TBMs_ArrNo.setText(GetArrNo);
			TBMs_ClArrNo.setText(GetClArrNo);
			TBMs_PlanDate.setText(GetPlanDate);
			TBMs_HdActualDate.setText(GetHdActualDate);

			TBMs_HdEntryDate.setText(GetHdEntryDate);
			TBMs_HdUpdateDate.setText(GetHdUpdateDate);
			TBMs_HdEntryUser.setText(GetHdEntryUser);
			TBMs_HdUpdateUser.setText(GetHdUpdateUser);
			TBMs_ArCom01.setText(GetArCom01);
			TBMs_ArCom02.setText(GetArCom02);
			TBMs_ArCom03.setText(GetArCom03);
			
			if(0==GetFixFg) {
				TBMs_ArCom01.setEditable(true);
				TBMs_ArCom02.setEditable(true);
				TBMs_ArCom03.setEditable(true);
				TBMs_PlanDate.setEditable(true);
				
				TBMs_lot.setEditable(true);
				TBMs_ExpDate.setEditable(true);
				TBMs_PlanQty.setEditable(true);
				TBMs_Com01.setEditable(true);
				TBMs_Com02.setEditable(true);
				
				
				TBMs_ArCom01.setBackground(B100_FrameParts.SelectColer("Entry"));
				TBMs_ArCom02.setBackground(B100_FrameParts.SelectColer("Entry"));
				TBMs_ArCom03.setBackground(B100_FrameParts.SelectColer("Entry"));
				TBMs_PlanDate.setBackground(B100_FrameParts.SelectColer("Entry"));
				
				TBMs_lot.setBackground(B100_FrameParts.SelectColer("Entry"));
				TBMs_ExpDate.setBackground(B100_FrameParts.SelectColer("Entry"));
				TBMs_PlanQty.setBackground(B100_FrameParts.SelectColer("Entry"));
				TBMs_Com01.setBackground(B100_FrameParts.SelectColer("Entry"));
				TBMs_Com02.setBackground(B100_FrameParts.SelectColer("Entry"));
				Ms_fm.add(MsRenewEntryBtn);
				Ms_fm.add(CancelBtn);
				Ms_fm.add(TBMs_PlanDateAfterBtn);
				Ms_fm.add(TBMs_PlanDateBeforeBtn);
				Ms_fm.add(TBMs_ExpDateAfterBtn);
				Ms_fm.add(TBMs_ExpDateBeforeBtn);
			}else{
				TBMs_ArCom01.setEditable(false);
				TBMs_ArCom02.setEditable(false);
				TBMs_ArCom03.setEditable(false);
				TBMs_PlanDate.setEditable(false);
				
				TBMs_lot.setEditable(false);
				TBMs_ExpDate.setEditable(false);
				TBMs_PlanQty.setEditable(false);
				TBMs_Com01.setEditable(false);
				TBMs_Com02.setEditable(false);
				
				
				TBMs_ArCom01.setBackground(B100_FrameParts.SelectColer("NoEntry"));
				TBMs_ArCom02.setBackground(B100_FrameParts.SelectColer("NoEntry"));
				TBMs_ArCom03.setBackground(B100_FrameParts.SelectColer("NoEntry"));
				TBMs_PlanDate.setBackground(B100_FrameParts.SelectColer("NoEntry"));
				
				TBMs_lot.setBackground(B100_FrameParts.SelectColer("NoEntry"));
				TBMs_ExpDate.setBackground(B100_FrameParts.SelectColer("NoEntry"));
				TBMs_PlanQty.setBackground(B100_FrameParts.SelectColer("NoEntry"));
				TBMs_Com01.setBackground(B100_FrameParts.SelectColer("NoEntry"));
				TBMs_Com02.setBackground(B100_FrameParts.SelectColer("NoEntry"));
				Ms_fm.remove(MsRenewEntryBtn);
				Ms_fm.remove(CancelBtn);
				Ms_fm.remove(TBMs_PlanDateAfterBtn);
				Ms_fm.remove(TBMs_PlanDateBeforeBtn);
				Ms_fm.remove(TBMs_ExpDateAfterBtn);
				Ms_fm.remove(TBMs_ExpDateBeforeBtn);
			}
			if(9==GetFixFg) {
				Ms_fm.remove(MsPlanListBtn);
				Ms_fm.remove(MsPlanPosterBtn);
			}else {
				Ms_fm.add(MsPlanListBtn);
				Ms_fm.add(MsPlanPosterBtn);
			}
			for(int i=0;i<ArrivalPlanMsRt.length;i++) {
				int GetMsNo				= (int)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColMsNo];				//明細番号
				String GetItemCd		= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColItemCd];			//商品コード
				String GetClItemCd		= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColClItemCd];		//荷主商品コード
				String GetJanCd			= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColJanCd];			//JANCD（バラ）
				String GetItemMdNo		= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColItemMdNo];		//商品型番
				String GetItemName		= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColItemName];		//商品名
				String Getlot			= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.Collot];				//ロット
				String GetExpDate		= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColExpDate];			//消費期限
				int GetPlanQty			= (int)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColPlanQty];			//予定数量
				int GetActualQty		= (int)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColActualQty];			//実績数
				String GetActualDate	= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColActualDate];		//入荷日
				String GetCom01			= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColCom01];			//コメント1
				String GetCom02			= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColCom02];			//コメント2
				String GetEntryDate		= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColEntryDate];		//登録日
				String GetUpdateDate	= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColUpdateDate];		//更新日
				String GetEntryUser		= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColEntryUser];		//登録者
				String GetUpdateUser	= (String)ArrivalPlanMsRt[i][T100_ArrivalPlanMsRt.ColUpdateUser];		//更新者
				
				Object[] SetOb	= new Object[18];
				SetOb[0]	= false;
				
				SetOb[ 1]= ""+GetMsNo;		//明細番号
				SetOb[ 2]= GetItemCd;		//商品コード
				SetOb[ 3]= GetClItemCd;		//荷主商品コード
				SetOb[ 4]= GetItemName;		//商品名
				SetOb[ 5]= Getlot;			//ロット
				SetOb[ 6]= GetExpDate;		//消費期限
				SetOb[ 7]= ""+GetPlanQty;	//予定数量
				SetOb[ 8]= ""+GetActualQty;	//実績数
				SetOb[ 9]= GetActualDate;	//入荷日
				SetOb[10]= GetCom01;		//コメント1
				SetOb[11]= GetCom02;		//コメント2
				SetOb[12]= GetJanCd;		//JANCD（バラ）
				SetOb[13]= GetItemMdNo;		//商品型番
				SetOb[14]= GetEntryDate;	//登録日
				SetOb[15]= GetUpdateDate;	//更新日
				SetOb[16]= GetEntryUser;	//登録者
				SetOb[17]= GetUpdateUser;	//更新者
				
				MstableModel.addRow(SetOb);
			}
		}
		Ms_fm.setVisible(false);
		if(MsViewMode && KickFg) {
			Ms_fm.setVisible(true);
		}
	}
	
	
	
	//入荷予定ヘッダ情報取得
	private static Object[][] ArrivalPlanHdRt(
			String GetSearchClWh,
			String GetSearchClCd,
			String GetSearchClGpCD,
			String GetSearchSpCd,
			String GetSearchArrNo,
			String GetSearchClArrNo,
			String GetSearchPlanDateMin,
			String GetSearchPlanDateMax,
			String GetSearchHdActualDateMin,
			String GetSearchHdActualDateMax,
			String GetSearchArCom,
			
			String GetSearchItemCd,
			String GetSearchClItemCd,
			String GetSearchJanCd,
			String GetSearchItemMdNo,
			String GetSearchItemName,
			String GetSearchlot,
			String GetSearchExpDateMin,
			String GetSearchExpDateMax,
			String GetSearchActualDateMin,
			String GetSearchActualDateMax,
			String GetSearchCom,
			String GetSearchEntryUser,
			String GetSearchUpdateUser,
			
			String GetSearchPlanQtyMin,
			String GetSearchPlanQtyMax,
			String GetSearchActualQtyMin,
			String GetSearchActualQtyMax,
			String GetSearchSpName,
			String GetSearchSpPost,
			String GetSearchSpAdd,
			String GetSearchSpTel,
			String GetSearchFixFg,
			String GetSearchEntryDateMin,
			String GetSearchEntryDateMax,
			String GetSearchUpdateDateMin,
			String GetSearchUpdateDateMax,
			String GetArrNoAnyString
			){
		GetSearchClWh				= B100_TextControl.Trim(GetSearchClWh);
		GetSearchClCd				= B100_TextControl.Trim(GetSearchClCd);
		GetSearchClGpCD				= B100_TextControl.Trim(GetSearchClGpCD);
		GetSearchSpCd				= B100_TextControl.Trim(GetSearchSpCd);
		GetSearchArrNo				= B100_TextControl.Trim(GetSearchArrNo);
		GetSearchClArrNo			= B100_TextControl.Trim(GetSearchClArrNo);
		GetSearchPlanDateMin		= B100_TextControl.Trim(GetSearchPlanDateMin);
		GetSearchPlanDateMax		= B100_TextControl.Trim(GetSearchPlanDateMax);
		GetSearchHdActualDateMin	= B100_TextControl.Trim(GetSearchHdActualDateMin);
		GetSearchHdActualDateMax	= B100_TextControl.Trim(GetSearchHdActualDateMax);
		GetSearchArCom				= B100_TextControl.Trim(GetSearchArCom);
		
		GetSearchItemCd				= B100_TextControl.Trim(GetSearchItemCd);
		GetSearchClItemCd			= B100_TextControl.Trim(GetSearchClItemCd);
		GetSearchJanCd				= B100_TextControl.Trim(GetSearchJanCd);
		GetSearchItemMdNo			= B100_TextControl.Trim(GetSearchItemMdNo);
		GetSearchItemName			= B100_TextControl.Trim(GetSearchItemName);
		GetSearchlot				= B100_TextControl.Trim(GetSearchlot);
		GetSearchExpDateMin			= B100_TextControl.Trim(GetSearchExpDateMin);
		GetSearchExpDateMax			= B100_TextControl.Trim(GetSearchExpDateMax);
		GetSearchActualDateMin		= B100_TextControl.Trim(GetSearchActualDateMin);
		GetSearchActualDateMax		= B100_TextControl.Trim(GetSearchActualDateMax);
		GetSearchCom				= B100_TextControl.Trim(GetSearchCom);
		GetSearchEntryUser			= B100_TextControl.Trim(GetSearchEntryUser);
		GetSearchUpdateUser			= B100_TextControl.Trim(GetSearchUpdateUser);
		
		GetSearchPlanQtyMin			= B100_TextControl.Trim(GetSearchPlanQtyMin);
		GetSearchPlanQtyMax			= B100_TextControl.Trim(GetSearchPlanQtyMax);
		GetSearchActualQtyMin		= B100_TextControl.Trim(GetSearchActualQtyMin);
		GetSearchActualQtyMax		= B100_TextControl.Trim(GetSearchActualQtyMax);
		GetSearchSpName				= B100_TextControl.Trim(GetSearchSpName);
		GetSearchSpPost				= B100_TextControl.Trim(GetSearchSpPost);
		GetSearchSpAdd				= B100_TextControl.Trim(GetSearchSpAdd);
		GetSearchSpTel				= B100_TextControl.Trim(GetSearchSpTel);
		GetSearchFixFg				= B100_TextControl.Trim(GetSearchFixFg);
		GetSearchEntryDateMin		= B100_TextControl.Trim(GetSearchEntryDateMin);
		GetSearchEntryDateMax		= B100_TextControl.Trim(GetSearchEntryDateMax);
		GetSearchUpdateDateMin		= B100_TextControl.Trim(GetSearchUpdateDateMin);
		GetSearchUpdateDateMax		= B100_TextControl.Trim(GetSearchUpdateDateMax);
		
		if(!"".equals(GetSearchPlanQtyMin	)){GetSearchPlanQtyMin		= B100_TextControl.num_only_String02(GetSearchPlanQtyMin);}
		if(!"".equals(GetSearchPlanQtyMax	)){GetSearchPlanQtyMax		= B100_TextControl.num_only_String02(GetSearchPlanQtyMax);}
		if(!"".equals(GetSearchActualQtyMin	)){GetSearchActualQtyMin	= B100_TextControl.num_only_String02(GetSearchActualQtyMin);}
		if(!"".equals(GetSearchActualQtyMax	)){GetSearchActualQtyMax	= B100_TextControl.num_only_String02(GetSearchActualQtyMax);}
		if(!"".equals(GetSearchFixFg		)){GetSearchFixFg			= B100_TextControl.num_only_String02(GetSearchFixFg);}
		
		//次に訪れた時の為に検索条件覚える
		DefaultSearchClWh					= GetSearchClWh;
		DefaultSearchClCd					= GetSearchClCd;
		DefaultSearchClGpCD				= GetSearchClGpCD;
		DefaultSearchSpCd					= GetSearchSpCd;
		DefaultSearchArrNo				= GetSearchArrNo;
		DefaultSearchClArrNo				= GetSearchClArrNo;
		DefaultSearchPlanDateMin		= GetSearchPlanDateMin;
		DefaultSearchPlanDateMax		= GetSearchPlanDateMax;
		DefaultSearchHdActualDateMin	= GetSearchHdActualDateMin;
		DefaultSearchHdActualDateMax	= GetSearchHdActualDateMax;
		DefaultSearchArCom				= GetSearchArCom;
		
		DefaultSearchItemCd				= GetSearchItemCd;
		DefaultSearchClItemCd			= GetSearchClItemCd;
		DefaultSearchJanCd				= GetSearchJanCd;
		DefaultSearchItemMdNo			= GetSearchItemMdNo;
		DefaultSearchItemName			= GetSearchItemName;
		DefaultSearchlot					= GetSearchlot;
		DefaultSearchExpDateMin			= GetSearchExpDateMin;
		DefaultSearchExpDateMax			= GetSearchExpDateMax;
		DefaultSearchActualDateMin		= GetSearchActualDateMin;
		DefaultSearchActualDateMax		= GetSearchActualDateMax;
		DefaultSearchCom					= GetSearchCom;
		DefaultSearchEntryUser			= GetSearchEntryUser;
		DefaultSearchUpdateUser			= GetSearchUpdateUser;
		
		DefaultSearchPlanQtyMin			= GetSearchPlanQtyMin;
		DefaultSearchPlanQtyMax			= GetSearchPlanQtyMax;
		DefaultSearchActualQtyMin		= GetSearchActualQtyMin;
		DefaultSearchActualQtyMax		= GetSearchActualQtyMax;
		DefaultSearchSpName				= GetSearchSpName;
		DefaultSearchSpPost				= GetSearchSpPost;
		DefaultSearchSpAdd				= GetSearchSpAdd;
		DefaultSearchSpTel				= GetSearchSpTel;
		DefaultSearchFixFg				= GetSearchFixFg;
		DefaultSearchEntryDateMin		= GetSearchEntryDateMin;
		DefaultSearchEntryDateMax		= GetSearchEntryDateMax;
		DefaultSearchUpdateDateMin		= GetSearchUpdateDateMin;
		DefaultSearchUpdateDateMax		= GetSearchUpdateDateMax;
		
		
		
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
		for(int i=0;i<GetArrNoAny.length;i++) {
			if(!"".equals(GetArrNoAny[i]		)){SearchArrNo.add(GetArrNoAny[i]);}
		}
		
		Object[][] ArrivalPlanHdRt = T100_ArrivalPlanHdRt.ArrivalPlanHdRt(
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
		
		return ArrivalPlanHdRt;
	}
	
	//入荷予定明細情報取得
	private static Object[][] ArrivalPlanMsRt(
			ArrayList<String> SearchClWh,
			ArrayList<String> SearchClCd,
			ArrayList<String> SearchClGpCD,
			ArrayList<String> SearchArrNo
			){
		ArrayList<String> SearchCLName01		= new ArrayList<String>();		//ヘッダ荷主名
		ArrayList<String> SearchCLGpName01		= new ArrayList<String>();		//ヘッダ荷主グループ名1
		ArrayList<String> SearchClArrNo			= new ArrayList<String>();		//ヘッダ荷主予定番号
		ArrayList<String> SearchPlanDateMin		= new ArrayList<String>();		//ヘッダ入荷予定日
		ArrayList<String> SearchPlanDateMax		= new ArrayList<String>();		//ヘッダ入荷予定日
		ArrayList<String> SearchHdActualDateMin	= new ArrayList<String>();		//ヘッダ入荷実績日
		ArrayList<String> SearchHdActualDateMax	= new ArrayList<String>();		//ヘッダ入荷実績日
		ArrayList<String> SearchSpCd			= new ArrayList<String>();		//ヘッダ仕入先CD
		ArrayList<String> SearchSpName			= new ArrayList<String>();		//ヘッダ仕入先名
		ArrayList<String> SearchSpPost			= new ArrayList<String>();		//ヘッダ仕入先郵便
		ArrayList<String> SearchSpAdd			= new ArrayList<String>();		//ヘッダ仕入先住所
		ArrayList<String> SearchSpTel			= new ArrayList<String>();		//ヘッダ仕入先電話
		ArrayList<String> SearchArCom			= new ArrayList<String>();		//ヘッダコメント
		ArrayList<Integer> SearchFixFg			= new ArrayList<Integer>();		//ヘッダ状況
				
		ArrayList<Integer> SearchMsNoMin		= new ArrayList<Integer>();		//明細番号最小
		ArrayList<Integer> SearchMsNoMax		= new ArrayList<Integer>();		//明細番号最大
		ArrayList<String> SearchItemCd			= new ArrayList<String>();		//商品コード
		ArrayList<String> SearchClItemCd		= new ArrayList<String>();		//荷主商品コード
		ArrayList<String> SearchJanCd			= new ArrayList<String>();		//JANCD（バラ）
		ArrayList<String> SearchItemMdNo		= new ArrayList<String>();		//商品型番
		ArrayList<String> SearchItemName		= new ArrayList<String>();		//商品名
		ArrayList<String> Searchlot				= new ArrayList<String>();		//ロット
		ArrayList<String> SearchExpDateMin		= new ArrayList<String>();		//消費期限最小
		ArrayList<String> SearchExpDateMax		= new ArrayList<String>();		//消費期限最大
		ArrayList<Integer> SearchPlanQtyMin		= new ArrayList<Integer>();		//予定数量最小
		ArrayList<Integer> SearchPlanQtyMax		= new ArrayList<Integer>();		//予定数量最大
		ArrayList<Integer> SearchActualQtyMin	= new ArrayList<Integer>();		//実績数
		ArrayList<Integer> SearchActualQtyMax	= new ArrayList<Integer>();		//実績数
		ArrayList<String> SearchActualDateMin	= new ArrayList<String>();		//入荷日
		ArrayList<String> SearchActualDateMax	= new ArrayList<String>();		//入荷日
		ArrayList<String> SearchCom				= new ArrayList<String>();		//コメント
		ArrayList<String> SearchEntryDateMin	= new ArrayList<String>();		//登録日
		ArrayList<String> SearchEntryDateMax	= new ArrayList<String>();		//登録日
		ArrayList<String> SearchUpdateDateMin	= new ArrayList<String>();		//更新日
		ArrayList<String> SearchUpdateDateMax	= new ArrayList<String>();		//更新日
		ArrayList<String> SearchEntryUser		= new ArrayList<String>();		//登録者
		ArrayList<String> SearchUpdateUser		= new ArrayList<String>();		//更新者
		boolean AllSearch = false;
		
		
		Object[][] ArrivalPlanMsRt	= T100_ArrivalPlanMsRt.ArrivalPlanMsRt(
				SearchClWh,					//ヘッダ担当倉庫
				SearchClCd,					//ヘッダ荷主CD
				SearchCLName01,				//ヘッダ荷主名
				SearchClGpCD,				//ヘッダ荷主グループCD
				SearchCLGpName01,			//ヘッダ荷主グループ名1
				SearchArrNo,				//ヘッダ入荷予定NO
				SearchClArrNo,				//ヘッダ荷主予定番号
				SearchPlanDateMin,			//ヘッダ入荷予定日
				SearchPlanDateMax,			//ヘッダ入荷予定日
				SearchHdActualDateMin,		//ヘッダ入荷実績日
				SearchHdActualDateMax,		//ヘッダ入荷実績日
				SearchSpCd,					//ヘッダ仕入先CD
				SearchSpName,				//ヘッダ仕入先名
				SearchSpPost,				//ヘッダ仕入先郵便
				SearchSpAdd,				//ヘッダ仕入先住所
				SearchSpTel,				//ヘッダ仕入先電話
				SearchArCom,				//ヘッダコメント
				SearchFixFg,				//ヘッダ状況
						
				SearchMsNoMin,				//明細番号最小
				SearchMsNoMax,				//明細番号最大
				SearchItemCd,				//商品コード
				SearchClItemCd,				//荷主商品コード
				SearchJanCd,				//JANCD（バラ）
				SearchItemMdNo,				//商品型番
				SearchItemName,				//商品名
				Searchlot,					//ロット
				SearchExpDateMin,			//消費期限最小
				SearchExpDateMax,			//消費期限最大
				SearchPlanQtyMin,			//予定数量最小
				SearchPlanQtyMax,			//予定数量最大
				SearchActualQtyMin,			//実績数
				SearchActualQtyMax,			//実績数
				SearchActualDateMin,		//入荷日
				SearchActualDateMax,		//入荷日
				SearchCom,					//コメント
				SearchEntryDateMin,			//登録日
				SearchEntryDateMax,			//登録日
				SearchUpdateDateMin,		//更新日
				SearchUpdateDateMax,		//更新日
				SearchEntryUser,			//登録者
				SearchUpdateUser,			//更新者
				AllSearch);
		
		return ArrivalPlanMsRt;
	}}