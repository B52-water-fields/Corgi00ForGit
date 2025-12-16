import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class M00000ParameterMstRt{
	//戻り値カラム
	public static Object[][] RtSettingParameterMstRtNANKO(){
		Object[][] RtSettingParameterMstRtNANKO = {
				 {"ParaCd"		,(int) 0	,"String"	,"パラメータコード"}
				,{"ParaCdSeq"	,(int) 1	,"String"	,"ナンバリング"}
				,{"ParaName"	,(int) 2	,"String"	,"パラメータ名"}
				,{"ParaTxt01"	,(int) 3	,"String"	,"パラメータテキスト項目01"}
				,{"ParaTxt02"	,(int) 4	,"String"	,"パラメータテキスト項目02"}
				,{"ParaTxt03"	,(int) 5	,"String"	,"パラメータテキスト項目03"}
				,{"ParaTxt04"	,(int) 6	,"String"	,"パラメータテキスト項目04"}
				,{"ParaTxt05"	,(int) 7	,"String"	,"パラメータテキスト項目05"}
				,{"ParaTxt06"	,(int) 8	,"String"	,"パラメータテキスト項目06"}
				,{"ParaTxt07"	,(int) 9	,"String"	,"パラメータテキスト項目07"}
				,{"ParaTxt08"	,(int)10	,"String"	,"パラメータテキスト項目08"}
				,{"ParaTxt09"	,(int)11	,"String"	,"パラメータテキスト項目09"}
				,{"ParaTxt10"	,(int)12	,"String"	,"パラメータテキスト項目10"}
				,{"ParaInt01"	,(int)13	,"int"		,"パラメータ数値項目01"}
				,{"ParaInt02"	,(int)14	,"int"		,"パラメータ数値項目02"}
				,{"ParaInt03"	,(int)15	,"int"		,"パラメータ数値項目03"}
				,{"ParaInt04"	,(int)16	,"int"		,"パラメータ数値項目04"}
				,{"ParaInt05"	,(int)17	,"int"		,"パラメータ数値項目05"}
				,{"ParaInt06"	,(int)18	,"int"		,"パラメータ数値項目06"}
				,{"ParaInt07"	,(int)19	,"int"		,"パラメータ数値項目07"}
				,{"ParaInt08"	,(int)20	,"int"		,"パラメータ数値項目08"}
				,{"ParaInt09"	,(int)21	,"int"		,"パラメータ数値項目09"}
				,{"ParaInt10"	,(int)22	,"int"		,"パラメータ数値項目10"}
				,{"EntryDate"	,(int)23	,"String"	,"登録日"}
				,{"UpdateDate"	,(int)24	,"String"	,"更新日"}
				,{"EntryUser"	,(int)25	,"String"	,"登録者"}
				,{"UpdateUser"	,(int)26	,"String"	,"更新者"}
				};
		
		return RtSettingParameterMstRtNANKO;
	}
	
	public static Object[][] ParameterMstRtNANKO(
			ArrayList SearchParaCd,	ArrayList SearchParaCdSeq,ArrayList SearchParaName,
			ArrayList SearchParaTxt01,ArrayList SearchParaTxt02,ArrayList SearchParaTxt03,ArrayList SearchParaTxt04,ArrayList SearchParaTxt05,
			ArrayList SearchParaTxt06,ArrayList SearchParaTxt07,ArrayList SearchParaTxt08,ArrayList SearchParaTxt09,ArrayList SearchParaTxt10,
			ArrayList SearchParaInt01Str,ArrayList SearchParaInt02Str,ArrayList SearchParaInt03Str,ArrayList SearchParaInt04Str,ArrayList SearchParaInt05Str,
			ArrayList SearchParaInt06Str,ArrayList SearchParaInt07Str,ArrayList SearchParaInt08Str,ArrayList SearchParaInt09Str,ArrayList SearchParaInt10Str,
			ArrayList SearchParaInt01End,ArrayList SearchParaInt02End,ArrayList SearchParaInt03End,ArrayList SearchParaInt04End,ArrayList SearchParaInt05End,
			ArrayList SearchParaInt06End,ArrayList SearchParaInt07End,ArrayList SearchParaInt08End,ArrayList SearchParaInt09End,ArrayList SearchParaInt10End,
			Boolean AllSearch){
		//NYANKOパラメータ返却
		Object[][] rt=new Object[0][27];
		boolean SearchKick = false;
		if(AllSearch) {
			SearchKick = true;
		}
		String sql= "Select "
			+"(KM0000_PARAMETER.ParaCd) as ParaCd,"			//パラメータコード
			+"(KM0000_PARAMETER.ParaCdSeq) as ParaCdSeq,"	//ナンバリング
			+"(KM0000_PARAMETER.ParaName) as ParaName,"		//パラメータ名
			+"(KM0000_PARAMETER.ParaTxt01) as ParaTxt01,"	//パラメータテキスト項目01
			+"(KM0000_PARAMETER.ParaTxt02) as ParaTxt02,"	//パラメータテキスト項目02
			+"(KM0000_PARAMETER.ParaTxt03) as ParaTxt03,"	//パラメータテキスト項目03
			+"(KM0000_PARAMETER.ParaTxt04) as ParaTxt04,"	//パラメータテキスト項目04
			+"(KM0000_PARAMETER.ParaTxt05) as ParaTxt05,"	//パラメータテキスト項目05
			+"(KM0000_PARAMETER.ParaTxt06) as ParaTxt06,"	//パラメータテキスト項目06
			+"(KM0000_PARAMETER.ParaTxt07) as ParaTxt07,"	//パラメータテキスト項目07
			+"(KM0000_PARAMETER.ParaTxt08) as ParaTxt08,"	//パラメータテキスト項目08
			+"(KM0000_PARAMETER.ParaTxt09) as ParaTxt09,"	//パラメータテキスト項目09
			+"(KM0000_PARAMETER.ParaTxt10) as ParaTxt10,"	//パラメータテキスト項目10
			+"(KM0000_PARAMETER.ParaInt01) as ParaInt01,"	//パラメータ数値項目01
			+"(KM0000_PARAMETER.ParaInt02) as ParaInt02,"	//パラメータ数値項目02
			+"(KM0000_PARAMETER.ParaInt03) as ParaInt03,"	//パラメータ数値項目03
			+"(KM0000_PARAMETER.ParaInt04) as ParaInt04,"	//パラメータ数値項目04
			+"(KM0000_PARAMETER.ParaInt05) as ParaInt05,"	//パラメータ数値項目05
			+"(KM0000_PARAMETER.ParaInt06) as ParaInt06,"	//パラメータ数値項目06
			+"(KM0000_PARAMETER.ParaInt07) as ParaInt07,"	//パラメータ数値項目07
			+"(KM0000_PARAMETER.ParaInt08) as ParaInt08,"	//パラメータ数値項目08
			+"(KM0000_PARAMETER.ParaInt09) as ParaInt09,"	//パラメータ数値項目09
			+"(KM0000_PARAMETER.ParaInt10) as ParaInt10,"	//パラメータ数値項目10
			+"(KM0000_PARAMETER.EntryDate) as EntryDate,"
			+"(KM0000_PARAMETER.UpdateDate) as UpdateDate,"
			+"(KM0000_PARAMETER.EntryUser) as EntryUser,"
			+"(KM0000_PARAMETER.UpdateUser) as UpdateUser"
			+ " from KM0000_PARAMETER "
			+ " where 1=1";
		if(null!=SearchParaCd && 0<SearchParaCd.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaCd.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "KM0000_PARAMETER.ParaCd ='"+SearchParaCd.get(i)+"'";
			}
			sql = sql + ")";
		}

		if(null!=SearchParaCdSeq && 0<SearchParaCdSeq.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaCdSeq.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "KM0000_PARAMETER.ParaCdSeq ='"+SearchParaCdSeq.get(i)+"'";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaName && 0<SearchParaName.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaName.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "KM0000_PARAMETER.ParaName like '%"+SearchParaName.get(i)+"%'";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaTxt01 && 0<SearchParaTxt01.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaTxt01.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "KM0000_PARAMETER.ParaTxt01 like '%"+SearchParaTxt01.get(i)+"%'";
			}
			sql = sql + ")";
		}

		if(null!=SearchParaTxt02 && 0<SearchParaTxt02.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaTxt02.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "KM0000_PARAMETER.ParaTxt02 like '%"+SearchParaTxt02.get(i)+"%'";
			}
			sql = sql + ")";
		}

		if(null!=SearchParaTxt03 && 0<SearchParaTxt03.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaTxt03.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "KM0000_PARAMETER.ParaTxt03 like '%"+SearchParaTxt03.get(i)+"%'";
			}
			sql = sql + ")";
		}

		if(null!=SearchParaTxt04 && 0<SearchParaTxt04.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaTxt04.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "KM0000_PARAMETER.ParaTxt04 like '%"+SearchParaTxt04.get(i)+"%'";
			}
			sql = sql + ")";
		}

		if(null!=SearchParaTxt05 && 0<SearchParaTxt05.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaTxt05.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "KM0000_PARAMETER.ParaTxt05 like '%"+SearchParaTxt05.get(i)+"%'";
			}
			sql = sql + ")";
		}

		if(null!=SearchParaTxt06 && 0<SearchParaTxt06.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaTxt06.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "KM0000_PARAMETER.ParaTxt06 like '%"+SearchParaTxt06.get(i)+"%'";
			}
			sql = sql + ")";
		}

		if(null!=SearchParaTxt07 && 0<SearchParaTxt07.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaTxt07.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "KM0000_PARAMETER.ParaTxt07 like '%"+SearchParaTxt07.get(i)+"%'";
			}
			sql = sql + ")";
		}

		if(null!=SearchParaTxt08 && 0<SearchParaTxt08.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaTxt08.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "KM0000_PARAMETER.ParaTxt08 like '%"+SearchParaTxt08.get(i)+"%'";
			}
			sql = sql + ")";
		}

		if(null!=SearchParaTxt09 && 0<SearchParaTxt09.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaTxt09.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "KM0000_PARAMETER.ParaTxt09 like '%"+SearchParaTxt09.get(i)+"%'";
			}
			sql = sql + ")";
		}

		if(null!=SearchParaTxt10 && 0<SearchParaTxt10.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaTxt10.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "KM0000_PARAMETER.ParaTxt10 like '%"+SearchParaTxt10.get(i)+"%'";
			}
			sql = sql + ")";
		}

		if(null!=SearchParaInt01Str && 0<SearchParaInt01Str.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt01Str.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "KM0000_PARAMETER.ParaInt01 >= "+SearchParaInt01Str.get(i)+"";
			}
			sql = sql + ")";
		}

		if(null!=SearchParaInt02Str && 0<SearchParaInt02Str.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt02Str.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "KM0000_PARAMETER.ParaInt02 >= "+SearchParaInt02Str.get(i)+"";
			}
			sql = sql + ")";
		}

		if(null!=SearchParaInt03Str && 0<SearchParaInt03Str.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt03Str.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "KM0000_PARAMETER.ParaInt03 >= "+SearchParaInt03Str.get(i)+"";
			}
			sql = sql + ")";
		}

		if(null!=SearchParaInt04Str && 0<SearchParaInt04Str.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt04Str.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "KM0000_PARAMETER.ParaInt04 >= "+SearchParaInt04Str.get(i)+"";
			}
			sql = sql + ")";
		}

		if(null!=SearchParaInt05Str && 0<SearchParaInt05Str.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt05Str.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "KM0000_PARAMETER.ParaInt05 >= "+SearchParaInt05Str.get(i)+"";
			}
			sql = sql + ")";
		}

		if(null!=SearchParaInt06Str && 0<SearchParaInt06Str.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt06Str.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "KM0000_PARAMETER.ParaInt06 >= "+SearchParaInt06Str.get(i)+"";
			}
			sql = sql + ")";
		}

		if(null!=SearchParaInt07Str && 0<SearchParaInt07Str.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt07Str.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "KM0000_PARAMETER.ParaInt07 >= "+SearchParaInt07Str.get(i)+"";
			}
			sql = sql + ")";
		}

		if(null!=SearchParaInt08Str && 0<SearchParaInt08Str.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt08Str.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "KM0000_PARAMETER.ParaInt08 >= "+SearchParaInt08Str.get(i)+"";
			}
			sql = sql + ")";
		}

		if(null!=SearchParaInt09Str && 0<SearchParaInt09Str.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt09Str.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "KM0000_PARAMETER.ParaInt09 >= "+SearchParaInt09Str.get(i)+"";
			}
			sql = sql + ")";
		}

		if(null!=SearchParaInt10Str && 0<SearchParaInt10Str.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt10Str.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "KM0000_PARAMETER.ParaInt10 >= "+SearchParaInt10Str.get(i)+"";
			}
			sql = sql + ")";
		}

		if(null!=SearchParaInt01End && 0<SearchParaInt01End.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt01End.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "KM0000_PARAMETER.ParaInt01 <= "+SearchParaInt01End.get(i)+"";
			}
			sql = sql + ")";
		}

		if(null!=SearchParaInt02End && 0<SearchParaInt02End.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt02End.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "KM0000_PARAMETER.ParaInt02 <= "+SearchParaInt02End.get(i)+"";
			}
			sql = sql + ")";
		}

		if(null!=SearchParaInt03End && 0<SearchParaInt03End.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt03End.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "KM0000_PARAMETER.ParaInt03 <= "+SearchParaInt03End.get(i)+"";
			}
			sql = sql + ")";
		}

		if(null!=SearchParaInt04End && 0<SearchParaInt04End.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt04End.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "KM0000_PARAMETER.ParaInt04 <= "+SearchParaInt04End.get(i)+"";
			}
			sql = sql + ")";
		}

		if(null!=SearchParaInt05End && 0<SearchParaInt05End.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt05End.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "KM0000_PARAMETER.ParaInt05 <= "+SearchParaInt05End.get(i)+"";
			}
			sql = sql + ")";
		}

		if(null!=SearchParaInt06End && 0<SearchParaInt06End.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt06End.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "KM0000_PARAMETER.ParaInt06 <= "+SearchParaInt06End.get(i)+"";
			}
			sql = sql + ")";
		}

		if(null!=SearchParaInt07End && 0<SearchParaInt07End.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt07End.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "KM0000_PARAMETER.ParaInt07 <= "+SearchParaInt07End.get(i)+"";
			}
			sql = sql + ")";
		}

		if(null!=SearchParaInt08End && 0<SearchParaInt08End.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt08End.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "KM0000_PARAMETER.ParaInt08 <= "+SearchParaInt08End.get(i)+"";
			}
			sql = sql + ")";
		}

		if(null!=SearchParaInt09End && 0<SearchParaInt09End.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt09End.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "KM0000_PARAMETER.ParaInt09 <= "+SearchParaInt09End.get(i)+"";
			}
			sql = sql + ")";
		}

		if(null!=SearchParaInt10End && 0<SearchParaInt10End.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt10End.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "KM0000_PARAMETER.ParaInt10 <= "+SearchParaInt10End.get(i)+"";
			}
			sql = sql + ")";
		}
		sql =sql + " order by KM0000_PARAMETER.ParaCd,KM0000_PARAMETER.ParaCdSeq";
		//System.out.println(sql);
		if(true==SearchKick) {
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

				rt = new Object[counter][27];
				counter = 0;
				rset01.beforeFirst();
				while (rset01.next()) {
					if(null==rset01.getString("ParaCd")){rt[counter][0]="";}else{rt[counter][0]=rset01.getString("ParaCd");}			//パラメータコード
					if(null==rset01.getString("ParaCdSeq")){rt[counter][1]="";}else{rt[counter][1]=rset01.getString("ParaCdSeq");}		//ナンバリング
					if(null==rset01.getString("ParaName")){rt[counter][2]="";}else{rt[counter][2]=rset01.getString("ParaName");}		//パラメータ名
					if(null==rset01.getString("ParaTxt01")){rt[counter][3]="";}else{rt[counter][3]=rset01.getString("ParaTxt01");}		//パラメータテキスト項目01
					if(null==rset01.getString("ParaTxt02")){rt[counter][4]="";}else{rt[counter][4]=rset01.getString("ParaTxt02");}		//パラメータテキスト項目02
					if(null==rset01.getString("ParaTxt03")){rt[counter][5]="";}else{rt[counter][5]=rset01.getString("ParaTxt03");}		//パラメータテキスト項目03
					if(null==rset01.getString("ParaTxt04")){rt[counter][6]="";}else{rt[counter][6]=rset01.getString("ParaTxt04");}		//パラメータテキスト項目04
					if(null==rset01.getString("ParaTxt05")){rt[counter][7]="";}else{rt[counter][7]=rset01.getString("ParaTxt05");}		//パラメータテキスト項目05
					if(null==rset01.getString("ParaTxt06")){rt[counter][8]="";}else{rt[counter][8]=rset01.getString("ParaTxt06");}		//パラメータテキスト項目06
					if(null==rset01.getString("ParaTxt07")){rt[counter][9]="";}else{rt[counter][9]=rset01.getString("ParaTxt07");}		//パラメータテキスト項目07
					if(null==rset01.getString("ParaTxt08")){rt[counter][10]="";}else{rt[counter][10]=rset01.getString("ParaTxt08");}	//パラメータテキスト項目08
					if(null==rset01.getString("ParaTxt09")){rt[counter][11]="";}else{rt[counter][11]=rset01.getString("ParaTxt09");}	//パラメータテキスト項目09
					if(null==rset01.getString("ParaTxt10")){rt[counter][12]="";}else{rt[counter][12]=rset01.getString("ParaTxt10");}	//パラメータテキスト項目10
					rt[counter][13]=rset01.getInt("ParaInt01");	//パラメータ数値項目01
					rt[counter][14]=rset01.getInt("ParaInt02");	//パラメータ数値項目02
					rt[counter][15]=rset01.getInt("ParaInt03");	//パラメータ数値項目03
					rt[counter][16]=rset01.getInt("ParaInt04");	//パラメータ数値項目04
					rt[counter][17]=rset01.getInt("ParaInt05");	//パラメータ数値項目05
					rt[counter][18]=rset01.getInt("ParaInt06");	//パラメータ数値項目06
					rt[counter][19]=rset01.getInt("ParaInt07");	//パラメータ数値項目07
					rt[counter][20]=rset01.getInt("ParaInt08");	//パラメータ数値項目08
					rt[counter][21]=rset01.getInt("ParaInt09");	//パラメータ数値項目09
					rt[counter][22]=rset01.getInt("ParaInt10");	//パラメータ数値項目10
					if(null==rset01.getTimestamp("EntryDate")){rt[counter][23]="";}else{rt[counter][23]=B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("EntryDate"))[1];}		//登録日
					if(null==rset01.getTimestamp("UpdateDate")){rt[counter][24]="";}else{rt[counter][24]=B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("UpdateDate"))[1];}		//更新日
					if(null==rset01.getString("EntryUser")){rt[counter][25]="";}else{rt[counter][25]=rset01.getString("EntryUser");}		//登録者
					if(null==rset01.getString("UpdateUser")){rt[counter][26]="";}else{rt[counter][26]=rset01.getString("UpdateUser");}		//更新者
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