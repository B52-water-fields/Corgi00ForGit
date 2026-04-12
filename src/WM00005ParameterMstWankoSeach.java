import java.awt.Font;
import java.awt.event.ActionEvent;
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
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class WM00005ParameterMstWankoSeach{
	static int SetX;
	static int SetY;
	static boolean RenewFg;
	public static void ParameterMstWankoSeach(int x,int y) {
		A00000Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		RenewFg = false;
		
		final JFrame main_fm = B00110FrameParts.FrameCreate(x,y,1200,750,"Corgi00荷主パラメータマスタ（WANKO）検索","");
		JLabel userinfo = B00110FrameParts.UserInfo();
		JButton exit_btn = B00110FrameParts.ExitBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		
		//検索条件パネル
		JPanel PN_Search = B00110FrameParts.JPanelSet(10,40,1180,300,"White");
		JLabel PN_SearchLabel = B00110FrameParts.JLabelSet(10,0,150,20,"検索条件",11,0);
		PN_Search.add(PN_SearchLabel);
		
		JLabel LB_SearchClCd			= B00110FrameParts.JLabelSet(	  								  0, 25,130,20,"荷主:"						,11,1);
		final JComboBox  TB_SearchClCd	= B00110FrameParts.JComboBoxSet(								130, 25,180,20,B00100DefaultVariable.SearchClList[0], 11);
		JLabel LB_SearchWhCd			= B00110FrameParts.JLabelSet(	  								  0, 50,130,20,"担当倉庫:"						,11,1);
		final JComboBox  TB_SearchWhCd	= B00110FrameParts.JComboBoxSet(								130, 50,180,20,B00100DefaultVariable.SearchWhList[0], 11);
		JLabel LB_SearchParaCd			= B00110FrameParts.JLabelSet(	  								  0, 75,130,20,"パラメータコード:"			,11,1);
		final JTextField  TB_SearchParaCd	= B00110FrameParts.JTextFieldSet(							130, 75,100,20,""							,11,0);
		JLabel LB2_SearchParaCd			= B00110FrameParts.JLabelSet(	  								230, 75, 80,20,"と一致"						,11,0);
		JLabel LB_SearchParaCdSeq	= B00110FrameParts.JLabelSet(	  									  0,100,130,20,"シーケンシャルNo:"			,11,1);
		final JFormattedTextField TB_SearchParaCdSeqStr= B00110FrameParts.JFormattedTextFieldSet(	130,100, 80,20,""							,11,1,"####");
		JLabel LB2_SearchParaCdSeq	= B00110FrameParts.JLabelSet(										210,100, 20,20,"～"							,11,2);
		final JFormattedTextField TB_SearchParaCdSeqEnd= B00110FrameParts.JFormattedTextFieldSet(	230,100, 80,20,""							,11,1,"####");
		JLabel LB_SearchParaName		= B00110FrameParts.JLabelSet(									  0,125,130,20,"パラメータ名:"				,11,1);
		final JTextField  TB_SearchParaName= B00110FrameParts.JTextFieldSet(							130,125,100,20,""							,11,0);
		JLabel LB2_SearchParaName		= B00110FrameParts.JLabelSet(									230,125, 80,20,"を含む"						,11,0);
		JLabel LB_SearchParaTxtAll		= B00110FrameParts.JLabelSet(									  0,150,130,20,"文字設定項目のどれかに:"	,10,1);
		final JTextField  TB_SearchParaTxtAll= B00110FrameParts.JTextFieldSet(						130,150,100,20,""							,11,0);
		JLabel LB2_SearchParaTxtAll		= B00110FrameParts.JLabelSet(									230,150, 80,20,"を含む"						,11,0);
		
		
		JLabel LB_SearchParaTxt01		= B00110FrameParts.JLabelSet(					310, 25,130,20,"文字設定項目01:"	,11,1);
		final JTextField  TB_SearchParaTxt01= B00110FrameParts.JTextFieldSet(			440, 25,100,20,""					,11,0);
		JLabel LB2_SearchParaTxt01		= B00110FrameParts.JLabelSet(					540, 25, 80,20,"を含む"				,11,0);
		JLabel LB_SearchParaTxt02		= B00110FrameParts.JLabelSet(					310, 50,130,20,"文字設定項目02:"	,11,1);
		final JTextField  TB_SearchParaTxt02= B00110FrameParts.JTextFieldSet(			440, 50,100,20,""					,11,0);
		JLabel LB2_SearchParaTxt02		= B00110FrameParts.JLabelSet(					540, 50, 80,20,"を含む"				,11,0);
		JLabel LB_SearchParaTxt03		= B00110FrameParts.JLabelSet(					310, 75,130,20,"文字設定項目03:"	,11,1);
		final JTextField  TB_SearchParaTxt03= B00110FrameParts.JTextFieldSet(			440, 75,100,20,""					,11,0);
		JLabel LB2_SearchParaTxt03		= B00110FrameParts.JLabelSet(					540, 75, 80,20,"を含む"				,11,0);
		JLabel LB_SearchParaTxt04		= B00110FrameParts.JLabelSet(					310,100,130,20,"文字設定項目04:"	,11,1);
		final JTextField  TB_SearchParaTxt04= B00110FrameParts.JTextFieldSet(			440,100,100,20,""					,11,0);
		JLabel LB2_SearchParaTxt04		= B00110FrameParts.JLabelSet(					540,100, 80,20,"を含む"				,11,0);
		JLabel LB_SearchParaTxt05		= B00110FrameParts.JLabelSet(					310,125,130,20,"文字設定項目05:"	,11,1);
		final JTextField  TB_SearchParaTxt05= B00110FrameParts.JTextFieldSet(			440,125,100,20,""					,11,0);
		JLabel LB2_SearchParaTxt05		= B00110FrameParts.JLabelSet(					540,125, 80,20,"を含む"				,11,0);
		JLabel LB_SearchParaTxt06		= B00110FrameParts.JLabelSet(					310,150,130,20,"文字設定項目06:"	,11,1);
		final JTextField  TB_SearchParaTxt06= B00110FrameParts.JTextFieldSet(			440,150,100,20,""					,11,0);
		JLabel LB2_SearchParaTxt06		= B00110FrameParts.JLabelSet(					540,150, 80,20,"を含む"				,11,0);
		JLabel LB_SearchParaTxt07		= B00110FrameParts.JLabelSet(					310,175,130,20,"文字設定項目07:"	,11,1);
		final JTextField  TB_SearchParaTxt07= B00110FrameParts.JTextFieldSet(			440,175,100,20,""					,11,0);
		JLabel LB2_SearchParaTxt07		= B00110FrameParts.JLabelSet(					540,175, 80,20,"を含む"				,11,0);
		JLabel LB_SearchParaTxt08		= B00110FrameParts.JLabelSet(					310,200,130,20,"文字設定項目08:"	,11,1);
		final JTextField  TB_SearchParaTxt08= B00110FrameParts.JTextFieldSet(			440,200,100,20,""					,11,0);
		JLabel LB2_SearchParaTxt08		= B00110FrameParts.JLabelSet(					540,200, 80,20,"を含む"				,11,0);
		JLabel LB_SearchParaTxt09		= B00110FrameParts.JLabelSet(					310,225,130,20,"文字設定項目09:"	,11,1);
		final JTextField  TB_SearchParaTxt09= B00110FrameParts.JTextFieldSet(			440,225,100,20,""					,11,0);
		JLabel LB2_SearchParaTxt09		= B00110FrameParts.JLabelSet(					540,225, 80,20,"を含む"				,11,0);
		JLabel LB_SearchParaTxt10		= B00110FrameParts.JLabelSet(					310,250,130,20,"文字設定項目10:"	,11,1);
		final JTextField  TB_SearchParaTxt10= B00110FrameParts.JTextFieldSet(			440,250,100,20,""					,11,0);
		JLabel LB2_SearchParaTxt10		= B00110FrameParts.JLabelSet(					540,250, 80,20,"を含む"				,11,0);
		
		JLabel LB_SearchParaInt01	= B00110FrameParts.JLabelSet(										620, 25,130,20,"数値設定項目01:"	,11,1);
		final JFormattedTextField TB_SearchParaInt01Str= B00110FrameParts.JFormattedTextFieldSet(	750, 25,100,20,""					,11,1,"####");
		JLabel LB2_SearchParaInt01	= B00110FrameParts.JLabelSet(										850, 25, 20,20,"～"					,11,2);
		final JFormattedTextField TB_SearchParaInt01End= B00110FrameParts.JFormattedTextFieldSet(	870, 25,100,20,""					,11,1,"####");
		JLabel LB_SearchParaInt02	= B00110FrameParts.JLabelSet(										620, 50,130,20,"数値設定項目02:"	,11,1);
		final JFormattedTextField TB_SearchParaInt02Str= B00110FrameParts.JFormattedTextFieldSet(	750, 50,100,20,""					,11,1,"####");
		JLabel LB2_SearchParaInt02	= B00110FrameParts.JLabelSet(										850, 50, 20,20,"～"					,11,2);
		final JFormattedTextField TB_SearchParaInt02End= B00110FrameParts.JFormattedTextFieldSet(	870, 50,100,20,""					,11,1,"####");
		JLabel LB_SearchParaInt03	= B00110FrameParts.JLabelSet(										620, 75,130,20,"数値設定項目03:"	,11,1);
		final JFormattedTextField TB_SearchParaInt03Str= B00110FrameParts.JFormattedTextFieldSet(	750, 75,100,20,""					,11,1,"####");
		JLabel LB2_SearchParaInt03	= B00110FrameParts.JLabelSet(										850, 75, 20,20,"～"					,11,2);
		final JFormattedTextField TB_SearchParaInt03End= B00110FrameParts.JFormattedTextFieldSet(	870, 75,100,20,""					,11,1,"####");
		JLabel LB_SearchParaInt04	= B00110FrameParts.JLabelSet(										620,100,130,20,"数値設定項目04:"	,11,1);
		final JFormattedTextField TB_SearchParaInt04Str= B00110FrameParts.JFormattedTextFieldSet(	750,100,100,20,""					,11,1,"####");
		JLabel LB2_SearchParaInt04	= B00110FrameParts.JLabelSet(										850,100, 20,20,"～"					,11,2);
		final JFormattedTextField TB_SearchParaInt04End= B00110FrameParts.JFormattedTextFieldSet(	870,100,100,20,""					,11,1,"####");
		JLabel LB_SearchParaInt05	= B00110FrameParts.JLabelSet(										620,125,130,20,"数値設定項目05:"	,11,1);
		final JFormattedTextField TB_SearchParaInt05Str= B00110FrameParts.JFormattedTextFieldSet(	750,125,100,20,""					,11,1,"####");
		JLabel LB2_SearchParaInt05	= B00110FrameParts.JLabelSet(										850,125, 20,20,"～"					,11,2);
		final JFormattedTextField TB_SearchParaInt05End= B00110FrameParts.JFormattedTextFieldSet(	870,125,100,20,""					,11,1,"####");
		JLabel LB_SearchParaInt06	= B00110FrameParts.JLabelSet(										620,150,130,20,"数値設定項目06:"	,11,1);
		final JFormattedTextField TB_SearchParaInt06Str= B00110FrameParts.JFormattedTextFieldSet(	750,150,100,20,""					,11,1,"####");
		JLabel LB2_SearchParaInt06	= B00110FrameParts.JLabelSet(										850,150, 20,20,"～"					,11,2);
		final JFormattedTextField TB_SearchParaInt06End= B00110FrameParts.JFormattedTextFieldSet(	870,150,100,20,""					,11,1,"####");
		JLabel LB_SearchParaInt07	= B00110FrameParts.JLabelSet(										620,175,130,20,"数値設定項目07:"	,11,1);
		final JFormattedTextField TB_SearchParaInt07Str= B00110FrameParts.JFormattedTextFieldSet(	750,175,100,20,""					,11,1,"####");
		JLabel LB2_SearchParaInt07	= B00110FrameParts.JLabelSet(										850,175, 20,20,"～"					,11,2);
		final JFormattedTextField TB_SearchParaInt07End= B00110FrameParts.JFormattedTextFieldSet(	870,175,100,20,""					,11,1,"####");
		JLabel LB_SearchParaInt08	= B00110FrameParts.JLabelSet(										620,200,130,20,"数値設定項目08:"	,11,1);
		final JFormattedTextField TB_SearchParaInt08Str= B00110FrameParts.JFormattedTextFieldSet(	750,200,100,20,""					,11,1,"####");
		JLabel LB2_SearchParaInt08	= B00110FrameParts.JLabelSet(										850,200, 20,20,"～"					,11,2);
		final JFormattedTextField TB_SearchParaInt08End= B00110FrameParts.JFormattedTextFieldSet(	870,200,100,20,""					,11,1,"####");
		JLabel LB_SearchParaInt09	= B00110FrameParts.JLabelSet(										620,225,130,20,"数値設定項目09:"	,11,1);
		final JFormattedTextField TB_SearchParaInt09Str= B00110FrameParts.JFormattedTextFieldSet(	750,225,100,20,""					,11,1,"####");
		JLabel LB2_SearchParaInt09	= B00110FrameParts.JLabelSet(										850,225, 20,20,"～"					,11,2);
		final JFormattedTextField TB_SearchParaInt09End= B00110FrameParts.JFormattedTextFieldSet(	870,225,100,20,""					,11,1,"####");
		JLabel LB_SearchParaInt10	= B00110FrameParts.JLabelSet(										620,250,130,20,"数値設定項目10:"	,11,1);
		final JFormattedTextField TB_SearchParaInt10Str= B00110FrameParts.JFormattedTextFieldSet(	750,250,100,20,""					,11,1,"####");
		JLabel LB2_SearchParaInt10	= B00110FrameParts.JLabelSet(										850,250, 20,20,"～"					,11,2);
		final JFormattedTextField TB_SearchParaInt10End= B00110FrameParts.JFormattedTextFieldSet(	870,250,100,20,""					,11,1,"####");
		
		//検索ボタン
		JButton SearchBtn = B00110FrameParts.BtnSet(130,200,100,20,"検索",11);
		PN_Search.add(SearchBtn);
		
		//条件クリアボタン
		JButton ClearBtn = B00110FrameParts.BtnSet( 10,200,100,20,"条件クリア",11);
		PN_Search.add(ClearBtn);
		
		for(int i=0;i<B00100DefaultVariable.SearchClList[1].length;i++) {
			if(B00100DefaultVariable.SearchClList[1][i].equals(A00000Main.ClCd)) {
				TB_SearchClCd.setSelectedIndex(i);
			}
		}
		for(int i=0;i<B00100DefaultVariable.SearchWhList[1].length;i++) {
			if(B00100DefaultVariable.SearchWhList[1][i].equals(A00000Main.ClWh)) {
				TB_SearchWhCd.setSelectedIndex(i);
			}
		}
		TB_SearchClCd.setEnabled(false);
		TB_SearchWhCd.setEnabled(false);
		
		PN_Search.add(LB_SearchClCd);
		PN_Search.add(LB_SearchWhCd);
		PN_Search.add(LB_SearchParaCd);
		PN_Search.add(LB_SearchParaCdSeq);
		PN_Search.add(LB2_SearchParaCdSeq);
		PN_Search.add(LB_SearchParaName);
		PN_Search.add(LB_SearchParaTxt01);
		PN_Search.add(LB_SearchParaTxt02);
		PN_Search.add(LB_SearchParaTxt03);
		PN_Search.add(LB_SearchParaTxt04);
		PN_Search.add(LB_SearchParaTxt05);
		PN_Search.add(LB_SearchParaTxt06);
		PN_Search.add(LB_SearchParaTxt07);
		PN_Search.add(LB_SearchParaTxt08);
		PN_Search.add(LB_SearchParaTxt09);
		PN_Search.add(LB_SearchParaTxt10);
		
		PN_Search.add(LB2_SearchParaCd);
		PN_Search.add(LB2_SearchParaName);
		PN_Search.add(LB2_SearchParaTxt01);
		PN_Search.add(LB2_SearchParaTxt02);
		PN_Search.add(LB2_SearchParaTxt03);
		PN_Search.add(LB2_SearchParaTxt04);
		PN_Search.add(LB2_SearchParaTxt05);
		PN_Search.add(LB2_SearchParaTxt06);
		PN_Search.add(LB2_SearchParaTxt07);
		PN_Search.add(LB2_SearchParaTxt08);
		PN_Search.add(LB2_SearchParaTxt09);
		PN_Search.add(LB2_SearchParaTxt10);
		
		PN_Search.add(LB_SearchParaInt01);
		PN_Search.add(LB_SearchParaInt02);
		PN_Search.add(LB_SearchParaInt03);
		PN_Search.add(LB_SearchParaInt04);
		PN_Search.add(LB_SearchParaInt05);
		PN_Search.add(LB_SearchParaInt06);
		PN_Search.add(LB_SearchParaInt07);
		PN_Search.add(LB_SearchParaInt08);
		PN_Search.add(LB_SearchParaInt09);
		PN_Search.add(LB_SearchParaInt10);
		PN_Search.add(LB2_SearchParaInt01);
		PN_Search.add(LB2_SearchParaInt02);
		PN_Search.add(LB2_SearchParaInt03);
		PN_Search.add(LB2_SearchParaInt04);
		PN_Search.add(LB2_SearchParaInt05);
		PN_Search.add(LB2_SearchParaInt06);
		PN_Search.add(LB2_SearchParaInt07);
		PN_Search.add(LB2_SearchParaInt08);
		PN_Search.add(LB2_SearchParaInt09);
		PN_Search.add(LB2_SearchParaInt10);
		
		PN_Search.add(LB_SearchParaTxtAll);
		PN_Search.add(LB2_SearchParaTxtAll);
		
		PN_Search.add(TB_SearchClCd);
		PN_Search.add(TB_SearchWhCd);
		PN_Search.add(TB_SearchParaCd);
		PN_Search.add(TB_SearchParaCdSeqStr);
		PN_Search.add(TB_SearchParaCdSeqEnd);
		PN_Search.add(TB_SearchParaName);
		PN_Search.add(TB_SearchParaTxt01);
		PN_Search.add(TB_SearchParaTxt02);
		PN_Search.add(TB_SearchParaTxt03);
		PN_Search.add(TB_SearchParaTxt04);
		PN_Search.add(TB_SearchParaTxt05);
		PN_Search.add(TB_SearchParaTxt06);
		PN_Search.add(TB_SearchParaTxt07);
		PN_Search.add(TB_SearchParaTxt08);
		PN_Search.add(TB_SearchParaTxt09);
		PN_Search.add(TB_SearchParaTxt10);
		PN_Search.add(TB_SearchParaInt01Str);
		PN_Search.add(TB_SearchParaInt02Str);
		PN_Search.add(TB_SearchParaInt03Str);
		PN_Search.add(TB_SearchParaInt04Str);
		PN_Search.add(TB_SearchParaInt05Str);
		PN_Search.add(TB_SearchParaInt06Str);
		PN_Search.add(TB_SearchParaInt07Str);
		PN_Search.add(TB_SearchParaInt08Str);
		PN_Search.add(TB_SearchParaInt09Str);
		PN_Search.add(TB_SearchParaInt10Str);
		PN_Search.add(TB_SearchParaInt01End);
		PN_Search.add(TB_SearchParaInt02End);
		PN_Search.add(TB_SearchParaInt03End);
		PN_Search.add(TB_SearchParaInt04End);
		PN_Search.add(TB_SearchParaInt05End);
		PN_Search.add(TB_SearchParaInt06End);
		PN_Search.add(TB_SearchParaInt07End);
		PN_Search.add(TB_SearchParaInt08End);
		PN_Search.add(TB_SearchParaInt09End);
		PN_Search.add(TB_SearchParaInt10End);
		PN_Search.add(TB_SearchParaTxtAll);
		
		main_fm.add(PN_Search);
		
		
		//検索結果
		Object[][] RtParameterMstWankoRt = M00000ParameterMstWankoRt.RtParameterMstWankoRt();
		
		String[] columnNames01 = new String[RtParameterMstWankoRt.length+1];
		
		columnNames01[0] = "Fg";
		for(int i=0;i<RtParameterMstWankoRt.length;i++) {
			columnNames01[1+i] = ""+RtParameterMstWankoRt[i][3];
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
		
		for(int i=0;i<RtParameterMstWankoRt.length;i++) {
			if("int".equals((String)RtParameterMstWankoRt[i][2])||"float".equals((String)RtParameterMstWankoRt[i][2])){
				column = columnModel01.getColumn(1+i);	column.setPreferredWidth( 90*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.rightCellRenderer());
			}else {
				column = columnModel01.getColumn(1+i);	column.setPreferredWidth( 90*A00000Main.Mul/A00000Main.Div);	column.setCellRenderer(B00110FrameParts.leftCellRenderer());
			}
			
		}
		
		//スクロール用設定
		JScrollPane scpn01 = B00110FrameParts.JScrollPaneSet(10,350,1180,280,tb01);
		main_fm.add(scpn01);
		
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
		
		//Excel出力ボタン
		JButton ExcelBtn = B00110FrameParts.BtnSet(		370,660,100,20,"Excel出力",11);
		main_fm.add(ExcelBtn);
		
		//Excel取込ボタン
		JButton ExcelEntryBtn = B00110FrameParts.BtnSet(	490,660,100,20,"Excel取込",11);
		main_fm.add(ExcelEntryBtn);
		
		main_fm.setVisible(true);
		RenewFg = true;
		
		//検索ボタン押下時の挙動
		SearchBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					
					int RowCount = tableModel_ms01.getRowCount();
					for(int i=0;i<RowCount;i++) {
						tableModel_ms01.removeRow(0);
					}
					
					String GetSearchClCd			= B00100DefaultVariable.SearchClList[1][TB_SearchClCd.getSelectedIndex()];
					String GetSearchWhCd			= B00100DefaultVariable.SearchWhList[1][TB_SearchWhCd.getSelectedIndex()];
					String GetSearchParaCd			= TB_SearchParaCd.getText();
					String GetSearchParaCdSeqStr	= TB_SearchParaCdSeqStr.getText();
					String GetSearchParaCdSeqEnd	= TB_SearchParaCdSeqEnd.getText();
					String GetSearchParaName		= TB_SearchParaName.getText();
					String GetSearchParaTxt01		= TB_SearchParaTxt01.getText();
					String GetSearchParaTxt02		= TB_SearchParaTxt02.getText();
					String GetSearchParaTxt03		= TB_SearchParaTxt03.getText();
					String GetSearchParaTxt04		= TB_SearchParaTxt04.getText();
					String GetSearchParaTxt05		= TB_SearchParaTxt05.getText();
					String GetSearchParaTxt06		= TB_SearchParaTxt06.getText();
					String GetSearchParaTxt07		= TB_SearchParaTxt07.getText();
					String GetSearchParaTxt08		= TB_SearchParaTxt08.getText();
					String GetSearchParaTxt09		= TB_SearchParaTxt09.getText();
					String GetSearchParaTxt10		= TB_SearchParaTxt10.getText();
					String GetSearchParaInt01Str	= TB_SearchParaInt01Str.getText();
					String GetSearchParaInt02Str	= TB_SearchParaInt02Str.getText();
					String GetSearchParaInt03Str	= TB_SearchParaInt03Str.getText();
					String GetSearchParaInt04Str	= TB_SearchParaInt04Str.getText();
					String GetSearchParaInt05Str	= TB_SearchParaInt05Str.getText();
					String GetSearchParaInt06Str	= TB_SearchParaInt06Str.getText();
					String GetSearchParaInt07Str	= TB_SearchParaInt07Str.getText();
					String GetSearchParaInt08Str	= TB_SearchParaInt08Str.getText();
					String GetSearchParaInt09Str	= TB_SearchParaInt09Str.getText();
					String GetSearchParaInt10Str	= TB_SearchParaInt10Str.getText();
					String GetSearchParaInt01End	= TB_SearchParaInt01End.getText();
					String GetSearchParaInt02End	= TB_SearchParaInt02End.getText();
					String GetSearchParaInt03End	= TB_SearchParaInt03End.getText();
					String GetSearchParaInt04End	= TB_SearchParaInt04End.getText();
					String GetSearchParaInt05End	= TB_SearchParaInt05End.getText();
					String GetSearchParaInt06End	= TB_SearchParaInt06End.getText();
					String GetSearchParaInt07End	= TB_SearchParaInt07End.getText();
					String GetSearchParaInt08End	= TB_SearchParaInt08End.getText();
					String GetSearchParaInt09End	= TB_SearchParaInt09End.getText();
					String GetSearchParaInt10End	= TB_SearchParaInt10End.getText();
					String GetSearchParaTxtAll		= TB_SearchParaTxtAll.getText();
					
					if(null== GetSearchClCd			){ GetSearchClCd="";}
					if(null== GetSearchWhCd			){ GetSearchWhCd="";}
					if(null== GetSearchParaCd		){ GetSearchParaCd="";}
					if(null== GetSearchParaCdSeqStr	){ GetSearchParaCdSeqStr="";}
					if(null== GetSearchParaCdSeqEnd	){ GetSearchParaCdSeqEnd="";}
					if(null== GetSearchParaName		){ GetSearchParaName="";}
					if(null== GetSearchParaTxt01	){ GetSearchParaTxt01="";}
					if(null== GetSearchParaTxt02	){ GetSearchParaTxt02="";}
					if(null== GetSearchParaTxt03	){ GetSearchParaTxt03="";}
					if(null== GetSearchParaTxt04	){ GetSearchParaTxt04="";}
					if(null== GetSearchParaTxt05	){ GetSearchParaTxt05="";}
					if(null== GetSearchParaTxt06	){ GetSearchParaTxt06="";}
					if(null== GetSearchParaTxt07	){ GetSearchParaTxt07="";}
					if(null== GetSearchParaTxt08	){ GetSearchParaTxt08="";}
					if(null== GetSearchParaTxt09	){ GetSearchParaTxt09="";}
					if(null== GetSearchParaTxt10	){ GetSearchParaTxt10="";}
					if(null== GetSearchParaInt01Str	){ GetSearchParaInt01Str="";}
					if(null== GetSearchParaInt02Str	){ GetSearchParaInt02Str="";}
					if(null== GetSearchParaInt03Str	){ GetSearchParaInt03Str="";}
					if(null== GetSearchParaInt04Str	){ GetSearchParaInt04Str="";}
					if(null== GetSearchParaInt05Str	){ GetSearchParaInt05Str="";}
					if(null== GetSearchParaInt06Str	){ GetSearchParaInt06Str="";}
					if(null== GetSearchParaInt07Str	){ GetSearchParaInt07Str="";}
					if(null== GetSearchParaInt08Str	){ GetSearchParaInt08Str="";}
					if(null== GetSearchParaInt09Str	){ GetSearchParaInt09Str="";}
					if(null== GetSearchParaInt10Str	){ GetSearchParaInt10Str="";}
					if(null== GetSearchParaInt01End	){ GetSearchParaInt01End="";}
					if(null== GetSearchParaInt02End	){ GetSearchParaInt02End="";}
					if(null== GetSearchParaInt03End	){ GetSearchParaInt03End="";}
					if(null== GetSearchParaInt04End	){ GetSearchParaInt04End="";}
					if(null== GetSearchParaInt05End	){ GetSearchParaInt05End="";}
					if(null== GetSearchParaInt06End	){ GetSearchParaInt06End="";}
					if(null== GetSearchParaInt07End	){ GetSearchParaInt07End="";}
					if(null== GetSearchParaInt08End	){ GetSearchParaInt08End="";}
					if(null== GetSearchParaInt09End	){ GetSearchParaInt09End="";}
					if(null== GetSearchParaInt10End	){ GetSearchParaInt10End="";}
					if(null== GetSearchParaTxtAll	){ GetSearchParaTxtAll="";}
					
					GetSearchClCd			= B00020ToolsTextControl.Trim(GetSearchClCd);
					GetSearchWhCd			= B00020ToolsTextControl.Trim(GetSearchWhCd);
					GetSearchParaCd			= B00020ToolsTextControl.Trim(GetSearchParaCd);
					GetSearchParaCdSeqStr	= B00020ToolsTextControl.Trim(GetSearchParaCdSeqStr);
					GetSearchParaCdSeqEnd	= B00020ToolsTextControl.Trim(GetSearchParaCdSeqEnd);
					GetSearchParaName		= B00020ToolsTextControl.Trim(GetSearchParaName);
					GetSearchParaTxt01		= B00020ToolsTextControl.Trim(GetSearchParaTxt01);
					GetSearchParaTxt02		= B00020ToolsTextControl.Trim(GetSearchParaTxt02);
					GetSearchParaTxt03		= B00020ToolsTextControl.Trim(GetSearchParaTxt03);
					GetSearchParaTxt04		= B00020ToolsTextControl.Trim(GetSearchParaTxt04);
					GetSearchParaTxt05		= B00020ToolsTextControl.Trim(GetSearchParaTxt05);
					GetSearchParaTxt06		= B00020ToolsTextControl.Trim(GetSearchParaTxt06);
					GetSearchParaTxt07		= B00020ToolsTextControl.Trim(GetSearchParaTxt07);
					GetSearchParaTxt08		= B00020ToolsTextControl.Trim(GetSearchParaTxt08);
					GetSearchParaTxt09		= B00020ToolsTextControl.Trim(GetSearchParaTxt09);
					GetSearchParaTxt10		= B00020ToolsTextControl.Trim(GetSearchParaTxt10);
					GetSearchParaInt01Str	= B00020ToolsTextControl.Trim(GetSearchParaInt01Str);
					GetSearchParaInt02Str	= B00020ToolsTextControl.Trim(GetSearchParaInt02Str);
					GetSearchParaInt03Str	= B00020ToolsTextControl.Trim(GetSearchParaInt03Str);
					GetSearchParaInt04Str	= B00020ToolsTextControl.Trim(GetSearchParaInt04Str);
					GetSearchParaInt05Str	= B00020ToolsTextControl.Trim(GetSearchParaInt05Str);
					GetSearchParaInt06Str	= B00020ToolsTextControl.Trim(GetSearchParaInt06Str);
					GetSearchParaInt07Str	= B00020ToolsTextControl.Trim(GetSearchParaInt07Str);
					GetSearchParaInt08Str	= B00020ToolsTextControl.Trim(GetSearchParaInt08Str);
					GetSearchParaInt09Str	= B00020ToolsTextControl.Trim(GetSearchParaInt09Str);
					GetSearchParaInt10Str	= B00020ToolsTextControl.Trim(GetSearchParaInt10Str);
					GetSearchParaInt01End	= B00020ToolsTextControl.Trim(GetSearchParaInt01End);
					GetSearchParaInt02End	= B00020ToolsTextControl.Trim(GetSearchParaInt02End);
					GetSearchParaInt03End	= B00020ToolsTextControl.Trim(GetSearchParaInt03End);
					GetSearchParaInt04End	= B00020ToolsTextControl.Trim(GetSearchParaInt04End);
					GetSearchParaInt05End	= B00020ToolsTextControl.Trim(GetSearchParaInt05End);
					GetSearchParaInt06End	= B00020ToolsTextControl.Trim(GetSearchParaInt06End);
					GetSearchParaInt07End	= B00020ToolsTextControl.Trim(GetSearchParaInt07End);
					GetSearchParaInt08End	= B00020ToolsTextControl.Trim(GetSearchParaInt08End);
					GetSearchParaInt09End	= B00020ToolsTextControl.Trim(GetSearchParaInt09End);
					GetSearchParaInt10End	= B00020ToolsTextControl.Trim(GetSearchParaInt10End);
					GetSearchParaTxtAll		= B00020ToolsTextControl.Trim(GetSearchParaTxtAll);
					
					GetSearchParaCdSeqStr	= B00020ToolsTextControl.num_only_String02(GetSearchParaCdSeqStr);
					GetSearchParaCdSeqEnd	= B00020ToolsTextControl.num_only_String02(GetSearchParaCdSeqEnd);
					GetSearchParaInt01Str	= B00020ToolsTextControl.num_only_String02(GetSearchParaInt01Str);
					GetSearchParaInt02Str	= B00020ToolsTextControl.num_only_String02(GetSearchParaInt02Str);
					GetSearchParaInt03Str	= B00020ToolsTextControl.num_only_String02(GetSearchParaInt03Str);
					GetSearchParaInt04Str	= B00020ToolsTextControl.num_only_String02(GetSearchParaInt04Str);
					GetSearchParaInt05Str	= B00020ToolsTextControl.num_only_String02(GetSearchParaInt05Str);
					GetSearchParaInt06Str	= B00020ToolsTextControl.num_only_String02(GetSearchParaInt06Str);
					GetSearchParaInt07Str	= B00020ToolsTextControl.num_only_String02(GetSearchParaInt07Str);
					GetSearchParaInt08Str	= B00020ToolsTextControl.num_only_String02(GetSearchParaInt08Str);
					GetSearchParaInt09Str	= B00020ToolsTextControl.num_only_String02(GetSearchParaInt09Str);
					GetSearchParaInt10Str	= B00020ToolsTextControl.num_only_String02(GetSearchParaInt10Str);
					GetSearchParaInt01End	= B00020ToolsTextControl.num_only_String02(GetSearchParaInt01End);
					GetSearchParaInt02End	= B00020ToolsTextControl.num_only_String02(GetSearchParaInt02End);
					GetSearchParaInt03End	= B00020ToolsTextControl.num_only_String02(GetSearchParaInt03End);
					GetSearchParaInt04End	= B00020ToolsTextControl.num_only_String02(GetSearchParaInt04End);
					GetSearchParaInt05End	= B00020ToolsTextControl.num_only_String02(GetSearchParaInt05End);
					GetSearchParaInt06End	= B00020ToolsTextControl.num_only_String02(GetSearchParaInt06End);
					GetSearchParaInt07End	= B00020ToolsTextControl.num_only_String02(GetSearchParaInt07End);
					GetSearchParaInt08End	= B00020ToolsTextControl.num_only_String02(GetSearchParaInt08End);
					GetSearchParaInt09End	= B00020ToolsTextControl.num_only_String02(GetSearchParaInt09End);
					GetSearchParaInt10End	= B00020ToolsTextControl.num_only_String02(GetSearchParaInt10End);
					
					ArrayList<String> SearchClWh 			= new ArrayList<String>();
					ArrayList<String> SearchClCd 			= new ArrayList<String>();
					ArrayList<String> SearchParaCd 			= new ArrayList<String>();	
					ArrayList<Integer> SearchParaCdSeqStr	= new ArrayList<Integer>();
					ArrayList<Integer> SearchParaCdSeqEnd	= new ArrayList<Integer>();
					ArrayList<String> SearchParaName 		= new ArrayList<String>();
					ArrayList<String> SearchParaTxt01		= new ArrayList<String>();
					ArrayList<String> SearchParaTxt02		= new ArrayList<String>();
					ArrayList<String> SearchParaTxt03		= new ArrayList<String>();
					ArrayList<String> SearchParaTxt04		= new ArrayList<String>();
					ArrayList<String> SearchParaTxt05		= new ArrayList<String>();
					ArrayList<String> SearchParaTxt06		= new ArrayList<String>();
					ArrayList<String> SearchParaTxt07		= new ArrayList<String>();
					ArrayList<String> SearchParaTxt08		= new ArrayList<String>();
					ArrayList<String> SearchParaTxt09		= new ArrayList<String>();
					ArrayList<String> SearchParaTxt10		= new ArrayList<String>();
					ArrayList<Integer> SearchParaInt01Str	= new ArrayList<Integer>();
					ArrayList<Integer> SearchParaInt02Str	= new ArrayList<Integer>();
					ArrayList<Integer> SearchParaInt03Str	= new ArrayList<Integer>();
					ArrayList<Integer> SearchParaInt04Str	= new ArrayList<Integer>();
					ArrayList<Integer> SearchParaInt05Str	= new ArrayList<Integer>();
					ArrayList<Integer> SearchParaInt06Str	= new ArrayList<Integer>();
					ArrayList<Integer> SearchParaInt07Str	= new ArrayList<Integer>();
					ArrayList<Integer> SearchParaInt08Str	= new ArrayList<Integer>();
					ArrayList<Integer> SearchParaInt09Str	= new ArrayList<Integer>();
					ArrayList<Integer> SearchParaInt10Str	= new ArrayList<Integer>();
					ArrayList<Integer> SearchParaInt01End	= new ArrayList<Integer>();
					ArrayList<Integer> SearchParaInt02End	= new ArrayList<Integer>();
					ArrayList<Integer> SearchParaInt03End	= new ArrayList<Integer>();
					ArrayList<Integer> SearchParaInt04End	= new ArrayList<Integer>();
					ArrayList<Integer> SearchParaInt05End	= new ArrayList<Integer>();
					ArrayList<Integer> SearchParaInt06End	= new ArrayList<Integer>();
					ArrayList<Integer> SearchParaInt07End	= new ArrayList<Integer>();
					ArrayList<Integer> SearchParaInt08End	= new ArrayList<Integer>();
					ArrayList<Integer> SearchParaInt09End	= new ArrayList<Integer>();
					ArrayList<Integer> SearchParaInt10End	= new ArrayList<Integer>();
					ArrayList<String> SearchParaTxtAll = new ArrayList<String>();
					Boolean AllSearch = true;
					
					if(!"".equals(GetSearchWhCd			)){SearchClWh.add(GetSearchWhCd);}
					if(!"".equals(GetSearchClCd			)){SearchClCd.add(GetSearchClCd);}
					if(!"".equals(GetSearchParaCd		)){SearchParaCd.add(GetSearchParaCd);}
					if(!"".equals(GetSearchParaCdSeqStr	)){SearchParaCdSeqStr.add(Integer.parseInt(GetSearchParaCdSeqStr));}
					if(!"".equals(GetSearchParaCdSeqEnd	)){SearchParaCdSeqEnd.add(Integer.parseInt(GetSearchParaCdSeqEnd));}
					if(!"".equals(GetSearchParaName		)){SearchParaName.add(GetSearchParaName);}
					if(!"".equals(GetSearchParaTxt01	)){SearchParaTxt01.add(GetSearchParaTxt01);}
					if(!"".equals(GetSearchParaTxt02	)){SearchParaTxt02.add(GetSearchParaTxt02);}
					if(!"".equals(GetSearchParaTxt03	)){SearchParaTxt03.add(GetSearchParaTxt03);}
					if(!"".equals(GetSearchParaTxt04	)){SearchParaTxt04.add(GetSearchParaTxt04);}
					if(!"".equals(GetSearchParaTxt05	)){SearchParaTxt05.add(GetSearchParaTxt05);}
					if(!"".equals(GetSearchParaTxt06	)){SearchParaTxt06.add(GetSearchParaTxt06);}
					if(!"".equals(GetSearchParaTxt07	)){SearchParaTxt07.add(GetSearchParaTxt07);}
					if(!"".equals(GetSearchParaTxt08	)){SearchParaTxt08.add(GetSearchParaTxt08);}
					if(!"".equals(GetSearchParaTxt09	)){SearchParaTxt09.add(GetSearchParaTxt09);}
					if(!"".equals(GetSearchParaTxt10	)){SearchParaTxt10.add(GetSearchParaTxt10);}
					if(!"".equals(GetSearchParaInt01Str	)){SearchParaInt01Str.add(Integer.parseInt(GetSearchParaInt01Str));}
					if(!"".equals(GetSearchParaInt02Str	)){SearchParaInt02Str.add(Integer.parseInt(GetSearchParaInt02Str));}
					if(!"".equals(GetSearchParaInt03Str	)){SearchParaInt03Str.add(Integer.parseInt(GetSearchParaInt03Str));}
					if(!"".equals(GetSearchParaInt04Str	)){SearchParaInt04Str.add(Integer.parseInt(GetSearchParaInt04Str));}
					if(!"".equals(GetSearchParaInt05Str	)){SearchParaInt05Str.add(Integer.parseInt(GetSearchParaInt05Str));}
					if(!"".equals(GetSearchParaInt06Str	)){SearchParaInt06Str.add(Integer.parseInt(GetSearchParaInt06Str));}
					if(!"".equals(GetSearchParaInt07Str	)){SearchParaInt07Str.add(Integer.parseInt(GetSearchParaInt07Str));}
					if(!"".equals(GetSearchParaInt08Str	)){SearchParaInt08Str.add(Integer.parseInt(GetSearchParaInt08Str));}
					if(!"".equals(GetSearchParaInt09Str	)){SearchParaInt09Str.add(Integer.parseInt(GetSearchParaInt09Str));}
					if(!"".equals(GetSearchParaInt10Str	)){SearchParaInt10Str.add(Integer.parseInt(GetSearchParaInt10Str));}
					if(!"".equals(GetSearchParaInt01End	)){SearchParaInt01End.add(Integer.parseInt(GetSearchParaInt01End));}
					if(!"".equals(GetSearchParaInt02End	)){SearchParaInt02End.add(Integer.parseInt(GetSearchParaInt02End));}
					if(!"".equals(GetSearchParaInt03End	)){SearchParaInt03End.add(Integer.parseInt(GetSearchParaInt03End));}
					if(!"".equals(GetSearchParaInt04End	)){SearchParaInt04End.add(Integer.parseInt(GetSearchParaInt04End));}
					if(!"".equals(GetSearchParaInt05End	)){SearchParaInt05End.add(Integer.parseInt(GetSearchParaInt05End));}
					if(!"".equals(GetSearchParaInt06End	)){SearchParaInt06End.add(Integer.parseInt(GetSearchParaInt06End));}
					if(!"".equals(GetSearchParaInt07End	)){SearchParaInt07End.add(Integer.parseInt(GetSearchParaInt07End));}
					if(!"".equals(GetSearchParaInt08End	)){SearchParaInt08End.add(Integer.parseInt(GetSearchParaInt08End));}
					if(!"".equals(GetSearchParaInt09End	)){SearchParaInt09End.add(Integer.parseInt(GetSearchParaInt09End));}
					if(!"".equals(GetSearchParaInt10End	)){SearchParaInt10End.add(Integer.parseInt(GetSearchParaInt10End));}
					if(!"".equals(GetSearchParaTxtAll	)){SearchParaTxtAll.add(GetSearchParaTxtAll);}
					
					Object[][] ParameterMstWankoRt = M00000ParameterMstWankoRt.ParameterMstWankoRt(
							SearchClWh,SearchClCd,
							SearchParaCd,SearchParaCdSeqStr,SearchParaCdSeqEnd,SearchParaName,
							SearchParaTxt01,SearchParaTxt02,SearchParaTxt03,SearchParaTxt04,SearchParaTxt05,
							SearchParaTxt06,SearchParaTxt07,SearchParaTxt08,SearchParaTxt09,SearchParaTxt10,
							SearchParaInt01Str,SearchParaInt02Str,SearchParaInt03Str,SearchParaInt04Str,SearchParaInt05Str,
							SearchParaInt06Str,SearchParaInt07Str,SearchParaInt08Str,SearchParaInt09Str,SearchParaInt10Str,
							SearchParaInt01End,SearchParaInt02End,SearchParaInt03End,SearchParaInt04End,SearchParaInt05End,
							SearchParaInt06End,SearchParaInt07End,SearchParaInt08End,SearchParaInt09End,SearchParaInt10End,
							SearchParaTxtAll,
							AllSearch);
					
					for(int i=0;i<ParameterMstWankoRt.length;i++) {
						Object[] SetOb = new Object[ParameterMstWankoRt[i].length+1];
						SetOb[0] = false;
						for(int i01=0;i01<ParameterMstWankoRt[i].length;i01++) {
							SetOb[i01+1] = ""+ParameterMstWankoRt[i][i01];
						}
						tableModel_ms01.addRow(SetOb);
					}
					if(0<ParameterMstWankoRt.length) {
						B10010TableControl.AddSortON(tb01,tableModel_ms01);
					}else {
						B10010TableControl.AddSortOFF(tb01,tableModel_ms01);
					}
					
					RenewFg = true;
				}
			}
		});
		
		//修正ボタン押下時の挙動
		RenewBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					int RowCount = tableModel_ms01.getRowCount();
					String TgtWhCd = "";
					String TgtClCd = "";
					String TgtParaCd = "";
					String TgtParaCdSeq = "";
					
					for(int i=0;i<RowCount;i++) {
						if((boolean)tableModel_ms01.getValueAt(i, 0)) {
							TgtWhCd 		= ""+tableModel_ms01.getValueAt(i, M00000ParameterMstWankoRt.ColClWh+1);
							TgtClCd			= ""+tableModel_ms01.getValueAt(i, M00000ParameterMstWankoRt.ColClCd+1);
							TgtParaCd 		= ""+tableModel_ms01.getValueAt(i, M00000ParameterMstWankoRt.ColParaCd+1);
							TgtParaCdSeq	= ""+tableModel_ms01.getValueAt(i, M00000ParameterMstWankoRt.ColParaCdSeq+1);
						}
					}
					if(!"".equals(TgtParaCd) && !"".equals(TgtParaCdSeq)) {
						SetX=main_fm.getX();
						SetY=main_fm.getY();
		
						main_fm.setVisible(false);
						main_fm.dispose();
						
						WM00006ParameterMstWankoRenewAndCreate.ParameterMstWankoRenewAndCreate(0,0,TgtWhCd,TgtClCd,TgtParaCd,TgtParaCdSeq);
					}
					RenewFg = true;
				}
			}
		});
		
		//新規登録ボタン押下時の挙動
		CreateBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					SetX=main_fm.getX();
					SetY=main_fm.getY();
	
					main_fm.setVisible(false);
					main_fm.dispose();
					
					WM00006ParameterMstWankoRenewAndCreate.ParameterMstWankoRenewAndCreate(0,0,"","","","");
					RenewFg = true;
				}
			}
		});
		
		//Excel取込ボタン押下時の挙動
		ExcelEntryBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					String MSG = "エクセルファイル選択";
					String[] file_type = {".xlsx"};
					String file_type_name = "エクセルファイル";
					String Selected = B00090FileSelect.FileSelect(MSG,file_type,file_type_name);
					
					if(null!=Selected && !Selected.equals(Selected.replace(".xlsx", ""))) {
						SetX=main_fm.getX();
						SetY=main_fm.getY();

						main_fm.setVisible(false);
						main_fm.dispose();
						WM00007ParameterMstWankoExcelEntry.ParameterMstWankoExcelEntry(0,0,Selected);
					}
					RenewFg = true;
				}
			}
		});
		
		//条件クリアボタン押下時の挙動
		ClearBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					for(int i=0;i<B00100DefaultVariable.SearchClList[1].length;i++) {
						if(B00100DefaultVariable.SearchClList[1][i].equals(A00000Main.ClCd)) {
							TB_SearchClCd.setSelectedIndex(i);
						}
					}
					for(int i=0;i<B00100DefaultVariable.SearchWhList[1].length;i++) {
						if(B00100DefaultVariable.SearchWhList[1][i].equals(A00000Main.ClWh)) {
							TB_SearchWhCd.setSelectedIndex(i);
						}
					}
					TB_SearchParaCd.setText("");
					TB_SearchParaCdSeqStr.setText("");
					TB_SearchParaCdSeqEnd.setText("");
					TB_SearchParaName.setText("");
					TB_SearchParaTxt01.setText("");
					TB_SearchParaTxt02.setText("");
					TB_SearchParaTxt03.setText("");
					TB_SearchParaTxt04.setText("");
					TB_SearchParaTxt05.setText("");
					TB_SearchParaTxt06.setText("");
					TB_SearchParaTxt07.setText("");
					TB_SearchParaTxt08.setText("");
					TB_SearchParaTxt09.setText("");
					TB_SearchParaTxt10.setText("");
					TB_SearchParaInt01Str.setText("");
					TB_SearchParaInt02Str.setText("");
					TB_SearchParaInt03Str.setText("");
					TB_SearchParaInt04Str.setText("");
					TB_SearchParaInt05Str.setText("");
					TB_SearchParaInt06Str.setText("");
					TB_SearchParaInt07Str.setText("");
					TB_SearchParaInt08Str.setText("");
					TB_SearchParaInt09Str.setText("");
					TB_SearchParaInt10Str.setText("");
					TB_SearchParaInt01End.setText("");
					TB_SearchParaInt02End.setText("");
					TB_SearchParaInt03End.setText("");
					TB_SearchParaInt04End.setText("");
					TB_SearchParaInt05End.setText("");
					TB_SearchParaInt06End.setText("");
					TB_SearchParaInt07End.setText("");
					TB_SearchParaInt08End.setText("");
					TB_SearchParaInt09End.setText("");
					TB_SearchParaInt10End.setText("");
					TB_SearchParaTxtAll.setText("");
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
					B10010TableControl.TableOutPutCsv("出力先選択","荷主パラメータマスタ検索結果",tb01);
					RenewFg = true;
				}
			}
		});
		
		//エクセル出力ボタン押下時の挙動
		ExcelBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				if(RenewFg) {
					RenewFg = false;
					B10010TableControl.TableOutPutExcel("出力先選択","荷主パラメータマスタ検索結果",tb01);
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
				W00020MstMain.MstMain(0, 0);
			}
		});
	}
}