import java.io.UnsupportedEncodingException;
import java.text.Normalizer;

public class B00020ToolsTextControl{
	//文字列操作をするクラス
	

	//指定のバイト数でテキストをカットすると共にSQL禁則文字対策を施す
	public static String byte_check(String check_st,int cut_byte){
		int int01=0;
		int check01 = 0;
		String w_st = "";
		String rt_st = "";
		//System.out.println(check_st);
		if(check_st==null){check_st="";}
		check_st = check_st.replace("'", "");
		check_st = check_st.replace("\"", "");
		if(check_st == null||"".equals(check_st)){
			return rt_st;
		}else{
			byte[] bt = check_st.getBytes();
			while(check01 == 0){
				rt_st = check_st.substring(0,check_st.length()-int01);
				bt = rt_st.getBytes();
				if(bt.length<=cut_byte){
					check01 = 1;
				}
				int01 = int01 + 1;
			}
			return rt_st;
		}
	}

	//指定のバイト数でテキストをカットする※後ろを捨てる
	public static String byte_checkonly(String check_st,int cut_byte){
		int int01=0;
		int check01 = 0;
		String w_st = "";
		String rt_st = "";
		if(check_st == null||"".equals(check_st)){
			return rt_st;
		}else{
			byte[] bt = check_st.getBytes();
			while(check01 == 0){
				rt_st = check_st.substring(0,check_st.length()-int01);
				bt = rt_st.getBytes();
				if(bt.length<=cut_byte){
					check01 = 1;
				}
				int01 = int01 + 1;
			}
			return rt_st;
		}
	}

	//前半が指定のバイト以内に収まるようにテキストをカットして前後を返す
	public static String[] byte_checkBA(String check_st,int cut_byte){
		int int01=0;
		int check01 = 0;
		String w_st = "";
		String[] rt_st = {"",""};
		if(check_st == null||"".equals(check_st)){
			return rt_st;
		}else{
			byte[] bt = check_st.getBytes();
			while(check01 == 0){
				rt_st[0] = check_st.substring(0,check_st.length()-int01);
				rt_st[1] = check_st.substring(check_st.length()-int01,check_st.length());
				bt = rt_st[0].getBytes();
				if(bt.length<=cut_byte){
					check01 = 1;
				}
				int01 = int01 + 1;
			}
			return rt_st;
		}
	}

	//数字のみで構成された半角の文字列を生成する
	public static String num_only_String(String st){
		String ws = st;
		String ws2 = "";
		String ws3 = "";
		if(ws==null){ws="";}
		//全角半角変換
		ws = Normalizer.normalize(ws, Normalizer.Form.NFKC);
		ws3 = Normalizer.normalize(ws, Normalizer.Form.NFKC);
		int wc = ws.length();
		//数字以外除去
		for(int i = 0; i<wc;i++){
			ws2 = ws.substring(wc-i-1,wc-i);
			try {
				ws2 = ""+Integer.parseInt(ws2);
			} catch (NumberFormatException nfex) {
				ws3 = ws3.replace(ws2, "");
			}
		}
		ws = ws3;
		return ws;
	}

	//数字のみで構成された半角の文字列を生成する但し小数点対応の為に"."一個だけの場合は除外対象外
	//マイナス対応のために先頭のマイナスも除外対象外
	public static String num_only_String02(String st){
		String ws = st;
		String ws2 = "";
		String ws3 = "";
		int pil_c = 0;
		if(ws==null){ws="";}
		//全角半角変換
		ws = Normalizer.normalize(ws, Normalizer.Form.NFKC);
		ws3 = Normalizer.normalize(ws, Normalizer.Form.NFKC);
		int wc = ws.length();
		//数字以外除去
		for(int i = 0; i<wc;i++){
			ws2 = ws.substring(wc-i-1,wc-i);
			if(".".equals(ws2)){
				pil_c = pil_c+1;
			}else if(wc-i-1==0 && "-".equals(ws2)){
			}else {
				try {
					ws2 = ""+Integer.parseInt(ws2);
				} catch (NumberFormatException nfex) {
					ws3 = ws3.replace(ws2, "");
				}
			}
		}
		ws = ws3;
		//"."だけであれば空白返却
		if(".".equals(ws)) {
			ws = "";
		}
		if(pil_c>1){
			//"."が2個以上の場合、num_only_Stringを呼び出して、"."も除去
			ws = num_only_String(ws);
		}
		return ws;
	}

