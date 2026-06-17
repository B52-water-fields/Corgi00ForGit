import javax.swing.JFileChooser;

public class B100_FolderSelect{
	//フォルダを選択し、選択結果をフルパスのテキストで返却する
	static String MSG;
	static String TgtFLD;
	public static String FolderSelect(String f1) {
		MSG = f1;
		String selected = null;
		final B100_FolderSelect frame = new B100_FolderSelect();
		if(null==f1||"".equals(f1)) {MSG="フォルダ選択";}
		int result = frame.fileChooser.showDialog(null, "決定");
		if (result != JFileChooser.APPROVE_OPTION){

		}else {
			selected = frame.fileChooser.getSelectedFile().getAbsolutePath();
			TgtFLD = selected;
		}
		return selected;

	}

	private JFileChooser fileChooser;
	public B100_FolderSelect(){
		if(null==TgtFLD||"".equals(TgtFLD)) {
			this.fileChooser = new JFileChooser("/");
		}else {
			this.fileChooser = new JFileChooser(TgtFLD);
		}
		this.fileChooser.setDialogTitle(MSG);
		this.fileChooser.setFileSelectionMode(
				JFileChooser.DIRECTORIES_ONLY);
	}
}