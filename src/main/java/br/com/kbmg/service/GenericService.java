package br.com.kbmg.service;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface GenericService<T> {

	/**
	 * Criar um novo registro da entidade.
	 * 
	 * @param entity - objeto para salvar.
	 * @return O registro criado.
	 */
	public T create(T entity);

//	/**
//	 * Atualizar uma entidade que já existe.
//	 * 
//	 * @param entity - objeto com os novos dados.
//	 * @param id     - identificador do objeto.
//	 * @return O registro atualizado.
//	 * @throws IllegalArgumentException Se id for null.
//	 * @throws EntityNotFoundException  Se não encontrar a entidade com o id
//	 *                                  informado.
//	 */
//	public T update(T entity, Long id);

	/**
	 * Busca a entidade por ID.
	 * 
	 * @param id - identificador único.
	 * @param nomeDoIdDaClasse - Em caso de erro, este parâmetro será utilizado
	 * 
	 * @return O registro encontrado.
	 * @throws EntityNotFoundException- Se não existir.
	 */
	public T findById(String id, String nomeDoIdDaClasse);

	/**
	 * Busca a entidade por ID e retorna um DTO especificado por typeConvert.
	 * 
	 * @param id - identificador único.
	 * @param nomeDoIdDaClasse - Em caso de erro, este parâmetro será utilizado
	 * @param typeConvert - tipo do DTO para conversão da entidade.
	 *  
	 * @return O registro encontrado.
	 * @throws EntityNotFoundException- Se não existir.
	 */
	Object findById(String id, String nomeDoIdDaClasse, Class<?> typeConvert);
	
	/**
	 * Busca uma lista de todos os registros.
	 * 
	 * @return A lista de entidades.
	 * @throws EntityNotFoundException Se a tabela estiver vazia.
	 */
	public List<T> findAll();

	/**
	 * Busca todas as entidades e retorna o DTO equivalente.
	 * 
	 * @param typeConvert - Class do DTO para conversão
	 * 
	 * @return A lista de DTO's.
	 * @throws EntityNotFoundException Se a tabela estiver vazia.
	 */
	List<?> findAllDto(Class<?> typeConvert);

	/**
	 * Cria uma lista paginada dos registros.
	 * 
	 * @param page         - número da página desejada.
	 * @param size         - quantidade de elementos por página.
	 * @param direction    - ASC (crescente) || DESC (decrescente).
	 * @param sortProperty - atributo base do objeto para ser ordenado.
	 * 
	 * @return A paginação baseada nos parâmetros passados.
	 */
	public Page<T> findAllPaginated(int page, int size, Sort.Direction direction, String sortProperty);

	/**
	 * Apaga um registro pela entidade.
	 * 
	 * @param entity - Objeto que será deletado.
	 */
	public void delete(T entity);

	/**
	 * Apaga um registro por ID.
	 * 
	 * @param id - identificador do objeto que será apagado.
	 */
	public void deleteById(Long id);

	/**
	 * Apaga uma lista de entidades.
	 * 
	 * @param entitys - Coleção de entidades que serão deletadas.
	 */
	public void deleteAll(Set<T> entitys);

	/**
	 * Apaga uma lista de entidades somente informando os IDs.
	 * 
	 * @param id - lista de identificadores que serão deletados
	 * 
	 * @throws EntityNotFoundException Se na lista houver algum id que não existe.
	 */
	public void deleteAllById(Set<Long> id);

	
	
	
	
	Object update(String id, Object dto, Class<?> typeReturn);

}
