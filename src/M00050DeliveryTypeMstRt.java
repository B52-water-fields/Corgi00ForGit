import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class M00050DeliveryTypeMstRt{
	//戻り値カラム
	public static Object[][] RtSettingDeliveryTypeMstRt(){
		Object[][] RtSettingDeliveryTypeMstRt = {
				 {"DeliveryTypeCd"		,(int) 1	,"String"	,"運送タイプコード"}
				,{"DeliveryTypeName"	,(int) 2	,"String"	,"運送タイプ名"}
				,{"EntryDate"			,(int) 3	,"String"	,"データ登録日時"}
				,{"UpdateDate"			,(int) 4	,"String"	,"データ更新日時"}
				,{"EntryUser"			,(int) 5	,"String"	,"登録者コード"}
				,{"UpdateUser"			,(int) 6	,"String"	,"更新者コード"}
				};
		
		return RtSettingDeliveryTypeMstRt;
	}
	public static Object[][] DeliveryTypeMstRt(
			ArrayList<String> SearchDeliveryTypeNo,
			ArrayList<String> SearchDeliveryTypeCd,
			ArrayList<String> SearchDeliveryTypeName,
			boolean AllSearch){
		Object[][] rt = new Object[0][7];
		boolean SearchKick = false;
		if(AllSearch) {SearchKick = true;}
		String sql = " select "
		+"(KM0050_DELIVERY_TYPEMST.DeliveryTypeNo) as DeliveryTypeNo,"		//タイプ番号
		+"(KM0050_DELIVERY_TYPEMST.DeliveryTypeCd) as DeliveryTypeCd,"		//運送タイプコード
		+"(KM0050_DELIVERY_TYPEMST.DeliveryTypeName) as DeliveryTypeName,"	//運送タイプ名
		+"(KM0050_DELIVERY_TYPEMST.EntryDate) as EntryDate,"				//データ登録日時
		+"(KM0050_DELIVERY_TYPEMST.UpdateDate) as UpdateDate,"				//データ更新日時
		+"(KM0050_DELIVERY_TYPEMST.EntryUser) as EntryUser,"				//登録者コード
		+"(KM0050_DELIVERY_TYPEMST.UpdateUser) as UpdateUser"				//更新者コード
		+" from "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0050_DELIVERY_TYPEMST"
		+" where 1=1";

		if(null!=SearchDeliveryTypeNo&&0<SearchDeliveryTypeNo.size()) {
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchDeliveryTypeNo.size();i++) {
				if(i>0) {sql = sql + " or ";}
				sql = sql + "KM0050_DELIVERY_TYPEMST.DeliveryTypeNo = '"+ SearchDeliveryTypeNo.get(i) +"'";
			}
			sql = sql + ")";
		}

		if(null!=SearchDeliveryTypeCd&&0<SearchDeliveryTypeCd.size()) {
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchDeliveryTypeCd.size();i++) {
				if(i>0) {sql = sql + " or ";}
				sql = sql + "KM0050_DELIVERY_TYPEMST.DeliveryTypeCd = '"+ SearchDeliveryTypeCd.get(i) +"'";
			}
			sql = sql + ")";
		}

		if(null!=SearchDeliveryTypeName&&0<SearchDeliveryTypeName.size()) {
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchDeliveryTypeName.size();i++) {
				if(i>0) {sql = sql + " or ";}
				sql = sql + "KM0050_DELIVERY_TYPEMST.DeliveryTypeName like '%"+ SearchDeliveryTypeName.get(i) +"%'";
			}
			sql = sql + ")";
		}

		sql = sql + " order by KM0050_DELIVERY_TYPEMST.DeliveryTypeNo,KM0050_DELIVERY_TYPEMST.DeliveryTypeCd";

		//System.out.println(sql);
		if(SearchKick) {
			A00010DbConnect.DB_CONN("NANKO");
			ResultSet rset01 = null;
			Statement stmt01 = null;
			try {
				stmt01 = A00010DbConnect.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					      ResultSet.CONCUR_UPDATABLE);
				rset01 = stmt01.executeQuery(sql);
				int counter = 0;
				rset01.beforeFirst();
				while (rset01.next()) {
					counter=counter+1;
				}

				rt = new Object[counter][7];
				counter = 0;
				rset01.beforeFirst();
				while (rset01.next()) {
					rt[counter][0]=rset01.getInt("DeliveryTypeNo");		//タイプ番号
					if(null==rset01.getString("DeliveryTypeCd")){rt[counter][1]="";}else{rt[counter][1]=rset01.getString("DeliveryTypeCd");}			//運送タイプコード
					if(null==rset01.getString("DeliveryTypeName")){rt[counter][2]="";}else{rt[counter][2]=rset01.getString("DeliveryTypeName");}		//運送タイプ名
					if(null==rset01.getTimestamp("EntryDate")){rt[counter][3]="";}else{rt[counter][3]=B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("EntryDate"))[1];}		//データ登録日時
					if(null==rset01.getTimestamp("UpdateDate")){rt[counter][4]="";}else{rt[counter][4]=B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("UpdateDate"))[1];}	//データ更新日時
					if(null==rset01.getString("EntryUser")){rt[counter][5]="";}else{rt[counter][5]=rset01.getString("EntryUser");}			//登録者コード
					if(null==rset01.getString("UpdateUser")){rt[counter][6]="";}else{rt[counter][6]=rset01.getString("UpdateUser");}		//更新者コード
					counter=counter+1;
				}
				if(rset01!=null){rset01.close();}
				if(stmt01!=null){stmt01.close();}
			}catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					if(rset01!=null){rset01.close();}
					if(stmt01!=null){stmt01.close();}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			A00010DbConnect.close();
		}
		return rt;
	}
}