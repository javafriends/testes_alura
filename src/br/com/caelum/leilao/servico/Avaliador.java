package br.com.caelum.leilao.servico;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;

public class Avaliador {

	private double maiorDeTodos = Double.NEGATIVE_INFINITY;
	private double menorDeTodos = Double.POSITIVE_INFINITY;
	List<Lance> maiores;

	public void avalia(Leilao leilao) {

		if(leilao.getLances().size() ==0)
            throw new RuntimeException("Não é possível avaliar um leilão sem lances");


		maiorDeTodos  = leilao.getLances().stream()
											.mapToDouble(l -> l.getValor()).summaryStatistics().getMax();

		menorDeTodos  = leilao.getLances().stream()
				.mapToDouble(l -> l.getValor()).summaryStatistics().getMin();

		maiores = leilao.getLances();
		maiores = leilao.getLances()
						.stream()
						.sorted((l1, l2) -> Double.compare(l2.getValor(), l1.getValor()))
						.collect(Collectors.toList()).subList(0, maiores.size() > 3 ? 3 : maiores.size());



	}

	public List<Lance> getTresMaiores() {
		return maiores;
	}
	public double getMenorLance() {
		return menorDeTodos;
	}
	public double getMaiorLance() {
		return maiorDeTodos;
	}
}
