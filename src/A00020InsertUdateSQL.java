import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JOptionPane;

public class A00020InsertUdateSQL{
	//
	//データの登録更新をまとめて行います
	// ==========================================================================
    //  A00020InsertUdateSQL（ウリエル／判断と裁定の天使　データベースの園の門番）
    // ==========================================================================
    //
    //このクラスは、以下のデータを引き渡された際に、データの登録・更新を判断し、データベースに書き込みます。
	//
	//tgt_table		：対象テーブル名
	//
	//field_name	：field_name[i][0]テーブルフィールド名
	//
	//judg_field	：存在チェックフィールド名	※WHERE条件
	//				　field_name[i][1]登録対象区分　"1"が対象	※全て0なら登録を行わない
	//				　field_name[i][2]更新対象区分　"1"が対象　 ※全て0なら更新を行わない
	//
	//entry_data	：登録データentry_data[i01][i02]　i01：登録データ明細行数 i02：各フィールドに対応する値
	//				  登録データentry_data[i01][i02]の値が"null"だった場合NULLセット
	//
	//judg_data		：存在チェックデータ値		※WHERE条件値　完全一致のみ考慮
	//				  judg_data[i01][i02]　i01：登録データ明細行数 i02：WHERE条件各フィールドに対応する値
	//				  
	//non_msg_fg	：1なら完了時にメッセージ表示しない
	//
	//
	//羊の群れに対応する前提：複数件のデータがバッチ的に処理されることを想定しています（もちろん　judg_dataとentry_dataが1行1件でも普通に動きます）
    //
    // ==========================================================================
	
