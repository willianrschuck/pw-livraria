package br.edu.ifsul.livraria.controller;

import java.io.ByteArrayInputStream;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.edu.ifsul.livraria.dao.AutorDAO;
import br.edu.ifsul.livraria.dao.FiltroTabelaLazy;
import br.edu.ifsul.livraria.dao.LivroDAO;
import br.edu.ifsul.livraria.dao.TableFilter;
import br.edu.ifsul.livraria.model.Autor;
import br.edu.ifsul.livraria.model.Imagem;
import br.edu.ifsul.livraria.model.Livro;
import br.edu.ifsul.livraria.util.JsfUtil;

@Named
@SessionScoped
public class LivroController extends AbstractController {
	private static final long serialVersionUID = 1L;

	@EJB private LivroDAO dao;
	@EJB private AutorDAO autorDao;
	
	private Livro livro;
	
	private Imagem imagem;

	private FiltroTabelaLazy<Livro> tabelaLazy;

	private List<Autor> autores;
	private List<Autor> autoresSelecionados;

	
	@PostConstruct
	private void init() {
		tabelaLazy = new FiltroTabelaLazy<Livro>(dao);
		tabelaLazy.addFiltro(new TableFilter("isbn", "Isbn", "like"));
		tabelaLazy.addFiltro(new TableFilter("titulo", "Titulo", "like"));
		tabelaLazy.addFiltro(new TableFilter("formato.nome", "Formato", "like"));
	}
	
	@Override
	public void novo() {
		livro = new Livro();
	}

	@Override
	public void salvar() {
		try {
			if (livro.getDataCadastro() == null) {
				livro.setDataCadastro(new Date());
				dao.persist(livro);
			} else {
				dao.merge(livro);
			}
			JsfUtil.sendInfoMessage("Livro cadastrado com sucesso.");
		} catch (Exception e) {
			JsfUtil.sendErrorMessage(JsfUtil.getErrorMessage(e));
		}
	}

	@Override
	public void editar(Object id) {
		try {
			livro = dao.getBy(id);
		} catch (Exception e) {
			JsfUtil.sendErrorMessage(JsfUtil.getErrorMessage(e));
		}
	}

	@Override
	public void remover(Object id) {
		try {
			dao.remove(dao.getBy(id));
			JsfUtil.sendInfoMessage("Livro removido com sucesso.");
		} catch (Exception e) {
			JsfUtil.sendErrorMessage(JsfUtil.getErrorMessage(e));
		}
	}
	
	@Override
	public String irParaLista() {
		return "/livro/lista.xhtml?faces-redirect=true";
	}
	
	
	public void novaImagem() {
		imagem = new Imagem();
	}
	
	public void salvarImagem() {
		livro.adicionarImagem(imagem);
		JsfUtil.sendInfoMessage("Imagem adicionada com sucesso!");
	}
	
	public void removerImagem(int index) {
		livro.removerImagem(index);
	}
	
	public void enviarImagem(FileUploadEvent event) {
		
		try {
			
			imagem.setArquivo(event.getFile().getContents());
			imagem.setNome(event.getFile().getFileName().replaceAll("[ ]", "_"));
			
			JsfUtil.sendInfoMessage("Imagem enviada com sucesso!");
			
		} catch (Exception e) {

			JsfUtil.sendErrorMessage("Falha no envio da imagem. Erro: " + JsfUtil.getErrorMessage(e));
			
		}
		
	}
	
	public void downloadImagem(int index) {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext ec = facesContext.getExternalContext();
		HttpServletResponse httpResponse = (HttpServletResponse) ec.getResponse();
		
		Imagem imagemDownload = livro.getImagens().get(index);
		
		try {
			httpResponse.addHeader("Content-Disposition", "attachment; filename=" + imagemDownload.getNome());
			httpResponse.setContentLength(imagemDownload.getArquivo().length);
			httpResponse.setContentType("application/octet-stream");
			httpResponse.getOutputStream().write(imagemDownload.getArquivo());
			httpResponse.getOutputStream().flush();
			facesContext.responseComplete();
		} catch (Exception e) {
			JsfUtil.sendErrorMessage("Erro no download da imagem.");
		}
		
	}
	
	public void visualizarImagem(int index) {
		imagem = livro.getImagens().get(index);
	}
	
	public StreamedContent getImagemDinamica() {
		if (imagem != null) {
			return new DefaultStreamedContent(new ByteArrayInputStream(imagem.getArquivo()));
		}
		return new DefaultStreamedContent();
	}
	
	public void removerAutor(int index) {
		livro.getAutores().remove(index);
	}
	
	public List<Autor> getAutores() {
		if (autores == null) {
			autores = autorDao.getLista();
		}
		return autores;
	}

	public List<Autor> getAutoresSelecionados() {
		return autoresSelecionados;
	}
	
	public void setAutoresSelecionados(List<Autor> autoresSelecionados) {
		this.autoresSelecionados = autoresSelecionados;
	}
	
	public Imagem getImagem() {
		return imagem;
	}
	
	public void setImagem(Imagem imagem) {
		this.imagem = imagem;
	}
	
	public Livro getLivro() {
		return livro;
	}
	
	public FiltroTabelaLazy<Livro> getTabelaLazy() {
		return tabelaLazy;
	}
	
}
