package br.com.kbmg.factoryTest;

import br.com.kbmg.domain.Endereco;
import br.com.kbmg.enums.StatusEnum;
import br.com.kbmg.enums.TipoEnderecoEnum;

public class CreateEndereco {

	public static Endereco get(Long idEndereco, String cep, String logradouro, String complemento, String bairro,
			String numero, String localidade, String uf, Integer ibge, TipoEnderecoEnum tipoEndereco) {
		return new Endereco() {
			private static final long serialVersionUID = 1L;
			{
				setIdEndereco(idEndereco);
				setCep(cep);
				setLogradouro(logradouro);
				setComplemento(complemento);
				setBairro(bairro);
				setNumero(numero);
				setLocalidade(localidade);
				setUf(uf);
				setIbge(ibge);
				setTipoEndereco(tipoEndereco);
				setStatus(StatusEnum.ATIVO);
			}
		};
	}
}
