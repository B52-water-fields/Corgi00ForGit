import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class M100_ParameterMstRtNyanko{
	/*
	コピペ用
	ArrayList<String> SearchParaCd			= new ArrayList<String>();
	ArrayList<Integer> SearchParaCdSeqStr	= new ArrayList<Integer>();
	ArrayList<Integer> SearchParaCdSeqEnd	= new ArrayList<Integer>();
	ArrayList<String> SearchParaName		= new ArrayList<String>();
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
	ArrayList<String> SearchParaTxtAll		= new ArrayList<String>();
	Boolean AllSearch = false;
	
	Object[][] ParameterMstRtNANKO = M100_ParameterMstRtNyanko.ParameterMstRtNANKO(
			SearchParaCd,SearchParaCdSeqStr,SearchParaCdSeqEnd,SearchParaName,
			SearchParaTxt01,SearchParaTxt02,SearchParaTxt03,SearchParaTxt04,SearchParaTxt05,
			SearchParaTxt06,SearchParaTxt07,SearchParaTxt08,SearchParaTxt09,SearchParaTxt10,
			SearchParaInt01Str,SearchParaInt02Str,SearchParaInt03Str,SearchParaInt04Str,SearchParaInt05Str,
			SearchParaInt06Str,SearchParaInt07Str,SearchParaInt08Str,SearchParaInt09Str,SearchParaInt10Str,
			SearchParaInt01End,SearchParaInt02End,SearchParaInt03End,SearchParaInt04End,SearchParaInt05End,
			SearchParaInt06End,SearchParaInt07End,SearchParaInt08End,SearchParaInt09End,SearchParaInt10End,
			SearchParaTxtAll,
			AllSearch);
			
	String GetParaCd		= (String)ParameterMstRtNANKO[i][M100_ParameterMstRtNyanko.ColParaCd];		//パラメータコード
	int GetParaCdSeq		= (int)ParameterMstRtNANKO[i][M100_ParameterMstRtNyanko.ColParaCdSeq];		//ナンバリング
	String GetParaName		= (String)ParameterMstRtNANKO[i][M100_ParameterMstRtNyanko.ColParaName];	//パラメータ名
	String GetParaTxt01		= (String)ParameterMstRtNANKO[i][M100_ParameterMstRtNyanko.ColParaTxt01];	//パラメータテキスト項目01
	String GetParaTxt02		= (String)ParameterMstRtNANKO[i][M100_ParameterMstRtNyanko.ColParaTxt02];	//パラメータテキスト項目02
	String GetParaTxt03		= (String)ParameterMstRtNANKO[i][M100_ParameterMstRtNyanko.ColParaTxt03];	//パラメータテキスト項目03
	String GetParaTxt04		= (String)ParameterMstRtNANKO[i][M100_ParameterMstRtNyanko.ColParaTxt04];	//パラメータテキスト項目04
	String GetParaTxt05		= (String)ParameterMstRtNANKO[i][M100_ParameterMstRtNyanko.ColParaTxt05];	//パラメータテキスト項目05
	String GetParaTxt06		= (String)ParameterMstRtNANKO[i][M100_ParameterMstRtNyanko.ColParaTxt06];	//パラメータテキスト項目06
	String GetParaTxt07		= (String)ParameterMstRtNANKO[i][M100_ParameterMstRtNyanko.ColParaTxt07];	//パラメータテキスト項目07
	String GetParaTxt08		= (String)ParameterMstRtNANKO[i][M100_ParameterMstRtNyanko.ColParaTxt08];	//パラメータテキスト項目08
	String GetParaTxt09		= (String)ParameterMstRtNANKO[i][M100_ParameterMstRtNyanko.ColParaTxt09];	//パラメータテキスト項目09
	String GetParaTxt10		= (String)ParameterMstRtNANKO[i][M100_ParameterMstRtNyanko.ColParaTxt10];	//パラメータテキスト項目10
	int GetParaInt01		= (int)ParameterMstRtNANKO[i][M100_ParameterMstRtNyanko.ColParaInt01];		//パラメータ数値項目01
	int GetParaInt02		= (int)ParameterMstRtNANKO[i][M100_ParameterMstRtNyanko.ColParaInt02];		//パラメータ数値項目02
	int GetParaInt03		= (int)ParameterMstRtNANKO[i][M100_ParameterMstRtNyanko.ColParaInt03];		//パラメータ数値項目03
	int GetParaInt04		= (int)ParameterMstRtNANKO[i][M100_ParameterMstRtNyanko.ColParaInt04];		//パラメータ数値項目04
	int GetParaInt05		= (int)ParameterMstRtNANKO[i][M100_ParameterMstRtNyanko.ColParaInt05];		//パラメータ数値項目05
	int GetParaInt06		= (int)ParameterMstRtNANKO[i][M100_ParameterMstRtNyanko.ColParaInt06];		//パラメータ数値項目06
	int GetParaInt07		= (int)ParameterMstRtNANKO[i][M100_ParameterMstRtNyanko.ColParaInt07];		//パラメータ数値項目07
	int GetParaInt08		= (int)ParameterMstRtNANKO[i][M100_ParameterMstRtNyanko.ColParaInt08];		//パラメータ数値項目08
	int GetParaInt09		= (int)ParameterMstRtNANKO[i][M100_ParameterMstRtNyanko.ColParaInt09];		//パラメータ数値項目09
	int GetParaInt10		= (int)ParameterMstRtNANKO[i][M100_ParameterMstRtNyanko.ColParaInt10];		//パラメータ数値項目10
	String GetEntryDate		= (String)ParameterMstRtNANKO[i][M100_ParameterMstRtNyanko.ColEntryDate];	//登録日
	String GetUpdateDate	= (String)ParameterMstRtNANKO[i][M100_ParameterMstRtNyanko.ColUpdateDate];	//更新日
	String GetEntryUser		= (String)ParameterMstRtNANKO[i][M100_ParameterMstRtNyanko.ColEntryUser];	//登録者
	String GetUpdateUser	= (String)ParameterMstRtNANKO[i][M100_ParameterMstRtNyanko.ColUpdateUser];	//更新者
	
	*/
	
	//戻り値カラム
	static final  int ColParaCd		= (int) 0;	//パラメータコード
	static final  int ColParaCdSeq	= (int) 1;	//ナンバリング
	static final  int ColParaName		= (int) 2;	//パラメータ名
	static final  int ColParaTxt01	= (int) 3;	//パラメータテキスト項目01
	static final  int ColParaTxt02	= (int) 4;	//パラメータテキスト項目02
	static final  int ColParaTxt03	= (int) 5;	//パラメータテキスト項目03
	static final  int ColParaTxt04	= (int) 6;	//パラメータテキスト項目04
	static final  int ColParaTxt05	= (int) 7;	//パラメータテキスト項目05
	static final  int ColParaTxt06	= (int) 8;	//パラメータテキスト項目06
	static final  int ColParaTxt07	= (int) 9;	//パラメータテキスト項目07
	static final  int ColParaTxt08	= (int)10;	//パラメータテキスト項目08
	static final  int ColParaTxt09	= (int)11;	//パラメータテキスト項目09
	static final  int ColParaTxt10	= (int)12;	//パラメータテキスト項目10
	static final  int ColParaInt01	= (int)13;	//パラメータ数値項目01
	static final  int ColParaInt02	= (int)14;	//パラメータ数値項目02
	static final  int ColParaInt03	= (int)15;	//パラメータ数値項目03
	static final  int ColParaInt04	= (int)16;	//パラメータ数値項目04
	static final  int ColParaInt05	= (int)17;	//パラメータ数値項目05
	static final  int ColParaInt06	= (int)18;	//パラメータ数値項目06
	static final  int ColParaInt07	= (int)19;	//パラメータ数値項目07
	static final  int ColParaInt08	= (int)20;	//パラメータ数値項目08
	static final  int ColParaInt09	= (int)21;	//パラメータ数値項目09
	static final  int ColParaInt10	= (int)22;	//パラメータ数値項目10
	static final  int ColEntryDate	= (int)23;	//登録日
	static final  int ColUpdateDate	= (int)24;	//更新日
	static final  int ColEntryUser	= (int)25;	//登録者
	static final  int ColUpdateUser	= (int)26;	//更新者
	
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
	
	public static Object[][] RtParameterMstRtNANKO(){
		Object[][] RtSettingParameterMstRtNANKO = {
				 {"ParaCd"		,ColParaCd			,"String"	,"パラメータCD"		,"Key"}
				,{"ParaCdSeq"	,ColParaCdSeq		,"int"		,"Seq"				,"Key"}
				,{"ParaName"	,ColParaName		,"String"	,"パラメータ名"		,""}
				,{"ParaTxt01"	,ColParaTxt01		,"String"	,"テキスト項目01"	,""}
				,{"ParaTxt02"	,ColParaTxt02		,"String"	,"テキスト項目02"	,""}
				,{"ParaTxt03"	,ColParaTxt03		,"String"	,"テキスト項目03"	,""}
				,{"ParaTxt04"	,ColParaTxt04		,"String"	,"テキスト項目04"	,""}
				,{"ParaTxt05"	,ColParaTxt05		,"String"	,"テキスト項目05"	,""}
				,{"ParaTxt06"	,ColParaTxt06		,"String"	,"テキスト項目06"	,""}
				,{"ParaTxt07"	,ColParaTxt07		,"String"	,"テキスト項目07"	,""}
				,{"ParaTxt08"	,ColParaTxt08		,"String"	,"テキスト項目08"	,""}
				,{"ParaTxt09"	,ColParaTxt09		,"String"	,"テキスト項目09"	,""}
				,{"ParaTxt10"	,ColParaTxt10		,"String"	,"テキスト項目10"	,""}
				,{"ParaInt01"	,ColParaInt01		,"int"		,"数値項目01"		,""}
				,{"ParaInt02"	,ColParaInt02		,"int"		,"数値項目02"		,""}
				,{"ParaInt03"	,ColParaInt03		,"int"		,"数値項目03"		,""}
				,{"ParaInt04"	,ColParaInt04		,"int"		,"数値項目04"		,""}
				,{"ParaInt05"	,ColParaInt05		,"int"		,"数値項目05"		,""}
				,{"ParaInt06"	,ColParaInt06		,"int"		,"数値項目06"		,""}
				,{"ParaInt07"	,ColParaInt07		,"int"		,"数値項目07"		,""}
				,{"ParaInt08"	,ColParaInt08		,"int"		,"数値項目08"		,""}
				,{"ParaInt09"	,ColParaInt09		,"int"		,"数値項目09"		,""}
				,{"ParaInt10"	,ColParaInt10		,"int"		,"数値項目10"		,""}
				,{"EntryDate"	,ColEntryDate		,"DateTime"	,"登録日"			,""}
				,{"UpdateDate"	,ColUpdateDate	,"DateTime"	,"更新日"			,""}
				,{"EntryUser"	,ColEntryUser		,"String"	,"登録者"			,""}
				,{"UpdateUser"	,ColUpdateUser	,"String"	,"更新者"			,""}
				};
		
		RtSettingParameterMstRtNANKO = B100_LanguageControl.RtControl(RtSettingParameterMstRtNANKO);
		
		return RtSettingParameterMstRtNANKO;
	}
	public static Object[][] ParameterMstRtFromParaCdAndSeq(String ParaCd,int Seq){
		//現パラメータ情報をパラメータコード・シーケンシャル番号指定で取得する
		
		if(null==ParaCd) {ParaCd="";}
		
		ArrayList<String> SearchParaCd			= new ArrayList<String>();
		ArrayList<Integer> SearchParaCdSeqStr	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaCdSeqEnd	= new ArrayList<Integer>();
		ArrayList<String> SearchParaName		= new ArrayList<String>();
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
		ArrayList<String> SearchParaTxtAll		= new ArrayList<String>();
		Boolean AllSearch = false;
		
		SearchParaCd.add(ParaCd);
		
		SearchParaCdSeqStr.add(Seq);
		SearchParaCdSeqEnd.add(Seq);
		
		
		
		Object[][] ParameterMstRtNANKO = M100_ParameterMstRtNyanko.ParameterMstRtNANKO(
				SearchParaCd,SearchParaCdSeqStr,SearchParaCdSeqEnd,SearchParaName,
				SearchParaTxt01,SearchParaTxt02,SearchParaTxt03,SearchParaTxt04,SearchParaTxt05,
				SearchParaTxt06,SearchParaTxt07,SearchParaTxt08,SearchParaTxt09,SearchParaTxt10,
				SearchParaInt01Str,SearchParaInt02Str,SearchParaInt03Str,SearchParaInt04Str,SearchParaInt05Str,
				SearchParaInt06Str,SearchParaInt07Str,SearchParaInt08Str,SearchParaInt09Str,SearchParaInt10Str,
				SearchParaInt01End,SearchParaInt02End,SearchParaInt03End,SearchParaInt04End,SearchParaInt05End,
				SearchParaInt06End,SearchParaInt07End,SearchParaInt08End,SearchParaInt09End,SearchParaInt10End,
				SearchParaTxtAll,
				AllSearch);
		return ParameterMstRtNANKO;
	}
	
	public static Object[][] ParameterMstRtFromParaCdAndSeq(String ParaCd,int SeqStr,int SeqEnd,Boolean SeqAllTgt){
		//パラメータ情報をパラメータコード・シーケンシャル番号範囲指定で取得する　Boolean SeqAllTgt = true　シーケンシャル番号気にせず全部取得
		if(null==ParaCd) {ParaCd="";}
		
		ArrayList<String> SearchParaCd			= new ArrayList<String>();
		ArrayList<Integer> SearchParaCdSeqStr	= new ArrayList<Integer>();
		ArrayList<Integer> SearchParaCdSeqEnd	= new ArrayList<Integer>();
		ArrayList<String> SearchParaName		= new ArrayList<String>();
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
		ArrayList<String> SearchParaTxtAll		= new ArrayList<String>();
		Boolean AllSearch = false;
		
		SearchParaCd.add(ParaCd);
		
		if(SeqAllTgt) {
			
		}else {
			SearchParaCdSeqStr.add(SeqStr);
			SearchParaCdSeqEnd.add(SeqEnd);
		}
		
		Object[][] ParameterMstRtNANKO = M100_ParameterMstRtNyanko.ParameterMstRtNANKO(
				SearchParaCd,SearchParaCdSeqStr,SearchParaCdSeqEnd,SearchParaName,
				SearchParaTxt01,SearchParaTxt02,SearchParaTxt03,SearchParaTxt04,SearchParaTxt05,
				SearchParaTxt06,SearchParaTxt07,SearchParaTxt08,SearchParaTxt09,SearchParaTxt10,
				SearchParaInt01Str,SearchParaInt02Str,SearchParaInt03Str,SearchParaInt04Str,SearchParaInt05Str,
				SearchParaInt06Str,SearchParaInt07Str,SearchParaInt08Str,SearchParaInt09Str,SearchParaInt10Str,
				SearchParaInt01End,SearchParaInt02End,SearchParaInt03End,SearchParaInt04End,SearchParaInt05End,
				SearchParaInt06End,SearchParaInt07End,SearchParaInt08End,SearchParaInt09End,SearchParaInt10End,
				SearchParaTxtAll,
				AllSearch);
		return ParameterMstRtNANKO;
	}

	public static Object[][] DefinitionRt(){
		Object[][] Definition = {
					 {"String"		,null	,"Exact"		,ColSearchParaCd			,""		,"パラメータコード"		,""}
					,{"Integer"		,null	,"RangeMin"		,ColSearchParaCdSeqStr	,""		,"ナンバリング"			,"最小"}
					,{"Integer"		,null	,"RangeMax"		,ColSearchParaCdSeqEnd	,""		,"ナンバリング"			,"最大"}
					,{"String"		,null	,"Partial"		,ColSearchParaName		,""		,"パラメータ名"			,""}
					,{"String"		,null	,"Partial"		,ColSearchParaTxt01		,""		,"テキスト項目01"		,""}
					,{"String"		,null	,"Partial"		,ColSearchParaTxt02		,""		,"テキスト項目02"		,""}
					,{"String"		,null	,"Partial"		,ColSearchParaTxt03		,""		,"テキスト項目03"		,""}
					,{"String"		,null	,"Partial"		,ColSearchParaTxt04		,""		,"テキスト項目04"		,""}
					,{"String"		,null	,"Partial"		,ColSearchParaTxt05		,""		,"テキスト項目05"		,""}
					,{"String"		,null	,"Partial"		,ColSearchParaTxt06		,""		,"テキスト項目06"		,""}
					,{"String"		,null	,"Partial"		,ColSearchParaTxt07		,""		,"テキスト項目07"		,""}
					,{"String"		,null	,"Partial"		,ColSearchParaTxt08		,""		,"テキスト項目08"		,""}
					,{"String"		,null	,"Partial"		,ColSearchParaTxt09		,""		,"テキスト項目09"		,""}
					,{"String"		,null	,"Partial"		,ColSearchParaTxt10		,""		,"テキスト項目10"		,""}
					,{"Integer"		,null	,"RangeMin"		,ColSearchParaInt01Str	,""		,"数値項目01"			,"最小"}
					,{"Integer"		,null	,"RangeMin"		,ColSearchParaInt02Str	,""		,"数値項目02"			,"最小"}
					,{"Integer"		,null	,"RangeMin"		,ColSearchParaInt03Str	,""		,"数値項目03"			,"最小"}
					,{"Integer"		,null	,"RangeMin"		,ColSearchParaInt04Str	,""		,"数値項目04"			,"最小"}
					,{"Integer"		,null	,"RangeMin"		,ColSearchParaInt05Str	,""		,"数値項目05"			,"最小"}
					,{"Integer"		,null	,"RangeMin"		,ColSearchParaInt06Str	,""		,"数値項目06"			,"最小"}
					,{"Integer"		,null	,"RangeMin"		,ColSearchParaInt07Str	,""		,"数値項目07"			,"最小"}
					,{"Integer"		,null	,"RangeMin"		,ColSearchParaInt08Str	,""		,"数値項目08"			,"最小"}
					,{"Integer"		,null	,"RangeMin"		,ColSearchParaInt09Str	,""		,"数値項目09"			,"最小"}
					,{"Integer"		,null	,"RangeMin"		,ColSearchParaInt10Str	,""		,"数値項目10"			,"最小"}
					,{"Integer"		,null	,"RangeMax"		,ColSearchParaInt01End	,""		,"数値項目01"			,"最大"}
					,{"Integer"		,null	,"RangeMax"		,ColSearchParaInt02End	,""		,"数値項目02"			,"最大"}
					,{"Integer"		,null	,"RangeMax"		,ColSearchParaInt03End	,""		,"数値項目03"			,"最大"}
					,{"Integer"		,null	,"RangeMax"		,ColSearchParaInt04End	,""		,"数値項目04"			,"最大"}
					,{"Integer"		,null	,"RangeMax"		,ColSearchParaInt05End	,""		,"数値項目05"			,"最大"}
					,{"Integer"		,null	,"RangeMax"		,ColSearchParaInt06End	,""		,"数値項目06"			,"最大"}
					,{"Integer"		,null	,"RangeMax"		,ColSearchParaInt07End	,""		,"数値項目07"			,"最大"}
					,{"Integer"		,null	,"RangeMax"		,ColSearchParaInt08End	,""		,"数値項目08"			,"最大"}
					,{"Integer"		,null	,"RangeMax"		,ColSearchParaInt09End	,""		,"数値項目09"			,"最大"}
					,{"Integer"		,null	,"RangeMax"		,ColSearchParaInt10End	,""		,"数値項目10"			,"最大"}
					,{"String"		,null	,"Partial"		,ColSearchParaTxtAll		,""		,"テキスト項目全体"		,""}
					};		
		
		Definition = B100_LanguageControl.DefinitionControl(Definition);
		
		return Definition;
	}
	public static Object[][] ParameterMstRtNANKO(
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
				default:
					break;
			}
		}
		
		Object[][] Rt	= ParameterMstRtNANKOMain(
				SearchParaCd,	SearchParaCdSeqStr,SearchParaCdSeqEnd,SearchParaName,
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
	
	private static Object[][] ParameterMstRtNANKOMain(
			ArrayList<String> SearchParaCd,	ArrayList<Integer> SearchParaCdSeqStr,ArrayList<Integer> SearchParaCdSeqEnd,ArrayList<String> SearchParaName,
			ArrayList<String> SearchParaTxt01,ArrayList<String> SearchParaTxt02,ArrayList<String> SearchParaTxt03,ArrayList<String> SearchParaTxt04,ArrayList<String> SearchParaTxt05,
			ArrayList<String> SearchParaTxt06,ArrayList<String> SearchParaTxt07,ArrayList<String> SearchParaTxt08,ArrayList<String> SearchParaTxt09,ArrayList<String> SearchParaTxt10,
			ArrayList<Integer> SearchParaInt01Str,ArrayList<Integer> SearchParaInt02Str,ArrayList<Integer> SearchParaInt03Str,ArrayList<Integer> SearchParaInt04Str,ArrayList<Integer> SearchParaInt05Str,
			ArrayList<Integer> SearchParaInt06Str,ArrayList<Integer> SearchParaInt07Str,ArrayList<Integer> SearchParaInt08Str,ArrayList<Integer> SearchParaInt09Str,ArrayList<Integer> SearchParaInt10Str,
			ArrayList<Integer> SearchParaInt01End,ArrayList<Integer> SearchParaInt02End,ArrayList<Integer> SearchParaInt03End,ArrayList<Integer> SearchParaInt04End,ArrayList<Integer> SearchParaInt05End,
			ArrayList<Integer> SearchParaInt06End,ArrayList<Integer> SearchParaInt07End,ArrayList<Integer> SearchParaInt08End,ArrayList<Integer> SearchParaInt09End,ArrayList<Integer> SearchParaInt10End,
			ArrayList<String> SearchParaTxtAll,
			Boolean AllSearch){
		
		//NYANKOパラメータ返却
		Object[][] rt=new Object[0][RtParameterMstRtNANKO().length];
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
			+ " from "+A00000_Main.MySqlDefaultSchemaNYANKO+".KM0000_PARAMETER \n"
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

		if(null!=SearchParaCdSeqStr && 0<SearchParaCdSeqStr.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaCdSeqStr.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "KM0000_PARAMETER.ParaCdSeq >=?";
			}
			sql = sql + ")";
		}
		if(null!=SearchParaCdSeqEnd && 0<SearchParaCdSeqEnd.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaCdSeqEnd.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "KM0000_PARAMETER.ParaCdSeq <=?";
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
		
		if(null!=SearchParaTxtAll && 0<SearchParaTxtAll.size()){
			SearchKick = true;
			sql = sql + " and (";
			for(int i=0;i<SearchParaTxtAll.size();i++){
				if(i>0){sql = sql + " or ";}
				sql = sql + "KM0000_PARAMETER.ParaTxt01 like ?";
				sql = sql + " or KM0000_PARAMETER.ParaTxt02 like ?";
				sql = sql + " or KM0000_PARAMETER.ParaTxt03 like ?";
				sql = sql + " or KM0000_PARAMETER.ParaTxt04 like ?";
				sql = sql + " or KM0000_PARAMETER.ParaTxt05 like ?";
				sql = sql + " or KM0000_PARAMETER.ParaTxt06 like ?";
				sql = sql + " or KM0000_PARAMETER.ParaTxt07 like ?";
				sql = sql + " or KM0000_PARAMETER.ParaTxt08 like ?";
				sql = sql + " or KM0000_PARAMETER.ParaTxt09 like ?";
				sql = sql + " or KM0000_PARAMETER.ParaTxt10 like ?";
			}
			sql = sql + ")";
		}
		
		
		sql =sql + " order by KM0000_PARAMETER.ParaCd,KM0000_PARAMETER.ParaCdSeq";
		//System.out.println(sql);
		if(true==SearchKick) {
			A100_DbConnect.DB_CONN("NANKO");
			ResultSet rset01 = null;
			PreparedStatement stmt01 = null;
			try {
				stmt01 = A100_DbConnect.conn.prepareStatement(sql);
				int StmtCount = 0;
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
				
				rt = B100_RtObjectCreate.B100_RtObjectCreate(rset01,RtParameterMstRtNANKO());

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