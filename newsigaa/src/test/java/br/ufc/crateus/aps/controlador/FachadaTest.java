package br.ufc.crateus.aps.controlador;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import br.ufc.crateus.aps.model.Admin;
import br.ufc.crateus.aps.model.Aluno;
import br.ufc.crateus.aps.model.InstituicaoFinanceira;
import br.ufc.crateus.aps.model.Programa;
import br.ufc.crateus.aps.model.Projeto;
import br.ufc.crateus.aps.model.Usuario;
import br.ufc.crateus.aps.repository.UserRepository;
import br.ufc.crateus.aps.services.Cadastros;
import br.ufc.crateus.aps.services.Fachada;

public class FachadaTest {
	private static Usuario admin;

	@BeforeClass
	public static void setup() {
		admin = new Admin("admin", "123", "Andre");
		UserRepository.add(admin);
	}

	@Test
	public void findOk() {
		Usuario u = Fachada.buscarUsuario(admin, "Andre");
		assertEquals(admin, u);
	}

	@Test
	public void autenticarFail() {
		Usuario u = Fachada.autenticar("admin", "1234");
		assertNull(u);
	}

	@Test
	public void insertFail() {

		Cadastros.inserirPrograma(admin, new InstituicaoFinanceira("iff", 2), new Programa("programa x"));
		Programa p = Fachada.buscarProgramaPorNome("programa y");
		assertNull(p);
	}
}
