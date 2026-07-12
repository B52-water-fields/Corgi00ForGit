import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class B100_FrameParts{
	static int Width;
	static int Height;
	
	//フレームを FrameCreate(int x,int y,int GetWidth,int GetHeight,String Title,String ColorType)で宣言後、中のパーツの設定をしてください
	
	public static Color SelectColer(String ColorType) {
		Color BackGroundColor = Color.decode("#e1f0ff");
		switch(ColorType){
			case "NK": 
				BackGroundColor = Color.decode("#66cdaa");		//入荷系バックグラウンドカラー　ミントグリーン
				break;
			case "ZK": 
				BackGroundColor = Color.decode("#87cefa"); 	//在庫系バックグラウンドカラー　スカイブルー
				break;
			case "SPPlan": 
				BackGroundColor = Color.decode("#ff8c00"); 	//出荷予定系バックグラウンドカラー　ダークオレンジ
				break;
			case "SP": 
				BackGroundColor = Color.decode("#ffa500"); 	//出荷系バックグラウンドカラー　オレンジ
				break;
			case "HS": 
				BackGroundColor = Color.decode("#ffd700"); 	//配車系バックグラウンドカラー　ゴールド
				break;
			case "SK": 
				BackGroundColor = Color.decode("#ffc0cb"); 	//請求系バックグラウンドカラー　ピンク
				break;
			case "KN": 
				BackGroundColor = Color.decode("#ffe4e1"); 	//管理系バックグラウンドカラー　ミストローズ
				break;
			case "White":	
				BackGroundColor = Color.decode("#FFFFFF");
				break;
			case "Black":	
				BackGroundColor = Color.decode("#000000");
				break;
			case "Red":	
				BackGroundColor = Color.decode("#ff0000");
				break;
			case "Lavender":	
				BackGroundColor = Color.decode("#e6e6fa");
				break;
			case "Aquamarine":
				BackGroundColor = Color.decode("#7fffd4");
				break;
			case "Entry":	
				BackGroundColor = Color.decode("#FFE6CE");
				break;
			case "NoEntry":	
				BackGroundColor = Color.decode("#D3D3D3");
				break;
			default:
				break;
		}
		
		return BackGroundColor;
		
	}
	
	public static JFrame FrameCreate(int x,int y,int GetWidth,int GetHeight,String Title,String ColorType) {
		if(null==Title) {Title="";}
		if(null==ColorType) {ColorType="";}
		if(0>x) {x=0;}
		if(0>y) {y=0;}
		
		final JFrame fm = new JFrame();
		//ウィンドウタイトルの設定
		fm.setTitle(Title + "　荷主：(" + A00000_Main.ClCd + ")" + A00000_Main.ClName + "　担当倉庫：("+A00000_Main.ClWh+")"+A00000_Main.ClWhName);
		//表示位置サイズの設定（表示横位置,表示縦位置,横幅,縦幅）
		fm.setBounds(x, y, GetWidth*A00000_Main.Mul/A00000_Main.Div, GetHeight*A00000_Main.Mul/A00000_Main.Div);
		//レイアウト無効
		fm.setLayout(null);
		//背景色指定
		fm.getContentPane().setBackground(SelectColer(ColorType));
		//ウィンドウの閉じるボタンでプログラム終了しない
		//閉じるボタンでDBのコネクション閉じてから終了させたい為
		fm.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		Width  = GetWidth;
		Height = GetHeight;
		
		return fm;
	}
	
	/*===========================================================================
 		ログイン情報
	===========================================================================*/
	public static JLabel UserInfo() {
		JLabel userinfo = new JLabel("(" + A00000_Main.LoginUserId + ")" + A00000_Main.LoginUserName+"　所属：(" + A00000_Main.LoginUserWH + ")" + A00000_Main.LoginUserWhName);
		userinfo.setFont(new Font(A00000_Main.DefaultFont, Font.PLAIN, 10*A00000_Main.Mul/A00000_Main.Div));
		userinfo.setBounds(10*A00000_Main.Mul/A00000_Main.Div, 0*A00000_Main.Mul/A00000_Main.Div, 500*A00000_Main.Mul/A00000_Main.Div, 15*A00000_Main.Mul/A00000_Main.Div);
		return userinfo;
	}
	
	/*===========================================================================
	 	基本のボタン配置
	===========================================================================*/
	public static JButton ExitBtn() {
		//EXITボタンを画面右上に配置
		JButton exit_btn=new JButton();
		exit_btn.setText("EXIT");
		exit_btn.setFont(new Font(A00000_Main.DefaultFont, Font.PLAIN, 11*A00000_Main.Mul/A00000_Main.Div));
		exit_btn.setBounds((Width-140)*A00000_Main.Mul/A00000_Main.Div, 15*A00000_Main.Mul/A00000_Main.Div, 100*A00000_Main.Mul/A00000_Main.Div, 20*A00000_Main.Mul/A00000_Main.Div);
		
		return exit_btn;
	}
	
	public static JButton EntryBtn() {
		//登録ボタンを画面右下に配置
		JButton Entry_btn=new JButton();
		Entry_btn.setText("Entry");
		Entry_btn.setFont(new Font(A00000_Main.DefaultFont, Font.PLAIN, 11*A00000_Main.Mul/A00000_Main.Div));
		Entry_btn.setBounds((Width-140)*A00000_Main.Mul/A00000_Main.Div, (Height-90)*A00000_Main.Mul/A00000_Main.Div, 100*A00000_Main.Mul/A00000_Main.Div, 20*A00000_Main.Mul/A00000_Main.Div);
		
		return Entry_btn;
	}
	
	public static JButton AnyBtn(int y,String TextSet,int TextSize) {
		//任意ボタンを画面右(EXITボタンと縦に並ぶ位置)に配置
		JButton Entry_btn=new JButton();
		Entry_btn.setText(TextSet);
		Entry_btn.setFont(new Font(A00000_Main.DefaultFont, Font.PLAIN, TextSize*A00000_Main.Mul/A00000_Main.Div));
		Entry_btn.setBounds((Width-140)*A00000_Main.Mul/A00000_Main.Div, y*A00000_Main.Mul/A00000_Main.Div, 100*A00000_Main.Mul/A00000_Main.Div, 20*A00000_Main.Mul/A00000_Main.Div);
		
		return Entry_btn;
	}
	
	public static JButton BtnSet(int x,int y,int Width,int Height,String TextSet,int TextSize) {
		//任意ボタンを指定位置に指定サイズで配置
		if(0>=TextSize) {TextSize=11;}
		JButton Entry_btn=new JButton();
		Entry_btn.setText(TextSet);
		Entry_btn.setFont(new Font(A00000_Main.DefaultFont, Font.PLAIN, TextSize*A00000_Main.Mul/A00000_Main.Div));
		Entry_btn.setBounds(x*A00000_Main.Mul/A00000_Main.Div,y*A00000_Main.Mul/A00000_Main.Div, Width*A00000_Main.Mul/A00000_Main.Div, Height*A00000_Main.Mul/A00000_Main.Div);
		
		return Entry_btn;
	}
	
	/*===========================================================================
 	ラベル配置
	===========================================================================*/
	public static JLabel JLabelSet(int x,int y,int Width,int Height,String DefaultText,int TextSize,int set) {
		if(0>=TextSize) {TextSize=11;}
		JLabel rt = new JLabel(DefaultText);
		rt.setFont(new Font(  A00000_Main.DefaultFont, Font.PLAIN, TextSize*A00000_Main.Mul/A00000_Main.Div));
		switch(set) {
			case 0:
				rt.setHorizontalAlignment(JLabel.LEFT);
				break;
			case 1:
				rt.setHorizontalAlignment(JLabel.RIGHT);
				break;
			case 2:
				rt.setHorizontalAlignment(JLabel.CENTER);
				break;
			default:
				break;
		}
		rt.setBounds( x*A00000_Main.Mul/A00000_Main.Div, y*A00000_Main.Mul/A00000_Main.Div, Width*A00000_Main.Mul/A00000_Main.Div, Height*A00000_Main.Mul/A00000_Main.Div);
		
		return rt;
	}
	/*===========================================================================
 	ラベル画像配置
	===========================================================================*/
	public static JLabel JLabelPictSet(int x,int y,int Width,int Height,ImageIcon ItemImage01,int set) {
		JLabel rt = new JLabel(ItemImage01);
		rt.setBounds( x*A00000_Main.Mul/A00000_Main.Div, y*A00000_Main.Mul/A00000_Main.Div, Width*A00000_Main.Mul/A00000_Main.Div, Height*A00000_Main.Mul/A00000_Main.Div);
		
		return rt;
	}
	
	/*===========================================================================
 	コンボBOX配置
	===========================================================================*/
	public static JComboBox JComboBoxSet(int x,int y,int Width,int Height,Object[] SetOb,int TextSize) {
		//コンボBOX配置
		if(0>=TextSize) {TextSize=11;}
		JComboBox rt = new JComboBox(SetOb);
		rt.setFont(new Font(  A00000_Main.DefaultFont, Font.PLAIN, TextSize*A00000_Main.Mul/A00000_Main.Div));
		
		rt.setBounds( x*A00000_Main.Mul/A00000_Main.Div, y*A00000_Main.Mul/A00000_Main.Div, Width*A00000_Main.Mul/A00000_Main.Div, Height*A00000_Main.Mul/A00000_Main.Div);
		
		return rt;
	}
	/*===========================================================================
 	チェックBOX配置
	===========================================================================*/
	public static JCheckBox JCheckBoxSet(int x,int y,int Width,int Height,String SetMsg,int TextSize) {
		//チェックBOX配置
		if(0>=TextSize) {TextSize=11;}
		JCheckBox rt = new JCheckBox(SetMsg);
		rt.setFont(new Font(  A00000_Main.DefaultFont, Font.PLAIN, TextSize*A00000_Main.Mul/A00000_Main.Div));
		rt.setBounds( x*A00000_Main.Mul/A00000_Main.Div, y*A00000_Main.Mul/A00000_Main.Div, Width*A00000_Main.Mul/A00000_Main.Div, Height*A00000_Main.Mul/A00000_Main.Div);
		
		return rt;
	}
	
	
	/*===========================================================================
 	入力BOX配置
	===========================================================================*/
	public static JTextField JTextFieldSet(int x,int y,int Width,int Height,String DefaultText,int TextSize,int set) {
		//テキスト入力BOX
		if(0>=TextSize) {TextSize=11;}
		JTextField rt = new JTextField(DefaultText);
		rt.setFont(new Font(  A00000_Main.DefaultFont, Font.PLAIN, TextSize*A00000_Main.Mul/A00000_Main.Div));
		switch(set) {
			case 0:
				rt.setHorizontalAlignment(JLabel.LEFT);
				break;
			case 1:
				rt.setHorizontalAlignment(JLabel.RIGHT);
				break;
			case 2:
				rt.setHorizontalAlignment(JLabel.CENTER);
				break;
			default:
				break;
		}
		rt.setBounds( x*A00000_Main.Mul/A00000_Main.Div, y*A00000_Main.Mul/A00000_Main.Div, Width*A00000_Main.Mul/A00000_Main.Div, Height*A00000_Main.Mul/A00000_Main.Div);
		
		return rt;
	}
	
	public static JPasswordField JPasswordFieldSet(int x,int y,int Width,int Height,String DefaultText,int TextSize,int set) {
		//パスワード入力BOX
		if(0>=TextSize) {TextSize=11;}
		JPasswordField rt = new JPasswordField(DefaultText);
		rt.setFont(new Font(  A00000_Main.DefaultFont, Font.PLAIN, TextSize*A00000_Main.Mul/A00000_Main.Div));
		switch(set) {
			case 0:
				rt.setHorizontalAlignment(JLabel.LEFT);
				break;
			case 1:
				rt.setHorizontalAlignment(JLabel.RIGHT);
				break;
			case 2:
				rt.setHorizontalAlignment(JLabel.CENTER);
				break;
			default:
				break;
		}
		rt.setBounds( x*A00000_Main.Mul/A00000_Main.Div, y*A00000_Main.Mul/A00000_Main.Div, Width*A00000_Main.Mul/A00000_Main.Div, Height*A00000_Main.Mul/A00000_Main.Div);
		
		return rt;
	}
	
	/*===========================================================================
 	テキストエリア
	===========================================================================*/
	public static JTextArea JTextAreaSet(int TextSize) {
		final JTextArea rt = new JTextArea("");
		rt.setFont(new Font(A00000_Main.DefaultFont, Font.PLAIN, TextSize*A00000_Main.Mul/A00000_Main.Div));
		return rt;
	}
	
	public static JFormattedTextField JFormattedTextFieldSet(int x,int y,int Width,int Height,String DefaultText,int TextSize,int set,String Type) {
		//テキスト入力BOX
		//入力規制
		final DecimalFormat df = new DecimalFormat("####");
		final DecimalFormat float_df = new DecimalFormat("####.##");
		final DecimalFormat df1 = new DecimalFormat("###,###.###");
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy'/'MM'/'dd' 'HH':'mm':'ss");
		final SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy'/'MM'/'dd");
		final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy'/'MM");
		final DecimalFormat df2 = new DecimalFormat("#,###");
	
		if(0>=TextSize) {TextSize=11;}
		JFormattedTextField rt = new JFormattedTextField(df);
		
		switch(Type) {
			case"####":
				rt = new JFormattedTextField(df);
				break;
			case"#,###":
				rt = new JFormattedTextField(df2);
				break;
			case"####.##":
				rt = new JFormattedTextField(float_df);
				break;
			case"#,###.##":
				rt = new JFormattedTextField(df1);
				break;
			case"YYYY/MM/DD":
				rt = new JFormattedTextField(sdf1);
				break;
			case"YYYY/MM/DD HH:MM:SS":
				rt = new JFormattedTextField(sdf);
				break;
			case"YYYY/MM":
				rt = new JFormattedTextField(sdf2);
				break;
		}
		
		rt.setText(DefaultText);
		
		rt.setFont(new Font(  A00000_Main.DefaultFont, Font.PLAIN, TextSize*A00000_Main.Mul/A00000_Main.Div));
		switch(set) {
			case 0:
				rt.setHorizontalAlignment(JLabel.LEFT);
				break;
			case 1:
				rt.setHorizontalAlignment(JLabel.RIGHT);
				break;
			case 2:
				rt.setHorizontalAlignment(JLabel.CENTER);
				break;
			default:
				break;
		}
		rt.setBounds( x*A00000_Main.Mul/A00000_Main.Div, y*A00000_Main.Mul/A00000_Main.Div, Width*A00000_Main.Mul/A00000_Main.Div, Height*A00000_Main.Mul/A00000_Main.Div);
		
		return rt;
	}
	
	/*===========================================================================
 	パネル配置
	===========================================================================*/
	public static JPanel JPanelSet(int x,int y,int GetWidth,int GetHeight,String ColorType) {
		JPanel PN = new JPanel();
		
		PN.setBounds(x*A00000_Main.Mul/A00000_Main.Div,y*A00000_Main.Mul/A00000_Main.Div,GetWidth*A00000_Main.Mul/A00000_Main.Div,GetHeight*A00000_Main.Mul/A00000_Main.Div);
		PN.setLayout(null);
		PN.setBackground(SelectColer(ColorType));
		return PN;
	}
	
	/*===========================================================================
	テーブルセル表示位置設定用
	===========================================================================*/
	public static DefaultTableCellRenderer rightCellRenderer() {
			DefaultTableCellRenderer rightCellRenderer = new DefaultTableCellRenderer();
			rightCellRenderer.setHorizontalAlignment(JLabel.RIGHT);
			return rightCellRenderer;
			
	}
	public static DefaultTableCellRenderer leftCellRenderer() {
			DefaultTableCellRenderer leftCellRenderer = new DefaultTableCellRenderer();
			leftCellRenderer.setHorizontalAlignment(JLabel.LEFT);
			return leftCellRenderer;
	}
	public static DefaultTableCellRenderer centerCellRenderer() {
			DefaultTableCellRenderer centerCellRenderer =  new DefaultTableCellRenderer();
			centerCellRenderer.setHorizontalAlignment(JLabel.CENTER);
			return centerCellRenderer;
	}

	

	/*===========================================================================
 	スクロールパネル配置
	===========================================================================*/
	public static JScrollPane JScrollPaneSet(int x,int y,int Width,int Height,Component SetOb) {
		JScrollPane scpn01= new JScrollPane(SetOb);
		scpn01.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scpn01.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scpn01.setBounds(x*A00000_Main.Mul/A00000_Main.Div,y*A00000_Main.Mul/A00000_Main.Div,Width*A00000_Main.Mul/A00000_Main.Div,Height*A00000_Main.Mul/A00000_Main.Div);
		
		return scpn01;
	}
}