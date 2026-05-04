import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class B00130SoundCreate{
	static final double NS   = 0;		//無音
    
	static final double C   = 32.703;	//ド		
	static final double CS  = 34.648;	//ド#		
	static final double D   = 36.708;	//レ		
	static final double DS  = 38.891;	//レ#		
	static final double E   = 41.203;	//ミ		
	static final double F   = 43.654;	//ファ		
	static final double FS  = 46.249;	//ファ#
	static final double G   = 48.999;	//ソ
	static final double GS  = 51.913;	//ソ#	
	static final double A   = 55.000;	//ラ
	static final double AS  = 58.270;	//ラ#
	static final double B   = 61.735;	//シ
	
	//G7を100msボリューム0.5で鳴らしたいときは
	//Sound(C	,7	,100	,0.5)
	//で指定します
	
	public static void OK() {
		byte[][] Sound = {
				Sound(C	,5  ,100	,0.5),
				Sound(E	,5  ,100	,0.5),
				Sound(C	,5  ,100	,0.5),
				Sound(E	,5  ,100	,0.5),
				Sound(NS	,1  ,100	,0.5),
				Sound(C	,7	,100	,0.5),
				Sound(E 	,7	,100	,0.8)
				};
		SoundCall(Sound);
	}
	public static void NG() {
		byte[][] Sound = {
				Sound(C	,5  ,100	,0.5),
				Sound(E	,5  ,100	,0.5),
				Sound(C	,5  ,100	,0.5),
				Sound(E	,5  ,100	,0.5),
				Sound(D	,4  ,100	,0.5),
				Sound(NS	,1  ,100	,0.5),
				Sound(C  	,4	,500	,0.8)
				};
		SoundCall(Sound);
	}
	
	public static void Alert() {
		byte[][] Sound = {
				Sound(C	,5  ,100	,0.5),
				Sound(E	,5  ,100	,0.5),
				Sound(C	,5  ,100	,0.5),
				Sound(E	,5  ,100	,0.5),
				Sound(NS	,1  ,100	,0.5),
				Sound(C	,6  ,300	,0.5),
				Sound(NS	,1  ,100	,0.5),
				Sound(C	,5  ,100	,0.5),
				Sound(E	,5  ,100	,0.5),
				Sound(C	,5  ,100	,0.5),
				Sound(E	,5  ,100	,0.5),
				Sound(NS	,1  ,100	,0.5),
				Sound(C	,6  ,300	,0.5),
		};
		SoundCall(Sound);
	}
	
	public static void Alert2() {
		byte[][] Sound = {
				Sound(E	,5  ,400	,0.5),
				Sound(C	,6  ,100	,0.5),
				Sound(E	,6  ,100	,0.5),
				Sound(NS	,1  ,100	,0.5),
				Sound(E	,5  ,400	,0.5),
				Sound(C	,6  ,100	,0.5),
				Sound(E	,6  ,100	,0.5),
		};
		SoundCall(Sound);
	}
	
	public static void LVUP() {
		byte[][] Sound = {
				Sound(F	,5 	,50		,0.5),
				Sound(NS	,1  ,50		,0.5),
				Sound(F	,5 	,50		,0.5),
				Sound(NS	,1  ,50		,0.5),
				Sound(F	,5 	,50		,0.5),
				Sound(NS	,1  ,50		,0.5),
				Sound(F	,5 	,50		,0.5),
				Sound(NS	,1  ,50		,0.5),
				Sound(E	,5  ,100	,0.5),
				Sound(NS	,1  ,50		,0.5),
				Sound(G	,5  ,200	,0.5),
				Sound(NS	,1  ,50		,0.5),
				Sound(F	,5  ,400	,0.5)
		};
		SoundCall(Sound);
	}
	
	public static void CAT() {
		byte[][] Sound = {
				Sound(D	,5  ,200	,0.5),
				Sound(C	,5  ,200	,0.5),
				Sound(F	,4  ,200	,0.5),
				Sound(F	,5  ,200	,0.5),
				Sound(F	,5  ,200	,0.5),
				Sound(D	,5  ,200	,0.5),
				Sound(C	,5  ,200	,0.5),
				Sound(F	,4  ,200	,0.5),
				Sound(F	,5  ,200	,0.5),
				Sound(F	,5  ,200	,0.5),
				Sound(D	,5  ,200	,0.5),
				Sound(D	,5  ,200	,0.5),
				Sound(F	,4  ,200	,0.5),
				Sound(F	,5  ,200	,0.5),
				Sound(F	,4  ,200	,0.5),
				Sound(D	,4  ,200	,0.5),
				Sound(C	,4  ,200	,0.5),
				Sound(E	,5  ,200	,0.5),
				Sound(E	,5  ,200	,0.5)
		};
		SoundCall(Sound);
	}
	public static void Condor(){
		byte[][] Sound = {
				Sound(E	,5  ,400,0.5),
				Sound(A	,6  ,100,0.5),
				Sound(GS	,6  ,200,0.5),
				Sound(A	,6  ,200,0.5),
				Sound(B	,6  ,200,0.5),
				Sound(C	,6  ,200,0.5),
				Sound(B	,6  ,200,0.5),
				Sound(C	,6  ,200,0.5),
				Sound(D	,6  ,200,0.5),
				Sound(E	,6  ,200,0.5),
				Sound(G	,6  ,100,0.5),
				Sound(E	,6  ,800,0.5)
			};
		SoundCall(Sound);
	}
	
	public static void TwinkleStar() {
	    byte[][] Sound = {
	        Sound(C,5,300,0.5), Sound(C,5,300,0.5),
	        Sound(G,5,300,0.5), Sound(G,5,300,0.5),
	        Sound(A,5,300,0.5), Sound(A,5,300,0.5),
	        Sound(G,5,600,0.5),

	        Sound(F,5,300,0.5), Sound(F,5,300,0.5),
	        Sound(E,5,300,0.5), Sound(E,5,300,0.5),
	        Sound(D,5,300,0.5), Sound(D,5,300,0.5),
	        Sound(C,5,600,0.5)
	    };
	    SoundCall(Sound);
	}

	public static void SoundTest() {
		
		OK();
		NG();
		Alert();
		Alert2();
		LVUP();
		CAT();
		Condor();
		TwinkleStar();
	}
	
	
	public static void SoundCreate() {
		//音を鳴らすテスト用
		
		/*
		double frequency = 440.000; // 周波数 (Hz) ラ
        int durationMs200 = 200; // 長さ (ms)
        int durationMs800 = 800; // 長さ (ms)
        */
        double volume = 0.5; // 音量 (0.0〜1.0)

		byte[] Sound200_NS  = Sound(NS,1  ,200,volume);	//無音

		byte[] Sound200_C3   = Sound(C		,3 ,200,volume);	//ド3		
		byte[] Sound200_CS3  = Sound(CS	,3 ,200,volume);	//ド#3		
		byte[] Sound200_D3   = Sound(D		,3 ,200,volume);	//レ3		
		byte[] Sound200_DS3  = Sound(DS	,3 ,200,volume);	//レ#3		
		byte[] Sound200_E3   = Sound(E		,3 ,200,volume);	//ミ3		
		byte[] Sound200_F3   = Sound(F		,3 ,200,volume);	//ファ3		
		byte[] Sound200_FS3  = Sound(FS	,3 ,200,volume);	//ファ#3		
		byte[] Sound200_G3   = Sound(G		,3 ,200,volume);	//ソ3		
		byte[] Sound200_GS3  = Sound(GS	,3 ,200,volume);	//ソ#3	
		byte[] Sound200_A3   = Sound(A		,3 ,200,volume);	//ラ3		
		byte[] Sound200_AS3  = Sound(AS	,3 ,200,volume);	//ラ#3		
		byte[] Sound200_B3   = Sound(B		,3 ,200,volume);	//シ3
        byte[] Sound200_C4   = Sound(C		,4 ,200,volume);	//ド4
		byte[] Sound200_CS4  = Sound(CS	,4 ,200,volume);	//ド#4
		byte[] Sound200_D4   = Sound(D		,4 ,200,volume);	//レ4
		byte[] Sound200_DS4  = Sound(DS	,4 ,200,volume);	//レ#4
		byte[] Sound200_E4   = Sound(E		,4 ,200,volume);	//ミ4
		byte[] Sound200_F4   = Sound(F		,4 ,200,volume);	//ファ4
		byte[] Sound200_FS4  = Sound(FS	,4 ,200,volume);	//ファ#4
		byte[] Sound200_G4   = Sound(G		,4 ,200,volume);	//ソ4
		byte[] Sound200_GS4  = Sound(GS	,4 ,200,volume);	//ソ#4
		byte[] Sound200_A4   = Sound(A		,4 ,200,volume);	//ラ4
		byte[] Sound200_AS4  = Sound(AS	,4 ,200,volume);	//ラ#4
		byte[] Sound200_B4   = Sound(B		,4 ,200,volume);	//シ4
		byte[] Sound200_C5   = Sound(C		,5 ,200,volume);	//ド5
		
		byte[] Sound800_C3   = Sound(C		,3 ,800,volume);	//ド3		
		byte[] Sound800_CS3  = Sound(CS	,3 ,800,volume);	//ド#3		
		byte[] Sound800_D3   = Sound(D		,3 ,800,volume);	//レ3		
		byte[] Sound800_DS3  = Sound(DS	,3 ,800,volume);	//レ#3		
		byte[] Sound800_E3   = Sound(E		,3 ,800,volume);	//ミ3		
		byte[] Sound800_F3   = Sound(F		,3 ,800,volume);	//ファ3		
		byte[] Sound800_FS3  = Sound(FS	,3 ,800,volume);	//ファ#3		
		byte[] Sound800_G3   = Sound(G		,3 ,800,volume);	//ソ3		
		byte[] Sound800_GS3  = Sound(GS	,3 ,800,volume);	//ソ#3	
		byte[] Sound800_A3   = Sound(A		,3 ,800,volume);	//ラ3		
		byte[] Sound800_AS3  = Sound(AS	,3 ,800,volume);	//ラ#3		
		byte[] Sound800_B3   = Sound(B		,3 ,800,volume);	//シ3
		byte[] Sound800_NS   = Sound(NS	,1 ,800,volume);	//無音
        byte[] Sound800_C4   = Sound(C		,4 ,800,volume);	//ド4
		byte[] Sound800_CS4  = Sound(CS	,4 ,800,volume);	//ド#4
		byte[] Sound800_D4   = Sound(D		,4 ,800,volume);	//レ4
		byte[] Sound800_DS4  = Sound(DS	,4 ,800,volume);	//レ#4
		byte[] Sound800_E4   = Sound(E		,4 ,800,volume);	//ミ4
		byte[] Sound800_F4   = Sound(F		,4 ,800,volume);	//ファ4
		byte[] Sound800_FS4  = Sound(FS	,4 ,800,volume);	//ファ#4
		byte[] Sound800_G4   = Sound(G		,4 ,800,volume);	//ソ4
		byte[] Sound800_GS4  = Sound(GS	,4 ,800,volume);	//ソ#4
		byte[] Sound800_A4   = Sound(A		,4 ,800,volume);	//ラ4
		byte[] Sound800_AS4  = Sound(AS	,4 ,800,volume);	//ラ#4
		byte[] Sound800_B4   = Sound(B		,4 ,800,volume);	//シ4
		byte[] Sound800_C5   = Sound(C		,5 ,800,volume);	//ド5
		
		byte[][] Cat = {Sound200_D4,Sound200_C4,Sound200_F3,Sound200_F4,Sound200_F4,Sound200_D4,Sound200_C4,Sound200_F3,Sound200_F4,Sound200_F4};
		SoundCall(Cat);
	}
	
	private static byte[] Sound(double frequency,int num,int durationMs,double volume) {
		int sampleRate = 44100;
	    int samples = (int)((durationMs / 1000.0) * sampleRate);
	    int fadeSamples = (int)(sampleRate * 0.003); // 3ms
	    byte[] buffer = new byte[samples * 2]; // 16bit = 2bytes
	    
	    
	    frequency = frequency*Math.pow(2, num);
	    

	    for (int i = 0; i < samples; i++) {
	        double angle = 2.0 * Math.PI * i * frequency / sampleRate;
	        double env = 1.0;
	        if (i < fadeSamples) {
	            env = (double)i / fadeSamples;
	        }

	        else if (i > samples - fadeSamples) {
	            env = (double)(samples - i) / fadeSamples;
	        }
	        
	        short value = (short)(Math.sin(angle) * Short.MAX_VALUE * volume * env);

	        buffer[i * 2]     = (byte)(value & 0xff);
	        buffer[i * 2 + 1] = (byte)((value >> 8) & 0xff);
	    }
		return buffer;
	}
	private static void SoundCall(byte[][] bufferList){
		AudioFormat format = new AudioFormat(44100,16, 1, true, false);
        SourceDataLine line;
		try {
			line = AudioSystem.getSourceDataLine(format);
			line.open(format);
	        line.start();
			for(int i=0;i<bufferList.length;i++) {
				line.write(bufferList[i], 0, bufferList[i].length);
			}
			line.drain();
	        line.close();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		
	}
}