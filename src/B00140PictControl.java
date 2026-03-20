import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class B00140PictControl{
	public static boolean PictCheck(String Selected) {
		//指定ファイルが存在し、画像ファイルだった場合にTrue返却する
		boolean rt = true;
		rt = B00040ToolsFolderCheck.FLD_CHECK_ONRY(Selected);
		if(rt) {
			File GetFile = new File(Selected);
			try {
				BufferedImage bi = ImageIO.read(GetFile);
				if(null==bi) {
					rt = false;
				}else {
					rt = true;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return rt;
	}
	public static void PictSave(Image Tgtimage,String FileType,String FullPath) {
		//イメージを受け取って　指定ファイルタイプで保存する
	    BufferedImage dst = new BufferedImage(Tgtimage.getWidth(null), Tgtimage.getHeight(null),
	                                         BufferedImage.TYPE_INT_RGB);
		Graphics2D g = dst.createGraphics();
		g.drawImage(Tgtimage, 0, 0, null);
		g.dispose();
	    File outputFile = new File(FullPath);
	    try {
			ImageIO.write(dst, FileType, outputFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Image PictReSize(int XSize,int YSize,String Selected) {
		//縦幅もしくは横幅のどちらかが、指定幅と一致するようにリサイズ
		//⇒戻った画像を使うと枠幅いっぱいで縦横比を維持した画像表示が可能になります
		Image RtImage = null;
		if(null!=Selected) {
			File file = new File(Selected);
			boolean GoFg = false;
			if (file.exists()) {
				GoFg=true;
			}
			
		    if(GoFg&&null!=Selected&&!("".equals(Selected))) {
			    BufferedImage bufferedImage;
			    try {
					bufferedImage = ImageIO.read(new File(Selected));
					int GetXSize = bufferedImage.getWidth();
					int GetYSize = bufferedImage.getHeight();
					
					float magnificationX=((float)GetXSize/(float)XSize);
					float magnificationY=((float)GetYSize/(float)YSize);
					float magnification=magnificationX;
					
					if(magnificationX<magnificationY) {
						magnification = magnificationY;
					}
					
					int SetXSize = (int)(GetXSize/magnification);
					int SetYSize = (int)(GetYSize/magnification);
					RtImage = bufferedImage.getScaledInstance(SetXSize, SetYSize, Image.SCALE_DEFAULT);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return RtImage;
	}
	
	public static Image PictRotate(int XSize,int YSize,int RotationAngle,String Selected) {
		//指定角度で画像を回転して指定枠内一杯にリサイズ
		//90度づつの回転のみ
		Image RtImage = null;
		if(null!=Selected) {
			File file = new File(Selected);
			boolean GoFg = false;
			if (file.exists()) {
				GoFg=true;
			}
			if(GoFg&&null!=Selected&&!("".equals(Selected))) {
			    try {
			    	BufferedImage src = ImageIO.read(file);
			        if (src == null) {
			            return null;
			        }

			        int angle = ((RotationAngle % 360) + 360) % 360;
			        if(90>angle) {
			        	angle = 0;
			        }else if(180>angle) {
			        	angle = 90;
			        }else if(270>angle) {
			        	angle = 180;
			        }else if(360>angle) {
			        	angle = 270;
			        }

			        BufferedImage rotated;
			        int srcW = src.getWidth();
			        int srcH = src.getHeight();
			    	
			        if (angle == 90 || angle == 270) {
			            rotated = new BufferedImage(srcH, srcW, BufferedImage.TYPE_INT_ARGB);
			        } else {
			            rotated = new BufferedImage(srcW, srcH, BufferedImage.TYPE_INT_ARGB);
			        }
			        
			        Graphics2D g2d = rotated.createGraphics();
			        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		            switch (angle) {
		                case 90:
		                    g2d.translate(srcH, 0);
		                    g2d.rotate(Math.toRadians(90));
		                    break;
		                case 180:
		                    g2d.translate(srcW, srcH);
		                    g2d.rotate(Math.toRadians(180));
		                    break;
		                case 270:
		                    g2d.translate(0, srcW);
		                    g2d.rotate(Math.toRadians(270));
		                    break;
		                case 0:
		                default:
		                    break;
		            }
			        
		            g2d.drawImage(src, 0, 0, null);
				    
				    int GetXSize = rotated.getWidth();
					int GetYSize = rotated.getHeight();
					
					float magnificationX = (float) GetXSize / (float) XSize;
			        float magnificationY = (float) GetYSize / (float) YSize;
			        float magnification = Math.max(magnificationX, magnificationY);

			        int SetXSize = (int) (GetXSize / magnification);
			        int SetYSize = (int) (GetYSize / magnification);

			        RtImage = rotated.getScaledInstance(SetXSize, SetYSize, Image.SCALE_SMOOTH);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
		}
		return RtImage;
	}
}