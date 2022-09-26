package br.gov.sp.saobernardo.pix;

import br.gov.sp.saobernardo.pix.entities.Auth;
import br.gov.sp.saobernardo.pix.entities.Cob;
import br.gov.sp.saobernardo.pix.entities.Loc;


public class App {

	public static void main(String[] args) {
		//Autenticacao
		Auth auth = new Auth();
		//Cobranca
		Cob cobranca = new Cob();
		String access_token;
		String resultCob;
		int idCob=0;
		//Loc
		Loc loc = new Loc();
		String resultLoc;
		String qrCode="";
		String image="";
		String imageName;
		
		//--------------------------------------------------
		//Autenticar
		access_token = auth.geraToken();
		System.out.println("Token = " + access_token);
		
		//--------------------------------------------------
		//Criar Cobranca
		resultCob = cobranca.geraCob(access_token);
		idCob = cobranca.getIdCob(resultCob);
		System.out.println("idCobranca = "+idCob);
		
		//--------------------------------------------------
		//Emissao QRCode de um location
		resultLoc = loc.genQrCode(idCob, access_token);
		qrCode = loc.getQrCode(resultLoc);
		System.out.println("qrCode = "+qrCode);
		//--------------------------------------------------
		//Salvar e exibir a imagem do QRCode
		image = loc.getImage(resultLoc);
		System.out.println("image = "+image);
		imageName=loc.saveImage(image); //salvando a imagem
		loc.showImage(imageName); //exibindo a imagem
		
	}
}