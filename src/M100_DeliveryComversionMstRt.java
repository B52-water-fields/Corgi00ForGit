import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class M100_DeliveryComversionMstRt{
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
	
	Object[][] DeliveryComversionMstRt = M100_DeliveryComversionMstRt.DeliveryComversionMstRt(
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
						
	String GetClGpCD			= (String)DeliveryComversionMstRt[i][M100_DeliveryComversionMstRt.ColClGpCD];			//荷主グループCD
	String GetCLGpName01		= (String)DeliveryComversionMstRt[i][M100_DeliveryComversionMstRt.ColCLGpName01];		//荷主グループ名
	String GetCL_DECD			= (String)DeliveryComversionMstRt[i][M100_DeliveryComversionMstRt.ColCL_DECD];			//荷主届先CD
	String GetDECD				= (String)DeliveryComversionMstRt[i][M100_DeliveryComversionMstRt.ColDECD];			//届先CD
	String GetDepartmentCd		= (String)DeliveryComversionMstRt[i][M100_DeliveryComversionMstRt.ColDepartmentCd];	//届先部署CD
	String GetDEName01			= (String)DeliveryComversionMstRt[i][M100_DeliveryComversionMstRt.ColDEName01];		//届先表記名
	String GetDEName02			= (String)DeliveryComversionMstRt[i][M100_DeliveryComversionMstRt.ColDEName02];		//届先正式名
	String GetDEName03			= (String)DeliveryComversionMstRt[i][M100_DeliveryComversionMstRt.ColDEName03];		//届先略名
	String GetPost				= (String)DeliveryComversionMstRt[i][M100_DeliveryComversionMstRt.ColPost];			//届先郵便
	String GetAdd01				= (String)DeliveryComversionMstRt[i][M100_DeliveryComversionMstRt.ColAdd01];			//届先住所1
	String GetAdd02				= (String)DeliveryComversionMstRt[i][M100_DeliveryComversionMstRt.ColAdd02];			//届先住所2
	String GetAdd03				= (String)DeliveryComversionMstRt[i][M100_DeliveryComversionMstRt.ColAdd03];			//届先住所3
	String GetTel				= (String)DeliveryComversionMstRt[i][M100_DeliveryComversionMstRt.ColTel];				//届先電話
	String GetFax				= (String)DeliveryComversionMstRt[i][M100_DeliveryComversionMstRt.ColFax];				//届先FAX
	String GetMail				= (String)DeliveryComversionMstRt[i][M100_DeliveryComversionMstRt.ColMail];			//届先MAIL
	String GetSetName			= (String)DeliveryComversionMstRt[i][M100_DeliveryComversionMstRt.ColSetName];			//送り状登録名
	String GetCom01				= (String)DeliveryComversionMstRt[i][M100_DeliveryComversionMstRt.ColCom01];			//コメント01
	String GetCom02				= (String)DeliveryComversionMstRt[i][M100_DeliveryComversionMstRt.ColCom02];			//コメント02
	String GetCom03				= (String)DeliveryComversionMstRt[i][M100_DeliveryComversionMstRt.ColCom03];			//コメント03
	String GetCom04				= (String)DeliveryComversionMstRt[i][M100_DeliveryComversionMstRt.ColCom04];			//コメント04
	String GetCom05				= (String)DeliveryComversionMstRt[i][M100_DeliveryComversionMstRt.ColCom05];			//コメント05
	String GetEntryDate			= (String)DeliveryComversionMstRt[i][M100_DeliveryComversionMstRt.ColEntryDate];		//データ登録日時
	String GetUpdateDate		= (String)DeliveryComversionMstRt[i][M100_DeliveryComversionMstRt.ColUpdateDate];		//データ更新日時
	String GetEntryUser			= (String)DeliveryComversionMstRt[i][M100_DeliveryComversionMstRt.ColEntryUser];		//登録者コード
	String GetUpdateUser		= (String)DeliveryComversionMstRt[i][M100_DeliveryComversionMstRt.ColUpdateUser];		//更新者コード
	int GetDelFg				= (int)DeliveryComversionMstRt[i][M100_DeliveryComversionMstRt.ColDelFg];				//削除区分
	int GetMstPriorityFirstFg	= (int)DeliveryComversionMstRt[i][M100_DeliveryComversionMstRt.ColMstPriorityFirstFg];	//届先マスタ優先フラグ
	
	*/
	
	//戻り値カラム
	
	static final int ColCLGpName01				= (int) 0;	//荷主グループ名
	static final int ColCL_DECD					= (int) 1;	//荷主届先CD
	static final int ColDECD						= (int) 2;	//届先CD
	static final int ColDepartmentCd				= (int) 3;	//届先部署CD
	static final int ColDEName01					= (int) 4;	//届先表記名
	static final int ColDEName02					= (int) 5;	//届先正式名
	static final int ColDEName03					= (int) 6;	//届先略名
	static final int ColPost						= (int) 7;	//届先郵便
	static final int ColAdd01						= (int) 8;	//届先住所1
	static final int ColAdd02						= (int) 9;	//届先住所2
	static final int ColAdd03						= (int)10;	//届先住所3
	static final int ColTel						= (int)11;	//届先電話
	static final int ColFax						= (int)12;	//届先FAX
	static final int ColMail						= (int)13;	//届先MAIL
	static final int ColSetName					= (int)14;	//送り状登録名
	static final int ColCom01						= (int)15;	//コメント01
	static final int ColCom02						= (int)16;	//コメント02
	static final int ColCom03						= (int)17;	//コメント03
	static final int ColCom04						= (int)18;	//コメント04
	static final int ColCom05						= (int)19;	//コメント05
	static final int ColEntryDate					= (int)20;	//データ登録日時
	static final int ColUpdateDate				= (int)21;	//データ更新日時
	static final int ColEntryUser					= (int)22;	//登録者コード
	static final int ColUpdateUser				= (int)23;	//更新者コード
	static final int ColClGpCD						= (int)24;	//荷主グループCD
	static final int ColDelFg						= (int)25;	//削除区分
	static final int ColMstPriorityFirstFg		= (int)26;	//届先マスタ優先フラグ
	
	public static Object[][] RtDeliveryComversionMstRt(){
		Object[][] RtSettingDeliveryMstRt = {
				 {"ClGpCD"				,ColClGpCD					,"String"	,"荷主グループCD"		,"Key"}
				,{"CLGpName01"			,ColCLGpName01			,"String"	,"荷主グループ名"		,""}
				,{"CL_DECD"				,ColCL_DECD				,"String"	,"荷主届先CD"			,"Key"}
				,{"DECD"				,ColDECD					,"String"	,"届先CD"				,""}
				,{"DepartmentCd"		,ColDepartmentCd			,"String"	,"届先部署CD"			,""}
				,{"DEName01"			,ColDEName01				,"String"	,"届先表記名"			,""}
				,{"DEName02"			,ColDEName02				,"String"	,"届先正式名"			,""}
				,{"DEName03"			,ColDEName03				,"String"	,"届先略名"				,""}
				,{"Post"				,ColPost					,"String"	,"届先郵便"				,""}
				,{"Add01"				,ColAdd01					,"String"	,"届先住所1"			,""}
				,{"Add02"				,ColAdd02					,"String"	,"届先住所2"			,""}
				,{"Add03"				,ColAdd03					,"String"	,"届先住所3"			,""}
				,{"Tel"					,ColTel					,"String"	,"届先電話"				,""}
				,{"Fax"					,ColFax					,"String"	,"届先FAX"				,""}
				,{"Mail"				,ColMail					,"String"	,"届先MAIL"				,""}
				,{"SetName"				,ColSetName				,"String"	,"送り状登録名"			,""}
				,{"Com01"				,ColCom01					,"String"	,"コメント01"			,""}
				,{"Com02"				,ColCom02					,"String"	,"コメント02"			,""}
				,{"Com03"				,ColCom03					,"String"	,"コメント03"			,""}
				,{"Com04"				,ColCom04					,"String"	,"コメント04"			,""}
				,{"Com05"				,ColCom05					,"String"	,"コメント05"			,""}
				,{"EntryDate"			,ColEntryDate				,"DateTime"	,"データ登録日時"		,""}
				,{"UpdateDate"			,ColUpdateDate			,"DateTime"	,"データ更新日時"		,""}
				,{"EntryUser"			,ColEntryUser				,"String"	,"登録者コード"			,""}
				,{"UpdateUser"			,ColUpdateUser			,"String"	,"更新者コード"			,""}
				,{"DelFg"				,ColDelFg					,"int"		,"削除区分"				,""}
				,{"MstPriorityFirstFg"	,ColMstPriorityFirstFg	,"int"		,"届先マスタ優先フラグ"	,""}
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
		
		SearchClGpCD				= B100_ArrayListControl.ArryListStringUniqueList(SearchClGpCD);
		SearchCLGpName				= B100_ArrayListControl.ArryListStringUniqueList(SearchCLGpName);
		SearchCL_DECD				= B100_ArrayListControl.ArryListStringUniqueList(SearchCL_DECD);
		SearchDECD					= B100_ArrayListControl.ArryListStringUniqueList(SearchDECD);
		SearchDepartmentCd			= B100_ArrayListControl.ArryListStringUniqueList(SearchDepartmentCd);
		SearchDEName				= B100_ArrayListControl.ArryListStringUniqueList(SearchDEName);
		SearchPost					= B100_ArrayListControl.ArryListStringUniqueList(SearchPost);
		SearchAdd					= B100_ArrayListControl.ArryListStringUniqueList(SearchAdd);
		SearchTel					= B100_ArrayListControl.ArryListStringUniqueList(SearchTel);
		SearchFax					= B100_ArrayListControl.ArryListStringUniqueList(SearchFax);
		SearchMail					= B100_ArrayListControl.ArryListStringUniqueList(SearchMail);
		SearchSetName				= B100_ArrayListControl.ArryListStringUniqueList(SearchSetName);
		SearchCom					= B100_ArrayListControl.ArryListStringUniqueList(SearchCom);
		SearchDelFg					= B100_ArrayListControl.ArryListStringUniqueList(SearchDelFg);
		SearchMstPriorityFirstFg	= B100_ArrayListControl.ArryListStringUniqueList(SearchMstPriorityFirstFg);
		
		Object[][] rt = new Object[0][RtDeliveryComversionMstRt().length];
		boolean SearchKick = false;
		if(AllSearch) {SearchKick = true;}
		
		String sql = "select \n"
				+"(KM0041_DELIVERY_COMVERSIONMST.ClGpCD)   as ClGpCD \n"			//荷主グループコード
					+",(KM0031_CLIENT_GROUP.CLGpName01)    as CLGpName01 \n"		//荷主グループ名
					
				+",(KM0041_DELIVERY_COMVERSIONMST.CL_DECD) as CL_DECD \n"			//荷主届先コード
				+",(KM0041_DELIVERY_COMVERSIONMST.DECD)    as DECD \n"				//届先コード
				+",(KM0041_DELIVERY_COMVERSIONMST.DepartmentCd) as DepartmentCd \n"	//届先部署CD
					+",(KM0040_DELIVERYMST.DEName01) as DEName01 \n"				//届先表記名
					+",(KM0040_DELIVERYMST.DEName02) as DEName02 \n"				//届先正式名
					+",(KM0040_DELIVERYMST.DEName03) as DEName03 \n"				//届先略名
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
				
				+" from "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0041_DELIVERY_COMVERSIONMST\n"
				
				+" left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0040_DELIVERYMST\n"
				+" on(KM0041_DELIVERY_COMVERSIONMST.DECD = KM0040_DELIVERYMST.DECD\n"
				+" and KM0041_DELIVERY_COMVERSIONMST.DepartmentCd = KM0040_DELIVERYMST.DepartmentCd\n"
				+")"
				
				+" left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0031_CLIENT_GROUP\n"
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
			A100_DbConnect.DB_CONN("NYANKO");
			ResultSet rset01 = null;
			PreparedStatement stmt01 = null;
			try {
				stmt01 = A100_DbConnect.conn.prepareStatement(sql);
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
				
				rt = B100_RtObjectCreate.B100_RtObjectCreate(rset01,RtDeliveryComversionMstRt());
				
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
			A100_DbConnect.close();
		}
		return rt;
	}
}