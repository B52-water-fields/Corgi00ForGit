import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
			ArrayList<String> SearchParaCd,	ArrayList<String> SearchParaCdSeq,ArrayList<String> SearchParaName,
			ArrayList<String> SearchParaTxt01,ArrayList<String> SearchParaTxt02,ArrayList<String> SearchParaTxt03,ArrayList<String> SearchParaTxt04,ArrayList<String> SearchParaTxt05,
			ArrayList<String> SearchParaTxt06,ArrayList<String> SearchParaTxt07,ArrayList<String> SearchParaTxt08,ArrayList<String> SearchParaTxt09,ArrayList<String> SearchParaTxt10,
			ArrayList<Integer> SearchParaInt01Str,ArrayList<Integer> SearchParaInt02Str,ArrayList<Integer> SearchParaInt03Str,ArrayList<Integer> SearchParaInt04Str,ArrayList<Integer> SearchParaInt05Str,
			ArrayList<Integer> SearchParaInt06Str,ArrayList<Integer> SearchParaInt07Str,ArrayList<Integer> SearchParaInt08Str,ArrayList<Integer> SearchParaInt09Str,ArrayList<Integer> SearchParaInt10Str,
			ArrayList<Integer> SearchParaInt01End,ArrayList<Integer> SearchParaInt02End,ArrayList<Integer> SearchParaInt03End,ArrayList<Integer> SearchParaInt04End,ArrayList<Integer> SearchParaInt05End,
			ArrayList<Integer> SearchParaInt06End,ArrayList<Integer> SearchParaInt07End,ArrayList<Integer> SearchParaInt08End,ArrayList<Integer> SearchParaInt09End,ArrayList<Integer> SearchParaInt10End,
			Boolean AllSearch){
		//NYANKOパラメータ返却
		Object[][] rt=new Object[0][27];
		boolean SearchKick = false;
		if(AllSearch) {
			SearchKick = true;
		}
		String sql= "Select \n"
			+"(KM0000_PARAMETER.ParaCd) as ParaCd,\n"			//パラメータコード
			+"(KM0000_PARAMETER.ParaCdSeq) as ParaCdSeq,\n"		//ナンバリング
			+"(KM0000_PARAMETER.ParaName) as ParaName,\n"		//パラメータ名
			+"(KM0000_PARAMETER.ParaTxt01) as ParaTxt01,\n"		//パラメータテキスト項目01
			+"(KM0000_PARAMETER.ParaTxt02) as ParaTxt02,\n"		//パラメータテキスト項目02
			+"(KM0000_PARAMETER.ParaTxt03) as ParaTxt03,\n"		//パラメータテキスト項目03
			+"(KM0000_PARAMETER.ParaTxt04) as ParaTxt04,\n"		//パラメータテキスト項目04
			+"(KM0000_PARAMETER.ParaTxt05) as ParaTxt05,\n"		//パラメータテキスト項目05
			+"(KM0000_PARAMETER.ParaTxt06) as ParaTxt06,\n"		//パラメータテキスト項目06
			+"(KM0000_PARAMETER.ParaTxt07) as ParaTxt07,\n"		//パラメータテキスト項目07
			+"(KM0000_PARAMETER.ParaTxt08) as ParaTxt08,\n"		//パラメータテキスト項目08
			+"(KM0000_PARAMETER.ParaTxt09) as ParaTxt09,\n"		//パラメータテキスト項目09
			+"(KM0000_PARAMETER.ParaTxt10) as ParaTxt10,\n"		//パラメータテキスト項目10
			+"(KM0000_PARAMETER.ParaInt01) as ParaInt01,\n"		//パラメータ数値項目01
			+"(KM0000_PARAMETER.ParaInt02) as ParaInt02,\n"		//パラメータ数値項目02
			+"(KM0000_PARAMETER.ParaInt03) as ParaInt03,\n"		//パラメータ数値項目03
			+"(KM0000_PARAMETER.ParaInt04) as ParaInt04,\n"		//パラメータ数値項目04
			+"(KM0000_PARAMETER.ParaInt05) as ParaInt05,\n"		//パラメータ数値項目05
			+"(KM0000_PARAMETER.ParaInt06) as ParaInt06,\n"		//パラメータ数値項目06
			+"(KM0000_PARAMETER.ParaInt07) as ParaInt07,\n"		//パラメータ数値項目07
			+"(KM0000_PARAMETER.ParaInt08) as ParaInt08,\n"		//パラメータ数値項目08
			+"(KM0000_PARAMETER.ParaInt09) as ParaInt09,\n"		//パラメータ数値項目09
			+"(KM0000_PARAMETER.ParaInt10) as ParaInt10,\n"		//パラメータ数値項目10
			+"(KM0000_PARAMETER.EntryDate) as EntryDate,\n"
			+"(KM0000_PARAMETER.UpdateDate) as UpdateDate,\n"
			+"(KM0000_PARAMETER.EntryUser) as EntryUser,\n"
			+"(KM0000_PARAMETER.UpdateUser) as UpdateUser\n"
			+ " from "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0000_PARAMETER \n"
			+ " where 1=1\n";

		if(null!=SearchParaCd && 0<SearchParaCd.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaCd.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "KM0000_PARAMETER.ParaCd =?";
			}
			sql = sql + ")";
		}

		if(null!=SearchParaCdSeq && 0<SearchParaCdSeq.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaCdSeq.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "KM0000_PARAMETER.ParaCdSeq =?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaName && 0<SearchParaName.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaName.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "KM0000_PARAMETER.ParaName like ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaTxt01 && 0<SearchParaTxt01.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaTxt01.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "KM0000_PARAMETER.ParaTxt01 like ?";
			}
			sql = sql + ")";
		}

		if(null!=SearchParaTxt02 && 0<SearchParaTxt02.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaTxt02.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "KM0000_PARAMETER.ParaTxt02 like ?";
			}
			sql = sql + ")";
		}

		if(null!=SearchParaTxt03 && 0<SearchParaTxt03.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaTxt03.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "KM0000_PARAMETER.ParaTxt03 like ?";
			}
			sql = sql + ")";
		}

		if(null!=SearchParaTxt04 && 0<SearchParaTxt04.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaTxt04.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "KM0000_PARAMETER.ParaTxt04 like ?";
			}
			sql = sql + ")";
		}

		if(null!=SearchParaTxt05 && 0<SearchParaTxt05.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaTxt05.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "KM0000_PARAMETER.ParaTxt05 like ?";
			}
			sql = sql + ")";
		}

		if(null!=SearchParaTxt06 && 0<SearchParaTxt06.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaTxt06.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "KM0000_PARAMETER.ParaTxt06 like ?";
			}
			sql = sql + ")";
		}

		if(null!=SearchParaTxt07 && 0<SearchParaTxt07.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaTxt07.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "KM0000_PARAMETER.ParaTxt07 like ?";
			}
			sql = sql + ")";
		}

		if(null!=SearchParaTxt08 && 0<SearchParaTxt08.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaTxt08.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "KM0000_PARAMETER.ParaTxt08 like ?";
			}
			sql = sql + ")";
		}

		if(null!=SearchParaTxt09 && 0<SearchParaTxt09.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaTxt09.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "KM0000_PARAMETER.ParaTxt09 like ?";
			}
			sql = sql + ")";
		}

		if(null!=SearchParaTxt10 && 0<SearchParaTxt10.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaTxt10.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "KM0000_PARAMETER.ParaTxt10 like ?";
			}
			sql = sql + ")";
		}

		if(null!=SearchParaInt01Str && 0<SearchParaInt01Str.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt01Str.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "KM0000_PARAMETER.ParaInt01 >= ?";
			}
			sql = sql + ")";
		}

		if(null!=SearchParaInt02Str && 0<SearchParaInt02Str.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt02Str.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "KM0000_PARAMETER.ParaInt02 >= ?";
			}
			sql = sql + ")";
		}

		if(null!=SearchParaInt03Str && 0<SearchParaInt03Str.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt03Str.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "KM0000_PARAMETER.ParaInt03 >= ?";
			}
			sql = sql + ")";
		}

		if(null!=SearchParaInt04Str && 0<SearchParaInt04Str.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt04Str.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "KM0000_PARAMETER.ParaInt04 >= ?";
			}
			sql = sql + ")";
		}

		if(null!=SearchParaInt05Str && 0<SearchParaInt05Str.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt05Str.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "KM0000_PARAMETER.ParaInt05 >= ?";
			}
			sql = sql + ")";
		}

		if(null!=SearchParaInt06Str && 0<SearchParaInt06Str.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt06Str.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "KM0000_PARAMETER.ParaInt06 >= ?";
			}
			sql = sql + ")";
		}

		if(null!=SearchParaInt07Str && 0<SearchParaInt07Str.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt07Str.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "KM0000_PARAMETER.ParaInt07 >= ?";
			}
			sql = sql + ")";
		}

		if(null!=SearchParaInt08Str && 0<SearchParaInt08Str.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt08Str.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "KM0000_PARAMETER.ParaInt08 >= ?";
			}
			sql = sql + ")";
		}

		if(null!=SearchParaInt09Str && 0<SearchParaInt09Str.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt09Str.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "KM0000_PARAMETER.ParaInt09 >= ?";
			}
			sql = sql + ")";
		}

		if(null!=SearchParaInt10Str && 0<SearchParaInt10Str.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt10Str.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "KM0000_PARAMETER.ParaInt10 >= ?";
			}
			sql = sql + ")";
		}

		if(null!=SearchParaInt01End && 0<SearchParaInt01End.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt01End.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "KM0000_PARAMETER.ParaInt01 <= ?";
			}
			sql = sql + ")";
		}

		if(null!=SearchParaInt02End && 0<SearchParaInt02End.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt02End.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "KM0000_PARAMETER.ParaInt02 <= ?";
			}
			sql = sql + ")";
		}

		if(null!=SearchParaInt03End && 0<SearchParaInt03End.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt03End.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "KM0000_PARAMETER.ParaInt03 <= ?";
			}
			sql = sql + ")";
		}

		if(null!=SearchParaInt04End && 0<SearchParaInt04End.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt04End.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "KM0000_PARAMETER.ParaInt04 <= ?";
			}
			sql = sql + ")";
		}

		if(null!=SearchParaInt05End && 0<SearchParaInt05End.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt05End.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "KM0000_PARAMETER.ParaInt05 <= ?";
			}
			sql = sql + ")";
		}

		if(null!=SearchParaInt06End && 0<SearchParaInt06End.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt06End.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "KM0000_PARAMETER.ParaInt06 <= ?";
			}
			sql = sql + ")";
		}

		if(null!=SearchParaInt07End && 0<SearchParaInt07End.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt07End.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "KM0000_PARAMETER.ParaInt07 <= ?";
			}
			sql = sql + ")";
		}

		if(null!=SearchParaInt08End && 0<SearchParaInt08End.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt08End.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "KM0000_PARAMETER.ParaInt08 <= ?";
			}
			sql = sql + ")";
		}

		if(null!=SearchParaInt09End && 0<SearchParaInt09End.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt09End.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "KM0000_PARAMETER.ParaInt09 <= ?";
			}
			sql = sql + ")";
		}

		if(null!=SearchParaInt10End && 0<SearchParaInt10End.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt10End.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "KM0000_PARAMETER.ParaInt10 <= ?";
			}
			sql = sql + ")";
		}
		sql =sql + " order by KM0000_PARAMETER.ParaCd,KM0000_PARAMETER.ParaCdSeq";
		//System.out.println(sql);
		if(true==SearchKick) {
			A00010DbConnect.DB_CONN("NANKO");
			ResultSet rset01 = null;
			PreparedStatement stmt01 = null;
			try {
				stmt01 = A00010DbConnect.conn.prepareStatement(sql);
				int StmtCount = 0;
				if(null!=SearchParaCd && 0<SearchParaCd.size()){
					for(int i=0;i<SearchParaCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchParaCd.get(i));
					}
				}

				if(null!=SearchParaCdSeq && 0<SearchParaCdSeq.size()){
					for(int i=0;i<SearchParaCdSeq.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchParaCdSeq.get(i));
					}
				}
				if(null!=SearchParaName && 0<SearchParaName.size()){
					for(int i=0;i<SearchParaName.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchParaName.get(i)+"%");
					}
				}
				if(null!=SearchParaTxt01 && 0<SearchParaTxt01.size()){
					for(int i=0;i<SearchParaTxt01.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchParaTxt01.get(i)+"%");
					}
				}

				if(null!=SearchParaTxt02 && 0<SearchParaTxt02.size()){
					for(int i=0;i<SearchParaTxt02.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchParaTxt02.get(i)+"%");
					}
				}

				if(null!=SearchParaTxt03 && 0<SearchParaTxt03.size()){
					for(int i=0;i<SearchParaTxt03.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchParaTxt03.get(i)+"%");
					}
				}

				if(null!=SearchParaTxt04 && 0<SearchParaTxt04.size()){
					for(int i=0;i<SearchParaTxt04.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchParaTxt04.get(i)+"%");
					}
				}

				if(null!=SearchParaTxt05 && 0<SearchParaTxt05.size()){
					for(int i=0;i<SearchParaTxt05.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchParaTxt05.get(i)+"%");
					}
				}

				if(null!=SearchParaTxt06 && 0<SearchParaTxt06.size()){
					for(int i=0;i<SearchParaTxt06.size();i++){
						if(i>0){sql = sql + " or ";}
						sql = sql + "KM0000_PARAMETER.ParaTxt06 like '%"+SearchParaTxt06.get(i)+"%'";
					}
				}

				if(null!=SearchParaTxt07 && 0<SearchParaTxt07.size()){
					for(int i=0;i<SearchParaTxt07.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchParaTxt07.get(i)+"%");
					}
				}

				if(null!=SearchParaTxt08 && 0<SearchParaTxt08.size()){
					for(int i=0;i<SearchParaTxt08.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchParaTxt08.get(i)+"%");
					}
				}

				if(null!=SearchParaTxt09 && 0<SearchParaTxt09.size()){
					for(int i=0;i<SearchParaTxt09.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchParaTxt09.get(i)+"%");
					}
				}

				if(null!=SearchParaTxt10 && 0<SearchParaTxt10.size()){
					for(int i=0;i<SearchParaTxt10.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchParaTxt10.get(i)+"%");
					}
				}

				if(null!=SearchParaInt01Str && 0<SearchParaInt01Str.size()){
					for(int i=0;i<SearchParaInt01Str.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setInt(StmtCount, SearchParaInt01Str.get(i));
					}
				}

				if(null!=SearchParaInt02Str && 0<SearchParaInt02Str.size()){
					for(int i=0;i<SearchParaInt02Str.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setInt(StmtCount, SearchParaInt02Str.get(i));
					}
				}

				if(null!=SearchParaInt03Str && 0<SearchParaInt03Str.size()){
					for(int i=0;i<SearchParaInt03Str.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setInt(StmtCount, SearchParaInt03Str.get(i));
					}
				}

				if(null!=SearchParaInt04Str && 0<SearchParaInt04Str.size()){
					for(int i=0;i<SearchParaInt04Str.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setInt(StmtCount, SearchParaInt04Str.get(i));
					}
				}

				if(null!=SearchParaInt05Str && 0<SearchParaInt05Str.size()){
					for(int i=0;i<SearchParaInt05Str.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setInt(StmtCount, SearchParaInt05Str.get(i));
					}
				}

				if(null!=SearchParaInt06Str && 0<SearchParaInt06Str.size()){
					for(int i=0;i<SearchParaInt06Str.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setInt(StmtCount, SearchParaInt06Str.get(i));
					}
				}

				if(null!=SearchParaInt07Str && 0<SearchParaInt07Str.size()){
					for(int i=0;i<SearchParaInt07Str.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setInt(StmtCount, SearchParaInt07Str.get(i));
					}
				}

				if(null!=SearchParaInt08Str && 0<SearchParaInt08Str.size()){
					for(int i=0;i<SearchParaInt08Str.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setInt(StmtCount, SearchParaInt08Str.get(i));
					}
				}

				if(null!=SearchParaInt09Str && 0<SearchParaInt09Str.size()){
					for(int i=0;i<SearchParaInt09Str.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setInt(StmtCount, SearchParaInt09Str.get(i));
					}
				}

				if(null!=SearchParaInt10Str && 0<SearchParaInt10Str.size()){
					for(int i=0;i<SearchParaInt10Str.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setInt(StmtCount, SearchParaInt10Str.get(i));
					}
				}

				if(null!=SearchParaInt01End && 0<SearchParaInt01End.size()){
					for(int i=0;i<SearchParaInt01End.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setInt(StmtCount, SearchParaInt01End.get(i));
					}
				}

				if(null!=SearchParaInt02End && 0<SearchParaInt02End.size()){
					for(int i=0;i<SearchParaInt02End.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setInt(StmtCount, SearchParaInt02End.get(i));
					}
				}

				if(null!=SearchParaInt03End && 0<SearchParaInt03End.size()){
					for(int i=0;i<SearchParaInt03End.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setInt(StmtCount, SearchParaInt03End.get(i));
					}
				}

				if(null!=SearchParaInt04End && 0<SearchParaInt04End.size()){
					for(int i=0;i<SearchParaInt04End.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setInt(StmtCount, SearchParaInt04End.get(i));
					}
				}

				if(null!=SearchParaInt05End && 0<SearchParaInt05End.size()){
					for(int i=0;i<SearchParaInt05End.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setInt(StmtCount, SearchParaInt05End.get(i));
					}
				}

				if(null!=SearchParaInt06End && 0<SearchParaInt06End.size()){
					for(int i=0;i<SearchParaInt06End.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setInt(StmtCount, SearchParaInt06End.get(i));
					}
				}

				if(null!=SearchParaInt07End && 0<SearchParaInt07End.size()){
					for(int i=0;i<SearchParaInt07End.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setInt(StmtCount, SearchParaInt07End.get(i));
					}
				}

				if(null!=SearchParaInt08End && 0<SearchParaInt08End.size()){
					for(int i=0;i<SearchParaInt08End.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setInt(StmtCount, SearchParaInt08End.get(i));
					}
				}

				if(null!=SearchParaInt09End && 0<SearchParaInt09End.size()){
					for(int i=0;i<SearchParaInt09End.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setInt(StmtCount, SearchParaInt09End.get(i));
					}
				}

				if(null!=SearchParaInt10End && 0<SearchParaInt10End.size()){
					for(int i=0;i<SearchParaInt10End.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setInt(StmtCount, SearchParaInt10End.get(i));
					}
				}
				rset01 = stmt01.executeQuery();
				
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