import javax.swing.JTextField;

public class B100_FramePartsControl{
	
	/*****************************************************
	テキストボックスの値を取得して文字列を返却する
	*****************************************************/
	
	public static String JTextFieldRt(JTextField Tgt) {
		/*
		コピペ用
		B100_FramePartsControl.JTextFieldRt(JTextField Tgt);
		*/
		
		String Rt = Tgt.getText();
		if(null==Rt	){Rt	= "";}
		Rt = B100_TextControl.Trim(Rt);
		return Rt;
	}
	
}