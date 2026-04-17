import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class M00100SupplierRt{
	/*
	コピペ用
	ArrayList<String> SearchClWh = new ArrayList<String>(); 			//担当倉庫
	ArrayList<String> SearchClCd = new ArrayList<String>();				//荷主CD
	ArrayList<String> SearchSPCd = new ArrayList<String>();				//仕入先コード
	ArrayList<String> SearchSPName = new ArrayList<String>();			//仕入先名
	ArrayList<String> SearchSPPost = new ArrayList<String>();			//仕入先郵便
	ArrayList<String> SearchSPAdd = new ArrayList<String>();			//仕入先住所
	ArrayList<String> SearchSPTel = new ArrayList<String>();			//仕入先電話
	ArrayList<String> SearchSPFax = new ArrayList<String>();			//仕入先FAX
	ArrayList<String> SearchSPMail = new ArrayList<String>();			//仕入先MAIL
	ArrayList<String> SearchCom = new ArrayList<String>();				//コメント
	ArrayList<String> SearchPTMSCDBMN = new ArrayList<String>();		//基幹Sysコード（部門）
	ArrayList<String> SearchPTMSCDNINUSHI = new ArrayList<String>();	//基幹Sysコード（荷主）
	ArrayList<Integer> SearchPaySiteStr = new ArrayList<Integer>();		//支払いサイト（月数）開始
	ArrayList<Integer> SearchPayDateStr = new ArrayList<Integer>();		//支払日（日＝99）開始
	ArrayList<Integer> SearchShimeDateStr = new ArrayList<Integer>();	//締め日（末日＝99）開始
	ArrayList<Integer> SearchPaySiteEnd = new ArrayList<Integer>();		//支払いサイト（月数）終了
	ArrayList<Integer> SearchPayDateEnd = new ArrayList<Integer>();		//支払日（日＝99）終了
	ArrayList<Integer> SearchShimeDateEnd = new ArrayList<Integer>();	//締め日（末日＝99）終了
	ArrayList<String> SearchDECD = new ArrayList<String>();				//納品先コード
	ArrayList<String> SearchDepartmentCd = new ArrayList<String>();		//部署CD
	boolean AllSearch = false;
	
	Object[][] SupplierRt = M00100SupplierRt.SupplierRt(
			SearchClWh,				//担当倉庫
			SearchClCd,				//荷主CD
			SearchSPCd,				//仕入先コード
			SearchSPName,			//仕入先名
			SearchSPPost,			//仕入先郵便
			SearchSPAdd,			//仕入先住所
			SearchSPTel,			//仕入先電話
			SearchSPFax,			//仕入先FAX
			SearchSPMail,			//仕入先MAIL
			SearchCom,				//コメント
			SearchPTMSCDBMN,		//基幹Sysコード（部門）
			SearchPTMSCDNINUSHI,	//基幹Sysコード（荷主）
			SearchPaySiteStr,		//支払いサイト（月数）開始
			SearchPayDateStr,		//支払日（日＝99）開始
			SearchShimeDateStr,		//締め日（末日＝99）開始
			SearchPaySiteEnd,		//支払いサイト（月数）終了
			SearchPayDateEnd,		//支払日（日＝99）終了
			SearchShimeDateEnd,		//締め日（末日＝99）終了
			SearchDECD,				//納品先コード
			SearchDepartmentCd,		//部署CD
			AllSearch);
			
			String GetClWh			=	(String)SupplierRt[i][M00100SupplierRt.ColClWh];			//担当倉庫
			String GetWHName		=	(String)SupplierRt[i][M00100SupplierRt.ColWHName];			//担当倉庫名
			String GetClCd			=	(String)SupplierRt[i][M00100SupplierRt.ColClCd];			//荷主CD
			String GetCLName01		=	(String)SupplierRt[i][M00100SupplierRt.ColCLName01];		//荷主名1
			String GetSPCd			=	(String)SupplierRt[i][M00100SupplierRt.ColSPCd];			//仕入先コード
			String GetSPName01		=	(String)SupplierRt[i][M00100SupplierRt.ColSPName01];		//仕入先名1
			String GetSPName02		=	(String)SupplierRt[i][M00100SupplierRt.ColSPName02];		//仕入先名2
			String GetSPName03		=	(String)SupplierRt[i][M00100SupplierRt.ColSPName03];		//仕入先名3
			String GetSPPost		=	(String)SupplierRt[i][M00100SupplierRt.ColSPPost];			//仕入先郵便
			String GetSPAdd01		=	(String)SupplierRt[i][M00100SupplierRt.ColSPAdd01];			//仕入先住所1
			String GetSPAdd02		=	(String)SupplierRt[i][M00100SupplierRt.ColSPAdd02];			//仕入先住所2
			String GetSPAdd03		=	(String)SupplierRt[i][M00100SupplierRt.ColSPAdd03];			//仕入先住所3
			String GetSPTel			=	(String)SupplierRt[i][M00100SupplierRt.ColSPTel];			//仕入先電話
			String GetSPFax			=	(String)SupplierRt[i][M00100SupplierRt.ColSPFax];			//仕入先FAX
			String GetSPMail		=	(String)SupplierRt[i][M00100SupplierRt.ColSPMail];			//仕入先MAIL
			String GetCom01			=	(String)SupplierRt[i][M00100SupplierRt.ColCom01];			//コメント1
			String GetCom02			=	(String)SupplierRt[i][M00100SupplierRt.ColCom02];			//コメント2
			String GetCom03			=	(String)SupplierRt[i][M00100SupplierRt.ColCom03];			//コメント3
			String GetPTMSCDBMN		=	(String)SupplierRt[i][M00100SupplierRt.ColPTMSCDBMN];		//基幹Sysコード（部門）
			String GetPTMSCDNINUSHI	=	(String)SupplierRt[i][M00100SupplierRt.ColPTMSCDNINUSHI];	//基幹Sysコード（荷主）
			int GetPaySite			=	(int)SupplierRt[i][M00100SupplierRt.ColPaySite];			//支払いサイト（月数）
			int GetPayDate			=	(int)SupplierRt[i][M00100SupplierRt.ColPayDate];			//支払日（末日＝99）
			int GetShimeDate		=	(int)SupplierRt[i][M00100SupplierRt.ColShimeDate];			//締め日（末日＝99）
			String GetEntryDate		=	(String)SupplierRt[i][M00100SupplierRt.ColEntryDate];		//登録日
			String GetUpdateDate	=	(String)SupplierRt[i][M00100SupplierRt.ColUpdateDate];		//更新日
			String GetEntryUser		=	(String)SupplierRt[i][M00100SupplierRt.ColEntryUser];		//登録者
			String GetUpdateUser	=	(String)SupplierRt[i][M00100SupplierRt.ColUpdateUser];		//更新者
			String GetDECD			=	(String)SupplierRt[i][M00100SupplierRt.ColDECD];			//納品先コード
			String GetDepartmentCd	=	(String)SupplierRt[i][M00100SupplierRt.ColDepartmentCd];	//部署CD
			String GetDEName01		=	(String)SupplierRt[i][M00100SupplierRt.ColDEName01];		//納品先名1
	
	*/
	static int ColClWh				= (int)0;	//担当倉庫
	static int ColWHName			= (int)1;	//担当倉庫名
	static int ColClCd				= (int)2;	//荷主CD
	static int ColCLName01			= (int)3;	//荷主名1
	static int ColSPCd				= (int)4;	//仕入先コード
	static int ColSPName01			= (int)5;	//仕入先名1
	static int ColSPName02			= (int)6;	//仕入先名2
	static int ColSPName03			= (int)7;	//仕入先名3
	static int ColSPPost			= (int)8;	//仕入先郵便
	static int ColSPAdd01			= (int)9;	//仕入先住所1
	static int ColSPAdd02			= (int)10;	//仕入先住所2
	static int ColSPAdd03			= (int)11;	//仕入先住所3
	static int ColSPTel			= (int)12;	//仕入先電話
	static int ColSPFax			= (int)13;	//仕入先FAX
	static int ColSPMail			= (int)14;	//仕入先MAIL
	static int ColCom01			= (int)15;	//コメント1
	static int ColCom02			= (int)16;	//コメント2
	static int ColCom03			= (int)17;	//コメント3
	static int ColPTMSCDBMN		= (int)18;	//基幹Sysコード（部門）
	static int ColPTMSCDNINUSHI	= (int)19;	//基幹Sysコード（荷主）
	static int ColPaySite			= (int)20;	//支払いサイト（月数）
	static int ColPayDate			= (int)21;	//支払日　末日＝99
	static int ColShimeDate		= (int)22;	//締め日　末日＝99
	static int ColEntryDate		= (int)23;	//登録日
	static int ColUpdateDate		= (int)24;	//更新日
	static int ColEntryUser		= (int)25;	//登録者
	static int ColUpdateUser		= (int)26;	//更新者
	static int ColDECD				= (int)27;	//納品先コード
	static int ColDepartmentCd	= (int)28;	//部署CD
	static int ColDEName01			= (int)29;	//納品先名1
	
	public static Object[][] RtSupplierRt(){
		Object[][] RtSupplierRt = {
				 {"ClWh"			,ColClWh			,"String"	,"担当倉庫"					,"Key"}
				,{"WHName"			,ColWHName			,"String"	,"担当倉庫名"				,""}
				,{"ClCd"			,ColClCd			,"String"	,"荷主CD"					,"Key"}
				,{"CLName01"		,ColCLName01		,"String"	,"荷主名1"					,""}
				,{"SPCd"			,ColSPCd			,"String"	,"仕入先コード"				,"Key"}
				,{"SPName01"		,ColSPName01		,"String"	,"仕入先名1"				,""}
				,{"SPName02"		,ColSPName02		,"String"	,"仕入先名2"				,""}
				,{"SPName03"		,ColSPName03		,"String"	,"仕入先名3"				,""}
				,{"SPPost"			,ColSPPost			,"String"	,"仕入先郵便"				,""}
				,{"SPAdd01"			,ColSPAdd01		,"String"	,"仕入先住所1"				,""}
				,{"SPAdd02"			,ColSPAdd02		,"String"	,"仕入先住所2"				,""}
				,{"SPAdd03"			,ColSPAdd03		,"String"	,"仕入先住所3"				,""}
				,{"SPTel"			,ColSPTel			,"String"	,"仕入先電話"				,""}
				,{"SPFax"			,ColSPFax			,"String"	,"仕入先FAX"				,""}
				,{"SPMail"			,ColSPMail			,"String"	,"仕入先MAIL"				,""}
				,{"Com01"			,ColCom01			,"String"	,"コメント1"				,""}
				,{"Com02"			,ColCom02			,"String"	,"コメント2"				,""}
				,{"Com03"			,ColCom03			,"String"	,"コメント3"				,""}
				,{"PTMSCDBMN"		,ColPTMSCDBMN		,"String"	,"基幹Sysコード（部門）"	,""}
				,{"PTMSCDNINUSHI"	,ColPTMSCDNINUSHI	,"String"	,"基幹Sysコード（荷主）"	,""}
				,{"PaySite"			,ColPaySite		,"int"		,"支払いサイト（月数）"		,""}
				,{"PayDate"			,ColPayDate		,"int"		,"支払日（末日＝99）"		,""}
				,{"ShimeDate"		,ColShimeDate		,"int"		,"締め日（末日＝99）"		,""}
				,{"EntryDate"		,ColEntryDate		,"String"	,"登録日"					,""}
				,{"UpdateDate"		,ColUpdateDate	,"String"	,"更新日"					,""}
				,{"EntryUser"		,ColEntryUser		,"String"	,"登録者"					,""}
				,{"UpdateUser"		,ColUpdateUser	,"String"	,"更新者"					,""}
				,{"DECD"			,ColDECD			,"String"	,"納品先コード"				,""}
				,{"DepartmentCd"	,ColDepartmentCd	,"String"	,"部署CD"					,""}
				,{"DEName01"		,ColDEName01		,"String"	,"納品先名1"				,""}
				};
		
		return RtSupplierRt;
	}
	
	public static Object[][] SupplierRt(
			ArrayList<String> SearchClWh,			//担当倉庫
			ArrayList<String> SearchClCd,			//荷主CD
			ArrayList<String> SearchSPCd,			//仕入先コード
			ArrayList<String> SearchSPName,			//仕入先名
			ArrayList<String> SearchSPPost,			//仕入先郵便
			ArrayList<String> SearchSPAdd,			//仕入先住所
			ArrayList<String> SearchSPTel,			//仕入先電話
			ArrayList<String> SearchSPFax,			//仕入先FAX
			ArrayList<String> SearchSPMail,			//仕入先MAIL
			ArrayList<String> SearchCom,			//コメント
			ArrayList<String> SearchPTMSCDBMN,		//基幹Sysコード（部門）
			ArrayList<String> SearchPTMSCDNINUSHI,	//基幹Sysコード（荷主）
			ArrayList<Integer> SearchPaySiteStr,	//支払いサイト（月数）開始
			ArrayList<Integer> SearchPayDateStr,	//支払日（日＝99）開始
			ArrayList<Integer> SearchShimeDateStr,	//締め日（末日＝99）開始
			ArrayList<Integer> SearchPaySiteEnd,	//支払いサイト（月数）終了
			ArrayList<Integer> SearchPayDateEnd,	//支払日（日＝99）終了
			ArrayList<Integer> SearchShimeDateEnd,	//締め日（末日＝99）終了
			ArrayList<String> SearchDECD,			//納品先コード
			ArrayList<String> SearchDepartmentCd,	//部署CD
			boolean AllSearch){
		
		SearchClWh			= B00150ArrayListControl.ArryListStringUniqueList(SearchClWh);			//担当倉庫
		SearchClCd			= B00150ArrayListControl.ArryListStringUniqueList(SearchClCd);			//荷主CD
		SearchSPCd			= B00150ArrayListControl.ArryListStringUniqueList(SearchSPCd);			//仕入先コード
		SearchSPName		= B00150ArrayListControl.ArryListStringUniqueList(SearchSPName);		//仕入先名
		SearchSPPost		= B00150ArrayListControl.ArryListStringUniqueList(SearchSPPost);		//仕入先郵便
		SearchSPAdd			= B00150ArrayListControl.ArryListStringUniqueList(SearchSPAdd);			//仕入先住所
		SearchSPTel			= B00150ArrayListControl.ArryListStringUniqueList(SearchSPTel);			//仕入先電話
		SearchSPFax			= B00150ArrayListControl.ArryListStringUniqueList(SearchSPFax);			//仕入先FAX
		SearchSPMail		= B00150ArrayListControl.ArryListStringUniqueList(SearchSPMail);		//仕入先MAIL
		SearchCom			= B00150ArrayListControl.ArryListStringUniqueList(SearchCom);			//コメント
		SearchPTMSCDBMN		= B00150ArrayListControl.ArryListStringUniqueList(SearchPTMSCDBMN);		//基幹Sysコード（部門）
		SearchPTMSCDNINUSHI	= B00150ArrayListControl.ArryListStringUniqueList(SearchPTMSCDNINUSHI);	//基幹Sysコード（荷主）
		SearchPaySiteStr	= B00150ArrayListControl.ArryListIntegerUniqueList(SearchPaySiteStr);	//支払いサイト（月数）開始
		SearchPayDateStr	= B00150ArrayListControl.ArryListIntegerUniqueList(SearchPayDateStr);	//支払日（日＝99）　開始
		SearchShimeDateStr	= B00150ArrayListControl.ArryListIntegerUniqueList(SearchShimeDateStr);	//締め日（末日＝99　開始
		SearchPaySiteEnd	= B00150ArrayListControl.ArryListIntegerUniqueList(SearchPaySiteEnd);	//支払いサイト（月数）終了
		SearchPayDateEnd	= B00150ArrayListControl.ArryListIntegerUniqueList(SearchPayDateEnd);	//支払日（日＝99）終了
		SearchShimeDateEnd	= B00150ArrayListControl.ArryListIntegerUniqueList(SearchShimeDateEnd);	//締め日（末日＝99）終了
		SearchDECD			= B00150ArrayListControl.ArryListStringUniqueList(SearchDECD);			//納品先コード
		SearchDepartmentCd	= B00150ArrayListControl.ArryListStringUniqueList(SearchDepartmentCd);	//部署CD
		
		Object [][]rt = new Object[0][RtSupplierRt().length];
		boolean SearchKick = false;
		if(AllSearch) {SearchKick = true;}
		
		String sql = "select "
				+"(WM0010Supplier.ClWh) as ClWh,\n"						//担当倉庫
				+"(KM0010_WHMST.WHName) as WHName,\n"					//担当倉庫名
				+"(WM0010Supplier.ClCd) as ClCd,\n"						//荷主CD
				+"(KM0030_CLIENTMST.CLName01) as CLName01,\n"			//荷主名1
				+"(WM0010Supplier.SPCd) as SPCd,\n"						//仕入先コード
				+"(WM0010Supplier.SPName01) as SPName01,\n"				//仕入先名1
				+"(WM0010Supplier.SPName02) as SPName02,\n"				//仕入先名2
				+"(WM0010Supplier.SPName03) as SPName03,\n"				//仕入先名3
				+"(WM0010Supplier.SPPost) as SPPost,\n"					//仕入先郵便
				+"(WM0010Supplier.SPAdd01) as SPAdd01,\n"				//仕入先住所1
				+"(WM0010Supplier.SPAdd02) as SPAdd02,\n"				//仕入先住所2
				+"(WM0010Supplier.SPAdd03) as SPAdd03,\n"				//仕入先住所3
				+"(WM0010Supplier.SPTel) as SPTel,\n"					//仕入先電話
				+"(WM0010Supplier.SPFax) as SPFax,\n"					//仕入先FAX
				+"(WM0010Supplier.SPMail) as SPMail,\n"					//仕入先MAIL
				+"(WM0010Supplier.Com01) as Com01,\n"					//コメント1
				+"(WM0010Supplier.Com02) as Com02,\n"					//コメント2
				+"(WM0010Supplier.Com03) as Com03,\n"					//コメント3
				+"(WM0010Supplier.PTMSCDBMN) as PTMSCDBMN,\n"			//基幹Sysコード（部門）
				+"(WM0010Supplier.PTMSCDNINUSHI) as PTMSCDNINUSHI,\n"	//基幹Sysコード（荷主）
				+"(WM0010Supplier.PaySite) as PaySite,\n"				//支払いサイト（月数）
				+"(WM0010Supplier.PayDate) as PayDate,\n"				//支払日（日＝99）
				+"(WM0010Supplier.ShimeDate) as ShimeDate,\n"			//締め日（末日＝99）
				+"(WM0010Supplier.EntryDate) as EntryDate,\n"			//登録日
				+"(WM0010Supplier.UpdateDate) as UpdateDate,\n"			//更新日
				+"(WM0010Supplier.EntryUser) as EntryUser,\n"			//登録者
				+"(WM0010Supplier.UpdateUser) as UpdateUser,\n"			//更新者
				+"(WM0010Supplier.DECD) as DECD,\n"						//納品先コード
				+"(WM0010Supplier.DepartmentCd) as DepartmentCd,\n"		//部署CD
				+"(KM0040_DELIVERYMST.DEName01) as DEName01\n"			//納品先名1
				+ " from "+A00000Main.MySqlDefaultSchemaWANKO+".WM0010Supplier"
				+ " left outer join "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0030_CLIENTMST"
				+ " on("
				+ " WM0010Supplier.ClCd = KM0030_CLIENTMST.cl_cd"
				+ ")\n"
				+ " left outer join "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST"
				+ " on("
				+ " WM0010Supplier.ClWh = KM0010_WHMST.WHCD"
				+ ")\n"
				+ " left outer join "+A00000Main.MySqlDefaultSchemaNYANKO+".KM0040_DELIVERYMST"
				+ " on("
				+ " WM0010Supplier.DECD = KM0040_DELIVERYMST.DECD"
				+ " and WM0010Supplier.DepartmentCd = KM0040_DELIVERYMST.DepartmentCd"
				+ ")\n"
				+" where 1=1 \n";
		
		if(null!=SearchClWh && 0<SearchClWh.size()){			//担当倉庫
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchClWh.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0010Supplier.ClWh =?";
			}
			sql = sql + ")";
		}
		if(null!=SearchClCd && 0<SearchClCd.size()){			//荷主CD
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchClCd.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0010Supplier.ClCd =?";
			}
			sql = sql + ")";
		}
		if(null!=SearchSPCd && 0<SearchSPCd.size()){			//仕入先コード
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchSPCd.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0010Supplier.SPCd =?";
			}
			sql = sql + ")";
		}
		if(null!=SearchSPName && 0<SearchSPName.size()){			//仕入先名
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchSPName.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0010Supplier.SPName01 like ?";
				sql = sql + " or WM0010Supplier.SPName02 like ?";
				sql = sql + " or WM0010Supplier.SPName03 like ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchSPPost && 0<SearchSPPost.size()){			//仕入先郵便
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchSPPost.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0010Supplier.SPPost like ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchSPAdd && 0<SearchSPAdd.size()){			//仕入先住所
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchSPAdd.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "CONCAT (WM0010Supplier.SPAdd01 "
						+ ",WM0010Supplier.SPAdd02 "
						+ ",WM0010Supplier.SPAdd03 "
						+ ") like ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchSPTel && 0<SearchSPTel.size()){			//仕入先電話
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchSPTel.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0010Supplier.SPTel like ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchSPFax && 0<SearchSPFax.size()){			//仕入先FAX
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchSPFax.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0010Supplier.SPFax like ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchSPMail && 0<SearchSPMail.size()){			//仕入先MAIL
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchSPMail.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0010Supplier.SPMail like ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchCom && 0<SearchCom.size()){			//コメント
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchCom.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0010Supplier.Com01 like ?";
				sql = sql + " or WM0010Supplier.Com02 like ?";
				sql = sql + " or WM0010Supplier.Com03 like ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchPTMSCDBMN && 0<SearchPTMSCDBMN.size()){		//基幹Sysコード（部門）
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchPTMSCDBMN.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0010Supplier.PTMSCDBMN =?";
			}
			sql = sql + ")";
		}
		if(null!=SearchPTMSCDNINUSHI && 0<SearchPTMSCDNINUSHI.size()){	//基幹Sysコード（荷主）
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchPTMSCDNINUSHI.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0010Supplier.PTMSCDNINUSHI =?";
			}
			sql = sql + ")";
		}
		if(null!=SearchPaySiteStr && 0<SearchPaySiteStr.size()){	//支払いサイト（月数）開始
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchPaySiteStr.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0010Supplier.PaySite >=?";
			}
			sql = sql + ")";
		}
		if(null!=SearchPayDateStr && 0<SearchPayDateStr.size()){	//支払日（日＝99）開始
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchPayDateStr.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0010Supplier.PayDate >=?";
			}
			sql = sql + ")";
		}
		if(null!=SearchShimeDateStr && 0<SearchShimeDateStr.size()){	//締め日（末日＝99）開始
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchShimeDateStr.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0010Supplier.ShimeDate >=?";
			}
			sql = sql + ")";
		}
		if(null!=SearchPaySiteEnd && 0<SearchPaySiteEnd.size()){	//支払いサイト（月数）終了
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchPaySiteEnd.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0010Supplier.PaySite <=?";
			}
			sql = sql + ")";
		}
		if(null!=SearchPayDateEnd && 0<SearchPayDateEnd.size()){	//支払日（日＝99）終了
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchPayDateEnd.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0010Supplier.PayDate <=?";
			}
			sql = sql + ")";
		}
		if(null!=SearchShimeDateEnd && 0<SearchShimeDateEnd.size()){	//締め日（末日＝99）終了
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchShimeDateEnd.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0010Supplier.ShimeDate <=?";
			}
			sql = sql + ")";
		}
		if(null!=SearchDECD && 0<SearchDECD.size()){			//納品先コード
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchDECD.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0010Supplier.DECD =?";
			}
			sql = sql + ")";
		}
		if(null!=SearchDepartmentCd && 0<SearchDepartmentCd.size()){	//部署CD
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchDepartmentCd.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0010Supplier.DepartmentCd =?";
			}
			sql = sql + ")";
		}
		sql = sql + " order by WM0010Supplier.ClWh,WM0010Supplier.ClCd,WM0010Supplier.SPCd";
		
		//System.out.println(sql);
		if(true==SearchKick) {
			A00010DbConnect.DB_CONN("WANKO");
			ResultSet rset01 = null;
			PreparedStatement stmt01 = null;
			try {
				stmt01 = A00010DbConnect.conn.prepareStatement(sql);
				int StmtCount = 0;
				
				if(null!=SearchClWh && 0<SearchClWh.size()){					//担当倉庫
					for(int i=0;i<SearchClWh.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchClWh.get(i)+"");
					}
				}
				if(null!=SearchClCd && 0<SearchClCd.size()){					//荷主CD
					for(int i=0;i<SearchClCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchClCd.get(i)+"");
					}
				}
				if(null!=SearchSPCd && 0<SearchSPCd.size()){					//仕入先コード
					for(int i=0;i<SearchSPCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchSPCd.get(i)+"");
					}
				}
				if(null!=SearchSPName && 0<SearchSPName.size()){				//仕入先名
					for(int i=0;i<SearchSPName.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchSPName.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchSPName.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchSPName.get(i)+"%");
					}
				}
				if(null!=SearchSPPost && 0<SearchSPPost.size()){				//仕入先郵便
					for(int i=0;i<SearchSPPost.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchSPPost.get(i)+"%");
					}
				}
				if(null!=SearchSPAdd && 0<SearchSPAdd.size()){					//仕入先住所
					for(int i=0;i<SearchSPAdd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchSPAdd.get(i)+"%");
					}
				}
				if(null!=SearchSPTel && 0<SearchSPTel.size()){					//仕入先Tel
					for(int i=0;i<SearchSPTel.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchSPTel.get(i)+"%");
					}
				}
				if(null!=SearchSPFax && 0<SearchSPFax.size()){					//仕入先FAX
					for(int i=0;i<SearchSPFax.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchSPFax.get(i)+"%");
					}
				}
				if(null!=SearchSPMail && 0<SearchSPMail.size()){				//仕入先MAIL
					for(int i=0;i<SearchSPMail.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchSPMail.get(i)+"%");
					}
				}
				if(null!=SearchCom && 0<SearchCom.size()){						//コメント
					for(int i=0;i<SearchCom.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchCom.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchCom.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchCom.get(i)+"%");
					}
				}
				if(null!=SearchPTMSCDBMN && 0<SearchPTMSCDBMN.size()){			//基幹Sysコード（部門）
					for(int i=0;i<SearchPTMSCDBMN.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchPTMSCDBMN.get(i)+"");
					}
				}
				if(null!=SearchPTMSCDNINUSHI && 0<SearchPTMSCDNINUSHI.size()){	//基幹Sysコード（荷主）
					for(int i=0;i<SearchPTMSCDNINUSHI.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchPTMSCDNINUSHI.get(i)+"");
					}
				}
				if(null!=SearchPaySiteStr && 0<SearchPaySiteStr.size()){		//支払いサイト（月数）開始
					for(int i=0;i<SearchPaySiteStr.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchPaySiteStr.get(i)+"");
					}
				}
				if(null!=SearchPayDateStr && 0<SearchPayDateStr.size()){		//支払日（日＝99）開始
					for(int i=0;i<SearchPayDateStr.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchPayDateStr.get(i)+"");
					}
				}
				if(null!=SearchShimeDateStr && 0<SearchShimeDateStr.size()){	//締め日（末日＝99）開始
					for(int i=0;i<SearchShimeDateStr.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchShimeDateStr.get(i)+"");
					}
				}
				if(null!=SearchPaySiteEnd && 0<SearchPaySiteEnd.size()){		//支払いサイト（月数）
					for(int i=0;i<SearchPaySiteEnd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchPaySiteEnd.get(i)+"");
					}
				}
				if(null!=SearchPayDateEnd && 0<SearchPayDateEnd.size()){		//支払日（日＝99）終了
					for(int i=0;i<SearchPayDateEnd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchPayDateEnd.get(i)+"");
					}
				}
				if(null!=SearchShimeDateEnd && 0<SearchShimeDateEnd.size()){	//締め日（末日＝99）終了
					for(int i=0;i<SearchShimeDateEnd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchShimeDateEnd.get(i)+"");
					}
				}
				if(null!=SearchDECD && 0<SearchDECD.size()){					//納品先コード
					for(int i=0;i<SearchDECD.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchDECD.get(i)+"");
					}
				}
				if(null!=SearchDepartmentCd && 0<SearchDepartmentCd.size()){	//部署CD
					for(int i=0;i<SearchDepartmentCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchDepartmentCd.get(i)+"");
					}
				}
				
				rset01 = stmt01.executeQuery();
				
				int counter = 0;
				rset01.beforeFirst();
				while (rset01.next()) {
					counter=counter+1;
				}
				
				rt = new Object[counter][RtSupplierRt().length];
				counter = 0;
				rset01.beforeFirst();
				
				while (rset01.next()) {
					if(null==rset01.getString("ClWh")			){rt[counter][ColClWh]				="";}else{rt[counter][ColClWh]				=rset01.getString("ClWh");}							//担当倉庫
					if(null==rset01.getString("WHName")			){rt[counter][ColWHName]			="";}else{rt[counter][ColWHName]			=rset01.getString("WHName");}						//担当倉庫名
					if(null==rset01.getString("ClCd")			){rt[counter][ColClCd]				="";}else{rt[counter][ColClCd]				=rset01.getString("ClCd");}							//荷主CD
					if(null==rset01.getString("CLName01")		){rt[counter][ColCLName01]			="";}else{rt[counter][ColCLName01]			=rset01.getString("CLName01");}						//荷主名1
					if(null==rset01.getString("SPCd")			){rt[counter][ColSPCd]				="";}else{rt[counter][ColSPCd]				=rset01.getString("SPCd");}							//仕入先コード
					if(null==rset01.getString("SPName01")		){rt[counter][ColSPName01]			="";}else{rt[counter][ColSPName01]			=rset01.getString("SPName01");}						//仕入先名1
					if(null==rset01.getString("SPName02")		){rt[counter][ColSPName02]			="";}else{rt[counter][ColSPName02]			=rset01.getString("SPName02");}						//仕入先名2
					if(null==rset01.getString("SPName03")		){rt[counter][ColSPName03]			="";}else{rt[counter][ColSPName03]			=rset01.getString("SPName03");}						//仕入先名3
					if(null==rset01.getString("SPPost")			){rt[counter][ColSPPost]			="";}else{rt[counter][ColSPPost]			=rset01.getString("SPPost");}						//仕入先郵便
					if(null==rset01.getString("SPAdd01")		){rt[counter][ColSPAdd01]			="";}else{rt[counter][ColSPAdd01]			=rset01.getString("SPAdd01");}						//仕入先住所1
					if(null==rset01.getString("SPAdd02")		){rt[counter][ColSPAdd02]			="";}else{rt[counter][ColSPAdd02]			=rset01.getString("SPAdd02");}						//仕入先住所2
					if(null==rset01.getString("SPAdd03")		){rt[counter][ColSPAdd03]			="";}else{rt[counter][ColSPAdd03]			=rset01.getString("SPAdd03");}						//仕入先住所3
					if(null==rset01.getString("SPTel")			){rt[counter][ColSPTel]			="";}else{rt[counter][ColSPTel]			=rset01.getString("SPTel");}						//仕入先電話
					if(null==rset01.getString("SPFax")			){rt[counter][ColSPFax]			="";}else{rt[counter][ColSPFax]			=rset01.getString("SPFax");}						//仕入先FAX
					if(null==rset01.getString("SPMail")			){rt[counter][ColSPMail]			="";}else{rt[counter][ColSPMail]			=rset01.getString("SPMail");}						//仕入先MAIL
					if(null==rset01.getString("Com01")			){rt[counter][ColCom01]			="";}else{rt[counter][ColCom01]			=rset01.getString("Com01");}						//コメント1
					if(null==rset01.getString("Com02")			){rt[counter][ColCom02]			="";}else{rt[counter][ColCom02]			=rset01.getString("Com02");}						//コメント2
					if(null==rset01.getString("Com03")			){rt[counter][ColCom03]			="";}else{rt[counter][ColCom03]			=rset01.getString("Com03");}						//コメント3
					if(null==rset01.getString("PTMSCDBMN")		){rt[counter][ColPTMSCDBMN]		="";}else{rt[counter][ColPTMSCDBMN]		=rset01.getString("PTMSCDBMN");}					//基幹Sysコード（部門）
					if(null==rset01.getString("PTMSCDNINUSHI")	){rt[counter][ColPTMSCDNINUSHI]	="";}else{rt[counter][ColPTMSCDNINUSHI]	=rset01.getString("PTMSCDNINUSHI");}				//基幹Sysコード（荷主）
					rt[counter][ColPaySite]=rset01.getInt("PaySite");		//支払いサイト（月数）
					rt[counter][ColPayDate]=rset01.getInt("PayDate");		//支払日（末日＝99）
					rt[counter][ColShimeDate]=rset01.getInt("ShimeDate");	//締め日（末日＝99）
					if(null==rset01.getTimestamp("EntryDate"	)){rt[counter][ColEntryDate]		="";}else{rt[counter][ColEntryDate]		=B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("EntryDate"))[1];}	//登録日
					if(null==rset01.getTimestamp("UpdateDate"	)){rt[counter][ColUpdateDate]		="";}else{rt[counter][ColUpdateDate]		=B00050ToolsDateTimeControl.dtmString2(rset01.getTimestamp("UpdateDate"))[1];}	//更新日
					if(null==rset01.getString("EntryUser")		){rt[counter][ColEntryUser]		="";}else{rt[counter][ColEntryUser]		=rset01.getString("EntryUser");}				//登録者
					if(null==rset01.getString("UpdateUser")		){rt[counter][ColUpdateUser]		="";}else{rt[counter][ColUpdateUser]		=rset01.getString("UpdateUser");}				//更新者
					if(null==rset01.getString("DECD")			){rt[counter][ColDECD]				="";}else{rt[counter][ColDECD]				=rset01.getString("DECD");}						//納品先コード
					if(null==rset01.getString("DepartmentCd")	){rt[counter][ColDepartmentCd]	="";}else{rt[counter][ColDepartmentCd]	=rset01.getString("DepartmentCd");}				//部署CD
					if(null==rset01.getString("DEName01")		){rt[counter][ColDEName01]			="";}else{rt[counter][ColDEName01]			=rset01.getString("DEName01");}					//納品先名1
					
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
	
	//仕入先コードを自動採番する
	public static String[] NewSpCdGet(int NeedCount) {
		//仕入先マスタ取得
		ArrayList<String> SearchClWh = new ArrayList<String>(); 			//担当倉庫
		ArrayList<String> SearchClCd = new ArrayList<String>();				//荷主CD
		ArrayList<String> SearchSPCd = new ArrayList<String>();				//仕入先コード
		ArrayList<String> SearchSPName = new ArrayList<String>();			//仕入先名
		ArrayList<String> SearchSPPost = new ArrayList<String>();			//仕入先郵便
		ArrayList<String> SearchSPAdd = new ArrayList<String>();			//仕入先住所
		ArrayList<String> SearchSPTel = new ArrayList<String>();			//仕入先電話
		ArrayList<String> SearchSPFax = new ArrayList<String>();			//仕入先FAX
		ArrayList<String> SearchSPMail = new ArrayList<String>();			//仕入先MAIL
		ArrayList<String> SearchCom = new ArrayList<String>();				//コメント
		ArrayList<String> SearchPTMSCDBMN = new ArrayList<String>();		//基幹Sysコード（部門）
		ArrayList<String> SearchPTMSCDNINUSHI = new ArrayList<String>();	//基幹Sysコード（荷主）
		ArrayList<Integer> SearchPaySiteStr = new ArrayList<Integer>();		//支払いサイト（月数）開始
		ArrayList<Integer> SearchPayDateStr = new ArrayList<Integer>();		//支払日（日＝99）開始
		ArrayList<Integer> SearchShimeDateStr = new ArrayList<Integer>();	//締め日（末日＝99）開始
		ArrayList<Integer> SearchPaySiteEnd = new ArrayList<Integer>();		//支払いサイト（月数）終了
		ArrayList<Integer> SearchPayDateEnd = new ArrayList<Integer>();		//支払日（日＝99）終了
		ArrayList<Integer> SearchShimeDateEnd = new ArrayList<Integer>();	//締め日（末日＝99）終了
		ArrayList<String> SearchDECD = new ArrayList<String>();				//納品先コード
		ArrayList<String> SearchDepartmentCd = new ArrayList<String>();		//部署CD
		boolean AllSearch = true;
		
		Object[][] SupplierRt = M00100SupplierRt.SupplierRt(
				SearchClWh,				//担当倉庫
				SearchClCd,				//荷主CD
				SearchSPCd,				//仕入先コード
				SearchSPName,			//仕入先名
				SearchSPPost,			//仕入先郵便
				SearchSPAdd,			//仕入先住所
				SearchSPTel,			//仕入先電話
				SearchSPFax,			//仕入先FAX
				SearchSPMail,			//仕入先MAIL
				SearchCom,				//コメント
				SearchPTMSCDBMN,		//基幹Sysコード（部門）
				SearchPTMSCDNINUSHI,	//基幹Sysコード（荷主）
				SearchPaySiteStr,		//支払いサイト（月数）開始
				SearchPayDateStr,		//支払日（日＝99）開始
				SearchShimeDateStr,		//締め日（末日＝99）開始
				SearchPaySiteEnd,		//支払いサイト（月数）終了
				SearchPayDateEnd,		//支払日（日＝99）終了
				SearchShimeDateEnd,		//締め日（末日＝99）終了
				SearchDECD,				//納品先コード
				SearchDepartmentCd,		//部署CD
				AllSearch);
		
		int SpNo = 0;
    	
    	for(int i=0;i<SupplierRt.length;i++) {
    		if("ATSP".equals((""+SupplierRt[i][M00100SupplierRt.ColSPCd]).substring(0,4))) {
    			String WST = B00020ToolsTextControl.num_only_String(""+SupplierRt[i][M00100SupplierRt.ColSPCd]);
				int wint = Integer.parseInt(WST);
				if(SpNo<wint) {
					SpNo=wint;
				}
    		}
    	}
    	
    	String[] rt = new String[NeedCount];
    	for(int i=0;i<NeedCount;i++) {
    		SpNo = SpNo+1;
    		if(9999999<SpNo) {
    			rt[i] = "ATSP"+SpNo;
    		}else {
		    	rt[i] = "0000000"+SpNo;
		    	rt[i] = "ATSP"+rt[i].substring(rt[i].length()-7,rt[i].length());
    		}
    	}
    	
    	return rt;
	}
	
}