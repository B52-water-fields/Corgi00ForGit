import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

public class B00090FileSelect{
	static String MSG;
	static String selected;
	static String[] file_type;
	static String file_type_name;
	static String TgtFLD;
	//ファイル選択し、選択結果をフルパスのテキストで返却する
	public static String FileSelect(String f1,String[] f2,String f3) {
		MSG = f1;
		file_type = f2;
		file_type_name = f3;
		selected = null;
		if(null==f1||"".equals(f1)) {MSG="ファイル選択";}
		if(null==f2||0==f2.length) {file_type= new String[2];file_type[0] = ".txt";file_type[1]=".csv";}
		if(null==f3||"".equals(f3)) {file_type_name="テキストファイル";}
		final B00090FileSelect frame = new B00090FileSelect();
		int result = frame.fileChooser.showDialog(null, "決定");
		if (result != JFileChooser.APPROVE_OPTION){

		}else {
			selected = frame.fileChooser.getSelectedFile().getAbsolutePath();
			TgtFLD = B00040ToolsFolderCheck.FILE_FLD(selected);
		}
		return selected;
	}
	
	private JFileChooser fileChooser;
	public B00090FileSelect(){
		if(null==TgtFLD||"".equals(TgtFLD)) {
			this.fileChooser = new JFileChooser("/");
		}else {
			this.fileChooser = new JFileChooser(TgtFLD);
		}
		this.fileChooser.setDialogTitle(MSG);
		this.fileChooser.setFileSelectionMode(
				JFileChooser.FILES_ONLY);
		this.fileChooser.setFileFilter(new TextFileFilter());
	}

	class TextFileFilter extends FileFilter{
		final String[] textExt = file_type;
		@Override
		public boolean accept(File file){
			if(file.isDirectory()){
				return true;
			}
			String fileName = file.getName();
			int idx =fileName.lastIndexOf('.');

			if (idx == -1){
				return false;
			}
			String ext = fileName.substring(idx).toLowerCase();
			for(int i=0;i<file_type.length;i++) {
				if(file_type[i].equals(ext)) {
					return true;
				}
			}
			return false;
		}
		@Override
		public String getDescription(){
			return file_type_name;
		}
	}
}