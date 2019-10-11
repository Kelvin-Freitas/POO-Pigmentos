package conversor.cores;

import conversor.chars.CharToDecApp;

import entidades.Cmyk;
import entidades.HexadecimalColor;
import entidades.Rgb;

public class ConversorCoresApp implements ServicoConversorCores {

	@Override
	public Rgb htmlToRgb(HexadecimalColor novo) {
		CharToDecApp CharToDec = new CharToDecApp();
		int iCont;
		int red = 0;
		int green = 0;
		int blue = 0;
		
		for (iCont = 0; iCont<6 ; iCont++ ) {
			if (iCont < 2) { //conta Red
				char este = novo.getTodos().charAt(iCont);
				if (( iCont % 2 )== 0)
					red += CharToDec.converter(este) * 16;
				else
					red += CharToDec.converter(este);
			}
			else if (iCont == 2 && iCont < 4) { //conta Green
				char este = novo.getTodos().charAt(iCont);
				if (( iCont % 2 )== 0)
					green += CharToDec.converter(este) * 16;
				else
					green += CharToDec.converter(este);
			}
			else if (iCont == 4 && iCont < 6) { //conta Blue
				//provavelmente isso (iCont < 6) conterá qualquer lixo que poderá vir
				char este = novo.getTodos().charAt(iCont);
				if (( iCont % 2 )== 0)
					blue += CharToDec.converter(este) * 16;
				else
					blue += CharToDec.converter(este);
			}
		}
		Rgb resultado = new Rgb (red,green,blue);
		return resultado;
	}

	@Override
	public Cmyk htmlToCmyk(HexadecimalColor novo) {
		Rgb este = this.htmlToRgb(novo);
		Cmyk resultado = this.rgbToCmyk(este);
		
		return resultado;
	}

	@Override
	public Rgb cmykToRgb(Cmyk novo) {
		double red = 255 * (1 - ((double)novo.getCyan()/100)) * (1 - ((double)novo.getKeyBlack()/100));
		double green = 255 * (1 - ((double)novo.getMagenta()/100)) * (1- ((double)novo.getKeyBlack()/100));
		double blue = 255 * (1 - ((double)novo.getYellow()/100)) * (1 - ((double)novo.getKeyBlack()/100));
		
		Rgb resultado = new Rgb((int)Math.round(red), (int)Math.round(green), (int)Math.round(blue));
		return resultado;
	}
	
	@Override
	public Cmyk rgbToCmyk (Rgb novo) {
		
		int rLinha = (novo.getRed()/255);
		int gLinha = (novo.getGreen()/255);
		int bLinha = (novo.getBlue()/255);
		
		int keyBlack = 1-Math.max(Math.max(rLinha,gLinha),Math.max(gLinha,bLinha));
		int cyan = ((1 - rLinha - keyBlack) / (1 - keyBlack));
		int magenta = ((1 - gLinha - keyBlack ) / (1 - keyBlack));
		int yellow = ((1 - bLinha - keyBlack ) / (1 - keyBlack));
		
		Cmyk resultado = new Cmyk(cyan, magenta, yellow, keyBlack);
		return resultado;
	}

}
