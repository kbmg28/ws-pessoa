package br.com.kbmg.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import br.com.kbmg.domain.Email;
import br.com.kbmg.domain.Endereco;
import br.com.kbmg.domain.Pessoa;
import br.com.kbmg.domain.PessoaFisica;
import br.com.kbmg.domain.PessoaJuridica;
import br.com.kbmg.enums.TipoDeUso;
import br.com.kbmg.enums.TipoEndereco;
import br.com.kbmg.enums.TipoPessoa;
import br.com.kbmg.repository.PessoaRepository;

@Component
@Profile(value = "test")
public class RunFake implements CommandLineRunner {

	@Autowired
	PessoaRepository repository;

	private List<Endereco> listEnd = Arrays.asList(
			this.novoEndereco("69097150", "Rua Laranjal", null, "Cidade Nova", "56", "Manaus", "AM", 1302603,
					TipoEndereco.FISCAL),
			this.novoEndereco("69090080", "Rua Guapiaçu", null, "Cidade Nova", "50", "Manaus", "AM", 1302603,
					TipoEndereco.FISCAL),
			this.novoEndereco("69087031", "Rua Charles Darwin", null, "Tancredo Neves", "40", "Manaus", "AM", 1302603,
					TipoEndereco.OUTROS),
			this.novoEndereco("69317566", "Rua Sirius", null, "Cidade Satélite", "7", "Boa Vista", "RR", 1400100,
					TipoEndereco.FISCAL),
			this.novoEndereco("69317471", "Rua Santa Catarina", null, "Estados", "3A", "Boa Vista", "RR", 1400100,
					TipoEndereco.FISCAL));

	private List<Email> listEmail = Arrays.asList(this.novoEmail("teste@teste.com", TipoDeUso.PARTICULAR),
			this.novoEmail("teste2@teste.com", TipoDeUso.OUTROS),
			this.novoEmail("teste3@teste.com", TipoDeUso.PARTICULAR),
			this.novoEmail("teste4@teste.com", TipoDeUso.PARTICULAR),
			this.novoEmail("teste5@teste.com", TipoDeUso.CORPORATIVO));

	private Pessoa p;

	@Override
	public void run(String... args) throws Exception {
		this.criaPessoaFisica("TESTE DO NOME", "71941528007", "242492757", Arrays.asList(0, 1));
		this.criaPessoaJuridica("NOME DA PESSOA JURIDICA", "19056334000105", "995253323", Arrays.asList(2));
		this.criaPessoaFisica("DEV4DEV GERADOR DE CPF", "47268406061", "363537454", Arrays.asList(3, 4));
	}

	private void criaPessoaFisica(String nomeCompleto, String cpf, String rg, List<Integer> listPosEnd) {
		PessoaFisica pf = new PessoaFisica();
		p = new Pessoa();

		p.setNomeCompleto(nomeCompleto);
		p.setTipoPessoa(TipoPessoa.PF);
		p.setPessoaFisica(pf);

		pf.setCpf(cpf);
		pf.setRg(rg);
		pf.setPessoa(p);

		p = repository.save(p);

		listPosEnd.forEach(pos -> {
			this.criaEndereco(p, pos);
			this.criaEmail(p, pos);
		});	

		repository.save(p);
	}

	private void criaPessoaJuridica(String nomeCompleto, String cnpj, String inscricaoEstadual,
			List<Integer> listPosEnd) {
		PessoaJuridica pj = new PessoaJuridica();
		p = new Pessoa();

		p.setNomeCompleto(nomeCompleto);
		p.setTipoPessoa(TipoPessoa.PJ);
		p.setPessoaJuridica(pj);

		pj.setCnpj(cnpj);
		pj.setInscricaoEstadual(inscricaoEstadual);
		pj.setPessoa(p);

		p = repository.save(p);

		listPosEnd.forEach(pos -> {
			this.criaEndereco(p, pos);
			this.criaEmail(p, 0);
		});

		repository.save(p);
	}

	private void criaEmail(Pessoa p, Integer pos) {
		Email email = this.getEmail(listEmail.get(pos));
		
		email.setIdEmail(pos.longValue() + 1);
		email.setPessoa(p);
		
		p.getEmails().add(email);
	}

	private Email getEmail(Email email) {
		return new Email() {
			private static final long serialVersionUID = 1L;
			{
				setEmail(email.getEmail());
				setTipoDeUso(email.getTipoDeUso());
			}
		};
	}

	private void criaEndereco(Pessoa p, Integer pos) {
		Endereco endereco = this.getEndereco(listEnd.get(pos));

		endereco.setIdEndereco(pos.longValue() + 1);
		endereco.setPessoa(p);
		p.getEnderecos().add(endereco);
	}

	private Endereco getEndereco(Endereco end) {
		return new Endereco() {
			private static final long serialVersionUID = 1L;
			{
				setCep(end.getCep());
				setLogradouro(end.getLogradouro());
				setComplemento(end.getComplemento());
				setBairro(end.getBairro());
				setNumero(end.getNumero());
				setLocalidade(end.getLocalidade());
				setUf(end.getUf());
				setIbge(end.getIbge());
				setTipoEndereco(end.getTipoEndereco());
			}
		};
	}

	private Endereco novoEndereco(String cep, String logradouro, String complemento, String bairro, String numero,
			String localidade, String uf, Integer ibge, TipoEndereco tipoEndereco) {
		return new Endereco() {
			private static final long serialVersionUID = 1L;
			{
				setCep(cep);
				setLogradouro(logradouro);
				setComplemento(complemento);
				setBairro(bairro);
				setNumero(numero);
				setLocalidade(localidade);
				setUf(uf);
				setIbge(ibge);
				setTipoEndereco(tipoEndereco);

			}
		};
	}

	private Email novoEmail(String email, TipoDeUso tipoDeUso) {
		return new Email() {
			private static final long serialVersionUID = 1L;
			{
				setEmail(email);
				setTipoDeUso(tipoDeUso);
			}
		};
	}

}