	//半角の文字列に変換※半角に出来なかった全角文字列は除去
	public static String only1byte_String(String st){
		String ws = st;
		String ws2 = "";
		String ws3 = "";
		if(ws==null){ws="";}
		//全角半角変換
		ws = Normalizer.normalize(ws, Normalizer.Form.NFKC);
		ws3 = Normalizer.normalize(ws, Normalizer.Form.NFKC);
		int wc = ws.length();
		for(int i = 0; i<wc;i++){
			ws2 = ws.substring(wc-i-1,wc-i);
			byte[] bt = ws2.getBytes();
			//System.out.println(ws2+":" + bt.length);
			if(bt.length<=1){
			}else{
				ws3 = ws3.replace(ws2, "");
			}
		}
		ws = ws3;
		return ws;
	}

	//開始位置～終了位置の文字列を返す
	public static String byte_cut(String check_st,int str,int end){
		String rt = "";
		byte[] bt = check_st.getBytes();
		if(bt.length>=end && str<end){
			int wi = end-str;
			rt = byte_checkBA(check_st,str)[1];
			rt = byte_checkBA(rt,wi)[0];
		}
		return rt;
	}

	//後方の空白文字を除去
	public static String Trim(String ws){
		boolean fg = true;
		int i = 0;
		int trim_i = ws.length();
		if(0>=ws.length()){fg=false;}
		while(fg){
			String ws01 = ws.substring(ws.length()-i-1,ws.length()-i);
			if(" ".equals(ws01)||"　".equals(ws01)){
				trim_i = ws.length()-i-1;
			}else{
				fg = false;
			}
			i = i+1;
			if(i>=ws.length()){
				fg = false;
			}
		}
		String rt = ws.substring(0,trim_i);
		return rt;
	}

	//任意の文字コードで指定のバイト数文字列を切り出し、切り出した文字列と後方の文字列を返す
	public static String[] Byte_cut(String check_st,int cut_byte){
		String[] rt = {check_st,""};
		int int01 = 0;
		int check01 = 0;
		String StCd = "MS932";
		if(0<check_st.length()) {
			String CST = check_st.substring(0,1);
			try {
				byte[] bytes = CST.getBytes("UTF-16");
			} catch (UnsupportedEncodingException e) {
			}
		}
		
		try {
			byte[] bt = check_st.getBytes("MS932");
			if (bt.length > cut_byte){
				while(check01==0){
					rt[0] = check_st.substring(0,check_st.length()-int01);
					rt[1] = check_st.substring(check_st.length()-int01,check_st.length());
					bt = rt[0].getBytes("MS932");
					if(bt.length<=cut_byte){
						check01 = 1;
					}
					int01 = int01 + 1;
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return rt;
	}
	
	
	//SHIFTJIS扱いで指定のバイト数文字列を切り出し、切り出した文字列と後方の文字列を返す
	public static String[] SJisByte_cut(String check_st,int cut_byte){
		String[] rt = {check_st,""};
		int int01 = 0;
		int check01 = 0;
		try {
			byte[] bt = check_st.getBytes("MS932");
			if (bt.length > cut_byte){
				while(check01==0){
					rt[0] = check_st.substring(0,check_st.length()-int01);
					rt[1] = check_st.substring(check_st.length()-int01,check_st.length());
					bt = rt[0].getBytes("MS932");
					if(bt.length<=cut_byte){
						check01 = 1;
					}
					int01 = int01 + 1;
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return rt;
	}

	//UTF扱いで指定のバイト数文字列を切り出し、切り出した文字列と後方の文字列を返す
	public static String[] UTFByte_cut(String check_st,int cut_byte){
		String[] rt = {check_st,""};
		int int01 = 0;
		int check01 = 0;
		try {
			byte[] bt = check_st.getBytes("UTF-8");
			//System.out.println(bt.length);
			if (bt.length > cut_byte){
				while(check01==0){
					rt[0] = check_st.substring(0,check_st.length()-int01);
					rt[1] = check_st.substring(check_st.length()-int01,check_st.length());
					bt = rt[0].getBytes("UTF-8");
					if(bt.length<=cut_byte){
						check01 = 1;
					}
					int01 = int01 + 1;
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return rt;
	}
}