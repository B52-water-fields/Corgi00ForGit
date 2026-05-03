import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class M00050DeliveryTypeMstRt{
	/*
	コピペ用
	ArrayList<String> SearchDeliveryTypeNo 		= ArrayList<String>();
	ArrayList<String> SearchDeliveryTypeCd 		= ArrayList<String>();
	ArrayList<String> SearchDeliveryTypeName	= ArrayList<String>();
	boolean AllSearch = false;
			
	Object[][] DeliveryTypeMstRt = M00050DeliveryTypeMstRt.DeliveryTypeMstRt(
			SearchDeliveryTypeNo,
			SearchDeliveryTypeCd,
			SearchDeliveryTypeName,
			AllSearch);
			
	int GetDeliveryTypeNo		= (int)DeliveryTypeMstRt[i][M00050DeliveryTypeMstRt.ColDeliveryTypeNo];			//タイプ番号
	String GetDeliveryTypeCd	= (String)DeliveryTypeMstRt[i][M00050DeliveryTypeMstRt.ColDeliveryTypeCd];		//運送タイプコード
	String GetDeliveryTypeName	= (String)DeliveryTypeMstRt[i][M00050DeliveryTypeMstRt.ColDeliveryTypeName];	//運送タイプ名
	String GetEntryDate			= (String)DeliveryTypeMstRt[i][M00050DeliveryTypeMstRt.ColEntryDate];			//データ登録日時
	String GetUpdateDate		= (String)DeliveryTypeMstRt[i][M00050DeliveryTypeMstRt.ColUpdateDate];			//データ更新日時
	String GetEntryUser			= (String)DeliveryTypeMstRt[i][M00050DeliveryTypeMstRt.ColEntryUser];			//登録者コード
	String GetUpdateUser		= (String)DeliveryTypeMstRt[i][M00050DeliveryTypeMstRt.ColUpdateUser];			//更新者コード
	*/
	
	
	//戻り値カラム
	static int ColDeliveryTypeNo		= (int) 0;	//タイプ番号
	static int ColDeliveryTypeCd		= (int) 1;	//運送タイプコード
	static int ColDeliveryTypeName	= (int) 2;	//運送タイプ名
	static int ColEntryDate			= (int) 3;	//"データ登録日時
	static int ColUpdateDate			= (int) 4;	//データ更新日時
	static int ColEntryUser			= (int) 5;	//登録者コード
	static int ColUpdateUser			= (int) 6;	//更新者コード
	
	public static Object[][] RtSettingDeliveryTypeMstRt(){
		Object[][] RtSettingDeliveryTypeMstRt = {
				 {"DeliveryTypeNo"		,ColDeliveryTypeNo		,"int"		,"タイプ番号"}
				,{"DeliveryTypeCd"		,ColDeliveryTypeCd		,"String"	,"運送タイプコード"}
				,{"DeliveryTypeName"	,ColDeliveryTypeName		,"String"	,"運送タイプ名"}
				,{"EntryDate"			,ColEntryDate				,"String"	,"データ登録日時"}
				,{"UpdateDate"			,ColUpdateDate			,"String"	,"データ更新日時"}
				,{"EntryUser"			,ColEntryUser				,"String"	,"登録者コード"}
				,{"UpdateUser"			,ColUpdateUser			,"String"	,"更新者コード"}
				};
		
		return RtSettingDeliveryTypeMstRt;
	}
	public static Object[][] DeliveryTypeMstRt(
			ArrayList<String> SearchDeliveryTypeNo,
			ArrayList<String> SearchDeliveryTypeCd,
			ArrayList<String> SearchDeliveryTypeName,
			boolean AllSearch){
		
		SearchDeliveryTypeNo	= B00150ArrayListControl.ArryListStringUniqueList(SearchDeliveryTypeNo);
		SearchDeliveryTypeCd	= B00150ArrayListControl.ArryListStringUniqueList(SearchDeliveryTypeCd);
		SearchDeliveryTypeName	= B00150ArrayListControl.ArryListStringUniqueList(SearchDeliveryTypeName);
		
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
				sql = sql + "KM0050_DELIVERY_TYPEMST.DeliveryTypeNo = ?";
			}
			sql = sql + ")";
		}

		if(null!=SearchDeliveryTypeCd&&0<SearchDeliveryTypeCd.size()) {
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchDeliveryTypeCd.size();i++) {
				if(i>0) {sql = sql + " or ";}
				sql = sql + "KM0050_DELIVERY_TYPEMST.DeliveryTypeCd = ?";
			}
			sql = sql + ")";
		}

		if(null!=SearchDeliveryTypeName&&0<SearchDeliveryTypeName.size()) {
			SearchKick = true;
			sql = sql + " and(";
			for(int i=0;i<SearchDeliveryTypeName.size();i++) {
				if(i>0) {sql = sql + " or ";}
				sql = sql + "KM0050_DELIVERY_TYPEMST.DeliveryTypeName like ?";
			}
			sql = sql + ")";
		}

		sql = sql + " order by KM0050_DELIVERY_TYPEMST.DeliveryTypeNo,KM0050_DELIVERY_TYPEMST.DeliveryTypeCd";

		//System.out.println(sql);
		if(SearchKick) {
			A00010DbConnect.DB_CONN("NYANKO");
			ResultSet rset01 = null;
			PreparedStatement stmt01 = null;
			try {
				stmt01 = A00010DbConnect.conn.prepareStatement(sql);
				int StmtCount = 0;
				
				if(null!=SearchDeliveryTypeNo&&0<SearchDeliveryTypeNo.size()) {
					for(int i=0;i<SearchDeliveryTypeNo.size();i++) {
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchDeliveryTypeNo.get(i)+"");
					}
				}

				if(null!=SearchDeliveryTypeCd&&0<SearchDeliveryTypeCd.size()) {
					for(int i=0;i<SearchDeliveryTypeCd.size();i++) {
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchDeliveryTypeCd.get(i)+"");
					}
				}

				if(null!=SearchDeliveryTypeName&&0<SearchDeliveryTypeName.size()) {
					for(int i=0;i<SearchDeliveryTypeName.size();i++) {
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchDeliveryTypeName.get(i)+"%");
					}
				}
				rset01 = stmt01.executeQuery();
				
				int counter = 0;
				rset01.beforeFirst();
				while (rset01.next()) {
					counter=counter+1;
				}

				rt = new Object[counter][7];
				counter = 0;
				rset01.beforeFirst();
				while (rset01.next()) {
					rt[counter][ColDeliveryTypeNo]=rset01.getInt("DeliveryTypeNo");		//タイプ番号
					if(null==rset01.getString("DeliveryTypeCd")){	rt[counter][ColDeliveryTypeCd]	="";}else{rt[counter][ColDeliveryTypeCd]		=rset01.getString("DeliveryTypeCd");}		//運送タイプコード
					if(null==rset01.getString("DeliveryTypeName")){	rt[counter][ColDeliveryTypeName]	="";}else{rt[counter][ColDeliveryTypeName]	=rset01.getString("DeliveryTypeName");}		//運送タイプ名
					if(null==rset01.getTimestamp("EntryDate")){		rt[counter][ColEntryDate]			="";}else{rt[counter][ColEntryDate]			=B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("EntryDate"))[1];}		//データ登録日時
					if(null==rset01.getTimestamp("UpdateDate")){	rt[counter][ColUpdateDate]		="";}else{rt[counter][ColUpdateDate]			=B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("UpdateDate"))[1];}		//データ更新日時
					if(null==rset01.getString("EntryUser")){		rt[counter][ColEntryUser]			="";}else{rt[counter][ColEntryUser]			=rset01.getString("EntryUser");}			//登録者コード
					if(null==rset01.getString("UpdateUser")){		rt[counter][ColUpdateUser]		="";}else{rt[counter][ColUpdateUser]			=rset01.getString("UpdateUser");}			//更新者コード
					
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