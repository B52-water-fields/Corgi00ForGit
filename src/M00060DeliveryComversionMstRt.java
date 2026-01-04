import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class M00060DeliveryComversionMstRt{
	/*
	コピペ用
	ArrayList<String> SearchClGpCD 				= new ArrayList<String>();
	ArrayList<String> SearchCLGpName 			= new ArrayList<String>();
	ArrayList<String> SearchCL_DECD 			= new ArrayList<String>();
	ArrayList<String> SearchDECD 				= new ArrayList<String>();
	ArrayList<String> SearchDepartmentCd 		= new ArrayList<String>();
	ArrayList<String> SearchDEName 				= new ArrayList<String>();
	ArrayList<String> SearchPost 				= new ArrayList<String>();
	ArrayList<String> SearchAdd 				= new ArrayList<String>();
	ArrayList<String> SearchTel 				= new ArrayList<String>();
	ArrayList<String> SearchFax 				= new ArrayList<String>();
	ArrayList<String> SearchMail 				= new ArrayList<String>();
	ArrayList<String> SearchSetName 			= new ArrayList<String>();
	ArrayList<String> SearchCom 				= new ArrayList<String>();
	ArrayList<String> SearchDelFg 				= new ArrayList<String>();
	ArrayList<String> SearchMstPriorityFirstFg	= new ArrayList<String>();
	boolean AllSearch = false;
	
	Object[][] DeliveryComversionMstRt = M00060DeliveryComversionMstRt.DeliveryComversionMstRt(
						SearchClGpCD,
						SearchCLGpName,
						SearchCL_DECD,
						SearchDECD,
						SearchDepartmentCd,
						SearchDEName,
						SearchPost,
						SearchAdd,
						SearchTel,
						SearchFax,
						SearchMail,
						SearchSetName,
						SearchCom,
						SearchDelFg,
						SearchMstPriorityFirstFg,
						AllSearch);
	
	*/
	
	//戻り値カラム
	public static Object[][] RtDeliveryComversionMstRt(){
		Object[][] RtSettingDeliveryMstRt = {
				 {"ClGpCD"				,(int) 0	,"String"	,"荷主グループCD"}
				,{"CLGpName01"			,(int) 1	,"String"	,"荷主グループ名"}
				,{"CL_DECD"				,(int) 2	,"String"	,"荷主届先CD"}
				,{"DECD"				,(int) 3	,"String"	,"届先CD"}
				,{"DepartmentCd"		,(int) 4	,"String"	,"届先部署CD"}
				,{"DEName01"			,(int) 5	,"String"	,"届先名1"}
				,{"DEName02"			,(int) 6	,"String"	,"届先名2"}
				,{"DEName03"			,(int) 7	,"String"	,"届先名3"}
				,{"Post"				,(int) 8	,"String"	,"届先郵便"}
				,{"Add01"				,(int) 9	,"String"	,"届先住所1"}
				,{"Add02"				,(int)10	,"String"	,"届先住所2"}
				,{"Add03"				,(int)11	,"String"	,"届先住所3"}
				,{"Tel"					,(int)12	,"String"	,"届先電話"}
				,{"Fax"					,(int)13	,"String"	,"届先FAX"}
				,{"Mail"				,(int)14	,"String"	,"届先MAIL"}
				,{"SetName"				,(int)15	,"String"	,"送り状登録名"}
				,{"Com01"				,(int)16	,"String"	,"コメント01"}
				,{"Com02"				,(int)17	,"String"	,"コメント02"}
				,{"Com03"				,(int)18	,"String"	,"コメント03"}
				,{"Com04"				,(int)19	,"String"	,"コメント04"}
				,{"Com05"				,(int)20	,"String"	,"コメント05"}
				,{"EntryDate"			,(int)21	,"String"	,"データ登録日時"}
				,{"UpdateDate"			,(int)22	,"String"	,"データ更新日時"}
				,{"EntryUser"			,(int)23	,"String"	,"登録者コード"}
				,{"UpdateUser"			,(int)24	,"String"	,"更新者コード"}
				,{"DelFg"				,(int)25	,"int"		,"削除区分"}
				,{"MstPriorityFirstFg"	,(int)26	,"int"		,"届先マスタ優先フラグ"}
				};
		
		return RtSettingDeliveryMstRt;
	}
	
	public static Object[][] DeliveryComversionMstRt(
											ArrayList<String> SearchClGpCD,
											ArrayList<String> SearchCLGpName,
											ArrayList<String> SearchCL_DECD,
											ArrayList<String> SearchDECD,
											ArrayList<String> SearchDepartmentCd,
											ArrayList<String> SearchDEName,
											ArrayList<String> SearchPost,
											ArrayList<String> SearchAdd,
											ArrayList<String> SearchTel,
											ArrayList<String> SearchFax,
											ArrayList<String> SearchMail,
											ArrayList<String> SearchSetName,
											ArrayList<String> SearchCom,
											ArrayList<String> SearchDelFg,
											ArrayList<String> SearchMstPriorityFirstFg,
											boolean AllSearch){
		Object[][] rt = new Object[0][27];
		boolean SearchKick = false;
		if(AllSearch) {SearchKick = true;}
		
		String sql = "select \n"
				+"(KM0041_DELIVERY_COMVERSIONMST.ClGpCD)   as ClGpCD \n"			//荷主グループコード
					+",(KM0031_CLIENT_GROUP.CLGpName01)    as CLGpName01 \n"		//荷主グループ名
					
				+",(KM0041_DELIVERY_COMVERSIONMST.CL_DECD) as CL_DECD \n"			//荷主届先コード
				+",(KM0041_DELIVERY_COMVERSIONMST.DECD)    as DECD \n"				//届先コード
				+",(KM0041_DELIVERY_COMVERSIONMST.DepartmentCd) as DepartmentCd \n"	//届先部署CD
					+",(KM0040_DELIVERYMST.DEName01) as DEName01 \n"				//届先名1
					+",(KM0040_DELIVERYMST.DEName02) as DEName02 \n"				//届先名2
					+",(KM0040_DELIVERYMST.DEName03) as DEName03 \n"				//届先名3
					+",(KM0040_DELIVERYMST.Post)  as Post  \n"						//届先郵便
					+",(KM0040_DELIVERYMST.Add01) as Add01 \n"						//届先住所1
					+",(KM0040_DELIVERYMST.Add02) as Add02 \n"						//届先住所2
					+",(KM0040_DELIVERYMST.Add03) as Add03 \n"						//届先住所3
					+",(KM0040_DELIVERYMST.Tel)   as Tel  \n"						//届先電話
					+",(KM0040_DELIVERYMST.Fax)   as Fax  \n"						//届先FAX
					+",(KM0040_DELIVERYMST.Mail)  as Mail \n"						//届先MAIL
					
				+",(KM0041_DELIVERY_COMVERSIONMST.SetName) as SetName \n"			//送り状登録名
				+",(KM0041_DELIVERY_COMVERSIONMST.Com01)   as Com01 \n"				//コメント01
				+",(KM0041_DELIVERY_COMVERSIONMST.Com02)   as Com02 \n"				//コメント02
				+",(KM0041_DELIVERY_COMVERSIONMST.Com03)   as Com03 \n"				//コメント03
				+",(KM0041_DELIVERY_COMVERSIONMST.Com04)   as Com04 \n"				//コメント04
				+",(KM0041_DELIVERY_COMVERSIONMST.Com05)   as Com05 \n"				//コメント05
				+",(KM0041_DELIVERY_COMVERSIONMST.EntryDate)  as EntryDate  \n"		//データ登録日時
				+",(KM0041_DELIVERY_COMVERSIONMST.UpdateDate) as UpdateDate \n"		//データ更新日時
				+",(KM0041_DELIVERY_COMVERSIONMST.EntryUser)  as EntryUser  \n"		//登録者コード
				+",(KM0041_DELIVERY_COMVERSIONMST.UpdateUser) as UpdateUser \n"		//更新者コード
				+",(KM0041_DELIVERY_COMVERSIONMST.DelFg)      as DelFg \n"			//削除区分
				+",(KM0041_DELIVERY_COMVERSIONMST.MstPriorityFirstFg) as MstPriorityFirstFg \n"	//届先マスタ優先フラグ
				
				+" from "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0041_DELIVERY_COMVERSIONMST\n"
				
				+" left outer join "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0040_DELIVERYMST\n"
				+" on(KM0041_DELIVERY_COMVERSIONMST.DECD = KM0040_DELIVERYMST.DECD\n"
				+" and KM0041_DELIVERY_COMVERSIONMST.DepartmentCd = KM0040_DELIVERYMST.DepartmentCd\n"
				+")"
				
				+" left outer join "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP\n"
				+" on(KM0041_DELIVERY_COMVERSIONMST.ClGpCD = KM0031_CLIENT_GROUP.ClGpCD)\n"
				
				+" where 1=1\n";
		
		if(null!=SearchClGpCD && 0<SearchClGpCD.size()){
			SearchKick=true;
			sql = sql + " and(";
			for(int i=0;i<SearchClGpCD.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + "KM0041_DELIVERY_COMVERSIONMST.ClGpCD = ?";
			}
			sql= sql + ")\n";
		}
		if(null!=SearchCLGpName && 0<SearchCLGpName.size()){
			SearchKick=true;
			sql = sql + " and(";
			for(int i=0;i<SearchCLGpName.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + "KM0031_CLIENT_GROUP.CLGpName01 like ?";
			}
			sql= sql + ")\n";
		}
		if(null!=SearchCL_DECD && 0<SearchCL_DECD.size()){
			SearchKick=true;
			sql = sql + " and(";
			for(int i=0;i<SearchCL_DECD.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + "KM0041_DELIVERY_COMVERSIONMST.CL_DECD = ?";
			}
			sql= sql + ")\n";
		}
		if(null!=SearchDECD && 0<SearchDECD.size()){
			SearchKick=true;
			sql = sql + " and(";
			for(int i=0;i<SearchDECD.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + "KM0041_DELIVERY_COMVERSIONMST.DECD = ?";
			}
			sql= sql + ")\n";
		}
		if(null!=SearchDepartmentCd && 0<SearchDepartmentCd.size()){
			SearchKick=true;
			sql = sql + " and(";
			for(int i=0;i<SearchDepartmentCd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + "KM0041_DELIVERY_COMVERSIONMST.DepartmentCd = ?";
			}
			sql= sql + ")\n";
		}
		if(null!=SearchDEName && 0<SearchDEName.size()){
			SearchKick=true;
			sql = sql + " and(";
			for(int i=0;i<SearchDEName.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + "KM0040_DELIVERYMST.DEName01 like ?";
				sql = sql + " or KM0040_DELIVERYMST.DEName02 like ?";
				sql = sql + " or KM0040_DELIVERYMST.DEName03 like ?";
			}
			sql= sql + ")\n";
		}
		if(null!=SearchPost && 0<SearchPost.size()){
			SearchKick=true;
			sql = sql + " and(";
			for(int i=0;i<SearchPost.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + "KM0040_DELIVERYMST.Post like ?";
			}
			sql= sql + ")\n";
		}
		if(null!=SearchAdd && 0<SearchAdd.size()){
			SearchKick=true;
			sql = sql + " and(";
			for(int i=0;i<SearchAdd.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + "CONCAT (KM0040_DELIVERYMST.Add01";
				sql = sql + ",KM0040_DELIVERYMST.Add02";
				sql = sql + ",KM0040_DELIVERYMST.Add03) like ?";
			}
			sql= sql + ")\n";
		}
		if(null!=SearchTel && 0<SearchTel.size()){
			SearchKick=true;
			sql = sql + " and(";
			for(int i=0;i<SearchTel.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + "KM0040_DELIVERYMST.Tel like ?";
			}
			sql= sql + ")\n";
		}
		if(null!=SearchFax && 0<SearchFax.size()){
			SearchKick=true;
			sql = sql + " and(";
			for(int i=0;i<SearchFax.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + "KM0040_DELIVERYMST.Fax like ?";
			}
			sql= sql + ")\n";
		}
		if(null!=SearchMail && 0<SearchMail.size()){
			SearchKick=true;
			sql = sql + " and(";
			for(int i=0;i<SearchMail.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + "KM0040_DELIVERYMST.Mail like ?";
			}
			sql= sql + ")\n";
		}
		if(null!=SearchSetName && 0<SearchSetName.size()){
			SearchKick=true;
			sql = sql + " and(";
			for(int i=0;i<SearchSetName.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + "KM0041_DELIVERY_COMVERSIONMST.SetName like ?";
			}
			sql= sql + ")\n";
		}
		if(null!=SearchCom && 0<SearchCom.size()){
			SearchKick=true;
			sql = sql + " and(";
			for(int i=0;i<SearchCom.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + "KM0041_DELIVERY_COMVERSIONMST.Com01 like ?";
				sql = sql + " or KM0041_DELIVERY_COMVERSIONMST.Com02 like ?";
				sql = sql + " or KM0041_DELIVERY_COMVERSIONMST.Com03 like ?";
				sql = sql + " or KM0041_DELIVERY_COMVERSIONMST.Com04 like ?";
				sql = sql + " or KM0041_DELIVERY_COMVERSIONMST.Com05 like ?";
			}
			sql= sql + ")\n";
		}
		if(null!=SearchDelFg && 0<SearchDelFg.size()){
			SearchKick=true;
			sql = sql + " and(";
			for(int i=0;i<SearchDelFg.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + "KM0041_DELIVERY_COMVERSIONMST.DelFg = ?";
			}
			sql= sql + ")\n";
		}
		if(null!=SearchMstPriorityFirstFg && 0<SearchMstPriorityFirstFg.size()){
			SearchKick=true;
			sql = sql + " and(";
			for(int i=0;i<SearchMstPriorityFirstFg.size();i++){
				if(0<i){sql = sql + " or ";}
				sql = sql + "KM0041_DELIVERY_COMVERSIONMST.MstPriorityFirstFg = ?";
			}
			sql= sql + ")\n";
		}

		sql = sql + " order by KM0041_DELIVERY_COMVERSIONMST.ClGpCD,KM0041_DELIVERY_COMVERSIONMST.CL_DECD";
		//System.out.println(sql);
		
		if(SearchKick) {
			A00010DbConnect.DB_CONN("NYANKO");
			ResultSet rset01 = null;
			PreparedStatement stmt01 = null;
			try {
				stmt01 = A00010DbConnect.conn.prepareStatement(sql);
				int StmtCount = 0;
				
				if(null!=SearchClGpCD && 0<SearchClGpCD.size()){
					for(int i=0;i<SearchClGpCD.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchClGpCD.get(i)+"");
					}
				}
				if(null!=SearchCLGpName && 0<SearchCLGpName.size()){
					for(int i=0;i<SearchCLGpName.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchCLGpName.get(i)+"%");
					}
				}
				if(null!=SearchCL_DECD && 0<SearchCL_DECD.size()){
					for(int i=0;i<SearchCL_DECD.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchCL_DECD.get(i)+"");
					}
				}
				if(null!=SearchDECD && 0<SearchDECD.size()){
					for(int i=0;i<SearchDECD.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchDECD.get(i)+"");
					}
				}
				if(null!=SearchDepartmentCd && 0<SearchDepartmentCd.size()){
					for(int i=0;i<SearchDepartmentCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchDepartmentCd.get(i)+"");
					}
				}
				if(null!=SearchDEName && 0<SearchDEName.size()){
					for(int i=0;i<SearchDEName.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchDEName.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchDEName.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchDEName.get(i)+"%");
					}
				}
				if(null!=SearchPost && 0<SearchPost.size()){
					for(int i=0;i<SearchPost.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchPost.get(i)+"%");
					}
				}
				if(null!=SearchAdd && 0<SearchAdd.size()){
					for(int i=0;i<SearchAdd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchAdd.get(i)+"%");
					}
				}
				if(null!=SearchTel && 0<SearchTel.size()){
					for(int i=0;i<SearchTel.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchTel.get(i)+"%");
					}
				}
				if(null!=SearchFax && 0<SearchFax.size()){
					for(int i=0;i<SearchFax.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchFax.get(i)+"%");
					}
				}
				if(null!=SearchMail && 0<SearchMail.size()){
					for(int i=0;i<SearchMail.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchMail.get(i)+"%");
					}
				}
				if(null!=SearchSetName && 0<SearchSetName.size()){
					for(int i=0;i<SearchSetName.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchSetName.get(i)+"%");
					}
				}
				if(null!=SearchCom && 0<SearchCom.size()){
					for(int i=0;i<SearchCom.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchCom.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchCom.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchCom.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchCom.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchCom.get(i)+"%");
					}
				}
				if(null!=SearchDelFg && 0<SearchDelFg.size()){
					for(int i=0;i<SearchDelFg.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchDelFg.get(i)+"");
					}
				}
				if(null!=SearchMstPriorityFirstFg && 0<SearchMstPriorityFirstFg.size()){
					for(int i=0;i<SearchMstPriorityFirstFg.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchMstPriorityFirstFg.get(i)+"");
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
					if(null==rset01.getString("ClGpCD"			)){rt[counter][ 0] = "";}else{rt[counter][ 0] = rset01.getString("ClGpCD");}		//荷主グループCD
					if(null==rset01.getString("CLGpName01"		)){rt[counter][ 1] = "";}else{rt[counter][ 1] = rset01.getString("CLGpName01");}	//荷主グループ名
					if(null==rset01.getString("CL_DECD"			)){rt[counter][ 2] = "";}else{rt[counter][ 2] = rset01.getString("CL_DECD");}		//荷主届先CD
					if(null==rset01.getString("DECD"			)){rt[counter][ 3] = "";}else{rt[counter][ 3] = rset01.getString("DECD");}			//届先CD
					if(null==rset01.getString("DepartmentCd"	)){rt[counter][ 4] = "";}else{rt[counter][ 4] = rset01.getString("DepartmentCd");}	//届先部署CD
					if(null==rset01.getString("DEName01"		)){rt[counter][ 5] = "";}else{rt[counter][ 5] = rset01.getString("DEName01");}		//届先名1
					if(null==rset01.getString("DEName02"		)){rt[counter][ 6] = "";}else{rt[counter][ 6] = rset01.getString("DEName02");}		//届先名2
					if(null==rset01.getString("DEName03"		)){rt[counter][ 7] = "";}else{rt[counter][ 7] = rset01.getString("DEName03");}		//届先名3
					if(null==rset01.getString("Post"			)){rt[counter][ 8] = "";}else{rt[counter][ 8] = rset01.getString("Post");}			//届先郵便
					if(null==rset01.getString("Add01"			)){rt[counter][ 9] = "";}else{rt[counter][ 9] = rset01.getString("Add01");}			//届先住所1
					if(null==rset01.getString("Add02"			)){rt[counter][10] = "";}else{rt[counter][10] = rset01.getString("Add02");}			//届先住所2
					if(null==rset01.getString("Add03"			)){rt[counter][11] = "";}else{rt[counter][11] = rset01.getString("Add03");}			//届先住所3
					if(null==rset01.getString("Tel"				)){rt[counter][12] = "";}else{rt[counter][12] = rset01.getString("Tel");}			//届先電話
					if(null==rset01.getString("Fax"				)){rt[counter][13] = "";}else{rt[counter][13] = rset01.getString("Fax");}			//届先FAX
					if(null==rset01.getString("Mail"			)){rt[counter][14] = "";}else{rt[counter][14] = rset01.getString("Mail");}			//届先MAIL
					if(null==rset01.getString("SetName"			)){rt[counter][15] = "";}else{rt[counter][15] = rset01.getString("SetName");}		//送り状登録名
					if(null==rset01.getString("Com01"			)){rt[counter][16] = "";}else{rt[counter][16] = rset01.getString("Com01");}			//コメント01
					if(null==rset01.getString("Com02"			)){rt[counter][17] = "";}else{rt[counter][17] = rset01.getString("Com02");}			//コメント02
					if(null==rset01.getString("Com03"			)){rt[counter][18] = "";}else{rt[counter][18] = rset01.getString("Com03");}			//コメント03
					if(null==rset01.getString("Com04"			)){rt[counter][19] = "";}else{rt[counter][19] = rset01.getString("Com04");}			//コメント04
					if(null==rset01.getString("Com05"			)){rt[counter][20] = "";}else{rt[counter][20] = rset01.getString("Com05");}			//コメント05
					if(null==rset01.getString("EntryDate"		)){rt[counter][21] = "";}else{rt[counter][21] = B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("EntryDate"))[1];}		//データ登録日時
					if(null==rset01.getString("UpdateDate"		)){rt[counter][22] = "";}else{rt[counter][22] = B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("UpdateDate"))[1];}		//データ更新日時
					if(null==rset01.getString("EntryUser"		)){rt[counter][23] = "";}else{rt[counter][23] = rset01.getString("EntryUser");}		//登録者コード
					if(null==rset01.getString("UpdateUser"		)){rt[counter][24] = "";}else{rt[counter][24] = rset01.getString("UpdateUser");}	//更新者コード
					rt[counter][25] = rset01.getInt("DelFg");					//削除区分
					rt[counter][26] = rset01.getInt("MstPriorityFirstFg");		//届先マスタ優先フラグ
					
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