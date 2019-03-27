package br.com.targettrust.locadora.db.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.postgresql.util.PSQLException;

import br.com.targettrust.locadora.db.EquipamentoRepository;
import br.com.targettrust.locadora.db.EquipamentoRepositoryImpl;
import br.com.targettrust.locadora.entidades.Equipamento;

public class EquipamentoRepositoryTest {
	
	private EquipamentoRepository equipamentoRepository 
		= new EquipamentoRepositoryImpl();
	
	@Test
	public void basicTest() throws Exception {
		// Arrange
		Equipamento equipamento = new Equipamento();
		equipamento.setDescricao("Equipamento de teste");
		// Act
		equipamentoRepository.insert(equipamento);
		// Assert
		List<Equipamento> equipamentos = equipamentoRepository.list();
		boolean encontrou = false;
		for(Equipamento e : equipamentos) {
			if(e.getDescricao().equals(equipamento.getDescricao())) {
				equipamentoRepository.delete(e);
				encontrou = true;
			}
		}
		Assert.assertTrue(encontrou);
	}
	
	@Test(expected=PSQLException.class)
	public void equipamentoSemDescricaoDeveLancarErro() throws Exception {
		// Arrange
		Equipamento equipamento = new Equipamento();
		// Act
		equipamentoRepository.insert(equipamento);
		// Assert - assert n�o � necess�rio aqui pois esperamos que uma exception seja lan�ada
		
	}

}