	public static void RUN_SQLS_EU(String tgt_table,String[][] field_name,String[][] entry_data,String[] judg_field,String[][] judg_data,int non_msg_fg,String TgtDB){
		//データの登録更新をまとめて行う

		ResultSet rset01=null;
		PreparedStatement stmt01=null;
		PreparedStatement stmt02=null;
		PreparedStatement stmt03=null;
		A00010DbConnect.DB_CONN(TgtDB);

		if(null==tgt_table){tgt_table="";}
		if(0!=non_msg_fg){non_msg_fg=1;}
		if(
				!("".equals(tgt_table))
				&& field_name.length>0
				&& entry_data.length>0
				&& judg_field.length>0
				&& judg_data.length>0
		){
			//受け取ったデータの後方空白文字をカットする
			for(int i01=0;i01<field_name.length;i01++){
				for(int i02=0;i02<field_name[i01].length;i02++){
					if(null==field_name[i01][i02]){field_name[i01][i02]="";}
					field_name[i01][i02] = B00020ToolsTextControl.Trim(field_name[i01][i02]);
				}
			}
			for(int i01=0;i01<entry_data.length;i01++){
				for(int i02=0;i02<entry_data[i01].length;i02++){
					if(null==entry_data[i01][i02]){entry_data[i01][i02]="";}
					entry_data[i01][i02] = B00020ToolsTextControl.Trim(entry_data[i01][i02]);
				}
			}
			for(int i01=0;i01<judg_field.length;i01++){
					if(null==judg_field[i01]){judg_field[i01]="";}
					judg_field[i01] = B00020ToolsTextControl.Trim(judg_field[i01]);
			}
			for(int i01=0;i01<judg_data.length;i01++){
				for(int i02=0;i02<judg_data[i01].length;i02++){
					if(null==judg_data[i01][i02]){judg_data[i01][i02]="";}
					judg_data[i01][i02] = B00020ToolsTextControl.Trim(judg_data[i01][i02]);
				}
			}
			//現在の日付時刻の取得
			Calendar cal = Calendar.getInstance();
			final Timestamp ps = new Timestamp(cal.getTimeInMillis());
			final SimpleDateFormat sdf01 = new SimpleDateFormat("yyyy'/'MM'/'dd' 'HH':'mm':'ss");
			String str_time = sdf01.format(ps);

			String sql="";	//sql文
			int ud_count = 0;
			int ent_count = 0;
			int ci = 0;
			int update_check = 0;	//1ならアップデートする
			int entry_check = 0;	//1なら登録する
			try {
				//prepareStatementの生成
				//データ存在チェック用SELSECT文
				sql = "select "
						+ field_name[0][0]
					+ " from "
						+ tgt_table;
				for(int i=0;i<judg_field.length;i++){
					if(ci==0){
						sql = sql + " where ";
						ci=1;
					}else{
						sql = sql + " and ";
					}
					sql = sql + judg_field[i] + "=?";
				}
				//System.out.println(sql);
				stmt01 = A00010DbConnect.conn.prepareStatement(sql);

				//データ登録用INSERT文
				ci=0;
				sql = "insert into " + tgt_table+"(";
				for(int i=0;i<field_name.length;i++){
					if("1".equals(field_name[i][1])){
						entry_check = 1;
						if(ci==0){
							ci=1;
						}else{
							sql = sql + ",";
						}
						sql = sql + field_name[i][0];
					}
				}
				ci=0;
				sql = sql + ") values (";
				for(int i=0;i<field_name.length;i++){
					if("1".equals(field_name[i][1])){
						if(ci==0){
							ci=1;
						}else{
							sql = sql + ",";
						}
						sql = sql + "?";
					}
				}
				sql = sql +")";
				//System.out.println(sql);
				stmt02 = A00010DbConnect.conn.prepareStatement(sql);

				//データ更新用UPDATE文
				ci=0;
				sql = "update " + tgt_table + " set ";
				for(int i=0;i<field_name.length;i++){
					if("1".equals(field_name[i][2])){
						update_check = 1;
						if(ci==0){
							ci=1;
						}else{
							sql = sql + ",";
						}
						sql = sql + field_name[i][0]+" = ?";
					}
				}
				for(int i=0;i<judg_field.length;i++){
					if(i==0){
						sql = sql+" where ";
					}else{
						sql = sql+" and ";
					}
					sql=sql + judg_field[i]+"=?";
				}
				//System.out.println(sql);
				stmt03 = A00010DbConnect.conn.prepareStatement(sql);

				for(int i01=0;i01<entry_data.length;i01++){
					int hit_fg = 0;
					int err_flg = 0;
					//データ存在チェック
					for(int i02=0;i02<judg_data[i01].length;i02++){
						if(judg_data[i01][i02]==null){judg_data[i01][i02]="";}
						stmt01.setString(i02+1, judg_data[i01][i02]);
					}
					rset01 = stmt01.executeQuery();
					while (rset01.next()) {
						hit_fg = 1;
					}
					rset01.close();
					if(hit_fg==0){
						if(entry_check == 1){
							//データ追加
							int ind_no = 0;
							for(int i02=0;i02<entry_data[i01].length;i02++){
								if("1".equals(field_name[i02][1])){
									if("null".equals(entry_data[i01][i02])||"NULL".equals(entry_data[i01][i02])) {
										stmt02.setString(ind_no+1, null);
									}else{
										stmt02.setString(ind_no+1, entry_data[i01][i02]);
									}
									ind_no = ind_no + 1;
								}
							}
							ent_count = ent_count + 1;
							stmt02.executeUpdate();
							if(null==A00010DbConnect.session) {
								A00010DbConnect.conn.commit();
							}
						}
					}else{
						if(update_check == 1){
							//データ更新
							int ind_no = 0;
							for(int i02=0;i02<entry_data[i01].length;i02++){
								if("1".equals(field_name[i02][2])){
									if("null".equals(entry_data[i01][i02])||"NULL".equals(entry_data[i01][i02])) {
										stmt03.setString(ind_no+1, null);
									}else{
										stmt03.setString(ind_no+1, entry_data[i01][i02]);
									}
									ind_no = ind_no + 1;
								}
							}
							for(int i02=0;i02<judg_data[i01].length;i02++){
								if(judg_data[i01][i02]==null){judg_data[i01][i02]="";}
								stmt03.setString(ind_no + i02 + 1, judg_data[i01][i02]);
							}
							ud_count=ud_count + 1;
							stmt03.executeUpdate();
							if(null==A00010DbConnect.session) {
								A00010DbConnect.conn.commit();
							}
						}
					}
				}
				if(rset01!=null){rset01.close();}
				if(stmt01!=null){stmt01.close();}
				if(stmt02!=null){stmt02.close();}
				if(stmt03!=null){stmt03.close();}
			} catch (SQLException e) {
				e.printStackTrace();
				try {
					if(rset01!=null){rset01.close();}
					if(stmt01!=null){stmt01.close();}
					if(stmt02!=null){stmt02.close();}
					if(stmt03!=null){stmt03.close();}
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}finally{
				try {
					if(rset01!=null){rset01.close();}
					if(stmt01!=null){stmt01.close();}
					if(stmt02!=null){stmt02.close();}
					if(stmt03!=null){stmt03.close();}
					System.gc();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
			A00010DbConnect.close();
			//現在の日付時刻の取得
			Calendar cal2 = Calendar.getInstance();
			final Timestamp ps2 = new Timestamp(cal2.getTimeInMillis());
			String end_time = sdf01.format(ps2);
			if(non_msg_fg!=1){
				JOptionPane.showMessageDialog(null, ent_count + "件の登録と "+ud_count+"件の更新を行いました " + str_time + "→" + end_time);
			}
		}
		A00010DbConnect.close();
	}
}