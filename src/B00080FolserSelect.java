import javax.swing.JFileChooser;

public class B00080FolserSelect{
	//フォルダを選択し、選択結果をフルパスのテキストで返却する
	static String MSG;
	public static String FolserSelect(String f1) {
		MSG = f1;
		String selected = null;
		final B00080FolserSelect frame = new B00080FolserSelect();
		if(null==f1||"".equals(f1)) {MSG="フォルダ選択";}
		int result = frame.fileChooser.showDialog(null, "決定");
		if (result != JFileChooser.APPROVE_OPTION){

		}else {
			selected = frame.fileChooser.getSelectedFile().getAbsolutePath();
		}
		return selected;

	}

	private JFileChooser fileChooser;
	public B00080FolserSelect(){
		this.fileChooser = new JFileChooser("/");
		this.fileChooser.setDialogTitle(MSG);
		this.fileChooser.setFileSelectionMode(
				JFileChooser.DIRECTORIES_ONLY);
	}
}