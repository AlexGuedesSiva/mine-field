package br.com.cod3r.cm.modelo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.cod3r.cm.excecao.ExplosaoException;

public class CampoTeste {
	
	private Campo campo;
	
	@BeforeEach
	void iniciarCampo() {
		campo = new Campo(3, 3);
	}
	
	@Test
	void testeVizinhoDistancia1Esquerda() {
		Campo vizinho = new Campo(3, 2);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoDistancia1Direta() {
		Campo vizinho = new Campo(3, 4);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoDistancia1EmCima() {
		Campo vizinho = new Campo(2, 3);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoDistancia1EmBaixo() {
		Campo vizinho = new Campo(4, 3);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoDistancia2() {
		Campo vizinho = new Campo(2, 2);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeNaoVizinho() {
		Campo vizinho = new Campo(1, 1);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertFalse(resultado);
	}
	
	@Test
	void testeValorPadraoAtributoMarcado() { 
		assertFalse(campo.isMarcado());
	}
	
	@Test
	void testeAlertanarMarcacao() { 
		campo.alternarMarcacao();
		assertTrue(campo.isMarcado());
	}
	
	@Test
	void testeAlertanarMarcacaoDuasChamada() { 
		campo.alternarMarcacao();
		campo.alternarMarcacao();
		assertFalse(campo.isMarcado());
	}
	
	@Test
	void testeAbrirNaoMinadoNaoMarcado() {
		assertTrue(campo.abrir());
	}
	
	@Test
	void testeAbrirNaoMinadoMarcado() {
		campo.alternarMarcacao();
		assertFalse(campo.abrir());
	}
	
	@Test
	void testeAbrirMinadoMarcado() {
		campo.minar();
		campo.alternarMarcacao();
		assertFalse(campo.abrir());
	}
	
	@Test
	void testeAbrirMinadoNaoMarcado() {
		campo.minar();
		
		assertThrows(ExplosaoException.class, () -> {
			campo.abrir();
		});
		
	}
	
	@Test
	void  testeAbrirComVizinhos1() {
		
		Campo campo11 = new Campo(1, 1);		
		Campo campo22 = new Campo(2, 2);
		campo22.adicionarVizinho(campo11);
		
		campo.adicionarVizinho(campo22);
		campo.abrir();
		
		assertTrue(campo22.isAberto() && campo11.isAberto());
		
	}
	
	@Test
	void  testeAbrirComVizinhos2() {
		
		Campo campo11 = new Campo(1, 1);
		Campo campo12 = new Campo(1, 2);
		campo12.minar();
		
		Campo campo22 = new Campo(2, 2);
		campo22.adicionarVizinho(campo11);
		campo22.adicionarVizinho(campo12);
		
		campo.adicionarVizinho(campo22);
		campo.abrir();
		
		assertTrue(campo22.isAberto() && campo11.isFechado());
		
	}

	@Test
	void testeGetLinhaEColuna() {
		Campo getCampo = new Campo(2, 3);		
		assertEquals(2, getCampo.getLinha());
		assertEquals(3, getCampo.getColuna());
	}
	
	 @Test
	    public void testObjectivoAlcancado() {
	        // Casos de teste com todas as combinações possíveis de valores
	        
		 	Campo caso1 = new Campo(false, true, false);
		 	Campo caso2 = new Campo(false, true, true);
		 	Campo caso3 = new Campo(true, true, false);
		 	Campo caso4 = new Campo(true, true, true);
		 	Campo caso5 = new Campo(false, false, false);
		 	Campo caso6 = new Campo(false, false, true);
		 	Campo caso7 = new Campo(false, false, false);
		 	Campo caso8 = new Campo(true, false, true);
		 
	        // Caso 1: Não minado, aberto e não marcado
		 	assertEquals(true, caso1.objectivoAlcancado());
	        
	        // Caso 2: Não minado, aberto e marcado
	        assertEquals(true, caso1.objectivoAlcancado());
	        
	        // Caso 3: Minado, aberto e não marcado
	        assertEquals(false, caso3.objectivoAlcancado());
	        
	        // Caso 4: Minado, aberto e marcado
	        assertEquals(true, caso4.objectivoAlcancado());
	        
	        // Caso 5: Não minado, fechado e não marcado
	        assertEquals(false, caso5.objectivoAlcancado());
	        
	        // Caso 6: Não minado, fechado e marcado
	        assertEquals(false, caso6.objectivoAlcancado());
	        
	        // Caso 7: Minado, fechado e não marcado
	        assertEquals(false, caso7.objectivoAlcancado());
	        
	        // Caso 8: Minado, fechado e marcado
	        assertEquals(true, caso8.objectivoAlcancado());
	    }
	
	@Test
	void testeReiniciar() {
		campo.reiniciar();
		
		assertFalse(campo.isAberto());
		assertFalse(campo.isMinado());
		assertFalse(campo.isMarcado());
	}
	
	// Teste do método ToString
	
	@Test
    void testeToStringMarcado() {
        // Arrange
        Campo objeto = new Campo(2, 2);
        objeto.alternarMarcacao();
        objeto.isMarcado();

        // Act
        String resultado = objeto.toString();

        // Assert
        String esperado = "x";
        assertEquals(esperado, resultado);
    }

    @Test
    void testeToStringAbertoEMinado() {
        // Arrange
    	Campo objeto = new Campo(3, 3);
    	campo.alternarMarcacao();
        objeto.abrir();
        objeto.minar();
        

        // Act
        String resultado = objeto.toString();

        // Assert
        String esperado = "*";
        assertEquals(esperado, resultado);
    }

     

    @Test
    void testeToStringAberto() {
        // Arrange
        Campo objeto = new Campo(1, 1);
        objeto.abrir();  
        

        // Act
        String resultado = objeto.toString();

        // Assert
        String esperado = " ";
        assertEquals(esperado, resultado);
    }

    @Test
    void testeToStringPadrao() {
        // Arrange
        Campo objeto = new Campo(1, 1);

        // Act
        String resultado = objeto.toString();

        // Assert
        String esperado = "?";
        assertEquals(esperado, resultado);
    }
	
	
	
}
