import javax.swing.table.DefaultTableModel;

public class B10010TableControl{
	//明細業表示テーブル制御
	//1列目のみ編集可
	static class MyTableModel01 extends DefaultTableModel{
		MyTableModel01(String[] columnNames, int rowNum){
			super(columnNames, rowNum);
		}
		//N列目のデータは項目のクラス取得する
		public Class getColumnClass(int col){
			if(0==col){
				Object ob = getValueAt(0,col);
				return ob.getClass();
			}else{
				Object ob = new Object();
				return ob.getClass();
			}
		}
		//1列目以外編集不可
		public boolean isCellEditable(int row, int column) {
			Object flag = this.getValueAt(0, column);
			if (column==0) {
				return true;
			}else{
				return false;
			}
		}
	}
}