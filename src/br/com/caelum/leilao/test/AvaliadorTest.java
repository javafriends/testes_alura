package br.com.caelum.leilao.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.leilao.builder.CriadorDeLeilao;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;

public class AvaliadorTest {

	private Avaliador leiloeiro;
	private Usuario joao;
    private Usuario jose;
    private Usuario maria;
    private Usuario antonio;;

	@Before
	public void init() {
		this.leiloeiro = new Avaliador();
		this.joao = new Usuario("João");
		this.jose = new Usuario("José");
		this.maria = new Usuario("Maria");
		this.antonio = new Usuario("Antônio");
	}

	@Test
	public void devemEntenderLancesEmOrdemCrescente() {

		Leilao leilao = new CriadorDeLeilao()
	            .para("Playstation 3 Novo")
	            .lance(joao, 250)
	            .lance(jose, 300)
	            .lance(maria, 400)
	            .constroi();

	        leiloeiro.avalia(leilao);

	        assertThat(leiloeiro.getMenorLance(), equalTo(250.0));
	        assertEquals(400.0, leiloeiro.getMaiorLance(), 0.00001);

	}

	@Test
	public void devemEntenderLeilaoComUmLance() {
		Leilao leilao = new Leilao("Playstation 4 Pro");

		leilao.propoe(new Lance(joao, 1000));
		leiloeiro.avalia(leilao);

		assertEquals(1000.00, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(1000.00, leiloeiro.getMenorLance(), 0.00001);
	}


	@Test
	public void deveEncontrarOsTresMaioresLances() {

		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
                .lance(joao, 600.0)
                .lance(maria, 400.0)
                .lance(joao, 800.0)
                .lance(maria, 300.0)
                .constroi();

		leiloeiro.avalia(leilao);

		List<Lance> maiores = leiloeiro.getTresMaiores();
		assertEquals(3, maiores.size());
		assertEquals(800.00, maiores.get(0).getValor(),0.00001);
		assertEquals(600.00, maiores.get(1).getValor(),0.00001);
		assertEquals(400.00, maiores.get(2).getValor(),0.00001);

	}

	@Test(expected=RuntimeException.class)
	public void naoDeveAvaliarLeiloesSemNenhumLanceDado() {
	    Leilao leilao = new CriadorDeLeilao()
	        .para("Playstation 3 Novo")
	        .constroi();

	    leiloeiro.avalia(leilao);
	}
}
