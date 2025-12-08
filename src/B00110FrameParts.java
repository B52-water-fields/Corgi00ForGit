import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class B00110FrameParts{
	static int Width;
	static int Height;
	
	
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
		fm.setTitle(Title + "　荷主：(" + A00000Main.ClCd + ")" + A00000Main.ClName + "　担当倉庫：("+A00000Main.ClWh+")"+A00000Main.ClWhName);
		//表示位置サイズの設定（表示横位置,表示縦位置,横幅,縦幅）
		fm.setBounds(x, y, GetWidth*A00000Main.Mul/A00000Main.Div, GetHeight*A00000Main.Mul/A00000Main.Div);
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
		JLabel userinfo = new JLabel("(" + A00000Main.LoginUserId + ")" + A00000Main.LoginUserName+"　所属：(" + A00000Main.LoginUserWH + ")" + A00000Main.LoginUserWhName);
		userinfo.setFont(new Font(A00000Main.DefaultFont, Font.PLAIN, 11*A00000Main.Mul/A00000Main.Div));
		userinfo.setBounds(10*A00000Main.Mul/A00000Main.Div, 10*A00000Main.Mul/A00000Main.Div, 500*A00000Main.Mul/A00000Main.Div, 20*A00000Main.Mul/A00000Main.Div);
		return userinfo;
	}
	
	/*===========================================================================
	 	基本のボタン配置
	===========================================================================*/
	public static JButton ExitBtn() {
		//EXITボタンを画面右上に配置
		JButton exit_btn=new JButton();
		exit_btn.setText("EXIT");
		exit_btn.setFont(new Font(A00000Main.DefaultFont, Font.PLAIN, 11*A00000Main.Mul/A00000Main.Div));
		exit_btn.setBounds((Width-140)*A00000Main.Mul/A00000Main.Div, 10*A00000Main.Mul/A00000Main.Div, 100*A00000Main.Mul/A00000Main.Div, 20*A00000Main.Mul/A00000Main.Div);
		
		return exit_btn;
	}
	
	public static JButton EntryBtn() {
		//登録ボタンを画面右下に配置
		JButton Entry_btn=new JButton();
		Entry_btn.setText("Entry");
		Entry_btn.setFont(new Font(A00000Main.DefaultFont, Font.PLAIN, 11*A00000Main.Mul/A00000Main.Div));
		Entry_btn.setBounds((Width-140)*A00000Main.Mul/A00000Main.Div, (Height-90)*A00000Main.Mul/A00000Main.Div, 100*A00000Main.Mul/A00000Main.Div, 20*A00000Main.Mul/A00000Main.Div);
		
		return Entry_btn;
	}
	
	public static JButton BtnSet(int x,int y,int Width,int Height,String TextSet,int TextSize) {
		//任意ボタンを指定位置に指定サイズで配置
		if(0>=TextSize) {TextSize=11;}
		JButton Entry_btn=new JButton();
		Entry_btn.setText(TextSet);
		Entry_btn.setFont(new Font(A00000Main.DefaultFont, Font.PLAIN, TextSize*A00000Main.Mul/A00000Main.Div));
		Entry_btn.setBounds(x*A00000Main.Mul/A00000Main.Div,y*A00000Main.Mul/A00000Main.Div, Width*A00000Main.Mul/A00000Main.Div, Height*A00000Main.Mul/A00000Main.Div);
		
		return Entry_btn;
	}
	
	/*===========================================================================
 	ラベル配置
	===========================================================================*/
	public static JLabel JLabelSet(int x,int y,int Width,int Height,String DefaultText,int TextSize,int set) {
		//テキスト入力BOX
		if(0>=TextSize) {TextSize=11;}
		JLabel rt = new JLabel(DefaultText);
		rt.setFont(new Font(  A00000Main.DefaultFont, Font.PLAIN, TextSize*A00000Main.Mul/A00000Main.Div));
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
		rt.setBounds( x*A00000Main.Mul/A00000Main.Div, y*A00000Main.Mul/A00000Main.Div, Width*A00000Main.Mul/A00000Main.Div, Height*A00000Main.Mul/A00000Main.Div);
		
		return rt;
	}
	
	/*===========================================================================
 	コンボBOX配置
	===========================================================================*/
	public static JComboBox JComboBoxSet(int x,int y,int Width,int Height,Object[] SetOb,int TextSize) {
		//テキスト入力BOX
		if(0>=TextSize) {TextSize=11;}
		JComboBox rt = new JComboBox(SetOb);
		rt.setFont(new Font(  A00000Main.DefaultFont, Font.PLAIN, TextSize*A00000Main.Mul/A00000Main.Div));
		
		rt.setBounds( x*A00000Main.Mul/A00000Main.Div, y*A00000Main.Mul/A00000Main.Div, Width*A00000Main.Mul/A00000Main.Div, Height*A00000Main.Mul/A00000Main.Div);
		
		return rt;
	}
	
	/*===========================================================================
 	入力BOX配置
	===========================================================================*/
	public static JTextField JTextFieldSet(int x,int y,int Width,int Height,String DefaultText,int TextSize,int set) {
		//テキスト入力BOX
		if(0>=TextSize) {TextSize=11;}
		JTextField rt = new JTextField(DefaultText);
		rt.setFont(new Font(  A00000Main.DefaultFont, Font.PLAIN, TextSize*A00000Main.Mul/A00000Main.Div));
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
		rt.setBounds( x*A00000Main.Mul/A00000Main.Div, y*A00000Main.Mul/A00000Main.Div, Width*A00000Main.Mul/A00000Main.Div, Height*A00000Main.Mul/A00000Main.Div);
		
		return rt;
	}
	
	public static JPasswordField JPasswordFieldSet(int x,int y,int Width,int Height,String DefaultText,int TextSize,int set) {
		//パスワード入力BOX
		if(0>=TextSize) {TextSize=11;}
		JPasswordField rt = new JPasswordField(DefaultText);
		rt.setFont(new Font(  A00000Main.DefaultFont, Font.PLAIN, TextSize*A00000Main.Mul/A00000Main.Div));
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
		rt.setBounds( x*A00000Main.Mul/A00000Main.Div, y*A00000Main.Mul/A00000Main.Div, Width*A00000Main.Mul/A00000Main.Div, Height*A00000Main.Mul/A00000Main.Div);
		
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
		
		rt.setFont(new Font(  A00000Main.DefaultFont, Font.PLAIN, TextSize*A00000Main.Mul/A00000Main.Div));
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
		rt.setBounds( x*A00000Main.Mul/A00000Main.Div, y*A00000Main.Mul/A00000Main.Div, Width*A00000Main.Mul/A00000Main.Div, Height*A00000Main.Mul/A00000Main.Div);
		
		return rt;
	}
	
	/*===========================================================================
 	パネル配置
	===========================================================================*/
	public static JPanel JPanelSet(int x,int y,int GetWidth,int GetHeight,String ColorType) {
		JPanel PN = new JPanel();
		
		PN.setBounds(x*A00000Main.Mul/A00000Main.Div,y*A00000Main.Mul/A00000Main.Div,GetWidth*A00000Main.Mul/A00000Main.Div,GetHeight*A00000Main.Mul/A00000Main.Div);
		PN.setLayout(null);
		PN.setBackground(SelectColer(ColorType));
		return PN;
	}
	
	
	
	
	
	
	
	
	
	
	
}