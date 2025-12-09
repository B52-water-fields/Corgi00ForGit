import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class WM10012PostMstCreateSum{
	static int SetX;
	static int SetY;
	public static void aa(int x,int y) {
		final JFrame AnyEntry_fm = new JFrame();
		//ウィンドウタイトルの設定
		AnyEntry_fm.setTitle("郵便番号マスタ一括登録");
		//表示位置サイズの設定（表示横位置,表示縦位置,横幅,縦幅）
		AnyEntry_fm.setBounds(x+10, y+10,900*A00000Main.Mul/A00000Main.Div, 720*A00000Main.Mul/A00000Main.Div);
		//レイアウト無効
		AnyEntry_fm.setLayout(null);
		//ウィンドウの閉じるボタンでプログラム終了しない
		//閉じるボタンでDBのコネクション閉じてから終了させたい為
		AnyEntry_fm.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		JLabel LB_EntryPost = new JLabel("郵便番号");
		JLabel LB_EntryPref = new JLabel("県");
		JLabel LB_EntryMunic01 = new JLabel("市区町村");
		JLabel LB_EntryMunic02 = new JLabel("町丁目");
		JLabel LB_EntryMunicipalityCd= new JLabel("市区町村CD");
		
		final JTextArea TB_EntryPost = new JTextArea();				//郵便番号
		final JTextArea TB_EntryPref = new JTextArea();				//県
		final JTextArea TB_EntryMunic01 = new JTextArea();			//市区町村
		final JTextArea TB_EntryMunic02 = new JTextArea();			//町丁目
		final JTextArea TB_EntryMunicipalityCd = new JTextArea();	//市区町村CD
		
		//スクロール用設定
		JScrollPane Scpn_EntryPost = new JScrollPane(TB_EntryPost);						//郵便番号
		JScrollPane Scpn_EntryPref = new JScrollPane(TB_EntryPref);						//県
		JScrollPane Scpn_EntryMunic01 = new JScrollPane(TB_EntryMunic01);				//市区町村
		JScrollPane Scpn_EntryMunic02 = new JScrollPane(TB_EntryMunic02);				//町丁目
		JScrollPane Scpn_EntryMunicipalityCd = new JScrollPane(TB_EntryMunicipalityCd);	//市区町村CD
		
		Scpn_EntryPost.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);			//郵便番号
		Scpn_EntryPref.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);			//県
		Scpn_EntryMunic01.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);		//市区町村
		Scpn_EntryMunic02.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);		//町丁目
		Scpn_EntryMunicipalityCd.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);	//市区町村CD
		
		Scpn_EntryPost.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);			//郵便番号
		Scpn_EntryPref.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);			//県
		Scpn_EntryMunic01.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);		//市区町村
		Scpn_EntryMunic02.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);		//町丁目
		Scpn_EntryMunicipalityCd.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);	//市区町村CD
		
		LB_EntryPost.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 11*A00000Main.Mul/A00000Main.Div));
		LB_EntryPref.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 11*A00000Main.Mul/A00000Main.Div));
		LB_EntryMunic01.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 11*A00000Main.Mul/A00000Main.Div));
		LB_EntryMunic02.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 11*A00000Main.Mul/A00000Main.Div));
		LB_EntryMunicipalityCd.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 11*A00000Main.Mul/A00000Main.Div));
		
		LB_EntryPost.setHorizontalAlignment(JLabel.CENTER);
		LB_EntryPref.setHorizontalAlignment(JLabel.CENTER);
		LB_EntryMunic01.setHorizontalAlignment(JLabel.CENTER);
		LB_EntryMunic02.setHorizontalAlignment(JLabel.CENTER);
		LB_EntryMunicipalityCd.setHorizontalAlignment(JLabel.CENTER);
		
		LB_EntryPost.setBounds(            10*A00000Main.Mul/A00000Main.Div,40*A00000Main.Mul/A00000Main.Div,100*A00000Main.Mul/A00000Main.Div, 20*A00000Main.Mul/A00000Main.Div);
		LB_EntryPref.setBounds(           120*A00000Main.Mul/A00000Main.Div,40*A00000Main.Mul/A00000Main.Div,200*A00000Main.Mul/A00000Main.Div, 20*A00000Main.Mul/A00000Main.Div);
		LB_EntryMunic01.setBounds(        330*A00000Main.Mul/A00000Main.Div,40*A00000Main.Mul/A00000Main.Div,200*A00000Main.Mul/A00000Main.Div, 20*A00000Main.Mul/A00000Main.Div);
		LB_EntryMunic02.setBounds(        540*A00000Main.Mul/A00000Main.Div,40*A00000Main.Mul/A00000Main.Div,200*A00000Main.Mul/A00000Main.Div, 20*A00000Main.Mul/A00000Main.Div);
		LB_EntryMunicipalityCd.setBounds( 750*A00000Main.Mul/A00000Main.Div,40*A00000Main.Mul/A00000Main.Div,100*A00000Main.Mul/A00000Main.Div, 20*A00000Main.Mul/A00000Main.Div);
		
		Scpn_EntryPost.setBounds(            10*A00000Main.Mul/A00000Main.Div,65*A00000Main.Mul/A00000Main.Div,100*A00000Main.Mul/A00000Main.Div,500*A00000Main.Mul/A00000Main.Div);	//郵便番号
		Scpn_EntryPref.setBounds(           120*A00000Main.Mul/A00000Main.Div,65*A00000Main.Mul/A00000Main.Div,200*A00000Main.Mul/A00000Main.Div,500*A00000Main.Mul/A00000Main.Div);	//県
		Scpn_EntryMunic01.setBounds(        330*A00000Main.Mul/A00000Main.Div,65*A00000Main.Mul/A00000Main.Div,200*A00000Main.Mul/A00000Main.Div,500*A00000Main.Mul/A00000Main.Div);	//市区町村
		Scpn_EntryMunic02.setBounds(        540*A00000Main.Mul/A00000Main.Div,65*A00000Main.Mul/A00000Main.Div,200*A00000Main.Mul/A00000Main.Div,500*A00000Main.Mul/A00000Main.Div);	//町丁目
		Scpn_EntryMunicipalityCd.setBounds( 750*A00000Main.Mul/A00000Main.Div,65*A00000Main.Mul/A00000Main.Div,100*A00000Main.Mul/A00000Main.Div,500*A00000Main.Mul/A00000Main.Div);	//市区町村CD
		
		AnyEntry_fm.add(LB_EntryPost);
		AnyEntry_fm.add(LB_EntryPref);
		AnyEntry_fm.add(LB_EntryMunic01);
		AnyEntry_fm.add(LB_EntryMunic02);
		AnyEntry_fm.add(LB_EntryMunicipalityCd);

		AnyEntry_fm.add(Scpn_EntryPost);			//郵便番号
		AnyEntry_fm.add(Scpn_EntryPref);			//県
		AnyEntry_fm.add(Scpn_EntryMunic01);			//市区町村
		AnyEntry_fm.add(Scpn_EntryMunic02);			//町丁目
		AnyEntry_fm.add(Scpn_EntryMunicipalityCd);	//市区町村CD
		
		//登録ボタン
		JButton EntryFix_btn=new JButton();
		EntryFix_btn.setText("登録");
		EntryFix_btn.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 11*A00000Main.Mul/A00000Main.Div));
		EntryFix_btn.setBounds(630*A00000Main.Mul/A00000Main.Div, 620*A00000Main.Mul/A00000Main.Div, 100*A00000Main.Mul/A00000Main.Div, 20*A00000Main.Mul/A00000Main.Div);
		AnyEntry_fm.add(EntryFix_btn);

		//EXITボタン
		JButton Entryexit_btn=new JButton();
		Entryexit_btn.setText("EXIT");
		Entryexit_btn.setFont(new Font("ＭＳ ゴシック", Font.PLAIN, 11*A00000Main.Mul/A00000Main.Div));
		Entryexit_btn.setBounds(760*A00000Main.Mul/A00000Main.Div, 620*A00000Main.Mul/A00000Main.Div, 100*A00000Main.Mul/A00000Main.Div, 20*A00000Main.Mul/A00000Main.Div);
		AnyEntry_fm.add(Entryexit_btn);
		
		AnyEntry_fm.setVisible(true);
		//EXITボタン
		Entryexit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				AnyEntry_fm.dispose();
				AnyEntry_fm.setVisible(false);
			}
		});
	}
	public static void PostMstCreateSum(int x,int y) {
		A00000Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		
		final JFrame main_fm = B00110FrameParts.FrameCreate(x,y,780,700,"Corgi00郵便番号一括登録","");
		JLabel userinfo = B00110FrameParts.UserInfo();
		JButton exit_btn = B00110FrameParts.ExitBtn();
		JButton entry_btn = B00110FrameParts.EntryBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		main_fm.add(entry_btn);
		
		JLabel LB_EntryPost = B00110FrameParts.JLabelSet(				 10,40,100,20,"郵便番号"  ,11,2);
		JLabel LB_EntryPref = B00110FrameParts.JLabelSet(				120,40,100,20,"県"        ,11,2);
		JLabel LB_EntryMunic01 = B00110FrameParts.JLabelSet(			230,40,200,20,"市区町村"  ,11,2);
		JLabel LB_EntryMunic02 = B00110FrameParts.JLabelSet(			440,40,200,20,"町丁目"    ,11,2);
		JLabel LB_EntryMunicipalityCd = B00110FrameParts.JLabelSet(	650,40,100,20,"市区町村CD",11,2);
		
		final JTextArea TB_EntryPost = new JTextArea();				//郵便番号
		final JTextArea TB_EntryPref = new JTextArea();				//県
		final JTextArea TB_EntryMunic01 = new JTextArea();			//市区町村
		final JTextArea TB_EntryMunic02 = new JTextArea();			//町丁目
		final JTextArea TB_EntryMunicipalityCd = new JTextArea();	//市区町村CD
		
		//スクロール用設定
		JScrollPane Scpn_EntryPost = B00110FrameParts.JScrollPaneSet(				 10,60,100,500,TB_EntryPost);			//郵便番号
		JScrollPane Scpn_EntryPref = B00110FrameParts.JScrollPaneSet(				120,60,100,500,TB_EntryPref);			//県
		JScrollPane Scpn_EntryMunic01 = B00110FrameParts.JScrollPaneSet(			230,60,200,500,TB_EntryMunic01);		//市区町村
		JScrollPane Scpn_EntryMunic02 = B00110FrameParts.JScrollPaneSet(			440,60,200,500,TB_EntryMunic02);		//町丁目
		JScrollPane Scpn_EntryMunicipalityCd = B00110FrameParts.JScrollPaneSet(	650,60,100,500,TB_EntryMunicipalityCd);	//市区町村CD

		main_fm.add(LB_EntryPost);
		main_fm.add(LB_EntryPref);
		main_fm.add(LB_EntryMunic01);
		main_fm.add(LB_EntryMunic02);
		main_fm.add(LB_EntryMunicipalityCd);

		main_fm.add(Scpn_EntryPost);			//郵便番号
		main_fm.add(Scpn_EntryPref);			//県
		main_fm.add(Scpn_EntryMunic01);			//市区町村
		main_fm.add(Scpn_EntryMunic02);			//町丁目
		main_fm.add(Scpn_EntryMunicipalityCd);	//市区町村CD
		
		main_fm.setVisible(true);
		
		//登録ボタン王家事の挙動
		entry_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				String[] GetEntryPost = TB_EntryPost.getText().split("\n");						//郵便番号
				String[] GetEntryPref = TB_EntryPref.getText().split("\n");						//県
				String[] GetEntryMunic01 = TB_EntryMunic01.getText().split("\n");				//市区町村
				String[] GetEntryMunic02 = TB_EntryMunic02.getText().split("\n");				//町丁目
				String[] GetEntryMunicipalityCd = TB_EntryMunicipalityCd.getText().split("\n");	//市区町村CD
				
				if(0<GetEntryPost.length) {
					boolean NonErr = true;
					int TgtLength = GetEntryPost.length;
					if(TgtLength!=GetEntryPref.length) {NonErr=false;}					//県
					if(TgtLength!=GetEntryMunic01.length) {NonErr=false;}				//市区町村
					if(TgtLength!=GetEntryMunic02.length) {NonErr=false;}				//町丁目
					if(TgtLength!=GetEntryMunicipalityCd.length) {NonErr=false;}		//市区町村CD
					
					if(NonErr) {
						for(int i=0;i<GetEntryPost.length;i++) {
							GetEntryPost[i] = B00020ToolsTextControl.num_only_String(GetEntryPost[i]);
							if("".equals(GetEntryPost[i])) {
								NonErr = false;
							}
						}
						if(NonErr) {
							String tgt_table = "M0010_PostMst";
							String[][] field_name = new String[5][3];
							String[][] entry_data = new String[GetEntryPost.length][5];
							String[] judg_field = new String[1];
							String[][] judg_data = new String[GetEntryPost.length][1];
							String TgtDB = "POST";
							int non_msg_fg = 0;
							
							judg_field[0] = "POST";					//郵便番号
							
							field_name[0][0] = "POST";				//郵便番号
							field_name[1][0] = "PREFECTURES";		//県
							field_name[2][0] = "MUNICI01";			//市区町村
							field_name[3][0] = "MUNICI02";			//町丁目
							field_name[4][0] = "MUNICIPALITY_CD";	//市区町村CD

							field_name[0][1] = "1";	//郵便番号
							field_name[1][1] = "1";	//県
							field_name[2][1] = "1";	//市区町村
							field_name[3][1] = "1";	//町丁目
							field_name[4][1] = "1";	//市区町村CD

							field_name[0][2] = "1";	//郵便番号
							field_name[1][2] = "1";	//県
							field_name[2][2] = "1";	//市区町村
							field_name[3][2] = "1";	//町丁目
							field_name[4][2] = "1";	//市区町村CD
							
							for(int i=0;i<GetEntryPost.length;i++) {
								judg_data[i][0] = B00020ToolsTextControl.Trim(GetEntryPost[i]);	//郵便番号
								
								entry_data[i][0] = B00020ToolsTextControl.Trim(GetEntryPost[i]);			//郵便番号
								entry_data[i][1] = B00020ToolsTextControl.Trim(GetEntryPref[i]);			//県
								entry_data[i][2] = B00020ToolsTextControl.Trim(GetEntryMunic01[i]);			//市区町村
								entry_data[i][3] = B00020ToolsTextControl.Trim(GetEntryMunic02[i]);			//町丁目
								entry_data[i][4] = B00020ToolsTextControl.Trim(GetEntryMunicipalityCd[i]);	//市区町村CD
							}
							A00020InsertUdateSQL.RUN_SQLS_EU(tgt_table, field_name, entry_data, judg_field, judg_data, non_msg_fg,TgtDB);
							
						}else {
							JOptionPane.showMessageDialog(null, "不正な郵便番号を登録しようとしています。やり直し");
						}
					}else {
						JOptionPane.showMessageDialog(null, "各項目の登録件数が一致しません。やり直し");
					}
				}
				TB_EntryPost.setText("");			//郵便番号
				TB_EntryPref.setText("");			//県
				TB_EntryMunic01.setText("");		//市区町村
				TB_EntryMunic02.setText("");		//町丁目
				TB_EntryMunicipalityCd.setText("");	//市区町村CD
			}
		});
		
		//EXITボタン押下時の挙動
		exit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				WM10010PostMstSearch.PostMstSearch(0, 0);
			}
		});
	}
}