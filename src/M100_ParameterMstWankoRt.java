import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class M100_ParameterMstWankoRt{
	/*
	コピペ用
	ArrayList<String> SearchClWh 			= new ArrayList<String>();
	ArrayList<String> SearchClCd 			= new ArrayList<String>();
	ArrayList<String> SearchParaCd 			= new ArrayList<String>();	
	ArrayList<Integer> SearchParaCdSeqStr	= new ArrayList<Integer>();
	ArrayList<Integer> SearchParaCdSeqEnd	= new ArrayList<Integer>();
	ArrayList<String> SearchParaName 		= new ArrayList<String>();
	ArrayList<String> SearchParaTxt01		= new ArrayList<String>();
	ArrayList<String> SearchParaTxt02		= new ArrayList<String>();
	ArrayList<String> SearchParaTxt03		= new ArrayList<String>();
	ArrayList<String> SearchParaTxt04		= new ArrayList<String>();
	ArrayList<String> SearchParaTxt05		= new ArrayList<String>();
	ArrayList<String> SearchParaTxt06		= new ArrayList<String>();
	ArrayList<String> SearchParaTxt07		= new ArrayList<String>();
	ArrayList<String> SearchParaTxt08		= new ArrayList<String>();
	ArrayList<String> SearchParaTxt09		= new ArrayList<String>();
	ArrayList<String> SearchParaTxt10		= new ArrayList<String>();
	ArrayList<Integer> SearchParaInt01Str	= new ArrayList<Integer>();
	ArrayList<Integer> SearchParaInt02Str	= new ArrayList<Integer>();
	ArrayList<Integer> SearchParaInt03Str	= new ArrayList<Integer>();
	ArrayList<Integer> SearchParaInt04Str	= new ArrayList<Integer>();
	ArrayList<Integer> SearchParaInt05Str	= new ArrayList<Integer>();
	ArrayList<Integer> SearchParaInt06Str	= new ArrayList<Integer>();
	ArrayList<Integer> SearchParaInt07Str	= new ArrayList<Integer>();
	ArrayList<Integer> SearchParaInt08Str	= new ArrayList<Integer>();
	ArrayList<Integer> SearchParaInt09Str	= new ArrayList<Integer>();
	ArrayList<Integer> SearchParaInt10Str	= new ArrayList<Integer>();
	ArrayList<Integer> SearchParaInt01End	= new ArrayList<Integer>();
	ArrayList<Integer> SearchParaInt02End	= new ArrayList<Integer>();
	ArrayList<Integer> SearchParaInt03End	= new ArrayList<Integer>();
	ArrayList<Integer> SearchParaInt04End	= new ArrayList<Integer>();
	ArrayList<Integer> SearchParaInt05End	= new ArrayList<Integer>();
	ArrayList<Integer> SearchParaInt06End	= new ArrayList<Integer>();
	ArrayList<Integer> SearchParaInt07End	= new ArrayList<Integer>();
	ArrayList<Integer> SearchParaInt08End	= new ArrayList<Integer>();
	ArrayList<Integer> SearchParaInt09End	= new ArrayList<Integer>();
	ArrayList<Integer> SearchParaInt10End	= new ArrayList<Integer>();
	ArrayList<String> SearchParaTxtAll = new ArrayList<String>();
	Boolean AllSearch = false;
	
	Object[][] ParameterMstWankoRt = M100_ParameterMstWankoRt.ParameterMstWankoRt(
			SearchClWh,SearchClCd,
			SearchParaCd,SearchParaCdSeqStr,SearchParaCdSeqEnd,SearchParaName,
			SearchParaTxt01,SearchParaTxt02,SearchParaTxt03,SearchParaTxt04,SearchParaTxt05,
			SearchParaTxt06,SearchParaTxt07,SearchParaTxt08,SearchParaTxt09,SearchParaTxt10,
			SearchParaInt01Str,SearchParaInt02Str,SearchParaInt03Str,SearchParaInt04Str,SearchParaInt05Str,
			SearchParaInt06Str,SearchParaInt07Str,SearchParaInt08Str,SearchParaInt09Str,SearchParaInt10Str,
			SearchParaInt01End,SearchParaInt02End,SearchParaInt03End,SearchParaInt04End,SearchParaInt05End,
			SearchParaInt06End,SearchParaInt07End,SearchParaInt08End,SearchParaInt09End,SearchParaInt10End,
			SearchParaTxtAll,
			AllSearch);
			
	String GetClWh			=(String)ParameterMstWankoRt[i][M100_ParameterMstWankoRt.ColClWh];			//担当倉庫コード
	String GetWHName		=(String)ParameterMstWankoRt[i][M100_ParameterMstWankoRt.ColWHName];		//担当倉庫名
	String GetClCd			=(String)ParameterMstWankoRt[i][M100_ParameterMstWankoRt.ColClCd];			//荷主コード
	String GetCLName01		=(String)ParameterMstWankoRt[i][M100_ParameterMstWankoRt.ColCLName01];		//荷主名
	String GetParaCd		=(String)ParameterMstWankoRt[i][M100_ParameterMstWankoRt.ColParaCd];		//パラメータコード
	int GetParaCdSeq		=(int)ParameterMstWankoRt[i][M100_ParameterMstWankoRt.ColParaCdSeq];		//ナンバリング
	String GetParaName		=(String)ParameterMstWankoRt[i][M100_ParameterMstWankoRt.ColParaName];		//パラメータ名
	String GetParaTxt01		=(String)ParameterMstWankoRt[i][M100_ParameterMstWankoRt.ColParaTxt01];		//パラメータテキスト項目01
	String GetParaTxt02		=(String)ParameterMstWankoRt[i][M100_ParameterMstWankoRt.ColParaTxt02];		//パラメータテキスト項目02
	String GetParaTxt03		=(String)ParameterMstWankoRt[i][M100_ParameterMstWankoRt.ColParaTxt03];		//パラメータテキスト項目03
	String GetParaTxt04		=(String)ParameterMstWankoRt[i][M100_ParameterMstWankoRt.ColParaTxt04];		//パラメータテキスト項目04
	String GetParaTxt05		=(String)ParameterMstWankoRt[i][M100_ParameterMstWankoRt.ColParaTxt05];		//パラメータテキスト項目05
	String GetParaTxt06		=(String)ParameterMstWankoRt[i][M100_ParameterMstWankoRt.ColParaTxt06];		//パラメータテキスト項目06
	String GetParaTxt07		=(String)ParameterMstWankoRt[i][M100_ParameterMstWankoRt.ColParaTxt07];		//パラメータテキスト項目07
	String GetParaTxt08		=(String)ParameterMstWankoRt[i][M100_ParameterMstWankoRt.ColParaTxt08];		//パラメータテキスト項目08
	String GetParaTxt09		=(String)ParameterMstWankoRt[i][M100_ParameterMstWankoRt.ColParaTxt09];		//パラメータテキスト項目09
	String GetParaTxt10		=(String)ParameterMstWankoRt[i][M100_ParameterMstWankoRt.ColParaTxt10];		//パラメータテキスト項目10
	int GetParaInt01		=(int)ParameterMstWankoRt[i][M100_ParameterMstWankoRt.ColParaInt01];		//パラメータ数値項目01
	int GetParaInt02		=(int)ParameterMstWankoRt[i][M100_ParameterMstWankoRt.ColParaInt02];		//パラメータ数値項目02
	int GetParaInt03		=(int)ParameterMstWankoRt[i][M100_ParameterMstWankoRt.ColParaInt03];		//パラメータ数値項目03
	int GetParaInt04		=(int)ParameterMstWankoRt[i][M100_ParameterMstWankoRt.ColParaInt04];		//パラメータ数値項目04
	int GetParaInt05		=(int)ParameterMstWankoRt[i][M100_ParameterMstWankoRt.ColParaInt05];		//パラメータ数値項目05
	int GetParaInt06		=(int)ParameterMstWankoRt[i][M100_ParameterMstWankoRt.ColParaInt06];		//パラメータ数値項目06
	int GetParaInt07		=(int)ParameterMstWankoRt[i][M100_ParameterMstWankoRt.ColParaInt07];		//パラメータ数値項目07
	int GetParaInt08		=(int)ParameterMstWankoRt[i][M100_ParameterMstWankoRt.ColParaInt08];		//パラメータ数値項目08
	int GetParaInt09		=(int)ParameterMstWankoRt[i][M100_ParameterMstWankoRt.ColParaInt09];		//パラメータ数値項目09
	int GetParaInt10		=(int)ParameterMstWankoRt[i][M100_ParameterMstWankoRt.ColParaInt10];		//パラメータ数値項目10
	String GetEntryDate		=(String)ParameterMstWankoRt[i][M100_ParameterMstWankoRt.ColEntryDate];		//登録日
	String GetUpdateDate	=(String)ParameterMstWankoRt[i][M100_ParameterMstWankoRt.ColUpdateDate];	//更新日
	String GetEntryUser		=(String)ParameterMstWankoRt[i][M100_ParameterMstWankoRt.ColEntryUser];		//登録者
	String GetUpdateUser	=(String)ParameterMstWankoRt[i][M100_ParameterMstWankoRt.ColUpdateUser];	//更新者
	
	*/
	
	//戻り値カラム
	static final  int ColClWh			= (int) 0;	//担当倉庫コード
	static final  int ColWHName		= (int) 1;	//担当倉庫名
	static final  int ColClCd			= (int) 2;	//荷主コード
	static final  int ColCLName01		= (int) 3;	//荷主名
	static final  int ColParaCd		= (int) 4;	//パラメータコード
	static final  int ColParaCdSeq	= (int) 5;	//ナンバリング
	static final  int ColParaName		= (int) 6;	//パラメータ名
	static final  int ColParaTxt01	= (int) 7;	//パラメータテキスト項目01
	static final  int ColParaTxt02	= (int) 8;	//パラメータテキスト項目02
	static final  int ColParaTxt03	= (int) 9;	//パラメータテキスト項目03
	static final  int ColParaTxt04	= (int)10;	//パラメータテキスト項目04
	static final  int ColParaTxt05	= (int)11;	//パラメータテキスト項目05
	static final  int ColParaTxt06	= (int)12;	//パラメータテキスト項目06
	static final  int ColParaTxt07	= (int)13;	//パラメータテキスト項目07
	static final  int ColParaTxt08	= (int)14;	//パラメータテキスト項目08
	static final  int ColParaTxt09	= (int)15;	//パラメータテキスト項目09
	static final  int ColParaTxt10	= (int)16;	//パラメータテキスト項目10
	static final  int ColParaInt01	= (int)17;	//パラメータ数値項目01
	static final  int ColParaInt02	= (int)18;	//パラメータ数値項目02
	static final  int ColParaInt03	= (int)19;	//パラメータ数値項目03
	static final  int ColParaInt04	= (int)20;	//パラメータ数値項目04
	static final  int ColParaInt05	= (int)21;	//パラメータ数値項目05
	static final  int ColParaInt06	= (int)22;	//パラメータ数値項目06
	static final  int ColParaInt07	= (int)23;	//パラメータ数値項目07
	static final  int ColParaInt08	= (int)24;	//パラメータ数値項目08
	static final  int ColParaInt09	= (int)25;	//パラメータ数値項目09
	static final  int ColParaInt10	= (int)26;	//パラメータ数値項目10
	static final  int ColEntryDate	= (int)27;	//登録日
	static final  int ColUpdateDate	= (int)28;	//更新日
	static final  int ColEntryUser	= (int)29;	//登録者
	static final  int ColUpdateUser	= (int)30;	//更新者
	
	//検索値カラム
	static final  int ColSearchParaCd			= (int) 0;	//パラメータコード
	static final  int ColSearchParaCdSeqStr	= (int) 1;	//ナンバリング最小
	static final  int ColSearchParaCdSeqEnd	= (int) 2;	//ナンバリング最大
	static final  int ColSearchParaName		= (int) 3;	//パラメータ名
	static final  int ColSearchParaTxt01		= (int) 4;	//パラメータテキスト項目01
	static final  int ColSearchParaTxt02		= (int) 5;	//パラメータテキスト項目02
	static final  int ColSearchParaTxt03		= (int) 6;	//パラメータテキスト項目03
	static final  int ColSearchParaTxt04		= (int) 7;	//パラメータテキスト項目04
	static final  int ColSearchParaTxt05		= (int) 8;	//パラメータテキスト項目05
	static final  int ColSearchParaTxt06		= (int) 9;	//パラメータテキスト項目06
	static final  int ColSearchParaTxt07		= (int)10;	//パラメータテキスト項目07
	static final  int ColSearchParaTxt08		= (int)11;	//パラメータテキスト項目08
	static final  int ColSearchParaTxt09		= (int)12;	//パラメータテキスト項目09
	static final  int ColSearchParaTxt10		= (int)13;	//パラメータテキスト項目10
	static final  int ColSearchParaInt01Str	= (int)14;	//パラメータ数値項目01最小
	static final  int ColSearchParaInt02Str	= (int)15;	//パラメータ数値項目02最小
	static final  int ColSearchParaInt03Str	= (int)16;	//パラメータ数値項目03最小
	static final  int ColSearchParaInt04Str	= (int)17;	//パラメータ数値項目04最小
	static final  int ColSearchParaInt05Str	= (int)18;	//パラメータ数値項目05最小
	static final  int ColSearchParaInt06Str	= (int)19;	//パラメータ数値項目06最小
	static final  int ColSearchParaInt07Str	= (int)20;	//パラメータ数値項目07最小
	static final  int ColSearchParaInt08Str	= (int)21;	//パラメータ数値項目08最小
	static final  int ColSearchParaInt09Str	= (int)22;	//パラメータ数値項目09最小
	static final  int ColSearchParaInt10Str	= (int)23;	//パラメータ数値項目10最小
	static final  int ColSearchParaInt01End	= (int)24;	//パラメータ数値項目01最大
	static final  int ColSearchParaInt02End	= (int)25;	//パラメータ数値項目02最大
	static final  int ColSearchParaInt03End	= (int)26;	//パラメータ数値項目03最大
	static final  int ColSearchParaInt04End	= (int)27;	//パラメータ数値項目04最大
	static final  int ColSearchParaInt05End	= (int)28;	//パラメータ数値項目05最大
	static final  int ColSearchParaInt06End	= (int)29;	//パラメータ数値項目06最大
	static final  int ColSearchParaInt07End	= (int)30;	//パラメータ数値項目07最大
	static final  int ColSearchParaInt08End	= (int)31;	//パラメータ数値項目08最大
	static final  int ColSearchParaInt09End	= (int)32;	//パラメータ数値項目09最大
	static final  int ColSearchParaInt10End	= (int)33;	//パラメータ数値項目10最大
	static final  int ColSearchParaTxtAll	= (int)34;	//パラメータテキスト項目全件
	static final  int ColSearchClWh			= (int)35;	//倉庫CD
	static final  int ColSearchClCd			= (int)36;	//荷主CD
	
	public static Object[][] RtParameterMstWankoRt(){
		Object[][] RtParameterMstWankoRt= {
					 {"ClWh"		,ColClWh			,"String"	,"担当倉庫CD"			,"Key"}
					,{"WHName"		,ColWHName			,"String"	,"担当倉庫名"			,"Key"}
					,{"ClCd"		,ColClCd			,"String"	,"荷主コード"			,"Key"}
					,{"CLName01"	,ColCLName01		,"String"	,"荷主名"				,""}
					,{"ParaCd"		,ColParaCd			,"String"	,"パラメータCD"			,""}
					,{"ParaCdSeq"	,ColParaCdSeq		,"int"		,"Seq"					,""}
					,{"ParaName"	,ColParaName		,"String"	,"パラメータ名"			,""}
					,{"ParaTxt01"	,ColParaTxt01		,"String"	,"テキスト項目01"		,""}
					,{"ParaTxt02"	,ColParaTxt02		,"String"	,"テキスト項目02"		,""}
					,{"ParaTxt03"	,ColParaTxt03		,"String"	,"テキスト項目03"		,""}
					,{"ParaTxt04"	,ColParaTxt04		,"String"	,"テキスト項目04"		,""}
					,{"ParaTxt05"	,ColParaTxt05		,"String"	,"テキスト項目05"		,""}
					,{"ParaTxt06"	,ColParaTxt06		,"String"	,"テキスト項目06"		,""}
					,{"ParaTxt07"	,ColParaTxt07		,"String"	,"テキスト項目07"		,""}
					,{"ParaTxt08"	,ColParaTxt08		,"String"	,"テキスト項目08"		,""}
					,{"ParaTxt09"	,ColParaTxt09		,"String"	,"テキスト項目09"		,""}
					,{"ParaTxt10"	,ColParaTxt10		,"String"	,"テキスト項目10"		,""}
					,{"ParaInt01"	,ColParaInt01		,"int"		,"数値項目01"			,""}
					,{"ParaInt02"	,ColParaInt02		,"int"		,"数値項目02"			,""}
					,{"ParaInt03"	,ColParaInt03		,"int"		,"数値項目03"			,""}
					,{"ParaInt04"	,ColParaInt04		,"int"		,"数値項目04"			,""}
					,{"ParaInt05"	,ColParaInt05		,"int"		,"数値項目05"			,""}
					,{"ParaInt06"	,ColParaInt06		,"int"		,"数値項目06"			,""}
					,{"ParaInt07"	,ColParaInt07		,"int"		,"数値項目07"			,""}
					,{"ParaInt08"	,ColParaInt08		,"int"		,"数値項目08"			,""}
					,{"ParaInt09"	,ColParaInt09		,"int"		,"数値項目09"			,""}
					,{"ParaInt10"	,ColParaInt10		,"int"		,"数値項目10"			,""}
					,{"EntryDate"	,ColEntryDate		,"DateTime"	,"登録日"				,""}
					,{"UpdateDate"	,ColUpdateDate	,"DateTime"	,"更新日"				,""}
					,{"EntryUser"	,ColEntryUser		,"String"	,"登録者"				,""}
					,{"UpdateUser"	,ColUpdateUser	,"String"	,"更新者"				,""}
					};
		
		RtParameterMstWankoRt = B100_LanguageControl.RtControl(RtParameterMstWankoRt);
		
		return RtParameterMstWankoRt;
	}
	
	public static Object[][] ParameterMstWankoRtFromParaCd(String ParaCd,int Seq){
		//現在ログイン中の荷主のパラメータ情報をパラメータコード・シーケンシャル番号指定で取得する
		
		if(null==ParaCd) {ParaCd="";}
		
		ArrayList<String> SearchClWh 			= new ArrayList<String>();
		ArrayList<String> SearchClCd 			= new ArrayList<String>();
		ArrayList<String> SearchParaCd 			= new ArrayList<String>();	
		ArrayList<Integer> SearchParaCdSeqStr	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaCdSeqEnd	= new ArrayList<Integer>();
		ArrayList<String> SearchParaName 		= new ArrayList<String>();
		ArrayList<String> SearchParaTxt01		= new ArrayList<String>();
		ArrayList<String> SearchParaTxt02		= new ArrayList<String>();
		ArrayList<String> SearchParaTxt03		= new ArrayList<String>();
		ArrayList<String> SearchParaTxt04		= new ArrayList<String>();
		ArrayList<String> SearchParaTxt05		= new ArrayList<String>();
		ArrayList<String> SearchParaTxt06		= new ArrayList<String>();
		ArrayList<String> SearchParaTxt07		= new ArrayList<String>();
		ArrayList<String> SearchParaTxt08		= new ArrayList<String>();
		ArrayList<String> SearchParaTxt09		= new ArrayList<String>();
		ArrayList<String> SearchParaTxt10		= new ArrayList<String>();
		ArrayList<Integer> SearchParaInt01Str	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt02Str	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt03Str	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt04Str	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt05Str	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt06Str	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt07Str	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt08Str	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt09Str	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt10Str	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt01End	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt02End	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt03End	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt04End	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt05End	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt06End	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt07End	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt08End	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt09End	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt10End	= new ArrayList<Integer>();
		ArrayList<String> SearchParaTxtAll = new ArrayList<String>();
		Boolean AllSearch = false;
		
		SearchClWh.add(A00000_Main.ClWh);
		SearchClCd.add(A00000_Main.ClCd);
		SearchParaCd.add(ParaCd);
		SearchParaCdSeqStr.add(Seq);
		SearchParaCdSeqEnd.add(Seq);
		
		Object[][] ParameterMstWankoRt = M100_ParameterMstWankoRt.ParameterMstWankoRt(
				SearchClWh,SearchClCd,
				SearchParaCd,SearchParaCdSeqStr,SearchParaCdSeqEnd,SearchParaName,
				SearchParaTxt01,SearchParaTxt02,SearchParaTxt03,SearchParaTxt04,SearchParaTxt05,
				SearchParaTxt06,SearchParaTxt07,SearchParaTxt08,SearchParaTxt09,SearchParaTxt10,
				SearchParaInt01Str,SearchParaInt02Str,SearchParaInt03Str,SearchParaInt04Str,SearchParaInt05Str,
				SearchParaInt06Str,SearchParaInt07Str,SearchParaInt08Str,SearchParaInt09Str,SearchParaInt10Str,
				SearchParaInt01End,SearchParaInt02End,SearchParaInt03End,SearchParaInt04End,SearchParaInt05End,
				SearchParaInt06End,SearchParaInt07End,SearchParaInt08End,SearchParaInt09End,SearchParaInt10End,
				SearchParaTxtAll,
				AllSearch);
		
		return ParameterMstWankoRt;
	}
	
	public static Object[][] ParameterMstWankoRtFromParaCdAndSeq(String SearchTgtParaCd,int SeqStr,int SeqEnd,Boolean SeqAllTgt){
		//現在ログイン中の荷主のパラメータ情報をパラメータコード・シーケンシャル番号範囲指定で取得する　Boolean SeqAllTgt = true　シーケンシャル番号気にせず全部取得
		if(null==SearchTgtParaCd) {SearchTgtParaCd="";}
		
		ArrayList<String> SearchClWh 			= new ArrayList<String>();
		ArrayList<String> SearchClCd 			= new ArrayList<String>();
		ArrayList<String> SearchParaCd 			= new ArrayList<String>();	
		ArrayList<Integer> SearchParaCdSeqStr	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaCdSeqEnd	= new ArrayList<Integer>();
		ArrayList<String> SearchParaName 		= new ArrayList<String>();
		ArrayList<String> SearchParaTxt01		= new ArrayList<String>();
		ArrayList<String> SearchParaTxt02		= new ArrayList<String>();
		ArrayList<String> SearchParaTxt03		= new ArrayList<String>();
		ArrayList<String> SearchParaTxt04		= new ArrayList<String>();
		ArrayList<String> SearchParaTxt05		= new ArrayList<String>();
		ArrayList<String> SearchParaTxt06		= new ArrayList<String>();
		ArrayList<String> SearchParaTxt07		= new ArrayList<String>();
		ArrayList<String> SearchParaTxt08		= new ArrayList<String>();
		ArrayList<String> SearchParaTxt09		= new ArrayList<String>();
		ArrayList<String> SearchParaTxt10		= new ArrayList<String>();
		ArrayList<Integer> SearchParaInt01Str	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt02Str	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt03Str	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt04Str	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt05Str	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt06Str	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt07Str	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt08Str	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt09Str	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt10Str	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt01End	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt02End	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt03End	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt04End	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt05End	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt06End	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt07End	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt08End	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt09End	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaInt10End	= new ArrayList<Integer>();
		ArrayList<String> SearchParaTxtAll = new ArrayList<String>();
		Boolean AllSearch = false;
		
		SearchClWh.add(A00000_Main.ClWh);
		SearchClCd.add(A00000_Main.ClCd);
		SearchParaCd.add(SearchTgtParaCd);
		if(SeqAllTgt) {
			
		}else {
			SearchParaCdSeqStr.add(SeqStr);
			SearchParaCdSeqEnd.add(SeqEnd);
		}
		Object[][] ParameterMstWankoRt = M100_ParameterMstWankoRt.ParameterMstWankoRt(
				SearchClWh,SearchClCd,
				SearchParaCd,SearchParaCdSeqStr,SearchParaCdSeqEnd,SearchParaName,
				SearchParaTxt01,SearchParaTxt02,SearchParaTxt03,SearchParaTxt04,SearchParaTxt05,
				SearchParaTxt06,SearchParaTxt07,SearchParaTxt08,SearchParaTxt09,SearchParaTxt10,
				SearchParaInt01Str,SearchParaInt02Str,SearchParaInt03Str,SearchParaInt04Str,SearchParaInt05Str,
				SearchParaInt06Str,SearchParaInt07Str,SearchParaInt08Str,SearchParaInt09Str,SearchParaInt10Str,
				SearchParaInt01End,SearchParaInt02End,SearchParaInt03End,SearchParaInt04End,SearchParaInt05End,
				SearchParaInt06End,SearchParaInt07End,SearchParaInt08End,SearchParaInt09End,SearchParaInt10End,
				SearchParaTxtAll,
				AllSearch);
		
		return ParameterMstWankoRt;
	}

	public static Object[][] DefinitionRt(){
		Object[][] Definition = {
					 {"String"		,null	,"Exact"		,ColSearchParaCd			,""											,"パラメータコード"		,""}
					,{"Integer"		,null	,"RangeMin"		,ColSearchParaCdSeqStr	,""											,"ナンバリング"			,"最小"}
					,{"Integer"		,null	,"RangeMax"		,ColSearchParaCdSeqEnd	,""											,"ナンバリング"			,"最大"}
					,{"String"		,null	,"Partial"		,ColSearchParaName		,""											,"パラメータ名"			,""}
					,{"String"		,null	,"Partial"		,ColSearchParaTxt01		,""											,"テキスト項目01"		,""}
					,{"String"		,null	,"Partial"		,ColSearchParaTxt02		,""											,"テキスト項目02"		,""}
					,{"String"		,null	,"Partial"		,ColSearchParaTxt03		,""											,"テキスト項目03"		,""}
					,{"String"		,null	,"Partial"		,ColSearchParaTxt04		,""											,"テキスト項目04"		,""}
					,{"String"		,null	,"Partial"		,ColSearchParaTxt05		,""											,"テキスト項目05"		,""}
					,{"String"		,null	,"Partial"		,ColSearchParaTxt06		,""											,"テキスト項目06"		,""}
					,{"String"		,null	,"Partial"		,ColSearchParaTxt07		,""											,"テキスト項目07"		,""}
					,{"String"		,null	,"Partial"		,ColSearchParaTxt08		,""											,"テキスト項目08"		,""}
					,{"String"		,null	,"Partial"		,ColSearchParaTxt09		,""											,"テキスト項目09"		,""}
					,{"String"		,null	,"Partial"		,ColSearchParaTxt10		,""											,"テキスト項目10"		,""}
					,{"Integer"		,null	,"RangeMin"		,ColSearchParaInt01Str	,""											,"数値項目01"			,"最小"}
					,{"Integer"		,null	,"RangeMin"		,ColSearchParaInt02Str	,""											,"数値項目02"			,"最小"}
					,{"Integer"		,null	,"RangeMin"		,ColSearchParaInt03Str	,""											,"数値項目03"			,"最小"}
					,{"Integer"		,null	,"RangeMin"		,ColSearchParaInt04Str	,""											,"数値項目04"			,"最小"}
					,{"Integer"		,null	,"RangeMin"		,ColSearchParaInt05Str	,""											,"数値項目05"			,"最小"}
					,{"Integer"		,null	,"RangeMin"		,ColSearchParaInt06Str	,""											,"数値項目06"			,"最小"}
					,{"Integer"		,null	,"RangeMin"		,ColSearchParaInt07Str	,""											,"数値項目07"			,"最小"}
					,{"Integer"		,null	,"RangeMin"		,ColSearchParaInt08Str	,""											,"数値項目08"			,"最小"}
					,{"Integer"		,null	,"RangeMin"		,ColSearchParaInt09Str	,""											,"数値項目09"			,"最小"}
					,{"Integer"		,null	,"RangeMin"		,ColSearchParaInt10Str	,""											,"数値項目10"			,"最小"}
					,{"Integer"		,null	,"RangeMax"		,ColSearchParaInt01End	,""											,"数値項目01"			,"最大"}
					,{"Integer"		,null	,"RangeMax"		,ColSearchParaInt02End	,""											,"数値項目02"			,"最大"}
					,{"Integer"		,null	,"RangeMax"		,ColSearchParaInt03End	,""											,"数値項目03"			,"最大"}
					,{"Integer"		,null	,"RangeMax"		,ColSearchParaInt04End	,""											,"数値項目04"			,"最大"}
					,{"Integer"		,null	,"RangeMax"		,ColSearchParaInt05End	,""											,"数値項目05"			,"最大"}
					,{"Integer"		,null	,"RangeMax"		,ColSearchParaInt06End	,""											,"数値項目06"			,"最大"}
					,{"Integer"		,null	,"RangeMax"		,ColSearchParaInt07End	,""											,"数値項目07"			,"最大"}
					,{"Integer"		,null	,"RangeMax"		,ColSearchParaInt08End	,""											,"数値項目08"			,"最大"}
					,{"Integer"		,null	,"RangeMax"		,ColSearchParaInt09End	,""											,"数値項目09"			,"最大"}
					,{"Integer"		,null	,"RangeMax"		,ColSearchParaInt10End	,""											,"数値項目10"			,"最大"}
					,{"String"		,null	,"Partial"		,ColSearchParaTxtAll		,""											,"テキスト項目全件"		,""}
					,{"String"		,null	,"Exact"		,ColSearchClWh			,B100_DefaultVariable.SearchWhList		,"倉庫CD"				,""}
					,{"String"		,null	,"Exact"		,ColSearchClCd			,B100_DefaultVariable.SearchClList		,"荷主CD"				,""}
					};		
		
		Definition = B100_LanguageControl.DefinitionControl(Definition);
		
		return Definition;
	}
	
	public static Object[][] ParameterMstWankoRt(
			ArrayList<String> SearchClWh,ArrayList<String> SearchClCd,
			ArrayList<String> SearchParaCd,	ArrayList<Integer> SearchParaCdSeqStr,ArrayList<Integer> SearchParaCdSeqEnd,ArrayList<String> SearchParaName,
			ArrayList<String> SearchParaTxt01,ArrayList<String> SearchParaTxt02,ArrayList<String> SearchParaTxt03,ArrayList<String> SearchParaTxt04,ArrayList<String> SearchParaTxt05,
			ArrayList<String> SearchParaTxt06,ArrayList<String> SearchParaTxt07,ArrayList<String> SearchParaTxt08,ArrayList<String> SearchParaTxt09,ArrayList<String> SearchParaTxt10,
			ArrayList<Integer> SearchParaInt01Str,ArrayList<Integer> SearchParaInt02Str,ArrayList<Integer> SearchParaInt03Str,ArrayList<Integer> SearchParaInt04Str,ArrayList<Integer> SearchParaInt05Str,
			ArrayList<Integer> SearchParaInt06Str,ArrayList<Integer> SearchParaInt07Str,ArrayList<Integer> SearchParaInt08Str,ArrayList<Integer> SearchParaInt09Str,ArrayList<Integer> SearchParaInt10Str,
			ArrayList<Integer> SearchParaInt01End,ArrayList<Integer> SearchParaInt02End,ArrayList<Integer> SearchParaInt03End,ArrayList<Integer> SearchParaInt04End,ArrayList<Integer> SearchParaInt05End,
			ArrayList<Integer> SearchParaInt06End,ArrayList<Integer> SearchParaInt07End,ArrayList<Integer> SearchParaInt08End,ArrayList<Integer> SearchParaInt09End,ArrayList<Integer> SearchParaInt10End,
			ArrayList<String> SearchParaTxtAll,
			Boolean AllSearch){
		
		Object[][] Definition = DefinitionRt();

		for(int i=0;i<Definition.length;i++) {
			switch((int)Definition[i][3]) {
				case ColSearchParaCd:	
					Definition[i][1]	= SearchParaCd;
					break;
				case ColSearchParaCdSeqStr:	
					Definition[i][1]	= SearchParaCdSeqStr;
					break;
				case ColSearchParaCdSeqEnd:	
					Definition[i][1]	= SearchParaCdSeqEnd;
					break;
				case ColSearchParaName:	
					Definition[i][1]	= SearchParaName;
					break;
				case ColSearchParaTxt01:	
					Definition[i][1]	= SearchParaTxt01;
					break;
				case ColSearchParaTxt02:	
					Definition[i][1]	= SearchParaTxt02;
					break;
				case ColSearchParaTxt03:	
					Definition[i][1]	= SearchParaTxt03;
					break;
				case ColSearchParaTxt04:	
					Definition[i][1]	= SearchParaTxt04;
					break;
				case ColSearchParaTxt05:	
					Definition[i][1]	= SearchParaTxt05;
					break;
				case ColSearchParaTxt06:	
					Definition[i][1]	= SearchParaTxt06;
					break;
				case ColSearchParaTxt07:	
					Definition[i][1]	= SearchParaTxt07;
					break;
				case ColSearchParaTxt08:	
					Definition[i][1]	= SearchParaTxt08;
					break;
				case ColSearchParaTxt09:	
					Definition[i][1]	= SearchParaTxt09;
					break;
				case ColSearchParaTxt10:	
					Definition[i][1]	= SearchParaTxt10;
					break;
				case ColSearchParaInt01Str:	
					Definition[i][1]	= SearchParaInt01Str;
					break;
				case ColSearchParaInt02Str:	
					Definition[i][1]	= SearchParaInt02Str;
					break;
				case ColSearchParaInt03Str:	
					Definition[i][1]	= SearchParaInt03Str;
					break;
				case ColSearchParaInt04Str:	
					Definition[i][1]	= SearchParaInt04Str;
					break;
				case ColSearchParaInt05Str:	
					Definition[i][1]	= SearchParaInt05Str;
					break;
				case ColSearchParaInt06Str:	
					Definition[i][1]	= SearchParaInt06Str;
					break;
				case ColSearchParaInt07Str:	
					Definition[i][1]	= SearchParaInt07Str;
					break;
				case ColSearchParaInt08Str:	
					Definition[i][1]	= SearchParaInt08Str;
					break;
				case ColSearchParaInt09Str:	
					Definition[i][1]	= SearchParaInt09Str;
					break;
				case ColSearchParaInt10Str:	
					Definition[i][1]	= SearchParaInt10Str;
					break;
				case ColSearchParaInt01End:	
					Definition[i][1]	= SearchParaInt01End;
					break;
				case ColSearchParaInt02End:	
					Definition[i][1]	= SearchParaInt02End;
					break;
				case ColSearchParaInt03End:	
					Definition[i][1]	= SearchParaInt03End;
					break;
				case ColSearchParaInt04End:	
					Definition[i][1]	= SearchParaInt04End;
					break;
				case ColSearchParaInt05End:	
					Definition[i][1]	= SearchParaInt05End;
					break;
				case ColSearchParaInt06End:	
					Definition[i][1]	= SearchParaInt06End;
					break;
				case ColSearchParaInt07End:	
					Definition[i][1]	= SearchParaInt07End;
					break;
				case ColSearchParaInt08End:	
					Definition[i][1]	= SearchParaInt08End;
					break;
				case ColSearchParaInt09End:	
					Definition[i][1]	= SearchParaInt09End;
					break;
				case ColSearchParaInt10End:	
					Definition[i][1]	= SearchParaInt10End;
					break;
				case ColSearchParaTxtAll:	
					Definition[i][1]	= SearchParaTxtAll;
					break;
				case ColSearchClWh:	
					Definition[i][1]	= SearchClWh;
					break;
				case ColSearchClCd:	
					Definition[i][1]	= SearchClCd;
					break;
				default:
					break;
			}
		}
		
		
		/*
		日付系検索最小は念のため00:00:00扱い
		日付系検索項目最大は一日進めて00:00:00扱い
		検索条件の重複除去
		*/
		Definition	= B100_ArraySearchControl.SearchDefinitionControl(Definition);
		
		for(int i=0;i<Definition.length;i++) {
			switch((int)Definition[i][3]) {
				case ColSearchParaCd:	
					SearchParaCd			= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchParaCdSeqStr:	
					SearchParaCdSeqStr		= (ArrayList<Integer>)Definition[i][1];
					break;
				case ColSearchParaCdSeqEnd:	
					SearchParaCdSeqEnd		= (ArrayList<Integer>)Definition[i][1];
					break;
				case ColSearchParaName:	
					SearchParaName			= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchParaTxt01:	
					SearchParaTxt01			= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchParaTxt02:	
					SearchParaTxt02			= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchParaTxt03:	
					SearchParaTxt03			= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchParaTxt04:	
					SearchParaTxt04			= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchParaTxt05:	
					SearchParaTxt05			= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchParaTxt06:	
					SearchParaTxt06			= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchParaTxt07:	
					SearchParaTxt07			= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchParaTxt08:	
					SearchParaTxt08			= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchParaTxt09:	
					SearchParaTxt09			= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchParaTxt10:	
					SearchParaTxt10			= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchParaInt01Str:	
					SearchParaInt01Str		= (ArrayList<Integer>)Definition[i][1];
					break;
				case ColSearchParaInt02Str:	
					SearchParaInt02Str		= (ArrayList<Integer>)Definition[i][1];
					break;
				case ColSearchParaInt03Str:	
					SearchParaInt03Str		= (ArrayList<Integer>)Definition[i][1];
					break;
				case ColSearchParaInt04Str:	
					SearchParaInt04Str		= (ArrayList<Integer>)Definition[i][1];
					break;
				case ColSearchParaInt05Str:	
					SearchParaInt05Str		= (ArrayList<Integer>)Definition[i][1];
					break;
				case ColSearchParaInt06Str:	
					SearchParaInt06Str		= (ArrayList<Integer>)Definition[i][1];
					break;
				case ColSearchParaInt07Str:	
					SearchParaInt07Str		= (ArrayList<Integer>)Definition[i][1];
					break;
				case ColSearchParaInt08Str:	
					SearchParaInt08Str		= (ArrayList<Integer>)Definition[i][1];
					break;
				case ColSearchParaInt09Str:	
					SearchParaInt09Str		= (ArrayList<Integer>)Definition[i][1];
					break;
				case ColSearchParaInt10Str:	
					SearchParaInt10Str		= (ArrayList<Integer>)Definition[i][1];
					break;
				case ColSearchParaInt01End:	
					SearchParaInt01End		= (ArrayList<Integer>)Definition[i][1];
					break;
				case ColSearchParaInt02End:	
					SearchParaInt02End		= (ArrayList<Integer>)Definition[i][1];
					break;
				case ColSearchParaInt03End:	
					SearchParaInt03End		= (ArrayList<Integer>)Definition[i][1];
					break;
				case ColSearchParaInt04End:	
					SearchParaInt04End		= (ArrayList<Integer>)Definition[i][1];
					break;
				case ColSearchParaInt05End:	
					SearchParaInt05End		= (ArrayList<Integer>)Definition[i][1];
					break;
				case ColSearchParaInt06End:	
					SearchParaInt06End		= (ArrayList<Integer>)Definition[i][1];
					break;
				case ColSearchParaInt07End:	
					SearchParaInt07End		= (ArrayList<Integer>)Definition[i][1];
					break;
				case ColSearchParaInt08End:	
					SearchParaInt08End		= (ArrayList<Integer>)Definition[i][1];
					break;
				case ColSearchParaInt09End:	
					SearchParaInt09End		= (ArrayList<Integer>)Definition[i][1];
					break;
				case ColSearchParaInt10End:	
					SearchParaInt10End		= (ArrayList<Integer>)Definition[i][1];
					break;
				case ColSearchParaTxtAll:	
					SearchParaTxtAll		= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchClWh:	
					SearchClWh				= (ArrayList<String>)Definition[i][1];
					break;
				case ColSearchClCd:	
					SearchClCd				= (ArrayList<String>)Definition[i][1];
					break;
				default:
					break;
			}
		}
		
		 Object[][] Rt	= ParameterMstWankoRtMain(
					SearchClWh,SearchClCd,
					SearchParaCd,SearchParaCdSeqStr,SearchParaCdSeqEnd,SearchParaName,
					SearchParaTxt01,SearchParaTxt02,SearchParaTxt03,SearchParaTxt04,SearchParaTxt05,
					SearchParaTxt06,SearchParaTxt07,SearchParaTxt08,SearchParaTxt09,SearchParaTxt10,
					SearchParaInt01Str,SearchParaInt02Str,SearchParaInt03Str,SearchParaInt04Str,SearchParaInt05Str,
					SearchParaInt06Str,SearchParaInt07Str,SearchParaInt08Str,SearchParaInt09Str,SearchParaInt10Str,
					SearchParaInt01End,SearchParaInt02End,SearchParaInt03End,SearchParaInt04End,SearchParaInt05End,
					SearchParaInt06End,SearchParaInt07End,SearchParaInt08End,SearchParaInt09End,SearchParaInt10End,
					SearchParaTxtAll,
					AllSearch);
		 return Rt;
		
	}
	
	private static Object[][] ParameterMstWankoRtMain(
			ArrayList<String> SearchClWh,ArrayList<String> SearchClCd,
			ArrayList<String> SearchParaCd,	ArrayList<Integer> SearchParaCdSeqStr,ArrayList<Integer> SearchParaCdSeqEnd,ArrayList<String> SearchParaName,
			ArrayList<String> SearchParaTxt01,ArrayList<String> SearchParaTxt02,ArrayList<String> SearchParaTxt03,ArrayList<String> SearchParaTxt04,ArrayList<String> SearchParaTxt05,
			ArrayList<String> SearchParaTxt06,ArrayList<String> SearchParaTxt07,ArrayList<String> SearchParaTxt08,ArrayList<String> SearchParaTxt09,ArrayList<String> SearchParaTxt10,
			ArrayList<Integer> SearchParaInt01Str,ArrayList<Integer> SearchParaInt02Str,ArrayList<Integer> SearchParaInt03Str,ArrayList<Integer> SearchParaInt04Str,ArrayList<Integer> SearchParaInt05Str,
			ArrayList<Integer> SearchParaInt06Str,ArrayList<Integer> SearchParaInt07Str,ArrayList<Integer> SearchParaInt08Str,ArrayList<Integer> SearchParaInt09Str,ArrayList<Integer> SearchParaInt10Str,
			ArrayList<Integer> SearchParaInt01End,ArrayList<Integer> SearchParaInt02End,ArrayList<Integer> SearchParaInt03End,ArrayList<Integer> SearchParaInt04End,ArrayList<Integer> SearchParaInt05End,
			ArrayList<Integer> SearchParaInt06End,ArrayList<Integer> SearchParaInt07End,ArrayList<Integer> SearchParaInt08End,ArrayList<Integer> SearchParaInt09End,ArrayList<Integer> SearchParaInt10End,
			ArrayList<String> SearchParaTxtAll,
			Boolean AllSearch){
		
		//WANKOパラメータ返却
		Object[][] rt=new Object[0][RtParameterMstWankoRt().length];
		boolean SearchKick = false;
		if(AllSearch) {
			SearchKick = true;
		}
		
		String sql= "Select \n"
				+"(WM0000PARAMETER.ClWh) as ClWh,\n"				//担当倉庫コード
				+"(KM0010_WHMST.WHName) as WHName,\n"				//担当倉庫名
				+"(WM0000PARAMETER.ClCd) as ClCd,\n"				//荷主コード
				+"(KM0030_CLIENTMST.CLName01) as CLName01,\n"		//荷主名
				+"(WM0000PARAMETER.ParaCd) as ParaCd,\n"			//パラメータコード
				+"(WM0000PARAMETER.ParaCdSeq) as ParaCdSeq,\n"		//ナンバリング
				+"(WM0000PARAMETER.ParaName) as ParaName,\n"		//パラメータ名
				+"(WM0000PARAMETER.ParaTxt01) as ParaTxt01,\n"		//パラメータテキスト項目01
				+"(WM0000PARAMETER.ParaTxt02) as ParaTxt02,\n"		//パラメータテキスト項目02
				+"(WM0000PARAMETER.ParaTxt03) as ParaTxt03,\n"		//パラメータテキスト項目03
				+"(WM0000PARAMETER.ParaTxt04) as ParaTxt04,\n"		//パラメータテキスト項目04
				+"(WM0000PARAMETER.ParaTxt05) as ParaTxt05,\n"		//パラメータテキスト項目05
				+"(WM0000PARAMETER.ParaTxt06) as ParaTxt06,\n"		//パラメータテキスト項目06
				+"(WM0000PARAMETER.ParaTxt07) as ParaTxt07,\n"		//パラメータテキスト項目07
				+"(WM0000PARAMETER.ParaTxt08) as ParaTxt08,\n"		//パラメータテキスト項目08
				+"(WM0000PARAMETER.ParaTxt09) as ParaTxt09,\n"		//パラメータテキスト項目09
				+"(WM0000PARAMETER.ParaTxt10) as ParaTxt10,\n"		//パラメータテキスト項目10
				+"(WM0000PARAMETER.ParaInt01) as ParaInt01,\n"		//パラメータ数値項目01
				+"(WM0000PARAMETER.ParaInt02) as ParaInt02,\n"		//パラメータ数値項目02
				+"(WM0000PARAMETER.ParaInt03) as ParaInt03,\n"		//パラメータ数値項目03
				+"(WM0000PARAMETER.ParaInt04) as ParaInt04,\n"		//パラメータ数値項目04
				+"(WM0000PARAMETER.ParaInt05) as ParaInt05,\n"		//パラメータ数値項目05
				+"(WM0000PARAMETER.ParaInt06) as ParaInt06,\n"		//パラメータ数値項目06
				+"(WM0000PARAMETER.ParaInt07) as ParaInt07,\n"		//パラメータ数値項目07
				+"(WM0000PARAMETER.ParaInt08) as ParaInt08,\n"		//パラメータ数値項目08
				+"(WM0000PARAMETER.ParaInt09) as ParaInt09,\n"		//パラメータ数値項目09
				+"(WM0000PARAMETER.ParaInt10) as ParaInt10,\n"		//パラメータ数値項目10
				+"(WM0000PARAMETER.EntryDate) as EntryDate,\n"		//登録日
				+"(WM0000PARAMETER.UpdateDate) as UpdateDate,\n"	//更新日
				+"(WM0000PARAMETER.EntryUser) as EntryUser,\n"		//登録者
				+"(WM0000PARAMETER.UpdateUser) as UpdateUser\n"		//更新者
				+ " from "+A00000_Main.MySqlDefaultSchemaWANKO+".WM0000PARAMETER"
				+ " left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST \n"
				+ " on("+A00000_Main.MySqlDefaultSchemaWANKO+".WM0000PARAMETER.ClWh="+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0010_WHMST.WHCD"
				+ ")\n"
				+ " left outer join "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0030_CLIENTMST \n"
				+ " on("+A00000_Main.MySqlDefaultSchemaWANKO+".WM0000PARAMETER.ClCd="+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0030_CLIENTMST.cl_cd"
				+ ")\n"
				+ " where 1=1\n";
		
		if(null!=SearchClWh && 0<SearchClWh.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchClWh.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ClWh =?";
			}
			sql = sql + ")";
		}
		if(null!=SearchClCd && 0<SearchClCd.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchClCd.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ClCd =?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaCd && 0<SearchParaCd.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaCd.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ParaCd =?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaCdSeqStr && 0<SearchParaCdSeqStr.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaCdSeqStr.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ParaCdSeq >=?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaCdSeqEnd && 0<SearchParaCdSeqEnd.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaCdSeqEnd.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ParaCdSeq <=?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaName && 0<SearchParaName.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaName.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ParaName like ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaTxt01 && 0<SearchParaTxt01.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaTxt01.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ParaTxt01 like ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaTxt02 && 0<SearchParaTxt02.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaTxt02.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ParaTxt02 like ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaTxt03 && 0<SearchParaTxt03.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaTxt03.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ParaTxt03 like ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaTxt04 && 0<SearchParaTxt04.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaTxt04.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ParaTxt04 like ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaTxt05 && 0<SearchParaTxt05.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaTxt05.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ParaTxt05 like ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaTxt06 && 0<SearchParaTxt06.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaTxt06.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ParaTxt06 like ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaTxt07 && 0<SearchParaTxt07.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaTxt07.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ParaTxt07 like ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaTxt08 && 0<SearchParaTxt08.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaTxt08.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ParaTxt08 like ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaTxt09 && 0<SearchParaTxt09.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaTxt09.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ParaTxt09 like ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaTxt10 && 0<SearchParaTxt10.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaTxt10.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ParaTxt10 like ?";
			}
			sql = sql + ")";
		}
		
		if(null!=SearchParaInt01Str && 0<SearchParaInt01Str.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt01Str.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ParaInt01 >= ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaInt02Str && 0<SearchParaInt02Str.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt02Str.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ParaInt02 >= ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaInt03Str && 0<SearchParaInt03Str.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt03Str.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ParaInt03 >= ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaInt04Str && 0<SearchParaInt04Str.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt04Str.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ParaInt04 >= ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaInt05Str && 0<SearchParaInt05Str.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt05Str.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ParaInt05 >= ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaInt06Str && 0<SearchParaInt06Str.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt06Str.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ParaInt06 >= ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaInt07Str && 0<SearchParaInt07Str.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt07Str.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ParaInt07 >= ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaInt08Str && 0<SearchParaInt08Str.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt08Str.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ParaInt08 >= ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaInt09Str && 0<SearchParaInt09Str.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt09Str.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ParaInt09 >= ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaInt10Str && 0<SearchParaInt10Str.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt10Str.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ParaInt10 >= ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaInt01End && 0<SearchParaInt01End.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt01End.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ParaInt01 <= ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaInt02End && 0<SearchParaInt02End.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt02End.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ParaInt02 <= ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaInt03End && 0<SearchParaInt03End.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt03End.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ParaInt03 <= ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaInt04End && 0<SearchParaInt04End.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt04End.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ParaInt04 <= ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaInt05End && 0<SearchParaInt05End.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt05End.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ParaInt05 <= ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaInt06End && 0<SearchParaInt06End.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt06End.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ParaInt06 <= ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaInt07End && 0<SearchParaInt07End.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt07End.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ParaInt07 <= ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaInt08End && 0<SearchParaInt08End.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt08End.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ParaInt08 <= ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaInt09End && 0<SearchParaInt09End.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt09End.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ParaInt09 <= ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaInt10End && 0<SearchParaInt10End.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaInt10End.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ParaInt10 <= ?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaTxtAll && 0<SearchParaTxtAll.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaTxtAll.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "WM0000PARAMETER.ParaTxt01 like ?";
				sql = sql + " or WM0000PARAMETER.ParaTxt02 like ?";
				sql = sql + " or WM0000PARAMETER.ParaTxt03 like ?";
				sql = sql + " or WM0000PARAMETER.ParaTxt04 like ?";
				sql = sql + " or WM0000PARAMETER.ParaTxt05 like ?";
				sql = sql + " or WM0000PARAMETER.ParaTxt06 like ?";
				sql = sql + " or WM0000PARAMETER.ParaTxt07 like ?";
				sql = sql + " or WM0000PARAMETER.ParaTxt08 like ?";
				sql = sql + " or WM0000PARAMETER.ParaTxt09 like ?";
				sql = sql + " or WM0000PARAMETER.ParaTxt10 like ?";
			}
			sql = sql + ")";
		}
		
		sql = sql + "order by WM0000PARAMETER.ClWh,WM0000PARAMETER.ClCd,WM0000PARAMETER.ParaCd,WM0000PARAMETER.ParaCdSeq";
		
		//System.out.println(sql);
		if(true==SearchKick) {
			A100_DbConnect.DB_CONN("WANKO");
			ResultSet rset01 = null;
			PreparedStatement stmt01 = null;
			try {
				stmt01 = A100_DbConnect.conn.prepareStatement(sql);
				int StmtCount = 0;
				if(null!=SearchClWh && 0<SearchClWh.size()){
					for(int i=0;i<SearchClWh.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchClWh.get(i));
					}
				}
				if(null!=SearchClCd && 0<SearchClCd.size()){
					for(int i=0;i<SearchClCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchClCd.get(i));
					}
				}
				if(null!=SearchParaCd && 0<SearchParaCd.size()){
					for(int i=0;i<SearchParaCd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchParaCd.get(i));
					}
				}
				if(null!=SearchParaCdSeqStr && 0<SearchParaCdSeqStr.size()){
					for(int i=0;i<SearchParaCdSeqStr.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchParaCdSeqStr.get(i));
					}
				}
				if(null!=SearchParaCdSeqEnd && 0<SearchParaCdSeqEnd.size()){
					for(int i=0;i<SearchParaCdSeqEnd.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchParaCdSeqEnd.get(i));
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
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchParaTxt06.get(i)+"%");
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
						stmt01.setString(StmtCount, ""+SearchParaInt01Str.get(i));
					}
				}
				if(null!=SearchParaInt02Str && 0<SearchParaInt02Str.size()){
					for(int i=0;i<SearchParaInt02Str.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchParaInt02Str.get(i));
					}
				}
				if(null!=SearchParaInt03Str && 0<SearchParaInt03Str.size()){
					for(int i=0;i<SearchParaInt03Str.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchParaInt03Str.get(i));
					}
				}
				if(null!=SearchParaInt04Str && 0<SearchParaInt04Str.size()){
					for(int i=0;i<SearchParaInt04Str.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchParaInt04Str.get(i));
					}
				}
				if(null!=SearchParaInt05Str && 0<SearchParaInt05Str.size()){
					for(int i=0;i<SearchParaInt05Str.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchParaInt05Str.get(i));
					}
				}
				if(null!=SearchParaInt06Str && 0<SearchParaInt06Str.size()){
					for(int i=0;i<SearchParaInt06Str.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchParaInt06Str.get(i));
					}
				}
				if(null!=SearchParaInt07Str && 0<SearchParaInt07Str.size()){
					for(int i=0;i<SearchParaInt07Str.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchParaInt07Str.get(i));
					}
				}
				if(null!=SearchParaInt08Str && 0<SearchParaInt08Str.size()){
					for(int i=0;i<SearchParaInt08Str.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchParaInt08Str.get(i));
					}
					sql = sql + ")";
				}
				if(null!=SearchParaInt09Str && 0<SearchParaInt09Str.size()){
					for(int i=0;i<SearchParaInt09Str.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchParaInt09Str.get(i));
					}
				}
				if(null!=SearchParaInt10Str && 0<SearchParaInt10Str.size()){
					for(int i=0;i<SearchParaInt10Str.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchParaInt10Str.get(i));
					}
				}
				if(null!=SearchParaInt01End && 0<SearchParaInt01End.size()){
					for(int i=0;i<SearchParaInt01End.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchParaInt01End.get(i));
					}
				}
				if(null!=SearchParaInt02End && 0<SearchParaInt02End.size()){
					for(int i=0;i<SearchParaInt02End.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchParaInt02End.get(i));
					}
				}
				if(null!=SearchParaInt03End && 0<SearchParaInt03End.size()){
					for(int i=0;i<SearchParaInt03End.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchParaInt03End.get(i));
					}
				}
				if(null!=SearchParaInt04End && 0<SearchParaInt04End.size()){
					for(int i=0;i<SearchParaInt04End.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchParaInt04End.get(i));
					}
				}
				if(null!=SearchParaInt05End && 0<SearchParaInt05End.size()){
					for(int i=0;i<SearchParaInt05End.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchParaInt05End.get(i));
					}
				}
				if(null!=SearchParaInt06End && 0<SearchParaInt06End.size()){
					for(int i=0;i<SearchParaInt06End.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchParaInt06End.get(i));
					}
				}
				if(null!=SearchParaInt07End && 0<SearchParaInt07End.size()){
					for(int i=0;i<SearchParaInt07End.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchParaInt07End.get(i));
					}
				}
				if(null!=SearchParaInt08End && 0<SearchParaInt08End.size()){
					for(int i=0;i<SearchParaInt08End.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchParaInt08End.get(i));
					}
				}
				if(null!=SearchParaInt09End && 0<SearchParaInt09End.size()){
					for(int i=0;i<SearchParaInt09End.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchParaInt09End.get(i));
					}
				}
				if(null!=SearchParaInt10End && 0<SearchParaInt10End.size()){
					for(int i=0;i<SearchParaInt10End.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, ""+SearchParaInt10End.get(i));
					}
				}
				if(null!=SearchParaTxtAll && 0<SearchParaTxtAll.size()){
					for(int i=0;i<SearchParaTxtAll.size();i++){
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchParaTxtAll.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchParaTxtAll.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchParaTxtAll.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchParaTxtAll.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchParaTxtAll.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchParaTxtAll.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchParaTxtAll.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchParaTxtAll.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchParaTxtAll.get(i)+"%");
						StmtCount = StmtCount+1;
						stmt01.setString(StmtCount, "%"+SearchParaTxtAll.get(i)+"%");
					}
				}
				
				rset01 = stmt01.executeQuery();
				
				rt = B100_RtObjectCreate.B100_RtObjectCreate(rset01,RtParameterMstWankoRt());
				
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