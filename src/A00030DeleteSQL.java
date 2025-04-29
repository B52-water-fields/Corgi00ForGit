import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class A00030DeleteSQL{
	public static void DeleteSql(String tgt_table,String[] judg_field,String[][] judg_data,int non_msg_fg,String TgtDB){
		//データ削除をまとめて行う
		//tgt_table		：対象テーブル名
		//judg_field	：存在チェックフィールド名	※WHWRE条件
		//judg_data		：存在チェックデータ値		※WHERE条件値　完全一致のみ考慮

		ResultSet rset01=null;
		PreparedStatement stmt01=null;
		A00010DbConnect.DB_CONN(TgtDB);

		if(null==tgt_table){tgt_table="";}
		if(0!=non_msg_fg){non_msg_fg=1;}
		if(null!=judg_data && 0<judg_data.length && null!=judg_field && 0<judg_field.length) {
			String sql="";	//sql文
			int del_count = 0;
			sql = "delete from "+tgt_table+" where ";

			for(int i=0;i<judg_field.length;i++) {
				if(0<i) {
					sql = sql+ " and ";
				}
				sql = sql+ judg_field[i] + "=?";
			}
			//System.out.println(sql);
			try {
				stmt01 = A00010DbConnect.conn.prepareStatement(sql);
				for(int i01=0;i01<judg_data.length;i01++) {
					for(int i02=0;i02<judg_data[i01].length;i02++) {
						stmt01.setString(i02+1, judg_data[i01][i02]);
						//System.out.println(judg_data[i01][i02]);
					}
					stmt01.executeUpdate();
					if(null==A00010DbConnect.session) {
						A00010DbConnect.conn.commit();
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
				try {
					if(rset01!=null){rset01.close();}
					if(stmt01!=null){stmt01.close();}
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}finally{
				try {
					if(rset01!=null){rset01.close();}
					if(stmt01!=null){stmt01.close();}
					System.gc();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
			A00010DbConnect.close();
			if(non_msg_fg!=1){
				JOptionPane.showMessageDialog(null, del_count + "件のデータ削除しました");
			}
		}
	}
}