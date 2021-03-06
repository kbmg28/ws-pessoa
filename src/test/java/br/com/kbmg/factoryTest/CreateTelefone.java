package br.com.kbmg.factoryTest;

import br.com.kbmg.domain.Telefone;
import br.com.kbmg.enums.TipoDeUsoEnum;

public class CreateTelefone {

	public static Telefone get(Long idTelefone, Integer ddd, Integer numero, String contato) {
		return new Telefone() {
			private static final long serialVersionUID = 1L;
			{
				setIdTelefone(idTelefone);
				setDdd(ddd);
				setNumero(numero);
				setContato(contato);
				setTipoDeUso(TipoDeUsoEnum.PARTICULAR);
			}
		};
	}
}
