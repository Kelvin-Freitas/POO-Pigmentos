package ui;

import java.io.BufferedReader;
import session.RegistroCompra;
import java.io.IOException;
import java.io.InputStreamReader;

import exceptions.PigmentoException;

//import //session
//import //exceptions na session

public class L2ConsoleUI {
	
	private BufferedReader br;
	private RegistroCompra rc;
	
	public L2ConsoleUI () throws ClassNotFoundException, IOException {
		this.br = new BufferedReader(new InputStreamReader (System.in));
		this.rc = new RegistroCompra ();
	}
	
	
	private void criarPigmentos () throws IOException, NumberFormatException, PigmentoException {
				
		
		//------------------
		//System.out.println("Ver Pigmentos");
		this.rc.asm();
		this.printLinha();
		
	}
	
	private void debitarPigmento () throws IOException, NumberFormatException, PigmentoException {
		String corHexa;
		String result;
		double quantidade;
		char opcao;
		
		//------------------
		
		System.out.println("Fazer Compra");
		this.printLinha();
		System.out.println("Informe a cor desejada em código Hexadecimal:");
		corHexa = this.br.readLine();
		System.out.println("Informe a quantidade de litros a debitar:");
		quantidade = Double.parseDouble(this.br.readLine());
		result = this.rc.buscarCor(corHexa, quantidade);
		double valor = this.rc.getValor(result, quantidade);
		System.out.println("A cor mais próxima encontrada foi:  " + result);
		System.out.println("Valor total R$:"+ valor);
		System.out.println("Deseja prosseguir? s ou n?");
		opcao = (char)System.in.read();
		if ((opcao == 'S') || (opcao == 's')) {
			try {
				this.rc.registrarCompra(result, quantidade);
			}
			catch (PigmentoException pex){
				System.err.println("Erro - Pigmento:  " + pex);
			}
		}
		else {
			System.out.println("Retornando para o menu... \n \n \n");
		}
	}
	
	private void debitarPigmentoRelampago () throws IOException, NumberFormatException, PigmentoException {
		String corHexa;
		double quantidade;
		
		//------------------
		try {
			System.out.println("Informe a cor desejada em código Hexadecimal:");
			corHexa = this.br.readLine();
			System.out.println("Informe a quantidade de litros a debitar:");
			quantidade = Double.parseDouble(this.br.readLine());
			this.rc.registrarCompra(this.rc.buscarCor(corHexa, quantidade), quantidade);
		}
		catch (PigmentoException pex) {
			System.err.println("Erro - Pigmento:  " + pex);
		}
	}
	
	private void printLinha () {
		System.out.println("------------------------");
	}
	
	public void run() {
		String opc = "0";
		do {
			
			System.out.println("Menu");
			this.printLinha();
			System.out.println("Digite 0 para SAIR");
			System.out.println("Digite 1 criar pigmentos iniciais");
			System.out.println("Digite 2 para debitar um pigmento");
			System.out.println("Digite 3 para compra-relâmpago");
			this.printLinha();
			
			try {
				opc = this.br.readLine();
			}
			catch (IOException a){
				System.out.println("Erro - E/S: Caractere digitado inválido!");
			}
			switch (opc) {
				case "1" :
					try {
						this.criarPigmentos();
					}
					catch (NumberFormatException nfe){
						System.err.println("Valor inválido");
					}
					catch (IOException | PigmentoException ioe){
						System.err.println("Operação não concluída:" + ioe);
					}
				break;
				case "2" :
					try {
						this.debitarPigmento();
					}
					catch (NumberFormatException nfe){
						System.err.println("Valor inválido");
					}
					catch (IOException | PigmentoException ioe){
						System.err.println("Operação não concluída:" + ioe);
					}
				break;
				case "3" :
					try {
						this.debitarPigmentoRelampago();
					}
					catch (NumberFormatException nfe){
						System.err.println("Valor inválido");
					}
					catch (IOException | PigmentoException ioe){
						System.err.println("Operação não concluída:" + ioe);
					}
					
			}
			
		} while (opc != "0");
	}
	
	public static void main (String args[]) throws ClassNotFoundException, IOException {
		(new L2ConsoleUI()).run();
	}
	
}
