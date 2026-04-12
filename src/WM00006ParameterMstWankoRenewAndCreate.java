import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class WM00006ParameterMstWankoRenewAndCreate{
	static int SetX;
	static int SetY;
	public static void ParameterMstWankoRenewAndCreate(int x,int y,String TgtWhCd,String TgtClCd,String TgtParaCd,String TgtParaCdSeq) {
		if(null==TgtClCd) {TgtClCd="";}
		if(null==TgtWhCd) {TgtWhCd="";}
		if(null==TgtParaCd) {TgtParaCd="";}
		if(null==TgtParaCdSeq) {TgtParaCdSeq="";}
		
		A00000Main.LoginCheck();
		if(0==SetX) {SetX=100;}
		if(0==SetY) {SetY=100;}
		if(x==0) {x=SetX;}
		if(y==0) {y=SetY;}
		
		final JFrame main_fm = B00110FrameParts.FrameCreate(x,y,700,650,"Corgi00荷主パラメータ登録・修正","");
		JLabel userinfo = B00110FrameParts.UserInfo();
		JButton exit_btn = B00110FrameParts.ExitBtn();
		JButton entry_btn = B00110FrameParts.EntryBtn();
		
		main_fm.add(userinfo);
		main_fm.add(exit_btn);
		main_fm.add(entry_btn);
		
		JLabel LB_ClCd			= B00110FrameParts.JLabelSet(	  								  0, 50,130,20,"荷主:"							,11,1);
		final JComboBox  TB_ClCd	= B00110FrameParts.JComboBoxSet(							130, 50,180,20,B00100DefaultVariable.ClList[0], 11);
		JLabel LB_WhCd			= B00110FrameParts.JLabelSet(	  								  0, 75,130,20,"担当倉庫:"						,11,1);
		final JComboBox  TB_WhCd	= B00110FrameParts.JComboBoxSet(							130, 75,180,20,B00100DefaultVariable.WhList[0], 11);
		JLabel LB_ParaCd			= B00110FrameParts.JLabelSet(	  							  0,100,130,20,"パラメータコード:"			,11,1);
		final JTextField  TB_ParaCd	= B00110FrameParts.JTextFieldSet(							130,100,100,20,""							,11,0);
		JLabel LB_ParaCdSeq	= B00110FrameParts.JLabelSet(	  									  0,125,130,20,"シーケンシャルNo:"			,11,1);
		final JFormattedTextField TB_ParaCdSeq= B00110FrameParts.JFormattedTextFieldSet(		130,125, 80,20,""							,11,1,"####");
		JLabel LB_ParaName		= B00110FrameParts.JLabelSet(									  0,150,130,20,"パラメータ名:"				,11,1);
		final JTextField  TB_ParaName= B00110FrameParts.JTextFieldSet(						130,150,100,20,""							,11,0);
		
		JLabel LB_ParaTxt01		= B00110FrameParts.JLabelSet(					  0,200,130,20,"文字設定項目01:"	,11,1);
		final JTextField  TB_ParaTxt01= B00110FrameParts.JTextFieldSet(		130,200,200,20,""					,11,0);
		JLabel LB_ParaTxt02		= B00110FrameParts.JLabelSet(					  0,225,130,20,"文字設定項目02:"	,11,1);
		final JTextField  TB_ParaTxt02= B00110FrameParts.JTextFieldSet(		130,225,200,20,""					,11,0);
		JLabel LB_ParaTxt03		= B00110FrameParts.JLabelSet(					  0,250,130,20,"文字設定項目03:"	,11,1);
		final JTextField  TB_ParaTxt03= B00110FrameParts.JTextFieldSet(		130,250,200,20,""					,11,0);
		JLabel LB_ParaTxt04		= B00110FrameParts.JLabelSet(					  0,275,130,20,"文字設定項目04:"	,11,1);
		final JTextField  TB_ParaTxt04= B00110FrameParts.JTextFieldSet(		130,275,200,20,""					,11,0);
		JLabel LB_ParaTxt05		= B00110FrameParts.JLabelSet(					  0,300,130,20,"文字設定項目05:"	,11,1);
		final JTextField  TB_ParaTxt05= B00110FrameParts.JTextFieldSet(		130,300,200,20,""					,11,0);
		JLabel LB_ParaTxt06		= B00110FrameParts.JLabelSet(					  0,325,130,20,"文字設定項目06:"	,11,1);
		final JTextField  TB_ParaTxt06= B00110FrameParts.JTextFieldSet(		130,325,200,20,""					,11,0);
		JLabel LB_ParaTxt07		= B00110FrameParts.JLabelSet(					  0,350,130,20,"文字設定項目07:"	,11,1);
		final JTextField  TB_ParaTxt07= B00110FrameParts.JTextFieldSet(		130,350,200,20,""					,11,0);
		JLabel LB_ParaTxt08		= B00110FrameParts.JLabelSet(					  0,375,130,20,"文字設定項目08:"	,11,1);
		final JTextField  TB_ParaTxt08= B00110FrameParts.JTextFieldSet(		130,375,200,20,""					,11,0);
		JLabel LB_ParaTxt09		= B00110FrameParts.JLabelSet(					  0,400,130,20,"文字設定項目09:"	,11,1);
		final JTextField  TB_ParaTxt09= B00110FrameParts.JTextFieldSet(		130,400,200,20,""					,11,0);
		JLabel LB_ParaTxt10		= B00110FrameParts.JLabelSet(					  0,425,130,20,"文字設定項目10:"	,11,1);
		final JTextField  TB_ParaTxt10= B00110FrameParts.JTextFieldSet(		130,425,200,20,""					,11,0);
		
		JLabel LB_ParaInt01	= B00110FrameParts.JLabelSet(									350,200,130,20,"数値設定項目01:"	,11,1);
		final JFormattedTextField TB_ParaInt01= B00110FrameParts.JFormattedTextFieldSet(	480,200,100,20,"0"					,11,1,"####");
		JLabel LB_ParaInt02	= B00110FrameParts.JLabelSet(									350,225,130,20,"数値設定項目02:"	,11,1);
		final JFormattedTextField TB_ParaInt02= B00110FrameParts.JFormattedTextFieldSet(	480,225,100,20,"0"					,11,1,"####");
		JLabel LB_ParaInt03	= B00110FrameParts.JLabelSet(									350,250,130,20,"数値設定項目03:"	,11,1);
		final JFormattedTextField TB_ParaInt03= B00110FrameParts.JFormattedTextFieldSet(	480,250,100,20,"0"					,11,1,"####");
		JLabel LB_ParaInt04	= B00110FrameParts.JLabelSet(									350,275,130,20,"数値設定項目04:"	,11,1);
		final JFormattedTextField TB_ParaInt04= B00110FrameParts.JFormattedTextFieldSet(	480,275,100,20,"0"					,11,1,"####");
		JLabel LB_ParaInt05	= B00110FrameParts.JLabelSet(									350,300,130,20,"数値設定項目05:"	,11,1);
		final JFormattedTextField TB_ParaInt05= B00110FrameParts.JFormattedTextFieldSet(	480,300,100,20,"0"					,11,1,"####");
		JLabel LB_ParaInt06	= B00110FrameParts.JLabelSet(									350,325,130,20,"数値設定項目06:"	,11,1);
		final JFormattedTextField TB_ParaInt06= B00110FrameParts.JFormattedTextFieldSet(	480,325,100,20,"0"					,11,1,"####");
		JLabel LB_ParaInt07	= B00110FrameParts.JLabelSet(									350,350,130,20,"数値設定項目07:"	,11,1);
		final JFormattedTextField TB_ParaInt07= B00110FrameParts.JFormattedTextFieldSet(	480,350,100,20,"0"					,11,1,"####");
		JLabel LB_ParaInt08	= B00110FrameParts.JLabelSet(									350,375,130,20,"数値設定項目08:"	,11,1);
		final JFormattedTextField TB_ParaInt08= B00110FrameParts.JFormattedTextFieldSet(	480,375,100,20,"0"					,11,1,"####");
		JLabel LB_ParaInt09	= B00110FrameParts.JLabelSet(									350,400,130,20,"数値設定項目09:"	,11,1);
		final JFormattedTextField TB_ParaInt09= B00110FrameParts.JFormattedTextFieldSet(	480,400,100,20,"0"					,11,1,"####");
		JLabel LB_ParaInt10	= B00110FrameParts.JLabelSet(									350,425,130,20,"数値設定項目10:"	,11,1);
		final JFormattedTextField TB_ParaInt10= B00110FrameParts.JFormattedTextFieldSet(	480,425,100,20,"0"					,11,1,"####");
		
		
		JLabel LB_EntryDate				= B00110FrameParts.JLabelSet(			  0,475,130,20,"登録日:"	,11,1);
		final JTextField  TB_EntryDate	= B00110FrameParts.JTextFieldSet(		130,475,200,20,""			,11,0);
		JLabel LB_UpdateDate			= B00110FrameParts.JLabelSet(			  0,500,130,20,"更新日:"	,11,1);
		final JTextField  TB_UpdateDate	= B00110FrameParts.JTextFieldSet(		130,500,200,20,""			,11,0);
		JLabel LB_EntryUser				= B00110FrameParts.JLabelSet(			  0,525,130,20,"登録者:"	,11,1);
		final JTextField  TB_EntryUser	= B00110FrameParts.JTextFieldSet(		130,525,200,20,""			,11,0);
		JLabel LB_UpdateUser			= B00110FrameParts.JLabelSet(			  0,550,130,20,"更新者:"	,11,1);
		final JTextField  TB_UpdateUser	= B00110FrameParts.JTextFieldSet(		130,550,200,20,""			,11,0);
		
		//新規登録ボタン
		JButton CreateBtn = B00110FrameParts.BtnSet(		250,100,100,20,"新規モード",10);
		main_fm.add(CreateBtn);
		
		TB_EntryDate.setEditable(false);
		TB_UpdateDate.setEditable(false);
		TB_EntryUser.setEditable(false);
		TB_UpdateUser.setEditable(false);
		
		main_fm.add(LB_ClCd);
		main_fm.add(LB_WhCd);
		main_fm.add(LB_ParaCd);
		main_fm.add(LB_ParaCdSeq);
		main_fm.add(LB_ParaName);
		main_fm.add(LB_ParaTxt01);
		main_fm.add(LB_ParaTxt02);
		main_fm.add(LB_ParaTxt03);
		main_fm.add(LB_ParaTxt04);
		main_fm.add(LB_ParaTxt05);
		main_fm.add(LB_ParaTxt06);
		main_fm.add(LB_ParaTxt07);
		main_fm.add(LB_ParaTxt08);
		main_fm.add(LB_ParaTxt09);
		main_fm.add(LB_ParaTxt10);
		main_fm.add(LB_ParaInt01);
		main_fm.add(LB_ParaInt02);
		main_fm.add(LB_ParaInt03);
		main_fm.add(LB_ParaInt04);
		main_fm.add(LB_ParaInt05);
		main_fm.add(LB_ParaInt06);
		main_fm.add(LB_ParaInt07);
		main_fm.add(LB_ParaInt08);
		main_fm.add(LB_ParaInt09);
		main_fm.add(LB_ParaInt10);
		main_fm.add(LB_EntryDate);
		main_fm.add(LB_UpdateDate);
		main_fm.add(LB_EntryUser);
		main_fm.add(LB_UpdateUser);
		
		main_fm.add(TB_ClCd);
		main_fm.add(TB_WhCd);
		main_fm.add(TB_ParaCd);
		main_fm.add(TB_ParaCdSeq);
		main_fm.add(TB_ParaName);
		main_fm.add(TB_ParaTxt01);
		main_fm.add(TB_ParaTxt02);
		main_fm.add(TB_ParaTxt03);
		main_fm.add(TB_ParaTxt04);
		main_fm.add(TB_ParaTxt05);
		main_fm.add(TB_ParaTxt06);
		main_fm.add(TB_ParaTxt07);
		main_fm.add(TB_ParaTxt08);
		main_fm.add(TB_ParaTxt09);
		main_fm.add(TB_ParaTxt10);
		main_fm.add(TB_ParaInt01);
		main_fm.add(TB_ParaInt02);
		main_fm.add(TB_ParaInt03);
		main_fm.add(TB_ParaInt04);
		main_fm.add(TB_ParaInt05);
		main_fm.add(TB_ParaInt06);
		main_fm.add(TB_ParaInt07);
		main_fm.add(TB_ParaInt08);
		main_fm.add(TB_ParaInt09);
		main_fm.add(TB_ParaInt10);
		main_fm.add(TB_EntryDate);
		main_fm.add(TB_UpdateDate);
		main_fm.add(TB_EntryUser);
		main_fm.add(TB_UpdateUser);
		
		TB_ParaCd.setText(TgtParaCd);
		TB_ParaCdSeq.setText(TgtParaCdSeq);
		

		for(int i=0;i<B00100DefaultVariable.ClList[1].length;i++) {
			if(B00100DefaultVariable.ClList[1][i].equals(A00000Main.ClCd)) {
				TB_ClCd.setSelectedIndex(i);
			}
		}


		for(int i=0;i<B00100DefaultVariable.WhList[1].length;i++) {
			if(B00100DefaultVariable.WhList[1][i].equals(A00000Main.ClWh)) {
				TB_WhCd.setSelectedIndex(i);
			}
		}

		
		TB_ClCd.setEnabled(false);
		TB_WhCd.setEnabled(false);
		
		if(!"".equals(TgtParaCd) && !"".equals(TgtParaCdSeq)) {
			Object[][] ParameterMstRtWANKO = ParameterMstRtWANKO(TgtWhCd,TgtClCd,TgtParaCd,TgtParaCdSeq);
			
			if(1==ParameterMstRtWANKO.length) {
				for(int i=0;i<B00100DefaultVariable.ClList[1].length;i++) {
					if(B00100DefaultVariable.ClList[1][i].equals((String)ParameterMstRtWANKO[0][M00000ParameterMstWankoRt.ColClCd])) {
						TB_ClCd.setSelectedIndex(i);
					}
				}
				for(int i=0;i<B00100DefaultVariable.WhList[1].length;i++) {
					if(B00100DefaultVariable.WhList[1][i].equals((String)ParameterMstRtWANKO[0][M00000ParameterMstWankoRt.ColClWh])) {
						TB_WhCd.setSelectedIndex(i);
					}
				}
				TB_ParaCd.setText(""+(String)ParameterMstRtWANKO[0][M00000ParameterMstWankoRt.ColParaCd]);			//パラメータコード
				TB_ParaCdSeq.setText(""+(int)ParameterMstRtWANKO[0][M00000ParameterMstWankoRt.ColParaCdSeq]);		//ナンバリング
				TB_ParaName.setText(""+(String)ParameterMstRtWANKO[0][M00000ParameterMstWankoRt.ColParaName]);		//パラメータ名
				TB_ParaTxt01.setText(""+(String)ParameterMstRtWANKO[0][M00000ParameterMstWankoRt.ColParaTxt01]);	//パラメータテキスト項目01
				TB_ParaTxt02.setText(""+(String)ParameterMstRtWANKO[0][M00000ParameterMstWankoRt.ColParaTxt02]);	//パラメータテキスト項目02
				TB_ParaTxt03.setText(""+(String)ParameterMstRtWANKO[0][M00000ParameterMstWankoRt.ColParaTxt03]);	//パラメータテキスト項目03
				TB_ParaTxt04.setText(""+(String)ParameterMstRtWANKO[0][M00000ParameterMstWankoRt.ColParaTxt04]);	//パラメータテキスト項目04
				TB_ParaTxt05.setText(""+(String)ParameterMstRtWANKO[0][M00000ParameterMstWankoRt.ColParaTxt05]);	//パラメータテキスト項目05
				TB_ParaTxt06.setText(""+(String)ParameterMstRtWANKO[0][M00000ParameterMstWankoRt.ColParaTxt06]);	//パラメータテキスト項目06
				TB_ParaTxt07.setText(""+(String)ParameterMstRtWANKO[0][M00000ParameterMstWankoRt.ColParaTxt07]);	//パラメータテキスト項目07
				TB_ParaTxt08.setText(""+(String)ParameterMstRtWANKO[0][M00000ParameterMstWankoRt.ColParaTxt08]);	//パラメータテキスト項目08
				TB_ParaTxt09.setText(""+(String)ParameterMstRtWANKO[0][M00000ParameterMstWankoRt.ColParaTxt09]);	//パラメータテキスト項目09
				TB_ParaTxt10.setText(""+(String)ParameterMstRtWANKO[0][M00000ParameterMstWankoRt.ColParaTxt10]);	//パラメータテキスト項目10
				TB_ParaInt01.setText(""+(int)ParameterMstRtWANKO[0][M00000ParameterMstWankoRt.ColParaInt01]);		//パラメータ数値項目01
				TB_ParaInt02.setText(""+(int)ParameterMstRtWANKO[0][M00000ParameterMstWankoRt.ColParaInt02]);		//パラメータ数値項目02
				TB_ParaInt03.setText(""+(int)ParameterMstRtWANKO[0][M00000ParameterMstWankoRt.ColParaInt03]);		//パラメータ数値項目03
				TB_ParaInt04.setText(""+(int)ParameterMstRtWANKO[0][M00000ParameterMstWankoRt.ColParaInt04]);		//パラメータ数値項目04
				TB_ParaInt05.setText(""+(int)ParameterMstRtWANKO[0][M00000ParameterMstWankoRt.ColParaInt05]);		//パラメータ数値項目05
				TB_ParaInt06.setText(""+(int)ParameterMstRtWANKO[0][M00000ParameterMstWankoRt.ColParaInt06]);		//パラメータ数値項目06
				TB_ParaInt07.setText(""+(int)ParameterMstRtWANKO[0][M00000ParameterMstWankoRt.ColParaInt07]);		//パラメータ数値項目07
				TB_ParaInt08.setText(""+(int)ParameterMstRtWANKO[0][M00000ParameterMstWankoRt.ColParaInt08]);		//パラメータ数値項目08
				TB_ParaInt09.setText(""+(int)ParameterMstRtWANKO[0][M00000ParameterMstWankoRt.ColParaInt09]);		//パラメータ数値項目09
				TB_ParaInt10.setText(""+(int)ParameterMstRtWANKO[0][M00000ParameterMstWankoRt.ColParaInt10]);		//パラメータ数値項目10
				TB_EntryDate.setText(""+(String)ParameterMstRtWANKO[0][M00000ParameterMstWankoRt.ColEntryDate]);	//登録日
				TB_UpdateDate.setText(""+(String)ParameterMstRtWANKO[0][M00000ParameterMstWankoRt.ColUpdateDate]);	//更新日
				TB_EntryUser.setText(""+(String)ParameterMstRtWANKO[0][M00000ParameterMstWankoRt.ColEntryUser]);	//登録者
				TB_UpdateUser.setText(""+(String)ParameterMstRtWANKO[0][M00000ParameterMstWankoRt.ColUpdateUser]);	//更新者
			}
		}
		
		main_fm.setVisible(true);
		
		String GetParaCd	= TB_ParaCd.getText();			//パラメータコード
		String GetParaCdSeq	= TB_ParaCdSeq.getText();		//ナンバリング
		
		if(null==GetParaCd		) {GetParaCd	= "";}
		if(null==GetParaCdSeq	) {GetParaCdSeq	= "";}
		
		B00020ToolsTextControl.Trim(GetParaCd);
		B00020ToolsTextControl.Trim(GetParaCdSeq);
		
		if("".equals(GetParaCd) && "".equals(GetParaCdSeq)) {
			TB_ParaCd.requestFocusInWindow();
		}else if("".equals(GetParaCdSeq)){
			TB_ParaCd.setEnabled(false);
			TB_ParaCdSeq.requestFocusInWindow();
		}else {
			TB_ParaCd.setEnabled(false);
			TB_ParaCdSeq.setEnabled(false);
			TB_ParaName.requestFocusInWindow();
			
			main_fm.add(CreateBtn);
		}
		
		//登録ボタン押下時の挙動
		entry_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				String GetClWh		= B00100DefaultVariable.WhList[1][TB_WhCd.getSelectedIndex()];	//担当倉庫コード
				String GetClCd		= B00100DefaultVariable.ClList[1][TB_ClCd.getSelectedIndex()];	//荷主コード
				String GetParaCd	= TB_ParaCd.getText();		//パラメータコード
				String GetParaCdSeq	= TB_ParaCdSeq.getText();	//ナンバリング
				String GetParaName	= TB_ParaName.getText();	//パラメータ名
				String GetParaTxt01	= TB_ParaTxt01.getText();	//パラメータテキスト項目01
				String GetParaTxt02	= TB_ParaTxt02.getText();	//パラメータテキスト項目02
				String GetParaTxt03	= TB_ParaTxt03.getText();	//パラメータテキスト項目03
				String GetParaTxt04	= TB_ParaTxt04.getText();	//パラメータテキスト項目04
				String GetParaTxt05	= TB_ParaTxt05.getText();	//パラメータテキスト項目05
				String GetParaTxt06	= TB_ParaTxt06.getText();	//パラメータテキスト項目06
				String GetParaTxt07	= TB_ParaTxt07.getText();	//パラメータテキスト項目07
				String GetParaTxt08	= TB_ParaTxt08.getText();	//パラメータテキスト項目08
				String GetParaTxt09	= TB_ParaTxt09.getText();	//パラメータテキスト項目09
				String GetParaTxt10	= TB_ParaTxt10.getText();	//パラメータテキスト項目10
				String GetParaInt01	= TB_ParaInt01.getText();	//パラメータ数値項目01
				String GetParaInt02	= TB_ParaInt02.getText();	//パラメータ数値項目02
				String GetParaInt03	= TB_ParaInt03.getText();	//パラメータ数値項目03
				String GetParaInt04	= TB_ParaInt04.getText();	//パラメータ数値項目04
				String GetParaInt05	= TB_ParaInt05.getText();	//パラメータ数値項目05
				String GetParaInt06	= TB_ParaInt06.getText();	//パラメータ数値項目06
				String GetParaInt07	= TB_ParaInt07.getText();	//パラメータ数値項目07
				String GetParaInt08	= TB_ParaInt08.getText();	//パラメータ数値項目08
				String GetParaInt09	= TB_ParaInt09.getText();	//パラメータ数値項目09
				String GetParaInt10	= TB_ParaInt10.getText();	//パラメータ数値項目10
				
				boolean Err = DataEntry(
						GetClWh,		//担当倉庫コード
						GetClCd,		//荷主コード
						GetParaCd,		//パラメータコード
						GetParaCdSeq,	//ナンバリング
						GetParaName,	//パラメータ名
						GetParaTxt01,	//パラメータテキスト項目01
						GetParaTxt02,	//パラメータテキスト項目02
						GetParaTxt03,	//パラメータテキスト項目03
						GetParaTxt04,	//パラメータテキスト項目04
						GetParaTxt05,	//パラメータテキスト項目05
						GetParaTxt06,	//パラメータテキスト項目06
						GetParaTxt07,	//パラメータテキスト項目07
						GetParaTxt08,	//パラメータテキスト項目08
						GetParaTxt09,	//パラメータテキスト項目09
						GetParaTxt10,	//パラメータテキスト項目10
						GetParaInt01,	//パラメータ数値項目01
						GetParaInt02,	//パラメータ数値項目02
						GetParaInt03,	//パラメータ数値項目03
						GetParaInt04,	//パラメータ数値項目04
						GetParaInt05,	//パラメータ数値項目05
						GetParaInt06,	//パラメータ数値項目06
						GetParaInt07,	//パラメータ数値項目07
						GetParaInt08,	//パラメータ数値項目08
						GetParaInt09,	//パラメータ数値項目09
						GetParaInt10	//パラメータ数値項目10
						) ;
				
				if(Err) {
					
				}else {
					SetX=main_fm.getX();
					SetY=main_fm.getY();
	
					main_fm.setVisible(false);
					main_fm.dispose();
					ParameterMstWankoRenewAndCreate(0,0,GetClWh,GetClCd,GetParaCd,GetParaCdSeq);
				}
			}
		});
		
		//パラメータコードフォーカス消失時の挙動
		TB_ParaCd.addFocusListener(new FocusAdapter(){
			@Override
			public void focusLost(FocusEvent e){
				String GetClWh		= B00100DefaultVariable.WhList[1][TB_WhCd.getSelectedIndex()];	//担当倉庫コード
				String GetClCd		= B00100DefaultVariable.ClList[1][TB_ClCd.getSelectedIndex()];	//荷主コード
				String GetParaCd	= TB_ParaCd.getText();			//パラメータコード
				String GetParaCdSeq	= TB_ParaCdSeq.getText();		//ナンバリング
				
				if(null==GetClWh		) {GetClWh	= "";}
				if(null==GetClCd		) {GetClCd	= "";}
				if(null==GetParaCd		) {GetParaCd	= "";}
				if(null==GetParaCdSeq	) {GetParaCdSeq	= "";}
				
				GetClWh			= B00020ToolsTextControl.Trim(GetClWh);
				GetClCd			= B00020ToolsTextControl.Trim(GetClCd);
				GetParaCd		= B00020ToolsTextControl.Trim(GetParaCd);
				GetParaCdSeq	= B00020ToolsTextControl.Trim(GetParaCdSeq);
				
				GetParaCdSeq = B00020ToolsTextControl.num_only_String(GetParaCdSeq);
				
				if(!"".equals(GetParaCd) && !"".equals(GetParaCdSeq)) {
					SetX=main_fm.getX();
					SetY=main_fm.getY();

					main_fm.setVisible(false);
					main_fm.dispose();
					ParameterMstWankoRenewAndCreate(0,0,GetClWh,GetClCd,GetParaCd,GetParaCdSeq);
				}
			}
		});
		
		//ナンバリングフォーカス消失時の挙動
		TB_ParaCdSeq.addFocusListener(new FocusAdapter(){
			@Override
			public void focusLost(FocusEvent e){
				String GetClWh		= B00100DefaultVariable.WhList[1][TB_WhCd.getSelectedIndex()];	//担当倉庫コード
				String GetClCd		= B00100DefaultVariable.ClList[1][TB_ClCd.getSelectedIndex()];	//荷主コード
				String GetParaCd	= TB_ParaCd.getText();			//パラメータコード
				String GetParaCdSeq	= TB_ParaCdSeq.getText();		//ナンバリング
				
				if(null==GetClWh		) {GetClWh	= "";}
				if(null==GetClCd		) {GetClCd	= "";}
				if(null==GetParaCd		) {GetParaCd	= "";}
				if(null==GetParaCdSeq	) {GetParaCdSeq	= "";}
				
				GetClWh			= B00020ToolsTextControl.Trim(GetClWh);
				GetClCd			= B00020ToolsTextControl.Trim(GetClCd);
				GetParaCd		= B00020ToolsTextControl.Trim(GetParaCd);
				GetParaCdSeq	= B00020ToolsTextControl.Trim(GetParaCdSeq);
				
				GetParaCdSeq = B00020ToolsTextControl.num_only_String(GetParaCdSeq);
				
				if(!"".equals(GetParaCd) && !"".equals(GetParaCdSeq)) {
					SetX=main_fm.getX();
					SetY=main_fm.getY();

					main_fm.setVisible(false);
					main_fm.dispose();
					ParameterMstWankoRenewAndCreate(0,0,GetClWh,GetClCd,GetParaCd,GetParaCdSeq);
				}
			}
		});
		
		//新規登録モードボタン押下時の挙動
		CreateBtn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				String GetClWh		= B00100DefaultVariable.WhList[1][TB_WhCd.getSelectedIndex()];	//担当倉庫コード
				String GetClCd		= B00100DefaultVariable.ClList[1][TB_ClCd.getSelectedIndex()];	//荷主コード
				String GetParaCd	= TB_ParaCd.getText();			//パラメータコード
				String GetParaCdSeq	= TB_ParaCdSeq.getText();		//ナンバリング
				
				if(null==GetClWh		) {GetClWh	= "";}
				if(null==GetClCd		) {GetClCd	= "";}
				if(null==GetParaCd		) {GetParaCd	= "";}
				if(null==GetParaCdSeq	) {GetParaCdSeq	= "";}
				
				GetClWh			= B00020ToolsTextControl.Trim(GetClWh);
				GetClCd			= B00020ToolsTextControl.Trim(GetClCd);
				GetParaCd		= B00020ToolsTextControl.Trim(GetParaCd);
				GetParaCdSeq	= B00020ToolsTextControl.Trim(GetParaCdSeq);
				
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				ParameterMstWankoRenewAndCreate(0,0,GetClWh,GetClCd,"","");
			}
		});
		
		//EXITボタン押下時の挙動
		exit_btn.addActionListener(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				SetX=main_fm.getX();
				SetY=main_fm.getY();

				main_fm.setVisible(false);
				main_fm.dispose();
				WM00005ParameterMstWankoSeach.ParameterMstWankoSeach(0, 0);
			}
		});
	}
	
	
	
	private static Object[][] ParameterMstRtWANKO(String TgtWhCd,String TgtClCd,String TgtParaCd,String TgtParaCdSeq){
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
		Boolean AllSearch = false;
		
		SearchClWh.add(TgtWhCd);
		SearchClCd.add(TgtClCd);
		SearchParaCd.add(TgtParaCd);
		SearchParaCdSeqStr.add(Integer.parseInt(TgtParaCdSeq));
		
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
		
		return ParameterMstWankoRt;
	}
	
	private static boolean DataEntry(
			String GetClWh,			//担当倉庫コード
			String GetClCd,			//荷主コード
			String GetParaCd,		//パラメータコード
			String GetParaCdSeq,	//ナンバリング
			String GetParaName,		//パラメータ名
			String GetParaTxt01,	//パラメータテキスト項目01
			String GetParaTxt02,	//パラメータテキスト項目02
			String GetParaTxt03,	//パラメータテキスト項目03
			String GetParaTxt04,	//パラメータテキスト項目04
			String GetParaTxt05,	//パラメータテキスト項目05
			String GetParaTxt06,	//パラメータテキスト項目06
			String GetParaTxt07,	//パラメータテキスト項目07
			String GetParaTxt08,	//パラメータテキスト項目08
			String GetParaTxt09,	//パラメータテキスト項目09
			String GetParaTxt10,	//パラメータテキスト項目10
			String GetParaInt01,	//パラメータ数値項目01
			String GetParaInt02,	//パラメータ数値項目02
			String GetParaInt03,	//パラメータ数値項目03
			String GetParaInt04,	//パラメータ数値項目04
			String GetParaInt05,	//パラメータ数値項目05
			String GetParaInt06,	//パラメータ数値項目06
			String GetParaInt07,	//パラメータ数値項目07
			String GetParaInt08,	//パラメータ数値項目08
			String GetParaInt09,	//パラメータ数値項目09
			String GetParaInt10	//パラメータ数値項目10
			) {
		
		if(null==GetClWh		) {GetClWh			= "";}	//担当倉庫コード
		if(null==GetClCd		) {GetClCd			= "";}	//荷主コード
		if(null==GetParaCd		) {GetParaCd		= "";}	//パラメータコード
		if(null==GetParaCdSeq	) {GetParaCdSeq		= "";}	//ナンバリング
		if(null==GetParaName	) {GetParaName		= "";}	//パラメータ名
		if(null==GetParaTxt01	) {GetParaTxt01		= "";}	//パラメータテキスト項目01
		if(null==GetParaTxt02	) {GetParaTxt02		= "";}	//パラメータテキスト項目02
		if(null==GetParaTxt03	) {GetParaTxt03		= "";}	//パラメータテキスト項目03
		if(null==GetParaTxt04	) {GetParaTxt04		= "";}	//パラメータテキスト項目04
		if(null==GetParaTxt05	) {GetParaTxt05		= "";}	//パラメータテキスト項目05
		if(null==GetParaTxt06	) {GetParaTxt06		= "";}	//パラメータテキスト項目06
		if(null==GetParaTxt07	) {GetParaTxt07		= "";}	//パラメータテキスト項目07
		if(null==GetParaTxt08	) {GetParaTxt08		= "";}	//パラメータテキスト項目08
		if(null==GetParaTxt09	) {GetParaTxt09		= "";}	//パラメータテキスト項目09
		if(null==GetParaTxt10	) {GetParaTxt10		= "";}	//パラメータテキスト項目10
		if(null==GetParaInt01	) {GetParaInt01		= "";}	//パラメータ数値項目01
		if(null==GetParaInt02	) {GetParaInt02		= "";}	//パラメータ数値項目02
		if(null==GetParaInt03	) {GetParaInt03		= "";}	//パラメータ数値項目03
		if(null==GetParaInt04	) {GetParaInt04		= "";}	//パラメータ数値項目04
		if(null==GetParaInt05	) {GetParaInt05		= "";}	//パラメータ数値項目05
		if(null==GetParaInt06	) {GetParaInt06		= "";}	//パラメータ数値項目06
		if(null==GetParaInt07	) {GetParaInt07		= "";}	//パラメータ数値項目07
		if(null==GetParaInt08	) {GetParaInt08		= "";}	//パラメータ数値項目08
		if(null==GetParaInt09	) {GetParaInt09		= "";}	//パラメータ数値項目09
		if(null==GetParaInt10	) {GetParaInt10		= "";}	//パラメータ数値項目10
		
		GetClWh			= B00020ToolsTextControl.Trim(GetClWh);			//担当倉庫コード
		GetClCd			= B00020ToolsTextControl.Trim(GetClCd);			//荷主コード
		GetParaCd		= B00020ToolsTextControl.Trim(GetParaCd);		//パラメータコード
		GetParaCdSeq	= B00020ToolsTextControl.Trim(GetParaCdSeq);	//ナンバリング
		GetParaName		= B00020ToolsTextControl.Trim(GetParaName);		//パラメータ名
		GetParaTxt01	= B00020ToolsTextControl.Trim(GetParaTxt01);	//パラメータテキスト項目01
		GetParaTxt02	= B00020ToolsTextControl.Trim(GetParaTxt02);	//パラメータテキスト項目02
		GetParaTxt03	= B00020ToolsTextControl.Trim(GetParaTxt03);	//パラメータテキスト項目03
		GetParaTxt04	= B00020ToolsTextControl.Trim(GetParaTxt04);	//パラメータテキスト項目04
		GetParaTxt05	= B00020ToolsTextControl.Trim(GetParaTxt05);	//パラメータテキスト項目05
		GetParaTxt06	= B00020ToolsTextControl.Trim(GetParaTxt06);	//パラメータテキスト項目06
		GetParaTxt07	= B00020ToolsTextControl.Trim(GetParaTxt07);	//パラメータテキスト項目07
		GetParaTxt08	= B00020ToolsTextControl.Trim(GetParaTxt08);	//パラメータテキスト項目08
		GetParaTxt09	= B00020ToolsTextControl.Trim(GetParaTxt09);	//パラメータテキスト項目09
		GetParaTxt10	= B00020ToolsTextControl.Trim(GetParaTxt10);	//パラメータテキスト項目10
		GetParaInt01	= B00020ToolsTextControl.Trim(GetParaInt01);	//パラメータ数値項目01
		GetParaInt02	= B00020ToolsTextControl.Trim(GetParaInt02);	//パラメータ数値項目02
		GetParaInt03	= B00020ToolsTextControl.Trim(GetParaInt03);	//パラメータ数値項目03
		GetParaInt04	= B00020ToolsTextControl.Trim(GetParaInt04);	//パラメータ数値項目04
		GetParaInt05	= B00020ToolsTextControl.Trim(GetParaInt05);	//パラメータ数値項目05
		GetParaInt06	= B00020ToolsTextControl.Trim(GetParaInt06);	//パラメータ数値項目06
		GetParaInt07	= B00020ToolsTextControl.Trim(GetParaInt07);	//パラメータ数値項目07
		GetParaInt08	= B00020ToolsTextControl.Trim(GetParaInt08);	//パラメータ数値項目08
		GetParaInt09	= B00020ToolsTextControl.Trim(GetParaInt09);	//パラメータ数値項目09
		GetParaInt10	= B00020ToolsTextControl.Trim(GetParaInt10);	//パラメータ数値項目10
		
		GetParaCdSeq	= B00020ToolsTextControl.num_only_String02(GetParaCdSeq);	//ナンバリング
		GetParaInt01	= B00020ToolsTextControl.num_only_String02(GetParaInt01);	//パラメータ数値項目01
		GetParaInt02	= B00020ToolsTextControl.num_only_String02(GetParaInt02);	//パラメータ数値項目02
		GetParaInt03	= B00020ToolsTextControl.num_only_String02(GetParaInt03);	//パラメータ数値項目03
		GetParaInt04	= B00020ToolsTextControl.num_only_String02(GetParaInt04);	//パラメータ数値項目04
		GetParaInt05	= B00020ToolsTextControl.num_only_String02(GetParaInt05);	//パラメータ数値項目05
		GetParaInt06	= B00020ToolsTextControl.num_only_String02(GetParaInt06);	//パラメータ数値項目06
		GetParaInt07	= B00020ToolsTextControl.num_only_String02(GetParaInt07);	//パラメータ数値項目07
		GetParaInt08	= B00020ToolsTextControl.num_only_String02(GetParaInt08);	//パラメータ数値項目08
		GetParaInt09	= B00020ToolsTextControl.num_only_String02(GetParaInt09);	//パラメータ数値項目09
		GetParaInt10	= B00020ToolsTextControl.num_only_String02(GetParaInt10);	//パラメータ数値項目10
		
		float WFT = (float)0;
		
		WFT = Float.parseFloat(GetParaCdSeq); GetParaCdSeq	= ""+(int)WFT;	//ナンバリング
		WFT = Float.parseFloat(GetParaInt01); GetParaInt01	= ""+(int)WFT;	//パラメータ数値項目01
		WFT = Float.parseFloat(GetParaInt02); GetParaInt02	= ""+(int)WFT;	//パラメータ数値項目02
		WFT = Float.parseFloat(GetParaInt03); GetParaInt03	= ""+(int)WFT;	//パラメータ数値項目03
		WFT = Float.parseFloat(GetParaInt04); GetParaInt04	= ""+(int)WFT;	//パラメータ数値項目04
		WFT = Float.parseFloat(GetParaInt05); GetParaInt05	= ""+(int)WFT;	//パラメータ数値項目05
		WFT = Float.parseFloat(GetParaInt06); GetParaInt06	= ""+(int)WFT;	//パラメータ数値項目06
		WFT = Float.parseFloat(GetParaInt07); GetParaInt07	= ""+(int)WFT;	//パラメータ数値項目07
		WFT = Float.parseFloat(GetParaInt08); GetParaInt08	= ""+(int)WFT;	//パラメータ数値項目08
		WFT = Float.parseFloat(GetParaInt09); GetParaInt09	= ""+(int)WFT;	//パラメータ数値項目09
		WFT = Float.parseFloat(GetParaInt10); GetParaInt10	= ""+(int)WFT;	//パラメータ数値項目10
		
		if("".equals(GetParaCdSeq)	) {GetParaCdSeq		= "0";}	//ナンバリング
		if("".equals(GetParaInt01)	) {GetParaInt01		= "0";}	//パラメータ数値項目01
		if("".equals(GetParaInt02)	) {GetParaInt02		= "0";}	//パラメータ数値項目02
		if("".equals(GetParaInt03)	) {GetParaInt03		= "0";}	//パラメータ数値項目03
		if("".equals(GetParaInt04)	) {GetParaInt04		= "0";}	//パラメータ数値項目04
		if("".equals(GetParaInt05)	) {GetParaInt05		= "0";}	//パラメータ数値項目05
		if("".equals(GetParaInt06)	) {GetParaInt06		= "0";}	//パラメータ数値項目06
		if("".equals(GetParaInt07)	) {GetParaInt07		= "0";}	//パラメータ数値項目07
		if("".equals(GetParaInt08)	) {GetParaInt08		= "0";}	//パラメータ数値項目08
		if("".equals(GetParaInt09)	) {GetParaInt09		= "0";}	//パラメータ数値項目09
		if("".equals(GetParaInt10)	) {GetParaInt10		= "0";}	//パラメータ数値項目10
		
		
		
		String now_dtm = B00050ToolsDateTimeControl.dtmString2(B00050ToolsDateTimeControl.dtm()[1])[1];
		boolean Err = ErrCheck(
				GetClWh,		//担当倉庫コード
				GetClCd,		//荷主コード
				GetParaCd,		//パラメータコード
				GetParaCdSeq,	//ナンバリング
				GetParaName,	//パラメータ名
				GetParaTxt01,	//パラメータテキスト項目01
				GetParaTxt02,	//パラメータテキスト項目02
				GetParaTxt03,	//パラメータテキスト項目03
				GetParaTxt04,	//パラメータテキスト項目04
				GetParaTxt05,	//パラメータテキスト項目05
				GetParaTxt06,	//パラメータテキスト項目06
				GetParaTxt07,	//パラメータテキスト項目07
				GetParaTxt08,	//パラメータテキスト項目08
				GetParaTxt09,	//パラメータテキスト項目09
				GetParaTxt10,	//パラメータテキスト項目10
				GetParaInt01,	//パラメータ数値項目01
				GetParaInt02,	//パラメータ数値項目02
				GetParaInt03,	//パラメータ数値項目03
				GetParaInt04,	//パラメータ数値項目04
				GetParaInt05,	//パラメータ数値項目05
				GetParaInt06,	//パラメータ数値項目06
				GetParaInt07,	//パラメータ数値項目07
				GetParaInt08,	//パラメータ数値項目08
				GetParaInt09,	//パラメータ数値項目09
				GetParaInt10	//パラメータ数値項目10
				);
		
		if(!Err) {
			String[][] SetString = {
					 {"ClWh"		,"1","1",GetClWh}		//担当倉庫コード
					,{"ClCd"		,"1","1",GetClCd}		//荷主コード
					,{"ParaCd"		,"1","1",GetParaCd}			//パラメータコード
					,{"ParaCdSeq"	,"1","1",GetParaCdSeq}		//ナンバリング
					,{"ParaName"	,"1","1",GetParaName}		//パラメータ名
					,{"ParaTxt01"	,"1","1",GetParaTxt01}		//パラメータテキスト項目01
					,{"ParaTxt02"	,"1","1",GetParaTxt02}		//パラメータテキスト項目02
					,{"ParaTxt03"	,"1","1",GetParaTxt03}		//パラメータテキスト項目03
					,{"ParaTxt04"	,"1","1",GetParaTxt04}		//パラメータテキスト項目04
					,{"ParaTxt05"	,"1","1",GetParaTxt05}		//パラメータテキスト項目05
					,{"ParaTxt06"	,"1","1",GetParaTxt06}		//パラメータテキスト項目06
					,{"ParaTxt07"	,"1","1",GetParaTxt07}		//パラメータテキスト項目07
					,{"ParaTxt08"	,"1","1",GetParaTxt08}		//パラメータテキスト項目08
					,{"ParaTxt09"	,"1","1",GetParaTxt09}		//パラメータテキスト項目09
					,{"ParaTxt10"	,"1","1",GetParaTxt10}		//パラメータテキスト項目10
					,{"ParaInt01"	,"1","1",GetParaInt01}		//パラメータ数値項目01
					,{"ParaInt02"	,"1","1",GetParaInt02}		//パラメータ数値項目02
					,{"ParaInt03"	,"1","1",GetParaInt03}		//パラメータ数値項目03
					,{"ParaInt04"	,"1","1",GetParaInt04}		//パラメータ数値項目04
					,{"ParaInt05"	,"1","1",GetParaInt05}		//パラメータ数値項目05
					,{"ParaInt06"	,"1","1",GetParaInt06}		//パラメータ数値項目06
					,{"ParaInt07"	,"1","1",GetParaInt07}		//パラメータ数値項目07
					,{"ParaInt08"	,"1","1",GetParaInt08}		//パラメータ数値項目08
					,{"ParaInt09"	,"1","1",GetParaInt09}		//パラメータ数値項目09
					,{"ParaInt10"	,"1","1",GetParaInt10}		//パラメータ数値項目10
					,{"EntryDate"	,"1","0",now_dtm}		//登録日
					,{"UpdateDate"	,"1","1",now_dtm}		//更新日
					,{"EntryUser"	,"1","0","(" + A00000Main.LoginUserId + ")" + A00000Main.LoginUserName}		//登録者
					,{"UpdateUser"	,"1","1","(" + A00000Main.LoginUserId + ")" + A00000Main.LoginUserName}		//更新者
			};
			
			String tgt_table = "WM0000PARAMETER";
			String[][] field_name = new String[SetString.length][3];
			String[][] entry_data = new String[1][SetString.length];
			String[] judg_field = new String[4];
			String[][] judg_data = new String[1][4];
			String TgtDB = "WANKO";
			int non_msg_fg = 1;

			judg_field[0] = "ClWh";			//担当倉庫コード
			judg_field[1] = "ClCd";			//荷主コード
			judg_field[2] = "ParaCd";		//注意事項コード
			judg_field[3] = "ParaCdSeq";	//運送タイプコード
			
			judg_data[0][0] = GetClWh;
			judg_data[0][1] = GetClCd;
			judg_data[0][2] = GetParaCd;
			judg_data[0][3] = GetParaCdSeq;
			
			for(int i=0;i<SetString.length;i++) {
				field_name[i][0] = (String)SetString[i][0];
				field_name[i][1] = (String)SetString[i][1];
				field_name[i][2] = (String)SetString[i][2];
				entry_data[0][i] = (String)SetString[i][3];
			}
			
			A00020InsertUdateSQL.RUN_SQLS_EU(tgt_table, field_name, entry_data, judg_field, judg_data, non_msg_fg,TgtDB);
		}
		return Err;
	}
	
	private static boolean ErrCheck(
			String GetClWh,			//担当倉庫コード
			String GetClCd,			//荷主コード
			String GetParaCd,		//パラメータコード
			String GetParaCdSeq,	//ナンバリング
			String GetParaName,		//パラメータ名
			String GetParaTxt01,	//パラメータテキスト項目01
			String GetParaTxt02,	//パラメータテキスト項目02
			String GetParaTxt03,	//パラメータテキスト項目03
			String GetParaTxt04,	//パラメータテキスト項目04
			String GetParaTxt05,	//パラメータテキスト項目05
			String GetParaTxt06,	//パラメータテキスト項目06
			String GetParaTxt07,	//パラメータテキスト項目07
			String GetParaTxt08,	//パラメータテキスト項目08
			String GetParaTxt09,	//パラメータテキスト項目09
			String GetParaTxt10,	//パラメータテキスト項目10
			String GetParaInt01,	//パラメータ数値項目01
			String GetParaInt02,	//パラメータ数値項目02
			String GetParaInt03,	//パラメータ数値項目03
			String GetParaInt04,	//パラメータ数値項目04
			String GetParaInt05,	//パラメータ数値項目05
			String GetParaInt06,	//パラメータ数値項目06
			String GetParaInt07,	//パラメータ数値項目07
			String GetParaInt08,	//パラメータ数値項目08
			String GetParaInt09,	//パラメータ数値項目09
			String GetParaInt10	//パラメータ数値項目10
			) {
		boolean rt = false;
		
		if(null==GetParaCd 		) {GetParaCd = "";}
		if(null==GetParaName 	) {GetParaName = "";}
		
		GetParaCd 		= B00020ToolsTextControl.Trim(GetParaCd);
		GetParaName 	= B00020ToolsTextControl.Trim(GetParaName);
		
		if("".equals(GetParaCd)||"".equals(GetParaName)) {
			JOptionPane.showMessageDialog(null, "パラメータコード・パラーメータ名は必須です");
			rt = true;
		}
		return rt;
	}
	
	
	
}