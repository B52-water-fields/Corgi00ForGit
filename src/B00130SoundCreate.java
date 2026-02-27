import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class B00130SoundCreate{
	public static void OK() {
		byte[][] OkSound = {
			Sound(261.626  	,200,0.5),
			Sound(329.628  	,200,0.5),
			Sound(261.626  	,200,0.5),
			Sound(329.628  	,200,0.5),
			Sound(523.251  	,200,0.5),
			Sound(0  		,200,0.5),
			Sound(523.251  	,200,0.5)
			};
		SoundCall(OkSound);
	}
	public static void NG() {
		byte[][] OkSound = {
			Sound(261.626  	,200,0.5),
			Sound(329.628  	,200,0.5),
			Sound(261.626  	,200,0.5),
			Sound(329.628  	,200,0.5),
			Sound(130.813  	,200,0.5),
			Sound(0  		,200,0.5),
			Sound(130.813  	,800,0.8)
			};
		SoundCall(OkSound);
	}
	public static void Alert() {
		byte[][] Sound = {
			Sound(261.626  	,200,0.5),
			Sound(329.628  	,200,0.5),
			Sound(261.626  	,200,0.5),
			Sound(329.628  	,200,0.5),
			Sound(0  	,200,0.5),
			Sound(523.251  	,800,0.5),
			Sound(0  	,200,0.5),
			Sound(261.626  	,200,0.5),
			Sound(329.628  	,200,0.5),
			Sound(261.626  	,200,0.5),
			Sound(329.628  	,200,0.5),
			Sound(0  	,200,0.5),
			Sound(523.251  	,800,0.5)
		};
		SoundCall(Sound);
	}
	
	public static void Alert2() {
		byte[][] Sound = {
			Sound(329.628  	,800,0.5),
			Sound(440.000  	,200,0.5),
			Sound(391.995  	,200,0.5),
			Sound(0  	,200,0.5),
			Sound(329.628  	,800,0.5),
			Sound(440.000  	,200,0.5),
			Sound(391.995  	,200,0.5)
		};
		SoundCall(Sound);
	}
	
	public static void LVUP() {
		byte[][] Sound = {
			Sound(349.228  	,100,0.5),
			Sound(0  	,100,0.5),
			Sound(349.228  	,100,0.5),
			Sound(0  	,100,0.5),
			Sound(349.228  	,100,0.5),
			Sound(0  	,100,0.5),
			Sound(349.228  	,100,0.5),
			Sound(0  	,100,0.5),
			Sound(311.127  	,200,0.5),
			Sound(0  	,100,0.5),
			Sound(391.995  	,400,0.5),
			Sound(0  	,200,0.5),
			Sound(349.228  	,800,0.5)
		};
		SoundCall(Sound);
	}
	
	public static void CAT() {
		byte[][] Sound = {
			Sound(293.665  	,200,0.5),
			Sound(261.626  	,200,0.5),
			Sound(174.614  	,200,0.5),
			Sound(349.228  	,200,0.5),
			Sound(349.228  	,200,0.5),
			Sound(293.665  	,200,0.5),
			Sound(261.626  	,200,0.5),
			Sound(174.614  	,200,0.5),
			Sound(349.228  	,200,0.5),
			Sound(349.228  	,200,0.5),
			Sound(293.665  	,200,0.5),
			Sound(261.626  	,200,0.5),
			Sound(174.614  	,200,0.5),
			Sound(349.228  	,200,0.5),
			Sound(146.832  	,200,0.5),
			Sound(349.228  	,200,0.5),
			Sound(130.813  	,200,0.5),
			Sound(329.628  	,200,0.5),
			Sound(329.628  	,200,0.5)
		};
		SoundCall(Sound);
	}
	
	public static void Condor(){
		byte[][] Sound = {
			Sound(220.000  	,400,0.5),
			Sound(277.183  	,100,0.5),
			Sound(293.665  	,200,0.5),
			Sound(277.183  	,200,0.5),
			Sound(293.665  	,200,0.5),
			Sound(329.628  	,200,0.5),
			Sound(349.228  	,200,0.5),
			Sound(329.628  	,200,0.5),
			Sound(349.228  	,200,0.5),
			Sound(391.995  	,200,0.5),
			Sound(493.883  	,100,0.5),
			Sound(440.000  	,800,0.5),
			Sound(523.251  	,100,0.5),
			Sound(440.000  	,800,0.5)
		};
		SoundCall(Sound);
	}
	
	public static void SoundCreate() {
		//音を鳴らすテスト用
		
		/*
		double frequency = 440.000; // 周波数 (Hz) ラ
        int durationMs200 = 200; // 長さ (ms)
        int durationMs800 = 800; // 長さ (ms)
        */
        double volume = 0.5; // 音量 (0.0〜1.0)
        
        double NS   = 0;		//無音
        
		double C3   = 130.813;	//ド3		
		double CS3  = 138.591;	//ド#3		
		double D3   = 146.832;	//レ3		
		double DS3  = 155.563;	//レ#3		
		double E3   = 164.814;	//ミ3		
		double F3   = 174.614;	//ファ3		
		double FS3  = 184.997;	//ファ#3
		double G3   = 195.998;	//ソ3
		double GS3  = 207.652;	//ソ#3	
		double A3   = 220.000;	//ラ3
		double AS3  = 233.082;	//ラ#3
		double B3   = 246.942;	//シ3
        double C4   = 261.626;	//ド4		
		double CS4  = 277.183;	//ド#4		
		double D4	= 293.665;	//レ4
		double DS4	= 311.127;	//レ#4		
		double E4	= 329.628;	//ミ4		
		double F4	= 349.228;	//ファ4		
		double FS4	= 369.994;	//ファ#4		
		double G4	= 391.995;	//ソ4		
		double GS4	= 415.305;	//ソ#4		
		double A4	= 440.000;	//ラ4		
		double AS4	= 466.164;	//ラ#4		
		double B4	= 493.883;	//シ4		
		double C5	= 523.251;	//ド5

		byte[] Sound200_NS  = Sound(NS  ,200,volume);	//無音

		byte[] Sound200_C3   = Sound(C3  ,200,volume);	//ド3		
		byte[] Sound200_CS3  = Sound(CS3 ,200,volume);	//ド#3		
		byte[] Sound200_D3   = Sound(D3  ,200,volume);	//レ3		
		byte[] Sound200_DS3  = Sound(DS3 ,200,volume);	//レ#3		
		byte[] Sound200_E3   = Sound(E3  ,200,volume);	//ミ3		
		byte[] Sound200_F3   = Sound(F3  ,200,volume);	//ファ3		
		byte[] Sound200_FS3  = Sound(FS3 ,200,volume);	//ファ#3		
		byte[] Sound200_G3   = Sound(G3  ,200,volume);	//ソ3		
		byte[] Sound200_GS3  = Sound(GS3 ,200,volume);	//ソ#3	
		byte[] Sound200_A3   = Sound(A3  ,200,volume);	//ラ3		
		byte[] Sound200_AS3  = Sound(AS3 ,200,volume);	//ラ#3		
		byte[] Sound200_B3   = Sound(B3  ,200,volume);	//シ3
        byte[] Sound200_C4   = Sound(C4  ,200,volume);	//ド4
		byte[] Sound200_CS4  = Sound(CS4 ,200,volume);	//ド#4
		byte[] Sound200_D4   = Sound(D4  ,200,volume);	//レ4
		byte[] Sound200_DS4  = Sound(DS4 ,200,volume);	//レ#4
		byte[] Sound200_E4   = Sound(E4  ,200,volume);	//ミ4
		byte[] Sound200_F4   = Sound(F4  ,200,volume);	//ファ4
		byte[] Sound200_FS4  = Sound(FS4 ,200,volume);	//ファ#4
		byte[] Sound200_G4   = Sound(G4  ,200,volume);	//ソ4
		byte[] Sound200_GS4  = Sound(GS4 ,200,volume);	//ソ#4
		byte[] Sound200_A4   = Sound(A4  ,200,volume);	//ラ4
		byte[] Sound200_AS4  = Sound(AS4 ,200,volume);	//ラ#4
		byte[] Sound200_B4   = Sound(B4  ,200,volume);	//シ4
		byte[] Sound200_C5   = Sound(C5  ,200,volume);	//ド5
		
		byte[] Sound800_C3   = Sound(C3  ,800,volume);	//ド3		
		byte[] Sound800_CS3  = Sound(CS3 ,800,volume);	//ド#3		
		byte[] Sound800_D3   = Sound(D3  ,800,volume);	//レ3		
		byte[] Sound800_DS3  = Sound(DS3 ,800,volume);	//レ#3		
		byte[] Sound800_E3   = Sound(E3  ,800,volume);	//ミ3		
		byte[] Sound800_F3   = Sound(F3  ,800,volume);	//ファ3		
		byte[] Sound800_FS3  = Sound(FS3 ,800,volume);	//ファ#3		
		byte[] Sound800_G3   = Sound(G3  ,800,volume);	//ソ3		
		byte[] Sound800_GS3  = Sound(GS3 ,800,volume);	//ソ#3	
		byte[] Sound800_A3   = Sound(A3  ,800,volume);	//ラ3		
		byte[] Sound800_AS3  = Sound(AS3 ,800,volume);	//ラ#3		
		byte[] Sound800_B3   = Sound(B3  ,800,volume);	//シ3
		byte[] Sound800_NS   = Sound(NS  ,800,volume);	//無音
        byte[] Sound800_C4   = Sound(C4  ,800,volume);	//ド4
		byte[] Sound800_CS4  = Sound(CS4 ,800,volume);	//ド#4
		byte[] Sound800_D4   = Sound(D4  ,800,volume);	//レ4
		byte[] Sound800_DS4  = Sound(DS4 ,800,volume);	//レ#4
		byte[] Sound800_E4   = Sound(E4  ,800,volume);	//ミ4
		byte[] Sound800_F4   = Sound(F4  ,800,volume);	//ファ4
		byte[] Sound800_FS4  = Sound(FS4 ,800,volume);	//ファ#4
		byte[] Sound800_G4   = Sound(G4  ,800,volume);	//ソ4
		byte[] Sound800_GS4  = Sound(GS4 ,800,volume);	//ソ#4
		byte[] Sound800_A4   = Sound(A4  ,800,volume);	//ラ4
		byte[] Sound800_AS4  = Sound(AS4 ,800,volume);	//ラ#4
		byte[] Sound800_B4   = Sound(B4  ,800,volume);	//シ4
		byte[] Sound800_C5   = Sound(C5  ,800,volume);	//ド5
		
		byte[][] Cat = {Sound200_D4,Sound200_C4,Sound200_F3,Sound200_F4,Sound200_F4,Sound200_D4,Sound200_C4,Sound200_F3,Sound200_F4,Sound200_F4};
		SoundCall(Cat);
	}
	
	private static byte[] Sound(double frequency,int durationMs,double volume) {
		// オーディオフォーマット設定
        // 波形生成（サイン波）
        float MSec = ((float)44.1*durationMs);
        byte[] buffer = new byte[(int)MSec];
        for (int i = 0; i < buffer.length; i++) {
            double angle = (2.0 * Math.PI * i * frequency) / 44100;
            buffer[i] = (byte) (Math.sin(angle) * 127.0 * volume);
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