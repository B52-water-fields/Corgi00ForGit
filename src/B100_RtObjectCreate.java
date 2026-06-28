import java.sql.ResultSet;
import java.sql.SQLException;

public class B100_RtObjectCreate{
	public static Object[][] B100_RtObjectCreate(
			ResultSet rset01,
			Object[][] RtXXXRt
			){
		Object[][] rt = new Object[0][0];
		if(null!=rset01 && null!=RtXXXRt) {
			rt = new Object[0][RtXXXRt.length];
			try {
			rset01.beforeFirst();
				int counter = 0;
				while (rset01.next()) {
					counter=counter+1;
				}
				rt = new Object[counter][RtXXXRt.length];
				counter = 0;
				rset01.beforeFirst();
				while (rset01.next()) {
					for(int i01=0;i01<RtXXXRt.length;i01++) {
						switch((String)RtXXXRt[i01][2]) {
							case "String":
								if(null==rset01.getString((String)RtXXXRt[i01][0])) {rt[counter][(int)RtXXXRt[i01][1]]="";}else {rt[counter][(int)RtXXXRt[i01][1]]=rset01.getString((String)RtXXXRt[i01][0]);}
								break;
							case "int":
								rt[counter][(int)RtXXXRt[i01][1]]=rset01.getInt((String)RtXXXRt[i01][0]);
								break;
							case "float":
								rt[counter][(int)RtXXXRt[i01][1]]=rset01.getFloat((String)RtXXXRt[i01][0]);
								break;
							case "Date":
								if(null==rset01.getTimestamp((String)RtXXXRt[i01][0])){rt[counter][(int)RtXXXRt[i01][1]]="";}else{rt[counter][(int)RtXXXRt[i01][1]]=B100_DateTimeControl.dtmString2(rset01.getTimestamp((String)RtXXXRt[i01][0]))[0];}
								break;
							case "DateTime":
								if(null==rset01.getTimestamp((String)RtXXXRt[i01][0])){rt[counter][(int)RtXXXRt[i01][1]]="";}else{rt[counter][(int)RtXXXRt[i01][1]]=B100_DateTimeControl.dtmString2(rset01.getTimestamp((String)RtXXXRt[i01][0]))[1];}
								break;
							default:
								if(null==rset01.getString((String)RtXXXRt[i01][0])) {rt[counter][(int)RtXXXRt[i01][1]]="";}else {rt[counter][(int)RtXXXRt[i01][1]]=rset01.getString((String)RtXXXRt[i01][0]);}
								break;
						}
					}
					counter=counter+1;
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rt;
	}
}