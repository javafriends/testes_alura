package br.com.caelum.leilao.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;

public class AvaliadorTest {

	@Test
	public void devemEntenderLancesEmOrdemCrescente() {

		Usuario joao = new Usuario("João");
		Usuario jose = new Usuario("José");
		Usuario maria = new Usuario("Maria");

		Leilao leilao = new Leilao("Playstation 4 Pro");

		leilao.propoe(new Lance(joao, 250));
		leilao.propoe(new Lance(jose, 300));
		leilao.propoe(new Lance(maria, 400));

		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);

		double maiorEsperado = 400;
		double menorEsperado = 250;

		assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.00001);

	}

	@Test
	public void devemEntenderLeilaoComUmLance() {
		Usuario joao = new Usuario("João");
		Leilao leilao = new Leilao("Playstation 4 Pro");

		leilao.propoe(new Lance(joao, 1000));
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);

		assertEquals(1000.00, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(1000.00, leiloeiro.getMenorLance(), 0.00001);
	}


	@Test
	public void deveEncontrarOsTresMaioresLances() {
		Usuario joao = new Usuario("João");
		Usuario jose = new Usuario("José");
		Usuario maria = new Usuario("Maria");
		Usuario antonio = new Usuario("Antônio");

		Leilao leilao = new Leilao("Playstation 4 Pro");

		leilao.propoe(new Lance(joao, 800));
		leilao.propoe(new Lance(jose, 600));
		leilao.propoe(new Lance(maria, 400));
		leilao.propoe(new Lance(antonio, 200));

		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);

		List<Lance> maiores = leiloeiro.getTresMaiores();
		assertEquals(3, maiores.size());
		assertEquals(800.00, maiores.get(0).getValor(),0.00001);
		assertEquals(600.00, maiores.get(1).getValor(),0.00001);
		assertEquals(400.00, maiores.get(2).getValor(),0.00001);


	}
}
