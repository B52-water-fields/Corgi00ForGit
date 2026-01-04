import javax.swing.JTextField;

public class B00111FramePartsControl{
	
	/*****************************************************
	テキストボックスの値を取得して文字列を返却する
	*****************************************************/
	
	public static String JTextFieldRt(JTextField Tgt) {
		/*
		コピペ用
		B00111FramePartsControl.JTextFieldRt(JTextField Tgt);
		*/
		
		String Rt = Tgt.getText();
		if(null==Rt	){Rt	= "";}
		Rt = B00020ToolsTextControl.Trim(Rt);
		return Rt;
	}
	
}