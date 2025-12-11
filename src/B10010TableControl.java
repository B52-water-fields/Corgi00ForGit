import javax.swing.table.DefaultTableModel;

public class B10010TableControl{
	//明細業表示テーブル制御
	//RenewTgt列のみ編集可
	static int[] RenewTgt;
	static class MyTableModel01 extends DefaultTableModel{
		MyTableModel01(String[] columnNames, int rowNum){
			super(columnNames, rowNum);
		}
		//RenewTgtのデータは項目のクラス取得する
		public Class getColumnClass(int col){
			boolean Renew = false;
			if(null==RenewTgt) {RenewTgt = new int[0];}
			for(int i=0;i<RenewTgt.length;i++) {
				if(RenewTgt[i]==col) {
					Renew = true;
				}
			}
			
			if(Renew){
				Object ob = getValueAt(0,col);
				return ob.getClass();
			}else{
				Object ob = new Object();
				return ob.getClass();
			}
		}
		//RenewTgt以外編集不可
		public boolean isCellEditable(int row, int column) {
			Object flag = this.getValueAt(0, column);
			boolean Renew = false;
			if(null==RenewTgt) {RenewTgt = new int[0];}
			for(int i=0;i<RenewTgt.length;i++) {
				if(RenewTgt[i]==column) {
					Renew = true;
				}
			}
			
			if(Renew){
				return true;
			}else{
				return false;
			}
		}
	}
}